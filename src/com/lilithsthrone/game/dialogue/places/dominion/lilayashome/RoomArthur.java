package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class RoomArthur {

	public static final DialogueNode ROOM_ARTHUR_INSTALLATION = new DialogueNode("Arthur's Room", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_INSTALLATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Find Lyssieth", "If you ever want to find out what's going on, it looks like you'll have to agree to help.", ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH = new DialogueNode("Arthur's Room", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_INSTALLATION_AGREE_TO_CONVINCE_LYSSIETH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Allow Arthur to get on with his experiments.", ROOM_ARTHUR);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR = new DialogueNode("Arthur's Room", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if(index == 1) {
				return new Response("Lyssieth", "Ask Arthur about Lilaya's mother, Lyssieth.", ROOM_ARTHUR_LYSSIETH);
				
			} else if(index == 2) {
				return new Response("Lilaya", "Ask Arthur about his past relationship with Lilaya.", ROOM_ARTHUR_LILAYA);
				
			} else if(index == 3) {
				if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HYPNO_WATCH)) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_HYPNO_WATCH)) {
						return new Response("Experiments", "Ask Arthur about the sort of experiments he's currently running.", ROOM_ARTHUR_HYPNO_WATCH_START) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_HYPNO_WATCH));
							}
						};
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH) == Quest.SIDE_HYPNO_WATCH_VICKY) {
						if(!Main.game.getPlayer().hasItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE))) {
							return new Response("Deliver package", "You need to fetch the package from Arcane Arts first!", null);
							
						} else {
							return new Response("Deliver package", "Hand over the package that you fetched from Arcane Arts.", ROOM_ARTHUR_HYPNO_WATCH_DELIVERY) {
								@Override
								public void effects() {
									Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT));
									Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
								}
							};
						}
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_LYSSIETH = new DialogueNode("Arthur's Room", ".", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_LYSSIETH");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 1) {
					return new Response("Lyssieth", "You're already asking Arthur about Lyssieth.", null);
					
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_LILAYA = new DialogueNode("Arthur's Room", ".", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_LILAYA");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index == 2) {
					return new Response("Lilaya", "You're already asking Arthur about Lilaya.", null);
				}
			}
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_START = new DialogueNode("Arthur's Room", ".", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_START");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ROOM_ARTHUR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM_ARTHUR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_DELIVERY = new DialogueNode("Arthur's Room", ".", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_DELIVERY");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Agree", "You trust Lilaya enough to agree with her request.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF);
				
			} else if(index == 2) {
				return new Response("Refuse", "There's no way that you're going to agree to be Lilaya's and Arthur's test subject! Perhaps Rose could be volunteered in your place...", ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF = new DialogueNode("Arthur's Room", ".", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wake up", "You suddenly snap out of your trance.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false, true));

						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_UTIL_COMPLETE));
						
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP = new DialogueNode("Arthur's Room", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_SELF_WAKE_UP");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Let Arthur continue with his other experiments.", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED = new DialogueNode("Arthur's Room", ".", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wake Rose", "Help Lilaya to wake Rose up.", ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ORIENTATION_HYPNO_WATCH), false, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HYPNO_WATCH, Quest.SIDE_UTIL_COMPLETE));
						
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE = new DialogueNode("Arthur's Room", ".", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/arthursRoom", "ROOM_ARTHUR_HYPNO_WATCH_OFFER_REFUSED_WAKE_ROSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Let Arthur continue with his other experiments.", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
}
