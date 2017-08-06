package com.base.game.dialogue.npcDialogue;

import com.base.game.Weather;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.effects.Fetish;
import com.base.game.character.race.Race;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.GenericDialogue;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseCombat;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.managers.universal.SMDomStanding;
import com.base.game.sex.managers.universal.SMSubStanding;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.world.places.Dominion;

public class HarpyNestsAttacker {
	public static final DialogueNodeOld HARPY_ATTACKS = new DialogueNodeOld("Angry harpy", "An angry harpy swoops down on you!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			if(Main.game.getCurrentRandomAttacker().isVisiblyPregnant()) {
				return "Pregnant harpy";
			} else {
				return "Angry harpy";
			}
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentRandomAttacker().isVisiblyPregnant()){
				if(!Main.game.getCurrentRandomAttacker().isReactedToPregnancy()) {
					if(Main.game.getCurrentRandomAttacker().hasFetish(Fetish.FETISH_PREGNANCY) || Main.game.getCurrentRandomAttacker().hasFetish(Fetish.FETISH_BROODMOTHER)) {
						return "<p>"
								+ "As you travel along the narrow walkways, you find yourself passing the nest of that aggressive [npc.race] who attacked you before."
								+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
								+ " [npc.Her] belly is clearly swollen; proof that you ended up getting [npc.her] pregnant from your previous encounter."
							+ "</p>"
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getCurrentRandomAttacker().getRace().isVulnerableToLilithsLustStorm()
								?"<p>"
									+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
									+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
								+ "</p>"
								:"")
							+ "<p>"
								+ "[npc.speech(It's you again!)] [npc.she] shouts, [npc.speech(Thanks for knocking me up by the way, I love being pregnant!"
								+ " Oh, but that's not gonna get you out of this! I need to teach you a lesson for getting so close to our nest!)]"
							+ "</p>"
							+ "<p>"
								+ "The excitable [npc.race] doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
								+ " Jumping backwards, you ready yourself for a fight; this [npc.race] doesn't look like [npc.she]'ll listen to reason..."
							+ "</p>"
							+ "<p style='text-align:center;'>" 
								+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You ended up getting [npc.name] pregnant, but it's done nothing to calm [npc.herHim] down!</b>"
							+ "</p>";
						
					} else {
						return "<p>"
								+ "As you travel along the narrow walkways, you find yourself passing the home of that aggressive [npc.race] who attacked you before."
								+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
								+ " [npc.Her] belly is clearly swollen; proof that you ended up getting [npc.her] pregnant from your previous encounter."
							+ "</p>"
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getCurrentRandomAttacker().getRace().isVulnerableToLilithsLustStorm()
								?"<p>"
									+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
									+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
								+ "</p>"
								:"")
							+ "<p>"
								+ "[npc.speech(It's you again!)] [npc.she] shouts, [npc.speech(Look what you did, you asshole! You got me pregnant! I'm gonna teach you a lesson for this!)]"
							+ "</p>"
							+ "<p>"
								+ "The angry [npc.race] doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
								+ " Jumping backwards, you ready yourself for a fight; this [npc.race] doesn't look like [npc.she]'ll listen to reason..."
							+ "</p>"
							+ "<p style='text-align:center;'>" 
								+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You ended up getting [npc.name] pregnant, and now [npc.she]'s even angrier than before!</b>"
							+ "</p>";
					}
					
				} else {
					return "<p>"
							+ "As you travel along the narrow walkways, you find yourself passing the home of that aggressive [npc.race] who attacked you before."
							+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
							+ " [npc.Her] belly is still clearly swollen; clear proof of your previous encounter with [npc.herHim]."
						+ "</p>"
						+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getCurrentRandomAttacker().getRace().isVulnerableToLilithsLustStorm()
							?"<p>"
								+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
								+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
							+ "</p>"
							:"")
						+ "<p>"
							+ "[npc.speech(It's you again!)] [npc.she] shouts, [npc.speech(I'm gonna teach you a lesson for getting so close to our nest!)]"
						+ "</p>"
						+ "<p>"
							+ "The angry [npc.race] doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
							+ " Jumping backwards, you ready yourself for a fight; this [npc.race] doesn't look like [npc.she]'ll listen to reason..."
						+ "</p>"
						+ "<p style='text-align:center;'>" 
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>[npc.Name] is still pregnant, and is as angry as ever!</b>"
						+ "</p>";
				}
				
			}else{
				if(Main.game.getCurrentRandomAttacker().getArmType()!=ArmType.HARPY || Main.game.getCurrentRandomAttacker().getRace()!=Race.HARPY) {
					return "<p>"
								+ "As you travel along the narrow walkways, you find yourself passing the home of that aggressive [npc.race] who attacked you before."
								+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
							+ "</p>"
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getCurrentRandomAttacker().getRace().isVulnerableToLilithsLustStorm()
								?"<p>"
									+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
									+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
								+ "</p>"
								:"")
							+ "<p>"
								+ "[npc.speech(You asshole!)] [npc.she] shouts, [npc.speech(You may have transformed me, but I'm still a part of this nest! You're gonna pay for coming back here!)]"
							+ "</p>"
							+ "<p>"
								+ "The angry [npc.race] doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
								+ " Jumping backwards, you ready yourself for a fight; this [npc.race] doesn't look like [npc.she]'ll listen to reason..."
							+ "</p>";
					
				} else {
					if(Main.game.getCurrentRandomAttacker().getFoughtPlayerCount()>0) {
						return "<p>"
									+ "As you travel along the narrow walkways, you find yourself passing the home of that aggressive [npc.race] who attacked you before."
									+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
								+ "</p>"
								+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getCurrentRandomAttacker().getRace().isVulnerableToLilithsLustStorm()
									?"<p>"
										+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
										+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
									+ "</p>"
									:"")
								+ "<p>"
									+ "[npc.speech(It's you again?!)] [npc.she] shouts, [npc.speech(I bet you've come back to steal our eggs! Or insult our matriarch! I'm gonna teach you a lesson!)]"
								+ "</p>"
								+ "<p>"
									+ "The angry harpy doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
									+ " Jumping backwards, you ready yourself for a fight; this harpy doesn't look like [npc.she]'ll listen to reason..."
								+ "</p>";
						
					} else {

						if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getCurrentRandomAttacker().getRace().isVulnerableToLilithsLustStorm()) {
							return 
								"<p>"
									+ "As you travel along the deserted walkways, you keep on catching glimpses of movement behind you."
									+ " Realising that it's only a matter of time before your pursuer attacks, you decide to take the initiative, and turn to face your stalker."
									+ " As you do, [npc.a_race] suddenly swoops down in front of you, blocking your path."
								+ "</p>"
								+ "<p>"
									+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
									+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
								+ "</p>"
								+ "<p>"
									+ "[npc.speech(How did you see me?!)] [npc.she] shouts, [npc.speech(Well, no matter! My matriarch hasn't let me have sex for days! Now just surrender and let me have some fun...)]"
								+ "</p>"
								+ "<p>"
									+ "The angry harpy doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
									+ " Jumping backwards, you ready yourself for a fight; this harpy doesn't look like [npc.she]'ll listen to reason..."
								+ "</p>";
							
						} else if(Main.game.getPlayer().isSideQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
							return 
									"<p>"
										+ "Thanks to your efforts, most of the surrounding harpy nests are very calm, and you end up having to travel out of your way in search of a suitably-angry-looking harpy."
										+ " After a short while, you spot a suitable target."
										+ " A harpy is walking around in little circles, kicking at the air and cursing under [npc.her] breath."
										+ " As you walk by, you smirk up at [npc.herHim], which seems to be all the provocation that's required; [npc.she] suddenly swoops down in front of you, blocking your path."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(What do you think you're doing?!)] [npc.she] shouts, [npc.speech(I bet you're here to steal our eggs! Or insult our matriarch! You'll pay for this!)]"
									+ "</p>"
									+ "<p>"
										+ "The angry harpy doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
										+ " Jumping backwards, you ready yourself for a fight; this harpy doesn't look like [npc.she]'ll listen to reason..."
									+ "</p>";
							
							
						} else {
							return 
								"<p>"
									+ "As you travel along the narrow walkways, you end up passing by several harpy nests; their occupants glaring at you as you pass."
									+ " Despite their suspicious looks, most of them seem content to leave you alone as you pass by, but every now and again, one or two will stand up, ready to confront you if you approach their nest."
									+ " The next nest you pass proves to be home to an exceptionally aggressive harpy, and as you walk by, [npc.she] suddenly swoops down in front of you, blocking your path."
								+ "</p>"
								+ "<p>"
									+ "[npc.speech(What do you think you're doing?!)] [npc.she] shouts, [npc.speech(I bet you're here to steal our eggs! Or insult our matriarch! You'll pay for this!)]"
								+ "</p>"
								+ "<p>"
									+ "The angry harpy doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
									+ " Jumping backwards, you ready yourself for a fight; this harpy doesn't look like [npc.she]'ll listen to reason..."
								+ "</p>";
						}
					}
				}
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "You find yourself fighting " + Main.game.getCurrentRandomAttacker().getName("the") + "!", HARPY_ATTACKS, Main.game.getCurrentRandomAttacker()){
					@Override
					public void effects() {
						if(Main.game.getCurrentRandomAttacker().isVisiblyPregnant())
							Main.game.getCurrentRandomAttacker().setReactedToPregnancy(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	//TODO unique descriptions from here
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()) {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and, despite [npc.her] defeat, you see that [npc.she]'s still got a hungry, lustful look in [npc.her] eyes."
							+ " [npc.She] reaches down to [npc.her] crotch and starts stroking [npc.herself], making pitiful little whining noises as [npc.she] squirms on the floor."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Aaah! What are you waiting for?! Come fuck me!)], [npc.she] pleads, biting [npc.her] [npc.lip] as [npc.she] continues touching [npc.herself]."
						+ "</p>"
						+ "<p>"
							+ "You wonder if you should indulge [npc.her] request."
							+ " After all, [npc.she] <i>was</i> going to do the same to you, and [npc.she] quite clearly wants it."
							+ " Then again, maybe it's best to just leave."
						+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and you see that there's a desperate look of regret in [npc.her] [npc.eyes]."
							+ " Making pitiful little whining noises, [npc.she] tries to shuffle away from you, clearly worried about what your intentions are."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(J-Just take my money and leave me alone!)], [npc.she] pleads, throwing [npc.her] "+(Main.game.getCurrentRandomAttacker().isFeminine()?"purse":"wallet")+" at your feet."
						+ "</p>"
						+ "<p>"
							+ "You wonder if you should do as [npc.she] says, and leave [npc.herHim] alone."
							+ " Then again, you <i>could</i> take advantage of [npc.her] weakened, vulnerable body..."
						+ "</p>");
				
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if(Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()) {
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
							Main.game.getCurrentRandomAttacker(), new SMDomStanding(), AFTER_SEX_VICTORY);
					
				} else if (index == 3) {
					return new ResponseSex("Have some gentle fun",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							AFTER_SEX_VICTORY,
							Main.game.getCurrentRandomAttacker(), new SMDomStanding(), AFTER_SEX_VICTORY) {
						@Override
						public void effects() {
							sexPacePlayer = (SexPace.DOM_GENTLE);
						}
					};
					
				} else if (index == 4) {
					return new ResponseSex("Have some rough fun",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							AFTER_SEX_VICTORY,
							Main.game.getCurrentRandomAttacker(), new SMDomStanding(), AFTER_SEX_VICTORY) {
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
							Main.game.getCurrentRandomAttacker(), new SMSubStanding(), AFTER_SEX_DEFEAT,
							"<p>"
								+ "You really aren't sure what to do next, and start to feel pretty uncomfortable with the fact that you just beat up this poor [npc.race]."
								+ " Leaning down, you do the first thing that comes into your mind, and start apologising,"
								+ " [pc.speech(Sorry... I was just trying to defend myself, you know... Erm... Is there anything I can do to make it up to you?)]"
							+ "</p>"
							+ "<p>"
								+ "For a moment, a look of confusion crosses over [npc.name]'s face, but, as [npc.she] sees that you're genuinely troubled by what you've just done, an evil grin crosses [npc.her] face."
								+ " [npc.She] stands up, and, grabbing you by the [pc.arm], roughly pulls you into [npc.her] as [npc.she] growls,"
								+ " [npc.speech(How about you start by apologising properly?!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name]'s strong, dominant grip on your [pc.arm] causes you to let out a lewd little moan, and your submissive nature takes over as you do as [npc.she] asks,"
								+ " [pc.speech(I'm really sorry! Please forgive me! I'll do anything! Anything you ask! Just please, don't be mad!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name] roughly yanks you forwards, and with a menacing growl, [npc.she] forces [npc.her] tongue into your mouth."
								+ " You let out a muffled yelp as your opponent takes charge, but as you feel [npc.her] [npc.hands] reaching down to start roughly groping your ass,"
									+ " you realise that you couldn't be happier with how things have turned out..."
							+ "</p>");
					
				} else if (index == 6 && Main.game.getPlayer().getLocationPlace() == Dominion.CITY_BACK_ALLEYS) {
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
							Main.game.removeNPC(Main.game.getCurrentRandomAttacker());
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
							Main.game.getCurrentRandomAttacker(), new SMDomStanding(), AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] firmly in your embrace..."
							+ "</p>");
					
				} else if (index == 3) {
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)", AFTER_SEX_VICTORY,
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SADIST)), null, CorruptionLevel.FOUR_LUSTFUL,
							null, null, null,
							Main.game.getCurrentRandomAttacker(), new SMDomStanding(), AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you take hold of [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start pressing yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] in your embrace..."
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
							Main.game.getCurrentRandomAttacker(), new SMDomStanding(), AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, roughly yanking [npc.herHim] to [npc.her] feet, you start forcefully grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you firmly hold [npc.herHim] in your embrace..."
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
					
				} else if (index == 6 && Main.game.getPlayer().getLocationPlace() == Dominion.CITY_BACK_ALLEYS) {
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
							Main.game.removeNPC(Main.game.getCurrentRandomAttacker());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()) {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
							+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Hah! How pathetic!)] [npc.she] taunts, looming over you, [npc.speech(I was kinda hoping you'd put up at least a little resistance!)]"
						+ "</p>"
						+ "<p>"
							+ "Leaning down to grab you by the [pc.arm], [npc.name] pulls you to your feet."
							+ " Yanking you towards [npc.herHim], [npc.she] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(It looks like I'll have to show you your place!)] [npc.she] growls, before pulling you into a forceful kiss."
						+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
							+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Hah! How pathetic!)] [npc.she] taunts, looming over you, [npc.speech(I was kinda hoping you'd put up at least a little resistance!)]"
						+ "</p>"
						+ "<p>"
							+ "Leaning down to grab you by the [pc.arm], [npc.name] pulls you to your feet before shoving you back against the walkway's railing."
							+ " Not having the strength to resist, you have no other option to comply with [npc.name]'s demand as [npc.she] orders you to hand over your money."
							+ " After giving [npc.herHim] some of your cash, [npc.she] roughly pushes you to the floor once more, laughing."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(You're even more pathetic than the males in our flock!)] [npc.she] sneers, before turning around and, with a flap of [npc.her] wings, flying off."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if(Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()) {
				
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							AFTER_SEX_DEFEAT,
							Main.game.getCurrentRandomAttacker(), new SMSubStanding(), AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							AFTER_SEX_DEFEAT,
							Main.game.getCurrentRandomAttacker(), new SMSubStanding(), AFTER_SEX_DEFEAT,
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
							AFTER_SEX_DEFEAT,
							Main.game.getCurrentRandomAttacker(), new SMSubStanding(), AFTER_SEX_DEFEAT,
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
			if(Sex.getNumberOfPartnerOrgasms() >= 1) {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfPartnerOrgasms() > 1?"s":"")+"."
							+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
						+ "</p>"
						+ "<p>"
							+ "Leaving [npc.herHim] to recover by [npc.herself], you set off and continue on your way."
						+ "</p>");
			} else {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
							+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
						+ "</p>"
						+ "<p>"
							+ "Leaving [npc.herHim] to get some pleasure by [npc.herself], you set off and continue on your way."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else if (index == 5) {
				return new Response(
						"Remove character",
						"Scare "+Main.game.getCurrentRandomAttacker().getName("the")+" away. <b>This will remove "+Main.game.getCurrentRandomAttacker().getName("the")+" from this area, allowing another NPC to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					
					@Override
					public DialogueNodeOld getNextDialogue() {
						return GenericDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.removeNPC(Main.game.getCurrentRandomAttacker());
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
			return UtilText.parse(Main.game.getCurrentRandomAttacker(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smile cross [npc.her] face."
						+ " [npc.She] leans down and pats you on the head,"
						+ " [npc.speech(We should do this again some time!)]"
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
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
