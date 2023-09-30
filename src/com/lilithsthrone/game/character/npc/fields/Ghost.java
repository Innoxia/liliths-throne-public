package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
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
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Innoxia
 */
public class Ghost extends NPC {

	public Ghost() {
		this(false);
	}
	
	public Ghost(boolean isImported) {
		super(isImported, new NameTriplet("Laura"), "Macaulay",
				"One of the SWORD Enforcers stationed in Elis, this small otter-girl is known only by her callsign, 'Ghost'.",
				67, Month.JULY, 11,
				42,
				Gender.F_V_B_FEMALE, Subspecies.getSubspeciesFromId("dsg_otter_subspecies_otter"), RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_enforcer_station"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_enforcer_station_sword"),
				true);
		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.setGenericName("small otter-girl");
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
		this.addSpecialPerk(Perk.SPECIAL_RANGED_EXPERT);
		this.addSpecialPerk(Perk.MARTIAL_ARTIST);
		this.addSpecialPerk(Perk.SPECIAL_ENFORCER_FIREARMS_TRAINING);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.CLOTHING_ENCHANTER,
						Perk.WEAPON_ENCHANTER,
						Perk.OBSERVANT,
						Perk.HYPERMOBILITY),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 10),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
			
			this.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			
			this.setHistory(Occupation.NPC_ENFORCER_SWORD_SERGEANT);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
		}
		
		// Body:
		
		// Core:
		this.setHeight(154);
		this.setFemininity(75);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("dsg_otter_eye"), CoveringPattern.EYE_IRISES, PresetColour.EYE_GREEN, false, PresetColour.EYE_GREEN, false));
		this.setSkinCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("dsg_otter_fur"), CoveringPattern.MARKED, CoveringModifier.SMOOTH, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);

		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("dsg_otter_hair"), CoveringPattern.HIGHLIGHTS, CoveringModifier.SMOOTH, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false), false);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.BUN);
		
		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("dsg_otter_body_hair"), PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLUE_LIGHT));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setAssPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		// Anus modifiers
		
		// Penis:
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		// Elle ribbons, 1st class graduate, two stars

		this.setScar(InventorySlot.WRIST, new Scar(ScarType.BURNS, true));
		this.setScar(InventorySlot.HAND, new Scar(ScarType.BURNS, true));
		
		this.setPiercedEar(true);
		this.setPiercedNose(true);
		this.setPiercedNavel(true);
		this.setPiercedNipples(true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_sports_bra", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);

		AbstractClothing trousers = Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_tpants", PresetColour.CLOTHING_GREY, false);
		trousers.setPattern("none");
		this.equipClothingFromNowhere(trousers, true, this);

		AbstractClothing shirt = Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_enf_sslcbtshirt", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_GREY_DARK, null, false);
		shirt.setPattern("none");
		this.equipClothingFromNowhere(shirt, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_battlebelt", false), true, this);

		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.setEssenceCount(150);
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("dsg_eep_pbweap_pbpistol")));
		}
		
		if(settings.contains(EquipClothingSetting.ADD_ACCESSORIES)) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_SILVER, false), true, this);
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.game.isLightTheme()) {
			return "#48372b";
		}
		return "#c4927c";
	}
	
	@Override
	public void hourlyUpdate(int hour) {
	}
	
	@Override
	public void turnUpdate() {
		if(Main.game.getDialogueFlags().hasFlag("innoxia_ghost_following")) {
			this.setLocation(Main.game.getPlayer(), false);
		}
	}
	
	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Sex:

	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	// Combat:

	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		equipMove("strike");
		equipMove("offhand-strike");
		equipMove("twin-strike");
		equipMove("block");
		this.equipAllSpellMoves();
	}
	
	@Override
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK;
	}
	
	// Extra methods:

	public void equipCivilianGear() {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_sports_bra", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("phlarx_sunglasses_round_sunglasses", PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_BLUE_DARK, PresetColour.CLOTHING_PINK_DARK, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_LONG_SLEEVE_DRESS, PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_trainer_socks", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_flats", PresetColour.CLOTHING_BLACK, false), true, this);
	}

	public void equipOpsGear() {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_sports_bra", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_bglasses", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_STEEL, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_pltcarrier", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cbtshirt", PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_battlebelt", PresetColour.CLOTHING_BLACK, false), true, this);
		AbstractClothing trousers = Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_tpants", PresetColour.CLOTHING_GREY, false);
		trousers.setPattern("none");
		this.equipClothingFromNowhere(trousers, true, this);
		
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_tkneepads", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false), true, this);
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_telbowpads", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, false), true, this);

		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbrevolver"));
		this.setEssenceCount(250);
		this.equipOffhandWeaponFromNowhere(getDagger());
	}
	
	public AbstractWeapon getDagger() {
		AbstractWeapon dagger = Main.game.getItemGen().generateWeapon("innoxia_dagger_sword", DamageType.POISON, Util.newArrayListOfValues(PresetColour.CLOTHING_GUNMETAL, PresetColour.CLOTHING_DESATURATED_BROWN_DARK));
		dagger.setEffects(Util.newArrayListOfValues(
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0),
				new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.BOOST, 0)
				));
		dagger.setName("Ghost's SWORD dagger");
		return dagger;
	}
	
	public void equipOpsGearHead() {
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_balaclava", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_chelmet", PresetColour.CLOTHING_BLACK, false), true, this);
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_gmask", PresetColour.CLOTHING_BLACK, false), true, this);
	}
	
}
