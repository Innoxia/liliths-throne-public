package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
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
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallDemographics;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaOral;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public class Vanessa extends NPC {
	
	public Vanessa() {
		this(false);
	}
	
	public Vanessa(boolean isImported) {
		super(isImported, new NameTriplet("Vanessa"), "Cunningham",
				"Ms. Cunningham, as she prefers to be called, is the sole person responsible for all of the affairs of Dominion's 'Bureau of Demographics'."
						+ " Although getting on years, she's aged remarkably well, and has a refined, elegant beauty about her.",
				55, Month.SEPTEMBER, 26,
				10, Gender.F_V_B_FEMALE, Subspecies.FOX_MORPH, RaceStage.PARTIAL,
				new CharacterInventory(10), WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES, true);
		
		if(!isImported) {
			this.setPlayerKnowsName(false);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 5);
			this.setAttribute(Attribute.MAJOR_ARCANE, 8);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 25);
			
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			this.setHistory(Occupation.NPC_OFFICE_WORKER);
			
			this.addFetish(Fetish.FETISH_FOOT_GIVING);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
		
		
		// Body:

		// Core:
		this.setHeight(177);
		this.setFemininity(90);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_BLUE));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FOX_MORPH, Colour.EYE_BLUE));
		this.setSkinCovering(new Covering(BodyCoveringType.FOX_FUR, Colour.COVERING_GREY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_GREY), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FOX_FUR, Colour.COVERING_GREY), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.BRAIDED);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, Colour.COVERING_GREY), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, Colour.COVERING_GREY), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE.getValue());
		this.setAreolaeSize(AreolaeSize.THREE_LARGE.getValue());
//		this.addNippleOrificeModifier(OrificeModifier.PUFFY);
//		this.setBreastLactationRegeneration(FluidRegeneration.TWO_FAST.getMedianRegenerationValuePerDay());
//		this.setBreastMilkStorage(500);
//		this.setBreastStoredMilk(500);
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL.getValue());
		this.setHipSize(HipSize.FOUR_WOMANLY.getValue());
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		this.setGirlcumFlavour(FluidFlavour.HONEY);
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);

		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_PENCIL_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_torso_feminine_short_sleeve_shirt", Colour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_OPEN_CARDIGAN, Colour.CLOTHING_GREY, false), true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_pantyhose", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_flats", Colour.CLOTHING_BLACK, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_eye_half_moon_glasses", Colour.CLOTHING_STEEL, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_BLACK, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_piercing_ear_pearl_studs", Colour.CLOTHING_WHITE, Colour.CLOTHING_GOLD, null, false), true, this);

	}

	@Override
	public String getSpeechColour() {
		return "#E7CAE6";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getGenericName() {
		return "Ms. Cunningham";
	}
	
	@Override
	public void dailyReset() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyHelped, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, false);
	}
	
	// Sex:

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Sex.getSexPositionSlot(Main.game.getNpc(Vanessa.class))==SexSlotBipeds.CHAIR_ORAL_SITTING) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
		}
		if(Sex.getSexPositionSlot(Main.game.getNpc(Vanessa.class))==SexSlotBipeds.CHAIR_KNEELING) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
		}
		if(Sex.getSexPositionSlot(Main.game.getNpc(Vanessa.class))==SexSlotBipeds.MISSIONARY_DESK_SUB) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}
		
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		return getForeplayPreference(target);
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_GENTLE;
	}
	
	@Override
	public void endSex() {
		if(!(Sex.getSexManager() instanceof SMVanessaOral) || !Sex.isDom(Main.game.getNpc(Vanessa.class))) {
			Main.game.getNpc(Vanessa.class).cleanAllDirtySlots();
			Main.game.getNpc(Vanessa.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE;
	}

	
}
