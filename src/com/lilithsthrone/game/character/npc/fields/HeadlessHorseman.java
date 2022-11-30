package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
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
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Innoxia
 */
public class HeadlessHorseman extends NPC {

	public HeadlessHorseman() {
		this(false);
	}
	
	public HeadlessHorseman(boolean isImported) {
		super(isImported, new NameTriplet("Tim"), "Irving",
				"During the month of October, this menace prowls the Foloi Fields at night, searching for his next victim to give him head...",
				36, Month.OCTOBER, 31,
				25, Gender.M_P_MALE, Subspecies.HORSE_MORPH, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				true);
		
		this.setGenericName("headless horseman");
		
		if(!isImported) {
			this.setPlayerKnowsName(false);
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.2")) {
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
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
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MUGGER);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_PENIS_GIVING);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.ONE_DISLIKE);
		}
		
		
		// Body:
		
		// Core:
		this.setHeight(206);
		this.setFemininity(0);
		this.setMuscle(90);
		this.setBodySize(75);
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, CoveringPattern.EYE_IRISES, PresetColour.EYE_RED, true, PresetColour.EYE_RED, true));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_JET_BLACK), true);

		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_EBONY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_JET_BLACK), true);
		this.setHairLength(HairLength.TWO_SHORT.getMedianValue());
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_JET_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE));
//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PURPLE));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PURPLE));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
//		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
//		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
//		this.setBreastSize(CupSize.E.getMeasurement());
//		this.setBreastShape(BreastShape.ROUND);
//		this.setNippleSize(NippleSize.THREE_LARGE);
//		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.TWO_NARROW);
//		this.setAssCapacity(Capacity.FIVE_ROOMY, true);
//		this.setAssWetness(Wetness.FIVE_SLOPPY);
		// Horse-like modifiers:
//		this.addAssOrificeModifier(OrificeModifier.PUFFY);
//		this.addAssOrificeModifier(OrificeModifier.RIBBED);
//		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		this.setPenisSize(38);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(2000);
		this.setPenisCumExpulsion(85);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		this.addCumModifier(FluidModifier.ADDICTIVE);
		
		// Vagina:
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_europeanSwords_zweihander", DamageType.PHYSICAL));
		AbstractClothing cloak = Main.game.getItemGen().generateClothing("innoxia_torsoOver_hooded_cloak", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK_STEEL, null, false);
		this.equipClothingFromNowhere(cloak, true, this);
		this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_OVER), DisplacementType.SHIFTS_ASIDE, true, true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_jack_o_lantern", false), true, this);
		
		if(!this.hasItemType(ItemType.ADDICTION_REMOVAL)) {
			this.addItem(Main.game.getItemGen().generateItem(ItemType.ADDICTION_REMOVAL));
		}
	}

	@Override
	public String getGenericName() {
		if(this.isFeminine()) {
			return "headless horsewoman";
		}
		return "headless horseman";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return PresetColour.BASE_RED.toWebHexString();
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void dailyUpdate() {
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	
	@Override
	public int getOrgasmsBeforeSatisfied() {
		int orgasmsNeeded = super.getOrgasmsBeforeSatisfied();
		
		if(orgasmsNeeded<2) {
			orgasmsNeeded = 2; // Horseman always wants at least 2 orgasms
		}
		
		return orgasmsNeeded;
	}
	
	@Override
	public void applyEscapeCombatEffects() {
		this.returnToHome();
	}

}