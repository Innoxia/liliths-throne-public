package com.lilithsthrone.game.character.npc.submission;
import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RatWarrensCaptive extends NPC {

	public RatWarrensCaptive() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public RatWarrensCaptive(Gender gender) {
		this(gender, false);
	}
	
	public RatWarrensCaptive(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public RatWarrensCaptive(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5,
				gender, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM, false);

		if(!isImported) {
			// RACE:
			
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			this.setGenericName("captive");

			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(0);
	
//			CharacterUtils.applyMakeup(this, true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());


			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50+Util.random.nextInt(26));
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
			
		}
		
//		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
			this.setVaginaCapacity(32*PenisGirth.FOUR_FAT.getOrificeStretchFactor(), true);
			this.setAssCapacity(32*PenisGirth.FOUR_FAT.getOrificeStretchFactor(), true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50+Util.random.nextInt(26));
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC); // Just to make player easier to handle
			
			this.setHistory(Occupation.NPC_CAPTIVE);
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_LACTATION_SELF);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_PREGNANCY);

			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.THREE_LIKE);
		}

		this.setFaceVirgin(false);
		this.setAssVirgin(false);
		this.setVaginaVirgin(false);
		
		// Milking:
		this.setBreastSize(CupSize.DD.getMeasurement()+Util.random.nextInt(6));
		this.setMilkFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()));
		this.setBreastMilkStorage(Lactation.SIX_EXTREME_AMOUNT_DRIPPING.getMedianValue()+Util.random.nextInt(Lactation.SIX_EXTREME_AMOUNT_DRIPPING.getMedianValue()));
		this.setBreastLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()+Util.random.nextInt(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
		
		// From anal sex:
		if(Main.game.isAnalContentEnabled()) {
			this.setAssCapacity(32*PenisGirth.FOUR_FAT.getOrificeStretchFactor(), true);
			this.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
			this.setAssPlasticity(OrificePlasticity.SIX_MALLEABLE.getValue());
		}

		// From vaginal sex:
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
		this.setVaginaCapacity(32*PenisGirth.FOUR_FAT.getOrificeStretchFactor(), true);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.SIX_MALLEABLE.getValue());
		
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.clearNonEquippedInventory(false);
		this.clearTattoosAndScars();
		
		AbstractClothing collar = AbstractClothingType.generateClothing("innoxia_bdsm_metal_collar", Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_STEEL, Colour.CLOTHING_GUNMETAL, false);
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		this.equipClothingFromNowhere(collar, true, this);
	}
	
	public void applyDilos(boolean equip) {
		if(equip) {
			AbstractClothing dildo = AbstractClothingType.generateClothing("norin_dildos_realistic_dildo", Colour.CLOTHING_PINK_HOT, false);
			dildo.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_VIBRATION, TFPotency.MAJOR_BOOST, 0));
			this.equipClothingFromNowhere(dildo, InventorySlot.VAGINA, true, this);
			
			dildo = AbstractClothingType.generateClothing("norin_dildos_realistic_dildo", Colour.CLOTHING_PINK_HOT, false);
			dildo.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_VIBRATION, TFPotency.MAJOR_BOOST, 0));
			this.equipClothingFromNowhere(dildo, InventorySlot.ANUS, true, this);
			
		} else {
			if(this.getClothingInSlot(InventorySlot.VAGINA)!=null) {
				this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.VAGINA), true, Main.game.getNpc(Murk.class));
			}
			if(this.getClothingInSlot(InventorySlot.ANUS)!=null) {
				this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.ANUS), true, Main.game.getNpc(Murk.class));
			}
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void hourlyUpdate() {
		// If the player is not a captive, and Murk has not been enslaved, then keep rolling for sex effects:
		if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerCaptive) && !Main.game.getNpc(Murk.class).isSlave()) {
			float rnd = (float) Math.random();
			if(rnd<0.1f && Main.game.isAnalContentEnabled()) {
				this.ingestFluid(null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new FluidCum(FluidType.CUM_RAT_MORPH), SexAreaOrifice.ANUS, 20+Util.random.nextInt(100));
				
			} else if(rnd<0.5f) {
				this.ingestFluid(null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new FluidCum(FluidType.CUM_RAT_MORPH), SexAreaOrifice.VAGINA, 20+Util.random.nextInt(100));
			}
		}
	}
	
	@Override
	public void endSex() {
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

}
