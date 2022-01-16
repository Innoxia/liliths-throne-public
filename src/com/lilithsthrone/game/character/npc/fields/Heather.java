package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
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
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
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
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
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
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
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
public class Heather extends NPC {

	public Heather() {
		this(false);
	}
	
	public Heather(boolean isImported) {
		super(isImported, new NameTriplet("Heather"), "Dreamstar",
				"Heather operates the stall, 'The Third Eye', in Elis's Farmer's market, from which [heather.she] sells potions and enchanted clothing, as well as telling fortunes in exchange for a Faire ticket.",
				24, Month.MAY, 7,
				5,
				Gender.N_P_TRAP, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_market"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_market_fortunes"),
				true);

//		this.setGenericName("concealed character");
		
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
		this.addSpecialPerk(Perk.SPECIAL_CLOTHING_FEMININITY_INDIFFERENCE);
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);

			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			this.addFetish(Fetish.FETISH_SIZE_QUEEN);
			
			this.setFetishDesire(Fetish.FETISH_BONDAGE_VICTIM, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DENIAL_SELF, FetishDesire.THREE_LIKE);
			
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ZERO_HATE);

			this.setSexCount(null, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), 87);
			this.setCumCount(null, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), 43);
			this.setSexCount(null, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), 244);
			this.setCumCount(null, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), 135);
		}
		
		// Body:
		
		// Core:
		this.setHeight(176);
		this.setFemininity(55);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_GREY_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, CoveringPattern.NONE, CoveringModifier.FURRY, PresetColour.COVERING_BROWN, false, PresetColour.COVERING_BROWN, false), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK_LIGHT));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK_LIGHT));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK_LIGHT));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.TRAINING_A.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		this.addNippleOrificeModifier(OrificeModifier.PUFFY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(8);
		this.setTesticleSize(TesticleSize.ONE_TINY);
		this.setPenisCumStorage(15);
		this.setPenisCumExpulsion(75);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
//		this.setVaginaVirgin(false);
//		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
//		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
//		this.setVaginaSquirter(true);
//		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
//		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
//		this.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue());
//		this.setVaginaPlasticity(OrificePlasticity.TWO_TENSILE.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(5000);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_GREY_DARK, PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_SILVER, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_GREY_DARK, PresetColour.CLOTHING_SILVER, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_GREY_DARK, PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_BLACK, false), true, this);
		
//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_SILVER, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_jewel", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ornate_chastity_cage", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, null, false), true, this);
		
		this.addStatusEffect(StatusEffect.CHASTITY_4, -1);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_SILVER, false), true, this);
		
		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			// Tramp stamp
			this.addTattoo(InventorySlot.TORSO_UNDER,
					new Tattoo(
						TattooType.getTattooTypeFromId("innoxia_hearts_hearts"),
						PresetColour.CLOTHING_PINK_LIGHT,
						PresetColour.CLOTHING_PINK,
						null,
						false,
						new TattooWriting(
								"Fill Me Up!",
								PresetColour.CLOTHING_PINK_HOT,
								false),
						new TattooCounter(
								TattooCounterType.CUM_TAKEN_ANUS,
								TattooCountType.NUMBERS,
								PresetColour.CLOTHING_PINK_HOT,
								false)));
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#d6a5e6";
	}
	
	@Override
	public void dailyUpdate() {
		if(Math.random()<0.2f) {
			this.incrementSexCount(null, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
			if(Math.random()<0.5f) {
				this.incrementCumCount(null, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
			}
		}
		if(Math.random()<0.4f) {
			this.incrementSexCount(null, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
			if(Math.random()<0.75f) {
				this.incrementCumCount(null, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
			}
		}
		
		clearNonEquippedInventory(false);

		// Transformative potions:
		// 10 generic potions:
		AbstractItem ingredient = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_rabbit_bunny_carrot_cake"));
		TFModifier primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		for(int i=0; i<10; i++) {
			try {
				if(ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0, Main.game.getPlayer(), Main.game.getPlayer())!=null) {
					AbstractItem potion = EnchantingUtils.craftItem(ingredient, Util.newArrayListOfValues(new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0)));
					potion.setName(EnchantingUtils.getPotionName(ingredient, potion.getEffects()));
					this.addItem(potion);
				}
				primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
			} catch(Exception ex) {
			}
		}
		
		// Heather's dream potions:
		Map<String, String> ingredientMap = new HashMap<>();
		ingredientMap.put("Heather's Fox Fantasy", "innoxia_race_fox_chicken_pot_pie");
		ingredientMap.put("Heather's Wolf Wish", "innoxia_race_wolf_meat_and_marrow");
		ingredientMap.put("Heather's Dog Dream", "innoxia_race_dog_canine_crunch");
		ingredientMap.put("Heather's Human Heart-throb", "innoxia_race_human_bread_roll");
		
		for(Entry<String, String> entry : ingredientMap.entrySet()) {
			ingredient = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId(entry.getValue()));
			AbstractItem potion = EnchantingUtils.craftItem(ingredient, Util.newArrayListOfValues(
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 0),
	
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_EARS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_EYES, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_TAIL, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_ARMS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_LEGS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_SKIN, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 0),
					
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_INTERNAL, TFPotency.MINOR_DRAIN, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0)
					));
			potion.setName(entry.getKey());
			this.addItem(potion);
		}
		
		
		// Transformative clothing:
		AbstractClothing cage = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_ornate_chastity_cage"),
				Util.newArrayListOfValues(PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_BRASS),
				Util.newArrayListOfValues(
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.DRAIN, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.BOOST, 55),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 8),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.DRAIN, 1),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_VAGINA, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 4),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 3),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.BOOST, 3),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_BREASTS, TFModifier.TF_MOD_ORIFICE_PUFFY, TFPotency.BOOST, 0)));
		cage.setName("Heather's Destiny");
		this.addClothing(cage, false);

		AbstractClothing bangle = Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE,
				Util.newArrayListOfValues(PresetColour.CLOTHING_SILVER),
				Util.newArrayListOfValues(
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.DRAIN, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_PENIS_RECEIVING, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_CUM_ADDICT, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_DOMINANT, TFPotency.MAJOR_DRAIN, 0)));
		bangle.setName("Heather's Advice");
		this.addClothing(bangle, false);

		AbstractClothing choker = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_velvet_choker"),
				Util.newArrayListOfValues(PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_SILVER),
				Util.newArrayListOfValues(
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.DRAIN, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_ORAL_GIVING, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 2)));
		choker.setName("Heather's Hunger");
		this.addClothing(choker, false);

		AbstractClothing anklet = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_ankle_anklet"),
				Util.newArrayListOfValues(PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT),
				Util.newArrayListOfValues(
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.DRAIN, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_ANAL_RECEIVING, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 3),
						new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 3)));
		anklet.setName("Heather's Offering");
		this.addClothing(anklet, false);
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
		return UtilText.parseFromXMLFile("places/fields/elis/market", "FORTUNES_TRANSACTION_START");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}

	@Override
	public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.removeTraderDescription, true);
		UtilText.addSpecialParsingString(itemSold.getName(), true);
		
		if((itemSold instanceof AbstractClothing) && itemSold.getName().contains("Heather's")) {
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/market", "FORTUNES_TRANSACTION_TICKET"));
			Main.game.appendToTextStartStringBuilder(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_faire_ticket"), false, true));
		} else {
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/market", "FORTUNES_TRANSACTION_NO_TICKET"));
		}
	}
	
	
	// Sex:

	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}
	
	@Override
	public void endSex() {
		this.cleanAllDirtySlots(true);
		this.equipClothing();
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

}
