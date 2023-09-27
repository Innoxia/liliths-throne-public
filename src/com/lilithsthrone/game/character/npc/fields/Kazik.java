package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.rendering.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class Kazik extends NPC {

	public Kazik() {
		this(false);
	}
	
	public Kazik(boolean isImported) {
		super(isImported, new NameTriplet("Kazik"), "Malinowski",
				"Kazik owns and runs the jewellery store 'Golden Treasures' in Elis's Farmer's market."
						+ " He's notable for the fact that he's a proficient arcane user, despite not having any racial affiliation for using the arcane.",
				27, Month.NOVEMBER, 11,
				30,
				Gender.M_P_MALE, Subspecies.getSubspeciesFromId("dsg_ferret_subspecies_ferret"), RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_market"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_market_trinkets"),
				true);

		this.setGenericName("handsome ferret-boy");
		
		if(!isImported) {
			dailyUpdate();
			this.setPlayerKnowsName(false);
			this.addSpell(Spell.FIREBALL);
			this.addSpell(Spell.FLASH);
			this.addSpell(Spell.ICE_SHARD);
			this.addSpell(Spell.RAIN_CLOUD);
			this.addSpell(Spell.ARCANE_AROUSAL);
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 10);
		}
	}
	
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.CLOTHING_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 2)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);

			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_PENIS_GIVING);

			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.ONE_DISLIKE);
		}
		
		// Body:
		
		// Core:
		this.setHeight(178);
		this.setFemininity(15);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("dsg_ferret_eye"), PresetColour.EYE_BLUE_DARK));
		this.setSkinCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("dsg_ferret_fur"), CoveringPattern.MARKED, CoveringModifier.SILKEN, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_GREY, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		
		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("dsg_ferret_hair"), PresetColour.COVERING_BROWN_DARK), true);
		this.setHairLength(HairLength.ZERO_BALD.getMinimumValue());
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("dsg_ferret_body_hair"), PresetColour.COVERING_WHITE), false);
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
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(18);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(100);
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

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_aviators", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_ORANGE, null, false), true, this);

		AbstractClothing boxers = Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false);
		boxers.setPattern(Pattern.getPatternIdByName("camo"));
		boxers.setPatternColour(0, PresetColour.CLOTHING_DESATURATED_BROWN);
		boxers.setPatternColour(1, PresetColour.CLOTHING_GREEN_DRAB);
		this.equipClothingFromNowhere(boxers, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_PLATINUM, PresetColour.CLOTHING_GUNMETAL, PresetColour.CLOTHING_GUNMETAL, false), true, this);

		AbstractClothing tshirt = Main.game.getItemGen().generateClothing("innoxia_torso_tshirt", PresetColour.CLOTHING_BLACK, false);
		tshirt.setPattern(Pattern.getPatternIdByName("camo"));
		tshirt.setPatternColour(0, PresetColour.CLOTHING_DESATURATED_BROWN);
		tshirt.setPatternColour(1, PresetColour.CLOTHING_GREEN_DRAB);
		this.equipClothingFromNowhere(tshirt, true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_ljacket_ljacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GREY_DARK, PresetColour.CLOTHING_STEEL, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
		
		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_PLATINUM, false);
		for(int i=0; i<5; i++) {
			ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.VIRILITY, TFPotency.MAJOR_BOOST, 0));
			ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
			ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.SPELL_COST_MODIFIER, TFPotency.MAJOR_BOOST, 0));
			ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.MANA_MAXIMUM, TFPotency.MAJOR_BOOST, 0));
		}
		this.equipClothingFromNowhere(ring, true, this);
		
		this.setPiercedEar(true);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_PLATINUM, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#94b0ff";
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);

		List<AbstractClothingType> clothingTypesToSell = new ArrayList<>();
		List<InventorySlot> extraSlots = Util.newArrayListOfValues(InventorySlot.NECK, InventorySlot.WRIST, InventorySlot.FINGER, InventorySlot.ANKLE);
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if((clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE) || (clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN) && extraSlots.contains(clothing.getEquipSlots().get(0))))
					&& clothing.getRarity() == Rarity.COMMON
					&& clothing.getClothingSet() == null
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
		
		// Enchanted items:
		while(clothingTypesToSell.size() >= 5) { // 5 types left for enchanted items
			clothingTypesToSell.remove(Util.random.nextInt(clothingTypesToSell.size()));
		}
		for(AbstractClothingType type : clothingTypesToSell) {
			AbstractClothing c = Main.game.getItemGen().generateClothingWithEnchantment(type);
			c.setEnchantmentKnown(this, true);
			this.addClothing(c, false);
		}
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(8, 19)) {
				this.returnToHome(); // Stall in farmer's market
				
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
		return UtilText.parseFromXMLFile("places/fields/elis/market", "TRINKETS_TRANSACTION_START");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		List<InventorySlot> extraSlots = Util.newArrayListOfValues(InventorySlot.NECK, InventorySlot.WRIST, InventorySlot.FINGER, InventorySlot.ANKLE);
		
		return (item instanceof AbstractClothing)
				&& (((AbstractClothing)item).getClothingType().getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE)
						|| (((AbstractClothing)item).getClothingType().getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN) && extraSlots.contains(((AbstractClothing)item).getClothingType().getEquipSlots().get(0))))
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY);
	}

	@Override
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		if(soldToPlayer) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, true);
			UtilText.addSpecialParsingString(itemSold.getName(), true);
			
			if(!itemSold.getEffects().isEmpty()) {
				Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/market", "TRINKETS_TRANSACTION_TICKET"));
				Main.game.appendToTextStartStringBuilder(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_faire_ticket"), false, true));
			} else {
				Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/market", "TRINKETS_TRANSACTION_NO_TICKET"));
			}
		}
	}
	
	@Override
	public void endSex() {
	}
	
}
