package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
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
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.2.5
 * @version 0.4.2.5
 * @author Innoxia
 */
public class Imsu extends NPC {

	public Imsu() {
		this(false);
	}
	
	public Imsu(boolean isImported) {
		super(isImported, new NameTriplet("Imsu"), "Lisophiamartu",
				"Imsu is one of the two baphomets who operate the blacksmiths, 'The Ninth Strike', in Elis's shopping precinct."
						+ " While most of his work involves manufacturing or repairing mundane items and machinery, his real passion is creating melee weapons.",
				43, Month.FEBRUARY, 4,
				25,
				Gender.M_P_MALE, Subspecies.getSubspeciesFromId("innoxia_goat_subspecies_goat"), RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_shops"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_shops_blacksmith"),
				true);

		this.setGenericName("black-furred baphomet");
		
		if(!isImported) {
			dailyUpdate();
			this.setPlayerKnowsName(false);
			this.addSpell(Spell.FIREBALL);
			this.addSpell(Spell.FLASH);
			this.addSpell(Spell.SLAM);
			this.addSpell(Spell.STONE_SHELL);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.WEAPON_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);

			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_BREASTS_OTHERS);

			this.setFetishDesire(Fetish.FETISH_ARMPIT_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		
		// Body:
		this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, Gender.M_P_MALE, Subspecies.getSubspeciesFromId("innoxia_goat_subspecies_goat"), true, RaceStage.GREATER), false);
		this.setWingType(WingType.NONE);
		this.setHornType(HornType.CURLED);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());
		
		// Core:
		this.setHeight(186);
		this.setFemininity(5);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_goat_eye"), CoveringPattern.EYE_IRISES, PresetColour.EYE_RED, true, PresetColour.EYE_RED, true));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_RED, true, PresetColour.EYE_RED, true));
		this.setSkinCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_goat_fur"), CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_goat_hair"), PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.ZERO_BALD.getMinimumValue());
		this.setHairStyle(HairStyle.NONE);
		
		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_goat_body_hair"), PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_BLACK));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_BLACK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_CLEAR));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
//		this.setAssCapacity(Capacity.TWO_TIGHT, true);
//		this.setAssWetness(Wetness.ZERO_DRY);
//		this.setAssElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
//		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(24);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(250);
		this.setPenisCumExpulsion(75);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(5000);
		this.setEssenceCount(250);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_safety_glasses", PresetColour.CLOTHING_BLUE_NAVY, PresetColour.CLOTHING_BLUE_NAVY, PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_NAVY, PresetColour.CLOTHING_STEEL, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.GROIN_BOXERS, PresetColour.CLOTHING_GREEN_DRAB, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#d18484";
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);

		List<AbstractWeaponType> weaponTypesToSell = new ArrayList<>();
		List<AbstractClothingType> clothingTypesToSell = new ArrayList<>();
		List<AbstractItemType> itemTypesToSell = new ArrayList<>();

		// Weapons:
		for(AbstractWeaponType weapon : WeaponType.getAllWeapons()) {
			if(weapon.getItemTags().contains(ItemTag.SOLD_BY_VICKY)
					&& weapon.isMelee()
					&& (!weapon.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				weaponTypesToSell.add(weapon);
			}
		}
		// Limit number of weapon types to fit 80% of inventory size:
		while(weaponTypesToSell.size() >= this.getMaximumInventorySpace() * 0.8) {
			weaponTypesToSell.remove(Util.random.nextInt(weaponTypesToSell.size()));
		}
		for(AbstractWeaponType type : weaponTypesToSell) {
			this.addWeapon(Main.game.getItemGen().generateWeapon(type), 1+Util.random.nextInt(3), false, false);
		}

		// Items:
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_VICKY)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				itemTypesToSell.add(item);
			}
		}
		// Limit number of item types to fit 80% of inventory size:
		while(itemTypesToSell.size() >= this.getMaximumInventorySpace() * 0.8) {
			itemTypesToSell.remove(Util.random.nextInt(itemTypesToSell.size()));
		}
		for(AbstractItemType type : itemTypesToSell) {
			this.addItem(Main.game.getItemGen().generateItem(type), 1+Util.random.nextInt(3), false, false);
		}
		this.addItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), 5+Util.random.nextInt(6), false, false);
		
		// Clothing:
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_VICKY)
					&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				clothingTypesToSell.add(clothing);
			}
		}
		// Limit number of clothing types to 80% inventory size:
		while(clothingTypesToSell.size() >= this.getMaximumInventorySpace() * 0.8) {
			clothingTypesToSell.remove(Util.random.nextInt(clothingTypesToSell.size()));
		}
		for(AbstractClothingType type : clothingTypesToSell) {
			this.addClothing(Main.game.getItemGen().generateClothing(type, false), false);
		}
		
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(7, 20)) {
				this.returnToHome();
				
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("places/fields/elis/shops/blacksmith", "IMSU_TRANSACTION_START");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY)) {
			return false;
		}
		
		if(item instanceof AbstractWeapon) {
			return true;
		}
		
		if(item instanceof AbstractItem || item instanceof AbstractClothing) {
			return item.getItemTags().contains(ItemTag.SOLD_BY_VICKY);
		}
		
		return false;
	}

	@Override
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, true);
		UtilText.addSpecialParsingString(itemSold.getName(), true);
		UtilText.addSpecialParsingString(Util.intToString(quantity*individualPrice), false);
		
		if(soldToPlayer) {
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/shops/blacksmith", "IMSU_BUY_TRANSACTION_COMPLETE"));
		} else {
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/shops/blacksmith", "IMSU_SELL_TRANSACTION_COMPLETE"));
		}
	}
	
	@Override
	public void endSex() {
	}
	
}
