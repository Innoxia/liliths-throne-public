package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.FortressMales;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class FortressMalesLeader extends NPC {

	public FortressMalesLeader() {
		this(false);
	}
	
	public FortressMalesLeader(boolean isImported) {
		super(isImported, null,
				"The ruler of one of Submission's imp fortresses, [npc.name] has a following consisting entirely of male imps...",
				Util.random.nextInt(15)+25, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				20, Gender.M_P_MALE, Subspecies.IMP_ALPHA, RaceStage.GREATER, new CharacterInventory(10), WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 30);
			this.setAttribute(Attribute.MAJOR_ARCANE, 20);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
			
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MUGGER);
			
			this.addFetish(Fetish.FETISH_PENIS_GIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_CUM_STUD);
		}
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(25);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.DEMON_COMMON);
		this.setLegType(LegType.DEMON_HOOFED);
		this.setHornType(HornType.STRAIGHT);

		// Core:
//		this.setHeight(174);
//		this.setFemininity(80);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:

		// Allow to be randomised:
//		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_PURPLE));
//		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_IVORY), true);
//		
//		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.COVERING_WHITE), false);
//
//		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
//		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
//		this.setHairStyle(HairStyle.HIME_CUT);
//		
//		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_BLACK), false);
//		this.setUnderarmHair(BodyHair.ZERO_NONE);
//		this.setAssHair(BodyHair.ZERO_NONE);
//		this.setPubicHair(BodyHair.ZERO_NONE);
//		this.setFacialHair(BodyHair.ZERO_NONE);
//
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_PURPLE));
//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_PURPLE));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.FOUR_FAT);
		this.setPenisSize(30);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(300);
		this.fillCumToMaxStorage();
		
		// Vagina:
//		this.setVaginaVirgin(false);
//		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
//		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
//		this.setVaginaSquirter(true);
//		this.setVaginaCapacity(Capacity.SIX_STRETCHED_OPEN, true);
//		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
//		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
//		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos) {
		
		this.unequipAllClothingIntoVoid(true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.STOMACH_SARASHI, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HAND_WRAPS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_CARGO_TROUSERS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_BROWN, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos] days of ruling over [npc.her] imp fortress are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else {
			return (UtilText.parse(this, description));
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	// Combat:
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", FortressMales.KEEP_AFTER_COMBAT_VICTORY);
		} else {
			return new Response("", "", FortressMales.KEEP_AFTER_COMBAT_DEFEAT);
		}
	}

}