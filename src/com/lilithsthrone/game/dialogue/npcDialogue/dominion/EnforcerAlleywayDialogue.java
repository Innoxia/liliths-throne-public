package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.EnforcerPatrol;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.8.3
 * @version 0.3.8.3
 * @author Innoxia
 */
public class EnforcerAlleywayDialogue {
	
	private static boolean searched = false;
	private static boolean demonRevealed = false;
	private static boolean hadSex = false;
	
	public static boolean isDemonRevealed() {
		return demonRevealed;
	}

	public static void setDemonRevealed(boolean demonRevealed) {
		EnforcerAlleywayDialogue.demonRevealed = demonRevealed;
	}
	
	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static boolean isKind() {
		return getEnforcerLeader().hasPersonalityTrait(PersonalityTrait.KIND);
	}
	
	private static GameCharacter getEnforcerLeader() {
		return getEnforcers().get(0);
	}
	
	private static GameCharacter getEnforcerSubordinate() {
		return getEnforcers().get(1);
	}
	
	private static List<GameCharacter> getEnforcers() {
		List<GameCharacter> enforcers = new ArrayList<>();
		enforcers.addAll(Main.game.getCharactersPresent());
		enforcers.removeIf((character) -> !(character instanceof EnforcerPatrol));
		Collections.sort(enforcers, (c1, c2) -> c2.getLevel()-c1.getLevel());
		return enforcers;
	}
	
	private static List<GameCharacter> getEnforcersAndCriminal() {
		List<GameCharacter> characters = new ArrayList<>(getEnforcers());
		characters.add(getCriminalInTile());
		return characters;
	}
	
	public static void banishEnforcers() {
		for(GameCharacter enforcer : getEnforcers()) {
			for(Entry<AbstractItem, Integer> entry : new HashMap<>(enforcer.getAllItemsInInventory()).entrySet()) {
				if(entry.getKey().getItemType()==ItemType.CONDOM_USED) {
					enforcer.dropItem(entry.getKey(), entry.getValue(), false);
				}
			}
			Main.game.banishNPC((NPC) enforcer);
		}
	}
	
	private static GameCharacter getCriminalInTile() {
		for(GameCharacter ch : Main.game.getCharactersPresent()) {
			if(((ch instanceof DominionAlleywayAttacker) || ch instanceof NPCOffspring) && !Main.game.getPlayer().getCompanions().contains(ch)) {
				return ch;
			}
		}
		return null;
	}

	private static void banishCriminal() {
		Main.game.banishNPC((NPC) getCriminalInTile());
	}
	
	private static SexManagerDefault getSexManager(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> domSlots,
			Map<GameCharacter, SexSlot> subSlots,
			SexType preference,
			SexType preferenceCompanion,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(true,
				position,
				domSlots,
				subSlots) {
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter character){
				return false;
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return false;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isCharacterStartNaked(GameCharacter character) {
				return character.isPlayer() || (isCompanionDialogue() && character.equals(getMainCompanion()));
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					if(character.equals(getEnforcerLeader())) {
						return preference;
					} else if(character.equals(getEnforcerSubordinate())) {
						return preferenceCompanion;
					}
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return character.getMainSexPreference(targetedCharacter);
			}
		};
	}

	private static SexType getWantedSexType(GameCharacter enforcer, GameCharacter target) {
		/* Involves:
			Penis-Vagina
			Penis-Anus
			Finger-Vagina
			Finger-anus + Finger-penis
		*/
		Map<SexType, Integer> sexTypeMap = new HashMap<>();
		
		if(enforcer.hasPenis()) {
			if(target.hasVagina() && target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				sexTypeMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), 20);
			}
			if(Main.game.isAnalContentEnabled() && target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
				sexTypeMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS), 10);
			}
		}
		if(target.hasVagina() && target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			sexTypeMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA), 5);
		}
		if(Main.game.isAnalContentEnabled() && target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
				&& target.hasPenisIgnoreDildo() && target.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			sexTypeMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS), 5);
		}
		
		if(!sexTypeMap.isEmpty()) {
			return Util.getRandomObjectFromWeightedMap(sexTypeMap);
		}
		
		return null;
	}
	
	private static SexActionInterface getSexActionStartingType(SexType sexType) {
		if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
			return PenisVagina.PENIS_FUCKING_START;
			
		} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
			return PenisAnus.PENIS_FUCKING_START;
			
		} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))) {
			return FingerVagina.FINGERING_START;
			
		} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS))) {
			return FingerAnus.ANAL_FINGERING_START;
		}
		return null;
	}
	
	private static ResponseSex getEnforcerSexResponse(String title,
			String description,
			SexType sexType,
			SexType sexTypeCompanion,
			boolean consensual,
			DialogueNode postSexNode) {
		GameCharacter leader = getEnforcerLeader();
		GameCharacter subordinate = getEnforcerSubordinate();

		if(sexType==null) {
			if(sexTypeCompanion.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
				return new ResponseSex(title,
						description,
						consensual, false,
						getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(leader, SexSlotAgainstWall.STANDING_WALL)),
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotAgainstWall.FACE_TO_WALL)),
								sexTypeCompanion,
								null,
								Util.newHashMapOfValues(new Value<>(leader, Util.newArrayListOfValues(CoverableArea.PENIS)))),
						Util.newArrayListOfValues(subordinate),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						postSexNode,
						UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_COMPANION_START", getEnforcers())
							+ UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_COMPANION_START_PENIS_PUSSY", getEnforcers())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(leader, getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						leader.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, leader);
					}
				};
				
			} else if(sexTypeCompanion.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
				return new ResponseSex(title,
						description,
						consensual, false,
						getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(leader, SexSlotAgainstWall.STANDING_WALL)),
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotAgainstWall.FACE_TO_WALL)),
								sexTypeCompanion,
								null,
								Util.newHashMapOfValues(new Value<>(leader, Util.newArrayListOfValues(CoverableArea.PENIS)))),
						Util.newArrayListOfValues(subordinate),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						postSexNode,
						UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_COMPANION_START", getEnforcers())
							+ UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_COMPANION_START_PENIS_ASSHOLE", getEnforcers())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(leader, getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						leader.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, leader);
					}
				};
				
			} else if(sexTypeCompanion.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))) {
				return new ResponseSex(title,
						description,
						consensual, false,
						getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(leader, SexSlotAgainstWall.STANDING_WALL)),
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotAgainstWall.FACE_TO_WALL)),
								sexTypeCompanion,
								null,
								Util.newHashMapOfValues()),
						Util.newArrayListOfValues(subordinate),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						postSexNode,
						UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_COMPANION_START", getEnforcers())
							+ UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_COMPANION_START_FINGER_PUSSY", getEnforcers())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(leader, getMainCompanion(), FingerVagina.FINGERING_START, false, true));
					}
				};
				
			} else if(sexTypeCompanion.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS))) {
				return new ResponseSex(title,
						description,
						consensual, false,
						getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(leader, SexSlotAgainstWall.STANDING_WALL)),
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotAgainstWall.FACE_TO_WALL)),
								sexTypeCompanion,
								null,
								Util.newHashMapOfValues()),
						Util.newArrayListOfValues(subordinate),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						postSexNode,
						UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_COMPANION_START", getEnforcers())
							+ UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_COMPANION_START_FINGER_ASSHOLE", getEnforcers())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(leader, getMainCompanion(), FingerAnus.ANAL_FINGERING_START, false, true),
								new InitialSexActionInformation(leader, getMainCompanion(), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
		
		} else {
			boolean companionAnalFingering = sexTypeCompanion!=null && sexTypeCompanion.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS));
			
			if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
				return new ResponseSex(title,
						description,
						consensual, false,
						getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(
										new Value<>(leader, SexSlotAgainstWall.STANDING_WALL),
										sexTypeCompanion!=null
											?new Value<>(subordinate, SexSlotAgainstWall.STANDING_WALL_TWO)
											:null),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL),
										sexTypeCompanion!=null
											?new Value<>(subordinate, SexSlotAgainstWall.FACE_TO_WALL_TWO)
											:null),
								sexType,
								sexTypeCompanion,
								Util.newHashMapOfValues(new Value<>(leader, Util.newArrayListOfValues(CoverableArea.PENIS)))),
						Util.newArrayListOfValues(subordinate),
						Main.game.getPlayer().getCompanions(),
						postSexNode,
						UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START", getEnforcers())
							+ UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START_PENIS_PUSSY", getEnforcers())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						list.add(new InitialSexActionInformation(leader, Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						if(isCompanionDialogue()) {
							list.add(new InitialSexActionInformation(leader, getMainCompanion(), getSexActionStartingType(sexTypeCompanion), false, true));
							if(companionAnalFingering) {
								list.add(new InitialSexActionInformation(leader, getMainCompanion(), FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						}
						return list;
					}
					@Override
					public void effects() {
						leader.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, leader);
						if(isCompanionDialogue() && sexTypeCompanion!=null && sexTypeCompanion.getPerformingSexArea()==SexAreaPenetration.PENIS) {
							subordinate.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, subordinate);
						}
					}
				};
				
			} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
				return new ResponseSex(title,
						description,
						consensual, false,
						getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(
										new Value<>(leader, SexSlotAgainstWall.STANDING_WALL),
										sexTypeCompanion!=null
											?new Value<>(subordinate, SexSlotAgainstWall.STANDING_WALL_TWO)
											:null),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL),
										sexTypeCompanion!=null
											?new Value<>(subordinate, SexSlotAgainstWall.FACE_TO_WALL_TWO)
											:null),
								sexType,
								sexTypeCompanion,
								Util.newHashMapOfValues(new Value<>(leader, Util.newArrayListOfValues(CoverableArea.PENIS)))),
						Util.newArrayListOfValues(subordinate),
						Main.game.getPlayer().getCompanions(),
						postSexNode,
						UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START", getEnforcers())
							+ UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START_PENIS_ASSHOLE", getEnforcers())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						list.add(new InitialSexActionInformation(leader, Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						if(isCompanionDialogue()) {
							list.add(new InitialSexActionInformation(leader, getMainCompanion(), getSexActionStartingType(sexTypeCompanion), false, true));
							if(companionAnalFingering) {
								list.add(new InitialSexActionInformation(leader, getMainCompanion(), FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						}
						return list;
					}
					@Override
					public void effects() {
						leader.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, leader);
						if(isCompanionDialogue() && sexTypeCompanion!=null && sexTypeCompanion.getPerformingSexArea()==SexAreaPenetration.PENIS) {
							subordinate.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, subordinate);
						}
					}
				};
				
			} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))) {
				return new ResponseSex(title,
						description,
						consensual, false,
						getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(
										new Value<>(leader, SexSlotAgainstWall.STANDING_WALL),
										sexTypeCompanion!=null
											?new Value<>(subordinate, SexSlotAgainstWall.STANDING_WALL_TWO)
											:null),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL),
										sexTypeCompanion!=null
											?new Value<>(subordinate, SexSlotAgainstWall.FACE_TO_WALL_TWO)
											:null),
								sexType,
								sexTypeCompanion,
								Util.newHashMapOfValues()),
						Util.newArrayListOfValues(subordinate),
						Main.game.getPlayer().getCompanions(),
						postSexNode,
						UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START", getEnforcers())
							+ UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START_FINGER_PUSSY", getEnforcers())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						list.add(new InitialSexActionInformation(leader, Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true));
						if(isCompanionDialogue()) {
							list.add(new InitialSexActionInformation(leader, getMainCompanion(), getSexActionStartingType(sexTypeCompanion), false, true));
							if(companionAnalFingering) {
								list.add(new InitialSexActionInformation(leader, getMainCompanion(), FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						}
						return list;
					}
					@Override
					public void effects() {
						if(isCompanionDialogue() && sexTypeCompanion!=null && sexTypeCompanion.getPerformingSexArea()==SexAreaPenetration.PENIS) {
							subordinate.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, subordinate);
						}
					}
				};
				
			} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS))) {
				return new ResponseSex(title,
						description,
						consensual, false,
						getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(
										new Value<>(leader, SexSlotAgainstWall.STANDING_WALL),
										sexTypeCompanion!=null
											?new Value<>(subordinate, SexSlotAgainstWall.STANDING_WALL_TWO)
											:null),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL),
										sexTypeCompanion!=null
											?new Value<>(subordinate, SexSlotAgainstWall.FACE_TO_WALL_TWO)
											:null),
								sexType,
								sexTypeCompanion,
								Util.newHashMapOfValues()),
						Util.newArrayListOfValues(subordinate),
						Main.game.getPlayer().getCompanions(),
						postSexNode,
						UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START", getEnforcers())
							+ UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START_FINGER_ASSHOLE", getEnforcers())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						list.add(new InitialSexActionInformation(leader, Main.game.getPlayer(), FingerAnus.ANAL_FINGERING_START, false, true));
						list.add(new InitialSexActionInformation(leader, Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
						if(isCompanionDialogue()) {
							list.add(new InitialSexActionInformation(leader, getMainCompanion(), getSexActionStartingType(sexTypeCompanion), false, true));
							if(companionAnalFingering) {
								list.add(new InitialSexActionInformation(leader, getMainCompanion(), FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						}
						return list;
					}
					@Override
					public void effects() {
						if(isCompanionDialogue() && sexTypeCompanion!=null && sexTypeCompanion.getPerformingSexArea()==SexAreaPenetration.PENIS) {
							subordinate.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, subordinate);
						}
					}
				};
			}
		}
		return null;
	}
	
	public static final DialogueNode ENFORCER_ALLEYWAY_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			searched = false;
			demonRevealed = Main.game.getPlayer().getRace()==Race.DEMON;
			hadSex = false;
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.SET_ENFORCER)
						&& (Main.game.getPlayer().getRace()==Race.HUMAN || !Main.game.isDayTime())
						&& !demonRevealed
						&& !searched) {
					return new Response("Continue", "The Enforcers aren't going to let you go without searching you first...", null);
				} else {
					return new Response("Continue", "Tell the Enforcers that you don't have any criminal activity to report to them and continue on your way.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							if(hadSex) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START_CONTINUE_HAD_SEX", getEnforcers()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START_CONTINUE", getEnforcers()));
							}
							banishEnforcers();
						}
					};
				}
				
			} if(index==2) {
				if(searched) {
					return new Response("Searched", "The Enforcers have already searched you, and so aren't interested in doing so again...", null);
					
				} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.SET_ENFORCER)) {
					return new Response("Searched", "As the Enforcers think that you're one of them, they have absolutely no interest in searching you.", null);
					
				} else {
					return new Response("Searched",
							((Main.game.getPlayer().getRace()==Race.HUMAN || !Main.game.isDayTime()) && !demonRevealed)
								?"Do as the Enforcers say and submit to a pat-down search so that they can confirm that you're not up to no good..."
								:"Tell the Enforcers that you think that they should do their job and give you a pat-down search to make sure that you're not up to no good...",
							ENFORCER_ALLEYWAY_SEARCHED);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().getRace()!=Race.DEMON
						&& Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON
						&& !demonRevealed) {
					return new Response("Demonic reveal",
							"Transform into a demon in front of the Enforcers...",
							ENFORCER_ALLEYWAY_DEMON_REVEAL);
					
				} else if(demonRevealed) {
					if(hadSex) {
						return new Response(
								"Demonic seduction"
									+(isCompanionDialogue()
										?" (solo)"
										:""),
								UtilText.parse(getEnforcers(), "As [npc.name] has already had sex, [npc.sheIs] unwilling to spend any more time with you..."),
								null);
						
					} else if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
						return new Response(
								"Demonic seduction"
									+(isCompanionDialogue()
										?" (solo)"
										:""),
								UtilText.parse(getEnforcers(), "As [npc.name] is not attracted to you, [npc.sheIs] unwilling to have sex with you..."),
								null);
					} else {
						return new ResponseSex(
								"Demonic seduction"
									+(isCompanionDialogue()
										?" (solo)"
										:""),
								UtilText.parse(getEnforcers(), "Use the fact that you're a demon to seduce [npc.name]"
									+(getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
										?" and [npc2.name]."
										:"")),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(
												Main.game.getPlayer(),
												isCompanionDialogue()
													?getMainCompanion()
													:null),
										Util.newArrayListOfValues(
												getEnforcerLeader(),
												getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
													?getEnforcerSubordinate()
													:null),
										null,
										null),
								AFTER_SEX_DEMONIC_SEDUCTION,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START_DEMONIC_SEDUCTION"+(getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())?"_DOUBLE":""), getEnforcers())) {
							@Override
							public void effects() {
								hadSex = true;
							}
						};
					}
				}
				
			} else if(index==4 && demonRevealed && isCompanionDialogue()) {
				boolean withSubordinate = getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
						&& getEnforcerSubordinate().isAttractedTo(getMainCompanion())
						&& getMainCompanion().isAttractedTo(getEnforcerSubordinate());
				
				if(hadSex) {
					return new Response(
							"Demonic seduction",
							UtilText.parse(getEnforcers(), "As [npc.name] has already had sex, [npc.sheIs] unwilling to spend any more time with you..."),
							null);
					
				} else if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
					return new Response(
							"Demonic seduction",
							UtilText.parse(getEnforcers(), "As [npc.name] is not attracted to you, [npc.sheIs] unwilling to have sex with you..."),
							null);
					
				} else if(!getEnforcerLeader().isAttractedTo(getMainCompanion())) {
					return new Response(
							"Demonic seduction",
							UtilText.parse(getMainCompanion(), getEnforcerLeader(), "As [npc.name] is not attracted to [npc2.name], [npc.sheIs] unwilling to have sex with [npc2.herHim]..."),
							null);
					
				} else if(!getMainCompanion().isAttractedTo(Main.game.getPlayer())) {
					return new Response(
							"Demonic seduction",
							UtilText.parse(getMainCompanion(), "As [npc.name] is not attracted to you, [npc.sheIs] unwilling to have sex with you..."),
							null);
					
				} else if(!getMainCompanion().isAttractedTo(getEnforcerLeader())) {
					return new Response(
							"Demonic seduction",
							UtilText.parse(getMainCompanion(), getEnforcerLeader(), "As [npc.name] is not attracted to [npc2.name], [npc.sheIs] unwilling to have sex with [npc2.herHim]..."),
							null);
					
				} else {
					return new ResponseSex(
							"Demonic seduction"
								+(isCompanionDialogue()
									?" (solo)"
									:""),
							UtilText.parse(getEnforcers(), "Use the fact that you're a demon to seduce [npc.name]"
								+(withSubordinate
									?" and [npc2.name]."
									:"")),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											getMainCompanion()),
									Util.newArrayListOfValues(
											getEnforcerLeader(),
											withSubordinate
												?getEnforcerSubordinate()
												:null),
									null,
									null),
							AFTER_SEX_DEMONIC_SEDUCTION,
							UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START_DEMONIC_SEDUCTION_WITH_COMPANION"+(withSubordinate?"_DOUBLE":""), getEnforcers())) {
						@Override
						public void effects() {
							hadSex = true;
						}
					};
				}
				
			} else if(index==5) {
				return new ResponseCombat("Attack",
						"There's nobody around to come to their aid, so if you really wanted to, you could attack the Enforcers without fear of being overwhelmed by reinforcements...",
						(NPC)getEnforcerLeader(),
						getEnforcers(),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), "[pc.speech(I'm going to show you who's <i>really</i> in charge here!)] you declare, getting ready to attack the Enforcers."),
								new Value<>(getEnforcers().get(0), UtilText.parse(getEnforcers().get(0), "[npc.speech(You're in for it now!)] [npc.name] shouts.")),
								new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.speech(This is the last mistake you'll ever make!)] [npc.name] exclaims."))));

				
			} else if(index==10) {
				if(getCriminalInTile()==null) {
					return new Response("Report",
							"There are no criminals lurking in this area, so you have nothing to report to the Enforcers...",
							null);
					
				} else if(!getCriminalInTile().isAbleToBeEnslaved()) {
					return new Response(UtilText.parse(getCriminalInTile(), "Report ([npc.Name])"),
							UtilText.parse(getCriminalInTile(), "As [npc.name] is not a wanted criminal, you are unable to report them to the Enforcers..."),
							null);
					
				} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.SET_ENFORCER)
							&& (Main.game.getPlayer().getRace()==Race.HUMAN || !Main.game.isDayTime())
							&& !demonRevealed
							&& !searched) {
					return new Response(UtilText.parse(getCriminalInTile(), "Report ([npc.Name])"),
							"The Enforcers aren't interested in anything you have to report until after they've searched you...",
							null);
							
				} else {
					return new Response(UtilText.parse(getCriminalInTile(), "Report ([npc.Name])"),
							UtilText.parse(getCriminalInTile(), "Tell the Enforcers that [npc.name] is lurking somewhere in this area."
									+ "<br/>[style.italicsBad(This will permanently remove [npc.name] from the game!)]"),
							ENFORCER_ALLEYWAY_REPORT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
					};
				}
			}
			return null;
		}
	};
	
	private static SexType playerSexType;
	private static boolean enforcerWantsPlayerSex;
	private static SexType companionSexType;
	private static boolean enforcerWantsCompanionSex;
	
	public static final DialogueNode ENFORCER_ALLEYWAY_SEARCHED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			searched = true;
			playerSexType = getWantedSexType(getEnforcerLeader(), Main.game.getPlayer());
			enforcerWantsPlayerSex = getEnforcerLeader().isAttractedTo(Main.game.getPlayer());
			
			companionSexType = null;
			enforcerWantsCompanionSex = false;
			if(isCompanionDialogue()) {
				GameCharacter enforcer = playerSexType==null?getEnforcerLeader():getEnforcerSubordinate();
				companionSexType = getWantedSexType(enforcer, getMainCompanion());
				enforcerWantsCompanionSex = Main.game.isInvoluntaryNTREnabled()
								&& enforcer.isAttractedTo(getMainCompanion())
								&& (enforcer.isWillingToRape() || getMainCompanion().isAttractedTo(enforcer));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED", getEnforcers()));
			
			if((playerSexType!=null && enforcerWantsPlayerSex) && (companionSexType!=null && enforcerWantsCompanionSex)) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND_BOTH", getEnforcers()));
			} else if((playerSexType!=null && enforcerWantsPlayerSex)) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND", getEnforcers()));
			} else if(companionSexType!=null && enforcerWantsCompanionSex) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND_COMPANION", getEnforcers()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND_NONE", getEnforcers()));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if((playerSexType!=null && enforcerWantsPlayerSex) || (companionSexType!=null && enforcerWantsCompanionSex)) {
				if(index==1) {
					if(playerSexType!=null && enforcerWantsPlayerSex) {
						return getEnforcerSexResponse("Strip", UtilText.parse(getEnforcerLeader(), "Submit to [npc.namePos] 'strip search'..."), playerSexType, companionSexType, true, ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX);
					} else {
						return getEnforcerSexResponse("Watch", UtilText.parse(getEnforcerLeader(), "Watch as [com.name] strips naked and is 'searched' by [npc.name]..."), playerSexType, companionSexType, true, ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX);
					}
					
				} else if(index==2) {
					if((Main.game.getPlayer().getRace()==Race.HUMAN || !Main.game.isDayTime())
							&& !demonRevealed
							&& !isKind()
							&& getEnforcerLeader().isWillingToRape()) {
						if(playerSexType!=null && enforcerWantsPlayerSex) {
							return new ResponseCombat("Refuse",
									"Your refusal to strip for the Enforcers will no doubt raise their suspicions and result in them attempting to subdue you by force...",
									(NPC)getEnforcerLeader(),
									getEnforcers(),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), "[pc.speech(Get away from me!)] you shout, getting ready to defend yourself against the Enforcers."),
											new Value<>(getEnforcers().get(0), UtilText.parse(getEnforcers().get(0), "[npc.speech(I knew you were up to no good!)] [npc.name] shouts.")),
											new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.speech(This is the last mistake you'll ever make!)] [npc.name] exclaims."))));
						} else {
							return new ResponseCombat("Stop them",
									"Stop the Enforcers from stripping your [com.companion]. This will no doubt raise their suspicions and result in them attempting to subdue you by force...",
									(NPC)getEnforcerLeader(),
									getEnforcers(),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), "[pc.speech(Get away from [com.herHim]!)] you shout, getting ready to defend your [com.companion] against the Enforcers."),
											new Value<>(getEnforcers().get(0), UtilText.parse(getEnforcers().get(0), "[npc.speech(I knew you were up to no good!)] [npc.name] shouts.")),
											new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.speech(This is the last mistake you'll ever make!)] [npc.name] exclaims."))));
						}
						
					} else {
						if(playerSexType!=null && enforcerWantsPlayerSex) {
							return new Response("Refuse",
									isKind() || !getEnforcerLeader().isWillingToRape()
										?UtilText.parse(getEnforcerLeader(), "Although [npc.she] clearly wants to take advantage of you, [npc.name] isn't going to force you to strip...")
										:"As you are [pc.a_subspecies], the Enforcers won't force you to strip for them...",
									ENFORCER_ALLEYWAY_SEARCHED_STRIP_REFUSED);
						} else {
							return new Response("Stop them",
									isKind() || !getEnforcerLeader().isWillingToRape()
										?UtilText.parse(getEnforcerLeader(), "Although [npc.she] clearly wants to take advantage of your [com.companion], [npc.name] isn't going to force [com.herHim] to strip...")
										:"As you are [pc.a_subspecies], the Enforcers won't force your [com.companion] to strip for them...",
									ENFORCER_ALLEYWAY_SEARCHED_STRIP_REFUSED);
						}
					}
					
				} else if(index==3
						&& Main.game.getPlayer().getRace()!=Race.DEMON
						&& Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON
						&& !demonRevealed) {
					return new Response("Demonic reveal",
							"Transform into a demon in front of the Enforcers, which will be sure to put a stop to their demands of you...",
							ENFORCER_ALLEYWAY_DEMON_REVEAL);
				}
				
			} else {
				if(index==1) {
					return new Response("Continue", "The Enforcers are satisfied that you're not up to no good...", ENFORCER_ALLEYWAY_SEARCHED_COMPLETED);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_SEARCHED_STRIP_REFUSED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(playerSexType!=null && enforcerWantsPlayerSex) {
				return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_REFUSED", getEnforcers());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_REFUSED_COMPANION", getEnforcers());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription(){
			return "Now that you've been 'searched', the Enforcers step back and prepare to let you go.";
		}
		@Override
		public void applyPreParsingEffects() {
			hadSex = true;
			if(playerSexType!=null && enforcerWantsPlayerSex) {
				Main.game.getPlayer().equipAllClothingFromHoldingInventory();
			}
			if(isCompanionDialogue() && (companionSexType!=null && enforcerWantsCompanionSex)) {
				getMainCompanion().equipAllClothingFromHoldingInventory();
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
				if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX_BOTH", getEnforcers());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX", getEnforcers());
				}
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX_COMPANION", getEnforcers());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_SEARCHED_COMPLETED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_COMPLETED", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_DEMON_REVEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			demonRevealed = true;
			Main.game.getPlayer().setEyeType(EyeType.DEMON_COMMON);
			Main.game.getPlayer().setEarType(EarType.DEMON_COMMON);
			Main.game.getPlayer().setHairType(HairType.DEMON);
			Main.game.getPlayer().setWingType(WingType.DEMON_COMMON);
			Main.game.getPlayer().setHornType(HornType.SWEPT_BACK);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_DEMON_REVEAL", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_REPORT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT", getEnforcersAndCriminal());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", UtilText.parse(getCriminalInTile(), "Leave the Enforcers to track down and enslave [npc.name] and continue on your way..."), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_CONTINUE", getEnforcersAndCriminal()));
						banishCriminal();
						banishEnforcers();
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Help",
							UtilText.parse(getCriminalInTile(), "As you don't have a slaver license, there's nothing to gain from helping the Enforcers track down and enslave [npc.name]..."),
							null);
				}
				return new Response("Help",
						UtilText.parse(getCriminalInTile(), "Help the Enforcers to track down and enslave [npc.name], thereby qualifying you to receive the [npc.race] criminal as your reward..."),
						ENFORCER_ALLEYWAY_REPORT_HELP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP", getEnforcersAndCriminal()));
						if(getCriminalInTile().isRelatedTo(Main.game.getPlayer())) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_CHILD", getEnforcersAndCriminal()));
						} else if(!getCriminalInTile().getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_ONE_FRIENDLY)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_FRIEND", getEnforcersAndCriminal()));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_CRIMINAL", getEnforcersAndCriminal()));
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_FINISH", getEnforcersAndCriminal()));
						Main.game.getTextEndStringBuilder().append(getCriminalInTile().setAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_REPORT_HELP = new DialogueNode("", "", true, true) {
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
				return new Response("Take ownership",
						UtilText.parse(getCriminalInTile(), "Take ownership of [npc.name]..."),
						ENFORCER_ALLEYWAY_REPORT_HELP_REWARD) {
					@Override
					public void effects() {
						AbstractClothing neckClothing = getCriminalInTile().getClothingInSlot(InventorySlot.NECK);
						if(neckClothing!=null) {
							getCriminalInTile().forceUnequipClothingIntoVoid(getEnforcerLeader(), neckClothing);
							getCriminalInTile().addClothing(neckClothing, false);
						}
						AbstractClothing collar = AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), PresetColour.CLOTHING_STEEL, false);
						if(!getCriminalInTile().isAbleToEquip(collar, true, Main.game.getPlayer())) {
							for(AbstractClothing c : new ArrayList<>(getCriminalInTile().getClothingCurrentlyEquipped())) {
								c.setSealed(false);
							}
						}
						UtilText.addSpecialParsingString(
								getCriminalInTile().equipClothingFromNowhere(collar, true, Main.game.getPlayer()),
								true);
						banishEnforcers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_REPORT_HELP_REWARD = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_REWARD", getEnforcersAndCriminal());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "As the Enforcers have left to file a report on this incident, you're free to continue on your way...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_REWARD_END", getEnforcers()));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY", getEnforcers());
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Interactions";
			} else if(index==1) {
				return "Inventories";
			} else if(index==2) {
				return "Transformations";
			}
 			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if (index == 1) {
					return new Response("Continue", "Leave the Enforcers to recover and continue on your way...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_CONTINUE", getEnforcers()));
							banishEnforcers();
						}
					};
					
				} else if (index == 2) {
					if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
						if(!Main.game.isNonConEnabled()) {
							return new Response("Sex", UtilText.parse(getEnforcerLeader(), "[npc.Name] has no interest in having sex with you!"), null);
							
						} else {
							return new ResponseSex(
									"Rape them",
									"These Enforcers need to be punished for daring to assume that they have authority over you...",
									Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											getEnforcers(),
											Main.game.getPlayer().getCompanions(),
											null),
									AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_RAPE", getEnforcers()));
						}
						
					} else {
						return new ResponseSex("Sex",
								UtilText.parse(getEnforcers(), "If it's sex that these Enforcers are after, then you're more than happy to give it to them!"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										getEnforcers(),
										Main.game.getPlayer().getCompanions(),
										null),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_SEX", getEnforcers()));
					}
					
				} else if (index == 3) {
					if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
						if(!Main.game.isNonConEnabled()) {
							return new Response("Gentle Sex", UtilText.parse(getEnforcerLeader(), "[npc.Name] has no interest in having sex with you!"), null);
							
						} else {
							return new ResponseSex("Rape [npc.herHim] (gentle)",
									"These Enforcers need to be punished for daring to assume that they have authority over you... (Start the sex scene in the 'gentle' pace.)",
									Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											getEnforcers(),
											Main.game.getPlayer().getCompanions(),
											null,
											ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
									AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getEnforcers()));
						}
						
					} else if(getEnforcerLeader().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
						return new ResponseSex("Gentle sex",
								UtilText.parse(getEnforcers(), "If it's sex that these Enforcers are after, then you're more than happy to give it to them! (Start the sex scene in the 'gentle' pace.)"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										getEnforcers(),
										Main.game.getPlayer().getCompanions(),
										null,
										ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getEnforcers()));
					}
					
				} else if (index == 4) {
					if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
						if(!Main.game.isNonConEnabled()) {
							return new Response("Rough Sex", UtilText.parse(getEnforcerLeader(), "[npc.Name] has no interest in having sex with you!"), null);
							
						} else {
							return new ResponseSex("Rape [npc.herHim] (rough)",
									"These Enforcers need to be punished for daring to assume that they have authority over you... (Start the sex scene in the 'rough' pace.)",
									Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer()),
											getEnforcers(),
											Main.game.getPlayer().getCompanions(),
											null,
											ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
									AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getEnforcers()));
						}
						
					} else if(getEnforcerLeader().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
						return new ResponseSex("Rough sex",
								UtilText.parse(getEnforcers(), "If it's sex that these Enforcers are after, then you're more than happy to give it to them! (Start the sex scene in the 'rough' pace.)"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										getEnforcers(),
										Main.game.getPlayer().getCompanions(),
										null,
										ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getEnforcers()));
						
					}
					
				} else if (index == 5) {
					if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Submit",
								UtilText.parse(getEnforcers(), "You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!"),
								null);
					} else {
						return new ResponseSex("Submit",
								UtilText.parse(getEnforcers(), "You're not really sure what to do now... Perhaps it would be best to let [npc.name] choose what to do next?"),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMGeneric(
										getEnforcers(),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion())),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getEnforcers()));
					}
				}
				
				if(isCompanionDialogue()) {
					if(index == 6) {
						GameCharacter companion = getMainCompanion();
						if(!companion.isAttractedTo(getEnforcerLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Group sex",
									"[com.Name] is not interested in having sex with the Enforcers, and as [com.sheIs] not a slave, you can't force [com.herHim] to do so...", null);
							
						} else if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
							if(!Main.game.isNonConEnabled()) {
								return new Response("Group sex", UtilText.parse(getEnforcerLeader(), "[npc.Name] has no interest in having sex with you!"), null);
								
							} else if(!getMainCompanion().isWillingToRape()) {
								return new Response("Group sex", "[com.Name] has no interest in raping people!", null);
								
							}else {
								return new ResponseSex("Group rape",
										"Rape the Enforcers, and get [com.name] to join in with the fun.",
										false,
										false,
										Main.game.getPlayer().getParty(),
										getEnforcers(),
										null,
										null,
										AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_GROUP_RAPE", getEnforcers()));
							}
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									"Have dominant sex with the Enforcers, and get [com.name] to join in with the fun.",
									true,
									false,
									Main.game.getPlayer().getParty(),
									getEnforcers(),
									null,
									null,
									AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_GROUP_SEX", getEnforcers()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Group submission",
									UtilText.parse(getEnforcerLeader(), "[npc.Name] is not interested in having sex with you..."),
									null);
							
						} else if(!companion.isAttractedTo(getEnforcerLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Group submission",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the Enforcers, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group submission"),
									UtilText.parse(companion, "Get [npc.name] to join you in submitting to the Enforcers, allowing them to have dominant sex with the two of you."),
									true,
									false,
									getEnforcers(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getEnforcers()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getEnforcerLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Give to [com.name]",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the Enforcers, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else if(!getEnforcerLeader().isAttractedTo(getMainCompanion()) && !getMainCompanion().isWillingToRape()) {
							return new Response("Give to [com.name]",
									UtilText.parse(getEnforcerLeader(), "[npc.Name] is not attracted to [com.name], and [com.name] has no interest in forcing [npc.herHim] to have sex with [com.herHim]..."),
									null);
							
						} else {
							return new ResponseSex("Give to [com.name]",
									UtilText.parse(companion, "Tell [npc.name] that [npc.she] can have some fun with the Enforcers while you watch."),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getEnforcers(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getEnforcers()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getEnforcerLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Offer [com.name]",
									UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the Enforcers, and you can't force [npc.herHim] to do so..."),
									null);
							
						} else if(!getEnforcerLeader().isAttractedTo(getMainCompanion())) {
							return new Response("Give to [com.name]",
									UtilText.parse(getEnforcerLeader(), "[npc.Name] is not attracted to [com.name], and so would be unwilling to have sex with [com.herHim]..."),
									null);
							
						} else {
							return new ResponseSex("Offer [com.name]",
									UtilText.parse(companion, "Hand [npc.name] over to the Enforcers, and watch as they have sex with [npc.herHim]."),
									true,
									false,
									getEnforcers(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getEnforcers())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getEnforcerLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
					}
				}
				
				return null;
			}
			
			if(responseTab == 1) {
				for(int i=1; i<=getEnforcers().size(); i++) {
					if(index==i) {
						NPC imp = (NPC) getEnforcers().get(i-1);
						return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
								UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
							@Override
							public void effects() {
								Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								Main.game.setResponseTab(0);
							}
						};
					}
				}
				
			} else if(responseTab == 2) {
				for(int i=1; i<=getEnforcers().size(); i++) {
					if(index==i) {
						NPC enforcer = (NPC) getEnforcers().get(i-1);
						if(!enforcer.isAbleToSelfTransform()) {
							return new Response(UtilText.parse(enforcer, "[npc.Name]"), UtilText.parse(enforcer, "[npc.Name] is unable to self-transform..."), null);
							
						} else {
							return new Response(UtilText.parse(enforcer, "[npc.Name]"),
									UtilText.parse(enforcer, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(enforcer);
								}
							};
						}
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
					if(getEnforcerLeader().isAttractedTo(getMainCompanion())) {
						if(getEnforcerLeader().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "RAPE_BOTH", getEnforcers());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "OFFER_SEX_BOTH", getEnforcers());
						}
						
					} else {
						if(getEnforcerLeader().isWillingToRape()) {
							return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "RAPE_PLAYER_SOLO", getEnforcers());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "OFFER_SEX_SOLO", getEnforcers());
						}
					}
					
				} else if(getEnforcerLeader().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					if(getEnforcerLeader().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "RAPE_COMPANION", getEnforcers());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "OFFER_SEX_COMPANION", getEnforcers());
					}
				}
				
			} else {
				if(getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
					if(getEnforcerLeader().isWillingToRape()) {
						return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "RAPE_PLAYER", getEnforcers());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "OFFER_SEX", getEnforcers());
					}
				}
			}
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "NO_SEX", getEnforcers());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getEnforcerLeader()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getEnforcerLeader().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getEnforcerLeader().isWillingToRape());
				
				if(getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
					if(getEnforcerLeader().isAttractedTo(getMainCompanion())) { // Threesome sex:
						if (index == 1) {
							return new ResponseSex("Sex",
									UtilText.parse(getEnforcerLeader(),
											getEnforcerLeader().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."
												:"Tell [npc.name] that you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+" would like to have sex with [npc.herHim]."),
									false, false,
									new SMGeneric(
											getEnforcers(),
											Util.newArrayListOfValues(
													Main.game.getPlayer(),
													companionSex
														?getMainCompanion()
														:null),
											null,
											Util.newArrayListOfValues(
												companionSex
													?null
													:getMainCompanion())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_THREESOME", getEnforcers()));
							
						} else if (index == 2) {
							return new ResponseSex("Eager Sex",
									UtilText.parse(getEnforcerLeader(),
											getEnforcerLeader().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."
												:"Tell [npc.name] that you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+" are more than happy to have sex with [npc.herHim]."),
									false, false,
									new SMGeneric(
											getEnforcers(),
											Util.newArrayListOfValues(
													Main.game.getPlayer(),
													companionSex
														?getMainCompanion()
														:null),
											null,
											Util.newArrayListOfValues(
												companionSex
													?null
													:getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_EAGER),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_THREESOME", getEnforcers()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("Resist Sex",
									UtilText.parse(getEnforcerLeader(), "[npc.Name] forces [npc.herself] on you"+(companionSex?UtilText.parse(getMainCompanion(), " and [npc.name]"):"")+"..."),
									false, false,
									new SMGeneric(
											getEnforcers(),
											Util.newArrayListOfValues(
													Main.game.getPlayer(),
													companionSex
														?getMainCompanion()
														:null),
											null,
											Util.newArrayListOfValues(
												companionSex
													?null
													:getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_THREESOME_RESIST", getEnforcers()));
							
						} else if (index == 4 && !getEnforcerLeader().isWillingToRape()) {
							return new Response("Refuse",
									UtilText.parse(getEnforcerLeader(), "Refuse to have sex with [npc.name]."),
									AFTER_DEFEAT_CELLS) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "DEFEATED_REFUSE_THREESOME", getEnforcers()));
								}
							};
						}
						return null;
						
					} else { // Solo sex with player:
						if (index == 1) {
							return new ResponseSex("Sex",
									UtilText.parse(getEnforcerLeader(),
											getEnforcerLeader().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you..."
												:"Tell [npc.name] that you would like to have sex with [npc.herHim]."),
									false, false,
									new SMGeneric(
											getEnforcers(),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_SOLO", getEnforcers()));
							
						} else if (index == 2) {
							return new ResponseSex("Eager Sex",
									UtilText.parse(getEnforcerLeader(),
											getEnforcerLeader().isWillingToRape()
												?"[npc.Name] forces [npc.herself] on you..."
												:"Tell [npc.name] that you are more than happy to have sex with [npc.herHim]."),
									false, false,
									new SMGeneric(
											getEnforcers(),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_EAGER),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_SOLO", getEnforcers()));
							
						} else if (index == 3 && Main.game.isNonConEnabled()) {
							return new ResponseSex("Resist Sex",
									UtilText.parse(getEnforcerLeader(), "[npc.Name] forces [npc.herself] on you..."),
									false, false,
									new SMGeneric(
											getEnforcers(),
											Util.newArrayListOfValues(Main.game.getPlayer()),
											null,
											Util.newArrayListOfValues(getMainCompanion()),
											ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_SOLO_RESIST", getEnforcers()));
							
						} else if (index == 4 && !getEnforcerLeader().isWillingToRape()) {
							return new Response("Refuse",
									UtilText.parse(getEnforcerLeader(), "Refuse to have sex with [npc.name]."),
									AFTER_DEFEAT_CELLS) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "DEFEATED_REFUSE_SEX_SOLO", getEnforcers()));
								}
							};
						}
						return null;
					}
					
				} else if(getEnforcerLeader().isAttractedTo(getMainCompanion())
						&& Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) { // Solo sex with companion:
					if(getEnforcerLeader().isWillingToRape()) {
						if (index == 1) {
							return new ResponseSex("Watch rape",
									UtilText.parse(getEnforcerLeader(), getMainCompanion(),
											"You can do nothing but watch as [npc.name] forces [npc.herself] on [npc2.name]."),
									false, false,
									new SMGeneric(
											getEnforcers(),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_SOLO_COMPANION_RAPE", getEnforcers()));
						}
						
					} else if(companionHappyToHaveSex) {
						if (index == 1) {
							return new ResponseSex("Watch sex",
									UtilText.parse(getEnforcerLeader(), getMainCompanion(),
											"You can do nothing but watch as [npc2.name] happily agrees to let [npc.name] fuck [npc2.herHim]."),
									false, false,
									new SMGeneric(
											getEnforcers(),
											Util.newArrayListOfValues(getMainCompanion()),
											null,
											Util.newArrayListOfValues(Main.game.getPlayer())),
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_SOLO_COMPANION", getEnforcers()));
						}
						
					} else if (index == 1) {
						return new Response(
								UtilText.parse(getMainCompanion(), "[npc.Name] refuses"),
								UtilText.parse(getEnforcerLeader(), getMainCompanion(), "It looks like [npc2.name] is going to refuse to have sex with [npc.name]."),
								AFTER_DEFEAT_CELLS) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "DEFEATED_REFUSE_SEX_SOLO_COMPANION", getEnforcers()));
							}
						};
					}
				}
				
			} else {
				if(getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) { // Solo sex with player:
					if (index == 1) {
						return new ResponseSex("Sex",
								UtilText.parse(getEnforcerLeader(),
										getEnforcerLeader().isWillingToRape()
											?"[npc.Name] forces [npc.herself] on you..."
											:"Tell [npc.name] that you would like to have sex with [npc.herHim]."),
								false, false,
								new SMGeneric(
										getEnforcers(),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion())),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX", getEnforcers()));
						
					} else if (index == 2) {
						return new ResponseSex("Eager Sex",
								UtilText.parse(getEnforcerLeader(),
										getEnforcerLeader().isWillingToRape()
											?"[npc.Name] forces [npc.herself] on you..."
											:"Tell [npc.name] that you are more than happy to have sex with [npc.herHim]."),
								false, false,
								new SMGeneric(
										getEnforcers(),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_EAGER),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX", getEnforcers()));
						
					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex("Resist Sex",
								UtilText.parse(getEnforcerLeader(), "[npc.Name] forces [npc.herself] on you..."),
								false, false,
								new SMGeneric(
										getEnforcers(),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										Util.newArrayListOfValues(getMainCompanion()),
										ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_RESIST", getEnforcers()));
						
					} else if (index == 4 && !getEnforcerLeader().isWillingToRape()) {
						return new Response("Refuse",
								UtilText.parse(getEnforcerLeader(), "Refuse to have sex with [npc.name]."),
								AFTER_DEFEAT_CELLS) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "DEFEATED_REFUSE_SEX", getEnforcers()));
							}
						};
					}
					return null;
				}
			}
			if (index == 1) {
				return new Response("Dragged off", "The Enforcers drag you off to the cells...", AFTER_DEFEAT_CELLS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "DEFEATED_NO_SEX", getEnforcers()));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEMONIC_SEDUCTION = new DialogueNode("Step back", "", true) {
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the Enforcers to recover.";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_SEX_DEMONIC_SEDUCTION", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the Enforcers to recover from their defeat.";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_SEX_VICTORY", getEnforcers());
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishEnforcers();
						}
					};
				}
				
			} else if(responseTab==1) {
				return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Collapse", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}
		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_SEX_WITH_COMPANION", getEnforcers());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_SEX", getEnforcers());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Dragged off", "The Enforcers drag you off to the cells...", AFTER_DEFEAT_CELLS);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_CELLS = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELL);
			for(GameCharacter enforcer : getEnforcers()) {
				enforcer.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_CELLS", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wait...", "There's really nothing that you can do except wait in your cell...", AFTER_DEFEAT_CELLS_WAITING) {
					@Override
					public void effects() {
						banishEnforcers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_CELLS_WAITING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_CELLS_WAITING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Released", "You're released from the cells and escorted out of the Enforcer HQ...", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_CELLS_WAITING_RELEASED", getEnforcers()));
					}
				};
			}
			return null;
		}
	};
	
}
