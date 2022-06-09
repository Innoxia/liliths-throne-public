package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.QuickTransformations;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.3.7.8
 * @author Innoxia
 */
public class AlleywayProstituteDialogue {
	
	private static boolean inApartment = false;
	private static boolean hadSex = false;
	
	private static int prostitutePrice(boolean threesome) {
		return getProstitute().getProstitutePrice() * (threesome?2:1);
	}
	
	private static NPC getProstitute() {
		return Main.game.getActiveNPC();
	}
	
	private static boolean isStorm() {
		return getProstitute().isVulnerableToArcaneStorm()
				&& Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
	}
	
	public static final DialogueNode ALLEY_PROSTITUTE = new DialogueNode("Prostitute", "", true) {
		@Override
		public void applyPreParsingEffects() {
			inApartment = false;
			hadSex = false;
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(false)), true);
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(true)), false);
		}
		@Override
		public String getContent() {
			if(isStorm()) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_STORM", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int cost = isStorm()?0:prostitutePrice(false);
			int threesomeCost = isStorm()?0:prostitutePrice(true);
			
			if (index == 1) {
				return new Response("Leave", "You're not at all interested in having sex with a prostitute. Walk around [npc.herHim] and continue on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(getProstitute().isVisiblyPregnant()){
							getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_LEAVE", getProstitute()));
					}
				};
				
			} else if (index == 2 && !hadSex) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("Dominant ("+UtilText.formatAsMoney(cost, "span")+")",
							UtilText.parse(getProstitute(), "You don't have "+cost+" flames, so you can't afford to have sex with [npc.name]."),
							null);
					
				} else {
					return new ResponseSex("Dominant ("+UtilText.formatAsMoney(cost, "span")+")",
							UtilText.parse(getProstitute(),
									cost==0
										?"[npc.Name] is so turned on that [npc.she] isn't going to charge you anything for having sex with [npc.herHim]!"
										:("Pay [npc.name] "+cost+" flames to have dominant sex with [npc.herHim].")),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getProstitute()),
							null,
							null) {
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.equals(getProstitute())) {
										return SexPace.SUB_NORMAL;
									}
									return super.getStartingSexPaceModifier(character);
								}
							},
							AFTER_SEX_PAID,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_DOM_SEX", getProstitute())) {
						@Override
						public void effects() {
							hadSex = true;
							inApartment = !isStorm();
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							if(cost>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
								getProstitute().incrementMoney(cost);
							}
						}
					};
				}
				
			} else if (index == 3 && !hadSex) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("Submissive ("+UtilText.formatAsMoney(cost, "span")+")",
							UtilText.parse(getProstitute(), "You don't have "+cost+" flames, so you can't afford to have sex with [npc.name]."),
							null);
					
				} else {
					return new ResponseSex("Submissive ("+UtilText.formatAsMoney(cost, "span")+")",
							UtilText.parse(getProstitute(),
									cost==0
										?"[npc.Name] is so turned on that [npc.she] isn't going to charge you anything for having sex with [npc.herHim]!"
										:("Pay [npc.name] "+cost+" flames to have submissive sex with [npc.herHim].")),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null) {
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.equals(getProstitute())) {
										return SexPace.DOM_NORMAL;
									}
									return super.getStartingSexPaceModifier(character);
								}
							},
							AFTER_SEX_PAID,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_SUB_SEX", getProstitute())) {
						@Override
						public void effects() {
							hadSex = true;
							inApartment = !isStorm();
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							if(cost>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
								getProstitute().incrementMoney(cost);
							}
						}
					};
				}
				
			} else if(index == 4) {
				if(!getProstitute().hasFlag(NPCFlagValue.prostituteQuestioned)) {
					return new Response("Question",
							UtilText.parse(getProstitute(), "Ask [npc.name] why [npc.sheIs] working in the alleyways. After all, it would be far safer to work out in the main streets of Dominion..."),
							ALLEY_PROSTITUTE_QUESTION) {
						@Override
						public void effects() {
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							getProstitute().addFlag(NPCFlagValue.prostituteQuestioned);
						}
					};
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
					if(RedLightDistrict.isSpaceForMoreProstitutes()) {
						return new Response("Angel's Kiss",
								UtilText.parse(getProstitute(), "Tell [npc.name] that [npc.she] can get back on the right side of the law if [npc.she] agrees to come and work at Angel's Kiss."),
								ALLEY_PROSTITUTE_ANGELS_KISS) {
							@Override
							public void effects() {
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								Main.game.getPlayer().incrementKarma(25);
								getProstitute().setDescription(UtilText.parse(getProstitute(), "You first found [npc.name] in the alleyways of Dominion, where [npc.she] was illegally selling [npc.her] body."
										+ " You offered [npc.herHim] the chance to move and work out of Angel's Kiss; an offer that [npc.she] happily accepted."));
								Main.game.getTextEndStringBuilder().append(getProstitute().incrementAffection(Main.game.getPlayer(), 50));
								getProstitute().setRandomUnoccupiedLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_BEDROOM, true);
							}
						};
						
					} else {
						return new Response("Angel's Kiss", "There's no room available at Angel's Kiss for another prostitute...", null);
					}
				}
			}
			
			if(Main.game.getPlayer().hasCompanions()) {
				if(index == 6 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("Threesome ("+UtilText.formatAsMoney(threesomeCost, "span")+")",
								UtilText.parse(getProstitute(), "Your [com.companion] isn't attracted to [npc.name], and so would be unwilling to have sex with [npc.herHim]."),
								null);
						
					} else if(Main.game.getPlayer().getMoney()<threesomeCost) {
						return new Response("Threesome ("+UtilText.formatAsMoney(threesomeCost, "span")+")",
								UtilText.parse(getProstitute(), "You don't have "+threesomeCost+" flames, so you can't afford to have a threesome with [com.name] and [npc.name]."),
								null);
						
					} else {
						return new ResponseSex("Threesome ("+UtilText.formatAsMoney(threesomeCost, "span")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name] is so turned on that [npc.she] isn't going to charge you anything for having sex with [npc.herHim]!"
											:("Pay [npc.name] "+threesomeCost+" flames to have a threesome with you and [com.name].")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(
												Main.game.getPlayer(),
												Main.game.getPlayer().getMainCompanion()),
										Util.newArrayListOfValues(getProstitute()),
								null,
								null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_THREESOME", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(threesomeCost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-threesomeCost));
									getProstitute().incrementMoney(threesomeCost);
								}
							}
						};
					}
					
				} else if (index == 7 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("Dominant ([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								UtilText.parse(getProstitute(), "Your [com.companion] isn't attracted to [npc.name], and so would be unwilling to have sex with [npc.herHim]."),
								null);
						
					} else if(Main.game.getPlayer().getMoney()<cost) {
						return new Response("Dominant ([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								UtilText.parse(getProstitute(), "You don't have "+cost+" flames, so you can't afford to pay for [com.name] to dominantly fuck [npc.name]."),
								null);
						
					} else {
						return new ResponseSex("Dominant ([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name] is so turned on that [npc.she] isn't going to charge you anything for letting [com.name] dominantly fuck [npc.herHim]!"
											:("Pay [npc.name] "+cost+" flames to let [com.name] dominantly fuck [npc.herHim].")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer().getMainCompanion()),
										Util.newArrayListOfValues(getProstitute()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null) {
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return !character.isPlayer();
									}
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_COMPANION_DOM_SEX", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(cost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
									getProstitute().incrementMoney(cost);
								}
							}
						};
					}
					
				} else if (index == 8 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("Submissive ([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								"Your [com.companion] isn't attracted to [npc.name], and so [npc.name] would be unwilling to make [com.herHim] have sex with [npc.herHim].",
								null);
						
					} if(Main.game.getPlayer().getMoney()<cost) {
						return new Response("Submissive ([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								"You don't have "+cost+" flames, so you can't afford to pay for [npc.name] to dominantly fuck [com.name].",
								null);
						
					} else {
						return new ResponseSex("Submissive ([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name] is so turned on that [npc.she] isn't going to charge you anything for dominantly fucking [com.name]!"
											:("Pay [npc.name] "+cost+" flames to dominantly fuck [com.name].")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getProstitute()),
										Util.newArrayListOfValues(Main.game.getPlayer().getMainCompanion()),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.DOM_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_COMPANION_SUB_SEX", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(cost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
									getProstitute().incrementMoney(cost);
								}
							}
						};
					}
				}
			}
		
			if(getProstitute().hasFlag(NPCFlagValue.prostituteQuestioned)) {
				if(index == 5) {
					return new Response("Attack",
							UtilText.parse(getProstitute(), "If you really wanted to, there's nothing stopping you from attacking [npc.name]. After all, as [npc.sheHas] run afoul of the law [npc.she] can be considered to be fair game!"
									+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
							PROSTITUTE_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
						}
					};
					
				} else if (index == 9) {
					return new Response(
							"Remove (threaten)",
							UtilText.parse(getProstitute(), "Tell [npc.name] that unless [npc.she] immediately leaves this area, you're going to tell the Enforcers where [npc.she] is."
									+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
							PROSTITUTE_REMOVAL_THREATENED) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
					};
					
				} else if (index == 10) {
					if(Main.game.getPlayer().getMoney()<Main.game.getDialogueFlags().getProstituteFine()) {
						return new Response("Remove ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getProstituteFine(), "span")+")",
								UtilText.parse(getProstitute(), "You don't have "+Util.intToString(Main.game.getDialogueFlags().getProstituteFine())+" flames, so you can't afford to pay [npc.name] to leave this area."),
								null);
					} else {
						return new Response(
								"Remove ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().getProstituteFine(), "span")+")",
								UtilText.parse(getProstitute(), "Give [npc.name] enough money to pay off the Enforcers who are after [npc.herHim], which would allow [npc.herHim] to stop having to work in these dangerous alleyways."
										+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
								PROSTITUTE_REMOVAL_PAID) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_NPC_REMOVAL;
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ALLEY_PROSTITUTE_QUESTION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().getProstituteFine()), true);
			if(inApartment) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_QUESTION_APARTMENT", getProstitute()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_QUESTION", getProstitute()));
			}
			// Reset special parsing so that ALLEY_PROSTITUTE.getResponse() parsing is correct:
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(false)), true);
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(true)), false);
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ALLEY_PROSTITUTE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ALLEY_PROSTITUTE_ANGELS_KISS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS", getProstitute()));
			if(inApartment) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS_END_APARTMENT", getProstitute()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS_END", getProstitute()));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Knowing that you can now find [npc.name] at Angel's Kiss, you set off on your way once again...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode PROSTITUTE_REMOVAL_THREATENED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().getProstituteFine()), true);
			if(inApartment) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_THREATENED_APARTMENT", getProstitute()));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_THREATENED", getProstitute()));
			}
			Main.game.banishNPC(getProstitute());
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Now that you've cleaned up this area of the city, you can continue on your way...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode PROSTITUTE_REMOVAL_PAID = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			double rnd = Math.random();
			AbstractWeapon weapon;
			if(rnd<0.60f) {
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_enbaton_enbaton"); // 60% chance of getting a baton
			} else if(rnd<0.30f){
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_pbweap_pbpistol"); // 30% chance of getting a pistol
			} else {
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_taser_taser"); // 10% chance of getting a taser
			}
			UtilText.addSpecialParsingString(weapon.getName(true, true), true);
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().getProstituteFine()), false);
			if(inApartment) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_PAID_APARTMENT", getProstitute()));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_PAID", getProstitute()));
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().getProstituteFine()));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(weapon, false));
			Main.game.banishNPC(getProstitute());
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Feeling happy to have been able to help out one of Dominion's troubled citizens, you continue on your way...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode PROSTITUTE_FIGHT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().incrementKarma(-25);
		}
		@Override
		public String getContent() {
			if(inApartment) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_FIGHT_APARTMENT", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_FIGHT", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Teach this insolent whore a lesson!", getProstitute());
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getProstitute(), "You have defeated [npc.name]!");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY", getProstitute());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave",
						"Leave [npc.name] and carry on your way."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]",
						Main.game.getDefaultDialogue(false)) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Have some fun",
						UtilText.parse(getProstitute(), "It's clear that [npc.name] wants you to fuck [npc.herHim], so maybe you should give [npc.herHim] what [npc.she] wants..."),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX", getProstitute()));
				
			} else if (index == 3) {
				return new ResponseSex("Gentle fun",
						UtilText.parse(getProstitute(), "It's clear that [npc.name] wants you to fuck [npc.herHim], so maybe you should give [npc.herHim] what [npc.she] wants..."),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
								null, null,
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getProstitute()));
				
			} else if (index == 4) {
				return new ResponseSex("Rough fun",
						UtilText.parse(getProstitute(), "It's clear that [npc.name] wants you to fuck [npc.herHim], so maybe you should give [npc.herHim] what [npc.she] wants..."),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
								null, null,
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getProstitute()));
				
			} else if (index == 5) {
				return new ResponseSex("Submit",
						UtilText.parse(getProstitute(), "You feel bad for having beaten [npc.name]. Perhaps submitting to [npc.herHim] and letting [npc.herHim] dominantly fuck you would make [npc.herHim] feel better?"),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getProstitute()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SUBMIT", getProstitute()));
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory",
						UtilText.parse(getProstitute(), "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")){
					@Override
					public void effects() {
						Main.mainController.openInventory(getProstitute(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 8 && getProstitute().isAbleToSelfTransform()) {
				return new Response("Transform [npc.herHim]",
						"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getProstitute());
					}
				};
				
			} else if (index == 9 && getProstitute().isAbleToSelfTransform()) {
				return new Response("Quick transformations",
						"As [npc.she] is able to transform [npc.herself], you have a few quick ideas in mind..."
								+ "(You'll return to these options once finished transforming [npc.herHim].)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getProstitute(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 11 && Main.game.getPlayer().hasCompanions()) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();
				
				if(!companion.isAttractedTo(getProstitute())) {
					return new Response(UtilText.parse(companion, "Threesome"),
							UtilText.parse(getProstitute(), "[com.Name] isn't attracted to [npc.name], so wouldn't be willing to have sex with [npc.herHim]!"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Threesome"),
							UtilText.parse(getProstitute(), "Have dominant sex with [npc.name], and get [com.name] to join in with the fun."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getProstitute()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_THREESOME", getProstitute()));
				}
				
			} else if (index == 12 && Main.game.getPlayer().hasCompanions()) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();

				if(!companion.isAttractedTo(getProstitute())) {
					return new Response("Give to [com.name]",
							UtilText.parse(getProstitute(), "[com.Name] isn't attracted to [npc.name], so wouldn't be willing to have sex with [npc.herHim]!"),
							null);
					
				} else {
					return new ResponseSex("Give to [com.name]",
							UtilText.parse(getProstitute(), "Tell [com.name] that [com.she] can have some fun with [npc.name] while you watch."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getProstitute()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getProstitute()));
				}
				
			} else if (index == 13 && Main.game.getPlayer().hasCompanions() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();

				if(!getProstitute().isAttractedTo(companion)) {
					return new Response("Offer [com.name]",
							UtilText.parse(getProstitute(), "[com.Name] isn't attracted to [npc.name], so wouldn't be willing to have sex with [npc.herHim]!"),
							null);
					
				} else if(!companion.isAttractedTo(getProstitute()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("Offer [com.name]",
							UtilText.parse(getProstitute(), "You can tell that [com.name] isn't at all interested in having sex with [npc.name], and you can't force [com.herHim] to do so..."),
							null);
					
				} else {
					return new ResponseSex("Offer [com.name]",
							UtilText.parse(getProstitute(), "Tell [npc.name] that [npc.she] can use [com.name]."),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getProstitute())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getProstitute()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getProstitute(), "You have been defeated by [npc.name]!");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT", getProstitute());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getProstitute().isAttractedTo(Main.game.getPlayer()) && getProstitute().isWillingToRape()) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX", getProstitute()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							UtilText.parse(getProstitute(), "[npc.Name] forces [npc.herself] on you..."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getProstitute()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							UtilText.parse(getProstitute(), "[npc.Name] forces [npc.herself] on you..."),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getProstitute()));
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue",
							"Carry on your way."
									+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]",
							AFTER_COMBAT_DEFEAT){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							Main.game.banishNPC(getProstitute());
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PAID = new DialogueNode("Step back", "", true) {
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}
		@Override
		public String getContent() {
			if(inApartment) {
				if(Main.game.getPlayer().hasCompanions()) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
						if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_THREESOME", getProstitute());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_COMPANION", getProstitute());
						}
					}
				}
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID", getProstitute());
				
			} else {
				if(Main.game.getPlayer().hasCompanions()) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
						if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_THREESOME", getProstitute());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_COMPANION", getProstitute());
						}
					}
				}
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						UtilText.parse(getProstitute(), "Leave [npc.name] behind and continue on your way."),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(inApartment) {
							if(Main.game.getPlayer().hasCompanions() && Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_LEAVE_COMPANION", getProstitute()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_LEAVE", getProstitute()));
							}
							
						} else {
							if(Main.game.getPlayer().hasCompanions() && Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_LEAVE_COMPANION", getProstitute()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_LEAVE", getProstitute()));
							}
						}
					}
				};
				
			} else if(index >= 4) {
				return ALLEY_PROSTITUTE.getResponse(responseTab, index);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Step back", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(getProstitute(), "Now that you've had your fun, you can step back and leave [npc.name] to recover.");
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_THREESOME", getProstitute());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_COMPANION", getProstitute());
					}
				}
			}
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY", getProstitute());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave",
						"Leave [npc.name] and carry on your way."
								+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_LEAVE", getProstitute()));
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getProstitute(), InventoryInteraction.FULL_MANAGEMENT);
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
			if(inApartment) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT_APARTMENT", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT", getProstitute());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"Carry on your way."
							+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT_LEAVE", getProstitute()));
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
