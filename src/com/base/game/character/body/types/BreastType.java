package com.base.game.character.body.types;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.race.Race;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Util;

/**
 * BreastType is only really a change of nipple type. Breasts always look
 * human-like. Don't use SkinType for Breasts. Instead, use the character's main
 * body skin.
 * 
 * @since 0.1.0
 * @version 0.1.8
 * @author Innoxia
 */
public enum BreastType implements BodyPartTypeInterface {
	HUMAN("milk", BodyCoveringType.HUMAN, Race.HUMAN),

	ANGEL("milk", BodyCoveringType.ANGEL, Race.ANGEL),

	DEMON_COMMON("milk", BodyCoveringType.DEMON_COMMON, Race.DEMON),

	DOG_MORPH("milk", BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),
	
	WOLF_MORPH("milk", BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),
	
	COW_MORPH("milk", BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),
	
	CAT_MORPH("milk", BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),
	
	SQUIRREL_MORPH("milk", BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),
	
	HORSE_MORPH("milk", BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH),
	
	HARPY("milk", BodyCoveringType.FEATHERS, Race.HARPY),

	SLIME("slime", BodyCoveringType.SLIME, Race.SLIME);

	
	private String milkName;
	private BodyCoveringType skinType;
	private Race race;

	private BreastType(String milkName, BodyCoveringType skinType, Race race) {
		this.skinType = skinType;
		this.milkName = milkName;
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getBreastRows()==1) {
			return "a pair of";
		} else if(gc.getBreastRows()==2) {
			return "two pairs of";
		} else {
			return "three pairs of";
		}
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(gc.getBreastSize()==CupSize.FLAT)
			return "pec";
		switch(this){
			default:
				return UtilText.returnStringAtRandom("breast", "boob", "tit");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(gc.getBreastSize()==CupSize.FLAT)
			return "chest";
		switch(this){
			default:
				return UtilText.returnStringAtRandom("breasts", "boobs", "mammaries", "tits");
		}
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		if(gc.getBreastSize()==CupSize.FLAT)
			return "";
		switch(this){
			default:
				return gc.getBreastSize().getDescriptor();
		}
	}
	
	@Override
	public BodyCoveringType getSkinType() {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}
	
	public String getMilkName() {
		return milkName;
	}
	
	// Extra methods for nipples:
	
	public String getNippleName() {
		String name[];
		
		// Names:
		switch(this){
			case DEMON_COMMON:
				name = new String[] {  "nipple-cunts", "nipples", "teats" };
				break;
			default:
				name = new String[] {  "nipples", "teats" };
				break;
		}
		
		return name[Util.random.nextInt(name.length)];
	}
	
	public String getNippleNameSingular() {
		String name[];
		
		// Names:
		switch(this){
			case DEMON_COMMON:
				name = new String[] {  "nipple-cunt", "nipple", "teat" };
				break;
			default:
				name = new String[] {  "nipple", "teat" };
				break;
		}
		
		return name[Util.random.nextInt(name.length)];
	}

	public String getNippleDescriptor() {
		String descriptor[];
		
		switch(this){
			case ANGEL:
				descriptor = new String[] { "flawless", "perky" };
				break;
			case DEMON_COMMON:
				descriptor = new String[] { "demonic", "irresistible" };
				break;
			case HUMAN:
				descriptor = new String[] { "" };
				break;
			case SLIME:
				descriptor = new String[] { "gooey" };
				break;
			default:
				descriptor = new String[] { "" };
				break;
		}
		
		return descriptor[Util.random.nextInt(descriptor.length)];
	}
}
