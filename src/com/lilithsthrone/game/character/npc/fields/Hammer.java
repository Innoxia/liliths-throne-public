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
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
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
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Innoxia
 */
public class Hammer extends NPC {

	public Hammer() {
		this(false);
	}
	
	public Hammer(boolean isImported) {
		super(isImported, new NameTriplet("Alex"), "Hall",
				"One of the SWORD Enforcers stationed in Elis, this grey-furred wolf-boy is known only by his callsign, 'Hammer'.",
				69, Month.DECEMBER, 1,
				40,
				Gender.M_P_MALE, Subspecies.WOLF_MORPH, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_enforcer_station"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_enforcer_station_sword"),
				true);
		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.setGenericName("one-eyed wolf-boy");
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.8.10")) {
			this.setupPerks(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		this.addSpecialPerk(Perk.SPECIAL_ENFORCER_FIREARMS_TRAINING);
		
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.CLOTHING_ENCHANTER,
						Perk.WEAPON_ENCHANTER,
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
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE,
					PersonalityTrait.CYNICAL);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_ENFORCER_SWORD_SERGEANT);
			
			this.addFetish(Fetish.FETISH_CUM_STUD);
			
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
		}
		
		// Body:
		// Core:
		this.setHeight(188);
		this.setFemininity(5);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());

		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, CoveringPattern.EYE_IRISES_HETEROCHROMATIC, CoveringModifier.EYE, PresetColour.EYE_YELLOW, false, PresetColour.EYE_WHITE, false));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_PUPILS, CoveringPattern.EYE_IRISES_HETEROCHROMATIC, CoveringModifier.EYE, PresetColour.EYE_BLACK, false, PresetColour.EYE_GREY, false));
		
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);
		body.getCoverings().put(BodyCoveringType.LYCAN_FUR, new Covering(BodyCoveringType.LYCAN_FUR, CoveringPattern.HIGHLIGHTS, CoveringModifier.SHORT, PresetColour.COVERING_GREY, false, PresetColour.COVERING_WHITE, false));

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, CoveringPattern.HIGHLIGHTS, PresetColour.COVERING_GREY, false, PresetColour.COVERING_WHITE, false), false);
		body.getCoverings().put(BodyCoveringType.HAIR_LYCAN_FUR, new Covering(BodyCoveringType.HAIR_LYCAN_FUR, CoveringPattern.HIGHLIGHTS, PresetColour.COVERING_GREY, false, PresetColour.COVERING_WHITE, false));
		this.setHairLength(0);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_DARK_GREY), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, PresetColour.COVERING_DARK_GREY), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.FIVE_UNKEMPT);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(24);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(80);
		this.fillCumToMaxStorage();
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		// Elle ribbons, but 2nd class graduate, one star
		
		this.setScar(InventorySlot.CHEST, new Scar(ScarType.CLAW_MARKS, true));
		this.setScar(InventorySlot.LEG, new Scar(ScarType.STRAIGHT_SCAR, false));
		this.setScar(InventorySlot.EYES, new Scar(ScarType.JAGGED_SCAR, false));
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_BLACK_STEEL, false), true, this);

		AbstractClothing trousers = Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_tpants", PresetColour.CLOTHING_GREY, false);
		trousers.setPattern("none");
		this.equipClothingFromNowhere(trousers, true, this);

		AbstractClothing shirt = Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_enf_sslcbtshirt", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_GREY_DARK, null, false);
		shirt.setPattern("none");
		this.equipClothingFromNowhere(shirt, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_battlebelt", false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_patch", PresetColour.CLOTHING_BLACK, false), true, this);
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_eep_pbweap_pbpistol"));
			this.setEssenceCount(150);
		}
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.game.isLightTheme()) {
			return "#3b3e4e";
		}
		return "#8fa2e6";
	}
	
	@Override
	public void hourlyUpdate(int hour) {
	}

	@Override
	public void turnUpdate() {
		if(Main.game.getDialogueFlags().hasFlag("innoxia_hammer_following")) {
			this.setLocation(Main.game.getPlayer(), false);
		}
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Combat:

	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		equipMove("strike");
		//equipMove("offhand-strike");
		//equipMove("twin-strike");
		//equipMove("block");
		this.equipAllSpellMoves();
	}
	
	@Override
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK;
	}
	
	// Extra methods:

	public void equipCivilianGear() {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_BLACK_STEEL, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_cap", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_tshirt", PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_ribbed_jumper", PresetColour.CLOTHING_GREY_DARK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
	}
	
	public void equipOpsGear() {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_BLACK_STEEL, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_patch", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_hpltcarrier", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cbtshirt", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_hbattlebelt", PresetColour.CLOTHING_BLACK, false), true, this);
		AbstractClothing trousers = Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_tpants", PresetColour.CLOTHING_GREY, false);
		trousers.setPattern("none");
		this.equipClothingFromNowhere(trousers, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_tkneepads", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_telbowpads", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, false), true, this);
		
		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_gbshotgun"));
		this.setEssenceCount(250);
	}
	
	public AbstractWeapon getDagger() {
		AbstractWeapon dagger = Main.game.getItemGen().generateWeapon("innoxia_dagger_sword", DamageType.PHYSICAL, Util.newArrayListOfValues(PresetColour.CLOTHING_GUNMETAL, PresetColour.CLOTHING_DESATURATED_BROWN_DARK));
		dagger.setEffects(Util.newArrayListOfValues(
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MINOR_BOOST, 0),

				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MINOR_BOOST, 0)
				));
		dagger.setName("Hammer's SWORD dagger");
		return dagger;
	}
	
	public void equipOpsGearHead() {
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_balaclava", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_chelmet", PresetColour.CLOTHING_BLACK, false), true, this);
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_gmask", PresetColour.CLOTHING_BLACK, false), true, this);
	}
	
	/**
	 * Moves the barricade in the abandoned bakery so that Hammer's route covers the entire ground floor.
	 */
	public void resetBarricade() {
		World bakery = Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_abandoned_bakery_f0"));
		bakery.getCell(2, 2).setPlace(new GenericPlace(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_abandoned_bakery_f0_barricade")), true);
		bakery.getCell(1, 3).setPlace(new GenericPlace(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_abandoned_bakery_f0_kitchen")), true);
	}
	
}
