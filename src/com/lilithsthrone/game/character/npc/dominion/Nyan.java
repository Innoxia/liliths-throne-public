package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
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
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
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
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
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
		super(isImported, new NameTriplet("Nyan"), "Rey",
				"Nyan is the owner of the store 'Nyan's Clothing Emporium', found in Dominion's shopping arcade."
						+ " She's extremely shy, and gets very nervous when having to talk to people.",
				21, Month.APRIL, 12,
				10, Gender.F_V_B_FEMALE, Subspecies.CAT_MORPH, RaceStage.LESSER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, true);
		
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
		if(!isImported) {
			dailyUpdate();
		}
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

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(this.getSexCount(Main.game.getPlayer().getId()).getTotalTimesHadSex()==0) {
			this.setVaginaVirgin(true);
		}
		
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
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.10")) {
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.COWARDLY,
					PersonalityTrait.INNOCENT,
					PersonalityTrait.SHY,
					PersonalityTrait.STUTTER);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
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
					PersonalityTrait.KIND,
					PersonalityTrait.COWARDLY,
					PersonalityTrait.INNOCENT,
					PersonalityTrait.SHY,
					PersonalityTrait.STUTTER);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_CLOTHING_STORE_OWNER);
	
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
		
		// Body:

		// Core:
		this.setHeight(165);
		this.setFemininity(85);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, PresetColour.EYE_BLUE));
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, PresetColour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_FELINE_FUR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED_LIGHT));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.ONE_SMALL.getValue());
		this.setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_leg_mini_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_trainer_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_head_headband", PresetColour.CLOTHING_BLACK, false), true, this);

	}
	
	@Override
	public String getArtworkFolderName() {
		return "Nyan";
		//TODO NyanSpecials
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
			try {
				if(clothing!=null && clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN)) {
					AbstractClothing generatedClothing = AbstractClothingType.generateClothing(clothing, false);

					for(int i=0; i<2+Util.random.nextInt(5); i++) {
						if(clothing.getRarity() == Rarity.COMMON) {
							if(clothing.getFemininityRestriction()==Femininity.FEMININE) {
								if(ClothingType.getCoreClothingSlots().contains(clothing.getEquipSlots().get(0))) {
									commonFemaleClothing.add(generatedClothing);
									
								} else if(ClothingType.getLingerieSlots().contains(clothing.getEquipSlots().get(0))) {
									commonFemaleUnderwear.add(generatedClothing);
									
								} else {
									commonFemaleAccessories.add(generatedClothing);
								}
								
							} else if(clothing.getFemininityRestriction()==Femininity.MASCULINE) {
								if(ClothingType.getCoreClothingSlots().contains(clothing.getEquipSlots().get(0))) {
									commonMaleClothing.add(generatedClothing);
									
								} else if(ClothingType.getLingerieSlots().contains(clothing.getEquipSlots().get(0))) {
									commonMaleLingerie.add(generatedClothing);
									
								} else {
									commonMaleAccessories.add(generatedClothing);
								}
								
							} else {
								if(ClothingType.getCoreClothingSlots().contains(clothing.getEquipSlots().get(0))) {
									commonAndrogynousClothing.add(generatedClothing);
									
								} else if(ClothingType.getLingerieSlots().contains(clothing.getEquipSlots().get(0))) {
									commonAndrogynousLingerie.add(generatedClothing);
									
								} else {
									commonAndrogynousAccessories.add(generatedClothing);
								}
							}
							
						} else {
							specials.add(generatedClothing);
						}
					}
				} 
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
			commonFemaleClothing.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonFemaleClothing, 3, 1));
			commonFemaleUnderwear.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonFemaleUnderwear, 3, 1));
			commonFemaleAccessories.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonFemaleAccessories, 3, 1));

			commonMaleClothing.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonMaleClothing, 3, 1));
			commonMaleLingerie.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonMaleLingerie, 3, 1));
			commonMaleAccessories.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonMaleAccessories, 3, 1));

			commonAndrogynousClothing.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonAndrogynousClothing, 3, 1));
			commonAndrogynousLingerie.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonAndrogynousLingerie, 3, 1));
			commonAndrogynousAccessories.addAll(CharacterUtils.generateEnchantedClothingForTrader(this, commonAndrogynousAccessories, 3, 1));
		}
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isExtendedWorkTime()) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public void handleSellingEffects(AbstractCoreItem item, int count, int itemPrice){
		for(int i=0; i<count; i++) {
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
					+ "Nyan nervously leafs through her little notebook, before guiding you over to some shelves that stock what you're looking for, "
					+ "[nyan.speechNoEffects(E-erm, j-just remember, I get new stock in every day! S-so if you don't like what I've got today, y-you can come back again tomorrow! I-if you want to, that is...)]"
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
								?Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 5)
								:"");
				
			} else if(type.equals(ItemType.GIFT_PERFUME)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_PERFUME")
					+(applyEffects
							?Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 5)
							:"");
				
			} else if(type.equals(ItemType.GIFT_ROSE_BOUQUET)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_ROSES")
					+(applyEffects
							?Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 10)
							:"");
				
			} else if(type.equals(ItemType.GIFT_TEDDY_BEAR)) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_TEDDY_BEAR")
					+(applyEffects
							?Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 15)
							:"");
			}
			
		} else if(gift instanceof AbstractClothing) {
			AbstractClothingType type = ((AbstractClothing)gift).getClothingType();
			if(type.equals(ClothingType.getClothingTypeFromId("innoxia_hair_rose"))) {
				text =  UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_GIFT_SINGLE_ROSE")
						+(applyEffects
								?Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 5)
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
	
	public List<AbstractClothing> getCommonFemaleClothing() {
		Collections.shuffle(commonFemaleClothing);
		return commonFemaleClothing;
	}

	public List<AbstractClothing> getCommonFemaleUnderwear() {
		Collections.shuffle(commonFemaleUnderwear);
		return commonFemaleUnderwear;
	}

	public List<AbstractClothing> getCommonFemaleAccessories() {
		Collections.shuffle(commonFemaleAccessories);
		return commonFemaleAccessories;
	}

	public List<AbstractClothing> getCommonMaleClothing() {
		Collections.shuffle(commonMaleClothing);
		return commonMaleClothing;
	}

	public List<AbstractClothing> getCommonAndrogynousClothing() {
		Collections.shuffle(commonAndrogynousClothing);
		return commonAndrogynousClothing;
	}

	public List<AbstractClothing> getCommonMaleLingerie() {
		Collections.shuffle(commonMaleLingerie);
		return commonMaleLingerie;
	}

	public List<AbstractClothing> getCommonMaleAccessories() {
		Collections.shuffle(commonMaleAccessories);
		return commonMaleAccessories;
	}

	public List<AbstractClothing> getCommonAndrogynousLingerie() {
		Collections.shuffle(commonAndrogynousLingerie);
		return commonAndrogynousLingerie;
	}

	public List<AbstractClothing> getCommonAndrogynousAccessories() {
		Collections.shuffle(commonAndrogynousAccessories);
		return commonAndrogynousAccessories;
	}

	public List<AbstractClothing> getSpecials() {
		Collections.shuffle(specials);
		return specials;
	}


}