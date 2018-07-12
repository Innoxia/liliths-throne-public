package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionClubNPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.SMKrugerChair;
import com.lilithsthrone.game.sex.managers.dominion.SMStallSex;
import com.lilithsthrone.game.sex.managers.universal.SMChair;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
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

	private static boolean isClubOpen(int minutesPassedForNextScene) {
		return !((Main.game.getMinutesPassed()+minutesPassedForNextScene) % (24 * 60) >= (60 * 5) && (Main.game.getMinutesPassed()+minutesPassedForNextScene) % (24 * 60) < (60 * 19));
	}
	
	public static List<GameCharacter> getClubbersPresent() {
		List<GameCharacter> clubbers = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		clubbers.removeIf((npc) -> !(npc instanceof DominionClubNPC));
		return clubbers;
	}
	
	public static List<GameCharacter> getSavedClubbers() {
		List<GameCharacter> clubbers = new ArrayList<>(Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL));
		clubbers.removeIf((npc) -> !(npc instanceof DominionClubNPC));
		return clubbers;
	}
	
	private static boolean hasPartner() {
		return !getClubbersPresent().isEmpty();
	}
	
	public static GameCharacter getPartner() {
		return getClubbersPresent().get(0);
	}
	
	public static void saveClubbers() {
		for(GameCharacter clubber : getClubbersPresent()) {
			clubber.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		}
	}
	
	public static void removeClubbers() {
		for(GameCharacter clubber : getClubbersPresent()) {
			Main.game.banishNPC((NPC) clubber);
		}
	}
	
	private static String getClubberStatus(int minutesPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		if(hasPartner()) {
			GameCharacter clubber = getClubbersPresent().get(0);
			
			AffectionLevel al = clubber.getAffectionLevel(Main.game.getPlayer());
			sb.append("<p style='text-align:center;'><i>");
			switch(al) {
				case NEGATIVE_FIVE_LOATHE:
				case NEGATIVE_FOUR_HATE:
				case NEGATIVE_THREE_STRONG_DISLIKE:
				case NEGATIVE_TWO_DISLIKE:
					sb.append("[npc.Name] looks <i style='color:"+al.getColour().toWebHexString()+";'>angry and frustrated</i>, and will leave you if you continue to treat [npc.herHim] poorly.");
					break;
				case NEGATIVE_ONE_ANNOYED:
					sb.append("[npc.Name] looks <i style='color:"+al.getColour().toWebHexString()+";'>mildly annoyed</i>, and is starting to have a bad time.");
					break;
				case ZERO_NEUTRAL:
				case POSITIVE_ONE_FRIENDLY:
					sb.append("[npc.Name] looks <i style='color:"+al.getColour().toWebHexString()+";'>entertained</i>, and has no reason to leave you.");
					break;
				case POSITIVE_TWO_LIKE:
				case POSITIVE_THREE_CARING:
					sb.append("[npc.Name] looks like [npc.sheIs] <i style='color:"+al.getColour().toWebHexString()+";'>having a great time</i>.");
					break;
				case POSITIVE_FOUR_LOVE:
				case POSITIVE_FIVE_WORSHIP:
					sb.append("[npc.NameIsFull] <i style='color:"+al.getColour().toWebHexString()+";'>desperate to have sex with you</i>.");
					break;
			}
			
			if(clubber.getAlcoholLevelValue()>0) {
				sb.append("</br>");
					switch(clubber.getAlcoholLevel()) {
						case ZERO_SOBER:
							break;
						case ONE_TIPSY:
							sb.append("[npc.Name] is currently <i style='color:"+Colour.ALCOHOL.toWebHexString()+";'>tipsy</i>.");
							break;
						case TWO_MERRY:
							sb.append("[npc.Name] is currently <i style='color:"+Colour.ALCOHOL.toWebHexString()+";'>merry</i>.");
							break;
						case THREE_DRUNK:
							sb.append("[npc.Name] is currently <i style='color:"+Colour.ALCOHOL.toWebHexString()+";'>drunk</i>.");
							break;
						case FOUR_HAMMERED:
							sb.append("[npc.Name] is currently <i style='color:"+Colour.ALCOHOL.toWebHexString()+";'>hammered</i>. Giving [npc.herHim] any more alcohol would be a bad idea.");
							break;
						case FIVE_WASTED:
							sb.append("[npc.Name] is currently <i style='color:"+Colour.ALCOHOL.toWebHexString()+";'>wasted</i>. [npc.She] looks like [npc.sheIs] going to pass out.");
							break;
					}
			}

			sb.append("</br>");
			if(likesSex(clubber)) {
				sb.append("You can tell that [npc.she] wants to have sex with you...");
				
			} else if(likesGroping(clubber)) {
				sb.append("You can tell that [npc.she] wants some physical contact...");
				
			} else if(likesKiss(clubber)) {
				sb.append("You can tell that [npc.she] wouldn't mind a kiss...");
				
			} else {
				sb.append("It would be best to talk to [npc.herHim] a little before making a move...");
			}
			
			sb.append("</i></p>");
			
		}
		
		if(isEndConditionMet(minutesPassedForNextScene)) {
			sb.append(getEndingStatus(minutesPassedForNextScene));
		}
		
		return UtilText.parse(getClubbersPresent(), sb.toString());
	}
	
	private static int getKalahariBreakTimeLeft() {
		return Math.max(0, (int) (35 - (Main.game.getMinutesPassed() - Main.game.getDialogueFlags().kalahariBreakStartTime)));
	}

	private static boolean isPartnerLeaving() {
		return hasPartner() && getPartner().getAffection(Main.game.getPlayer())<AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue();
	}
	
	private static boolean isPartnerPassingOut() {
		return hasPartner() && getPartner().getAlcoholLevelValue()>=AlcoholLevel.FIVE_WASTED.getMaximumValue()-0.05f;
	}
	
	private static String getEndingStatus(int minutesPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
		if(isPartnerLeaving()) {
			sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible([npc.Name] leaves you!)]</br>"
					+ "[npc.Name] is fed up, and, with a dismissive wave of [npc.her] [npc.hand], [npc.she] turns around and leaves you!"));
		
		} else if(isPartnerPassingOut()) {
			sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible([npc.Name] collapses!)]</br>"
					+ "Having had far too much to drink, [npc.name] slumps down to the ground!"));
			
		} else if(!isClubOpen(minutesPassedForNextScene)) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.WATERING_HOLE_VIP_AREA) {
				sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible(Closing time)]</br>"
						+ "All of the lights in the club suddenly flash off and on a few times, and as the background music stops playing, Kruger growls,"
							+ " [kruger.speech(Looks like it's time to close for the night. Go on, [pc.name]. We can do this another time.)]"));
				
			} else {
				sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible(Closing time)]</br>"
						+ "All of the lights in the club suddenly flash off and on a few times, and as the background music stops playing, you hear Jules shout out, [jules.speech(It's closing time! Everyone out!)]"));
			}
		}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static boolean isEndConditionMet(int minutesPassedForNextScene) {
		return isPartnerLeaving() || isPartnerPassingOut() || (!isClubOpen(minutesPassedForNextScene) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules));
	}
	
	private static Response getEndResponse(int index, int minutesPassedForNextScene) {
		if(isPartnerLeaving()) {
			if(index==1) {
				return new Response("Continue", UtilText.parse(getClubbersPresent(), "Perhaps you should have treated [npc.name] a little better..."), Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_LEAVES", getClubbersPresent()));
						removeClubbers();
					}
				};
			}
			
		} else if(isPartnerPassingOut()) {
			if(index==1) {
				return new Response("Continue", UtilText.parse(getClubbersPresent(), "[npc.Name] collapses!"), Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_TOO_WASTED", getClubbersPresent()));
						saveClubbers();
					}
				};
			}
			
		} else if(!isClubOpen(minutesPassedForNextScene)) {
			if(hasPartner()) {
				if(index==1) {
					return new ResponseEffectsOnly("Say goodbye",
							UtilText.parse(getClubbersPresent(), "It's closing time, so you need to leave the club now. Say goodbye to [npc.name] before heading back out into the district of Dominion known as 'Nightlife'."
									+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]")) {
						@Override
						public void effects() {
							saveClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
						}
					};
					
				} else if(index==2) {
					if(likesSex(getPartner())) {
						return new Response("Invite home",
								UtilText.parse(getClubbersPresent(), "It's closing time, so you need to leave the club now. Ask [npc.name] to come back to your place."),
								RoomPlayer.AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME) {
							@Override
							public void effects() {
								for(GameCharacter clubber : getClubbersPresent()) {
									clubber.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								}
								
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
							}
						};
					} else {
						return new Response("Invite home", UtilText.parse(getClubbersPresent(),
								"It's closing time, so you need to leave the club now. [npc.Name] is showing no interest in wanting to go back to your place..."), null);
					}
					
				} else if(index==3) {
					return new ResponseEffectsOnly("Lose company",
							UtilText.parse(getClubbersPresent(), "It's closing time, so you need to leave the club now. Tell [npc.name] you've got to go before heading back out into the district of Dominion known as 'Nightlife'."
									+ "</br>[style.italicsBad(Removes this character from the game.)]")) {
						@Override
						public void effects() {
							removeClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
						}
					};
					
				}
				
			} else {
				if(index==1) {
					return new ResponseEffectsOnly("Leave", "It's closing time, so you need to leave the club now.") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
						}
					};
				}
			}
			return null;
			
		}
		return null;
	}
	
	private static String getKalahariStatus(boolean withBreakTime, int minutesPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'><i>");
		sb.append("Kalahari has "+(getKalahariBreakTimeLeft()-5)+" minutes of her break left.");
		sb.append("</i></p>");
		
		if(isEndConditionMet(minutesPassedForNextScene)) {
			sb.append(getEndingStatus(minutesPassedForNextScene));
		}
		
		return sb.toString();
	}
	
	private static boolean likesKiss(GameCharacter clubber) {
		return clubber.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue()
				|| clubber.getAlcoholLevelValue()>0;
	}
	
	private static boolean likesGroping(GameCharacter clubber) {
		return clubber.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue()
				|| clubber.getAlcoholLevelValue()>AlcoholLevel.TWO_MERRY.getMinimumValue();
	}
	
	private static boolean likesSex(GameCharacter clubber) {
		return clubber.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_THREE_CARING.getMedianValue()
				|| clubber.getAlcoholLevelValue()>AlcoholLevel.THREE_DRUNK.getMinimumValue();
	}
	
	private static final float KALAHARI_SELL_MODIFIER = 1.2f;

	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Nightlife", "Nightlife", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public String getContent() {
			if(!isClubOpen(0)) {
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
				if(!isClubOpen(0)) {
					return new Response("Watering Hole", "The nightclub, 'The Watering Hole', is currently closed. A sign by the entrance informs you that it's open from 19:00-05:00 every night.", null);
				} else {
					return new Response("Watering Hole", "The nightclub, 'The Watering Hole', is currently open. You could enter if you wanted to.", WATERING_HOLE_ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_ENTRANCE);
						}
					};
				}
				
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
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules) || isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.julesIntroduced)) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_REPEAT", getClubbersPresent());
				}
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_PASSED")
						+ getClubberStatus(this.getMinutesPassed());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!hasPartner()) {
				if(index==1) {
					return new ResponseEffectsOnly("Exit",
							"Leave 'The Watering Hole' and head back out into the district of Dominion known as 'Nightlife'.") {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
						}
					};
					
				} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
					if(index==2) {
						return new Response("Wait", "Wait patiently in the queue to get in to the club.", WATERING_HOLE_ENTRANCE_WAITING) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
							}
						};
						
					} else if(index==3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("Suck cock", "You can't gain access to your mouth, so you can't suck Jules's cock!", null);
						}
						return new ResponseSex("Suck cock", "Suck Jules's cock in front of everyone in order to skip to the front of the queue.",
								true, false,
								new SMJulesCockSucking(
										Util.newHashMapOfValues(new Value<>(Main.game.getJules(), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL))),
								AFTER_JULES_BLOWJOB,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_START_BLOWJOB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suckedJulesCock, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								
							}
						};
						
					}
					return null;
				}
				
			} else {
				if(index==1) {
					return new ResponseEffectsOnly("Say goodbye",
							UtilText.parse(getClubbersPresent(), "Say goodbye to [npc.name] before heading back out into the district of Dominion known as 'Nightlife'."
									+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]")) {
						@Override
						public void effects() {
							saveClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
						}
					};
					
				} else if(index==2) {
					if(likesSex(getPartner())) {
						return new Response("Invite home", UtilText.parse(getClubbersPresent(), "Take [npc.name] back to your room."), RoomPlayer.AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME) {
							@Override
							public void effects() {
								for(GameCharacter clubber : getClubbersPresent()) {
									clubber.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								}
								
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
							}
						};
					} else {
						return new Response("Invite home", UtilText.parse(getClubbersPresent(), "[npc.Name] is showing no interest in wanting to go back to your place. You should interact with [npc.herHim] a little more first..."), null);
					}
					
				} else if(index==3) {
					return new ResponseEffectsOnly("Lose company",
							UtilText.parse(getClubbersPresent(), "Tell [npc.name] you've got to go before heading back out into the district of Dominion known as 'Nightlife'."
									+ "</br>[style.italicsBad(Removes this character from the game.)]")) {
						@Override
						public void effects() {
							removeClubbers();
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
						}
					};
					
				} 
				return null;
				
			}
			
			return null;
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
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN", getClubbersPresent())
					+getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("Talk", UtilText.parse(getClubbersPresent(), "Talk to [npc.name] and get to know [npc.herHim] a little better."), WATERING_HOLE_MAIN_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_TALK.getMinutesPassed()));
						}
					};
					
				} else if(index==2) {
					return new Response("Flirt", UtilText.parse(getClubbersPresent(), "Compliment [npc.namePos] appearance and start flirting with [npc.herHim]."), WATERING_HOLE_MAIN_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_FLIRT.getMinutesPassed()));
						}
					};
					
				} else if(index==3) {
					return new Response("Kiss",
							UtilText.parse(getClubbersPresent(), "Step forwards and kiss [npc.name]."+(likesKiss(getPartner())?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_MAIN_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner())) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_KISS.getMinutesPassed()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_KISS.getMinutesPassed()));
								
							}
						}
					};
					
				} else if(index==4) {
					return new Response("Feel up",
							UtilText.parse(getClubbersPresent(), "Grind up against [npc.name] and start groping [npc.herHim]."+(likesGroping(getPartner())?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_MAIN_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner())) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 20));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_GROPE.getMinutesPassed()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_GROPE.getMinutesPassed()));
							}
						}
					};
					
				} if(index==9) {
					return new Response("Say goodbye",
							UtilText.parse(getClubbersPresent(), "Tell [npc.name] that you've got to head off for a little while, but that you hope to see [npc.herHim] again."
									+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
							WATERING_HOLE_MAIN_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("Lose company",
							UtilText.parse(getClubbersPresent(), "Make up an excuse to get rid of [npc.name].</br>[style.italicsBad(Removes this character from the game.)]"),
							WATERING_HOLE_MAIN_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Search (dom)",
							"Search for someone to approach and start talking to. This will put you in the dominant role, where you'll be the one leading your partner around the club.",
							WATERING_HOLE_SEARCH_GENDER);
					
				} else if(index==2) {
					if(clubberGender==null || clubberSubspecies==null) {
						return new Response("Repeat search", "You need to have already searched the club!", null);
					} else {
						return new Response("Repeat search",
								"Repeat your last search for someone to approach and start talking to. ("+Util.capitaliseSentence(clubberGender.getName())+" "+clubberSubspecies.getName()+")",
								WATERING_HOLE_SEARCH_GENERATE) {
							@Override
							public void effects() {
								spawnClubbers();
							}
						};
					}
					
				} else if(index==3) {
					if(getSavedClubbers().isEmpty()) {
						return new Response("Contacts (dom)", "You haven't met anybody who you said you'd meet in the club again...", null);
						
					} else {
						return new Response("Contacts (dom)",
								"Search for someone who you've already met in the club before. This will put you in the dominant role, where you'll be the one leading your partner around the club.",
								WATERING_HOLE_CONTACTS);
					}
				}
				//TODO Add NPC as dom variation
				// Partner leads you to bar/dance/seating/toilets
				// Partner buys you drink
//				else if(index==3) {
//					return new Response("Loiter",
//							"Loiter in the area and wait for someone to come up and start talking to you.", WATERING_HOLE_LOITER_GENERATE) {
//						@Override
//						public void effects() {
//							spawnClubbers();
//						}
//					};
//					
//				}
				else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_MAIN_TALK = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_MAIN_FLIRT = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_MAIN_KISS = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_MAIN_GROPE = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_MAIN_LOSE_COMPANY = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEARCH_GENDER = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENDER");
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "[style.colourFeminine(Feminine)]";
				
			} else if(index==1) {
				return "[style.colourMasculine(Masculine)]";
				
			} else if(index==2) {
				return "[style.colourAndrogynous(Androgynous)]";
				
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Decide against looking for someone to approach.", Main.game.getDefaultDialogueNoEncounter());
			}
			int count = 1;
			for(Gender gender : Gender.values()) {
				if((responseTab==0 && gender.getType()==PronounType.FEMININE)
						|| (responseTab==1 && gender.getType()==PronounType.MASCULINE)
						|| (responseTab==2 && gender.getType()==PronounType.NEUTRAL)) {
					if(count==index) {
						return new Response(Util.capitaliseSentence(gender.getName()),
								"Look for "+UtilText.generateSingularDeterminer(gender.getName())+" "+gender.getName()+" in amongst the crowds of revellers."
									+ " ("+(gender.getGenderName().isHasBreasts()?"[style.colourGood(Breasts)]":"[style.colourBad(Breasts)]")+", "
										+(gender.getGenderName().isHasPenis()?"[style.colourGood(Penis)]":"[style.colourBad(Penis)]")+", "
										+(gender.getGenderName().isHasVagina()?"[style.colourGood(Vagina)]":"[style.colourBad(Vagina)]")+")",
								WATERING_HOLE_SEARCH_RACE) {
							@Override
							public void effects() {
								clubberGender = gender;
							}
						};
					}
					count++;
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEARCH_RACE = new DialogueNodeOld("The Watering Hole", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_RACE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Decide to look for a different gender.", WATERING_HOLE_SEARCH_GENDER);
			}
			int count = 1;
			for(Subspecies subspecies : Main.game.getPlayer().getLocationPlace().getPlaceType().getSpeciesPopulatingArea()) {
				if(count==index) {
					return new Response(Util.capitaliseSentence(subspecies.getName()),
							"Look for "+UtilText.generateSingularDeterminer(subspecies.getName())+" "+subspecies.getName()+" in amongst the crowds of revellers.",
							WATERING_HOLE_SEARCH_GENERATE) {
						@Override
						public void effects() {
							clubberSubspecies = subspecies;
							
							spawnClubbers();
						}
					};
				}
				count++;
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEARCH_GENERATE = new DialogueNodeOld("The Watering Hole", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			if(getClubbersPresent().size()==1) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE", getClubbersPresent())
						+getClubberStatus(this.getMinutesPassed());
				
			} else {
				if(Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE_TWO", getClubbersPresent());
					
				} else {
					getClubbersPresent().addAll(Main.game.getPlayer().getNonElementalCompanions());
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE_TWO_WITH_COMPANION", getClubbersPresent());
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogueNoEncounter().getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_CONTACTS = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_CONTACTS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Decide to stop searching for someone you've met before.", WATERING_HOLE_MAIN);
			}
			int count = 1;
			for(GameCharacter character : getSavedClubbers()) {
				if(count==index) {
					return new Response(character.getName(),
							UtilText.parse(character, "Look for [npc.name] in amongst the crowds of revellers. ([npc.She] is [npc.a_fullRace(true)].)"),
							WATERING_HOLE_FIND_CONTACT) {
						@Override
						public void effects() {
							character.setLocation(WorldType.NIGHTLIFE_CLUB, Main.game.getPlayer().getLocation(), false);
						}
					};
				}
				count++;
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_FIND_CONTACT = new DialogueNodeOld("The Watering Hole", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FIND_CONTACT", getClubbersPresent())
					+getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogueNoEncounter().getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_LOITER_GENERATE = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}

		@Override
		public String getContent() {
			List<GameCharacter> clubbers = getClubbersPresent();
			
			if(clubbers.size()==1) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE", clubbers);
				
			} else {
				if(Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE_TWO", clubbers);
					
				} else {
					clubbers.addAll(Main.game.getPlayer().getNonElementalCompanions());
					return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_LOITER_GENERATE_TWO_WITH_COMPANION", clubbers);
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogueNoEncounter().getResponse(responseTab, index);
		}
	};
	
	private static Gender clubberGender;
	private static Subspecies clubberSubspecies;
	
	private static void spawnClubbers() {
		NPC clubber = new DominionClubNPC(clubberGender, clubberSubspecies, false);
				
		if(Math.random()<0.4f) {
			clubber.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				clubber.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				clubber.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		
		try {
			Main.game.addNPC(clubber, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		if(Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
//			// spawn 1 or 2
//		} else {
//			// spawn 2
//			// spawn second one with companion's preferences
//		}
	}

	public static final DialogueNodeOld WATERING_HOLE_SEATING = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(hasPartner()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_WITH_PARTNER", getClubbersPresent())
						+ getClubberStatus(this.getMinutesPassed());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("Talk", UtilText.parse(getClubbersPresent(), "Talk to [npc.name] and get to know [npc.herHim] a little better."), WATERING_HOLE_SEATING_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_TALK.getMinutesPassed()));
						}
					};
					
				} else if(index==2) {
					return new Response("Flirt", UtilText.parse(getClubbersPresent(), "Compliment [npc.namePos] appearance and start flirting with [npc.herHim]."), WATERING_HOLE_SEATING_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FLIRT.getMinutesPassed()));
						}
					};
					
				} else if(index==3) {
					return new Response("Footsie",
							UtilText.parse(getClubbersPresent(), "Lightly push your [pc.foot] into [npc.namePos] groin."+(likesGroping(getPartner())?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_SEATING_FOOTSIE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner())) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FOOTSIE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FOOTSIE.getMinutesPassed()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FOOTSIE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FOOTSIE.getMinutesPassed()));
								
							}
						}
					};

				} else if(index==4) {
					if(likesSex(getPartner())) {
						return new ResponseSex("Sex (dom)", UtilText.parse(getClubbersPresent(), "Pull [npc.name] into your lap and start having dominant sex with [npc.herHim]."),
								true, true,
								new SMChair(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM)),
										Util.newHashMapOfValues(new Value<>(getPartner(), SexPositionSlot.CHAIR_TOP))),
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_DOM", getClubbersPresent()));
						
					} else {
						return new Response("Sex (dom)",
								UtilText.parse(getClubbersPresent(), "Pull [npc.name] into your lap and start having dominant sex with [npc.herHim].</br>[style.italicsBad([npc.She] might not react well to this!)]"),
								WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED.getMinutesPassed()));
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else if(index==5) {
					if(likesSex(getPartner())) {
						return new ResponseSex("Sex (sub)", UtilText.parse(getClubbersPresent(), "Slide into [npc.namePos] lap and start having submissive sex with [npc.herHim]."),
								true, true,
								new SMChair(
										Util.newHashMapOfValues(new Value<>(getPartner(), SexPositionSlot.CHAIR_BOTTOM)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP))),
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_SUB", getClubbersPresent()));
						
					} else {
						return new Response("Sex (sub)",
								UtilText.parse(getClubbersPresent(), "Pull [npc.name] into your lap and start having submissive sex with [npc.herHim].</br>[style.italicsBad([npc.She] might not react well to this!)]"),
								WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED.getMinutesPassed()));
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} if(index==9) {
					return new Response("Say goodbye",
							UtilText.parse(getClubbersPresent(), "Tell [npc.name] that you've got to head off for a little while, but that you hope to see [npc.herHim] again."
									+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
							WATERING_HOLE_SEATING_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("Lose company",
							UtilText.parse(getClubbersPresent(), "Make up an excuse to get rid of [npc.name].</br>[style.italicsBad(Removes this character from the game.)]"),
							WATERING_HOLE_SEATING_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				}
				return null;
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_TALK = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_FLIRT = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_FOOTSIE = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED", getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_AFTER_SEX = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(getPartner())>0) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX", getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_NO_ORGASM", getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("See again",
						"Tell [npc.name] that you hope to see [npc.herHim] again.</br>"
								+ "[style.italicsGood(Saves this character, who can then be encountered in the club again.)]",
						WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Hope not (gentle)",
						"Make a non-committal response, secretly hoping that you won't see [npc.name] again.</br>[style.italicsBad(Removes this character from the game.)]",
						WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Hope not (harsh)",
						"Crudely tell [npc.name] that you were only interested in fucking [npc.herHim].</br>[style.italicsBad(Removes this character from the game.)]",
						WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", getClubbersPresent()));
						removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN = new DialogueNodeOld("The Watering Hole", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN = new DialogueNodeOld("The Watering Hole", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_SEATING_LOSE_COMPANY = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_SEATING.getResponse(responseTab, index);
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_BAR = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced);
		}

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR", getClubbersPresent());
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_REPEAT", getClubbersPresent())
						+ getClubberStatus(this.getMinutesPassed());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				return getEndResponse(index, 0);
			}
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariIntroduced)) {
				if(index==1) {
					return new Response("Barmaid", "The lioness barmaid says hello.", WATERING_HOLE_BAR_KALAHARI_INTRO) {
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
					return new Response("Kalahari", "Kalahari steps forwards and says hello.", WATERING_HOLE_BAR_KALAHARI) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI", getClubbersPresent()));
							Main.game.getKalahari().setReactedToPregnancy(true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	private static String getDrinkEffects(AbstractItemType item) {
		StringBuilder sb = new StringBuilder();
		
		ItemEffect ie = item.getEffects().get(0);
		for(int i=0; i<ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size(); i++) {
			sb.append("</br>"+ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).get(i));
		}
		
		return sb.toString();
	}
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI_INTRO = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_INTRO", getClubbersPresent())
					+ getClubberStatus(this.getMinutesPassed());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(hasPartner()) {
				if(index==0) {
					return "Self";
					
				} else if(index==1) {
					return UtilText.parse(getClubbersPresent(), "[npc.Name]");
					
				}
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(!hasPartner() || responseTab==0) {
				if(index==1) {
					AbstractItemType drink = ItemType.INT_INGREDIENT_VANILLA_WATER;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Water ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a bottle of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response("Water ("+UtilText.formatAsMoney(price, "span")+")", "Ask Kalahari for a bottle of "+drink.getName(false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a bottle of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she sets the cold beverage down in front of you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you twist off the cap and bring the plastic bottle up to your [pc.lips]."
											+ " A faint, sweet smell informs you that this isn't any ordinary water, and as you tilt your head back and start drinking the cool liquid, the taste of vanilla overwhelms your senses."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==2) {
					AbstractItemType drink = ItemType.FIT_INGREDIENT_CANINE_CRUSH;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Beer ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a bottle of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response("Beer ("+UtilText.formatAsMoney(price, "span")+")", "Ask Kalahari for a bottle of "+drink.getName(false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a bottle of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she sets the cold beer down in front of you."
											+ " Grabbing a nearby bottle-opener, Kalahari pops the cap off, before pushing the bottle towards you over the bar top."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you grab the cold bottle and bring it up to your [pc.lips]."
											+ " As you start gulping it down, you find that it doesn't taste anything like any other beer you've ever had, and it reminds you more of a sugary energy drink rather than any alcoholic beverage."
											+ " As the last few drops slide down your throat, a strange, musky aftertaste lingers on your tongue."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==3) {
					AbstractItemType drink = ItemType.INT_INGREDIENT_FELINE_FANCY;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoney(price, "span")+")", "Ask Kalahari for a glass of "+drink.getName(false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the creamy alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you take hold of the glass and bring it up to your [pc.lips]."
											+ " The beverage gives off a rich, creamy smell, and as you greedily drink down the cool liquid,"
												+ " you're delighted to discover that it tastes every bit as good as its delicious aroma suggested it would."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==4) {
					AbstractItemType drink = ItemType.STR_INGREDIENT_WOLF_WHISKEY;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoney(price, "span")+")", "Ask Kalahari for a glass of "+drink.getName(false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the shelves behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the strong alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you take hold of the glass and bring it up to your [pc.lips]."
											+ " The whiskey gives off a thick, musky scent, and as you start downing the liquid, you discover that the taste is almost identical to its pungent aroma."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==5) {
					AbstractItemType drink = ItemType.STR_INGREDIENT_BLACK_RATS_RUM;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoney(price, "span")+")", "Ask Kalahari for a glass of "+drink.getName(false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the shelves behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the strong alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you take hold of the glass and bring it up to your [pc.lips],"
												+ " before tilting your head back and quickly gulping down the golden liquid."
											+ " As the last few drops slide down your throat, you notice a faint, musky dryness permeating through the alcoholic beverage, which lingers for some time as a slightly unpleasant aftertaste."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==6) {
					if(!hasPartner()) {
						return new Response("Talk", "Lean forwards and talk to Kalahari for a little while.", WATERING_HOLE_BAR_KALAHARI_TALK) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getKalahari().incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KALAHARI_TALK.getMinutesPassed()));
							}
						};
					} else {
						return new Response("Talk", UtilText.parse(getClubbersPresent(), "You can't talk to Kalahari while [npc.name] is with you!"), null);
					}
					
				} else if(index==7) {
					if(!hasPartner()) {
						return new Response("Flirt", "Start flirting with Kalahari.", WATERING_HOLE_BAR_KALAHARI_FLIRT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getKalahari().incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getKalahari().getPlayerKnowsAreas().add(CoverableArea.BREASTS);
								Main.game.getKalahari().getPlayerKnowsAreas().add(CoverableArea.NIPPLES);
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KALAHARI_FLIRT.getMinutesPassed()));
							}
						};
					} else {
						return new Response("Flirt", UtilText.parse(getClubbersPresent(), "You can't flirt with Kalahari while [npc.name] is with you!"), null);
					}
					
				} else if(index==8) {
					if(hasPartner()) {
						return new Response("Break", UtilText.parse(getClubbersPresent(), "You can't ask Kalahari to spend her break with you while [npc.name] is with you!"), null);
						
					} else if(Main.game.getMinutesPassed() - Main.game.getDialogueFlags().kalahariBreakStartTime < 60 * 12) {
							return new Response("Break", "Kalahari has already used up her break tonight!", null);
							
					} else if(!likesKiss(Main.game.getKalahari())) {
						return new Response("Break", "You don't know Kalahari well enough to ask her to spend her break with you. Try talking and flirting with her a little first...", null);
						
					} else {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
							return new Response("Break", "Ask Kalahari if she's got a break coming up, and if she'd like to spend it with you.", WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO) {
								@Override
								public void effects() {
									Main.game.getKalahari().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.krugerIntroduced, true);
									Main.game.getDialogueFlags().kalahariBreakStartTime = Main.game.getMinutesPassed();
									Main.game.getKruger().setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue()-7);
								}
							};
							
						} else {
							return new Response("Break", "Ask Kalahari if she's got a break coming up, and if she'd like to relax in the VIP area with you again.", WATERING_HOLE_BAR_KALAHARI_BREAK) {
								@Override
								public void effects() {
									Main.game.getKalahari().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getDialogueFlags().kalahariBreakStartTime = Main.game.getMinutesPassed();
								}
							};
						}
					}
					
				}
				
			} else {
				GameCharacter clubber = getClubbersPresent().get(0);
				
				if(index==1) {
					AbstractItemType drink = ItemType.INT_INGREDIENT_VANILLA_WATER;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Water ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a bottle of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response("Water ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a bottle of "+drink.getName(false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(Can I get a bottle of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she sets the cold beverage down in front of you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you pass the bottle to [npc.name]."
											+ " [pc.speech(You look like you could use some water. Here you go.)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Thanks, I was getting pretty thirsty, yeah,)] [npc.name] responds, before twisting off the bottle's cap and gulping down the water."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(clubber, clubber, 1)
										+ clubber.incrementAffection(Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price)));
							}
						};
					}
				
				} else if(index==2) {
					AbstractItemType drink = ItemType.FIT_INGREDIENT_CANINE_CRUSH;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Beer ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a bottle of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response("Beer ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a bottle of "+drink.getName(false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a bottle of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she sets the cold beer down in front of you."
											+ " Grabbing a nearby bottle-opener, Kalahari pops the cap off, before pushing the bottle towards you over the bar top."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you pass the bottle to [npc.name]."
											+ " [pc.speech(You look like you could use a drink. Here you go.)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Thanks! I was getting pretty thirsty, yeah,)] [npc.name] responds, before lifting the bottle up to [npc.her] [npc.lips] and gulping down the beer."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(clubber, clubber, 1)
										+ clubber.incrementAffection(Main.game.getPlayer(), 2)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==3) {
					AbstractItemType drink = ItemType.INT_INGREDIENT_FELINE_FANCY;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a glass of "+drink.getName(false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the creamy alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you pass the glass to [npc.name]."
											+ " [pc.speech(You look like you could use a drink. Here you go.)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Aww, thanks! I love Feline's Fancy!)] [npc.name] happily responds, before lifting the glass up to [npc.her] [npc.lips] and gulping down the creamy alcoholic drink."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(clubber, clubber, 1)
										+ clubber.incrementAffection(Main.game.getPlayer(), 3)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==4) {
					AbstractItemType drink = ItemType.STR_INGREDIENT_WOLF_WHISKEY;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a glass of "+drink.getName(false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the shelves behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the strong alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you pass the glass to [npc.name]."
											+ " [pc.speech(You look like you could use a drink. Here you go.)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Hey, thanks, [pc.name]! Wolf Whiskey's the best!)] [npc.name] happily responds, before lifting the glass up to [npc.her] [npc.lips] and gulping down the strong alcoholic drink."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(clubber, clubber, 1)
										+ clubber.incrementAffection(Main.game.getPlayer(), 4)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==5) {
					AbstractItemType drink = ItemType.STR_INGREDIENT_BLACK_RATS_RUM;
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a glass of "+drink.getName(false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false)+" from one of the shelves behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the strong alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you pass the glass to [npc.name]."
											+ " [pc.speech(You look like you could use a drink. Here you go.)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Thanks, [pc.name]! This rum's the best!)] [npc.name] happily responds, before lifting the glass up to [npc.her] [npc.lips] and gulping down the strong alcoholic drink."
										+ "</p>"
										+ drink.getEffects().get(0).applyEffect(clubber, clubber, 1)
										+ clubber.incrementAffection(Main.game.getPlayer(), 5)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==6) {
					return new Response("Talk", UtilText.parse(getClubbersPresent(), "Talk to [npc.name] in order to get to know [npc.herHim] better."), WATERING_HOLE_BAR_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_TALK.getMinutesPassed()));
						}
					};
					
				} else if(index==7) {
					return new Response("Flirt", UtilText.parse(getClubbersPresent(), "Start flirting with [npc.name]."), WATERING_HOLE_BAR_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_FLIRT.getMinutesPassed()));
						}
					};
					
				} else if(index==8) {
					return new Response("Kiss",
							UtilText.parse(getClubbersPresent(), "Step forwards and kiss [npc.name]."+(likesKiss(getPartner())?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_BAR_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner())) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KISS.getMinutesPassed()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KISS.getMinutesPassed()));
								
							}
						}
					};
					
				} else if(index==9) {
					return new Response("Feel up",
							UtilText.parse(getClubbersPresent(), "Press yourself against [npc.name] and start groping [npc.herHim]."+(likesGroping(getPartner())?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_BAR_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner())) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_GROPE.getMinutesPassed()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_GROPE.getMinutesPassed()));
								
							}
						}
					};
					
				} else if(index==11) {
					return new Response("Say goodbye",
							UtilText.parse(getClubbersPresent(), "Tell [npc.name] that you've got to head off for a little while, but that you hope to see [npc.herHim] again."
									+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
							WATERING_HOLE_BAR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==12) {
					return new Response("Lose company",
							UtilText.parse(getClubbersPresent(), "Make up an excuse to get rid of [npc.name].</br>[style.italicsBad(Removes this character from the game.)]"),
							WATERING_HOLE_BAR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
				}
			}
				
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_TALK = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_TALK", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TALK_CONTENT", getClubbersPresent());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_FLIRT = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_FLIRT", getClubbersPresent())
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FLIRT_CONTENT", getClubbersPresent());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KISS = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_GROPE = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_LOSE_COMPANY = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_DRINK = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getMinutesPassed());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI_TALK = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_TALK");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI_FLIRT = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_FLIRT")
					+ "<p>"
						+ Main.game.getKalahari().getBreastDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_FLIRT_END");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO")
					+ getKalahariStatus(true, this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(getKalahariBreakTimeLeft()<=0) {
				if(index==1) {
					return new Response("Break over", "Kalahari has run out of break time. Let her get back to work.", WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME) {
						@Override
						public void effects() {
							Main.game.getKalahari().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Talk", "Kalahari is only interested in having sex right now!", null);
						
					} else {
						return new Response("Talk", "Talk to Kalahari in order to get to know her better.", WATERING_HOLE_KALAHARI_BREAK_TALK) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getKalahari().incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_TALK.getMinutesPassed()));
							}
						};
					}
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Flirt", "Kalahari is only interested in having sex right now!", null);
						
					} else {
						return new Response("Flirt", "Start flirting with Kalahari.", WATERING_HOLE_KALAHARI_BREAK_FLIRT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getKalahari().incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_FLIRT.getMinutesPassed()));
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Kiss", "Kalahari is only interested in having sex right now!", null);
						
					} else if(!likesKiss(Main.game.getKalahari())) {
						return new Response("Kiss", "You can tell that Kalahari doesn't want a kiss at the moment. It would be best to get to know her a little better first.", null);
						
					} else {
						return new Response("Kiss", "Lean forwards and kiss Kalahari.", WATERING_HOLE_KALAHARI_BREAK_KISS) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getKalahari().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_KISS.getMinutesPassed()));
							}
						};
					}
					
				} else if(index==4) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Feel up", "Kalahari is only interested in having sex right now!", null);
						
					} else if(!likesGroping(Main.game.getKalahari())) {
						return new Response("Feel up", "You can tell that Kalahari would react badly to any dominant move to feel her up. It would be best to spend some time flirting with her first.", null);
						
					} else {
						return new Response("Feel up", "Press yourself against Kalahari and start feeling her up.", WATERING_HOLE_KALAHARI_BREAK_GROPE) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getKalahari().incrementAffection(Main.game.getPlayer(), 20));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_GROPE.getMinutesPassed()));
								Main.game.getKalahari().getPlayerKnowsAreas().add(CoverableArea.BREASTS);
								Main.game.getKalahari().getPlayerKnowsAreas().add(CoverableArea.NIPPLES);
								Main.game.getKalahari().getPlayerKnowsAreas().add(CoverableArea.VAGINA);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, true);
								Main.game.getKalahari().displaceClothingForAccess(CoverableArea.BREASTS);
								Main.game.getKalahari().displaceClothingForAccess(CoverableArea.VAGINA);
							}
						};
					}
					
				} else if(index==6) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Sex (dom)", "You need to get Kalahari fully into the mood before having sex with her.", null);
					} else {
						return new ResponseSex("Sex (dom)", "Pull Kalahari into your lap and start having dominant sex with her.",
								true, true,
								new SMChair(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM)),
										Util.newHashMapOfValues(new Value<>(Main.game.getKalahari(), SexPositionSlot.CHAIR_TOP))),
								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_DOM"));
					}
					
				} else if(index==7) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Sex (sub)", "You need to get Kalahari fully into the mood before having sex with her.", null);
					} else {
						return new ResponseSex("Sex (sub)", "Slide into Kalahari's lap and start having submissive sex with her.",
								true, true,
								new SMChair(
										Util.newHashMapOfValues(new Value<>(Main.game.getKalahari(), SexPositionSlot.CHAIR_BOTTOM)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP))),
								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_SUB"));
					}
					
				}
				//TODO requires several improvements to sex AI and positioning first
//				else if(index==6) {
//					if(!Main.game.getPlayer().isFeminine()) {
//						return new Response("Kruger threesome", "Kruger is gynephilic, so wouldn't be interested in a threesome with you and Kalahari.", null);
//						
//					} else {
//						return new ResponseSex("Kruger threesome", "Have a threesome with Kruger as the dom, and you and Kalahari as the subs.",
//								true, true,
//								new SMKrugerThreesome(
//										Util.newHashMapOfValues(new Value<>(Main.game.getKruger(), SexPositionSlot.DOGGY_BEHIND)),
//										Util.newHashMapOfValues(
//												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
//												new Value<>(Main.game.getKalahari(), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
//								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
//								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_SUB"));
//					}
//					
//				}
				else if(index==0) {
					return new Response("Finish", "Let Kalahari get back to work.", WATERING_HOLE_KALAHARI_BREAK_END) {
						@Override
						public void effects() {
							Main.game.getKalahari().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI_BREAK = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK")
					+ getKalahariStatus(true, this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	

	public static final DialogueNodeOld WATERING_HOLE_KALAHARI_BREAK_TALK = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_KALAHARI_BREAK_FLIRT = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_FLIRT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_KALAHARI_BREAK_KISS = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_KISS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_KALAHARI_BREAK_GROPE = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE")
					+ "<p>"
						+ Main.game.getKalahari().getBreastDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE_AFTER_BREAST_REVEAL")
						+ Main.game.getKalahari().getVaginaDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE_AFTER_PUSSY_REVEAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Main.game.getKalahari())>0) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_KALAHARI_BREAK_END = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNodeOld WATERING_HOLE_VIP = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP", getClubbersPresent())
						+ getClubberStatus(this.getMinutesPassed());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_BLOCKED", getClubbersPresent())
						+ getClubberStatus(this.getMinutesPassed());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
				if(index==1) {
					if(hasPartner()) {
						return new Response("Kruger", UtilText.parse(getClubbersPresent(), "You can't talk to Kruger while [npc.name] is with you."), null);
						
					} else if(Main.game.getKruger().getLastTimeHadSex() >= Main.game.getMinutesPassed()-(60*12)) {
						return new Response("Kruger", "You've already had sex with Kruger tonight."
								+ " Although you wouldn't mind having some more fun with him, you can tell that his patience would soon wear thin if you keep going back to him."
								+ " You can always come back and ride his cock tomorrow night.", null);
						
					} else {
						return new Response("Kruger", "Walk up to Kruger and say hello.", WATERING_HOLE_VIP_KRUGER) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER"));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER.getMinutesPassed()));
								Main.game.getKruger().setReactedToPlayerPregnancy(true);
							}
						};
					}
				}
				return null;
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_VIP_KRUGER = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				return new Response("Talk", "Talk to Kruger for a little while.", WATERING_HOLE_VIP_KRUGER_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getKruger().incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_TALK.getMinutesPassed()));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isFeminine()) {
					return new Response("Flirt", "Flirt with Kruger.", WATERING_HOLE_VIP_KRUGER_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getKruger().incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_FLIRT.getMinutesPassed()));
						}
					};
				} else {
					return new Response("Flirt", "Kruger is gynephilic, so isn't interested in doing anything sexual with you.", null);
				}
				
			} else if(index==3) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Kissed", "Kruger is gynephilic, so isn't interested in doing anything sexual with you.", null);
					
				} else if(!likesKiss(Main.game.getKruger())) {
					return new Response("Kissed", "Kruger doesn't seem to be interested in kissing you.", null);
					
				} else {
					return new Response("Kissed", "You can tell that Kruger wants to kiss you. Lean forwards and let him.", WATERING_HOLE_VIP_KRUGER_KISSED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getKruger().incrementAffection(Main.game.getPlayer(), 15));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_KISSED.getMinutesPassed()));
						}
					};
				}
				
			} else if(index==4) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Felt up", "Kruger is gynephilic, so isn't interested in doing anything sexual with you.", null);
					
				} else if(!likesGroping(Main.game.getKruger())) {
					return new Response("Felt up", "Kruger doesn't seem to be interested in feeling you up at the moment.", null);
					
				} else {
					return new Response("Felt up", "You can tell that Kruger wants to have some physical contact with you. Shuffle up beside him and let him feel you up.", WATERING_HOLE_VIP_KRUGER_FELT_UP) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getKruger().incrementAffection(Main.game.getPlayer(), 20));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_FELT_UP.getMinutesPassed()));
						}
					};
					
				}
				
			} else if(index==5) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Sex (sub)", "Kruger is gynephilic, so isn't interested in doing anything sexual with you.", null);
					
				} else if(!likesSex(Main.game.getKruger())) {
					return new Response("Sex (sub)", "Kruger doesn't seem to be interested in having sex with you at the moment.", null);
					
				} else {
					return new ResponseSex("Sex (sub)", "Slide into Kruger's lap and start having submissive sex with [npc.herHim].",
							true, true,
							new SMKrugerChair(
									Util.newHashMapOfValues(new Value<>(Main.game.getKruger(), SexPositionSlot.CHAIR_ORAL_SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_KNEELING))),
							WATERING_HOLE_VIP_KRUGER_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_SEX_AS_SUB"));
				}
				
			} else if(index==0) {
				return new Response("Leave", "Tell Kruger that you need to leave.", WATERING_HOLE_VIP_KRUGER_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_VIP_KRUGER_TALK = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_TALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_VIP_KRUGER_FLIRT = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_FLIRT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_VIP_KRUGER_KISSED = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_KISSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_VIP_KRUGER_FELT_UP = new DialogueNodeOld("The Watering Hole", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_FELT_UP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP_KRUGER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_VIP_KRUGER_AFTER_SEX = new DialogueNodeOld("The Watering Hole",
			"Kruger is finished with you, and, being exhausted from the sex, you readily allow him to push you off of him.",
			false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_VIP_KRUGER_LEAVE = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_LEAVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_DANCE_FLOOR = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR", getClubbersPresent())
					+ getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("Dance", UtilText.parse(getClubbersPresent(), "Dance with [npc.namePos] for a little while."), WATERING_HOLE_DANCE_FLOOR_DANCE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_DANCE_FLOOR_DANCE.getMinutesPassed()));
						}
					};
					
				} else if(index==2) {
					return new Response("Kiss",
							UtilText.parse(getClubbersPresent(), "Step forwards and kiss [npc.name]."+(likesKiss(getPartner())?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_DANCE_FLOOR_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner())) {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								
							}
						}
					};
					
				} else if(index==3) {
					return new Response("Feel up",
							UtilText.parse(getClubbersPresent(), "Grind up against [npc.name] and start groping [npc.herHim]."+(likesGroping(getPartner())?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_DANCE_FLOOR_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner())) {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextStartStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								
							}
						}
					};
					
				} else if(index==9) {
					return new Response("Say goodbye",
							UtilText.parse(getClubbersPresent(), "Tell [npc.name] that you've got to head off for a little while, but that you hope to see [npc.herHim] again."
									+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
							WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_SAVE_CLUBBER", getClubbersPresent()));
							saveClubbers();
						}
					};
					
				} else if(index==10) {
					return new Response("Lose company",
							UtilText.parse(getClubbersPresent(), "Make up an excuse to get rid of [npc.name].</br>[style.italicsBad(Removes this character from the game.)]"),
							WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY", getClubbersPresent()));
							removeClubbers();
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Dance", "Dance for a little while.", WATERING_HOLE_DANCE_FLOOR_DANCE);
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_DANCE_FLOOR_DANCE = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(hasPartner()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_DANCE", getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR_DANCE_SOLO");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_DANCE_FLOOR_KISS = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_DANCE_FLOOR_GROPE = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY = new DialogueNodeOld("The Watering Hole", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNodeOld WATERING_HOLE_TOILETS = new DialogueNodeOld("Toilets", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS", getClubbersPresent())
					+ getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(hasPartner()) {
				if(index==1) {
					return new Response("Toilet", "Use the toilet.", WATERING_HOLE_TOILETS_USE);
					
				} else if(index==2) {
					if(likesSex(getPartner())) {
						return new ResponseSex("Stall sex", UtilText.parse(getClubbersPresent(), "Try and get [npc.name] to have sex in one of the toilet's stalls."),
								true, true,
								new SMStallSex(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getPartner(), SexPositionSlot.STANDING_SUBMISSIVE))),
								WATERING_HOLE_TOILETS_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_SEX", getClubbersPresent()));
						
					} else {
						return new Response("Stall sex",
								UtilText.parse(getClubbersPresent(), "Try and get [npc.name] to have sex in one of the toilet's stalls.</br>[style.italicsBad([npc.She] might not react well to this!)]"),
								WATERING_HOLE_TOILETS_SEX_REJECTED) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_TOILETS_SEX_REJECTED.getMinutesPassed()));
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Toilet", "Use the toilet.", WATERING_HOLE_TOILETS_USE);
					
				}
//				else if(index==2) {//TODO needs new sex manager and descriptions
//					return new Response("Glory hole (dom)", "A couple of the toilet's stalls have glory holes in them. Step up to one and have the person on the other side service you.", null);
//					
//				} else if(index==3) {
//					return new ResponseSex("Glory hole (sub)", "A couple of the toilet's stalls have glory holes in them. Kneel down and get ready to service whatever comes through the holes.",
//							true, false,
//							new SMGloryHole(
//									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.GLORY_HOLE_KNEELING)),
//									Util.newHashMapOfValues(
//											new Value<>(getGloryHoleDom(), SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_ONE),
//											new Value<>(getGloryHoleDom(), SexPositionSlot.GLORY_HOLE_RECEIVING_ORAL_TWO))),
//							WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
//							UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_DOM"));
//					
//				}
				else {
					return null;
				}
			}
		}
	};
	
//	private static GameCharacter getGloryHoleDom() {
//		NPC npc = new GenericSexualPartner(GenderPreference.getGenderFromUserPreferences(), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
//		if(Math.random()<0.4f) {
//			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
//		} else {
//			if(Main.game.getPlayer().isFeminine()) {
//				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
//			} else {
//				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
//			}
//		}
//		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
//		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
//		npc.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
//		if(npc.hasVagina()) {
//			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
//		}
//		if(npc.hasPenis()) {
//			npc.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
//		}
//		try {
//			Main.game.addNPC(npc, false);
//			Main.game.setActiveNPC(npc);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return npc;
//	}
	
	public static final DialogueNodeOld WATERING_HOLE_TOILETS_AFTER_SEX = new DialogueNodeOld("Toilets", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(getPartner())>0) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX", getClubbersPresent())
						+ getClubberStatus(this.getMinutesPassed());
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_NO_ORGASM", getClubbersPresent())
						+ getClubberStatus(this.getMinutesPassed());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("See again",
						"Tell [npc.name] that you hope to see [npc.herHim] again.</br>"
								+ "[style.italicsGood(Saves this character, who can then be encountered in the club again.)]",
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Hope not (gentle)",
						"Make a non-committal response, secretly hoping that you won't see [npc.name] again.</br>[style.italicsBad(Removes this character from the game.)]",
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Hope not (harsh)",
						"Crudely tell [npc.name] that you were only interested in fucking [npc.herHim].</br>[style.italicsBad(Removes this character from the game.)]",
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", getClubbersPresent()));
						removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN = new DialogueNodeOld("The Watering Hole", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_TOILETS_SEX_REJECTED = new DialogueNodeOld("Toilets", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 1;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_SEX_REJECTED", getClubbersPresent())
					+ getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld WATERING_HOLE_TOILETS_USE = new DialogueNodeOld("Toilets", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_USE", getClubbersPresent())
					+ getClubberStatus(this.getMinutesPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
}
