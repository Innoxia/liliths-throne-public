package com.base.game.inventory;

import java.io.Serializable;

/**
 * @since 0.1.65
 * @version 0.1.66
 * @author Innoxia
 */
public class ShopTransaction implements Serializable {

	private static final long serialVersionUID = 1L;

	private AbstractCoreItem abstractItemSold;
	private int price;

	public ShopTransaction(AbstractCoreItem abstractItemSold, int price) {
		this.abstractItemSold = abstractItemSold;
		this.price = price;
	}

	public AbstractCoreItem getAbstractItemSold() {
		return abstractItemSold;
	}

	public int getPrice() {
		return price;
	}

}
