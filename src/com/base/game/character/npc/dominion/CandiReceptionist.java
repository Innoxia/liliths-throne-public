package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.HipSize;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.character.effects.Fetish;
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
import com.base.world.places.EnforcerHQ;

/**
 * @since 0.1.78
 * @version 0.1.82
 * @author Innoxia
 */
public class CandiReceptionist extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing
			underwear = ClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_PINK_LIGHT, false),
			
			torso = ClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_WHITE, false),
			shorts = ClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_WHITE, false),
			
			boots = ClothingType.generateClothing(ClothingType.FOOT_THIGH_HIGH_BOOTS, Colour.CLOTHING_BROWN, false),
			
			neck = ClothingType.generateClothing(ClothingType.NECK_HEART_NECKLACE, Colour.CLOTHING_SILVER, false),
			
			ear = ClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false),
			nose = ClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false),
			navel = ClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false),
			vagina = ClothingType.generateClothing(ClothingType.PIERCING_VAGINA_BARBELL_RING, Colour.CLOTHING_GOLD, false);

	public CandiReceptionist() {
		super(new NameTriplet("Candi"),
				"Candi is the receptionist at the Enforcer HQ."
				+ " A completely brain-dead bimbo, Candi seems to only ever be interested in three things; applying makeup, flirting, and fucking.",
				1, Gender.FEMALE, RacialBody.CAT_MORPH, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.ENFORCER_HQ, EnforcerHQ.RECEPTION_DESK, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_BLUE));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLEACH_BLONDE));
		this.setCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLEACH_BLONDE));
		this.setCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE));

		this.addFetish(Fetish.FETISH_BIMBO);
		this.addFetish(Fetish.FETISH_ORAL_GIVING);
		this.addFetish(Fetish.FETISH_CUM_ADDICT);
		
		this.setFemininity(85);
		
		this.setAssVirgin(false);
		this.setAssSize(AssSize.FIVE_HUGE.getValue());
		this.setHipSize(HipSize.SIX_EXTREMELY_WIDE.getValue());
		this.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue());
		
		this.setFaceVirgin(false);
		this.setFaceCapacity(Capacity.SEVEN_GAPING.getMedianValue());
		
		this.setVaginaVirgin(false);
		this.setVaginaCapacity(Capacity.SIX_STRETCHED_OPEN.getMedianValue());
		this.setVaginaWetness(Wetness.FOUR_SLIMY.getValue());
		
		this.setBreastSize(CupSize.G.getMeasurement());
		
		this.setHeight(168);
		

		this.equipClothingFromNowhere(underwear, true, this);
		this.equipClothingFromNowhere(torso, true, this);
		this.equipClothingFromNowhere(shorts, true, this);
		this.equipClothingFromNowhere(boots, true, this);
		this.equipClothingFromNowhere(neck, true, this);

		this.setPiercedEar(true);
		this.setPiercedNose(true);
		this.setPiercedNavel(true);
		this.setPiercedVagina(true);
		this.equipClothingFromNowhere(ear, true, this);
		this.equipClothingFromNowhere(nose, true, this);
		this.equipClothingFromNowhere(navel, true, this);
		this.equipClothingFromNowhere(vagina, true, this);
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