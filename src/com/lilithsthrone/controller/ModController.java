package com.lilithsthrone.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.modding.BasePlugin;
import com.lilithsthrone.modding.LegacyModInfo;
import com.lilithsthrone.modding.ModInfo;
import com.lilithsthrone.modding.ModSpec;
import com.lilithsthrone.modding.PluginHashes;
import com.lilithsthrone.modding.EJarIntegrityResult;
import com.vdurmont.semver4j.Semver;

import org.xml.sax.SAXException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * This class handles most of the heavy lifting RE mod loading and management.
 * 
 * The class loads them from mods/ (if it exists; formerly res/mods).
 * 
 * Loading a mod consists of:
 *   1. Finding a manifest file in mods/mod_dir/mod.xml
 *   2. Loading the manifest into ModInfo
 *   3. If there's a JAR:
 *   	1. Generate checksum of JAR
 *   	2. Check against any known hash values
 *      3. If different, tell the user:
 *      	* A mod with code has been added or changed
 *          * Mods with code can be dangerous
 *          * Loading mods is done at the user's own risk
 *      4. Write checksums to disk for next time
 *   4. Load built-ins
 *   5. Load mods
 * @author Anonymous-BCFED
 * @since FIXME
 */
public class ModController {
    public static final File MOD_FOLDER = new File("mods");
    public static final File OLD_MOD_FOLDER = new File("res/mods");
    
    public HashMap<String, ModInfo> modList;
    public PluginHashes pluginHashes = new PluginHashes();
    
    public HashSet<String> jarHashesChanged = new HashSet<String>();
    public HashSet<String> jarHashesNew = new HashSet<String>();
    public HashSet<String> modsLoadable = new HashSet<String>();
    
    public ModController() {
        this.modList = new HashMap<String, ModInfo>();
        this.load();
    }
    
    public void load() {
    	loadOldMods(); // TODO: Deprecate
        loadNewMods();

        if(jarHashesNew.size() > 0 || jarHashesChanged.size() > 0) {
            String changes = "";
            if(jarHashesNew.size() > 0) {
                changes += "New mods with plugins:\n";
                for(String modID : jarHashesNew) {
                    changes += " + "+modList.get(modID).name+"\n";
                }
            }
            if(jarHashesChanged.size() > 0) {
                changes += "Mods with changed plugins:\n";
                for(String modID : jarHashesChanged) {
                    changes += " * "+modList.get(modID).name+"\n";
                }
            }
            Alert a = new Alert(AlertType.WARNING, 
                "Several mods with plugins have been changed and/or added to your game's mods/ directory.\n\n"
                + "Plugins allow modders to add or remove features that need accompanying code, but this code can also be DANGEROUS.\n\n"
                + "DO YOU TRUST THESE MODS?",
                ButtonType.YES,
                ButtonType.NO);
			System.err.println("New or changed Jars.");
            a.showAndWait().ifPresent(response -> {
                if(response == ButtonType.YES) {
                    this.confirmAll();
                }
                else if(response == ButtonType.NO) {
                    Main.restartProcess();
                }
            });
            return;
        }

        for(ModInfo mod : modList) {
            mod.loadXML(this);
        }
    }

    private void confirmAll() {
        for(ModInfo mod : modList.values()) {
            this.pluginHashes.confirm(mod);
        }
        this.pluginHashes.save();
        Main.restartProcess();
    }

    private void loadOldMods() {
        // res/mods/author/mod/

        // If the mod folder doesn't exist or isn't a directory, we exit the method.
        if (!OLD_MOD_FOLDER.isDirectory())
            return;
        String authorID;
        ModInfo modInfo;
        // Find author directories.
        for(File authorDir : OLD_MOD_FOLDER.listFiles()) {
            // Make sure we only see subdirs.
            if(!authorDir.isDirectory())
                continue;
            authorID = authorDir.getName();
            for(File modDir : authorDir.listFiles()) {
                // Make sure we only see directories.
                if(!modDir.isDirectory())
                    continue;
                modInfo = new LegacyModInfo(); // Prevents loading JARs
                modInfo.id = modDir.getName();
                modInfo.authors = new String[]{authorID};
                modInfo.name = modInfo.name + " (LEGACY)";
                modInfo.version = new Semver("0.0.0");
                modInfo.conflicts = new ModSpec[0];
                modInfo.requires = new ModSpec[0];
                modInfo.modDir = modDir;
                this.addMod(modInfo);
                System.out.println(String.format(
                    "WARNING: Mod {0} is of an older format. This may not be loadable",
                    modInfo.id));
            }
        }
    }

    private void loadNewMods() {
        // Make sure there's a mod folder at all before we iterate it.
        if(!MOD_FOLDER.isDirectory())
            return;

        // We don't need to make assumptions about the mod via folder name, so no more strings floating around.
        for(File modSubDir : MOD_FOLDER.listFiles()) {
            // Make sure we only see subdirs.
            if (!modSubDir.isDirectory())
                continue;

            File modXMLFile = new File(modSubDir, "mod.xml");
            if(!modXMLFile.isFile()) {
                System.out.println(String.format(
                    "WARNING: Mod in folder {0} not loaded (mod.xml missing).",
                    modSubDir.toString()
                ));
            }

            ModInfo mod;
            try {
                mod = ModInfo.loadFromXML(modXMLFile);
                mod.modDir = modSubDir;
                this.addMod(mod);
            } catch (SAXException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public void addMod(ModInfo mod) {
    	this.modList.put(mod.id, mod);
    	if (mod.hasPlugin()) {
    		switch(pluginHashes.check(mod)) {
                case CHANGED:
                    jarHashesChanged.add(mod.id);
                    break;
                case NEW:
                    jarHashesNew.add(mod.id);
                    break;
                case OK:
                    modsLoadable.add(mod.id);
                    break;
            }
    	} else {
            modsLoadable.add(mod.id);
        }
    }
    
}