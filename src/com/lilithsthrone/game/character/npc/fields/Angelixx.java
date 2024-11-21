package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
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
import com.lilithsthrone.utils.colours.Colour;
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
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.12")) {
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
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
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ONE_DISLIKE);
		}
		
		
		// Body:
		this.setAgeAppearanceAbsolute(18);
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
	public String getSpeechColour() {
		if(Main.game.isLightTheme()) {
			return PresetColour.BASE_YELLOW_LIGHT.toWebHexString();
		}
		return "#fafad8";
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
	
	private static List<String> diaryEntries = Util.newArrayListOfValues(
		"A cat-girl pushed me out of the way today and called me a 'silly little girl'."
			+ " Luckily my boys were nearby, and so I had them take her down an alley and double-team her."
			+ " I ordered them to cum all over her and then asked her who the silly one was now."
			+"<br/>(01/14)",
		"This evening I got hit on again at the bar. He was a big, handsome zebra-boy, and I kind of have a weakness for all horse-morphs, so I let him take me back to his grotty little apartment and rail me hard on his bed."
			+ " His fat fucking cock stretched me out so good, and after giving me a creampie he made me suck him off."
			+ " Fuck, I'm getting horny thinking about it... I think I'll call a slave to come and pound me on the bed..."
			+"<br/>(02/14)",
		"Saellatrix wants me to increase the number of kidnappings, but insists that they be loners who won't have friends or family to report their disappearance."
			+ " My gang's doing all they can, doesn't she know how hard it is?! At this rate I'll have to go back to seducing people in the alleyways, and that'll seriously eat into the time I get to spend with my boys..."
			+"<br/>(03/14)",
		"My good boys, Sleip and Nir, captured three refugees today! As a reward, I told them to come to my room at midnight, where I'd dressed up in their favourite lingerie and had a slave tie me to the bed."
			+ " Fuck, they pounded me so hard for the rest of the night, I can barely walk this morning..."
			+"<br/>(04/14)",
		"As I was teleporting the refugees to Saellatrix's shop, one of them called me a stupid little bitch."
			+ " Fuck, that pissed me off. I got my boys to double-penetrate her dirty little pussy while I carried on teleporting the others."
			+ " By the time I'd finished, the bitch's cunt was a gaping, cum-filled mess. I promised her that this was just a taste of her new life, before teleporting her too..."
			+"<br/>(05/14)",
		"I went to the bank today, and the asshole in the lobby told me to go to the back of the queue. I told him who the fuck I am, and he realised his mistake."
			+ " Too late! I got the stupid prick fired, and after making a potion that'd turn him into a big-titted, fertile horse-girl, I sent Sleip and Nir after him with instructions to leave 'her' as a naked, pregnant, cum-covered slut..."
			+"<br/>(06/14)",
		"That centaur who made me his cock-sleeve yesterday ended up getting me pregnant with triplets... Eugh..."
			+ " Horse-morphs are one thing, but pushing out demonic centaurs is really fucking tiring."
			+ " I'll get some Mother's Milk later and get it over and done with. It kind of ruins my whole look when I'm pregnant anyway..."
			+"<br/>(07/14)",
		"Mother sent Saellatrix to see me today. That bitch kept on telling me to increase the number of kidnappings."
			+ " She was so dominant and direct in her behaviour that I couldn't help but get turned on, and when she grew herself a fat cock, I couldn't resist!"
			+ " She pinned me down and fucked me raw while my boys just stood there and watched..."
			+"<br/>(08/14)",
		"Some of my gang members have disappeared... It's got to be the Enforcers who got them, or maybe it's a rival gang?"
			+ " Fuck, I'm not cut out for some kind of gang war! I'll seduce one of the Enforcers tonight and see what I can learn."
			+ " Perhaps that big, hunky horse-boy who I've seen doing patrols?"
			+"<br/>(09/14)",
		"Tonight I found that big, handsome horse-boy Enforcer I'd seen doing patrols. He was harder to seduce than I expected, and I had to fall back on telepathy to get him aroused."
			+ " Oh well, I got what I wanted in the end. He didn't have any information on who was responsible for attacking my gang, but I did get his massive, fat cock balls-deep in my hot little pussy..."
			+"<br/>(10/14)",
		"I got mugged in the alleyway today! A couple of dog-boys demanded flames from me, and one of them had an Enforcer's pistol!"
			+ " I know I could have beaten them, but I just couldn't move... After they took my flames, they said they'd give me their knots in exchange."
			+ " Fuck, that got me wet, and so I let them rail me doggy-style right there on the floor..."
			+"<br/>(11/14)",
		"I saw that assistant of Minotallys's today in the Farmer's Market. Alicorns really do it for me, so I ambushed him by the kissing booth stall and seduced him."
			+ " Sure, I had to use arcane arousal *and* telepathy, but I got him in the end. We found a quiet spot behind a nearby building, where he insisted on starting by eating me out."
			+ " He actually managed to get me to orgasm with just his tongue, and then a dozen or so more times when his fat horse-cock was balls-deep in my sloppy cunt..."
			+"<br/>(12/14)",
		"Mother called me back to Dominion today to see her, and I decided to take a carriage instead of teleporting."
			+ " The big, hunky centaur who was pulling it was called 'Vronti', and I tried everything to get him to stop and fuck me raw with his massive horse-cock."
			+ " I could barely get a word out of him, though. Fuck! I had a centaur rail me just now and I was thinking of Vronti the whole time..."
			+"<br/>(13/14)",
		"Nir recruited a new member into the gang today. I don't normally pay much attention to new recruits, but this one's a big, dumb brute of a donkey-boy."
			+ " I wanted him so badly that I let him pull me into that filthy bathroom at our HQ and fuck me hard up against the wall."
			+ " His fat cock felt as good as I'd hoped, but the dumb, horny idiot tore my favourite pair of panties in two in his haste to get to my pussy..."
			+"<br/>(14/14)"
		);

	private static Set<Integer> viewedDiaryIndexes = new HashSet<>();
	private static Colour[] diaryPageColour = new Colour[] {PresetColour.BASE_BLUE_LIGHT, PresetColour.BASE_ORANGE_LIGHT, PresetColour.BASE_RED_LIGHT, PresetColour.BASE_GREEN_LIME, PresetColour.BASE_PINK_LIGHT};
	
	/**
	 * Does not repeat entries until all are seen, then restarts.
	 */
	public String getDiaryEntry() {
		List<Integer> availableIndexes = new ArrayList<>();
		for(int i=0; i<diaryEntries.size(); i++) {
			availableIndexes.add(i);
		}
		for(Integer i : viewedDiaryIndexes) {
			availableIndexes.remove(i);
		}
		if(availableIndexes.isEmpty()) {
			viewedDiaryIndexes = new HashSet<>();
			return getDiaryEntry();
		}
		
		int index = Util.randomItemFrom(availableIndexes);
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_1 && viewedDiaryIndexes.isEmpty()) {
			index = 4; // Make sure that the 'evidence' entry is the first one to be seen when the player likely checks the diary out for the first time
		}
		viewedDiaryIndexes.add(index);
		
		Colour colour = diaryPageColour[index%diaryPageColour.length];
		
		return "<span style='color:"+colour.toWebHexString()+";'>"+diaryEntries.get(index)+"</spn>";
		
	}
}
