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
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
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
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class Vronti extends NPC {

	public Vronti() {
		this(false);
	}
	
	public Vronti(boolean isImported) {
		super(isImported, new NameTriplet("Vronti"), "Grigori",
				"The older brother of the only two centaurs brave enough to keep their Dominion-to-Elis transport business running, Vronti has the body of a Greek god, and a stern, stoic personality to match.",
				29, Month.AUGUST, 15,
				20, Gender.M_P_MALE, Subspecies.CENTAUR, RaceStage.PARTIAL_FULL,
				new CharacterInventory(10),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				true);
		
		this.setGenericName("muscular centaur");
		
		if(!isImported) {
			this.setPlayerKnowsName(false);
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.0.5")) {
			this.equipClothing();
			this.setPlayerKnowsName(false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.0.6")) {
			this.setHomeLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.6.1")) {
			this.setStartingBody(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(Perk.MELEE_DAMAGE),
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
					PersonalityTrait.BRAVE,
					PersonalityTrait.PRUDE);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_TAUR_TRANSPORT);
			
			this.setFetishDesire(Fetish.FETISH_MASTURBATION, FetishDesire.THREE_LIKE);
		}
		
		
		// Body:
		setBody(Gender.M_P_MALE, Subspecies.CENTAUR, RaceStage.PARTIAL_FULL, false);
		
		// Core:
		this.setHeight(225);
		this.setFemininity(0);
		this.setMuscle(95);
		this.setBodySize(BodySize.FOUR_HUGE.getMedianValue());
		// Parts changes:
		this.setHairType(HairType.HUMAN);
		this.setEarType(EarType.HUMAN);
		this.setEyeType(EyeType.HUMAN);
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_BROWN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BLACK), true);

		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_EBONY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BLACK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.TWO_SHORT.getMedianValue());
		this.setHairStyle(HairStyle.STRAIGHT);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
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
		this.setPenisVirgin(true);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(48);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(350);
		this.setPenisCumExpulsion(85);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		// Horse-like modifiers:
//		this.clearPenisModifiers();
//		this.addPenisModifier(PenetrationModifier.FLARED);
//		this.addPenisModifier(PenetrationModifier.VEINY);
//		this.addPenisModifier(PenetrationModifier.SHEATHED);
//		this.addCumModifier(FluidModifier.MUSKY);
		
		// Vagina:
//		this.setVaginaVirgin(false);
//		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
//		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
//		this.setVaginaSquirter(true);
//		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
//		this.setVaginaWetness(Wetness.FOUR_SLIMY);
//		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
//		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_BRONZE, PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		
	}

	/**
	 * Equips weapon, ready for transport.
	 */
	public void applyWeapon(boolean equip) {
		if(equip) {
			// Weapon:
			AbstractWeapon spear = null;
			for(AbstractWeapon w : this.getAllWeaponsInInventory().keySet()) {
				if(w.getWeaponType()==WeaponType.getWeaponTypeFromId("innoxia_spear_dory")) {
					spear = w;
					break;
				}
			}
			if(spear!=null) {
				this.equipMainWeaponFromInventory(spear, this);
			} else {
				this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_spear_dory", DamageType.PHYSICAL));
			}

		} else {
			// Weapon:
			AbstractWeapon spear = this.getMainWeapon(0);
			if(spear!=null) {
				this.unequipWeapon(InventorySlot.WEAPON_MAIN_1, spear, false, false);
			}
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.game.isLightTheme()) {
			return "#1c5583";
		}
		return "#37a5ff";
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

}