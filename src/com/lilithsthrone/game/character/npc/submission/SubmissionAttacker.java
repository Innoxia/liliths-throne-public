package com.lilithsthrone.game.character.npc.submission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelAttackDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelSlimeDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.1
 * @version 0.3.5.5
 * @author Innoxia
 */
public class SubmissionAttacker extends RandomNPC {

	public SubmissionAttacker(NPCGenerationFlag... generationFlags) {
		this(false, generationFlags);
	}
	
	public SubmissionAttacker(boolean isImported, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		setLevel(5+Util.random.nextInt(4));
		int slimeChance = 800;
		if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
			slimeChance *= 3; // Increase chance of encountering slime if the player helped the slime queen (which results in more slimes being in Submission)
			
		} else if (!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_ONE)) {
			slimeChance *= 4; // Increase chance of encountering slime if the player is at the start of the slime quest
		}
		
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		for (AbstractSubspecies s : Subspecies.getAllSubspecies()) {
			Map<AbstractSubspecies, SubspeciesSpawnRarity> worldSpeciesMap = Subspecies.getWorldSpecies(WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS, false);
			if (s == Subspecies.SLIME) {
				AbstractSubspecies.addToSubspeciesMap(slimeChance, this.getGenderIdentity(), s, subspeciesMap, SubspeciesPreference.FOUR_ABUNDANT);
				
			} else if (worldSpeciesMap.containsKey(s)) {
				if (s == Subspecies.IMP || s == Subspecies.IMP_ALPHA) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*worldSpeciesMap.get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap, SubspeciesPreference.FOUR_ABUNDANT);
				} else {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*worldSpeciesMap.get(s).getChanceMultiplier()), this.getGenderIdentity(), s, subspeciesMap);
				}
			}
		}
		AbstractSubspecies randomSpecies = Util.getRandomObjectFromWeightedMap(subspeciesMap);
		RaceStage stage = null;
		if (randomSpecies == Subspecies.SLIME || randomSpecies == Subspecies.IMP || randomSpecies == Subspecies.IMP_ALPHA) {
			stage = RaceStage.GREATER;
		}
		
		// Setup
		setupNPC(randomSpecies,
				stage,
				null,
				true,
				true,
				true,
				true,
				true,
				true,
				true,
				generationFlags);
		
		// Post-setup
		this.setDescription(UtilText.parse(this,
				"[npc.Name] is a resident of Submission, who prowls the tunnels looking for strangers to mug and assault..."));
		if ((this.getSubspecies() == Subspecies.IMP || this.getSubspecies() == Subspecies.IMP_ALPHA)
				&& !this.hasPersonalityTrait(PersonalityTrait.MUTE)
				&& Math.random()<0.9f) {
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		if (this.getBodyMaterial() == BodyMaterial.SLIME) {
			if (Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN) == Quest.SLIME_QUEEN_ONE) {
				this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
				this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			}
		}
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
	}
	
	@Override
	public String getDescription() {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of whoring [npc.herself] out in the tunnels of Submission are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if(this.getLocationPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)){
				return (UtilText.parse(this,
						"You first found [npc.name] in the tunnels of Submission, where [npc.she] was illegally selling [npc.her] body. You offered [npc.herHim] the chance to move and work out of Angel's Kiss; an offer which [npc.she] happily accepted."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the tunnels of Submission."));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the tunnels of Submission and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else if(Main.game.getPlayer().getFriendlyOccupants().contains(this.getId())){
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the tunnels of Submission and mugging innocent travellers are now over. Having befriended [npc.herHim], you invited [npc.name] to move in with you and helped [npc.herHim] to start a new life."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Submission, who prowls the tunnels in search of innocent travellers to mug and rape."));
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
			
			if(this.getLastTimeEncountered()==NPC.DEFAULT_TIME_START_VALUE) {
				if(this.hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN) == Quest.SLIME_QUEEN_ONE) {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER_SLIME_QUEEN"));
					} else {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/tunnelSlime", "ATTACK_TRANSFORMER"));
					}
				}
				
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_ONE) {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO));
				}
				
				return TunnelSlimeDialogue.ATTACK;
				
			} else {
				if(!this.isCharacterReactedToPregnancy(Main.game.getPlayer()) && this.isVisiblyPregnant()) {
					this.setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
					return TunnelSlimeDialogue.ATTACK_PREGNANCY_REVEAL;
				}
				
				return TunnelSlimeDialogue.ATTACK_REPEAT;
			}
			
		} else {
			return TunnelAttackDialogue.TUNNEL_ATTACK;
		}
	}

	// Combat:
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			if(this.getBodyMaterial()==BodyMaterial.SLIME) {
				return new Response("", "", TunnelSlimeDialogue.AFTER_COMBAT_PLAYER_VICTORY);
			} else {
				return new Response("", "", TunnelAttackDialogue.AFTER_COMBAT_VICTORY);
			}
			
		} else {
			if(this.getBodyMaterial()==BodyMaterial.SLIME) {
				return new Response("", "", TunnelSlimeDialogue.AFTER_COMBAT_PLAYER_DEFEAT);
			} else {
				return new Response("", "", TunnelAttackDialogue.AFTER_COMBAT_DEFEAT);
			}
		}
	}
}
