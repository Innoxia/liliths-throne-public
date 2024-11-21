package com.lilithsthrone.game.character.npc.dominion;

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
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
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
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9.1
 * @version 0.4.9.1
 * @author Innoxia
 */
public class Fiammetta extends NPC {

	public Fiammetta() {
		this(false);
	}
	
	public Fiammetta(boolean isImported) {
		super(isImported, new NameTriplet("Fiammetta"), "Sciarra",
				"Fiammetta is a human reporter who works for Dominion's only newspaper; The Octogram Herald.",
				23, Month.JULY, 14,
				15, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(30),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				true);

	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.12")) {
			setStartingBody(false);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE);
		
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
					PersonalityTrait.KIND,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_JOURNALIST);
			
			// For the 'Lusty maiden' fetish:
			this.addFetish(Fetish.FETISH_PURE_VIRGIN);
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			
			this.setFetishDesire(Fetish.FETISH_ARMPIT_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_LEG_LOVER, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
		}
		
		// Body:
		// Core:
		this.setHeight(154);
		this.setFemininity(65);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_TANNED), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.CURLY);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ONE_STUBBLE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setFaceDepth(OrificeDepth.FOUR_DEEP.getValue());
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		this.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setAssDepth(OrificeDepth.THREE_SPACIOUS.getValue());
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setAssPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boyshorts", PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_sports_bra", PresetColour.CLOTHING_GREY, false), true, this);
		
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("TonyJC_tank_top", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_GREEN_DRAB, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("TonyJC_daisy_dukes", PresetColour.CLOTHING_BLUE_GREY, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_SILVER, false), true, this);
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
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public void turnUpdate() {
		if((Main.game.getPlayer().getWorldLocation()==WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop") || Main.game.getPlayer().getWorldLocation()==WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop_factory"))
				&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_DOLL_FACTORY, Quest.DOLL_FACTORY_2)
				&& !Main.game.isBadEnd()) {
			if(Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_fia_in_hiding")) {
				this.setLocation("innoxia_dominion_sex_shop_factory", "innoxia_dominion_sex_shop_factory_storage_crates", false);
			} else {
				this.setLocation(Main.game.getPlayer(), false);
			}
		}
	}
	
	@Override
	public SexPace getSexPaceDomPreference() {
		return SexPace.DOM_NORMAL;
	}
	
	/**
	 * Sets up Fia's friend for the start of the factory quest
	 */
	public void initFriend(GameCharacter friend) {
		friend.setBody(Gender.M_P_MALE, RacialBody.RAT_MORPH, RaceStage.GREATER, true);
		friend.setAge(37);
		friend.setHeight(182);
		friend.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		friend.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		
		friend.setSexualOrientation(SexualOrientation.GYNEPHILIC);
		
		friend.setPenisVirgin(false);
		
		friend.setEyeCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_GREY_GREEN));
		friend.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_GREY), true);
		friend.setSkinCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.BASE_GREY_DARK), false);
		friend.setSkinCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, PresetColour.COVERING_DARK_GREY), false);
		
		friend.setPenisSize(PenisLength.THREE_LARGE.getMedianValue());
		friend.setTesticleSize(TesticleSize.THREE_LARGE);
		friend.setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
		
		// Fetishes:
		friend.clearFetishes();
		friend.clearFetishDesires();
		
		friend.addFetish(Fetish.FETISH_ANAL_GIVING);
		friend.addFetish(Fetish.FETISH_ORAL_RECEIVING);
		friend.addFetish(Fetish.FETISH_BREASTS_OTHERS);

		friend.setFetishDesire(Fetish.FETISH_ARMPIT_GIVING, FetishDesire.THREE_LIKE);
		friend.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
		friend.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
		
		// Inventory:
		friend.resetInventory(true);
		AbstractClothing boxers = Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_PINK_DARK, false);
		boxers.setPattern(Pattern.getPatternIdByName("polka_dots_big"));
		boxers.setPatternColour(0, PresetColour.CLOTHING_PINK);
		boxers.setPatternColour(1, PresetColour.CLOTHING_WHITE);
		friend.equipClothingFromNowhere(boxers, true, this);

		friend.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_cargo_trousers", PresetColour.CLOTHING_BLACK, false), true, friend);
		friend.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_GREY_DARK, false), true, friend);
		friend.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, friend);

		friend.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_NAVY, false), true, friend);
		friend.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_ribbed_jumper", PresetColour.CLOTHING_GREEN_DRAB, false), true, friend);
		
		friend.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_cap", PresetColour.CLOTHING_BLUE_NAVY, false), true, friend);

		friend.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_BLACK_STEEL, false), true, friend);
		
	}
}