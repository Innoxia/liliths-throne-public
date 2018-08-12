package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.RentalMommyDialogue;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.5
 * @version 0.2.6
 * @author Innoxia
 */
public class RentalMommy extends NPC {
	
	public RentalMommy() {
		this(false);
	}
	
	public RentalMommy(boolean isImported) {
		super(new NameTriplet("Mommy"),
				"'Mommy' earns a living by renting herself out to those in need of some motherly love.",
				45, Month.JULY, 3,
				10, Gender.F_V_B_FEMALE, RacialBody.COW_MORPH, RaceStage.PARTIAL,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BOULEVARD, false);

		if(!isImported) {
			
			this.setLocation(WorldType.DOMINION, Main.game.getPlayer().getLocation(), true);
			
			// RACE & NAME:
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			Subspecies species = Subspecies.COW_MORPH;
				
			switch(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species)) {
				case HUMAN:
					setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN);
					break;
				case MINIMUM:
					setBodyFromPreferences(1, Gender.F_V_B_FEMALE, species);
					break;
				case REDUCED:
					setBodyFromPreferences(2, Gender.F_V_B_FEMALE, species);
					break;
				case NORMAL:
					setBodyFromPreferences(3, Gender.F_V_B_FEMALE, species);
					break;
				case MAXIMUM:
					setBody(Gender.F_V_B_FEMALE, species, RaceStage.GREATER);
					break;
			}

			this.setPlayerKnowsName(true);
			
			// PERSONALITY & BACKGROUND:
			
			this.setHistory(Occupation.NPC_PROSTITUTE);

			this.setPersonalityTrait(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE);
			this.setPersonalityTrait(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH);
			this.setPersonalityTrait(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE);
			this.setPersonalityTrait(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH);
			this.setPersonalityTrait(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE);
			
			// ADDING FETISHES:

			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_PREGNANCY);
			
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_LACTATION_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_PURE_VIRGIN, FetishDesire.ONE_DISLIKE);
			
			this.addPerk(Perk.FETISH_BROODMOTHER);
			this.addTrait(Perk.FETISH_BROODMOTHER);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			this.setHairStyle(HairStyle.STRAIGHT);
			
			this.setBreastSize(CupSize.GG.getMeasurement());
			this.setBreastShape(BreastShape.ROUND);
			this.setAreolaeSize(AreolaeSize.THREE_LARGE.getValue());
			this.setNippleSize(NippleSize.THREE_LARGE.getValue());
			this.addNippleOrificeModifier(OrificeModifier.PUFFY);
			this.setHipSize(HipSize.FIVE_VERY_WIDE.getValue());
			this.setAssSize(AssSize.FOUR_LARGE.getValue());
			
			this.setHeight(176);
			
			this.setBreastLactationRegeneration(FluidRegeneration.THREE_PLUMP.getValue());
			this.setBreastMilkStorage(500);
			this.setBreastStoredMilk(500);

			this.setSkinCovering(new Covering(BodyCoveringType.BOVINE_FUR, CoveringPattern.SPOTTED, Colour.COVERING_WHITE, false, Colour.COVERING_BLACK, false), true);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BLACK), true);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_BOVINE_FUR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getLilaya().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);
			
			this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
			this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
			
			this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED));
			this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED));
			
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_NURSING_BRA, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TIGHT_TROUSERS, Colour.CLOTHING_BLUE, false), true, this);
			try {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_rentalMommy_rental_mommy"), Colour.CLOTHING_WHITE, Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK, false), true, this);
			} catch(Exception ex) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_TSHIRT, Colour.CLOTHING_WHITE, false), true, this);
			}
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_ANKLE_BOOTS, Colour.CLOTHING_TAN, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
			
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	// Sex:
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_GENTLE;
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return RentalMommyDialogue.ENCOUNTER;
	}

	
}
