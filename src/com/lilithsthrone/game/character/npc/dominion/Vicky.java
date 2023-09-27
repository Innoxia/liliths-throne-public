package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
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
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.1
 * @author Innoxia
 */
public class Vicky extends NPC {

	private Map<AbstractWeapon, Integer> weaponsForSale;
	private Map<AbstractItem, Integer> itemsForSale;
	private Map<AbstractClothing, Integer> clothingForSale;
	
	private AbstractItemType[] availableIngredients = new AbstractItemType[] {
			ItemType.getItemTypeFromId("innoxia_race_cat_kittys_reward"),
			ItemType.getItemTypeFromId("innoxia_race_dog_canine_crunch"),
			ItemType.getItemTypeFromId("innoxia_race_harpy_bubblegum_lollipop"),
			ItemType.getItemTypeFromId("innoxia_race_horse_sugar_carrot_cube"),
			ItemType.getItemTypeFromId("innoxia_race_wolf_meat_and_marrow"),
			ItemType.getItemTypeFromId("innoxia_race_squirrel_round_nuts"),
			ItemType.getItemTypeFromId("innoxia_race_cow_bubble_cream"),
			ItemType.getItemTypeFromId("innoxia_race_alligator_gators_gumbo"),
			ItemType.getItemTypeFromId("innoxia_race_reindeer_sugar_cookie"),
			ItemType.getItemTypeFromId("innoxia_race_human_bread_roll")};
	
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
				case SOOTHING_WATERS:
					availableSpellBooks.add(ItemType.getSpellBookType(s));
					break;
				case CLEANSE:
				case STEAL:
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
				case DARK_SIREN_SIRENS_CALL:
				case LIGHTNING_SPHERE_DISCHARGE:
				case LIGHTNING_SPHERE_OVERCHARGE:
				case ARCANE_CHAIN_LIGHTNING:
				case ARCANE_LIGHTNING_SUPERBOLT:
					break;
			}
		}
	}
	
	public Vicky() {
		this(false);
	}
	
	public Vicky(boolean isImported) {
		super(isImported, new NameTriplet("Vicky"), "Haugen",
				"Vicky is the owner of the shop 'Arcane Arts'. Her manner of staring at anyone who enters her shop is quite unsettling, and you feel as though she's ready to pounce on you at any moment...",
				37, Month.MAY, 26,
				10, Gender.F_P_V_B_FUTANARI,
				Subspecies.WOLF_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, true);

		weaponsForSale = new HashMap<>();
		itemsForSale = new HashMap<>();
		clothingForSale = new HashMap<>();
		if(!isImported) {
			dailyUpdate();
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 25);
		}
	}


	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element weaponsElement = doc.createElement("weaponsForSale");
		properties.appendChild(weaponsElement);
		for(Entry<AbstractWeapon, Integer> weapon : weaponsForSale.entrySet()) {
			try {
				Element e = weapon.getKey().saveAsXML(weaponsElement, doc);
				e.setAttribute("count", String.valueOf(weapon.getValue()));
			} catch(Exception ex) {
			}
		}

		Element itemsElement = doc.createElement("itemsForSale");
		properties.appendChild(itemsElement);
		for(Entry<AbstractItem, Integer> item : itemsForSale.entrySet()) {
			try {
				Element e = item.getKey().saveAsXML(itemsElement, doc);
				e.setAttribute("count", String.valueOf(item.getValue()));
			} catch(Exception ex) {
			}
		}

		Element clothingElement = doc.createElement("clothingForSale");
		properties.appendChild(clothingElement);
		for(Entry<AbstractClothing, Integer> clothing : clothingForSale.entrySet()) {
			try {
				Element e = clothing.getKey().saveAsXML(clothingElement, doc);
				e.setAttribute("count", String.valueOf(clothing.getValue()));
			} catch(Exception ex) {
			}
		}
		
		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12")) {
			setStartingBody(false);
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.3")) {
			this.dailyUpdate();
			
		} else {
			Element weaponsElement = (Element) parentElement.getElementsByTagName("weaponsForSale").item(0);
			if(weaponsElement!=null) {
				weaponsForSale.clear();
				
				NodeList nodeList = weaponsElement.getElementsByTagName("weapon");
				for(int i=0; i < nodeList.getLength(); i++){
					Element e = (Element) nodeList.item(i);
					try {
						AbstractWeapon weapon = AbstractWeapon.loadFromXML(e, doc);
						int count = 1;
						try {
							count = Integer.valueOf(e.getAttribute("count"));
						} catch(Exception ex) {
							weaponsForSale.putIfAbsent(weapon, 0);
							count = weaponsForSale.get(weapon)+1;
						}
						weaponsForSale.put(weapon, count);
					} catch(Exception ex) {
					}
				}
			}
			Element itemsElement = (Element) parentElement.getElementsByTagName("itemsForSale").item(0);
			if(itemsElement!=null) {
				itemsForSale.clear();
				
				NodeList nodeList = itemsElement.getElementsByTagName("item");
				for(int i=0; i < nodeList.getLength(); i++){
					Element e = (Element) nodeList.item(i);
					try {
						AbstractItem item = AbstractItem.loadFromXML(e, doc);
						int count = 1;
						try {
							count = Integer.valueOf(e.getAttribute("count"));
						} catch(Exception ex) {
							itemsForSale.putIfAbsent(item, 0);
							count = itemsForSale.get(item)+1;
						}
						itemsForSale.put(item, count);
					} catch(Exception ex) {
					}
				}
			}
			Element clothingElement = (Element) parentElement.getElementsByTagName("clothingForSale").item(0);
			if(clothingElement!=null) {
				clothingForSale.clear();
				
				NodeList nodeList = clothingElement.getElementsByTagName("clothing");
				for(int i=0; i < nodeList.getLength(); i++){
					Element e = (Element) nodeList.item(i);
					try {
						AbstractClothing clothing = AbstractClothing.loadFromXML(e, doc);
						int count = 1;
						try {
							count = Integer.valueOf(e.getAttribute("count"));
						} catch(Exception ex) {
							clothingForSale.putIfAbsent(clothing, 0);
							count = clothingForSale.get(clothing)+1;
							}
						clothingForSale.put(clothing, count);
					} catch(Exception ex) {
					}
				}
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.8")) {
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.1")) {
			this.setPenisCumStorage(150);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 5),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_NON_CON_DOM);
		}
		
		
		// Body:

		// Core:
		this.setHeight(175);
		this.setFemininity(85);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, PresetColour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, PresetColour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, PresetColour.COVERING_BLACK), true);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.LOOSE);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_BLACK, false), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_AMBER));
//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_AMBER));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisSize(22);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(150);
		this.fillCumToMaxStorage();
		this.addCumModifier(FluidModifier.MUSKY); // Wolf penis should give musky automatically, but as it's so crucial for Vicky's content it's added here to make sure
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_CORSET_DRESS, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.HIPS_SUSPENDER_BELT, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_thigh_high_boots", PresetColour.CLOTHING_BLACK, false), true, this);
	}

	public void equipUnderwear() {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_BLACK, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);
		itemsForSale.clear();
		weaponsForSale.clear();
		clothingForSale.clear();
		
		int requiredRoomForMiscItems = ItemType.getEssences().size()+SpellSchool.values().length+availableSpellBooks.size()+10;
		
		List<AbstractCoreType> types = new ArrayList<>();
		
		for(AbstractWeaponType wt : WeaponType.getAllWeapons()) {
			if(wt.getItemTags().contains(ItemTag.SOLD_BY_VICKY)
					&& (!wt.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				types.add(wt);
			}
		}
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_VICKY)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				types.add(item);
			}
		}
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			try {
				if(clothing!=null
						&& clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_VICKY)
						&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
					types.add(clothing);
				} 
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		Collections.shuffle(types);
		int count=0;
		for(AbstractCoreType type : types) {
			if(type instanceof AbstractWeaponType) {
				weaponsForSale.put(Main.game.getItemGen().generateWeapon((AbstractWeaponType) type), 2+Util.random.nextInt(5));
				
			} else if(type instanceof AbstractItemType) {
				itemsForSale.put(Main.game.getItemGen().generateItem((AbstractItemType) type), 2+Util.random.nextInt(5));
				
			} else if(type instanceof AbstractClothingType) {
				clothingForSale.put(Main.game.getItemGen().generateClothing((AbstractClothingType) type), 2+Util.random.nextInt(5));
			}
			count++;
			if(count>=this.getMaximumInventorySpace()-requiredRoomForMiscItems) {
				break;
			}
		}
		
		AbstractItem ingredient = Main.game.getItemGen().generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
		TFModifier primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		for(int i=0; i<10;i++) {
			try {
				if(ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0, Main.game.getPlayer(), Main.game.getPlayer())!=null) {
					AbstractItem potion = EnchantingUtils.craftItem(ingredient, Util.newArrayListOfValues(new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0)));
					itemsForSale.putIfAbsent(potion, 0);
					itemsForSale.put(potion, 1+itemsForSale.get(potion));
					potion.setName(EnchantingUtils.getPotionName(ingredient, potion.getEffects()));
				}
				
				ingredient = Main.game.getItemGen().generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
				primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
			} catch(Exception ex) {
			}
		}
		
		for(AbstractItemType itemType : availableSpellBooks) {
			itemsForSale.put(Main.game.getItemGen().generateItem(itemType), 1);
		}
		
		for(SpellSchool school : SpellSchool.values()) {
			AbstractItem item = Main.game.getItemGen().generateItem(ItemType.getSpellScrollType(school));
			itemsForSale.put(item, 10+Util.random.nextInt(20));
		}
		
//		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			for(AbstractItemType itemType : ItemType.getEssences()) {
				if (!itemType.getItemTags().contains(ItemTag.CONTRABAND_HEAVY)) {
					AbstractItem item = Main.game.getItemGen().generateItem(itemType);
					itemsForSale.put(item, 500+Util.random.nextInt(251));
				}
			}
//		}
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isWorkTime()) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		if(soldToPlayer) {
			int oldCount;
			if(weaponsForSale.containsKey(itemSold)) {
				oldCount = weaponsForSale.get(itemSold);
				if(oldCount > quantity) {
					weaponsForSale.put((AbstractWeapon) itemSold, oldCount-quantity);
				} else {
					weaponsForSale.remove((AbstractWeapon) itemSold);
				}
			}
			if(clothingForSale.containsKey(itemSold)) {
				oldCount = clothingForSale.get(itemSold);
				if(oldCount > quantity) {
					clothingForSale.put((AbstractClothing) itemSold, oldCount-quantity);
				} else {
					clothingForSale.remove((AbstractClothing) itemSold);
				}
			}
			if(itemsForSale.containsKey(itemSold)) {
				oldCount = itemsForSale.get(itemSold);
				if(oldCount > quantity) {
					itemsForSale.put((AbstractItem) itemSold, oldCount-quantity);
				} else {
					itemsForSale.remove((AbstractItem) itemSold);
				}
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
		if(item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY)) {
			return false;
		}
		
		if(item instanceof AbstractWeapon) {
			return true;
		}
		
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType().getItemTags().contains(ItemTag.ESSENCE)
					|| ((AbstractItem)item).getItemType().getItemTags().contains(ItemTag.SPELL_BOOK)
					|| ((AbstractItem)item).getItemType().getItemTags().contains(ItemTag.SPELL_SCROLL)
					|| ((AbstractItem)item).getItemType()==ItemType.POTION
					|| ((AbstractItem)item).getItemType()==ItemType.ELIXIR) {
				return true;
			}
		}
		
		return false;
	}

	public Map<AbstractWeapon, Integer> getWeaponsForSale() {
		return weaponsForSale;
	}

	public Map<AbstractItem, Integer> getItemsForSale() {
		return itemsForSale;
	}

	public Map<AbstractClothing, Integer> getClothingForSale() {
		return clothingForSale;
	}

	@Override
	public void endSex() {
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursPackageObtained, true);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE), false, true));
		}
	}
	
	// Sex:

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_ROUGH;
	}
	
	@Override
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest) {
		return true;
	}
	
	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		return slot==SexSlotDesk.BETWEEN_LEGS;
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			
		} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ASS);
		}
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			
		} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ASS);
		}
	}

}
