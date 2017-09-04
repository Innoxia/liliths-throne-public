package com.base.game.inventory.clothing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.6?
 * @version 0.1.78
 * @author Innoxia
 */
public class BlockedParts implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DisplacementType displacementType;
	public List<ClothingAccess> clothingAccessRequired;
	public List<CoverableArea> blockedBodyParts;
	public List<ClothingAccess> clothingAccessBlocked;

	/**
	 * A class that holds information about what clothing-related parts are
	 * blocked.
	 * 
	 * @param displacementType
	 *            The type of displacement that's required to block/unblock
	 *            the areas .
	 * @param clothingAccessRequired
	 *            The access required in order to achieve the
	 *            DisplacementType.
	 * @param blockedBodyParts
	 *            The body parts that this displacement blocks/reveals.
	 * @param clothingAccessBlocked
	 *            The clothing access that this displacement blocks/reveals.
	 */
	public BlockedParts(DisplacementType displacementType, List<ClothingAccess> clothingAccessRequired, List<CoverableArea> blockedBodyParts, List<ClothingAccess> clothingAccessBlocked) {

		this.displacementType = displacementType;

		if (clothingAccessRequired != null)
			this.clothingAccessRequired = clothingAccessRequired;
		else
			this.clothingAccessRequired = new ArrayList<>();

		if (blockedBodyParts != null)
			this.blockedBodyParts = blockedBodyParts;
		else
			this.blockedBodyParts = new ArrayList<>();

		if (clothingAccessBlocked != null)
			this.clothingAccessBlocked = clothingAccessBlocked;
		else
			this.clothingAccessBlocked = new ArrayList<>();
	}
}
