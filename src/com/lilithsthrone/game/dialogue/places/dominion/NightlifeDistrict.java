package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public class NightlifeDistrict {

	private static boolean isClubOpen() {
		return !(Main.game.getMinutesPassed() % (24 * 60) >= (60 * 5) && Main.game.getMinutesPassed() % (24 * 60) < (60 * 19));
	}

	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Nightlife", "Nightlife", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			if(!isClubOpen()) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_DAY_STORM");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_DAY");
				}
				
			} else {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_NIGHT_STORM");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "OUTSIDE_NIGHT");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
//				if(!isClubOpen()) {
					return new Response("Watering Hole", "The nightclub, 'The Watering Hole', is currently closed. (Will be added for the next release!)" /*A sign by the entrance informs you that it's open from 19:00-05:00 every night.*/, null);
//				} else {
//					return new Response("Watering Hole", "The nightclub, 'The Watering Hole', is currently open. There's not much of a queue to get in, so you could enter if you wanted to.", WATERING_HOLE_ENTRANCE) {
//						@Override
//						public void effects() {
//							Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_ENTRANCE);
//						}
//					};
//				}
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_ENTRANCE = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.julesIntroduced)) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_REPEAT");
				}
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_PASSED");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseEffectsOnly("Exit", "Leave 'The Watering Hole' and head back out into the district of Dominion known as 'nightlife'.") {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
					}
				};
				
			} else if(index==2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
				return new Response("Wait", "Wait patiently in the queue to get in to the club.", WATERING_HOLE_ENTRANCE_WAITING) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
					}
				};
				
			} else if(index==3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {

				return new ResponseSex("Suck cock", "Suck Jules's cock in front of everyone in order to skip to the front of the queue.",
						true, false,
						new SMJulesCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getJules(), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL))),
						AFTER_JULES_BLOWJOB,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_START_BLOWJOB")) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
						
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_ENTRANCE_WAITING = new DialogueNodeOld("The Watering Hole", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_WAITING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_JULES_BLOWJOB = new DialogueNodeOld("Finished", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AFTER_JULES_BLOWJOB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_ENTRANCE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_MAIN = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;//TODO approach girl/boy
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_SEATING = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;//TODO if have partner(s), sit and chat
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_VIP = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_BAR = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				if(index==1) {
					return new Response("Barmaid", "Get the lioness barmaid's attention.", WATERING_HOLE_BAR_KALAHARI_INTRO) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariIntroduced, true);
						}
					};
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Kalahari", "Get the lioness barmaid's attention.", WATERING_HOLE_BAR_KALAHARI);
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI_INTRO = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_INTRO");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO buy drinks
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_DANCE_FLOOR = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null; //TODO dance
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_TOILETS = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null; //TODO use toilets, gloryhole
		}
	};
}
