package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.rendering.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.LegType;
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
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.pregnancy.FertilisationType;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
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
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.PregnancyDescriptor;
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
public class Fae extends NPC {

	public Fae() {
		this(false);
	}
	
	public Fae(boolean isImported) {
		super(isImported, new NameTriplet("Fae"), "Woodward",
				"Fae is the younger sister of Silvia, with whom she runs the clothing stall 'Rainbow's End' in the Farmer's Market at Elis."
						+ " Being far more confident and assertive than her sister, Fae is the one who handles most of the interactions with their stall's customers.",
				30, Month.JANUARY, 19,
				15,
				Gender.F_P_V_B_FUTANARI, Subspecies.FOX_MORPH, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_market"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_market_clothing"),
				true);

		this.setGenericName("green-eyed foxtauress");
		
		if(!isImported) {
			dailyUpdate();
			this.setPlayerKnowsName(false);
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
				Util.newArrayListOfValues(),
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
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);

			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			this.addFetish(Fetish.FETISH_PENIS_GIVING);

			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		
		// Body:
		this.setLegConfiguration(LegType.FOX_MORPH, LegConfiguration.QUADRUPEDAL, true);
		this.setTailGirth(PenetrationGirth.FIVE_THICK);
		
		// Core:
		this.setHeight(195);
		this.setFemininity(95);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FOX_MORPH, PresetColour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.MARKED, CoveringModifier.SMOOTH, PresetColour.COVERING_ORANGE, false, PresetColour.COVERING_WHITE, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FOX_FUR, CoveringPattern.HIGHLIGHTS, CoveringModifier.FURRY, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_RED, false), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.PONYTAIL);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, PresetColour.COVERING_WHITE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_BLACK));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_BLACK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_CLEAR));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(35);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(350);
		this.setPenisCumExpulsion(75);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setVaginaPlasticity(OrificePlasticity.TWO_TENSILE.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(5000);
		
		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_BLACK, false);
		scrunchie.setPattern(Pattern.getPatternIdByName("polka_dots_big"));
		scrunchie.setPatternColour(0, PresetColour.CLOTHING_RED_BURGUNDY);
		scrunchie.setPatternColour(1, PresetColour.CLOTHING_BLACK);
		this.equipClothingFromNowhere(scrunchie, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_gemstone_necklace", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_ankle_anklet", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD, null, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("TonyJC_one_strap_crop_tank_top", PresetColour.CLOTHING_RED_BURGUNDY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_womens_leather_jacket", PresetColour.CLOTHING_BLACK, false), true, this);
		AbstractClothing jacket = this.getClothingInSlot(InventorySlot.TORSO_OVER);
		this.isAbleToBeDisplaced(jacket, DisplacementType.UNZIPS, true, true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_GOLD, false), true, this);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.setPiercedTongue(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_TONGUE, true, this);
		this.setPiercedNipples(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_NIPPLE, true, this);
		this.setPiercedNavel(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, null, false), InventorySlot.PIERCING_STOMACH, true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#ff99cb";
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);

		List<AbstractClothingType> clothingTypesToSell = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN)
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
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(8, 19)) {
				this.returnToHome(); // Stall in farmer's market
				
			} else if(Main.game.getDialogueFlags().hasFlag("innoxia_elis_market_fae_passionate")
					&& Main.game.isHourBetween(19, 22)
					&& !Main.game.getDialogueFlags().hasFlag("innoxia_tavern_fae_met")) { // After meeting they go home
				if(this.getWorldLocation()!=WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")) {
					this.setRandomLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_seating"), false);
					Main.game.getNpc(Silvia.class).setLocation(this);
				}
				
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
		return UtilText.parseFromXMLFile("places/fields/elis/market", "CLOTHING_TRANSACTION_START");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return (item instanceof AbstractClothing)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY);
	}

	@Override
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		if(soldToPlayer) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, true);
			UtilText.addSpecialParsingString(itemSold.getName(), true);
			
			if(individualPrice>500) {
				Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/market", "CLOTHING_TRANSACTION_TICKET"));
				Main.game.appendToTextStartStringBuilder(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_faire_ticket"), false, true));
			} else {
				Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/market", "CLOTHING_TRANSACTION_NO_TICKET"));
			}
		}
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public String rollForPregnancy(GameCharacter partner, float cumQuantity, boolean directSexInsemination, FertilisationType fertilisationType, AbstractAttribute virilityAttribute) {
		if(!partner.isPlayer()) { // Only the player can impregnate Fae & Silvia
			return PregnancyDescriptor.NO_CHANCE.getDescriptor(this, partner, directSexInsemination);
		}
		return super.rollForPregnancy(partner, cumQuantity, directSexInsemination, fertilisationType, virilityAttribute);
	}
}
