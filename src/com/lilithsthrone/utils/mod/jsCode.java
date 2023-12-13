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
    private static void loadFromXML(File XMLFile){
        assert XMLFile.exists() : "file dosn't exist";
        try{
            Element coreElement = Element.getDocumentRootElement(XMLFile);
            UtilText.parse(coreElement.getTextContent());
        }catch (Exception ex){
            ex.printStackTrace();
            System.err.println("prase mod code fail! (" + XMLFile.getName() + ")\n" + ex);
        }
    }

    public  static  void initModJsCode(){
        Map<String, Map<String, File>> filesMap = Util.getExternalModFilesById("/code", null, "initCode");
        for(Map.Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
            for(Map.Entry<String, File> innerEntry : entry.getValue().entrySet()) {
                try {
                    loadFromXML(innerEntry.getValue());
                } catch(Exception ex) {
                    System.err.println("load mod code fail. File path: "+innerEntry.getValue().getAbsolutePath());
                    System.err.println("Actual exception: ");
                    ex.printStackTrace(System.err);
                }
            }
        }

    }
}
