package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.WingType;
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
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class Yui extends NPC {

	public Yui() {
		this(false);
	}
	
	public Yui(boolean isImported) {
		super(isImported, new NameTriplet("Yui"), "Lyxiasmartu",
				"Yui owns and operates the stall 'Yui's Toys' in Elis's Farmer's Market."
						+ " Despite her small size and thin frame, she has an extremely dominant personality, and delights in sadistically abusing her customers.",
				25, Month.DECEMBER, 22,
				25,
				Gender.F_V_B_FEMALE, Subspecies.getSubspeciesFromId("charisma_spider_subspecies_spider"), RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_market"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_market_toys"),
				true);

		this.setGenericName("grinning jorogumo");
		
		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.dailyUpdate();
		}
	}
	
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(Perk.CLOTHING_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.CYNICAL,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);

			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
			this.addFetish(Fetish.FETISH_DENIAL);

			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_EXHIBITIONIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ARMPIT_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		}
		
		// Body:
		this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, Gender.F_V_B_FEMALE, Subspecies.getSubspeciesFromId("charisma_spider_subspecies_spider"), true, RaceStage.GREATER), false);
		this.setArmRows(3);
		this.setWingType(WingType.NONE);
		this.setHornType(HornType.CURVED);
		this.setHornLength(HornLength.ONE_SMALL.getMedianValue());
		
		// Core:
		this.setHeight(150);
		this.setFemininity(80);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, PresetColour.EYE_CRIMSON, true, PresetColour.EYE_CRIMSON, true));
		this.setSkinCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("charisma_spider_chitinSmooth"), CoveringPattern.STRIPED, CoveringModifier.SMOOTH, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_YELLOW, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_EBONY), true);

		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_BLACK), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("charisma_spider_hair"), CoveringPattern.OMBRE, CoveringModifier.SILKEN, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_RED_DARK, false), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.BUN);
		
		this.setHairCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("charisma_spider_body_hair"), PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED_DARK));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_CLEAR));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK_LIGHT));
		
		// Face:
		this.setEyePairs(4);
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.THREE_WET);
		this.setAssElasticity(OrificeElasticity.SIX_SUPPLE.getValue());
		this.setAssPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(18);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(150);
		this.setPenisCumExpulsion(75);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(5000);
		
		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bdsm_riding_crop", DamageType.FIRE));
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_sticks", PresetColour.CLOTHING_BLACK_JET, false), true, this);
		
		AbstractClothing necklace = Main.game.getItemGen().generateClothing("innoxia_neck_demonstone_necklace", PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_GOLD, null, false);
		for(int i=0;i<10;i++) {
			necklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0));
		}
		this.equipClothingFromNowhere(necklace, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_stockings", PresetColour.CLOTHING_BLACK_JET, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_panties", PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_GOLD, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_goth_boots_fem", PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK_JET, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_bra", PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_GOLD, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_croptop", PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_GOLD, null, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_GOLD, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#ff799b";
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);

		List<AbstractClothingType> clothingTypesToSell = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if((clothing.getClothingSet() == SetBonus.getSetBonusFromId("innoxia_bdsm") || clothing.getClothingSet() == SetBonus.getSetBonusFromId("sage_ltxset") || clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_FINCH))
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
		
		for(int i=0; i<3; i++) {
			this.addWeapon(Main.game.getItemGen().generateWeapon("innoxia_bdsm_riding_crop"), false);
		}
		
		this.addItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), 10, false, false);
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
	public void endSex() {
		this.cleanAllDirtySlots(true);
		this.cleanAllClothing(false, false);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("places/fields/elis/market", "TOYS_TRANSACTION_START");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}

	
	// Methods for bondage basement and alleyway content:
	
	private Map<InventorySlot, AbstractClothing> removedClothing;
	private Map<InventorySlot, AbstractWeapon> removedWeapons;
	private List<InventorySlot> mainSlots = Util.newArrayListOfValues(InventorySlot.WEAPON_MAIN_1, InventorySlot.WEAPON_MAIN_2, InventorySlot.WEAPON_MAIN_3);
	private List<InventorySlot> offhandSlots = Util.newArrayListOfValues(InventorySlot.WEAPON_OFFHAND_1, InventorySlot.WEAPON_OFFHAND_2, InventorySlot.WEAPON_OFFHAND_3);
	
	public void equipGearBondageBasement(GameCharacter target, int stage) {
//		Main.game.addSavedInventory(target);
		
		// Unequip everything onto the toys tile:
		
		Cell originalCell = target.getCell();
		Cell toysCell = Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_yui_dungeon")).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_yui_dungeon_toys"));
		
		removedClothing = target.unequipAllClothing(this, true, true);
		for(AbstractClothing c : removedClothing.values()) {
			toysCell.getInventory().addClothing(c);
		}
		
		target.setLocation(toysCell);
		removedWeapons = new HashMap<>();
		for(int i=0; i<target.getArmRows();  i++) {
			if(target.getMainWeapon(i) != null) {
				removedWeapons.put(mainSlots.get(i), target.getMainWeapon(i));
				target.unequipMainWeapon(i, true, false);
			}
			if(target.getOffhandWeapon(i) != null) {
				removedWeapons.put(offhandSlots.get(i), target.getOffhandWeapon(i));
				target.unequipOffhandWeapon(i, true, false);
			}
		}
		target.setLocation(originalCell);
		
		// Equip clothing:
		if(stage==1) {
			Colour colourPrimary = PresetColour.CLOTHING_PINK_HOT;
			Colour colourSecondary = PresetColour.CLOTHING_SILVER;

			AbstractClothing blindfold = Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", colourPrimary, colourSecondary, colourSecondary, false);
			blindfold.clearEffects();
			target.equipClothingFromNowhere(blindfold, true, this);
			
			if(target.getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()) {
				target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_pins", colourSecondary, false), true, this);
			}

			AbstractClothing gag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ringgag", colourSecondary, colourPrimary, colourSecondary, false);
			gag.clearEffects();
			target.equipClothingFromNowhere(gag, true, this);

			applyCollar(target, this, false);

			target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_croptop", colourPrimary, colourSecondary, colourSecondary, false), true, this);
			target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_corset", colourPrimary, colourSecondary, colourSecondary, false), true, this);
			if(target.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_stockings_open", colourPrimary, false), true, this);
			}
			
			// Breasts/nipples:
//			if(target.hasBreasts()) {
//				target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_open_cup_bra", colourPrimary, false), true, this);
//			}
			target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_clover_clamps_clover_clamps", colourSecondary, false), true, this);
			
			AbstractClothing restraints = Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_restraints", colourPrimary, colourSecondary, colourSecondary, false);
			restraints.clearEffects();
			target.equipClothingFromNowhere(restraints, InventorySlot.WRIST, true, this);
			
			if(target.hasLegs()) {
				AbstractClothing spreaderBar = Main.game.getItemGen().generateClothing("innoxia_bdsm_spreaderbar", colourPrimary, colourSecondary, colourSecondary, false);
				spreaderBar.clearEffects();
				target.equipClothingFromNowhere(spreaderBar, true, this);
			}
			
			if(Main.game.isAnalContentEnabled()) {
				target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_heart", colourSecondary, colourPrimary, colourSecondary, false), true, this);
			}

			if(target.hasPenis()) {
				AbstractClothing cage = Main.game.getItemGen().generateClothing("innoxia_bdsm_ornate_chastity_cage", colourSecondary, colourSecondary, PresetColour.CLOTHING_BRASS, false);
				cage.clearEffects();
				target.equipClothingFromNowhere(cage, true, this);
			}
		}
	}
	
	public void applyCollar(GameCharacter target, GameCharacter equipper, boolean isBreederCollar) {
		if(target.getClothingInSlot(InventorySlot.NECK)!=null) {
			target.unequipClothingIntoVoid(InventorySlot.NECK, true, equipper);
		}
		
		if(isBreederCollar) {
			Colour colourPrimary = PresetColour.CLOTHING_PINK_HOT;
			Colour colourSecondary = PresetColour.CLOTHING_SILVER;
	
			AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_neck_breeder_collar", colourPrimary, colourSecondary, colourSecondary, false);
			
			collar.clearEffects();
			
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SERVITUDE, TFPotency.MINOR_BOOST, 0));
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0));
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_MASOCHIST, TFPotency.MAJOR_BOOST, 0));
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_PREGNANCY, TFPotency.MAJOR_BOOST, 0));
			
			for(int i=0;i<33;i++) { //+100 fertility
				collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0));
			}
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MINOR_BOOST, 0));
	
			target.equipClothingFromNowhere(collar, true, equipper);
			
		} else {
			Colour colourPrimary = PresetColour.CLOTHING_PINK_HOT;
			Colour colourSecondary = PresetColour.CLOTHING_SILVER;
			AbstractClothing choker = Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", colourPrimary, colourSecondary, colourSecondary, false);
			choker.setSticker("top_txt", "good");
			choker.setSticker("btm_txt", "toy");
			choker.clearEffects();
			choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SERVITUDE, TFPotency.MINOR_BOOST, 0));
			choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0));
			choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_MASOCHIST, TFPotency.MAJOR_BOOST, 0));
//			choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_BONDAGE_VICTIM, TFPotency.MAJOR_BOOST, 0));
//			choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_EXHIBITIONIST, TFPotency.MAJOR_BOOST, 0));
			target.equipClothingFromNowhere(choker, true, equipper);
		}
	}
	
	public boolean isAnalBeadsEquipped(GameCharacter target) {
		return target.getClothingInSlot(InventorySlot.ANUS)!=null && target.getClothingInSlot(InventorySlot.ANUS).getClothingType()==ClothingType.getClothingTypeFromId("norin_anal_beads_anal_beads");
	}
	
	public void applyAnalBeads(GameCharacter target, GameCharacter equipper) {
		if(isAnalBeadsEquipped(target)) {
			return;
		}
		if(target.getClothingInSlot(InventorySlot.ANUS)!=null) {
			target.unequipClothingIntoVoid(InventorySlot.ANUS, true, equipper);
		}
		target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_anal_beads_anal_beads", PresetColour.CLOTHING_PINK_HOT, false), true, this);
	}
	
	public void spawnBasementClient() {
		GenericSexualPartner client = new GenericSexualPartner();
		try {
			Main.game.addNPC(client, false);
			Main.game.setParserTarget("client", client);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		client.setOrgasmsForSatisfaction(3);
		
		client.setPlayerKnowsName(false);
		
		client.setPetName(Main.game.getPlayer(), Util.randomItemFrom(new String[] {"bitch", "cunt", "slut"}));
		
		client.unequipAllClothingIntoVoid(true, true);

		client.resetBodyToGenderPreferences(false, true);

		client.clearFetishDesires();
		client.clearFetishes();
		
		if(Util.random.nextFloat()<0.6f) {
			client.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				client.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				client.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}

		client.setGenericName("client");
		
		client.setDescription("This is one of Yui's clients, who's paid her to visit her basement and roughly fuck the pathetic sub who's tied up down there. Tonight, that pathetic sub is you.");
		
		// Determines how the client treats the player (by default they punish player):
		if(Util.random.nextInt(100)<33
				&& Main.game.getPlayer().hasVagina()
				&& !Main.game.getPlayer().isVisiblyPregnant()
				&& (!Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)
						|| Main.game.getPlayer().getStatusEffectDuration(StatusEffect.PREGNANT_0)<60*30)) { // ONly spawn a breeder NPC if the player is not pregnant and the 'risk of pregnancy' has over 30mins before resolution
			client.addFetish(Fetish.FETISH_IMPREGNATION);
			if(client.getPenisRawCumStorageValue()<250) {
				client.setPenisCumStorage(250);
				client.fillCumToMaxStorage();
				client.setPenisCumProductionRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay());
			}
		}
		
		if(!client.hasFetish(Fetish.FETISH_IMPREGNATION)) {
			if(Util.random.nextInt(100)<50) { // For an alternate intro:
				client.addFetish(Fetish.FETISH_VOYEURIST);
			}
		}
		
		
		client.addFetish(Fetish.FETISH_DOMINANT);
		client.addFetish(Fetish.FETISH_SADIST);
		client.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
		client.addFetish(Fetish.FETISH_PENIS_GIVING);

		client.setFetishDesire(Fetish.FETISH_VOYEURIST, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
		
		client.removePersonalityTraits(PersonalityCategory.SPEECH);
		
		// Remove virginities:
		client.setPenisVirgin(false);
		client.setAnalVirgin(false);
		client.setFaceVirgin(false);
		if(client.hasVagina()) {
			client.setVaginaVirgin(false);
		}
		
		client.setRaceConcealed(true);
		// This gets cleared before sex so the player can see the client's penis
		client.setExtraBlockedParts(new BlockedParts(
				DisplacementType.REMOVE_OR_EQUIP,
				new ArrayList<>(),
				new ArrayList<>(),
				new ArrayList<>(),
				new ArrayList<>(Arrays.asList(InventorySlot.values()))));
		
		client.setLocation(Main.game.getPlayer());
	}
	
	public void applyClientIntro(GameCharacter client) {
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_INCEST) && Math.random()<0.33f) {
			if(client.isFeminine()) {
				client.setGenericName("Mommy");
			} else {
				client.setGenericName("Daddy");
			}
		}
		if(client.isFeminine()) {
			client.setGenericName("Mistress");
		} else {
			client.setGenericName("Master");
		}
	}

	public void initPostClientSex() {
		if(Main.game.getPlayer().getPregnantLitter()!=null) {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getPlayer().getPregnantLitter().getTotalLitterCount()), true);
		}
	}
	
	public void applyNightOfSex(GameCharacter target, int stage) {
		int numberOfClients = 5 + Util.random.nextInt(4); // 5-8 more clients
		if(Main.game.isHourBetween(3, 12)) {
			numberOfClients = 1 + Util.random.nextInt(3); // If after 03:00, just 1-3 more clients
		}
		
		// For each client, get fucked in mouth, pussy, and ass:
		for(int i=0; i<numberOfClients; i++) {
			target.calculateGenericSexEffects(
					false, true, null,
					Util.randomItemFrom(Subspecies.getWorldSpecies(target.getWorldLocation(), target.getLocationPlaceType(), false, true).keySet()),
					Subspecies.HUMAN, new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
			
			if(target.hasVagina()) {
				target.calculateGenericSexEffects(
						false, true, null,
						Util.randomItemFrom(Subspecies.getWorldSpecies(target.getWorldLocation(), target.getLocationPlaceType(), false, true).keySet()),
						Subspecies.HUMAN, new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
			}
			if(Main.game.isAnalContentEnabled()) {
				target.calculateGenericSexEffects(
						false, true, null,
						Util.randomItemFrom(Subspecies.getWorldSpecies(target.getWorldLocation(), target.getLocationPlaceType(), false, true).keySet()),
						Subspecies.HUMAN, new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
			}
		}
	}
	
	// Alleyway methods:

	public void equipGearAlleyway(GameCharacter target, int stage) {
		Colour colourPrimary = PresetColour.CLOTHING_PINK_HOT;
		Colour colourSecondary = PresetColour.CLOTHING_SILVER;
		
		if(stage==1) {
			// Unequip everything onto the toys tile:
			Cell originalCell = target.getCell();
			Cell toysCell = Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_yui_dungeon")).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_yui_dungeon_toys"));
			
			removedClothing = target.unequipAllClothing(this, true, true);
			for(AbstractClothing c : removedClothing.values()) {
				toysCell.getInventory().addClothing(c);
			}
			
			target.setLocation(toysCell);
			removedWeapons = new HashMap<>();
			for(int i=0; i<target.getArmRows();  i++) {
				if(target.getMainWeapon(i) != null) {
					removedWeapons.put(mainSlots.get(i), target.getMainWeapon(i));
					target.unequipMainWeapon(i, true, false);
				}
				if(target.getOffhandWeapon(i) != null) {
					removedWeapons.put(offhandSlots.get(i), target.getOffhandWeapon(i));
					target.unequipOffhandWeapon(i, true, false);
				}
			}
			target.setLocation(originalCell);
			
			// Equip clothing:
			
			if(target.hasPenis()) {
				AbstractClothing cage = Main.game.getItemGen().generateClothing("innoxia_bdsm_ornate_chastity_cage", colourSecondary, colourSecondary, PresetColour.CLOTHING_BRASS, false);
				cage.clearEffects();
				target.equipClothingFromNowhere(cage, true, this);
			}
			
			target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_latex_catsuit", colourPrimary, false), true, this);
			target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_latex_hood", colourPrimary, colourPrimary, PresetColour.CLOTHING_WHITE, false), true, this);
			
	//		target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_goth_boots_fem", colourPrimary, colourSecondary, colourPrimary, false), true, this);
			target.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_elbow_gloves", colourPrimary, false), true, this);
			
			applyCollar(target, this, false);
		}
		
		if(stage==2) {
			AbstractClothing blindfold = Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", colourPrimary, colourSecondary, colourSecondary, false);
			blindfold.clearEffects();
			target.equipClothingFromNowhere(blindfold, true, this);
	
			AbstractClothing gag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_WHITE, colourPrimary, colourSecondary, false);
			gag.clearEffects();
			target.equipClothingFromNowhere(gag, true, this);
		}
	}
	
	private boolean previousClientGangVictim = false; // used to make sure that two victims don't spawn in a row
	
	public void spawnAlleywayClient(boolean gangContent) {
		GenericSexualPartner client = new GenericSexualPartner();
		try {
			Main.game.addNPC(client, false);
			Main.game.setParserTarget("client", client);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		boolean isGangVictim = false;
		boolean playerHasCreampie = !Main.game.getPlayer().getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).isEmpty() || !Main.game.getPlayer().getFluidsStoredInOrifice(SexAreaOrifice.ANUS).isEmpty();
		if(Main.game.getDialogueFlags().hasFlag("innoxia_yui_alleyway_gang_present")
				&& (!Main.game.getDialogueFlags().hasFlag("innoxia_yui_alleyway_gang_victim_cleaned")
						|| (playerHasCreampie && Math.random()<0.2f))
				&& !previousClientGangVictim) {
			isGangVictim = true;
			previousClientGangVictim = true;
			Main.game.getDialogueFlags().setFlag("innoxia_yui_alleyway_gang_victim_cleaned", true);
			
		} else {
			previousClientGangVictim = false;
		}
		
		client.setPlayerKnowsName(false);

		client.unequipAllClothingIntoVoid(true, true);

		client.resetBodyToGenderPreferences(false, true);

		client.clearFetishDesires();
		client.clearFetishes();
		
		if(Util.random.nextFloat()<0.6f) {
			client.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				client.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				client.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		
		if(isGangVictim) {
			client.setGenericName("victim");
			client.setDescription("This stranger is a victim of the gang that's taken ownership of you...");
			
		} else {
			if(gangContent) {
				if(Math.random()<0.33f) {
					client.useItem(Main.game.getItemGen().generateItem(ItemType.CIGARETTE), client, false);
				} else {
					client.addStatusEffect(StatusEffect.RECENTLY_SMOKED, 4 * 60 * 60);
				}
				client.setGenericName("gang member");
				client.setDescription("This stranger is a member of a local gang, and is interested only in fucking you...");
				client.setPetName(Main.game.getPlayer(), Util.randomItemFrom(new String[] {"bitch", "cunt", "slut"}));
				client.setOccupation(Occupation.NPC_GANG_MEMBER);
				
			} else {
				client.setGenericName("stranger");
				client.setDescription("This stranger is completely unknown to you, and is interested only in fucking you...");
				client.setPetName(Main.game.getPlayer(), Util.randomItemFrom(new String[] {"bitch", "cunt", "slut"}));
			}
		}

		// Chance for big creampie
		if(Math.random()<0.4f
				|| (!Main.game.getDialogueFlags().hasFlag("innoxia_yui_alleyway_gang_victim_cleaned") && !isGangVictim)) { // Make sure that first gang fuck is a big creampie, so follow-up victim licking makes sense
			if(Math.random()<0.5f) {
				client.addFetish(Fetish.FETISH_IMPREGNATION);
			}
			if(client.getPenisRawCumStorageValue()<250) {
				client.setPenisCumStorage(250 + Util.random.nextInt(501));
				client.fillCumToMaxStorage();
				client.setPenisCumProductionRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay());
			}
		}

		// Used for determining pullout:
		if(Math.random()<0.5f) {
			client.addFetish(Fetish.FETISH_CUM_STUD);
		}
		
		// Foot content
		if(Math.random()<(Main.game.getPlayer().hasFetish(Fetish.FETISH_FOOT_GIVING)?0.5f:0.1f) && Main.game.isFootContentEnabled()) {
			client.addFetish(Fetish.FETISH_FOOT_RECEIVING);
		}
		
		if(!isGangVictim) {
			client.addFetish(Fetish.FETISH_DOMINANT);
			client.addFetish(Fetish.FETISH_SADIST);
			client.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
		}
		client.addFetish(Fetish.FETISH_PENIS_GIVING);

		client.setFetishDesire(Fetish.FETISH_VOYEURIST, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		client.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		
		
		if(isGangVictim) {
			if(!Main.game.getPlayer().getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).isEmpty()) {
				client.addFetish(Fetish.FETISH_VAGINAL_GIVING);
				
			} else if(!Main.game.getPlayer().getFluidsStoredInOrifice(SexAreaOrifice.ANUS).isEmpty()) {
				client.addFetish(Fetish.FETISH_ANAL_GIVING);
			}
			
			client.addFetish(Fetish.FETISH_ORAL_GIVING);
			
		} else {
			if(Main.game.getPlayer().hasVagina()) {
				if(Main.game.isAnalContentEnabled() && Math.random()<0.25f) {
					client.addFetish(Fetish.FETISH_ANAL_GIVING);
				} else {				
					client.addFetish(Fetish.FETISH_VAGINAL_GIVING);
				}
				
			} else {
				client.addFetish(Fetish.FETISH_ANAL_GIVING);
			}
		}
		
		
		client.removePersonalityTraits(PersonalityCategory.SPEECH);
		
		// Remove virginities:
		client.setPenisVirgin(false);
		client.setAnalVirgin(false);
		client.setFaceVirgin(false);
		if(client.hasVagina()) {
			client.setVaginaVirgin(false);
		}
		
		client.setRaceConcealed(true);
		// This gets cleared before sex so the player can see the client's penis
		client.setExtraBlockedParts(new BlockedParts(
				DisplacementType.REMOVE_OR_EQUIP,
				new ArrayList<>(),
				new ArrayList<>(),
				new ArrayList<>(),
				new ArrayList<>(Arrays.asList(InventorySlot.values()))));
		
		client.setLocation(Main.game.getPlayer());
	}
	
	public void restoreInventory(GameCharacter target) {
		target.unequipAllClothingIntoVoid(true, true);

		// Clear this status effect description as otherwise it gives pointless info about the clothing that's already removed:
		target.getStatusEffectDescriptions().values().forEach(map->{map.remove(StatusEffect.BODY_CUM);map.remove(StatusEffect.BODY_CUM_MASOCHIST);});
		
		Cell originalCell = target.getCell();
		Cell toysCell = Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_yui_dungeon")).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_yui_dungeon_toys"));
		
		target.setLocation(toysCell);
		for(Entry<InventorySlot, AbstractClothing> entry : removedClothing.entrySet()) {
			target.equipClothingFromGround(entry.getValue(), entry.getKey(), true, target);
		}
		for(Entry<InventorySlot, AbstractWeapon> entry : removedWeapons.entrySet()) {
			if(mainSlots.contains(entry.getKey())) {
				target.equipMainWeapon(entry.getValue(), mainSlots.indexOf(entry.getKey()), false);
			} else {
				target.equipOffhandWeapon(entry.getValue(), offhandSlots.indexOf(entry.getKey()), false);
			}
			toysCell.getInventory().removeWeapon(entry.getValue());
		}
		target.setLocation(originalCell);
	}
	
	public void equipDildo(GameCharacter target) {
		AbstractClothing dildo = Main.game.getItemGen().generateClothing("innoxia_vagina_equine_dildo", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_PINK_HOT, false);
		dildo.setSticker("colour", "two");
		
		if(target.hasVagina()) {
			target.equipClothingFromNowhere(dildo, InventorySlot.VAGINA, true, this);
		} else {
			target.equipClothingFromNowhere(dildo, InventorySlot.ANUS, true, this);
		}
	}
	
	public void removeDildo(GameCharacter target) {
		if(target.hasVagina()) {
			target.unequipClothingIntoVoid(InventorySlot.VAGINA, true, this);
		} else {
			target.unequipClothingIntoVoid(InventorySlot.ANUS, true, this);
		}
	}
	
	public void applyBadEndLocationChange() {
		// move to bad end map
		Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_misc_bad_end"), PlaceType.getPlaceTypeFromId("innoxia_misc_bad_end_default"));
		
		Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_misc_bad_end_yui_home"));
		Main.game.getPlayerCell().getPlace().setName(PlaceType.getPlaceTypeFromId("innoxia_misc_bad_end_yui_home").getName());
		
		this.setLocation(Main.game.getPlayer());
		
		// Spawn other toys:
		
		
	}
}
