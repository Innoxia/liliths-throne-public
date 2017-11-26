package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
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
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public class Zaranix extends NPC {

	private static final long serialVersionUID = 1L;

	public Zaranix() {
		this(false);
	}
	
	private Zaranix(boolean isImported) {
		super(new NameTriplet("Zaranix", "Zaranix", "Zoelix"),
				"Zaranix is one of the few demons that feels more comfortable in his incubus, rather than succubus, form."
						+ " Muscular, tall, and handsome, Zaranix uses both his cunning mind and good looks to get what he wants.",
				15, Gender.M_P_MALE, RacialBody.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_OFFICE, true);

		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_ORANGE));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_PURPLE), true);
			
			this.setHeight(Height.THREE_TALL.getMedianValue());
			
			this.setHornType(HornType.STRAIGHT);
			
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
			this.setAssVirgin(true);
			this.setFaceVirgin(true);
			this.setNippleVirgin(true);
			this.setPenisVirgin(false);
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OXFORD_SHIRT, Colour.CLOTHING_GREY, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_MENS_SMART_SHOES, Colour.CLOTHING_BLACK, false), true, this);
			
		}
	}
	
	@Override
	public Zaranix loadFromXML(Element parentElement, Document doc) {
		Zaranix npc = new Zaranix(true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);
		
		return npc;
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#42C6FF";
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
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	

	// Combat: TODO
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
	
	// Sex:
	
	public SexType getForeplayPreference() {
		if(Sex.getSexManager().getPosition() == SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
			return new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER);
		}
		
		return super.getForeplayPreference();
	}
	
	public SexType getMainSexPreference() {
		if(Sex.getSexManager().getPosition() == SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
			return new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER);
		}

		return super.getMainSexPreference();
	}

}