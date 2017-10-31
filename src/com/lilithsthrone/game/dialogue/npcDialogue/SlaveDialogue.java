package com.lilithsthrone.game.dialogue.npcDialogue;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.GenericDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMDomStanding;
import com.lilithsthrone.game.sex.managers.universal.SMSubStanding;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.85
 * @version 0.1.86
 * @author Innoxia
 */
public class SlaveDialogue {
	
	private static NPC slave() {
		return Main.game.getActiveNPC();
	}
	
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
							+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
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
							+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
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
						+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
							?"You can tell that [npc.she]'s attracted to you..."
							:"[npc.She] doesn't show any interest in being attracted to you.")
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int index) {
			/*
			 * flagSlaveBackground, flagSlaveSmallTalk,
						flagSlaveEncourage, flagSlaveHug, flagSlavePettings,
						flagSlaveInspect, flagSlaveSpanking, flagSlaveMolest;
			 */
			
			if (index == 1) {
				if(!slave().flagSlaveBackground) {
					return new Response("Background", "Ask [npc.name] about [npc.her] past life.", SLAVE_PROGRESSION) {
						@Override
						public void effects() {
							slave().flagSlaveBackground = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 3));
						}
					};
				} else {
					return new Response("Background", "You've already talked with [npc.name] about [npc.her] past life today.", null);
				}
				
			} else if (index == 2) {
				if(!slave().flagSlaveSmallTalk) {
					return new Response("Small talk", "Chat about this and that with [npc.name].", SLAVE_MINOR) {
						@Override
						public void effects() {
							slave().flagSlaveSmallTalk = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 2));
						}
					};
				} else {
					return new Response("Small talk", "You've already spent time talking with [npc.name] today.", null);
				}
				
			} else if (index == 6) {
				if(!slave().flagSlaveEncourage) {
					return new Response("Encourage", "Encourage [npc.name] by telling [npc.her] how good [npc.she] is.", SLAVE_ENCOURAGE) {
						@Override
						public void effects() {
							slave().flagSlaveEncourage = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-2));
						}
					};
				} else {
					return new Response("Encourage", "You've encouraged [npc.name] today.", null);
				}
				
			} else if (index == 7) {
				if(!slave().flagSlaveHug) {
					return new Response("Hug", "Hug [npc.name].", SLAVE_HUG) {
						@Override
						public void effects() {
							slave().flagSlaveHug = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-2));
						}
					};
				} else {
					return new Response("Hug", "You've already spent time hugging [npc.name] today.", null);
				}
				
			} else if (index == 8) {
				if(!slave().flagSlavePettings) {
					return new Response("Pettings", "Give [npc.name] some loving pettings.", SLAVE_PETTINGS) {
						@Override
						public void effects() {
							slave().flagSlavePettings = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-2));
						}
					};
				} else {
					return new Response("Pettings", "You've already spent time petting [npc.name] today.", null);
				}
				
			} else if (index == 11) {
				if(!slave().flagSlaveInspect) {
					return new Response("Inspect", "Make [npc.name] strip and parade around [npc.her] room for your inspection.", SLAVE_INSPECT) {
						@Override
						public void effects() {
							slave().flagSlaveInspect = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -5));
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(5));
						}
					};
				} else {
					return new Response("Inspect", "You've already inspected [npc.name] today.", null);
				}
				
			} else if (index == 12) {
				if(!slave().flagSlaveSpanking) {
					return new Response("Spanking", "Bend [npc.name] over your knee and give [npc.herHim] a rough spanking.", SLAVE_SPANKING) {
						@Override
						public void effects() {
							slave().flagSlaveSpanking = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -5));
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(10));
						}
					};
				} else {
					return new Response("Spanking", "You've already spanked [npc.name] today.", null);
				}
				
			} else if (index == 13) {
				if(!slave().flagSlaveMolest) {
					return new Response("Molest", "Make [npc.name] sit still as you grope and molest [npc.her] body.", SLAVE_MOLEST) {
						@Override
						public void effects() {
							slave().flagSlaveMolest = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -10));
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(5));
						}
					};
				} else {
					return new Response("Molest", "You've already molested [npc.name] today.", null);
				}
				
			} else if (index == 5) {
				if(Main.game.isNonConEnabled() && !Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
					return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
							AFTER_SEX,
							false, false, Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX,
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
							true, false, Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX,
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
	
	private static String getFooterText() {
		return "<p>"
					+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
						?"[npc.She] keeps glancing at your body..."
						:"[npc.She] doesn't show any interest in being attracted to you.")
				+ "</p>";
	}
	
	public static final DialogueNodeOld SLAVE_PROGRESSION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			return 
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<i>The slave interactions are currently placeholders! I'll get all this added soon!</i>"
					+ "</div>"
					+ "<p>"
						+ "This will be the main progression option, where you'll talk about [npc.her] old life, [npc.her] hopes for the future, etc."
					+ "</p>"
					+getFooterText();
		}

		@Override
		public Response getResponse(int index) {
			return SLAVE_START.getResponse(index);
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
			return 
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<i>The slave interactions are currently placeholders! I'll get all this added soon!</i>"
					+ "</div>"
					+ "<p>"
						+ "This will be a minor small-talk action, which will be a generated section of dialogue based on what's been happening to [npc.herHim] lately."
					+ "</p>"
					+getFooterText();
		}

		@Override
		public Response getResponse(int index) {
			return SLAVE_START.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_ENCOURAGE = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Encouraging [npc.Name]";
		}

		@Override
		public String getContent() {
			return 
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<i>The slave interactions are currently placeholders! I'll get all this added soon!</i>"
					+ "</div>"
					+ "<p>"
						+ "This will be a generic affection-boost option, where you'll talk about [npc.name]'s current assignment."
					+ "</p>"
					+getFooterText();
		}

		@Override
		public Response getResponse(int index) {
			return SLAVE_START.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_HUG = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Hugging [npc.Name]";
		}

		@Override
		public String getContent() {
			return 
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<i>The slave interactions are currently placeholders! I'll get all this added soon!</i>"
					+ "</div>"
					+ "<p>"
						+ "This will be a generic affection-boost action, which will have a chance to backfire if the slave doesn't like you enough already."
					+ "</p>"
					+getFooterText();
		}

		@Override
		public Response getResponse(int index) {
			return SLAVE_START.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_PETTINGS = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Petting [npc.Name]";
		}

		@Override
		public String getContent() {
			return 
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<i>The slave interactions are currently placeholders! I'll get all this added soon!</i>"
					+ "</div>"
					+ "<p>"
						+ "This will be a large affection-boost action, which will have a significant chance to backfire if the slave doesn't like you enough already."
					+ "</p>"
					+getFooterText();
		}

		@Override
		public Response getResponse(int index) {
			return SLAVE_START.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_INSPECT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Petting [npc.Name]";
		}

		@Override
		public String getContent() {
			return 
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<i>The slave interactions are currently placeholders! I'll get all this added soon!</i>"
					+ "</div>"
					+ "<p>"
						+ "This will be an obedience-training action, which will have variations based on your slave's affection and obedience."
					+ "</p>"
					+getFooterText();
		}

		@Override
		public Response getResponse(int index) {
			return SLAVE_START.getResponse(index);
		}
	};
	
	
	public static final DialogueNodeOld SLAVE_SPANKING = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Spanking [npc.Name]";
		}

		@Override
		public String getContent() {
			return 
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<i>The slave interactions are currently placeholders! I'll get all this added soon!</i>"
					+ "</div>"
					+ "<p>"
						+ "This will be a large obedience-training action, which will have variations based on your slave's affection and obedience."
					+ "</p>"
					+getFooterText();
		}

		@Override
		public Response getResponse(int index) {
			return SLAVE_START.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_MOLEST = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Molesting [npc.Name]";
		}

		@Override
		public String getContent() {
			return 
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<i>The slave interactions are currently placeholders! I'll get all this added soon!</i>"
					+ "</div>"
					+ "<p>"
						+ "This will be another large obedience-training action, which will have variations based on your slave's affection and obedience."
					+ "</p>"
					+getFooterText();
		}

		@Override
		public Response getResponse(int index) {
			return SLAVE_START.getResponse(index);
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
			if(!Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
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
	
	public static final DialogueNodeOld SLAVE_USES_YOU = new DialogueNodeOld("Ambushed", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "[npc.Name] ambushes you in the corridor!";
		}

		@Override
		public String getContent() {
				return"<div class='container-full-width' style='text-align:center;'>"
							+ "<i>This scene is a very rough placeholder! I'll get a proper version added soon!</i>"
						+ "</div>"
						+"<p>"
							+ "As you walk down one of the empty corridors, a figure suddenly jumps out at you!"
							+ " Taking you completely by surprise, you're unable to react, and find yourself being pinned up against a nearby wall as your assailant growls into your ear,"
							+ " [npc.speech(Who's the bitch now?!)]"
						+ "</p>"
						+ "<p>"
							+ "Looking up into [npc.name]'s grinning face as [npc.she] pulls you forward into [npc.her] [npc.arms], you let out a surprised little cry,"
							+ " [pc.speech(I-I'm your [pc.master]! You can't d-)]"
						+ "</p>"
						+ "<p>"
							+ "Your words are cut off as [npc.name] clasps a [npc.hand] over your mouth,"
							+ " [npc.speech(No. Right now, you're no better than a slave yourself!)]"
						+ "</p>"
						+ "<p>"
							+ "Being caught totally unawares, you're in no position to fight back, and are at the mercy of your supposed 'slave'..."
						+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						SLAVE_USES_YOU_POST_SEX,
						false, false, Main.game.getActiveNPC(), new SMSubStanding(), SLAVE_USES_YOU_POST_SEX,
						"<p>"
							+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally pulling away."
							+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
						+ "</p>");
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						SLAVE_USES_YOU_POST_SEX,
						false, false, Main.game.getActiveNPC(), new SMSubStanding(), SLAVE_USES_YOU_POST_SEX,
						"<p>"
							+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
							+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
						+ "</p>") {
					@Override
					public void effects() {
						sexPacePlayer = (SexPace.SUB_EAGER);
					}
				};
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						SLAVE_USES_YOU_POST_SEX,
						false, false, Main.game.getActiveNPC(), new SMSubStanding(), SLAVE_USES_YOU_POST_SEX,
						"<p>"
							+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
							+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
							+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
						+ "</p>") {
					@Override
					public void effects() {
						sexPacePlayer = (SexPace.SUB_RESISTING);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_USES_YOU_POST_SEX = new DialogueNodeOld("Used", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that [npc.she]'s had [npc.her] fun, [npc.name] starts to step back...";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smirk cross [npc.her] face,"
						+ " [npc.speech(That was fun, <i>[npc.pcName]</i>!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way.", SLAVE_USES_YOU_POST_SEX) {
					@Override
					public DialogueNodeOld getNextDialogue(){
						return GenericDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setLocation(Main.game.getActiveNPC().getHomeWorldLocation(), Main.game.getActiveNPC().getHomeLocation(), false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	

}
