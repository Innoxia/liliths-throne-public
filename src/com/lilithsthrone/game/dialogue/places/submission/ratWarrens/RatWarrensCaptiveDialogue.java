package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * Dialogue for when the player is taken captive by the rats in the Rat Warrens.
 * 
 * @since 0.3.5.5
 * @version 0.3.8.9
 * @author Innoxia
 */
public class RatWarrensCaptiveDialogue {
	
	private static CaptiveInteractionType playerInteraction;
	private static boolean playerMurkSex;
	private static Value<SexSlot, SexType> playerFuckedSexType;
	private static boolean playerGrewVagina;
	
	private static List<GameCharacter> getCharacters(boolean includeMilkers) {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> (!includeMilkers && (npc instanceof RatWarrensCaptive)));
		Collections.sort(guards, (a, b)->a.getLevel()-b.getLevel());
		return guards;
	}
	
	private static List<GameCharacter> getMilkers() {
		List<GameCharacter> milkers = new ArrayList<>();
		for(GameCharacter milker : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM))) {
			if(milker instanceof RatWarrensCaptive) {
				milkers.add(milker);
			}
		}
		return milkers;
	}
	
	private static void spawnRats(int count) {
		List<String> adjectives = new ArrayList<>();
		for(int i=0;i<count;i++) {
			try {
				String[] names = new String[] {"thug", "gangster", "gang-member", "mobster"};
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, i==0));
				Main.game.addNPC(rat, false);
				rat.setLevel(8-i);
				rat.setLocation(Main.game.getPlayer(), true);
				adjectives.add(CharacterUtils.setGenericName(rat, Util.randomItemFrom(names), adjectives));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void banishRats() {
		for(GameCharacter rat : getCharacters(false)) {
			Main.game.banishNPC((NPC) rat);
		}
	}
	
	private static GameCharacter getMurk() {
		return Main.game.getNpc(Murk.class);
	}

	public static String equipCollar(GameCharacter character, GameCharacter equipper, Colour collarColour) {
		AbstractClothing collar = AbstractClothingType.generateClothing("innoxia_bdsm_metal_collar", collarColour, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_GUNMETAL, false);
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		return character.equipClothingFromNowhere(collar, true, equipper);
	}
	
	private static String equipRingGag(GameCharacter character) {
		AbstractClothing gag = AbstractClothingType.generateClothing(ClothingType.BDSM_RINGGAG, PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_STEEL, false);
		gag.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0));
		gag.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_BOOST, 0));
		return character.equipClothingFromNowhere(gag, true, getMurk());
	}
	
	private static void calculatePlayerSexType(boolean includeForeplayTypes) {
		List<Value<SexSlot, SexType>> sexTypes = new ArrayList<>();
		
		if(Main.game.isAnalContentEnabled()) {
			sexTypes.add(new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)));
		}
		if(Main.game.getPlayer().hasVagina()) {
			sexTypes.add(new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)));
		}
		sexTypes.add(new Value<>(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)));
		
		if(includeForeplayTypes) {
			if(Main.game.isAnalContentEnabled()) {
				sexTypes.add(new Value<>(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)));
				sexTypes.add(new Value<>(SexSlotMilkingStall.PERFORMING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)));
			}
			
			if(Main.game.getPlayer().hasPenis()) {
				sexTypes.add(new Value<>(SexSlotMilkingStall.PERFORMING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)));
			}
			
			if(Main.game.getPlayer().hasVagina()) {
				sexTypes.add(new Value<>(SexSlotMilkingStall.PERFORMING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)));
				sexTypes.add(new Value<>(SexSlotMilkingStall.PERFORMING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)));
			}
		}
		
		playerFuckedSexType = Util.randomItemFrom(sexTypes);
	}
	
	private static ResponseSex getPlayerOwnerSexResponse(String title, String description, DialogueNode node) {
		String introDescriptionPath = "BEG_FOR_SEX";
		if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
			introDescriptionPath = "BEG_FOR_SEX_ANAL";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
			introDescriptionPath = "BEG_FOR_SEX_VAGINAL";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
			introDescriptionPath = "BEG_FOR_SEX_BLOWJOB";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
			introDescriptionPath = "BEG_FOR_SEX_ANILINGUS";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))) {
			introDescriptionPath = "BEG_FOR_SEX_RECEIVE_ANILINGUS";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS))) {
			introDescriptionPath = "BEG_FOR_SEX_RECEIVE_HANDJOB";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))) {
			introDescriptionPath = "BEG_FOR_SEX_RECEIVE_FINGERING";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))) {
			introDescriptionPath = "BEG_FOR_SEX_RECEIVE_CUNNILINGUS";
		}
		
		return new ResponseSex(title,
				description,
				true,
				false,
				new SexManagerDefault(
						SexPosition.MILKING_STALL,
						Util.newHashMapOfValues(
								new Value<>(getMurk(), playerFuckedSexType.getKey())),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
					@Override
					public SexControl getSexControl(GameCharacter character) {
						if(character.isPlayer()) {
							return SexControl.NONE;
						}
						return super.getSexControl(character);
					}
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
						if(character.isPlayer()) {
							return false;
						}
						return super.isAbleToRemoveOthersClothing(character, clothing);
					}
					@Override
					public boolean isAbleToEquipSexClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
						if(!performer.isPlayer()
								&& (playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaPenetration.PENIS || playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaOrifice.VAGINA)) {
							return Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA);
						}
						return new ArrayList<>();
					}
					@Override
					public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getForeplayPreference(character, targetedCharacter);
						}
						return playerFuckedSexType.getValue();
					}
					@Override
					public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getMainSexPreference(character, targetedCharacter);
						}
						return playerFuckedSexType.getValue();
					}
					
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", introDescriptionPath, getCharacters(false))) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));

				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
				}
				return super.getInitialSexActions();
			}
		};
	}
	
	private static ResponseSex getPlayerOwnerEscapeSexResponse(DialogueNode node, String nodePathOral, String nodePathSex) {
		playerFuckedSexType =  new Value<>(SexSlotAllFours.IN_FRONT, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)) {
			if(Main.game.getPlayer().hasVagina()) {
				playerFuckedSexType = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
				
			} else if(Main.game.isAnalContentEnabled()) {
				playerFuckedSexType = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
			}
		}
		return new ResponseSex(
				playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH
					?"Get down"
					:"Lie back",
				playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH
					?"Get down on all fours to show your submission to Murk, allowing him to fuck your face."
					:"Lie down on your back to show your submission to Murk, allowing him to fuck you.",
				true,
				false,
				new SexManagerDefault(
						playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH
							?SexPosition.ALL_FOURS
							:SexPosition.LYING_DOWN,
						Util.newHashMapOfValues(
								new Value<>(getMurk(), playerFuckedSexType.getKey())),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), 
										playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH
											?SexSlotAllFours.ALL_FOURS
											:SexSlotLyingDown.LYING_DOWN))) {
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
						if(character.isPlayer()) {
							return false;
						}
						return super.isAbleToRemoveOthersClothing(character, clothing);
					}
					@Override
					public boolean isAbleToEquipSexClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
						if(!performer.isPlayer()
								&& (playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaPenetration.PENIS || playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaOrifice.VAGINA)) {
							return Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA);
						}
						return new ArrayList<>();
					}
					@Override
					public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getForeplayPreference(character, targetedCharacter);
						}
						return playerFuckedSexType.getValue();
					}
					@Override
					public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getMainSexPreference(character, targetedCharacter);
						}
						return playerFuckedSexType.getValue();
					}
					
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH?nodePathOral:nodePathSex, getCharacters(false))) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					
				} else {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					
				}
			}
		};
	}

	private static SexManagerDefault getPlayerMurkMilkingStallSM(SexSlot murkSlot, SexType murkSexPreference, int murkOrgasms) {
		return getPlayerMurkMilkingStallSM(murkSlot, murkSexPreference, murkOrgasms, null);
	}

	private static SexManagerDefault getPlayerMurkMilkingStallSM(SexSlot murkSlot, SexType murkSexPreference, int murkOrgasms, SexPace startingSexPace) {
		return getPlayerMurkSM(SexPosition.MILKING_STALL, murkSlot, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL, murkSexPreference, murkOrgasms, startingSexPace);
	}
	
	private static SexManagerDefault getPlayerMurkSM(AbstractSexPosition position, SexSlot murkSlot, SexSlot playerSlot, SexType murkSexPreference, int murkOrgasms) {
		return getPlayerMurkSM(position, murkSlot, playerSlot, murkSexPreference, murkOrgasms, null);
	}
	
	private static SexManagerDefault getPlayerMurkSM(AbstractSexPosition position, SexSlot murkSlot, SexSlot playerSlot, SexType murkSexPreference, int murkOrgasms, SexPace startingSexPace) {
		return new SexManagerDefault(
				position,
					Util.newHashMapOfValues(new Value<>(getMurk(), murkSlot)),
					Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(murkOrgasms>0) {
					return Main.sex.getNumberOfOrgasms(partner)>=murkOrgasms;
				}
				return super.isPartnerWantingToStopSex(partner);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.NONE;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
				return false;
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return false;
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return murkSexPreference;
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return super.getMainSexPreference(character, targetedCharacter);
			}
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer() && startingSexPace!=null) {
					return startingSexPace;
				}
				return super.getStartingSexPaceModifier(character);
			}
		};
	}
	
	private static ResponseSex getPlayerMilkingStallSexResponse(String title, String description, DialogueNode node, Map<GameCharacter, SexSlot> npcSlots, String introDialoguePath) {
		return new ResponseSex(title,
				description,
				true,
				false,
				new SexManagerDefault(
						SexPosition.MILKING_STALL,
						npcSlots,
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
					@Override
					public SexControl getSexControl(GameCharacter character) {
						if(character.isPlayer()) {
							return SexControl.NONE;
						}
						return super.getSexControl(character);
					}
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
						if(character.isPlayer()) {
							return false;
						}
						return super.isAbleToRemoveOthersClothing(character, clothing);
					}
					@Override
					public boolean isAbleToEquipSexClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
						return !character.isPlayer();
					}
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", introDialoguePath, getCharacters(false)));
	}
	
	private static void applyWaitingEffects() {
		Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_START", getCharacters(false)));
		
		getMurk().returnToHome();

		calculatePlayerSexType(true);
		playerInteraction = CaptiveInteractionType.getRandomType(Main.game.getPlayer());
		playerMurkSex = false;
		switch(playerInteraction) {
			case MILKING:
				getMurk().setLocation(Main.game.getPlayer(), false);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_MILKING", getCharacters(false)));
				break;
				
			case PUNISHMENT:
				getMurk().setLocation(Main.game.getPlayer(), false);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_PUNISHMENT", getCharacters(false)));
				break;
				
			case RAT_SEX:
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_SEX", getCharacters(false)));
				spawnRats(1);
				break;
				
			case RAT_SEX_THREESOME:
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_SEX_THREESOME", getCharacters(false)));
				spawnRats(2);
				break;
				
			case TEASE:
				calculatePlayerSexType(false);
				getMurk().setLocation(Main.game.getPlayer(), false);
				playerMurkSex = Math.random()<0.25f
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveOwnerSex);
				
				if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_TEASE_VAGINA"+(playerMurkSex?"_SEX":""), getCharacters(false)));
				} else if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_TEASE_ANUS"+(playerMurkSex?"_SEX":""), getCharacters(false)));
				} else {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_TEASE"+(playerMurkSex?"_SEX":""), getCharacters(false)));
				}
				break;
				
			case INSPECTION:
				getMurk().setLocation(Main.game.getPlayer(), false);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_INSPECTION", getCharacters(false)));
				playerMurkSex = Math.random()<0.25f
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveOwnerSex);
				break;
				
			case MURK_SEX:
				calculatePlayerSexType(false);
				getMurk().setLocation(Main.game.getPlayer(), false);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_MURK_SEX", getCharacters(false)));
				break;
		}
	}
	private static void applyWakingEffects() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut, false);
	}
	
	public static void restoreInventories() {
		int essences = Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE);
		Main.game.getPlayer().setInventory(Main.game.getSavedInventories().get(Main.game.getPlayer().getId()));
		Main.game.getPlayer().setEssenceCount(TFEssence.ARCANE, essences);
	}
	
	public static boolean isTransformationFinished() {
		CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
		return playerTf==null;
	}
	
	private static DialogueNode getSleepNode() {
		if(!isTransformationFinished()) {
			int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
			if(stage==1) {
				return CAPTIVE_DAY_2_WAKE;
			}
			if(stage==3) {
				return CAPTIVE_DAY_3_WAKE;
			}
		}
		return CAPTIVE_MILKER_WAKE;
	}
	
	private static boolean isMasculineTransform() {
		return Main.getProperties().getForcedTFTendency().isMasculine()
				&& Main.game.isAnalContentEnabled();
	}
	
	private static String incrementPlayerObedience(float increment) {
		Main.game.getPlayer().incrementObedience(increment);
		
		float obedience = Main.game.getPlayer().getObedienceValue();
		if(obedience<0) {
			Main.game.getPlayer().setObedience(0);
		}
		ObedienceLevel level = Main.game.getPlayer().getObedience();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
			sb.append("You "+(increment>0?"[style.boldGrow(gained)]":"[style.boldShrink(lost)]")+" <b>"+Math.abs(increment)+"</b> [style.boldObedience(obedience)], and now have"
					+ " <b style='color:"+level.getColour().toWebHexString()+";'>"+(obedience>0?"+":"")+Units.round(obedience, 1)+"</b><b>/100</b>!");
			sb.append("<br/>You are <span style='color:"+level.getColour().toWebHexString()+";'>"+level.getName()+"</span>, ");
			if(obedience>=100 && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
				sb.append("and if you don't snap yourself out of it, [style.italicsBadEnd(you'll fully capitulate to Murk at the end of the day)]!");
				
			} else {
				switch(level) {
					case ZERO_FREE_WILLED:
					case POSITIVE_ONE_AGREEABLE:
						sb.append("and can refuse or obey any order!");
						break;
					case POSITIVE_TWO_OBEDIENT:
					case POSITIVE_THREE_DISCIPLINED:
						sb.append("and cannot refuse Murk's orders!");
						break;
					case POSITIVE_FOUR_DUTIFUL:
					case POSITIVE_FIVE_SUBSERVIENT:
						sb.append("and will do absolutely anything for Murk!");
						break;
					default:
						break;
				}
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	// --------- START OF DAY 0 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_0 = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Stay quiet",
						"Stay quiet and hope that Murk leaves you alone.",
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_QUIET", getCharacters(false)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END", getCharacters(false)));
					}
				};
				
			} else if(index==2) {
				return new Response("Thank him",
						"Say thank you to Murk.",
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_THANK_HIM", getCharacters(false)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END", getCharacters(false)));
					}
				};
				
			} else if(index==3) {
				return new Response("Insult him",
						"Tell Murk to fuck off.",
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_INSULT", getCharacters(false)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END", getCharacters(false)));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_0_THANKS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isPregnant()) {
				if(index==1) {
					return new Response("Birthing", "Murk notices that you're ready to give birth...", CAPTIVE_GIVE_BIRTH) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							Main.game.getPlayer().setMana(0);
							
							if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
								Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
								Main.game.getPlayer().incrementVaginaCapacity(
										(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
										false);
							}
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) { // If birthing side quest is not complete, remove it, as otherwise completion (referencing Lily) doesn't make any sense.
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY);
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Sleep",
							"Completely exhausted, you find yourself drifting off to sleep...",
							CAPTIVE_DAY_1_WAKE) {
						@Override
						public void effects() {
							// This is needed as CAPTIVE_DAY_1_WAKE can also start as a follow-on to giving birth.
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_START", getCharacters(false)));
						}
					};
				}
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 0 --------- //

	
	// --------- START OF DAY 1 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_1_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			if(getMilkers().get(3).isPregnant()) {
				getMilkers().get(3).endPregnancy(true); // End milker 4's pregnancy as she's the one to lie on top of the player at the end of day 2 (which seems wrong if she's pregnant)
			}
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			// Remember that dialogue starts with either CAPTIVE_DAY_1_WAKE_START or CAPTIVE_GIVE_BIRTH_INITIAL_FINISHED
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_PREP", getCharacters(false)));
			if(isMasculineTransform()) {
				sb.append( UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_MASCULINE", getCharacters(false)));
			} else {
				sb.append( UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE", getCharacters(false)));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isMasculineTransform()) {
				if(index==1) {
					return new Response("Pull away",
							"Pull away from [murk.namePos] cock, which will make him want to transform you into a [style.colourMasculineStrong(masculine)] cum-milker.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveMasculine, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_MASCULINE", getCharacters(false)));
						}
					};
					
				} else if(index==2) {
					return new Response("Push back",
							"Raise your hips and push your ass back into [murk.namePos] cock, which will make him want to transform you into an [style.colourAndrogynous(androgynous)] sissy cum-milker.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveSissy, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_SISSY", getCharacters(false)));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Pull away",
							"Pull away from Murk's hand, signalling that you'd prefer to be transformed into a [style.colourFeminine(female)] milker.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFeminine, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_FEMALE", getCharacters(false)));
						}
					};
					
				} else if(index==2) {
					return new Response("Hump hand",
							"Hump Murk's hand, signalling that you'd prefer to be transformed into a [style.colourFeminineStrong(futa)] milker.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFuta, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_FUTA", getCharacters(false)));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_TF_CHOICE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "You don't have any choice but to wait in the stocks...", CAPTIVE_DAY_1_MORNING) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(1);
						milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_MORNING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(11*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING", getCharacters(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Endure",
						"Stay still and endure Murk's abuse...",
						CAPTIVE_DAY_1_LUNCH) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_ENDURE", getCharacters(true)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Clean",
						"Open your mouth as an offer to clean Murk's cock...",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_1_MORNING_AFTER_CLEAN,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_CLEAN", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==3) {
				return new Response("Resist",
						"Struggle and try to pull away from Murk's abuse...",
						CAPTIVE_DAY_1_LUNCH) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_RESIST", getCharacters(true)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_MORNING_AFTER_CLEAN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_AFTER_CLEAN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "You don't have any option other to wait in the stocks...", CAPTIVE_DAY_1_LUNCH);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_LUNCH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(13*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Eat",
						"Do as Murk says and eat the gruel...",
						CAPTIVE_DAY_1_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH_EAT"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse",
						"Refuse to eat the disgusting gruel...",
						CAPTIVE_DAY_1_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH_REFUSE"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_LUNCH_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "You don't have any option other to wait in the stocks...", CAPTIVE_DAY_1_AFTERNOON);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON"));
			Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(19*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Open wide", "Do as Murk says and swallow down the transformative potion...", CAPTIVE_DAY_1_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON_SWALLOW"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Resist", "Keep your mouth closed and try to resist drinking down the transformative potion!", CAPTIVE_DAY_1_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						equipRingGag(Main.game.getPlayer());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON_RESIST"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-10));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_AFTER_TRANSFORMATION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Recover", "With the transformations now at an end, you can start to recover...", CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Suck cock",
						"After having your mind altered by Murk's transformative potion, you find yourself hungering for a taste of Murk's cock...",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_1_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK_BLOWJOB", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(22*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Chained up", "You once again find yourself chained up...", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 1 (Max. obedience = 60) --------- //
	
	
	
	
	// --------- START OF DAY 2 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_2_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Obey", "Get into your milking stall so that Murk can fasten your restraints...", CAPTIVE_DAY_2_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE_OBEY", getCharacters(false)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
					return new Response("Resist", "You are too obedient to even think about refusing Murk's command!", null);
				}
				return new Response("Resist", "Try and resist being locked into your milking stall...", CAPTIVE_DAY_2_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE_RESIST", getCharacters(false)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_LOCKED_IN_STALL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "You don't have any option other than to wait in the stocks...", CAPTIVE_DAY_2_BLOWJOB) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(2);
						milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_BLOWJOB = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(10*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_BLOWJOB", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_THREE_DISCIPLINED)) {
					return new Response("Clean", "By now you're so obedient that you can't consider doing anything but eagerly cleaning Murk's cock!", null);
				}
				return new ResponseSex(
						"Clean",
						"You can't help but want a taste of Murk's delicious cock...",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_2_POTION,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_BLOWJOB_START", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Eagerly clean",
						"You're absolutely desperate to get a taste of Murk's delicious cock!",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_2_POTION,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_BLOWJOB_START_EAGER", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_POTION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Eagerly beg", "You find yourself desperately wanting to be transformed, and so you eagerly beg to receive a taste of Murk's potion...", CAPTIVE_DAY_2_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
					return new Response("Reluctantly beg", "You are too obedient to do anything other than eagerly beg for the transformation potion!", null);
				}
				return new Response("Reluctantly beg", "You find yourself desperately wanting to be transformed, but that doesn't mean you should be too enthusiastic about it...", CAPTIVE_DAY_2_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTER_POTION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "You don't have any option other to wait in the stocks...", CAPTIVE_DAY_2_LUNCH);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_LUNCH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(13*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Eat",
						"Do as Murk says and eat the gruel...",
						CAPTIVE_DAY_2_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_EAT"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
					return new Response("Refuse", "You are too obedient to even think about refusing to eat the gruel!", null);
				}
				return new Response("Refuse",
						"Refuse to eat the disgusting gruel...",
						CAPTIVE_DAY_2_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_REFUSE"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_2_LUNCH_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "You don't have any option other to wait in the stocks...", CAPTIVE_DAY_2_AFTERNOON) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(0);
						if(Main.game.isAnalContentEnabled()) {
							milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						} else {
							milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON", getMilkers()));
			Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
			playerGrewVagina = false;
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(17*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Eagerly beg", "You find yourself desperately wanting to be transformed, and so you eagerly beg to receive a taste of Murk's potion...", CAPTIVE_DAY_2_AFTER_POTION) {
					@Override
					public void effects() {
						boolean vagina = Main.game.getPlayer().hasVagina();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
						if(!vagina && Main.game.getPlayer().hasVagina()) {
							playerGrewVagina = true;
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
					return new Response("Reluctantly beg", "You are too obedient to do anything other than eagerly beg for the transformation potion!", null);
				}
				return new Response("Reluctantly beg", "You find yourself desperately wanting to be transformed, but that doesn't mean you should be too enthusiastic about it...", CAPTIVE_DAY_2_AFTER_POTION) {
					@Override
					public void effects() {
						boolean vagina = Main.game.getPlayer().hasVagina();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
						if(!vagina && Main.game.getPlayer().hasVagina()) {
							playerGrewVagina = true;
						}
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Groped", "Murk spends some time groping your new body...", CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(playerGrewVagina), true);
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Training", "Murk continues with the final stage of your obedience training...", CAPTIVE_DAY_2_EVENING_TRAINING);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_EVENING_TRAINING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLastTimeOrgasmed(Main.game.getMinutesPassed()+30);
			GameCharacter milker = getMilkers().get(3);
			milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			getMurk().fillCumToMaxStorage();
			if(Main.game.isAnalContentEnabled()) {
				milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			} else {
				milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			}
			if(Main.game.getPlayer().hasPenis()) {
				Main.game.getPlayer().addDirtySlot(InventorySlot.PENIS);
			}
			if(Main.game.getPlayer().hasVagina()) {
				Main.game.getPlayer().addDirtySlot(InventorySlot.VAGINA);
			}
			Main.game.getPlayer().addDirtySlot(InventorySlot.ANUS);
			Main.game.getPlayer().addDirtySlot(InventorySlot.LEG);
			Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Submit", "Tell Murk that you're his submissive milker bitch and that you can't wait to be fucked tomorrow.", CAPTIVE_DAY_2_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_BEG"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
					return new Response("Refuse", "You are too obedient to refuse Murk's orders!", null);
				}
				return new Response("Refuse", "Despite your overwhelming lust, you refuse to tell Murk that you're his submissive milker bitch.", CAPTIVE_DAY_2_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_REFUSE"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(22*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Chained up", "You once again find yourself chained up...", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 2 (max obedience = 100) --------- //
	
	
	// --------- START OF DAY 3 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_3_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Obey", "Get into your milking stall so that Murk can fasten your restraints...", CAPTIVE_DAY_3_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE_OBEY", getCharacters(false)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
					return new Response("Resist", "You are too obedient to even think about refusing Murk's command!", null);
				}
				return new Response("Resist", "Try and resist being locked into your milking stall...", CAPTIVE_DAY_3_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE_RESIST", getCharacters(false)));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LOCKED_IN_STALL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait for Murk to return with your final transformative potion...", CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Eagerly kiss", "You find yourself desperately wanting to be transformed, and so you eagerly kiss Murk's cock and tell him that you've fallen in love with it...", CAPTIVE_DAY_3_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
					return new Response("Reluctantly kiss", "You are too obedient to do anything other than eagerly beg for the transformation potion!", null);
				}
				return new Response("Reluctantly kiss", "You find yourself desperately wanting to be transformed, but that doesn't mean you should be too enthusiastic about it...", CAPTIVE_DAY_3_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_POTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Milked", "Murk moves to hook you up to the milking machine...", CAPTIVE_DAY_3_MILKING);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_MILKING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_THREE_DISCIPLINED)) {
					return new Response("Beg", "You're so obedient that you can't bring yourself to do anything but completely submit to Murk!", null);
				}
				return new ResponseSex(
						"Beg",
						"You're far too turned on to do anything other than beg for Murk to finally fuck you...",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.BEHIND_MILKING_STALL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_FIRST_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_FIRST_SEX")) { //TODO include virginity loss (hymen, pure virgin)
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Fully submit",
						"Tell Murk that you want him to completely break you and turn you into his submissive milker bitch!",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_FIRST_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_FIRST_SEX_EAGER")) { //TODO include virginity loss (hymen, pure virgin)
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(15));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_FIRST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_FIRST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Manually milked", "Murk spends some time manually milking you...", CAPTIVE_DAY_3_MANUALLY_MILKED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_MANUALLY_MILKED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MANUALLY_MILKED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_THREE_DISCIPLINED)) {
					return new Response("Beg", "You're so obedient that you can't bring yourself to do anything but completely submit to Murk!", null);
				}
				return new ResponseSex(
						"Beg",
						"You're far too turned on to do anything other than beg for Murk to fuck you...",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.BEHIND_MILKING_STALL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()&&!Main.game.isAnalContentEnabled()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_SECOND_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MANUALLY_MILKED_SEX")) { //TODO include virginity loss (hymen, pure virgin)
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Fully submit",
						"Tell Murk that you're his submissive milker bitch and that you need him to break you!",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()&&!Main.game.isAnalContentEnabled()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_SECOND_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MANUALLY_MILKED_SEX_EAGER")) { //TODO include virginity loss (hymen, pure virgin)
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(15));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_SECOND_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SECOND_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lunch time", "Murk leaves you to be milked while he goes off to get lunch ready...", CAPTIVE_DAY_3_MANUALLY_MILKED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_LUNCH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(13*60)*60; //TODO sex could have gone past this?
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_THREE_DISCIPLINED)) {
					return new Response("Eat", "You are so obedient that you can't think of doing anything other than trying to earn some dessert!", null);
				}
				return new Response("Eat",
						"Do as Murk says and eat the gruel...",
						CAPTIVE_DAY_3_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_EAT"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
					return new Response("Refuse", "You are too obedient to even think about refusing to eat the gruel!", null);
				}
				return new Response("Refuse",
						"Refuse to eat the disgusting gruel...",
						CAPTIVE_DAY_3_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_REFUSE"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
				
			} else if(index==3) {
				return new ResponseSex(
						"Dessert",
						"Quickly eat the gruel so that you can earn a delicious dessert from Murk...",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_3_LUNCH_AFTER_DESSERT,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_DESSERT")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LUNCH_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Enjoy the sensation of being milked as you wait for Murk to return...", CAPTIVE_DAY_3_AFTERNOON);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LUNCH_AFTER_DESSERT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_AFTER_DESSERT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Milked", "Enjoy the sensation of being milked as you wait for Murk to return...", CAPTIVE_DAY_3_AFTERNOON);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_THREE_DISCIPLINED)) {
					return new Response("Beg", "You're so obedient that you can't bring yourself to do anything but completely submit to Murk!", null);
				}
				return new ResponseSex(
						"Beg",
						"You're far too turned on to do anything other than beg for Murk to fuck you again...",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.BEHIND_MILKING_STALL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Fully submit",
						"Tell Murk that you're his submissive milker bitch and to use you as his cock-sleeve!",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SEX_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(15));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Milked", "Enjoy the sensation of being milked as you wait for Murk to return again...", CAPTIVE_DAY_3_AFTERNOON_END);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(20*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_THREE_DISCIPLINED)) {
					return new Response("Beg", "You're so obedient that you can't bring yourself to do anything but completely submit to Murk!", null);
				}
				return new ResponseSex(
						"Beg",
						"You're far too turned on to do anything other than beg for Murk to fuck you again...",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.BEHIND_MILKING_STALL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()&&!Main.game.isAnalContentEnabled()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_END_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Fully submit",
						"Tell Murk that you love being his submissive milker bitch!",
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()&&!Main.game.isAnalContentEnabled()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_END_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_SEX_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(15));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_END_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait as Murk releases the other milkers from their stalls...", CAPTIVE_DAY_3_EVENING);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_EVENING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_EVENING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_MILKER_EVENING.getResponse(responseTab, index);
		}
	};
	
	
	// --------- END OF DAY 3 --------- //

	
	// --------- GENERIC LOOP FROM DAY 4 ONWARDS --------- //
	
	
	public static final DialogueNode CAPTIVE_MILKER_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_WAKE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				// Silence delivers
				if(index==1) {
					return new Response("Birthing", "[pc.PetName(murk)] notices that you're ready to give birth...", CAPTIVE_GIVE_BIRTH) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							Main.game.getPlayer().setMana(0);
							
							if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
								Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
								Main.game.getPlayer().incrementVaginaCapacity(
										(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
										false);
							}
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) { // If birthing side quest is not complete, remove it, as otherwise completion (referencing Lily) doesn't make any sense.
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY);
							}
						}
					};
				}
				
			} else {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
					if(index==1) {
						return new Response("Obey", "Get into your milking stall so that [pc.petName(murk)] can start milking you.", CAPTIVE_MILKER_LOCKED_IN_STALL) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_START_BROKEN"));
							}
						};
					}
					
				} else {
					if(index==1) {
						return new Response("Obey", "Get into your milking stall so that [pc.petName(murk)] can fasten your restraints and start milking you...", CAPTIVE_MILKER_LOCKED_IN_STALL) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_START_OBEY", getCharacters(false)));
								Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
							}
						};
						
					} else if(index==2) {
						if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
							return new Response("Resist", "You are too obedient to even think about refusing [pc.petName(murk)]'s command!", null);
						}
						return new Response("Resist", "Try and resist being locked into your milking stall...", CAPTIVE_MILKER_LOCKED_IN_STALL) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_START_RESIST", getCharacters(false)));
								Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
							}
						};
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_LOCKED_IN_STALL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
				
			} else {
				
			}
			//TODO Begging for a fuck when Murk returns or not (chcen to be fucked)
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_MORNING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO random milking event
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
				
			} else {
				
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_LUNCH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(13*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			 //TODO chance to earn dessert, with failed chance resulting in Murk giving you a bowl of leftover 'dessert' from giving it to a different milker
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
				if(index==1) {
					return new ResponseSex(
							"Dessert",
							"Quickly eat the gruel so that you can earn a delicious dessert from [pc.petName(murk)]!",
							true,
							false,
							getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
							null,
							null,
							CAPTIVE_DAY_3_LUNCH_AFTER_DESSERT,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_BROKEN")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_THREE_DISCIPLINED)) {
						return new Response("Eat", "You are so obedient that you can't think of doing anything other than trying to earn some dessert!", null);
					}
					return new Response("Eat",
							"Do as Murk says and eat the gruel...",
							CAPTIVE_DAY_3_LUNCH_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_EAT"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_END"));
							Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
						return new Response("Refuse", "You are too obedient to even think about refusing to eat the gruel!", null);
					}
					return new Response("Refuse",
							"Refuse to eat the disgusting gruel...",
							CAPTIVE_DAY_3_LUNCH_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_REFUSE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_END"));
							Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
						}
					};
					
				} else if(index==3) {
					return new ResponseSex(
							"Dessert",
							"Quickly eat the gruel so that you can earn a delicious dessert from Murk...",
							true,
							false,
							getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
							null,
							null,
							CAPTIVE_DAY_3_LUNCH_AFTER_DESSERT,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_DESSERT")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_LUNCH_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO Random event 1
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_AFTERNOON_EVENT_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO Random event 2
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_AFTERNOON_EVENT_2_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO Waiting
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_EVENING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(22*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
				if(index==1) {
					return new Response("Kiss cock", "Show your complete devotion to [pc.petName(murk)] by refusing to escape and instead crawling over to kiss his cock.", CAPTIVE_MILKER_DAY_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_EVENING_BROKEN"));
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getObedienceValue()>=100 && Main.game.isBadEndsEnabled()) {
						return new Response("Submit", "You are completely broken and cannot think of doing anything other than fully capitulating to Murk...", null);
					}
					return new Response("Submit", "Tell Murk that you're his submissive milker bitch and that you can't wait to be fucked tomorrow.", CAPTIVE_MILKER_DAY_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_EVENING_SUBMIT"));
							Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getPlayer().getObedienceValue()>=100 && Main.game.isBadEndsEnabled()) {
						return new Response("Refuse", "You are completely broken and cannot think of doing anything other than fully capitulating to Murk...", null);
					}
					if(Main.game.getPlayer().getObedience().isGreaterThan(ObedienceLevel.POSITIVE_ONE_AGREEABLE)) {
						return new Response("Refuse", "You are too obedient to refuse Murk's orders!", null);
					}
					return new Response("Refuse", "Despite your overwhelming lust, you refuse to tell Murk that you're his submissive milker bitch.", CAPTIVE_MILKER_DAY_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_EVENING_REFUSE"));
							Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-10));
						}
					};
					
				} else if(index==3 && Main.game.isBadEndsEnabled()) {
					if(Main.game.getPlayer().getObedience().isLessThan(ObedienceLevel.POSITIVE_FOUR_DUTIFUL)) {
						return new Response("Fully capitulate", "You are not yet obedient enough to fully capitulate to Murk...", null);
					}
					return new Response("Fully capitulate",
							"Tell Murk that you're his submissive milker slut and that you'll do anything for him!"
								+ "<br/>[style.boldBadEnd(BAD END!)]"
								+ "<br/>You will be unable to attempt to escape after this!",
							CAPTIVE_MILKER_FULL_CAPITULATION) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_BAD_END;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(50));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.badEnd, true);
							Main.getProperties().badEndTitle = "Murk's Milker";
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_DAY_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Chained up", "You once again find yourself chained up...", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_MILKER_FULL_CAPITULATION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_FULL_CAPITULATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Master", "Decide to call Murk your Master from now on...", CAPTIVE_MILKER_FULL_CAPITULATION_FUCKED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_EVENING_TRAINING_CAPITULATION_MASTER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(50));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkMaster, true);
						Main.game.getPlayer().setPetName(Main.game.getNpc(Murk.class), "Master");
					}
				};
				
			} else if(index==2) {
				return new Response("Daddy", "Decide to call Murk your Daddy from now on...", CAPTIVE_MILKER_FULL_CAPITULATION_FUCKED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_EVENING_TRAINING_CAPITULATION_DADDY"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(50));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkDaddy, true);
						Main.game.getPlayer().setPetName(Main.game.getNpc(Murk.class), "Daddy");
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_FULL_CAPITULATION_FUCKED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_FULL_CAPITULATION_FUCKED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Spread legs",
						"Spread your legs for [pc.petName(murk)] and let him mating press you.",
						true,
						false,
						getPlayerMurkSM(SexPosition.LYING_DOWN,
								SexSlotLyingDown.MATING_PRESS,
								SexSlotLyingDown.LYING_DOWN,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_MILKER_AFTER_CAPITULATION_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_FULL_CAPITULATION_FUCKED_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_MILKER_AFTER_CAPITULATION_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_MILKER_AFTER_CAPITULATION_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Chained up", "You once again find yourself chained up...", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF GENERIC LOOP --------- //

	
	// --------- MISCELLANEOUS DIALOGUES --------- //
	
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			//TODO set location
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH"));
			
			if(Main.game.getPlayer().getVaginaType().isEggLayer()) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_END_EGGS"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getVaginaType().isEggLayer()) {
					return new Response("Protect the eggs!", "Protect your eggs from these rats!", CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						}
					};
				} else {
					return new Response("Rest", "You spend some time recovering from your ordeal...", CAPTIVE_GIVE_BIRTH_FINISHED) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 24*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Rest", "You spend some time recovering from your ordeal...", CAPTIVE_GIVE_BIRTH_FINISHED);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_GIVE_BIRTH_FINISHED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			Main.game.getNpc(Silence.class).returnToHome();
			Main.game.getNpc(Shadow.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(isTransformationFinished()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_FINISHED", getCharacters(false));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_INITIAL_FINISHED", getCharacters(false)));
				sb.append(CAPTIVE_DAY_1_WAKE.getContent());
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isTransformationFinished()) {
				return CAPTIVE_MILKER_WAKE.getResponse(responseTab, index);
			} else {
				return CAPTIVE_DAY_1_WAKE.getResponse(responseTab, index);
			}
		}
	};
	
	
	
	
	
	
	public static final DialogueNode CAPTIVE_WAITING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			int hours = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)
					?1+Util.random.nextInt(4) // 1-4 Hours
					:3+Util.random.nextInt(4); // 3-7 hours
			int seconds = (hours)*60*60;
			int progression = seconds + Main.game.getDaySeconds();
			if(progression > 22*60*60) { // Do not pass 22:10:
				seconds = seconds - (progression - 22*60*60);
				seconds += 10*60;
			}
			return seconds; 
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			switch(playerInteraction) {
				case MILKING:
				case PUNISHMENT:
					if(index==1) {
						return new Response("Wait",
								UtilText.parse(getMurk(), "Having had [npc.her] fun, [npc.name] heads back to the milk storage room, leaving you to wait in the stocks..."),
								Main.game.isExtendedWorkTime()
									?CAPTIVE_WAITING
									:CAPTIVE_NIGHT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_MURK_LEAVES", getCharacters(false)));
								getMurk().returnToHome();
								if(Main.game.isExtendedWorkTime()) {
									applyWaitingEffects();
								}
							}
						};
					}
					break;
					
				case TEASE:
				case INSPECTION: //TODO split with different responses
					if(index==1) {
						return new Response(
								"Continue",
								playerMurkSex
									?"Stay quiet and wait for Murk to leave..."
									:"Murk isn't interested in doing anything else with you right now, and turns around to leave...",
								Main.game.isExtendedWorkTime()
									?CAPTIVE_WAITING
									:CAPTIVE_NIGHT) {
							@Override
							public void effects() {
								if(playerMurkSex) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_REFUSE_TO_BEG", getCharacters(false)));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_WAITING_MURK_LEAVES", getCharacters(false)));
								}
								getMurk().returnToHome();
								if(Main.game.isExtendedWorkTime()) {
									applyWaitingEffects();
								}
							}
						};
						
					} else if(index==2 && playerMurkSex) {
						return getPlayerOwnerSexResponse("Beg for it",
								UtilText.parse(getMurk(), "Now that [npc.name] has turned you on so much, you can't help but beg for [npc.herHim] to fuck you!"),
								CAPTIVE_AFTER_SEX);
					}
					break;
					
				case RAT_SEX:
					if(index==1) {
						return getPlayerMilkingStallSexResponse("Fucked",
								UtilText.parse(getCharacters(false).get(0), "There's nothing you can do to stop the rat from having [npc.her] way with you..."),
								CAPTIVE_AFTER_SEX, 
								Util.newHashMapOfValues(
										new Value<>(getCharacters(false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
								"CAPTIVE_WAITING_SOLO_SEX");
					}
					break;
					
				case RAT_SEX_THREESOME:
					if(index==1) {
						return getPlayerMilkingStallSexResponse("Fucked",
								UtilText.parse(getCharacters(false).get(0), "There's nothing you can do to stop the rats from having their way with you..."),
								CAPTIVE_AFTER_SEX, 
								Util.newHashMapOfValues(
										new Value<>(getCharacters(false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL),
										new Value<>(getCharacters(false).get(1), SexSlotMilkingStall.RECEIVING_ORAL)),
								"CAPTIVE_WAITING_THREESOME_SEX");
					}
					break;
					
				case MURK_SEX:
					if(index==1) {
						return getPlayerOwnerSexResponse("Beg for it",
								UtilText.parse(getMurk(), "Now that [npc.name] has turned you on so much, you can't help but beg for [npc.herHim] to fuck you!"),
								CAPTIVE_AFTER_SEX);
					}
					break;
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			if(Main.sex.getDominantParticipants(false).containsKey(getMurk())) {
				return UtilText.parse(getMurk(), "[npc.NameHasFull] finished with you for now...");
			}
			if(Main.sex.getDominantParticipants(false).size()>1) {
				return "The rats have finished with you...";
			}
			return UtilText.parse(getCharacters(false), "[npc.Name] has finished with you...");
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getDominantParticipants(false).containsKey(getMurk())) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_AFTER_SEX_MURK", getCharacters(false));
				
			} else if(Main.sex.getDominantParticipants(false).size()>1) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_AFTER_SEX_THREESOME", getCharacters(false));
				
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_AFTER_SEX_SOLO", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait",
						"You can't do anything about your situation at the moment, other than wait to see what happens to you next...",
						Main.game.isExtendedWorkTime()
							?CAPTIVE_WAITING
							:CAPTIVE_NIGHT) {
					@Override
					public void effects() {
						banishRats();
						if(Main.sex.getDominantParticipants(false).containsKey(getMurk())) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveOwnerSex, true);
						}
						getMurk().returnToHome();
						if(Main.game.isExtendedWorkTime()) {
							applyWaitingEffects();
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_NIGHT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {//TODO clean slots
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Fall asleep...", getSleepNode());
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut)) {
					return new Response("Call out", "Murk won't pay attention to you if you call out again. You'll have to wait until tomorrow night...", null);
				}
				return new Response("Call out", "Call out for Murk...", CAPTIVE_CALL_OUT);
				
			} else if(index==3) {
				//TODO disable for bad end
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)>=80) {
					return new Response("Break lock", "Use your raw physical power to break free of the lock...", CAPTIVE_BROKEN_FREE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_BREAK_LOCK", getCharacters(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("Break lock", "You are not strong enough to break the lock...<br/>[style.italicsMinorBad(Required at least 80 "+Attribute.MAJOR_PHYSIQUE.getName()+"...)]", null);
				
			} else if(index==4) {
				//TODO disable for bad end
				if(Main.game.getPlayer().hasSpell(Spell.FIREBALL)
						|| Main.game.getPlayer().hasSpell(Spell.ICE_SHARD)
						|| Main.game.getPlayer().hasSpell(Spell.SLAM)) {
					return new Response("Break lock (Spell)",
							"Spend some time channelling your arcane power in an attempt to overcome your slave collar's enchantment and cast a spell to break the lock on the stocks.",
							CAPTIVE_BROKEN_FREE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_BREAK_LOCK_SPELL", getCharacters(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("Break lock",
						"You do not know a spell that would be suitable to break the lock..."
								+ "<br/>[style.italicsMinorBad(Requires knowing one of the following spells: "+Spell.FIREBALL.getName()+"; "+Spell.ICE_SHARD.getName()+"; "+Spell.SLAM.getName()+".)]",
						null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						"Now that you're free of your chain, you can finally attack [murk.name]!",
						(NPC) getMurk(),
						Util.newHashMapOfValues(new Value<>(getMurk(), "[murk.speech(Yer gonna pay fer this!)] [murk.name] shouts as [murk.she] prepares to fight you."))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return getPlayerOwnerEscapeSexResponse(CAPTIVE_RELEASED_AFTER_SEX, "CAPTIVE_BROKEN_FREE_SUBMIT_ORAL", "CAPTIVE_BROKEN_FREE_SUBMIT_SEX");
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_CALL_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				//TODO obedience block
				return new Response("Feign choking", "Pretend to be choking in order to trick [murk.name] into releasing you...", CAPTIVE_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT_CHOKE", getCharacters(false)));
//						AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
//						if(collar!=null) {
//							Main.game.getPlayer().forceUnequipClothingIntoVoid(getMurk(), collar);
//						}
					}
				};
				
			} else if(index==2) {
				//TODO obedience unlock
				if(!Main.game.getPlayer().hasPerkAnywhereInTree(Perk.CONVINCING_REQUESTS)) {
					return new Response("Seduce",
							UtilText.parse(getMurk(),
									"You aren't convincing enough at seduction to attempt to trick [npc.name] into taking your collar off..."
									+ "<br/>[style.italicsMinorBad(Requires the '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' perk.)]"),
							null);
				}
				return new Response("Seduce",
						UtilText.parse(getMurk(),
								"Tell [npc.name] that you're desperate for sex in an attempt to trick [npc.herHim] into taking your collar off..."
								+ "<br/>[style.italicsMinorGood(Unlocked from having the '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' perk.)]"),
						CAPTIVE_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT_SEDUCE", getCharacters(false)));
//						AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
//						if(collar!=null) {
//							Main.game.getPlayer().forceUnequipClothingIntoVoid(getMurk(), collar);
//						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_CALL_OUT_RELEASED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						UtilText.parse(getMurk(), "Now that you're free of your collar, you can finally attack [npc.name]!"),
						null,
						(NPC) getMurk(),
						Util.newArrayListOfValues(getMurk()),
						Util.newHashMapOfValues(new Value<>(getMurk(), "[npc.speech(Y-Yer gonna pay fer this!)] [npc.name] shouts in panic, wielding his bat ."))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return getPlayerOwnerEscapeSexResponse(CAPTIVE_RELEASED_AFTER_SEX, "CAPTIVE_CALL_OUT_RELEASED_ORAL", "CAPTIVE_CALL_OUT_RELEASED_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Locked up", "Let Murk lock you back into the stocks...", CAPTIVE_CALL_OUT_END_LOCKED_UP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP", getCharacters(false)));
						equipCollar(Main.game.getPlayer(), getMurk(), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
				
			} else if(index==2) {
				return new Response("Offer company", "Offer to sleep with Murk, which would give you the opportunity to sneak out and escape...", CAPTIVE_RELEASED_OFFER_COMPANY);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Slip away", "Use this opportunity to slip away and attempt to escape...", CAPTIVE_ESCAPING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_SLIP_AWAY", getCharacters(false)));
						restoreInventories();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Stay", "Stay with Murk until it's time for you to be locked back into the stocks...", CAPTIVE_CALL_OUT_END_LOCKED_UP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_LOCKED_UP", getCharacters(false)));
						equipCollar(Main.game.getPlayer(), getMurk(), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_CALL_OUT_END_LOCKED_UP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Fall asleep...", getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPE_FIGHT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_VICTORY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Tunnels", "Head back through the tunnels and escape from the Rat Warrens.", CAPTIVE_ESCAPING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_VICTORY_ESCAPING", getCharacters(false)));
						restoreInventories();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPE_FIGHT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_DEFEAT", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(CAPTIVE_AFTER_DEFEAT_SEX, "DEFEAT_SEX_ORAL_START", "DEFEAT_SEX_START");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_AFTER_DEFEAT_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getMurk(), "[npc.NameHasFull] finished with you for now...");
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_AFTER_DEFEAT_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep",
						"Completely exhausted from your fight and the subsequent sex, you soon find yourself falling asleep...",
						getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Shadow and Silence as they lead you out of the Rat Warrens.", ESCAPING) {
					@Override
					public void effects() {
						RatWarrensCaptiveDialogue.restoreInventories();
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ESCAPING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "ESCAPING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Escape", "Follow Shadow through the tunnels.", RatWarrensDialogue.POST_CAPTIVITY_SWORD_RAID) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
						Main.game.getNpc(Silence.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
					}
				};
			}
			return null;
		}
	};
	
}
