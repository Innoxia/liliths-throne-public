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
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
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
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.submission.SlimeQueensLair;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
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
public class SlimeRoyalGuard extends NPC {

	public SlimeRoyalGuard() {
		this(false);
	}
	
	public SlimeRoyalGuard(boolean isImported) {
		super(new NameTriplet("Maximilian", "Max", "Maxine"), "An incredibly rare and powerful demonic slime, [npc.name] prides [npc.herself] on [npc.her] skill with the sword.",
				35, Month.APRIL, 14,
				20, Gender.M_P_MALE, RacialBody.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SLIME_QUEENS_LAIR_FIRST_FLOOR, PlaceType.SLIME_QUEENS_LAIR_ROYAL_GUARD, true);

		if(!isImported) {
			// RACE & NAME:
			this.setBodyMaterial(BodyMaterial.SLIME);
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setPlayerKnowsName(true);
			
			// PERSONALITY & BACKGROUND:

			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			// ADDING FETISHES:
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_IMPREGNATION);

			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.ONE_DISLIKE);
			
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ZERO_HATE);
			
			// BODY:
			
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.FOUR_HUGE.getMedianValue());
			this.setHeight(205);

			this.setSkinCovering(new Covering(BodyCoveringType.SLIME, Colour.SLIME_PURPLE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_EYE, Colour.SLIME_PURPLE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_SCLERA, Colour.SLIME_PURPLE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.SLIME_HAIR, Colour.SLIME_PURPLE_DARK), true);
			
			this.setPenisVirgin(false);
			this.setPenisGirth(PenisGirth.FOUR_FAT.getValue());
			this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
			this.setPenisCumStorage(CumProduction.FOUR_LARGE.getMedianValue());
			this.fillCumToMaxStorage();
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
			
			equipClothing(true, false);
			
			this.addSpell(Spell.SLAM);
			this.addSpell(Spell.TELEKENETIC_SHOWER);
			
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 60);
			this.setAttribute(Attribute.MAJOR_ARCANE, 20);
			this.setAttribute(Attribute.DAMAGE_MELEE_WEAPON, 50);
			this.setAttribute(Attribute.RESISTANCE_LUST, 50);
			this.setAttribute(Attribute.CRITICAL_CHANCE, 15);
			this.setAttribute(Attribute.CRITICAL_DAMAGE, 250);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
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
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_CROTCHLESS_CHAPS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.STOMACH_SARASHI, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HAND_WRAPS, Colour.CLOTHING_BLACK, false), true, this);
		
		if(this.getMainWeapon()==null) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_ZWEIHANDER, DamageType.PHYSICAL));
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
		return null; //TODO
	}

	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("Victorious", "[slimeRoyalGuard.Name] is defeated!", SlimeQueensLair.SLIME_ROYAL_GUARD_COMBAT_PLAYER_VICTORY) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slimeRoyalGuardDefeated, true);
				}
			};
		} else {
			return new Response ("Defeated", "[slimeRoyalGuard.name] has defeated you!", SlimeQueensLair.SLIME_ROYAL_GUARD_COMBAT_PLAYER_DEFEAT);
		}
	}
	
	@Override
	public Attack attackType() {
		boolean canCastASpell = !getSpellsAbleToCast().isEmpty();
		
		Map<Attack, Integer> attackWeightingMap = new HashMap<>();
		
		attackWeightingMap.put(Attack.MAIN, 100);
		attackWeightingMap.put(Attack.SPELL, !canCastASpell?0:25);
		
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
