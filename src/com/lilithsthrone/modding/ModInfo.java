/**
 * 
 */
package com.lilithsthrone.modding;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lilithsthrone.controller.ModController;
import com.lilithsthrone.main.Main;
import com.vdurmont.semver4j.Semver;

/**
 * Mod metadata.
 * @author Anonymous-BCFED
 *
 */
public class ModInfo {
    public String id = "";
    public String name = "";
    public Semver version = new Semver("0.0.0");
    public String[] authors;
    public String website = "";
    public String sourceCode = "";

    public ModSpec[] requires;
    public ModSpec[] conflicts;
    
    public BasePlugin plugin = null;
    
    public File modDir = null;
    public File pluginJar = null;
    public byte[] pluginHash = null;
    
    public static ModInfo loadFromXML(Element parentElement, Document doc) {
        /*
         * <modInfo>
		 *  <id>ExampleMod</id>
         *  <name>My Cool Mod</name>
         *  <version>0.1.2</version>
         *  <contributors>
         *   <contributor>Anonymous-BCFED</contributor>
         *   <contributor>Innoxia</contributor>
         *  </contributors>
         *  <website>https://github.com/Anonymous-BCFED/...</website>
         *  <sourceCode>https://github.com/Anonymous-BCFED/...</sourceCode>
         *  <requires>
         *   <mod id="game" version=">0.4.2" />
         *  </requires>
         *  <conflicts>
         *   <mod id="badMod" version="==1.2.3" />
         *  </conflicts>
         * </modInfo>
         */
        ModInfo mod = new ModInfo();
        mod.id = ((Element) parentElement.getElementsByTagName("id").item(0)).getTextContent().strip();
        mod.name = ((Element) parentElement.getElementsByTagName("name").item(0)).getTextContent().strip();
        mod.version = new Semver(((Element) parentElement.getElementsByTagName("version").item(0)).getTextContent().strip());
        
        NodeList authorsList = ((Element)parentElement.getElementsByTagName("contributors").item(0)).getElementsByTagName("contributor");
        mod.authors = new String[authorsList.getLength()];
        for (int i = 0;i<mod.authors.length;i++) {
            mod.authors[i] = ((Element)authorsList.item(i)).getTextContent().strip();
        }
        
        mod.website = ((Element) parentElement.getElementsByTagName("website").item(0)).getTextContent().strip();
        mod.sourceCode = ((Element) parentElement.getElementsByTagName("sourceCode").item(0)).getTextContent().strip();

        if(parentElement.getElementsByTagName("requires").getLength() > 0) {
            NodeList requirementsList = ((Element)parentElement.getElementsByTagName("requires").item(0)).getElementsByTagName("mod");
            mod.requires = new ModSpec[requirementsList.getLength()];
            for(int i=0;i<mod.requires.length;i++) {
                mod.requires[i] = ModSpec.loadFromXML((Element)requirementsList.item(i), doc);
            }
        }
        if(parentElement.getElementsByTagName("conflicts").getLength() > 0) {
            NodeList conflictsList = ((Element)parentElement.getElementsByTagName("conflicts").item(0)).getElementsByTagName("mod");
            mod.conflicts = new ModSpec[conflictsList.getLength()];
            for(int i=0;i<mod.conflicts.length;i++) {
                mod.conflicts[i] = ModSpec.loadFromXML((Element)conflictsList.item(i), doc);
            }
        }
        return mod;
    }

	public boolean validate() {
		return this.id!=null && !this.id.isEmpty()
            && this.name!=null && !this.name.isEmpty()
            && this.version!=null;
	}

    public boolean hasPlugin() {
        if(pluginJar == null)
            pluginJar = new File(modDir, "plugin.jar");
        return pluginJar.isFile();
    }

    /**
     * TODO: Implement
     */
	public void loadModXML(ModController mc) {}

    /**
     * Loads plugin from JAR.
     */
    public void loadPlugin(ModController mc) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(this.pluginJar);
            URL[] url = {new URL("jar:file:"+this.pluginJar.toString()+"!/")};
            
            URLClassLoader cl = URLClassLoader.newInstance(url);
            Enumeration<JarEntry> entries = jarFile.entries();
            while(entries.hasMoreElements()){
                JarEntry entry = entries.nextElement();
                if(entry.isDirectory() || !entry.getName().endsWith(".class"))
                    continue;
                String className = entry.getName().substring(0, entry.getName().length() - 6);
                className = className.replace('/', '.');
                Class cls = cl.loadClass(className);
                if(BasePlugin.class.isAssignableFrom(cls)) {
                    // Could throw SecurityException, NoSuchMethodException
                    this.plugin = (BasePlugin) cls.getConstructor(null).newInstance(null);
                    break;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(jarFile!=null) {
                try {
                    jarFile.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static ModInfo loadFromXML(File modXMLFile) throws SAXException, IOException {
        Document doc = Main.getDocBuilder().parse(modXMLFile);

        // Cast magic:
        doc.getDocumentElement().normalize();
        return ModInfo.loadFromXML(doc.getDocumentElement(), doc);
    }
}
