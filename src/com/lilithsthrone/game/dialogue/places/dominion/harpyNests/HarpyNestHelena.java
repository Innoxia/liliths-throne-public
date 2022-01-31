package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class HarpyNestHelena {
	
	private static float randomChance = 0f;
	
	private static SexManagerInterface getScarlettSexManager(AbstractSexPosition position,
			SexSlot scarlettSlot,
			SexSlot playerSlot,
			SexType scarlettPreference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap,
			String publicSexStartDescription) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override 
			public String getPublicSexStartingDescription() {
				return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
							+ publicSexStartDescription
						+ "</p>";
			}
			@Override
			public String getRandomPublicSexDescription() {
				return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
							+ UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()),
								UtilText.returnStringAtRandom(
									"Scarlett's harpy followers smirk and make the occasional comment on your performance.",
									"One of the harpies starts making lewd comments to her friend as she watches you having sex.",
									"You several harpies commenting on your performance.",
									"Smirking and giggling to one another, the group of harpies watch as you continue having sex with [npc.name].",
									"You glance across to see one of the harpies touching herself as she watches you and Scarlett go at it."))
						+"</p>";
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(partner.getWorldLocation()==WorldType.HARPY_NEST) { // If this is a scene in the nest, Scarlett stops after cumming.
					return super.isPartnerWantingToStopSex(partner);
				}
				return Main.sex.isSatisfiedFromOrgasms(partner, true) && (Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true) || Main.sex.getNumberOfOrgasms(partner)>=3);
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.ONGOING_ONLY;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return character.getWorldLocation()!=WorldType.HARPY_NEST;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return character.getWorldLocation()!=WorldType.HARPY_NEST;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isCharacterStartNaked(GameCharacter character) {
				return !character.isPlayer() && character.getWorldLocation()==WorldType.HELENAS_APARTMENT;
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
				if(!character.isPlayer() && scarlettPreference!=null) {
					return scarlettPreference;
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
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
		};
	}
	
	private static void applyScarlettFuckedEffects() {
		Main.game.getNpc(Scarlett.class).returnToHome();
		Main.game.getNpc(Scarlett.class).equipClothing();
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettGoneHome, true);
		if(Main.game.getNpc(Scarlett.class).hasVagina()) {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer() || Math.random()<0.8f) { // If Scarlett likes the player, she won't let anyone else get her pregnant. Also 80% chance for her to force her followers to pull out or use a condom.
				Main.game.getNpc(Scarlett.class).calculateGenericSexEffects(
						true, true, null, Subspecies.HARPY, Subspecies.HARPY, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED, GenericSexFlag.PREVENT_CREAMPIE);
			} else {
				Main.game.getNpc(Scarlett.class).calculateGenericSexEffects(
						true, true, null, Subspecies.HARPY, Subspecies.HARPY, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
			}
		}
	}
	
	public static final DialogueNode HELENAS_NEST_EXTERIOR = new DialogueNode("Helena's nest", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR_STORM");
			} else if(!Main.game.isExtendedWorkTime()) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR_SLEEPING");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_EXTERIOR");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
					return new Response("Helena",
							"Helena's flock is taking shelter in the buildings below her nest. You'll have to come back after the arcane storm has passed.",
							null);
					
				} else if(!Main.game.isExtendedWorkTime()) {
					return new Response("Meet with Helena",
							Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)
								?"Both Helena and her flock are sleeping in the buildings below her nest. If you wanted to speak with Helena, you should go to her shop in Slaver Alley."
								:"Both Helena and her flock are sleeping in the buildings below her nest. You'll have to come back during the day if you want to speak with her.",
							null);
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
						return new Response("Helena", "Walk over to the tall platform to meet with Helena.", HELENAS_NEST_MAIN_QUEST);
						
					} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA) && Main.game.getCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
						return new Response("Helena", "Walk over to the tall platform to meet with Helena.", HELENAS_NEST);
						
					}  else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA)) {
						return new Response("Helena", "Helena has flown off to Slaver Alley! You'll have to find her there.", null);
						
					} else {
						return new Response("Helena", "You have no reason to talk to Helena.", null);
					}
				}
				
			} else if(index==2) {
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
					if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
						return new Response("Scarlett",
								"As there's an arcane storm currently raging overhead, Scarlett and the rest of the nest's inhabitants are sheltering in the buildings below her nest."
										+ " You'll have to come back once the storm has passed if you want to speak with her.",
								null);
						
					} else if(!Main.game.isExtendedWorkTime()) {
						return new Response("Scarlett",
								"Both Scarlett and the rest of Helena's flock are sleeping in the buildings below her nest. You'll have to come back during the day if you want to speak with her.",
								null);
						
					} else {
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
							return new Response("Scarlett", "Head over to where Scarlett is surrounded by a crowd of harpies and say hello.", HELENAS_NEST_MEETING_SCARLETT);
						}
						return new Response("Scarlett", "Head over to where Scarlett is sitting and say hello.", HELENAS_NEST_MEETING_SCARLETT);
					}
				}
				
			} else if(index==5 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaDateFirstDateComplete)) {
				return new Response("Dominion", "Use the elevator to travel down through 'The Golden Feather' and out into Dominion.", HelenaHotel.HOTEL_TRAVEL_TO_DOMINION) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HELENA_HOTEL);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST = new DialogueNode("Helena's nest", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Scarlett's woe", "Tell Helena about Scarlett's failure to run her slavery business.", HELENAS_NEST_MAIN_QUEST_SCARLETT);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_SCARLETT = new DialogueNode("Helena's nest", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("No punishment", "Don't take Scarlett's punishment for her.", HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==2) {
				return new Response("Take punishment", "Offer to take Scarlett's punishment for her.", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT,
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_MASOCHIST),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.punishedByHelena);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT = new DialogueNode("Helena's nest", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_NO_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Helena.", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT = new DialogueNode("Helena's nest", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Endure it", "Try and keep quiet and endure your punishment.", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==2) {
				return new Response("Struggle", "Start struggling and crying out in discomfort.", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
				
			} else if(index==3) {
				return new Response("Beg for more", "Beg to be punished.", HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST),
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE));
						Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE = new DialogueNode("Helena's nest", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENDURE"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Helena.", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE = new DialogueNode("Helena's nest", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_STRUGGLE"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Helena.", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					return new Response("Fly after her", "You can't fly, so you'll have to travel to Slaver Alley by foot.", null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY = new DialogueNode("Helena's nest", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_ENJOY"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_PUNISHMENT_END"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_LEAVING"));
						
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Helena.", HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT);
					
				} else {
					if(Main.game.getPlayer().isAbleToFly()) {
						return new Response("Fly after her", "As your companion is unable to fly, you'll have to travel to Slaver Alley by foot...", null);
					} else {
						return new Response("Fly after her", "As you are unable to fly, you'll have to travel to Slaver Alley by foot...", null);
					}
				}
			}
			
			return null;
			
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT = new DialogueNode("Helena's nest", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MAIN_QUEST_TAKE_FLIGHT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Scarlett's Shop", "You arrive at Scarlett's Shop.", PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	// Meeting with Helena after completing her romance quest:
	
	public static final DialogueNode HELENAS_NEST = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "Say goodbye to Helena and exit her nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
						}
					}
				};
				
			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_NEST_TALK) {
					return new Response("Talk", "You are already talking to Helena...", null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaNestTalkedTo)) {
					return new Response("Talk", "You've already spent some time talking to Helena in her nest today...", null);
				}
				return new Response("Talk", "Ask Helena how she is.", HELENAS_NEST_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaNestTalkedTo, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 2));
					}
				};
				
			} else if(index==2 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaNestFucked)) {
					return new Response("Apartment", "You've already had sex with Helena in her nest today, and she doesn't have time to do it again...", null);
				}
				return new Response("Apartment", "Accept Helena's offer of 'showing you around her apartment'.<br/>[style.italicsSex(This will lead into having sex with her...)]", HELENAS_NEST_APARTMENT_BEDROOM) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaNestFucked, true);
						if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_NEST.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_NEST_APARTMENT_BEDROOM = new DialogueNode("Helena's Bedroom", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_HELENA_BEDROOM);
			Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaBedroomFromNest, true);
			((Helena)Main.game.getNpc(Helena.class)).applyLingerie();
		}
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_APARTMENT_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HelenaHotel.DATE_APARTMENT_BEDROOM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MEETING_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_HELENA_QUEST_COMPLETE"));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT"));
			}
			Main.game.getNpc(Scarlett.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			if(index==0) {
				return new Response("Leave",
						((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()
							?"Tell Scarlett that you only wanted to stop by and say hello, and that you've got to leave now."
							:"Not wanting to put up with her awful attitude, you tell Scarlett that you're going to leave.",
						HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_STEP_BACK_POST_QUEST"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_STEP_BACK"));
						}
					}
				};
			}
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					responses.add(new Response("Servant",
							"As Scarlett is not attracted to you, she does not want you acting as her servant...",
							null));
					
				} else if(Main.game.getDayMinutes()>19*60) {
					responses.add(new Response("Servant",
							"It's too late in the day to start working as Scarlett's servant. You should try again another day before [units.time(19)]...",
							null));
					
				} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) // Performing oral
						|| (Main.game.getNpc(Scarlett.class).hasVagina() && Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) // Fucking Scarlett
						|| (Main.game.getNpc(Scarlett.class).hasVagina() && Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) // Scissoring
						|| (Main.game.isAnalContentEnabled() && Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) // Fucking Scarlett's ass
						|| (Main.game.getNpc(Scarlett.class).hasPenis() && Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) // Getting anally fucked
						|| (Main.game.getNpc(Scarlett.class).hasPenis() && Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) // Getting fucked
						) {
					responses.add(new Response("Servant",
							((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()
								?"Tell Scarlett that you'd like to repay her kindness towards you by acting as her servant for the day."
								:"Work as Scarlett's servant for the day to get a chance at fucking her.",
							HELENAS_NEST_SCARLETTS_SERVANT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getPlayer().removeAllCompanions(true);
						}
					});
					
				} else {
					responses.add(new Response("Servant",
							"You need to be able to access your mouth or genitals in order to work as Scarlett's servant...",
							null));
				}
				
				if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.scarlettRelaxed)) {
						responses.add(new Response("Relax",
								"Scarlett has already spent time relaxing with you today. If you wanted to spend more time with her, you'll have to return tomorrow.",
								null));
					} else {
						responses.add(new Response("Relax",
								"Accept Scarlett's invitation to sit down and relax with her for a while.",
								HELENAS_NEST_SCARLETT_RELAX) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettRelaxed, true);
							}
						});
					}
				}
				
			} else {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) { // Scarlett has a penis (this should always be the case, as Scarlett only loses her vagina after Helena's romance quest is complete):
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
							responses.add(new Response("Offer ass", "Scarlett is not attracted to you, and so is unwilling to have sex with you.", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							responses.add(new Response("Offer ass", "Scarlett is only interested in fucking your ass, and as you can't get access to it, she's not interested in having sex with you.", null));
							
						} else {
							responses.add(new ResponseSex("Offer ass",
									"Tell Scarlett that if that's what she wants, then of course she can fuck your ass.",
									true, false,
									new SMAllFours(
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getMainSexPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.equals(Main.game.getNpc(Scarlett.class))) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
											}
											return character.getMainSexPreference(targetedCharacter);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.equals(Main.game.getNpc(Scarlett.class));
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
											map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
											return map;
										}
									},
									null,
									null,
									AFTER_SCARLETT_SEX,
									UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX")) {
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
								}
							});
						}
						
					} else { // If anal content is off, Scarlett will fuck the player's pussy or receive oral:
						if(Main.game.getPlayer().hasVagina()) {
							if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
								responses.add(new Response("Offer pussy", "Scarlett is not attracted to you, and so is unwilling to have sex with you.", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								responses.add(new Response("Offer pussy", "Scarlett is only interested in fucking your pussy, and as you can't get access to it, she's not interested in having sex with you.", null));
								
							} else {
								responses.add(new ResponseSex("Offer pussy",
										"Tell Scarlett that if that's what she wants, then of course she can fuck your pussy.",
										true, false,
										new SMAllFours(
												Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotAllFours.BEHIND)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
											@Override
											public boolean isPublicSex() {
												return false;
											}
											@Override
											public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
												return getMainSexPreference(character, targetedCharacter);
											}
											@Override
											public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
												if(character.equals(Main.game.getNpc(Scarlett.class))) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
												return character.getMainSexPreference(targetedCharacter);
											}
											@Override
											public boolean isCharacterStartNaked(GameCharacter character) {
												return character.equals(Main.game.getNpc(Scarlett.class));
											}
											@Override
											public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
												Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
												map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
												return map;
											}
										},
										null,
										null,
										AFTER_SCARLETT_SEX,
										UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX_VAGINA")) {
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(
												new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
									}
								});
							}
							
						} else {
							if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
								responses.add(new Response("Offer oral", "Scarlett is not attracted to you, and so is unwilling to have sex with you.", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								responses.add(new Response("Offer oral", "Scarlett is only interested in receiving oral, and as you can't get access to your mouth, she's not interested in having sex with you.", null));
								
							} else {
								responses.add(new ResponseSex("Offer oral",
										"Tell Scarlett that if that's what she wants, then of course you'll suck her cock.",
										true, false,
										new SMStanding(
												Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), SexSlotStanding.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
											@Override
											public boolean isPublicSex() {
												return false;
											}
											@Override
											public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
												return getMainSexPreference(character, targetedCharacter);
											}
											@Override
											public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
												if(character.equals(Main.game.getNpc(Scarlett.class))) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												}
												return character.getMainSexPreference(targetedCharacter);
											}
											@Override
											public boolean isCharacterStartNaked(GameCharacter character) {
												return character.equals(Main.game.getNpc(Scarlett.class));
											}
											@Override
											public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
												Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
												map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
												return map;
											}
										},
										null,
										null,
										AFTER_SCARLETT_SEX,
										UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "START_SCARLETT_SEX_ORAL")) {
									public List<InitialSexActionInformation> getInitialSexActions() {
										return Util.newArrayListOfValues(
												new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
									}
								});
							}
						}
					}
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				responses.add(new Response("Helena", "Tell Scarlett that Helena is requesting her presence back at her shop in Slaver Alley.", HELENAS_NEST_MEETING_SCARLETT_TO_SHOP) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettToldToReturn, true);
					}
				});
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_MEETING_SCARLETT_TO_SHOP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_TO_SHOP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Step back", "Now that your work here is done, there's nothing left to do but step back and prepare to travel back down to Helena's store in Slaver Alley...", HELENAS_NEST_EXTERIOR);
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Fly after her", "Take off and fly after Scarlett.", ScarlettsShop.ROMANCE_SHOP_CORE) {
						@Override
						public void effects() {
							// Move them both here to make sure they haven't gone due to time ticking over into night time when player arrives:
							Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
							Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_MEETING_SCARLETT_TO_SHOP_FLY_AFTER"));
						}
					};
					
				} else {
					if(Main.game.getPlayer().isAbleToFly()) {
						return new Response("Fly after her", "As your companion is unable to fly, you cannot fly after Scarlett...", null);
					} else {
						return new Response("Fly after her", "As you are unable to fly, you cannot fly after Scarlett...", null);
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SCARLETT_SEX = new DialogueNode("Finished", "Scarlett has had enough for now...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0
					|| Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH))>0) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX_ORAL");
				
			} else if(Main.sex.getSexTypeCount(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0) {
				return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX_VAGINA");
			}
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Do as Scarlett says and leave the nest...", HELENAS_NEST_EXTERIOR);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETT_RELAX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response("Apartment", "As Scarlett is not attracted to you, she will not invite you to spend some time with her in her room...", null);
				}
				return new Response("Apartment",
						"Accept Scarlett's invitation to spend some time with her in her room.",
						HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeAllCompanions(true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX_APARTMENT"));
					}
				};
				
			} else if(index==2) {
				return new Response(
						!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())
							?"Leave"
							:"Decline",
						!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())
							?"Say goodbye to Scarlett and head out of Helena's nest."
							:"Tell Scarlett that you've got other matters which require your attention today, before taking your leave and heading back out of Helena's nest.",
						HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETT_RELAX_LEAVE"));
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			responses.add(new Response("Back massage", "Choose to massage Scarlett's back...", HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
				}
			});
			
			responses.add(new Response("Groom wings", "Choose to groom Scarlett's wings...", HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
				}
			});
			
			if(Main.game.isFootContentEnabled()) {
				responses.add(new Response("Talons", "Choose to massage Scarlett's bird-like feet...", HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				});
			}

			if(index!=0 && index-1<responses.size()) {
				return responses.get(index-1);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE"));
			Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(Main.game.getNpc(Scarlett.class).getClothingBlockingCoverableAreaAccess(CoverableArea.BACK, false), true, Main.game.getNpc(Scarlett.class));
		}
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Hold back", "Choose to hold back and wait until later for Scarlett's reward.", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_HOLD_BACK"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Blowjob", "As you are unable to access your mouth, you cannot perform oral on Scarlett...", null);
					}
					return new ResponseSex(
							"Blowjob",
							"Take your reward now and suck Scarlett's cock while the other harpies continue to pamper her...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"Scarlett's harpy followers continue to pamper her while you get started on sucking her cock..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Cunnilingus", "As you are unable to access your mouth, you cannot perform oral on Scarlett...", null);
					}
					return new ResponseSex(
							"Cunnilingus",
							"Take your reward now and orally service Scarlett's pussy while the other harpies continue to pamper her...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"Scarlett's harpy followers continue to pamper her while you get started on licking her pussy..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_BACK_MASSAGE_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Hold back", "Choose to hold back and wait until later for Scarlett's reward.", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_HOLD_BACK"));
					}
				};
				
			} else if(index==2)  {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"Handjob",
							"Take your reward now and jerk off Scarlett's cock while the other harpies continue to pamper her...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS))),
									"Scarlett's harpy followers continue to pamper her while you get started on jerking off her cock..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_HANDJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), FingerPenis.COCK_MASTURBATING_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"Finger her",
							"Take your reward now and finger Scarlett's pussy while the other harpies continue to pamper her...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))),
									"Scarlett's harpy followers continue to pamper her while you get started on fingering her pussy..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_GROOM_WINGS_FINGERING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), FingerVagina.FINGERING_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Hold back", "Choose to hold back and wait until later for Scarlett's reward.", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_HOLD_BACK"));
						Main.game.getNpc(Scarlett.class).setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Blowjob", "As you are unable to access your mouth, you cannot perform oral on Scarlett...", null);
					}
					return new ResponseSex(
							"Blowjob",
							"Take your reward now and suck Scarlett's cock while the other harpies continue to pamper her...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"Scarlett's harpy followers continue to pamper her while you get started on sucking her cock..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Cunnilingus", "As you are unable to access your mouth, you cannot perform oral on Scarlett...", null);
					}
					return new ResponseSex(
							"Cunnilingus",
							"Take your reward now and orally service Scarlett's pussy while the other harpies continue to pamper her...",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									"Scarlett's harpy followers continue to pamper her while you get started on licking her pussy..."),
							null,
							null,
							AFTER_SCARLETT_SERVANT_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
				
			} else if(index==3) {
				if(!Main.game.getPlayer().hasPenis()) {
					return new Response("Talon-job", "As you don't have a penis, you cannot receive a talon-job from Scarlett...", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Talon-job", "As you can't get access to your penis, you cannot receive a talon-job from Scarlett...", null);
				}
				return new ResponseSex(
						"Talon-job",
						"Take your reward now and have Scarlett use her talons to jerk you off while the other harpies continue to pamper her...",
						true,
						false,
						getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
								"Scarlett's harpy followers continue to pamper her while she jerks you off using her bird-like talons..."),
						null,
						null,
						AFTER_SCARLETT_SERVANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FOOT_MASSAGE_TALONJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisFeet.FOOT_JOB_DOUBLE_RECEIVING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SCARLETT_SERVANT_SEX = new DialogueNode("Finished", "Scarlett has had enough for now...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Continue on your way over to the entrance of Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						applyScarlettFuckedEffects();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			randomChance = (float) Math.random();
			Main.game.getNpc(Scarlett.class).equipClothing();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60) * 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back out", "Back out of this and tell Scarlett that you're going to leave...", HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE);
				
			} else if(index==1) {
				return new Response("Kiss feet",
						"Kiss Scarlett's feet.<br/>[style.italicsExcellent(She will definitely choose you as her partner if you do this!)]",
						HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_KISS_FEET"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index==2) {
				return new Response("Bow down",
						"Bow down in front of Scarlett.<br/>[style.italicsGood(She is likely, but not certain, to choose you as her partner if you do this.)]",
						randomChance<0.75f
							?HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX
							:HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						if(randomChance<0.75f) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_BOW_DOWN"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_BOW_DOWN_NOT_CHOSEN"));
						}
					}
				};
				
			} else if(index==3) {
				return new Response("Flatter",
						"Do your best to flatter and impress Scarlett.<br/>[style.italicsMinorGood(She is just as likely as not to choose you as her partner if you do this.)]",
						randomChance<0.5f
							?HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX
							:HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						if(randomChance<0.5f) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_FLATTER"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_CHOSEN"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_FLATTER_NOT_CHOSEN"));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_LEAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Exit Helena's nest.", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						applyScarlettFuckedEffects();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NOT_CHOSEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applyScarlettFuckedEffects();
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
				return new Response("Leave", "Leave the nest and hope to be chosen by Scarlett the next time you decide to act as her servant...", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).returnToHome();
			Main.game.getPlayer().setLocation(Main.game.getNpc(Scarlett.class), false);
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
			
			responses.add(new ResponseSex(
					"No preference",
					"Tell Scarlett that you have no preference in how you want her to fuck you, and that she can figure it out after getting started...",
					true,
					false,
					getScarlettSexManager(SexPosition.STANDING, SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_SUBMISSIVE,
							null,
							Util.newHashMapOfValues(),
							""),
					null,
					null,
					AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
					UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_NO_PREFERENCE")) {
			});
			
			if(Main.game.getNpc(Scarlett.class).hasPenis()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("Blowjob", "As you cannot access your mouth, you cannot suck Scarlett's cock...", null));
					
				} else {
					responses.add(new ResponseSex(
							"Blowjob",
							"Tell Scarlett that you want to suck her cock.",
							true,
							false,
							getScarlettSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									""),
							null,
							null,
							AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_BLOWJOB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					});
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						responses.add(new Response("Anal", "As you cannot gain access to your ass, you cannot ask Scarlett to anally fuck you...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Anal",
								"Ask Scarlett to fuck your ass.",
								true,
								false,
								getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_ANAL")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
						});
					}
				}
				
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("Fucked", "As you cannot gain access to your pussy, you cannot ask Scarlett to fuck you...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Fucked",
								"Ask Scarlett to fuck your pussy.",
								true,
								false,
								getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_VAGINAL")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
				}
				
			} else { // Scarlett has a pussy:
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("Cunnilingus", "As you cannot access your mouth, you cannot perform oral on Scarlett...", null));
					
				} else {
					responses.add(new ResponseSex(
							"Cunnilingus",
							"Tell Scarlett that you want to eat her out.",
							true,
							false,
							getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH))),
									""),
							null,
							null,
							AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
							UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
				}

				if(Main.game.getPlayer().hasVagina()) {
					if(Main.game.getPlayer().isTaur()) {
						responses.add(new Response("Scissor", "As you are a taur, you can't get into a suitable position in which to scissor with Scarlett...", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Scissor", "As you cannot gain access to your pussy, you cannot ask to scissor with Scarlett...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Scissor",
								"Ask Scarlett if you can scissor with her.",
								true,
								false,
								getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SCISSORING")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true));
							}
						});
					}
				}
				
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Fuck her", "As you cannot gain access to your cock, you cannot ask to fuck Scarlett's pussy...", null));
						
					} else {
						responses.add(new ResponseSex(
								"Fuck her",
								"Tell Scarlett that you want to fuck her pussy.",
								true,
								false,
								getScarlettSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
										""),
								null,
								null,
								AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
								UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_FUCK_HER")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
					
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Anal", "As you cannot gain access to your cock, you cannot ask to fuck Scarlett's ass...", null));
							
						} else {
							responses.add(new ResponseSex(
									Main.game.getNpc(Scarlett.class).isAssVirgin()
										?"Take anal virginity"
										:"Anal",
									Main.game.getNpc(Scarlett.class).isAssVirgin()
										?"Ask Scarlett if you can take her anal virginity and fuck her ass."
										:"Ask Scarlett if you can fuck her ass.",
									true,
									false,
									getScarlettSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS))),
											""),
									null,
									null,
									AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX,
									UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "HELENAS_NEST_SCARLETTS_SERVANT_FINAL_REWARD_SEX_FUCK_HER_ASS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), PenisAnus.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
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
	
	public static final DialogueNode AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX = new DialogueNode("Finished", "Scarlett has had enough for now...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.scarlettGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "Tell Scarlett that you need to be leaving now and ride the elevator back up to Helena's nest...", HELENAS_NEST_EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
				
			} else if(index==1) {
				return new Response("Sleep over", "Agree to spend the night with Scarlett.", AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(60*8) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/harpyNests/helena", "AFTER_SCARLETT_SERVANT_FINAL_REWARD_SEX_SLEEP_OVER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on over to the exit of Helena's nest...", HELENAS_NEST_EXTERIOR);
			}
			return null;
		}
	};
	
}
