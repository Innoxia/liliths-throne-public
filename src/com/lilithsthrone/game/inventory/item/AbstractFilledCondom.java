package com.lilithsthrone.game.inventory.item;

import java.io.Serializable;

/**
 * @since 0.1.86
 * @version 0.1.86
 * @author Innoxia
 */
public class AbstractFilledCondom extends AbstractItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public AbstractFilledCondom(AbstractItemType itemType) {
		super(itemType);
	}

}
