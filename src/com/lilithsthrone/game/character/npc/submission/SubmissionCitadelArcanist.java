package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
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
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.sexActions.submission.CitadelYoukoSA;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.3.4
 * @author Innoxia
 */
public class SubmissionCitadelArcanist extends NPC {

	public SubmissionCitadelArcanist() {
		this(false);
	}
	
	public SubmissionCitadelArcanist(boolean isImported) {
		super(isImported, new NameTriplet("Hitomi", "Hitomi", "Hitomi"), "Takahashi",
				"",
				23, Month.NOVEMBER, 27,
				20, Gender.F_V_B_FEMALE, Subspecies.FOX_ASCENDANT, RaceStage.PARTIAL_FULL,
				new CharacterInventory(10), WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_LAB, false);
		
		if(!isImported) {
			this.setPlayerKnowsName(false);

			//TODO illusion magic
			this.addSpell(Spell.ICE_SHARD);
			this.addSpellUpgrade(SpellUpgrade.ICE_SHARD_1);

			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.6")) {
			this.setStartingBody(true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.8")) {
			this.setLevel(20);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.COWARDLY,
					PersonalityTrait.SELFISH);
		}
		setStartingCombatMoves();
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.5")) {
			this.setTailCount(3, true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.WEAPON_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 5)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.COWARDLY,
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_ARCANE_RESEARCHER);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_DENIAL);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.ZERO_HATE);
		}
		
		// Body:
		this.setTailCount(3, true);

		// Core:
		this.setHeight(172);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:

		// Allow to be randomised:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FOX_MORPH, Colour.EYE_LILAC));
		this.setSkinCovering(new Covering(BodyCoveringType.FOX_FUR, Colour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_PALE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FOX_FUR, Colour.COVERING_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMinimumValue());
		this.setHairStyle(HairStyle.HIME_CUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, Colour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_BLACK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_BLACK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		// Anus settings and modifiers
		
		// Penis:
		// n/a
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
		// Foot shape
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_japaneseSwords_wakizashi"), DamageType.ICE));
		}
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.KIMONO_HAIR_KANZASHI, Colour.CLOTHING_WHITE, Colour.CLOTHING_PURPLE_VERY_DARK, Colour.CLOTHING_WHITE, false), true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_VIRGIN_KILLER_SWEATER, Colour.CLOTHING_WHITE, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_socks", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_platform_boots", Colour.CLOTHING_BLACK, false), true, this);
		
//		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_BLACK, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SCIENTIST_TORSO_OVER_LAB_COAT, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SCIENTIST_EYES_SAFETY_GOGGLES, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_SNOW_FLAKES, Colour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public String getDescription() {
		if(ImpCitadelDialogue.isDefeated() || ImpCitadelDialogue.isImpsDefeated()) {
			return UtilText.parse(this,
					"[npc.Name] is a rude, three-tailed youko, who treats others with very little respect."
							+ " At the first sign of trouble in the citadel, she vanished, leaving nothing but an empty laboratory behind."
							+ " You get the feeling that someday your paths will cross again...");
		}
		
		return UtilText.parse(this,
				"[npc.Name] is a rude, three-tailed youko, who treats others with very little respect."
						+ " Placed in charge of the Dark Siren's laboratory, she oversees the production of transformative potions.");
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void endSex() {
		this.replaceAllClothing();
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(CitadelYoukoSA.class);
	}

	@Override
	public boolean isClothingStealable() {
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

	// TF potion:
	
	@SuppressWarnings("fallthrough")
	@Override
	public Value<AbstractItemType, Map<ItemEffect, String>> generateTransformativePotion(GameCharacter target) {
		
		AbstractItemType itemType = ItemType.RACE_INGREDIENT_FOX_MORPH;
		
		Map<ItemEffect, String> effects = new HashMap<>();
		
		Map<ItemEffect, String> minimumEffects = new HashMap<>();
		Map<ItemEffect, String> reducedEffects = new HashMap<>();
		Map<ItemEffect, String> maximumEffects = new HashMap<>();
		
		switch(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(Subspecies.FOX_MORPH)) {
			case MAXIMUM:
			case NORMAL:
				maximumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_SKIN, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
				if(Main.getProperties().multiBreasts>0) {
					if(target.getBreastRows()<3) {
						maximumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_COUNT, TFPotency.MINOR_BOOST, 1), "");
					}
					if(target.getBreastRows()<2) {
						maximumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_COUNT, TFPotency.MINOR_BOOST, 1), "");
					}
				}
				maximumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
//				break;
			case REDUCED:
				reducedEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
				reducedEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
				reducedEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_LEGS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
				reducedEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ARMS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
//				break;
				
			case MINIMUM:
				minimumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_EARS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
				minimumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_EYES, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
				minimumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_TAIL, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
				minimumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), "");
				minimumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HORNS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), "");
//				minimumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ANTENNA, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), "");
				minimumEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_WINGS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), "");
//				break;
			case HUMAN:
				break;
		}
		
		for(Entry<ItemEffect, String> e : minimumEffects.entrySet()) {
			effects.put(e.getKey(), e.getValue());
		}
		
		for(Entry<ItemEffect, String> e : getFeminineEffects(target, itemType).entrySet()) {
			effects.put(e.getKey(), e.getValue());
		}
		
		// Remove penis:
		effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), "");
		
		// Add wet vagina:
		effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), "");
		effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), "");
		effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), "");
		effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), "");

		for(Entry<ItemEffect, String> e : reducedEffects.entrySet()) {
			effects.put(e.getKey(), e.getValue());
		}
		for(Entry<ItemEffect, String> e : maximumEffects.entrySet()) {
			effects.put(e.getKey(), e.getValue());
		}
		
		return new Value<>(itemType, effects);
	}
	
	private static Map<ItemEffect, String> getFeminineEffects(GameCharacter target, AbstractItemType itemType) {
		Map<ItemEffect, String> effects = new HashMap<>();
		
		for(int i=target.getFemininityValue(); i<Femininity.FEMININE.getMinimumFemininity(); i+=15) { // Turn feminine:
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1), "");
		}
		if(target.getMuscleValue()>Muscle.THREE_MUSCULAR.getMedianValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1), "");
		}
		if(target.getBodySizeValue()>BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_DRAIN, 1), "");
		}
		for(int i=target.getBreastSize().getMeasurement(); i<CupSize.C.getMeasurement(); i+=3) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), "");
		}
		if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1), "");
		}
		if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), "");
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i<HairLength.THREE_SHOULDER_LENGTH.getMaximumValue(); i+=15) {
				effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), "");
			}
		}
		for(int i=target.getLipSizeValue(); i<LipSize.TWO_FULL.getValue(); i+=2) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), "");
		}
		
		return effects;
	}
}
