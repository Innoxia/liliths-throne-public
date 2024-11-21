package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.BasicDoll;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.ParserTarget;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerLoader;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Innoxia
 */
public class Saellatrix extends NPC {

	public Saellatrix() {
		this(false);
	}
	
	public Saellatrix(boolean isImported) {
		super(isImported, new NameTriplet("Saellatrix"), "Loviennemartuilani",
				"Saellatrix is a recognised daughter of the elder lilin, Lovienne, and as such commands an immense deal of respect."
					+ " The fact that she works at her mother's sex shop shows how important the business is to Lovienne.",
				187, Month.AUGUST, 2,
				35, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_counter"),
				true);
		
		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.setGenericName("big-chested succubus");
			dailyUpdate();
		}
	}
	
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9")) {
			this.setStartingBody(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.1")) {
			this.addFetish(Fetish.FETISH_SADIST);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 1)));
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

			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_SADIST);

			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASTURBATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_TRANSFORMATION_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SIZE_QUEEN, FetishDesire.THREE_LIKE);
		}
		
		// Body:
		this.setAgeAppearanceAbsolute(38);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());
		this.setWingType(WingType.NONE);
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.TWO_NARROW);

		// Core:
		this.setHeight(172);
		this.setFemininity(100);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED_DARK), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, CoveringPattern.OMBRE, CoveringModifier.SMOOTH, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_RED_DARK, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, PresetColour.SKIN_EBONY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_EBONY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.SKIN_EBONY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);

		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_FEATHER, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_BLACK, false), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.BOB_CUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_BLACK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_BLACK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_PINK_LIGHT));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_BLACK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setFaceDepth(OrificeDepth.SIX_CAVERNOUS.getValue());
		this.addFaceOrificeModifier(OrificeModifier.TENTACLED);
		this.addFaceOrificeModifier(OrificeModifier.RIBBED);
		this.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.FOUR_ABSURDLY_LONG.getMedianValue());
		this.addTongueModifier(TongueModifier.STRONG);
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.HH.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.FOUR_MASSIVE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		this.setNippleCapacity(Capacity.TWO_TIGHT.getMedianValue(), true);
		this.setNippleDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
		this.setNippleElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setNipplePlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.addNippleOrificeModifier(OrificeModifier.TENTACLED);
		this.addNippleOrificeModifier(OrificeModifier.RIBBED);
		this.addNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addNippleOrificeModifier(OrificeModifier.PUFFY);
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FIVE_HUGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssDepth(OrificeDepth.SIX_CAVERNOUS.getValue());
		this.setAssWetness(Wetness.FIVE_SLOPPY);
		this.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setAssPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.addAssOrificeModifier(OrificeModifier.TENTACLED);
		this.addAssOrificeModifier(OrificeModifier.RIBBED);
		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addAssOrificeModifier(OrificeModifier.PUFFY);
		
		// Penis:
		this.setPenisVirgin(false);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaDepth(OrificeDepth.SIX_CAVERNOUS.getValue());
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.addVaginaOrificeModifier(OrificeModifier.TENTACLED);
		this.addVaginaOrificeModifier(OrificeModifier.RIBBED);
		this.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(20_000);

		this.addTattoo(InventorySlot.GROIN,
				new Tattoo("innoxia_symbol_pentagram",
					PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK,
					false, null, null));

		this.addTattoo(InventorySlot.TORSO_OVER,
				new Tattoo("innoxia_symbol_tribal",
					PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK,
					false, null, null));
		this.addTattoo(InventorySlot.TORSO_UNDER,
				new Tattoo("innoxia_symbol_tribal",
					PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK,
					false, null, null));

		this.addTattoo(InventorySlot.NIPPLE,
				new Tattoo("innoxia_misc_none",
					PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK,
					false,
					new TattooWriting(
							"&#9829; Fuck Hole &#9829; | &#9829; Cock Sleeve &#9829;",
							PresetColour.CLOTHING_BLACK,
							false),
					null));

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_thick_rim_glasses", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_velvet_choker", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GUNMETAL, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_meander_ring", PresetColour.CLOTHING_GUNMETAL, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_feminine_short_sleeve_shirt", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_feminine_blazer", PresetColour.CLOTHING_BLACK, false), true, this);

//		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GUNMETAL, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_mini_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_anal_beads_anal_beads", PresetColour.CLOTHING_PINK_HOT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_vagina_insertable_dildo", PresetColour.CLOTHING_PINK_HOT, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_GUNMETAL, false), true, this);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ball_stud", PresetColour.CLOTHING_GUNMETAL, false), true, this);
		this.setPiercedTongue(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_GUNMETAL, false), InventorySlot.PIERCING_TONGUE, true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getSpeechColour() {
		return "#eb6a91";
	}
	
	public void incrementDollsSold(int increment) {
		if(!Main.game.getDialogueFlags().hasSavedLong("saellatrix_dolls_sold")) {
			Main.game.getDialogueFlags().setSavedLong("saellatrix_dolls_sold", 3109);
		}
		Main.game.getDialogueFlags().incrementSavedLong("saellatrix_dolls_sold", increment);
	}
	
	public int getDollsSold() {
		return (int) Main.game.getDialogueFlags().getSavedLong("saellatrix_dolls_sold");
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);
		
		incrementDollsSold(1+Util.random.nextInt(4));
		
		for(AbstractWeaponType wt : WeaponType.getAllWeapons()) {
			if(wt.getItemTags().contains(ItemTag.SOLD_BY_FINCH)
					&& (!wt.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addWeapon(Main.game.getItemGen().generateWeapon(wt), false);
			}
		}
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_FINCH)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addItem(Main.game.getItemGen().generateItem(item), false);
			}
		}
		
		List<AbstractClothing> clothingToSell = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if((clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_FINCH)
					|| clothing.getClothingSet()==SetBonus.getSetBonusFromId("sage_ltxset")
					|| clothing.getClothingSet()==SetBonus.getSetBonusFromId("innoxia_bdsm"))
				&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				AbstractClothing cl = Main.game.getItemGen().generateClothing(clothing, false);
				for(ItemEffect ie : new ArrayList<>(cl.getEffects())) {
					if(ie.getSecondaryModifier()==TFModifier.CLOTHING_ENSLAVEMENT) {
						cl.removeEffect(ie);
					}
				}
				clothingToSell.add(cl);
			}
		}

		for(AbstractClothing c : clothingToSell) {
			this.addClothing(c, 4+Util.random.nextInt(8), false, false);
		}
		
		for(AbstractClothing c : Main.game.getCharacterUtils().generateEnchantedClothingForTrader(this, clothingToSell, 4, 2)) {
			this.addClothing(c, false);
		}
		
		this.addItem(Main.game.getItemGen().generateItem("innoxia_race_doll_silic_oil"), 50, false, false);
	}
	
	@Override
	public void turnUpdate() {
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(this)) {
			if(Main.sex.getInitialSexManager() == SexManagerLoader.getSexManagerFromId("innoxia_dominion_sex_shop_saellatrix_blowjob") && this.getClothingInSlot(InventorySlot.NECK)==null) {
				Main.game.getDialogueFlags().setFlag("innoxia_sex_shop_choker_snapped", true);
			}
		}
		if(Main.game.getDialogueFlags().hasFlag("innoxia_sex_shop_saellatrix_following")) {
			this.setLocation(Main.game.getPlayer());
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
		return UtilText.parseFromXMLFile("places/dominion/sex_shop/generic", "TRANSACTION_START");
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
	public float getSellModifier(AbstractCoreItem item) {
		if(Main.game.getDialogueFlags().hasFlag("innoxia_sex_shop_discount")) {
			return 2 * 0.75f;
		} else if(Main.game.getDialogueFlags().hasFlag("innoxia_sex_shop_penalty")) {
			return 4f;
		}
		return 2f;
	}

	@Override
	public void endSex() {
		removePenis();
		this.applyWash(true, true, null, 0);
	}

	@Override
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest) {
		return true;
	}
	
	@Override
	public Value<AbstractClothing, String> getSexClothingToEquip(GameCharacter partner, boolean inQuickSex) {
		return null; // Do not equip anything onto the player during sex
	}
	
	@Override
	public int calculateSexTypeWeighting(SexType type, GameCharacter target, List<SexType> request, boolean lustOrArousalCalculation) {
		if(Main.sex.getInitialSexManager() == SexManagerLoader.getSexManagerFromId("innoxia_dominion_sex_shop_saellatrix_fucked")) {
			if(target.isPlayer() && type.getPerformingSexArea()!=null && type.getPerformingSexArea()!=SexAreaPenetration.PENIS) {
				return -10_000; // Really wants to use her cock
			}
		}
		if(Main.sex.getInitialSexManager() == SexManagerLoader.getSexManagerFromId("innoxia_dominion_sex_shop_saellatrix_nipple_fuck")) {
			if(target.isPlayer() && type.getPerformingSexArea()!=null && type.getPerformingSexArea()!=SexAreaOrifice.NIPPLE && type.getTargetedSexArea()!=null && type.getTargetedSexArea()!=SexAreaPenetration.PENIS) {
				return -10_000; // Really wants to have her nipples fucked
			}
		}
		return super.calculateSexTypeWeighting(type, target, request, lustOrArousalCalculation);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	public void growPenis(boolean exosePenis) {
		if(exosePenis) {
			this.displaceClothingForAccess(CoverableArea.PENIS, null);
			this.unequipClothingIntoVoid(InventorySlot.GROIN, true, this);
		}
		if(!this.isPregnant()) {
			this.setVaginaType(VaginaType.NONE);
			this.setInternalTesticles(false);
		} else {
			this.setInternalTesticles(true);
		}
		
		this.setPenisType(PenisType.DEMON_COMMON);
		this.setPenisGirth(PenetrationGirth.SIX_CHUBBY);
		this.setPenisSize(38);
		this.setTesticleSize(TesticleSize.FIVE_MASSIVE);
		this.setPenisCumStorage(1500);
		this.fillCumToMaxStorage();
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.KNOTTED);
		this.addPenisModifier(PenetrationModifier.TENTACLED);
		this.addPenisModifier(PenetrationModifier.RIBBED);
		this.addPenisModifier(PenetrationModifier.VEINY);
	}
	
	public void removePenis() {
		this.setPenisType(PenisType.NONE);
		this.setVaginaType(VaginaType.DEMON_COMMON);
	}
	
	public boolean isPlayerVisited() {
		return !Main.game.getCharactersPresent(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_display")).isEmpty();
	}
	
	public void initDemoDoll() {
		boolean initDoll = false;
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.2")) {
			// Remove old GenericSexualPartner doll so it can be replaced with BasicDoll:
			List<NPC> characters = Main.game.getCharactersPresent(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_display"));
			for(NPC npc : new ArrayList<>(characters)) {
				if(npc instanceof GenericSexualPartner && npc.getGenericName().equals("demo doll")) {
					Main.game.banishNPC(npc);
					initDoll = true;
				}
			}
		}
		if(!isPlayerVisited() || initDoll) {
			NPC doll = new BasicDoll(false);
			doll.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, true);
			doll.setBodyMaterial(BodyMaterial.SILICONE);
			doll.setPlayerKnowsName(false);
			doll.setName("doll");
			doll.setSurname("#01734");
			doll.setGenericName("demo doll");
			doll.setDescription("The demo doll works in Lovienne's Luxuries, providing oral sex to potential customers.");

			doll.setBirthday(doll.getBirthday().minusDays((365*4) + Util.random.nextInt(365))); // Creation date is 4-5 years before encounter
			
			doll.setPetName(Main.game.getPlayer(), "master");
			
			doll.completeVirginityLoss();
			
			doll.setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_display"), true);
			AbstractBodyCoveringType dollCovering = BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SILICONE, BodyCoveringCategory.MAIN_SKIN);
			doll.setSkinCovering(new Covering(dollCovering, CoveringPattern.NONE, CoveringModifier.GLOSSY, PresetColour.COVERING_PINK, false, PresetColour.COVERING_PINK, false), true);
			
			doll.addTattoo(InventorySlot.EYES,
					new Tattoo("innoxia_property_barcode",
							PresetColour.CLOTHING_WHITE,
							false,
							new TattooWriting(
									"DEMONSTRATION MODEL - NOT FOR SALE",
									PresetColour.CLOTHING_WHITE,
									false),
							null));
			
			doll.unequipAllClothingIntoVoid(true, true);
			doll.setMoney(0);
			
			doll.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_heart", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_DARK, null, false), true, this);
			doll.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_anus_ribbed_dildo", PresetColour.CLOTHING_PINK_HOT, false), InventorySlot.VAGINA, true, this);
			
			StatusEffect.STRETCHING_ORIFICE.applyEffect(doll, 60*60*24, 0);
			
			try {
				Main.game.addNPC(doll, false);
				ParserTarget.addAdditionalParserTarget("demoDoll", doll);
				
				Main.game.getNpc(Saellatrix.class).addSlave(doll);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isFactoryTourTimeUp() {
		return isFactoryTourTimeUp(0);
	}
	
	public boolean isFactoryTourTimeUp(int additionalSecondsPassed) { // additionalSecondsPassed will normally be 30 to account for dialogue seconds passed, as the seconds are not incremented until after preParsingEffects, in which this method is used
		long secondsAtStart = Main.game.getDialogueFlags().getSavedLong("saellatrix_tour_start");
		long secondsRemaining = (10 * 60) - (Main.game.getSecondsPassed() - secondsAtStart) - additionalSecondsPassed; 
		return secondsRemaining<=0;
	}
	
	public String getFactoryTimeRemaining(int extraSeconds) {
		if(!Main.game.getDialogueFlags().hasFlag("innoxia_sex_shop_saellatrix_following")) {
			return "";
		}
		
		long secondsAtStart = Main.game.getDialogueFlags().getSavedLong("saellatrix_tour_start");
		long secondsRemaining = (10 * 60) - (Main.game.getSecondsPassed() - secondsAtStart) - extraSeconds;
		int minutes = (int) (secondsRemaining/60);
		int seconds = (int) (secondsRemaining%60);
		
		return "<p style='text-align:center;'>"
					+ "Tour time remaining: "
					+ (minutes>=2
							?"[style.colourMinorGood("
							:"[style.colourMinorBad(")
					+ String.format("%02d", minutes)
					+ ":"
					+ String.format("%02d", seconds)
					+")]"
				+ "</p>";
	}
	
	
	// Methods for during the actual factory side quest with Fiammetta:
	
	private static Map<AbstractFetish, String> sisterFetishesToFlagIds = Util.newHashMapOfValues(
			new Value<>(Fetish.FETISH_ANAL_RECEIVING, "innoxia_doll_factory_succubus_seen_anal"),
			new Value<>(Fetish.FETISH_PURE_VIRGIN, "innoxia_doll_factory_succubus_seen_virgin"),
			new Value<>(Fetish.FETISH_ORAL_GIVING, "innoxia_doll_factory_succubus_seen_oral"),
			new Value<>(Fetish.FETISH_BREASTS_SELF, "innoxia_doll_factory_succubus_seen_breasts"),
			new Value<>(Fetish.FETISH_PENIS_GIVING, "innoxia_doll_factory_succubus_seen_penis"));
	
	public AbstractFetish getNextSisterSlaveFetish() {
//		return Fetish.FETISH_PENIS_GIVING;
		List<AbstractFetish> fetishes = new ArrayList<>(sisterFetishesToFlagIds.keySet());
		
		for(Entry<AbstractFetish, String> entry : sisterFetishesToFlagIds.entrySet()) {
			if(Main.game.getDialogueFlags().hasFlag(entry.getValue()) || (entry.getKey()==Fetish.FETISH_ANAL_RECEIVING && !Main.game.isAnalContentEnabled())) {
				fetishes.remove(entry.getKey());
			}
		}
		
		if(!fetishes.isEmpty()) {
			return Util.randomItemFrom(fetishes);
		}
		
		return null;
	}
	
	public boolean isAnySistersAvailable() {
		return getNextSisterSlaveFetish()!=null;
	}

	public void generateSister() {
		AbstractFetish fetish = getNextSisterSlaveFetish();
		DollFactorySuccubus sister = null;
		for(NPC npc : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
			if(npc instanceof DollFactorySuccubus && npc.hasFetish(fetish)) {
				sister = (DollFactorySuccubus) npc;
				Main.game.setParserTarget("sister", sister);
				return;
			}
		}
		if(sister==null) {
			sister = new DollFactorySuccubus();
			try {
				Main.game.addNPC(sister, false);
				initSister(sister, fetish);
				Main.game.setParserTarget("sister", sister);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	private void initSister(DollFactorySuccubus sister) {
//		initSister(sister, getNextSisterSlaveFetish());
//	}
	
	private void initSister(DollFactorySuccubus sister, AbstractFetish fetishFocus) {
		String chokerTopText = "worthless";
		String chokerBottomText = "bitch";
		String crimeText = "";
		
		sister.addFetish(fetishFocus);
		Main.game.getDialogueFlags().setFlag(sisterFetishesToFlagIds.get(fetishFocus), true); // So that no repeats occur
		
		sister.setAffection(this, 100);
		this.setAffection(sister, -100);
		
		sister.setMother(Main.game.getNpc(Lovienne.class));
		
		// Perks:
		sister.resetSpecialPerksMap();
		sister.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(sister,
				Util.newArrayListOfValues(
						Perk.BARREN,
						Perk.NYMPHOMANIAC),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 1)));
		
		// Bald:
		sister.setHairLength(0);
		sister.setHairStyle(HairStyle.NONE);
		// Stunted horns:
		sister.setHornType(HornType.SWEPT_BACK);
		sister.setHornLength(HornLength.ZERO_TINY.getMedianValue());
		sister.setWingType(WingType.NONE);
		sister.setTailType(TailType.DEMON_COMMON);
		sister.setLegType(LegType.DEMON_HOOFED);
		
		sister.setAgeAppearanceAbsolute(24+Util.random.nextInt(11));

		sister.setFaceVirgin(false);
		sister.setNippleVirgin(false);
		sister.setAssVirgin(false);
		sister.setPenisVirgin(false);
		sister.setVaginaVirgin(false);
		
		if(fetishFocus==Fetish.FETISH_ANAL_RECEIVING) {
			// Anal-only pastie, dildo, puffy, dripping asshole and butt slut tattoo - crime of telling Saellatrix to kiss her ass
			// Loss is doughnut face sitting
			chokerTopText = "dumb";
			chokerBottomText = "bitch";
			crimeText = "Telling Saellatrix to kiss my ass";
			
			sister.setName("Raenix");
			sister.setSpeechColour(PresetColour.BASE_PURPLE_LIGHT);
			
			sister.setVaginaSquirter(false);
			
			sister.setAssBleached(false);
			sister.setAssSize(AssSize.SIX_MASSIVE);
			sister.setHipSize(HipSize.SIX_EXTREMELY_WIDE);
			sister.setAssCapacity(Capacity.SEVEN_GAPING, true);
			sister.setAssDepth(OrificeDepth.SEVEN_FATHOMLESS.getValue());
			sister.setAssWetness(Wetness.SEVEN_DROOLING);
			sister.setAssElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
			sister.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue());
			sister.addAssOrificeModifier(OrificeModifier.RIBBED);
			sister.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
			sister.addAssOrificeModifier(OrificeModifier.PUFFY);
			
			initSisterCoverings(sister, PresetColour.EYE_YELLOW, PresetColour.SKIN_PURPLE, PresetColour.SKIN_EBONY, PresetColour.COVERING_BLACK);
			
			initSisterClothing(sister, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, null);
			sister.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_vagina_sticker_anal_only", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PURPLE, PresetColour.CLOTHING_BLACK, false), true, sister);
//			sister.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_anus_ribbed_dildo", PresetColour.CLOTHING_BLACK, false), true, sister);
			
			sister.addTattoo(InventorySlot.ANUS,
					new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_PINK_HOT,
						false,
						new TattooWriting(
								"Butt Slut",
								PresetColour.CLOTHING_PINK_HOT,
								false),
						new TattooCounter(
								TattooCounterType.UNIQUE_SEX_PARTNERS,
								TattooCountType.TALLY,
								PresetColour.CLOTHING_PINK_HOT,
								false)));
			
			
		} else if(fetishFocus==Fetish.FETISH_PURE_VIRGIN) {
			sister.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			// Pure virgin, pierced clit, ugly hairy musky pussy and free fuck tattoo - crime of gloating about her perfect, virgin pussy
			// Loss is riding pc cock or face sitting
			chokerTopText = "broken";
			chokerBottomText = "cunt";
			crimeText = "Boasting about my pretty, virgin pussy";

			sister.setName("Jasmixia");
			sister.setSpeechColour(PresetColour.BASE_PINK_LIGHT);
			
			PerkManager.initialisePerks(sister,
					Util.newArrayListOfValues(
							Perk.BARREN,
							Perk.NYMPHOMANIAC,
							Perk.AHEGAO),
					Util.newHashMapOfValues(
							new Value<>(PerkCategory.PHYSICAL, 0),
							new Value<>(PerkCategory.LUST, 5),
							new Value<>(PerkCategory.ARCANE, 1)));
			
			sister.setAgeAppearanceAbsolute(19);
			sister.setFemininity(100);
			
			sister.setVaginaClitorisSize(ClitorisSize.TWO_LARGE);
			sister.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
			sister.setVaginaSquirter(true);
			sister.setVaginaCapacity(Capacity.SEVEN_GAPING, true);
			sister.setVaginaDepth(OrificeDepth.SEVEN_FATHOMLESS.getValue());
			sister.setVaginaWetness(Wetness.SEVEN_DROOLING);
			sister.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
			sister.setVaginaPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue());
			sister.addVaginaOrificeModifier(OrificeModifier.RIBBED);
			sister.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
			sister.addVaginaOrificeModifier(OrificeModifier.PUFFY);
			sister.clearGirlcumModifiers();
			sister.addGirlcumModifier(FluidModifier.SLIMY);
			sister.addGirlcumModifier(FluidModifier.MUSKY);
			sister.setPubicHair(BodyHair.SEVEN_WILD);

			initSisterCoverings(sister, PresetColour.EYE_BLUE_LIGHT, PresetColour.SKIN_PALE, PresetColour.SKIN_LIGHT, PresetColour.COVERING_WHITE);
			sister.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), false);

			initSisterClothing(sister, PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_STEEL, Util.newArrayListOfValues(InventorySlot.PIERCING_VAGINA));
			sister.setPiercedVagina(true);
			sister.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_vagina_sex", PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_PINK_HOT, false), true, sister);
			
			sister.addTattoo(InventorySlot.GROIN,
					new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_PINK_HOT,
						false,
						new TattooWriting(
								"&#8595; Free Fuck &#8595;",
								PresetColour.CLOTHING_PINK_HOT,
								false),
						new TattooCounter(
								TattooCounterType.UNIQUE_SEX_PARTNERS,
								TattooCountType.TALLY,
								PresetColour.CLOTHING_PINK_HOT,
								false)));
			
			
		} else if(fetishFocus==Fetish.FETISH_BREASTS_SELF) {
			sister.addFetish(Fetish.FETISH_LACTATION_SELF);
			// Breast lover, 3 pairs of N-cup tits with piercings, huge nipples and tit cow tattoo - crime of calling Saellatrix a tit cow
			// Loss is forced to fuck nipples, suckle while fingered or jerked off, or face sitting while she plays with tits (based on content settings)
			chokerTopText = "worthless";
			chokerBottomText = "slut";
			crimeText = "Called Saellatrix a tit cow";

			sister.setName("Niomia");
			sister.setSpeechColour(PresetColour.BASE_INDIGO);
			
			sister.setAgeAppearanceAbsolute(24);
			sister.setFemininity(95);
			
			sister.setBreastSize(CupSize.N.getMeasurement());
			sister.setBreastRows(3);
			sister.setBreastShape(BreastShape.POINTY);
			sister.setNippleSize(NippleSize.FOUR_MASSIVE);
			sister.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
			sister.setNippleCapacity(Capacity.SEVEN_GAPING.getMedianValue(), true);
			sister.setNippleDepth(OrificeDepth.SEVEN_FATHOMLESS.getValue());
			sister.setNippleElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
			sister.setNipplePlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue());
			sister.addNippleOrificeModifier(OrificeModifier.PUFFY);

			sister.clearMilkModifiers();
			sister.setBreastMilkStorage(4000);
			sister.fillMilkToMaxStorage();
			sister.setBreastLactationRegeneration(10_000);
			sister.setMilkFlavour(FluidFlavour.MILK);
			sister.addMilkModifier(FluidModifier.MUSKY);
			sister.addMilkModifier(FluidModifier.VISCOUS);

			initSisterCoverings(sister, PresetColour.EYE_GREEN, PresetColour.SKIN_IVORY, PresetColour.SKIN_GREY, PresetColour.COVERING_WHITE);
			sister.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, PresetColour.SKIN_EBONY, false, PresetColour.ORIFICE_INTERIOR, false), false);

			initSisterClothing(sister, PresetColour.CLOTHING_PURPLE_DARK, PresetColour.CLOTHING_PURPLE_DARK, PresetColour.CLOTHING_STEEL, Util.newArrayListOfValues(InventorySlot.PIERCING_NIPPLE));
			sister.setPiercedNipples(true);
			sister.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_nipple_chain", PresetColour.CLOTHING_STEEL, false), true, sister);

			sister.addTattoo(InventorySlot.CHEST,
					new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_PINK_HOT,
						false,
						new TattooWriting(
								"Tit Cow",
								PresetColour.CLOTHING_PINK_HOT,
								false),
						new TattooCounter(
								TattooCounterType.UNIQUE_SEX_PARTNERS,
								TattooCountType.TALLY,
								PresetColour.CLOTHING_PINK_HOT,
								false)));
			
		} else if(fetishFocus==Fetish.FETISH_PENIS_GIVING) {
			// Penis fetish with massive cock, use me tattoo - crime of insulting Saellatrix's cock
			// Loss is fucking player
			chokerTopText = "bad";
			chokerBottomText = "dog";
			crimeText = "Called Saellatrix's cock disgusting";

			sister.setName("Ellaxia");
			
			sister.setVaginaType(VaginaType.NONE);
			sister.setSpeechColour(PresetColour.BASE_RED);
			
			sister.setPenisType(PenisType.DEMON_COMMON);
			sister.setPenisGirth(PenetrationGirth.SIX_CHUBBY);
			sister.setPenisSize(PenisLength.SIX_GIGANTIC);
			sister.setTesticleSize(TesticleSize.SIX_GIGANTIC);
			sister.setInternalTesticles(false);
			sister.setPenisCumStorage(2500);
			sister.fillCumToMaxStorage();
			sister.setPenisCumProductionRegeneration(FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay());
			sister.setPenisCumExpulsion(100);
			sister.clearCumModifiers();
			sister.addCumModifier(FluidModifier.MUSKY);
			sister.addCumModifier(FluidModifier.SLIMY);
			sister.addCumModifier(FluidModifier.STICKY);
			sister.clearPenisModifiers();
			sister.addPenisModifier(PenetrationModifier.KNOTTED);
			sister.addPenisModifier(PenetrationModifier.TAPERED);
			sister.addPenisModifier(PenetrationModifier.SHEATHED);
			sister.addPenisModifier(PenetrationModifier.TENTACLED);
			sister.addPenisModifier(PenetrationModifier.RIBBED);

			initSisterCoverings(sister, PresetColour.EYE_AMBER, PresetColour.SKIN_BROWN, PresetColour.SKIN_EBONY, PresetColour.COVERING_BLACK);
			sister.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);

			initSisterClothing(sister, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, null);
			sister.setPiercedPenis(true);
			sister.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_penis_ring", PresetColour.CLOTHING_GOLD, false), true, sister);
			
			sister.addItem(Main.game.getItemGen().generateItem(ItemType.REJUVENATION_POTION), 5, false, false);
			
			sister.addTattoo(InventorySlot.GROIN,
					new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_RED_BRIGHT,
						false,
						new TattooWriting(
								"Get knotted, bitch!",
								PresetColour.CLOTHING_PINK_HOT,
								false),
						new TattooCounter(
								TattooCounterType.UNIQUE_SEX_PARTNERS,
								TattooCountType.TALLY,
								PresetColour.CLOTHING_PINK_HOT,
								false)));
			
		} else if(fetishFocus==Fetish.FETISH_ORAL_GIVING) {
			// Oral fetish with huge lips and massive tongue, feed me tattoo - crime of complaining about the food at Saellatrix's dinner party
			// Loss is receive blowjob/cunnilingus
			chokerTopText = "broken";
			chokerBottomText = "slut";
			crimeText = "Refused to suck Saellatrix's cock";

			sister.setName("Ionixis");
			sister.setSpeechColour(PresetColour.BASE_LILAC);
			
			PerkManager.initialisePerks(sister,
					Util.newArrayListOfValues(
							Perk.BARREN,
							Perk.NYMPHOMANIAC,
							Perk.HYPERMOBILITY),
					Util.newHashMapOfValues(
							new Value<>(PerkCategory.PHYSICAL, 0),
							new Value<>(PerkCategory.LUST, 5),
							new Value<>(PerkCategory.ARCANE, 1)));
			
			sister.setVaginaSquirter(true);
			
			sister.setLipSize(LipSize.SEVEN_ABSURD);
			sister.setFaceCapacity(Capacity.SEVEN_GAPING, true);
			sister.setFaceDepth(OrificeDepth.SEVEN_FATHOMLESS.getValue());
			sister.addFaceOrificeModifier(OrificeModifier.PUFFY);
			sister.addFaceOrificeModifier(OrificeModifier.TENTACLED);
			sister.addFaceOrificeModifier(OrificeModifier.RIBBED);
			sister.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
			// Throat settings and modifiers
			sister.setTongueLength(TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue());
			sister.addTongueModifier(TongueModifier.BIFURCATED);
			sister.addTongueModifier(TongueModifier.STRONG);
			sister.addTongueModifier(TongueModifier.TENTACLED);

			initSisterCoverings(sister, PresetColour.EYE_PINK_SALMON, PresetColour.SKIN_BLUE_LIGHT, PresetColour.SKIN_BLUE, PresetColour.COVERING_BLACK);

			initSisterClothing(sister, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_BLACK_STEEL, null);
			
			sister.addTattoo(InventorySlot.NECK,
					new Tattoo(
						"innoxia_misc_none",
						PresetColour.CLOTHING_PINK_HOT,
						false,
						new TattooWriting(
								"&#8593; Fuck Hole &#8593;",
								PresetColour.CLOTHING_PINK_HOT,
								false),
						new TattooCounter(
								TattooCounterType.UNIQUE_SEX_PARTNERS,
								TattooCountType.TALLY,
								PresetColour.CLOTHING_PINK_HOT,
								false)));
		}
		
		// Add standard choker:
		AbstractClothing choker = Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_SILVER, null, false);
		choker.setSticker("top_txt", chokerTopText);
		choker.setSticker("btm_txt", chokerBottomText);
		
		choker.clearEffects();
		
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SERVITUDE, TFPotency.MINOR_BOOST, 0));
		
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_NON_CON_SUB, TFPotency.MAJOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SADIST, TFPotency.MAJOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_MASOCHIST, TFPotency.MAJOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_EXHIBITIONIST, TFPotency.MAJOR_BOOST, 0));
		
		choker.setName("Saellatrix's Gift");
		
		sister.equipClothingFromNowhere(choker, true, sister);
		
		
		// Add standard crimes tattoo:
		sister.addTattoo(InventorySlot.TORSO_UNDER,
				new Tattoo(
					"innoxia_misc_none",
					PresetColour.CLOTHING_GOLD,
					false,
					new TattooWriting(
							"<b>Crimes:</b>"
							+ "<br/>Scheming against Saellatrix"
							+ "<br/>Attempting to gain Lovienne's recognition"
							+ "<br/>"
							+ crimeText,
							PresetColour.CLOTHING_GOLD,
							false),
					null));
		
		// Add slave after all clothing equipped so Saellatrix gets the keys
		this.addSlave(sister);
		sister.setObedience(100);
	}
	
	private void initSisterCoverings(DollFactorySuccubus sister, Colour eye, Colour skin, Colour genitals, Colour horns) {
		sister.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, eye));
		sister.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, skin), true);
		
		sister.setSkinCovering(new Covering(BodyCoveringType.HORN, CoveringPattern.NONE, horns), false);
		
		sister.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, genitals, false, PresetColour.ORIFICE_INTERIOR, false), false);
		sister.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, genitals, false, PresetColour.ORIFICE_INTERIOR, false), false);
		sister.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, genitals, false, PresetColour.ORIFICE_INTERIOR, false), false);
		sister.setSkinCovering(new Covering(BodyCoveringType.PENIS, genitals), false);
	}
	
	private void initSisterClothing(DollFactorySuccubus sister, Colour fishnetColour, Colour restraintsAndSkirtColour, Colour piercingsColour, List<InventorySlot> slotsToExclude) {
		if(slotsToExclude==null) {
			slotsToExclude = new ArrayList<>();
		}
		
		// Basic equipment:
		sister.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bdsm_riding_crop", DamageType.PHYSICAL));
		
		Map<InventorySlot, AbstractClothing> clothingToEquip = new HashMap<>();
		//TODO change
		clothingToEquip.put(InventorySlot.TORSO_UNDER, Main.game.getItemGen().generateClothing(ClothingType.TORSO_FISHNET_TOP, fishnetColour, false));
		clothingToEquip.put(InventorySlot.SOCK, Main.game.getItemGen().generateClothing("innoxia_sock_fishnets", fishnetColour, false));
		clothingToEquip.put(InventorySlot.HAND, Main.game.getItemGen().generateClothing("innoxia_hand_fishnet_gloves", fishnetColour, false));
		
		clothingToEquip.put(InventorySlot.WRIST, Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", restraintsAndSkirtColour, false));
		clothingToEquip.put(InventorySlot.ANKLE, Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_bracelets", restraintsAndSkirtColour, false));
		
		if(Util.random.nextFloat()<0.5f) {
			clothingToEquip.put(InventorySlot.LEG, Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_pleated", restraintsAndSkirtColour, false));
		} else {
			clothingToEquip.put(InventorySlot.LEG, Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_belted", restraintsAndSkirtColour, false));
		}

		// Piercings:
		sister.setPiercedEar(true);
		sister.setPiercedNose(true);
		sister.setPiercedTongue(true);
		sister.setPiercedLip(true);
		sister.setPiercedNavel(true);
		sister.setPiercedNipples(true);
		
		if(Util.random.nextFloat()<0.5f) {
			clothingToEquip.put(InventorySlot.PIERCING_EAR, Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", piercingsColour, false));
		} else {
			clothingToEquip.put(InventorySlot.PIERCING_EAR, Main.game.getItemGen().generateClothing("innoxia_piercing_ear_cuff_chains", piercingsColour, false));
		}
		if(Util.random.nextFloat()<0.5f) {
			clothingToEquip.put(InventorySlot.PIERCING_NOSE, Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ball_stud", piercingsColour, false));
		} else {
			clothingToEquip.put(InventorySlot.PIERCING_NOSE, Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", piercingsColour, false));
		}
		clothingToEquip.put(InventorySlot.PIERCING_TONGUE, Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", piercingsColour, false));
		clothingToEquip.put(InventorySlot.PIERCING_LIP, Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", piercingsColour, false));
		clothingToEquip.put(InventorySlot.PIERCING_STOMACH, Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", piercingsColour, false));
		if(Util.random.nextFloat()<0.5f) {
			clothingToEquip.put(InventorySlot.PIERCING_NIPPLE, Main.game.getItemGen().generateClothing("norin_piercings_piercing_nipple_rings", piercingsColour, false));
		} else {
			clothingToEquip.put(InventorySlot.PIERCING_NIPPLE, Main.game.getItemGen().generateClothing("norin_piercings_heart_barbells", piercingsColour, piercingsColour, piercingsColour, false));
		}
		
		for(Entry<InventorySlot, AbstractClothing> entry : clothingToEquip.entrySet()) {
			if(!slotsToExclude.contains(entry.getKey())) {
				sister.equipClothingOverride(entry.getValue(), entry.getKey(), true, false);
			}
		}
	}
	
	// Methods for spawned sister detection:
	
	public boolean isAnySisterPresent() {
		return getSisterPresent()!=null;
	}
	
	public DollFactorySuccubus getSisterPresent() {
		for(GameCharacter c : Main.game.getCharactersPresent()) {
			if(c instanceof DollFactorySuccubus) {
				return (DollFactorySuccubus) c;
			}
		}
		return null;
	}
	
	public void removeSister(DollFactorySuccubus sister) {
		for(Entry<AbstractFetish, String> entry : sisterFetishesToFlagIds.entrySet()) {
			if(sister.hasFetish(entry.getKey())) {
				Main.game.getDialogueFlags().setFlag(entry.getValue(), false);
			}
		}
		sister.returnToHome();
	}
	
//	/**
//	 * For use when factory quest is completely finished.
//	 */
//	public void deleteAllSisters() {
//		for(NPC npc : new ArrayList<>(Main.game.getAllNPCs())) {
//			if(npc instanceof DollFactorySuccubus) {
//				Main.game.banishNPC(npc);
//			}
//		}
//	}
	
	private static String[] toyNames = new String[]{"dildos", "anal beads", "cock rings", "strap-ons", "onaholes"};
	private static Colour[] toyColours = new Colour[]{
			PresetColour.CLOTHING_PURPLE,
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BLACK,
			PresetColour.CLOTHING_PINK,
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_YELLOW,
			PresetColour.CLOTHING_BLUE_LIGHT,
			PresetColour.CLOTHING_BROWN_DARK,
			PresetColour.CLOTHING_BLACK};
	
	public void initFactoryMachineParsing() {
		int toyIndex = Main.game.getPlayer().getLocation().getX()-3;
		String toyNamePlural = "dildos";
		try {
			toyNamePlural = toyNames[toyIndex];
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		int toyColourIndex = Main.game.getPlayer().getLocation().getX()-3 + Main.game.getPlayer().getLocation().getY();
		String toyColourName = "black";
		try {
			toyColourName = toyColours[toyColourIndex].getName();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		Main.game.addSpecialParsingString(toyNamePlural, true);
		Main.game.addSpecialParsingString(toyColourName, false);
	}
	
	
	public void generateSlimeMilker(String parserTag) {
		GenericSexualPartner slime = new GenericSexualPartner();
		try {
			Main.game.addNPC(slime, false);
			Main.game.setParserTarget(parserTag, slime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Perks:
		slime.resetSpecialPerksMap();
		slime.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(slime,
				Util.newArrayListOfValues(
						Perk.BARREN,
						Perk.NYMPHOMANIAC),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
		
		// Fetishes:
		slime.clearFetishDesires();
		slime.clearFetishes();
		slime.addFetish(Fetish.FETISH_LACTATION_SELF);
		slime.addFetish(Fetish.FETISH_BIMBO);
		
		slime.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.SLIME, RaceStage.GREATER, true);
		
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.MAIN_SKIN), PresetColour.COVERING_CLEAR), true);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.EYE_IRIS), PresetColour.COVERING_CLEAR), false);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.EYE_SCLERA), PresetColour.COVERING_CLEAR), false);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.EYE_PUPIL), PresetColour.COVERING_CLEAR), false);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.ANUS), CoveringPattern.ORIFICE_ANUS, PresetColour.COVERING_CLEAR, false, PresetColour.COVERING_CLEAR, true), false);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.MOUTH), CoveringPattern.ORIFICE_MOUTH, PresetColour.COVERING_RED, false, PresetColour.COVERING_CLEAR, true), false);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.TONGUE), CoveringPattern.NONE, PresetColour.COVERING_CLEAR, true, PresetColour.COVERING_CLEAR, true), false);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.NIPPLE), PresetColour.COVERING_CLEAR), false);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.VAGINA), CoveringPattern.ORIFICE_VAGINA, PresetColour.COVERING_CLEAR, false, PresetColour.COVERING_CLEAR, true), false);
//		slime.setSkinCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.PENIS), CoveringPattern.NONE, PresetColour.COVERING_CLEAR, false, PresetColour.COVERING_CLEAR, true), false);
//		slime.setHairCovering(new Covering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.HAIR), PresetColour.COVERING_CLEAR), false);

		slime.setSkinCovering(new Covering(BodyCoveringType.GIRL_CUM, CoveringPattern.FLUID, PresetColour.COVERING_CLEAR, false, PresetColour.COVERING_CLEAR, false), false);
		slime.setSkinCovering(new Covering(BodyCoveringType.MILK, CoveringPattern.FLUID, PresetColour.COVERING_CLEAR, false, PresetColour.COVERING_CLEAR, false), false);
		slime.setSkinCovering(new Covering(BodyCoveringType.CUM, CoveringPattern.FLUID, PresetColour.COVERING_CLEAR, false, PresetColour.COVERING_CLEAR, false), false);
		
		slime.setHeight(250);
		
		slime.setMuscle(0);
		slime.setBodySize(100);

		slime.setPenisSize(PenisLength.SEVEN_STALLION.getMedianValue());
		slime.setPenisCapacity(Capacity.FOUR_LOOSE.getMedianValue(), true);
		slime.setPenisCumStorage(CumProduction.SEVEN_MONSTROUS.getMaximumValue());
		slime.setPenisCumExpulsion(100);
		slime.setPenisCumProductionRegeneration(FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay());
		
		slime.setBreastSize(CupSize.XX_N);
		slime.setNippleSize(NippleSize.FOUR_MASSIVE);
		slime.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		slime.setNippleCapacity(Capacity.FIVE_ROOMY.getMedianValue(), true);
		slime.setBreastMilkStorage(Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue());
		slime.setBreastLactationRegeneration(FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay());
		
	}
}
