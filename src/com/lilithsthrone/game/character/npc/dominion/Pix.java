package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.6x
 * @version 0.1.89
 * @author Innoxia
 */
public class Pix extends NPC {

	private static final long serialVersionUID = 1L;

	public Pix() {
		this(false);
	}
	
	private Pix(boolean isImported) {
		super(new NameTriplet("Pix"), "An extremely energetic dog-girl. While you're at the gym, Pix is never far away.",
				10, Gender.F_V_B_FEMALE, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_PIXS_GYM, true);

		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DOG_MORPH, Colour.EYE_BROWN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_CANINE_FUR, Colour.COVERING_BROWN_DARK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.CANINE_FUR, Colour.COVERING_BROWN), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
	
			this.setVaginaVirgin(false);
			this.setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue(), true);
			this.setBreastSize(CupSize.B.getMeasurement());
			this.setBreastRows(3);
	
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOYSHORTS, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			
			this.addFetish(Fetish.FETISH_DENIAL);
			this.addFetish(Fetish.FETISH_DOMINANT);
		}
	}
	
	@Override
	public Pix loadFromXML(Element parentElement, Document doc) {
		Pix npc = new Pix(true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);
		
		return npc;
	}

	@Override
	public boolean isUnique() {
		return true;
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

	// Combat (you never fight Pix):
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
