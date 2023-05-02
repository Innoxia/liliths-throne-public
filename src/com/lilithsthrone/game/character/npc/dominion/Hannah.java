package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.ScarType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.7.9
 * @version 0.4.7.9
 * @author Innoxia
 */
public class Hannah extends NPC {

	private static String PERK_PROGRESS_ID = "boxing_perk_progress";
	
	public Hannah() {
		this(false);
	}
	
	public Hannah(boolean isImported) {
		super(isImported, new NameTriplet("Hannah"), "Wilkinson",
				"Hannah works at the gym, 'Pix's Playground' as a boxing instructor.",
				31, Month.AUGUST, 22,
				40,
				Gender.F_V_B_FEMALE, Subspecies.getSubspeciesFromId("innoxia_hyena_subspecies_spotted"), RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_dominion_shopping_arcade_gym"), PlaceType.getPlaceTypeFromId("innoxia_dominion_shopping_arcade_gym_boxing"),
				true);
	}
	
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.11")) {
			this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.UNARMED_TRAINING,
						Perk.OBSERVANT),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.BRAVE,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.PRUDE);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_PUGILIST);
			
			this.clearFetishDesires();
			this.clearFetishes();

			this.setFetishDesire(Fetish.FETISH_BONDAGE_APPLIER, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
		}
		
		// Body:
		
		// Core:
		this.setHeight(192);
		this.setFemininity(65);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_hyena_eye"), CoveringPattern.EYE_IRISES, PresetColour.EYE_BLUE, false, PresetColour.EYE_BLUE, false));
		this.setSkinCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_hyena_fur"), CoveringPattern.SPOTTED, CoveringModifier.SMOOTH, PresetColour.COVERING_BROWN, false, PresetColour.COVERING_BLACK, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);

		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_hyena_hair"), CoveringPattern.NONE, CoveringModifier.FLUFFY, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_BLACK, false), false);
		this.setHairLength(HairLength.TWO_SHORT.getMedianValue());
		this.setHairStyle(HairStyle.SLICKED_BACK);
		
		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_hyena_body_hair"), PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_ORANGE));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_ORANGE));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_CLEAR));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLUE_LIGHT));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.WIDE);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setAssPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		// Anus modifiers
		
		// Penis:
		
		// Vagina:
		this.setVaginaVirgin(false);
//		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE); // Leave as default spotted hyena big clit
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.TWO_MOIST);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(1500);

		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			this.setScar(InventorySlot.LEG, new Scar(ScarType.STRAIGHT_SCAR, true));
			this.setScar(InventorySlot.WRIST, new Scar(ScarType.CLAW_MARKS, true));
			this.setScar(InventorySlot.CHEST, new Scar(ScarType.JAGGED_SCAR, true));
			this.setScar(InventorySlot.MOUTH, new Scar(ScarType.STRAIGHT_SCAR, true));
		}
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_sweatband", PresetColour.CLOTHING_RED_DARK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_sports_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boyshorts", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_sport_shorts", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.setPiercedEar(true);
		this.setPiercedNose(true);
	}
	
	public void equipBarClothing() {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boyshorts", PresetColour.CLOTHING_GREY, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_ljacket_ljacket_f", PresetColour.CLOTHING_BLACK, false), true, this);
		this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_OVER), DisplacementType.UNZIPS, true, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("TonyJC_crop_tank_top", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_distressed_jeans", PresetColour.CLOTHING_BLUE_NAVY, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BRASS, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("nerdlinger_street_hitop_canvas_sneakers_hi_top_canvas_sneakers", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		
		// Jewellery:
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ball_stud", PresetColour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#c484da";
	}
	
	@Override
	public void turnUpdate() {
		if(Main.game.getPlayer().getWorldLocation()!=WorldType.getWorldTypeFromId("innoxia_dominion_shopping_arcade_gym")
				&& Main.game.getDialogueFlags().hasFlag("innoxia_pix_had_tour")
				&& !Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(8, 20)) {
				if(this.getWorldLocation()!=WorldType.getWorldTypeFromId("innoxia_dominion_shopping_arcade_gym")) {
					this.equipClothing(); // Make sure to equip workout clothing when going to gym
				}
				this.returnToHome();
			} else if(Main.game.isHourBetween(21, 24)
					&& !Main.game.getDialogueFlags().hasFlag("innoxia_lights_out_hannah_left")
					&& !Main.game.getDialogueFlags().hasFlag("innoxia_lights_out_hannah_sex")) {
				if(this.getWorldLocation()!=WorldType.getWorldTypeFromId("innoxia_dominion_nightlife_lights_out")) {
					this.equipBarClothing(); // Make sure to equip bar clothing when going to Lights Out
				}
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_nightlife_lights_out"), PlaceType.getPlaceTypeFromId("innoxia_dominion_nightlife_lights_out_bar"));
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			}
		}
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
		this.replaceAllClothing();
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	// Methods for her boxing lessons in the gym:
	
	private int getBoxingLessonIncrement() {
		return 20;
	}
	
	public void incrementPerkProgress() {
		int increment = getBoxingLessonIncrement();
		
		if(!Main.game.getDialogueFlags().hasSavedLong(PERK_PROGRESS_ID) || Main.game.getDialogueFlags().getSavedLong(PERK_PROGRESS_ID)<0) {
			Main.game.getDialogueFlags().setSavedLong(PERK_PROGRESS_ID, 0);
		}
		Main.game.getDialogueFlags().incrementSavedLong(PERK_PROGRESS_ID, increment);
	}
	
	public String getPerkProgressString() {
		int increment = getBoxingLessonIncrement();
		
		if(!Main.game.getPlayer().hasPerkAnywhereInTree(Perk.MARTIAL_ARTIST) && Main.game.getDialogueFlags().getSavedLong(PERK_PROGRESS_ID)<100) {
			UtilText.addSpecialParsingString(String.valueOf(increment), true);
			UtilText.addSpecialParsingString(String.valueOf(Main.game.getDialogueFlags().getSavedLong(PERK_PROGRESS_ID)), false);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/pixsPlayground", "BOXING_PERK_PROGRESS");
		}
		return "";
	}
}
