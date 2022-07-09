package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.9
 * @author Innoxia
 */
public class RatWarrensCaptive extends NPC {

	public RatWarrensCaptive() {
		this(Gender.getGenderFromUserPreferences(Femininity.FEMININE), false);
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

			Main.game.getCharacterUtils().randomiseBody(this, true);
			setStartingBody(false);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(0);
	
//			Main.game.getCharacterUtils().applyMakeup(this, true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			initPerkTreeAndBackgroundPerks();
			this.removePersonalityTrait(PersonalityTrait.MUTE);
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50+Util.random.nextInt(26));
			
			this.setStartingCombatMoves();
			
			initHealthAndManaToMax();
		}
		
//		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 50+Util.random.nextInt(26));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.9")) {
			this.removePersonalityTrait(PersonalityTrait.MUTE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9")) {
			setStartingBody(true);
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.3.3")) {
			this.resetPerksMap(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.AHEGAO),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC); // Just to make player easier to handle
			
			this.setHistory(Occupation.NPC_CAPTIVE);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING);
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_LACTATION_SELF);

			this.addFetish(Fetish.FETISH_SIZE_QUEEN);
			this.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
		}

		this.setFaceVirgin(false);
		this.setAssVirgin(false);
		this.setVaginaVirgin(false);
		
		// Milking:
		this.setBreastSize(CupSize.FF.getMeasurement()+Util.random.nextInt(10));
		this.setNippleSize(NippleSize.FOUR_MASSIVE);
		this.setAreolaeSize(AreolaeSize.FOUR_MASSIVE);
		this.setNippleCapacity(0, true);
		
		this.setMilkFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()));
		this.setBreastMilkStorage(Lactation.SIX_EXTREME_AMOUNT_DRIPPING.getMedianValue()+Util.random.nextInt(Lactation.SIX_EXTREME_AMOUNT_DRIPPING.getMedianValue()));
		this.setBreastLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()+Util.random.nextInt(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));

		if(this.getCovering(BodyCoveringType.NIPPLES).getPrimaryColour().getDarkerLinkedColours().contains(PresetColour.SKIN_DARK)) {
			this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_DARK), false);
		}
		
		// Anus:
		if(Main.game.isAnalContentEnabled()) {
			this.addAssOrificeModifier(OrificeModifier.PUFFY);
			this.setAssWetness(Wetness.FIVE_SLOPPY);
			this.setAssDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
			this.setAssCapacity(Penis.getGenericDiameter(38, PenetrationGirth.SEVEN_FAT), true);
			this.setAssElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue());
			this.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue());
			if(Main.game.isAssHairEnabled()) {
				this.setPubicHair(BodyHair.FOUR_NATURAL);
			}
			if(this.getCovering(BodyCoveringType.ANUS).getPrimaryColour().getDarkerLinkedColours().contains(PresetColour.SKIN_DARK)) {
				this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_DARK), false);
			}
		}

		// Vagina:
		this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
		
		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
		this.setGirlcumFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()));
		this.addGirlcumModifier(FluidModifier.MUSKY);
		this.setVaginaSquirter(true);
		
		this.setVaginaDepth(OrificeDepth.FIVE_VERY_DEEP.getValue());
		this.setVaginaElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue());
		this.setVaginaPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue());
		this.setVaginaCapacity(Penis.getGenericDiameter(38, PenetrationGirth.SEVEN_FAT), true);
		
		if(Main.game.isPubicHairEnabled()) {
			this.setPubicHair(BodyHair.FOUR_NATURAL);
		}
		if(this.getCovering(BodyCoveringType.VAGINA).getPrimaryColour().getDarkerLinkedColours().contains(PresetColour.SKIN_DARK)) {
			this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_DARK), false);
		}
	}

	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		if(this.getHomeWorldLocation()==WorldType.RAT_WARRENS) {
			sb.append("This woman is one of Murk's 'milkers', and is whored out to anyone who can afford her."
					+ " While not being fucked by Murk or some stranger, this 'milker' earns her name by having her breasts milked by an arcane-powered machine.");
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensMilkersBackground)) {
				sb.append("<br/>"
						+ "After asking Murk about the milkers, you discovered that they were originally kidnapped and forced to be milked against their will."
						+ " Over time, however, the rat-boy totally broke their will to resist, to the point where they now desperately plead to get fucked by him...");
			}
			
		} else {
			sb.append(UtilText.parse(this,
					"This [npc.race] used to be one of Murk's 'milkers', and was whored out to anyone who can afford [npc.herHim]."
						+ " While not being fucked by Murk or some stranger, this 'milker' used to earn [npc.her] name by having [npc.her] breasts milked by an arcane-powered machine."
						+ "<br/>"
						+ "Claire informed you that [npc.she] was originally kidnapped by Murk and forced to be milked against [npc.her] will."
						+ " Over time, however, the rat-boy totally broke [npc.her] will to resist,"
							+ " to the point where [npc.she] was so desperate to continue being milked and fucked that the Enforcers had no other choice but to enslave [npc.herHim] to give [npc.herHim] the life [npc.she] craved."
						+ "<br/>"
						+ "Having agreed to take care of [npc.herHim], Claire signed possession of this slave over to you."
						+ " It's now completely up to you whether you subject [npc.herHim] to the abusive treatment [npc.she] craves, or to instead choose to give [npc.herHim] a different type of life..."));
		}
		return sb.toString();
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.clearNonEquippedInventory(false);
		this.clearTattoosAndScars();
		
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_GUNMETAL, false);
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		this.equipClothingFromNowhere(collar, true, this);
		
		applyMilkingEquipment(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
	}
	
	
	public void applyMilkingEquipment(boolean equip, List<InventorySlot> slots) {
		if(equip) {
			if(slots.contains(InventorySlot.NIPPLE)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, this);
			}
			if(slots.contains(InventorySlot.VAGINA)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, this);
			}
			
		} else {
			for(AbstractClothing c : new ArrayList<>(this.getClothingCurrentlyEquipped())) {
				if(c.isMilkingEquipment() && slots.contains(c.getSlotEquippedTo())) {
					this.unequipClothingIntoVoid(c, true, this);
				}
			}
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public void hourlyUpdate() {
		if(this.getHomeWorldLocation()==WorldType.RAT_WARRENS) {
			// If the player is not a captive, and Murk has not been enslaved, then keep rolling for sex effects:
			if(!Main.game.getPlayer().isCaptive() && !Main.game.getNpc(Murk.class).isSlave()) {
				float rnd = (float) Math.random();
				if(rnd<0.005f && Main.game.isAnalContentEnabled()) { // Average fucked once every week
					this.ingestFluid(null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new FluidCum(FluidType.CUM_RAT_MORPH), SexAreaOrifice.ANUS, 20+Util.random.nextInt(100));
					
				} else if(rnd<0.05f) { // Average fucked once or twice a day
					this.ingestFluid(null, Subspecies.RAT_MORPH, Subspecies.RAT_MORPH, new FluidCum(FluidType.CUM_RAT_MORPH), SexAreaOrifice.VAGINA, 20+Util.random.nextInt(100));
				}
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
