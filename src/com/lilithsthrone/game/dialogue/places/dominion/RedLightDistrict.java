package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Angel;
import com.lilithsthrone.game.character.npc.dominion.Bunny;
import com.lilithsthrone.game.character.npc.dominion.Loppy;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.2
 * @author Innoxia
 */
public class RedLightDistrict {
	
	public static boolean isSpaceForMoreProstitutes() {
		Cell[][] grid = Main.game.getWorlds().get(WorldType.ANGELS_KISS_GROUND_FLOOR).getGrid();
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(PlaceType.ANGELS_KISS_BEDROOM)
						&& Main.game.getCharactersPresent(WorldType.ANGELS_KISS_GROUND_FLOOR, new Vector2i(i, j)).isEmpty()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Red-light District", "Red-light District", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/redLightDistrict", "OUTSIDE"));
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/redLightDistrict", "OUTSIDE_STORM"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/redLightDistrict", "OUTSIDE_NO_STORM"));
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/redLightDistrict", "OUTSIDE_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Angel's Kiss", "Step inside the large brothel named 'Angel's Kiss'."){
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelIntroduced)) {
							Main.game.getNpc(Angel.class).setLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_ENTRANCE, false);
						}
						Main.mainController.moveGameWorld(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_ENTRANCE = new DialogueNode("Entrance", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelIntroduced);
		}
		
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ENTRANCE");
				
			} else {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ENTRANCE_REPEAT"));

				List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
				charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
				
				if(charactersPresent.isEmpty()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ENTRANCE_REPEAT_EMPTY"));
				}else {
					UtilText.nodeContentSB.append(UtilText.parse(charactersPresent.get(0), UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "ENTRANCE_REPEAT_STAFFED")));
				}
				
				return UtilText.nodeContentSB.toString();
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelIntroduced)) {
				if (index == 1) {
					return new Response("Continue", "Angel leaves you alone to explore Angel's Kiss by yourself...", ANGELS_KISS_ENTRANCE){
						@Override
						public void effects() {
							Main.game.getNpc(Angel.class).setLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_OFFICE, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.angelIntroduced, true);
						}
					};
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new ResponseEffectsOnly("Exit", "Exit the brothel and head back out into Dominion."){
						@Override
						public void effects() {
							Main.mainController.moveGameWorld(WorldType.DOMINION, PlaceType.DOMINION_RED_LIGHT_DISTRICT, true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_CORRIDOR = new DialogueNode("Corridor", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_STAIRS_UP = new DialogueNode("Staircase Up", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "STAIRS_UP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Upstairs", "Go up the staircase to the first floor of the brothel."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_STAIRCASE_DOWN, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_STAIRS_DOWN = new DialogueNode("Staircase Down", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "STAIRS_DOWN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Downstairs", "Go down the staircase to the ground floor of the brothel."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_STAIRCASE_UP, true);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ANGELS_KISS_BEDROOM = new DialogueNode("Bedroom", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM"));
			
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			if(charactersPresent.size()>1){
				NPC prostitute = charactersPresent.get(0);
				NPC client = charactersPresent.get(1);
				for(NPC npc : charactersPresent) {
					if(npc instanceof GenericSexualPartner) {
						client = npc;
					} else {
						prostitute = npc;
						Main.game.setActiveNPC(npc);
					}
				}
				
				
				if(prostitute.isSlave() && prostitute.getOwner().isPlayer()) {
					//TODO append description of sex type
//					SlaveryEventLogEntry currentSex = null;
//					long time = 0;
//					for(SlaveryEventLogEntry entry : Main.game.getSlaveryEventLog().get(Main.game.getDayNumber())) {
//						if(entry.getSlaveID().equals(prostitute.getId()) && entry.getTime()>time) {
//							currentSex = entry;
//						}
//					}
//					System.out.println(currentSex.getTags().get(0));
					
					return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_OCCUPIED_SLAVE_SEX", Util.newArrayListOfValues(prostitute, client)); //TODO need obedience/affection variations

				} else {
					return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_OCCUPIED_SEX", Util.newArrayListOfValues(prostitute, client));
				}
				
			} else if(charactersPresent.isEmpty()) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
					if(Main.game.getPlayer().getWorldLocation()==WorldType.ANGELS_KISS_GROUND_FLOOR) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_EMPTY_WHORE_SELF_GROUND_FLOOR"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_EMPTY_WHORE_SELF"));
					}
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_EMPTY"));
				}
				
			} else {
				Main.game.setActiveNPC(charactersPresent.get(0));
				if(charactersPresent.get(0).isSlave() && charactersPresent.get(0).getOwner().isPlayer()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_OCCUPIED_SLAVE", Util.newArrayListOfValues(charactersPresent.get(0)))); //TODO need obedience/affection variations
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_OCCUPIED", Util.newArrayListOfValues(charactersPresent.get(0))));
				}
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			if(!charactersPresent.isEmpty()) {
				int cost = 300;
				NPC npc = charactersPresent.get(0);

				if(charactersPresent.size()<=1){
					if(charactersPresent.get(0).isSlave() && charactersPresent.get(0).getOwner().isPlayer()) {
						if (index == 1) {
								return new ResponseSex("Sex",
										UtilText.parse(npc, "Have sex with [npc.name], with you as the dominant partner."),
										true, false,
										new SMGeneric(
												Util.newArrayListOfValues(Main.game.getPlayer()),
												Util.newArrayListOfValues(npc),
										null,
										null) {
											public SexPace getStartingSexPaceModifier(GameCharacter character) {
												if(!character.isPlayer()) {
													return SexPace.SUB_NORMAL;
												}
												return super.getStartingSexPaceModifier(character);
											}
										},
										AFTER_SEX_PROSTITUTE,
										UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_PROSTITUTE_SLAVE_SEX"));
							
						} else if (index == 2) {
								return new ResponseSex("Submissive Sex",
										UtilText.parse(npc, "Let [npc.name] take charge, allowing you to have submissive sex with [npc.herHim]."),
										true, false,
										new SMGeneric(
												Util.newArrayListOfValues(npc),
												Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null){
											public SexPace getStartingSexPaceModifier(GameCharacter character) {
												if(!character.isPlayer()) {
													return SexPace.DOM_NORMAL;
												}
												return super.getStartingSexPaceModifier(character);
											}
										},
										AFTER_SEX_PROSTITUTE,
										UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_PROSTITUTE_SLAVE_SEX_SUB"));
							
						}
						
						
					} else {
						if (index == 1) {
							if(Main.game.getPlayer().getMoney()<cost) {
								return new Response("Sex ("+UtilText.formatAsMoney(cost, "span")+")",
										UtilText.parse(npc, "You don't have "+cost+" flames, so you can't afford to have sex with [npc.name]."), null);
								
							} else {
								return new ResponseSex("Sex ("+UtilText.formatAsMoney(cost, "span")+")",
										UtilText.parse(npc, "Pay "+cost+" flames to have sex with [npc.name], with you as the dominant partner."),
										true, false,
										new SMGeneric(
												Util.newArrayListOfValues(Main.game.getPlayer()),
												Util.newArrayListOfValues(npc),
										null,
										null){
											public SexPace getStartingSexPaceModifier(GameCharacter character) {
												if(!character.isPlayer()) {
													return SexPace.SUB_NORMAL;
												}
												return super.getStartingSexPaceModifier(character);
											}
										},
										AFTER_SEX_PROSTITUTE,
										UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_PROSTITUTE_SEX")) {
									@Override
									public void effects() {
										Main.game.getPlayer().incrementMoney(-cost);
									}
								};
							}
							
						} else if (index == 2) {
							if(Main.game.getPlayer().getMoney()<cost) {
								return new Response("Submissive Sex ("+UtilText.formatAsMoney(cost, "span")+")",
										UtilText.parse(npc, "You don't have "+cost+" flames, so you can't afford to have submissive sex with [npc.name]."), null);
								
							} else {
								return new ResponseSex("Submissive Sex ("+UtilText.formatAsMoney(cost, "span")+")",
										UtilText.parse(npc, "Pay "+cost+" flames to let [npc.name] take charge, allowing you to have submissive sex with [npc.herHim]."),
										true, false,
										new SMGeneric(
												Util.newArrayListOfValues(npc),
												Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null){
											public SexPace getStartingSexPaceModifier(GameCharacter character) {
												if(!character.isPlayer()) {
													return SexPace.DOM_NORMAL;
												}
												return super.getStartingSexPaceModifier(character);
											}
										},
										AFTER_SEX_PROSTITUTE,
										UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_PROSTITUTE_SEX_SUB")) {
									@Override
									public void effects() {
										Main.game.getPlayer().incrementMoney(-cost);
									}
								};
							}
							
						}
					}
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained) && Main.game.getPlayer().getWorldLocation()==WorldType.ANGELS_KISS_FIRST_FLOOR) {
				if(index==1) {
					return new Response("Sell body (Sub)", "Tell Angel that you've like to act as the submissive partner, and then wait around for a client to show up.", ANGELS_KISS_SELL_SELF_SUB){
						@Override
						public void effects() {
							NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
							if(Math.random()<0.4f) {
								npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
							} else {
								if(Main.game.getPlayer().isFeminine()) {
									npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
								} else {
									npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
								}
							}
							npc.removeFetish(Fetish.FETISH_SUBMISSIVE);
							npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
							try {
								Main.game.addNPC(npc, false);
								Main.game.setActiveNPC(npc);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
					
				} else if(index==2) {
					return new Response("Sell body (Dom)", "Tell Angel that you've like to act as the dominant partner, and then wait around for a client to show up.", ANGELS_KISS_SELL_SELF_DOM){
						@Override
						public void effects() {
							NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
							if(Math.random()<0.4f) {
								npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
							} else {
								if(Main.game.getPlayer().isFeminine()) {
									npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
								} else {
									npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
								}
							}
							npc.removeFetish(Fetish.FETISH_DOMINANT);
							npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
							try {
								Main.game.addNPC(npc, false);
								Main.game.setActiveNPC(npc);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PROSTITUTE = new DialogueNode("Bedroom", "Disentangle yourself from [npc.namePos] clutches.", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			if(Sex.getNumberOfOrgasms(charactersPresent.get(0))==0) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "PROSTITUTE_AFTER_SEX_NO_ORGASM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "PROSTITUTE_AFTER_SEX");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_SELL_SELF_SUB = new DialogueNode("Bedroom", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int payment = 2000;
			
			if (index == 1) {
				return new ResponseSex("Sex ("+UtilText.formatAsMoney(2000, "span")+")",
						"Accept the price of "+2000+" flames to have sex with [npc.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getActiveNPC()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_SELL_SELF_SUB,
						UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_SUB_START")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(payment);
					}
				};
				
			} else if(index == 2) {
				return new Response("Decline", "Tell [npc.name] that you're not interested in what [npc.she] has in mind...", ANGELS_KISS_SELL_SELF_DECLINE){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DECLINE")
								+UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DECLINE_SUB"));
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_SELL_SELF_DOM = new DialogueNode("Bedroom", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 25*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int payment = 2000;
			
			if (index == 1) {
				return new ResponseSex("Sex ("+UtilText.formatAsMoney(2000, "span")+")",
						"Accept the price of "+2000+" flames to have sex with [npc.name].",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getActiveNPC()),
						null,
						null), AFTER_SEX_SELL_SELF_DOM, UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DOM_START")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(payment);
					}
				};
				
			} else if(index == 2) {
				return new Response("Decline", "Tell [npc.name] that you're not interested in what [npc.she] has in mind...", ANGELS_KISS_SELL_SELF_DECLINE){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DECLINE")
								+UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DECLINE_DOM"));
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_SELL_SELF_DECLINE = new DialogueNode("Bedroom", ".", false, true) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ANGELS_KISS_BEDROOM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX_SELL_SELF_DOM = new DialogueNode("Bedroom", "Disentangle yourself from [npc.namePos] clutches.", true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_DOM_AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Recover", "Recover from the exhausting sex you've just had.", ANGELS_KISS_BEDROOM){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_SELL_SELF_SUB = new DialogueNode("Bedroom", "Disentangle yourself from [npc.namePos] clutches.", true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "SELL_SELF_SUB_AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Recover", "Recover from the exhausting sex you've just had.", ANGELS_KISS_BEDROOM){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ANGELS_KISS_BEDROOM_BUNNY = new DialogueNode("Bunny's Bedroom", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bunnyIntroduced);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bunnyIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int cost = 1500;
			int threesomeCost = 5000;
			
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("Sex ("+UtilText.formatAsMoney(cost, "span")+")", "You don't have "+cost+" flames, so you can't afford to have sex with Bunny.", null);
					
				} else {
					return new ResponseSex("Sex ("+UtilText.formatAsMoney(cost, "span")+")",
							"Pay "+cost+" flames to have sex with Bunny.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Bunny.class)),
							null,
							null), AFTER_SEX_BUNNY, UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.bunnyIntroduced, true);
							Main.game.getPlayer().incrementMoney(-cost);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()<threesomeCost) {
					return new Response("Threesome ("+UtilText.formatAsMoney(threesomeCost, "span")+")", "You don't have "+threesomeCost+" flames, so you can't afford to have sex with both Bunny and Loppy at the same time.", null);
					
				} else {
					return new ResponseSex("Threesome ("+UtilText.formatAsMoney(threesomeCost, "span")+")",
							"Pay "+threesomeCost+" flames to have sex with both Bunny and Loppy at the same time.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Bunny.class), Main.game.getNpc(Loppy.class)),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_BUNNY_THREESOME,
							UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_THREESOME")
							+(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.loppyIntroduced)
									?UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_THREESOME_LOPPY_INTRODUCED")
									:UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_THREESOME_LOPPY_NOT_INTRODUCED"))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.bunnyIntroduced, true);
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
							Main.game.getNpc(Loppy.class).setLocation(WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_BEDROOM_BUNNY, false);
							Main.game.getPlayer().incrementMoney(-threesomeCost);
						}
					};
				}
				
			} else if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bunnyIntroduced) && index == 3) {
				return new Response("Decline", "You're not really interested in paying for sex with Bunny right now...", ANGELS_KISS_BEDROOM_BUNNY){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.bunnyIntroduced, true);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_DECLINE"));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_SEX_BUNNY = new DialogueNode("Bunny's Bedroom", "Disentangle yourself from Bunny's clutches.", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Main.game.getNpc(Bunny.class)) == 0) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_AFTER_SEX_NO_ORGASM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_AFTER_SEX");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_BUNNY_THREESOME = new DialogueNode("Bunny's Bedroom", "Disentangle yourself from Bunny's clutches.", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_BUNNY_AFTER_THREESOME");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ANGELS_KISS_BEDROOM_LOPPY = new DialogueNode("Loppy's Bedroom", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.loppyIntroduced);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.loppyIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int cost = 2000;
			int dominantCost = 2500;
			int threesomeCost = 5000;
			
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("Sex ("+UtilText.formatAsMoney(cost, "span")+")", "You don't have "+cost+" flames, so you can't afford to have sex with Loppy.", null);
					
				} else {
					return new ResponseSex("Sex ("+UtilText.formatAsMoney(cost, "span")+")",
							"Pay "+cost+" flames to have sex with Loppy.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Loppy.class)),
							null,
							null), AFTER_SEX_LOPPY, UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
							Main.game.getPlayer().incrementMoney(-cost);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()<dominantCost) {
					return new Response("Submissive Sex ("+UtilText.formatAsMoney(dominantCost, "span")+")", "You don't have "+dominantCost+" flames, so you can't afford to have submissive sex with Loppy.", null);
					
				} else {
					return new ResponseSex("Submissive Sex ("+UtilText.formatAsMoney(dominantCost, "span")+")",
							"Pay "+dominantCost+" flames to let Loppy take charge and fuck you.",
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(Loppy.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_SEX_LOPPY, UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_SEX_SUBMISSIVE")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
							Main.game.getPlayer().incrementMoney(-dominantCost);
						}
					};
				}
				
			} else if (index == 3) {
				if(Main.game.getPlayer().getMoney()<threesomeCost) {
					return new Response("Threesome ("+UtilText.formatAsMoney(threesomeCost, "span")+")", "You don't have "+threesomeCost+" flames, so you can't afford to have sex with both Loppy and Bunny at the same time.", null);
					
				} else {
					return new ResponseSex("Threesome ("+UtilText.formatAsMoney(threesomeCost, "span")+")",
							"Pay "+threesomeCost+" flames to have sex with both Loppy and Bunny at the same time.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Loppy.class), Main.game.getNpc(Bunny.class)),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_LOPPY_THREESOME,
							UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_THREESOME")
							+(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bunnyIntroduced)
									?UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_THREESOME_BUNNY_INTRODUCED")
									:UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_THREESOME_BUNNY_NOT_INTRODUCED"))) {
						@Override
						public void effects() {
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.bunnyIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
							Main.game.getNpc(Bunny.class).setLocation(WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_BEDROOM_LOPPY, false);
							Main.game.getPlayer().incrementMoney(-threesomeCost);
						}
					};
				}
				
			} else if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.loppyIntroduced) && index == 4) {
				return new Response("Decline", "You're not really interested in paying for sex with Loppy right now...", ANGELS_KISS_BEDROOM_LOPPY){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.loppyIntroduced, true);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_DECLINE"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_LOPPY = new DialogueNode("Loppy's Bedroom", "Disentangle yourself from Loppy's clutches.", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Main.game.getNpc(Loppy.class)) == 0) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_AFTER_SEX_NO_ORGASM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_AFTER_SEX");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	
	public static final DialogueNode AFTER_SEX_LOPPY_THREESOME = new DialogueNode("Loppy's Bedroom", "Disentangle yourself from Loppy's clutches.", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "BEDROOM_LOPPY_AFTER_THREESOME");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE = new DialogueNode("Angel's Office", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelsOfficeIntroduced);
		}
		
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelsOfficeIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE");
			} else {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
					return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_REPEAT");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_REPEAT_WITH_LICENSE");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.angelsOfficeIntroduced)) {
				if (index == 1) {
					return new Response("Continue", "Now that Angel's offered you her deal, you could ask some other questions...", ANGELS_KISS_OFFICE_CONTINUE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.angelsOfficeIntroduced, true);
							Main.game.updateResponses();
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Prostitution", "Ask Angel about the laws and regulations regarding prostitution in Dominion.", ANGELS_KISS_OFFICE_PROSTITUTION);
					
				} else if (index == 2) {
					return new Response("Background", "Ask Angel about her background, and how she ended up owning Angel's Kiss.", ANGELS_KISS_OFFICE_BACKGROUND);
					
				} else if (index == 3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
					if(Main.game.getPlayer().getMoney()<5000) {
						return new Response("License ("+UtilText.formatAsMoney(5000, "span")+")", "You don't have enough money to purchase a prostitution license!", null);
					} else {
						return new Response("License ("+UtilText.formatAsMoney(5000, "span")+")", "Agree to Angel's deal and purchase a prostitution license.", ANGELS_KISS_OFFICE_LICENSE_PURCHASE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.prostitutionLicenseObtained, true);
								Main.game.getPlayer().incrementMoney(-5000);
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE_CONTINUE = new DialogueNode("Angel's Office", ".", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_CONTINUE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ANGELS_KISS_OFFICE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE_PROSTITUTION = new DialogueNode("Angel's Office", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_PROSTITUTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Prostitution", "You are already talking to Angel about prostitution.", null);
				
			} else {
				return ANGELS_KISS_OFFICE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE_BACKGROUND = new DialogueNode("Angel's Office", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_BACKGROUND");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2) {
				return new Response("Background", "You are already talking to Angel about her background.", null);
				
			} else {
				return ANGELS_KISS_OFFICE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ANGELS_KISS_OFFICE_LICENSE_PURCHASE = new DialogueNode("Angel's Office", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/redLightDistrict/angelsKiss", "OFFICE_LICENSE_PURCHASE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ANGELS_KISS_OFFICE.getResponse(responseTab, index);
		}
	};
	
}
