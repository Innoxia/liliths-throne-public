package com.lilithsthrone.game.character.npc.fields;

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
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
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
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
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
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Innoxia
 */
public class Evelyx extends NPC {

	public Evelyx() {
		this(false);
	}
	
	public Evelyx(boolean isImported) {
		super(isImported,
				new NameTriplet("Evelyx"), "Lisophiamartu",
				"The owner and namesake of the farm 'Evelyne's Dairy', this succubus is extremely arrogant and rude, and will do whatever it takes to make the most amount of money possible.",
				76, Month.JULY, 2,
				25, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_manager"),
				true);
		
		if(!isImported) {
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.3")) {
			this.setStartingBody(true);
			this.equipClothing();
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
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
					PersonalityTrait.LEWD,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.CYNICAL);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_BUSINESS_OWNER);

			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_INCEST, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ONE_DISLIKE);
			
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
		
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(18);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_HOOFED);
		this.setHornType(HornType.SWEPT_BACK);
		this.setHornRows(2);
		
		// Core:
		this.setHeight(172);
		this.setFemininity(95);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_ORANGE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.COVERING_BLUE_LIGHT), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, CoveringPattern.OMBRE, CoveringModifier.SMOOTH, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_ORANGE, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.COVERING_ORANGE, false, PresetColour.COVERING_ORANGE, false), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, PresetColour.COVERING_ORANGE, false, PresetColour.COVERING_ORANGE, false), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.PONYTAIL);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_ORANGE));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_ORANGE));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_PINK_LIGHT));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_ORANGE));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLUE_DARK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.DD.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		// n/a
		this.setTesticleCount(2); // For if she grows one
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.resetPussy();
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_ORANGE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_ORANGE, false), true, this);
		
		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_ORANGE, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_feminine_short_sleeve_shirt", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_feminine_blazer", PresetColour.CLOTHING_BLACK, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_pencil_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_ORANGE, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_gemstone_ring", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, null, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_pearl_studs", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GOLD, null, false), true, this);
		this.setPiercedNavel(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, null, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return true;
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
		this.equipClothing();
		this.resetPussy();
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	public void loosenPussy() {
		this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
	}
	
	public void gapingMessPussy() {
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.COVERING_ORANGE_DARK, false, PresetColour.COVERING_ORANGE_DARK, false), false);
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
		this.setVaginaCapacity(Capacity.SEVEN_GAPING, true);
		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
		this.addGirlcumModifier(FluidModifier.MUSKY);
	}
	
	public void resetPussy() {
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.COVERING_ORANGE, false, PresetColour.COVERING_ORANGE, false), false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.removeGirlcumModifier(FluidModifier.MUSKY);
	}
	
	// Public methods for use within Evelyx's Dairy:

	public void transformCock(boolean addCock) {
		if(addCock) {
			this.setVaginaType(VaginaType.NONE);
			this.setPenisType(PenisType.DEMON_COMMON);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, PresetColour.COVERING_ORANGE, false, PresetColour.COVERING_ORANGE, false), false);
			this.setTesticleCount(4);
			this.setInternalTesticles(false);
			this.setPenisVirgin(false);
			this.setPenisGirth(PenetrationGirth.SIX_CHUBBY);
			this.setPenisSize(50);
			this.setTesticleSize(TesticleSize.FIVE_MASSIVE);
			this.setPenisCumStorage(5000);
			this.fillCumToMaxStorage();
			this.clearPenisModifiers();
			this.addPenisModifier(PenetrationModifier.FLARED);
			this.addPenisModifier(PenetrationModifier.KNOTTED);
			this.addPenisModifier(PenetrationModifier.VEINY);
			
		} else {
			this.setPenisType(PenisType.NONE);
			this.setVaginaType(VaginaType.DEMON_COMMON);
		}
	}
	
	// Transformatives for when player is working:
	
	private class TransformationData {
		String transformationAvailabilityText;
		String transformationAppliedText;
		boolean anyTransformationAvailable;
	}
	
	public boolean isAnyCowTransformationAvailable(GameCharacter worker) {
		return generateCowTransformationData(worker, false).anyTransformationAvailable;
	}
	
	public String getCowTransformationText(GameCharacter worker) {
		return generateCowTransformationData(worker, false).transformationAvailabilityText;
	}
	
	public String applyCowTransformation(GameCharacter worker) {
		return generateCowTransformationData(worker, true).transformationAppliedText;
	}
	
	private String getTransformationFormat(boolean available, Colour titleColour, String title, String text, StringBuilder transformationStringBuilder) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
			if(available) {
				sb.append("[style.boldGood(Apply:)] <span style='color:"+titleColour.toWebHexString()+";'>"+title+"</span>");
				sb.append("</br>");
				sb.append(text);
			} else {
				sb.append("[style.boldDisabled(Not Required:)] [style.colourDisabled("+title);
				sb.append("</br>");
				sb.append(text);
				sb.append(")]");
			}
		sb.append("</p>");
		
		// Appending to transformation:
		transformationStringBuilder.append("<p style='text-align:center; margin-bottom:0; padding-bottom:0;'>");
			if(available) {
				transformationStringBuilder.append("<i><b style='color:"+titleColour.toWebHexString()+";'>"+title+":</b> "+text+"</i>");
			} else {
				transformationStringBuilder.append("<i>[style.boldDisabled(Not Required:)] [style.colourDisabled("+title+": "+text+")]</i>");
			}
		transformationStringBuilder.append("</p>");
		
		return sb.toString();
	}
	
	private TransformationData generateCowTransformationData(GameCharacter worker, boolean applyTransformation) {
		StringBuilder sbTransformationText = new StringBuilder();
		StringBuilder sbAppliedTransformation = new StringBuilder();
		boolean anyTransformationAvailable = false;

		// Trainee values:
		CupSize cupSize = CupSize.DD;
		int milkStorage = 1000;
		int milkRegeneration = 10_000;
		Wetness pussyWetness = Wetness.TWO_MOIST;
		boolean makeSquirter = false;
		boolean crotchBoobs = false;
		
		// Heifer:
		if(worker.getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_barn2")) {
			cupSize = CupSize.GG;
			milkStorage = 2500;
			milkRegeneration = 25_000;
			pussyWetness = Wetness.FOUR_SLIMY;
//			makeSquirter = true;
			crotchBoobs = true;
		}
		// Cow:
		if(worker.getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_barn3")) {
			cupSize = CupSize.KK;
			milkStorage = 5000;
			milkRegeneration = 50_000;
			pussyWetness = Wetness.SIX_SOPPING_WET;
//			makeSquirter = true;
			crotchBoobs = true;
		}

		// Femininity:
		boolean applyFemininity = !worker.isFeminine();
		if(!anyTransformationAvailable) {
			anyTransformationAvailable = applyFemininity;
		}
		sbTransformationText.append(getTransformationFormat(applyFemininity, PresetColour.BASE_PINK_LIGHT, "Femininity Increase", "Cows are required to be feminine in appearance.", sbAppliedTransformation));
		if(applyTransformation && applyFemininity) {
			sbAppliedTransformation.append(worker.setFemininity(80));
		}
		
		// Breasts:
		boolean applyBreastSize = worker.getBreastRawSizeValue()<cupSize.getMeasurement();
		if(!anyTransformationAvailable) {
			anyTransformationAvailable = applyBreastSize;
		}
		sbTransformationText.append(getTransformationFormat(applyBreastSize, PresetColour.BASE_PINK, "Breast Enlargement", "Cows should have large breasts.", sbAppliedTransformation));
		if(applyTransformation && applyBreastSize) {
			sbAppliedTransformation.append(worker.setBreastSize(cupSize));
		}
		
		boolean applyMilkStorage = worker.getBreastRawMilkStorageValue()<milkStorage;
		if(!anyTransformationAvailable) {
			anyTransformationAvailable = applyMilkStorage;
		}
		sbTransformationText.append(getTransformationFormat(applyMilkStorage, PresetColour.MILK, "Milk Storage", "A cow's breasts should be full of milk.", sbAppliedTransformation));
		if(applyTransformation && applyMilkStorage) {
			sbAppliedTransformation.append(worker.setBreastMilkStorage(milkStorage));
		}
		
		boolean applyMilkRegeneration = worker.getBreastRawLactationRegenerationValue()<milkRegeneration;
		if(!anyTransformationAvailable) {
			anyTransformationAvailable = applyMilkRegeneration;
		}
		sbTransformationText.append(getTransformationFormat(applyMilkRegeneration, PresetColour.MILK, "Milk Regeneration", "Cows should never stop producing milk.", sbAppliedTransformation));
		if(applyTransformation && applyMilkRegeneration) {
			sbAppliedTransformation.append(worker.setBreastLactationRegeneration(milkRegeneration));
		}
		
		boolean applyPuffy = !worker.getNippleOrificeModifiers().contains(OrificeModifier.PUFFY);
		if(!anyTransformationAvailable) {
			anyTransformationAvailable = applyPuffy;
		}
		sbTransformationText.append(getTransformationFormat(applyPuffy, PresetColour.BASE_PINK_LIGHT, "Puffy Nipples", "Large nipples improve milking machine compatibility.", sbAppliedTransformation));
		if(applyTransformation && applyPuffy) {
			sbAppliedTransformation.append(worker.addNippleOrificeModifier(OrificeModifier.PUFFY));
		}
		
		// Crotch boobs:
		if(crotchBoobs && Main.game.isUdderContentEnabled() && (worker.isTaur() || worker.getRace().getRacialBody().getBreastCrotchType()!=BreastType.NONE)) {
			boolean applyCrotchBoobs = !worker.hasBreastsCrotch();
			if(!anyTransformationAvailable) {
				anyTransformationAvailable = applyCrotchBoobs;
			}
			sbTransformationText.append(getTransformationFormat(applyCrotchBoobs, PresetColour.BASE_PURPLE, "Udders", "Cows are required to have udders.", sbAppliedTransformation));
			if(applyTransformation && applyCrotchBoobs) {
				sbAppliedTransformation.append(worker.setBreastCrotchType(worker.getRace().getRacialBody().getBreastCrotchType()));
			}
			
			boolean applyCrotchBoobSize = worker.getBreastCrotchRawSizeValue()<cupSize.getMeasurement()/2;
			if(!anyTransformationAvailable) {
				anyTransformationAvailable = applyCrotchBoobSize;
			}
			sbTransformationText.append(getTransformationFormat(applyCrotchBoobSize, PresetColour.BASE_PURPLE_LIGHT, "Udder Enlargement", "Cows should have large udders.", sbAppliedTransformation));
			if(applyTransformation && applyCrotchBoobSize) {
				sbAppliedTransformation.append(worker.setBreastCrotchSize(cupSize.getMeasurement()/2));
			}
			
			boolean applyCrotchBoobMilkStorage = worker.getBreastCrotchRawMilkStorageValue()<milkStorage*2;
			if(!anyTransformationAvailable) {
				anyTransformationAvailable = applyCrotchBoobMilkStorage;
			}
			sbTransformationText.append(getTransformationFormat(applyCrotchBoobMilkStorage, PresetColour.MILK, "Udder Milk Storage", "A cow's udders should be full of milk.", sbAppliedTransformation));
			if(applyTransformation && applyCrotchBoobMilkStorage) {
				sbAppliedTransformation.append(worker.setBreastCrotchMilkStorage(milkStorage*2));
			}
			
			boolean applyCrotchBoobMilkRegeneration = worker.getBreastCrotchRawLactationRegenerationValue()<milkRegeneration*2;
			if(!anyTransformationAvailable) {
				anyTransformationAvailable = applyCrotchBoobMilkRegeneration;
			}
			sbTransformationText.append(getTransformationFormat(applyCrotchBoobMilkRegeneration, PresetColour.MILK, "Udder Milk Regeneration", "Cows should never stop producing milk.", sbAppliedTransformation));
			if(applyTransformation && applyCrotchBoobMilkRegeneration) {
				sbAppliedTransformation.append(worker.setBreastCrotchLactationRegeneration(milkRegeneration*2));
			}
			
			boolean applyCrotchBoobPuffy = !worker.getNippleCrotchOrificeModifiers().contains(OrificeModifier.PUFFY);
			if(!anyTransformationAvailable) {
				anyTransformationAvailable = applyCrotchBoobPuffy;
			}
			sbTransformationText.append(getTransformationFormat(applyCrotchBoobPuffy, PresetColour.BASE_PURPLE_LIGHT, "Puffy Teats", "Large teats improve milking machine compatibility.", sbAppliedTransformation));
			if(applyTransformation && applyCrotchBoobPuffy) {
				sbAppliedTransformation.append(worker.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
			}
		}
		
		// Vagina:
		boolean applyVagina = !worker.hasVagina();
		if(!anyTransformationAvailable) {
			anyTransformationAvailable = applyVagina;
		}
		sbTransformationText.append(getTransformationFormat(applyVagina, PresetColour.BASE_PINK_LIGHT, "Grow Vagina", "A cow needs to be able to be impregnated.", sbAppliedTransformation));
		if(applyTransformation && applyVagina) {
			sbAppliedTransformation.append(worker.setVaginaType(worker.getAssRace().getRacialBody().getVaginaType()));
		}
		
		boolean applyVaginaWetness = worker.getVaginaWetness().getValue()<pussyWetness.getValue();
		if(!anyTransformationAvailable) {
			anyTransformationAvailable = applyVaginaWetness;
		}
		sbTransformationText.append(getTransformationFormat(applyVaginaWetness, PresetColour.GIRLCUM, "Wet Vagina", "A cow's pussy needs to be wet and ready to be fucked at any moment.", sbAppliedTransformation));
		if(applyTransformation && applyVaginaWetness) {
			sbAppliedTransformation.append(worker.setVaginaWetness(pussyWetness));
		}
		
		if(makeSquirter) {
			boolean applyVaginaSquirter = !worker.isVaginaSquirter();
			if(!anyTransformationAvailable) {
				anyTransformationAvailable = applyVaginaSquirter;
			}
			sbTransformationText.append(getTransformationFormat(applyVaginaSquirter, PresetColour.GIRLCUM, "Squirter", "A cow's orgasm should be profitable.", sbAppliedTransformation));
			if(applyTransformation && applyVaginaSquirter) {
				sbAppliedTransformation.append(worker.setVaginaSquirter(true));
			}
		}
		
		boolean applyPenisRemoval = worker.hasPenisIgnoreDildo();
		if(!anyTransformationAvailable) {
			anyTransformationAvailable = applyPenisRemoval;
		}
		sbTransformationText.append(getTransformationFormat(applyPenisRemoval, PresetColour.GENERIC_MINOR_BAD, "Remove Penis", "Cows do not require a penis.", sbAppliedTransformation));
		if(applyTransformation && applyPenisRemoval) {
			sbAppliedTransformation.append(worker.setPenisType(PenisType.NONE));
		}
		
		TransformationData tfData = new TransformationData();
		
		tfData.transformationAvailabilityText = sbTransformationText.toString();
		tfData.transformationAppliedText = sbAppliedTransformation.toString();
		tfData.anyTransformationAvailable = anyTransformationAvailable;
		
		return tfData;
	}
	

	public boolean isAnyCowFetishAvailable(GameCharacter worker) {
		return generateCowFetishData(worker, false).anyTransformationAvailable;
	}
	
	public String getCowFetishText(GameCharacter worker) {
		return generateCowFetishData(worker, false).transformationAvailabilityText;
	}
	
	public String applyCowFetish(GameCharacter worker) {
		return generateCowFetishData(worker, true).transformationAppliedText;
	}
	
	private TransformationData generateCowFetishData(GameCharacter worker, boolean applyTransformation) {
		StringBuilder sbTransformationText = new StringBuilder();
		StringBuilder sbAppliedTransformation = new StringBuilder();
		boolean anyFetishChangesAvailable = false;
		
		Map<AbstractFetish, String> fetishesToBeAdded = new HashMap<>();
		
		// Trainee values:
		fetishesToBeAdded.put(Fetish.FETISH_LACTATION_SELF, "Cows should love to be milked.");
		fetishesToBeAdded.put(Fetish.FETISH_BREASTS_SELF, "A good cow likes having her breasts used.");
		fetishesToBeAdded.put(Fetish.FETISH_SUBMISSIVE, "Cows must at all times act in a submissive manner.");
		fetishesToBeAdded.put(Fetish.FETISH_EXHIBITIONIST, "Cows should enjoy being exposed to members of the public.");

		// Heifer:
		if(worker.getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_barn2") || worker.getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_barn3")) {
			fetishesToBeAdded.put(Fetish.FETISH_VAGINAL_RECEIVING, "A good cow always wants her pussy to be used.");
			fetishesToBeAdded.put(Fetish.FETISH_PENIS_RECEIVING, "Cows should love being rutted by their mates.");
			fetishesToBeAdded.put(Fetish.FETISH_CUM_ADDICT, "A good cow wants to be filled with her mate's cum.");
			fetishesToBeAdded.put(Fetish.FETISH_PREGNANCY, "Cows should want to be pregnant at all times.");
		}
		
		// Cow:
		if(worker.getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_barn3")) {
			fetishesToBeAdded.put(Fetish.FETISH_MASOCHIST, "Cows should be prepared for their mates to sometimes be rough.");
			fetishesToBeAdded.put(Fetish.FETISH_BONDAGE_VICTIM, "Cows should enjoy being locked into their stalls.");
			fetishesToBeAdded.put(Fetish.FETISH_SIZE_QUEEN, "No insertion is too big for a good cow.");
		}
		
		for(Entry<AbstractFetish, String> fetishToAdd : fetishesToBeAdded.entrySet()) {
			boolean applyFetish = !worker.hasFetish(fetishToAdd.getKey());
			if(!anyFetishChangesAvailable) {
				anyFetishChangesAvailable = applyFetish;
			}
			sbTransformationText.append(getTransformationFormat(applyFetish, PresetColour.FETISH, Util.capitaliseSentence(fetishToAdd.getKey().getName(worker)), fetishToAdd.getValue(), sbAppliedTransformation));
			if(applyTransformation && applyFetish) {
				sbAppliedTransformation.append(worker.addFetish(fetishToAdd.getKey()));
			}
		}
		
		TransformationData tfData = new TransformationData();
		
		tfData.transformationAvailabilityText = sbTransformationText.toString();
		tfData.transformationAppliedText = sbAppliedTransformation.toString();
		tfData.anyTransformationAvailable = anyFetishChangesAvailable;
		
		return tfData;
	}
	
	public String applyCowRaceTransformationLesser(GameCharacter cow) {
		StringBuilder sb = new StringBuilder();
		
		// Human:
		if(cow.getFaceType().getRace()!=Race.HUMAN) {
			sb.append(cow.setFaceType(FaceType.HUMAN));
		}
		if(cow.getTorsoType().getRace()!=Race.HUMAN) {
			sb.append(cow.setTorsoType(TorsoType.HUMAN));
		}
		if(cow.getBreastType().getRace()!=Race.HUMAN) {
			sb.append(cow.setBreastType(BreastType.HUMAN));
		}
		if(cow.getArmType().getRace()!=Race.HUMAN) {
			sb.append(cow.setArmType(ArmType.HUMAN));
		}
		if(cow.getLegType().getRace()!=Race.HUMAN) {
			sb.append(cow.setLegType(LegType.HUMAN));
		}
		if(cow.getAssType().getRace()!=Race.HUMAN) {
			sb.append(cow.setAssType(AssType.HUMAN));
		}
		if(cow.getVaginaType().getRace()!=Race.HUMAN) {
			sb.append(cow.setVaginaType(VaginaType.HUMAN));
		}
		if(cow.hasPenis()) {
			sb.append(cow.setPenisType(PenisType.NONE));
		}
		if(cow.hasWings()) {
			sb.append(cow.setWingType(WingType.NONE));
		}
		if(cow.hasAntennae()) {
			sb.append(cow.setAntennaType(AntennaType.NONE));
		}
		
		// Cow:
		if(cow.getEarType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setEarType(EarType.COW_MORPH));
		}
		if(cow.getEyeType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setEyeType(EyeType.COW_MORPH));
		}
		if(cow.getHairType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setHairType(HairType.COW_MORPH));
		}
		if(cow.getTailType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setTailType(TailType.COW_MORPH));
		}
		if(!cow.hasHorns() || cow.getHornType().getRace()!=HornType.STRAIGHT.getRace()) {
			sb.append(cow.setHornType(HornType.STRAIGHT));
			cow.setHornLength(HornLength.ONE_SMALL.getMedianValue());
		}
		
		return sb.toString();
	}

	public String applyCowRaceTransformationGreater(GameCharacter cow) {
		StringBuilder sb = new StringBuilder();
		
		if(cow.getEarType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setEarType(EarType.COW_MORPH));
		}
		if(cow.getEyeType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setEyeType(EyeType.COW_MORPH));
		}
		if(cow.getHairType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setHairType(HairType.COW_MORPH));
		}
		if(cow.getTailType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setTailType(TailType.COW_MORPH));
		}
		if(!cow.hasHorns() || cow.getHornType().getRace()!=HornType.STRAIGHT.getRace()) {
			sb.append(cow.setHornType(HornType.STRAIGHT));
			cow.setHornLength(HornLength.ONE_SMALL.getMedianValue());
		}
		if(cow.getAssType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setAssType(AssType.COW_MORPH));
		}
		if(cow.getVaginaType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setVaginaType(VaginaType.COW_MORPH));
		}
		if(cow.hasPenis()) {
			sb.append(cow.setPenisType(PenisType.NONE));
		}
		if(cow.hasWings()) {
			sb.append(cow.setWingType(WingType.NONE));
		}
		if(cow.hasAntennae()) {
			sb.append(cow.setAntennaType(AntennaType.NONE));
		}
		if(cow.getArmType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setArmType(ArmType.COW_MORPH));
		}
		if(cow.getLegType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setLegType(LegType.COW_MORPH));
		}
		if(cow.getBreastType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setBreastType(BreastType.COW_MORPH));
		}
		if(cow.getTorsoType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setTorsoType(TorsoType.COW_MORPH));
		}
		if(cow.getFaceType().getRace()!=Race.COW_MORPH) {
			sb.append(cow.setFaceType(FaceType.COW_MORPH));
		}
		
		return sb.toString();
	}
	
	// Clothing:
	
	public AbstractClothing getOralRing() {
		AbstractClothing oralRing = Main.game.getItemGen().generateClothing("innoxia_finger_lips_ring", PresetColour.CLOTHING_ORANGE_DARK, PresetColour.CLOTHING_BLACK_STEEL, null, false);
		
		oralRing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		oralRing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SERVITUDE, TFPotency.MINOR_BOOST, 0));
		
		for(int i=0;i<16;i++) { //+50 corruption
			oralRing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0));
		}
		oralRing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.BOOST, 0));
		
		oralRing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_ORAL_GIVING, TFPotency.MAJOR_BOOST, 0));
		oralRing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_VAGINAL_GIVING, TFPotency.MAJOR_BOOST, 0));
		
		oralRing.setName("Evelyx's oral ring");
		
		return oralRing;
	}
	
	public void equipMilkingPumps(GameCharacter cow) {
//		if(cow.hasVagina()) {
//			cow.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), PresetColour.CLOTHING_BLACK, false), InventorySlot.VAGINA, true, cow);
//		}
		cow.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), PresetColour.CLOTHING_BLACK, false), InventorySlot.NIPPLE, true, cow);
		if(cow.hasBreastsCrotch()) {
			cow.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), PresetColour.CLOTHING_BLACK, false), InventorySlot.STOMACH, true, cow);
		}
	}

	public void unequipMilkingPumps(GameCharacter cow) {
		List<InventorySlot> slots = Util.newArrayListOfValues(InventorySlot.VAGINA, InventorySlot.NIPPLE, InventorySlot.STOMACH);
		for(InventorySlot slot : slots) {
			AbstractClothing clothing = cow.getClothingInSlot(slot);
			if(clothing!=null && (clothing.getClothingType()==ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps") || clothing.getClothingType()==ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"))) {
				cow.unequipClothingIntoVoid(clothing, true, cow);
			}
		}
	}
	
	public String upgradeEarTag(GameCharacter cow, int level) {
		AbstractClothing equippedTag = cow.getClothingInSlot(InventorySlot.PIERCING_EAR);
		AbstractClothing upgradedEarTag = getEvelyxEarTag(cow, level);
				
		if(equippedTag!=null && equippedTag.getClothingType()==ClothingType.getClothingTypeFromId("innoxia_quest_dairy_ear_tag")) {
			cow.forceUnequipClothingIntoVoid(cow, equippedTag);
			return "<p style='text-align:center;'>"
						+"You have traded in your old ear tag for "+upgradedEarTag.getDisplayName(true)+"!"
						+"<br/>"+cow.equipClothingFromNowhere(upgradedEarTag, true, cow)
					+"</p>";
			
		} else {
			cow.removeClothingByType(ClothingType.getClothingTypeFromId("innoxia_quest_dairy_ear_tag"));
			return "<p style='text-align:center;'>"
				+"You have traded in your old ear tag for "+upgradedEarTag.getDisplayName(true)+"!"
				+"<br/>"+cow.addClothing(upgradedEarTag, false)
			+"</p>";
		}
	}
	
	public AbstractClothing getEvelyxEarTag(GameCharacter cow, int level) {
		Colour tagColour = PresetColour.CLOTHING_BRONZE;
		if(level==1) {
			tagColour = PresetColour.CLOTHING_SILVER;
		} else if(level==2) {
			tagColour = PresetColour.CLOTHING_GOLD;
		}
		
		AbstractClothing earTag = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId(cow.isPlayer()?"innoxia_quest_dairy_ear_tag":"innoxia_quest_dairy_ear_tag_npc"), PresetColour.CLOTHING_BLACK, tagColour, null, false);
		
		if(level==0) {
			earTag.setSticker("level", "trainee");
		} else if(level==1) {
			earTag.setSticker("level", "heifer");
		} else if(level==2) {
			earTag.setSticker("level", "cow");
		}
		
		earTag.getEffects().removeIf(e->e.getPrimaryModifier()==TFModifier.TF_BREASTS && e.getSecondaryModifier()==TFModifier.TF_MOD_SIZE);
		earTag.getEffects().removeIf(e->e.getPrimaryModifier()==TFModifier.TF_BREASTS && e.getSecondaryModifier()==TFModifier.TF_MOD_WETNESS);
		earTag.getEffects().removeIf(e->e.getPrimaryModifier()==TFModifier.TF_BREASTS && e.getSecondaryModifier()==TFModifier.TF_MOD_REGENERATION);
		earTag.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, (level==0?CupSize.DD:(level==1?CupSize.GG:CupSize.KK)).getMeasurement()));
		earTag.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_BREASTS, TFModifier.TF_MOD_WETNESS, TFPotency.BOOST, (level==0?1000:(level==1?2500:5000))));
		earTag.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_BREASTS, TFModifier.TF_MOD_REGENERATION, TFPotency.BOOST, (level==0?10_000:(level==1?25_000:50_000))));
		
		return earTag;
	}
	
	public boolean isCowAbleToEquipGear(GameCharacter worker) {
		AbstractClothing collar = getEvelyxCollar(0);
		AbstractClothing noseRing = getEvelyxNoseRing(0);
		
		return worker.isAbleToEquip(collar, true, worker) && (worker.isAbleToEquip(noseRing, true, worker) || !worker.isPiercedNose());
	}
	
	public AbstractClothing getEvelyxCollar(int level) {
		Colour collarColour = PresetColour.CLOTHING_BRONZE;
		if(level==1) {
			collarColour = PresetColour.CLOTHING_SILVER;
		} else if(level==2) {
			collarColour = PresetColour.CLOTHING_GOLD;
		}
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_cattle_cowbell_collar", collarColour, PresetColour.CLOTHING_BLACK, collarColour, false);
		collar.setName("Evelyx's Milker cowbell collar");
		collar.clearEffects();
		
//		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		
		// +10 physique
		for(int i=0; i<(level==2?2:1); i++) {
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MINOR_BOOST, 0));
		}
		
		// +50 fertility:
		if(level>=1) {
			for(int j=0; j<(level==2?2:1); j++) {
				for(int i=0; i<16; i++) {
					collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0));
				}
				collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.BOOST, 0));
			}
		}
		
		return collar;
	}
	
	public AbstractClothing getEvelyxNoseRing(int level) {
		Colour collarColour = PresetColour.CLOTHING_BRONZE;
		if(level==1) {
			collarColour = PresetColour.CLOTHING_SILVER;
		} else if(level==2) {
			collarColour = PresetColour.CLOTHING_GOLD;
		}
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_cattle_piercing_nose_ring", collarColour, false);
		collar.setName("Evelyx's Milker nose ring");
		collar.clearEffects();
		
//		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		
		// -25 lust resistance
		for(int j=0; j<(level==2?2:1); j++) {
			for(int i=0; i<8; i++) {
				collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_DRAIN, 0));
			}
			collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MINOR_DRAIN, 0));
		}
		
		// +50 corruption:
		if(level>=1) {
			for(int j=0; j<(level==2?2:1); j++) {
				for(int i=0; i<16; i++) {
					collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.MAJOR_BOOST, 0));
				}
				collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.CORRUPTION, TFPotency.BOOST, 0));
			}
		}
		
		return collar;
	}
	
	public void applyCowTattoo(GameCharacter worker, boolean slave) {
		worker.addTattoo(InventorySlot.ANUS,
				new Tattoo(
					"innoxia_misc_none",
					PresetColour.CLOTHING_BLACK,
					null,
					null,
					false,
					new TattooWriting(
							(slave
								?"Princess's Pet"
								:"Evelyx's Dairy Milker")
								+" #"+(worker.isPlayer()?Main.game.getDialogueFlags().getSavedLong("evelyx_player_number"):(Main.game.getNpcTally()+1500)),
							PresetColour.CLOTHING_BLACK,
							false),
					null));
	}
	
	// Milk:
	
	public int getMilkedAmountForSession(GameCharacter worker, int hours) {
		float maxToMilk = worker.getBreastRawStoredMilkValue() + (worker.getLactationRegenerationPerSecond(true) * 60 * 60 * hours);
		
		if(worker.hasBreastsCrotch()) {
			maxToMilk += worker.getBreastCrotchRawStoredMilkValue() + (worker.getCrotchLactationRegenerationPerSecond(true) * 60 * 60 * hours);
		}
		
		return (int) maxToMilk;
	}
	
	// Other:
	

	public void applyWorkSessionEndEffects() {
		if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_work_not_pregnant_at_start") && Main.game.getPlayer().isPregnant() && !Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_work_impregnated")) {
			Main.game.getDialogueFlags().setFlag("innoxia_evelyx_work_impregnated", true);
		}
		
		if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_barn1")) {
			if(Main.game.getDialogueFlags().getSavedLong("evelyx_total_milked")>100*1000
					&& Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_work_fucked")
					&& Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isFeminine()) {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_work_promotion1", true);
			}
			
		} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_barn2")) {
			if(Main.game.getDialogueFlags().getSavedLong("evelyx_total_milked")>1000*1000
					&& Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_work_impregnated")
					&& Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isFeminine()) {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_work_promotion2", true);
			}
			
		} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_barn3")) {
			if(Main.game.getDialogueFlags().getSavedLong("evelyx_total_milked")>10_000*1000
					&& Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isFeminine()
					&& (Main.game.getPlayer().getRace()==Race.COW_MORPH || !Main.game.getPlayer().isAbleToHaveRaceTransformed())
					&& Main.game.getPlayer().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_work_promotion3", true);
			}
		}
	}
	
	public void applyEndWorkEffects() {
		Main.game.getPlayer().addStatusEffect(StatusEffect.FATIGUED, 21600);
		unequipMilkingPumps(Main.game.getPlayer());
	}
	
	public void setDairyRevealed(int barn) {
		World dairy = Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"));
		List<String> placesToReveal = Util.newArrayListOfValues(
				"innoxia_fields_dairyFarm_barn1_door",
				"innoxia_fields_dairyFarm_barn2_door",
				"innoxia_fields_dairyFarm_barn3_door",
				"innoxia_fields_dairyFarm_locked_door",
				"innoxia_fields_dairyFarm_corridor",
				"innoxia_fields_dairyFarm_dormitory",
				"innoxia_fields_dairyFarm_manager",
				"innoxia_fields_dairyFarm_storage");
		
		if(barn==0) {
			placesToReveal.add("innoxia_fields_dairyFarm_barn1");
		} else if(barn==1) {
			placesToReveal.add("innoxia_fields_dairyFarm_barn2");
		} else if(barn==2) {
			placesToReveal.add("innoxia_fields_dairyFarm_barn3");
		} else if(barn==3) {
			placesToReveal.add("innoxia_fields_dairyFarm_barn4");
			placesToReveal.add("innoxia_fields_dairyFarm_barn4_door");
			placesToReveal.add("innoxia_fields_dairyFarm_auction");
		}
		
		for(String placeId : placesToReveal) {
			for(Cell c : dairy.getCells(PlaceType.getPlaceTypeFromId(placeId))) {
				c.setDiscovered(true);
				if(barn==3) {
					c.setTravelledTo(true);
				}
			}
		}
	}

	public AbstractItem getVendingMachinePotionFem() {
		AbstractItem ingredient = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_cow_bubble_cream"));
		
		AbstractItem potion = EnchantingUtils.craftItem(ingredient,
				Util.newArrayListOfValues(
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.BOOST, 0)));
		potion.setName("Evelyx's 'Pretty Cow' Potion");
		
		return potion;
	}

	public AbstractItem getVendingMachinePotionMilky() {
		AbstractItem ingredient = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_cow_bubble_cream"));
		
		AbstractItem potion = EnchantingUtils.craftItem(ingredient,
				Util.newArrayListOfValues(
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_REGENERATION, TFPotency.MAJOR_BOOST, 0)));
		potion.setName("Evelyx's 'Milky Cow' Potion");
		
		return potion;
	}

	public AbstractItem getVendingMachinePotionBusty() {
		AbstractItem ingredient = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_cow_bubble_cream"));
		
		AbstractItem potion = EnchantingUtils.craftItem(ingredient,
				Util.newArrayListOfValues(
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MINOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_ORIFICE_PUFFY, TFPotency.MINOR_BOOST, 0)));
		potion.setName("Evelyx's 'Busty Cow' Potion");
		
		return potion;
	}

	public AbstractItem getVendingMachinePotionWet() {
		AbstractItem ingredient = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_cow_bubble_cream"));
		
		AbstractItem potion = EnchantingUtils.craftItem(ingredient,
				Util.newArrayListOfValues(
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 0),
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_VAGINA_SQUIRTER, TFPotency.MINOR_BOOST, 0)));
		potion.setName("Evelyx's 'Wet Cow' Potion");
		
		return potion;
	}

	public AbstractItem getVendingMachinePotionBimbo() {
		AbstractItem ingredient = Main.game.getItemGen().generateItem(ItemType.FETISH_UNREFINED);
		
		AbstractItem potion = EnchantingUtils.craftItem(ingredient,
				Util.newArrayListOfValues(
						new ItemEffect(ingredient.getEnchantmentEffect(), TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_BIMBO, TFPotency.BOOST, 0)));
		potion.setName("Evelyx's 'Happy Cow' Potion");
		
		return potion;
	}
	
	
	// Bad end methods:
	
	public String stripPlayer() {
		StringBuilder sb = new StringBuilder();
		boolean anySeals = false;
		int clothingValue = Main.game.getPlayer().getInventoryNonEquippedValue();
		int flames = Main.game.getPlayer().getMoney();
		
		// Clothing removal:
		List<AbstractClothing> clothingToRemove = new ArrayList<>();
		for(AbstractClothing clothing : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
			if(clothing.getClothingType().getClothingSet()!=SetBonus.getSetBonusFromId("innoxia_cattle")
					&& clothing.getClothingType().getClothingSet()!=SetBonus.getSetBonusFromId("innoxia_bdsm")) {
				clothingToRemove.add(clothing);
				if(!anySeals) {
					anySeals = clothing.isSealed();
				}
			}
		}
		
		for(AbstractClothing clothing : clothingToRemove) {
			clothingValue += clothing.getValue();
			Main.game.getPlayer().forceUnequipClothingIntoVoid(this, clothing);
		}
		
		
		// Weapon removal:
		for(AbstractWeapon weapon : Main.game.getPlayer().getMainWeaponArray()) {
			if(weapon!=null) {
				clothingValue += weapon.getValue();
			}
		}
		for(AbstractWeapon weapon : Main.game.getPlayer().getOffhandWeaponArray()) {
			if(weapon!=null) {
				clothingValue += weapon.getValue();
			}
		}
		Main.game.getPlayer().unequipAllWeaponsIntoVoid(true);
		
		
		// Item removal:
		Main.game.getPlayer().clearNonEquippedInventory(true);
		
		// Generating the content based on the value of inventory stripped:
		UtilText.addSpecialParsingString(Util.intToString((flames/100) * 100), true);
		if(flames > 10_000_000) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_FLAMES_TOP"));
		} else if(flames > 1_000_000) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_FLAMES_RICH"));
		} else if(flames > 100_000) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_FLAMES_LOTS"));
		} else if(flames > 1_000) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_FLAMES_AVERAGE"));
		} else {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_FLAMES_POOR"));
		}

		if(anySeals) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_CLOTHING_START_SEALS"));
		} else {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_CLOTHING_START"));
		}
		if(clothingValue > 10_000_000) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_CLOTHING_TOP"));
		} else if(clothingValue > 1_000_000) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_CLOTHING_RICH"));
		} else if(clothingValue > 100_000) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_CLOTHING_LOTS"));
		} else if(clothingValue > 1_000) {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_CLOTHING_AVERAGE"));
		} else {
			sb.append(UtilText.parseFromXMLFile("badEnds/evelyx", "STRIP_CLOTHING_POOR"));
		}
		
		return sb.toString();
	}
	
	/**
	 * Takes in 'Princess', who is an instance of the NPC class 'LunetteMelee', and applies changes to make her consistent across all playthroughs.
	 * I didn't add her as an instanced character due to her only appearing in this one bad end.
	 */
	public void badEndInitPrincess(NPC princess) {
		princess.setGenericName("Princess");
		
		princess.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		princess.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		princess.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
		princess.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		
		PerkManager.initialisePerks(princess,
				Util.newArrayListOfValues(Perk.CLOTHING_ENCHANTER),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 5),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 1)));
		
		princess.setSpeechColour(PresetColour.BASE_RED);
		
		// Persona:
		princess.setPersonalityTraits(
				PersonalityTrait.CONFIDENT,
				PersonalityTrait.BRAVE,
				PersonalityTrait.SELFISH,
				PersonalityTrait.LEWD);
		
		princess.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		princess.setHistory(Occupation.NPC_LUNETTE_HERD);

		princess.clearFetishes();
		princess.clearFetishDesires();
		
		princess.addFetish(Fetish.FETISH_DOMINANT);
		princess.addFetish(Fetish.FETISH_SADIST);

		princess.addFetish(Fetish.FETISH_ANAL_GIVING);
		princess.addFetish(Fetish.FETISH_VAGINAL_GIVING);
		princess.addFetish(Fetish.FETISH_ORAL_RECEIVING);
		princess.addFetish(Fetish.FETISH_PENIS_GIVING);
		princess.addFetish(Fetish.FETISH_NON_CON_DOM);
		
		princess.setFetishDesire(Fetish.FETISH_TRANSFORMATION_GIVING, FetishDesire.THREE_LIKE);
		princess.setFetishDesire(Fetish.FETISH_BONDAGE_APPLIER, FetishDesire.THREE_LIKE);
		princess.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.THREE_LIKE);
		princess.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
		princess.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
		
		princess.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ZERO_HATE);
		princess.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
		
		// Body:
		princess.setAgeAppearanceDifferenceToAppearAsAge(32);
		princess.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);
		princess.setWingType(WingType.NONE);
		princess.setHornType(HornType.CURVED);
		princess.setHornLength(HornLength.THREE_HUGE.getMedianValue());
		princess.setLegType(LegType.DEMON_HORSE_HOOFED);
		princess.setLegConfiguration(LegType.DEMON_HORSE_HOOFED, LegConfiguration.QUADRUPEDAL, true);
		princess.setArmRows(2);
		
		// Core:
		princess.setHeight(244); // 8 feet tall
		princess.setFemininity(75);
		princess.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		princess.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		princess.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_YELLOW));
		princess.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED_DARK), true);
		princess.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BLACK), true);

		princess.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_EBONY), false);
		princess.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_EBONY), false);
		princess.setSkinCovering(new Covering(BodyCoveringType.PENIS, CoveringPattern.OMBRE, PresetColour.SKIN_RED_DARK, false, PresetColour.SKIN_EBONY, false), false);
		princess.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_EBONY, false, PresetColour.ORIFICE_INTERIOR, false), false);
		princess.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_EBONY), false);
		princess.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_BLACK), false);
		
		princess.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		princess.setHairLength(HairLength.TWO_SHORT.getMedianValue());
		princess.setHairStyle(HairStyle.PIXIE);
		
		princess.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		princess.setUnderarmHair(BodyHair.ZERO_NONE);
		princess.setAssHair(BodyHair.ZERO_NONE);
		princess.setPubicHair(BodyHair.ZERO_NONE);
		princess.setFacialHair(BodyHair.ZERO_NONE);

//		princess.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE));
		princess.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_BLACK));
//		princess.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		princess.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_BLACK));
		princess.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		princess.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		princess.setFaceVirgin(false);
		princess.setLipSize(LipSize.TWO_FULL);
		princess.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
		princess.setTongueLength(TongueLength.ONE_LONG.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		princess.setNippleVirgin(false);
		princess.setBreastSize(CupSize.F.getMeasurement());
		princess.setBreastShape(BreastShape.ROUND);
		princess.setNippleSize(NippleSize.THREE_LARGE);
		princess.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		princess.setAssVirgin(false);
		princess.setAssBleached(false);
		princess.setAssSize(AssSize.FIVE_HUGE);
		princess.setHipSize(HipSize.FIVE_VERY_WIDE);
		princess.setAssCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		princess.setAssWetness(Wetness.ZERO_DRY);
		// Horse-like modifiers:
		princess.addAssOrificeModifier(OrificeModifier.PUFFY);
		princess.addAssOrificeModifier(OrificeModifier.RIBBED);
		princess.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		
		// Penis:
		princess.setPenisVirgin(false);
		princess.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		princess.setPenisSize(62); // 2 foot
		princess.setTesticleSize(TesticleSize.FIVE_MASSIVE);
		princess.setPenisCumStorage(5000);
		princess.setPenisCumExpulsion(50);
		princess.setPenisCumProductionRegeneration(FluidRegeneration.FOUR_VERY_RAPID.getMedianRegenerationValuePerDay());
		princess.fillCumToMaxStorage();
		princess.setTesticleCount(2);
		// Horse-like modifiers:
		princess.clearPenisModifiers();
		princess.addPenisModifier(PenetrationModifier.FLARED);
		princess.addPenisModifier(PenetrationModifier.VEINY);
		princess.addPenisModifier(PenetrationModifier.SHEATHED);
		princess.addCumModifier(FluidModifier.MUSKY);
		
		// Vagina:
		princess.setVaginaVirgin(false);
		princess.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		princess.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		princess.setVaginaSquirter(true);
		princess.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		princess.setVaginaWetness(Wetness.FOUR_SLIMY);
		princess.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue());
		princess.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		princess.addGirlcumModifier(FluidModifier.MUSKY);
		
		// Feet:
		// Foot shape
		
		
		// --- Init Inventory ---

		princess.unequipAllClothingIntoVoid(true, true);
		princess.setEssenceCount(10_000);
		princess.setMoney(150_000);

		princess.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_axe_battle", DamageType.FIRE));
		princess.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_axe_battle", DamageType.FIRE));
		
		princess.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_velvet_choker", PresetColour.CLOTHING_BLACK, false), true, princess);
		princess.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_nipple_tape_crosses", PresetColour.CLOTHING_BLACK, false), true, princess);
		princess.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_BLACK, false), true, princess);
		
		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_gemstone_ring_unisex", PresetColour.CLOTHING_PLATINUM, PresetColour.CLOTHING_RED_VERY_DARK, null, false);
		for(int i=0;i<3;i++) {
			ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.HEALTH_MAXIMUM, TFPotency.MAJOR_BOOST, 0));
			ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
			ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.INTELLIGENCE, TFPotency.MAJOR_BOOST, 0));
		}
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.HEALTH_MAXIMUM, TFPotency.MINOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MINOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.INTELLIGENCE, TFPotency.MINOR_BOOST, 0));
		ring.setName("Lunette's Gift");
		princess.equipClothingFromNowhere(ring, true, princess);

		princess.setPiercedNose(true);
		princess.setPiercedNipples(true);
		princess.setPiercedEar(true);
		princess.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_PLATINUM, false), true, princess);
		princess.setPiercedNavel(true);
		princess.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_PLATINUM, PresetColour.CLOTHING_RED_VERY_DARK, null, false), true, princess);
		princess.setPiercedTongue(true);
		princess.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_PLATINUM, false), InventorySlot.PIERCING_TONGUE, true, princess);
	}
	
	// Stage 1:
	public void applyBadEndPussy() {
		Main.game.getPlayer().addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		Main.game.getPlayer().addVaginaOrificeModifier(OrificeModifier.TENTACLED);
		Main.game.getPlayer().addVaginaOrificeModifier(OrificeModifier.PUFFY);
		Main.game.getPlayer().addVaginaOrificeModifier(OrificeModifier.RIBBED);
		
		Main.game.getPlayer().setVaginaWetness(Wetness.SEVEN_DROOLING);
		Main.game.getPlayer().setVaginaSquirter(true);
		Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		Main.game.getPlayer().setVaginaCapacity(Capacity.TWO_TIGHT, true);
		Main.game.getPlayer().setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		Main.game.getPlayer().setVaginaPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
	}
	
	public void applyBadEndAnus() {
		Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.TENTACLED);
		Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.PUFFY);
		Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.RIBBED);
		
		Main.game.getPlayer().setAssWetness(Wetness.SEVEN_DROOLING);
		Main.game.getPlayer().setAssCapacity(Capacity.TWO_TIGHT, true);
		Main.game.getPlayer().setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		Main.game.getPlayer().setAssPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
	}
	
	public void applyBadEndMouth() {
		Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.TENTACLED);
		Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY);
		Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.RIBBED);
		
		Main.game.getPlayer().setLipSize(LipSize.SEVEN_ABSURD);
		Main.game.getPlayer().setTongueLength(50);
		Main.game.getPlayer().addTongueModifier(TongueModifier.STRONG);
		Main.game.getPlayer().addTongueModifier(TongueModifier.TENTACLED);
		
		Main.game.getPlayer().setFaceWetness(Wetness.SEVEN_DROOLING.getValue());
		Main.game.getPlayer().setFaceCapacity(Capacity.TWO_TIGHT, true);
		Main.game.getPlayer().setFaceElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		Main.game.getPlayer().setFacePlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
	}
	

	// Stage 2:
	public void applyBadEndFlavours() {
		Main.game.getPlayer().setGirlcumFlavour(FluidFlavour.STRAWBERRY);
		Main.game.getPlayer().setMilkCrotchFlavour(FluidFlavour.CHOCOLATE);
		Main.game.getPlayer().setMilkFlavour(FluidFlavour.VANILLA);
	}
	
	public void applyBadEndHyper() {
		Main.game.getPlayer().setBreastSize(CupSize.XXX_DD);
		Main.game.getPlayer().setNippleSize(NippleSize.FOUR_MASSIVE);
		Main.game.getPlayer().setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		Main.game.getPlayer().setBreastCrotchSize(CupSize.XXX_DD);
		Main.game.getPlayer().setNippleCrotchSize(NippleSize.FOUR_MASSIVE);
		Main.game.getPlayer().setAreolaeCrotchSize(AreolaeSize.FOUR_MASSIVE);
	}
	
	public void applyBadEndMulti() {
		Main.game.getPlayer().setBreastRows(Breast.MAXIMUM_BREAST_ROWS);
		Main.game.getPlayer().setNippleCountPerBreast(Breast.MAXIMUM_NIPPLES_PER_BREAST);
		Main.game.getPlayer().addNippleOrificeModifier(OrificeModifier.PUFFY);
		Main.game.getPlayer().setBreastCrotchRows(Breast.MAXIMUM_BREAST_ROWS);
		Main.game.getPlayer().setNippleCrotchCountPerBreast(Breast.MAXIMUM_NIPPLES_PER_BREAST);
		Main.game.getPlayer().addNippleCrotchOrificeModifier(OrificeModifier.PUFFY);
	}

	// Stage 3:
	public void applyBadEndBondage() {
		Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_IRON, false), true, this);
		Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_IRON, null, false), true, this);
		Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_croptop", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_IRON, null, false), true, this);
		Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_bra", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_IRON, null, false), true, this);
		Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("sage_latex_corset", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_IRON, null, false), true, this);
		Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_restraints", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_IRON, null, false), true, this);
		Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_spreaderbar", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_IRON, null, false), true, this);
	}
	
	public void applyBadEndBimbo() {
		Main.game.getPlayer().addFetish(Fetish.FETISH_BIMBO);
	}
	
	public void applyBadEndFeral(NPC princess) {
		Main.game.getPlayer().setFeral(Subspecies.COW_MORPH);
		
		Main.game.getPlayer().setVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), princess, " in Evelyx's slave barn as you were transformed into a feral cow");
		Main.game.getPlayer().setVaginaVirgin(false);
		
		Main.game.getPlayer().setVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), princess, " in Evelyx's slave barn soon after you were transformed into a feral cow");
		Main.game.getPlayer().setAssVirgin(false);
		
		Main.game.getPlayer().setVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), princess, " in Evelyx's slave barn soon after you were transformed into a feral cow");
		Main.game.getPlayer().setFaceVirgin(false);
		
		if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_bad_end_pussy")) {
			applyBadEndPussy();
		} else if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_bad_end_oral")) {
			applyBadEndMouth();
		} else if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_bad_end_anal")) {
			applyBadEndAnus();
		}

		if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_bad_end_flavours")) {
			applyBadEndFlavours();
		} else if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_bad_end_hyper")) {
			applyBadEndHyper();
		} else if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_bad_end_multi")) {
			applyBadEndMulti();
		}
	}
}



