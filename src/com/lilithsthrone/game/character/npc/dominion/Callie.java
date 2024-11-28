package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.3
 * @version 0.4.3
 * @author Nnxx, Innoxia
 */
public class Callie extends NPC {

	public Callie() {
		this(false);
	}
	
	public Callie(boolean isImported) {
		super(isImported, new NameTriplet("Callie"), "Dulce",
				"Callie is the owner of the bakery, 'The Creamy Bakey', which is to be found in the north-west of Dominion.",
				26, Month.JUNE, 11,
				10, Gender.F_P_B_SHEMALE, Subspecies.HORSE_MORPH, RaceStage.PARTIAL_FULL,
				new CharacterInventory(250),
				WorldType.getWorldTypeFromId("nnxx_callie_bakery"), PlaceType.getPlaceTypeFromId("nnxx_callie_bakery_counter"),
				true);
		if(!isImported) {
			this.dailyUpdate();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.4.3.2")) {
			 // Fix for incorrect covering colours:
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_AQUA));
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_JET_BLACK), true);
			
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_JET_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_JET_BLACK), true);
			
			this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_EBONY), false);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);
	
			this.setSkinCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_JET_BLACK), false);
			this.setSkinCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_JET_BLACK), false);
			
			// Add firing blanks to avoid impregnating player:
			setupPerks(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.FIRING_BLANKS),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_BUSINESS_OWNER);
	
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_CUM_STUD);
			this.addFetish(Fetish.FETISH_DOMINANT);
			
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
		}
		
		
		// Body:
		this.setHairType(HairType.HUMAN);
		this.setEyeType(EyeType.HUMAN);
		this.setLegType(LegType.HORSE_MORPH);

		// Core:
		this.setHeight(182);
		this.setFemininity(80);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_AQUA));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_JET_BLACK), true);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_JET_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_JET_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_EBONY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);

		this.setSkinCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_JET_BLACK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_JET_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(true);
//		this.setLipSize(LipSize.TWO_FULL);
//		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
//		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE.getValue());
		this.setHipSize(HipSize.FOUR_WOMANLY.getValue());
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(28);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(250);
		this.fillCumToMaxStorage();
		
		// Vagina:
//		this.setVaginaVirgin(false);
//		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
//		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
//		this.setVaginaSquirter(true);
//		this.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
//		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
//		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
//		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_feminine_short_sleeve_shirt", PresetColour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_tight_jeans", PresetColour.CLOTHING_WHITE, false), true, this);
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_WHITE, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_wrist_bangle", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public void dailyUpdate() {
		// Set show upgrade flags:
		if(Main.game.getDialogueFlags().getSavedLong("nnxx_callie_total_donated") >= 100_000 && !Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_3")) {
			Main.game.getDialogueFlags().setFlag("nnxx_callie_upgrade_3", true);
			Main.game.getDialogueFlags().setFlag("nnxx_callie_upgrade_reaction_pending", true);
			Main.game.getDialogueFlags().setFlag("nnxx_callie_sex_is_free", true);
			
		} else if(Main.game.getDialogueFlags().getSavedLong("nnxx_callie_total_donated") >= 50_000 && !Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_2")) {
			Main.game.getDialogueFlags().setFlag("nnxx_callie_upgrade_2", true);
			Main.game.getDialogueFlags().setFlag("nnxx_callie_upgrade_reaction_pending", true);
			this.setSellModifier(1.2f); //20% discount
			
		} else if(Main.game.getDialogueFlags().getSavedLong("nnxx_callie_total_donated") >= 3_000 && !Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_1")) {
			Main.game.getDialogueFlags().setFlag("nnxx_callie_upgrade_1", true);
			Main.game.getDialogueFlags().setFlag("nnxx_callie_upgrade_reaction_pending", true);
		}
		
		// Reset doughnut inventory:
		this.clearNonEquippedInventory(false);
		this.addItem(Main.game.getItemGen().generateItem("innoxia_food_doughnut"), 12, false, false);
		if(Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_1")) {
			this.addItem(Main.game.getItemGen().generateItem("innoxia_food_doughnut"), 12, false, false);
			this.addItem(Main.game.getItemGen().generateItem("innoxia_food_doughnut_iced"), 12, false, false);
		}
		if(Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_2")) {
			this.addItem(Main.game.getItemGen().generateItem("innoxia_food_doughnut"), 12, false, false);
			this.addItem(Main.game.getItemGen().generateItem("innoxia_food_doughnut_iced"), 12, false, false);
			this.addItem(Main.game.getItemGen().generateItem("innoxia_food_doughnut_iced_sprinkles"), 12, false, false);
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex() {
		this.applyWash(true, true, null, 0);
	}
	
	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_NORMAL;
	}
	
	// Trade methods:

	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("nnxx/callie_bakery", "TRANSACTION_START");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}

	@Override
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, true);
		UtilText.addSpecialParsingString(itemSold.getName(), true);
		
		Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("nnxx/callie_bakery", "TRANSACTION_COMPLETE"));
	}
	

}