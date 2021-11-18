/**
 * 
 */
package com.lilithsthrone.modding;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
    public String pluginHash = null;
    
    public static ModInfo loadFromXML(Element parentElement, Document doc) {
    	/*
    	 * <modInfo>
    	 * 	<id>ExampleMod</id>
    	 *  <name>My Cool Mod</name>
    	 *  <version>0.1.2</version>
    	 *  <contributors>
    	 *   <author>Anonymous-BCFED</author>
    	 *   <author>Innoxia</author>
    	 *  </authors>
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

	public boolean hasPlugin() {
		if(pluginJar == null)
			pluginJar = new File(modDir, "plugin.jar");
		return pluginJar.isFile();
	}
}
