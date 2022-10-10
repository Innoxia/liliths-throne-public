package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
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
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
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
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.sexActions.dominion.SALilayaSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.4.1
 * @author Innoxia
 */
public class Lilaya extends NPC {
	
	public Lilaya() {
		this(false);
	}
	
	public Lilaya(boolean isImported) {
		super(isImported, new NameTriplet("Lilaya"), "Lyssiethmartusarri",
				"Along with your twin cousins, your aunt Lily was the only family you'd ever known."
						+ " Although she still exists in this world, she isn't your aunt any more, and in this reality, she's a half-demon called 'Lilaya'."
						+ " Whereas your old aunt was a researcher at the city museum, Lilaya is a privately-funded researcher of the arcane."
						+ " Due to her demonic appearance and the fact that she's the daughter of the Lilin Lyssieth, people usually regard Lilaya with a mixture of fear and respect.",
				48, Month.DECEMBER, 28,
				25,
				Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.PARTIAL_FULL,
				new CharacterInventory(10),
				WorldType.LILAYAS_HOUSE_FIRST_FLOOR,
				PlaceType.LILAYA_HOME_ROOM_LILAYA,
				true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12")) {
			this.setAgeAppearanceDifferenceToAppearAsAge(32);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.setStartingBody(true);
			this.setLegType(LegType.HUMAN);
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.6")) {
			this.setWingSize(WingSize.THREE_LARGE.getValue());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(25);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.10")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4")) {
			this.equipClothing();
			if(this.getSubspecies()!=Subspecies.DEMON) {
				setupCoverings(this.getCovering(BodyCoveringType.HUMAN).getPrimaryColour());
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4.9")) {
			if(this.getSubspecies()==Subspecies.DEMON) {
				this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.SHY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7")) {
			this.setTailGirth(PenetrationGirth.FIVE_THICK);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.20")) {
			this.setHomeLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_LILAYA);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 5)));
	}
	
	private void setupCoverings(Colour humanSkinColour) {
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);

		this.setAssType(AssType.DEMON_COMMON);
		this.setBreastType(BreastType.DEMON_COMMON);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, humanSkinColour), true);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, humanSkinColour), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, humanSkinColour), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, humanSkinColour), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, humanSkinColour), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, humanSkinColour), false);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.LOOSE);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.SHY);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ARCANE_RESEARCHER);
	
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ZERO_HATE);
		}
		
		// Body:

		// Core:
		this.setSubspeciesOverride(Subspecies.HALF_DEMON);
		this.setAgeAppearanceDifferenceToAppearAsAge(32);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.THREE_LARGE.getValue());
		this.setHornType(HornType.SWEPT_BACK);
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.FIVE_THICK);

		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		setupCoverings(Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour());

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_AMBER));
//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_AMBER));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
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
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		// For when she grows one:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(15);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(65);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		this.setHairStyle(HairStyle.LOOSE);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.CHEST_FULLCUP_BRA, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_pencil_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_torso_feminine_short_sleeve_shirt"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_GREY, false), true, this);
		
		AbstractClothing labCoat = Main.game.getItemGen().generateClothing("innoxia_scientist_lab_coat", PresetColour.CLOTHING_WHITE, false);
		this.equipClothingFromNowhere(labCoat, true, this);
		this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_OVER), DisplacementType.UNBUTTONS, true, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.setPiercedEar(true);
	}
	
	public void applyGeishaChange() {
		Main.game.getNpc(Lilaya.class).resetInventory(false);
		this.setHairStyle(HairStyle.LOOSE);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_japanese_kanzashi", PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PURPLE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_japanese_kimono", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PURPLE, PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_japanese_geta", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PINK, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
	}
	
	public void applyDinnerDateChange() {
		Main.game.getNpc(Lilaya.class).resetInventory(false);
		this.setHairStyle(HairStyle.PONYTAIL);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_PLUNGE_DRESS, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_pearl_studs", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK_STEEL, null, false), true, this);
		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_RED_VERY_DARK, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);
		
	}

	public boolean isCondomBroke() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaCondomBroke);
	}

	public boolean isAmazonsSecretImpregnation() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaAmazonsSecretImpregnation);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	// Prevent issues with Geisha Lilaya immediately backing out of submissive sex:
	@Override
	public boolean isAttractedTo(GameCharacter character) {
		return true;
	}

	@Override
	public String getArtworkFolderName() {
		if(this.getRaceStage()==RaceStage.GREATER) {
			return "LilayaDemon";
		}
		
		Colour skinColour = this.getCovering(BodyCoveringType.HUMAN).getPrimaryColour();
		if(skinColour==PresetColour.SKIN_PORCELAIN
				|| skinColour==PresetColour.SKIN_PALE) {
			return "LilayaPale";
			
		} else if(skinColour==PresetColour.SKIN_TANNED
				|| skinColour==PresetColour.SKIN_OLIVE) {
			return "LilayaOlive";
			
		} else if(skinColour==PresetColour.SKIN_CHOCOLATE
				|| skinColour==PresetColour.SKIN_DARK) {
			return "LilayaDark";
			
		} else if(skinColour==PresetColour.SKIN_EBONY) {
			return "LilayaEbony";
		}
		return "LilayaLight";
	}

	@Override
	public String setSkinCovering(Covering covering, boolean updateAllSkinColours) {
		String returnValue = super.setSkinCovering(covering, updateAllSkinColours);
		if (covering.getType() == BodyCoveringType.HUMAN) {
			// Reload images when the skin changes
			loadImages();
		}
		return returnValue;
	}
	
	@Override
	public String getSpeechColour() {
		return "#ff66a3";
	}
	
	@Override
	public void changeFurryLevel(){
	}

	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this) && !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
			if(Main.game.isExtendedWorkTime()) {
				this.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
			} else {
				this.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE);
			}
		}
	}
	
	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character.isPlayer()) {
			if(character.getSubspeciesOverrideRace()==Race.DEMON) {
				Set<Relationship> rSet = new LinkedHashSet<>();
				rSet.add(Relationship.HalfSibling);
				rSet.add(Relationship.Pibling);
				return rSet;
			}
			return Util.newHashSetOfValues(Relationship.Pibling);
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex() {
		setPenisType(PenisType.NONE);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	public void growCock() {
		this.setPenisType(PenisType.DEMON_COMMON);
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(28);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setInternalTesticles(false);
		this.setPenisCumStorage(2000);
		this.fillCumToMaxStorage();
	}
	
	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer() && item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
			if(this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
				itemOwner.removeItem(item);
				return new Value<>(false,
						"<p>"
							+ "Producing a "+item.getColour(0).getName()+" "+item.getName(false, false)+" from your inventory, you pop it out of its plastic wrapper before bringing it up to Lilaya's mouth."
							+ " Seeing what it is you're trying to get her to swallow, she furrows her eyebrows and smacks the pill out of your [pc.hand], sending it flying off under one of the lab tables."
							+ " In a sharp tone, she admonishes you, "
							+ (this.hasPenis()
									?" [lilaya.speech(I don't care if you're trying to make my cum more virile! I'm <i>not</i> swallowing anything that would make me more fertile!)]"
									:" [lilaya.speech(I'm <i>not</i> swallowing anything that would make me more fertile! You're <i>not</i> going to cum inside of me anyway, so why would you even try that?!)]")
						+ "</p>");
				
			} else {
				itemOwner.useItem(item, this, false);
				return new Value<>(true,
						"<p>"
							+ "Producing a "+item.getName(false, false)+" from your inventory, you pop it out of its plastic wrapper before pushing it into Lilaya's mouth."
							+ " She lets out a delighted moan as she happily swallows the little "+ item.getColour(0).getName() +" pill,"
								+ " [lilaya.speechNoEffects(~Mmm!~ That's right, make my demonic womb nice and fertile! I don't hate getting pregnant anymore...)]"
						+ "</p>");
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
	@Override
	public String getAttributeChangeText(AbstractAttribute att, float value) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.getAttributeChangeText(att, value));
		
		if(this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
			if(att==Attribute.FERTILITY && value>0) {
				sb.append(UtilText.parse(this,
						"<p>"
							+ "[lilaya.speech(Wait... That made me more fertile?! What were you thinking?!)]"
							+ " Lilaya angrily exclaims, before letting out a deep sigh and muttering,"
							+ " [lilaya.speech(Whatever... It's not like it's ever going to make a difference...)]"
						+ "</p>"));
				
			} else if(att==Attribute.FERTILITY && value<0) {
				sb.append(UtilText.parse(this,
						"<p>"
							+ "[lilaya.speech(Wait... That made me less fertile?! Thank you, [pc.name]!)]"
							+ " Lilaya happily exclaims, before sighing,"
							+ " [lilaya.speech(It's not like it's ever going to make a difference anyway, but the thought is nice...)]"
						+ "</p>"));
			}
		}
		
		return sb.toString();
	}
	
	@Override
	public String getPotionAttributeChangeText(AbstractAttribute att, float value) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.getPotionAttributeChangeText(att, value));
		
		if(this.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
			if(att==Attribute.FERTILITY && value>0) {
				sb.append(UtilText.parse(this,
						"<p>"
							+ "[lilaya.speech(Wait... That made me more fertile?! What were you thinking?!)]"
							+ " Lilaya angrily exclaims, before letting out a deep sigh and muttering,"
							+ " [lilaya.speech(Whatever... It's not like it's ever going to make a difference...)]"
						+ "</p>"));
				
			} else if(att==Attribute.FERTILITY && value<0) {
				sb.append(UtilText.parse(this,
						"<p>"
							+ "[lilaya.speech(Wait... That made me less fertile?! Thank you, [pc.name]!)]"
							+ " Lilaya happily exclaims, before sighing,"
							+ " [lilaya.speech(It's not like it's ever going to make a difference anyway, but the thought is nice...)]"
						+ "</p>"));
			}
		}
		
		return sb.toString();
	}
	
	// Sex:
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SALilayaSpecials.class);
	}

	/**
	 * @return A <b>non-formatted</b> String of this NPCs speech related to no ongoing penetration.
	 */
	public String getDirtyTalkNoPenetration(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();

		speech.add("Fuck, why do demons always have to feel so horny?! All I ever think about is fucking you or Rose!");
		speech.add("I'm sure I can collect some valuable data from this...");
		if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
			speech.add("Horny for your new half-sister, hmm?");
			speech.add("There's nothing wrong with demonic siblings fucking one another...");
		} else {
			speech.add("I wonder if you ever did this with your real aunt?");
			speech.add("Wait, you still see me as your aunt, right? I guess I can go along with that...");
		}
		
		return speech.get(Util.random.nextInt(speech.size()));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this NPCs speech related to no ongoing penetration.
	 */
	public String getPlayerDirtyTalkNoPenetration(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		speech.add("Ah yes! I've wanted to fuck you for so long...");
		speech.add("You're so hot!");
		speech.add("I've wanted this for so long...");
		
		return speech.get(Util.random.nextInt(speech.size()));
	}

}