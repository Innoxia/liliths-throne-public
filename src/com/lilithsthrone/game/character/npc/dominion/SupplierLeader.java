package com.lilithsthrone.game.character.npc.dominion;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SupplierDepot;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public class SupplierLeader extends NPC {

	private static final long serialVersionUID = 1L;

	public SupplierLeader() {
		this(false);
	}
	
	public SupplierLeader(boolean isImported) {
		super(new NameTriplet("Wolfgang", "Wolfgang", "Winifred"),
				".",
				8,
				Gender.M_P_MALE,
				RacialBody.DOG_MORPH,
				RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.SUPPLIER_DEN,
				PlaceType.SUPPLIER_DEPOT_OFFICE,
				true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setBody(Gender.M_P_MALE, Subspecies.DOG_MORPH_DOBERMANN, RaceStage.GREATER);
			this.setFemininity(5);
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DOG_MORPH, Colour.EYE_BROWN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_CANINE_FUR, Colour.COVERING_BLACK), true);
			this.setHairLength(0);
	
			this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
			this.setPenisVirgin(false);
	
			this.setHeight(190);
			
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.THREE_LARGE.getMedianValue());

			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_JEANS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_BLACK, false), true, this);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_CUM_STUD);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.ONE_DISLIKE);
			
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 35);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 70);
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
	
	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
		}
	}
	
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	// Combat:
	@Override
	public String getCombatDescription() { //TODO
		return ".";
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", SupplierDepot.SUPPLIER_DEPOT_OFFICE_COMBAT_PLAYER_VICTORY) {
				@Override
				public void effects() {
					if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_NYAN_HELP, Quest.SIDE_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN)) {
						SupplierDepot.applySuppliersBeatenEffects();
					}
				}
			};
		} else {
			return new Response("", "", SupplierDepot.SUPPLIER_DEPOT_OFFICE_COMBAT_PLAYER_LOSS);
		}
	}
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	public List<AbstractCoreItem> getLootItems() {
		return null;
	}
	
}
