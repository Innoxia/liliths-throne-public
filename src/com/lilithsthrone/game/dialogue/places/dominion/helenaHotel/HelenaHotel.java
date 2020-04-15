package com.lilithsthrone.game.dialogue.places.dominion.helenaHotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestHelena;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class HelenaHotel {
	
//	- Post-Helena's romance quest
//		Scarlett is present during extended work hours, but Helena is only there during normal work hours (she works late to 21:00 on Fridays)
//		If evening, and not an arcane storm:
//			Can go on date to an upmarket restaurant beneath Harpy Nests (turns out Helena owns it, and it's connected to her nest)
//			Scarlett accompanies, but has to wait out of sight
//				She says you don't have the opportunity to impress her by buying the meal; everything here is free for her
//				Affection increases instead by acting firmly but kindly to her
//				You can also lose affection by belittling her or implying that other harpies are more attractive (or other such obviously bad things)
//			Use actions to increase (or decrease) affection. Can increase by about 10 per date.
//				If have a rose, can give to her.
//				She only likes white roses. Offering any other colour decreases affection.
//			Can then ask out on date every Friday between 17:00 and 21:00
//			Once affection >=75, you can talk to Helena about her sex life. She is very flustered and hesitantly admits that she's never had sex. (Believing herself to be too good for anyone)
//			You can take her up to her penthouse apartment and have sex with her.
//				She suggests having Scarlett demonstrate what she wants on one of her maids. (If accept, the limits of what Helena wants are displayed to the player)
//					First time, she will only accept kissing/groping.
//						She gains oral fetishes afterwards
//					Second time, she comes out in lingerie, and allows you to play with her naked breasts (dominantly lets the player fuck her breasts if player has cock).
//						Gains self-breasts fetish
//					Third time, she lets you perform oral on her
//				After oral, she says that this is as far she wants to go - wants to keep her virginity
//					Can convince for giving you oral
//					Can convince for anal (hard if she is still virgin; if virginity taken, she is pretty slutty)
//						She gains anal receiving fetish afterwards (becoming lusty maiden)
//					Can ask Scarlett to set up romantic date in her apartment for taking her virginity
//						Can either let her dom at her own pace, or dom her
//							Gives her dom or sub fetish
//					After virginity taken
//						Wears black lingerie
//						Wears different dress - comments that it's easier to lift up for you
//						Unlocks sex in store, makes her a lot lewder
//						Can convince her to let Scarlett have sex with her as well
//							If convinced, can sometimes find store closed. Entering will lead to scene where Scarlett is fucking Helena (with strapon if female)
//							She says it would be nice for Scarlett to be bigger; other harpies prefer small feminine penises, but she wants to get fucked by a big cock (player has option to increase Scarlett's penis size)
//						Can be convinced to perform or receive under-table oral during date
	

	private static HelenaConversationTopic talkTopic = HelenaConversationTopic.SLAVES;
	private static boolean firstKissScene = false;
	
	private static SexManagerInterface getScarlettOralSexManager() {
		return new SexManagerDefault(
				false,
				SexPosition.SITTING,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotSitting.SITTING)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.ONGOING_ONLY;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return Util.newHashMapOfValues(
						new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA)),
						new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					if(Main.game.getNpc(Scarlett.class).hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
					} else {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
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
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE; // Doesn't want to cover player in cum before Helena's date
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
		};
	}
	
	private static String incrementHelenaAffection(float affection) {
		float currentAffection = Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer());
		if(affection<0) {
			if(currentAffection+affection < AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue()) {
				return ""; // Don't drop affection below 'like' level.
			}
		}
		
		return Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), affection);
	}

	public static SexManagerInterface getHelenaSexManager(boolean helenaDom,
			AbstractSexPosition position,
			SexSlot helenaSlot,
			SexSlot playerSlot,
			SexType helenaPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return getHelenaSexManager(helenaDom, false, !helenaDom, position, helenaSlot, null, playerSlot, helenaPreference, null, null, null, exposeAtStartOfSexMap);
	}
	
	public static SexManagerInterface getHelenaSexManager(boolean helenaDom,
			boolean scarlettDom,
			boolean playerDom,
			AbstractSexPosition position,
			SexSlot helenaSlot,
			SexSlot scarlettSlot,
			SexSlot playerSlot,
			SexType helenaToPlayerPreference,
			SexType helenaToScarlettPreference,
			SexType scarlettToPlayerPreference,
			SexType scarlettToHelenaPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(
						(helenaDom
							?new Value<>(Main.game.getNpc(Helena.class), helenaSlot)
							:null),
						(scarlettDom && scarlettSlot!=null
								?new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)
								:null),
						(playerDom
								?new Value<>(Main.game.getPlayer(), playerSlot)
								:null)),
				Util.newHashMapOfValues(
						(!helenaDom
							?new Value<>(Main.game.getNpc(Helena.class), helenaSlot)
							:null),
						(!scarlettDom && scarlettSlot!=null
								?new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)
								:null),
						(!playerDom
								?new Value<>(Main.game.getPlayer(), playerSlot)
								:null))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter character) {
				return false; // Do not allow toy use
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
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
				if(character.equals(Main.game.getNpc(Helena.class))) {
					if(targetedCharacter.isPlayer() && helenaToPlayerPreference!=null) {
						return helenaToPlayerPreference;
					} else if(character.equals(Main.game.getNpc(Scarlett.class)) && helenaToScarlettPreference!=null) {
						return helenaToScarlettPreference;
					}
				} else if(character.equals(Main.game.getNpc(Scarlett.class))) {
					if(targetedCharacter.isPlayer() && scarlettToPlayerPreference!=null) {
						return scarlettToPlayerPreference;
					} else if(character.equals(Main.game.getNpc(Helena.class)) && scarlettToHelenaPreference!=null) {
						return scarlettToHelenaPreference;
					}
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(character.equals(Main.game.getNpc(Helena.class))) {
					if(targetedCharacter.isPlayer() && helenaToPlayerPreference!=null) {
						return helenaToPlayerPreference;
					} else if(character.equals(Main.game.getNpc(Scarlett.class)) && helenaToScarlettPreference!=null) {
						return helenaToScarlettPreference;
					}
				} else if(character.equals(Main.game.getNpc(Scarlett.class))) {
					if(targetedCharacter.isPlayer() && scarlettToPlayerPreference!=null) {
						return scarlettToPlayerPreference;
					} else if(character.equals(Main.game.getNpc(Helena.class)) && scarlettToHelenaPreference!=null) {
						return scarlettToHelenaPreference;
					}
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public Map<GameCharacter, List<SexType>> getSexTypesBannedMap() { //TODO test
				Map<GameCharacter, List<SexType>> bannedMap = new HashMap<>();
				if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE));
				}
				if(Main.game.getNpc(Helena.class).isAssVirgin()) {
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TAIL));
					bannedMap.get(Main.game.getNpc(Helena.class)).add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE));
				}
				return bannedMap;
			}
			@Override
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(helenaToPlayerPreference.getPerformingSexArea()==SexAreaOrifice.ANUS
						&& helenaToPlayerPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				if(helenaToScarlettPreference.getPerformingSexArea()==SexAreaOrifice.ANUS
						&& helenaToScarlettPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Scarlett.class), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				if(Main.game.getNpc(Helena.class).isVaginaVirgin()
						&& helenaToPlayerPreference.getPerformingSexArea()==SexAreaOrifice.VAGINA
						&& helenaToPlayerPreference.getTargetedSexArea()==SexAreaPenetration.PENIS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getNpc(Helena.class), new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).put(SexAreaOrifice.VAGINA, new HashMap<>());
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.VAGINA).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
					map.get(Main.game.getNpc(Helena.class)).get(SexAreaOrifice.VAGINA).put(Main.game.getNpc(Helena.class), Util.newHashSetOfValues(LubricationType.GIRLCUM));
					return map;
				}
				return super.getStartingWetAreas();
			}
		};
	}

	
	
	// Generic dialogue:
	
	public static final DialogueNode HOTEL_TRAVEL_TO_NEST = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotel", "HOTEL_TRAVEL_TO_NEST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.HARPY_NESTS_HELENAS_NEST.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HOTEL_TRAVEL_TO_DOMINION = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotel", "HOTEL_TRAVEL_TO_DOMINION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.DOMINION_HELENA_HOTEL.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	
	// Helena dates:
	
	public static final DialogueNode DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeAllCompanions(true);
			// Reset date flags:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Walk", "Walk back to Helena's apartment with her.", DATE_TRAVEL) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WALK"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WALK_REPEAT"));
						}
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly", "Fly back to Helena's apartment with her.", DATE_TRAVEL) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_FLY"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_FLY_REPEAT"));
							}
						}
					};
				}
				return new Response("Fly", "You aren't able to fly...", null);
				
			} else if(index==3) {
				if(Main.game.getPlayer().isTaur()) {
					return new Response("Offer ride", "Offer to let Helena ride on your back and carry her back to her apartment.", DATE_TRAVEL) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_RIDE"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_RIDE_REPEAT"));
							}
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
						}
					};
				}
				return new Response("Offer ride", "You are not a taur, so can't offer to let Helena ride on your back...", null);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DATE_TRAVEL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			// Set place 2 tiles left of harpy nests as Helena's hotel:
			if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HELENA_HOTEL)==null) {
				Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_HARPY_NESTS_ENTRANCE).getLocation();
				vec.setX(vec.getX()-2);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_HELENA_HOTEL);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_HELENA_HOTEL.getName());
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);
				Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setTravelledTo(true);
			}
			
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL");
		}
		@Override
		public Response getResponse(int responseTab, int index) { // Scarlett interaction
			if(index==1) {
				return new Response("Wait", "Just wait for Helena to return.", DATE_RESTAURANT_START) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_WAIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("Small talk", "Make small talk with Scarlett while you wait for Helena to return.", DATE_RESTAURANT_START) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_TRAVEL_SMALL_TALK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==3
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateVirginityTalk)) {// Romantic setup for taking virginity or repeat of scene:
				if(Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.GIFT_ROSE_BOUQUET))<3) {
					return new Response("Romantic setup", "You require at least [style.boldMinorBad(three "+ItemType.GIFT_ROSE_BOUQUET.getNamePlural(false)+")] in your inventory to do this!", null);
				}
				if((!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
						 && (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))) {
					return new Response("Romantic setup", "You [style.colourBad(require either a penis or vagina)], and [style.colourBad(to be able to access your genitals)] for this action!", null);
				}
				return new Response("Romantic setup", "Ask Scarlett to set up a romantic scene in Helena's apartment for when the two of you return from your date.", DATE_RESTAURANT_ROMANTIC_SETUP);
				
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_ROMANTIC_SETUP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getNpc(Scarlett.class).getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_AGREED");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_DECLINED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Scarlett.class).getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE) {
				if(index==1) {
					return new Response("Thank her", "Thank Scarlett for agreeing to do as you've asked.", DATE_RESTAURANT_START) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_THANKS"));
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Refuse", "Refuse to agree to Scarlett's conditions.", DATE_RESTAURANT_START) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_REFUSED"));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getPlayer().getMoney()<1000) {
						return new Response("Pay her ("+UtilText.formatAsMoneyUncoloured(1000, "span")+")", "You don't have enough money to pay Scarlett...", null);
					}
					return new Response("Pay her ("+UtilText.formatAsMoney(1000, "span")+")", "Pay Scarlett one thousand flames in exchange for her setting up the romantic scene in Helena's apartment.", DATE_RESTAURANT_START) {
						@Override
						public int getSecondsPassed() {
							return 5*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_ROMANTIC_SETUP_PAID"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-1000));
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getNpc(Scarlett.class).hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("Suck cock", "You can't access your mouth at the moment, so can't offer to suck Scarlett's cock...", null);
						}
						return new ResponseSex(
								"Suck cock",
								"Do as Scarlett suggests and quickly suck her cock in exchange for her setting up the romantic scene in Helena's apartment.",
								true,
								false,
								getScarlettOralSexManager(),
								null,
								null,
								DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_RESTAURANT_ROMANTIC_SETUP_START_BLOWJOB")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							}
						};
						
					} else {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("Cunnilingus", "You can't access your mouth at the moment, so can't offer to perform cunnilingus on Scarlett...", null);
						}
						return new ResponseSex(
								"Cunnilingus",
								"Do as Scarlett suggests and quickly eat her out in exchange for her setting up the romantic scene in Helena's apartment.",
								true,
								false,
								getScarlettOralSexManager(),
								null,
								null,
								DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_RESTAURANT_ROMANTIC_SETUP_START_CUNNILINGUS")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
					}
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL = new DialogueNode("Finished", "Now that she's orgasmed, Scarlett has had enough.", true) {
		@Override
		public void applyPreParsingEffects() { //TODO test
			Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.GIFT_ROSE_BOUQUET), 3, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateRomanticSetup, true);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.MOUTH);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.EYES);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.HAIR);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.HEAD);
			Main.game.getPlayer().removeDirtySlot(InventorySlot.NECK);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_RESTAURANT_ROMANTIC_SETUP_AFTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait for Helena to arrive.", DATE_RESTAURANT_START) {
					@Override
					public int getSecondsPassed() {
						return 3*60;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_START = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wine", "Tell Helena and the harpy waitress that you'll also be drinking wine this evening.", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WINE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.1f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			} else if(index==2) {
				return new Response("Water", "Ask the harpy for a drink of water.", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WATER"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
				
			} else if(index==3) {
				return new Response("Beer", "Ask the harpy for a beer.", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_BEER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.05f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
				
			} else if(index==4) {
				return new Response("Whiskey", "Ask the harpy for a glass of whiskey.", DATE_RESTAURANT_TALKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_START_WHISKEY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.4f));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_TALKING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			talkTopic = HelenaConversationTopic.getRandomTopic();
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAlcoholLevel(0.1f));
		}
		@Override
		public int getSecondsPassed() {
			return 40*60;
		}
		@Override
		public String getContent() {
			// One of several talk topics:
			if(Main.game.getDialogueFlags().hasHelenaConversationTopic(talkTopic)) {
				return (UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_TALKING_"+talkTopic+"_REPEAT"));
			} else {
				return (UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_TALKING_"+talkTopic));
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			switch(talkTopic) {
				case SLAVES:
					if(index==1) {
						return new Response("Natural", "Tell Helena that you think it's perfectly natural for some people to be slaves.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_NATURAL"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Necessity", "Tell Helena that you think that slavery is an unfortunate necessity in a society like Dominion's.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_NECESSITY"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Wrong", "Tell Helena that slavery is morally wrong, and that one day it will surely be abolished.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_SLAVES_WRONG"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case HARPY_NESTS:
					if(index==1) {
						return new Response("Queen", "Tell Helena that there should be a single queen in control of all of the Harpy Nests, and that she should take that role.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_HELENA_TOP"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Balance", "Tell Helena that the Harpy Nests seem to have found a somewhat stable balance, and so nothing should be changed.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_BALANCE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Dismantle", "Tell Helena that the societal structure of the Harpy Nests should be dismantled and completely remade.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPY_NESTS_BAD"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case RACES:
					if(index==1) {
						return new Response("Harpies", "Tell Helena that harpies are undeniably the most beautiful of all races, and that she is the most beautiful of all.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_HARPIES"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Individual", "Tell Helena that beauty depends on the individual, and that race isn't important.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_INDIVIDUAL"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Personality", "Tell Helena that looks mean nothing, and a person's beauty is found within.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_RACES_INNER_BEAUTY"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case HARPIES:
					if(index==1) {
						return new Response("Admirable", "Tell Helena that Scarlett makes for a most admirable companion.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_CONTROL"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Rude", "Tell Helena that Scarlett is very rude, but at least she behaves herself when in Helena's presence.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_RUDE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Bitch", "Tell Helena that Scarlett is a complete bitch.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_HARPIES_BITCH"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
					
				case BUSINESS:
					if(index==1) {
						return new Response("Flatter", "Tell Helena that only someone of her exceptional abilities could have turned that business around.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_FLATTER"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						};
						
					} else if(index==2) {
						return new Response("Take credit", "Tell Helena that you deserve at least some of the credit for how well things turned out.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_CREDIT"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
							}
						};
						
					} else if(index==3) {
						return new Response("Surprised", "Tell Helena that you're surprised at how successful it is; you never would have imagined Helena's idea to actually work.", DATE_RESTAURANT_PLAYER_TOPIC) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "RESPONSE_BUSINESS_SURPRISE"));
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
							}
						};
					}
					break;
			}
			return null;
		}
	};
	
	// You can bring up a topic to discuss over pudding
	public static final DialogueNode DATE_RESTAURANT_PLAYER_TOPIC = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().addHelenaConversationTopic(talkTopic);
		}
		@Override
		public int getSecondsPassed() {
			return 45*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC");
		}
		@Override
		public Response getResponse(int responseTab, int index) {// Topics
			if(index==1) {
				return new Response("Helena", "Talk about Helena and ask her what she's got planned for the weekend.", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_HELENA"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
					}
				};
				
			} else if(index==2) {// Indifferent
				return new Response("Arcane", "Turn the topic of conversation to the arcane.", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_ARCANE"));
					}
				};
				
			} else if(index==3) {// Disapproves
				return new Response("Politics", "Talk to Helena about Lilith and her rule over Dominion.", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_POLITICS"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
					}
				};
				
			} else if(index==4) {
				if(Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer())<70) {
					return new Response("Sex life",
							"You can tell that Helena isn't ready to talk openly about her sex life with you yet..."
									+ "<br/>[style.italicsMinorBad(Requires Helena's affection towards you to be 70 or greater. It is currently "+Main.game.getNpc(Helena.class).getAffection(Main.game.getPlayer())+".)]",
							null);
				}
				return new Response("Sex life", "Talk to Helena about her sex life.", DATE_RESTAURANT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_PLAYER_TOPIC_SEX_LIFE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateSexLifeTalk, true);
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
					}
				};
				
			} else if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGift)) {
					return new Response("Gift", "You've already given Helena a gift...", null);
					
				} else {
					return new Response("Gift",
							"Give Helena a gift (opens gift selection screen)."
									+ "<br/>[style.italicsMinorGood(You will be able to return to this scene and ask her about one of the other topics after giving her a gift.)]",
							DATE_RESTAURANT_PLAYER_TOPIC) {
						@Override
						public DialogueNode getNextDialogue() {
							return GiftDialogue.getGiftDialogue(Main.game.getNpc(Nyan.class), DATE_RESTAURANT_PLAYER_TOPIC, 0, DATE_RESTAURANT_GIFT, 0); //TODO test
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_GIFT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DATE_RESTAURANT_PLAYER_TOPIC.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_END = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
					return new Response("Goodbye", "With Scarlett having set up a romantic scene for you in Helena's apartment, you don't want to leave her now!", null);
				}
				return new Response("Goodbye", "Say goodbye to Helena and that you hope to see her again soon.", DATE_RESTAURANT_END_GOODBYE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateFirstDateComplete, true);
						Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_GOODBYE"));
					}
				};
				
			} else if(index==2) {
				return new Response("Accompany", "Accompany Helena back up to her penthouse apartment.", DATE_RESTAURANT_END_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(1));
						Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_ENTRANCE);
						Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_END_GOODBYE = new DialogueNode("", "", true, true) {
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
				return new Response("Continue", "Continue on out into the streets of Dominion.", PlaceType.DOMINION_HELENA_HOTEL.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_RESTAURANT_END_HOME = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup)) {
				if(index==1) {
					if(((Helena)Main.game.getNpc(Helena.class)).isSlutty()) {
						return new Response("Bedroom", "Helena leads you down the hallway to her bedroom.", DATE_APARTMENT_BEDROOM) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_ROMANTIC_SCENE_BEDROOM"));
							}
						};
						
					} else {
						return new Response("Kiss", "Take advantage of Helena's bashfulness to kiss her.", DATE_APARTMENT_ROMANTIC_SCENE_KISS);
					}
				}
				
			} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
				if(index==1) {
					return new Response("Bedroom", "Helena isn't taking no for an answer as she pulls you along towards her bedroom...", DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_HELENA_DOM"));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Goodbye", "Say goodbye to Helena and head back out into Dominion.", DATE_RESTAURANT_END_GOODBYE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_GOODBYE"));
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-1));
							}
						}
					};
					
				} else if(index==2) {
					return new Response("Parting kiss", "Kiss Helena on the cheek and say goodbye.", DATE_RESTAURANT_END_GOODBYE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_GOODBYE_KISS"));
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
								Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(5));
							}
						}
					};
					
				} else if(index==3) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateSexLifeTalk)) {
						return new Response("Inside",
								"You think that Helena would invite you inside if you were first able to get her to openly talk to you about her sex life..."
										+ "<br/>[style.italicsMinorBad(Next time you go on a date with Helena, if her affection towards you is 70 or more, you should ask her about her sex life to unlock this action.)]",
								null);
					}
					if(Main.game.getNpc(Helena.class).getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
						return new Response("Inside",
								"Helena doesn't like you enough to want to invite you inside...<br/>[style.italicsBad(Requires affection of at least "+AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue()+".)]",
								null);
					}
					return new Response("Inside", "Accept Helena's invitation to come inside.", DATE_APARTMENT_START) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_LOUNGE);
							Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
						}
					};
					
				} else if(index==4 && ((Helena)Main.game.getNpc(Helena.class)).isSlutty()) {
					return new Response("Bedroom", "Take Helena straight into her bedroom to give her the fucking she's so obviously craving.", DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_RESTAURANT_END_HOME_BEDROOM"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_ROMANTIC_SCENE_KISS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_ROMANTIC_SCENE_KISS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Bedroom", "Lead Helena into her bedroom...", DATE_APARTMENT_BEDROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_ROMANTIC_SCENE_KISS_BEDROOM"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_START = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			firstKissScene = false;
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(index==0) {
				if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_COFFEE) {
					return new Response("Leave", "Now that you've had coffee, it's time to leave...", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START_LEAVE"));
						}
					};
					
				} else {
					return new Response("Leave", "You should at least have some coffee before you leave...", null);
				}
			}
			
			if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_KISS) {
				return new Response("Coffee", "Helena isn't going to want to stop the fun and have coffee now.", null);
				
			} else if(Main.game.getCurrentDialogueNode()==DATE_APARTMENT_COFFEE) {
				return new Response("Coffee", "You're already having coffee with Helena...", null);
				
			} else {
				responses.add(new Response("Coffee", "Well, this is what you're here for, isn't it?", DATE_APARTMENT_COFFEE));
			}
			
			if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
				if(Main.game.getNpc(Helena.class).getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
					responses.add(new Response("First kiss",
							"Helena doesn't like you enough to let you take her first kiss.<br/>[style.italicsBad(Requires affection of at least "+AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue()+".)]",
							null));
					
				} else {
					responses.add(new Response("First kiss",
							"Helena told you over dinner that she's never so much as been kissed by anyone before. With how she's acting towards you, you think that you have an opportunity to change that...",
							DATE_APARTMENT_KISS) {
						@Override
						public void effects() {
							firstKissScene = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE, true));
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(10));
						}
					});
				}
				
			} else {
				if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
					responses.add(new Response("Make out", "Although she definitely wants more than just a kiss, Helena is still more than happy to spend some time making out with you on the sofa.", DATE_APARTMENT_KISS));
					
				} else {
					responses.add(new Response("Make out", "Now that Helena has discovered the pleasure of kissing, you know that she'd be willing to spend some time making out with you on the sofa.", DATE_APARTMENT_KISS));
				}
			}
			
			if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
				responses.add(new Response("Bedroom", "Helena isn't quite ready for this yet. Perhaps if you were to take her first kiss, she'd be willing to take things into the bedroom the next time you're here.", null));
				
			} else {
				if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive() // Once been in bedroom once, it's always unlocked.
						&& Main.game.getNpc(Helena.class).getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_THREE_CARING)) {
					responses.add(new Response("Bedroom",
							"Helena doesn't like you enough to want to spend time with you in her bedroom...<br/>[style.italicsBad(Requires affection of at least "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+".)]",
							null));
					
				} else {
					responses.add(new Response("Bedroom",
							((Helena) Main.game.getNpc(Helena.class)).isSlutty()
								?"Give Helena what she so desperately wants and suggest that the two of you head to her bedroom."
								:"Ask Helena if she'd like to head into her bedroom with you.",
							DATE_APARTMENT_BEDROOM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_START_BEDROOM"));
						}
					});
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_LEAVE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
			Main.game.getNpc(Helena.class).cleanAllClothing(true);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots();
			Main.game.getNpc(Helena.class).equipClothing();
		}
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
				return new Response("Continue", "Continue on out into the streets of Dominion.", PlaceType.DOMINION_HELENA_HOTEL.getDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_COFFEE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_COFFEE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DATE_APARTMENT_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DATE_APARTMENT_KISS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			if(firstKissScene) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_FIRST");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(firstKissScene) {
				if(index==1) {
					return new Response("Leave",
							"Helena seems overwhelmed with what's just happened, and needs time in which to recover. Perhaps next time you take her on a date, she'll be willing to take things further...",
							DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_FIRST_LEAVE"));
						}
					};
				}
				
			} else {
				if(index==0) {
					return new Response("Leave", "Tell Helena that you need to be going now.", DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_LEAVE"));
						}
					};
					
				} else if(index==1) {
					if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive() // Once been in bedroom once, it's always unlocked.
							&& Main.game.getNpc(Helena.class).getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_THREE_CARING)) {
						return new Response("Bedroom",
								"Helena doesn't like you enough to want to spend time with you in her bedroom...<br/>[style.italicsBad(Requires affection of at least "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+".)]",
								null);
						
					} else {
						return new Response("Bedroom", "Ask Helena if she'd like to head into her bedroom with you.", DATE_APARTMENT_BEDROOM) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_KISS_BEDROOM"));
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_HELENA_BEDROOM);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			((Helena)Main.game.getNpc(Helena.class)).applyLingerie();
		}
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
			List<Response> responses = new ArrayList<>();
			
			// If romantic scene and Helena is still a virgin, you are led into oral:
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateRomanticSetup) && Main.game.getNpc(Helena.class).isVaginaVirgin()) {
				responses.add(new ResponseSex(
						"Perform cunnilingus",
						"Push Helena onto the bed, drop your head between her legs, and start eating her out.",
						true,
						true,
						getHelenaSexManager(false,
								SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_ROMANCE_PERFORM_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
				});
				
			} else { // This is not a romantic scene, so give all options:
				
				responses.add(new ResponseSex(
						"Kiss breasts",
						"Give Helena's breasts some oral attention.",
						true,
						true,
						getHelenaSexManager(false,
								Main.game.getPlayer().isTaur()
									?SexPosition.STANDING
									:SexPosition.SITTING,
								Main.game.getPlayer().isTaur()
									?SexSlotStanding.STANDING_SUBMISSIVE
									:SexSlotSitting.SITTING_IN_LAP,
								Main.game.getPlayer().isTaur()
									?SexSlotStanding.STANDING_DOMINANT
									:SexSlotSitting.SITTING,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.NIPPLES)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						DATE_APARTMENT_BEDROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_KISS_BREASTS")
						+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE))==0
							?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "BREASTS_FIRST_TIME")
							:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "BREASTS_EXPERIENCED"))) {
					@Override
					public void effects() {
						if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE));
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueNipple.SUCKLE_START, false, true));
					}
				});
				
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
						responses.add(new Response("Receive paizuri", "Helena isn't ready to go this far with you yet...", null));
						
					} else if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("Receive paizuri", "As you are a taur, Helena can't get into a suitable position in which to give you a titjob...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Receive paizuri",
								"Get Helena to push her breasts together and give you a titjob.",
								true,
								true,
								getHelenaSexManager(false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.NIPPLES)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_RECEIVE_PAIZURI")
								+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "PAIZURI_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "PAIZURI_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_BREASTS_SELF)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_BREASTS_SELF));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisBreasts.FUCKING_START, false, true));
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
					responses.add(new Response("Perform cunnilingus", "Helena isn't ready to go this far with you yet...", null));
					
				} else {
					responses.add(new ResponseSex(
							"Perform cunnilingus",
							"Push Helena onto the bed, drop your head between her legs, and start eating her out.",
							true,
							true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_PERFORM_CUNNILINGUS")
							+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE))==0
								?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "CUNNILINGUS_FIRST_TIME")
								:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "CUNNILINGUS_EXPERIENCED"))) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_RECEIVING));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}
	
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("Receive cunnilingus", "As you cannot access your vagina, Helena cannot perform oral on you...", null));
						
					} else if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
						responses.add(new Response("Receive cunnilingus", "Helena isn't ready to go this far with you yet...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Receive cunnilingus",
								"Get Helena to get down on her knees and eat you out.",
								true,
								true,
								getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.LYING_DOWN,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL_BEHIND
											:SexSlotLyingDown.MISSIONARY_ORAL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_RECEIVE_CUNNILINGUS")
								+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "GIVING_CUNNILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "GIVING_CUNNILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						});
					}
				}
	
				if(Main.game.getPlayer().hasPenis()) {// Press her on her desire to experience oral - convince her to perform oral on you.
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Receive blowjob", "As you cannot access your penis, Helena cannot perform oral on you...", null));
						
					} else if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
						responses.add(new Response("Receive blowjob", "Helena isn't ready to go this far with you yet...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Receive blowjob",
								"Get Helena to get down on her knees and suck your cock.",
								true,
								true,
								getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.LYING_DOWN,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL
											:SexSlotLyingDown.MISSIONARY_ORAL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_RECEIVE_BLOWJOB")
								+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "BLOWJOB_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "BLOWJOB_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
					responses.add(new Response("Sixty-nine", "Helena isn't ready to do this with you just yet...", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Sixty-nine", "As you are a taur, you can't get into a suitable position in which to sixty-nine with Helena...", null));
					
				} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					
					responses.add(new ResponseSex(
							"Sixty-nine",
							"Suggest that Helena sit on your face and bend down to mutually perform oral on one another...",
							true, true,
							getHelenaSexManager(true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.SIXTY_NINE, SexSlotLyingDown.LYING_DOWN,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, Main.game.getPlayer().hasPenis()?SexAreaPenetration.PENIS:SexAreaOrifice.VAGINA),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, Main.game.getPlayer().hasPenis()?CoverableArea.PENIS:CoverableArea.VAGINA)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_SIXTY_NINE_HELENA_DOM")
							+ (Main.game.getPlayer().hasPenis()
								?(Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "BLOWJOB_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "BLOWJOB_EXPERIENCED"))
								:(Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "GIVING_CUNNILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "GIVING_CUNNILINGUS_EXPERIENCED")))) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis() && !Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
							}
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_RECEIVING));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), Main.game.getPlayer().hasPenis()?PenisMouth.GIVING_BLOWJOB_START:TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					});
					
				} else {
					responses.add(new ResponseSex(
							"Sixty-nine",
							"Suggest you sit on Helena's face and bend down to mutually perform oral on one another...",
							true, true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.SIXTY_NINE,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, Main.game.getPlayer().hasPenis()?SexAreaPenetration.PENIS:SexAreaOrifice.VAGINA),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, Main.game.getPlayer().hasPenis()?CoverableArea.PENIS:CoverableArea.VAGINA)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_SIXTY_NINE_HELENA_SUB")
							+ (Main.game.getPlayer().hasPenis()
									?(Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
										?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "BLOWJOB_FIRST_TIME")
										:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "BLOWJOB_EXPERIENCED"))
									:(Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
										?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "GIVING_CUNNILINGUS_FIRST_TIME")
										:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "GIVING_CUNNILINGUS_EXPERIENCED")))) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis() && !Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
							}
							if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_RECEIVING));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), Main.game.getPlayer().hasPenis()?PenisMouth.BLOWJOB_START:TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}
				
				if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
					responses.add(new Response("Scissoring", "Helena isn't ready to do this with you just yet...", null));
					
				} else if(!Main.game.getPlayer().hasVagina()) {
					responses.add(new Response("Scissoring", "You do not have a vagina, so you cannot scissor with Helena...", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Scissoring", "As you are a taur, you can't get into a suitable position in which to scissor with Helena...", null));
					
				} else {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						responses.add(new ResponseSex(
								"Scissoring",
								"Suggest that the two of you scissor with one another...",
								true, true,
								getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_SCISSORING_HELENA_DOM")
								+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT))==0
										?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "SCISSORING_FIRST_TIME")
										:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "SCISSORING_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true));
							}
						});
						
					} else {
						responses.add(new ResponseSex(
								"Scissoring",
								"Suggest that the two of you scissor with one another...",
								true, true,
								getHelenaSexManager(false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.SCISSORING,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_SCISSORING")
								+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT))==0
								?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "SCISSORING_FIRST_TIME")
								:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "SCISSORING_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), ClitClit.TRIBBING_START, false, true));
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).isVaginaVirgin()) {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Cowgirl", "As you don't have a penis, you can't get ridden by Helena...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Cowgirl",
									"Let Helena dominantly ride your cock.",
									true, true,
									getHelenaSexManager(true,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_COWGIRL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							});
						}
						
					} else { // SUBMISSIVE:
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Missionary", "As you don't have a penis, you can't dominantly fuck Helena in the missionary position...", null));
						} else {
							responses.add(new ResponseSex(
									"Missionary",
									"Dominantly fuck Helena in the missionary position.",
									true, true,
									getHelenaSexManager(false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_MISSIONARY")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
						
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Doggy-style", "As you don't have a penis, you can't dominantly fuck Helena in the doggy-style position...", null));
						} else {
							responses.add(new ResponseSex(
									"Doggy-style",
									"Dominantly fuck Helena in the doggy-style position.",
									true, true,
									getHelenaSexManager(false,
											SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_DOGGY")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
						
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Mating press", "As you don't have a penis, you can't dominantly breed Helena...", null));
						} else if(Main.game.getNpc(Helena.class).isVisiblyPregnant()) {
							responses.add(new Response("Mating press", "As Helena already has a swollen, pregnant belly, she wouldn't want you to push her down into this position...", null));
						} else {
							responses.add(new ResponseSex(
									"Mating press",
									"Push Helena down onto her back and immediately start breeding her.",
									true, true,
									getHelenaSexManager(false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MATING_PRESS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_MATING_PRESS")) {
								@Override
								public void effects() {
									if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE));
									}
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
					
				} else {
					if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)
							|| !Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
						responses.add(new Response("Virginity", "Helena isn't going to want to discuss losing her virginity until she's both received and given oral...", null));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateVirginityTalk)) {
						responses.add(new Response(
								"Virginity",
								"Helena has already told you that she's unwilling to lose her virginity."
									+ "<br/>[style.italicsMinorGood(Perhaps you could arrange something with Scarlett the next time you take Helena on a date...)]",
								null));
						
					} else {
						responses.add(new Response("Virginity", "Ask Helena if she's ready to lose her virginity to you.", DATE_APARTMENT_BEDROOM) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_VIRGINITY_TALK"));
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaDateVirginityTalk, true);
							}
						});
					}
				}
				
				
				// Anal:
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
						responses.add(new Response("Perform anilingus", "Helena will not want to receive anilingus until after she's received cunnilingus from you.", null));
						
					} else if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						responses.add(new ResponseSex(
								"Anilingus face-sitting",
								"Get Helena to sit on your face so that you can perform anilingus on her.",
								true, true,
								getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_PERFORM_ANILINGUS_FACESITTING")
								+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ANILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ANILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
							}
						});
						
					} else {
						responses.add(new ResponseSex(
								"Perform anilingus",
								"Get Helena to present her ass to you so that you can perform anilingus on her.",
								true, true,
								getHelenaSexManager(false,
										SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_PERFORM_ANILINGUS")
								+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ANILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ANILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_RECEIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
							}
						});
					}
					
					if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
						responses.add(new Response("Receive anilingus", "Helena will only be willing to perform anilingus on you once you've done it to her...", null));
						
					} else {
						if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_SUBMISSIVE)) {
							responses.add(new ResponseSex(
									"Anilingus face-sitting",
									"Sit on Helena's face so that she can perform anilingus on you.",
									true, true,
									getHelenaSexManager(false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_RECEIVE_ANILINGUS_FACESITTING")
									+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
										?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "PERFORMING_ANILINGUS_FIRST_TIME")
										:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "PERFORMING_ANILINGUS_EXPERIENCED"))) {
								@Override
								public void effects() {
									if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE));
									}
									if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
									}
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
								}
							});
							
						} else {
							responses.add(new ResponseSex(
									"Receive anilingus",
									"Get on all fours and present your ass to Helena so that she can perform anilingus on you.",
									true, true,
									getHelenaSexManager(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT),
											SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND_ORAL, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_RECEIVE_ANILINGUS")
									+ (Main.game.getNpc(Helena.class).getSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
										?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "PERFORMING_ANILINGUS_FIRST_TIME")
										:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "PERFORMING_ANILINGUS_EXPERIENCED"))) {
								@Override
								public void effects() {
									if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE));
									}
									if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
									}
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
									} else {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
									}
								}
							});
						}
					}
					
					if(Main.game.getNpc(Helena.class).isAssVirgin()) {
						if(!Main.game.getPlayer().hasPenis()) {
							responses.add(new Response("Take anal virginity", "As you don't have a penis, you can't take Helena's anal virginity...", null));
							
						} else if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
							responses.add(new Response("Take anal virginity",
									"Helena isn't at all interested in losing her anal virginity. Perhaps if you eased her into it by performing anilingus on her first, she could be convinced otherwise...",
									null));
							
						} else {
							responses.add(new ResponseSex(
									"Take anal virginity",
									"Suggest to Helena that you could take her anal virginity...",
									true, true,
									getHelenaSexManager(false,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexPosition.LYING_DOWN
												:SexPosition.ALL_FOURS,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexSlotLyingDown.COWGIRL
												:SexSlotAllFours.ALL_FOURS,
											Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)
												?SexSlotLyingDown.LYING_DOWN
												:SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									DATE_APARTMENT_BEDROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_ANAL_VIRGINITY")) {
								@Override
								public void effects() {
									if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
									}
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ANAL_RECEIVING));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
									} else {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
									}
								}
							});
						}
						
					} else {
						if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
							if(!Main.game.getPlayer().hasPenis()) {
								responses.add(new Response("Cowgirl (anal)", "As you don't have a penis, you can't get Helena to anally ride your cock...", null));
							} else {
								responses.add(new ResponseSex(
										"Cowgirl (anal)",
										"Let Helena use her ass to dominantly ride your cock.",
										true, true,
										getHelenaSexManager(false,
												SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										DATE_APARTMENT_BEDROOM_AFTER_SEX,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_COWGIRL_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
									}
								});
							}
							
						} else {
							if(!Main.game.getPlayer().hasPenis()) {
								responses.add(new Response("Doggy-style (anal)", "As you don't have a penis, you can't dominantly fuck Helena's ass in the doggy-style position...", null));
							} else {
								responses.add(new ResponseSex(
										"Doggy-style (anal)",
										"Dominantly fuck Helena's ass in the doggy-style position.",
										true, true,
										getHelenaSexManager(false,
												SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										DATE_APARTMENT_BEDROOM_AFTER_SEX,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_DOGGY_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
									}
								});
							}
							
							if(!Main.game.getPlayer().hasPenis()) {
								responses.add(new Response("Over desk (anal)", "As you don't have a penis, you can't dominantly bend Helena over her desk and fuck her ass...", null));
							} else {
								responses.add(new ResponseSex(
										"Over desk (anal)",
										"Dominantly bend Helena over her desk and fuck her ass.",
										true, true,
										getHelenaSexManager(false,
												SexPosition.OVER_DESK, SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.BETWEEN_LEGS,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										DATE_APARTMENT_BEDROOM_AFTER_SEX,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_DESK_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
									}
								});
							}
						}
					}
				}
				
				if(Main.game.isFootContentEnabled() && Main.game.getPlayer().hasPenis()) {
					if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("Talon-job", "As you are a taur, you can't get into a suitable position in which Helena could give you a talon-job...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Talon-job",
								"Ask Helena to use her talons to jerk you off.",
								true, true,
								getHelenaSexManager(true,
										SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								DATE_APARTMENT_BEDROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_TALONJOB")) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_FOOT_GIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_FOOT_GIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisFoot.FOOT_JOB_SINGLE_GIVING_START, false, true));
							}
						});
					}
				}
				
				// TODO Extras:
				// Scarlett threesome
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX = new DialogueNode("Finished", "Helena seems to have had enough and pulls away...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave",
						"Decide not to take Helena's virginity and instead tell her that you need to leave now.",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_LEAVE"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-5));
					}
				};
				
			} else if(index==1) {
				return new Response("Wash & sleep",
						"Decide not to take Helena's virginity and instead use her bathroom to wash yourself clean and then join her in her bed.",
						DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP);
				
			} else if(index==2) {
				return new ResponseSex(
						"Take virginity (as dom)",
						(Main.game.getPlayer().isTaur()
								?"Mount Helena and take her virginity in the doggy-style position."
								:"Take Helena's virginity in the missionary position.")
							+ "<br/>[style.italicsSub(This will cause Helena to permanently gain the submissive fetish!)]",
						true, true,
						getHelenaSexManager(false,
								Main.game.getPlayer().isTaur()
									?SexPosition.ALL_FOURS
									:SexPosition.LYING_DOWN,
								Main.game.getPlayer().isTaur()
									?SexSlotAllFours.ALL_FOURS
									:SexSlotLyingDown.LYING_DOWN,
								Main.game.getPlayer().isTaur()
									?SexSlotAllFours.BEHIND
									:SexSlotLyingDown.MISSIONARY,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
						null,
						null,
						DATE_APARTMENT_BEDROOM_AFTER_VIRGINITY_LOST_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_VIRGINITY_SUB")) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_SUBMISSIVE));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_VAGINAL_RECEIVING));
						if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new ResponseSex(
						"Take virginity (as sub)",
						"Let Helena lose her virginity at her own pace by letting her ride your cock.<br/>[style.italicsSub(This will cause Helena to permanently gain the dominant fetish!)]",
						true, true,
						getHelenaSexManager(true,
								SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
						null,
						null,
						DATE_APARTMENT_BEDROOM_AFTER_VIRGINITY_LOST_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_AFTER_ROMANCE_SEX_VIRGINITY_DOM")) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_DOMINANT));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_VAGINAL_RECEIVING));
						if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_PENIS_RECEIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_PENIS_RECEIVING));
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX = new DialogueNode("Finished", "Helena seems to have had enough and pulls away...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaBedroomFromNest)) {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_FROM_NEST");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaBedroomFromNest)) { // Helena & the player have entered her bedroom directly from the nest, and so should return there:
				if(index==1) {
					return new Response("Return",
							"Tell Helena that you're ready to return to her nest.",
							HarpyNestHelena.HELENAS_NEST_RETURN_AFTER_SEX) {
						@Override
						public void effects() {
							Main.game.getNpc(Helena.class).cleanAllClothing(true);
							Main.game.getNpc(Helena.class).cleanAllDirtySlots();
							Main.game.getNpc(Helena.class).equipClothing();
							
							Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
							Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaBedroomFromNest, false);
						}
					};
				}
				
			} else {
				if(index==0) {
					return new Response("Leave",
							"Tell Helena that you should be leaving right away...",
							DATE_APARTMENT_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_LEAVE"));
							Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-2));
						}
					};
					
				} else if(index==1) {
					return new Response("Wash & sleep",
							"Use Helena's bathroom to wash yourself clean and then join her in her bed.",
							DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP);
				}
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_VIRGINITY_LOST_SEX = new DialogueNode("Finished", "Helena seems to have had enough and pulls away...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_VIRGINITY_LOST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave",
						"Tell Helena that you should be leaving right away...",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_VIRGINITY_LOST_SEX_LEAVE"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-10));
					}
				};
				
			} else if(index==1) {
				return new Response("Wash & sleep",
						"Use Helena's bathroom to wash yourself clean and then join her in her bed.",
						DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP);
			}
			
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().cleanAllClothing(true);
			Main.game.getPlayer().cleanAllDirtySlots();
			Main.game.getNpc(Helena.class).cleanAllClothing(true);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots();
			((Helena)Main.game.getNpc(Helena.class)).applyDressForMorning();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave",
						"Tell Helena that you should be leaving right away...",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_LEAVE"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-1));
					}
				};
				
			} else if(index==1) {
				return new Response("Breakfast",
						"Take up Helena's offer of having breakfast with her before leaving.",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_SLEEP_BREAKFAST"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			} else if(index==2) { //TODO change to shower sex?
				if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					return new ResponseSex(
							"Morning sex",
							"Let Helena take charge and have sex with her.",
							true, true,
							getHelenaSexManager(true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
									null,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_COWGIRL")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueMouth.KISS_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"Morning sex",
							"Take charge and have sex with Helena.",
							true, true,
							getHelenaSexManager(false,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_MISSIONARY")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueMouth.KISS_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING = new DialogueNode("Finished", "Helena seems to have had enough and pulls away...", true) {
		@Override
		public void applyPreParsingEffects() { //TODO test
			Main.game.getPlayer().cleanAllClothing(true);
			Main.game.getPlayer().cleanAllDirtySlots();
			Main.game.getNpc(Helena.class).cleanAllClothing(true);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots();
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave",
						"Tell Helena that you should be leaving right away...",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_LEAVE"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(-1));
					}
				};
				
			} else if(index==1) {
				return new Response("Breakfast",
						"Take up Helena's offer of having breakfast with her before leaving.",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BEDROOM_AFTER_SEX_MORNING_BREAKFST"));
						Main.game.getTextEndStringBuilder().append(incrementHelenaAffection(2));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_APARTMENT_BREAKFAST = new DialogueNode("", "", true, true) {
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
				return new Response("Leave",
						"Tell Helena that you should be leaving now.",
						DATE_APARTMENT_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/helenaHotel/hotelDate", "DATE_APARTMENT_BREAKFAST_LEAVE"));
					}
				};
			}
			return null;
		}
	};
	
	
}
