package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
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
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class SlimeGuardFire extends NPC {

	public SlimeGuardFire() {
		this(false);
	}
	
	public SlimeGuardFire(boolean isImported) {
		super(new NameTriplet("Blaze"), "[npc.Name] is one of the Slime Queen's guards, tasked to challenge anyone who dares to enter [npc.her] Queen's territory.",
				22, Month.JANUARY, 13,
				10, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10), WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_ENTRANCE_GUARDS, true);

		if(!isImported) {
			
			// RACE & NAME:
			this.setBodyMaterial(BodyMaterial.SLIME);
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);

			this.setPlayerKnowsName(true);
			
			// PERSONALITY & BACKGROUND:

			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			// ADDING FETISHES:
			
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			this.addFetish(Fetish.FETISH_INCEST);

			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			
			// BODY RANDOMISATION:
			
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME, Colour.SLIME_ORANGE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_EYE, Colour.SLIME_ORANGE), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_SCLERA, Colour.SLIME_ORANGE), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_PUPILS, Colour.SLIME_ORANGE), false);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_HAIR, Colour.SLIME_RED), false);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
			
			equipClothing(true, false);
			
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 45);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.setSkinCovering(new Covering(BodyCoveringType.SLIME_PUPILS, Colour.SLIME_ORANGE), false);
		this.clearNonEquippedInventory();
		equipClothing(true, false);
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
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		this.unequipAllClothingIntoVoid();

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(
				ClothingType.FINGER_RING,
				Colour.CLOTHING_COPPER,
				Util.newArrayListOfValues(
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_FIRE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_FIRE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_FIRE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_FIRE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_FIRE, TFPotency.MAJOR_BOOST, 0))),
				true,
				this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_SUN_STUD, Colour.CLOTHING_COPPER, false),
				true,
				this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_SUN, Colour.CLOTHING_COPPER, false),
				true,
				this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SUN_NECKLACE, Colour.CLOTHING_COPPER, false),
				true,
				this);
		
		if(this.getMainWeapon()==null) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_KNIGHTLY_SWORD, DamageType.FIRE));
		}
		if(this.getOffhandWeapon()==null) {
			this.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_BUCKLER, DamageType.FIRE));
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
	public DialogueNodeOld getEncounterDialogue() {
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

	@Override
	public Attack attackType() {
		boolean canCastASpecialAttack = !getSpecialAttacksAbleToUse().isEmpty();
		
		Map<Attack, Integer> attackWeightingMap = new HashMap<>();
		
		attackWeightingMap.put(Attack.MAIN, 100);
		attackWeightingMap.put(Attack.OFFHAND, this.getOffhandWeapon()==null?0:25);
		attackWeightingMap.put(Attack.SPECIAL_ATTACK, !canCastASpecialAttack?0:50);
		
		int total = 0;
		for(Entry<Attack, Integer> entry : attackWeightingMap.entrySet()) {
			total+=entry.getValue();
		}
		
		int index = Util.random.nextInt(total);
		total = 0;
		for(Entry<Attack, Integer> entry : attackWeightingMap.entrySet()) {
			total+=entry.getValue();
			if(index<total) {
				return entry.getKey();
			}
		}
		
		return Attack.MAIN;
	}
}
