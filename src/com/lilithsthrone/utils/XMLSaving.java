package com.lilithsthrone.utils;

import com.lilithsthrone.controller.xmlParsing.Element;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public interface XMLSaving {
	
	/**
	 * @param parentElement The parent element to which to save this object to.
	 * @return true if the save completed successfully.
	 */
	boolean saveAsXML(Element parentElement);

	/**
	 * @param element The parent element from which to load this object from.
	 * @return The base element for this object's XML export.
	 */
	static <T> T loadFromXML(Element element) {
		return loadFromXML(null, element);
	}
	
	/**
	 * @param log A StringBuilder to write a log to.
	 * @param element The parent element from which to load this object from.
	 * @return The base element for this object's XML export.
	 */
	static <T> T loadFromXML(StringBuilder log, Element element) {
		return null;
	}
}
