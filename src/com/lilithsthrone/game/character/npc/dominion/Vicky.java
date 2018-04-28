package com.lilithsthrone.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public class Vicky extends NPC {

	private static final long serialVersionUID = 1L;
	
	private AbstractItemType[] availableIngredients = new AbstractItemType[] {
			ItemType.RACE_INGREDIENT_CAT_MORPH,
			ItemType.RACE_INGREDIENT_DOG_MORPH,
			ItemType.RACE_INGREDIENT_HARPY,
			ItemType.RACE_INGREDIENT_HORSE_MORPH,
			ItemType.RACE_INGREDIENT_WOLF_MORPH,
			ItemType.RACE_INGREDIENT_SQUIRREL_MORPH,
			ItemType.RACE_INGREDIENT_COW_MORPH,
			ItemType.RACE_INGREDIENT_ALLIGATOR_MORPH,
			ItemType.RACE_INGREDIENT_REINDEER_MORPH,
			ItemType.RACE_INGREDIENT_HUMAN,
			ItemType.RACE_INGREDIENT_DEMON};
	
	private static List<AbstractItemType> availableSpellBooks = new ArrayList<>();
	
	static {
		for(Spell s : Spell.values()) {
			switch(s) {
				// Tier 1:
				case ARCANE_AROUSAL:
				case ICE_SHARD:
				case POISON_VAPOURS:
				case FIREBALL:
				case SLAM:
					availableSpellBooks.add(ItemType.getSpellBookType(s));
					break;
					
				// Tier 2:
				case ARCANE_CLOUD:
				case FLASH:
				case RAIN_CLOUD:
				case TELEKENETIC_SHOWER:
				case TELEPATHIC_COMMUNICATION:
				case VACUUM:
					availableSpellBooks.add(ItemType.getSpellBookType(s));
					break;
		
				// Tier 3:
				case STONE_SHELL:
				case PROTECTIVE_GUSTS:
				case CLOAK_OF_FLAMES:
					availableSpellBooks.add(ItemType.getSpellBookType(s));
					break;
				case CLEANSE:
				case STEAL:
				case SOOTHING_WATERS: // Special quest spells
					break;
					
				// Tier 4:
				case ELEMENTAL_AIR:
				case ELEMENTAL_ARCANE:
				case ELEMENTAL_EARTH:
				case ELEMENTAL_FIRE:
				case ELEMENTAL_WATER:
					availableSpellBooks.add(ItemType.getSpellBookType(s));
					break;
					
				// Tier 5: // Special quest spells
				case LILITHS_COMMAND:
				case TELEPORT:
					break;
					
				case WITCH_CHARM:
				case WITCH_SEAL:
					break;
			}
		}
	}
	
	public Vicky() {
		this(false);
	}
	
	public Vicky(boolean isImported) {
		super(new NameTriplet("Vicky"),
				"Vicky is the owner of the shop 'Arcane Arts'. Her manner of staring at anyone who enters her shop is quite unsettling, and you feel as though she's ready to pounce on you at any moment...", 10, Gender.F_P_V_B_FUTANARI,
				RacialBody.WOLF_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, Colour.EYE_YELLOW));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, Colour.COVERING_BLACK), true);
	
			this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
			this.setPenisVirgin(false);
			
			this.setVaginaVirgin(false);
			this.setVaginaCapacity(Capacity.FIVE_ROOMY.getMedianValue(), true);
			this.setBreastSize(CupSize.C.getMeasurement());
	
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_NON_CON_DOM);
			
			this.setMoney(10);

			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_CORSET_DRESS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HIPS_SUSPENDER_BELT, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_THIGH_HIGH_BOOTS, Colour.CLOTHING_BLACK, false), true, this);
		}
		//TODO import this:
		dailyReset();
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		this.unequipAllClothingIntoVoid();

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_CORSET_DRESS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HIPS_SUSPENDER_BELT, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_THIGH_HIGH_BOOTS, Colour.CLOTHING_BLACK, false), true, this);
		
		this.addFetish(Fetish.FETISH_DOMINANT);
		this.addFetish(Fetish.FETISH_ANAL_GIVING);
		this.addFetish(Fetish.FETISH_NON_CON_DOM);
		
		this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
		this.setPenisVirgin(false);
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public void dailyReset() {
		clearNonEquippedInventory();

		for(int i=0;i<2;i++){
			this.addWeapon(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_CHAOS_RARE), false);
			this.addWeapon(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE), false);
		}
		this.addWeapon(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_CHAOS_EPIC), false);
		this.addWeapon(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC), false);
		
		AbstractItem ingredient = AbstractItemType.generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
		TFModifier primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		for(int i=0; i<6;i++) {
			if(ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0, Main.game.getPlayer(), Main.game.getPlayer())!=null) {
				this.addItem(EnchantingUtils.craftItem(ingredient, Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0)))), false);
				
			}
			
			ingredient = AbstractItemType.generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
			primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		}
		
		for(AbstractItemType itemType : availableSpellBooks) {
			this.addItem(AbstractItemType.generateItem(itemType), false);
		}
		
		for(SpellSchool school : SpellSchool.values()) {
			for(int i=0; i<10+Util.random.nextInt(20); i++) {
				this.addItem(AbstractItemType.generateItem(ItemType.getSpellScrollType(school)), false);
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			for(AbstractItemType itemType : ItemType.essences) {
				for(int i=0;i<20+Util.random.nextInt(11);i++) {
					this.addItem(AbstractItemType.generateItem(itemType), false);
				}
			}
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public String getTraderDescription() {
		return "<p>"
					+ "You walk up to the counter, trying to ignore Vicky's piercing gaze and wolfish grin."
					+ " As you start to ask her about what sort of things she has in stock, she suddenly crouches down slightly, and you brace yourself as you half-expect her to leap over the counter and attack."
					+ " Instead, thankfully, she bends down and retrieves a few items, placing them before you on the countertop."
				+ "</p>"
				+ "<p>"
					+ UtilText.parseSpeech("This is what I've got today... I might have some new stuff tomorrow... You interested?", this) + " she asks, never taking her eyes off of you for a second."
				+ "</p>";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item instanceof AbstractWeapon)
			return true;
		
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType().getItemTags().contains(ItemTag.ESSENCE)
					|| ((AbstractItem)item).getItemType()==ItemType.POTION
					|| ((AbstractItem)item).getItemType()==ItemType.ELIXIR) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursPackageObtained, true);
				Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ARTHURS_PACKAGE), false, true));
			}
		}
	}
	
	// Sex:
	
	@Override
	public boolean getSexBehaviourDeniesRequests() {
		return false;
	}
	
	public Set<SexPositionSlot> getSexPositionPreferences() {
		sexPositionPreferences.clear();
		
		sexPositionPreferences.add(SexPositionSlot.MISSIONARY_DESK_DOM_VICKY);
		
		return sexPositionPreferences;
	}
	
	public SexType getForeplayPreference() {
		if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) && Sex.getPlayerPenetrationRequests().contains(OrificeType.ANUS)) {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS);
			
		} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina()) {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA);
			
		} else {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS);
		}
	}
	
	public SexType getMainSexPreference() {
		if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) && Sex.getPlayerPenetrationRequests().contains(OrificeType.ANUS)) {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS);
			
		} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina()) {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA);
			
		} else {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS);
		}
	}

}