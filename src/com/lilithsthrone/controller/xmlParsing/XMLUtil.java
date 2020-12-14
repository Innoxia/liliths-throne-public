package com.lilithsthrone.controller.xmlParsing;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Helper methods for XMl saving/loading. Used to be in CharacterUtils for some reason.
 * 
 * @since 0.4
 * @version 0.4.0
 * @author Innoxia
 */
public class XMLUtil {
	
	public static void createXMLElementWithValue(Document doc, Element parentElement, String elementName, String value){
		Element element = doc.createElement(elementName);
		parentElement.appendChild(element);
		Attr attr = doc.createAttribute("value");
		attr.setValue(value);
		element.setAttributeNode(attr);
	}
	
	public static void addAttribute(Document doc, Element parentElement, String attributeName, String value){
		Attr attr = doc.createAttribute(attributeName);
		attr.setValue(value);
		parentElement.setAttributeNode(attr);
	}
	
}
