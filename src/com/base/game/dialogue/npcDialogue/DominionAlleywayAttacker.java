package com.base.game.dialogue.npcDialogue;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
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

public class DominionAlleywayAttacker {
	public static final DialogueNodeOld ALLEY_ATTACK = new DialogueNodeOld("Assaulted!", "A figure jumps out from the shadows!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Assaulted!";
		}

		@Override
		public String getContent() {
			if(Main.game.getCurrentRandomAttacker().getFoughtPlayerCount()>0) {
				
				if(Main.game.getCurrentRandomAttacker().isVisiblyPregnant()){
					// Pregnant encounters:
					if(!Main.game.getCurrentRandomAttacker().isReactedToPregnancy()) {
						return "<p>"
									+ "Knowing that [npc.name(a)] is prowling around this area, you make sure to stay on high alert, carefully checking each and every dark corner and recess as you walk through the twisting passages."
									+ " Just as you suspected, as you cautiously pass an inset doorway, a shadowy figure leaps out, blocking your path."
								+ "</p>"
								+ "<p>"
									+ "You instantly recognise the "
									+ " <b style='color:"+Femininity.valueOf(Main.game.getCurrentRandomAttacker().getFemininity()).getColour().toWebHexString()+";'>"
									+ Femininity.getFemininityName(Main.game.getCurrentRandomAttacker().getFemininity(), false)+"</b>"
									+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getCurrentRandomAttacker().getRaceStage().getName()+"</b>"
									+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRace().getColour().toWebHexString()+";'>" + Main.game.getCurrentRandomAttacker().getName() + "</b>"
									+ ", and you instinctively jump back into a fighting stance as you expect this encounter to be much the same as the last one."
									+ " As [npc.she] steps out from the shadows, however, you notice that there's definitely something different about [npc.herHim] this time."
									+ " The consequence of ejaculating inside of [npc.herHim] is staring you right in the face, and you gulp as you see [npc.herHim] pointing down at her pregnant belly."
								+ "</p>"
								+ (Main.game.getCurrentRandomAttacker().hasFetish(Fetish.FETISH_PREGNANCY)  || Main.game.getCurrentRandomAttacker().hasFetish(Fetish.FETISH_BROODMOTHER)
										
										?"<p>"
											+"[npc.speech(That's right, you got me knocked up!)] she shouts, the excitement in [npc.her] voice clearly giving away the fact that [npc.she]'s got a thing for being pregnant."
											+ " After confirming that you're the father of [npc.her] children, [npc.she] continues, "
											+ (Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()
												?"[npc.speech(Don't think I'm letting you off the hook though! Pregnant [npc.girl]s get horny too, and you're gonna help me with that, understood?!)]"
												:"[npc.speech(Don't think I'm letting you off the hook though! I could do with some cash, so just give up and let me rob you already!)]")
										+"</p>"
										+ "<p style='text-align:center;'>" 
											+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You ended up getting [npc.name] pregnant, and, although [npc.she] seems pleased, [npc.she] still wants to fight you!</b>"
										+ "</p>"
											
										:"<p>"
											+"[npc.speech(Look what you did, you asshole!)] [npc.she] shouts, confirming that you're the father of [npc.her] children, "
											+ "[npc.speech(Do you know how annoying it is being pregnant?! You're gonna pay for this!)]"
										+"</p>"
										+ "<p style='text-align:center;'>" 
											+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You ended up getting [npc.name] pregnant, and now [npc.she] wants revenge!</b>"
										+ "</p>");
					
					} else {
						return "<p>"
								+ "Knowing that [npc.name(a)] is prowling around this area, you make sure to stay on high alert, carefully checking each and every dark corner and recess as you walk through the twisting passages."
								+ " Just as you suspected, as you cautiously pass an inset doorway, a shadowy figure leaps out, blocking your path."
							+ "</p>"
							+ "<p>"
								+ "You instantly recognise the "
								+ " <b style='color:"+Femininity.valueOf(Main.game.getCurrentRandomAttacker().getFemininity()).getColour().toWebHexString()+";'>"
								+ Femininity.getFemininityName(Main.game.getCurrentRandomAttacker().getFemininity(), false)+"</b>"
								+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getCurrentRandomAttacker().getRaceStage().getName()+"</b>"
								+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRace().getColour().toWebHexString()+";'>" + Main.game.getCurrentRandomAttacker().getName() + "</b>"
								+ ", and you jump back into a fighting stance as you expect this encounter to be much the same as the last one."
								+ " [npc.She]'s still sporting a round belly, and [npc.she] absent-mindedly strokes [npc.her] swollen bump as [npc.she] reacts to your sudden appearance in [npc.her] alleyway."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(<i>You again?!</i>)] [npc.she] shouts, [npc.speech(This'll be a lot easier if you give up right now!)]" 
							+ "</p>"
							+ "<p>"
								+ (Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()
									?"Your powerful arcane aura is clearly turning [npc.herHim] on, and from [npc.her] hungry gaze that lingers on your body, you're able to get a good idea of what [npc.she] wants to do with you."
											+ " Knowing that defeat will result in being raped by this horny [npc.race], you ready yourself for a fight."
									:"Although your powerful arcane aura is turning [npc.herHim] on a little, it doesn't look as though [npc.she]'s really all that interested in your body,"
											+ " and will most likely only rob you if you were to lose this fight.")
							+ "</p>";
					}
					
				} else {
					// Standard repeat encounter:
					return "<p>"
								+ "Knowing that [npc.name(a)] is prowling around this area, you make sure to stay on high alert, carefully checking each and every dark corner and recess as you walk through the twisting passages."
								+ " Just as you suspected, as you cautiously pass an inset doorway, a shadowy figure leaps out, blocking your path."
							+ "</p>"
							+ "<p>"
								+ "You instantly recognise the "
								+ " <b style='color:"+Femininity.valueOf(Main.game.getCurrentRandomAttacker().getFemininity()).getColour().toWebHexString()+";'>"
								+ Femininity.getFemininityName(Main.game.getCurrentRandomAttacker().getFemininity(), false)+"</b>"
								+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getCurrentRandomAttacker().getRaceStage().getName()+"</b>"
								+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRace().getColour().toWebHexString()+";'>" + Main.game.getCurrentRandomAttacker().getName() + "</b>"
								+ ", and you jump back into a fighting stance as you expect this encounter to be much the same as the last one."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(<i>You again?!</i>)] [npc.she] shouts, [npc.speech(This'll be a lot easier if you give up right now!)]" 
							+ "</p>"
							+ "<p>"
								+ (Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()
									?"Your powerful arcane aura is clearly turning [npc.herHim] on, and from [npc.her] hungry gaze that lingers on your body, you're able to get a good idea of what [npc.she] wants to do with you."
											+ " Knowing that defeat will result in being raped by this horny [npc.race], you ready yourself for a fight."
									:"Although your powerful arcane aura is turning [npc.herHim] on a little, it doesn't look as though [npc.she]'s really all that interested in your body,"
											+ " and will most likely only rob you if you were to lose this fight.")
							+ "</p>";
				}
				
			} else {
				return "<p>"
						+ "You find yourself walking down yet another narrow passage, lined by a series of inset doorways."
						+ " As you're passing one such recess, a figure suddenly jumps out from the shadows, blocking your path."
					+ "</p>"
					+ "<p>"
						+ "You leap back, narrowly avoiding a blow aimed at your stomach, and prepare yourself for a fight."
						+ " Looking up, you see"
						+ " <b style='color:"+Femininity.valueOf(Main.game.getCurrentRandomAttacker().getFemininity()).getColour().toWebHexString()+";'>"
						+ Femininity.getFemininityName(Main.game.getCurrentRandomAttacker().getFemininity(), true)+"</b>"
						+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getCurrentRandomAttacker().getRaceStage().getName()+"</b>"
						+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRace().getColour().toWebHexString()+";'>" + Main.game.getCurrentRandomAttacker().getName() + "</b>"
						+ " grinning devilishly at you."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(What do we have here?)] [npc.she] asks, letting out a short laugh as [npc.she] sees that you're ready to fight [npc.herHim], [npc.speech(It'll be a lot easier if you just give up!)]"
					+ "</p>"
					+ "<p>"
					+ (Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()
						?"Your powerful arcane aura is clearly turning [npc.herHim] on, and from [npc.her] hungry gaze that lingers on your body, you're able to get a good idea of what [npc.she] wants to do with you."
								+ " Knowing that defeat will result in being raped by this horny [npc.race], you ready yourself for a fight."
						:"Although your powerful arcane aura is turning [npc.herHim] on a little, it doesn't look as though [npc.she]'s really all that interested in your body,"
								+ " and will most likely only rob you if you were to lose this fight.")
					+ "</p>";
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "You find yourself fighting [npc.name]!", ALLEY_ATTACK, Main.game.getCurrentRandomAttacker());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld STORM_ATTACK = new DialogueNodeOld("Attacked!", "A figure jumps out of a nearby doorway!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Assaulted!";
		}

		@Override
		public String getContent() {
			// Storm attackers are different from alley attackers. They are not saved as persistent NPCs, so don't worry about giving any repeat-encounter descriptions.
			return "<p>"
						+ "You find yourself walking down the deserted streets of Dominion, the eerie silence broken only by the occasional crash of arcane thunder high above you."
						+ " There are many hiding places for a would-be attacker to conceal themselves in, and as you pass one such place, taking the form of a building's shadowy doorway, a figure suddenly leaps out at you."
						+ " Thankfully, as you were taking care to be alert, you quickly step backwards and manage to avoid a punch aimed at your stomach."
					+ "</p>"
					+ "<p>"
						+ "Turning to face the assailant, you see"
						+ " <b style='color:"+Femininity.valueOf(Main.game.getCurrentRandomAttacker().getFemininity()).getColour().toWebHexString()+";'>"
						+ Femininity.getFemininityName(Main.game.getCurrentRandomAttacker().getFemininity(), true)+"</b>"
						+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getCurrentRandomAttacker().getRaceStage().getName()+"</b>"
						+ " <b style='color:"+Main.game.getCurrentRandomAttacker().getRace().getColour().toWebHexString()+";'>" + Main.game.getCurrentRandomAttacker().getName() + "</b>"
						+ " grinning devilishly at you."
						+ " There's no mistaking that hungry look in [npc.her] [npc.eyes], and you know that the arcane thunder has driven [npc.herHim] into a state of uncontrollable lust."
						+ " [npc.She] looks determined to claim you as [npc.her] prize, and is ready to fight you in order to claim your body."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Aww, there's no need for that!)]"
						+ " [npc.she] teases, the desperation clearly audible in [npc.her] voice as [npc.she] sees that you're ready to fight [npc.herHim],"
						+ " [npc.speech(Just give up! Come on! I <i>need</i> a good fuck right now!)]"
					+ "</p>"
					+ "<p>"
						+ "The arcane storm, combined with your powerful aura, has filled [npc.herHim] with a wild lust, and you have a good idea of what [npc.she] wants to do with you."
						+ " Knowing that defeat will result in being raped by this desperate [npc.race], you ready yourself for a fight."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "You find yourself fighting [npc.name]!", STORM_ATTACK, Main.game.getCurrentRandomAttacker());
				
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
			if(Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()) {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and you see that [npc.she]'s still got that same hungry look in [npc.her] eyes, despite [npc.her] defeat."
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
							+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
						+ "</p>"
						+ "<p>"
							+ "Pulling you to your feet, [npc.name] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Don't try anything bitch! <i>I'm</i> the one in charge here!)] [npc.she] growls, before pulling you into a forceful kiss."
						+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
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
			if(!Main.game.getCurrentRandomAttacker().isWantsToHaveSexWithPlayer()) {
				return UtilText.parse(Main.game.getCurrentRandomAttacker(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a thankful sob as [npc.she] realises that you've finished."
							+ " Frantically gathering [npc.her] belongings, [npc.she] quickly runs off before you decide to do anything else."
						+ "</p>"
						+ "<p>"
							+ "Smirking as you watch [npc.herHim] run off around a corner, you set off and continue on your way."
						+ "</p>");
				
			} else {
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
				
			} else if (index == 5 && Main.game.getPlayer().getLocationPlace() == Dominion.CITY_BACK_ALLEYS) {
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
						return GenericDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
