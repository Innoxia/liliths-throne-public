package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

public class HarpyNestsAttackerDialogue {
	public static final DialogueNodeOld HARPY_ATTACKS = new DialogueNodeOld("Angry harpy", "An angry harpy swoops down on you!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			if(Main.game.getActiveNPC().isVisiblyPregnant()) {
				return "Pregnant harpy";
			} else {
				return "Angry harpy";
			}
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isVisiblyPregnant()){
				if(!Main.game.getActiveNPC().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					if(Main.game.getActiveNPC().hasFetish(Fetish.FETISH_PREGNANCY)) {
						return "<p>"
								+ "As you travel along the narrow walkways, you find yourself passing the nest of that aggressive [npc.race] who attacked you before."
								+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
								+ " [npc.Her] belly is clearly swollen; proof that you ended up getting [npc.her] pregnant from your previous encounter."
							+ "</p>"
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getActiveNPC().getRace().isVulnerableToArcaneStorm()
								?"<p>"
									+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
									+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
								+ "</p>"
								:"")
							+ "<p>"
								+ "[npc.speech(It's you again!)] [npc.she] shouts. [npc.speech(Thanks for knocking me up by the way, I love being pregnant!"
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
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getActiveNPC().getRace().isVulnerableToArcaneStorm()
								?"<p>"
									+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
									+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
								+ "</p>"
								:"")
							+ "<p>"
								+ "[npc.speech(It's you again!)] [npc.she] shouts. [npc.speech(Look what you did, you asshole! You got me pregnant! I'm gonna teach you a lesson for this!)]"
							+ "</p>"
							+ "<p>"
								+ "The angry [npc.race] doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
								+ " Jumping backwards, you ready yourself for a fight; this [npc.race] doesn't look like [npc.she]'ll listen to reason..."
							+ "</p>"
							+ "<p style='text-align:center;'>" 
								+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You ended up getting [npc.name] pregnant, and now [npc.sheIs] even angrier than before!</b>"
							+ "</p>";
					}
					
				} else {
					return "<p>"
							+ "As you travel along the narrow walkways, you find yourself passing the home of that aggressive [npc.race] who attacked you before."
							+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
							+ " [npc.Her] belly is still clearly swollen; clear proof of your previous encounter with [npc.herHim]."
						+ "</p>"
						+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getActiveNPC().getRace().isVulnerableToArcaneStorm()
							?"<p>"
								+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
								+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
							+ "</p>"
							:"")
						+ "<p>"
							+ "[npc.speech(It's you again!)] [npc.she] shouts. [npc.speech(I'm gonna teach you a lesson for getting so close to our nest!)]"
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
				if(Main.game.getActiveNPC().getArmType()!=ArmType.HARPY || Main.game.getActiveNPC().getRace()!=Race.HARPY) {
					return "<p>"
								+ "As you travel along the narrow walkways, you find yourself passing the home of that aggressive [npc.race] who attacked you before."
								+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
							+ "</p>"
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getActiveNPC().getRace().isVulnerableToArcaneStorm()
								?"<p>"
									+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
									+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
								+ "</p>"
								:"")
							+ "<p>"
								+ "[npc.speech(You asshole!)] [npc.she] shouts. [npc.speech(You may have transformed me, but I'm still a part of this nest! You're gonna pay for coming back here!)]"
							+ "</p>"
							+ "<p>"
								+ "The angry [npc.race] doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
								+ " Jumping backwards, you ready yourself for a fight; this [npc.race] doesn't look like [npc.she]'ll listen to reason..."
							+ "</p>";
					
				} else {
					if(Main.game.getActiveNPC().getFoughtPlayerCount()>0) {
						return "<p>"
									+ "As you travel along the narrow walkways, you find yourself passing the home of that aggressive [npc.race] who attacked you before."
									+ " As you walk by, [npc.she] suddenly jumps down in front of you, blocking your path."
								+ "</p>"
								+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getActiveNPC().getRace().isVulnerableToArcaneStorm()
									?"<p>"
										+ "A flash of arcane lightning illuminates [npc.her] face, and you see a desperate, hungry look deep in [npc.her] [npc.eyes+]."
										+ " [npc.Her] gaze rests on your body for a moment, and [npc.she] licks [npc.her] [npc.lips]; proof that she's completely lost to the storm's potent effects."
									+ "</p>"
									:"")
								+ "<p>"
									+ "[npc.speech(It's you again?!)] [npc.she] shouts. [npc.speech(I bet you've come back to steal our eggs! Or insult our matriarch! I'm gonna teach you a lesson!)]"
								+ "</p>"
								+ "<p>"
									+ "The angry harpy doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
									+ " Jumping backwards, you ready yourself for a fight; this harpy doesn't look like [npc.she]'ll listen to reason..."
								+ "</p>";
						
					} else {

						if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.getActiveNPC().getRace().isVulnerableToArcaneStorm()) {
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
									+ "[npc.speech(How did you see me?!)] [npc.she] shouts. [npc.speech(Well, no matter! My matriarch hasn't let me have sex for days! Now just surrender and let me have some fun...)]"
								+ "</p>"
								+ "<p>"
									+ "The angry harpy doesn't even give you a chance to respond, and immediately darts forwards, swiping [npc.her] [npc.arm] at you."
									+ " Jumping backwards, you ready yourself for a fight; this harpy doesn't look like [npc.she]'ll listen to reason..."
								+ "</p>";
							
						} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
							return 
									"<p>"
										+ "Thanks to your efforts, most of the surrounding harpy nests are very calm, and you end up having to travel out of your way in search of a suitably-angry-looking harpy."
										+ " After a short while, you spot a suitable target."
										+ " A harpy is walking around in little circles, kicking at the air and cursing under [npc.her] breath."
										+ " As you walk by, you smirk up at [npc.herHim], which seems to be all the provocation that's required; [npc.she] suddenly swoops down in front of you, blocking your path."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(What do you think you're doing?!)] [npc.she] shouts. [npc.speech(I bet you're here to steal our eggs! Or insult our matriarch! You'll pay for this!)]"
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
									+ "[npc.speech(What do you think you're doing?!)] [npc.she] shouts. [npc.speech(I bet you're here to steal our eggs! Or insult our matriarch! You'll pay for this!)]"
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
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "You find yourself fighting " + Main.game.getActiveNPC().getName("the") + "!", Main.game.getActiveNPC()){
					@Override
					public void effects() {
						if(Main.game.getActiveNPC().isVisiblyPregnant())
							Main.game.getActiveNPC().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
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
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and, despite [npc.her] defeat, you see that [npc.sheIs] still got a hungry, lustful look in [npc.her] eyes."
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
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and you see that there's a desperate look of regret in [npc.her] [npc.eyes]."
							+ " Making pitiful little whining noises, [npc.she] tries to shuffle away from you, clearly worried about what your intentions are."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(J-Just take my money and leave me alone!)], [npc.she] pleads, throwing [npc.her] "+(Main.game.getActiveNPC().isFeminine()?"purse":"wallet")+" at your feet."
						+ "</p>"
						+ "<p>"
							+ "You wonder if you should do as [npc.she] says, and leave [npc.herHim] alone."
							+ " Then again, you <i>could</i> take advantage of [npc.her] weakened, vulnerable body..."
						+ "</p>");
				
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", null){
						@Override
						public DialogueNodeOld getNextDialogue() {
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
							null, AFTER_SEX_VICTORY);
					
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
							null, AFTER_SEX_VICTORY);
					
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
							null, AFTER_SEX_VICTORY);
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now...<br/>"
								+ "Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_DEFEAT, "<p>"
								+ "You really aren't sure what to do next, and start to feel pretty uncomfortable with the fact that you just beat up this poor [npc.race]."
								+ " Leaning down, you do the first thing that comes into your mind, and start apologising,"
								+ " [pc.speech(Sorry... I was just trying to defend myself, you know... Erm... Is there anything I can do to make it up to you?)]"
							+ "</p>"
							+ "<p>"
								+ "For a moment, a look of confusion crosses over [npc.namePos] face, but, as [npc.she] sees that you're genuinely troubled by what you've just done, an evil grin crosses [npc.her] face."
								+ " [npc.She] stands up, and, grabbing you by the [pc.arm], roughly pulls you into [npc.her] as [npc.she] growls,"
								+ " [npc.speech(How about you start by apologising properly?!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.NamePos] strong, dominant grip on your [pc.arm] causes you to let out a lewd little moan, and your submissive nature takes over as you do as [npc.she] asks."
								+ " [pc.speech(I'm really sorry! Please forgive me! I'll do anything! Anything you ask! Just please, don't be mad!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name] roughly yanks you forwards, and with a menacing growl, [npc.she] forces [npc.her] tongue into your mouth."
								+ " You let out a muffled yelp as your opponent takes charge, but as you feel [npc.her] [npc.hands] reaching down to start roughly groping your ass,"
									+ " you realise that you couldn't be happier with how things have turned out..."
							+ "</p>");
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							AFTER_COMBAT_VICTORY){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.banishNPC(Main.game.getActiveNPC());
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
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...", Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM),
							null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null,
							null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Reaching down, you grab [npc.namePos] [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] firmly in your embrace..."
							+ "</p>");
					
				} else if (index == 3) {
					return new ResponseSex("Rape [npc.herHim] (gentle)",
							"[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)", Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM),
							null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
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
							AFTER_SEX_VICTORY, "<p>"
								+ "Reaching down, you take hold of [npc.namePos] [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start pressing yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] in your embrace..."
							+ "</p>");
					
				} else if (index == 4) {
					return new ResponseSex("Rape [npc.herHim] (rough)",
							"[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)", Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM),
							null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
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
							AFTER_SEX_VICTORY, "<p>"
								+ "Reaching down, you grab [npc.namePos] [npc.arm], and, roughly yanking [npc.herHim] to [npc.her] feet, you start forcefully grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you firmly hold [npc.herHim] in your embrace..."
							+ "</p>");
					
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
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							AFTER_COMBAT_VICTORY){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.banishNPC(Main.game.getActiveNPC());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;

		Util.Value<String, AbstractItem> potion = null;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			
			if(Main.game.getActiveNPC().hasTransformationFetish() && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer()) ) {
				potion = Main.game.getActiveNPC().getTransfomativePotion(true);
				
				if(potion == null) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
							+ "</p>"
							+ "<p>"
								+ "Pulling you to your feet, [npc.name] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(You're my perfect little "
											+Main.game.getActiveNPC().getPreferredBodyDescription("b")
											+ " now! Don't forget bitch, <i>I'm</i> the one in charge!)] [npc.she] growls, before pulling you into a forceful kiss."
							+ "</p>");
					
				} else {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down and pushing you to the ground."
							+ "</p>"
							+ "<p>"
								+ "As [npc.she] pins you to the floor, [npc.she] produces a curious little bottle from somewhere out of sight, and shakes it from side to side, grinning."
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
			}
				
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer())) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
							+ "</p>"
							+ "<p>"
								+ "Pulling you to your feet, [npc.name] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Don't try anything, bitch! <i>I'm</i> the one in charge here!)] [npc.she] growls, before pulling you into a forceful kiss."
							+ "</p>");
				
				
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
							+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
						+ "</p>"
						+ "<p>"
							+ "Pulling you to your feet, [npc.name] pushes you against a nearby wall, before demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] roughly pushes you to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Don't even <i>think</i> about reporting this to the enforcers!)] [npc.she] growls down at you, before turning around and running off."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().hasTransformationFetish() && potion != null && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer()) ) {
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION_REFUSED);
					}
					
				} else if (index == 2) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					if(potion.getValue().getItemType() == ItemType.FETISH_REFINED) {
						applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_KINK_RECEIVING);
						applicableCorrutionLevel = Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel();
					}
					
					return new Response("Swallow", "Do as you're told and swallow the strange potion.", AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorrutionLevel,
							null,
							null,
							null){
						@Override
						public void effects(){
							Util.Value<String, AbstractItem> potion = Main.game.getActiveNPC().getTransfomativePotion();
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "[npc.Name] steps back, grinning down at you as you obediently swallow the strange liquid."
										+ " [npc.speech(Good [pc.girl]! "+potion.getKey()+")]"
									+ "</p>"
									+ "<p>"
										+Main.game.getActiveNPC().useItem(potion.getValue(), Main.game.getPlayer(), false)
									+"</p>");
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer())) {
					if (index == 1) {
						return new ResponseSex("Sex",
								"[npc.Name] forces [npc.herself] on you...",
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX_DEFEAT, "<p>"
									+ "[npc.NamePos] [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
									+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.sheIs] probably not going to be content with just a kiss..."
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
								AFTER_SEX_DEFEAT, "<p>"
									+ "[npc.NamePos] [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
									+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.sheIs] going to want more than just a kiss..."
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
								AFTER_SEX_DEFEAT, "<p>"
									+ "[npc.NamePos] [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
									+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
									+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.sheIs] not going to let you go..."
								+ "</p>");
						
					} else {
						return null;
					}
					
				} else {
					if (index == 1) {
						return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
							@Override
							public DialogueNodeOld getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
						};
						
					} else {
						return null;
					}
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION_REFUSED = new DialogueNodeOld("Avoided Transformation", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "Despite [npc.namePos] best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
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
							+ "Despite [npc.namePos] best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
							+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
							+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?!)]"
						+ "</p>"
						+"<p>"
							+ "After shouting down into your face, [npc.name] stands up, pulling you roughly to your [pc.feet] as [npc.she] does so, before pushing you against a nearby wall and demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] shoves you down to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(This money of yours is going to pay for your next potion!)] [npc.she] growls down at you. [npc.speech(Come back and pay me another visit, <i>or else</i>! And don't you dare refuse to swallow next time!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and runs off, leaving you to recover from your ordeal and continue on your way..."
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
							AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.sheIs] probably not going to be content with just a kiss..."
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
							AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.sheIs] going to want more than just a kiss..."
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
							AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.sheIs] not going to let you go..."
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION = new DialogueNodeOld("Transformed", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you struggle to recover from your transformation, [npc.name] pulls you to your [pc.feet], before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect little "
								+Main.game.getActiveNPC().getPreferredBodyDescription("b")
								+"! Now for the real fun!)]"
						+ "</p>");
			
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you struggle to recover from your transformation, [npc.name] pulls you to your [pc.feet], before pushing you against a nearby wall and demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] roughly pushes you to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(You're not good enough for me to be interested in you just yet!)] [npc.she] growls down at you. [npc.speech(Come back and pay me another visit, <i>or else</i>! I'm going to turn you into my perfect little "
								+Main.game.getActiveNPC().getPreferredBodyDescription("b")
								+"!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and runs off, leaving you panting and sweating as you attempt to recover from the transformations that were just forced upon you..."
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
							AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.sheIs] probably not going to be content with just a kiss..."
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
							AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.sheIs] going to want more than just a kiss..."
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
							AFTER_SEX_DEFEAT, "<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.sheIs] not going to let you go..."
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
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
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
							+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
						+ "</p>");
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
							+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare "+Main.game.getActiveNPC().getName("the")+" away. <b>This will remove "+Main.game.getActiveNPC().getName("the")+" from this area, allowing another NPC to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
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
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smile cross [npc.her] face."
						+ " [npc.She] leans down and pats you on the head."
						+ " [npc.speech(We should do this again some time!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
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
