package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.4
 * @author Innoxia
 */
public class ClothingEmporium {
	
	public static final String NYAN_HIDING_DAY_ID = "nyanHidingDay";
	
	public static String incrementAffection(GameCharacter character, float increment, float minimumLimit, float maximumLimit) {
		float currentAffection = character.getAffection(Main.game.getPlayer());
		
		
		if(increment>=0) {
			increment = Math.min(increment, Math.max(0, maximumLimit-currentAffection));
		} else {
			increment = Math.max(increment, Math.min(0, minimumLimit-currentAffection));//TODO test
		}
		
		if(increment==0 || (increment>0 && currentAffection>=maximumLimit)) {
			return "<p style='text-align:center'>"
						+ UtilText.parse(character, "[style.italicsDisabled([npc.Name] doesn't gain any additional affection towards you from this action...)]")
						+ "<br/>"
						+ AffectionLevel.getDescription(character, Main.game.getPlayer(), true)
					+ "</p>";
		}
		if(increment<0 && currentAffection<=minimumLimit) {
			return "<p style='text-align:center'>"
						+ UtilText.parse(character, "[style.italicsDisabled([npc.Name] doesn't lose any affection towards you from this action...)]")
						+ "<br/>"
						+ AffectionLevel.getDescription(character, Main.game.getPlayer(), true)
					+ "</p>";
		}
		
		return character.incrementAffection(Main.game.getPlayer(), increment);
	}
	
	private static void applyRepeatMeetingReactionUpdates() {
		if(getNyan().isVisiblyPregnant()) {
			getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanApologised, true);
		}
	}
	
	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Nyan's Clothing Emporium (Exterior)", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXTERIOR");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(Main.game.getHourOfDay()<9 || Main.game.getHourOfDay()>=20) {
						return new Response("Enter", "Nyan's Clothing Emporium is currently closed. You'll have to come back later...", null);
						
					} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.nyanIntroduced)) {
						return new Response("Enter", "Step inside Nyan's Clothing Emporium.", SHOP_CLOTHING_REPEAT);
						
					} else {
						return new Response("Enter", "Step inside Nyan's Clothing Emporium.", SHOP_CLOTHING);
					}
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Stockroom", "Nyan has shown you into the stockroom.", SHOP_CLOTHING_STOCK_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.nyanIntroduced);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING_STOCK_ROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_STOCK_ROOM");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING_REPEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getAuthor() {
			if(getNyan().isPregnant() && !getNyan().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				return "Duner";
			} else {
				return "Innoxia";
			}
		}
		@Override
		public String getContent() {
			if(!Main.game.getCharactersPresent().contains(getNyan())) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_REPEAT_NO_NYAN");
				
			} else {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)) {
					if(Main.game.getDialogueFlags().getSavedLong(NYAN_HIDING_DAY_ID)<Main.game.getDayNumber()) {
						return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_NEXT_DAY");
					} else {
						return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING");
					}
				} else if(getNyan().isVisiblyPregnant() && !getNyan().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT_PREGNANT");
				} else {
					return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT");
				}
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)
					|| !Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)
					|| !Main.game.getCharactersPresent().contains(getNyan())) {
				return null;
			}

			if(index==0) {
				return "Trade";
			} else if(index==1) {
				return "Talk";
			} else {
				return null;
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHiding)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().getSavedLong(NYAN_HIDING_DAY_ID)<Main.game.getDayNumber()) {
						return new Response("Follow", "Follow Nyan into her stockroom again and listen to what she has to say.", NYAN_HIDING_END) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHiding, false);
							}
						};
						
					} else {
						return new Response("Leave", "With Nyan in hiding and no shop assistant to help, there's nothing for you to do but leave.", EXTERIOR) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_LEAVE"));
							}
						};
					}
				}
				
				return null;
			}
			
			if(responseTab==1) {
				float currentAffection = getNyan().getAffection(Main.game.getPlayer());
				
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanDating)) {
					if(index==1) {
						return new Response("Girlfriend",
								"Tell Nyan that you've changed your mind and would like her to be your girlfriend.",
								NYAN_HIDING_END_SHOP) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanDating, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_CLOTHING_REPEAT_GIRLFRIEND"));
								Main.game.setResponseTab(1);
							}
						};
					}
					
				} else {
					if(index==1) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo)) {
							return new Response("Talk", "You've already had a chat with Nyan today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Talk", "Talk with Nyan for a while and get to know her a little better.", ROMANCE_TALK) {
								@Override
								public void effects() {
									UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_BASE"));
									
									List<String> topics = Util.newArrayListOfValues(
											"NYAN_NOVELS",
											"NYAN_WORK",
											"NYAN_HOBBIES",
											"NYAN_HOME");
									long lowestValue = 1_000_000;
									for(String topic : topics) {
										if(Main.game.getDialogueFlags().getSavedLong(topic)<lowestValue) {
											lowestValue = Main.game.getDialogueFlags().getSavedLong(topic);
										}
									}
									long thanksJava = lowestValue;
									topics.removeIf(s -> Main.game.getDialogueFlags().getSavedLong(s)>thanksJava);
									String topicSelected = Util.randomItemFrom(topics);
									if(Main.game.getDialogueFlags().getSavedLong("NYAN_NOVELS")==-1) { // Make sure that novels is first topic
										topicSelected = "NYAN_NOVELS";
									}
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_"+topicSelected));
									Main.game.getDialogueFlags().incrementSavedLong(topicSelected, 1);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_FINAL"));
									
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanTalkedTo, true);
								
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==2) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanComplimented)) {
							return new Response("Compliment", "You've already complimented Nyan today. You can repeat this action tomorrow.", null);
							
						} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo) && currentAffection<50) {
							return new Response("Compliment",
									"You can tell that attempting to give Nyan a compliment without first spending some time talking with her would end in disaster."
										+ "<br/>[style.italicsMinorBad(You need to talk to Nyan before complimenting her!)]",
									null);
							
						} else {
							return new Response("Compliment", "Compliment Nyan's appearance and abilities.", ROMANCE_COMPLIMENT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanComplimented, true);
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==3) {
						int requiredAffection = 40;
						if(currentAffection<requiredAffection) {
							return new Response(
									"Flirt",
									"You can tell that attempting to flirt with Nyan would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ "<br/>[style.italicsMinorBad(Requires Nyan's affection to be at least "+requiredAffection+", and is currently "+currentAffection+".)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFlirtedWith)) {
							return new Response("Flirt", "You've already flirted with Nyan today. You can repeat this action tomorrow.", null);
							
						} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo) && currentAffection<50) {
							return new Response("Flirt",
									"You can tell that attempting to flirt with Nyan without first spending some time talking with her would end in disaster."
										+ "<br/>[style.italicsMinorBad(You need to talk to Nyan before flirting with her!)]",
									null);
							
						} else {
							return new Response("Flirt", "Flirt with Nyan a little.", ROMANCE_FLIRT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 3, 30, 50));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanFlirtedWith, true);
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
						
					} else if(index==6) {
						int requiredAffection = 50;
						if(currentAffection<requiredAffection) {
							return new Response(
									"Walk",
									"You don't think that Nyan is ready to go for a walk with you..."
										+ "<br/>[style.italicsMinorBad(Requires Nyan's affection to be at least "+requiredAffection+", and is currently "+currentAffection+".)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWalked)) {
							return new Response("Walk", "You've already gone for a walk with Nyan today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Walk", "Ask Nyan if she'd like to go for a walk with you.", ROMANCE_WALK) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
					}
					
					// Requires first kiss:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFirstKissed)) {
						if(index==4) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanHeadPatted)) {
								return new Response("Head pat", "You've already patted Nyan on the head today. You can repeat this action tomorrow.", null);
								
							} else {
								return new Response("Head pat", "Pat Nyan on the head and tell her she's a good girl.", ROMANCE_HEAD_PAT) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHeadPatted, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
							
						} else if(index==5) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanKissed)) {
								return new Response("Kiss", "You've already kissed Nyan today. You can repeat this action tomorrow.", null);
								
							} else {
								return new Response("Kiss", "You can tell from the way Nyan is looking at you that she wants to be kissed again. Lean forwards and give her what she wants.", ROMANCE_KISS) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanKissed, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}

						if(index==7) {
							int requiredAffection = 60;
							if(currentAffection<requiredAffection) {
								return new Response(
										"Restaurant date",
										"Considering Nyan's nervousness, you need to be sure that she'll be able to handle the prospect of going out on a public date with you..."
											+ "<br/>[style.italicsMinorBad(Requires Nyan's affection to be at least "+requiredAffection+", and is currently "+currentAffection+".)]",
										null);
								
							} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateRequested)) {
								if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY) {
									return new Response("Restaurant date",
											"Ask Nyan if she'd like to go out with you on a date to the restaurant, 'The Oaken Glade'."
												+ "<br/>[style.italicsMinorBad(This action cannot be performed on Fridays or Saturdays!)]",
											null);
									
								} else {
									return new Response("Restaurant date",
											"Ask Nyan if she'd like to go out with you on a date to the restaurant, 'The Oaken Glade'.",
											ROMANCE_DATE_REQUESTED) {
										@Override
										public void effects() {
											applyRepeatMeetingReactionUpdates();
										}
									};
								}
							}
						}
					}
					
					// Requires affection of over 60, which can only be achieved after first date (added flag check just to be sure):
					if(currentAffection>60 || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted)) {
						if(index==7) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanMakeOut)) {
								return new Response("Make Out", "You've already made out with Nyan today. You can repeat this action tomorrow.", null);
								
							} else {
								return new Response("Make Out", "You can tell that Nyan is desperate for some intense physical contact with you. Lean forwards and passionately start making out with her.", ROMANCE_MAKE_OUT) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 60, 100));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanMakeOut, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}
					}
					
					if(index==8) {
						if(getNyan().isVisiblyPregnant()) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTummyRubbed)) {
								return new Response("Rub tummy", "You've already spent time rubbing Nyan's tummy today. You can repeat this action tomorrow.", null);
								
							} else {
								return new Response("Rub tummy",
										"Rub Nyan's pregnant tummy and ask her how she's handling her pregnancy.",
										ROMANCE_RUB_TUMMY) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanTummyRubbed, true);
										applyRepeatMeetingReactionUpdates();
									}
								};
							}
						}
					}
					
					if(index==10) {
						int requiredAffection = 40;
						if(currentAffection<requiredAffection) {
							return new Response(
									"Gift",
									"Receiving a gift might be too much for the delicate cat-girl to handle right now..."
										+ "<br/>[style.italicsMinorBad(Requires Nyan's affection to be at least "+requiredAffection+", and is currently "+currentAffection+".)]",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanGift)) { // This is set in Nyan's getGiftReaction() method
							return new Response("Gift", "You've already given Nyan a gift today. You can repeat this action tomorrow.", null);
						} else {
							return new Response("Gift", "Give Nyan a gift (opens gift selection screen).", ROMANCE_GIFT) {
								@Override
								public DialogueNode getNextDialogue() {
									return GiftDialogue.getGiftDialogue(getNyan(), ROMANCE_GIFT, 1);
								}
								@Override
								public void effects() {
									applyRepeatMeetingReactionUpdates();
								}
							};
						}
					}
				}
				
				if(index == 0) {
					return new Response("Leave", "Tell Nyan that you've got to get going.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.setResponseTab(0);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
							applyRepeatMeetingReactionUpdates();
						}
					};
				}
				
				return null;
				
			} else if(responseTab==0) {
				String descriptionStart = "Ask her ";
				if(!Main.game.getCharactersPresent().contains(getNyan())) {
					descriptionStart = "Take a look at ";
				}
				
				if (index == 1) {
					return new ResponseTrade("Female Clothing", descriptionStart+"what female clothing is available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseTrade("Female Lingerie", descriptionStart+"what female lingerie is available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleUnderwear()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 3) {
					return new ResponseTrade("Female Accessories", descriptionStart+"what female accessories are available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonFemaleAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 6) {
					return new ResponseTrade("Male Clothing", descriptionStart+"what male clothing is available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 7) {
					return new ResponseTrade("Male Underwear", descriptionStart+"what male underwear is available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleLingerie()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 8) {
					return new ResponseTrade("Male Accessories", descriptionStart+"what male accessories are is available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonMaleAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 11) {
					return new ResponseTrade("Unisex Clothing", descriptionStart+"what unisex clothing is available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousClothing()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 12) {
					return new ResponseTrade("Unisex Underwear", descriptionStart+"what unisex underwear is available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousLingerie()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 13) {
					return new ResponseTrade("Unisex Accessories", descriptionStart+"what unisex accessories are is available.", getNyan()){
						@Override
						public void effects() {
							getNyan().clearNonEquippedInventory(false);
	
							for (AbstractClothing c : getNyan().getCommonAndrogynousAccessories()) {
								if(getNyan().isInventoryFull()) {
									break;
								}
								getNyan().addClothing(c, false);
							}
						}
					};
					
				} else if (index == 5) {
					if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
						return new ResponseTrade("Enchanted Clothing", descriptionStart+" what special items of clothing are now in stock.", getNyan()){
							@Override
							public void effects() {
								getNyan().clearNonEquippedInventory(false);
		
								for (AbstractClothing c : getNyan().getSpecials()) {
									if(getNyan().isInventoryFull()) {
										break;
									}
									getNyan().addClothing(c, false);
								}
							}
						};
						
					} else if(!Main.game.getPlayer().hasQuest(QuestLine.RELATIONSHIP_NYAN_HELP)) {
						return new Response("Enchanted Clothing",
								"Ask Nyan if she stocks enchanted clothing."
										+ "<br/>[style.italicsQuestRomance(This will start Nyan's romance quest!)]",
								SHOP_ENCHANTED_CLOTHING) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.RELATIONSHIP_NYAN_HELP));
							}
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
						};
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN) {
						return new Response("Report back", "Tell Nyan that you've dealt with the suppliers.", SHOP_REPORT_BACK) {
							@Override
							public void effects() {
								getNyan().setSellModifier(1.25f);
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(5000));
								Main.game.getTextEndStringBuilder().append(getNyan().setAffection(Main.game.getPlayer(), 30));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.SIDE_UTIL_COMPLETE));
							}
						};
						
					} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_1_STOCK_ISSUES)) {
						return new Response("Report back", "Report back to Nyan once you've dealt with the suppliers.", null);
						
					} else {
						return new Response("Offer help", "Tell Nyan that you'll help her with her supplier problem.", SHOP_OFFER_HELP) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(getNyan().setAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP));
							}
						};
					}
					 
				} else if (index == 0) {
					return new Response("Leave", "Tell Nyan that you've got to get going.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
						}
					};
				}
				
				return null;
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SHOP_ENCHANTED_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_ENCHANTED_CLOTHING");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_OFFER_HELP");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_REPORT_BACK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "SHOP_REPORT_BACK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Exit the store.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT_EMBARRASSED"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanHiding, true);
						Main.game.getDialogueFlags().setSavedLong(NYAN_HIDING_DAY_ID, Main.game.getDayNumber());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NYAN_HIDING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Girlfriend",
						"Tell Nyan that you'd like her to be your girlfriend.",
						NYAN_HIDING_END_SHOP) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanDating, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END_GIRLFRIEND"));
						Main.game.setResponseTab(1);
					}
				};
				
			} else if(index==2) {
				return new Response("Decline",
						"Tell Nyan that you're not able to commit to having a girlfriend at the moment."
							+ "<br/>[style.italicsMinorGood(You will be able to change your mind and ask Nyan to be your girlfriend at any time.)]",
							NYAN_HIDING_END_SHOP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_HIDING_END_DECLINE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode NYAN_HIDING_END_SHOP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_TALK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 55*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_COMPLIMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_FLIRT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_HEAD_PAT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_HEAD_PAT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_KISS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_RUB_TUMMY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_RUB_TUMMY"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_WALK = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Cell destination = null;
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWalked)) {
				destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL"));
				
			} else {
				Map<AbstractPlaceType, String> places = Util.newHashMapOfValues(
						new Value<>(PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, "ASHLEY"),
						new Value<>(PlaceType.SHOPPING_ARCADE_ANTIQUES, "ANTIQUES"),
						new Value<>(PlaceType.SHOPPING_ARCADE_KATES_SHOP, "KATE"),
						new Value<>(PlaceType.SHOPPING_ARCADE_PIXS_GYM, "PIX"),
						new Value<>(PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, "RALPH"),
						new Value<>(PlaceType.SHOPPING_ARCADE_RESTAURANT, "RESTAURANT"),
						new Value<>(PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, "VICKY"));
				AbstractPlaceType place = Util.randomItemFrom(places.keySet());
				destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(place);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_START"));
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_"+places.get(place)));
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_REPEAT_END"));
			}
			
			Cell finalDestination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getClosestCell(destination.getLocation(), PlaceType.SHOPPING_ARCADE_PATH);
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, finalDestination.getLocation(), false);
			getNyan().setLocation(Main.game.getPlayer(), false);
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
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFirstKissed)) {
				if(index==1) {
					return new Response("Continue", "Continue walking with Nyan around the Arcade.", ROMANCE_WALK_INITIAL_CONTINUE);
				}
				
			} else {
				if(index==1) {
					return new Response("Return", "Walk Nyan back to her store.", ROMANCE_WALK_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_NO_EVENT"));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.getPlayer().hasArms()) {
						return new Response("Hold hands", "As you do not have any arms, you cannot hold hands with Nyan!", null);
						
					} else if(Main.game.getPlayer().isArmMovementHindered()) {
						return new Response("Hold hands", "As your [pc.arm] movement is currently hindered by your clothing, you cannot comfortably hold hands with Nyan!", null);
						
					} else {
						return new Response("Hold hands", "Hold hands with Nyan as you walk her back to her store.", ROMANCE_WALK_END) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_HOLD_HANDS"));
								Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 2, 50, 60));
							}
						};
					}
					
				} else if(index==3) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Kiss", "As you are unable to access your mouth, you cannot kiss Nyan!", null);
						
					} else {
						return new Response("Kiss", "Take this opportunity to kiss Nyan in public.", ROMANCE_WALK_END) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_END_KISS"));
								Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_INITIAL_CONTINUE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Cell destination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_RESTAURANT);
				
			Cell finalDestination = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getClosestCell(destination.getLocation(), PlaceType.SHOPPING_ARCADE_PATH);
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, finalDestination.getLocation(), false);
			getNyan().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_CONTINUE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Return", "Accompany Nyan back to her shop.", ROMANCE_WALK_INITIAL_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_INITIAL_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
			getNyan().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Kiss", "As you are unable to access your mouth, you cannot kiss Nyan!", null);
					
				} else {
					return new Response("Kiss", "Take this opportunity to kiss Nyan.", ROMANCE_WALK_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END_KISS"));
							Main.game.getTextEndStringBuilder().append(incrementAffection(getNyan(), 5, 50, 60));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanFirstKissed, true);
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Don't kiss", "You can always repeat this experience again at another time...", ROMANCE_WALK_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_WALK_INITIAL_END_NO_KISS"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_WALK_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
			getNyan().setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWalked, true);
			Main.game.setResponseTab(1);
			
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
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_DATE_REQUESTED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanRestaurantDateRequested, true);
			Cell shoppingArcadeCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SHOPPING_ARCADE);
			Cell nyanApartmentCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(new Vector2i(shoppingArcadeCell.getLocation().getX()-1, shoppingArcadeCell.getLocation().getY()));
			nyanApartmentCell.getPlace().setPlaceType(PlaceType.DOMINION_NYAN_APARTMENT);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_DATE_REQUESTED");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_MAKE_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Bring an end to your makeout session.", POST_MAKEOUT);
				
			} else if(index==2) {
				return new ResponseSex("Cunnilingus", "Suggest to the horny cat-girl that you could eat her out right here in the storeroom.",
						true, true,
						new SMNyanSex(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.PERFORMING_ORAL_WALL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotAgainstWall.BACK_TO_WALL))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
								}
							}
//							@Override
//							public Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> exposeAtStartOfSexMapExtendedInformation() {
//								Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> defaultMap = super.exposeAtStartOfSexMapExtendedInformation();
//								
//								defaultMap.get(true).putIfAbsent(getNyan(), new HashMap<>());
//								defaultMap.get(true).get(getNyan()).put(CoverableArea.VAGINA, new ArrayList<>());
//								
//								return defaultMap;
//							}
							@Override
							public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						POST_ORAL,
						UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new ResponseSex("Fingering", "Pull up Nyan's miniskirt and start fingering her while continuing to kiss her.",
						true, true,
						new SMNyanSex(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.STANDING_WALL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotAgainstWall.BACK_TO_WALL))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
								}
							}
							@Override
							public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						POST_ORAL,
						UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			List<Response> receiveOralResponses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("Receive blowjob", "As you are unable to access your penis, you cannot get Nyan to suck your cock!", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("Receive blowjob", "Get Nyan to kneel down and suck your cock right here in the storeroom.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.AGAINST_WALL,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotAgainstWall.BACK_TO_WALL)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotAgainstWall.PERFORMING_ORAL_WALL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)));
										}
										@Override
										public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
											return false;
										}
									},
									null,
									null,
									POST_ORAL,
									UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_RECEIVE_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					receiveOralResponses.add(new Response("Receive cunnilingus", "As you are unable to access your vagina, you cannot get Nyan to perform cunnilingus on you!", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("Receive cunnilingus", "Get Nyan to kneel down and eat you out right here in the storeroom.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.AGAINST_WALL,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotAgainstWall.BACK_TO_WALL)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotAgainstWall.PERFORMING_ORAL_WALL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
										}
										@Override
										public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
											return false;
										}
									},
									null,
									null,
									POST_ORAL,
									UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			if(Main.game.isAnalContentEnabled() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
					receiveOralResponses.add(new Response("Ask about anal", "Ask Nyan if she'd be comfortable doing anal stuff with you.", ANAL_TALK));
					
				} else {
					receiveOralResponses.add(
							new ResponseSex("Perform anilingus",
								"Get the horny cat-girl to present you with her ass and start rimming her.",
								true, true,
								new SMNyanSex(
										SexPosition.AGAINST_WALL,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.STANDING_WALL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAgainstWall.FACE_TO_WALL))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
										return Util.newHashMapOfValues(
												new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.ANUS)));
									}
									@Override
									public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
										return false;
									}
								},
								null,
								null,
								POST_ORAL,
								UtilText.parseFromXMLFile("characters/dominion/nyan", "ROMANCE_MAKE_OUT_PERFORM_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
				}
			}
			if(index>0 && index-4<receiveOralResponses.size()) {
				return receiveOralResponses.get(index-4);
			}
			return null;
		}
	};

	public static final DialogueNode ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_MAKE_OUT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_MAKEOUT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "POST_MAKEOUT");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_ORAL = new DialogueNode("Finished", "Nyan has had enough for now...", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().cleanAllDirtySlots(true);
			getNyan().cleanAllClothing(false, false);
			Main.game.setResponseTab(1);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/nyan", "POST_ORAL");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_GIFT = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
}
