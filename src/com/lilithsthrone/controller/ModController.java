package com.lilithsthrone.controller;
import java.io.File;
import java.util.HashMap;

import com.lilithsthrone.modding.BasePlugin;
import com.lilithsthrone.modding.LegacyModInfo;
import com.lilithsthrone.modding.ModInfo;
import com.lilithsthrone.modding.ModSpec;
import com.lilithsthrone.modding.PluginHashes;
import com.vdurmont.semver4j.Semver;

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
    public Set<String> pluginsNeedingConfirmation;
    
    public ModController() {
        this.modList = new HashMap<String, ModInfo>();
        this.pluginChecksums = new HashMap<String, String>();
    }
    
    public void load() {
    	loadOldMods();
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
            }
        }
    }

    public bool checkHashOf(mod) {
        File("data/modhashes.dat")
    }
    
    public void addMod(ModInfo mod) {
    	this.modList.put(mod.id, mod);
    	if (mod.hasPlugin()) {
    		if (this.checkHashOf(mod)) {
                mod.loadPlugin(this);
            }
    	}
        mod.loadModXML();
    }
    
}