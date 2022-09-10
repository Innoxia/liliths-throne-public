package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
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
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
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
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.4.5
 * @version 0.4.4.5
 * @author Innoxia
 */
public class Lunexis extends NPC {

	public Lunexis() {
		this(false);
	}
	
	public Lunexis(boolean isImported) {
		super(isImported, new NameTriplet("Lunexis"), "Lunettemartu",
				"One of Lunette's recognised daughters, Lunexis is particularly strong, dominant, and brutal."
						+ " Delighting in destruction, chaos, and mayhem, she is used by her elder lilin mother as a living weapon, who tasks her with destroying towns and villages which have displeased her.",
				48, Month.JANUARY, 15,
				35,
				null, null, null,
				new CharacterInventory(10_000),
				WorldType.getWorldTypeFromId("innoxia_fields_themiscyra"), PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra_plaza"),
				true);
		
		if(!isImported) {
			this.setGenericName("four-armed demonic-centauresses");
			this.setPlayerKnowsName(false);
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.4.5")) {
			setupPerks(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 5),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.BRAVE,
					PersonalityTrait.LEWD,
					PersonalityTrait.CONFIDENT);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_LUNETTE_RECOGNISED_DAUGHTER);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_NON_CON_DOM);
			this.addFetish(Fetish.FETISH_PENIS_GIVING);
			
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BONDAGE_APPLIER, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_BONDAGE_VICTIM, FetishDesire.ONE_DISLIKE);
			
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		}
		
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(28);
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setWingType(WingType.NONE);
		this.setHornType(HornType.CURVED);
		this.setHornLength(HornLength.THREE_HUGE.getMedianValue());
		this.setLegType(LegType.DEMON_HORSE_HOOFED);
		this.setLegConfiguration(LegType.DEMON_HORSE_HOOFED, LegConfiguration.QUADRUPEDAL, true);
		this.setArmRows(2);
		
		// Core:
		this.setHeight(260);
		this.setFemininity(75);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.FOUR_HUGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_GOLD));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BLACK), true);

		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_EBONY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_EBONY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, CoveringPattern.MOTTLED, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_RED_DARK, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_BLACK), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.SIDECUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_BLACK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_BLACK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.F.getMeasurement());
		this.setBreastRows(3);
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FIVE_HUGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.setAssCapacity(Capacity.FIVE_ROOMY, true);
		this.setAssWetness(Wetness.FIVE_SLOPPY);
		// Horse-like modifiers:
		this.addAssOrificeModifier(OrificeModifier.PUFFY);
		this.addAssOrificeModifier(OrificeModifier.RIBBED);
		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.SIX_CHUBBY);
		this.setPenisSize(58);
		this.setTesticleSize(TesticleSize.FIVE_MASSIVE);
		this.setPenisCumStorage(5000);
		this.setPenisCumExpulsion(85);
		this.fillCumToMaxStorage();
		this.setTesticleCount(4);
		// Horse-like modifiers:
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.FLARED);
		this.addPenisModifier(PenetrationModifier.VEINY);
		this.addPenisModifier(PenetrationModifier.SHEATHED);
		this.addCumModifier(FluidModifier.MUSKY);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(10_000);
		
		// Weapons:
		
		this.equipMainWeapon(Main.game.getItemGen().generateWeapon("innoxia_blunt_morning_star", DamageType.PHYSICAL), 0, false);
		this.equipOffhandWeapon(Main.game.getItemGen().generateWeapon("innoxia_blunt_morning_star", DamageType.PHYSICAL), 0, false);
		this.equipMainWeapon(Main.game.getItemGen().generateWeapon("innoxia_blunt_morning_star", DamageType.FIRE), 1, false);
		this.equipOffhandWeapon(Main.game.getItemGen().generateWeapon("innoxia_blunt_morning_star", DamageType.FIRE), 1, false);
		
		
		// Clothing:

		AbstractClothing cloak = Main.game.getItemGen().generateClothing("innoxia_torsoOver_fur_cloak", PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false);
		this.equipClothingFromNowhere(cloak, true, this);
		this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_OVER), DisplacementType.OPEN, true, true, this);
		
		AbstractClothing hornRings = Main.game.getItemGen().generateClothing("innoxia_horn_ring_chain", PresetColour.CLOTHING_GOLD, false);
		hornRings.setSticker("more_chains", "add2");
		this.equipClothingFromNowhere(hornRings, true, this);
		

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_circlet", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_wrist_thin_bangles", PresetColour.CLOTHING_GOLD, false), true, this);
		
		
		// Piercings:
		
		this.setPiercedEar(true);
		AbstractClothing earChains = Main.game.getItemGen().generateClothing("innoxia_piercing_ear_cuff_chains", PresetColour.CLOTHING_GOLD, false);
		earChains.setSticker("more_chains", "add2");
		this.equipClothingFromNowhere(earChains, true, this);

		this.setPiercedNipples(true);
		AbstractClothing nippleChains = Main.game.getItemGen().generateClothing("innoxia_piercing_nipple_chain", PresetColour.CLOTHING_GOLD, false);
		nippleChains.setSticker("second_chain", "add1");
		nippleChains.setSticker("third_chain", "add2");
		this.equipClothingFromNowhere(nippleChains, true, this);

		this.setPiercedTongue(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_GOLD, false), true, this);

		this.setPiercedNavel(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_pentagram_navel", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_RED_DARK, PresetColour.CLOTHING_GOLD, false), true, this);

		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return PresetColour.BASE_RED.toWebHexString();
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
	
	
	// Utility classes for Themiscyra:
	
	public void initThemiscyra() {
		// Add raiders:
		for(Cell cell : Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_themiscyra")).getCells(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra_raiders"))) {
			addRandomRaider(cell, true);
			addRandomRaider(cell, false);
		}
	}
	
	public void clearThemiscyra() {
		// Clear raiders:
		for(Cell cell : Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_themiscyra")).getCells(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra_raiders"))) {
			for(NPC npc : Main.game.getCharactersPresent(cell)) {
				if(npc instanceof LunetteMelee || npc instanceof LunetteRanged) {
					Main.game.banishNPC(npc);
				}
			}
		}
	}
	
	private void addRandomRaider(Cell cell, boolean leader) {
		List<String> defaultNamePrefixes = Util.newArrayListOfValues("angry", "furious", "wrathful", "looting", "pillaging");
		String defaultName = "raider";
		if(leader) {
			defaultNamePrefixes = Util.newArrayListOfValues("commanding", "dominant");
			defaultName = "raider";
		}
		try {
			NPC npc;
			if(leader || Math.random()>0.5f) {
				npc = new LunetteMelee(defaultNamePrefixes, defaultName, false);
			} else {
				npc = new LunetteRanged(defaultNamePrefixes, defaultName, false);
			}
			npc.setLocation(cell, true);
			Main.game.addNPC(npc, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getRaidersClearedString() {
		StringBuilder sb = new StringBuilder();
		
		List<Cell> raidersCells = Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_themiscyra")).getCells(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra_raiders"));
		List<Cell> raidersClearedCells = Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_themiscyra")).getCells(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra_raiders_cleared"));
		int totalCells = raidersCells.size() + raidersClearedCells.size();
		
		if(raidersCells.size() > (totalCells/4)*3) { // 75%+ dangerous
			sb.append("Although you've defeated the raiders who were looting this part of Themiscyra, [style.colourBad(there are still many more)] to be found throughout the town...");
			
		} else if(raidersCells.size() > totalCells/2) { // 50%+ dangerous
			sb.append("You've defeated many groups of raiders within Themiscyra, although [style.colourMinorBad(there are still more)] to be found throughout the town...");
			
		} else if(raidersCells.size() > totalCells/4) { // 25%+ dangerous
			sb.append("You've defeated the majority of the raiders who are looting Themiscyra, and now [style.colourMinorGood(there aren't too many more)] to be found...");
			
		} else { // 0%+ dangerous
			if(raidersCells.size()==0) {
				sb.append("You've defeated all of the looting raiders, and are now recognised, by Aurokaris at least, as the [style.colourExcellent(Hero"+(Main.game.getPlayer().isFeminine()?"ine":"")+" of Themiscyra)]!");
			} else {
				sb.append("You've defeated the vast majority of the raiders who are looting Themiscyra; [style.colourGood(there are now only one or two groups left)] to defeat...");
			}
		}
		
		return sb.toString();
	}
	
	public void applyCollar(GameCharacter target) {
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_IRON, false);
		target.equipClothingFromNowhere(collar, true, this);
	}
	
	public void applyTraining(GameCharacter target) {
		target.setVaginaVirgin(false);
		target.setAnalVirgin(false);
		target.setFaceVirgin(false);

		target.addFetish(Fetish.FETISH_SUBMISSIVE);
		target.addFetish(Fetish.FETISH_MASOCHIST);
		target.addFetish(Fetish.FETISH_NON_CON_SUB);

		target.removeFetish(Fetish.FETISH_DOMINANT);
		target.removeFetish(Fetish.FETISH_NON_CON_DOM);
		target.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ZERO_HATE);
		target.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.ZERO_HATE);
	}

	public void applyEnslavedClothingChange() {
		List<InventorySlot> slotsToUnequip = Util.newArrayListOfValues(InventorySlot.TORSO_OVER, InventorySlot.HAND, InventorySlot.HEAD, InventorySlot.HORNS);
		for(InventorySlot slot : slotsToUnequip) {
			AbstractClothing clothingInSlot = this.getClothingInSlot(slot);
			if(clothingInSlot!=null) {
				this.unequipClothingIntoVoid(clothingInSlot, true, this);
			}
		}
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false);
		this.equipClothingFromNowhere(collar, true, this);
	}
	
	public void generateSurrenderedSexChoice() {
		List<Integer> availableChoices = new ArrayList<>();
		if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			availableChoices.add(2);
		}
		if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			availableChoices.add(3);
		}
		if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
			availableChoices.add(4);
		}
		if(availableChoices.isEmpty()) {
			Main.game.getDialogueFlags().setSavedLong("lunexis_sex_choice", 1);
		} else {
			Main.game.getDialogueFlags().setSavedLong("lunexis_sex_choice", Util.randomItemFrom(availableChoices));
		}
	}
}