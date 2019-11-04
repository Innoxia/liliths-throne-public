package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
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
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
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
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Shadow extends NPC {

	public Shadow() {
		this(false);
	}
	
	public Shadow(boolean isImported) {
		super(isImported, new NameTriplet("Shadow"), "Werlock",
				"",
				23, Month.DECEMBER, 29,
				15, Gender.F_V_B_FEMALE, Subspecies.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, true);
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(Perk.BESERK),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.BRAVE,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_GANG_BODY_GUARD);

			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
		}
		
		// Body:
		// Core:
		this.setHeight(173);
		this.setFemininity(80);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_RAT, Colour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, Colour.COVERING_JET_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, Colour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_EBONY), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, Colour.COVERING_JET_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()+5);
		this.setHairStyle(HairStyle.PIXIE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, Colour.COVERING_JET_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_GREEN_DARK));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_GREEN_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_PINK_LIGHT));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK), true);
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PINK), true);
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.TWO_BIG.getValue());
		this.setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL.getValue());
		this.setHipSize(HipSize.THREE_GIRLY.getValue());
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
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			this.addTattoo(InventorySlot.WRIST, new Tattoo(TattooType.getTattooTypeFromId("innoxia_gang_rat_skull"), Colour.CLOTHING_GREEN, Colour.CLOTHING_GREEN, Colour.CLOTHING_GREEN, true, null, null));
		}
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon("innoxia_kerambit_kerambit", DamageType.POISON, Colour.CLOTHING_BLACK, Colour.CLOTHING_STEEL));
			this.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon("innoxia_kerambit_kerambit", DamageType.POISON, Colour.CLOTHING_BLACK, Colour.CLOTHING_STEEL));
		}

		this.setPiercedEar(true);
		this.setPiercedNose(true);
		this.setPiercedVagina(true);
		this.setPiercedNavel(true);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_HOOPS, Colour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_VAGINA_BARBELL_RING, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_SILVER, false), true, this);
		
		AbstractClothing ring = AbstractClothingType.generateClothing("innoxia_finger_gemstone_ring", Colour.CLOTHING_BLACK_STEEL, Colour.CLOTHING_GREEN_DARK, null, false);
		
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MINOR_BOOST, 0));
		
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MINOR_BOOST, 0));

		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.BOOST, 0));
		
		ring.setName("Shadow's Kiss");
		
		this.equipClothingFromNowhere(ring, true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_HOTPANTS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_TUBE_TOP, Colour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_hand_fishnet_gloves", Colour.CLOTHING_GREEN_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_fishnets", Colour.CLOTHING_GREEN_VERY_DARK, false), true, this);
		
	}

	@Override
	public String getDescription() {
		return "";//TODO
	}
	
	@Override
	public boolean isUnique() {
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
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this) && !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
			if(!Main.game.isExtendedWorkTime() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
				this.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_BEDROOM);
			} else {
				this.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
			}
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated(){
		return true;
	}

	@Override
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK;
	}
}