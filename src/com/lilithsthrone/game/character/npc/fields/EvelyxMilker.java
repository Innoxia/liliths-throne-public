package com.lilithsthrone.game.character.npc.fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.pregnancy.FertilisationType;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Innoxia
 */
public class EvelyxMilker extends RandomNPC {

	public EvelyxMilker(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public EvelyxMilker(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(1 + Util.random.nextInt(20));
		this.setGenderIdentity(Gender.getGenderFromUserPreferences(Femininity.FEMININE));
		
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if(s.getSubspeciesOverridePriority()>0 || s==Subspecies.SLIME) { // Do not spawn demonic races, elementals, or youko (or slimes)
				continue;
			}
			if(Subspecies.getWorldSpecies(WorldType.WORLD_MAP, PlaceType.WORLD_MAP_FIELDS, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (10000 * Subspecies.getWorldSpecies(WorldType.WORLD_MAP, PlaceType.WORLD_MAP_FIELDS, false).get(s).getChanceMultiplier()), getGenderIdentity(), s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupNPC(subspeciesMap,
				null,
				null,
				true,
				true,
				true,
				false,
				true,
				true,
				false,
				generationFlags);
		
		// Post-setup
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		this.removePersonalityTrait(PersonalityTrait.MUTE); // Don't want mutes in cow sex as they have dialogue
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		// Set in init milker
	}
	
	// Special methods:
	
	public void initMilkerLevel(int level) {
		// Set name:
		if(level==0) {
			this.setGenericName("trainee");
			this.setDescription("This [npc.race] is an employee of Evelyx's Dairy, and is currently classed at the lowest promotional level, 'trainee'."
					+ " She will only be eligible for promotion after she's met certain milk production requirements, at which time she'll have the option to get fucked by a member of the public and be elevated to the position of 'heifer'.");
		} else if(level==1) {
			this.setGenericName("heifer");
			this.setDescription("This [npc.race] is an employee of Evelyx's Dairy, and is currently classed at the middle promotional level, 'heifer'."
					+ " She will only be eligible for promotion after she's been impregnated at least once and met certain milk production requirements, after which she will be elevated to the position of 'cow'.");
		} else {
			this.setGenericName("cow");
			this.setDescription("This [npc.race] is an employee of Evelyx's Dairy, and is currently classed at the highest promotional level, 'cow'."
					+ " Although cows are offered a special promotion after meeting certain milk production requirements, most choose to remain at this level for the remainder of their careers.");
		}
		
		// Add vagina and set race to cow-girl where appropriate:
		if((level==1 && Math.random()<0.8f)) {
			this.setBodyFromSubspeciesPreference(Gender.F_V_B_FEMALE, Util.newHashMapOfValues(new Value<>(Subspecies.COW_MORPH, 1)), true, true);
			
			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f && this.getSubspecies()!=Subspecies.SLIME) { // Don't convert slimes, as their getFleshSubspecies() can be of any non-Fields subspecies
				this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, Gender.F_V_B_FEMALE, this.getBody().getFleshSubspecies(), true), true);
			}
			
			if(Math.random()<Main.getProperties().taurSpawnRate/100f
					&& this.getLegConfiguration()!=LegConfiguration.QUADRUPEDAL) { // Do not reset this character's taur body if they spawned as a taur (as otherwise subspecies-specific settings get overridden by global taur settings)
				// Check for race's leg type as taur, otherwise NPCs which spawn with human legs won't be affected by taur conversion rate:
				if(this.getRace().getRacialBody().getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)) {
					this.setLegType(this.getRace().getRacialBody().getLegType());
					Main.game.getCharacterUtils().applyTaurConversion(this);
				}
			}
		}
		if(!this.hasVagina()) {
			this.setVaginaType(this.getAssRace().getRacialBody().getVaginaType()); // Make sure they have a vagina
		}
		if(level==2) {
			// Furry preference:
			if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_cow_search_furry")) {
				this.setBody(Gender.F_V_B_FEMALE, Subspecies.COW_MORPH, RaceStage.GREATER, true);
			} else {
				this.setBody(Gender.F_V_B_FEMALE, Subspecies.COW_MORPH, RaceStage.PARTIAL, true);
			}
			// Taur preference:
			if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_cow_search_taur")) {
				this.setLegType(LegType.COW_MORPH);
				Main.game.getCharacterUtils().applyTaurConversion(this);
			}
			// Pregnancy preference:
			if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_cow_search_pregnant")) {
				this.setPregnantLitter(new Litter(Main.game.getDateNow(), Main.game.getDateNow(), this, this, FertilisationType.NORMAL, new ArrayList<>()));
			}
			// Mileage preference:
			if(Main.game.getDialogueFlags().hasFlag("innoxia_evelyx_cow_search_high_mileage")) {
				this.setVaginaLabiaSize(Util.randomItemFromValues(LabiaSize.THREE_LARGE, LabiaSize.FOUR_MASSIVE));
				this.setVaginaSquirter(Math.random()<0.5f);
				this.setVaginaCapacity(Util.randomItemFromValues(Capacity.FOUR_LOOSE, Capacity.FIVE_ROOMY, Capacity.SIX_STRETCHED_OPEN, Capacity.SEVEN_GAPING), true);
				this.setVaginaWetness(Util.randomItemFromValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING));
				this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
				this.setVaginaPlasticity(OrificePlasticity.SIX_MALLEABLE.getValue());

				this.setAssCapacity(Util.randomItemFromValues(Capacity.FOUR_LOOSE, Capacity.FIVE_ROOMY, Capacity.SIX_STRETCHED_OPEN, Capacity.SEVEN_GAPING), true);
				this.setAssWetness(Util.randomItemFromValues(Wetness.TWO_MOIST, Wetness.THREE_WET, Wetness.FOUR_SLIMY));
				this.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
				this.setAssPlasticity(OrificePlasticity.SIX_MALLEABLE.getValue());
				
			} else {
				this.setVaginaLabiaSize(Util.randomItemFromValues(LabiaSize.TWO_AVERAGE, LabiaSize.THREE_LARGE));
				this.setVaginaSquirter(Math.random()<0.25f);
				this.setVaginaCapacity(Util.randomItemFromValues(Capacity.TWO_TIGHT, Capacity.THREE_SLIGHTLY_LOOSE), true);
				this.setVaginaWetness(Util.randomItemFromValues(Wetness.TWO_MOIST, Wetness.THREE_WET, Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY));
				this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
				this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());

				this.setAssCapacity(Util.randomItemFromValues(Capacity.TWO_TIGHT, Capacity.THREE_SLIGHTLY_LOOSE), true);
				this.setAssWetness(Util.randomItemFromValues(Wetness.ZERO_DRY, Wetness.ONE_SLIGHTLY_MOIST, Wetness.TWO_MOIST));
				this.setAssElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
				this.setAssPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
			}
		}
		
		// Set body modifications:
		if(level>=0) {
			this.setBreastSize(Math.max(this.getBreastSize().getMeasurement(), CupSize.DD.getMeasurement() + Util.random.nextInt(6)));
			this.setBreastMilkStorage(1000);
			this.fillMilkToMaxStorage();
			this.setBreastLactationRegeneration(10_000);
			if(level==0 && Math.random()<0.5f) {
				this.setVaginaVirgin(true);
			}
		}
		if(level>=1) {
			this.setBreastSize(Math.max(this.getBreastSize().getMeasurement(), CupSize.GG.getMeasurement() + Util.random.nextInt(6)));
			this.setBreastMilkStorage(2500);
			this.fillMilkToMaxStorage();
			this.setBreastLactationRegeneration(25_000);
			this.addNippleOrificeModifier(OrificeModifier.PUFFY);

			this.setFaceVirgin(false);
			this.setVaginaVirgin(false);
			if(Math.random()<0.25f) {
				this.setAssVirgin(false);
			}
			this.setVaginaWetness(Math.max(this.getVaginaWetness().getValue(), Wetness.FOUR_SLIMY.getValue()));
			Main.game.getItemGen().generateItem("innoxia_pills_broodmother").applyEffect(this, this);
			
			if(this.getRace()==Race.COW_MORPH && ((Main.getProperties().getUddersLevel()==1 && this.isTaur()) || Main.getProperties().getUddersLevel()==2)) {
				this.setBreastCrotchType(BreastType.COW_MORPH);
				this.setBreastCrotchShape(BreastShape.UDDERS);
				this.setBreastCrotchRows(1);
				this.setNippleCrotchCountPerBreast(4);
				this.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY);
			}
		}
		if(level>=2) {
			this.setBreastSize(Math.max(this.getBreastSize().getMeasurement(), CupSize.KK.getMeasurement() + Util.random.nextInt(6)));
			this.setBreastMilkStorage(5000);
			this.fillMilkToMaxStorage();
			this.setBreastLactationRegeneration(50_000);

			this.setAssVirgin(false);
			this.setVaginaWetness(Math.max(this.getVaginaWetness().getValue(), Wetness.SIX_SOPPING_WET.getValue()));
		}
		if(this.hasBreastsCrotch()) {
			this.setBreastCrotchMilkStorage(this.getBreastRawMilkStorageValue() * 2);
			this.fillMilkCrotchToMaxStorage();
			this.setBreastCrotchLactationRegeneration(this.getBreastRawLactationRegenerationValue() * 2);
		}
		
		// Set fetishes:
		this.clearFetishes();
		this.clearFetishDesires();
		if(level>=0) {
			this.addFetish(Fetish.FETISH_LACTATION_SELF);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
		}
		if(level>=1) {
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			this.addFetish(Fetish.FETISH_PREGNANCY);
		} else {
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
		}
		if(level>=2) {
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.addFetish(Fetish.FETISH_BONDAGE_VICTIM);
			this.addFetish(Fetish.FETISH_SIZE_QUEEN);
		} else {
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BONDAGE_VICTIM, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SIZE_QUEEN, FetishDesire.THREE_LIKE);
		}
		
		// Set clothing & tattoos:
		// Pumps:
		((Evelyx)Main.game.getNpc(Evelyx.class)).equipMilkingPumps(this);
		// Ear tag:
		AbstractClothing earTag = ((Evelyx)Main.game.getNpc(Evelyx.class)).getEvelyxEarTag(this, level);
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(earTag, InventorySlot.PIERCING_EAR, true, this);
		// Collar and nose ring:
		this.equipClothingFromNowhere(((Evelyx)Main.game.getNpc(Evelyx.class)).getEvelyxCollar(level), true, this);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(((Evelyx)Main.game.getNpc(Evelyx.class)).getEvelyxNoseRing(level), true, this);
		// This seemed a bit jarring when encountered, so removed in 0.4.2.2:
//		// Bdsm gear:
//		if(this.hasFetish(Fetish.FETISH_BONDAGE_VICTIM)) {
//			if(Math.random()<0.25f) { // 25% chance of ring gag
//				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.BDSM_RINGGAG, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, false), true, this);
//			}
//			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.BDSM_SPREADER_BAR, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, null, false), true, this);
//			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_wrist_bracelets"), PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, null, false), true, this);
//		}
		
		// Number tattoo on ass:
		if(level==2) {
			((Evelyx)Main.game.getNpc(Evelyx.class)).applyCowTattoo(this, false);
		}
	}
	
	
}