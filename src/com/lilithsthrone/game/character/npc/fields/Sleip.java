package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
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
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Innoxia
 */
public class Sleip extends NPC {

	public Sleip() {
		this(false);
	}
	
	public Sleip(boolean isImported) {
		super(isImported, new NameTriplet("Sleip"), "Loviennemartu",
				"Sleip is one of two nightmare bodyguards who protect Angelixx at all times.",
				28, Month.AUGUST, 28,
				20, Gender.M_P_MALE, Subspecies.HORSE_MORPH, RaceStage.GREATER,
				new CharacterInventory(30),
				WorldType.getWorldTypeFromId("innoxia_dominion_angelixx_apartment"), PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom"),
				true);
		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.setGenericName("furious nightmare");
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.13")) {
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_EBONY), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), false);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.2") && !this.isDoll()) {
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.TWO_NEUTRAL);
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_angelixx_apartment"), PlaceType.getPlaceTypeFromId("innoxia_dominion_angelixx_apartment_bedroom"), true);

			this.clearPenisModifiers();
			this.addPenisModifier(PenetrationModifier.FLARED);
			this.addPenisModifier(PenetrationModifier.VEINY);
			this.addPenisModifier(PenetrationModifier.SHEATHED);
			this.addCumModifier(FluidModifier.MUSKY);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		
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
					PersonalityTrait.SELFISH,
					PersonalityTrait.BRAVE,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.LEWD);

			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_GANG_BODY_GUARD);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_NON_CON_DOM);
			this.addFetish(Fetish.FETISH_DEFLOWERING);

			this.setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);
			
			this.setFetishDesire(Fetish.FETISH_BONDAGE_VICTIM, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.ZERO_HATE);
		}
		
		
		// Body:
		this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, Gender.M_P_MALE, Subspecies.HORSE_MORPH, true, RaceStage.GREATER), false);
		this.setWingType(WingType.NONE);
		this.setHornType(HornType.NONE);
		this.setTailType(TailType.HORSE_MORPH);

		// Core:
		this.setHeight(205);
		this.setFemininity(0);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_RED));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_RED));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_JET_BLACK), true);
		this.setHairLength(HairLength.TWO_SHORT);
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_JET_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_JET_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_JET_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		// Leave as default:
//		this.setLipSize(LipSize.ONE_AVERAGE);
//		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
//		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		// Leave as default:
//		this.setNippleVirgin(true);
//		this.setBreastSize(CupSize.FLAT.getMeasurement());
//		this.setBreastShape(BreastShape.ROUND);
//		this.setNippleSize(NippleSize.ZERO_TINY);
//		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		// Leave as default:
//		this.setAssSize(AssSize.TWO_SMALL);
//		this.setHipSize(HipSize.TWO_NARROW);
//		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
//		this.setAssWetness(Wetness.ZERO_DRY);
//		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
//		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(34);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(400);
		this.fillCumToMaxStorage();
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.FLARED);
		this.addPenisModifier(PenetrationModifier.VEINY);
		this.addPenisModifier(PenetrationModifier.SHEATHED);
		this.addCumModifier(FluidModifier.MUSKY);
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_jockstrap", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_meander_ring", PresetColour.CLOTHING_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_STEEL, false), true, this);
		
		if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_FACTORY)) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hips_leather_belt", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_tshirt", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_stpvest", false), true, this);
			
		} else { // Randomise clothing when meeting in apartment
			Colour torsoColour = Util.randomItemFromValues(
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_DESATURATED_BROWN_DARK,
					PresetColour.CLOTHING_BLUE_NAVY,
					PresetColour.CLOTHING_GREY);
			String torsoId = Util.randomItemFromValues(
					"innoxia_torso_polo_shirt",
					"innoxia_torso_tshirt");
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(torsoId, torsoColour, false), true, this);

			Colour legColour = Util.randomItemFromValues(
					PresetColour.CLOTHING_BLUE_NAVY,
					PresetColour.CLOTHING_BLUE_GREY);
			String legId = Util.randomItemFromValues(
					"innoxia_leg_jeans",
					"innoxia_leg_jeans",
					"innoxia_leg_jeans",
					"innoxia_leg_chinos");
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hips_leather_belt", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(legId, legColour, false), true, this);
		}
		
		AbstractClothing necklace = Main.game.getItemGen().generateClothing("innoxia_neck_horseshoe_necklace", PresetColour.CLOTHING_RED_DARK, PresetColour.CLOTHING_STEEL, null, false);
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MINOR_DRAIN, 0));

		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MINOR_BOOST, 0));

		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0));
		necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MINOR_BOOST, 0));
		this.equipClothingFromNowhere(necklace, true, this);
		necklace.setName("Angelixx's Gift");
		
		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bat_metal", DamageType.PHYSICAL));
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_STEEL, false), true, this);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_cattle_piercing_nose_ring", PresetColour.CLOTHING_STEEL, false), true, this);
		this.setPiercedNipples(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", PresetColour.CLOTHING_STEEL, false), true, this);
		this.setPiercedPenis(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_penis_ring", PresetColour.CLOTHING_STEEL, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getSpeechColour() {
		if(Main.game.isLightTheme()) {
			return "#242120";
		}
		return "#a0a2b1";
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
//	@Override
//	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
//		if(character instanceof Nir) {
//			Set<Relationship> result = new LinkedHashSet<>();
//			result.add(Relationship.Sibling);
//			return result;
//		}
//		return super.getRelationshipsTo(character, excludedRelationships);
//	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex() {
	}

	// combat behaviour is attacking
	@Override
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK;
	}
	
	public void applyPlayerFeminineTf(GameCharacter player) {
		if(player.isAbleToHaveRaceTransformed()) {
			Map<AbstractBodyCoveringType, Covering> oldCoverings = new HashMap<>(player.getBody().getCoverings());
			player.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, false);
			player.getBody().setCoverings(oldCoverings);
			
			// Standard attributes
			player.setHeight(160);
			player.setMuscle(Muscle.TWO_TONED.getMedianValue());
			player.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			player.setFemininity(100);
			player.setArmRows(1);
			if(player.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				player.setLegConfiguration(player.getLegType().getRace().getRacialBody().getLegType(), LegConfiguration.BIPEDAL, false);
			}
			player.setTailType(TailType.NONE);
			player.setHornType(HornType.NONE);
			player.setAntennaType(AntennaType.NONE);
			player.setWingType(WingType.NONE);
			
			// Breasts:
			player.setBreastSize(CupSize.DD);
			player.setBreastShape(BreastShape.ROUND);
			player.setBreastRows(1);
			player.setBreastMilkStorage(0);
			player.setBreastCrotchMilkStorage(0);
			
			// Nipples:
			player.setNippleSize(NippleSize.TWO_BIG);
			player.setNippleShape(NippleShape.NORMAL);
			player.setAreolaeSize(AreolaeSize.TWO_BIG);
			player.setAreolaeShape(AreolaeShape.NORMAL);
			player.setNippleCountPerBreast(1);
			player.setNippleCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
			player.setNippleElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			player.setNipplePlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
			player.clearNippleOrificeModifiers();
			player.addNippleOrificeModifier(OrificeModifier.RIBBED);
			player.clearMilkModifiers();
			player.addMilkModifier(FluidModifier.MINERAL_OIL);
			player.addMilkModifier(FluidModifier.SLIMY);
			player.setMilkFlavour(FluidFlavour.FLAVOURLESS);
			player.getCovering(BodyCoveringType.MILK).setPrimaryColour(PresetColour.COVERING_CLEAR);
			boolean hadCrotchBoobs = player.hasBreastsCrotch();
			if(!hadCrotchBoobs) {
				player.setBreastCrotchType(BreastType.HORSE_MORPH);
			}
			player.setNippleCrotchElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			player.setNippleCrotchPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
			player.clearNippleCrotchOrificeModifiers();
			player.addNippleCrotchOrificeModifier(OrificeModifier.RIBBED);
			player.clearMilkCrotchModifiers();
			player.addMilkCrotchModifier(FluidModifier.MINERAL_OIL);
			player.addMilkCrotchModifier(FluidModifier.SLIMY);
			player.setMilkCrotchFlavour(FluidFlavour.FLAVOURLESS);
			if(!hadCrotchBoobs) {
				player.setBreastCrotchType(BreastType.NONE);
			}
			
			// Ass:
			player.setAssCapacity(Capacity.TWO_TIGHT, true);
			player.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			player.setAssPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
			player.setAssWetness(Wetness.THREE_WET);
			player.clearAssOrificeModifiers();
			player.addAssOrificeModifier(OrificeModifier.RIBBED);
			
			// Tongue:
			player.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
			player.resetTongueModifiers();
			// Face:
			player.setFaceCapacity(Capacity.TWO_TIGHT, true);
			player.setFaceElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			player.setFacePlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
			player.setFaceWetness(Wetness.THREE_WET.getValue());
			player.clearFaceOrificeModifiers();
			player.addFaceOrificeModifier(OrificeModifier.RIBBED);
			// Eyes:
			player.setIrisShape(EyeShape.ROUND);
			
			// Vagina:
			player.setVaginaType(player.getTrueRace().getRacialBody().getVaginaType());
			player.setClitorisGirth(PenetrationGirth.ZERO_THIN.getValue());
			player.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE.getMedianValue());
			player.resetClitorisModifiers();
			player.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
			player.setVaginaCapacity(Capacity.TWO_TIGHT, true);
			player.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			player.setVaginaPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
			player.setVaginaUrethraCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
			player.setVaginaUrethraElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			player.setVaginaUrethraPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
			player.clearVaginaUrethraOrificeModifiers();
			player.clearGirlcumModifiers();
			player.addGirlcumModifier(FluidModifier.MINERAL_OIL);
			player.addGirlcumModifier(FluidModifier.SLIMY);
			player.setGirlcumFlavour(FluidFlavour.FLAVOURLESS);
			player.getCovering(BodyCoveringType.GIRL_CUM).setPrimaryColour(PresetColour.COVERING_CLEAR);
			player.setHymen(false);
			player.setVaginaWetness(Wetness.THREE_WET);
			player.setVaginaSquirter(true);
			player.clearVaginaOrificeModifiers();
			player.addVaginaOrificeModifier(OrificeModifier.RIBBED);
			
			// Penis:
			player.setPenisSize(PenisLength.THREE_LARGE.getMedianValue());
			player.setTesticleSize(TesticleSize.THREE_LARGE);
			if(player.hasVagina()) {
				player.setInternalTesticles(true);
			} else {
				player.setInternalTesticles(false);
			}
			player.setPenisCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
			player.setUrethraElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			player.setUrethraPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
			player.clearUrethraOrificeModifiers();
			player.clearCumModifiers();
			player.addCumModifier(FluidModifier.MINERAL_OIL);
			player.addCumModifier(FluidModifier.SLIMY);
			player.setCumFlavour(FluidFlavour.FLAVOURLESS);
			player.getCovering(BodyCoveringType.CUM).setPrimaryColour(PresetColour.COVERING_CLEAR);
			player.setPenisCumStorage(250);
			player.setPenisType(PenisType.NONE);
			
			// Spinneret:
			boolean hadSpinneret = player.hasSpinneret();
			AbstractTailType originalTail = player.getTailType();
			if(!hadSpinneret) {
				player.setTailType(TailType.getTailTypeFromId("charisma_spider_tail"));
			}
			player.setSpinneretCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue(), true);
			player.setSpinneretElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			player.setSpinneretPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
			player.setSpinneretWetness(Wetness.THREE_WET.getValue());
			if(!hadSpinneret) {
				player.setTailType(originalTail);
			}
			
			// Remove all hair:
			player.setHairLength(0);
			player.setFacialHair(0);
			player.setUnderarmHair(0);
			player.setPubicHair(0);
			player.setAssHair(0);
			
		} else {
			player.setFemininity(100);
			if(player.getBreastSize().getMeasurement()<9) {
				player.setBreastSize(CupSize.DD);
			}
			player.setVaginaType(player.getTrueRace().getRacialBody().getVaginaType());
		}
		
		
	
	}
}