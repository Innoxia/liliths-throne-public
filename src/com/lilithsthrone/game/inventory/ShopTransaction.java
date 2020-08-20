package com.lilithsthrone.game.inventory;

/**
 * @since 0.1.65
 * @version 0.3.4.5
 * @author Innoxia
 */
public class ShopTransaction {

	private AbstractCoreItem abstractItemSold;
	private int price;
	private int count;

	public ShopTransaction(AbstractCoreItem abstractItemSold, int price, int count) {
		this.abstractItemSold = abstractItemSold;
		this.price = price;
		this.count = count;
	}

	public AbstractCoreItem getAbstractItemSold() {
		return abstractItemSold;
	}

	public int getPrice() {
		return price;
	}

	public int getCount() {
		return count;
	}
	
	public void incrementCount(int increment) {
		count+=increment;
	}
}