package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.?
 * @version 0.2.11
 * @author Innoxia
 */
public class AlleywayDemonDialogue {

	private static NPC getDemon() {
		return Main.game.getActiveNPC();
	}
	
	private static boolean isWantsToFight() {
		return getDemon().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_ONE_FRIENDLY.getMinimumValue();
	}
	
	private static boolean isAffectionHighEnoughToInviteHome() {
		return getDemon().getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue();
	}
	
	private static void applyPregnancyReactions() {
		if(getDemon().isVisiblyPregnant()){
			getDemon().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getDemon(), true);
		}
	}
	
	private static String getStatus() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'><i>");
		AffectionLevel al = getDemon().getAffectionLevel(Main.game.getPlayer());
		switch(al) {
			case NEGATIVE_FIVE_LOATHE:
			case NEGATIVE_FOUR_HATE:
			case NEGATIVE_THREE_STRONG_DISLIKE:
			case NEGATIVE_TWO_DISLIKE:
			case NEGATIVE_ONE_ANNOYED:
			case ZERO_NEUTRAL:
				break;
			case POSITIVE_ONE_FRIENDLY:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] is acting in a <i style='color:"+al.getColour().toWebHexString()+";'>friendly, flirtatious</i> manner towards you.");
				} else {
					sb.append("[npc.Name] is acting in a <i style='color:"+al.getColour().toWebHexString()+";'>friendly</i> manner towards you.");
				}
				break;
			case POSITIVE_TWO_LIKE:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>likes you</i>, and sees you as more than just a friend.");
				} else {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>likes you</i>, and sees you as a close friend.");
				}
				break;
			case POSITIVE_THREE_CARING:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>cares about you a lot</i>, and is deeply attracted towards you.");
				} else {
					sb.append("[npc.Name] quite clearly <i style='color:"+al.getColour().toWebHexString()+";'>cares about you a lot</i>, and considers you to be [npc.her] best friend.");
				}
				break;
			case POSITIVE_FOUR_LOVE:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("You can tell from the way that [npc.she] looks at you that [npc.name] <i style='color:"+al.getColour().toWebHexString()+";'>loves you</i>.");
				} else {
					sb.append("You can tell that [npc.name] <i style='color:"+al.getColour().toWebHexString()+";'>loves you</i> in a purely platonic manner.");
				}
				break;
			case POSITIVE_FIVE_WORSHIP:
				if(getDemon().isAttractedTo(Main.game.getPlayer())) {
					sb.append("[npc.Name] <i style='color:"+al.getColour().toWebHexString()+";'>worships you</i>, and is head-over-heels in love with you.");
				} else {
					sb.append("[npc.Name] <i style='color:"+al.getColour().toWebHexString()+";'>worships you</i>, and would do almost anything you asked of [npc.herHim].");
				}
				break;
		}
		sb.append("</i></p>");
		
		return UtilText.parse(getDemon(), sb.toString());
	}
	
	public static final DialogueNode ALLEY_DEMON_ATTACK = new DialogueNode("", "You've ended up walking right into a trap!", true) {
		
		@Override
		public String getLabel(){
			return "An obvious trap";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean pregnancyReaction = false;
			
			if(getDemon().getLastTimeEncountered() != -1) {
				
				if(isWantsToFight()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_REPEAT_INTRO", getDemon()));
					
					if(getDemon().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getDemon().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_REPEAT_PREGNANCY_REVEAL", getDemon()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_REPEAT_STILL_PREGNANT", getDemon()));
						}
					}

					if(Main.game.getPlayer().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!Main.game.getPlayer().isCharacterReactedToPregnancy(getDemon())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_REPEAT_PLAYER_PREGNANCY", getDemon()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_REPEAT_CONTINUED_PLAYER_PREGNANCY", getDemon()));
						}
					}
					
					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_REPEAT", getDemon()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_REPEAT_END", getDemon()));
					
				
				} else { // The mugger doesn't want to attack the player:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL_INTRO", getDemon()));
					
					if(getDemon().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!getDemon().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL_PREGNANCY_REVEAL", getDemon()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL_STILL_PREGNANT", getDemon()));
						}
					}

					if(Main.game.getPlayer().isVisiblyPregnant()) {
						pregnancyReaction = true;
						
						if(!Main.game.getPlayer().isCharacterReactedToPregnancy(getDemon())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL_PLAYER_PREGNANCY", getDemon()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL_CONTINUED_PLAYER_PREGNANCY", getDemon()));
						}
					}
					
					if(!pregnancyReaction) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL", getDemon()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL_END", getDemon()));

					UtilText.nodeContentSB.append(getStatus());
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_INTRO", getDemon()));
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK", getDemon()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("Fight", "This demon doesn't look so tough! You're pretty sure you can beat [npc.herHim].", getDemon()){
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"[npc.Name] is't interested in your money! You'll have to either fight or submit to [npc.herHim]!", null);
						
					} else if(Main.game.getPlayer().getMoney()<250) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
						
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"Offer to pay [npc.name] 250 flames to leave you alone.", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PAY_OFF", getDemon()));
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-250));
							}
						};
					}
					
				} else if (index == 3) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Kneel",
								"The [npc.race] is ready to use you as [npc.her] little fuck-toy...",
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getActiveNPC()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null),
								AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_OFFER_BODY", getDemon())){
							@Override
							public void effects() {
								Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getActiveNPC().setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
								Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
								Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
								Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
							}
						};
						
					} else {
						return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
					}
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Talk", "Talk to [npc.name] for a while in order to get to know [npc.herHim] a little better.", ALLEY_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 10));
							
							if(isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<250) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"You don't have enough money to offer [npc.name] any.", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"Offer [npc.name] some money to help [npc.herHim] buy food and clothing.", ALLEY_PEACEFUL_OFFER_MONEY) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-250));
								Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 15));

								if(isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
								}
							}
						};
					}
					
				} else if (index == 3) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (dom)", "Take the dominant role and have sex with [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getDemon()),
								null,
								null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL_SEX_AS_DOM", getDemon())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (dom)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} else if (index == 4) {
					if(getDemon().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (sub)", "Offer your body to [npc.name].",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getDemon()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
								AFTER_SEX_PEACEFUL, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_ATTACK_PEACEFUL_SEX_AS_SUB", getDemon())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (sub)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} if (index == 5) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION) || !isAffectionHighEnoughToInviteHome()) {
						return new Response("Offer room",
								"You feel as though it would be best to spend some more time getting to know [npc.name] before inviting [npc.herHim] back to Lilaya's mansion...<br/>"
								+ "[style.italics(Requires [npc.name] to have at least "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()+" affection towards you.)]",
								null);
						
					} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						return new Response("Offer room",
								"You'll need to get Lilaya's permission before inviting [npc.name] back to her mansion...",
								null);
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						return new Response("Offer room",
								"You don't have a suitable room prepared for [npc.name] to move in to. Upgrade one of the empty rooms in Lilaya's house to a 'Guest Room' first.",
								null);
						
					}else {
						return new Response("Offer room", "Ask [npc.name] if [npc.she] would like a room in Lilaya's mansion.", ALLEY_PEACEFUL_OFFER_ROOM) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								getDemon().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
							}
						};
					}
					
				} else if (index==10) {
					return new Response("Attack",
							"Betray [npc.namePos] trust and attack [npc.herHim]!<br/>"
									+ "[style.italicsBad(This will devastate [npc.name], causing [npc.herHim] to leave Dominion in despair. (Removing [npc.herHim] from the game.))]",
							ALLEY_PEACEFUL_ATTACK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), -200));
							getDemon().addFlag(NPCFlagValue.genericNPCBetrayedByPlayer);
							Main.game.getPlayer().incrementKarma(-50); // Why would you make friends with them and then attack them? ;_;
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you're in a rush to be somewhere else, before continuing on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode ALLEY_PEACEFUL_TALK = new DialogueNode("Talk", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_TALK", getDemon()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_CAN_INVITE_HOME", getDemon()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getDemon()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ALLEY_PEACEFUL_OFFER_MONEY = new DialogueNode("Offer money", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_OFFER_MONEY", getDemon()));

			UtilText.nodeContentSB.append(getStatus());
			
			if(isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_CAN_INVITE_HOME", getDemon()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getDemon()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go and buy food.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ALLEY_PEACEFUL_OFFER_ROOM = new DialogueNode("Offer room", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_OFFER_ROOM", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Show to room", "Take [npc.name] to [npc.her] new room.", ALLEY_PEACEFUL_OFFER_ROOM_BACK_HOME) {
					@Override
					public void effects() {
						Cell c = OccupancyUtil.getFreeRoomForOccupant();
						getDemon().setLocation(c.getType(), c.getLocation(), true);
						Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
						Main.game.getPlayer().addFriendlyOccupant(getDemon());
						Main.game.getTextEndStringBuilder().append(getDemon().incrementAffection(Main.game.getPlayer(), 50));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ALLEY_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNode("New Room", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_OFFER_ROOM_BACK_HOME", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Give [npc.name] some time to get settled in [npc.her] new room. You can come back at any time to talk with [npc.herHim].", LilayaHomeGeneric.CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(Main.game.getPlayer().getWorldLocation(), PlaceType.LILAYA_HOME_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ALLEY_PEACEFUL_ATTACK = new DialogueNode("Attack", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "ALLEY_PEACEFUL_ATTACK", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Start fighting [npc.name]!", getDemon());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_PEACEFUL = new DialogueNode("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(getDemon())>=getDemon().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL", getDemon());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_PEACEFUL_NO_ORGASM", getDemon());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if((getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getDemon());
				
			} else {
				if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_BETRAYED", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getDemon());
				}
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if((getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Continue", "Leave [npc.name] be and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
								Main.game.banishNPC(getDemon());
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
							null,
							null),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX", getDemon()));
					
				} else if (index == 3) {
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getDemon()));
					
				} else if (index == 4) {
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getDemon()));
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"Seeing the desperate, whining form of the horny [npc.race] is proving to be too much for you to bear."
								+ " Perhaps you could cheer [npc.herHim] up by <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>submitting to [npc.herHim]</b> and letting [npc.herHim] use your body?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getDemon()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getDemon())) {
						@Override
						public void effects() {
							getDemon().setPenisType(PenisType.DEMON_COMMON);
							getDemon().setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
							getDemon().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
							getDemon().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
							getDemon().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
						}
					};
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 7) {
					if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
						return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
						
					} else {
						return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
							@Override
							public void effects() {
								getDemon().setPlayerKnowsName(true);
								Main.game.getTextEndStringBuilder().append(getDemon().setAffection(Main.game.getPlayer(), 10));
							}
						};
					}
					
				} else if (index == 8 && getDemon().isAbleToSelfTransform()) {
					return new Response("Transform [npc.herHim]",
							"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getDemon());
						}
					};
					
				} else if (index == 9 && getDemon().isAbleToSelfTransform()) {
						return new Response("Quick transformations",
								"If all [npc.she] wants is sex, then you're more than happy to oblige. Besides, if [npc.sheIs] able to transform [npc.herself], you have a few ideas in mind..."
										+ "(You'll return to these options once finished transforming [npc.herHim].)",
								AFTER_COMBAT_TRANSFORMATIONS_FEMININITY);
					
				} else if (index == 10 && !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getDemon()));
							Main.game.banishNPC(getDemon());
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
								Main.game.banishNPC(getDemon());
							}
						}
					};
					
				} else if (index == 2) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
							null,
							null),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE", getDemon()));
					
				} else if (index == 3) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Gentle Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getDemon()));
					
				} else if (index == 4) {
					if(!Main.game.isNonConEnabled()) {
						return new Response("Rough Sex", "[npc.Name] has no interest in having sex with you!", null);
					}
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getDemon()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_DOM_ROUGH), AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getDemon()));
					
				} else if (index == 5) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 7) {
					if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
						return new Response("Talk", "After betraying [npc.namePos] trust, [npc.she] will never want to talk to you again.", null);
						
					} else {
						return new Response("Talk", "Talk to [npc.name] and ask [npc.herHim] why [npc.she] attacked you.", AFTER_COMBAT_VICTORY_TALK){
							@Override
							public void effects() {
								getDemon().setPlayerKnowsName(true);
								Main.game.getTextEndStringBuilder().append(getDemon().setAffection(Main.game.getPlayer(), 10));
							}
						};
					}
					
				} else if (index == 8 && getDemon().isAbleToSelfTransform()) {
					return new Response("Transform [npc.herHim]",
							"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getDemon());
						}
					};
					
				} else if (index == 9 && getDemon().isAbleToSelfTransform()) {
						return new Response("Quick transformations",
								"If all [npc.she] wants is sex, then you're more than happy to oblige. Besides, if [npc.sheIs] able to transform [npc.herself], you have a few ideas in mind..."
										+ "(You'll return to these options once finished transforming [npc.herHim].)",
								AFTER_COMBAT_TRANSFORMATIONS_FEMININITY);
					
				} else if (index == 10 && !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_BANISH_NPC", getDemon()));
							Main.game.banishNPC(getDemon());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY_TALK = new DialogueNode("Talk", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_VICTORY_TALK", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"Let [npc.name] go.",
						Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATIONS_FEMININITY = new DialogueNode("Transformations", "", true, true) {


		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			String femaleName = getDemon().getSubspecies().getSingularFemaleName(getDemon());
			String maleName = getDemon().getSubspecies().getSingularMaleName(getDemon());
			
			if (index == 1) {
				return new Response("[style.colourMasculineStrong(Very masculine)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(maleName)+" "+maleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_VERY_MASCULINE", getDemon()));
						Main.game.getActiveNPC().setFemininity(10);
					}
				};
				
			} else if (index == 2) {
				return new Response("[style.colourMasculine(Masculine)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(maleName)+" "+maleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_MASCULINE", getDemon()));
						Main.game.getActiveNPC().setFemininity(30);
					}
				};
				
			} else if (index == 3) {
				return new Response("[style.colourAndrogynous(Androgynous)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(femaleName)+" "+femaleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_ANDROGYNOUS", getDemon()));
						Main.game.getActiveNPC().setFemininity(50);
					}
				};
				
			} else if (index == 4) {
				return new Response("[style.colourFeminine(Feminine)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(femaleName)+" "+femaleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_FEMININE", getDemon()));
						Main.game.getActiveNPC().setFemininity(70);
					}
				};
				
			} else if (index == 5) {
				return new Response("[style.colourFeminineStrong(Very feminine)]",
						"Tell [npc.name] that you want [npc.herHim] to shift [npc.her] femininity so that [npc.sheIs] "+UtilText.generateSingularDeterminer(femaleName)+" "+femaleName+".",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_FEMININITY_VERY_FEMININE", getDemon()));
						Main.game.getActiveNPC().setFemininity(90);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATIONS_PENIS = new DialogueNode("Transformations", "", true, true) {
		

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("No cock",
						"Tell the [npc.race] that you don't want [npc.herHim] to have a cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(PenisType.NONE);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_NONE", getDemon()));
					}
				};
				
			} else if (index == 2) {
				return new Response("Tiny cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a tiny little [unit.size(3)] cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getPenisType());
						Main.game.getActiveNPC().setPenisGirth(PenisGirth.ONE_THIN);
						Main.game.getActiveNPC().setPenisSize(3);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.ONE_TRICKLE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.ONE_TRICKLE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_TINY", getDemon()));
					}
				};
				
			} else if (index == 3) {
				return new Response("Average-sized cock",
						"Tell the [npc.race] that you want [npc.herHim] to have an average, [unit.size(15)] cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getPenisType());
						Main.game.getActiveNPC().setPenisGirth(PenisGirth.TWO_AVERAGE);
						Main.game.getActiveNPC().setPenisSize(15);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.THREE_AVERAGE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.THREE_AVERAGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_AVERAGE", getDemon()));
					}
				};
				
			} if (index == 4) {
				return new Response("Large cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a large, [unit.size(30)] cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getPenisType());
						Main.game.getActiveNPC().setPenisGirth(PenisGirth.THREE_THICK);
						Main.game.getActiveNPC().setPenisSize(30);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_LARGE", getDemon()));
					}
				};
				
			} if (index == 5) {
				return new Response("Enormous cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a massive [unit.size(50)] cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getPenisType());
						Main.game.getActiveNPC().setPenisGirth(PenisGirth.FOUR_FAT);
						Main.game.getActiveNPC().setPenisSize(50);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_PENIS_HUGE", getDemon()));
					}
				};
				
			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATIONS_VAGINA = new DialogueNode("Transformations", "", true, true) {


		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_VAGINA", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Vagina",
						"Tell the [npc.race] that you want [npc.herHim] to have a pussy.",
						AFTER_COMBAT_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setVaginaType(RacialBody.valueOfRace(getDemon().getBody().getRaceFromPartWeighting()).getVaginaType());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_VAGINA_ENABLED", getDemon()));
					}
				};
				
			} else if (index == 2) {
				return new Response("No Vagina",
						"Tell the [npc.race] that [npc.she] won't be needing a pussy.",
						AFTER_COMBAT_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setVaginaType(VaginaType.NONE);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_VAGINA_DISABLED", getDemon()));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATIONS_BREASTS = new DialogueNode("Transformations", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS", getDemon());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Flat chest",
						"Tell [npc.name] that [npc.she] should have no breasts.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_FLAT", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.FLAT.getMeasurement());
					}
				};
				
			} else if (index == 2) {
				return new Response("AA-cup",
						"Tell [npc.name] to make [npc.her] breasts tiny little AA-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_AA", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.AA.getMeasurement());
					}
				};
				
			} else if (index == 3) {
				return new Response("C-cup",
						"Tell [npc.name] to make [npc.her] breasts C-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_C", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.C.getMeasurement());
					}
				};
				
			} else if (index == 4) {
				return new Response("E-cup",
						"Tell [npc.name] to make [npc.her] breasts E-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_E", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.E.getMeasurement());
					}
				};
				
			} else if (index == 5) {
				return new Response("H-cup",
						"Tell [npc.name] to make [npc.her] breasts H-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_H", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.H.getMeasurement());
					}
				};
				
			} else if (index == 6) {
				return new Response("N-cup",
						"Tell [npc.name] to make [npc.her] breasts N-cups.",
						AFTER_COMBAT_TRANSFORMATIONS_FINISHED){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_TRANSFORMATIONS_BREASTS_N", getDemon()));
						Main.game.getActiveNPC().setBreastSize(CupSize.N.getMeasurement());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATIONS_FINISHED = new DialogueNode("Transformations", "", true, true) {
		

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		

		@Override
		public String getDescription() {
			return "You have been defeated by " + Main.game.getActiveNPC().getName("the") + "!";
		}

		@Override
		public String getContent() {

			if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_BETRAYED", getDemon());
			}
				
			if(getDemon().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_NO_TF_ATTRACTED", getDemon());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_NO_TF_NOT_ATTRACTED", getDemon());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							Main.game.banishNPC(getDemon());
						}
					};
				}
				return null;
			}
			
			if(getDemon().isAttractedTo(Main.game.getPlayer())) {
				
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getDemon()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_SEX", getDemon())){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getActiveNPC().setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
							Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
							Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
							Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getDemon()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getDemon())){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getActiveNPC().setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
							Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
							Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
							Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
						}
					};
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getDemon()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getDemon())){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getActiveNPC().setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
							Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
							Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
							Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
						}
					};
					
				}
				
			} else {
				
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				}
			
			}

			return null;
			
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if((getDemon().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled())
					&& !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				if(Sex.getNumberOfOrgasms(getDemon()) >= getDemon().getOrgasmsBeforeSatisfied()) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getDemon());
				}
				
			} else {
				if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_RAPE_BETRAYED", getDemon());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_VICTORY_RAPE", getDemon());
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getDemon());
						}
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getDemon(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && !getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getDemon());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Collapse", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayDemonAttack", "AFTER_SEX_DEFEAT", getDemon());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_DEFEAT) {
					@Override
					public void effects() {
						if(getDemon().hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer)) {
							Main.game.banishNPC(getDemon());
						}
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
