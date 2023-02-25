package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
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
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
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
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.4.5
 * @version 0.4.4.5
 * @author Innoxia
 */
public class Aurokaris extends NPC {

	public Aurokaris() {
		this(false);
	}
	
	public Aurokaris(boolean isImported) {
		super(isImported, new NameTriplet("Aurokaris"), "Agelada",
				"Aurokaris is an Amazon, a member of the all-female warrior community who live in the town of Themiscyra.",
				19, Month.JANUARY, 1,
				25,
				Gender.F_V_B_FEMALE, Subspecies.COW_MORPH, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_themiscyra"), PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra_palace"),
				true);
		
		if(!isImported) {
			this.setGenericName("Amazonian cow-girl");
			this.setPlayerKnowsName(false);
		}
	}
	
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.5.5")) {
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}
	
	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		this.equipMove("strike");
		this.equipMove("offhand-strike");
		this.equipMove("twin-strike");
		this.equipMove("block");
		this.equipAllSpellMoves();
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.BRAVE,
					PersonalityTrait.INNOCENT,
					PersonalityTrait.KIND,
					PersonalityTrait.CONFIDENT);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_AMAZONIAN);
			
			this.clearFetishDesires();
			this.clearFetishes();

			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			this.addFetish(Fetish.FETISH_BREASTS_OTHERS);
			this.addFetish(Fetish.FETISH_ARMPIT_GIVING);
			this.addFetish(Fetish.FETISH_PURE_VIRGIN);

			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.ONE_DISLIKE);
		}
		
		// Body:
		
		// Core:
		this.setHeight(194);
		this.setFemininity(80);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		this.setHornType(HornType.CURVED);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_COW_MORPH, PresetColour.EYE_HAZEL));
		this.setSkinCovering(new Covering(BodyCoveringType.BOVINE_FUR, CoveringPattern.NONE, CoveringModifier.SHAGGY, PresetColour.COVERING_BROWN, false, PresetColour.COVERING_BROWN, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_TANNED), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_TANNED), false);
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_WHITE), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_BOVINE_FUR, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.BRAIDED);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_BOVINE_FUR, PresetColour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, CoveringPattern.NONE, CoveringModifier.METALLIC, PresetColour.COVERING_SILVER, false, PresetColour.COVERING_SILVER, false));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, CoveringPattern.NONE, CoveringModifier.METALLIC, PresetColour.COVERING_SILVER, false, PresetColour.COVERING_SILVER, false));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED_LIGHT));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE_LIGHT));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.FOUR_MASSIVE);
		this.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		
//		this.setBreastMilkStorage(4000);
//		this.fillMilkToMaxStorage();
//		this.setBreastLactationRegeneration(10_000);
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipMainWeapon(Main.game.getItemGen().generateWeapon("innoxia_spear_dory", DamageType.FIRE), 0, false);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_peplos", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_celtic_barrette", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_wrap_ring", PresetColour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_SILVER, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return PresetColour.BASE_PERIWINKLE.toWebHexString();
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
		this.equipClothing();
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public void turnUpdate() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.getWorldTypeFromId("innoxia_fields_themiscyra")
				&& Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_3_E_THEMISCYRA_ATTACK
				&& !Main.game.isBadEnd()
				&& this.isPlayerKnowsName()) {
			this.setLocation(Main.game.getPlayer(), false);
		}
	}

	@Override
	public void hourlyUpdate() {
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_3_E_THEMISCYRA_ATTACK)
				&& !Main.game.getCharactersPresent().contains(this)
				 && !Main.game.isBadEnd()) {
			if(Main.game.isHourBetween(8, 18)) {
				this.setLocation("innoxia_fields_elis_amazon_camp", "innoxia_fields_elis_amazon_camp_trader", false);
			} else {
				this.setLocation("innoxia_fields_elis_amazon_camp", "innoxia_fields_elis_amazon_camp_aurokaris", true);
			}
		}
	}

	@Override
	public void dailyUpdate() {
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_3_E_THEMISCYRA_ATTACK) && !Main.game.isBadEnd()) {
			clearNonEquippedInventory(false);
			
			// Clothing:
			List<AbstractClothingType> clothingTypesToSell = new ArrayList<>();
			clothingTypesToSell.add(ClothingType.getClothingTypeFromId("innoxia_torso_peplos"));
			clothingTypesToSell.add(ClothingType.getClothingTypeFromId("innoxia_torsoOver_himation"));
			clothingTypesToSell.add(ClothingType.getClothingTypeFromId("innoxia_foot_gladiator_sandals"));
			clothingTypesToSell.add(ClothingType.getClothingTypeFromId("innoxia_finger_meander_ring"));
			//TODO male clothing
			for(AbstractClothingType type : clothingTypesToSell) {
				for(int i=0; i<Util.random.nextInt(4)+3; i++) {
					this.addClothing(Main.game.getItemGen().generateClothing(type, false), false);
				}
			}
			
			// Weapons:
			List<AbstractWeaponType> weaponTypesToSell = new ArrayList<>();
			weaponTypesToSell.add(WeaponType.getWeaponTypeFromId("innoxia_spear_dory"));
			weaponTypesToSell.add(WeaponType.getWeaponTypeFromId("innoxia_europeanSwords_xiphos"));
			weaponTypesToSell.add(WeaponType.getWeaponTypeFromId("innoxia_bow_recurve"));
	//		weaponTypesToSell.add(WeaponType.getWeaponTypeFromId("innoxia_europeanSwords_xiphos")); //TODO shield
			for(AbstractWeaponType type : weaponTypesToSell) {
				for(int i=0; i<Util.random.nextInt(4)+2; i++) {
					this.addWeapon(Main.game.getItemGen().generateWeapon(type), false);
				}
			}
			
			// Items:
			List<AbstractItemType> itemTypesToSell = new ArrayList<>();
			for(AbstractItemType item : ItemType.getAllItems()) {
				if(item.getItemTags().contains(ItemTag.SOLD_BY_RALPH)
						&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())
						&& !item.getItemTags().contains(ItemTag.ALCOHOLIC)
						&& (item.getEnchantmentItemType(null)==ItemType.POTION || item.getEnchantmentItemType(null)==ItemType.ELIXIR)) {
					itemTypesToSell.add(item);
				}
			}
			Collections.shuffle(itemTypesToSell);
			int listSize = itemTypesToSell.size();
			itemTypesToSell.subList(0, itemTypesToSell.size()-(listSize<=8?1:8)).clear();
			for(AbstractItemType type : itemTypesToSell) {
				this.addItem(Main.game.getItemGen().generateItem(type), !type.isConsumedOnUse()?1:(4+Util.random.nextInt(7)), false, false);
			}
			
			// Specials:
			itemTypesToSell.clear();
			itemTypesToSell.add(ItemType.getItemTypeFromId("innoxia_potions_amazonian_ambrosia"));
			itemTypesToSell.add(ItemType.getItemTypeFromId("innoxia_potions_amazons_secret"));
			for(AbstractItemType type : itemTypesToSell) {
				for(int i=0; i<Util.random.nextInt(6)+3; i++) {
					this.addItem(Main.game.getItemGen().generateItem(type), false);
				}
			}
		}
	}

	@Override
	public float getSellModifier(AbstractCoreItem item) {
		return 2.0f;
	}
	
	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("places/fields/elis/amazon_camp", "AUROKARIS_TRANSACTION_START");
	}

	public String applyFeminisationPotion(GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		if(target.getFemininityValue()<80) {
			sb.append(target.setFemininity(80));
		}
		if(target.getBreastRawSizeValue()<CupSize.C.getMeasurement()) {
			sb.append(target.setBreastSize(CupSize.C));
		}
		if(target.hasPenis()) {
			sb.append(target.setPenisType(PenisType.NONE));
		}
		if(!target.hasVagina()) {
			sb.append(target.setVaginaType(VaginaType.getVaginaTypes(target.getRace()).get(0)));
		}
		
		return sb.toString();
	}
	
}
