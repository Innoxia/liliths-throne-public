package com.lilithsthrone.utils.mod;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

import java.io.File;
import java.util.Map;

/**
 * Allow mods to def their global variable, function, class by JavaScript
 *
 * @since 0.4.9
 * @version 0.4.9
 * @author Mr.Lee
 */
public class jsCode {
    public static void initModJsCode(){
        generalModJsCode("initCode");
    }

    public static void saveGameModJsCode(){
        generalModJsCode("saveGameCode");
    }

    public static void updateCheck(){
        generalModJsCode("updataCheckCode");
    }


    private static void generalModJsCode(String fileName){
        Map<String, Map<String, File>> filesMap = Util.getExternalModJsFilesById("/code", null, fileName);
        for(Map.Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
            for(Map.Entry<String, File> innerEntry : entry.getValue().entrySet()) {
                try {
                    load(innerEntry.getValue());
                } catch(Exception ex) {
                    System.err.println("load mod code fail. File path: "+innerEntry.getValue().getAbsolutePath());
                    System.err.println("Actual exception: ");
                    ex.printStackTrace(System.err);
                }
            }
        }
    }

    private static void load(File jsFile){
        assert jsFile.exists() : "file dosn't exist";
        try{
            UtilText.parseJs(jsFile);
        }catch (Exception ex){
            ex.printStackTrace();
            System.err.println("prase mod code fail! (" + jsFile.getName() + ")\n" + ex);
        }
    }
}
