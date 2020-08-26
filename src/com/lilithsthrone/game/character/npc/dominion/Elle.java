package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
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
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.9.4
 * @version 0.3.9.4
 * @author DSG, Innoxia
 */
public class Elle extends NPC {

	public Elle() {
		this(false);
	}
	
	public Elle(boolean isImported) {
		super(isImported, new NameTriplet("Aellasys"), "Lunettemartu",
				"",
				44, Month.FEBRUARY, 5,
				15,
				Gender.F_V_B_FEMALE, Subspecies.HORSE_MORPH_UNICORN, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_OFFICE_QUARTERMASTER,
				true);
		
		if(!isImported) {
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 3),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT, 
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ENFORCER_SWORD_SUPER);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);

			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(41);
		this.setWingType(WingType.DEMON_COMMON);
		this.setHornType(HornType.HORSE_STRAIGHT);
		this.setHornLength(22);
		this.setFootStructure(FootStructure.UNGULIGRADE);

		this.setAssType(AssType.DEMON_COMMON);
		this.setBreastType(BreastType.DEMON_COMMON);
		this.setVaginaType(VaginaType.DEMON_COMMON);
//		this.setTailType(TailType.DEMON_COMMON); //TODO?
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.THREE_LARGE.getValue()); //TODO?
		this.setEyeType(EyeType.DEMON_COMMON);
		this.setSubspeciesOverride(Subspecies.HALF_DEMON);
		
		// Core:
		this.setHeight(188);
		this.setFemininity(90);
		this.setMuscle(70);
		this.setBodySize(50);
		
		// Coverings: //TODO?
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_PINK));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_LILAC), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_INDIGO), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_SANDY), true);
		this.setHairLength(22);
		this.setHairStyle(HairStyle.BUN);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.SIX_BUSHY);
		this.setAssHair(BodyHair.FIVE_UNKEMPT);
		this.setPubicHair(BodyHair.SEVEN_WILD);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		
		this.setNippleCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true); //TODO?
		
		// Crotch-boobs:
		this.setBreastCrotchType(BreastType.NONE);
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		this.setAssCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setAssWetness(Wetness.THREE_WET);
		this.setAssDepth(OrificeDepth.FOUR_DEEP.getValue());
		this.setAssPlasticity(0);
		this.setAssElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		
		// Penis:
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaDepth(OrificeDepth.FOUR_DEEP.getValue());
		this.setVaginaPlasticity(0);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		if(isSlave()) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BRASS, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.ENFORCER_SHIRT, PresetColour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.ENFORCER_MINI_SKIRT, PresetColour.CLOTHING_PINK, false), true, this);
			
		} else {
			this.setEssenceCount(100);
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_eep_pbweap_pbpistol"));
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, null, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_uniques_enfdjacket_elle", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfberet_sword", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BLACK, null, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, PresetColour.CLOTHING_DESATURATED_BROWN, PresetColour.CLOTHING_GOLD, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return "Elle is no longer the Senior Quartermaster for all of the Enforcers in Central Dominion thanks to you. Although her fall from her former office was meteoric, she doesn't seem to mind at all.";
			
		} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
			return "With your help, Elle remains the Senior Quartermaster for all of the Enforcers in Central Dominion. Although she could fill Wes' former position with any of her subordinates, she has chosen to man the desk herself.";
			
		} else {
			return "Elle is the Senior Quartermaster for all of the Enforcers in Central Dominion and is Wes' commanding officer. Although her temper makes her difficult to work for, her subordinates, except for Wes, have little complaint.";
		}
	}
	
	@Override
	public String getSpeechColour() {//TODO
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#754a86";
		}
		return "#d69423";
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

}