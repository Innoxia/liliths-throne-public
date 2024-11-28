package com.lilithsthrone.game.character.npc.dominion;

import java.time.LocalTime;
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
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
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
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.dominion.DaddySexActions;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.3.10
 * @version 0.3.9
 * @author Innoxia
 */
public class Daddy extends NPC {

	public static final String DADDY_RESET_TIMER_ID = "daddy_reset_timer";
	
	public Daddy() {
		this(false);
	}
	
	public Daddy(boolean isImported) {
		super(isImported, new NameTriplet("Desryth"), "Loviennemartu",
				"First encountered as an unwelcome guest being turned away from Lilaya's home, the demon [npc.name] has the lofty goal of winning the love and affection of none other than the elder Lilin, Lyssieth.",
				57, Month.JANUARY, 17,
				25, Gender.M_P_MALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
		
		if(!isImported) {
			this.setGenericName("unwelcome incubus");
			
			this.setPlayerKnowsName(false);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.10")) {
			this.removeFetish(Fetish.FETISH_IMPREGNATION);
			this.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.THREE_LIKE);
			this.addSpecialPerk(Perk.SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.BRAVE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(Perk.FETISH_SEEDER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 5),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.BRAVE);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			this.setHistory(Occupation.NPC_CONSTRUCTION_WORKER_ARCANE);
	
			this.addFetish(Fetish.FETISH_PENIS_GIVING);

			this.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);
	
			this.setFetishDesire(Fetish.FETISH_PURE_VIRGIN, FetishDesire.ONE_DISLIKE);
		}
		
		
		// Body:
		
		// (S)He's (sort of) trying to appear human:
		this.setTailType(TailType.NONE);
		this.setWingType(WingType.NONE);
		this.setHornType(HornType.NONE);
		
		this.setAgeAppearanceAbsolute(42);

		if(this.getGenderIdentity().isFeminine()) { // For if they transform into shemale:
			
			// Core:
			this.setHeight(178);
			this.setFemininity(60);
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

			// Coverings:
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_VIOLET));
			
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_LIGHT), true);

			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_WHITE), true);
			this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
			this.setHairStyle(HairStyle.WAVY);

			this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_WHITE), false);
			this.setUnderarmHair(BodyHair.ZERO_NONE);
			this.setAssHair(BodyHair.TWO_MANICURED);
			this.setPubicHair(BodyHair.ZERO_NONE);
			this.setFacialHair(BodyHair.ZERO_NONE);

//			this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//			this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//			this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//			this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//			this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
			
			// Face:
			this.setFaceVirgin(false);
			this.setLipSize(LipSize.TWO_FULL);
			this.setFaceCapacity(Capacity.SIX_STRETCHED_OPEN, true);
			// Throat settings and modifiers
			this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
			// Tongue modifiers
			
			// Chest:
			this.setNippleVirgin(true);
			this.setBreastSize(CupSize.B.getMeasurement());
			this.setBreastShape(BreastShape.ROUND);
			this.setNippleSize(NippleSize.THREE_LARGE.getValue());
			this.setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
//			this.setBreastLactationRegeneration(FluidRegeneration.TWO_FAST.getMedianRegenerationValuePerDay());
//			this.setBreastMilkStorage(500);
//			this.setBreastStoredMilk(500);
			
			// Ass:
			this.setAssVirgin(false);
			this.setAssBleached(false);
			this.setAssSize(AssSize.THREE_NORMAL.getValue());
			this.setHipSize(HipSize.THREE_GIRLY.getValue());
			this.setAssCapacity(Capacity.TWO_TIGHT, true);
			this.setAssWetness(Wetness.ZERO_DRY);
			this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
			this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
			// Anus modifiers
			
			// Penis:
			this.setPenisVirgin(false);
			this.setPenisGirth(PenetrationGirth.FIVE_THICK);
			this.setPenisSize(25);
			this.setTesticleSize(TesticleSize.FOUR_HUGE);
			this.setPenisCumStorage(250);
			this.fillCumToMaxStorage();
			this.clearPenisModifiers();
			this.setTesticleCount(2);
			
			// Vagina:
			// No vagina
			
			// Feet:
//			this.setFootStructure(FootStructure.PLANTIGRADE);
			
		} else {
			// Core:
			this.setHeight(192);
			this.setFemininity(5);
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.THREE_LARGE.getMedianValue());

			// Coverings:
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_VIOLET));
			
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_LIGHT), true);

			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_WHITE), true);
			this.setHairLength(HairLength.ONE_VERY_SHORT.getMedianValue());
			this.setHairStyle(HairStyle.STRAIGHT);

			this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_WHITE), false);
			this.setUnderarmHair(BodyHair.FOUR_NATURAL);
			this.setAssHair(BodyHair.FOUR_NATURAL);
			this.setPubicHair(BodyHair.FOUR_NATURAL);
			this.setFacialHair(BodyHair.THREE_TRIMMED);

//			this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//			this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//			this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//			this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//			this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
			
			// Face:
			this.setFaceVirgin(false);
			this.setLipSize(LipSize.ONE_AVERAGE);
			this.setFaceCapacity(Capacity.SIX_STRETCHED_OPEN, true);
			// Throat settings and modifiers
			this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
			// Tongue modifiers
			
			// Chest:
			this.setNippleVirgin(true);
			this.setBreastSize(CupSize.FLAT.getMeasurement());
			this.setBreastShape(BreastShape.SIDE_SET);
			this.setNippleSize(NippleSize.ZERO_TINY.getValue());
			this.setAreolaeSize(AreolaeSize.ZERO_TINY.getValue());
//			this.setBreastLactationRegeneration(FluidRegeneration.TWO_FAST.getMedianRegenerationValuePerDay());
//			this.setBreastMilkStorage(500);
//			this.setBreastStoredMilk(500);
			
			// Ass:
			this.setAssVirgin(false);
			this.setAssBleached(false);
			this.setAssSize(AssSize.ONE_TINY.getValue());
			this.setHipSize(HipSize.TWO_NARROW.getValue());
			this.setAssCapacity(Capacity.TWO_TIGHT, true);
			this.setAssWetness(Wetness.ZERO_DRY);
			this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
			this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
			// Anus modifiers
			
			// Penis:
			this.setPenisVirgin(false);
			this.setPenisGirth(PenetrationGirth.FIVE_THICK);
			this.setPenisSize(25);
			this.setTesticleSize(TesticleSize.FOUR_HUGE);
			this.setPenisCumStorage(250);
			this.fillCumToMaxStorage();
			this.clearPenisModifiers();
			
			// Vagina:
			// No vagina
			
			// Feet:
//			this.setFootStructure(FootStructure.PLANTIGRADE);
		}
		
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);

		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

		if(this.getGenderIdentity().isFeminine()) { 
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_sweatband", PresetColour.CLOTHING_BLACK, false), true, this);
		}
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_tshirt", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_TAN, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_BLACK_STEEL, false), true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public boolean isAddedToContacts() {
		return true;
	}
	
	// Sex:
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(DaddySexActions.class);
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_NORMAL;
	}

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_NORMAL;
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return DaddyDialogue.FIRST_ENCOUNTER;
	}
	
	/**
	 * Moves Daddy to a place in demon home, sets that place to the 'Daddy' place type, then moves Daddy inside his apartment.
	 */
	public void sendToNewHome() {
		Vector2i towerLoc = new Vector2i(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_LILITHS_TOWER).getLocation());
		towerLoc.setX(towerLoc.getX()+1);
		towerLoc.setY(towerLoc.getY()+2);
		this.setLocation(WorldType.DOMINION, towerLoc, true);
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.DOMINION_DEMON_HOME)) {
			this.setNearestLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, true);
		}
		this.getCell().setPlace(
				new GenericPlace(PlaceType.DOMINION_DEMON_HOME_DADDY) {
					@Override
					public String getName() {
						return PlaceType.DOMINION_DEMON_HOME.getName();
					}
				},
			false);
		this.setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE, true);
	}
	
	/**
	 * @return true if between 18:00 and 21:00, and a day has passed since rejecting them last.
	 */
	public static boolean isAvailable() {
		return Main.game.getHourOfDay()>=getHourAvailableStart()
				&& Main.game.getHourOfDay()<getHourAvailableEnd()
				&& Main.game.getSecondsPassed()>Main.game.getDialogueFlags().getSavedLong(DADDY_RESET_TIMER_ID)+(60*60*12);
	}
	
	public static String getAvailabilityText() {
//		return "[daddy.Name] is [style.colourBad(unavailable)] at the moment, as [daddy.sheIs] only at home between the hours of "
//				+Units.time(LocalTime.of(Daddy.getHourAvailableStart(), 00))+" and "+Units.time(LocalTime.of(Daddy.getHourAvailableEnd(), 00))
//				+"."
//				+ (Main.game.getSecondsPassed()<Main.game.getDialogueFlags().daddyResetTimer+(60*60*3)
//					?"<br/>"
//						+ "[style.colourBad([daddy.She] will also be unavailable until tomorrow.)]"
//					:"");
		
		return " [daddy.SheIsFull] only at home between the hours of "+Units.time(LocalTime.of(Daddy.getHourAvailableStart(), 00))+" and "+Units.time(LocalTime.of(Daddy.getHourAvailableEnd(), 00))
				+(Main.game.getSecondsPassed()<Main.game.getDialogueFlags().getSavedLong(DADDY_RESET_TIMER_ID)+(60*60*3)
						?". [style.colourBad(Due to recently meeting [daddy.herHim], [daddy.she] will not be available again until tomorrow.)]"
						:", and as such, [daddy.sheIs] currently "
							+(Daddy.isAvailable()
									?"[style.colourGood(available)]."
									:"[style.colourBad(not available)]."));
	}
	
	/**
	 * @return The starting hour (on a 24-hour clock) at which daddy is at home.
	 */
	public static int getHourAvailableStart() {
		return 18;
	}
	
	/**
	 * @return The ending hour (on a 24-hour clock) at which daddy is at home.
	 */
	public static int getHourAvailableEnd() {
		return 22;
	}
}
