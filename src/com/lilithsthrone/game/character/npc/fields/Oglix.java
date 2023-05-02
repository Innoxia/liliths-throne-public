package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.4
 * @version 0.4.4
 * @author Innoxia
 */
public class Oglix extends NPC {

	public Oglix() {
		this(false);
	}
	
	public Oglix(boolean isImported) {
		super(isImported, new NameTriplet("Oglix"), "Lunettemartu",
				"The owner of the tavern, 'The Crossed Blades', Oglix's green skin, bulging muscles, and considerable height almost make her look more like some kind of fantasy orc than a succubus."
						+ " Her size, strength, and considerable arcane abilities make her respected and feared by even the toughest of thugs to be found in Elis.",
				48, Month.AUGUST, 9,
				35, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(30),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_alley_bar"),
				true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 3)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.BRAVE,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.LEWD,
					PersonalityTrait.SLOVENLY);

			this.addSpell(Spell.POISON_VAPOURS);
			this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_1);
			this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_2);
			this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_3);
			
			this.addSpell(Spell.SLAM);
			this.addSpellUpgrade(SpellUpgrade.SLAM_1);
			this.addSpellUpgrade(SpellUpgrade.SLAM_2);
			this.addSpellUpgrade(SpellUpgrade.SLAM_3);

			this.addSpell(Spell.STONE_SHELL);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_1);
			
			this.addSpell(Spell.ELEMENTAL_EARTH);
			this.addSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_1);
			this.addSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_2);
			this.addSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3B);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_TAVERN_OWNER);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
			this.addFetish(Fetish.FETISH_VOYEURIST);

			this.setFetishDesire(Fetish.FETISH_LACTATION_OTHERS, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
		}
		
		
		// Body:
		this.setWingType(WingType.NONE);
		this.setHornType(HornType.NONE);
		this.setTailType(TailType.NONE);
		this.setLegType(LegType.DEMON_COMMON);

		// Core:
		this.setAgeAppearanceDifferenceToAppearAsAge(36);
		this.setHeight(222);
		this.setFemininity(75);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.FOUR_HUGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_ORANGE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_GREEN), true);

		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, PresetColour.SKIN_GREEN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_GREEN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_GREEN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, PresetColour.SKIN_GREEN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.BRAIDED);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_BLUE_LIGHT));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_BLUE_LIGHT));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_BLUE));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
//		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
//		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.WIDE);
		this.setNippleSize(NippleSize.FOUR_MASSIVE);
		this.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FIVE_HUGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis

		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_sweatband", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("TonyJC_tie_up_crop_top", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_hotpants", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_PURPLE_DARK, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_thigh_high_boots", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);

		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.setPiercedNipples(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", PresetColour.CLOTHING_GOLD, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public String getSpeechColour() {
		return PresetColour.BASE_GREEN.toWebHexString();
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
		this.equipClothing();
	}
	
	@Override
	public void hourlyUpdate(int hour) {
		// beer bitch sex every hour
		if(!(hour>=5 && hour<14)) {
			for(NPC npc : getBeerBitches()) {
				if((npc.hasVagina() || Main.game.isAnalContentEnabled()) && Math.random()<0.8f) {
					Gender gender = Gender.getGenderFromUserPreferences(false, true);
					Map<AbstractSubspecies, Integer> availableRaces = AbstractSubspecies.getGenericSexPartnerSubspeciesMap(gender);
					
					AbstractSubspecies subspecies = Subspecies.HUMAN;
					AbstractSubspecies halfDemonSubspecies = null;
					if(!availableRaces.isEmpty()) {
						subspecies = Util.getRandomObjectFromWeightedMap(availableRaces);
					}
					SexType sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
					if(npc.hasVagina() && Math.random()<0.75f) {
						sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
					}
					npc.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, sexType);
					if(Math.random()<0.1f && npc.getTotalFluidInArea((SexAreaOrifice)sexType.getPerformingSexArea())<100) {
						// Big creampie:
						npc.ingestFluid(null, subspecies, halfDemonSubspecies, subspecies.getRace().getRacialBody().getPenisType().getTesticleType().getFluidType(), (SexAreaOrifice)sexType.getPerformingSexArea(), 500 + Util.random.nextInt(501));
					}
				}
			}
		}
	}
	
	// Methods for use in external dialogue:
	
	public void initElemental() {
		if(!this.isElementalSummoned()) {
			Spell.ELEMENTAL_EARTH.applyEffect(this, this, true, false);
			
			Elemental elemental = this.getElemental();
			elemental.setName("Golix");
			
			elemental.setSpeechColour(PresetColour.BASE_BROWN_LIGHT);

			elemental.addFetish(Fetish.FETISH_DOMINANT);
			elemental.addFetish(Fetish.FETISH_NON_CON_DOM);
			
			elemental.addFetish(Fetish.FETISH_PENIS_GIVING);
			elemental.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			elemental.addFetish(Fetish.FETISH_ANAL_GIVING);
			elemental.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			
			elemental.setBodyMaterial(BodyMaterial.STONE);
			elemental.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(elemental.getBodyMaterial(), BodyCoveringCategory.MAIN_SKIN), PresetColour.COVERING_GREY), true);
			
			elemental.setVaginaType(VaginaType.NONE);
			
			elemental.setPenisType(PenisType.DEMON_COMMON);
			elemental.setPenisGirth(PenetrationGirth.FIVE_THICK);
			elemental.setPenisSize(38);
			elemental.setTesticleSize(TesticleSize.FOUR_HUGE);
			elemental.setPenisCumStorage(1000);
			elemental.setPenisCumExpulsion(85);
			elemental.fillCumToMaxStorage();
			elemental.setTesticleCount(2);
			
			elemental.clearPenisModifiers();
			elemental.addPenisModifier(PenetrationModifier.RIBBED);
			
			elemental.setVaginaVirgin(false);
			elemental.setPenisVirgin(false);
			elemental.setFaceVirgin(false);
			elemental.setNippleVirgin(false);
			elemental.setAssVirgin(false);
			
			Main.game.getNpc(Kheiron.class).setAffection(elemental, AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
			elemental.setAffection(Main.game.getNpc(Kheiron.class), AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
		}
	}
	
	public void initBeerBitches() {
		if(!Main.game.getDialogueFlags().hasFlag("innoxia_oglix_beer_bitches_spawned")) {
			Main.game.getDialogueFlags().setFlag("innoxia_oglix_beer_bitches_spawned", true);
			
			GenericSexualPartner bitch1 = new GenericSexualPartner(Gender.getGenderFromUserPreferences(Femininity.FEMININE),
					WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley"),
					PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_alley_bar"),
					false,
					((s) -> s.isNonBiped() && s.getRace()!=Race.SLIME));

			bitch1.addFetish(Fetish.FETISH_EXHIBITIONIST);
			bitch1.addFetish(Fetish.FETISH_BONDAGE_VICTIM);
			bitch1.addFetish(Fetish.FETISH_BREASTS_SELF);
			bitch1.addFetish(Fetish.FETISH_LACTATION_SELF);
			bitch1.addFetish(Fetish.FETISH_NON_CON_SUB);
			bitch1.addFetish(Fetish.FETISH_SUBMISSIVE);
			bitch1.addFetish(Fetish.FETISH_MASOCHIST);
			bitch1.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			bitch1.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			bitch1.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			bitch1.addFetish(Fetish.FETISH_CUM_ADDICT);
			
			bitch1.setBreastSize(CupSize.JJ);
			bitch1.setNippleSize(NippleSize.FOUR_MASSIVE);
			bitch1.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
			bitch1.setBreastMilkStorage(5000);
			bitch1.fillMilkToMaxStorage();
			bitch1.setBreastLactationRegeneration(50_000);
			
			bitch1.clearMilkModifiers();
			bitch1.setMilkFlavour(FluidFlavour.BEER);
			bitch1.addMilkModifier(FluidModifier.ALCOHOLIC_WEAK);
			bitch1.addMilkModifier(FluidModifier.BUBBLING);
			bitch1.setSkinCovering(new Covering(BodyCoveringType.MILK, CoveringPattern.FLUID, PresetColour.COVERING_ORANGE_LIGHT, false, PresetColour.COVERING_ORANGE_LIGHT, false), false);
			
			bitch1.setVaginaWetness(Wetness.SIX_SOPPING_WET);
			
			bitch1.unequipAllClothingIntoVoid(true, true);
			bitch1.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), PresetColour.CLOTHING_BROWN_DARK, false), InventorySlot.NIPPLE, true, bitch1);
			bitch1.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_torsoOver_beer_barrel"), false), true, bitch1);
			bitch1.setGenericName("beer-bitch");
			try {
				Main.game.addNPC(bitch1, false, true);
				this.addSlave(bitch1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			GenericSexualPartner bitch2 = new GenericSexualPartner(Gender.getGenderFromUserPreferences(Femininity.FEMININE),
//					WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley"),
//					PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_alley_bar"),
//					false,
//					((s) -> s.isNonBiped() && s.getRace()!=Race.SLIME));
//			bitch2.setBreastSize(CupSize.HH);
//			bitch2.setNippleSize(NippleSize.THREE_LARGE);
//			bitch2.setAreolaeSize(AreolaeSize.THREE_LARGE);
//			bitch2.setBreastMilkStorage(5000);
//			bitch2.fillMilkToMaxStorage();
//			bitch2.setBreastLactationRegeneration(50_000);
//			
//			bitch2.clearMilkModifiers();
//			bitch2.setMilkFlavour(FluidFlavour.BEER);
//			bitch2.addMilkModifier(FluidModifier.ALCOHOLIC);
//			bitch2.addMilkModifier(FluidModifier.BUBBLING);
//			bitch2.setSkinCovering(new Covering(BodyCoveringType.MILK, CoveringPattern.FLUID, PresetColour.COVERING_ORANGE_LIGHT, false, PresetColour.COVERING_ORANGE_LIGHT, false), false);
//			
//			bitch2.unequipAllClothingIntoVoid(true, true);
//			bitch2.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), PresetColour.CLOTHING_BROWN_DARK, false), InventorySlot.NIPPLE, true, bitch2);
//			bitch2.setGenericName("buxom beer-bitch");
//			try {
//				Main.game.addNPC(bitch2, false, true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}
	
	public void beerBitchCreampie() {
		NPC npc = this.getBeerBitches().get(0);
		
		Gender gender = Gender.getGenderFromUserPreferences(false, true);
		Map<AbstractSubspecies, Integer> availableRaces = AbstractSubspecies.getGenericSexPartnerSubspeciesMap(gender);
		
		AbstractSubspecies subspecies = Subspecies.HUMAN;
		AbstractSubspecies halfDemonSubspecies = null;
		if(!availableRaces.isEmpty()) {
			subspecies = Util.getRandomObjectFromWeightedMap(availableRaces);
		}
		SexType sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
		if(npc.hasVagina() && Math.random()<0.75f) {
			sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
		}
		npc.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, sexType);
		npc.ingestFluid(null, subspecies, halfDemonSubspecies, subspecies.getRace().getRacialBody().getPenisType().getTesticleType().getFluidType(), (SexAreaOrifice)sexType.getPerformingSexArea(), 500);
	}
	
	public List<NPC> getBeerBitches() {
		List<NPC> bitches = new ArrayList<>();
		for(NPC npc : Main.game.getCharactersPresent(this.getCell())) {
			if(npc.isSlave() && npc.getOwner().equals(this)) {
				bitches.add(npc);
			}
		}
		bitches.sort((b1, b2) -> {
			if(b1.getGenericName().equals("beer-bitch")) {
				return -1;
			}
			int b1Index = 0;
			int b2Index = 0;
			for(int index=0; index<beerBitchNames.length;index++) {
				if(b1.getGenericName().equals(beerBitchNames[index])) {
					b1Index = index;
				}
				if(b2.getGenericName().equals(beerBitchNames[index])) {
					b2Index = index;
				}
			}
			return b1Index - b2Index;
		});
		return bitches;
	}
	
	public boolean isBeerBitchBarrelAvailable() {
		return Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_OGLIX_BEER_BARRELS) && getBeerBitches().size()<5;
	}

	private String[] beerBitchNames = new String[] {"beer-slut", "beer-cunt", "beer-whore", "beer-tits"};
	
	public void addBeerBitch(NPC bitch) {
		bitch.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley"),
					PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_alley_bar"),
					true);

		bitch.addFetish(Fetish.FETISH_EXHIBITIONIST);
		bitch.addFetish(Fetish.FETISH_BONDAGE_VICTIM);
		bitch.addFetish(Fetish.FETISH_BREASTS_SELF);
		bitch.addFetish(Fetish.FETISH_LACTATION_SELF);
		bitch.addFetish(Fetish.FETISH_NON_CON_SUB);
		bitch.addFetish(Fetish.FETISH_SUBMISSIVE);
		bitch.addFetish(Fetish.FETISH_MASOCHIST);
		bitch.addFetish(Fetish.FETISH_ANAL_RECEIVING);
		if(bitch.hasVagina()) {
			bitch.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		}
		if(bitch.hasPenisIgnoreDildo()) {
			bitch.addFetish(Fetish.FETISH_PENIS_GIVING);
		}
		bitch.addFetish(Fetish.FETISH_PENIS_RECEIVING);
		bitch.addFetish(Fetish.FETISH_CUM_ADDICT);
		
		if(!bitch.isFeminine()) {
			bitch.setFemininity(75);
		}
		
		bitch.setBreastSize(CupSize.HH.getMeasurement()+Util.random.nextInt(5));
		bitch.setNippleSize(NippleSize.FOUR_MASSIVE);
		bitch.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		bitch.setBreastMilkStorage(5000);
		bitch.fillMilkToMaxStorage();
		bitch.setBreastLactationRegeneration(50_000);
		
		bitch.clearMilkModifiers();
		bitch.setMilkFlavour(FluidFlavour.BEER);
		bitch.addMilkModifier(FluidModifier.ALCOHOLIC_WEAK);
		bitch.addMilkModifier(FluidModifier.BUBBLING);
		bitch.setSkinCovering(new Covering(BodyCoveringType.MILK, CoveringPattern.FLUID, PresetColour.COVERING_ORANGE_LIGHT, false, PresetColour.COVERING_ORANGE_LIGHT, false), false);

		bitch.setVaginaWetness(Wetness.SIX_SOPPING_WET);
		
		bitch.unequipAllClothingIntoVoid(true, true);
		bitch.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), PresetColour.CLOTHING_BROWN_DARK, false), InventorySlot.NIPPLE, true, bitch);
		bitch.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_torsoOver_beer_barrel"), false), true, bitch);
		
		bitch.setGenericName(beerBitchNames[getBeerBitches().size()-1]);
		bitch.setPlayerKnowsName(false);
		
		this.addSlave(bitch);
	}
}