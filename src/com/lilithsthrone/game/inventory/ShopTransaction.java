package com.lilithsthrone.game.inventory;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.65
 * @version 0.1.89
 * @author Innoxia
 */
public class ShopTransaction implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

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
		
		CharacterUtils.addAttribute(doc, element, "price", String.valueOf(this.getPrice()));
		
		Element innerElement = doc.createElement("item");
		element.appendChild(innerElement);
		
		this.getAbstractItemSold().saveAsXML(innerElement, doc);
		
		return element;
	}
	
	public static AbstractItem loadFromXML(Element parentElement, Document doc) {
		return AbstractItemType.generateItem(ItemType.idToItemMap.get(parentElement.getAttribute("id")));
	}

	public AbstractCoreItem getAbstractItemSold() {
		return abstractItemSold;
	}

	public int getPrice() {
		return price;
	}
}