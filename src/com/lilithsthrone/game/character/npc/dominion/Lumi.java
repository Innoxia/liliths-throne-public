package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.unique.LumiDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Nnxx, Innoxia
 */
public class Lumi extends NPC {
	
	public Lumi() {
		this(false);
	}
	
	public Lumi(boolean isImported) {
		super(new NameTriplet(
				"Lumi"),
				"Lumi is a semi-feral wolf-girl, who you first met prowling one of Dominion's alleyways.",
				19, Month.JULY, 15,
				5,
				Gender.F_V_B_FEMALE,
				RacialBody.HUMAN,
				RaceStage.HUMAN,
				new CharacterInventory(10),
				WorldType.EMPTY,
				PlaceType.GENERIC_EMPTY_TILE,
				true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE)));
		
		this.useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), this, false);
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);

			this.setPlayerKnowsName(false);
			
			this.setEarType(EarType.LYCAN);
			this.setTailType(TailType.LYCAN);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, Colour.COVERING_WHITE), true);
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
			
			this.setHeight(157); // About 5'2"
			
			this.setFemininity(85); // Very feminine
			
			this.setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
			this.setHairStyle(HairStyle.NONE);
			
			this.setVaginaVirgin(true);
			this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
			
			this.setBreastSize(CupSize.B.getMeasurement());
			
			this.setAssSize(AssSize.TWO_SMALL.getValue());
			this.setHipSize(HipSize.THREE_GIRLY.getValue());
			
			// Give body shape "thin":
			this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
			this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASTURBATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_DENIAL_SELF, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);

			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING, FetishDesire.ZERO_HATE);
			
			this.setMoney(0);
			
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 35);
			this.setAttribute(Attribute.MAJOR_ARCANE, 6);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SPORT_SHORTS, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HAND_WRAPS, Colour.CLOTHING_RED, false), true, this);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.5")) {
			this.unequipAllClothingIntoVoid();
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SPORT_SHORTS, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HAND_WRAPS, Colour.CLOTHING_RED, false), true, this);
		}
	}

	@Override
	public void hourlyUpdate() {
		this.useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), this, false);
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
	public void endSex() {
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_RESISTING;
	}
	
	// Combat:
	
	@Override
	public boolean isImmuneToDamageType(DamageType type) {
		return type==DamageType.LUST;
	}
	
	@Override
	public int getLootMoney() {
		return 0;
	}
	
	@Override
	public List<AbstractCoreItem> getLootItems() {
		return new ArrayList<>();
	}
	
	@Override
	public Attack attackType() {
		return Attack.MAIN;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean playerVictory) {
		if(playerVictory) {
			return new Response("Victory", "The wolf-girl is utterly beaten!", LumiDialogue.COMBAT_PLAYER_WIN);
		} else {
			return new Response("Defeated", "The wolf-girl has bested you!", LumiDialogue.COMBAT_PLAYER_LOSS);
		}
	};
}