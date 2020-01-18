package com.lilithsthrone.controller.xmlParsing;
import java.io.File;

/**
 * @author BlazingMagpie@gmail.com (or ping BlazingMagpie in Discord)
 * @since 0.2.5.8
 * @version 0.2.12
 * 
 * <p>Exception thrown when XML file fails to load, with details for exact cause.</p>
 */
public class XMLLoadException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * @param cause Actual exception that was thrown, for example SAXException or XMLMissingTagException.
	 */
	public XMLLoadException(Throwable cause){	
		super("XML file failed to load. Cause: " + cause.getMessage(), cause);
	}

	public XMLLoadException(Throwable cause, File causedByFile){
		super("XML file ("+causedByFile.getParentFile().getName()+"/"+causedByFile.getName()+") failed to load. Cause: " + cause.getMessage(), cause);
	}
}