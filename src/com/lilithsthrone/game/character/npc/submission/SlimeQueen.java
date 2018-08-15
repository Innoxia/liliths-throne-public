package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
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
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
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
 * @version 0.2.6
 * @author Innoxia
 */
public class SlimeQueen extends NPC {

	public SlimeQueen() {
		this(false);
	}

	public SlimeQueen(boolean isImported) {
		super(new NameTriplet("Catherine"),
				"The self-proclaimed Slime Queen, Catherine, is a gigantic female slime, who wants to turn as many people into her subjects as possible.",
				38, Month.JANUARY, 29,
				15, Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10),
				WorldType.SLIME_QUEENS_LAIR_FIRST_FLOOR, PlaceType.SLIME_QUEENS_LAIR_SLIME_QUEEN, true);

		if(!isImported) {
			
			// RACE & NAME:
			
			setBody(Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN);
			
			this.setBodyMaterial(BodyMaterial.SLIME);
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);

			this.setPlayerKnowsName(true);
			
			// PERSONALITY & BACKGROUND:

			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
			
			// ADDING FETISHES:
			
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
			
			// BODY RANDOMISATION:
			
			this.setHeight(Height.SEVEN_COLOSSAL.getMaximumValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME, Colour.SLIME_PINK), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_EYE, Colour.SLIME_PINK_LIGHT), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_SCLERA, Colour.SLIME_PINK_LIGHT), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_PUPILS, Colour.SLIME_PINK), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_ANUS, CoveringPattern.ORIFICE_ANUS, Colour.SLIME_PINK_DARK, false, Colour.SLIME_PINK_DARK, true), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_HAIR, Colour.SLIME_PINK_DARK), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_MOUTH, CoveringPattern.ORIFICE_MOUTH, Colour.SLIME_PINK_DARK, false, Colour.SLIME_PINK_DARK, true), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_NIPPLES, Colour.SLIME_PINK_DARK), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_VAGINA, CoveringPattern.ORIFICE_VAGINA, Colour.SLIME_PINK_DARK, false, Colour.SLIME_PINK_DARK, true), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_PENIS, CoveringPattern.NONE, Colour.SLIME_PINK_DARK, false, Colour.SLIME_PINK_DARK, true), false);
			
			this.setHairLength(HairLength.FOUR_MID_BACK.getMaximumValue());
			this.setHairStyle(HairStyle.HIME_CUT);
			
			this.setLipSize(LipSize.THREE_PLUMP.getValue());
			
			this.setFaceVirgin(false);
			this.setAssVirgin(false);
			this.setVaginaVirgin(false);
			this.setPenisVirgin(false);
			
			this.setBreastSize(CupSize.JJ.getMeasurement());
			this.setBreastShape(BreastShape.ROUND);
			this.setAreolaeSize(AreolaeSize.THREE_LARGE.getValue());
			this.setNippleSize(NippleSize.TWO_BIG.getValue());
			this.setNippleCapacity(Capacity.TWO_TIGHT.getMedianValue(), true);
			this.setBreastLactationRegeneration(FluidRegeneration.ONE_AVERAGE.getValue());
			this.setMilkFlavour(FluidFlavour.STRAWBERRY);
			this.setBreastMilkStorage(Lactation.FIVE_VERY_LARGE_DROOLING.getMedianValue());
			this.setBreastStoredMilk(Lactation.FIVE_VERY_LARGE_DROOLING.getMedianValue());
			
			this.setHipSize(HipSize.FIVE_VERY_WIDE.getValue());
			this.setAssSize(AssSize.FIVE_HUGE.getValue());
			this.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(), true);
			
			this.setVaginaCapacity(Capacity.FIVE_ROOMY.getMedianValue(), true);
			this.setGirlcumFlavour(FluidFlavour.STRAWBERRY);
			this.setVaginaSquirter(true);
			this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE.getValue());
			
			this.setCumFlavour(FluidFlavour.STRAWBERRY);
			
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			
			equipClothing(true, false);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.equipClothing(true, false);
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE.getValue());
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		this.unequipAllClothingIntoVoid();
		
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
