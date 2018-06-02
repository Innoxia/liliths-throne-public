package com.lilithsthrone.controller.xmlParsing;

/**
 * @author BlazingMagpie@gmail.com (or ping BlazingMagpie in Discord)
 * @since 0.2.5.8
 * 
 * <p>Exception thrown when XML file fails to load, with details for exact cause.</p>
 */
public class XMLLoadException extends Exception {
	/**
	 * @param cause Actual exception that was thrown, for example SAXException or XMLMissingTagException.
	 */
	public XMLLoadException(Throwable cause){	
		super("XML file failed to load. Cause: " + cause.getClass().getSimpleName(), cause);
	}
}