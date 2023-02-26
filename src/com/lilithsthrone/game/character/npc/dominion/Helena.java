package com.lilithsthrone.game.character.npc.dominion;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
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
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.SlaveForSale;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
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
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.4.2
 * @author Innoxia, AceXP
 */
public class Helena extends NPC {

	public Helena() {
		this(false);
	}
	
	public Helena(boolean isImported) {
		super(isImported, new NameTriplet("Helena"), "Labelle",
				"Helena is an extremely powerful harpy matriarch, and is in control of one of the largest harpy flocks in Dominion."
						+ " Her beauty rivals that of even the most gorgeous of succubi, which, combined with her sharp mind and regal personality, makes her somewhat of an idol in harpy society.",
				26, Month.MAY, 3,
				10, Gender.F_V_B_FEMALE, Subspecies.HARPY_SWAN, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_HELENA_BEDROOM, true);
		
		if(!isImported) {
			this.addSpell(Spell.SLAM);
			this.addSpell(Spell.ARCANE_AROUSAL);
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 5);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(10);
			this.resetPerksMap(true);
			this.addSpell(Spell.SLAM);
			this.addSpell(Spell.ARCANE_AROUSAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.INNOCENT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.setName(new NameTriplet("Helena"));
			this.getHomeCell().removeCharacterHomeId("-1,Alexa");
			this.getHomeCell().addCharacterHomeId("-1,Helena");
			this.getCell().removeCharacterPresentId("-1,Alexa");
			this.getCell().addCharacterPresentId("-1,Helena");
			this.setId("-1,Helena");
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.2")) {
			this.setSurname("Labelle");
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.9")) {
			this.clearFetishes();
			this.addFetish(Fetish.FETISH_PURE_VIRGIN);
			this.resetPerksMap(true, false);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, PresetColour.EYE_BLUE_LIGHT));
			this.setHairStyle(HairStyle.STRAIGHT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7")) {
			this.equipClothing();
			this.setFetishDesire(Fetish.FETISH_KINK_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.1")) {
			this.setDescription("Helena is an extremely powerful harpy matriarch, and is in control of one of the largest harpy flocks in Dominion."
						+ " Her beauty rivals that of even the most gorgeous of succubi, which, combined with her sharp mind and regal personality, makes her somewhat of an idol in harpy society.");
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.7")) {
			this.setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_HELENA_BEDROOM);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.20")) {
			this.setSkinCovering(new Covering(BodyCoveringType.HARPY_SKIN, PresetColour.SKIN_EBONY), false);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.INNOCENT);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_HARPY_MATRIARCH);
			
			this.addFetish(Fetish.FETISH_PURE_VIRGIN);
			this.setFetishDesire(Fetish.FETISH_KINK_RECEIVING, FetishDesire.THREE_LIKE);
		}
		
		// Body:

		// Core:
		this.setHeight(162);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, PresetColour.EYE_BLUE_LIGHT));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, PresetColour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HARPY_SKIN, PresetColour.SKIN_EBONY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, PresetColour.COVERING_WHITE), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.STRAIGHT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HARPY, PresetColour.COVERING_WHITE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.D.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
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
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(this.isSlutty()) {
			this.setPiercedEar(true);
			this.setPiercedNavel(true);
			this.setPiercedNose(true);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_PURPLE_ROYAL, null, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_velvet_choker", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_aviators", PresetColour.CLOTHING_GOLD, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_BLACK, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_tube_top", PresetColour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_belted", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GOLD, null, false), true, this);
			
		} else {
			this.setPiercedEar(true);
			this.setPiercedNavel(false);
			this.setPiercedNose(false);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_chain_dangle", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_tiara", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_WHITE, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_PLUNGE_DRESS, PresetColour.CLOTHING_WHITE, false), true, this);
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
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#59005C";
		} else {
			return "#FFDFB3";
		}
	}
	
	@Override
	public void dailyUpdate() {
		if(!Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY)) {
			for(String id : new ArrayList<>(this.getSlavesOwned())) {
				if(Main.game.isCharacterExisting(id)) {
					Main.game.banishNPC(id);
				}
			}
			// Catch for old version which had bugged slaves standing on Helena's tile:
			for(GameCharacter character : new ArrayList<>(Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP)))) {
				if(character.isSlave() && !character.getOwner().isPlayer() && (character instanceof DominionAlleywayAttacker || character instanceof SlaveForSale)) {
					Main.game.banishNPC((NPC) character);
				}
			}

			// Helena's slaves after completing her romance quest are in holding for the player, and so should not be removed.
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA) && !this.getSlavesOwned().isEmpty() && !Main.game.isWorkTime() && Main.game.getHourOfDay()>12) {
				sellOffRemainingSlaves();
			}
			
			//this.removeAllSlaves();
			
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
				for (int i=0; i<2; i++) {
					NPC newSlave = new SlaveForSale(Gender.getGenderFromUserPreferences(false, false), false);
					try {
						Main.game.addNPC(newSlave, false, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					newSlave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
					addSlave(newSlave);
					newSlave.resetInventory(true);
					newSlave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_PLATINUM, false), true, Main.game.getNpc(Helena.class));
					newSlave.setPlayerKnowsName(true);
				}
			}
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			boolean nestHours = (Main.game.isDayTime() && Main.game.getDateNow().getDayOfWeek()!=DayOfWeek.FRIDAY) // If Friday, don't set Helena to her nest after work
					|| (Main.game.getHourOfDay()>8 && Main.game.getHourOfDay()<21);
			
			if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA) || Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				if(nestHours) {
					this.setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					
				} else {
					this.returnToHome();
				}
				
			} else {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)) {
					this.returnToHome();
					
				} else if(((Main.game.isWorkTime() && !Main.game.isWeekend()) // Helena doesn't work over the weekend
							|| Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE
							|| (Main.game.getDateNow().getDayOfWeek()==DayOfWeek.FRIDAY && Main.game.getHourOfDay()>12 && Main.game.getHourOfDay()<21))) { // Helena works late on Fridays
					this.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
					
				} else {
					if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)
							&& Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
							&& nestHours
							&& (Main.game.isWeekend() || Main.game.getHourOfDay()>12)) { // Do not appear in nest before work
						this.setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
						
					} else {
						this.returnToHome();
					}
				}
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public String getGiftReaction(AbstractCoreItem gift, boolean applyEffects) {
		String text = null;
		if(gift instanceof AbstractItem) {
			AbstractItemType type = ((AbstractItem)gift).getItemType();
			if(type.equals(ItemType.GIFT_CHOCOLATES)) {
				text = UtilText.parseFromXMLFile("characters/dominion/helena", "GIFT_CHOCOLATES")
						+(applyEffects
								?this.incrementAffection(Main.game.getPlayer(), 5)
								:"");
				
			} else if(type.equals(ItemType.GIFT_PERFUME)) {
				text = UtilText.parseFromXMLFile("characters/dominion/helena", "GIFT_PERFUME")
						+(applyEffects
								?this.incrementAffection(Main.game.getPlayer(), 5)
								:"");
				
			} else if(type.equals(ItemType.GIFT_ROSE_BOUQUET)) {
				text = UtilText.parseFromXMLFile("characters/dominion/helena", "GIFT_ROSES")
						+(applyEffects
								?this.incrementAffection(Main.game.getPlayer(), 10)
								:"");
				
			} else if(type.equals(ItemType.GIFT_TEDDY_BEAR)) {
				text = UtilText.parseFromXMLFile("characters/dominion/helena", "GIFT_TEDDY_BEAR")
						+(applyEffects
								?this.incrementAffection(Main.game.getPlayer(), -5)
								:"");
			}
			
		} else if(gift instanceof AbstractClothing) {
			AbstractClothingType type = ((AbstractClothing)gift).getClothingType();
			if(type.equals(ClothingType.getClothingTypeFromId("innoxia_hair_rose"))) {
				text = UtilText.parseFromXMLFile("characters/dominion/helena", "GIFT_SINGLE_ROSE")
						+(applyEffects
								?this.incrementAffection(Main.game.getPlayer(), 5)
								:"");
					
			}
		}
		
		if(applyEffects) {
			if(text!=null) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGift, true);
			}
		}
		return text;
	}
	
	public void sellOffRemainingSlaves() {
		for(String id : new ArrayList<>(this.getSlavesOwned())) {
			try {
				if(Main.game.isCharacterExisting(id) && !Main.game.getNPCById(id).isUnique()) {
					Main.game.banishNPC(id);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.removeAllSlaves();
	}
	
	public boolean isSlutty() {
		return !this.isVaginaVirgin() || !this.isAssVirgin();
	}
	
	public void applySlut() {
		// Hair:
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.HIGHLIGHTS, CoveringModifier.SMOOTH, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true);
		this.setPubicHair(BodyHair.ZERO_NONE);
		
		// Makeup:
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, CoveringPattern.NONE, CoveringModifier.SPARKLY, PresetColour.COVERING_PINK, false, PresetColour.COVERING_PINK, false));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, CoveringPattern.NONE, CoveringModifier.SPARKLY, PresetColour.COVERING_PINK, false, PresetColour.COVERING_PINK, false));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.GLOSSY, PresetColour.COVERING_PINK, false, PresetColour.COVERING_PINK, false));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.BASE_PURPLE_DARK));
	}
	
	public void applyDressForDate() {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(this.isSlutty()) {
			this.setPiercedEar(true);
			this.setPiercedNavel(true);
			this.setPiercedNose(true);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_PURPLE_ROYAL, null, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_velvet_choker", PresetColour.CLOTHING_BLACK, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_plunge_club_dress", PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_GOLD, null, false), true, this);
			
		} else {
			this.setPiercedEar(true);
			this.setPiercedNavel(false);
			this.setPiercedNose(false);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_pearl_studs", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_SILVER, null, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_tiara", PresetColour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_diamond_necklace", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, null, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_strapless_bra", PresetColour.CLOTHING_WHITE, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("phlarx_dresses_evening_gown", PresetColour.CLOTHING_WHITE, false), true, this);
		}
	}

	public void applyLingerie() {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(this.isSlutty()) {
			this.setPiercedEar(true);
			this.setPiercedNavel(true);
			this.setPiercedNose(true);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_PURPLE_ROYAL, null, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_open_cup_bra", PresetColour.CLOTHING_BLACK, false), true, this);
			
		} else {
			this.setPiercedEar(true);
			this.setPiercedNavel(false);
			this.setPiercedNose(false);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaBedroomFromNest)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_chain_dangle", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			} else {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_pearl_studs", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_SILVER, null, false), true, this);
			}

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_WHITE, false), true, this);
		}
	}
	
	public void applyDressForMorning() {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_shimapan", PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_WHITE, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_chemise", PresetColour.CLOTHING_BLACK, false), true, this);
	}

}