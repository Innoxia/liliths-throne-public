package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
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
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
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
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public class Elizabeth extends NPC {

	public Elizabeth() {
		this(false);
	}
	
	public Elizabeth(boolean isImported) {
		super(isImported,
				new NameTriplet("Elizabeth"), "Lyssiethmartu",
				"An unrecognised daughter of Lyssieth, Elizabeth is captain of her mother's royal guard. She is tasked with protecting the entrance to Lyssieth's palace.",
				23, Month.JUNE, 22,
				25, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.PARTIAL_FULL, new CharacterInventory(10), WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE, true);

		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.setEssenceCount(TFEssence.ARCANE, 100);
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		this.setDescription("An unrecognised daughter of Lyssieth, Elizabeth is captain of her mother's royal guard. She is tasked with protecting the entrance to Lyssieth's palace.");
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 50);
			this.setAttribute(Attribute.MAJOR_ARCANE, 30);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
			
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.LOW)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MUGGER);
			
			this.clearFetishes();
			
			this.addFetish(Fetish.FETISH_STRUTTER);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ONE_DISLIKE);
		}
		
		
		// Body:
		this.setSubspeciesOverride(Subspecies.HALF_DEMON);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.DEMON_COMMON);
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(HornLength.ZERO_TINY.getMedianValue());

		// Core:
		this.setHeight(174);
		this.setFemininity(80);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_LILAC), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_DIRTY_BLONDE), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_DIRTY_BLONDE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.TWO_MANICURED);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_CLEAR));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.DD.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		// n/a
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_trainer_socks", Colour.CLOTHING_BLACK, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_torso_feminine_short_sleeve_shirt", Colour.CLOTHING_GREEN_DRAB, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_TIE, Colour.CLOTHING_GREEN_DRAB, false), true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_lyssiethUniform_hat", false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_lyssiethUniform_shoes", false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_lyssiethUniform_skirt", false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_lyssiethUniform_tunic", false), true, this);
		
		this.setPiercedEar(true);
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_revolver_revolver"), DamageType.PHYSICAL));
		}
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
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	public Attack attackType() {
		return Attack.MAIN;
	}
	
}