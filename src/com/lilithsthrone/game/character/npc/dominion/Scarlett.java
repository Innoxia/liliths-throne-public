package com.lilithsthrone.game.character.npc.dominion;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
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
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
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
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.dominion.SAScarlett;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.3.7
 * @author Innoxia
 */
public class Scarlett extends NPC {

	public Scarlett() {
		this(false);
	}
	
	public Scarlett(boolean isImported) {
		super(isImported, new NameTriplet("Scarlett"), "Kardos",
				"Scarlett is the owner of the rather unoriginally named establishment 'Scarlett's shop'."
						+ " Rude, loud, and quick to anger, Scarlett isn't a very pleasant person to have to deal with.",
				23, Month.NOVEMBER, 14,
				5, Gender.M_P_MALE, Subspecies.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
		
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(!this.isSlave() && Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.LEWD,
					PersonalityTrait.SELFISH);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.9") && !this.isSlave()) {
			this.equipClothing();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.3")) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN)) {
				this.setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.6") && !this.isSlave()) {
			this.getPetNameMap().remove(Main.game.getPlayer().getId());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.20")) {
			this.setSkinCovering(new Covering(BodyCoveringType.HARPY_SKIN, PresetColour.SKIN_EBONY), false);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		if(this.isSlave() && this.getOwner().isPlayer()) {
			super.setupPerks(autoSelectPerks);
			
		} else {
			PerkManager.initialisePerks(this,
					Util.newArrayListOfValues(),
					Util.newHashMapOfValues(
							new Value<>(PerkCategory.PHYSICAL, 5),
							new Value<>(PerkCategory.LUST, 1),
							new Value<>(PerkCategory.ARCANE, 0)));
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.LEWD,
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_HARPY_FLOCK_MEMBER);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);

			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
		}
		
		// Body:

		// Core:
		this.setHeight(155);
		this.setFemininity(75);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, PresetColour.EYE_RED));
		this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, PresetColour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HARPY_SKIN, PresetColour.SKIN_EBONY), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.TWO_SHORT);
		this.setHairStyle(HairStyle.LOOSE);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HARPY, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_BLACK));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_BLACK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_BLACK));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.AA.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setAssPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisSize(8);
		this.setTesticleSize(TesticleSize.ZERO_VESTIGIAL);
		this.setPenisCumStorage(2);
		this.fillCumToMaxStorage();
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boyshorts", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.CHEST_CROPTOP_BRA, PresetColour.CLOTHING_BLACK, false), true, this);
		
		if(Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)
				&& (this.getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP || this.hasVagina())) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_SKATER_DRESS, PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_velvet_choker", PresetColour.CLOTHING_BLACK, false), true, this);
			
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_SLEEVELESS_TURTLENECK, PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
		}
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_SILVER, false), true, this);
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
	public boolean isImmuneToLevelDrain() {
		return !this.isSlave();
	}

	@Override
	public boolean isAbleToBeSold() {
		return Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN;
	}
	
	@Override
	public String getDescription() {
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN)) {
			return UtilText.parse(this,
					"Scarlett is currently employed in the very same establishment that she used to own as Helena's personal assistant."
						+ " Showing respect only to her matriarch, she's extremely rude and obnoxious towards everyone else.");
		}
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
			if(this.getLocationPlace().getPlaceType()==PlaceType.GENERIC_EMPTY_TILE) {
				return UtilText.parse(this,
						"Once the owner of a shop in Slaver Alley, Scarlett is now a slave [npc.herself]."
								+ " Having been sold by you, there's no knowing where [npc.she] is or what [npc.sheIs] doing...");
				
			} else if(this.isSlave()) {
				return UtilText.parse(this,
						"Once the owner of a shop in Slaver Alley, Scarlett is now a slave [npc.herself]."
								+ " Rude, loud, and quick to anger, [npc.she] isn't a very pleasant person to have to deal with.");
				
			} else {
				return UtilText.parse(this,
						"Once the owner of a shop in Slaver Alley, Scarlett was enslaved by [helena.name], before then being set free by you."
								+ " Rude, loud, and quick to anger, [npc.she] isn't a very pleasant person to have to deal with.");
			}
			
		} else {
			return UtilText.parse(this,
					"Scarlett is the owner of the rather unoriginally named establishment, 'Scarlett's shop'."
							+ " Rude, loud, and quick to anger, [npc.she] isn't a very pleasant person to have to deal with.");
		}
	}
	
	@Override
	public String getSpeechColour() {
		if(this.hasVagina()) {
			if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
				return "#fa2ca7";
			} else {
				return "#ff94d6";
			}
		}
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#FA0060";
		} else {
			return "#FF94BD";
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public String getVirginityLossDescription(SexType sexType) {
		if(this.getVirginityLoss(sexType)!=null
				&& sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS))
				&& this.getVirginityLoss(sexType).getKey().isEmpty()) {
			return UtilText.parse(this, "[npc.Name] lost [npc.her] virginity to one of [npc.her] harpy friends up in Helena's nest.");
		}
		return super.getVirginityLossDescription(sexType);
	}
	
	@Override
	public void dailyUpdate() {
		if(!this.isSlave() && this.hasVagina() && !Main.game.getCharactersPresent().contains(this)) { // Female Scarlett:
			if(this.getMinutesSinceLastTimeHadSex()>(60*22) || this.isVaginaVirgin()) {// Scarlett has sex with one of her followers every night (if she is horny or is a virgin):
				if(this.isLikesPlayer() || Math.random()<0.8f) { // If Scarlett likes the player, she won't let anyone else get her pregnant. Also 80% chance for her to force her followers to pull out or use a condom.
					this.calculateGenericSexEffects(
							true, true, null, Subspecies.HARPY, Subspecies.HARPY, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED, GenericSexFlag.PREVENT_CREAMPIE);
				} else {
					this.calculateGenericSexEffects(
							true, true, null, Subspecies.HARPY, Subspecies.HARPY, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
				}
			}
			this.applyMakeup();
		}
		if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA) && this.getAffection(Main.game.getNpc(Helena.class))<0) {
			this.incrementAffection(Main.game.getNpc(Helena.class), 5);
		}
	}
	
	private void goHome() {
		if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
			this.returnToHome();
		} else {
			this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
		}
	}
	
	@Override
	public void turnUpdate() {
		if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_1_F_SCARLETTS_FATE) { // Scarlett needs to be at the shop for the player to discover.
			this.returnToHome();
			return;
		}
		if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_1_G_SLAVERY) {
			Main.game.getNpc(Helena.class).turnUpdate();
			this.setLocation(Main.game.getNpc(Helena.class), false);
			return;
		}
		if(this.isSlave() || Main.game.getCharactersPresent().contains(this) || this.getLocationPlace().getPlaceType()==PlaceType.GENERIC_EMPTY_TILE) {
			return; // Do not move if a slave, if the player is present in her tile, or if banished to the empty tile.
		}

		if(this.getHomeLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) { // If her home tile is her shop, then she works and disappears home.
			if(Main.game.isExtendedWorkTime()) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
			return;
		}

		boolean nestHours = Main.game.isDayTime() || (Main.game.getHourOfDay()>9 && Main.game.getHourOfDay()<21);
		
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettToldToReturn)) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.scarlettGoneHome)) {
				goHome(); // If Helena is home on special business, Scarlett is with her.
				
			} else if((Main.game.isWorkTime() || (Main.game.getDateNow().getDayOfWeek()==DayOfWeek.FRIDAY && Main.game.getHourOfDay()>12 && Main.game.getHourOfDay()<21))
					&& !Main.game.isWeekend()) {
				this.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false); // If Scarlett is working for Helena, she goes to the shop during work hours.
				this.equipClothing();
				
			} else if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
						&& nestHours
						&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN // Once Scarlett goes to meet Helena, she stays with her until player progresses to next stage of quest
						&& (Main.game.isWeekend() || Main.game.getHourOfDay()>12)) { // Do not appear in nest before work
				this.setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, false);
				this.equipClothing();
				
			} else {
				goHome();
			}
			
		} else {
			if(nestHours && Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				this.setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, false);
				
			} else {
				goHome();
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	public void resetName() {
		this.setName(new NameTriplet("Scarlett"));
		this.setSurname("Kardos");
	}
	
	public void completeBodyReset() {
		boolean analVirgin = this.isAnalVirgin();
		this.setBody(Gender.M_P_MALE, RacialBody.HARPY, RaceStage.LESSER, false);
		this.setStartingBody(true);
		this.setAnalVirgin(analVirgin);
		this.endPregnancy(true);
		this.equipClothing();
		this.getPetNameMap().remove(Main.game.getPlayer().getId());
	}
	
	public void applyFeminisation() {
		this.setFemininity(85);

		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH);
		
		this.setLipSize(LipSize.THREE_PLUMP);
		
		this.setBreastSize(CupSize.B);
		this.setNippleSize(NippleSize.THREE_LARGE);
		
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);

		this.setPenisType(PenisType.NONE);
		
		this.setVaginaType(VaginaType.HARPY);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaSquirter(true);
	}

	public void applyMakeup() {
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_NONE));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_NONE));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE_LIGHT));
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SAScarlett.class);
	}
	
	public boolean isLikesPlayer() {
		return this.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE;
	}
	
}