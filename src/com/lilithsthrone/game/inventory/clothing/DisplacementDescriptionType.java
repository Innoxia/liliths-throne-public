package com.lilithsthrone.game.inventory.clothing;

/**
 * @since 0.2.4
 * @version 0.2.12
 * @author Innoxia, BlazingMagpie@gmail.com (or ping BlazingMagpie in Discord)
 */
public enum DisplacementDescriptionType {

	DISPLACEMENT_SELF,
	REPLACEMENT_SELF,
	
	DISPLACEMENT,
	DISPLACEMENT_ROUGH,
	
	REPLACEMENT,
	REPLACEMENT_ROUGH,
	
	NPC_ON_NPC_DISPLACEMENT,
	NPC_ON_NPC_DISPLACEMENT_ROUGH,
	
	NPC_ON_NPC_REPLACEMENT,
	NPC_ON_NPC_REPLACEMENT_ROUGH;
	
	/**
	 * @param tagsIdentifier "[parent element's tag name] [child element's tag name]", for example "displacementText playerSelf"
	 * @return 
	 * @throws IllegalArgumentException
	 */
	public static DisplacementDescriptionType byTagsPath(String tagsIdentifier) throws IllegalArgumentException {
		switch(tagsIdentifier){
			case "displacementText playerNPC":
			case "displacementText NPCPlayer":
			case "displacementText other":
				return DISPLACEMENT;
			case "displacementText playerNPCRough":
			case "displacementText NPCPlayerRough":
			case "displacementText otherRough":
				return DISPLACEMENT_ROUGH;
			case "displacementText playerSelf":
			case "displacementText NPCSelf":
			case "displacementText self":
				return DISPLACEMENT_SELF;
			case "displacementText NPCOtherNPC":
				return NPC_ON_NPC_DISPLACEMENT;
			case "displacementText NPCOtherNPCRough":
				return NPC_ON_NPC_DISPLACEMENT_ROUGH;

			case "replacementText playerNPC":
			case "replacementText NPCPlayer":
			case "replacementText other":
				return REPLACEMENT;
			case "replacementText playerNPCRough":
			case "replacementText NPCPlayerRough":
			case "replacementText otherRough":
				return REPLACEMENT_ROUGH;
			case "replacementText playerSelf":
			case "replacementText NPCSelf":
			case "replacementText self":
				return REPLACEMENT_SELF;
			case "replacementText NPCOtherNPC":
				return NPC_ON_NPC_REPLACEMENT;
			case "replacementText NPCOtherNPCRough":
				return NPC_ON_NPC_REPLACEMENT_ROUGH;

			default:
				throw new IllegalArgumentException();
		}
	}
	
//	public static DisplacementDescriptionType byTagsPath(String tagsIdentifier) throws IllegalArgumentException {
//		for(DisplacementDescriptionType desc : DisplacementDescriptionType.values()){
//			if(desc.identifier.equals(tagsIdentifier)) return desc;
//		}
//		throw new IllegalArgumentException();
//	}
}
