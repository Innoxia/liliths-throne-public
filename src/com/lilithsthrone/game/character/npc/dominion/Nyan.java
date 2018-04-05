package com.lilithsthrone.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.0
 * @author Innoxia
 */
public class Nyan extends NPC {

	private static final long serialVersionUID = 1L;

	private List<AbstractClothing> commonFemaleClothing, commonFemaleUnderwear, commonFemaleOtherLingerie, commonFemaleAccessories,
									commonMaleClothing, commonMaleLingerie, commonMaleAccessories,
									commonAndrogynousClothing, commonAndrogynousLingerie, commonAndrogynousAccessories,
									specials;

	public Nyan() {
		this(false);
	}
	
	public Nyan(boolean isImported) {
		super(new NameTriplet("Nyan"), "Nyan is the owner of the store 'Nyan's Clothing Emporium', found in Dominion's shopping arcade."
				+ " She's extremely shy, and gets very nervous when having to talk to people.",
				10, Gender.F_V_B_FEMALE, RacialBody.CAT_MORPH, RaceStage.LESSER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, true,
				Util.newArrayListOfValues(new ListValue<>(new Artwork("nyan", Artist.JAM))));

		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_BLUE));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLACK), true);
			this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			this.setHairStyle(HairStyle.LOOSE);
			
			this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
	
			this.setBreastSize(CupSize.B.getMeasurement());
	
			this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
			this.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
			
			this.setMoney(10);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_PENCIL_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TRAINER_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false), true, this);

			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
		
		commonFemaleClothing = new ArrayList<>();
		commonFemaleUnderwear = new ArrayList<>();
		commonFemaleOtherLingerie = new ArrayList<>();
		commonFemaleAccessories = new ArrayList<>();
		commonMaleClothing = new ArrayList<>();
		commonMaleLingerie = new ArrayList<>();
		commonMaleAccessories = new ArrayList<>();
		commonAndrogynousClothing = new ArrayList<>();
		commonAndrogynousLingerie = new ArrayList<>();
		commonAndrogynousAccessories = new ArrayList<>();
		specials = new ArrayList<>();
		dailyReset();
	}
	
	private Map<String, List<AbstractClothing>> getAllClothingListsMap() {
		return Util.newHashMapOfValues(
				new Value<>("commonFemaleClothing", commonFemaleClothing),
				new Value<>("commonFemaleUnderwear", commonFemaleUnderwear),
				new Value<>("commonFemaleOtherLingerie", commonFemaleOtherLingerie),
				new Value<>("commonFemaleAccessories", commonFemaleAccessories),
				new Value<>("commonMaleClothing", commonMaleClothing),
				new Value<>("commonMaleLingerie", commonMaleLingerie),
				new Value<>("commonMaleAccessories", commonMaleAccessories),
				new Value<>("commonAndrogynousClothing", commonAndrogynousClothing),
				new Value<>("commonAndrogynousLingerie", commonAndrogynousLingerie),
				new Value<>("commonAndrogynousAccessories", commonAndrogynousAccessories),
				new Value<>("specials", specials));
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		for(Entry<String, List<AbstractClothing>> entry : getAllClothingListsMap().entrySet()) {
			Element clothingElement = doc.createElement(entry.getKey());
			properties.appendChild(clothingElement);
			for(AbstractClothing c : entry.getValue()) {
				c.saveAsXML(clothingElement, doc);
			}
		}
		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLACK), true);
		
		this.unequipAllClothingIntoVoid();
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_PENCIL_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TRAINER_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false), true, this);
		
		this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
		this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ONE_DISLIKE);
		this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		
		for(Entry<String, List<AbstractClothing>> entry : this.getAllClothingListsMap().entrySet()) {
			Element npcSpecificElement = (Element) parentElement.getElementsByTagName(entry.getKey()).item(0);
			if(npcSpecificElement!=null) {
				entry.getValue().clear();
				
				for(int i=0; i<npcSpecificElement.getElementsByTagName("clothing").getLength(); i++){
					Element e = (Element) npcSpecificElement.getElementsByTagName("clothing").item(i);
					entry.getValue().add(AbstractClothing.loadFromXML(e, doc));
				}
				
			}
		}
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public void dailyReset() {
		clearNonEquippedInventory();
		
		Main.game.getDialogueFlags().resetNyanActions();
		
		// Changing old versions of Nyan:
		if(this.getClothingInSlot(InventorySlot.LEG)!=null && this.getClothingInSlot(InventorySlot.LEG).getClothingType().getId()==ClothingType.LEG_PENCIL_SKIRT.getId()) {
			this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.LEG), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
		}
		
		commonFemaleClothing.clear();
		commonFemaleUnderwear.clear();
		commonFemaleOtherLingerie.clear();
		commonFemaleAccessories.clear();
		
		commonMaleClothing.clear();
		commonMaleLingerie.clear();
		commonMaleAccessories.clear();
		
		commonAndrogynousClothing.clear();
		commonAndrogynousLingerie.clear();
		commonAndrogynousAccessories.clear();
		
		specials.clear();

		boolean enchantedGear = Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP);
		
		// Female:
		for(AbstractClothingType ct : ClothingType.getCommonFemaleClothing()) {
			commonFemaleClothing.add(AbstractClothingType.generateClothing(ct, false));
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonFemaleClothing.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonFemaleClothing().get(Util.random.nextInt(ClothingType.getCommonFemaleClothing().size()))));
			}
			commonFemaleClothing.add(generateRareClothing(ClothingType.getCommonFemaleClothing().get(Util.random.nextInt(ClothingType.getCommonFemaleClothing().size()))));
		}
		
		for(AbstractClothingType ct : ClothingType.getCommonFemaleLingerie()) {
			if(ct.getSlot() == InventorySlot.GROIN) {
				commonFemaleUnderwear.add(AbstractClothingType.generateClothing(ct, false));
			} else {
				commonFemaleOtherLingerie.add(AbstractClothingType.generateClothing(ct, false));
			}
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonFemaleUnderwear.add(AbstractClothingType.generateClothingWithEnchantment(commonFemaleUnderwear.get(Util.random.nextInt(commonFemaleUnderwear.size())).getClothingType()));
				commonFemaleOtherLingerie.add(AbstractClothingType.generateClothingWithEnchantment(commonFemaleOtherLingerie.get(Util.random.nextInt(commonFemaleOtherLingerie.size())).getClothingType()));
			}
			commonFemaleUnderwear.add(generateRareClothing(commonFemaleUnderwear.get(Util.random.nextInt(commonFemaleUnderwear.size())).getClothingType()));
			commonFemaleOtherLingerie.add(generateRareClothing(commonFemaleOtherLingerie.get(Util.random.nextInt(commonFemaleOtherLingerie.size())).getClothingType()));
		}
		
		for(AbstractClothingType ct : ClothingType.getCommonFemaleAccessories()) {
			commonFemaleAccessories.add(AbstractClothingType.generateClothing(ct, false));
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonFemaleAccessories.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonFemaleAccessories().get(Util.random.nextInt(ClothingType.getCommonFemaleAccessories().size()))));
			}
			commonFemaleAccessories.add(generateRareClothing(ClothingType.getCommonFemaleAccessories().get(Util.random.nextInt(ClothingType.getCommonFemaleAccessories().size()))));
		}

		// Male:
		for(AbstractClothingType ct : ClothingType.getCommonMaleClothing()) {
			commonMaleClothing.add(AbstractClothingType.generateClothing(ct, false));
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonMaleClothing.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleClothing().get(Util.random.nextInt(ClothingType.getCommonMaleClothing().size()))));
			}
			commonMaleClothing.add(generateRareClothing(ClothingType.getCommonMaleClothing().get(Util.random.nextInt(ClothingType.getCommonMaleClothing().size()))));
		}
		
		for(AbstractClothingType ct : ClothingType.getCommonMaleLingerie()) {
			commonMaleLingerie.add(AbstractClothingType.generateClothing(ct, false));
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonMaleLingerie.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleLingerie().get(Util.random.nextInt(ClothingType.getCommonMaleLingerie().size()))));
			}
			commonMaleLingerie.add(generateRareClothing(ClothingType.getCommonMaleLingerie().get(Util.random.nextInt(ClothingType.getCommonMaleLingerie().size()))));
		}
		
		for(AbstractClothingType ct : ClothingType.getCommonMaleAccessories()) {
			commonMaleAccessories.add(AbstractClothingType.generateClothing(ct, false));
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonMaleAccessories.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleAccessories().get(Util.random.nextInt(ClothingType.getCommonMaleAccessories().size()))));
			}
			commonMaleAccessories.add(generateRareClothing(ClothingType.getCommonMaleAccessories().get(Util.random.nextInt(ClothingType.getCommonMaleAccessories().size()))));
		}

		// Androgynous:
		for(AbstractClothingType ct : ClothingType.getCommonAndrogynousClothing()) {
			commonAndrogynousClothing.add(AbstractClothingType.generateClothing(ct, false));
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonAndrogynousClothing.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousClothing().get(Util.random.nextInt(ClothingType.getCommonAndrogynousClothing().size()))));
			}
			commonAndrogynousClothing.add(generateRareClothing(ClothingType.getCommonAndrogynousClothing().get(Util.random.nextInt(ClothingType.getCommonAndrogynousClothing().size()))));
		}
		
		for(AbstractClothingType ct : ClothingType.getCommonAndrogynousLingerie()) {
			commonAndrogynousLingerie.add(AbstractClothingType.generateClothing(ct, false));
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonAndrogynousLingerie.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousLingerie().get(Util.random.nextInt(ClothingType.getCommonAndrogynousLingerie().size()))));
			}
			commonAndrogynousLingerie.add(generateRareClothing(ClothingType.getCommonAndrogynousLingerie().get(Util.random.nextInt(ClothingType.getCommonAndrogynousLingerie().size()))));
		}
		
		for(AbstractClothingType ct : ClothingType.getCommonAndrogynousAccessories()) {
			commonAndrogynousAccessories.add(AbstractClothingType.generateClothing(ct, false));
		}
		if(enchantedGear) {
			for (int i = 0; i < 3; i++) {
				commonAndrogynousAccessories.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousAccessories().get(Util.random.nextInt(ClothingType.getCommonAndrogynousAccessories().size()))));
			}
			commonAndrogynousAccessories.add(generateRareClothing(ClothingType.getCommonAndrogynousAccessories().get(Util.random.nextInt(ClothingType.getCommonAndrogynousAccessories().size()))));
		}
		
		// Specials:
		if(enchantedGear) {
			specials.add(AbstractClothingType.generateClothing(ClothingType.SOCK_RAINBOW_STOCKINGS));
			specials.add(AbstractClothingType.generateClothing(ClothingType.HAND_RAINBOW_FINGERLESS_GLOVES));
	
			specials.add(AbstractClothingType.generateClothing(ClothingType.MILK_MAID_KERCHIEF));
			specials.add(AbstractClothingType.generateClothing(ClothingType.MILK_MAID_HEADBAND));
			specials.add(AbstractClothingType.generateClothing(ClothingType.MILK_MAID_TORSO_DRESS));
	
			specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS));
			specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE));
			specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS));
			specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES));
			specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS));
			
			specials.add(AbstractClothingType.generateClothing(ClothingType.CATTLE_PIERCING_NOSE_BOVINE_RING));
			specials.add(AbstractClothingType.generateClothing(ClothingType.CATTLE_NECK_COWBELL_COLLAR));
			specials.add(AbstractClothingType.generateClothing(ClothingType.CATTLE_PIERCING_EAR_TAGS));
	
			specials.add(AbstractClothingType.generateClothing(ClothingType.MEGA_MILK));
	
			specials.add(AbstractClothingType.generateClothing(ClothingType.NECK_BREEDER_COLLAR));
	
			specials.add(AbstractClothingType.generateClothing(ClothingType.SCIENTIST_TORSO_OVER_LAB_COAT));
		}
		
		for(AbstractClothing c : commonFemaleClothing) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonFemaleUnderwear) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonFemaleOtherLingerie) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonFemaleAccessories) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonMaleClothing) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonMaleLingerie) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonMaleAccessories) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonAndrogynousClothing) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonAndrogynousLingerie) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonAndrogynousAccessories) {
			c.setEnchantmentKnown(true);
		}
	}
	
	private static AbstractClothing generateRareClothing(AbstractClothingType type) {
		List<ItemEffect> effects = new ArrayList<>();
		
		List<TFModifier> attributeMods = new ArrayList<>(TFModifier.getClothingAttributeList());
		
		TFModifier rndMod = attributeMods.get(Util.random.nextInt(attributeMods.size()));
		attributeMods.remove(rndMod);
		TFModifier rndMod2 = attributeMods.get(Util.random.nextInt(attributeMods.size()));
		
		effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.MAJOR_BOOST, 0));
		effects.add(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod2, TFPotency.MAJOR_BOOST, 0));
		
		return AbstractClothingType.generateClothing(
				type,
				type.getAvailablePrimaryColours().get(Util.random.nextInt(type.getAvailablePrimaryColours().size())),
				effects);
	}
	
	@Override
	public void handleSellingEffects(AbstractCoreItem item, int count, int itemPrice){
		commonFemaleClothing.remove(item);
		commonFemaleUnderwear.remove(item);
		commonFemaleOtherLingerie.remove(item);
		commonFemaleAccessories.remove(item);
		
		commonMaleClothing.remove(item);
		commonMaleLingerie.remove(item);
		commonMaleAccessories.remove(item);
		
		commonAndrogynousClothing.remove(item);
		commonAndrogynousLingerie.remove(item);
		commonAndrogynousAccessories.remove(item);
		
		specials.remove(item);
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
					+ "Nyan nervously leafs through her little notebook, before guiding you over to some shelves that stock what you're looking for, "
					+ "[nyan.speech(E-erm, j-just remember, I get new stock in every day! S-so if you don't like what I've got today, y-you can come back again tomorrow! I-if you want to, that is...)]"
				+ "</p>";
	}

	@Override
	public boolean isTrader() {
		return true;
	}
	
	@Override
	public String getGiftReaction(AbstractCoreItem gift, boolean applyEffects) {
		String text = null;
		if(gift instanceof AbstractItem) {
			AbstractItemType type = ((AbstractItem)gift).getItemType();
			if(type.equals(ItemType.GIFT_CHOCOLATES)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_CHOCOLATES")
						+(applyEffects
								?Main.game.getNyan().incrementAffection(Main.game.getPlayer(), 5)
								:"");
				
			} else if(type.equals(ItemType.GIFT_PERFUME)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_PERFUME")
					+(applyEffects
							?Main.game.getNyan().incrementAffection(Main.game.getPlayer(), 5)
							:"");
				
			} else if(type.equals(ItemType.GIFT_ROSE_BOUQUET)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_ROSES")
					+(applyEffects
							?Main.game.getNyan().incrementAffection(Main.game.getPlayer(), 5)
							:"");
				
			} else if(type.equals(ItemType.GIFT_TEDDY_BEAR)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_TEDDY_BEAR")
					+(applyEffects
							?Main.game.getNyan().incrementAffection(Main.game.getPlayer(), 15)
							:"");
				
			}
		}
		
		if(applyEffects) {
			if(text!=null) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanGift, true);
			}
		}
		return text;
	}
	
	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return item instanceof AbstractClothing;
	}

	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			if(Sex.getNumberOfOrgasms(Main.game.getNyan())==0) {
				Main.game.getTextEndStringBuilder().append(Main.game.getNyan().incrementAffection(Main.game.getPlayer(), -15));
			}
		}
	}
	
	public List<AbstractClothing> getCommonFemaleClothing() {
		return commonFemaleClothing;
	}

	public List<AbstractClothing> getCommonFemaleUnderwear() {
		return commonFemaleUnderwear;
	}
	
	public List<AbstractClothing> getCommonFemaleOtherLingerie() {
		return commonFemaleOtherLingerie;
	}

	public List<AbstractClothing> getCommonFemaleAccessories() {
		return commonFemaleAccessories;
	}

	public List<AbstractClothing> getCommonMaleClothing() {
		return commonMaleClothing;
	}

	public List<AbstractClothing> getCommonAndrogynousClothing() {
		return commonAndrogynousClothing;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<AbstractClothing> getCommonMaleLingerie() {
		return commonMaleLingerie;
	}

	public List<AbstractClothing> getCommonMaleAccessories() {
		return commonMaleAccessories;
	}

	public List<AbstractClothing> getCommonAndrogynousLingerie() {
		return commonAndrogynousLingerie;
	}

	public List<AbstractClothing> getCommonAndrogynousAccessories() {
		return commonAndrogynousAccessories;
	}

	public List<AbstractClothing> getSpecials() {
		return specials;
	}

	// Combat (you never fight Nyan):
	@Override
	public String getCombatDescription() {
		return null;
	}
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	@Override
	public Attack attackType() {
		return null;
	}

}