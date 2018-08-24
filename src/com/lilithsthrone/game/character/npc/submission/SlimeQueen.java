package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
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
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
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
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.2.11
 * @author Innoxia
 */
public class SlimeQueen extends NPC {

	public SlimeQueen() {
		this(false);
	}

	public SlimeQueen(boolean isImported) {
		super(isImported, new NameTriplet("Catherine"),
				"The self-proclaimed Slime Queen, Catherine, is a gigantic female slime, who wants to turn as many people into her subjects as possible.",
				38, Month.JANUARY, 29,
				15, Gender.F_V_B_FEMALE, Subspecies.SLIME, RaceStage.HUMAN,
				new CharacterInventory(10),
				WorldType.SLIME_QUEENS_LAIR_FIRST_FLOOR, PlaceType.SLIME_QUEENS_LAIR_SLIME_QUEEN, true);

	}
	

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 60);
			this.setAttribute(Attribute.MAJOR_ARCANE, 20);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 40);
	
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_SLIME_QUEEN);
	
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			this.addFetish(Fetish.FETISH_NON_CON_SUB);
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
	
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DEFLOWERING, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_DENIAL_SELF, FetishDesire.ZERO_HATE);
		}
		
		
		// Body:

		// Core:
		this.setHeight(Height.SEVEN_COLOSSAL.getMaximumValue());
		this.setFemininity(90);
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME, Colour.SLIME_PINK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_EYE, Colour.SLIME_PINK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_SCLERA, Colour.SLIME_PINK_LIGHT), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_PUPILS, Colour.SLIME_PINK_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_ANUS, CoveringPattern.ORIFICE_ANUS, Colour.SLIME_PINK_DARK, false, Colour.SLIME_PINK_DARK, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_HAIR, Colour.SLIME_PINK_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_MOUTH, CoveringPattern.ORIFICE_MOUTH, Colour.SLIME_PINK_DARK, false, Colour.SLIME_PINK_DARK, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_NIPPLES, Colour.SLIME_PINK_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_VAGINA, CoveringPattern.ORIFICE_VAGINA, Colour.SLIME_PINK_DARK, false, Colour.SLIME_PINK_DARK, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_PENIS, CoveringPattern.NONE, Colour.SLIME_PINK_DARK, false, Colour.SLIME_PINK_DARK, true), false);
		
		this.setHairLength(HairLength.FOUR_MID_BACK.getMaximumValue());
		this.setHairStyle(HairStyle.HIME_CUT);

		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP.getValue());
		this.setFaceElasticity(OrificeElasticity.SEVEN_ELASTIC);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.JJ.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.TWO_BIG.getValue());
		this.setAreolaeSize(AreolaeSize.THREE_LARGE.getValue());
		this.setNippleCapacity(Capacity.TWO_TIGHT.getMedianValue(), true);
		this.setNippleElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setNipplePlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		
		this.setBreastLactationRegeneration(FluidRegeneration.ONE_AVERAGE.getValue());
		this.setMilkFlavour(FluidFlavour.STRAWBERRY);
		this.setBreastMilkStorage(Lactation.FIVE_VERY_LARGE_DROOLING.getMedianValue());
		this.setBreastStoredMilk(Lactation.FIVE_VERY_LARGE_DROOLING.getMedianValue());
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setHipSize(HipSize.FIVE_VERY_WIDE.getValue());
		this.setAssSize(AssSize.FIVE_HUGE.getValue());
		this.setAssCapacity(Capacity.FIVE_ROOMY.getMedianValue(), true);
		this.setAssWetness(Wetness.SEVEN_DROOLING);
		this.setAssElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.FOUR_FAT.getValue());
		this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
		this.setPenisCumStorage(CumProduction.FOUR_LARGE.getMedianValue());
		this.fillCumToMaxStorage();
		this.setCumFlavour(FluidFlavour.STRAWBERRY);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		this.setGirlcumFlavour(FluidFlavour.STRAWBERRY);
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos) {

		this.unequipAllClothingIntoVoid(true);
		
		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN) && Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FIVE_CONVINCE)) {
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_SLIME_QUEENS_TIARA, false), true, this);
		}
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_HEART_NECKLACE, Colour.CLOTHING_GOLD, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_HOOPS, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false), true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", null); //TODO
		} else {
			return new Response ("", "", null); //TODO
		}
	}
	
}
