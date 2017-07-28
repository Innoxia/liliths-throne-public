package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
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
import com.base.game.inventory.clothing.ClothingType;
import com.base.utils.Colour;
import com.base.world.WorldType;
import com.base.world.places.HarpyNests;

/**
 * @since 0.1.75
 * @version 0.1.8
 * @author Innoxia
 */
public class Alexa extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing
			underwear = ClothingType.generateClothing(ClothingType.GROIN_BIKINI, Colour.CLOTHING_BLACK, false),
			bra = ClothingType.generateClothing(ClothingType.CHEST_BIKINI, Colour.CLOTHING_BLACK, false),
			
			eye = ClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_GOLD, false),
			
			ear = ClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false),
			navel = ClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false),
			nose = ClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false);

	public Alexa() {
		super(new NameTriplet("Alexa"),
				"Alexa is an extremely powerful harpy matriarch, and is in control of one of the largest harpy flocks in Dominion."
						+ " Her beauty rivals that of even the most gorgeous of succubi, which, combined with her sharp mind and flirtatious personality, makes her somewhat of an idol in harpy society.",
				8, Gender.FEMALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HARPY_NEST, HarpyNests.ALEXAS_NEST, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeColour(Colour.EYE_YELLOW);
		this.setHairColour(Colour.FEATHERS_WHITE);
		this.setSkinColour(BodyCoveringType.HUMAN, Colour.HUMAN_SKIN_LIGHT);
		this.setSkinColour(BodyCoveringType.FEATHERS, Colour.FEATHERS_WHITE);

		this.setFemininity(100);
		
		this.setVaginaVirgin(false);
		this.setAssVirgin(false);
		this.setFaceVirgin(false);
		this.setBreastSize(CupSize.D.getMeasurement());
		
		this.setHeight(162);
		
		this.setPiercedEar(true);
		this.setPiercedNavel(true);
		this.setPiercedNose(true);

		this.equipClothingFromNowhere(underwear, true, this);
		this.equipClothingFromNowhere(bra, true, this);

		this.equipClothingFromNowhere(eye, true, this);
		
		this.equipClothingFromNowhere(ear, true, this);
		this.equipClothingFromNowhere(navel, true, this);
		this.equipClothingFromNowhere(nose, true, this);
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