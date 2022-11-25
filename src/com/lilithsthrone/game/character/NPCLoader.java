package com.lilithsthrone.game.character;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.ModNPC;
import com.lilithsthrone.game.dialogue.utils.ParserTarget;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Helper class to handle loading NPC from a file.
 */
public class NPCLoader {
    private final Map<String, Class<? extends NPC>> classMap = new ConcurrentHashMap<>();
    private final Map<Class<? extends NPC>, Method> loadFromXMLMethodMap = new ConcurrentHashMap<>();
    private final Map<Class<? extends NPC>, Constructor<? extends NPC>> constructorMap = new ConcurrentHashMap<>();
    private final DocumentBuilder builder;

    public NPCLoader(final DocumentBuilder builder) {
        this.builder = builder;
    }

    Class<? extends NPC> getClassFor(final String className) throws ClassNotFoundException {
        if (!classMap.containsKey(className)) {
            Class<? extends NPC> npcClass = (Class<? extends NPC>) Class.forName(className);
            classMap.putIfAbsent(className, npcClass);
        }

        return classMap.get(className);
    }

    Method getLoadFromXMLMethod(final Class<? extends NPC> npcClass) throws NoSuchMethodException {
        if (!loadFromXMLMethodMap.containsKey(npcClass)) {
            final Method m = npcClass.getMethod("loadFromXML", Element.class, Document.class, CharacterImportSetting[].class);
            loadFromXMLMethodMap.put(npcClass, m);
        }

        return loadFromXMLMethodMap.get(npcClass);
    }

    Constructor <? extends NPC> getConstructor(final Class<? extends NPC> npcClass) throws NoSuchMethodException {
        if (!constructorMap.containsKey(npcClass)) {
            final Constructor<? extends NPC> declaredConstructor = npcClass.getDeclaredConstructor(boolean.class);
            constructorMap.put(npcClass, declaredConstructor);
        }

        return constructorMap.get(npcClass);
    }

    /**
     * Used instantiate an NPC instance from XML.
     *
     * @param doc XML Document that the NPC is being loaded from.
     * @param e Element within the document representing the NPC Instance.
     * @param className Class to use when loading the NPC.
     *
     * @return null|Class<? extends NPC>
     */
    public NPC loadNPC(final Document doc, final Element e, final String className) {
        return loadNPC(doc, e, className, null);
    }

    /**
     * Used instantiate an NPC instance from XML.
     *
     * @param doc XML Document that the NPC is being loaded from.
     * @param e Element within the document representing the NPC Instance.
     * @param className Class to use when loading the NPC.
     * @param loadHook Allows modification of the instantiated NPC object before it is fully loaded.
     *
     * @return null|Class<? extends NPC>
     */
    public NPC loadNPC(final Document doc, final Element e, final String className, final Consumer<NPC> loadHook) {
        try {
            final Class<? extends NPC> npcClass = getClassFor(className);
            final NPC npc = getConstructor(npcClass).newInstance(true);

            // Apply preloading hooks
            if(loadHook != null) {
                loadHook.accept(npc);
            }

            getLoadFromXMLMethod(npcClass).invoke(npc, e, doc, new CharacterImportSetting[] {});

            return npc;
        } catch(NoSuchMethodException nsme) {
            System.err.println("Couldn't find required method(loadFromXML or constructor(boolean)) for class: " + className);
            nsme.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            System.err.println("Unable to find NPC class: " + className);
            cnfe.printStackTrace();
        } catch(Exception ex) {
            System.err.println("Failed to load NPC class: "+className);
            ex.printStackTrace();
        }

        return null;
    }

    NPC loadNPC(final File xmlFile, final String modPrefix) throws IOException, SAXException {
        try {
            final Document doc = builder.parse(xmlFile);
            final Element root = doc.getDocumentElement();

            root.normalize();

            return loadNPC(doc,
                    root,
                    ModNPC.class.getCanonicalName(),
                    npc -> ((ModNPC)npc).setModIdOverride(modPrefix));

        } catch (IOException ex) {
            System.err.println("IOException while loading: "+ xmlFile.getCanonicalPath());
            ex.printStackTrace();
        } catch (SAXException ex ) {
            System.err.println("SAXException while parsing: "+ xmlFile.getCanonicalPath());
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Used to load XML based mod characters.
     *
     * @param game A game instance to attach mod characters to.
     */
    public void loadModNPC(final Game game) throws IOException, SAXException {
        final Map<String, Map<String, File>> modNPCFiles = Util.getExternalModFilesById("/npcs");
        final Map<String, NPC> npcMap = game.getNPCMap();

        // There's a way to do this with streams, but I can't think of it right now.
        for(Map.Entry<String, Map<String, File>> authorEntry : modNPCFiles.entrySet()) {
            for (Map.Entry<String, File> modCharacterEntry : authorEntry.getValue().entrySet()) {
                final String idPrefix = modCharacterEntry.getKey();
                final File npcFile = modCharacterEntry.getValue();

                NPC npc = loadNPC(npcFile, idPrefix);
                if (npc == null) {
                    continue;
                }

                // Remove the previous version of the NPC
                if (npcMap.containsKey(npc.getId())) {
                    NPC npcSaved = Main.game.getNpc(idPrefix);
                    Main.game.removeNPC(npcSaved);
                }

                try {
                    game.addNPC(npc, true);
                } catch (Exception e) {
                    System.err.println("Unable to Add NPC: " + npcFile.getCanonicalPath());
                    e.printStackTrace();
                }

                // Can't add a target that already exists.
                if (ParserTarget.getParserTargetFromId(idPrefix, true) != null) {
                    ParserTarget.removeAdditionalParserTarget(npc);
                }

                ParserTarget.addAdditionalParserTarget(idPrefix, npc);
            }
        }
    }
}
