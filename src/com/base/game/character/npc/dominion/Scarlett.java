package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.AbstractClothingType;
import com.base.game.inventory.clothing.ClothingType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.world.WorldType;
import com.base.world.places.SlaverAlley;

/**
 * @since 0.1.75
 * @version 0.1.78
 * @author Innoxia
 */
public class Scarlett extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing
			underwear = AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_RED, false),
			bra = AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_RED, false),
			
			torso = AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_WHITE, false),
			skirt = AbstractClothingType.generateClothing(ClothingType.LEG_SKIRT, Colour.CLOTHING_WHITE, false),
			
			neck = AbstractClothingType.generateClothing(ClothingType.NECK_HEART_NECKLACE, Colour.CLOTHING_SILVER, false),
			ear = AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false);

	public Scarlett() {
		super(new NameTriplet("Scarlett"),
				"Scarlett is the owner of the rather unoriginally named establishment 'Scarlett's shop'."
						+ " Rude, loud, and quick to anger, Scarlett isn't a very pleasant person to have to deal with.",
				5, Gender.MALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.SLAVER_ALLEY, SlaverAlley.SCARLETTS_SHOP, true);

		this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_BROWN));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.FEATHERS_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, Colour.FEATHERS_PINK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);

		this.setAssVirgin(true);
		this.setFaceVirgin(true);
		this.setBreastSize(CupSize.AA.getMeasurement());
		
		this.setHeight(155);
		
		this.setPiercedEar(true);

		this.equipClothingFromNowhere(underwear, true, this);
		this.equipClothingFromNowhere(bra, true, this);
		this.equipClothingFromNowhere(torso, true, this);
		this.equipClothingFromNowhere(skirt, true, this);
		this.equipClothingFromNowhere(neck, true, this);
		this.equipClothingFromNowhere(ear, true, this);
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().lightTheme) {
			return "#FA0060";
		} else {
			return "#FF94BD";
		}
	}

	@Override
	public void applyReset() {
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex(boolean applyEffects) {
		if (applyEffects)
			applyReset();
	}

	// Combat (you never fight Rose):
	@Override
	public String getCombatDescription() {
		return null;
	}
	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		return null;
	}
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	@Override
	public Attack attackType() {
		return null;
	}
	@Override
	public int getExperienceFromVictory() {
		return 0;
	}

}