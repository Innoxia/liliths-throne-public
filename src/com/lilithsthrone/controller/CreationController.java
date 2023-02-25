package com.lilithsthrone.controller;

import java.time.Month;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInventoryEventListener;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
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
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
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
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class CreationController {
	// Antenna
	public static void initAntennaTypeListeners() {
		for (AbstractAntennaType antennaType : AntennaType.getAllAntennaTypes()) {
			String id = "ANTENNA_"+AntennaType.getIdFromAntennaType(antennaType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAntennaType(antennaType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAntennaCountListeners() {
		for (int i = 1; i<=Antenna.MAXIMUM_ROWS; i++) {
			String id = "ANTENNA_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAntennaRows(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAntennaLengthListeners() {
		for (HornLength antennaLength : HornLength.values()) {
			String id = "ANTENNA_LENGTH_"+antennaLength;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAntennaLength(antennaLength.getMedianValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAntennaRowsListeners() {
		for (int i = 1; i<=Antenna.MAXIMUM_ANTENNAE_PER_ROW; i++) {
			String id = "ANTENNA_COUNT_PER_ROW_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAntennaePerRow(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Anus
	public static void initAnusCapacityListeners() {
		for (Capacity capacity : Capacity.getCapacityListFromPreferences()) {
			String id = "ANUS_CAPACITY_"+capacity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAssCapacity(capacity.getMedianValue(), true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAnusDepthListeners() {
		for (OrificeDepth depth : OrificeDepth.values()) {
			String id = "ANUS_DEPTH_"+depth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAssDepth(depth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAnusElasticityListeners() {
		for (OrificeElasticity elasticity : OrificeElasticity.values()) {
			String id = "ANUS_ELASTICITY_"+elasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAssElasticity(elasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAnusModifierListeners() {
		for (OrificeModifier orificeMod : OrificeModifier.values()) {
			String id = "ANUS_MOD_"+orificeMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasAssOrificeModifier(orificeMod)) {
						BodyChanging.getTarget().removeAssOrificeModifier(orificeMod);
					} else {
						BodyChanging.getTarget().addAssOrificeModifier(orificeMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(orificeMod.getName()),
						(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
			}
		}
	}
	
	public static void initAnusPlasticityListeners() {
		for (OrificePlasticity plasticity : OrificePlasticity.values()) {
			String id = "ANUS_PLASTICITY_"+plasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAssPlasticity(plasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAnusWetnessListeners() {
		for (Wetness wetness : Wetness.values()) {
			String id = "ANUS_WETNESS_"+wetness;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAssWetness(wetness.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Arm
	public static void initArmTypeListeners() {
		for (AbstractArmType armType : ArmType.getAllArmTypes()) {
			String id = "ARM_"+ArmType.getIdFromArmType(armType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setArmType(armType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initArmCountListeners() {
		for (int i = 1; i<=Arm.MAXIMUM_ROWS; i++) {
			String id = "ARM_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setArmRows(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Ass
	public static void initAssTypeListeners() {
		for (AbstractAssType assType : AssType.getAllAssTypes()) {
			String id = "ASS_"+AssType.getIdFromAssType(assType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAssType(assType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAssSizeListeners(boolean limitedSelection) {
		AssSize[] sizes;
		if (limitedSelection) {
			sizes = CharacterModificationUtils.getAssSizesAvailable();
		} else {
			sizes = AssSize.values();
		}
		for (AssSize as : sizes) {
			String id = "ASS_SIZE_"+as;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAssSize(as.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initHipSizeListeners(boolean limitedSelection) {
		HipSize[] sizes;
		if (limitedSelection) {
			sizes = CharacterModificationUtils.getHipSizesAvailable();
		} else {
			sizes = HipSize.values();
		}
		for (HipSize hs : sizes) {
			String id = "HIP_SIZE_"+hs;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setHipSize(hs.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Breast
	public static void initBreastTypeListeners() {
		for (AbstractBreastType breastType : BreastType.getAllBreastTypes()) {
			String id = "BREAST_"+BreastType.getIdFromBreastType(breastType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setBreastType(breastType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initBreastCountListeners() {
		for (int i = 1; i<=Breast.MAXIMUM_BREAST_ROWS; i++) {
			String id = "BREAST_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setBreastRows(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initBreastShapeListeners() {
		for (BreastShape bs : BreastShape.values()) {
			String id = "BREAST_SHAPE_"+bs;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setBreastShape(bs);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initBreastSizeListeners(boolean limitedSelection) {
		String id;
		if (limitedSelection) {
			for (CupSize cs : CharacterModificationUtils.getBreastSizesAvailable()) {
				id = "BREAST_SIZE_"+cs;
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						BodyChanging.getTarget().setBreastSize(cs.getMeasurement());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
		} else {
			id = "BREAST_SIZE_INCREASE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementBreastSize(1);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "BREAST_SIZE_INCREASE_LARGE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementBreastSize(5);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "BREAST_SIZE_DECREASE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementBreastSize(-1);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				if (BodyChanging.getTarget().getBreastRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"[style.colourBad(Size Decrease Blocked)]",
							UtilText.parse(BodyChanging.getTarget(),
									"[npc.NamePos] breasts cannot be shrunk any further while eggs are being incubated in them!"),
							32));
				}
			}
			id = "BREAST_SIZE_DECREASE_LARGE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementBreastSize(-5);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				if (BodyChanging.getTarget().getBreastRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"[style.colourBad(Size Decrease Blocked)]",
							UtilText.parse(BodyChanging.getTarget(),
									"[npc.NamePos] breasts cannot be shrunk any further while eggs are being incubated in them!"),
							32));
				}
			}
		}
	}
	
	public static void initLactationCapacityListeners() {
		String id = "MILK_PRODUCTION_INCREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBreastMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_SMALL));
				BodyChanging.getTarget().fillMilkToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_PRODUCTION_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBreastMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_AVERAGE));
				BodyChanging.getTarget().fillMilkToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_PRODUCTION_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBreastMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_LARGE));
				BodyChanging.getTarget().fillMilkToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_PRODUCTION_INCREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBreastMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_HUGE));
				BodyChanging.getTarget().fillMilkToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_PRODUCTION_DECREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_PRODUCTION_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_PRODUCTION_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_PRODUCTION_DECREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initLactationFlavourListeners() {
		for (FluidFlavour flavour : FluidFlavour.values()) {
			String id = "MILK_FLAVOUR_"+flavour;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setMilkFlavour(flavour);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initLactationModifierListeners() {
		for (FluidModifier modifier : FluidModifier.values()) {
			String id = "MILK_MODIFIER_"+modifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasMilkModifier(modifier)) {
						BodyChanging.getTarget().removeMilkModifier(modifier);
					} else {
						BodyChanging.getTarget().addMilkModifier(modifier);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(modifier.getName()),
						(modifier.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+modifier.getDescription()));
			}
		}
	}
	
	public static void initLactationRegenListeners() {
		String id = "MILK_REGENERATION_INCREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_REGENERATION_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_REGENERATION_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_REGENERATION_INCREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_REGENERATION_DECREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_REGENERATION_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_REGENERATION_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_REGENERATION_DECREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initNippleCapacityListeners() {
		for (Capacity capacity : Capacity.getCapacityListFromPreferences()) {
			String id = "NIPPLE_CAPACITY_"+capacity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCapacity(capacity.getMedianValue(), true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initNippleCountListeners() {
		for (int i = 1; i<=Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
			String id = "NIPPLE_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCountPerBreast(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initNippleDepthListeners() {
		for (OrificeDepth depth : OrificeDepth.values()) {
			String id = "NIPPLE_DEPTH_"+depth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleDepth(depth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initNippleElasticityListeners() {
		for (OrificeElasticity elasticity : OrificeElasticity.values()) {
			String id = "NIPPLE_ELASTICITY_"+elasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleElasticity(elasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initNippleModifierListeners() {
		for (OrificeModifier orificeMod : OrificeModifier.values()) {
			String id = "NIPPLE_MOD_"+orificeMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasNippleOrificeModifier(orificeMod)) {
						BodyChanging.getTarget().removeNippleOrificeModifier(orificeMod);
					} else {
						BodyChanging.getTarget().addNippleOrificeModifier(orificeMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(orificeMod.getName()),
						(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
			}
		}
	}
	
	public static void initNipplePlasticityListeners() {
		for (OrificePlasticity plasticity : OrificePlasticity.values()) {
			String id = "NIPPLE_PLASTICITY_"+plasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNipplePlasticity(plasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initNippleShapeListeners() {
		for (NippleShape ns : NippleShape.values()) {
			String id = "NIPPLE_SHAPE_"+ns;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleShape(ns);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initNippleSizeListeners() {
		for (NippleSize ns : NippleSize.values()) {
			String id = "NIPPLE_SIZE_"+ns;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleSize(ns.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAreolaeSizeListeners() {
		for (AreolaeSize as : AreolaeSize.values()) {
			String id = "AREOLAE_SIZE_"+as;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAreolaeSize(as.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Core
	public static void initBodyMaterialListeners() {
		for (BodyMaterial mat : BodyMaterial.values()) {
			String id = "BODY_MATERIAL_"+mat;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setBodyMaterial(mat);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initBodySizeListeners() {
		for (BodySize bs : BodySize.values()) {
			String id = "BODY_SIZE_"+bs;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setBodySize(bs.getMedianValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initGenitalArrangementListeners() {
		for (GenitalArrangement genitalArrangement : GenitalArrangement.values()) {
			String id = "GENITAL_ARRANGEMENT_"+genitalArrangement;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setGenitalArrangement(genitalArrangement);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initHeightListeners() {
		String id = "HEIGHT_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementHeight(1, BodyChanging.isDebugMenu());
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "HEIGHT_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementHeight(5, BodyChanging.isDebugMenu());
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "HEIGHT_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementHeight(-1, BodyChanging.isDebugMenu());
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "HEIGHT_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementHeight(-5, BodyChanging.isDebugMenu());
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initMuscleListeners() {
		for (Muscle muscle : Muscle.values()) {
			String id = "MUSCLE_"+muscle;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setMuscle(muscle.getMedianValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Crotch-Breast
	public static void initCrotchBreastTypeListeners() {
		for (AbstractBreastType breastType : BreastType.getAllBreastTypes()) {
			String id = "BREAST_CROTCH_"+BreastType.getIdFromBreastType(breastType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setBreastCrotchType(breastType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchBreastCountListeners() {
		for (int i = 1; i<=Breast.MAXIMUM_BREAST_ROWS; i++) {
			String id = "BREAST_CROTCH_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setBreastCrotchRows(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchBreastShapeListeners() {
		for (BreastShape bs : BreastShape.values()) {
			String id = "BREAST_CROTCH_SHAPE_"+bs;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setBreastCrotchShape(bs);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchBreastSizeListeners() {
		String id = "BREAST_CROTCH_SIZE_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchSize(1);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BREAST_CROTCH_SIZE_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchSize(5);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BREAST_CROTCH_SIZE_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchSize(-1);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			if (BodyChanging.getTarget().getBreastRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"[style.colourBad(Size Decrease Blocked)]",
						UtilText.parse(BodyChanging.getTarget(),
								"[npc.NamePos] breasts cannot be shrunk any further while eggs are being incubated in them!"),
						32));
			}
		}
		id = "BREAST_CROTCH_SIZE_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchSize(-5);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			if (BodyChanging.getTarget().getBreastRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"[style.colourBad(Size Decrease Blocked)]",
						UtilText.parse(BodyChanging.getTarget(),
								"[npc.NamePos] breasts cannot be shrunk any further while eggs are being incubated in them!"),
						32));
			}
		}
	}
	
	public static void initCrotchLactationCapacityListeners() {
		String id = "MILK_CROTCH_PRODUCTION_INCREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBreastCrotchMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_SMALL));
				BodyChanging.getTarget().fillMilkCrotchToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_PRODUCTION_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBreastCrotchMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_AVERAGE));
				BodyChanging.getTarget().fillMilkCrotchToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_PRODUCTION_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBreastCrotchMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_LARGE));
				BodyChanging.getTarget().fillMilkCrotchToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_PRODUCTION_INCREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBreastCrotchMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_HUGE));
				BodyChanging.getTarget().fillMilkToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_PRODUCTION_DECREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_PRODUCTION_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_PRODUCTION_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_PRODUCTION_DECREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initCrotchLactationFlavourListeners() {
		for (FluidFlavour flavour : FluidFlavour.values()) {
			String id = "MILK_CROTCH_FLAVOUR_"+flavour;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setMilkCrotchFlavour(flavour);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchLactationModifierListeners() {
		for (FluidModifier modifier : FluidModifier.values()) {
			String id = "MILK_CROTCH_MODIFIER_"+modifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasMilkCrotchModifier(modifier)) {
						BodyChanging.getTarget().removeMilkCrotchModifier(modifier);
					} else {
						BodyChanging.getTarget().addMilkCrotchModifier(modifier);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(modifier.getName()),
						(modifier.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+modifier.getDescription()));
			}
		}
	}
	
	public static void initCrotchLactationRegenListeners() {
		String id = "MILK_CROTCH_REGENERATION_INCREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_REGENERATION_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_REGENERATION_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_REGENERATION_INCREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_REGENERATION_DECREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_REGENERATION_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_REGENERATION_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "MILK_CROTCH_REGENERATION_DECREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initCrotchNippleCapacityListeners() {
		for (Capacity capacity : Capacity.getCapacityListFromPreferences()) {
			String id = "NIPPLE_CROTCH_CAPACITY_"+capacity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCrotchCapacity(capacity.getMedianValue(), true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchNippleCountListeners() {
		for (int i = 1; i<=Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
			String id = "NIPPLE_CROTCH_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCrotchCountPerBreast(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchNippleDepthListeners() {
		for (OrificeDepth depth : OrificeDepth.values()) {
			String id = "NIPPLE_CROTCH_DEPTH_"+depth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCrotchDepth(depth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchNippleElasticityListeners() {
		for (OrificeElasticity elasticity : OrificeElasticity.values()) {
			String id = "NIPPLE_CROTCH_ELASTICITY_"+elasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCrotchElasticity(elasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchNippleModifierListeners() {
		for (OrificeModifier orificeMod : OrificeModifier.values()) {
			String id = "NIPPLE_CROTCH_MOD_"+orificeMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasNippleCrotchOrificeModifier(orificeMod)) {
						BodyChanging.getTarget().removeNippleCrotchOrificeModifier(orificeMod);
					} else {
						BodyChanging.getTarget().addNippleCrotchOrificeModifier(orificeMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(orificeMod.getName()),
						(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
			}
		}
	}
	
	public static void initCrotchNipplePlasticityListeners() {
		for (OrificePlasticity plasticity : OrificePlasticity.values()) {
			String id = "NIPPLE_CROTCH_PLASTICITY_"+plasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCrotchPlasticity(plasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchNippleShapeListeners() {
		for (NippleShape ns : NippleShape.values()) {
			String id = "NIPPLE_CROTCH_SHAPE_"+ns;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCrotchShape(ns);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchNippleSizeListeners() {
		for (NippleSize ns : NippleSize.values()) {
			String id = "NIPPLE_CROTCH_SIZE_"+ns;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setNippleCrotchSize(ns.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initCrotchAreolaeSizeListeners() {
		for (AreolaeSize as : AreolaeSize.values()) {
			String id = "AREOLAE_CROTCH_SIZE_"+as;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAreolaeCrotchSize(as.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Ear
	public static void initEarTypeListeners() {
		for (AbstractEarType earType : EarType.getAllEarTypes()) {
			String id = "EAR_"+EarType.getIdFromEarType(earType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setEarType(earType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Eye
	public static void initEyeTypeListeners() {
		for (AbstractEyeType eyeType : EyeType.getAllEyeTypes()) {
			String id = "EYE_"+EyeType.getIdFromEyeType(eyeType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setEyeType(eyeType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initEyeCountListeners() {
		for (int i = 1; i<=Eye.MAXIMUM_PAIRS; i++) {
			String id = "EYE_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setEyePairs(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
		}
	}
	
	public static void initEyeShapeListeners() { // Iris & Pupil are always called together so no need to loop twice in separate methods
		for (EyeShape eyeShape : EyeShape.values()) {
			String id = "IRIS_SHAPE_"+eyeShape;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setIrisShape(eyeShape);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PUPIL_SHAPE_"+eyeShape;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setPupilShape(eyeShape);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Face
	public static void initFaceTypeListeners() {
		for (AbstractFaceType faceType : FaceType.getAllFaceTypes()) {
			String id = "FACE_"+FaceType.getIdFromFaceType(faceType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFaceType(faceType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initLipSizeListeners() {
		for (LipSize ls : LipSize.values()) {
			String id = "LIP_SIZE_"+ls;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setLipSize(ls.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initThroatCapacityListeners() {
		for (Capacity capacity : Capacity.getCapacityListFromPreferences()) {
			String id = "THROAT_CAPACITY_"+capacity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFaceCapacity(capacity.getMedianValue(), true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initThroatDepthListeners() {
		for (OrificeDepth depth : OrificeDepth.values()) {
			String id = "THROAT_DEPTH_"+depth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFaceDepth(depth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initThroatElasticityListeners() {
		for (OrificeElasticity elasticity : OrificeElasticity.values()) {
			String id = "THROAT_ELASTICITY_"+elasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFaceElasticity(elasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initThroatModifierListeners() {
		for (OrificeModifier orificeMod : OrificeModifier.values()) {
			String id = "THROAT_MOD_"+orificeMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasFaceOrificeModifier(orificeMod)) {
						BodyChanging.getTarget().removeFaceOrificeModifier(orificeMod);
					} else {
						BodyChanging.getTarget().addFaceOrificeModifier(orificeMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(orificeMod.getName()),
						(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
			}
		}
	}
	
	public static void initThroatPlasticityListeners() {
		for (OrificePlasticity plasticity : OrificePlasticity.values()) {
			String id = "THROAT_PLASTICITY_"+plasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFacePlasticity(plasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initThroatWetnessListeners() {
		for (Wetness wetness : Wetness.values()) {
			String id = "THROAT_WETNESS_"+wetness;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFaceWetness(wetness.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initTongueLengthListeners() {
		for (TongueLength tongueLength : TongueLength.values()) {
			String id = "TONGUE_LENGTH_"+tongueLength;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setTongueLength(tongueLength.getMedianValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initTongueModifierListeners() {
		for (TongueModifier tongueMod : TongueModifier.values()) {
			String id = "TONGUE_MOD_"+tongueMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasTongueModifier(tongueMod)) {
						BodyChanging.getTarget().removeTongueModifier(tongueMod);
					} else {
						BodyChanging.getTarget().addTongueModifier(tongueMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Hair
	public static void initHairTypeListeners() {
		for (AbstractHairType hairType : HairType.getAllHairTypes()) {
			String id = "HAIR_"+HairType.getIdFromHairType(hairType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setHairType(hairType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initNeckFluffListeners() {
		String id = "NECK_FLUFF_ON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						BodyChanging.getTarget().setNeckFluff(true);
					}
				});
			}, false);
		}
		
		id = "NECK_FLUFF_OFF";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						BodyChanging.getTarget().setNeckFluff(false);
					}
				});
			}, false);
		}
	}
	
	// Horn
	public static void initHornTypeListeners() {
		for (AbstractHornType hornType : HornType.getAllHornTypes()) {
			String id = "HORN_"+HornType.getIdFromHornType(hornType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setHornType(hornType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initHornCountListeners() {
		for (int i = 1; i<=Horn.MAXIMUM_ROWS; i++) {
			String id = "HORN_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setHornRows(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initHornLengthListeners() {
		for (HornLength hornLength : HornLength.values()) {
			String id = "HORN_LENGTH_"+hornLength;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setHornLength(hornLength.getMedianValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initHornRowsListeners() {
		for (int i = 1; i<=Horn.MAXIMUM_HORNS_PER_ROW; i++) {
			String id = "HORN_COUNT_PER_ROW_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setHornsPerRow(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Leg
	public static void initLegTypeListeners() {
		for (AbstractLegType legType : LegType.getAllLegTypes()) {
			String id = "LEG_"+LegType.getIdFromLegType(legType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setLegType(legType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initLegConfigListeners() {
		for (LegConfiguration legConfig : LegConfiguration.values()) {
			String id = "LEG_CONFIGURATION_"+legConfig;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().getLegType().applyLegConfigurationTransformation(BodyChanging.getTarget(), legConfig, true, false);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initFootStructureListeners() {
		for (FootStructure footStructure : FootStructure.values()) {
			String id = "FOOT_STRUCTURE_"+footStructure;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFootStructure(footStructure);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Penis
	public static void initPenisTypeListeners() {
		for (AbstractPenisType penisType : PenisType.getAllPenisTypes()) {
			String id = "PENIS_"+PenisType.getIdFromPenisType(penisType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setPenisType(penisType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initPenisGirthListeners() {
		for (PenetrationGirth girth : PenetrationGirth.values()) {
			String id = "PENIS_GIRTH_"+girth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setPenisGirth(girth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initPenisModifierListeners() {
		for (PenetrationModifier penMod : PenetrationModifier.getPenetrationModifiers(SexAreaPenetration.PENIS)) {
			String id = "PENIS_MOD_"+penMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasPenisModifier(penMod)) {
						BodyChanging.getTarget().removePenisModifier(penMod);
					} else {
						BodyChanging.getTarget().addPenisModifier(penMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(penMod.getName()),
						(penMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+penMod.getDescription()));
			}
		}
	}
	
	public static void initPenisSizeListeners(boolean limitedSelection) {
		String id;
		if (limitedSelection) {
			for (int ps : CharacterModificationUtils.getPenisSizesAvailable()) {
				id = "PENIS_SIZE_"+ps;
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						BodyChanging.getTarget().setPenisSize(ps);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
		} else {
			id = "PENIS_SIZE_INCREASE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementPenisSize(1);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PENIS_SIZE_INCREASE_LARGE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementPenisSize(5);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PENIS_SIZE_DECREASE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementPenisSize(-1);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PENIS_SIZE_DECREASE_LARGE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementPenisSize(-5);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initPenisCumExpulsionListeners() {
		String id = "CUM_EXPULSION_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumExpulsion(1);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_EXPULSION_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumExpulsion(10);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_EXPULSION_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumExpulsion(-1);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_EXPULSION_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumExpulsion(-10);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initPenisCumProductionListeners() {
		String id = "CUM_PRODUCTION_INCREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setPenisCumStorage(Math.min(CharacterModificationUtils.getCumUpperLimit(), BodyChanging.getTarget().getPenisRawCumStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_SMALL));
				BodyChanging.getTarget().fillCumToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_PRODUCTION_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setPenisCumStorage(Math.min(CharacterModificationUtils.getCumUpperLimit(), BodyChanging.getTarget().getPenisRawCumStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_AVERAGE));
				BodyChanging.getTarget().fillCumToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_PRODUCTION_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setPenisCumStorage(Math.min(CharacterModificationUtils.getCumUpperLimit(), BodyChanging.getTarget().getPenisRawCumStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_LARGE));
				BodyChanging.getTarget().fillCumToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_PRODUCTION_INCREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setPenisCumStorage(Math.min(CharacterModificationUtils.getCumUpperLimit(), BodyChanging.getTarget().getPenisRawCumStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_HUGE));
				BodyChanging.getTarget().fillCumToMaxStorage();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_PRODUCTION_DECREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_PRODUCTION_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_PRODUCTION_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_PRODUCTION_DECREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initPenisCumRegenListeners() {
		String id = "CUM_REGENERATION_INCREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumProductionRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_REGENERATION_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumProductionRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_REGENERATION_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumProductionRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_REGENERATION_INCREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumProductionRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_REGENERATION_DECREASE_SMALL";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumProductionRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_REGENERATION_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumProductionRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_REGENERATION_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumProductionRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "CUM_REGENERATION_DECREASE_HUGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementPenisCumProductionRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_HUGE);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initPenisCumFlavourListeners() {
		for (FluidFlavour flavour : FluidFlavour.values()) {
			String id = "CUM_FLAVOUR_"+flavour;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setCumFlavour(flavour);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initPenisCumModifierListeners() {
		for (FluidModifier modifier : FluidModifier.values()) {
			String id = "CUM_MODIFIER_"+modifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasCumModifier(modifier)) {
						BodyChanging.getTarget().removeCumModifier(modifier);
					} else {
						BodyChanging.getTarget().addCumModifier(modifier);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(modifier.getName()),
						(modifier.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+modifier.getDescription()));
			}
		}
	}
	
	public static void initTesticleCountListeners() {
		for (int i = Testicle.MIN_TESTICLE_COUNT; i<=Testicle.MAX_TESTICLE_COUNT; i += 2) {
			String id = "TESTICLE_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setTesticleCount(finalI);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initTesticleInternalListeners() {
		String id = "TESTICLES_INTERNAL_ON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setInternalTesticles(true);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "TESTICLES_INTERNAL_OFF";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setInternalTesticles(false);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initTesticleSizeListeners(boolean limitedSelection) {
		TesticleSize[] sizes;
		if (limitedSelection) {
			sizes = CharacterModificationUtils.getTesticleSizesAvailable();
		} else {
			sizes = TesticleSize.values();
		}
		for (TesticleSize ts : sizes) {
			String id = "TESTICLE_SIZE_"+ts;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setTesticleSize(ts.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initUrethraCapacityListeners() {
		for (Capacity capacity : Capacity.getCapacityListFromPreferences()) {
			String id = "URETHRA_CAPACITY_"+capacity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setPenisCapacity(capacity.getMedianValue(), true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initUrethraDepthListeners() {
		for (OrificeDepth depth : OrificeDepth.values()) {
			String id = "URETHRA_DEPTH_"+depth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setUrethraDepth(depth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initUrethraElasticityListeners() {
		for (OrificeElasticity elasticity : OrificeElasticity.values()) {
			String id = "URETHRA_ELASTICITY_"+elasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setUrethraElasticity(elasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initUrethraModifierListeners() {
		for (OrificeModifier orificeMod : OrificeModifier.values()) {
			String id = "URETHRA_MOD_"+orificeMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasUrethraOrificeModifier(orificeMod)) {
						BodyChanging.getTarget().removeUrethraOrificeModifier(orificeMod);
					} else {
						BodyChanging.getTarget().addUrethraOrificeModifier(orificeMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(orificeMod.getName()),
						(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
			}
		}
	}
	
	public static void initUrethraPlasticityListeners() {
		for (OrificePlasticity plasticity : OrificePlasticity.values()) {
			String id = "URETHRA_PLASTICITY_"+plasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setUrethraPlasticity(plasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Spinneret
	public static void initSpinneretCapacityListeners() {
		for (Capacity capacity : Capacity.getCapacityListFromPreferences()) {
			String id = "SPINNERET_CAPACITY_"+capacity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setSpinneretCapacity(capacity.getMedianValue(), true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initSpinneretDepthListeners() {
		for (OrificeDepth depth : OrificeDepth.values()) {
			String id = "SPINNERET_DEPTH_"+depth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setSpinneretDepth(depth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initSpinneretElasticityListeners() {
		for (OrificeElasticity elasticity : OrificeElasticity.values()) {
			String id = "SPINNERET_ELASTICITY_"+elasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setSpinneretElasticity(elasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initSpinneretModifierListeners() {
		for (OrificeModifier orificeMod : OrificeModifier.values()) {
			String id = "SPINNERET_MOD_"+orificeMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasSpinneretOrificeModifier(orificeMod)) {
						BodyChanging.getTarget().removeSpinneretOrificeModifier(orificeMod);
					} else {
						BodyChanging.getTarget().addSpinneretOrificeModifier(orificeMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(orificeMod.getName()),
						(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
			}
		}
	}
	
	public static void initSpinneretPlasticityListeners() {
		for (OrificePlasticity plasticity : OrificePlasticity.values()) {
			String id = "SPINNERET_PLASTICITY_"+plasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setSpinneretPlasticity(plasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initSpinneretWetnessListeners() {
		for (Wetness wetness : Wetness.values()) {
			String id = "SPINNERET_WETNESS_"+wetness;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setSpinneretWetness(wetness.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Tail
	public static void initTailTypeListeners() {
		for (AbstractTailType tailType : TailType.getAllTailTypes()) {
			String id = "TAIL_"+TailType.getIdFromTailType(tailType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setTailType(tailType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initTailCountListeners() {
		for (int i = 1; i<=Tail.MAXIMUM_COUNT; i++) {
			String id = "TAIL_COUNT_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setTailCount(finalI, BodyChanging.isDebugMenu());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initTailGirthListeners() {
		for (PenetrationGirth girth : PenetrationGirth.values()) {
			String id = "TAIL_GIRTH_"+girth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setTailGirth(girth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initTailLengthListeners() {
		String id = "TAIL_LENGTH_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if (BodyChanging.getTarget().getLegConfiguration() == LegConfiguration.TAIL_LONG) {
					BodyChanging.getTarget().incrementLegTailLengthAsPercentageOfHeight(0.05f);
				} else {
					BodyChanging.getTarget().incrementTailLengthAsPercentageOfHeight(0.05f);
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "TAIL_LENGTH_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if (BodyChanging.getTarget().getLegConfiguration() == LegConfiguration.TAIL_LONG) {
					BodyChanging.getTarget().incrementLegTailLengthAsPercentageOfHeight(0.25f);
				} else {
					BodyChanging.getTarget().incrementTailLengthAsPercentageOfHeight(0.25f);
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "TAIL_LENGTH_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if (BodyChanging.getTarget().getLegConfiguration() == LegConfiguration.TAIL_LONG) {
					BodyChanging.getTarget().incrementLegTailLengthAsPercentageOfHeight(-0.05f);
				} else {
					BodyChanging.getTarget().incrementTailLengthAsPercentageOfHeight(-0.05f);
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "TAIL_LENGTH_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if (BodyChanging.getTarget().getLegConfiguration() == LegConfiguration.TAIL_LONG) {
					BodyChanging.getTarget().incrementLegTailLengthAsPercentageOfHeight(-0.25f);
				} else {
					BodyChanging.getTarget().incrementTailLengthAsPercentageOfHeight(-0.25f);
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	// Tentacle
	public static void initTentacleGirthListeners() {
		for (PenetrationGirth girth : PenetrationGirth.values()) {
			String id = "TENTACLE_GIRTH_"+girth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setTentacleGirth(girth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initTentacleLengthListeners() {
		String id = "TENTACLE_LENGTH_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementTentacleLengthAsPercentageOfHeight(0.05f);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "TENTACLE_LENGTH_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementTentacleLengthAsPercentageOfHeight(0.25f);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "TENTACLE_LENGTH_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementTentacleLengthAsPercentageOfHeight(-0.05f);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "TENTACLE_LENGTH_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementTentacleLengthAsPercentageOfHeight(-0.25f);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	// Torso
	public static void initTorsoTypeListeners() {
		for (AbstractTorsoType torsoType : TorsoType.getAllTorsoTypes()) {
			String id = "TORSO_"+TorsoType.getIdFromTorsoType(torsoType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setTorsoType(torsoType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Vagina
	public static void initVaginaTypeListeners() {
		for (AbstractVaginaType vaginaType : VaginaType.getAllVaginaTypes()) {
			String id = "VAGINA_"+VaginaType.getIdFromVaginaType(vaginaType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaType(vaginaType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				if (vaginaType == VaginaType.NONE
						&& (BodyChanging.getTarget().isPregnant()
						|| BodyChanging.getTarget().hasStatusEffect(StatusEffect.PREGNANT_0)
						|| BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.VAGINA) != null)) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"[style.colourBad(Vagina Removal Blocked)]",
							UtilText.parse(BodyChanging.getTarget(),
									BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.VAGINA) != null
											?"[npc.NamePos] vagina cannot be removed while eggs are being incubated in [npc.namePos] womb!"
											:(BodyChanging.getTarget().hasStatusEffect(StatusEffect.PREGNANT_0)
											?"[npc.NamePos] vagina cannot be removed while there is a chance that [npc.name] might be pregnant!"
											:"[npc.NamePos] vagina cannot be removed while [npc.nameIsFull] pregnant!")),
							32));
				}
			}
		}
	}
	
	public static void initVaginaCapacityListeners() {
		for (Capacity capacity : Capacity.getCapacityListFromPreferences()) {
			String id = "VAGINA_CAPACITY_"+capacity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaCapacity(capacity.getMedianValue(), true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaCumFlavourListeners() {
		for (FluidFlavour flavour : FluidFlavour.values()) {
			String id = "GIRLCUM_FLAVOUR_"+flavour;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setGirlcumFlavour(flavour);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaCumModifierListeners() {
		for (FluidModifier modifier : FluidModifier.values()) {
			String id = "GIRLCUM_MODIFIER_"+modifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasGirlcumModifier(modifier)) {
						BodyChanging.getTarget().removeGirlcumModifier(modifier);
					} else {
						BodyChanging.getTarget().addGirlcumModifier(modifier);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(modifier.getName()),
						(modifier.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+modifier.getDescription()));
			}
		}
	}
	
	public static void initVaginaDepthListeners() {
		for (OrificeDepth depth : OrificeDepth.values()) {
			String id = "VAGINA_DEPTH_"+depth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaDepth(depth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaEggLayerListeners() {
		String id = "VAGINA_EGG_LAYER_ON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if (!BodyChanging.getTarget().isPregnant()) {
					BodyChanging.getTarget().setVaginaEggLayer(true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			}, false);
		}
		id = "VAGINA_EGG_LAYER_OFF";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if (!BodyChanging.getTarget().isPregnant()) {
					BodyChanging.getTarget().setVaginaEggLayer(false);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			}, false);
		}
	}
	
	public static void initVaginaElasticityListeners() {
		for (OrificeElasticity elasticity : OrificeElasticity.values()) {
			String id = "VAGINA_ELASTICITY_"+elasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaElasticity(elasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaHymenListeners() {
		String id = "VAGINA_HYMEN_ON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setHymen(true);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "VAGINA_HYMEN_OFF";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setHymen(false);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initVaginaModifierListeners() {
		for (OrificeModifier orificeMod : OrificeModifier.values()) {
			String id = "VAGINA_MOD_"+orificeMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasVaginaOrificeModifier(orificeMod)) {
						BodyChanging.getTarget().removeVaginaOrificeModifier(orificeMod);
					} else {
						BodyChanging.getTarget().addVaginaOrificeModifier(orificeMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(orificeMod.getName()),
						(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
			}
		}
	}
	
	public static void initVaginaPlasticityListeners() {
		for (OrificePlasticity plasticity : OrificePlasticity.values()) {
			String id = "VAGINA_PLASTICITY_"+plasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaPlasticity(plasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaSquinterListeners() {
		String id = "VAGINA_SQUIRTER_ON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setVaginaSquirter(true);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "VAGINA_SQUIRTER_OFF";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setVaginaSquirter(false);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initVaginaWetnessListeners() {
		for (Wetness wetness : Wetness.values()) {
			String id = "VAGINA_WETNESS_"+wetness;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaWetness(wetness.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initClitorisGirthListeners() {
		for (PenetrationGirth girth : PenetrationGirth.values()) {
			String id = "CLITORIS_GIRTH_"+girth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setClitorisGirth(girth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initClitorisModifierListeners() {
		for (PenetrationModifier penMod : PenetrationModifier.getPenetrationModifiers(SexAreaPenetration.CLIT)) {
			String id = "CLITORIS_MOD_"+penMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasClitorisModifier(penMod)) {
						BodyChanging.getTarget().removeClitorisModifier(penMod);
					} else {
						BodyChanging.getTarget().addClitorisModifier(penMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(penMod.getName()),
						(penMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+penMod.getDescription()));
			}
		}
	}
	
	public static void initClitorisSizeListeners(boolean limitedSelection) {
		String id;
		if (limitedSelection) {
			for (ClitorisSize cs : CharacterModificationUtils.getClitorisSizesAvailable()) {
				id = "CLITORIS_SIZE_"+cs;
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						BodyChanging.getTarget().setVaginaClitorisSize(cs.getMedianValue());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
		} else {
			id = "CLITORIS_SIZE_INCREASE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementVaginaClitorisSize(1);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "CLITORIS_SIZE_INCREASE_LARGE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementVaginaClitorisSize(5);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "CLITORIS_SIZE_DECREASE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementVaginaClitorisSize(-1);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "CLITORIS_SIZE_DECREASE_LARGE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().incrementVaginaClitorisSize(-5);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initLabiaSizeListeners() {
		for (LabiaSize ls : LabiaSize.values()) {
			String id = "LABIA_SIZE_"+ls;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaLabiaSize(ls.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaUrethraCapacityListeners() {
		for (Capacity capacity : Capacity.getCapacityListFromPreferences()) {
			String id = "VAGINA_URETHRA_CAPACITY_"+capacity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaUrethraCapacity(capacity.getMedianValue(), true);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaUrethraDepthListeners() {
		for (OrificeDepth depth : OrificeDepth.values()) {
			String id = "VAGINA_URETHRA_DEPTH_"+depth;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaUrethraDepth(depth.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaUrethraElasticityListeners() {
		for (OrificeElasticity elasticity : OrificeElasticity.values()) {
			String id = "VAGINA_URETHRA_ELASTICITY_"+elasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaUrethraElasticity(elasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initVaginaUrethraModifierListeners() {
		for (OrificeModifier orificeMod : OrificeModifier.values()) {
			String id = "VAGINA_URETHRA_MOD_"+orificeMod;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (BodyChanging.getTarget().hasVaginaUrethraOrificeModifier(orificeMod)) {
						BodyChanging.getTarget().removeVaginaUrethraOrificeModifier(orificeMod);
					} else {
						BodyChanging.getTarget().addVaginaUrethraOrificeModifier(orificeMod);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(orificeMod.getName()),
						(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription()));
			}
		}
	}
	
	public static void initVaginaUrethraPlasticityListeners() {
		for (OrificePlasticity plasticity : OrificePlasticity.values()) {
			String id = "VAGINA_URETHRA_PLASTICITY_"+plasticity;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setVaginaUrethraPlasticity(plasticity.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Wing
	public static void initWingTypeListeners() {
		for (AbstractWingType wingType : WingType.getAllWingTypes()) {
			String id = "WING_"+WingType.getIdFromWingType(wingType);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setWingType(wingType);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initWingSizeListeners() {
		for (WingSize wingSize : WingSize.values()) {
			String id = "WING_SIZE_"+wingSize;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setWingSize(wingSize.getValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Prologue & Slave Character Creation
	public static void initAffectionListeners() {
		for (AffectionLevel affection : AffectionLevel.values()) {
			String id = "AFFECTION_LEVEL_"+affection;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setAffection(Main.game.getPlayer(), affection.getMedianValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initAgeListeners() {
		String id = "AGE_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusYears(1));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "AGE_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusYears(5));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "AGE_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(1));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "AGE_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(5));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initAgeAppearanceListeners() {
		String id = "AGE_APPEARANCE_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setAgeAppearanceDifferenceToAppearAsAge(Math.max(18, Math.min(BodyChanging.getTarget().getAppearsAsAgeValue()+1, BodyChanging.getTarget().getAgeValue()+10)));
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "AGE_APPEARANCE_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setAgeAppearanceDifferenceToAppearAsAge(Math.max(18, Math.min(BodyChanging.getTarget().getAppearsAsAgeValue()+5, BodyChanging.getTarget().getAgeValue()+10)));
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "AGE_APPEARANCE_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setAgeAppearanceDifferenceToAppearAsAge(Math.max(18, Math.min(BodyChanging.getTarget().getAppearsAsAgeValue()-1, BodyChanging.getTarget().getAgeValue()+10)));
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "AGE_APPEARANCE_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setAgeAppearanceDifferenceToAppearAsAge(Math.max(18, Math.min(BodyChanging.getTarget().getAppearsAsAgeValue()-5, BodyChanging.getTarget().getAgeValue()+10)));
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initBackgroundSelectionListeners() {
		for (Occupation history : Occupation.values()) {
			String id = "OCCUPATION_"+history;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event->{
					Main.game.getPlayer().setHistory(history);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setPerk(
						history.getAssociatedPerk(), Main.game.getPlayer()));
			}
		}
	}
	
	public static void initBirthdayListeners() {
		String id;
		for (Month month : Month.values()) {
			id = "STARTING_MONTH_"+month;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setStartingDateMonth(month);
					CharacterModificationUtils.performAgeCheck();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
		
		// Birthday:
		id = "BIRTH_DAY_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusDays(1));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BIRTH_DAY_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusDays(5));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BIRTH_DAY_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusDays(1));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BIRTH_DAY_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusDays(5));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		
		// Birth month:
		id = "BIRTH_MONTH_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusMonths(1));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BIRTH_MONTH_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusMonths(5));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BIRTH_MONTH_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusMonths(1));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BIRTH_MONTH_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusMonths(5));
				CharacterModificationUtils.performAgeCheck();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initFemininityListeners() {
		String id = "FEMININITY_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementFemininity(1);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "FEMININITY_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementFemininity(5);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "FEMININITY_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementFemininity(-1);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "FEMININITY_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().incrementFemininity(-5);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initFemininityPresetListeners() {
		String id = "CHOOSE_FEM_ANDROGYNOUS";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity());
				if (!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
					CharacterCreation.getDressed();
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		if (Main.game.getPlayer().hasPenis()) {
			id = "CHOOSE_FEM_MASCULINE_STRONG";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFemininity(Femininity.MASCULINE_STRONG.getMedianFemininity());
					if (!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
						CharacterCreation.getDressed();
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "CHOOSE_FEM_MASCULINE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFemininity(Femininity.MASCULINE.getMedianFemininity());
					if (!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
						CharacterCreation.getDressed();
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		} else {
			id = "CHOOSE_FEM_FEMININE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFemininity(Femininity.FEMININE.getMedianFemininity());
					if (!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
						CharacterCreation.getDressed();
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "CHOOSE_FEM_FEMININE_STRONG";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setFemininity(Femininity.FEMININE_STRONG.getMedianFemininity());
					if (!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
						CharacterCreation.getDressed();
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initFetishListeners() {
		for (AbstractFetish fetish : Fetish.getAllFetishes()) {
			for (FetishDesire desire : FetishDesire.values()) {
				String id = "FETISH_DESIRE_"+Fetish.getIdFromFetish(fetish)+desire;
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (desire == FetishDesire.FOUR_LOVE) {
							BodyChanging.getTarget().addFetish(fetish);
						} else {
							BodyChanging.getTarget().removeFetish(fetish);
							BodyChanging.getTarget().setFetishDesire(fetish, desire);
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
		}
	}
	
	public static void initGenderListeners() {
		String id = "CHOOSE_GENDER_MALE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				CharacterCreation.setGenderMale();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		
		id = "CHOOSE_GENDER_FEMALE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				CharacterCreation.setGenderFemale();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initLipPuffinessListeners() {
		String id = "LIP_PUFFY_ON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().addFaceOrificeModifier(OrificeModifier.PUFFY);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "LIP_PUFFY_OFF";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().removeFaceOrificeModifier(OrificeModifier.PUFFY);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initNipplePuffinessListeners() {
		String id = "NIPPLE_PUFFY_ON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().addNippleOrificeModifier(OrificeModifier.PUFFY);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "NIPPLE_PUFFY_OFF";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				BodyChanging.getTarget().removeNippleOrificeModifier(OrificeModifier.PUFFY);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initObedienceListeners() {
		for (ObedienceLevel obedience : ObedienceLevel.values()) {
			String id = "OBEDIENCE_LEVEL_"+obedience;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setObedience(obedience.getMedianValue());
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initOrientationListeners() {
		for (SexualOrientation orientation : SexualOrientation.values()) {
			String id = "SEXUAL_ORIENTATION_"+orientation;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					BodyChanging.getTarget().setSexualOrientation(orientation);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initPersonalityListeners() {
		for (PersonalityTrait trait : PersonalityTrait.values()) {
			String id = "PERSONALITY_TRAIT_"+trait;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!BodyChanging.getTarget().hasPersonalityTrait(trait)) {
						BodyChanging.getTarget().addPersonalityTrait(trait);
					} else {
						BodyChanging.getTarget().removePersonalityTrait(trait);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence("<b style='color:"+trait.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(trait.getName())+"</b>"),
						trait.getDescription(BodyChanging.getTarget(), true, true)));
			}
		}
	}
	
	public static void initSexExperienceListeners() {
		Map<String, Integer> mappings = Util.newHashMapOfValues(
				new Value<>("DECREASE_LARGE", -10),
				new Value<>("DECREASE", -1),
				new Value<>("INCREASE", 1),
				new Value<>("INCREASE_LARGE", 10));
		for (Entry<String, Integer> entry : mappings.entrySet()) {
			String i = entry.getKey();
			int j = entry.getValue();
			// Given:
			String id = "HANDJOBS_GIVEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "FINGERINGS_GIVEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "BLOWJOBS_GIVEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "CUNNILINGUS_GIVEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "ANILINGUS_GIVEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "VAGINAL_GIVEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "ANAL_GIVEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			// Received:
			id = "HANDJOBS_TAKEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "FINGERINGS_TAKEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "BLOWJOBS_TAKEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "CUNNILINGUS_TAKEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "ANILINGUS_TAKEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "VAGINAL_TAKEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "ANAL_TAKEN_"+i;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CharacterModificationUtils.incrementSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), j);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	// Tattoo's
	public static void initTattooInfoListeners(DialogueNode currentNode) {
		for (InventorySlot invSlot : InventorySlot.getClothingSlots()) {
			String id = "TATTOO_INFO_"+invSlot;
			if (MainController.document.getElementById(id) != null) {
				if (BodyChanging.getTarget().getTattooInSlot(invSlot) != null) {
					MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setTattoo(
							invSlot,
							BodyChanging.getTarget().getTattooInSlot(invSlot),
							BodyChanging.getTarget(),
							BodyChanging.getTarget()));
				} else {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							Util.capitaliseSentence(invSlot.getTattooSlotName()), ""));
				}
			}
			
			id = "TATTOO_ADD_REMOVE_"+invSlot;
			if (MainController.document.getElementById(id) != null) {
				if (BodyChanging.getTarget().getTattooInSlot(invSlot) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (Main.game.getPlayer().getMoney()>=100 || !Main.game.isInNewWorld()) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									if (SuccubisSecrets.invSlotTattooToRemove == invSlot || !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)) {
										SuccubisSecrets.invSlotTattooToRemove = null;
										if (Main.game.isInNewWorld()) {
											Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-100));
										}
										BodyChanging.getTarget().removeTattoo(invSlot);
									} else {
										SuccubisSecrets.invSlotTattooToRemove = invSlot;
									}
								}
							});
						}
					}, false);
				} else {
					DialogueNode nextDialogue;
					if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS)) {
						nextDialogue = CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD;
					} else if (currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_TATTOOS)) {
						nextDialogue = CompanionManagement.SLAVE_MANAGEMENT_TATTOOS_ADD;
					} else if (currentNode.equals(CosmeticsDialogue.BEAUTICIAN_TATTOOS)) {
						nextDialogue = CosmeticsDialogue.BEAUTICIAN_TATTOOS_ADD;
					} else if (currentNode.equals(SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS)) {
						nextDialogue = SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS_ADD;
					} else {
						throw new NullPointerException("This node doesn't have a nextDialogue assigned");
					}
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", nextDialogue) {
							@Override
							public void effects() {
								SuccubisSecrets.invSlotTattooToRemove = null;
								CharacterModificationUtils.resetTattooVariables(invSlot);
							}
						});
					}, false);
				}
				StringBuilder sb = new StringBuilder();
				boolean confirm = Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations);
				if (Main.game.isInNewWorld()) {
					if (Main.game.getPlayer().getMoney()>=100) {
						sb.append("It will cost "+UtilText.formatAsMoney(100, "span")+" to remove this tattoo!");
					} else {
						sb.append("You don't have the required "+UtilText.formatAsMoney(100, "span")+" to remove this tattoo!");
						confirm = false;
					}
				} else {
					sb.append("Remove this tattoo.");
				}
				if (confirm) {
					sb.append(" (<i>You will need to click twice to remove it.</i>)");
				}
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Remove tattoo", sb.toString()));
			}
			
			id = "TATTOO_ENCHANT_"+invSlot.toString();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", EnchantmentDialogue.ENCHANTMENT_MENU) {
						@Override
						public DialogueNode getNextDialogue() {
							return EnchantmentDialogue.getEnchantmentMenu(BodyChanging.getTarget().getTattooInSlot(invSlot), BodyChanging.getTarget(), invSlot);
						}
					});
				}, false);
			}
		}
	}
	
	public static void initTattooAddListeners() {
		String id = "NEW_TATTOO_INFO";
		if (MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setTattoo(
					CharacterModificationUtils.tattooInventorySlot,
					CharacterModificationUtils.tattoo,
					BodyChanging.getTarget(),
					BodyChanging.getTarget()));
		}
		
		for (AbstractTattooType type : TattooType.getAllTattooTypes()) {
			id = "TATTOO_TYPE_"+type.getId();
			if (MainController.document.getElementById(id) != null) {
				if (type.getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot)) {
					if (!CharacterModificationUtils.tattoo.getType().equals(type)) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.setType(type);
									CharacterModificationUtils.resetTattooColours();
								}
							});
						}, false);
					}
				}
				String description = type.getDescription();
				if (!type.getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot)) {
					description += "<br/>[style.italicsBad(This tattoo type can't be applied to '"+CharacterModificationUtils.tattooInventorySlot.getTattooSlotName()+"'!)]<br/>"
							+"Available slots: "+Util.tattooInventorySlotsToStringList(type.getSlotAvailability());
				}
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(type.getName()), description));
			}
		}
		
		for (Colour c : CharacterModificationUtils.tattoo.getType().getAvailablePrimaryColours()) {
			id = "TATTOO_COLOUR_PRIMARY_"+c.getId();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setPrimaryColour(c);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(c.getName()), ""));
			}
		}
		
		for (Colour c : CharacterModificationUtils.tattoo.getType().getAvailableSecondaryColours()) {
			id = "TATTOO_COLOUR_SECONDARY_"+c.getId();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setSecondaryColour(c);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(c.getName()), ""));
			}
		}
		
		for (Colour c : CharacterModificationUtils.tattoo.getType().getAvailableTertiaryColours()) {
			id = "TATTOO_COLOUR_TERTIARY_"+c.getId();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setTertiaryColour(c);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(c.getName()), ""));
			}
		}
		
		id = "TATTOO_GLOW";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
						CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
						CharacterModificationUtils.tattoo.setGlowing(!CharacterModificationUtils.tattoo.isGlowing());
					}
				});
			}, false);
		}
		
		// Writing:
		for (TattooWritingStyle style : TattooWritingStyle.values()) {
			id = "TATTOO_WRITING_STYLE_"+style.toString();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							if (!CharacterModificationUtils.tattoo.getWriting().getStyles().contains(style)) {
								CharacterModificationUtils.tattoo.getWriting().addStyle(style);
							} else {
								CharacterModificationUtils.tattoo.getWriting().removeStyle(style);
							}
						}
					});
				}, false);
			}
		}
		
		for (Colour c : TattooWriting.getAvailableColours()) {
			id = "TATTOO_WRITING_COLOUR_"+c.getId();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.getWriting().setColour(c);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(c.getName()), ""));
			}
		}
		
		id = "TATTOO_WRITING_GLOW";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
						CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
						CharacterModificationUtils.tattoo.getWriting().setGlow(!CharacterModificationUtils.tattoo.getWriting().isGlow());
					}
				});
			}, false);
		}
		
		// Counter:
		for (TattooCounterType counterType : TattooCounterType.values()) {
			id = "TATTOO_COUNTER_TYPE_"+counterType.toString();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.getCounter().setType(counterType);
						}
					});
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(counterType.getName()), counterType.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
		}
		
		for (TattooCountType countType : TattooCountType.values()) {
			id = "TATTOO_COUNT_TYPE_"+countType.toString();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.getCounter().setCountType(countType);
						}
					});
				}, false);
			}
		}
		
		for (Colour c : TattooCounter.getAvailableColours()) {
			id = "TATTOO_COUNTER_COLOUR_"+c.getId();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.getCounter().setColour(c);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(c.getName()), ""));
			}
		}
		
		id = "TATTOO_COUNTER_GLOW";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
						CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
						CharacterModificationUtils.tattoo.getCounter().setGlow(!CharacterModificationUtils.tattoo.getCounter().isGlow());
					}
				});
			}, false);
		}
	}
}
