package com.lilithsthrone.game.dialogue.places.submission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.submission.SMBreedingStallBack;
import com.lilithsthrone.game.sex.managers.submission.SMBreedingStallFront;
import com.lilithsthrone.game.sex.managers.submission.SMRoxyPussyLicker;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;


/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class GamblingDenDialogue {

	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("Entrance", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced);
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "ENTRANCE_REPEAT");
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "ENTRANCE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelIntroduced)) {
					return new Response("Continue", "Set off to explore the Gambling Den.", ENTRANCE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
						}
					};
					
				} else {
					return new Response("Leave", "Push open the doors and step back out into Submission.", SubmissionGenericPlaces.GAMBLING_DEN){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN);
						}
					};
				}
				
			}
			// TODO add with Axel + Vengar quest
//			else if(index==2) {
//				return new Response("Axel", "Walk over to Axel and say hello.", AXEL){
//					@Override
//					public void effects() {
//						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelIntroduced, true);
//					}
//				};
//			
//			}
			else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AXEL = new DialogueNodeOld("Talking to Axel", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AXEL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Business", "Talk to Axel about his business.", AXEL_BUSINESS);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
					return new Response("Roxy", "Ask Axel about Roxy.", AXEL_ROXY) {
						@Override
						public void effects() {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelToldAboutVengar)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "AXEL_ROXY_VENGAR"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "AXEL_ROXY"));
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.axelToldAboutVengar, true);
						}
					};
				} else {
					return new Response("Roxy", "You'd need to talk with Roxy before asking Axel about her.", null);
				}
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelToldAboutVengar)) {
					return new Response("Vengar", "Ask Axel about Vengar.", AXEL_VENGAR);
				} else {
					return null;
				}
				
			} else if(index==0) {
				return new Response("Back", "Say goodbye and walk back out into the entrance hall.", ENTRANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AXEL_BUSINESS = new DialogueNodeOld("Talking to Axel", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelToldAboutVengar)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AXEL_BUSINESS_VENGAR");
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AXEL_BUSINESS");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Talk", "You're already talking to Axel.", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AXEL_ROXY = new DialogueNodeOld("Talking to Axel", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Roxy", "You're already talking with Axel about Roxy.", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AXEL_VENGAR = new DialogueNodeOld("Talking to Axel", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AXEL_VENGAR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("Vengar", "You're already talking with Axel about Vengar.", null);
			}
			return AXEL.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNodeOld CORRIDOR = new DialogueNodeOld("Gambling Den", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "CORRIDOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<10) {
					return new Response("Slot Machine ("+UtilText.formatAsMoney(10, "span")+")", "You don't have enough money to play on the slot machines!", null);
				} else {
					return new Response("Slot Machine ("+UtilText.formatAsMoney(10, "span")+")", "Put 10 flames into the nearest slot machine and pull the handle.", SLOT_MACHINE) {
						@Override
						public void effects() {
							
							Map<Subspecies, Integer> slotMachineValues = Util.newHashMapOfValues(
									new Value<>(Subspecies.HUMAN, 5),
									new Value<>(Subspecies.IMP, 10),
									new Value<>(Subspecies.DOG_MORPH, 25),
									new Value<>(Subspecies.CAT_MORPH, 25),
									new Value<>(Subspecies.COW_MORPH, 50),
									new Value<>(Subspecies.DEMON, 100),
									new Value<>(Subspecies.ELEMENTAL_ARCANE, 500));
							
							Map<Subspecies, Integer> slotMachineValueProbabilities = Util.newHashMapOfValues(
									new Value<>(Subspecies.HUMAN, 16),
									new Value<>(Subspecies.IMP, 8),
									new Value<>(Subspecies.DOG_MORPH, 2),
									new Value<>(Subspecies.CAT_MORPH, 2),
									new Value<>(Subspecies.COW_MORPH, 2),
									new Value<>(Subspecies.DEMON, 1),
									new Value<>(Subspecies.ELEMENTAL_ARCANE, 1));
							
							Main.game.getTextEndStringBuilder().append(
									"<p>"
										+ "Deciding to try your luck at one of the slot machines, you walk up to the nearest one and place ten flames in the coin slot."
									+ "</p>"
									+ Main.game.getPlayer().incrementMoney(-10)
									+"<p>"
										+ "Pulling the handle, you watch as the three reels on the front rapidly spin round and round, before slowly stopping on three pictures..."
									+ "</p>");
							

							Main.game.getTextEndStringBuilder().append("<div class='container-full-width'>");
							
								Main.game.getTextEndStringBuilder().append("<div class='container-half-width' style='position:relative; text-align:center;'>"
										+ "<p style='width:100%'><b>Slot Machine Results:</b></p>");
								
								List<Subspecies> races = new ArrayList<>(slotMachineValues.keySet());
								
								List<Subspecies> results = new ArrayList<>();
	
								boolean winner = false;
								if(Math.random()<0.32f) {
									Subspecies s = Util.getRandomObjectFromWeightedMap(slotMachineValueProbabilities);
									for(int i=0; i<3; i++) {
										results.add(s);
									}
									winner=true;
									
								} else {
									for(int i=0; i<3; i++) {
										Subspecies s = races.get(Util.random.nextInt(races.size()));
										results.add(s);
										if(i==0) {
											races.remove(s);
										}
									}
									Collections.shuffle(results);
								}
								
								for(Subspecies r : results) {
									Main.game.getTextEndStringBuilder().append(
											"<div class='modifier-icon' style='width:31.3%; margin:0 1%; border:3px solid "+(winner?Colour.GENERIC_EXCELLENT.toWebHexString():"")+"; display:inline-block;'>"
													+"<div class='modifier-icon-content'>"+r.getSVGString(null)+"</div>"
											+ "</div>");
								}
								if(winner) {
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
													+ "[style.colourExcellent(You won!)]<br/>Three "+results.get(0).getNamePlural()+" pay out "+UtilText.formatAsMoney(slotMachineValues.get(results.get(0)), "span")+"!"
											+ "</p>");
								} else {
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
													+ "[style.colourTerrible(You lost!)]"
											+ "</p>");
								}
								
								Main.game.getTextEndStringBuilder().append("</div>");
								Main.game.getTextEndStringBuilder().append("<div class='container-half-width' style='position:relative; text-align:center;'>"
										+"<p style='text-align:center;'>");
							
									for(Entry<Subspecies, Integer> entry : slotMachineValues.entrySet()) {
										Main.game.getTextEndStringBuilder().append("<span style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getNamePlural())+"</span>: "
												+UtilText.formatAsMoney(entry.getValue(), "span")+"<br/>");
									}
												
									Main.game.getTextEndStringBuilder().append("</p>");
								Main.game.getTextEndStringBuilder().append("</div>");
							Main.game.getTextEndStringBuilder().append("</div>");
								
							if(winner) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(slotMachineValues.get(results.get(0))));
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Having won "+Util.intToString(slotMachineValues.get(results.get(0)))+" flames, you wonder if you should pocket your money and continue on your way, or have another go and try for another win..."
										+ "</p>");
							} else {
								Main.game.getTextEndStringBuilder().append(
										"<p>"
											+ "Having lost your ten flames, you wonder if you should cut your losses and continue on your way, or have another go and try for a win..."
										+ "</p>");
							}
						}
					};
				}
			}
			return null;
		}
	};
	

	public static final DialogueNodeOld SLOT_MACHINE = new DialogueNodeOld("Gambling Den", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CORRIDOR.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNodeOld TRADER = new DialogueNodeOld("Roxy's Fun Box", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced);
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
				Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
				
				if(ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getRoxy().getId())) {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER_REPEAT_ADDICT");
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyAddicted)) {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER_REPEAT_BEATEN_ADDICTION");
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER_REPEAT");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
				if (index == 1) {
					return new Response("Refuse", "Tell Roxy that you're only interested in having a look around her shop.", TRADER_REPLY_NO){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex("Lick for item", "Agree to let Roxy sit on your face for an hour, in exchange for a random item from her shop.",
								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMRoxyPussyLicker(
										Util.newHashMapOfValues(new Value<>(Main.game.getRoxy(), SexPositionSlot.FACE_SITTING_ON_FACE)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_SITTING_ON_BACK))),
								AFTER_ROXY_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER_REPLY_YES")){
							@Override
							public void effects() {
								Main.game.getRoxy().displaceClothingForAccess(CoverableArea.VAGINA);
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
							}
						};
						
					} else {
						return new Response("Lick for item", "You can only service Roxy if you're able to gain access to your mouth!", null);
					}
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new ResponseTrade("Trade", "Trade with Roxy.", Main.game.getRoxy()){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						}
					};
					
				} else if (index == 2) {
					Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
					
					if(ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getRoxy().getId())) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Response("Get fix ("+UtilText.formatAsMoneyUncoloured(1000, "span")+")", "You can only service Roxy if you're able to gain access to your mouth!", null);
							
						} else if(Main.game.getPlayer().getMoney()<1000) {
							return new Response("Get fix ("+UtilText.formatAsMoneyUncoloured(1000, "span")+")", "You don't have the one thousand flames that Roxy is asking for!", null);
							
						} else {
							return new ResponseSex("Get fix ("+UtilText.formatAsMoney(1000, "span")+")", "Desperate to get another fix of her addictive girl cum, you agree to <b>pay Roxy 1000 flames</b> to get her to sit on your face for an hour.",
									Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
									true, false,
									new SMRoxyPussyLicker(
											Util.newHashMapOfValues(new Value<>(Main.game.getRoxy(), SexPositionSlot.FACE_SITTING_ON_FACE)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_SITTING_ON_BACK))),
									AFTER_ROXY_SEX_ADDICT,
									UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROXY_SEX_START_ADDICT")){
								@Override
								public void effects() {
									Main.game.getRoxy().displaceClothingForAccess(CoverableArea.VAGINA);
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-1000));
								}
							};
						}
						
					} else {
						if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new ResponseSex("Lick for item", "Agree to let Roxy sit on your face for an hour, in exchange for a random item from her shop.",
									Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
									true, false,
									new SMRoxyPussyLicker(
											Util.newHashMapOfValues(new Value<>(Main.game.getRoxy(), SexPositionSlot.FACE_SITTING_ON_FACE)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.FACE_SITTING_ON_BACK))),
									AFTER_ROXY_SEX,
									UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROXY_SEX_START")){
								@Override
								public void effects() {
									Main.game.getRoxy().displaceClothingForAccess(CoverableArea.VAGINA);
									Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
								}
							};
							
						} else {
							return new Response("Lick for item", "You can only service Roxy if you're able to gain access to your mouth!", null);
						}
					}
					
	
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld TRADER_REPLY_NO = new DialogueNodeOld("Roxy's Fun Box", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "TRADER_REPLY_NO");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_ROXY_SEX = new DialogueNodeOld("Roxy stands up", "Roxy is finished, and, not caring whether you've had any fun or not, starts to stand up.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AFTER_ROXY_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Roll", "Watch Roxy roll her dice.", AFTER_ROXY_SEX_ITEM_OBTAINED) {
					@Override
					public void effects() {
						Dice d = new Dice(Util.newHashMapOfValues(new Value<>(DiceFace.ONE, 8f), new Value<>(DiceFace.TWO, 4f), new Value<>(DiceFace.THREE, 2f)));
						d.roll();
						int d1 = d.getFace().getValue();
						d.roll();
						int d2 = d.getFace().getValue();
						AbstractItem item;
						
						int dTotal = d1 + d2;
						if(dTotal<=3) {
							item = AbstractItemType.generateItem(ItemType.INT_INGREDIENT_VANILLA_WATER);
						} else if(dTotal<=5) {
							item = AbstractItemType.generateItem(ItemType.INT_INGREDIENT_FRUIT_BAT_SQUASH);
						} else if(dTotal<=7) {
							item = AbstractItemType.generateItem(ItemType.MOTHERS_MILK);
						} else if(dTotal<=9) {
							item = AbstractItemType.generateItem(ItemType.STR_INGREDIENT_BLACK_RATS_RUM);
						} else if(dTotal<=11) {
							item = AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_RAT_MORPH);
						} else {
							item = AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HUMAN);
						}

						Main.game.getTextEndStringBuilder().append(
								"<p>"
									+ "Roxy rolls the dice onto the counter, and you both watch as they quickly come clattering to a halt."
									+ " You can't help but get the feeling that there's something a little unnatural in the way that they tumble across the counter-top, but, not being certain about it, you decide not to say anything."
								+ "</p>"
								+ "<p>"
									+ "The first dice stops on a <b>"+Util.intToString(d1)+"</b>, and the second dice stops on a <b>"+Util.intToString(d2)+"</b>, giving a total value of <b>"+Util.intToString(dTotal)+"</b>."
								+ "</p>"
								+ "<p>"
									+ "[roxy.speech(Well, would yer fuckin' look at that!)] Roxy exclaims, looking down at the result of her roll. [roxy.speech(That fuckin' gets yer "+item.getName(true, false)+"!)]"
								+ "</p>"
								+ "<p>"
									+ "As the rat-girl throws the promised item to you, she laughs her abrasive, cackling laugh,"
									+ " [roxy.speech(Heh! I'll be makin' the value o' that back soon enough!"
									+ " Yer gonna get some real fuckin' nice cravings fer my cunt before long, and when yer do, yer gonna come crawlin' back here beggin' fer another taste o' my fuckin' sweet nectar."
									+ " I think a thousand flames is a fair price, so make sure yer bring yer cash with yer when the cravings kick in! Heh!)]"
								+ "</p>"
								+ "<p>"
									+ "Taking the "+item.getName()+" that Roxy just gave to you, you turn around and prepare to take your leave..."
								+ "</p>");
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, false));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_ROXY_SEX_ITEM_OBTAINED = new DialogueNodeOld("Roxy's Fun Box", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_ROXY_SEX_ADDICT = new DialogueNodeOld("Roxy stands up", "Roxy is finished, and, not caring whether you've had any fun or not, starts to stand up.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AFTER_ROXY_SEX_ADDICT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld GAMBLING = new DialogueNodeOld("Dice Poker Tables", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "GAMBLING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> gamblers = Main.game.getNonCompanionCharactersPresent();
			
			if(index==0) {
				return null;
				
			} else if(index==gamblers.size()+1){
				return new Response("Rules", "Take a look at a nearby sign which displays the rules of dice poker.", GAMBLING_RULES);
				
			} else {

				try {
					gamblers.sort((g1, g2) -> ((GamblingDenPatron) g1).getTable().compareTo(((GamblingDenPatron) g2).getTable()));
				} catch(Exception ex) {
				}
				
				if(index-1<gamblers.size()) {
					DicePokerTable table = DicePokerTable.COPPER;
					NPC gambler = gamblers.get(index-1);
					try {
						table = ((GamblingDenPatron) gambler).getTable();
					} catch(Exception ex) {
					}
					int buyIn = table.getInitialBet()+table.getRaiseAmount();
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly("<span style='color:"+table.getColour().toWebHexString()+";'>"+gambler.getName()+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
								"Start playing dice poker with "+gambler.getName()+". The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
									+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises.") {
							@Override
							public void effects() {
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler)));
							}
						};
						
					} else {
						return new Response("<span style='color:"+table.getColour().toWebHexString()+";'>"+gambler.getName()+"</span> ("+UtilText.formatAsMoney(buyIn, "span")+")",
								"The buy-in amount is "+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+", but you'll also need "+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+" for any raises. As a result, you don't have enough money to play at this table!",
								null);
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld GAMBLING_RULES = new DialogueNodeOld("Dice Poker Tables", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "GAMBLING_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Finish reading the rules and step back from the sign.", GAMBLING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_MALE_STALLS = new DialogueNodeOld("Male Breeding Stalls", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MALE_STALLS"));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_STALLS_KNOWLEDGE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_STALLS_NO_KNOWLEDGE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_FUTA_STALLS = new DialogueNodeOld("Futa Breeding Stalls", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_FUTA_STALLS"));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_STALLS_KNOWLEDGE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_STALLS_NO_KNOWLEDGE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	private static List<GenericSexualPartner> breeders = new ArrayList<>();
	private static int breederIndex = 0;
	private static int roll = 1;
	private static GenericSexualPartner mother;
	private static GameCharacter selectedBreeder;
	
	private static void initBreeder(NPC partner) {
		partner.deleteAllEquippedClothing();
		partner.clearFetishes();
		partner.clearFetishDesires();
		partner.addFetish(Fetish.FETISH_IMPREGNATION);
		partner.addFetish(Fetish.FETISH_VAGINAL_GIVING);
		partner.setPlayerKnowsName(true);
		partner.setPenisVirgin(false);
		partner.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		partner.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		partner.setAttribute(Attribute.VIRILITY, (partner.getPenisRawSizeValue()*2)+(partner.getTesticleSize().getValue() * 5)+partner.getPenisRawCumStorageValue());
		
		try {
			Main.game.addNPC(partner, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initMother() {
		mother = new GenericSexualPartner(Gender.F_V_B_FEMALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
		mother.clearFetishes();
		mother.clearFetishDesires();
		mother.addFetish(Fetish.FETISH_PREGNANCY);
		mother.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		mother.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		mother.deleteAllEquippedClothing();
		mother.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		mother.setPlayerKnowsName(true);
		mother.useItem(AbstractItemType.generateItem(ItemType.VIXENS_VIRILITY), mother, false);
		try {
			Main.game.addNPC(mother, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE = new DialogueNodeOld("Pregnancy Roulette Counter", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)
					|| (Main.game.getEpona().isVisiblyPregnant() && !Main.game.getEpona().isReactedToPregnancy());
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE"));
				
				boolean preg = Main.game.getPlayer().hasFetish(Fetish.FETISH_PREGNANCY);
				boolean impreg = Main.game.getPlayer().hasFetish(Fetish.FETISH_IMPREGNATION);
				if(preg) {
					if(impreg) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BOTH_FETISH"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_PREGNANCY_FETISH"));
					}
					
				} else if(impreg) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_IMPREGNATION_FETISH"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_NO_FETISH"));
				}
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_END"));
				
			} else if(Main.game.getEpona().isVisiblyPregnant() && !Main.game.getEpona().isReactedToPregnancy()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_EPONA_IMPREGNATED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_REPEAT"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Returning her greeting, you walk up to the counter, where the happy horse-girl asks, [epona.speech(You interested in having another go at pregnancy roulette?");
					
					if(Main.game.getDialogueFlags().eponaStamps>=6) {
						UtilText.nodeContentSB.append(" Or perhaps you want to cash in your stamps and take me for a ride!)]"
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(" You've got "+Util.intToString(6-Main.game.getDialogueFlags().eponaStamps)+" stamps to go until you get to take me for a ride!)]"
								+ "</p>");
					}
				}  else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Returning her greeting, you walk up to the counter, where the happy horse-girl asks, [epona.speech(You interested in having a go at pregnancy roulette? Gotta start collecting those stamps!)]"
							+ "</p>");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)) {
				if(index==1) {
					return new Response("Continue", "Now that Epona has told you about the game of pregnancy roulette, you wonder what you should do next...", PREGNANCY_ROULETTE_GREETING_UTIL) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaIntroduced, true);
							Main.game.getTextStartStringBuilder().append(UtilText.nodeContentSB.toString());
						}
					};
				} else {
					return null;
				}
				
			} else if(Main.game.getEpona().isVisiblyPregnant() && !Main.game.getEpona().isReactedToPregnancy()) {
				if(index==1) {
					return new Response("Continue", "You're happy to see how delighted Epona is to be the mother of your children.", PREGNANCY_ROULETTE_GREETING_UTIL) {
						@Override
						public void effects() {
							Main.game.getEpona().setReactedToPregnancy(true);
							Main.game.getTextStartStringBuilder().append(UtilText.nodeContentSB.toString());
						}
					};
				} else {
					return null;
				}
				
			} else {
				if(index == 1) {
					return new Response("Rules", "Ask Epona about the rules for pregnancy roulette", PREGNANCY_ROULETTE_RULES);
					
				} else if(index==2) {
					if(Main.game.getPlayer().isPregnant()) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You are already pregnant, so you can't sign up to be the mother!", null);
						
					} else if(Main.game.getPlayer().getTotalFluidInArea(SexAreaOrifice.VAGINA)>0) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You can't sign up for pregnancy roulette if your pussy already is filled with cum!", null);
						
					} else if(!Main.game.getPlayer().hasVagina()) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have a vagina, so you can't sign up to be the mother!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You aren't able to gain access to your vagina, so you can't sign up to be the mother!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have enough money, so you can't sign up to be the mother!", null);
						
					} else {
						return new Response("Male Bred ("+UtilText.formatAsMoney(10000, "span")+")", "Sign up as the mother for pregnancy roulette, asking to bred by the males.", PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION) {
							@Override
							public Colour getHighlightColour() {
								return Colour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
								Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								for(int i=0; i<6; i++) {
									GenericSexualPartner partner = new GenericSexualPartner(Gender.M_P_MALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
									initBreeder(partner);
									breeders.add(partner);
								}
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getPlayer().isPregnant()) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You are already pregnant, so you can't sign up to be the mother!", null);
						
					} else if(Main.game.getPlayer().getTotalFluidInArea(SexAreaOrifice.VAGINA)>0) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You can't sign up for pregnancy roulette if your pussy already is filled with cum!", null);
						
					} else if(!Main.game.getPlayer().hasVagina()) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have a vagina, so you can't sign up to be the mother!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You aren't able to gain access to your vagina, so you can't sign up to be the mother!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have enough money, so you can't sign up to be the mother!", null);
						
					} else {
						return new Response("Futa Bred ("+UtilText.formatAsMoney(10000, "span")+")", "Sign up as the mother for pregnancy roulette, asking to bred by the futas.", PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION) {
							@Override
							public Colour getHighlightColour() {
								return Colour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
								Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								for(int i=0; i<6; i++) {
									GenericSexualPartner partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
									initBreeder(partner);
									breeders.add(partner);
								}
							}
						};
					}
					
				} else if(index==4) {
					if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
						return new Response("Breeder ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have a penis, so you can't sign up to be one of the breeders!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Breeder ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You aren't able to gain access to your penis, so you can't sign up to be one of the breeders!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Breeder ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have enough money, so you can't sign up to be one of the breeders!", null);
						
					} else {
						return new Response("Breeder ("+UtilText.formatAsMoney(10000, "span")+")", "Sign up as one of the breeders for pregnancy roulette.", PREGNANCY_ROULETTE_BREEDER) {
							@Override
							public Colour getHighlightColour() {
								return Colour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								// Skew the dice roll in the player's favour (lowest number goes first):
								Dice d = new Dice(Util.newHashMapOfValues(
										new Value<>(DiceFace.ONE, 4f),
										new Value<>(DiceFace.TWO, 3f),
										new Value<>(DiceFace.THREE, 2f),
										new Value<>(DiceFace.FOUR, 1f),
										new Value<>(DiceFace.FIVE, 0.5f),
										new Value<>(DiceFace.SIX, 0.25f)));
								d.roll();
								roll = d.getFace().getValue();
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
									Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
									for(int i=0; i<5; i++) {
										GenericSexualPartner partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
										initBreeder(partner);
										breeders.add(partner);
									}
									
								} else {
									Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
									Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
									for(int i=0; i<5; i++) {
										GenericSexualPartner partner = new GenericSexualPartner(Gender.M_P_MALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
										initBreeder(partner);
										breeders.add(partner);
									}
								}
								
								initMother();
								
								
								Main.game.getPlayer().displaceClothingForAccess(CoverableArea.PENIS);
							}
						};
					}
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().eponaStamps<6) {
						return new Response("Ride Epona", "You haven't collected enough stamps to take Epona for a ride! You have "+Util.intToString(Main.game.getDialogueFlags().eponaStamps)+", and you need at least six.", null);
						
					} else {
						return new ResponseSex("Ride Epona", "Tell Epona that you want to cash in your stamps and take her for a ride. (Have sex as the dominant partner.)",
								null, null, null, null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getEpona(), SexPositionSlot.STANDING_SUBMISSIVE))),
								EPONA_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen", "EPONA_START_SEX_AS_SUB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().eponaStamps-=6;
							}
						};
					}
					
				} else if(index==7) {
					if(Main.game.getDialogueFlags().eponaStamps<6) {
						return new Response("Epona Rides", "You haven't collected enough stamps to get Epona to take you for a ride! You have "+Util.intToString(Main.game.getDialogueFlags().eponaStamps)+", and you need at least six.", null);
						
					} else {
						return new ResponseSex("Epona Rides", "Tell Epona that you want to cash in your stamps and have her take you for a ride. (Have sex as the submissive partner.)",
								null, null, null, null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getEpona(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								EPONA_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen", "EPONA_START_SEX_AS_DOM")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().eponaStamps-=6;
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld EPONA_POST_SEX = new DialogueNodeOld("Finished", "Epona takes a moment to catch her breath, before getting ready to return to work.", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Main.game.getEpona())>0) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "EPONA_POST_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen", "EPONA_POST_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_GREETING_UTIL = new DialogueNodeOld("Pregnancy Roulette Counter", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_RULES = new DialogueNodeOld("Pregnancy Roulette Counter", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
	};
	
	private static String getBreederPanel(NPC breeder) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-half-width'>"
				+ "[npc.Name] - [npc.FullRace(true)]<br/>"
				+ "[npc.CockGirth], [npc.penisInches]-inch, [npc.cockColour(true)] [npc.cockRace] [npc.cock], with [npc.ballSize] balls."
				+ "</div>");
		
		return UtilText.parse(breeder, sb.toString());
	}
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_FUTA_PREGNANCY) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION_FUTA"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION"));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index==1) {
				return new Response("Wait", "Wait for Epona to lead the breeders into the room.", PREGNANCY_ROULETTE_MOTHER_SELECTION) {
					@Override
					public void effects() {
						Main.game.getEpona().useItem(AbstractItemType.generateItem(ItemType.VIXENS_VIRILITY), Main.game.getPlayer(), false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_MOTHER_SELECTION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_FUTA_PREGNANCY) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_SELECTION_FUTA"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_SELECTION"));
			}

			for(int i=0;i< breeders.size()/2;i++) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='width:100%; margin:0'>");
					UtilText.nodeContentSB.append(getBreederPanel(breeders.get(i*2)));
					UtilText.nodeContentSB.append(getBreederPanel(breeders.get(i*2 +1)));
				UtilText.nodeContentSB.append("</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index<=breeders.size()) {
				NPC breeder = breeders.get(index-1);
				return new Response(breeder.getName(), "Tell Epona that you think "+breeder.getName()+" will get you pregnant.", PREGNANCY_ROULETTE_MOTHER_START) {
					@Override
					public void effects() {
						selectedBreeder=breeder;
						Collections.shuffle(breeders);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_MOTHER_START = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_START", Util.newArrayListOfValues(selectedBreeder));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				NPC breeder = breeders.get(breederIndex);
				
				return new ResponseSex("Front "+breeder.getName(), "Lie on your front, where "+breeder.getName()+" will be the first to move up to fuck you.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStallFront(true, false, false,
								Util.newHashMapOfValues(new Value<>(breeder, SexPositionSlot.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.BREEDING_STALL_FRONT))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPartnerWantingToStopSex() {
								return Sex.getNumberOfOrgasms(Sex.getActivePartner())>=1;
							}
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								if(!character.isPlayer()) {
									character.setArousal(75);
									character.setLust(80);
								}
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
						},
						AFTER_ROULETTE_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
					@Override
					public void effects() {
						breederIndex++;
					}
				};
				
			} else if(index==2) {
				NPC breeder = breeders.get(breederIndex);
				
				return new ResponseSex("Back "+breeder.getName(), "Lie on your back, where "+breeder.getName()+" will be the first to move up to fuck you.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStallBack(true, false, false,
								Util.newHashMapOfValues(new Value<>(breeder, SexPositionSlot.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.BREEDING_STALL_BACK))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPartnerWantingToStopSex() {
								return Sex.getNumberOfOrgasms(Sex.getActivePartner())>=1;
							}
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								if(!character.isPlayer()) {
									character.setArousal(75);
									character.setLust(80);
								}
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
						},
						AFTER_ROULETTE_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
					@Override
					public void effects() {
						breederIndex++;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_ROULETTE_SEX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return breeders.get(breederIndex-1).getName()+" is done";
		}
		
		@Override
		public String getContent() {
			NPC breeder = breeders.get(breederIndex-1);
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "AFTER_ROULETTE_SEX", Util.newArrayListOfValues(breeder));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(breederIndex<breeders.size()) {
				if(index==1) {
					NPC breeder = breeders.get(breederIndex);
					
					return new ResponseSex("Front "+breeder.getName(), "Position yourself so that you're lying on your front, where "+breeder.getName()+" will be the next breeder to move up to fuck you.",
							null, null, null, null, null, null,
							true, false,
							new SMBreedingStallFront(true, false, false,
									Util.newHashMapOfValues(new Value<>(breeder, SexPositionSlot.BREEDING_STALL_FUCKING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.BREEDING_STALL_FRONT))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isPartnerWantingToStopSex() {
									return Sex.getNumberOfOrgasms(Sex.getActivePartner())>=1;
								}
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setArousal(75);
										character.setLust(80);
									}
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
								}
							},
							AFTER_ROULETTE_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
						@Override
						public void effects() {
							breederIndex++;
						}
					};
					
				} else if(index==2) {
					NPC breeder = breeders.get(breederIndex);
					
					return new ResponseSex("Back "+breeder.getName(), "Position yourself so that you're lying on your back, where "+breeder.getName()+" will be the next breeder to move up to fuck you.",
							null, null, null, null, null, null,
							true, false,
							new SMBreedingStallBack(true, false, false,
									Util.newHashMapOfValues(new Value<>(breeder, SexPositionSlot.BREEDING_STALL_FUCKING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.BREEDING_STALL_BACK))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isPartnerWantingToStopSex() {
									return Sex.getNumberOfOrgasms(Sex.getActivePartner())>=1;
								}
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setArousal(75);
										character.setLust(80);
									}
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
								}
							},
							AFTER_ROULETTE_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
						@Override
						public void effects() {
							breederIndex++;
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Finished", "All six of the breeders have deposited their cum in your [pc.pussy+].", PREGNANCY_ROULETTE_MOTHER_FINISHED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getEpona().useItem(AbstractItemType.generateItem(ItemType.PREGNANCY_TEST), Main.game.getPlayer(), false));
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_MOTHER_FINISHED = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_FINISHED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPregnant()) {
					if(Main.game.getPlayer().getPregnantLitter().getFather().equals(selectedBreeder)) {
						return new Response("Winner!", "You correctly guessed that "+selectedBreeder.getName()+" would be the father!", PREGNANCY_ROULETTE_MOTHER_WINNER) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(50000));
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
							}
						};
						
					} else {
						return new Response("Loser", "You incorrectly guessed that "+selectedBreeder.getName()+" would be the father, so lost your bet...", PREGNANCY_ROULETTE_MOTHER_LOSER){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
							}
						};
					}
						
				} else {
					return new Response("Loser", "You didn't get pregnant, so everyone loses their bets...", PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							for(NPC npc : breeders) {
								Main.game.banishNPC(npc);
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_MOTHER_WINNER = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_WINNER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_MOTHER_LOSER = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_LOSER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld PREGNANCY_ROULETTE_BREEDER = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isFeminine()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER_FUTA", Util.newArrayListOfValues(mother)));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER", Util.newArrayListOfValues(mother)));
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Epona rolls the dice, and you watch as it clatters to a halt on the number "+Util.intToString(roll)+"."
						+ " [epona.speech(Alright, [pc.name], that means you're going "+Util.intToPosition(roll)+"! Now, erm, I'll keep rolling for the rest of you!)]"
					+ "</p>"
					+ "<p>"
						+ "You all then have to wait while Epona rolls for each of the other breeders."
						+ " Every time she rolls an already-rolled number, she has to re-roll the dice, which results in you all having to wait for far longer than if she'd just used a more efficient method of deciding who goes first."
					+ "</p>"
					+ "<p>"
						+ "Eventually, however, Epona has finished, and you move forwards to take your place in the line..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<i>You are going <b>"+Util.intToPosition(roll)+"</b>!</i>"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1){
				return new ResponseSex(roll==1?"First":"Your turn",
						roll==1
							?"As you rolled a one, you're the first to have a go at fucking the volunteer."
							:"As the breeder steps away from the volunteer, you step forwards to take your turn at fucking her creampied-pussy.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStallBack(true, false, false,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(mother, SexPositionSlot.BREEDING_STALL_BACK))) {
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								character.setArousal(50);
								character.setLust(80);
							}
						},
						PREGNANCY_ROULETTE_BREEDER_POST_SEX,
						roll==1
							?UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER_FIRST", Util.newArrayListOfValues(mother))
							:UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER_MIDDLE", Util.newArrayListOfValues(mother))){
					@Override
					public void effects() {
						for(int i=0; i<roll-1; i++) {
							mother.setVaginaVirgin(false);
							mother.ingestFluid(breeders.get(i), breeders.get(i).getCumType(), SexAreaOrifice.VAGINA, breeders.get(i).getPenisRawOrgasmCumQuantity(), breeders.get(i).getCum().getFluidModifiers());
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_BREEDER_POST_SEX = new DialogueNodeOld("Finished", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER_POST_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait for Epona to return.", PREGNANCY_ROULETTE_BREEDER_FINISHED) {
					@Override
					public void effects() {
						for(int i=roll-1; i<breeders.size(); i++) {
							mother.setVaginaVirgin(false);
							mother.ingestFluid(breeders.get(i), breeders.get(i).getCumType(), SexAreaOrifice.VAGINA, breeders.get(i).getPenisRawOrgasmCumQuantity(), breeders.get(i).getCum().getFluidModifiers());
						}
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_BREEDER_FINISHED = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER_FINISHED")
					+mother.useItem(AbstractItemType.generateItem(ItemType.PREGNANCY_TEST), mother, false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(mother.isPregnant()) {
					if(mother.getPregnantLitter().getFather().isPlayer()) {
						return new Response("Winner!", "You got "+mother.getName()+" pregnant!", PREGNANCY_ROULETTE_BREEDER_WINNER) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(20000));
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
								Main.game.banishNPC(mother);
								
							}
						};
						
					} else {
						return new Response("Loser", "You didn't get "+mother.getName()+" pregnant, so lost your bet...", PREGNANCY_ROULETTE_BREEDER_LOSER){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
								Main.game.banishNPC(mother);
							}
						};
					}
						
				} else {
					return new Response("Loser", "Nobody got "+mother.getName()+" pregnant, so everyone loses their bets...", PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							Main.game.getEpona().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							for(NPC npc : breeders) {
								Main.game.banishNPC(npc);
							}
							Main.game.banishNPC(mother);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_BREEDER_WINNER = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER_WINNER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_BREEDER_LOSER = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER_LOSER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY = new DialogueNodeOld("", "", false, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen", "PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
