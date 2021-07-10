package com.lilithsthrone.controller.xmlParsing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author BlazingMagpie@gmail.com (or ping BlazingMagpie in Discord)
 * @version 0.2.12
 *
 * <p>Wrapper for org.w3c.dom.Element that's supposed to simplify mod support</p>
 * @since 0.2.5.8
 */
public class Element {
	
	private static final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder docBuilder;
	
	static {
		if (docBuilder == null) {
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
	}
	
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
	private Element(org.w3c.dom.Element w3cElement, String fileDirectory, org.w3c.dom.Document document) {
		innerElement = w3cElement;
		this.fileDirectory = fileDirectory;
		this.document = document;
	}
	
	/**
	 * Loads and prepares document for reading and returns root element, for example {@code <clothing>} in clothes mods.
	 *
	 * @param xmlFile File in XML format. Throws an exception with necessary details if something goes wrong. {@link XMLLoadException} for mode details.
	 * @return Element at the root of the document
	 * @throws XMLLoadException
	 */
	public static Element getDocumentRootElement(File xmlFile) throws XMLLoadException{
		try{
			String fileDirectory = xmlFile.getAbsolutePath();
			Document parsedDocument = docBuilder.parse(xmlFile);
			parsedDocument.getDocumentElement().normalize();
			return new Element(parsedDocument.getDocumentElement(), fileDirectory, parsedDocument);
		} catch(Exception e){
			throw new XMLLoadException(e);
		}
	}
	
	public static Element createNewDocument(String root) {
		if (docBuilder == null) {
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		Document doc = docBuilder.newDocument();
		doc.getDocumentElement().normalize();
		org.w3c.dom.Element rootElement = doc.createElement(root);
		doc.appendChild(rootElement);
		return new Element(doc.getDocumentElement(), "", doc);
	}
	
	public static Element getElement(org.w3c.dom.Element w3cElement, String fileDirectory, org.w3c.dom.Document document) throws XMLLoadException {
		try {
			return new Element(w3cElement, "", document);
		} catch(Exception e){
			throw new XMLLoadException(e);
		}
	}
	
	public Element addElement(String tagName) {
		org.w3c.dom.Element element = document.createElement(tagName);
		innerElement.appendChild(element);
		return new Element(element, "", document);
	}
	
	// Add Element with a single attribute - Paired with getValue
	public Element addElement(String tagName, String value){
		Element element = addElement(tagName);
		element.addAttribute("value", value);
		return element;
	}
	
	public void removeElement(String id) {
		org.w3c.dom.Element element = document.getElementById(innerElement.getTagName());
		NodeList children = element.getElementsByTagName(id);
		for(int i=0; i<children.getLength(); i++) {
			element.removeChild(children.item(i));
		}
	}
	
	public void addComment(String comment) {
		org.w3c.dom.Comment element = document.createComment(comment);
		innerElement.appendChild(element);
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
	 * Returns value of attribute requested by name.</p>
	 *
	 * @param name String name of the attribute to return value of
	 * @return Attribute as String, for example:
	 * {@code <element attribute = "value"></element>} returns "value". Returns an empty String if no such attribute was present.
	 */
	public String getAttribute(String name) {
		return innerElement.getAttribute(name);
	}
	
	public String getValue() {
		return innerElement.getAttribute("value");
	}

	public void addAttribute(String attributeName, String value){
		Attr attr = document.createAttribute(attributeName);
		attr.setValue(value);
		innerElement.setAttributeNode(attr);
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
			System.err.printf("DOM exception: text content in element \"%s\" probably exceeds max allowed."
							+"XML parsing will try to continue with empty text content%n",
					getTagName());
			return "";
		}
	}
	
	public void setTextContent(String s) {
		innerElement.setTextContent(s);
	}
	
	/**
	 * Returns the document.
	 *
	 * @return w3c DOM Document element is in.
	 */
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
	 *		(Element e) ->{
	 *		System.out.println(e.getTextContent);
	 *		}
	 * );}
	 * </pre>
	 *
	 * @param tag String tag of the element to return.
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
	 * @return {@link java.util.List} of Elements with given tag.
	 */
	public List<Element> getAllOf(String tag) {
		org.w3c.dom.NodeList nl = innerElement.getElementsByTagName(tag);
		List<Element> returnList = new ArrayList<>();
		for (int i = 0; i < nl.getLength(); i++) {
			returnList.add(new Element((org.w3c.dom.Element) nl.item(i), this.fileDirectory, this.document));
		}
		return returnList;
	}
	
	public List<Element> getAll(){
		org.w3c.dom.NodeList nl = innerElement.getChildNodes();
		List<Element> returnList = new ArrayList<>();
		for (int i = 0; i < nl.getLength(); i++) {
			if(nl.item(i) instanceof org.w3c.dom.Element) {
				returnList.add(new Element((org.w3c.dom.Element) nl.item(i), this.fileDirectory, this.document));
			}
		}
		return returnList;
	}
}