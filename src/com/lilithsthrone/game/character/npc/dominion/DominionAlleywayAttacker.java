package com.lilithsthrone.game.character.npc.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayProstituteDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.StormStreetAttackerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.3.5.5
 * @author Innoxia
 */
public class DominionAlleywayAttacker extends RandomNPC {
	
	public DominionAlleywayAttacker(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public DominionAlleywayAttacker(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		boolean canalSpecies = false;
		AbstractPlaceType pt = Main.game.getPlayerCell().getPlace().getPlaceType();
		if (pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
				|| pt.equals(PlaceType.DOMINION_CANAL)
				|| pt.equals(PlaceType.DOMINION_CANAL_END)) {
			canalSpecies = true;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(3)+1);
		
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			if (s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			if (s == Subspecies.REINDEER_MORPH) {
				if (Main.game.getSeason() == Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
					AbstractSubspecies.addToSubspeciesMap((int) ((canalSpecies?500:10000)*SubspeciesSpawnRarity.FIVE.getChanceMultiplier()), getGenderIdentity(), s, subspeciesMap);
				}
			} else {
				if (Subspecies.getWorldSpecies(WorldType.DOMINION, pt, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) ((canalSpecies?2500:10000)*Subspecies.getWorldSpecies(WorldType.DOMINION, pt, false).get(s).getChanceMultiplier()), getGenderIdentity(), s, subspeciesMap);
				}
				if (canalSpecies && Subspecies.getWorldSpecies(WorldType.SUBMISSION, pt, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (10000*Subspecies.getWorldSpecies(WorldType.SUBMISSION, pt, false).get(s).getChanceMultiplier()), getGenderIdentity(), s, subspeciesMap);
				}
			}
		}
		
		// Setup
		setupAlleyAttacker(subspeciesMap,
				null,
				Main.game.getCurrentWeather() != Weather.MAGIC_STORM || canalSpecies || pt == PlaceType.DOMINION_BACK_ALLEYS,
				generationFlags);
		
		// Post-setup
		if (Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
			this.setHistory(Occupation.NPC_MUGGER);
		}
		
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			this.setPlayerKnowsName(true);
		}
		
		if (this.isStormAttacker() && !this.isVulnerableToArcaneStorm()) { // NPCs spawned during a storm should be vulnerable to it.
			this.addSpecialPerk(Perk.SPECIAL_ARCANE_ALLERGY);
		}
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue()*0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.PROSTITUTE, settings);
		} else {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
		}
	}
	
	@Override
	public String getDescription() {
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			if (this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of whoring [npc.herself] out in the back alleys of Dominion are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if (this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)) {
				return (UtilText.parse(this,
						"You first found [npc.name] in the alleyways of Dominion, where [npc.she] was illegally selling [npc.her] body. You offered [npc.herHim] the chance to move and work out of Angel's Kiss; an offer which [npc.she] happily accepted."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the backalleys of Dominion."));
			}
		} else {
			if (this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Dominion and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if (Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Dominion and mugging innocent travellers are now over. Having befriended [npc.herHim], you invited [npc.name] to move in with you and helped [npc.herHim] to start a new life."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Dominion, who prowls the back alleys in search of innocent travellers to prey upon."));
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		if (!isStormAttacker()) {
			if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
				return AlleywayProstituteDialogue.ALLEY_PROSTITUTE;
			} else {
				return AlleywayAttackerDialogue.ALLEY_ATTACK;
			}
		} else {
			return StormStreetAttackerDialogue.STORM_ATTACK;
		}
	}
	
	// Combat:
	
	@Override
	public void applyEscapeCombatEffects() {
		if (isStormAttacker()) {
			Main.game.banishNPC(this);
		}
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (this.getHistory() == Occupation.NPC_PROSTITUTE) {
			if (victory) {
				return new Response("", "", AlleywayProstituteDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response("", "", AlleywayProstituteDialogue.AFTER_COMBAT_DEFEAT);
			}
			
		} else if (isStormAttacker()) {
			if (victory) {
				return new Response("", "", StormStreetAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response("", "", StormStreetAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
			
		} else {
			if (victory) {
				return new Response("", "", AlleywayAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response("", "", AlleywayAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
		}
	}
	
	public boolean isStormAttacker() {
		AbstractPlaceType pt = this.getLocationPlace().getPlaceType();
		return this.getWorldLocation().equals(WorldType.DOMINION)
				&& !pt.equals(PlaceType.DOMINION_BACK_ALLEYS)
				&& !pt.equals(PlaceType.DOMINION_ALLEYS_CANAL_CROSSING)
				&& !pt.equals(PlaceType.DOMINION_CANAL)
				&& !pt.equals(PlaceType.DOMINION_CANAL_END)
				&& !Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())
				&& (!this.isSlave() || !this.getOwner().isPlayer());
	}
}
