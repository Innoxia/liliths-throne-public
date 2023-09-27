package com.lilithsthrone.game.dialogue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.main.Main;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class AbstractDialogueFlagValue {
	
	private String id;
	private int resetHour = -1;

	public AbstractDialogueFlagValue() {
		this(false);
	}
	
	public AbstractDialogueFlagValue(boolean dailyReset) {
		if(dailyReset) {
			resetHour = 0;
		}
	}
	
	public static List<AbstractDialogueFlagValue> loadFlagsFromFile(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in AbstractDialogueFlagValue files it's <dialogueFlags>
				
				List<AbstractDialogueFlagValue> flags = new ArrayList<>();
				for(Element e : coreElement.getAllOf("flag")) {
					AbstractDialogueFlagValue newFlag = new AbstractDialogueFlagValue(Boolean.valueOf(e.getAttribute("dailyReset".trim())));
					if(!e.getAttribute("resetHour").isEmpty()) {
						newFlag.resetHour = Integer.valueOf(e.getAttribute("resetHour"));
					}
					newFlag.id = author + "_" + e.getTextContent();
//					System.out.println(newFlag.id);
					flags.add(newFlag);
				}
				
				return flags;
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractDialogueFlagValue was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
		return null;
	}

	public int getResetHour() {
		return resetHour;
	}

	/**
	 * @return The id for this flag.
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
