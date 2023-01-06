package com.lilithsthrone.game.dialogue.places.dominion.nightlife;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionClubNPC;
import com.lilithsthrone.game.character.npc.dominion.Jules;
import com.lilithsthrone.game.character.npc.dominion.Kalahari;
import com.lilithsthrone.game.character.npc.dominion.Kruger;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.SMJulesCockSucking;
import com.lilithsthrone.game.sex.managers.dominion.SMKrugerChair;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.managers.dominion.toiletStall.SMStallSex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.population.Population;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class NightlifeDistrict {
	
	private static boolean isSearchingForASub = true;
	private static Gender clubberGender;
	private static AbstractSubspecies clubberSubspecies;
	private static RaceStage clubberRaceStage;
	
	private static boolean isClubOpen(int minutesPassedForNextScene) {
		return !((Main.game.getMinutesPassed()+minutesPassedForNextScene) % (24 * 60) >= (60 * 5) && (Main.game.getMinutesPassed()+minutesPassedForNextScene) % (24 * 60) < (60 * 19));
	}
	
	public static boolean isSearchingForASub() {
		return isSearchingForASub;
	}

	public static List<GameCharacter> getClubbersPresent() {
		List<GameCharacter> clubbers = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		// Do not filter by checking for DominionClubNPC classes, as imported clubbers are not of this class
		clubbers.removeIf((npc) -> npc.isUnique() || npc.isSlave() || Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())); // So that when player takes clubber home, slaves/occupants in player's room are not added to this list
		return clubbers;
	}
	
	public static List<GameCharacter> getGloryHoleCharacters() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters;
	}

	
	public static List<GameCharacter> getSavedClubbers(boolean submissiveClubbers) {
		List<GameCharacter> clubbers = new ArrayList<>(Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL));
		
		clubbers.removeIf((npc) -> (submissiveClubbers
						?npc.hasPersonalityTrait(PersonalityTrait.CONFIDENT)
						:!npc.hasPersonalityTrait(PersonalityTrait.CONFIDENT)));
		
		return clubbers;
	}
	
	private static boolean hasPartner() {
		return !getClubbersPresent().isEmpty();
	}
	
	public static GameCharacter getPartner() {
		return getClubbersPresent().get(0);
	}
	
	public static boolean isPartnerSub() {
		return !getPartner().hasPersonalityTrait(PersonalityTrait.CONFIDENT);
	}

	private static void spawnClubbers(boolean submissiveClubbers) {
		NPC clubber = new DominionClubNPC(clubberGender, clubberSubspecies, clubberRaceStage, false);
				
		if(Math.random()<0.4f) {
			clubber.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				clubber.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				clubber.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		
		if(submissiveClubbers) {
			clubber.removePersonalityTrait(PersonalityTrait.SELFISH);
			clubber.removePersonalityTrait(PersonalityTrait.BRAVE);
			clubber.removePersonalityTrait(PersonalityTrait.CONFIDENT);
			if(Math.random()<0.5) {
				clubber.addPersonalityTrait(PersonalityTrait.SHY);
			}
			if(clubber.getFetishDesire(Fetish.FETISH_SUBMISSIVE).isNegative()) {
				clubber.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
			}
			clubber.removeFetish(Fetish.FETISH_DOMINANT);
			if(clubber.getFetishDesire(Fetish.FETISH_DOMINANT).isPositive()) {
				clubber.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
			}
			
		} else {
			double rnd = Math.random();
			clubber.removePersonalityTrait(PersonalityTrait.SHY);
			clubber.addPersonalityTrait(PersonalityTrait.CONFIDENT);
			if(rnd<0.33f) {
				clubber.addPersonalityTrait(PersonalityTrait.KIND);
			} else if(rnd<0.66f) {
				clubber.addPersonalityTrait(PersonalityTrait.SELFISH);
			}
			if(clubber.getFetishDesire(Fetish.FETISH_DOMINANT).isNegative()) {
				clubber.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
			}
			clubber.removeFetish(Fetish.FETISH_SUBMISSIVE);
			if(clubber.getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive()) {
				clubber.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
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
	
	public static void saveClubbers() {
		for(GameCharacter clubber : getClubbersPresent()) {
			clubber.setLocation(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL);
		}
	}
	
	public static void removeClubbers() {
		for(GameCharacter clubber : getClubbersPresent()) {
			Main.game.banishNPC((NPC) clubber);
		}
	}
	
	private static String getClubberStatus(int secondsPassedForNextScene, boolean isDominantPartner) {
		StringBuilder sb = new StringBuilder();
		
		if(hasPartner()) {
			GameCharacter clubber = getClubbersPresent().get(0);
			
			AffectionLevel al = clubber.getAffectionLevel(Main.game.getPlayer());
			if(isDominantPartner) {
				al = AffectionLevel.getAffectionLevelFromValue(domPartnerNightlyAffection);
			}
			
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
							sb.append("[npc.Name] is currently <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>tipsy</i>.");
							break;
						case TWO_MERRY:
							sb.append("[npc.Name] is currently <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>merry</i>.");
							break;
						case THREE_DRUNK:
							sb.append("[npc.Name] is currently <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>drunk</i>.");
							break;
						case FOUR_HAMMERED:
							sb.append("[npc.Name] is currently <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>hammered</i>. Giving [npc.herHim] any more alcohol would be a bad idea.");
							break;
						case FIVE_WASTED:
							sb.append("[npc.Name] is currently <i style='color:"+PresetColour.ALCOHOL.toWebHexString()+";'>wasted</i>."+(isPartnerSub()?" [npc.She] looks like [npc.sheIs] going to pass out.":""));
							break;
					}
			}

			sb.append("</br>");
			if(likesSex(clubber, isDominantPartner)) {
				sb.append("You can tell that [npc.she] wants to have sex with you...");
				
			} else if(likesGroping(clubber, isDominantPartner)) {
				if(isDominantPartner) {
					sb.append("You can tell that [npc.sheIs] about to try and get some physical contact with you...");
				} else {
					sb.append("You can tell that [npc.she] wants some physical contact...");
				}
				
			} else if(likesKiss(clubber, isDominantPartner)) {
				if(isDominantPartner) {
					sb.append("You can tell that [npc.sheIs] going to try and kiss you at any moment...");
				} else {
					sb.append("You can tell that [npc.she] wouldn't mind a kiss...");
				}
				
			} else {
				if(isDominantPartner) {
					sb.append("You can tell that [npc.sheIs] not yet ready to make a move on you...");
				} else {
					sb.append("It would be best to talk to [npc.herHim] a little before making a move...");
				}
			}
			
			sb.append("</i></p>");
			
		}
		
		int minutesPassedForNextScene = secondsPassedForNextScene/60;
		if(isEndConditionMet(minutesPassedForNextScene)) {
			sb.append(getEndingStatus(minutesPassedForNextScene));
		}
		
		return UtilText.parse(getClubbersPresent(), sb.toString());
	}
	
	private static int getKalahariBreakTimeLeft() {
		return Math.max(0, (int) (35 - (Main.game.getMinutesPassed() - Main.game.getDialogueFlags().getSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID))));
	}

	private static boolean isPartnerLeaving() {
		return hasPartner() && getPartner().getAffection(Main.game.getPlayer())<AffectionLevel.NEGATIVE_THREE_STRONG_DISLIKE.getMedianValue();
	}
	
	private static boolean isPartnerPassingOut() {
		return hasPartner() && isPartnerSub() && getPartner().getAlcoholLevelValue()>=AlcoholLevel.FIVE_WASTED.getMaximumValue()-0.05f;
	}
	
	private static String getEndingStatus(int minutesPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
		if(isPartnerLeaving()) {
			sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible([npc.Name] leaves you!)]</br>"
					+ "[npc.Name] is fed up, and with a dismissive wave of [npc.her] [npc.hand], [npc.she] turns around and leaves you!"));
		
		} else if(isPartnerPassingOut()) {
			sb.append(UtilText.parse(getClubbersPresent(), "[style.boldTerrible([npc.Name] collapses!)]</br>"
					+ "Having had far too much to drink, [npc.name] slumps down to the ground!"));
			
		} else if(!isClubOpen(minutesPassedForNextScene)) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_VIP_AREA)) {
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
				return new Response("Continue", UtilText.parse(getClubbersPresent(), "Perhaps you should have treated [npc.name] a little better..."), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(isPartnerSub()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_LEAVES", getClubbersPresent()));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_PARTNER_DOM_LEAVES", getClubbersPresent()));
						}
						removeClubbers();
					}
				};
			}
			
		} else if(isPartnerPassingOut()) {
			if(index==1) {
				return new Response("Continue", UtilText.parse(getClubbersPresent(), "[npc.Name] collapses!"), Main.game.getDefaultDialogue(false)) {
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
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(index==2) {
					if(likesSex(getPartner(), false)) {
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
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				}
				
			} else {
				if(index==1) {
					return new ResponseEffectsOnly("Leave", "It's closing time, so you need to leave the club now.") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
						}
					};
				}
			}
			return null;
			
		}
		return null;
	}
	
	private static String getKalahariStatus(boolean withBreakTime, int secondsPassedForNextScene) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'><i>");
		sb.append("Kalahari has "+(getKalahariBreakTimeLeft()-5)+" minutes of her break left.");
		sb.append("</i></p>");
		
		int minutesPassedForNextScene = secondsPassedForNextScene/60;
		if(isEndConditionMet(minutesPassedForNextScene)) {
			sb.append(getEndingStatus(minutesPassedForNextScene));
		}
		
		return sb.toString();
	}
	
	private static boolean likesKiss(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue()
				|| clubber.getAlcoholLevelValue()>0;
	}
	
	private static boolean likesGroping(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue()
				|| clubber.getAlcoholLevelValue()>AlcoholLevel.TWO_MERRY.getMinimumValue();
	}
	
	private static boolean likesSex(GameCharacter clubber, boolean isDominantPartner) {
		return (isDominantPartner?domPartnerNightlyAffection:clubber.getAffection(Main.game.getPlayer()))>=AffectionLevel.POSITIVE_THREE_CARING.getMedianValue()
				|| clubber.getAlcoholLevelValue()>AlcoholLevel.THREE_DRUNK.getMinimumValue();
	}
	
	private static final float KALAHARI_SELL_MODIFIER = 1.2f;

	public static final DialogueNode OUTSIDE = new DialogueNode("Nightlife", "Nightlife", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
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
					return new Response("Watering Hole", UtilText.parse("The nightclub, 'The Watering Hole', is currently closed. A sign by the entrance informs you that it's open from [unit.time(19)]-[unit.time(05)] every night."), null);
				} else {
					return new Response("Watering Hole", "The nightclub, 'The Watering Hole', is currently open. You could enter if you wanted to.", WATERING_HOLE_ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, false);
							Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_ENTRANCE);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode WATERING_HOLE_ENTRANCE = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!hasPartner()) {
				if(index==0) {
					return new ResponseEffectsOnly("Exit",
							"Leave 'The Watering Hole' and head back out into the district of Dominion known as 'Nightlife'.") {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NIGHTLIFE_DISTRICT);
							
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
					if(index==1) {
						return new Response("Wait", "Wait patiently in the queue to get in to the club.", WATERING_HOLE_ENTRANCE_WAITING) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								Main.game.getPlayer().setNearestLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_MAIN_AREA, false);
							}
						};
						
					} else if(index==2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.passedJules)) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("Suck cock", "You can't gain access to your mouth, so you can't suck Jules's cock!", null);
						}
						return new ResponseSex("Suck cock", "Suck Jules's cock in front of everyone in order to skip to the front of the queue.",
								true,
								false,
								new SMJulesCockSucking(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Jules.class), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
								null,
								null,
								AFTER_JULES_BLOWJOB,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_START_BLOWJOB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suckedJulesCock, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.passedJules, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.julesIntroduced, true);
								
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Jules.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
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
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else if(index==2) {
					if(likesSex(getPartner(), false)) {
						return new Response("Invite home", UtilText.parse(getClubbersPresent(), "Take [npc.name] back to your room."), RoomPlayer.AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME) {
							@Override
							public void effects() {
								for(GameCharacter clubber : getClubbersPresent()) {
									clubber.setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								}
								
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
								Main.game.setRequestAutosave(false);
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
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} 
				return null;
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_ENTRANCE_WAITING = new DialogueNode("The Watering Hole", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_ENTRANCE_WAITING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_JULES_BLOWJOB = new DialogueNode("Finished", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AFTER_JULES_BLOWJOB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_MAIN = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
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
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("Flirt", UtilText.parse(getClubbersPresent(), "Compliment [npc.namePos] appearance and start flirting with [npc.herHim]."), WATERING_HOLE_MAIN_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==3) {
					return new Response("Kiss",
							UtilText.parse(getClubbersPresent(), "Step forwards and kiss [npc.name]."+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_MAIN_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_KISS.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_KISS.getSecondsPassed(), false));
								
							}
						}
					};
					
				} else if(index==4) {
					return new Response("Feel up",
							UtilText.parse(getClubbersPresent(), "Grind up against [npc.name] and start groping [npc.herHim]."+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_MAIN_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 20));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_GROPE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_MAIN_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_MAIN_GROPE.getSecondsPassed(), false));
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
					return new Response("Search (as dom)",
							"Search for someone to approach and start talking to. This will put you in the dominant role, where you'll be the one leading your partner around the club.",
							WATERING_HOLE_SEARCH_GENDER) {
						@Override
						public void effects() {
							isSearchingForASub = true;
						}
					};
					
				} else if(index==2) {
					if(clubberGender==null || clubberSubspecies==null) {
						return new Response("Repeat search (as dom)", "You need to have already searched the club!", null);
					} else {
						return new Response("Repeat search (as dom)",
								"Repeat your last search for someone to approach and start talking to. ("+Util.capitaliseSentence(clubberGender.getName())+" "+clubberSubspecies.getName(null)+")",
								WATERING_HOLE_SEARCH_GENERATE) {
							@Override
							public void effects() {
								isSearchingForASub = true;
								spawnClubbers(true);
							}
						};
					}
					
				} else if(index==3) {
					if(getSavedClubbers(true).isEmpty()) {
						return new Response("Contacts (as dom)", "You haven't met anybody who you said you'd meet in the club again...", null);
						
					} else {
						return new Response("Contacts (as dom)",
								"Search for someone who you've already met in the club before. This will put you in the dominant role, where you'll be the one leading your partner around the club.",
								WATERING_HOLE_CONTACTS) {
							@Override
							public void effects() {
								isSearchingForASub = true;
							}
						};
					}
					
				} else if(index==4) {
					return new Response("Import (as dom)",
							"View the character import screen."
								+ " Characters imported in the following screen will be marked as being submissive, and will show up in your 'Contacts (as dom)' list after they've been imported.",
							WATERING_HOLE_IMPORT) {
						@Override
						public void effects() {
							isSearchingForASub = true;
						}
					};
					
				} if(index==6) {
					return new Response("Search (as sub)",
							"Loiter in the main area of the club and try to catch the eye of someone you like the look of."
									+ " This will put you in the submissive role, where your partner will be the one leading you around the club.",
							WATERING_HOLE_SEARCH_GENDER) {
						@Override
						public void effects() {
							isSearchingForASub = false;
						}
					};
					
				} else if(index==7) {
					if(clubberGender==null || clubberSubspecies==null) {
						return new Response("Repeat search (as sub)", "You need to have already searched the club!", null);
					} else {
						return new Response("Repeat search (as sub)",
								"Repeat your last search for someone to catch the eye of. ("+Util.capitaliseSentence(clubberGender.getName())+" "+clubberSubspecies.getName(null)+")",
								WATERING_HOLE_SEARCH_GENERATE_DOM) {
							@Override
							public void effects() {
								isSearchingForASub = false;
								spawnClubbers(false);
								resetPreviousBehaviour(); 
							}
						};
					}
					
				} else if(index==8) {
					if(getSavedClubbers(false).isEmpty()) {
						return new Response("Contacts (as sub)", "You haven't met anybody who you said you'd meet in the club again...", null);
						
					} else {
						return new Response("Contacts (as sub)",
								"Search for one of your dominant contacts who you've already met in the club before. This will put you in the submissive role, where your partner will be the one leading you around the club.",
								WATERING_HOLE_CONTACTS_DOM) {
							@Override
							public void effects() {
								isSearchingForASub = false;
							}
						};
					}
					
				} else if(index==9) {
					return new Response("Import (as sub)",
							"View the character import screen."
								+ " Characters imported in the following screen will be marked as being dominant, and will show up in your 'Contacts (as sub)' list after they've been imported.",
							WATERING_HOLE_IMPORT) {
						@Override
						public void effects() {
							isSearchingForASub = false;
						}
					};
				}
				
				return null;
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_MAIN_TALK = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
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
	
	public static final DialogueNode WATERING_HOLE_MAIN_FLIRT = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
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
	
	public static final DialogueNode WATERING_HOLE_MAIN_KISS = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
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
	
	public static final DialogueNode WATERING_HOLE_MAIN_GROPE = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
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
	
	public static final DialogueNode WATERING_HOLE_MAIN_LOSE_COMPANY = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
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
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENDER = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
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
				return new Response("Back", "Decide against looking for someone to approach.", Main.game.getDefaultDialogue(false));
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
	
	public static final DialogueNode WATERING_HOLE_SEARCH_RACE = new DialogueNode("The Watering Hole", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_RACE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "[style.colourTfPartial(Partial)]";
			} else if(index == 1) {
				return "[style.colourTfMinor(Minor)]";
			} else if(index == 2) {
				return "[style.colourTfLesser(Lesser)]";
			} else if(index == 3) {
				return "[style.colourTfGreater(Greater)]";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Decide to look for a different gender.", WATERING_HOLE_SEARCH_GENDER);
			}
			int count = 1;
			
			Set<AbstractSubspecies> subspeciesSet = new HashSet<>();
			for(Population pop : Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation()) {
				subspeciesSet.addAll(pop.getSpecies().keySet());
			}
			if(!subspeciesSet.isEmpty()) {
				List<AbstractSubspecies> sortedSubspecies = new ArrayList<>(subspeciesSet);
				sortedSubspecies.sort((s1, s2) -> s1.getRace().getName(false).compareTo(s2.getRace().getName(false)));
				for(AbstractSubspecies subspecies : sortedSubspecies) {
					if(count==index) {
						return new Response(Util.capitaliseSentence(subspecies.getName(null)),
								"Look for "+UtilText.generateSingularDeterminer(subspecies.getName(null))+" "+subspecies.getName(null)+" in amongst the crowds of revellers.",
								(isSearchingForASub
										?WATERING_HOLE_SEARCH_GENERATE
										:WATERING_HOLE_SEARCH_GENERATE_DOM)) {
							@Override
							public void effects() {
								switch(responseTab) {
									case 0:
										clubberRaceStage = RaceStage.PARTIAL;
										break;
									case 1:
										clubberRaceStage = RaceStage.PARTIAL_FULL;
										break;
									case 2:
										clubberRaceStage = RaceStage.LESSER;
										break;
									default:
										clubberRaceStage = RaceStage.GREATER;
										break;
								}
								clubberSubspecies = subspecies;
								spawnClubbers(isSearchingForASub);
							}
						};
					}
					count++;
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENERATE = new DialogueNode("The Watering Hole", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isPartnerSub()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE", getClubbersPresent())
						+getClubberStatus(this.getSecondsPassed(), false);
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE_DOM", getClubbersPresent())
						+getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_CONTACTS = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
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
			for(GameCharacter character : getSavedClubbers(true)) {
				if(count==index) {
					if(!character.isAttractedTo(Main.game.getPlayer())) {
						return new Response(character.getName(true),
								UtilText.parse(character, "[npc.Name] is [style.colourBad(no longer attracted to you)], and so would be unwilling to spend time with you in the club.<br/>([npc.She] is [npc.a_fullRace(true)].)"),
								null);
					}
					if(Main.game.getMinutesPassed()-((NPC)character).getLastTimeEncountered()<12*60) {
						return new Response(character.getName(true),
								UtilText.parse(character, "You have already met [npc.name] in the club tonight, and as such, [style.colourBad(you will not be able to encounter [npc.herHim] again until tomorrow)]."),
								null);
					}
					return new Response(character.getName(true),
							UtilText.parse(character, "Look for [npc.name] in amongst the crowds of revellers.<br/>([npc.She] is [npc.a_fullRace(true)].)"),
							WATERING_HOLE_FIND_CONTACT) {
						@Override
						public void effects() {
							character.setLocation(WorldType.NIGHTLIFE_CLUB, Main.game.getPlayer().getLocation(), false);
							if(character.getTotalTimesHadSex(Main.game.getPlayer()) == 0) {
								character.setAffection(Main.game.getPlayer(), 5);
							}
						}
					};
				}
				count++;
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_FIND_CONTACT = new DialogueNode("The Watering Hole", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FIND_CONTACT", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_IMPORT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			if(isSearchingForASub) {
				saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_IMPORT_DOM"));
			} else {
				saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_IMPORT_SUB"));
			}
			saveLoadSB.append(
					"<p>"
						+ "<table align='center'>");
			Main.getSlavesForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSlavesForImport()){
				saveLoadSB.append(getImportRow(f.getName()));
			}
			
			saveLoadSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Return to the previous screen.", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};
	
	private static String getImportRow(String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='IMPORT_CLUBBER_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Import</div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNode WATERING_HOLE_LOITER_GENERATE = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_SEATING = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(hasPartner()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_WITH_PARTNER", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
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
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("Flirt", UtilText.parse(getClubbersPresent(), "Compliment [npc.namePos] appearance and start flirting with [npc.herHim]."), WATERING_HOLE_SEATING_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==3) {
					boolean bothBipeds = true;
					if(Main.game.getPlayer().isTaur() || getPartner().isTaur()) {
						bothBipeds = false;
					}
					return new Response( // If both partners are bipeds, play footsie. If not, feeling up occurs instead.
							bothBipeds
								?"Footsie"
								:"Feel up",
							UtilText.parse(getClubbersPresent(),
									(bothBipeds
										?"Lightly push your [pc.foot] into [npc.namePos] groin."
										:"Press yourself against [npc.name] and start groping [npc.herHim].")
									+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_SEATING_FOOTSIE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FOOTSIE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FOOTSIE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_FOOTSIE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_FOOTSIE.getSecondsPassed(), false));
								
							}
						}
					};

				} else if(index==4) {
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Sex (dom)",
								UtilText.parse(getClubbersPresent(), "[npc.Name] is [style.colourBad(not attracted to you)], and so is unwilling to have sex with you..."),
								null);
					}
					if(likesSex(getPartner(), false)) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};
						
						if(Main.game.getPlayer().isTaur()) { // Player is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						return new ResponseSex("Sex (dom)",
								UtilText.parse(getClubbersPresent(), "You can't resist [npc.name] any longer! Make a move to start having dominant sex with [npc.herHim]."),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_DOM", getClubbersPresent()));
						
					} else {
						return new Response("Sex (dom)",
								UtilText.parse(getClubbersPresent(), "You can't resist [npc.name] any longer! Make a move to start having dominant sex with [npc.herHim].</br>[style.italicsBad([npc.She] might not react well to this!)]"),
								WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED.getSecondsPassed(), false));
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else if(index==5) {
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Sex (sub)",
								UtilText.parse(getClubbersPresent(), "[npc.Name] is [style.colourBad(not attracted to you)], and so is unwilling to have sex with you..."),
								null);
					}
					if(likesSex(getPartner(), false)) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};
						
						if(Main.game.getPlayer().isTaur()) {
							if(getPartner().isTaur()) { // Both taurs/arachnids:
								sm = new SexManagerDefault(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
									@Override
									public List<AbstractSexPosition> getAllowedSexPositions() {
										return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
									}
								};
							}
							
						} else if(getPartner().isTaur()) { // Partner is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), getPartner().hasPenis()?SexSlotStanding.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL_BEHIND))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						return new ResponseSex(
								"Sex (sub)",
								UtilText.parse(getClubbersPresent(), "You can't resist [npc.name] any longer! Make a move to start having submissive sex with [npc.herHim]."),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_SEX_AS_SUB", getClubbersPresent()));
						
					} else {
						return new Response("Sex (sub)",
								UtilText.parse(getClubbersPresent(), "You can't resist [npc.name] any longer! Make a move to start having submissive sex with [npc.herHim].</br>[style.italicsBad([npc.She] might not react well to this!)]"),
								WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED.getSecondsPassed(), false));
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_TALK = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_FLIRT = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_FOOTSIE = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_SEX_AS_SUB_REJECTED = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_SEX_AS_DOM_REJECTED = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "[npc.Name] has had enough for now...");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getPartner())>=getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX", getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_NO_ORGASM", getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("See again",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you hope to see [npc.herHim] again.</br>"
								+ "[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Hope not (gentle)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Make a non-committal response, secretly hoping that you won't see [npc.name] again.</br>[style.italicsBad(Removes this character from the game.)]"),
						WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Hope not (harsh)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Crudely tell [npc.name] that you were only interested in fucking [npc.herHim].</br>[style.italicsBad(Removes this character from the game.)]"),
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX_SEE_AGAIN = new DialogueNode("The Watering Hole", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_AFTER_SEX_DO_NOT_SEE_AGAIN = new DialogueNode("The Watering Hole", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
	
	public static final DialogueNode WATERING_HOLE_SEATING_LOSE_COMPANY = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
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

	public static final DialogueNode WATERING_HOLE_BAR = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
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
						+ getClubberStatus(this.getSecondsPassed(), false);
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
							Main.game.getNpc(Kalahari.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	private static String getDrinkEffects(AbstractItem drink) {
		StringBuilder sb = new StringBuilder();
		
		for(ItemEffect ie : drink.getEffects()) {
			for(String desc : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
				sb.append("</br>"+desc);
			}
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_INTRO = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_INTRO", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
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
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Water ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a bottle of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response("Water ("+UtilText.formatAsMoney(price, "span")+")", "Ask Kalahari for a bottle of "+drink.getName(false, false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(Can I get a bottle of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she sets the cold beverage down in front of you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you twist off the cap and bring the plastic bottle up to your [pc.lips]."
											+ " A faint, sweet smell informs you that this isn't any ordinary water, and as you tilt your head back and start drinking the cool liquid, the taste of vanilla overwhelms your senses."
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==2) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Beer ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a bottle of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response("Beer ("+UtilText.formatAsMoney(price, "span")+")", "Ask Kalahari for a bottle of "+drink.getName(false, false)+"."+getDrinkEffects(drink), WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(Can I get a bottle of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she sets the cold beer down in front of you."
											+ " Grabbing a nearby bottle-opener, Kalahari pops the cap off, before pushing the bottle towards you over the bar top."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you grab the cold bottle and bring it up to your [pc.lips]."
											+ " As you start gulping it down, you find that it doesn't taste anything like any other beer you've ever had, and it reminds you more of a sugary energy drink rather than any alcoholic beverage."
											+ " As the last few drops slide down your throat, a strange, musky aftertaste lingers on your tongue."
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==3) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								"Ask Kalahari for a glass of "+drink.getName(false, false)+"."+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the creamy alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you take hold of the glass and bring it up to your [pc.lips]."
											+ " The beverage gives off a rich, creamy smell, and as you greedily drink down the cool liquid,"
												+ " you're delighted to discover that it tastes every bit as good as its delicious aroma suggested it would."
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==4) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								"Ask Kalahari for a glass of "+drink.getName(false, false)+"."+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the shelves behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the strong alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you take hold of the glass and bring it up to your [pc.lips]."
											+ " The whiskey gives off a thick, musky scent, and as you start downing the liquid, you discover that the taste is almost identical to its pungent aroma."
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==5) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								"Ask Kalahari for a glass of "+drink.getName(false, false)+"."+getDrinkEffects(drink),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the shelves behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she grabs a clean glass from under the bar top."
											+ " Placing the glass before you, the lioness pours out a serving of the strong alcoholic beverage, before sliding it over the bar towards you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you take hold of the glass and bring it up to your [pc.lips],"
												+ " before tilting your head back and quickly gulping down the golden liquid."
											+ " As the last few drops slide down your throat, you notice a faint, musky dryness permeating through the alcoholic beverage, which lingers for some time as a slightly unpleasant aftertaste."
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==6) {
					if(!hasPartner()) {
						return new Response("Talk", "Lean forwards and talk to Kalahari for a little while.", WATERING_HOLE_BAR_KALAHARI_TALK) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KALAHARI_TALK.getSecondsPassed(), false));
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
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KALAHARI_FLIRT.getSecondsPassed(), false));
							}
						};
					} else {
						return new Response("Flirt", UtilText.parse(getClubbersPresent(), "You can't flirt with Kalahari while [npc.name] is with you!"), null);
					}
					
				} else if(index==8) {
					if(hasPartner()) {
						return new Response("Break", UtilText.parse(getClubbersPresent(), "You can't ask Kalahari to spend her break with you while [npc.name] is with you!"), null);
						
					} else if(Main.game.getMinutesPassed() - Main.game.getDialogueFlags().getSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID) < 60 * 12) {
							return new Response("Break", "Kalahari has already used up her break tonight!", null);
							
					} else if(!likesKiss(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("Break", "You don't know Kalahari well enough to ask her to spend her break with you. Try talking and flirting with her a little first...", null);
						
					} else {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
							return new Response("Break", "Ask Kalahari if she's got a break coming up, and if she'd like to spend it with you.", WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO) {
								@Override
								public void effects() {
									Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.krugerIntroduced, true);
									Main.game.getDialogueFlags().setSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID, Main.game.getMinutesPassed());
									Main.game.getNpc(Kruger.class).setAffection(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue()-7);
								}
							};
							
						} else {
							return new Response("Break", "Ask Kalahari if she's got a break coming up, and if she'd like to relax in the VIP area with you again.", WATERING_HOLE_BAR_KALAHARI_BREAK) {
								@Override
								public void effects() {
									Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
									Main.game.getDialogueFlags().setSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID, Main.game.getMinutesPassed());
								}
							};
						}
					}
					
				}
				
			} else {
				GameCharacter clubber = getClubbersPresent().get(0);
				
				if(index==1) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Water ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a bottle of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response("Water ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a bottle of "+drink.getName(false, false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(Can I get a bottle of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the fridges behind the bar."
											+ " Stepping back towards you, she leans forwards, flashing her cleavage and throwing you a playful wink as she sets the cold beverage down in front of you."
											+ " [kalahari.speech(Enjoy!)]"
										+ "</p>"
										+ "<p>"
											+ "Shouting out your thanks over the background noise of the club, you pass the bottle to [npc.name]."
											+ " [pc.speech(You look like you could use some water. Here you go.)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Thanks, I was getting pretty thirsty, yeah,)] [npc.name] responds, before twisting off the bottle's cap and gulping down the water."
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 1)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==2) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Beer ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a bottle of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response("Beer ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a bottle of "+drink.getName(false, false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(Can I get a bottle of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the fridges behind the bar."
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
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 2)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==3) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a glass of "+drink.getName(false, false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the fridges behind the bar."
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
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 3)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==4) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a glass of "+drink.getName(false, false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the shelves behind the bar."
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
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
										+ clubber.incrementAffection(Main.game.getPlayer(), 4)
										+ Main.game.getPlayer().incrementMoney(-price));
							}
						};
					}
				
				} else if(index==5) {
					AbstractItem drink = Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum");
					int price = (int) (drink.getValue()*KALAHARI_SELL_MODIFIER);
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "You can't afford a glass of "+drink.getName(false, false)+"!", null);
						
					} else {
						return new Response(Util.capitaliseSentence(drink.getName(false, false))+" ("+UtilText.formatAsMoney(price, "span")+")",
								UtilText.parse(getClubbersPresent(), "Ask Kalahari for a glass of "+drink.getName(false, false)+" for [npc.name]."+getDrinkEffects(drink)),
								WATERING_HOLE_BAR_DRINK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parse(getClubbersPresent(), 
										"<p>"
											+ "[pc.speech(Can I get a glass of "+drink.getName(false, false)+"?)] you call out over the noise of the club to Kalahari."
										+ "</p>"
										+ "<p>"
											+ "[kalahari.speech(Sure thing hun!)] the lioness responds. [kalahari.speech(That'll be "+Util.intToString(price)+" flames.)]"
										+ "</p>"
										+ "<p>"
											+ "Handing over the money to Kalahari, you watch as she turns around and grabs a bottle of "+drink.getName(false, false)+" from one of the shelves behind the bar."
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
										+ "</p>"));
								
								Main.game.getTextStartStringBuilder().append(
										drink.applyEffect(clubber, clubber)
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
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_TALK.getSecondsPassed(), false));
						}
					};
					
				} else if(index==7) {
					return new Response("Flirt", UtilText.parse(getClubbersPresent(), "Start flirting with [npc.name]."), WATERING_HOLE_BAR_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_FLIRT.getSecondsPassed(), false));
						}
					};
					
				} else if(index==8) {
					return new Response("Kiss",
							UtilText.parse(getClubbersPresent(), "Step forwards and kiss [npc.name]."+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_BAR_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KISS", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KISS_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KISS.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KISS_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -15));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_KISS.getSecondsPassed(), false));
								
							}
						}
					};
					
				} else if(index==9) {
					return new Response("Feel up",
							UtilText.parse(getClubbersPresent(), "Press yourself against [npc.name] and start groping [npc.herHim]."+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_BAR_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_GROPE", getClubbersPresent())
										+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_GROPE_CONTENT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_GROPE.getSecondsPassed(), false));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_GROPE_REJECTED", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(getPartner().incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_BAR_GROPE.getSecondsPassed(), false));
								
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
	
	public static final DialogueNode WATERING_HOLE_BAR_TALK = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_BAR_FLIRT = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_BAR_KISS = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_BAR_GROPE = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_BAR_LOSE_COMPANY = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
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
	
	public static final DialogueNode WATERING_HOLE_BAR_DRINK = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
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
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
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

	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_TALK = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_FLIRT = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_FLIRT")
					+ "<p>"
						+ Main.game.getNpc(Kalahari.class).getBreastDescription()
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
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO")
					+ getKalahariStatus(true, this.getSecondsPassed());
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
							Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
							Main.game.getNpc(Kalahari.class).equipClothing();
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
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_TALK.getSecondsPassed()));
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
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_FLIRT.getSecondsPassed()));
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Kiss", "Kalahari is only interested in having sex right now!", null);
						
					} else if(!likesKiss(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("Kiss", "You can tell that Kalahari doesn't want a kiss at the moment. It would be best to get to know her a little better first.", null);
						
					} else {
						return new Response("Kiss", "Lean forwards and kiss Kalahari.", WATERING_HOLE_KALAHARI_BREAK_KISS) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 15));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_KISS.getSecondsPassed()));
							}
						};
					}
					
				} else if(index==4) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Feel up", "Kalahari is only interested in having sex right now!", null);
						
					} else if(!likesGroping(Main.game.getNpc(Kalahari.class), false)) {
						return new Response("Feel up", "You can tell that Kalahari would react badly to any dominant move to feel her up. It would be best to spend some time flirting with her first.", null);
						
					} else {
						return new Response("Feel up", "Press yourself against Kalahari and start feeling her up.", WATERING_HOLE_KALAHARI_BREAK_GROPE) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kalahari.class).incrementAffection(Main.game.getPlayer(), 20));
								Main.game.getTextEndStringBuilder().append(getKalahariStatus(false, WATERING_HOLE_KALAHARI_BREAK_GROPE.getSecondsPassed()));
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer(), true);
								Main.game.getNpc(Kalahari.class).setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, true);
								Main.game.getNpc(Kalahari.class).displaceClothingForAccess(CoverableArea.BREASTS, null);
								Main.game.getNpc(Kalahari.class).displaceClothingForAccess(CoverableArea.VAGINA, null);
							}
						};
					}
					
				} else if(index==5) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kalahariWantsSex)) {
						return new Response("Sex (dom)", "You need to get Kalahari fully into the mood before having sex with her.", null);
					} else {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kalahari.class), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						};
						if(Main.game.getPlayer().isTaur()) { // Player is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kalahari.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							};
						}
						
						return new ResponseSex("Sex (dom)",
								!Main.game.getPlayer().isTaur()
									?"Pull Kalahari into your lap and start having dominant sex with her."
									:"Stand up, pulling Kalahari to her feet as you do so, and start having dominant sex with her.",
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_DOM"));
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
//										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kruger.class), SexPositionSlot.DOGGY_BEHIND)),
//										Util.newHashMapOfValues(
//												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
//												new Value<>(Main.game.getNpc(Kalahari.class), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
//								WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX,
//								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_SEX_AS_SUB"));
//					}
//					
//				}
				else if(index==0) {
					return new Response("Finish", "Let Kalahari get back to work.", WATERING_HOLE_KALAHARI_BREAK_END) {
						@Override
						public void effects() {
							Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kalahariWantsSex, false);
							Main.game.getNpc(Kalahari.class).equipClothing();
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK")
					+ getKalahariStatus(true, this.getSecondsPassed());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	

	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_TALK = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_FLIRT = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_KISS = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_GROPE = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE")
					+ "<p>"
						+ Main.game.getNpc(Kalahari.class).getBreastDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE_AFTER_BREAST_REVEAL")
						+ Main.game.getNpc(Kalahari.class).getVaginaDescription()
					+ "</p>"
					+ UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_KALAHARI_BREAK_GROPE_AFTER_PUSSY_REVEAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_BAR_KALAHARI_BREAK_KRUGER_INTRO.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX = new DialogueNode("Finished", "Kalahari needs to get back to work...", true) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Kalahari.class))>=Main.game.getNpc(Kalahari.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_BAR_KALAHARI_BREAK_AFTER_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Head back out into the main area of the club.", WATERING_HOLE_MAIN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_MAIN_AREA, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_END = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_KALAHARI_BREAK_OUT_OF_TIME = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	
	public static final DialogueNode WATERING_HOLE_VIP = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.krugerIntroduced)) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_BLOCKED", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
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
						
					} else if(Main.game.getNpc(Kruger.class).getLastTimeHadSex() >= Main.game.getMinutesPassed()-(60*12)) {
						return new Response("Kruger", "You've already had sex with Kruger tonight."
								+ " Although you wouldn't mind having some more fun with him, you can tell that his patience would soon wear thin if you keep going back to him."
								+ " You can always come back and ride his cock tomorrow night.", null);
						
					} else {
						return new Response("Kruger", "Walk up to Kruger and say hello.", WATERING_HOLE_VIP_KRUGER) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER"));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER.getSecondsPassed(), false));
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Kruger.class), true);
							}
						};
					}
				}
				return null;
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER = new DialogueNode("The Watering Hole", "", true) {
		
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
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				return new Response("Talk", "Talk to Kruger for a little while.", WATERING_HOLE_VIP_KRUGER_TALK) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_TALK.getSecondsPassed(), false));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isFeminine()) {
					return new Response("Flirt", "Flirt with Kruger.", WATERING_HOLE_VIP_KRUGER_FLIRT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_FLIRT.getSecondsPassed(), false));
						}
					};
				} else {
					return new Response("Flirt", "Kruger is gynephilic, so isn't interested in doing anything sexual with you.", null);
				}
				
			} else if(index==3) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Kissed", "Kruger is gynephilic, so isn't interested in doing anything sexual with you.", null);
					
				} else if(!likesKiss(Main.game.getNpc(Kruger.class), false)) {
					return new Response("Kissed", "Kruger doesn't seem to be interested in kissing you.", null);
					
				} else {
					return new Response("Kissed", "You can tell that Kruger wants to kiss you. Lean forwards and let him.", WATERING_HOLE_VIP_KRUGER_KISSED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 15));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_KISSED.getSecondsPassed(), false));
						}
					};
				}
				
			} else if(index==4) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Felt up", "Kruger is gynephilic, so isn't interested in doing anything sexual with you.", null);
					
				} else if(!likesGroping(Main.game.getNpc(Kruger.class), false)) {
					return new Response("Felt up", "Kruger doesn't seem to be interested in feeling you up at the moment.", null);
					
				} else {
					return new Response("Felt up", "You can tell that Kruger wants to have some physical contact with you. Shuffle up beside him and let him feel you up.", WATERING_HOLE_VIP_KRUGER_FELT_UP) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kruger.class).incrementAffection(Main.game.getPlayer(), 20));
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_VIP_KRUGER_FELT_UP.getSecondsPassed(), false));
						}
					};
					
				}
				
			} else if(index==5) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Sex (sub)", "Kruger is gynephilic, so isn't interested in doing anything sexual with you.", null);
					
				} else if(!likesSex(Main.game.getNpc(Kruger.class), false)) {
					return new Response("Sex (sub)", "Kruger doesn't seem to be interested in having sex with you at the moment.", null);
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
							&& (!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
						return new Response("Sex (sub)",
								"Kruger needs to be able to access"
									+ (Main.game.getPlayer().hasVagina()
										?(Main.game.isAnalContentEnabled()
											?" your mouth, pussy, or asshole"
											:" either your mouth or pussy")
										:(Main.game.isAnalContentEnabled()
											?" either your mouth or asshole"
											:" your mouth"))
								+ " to be able to have sex with you!",
								null);
					}
					return new ResponseSex("Sex (sub)", "Tell Kruger that you want him to fuck you...",
							true, true,
							new SMKrugerChair(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kruger.class), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
							null,
							null,
							WATERING_HOLE_VIP_KRUGER_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_SEX_AS_SUB")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
							} else if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
							} else {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kruger.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, true, true));
							}
						}
					};
				}
				
			} else if(index==0) {
				return new Response("Leave", "Tell Kruger that you need to leave.", WATERING_HOLE_VIP_KRUGER_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_TALK = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
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
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_FLIRT = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
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
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_KISSED = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_FELT_UP = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_AFTER_SEX = new DialogueNode("Finished", "Kruger is finished with you, and, being exhausted from the sex, you readily allow him to push you off of him.", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_VIP_KRUGER_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_VIP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_VIP_KRUGER_LEAVE = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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

	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DANCE_FLOOR", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
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
							Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_DANCE_FLOOR_DANCE.getSecondsPassed(), false));
						}
					};
					
				} else if(index==2) {
					return new Response("Kiss",
							UtilText.parse(getClubbersPresent(), "Step forwards and kiss [npc.name]."+(likesKiss(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_DANCE_FLOOR_KISS) {
						@Override
						public void effects() {
							if(likesKiss(getPartner(), false)) {
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
							UtilText.parse(getClubbersPresent(), "Grind up against [npc.name] and start groping [npc.herHim]."+(likesGroping(getPartner(), false)?"":"</br>[style.italicsBad([npc.She] might not react well to this!)]")),
							WATERING_HOLE_DANCE_FLOOR_GROPE) {
						@Override
						public void effects() {
							if(likesGroping(getPartner(), false)) {
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
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_DANCE = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
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
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_KISS = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_GROPE = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DANCE_FLOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DANCE_FLOOR_LOSE_COMPANY = new DialogueNode("The Watering Hole", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
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

	public static final DialogueNode WATERING_HOLE_TOILETS = new DialogueNode("Toilets", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
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
					if(!getPartner().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Stall sex",
								UtilText.parse(getClubbersPresent(), "[npc.Name] is [style.colourBad(not attracted to you)], and so is unwilling to have sex with you..."),
								null);
					}
					if(likesSex(getPartner(), false)) {
						return new ResponseSex("Stall sex", UtilText.parse(getClubbersPresent(), "Try and get [npc.name] to have sex in one of the toilet's stalls."),
								true, true,
								new SMStallSex(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								null,
								null,
								WATERING_HOLE_TOILETS_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_SEX", getClubbersPresent()));
						
					} else {
						return new Response("Stall sex",
								UtilText.parse(getClubbersPresent(), "Try and get [npc.name] to have sex in one of the toilet's stalls.</br>[style.italicsBad([npc.She] might not react well to this!)]"),
								WATERING_HOLE_TOILETS_SEX_REJECTED) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getClubbersPresent().get(0).incrementAffection(Main.game.getPlayer(), -25));
								Main.game.getTextEndStringBuilder().append(getClubberStatus(WATERING_HOLE_TOILETS_SEX_REJECTED.getSecondsPassed(), false));
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
					
				} else if(index==2) {
					List<InventorySlot> washSlots = Util.newArrayListOfValues(InventorySlot.HEAD, InventorySlot.EYES, InventorySlot.MOUTH, InventorySlot.NECK, InventorySlot.HAIR, InventorySlot.FINGER, InventorySlot.HAND, InventorySlot.WRIST);
					return new Response("Wash",
							"Use the sinks to wash your hands and face."
								+ "<br/>[style.italicsGood(This will clean your "+Util.inventorySlotsToParsedStringList(washSlots, Main.game.getPlayer())+", as well as any clothing worn in these slots.)]"
								+ "<br/>[style.italicsMinorBad(This does <b>not</b> clean companions.)]",
								WATERING_HOLE_TOILETS_WASH) {
						@Override
						public void effects() {
							for(InventorySlot slot : washSlots) {
								Main.game.getPlayer().removeDirtySlot(slot, true);
								AbstractClothing c = Main.game.getPlayer().getClothingInSlot(slot);
								if(c!=null) {
									c.setDirty(Main.game.getPlayer(), false);
								}
							}
						}
					};
					
				} else if(index==3) {
					boolean penisAvailable = Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
					boolean vaginaAvailable = Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
					
					if((penisAvailable && !Main.game.getPlayer().isTaur()) || vaginaAvailable) {
						return new Response("Glory hole (use)",
								"A couple of the toilet's stalls have glory holes in them. Step up to one and have the person on the other side service you.",
								WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY) {
							@Override
							public void effects() {
								spawnSubGloryHoleNPC("stranger");
							}
						};
						
					} else if(penisAvailable && Main.game.getPlayer().isTaur()) {
						return new Response("Glory hole (use)",
								"Due to the shape of your [pc.legRace]'s body, you cannot get into a suitable position for using the glory hole...",
								null);
						
					} else {
						return new Response("Glory hole (use)",
								"You can't get access to your genitals, so can't get serviced at a glory hole.",
								null);
					}
//					
//					if((Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
//							|| (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
//							|| (!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))) {
//						return new Response("Glory hole (use)",
//								"A couple of the toilet's stalls have glory holes in them. Step up to one and have the person on the other side service you.",
//								WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY) {
//							@Override
//							public void effects() {
//								spawnSubGloryHoleNPC("stranger");
//							}
//						};
//						
//					} else {
//						return new Response("Glory hole (use)",
//								"You can't get access to your genitals, so can't get serviced at a glory hole.",
//								null);
//					}
					
					
				} else if(index==4) {
					if((Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true))
							|| (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
							|| (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
						return new Response("Glory hole (service)",
								"A couple of the toilet's stalls have glory holes in them. Kneel down and get ready to service whatever comes through the holes.",
								WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY) {
							@Override
							public void effects() {
								spawnDomGloryHoleNPC("stranger");
								spawnDomGloryHoleNPC("stranger");
							}
						};
					
					} else {
						return new Response("Glory hole (service)",
								"You can't get access to your mouth, genitals, or asshole, so can't service any strangers at the glory holes.",
								null);
					}
					
					
				}
				else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY = new DialogueNode("Toilets", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_USING_GET_READY", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "On second thoughts, you don't really want some stranger having fun with your private parts...", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
				
			} else if(index==1) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("Use glory hole", UtilText.parse(characters.get(0), "Do as [npc.name] says and step up to the glory hole."),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_USING", characters));
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX = new DialogueNode("Toilets", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_USING_POST_SEX", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Walk out of the stall.", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY = new DialogueNode("Toilets", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_GET_READY", getGloryHoleCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "On second thoughts, you don't really want to suck the cocks of some strangers...", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
				
			} else if(index==1) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("Start (close door)", "Close the door, affording yourself some privacy as you start to service the cocks before you.",
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(
										new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
										new Value<>(characters.get(1), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_SERVICING", characters));
				
			} else if(index==2) {
				List<GameCharacter> characters = getGloryHoleCharacters();
				
				return new ResponseSex("Start (public view)", "Leave the door open, so that everyone in the toilets can watch as you service the cocks before you.",
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(
										new Value<>(characters.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
										new Value<>(characters.get(1), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
						},
						null,
						null,
						WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_START_SERVICING_PUBLIC", characters));
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX = new DialogueNode("Toilets", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.sex.isPublicSex()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX_PUBLIC", getGloryHoleCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_GLORY_HOLE_SERVICING_POST_SEX", getGloryHoleCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Walk out of the stall.", WATERING_HOLE_TOILETS) {
					@Override
					public void effects() {
						for(GameCharacter npc : getGloryHoleCharacters()) {
							Main.game.banishNPC((NPC) npc);
						}
					}
				};
			}
			return null;
		}
	};
	
	private static String gloryholeNpcNameDescriptor ="";
	private static void spawnDomGloryHoleNPC(String genericName) {
		NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, true), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s.isNonBiped()) {
			@Override
			public void turnUpdate() {
				if(this.getGenitalArrangement()==GenitalArrangement.NORMAL) { // Hide ass areas if normal genitals (not entirely sure why this was added...)
					this.setAreaKnownByCharacter(CoverableArea.ASS, Main.game.getPlayer(), false);
					this.setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), false);
				}
			}
		};
		
		npc.setRaceConcealed(true);
		
		double rnd = Math.random();
		if(rnd<0.1f && !gloryholeNpcNameDescriptor.equals("wasted")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			gloryholeNpcNameDescriptor ="wasted";
			npc.setGenericName("wasted "+genericName);
			
		} else if(Math.random()<0.3f && !gloryholeNpcNameDescriptor.equals("drunk")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			gloryholeNpcNameDescriptor ="drunk";
			npc.setGenericName("drunk "+genericName);
			
		} else if(Math.random()<0.4f && !gloryholeNpcNameDescriptor.equals("tipsy")) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			gloryholeNpcNameDescriptor ="tipsy";
			npc.setGenericName("tipsy "+genericName);
			
		} else {
			gloryholeNpcNameDescriptor = Main.game.getCharacterUtils().setGenericName(npc, genericName, Util.newArrayListOfValues(gloryholeNpcNameDescriptor));
		}
		
		npc.setDescription("[npc.Name] is one of the Water Hole's patrons, who, seeking to take a break from the club floor, has wandered into the toilets to find you servicing the glory holes...");
		
		if(Math.random()<0.4f) {
			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.TWO_NEUTRAL);
		npc.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		npc.removeFetish(Fetish.FETISH_NON_CON_SUB);
		if(npc.hasVagina()) {
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(npc.hasPenis()) {
			npc.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
		}

		npc.unequipAllClothingIntoVoid(true, true);
		
		npc.setPenisVirgin(false);
		npc.setVaginaVirgin(false);
		
		npc.setAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer(), false);
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void spawnSubGloryHoleNPC(String genericName) {
		NPC npc = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, (s)->s.isNonBiped());
		
		npc.setRaceConcealed(true);
		
		List<String> descriptors;
		double rnd = Math.random();
		if(rnd<0.1f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), npc, false);
			descriptors = Util.newArrayListOfValues("wasted", "intoxicated");
			
		} else if(Math.random()<0.3f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), npc, false);
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			descriptors = Util.newArrayListOfValues("drunk");
			
		} else if(Math.random()<0.4f) {
			npc.useItem(Main.game.getItemGen().generateItem("innoxia_race_horse_equine_cider"), npc, false);
			descriptors = Util.newArrayListOfValues("tipsy");
			
		} else {
			descriptors = Util.newArrayListOfValues("horny", "desperate", "horny");
		}
		npc.setGenericName(Util.randomItemFrom(descriptors)+" "+genericName);
		
		npc.setDescription("[npc.Name] is one of the Water Hole's patrons, who, seeking to take a break from the club floor, has wandered into the toilets to service the glory holes...");
		
		if(Math.random()<0.4f) {
			npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		} else {
			if(Main.game.getPlayer().isFeminine()) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
			}
		}
		npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.TWO_NEUTRAL);
		npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
		npc.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		npc.removeFetish(Fetish.FETISH_NON_CON_SUB);
		if(npc.hasVagina()) {
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		}
		if(Math.random()>0.75f) {
			npc.addFetish(Fetish.FETISH_ORAL_GIVING);
		}
		
		npc.unequipAllClothingIntoVoid(true, true);
		
		npc.setPenisVirgin(false);
		npc.setVaginaVirgin(false);
		npc.setAssVirgin(false);
		npc.setFaceVirgin(false);
		
		npc.setAllAreasKnownByCharacter(Main.game.getPlayer(), false);
		
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final DialogueNode WATERING_HOLE_TOILETS_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "[npc.Name] has had enough for now...");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getPartner())>=getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_NO_ORGASM", getClubbersPresent())
						+ getClubberStatus(this.getSecondsPassed(), false);
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("See again",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you hope to see [npc.herHim] again.</br>"
								+ "[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Hope not (gentle)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Make a non-committal response, secretly hoping that you won't see [npc.name] again.</br>[style.italicsBad(Removes this character from the game.)]"),
						WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Hope not (harsh)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Crudely tell [npc.name] that you were only interested in fucking [npc.herHim].</br>[style.italicsBad(Removes this character from the game.)]"),
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
	
	public static final DialogueNode WATERING_HOLE_TOILETS_AFTER_SEX_SEE_AGAIN = new DialogueNode("The Watering Hole", "", false, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_SEX_REJECTED = new DialogueNode("Toilets", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_SEX_REJECTED", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_TOILETS_USE = new DialogueNode("Toilets", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_USE", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode WATERING_HOLE_TOILETS_WASH = new DialogueNode("Toilets", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return isEndConditionMet(0);
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_TOILETS_WASH", getClubbersPresent())
					+ getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_TOILETS.getResponse(responseTab, index);
		}
	};
	
	// Dom partner:
	
	public static final DialogueNode WATERING_HOLE_SEARCH_GENERATE_DOM = new DialogueNode("The Watering Hole", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_SEARCH_GENERATE_DOM", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DOM_PARTNER_REACT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WATERING_HOLE_CONTACTS_DOM = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 0*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_CONTACTS_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Decide to stop searching for someone you've met before.", WATERING_HOLE_MAIN);
			}
			int count = 1;
			for(GameCharacter character : getSavedClubbers(false)) {
				if(count==index) {
					if(!character.isAttractedTo(Main.game.getPlayer())) {
						return new Response(character.getName(true),
								UtilText.parse(character, "[npc.Name] is [style.colourBad(no longer attracted to you)], and so would be unwilling to spend time with you in the club.<br/>([npc.She] is [npc.a_fullRace(true)].)"),
								null);
					}
					if(Main.game.getMinutesPassed()-((NPC)character).getLastTimeEncountered()<12*60) {
						return new Response(character.getName(true),
								UtilText.parse(character, "You have already met [npc.name] in the club tonight, and as such, [style.colourBad(you will not be able to encounter [npc.herHim] again until tomorrow)]."),
								null);
					}
					return new Response(character.getName(true),
							UtilText.parse(character, "Look for [npc.name] in amongst the crowds of revellers.<br/>([npc.She] is [npc.a_fullRace(true)].)"),
							WATERING_HOLE_FIND_CONTACT_DOM) {
						@Override
						public void effects() {
							character.setLocation(WorldType.NIGHTLIFE_CLUB, Main.game.getPlayer().getLocation(), false);
//							character.setAffection(Main.game.getPlayer(), 5);
							domPartnerNightlyAffection = 5;
							resetPreviousBehaviour(); 
						}
					};
				}
				count++;
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_FIND_CONTACT_DOM = new DialogueNode("The Watering Hole", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_FIND_CONTACT_DOM", getClubbersPresent())
					+getClubberStatus(this.getSecondsPassed(), true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return WATERING_HOLE_DOM_PARTNER_REACT.getResponse(responseTab, index);
		}
	};

	
	private static ClubberBehaviour currentBehaviour = ClubberBehaviour.INTRODUCTION;
	private static int turnsAtPlace = 0;
	private static boolean buyingDrinks = true;
	private static int domPartnerNightlyAffection = 0;
	
	private static String incrementDominantPartnerAffection(int increment) {
		domPartnerNightlyAffection += increment;
		domPartnerNightlyAffection = Math.max(-100, Math.min(100, domPartnerNightlyAffection));
		if(domPartnerNightlyAffection<=getPartner().getAffection(Main.game.getPlayer()) && domPartnerNightlyAffection<100) {
			return "";
		}
		return getPartner().incrementAffection(Main.game.getPlayer(), increment);
	}
	
	private static void resetPreviousBehaviour() {
		currentBehaviour = ClubberBehaviour.INTRODUCTION;
		turnsAtPlace = 0;
		buyingDrinks = true;
	}
	
	private static AbstractPlaceType getCurrentPlaceType() {
		return Main.game.getPlayer().getLocationPlace().getPlaceType();
	}
	
	private static boolean isWillingToMoveLocation() {
		return turnsAtPlace>=2 || currentBehaviour == ClubberBehaviour.INTRODUCTION;
	}
	
	private static boolean isPartnerOfferingDrinks() {
		if(buyingDrinks) {
			if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.FOUR_HAMMERED.getMinimumValue();
				
			} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.ONE_TIPSY.getMinimumValue();
				
			} else {
				return Main.game.getPlayer().getAlcoholLevelValue() < AlcoholLevel.THREE_DRUNK.getMinimumValue();
			}
		}
		return false;
	}
	
	/*
		Sleazy - bar, dance floor, toilets
			Wants you wasted
			Doesn't care about talking
			Likes feeling up/kissing
			Wants toilet sex
		Normal - bar, dance floor, seating area
			Wants you drunk
			Needs to talk -> flirt -> kiss -> grope
			Seating area sex
		Nice - bar, seating area, home
			Wants you tipsy
			Needs to talk -> flirt -> kiss
			Invites you home
	 */
	
	private static ClubberBehaviour getClubberBehaviour() {

		if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) { // Only goes to: Bar, dance floor, and toilets.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
				&& isPartnerOfferingDrinks()
				&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if(isWillingToMoveLocation() && likesSex(getPartner(), true)) {
				return ClubberBehaviour.TOILETS;
				
			}
			if(!isWillingToMoveLocation()) {
				if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
				} else {
					if(likesKiss(getPartner(), true)) {
						if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
							return ClubberBehaviour.BAR_GROPE;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					} else {
						if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
							return ClubberBehaviour.BAR_FLIRT;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					}
				}
				
			} else {
				if(getCurrentPlaceType()!=ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
				} else {
					if(likesKiss(getPartner(), true)) {
						if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
							return ClubberBehaviour.BAR_GROPE;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					} else {
						if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
							return ClubberBehaviour.BAR_FLIRT;
						} else {
							return ClubberBehaviour.BAR_KISS;
						}
					}
				}
			}
			
		} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) { // Only goes to: Bar, seating area.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
					&& isPartnerOfferingDrinks()
					&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if(likesSex(getPartner(), true)) {
				if(getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_INVITE_HOME.getPlaceType()) {
					return ClubberBehaviour.SIT_DOWN_INVITE_HOME;
				} else {
					return ClubberBehaviour.BAR_INVITE_HOME;
				}
			}
			if(likesGroping(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_FOOTSIE.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FOOTSIE) {
						return ClubberBehaviour.SIT_DOWN_FOOTSIE;
					} else {
						return ClubberBehaviour.SIT_DOWN_KISS;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
						return ClubberBehaviour.BAR_GROPE;
					} else {
						return ClubberBehaviour.BAR_KISS;
					}
				}
			}
			if(likesKiss(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_KISS.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_KISS) {
						return ClubberBehaviour.SIT_DOWN_KISS;
					} else {
						return ClubberBehaviour.SIT_DOWN_FLIRT;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_KISS) {
						return ClubberBehaviour.BAR_KISS;
					} else {
						return ClubberBehaviour.BAR_FLIRT;
					}
				}
			}
			if((getCurrentPlaceType()==ClubberBehaviour.BAR_FLIRT.getPlaceType() || isWillingToMoveLocation()) && isPartnerOfferingDrinks()) {
				if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
					return ClubberBehaviour.BAR_FLIRT;
				} else {
					return ClubberBehaviour.BAR_TALK;
				}
				
			} else {
				if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FLIRT) {
					return ClubberBehaviour.SIT_DOWN_FLIRT;
				} else {
					return ClubberBehaviour.SIT_DOWN_TALK;
				}
			}
				
		} else { // Only goes to: Bar, dance floor, and seating area.
			if(currentBehaviour!=ClubberBehaviour.BAR_DRINK
					&& isPartnerOfferingDrinks()
					&& (getCurrentPlaceType()==ClubberBehaviour.BAR_DRINK.getPlaceType() || isWillingToMoveLocation())) {
				return ClubberBehaviour.BAR_DRINK;
			}
			
			if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_SEX.getPlaceType()) && likesSex(getPartner(), true)) {
				return ClubberBehaviour.SIT_DOWN_SEX;
			}
			if(likesGroping(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_FOOTSIE.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_FOOTSIE) {
						return ClubberBehaviour.SIT_DOWN_FOOTSIE;
					} else {
						return ClubberBehaviour.SIT_DOWN_KISS;
					}
					
				} else if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_GROPE) {
						return ClubberBehaviour.DANCE_GROPE;
					} else {
						return ClubberBehaviour.DANCE_KISS;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_GROPE) {
						return ClubberBehaviour.BAR_GROPE;
					} else {
						return ClubberBehaviour.BAR_KISS;
					}
				}
			}
			if(likesKiss(getPartner(), true)) {
				if((isWillingToMoveLocation() || getCurrentPlaceType()==ClubberBehaviour.SIT_DOWN_KISS.getPlaceType())) {
					if(currentBehaviour!=ClubberBehaviour.SIT_DOWN_KISS) {
						return ClubberBehaviour.SIT_DOWN_KISS;
					} else {
						return ClubberBehaviour.SIT_DOWN_FLIRT;
					}
					
				} else if(getCurrentPlaceType()==ClubberBehaviour.DANCE_GROPE.getPlaceType()) {
					if(currentBehaviour!=ClubberBehaviour.DANCE_KISS) {
						return ClubberBehaviour.DANCE_KISS;
					} else {
						return ClubberBehaviour.DANCE;
					}
					
				} else {
					if(currentBehaviour!=ClubberBehaviour.BAR_KISS) {
						return ClubberBehaviour.BAR_KISS;
					} else {
						return ClubberBehaviour.BAR_FLIRT;
					}
				}
			}
			if(getCurrentPlaceType()==ClubberBehaviour.DANCE.getPlaceType() || !isWillingToMoveLocation()) { // If dancing, just move back to bar
				if(currentBehaviour!=ClubberBehaviour.BAR_FLIRT) {
					return ClubberBehaviour.BAR_FLIRT;
				} else {
					return ClubberBehaviour.BAR_TALK;
				}
				
			} else {
				return ClubberBehaviour.DANCE;
			}
		}
	}
	
	private static void applyBehaviourEffects() {
		ClubberBehaviour newBehaviour = getClubberBehaviour();

		turnsAtPlace++;
		
		if(currentBehaviour.getPlaceType()!=newBehaviour.getPlaceType()) {
			if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_BAR)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_BAR", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_DANCE_FLOOR)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_DANCE_FLOOR", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_SEATING_AREA", getClubbersPresent()));
				
			} else if(newBehaviour.getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS)) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_CHANGE_LOCATION_TOILETS", getClubbersPresent()));
			}

			turnsAtPlace=0;
		}
		
		currentBehaviour = newBehaviour;
		
		Main.game.getPlayer().setLocation(WorldType.NIGHTLIFE_CLUB, currentBehaviour.getPlaceType());
	}
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER = new DialogueNode("The Watering Hole", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			ClubberBehaviour behaviour = currentBehaviour;

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_"+behaviour.toString(), getClubbersPresent()));

			UtilText.nodeContentSB.append(getClubberStatus(this.getSecondsPassed(), true));
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
//			if(isEndConditionMet(0)) {
//				return getEndResponse(index, 0);
//			}
			
			ClubberBehaviour behaviour = currentBehaviour;
			
			switch(behaviour) {
				case BAR_DRINK:
					if(getPartner().hasPersonalityTrait(PersonalityTrait.SELFISH)) {
						if(index==1) {
							// Accept rum
							return new Response("Accept rum",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Accept the glass of Black Rat's Rum that [npc.name] has offered you.<br/>"
											+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									getPartner().incrementAlcoholLevel(-0.05f); // TO stop them from drinking to collapse
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_RUM", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
							
						} else if(index==2) {
							// Refuse rum
							return new Response("Refuse rum",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Refuse the glass of Black Rat's Rum that [npc.name] has offered you."),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_RUM", getClubbersPresent()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
									buyingDrinks = false;
								}
							};
						}
						
					} else if(getPartner().hasPersonalityTrait(PersonalityTrait.KIND)) {
						if(index==1) {
							// Accept Feline's fancy/beer
							return new Response((Main.game.getPlayer().isFeminine()
										?"Accept Feline's Fancy"
										:"Accept Canine Crush"),
									(Main.game.getPlayer().isFeminine()
										?UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Accept the glass of Feline's Fancy that [npc.name] has offered you.<br/>"
												+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy")))
										:UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Accept the bottle of Canine Crush that [npc.name] has offered you.<br/>"
												+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush")))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									if(Main.game.getPlayer().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_FELINES_FANCY", getClubbersPresent()));
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_BEER", getClubbersPresent()));
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									}
									if(getPartner().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy").applyEffect(getPartner(), getPartner()));
									} else {
										Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush").applyEffect(getPartner(), getPartner()));
									}
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
						} else if(index==2) {
							// Refuse Feline's fancy/beer
							return new Response((Main.game.getPlayer().isFeminine()
										?"Refuse Feline's Fancy"
										:"Refuse Canine Crush"),
									(Main.game.getPlayer().isFeminine()
											?UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Refuse the glass of Feline's Fancy that [npc.name] has offered you.")
											:UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Refuse the bottle of Canine Crush that [npc.name] has offered you.")),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									if(Main.game.getPlayer().isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_FELINES_FANCY", getClubbersPresent()));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_BEER", getClubbersPresent()));
									}
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
									buyingDrinks = false;
								}
							};
						}
						
					} else {
						if(index==1) {
							// Accept whiskey
							return new Response("Accept whiskey",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Accept the glass of Wolf Whiskey that [npc.name] has offered you.<br/>"
										+getDrinkEffects(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"))),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_ACCEPT_WOLF_WHISKEY", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
								}
							};
							
						} else if(index==2) {
							// Refuse whiskey
							return new Response("Refuse whiskey",
									UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Refuse the glass of Wolf Whiskey that [npc.name] has offered you."),
									WATERING_HOLE_DOM_PARTNER_REACT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_REFUSE_WOLF_WHISKEY", getClubbersPresent()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(Main.game.getPlayer(), Main.game.getPlayer()));
									Main.game.getTextStartStringBuilder().append(Main.game.getItemGen().generateItem("innoxia_race_human_vanilla_water").applyEffect(getPartner(), getPartner()));
									Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
									buyingDrinks = false;
								}
							};
						}
					}
					break;
				case BAR_FLIRT:
					if(index==1) {
						// Flirt back
						return new Response("Flirt back",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Respond positively to [npc.namePos] flirtatious remarks, and flirt back with [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_FLIRT_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
						
					} else if(index==2) {
						// Shut down
						return new Response("Show disdain",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Respond negatively to [npc.namePos] flirtatious remarks, and tell [npc.herHim] to stop."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_FLIRT_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case BAR_GROPE:
					if(index==1) {
						// Let them grope you
						return new Response("Submit",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Lean in against [npc.name] and let [npc.herHim] feel you up."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_GROPE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Push away",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] to get away from you."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_GROPE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case BAR_INVITE_HOME:
					if(index==1) {
						return new Response("Follow home",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Follow [npc.name] back to [npc.her] house, where [npc.sheIs] sure to want to have sex with you..."),
								WATERING_HOLE_DOM_PARTNER_TAKEN_HOME) {
							@Override
							public void effects() {
								
								for(GameCharacter clubber : getClubbersPresent()) {
									if(!clubber.getHomeLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BOULEVARD)) {
										clubber.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_BOULEVARD, true);
									} else {
										clubber.returnToHome();
									}
									Main.game.getPlayer().setLocation(WorldType.DOMINION, clubber.getLocation(), false);
									Main.game.setRequestAutosave(false); // Autosaving when moving world here will cause the NPC to disappear when loaded
								}
								
							}
						};
						
					} else if(index==4) {
						return new Response("Refuse (gentle)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you're not interested in going back to [npc.her] place, but that you hope to see [npc.herHim] at the club another time."
										+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BAR_INVITE_HOME_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("Refuse (harsh)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Bluntly tell [npc.name] that you have no interest whatsoever in having sex with [npc.herHim]."
										+ "</br>[style.italicsBad(Removes this character from the game.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BAR_INVITE_HOME_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case BAR_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("Kiss", 
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Lean in against [npc.name] and start making out with [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Push away",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Push [npc.name] away and tell [npc.herHim] to keep [npc.her] [npc.lips] to [npc.herself]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case BAR_TALK:
					if(index==1) {
						// Continue conversation
						return new Response("Continue conversation",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Happily continue chatting with [npc.name]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_TALK_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(5));
							}
						};
					} else if(index==2) {
						// Show boredom
						return new Response("Stay silent",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Make a non-committal humming noise, before waiting for [npc.name] to continue."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_BAR_TALK_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
							}
						};
					}
					break;
				case DANCE:
					if(index==1) {
						// Dance
						return new Response("Dance",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Step out onto the dance floor and start dancing with [npc.name]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
					} else if(index==2) {
						// Step back
						return new Response("Refuse",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you have no interest in dancing with [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case DANCE_GROPE:
					if(index==1) {
						// Let them grope you
						return new Response("Submit",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Lean in against [npc.name] and let [npc.herHim] feel you up."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_GROPE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Push away",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] to get away from you."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_GROPE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case DANCE_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("Kiss", 
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Lean in against [npc.name] and start making out with [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Push away",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Push [npc.name] away and tell [npc.herHim] to keep [npc.her] [npc.lips] to [npc.herself]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_DANCE_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case INTRODUCTION:
					break;
				case SIT_DOWN_FLIRT:
					if(index==1) {
						// Flirt back
						return new Response("Flirt back",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Respond positively to [npc.namePos] flirtatious remarks, and flirt back with [npc.herHim]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FLIRT_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(10));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Show disdain",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Respond negatively to [npc.namePos] flirtatious remarks, and tell [npc.herHim] to stop."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FLIRT_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-10));
							}
						};
					}
					break;
				case SIT_DOWN_FOOTSIE:
					boolean bothBipeds = true;
					if(Main.game.getPlayer().isTaur() || getPartner().isTaur()) {
						bothBipeds = false;
					}
					// If both partners are bipeds, play footsie. If not, feeling up occurs instead.
					if(index==1) {
						// Enjoy
						return new Response(
								bothBipeds
									?"Play footsie"
									:"Submit",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(),
										bothBipeds
											?"Let [npc.namePos] [npc.foot] work all the way up to your groin, and start reciprocating [npc.her] flirtatious movements."
											:"Buck your body back against [npc.name] and let [npc.herHim] feel you up."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FOOTSIE_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(20));
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.PENIS, getPartner(), true);
								Main.game.getPlayer().setAreaKnownByCharacter(CoverableArea.VAGINA, getPartner(), true);
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Pull away",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(),
										bothBipeds
											?"Pull away from [npc.namePos] [npc.foot], before angrily telling [npc.herHim] to stop."
											:"Pull away from [npc.namePos] unwanted touch, before angrily telling [npc.herHim] to stop."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_FOOTSIE_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-20));
							}
						};
					}
					break;
				case SIT_DOWN_INVITE_HOME:
					if(index==1) {
						return new Response("Follow home",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Follow [npc.name] back to [npc.her] house, where [npc.sheIs] sure to want to have sex with you..."),
								WATERING_HOLE_DOM_PARTNER_TAKEN_HOME) {
							@Override
							public void effects() {
								
								for(GameCharacter clubber : getClubbersPresent()) {
									if(!clubber.getHomeLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BOULEVARD)) {
										clubber.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_BOULEVARD, true);
									} else {
										clubber.returnToHome();
									}
									Main.game.getPlayer().setLocation(WorldType.DOMINION, clubber.getLocation(), false);
									Main.game.setRequestAutosave(false); // Autosaving when moving world here will cause the NPC to disappear when loaded
								}
							}
						};
						
					} else if(index==4) {
						return new Response("Refuse (gentle)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you're not interested in going back to [npc.her] place, but that you hope to see [npc.herHim] at the club another time."
										+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "SIT_DOWN_INVITE_HOME_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("Refuse (harsh)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Bluntly tell [npc.name] that you have no interest whatsoever in having sex with [npc.herHim]."
										+ "</br>[style.italicsBad(Removes this character from the game.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "SIT_DOWN_INVITE_HOME_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case SIT_DOWN_KISS:
					if(index==1) {
						// Let them kiss you
						return new Response("Kiss",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Let [npc.name] start kissing you."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_KISS_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(15));
							}
						};
					} else if(index==2) {
						// Shut down
						return new Response("Push away",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Reject [npc.namePos] attempt to kiss you and push [npc.herHim] away."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_KISS_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-15));
							}
						};
					}
					break;
				case SIT_DOWN_SEX:
					if(index==1) {
						SexManagerDefault sm = new SMSitting(
								Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(SexPosition.SITTING);
							}
						};

						if(getPartner().isTaur()) { // Partner is a taur/arachnid:
							sm = new SexManagerDefault(
									SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public List<AbstractSexPosition> getAllowedSexPositions() {
									return Util.newArrayListOfValues(SexPosition.STANDING, SexPosition.SITTING);
								}
							};
						}
						
						return new ResponseSex("Sex (sub)",
								UtilText.parse(getClubbersPresent(), "Do as [npc.name] says and start having submissive sex with [npc.herHim]."),
								true, true,
								sm,
								null,
								null,
								WATERING_HOLE_SEATING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_START", getClubbersPresent()));
						
					} else if(index==4) {
						return new Response("Refuse (gentle)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you're not interested in having sex with [npc.herHim], but that you hope to see [npc.herHim] at the club another time."
										+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("Refuse (harsh)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Bluntly tell [npc.name] that you have no interest whatsoever in having sex with [npc.herHim]."
										+ "</br>[style.italicsBad(Removes this character from the game.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_SEX_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
					
				case SIT_DOWN_TALK:
					if(index==1) {
						// Continue conversation
						return new Response("Continue conversation",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Happily continue chatting with [npc.name]."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_TALK_ACCEPT", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(5));
							}
						};
					} else if(index==2) {
						// Show boredom
						return new Response("Stay silent",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Make a non-committal humming noise, before waiting for [npc.name] to continue."),
								WATERING_HOLE_DOM_PARTNER_REACT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SIT_DOWN_TALK_DECLINE", getClubbersPresent()));
								Main.game.getTextEndStringBuilder().append(incrementDominantPartnerAffection(-5));
							}
						};
					}
					break;
				case TOILETS:
					if(index==1) {
						return new ResponseSex("Stall sex", UtilText.parse(getClubbersPresent(), "Let [npc.name] fuck you in one of the toilet's stalls."),
								true, true,
								new SMStallSex(
										Util.newHashMapOfValues(new Value<>(getPartner(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								null,
								null,
								WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START", getClubbersPresent()));
						
					} else if(index==4) {
						return new Response("Refuse (gentle)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you're not interested in having sex with [npc.herHim], but that you hope to see [npc.herHim] at the club another time."
										+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START_REFUSE_GENTLE", NightlifeDistrict.getClubbersPresent()));
								saveClubbers();
							}
						};
						
					} else if(index==5) {
						return new Response("Refuse (harsh)",
								UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Bluntly tell [npc.name] that you have no interest whatsoever in having sex with [npc.herHim]."
										+ "</br>[style.italicsBad(Removes this character from the game.)]"),
								UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(
										UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_SEX_START_REFUSE_RUDE", NightlifeDistrict.getClubbersPresent()));
								NightlifeDistrict.removeClubbers();
							}
						};
						
					} else {
						return null;
					}
			}
			
			if(index==9) {
				return new Response("Say goodbye",
						UtilText.parse(getClubbersPresent(), "Tell [npc.name] that you've got to head off for a little while, but that you hope to see [npc.herHim] again."
								+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						WATERING_HOLE_SEATING_LOSE_COMPANY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_SAVE_CLUBBER", getClubbersPresent()));
						saveClubbers();
					}
				};
				
			} else if(index==10) {
				return new Response("Lose company",
						UtilText.parse(getClubbersPresent(), "Make up an excuse to get rid of [npc.name].</br>[style.italicsBad(Removes this character from the game.)]"),
						WATERING_HOLE_SEATING_LOSE_COMPANY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_LOSE_COMPANY", getClubbersPresent()));
						removeClubbers();
					}
				};
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_REACT = new DialogueNode("The Watering Hole", "", true, true) {
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return getClubberStatus(this.getSecondsPassed(), true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isEndConditionMet(0)) {
				return getEndResponse(index, 0);
			}
			
			if(index==1) {
				return new Response("Continue", UtilText.parse(getClubbersPresent(), "See what [npc.name] wants to do next..."), WATERING_HOLE_DOM_PARTNER) {
					@Override
					public void effects() {
						applyBehaviourEffects();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TAKEN_HOME = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getLabel() {
			return UtilText.parse(NightlifeDistrict.getClubbersPresent(), "[npc.NamePos] Apartment");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME", NightlifeDistrict.getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Sex", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Have submissive sex with [npc.name]."),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_SEX", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==4) {
				return new Response("Refuse",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you don't really want to go back to [npc.her] place to have sex..."
								+ "</br>[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole",
								"WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_CHANGE_MIND", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==5) {
				return new Response("Angrily refuse",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that this isn't at all the sort of thing you were thinking of when accepting [npc.her] invitation to come back to [npc.her] place!"
								+ "</br>[style.italicsBad(Removes this character from the game.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole",
								"WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_CHANGE_MIND_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getLabel() {
			return UtilText.parse(NightlifeDistrict.getClubbersPresent(), "[npc.NamePos] Apartment");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("See again",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you hope to see [npc.herHim] again.</br>"
								+ "[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==2) {
				return new Response("Hope not (gentle)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Make a non-committal response, secretly hoping that you won't see [npc.name] again.</br>[style.italicsBad(Removes this character from the game.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==3) {
				return new Response("Hope not (harsh)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Crudely tell [npc.name] that you were only interested in fucking [npc.herHim].</br>[style.italicsBad(Removes this character from the game.)]"),
						UTIL_NEUTRAL_DIALOGUE_NO_TEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TAKEN_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getClubbersPresent(), "[npc.Name] has had enough for now...");
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("See again",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Tell [npc.name] that you hope to see [npc.herHim] again.</br>"
								+ "[style.italicsGood(Saves this character, who can then be encountered in the club again.)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
					}
				};
				
			} else if(index==2) {
				return new Response("Hope not (gentle)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Make a non-committal response, secretly hoping that you won't see [npc.name] again.</br>[style.italicsBad(Removes this character from the game.)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
				
			} else if(index==3) {
				return new Response("Hope not (harsh)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Crudely tell [npc.name] that you were only interested in fucking [npc.herHim].</br>[style.italicsBad(Removes this character from the game.)]"),
						PlaceType.WATERING_HOLE_TOILETS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "WATERING_HOLE_DOM_PARTNER_TOILETS_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode UTIL_NEUTRAL_DIALOGUE_NO_TEXT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue().getResponse(responseTab, index);
		}
	};
	
}
