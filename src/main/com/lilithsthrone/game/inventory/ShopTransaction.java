package com.lilithsthrone.game.inventory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.65
 * @version 0.3
 * @author Innoxia
 */
public class ShopTransaction implements XMLSaving {

	private AbstractCoreItem abstractItemSold;
	private int price;

	public ShopTransaction(AbstractCoreItem abstractItemSold, int price) {
		this.abstractItemSold = abstractItemSold;
		this.price = price;
	}
	
	//TODO
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("shopTransaction");
		parentElement.appendChild(element);
		
//		CharacterUtils.addAttribute(doc, element, "price", String.valueOf(this.getPrice()));
//		
//		Element innerElement = doc.createElement("item");
//		element.appendChild(innerElement);
//		
//		this.getAbstractItemSold().saveAsXML(innerElement, doc);
		
		return element;
	}
	
//	public static AbstractItem loadFromXML(Element parentElement, Document doc) {
//		return AbstractItemType.generateItem(ItemType.getIdToItemMap().get(parentElement.getAttribute("id")));
//	}

	public AbstractCoreItem getAbstractItemSold() {
		return abstractItemSold;
	}

	public int getPrice() {
		return price;
	}
}