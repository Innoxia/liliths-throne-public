package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public class Nyan extends NPC {

	private List<AbstractClothing> commonFemaleClothing;
	private List<AbstractClothing> commonFemaleUnderwear;
	private List<AbstractClothing> commonFemaleAccessories;
	private List<AbstractClothing> commonMaleClothing;
	private List<AbstractClothing> commonMaleLingerie;
	private List<AbstractClothing> commonMaleAccessories;
	private List<AbstractClothing> commonAndrogynousClothing;
	private List<AbstractClothing> commonAndrogynousLingerie;
	private List<AbstractClothing> commonAndrogynousAccessories;
	private List<AbstractClothing> specials;

	public Nyan() {
		this(false);
	}
	
	public Nyan(boolean isImported) {
		super(new NameTriplet("Nyan"), "Nyan is the owner of the store 'Nyan's Clothing Emporium', found in Dominion's shopping arcade."
				+ " She's extremely shy, and gets very nervous when having to talk to people.",
				21, Month.APRIL, 12,
				10, Gender.F_V_B_FEMALE, RacialBody.CAT_MORPH, RaceStage.LESSER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.LOW)));
		
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
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MINI_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TRAINER_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false), true, this);

			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
		
		commonFemaleClothing = new ArrayList<>();
		commonFemaleUnderwear = new ArrayList<>();
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
				try {
					c.saveAsXML(clothingElement, doc);
				} catch(Exception ex) {
				}
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
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MINI_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TRAINER_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
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
				
				NodeList nodeList = npcSpecificElement.getElementsByTagName("clothing");
				for(int i=0; i < nodeList.getLength(); i++){
					Element e = (Element) nodeList.item(i);
					try {
						entry.getValue().add(AbstractClothing.loadFromXML(e, doc));
					} catch(Exception ex) {
					}
				}
				
			}
		}
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
		
		Main.game.getDialogueFlags().resetNyanActions();
		
		commonFemaleClothing.clear();
		commonFemaleUnderwear.clear();
		commonFemaleAccessories.clear();
		
		commonMaleClothing.clear();
		commonMaleLingerie.clear();
		commonMaleAccessories.clear();
		
		commonAndrogynousClothing.clear();
		commonAndrogynousLingerie.clear();
		commonAndrogynousAccessories.clear();
		
		specials.clear();

		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing!=null && clothing.getItemTags().contains(ItemTag.SOLD_BY_NYAN)) {
				if(clothing.getRarity() == Rarity.COMMON) {
					if(clothing.getFemininityRestriction()==Femininity.FEMININE) {
						if(ClothingType.getCoreClothingSlots().contains(clothing.getSlot())) {
							commonFemaleClothing.add(AbstractClothingType.generateClothing(clothing, false));
							
						} else if(ClothingType.getLingerieSlots().contains(clothing.getSlot())) {
							commonFemaleUnderwear.add(AbstractClothingType.generateClothing(clothing, false));
							
						} else {
							commonFemaleAccessories.add(AbstractClothingType.generateClothing(clothing, false));
						}
						
					} else if(clothing.getFemininityRestriction()==Femininity.MASCULINE) {
						if(ClothingType.getCoreClothingSlots().contains(clothing.getSlot())) {
							commonMaleClothing.add(AbstractClothingType.generateClothing(clothing, false));
							
						} else if(ClothingType.getLingerieSlots().contains(clothing.getSlot())) {
							commonMaleLingerie.add(AbstractClothingType.generateClothing(clothing, false));
							
						} else {
							commonMaleAccessories.add(AbstractClothingType.generateClothing(clothing, false));
						}
						
					} else {
						if(ClothingType.getCoreClothingSlots().contains(clothing.getSlot())) {
							commonAndrogynousClothing.add(AbstractClothingType.generateClothing(clothing, false));
							
						} else if(ClothingType.getLingerieSlots().contains(clothing.getSlot())) {
							commonAndrogynousLingerie.add(AbstractClothingType.generateClothing(clothing, false));
							
						} else {
							commonAndrogynousAccessories.add(AbstractClothingType.generateClothing(clothing, false));
						}
					}
					
				} else {
					specials.add(AbstractClothingType.generateClothing(clothing, false));
				}
			}
		}
		
		if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
			addEnchantedClothing(commonFemaleClothing);
			addEnchantedClothing(commonFemaleUnderwear);
			addEnchantedClothing(commonFemaleAccessories);
	
			addEnchantedClothing(commonMaleClothing);
			addEnchantedClothing(commonMaleLingerie);
			addEnchantedClothing(commonMaleAccessories);
	
			addEnchantedClothing(commonAndrogynousClothing);
			addEnchantedClothing(commonAndrogynousLingerie);
			addEnchantedClothing(commonAndrogynousAccessories);
		}
	}
	
	/**
	 * Adds three uncommon clothing items to the list, and one rare item.
	 */
	private static void addEnchantedClothing(List<AbstractClothing> clothingList) {
		List<AbstractClothingType> typesToAdd = new ArrayList<>();
		for(int i=0;i<4;i++) {
			typesToAdd.add(Util.randomItemFrom(clothingList).getClothingType());
		}
		
		for(int i=0; i<typesToAdd.size(); i++) {
			if(i==typesToAdd.size()-1) {
				clothingList.add(AbstractClothingType.generateRareClothing(typesToAdd.get(i)));
			} else {
				clothingList.add(AbstractClothingType.generateClothingWithEnchantment(typesToAdd.get(i)));
			}
		}

		for(AbstractClothing c : clothingList) {
			c.setEnchantmentKnown(true);
		}
	}
	
	@Override
	public void handleSellingEffects(AbstractCoreItem item, int count, int itemPrice){
		commonFemaleClothing.remove(item);
		commonFemaleUnderwear.remove(item);
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
				
			} else if(type.equals(ItemType.GIFT_ROSE)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_SINGLE_ROSE")
						+(applyEffects
								?Main.game.getNyan().incrementAffection(Main.game.getPlayer(), 5)
								:"");
					
				} else if(type.equals(ItemType.GIFT_ROSE_BOUQUET)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_ROSES")
					+(applyEffects
							?Main.game.getNyan().incrementAffection(Main.game.getPlayer(), 10)
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
	public void endSex() {
	}
	
	public List<AbstractClothing> getCommonFemaleClothing() {
		return commonFemaleClothing;
	}

	public List<AbstractClothing> getCommonFemaleUnderwear() {
		return commonFemaleUnderwear;
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


}