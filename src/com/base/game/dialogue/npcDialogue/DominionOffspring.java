package com.base.game.dialogue.npcDialogue;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.GenericDialogue;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseCombat;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.utils.InventoryInteraction;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.managers.universal.SMDomStanding;
import com.base.game.sex.managers.universal.SMSubStanding;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.world.places.Dominion;

public class DominionOffspring {
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER = new DialogueNodeOld("A familiar face", "You encounter a certain special someone in the alleyway.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "A familiar face";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().getFoughtPlayerCount()>0) {
				
				if(Main.game.getActiveNPC().isVisiblyPregnant()){
					// Pregnant encounters:
					if(!Main.game.getActiveNPC().isReactedToPregnancy()) {
						return "<p>"
									+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
								+ "</p>"
								+ "<p>"
									+ "You encounter your [npc.daughter] prowling around in the alleyway once again."
									+ " [npc.She]'s a <b style='color:"+Femininity.valueOf(Main.game.getActiveNPC().getFemininityValue()).getColour().toWebHexString()+";'>"
									+ Femininity.getFemininityName(Main.game.getActiveNPC().getFemininityValue(), true)+"</b>"
									+ " <b style='color:"+Main.game.getActiveNPC().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getActiveNPC().getRaceStage().getName()+"</b>"
									+ " <b style='color:"+Main.game.getActiveNPC().getRace().getColour().toWebHexString()+";'>[npc.race]</b>."
								+ "</p>"
								+ "<p>"
									+ "You see that [npc.she]'s sporting a round belly, and [npc.she] absent-mindedly strokes [npc.her] swollen bump as [npc.she] walks up to you,"
									+ " [npc.speech(Hi [npc.pcName]! Y-You got me pregnant...)]"
								+ "</p>"
								+ "<p>"
								+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
									?"You can tell that [npc.she]'s attracted to you..."
									:"[npc.She] doesn't show any interest in being attracted to you.")
								+ "</p>";
					
					} else {
						return "<p>"
									+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
								+ "</p>"
								+ "<p>"
									+ "You encounter your [npc.daughter] prowling around in the alleyway once again."
									+ " [npc.She]'s a <b style='color:"+Femininity.valueOf(Main.game.getActiveNPC().getFemininityValue()).getColour().toWebHexString()+";'>"
									+ Femininity.getFemininityName(Main.game.getActiveNPC().getFemininityValue(), true)+"</b>"
									+ " <b style='color:"+Main.game.getActiveNPC().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getActiveNPC().getRaceStage().getName()+"</b>"
									+ " <b style='color:"+Main.game.getActiveNPC().getRace().getColour().toWebHexString()+";'>[npc.race]</b>."
								+ "</p>"
								+ "<p>"
									+ "[npc.She]'s still sporting a round belly, and [npc.she] absent-mindedly strokes [npc.her] swollen bump as [npc.she] walks up to you,"
									+ " [npc.speech(Hi [npc.pcName]! I'm looking after our children...)]"
								+ "</p>"
								+ "<p>"
								+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
									?"You can tell that [npc.she]'s attracted to you..."
									:"[npc.She] doesn't show any interest in being attracted to you.")
								+ "</p>";
					}
					
				} else {
					// Standard repeat encounter:
					return "<p>"
									+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
							+ "</p>"
							+ "<p>"
								+ "You encounter your [npc.daughter] prowling around in the alleyway once again."
								+ " [npc.She]'s a <b style='color:"+Femininity.valueOf(Main.game.getActiveNPC().getFemininityValue()).getColour().toWebHexString()+";'>"
								+ Femininity.getFemininityName(Main.game.getActiveNPC().getFemininityValue(), true)+"</b>"
								+ " <b style='color:"+Main.game.getActiveNPC().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getActiveNPC().getRaceStage().getName()+"</b>"
								+ " <b style='color:"+Main.game.getActiveNPC().getRace().getColour().toWebHexString()+";'>[npc.race]</b>."
							+ "</p>"
							+ "<p>"
								+ "[npc.Name] greets you, [npc.speech(Hi [npc.pcName]! I-I wasn't trying to rob people or anything!)]"
							+ "</p>"
							+ "<p>"
							+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
								?"You can tell that [npc.she]'s attracted to you..."
								:"[npc.She] doesn't show any interest in being attracted to you.")
							+ "</p>";
				}
				
			} else {
				return "<p>"
							+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
						+ "</p>"
						+ "<p>"
							+ "You encounter your [npc.daughter] prowling around in the alleyway."
							+ " [npc.She]'s a <b style='color:"+Femininity.valueOf(Main.game.getActiveNPC().getFemininityValue()).getColour().toWebHexString()+";'>"
							+ Femininity.getFemininityName(Main.game.getActiveNPC().getFemininityValue(), true)+"</b>"
							+ " <b style='color:"+Main.game.getActiveNPC().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getActiveNPC().getRaceStage().getName()+"</b>"
							+ " <b style='color:"+Main.game.getActiveNPC().getRace().getColour().toWebHexString()+";'>[npc.race]</b>."
						+ "</p>"
						+ "<p>"
							+ "As you see each other, you recognise your relationship, [npc.speech([npc.pcName]?! I-Is that you?!)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.She] goes on to introduce [npc.herself] as [npc.name], and looks like [npc.she] wants to talk to you."
						+ "</p>"
						+ "<p>"
						+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
							?"You can tell that [npc.she]'s attracted to you..."
							:"[npc.She] doesn't show any interest in being attracted to you.")
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Talk", "Talk with [npc.name].", OFFSPRING_ENCOUNTER_TALK);
				
			} else if (index == 2) {
				return new ResponseCombat("Attack", "Decide to show [npc.name] [npc.her] true place in this family!", OFFSPRING_ENCOUNTER, Main.game.getActiveNPC(),
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SADIST)), CorruptionLevel.FIVE_CORRUPT,
						null, null, null);
				
			} else if (index == 0) {
				return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", OFFSPRING_ENCOUNTER) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return GenericDialogue.getDefaultDialogueNoEncounter();
						}
					};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_TALK = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
					+ "</p>"
					+ "<p>"
						+ "You say that you want to talk to [npc.name], and, eager to get to know you, [npc.she] leads to you to a (seedy) pub [npc.she] likes to visit."
					+ "</p>"
					+ "<p>"
						+ "You buy drinks, and start talking with [npc.herHim]."
					+ "</p>"
					+ "<p>"
					+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
						?"[npc.She] keeps glancing at your body..."
						:"[npc.She] doesn't show any interest in being attracted to you.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Background", "Ask [npc.name] how [npc.she] makes a living.", OFFSPRING_ENCOUNTER_PROGRESSION);
				
			} else if (index == 2) {
				return new Response("Hug", "Hug [npc.name].", OFFSPRING_ENCOUNTER_PHYSICAL) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 3));
					}
				};
				
			} else if (index == 3) {
				return new Response("Small talk", "Chat about this and that with [npc.name].", OFFSPRING_ENCOUNTER_MINOR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 1));
					}
				};
				
			} else if (index == 4) {
				return new Response("Gift", "Give [npc.name] a gift.", OFFSPRING_ENCOUNTER_GIFT);
				
			} else if (index == 5) {
				return new Response("Ask for pet name", "Ask [npc.name] to call you by a different name.", OFFSPRING_ENCOUNTER_CHOOSE_NAME);
				
			} else if (index == 6) {
				if(Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()) {
					return new ResponseSex("Sex", "Ask [npc.name] if there's a quiet spot nearby...", 
							AFTER_SEX_VICTORY,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
							"<p>"
								+ "[npc.Name] leads you out a back door and into a deserted alleyway,"
								+ " [npc.speech([npc.pcName]! I need you!)]"
							+ "</p>"
							+ "<p>"
								+ "Grinning, you step forwards and pull your [npc.daughter] into a passionate kiss..."
							+ "</p>") {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 10));
						}
					};
				} else {
					return new Response("Sex", "[npc.Name] s clearly not interested in having sex with you.", null);
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.her] some other time.", OFFSPRING_ENCOUNTER) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return GenericDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_PROGRESSION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
					+ "</p>"
					+ "<p>"
						+ "This will be the main progression option, and will change based on [npc.name]'s affection."
					+ "</p>"
					+ "<p>"
					+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
						?"[npc.She] keeps glancing at your body..."
						:"[npc.She] doesn't show any interest in being attracted to you.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Background", "Ask [npc.name] how [npc.she] makes a living.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALK.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_PHYSICAL = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
					+ "</p>"
					+ "<p>"
						+ "This will be the 'physical' action, and will change based on [npc.name]'s affection. I'm going to use this as a way to add/remove fetishes from [npc.name]."
					+ "</p>"
					+ "<p>"
					+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
						?"[npc.She] keeps glancing at your body..."
						:"[npc.She] doesn't show any interest in being attracted to you.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 2) {
				return new Response("Hug", "Hug [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALK.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_MINOR = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
					+ "</p>"
					+ "<p>"
						+ "This will be the 'minor affection boost' action, and will change based on [npc.name]'s affection, as well as having a few different varieties."
					+ "</p>"
					+ "<p>"
					+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
						?"[npc.She] keeps glancing at your body..."
						:"[npc.She] doesn't show any interest in being attracted to you.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 3) {
				return new Response("Small talk", "Chat about this and that with [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALK.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_GIFT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
					+ "</p>"
					+ "<p>"
						+ "This will be the 'major affection boost' option, and will either cost Flames or an item in your inventory to use [npc.name]'s affection."
					+ "</p>"
					+ "<p>"
					+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
						?"[npc.She] keeps glancing at your body..."
						:"[npc.She] doesn't show any interest in being attracted to you.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 4) {
				return new Response("Gift", "Give [npc.name] a gift.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALK.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_CHOOSE_NAME = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the incest encounter dialogue is currently placeholder!</i>"
					+ "</p>"
					+ "<p>"
						+ "You ask [npc.name]'s to call you by a different name."
					+ "</p>"
					+ "<p>"
					+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
						?"[npc.She] keeps glancing at your body..."
						:"[npc.She] doesn't show any interest in being attracted to you.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("[pc.Name]", "Ask [npc.name] to call you by your name.", OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPlayerPetName(Main.game.getPlayer().getName());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[npc.Name] blushes, but does as [npc.she]'s told, [npc.speech(Yes [npc.pcName]...)]"
								+ "</p>");
					}
				};
				
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isFeminine()?"Mom":"Dad"), "Ask [npc.name] to call you "+(Main.game.getPlayer().isFeminine()?"Mom":"Dad")+".", OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPlayerPetName((Main.game.getPlayer().isFeminine()?"Mom":"Dad"));
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[npc.Name] blushes, but does as [npc.she]'s told, [npc.speech(Yes [npc.pcName]...)]"
								+ "</p>");
					}
				};
				
			} else if (index == 3) {
				return new Response((Main.game.getPlayer().isFeminine()?"Mommy":"Daddy"), "Ask [npc.name] to call you "+(Main.game.getPlayer().isFeminine()?"Mommy":"Daddy")+".", OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPlayerPetName((Main.game.getPlayer().isFeminine()?"Mommy":"Daddy"));
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[npc.Name] blushes, but does as [npc.she]'s told, [npc.speech(Yes [npc.pcName]...)]"
								+ "</p>");
					}
				};
				
			} else if (index == 4) {
				return new Response((Main.game.getPlayer().isFeminine()?"Mistress":"Master"), "Ask [npc.name] to call you "+(Main.game.getPlayer().isFeminine()?"Mistress":"Master")+".", OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPlayerPetName((Main.game.getPlayer().isFeminine()?"Mistress":"Master"));
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[npc.Name] blushes, but does as [npc.she]'s told, [npc.speech(Yes [npc.pcName]...)]"
								+ "</p>");
					}
				};
				
			} else if (index == 5) {
				return new Response("Slut", "Ask [npc.name] to call you Slut.", OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPlayerPetName("Slut");
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[npc.Name] blushes, but does as [npc.she]'s told, [npc.speech(Yes [npc.pcName]...)]"
								+ "</p>");
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Tell [npc.name] that you're happy being called [npc.pcName].", OFFSPRING_ENCOUNTER_TALK);
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isWantsToHaveSexWithPlayer() || !Main.game.isNonConEnabled()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and you see that [npc.she]'s still got that same hungry look in [npc.her] eyes, despite [npc.her] defeat."
							+ " [npc.She] reaches down to [npc.her] crotch and starts stroking [npc.herself], making pitiful little whining noises as [npc.she] squirms on the floor."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Please [npc.pcName]! Fuck me!)], [npc.she] pleads, biting [npc.her] [npc.lip] as [npc.she] continues touching [npc.herself]."
						+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and you see that there's a desperate look of regret in [npc.her] [npc.eyes]."
							+ " Making pitiful little whining noises, [npc.she] tries to shuffle away from you, clearly worried about what your intentions are."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Please [npc.pcName]! Don't! Leave me alone!)], [npc.she] pleads with tears in [npc.her] [npc.eyes]."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if(Main.game.getActiveNPC().isWantsToHaveSexWithPlayer() || !Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", null){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return GenericDialogue.getDefaultDialogueNoEncounter();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Have some fun",
							"Well, [npc.she] <i>is</i> asking for it!",
							AFTER_SEX_VICTORY,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY);
					
				} else if (index == 3) {
					return new ResponseSex("Have some gentle fun",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							AFTER_SEX_VICTORY,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY) {
						@Override
						public void effects() {
							sexPacePlayer = (SexPace.DOM_GENTLE);
						}
					};
					
				} else if (index == 4) {
					return new ResponseSex("Have some rough fun",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							AFTER_SEX_VICTORY,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY) {
						@Override
						public void effects() {
							sexPacePlayer = (SexPace.DOM_ROUGH);
						}
					};
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now...</br>"
								+ "Perhaps it would be best to let [npc.name] choose what to do next?",
							AFTER_SEX_DEFEAT,
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
							Main.game.getActiveNPC(), new SMSubStanding(), AFTER_SEX_DEFEAT,
							"<p>"
								+ "You apologise to [npc.name] and give [npc.herHim] a hug."
								+ " [npc.She] happily wraps [npc.her] [npc.arms] around you, but says [npc.she] won't forgive you unless you let [npc.herHim] have some fun."
							+ "</p>"
							+ "<p>"
								+ "You agree to do as [npc.she] says."
								+ " [npc.Name] starts kissing you, [npc.speech(Yes [npc.pcName]! You'll be good now, won't you?)]"
							+ "</p>");
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 10 && Main.game.getPlayer().getLocationPlace().getPlaceType() == Dominion.CITY_BACK_ALLEYS) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							AFTER_COMBAT_VICTORY){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return GenericDialogue.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.removeNPC(Main.game.getActiveNPC());
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", null){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return GenericDialogue.getDefaultDialogueNoEncounter();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...", AFTER_SEX_VICTORY,
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SADIST)), null, CorruptionLevel.FOUR_LUSTFUL,
							null, null, null,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] firmly in your embrace."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! No! Please!)]"
							+ "</p>");
					
				} else if (index == 3) {
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)", AFTER_SEX_VICTORY,
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SADIST)), null, CorruptionLevel.FOUR_LUSTFUL,
							null, null, null,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you take hold of [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start pressing yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] in your embrace."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! No! Please!)]"
							+ "</p>") {
						@Override
						public void effects() {
							sexPacePlayer = (SexPace.DOM_GENTLE);
						}
					};
					
				} else if (index == 4) {
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)", AFTER_SEX_VICTORY,
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SADIST)), null, CorruptionLevel.FOUR_LUSTFUL,
							null, null, null,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, roughly yanking [npc.herHim] to [npc.her] feet, you start forcefully grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you firmly hold [npc.herHim] in your embrace."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! No! Please!)]"
							+ "</p>") {
						@Override
						public void effects() {
							sexPacePlayer = (SexPace.DOM_ROUGH);
						}
					};
					
				} else if (index == 5) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 10 && Main.game.getPlayer().getLocationPlace().getPlaceType() == Dominion.CITY_BACK_ALLEYS) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							AFTER_COMBAT_VICTORY){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return GenericDialogue.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.removeNPC(Main.game.getActiveNPC());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) { //TODO
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Are you ok [npc.pcName]?!)] [npc.she] shouts, before leaning down to grab one of your [pc.arms]."
						+ "</p>"
						+ "<p>"
							+ "As you groan in the affirmative, [npc.name] lets out a sigh of relief."
							+ " Pulling you to your feet, [npc.name] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Well, if [npc.pcName] wants to play like this, that's fine with me!)] [npc.she] growls, before pulling you into a forceful kiss."
						+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Are you ok [npc.pcName]?!)] [npc.she] shouts, before leaning down to grab one of your [pc.arms]."
						+ "</p>"
						+ "<p>"
							+ "As you groan in the affirmative, [npc.name] lets out a sigh of relief."
							+ " Pulling you to your feet, [npc.name] pushes you against a nearby wall, before demanding that [npc.she] get some allowance money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] backs off."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Don't try this again [npc.pcName]!)] [npc.she] shouts, before turning around and running off."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if(Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()) {
				
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							AFTER_SEX_DEFEAT,
							Main.game.getActiveNPC(), new SMSubStanding(), AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Time for some fun [npc.pcName]!)]"
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							AFTER_SEX_DEFEAT,
							Main.game.getActiveNPC(), new SMSubStanding(), AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Time for some fun [npc.pcName]!)]"
							+ "</p>") {
						@Override
						public void effects() {
							sexPacePlayer = (SexPace.SUB_EAGER);
						}
					};
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							AFTER_SEX_DEFEAT,
							Main.game.getActiveNPC(), new SMSubStanding(), AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Time for some fun [npc.pcName]!)]"
							+ "</p>") {
						@Override
						public void effects() {
							sexPacePlayer = (SexPace.SUB_RESISTING);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue(){
							return GenericDialogue.getDefaultDialogueNoEncounter();
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(!Main.game.getActiveNPC().isWantsToHaveSexWithPlayer() && Main.game.isNonConEnabled()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a thankful sob as [npc.she] realises that you've finished."
							+ " Frantically gathering [npc.her] belongings, [npc.she] quickly runs off before you decide to do anything else."
						+ "</p>"
						+ "<p>"
							+ "Smirking as you watch [npc.herHim] run off around a corner, you set off and continue on your way."
						+ "</p>");
				
			} else {
				if(Sex.getNumberOfPartnerOrgasms() >= 1) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfPartnerOrgasms() > 1?"s":"")+"."
								+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Thanks [npc.pcName]!)]"
							+ "</p>"
							+ "<p>"
								+ "Leaving [npc.herHim] to recover by [npc.herself], you set off and continue on your way."
							+ "</p>");
				} else {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
								+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! I'm still horny!)]"
							+ "</p>"
							+ "<p>"
								+ "Leaving [npc.herHim] to get some pleasure by [npc.herself], you set off and continue on your way."
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return GenericDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 10 && Main.game.getPlayer().getLocationPlace().getPlaceType() == Dominion.CITY_BACK_ALLEYS) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return GenericDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.removeNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 30;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.name]'s dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smile cross [npc.her] face."
						+ " [npc.She] leans down and pats you on the head,"
						+ " [npc.speech(Good job [npc.pcName]! We should do this again some time!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return GenericDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENSLAVEMENT_DIALOGUE = new DialogueNodeOld("New Slave", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return ".";
		}

		@Override
		public String getContent() {//TODO
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "TODO</br>"
						+ "You clasp the collar around [npc.name]'s neck.</br>"
						+ "The arcane enchantment recognises [npc.herHim] as being a criminal, and, with a purple flash, <b>they're teleported to the 'Slave Administration' building in Slaver Alley, where they'll be waiting for you to pick them up</b>."
					+ "</p>");
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ENSLAVEMENT_DIALOGUE){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return GenericDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
