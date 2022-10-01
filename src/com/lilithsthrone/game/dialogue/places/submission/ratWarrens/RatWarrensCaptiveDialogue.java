package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.persona.NameTriplet;
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
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
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
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
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
 * @version 0.3.9
 * @author Innoxia
 */
public class RatWarrensCaptiveDialogue {
	
	private static boolean playerGrewVagina;
	public static int murkOrgasmsRequired = 1;
	
	private static List<GameCharacter> getCharacters(boolean includeMilkers) {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> npc.isUnique() || (!includeMilkers && (npc instanceof RatWarrensCaptive)));
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
	
	private static void spawnRat(boolean vaginaNeeded, boolean penisNeeded) {
		List<String> adjectives = new ArrayList<>();
		try {
			String[] names = new String[] {"thug", "gangster", "gang-member", "mobster"};
			NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(vaginaNeeded, penisNeeded));
			Main.game.addNPC(rat, false);
			rat.setLevel(4+Util.random.nextInt(5));
			rat.setLocation(Main.game.getPlayer(), true);
			adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, Util.randomItemFrom(names), adjectives));
		} catch (Exception e) {
			e.printStackTrace();
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
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", collarColour, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_GUNMETAL, false);
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		return character.equipClothingFromNowhere(collar, true, equipper);
	}
	
	private static ResponseSex getPlayerOwnerEscapeSexResponse(boolean lyingDown, DialogueNode node, String nodePathHandjob, String nodePathOral, String nodePathSex) {
		AbstractSexPosition position;
		Value<SexSlot, SexType> murkSexInfo;
		SexSlot playerSlot;
		String sexIntroTextPath;
		String responseTitle;
		String responseDescription;
		int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
		
		if(lyingDown) {
			if(stage==0) {
				responseTitle = "Handjob";
				responseDescription = "Do as Murk says and give him a handjob...";
				sexIntroTextPath = nodePathHandjob;
				position = SexPosition.LYING_DOWN;
				murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER));
				playerSlot = SexSlotLyingDown.BESIDE;
				
			} else if(stage>=4) {
				sexIntroTextPath = nodePathSex;
				responseTitle = "Ride cock";
				responseDescription = "Do as your Master says and ride his cock...";
				if(Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs()) {
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.COWGIRL_REVERSE;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.COWGIRL_REVERSE;
					}
					
				} else {
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.COWGIRL;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.COWGIRL;
					}
				}
				
			} else {
				responseTitle = "Kiss cock";
				responseDescription = "Do as Murk says and kiss his cock...";
				sexIntroTextPath = nodePathOral;
				position = SexPosition.LYING_DOWN;
				murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
				playerSlot = SexSlotLyingDown.MISSIONARY_ORAL;
			}
			
		} else {
			if(stage==0) {
				responseTitle = "Kneel";
				responseDescription = "Do as Murk says and kneel down before him so that you can give him a handjob...";
				sexIntroTextPath = nodePathHandjob;
				position = SexPosition.STANDING;
				murkSexInfo = new Value<>(SexSlotStanding.STANDING_DOMINANT, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER));
				playerSlot = SexSlotStanding.PERFORMING_ORAL;
				
			} else if(stage>=4) {
				sexIntroTextPath = nodePathSex;
				if(Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs()) {
					responseTitle = "Humped";
					responseDescription = "Do as your Master says and present yourself to him so that he can give you a good humping...";
					position = SexPosition.ALL_FOURS;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotAllFours.HUMPING, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotAllFours.ALL_FOURS;
						
					} else {
						murkSexInfo = new Value<>(SexSlotAllFours.HUMPING, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotAllFours.ALL_FOURS;
					}
					
				} else {
					responseTitle = "Lie back";
					responseDescription = "Do as your Master says and lie down before him so that he can give you a good fucking...";
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.LYING_DOWN;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.LYING_DOWN;
					}
				}
				
			} else {
				responseTitle = "Get down";
				responseDescription = "Do as Murk says and drop down before him so that you can suck his cock...";
				sexIntroTextPath = nodePathOral;
				position = SexPosition.ALL_FOURS;
				murkSexInfo = new Value<>(SexSlotAllFours.IN_FRONT, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
				playerSlot = SexSlotAllFours.ALL_FOURS;
			}
		}
		
		return new ResponseSex(
				responseTitle,
				responseDescription,
				true,
				false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(new Value<>(getMurk(), murkSexInfo.getKey())),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
					@Override
					public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
						return false;
					}
					@Override
					public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
						if(!character.isPlayer()) {
							if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH || murkSexInfo.getValue().getTargetedSexArea()==SexAreaPenetration.FINGER) {
								return OrgasmBehaviour.PULL_OUT;
							}
							return OrgasmBehaviour.CREAMPIE;
						}
						return super.getCharacterOrgasmBehaviour(character);
					}
					@Override
					public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
						if(!character.isPlayer()) {
							return OrgasmCumTarget.FACE;
						}
						return null;
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
						if(character.isPlayer()) {
							return false;
						}
						return super.isAbleToRemoveOthersClothing(character, clothing);
					}
					@Override
					public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
						return !equippingCharacter.isPlayer();
					}
					@Override
					public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
						if(!performer.isPlayer()
								&& (murkSexInfo.getValue().getPerformingSexArea()==SexAreaPenetration.PENIS || murkSexInfo.getValue().getPerformingSexArea()==SexAreaOrifice.VAGINA)) {
							return Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA);
						}
						return new ArrayList<>();
					}
					@Override
					public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getForeplayPreference(character, targetedCharacter);
						}
						return murkSexInfo.getValue();
					}
					@Override
					public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getMainSexPreference(character, targetedCharacter);
						}
						return murkSexInfo.getValue();
					}
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", sexIntroTextPath, getCharacters(false))) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					
				} else if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));

				} else if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					
				} else {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
					
				}
			}
		};
	}

	private static SexManagerDefault getPlayerMurkMilkingStallSM(SexSlot murkSlot, SexType murkSexPreference, int murkOrgasms) {
		return getPlayerMurkMilkingStallSM(murkSlot, murkSexPreference, murkOrgasms, null);
	}

	private static SexManagerDefault getPlayerMurkMilkingStallSM(SexSlot murkSlot, SexType murkSexPreference, int murkOrgasms, SexPace startingSexPace) {
		return getBasicSexManager(SexPosition.MILKING_STALL,
				Util.newHashMapOfValues(new Value<>(getMurk(), murkSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
				murkSexPreference, murkOrgasms, startingSexPace, false);
	}
	
	private static SexManagerDefault getBasicSexManager(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives,
			SexType murkSexPreference,
			int murkOrgasms,
			SexPace startingSexPace,
			boolean spitOnAsshole) {
		if(murkOrgasms>0) {
			murkOrgasmsRequired = murkOrgasms;
		}
		return new SexManagerDefault(position, dominants, submissives) {
			@Override
			public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(murkOrgasms>0 && partner.equals(getMurk())) {
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
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
				for(GameCharacter dom : dominants.keySet()) {
					map.put(dom, Util.newArrayListOfValues(CoverableArea.PENIS));
				}
				return map;
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return false;
			}
			@Override
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(spitOnAsshole) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getPlayer(), new HashMap<>());
					map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, Util.newHashMapOfValues(new Value<>(getMurk(), Util.newHashSetOfValues(LubricationType.SALIVA))));
					return map;
				}
				return super.getStartingWetAreas();
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
	
	private static void applyWakingEffects() {
		getMurk().setLocation(Main.game.getPlayer(), false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveWashed, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
		for(GameCharacter milker : getMilkers()) {
			((RatWarrensCaptive)milker).applyMilkingEquipment(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
		}
	}
	
	private static void applyPlayerMilkingPumps(boolean equip, List<InventorySlot> slots) {
		GameCharacter player = Main.game.getPlayer();
		if(equip) {
			// It really doens't make any narrative sense for the game's lactation content setting to limit the entire purpose of Murk's milkers. Instead of preventing milking, the lactation content setting just limits descriptions of it.
			if(slots.contains(InventorySlot.NIPPLE) && player.hasBreasts()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, player);
			}
			if(slots.contains(InventorySlot.STOMACH) && player.hasBreastsCrotch()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.STOMACH, true, player);
			}
			if(slots.contains(InventorySlot.PENIS) && player.hasPenis()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_penis_pump"), false), true, player);
			}
			if(slots.contains(InventorySlot.VAGINA) && player.hasVagina()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, player);
			}
			
		} else {
			for(AbstractClothing c : new ArrayList<>(player.getClothingCurrentlyEquipped())) {
				if(c.isMilkingEquipment() && slots.contains(c.getSlotEquippedTo())) {
					player.unequipClothingIntoVoid(c, true, player);
				}
			}
		}
	}
	
	public static void restoreInventories() {
		int essences = Main.game.getPlayer().getEssenceCount();
		Main.game.getPlayer().setInventory(Main.game.getSavedInventories().get(Main.game.getPlayer().getId()));
		Main.game.getPlayer().setEssenceCount(essences);
	}
	
	public static boolean isTransformationFinished() {
		CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
		return playerTf==null;
	}
	
	private static DialogueNode getSleepNode() {
		if(!isTransformationFinished()) {
			int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
			if(stage==0) {
				return CAPTIVE_DAY_1_WAKE;
			}
			if(stage==1) {
				return CAPTIVE_DAY_2_WAKE;
			}
			if(stage==3) {
				return CAPTIVE_DAY_3_WAKE;
			}
		}
		return BAD_END;
	}
	
	private static boolean isMasculineTransform() {
		return Main.getProperties().getForcedTFTendency().isMasculine()
				&& Main.game.isAnalContentEnabled();
	}

	private static String getObedienceResponseDescription(float increment) {
		String descriptor = "";
		if(increment<-10) {
			descriptor = "[style.italicsMinorGood(significantly less)]";
		} else if(increment<5) {
			descriptor = "[style.italicsBad(less)]";
		} else if(increment<0) {
			descriptor = "[style.italicsMinorBad(slightly less)]";
		} else if(increment<=5) {
			descriptor = "[style.italicsMinorGood(slightly more)]";
		} else if(increment<=10) {
			descriptor = "[style.italicsGood(more)]";
		} else {
			descriptor = "[style.italicsExcellent(significantly more)]";
		}
		return "<br/>"
				+ "<i>You will become "+descriptor+" [style.boldObedience(obedient)]!</i>";
	}
	
	private static String incrementPlayerObedience(int increment) {
		Main.game.getPlayer().incrementObedience(increment);
		
		int obedience = (int) Units.round(Main.game.getPlayer().getObedienceValue(), 0);
		if(obedience<0) {
			Main.game.getPlayer().setObedience(0);
			obedience = 0;
		}
		ObedienceLevel level = Main.game.getPlayer().getObedience();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
			if(increment!=0) {
				sb.append("You "+(increment>0?"[style.boldGrow(gained)]":"[style.boldShrink(lost)]")+" <b>"+Math.abs(increment)+"</b> [style.boldObedience(obedience)]!<br/>");
			}
			sb.append("You are <span style='color:"+level.getColour().toWebHexString()+";'>"+level.getName()+"</span>"
					+ " (<span style='color:"+level.getColour().toWebHexString()+";'>"+obedience+"</span>/100), ");
			switch(level) {
				case ZERO_FREE_WILLED:
				case POSITIVE_ONE_AGREEABLE:
				case POSITIVE_TWO_OBEDIENT:
					sb.append("and [style.colourMinorGood(can refuse or obey any of Murk's orders)]!");
					sb.append("<br/><i>At "+ObedienceLevel.POSITIVE_THREE_DISCIPLINED.getMinimumValue()+"/100, you will be unable to refuse Murk's orders!</i>");
					break;
				case POSITIVE_THREE_DISCIPLINED:
				case POSITIVE_FOUR_DUTIFUL:
					sb.append("and [style.colourMinorBad(cannot bring yourself to refuse Murk's orders)]!");
					sb.append("<br/><i>At "+ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMinimumValue()+"/100, you will do absolutely anything to please Murk!</i>");
					break;
				case POSITIVE_FIVE_SUBSERVIENT:
					if(obedience>=100) {
						sb.append("and [style.colourBadEnd(have accepted your fate as one of Murk's milkers)]!");
					} else {
						sb.append("and [style.colourBad(will do absolutely anything to please Murk)]!");
						sb.append("<br/><i>At 100/100, you will willingly accept your fate as one of Murk's milkers!</i>");
					}
					break;
				default:
					break;
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static boolean isPlayerObeyingOrders(boolean extremeSubCheck) {
		return Main.game.getPlayer().getObedience().isGreaterThan(extremeSubCheck?ObedienceLevel.POSITIVE_FOUR_DUTIFUL:ObedienceLevel.POSITIVE_TWO_OBEDIENT);
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
						"Stay quiet and hope that Murk leaves you alone."
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_QUIET"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
					}
				};
				
			} else if(index==2) {
				return new Response("Thank him",
						"Say thank you to Murk."
								+getObedienceResponseDescription(10),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_THANK_HIM"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
					}
				};
				
			} else if(index==3) {
				return new Response("Insult him",
						"Tell Murk to fuck off."
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_INSULT"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
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
					return new Response("Wait", "Wait for Murk to return...", CAPTIVE_GIVE_BIRTH) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							boolean eggs = !Main.game.getPlayer().getIncubatingLitters().isEmpty();
							if(eggs) {
								for(SexAreaOrifice orifice : new ArrayList<>(Main.game.getPlayer().getIncubatingLitters().keySet())) {
									Main.game.getPlayer().endIncubationPregnancy(orifice, true);
								}
							}
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
							if(eggs && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION);
							}
						}
					};
				}
				
			} else if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
				if(index==1) {
					return new Response("Wait", "Wait for Murk to return...", CAPTIVE_LAY_EGGS) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							for(SexAreaOrifice orifice : new ArrayList<>(Main.game.getPlayer().getIncubatingLitters().keySet())) {
								Main.game.getPlayer().endIncubationPregnancy(orifice, true);
							}
							Main.game.getPlayer().setMana(0);
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) { // If birthing side quest is not complete, remove it, as otherwise completion (referencing Lily) doesn't make any sense.
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION);
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Sleep",
							"Completely exhausted, you find yourself drifting off to sleep...",
							CAPTIVE_NIGHT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_SLEEP_START"));
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
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_PREP"));
			if(isMasculineTransform()) {
				sb.append( UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_MASCULINE"));
			} else {
				sb.append( UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE"));
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
						public Colour getHighlightColour() {
							return PresetColour.MASCULINE_PLUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveMasculine, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_MASCULINE"));
						}
					};
					
				} else if(index==2) {
					return new Response("Push back",
							"Raise your hips and push your ass back into [murk.namePos] cock, which will make him want to transform you into an [style.colourAndrogynous(androgynous)] sissy cum-milker.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.ANDROGYNOUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveSissy, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_SISSY"));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Pull away",
							"Pull away from Murk's hand, signalling that you'd prefer to be transformed into a [style.colourFeminineStrong(female)] milker.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE_PLUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFeminine, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_FEMALE"));
						}
					};
					
				} else if(index==2) {
					return new Response("Hump hand",
							"Hump Murk's hand, signalling that you'd prefer to be transformed into a [style.colourFeminine(futa)] milker.",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFuta, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_FUTA"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_TF_CHOICE = new DialogueNode("", "", true, true) {
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
			return Main.game.getMinutesUntilTimeInMinutes(11*60 + (Util.random.nextInt(45)))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Say hello",
						"Say hello to Murk's cock in the hopes that he'll then leave and end his abuse."
							+getObedienceResponseDescription(5),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_ENDURE", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new Response("Compliment",
						"Say hello to Murk's cock and compliment it on its size and potent musk."
							+getObedienceResponseDescription(10),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_COMPLIMENT", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==3) {
				return new Response("Resist",
						"Refuse to play along with Murk's game and try to pull away from his cock."
							+getObedienceResponseDescription(-10),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_RESIST", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_MORNING_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
				return new Response("Wait",
						"You have no choice other than to wait for Murk's return...",
						CAPTIVE_DAY_1_LUNCH);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_LUNCH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
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
						"Do as Murk says and eat the gruel..."
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_1_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH_EAT"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse",
						"Refuse to eat the disgusting gruel..."
								+getObedienceResponseDescription(-10),
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

	public static final DialogueNode CAPTIVE_DAY_1_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
			getMurk().setLocation(Main.game.getPlayer(), false);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON"));
			Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Open wide",
						"Do as Murk says and swallow down the transformative potion..."
								+getObedienceResponseDescription(10),
						CAPTIVE_DAY_1_AFTER_TRANSFORMATION) {
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
				return new Response("Resist",
						"Keep your mouth closed and try to resist drinking down the transformative potion!"
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_1_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
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
	
	public static final DialogueNode CAPTIVE_DAY_1_AFTER_TRANSFORMATION = new DialogueNode("", "", true, true) {
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
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Open wide",
						"You can't help but want to suck Murk's throbbing cock, but that doesn't mean you have to admit that you're in love with it..."
								+getObedienceResponseDescription(5),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_1_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkCaptiveBlowjob, true);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Love cock",
						"Play along with Murk by telling his cock that you've fallen in love with it and are eager to share your first kiss together."
								+getObedienceResponseDescription(15),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_1_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK_BLOWJOB_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkCaptiveBlowjob, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_END = new DialogueNode("Finished", "Now that he's filled your belly with his hot, musky cum, Murk has had enough...", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
				return new Response("Obey",
						"Get into your milking stall so that Murk can fasten your restraints..."
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE_OBEY"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Resist", "You are too obedient to even think about refusing Murk's command!", null);
				}
				return new Response("Resist",
						"Try and resist being locked into your milking stall..."
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE_RESIST"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_LOCKED_IN_STALL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
				return new Response("Wait", "You don't have any option other than to wait in the stocks...", CAPTIVE_DAY_2_MORNING) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(2);
						milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_MORNING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(10*60 + Util.random.nextInt(30))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						"Kiss",
						"Do as Murk says and kiss his musky, cum-coated cock."
							+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new Response(
						"Eagerly kiss",
						"Tell Murk that you're in love with his cock as you kiss it."
							+getObedienceResponseDescription(10),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS_EAGER"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==3) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Refuse", "You're so obedient that you can't bring yourself to disobey Murk...", null);
				}
				return new Response(
						"Refuse",
						"Refuse to kiss Murk's stinky cock."
							+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS_REFUSE"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
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
				return new Response("Eagerly beg",
						"You find yourself desperately wanting to be transformed, and so you eagerly beg to receive a taste of Murk's potion..."
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_AFTER_POTION) {
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
				if(isPlayerObeyingOrders(false)) {
					return new Response("Reluctantly beg", "You are too obedient to do anything other than eagerly beg for the transformation potion!", null);
				}
				return new Response("Reluctantly beg",
						"You find yourself desperately wanting to be transformed, but that doesn't mean you should be too enthusiastic about it...",
						CAPTIVE_DAY_2_AFTER_POTION) {
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
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTER_POTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(isPlayerObeyingOrders(true)) {
					return new Response("Suck cock", "By now you're so obedient that you can't consider doing anything but completely submitting to Murk's cock!", null);
				}
				return new ResponseSex(
						"Suck cock",
						"While you don't want to admit that you've fallen in love with Murk's cock, you still want to get a taste of it..."
							+getObedienceResponseDescription(5),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_2_AFTER_BLOWJOB,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION_BLOWJOB")) {
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
						"Submit to cock",
						"Tell Murk that you're madly in love with his delicious cock, and that you want it to dominate your throat!"
							+getObedienceResponseDescription(10),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_2_AFTER_BLOWJOB,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION_BLOWJOB_EAGER")) {
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
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTER_BLOWJOB = new DialogueNode("Finished", "Murk has had enough of fucking your throat...", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_BLOWJOB");
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
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
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
						"Do as Murk says and eat the gruel..."
							+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_EAT"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Refuse", "You are too obedient to even think about refusing to eat the gruel!", null);
				}
				return new Response("Refuse",
						"Refuse to eat the disgusting gruel..."
							+getObedienceResponseDescription(-10),
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

	public static final DialogueNode CAPTIVE_DAY_2_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
			getMurk().setLocation(Main.game.getPlayer(), false);
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
				return new Response("Eagerly beg",
						"You find yourself desperately wanting to be transformed, and so you eagerly beg to receive a taste of Murk's potion..."
								+getObedienceResponseDescription(5),
								CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION) {
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
				if(isPlayerObeyingOrders(false)) {
					return new Response("Reluctantly beg", "You are too obedient to do anything other than eagerly beg for the transformation potion!", null);
				}
				return new Response("Reluctantly beg",
						"You find yourself desperately wanting to be transformed, but that doesn't mean you should be too enthusiastic about it...",
						CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION) {
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
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION = new DialogueNode("", "", true, true) {
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
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED = new DialogueNode("", "", true, true) {
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
				return new Response("Training", "Murk continues with the final stage of your obedience training...", CAPTIVE_DAY_2_EVENING_TRAINING_START);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_EVENING_TRAINING_START = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60 + Util.random.nextInt(30))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Beg for it",
						"Desperately plead for Murk to fuck you instead of the milker."
								+getObedienceResponseDescription(10),
								CAPTIVE_DAY_2_EVENING_TRAINING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START_BEG"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Stay silent", "You are too obedient to ignore Murk, and find yourself unable to think of doing anything other than begging for him to fuck you!", null);
				}
				return new Response("Stay silent",
						"Despite your overwhelming lust, you refuse to beg for Murk to fuck you."
								+getObedienceResponseDescription(-10),
								CAPTIVE_DAY_2_EVENING_TRAINING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START_REFUSE"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-5));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_EVENING_TRAINING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed()+(60*30));
			GameCharacter milker = getMilkers().get(3);
			milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Submit",
						"Agree with Murk that you're a submissive milker slut who needs to be rutted by his fat cock."
							+getObedienceResponseDescription(10),
						CAPTIVE_DAY_2_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_BEG"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Resist", "You are too obedient to refuse Murk's orders!", null);
				}
				return new Response("Resist",
						"Do your best to resist your urge to submit to Murk and just stay silent."
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_END) {
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
	
	public static final DialogueNode CAPTIVE_DAY_2_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			GameCharacter milker = getMilkers().get(3);
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
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_END", getMilkers());
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
				return new Response("Obey",
						"Get into your milking stall so that Murk can fasten your restraints..."
							+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE_OBEY"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Resist", "You are too obedient to even think about refusing Murk's command!", null);
				}
				return new Response("Resist",
						"Try and resist being locked into your milking stall..."
								+ getObedienceResponseDescription(-10),
						CAPTIVE_DAY_3_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE_RESIST"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LOCKED_IN_STALL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
				return new Response("Wait", "Wait for Murk to return with your final transformative potion...", CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("Eagerly kiss",
						"You find yourself desperately wanting to be transformed, and so you eagerly kiss Murk's cock and tell him that you've fallen in love with it..."
								+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_AFTER_POTION) {
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
				if(isPlayerObeyingOrders(false)) {
					return new Response("Reluctantly kiss", "You are too obedient to do anything other than eagerly beg for the transformation potion!", null);
				}
				return new Response("Reluctantly kiss",
						"You find yourself desperately wanting to be transformed, but that doesn't mean you should be too enthusiastic about it...",
						CAPTIVE_DAY_3_AFTER_POTION) {
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
		public void applyPreParsingEffects() {
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.STOMACH, InventorySlot.PENIS)); // No vagina as Murk is going to fuck it first
		}
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
			if(Main.game.getPlayer().hasVagina()) {
				if(index==1) {
					return new Response("Drink",
							"You're so turned on that you can't think of doing anything other than gulping down the transformative liquid..."
								+ getObedienceResponseDescription(10),
							CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_DRINK"));
							Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
							Map<String, String> effects = CaptiveTransformation.FEMININE_PUSSY_FINAL.getEffects(Main.game.getPlayer());
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
				
			} else {
				return CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF.getResponse(responseTab, index);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"In love",
						"Admit to Murk that you've fallen in love with his cock and beg for him to fuck you!"
							+ getObedienceResponseDescription(25),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.HUMPING,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_FIRST_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_FIRST_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
					}
					@Override
					public void postSexInitEffects() {
						Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_FIRST_SEX = new DialogueNode("Finished", "Murk has finished giving you your first real experience with his fat, musky cock...", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().hasVagina()) {
				Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
				applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
			}
		}
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
				return new Response("Milked", "Murk leaves you to be milked by the machine as he swaggers off to check on the other milkers...", CAPTIVE_DAY_3_AFTER_SEX_MILKED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_SEX_MILKED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SEX_MILKED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Fully submit",
						Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
							?"Tell Murk that you're his submissive milker bitch and that you need him to break you!"
							:"Tell Murk that you're a filthy butt-slut milker and that you love his cock!"
								+ getObedienceResponseDescription(25),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getMurk(), SexSlotMilkingStall.HUMPING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								2,
								null,
								true),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_SECOND_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SEX_MILKED_SEX")) {
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
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
						applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
					}
					@Override
					public void postSexInitEffects() {
						if(Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()) {
							Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_SECOND_SEX = new DialogueNode("Finished", "Murk has finished giving you your 'second date' with his fat, musky cock...", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
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
				return new Response("Lunch time", "Murk leaves you to be milked while he goes off to get lunch ready...", CAPTIVE_DAY_3_LUNCH);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_LUNCH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(Math.max(Main.game.getHourOfDay()+1, 13)*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Eat",
						"Do as Murk says and eat the gruel..."
								+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_EAT"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) { // Should be impossible...
				return new Response("Refuse", "You are too obedient to even think about refusing to eat the gruel!", null);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			GameCharacter milker = Util.randomItemFrom(getMilkers());
			milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
			getMurk().returnToHome();
		}
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
				return new Response("Milked", "Enjoy the sensation of being milked as you wait for Murk to return...", CAPTIVE_DAY_3_AFTERNOON);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			spawnRat(false, true);
			getCharacters(false).get(0).setGenericName("gang-member");
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Beg for cock",
						UtilText.parse(getCharacters(false), "Beg for [npc.namePos] rat-cock so that [npc.she]'ll start fucking you.")
								+ getObedienceResponseDescription(10),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getCharacters(false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SEX", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(
								getCharacters(false).get(0), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
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

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharacters(false), "[npc.Name] feels as though [npc.sheHas] got [npc.her] money's worth out of you, and so brings an end to the sex...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX", getCharacters(false)));
			banishRats();
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
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
				return new Response("Milked", "Enjoy the sensation of being milked as you wait for Murk to return again...", CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			spawnRat(false, true);
			getCharacters(false).get(0).setGenericName("gang-member");
			getCharacters(false).get(0).addFetish(Fetish.FETISH_SADIST);
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(19*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Fucked again",
						UtilText.parse(getCharacters(false), "[npc.Name] doesn't seem to care about giving you any foreplay and just starts fucking you...")
								+ getObedienceResponseDescription(10),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getCharacters(false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX_START", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getCharacters(false).get(0), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
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

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharacters(false), "[npc.Name] [npc.has] had enough of fucking you, and so brings an end to the sex...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX", getCharacters(false)));
			banishRats();
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
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
				return new Response("Milked", "Enjoy the sensation of being milked as you wait for Murk to return again...", CAPTIVE_DAY_3_AFTERNOON_END);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA, InventorySlot.NIPPLE, InventorySlot.STOMACH, InventorySlot.PENIS));
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(Math.max(22, Main.game.getHourOfDay()+1)*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Submit", "Kneel down before Murk and beg for him to fuck you.", CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT);
				
			} else if(index==2) {
				return new Response("Escape", "The thought of trying to escape doesn't even cross your obedient milker mind...", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				boolean allFours = Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs();
				return new ResponseSex(
						allFours
							?"Present yourself"
							:"Spread legs",
						(allFours
							?"Present your pussy to your Master and tell him that his cock is your one true love!"
							:"Spread your legs for your Master and tell him that his cock is your one true love!")
							+ getObedienceResponseDescription(25),
						true,
						false,
						getBasicSexManager(
								allFours
									?SexPosition.ALL_FOURS
									:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(getMurk(),
										allFours
											?SexSlotAllFours.HUMPING
											:(Main.game.getPlayer().isVisiblyPregnant()
												?SexSlotLyingDown.MISSIONARY
												:SexSlotLyingDown.MATING_PRESS))),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(),
										allFours
											?SexSlotAllFours.ALL_FOURS
											:SexSlotLyingDown.LYING_DOWN)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true),
								allFours
									?null
									:new InitialSexActionInformation(getMurk(),
											Main.game.getPlayer(),
											TongueMouth.KISS_START,
											false,
											true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkMaster, true);
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
					}
					@Override
					public void postSexInitEffects() {
						Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
						if(Main.game.getPlayer().hasPenis()) {
							Main.game.getPlayer().applyOrgasmCumEffect();
							if(!allFours) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.CHEST);
							}
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_END = new DialogueNode("Finished", "Murk has had his fun by stuffing you full of his cum...", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Chained up", "You once again find yourself chained up...", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 3 --------- //
	
	
	
	
	// --------- NIGHT --------- //
	
	
	public static final DialogueNode CAPTIVE_NIGHT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			for(GameCharacter milker : getMilkers()) {
				((RatWarrensCaptive)milker).applyMilkingEquipment(false, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
			}
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())==0) {
				return Main.game.getMinutesUntilTimeInMinutes(01*60)*60; // First night
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			if(Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())==0
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "DAY_0_SLEEP"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_WARNING"));
			sb.append(incrementPlayerObedience(0));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
				if(stage>=4) {
					if(Main.game.isBadEndsEnabled()) {
						return new Response("Sleep",
								"Fall asleep..."
								+ "<br/>[style.boldBadEnd(BAD END:)] By failing to escape for the third night in a row, you will fully capitulate to Murk and accept your new life as one of his milkers!",
								CAPTIVE_NIGHT_SLEEP) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_BAD_END;
							}
						};
						
					} else {
						return new Response("Sleep", "Fall asleep...", CAPTIVE_NIGHT_SLEEP_RESCUED);
					}
				}
				return new Response("Sleep", "Fall asleep...", CAPTIVE_NIGHT_SLEEP);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveWashed)) {
					return new Response("Wash", "You've already washed yourself this evening...", null);
				}
				return new Response("Wash", "Use the shallow stream to wash your body.", CAPTIVE_NIGHT) {
					@Override
					public int getSecondsPassed() {
						return 10*60;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveWashed, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
						StringBuilder sb = new StringBuilder();
						GameCharacter player = Main.game.getPlayer();
						
						player.setHealth(player.getAttributeValue(Attribute.HEALTH_MAXIMUM));
						player.setMana(player.getAttributeValue(Attribute.MANA_MAXIMUM));
						
						sb.append(player.washAllOrifices(false));
						player.calculateStatusEffects(0);
						player.cleanAllDirtySlots(true);
						player.cleanAllClothing(false, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_WASH", getCharacters(false)));
						Main.game.getTextStartStringBuilder().append(sb.toString());
					}
				};
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut)) {
					return new Response("Call out", "Murk won't pay attention to you if you call out again. You'll have to wait until tomorrow night...", null);
				}
				return new Response("Call out", "Call out for Murk...", CAPTIVE_CALL_OUT);
				
			} else if(index==4) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Break lock", "You're too obedient to even consider trying to damage Murk's property!", null);
				}
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)>=PhysiqueLevel.THREE_POWERFUL.getMinimumValue()) {
					return new Response("Break lock", "Use your raw physical power to break free of the lock...", CAPTIVE_BROKEN_FREE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_BREAK_LOCK", getCharacters(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("Break lock", "You are not strong enough to break the lock...<br/>[style.italicsMinorBad(Requires at least "+PhysiqueLevel.THREE_POWERFUL.getMinimumValue()+" "+Attribute.MAJOR_PHYSIQUE.getName()+"...)]", null);
				
			} else if(index==5) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("Break lock (Spell)", "You're too obedient to even consider trying to damage Murk's property!", null);
				}
				if(Main.game.getPlayer().hasSpell(Spell.FIREBALL)
						|| Main.game.getPlayer().hasSpell(Spell.ICE_SHARD)
						|| Main.game.getPlayer().hasSpell(Spell.SLAM)) {
					return new Response("Break lock (Spell)",
							"Spend some time channelling your arcane power in an attempt to overcome your slave collar's enchantment and cast a spell to break the lock on the stocks.",
							CAPTIVE_BROKEN_FREE) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
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
	
	public static final DialogueNode CAPTIVE_NIGHT_SLEEP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getHourOfDay()>0) {
				return Main.game.getMinutesUntilTimeInMinutes(Main.game.getHourOfDay()+1*60)*60;
			}
			return Main.game.getMinutesUntilTimeInMinutes(01*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_SLEEP", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wake up", "You wake up the next morning...", getSleepNode());
			}
			return null;
		}
	};
	
	
	
	// --------- MISCELLANEOUS DIALOGUES --------- //
	
	
	public static final DialogueNode BAD_END = new DialogueNode("[style.boldBadEnd(Bad End: Murk's Milker)]", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.badEnd, true);
			Main.getProperties().badEndTitle = "Murk's Milker";
			Main.game.getPlayer().setName(new NameTriplet("Horny milker"));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "BAD_END", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("The End...", "[style.italicsBadEnd(With this end to your journey, the thread of prophecy is severed. Restore a saved game to restore the weave of fate, or persist in the doomed world you have created.)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
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
			
			if(Main.game.getPlayer().isVaginaEggLayer()) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_END_EGGS"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isVaginaEggLayer()) {
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
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_INITIAL_FINISHED", getCharacters(false)));
			sb.append(CAPTIVE_NIGHT.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_NIGHT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_LAY_EGGS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
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

			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_LAY_EGGS"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Rest", "You spend some time recovering from your ordeal...", CAPTIVE_LAY_EGGS_FINISHED) {
					@Override
					public void effects() {
						Main.game.getNpc(Silence.class).returnToHome();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_LAY_EGGS_FINISHED = new DialogueNode("", "", true) {
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
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_LAY_EGGS_INITIAL_FINISHED", getCharacters(false)));
			sb.append(CAPTIVE_NIGHT.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_NIGHT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Attack",
						"Now that you're free of your chain, you can finally attack Murk!",
						(NPC) getMurk(),
						Util.newHashMapOfValues(new Value<>(getMurk(), "[murk.speech(Yer gonna pay fer this!)] Murk shouts as he prepares to fight you."))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Submit",
						"Do as Murk says and submit to him..."
								+getObedienceResponseDescription(10),
						CAPTIVE_BROKEN_FREE_SUBMIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_BROKEN_FREE_AFTER_SEX, "CAPTIVE_BROKEN_FREE_SUBMIT_HANDJOB", "CAPTIVE_BROKEN_FREE_SUBMIT_ORAL", "CAPTIVE_BROKEN_FREE_SUBMIT_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public String getDescription() {
			return "Murk has finished with you for now...";
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("Sleep", "Completely exhausted from sexually pleasuring Murk, you soon find yourself falling asleep...", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("Sleep",
						"Completely exhausted from sexually pleasuring Murk, you soon find yourself falling asleep...",
						getSleepNode());
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_CALL_OUT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
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
				if(isPlayerObeyingOrders(false)) {
					return new Response("Feign choking", "You're too obedient to even consider trying to lie to Murk!", null);
				}
				return new Response("Feign choking", "Pretend to be choking in order to trick [murk.name] into releasing you...", CAPTIVE_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT_CHOKE", getCharacters(false)));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().hasTraitActivated(Perk.CONVINCING_REQUESTS) && !isPlayerObeyingOrders(false)) {
					return new Response("Seduce",
							UtilText.parse(getMurk(),
									"You aren't convincing enough at seduction to attempt to trick [npc.name] into taking your collar off..."
									+ "<br/>[style.italicsMinorBad(Requires the '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' trait to be active.)]"),
							null);
				}
				return new Response("Seduce",
						isPlayerObeyingOrders(false)
							?"Tell Murk that you're so desperate for sex that you can't sleep..."
							:"Tell Murk that you're desperate for sex in an attempt to trick him into taking your collar off..."
								+ "<br/>[style.italicsMinorGood(Unlocked from having the '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' trait activated.)]",
						CAPTIVE_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT_SEDUCE", getCharacters(false)));
						getMurk().returnToHome();
						Main.game.getPlayer().setLocation(getMurk(), false);
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
				if(isPlayerObeyingOrders(false)) {
					return new Response("Fight", "You're too obedient to even consider fighting Murk!", null);
				}
				return new ResponseCombat("Fight",
						UtilText.parse(getMurk(), "Now that you're free of your collar, you can finally attack [npc.name]!"),
						null,
						(NPC) getMurk(),
						Util.newArrayListOfValues(getMurk()),
						Util.newHashMapOfValues(new Value<>(getMurk(), "[npc.speech(Y-Yer gonna pay fer this!)] [npc.name] shouts in panic, wielding his bat."))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_RELEASED_AFTER_SEX, "CAPTIVE_CALL_OUT_RELEASED_HANDJOB", "CAPTIVE_CALL_OUT_RELEASED_ORAL", "CAPTIVE_CALL_OUT_RELEASED_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_AFTER_SEX = new DialogueNode("Finished", "Murk is finished with you...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
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
				return new Response("Locked up", "Let Murk lock you back into the stocks...", CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP);
				
			} else if(index==2) {
				return new Response("Offer company", "Offer to sleep with Murk, which would give you the opportunity to sneak out and escape...", CAPTIVE_RELEASED_OFFER_COMPANY);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("Sleep", "Fall asleep...", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("Sleep", "Fall asleep...", getSleepNode());
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
				return new Response("Stay", "Stay with Murk until it's time for you to be locked back into your milking stall...", CAPTIVE_RELEASED_OFFER_COMPANY_STAY);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY_STAY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_STAY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(true,
						CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX,
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_HANDJOB",
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_ORAL",
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("Sleep", "Fall asleep...", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
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
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_AFTER_DEFEAT_SEX, "DEFEAT_SEX_HANDJOB", "DEFEAT_SEX_ORAL", "DEFEAT_SEX_START");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_AFTER_DEFEAT_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
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
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("Sleep", "Completely exhausted from your fight and the subsequent sex, you soon find yourself falling asleep...", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("Sleep",
						"Completely exhausted from your fight and the subsequent sex, you soon find yourself falling asleep...",
						getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_NIGHT_SLEEP_RESCUED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 3*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_SLEEP_RESCUED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_ESCAPING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
		}
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
				return new Response("Escape", "Follow Shadow and Silence as they lead you out of the Rat Warrens.", ESCAPING) {
					@Override
					public void effects() {
						RatWarrensCaptiveDialogue.restoreInventories();
						Main.game.getPlayer().setCaptive(false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ESCAPING = new DialogueNode("", "", true, true) {
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
				return new Response("Follow", "Join Shadow and Silence in following Constable Adams to the nearest Enforcer post.", RatWarrensDialogue.POST_CAPTIVITY_SWORD_RAID) {
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
