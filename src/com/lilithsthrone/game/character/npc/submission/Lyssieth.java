package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
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
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
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
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMLilayaDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionOrgasmOverride;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.submission.SALyssiethSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.3.5.5
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
				WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE,
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
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER, false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.5")) {
			this.setStartingBody(true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.2.1")) {
			this.setPiercedEar(true);
			AbstractClothing earrings = null;
			for(Entry<AbstractClothing, Integer> c : this.getAllClothingInInventory().entrySet()) {
				if(c.getKey().getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_piercing_ear_ring"))) {
					earrings = c.getKey();
				}
			}
			if(earrings!=null) {
				this.equipClothingFromInventory(earrings, true, this, this);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(1000);
			this.setHistory(Occupation.NPC_ELDER_LILIN);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setStartingBody(false);
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setTailGirth(PenetrationGirth.FOUR_THICK);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ELDER_LILIN);
			
			this.clearFetishes();
			
			this.addFetish(Fetish.FETISH_INCEST);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);

			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		}
		
		this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER, false);
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceDifferenceToAppearAsAge(45);
//		this.setTailType(TailType.DEMON_COMMON);
//		this.setWingType(WingType.DEMON_COMMON);
//		this.setHornType(HornType.CURLED);
		this.setTailGirth(PenetrationGirth.FOUR_THICK);

		// Core:
		this.setHeight(184);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, true, PresetColour.EYE_YELLOW, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
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
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.clearVaginaOrificeModifiers();
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.resetInventory(true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_eye_half_rim_glasses", PresetColour.CLOTHING_BROWN_VERY_DARK, PresetColour.CLOTHING_BRASS, PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_torso_plunge_blouse", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_leg_asymmetrical_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_strappy_sandals", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_RED_DARK, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_SILVER, false), true, this);
	}

	@Override
	public Colour getSpeechGlowColour() {
		if(this.getSkinType().getRace()==Race.DEMON) {
			return PresetColour.BASE_PINK_LIGHT;
		}
		return null;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#71009E";
		}
		if(this.getSkinType().getRace()==Race.DEMON) {
			return "#FF99F8";
		}
		return "#E194FF";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			this.setStartingBody(false);
		}
	}
	
	@Override
	protected Set<GameCharacter> getChildren() {
		Set<GameCharacter> children = super.getChildren();
		
		children.add(Main.game.getNpc(Lilaya.class));
		children.add(Main.game.getNpc(DarkSiren.class));
		if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
			children.add(Main.game.getPlayer());
		}
		
		return children;
	}

	@Override
	public String getArtworkFolderName() {
		if(this.getSkinType().getRace()==Race.HUMAN) {
			if(this.isVisiblyPregnant()) {
				return "LyssiethHumanPregnant";
			}
			return "LyssiethHuman";
			
		} else {
			if(this.isVisiblyPregnant()) {
				return "LyssiethDemonPregnant";
			}
			return "LyssiethDemon";
		}
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

	private void setPlayerToPartialDemon() {
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);
		Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, false, PresetColour.EYE_YELLOW, false));
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		
		Main.game.getPlayer().setTailType(TailType.DEMON_COMMON);
		Main.game.getPlayer().setTailGirth(PenetrationGirth.FOUR_THICK);
		Main.game.getPlayer().setHornType(HornType.SWEPT_BACK);
		Main.game.getPlayer().setHornLength(HornLength.ONE_SMALL.getMedianValue());
		Main.game.getPlayer().setMinimumHornsPerRow(2);
		Main.game.getPlayer().getLegConfiguration().setWingsToDemon(Main.game.getPlayer());
		Main.game.getPlayer().setEarType(EarType.DEMON_COMMON);
		Main.game.getPlayer().setEyeType(EyeType.DEMON_COMMON);
		Main.game.getPlayer().setHairType(HairType.DEMON);
	}
	
	private void setPlayerToFullDemon() {
		Main.game.getPlayer().setSkinType(SkinType.DEMON_COMMON);
		Main.game.getPlayer().setFaceType(FaceType.DEMON_COMMON);
		Main.game.getPlayer().setSubspeciesOverride(Subspecies.DEMON);
		Main.game.getPlayer().setArousal(100, true);
		if(Main.game.getPlayer().hasPenis()) {
			Main.game.getPlayer().fillCumToMaxStorage();
		}
		
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
	}
	
	public void setDaughterToFullDemon(Class<? extends NPC> daughterClass) {
		Main.game.getNpc(daughterClass).setAssType(AssType.DEMON_COMMON);
		Main.game.getNpc(daughterClass).setBreastType(BreastType.DEMON_COMMON);
		Main.game.getNpc(daughterClass).setArmType(ArmType.DEMON_COMMON);
		Main.game.getNpc(daughterClass).getLegConfiguration().setLegsToDemon(Main.game.getNpc(daughterClass));
		Main.game.getNpc(daughterClass).setSkinType(SkinType.DEMON_COMMON);
		Main.game.getNpc(daughterClass).setFaceType(FaceType.DEMON_COMMON);
		Main.game.getNpc(daughterClass).setSubspeciesOverride(Subspecies.DEMON);

		Main.game.getNpc(daughterClass).setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getNpc(daughterClass).setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getNpc(daughterClass).setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getNpc(daughterClass).setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
		Main.game.getNpc(daughterClass).setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		Main.game.getNpc(daughterClass).setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
		
		Main.game.getNpc(Lilaya.class).setArousal(100);
		Main.game.getPlayer().setArousal(100, true);
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(Main.game.getNpc(DarkSiren.class))) {
			Main.game.getNpc(DarkSiren.class).setArousal(100);
		}
		
		Main.game.getNpc(daughterClass).loadImages(true);
	}
	
	@Override
	public SexActionOrgasmOverride getSexActionOrgasmOverride(SexActionInterface sexAction, OrgasmCumTarget target, boolean applyExtraEffects) {
		if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) { // Vision scene:
			StringBuilder sb = new StringBuilder();
			sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			
			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			// Gaining Lyssieth's power:
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "SEX_ORGASM_GAINING_POWER"));
			}
			
			return new SexActionOrgasmOverride(true) {
				@Override
				public String getDescription() {
					return sb.toString();
				}
				@Override
				public void applyEffects() {
					if(applyExtraEffects) {
						Main.game.getPlayer().setArousal(50);
					}
				}
			};
			
		} else if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) { // Demon TF scene:
			StringBuilder sb = new StringBuilder();
			sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));

			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==0) {
				// Stage 1) Player is sucking Lyssieth's cock:
				if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.MOUTH)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GIVING_LYSSIETH_BLOWJOB"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().incrementFemininity(20);
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.MASCULINE.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.MASCULINE.getMaximumFemininity());
								} else if(Main.game.getPlayer().getFemininityValue()<Femininity.ANDROGYNOUS.getMaximumFemininity()){
									Main.game.getPlayer().setFemininity(Femininity.ANDROGYNOUS.getMaximumFemininity());
								}
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
	
				// Stage 1) Lyssieth is sucking player's cock:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.MOUTH, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_BLOWJOB_FROM_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								Main.game.getPlayer().setHornType(HornType.CURVED);
								Main.game.getPlayer().setMinimumHornsPerRow(2);
								if(!Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().incrementFemininity(-20);
								}
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
	
				// Stage 1) Lyssieth is eating the player out:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_1_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToPartialDemon();
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
				}
				
			} else if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1) {
				// Stage 2) Lyssieth is fucking the player:
				if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_PUSSY_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};

				// Stage 2) Lyssieth is fucking the player's ass:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_ASS_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
					};
					
				// Stage 2) The player is fucking Lyssieth:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_STAGE_2_PC_FUCKING_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
								Main.game.getPlayer().getLegConfiguration().setLegsToDemon(Main.game.getPlayer());
								Main.game.getPlayer().setArousal(100, true);
							}
						}
						@Override
						public void applyEndEffects() {
							if(applyExtraEffects) {
								Main.sex.setCreampieLockedBy(new Value<>(Main.game.getNpc(Lyssieth.class), Leg.class));
							}
						}
					};
				}
				
			} else {
				// Stage 3) The player is fucking/breeding Lyssieth:
				if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.MATING_PRESS) {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_BREEDING_LYSSIETH"));
					} else {
						sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_FUCKING_LYSSIETH"));
					}
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
						@Override
						public void applyEndEffects() {
							if(applyExtraEffects) {
								if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotLyingDown.MATING_PRESS) {
									Main.sex.setCreampieLockedBy(new Value<>(Main.game.getNpc(Lyssieth.class), Leg.class));
								} else {
									Main.sex.setCreampieLockedBy(new Value<>(Main.game.getNpc(Lyssieth.class), Tail.class));
								}
							}
						}
					};

				// Stage 3) Lyssieth is fucking the player:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_PUSSY_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};

				// Stage 3) Lyssieth is fucking the player's ass:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_ASS_FUCKED_BY_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
					
				// Stage 3) Lyssieth is sucking player's cock:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaOrifice.MOUTH, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_BLOWJOB_FROM_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
	
				// Stage 3) Lyssieth is eating the player out:
				} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.TONGUE, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
					sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "DEMON_TF_FINAL_PC_GETTING_CUNNILINGUS_FROM_LYSSIETH"));
					
					return new SexActionOrgasmOverride(false) {
						@Override
						public String getDescription() {
							return sb.toString();
						}
						@Override
						public void applyEffects() {
							if(applyExtraEffects) {
								setPlayerToFullDemon();
							}
						}
					};
				}
			}
			
		} else if(Main.sex.getSexManager() instanceof SMLilayaDemonTF) { // TF Lilaya or Meraxis into full demons
			StringBuilder sb = new StringBuilder();
			sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));

			Main.sex.addRemoveEndSexAffection(Main.game.getNpc(Lyssieth.class));
			
			// Lyssieth is fucking Lilaya's pussy:
			if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lilaya.class)).contains(SexAreaOrifice.VAGINA) && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "LILAYA_DEMON_TF_VAGINA"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(Lilaya.class);
						}
					}
				};
				
			// Lyssieth is fucking Lilaya's ass:
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(Lilaya.class)).contains(SexAreaOrifice.ANUS) && Main.game.getNpc(Lilaya.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "LILAYA_DEMON_TF_ANUS"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(Lilaya.class);
						}
					}
				};
			
			// Lyssieth is fucking Meraxis's ass:
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(DarkSiren.class)).contains(SexAreaOrifice.VAGINA) && Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "MERAXIS_DEMON_TF_VAGINA"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(DarkSiren.class);
						}
					}
				};
				
			} else if(Main.sex.getOngoingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getNpc(DarkSiren.class)).contains(SexAreaOrifice.ANUS) && Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				sb.append(UtilText.parseFromXMLFile("characters/submission/lyssieth", "MERAXIS_DEMON_TF_ANUS"));
				
				return new SexActionOrgasmOverride(false) {
					@Override
					public String getDescription() {
						return sb.toString();
					}
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							setDaughterToFullDemon(DarkSiren.class);
						}
					}
				};
			}
		}

		return super.getSexActionOrgasmOverride(sexAction, target, applyExtraEffects); // Normal scene
	}
	
	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) {
			if(Main.sex.getNumberOfOrgasms(this)==0) { // Only need to override the start, as preferences are set in the class SALyssiethSpecials after this.
				if(Main.sex.getSexPositionSlot(this)==SexSlotStanding.STANDING_DOMINANT) {
					return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
				} else {
					if(target.hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
					}
				}
			}
		}
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager() instanceof SMLyssiethDemonTF) {
			return getForeplayPreference(target);
		}
		return super.getMainSexPreference(target);
	}
	
	@Override
	public void endSex() {
//		this.setPenisType(PenisType.NONE);
	}

	@Override
	public int getOrgasmsBeforeSatisfied() {
		return 3;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	public void growCock(PenisType type) {
		this.setPenisType(type);
		this.setPenisVirgin(false);
		if(type.getRace()==Race.HUMAN) {
			this.setPenisGirth(PenetrationGirth.FOUR_THICK);
			this.setPenisSize(18);
			this.setTesticleSize(TesticleSize.THREE_LARGE);
			this.setPenisCumStorage(500);
		} else {
			this.setPenisGirth(PenetrationGirth.FIVE_FAT);
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
			if(c.getKey().getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_piercing_ear_ring"))) {
				earrings = c.getKey();
			}
		}
		if(earrings!=null) {
			this.equipClothingFromInventory(earrings, true, this, this);
		}
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceDifferenceToAppearAsAge(45);
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.FOUR_THICK);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.FOUR_HUGE.getValue());
		this.setHornType(HornType.SWEPT_BACK);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());
		this.setLegType(LegType.DEMON_COMMON);

		// Core:
		this.setHeight(196);
		this.setFemininity(100);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_YELLOW, true, PresetColour.EYE_YELLOW, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED_DARK));
//			this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
		
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
	
	public static boolean isPlayersMommy() {
		return Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST)
				&& (Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON || (Main.game.isInSex() && Main.sex.getSexManager() instanceof SMLyssiethDemonTF));
	}
	
	@Override
	public String getDirtyTalkVaginaPenetrated(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkMouthPenetrated(target, isPlayerDom);
		}
		String returnedLine = "";
		
		if(getVaginaType()!=VaginaType.NONE) {
			if(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.VAGINA) != null) {
				switch(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.VAGINA)) {
					case FINGER:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Good [npc2.girl]! Keep fingering mommy!",
								"Keep going! Mommy's pussy loves your attention!",
								"Oh yes! Mommy loves being fingered!");
						break;
					case PENIS:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Fuck me! Give mommy your cock! Don't stop!",
								"Don't stop! Harder! Keep fucking mommy! Yes, yes, yes!",
								"Oh yes! Fuck me! Mommy loves your [npc2.cock]!");
						break;
					case TAIL:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Fuck me! Give mommy your tail! Don't stop!",
								"Don't stop! Harder! Keep fucking mommy! Yes, yes, yes!",
								"Oh yes! Fuck me! Mommy loves your [npc2.tail]!");
						break;
					case TONGUE:
						returnedLine = UtilText.returnStringAtRandom(
								"Yes! Mommy loves your [npc2.tongue]! Don't stop!",
								"Don't stop! Get that tongue deep in mommy's pussy! Yes, yes, yes!",
								"Oh yes! Taste mommy's pussy! Get it deeper! Good [npc2.girl]!");
						break;
					default:
						returnedLine =  UtilText.returnStringAtRandom(
								"Fuck!",
								"Yeah!",
								"Oh yeah!");
						break;
				}
			}
		}
		
		if(returnedLine.isEmpty()) {
			return null;
		}
		
		return UtilText.parse(this, target, returnedLine);
	}

	@Override
	public String getDirtyTalkMouthPenetrated(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkMouthPenetrated(target, isPlayerDom);
		}
		String returnedLine = "";

		if(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.MOUTH) != null) {
			switch(Main.sex.getFirstOngoingSexAreaPenetration(this, SexAreaOrifice.MOUTH)) {
				case FINGER:
					returnedLine = UtilText.returnStringAtRandom(
							"Mommy loves the taste of your [npc2.fingers]! Don't stop!",
							"Keep going! Mommy loves sucking on your [npc2.fingers]!",
							"Oh yes! Mommy loves the taste of your [npc2.fingers]!");
					break;
				case PENIS:
					returnedLine = UtilText.returnStringAtRandom(
							"Oh yes! Mommy loves your [npc2.cock]! Fuck her face like a good [npc2.girl]!",
							"Don't stop! Harder! Fuck mommy's throat! Yes, yes, yes!",
							"Oh yes! Mommy loves your [npc2.cock]! You taste so good!");
					break;
				case TAIL:
					returnedLine = UtilText.returnStringAtRandom(
							"Oh yes! Mommy loves your [npc2.tail]! Fuck her face like a good [npc2.girl]!",
							"Don't stop! Harder! Fuck mommy's throat! Yes, yes, yes!",
							"Oh yes! Mommy loves your [npc2.tail]! You taste so good!");
					break;
				case TONGUE:
					returnedLine = UtilText.returnStringAtRandom(
							"Good [npc2.girl]! Show mommy how much you love her!",
							"Yes! Good [npc2.girl]!",
							"Good [npc2.girl]! Mommy loves your [npc2.lips]!");
					break;
				default:
					returnedLine = UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!");
					break;
			}
		}

		if(returnedLine.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, returnedLine);
	}

	@Override
	public String getDirtyTalkPenisPenetrating(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkPenisPenetrating(target, isPlayerDom);
		}
		
		List<String> availableLines = new ArrayList<>();
		
		if(!Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target).isEmpty()) {
			for(SexAreaOrifice orifice : Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target)) {
				switch(orifice) {
					case ANUS:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Mommy loves your ass! Take her cock like a good [npc2.girl]!",
								"Oh yes, let mommy fuck your cute little butt! Yes, yes, yes!",
								"Oh yes! Good [npc2.girl]! Mommy loves your ass!"));
						break;
					case ASS:
						break;
					case BREAST_CROTCH:
						break;
					case BREAST:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Mommy loves your [npc2.breasts]! Service her cock like a good [npc2.girl]!",
								"Oh yes, let mommy fuck your tits! Yes, yes, yes!",
								"Oh yes! Good [npc2.girl]! Mommy loves your tits!"));
						break;
					case MOUTH:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Keep sucking mommy's cock, like a good [npc2.girl]! Just like that!",
								"Oh yes! Wrap those lips of yours around mommy's cock! Keep going!",
								"Keep sucking mommy's cock! Yes! Good [npc2girl]!"));
						break;
					case NIPPLE:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Take mommy's cock! Good [npc2.girl]!",
								"Mommy loves fucking your tits! Take my cock like a good [npc2.girl]!",
								"Oh yes! Take mommy's cock! Your tits feel so good to fuck!"));
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Take mommy's cock! Your pussy belongs to me!",
								"Good [npc2.girl]! Take mommy's cock deep in your little pussy!",
								"Oh yes! Let mommy fill your pussy with her cock! Good [npc2.girl]!"));
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}

	@Override
	public String getDirtyTalkTonguePenetrating(GameCharacter target, boolean isPlayerDom){
		if(!isPlayersMommy()) {
			return super.getDirtyTalkTonguePenetrating(target, isPlayerDom);
		}
		List<String> availableLines = new ArrayList<>();
		
		if(!Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TONGUE, target).isEmpty()) {
			for(SexAreaOrifice orifice : Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.TONGUE, target)) {
				switch(orifice) {
					case ANUS:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Mommy loves your ass! Feel my [npc1.tongue] pushing deep!",
								"Oh yes! Let mommy lick your ass! Yes, yes, yes!",
								"Oh yes! I love licking ass! Let mommy get her [npc1.tongue] nice and deep!"));
						break;
					case ASS:
						break;
					case BREAST_CROTCH:
						break;
					case BREAST:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Mommy loves your tits! Let her suck on your nipples!",
								"Oh yes! Let mommy suck on your nipples! Yes, yes, yes!",
								"Oh yes! Mommy loves your tits!"));
						break;
					case MOUTH:
						availableLines.add(UtilText.returnStringAtRandom(
								"Oh yes! Your lips taste so good! Mommy needs more!",
								"Mommy loves kissing you! Yes, yes, yes!",
								"Oh yes! Your lips taste so good!"));
						break;
					case NIPPLE:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Mommy loves your tits! Let her suck on your nipples!",
								"Oh yes! Let mommy suck on your nipples! Yes, yes, yes!",
								"Oh yes! Mommy loves your nipples! Let her get her [npc1.tongue] nice and deep!"));
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						availableLines.add(UtilText.returnStringAtRandom(
								"Yes! Mommy loves servicing your pussy! Good [npc2.girl]!",
								"Oh yes! Let mommy eat you out! Yes, yes, yes!",
								"Oh yes! Mommy loves the taste of your pussy! Feel her [npc1.tongue] getting in nice and deep!"));
						break;
				}
			}
		}

		if(availableLines.isEmpty()) {
			return null;
		}
		return UtilText.parse(this, target, availableLines.get(Util.random.nextInt(availableLines.size())));
	}
}