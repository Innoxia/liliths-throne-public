package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelAttackDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelSlimeDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.1
 * @version 0.3.5.5
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
				3,
				null, null, null,
				new CharacterInventory(10), WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			// RACE & NAME:
			
			int slimeChance = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN) ? 200 : 80;
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				if(s==Subspecies.SLIME) {
					Subspecies.addToSubspeciesMap(slimeChance, gender, s, availableRaces);
					
				} else if(Subspecies.getWorldSpecies().get(WorldType.SUBMISSION).containsKey(s)) {
					Subspecies.addToSubspeciesMap((int) (100 * Subspecies.getWorldSpecies().get(WorldType.SUBMISSION).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true);
			
			if(Math.random()<0.05 && !this.getRace().equals(Race.DEMON) && this.getSubspecies()!=Subspecies.SLIME) { //5% chance for the NPC to be a half-demon
				this.setBody(CharacterUtils.generateHalfDemonBody(this, gender, Subspecies.getFleshSubspecies(this), true), true);
			}
			
			if(Main.getProperties().taurFurryLevel>0 && Math.random()<0.05 && this.isLegConfigurationAvailable(LegConfiguration.TAUR)) { //5% chance for the NPC to be a taur
				int taurLevel = Main.getProperties().taurFurryLevel;
				if(this.getRace()==Race.DEMON) {
					taurLevel = 3; // Demons should always be untouched
				}
				switch(taurLevel) {
					case 1:
						this.setLegConfiguration(LegConfiguration.TAUR, true);
						this.setAntennaType(AntennaType.NONE);
						this.setArmType(ArmType.HUMAN);
						this.setBreastType(BreastType.HUMAN);
						this.setEarType(EarType.HUMAN);
						this.setEyeType(EyeType.HUMAN);
						this.setFaceType(FaceType.HUMAN);
						this.setHairType(HairType.HUMAN);
						this.setHornType(HornType.NONE);
						this.setSkinType(SkinType.HUMAN);
						break;
					case 2:
						this.setLegConfiguration(LegConfiguration.TAUR, true);
						this.setAntennaType(Util.randomItemFrom(AntennaType.getAntennaTypes(this.getLegRace())));
						this.setArmType(ArmType.HUMAN);
						this.setBreastType(BreastType.HUMAN);
						this.setEarType(Util.randomItemFrom(EarType.getEarTypes(this.getLegRace())));
						this.setEyeType(Util.randomItemFrom(EyeType.getEyeTypes(this.getLegRace())));
						this.setFaceType(FaceType.HUMAN);
						this.setHairType(Util.randomItemFrom(HairType.getHairTypes(this.getLegRace())));
						this.setHornType(Util.randomItemFrom(HornType.getHornTypes(this.getLegRace())));
						this.setSkinType(SkinType.HUMAN);
						break;
					case 3:
						this.setLegConfiguration(LegConfiguration.TAUR, true);
						break;
					case 4:
						this.setLegConfiguration(LegConfiguration.TAUR, true);
						this.setAntennaType(Util.randomItemFrom(AntennaType.getAntennaTypes(this.getLegRace())));
						this.setArmType(Util.randomItemFrom(ArmType.getArmTypes(this.getLegRace())));
						this.setBreastType(Util.randomItemFrom(BreastType.getBreastTypes(this.getLegRace())));
						this.setEarType(Util.randomItemFrom(EarType.getEarTypes(this.getLegRace())));
						this.setEyeType(Util.randomItemFrom(EyeType.getEyeTypes(this.getLegRace())));
						this.setFaceType(Util.randomItemFrom(FaceType.getFaceTypes(this.getLegRace())));
						this.setHairType(Util.randomItemFrom(HairType.getHairTypes(this.getLegRace())));
						this.setHornType(Util.randomItemFrom(HornType.getHornTypes(this.getLegRace())));
						this.setSkinType(Util.randomItemFrom(SkinType.getSkinTypes(this.getLegRace())));
						break;
					default:
						break;
				}
			}
			
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
	
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}
		
		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
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
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		CharacterUtils.generateItemsInInventory(this);
		
		CharacterUtils.equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
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
