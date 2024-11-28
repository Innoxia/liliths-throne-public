package com.lilithsthrone.game.character.npc.fields;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
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
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class Minotallys extends NPC {
	
	private Map<DayOfWeek, FlavourInformation> flavourInformationMap = Util.newHashMapOfValues(
			new Value<>(DayOfWeek.MONDAY, new FlavourInformation(FluidFlavour.VANILLA, PresetColour.COVERING_BLACK, PresetColour.COVERING_BLACK, PresetColour.COVERING_BLACK, PresetColour.CLOTHING_BLACK)),
			new Value<>(DayOfWeek.TUESDAY, new FlavourInformation(FluidFlavour.STRAWBERRY, PresetColour.COVERING_PINK_LIGHT, PresetColour.COVERING_PINK, PresetColour.COVERING_PINK_DARK, PresetColour.CLOTHING_PINK_HOT)),
			new Value<>(DayOfWeek.WEDNESDAY, new FlavourInformation(FluidFlavour.BLUEBERRY, PresetColour.COVERING_BLUE_LIGHT, PresetColour.COVERING_BLUE, PresetColour.COVERING_BLUE_DARK, PresetColour.CLOTHING_BLUE_LIGHT)),
			new Value<>(DayOfWeek.THURSDAY, new FlavourInformation(FluidFlavour.CHOCOLATE, PresetColour.COVERING_BROWN_LIGHT, PresetColour.COVERING_BROWN, PresetColour.COVERING_BROWN_DARK, PresetColour.CLOTHING_BROWN_DARK)),
			new Value<>(DayOfWeek.FRIDAY, new FlavourInformation(FluidFlavour.HONEY, PresetColour.COVERING_AMBER, PresetColour.COVERING_YELLOW, PresetColour.COVERING_AMBER, PresetColour.CLOTHING_YELLOW)),
			new Value<>(DayOfWeek.SATURDAY, new FlavourInformation(FluidFlavour.MINT, PresetColour.COVERING_GREEN_LIGHT, PresetColour.COVERING_GREEN, PresetColour.COVERING_GREEN_DARK, PresetColour.CLOTHING_GREEN)),
			new Value<>(DayOfWeek.SUNDAY, new FlavourInformation(FluidFlavour.CHERRY, PresetColour.COVERING_RED, PresetColour.COVERING_RED, PresetColour.COVERING_RED_DARK, PresetColour.CLOTHING_RED_DARK)));

	private static FluidFlavour milkFlavour = FluidFlavour.VANILLA;
	private static Colour coveringColour = PresetColour.COVERING_BLACK;
	private static Colour bodyHairColour = PresetColour.COVERING_BLACK;
	private static Colour makeupColour = PresetColour.COVERING_BLACK;
	private static Colour clothingColour = PresetColour.CLOTHING_BLACK;
	
	private static String earringId = "innoxia_piercing_ear_chain_dangle";
	
	private class FlavourInformation {
		FluidFlavour flavour;
		Colour coveringColour;
		Colour bodyHairColour;
		Colour makeupColour;
		Colour clothingColour;
		public FlavourInformation(FluidFlavour flavour, Colour coveringColour, Colour bodyHairColour, Colour makeupColour, Colour clothingColour) {
			this.flavour = flavour;
			this.coveringColour = coveringColour;
			this.bodyHairColour = bodyHairColour;
			this.makeupColour = makeupColour;
			this.clothingColour = clothingColour;
		}
		public FluidFlavour getFlavour() {
			return flavour;
		}
		public Colour getCoveringColour() {
			return coveringColour;
		}
		public Colour getBodyHairColour() {
			return bodyHairColour;
		}
		public Colour getMakeupColour() {
			return makeupColour;
		}
		public Colour getClothingColour() {
			return clothingColour;
		}
	}
	
	public Minotallys() {
		this(false);
	}
	
	public Minotallys(boolean isImported) {
		super(isImported,
				new NameTriplet("Minotallys"), "Lilithmartuilani",
				"Transformed into a lilin by Lilith herself, Minotallys is unquestionably loyal to her mother."
					+ " She typically defers to her assistant, Arion, on all matters related to the everyday running of Elis.",
				643, Month.MAY, 18,
				250,
				Gender.F_V_B_FEMALE, Subspecies.COW_MORPH, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_town_hall_f1"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_hall_f1_minotallys_room"),
				true);
		if(!isImported) {
			this.setPlayerKnowsName(true);
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.5")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.LEWD);
			this.setDescription("Transformed into a lilin by Lilith herself, Minotallys is unquestionably loyal to her mother."
					+ " Despite her status and power, she typically defers to the advice of her assistant, Arion, on all decisions.");
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
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
			
			this.setHistory(Occupation.NPC_ELIS_MAYOR);
			
			this.clearFetishes();
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_LACTATION_SELF);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);

			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		}
		
		this.setBody(Gender.F_V_B_FEMALE, Subspecies.COW_MORPH, RaceStage.GREATER, false);

		this.setPiercedEar(true);
		AbstractClothing earrings = null;
		for(Entry<AbstractClothing, Integer> c : this.getAllClothingInInventory().entrySet()) {
			if(c.getKey().getClothingType().equals(ClothingType.getClothingTypeFromId(earringId))) {
				earrings = c.getKey();
			}
		}
		if(earrings!=null) {
			this.equipClothingFromInventory(earrings, true, this, this);
		}
		
		// Body:
		this.setSubspeciesOverride(Subspecies.LILIN);
		this.setAgeAppearanceAbsolute(20);
//		this.setTailType(TailType.DEMON_COMMON);
//		this.setWingType(WingType.DEMON_COMMON);
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(5);
//		this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);

		// Core:
		this.setHeight(178);
		this.setFemininity(90);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		
		this.setEyeType(EyeType.DEMON_COMMON);
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_COW_MORPH, PresetColour.EYE_GOLD));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_GOLD));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_IVORY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.BOVINE_FUR, CoveringPattern.SPOTTED, PresetColour.COVERING_WHITE, false, coveringColour, false), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_WHITE), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_ROSY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_ROSY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_ROSY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_ROSY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_ROSY), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_BOVINE_FUR, coveringColour), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, coveringColour), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.BOB_CUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, bodyHairColour), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_BOVINE_FUR, bodyHairColour), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, makeupColour));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, makeupColour));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.HH.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.FOUR_MASSIVE);
		this.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		this.setBreastMilkStorage(500);
		this.setMilkFlavour(milkFlavour);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FIVE_HUGE);
		this.setHipSize(HipSize.SIX_EXTREMELY_WIDE);
		this.clearAssOrificeModifier();
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisType(PenisType.NONE);
		this.clearPenisModifiers();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.SIX_SOPPING_WET);
		this.setVaginaElasticity(OrificeElasticity.SIX_SUPPLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		this.clearVaginaOrificeModifiers();
		
		// Feet:
		// Foot shape
		
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.minotallys_tf_required, true);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.resetInventory(true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_panties", clothingColour, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_nursing_bra", clothingColour, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_feminine_blazer", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_feminine_short_sleeve_shirt", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_mini_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_wrist_bangle", PresetColour.CLOTHING_PLATINUM, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_gemstone_ring", PresetColour.CLOTHING_PLATINUM, clothingColour, null, false), true, this);
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(earringId, PresetColour.CLOTHING_PLATINUM, false), true, this);
	}

	@Override
	public Colour getSpeechGlowColour() {
		if(this.getTorsoType().getRace()==Race.DEMON) {
			return PresetColour.BASE_PINK_LIGHT;
		}
		return null;
	}
	
	@Override
	public String getSpeechColour() {
		if(this.getTorsoType().getRace()==Race.DEMON) {
			if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
				return "#872f91";
			}
			return "#e550f5";
		}
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#b45cbe";
		}
		return "#e977f5";
	}
	
	@Override
	public boolean isUnique() {
		return true;
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
	}
	
	@Override
	public void dailyUpdate() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.minotallys_tf_required, true);
	}
	
	@Override
	public void hourlyUpdate(int hour) {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(hour<7 || hour>21) {
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town_hall_f1"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_hall_f1_minotallys_room"), true);
			} else {
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town_hall_f1"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_hall_f1_minotallys_office"));
			}
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.minotallys_tf_required) && !Main.game.getCharactersPresent().contains(this)) {
			changeFlavour(Main.game.getDayOfWeek());
		}
	}
	
	public void changeFlavour(DayOfWeek dayOfTheWeek) {
		FlavourInformation flavourInfo = flavourInformationMap.get(dayOfTheWeek);

		milkFlavour = flavourInfo.getFlavour();
		coveringColour = flavourInfo.getCoveringColour();
		bodyHairColour = flavourInfo.getBodyHairColour();
		makeupColour = flavourInfo.getMakeupColour();
		clothingColour = flavourInfo.getClothingColour();
		
		setStartingBody(false);
		equipClothing();
		
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.minotallys_tf_required, false);
	}
	
	public void growCock(AbstractPenisType type) {
		this.setPenisType(type);
		this.setPenisVirgin(false);
		if(type.getRace()==Race.HUMAN) {
			this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
			this.setPenisSize(18);
			this.setTesticleSize(TesticleSize.THREE_LARGE);
			this.setPenisCumStorage(500);
		} else {
			this.setPenisGirth(PenetrationGirth.FIVE_THICK);
			this.setPenisSize(30);
			this.setTesticleSize(TesticleSize.FOUR_HUGE);
			this.setPenisCumStorage(2500);
		}
		this.fillCumToMaxStorage();
	}
	
	public void setLilinBody() {
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);

		this.setPiercedEar(true);
		AbstractClothing earrings = null;
		for(Entry<AbstractClothing, Integer> c : this.getAllClothingInInventory().entrySet()) {
			if(c.getKey().getClothingType().equals(ClothingType.getClothingTypeFromId(earringId))) {
				earrings = c.getKey();
			}
		}
		if(earrings!=null) {
			this.equipClothingFromInventory(earrings, true, this, this);
		}
		
		// Body:
		this.setSubspeciesOverride(Subspecies.LILIN);
		this.setAgeAppearanceAbsolute(25);
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.THREE_AVERAGE);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.FOUR_HUGE.getValue());
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());
		this.setLegType(LegType.DEMON_HOOFED);

		// Core:
		this.setHeight(180);
		this.setFemininity(100);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_GOLD, true, PresetColour.EYE_GOLD, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_IVORY), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_WHITE), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.STRAIGHT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_IVORY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_IVORY), false);
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.HH.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.FOUR_MASSIVE);
		this.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		this.setBreastMilkStorage(500);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.clearAssOrificeModifier();
		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addAssOrificeModifier(OrificeModifier.RIBBED);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.growCock(PenisType.DEMON_COMMON);
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.FLARED);
		this.addPenisModifier(PenetrationModifier.RIBBED);
		this.addPenisModifier(PenetrationModifier.PREHENSILE);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.clearVaginaOrificeModifiers();
		this.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addVaginaOrificeModifier(OrificeModifier.RIBBED);
		
		// Feet:
		// Foot shape
	}
	
}