package com.lilithsthrone.controller.xmlParsing;

/**
 * @author BlazingMagpie@gmail.com (or ping BlazingMagpie in Discord)
 * @since 0.2.5.8
 * 
 * <p>Exception thrown when mandatory tag is missing, thus making XML mod invalid. If mod can still be loaded and used without a tag, use {@link Element#getOptionalFirstOf(java.lang.String)} instead</p>
 */
public class XMLMissingTagException extends Exception {
	/**
	 * <p>Error that reports the missing tag and file in which missing tag was reported.</p>
	 * 
	 * @param tag String tag that was attempted to look for
	 * @param fileDirectory String location of the file which has missing tag
	 */
	public XMLMissingTagException(String tag, String fileDirectory){
		super(String.format("XML missing tag exception: \"%s\" tag is required, but not found in %s", tag, fileDirectory));
	}
}
