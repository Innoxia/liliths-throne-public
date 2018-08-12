package com.lilithsthrone.game.slavery.playerSlavery.events;

import java.util.ArrayList;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.slavery.playerSlavery.PlayerSlaveryEvent;
import com.lilithsthrone.game.slavery.playerSlavery.PlayerSlaveryRule;
import com.lilithsthrone.game.slavery.playerSlavery.rules.RulesSlaveryAlleyway;
import com.lilithsthrone.game.slavery.playerSlavery.rules.RulesSlaveryDefault;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

public class EventsSlaveryAlleyway {

	public static Value<String, AbstractItem> potion = null;
	public static Value<String, AbstractItem> kinkPotion = null;

	// ======
	//
	//
	// Scenes
	//
	//
	// ======

	public static final DialogueNodeOld ALLEYWAY_SLAVE_NORMAL_SEX_END = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 30;
		}

		@Override
		public String getDescription(){
			return "You're completely worn out from sex with [npc.name], and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smile cross [npc.her] face."
					+ "</p>"
					+ "<p>"
						+ "After that [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_NORMAL_SEX_END){
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld ALLEYWAY_SLAVE_PUNISHMENT_SEX_END = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public int getMinutesPassed(){
			return 30;
		}

		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.name]'s punishment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smile cross [npc.her] face."
						+ " [npc.She] leans down and says,"
						+ " [npc.speech(Hope you've learned your lesson, you <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_TF_POTION_DIALOGUE_START = new DialogueNodeOld("Punished", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getDescription() {
			return "You are been punished by [npc.name]!";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "You anxiously wait as [npc.name] is coming up with your punishment."
						+ " After a while, [npc.she] grins, walking over to you, pulling out a mysterious bottle from out of your sight."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(You [npc.playerSlaveryPerformanceRemark(negative)].)] [npc.she] says, before leaning down and pushing you to the ground,"
						+ " [npc.speech(And for that, you deserve a little bit of punishment!)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.She] holds the bottle next to your face, smirking."
						+ " [npc.speech(I think you could do with some <i>improvements</i>! I'm going to turn you into my perfect "+Main.game.getActiveNPC().getPreferredBodyDescription("b")+"!)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.She] pulls out the little stopper from the top of the bottle, and as you open your mouth to protest, [npc.she] suddenly shoves the neck past your [pc.lips+]."
						+ " As the sickly-sweet fluid pours out into your mouth, you let out a muffled whine; the only act of resistance that you're able to summon in your current state."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Come on! Swallow it all down already!)] [npc.she] growls, throwing the now-empty vessel to one side as [npc.she] tries to force you to swallow the strange fluid..."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
					return new Response("Spit",
							"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
								+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
							null);
				} else if(Main.game.getPlayer().getObedienceValue() >= 0) {
					return new Response("Spit",
							"Due to your <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+"high obedience"
								+"</b>, you can't bring yourself to disobey and spit out the transformative liquid!",
							null);
				} else {
					return new Response("Spit", "Spit out the potion.", PUNISHMENT_TF_POTION_DIALOGUE_END_RESISTED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(-5f, false));
						}
					};
				}

			} else if (index == 2) {
				ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING, Fetish.FETISH_SLAVE);
				CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

				return new Response("Swallow", "Do as you're told and swallow the strange potion.", PUNISHMENT_TF_POTION_DIALOGUE_END_OBEYED,
						applicableFetishes,
						applicableCorrutionLevel,
						null,
						null,
						null){
					@Override
					public void effects(){
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[npc.Name] steps back, grinning down at you as you obediently swallow the strange liquid,"
									+ " [npc.speech(Good [pc.girl]! "+potion.getKey()+")]"
								+ "</p>"
								+ "<p>"
									+Main.game.getActiveNPC().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
								+"</p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(5f, false));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_TF_POTION_DIALOGUE_END_RESISTED = new DialogueNodeOld("Avoided Transformation", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().getOwner().getRule(RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY) != null)
			{
				RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.modifyCashRequirement(1000);
			}
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "Despite [npc.name]'s best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
							+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
							+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?!)]"
						+ "</p>"
						+ "<p>"
							+ "After shouting down into your face, [npc.name] stands up, pulling you roughly to your [pc.feet] as [npc.she] does so, before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect little "
							+Main.game.getActiveNPC().getPreferredBodyDescription("b")
							+" next time! For now, I'm going to get some fun out of you just as you are!)]"
						+ "</p>");

			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
								+ "Despite [npc.name]'s best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
								+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
								+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?! You will have to pay for that with your own ass, you know about that?)]"
							+ "</p>"
							+ "<p>"
								+ "With that, [npc.she] turns around and goes away, leaving you to recover from your ordeal and continue on your way..."
							+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_TF_POTION_DIALOGUE_END_OBEYED = new DialogueNodeOld("Transformed", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you struggle to recover from your transformation, [npc.name] pulls you to your [pc.feet], before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect little "
							+ Main.game.getActiveNPC().getPreferredBodyDescription("b")
							+ "! Now for the real fun!)]"
						+ "</p>");

			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you struggle to recover from your transformation, [npc.name] pulls you to your [pc.feet], looking appraisingly at you"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(You're not good enough for me to be interested in you just yet!)] [npc.she] growls down at you, [npc.speech(Don't worry, soon I'm going to turn you into my perfect little "
								+Main.game.getActiveNPC().getPreferredBodyDescription("b")+"!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and walks off, leaving you panting and sweating as you attempt to recover from the transformations that were just forced upon you..."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_KINK_POTION_DIALOGUE_START = new DialogueNodeOld("Punished", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getDescription() {
			return "You are been punished by [npc.name]!";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "You anxiously wait as [npc.name] is coming up with your punishment."
						+ " After a while, [npc.she] grins, walking over to you, pulling out a mysterious bottle from out of your sight."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(You [npc.playerSlaveryPerformanceRemark(negative)].)] [npc.she] says, before leaning down and pushing you to the ground,"
						+ " [npc.speech(And for that, you deserve a little bit of punishment!)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.She] holds the bottle next to your face, smirking."
						+ " [npc.speech(I think you could do with some <i>improvements</i>! I'm going to turn you into my perfect plaything!)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.She] pulls out the little stopper from the top of the bottle, and as you open your mouth to protest, [npc.she] suddenly shoves the neck past your [pc.lips+]."
						+ " As the sickly-sweet fluid pours out into your mouth, you let out a muffled whine; the only act of resistance that you're able to summon in your current state."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Come on! Swallow it all down already!)] [npc.she] growls, throwing the now-empty vessel to one side as [npc.she] tries to force you to swallow the strange fluid..."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
					return new Response("Spit",
							"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_KINK_RECEIVING.getName(Main.game.getPlayer())
								+"</b> fetish, you love being given fetishes so much that you can't bring yourself to spit out the transformative liquid!",
							null);
				} else if(Main.game.getPlayer().getObedienceValue() >= 0) {
					return new Response("Spit",
							"Due to your <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+"high obedience"
								+"</b>, you can't bring yourself to disobey and spit out the transformative liquid!",
							null);
				} else {
					return new Response("Spit", "Spit out the potion.", PUNISHMENT_KINK_POTION_DIALOGUE_END_RESISTED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(-5f, false));
						}
					};
				}

			} else if (index == 2) {
				ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_KINK_RECEIVING, Fetish.FETISH_SLAVE);
				CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel();

				return new Response("Swallow", "Do as you're told and swallow the strange potion.", PUNISHMENT_KINK_POTION_DIALOGUE_END_OBEYED,
						applicableFetishes,
						applicableCorrutionLevel,
						null,
						null,
						null){
					@Override
					public void effects(){
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[npc.Name] steps back, grinning down at you as you obediently swallow the strange liquid,"
									+ " [npc.speech(Good [pc.girl]! "+kinkPotion.getKey()+")]"
								+ "</p>"
								+ "<p>"
									+Main.game.getActiveNPC().useItem(kinkPotion.getValue(), Main.game.getPlayer(), false, true)
								+"</p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(5f, false));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_KINK_POTION_DIALOGUE_END_RESISTED = new DialogueNodeOld("Avoided Transformation", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().getOwner().getRule(RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY) != null)
			{
				RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.modifyCashRequirement(1000);
			}
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "Despite [npc.name]'s best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
							+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
							+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?!)]"
						+ "</p>"
						+ "<p>"
							+ "After shouting down into your face, [npc.name] stands up, pulling you roughly to your [pc.feet] as [npc.she] does so, before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect little plaything"
							+" next time! For now, I'm going to get some fun out of you just as you are!)]"
						+ "</p>");

			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "Despite [npc.name]'s best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
							+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
							+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?! You will have to pay for that with your own ass, you know about that?)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and goes away, leaving you to recover from your ordeal and continue on your way..."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_KINK_POTION_DIALOGUE_END_OBEYED = new DialogueNodeOld("Transformed", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you struggle to recover from your mental change, [npc.name] pulls you to your [pc.feet], before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect slutty plaything"
							+ "! Now for the real fun!)]"
						+ "</p>");

			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you struggle to recover from your mental change, [npc.name] pulls you to your [pc.feet], looking appraisingly at you"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(You're not good enough for me to be interested in you just yet!)] [npc.she] growls down at you, [npc.speech(Don't worry, soon I'm going to turn you into my perfect little "
							+ "plaything!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and walks off, leaving you panting and sweating as you attempt to recover from the mental changes that were just forced upon you..."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_BEGGING_DIALOGUE_START = new DialogueNodeOld("Punished", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getDescription() {
			return "You are been punished by [npc.name]!";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "You anxiously wait as [npc.name] is coming up with your punishment."
						+ " After a while, [npc.she] grins, walking over to you, forcing you to your knees."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(You [npc.playerSlaveryPerformanceRemark(negative)],)] [npc.she] says,"
						+ " [npc.speech(Seems like you need to be taught how to be a good [pc.girl]!)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.She] puts [npc.her] hands on [npc.her] hips, smirking, letting you prepare."
						+ " [npc.speech(<i>Beg</i> for me! I want you to understand how much of a worthless <i>bitch</i> you are! Go on, do it.)]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getObedienceValue() >= 0) {
					return new Response("Don't beg",
							"Due to your <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+"high obedience"
								+"</b>, you can't bring yourself to disobey the order!",
							null);
				} else {
					return new Response("Don't beg", "Fuck that asshole. Keep your mouth shut!", PUNISHMENT_BEGGING_DIALOGUE_END_RESISTED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(-5f, false));
						}
					};
				}

			} else if (index == 2) {
				ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_SLAVE);
				CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel();

				return new Response("Beg", "Do as you're told and beg.", PUNISHMENT_BEGGING_DIALOGUE_END_OBEYED,
						applicableFetishes,
						applicableCorrutionLevel,
						null,
						null,
						null){
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(5f, false));
					}
				};

			} else if (index == 3) {
				ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_SLAVE);
				CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel();

				return new Response("Beg (Perform)", "Do as you're told and beg, not holding back and being as eager to please as you can.", PUNISHMENT_BEGGING_DIALOGUE_END_PERFORMED,
						applicableFetishes,
						applicableCorrutionLevel,
						null,
						null,
						null){
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(15f, false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().getOwner().incrementAffection(Main.game.getPlayer(), 10f));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_BEGGING_DIALOGUE_END_RESISTED = new DialogueNodeOld("Defiance", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "In spite of the orders given to you, you keep your mouth shut in front of [npc.name]."
							+ " [npc.She] started to yell obscenities at you, but you stood defiant and kept the silence, clearly making [npc.her] frustrated."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(I see, you think your little rebellion is going to get you somewhere?)], [npc.she] asks you, before glancing around impatiently and suddenly starting to grope you,"
							+ " [npc.speech(Fuck it, I don't have time for that, you'll learn your lesson one way or another!)]."
						+ "</p>");

			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
								+ "In spite of the orders given to you, you keep your mouth shut in front of [npc.name]."
								+ " [npc.She] started to yell obscenities at you, but you stood defiant and kept the silence, clearly making [npc.her] frustrated."
							+ "</p>"
						+ "<p>"
							+ "[npc.speech(I see, you think your little rebellion is going to get you somewhere?)], [npc.she] asks you, before glancing around impatiently,"
							+ " [npc.speech(Fuck it, I don't have time for that, you'll learn your lesson one way or another! Now, get <i>lost</i>, bitch.)]."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_BEGGING_DIALOGUE_END_OBEYED = new DialogueNodeOld("Obedience", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You obediently look down as you start to fullfill the order."
							+ " Half-heartedly mentioning that you are a slave and when prompted, agreeing that your worth is determined by your master, [npc.name]."
						+ "</p>"
						+ "<p>"
							+ "[npc.She] wasn't that convinced, but sighed. [npc.speech(That'll do, I suppose. And now, for the fun part...)]"
						+ "</p>");

			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You obediently look down as you start to fullfill the order."
							+ " Half-heartedly mentioning that you are a slave and when prompted, agreeing that your worth is determined by your master, [npc.name]."
						+ "</p>"
						+ "<p>"
							+ "[npc.She] wasn't that convinced, but sighed. [npc.speech(That'll do, I suppose. I hope you've learned your lesson!)]"
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_BEGGING_DIALOGUE_END_PERFORMED = new DialogueNodeOld("Obedience", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As soon as you were told to beg, you smile and nod, starting to speak."
							+ " [pc.speech(I'm a worthless [pc.slut], my [pc.master]! I'm here only to please you and be your perfect fucktoy!)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.name] laughs, [npc.speech(Oh is that so?)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Yes! I exist only to serve my [pc.master] and I am grateful for your protection and guidance!"
							+ " I wish of nothing more than your body to bless me every day with closeness so I could pleasure you and"
							+ " I only desire to be a good, obedient toy for you!)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.name] nods, approvingly, patting you on your head. [npc.speech(Good slut! Now get up and actually get your worthless body to work!)]"
						+ "</p>");

			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As soon as you were told to beg, you smile and nod, starting to speak."
							+ " [pc.speech(I'm a worthless [pc.slut], my [pc.master]! I'm here only to please you and be your perfect fucktoy!)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.name] laughs, [pc.speech(Oh is that so?)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Yes! I exist only to serve my [pc.master] and I am grateful for your protection and guidance!"
							+ " I wish of nothing more than your body to bless me every day with closeness so I could pleasure you and"
							+ " I only desire to be a good, obedient toy for you!)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.name] nods, approvingly, patting you on your head. [npc.speech(Good slut! Almost made me want to fuck you, but not quite. Now get back to work!)]"
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld PUNISHMENT_MUG = new DialogueNodeOld("Punished", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "[npc.name] is punishing you!";
		}

		@Override
		public String getContent() {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			String introRemark = "<p>"
							+ "[npc.Name] walks over to you, clearly furious."
							+ " [npc.speech(Are you dumb? You [npc.playerSlaveryPerformanceRemark(negative)]!)]"
						+ "</p>"
						+ "<p>"
							+ "You close your eyes, bracing yourself for the punishment as [npc.name] scowls, slapping you."
							+ " That wasn't it, however, as she lowers her hands to your body, starting to check you for your money."
						+ "</p>";

			if(player.getMoney() > 0)
			{
				return UtilText.parse(Main.game.getActiveNPC(),
							introRemark
							+ "<p>"
								+ "After a while, [npc.she] pulls out all the flames you had with you, not sparing you a single one."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(What's that, pocket change? You are <i>my</i> property, and this is <i>my</i> money."
								+ " You got it? Now go, make yourself useful!)]"
							+ "</p>"
							+ "<p>"
								+ "Not wishing to upset [npc.her] more, you gather yourself and hide away, watching [npc.her] leave."
							+ "</p>");
			}
			else
			{
				return UtilText.parse(Main.game.getActiveNPC(),
						introRemark
						+ "<p>"
							+ "Unfortunately for [npc.her], you had not a single flame to spare. [npc.She]'s positively furious."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(You don't have anything?! What kind of freeloading crap are you?"
							+ " I need your cash and you have to be earning it! Got it? Now get lost and bring me some!)]"
						+ "</p>"
						+ "<p>"
							+ "Not wishing to upset [npc.her] more, you gather yourself and hide away, watching [npc.her] leave."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						Main.game.getPlayer().setMoney(0);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld RULE_CHANGE_DAILY_TRIBUTE_MONEY = new DialogueNodeOld("Rule Change", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "[npc.name] wants to have a talk with you.";
		}

		@Override
		public String getContent() {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.


			// If for some reason the rule isn't added, we need to actually have it added.
			if(owner.getRule(RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY) == null)
			{
				owner.addRule(RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY);
				RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.setDailyCashRequirement(2000);
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] walks over to you, scratching [npc.her] chin."
							+ " [npc.speech(You know, you've been freeloading, muching off my goodwill, my protection..."
							+ " How about you show some respect towards that? I think 2 grand daily will be a good price for my generous attitude towards you."
							+ " Pay up, or you'll regret that, understood?)]"
						+ "</p>"
						+"<p>"
							+ "You nod, acknowledging the change, making a mental note of it."
						+ "</p>");
			}

			// Disobedient slaves will have to pay at least 2000 flames daily
			if(player.getObedienceValue() < 0 && RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.getDailyCashRequirement() < 2000)
			{
				RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.setDailyCashRequirement(2000);
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] walks over to you, scratching [npc.her] chin."
							+ " [npc.speech(You know, you've been freeloading, muching off my goodwill, my protection at a reduced price..."
							+ " How about you show some respect towards that? I think 2 grand daily will be a good price for my generous attitude towards you."
							+ " Remember, as usual, pay up or you'll regret that!)]"
						+ "</p>"
						+"<p>"
							+ "You nod, acknowledging the change, making a mental note of it."
						+ "</p>");
			}

			// Obedient slaves will have a slight cut
			RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.setDailyCashRequirement(1000);
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "[npc.Name] walks over to you, scratching [npc.her] chin."
						+ " [npc.speech(You know, you [npc.playerSlaveryPerformanceRemark(positive)]..."
						+ " I feel nice today, so I thought that you can be rewarded with a smaller cut."
						+ " Just one thousand flames daily, no need to thank me.)]"
					+ "</p>"
					+"<p>"
						+ "You nod, acknowledging the change, making a mental note of it."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld RULE_CHANGE_NAKED = new DialogueNodeOld("Rule Change", "", true) {
		private static final long serialVersionUID = 1L;

		private PlayerSlaveryRule ruleToAdd = null;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "[npc.name] wants to have a talk with you.";
		}

		@Override
		public String getContent() {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			String rulePrefix;
			ruleToAdd = null;

			if((owner.hasFetish(Fetish.FETISH_BREASTS_OTHERS) || player.hasFetish(Fetish.FETISH_EXHIBITIONIST) || owner.hasFetish(Fetish.FETISH_VOYEURIST))
					&& owner.getRule(RulesSlaveryDefault.RULE_NO_BREAST_BLOCKERS) == null)
			{
				ruleToAdd = RulesSlaveryDefault.RULE_NO_BREAST_BLOCKERS;
				rulePrefix = "<p>"
								+ "[npc.Name] walks over to you, eyeing your [pc.breasts] with a grin."
								+ " [npc.speech(I do like what you've got there, so from now and on,"
								+ ")] [npc.she] says, [npc.speech(You'll be wearing nothing that covers your breasts. Understood?)]"
							+ "</p>";
			}
			else if((owner.hasFetish(Fetish.FETISH_ANAL_GIVING) || player.hasFetish(Fetish.FETISH_EXHIBITIONIST) || owner.hasFetish(Fetish.FETISH_VOYEURIST))
					&& owner.getRule(RulesSlaveryDefault.RULE_NO_ASS_BLOCKERS) == null)
			{
				ruleToAdd = RulesSlaveryDefault.RULE_NO_ASS_BLOCKERS;
				rulePrefix = "<p>"
								+ "[npc.Name] walks over to you, groping your [pc.ass] with a grin."
								+ " [npc.speech(I don't want to wait before getting to that sweet ass of yours,"
								+ ")] [npc.she] says, [npc.speech(So from now and on, nothing has to cover it. Understood?)]"
							+ "</p>";
			}
			else if((owner.hasFetish(Fetish.FETISH_VAGINAL_GIVING) || player.hasFetish(Fetish.FETISH_EXHIBITIONIST) || owner.hasFetish(Fetish.FETISH_VOYEURIST))
					&& owner.getRule(RulesSlaveryDefault.RULE_NO_GROIN_BLOCKERS) == null)
			{
				ruleToAdd = RulesSlaveryDefault.RULE_NO_GROIN_BLOCKERS;
				rulePrefix = "<p>"
								+ "[npc.Name] walks over to you, groping your groin with a grin."
								+ " [npc.speech(Mmmmh, yeah, I like what you've got... From now and on,"
								+ ")] [npc.she] says, [npc.speech(Nothing has to block me from your groin. Understood?)]"
							+ "</p>";
			}
			else if((player.hasFetish(Fetish.FETISH_EXHIBITIONIST) || owner.hasFetish(Fetish.FETISH_VOYEURIST))
					&& owner.getRule(RulesSlaveryDefault.RULE_NO_FOOTWEAR) == null)
			{
				ruleToAdd = RulesSlaveryDefault.RULE_NO_FOOTWEAR;
				rulePrefix = "<p>"
								+ "[npc.Name] walks over to you, glancing at your feet."
								+ " [npc.speech(Wearing shoes? How dignifying,"
								+ ")] [npc.she] says with a scoff, [npc.speech(You aren't allowed to wear them anymore; socks are right out either. Understood?)]"
							+ "</p>";
			}
			else
			{
				rulePrefix = "<p>"
								+ "Ooopse woopsie! We did a fucky wucky~! A hecking boingo woingo!"
								+ " Please do send this one as a bug report if you run into it!"
							+ "</p>";
			}

			return UtilText.parse(Main.game.getActiveNPC(),
					rulePrefix
					+"<p>"
						+ "You nod, acknowledging the order, as [npc.name] continues."
					+ "</p>"
					+"<p>"
						+ " [npc.speech(I won't be pulling your clothing off you, so get to it yourself.)]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						if(ruleToAdd != null)
						{
							Main.game.getPlayer().getOwner().addRule(ruleToAdd);
						}
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld RULE_CHANGE_OUTSIDE_FREEDOM = new DialogueNodeOld("Rule Change", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "[npc.name] wants to have a talk with you.";
		}

		@Override
		public String getContent() {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.


			if(owner.getRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM) == null && player.getObedienceValue() > 50f)
			{
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] walks over to you, scratching [npc.her] chin."
							+ " [npc.speech(You know, you [npc.playerSlaveryPerformanceRemark(positive)]..."
							+ " I think I should allow you some free time outside, so you could do some errands for me, you know?"
							+ " I'll let you have about four hours at first, and if you won't fuck up, I'll let you have more understood?)]"
						+ "</p>"
						+"<p>"
							+ "You nod, acknowledging the change, making a mental note of it."
						+ "</p>");
			}

			if(player.getObedienceValue() < 0 && owner.getRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM) != null)
			{
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] walks over to you, scratching [npc.her] chin."
							+ " [npc.speech(You know, you [npc.playerSlaveryPerformanceRemark(negative)]..."
							+ " I think I made a mistake to allow you some free time outside."
							+ " From that point forward, you are not allowed to leave this place. Got it?)]"
						+ "</p>"
						+"<p>"
							+ "[npc.She] sighs, adding, [npc.speech(Thought I could trust you with this, you know?"
							+ " I hope you realise that this is because of your poor behavior. Now, get lost and start working.)]"
						+ "</p>"
						+"<p>"
							+ "You nod, acknowledging the change, making a mental note of it."
						+ "</p>");
			}

			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "[npc.Name] walks over to you, scratching [npc.her] chin."
						+ " [npc.speech(You know, you [npc.playerSlaveryPerformanceRemark(positive)]..."
						+ " Seems like you deserve a couple more hours of free time.)]"
					+ "</p>"
					+"<p>"
						+ "You nod, acknowledging the change, making a mental note of it."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public void effects() {

						PlayerCharacter player = Main.game.getPlayer();
						NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

						if(owner.getRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM) == null && player.getObedienceValue() > 50f)
						{
							owner.addRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM);
							RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM.setMaxFreeTime(240);
						}
						else if(player.getObedienceValue() < 0 && owner.getRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM) != null)
						{
							owner.removeRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM);
							RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM.setMaxFreeTime(0);
						}
						else
						{
							RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM.adjustMaxFreeTime(120);
						}
					}
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld RULE_CHANGE_DEGRADING_NAME_START = new DialogueNodeOld("Rule Change", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "[npc.name] wants to have a talk with you.";
		}

		@Override
		public String getContent() {
			Main.game.getActiveNPC().setPlayerKnowsName(true);
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "Your [pc.mistress] glances at you as you pass [npc.her] by and calls you over."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(You know what, you have been so obedient, I wonder...)] [npc.she] says,"
						+ " [npc.speech(Will you, bitch, actually change your name to [npc.name]'s Bitch for me?)]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("No way.", "You have at least <i>some</i> dignity remaining.", RULE_CHANGE_DEGRADING_NAME_REFUSED);

			} else if (index == 2) {
				ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_SLAVE);
				CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel();

				return new Response("Agree", "Agree to that. The rule will remain in place untill your master releases you, so beware! Not agreeing will not result in an obedience or affection penalty.", RULE_CHANGE_DEGRADING_NAME_ACCEPTED,
						applicableFetishes,
						applicableCorrutionLevel,
						null,
						null,
						null);

			} else {
				return null;
			}
		}
	};


	public static final DialogueNodeOld RULE_CHANGE_DEGRADING_NAME_REFUSED = new DialogueNodeOld("Refused", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "You refuse such a degrading treatment.";
		}

		@Override
		public String getContent() {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You shake your head, knowing full well that you wouldn't be able to live with that name."
						+ "<p>"
							+ "[npc.speech(Whatever. I guess asking you over and over again about it won't have you change it.)] [npc.name] says, shrugging and taking [npc.hisHer] leave."
						+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public void effects() {

						PlayerCharacter player = Main.game.getPlayer();
						NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

						owner.addRule(RulesSlaveryDefault.RULE_NAME_FREEDOM);
					}
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};


	public static final DialogueNodeOld RULE_CHANGE_DEGRADING_NAME_ACCEPTED = new DialogueNodeOld("Accepted", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "You accept such a sexy treatment.";
		}

		@Override
		public String getContent() {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You nod, eagerly, ready to please and degrade yourself with any way possible."
						+ "<p>"
							+ "[npc.speech(Wonderful! I want you to do it <i>officially</i>,)] [npc.name] says, grinning,"
							+ " [npc.speech(Right in the fucking city hall, you got me?)]"
						+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public void effects() {

						PlayerCharacter player = Main.game.getPlayer();
						NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

						owner.addRule(RulesSlaveryDefault.RULE_OWNERS_BITCH);
					}
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld EVENT_ARCANE_STORM_SEX = new DialogueNodeOld("Back Alley", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}
		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "As the arcane storm was brewing, you didn't notice your master sneak up on you and once you did, it was a bit too late."
						+ " [Npc.She] arrived from the shadows, pinning you to the wall, breathing heavily from the lust that built up inside [npc.herHim]."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(F-fuck I'm so horny... Don't just stand still!)] [npc.she] says, holding your hands tightly and starting to forcibly make out with you,"
						+ "[npc.speech(Come on, I know you want it too...)]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END,
						"<p>"
							+ "[npc.Name]'s [npc.arms] remained on your hands, still pinning you to the wall, as [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
							+ " Giving you an lusty smirk, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
						+ "</p>");

			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END,
						"<p>"
								+ "[npc.Name]'s [npc.arms] remained on your hands, still pinning you to the wall, as [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an lusty smirk, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END,
						"<p>"
							+ "[npc.Name]'s [npc.arms] pin you to the wall, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
							+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
							+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
						+ "</p>");

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld EVENT_NORMAL_SEX = new DialogueNodeOld("Back Alley", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}
		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "[npc.She] smirks as [npc.she] spots you, gesturing you to come over to [npc.her]. Once you did, [npc.she] speaks up."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(You know what you have to do,)] [npc.name] says, smirking, grabbing you by your arms and pinning you to the wall, [npc.speech(I want to get a real close look to that sexy body of yours.)]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END,
						"<p>"
							+ "[npc.Name]'s [npc.arms] remained on your hands, still pinning you to the wall, as [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
							+ " Giving you an lusty smirk, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
						+ "</p>");

			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END,
						"<p>"
								+ "[npc.Name]'s [npc.arms] remained on your hands, still pinning you to the wall, as [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an lusty smirk, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END,
						"<p>"
							+ "[npc.Name]'s [npc.arms] pin you to the wall, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
							+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
							+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
						+ "</p>");

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld EVENT_SUBMISSIVE_SEX = new DialogueNodeOld("Back Alley", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}
		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "[npc.She] smirks as [npc.she] spots you, gesturing you to come over to [npc.her]. Once you did, [npc.she] speaks up."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(I do have a soft spot for you now, you know,)] [npc.name] says, smirking, grabbing you by your arms gently,"
						+ " [npc.speech(And you know what? I want to see what you can do when you take the lead. Come on, I'm all yours, [pc.girl]!)]"
					+ "</p>"
					+ "<p>"
						+ "Judging by [npc.name]'s tone, you can tell that [npc.she] is horny and actually wants you to be the dominant partner this time."
						+ " You can probably even just say no, but [npc.she] wouldn't like it..."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Say no", "You aren't in the mood for that. It will reduce your master's affection to you!", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().getOwner().incrementAffection(Main.game.getPlayer(), -15f));
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else if (index == 2) {
				return new ResponseSex("Have some fun",
						"Well, [npc.she] <i>is</i> asking for it!",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END);

			} else if (index == 3) {
				return new ResponseSex("Have some gentle fun",
						"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.DOM_GENTLE;
								}
								return null;
							}
						},
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END);

			} else if (index == 4) {
				return new ResponseSex("Have some rough fun",
						"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.DOM_ROUGH;
								}
								return null;
							}
						},
						null,
						ALLEYWAY_SLAVE_NORMAL_SEX_END);

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld EVENT_INSPECTION_START = new DialogueNodeOld("Inspecting", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public String getDescription() {
			return "[npc.name] is inspecting you.";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "[npc.name] glances at you as you pass [npc.her] by and calls you over."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(I think I need to know how is my slave doing today,)] [npc.she] says,"
						+ " [npc.speech(Show me your body, I want to see it all.)]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getObedienceValue() >= 0) {
					return new Response("Ignore",
							"Due to your <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+"high obedience"
								+"</b>, you can't bring yourself to disobey the order!",
							null);
				} else {
					return new Response("Ignore", "Fuck that asshole. Don't show off yourself!", EVENT_INSPECTION_END_RESISTED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(-5f, false));
						}
					};
				}

			} else if (index == 2) {
				ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_SLAVE, Fetish.FETISH_EXHIBITIONIST);
				CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel();

				return new Response("Show off", "Do as you're told and show your body off.", EVENT_INSPECTION_END_OBEYED,
						applicableFetishes,
						applicableCorrutionLevel,
						null,
						null,
						null){
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(5f, false));
					}
				};

			} else if (index == 3) {
				ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_SLAVE, Fetish.FETISH_EXHIBITIONIST);
				CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel();

				return new Response("Show off (Perform)", "Do as you're told and show your body off, eargerly letting your master see <i>everything</i>.", EVENT_INSPECTION_END_PERFORMED,
						applicableFetishes,
						applicableCorrutionLevel,
						null,
						null,
						null){
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementObedience(15f, false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().getOwner().incrementAffection(Main.game.getPlayer(), 10f));
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld EVENT_INSPECTION_END_RESISTED = new DialogueNodeOld("Defiance", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "In spite of the orders given to you, you stayed still in front of [npc.name]."
							+ " [npc.She] started to yell obscenities at you, but you stood defiant and kept unmoving, clearly making [npc.her] frustrated."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(I see, you think your little rebellion is going to get you somewhere?)], [npc.she] asks you, before glancing around impatiently and suddenly starting to grope you,"
							+ " [npc.speech(Fuck it, I don't have time for that.!)]."
						+ "</p>");

			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
								+ "In spite of the orders given to you, you stayed still in front of [npc.name]."
								+ " [npc.She] started to yell obscenities at you, but you stood defiant and kept unmoving, clearly making [npc.her] frustrated."
							+ "</p>"
						+ "<p>"
							+ "[npc.speech(I see, you think your little rebellion is going to get you somewhere?)], [npc.she] asks you, before glancing around impatiently,"
							+ " [npc.speech(Fuck it, I don't have time for that. Now, get <i>lost</i>, bitch.)]."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_PUNISHMENT_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld EVENT_INSPECTION_END_OBEYED = new DialogueNodeOld("Obedience", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public String getContent() {
			boolean isPlayerNaked = Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS)
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES);
			String clothingRemark;
			if(isPlayerNaked)
			{
				clothingRemark = "Most of your body was already shown, so you didn't need to take off all that much.";
			}
			else
			{
				clothingRemark = "You quickly take off your clothing to let [npc.name] see you better.";
			}
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "You nod, getting ready to do as [npc.name] ordered you to. "
						+ clothingRemark
					+ "</p>"
					+ "<p>"
						+ "You then turn around slowly, to let [npc.her] see your body, mostly going through the motions rather than showing off."
						+ " [npc.Name] does seem to be satisfied, not impressed but not disappointed either. After a while, you start putting your clothing back on, when suddenly..."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Mmmmh... I liked it... Now for the <i>good</i> part!)] [npc.she] says, grinning, wrapping [npc.her] [npc.arms] around your waist, groping you."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_NORMAL_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_NORMAL_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_NORMAL_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld EVENT_INSPECTION_END_PERFORMED = new DialogueNodeOld("Obedience", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public String getContent() {

			boolean isPlayerNaked = Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS)
					&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES);
			String clothingRemark;
			if(isPlayerNaked)
			{
				clothingRemark = "Most of your body was already shown, so you didn't need to take off all that much.";
			}
			else
			{
				clothingRemark = "You slowly take off your clothing to let [npc.name] see you better, swaying your hips throughout, turning it into a striptease.";
			}
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "You nod, getting ready to do as [npc.name] ordered you to. "
						+ clothingRemark
					+ "</p>"
					+ "<p>"
						+ "You then turn around slowly, to let [npc.her] see your body, moving your best assets forward and backward to show off, teasing [npc.her] with a smirk."
						+ " You finish it off with a few clearly sexual motions, getting into it as much as [npc.name] was."
						+ " [npc.Name] grins, watching your performance, touching [npc.herself] openly. After a while, you start putting your clothing back on, when suddenly..."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Mmmmh... I liked it a <i>lot</i>... Now for the <i>good</i> part!)] [npc.she] says, grinning, wrapping [npc.her] [npc.arms] around your waist, groping you."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							ALLEYWAY_SLAVE_NORMAL_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");

				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_NORMAL_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");

				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							ALLEYWAY_SLAVE_NORMAL_SEX_END,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");

				} else {
					return null;
				}

			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
						@Override
						public DialogueNodeOld getNextDialogue() {
							Main.game.setActiveNPC(null);
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};

				} else {
					return null;
				}
			}
		}
	};


	public static final DialogueNodeOld QUEST_BRIBE = new DialogueNodeOld("A little errand", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "[npc.name] wants to give you a task.";
		}

		@Override
		public String getContent() {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			// NPC reveals it to the player.
			if(!owner.isPlayerKnowsName())
			{
				owner.setPlayerKnowsName(true);
			}

			player.incrementMoney(5000);

			return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] walks over to you, pulling out five thousand flames, before giving them to you."
							+ " [npc.speech(Don't think that this is a gift. I want every single one of those to be delivered to my friends at Enforcer HQ."
							+ " Talk to the secretary there, Candi, she's one helluva slut. Tell her that it's a gift from [npc.Name], got it?)]"
						+ "</p>"
						+"<p>"
							+ "You nod, acknowledging the order, making a mental note of it."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Good. You have an entire day to do it. Don't fuck it up, I need to have it there pronto.)]"
						+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public void effects() {

						PlayerCharacter player = Main.game.getPlayer();
						NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

						owner.addRule(RulesSlaveryAlleyway.RULE_ALLEYWAY_BRIBE_COURIER);
						RulesSlaveryAlleyway.RULE_ALLEYWAY_BRIBE_COURIER.timerSet(1440); // Setting the timer to 24 hours.
					}
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld QUEST_SUPPLY_RUN = new DialogueNodeOld("A little errand", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed(){
			return 0;
		}

		@Override
		public String getDescription(){
			return "[npc.name] wants to give you a task.";
		}

		@Override
		public String getContent() {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] walks over to you, clearly intent on asking you to do something."
							+ " [npc.speech(I have a little task for you."
							+ " You need to get me a "+RulesSlaveryAlleyway.RULE_ALLEYWAY_SUPPLY_RUN.getTargetName()+"."
							+ " Don't think it will be too hard for you, will it? Don't care how you get it, as long as I'll have it.)]"
						+ "</p>"
						+"<p>"
							+ "You nod, acknowledging the order, making a mental note of it."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Good. You have an entire day to do it. Get to it!)]"
						+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ALLEYWAY_SLAVE_PUNISHMENT_SEX_END){
					@Override
					public DialogueNodeOld getNextDialogue(){
						Main.game.setActiveNPC(null);
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};

			} else {
				return null;
			}
		}
	};

	// ======
	//
	//
	// Events (Punishments)
	//
	//
	// ======

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_PUNISHMENT_TF_POTION = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)Math.random()*25; // Random portion. Will get added if result is non-zero

			// Doesn't give more weight to obedient characters, but otherwise increases in likelyhood for disobedient ones.
			if(player.getObedienceValue() < 0)
			{
				weight += (int)(player.getObedienceValue() * -0.50);
			}

			if(isForced)
			{
				weight += 25;
			}

			// Doesn't give more weight if it's at 0; if non-zero, increases weight if master is interested in it
			if(player.getOwner().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && weight > 0)
			{
				weight += 25;
			}

			// Doesn't give more weight if it's at 0; if non-zero, increases weight if player is interested in it
			if(player.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING) && weight > 0)
			{
				weight += 25;
			}

			potion = owner.generateTransformativePotion(); // Making a new potion. Making sure it's not a fetish one.

			// Making sure this event won't get picked if the player doesn't need a transformation.
			if(potion == null)
			{
				weight = -100;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return PUNISHMENT_TF_POTION_DIALOGUE_START;
		}

		@Override
		public boolean isPunishment() {
			return true;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_PUNISHMENT_KINK_POTION = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*25f); // Random portion. Will get added if result is non-zero

			// Doesn't give more weight to obedient characters, but otherwise increases in likelyhood for disobedient ones.
			if(player.getObedienceValue() < 0)
			{
				weight += (int)(player.getObedienceValue() * -0.50);
			}

			if(isForced)
			{
				weight += 25;
			}

			// Doesn't give more weight if it's at 0; if non-zero, increases weight if master is interested in it
			if(player.getOwner().hasFetish(Fetish.FETISH_KINK_GIVING) && weight > 0)
			{
				weight += 25;
			}

			// Doesn't give more weight if it's at 0; if non-zero, increases weight if player is interested in it
			if(player.hasFetish(Fetish.FETISH_KINK_RECEIVING) && weight > 0)
			{
				weight += 25;
			}

			kinkPotion = owner.generateFetishPotion(true); // Making a new potion. Making sure it's not a tf one.

			// Making sure this event won't get picked if the player doesn't need a transformation.
			if(kinkPotion == null)
			{
				weight = -100;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return PUNISHMENT_KINK_POTION_DIALOGUE_START;
		}

		@Override
		public boolean isPunishment() {
			return true;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_PUNISHMENT_BEGGING = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*25f); // Random portion. Will get added if result is non-zero

			// Doesn't give more weight to obedient characters, but otherwise increases in likelyhood for disobedient ones.
			if(player.getObedienceValue() < 0)
			{
				weight += (int)(player.getObedienceValue() * -0.50);
			}

			// Doesn't give more weight if it's at 0; if non-zero, increases weight if master is interested in it
			if(owner.hasFetish(Fetish.FETISH_DOMINANT) && weight > 0)
			{
				weight += 25;
			}

			// Doesn't give more weight if it's at 0; if non-zero, increases weight if player is interested in it
			if(player.hasFetish(Fetish.FETISH_SUBMISSIVE) && weight > 0)
			{
				weight += 25;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return PUNISHMENT_BEGGING_DIALOGUE_START;
		}

		@Override
		public boolean isPunishment() {
			return true;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_PUNISHMENT_MUG = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*25f); // Random portion. Will get added if result is non-zero

			if(player.getObedienceValue() < -90)
			{
				weight += 75;
			}

			// Adding random variance if needed. Unlike other punishments, we don't want to force this one to happen for obedient slaves begging for it.
			if(weight > 0)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return PUNISHMENT_MUG;
		}

		@Override
		public boolean isPunishment() {
			return true;
		}

	};

	// ======
	//
	//
	// Events (Rule Changes)
	//
	//
	// ======

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_RULE_DAILY_TRIBUTE = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*50f); // Random portion. Will get added if result is non-zero

			// If for some reason the rule isn't added, we need to actually have it added.
			if(owner.getRule(RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY) == null)
			{
				weight += 75;
			}

			// Disobedient slaves will have to pay at least 2000 flames daily
			if(player.getObedienceValue() < 0 && RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.getDailyCashRequirement() < 2000)
			{
				weight += 75;
			}

			// Obedient slaves will have a slight cut
			if(player.getObedienceValue() >= 50 && RulesSlaveryDefault.RULE_DAILY_TRIBUTE_MONEY.getDailyCashRequirement() > 1000)
			{
				weight += 75;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return RULE_CHANGE_DAILY_TRIBUTE_MONEY;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_RULE_NAKED = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*50f); // Random portion. Will get added if result is non-zero

			//Checking if the rules are not in place yet.
			if(owner.hasFetish(Fetish.FETISH_VOYEURIST)
					|| (owner.hasFetish(Fetish.FETISH_BREASTS_OTHERS) && owner.getRule(RulesSlaveryDefault.RULE_NO_BREAST_BLOCKERS) == null)
					|| (owner.hasFetish(Fetish.FETISH_ANAL_GIVING)  && owner.getRule(RulesSlaveryDefault.RULE_NO_ASS_BLOCKERS) == null)
					|| (owner.hasFetish(Fetish.FETISH_VAGINAL_GIVING) && owner.getRule(RulesSlaveryDefault.RULE_NO_GROIN_BLOCKERS) == null && player.hasVagina())
					// TODO : Add penis related fetish here.
					|| player.hasFetish(Fetish.FETISH_EXHIBITIONIST))
			{
				weight += 25;
			}

			// Doubling RNG influence on the outcome for this one if the player is an exhibitionist.
			if(player.hasFetish(Fetish.FETISH_EXHIBITIONIST))
			{
				randomPart *= 2;
			}

			// Owner has set all the rules, no need to generate this one.
			if(owner.getRule(RulesSlaveryDefault.RULE_NO_ASS_BLOCKERS) != null
					&& owner.getRule(RulesSlaveryDefault.RULE_NO_BREAST_BLOCKERS) != null
					&& owner.getRule(RulesSlaveryDefault.RULE_NO_GROIN_BLOCKERS) != null
					&& owner.getRule(RulesSlaveryDefault.RULE_NO_FOOTWEAR) != null)
			{
				weight = -100;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return RULE_CHANGE_NAKED;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_RULE_OUTSIDE_FREEDOM = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*50f); // Random portion. Will get added if result is non-zero

			// Adds the rule for obedient slaves
			if(owner.getRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM) == null && player.getObedienceValue() > 50f)
			{
				weight += 75;
			}

			// Disobedient slaves will get stripped of their free hours
			if(player.getObedienceValue() < 0 && owner.getRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM) != null)
			{
				weight += 75;
			}

			// Obedient slaves will slowly start gaining free hours
			if(player.getObedienceValue() > 75f && RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM.getFreeTime() < 720)
			{
				weight += 75;
			}

			// Avoiding duplicate rule settings
			if(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM.wasRuleChangedToday())
			{
				weight = -100;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return RULE_CHANGE_OUTSIDE_FREEDOM;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_RULE_DEGRADING_NAME_CHANGE = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*50f); // Random portion. Will get added if result is non-zero

			if(player.getObedienceValue() >= 90f && owner.getRule(RulesSlaveryDefault.RULE_OWNERS_BITCH) == null  && owner.getRule(RulesSlaveryDefault.RULE_NAME_FREEDOM) == null )
			{
				weight += 150;
			}

			// Avoiding duplicate rule settings
			if(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM.wasRuleChangedToday())
			{
				weight = -100;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return RULE_CHANGE_DEGRADING_NAME_START;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	// ======
	//
	//
	// Events (Quests)
	//
	//
	// ======

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_QUEST_BRIBE = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = -50 + (int)(Math.random()*100f); // Random portion. Will get added if result is non-zero

			// Quest is given to players who can actually go outside for errands.
			if(owner.getRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM) != null && owner.getRule(RulesSlaveryAlleyway.RULE_ALLEYWAY_BRIBE_COURIER) == null)
			{
				weight += 25;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return QUEST_BRIBE;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_QUEST_SUPPLY_RUN = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = -50 + (int)(Math.random()*100f); // Random portion. Will get added if result is non-zero

			// Quest is given to players who can actually go outside for errands.
			if(owner.getRule(RulesSlaveryDefault.RULE_OUTSIDE_FREEDOM) != null && owner.getRule(RulesSlaveryAlleyway.RULE_ALLEYWAY_SUPPLY_RUN) == null)
			{
				weight += 25;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			RulesSlaveryAlleyway.RULE_ALLEYWAY_SUPPLY_RUN.resetRule();
			Main.game.getPlayer().getOwner().addRule(RulesSlaveryAlleyway.RULE_ALLEYWAY_SUPPLY_RUN);
			return QUEST_SUPPLY_RUN;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	// ======
	//
	//
	// Events (Special)
	//
	//
	// ======

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_EVENT_ARCANE_STORM_SEX = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			// This is a special event that can only happen if an arcane storm is present.

			int weight = -100; // Normal "base" value.

			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM)
			{
				weight = 150;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return EVENT_ARCANE_STORM_SEX;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_EVENT_NORMAL_SEX = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*50); // Random portion. Will get added if result is non-zero

			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM)
			{
				weight = -150;
			}

			if(owner.isAttractedTo(player))
			{
				weight += 25;
			}

			if(owner.hasFetish(Fetish.FETISH_DOMINANT))
			{
				weight += 25;
				randomPart -= 25;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return EVENT_NORMAL_SEX;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_EVENT_SUBMISSIVE_SEX = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*50f); // Random portion. Will get added if result is non-zero

			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM)
			{
				weight = -150;
			}

			if(owner.isAttractedTo(player) && owner.hasFetish(Fetish.FETISH_SUBMISSIVE) && owner.getAffection(player) > 50)
			{
				weight += 25;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return EVENT_SUBMISSIVE_SEX;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};

	static public final PlayerSlaveryEvent ALLEYWAY_SLAVE_EVENT_INSPECTION = new PlayerSlaveryEvent() {

		@Override
		public int getWeight(boolean isForced) {

			PlayerCharacter player = Main.game.getPlayer();
			NPC owner = (NPC) player.getOwner(); // Don't think a non-NPC will be able to own a player. Change in future if necessary.

			int weight = 0; // Normal "base" value.
			int randomPart = (int)(Math.random()*50f); // Random portion. Will get added if result is non-zero

			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM)
			{
				weight = -150;
			}

			if(owner.isAttractedTo(player))
			{
				weight += 25;
			}

			// Adding random variance if needed.
			if(weight > 0 || isForced)
			{
				weight += randomPart;
			}

			return weight;
		}

		@Override
		public DialogueNodeOld getTriggerDialogue() {
			return EVENT_INSPECTION_START;
		}

		@Override
		public boolean isPunishment() {
			return false;
		}

	};


}
