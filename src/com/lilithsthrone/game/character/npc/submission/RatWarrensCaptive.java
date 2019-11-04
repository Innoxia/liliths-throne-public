package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
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
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
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
			
			//TODO set sex had
			this.setSexAsSubCount("", 100+Util.random.nextInt(200));
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}
		
//		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
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
		
		// Milking:
		this.setBreastSize(CupSize.DD.getMeasurement()+Util.random.nextInt(6));
		this.setMilkFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()));
		this.setBreastMilkStorage(Lactation.SIX_EXTREME_AMOUNT_DRIPPING.getMedianValue()+Util.random.nextInt(Lactation.SIX_EXTREME_AMOUNT_DRIPPING.getMedianValue()));
		this.setBreastLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()+Util.random.nextInt(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
		
		// From sex:
		this.setVaginaCapacity(Util.randomItemFrom(new Capacity[] {Capacity.THREE_SLIGHTLY_LOOSE, Capacity.FOUR_LOOSE, Capacity.FIVE_ROOMY, Capacity.SIX_STRETCHED_OPEN}), true);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.SIX_MALLEABLE.getValue());
		
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.clearNonEquippedInventory(false);
		
		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			//Tattoo of milk flavour:
			String name = "Flavour: "+this.getMilkFlavour().getName();
			this.addTattoo(InventorySlot.CHEST, new Tattoo(TattooType.NONE, Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK, false, new TattooWriting(name, Colour.CLOTHING_BLACK, false), null));
			
			// Tattoo for times fucked:
			this.addTattoo(InventorySlot.TORSO_UNDER, new Tattoo(TattooType.NONE, Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK, false,
					new TattooWriting("Times fucked", Colour.CLOTHING_BLACK, false),
					new TattooCounter(TattooCounterType.SEX_SUB, TattooCountType.TALLY, Colour.BASE_BLACK, false)));
		}
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_STEEL, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_SPREADER_BAR, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_WRIST_RESTRAINTS, Colour.CLOTHING_BLACK, false), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return false;
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
