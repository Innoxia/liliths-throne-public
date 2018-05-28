package com.lilithsthrone.controller.xmlParsing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

/**
 * @author BlazingMagpie@gmail.com (or ping BlazingMagpie in Discord)
 * @since 0.2.5.8
 * 
 * <p>Wrapper for org.w3c.dom.Element that's supposed to simplify mod support</p>
 */
public class Element {

    private final org.w3c.dom.Element innerElement;//contains wrapped element
    private final String fileDirectory;//contains location of the file to show in case of error
    private final org.w3c.dom.Document document;//document the element is in, legacy

    /**
     * <p>
     * Construct new instance with given {@linkplain  org.w3c.dom.Element} and
     * location of XML document as String (used only for error reporting).</p>
     * <p>
     * Should only be made by other Element or {@link Element#getDocumentRootElement(java.io.File) }.</p>
     *
     * @param w3cElement {@link org.w3c.dom.Element} to wrap
     * @param fileDirectory String of a location of original file
     */
    Element(org.w3c.dom.Element w3cElement, String fileDirectory, org.w3c.dom.Document document) {
	innerElement = w3cElement;
	this.fileDirectory = fileDirectory;
	this.document = document;
    }
    /**
     * Loads and prepares document for reading and returns root element, for example {@code <clothing>} in clothes mods.
     *
     * @param xmlFile File in XML format. Throws an exception with necessary details if something goes wrong. {@link XMLLoadException} for mode details.
     *
     * @return Element at the root of the document
     * @throws XMLLoadException
     */
    public static Element getDocumentRootElement(File xmlFile) throws XMLLoadException{
	try{
	    
	    String fileDirectory = xmlFile.getAbsolutePath();
	    Document parsedDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
	    parsedDocument.getDocumentElement().normalize();
	    Element e = new Element(parsedDocument.getDocumentElement(),fileDirectory, parsedDocument);
	    return e;
	}
	catch(Exception e){
	    throw new XMLLoadException(e);	
	}	
    }
    /**
     * <p>
     * Returns tag name of the element</p>
     *
     * @return Tag name as String, for example: {@code <element></element>}
     * returns "element"
     */
    public String getTagName() {
	return innerElement.getTagName();
    }

    /**
     * <p>
     * Returns value of attribute requested by name</p>.
     *
     * @param name String name of the attribute to return value of
     *
     * @return Attribute as String, for example:
     * {@code <element attribute = "value"></element>} returns "value".
     */
    public String getAttribute(String name) {
	return innerElement.getAttribute(name);
    }

    /**
     * <p>
     * Returns raw text inside the element.</p>
     *
     * <p>
     * If text content size exceeds allowed by internal method, error will be
     * output, but method will continue and return empty string.</p>
     *
     * @return Text content as String, for example:
     * {@code <element>Inner text</element>} returns "Inner text".
     */
    public String getTextContent() {
	try {
	    return innerElement.getTextContent();
	} catch (DOMException ex) {
	    System.err.println(String.format("DOM exception: text content in element \"%s\" probably exceeds max allowed."
		    + "XML parsing will try to continue with empty text content",
		    getTagName()));
	    return "";
	}
    }
    /**
     * Returns inner element. Used to support legacy code
     * 
     * @return w3c DOM Element that wrapper contains
     * @deprecated 
     */
    @Deprecated
    public org.w3c.dom.Element getInnerElement(){
	return innerElement;
    }
    
    /**
     * Returns the document. Used for legacy code
     *
     * @return w3c DOM Document element is in.
     * @deprecated 
     */
    @Deprecated
    public org.w3c.dom.Document getDocument(){
	return document;
    }
    /**
     * <p>
     * Returns first child element with a given tag, throwing a
     * {@link XMLMissingTagException} if no such tag found. Used when
     * document is invalid without element.</p>
     * <p>
     * Thrown exception provides the information for which tag in which file
     * causes the error, so only one catch is needed to get all of the
     * details.</p>
     *
     * @param tag String tag of the element to return.
     *
     * @return First element found with given tag
     * @throws XMLMissingTagException
     */
    public Element getMandatoryFirstOf(String tag) throws XMLMissingTagException {
	org.w3c.dom.Element firstElement = (org.w3c.dom.Element) innerElement.getElementsByTagName(tag).item(0);
	if (firstElement == null) {
	    throw new XMLMissingTagException(tag, fileDirectory);//throw error if there's no elements found
	}
	return new Element(firstElement, fileDirectory, document);
    }

    /**
     * <p>
     * Returns first child element with a given tag, or empty
     * {@link java.util.Optional} if none are found.</p>
     * <p>
     * Use it when document is still valid if element is missing. Use
     * {@link java.util.Optional#ifPresent(java.util.function.Consumer)} to
     * create blocks that are executed only when element with a given tag is
     * found, for example: </p>
     * <pre>
     *{@code
     * parentElement.getOptionalFirstOf("extraDescription").ifPresent(
     *	    (Element e) ->{
     *		System.out.println(e.getTextContent);
     *	    }
     * );}
     * </pre>
     *
     * @param tag String tag of the element to return.
     *
     * @return First element found with given tag.
     */
    public Optional<Element> getOptionalFirstOf(String tag) {
	org.w3c.dom.Element foundElement = (org.w3c.dom.Element) innerElement.getElementsByTagName(tag).item(0);
	if (foundElement == null) {
	    return Optional.empty(); //return empty Optional if there's no elements found
	}
	return Optional.of(new Element(foundElement, fileDirectory, document));
    }

    /**
     * <p>
     * Returns iterable list of all child elements with a given tag</p>
     *
     * @param tag String tag of elements to return.
     *
     * @return {@link java.util.List} of Elements with given tag.
     */
    public List<Element> getAllOf(String tag) {
	org.w3c.dom.NodeList nl = innerElement.getElementsByTagName(tag);
	List<Element> returnList = new LinkedList<>();
	for (int i = 0; i < nl.getLength(); i++) {
	    returnList.add(new Element((org.w3c.dom.Element) nl.item(i), this.fileDirectory, this.document));
	}
	return returnList;
    }
    /**
     * <p>
     * Helper method that takes a function requiring <b>String</b> and turns it into
     * function requiring <b>Element</b> and uses it's text content as String like orginal one would. <br>
     * Mostly used to shorten certain method calls.</p>
     * 
     * <p>Example use:<br>
     * {@code (Element e) -> Integer.valueOf(e.getTextContent())}<br>
     * to<br>
     * {@code Element.applyToContents(Integer::valueOf)}</p>
     * 
     * 
     * @param <T> Return type of transformed function
     * @param useFunction Function with String parameter and variable return type to transform
     * 
     * @return Function that takes Element and returns same type as original function
     */
    public static <T> Function<Element, T> applyToContent(Function<String,T> useFunction){
	return (Element e) -> useFunction.apply(e.getTextContent());
    }
}
