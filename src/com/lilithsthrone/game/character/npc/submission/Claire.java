package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
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
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.sexActions.submission.SAClaireDangerSex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.5
 * @version 0.3.5
 * @author Innoxia
 */
public class Claire extends NPC {

	public Claire() {
		this(false);
	}
	
	public Claire(boolean isImported) {
		super(isImported, new NameTriplet("Claire"), "Kasun",
				"Claire is the messenger who's responsible for keeping all of Submission's Enforcer posts up-to-date with one another."
					+ " Having been recognised by her superiors as a particularly hard-working and reliable individual, she's been granted permission to use the teleportation pads which link all of these posts together."
					+ " As a result of this, she seems to be in multiple places at once, and whenever you enter one of these Enforcer posts, Claire is sure to already be there.",
				31, Month.DECEMBER, 14,
				10, Gender.F_V_B_FEMALE, Subspecies.CAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, true);
		
		if(!isImported) {
			this.setPlayerKnowsName(false);
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 10);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4.9")) {
			this.setStartingBody(false);
			this.setHistory(Occupation.NPC_ENFORCER_PATROL_SERGEANT);
			this.setDescription(
				"Claire is the messenger who's responsible for keeping all of Submission's Enforcer posts up-to-date with one another."
					+ " Having been recognised by her superiors as a particularly hard-working and reliable individual, she's been granted permission to use the teleportation pads which link all of these posts together."
					+ " As a result of this, she seems to be in multiple places at once, and whenever you enter one of these Enforcer posts, Claire is sure to already be there.");
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.6")) {
			this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.7")) {
			equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ENFORCER_PATROL_SERGEANT);
	
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_STRUTTER);
			this.setFetishDesire(Fetish.FETISH_VOYEURIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_LEG_LOVER, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ZERO_HATE);
		}
		
		
		// Body:

		// Core:
		this.setHeight(149);
		this.setFemininity(85);
		this.setMuscle(Muscle.ZERO_SOFT.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, PresetColour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, PresetColour.COVERING_PINK_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, PresetColour.COVERING_PINK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_PINK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_FELINE_FUR, PresetColour.COVERING_PINK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FF.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE.getValue());
		this.setAreolaeSize(AreolaeSize.FOUR_MASSIVE.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FIVE_HUGE);
		this.setHipSize(HipSize.SEVEN_ABSURDLY_WIDE);
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
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.setEssenceCount(50);
		
		this.setPiercedEar(true);
		
		if(settings!=null && settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("dsg_eep_taser_taser")));
		}
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_sports_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boyshorts", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_enfslacks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_utilbelt", PresetColour.CLOTHING_BLACK, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_fssldshirt", PresetColour.CLOTHING_PINK, false), true, this);

		AbstractClothing vest = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_stpvest", PresetColour.CLOTHING_BLACK, false);
		vest.setSticker("name_plate", "claire");
		this.equipClothingFromNowhere(vest, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", PresetColour.CLOTHING_BLACK, false), true, this);
		
		AbstractClothing beret = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfberet", PresetColour.CLOTHING_PINK, false);
		beret.setSticker("flash", "flash_patrol_dominion");
		this.equipClothingFromNowhere(beret, true, this);

	}

	@Override
	public void turnUpdate() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.SUBMISSION) {
			this.setLocation(WorldType.SUBMISSION, Main.game.getWorlds().get(WorldType.SUBMISSION).getClosestCell(Main.game.getPlayer().getLocation(), PlaceType.SUBMISSION_ENTRANCE).getLocation(), true);
			
		} else if(Main.game.getPlayer().getWorldLocation()==WorldType.ENFORCER_WAREHOUSE) {
			AbstractPlaceType pt = Main.game.getPlayerCell().getPlace().getPlaceType();
			if(!pt.equals(PlaceType.ENFORCER_WAREHOUSE_ENCLOSURE)
					&& !pt.equals(PlaceType.ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_PADS)
					&& !pt.equals(PlaceType.ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_SHELVING)
					&& !pt.equals(PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST)) {
				this.setLocation(Main.game.getPlayer(), false);
			}
			if(pt.equals(PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST) && Main.game.getDialogueFlags().warehouseDefeatedIDs.contains(EnforcerWarehouse.getEnforcersPresent().get(0).getId())) {
				this.setLocation(Main.game.getPlayer(), false);
			}

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireObtainedLightningGlobe) && Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()==0) {
				this.setLust(80);
			}
		}

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
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	

	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SAClaireDangerSex.class);
	}

}