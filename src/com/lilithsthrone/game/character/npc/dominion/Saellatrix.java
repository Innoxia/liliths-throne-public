package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
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
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
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

			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_MASTURBATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_TRANSFORMATION_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SIZE_QUEEN, FetishDesire.THREE_LIKE);
		}
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(38);
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
	public void dailyUpdate() {
		clearNonEquippedInventory(false);
		
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
	}
	
	@Override
	public void turnUpdate() {
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(this)) {
			if(Main.sex.getInitialSexManager() == SexManagerLoader.getSexManagerFromId("innoxia_dominion_sex_shop_saellatrix_blowjob") && this.getClothingInSlot(InventorySlot.NECK)==null) {
				Main.game.getDialogueFlags().setFlag("innoxia_sex_shop_choker_snapped", true);
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
		if(!isPlayerVisited()) {
			NPC doll = new GenericSexualPartner(false);
			doll.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, true);
			doll.setBodyMaterial(BodyMaterial.SILICONE);
			doll.setPlayerKnowsName(false);
			doll.setName("doll");
			doll.setSurname("#01734");
			doll.setGenericName("demo doll");
			doll.setDescription("The demo doll works in Lovienne's Luxuries, providing oral sex to potential customers.");
			
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
}
