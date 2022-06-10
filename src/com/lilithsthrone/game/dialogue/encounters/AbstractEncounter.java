package com.lilithsthrone.game.dialogue.encounters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.EnforcerPatrol;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractEncounter {

	protected static AbstractCoreItem randomItem;
	
	protected static final double INCEST_ENCOUNTER_RATE = 0.2f;
	protected static double IncestEncounterRate() { return INCEST_ENCOUNTER_RATE; }
	
	protected static Map<String, List<AbstractEncounter>> additionalPlaceTypeEncounters = new HashMap<>();
	
	private boolean mod;
	private boolean fromExternalFile;
	private String author;

	private List<String> placeTypeIds;
	private List<ExternalEncounterData> possibleEncounters;
	
	/**
	 * Utility class to store data loaded from external files.
	 */
	private class ExternalEncounterData {
		private String name;
		private String triggerConditional;
		private boolean opportunistic;
		private String dialogueId;
		
		public ExternalEncounterData(String name, String triggerConditional, boolean opportunistic, String dialogueId) {
			this.name = name;
			this.triggerConditional = triggerConditional;
			this.opportunistic = opportunistic;
			this.dialogueId = dialogueId;
		}
		
		public float getTriggerChance() {
			try {
				return Float.valueOf(UtilText.parse(this.getTriggerConditional()).trim());
			} catch(Exception ex) {
				System.err.println("Error in AbstractEncounter's ExternalEncounterData: getTriggerChance() for '"+getName()+"' failed to parse!");
				ex.printStackTrace();
				return 0f;
			}
		}
		
		public String getName() {
			return name;
		}
		public String getTriggerConditional() {
			return triggerConditional;
		}
		public boolean isOpportunistic() {
			return opportunistic;
		}
		public String getDialogueId() {
			return dialogueId;
		}
	}
	
	public AbstractEncounter() {
	}
	
	public AbstractEncounter(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in AbstractEncounter files it's <encounter>
				
				this.mod = mod;
				this.fromExternalFile = true;
				this.author = author;
				
				this.placeTypeIds = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("additionalPlaceTypeTriggers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("additionalPlaceTypeTriggers").getAllOf("placeType")) {
						this.placeTypeIds.add(e.getTextContent());
					}
				}
				
				this.possibleEncounters = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("possibleEncounters").getAllOf("encounter")) {
					String name = e.getMandatoryFirstOf("name").getTextContent();
					String chanceToTrigger = e.getMandatoryFirstOf("chanceToTrigger").getTextContent();
					boolean opportunistic = Boolean.valueOf(e.getMandatoryFirstOf("chanceToTrigger").getAttribute("opportunisticEncounter").trim());
					String dialogueId = e.getMandatoryFirstOf("dialogueReturned").getTextContent();
					
					ExternalEncounterData data = new ExternalEncounterData(name, chanceToTrigger, opportunistic, dialogueId);
					this.possibleEncounters.add(data);
				}
					
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("WorldType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}

	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public String getAuthor() {
		return author;
	}

	public String getId() {
		return Encounter.getIdFromEncounter(this);
	}
	
	protected static void spawnEnforcers() {
		List<List<String>> savedEnforcerIds = Main.game.getSavedEnforcers(WorldType.DOMINION);
		
		Main.game.getDialogueFlags().setSavedLong("enforcer_encounter_minutes", Main.game.getMinutesPassed());
		
		// Keep 4 sets of Enforcers saved
		float chanceOfNewEnforcers = 1f - (0.25f * savedEnforcerIds.size());
		if(Math.random()<chanceOfNewEnforcers) {
			try {
				List<String> enforcerIds = new ArrayList<>();
				EnforcerPatrol npc = new EnforcerPatrol(Occupation.NPC_ENFORCER_PATROL_CONSTABLE, Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(npc, false);
				npc.setLevel(9+Util.random.nextInt(4)); // 9-12
				((EnforcerPatrol)npc).setWeapons("dsg_eep_pbweap_pbpistol");
				enforcerIds.add(npc.getId());
				
				EnforcerPatrol npc2 = new EnforcerPatrol(Occupation.NPC_ENFORCER_PATROL_CONSTABLE, Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(npc2, false);
				npc2.setLevel(4+Util.random.nextInt(5)); // 4-8
				((EnforcerPatrol)npc2).setWeapons("dsg_eep_taser_taser");
				enforcerIds.add(npc2.getId());
				
				Main.game.addSavedEnforcers(WorldType.DOMINION, enforcerIds);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			List<String> enforcerIds = Util.randomItemFrom(savedEnforcerIds);
			for(String id : enforcerIds) {
				try {
					Main.game.getNPCById(id).setLocation(Main.game.getPlayer(), false);
				} catch (Exception e) {
					System.err.println("Error in Encounter.spawnEnforcers()");
					e.printStackTrace();
				}
			}
		}
	}
	
	public static DialogueNode SpawnAndStartChildHere(List<OffspringSeed> offspringAvailable)  {
		NPC offspring = new NPCOffspring(offspringAvailable.get(Util.random.nextInt(offspringAvailable.size())));

		offspring.setLocation(Main.game.getPlayer(), true);
		
		offspring.equipClothing(EquipClothingSetting.getAllClothingSettings());
		
		Main.game.setActiveNPC(offspring);

		return Main.game.getActiveNPC().getEncounterDialogue();
	}
	
	protected static NPC getSlaveWantingToUseYouInDominion() {
		List<NPC> slaves = new ArrayList<>();
		List<NPC> hornySlaves = new ArrayList<>();
		
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
						&& slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
						&& slave.getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION 
						&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM)
						&& (!Main.game.getPlayer().getLocationPlace().getPlaceType().isPopulated() || slave.hasFetish(Fetish.FETISH_EXHIBITIONIST))
						&& slave.isAttractedTo(Main.game.getPlayer())) {
					if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
						slaves.add(slave);
					}
					if(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
						hornySlaves.add(slave);
					}
				}
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in getSlaveWantingToUseYouInDominion()");
			}
		}
		
//		System.out.println(hornySlaves.size() +" | " + slaves.size());
		
		if(!hornySlaves.isEmpty()) {
			Collections.shuffle(hornySlaves);
			return hornySlaves.get(0);
			
		} else if(!slaves.isEmpty()) {
			Collections.shuffle(slaves);
			return slaves.get(0);
		}
		
		return null;
	}

	/**
	 * @return A Value, with the key being the dominant slave and the value being the submissive slave.
	 */
	protected static Value<NPC, NPC> getSlaveUsingOtherSlaveInDominion() {
		Map<NPC, List<NPC>> hornySlaves = new HashMap<>();
		
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_SLAVES)
						&& slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
						&& slave.getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION
						&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM)) {
					if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
						hornySlaves.put(slave, new ArrayList<>());
					}
				}
				
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in getSlaveUsingOtherSlaveInDominion() 1");
			}
		}

		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RECEIVE_SLAVES)
						&& slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
						&& slave.getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION
						&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM)) {
					for(NPC horny : hornySlaves.keySet()) {
						if(!horny.equals(slave) && horny.isAttractedTo(slave)) {
							hornySlaves.get(horny).add(slave);
						}
					}
				}
				
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in getSlaveUsingOtherSlaveInDominion() 2");
			}
		}

		List<NPC> keys = new ArrayList<>(hornySlaves.keySet());
		for(NPC key : keys) {
			if(hornySlaves.get(key).isEmpty()) {
				hornySlaves.remove(key);
			}
		}
		
//		System.out.println(hornySlaves.size());

		keys = new ArrayList<>(hornySlaves.keySet());
		
		if(!hornySlaves.isEmpty()) {
			Collections.shuffle(keys);
			return new Value<>(keys.get(0), Util.randomItemFrom(hornySlaves.get(keys.get(0))));
		}
		
		return null;
	}

	/**
	 * @return A Value, with the key being the dominant slave and the value being the submissive slave.
	 */
	protected static Value<NPC, NPC> getSlaveUsingOtherSlaveInLilayaCorridor() {
		Map<NPC, List<NPC>> hornySlaves = new HashMap<>();
		
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_SLAVES)
						&& ((slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
								&& !slave.getLocationPlace().getPlaceUpgrades().stream().anyMatch(upgrade->upgrade.getImmobilisationType()!=null)
								&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM))
							|| slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.CLEANING)
						&& slave.getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION) {
					if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
						hornySlaves.put(slave, new ArrayList<>());
					}
				}
				
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in getSlaveUsingOtherSlaveInLilayaCorridor() 1");
			}
		}

		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RECEIVE_SLAVES)
						&& ((slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
								&& !slave.getLocationPlace().getPlaceUpgrades().stream().anyMatch(upgrade->upgrade.getImmobilisationType()!=null)
								&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM))
							|| slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.CLEANING)
						&& slave.getLocationPlace().getPlaceType()!=PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION) {
					for(NPC horny : hornySlaves.keySet()) {
						if(!horny.equals(slave) && horny.isAttractedTo(slave)) {
							hornySlaves.get(horny).add(slave);
						}
					}
				}
				
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in getSlaveUsingOtherSlaveInLilayaCorridor() 2");
			}
		}

		List<NPC> keys = new ArrayList<>(hornySlaves.keySet());
		for(NPC key : keys) {
			if(hornySlaves.get(key).isEmpty()) {
				hornySlaves.remove(key);
			}
		}
		
		keys = new ArrayList<>(hornySlaves.keySet());
		
		if(!hornySlaves.isEmpty()) {
			Collections.shuffle(keys);
			return new Value<>(keys.get(0), Util.randomItemFrom(hornySlaves.get(keys.get(0))));
		}
		
		return null;
	}
	
	protected abstract DialogueNode initialiseEncounter(EncounterType node);
	
	public abstract Map<EncounterType, Float> getDialogues();

	public boolean isAnyEncounterAvailable() {
		return getBaseRandomEncounter(true)!=null;
	}
	
	/**
	 * Returns a random encounter from the list, or null if no encounter was selected.
	 * 
	 * @param forceEncounter Forces an encounter to be selected. (Will still return null if the encounter list is empty.)
	 * @return null if no encounter.
	 */
	public DialogueNode getRandomEncounter(boolean forceEncounter) {
		return getBaseRandomEncounter(forceEncounter);
	}

	public boolean isAnyBaseTriggerChanceOverOneHundred() {
		if(this.isFromExternalFile()) {
			for(ExternalEncounterData data : possibleEncounters) {
				if(data.getTriggerChance()>100) {
					return true;
				}
			}
			
		} else {
			for(Entry<EncounterType, Float> e : getDialogues().entrySet()) {
				if(e.getValue()>100) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return The sum of all possible encounter chances which this AbstractEncounter contains. Will typically be a value under 100.
	 */
	public float getTotalChanceValue() {
		float total = 0;
		if(this.isFromExternalFile()) {
			for(ExternalEncounterData data : possibleEncounters) {
				float weighting = data.getTriggerChance();
				total += weighting;
			}
		} else {
			for(Entry<EncounterType, Float> e : getDialogues().entrySet()) {
				float weighting = e.getValue();
				total += weighting;
			}
		}
		return total;
	}

	private void setEncounterDialogue(DialogueNode dialogueNode, boolean forced) {
		if(forced) {
			Main.game.forcedEncounterAtSeconds = new Value<>(Main.game.getSecondsPassed(), dialogueNode);
		} else {
			Main.game.encounterAtSeconds = new Value<>(Main.game.getSecondsPassed(), dialogueNode);
		}
	}
	
	protected DialogueNode getBaseRandomEncounter(boolean forceEncounter) {
		if(forceEncounter) {
			if(Main.game.forcedEncounterAtSeconds.getKey()==Main.game.getSecondsPassed()) {
				return Main.game.forcedEncounterAtSeconds.getValue();
			}
			
		} else {
			if(Main.game.encounterAtSeconds.getKey()==Main.game.getSecondsPassed()) {
				return Main.game.encounterAtSeconds.getValue();
			}
		}
		
		float opportunisticMultiplier = 1;
		if(Main.game.isOpportunisticAttackersEnabled()) {
			// lust: linear boost; 25% max
			opportunisticMultiplier += Main.game.getPlayer().getLust() / 200;
			// health: linear boost; 25% (theoretical) max
			opportunisticMultiplier += 0.25f - Main.game.getPlayer().getHealthPercentage() * 0.25f;
			// smelly body: 25% boost
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.BODY_CUM) || Main.game.getPlayer().hasStatusEffect(StatusEffect.BODY_CUM_MASOCHIST)) {
				opportunisticMultiplier += 0.25f;
			}
			// smelly clothes: 25% boost
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CLOTHING_CUM) || Main.game.getPlayer().hasStatusEffect(StatusEffect.CLOTHING_CUM_MASOCHIST)) {
				opportunisticMultiplier += 0.25f;
			}
			// exposure: 50% or 75% boost
			if(!Collections.disjoint(
					Util.newArrayListOfValues(
						StatusEffect.EXPOSED_PLUS_BREASTS,
						StatusEffect.FETISH_EXHIBITIONIST_PLUS_BREASTS),
					Main.game.getPlayer().getStatusEffects())) {
				opportunisticMultiplier += 0.75f;
				
			} else if(!Collections.disjoint(
					Util.newArrayListOfValues(
						StatusEffect.EXPOSED,
						StatusEffect.EXPOSED_BREASTS,
						StatusEffect.FETISH_EXHIBITIONIST,
						StatusEffect.FETISH_EXHIBITIONIST_BREASTS),
					Main.game.getPlayer().getStatusEffects())) {
				opportunisticMultiplier += 0.5f;
			}
			// drunk: 50% boost
			if(!Collections.disjoint(
					Util.newArrayListOfValues(
						StatusEffect.DRUNK_3,
						StatusEffect.DRUNK_4,
						StatusEffect.DRUNK_5),
					Main.game.getPlayer().getStatusEffects())) {
				opportunisticMultiplier += 0.5f;
			}
		}
		
		if(this.isFromExternalFile()) {
//			System.out.println("--- Encounter Generation Start ---");
			float total = 0;
			float opportunisticIncrease = 0;
			Map<ExternalEncounterData, Float> finalMap = new HashMap<>();
			for(ExternalEncounterData data : possibleEncounters) {
				float weighting = data.getTriggerChance();
				if(!this.isAnyBaseTriggerChanceOverOneHundred() || data.getTriggerChance()>100) { // If a value of >100 is used for the encounter chance, then all other encounters with chances of <=100 are discarded
					if(data.isOpportunistic()) {
						weighting *= opportunisticMultiplier;
						opportunisticIncrease+=opportunisticMultiplier;
					}
					total+=weighting;
					finalMap.put(data, weighting);
//					System.out.println("Weighting add: "+weighting+" ("+data.getName()+")");
				}
			}
			if(total==0) {
				setEncounterDialogue(null, forceEncounter);
				return null;
			}
//			System.out.println("Final total: "+total);
//			System.out.println("Final opportunisticIncrease: "+opportunisticIncrease);
			
			if(forceEncounter || Math.random()*(100+opportunisticIncrease)<total) {
				ExternalEncounterData encounter;
				DialogueNode dn = null;
				int tries = 0;
				while(dn==null && tries<=3) { // As some Encounters rarely return null, try 3 times to get an Encounter. Yes this is not ideal, but it was either this or suffer performance issues in calculating Encounter availabilities.
					tries++;
					encounter = Util.getRandomObjectFromWeightedFloatMap(finalMap);
					finalMap.remove(encounter);
					dn = DialogueManager.getDialogueFromId(UtilText.parse(encounter.getDialogueId()).trim());
				}
//				System.out.println("Returning: "+dn.getId());
//				System.out.println("--- END ---");
				setEncounterDialogue(dn, forceEncounter);
				return dn;
			}
//			System.out.println("--- END ---");
			
		} else {
			float total = 0;
			float opportunisticIncrease = 0;
			Map<EncounterType, Float> finalMap = new HashMap<>();
			for(Entry<EncounterType, Float> e : getDialogues().entrySet()) { // Iterate through the base encounter map, apply opportunisticMultiplier if applicable, and create a new 'finalMap' of these weighted chances.
				float weighting = e.getValue();
				if(!this.isAnyBaseTriggerChanceOverOneHundred() || weighting>100) { // If a value of >100 is used for the encounter chance, then all other encounters with chances of <=100 are discarded
					if(e.getKey().isOpportunistic()) {
						weighting *= opportunisticMultiplier;
						opportunisticIncrease+=opportunisticMultiplier;
					}
					total+=weighting;
					finalMap.put(e.getKey(), weighting);
				}
			}
			if(total==0) {
				setEncounterDialogue(null, forceEncounter);
				return null;
			}
			
			if(forceEncounter || Math.random()*(100+opportunisticIncrease)<total) {
				EncounterType encounter;
				DialogueNode dn = null;
				int tries = 0;
				while(dn==null && tries<=3) { // As some Encounters rarely return null, try 3 times to get an Encounter. Yes this is not ideal, but it was either this or suffer performance issues in calculating Encounter availabilities.
					tries++;
					encounter = Util.getRandomObjectFromWeightedFloatMap(finalMap);
					finalMap.remove(encounter);
					dn = initialiseEncounter(encounter);
				}
				setEncounterDialogue(dn, forceEncounter);
				return dn;
			}
		}

		setEncounterDialogue(null, forceEncounter);
		return null;
	}
	
	public static AbstractCoreItem getRandomItem() {
		return randomItem;
	}

	public List<String> getPlaceTypeIds() {
		return placeTypeIds;
	}

}
