package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelAttackDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelAttackDialogueCompanions;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelSlimeDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.1
 * @version 0.2.11
 * @author Innoxia
 */
public class SubmissionAttacker extends NPC {

	public SubmissionAttacker() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public SubmissionAttacker(Gender gender) {
		this(gender, false);
	}
	
	public SubmissionAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public SubmissionAttacker(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.ALLIGATOR_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			// RACE & NAME:
			
			int slimeChance = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN) ? 200 : 80;
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No chance of spawn:
					case ANGEL:
					case CAT_MORPH:
					case CAT_MORPH_LYNX:
					case CAT_MORPH_LEOPARD_SNOW:
					case CAT_MORPH_LEOPARD:
					case CAT_MORPH_LION:
					case CAT_MORPH_TIGER:
					case CAT_MORPH_CHEETAH:
					case CAT_MORPH_CARACAL:
					case COW_MORPH:
					case HALF_DEMON:
					case DEMON:
					case LILIN:
					case ELDER_LILIN:
					case DOG_MORPH:
					case DOG_MORPH_DOBERMANN:
					case DOG_MORPH_BORDER_COLLIE:
					case FOX_MORPH:
					case FOX_MORPH_FENNEC:
					case FOX_ASCENDANT:
					case FOX_ASCENDANT_FENNEC:
					case HARPY:
					case HARPY_RAVEN:
					case HARPY_BALD_EAGLE:
					case HORSE_MORPH:
					case HORSE_MORPH_ZEBRA:
					case HUMAN:
					case REINDEER_MORPH:
					case SQUIRREL_MORPH:
					case WOLF_MORPH:
					case RABBIT_MORPH:
					case RABBIT_MORPH_LOP:
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
						break;
					
					// Rare spawns:
					case BAT_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case IMP_ALPHA:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
						
					// Common spawns:
					case ALLIGATOR_MORPH:
						addToSubspeciesMap(30, gender, s, availableRaces);
						break;
					case IMP:
						addToSubspeciesMap(50, gender, s, availableRaces);
						break;
					case RAT_MORPH:
						addToSubspeciesMap(40, gender, s, availableRaces);
						break;
					case SLIME:
						addToSubspeciesMap(slimeChance, gender, s, availableRaces);
						break;
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Submission, who prowls the tunnels looking for strangers to mug and assault..."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, true);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			if(this.getBodyMaterial()==BodyMaterial.SLIME) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN) == Quest.SLIME_QUEEN_ONE) {
					this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
					this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				}
			}
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
	
			equipClothing(true, true, true, true);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			initAttributes();
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
		
		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		CharacterUtils.equipClothing(this, replaceUnsuitableClothing, false);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of whoring [npc.herself] out in the tunnels of Submission are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the tunnels of Submission."));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the tunnels of Submission and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Submission, who prowls the tunnels in search of innocent travellers to mug and rape."));
			}
		}
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
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
			if(Main.game.getPlayer().getCompanions().isEmpty()) {
				return TunnelAttackDialogue.TUNNEL_ATTACK;
			} else {
				return TunnelAttackDialogueCompanions.TUNNEL_ATTACK;
			}
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
