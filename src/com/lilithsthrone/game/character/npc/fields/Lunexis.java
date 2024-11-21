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
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
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
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.pregnancy.FertilisationType;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
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
		super(isImported, new NameTriplet("Lunexis"), "Lunettemartuilani",
				"One of Lunette's recognised daughters, Lunexis is particularly strong, dominant, and brutal."
						+ " Delighting in destruction, chaos, and mayhem, she is used by her elder lilin mother as a living weapon, who tasks her with destroying towns and villages which have displeased her.",
				517, Month.JANUARY, 15,
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
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
			this.setAge(517);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.13")) {
			this.setBreastRows(2);
			this.setPiercedPenis(true);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_penis_ring", PresetColour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_wrist_thin_bangles", PresetColour.CLOTHING_GOLD, false), InventorySlot.TAIL, true, this);
			this.setSurname("Lunettemartuilani");
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
		this.setAgeAppearanceAbsolute(28);
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
		this.setBreastRows(2);
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
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_wrist_thin_bangles", PresetColour.CLOTHING_GOLD, false), InventorySlot.WRIST, true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_wrist_thin_bangles", PresetColour.CLOTHING_GOLD, false), InventorySlot.TAIL, true, this);
		
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

		this.setPiercedPenis(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_penis_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		
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
	public String getArtworkFolderName() {
		return "Lunexis";
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
	
	@Override
	public int getBreastRows() {
		return body.getBreast().getRows(); // Should not be affected by settings
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

	public void applyPlayerBadEnd() {
		GameCharacter player = Main.game.getPlayer();
		
		if(Main.game.getDialogueFlags().hasFlag("innoxia_themiscyra_bad_end_oral")) {
			player.setFaceCapacity(Capacity.FIVE_ROOMY, true);
			player.setFaceDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
			
		} else if(Main.game.getDialogueFlags().hasFlag("innoxia_themiscyra_bad_end_vaginal")) {
			player.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
			player.setVaginaWetness(Wetness.FIVE_SLOPPY);
			player.setVaginaDepth(OrificeDepth.FOUR_DEEP.getValue());
			
		} else {
			player.setAssCapacity(Capacity.FIVE_ROOMY, true);
			player.setAssWetness(Wetness.FIVE_SLOPPY);
			player.setAssDepth(OrificeDepth.FOUR_DEEP.getValue());
		}
	}
	
	public void applyMeraxisBadEnd() {
		DarkSiren meraxis = (DarkSiren) Main.game.getNpc(DarkSiren.class);
		
		meraxis.unequipAllClothingIntoVoid(true, true); // No longer needs collar as she gets a naval piercing
		
		// Body changes:
			// Coverings:
			meraxis.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK));
			meraxis.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK));
			meraxis.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, CoveringPattern.NONE, PresetColour.COVERING_BLACK));
			meraxis.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK));
			meraxis.addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
			meraxis.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
			meraxis.addHeavyMakeup(BodyCoveringType.MAKEUP_EYE_LINER);
			meraxis.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
			meraxis.addHeavyMakeup(BodyCoveringType.MAKEUP_EYE_SHADOW);
			// Misc.:
			meraxis.setHairStyle(HairStyle.NONE);
			meraxis.setHairLength(0);
			meraxis.setWingType(WingType.NONE);
			meraxis.setLegType(LegType.DEMON_HOOFED);
			// Breasts:
			meraxis.setBreastRows(5);
			meraxis.setBreastSize(CupSize.F);
			meraxis.setBreastShape(BreastShape.PERKY);
			meraxis.setNippleSize(NippleSize.FOUR_MASSIVE);
			meraxis.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
			meraxis.setNippleCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(), true);
			meraxis.addNippleOrificeModifier(OrificeModifier.PUFFY);
			// Vagina:
			meraxis.setVaginaCapacity(Capacity.SIX_STRETCHED_OPEN, true);
			meraxis.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
			meraxis.setVaginaWetness(Wetness.SEVEN_DROOLING);
			meraxis.setVaginaDepth(OrificeDepth.FOUR_DEEP.getValue());
			meraxis.addVaginaOrificeModifier(OrificeModifier.PUFFY);
			meraxis.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
			meraxis.addVaginaOrificeModifier(OrificeModifier.RIBBED);
			meraxis.addVaginaOrificeModifier(OrificeModifier.TENTACLED);
			// Ass:
			meraxis.setAssSize(AssSize.FOUR_LARGE);
			meraxis.setHipSize(HipSize.FOUR_WOMANLY);
			meraxis.setAssCapacity(Capacity.FOUR_LOOSE, true);
			meraxis.setAssDepth(OrificeDepth.FOUR_DEEP.getValue());
			meraxis.addAssOrificeModifier(OrificeModifier.PUFFY);
			meraxis.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
			meraxis.addAssOrificeModifier(OrificeModifier.RIBBED);
			meraxis.addAssOrificeModifier(OrificeModifier.TENTACLED);
			// Mouth:
			meraxis.setFaceCapacity(Capacity.SEVEN_GAPING, true);
			meraxis.setLipSize(LipSize.SEVEN_ABSURD);
			meraxis.setFaceDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
			meraxis.addFaceOrificeModifier(OrificeModifier.PUFFY);
			meraxis.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
			meraxis.addFaceOrificeModifier(OrificeModifier.RIBBED);
			meraxis.addFaceOrificeModifier(OrificeModifier.TENTACLED);
		// Fetish changes (super slut):
			meraxis.clearFetishDesires();
			meraxis.clearFetishes();
			meraxis.addFetish(Fetish.FETISH_NON_CON_SUB);
			meraxis.addFetish(Fetish.FETISH_SUBMISSIVE);
			meraxis.addFetish(Fetish.FETISH_SIZE_QUEEN);
			meraxis.addFetish(Fetish.FETISH_ANAL_GIVING);
			meraxis.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			meraxis.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			meraxis.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			meraxis.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			meraxis.addFetish(Fetish.FETISH_ORAL_GIVING);
			meraxis.addFetish(Fetish.FETISH_ARMPIT_GIVING);
			meraxis.addFetish(Fetish.FETISH_BONDAGE_VICTIM);
			meraxis.addFetish(Fetish.FETISH_BREASTS_OTHERS);
			meraxis.addFetish(Fetish.FETISH_BREASTS_SELF);
			meraxis.addFetish(Fetish.FETISH_PREGNANCY);
			meraxis.addFetish(Fetish.FETISH_CUM_ADDICT);
			meraxis.addFetish(Fetish.FETISH_DENIAL_SELF);
			meraxis.addFetish(Fetish.FETISH_EXHIBITIONIST);
			meraxis.addFetish(Fetish.FETISH_FOOT_RECEIVING);
			meraxis.addFetish(Fetish.FETISH_LACTATION_SELF);
			meraxis.addFetish(Fetish.FETISH_MASOCHIST);
			meraxis.addFetish(Fetish.FETISH_STRUTTER);
			meraxis.addFetish(Fetish.FETISH_VOYEURIST);
		// Piercings:
			meraxis.setPiercedEar(true);
			meraxis.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_GOLD, false), true, meraxis);
			meraxis.setPiercedLip(true);
			meraxis.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", PresetColour.CLOTHING_GOLD, false), true, meraxis);
			
			meraxis.setPiercedNavel(true);
			AbstractClothing pentagram = Main.game.getItemGen().generateClothing("norin_piercings_piercing_pentagram_navel", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_PINK_HOT, null, false);
			pentagram.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
			pentagram.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
			pentagram.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SERVITUDE, TFPotency.MINOR_BOOST, 0));
			for(int i=0;i<33;i++) { //+100 corruption, -100 lust shielding
				pentagram.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0));
				pentagram.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0));
			}
			pentagram.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MINOR_BOOST, 0));
			pentagram.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MINOR_DRAIN, 0));
			pentagram.setName("Cock-sleeve's Reward");
			meraxis.equipClothingFromNowhere(pentagram, true, meraxis);
			
			meraxis.setPiercedNipples(true);
			meraxis.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_nipple_rings", PresetColour.CLOTHING_GOLD, false), true, meraxis);
			meraxis.setPiercedNose(true);
			meraxis.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_cattle_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, meraxis);
			meraxis.setPiercedTongue(true);
			meraxis.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_GOLD, false), true, meraxis);
			meraxis.setPiercedVagina(true);
			meraxis.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_vagina_sex", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_PINK_HOT, null, false), true, meraxis);
		// Tattoos:
			meraxis.addTattoo(InventorySlot.EYES,
					new Tattoo(
							"innoxia_animal_hoof",
							PresetColour.CLOTHING_PURPLE,
							false,
							new TattooWriting("Hoof Cleaner", PresetColour.CLOTHING_PURPLE, false),
							null));
			meraxis.addTattoo(InventorySlot.HORNS,
					new Tattoo(
							"innoxia_symbol_lines",
							PresetColour.CLOTHING_PURPLE_LIGHT,
							false,
							null,
							null));
			meraxis.addTattoo(InventorySlot.TORSO_OVER,
					new Tattoo(
							"innoxia_animal_hoof",
							PresetColour.CLOTHING_PURPLE_DARK,
							false,
							new TattooWriting("Step On Me!", PresetColour.CLOTHING_PURPLE, false, TattooWritingStyle.BOLD),
							null));
			meraxis.addTattoo(InventorySlot.NECK,
					new Tattoo(
							"innoxia_symbol_lines",
							PresetColour.CLOTHING_PURPLE,
							false,
							new TattooWriting("Choke Me!", PresetColour.CLOTHING_PURPLE, false, TattooWritingStyle.BOLD),
							new TattooCounter(TattooCounterType.SEX_SUB, TattooCountType.NUMBERS, PresetColour.CLOTHING_PURPLE, false)));
			meraxis.addTattoo(InventorySlot.WRIST,
					new Tattoo(
							"innoxia_hearts_hearts",
							PresetColour.CLOTHING_PURPLE,
							PresetColour.CLOTHING_PURPLE_DARK,
							PresetColour.CLOTHING_PURPLE,
							false,
							null,
							null));
			meraxis.addTattoo(InventorySlot.TORSO_UNDER,
					new Tattoo(
							"innoxia_symbol_tribal",
							PresetColour.CLOTHING_PINK_HOT,
							false,
							new TattooWriting("~Fill Me Up~", PresetColour.CLOTHING_PINK_HOT, false, TattooWritingStyle.ITALICISED),
							new TattooCounter(TattooCounterType.CUM_TAKEN, TattooCountType.NUMBERS, PresetColour.CLOTHING_PINK_HOT, false)));
			meraxis.addTattoo(InventorySlot.CHEST,
					new Tattoo(
							"innoxia_hearts_hearts",
							PresetColour.CLOTHING_PINK_HOT,
							PresetColour.CLOTHING_PURPLE_ROYAL,
							PresetColour.CLOTHING_PURPLE,
							false,
							new TattooWriting("~Slap My Tits~", PresetColour.CLOTHING_PINK_HOT, false, TattooWritingStyle.ITALICISED),
							new TattooCounter(TattooCounterType.UNIQUE_SEX_PARTNERS, TattooCountType.NUMBERS, PresetColour.CLOTHING_PINK_HOT, false)));
			meraxis.addTattoo(InventorySlot.NIPPLE,
					new Tattoo(
							"innoxia_knot_spiral",
							PresetColour.CLOTHING_RED_BURGUNDY,
							false,
							new TattooWriting("~Pinch & Pull~", PresetColour.CLOTHING_RED, false, TattooWritingStyle.ITALICISED),
							null));
			meraxis.addTattoo(InventorySlot.HAND,
					new Tattoo(
							"innoxia_hearts_hearts",
							PresetColour.CLOTHING_PURPLE,
							PresetColour.CLOTHING_PURPLE_DARK,
							PresetColour.CLOTHING_PURPLE,
							false,
							null,
							null));
			meraxis.addTattoo(InventorySlot.HIPS,
					new Tattoo(
							"innoxia_animal_butterflies",
							PresetColour.CLOTHING_PINK_HOT,
							PresetColour.CLOTHING_PINK,
							PresetColour.CLOTHING_PINK_LIGHT,
							false,
							null,
							null));
			meraxis.addTattoo(InventorySlot.STOMACH,
					new Tattoo(
							"innoxia_symbol_pentagram",
							PresetColour.CLOTHING_PURPLE_VERY_DARK,
							PresetColour.CLOTHING_PURPLE,
							PresetColour.CLOTHING_PURPLE,
							false,
							new TattooWriting("Foal Factory", PresetColour.CLOTHING_PURPLE, false),
							new TattooCounter(TattooCounterType.OFFSPRING_BIRTHED, TattooCountType.NUMBERS, PresetColour.CLOTHING_PURPLE, false)));
			meraxis.addTattoo(InventorySlot.FINGER,
					new Tattoo(
							"innoxia_symbol_lines",
							PresetColour.CLOTHING_BLACK,
							false,
							null,
							null));
			meraxis.addTattoo(InventorySlot.ANKLE,
					new Tattoo(
							"innoxia_knot_trinity",
							PresetColour.CLOTHING_PURPLE,
							false,
							new TattooWriting("Trip Me Up!", PresetColour.CLOTHING_PURPLE, false, TattooWritingStyle.BOLD),
							null));
			meraxis.addTattoo(InventorySlot.LEG,
					new Tattoo(
							"innoxia_hearts_hearts",
							PresetColour.CLOTHING_PURPLE,
							PresetColour.CLOTHING_PURPLE_DARK,
							PresetColour.CLOTHING_PURPLE,
							false,
							null,
							null));
			meraxis.addTattoo(InventorySlot.SOCK,
					new Tattoo(
							"innoxia_hearts_hearts",
							PresetColour.CLOTHING_PURPLE,
							PresetColour.CLOTHING_PURPLE_DARK,
							PresetColour.CLOTHING_PURPLE,
							false,
							null,
							null));
			meraxis.addTattoo(InventorySlot.GROIN,
					new Tattoo(
							"innoxia_heartWomb_heart_womb",
							PresetColour.CLOTHING_PINK_HOT,
							PresetColour.CLOTHING_BLACK,
							PresetColour.CLOTHING_PINK_HOT,
							false,
							new TattooWriting("~Knock Me Up~", PresetColour.CLOTHING_PINK_HOT, false, TattooWritingStyle.ITALICISED),
							new TattooCounter(TattooCounterType.PREGNANCY, TattooCountType.TALLY, PresetColour.CLOTHING_PINK_HOT, false)));
			meraxis.addTattoo(InventorySlot.ANUS,
					new Tattoo(
							"innoxia_symbol_pentagram",
							PresetColour.CLOTHING_BLACK_JET,
							PresetColour.CLOTHING_RED_VERY_DARK,
							PresetColour.CLOTHING_PURPLE,
							false,
							new TattooWriting("Rough Anal Only!", PresetColour.CLOTHING_RED, false, TattooWritingStyle.BOLD),
							new TattooCounter(TattooCounterType.CUM_TAKEN_ANUS, TattooCountType.NUMBERS, PresetColour.CLOTHING_RED, false)));
			meraxis.addTattoo(InventorySlot.VAGINA,
					new Tattoo(
							"innoxia_animal_hoof",
							PresetColour.CLOTHING_PURPLE_DARK,
							false,
							new TattooWriting("Break This Cunt!", PresetColour.CLOTHING_PURPLE, false, TattooWritingStyle.BOLD),
							new TattooCounter(TattooCounterType.CUM_TAKEN_PUSSY, TattooCountType.NUMBERS, PresetColour.CLOTHING_PURPLE, false)));
		// Increment stats for tattoo counters:
			applyTrainingSexCounts(meraxis, new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), 1);
			applyTrainingSexCounts(meraxis, new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), 0.75f);
			applyTrainingSexCounts(meraxis, new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), 0.8f);
			applyTrainingSexCounts(meraxis, new SexType(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS), 0.1f);
			// Non-penetrative:
			applyTrainingSexCounts(meraxis, new SexType(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS), 0.1f);
			applyTrainingSexCounts(meraxis, new SexType(SexAreaOrifice.ASS, SexAreaPenetration.PENIS), 0.05f);
			applyTrainingSexCounts(meraxis, new SexType(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS), 0.05f);
			applyTrainingSexCounts(meraxis, new SexType(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS), 0.05f);
			for(int i=0; i<2+Util.random.nextInt(3); i++) {
				meraxis.guaranteePregnancyOnNextRoll();
				meraxis.rollForPregnancy(Subspecies.DEMON, Subspecies.HORSE_MORPH, 2500, true, FertilisationType.NORMAL);
				meraxis.endPregnancy(true);
			}
	}
	
	public void applyMeraxisBadEndFinal() {
		DarkSiren meraxis = (DarkSiren) Main.game.getNpc(DarkSiren.class);
		meraxis.addTattoo(InventorySlot.MOUTH,
				new Tattoo(
						"innoxia_symbol_tribal",
						PresetColour.CLOTHING_PINK,
						false,
						new TattooWriting(Main.game.isAnalContentEnabled()?"Rimjob Whore":"Cunt Kisser", PresetColour.CLOTHING_PINK, false),
						null));
	}
	
	public void applyAurokarisBadEnd() {
		Aurokaris aurokaris = (Aurokaris) Main.game.getNpc(Aurokaris.class);
		
		// Body changes:
			// Vagina:
			aurokaris.setVaginaCapacity(Capacity.SEVEN_GAPING, true);
			aurokaris.setVaginaWetness(Wetness.FIVE_SLOPPY);
			aurokaris.setVaginaDepth(OrificeDepth.FOUR_DEEP.getValue());
			// Ass:
			aurokaris.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Fetish changes:
			aurokaris.clearFetishes();
			aurokaris.addFetish(Fetish.FETISH_NON_CON_SUB);
			aurokaris.addFetish(Fetish.FETISH_SUBMISSIVE);
			aurokaris.addFetish(Fetish.FETISH_SIZE_QUEEN);
			aurokaris.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			aurokaris.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			aurokaris.addFetish(Fetish.FETISH_PREGNANCY);
			aurokaris.addFetish(Fetish.FETISH_CUM_ADDICT);
			aurokaris.addFetish(Fetish.FETISH_EXHIBITIONIST);
			aurokaris.addFetish(Fetish.FETISH_LACTATION_SELF);
			aurokaris.addFetish(Fetish.FETISH_MASOCHIST);
	
			applyTrainingSexCountsAurokaris(aurokaris, new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), 1);
			applyTrainingSexCountsAurokaris(aurokaris, new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), 0.1f);
			applyTrainingSexCountsAurokaris(aurokaris, new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), 0.1f);
			
			aurokaris.guaranteePregnancyOnNextRoll();
			aurokaris.rollForPregnancy(Subspecies.DEMON, Subspecies.HORSE_MORPH, 2500, true, FertilisationType.NORMAL);
			ItemEffectType.MOTHERS_MILK.applyEffect(null, null, null, 0, aurokaris, aurokaris, null);
			ItemEffectType.MOTHERS_MILK.applyEffect(null, null, null, 0, aurokaris, aurokaris, null);
			ItemEffectType.MOTHERS_MILK.applyEffect(null, null, null, 0, aurokaris, aurokaris, null);
	}

	public void applyUrsaBadEnd() {
		Ursa ursa = (Ursa) Main.game.getNpc(Ursa.class);

		// Body changes:
			// Vagina:
			ursa.setVaginaCapacity(Capacity.SEVEN_GAPING, true);
			ursa.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
			ursa.setVaginaWetness(Wetness.SEVEN_DROOLING);
			ursa.setVaginaDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
			// Ass:
			ursa.setAssCapacity(Capacity.SEVEN_GAPING, true);
			ursa.setAssWetness(Wetness.SEVEN_DROOLING);
			ursa.setAssDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
			// Mouth:
			ursa.setFaceCapacity(Capacity.SEVEN_GAPING, true);
			ursa.setFaceDepth(OrificeDepth.SIX_CAVERNOUS.getValue());
		// Fetish changes:
			ursa.clearFetishDesires();
			ursa.clearFetishes();
			ursa.addFetish(Fetish.FETISH_NON_CON_SUB);
			ursa.addFetish(Fetish.FETISH_SUBMISSIVE);
			ursa.addFetish(Fetish.FETISH_SIZE_QUEEN);
			ursa.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			ursa.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			ursa.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			ursa.addFetish(Fetish.FETISH_ORAL_GIVING);
			ursa.addFetish(Fetish.FETISH_ARMPIT_GIVING);
			ursa.addFetish(Fetish.FETISH_BONDAGE_VICTIM);
			ursa.addFetish(Fetish.FETISH_BREASTS_SELF);
			ursa.addFetish(Fetish.FETISH_CUM_ADDICT);
			ursa.addFetish(Fetish.FETISH_EXHIBITIONIST);
			ursa.addFetish(Fetish.FETISH_FOOT_RECEIVING);
			ursa.addFetish(Fetish.FETISH_MASOCHIST);
		// Clothing:
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", PresetColour.CLOTHING_RED_BURGUNDY, PresetColour.CLOTHING_IRON, null, false), true, ursa);
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_croptop", PresetColour.CLOTHING_RED_BURGUNDY, PresetColour.CLOTHING_IRON, null, false), true, ursa);
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_clover_clamps_clover_clamps", PresetColour.CLOTHING_IRON, false), true, ursa);
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_corset", PresetColour.CLOTHING_RED_BURGUNDY, PresetColour.CLOTHING_IRON, null, false), true, ursa);
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_elbow_gloves", PresetColour.CLOTHING_RED_BURGUNDY, false), true, ursa);
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_restraints", PresetColour.CLOTHING_RED_BURGUNDY, PresetColour.CLOTHING_IRON, null, false), true, ursa);
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_goth_boots_fem", PresetColour.CLOTHING_RED_BURGUNDY, PresetColour.CLOTHING_IRON, PresetColour.CLOTHING_RED_BURGUNDY, false), true, ursa);
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_stockings", PresetColour.CLOTHING_RED_BURGUNDY, false), true, ursa);
			ursa.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_spreaderbar", PresetColour.CLOTHING_RED_BURGUNDY, PresetColour.CLOTHING_IRON, null, false), true, ursa);
		// Sex stats:
			applyTrainingSexCounts(ursa, new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), 1);
			applyTrainingSexCounts(ursa, new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), 0.75f);
			applyTrainingSexCounts(ursa, new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), 0.8f);
			applyTrainingSexCounts(ursa, new SexType(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS), 0.1f);
			// Non-penetrative:
			applyTrainingSexCounts(ursa, new SexType(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS), 0.1f);
			applyTrainingSexCounts(ursa, new SexType(SexAreaOrifice.ASS, SexAreaPenetration.PENIS), 0.05f);
			applyTrainingSexCounts(ursa, new SexType(SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS), 0.05f);
			applyTrainingSexCounts(ursa, new SexType(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS), 0.05f);
	}
	
	private void applyTrainingSexCounts(GameCharacter character, SexType sexType, float frequency) {
		// Fucked by up to 200-300 different centauresses in this SexType:
		int luckyCentauressCount = (int) ((200 + Util.random.nextInt(100)) * frequency);
		
		for(int i=0; i<luckyCentauressCount; i++) {
			String centauressId = "lunexisCentauress"+i;
			int sexCount = 1 + Util.random.nextInt(3);
			if(character instanceof Ursa) {
				sexCount*=4;// Ursa gets fucked a lot. What a sloot.
			}
			character.setSexAsSubCountById(centauressId, sexCount);
			character.setSexCountById(centauressId, sexType, sexCount);
			character.setCumCountById(centauressId, sexType, (Math.random()<0.75f?1:0)+Util.random.nextInt(sexCount));
		}
	}

	private void applyTrainingSexCountsAurokaris(GameCharacter character, SexType sexType, float frequency) {
		// Fucked by only her owner in this SexType:
		String centauressId = "aurokarisOwner";
		// She gets fucked a lot over 2 months:
		int sexCount = (int) ((2000 + Util.random.nextInt(501)) * frequency);
		character.setSexAsSubCountById(centauressId, sexCount);
		character.setSexCountById(centauressId, sexType, sexCount);
		character.setCumCountById(centauressId, sexType, sexCount-Util.random.nextInt(501));
		
	}
}