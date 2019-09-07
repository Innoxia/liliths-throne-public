package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
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
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.SlimeQueensLair;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.4
 * @author Innoxia
 */
public class SlimeGuardIce extends NPC {

	public SlimeGuardIce() {
		this(false);
	}
	
	public SlimeGuardIce(boolean isImported) {
		super(isImported, new NameTriplet("Crystal"), "Triggs",
				"[npc.Name] is one of the Slime Queen's guards, tasked to challenge anyone who dares to enter [npc.her] Queen's territory.",
				19, Month.JANUARY, 29,
				15, Gender.F_V_B_FEMALE, Subspecies.SLIME, RaceStage.HUMAN,
				new CharacterInventory(10), WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_ENTRANCE_GUARDS, true);

	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12") && Subspecies.getFleshSubspecies(this)!=Subspecies.HUMAN) {
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.SLIME, RaceStage.HUMAN);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.8")) {
			this.setLevel(15);
			this.resetPerksMap(true);
		}
		setStartingCombatMoves();
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.CLOTHING_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}
	
	@Override
	public void setStartingCombatMoves() {
		this.clearEquippedMoves();
		this.equipMove("strike");
		this.equipMove("tease");
		this.equipMove("block");
		this.equipMove("avert");
		this.equipAllKnownMoves();
		this.equipAllSpellMoves();
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.LOW)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_SLIME_QUEEN_GUARD);
	
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			this.addFetish(Fetish.FETISH_INCEST);
			
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
		}
		
		
		// Body:

		// Core:
		this.setHeight(169);
		this.setFemininity(80);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME, Colour.SLIME_BLUE), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_EYE, Colour.SLIME_BLUE), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_SCLERA, Colour.SLIME_BLUE_LIGHT), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_PUPILS, Colour.SLIME_BLUE_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_ANUS, CoveringPattern.ORIFICE_ANUS, Colour.SLIME_BLUE_DARK, false, Colour.SLIME_BLUE_DARK, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_HAIR, Colour.SLIME_BLUE_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_MOUTH, CoveringPattern.ORIFICE_MOUTH, Colour.SLIME_BLUE_DARK, false, Colour.SLIME_BLUE_DARK, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_NIPPLES, Colour.SLIME_BLUE_DARK), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_VAGINA, CoveringPattern.ORIFICE_VAGINA, Colour.SLIME_BLUE_DARK, false, Colour.SLIME_BLUE_DARK, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_PENIS, CoveringPattern.NONE, Colour.SLIME_BLUE_DARK, false, Colour.SLIME_BLUE_DARK, true), false);

		this.setHairCovering(new Covering(BodyCoveringType.SLIME_HAIR, Colour.SLIME_BLUE_DARK), false);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.PIXIE);

		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceElasticity(OrificeElasticity.SEVEN_ELASTIC);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.NARROW);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.FIVE_ROOMY, true);
		this.setAssWetness(Wetness.SEVEN_DROOLING);
		this.setAssElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);
		
		this.setMoney(0);
		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
		CharacterUtils.generateItemsInInventory(this);
		
		this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon("innoxia_europeanSwords_arming_sword", DamageType.ICE));
		this.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon("innoxia_buckler_buckler", DamageType.ICE));
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(
				ClothingType.FINGER_RING,
				Colour.CLOTHING_SILVER,
				Util.newArrayListOfValues(
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_ICE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_ICE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_ICE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_ICE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_ICE, TFPotency.MAJOR_BOOST, 0))),
				true,
				this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_SNOWFLAKE_STUD, Colour.CLOTHING_SILVER, false),
				true,
				this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_SNOW_FLAKES, Colour.CLOTHING_SILVER, false),
				true,
				this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SNOWFLAKE_NECKLACE, Colour.CLOTHING_SILVER, false),
				true,
				this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
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

	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("Victorious", "[slimeFire.Name] and [slimeIce.name] are defeated!", SlimeQueensLair.SLIME_GUARDS_COMBAT_PLAYER_VICTORY) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeGuardsDefeated, true);
				}
			};
		} else {
			return new Response ("Defeated", "[slimeFire.Name] and [slimeIce.name] have defeated you!", SlimeQueensLair.SLIME_GUARDS_COMBAT_PLAYER_DEFEAT);
		}
	}

}
