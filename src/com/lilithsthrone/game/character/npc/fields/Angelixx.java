package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
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
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Innoxia
 */
public class Angelixx extends NPC {

	public Angelixx() {
		this(false);
	}
	
	public Angelixx(boolean isImported) {
		super(isImported,
				new NameTriplet("Angelixx"), "Loviennemartuilani",
				"Although she appears as a young, innocent succubus, Angelixx is an extremely powerful and dangerous pawn of her mother, the elder lilin Lovienne.",
				52, Month.MAY, 17,
				30, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				true);
		
		if(!isImported) {
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.8.10")) {
			this.setupPerks(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9")) {
			this.setStartingBody(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.ORGASMIC_LEVEL_DRAIN,
						Perk.CLOTHING_ENCHANTER,
						Perk.WEAPON_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.LEWD,
					PersonalityTrait.INNOCENT,
					PersonalityTrait.COWARDLY);

			this.addSpell(Spell.CLOAK_OF_FLAMES);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_3);
			
			this.addSpell(Spell.PROTECTIVE_GUSTS);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3);

			this.addSpell(Spell.STONE_SHELL);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_1);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_2);
			this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_3);
			
			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3);

			this.addSpell(Spell.FLASH);
			this.addSpellUpgrade(SpellUpgrade.FLASH_1);
			this.addSpellUpgrade(SpellUpgrade.FLASH_2);
			this.addSpellUpgrade(SpellUpgrade.FLASH_3);

			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3);

			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_LILIN_PAWN);

			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);

			this.setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ARMPIT_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DENIAL, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ONE_DISLIKE);
		}
		
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(18);
		this.setTailType(TailType.NONE);
		this.setWingType(WingType.DEMON_FEATHERED);
		this.setWingSize(WingSize.ZERO_TINY.getValue());
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(HornLength.ZERO_TINY.getMedianValue());
		this.setHornRows(1);
		
		// Core:
		this.setHeight(152);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_BLUE_LIGHT));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_LIGHT), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, CoveringPattern.OMBRE, CoveringModifier.SMOOTH, PresetColour.COVERING_WHITE, false, PresetColour.SKIN_LIGHT, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_ROSY, false, PresetColour.ORIFICE_INTERIOR, false), false);

		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_FEATHER, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLONDE), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.TWIN_TAILS);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_DIRTY_BLONDE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_PINK_LIGHT));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLUE_DARK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.A.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		this.addNippleOrificeModifier(OrificeModifier.PUFFY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.FOUR_SLIMY);
		this.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setAssPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		// Anus settings and modifiers
		
		// Penis:
		// n/a
		this.setTesticleCount(2); // For if she grows one
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.addTattoo(InventorySlot.VAGINA,
				new Tattoo("innoxia_animal_hoof", PresetColour.COVERING_BLACK, PresetColour.COVERING_BLACK, PresetColour.COVERING_BLACK, false,
						null, null));
		
		AbstractClothing vstring = Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_WHITE, false);
		vstring.setPattern("irbynx_polka_dots_big");
		vstring.setPatternColours(Util.newArrayListOfValues(PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE));
		this.equipClothingFromNowhere(vstring, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false), true, this);

//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband_bow", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_PINK, false), true, this);
		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_BLUE_LIGHT, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_pleated", PresetColour.CLOTHING_BLUE_LIGHT, false), true, this);


		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_leg_warmers", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("nerdlinger_street_hitop_canvas_sneakers_hi_top_canvas_sneakers", PresetColour.CLOTHING_BLUE_LIGHT, false), true, this);
		
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_velvet_choker", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_SILVER, null, false), true, this);
		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_wrap_ring", PresetColour.CLOTHING_SILVER, false);
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
		this.equipClothingFromNowhere(ring, true, this);
		
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_heart", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, null, false), true, this);
		
		this.setPiercedEar(true);
		this.setPiercedNavel(true);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	protected Set<GameCharacter> getChildren() {
		Set<GameCharacter> children = super.getChildren();
		
		children.add(Main.game.getNpc(Sleip.class));
		children.add(Main.game.getNpc(Nir.class));
		
		return children;
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
	
	// External methods:
	
	private List<String> adjectivesUsed = new ArrayList<>();
	public void initGangMember(NPC gangMember, boolean resetAdjectives) {
		if(resetAdjectives) {
			adjectivesUsed = new ArrayList<>();
		}
		gangMember.setHistory(Occupation.NPC_MUGGER);
		gangMember.equipClothing();
		gangMember.setLocation(Main.game.getPlayerCell(), true);
		gangMember.removePersonalityTrait(PersonalityTrait.MUTE);
		adjectivesUsed.add(Main.game.getCharacterUtils().setGenericName(gangMember, "gang member", adjectivesUsed));
		
		gangMember.unequipAllWeaponsIntoVoid(true);
	}
	
	public void setDefeatedName(NPC gangMember, boolean resetAdjectives) {
		if(resetAdjectives) {
			adjectivesUsed = Util.newArrayListOfValues("knocked-out", "unconscious", "defeated");
			Collections.shuffle(adjectivesUsed);
		}
		String adjective = "defeated";
		if(adjectivesUsed.size()>0) {
			adjective = adjectivesUsed.get(0);
			adjectivesUsed.remove(0);
		}
		
		gangMember.setGenericName(adjective+" "+(gangMember.isFeminine()?gangMember.getSubspecies().getSingularFemaleName(gangMember.getBody()):gangMember.getSubspecies().getSingularMaleName(gangMember.getBody())));
	}
	
	public void setDefeatedNamesPostCombat() {
		List<NPC> enemies = Main.game.getCharactersPresent().stream().filter(npc->npc instanceof ElisAlleywayAttacker && npc.hasFoughtPlayer()).collect(Collectors.toList());
		for(int i=0;i<enemies.size();i++) {
			setDefeatedName(enemies.get(i), i==0);
		}
	}
	
	public void initVictim(NPC victim, NPC gangMember) {
		victim.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, true);
		victim.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
		victim.setAnalVirgin(false);
		victim.setFaceVirgin(false);
		victim.setVaginaVirgin(false);
		
		victim.setHistory(Occupation.NPC_CAPTIVE);
		victim.unequipAllClothingIntoVoid(true, true);

		AbstractClothing choker = Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false);
		choker.setSticker("top_txt", "worthless");
		choker.setSticker("btm_txt", "whore");
		victim.equipClothingFromNowhere(choker, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false), InventorySlot.WRIST, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, null, false), InventorySlot.ANKLE, true, gangMember);
		victim.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_STEEL, false), true, gangMember);
		
		victim.addFluidStored(SexAreaOrifice.VAGINA, new FluidStored(gangMember, gangMember.getCum(), 250));
		victim.addFluidStored(SexAreaOrifice.MOUTH, new FluidStored(gangMember, gangMember.getCum(), 100));
		victim.addDirtySlot(InventorySlot.ANUS);
		victim.addDirtySlot(InventorySlot.CHEST);
		victim.addDirtySlot(InventorySlot.GROIN);
		victim.addDirtySlot(InventorySlot.LEG);
		victim.addDirtySlot(InventorySlot.HIPS);
		victim.addDirtySlot(InventorySlot.STOMACH);
		victim.addDirtySlot(InventorySlot.TORSO_UNDER);
		victim.addDirtySlot(InventorySlot.EYES);
		victim.addDirtySlot(InventorySlot.MOUTH);
		victim.addDirtySlot(InventorySlot.HEAD);
		
		victim.setLocation(Main.game.getPlayerCell(), true);
		victim.removePersonalityTrait(PersonalityTrait.MUTE);
		victim.setGenericName("captive");
	}

	public void initGangMemberGuards() {
		Main.game.getNpc(Sleip.class).setLocation(this);
		Main.game.getNpc(Nir.class).setLocation(this);
	}
	
	/**
	 * Banishes all gang members from ground floor (pass in 0 as argument) or first floor (pass in 1 as argument)
	 */
	public void banishGangMembers(int floor) {
		String worldId = "innoxia_fields_elis_abandoned_bakery_f0";
		if(floor==1) {
			worldId = "innoxia_fields_elis_abandoned_bakery_f1";
		}
		List<NPC> present = new ArrayList<>();
		Cell[][] cellGrid = Main.game.getWorlds().get(WorldType.getWorldTypeFromId(worldId)).getCellGrid();
		for(int i=0; i<cellGrid.length; i++) {
			for(int j=0; j<cellGrid[i].length; j++) {
				present.addAll(Main.game.getCharactersPresent(cellGrid[i][j]).stream().filter(
						npc->(npc instanceof ElisAlleywayAttacker || npc instanceof GenericSexualPartner)
							&& !npc.isSlave()
							&& !Main.game.getPlayer().getParty().contains(this)
						).collect(Collectors.toList()));
				Cell c = cellGrid[i][j];
				c.resetInventory();
			}
		}
		
		for(NPC npc : present) {
			Main.game.banishNPC(npc);
		}

		if(floor==1) {
			Main.game.getNpc(Sleip.class).returnToHome();
			Main.game.getNpc(Nir.class).returnToHome();
		}
	}
	
}
