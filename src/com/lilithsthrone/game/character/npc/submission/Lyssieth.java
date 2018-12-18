package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
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
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.submission.SALyssiethSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.3
 * @author Innoxia
 */
public class Lyssieth extends NPC {

	public Lyssieth() {
		this(false);
	}
	
	public Lyssieth(boolean isImported) {
		super(isImported,
				new NameTriplet("Lyssieth"), "Lilithmartuilani",
				"One of the seven elder Lilin, Lyssieth is one of the most powerful beings in existence.",
				7734, Month.OCTOBER, 13,
				1000,
				Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_THRONE_ROOM,
				true);
		
		if(!isImported) {
			this.setPlayerKnowsName(true);
			//TODO spells
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER);
			this.setStartingBody(true);
			this.equipClothing(true, true, true, true);
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
			this.setAttribute(Attribute.MAJOR_ARCANE, 100);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
			
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_LILIN);
			
			this.clearFetishes();
			
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			this.addFetish(Fetish.FETISH_INCEST);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);

			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		}
		
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceDifferenceToAppearAsAge(45);
//		this.setTailType(TailType.DEMON_COMMON);
//		this.setWingType(WingType.DEMON_COMMON);
//		this.setHornType(HornType.CURLED);

		// Core:
		this.setHeight(178);
		this.setFemininity(100);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_AUBURN), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_AUBURN), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, Colour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		// Anus settings and modifiers
		
		// Penis:
		// For if she grows one:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.FOUR_FAT);
		this.setPenisSize(12);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(250);
		this.fillCumToMaxStorage();
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
		
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		this.unequipAllClothingIntoVoid(true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_GLASSES, Colour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_torso_plunge_blouse", Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_leg_asymmetrical_skirt", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_strappy_sandals", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_groin_lacy_thong", Colour.CLOTHING_RED_DARK, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#71009E";
		}
		return "#E194FF";
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
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SALyssiethSpecials.class);
	}
	
	@Override
	public String getOrgasmDescription(OrgasmCumTarget target) {
		StringBuilder sb = new StringBuilder();
		sb.append(GenericOrgasms.getGenericOrgasmDescription(this, target));
		
		// Gaining Lyssieth's power:
		if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
			sb.append("<p>"
						+ "" //TODO
					+ "</p>");
		}
		
		return sb.toString();
	}
	
	@Override
	public void endSex() {
		this.setPenisType(PenisType.NONE);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

}