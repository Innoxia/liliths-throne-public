package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
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
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
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
public class Penelope extends NPC {

	public Penelope() {
		this(false);
	}
	
	public Penelope(boolean isImported) {
		super(isImported, new NameTriplet("Penelope"), "Fear",
				"A beautiful border collie-girl, who is the owner and manager of Elis's spa; 'Woolly Heaven'.",
				29, Month.FEBRUARY, 21, 
				15, Gender.F_P_V_B_FUTANARI, Subspecies.DOG_MORPH_BORDER_COLLIE, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_shops"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_shops_spa"),
				true);

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
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TATTOOIST);
		this.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 3),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_BEAUTICIAN);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_PENIS_GIVING);
			
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ONE_DISLIKE);
		}
		
		// Body:
		
		// Core:
		this.setHeight(175);
		this.setFemininity(95);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DOG_MORPH, PresetColour.EYE_BLUE));
		this.setSkinCovering(new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_CANINE_FUR, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.BRAIDED);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_CANINE_FUR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED_DARK));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setBreastRows(3);
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.THREE_LARGE.getValue());
		this.setAreolaeSize(AreolaeSize.THREE_LARGE.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(16);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setInternalTesticles(true);
		this.setPenisCumStorage(50);
		this.setPenisCumExpulsion(75);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		this.setHairStyle(HairStyle.BRAIDED);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_celtic_barrette", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_RED_BURGUNDY, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_gemstone_necklace", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_RED_BURGUNDY, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_VIRGIN_KILLER_SWEATER, PresetColour.CLOTHING_RED_BURGUNDY, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_RED_BURGUNDY, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_platform_boots", PresetColour.CLOTHING_RED_BURGUNDY, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_wrap_ring", PresetColour.CLOTHING_GOLD, false), true, this);

		this.setPiercedEar(true);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.setPiercedTongue(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_GOLD, false), true, this);
		this.setPiercedNipples(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", PresetColour.CLOTHING_GOLD, false), true, this);
		
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	@Override
	public void hourlyUpdate(int hour) {
		if(!Main.game.isInSex()) {
			this.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), this, false);
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
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_NORMAL;
	}

	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(9, 24)) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);

		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_KATE)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addItem(Main.game.getItemGen().generateItem(item), !item.isConsumedOnUse()?1:(6+Util.random.nextInt(12)), false, false);
			}
		}
		
		List<AbstractClothing> clothingToSell = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE)
					&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				clothingToSell.add(Main.game.getItemGen().generateClothing(clothing, false));
			}
		}

		for(AbstractClothing c : clothingToSell) {
			this.addClothing(c, 2+Util.random.nextInt(5), false, false);
		}
		
		for(AbstractClothing c : Main.game.getCharacterUtils().generateEnchantedClothingForTrader(this, clothingToSell, 6, 2)) {
			this.addClothing(c, false);
		}
	}

	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("places/fields/elis/shops/spa", "TRANSACTION_START");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return (item instanceof AbstractClothing)
				&& item.getItemTags().contains(ItemTag.SOLD_BY_KATE)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY);
	}

	@Override
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, true);
		UtilText.addSpecialParsingString(itemSold.getName(), true);
		UtilText.addSpecialParsingString(Util.intToString(quantity*individualPrice), false);
		
		if(soldToPlayer) {
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/shops/spa", "BUY_TRANSACTION_COMPLETE"));
		} else {
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/shops/spa", "SELL_TRANSACTION_COMPLETE"));
		}
	}
	
	@Override
	public void endSex() {
		this.applyWash(true, true, null, 0);
		this.equipClothing();
	}
	
	// Outfit changes for catwalk sex:
	
	public void equipOutfitBall() {
		this.unequipAllClothingIntoVoid(true, true);
		this.setHairStyle(HairStyle.WAVY);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_tiara", PresetColour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_diamond_necklace", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_WHITE, null, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("phlarx_dresses_ballgown", PresetColour.CLOTHING_BLUE_LIGHT, PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_elbow_length_gloves", PresetColour.CLOTHING_BLUE_LIGHT, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_strapless_bra", PresetColour.CLOTHING_RED_BURGUNDY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_RED_BURGUNDY, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLUE_LIGHT, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_pearl_studs", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_SILVER, null, false), true, this);
	}

	public void equipOutfitTown() {
		this.unequipAllClothingIntoVoid(true, true);
		this.setHairStyle(HairStyle.PONYTAIL);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_aviators", PresetColour.CLOTHING_GOLD, false), true, this);

		if(Main.game.isFurryHairEnabled()) {
			AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_WHITE, false);
			scrunchie.setPattern(Pattern.getPatternIdByName("none"));
			this.equipClothingFromNowhere(scrunchie, true, this);
		}
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_blouse", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLUE_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_open_front_cardigan", PresetColour.CLOTHING_TAN, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GOLD, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_chelsea_boots", PresetColour.CLOTHING_TAN, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BRASS, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
	}

	public void equipOutfitAlley() {
		this.unequipAllClothingIntoVoid(true, true);
		this.setHairStyle(HairStyle.MESSY);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_velvet_choker", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_ROSE_GOLD, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_plunge_club_dress", PresetColour.CLOTHING_PINK_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_strappy_sandals", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_TAN, PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
		
		AbstractClothing thong = Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_ORANGE_DARK, false);
		thong.setPattern(Pattern.getPatternIdByName("tiger_striped"));
		thong.setPatternColour(0, PresetColour.CLOTHING_BLACK);
		thong.setPatternColour(1, PresetColour.CLOTHING_ORANGE_BRIGHT);
		this.equipClothingFromNowhere(thong, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_ankle_anklet", PresetColour.CLOTHING_ROSE_GOLD, PresetColour.CLOTHING_ROSE_GOLD, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_wrap_ring", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
		
	}

	public void equipOutfitLingerie() {
		this.unequipAllClothingIntoVoid(true, true);
		this.setHairStyle(HairStyle.LOOSE);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_chemise", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.HIPS_SUSPENDER_BELT, PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("corpseBloom_toeless_stockings_toeless_stockings", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_SILVER, false), true, this);
	}

	public void equipOutfitLatex() {
		this.unequipAllClothingIntoVoid(true, true);
		this.setHairStyle(HairStyle.BUN);

		if(Main.game.isFurryHairEnabled()) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_sticks", PresetColour.CLOTHING_RED_BRIGHT, false), true, this);
		}
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_croptop", PresetColour.CLOTHING_RED_BRIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_corset", PresetColour.CLOTHING_RED_BRIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_RED_BRIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_stockings", PresetColour.CLOTHING_RED_BRIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_dildos_realistic_dildo", PresetColour.CLOTHING_PURPLE_DARK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_ear_bats", false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
	}
}
