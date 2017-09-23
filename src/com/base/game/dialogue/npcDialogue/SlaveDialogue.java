package com.base.game.dialogue.npcDialogue;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.GenericDialogue;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.Sex;
import com.base.game.sex.managers.universal.SMDomStanding;
import com.base.main.Main;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class SlaveDialogue {
	
	public static final DialogueNodeOld SLAVE_START = new DialogueNodeOld("", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isVisiblyPregnant()){
				// Pregnant encounters:
				if(!Main.game.getActiveNPC().isReactedToPregnancy()) {
					return "<p>"
								+ "<i>All of the slave-interaction dialogue is currently placeholder!</i>"
							+ "</p>"
							+ "<p>"
								+ "You see that [npc.she]'s sporting a round belly, and [npc.she] absent-mindedly strokes [npc.her] swollen bump as [npc.she] looks up at you,"
								+ " [npc.speech(Y-You got me pregnant...)]"
							+ "</p>"
							+ "<p>"
							+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
								?"You can tell that [npc.she]'s attracted to you..."
								:"[npc.She] doesn't show any interest in being attracted to you.")
							+ "</p>";
				
				} else {
					return "<p>"
								+ "<i>All of the slave-interaction dialogue is currently placeholder!</i>"
							+ "</p>"
							+ "<p>"
								+ "[npc.She]'s still sporting a round belly, and [npc.she] absent-mindedly strokes [npc.her] swollen bump as [npc.she] walks up to you,"
								+ " [npc.speech(I'm looking after our children...)]"
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
							+ "<i>All of the slave-interaction dialogue is currently placeholder!</i>"
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] greets you, [npc.speech(Hello [npc.pcName]...)]"
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
				return new Response("Background", "Ask [npc.name] about [npc.her] past life.", SLAVE_PROGRESSION);
				
			} else if (index == 2) {
				return new Response("Hug", "Hug [npc.name].", SLAVE_PHYSICAL) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 3));
					}
				};
				
			} else if (index == 3) {
				return new Response("Small talk", "Chat about this and that with [npc.name].", SLAVE_MINOR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 1));
					}
				};
				
			} else if (index == 4) {
				return new Response("Gift", "Give [npc.name] a gift.", SLAVE_GIFT);
				
			} else if (index == 5) {
				return new Response("Punish", "Punish [npc.name].", SLAVE_PUNISHMENT);
				
			} else if (index == 6) {
				if(Main.game.isNonConEnabled() && !Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()) {
					return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
							AFTER_SEX,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX,
							"<p>"
								+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
								+ " [npc.She] desperately tries to push you away, [npc.moaning]"
								+ " [npc.speech(No! Stop!)]"
							+ "</p>") {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -15));
						}
					};
					
				} else {
					return new ResponseSex("Sex", "Have sex with [npc.name].", 
							AFTER_SEX,
							Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX,
							"<p>"
								+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
								+ " [npc.She] desperately leans into you, [npc.moaning]"
								+ " [npc.speech(~Mmm!~ Yes!)]"
							+ "</p>") {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
			} else if (index == 0) {
				return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.her] some other time.", SLAVE_START) {
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
	
	public static final DialogueNodeOld SLAVE_PROGRESSION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the slave-interaction dialogue is currently placeholder!</i>"
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
				return SLAVE_START.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_PHYSICAL = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the slave-interaction dialogue is currently placeholder!</i>"
					+ "</p>"
					+ "<p>"
						+ "This will be the 'physical' action, and will change based on [npc.name]'s affection."
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
				return SLAVE_START.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_MINOR = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the slave-interaction dialogue is currently placeholder!</i>"
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
				return SLAVE_START.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_GIFT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the slave-interaction dialogue is currently placeholder!</i>"
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
				return SLAVE_START.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_PUNISHMENT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "<i>All of the slave-interaction dialogue is currently placeholder!</i>"
					+ "</p>"
					+ "<p>"
						+ "This will be a way to increase obedience at the cost of affection."
					+ "</p>"
					+ "<p>"
					+ (Main.game.getActiveNPC().isWantsToHaveSexWithPlayer()
						?"[npc.She] keeps glancing at your body..."
						:"[npc.She] doesn't show any interest in being attracted to you.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 5) {
				return new Response("Punish", "Punish [npc.name].", null);
				
			} else {
				return SLAVE_START.getResponse(index);
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Step back", "", true) {
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
						+ "</p>");
				
			} else {
				if(Sex.getNumberOfPartnerOrgasms() >= 1) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfPartnerOrgasms() > 1?"s":"")+"."
								+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
							+ "</p>");
				} else {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
								+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! I'm still horny!)]"
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Decide what to do next.", SLAVE_START);
				
			} else {
				return null;
			}
		}
	};
}
