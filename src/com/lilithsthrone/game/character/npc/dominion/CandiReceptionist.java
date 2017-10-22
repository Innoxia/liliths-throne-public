package com.lilithsthrone.game.character.npc.dominion;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.EnforcerHQ;

/**
 * @since 0.1.78
 * @version 0.1.82
 * @author Innoxia
 */
public class CandiReceptionist extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing
			underwear = AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_PINK_LIGHT, false),
			
			torso = AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLUE, false),
			shorts = AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLUE, false),
			
			boots = AbstractClothingType.generateClothing(ClothingType.FOOT_STILETTO_HEELS, Colour.CLOTHING_BLACK, false),
			
			neck = AbstractClothingType.generateClothing(ClothingType.NECK_HEART_NECKLACE, Colour.CLOTHING_SILVER, false),
			
			ear = AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false),
			nose = AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false),
			navel = AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false),
			vagina = AbstractClothingType.generateClothing(ClothingType.PIERCING_VAGINA_BARBELL_RING, Colour.CLOTHING_GOLD, false);

	public CandiReceptionist() {
		super(new NameTriplet("Candi"),
				"Candi is the receptionist at the Enforcer HQ."
				+ " A completely brain-dead bimbo, Candi seems to only ever be interested in three things; applying makeup, flirting, and fucking.",
				1, Gender.F_V_B_FEMALE, RacialBody.CAT_MORPH, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.ENFORCER_HQ, EnforcerHQ.RECEPTION_DESK, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_BLUE));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLEACH_BLONDE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLEACH_BLONDE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);

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
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex(boolean applyEffects) {
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