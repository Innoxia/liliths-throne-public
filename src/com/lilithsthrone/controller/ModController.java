package com.lilithsthrone.controller;
import java.util.HashMap;

import com.lilithsthrone.modding.BaseMod;
import com.lilithsthrone.modding.ModInfo;

/**
 * This class handles most of the heavy lifting RE mod loading and management.
 * 
 * The class loads them from mods/ (if it exists; formerly res/mods).
 * 
 * Loading a mod consists of:
 *   1. Finding a manifest file in mods/mod_dir/mod.xml
 *   2. Loading the manifest into ModInfo
 *   3. If the manifest declares an entry point (BaseMod class):
 *   	1. Generate checksums of all known files
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
    public static final String MOD_FOLDER = "mods";
    
    public HashMap<String, ModInfo> modList;
    public HashMap<String, String> pluginChecksums;
    public Set<String> pluginsNeedingConfirmation;
    
    public ModController() {
        this.modList = new HashMap<String, ModInfo>();
        this.pluginChecksums = new HashMap<String, String>();
    }
    
    public void addMod(ModInfo mod) {
    	this.modList[mod.id] = mod;
    	if (mod.hasPlugin()) {
    		
    	}
    }
    
}