package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
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
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
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
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.2
 * @author Innoxia
 */
public class Vicky extends NPC {

	private List<AbstractWeapon> weaponsForSale;
	private List<AbstractItem> itemsForSale;
	private List<AbstractClothing> clothingForSale;
	
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
			ItemType.RACE_INGREDIENT_HUMAN};
	
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

		weaponsForSale = new ArrayList<>();
		itemsForSale = new ArrayList<>();
		clothingForSale = new ArrayList<>();
		if(!isImported) {
			dailyReset();
		}
	}


	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element weaponsElement = doc.createElement("weaponsForSale");
		properties.appendChild(weaponsElement);
		for(AbstractWeapon weapon : weaponsForSale) {
			try {
				weapon.saveAsXML(weaponsElement, doc);
			} catch(Exception ex) {
			}
		}

		Element itemsElement = doc.createElement("itemsForSale");
		properties.appendChild(itemsElement);
		for(AbstractItem item : itemsForSale) {
			try {
				item.saveAsXML(itemsElement, doc);
			} catch(Exception ex) {
			}
		}

		Element clothingElement = doc.createElement("clothingForSale");
		properties.appendChild(clothingElement);
		for(AbstractClothing clothing : clothingForSale) {
			try {
				clothing.saveAsXML(clothingElement, doc);
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
			this.dailyReset();
			
		} else {
			Element weaponsElement = (Element) parentElement.getElementsByTagName("weaponsForSale").item(0);
			if(weaponsElement!=null) {
				weaponsForSale.clear();
				
				NodeList nodeList = weaponsElement.getElementsByTagName("weapon");
				for(int i=0; i < nodeList.getLength(); i++){
					Element e = (Element) nodeList.item(i);
					try {
						weaponsForSale.add(AbstractWeapon.loadFromXML(e, doc));
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
						itemsForSale.add(AbstractItem.loadFromXML(e, doc));
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
						clothingForSale.add(AbstractClothing.loadFromXML(e, doc));
					} catch(Exception ex) {
					}
				}
			}
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 25);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 60);
	
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
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
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, Colour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, Colour.COVERING_BLACK), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_BLACK), true);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.LOOSE);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_BLACK, false), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_AMBER));
//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_AMBER));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
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
		this.setPenisCumStorage(65);
		this.fillCumToMaxStorage();
		
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
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		
		this.unequipAllClothingIntoVoid(true);

		this.setMoney(10);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_CORSET_DRESS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HIPS_SUSPENDER_BELT, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_thigh_high_boots", Colour.CLOTHING_BLACK, false), true, this);

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
	public void dailyReset() {
		clearNonEquippedInventory();

		int requiredRoomForMiscItems = ItemType.getEssences().size()+SpellSchool.values().length+availableSpellBooks.size()+10;
		
		List<AbstractCoreType> types = new ArrayList<>();
		
		for(AbstractWeaponType wt : WeaponType.getAllweapons()) {
			if(wt.getItemTags().contains(ItemTag.SOLD_BY_VICKY)) {
				types.add(wt);
			}
		}
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_VICKY)) {
				types.add(item);
			}
		}
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			try {
				if(clothing!=null && clothing.getItemTags().contains(ItemTag.SOLD_BY_VICKY)) {
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
				for(int i=0; i<1+Util.random.nextInt(3); i++){
					weaponsForSale.add(AbstractWeaponType.generateWeapon((AbstractWeaponType) type));
				}
				
			} else if(type instanceof AbstractItemType) {
				itemsForSale.add(AbstractItemType.generateItem((AbstractItemType) type));
				
			} else if(type instanceof AbstractClothingType) {
				clothingForSale.add(AbstractClothingType.generateClothing((AbstractClothingType) type));
			}
			count++;
			if(count>=this.getMaximumInventorySpace()-requiredRoomForMiscItems) {
				break;
			}
		}
		
		AbstractItem ingredient = AbstractItemType.generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
		TFModifier primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		for(int i=0; i<10;i++) {
			try {
				if(ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0, Main.game.getPlayer(), Main.game.getPlayer())!=null) {
					AbstractItem potion = EnchantingUtils.craftItem(ingredient, Util.newArrayListOfValues(new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0)));
					itemsForSale.add(potion);
					potion.setName(EnchantingUtils.getPotionName(ingredient, potion.getEffects()));
				}
				
				ingredient = AbstractItemType.generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
				primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
			} catch(Exception ex) {
			}
		}
		
		for(AbstractItemType itemType : availableSpellBooks) {
			itemsForSale.add(AbstractItemType.generateItem(itemType));
		}
		
		for(SpellSchool school : SpellSchool.values()) {
			AbstractItem item = AbstractItemType.generateItem(ItemType.getSpellScrollType(school));
			for(int i=0; i<10+Util.random.nextInt(20);i++) {
				itemsForSale.add(item);
			}
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			for(AbstractItemType itemType : ItemType.getEssences()) {
				AbstractItem item = AbstractItemType.generateItem(itemType);
				for(int i=0; i<+Util.random.nextInt(11);i++) {
					itemsForSale.add(item);
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
		if(item instanceof AbstractWeapon)
			return true;
		
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

	public List<AbstractWeapon> getWeaponsForSale() {
		return weaponsForSale;
	}

	public List<AbstractItem> getItemsForSale() {
		return itemsForSale;
	}

	public List<AbstractClothing> getClothingForSale() {
		return clothingForSale;
	}

	@Override
	public void endSex() {
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursPackageObtained, true);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ARTHURS_PACKAGE), false, true));
		}
	}
	
	// Sex:
	
	@Override
	public boolean getSexBehaviourDeniesRequests(SexType sexTypeRequest) {
		return true;
	}
	
	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		return slot==SexSlotBipeds.MISSIONARY_DESK_DOM;
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			
		} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
		}
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			
		} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
		}
	}

}