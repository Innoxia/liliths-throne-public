package com.lilithsthrone.game.dialogue.npcDialogue;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.8?
 * @version 0.1.86
 * @author Innoxia
 */
public class DominionOffspringDialogue {
	
	private static NPCOffspring offspring() {
		return (NPCOffspring) Main.game.getActiveNPC();
	}
	
	private static String getOffspringLabel() {
		if(offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
			return UtilText.parse(offspring(), "Talking to [npc.Name]");
		} else {
			return "A familiar face";
		}
	}
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER = new DialogueNodeOld("", "You encounter a certain special someone in the alleyway.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
				
				UtilText.nodeContentSB.append(
						"<p>"
							+ "In stark contrast to Dominion's bustling, ever-busy streets, the city's back alleys are all but completely deserted."
							+ " Most likely due to their deserved reputation as being the dangerous home to muggers and rapists,"
								+ " you find that most of your time travelling through these twisting, labyrinthine passageways is spent in complete isolation."
							+ " As you turn the next corner, however, your solitary journey is interrupted, as up ahead you see "
								+ "<b style='color:"+Femininity.valueOf(offspring().getFemininityValue()).getColour().toWebHexString()+";'>"
								+ Femininity.getFemininityName(offspring().getFemininityValue(), true)+"</b>"
								+ " <b style='color:"+offspring().getRaceStage().getColour().toWebHexString()+";'>" +offspring().getRaceStage().getName()+"</b>"
								+ " <b style='color:"+offspring().getRace().getColour().toWebHexString()+";'>[npc.race]</b> leaning back against one of the alley's walls."
						+ "</p>");
				
				if(offspring().getHistory()==History.PROSTITUTE) { // Prostitute introduction:
					
					if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "From [npc.her] slutty attire, the amount of makeup plastered on [npc.her] face, and [npc.her] general posturing, there's almost no doubt in your mind that [npc.she]'s a prostitute."
									+ " As [npc.she] notices your approach, [npc.she] steps out to block your path, and in a sultry tone, confirms your suspicions,"
									+ " [npc.speech(Hey babe! You lookin' for a good time? Fifty flames for an hour's ride with [npc.name]!)]"
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "From [npc.her] slutty attire, the amount of makeup plastered on [npc.her] face, and [npc.her] general posturing, there's almost no doubt in your mind that [npc.she]'s a prostitute."
									+ " As [npc.she] notices your approach, [npc.she] looks over at you, and in a sultry tone, confirms your suspicions,"
									+ " [npc.speech(You lookin' for a good time? Only fifty flames and [npc.name]'s all yours for an hour!)]"
								+ "</p>");
					}
					
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Your gaze is drawn to [npc.her] [npc.hands] as [npc.she] sensually runs [npc.her] [npc.fingers+] up the length of [npc.her] body, before stopping at [npc.her] chest to push [npc.her] [npc.breasts+] together."
								+ " Looking up to [npc.her] face as you prepare to give [npc.herHim] your answer, your planned response leaves your mouth as a surprised gasp, as you find yourself instantly recognising the person before you."
							+ "</p>");
					
					if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "[npc.speech(Yeah, I know, right?! It's such a bargain that you don't know what to say! So, just hand those fifty flames over and we can get right to the fuckin-)]"
									+ " [npc.her] words are abruptly cut off as [npc.her] [npc.eyeColour] [npc.eyes] finally come to rest on your [pc.face],"
									+ " [npc.speech(No way... [npc.PcName]?!)]"
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "[npc.speech(Don't act so surprised! Just hand over the fifty flames and we can start alre-)]"
									+ " [npc.her] words are abruptly cut off as [npc.her] [npc.eyeColour] [npc.eyes] finally come to rest on your [pc.face],"
									+ " [npc.speech(N-No way... [npc.PcName]?!)]"
								+ "</p>");
					}
					
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Whether by some natural instinct, or perhaps some curious quirk of the arcane, you're instantly left with no doubt in your mind that the [npc.woman] in front of you is your [npc.daughter]."
								+ " From [npc.her] reaction, it's quite apparent that [npc.she]'s recognised you as well, and as [npc.her] [npc.hands] reach up to cover [npc.her] [npc.mouth] in shock, you're left to decide how best to respond..."
							+ "</p>");
					
					
				} else { // Mugger introduction:
					
					if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "From the devilish grin on [npc.her] face, combined with the evidence of an empty wallet on the floor beside [npc.herHim], and the handful of coins that you see [npc.herHim] putting away,"
										+ " there's almost no doubt in your mind that [npc.she]'s a mugger, and has just finished with [npc.her] latest victim."
									+ " As [npc.she] notices your approach, [npc.she] steps out to block your path, and in a low growl, confirms your suspicions,"
									+ " [npc.speech(Looks like another poor lost little lamb's come to pay [npc.name] the toll for walking through [npc.her] territory!)]"
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "From the devilish grin on [npc.her] face, combined with the evidence of an empty wallet on the floor beside [npc.herHim], and the handful of coins that you see [npc.herHim] putting away,"
										+ " there's almost no doubt in your mind that [npc.she]'s a mugger, and has just finished with [npc.her] latest victim."
									+ " As [npc.she] notices your approach, [npc.she] glances over at you, and in a low growl, confirms your suspicions,"
									+ " [npc.speech(You're gonna have to pay the toll for walking through [npc.name]'s territory!)]"
								+ "</p>");
					}
					
					UtilText.nodeContentSB.append(
							"<p>"
								+ "You let out an irritated sigh as you realise that this amateur is referring to [npc.herself] in the third person, as if that's meant to make them sound more important."
								+ " Looking up to [npc.her] face as you prepare to give [npc.herHim] a piece of your mind, your planned response leaves your mouth as a surprised gasp, as you find yourself instantly recognising the person before you."
							+ "</p>");
					
					if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "[npc.speech(Yeah, that's the reaction I'm lookin' for! Poor, scared little thing, gasping at the thought of what I'm gonna do to you! Perhaps after I've got your flames, I'll give you a good fuckin-)]"
									+ " [npc.her] words are abruptly cut off as [npc.her] [npc.eyeColour] [npc.eyes] finally come to rest on your [pc.face],"
									+ " [npc.speech(No way... [npc.PcName]?!)]"
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "[npc.speech(Gasping and acting all innocent isn't gonna make me let you go! Just hand over your flames, and maybe I'll decide not to fuck yo-)]"
									+ " [npc.her] words are abruptly cut off as [npc.her] [npc.eyeColour] [npc.eyes] finally come to rest on your [pc.face],"
									+ " [npc.speech(N-No way... [npc.PcName]?!)]"
								+ "</p>");
					}
					
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Whether by some natural instinct, or perhaps some curious quirk of the arcane, you're instantly left with no doubt in your mind that the [npc.race] in front of you is your [npc.daughter]."
								+ " From [npc.her] reaction, it's quite apparent that [npc.she]'s recognised you as well, and as [npc.her] [npc.hands] reach up to cover [npc.her] [npc.mouth] in shock, you're left to decide how best to respond..."
							+ "</p>");
				}
				
			} else { // Repeat encounter:
				
				if(offspring().getHistory()==History.PROSTITUTE) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Knowing that [npc.name] lives in this area, you keep an eye out for your [npc.daughter] as you travel through the eerie quiet of Dominion's back alleys."
								+ " Sure enough, as you cast your glance down a particularly seedy-looking passageway that you pass on your right,"
									+ " you see the familiar figure of your [npc.race] [npc.daughter] applying yet more makeup in an attempt to attract future customers."
								+ " Deciding to take a quick detour to check in on [npc.herHim], you enter the alleyway and start to walk towards [npc.herHim]."
							+ "</p>"
							+"<p>");
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Knowing that [npc.name] lives in this area, you keep an eye out for your [npc.daughter] as you travel through the eerie quiet of Dominion's back alleys."
								+ " Sure enough, as you cast your glance down a particularly gloomy passageway that you pass on your right, you see the familiar figure of your [npc.race] [npc.daughter] lurking in the shadows."
								+ " Deciding to take a quick detour to check in on [npc.herHim], you enter the alleyway and start to walk towards [npc.herHim]."
							+ "</p>"
							+"<p>");
				}
				
				// Reaction:
				switch(offspring().getAffectionLevel(Main.game.getPlayer())) {
					case NEGATIVE_FIVE_LOATHE: case NEGATIVE_FOUR_HATE:
						if(offspring().getPersonality().get(PersonalityTrait.AGREEABLENESS) == PersonalityWeight.LOW) {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and in a furious tone, [npc.she] shouts at you,"
															+" [npc.speech(What the fuck do you want?! If you're not here to apologise, then turn around and fuck off!)]");
						} else {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and with an angry scowl, [npc.she] cries out,"
															+" [npc.speech(What the hell do you want?! Unless you're here to apologise, I'm not interested in anything you have to say!)]");
						}
						break;
					case NEGATIVE_THREE_STRONG_DISLIKE: case NEGATIVE_TWO_DISLIKE:
						if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and in an angry tone, [npc.she] scowls at you,"
															+" [npc.speech(I'm not talking to you! If you're not here to apologise, you may as well turn right around and get out of here!)]");
						} else {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and with an angry scowl, [npc.she] calls out,"
															+" [npc.speech(I'm not talking to you! Unless you're here to apologise, just go away and leave me alone!)]");
						}
						break;
					case NEGATIVE_ONE_ANNOYED:
						if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and in a rather cold manner, [npc.she] calls out,"
															+" [npc.speech(Oh, it's you. Hi [npc.pcName].)]");
						} else {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and with a distinct lack of emotion in [npc.her] voice, [npc.she] calls out,"
															+" [npc.speech(Hi [npc.pcName].)]");
						}
						break;
					case ZERO_NEUTRAL: case POSITIVE_ONE_FRIENDLY:
						if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and with a big smile on [npc.her] [npc.face], [npc.she] calls out,"
															+" [npc.speech([npc.PcName]! You're back!)]");
						} else {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and with a smile on [npc.her] [npc.face], [npc.she] calls out,"
															+" [npc.speech(Hi [npc.pcName]!)]");
						}
						break;
					case POSITIVE_TWO_LIKE: case POSITIVE_THREE_CARING:
						if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and you see [npc.her] [npc.face] light up as [npc.she] excitedly calls out,"
															+" [npc.speech([npc.PcName]! I've missed you so much!)]");
						} else {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and with a voice filled with excitement, [npc.she] calls out,"
															+" [npc.speech([npc.PcName]! You've come back to see me again!)]");
						}
						break;
					case POSITIVE_FOUR_LOVE: case POSITIVE_FIVE_WORSHIP:
						if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and upon seeing who you are, [npc.she] excitedly jumps up and down on the spot and [npc.she] gleefully calls out,"
															+" [npc.speech([npc.PcName]! You're back! I've missed you so much! I love you!)]");
						} else {
							UtilText.nodeContentSB.append("Hearing your approach, [npc.name] turns towards you, and you see [npc.her] [npc.eyes] light up with joy as [npc.she] gleefully calls out,"
															+" [npc.speech([npc.PcName]! You've come back to see me again! I love you!)]");
						}
						break;
					default:
						break;
				}
				UtilText.nodeContentSB.append("</p>");

				boolean offspringPregnant = offspring().isVisiblyPregnant();
				boolean reactedToOffspringPregnancy = offspring().isReactedToPregnancy();
				boolean playerPregnant = Main.game.getPlayer().isVisiblyPregnant();
				boolean reactedToPlayerPregnancy = offspring().isReactedToPlayerPregnancy();
				
				// Taking into account pregnancy reactions
				if(offspringPregnant || playerPregnant) {
					UtilText.nodeContentSB.append("<p>");
					switch(offspring().getAffectionLevel(Main.game.getPlayer())) {
						case NEGATIVE_FIVE_LOATHE: case NEGATIVE_FOUR_HATE: case NEGATIVE_THREE_STRONG_DISLIKE: case NEGATIVE_TWO_DISLIKE:
							if(playerPregnant) {
								if(reactedToPlayerPregnancy) {
									UtilText.nodeContentSB.append("As you continue to approach your [npc.daughter], [npc.she] lets out a disapproving snort as [npc.her] [npc.eyes] move down to rest on your pregnant belly,"
																	+ " [npc.speech(Still pregnant, I see. Stupid slut.)]");
								} else {
									UtilText.nodeContentSB.append("As you continue to approach your [npc.daughter], [npc.she] lets out an exasperated gasp as [npc.her] [npc.eyes] move down to rest on your pregnant belly,"
																	+ " [npc.speech(You're pregnant?! Again?! Do this next lot a favour and don't try to ruin their lives, like you have with mine!)]");
								}
								if(offspringPregnant) {
									UtilText.nodeContentSB.append("</p><p>");
								}
							}
							if(offspringPregnant) {
								if(reactedToOffspringPregnancy) {
									UtilText.nodeContentSB.append(
											"The sound of your footsteps echoing off the brick walls of the alleyway are interrupted as [npc.name] steps out of the shadows, stroking [npc.her] swollen stomach as [npc.she] calls out,"
												+ " [npc.speech(Unlike <i>you</i>, I'm taking good care of my children! Not that you'd give a damn; you're probably going to treat them just as badly as you do with me!)]");
								} else {
									UtilText.nodeContentSB.append(
											"The sound of your footsteps echoing off the brick walls of the alleyway are suddenly silenced as you stop dead in your tracks."
												+ " As [npc.name] steps out from the shadows, you see that [npc.she]'s stroking a very swollen stomach, and calls out,"
												+ " [npc.speech(That's right, <i>[npc.pcName]</i>! You got me pregnant, you stupid slut! I seriously can't believe that my own [pc.mother] knocked me up! I hate you, I <i>hate</i> you!)]");
								}
							}
							break;
							
						case NEGATIVE_ONE_ANNOYED:
							if(playerPregnant) {
								if(reactedToPlayerPregnancy) {
									UtilText.nodeContentSB.append("As you continue to approach your [npc.daughter], [npc.she] lets out a disinterested hum as [npc.her] [npc.eyes] move down to rest on your pregnant belly,"
																	+ " [npc.speech(Still pregnant, I see.)]");
								} else {
									UtilText.nodeContentSB.append("As you continue to approach your [npc.daughter], [npc.she] lets out a shocked gasp as [npc.her] [npc.eyes] move down to rest on your pregnant belly,"
																	+ " [npc.speech(You're pregnant?! Well, it's obviously not your first time, so I'm sure you know what you're doing...)]");
								}
								if(offspringPregnant) {
									UtilText.nodeContentSB.append("</p><p>");
								}
							}
							if(offspringPregnant) {
								if(reactedToOffspringPregnancy) {
									UtilText.nodeContentSB.append(
											"The sound of your footsteps echoing off the brick walls of the alleyway are interrupted as [npc.name] steps out of the shadows, stroking [npc.her] swollen stomach as [npc.she] calls out,"
												+ " [npc.speech(I'm taking good care of our children.)]");
								} else {
									UtilText.nodeContentSB.append(
											"The sound of your footsteps echoing off the brick walls of the alleyway are suddenly silenced as you stop dead in your tracks."
												+ " As [npc.name] steps out from the shadows, you see that [npc.she]'s stroking a very swollen stomach, and calls out,"
												+ " [npc.speech(That's right [npc.pcName], you got me pregnant! I can't believe that my own [pc.mother] knocked me up...)]");
								}
							}
							break;
							
						case ZERO_NEUTRAL: case POSITIVE_ONE_FRIENDLY: case POSITIVE_TWO_LIKE: case POSITIVE_THREE_CARING: case POSITIVE_FOUR_LOVE: case POSITIVE_FIVE_WORSHIP:
							if(playerPregnant) {
								if(reactedToPlayerPregnancy) {
									UtilText.nodeContentSB.append("As you continue to approach your [npc.daughter], [npc.she] lets out a happy gasp as [npc.her] [npc.eyes] move down to rest on your pregnant belly,"
																	+ " [npc.speech(You're still pregnant, I see. I wonder if I'll end up meeting any of my new brothers and sisters?)]");
								} else {
									UtilText.nodeContentSB.append("As you continue to approach your [npc.daughter], [npc.she] lets out a surprised gasp as [npc.her] [npc.eyes] move down to rest on your pregnant belly,"
																	+ " [npc.speech(You're pregnant?! Oh, [npc.pcName], I'm so happy for you! I'm going to have some new brothers and sisters!)]");
								}
								if(offspringPregnant) {
									UtilText.nodeContentSB.append("</p><p>");
								}
							}
							if(offspringPregnant) {
								if(reactedToOffspringPregnancy) {
									UtilText.nodeContentSB.append(
											"The sound of your footsteps echoing off the brick walls of the alleyway are interrupted as [npc.name] steps out of the shadows, stroking [npc.her] swollen stomach as [npc.she] calls out,"
												+ " [npc.speech(I'm taking good care of our children, [npc.pcName]! I'll make you proud!)]");
								} else {
									UtilText.nodeContentSB.append(
											"The sound of your footsteps echoing off the brick walls of the alleyway are suddenly silenced as you stop dead in your tracks."
												+ " As [npc.name] steps out from the shadows, you see that [npc.she]'s stroking a very swollen stomach, and calls out,"
												+ " [npc.speech([npc.PcName]... You got me pregnant! I can't believe that my own [pc.mother] knocked me up... I love you [npc.pcName], and I'll take good care of our children!)]");
								}
							}
							break;
						default:
							break;
					}
					UtilText.nodeContentSB.append("</p>");
				}
				
				
				// Taking into account player not apologised for attack/rape
				if(offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded) || offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
					UtilText.nodeContentSB.append("<p>");
					switch(offspring().getAffectionLevel(Main.game.getPlayer())) {
						case NEGATIVE_FIVE_LOATHE: case NEGATIVE_FOUR_HATE: case NEGATIVE_THREE_STRONG_DISLIKE: case NEGATIVE_TWO_DISLIKE:
							UtilText.nodeContentSB.append(
									"Coming to a halt in front of your [npc.daughter], you see that [npc.she]'s sunk into a slight crouch, as though [npc.she]'s prepared to attack you at any moment."
									+ " You're not entirely surprised by [npc.her] reaction, and considering what you did to [npc.herHim], you're lucky that [npc.she] didn't run away or attempt to fight you the moment [npc.she] saw you."
									+ " Realising that you're not going to be able to have a proper conversation with [npc.herHim] until you've sincerely apologised, you're only left with a few options...");
							break;
						case NEGATIVE_ONE_ANNOYED:
							UtilText.nodeContentSB.append(
									"Coming to a halt in front of your [npc.daughter], you see that [npc.she]'s avoiding looking directly at you, as though [npc.she]'s disinterested in anything you have to say."
									+ " You're not entirely surprised by [npc.her] reaction, and considering what you did to [npc.herHim], you're lucky that [npc.she]'s tolerating your presence in such a dignified manner."
									+ " Realising that you're not going to be able to have a proper conversation with [npc.herHim] until you've apologised for your actions, you're only left with a few options...");
							break;
						case ZERO_NEUTRAL: case POSITIVE_ONE_FRIENDLY: case POSITIVE_TWO_LIKE: case POSITIVE_THREE_CARING: case POSITIVE_FOUR_LOVE: case POSITIVE_FIVE_WORSHIP:
							UtilText.nodeContentSB.append(
									"Coming to a halt in front of your [npc.daughter], you get the sense that [npc.she]'s slightly uneasy in your presence, despite [npc.her] amicable greeting."
									+ " You're not entirely surprised by this, and considering what you did to [npc.herHim], you're lucky that [npc.she]'s still treating you in such a friendly manner."
									+ " Realising that you're not going to be able to have a proper conversation with [npc.herHim] until you've apologised for your actions, you're only left with a few options...");
							break;
						default:
							break;
					}
					UtilText.nodeContentSB.append("</p>");
					
				} else {// Standard greeting
					UtilText.nodeContentSB.append("<p>");
					switch(offspring().getAffectionLevel(Main.game.getPlayer())) {
						case NEGATIVE_FIVE_LOATHE: case NEGATIVE_FOUR_HATE:
							UtilText.nodeContentSB.append(
									"Coming to a halt in front of your [npc.daughter], you see that [npc.she]'s sunk into a slight crouch, as though [npc.she]'s prepared to attack you at any moment."
									+ " It really seems as though [npc.she] hates you, and you consider that you're quite lucky that [npc.she] didn't run away or attempt to fight you the moment [npc.she] saw you."
									+ " Realising that you're not going to be able to have a proper conversation with [npc.herHim] until you've apologised for the way you've acted towards [npc.herHim], you're only left with a few options...");
							break;
							
						case NEGATIVE_THREE_STRONG_DISLIKE: case NEGATIVE_TWO_DISLIKE:
							UtilText.nodeContentSB.append(
									"Coming to a halt in front of your [npc.daughter], you see that [npc.she]'s making a point of not looking in your direction, and with head held high, [npc.she] lets out a derisive hum."
									+ " It really seems as though [npc.she] dislikes you, and if you wanted to have a proper conversation with [npc.herHim], it looks like you'll have to apologise for the way you've acted towards [npc.herHim]...");
							break;
						case NEGATIVE_ONE_ANNOYED:
							UtilText.nodeContentSB.append(
									"As you come to a halt in front of your [npc.daughter], [npc.she] steps forwards and looks into your [pc.eyes]."
									+ " [npc.She] seems to be mildly annoyed with you at the moment, and you wonder how best to greet [npc.herHim] while [npc.she]'s like this...");
							break;
						case ZERO_NEUTRAL: case POSITIVE_ONE_FRIENDLY:
							UtilText.nodeContentSB.append(
									"As you come to a halt in front of your [npc.daughter], [npc.she] steps forwards, and with a smile on [npc.her] face, looks into your [pc.eyes]."
									+ " [npc.She] seems to be happy to see you, and you wonder how best to greet [npc.herHim] while [npc.she]'s like this...");
							break;
						case POSITIVE_TWO_LIKE:case POSITIVE_THREE_CARING:
							UtilText.nodeContentSB.append(
									"As you come to a halt in front of your [npc.daughter], [npc.she] bounces forwards, and with a big smile on [npc.her] face, [npc.she] looks expectantly at you as [npc.she] waits for your greeting."
									+ " It's quite obvious that's [npc.she]'s very happy to see you, and you wonder how best to greet [npc.herHim] while [npc.she]'s like this...");
							break;
						case POSITIVE_FOUR_LOVE: case POSITIVE_FIVE_WORSHIP:
							UtilText.nodeContentSB.append(
									"As you come to a halt in front of your [npc.daughter], [npc.she] bounces forwards, and with a big smile on [npc.her] face, [npc.she] gazes lovingly into your [pc.eyes]."
									+ " It's quite obvious that's [npc.she]'s more than happy to see you, and you wonder how best to greet [npc.herHim] while [npc.she]'s like this...");
							break;
						default:
							break;
					}
					UtilText.nodeContentSB.append("</p>");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(offspring().getAffection(Main.game.getPlayer()) < AffectionLevel.NEGATIVE_TWO_DISLIKE.getMaximumValue()) {
				if (index == 1) {
					return new Response("Apologise", "Apologise to [npc.name].", OFFSPRING_ENCOUNTER_APOLOGY) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded) && !offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 20));
							} else {
								if(offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded)) {
									offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
								}
								if(offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
									offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, false);
									Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
								}
							}
							setOffspringFlags();
						}
					};
					
				} else if (index == 8) {
					return new Response("Sex", "There's no way [npc.she]'ll consider having sex with you when [npc.she]'s this angry.", null) {
						@Override
						public void effects() {
							setOffspringFlags();
						}
					};
					
				} else if (index == 10) {
					return new Response("Attack", "How dare [npc.name] talk to you like that! It's time to show [npc.herHim] [npc.her] place!", OFFSPRING_ENCOUNTER_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							setOffspringFlags();
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll come back some other time.", OFFSPRING_ENCOUNTER) {
							@Override
							public DialogueNodeOld getNextDialogue() {
								setOffspringFlags();
								return DebugDialogue.getDefaultDialogueNoEncounter();
							}
						};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Greeting", "Say hello to [npc.name].", OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "You're the first to recover from the shock of your surprise meeting, and, stepping forwards, you greet your [npc.daughter],"
											+ " [pc.speech([npc.Name], was it? I'm so happy to meet you! I'm sure "
												+(offspring().getMother().isPlayer()?"Lilaya":offspring().getMother().isPlayerKnowsName()?offspring().getMother().getName():"your mother")+" told you, but I'm-)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech([pc.Name], yeah, "+(offspring().getMother().isPlayer()?"Lilaya":"mom")+" told me about you. Sorry that I didn't stick around for you to wake up, but, you know how it is...)]"
											+ " [npc.name] explains, clearly feeling a little guilty about it,"
											+ " [npc.speech(But anyway! Now that you're here, come with me! I can show you my place!)]"
										+ "</p>");
							} else {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "Deciding that just a friendly verbal greeting will do, you smile at [npc.name],"
											+ " [pc.speech(Hello again [npc.Name], how have you been?)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(I've been fine [npc.pcName], you don't need to worry about me,)]"
											+ " [npc.she] replies, before continuing,"
											+ " [npc.speech(I don't like to stop and chat in the same place as I work, so let's go back to my place.)]"
										+ "</p>");
							}
							setOffspringFlags();
						}
					};
					
				} else if (index == 2) {
					return new Response("Hug", "Hug [npc.name].", OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "You're the first to recover from the shock of your surprise meeting, and, stepping forwards, you wrap your [pc.arms] around your [npc.daughter], before pulling [npc.herHim] into a loving hug."
											+ " [npc.She] returns your embrace, hugging you close to [npc.her] warm body as you both share a happy moment, before breaking away from each other."
											+ " Looking into [npc.her] [npc.eyes+], you greet your [npc.daughter] for the first time,"
											+ " [pc.speech([npc.Name], was it? I'm so happy to meet you! I'm sure "
												+(offspring().getMother().isPlayer()?"Lilaya":offspring().getMother().isPlayerKnowsName()?offspring().getMother().getName():"your mother")+" told you, but I'm-)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech([pc.Name], yeah, "+(offspring().getMother().isPlayer()?"Lilaya":"mom")+" told me about you. Sorry that I didn't stick around for you to wake up, but, you know how it is...)]"
											+ " [npc.name] explains, clearly feeling a little guilty about it,"
											+ " [npc.speech(But anyway! Now that you're here, come with me! I can show you my place!)]"
										+ "</p>");
							} else {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "Deciding that [npc.name]'s in need of a hug, you step forwards and wrap your [pc.arms] around your [npc.daughter], before pulling [npc.herHim] into your loving embrace."
											+ " [npc.She] returns your gesture, hugging you close to [npc.her] warm body as you both share a happy moment, before breaking away from each other."
											+ " Looking into [npc.her] [npc.eyes+], you greet your [npc.daughter] once again,"
											+ " [pc.speech(Hello [npc.Name], how have you been?)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(I've been fine [npc.pcName], you don't need to worry about me,)]"
											+ " [npc.she] replies, before continuing,"
											+ " [npc.speech(I don't like to stop and chat in the same place as I work, so let's go back to my place.)]"
										+ "</p>");
							}
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5));
							setOffspringFlags();
						}
					};
					
				} else if (index == 3) {
					return new Response("Kiss", "Give [npc.name] a hug and a kiss.", OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "You're the first to recover from the shock of your surprise meeting, and, stepping forwards, you wrap your [pc.arms] around your [npc.daughter],"
												+ " before planting a wet kiss on [npc.her] cheek and pulling [npc.herHim] into a loving hug."
											+ " [npc.She] returns your embrace, hugging you close to [npc.her] warm body as you both share a happy moment, before breaking away from each other."
											+ " Looking into [npc.her] [npc.eyes+], you greet your [npc.daughter] for the first time,"
											+ " [pc.speech([npc.Name], was it? I'm so happy to meet you! I'm sure "
												+(offspring().getMother().isPlayer()?"Lilaya":offspring().getMother().isPlayerKnowsName()?offspring().getMother().getName():"your mother")+" told you, but I'm-)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech([pc.Name], yeah, "+(offspring().getMother().isPlayer()?"Lilaya":"mom")+" told me about you. Sorry that I didn't stick around for you to wake up, but, you know how it is...)]"
											+ " [npc.name] explains, clearly feeling a little guilty about it,"
											+ " [npc.speech(But anyway! Now that you're here, come with me! I can show you my place!)]"
										+ "</p>");
							} else {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "Deciding that [npc.name]'s in need of some parental love, you step forwards and wrap your [pc.arms] around your [npc.daughter],"
												+ " before planting a wet kiss on [npc.her] cheek and pulling [npc.herHim] into a loving hug."
											+ " [npc.She] returns your gesture, hugging you close to [npc.her] warm body as you both share a happy moment, before breaking away from each other."
											+ " Looking into [npc.her] [npc.eyes+], you greet your [npc.daughter] once again,"
											+ " [pc.speech(Hello [npc.Name], how have you been?)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(I've been fine [npc.pcName], you don't need to worry about me,)]"
											+ " [npc.she] replies, before continuing,"
											+ " [npc.speech(I don't like to stop and chat in the same place as I work, so let's go back to my place.)]"
										+ "</p>");
							}
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							setOffspringFlags();
						}
					};
					
				} else if (index == 4) {
					return new Response("Passionate kiss", "Passionately kiss [npc.name] on the lips, and feel [npc.herHim] up as you do so.", OFFSPRING_ENCOUNTER_TALKING,
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_INCEST)),
							CorruptionLevel.FOUR_LUSTFUL,
							null,
							null,
							null) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
								if(offspring().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextStartStringBuilder().append(
											"<p>"
												+ "You're the first to recover from the shock of your surprise meeting, and, with one thought in your mind, you step forwards, before wrapping your [pc.arms] around [npc.name]'s back."
												+ " [npc.She] smiles fondly at you, [npc.her] innocent [npc.eyes] informing you that [npc.she]'s expecting nothing more than a loving hug."
											+ "</p>"
											+ "<p>"
												+ "That's not quite what you have in mind, however, and as [npc.she] leans forwards to return your embrace, you quickly press your [pc.lips+] against [npc.hers]."
												+ " Darting your [pc.hands] up to take hold of [npc.her] head, you passionately thrust your [pc.tongue+] into [npc.her] mouth,"
													+ " desperately [pc.moaning] as you push your surprised [npc.daughter] back against the alleyway's brick wall and start to make out with [npc.herHim]."
											+ "</p>"
											+ "<p>"
												+ "Much to your delight, [npc.she] doesn't try to push you away, and instead, [npc.her] own [npc.hands] reach up to wrap around your back."
												+ " Pulling you in closer, [npc.she] starts grinding against you, and you feel [npc.her] own [npc.tongue] wetly sliding over yours as [npc.she] enthusiastically returns your kiss."
											+ "</p>"
											+ "<p>"
												+ "You continue with this for some time, and as you press your [npc.daughter] back against the wall, your [pc.hands] start to move down over [npc.her] body,"
													+ " eagerly groping, stroking, and squeezing every inch of [npc.her] body that's within your reach."
												+ " Eventually, you feel as though you should give [npc.herHim] a chance to say something, and after planting one last wet kiss on [npc.her] [npc.lips+], you pull back, smiling."
											+ "</p>"
											+ "<p>"
												+ "[npc.speech([npc.pcName]... That was...)]"
												+ " [npc.she] pants, wiping the excess saliva from [npc.her] mouth,"
												+ " [npc.speech(Wow...)]"
											+ "</p>"
											+ "<p>"
												+ "You can't help but laugh at [npc.name]'s stunned reaction,"
												+ " [pc.speech(Haha, you like that sort of greeting then?)]"
											+ "</p>"
											+ "<p>"
												+ "[npc.speech(Yeah, perhaps we should do it more often... But first, come with me! We'll be more comfortable at my place!)]"
											+ "</p>");
								} else {
									Main.game.getTextStartStringBuilder().append(
											"<p>"
												+ "You're the first to recover from the shock of your surprise meeting, and, with one thought in your mind, you step forwards, before wrapping your [pc.arms] around [npc.name]'s back."
												+ " [npc.She] smiles fondly at you, [npc.her] innocent [npc.eyes] informing you that [npc.she]'s expecting nothing more than a loving hug."
											+ "</p>"
											+ "<p>"
												+ "That's not quite what you have in mind, however, and as [npc.she] leans forwards to return your embrace, you quickly press your [pc.lips+] against [npc.hers]."
												+ " Darting your [pc.hands] up to take hold of [npc.her] head, you passionately thrust your [pc.tongue+] into [npc.her] mouth,"
													+ " desperately [pc.moaning] as you push your surprised [npc.daughter] back against the alleyway's brick wall and start to make out with [npc.herHim]."
											+ "</p>"
											+ "<p>"
												+ "As the initial shock wears off, [npc.name] starts to struggle, and desperately pushes you away from [npc.herHim]."
												+ " Taking the opportunity to plant one last wet kiss on [npc.her] [npc.lips+], you then allow yourself to be pushed away,"
													+ " and let out a little laugh as you see the startled expression on your [npc.daughter]'s face."
											+ "</p>"
											+ "<p>"
												+ "[npc.speech([npc.pcName]! What was that?!)]"
												+ " [npc.she] pants, wiping the excess saliva from [npc.her] mouth,"
												+ " [npc.speech(Y-You can't greet your [npc.daughter] like that!)]"
											+ "</p>"
											+ "<p>"
												+ "You can't help but continue to smile at [npc.name]'s reaction,"
												+ " [pc.speech(Aw, sorry [npc.name], I just wanted to show you how much I love you!)]"
											+ "</p>"
											+ "<p>"
												+ "[npc.speech(Yeah, well, perhaps leave out the whole kissing part next time... Anyway, come with me! We can talk more at my place.)]"
											+ "</p>");
								}
								
							} else {
								if(offspring().isAttractedTo(Main.game.getPlayer())) {
									Main.game.getTextStartStringBuilder().append(
											"<p>"
												+ "Deciding that your [npc.daughter]'s in need of some intimate parental love, you step forwards and wrap your [pc.arms] around [npc.her] back."
												+ " [npc.She] smiles fondly at you, [npc.her] innocent [npc.eyes] informing you that [npc.she]'s expecting nothing more than a loving hug."
											+ "</p>"
											+ "<p>"
												+ "That's not quite what you have in mind, however, and as [npc.she] leans forwards to return your embrace, you quickly press your [pc.lips+] against [npc.hers]."
												+ " Darting your [pc.hands] up to take hold of [npc.her] head, you passionately thrust your [pc.tongue+] into [npc.her] mouth,"
													+ " desperately [pc.moaning] as you push your surprised [npc.daughter] back against the alleyway's brick wall and start to make out with [npc.herHim]."
											+ "</p>"
											+ "<p>"
												+ "Much to your delight, [npc.she] doesn't try to push you away, and instead, [npc.her] own [npc.hands] reach up to wrap around your back."
												+ " Pulling you in closer, [npc.she] starts grinding against you, and you feel [npc.her] own [npc.tongue] wetly sliding over yours as [npc.she] enthusiastically returns your kiss."
											+ "</p>"
											+ "<p>"
												+ "You continue with this for some time, and as you press your [npc.daughter] back against the wall, your [pc.hands] start to move down over [npc.her] body,"
													+ " eagerly groping, stroking, and squeezing every inch of [npc.her] body that's within your reach."
												+ " Eventually, you feel as though you should give [npc.herHim] a chance to say something, and after planting one last wet kiss on [npc.her] [npc.lips+], you pull back, smiling."
											+ "</p>"
											+ "<p>"
												+ "[npc.speech(Mmm, thanks [npc.pcName]...)]"
												+ " [npc.she] pants, wiping the excess saliva from [npc.her] mouth,"
												+ " [npc.speech(I love these greetings of yours!)]"
											+ "</p>"
											+ "<p>"
												+ "You can't help but smile at [npc.name]'s happy reaction,"
												+ " [pc.speech(I'll make sure to keep them coming!)]"
											+ "</p>"
											+ "<p>"
												+ "[npc.speech(Mmm, I think I'm in the mood for some more of your attention... We'll be more comfortable at my place, follow me!)]"
											+ "</p>");
								} else {
									Main.game.getTextStartStringBuilder().append(
											"<p>"
												+ "Deciding that your [npc.daughter]'s in need of some intimate parental love, you step forwards and wrap your [pc.arms] around [npc.her] back."
												+ " [npc.She] smiles fondly at you, [npc.her] innocent [npc.eyes] informing you that [npc.she]'s expecting nothing more than a loving hug."
											+ "</p>"
											+ "<p>"
												+ "That's not quite what you have in mind, however, and as [npc.she] leans forwards to return your embrace, you quickly press your [pc.lips+] against [npc.hers]."
												+ " Darting your [pc.hands] up to take hold of [npc.her] head, you passionately thrust your [pc.tongue+] into [npc.her] mouth,"
													+ " desperately [pc.moaning] as you push your surprised [npc.daughter] back against the alleyway's brick wall and start to make out with [npc.herHim]."
											+ "</p>"
											+ "<p>"
												+ "As the initial shock wears off, [npc.name] starts to struggle, and desperately pushes you away from [npc.herHim]."
												+ " Taking the opportunity to plant one last wet kiss on [npc.her] [npc.lips+], you then allow yourself to be pushed away,"
													+ " and let out a little laugh as you see the startled expression on your [npc.daughter]'s face."
											+ "</p>"
											+ "<p>"
												+ "[npc.speech([npc.pcName]! What was that?!)]"
												+ " [npc.she] pants, wiping the excess saliva from [npc.her] mouth,"
												+ " [npc.speech(Y-You can't greet your [npc.daughter] like that!)]"
											+ "</p>"
											+ "<p>"
												+ "You can't help but continue to smile at [npc.name]'s reaction,"
												+ " [pc.speech(Aw, sorry [npc.name], I just wanted to show you how much I love you!)]"
											+ "</p>"
											+ "<p>"
												+ "[npc.speech(Yeah, well, perhaps leave out the whole kissing part next time... Anyway, come with me. We can talk more at my place.)]"
											+ "</p>");
								}
							}
							if(offspring().isAttractedTo(Main.game.getPlayer())) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
								
							} else if(
									((Main.game.getPlayer().isFeminine() && offspring().getSexualOrientation()==SexualOrientation.GYNEPHILIC)
									|| (!Main.game.getPlayer().isFeminine() && offspring().getSexualOrientation()==SexualOrientation.ANDROPHILIC)
									|| (offspring().getSexualOrientation()==SexualOrientation.AMBIPHILIC))
									|| offspring().hasFetish(Fetish.FETISH_INCEST)) {
								//Incest fetish and not attracted to player, or attracted to player and no incest fetsh:
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -5));
								
							} else {
								//Not attracted to player, and no incest fetsh:
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -20));
							}
							setOffspringFlags();
						}
					};
					
				} if (index == 5) {
					return new Response("Scold [npc.herHim]",
							"Ask [npc.name] just what [npc.she] thinks [npc.she]'s doing!"
									+(offspring().getHistory()==History.PROSTITUTE
											?" (This will voice disapproval about [npc.herHim] being a prostitute.)"
											:" (This will voice disapproval about [npc.herHim] being a mugger.)"),
							OFFSPRING_ENCOUNTER_TALKING) {
						@Override
						public void effects() {
							if(!offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
								if(offspring().getHistory()==History.PROSTITUTE) {
									Main.game.getTextStartStringBuilder().append(
											"<p>"
												+ "You're the first to recover from the shock of your surprise meeting, and as your initial surprise fades away, you find that you're shaking your head in utter disbelief."
												+ " Looking into your [npc.daughter]'s [npc.eyes], you frown in disapproval as you start to voice your concerns,"
												+ " [pc.speech([npc.Name]! Please don't tell me that you're working as a... a prostitute!)]"
											+ "</p>");
									
									if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
										Main.game.getTextStartStringBuilder().append("<p>"
													+ "[npc.speech(So what if I am?!)]"
													+ " [npc.name] shouts back, clearly upset by your judgmental remark,"
													+ " [npc.speech(It's my body, I can do what I want with it!)]"
												+ "</p>");
									} else {
										Main.game.getTextStartStringBuilder().append("<p>"
													+ "[npc.speech(Yeah, so?)]"
													+ " [npc.name] replies, shuffling [npc.her] [npc.feet],"
													+ " [npc.speech(I can do what I want. It's <i>my</i> body...)]"
												+ "</p>");
										
									}
								} else { // Mugger:
									Main.game.getTextStartStringBuilder().append(
											"<p>"
												+ "You're the first to recover from the shock of your surprise meeting, and as your initial surprise fades away, you find that you're shaking your head in utter disbelief."
												+ " Looking into your [npc.daughter]'s [npc.eyes], you frown in disapproval as you start to voice your concerns,"
												+ " [pc.speech([npc.Name]! Please don't tell me that you're just another low-life mugger!)]"
											+ "</p>");
									
									if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
										Main.game.getTextStartStringBuilder().append("<p>"
													+ "[npc.speech(So what if I am?!)]"
													+ " [npc.name] shouts back, clearly upset by your judgmental remark,"
													+ " [npc.speech(Do you know how hard it is to make a living around here?!)]"
												+ "</p>");
									} else {
										Main.game.getTextStartStringBuilder().append("<p>"
													+ "[npc.speech(Yeah, so?)]"
													+ " [npc.name] replies, shuffling [npc.her] [npc.feet],"
													+ " [npc.speech(I need a way to pay the rent...)]"
												+ "</p>");
									}
								}
								
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Don't you back-talk me!)]"
											+ " you respond in the sternest tone you can muster,"
											+ " [pc.speech(I don't approve of what you're doing, and whatsmore, I don't want you hanging out in these alleyways! It's very dangerous back here!)]"
										+ "</p>"
										+ "<p>"
											+ "Obviously deciding that it's better to just endure your lecture rather than trying to talk back, [npc.name] just rolls [npc.her] [npc.eyes] and leans back against the wall."
											+ " You continue to scold [npc.herHim] for a little while, making comments on everything, from the state of [npc.her] clothes, to speculating about the sort of hours [npc.she] keeps."
										+ "</p>"
										+ "<p>"
											+ "Eventually, however, you run out of steam, and [npc.name] pushes [npc.herself] off of the wall, before turning to you,"
											+ " [npc.speech(Thanks for the lecture, [npc.pcName], I'll do my best to meet with your approval for the next time we see each other."
												+ " Anyway, I don't like to stop and chat in the same place as I work, so let's go back to my place."
												+ " Perhaps you can have fun lecturing me about the state of my apartment as well...)]"
										+ "</p>");
								
							} else {
								if(offspring().getHistory()==History.PROSTITUTE) {
									Main.game.getTextStartStringBuilder().append(
											"<p>"
												+ "As you look [npc.name] up and down, you find yourself shaking your head in utter disbelief."
												+ " Looking into your [npc.daughter]'s [npc.eyes], you frown in disapproval as you start to voice your concerns,"
												+ " [pc.speech([npc.Name]! Please don't tell me that you're still out here mugging people!)]"
											+ "</p>");
									
									if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
										Main.game.getTextStartStringBuilder().append("<p>"
													+ "[npc.speech(So what if I am?!)]"
													+ " [npc.name] shouts back, clearly upset by your judgmental remark,"
													+ " [npc.speech(Do you know how hard it is to make a living around here?!)]"
												+ "</p>");
									} else {
										Main.game.getTextStartStringBuilder().append("<p>"
													+ "[npc.speech(Yeah, so?)]"
													+ " [npc.name] replies, shuffling [npc.her] [npc.feet],"
													+ " [npc.speech(I need a way to pay the rent...)]"
												+ "</p>");
									}
									
								} else {
									Main.game.getTextStartStringBuilder().append(
											"<p>"
												+ "As you look [npc.name] up and down, you find yourself shaking your head in utter disbelief."
												+ " Looking into your [npc.daughter]'s [npc.eyes], you frown in disapproval as you start to voice your concerns,"
												+ " [pc.speech([npc.Name]! Please don't tell me that you're still working as a prostitute!)]"
											+ "</p>");
									
									if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
										Main.game.getTextStartStringBuilder().append("<p>"
													+ "[npc.speech(So what if I am?!)]"
													+ " [npc.name] shouts back, clearly upset by your judgmental remark,"
													+ " [npc.speech(It's my body, I can do what I want with it!)]"
												+ "</p>");
									} else {
										Main.game.getTextStartStringBuilder().append("<p>"
													+ "[npc.speech(Yeah, so?)]"
													+ " [npc.name] replies, shuffling [npc.her] [npc.feet],"
													+ " [npc.speech(I can do what I want. It's <i>my</i> body...)]"
												+ "</p>");
									}
								}
								
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "[pc.speech(Don't you back-talk me!)]"
											+ " you respond in the sternest tone you can muster,"
											+ " [pc.speech(I don't approve of what you're doing, and whatsmore, I don't want you hanging out in these alleyways! It's very dangerous back here!)]"
										+ "</p>"
										+ "<p>"
											+ "Obviously deciding that it's better to just endure your lecture rather than trying to talk back, [npc.name] just rolls [npc.her] [npc.eyes] and leans back against the wall."
											+ " You continue to scold [npc.herHim] for a little while, making comments on everything, from the state of [npc.her] clothes, to speculating about the sort of hours [npc.she] keeps."
										+ "</p>"
										+ "<p>"
											+ "Eventually, however, you run out of steam, and [npc.name] pushes [npc.herself] off of the wall, before turning to you,"
											+ " [npc.speech(Thanks for the lecture, [npc.pcName], I'll do my best to meet with your approval for the next time we see each other."
												+ " Anyway, I don't like to stop and chat in the same place as I work, so let's go back to my place."
												+ " Perhaps you can have fun lecturing me about the state of my apartment as well...)]"
										+ "</p>");
							}
							
							// If masochist fetish, +5, if not, -5:
							if(offspring().hasFetish(Fetish.FETISH_MASOCHIST)) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5)); 
							} else {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -5));
							}
							setOffspringFlags();
						}
					};
					
				} else if (index == 10) {
					return new Response("Attack", "How dare [npc.name] talk to you like that! It's time to show [npc.herHim] [npc.her] place!", OFFSPRING_ENCOUNTER_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							setOffspringFlags();
						}
					};
					
				} else if (index == 0 && offspring().hasFlag(NPCFlagValue.flagOffspringIntroduced)) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", OFFSPRING_ENCOUNTER) {
							@Override
							public DialogueNodeOld getNextDialogue() {
								return DebugDialogue.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "You don't really have time to stop and talk with [npc.name] at the moment, and you tell [npc.herHim] as much,"
											+ " [pc.speech(This isn't the best time for me right now, I'll come back later.)]"
										+ "</p>");
								
								// Taking into account player not apologised for attack/rape
								if(offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded) || offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
									Main.game.getTextStartStringBuilder().append("<p>");
									switch(offspring().getAffectionLevel(Main.game.getPlayer())) {
										case NEGATIVE_FIVE_LOATHE: case NEGATIVE_FOUR_HATE: case NEGATIVE_THREE_STRONG_DISLIKE: case NEGATIVE_TWO_DISLIKE:
											Main.game.getTextStartStringBuilder().append(
												"<p>"
													+ "Letting out an angry snort, your [npc.daughter] turns [npc.her] back on you and starts to walk away, shouting over [npc.her] shoulder as [npc.she] makes [npc.her] exit,"
													+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
															?" [npc.speech(Fine, fuck off then! See if I care! Don't bother coming back unless you're gonna apologise!)]"
															:" [npc.speech(Eugh, fine! See if I care! Oh, and don't bother coming back unless you're gonna apologise!)]")
												+"</p>");
											break;
										case NEGATIVE_ONE_ANNOYED:
											Main.game.getTextStartStringBuilder().append(
													"<p>"
														+ "Letting out a dismissive snort, your [npc.daughter] turns [npc.her] back on you and starts to walk away, shouting over [npc.her] shoulder as [npc.she] makes [npc.her] exit,"
														+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
																?" [npc.speech(Fine, see you later! And you'd better be ready to apologise by then!)]"
																:" [npc.speech(Fine, see you later! I'm expecting an apology when you return!)]")
													+"</p>");
											break;
										case ZERO_NEUTRAL: case POSITIVE_ONE_FRIENDLY: case POSITIVE_TWO_LIKE: case POSITIVE_THREE_CARING: case POSITIVE_FOUR_LOVE: case POSITIVE_FIVE_WORSHIP:
											Main.game.getTextStartStringBuilder().append(
													"<p>"
														+ "Letting out a disappointed whine, your [npc.daughter] reluctantly accepts your excuse to leave,"
														+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
																?" [npc.speech(Ok then... I'll see you later!)]"
																:" [npc.speech(Ok then... I'll see you later I guess...)]")
													+"</p>");
											break;
										default:
											break;
									}
									Main.game.getTextStartStringBuilder().append("</p>");
									
								} else {
									Main.game.getTextStartStringBuilder().append("<p>");
									switch(offspring().getAffectionLevel(Main.game.getPlayer())) {
										case NEGATIVE_FIVE_LOATHE: case NEGATIVE_FOUR_HATE:
											Main.game.getTextStartStringBuilder().append(
													"<p>"
														+ "Letting out an angry snort, your [npc.daughter] turns [npc.her] back on you and starts to walk away, shouting over [npc.her] shoulder as [npc.she] makes [npc.her] exit,"
														+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
																?" [npc.speech(Fine, fuck off then! See if I care!)]"
																:" [npc.speech(Eugh, fine! See if I care!)]")
													+ "</p>");
											break;
											
										case NEGATIVE_THREE_STRONG_DISLIKE: case NEGATIVE_TWO_DISLIKE:
											Main.game.getTextStartStringBuilder().append(
													"<p>"
														+ "Letting out a derisive snort, your [npc.daughter] turns [npc.her] back on you and starts to walk away, shouting over [npc.her] shoulder as [npc.she] makes [npc.her] exit,"
														+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
																?" [npc.speech(Fine! See if I care!)]"
																:" [npc.speech(Eugh, fine! See if I care!)]")
													+ "</p>");
											break;
										case NEGATIVE_ONE_ANNOYED:
											Main.game.getTextStartStringBuilder().append(
													"<p>"
														+ "Letting out a dismissive snort, your [npc.daughter] turns [npc.her] back on you and starts to walk away, shouting over [npc.her] shoulder as [npc.she] makes [npc.her] exit,"
														+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
																?" [npc.speech(Fine, see you later [npc.pcName]!)]"
																:" [npc.speech(Fine, see you later I guess!)]")
													+"</p>");
											break;
										case ZERO_NEUTRAL: case POSITIVE_ONE_FRIENDLY:
											Main.game.getTextStartStringBuilder().append(
													"<p>"
														+ "Letting out a disappointed whine, your [npc.daughter] reluctantly accepts your excuse to leave,"
														+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
																?" [npc.speech(Fine, see you later [npc.pcName]...)]"
																:" [npc.speech(Fine, see you later I guess...)]")
													+"</p>");
											break;
										case POSITIVE_TWO_LIKE:case POSITIVE_THREE_CARING:
											Main.game.getTextStartStringBuilder().append(
													"<p>"
														+ "Letting out a miserable whine, your [npc.daughter] reluctantly accepts your excuse to leave,"
														+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
																?" [npc.speech(Aww... Come back soon, ok?! I miss you!)]"
																:" [npc.speech(Aww... I hope you come back soon...)]")
													+"</p>");
											break;
										case POSITIVE_FOUR_LOVE: case POSITIVE_FIVE_WORSHIP:
											Main.game.getTextStartStringBuilder().append(
													"<p>"
														+ "With a look of despair on [npc.her] face, your [npc.daughter] miserably accepts your excuse to leave,"
														+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH
																?" [npc.speech(Aww... Please come back soon! I love you [npc.pcName]!)]"
																:" [npc.speech(Aww... I hope you come back soon... I love you [npc.pcName]!)]")
													+ "</p>");
											break;
										default:
											break;
									}
									Main.game.getTextStartStringBuilder().append("</p>");
								}
								
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "Walking away, you leave your [npc.daughter] to get on with whatever it was that [npc.she] was up to, and continue on your journey."
										+ "</p>");
								
								
								setOffspringFlags();
							}	
						};
					
				} else {
					return null;
				}
			}
		}
	};
	
	private static void setOffspringFlags() {
		offspring().setFlag(NPCFlagValue.flagOffspringIntroduced, true);
		offspring().setReactedToPregnancy(true);
		offspring().setReactedToPlayerPregnancy(true);
		Main.game.getDialogueFlags().offspringDialogueTokens = 2;
	}
	
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_APOLOGY = new DialogueNodeOld("A familiar face", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded) && offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Deciding that you need to apologise for both attacking, and then raping your [npc.daughter], you step forwards and start to speak,"
							+ " [pc.speech([npc.Name], I really am sorry for what I've done to you. I'm truly sorry...)]"
						+ "</p>");
				
			} else if (offspring().hasFlag(NPCFlagValue.flagOffspringFightApologyNeeded)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Deciding that you need to apologise for attacking your [npc.daughter], you step forwards and start to speak,"
							+ " [pc.speech([npc.Name], I really am sorry for what I've done to you. I'm truly sorry...)]"
						+ "</p>");
				
			} else if(offspring().hasFlag(NPCFlagValue.flagOffspringRapeApologyNeeded)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Deciding that you need to apologise for attacking your [npc.daughter], you step forwards and start to speak,"
							+ " [pc.speech([npc.Name], I really am sorry for what I've done to you. I'm truly sorry...)]"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Deciding that you need to apologise for treating your [npc.daughter] so poorly, you step forwards and start to speak,"
							+ " [pc.speech([npc.Name], I really am sorry for the way I've treated you. I know I've done some bad things, but please believe me when I say that I'm truly sorry.)]"
						+ "</p>");
			}
			
			if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
				UtilText.nodeContentSB.append("<p>"
							+ "[npc.Name] turns towards you and puts [npc.her] [npc.hands] on [npc.her] [npc.hips],"
							+ " [npc.speech(You're damn right you're sorry! And you're going to need to say more than that if you want me to forgive you!)]"
						+ "</p>"
						+ "<p>"
							+ "You continue to apologise to your [npc.daughter] for some time, and while [npc.she] frequently interrupts you to berate you, you can tell that your words are having a positive effect."
							+ " After a while of this back-and-forth, you run out of ways in which to say sorry, and [npc.name] finally stops shouting at you, before stepping back and sighing."
						+ "</p>"
						+ "<p>"
							+ "A heavy silence fills the air, and just as you're about to say something to break it, [npc.name] finally speaks once more,"
							+ " [npc.speech(I need some time to think about this. Just... Eugh! Come back later if you really care.)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] spins around and hurriedly walks off, [npc.her] words echoing in your [pc.ears] as you watch [npc.herHim] turn the corner and disappear out of sight."
						+ "</p>"
						+ "<p>"
							+ "[pc.thought(That could have gone better,)] you think to yourself as you prepare to continue on your way,"
							+ " [pc.thought(then again, it could also have gone a lot worse...)]"
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append("<p>"
						+ "[npc.Name] turns towards you and crosses [npc.her] [npc.arms],"
						+ " [npc.speech(Yeah? Go on, I'm listening.)]"
					+ "</p>"
					+ "<p>"
						+ "You continue to apologise to your [npc.daughter] for some time, and while [npc.she] frequently turns [npc.her] back on you, before spinning around to mention yet another thing you've done wrong,"
							+ " you can tell that your words are having a positive effect."
						+ " After a while of this back-and-forth, you run out of ways in which to say sorry, and [npc.name] lets out a deep sigh, before starting to pace back and forth in front of you."
					+ "</p>"
					+ "<p>"
						+ "A heavy silence fills the air, and just as you're about to say something to break it, [npc.name] finally speaks once more,"
						+ " [npc.speech(I need some time to think about this. Just... Eugh! Come back later if you really care.)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] spins around and hurriedly walks off, [npc.her] words echoing in your [pc.ears] as you watch [npc.herHim] turn the corner and disappear out of sight."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(That could have gone better,)] you think to yourself as you prepare to continue on your way,"
						+ " [pc.thought(then again, it could also have gone a lot worse...)]"
					+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Give [npc.name] some time to think, and continue on your way.", OFFSPRING_ENCOUNTER_APOLOGY) {
					@Override
					public DialogueNodeOld getNextDialogue(){
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_FIGHT = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
					+ "Just because [npc.she]'s your [npc.daughter], that doesn't mean [npc.she]'s any better than the rest of the low-lifes inhabiting these alleyways."
					+ " Lowering into a combat stance, you grin at [npc.name],"
					+ " [pc.speech(No [npc.daughter] of mine is going to live like this and expect not to be punished! I'm going to teach you a lesson!)]"
				+ "</p>");
			
			if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.speech(What the hell?!)]"
							+ " [npc.name] cries out, before jumping back and preparing to defend [npc.herself],"
							+ " [npc.speech(You'll be sorry!)]"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.speech(W-What?!)]"
							+ " [npc.name] cries out, before jumping back and preparing to defend [npc.herself],"
							+ " [npc.speech(I can't believe this!)]"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "You find yourself fighting your [npc.daughter]!", offspring()) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.fightOffspringInApartment, true);
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
						};
				};
			} else {
				return null;
			}
		}
	};
	
	private static StringBuilder footerSB = new StringBuilder();
	
	private static String getFooterInformationText() {
		footerSB.setLength(0);

		//TODO affection
		
		// Attraction:
		if(offspring().isAttractedTo(Main.game.getPlayer())) {
			footerSB.append(
					"<p>"
						+ "<i>You notice [npc.name]'s gaze flick down as [npc.she] tries to take an unnoticed peek at your body."
						+ " From the hungry look in [npc.her] [npc.eyes], you can tell that [npc.she]'s attracted to you...</i>"
					+ "</p>");
		} else {
			footerSB.append(
					"<p>"
						+ "<i>[npc.Name] doesn't show any sign of being attracted to you, and any affection that [npc.she] shows is no doubt simply due to your [pc.mother]-[npc.daughter] relationship.</i>"
					+ "</p>");
		}
		
		// Time limitation:
		if(Main.game.getDialogueFlags().offspringDialogueTokens>=1) {
			footerSB.append("<p>"
					+ "[npc.Name] only has time for <b>"+Util.intToString(Main.game.getDialogueFlags().offspringDialogueTokens)+" more thing"+(Main.game.getDialogueFlags().offspringDialogueTokens>1?"s":"")+"</b> to discuss."
				+ "</p>");
		} else {
			footerSB.append("<p>"
					+ "[npc.Name] <b>doesn't have any time left</b>, and needs to get back to work."
				+ "</p>");
		}
		
		return footerSB.toString();
	}
	
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_TALKING = new DialogueNodeOld("", "You encounter a certain special someone in the alleyway.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!offspring().hasFlag(NPCFlagValue.flagOffspringApartmentIntroduced)) {
				UtilText.nodeContentSB.append("<p>"
							+ "[npc.Name] turns around and sets off down the alleyway."
							+ " Following in [npc.her] footsteps, you allow your [npc.daughter] to take the lead as [npc.she] guides you through the trail of narrow passageways that lead to [npc.her] home."
							+ " As the width of the alleyways rarely offers you the chance to walk side-by-side, you decide to save conversation for when you finally arrive, leading to your five-minute journey being mostly in silence."
						+ "</p>"
						+ "<p>"
							+ "Eventually, [npc.name] steps off to one side, and, producing a set of keys, [npc.she] unlocks an unassuming wooden door, before stepping inside and calling for you to follow [npc.herHim]."
							+ " Although there's not much of an exterior to look at, as you step into your [npc.daughter]'s home, you find that the interior is far nicer, and larger, than you'd expected."
						+ "</p>"
						+ "<p>"
							+ "Looking down the length of the apartment, you see that there are both windows and a glass door set into the far end, allowing access to what appears to be a small, shared garden."
							+ " A couple of doors off to the left-hand side hint at this place having two bedrooms, while the main body of the residence is used as a combined living and dining-room,"
								+ " with a small section partitioned off at the far end serving as a kitchen."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(This is a nice place you've got,)]"
							+ " you say, and even if you don't approve of the methods in which [npc.she] earns [npc.her] rent, you're happy that your [npc.daughter] has a roof over [npc.her] head,"
							+ " [pc.speech(and you seem to be keeping the place clean, good [npc.girl]!)]"
						+ "</p>"
						+ "<p>"
							+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH?"[npc.speech(Thanks [npc.pcName]!)]":"[npc.speech([npc.pcName]... You're embarrassing me...)]")
							+ " [npc.name] replies, locking the door after you and motioning for you to take a seat, before heading over to the kitchen to make you a drink."
							+ " After just a moment [npc.she] returns, and sits down on the sofa next to you,"
							+ " [npc.speech(So, what do you want to talk about? I only have a little while before I need to get back to... erm... 'work', but let's make the most of our time together!)]"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("<p>"
						+ "[npc.Name] turns around and sets off down the alleyway."
						+ " Following in [npc.her] footsteps, you allow your [npc.daughter] to take the lead as [npc.she] guides you through the trail of narrow passageways that lead to [npc.her] home."
						+ " As the width of the alleyways rarely offers you the chance to walk side-by-side, you decide to save conversation for when you finally arrive, leading to your five-minute journey being mostly in silence."
					+ "</p>"
					+ "<p>"
						+ "Eventually, you recognise that you're almost at your destination, and sure enough, [npc.name] soon steps off to one side, before unlocking the door to [npc.her] apartment and stepping inside."
						+ " Following your [npc.daughter] inside, you're once again pleasantly surprised to find that the spacious interior is in a clean and orderly state."
					+ "</p>"
					+ "<p>"
						+ "Looking down the length of the apartment, you see that there are both windows and a glass door set into the far end, allowing access to what appears to be a small, shared garden."
						+ " A couple of doors off to the left-hand side hint at this place having two bedrooms, while the main body of the residence is used as a combined living and dining-room,"
							+ " with a small section partitioned off at the far end serving as a kitchen."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Good [npc.girl], you're keeping this place nice and clean,)]"
						+ " you say, turning to smile at [npc.name]."
					+ "</p>"
					+ "<p>"
						+ (offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH?"[npc.speech(Thanks [npc.pcName], I'm doing my best!)]":"[npc.speech([npc.pcName]... You're embarrassing me again...)]")
						+ " [npc.name] replies, locking the door after you and motioning for you to take your usual seat, before heading over to the kitchen to fetch some refreshments."
						+ " After just a moment [npc.she] returns, and sits down on the sofa next to you,"
						+ " [npc.speech(So, what do you want to talk about? I only have a little while before I need to get back to... erm... 'work', but let's make the most of our time together!)]"
					+ "</p>");
			}

			UtilText.nodeContentSB.append(getFooterInformationText());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().offspringDialogueTokens<=0) {
				if (index == 1) {
					return new Response("Time to go", "[npc.Name] has started glancing at the clock on the wall, giving you a clear indication that it's time to make your exit.", OFFSPRING_ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "[npc.Name] has been glancing at the clock on the wall for the last few minutes, and as you finish what you were talking about, [npc.she] stands up,"
										+ " [npc.speech(Sorry [npc.pcName], I really need to be getting on now...)]"
									+ "</p>"
									+ "<p>"
										+ "You follow your [npc.daughter]'s lead and get up off the sofa,"
										+ " [pc.speech(Ok, well I hope you don't mind me coming to visit some other time!)]"
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Not at all! I'd love to see you again!)]"
										+ " [npc.she] replies, showing you to the door."
										+ " You both say your farewells, and after just a few moments, you find yourself back out in the alleyways once again..."
									+ "</p>");
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
						}
						@Override
						public DialogueNodeOld getNextDialogue() {
							return DebugDialogue.getDefaultDialogueNoEncounter();
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Background", "Ask [npc.name] about [npc.her] background, and about what [npc.she] does for a living.", OFFSPRING_ENCOUNTER_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 2) {
					return new Response("Small talk", "Chat about this and that with [npc.name].", OFFSPRING_ENCOUNTER_SMALL_TALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 5));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 3) {
					return new Response("Encourage", "Encourage [npc.name] to do [npc.her] best.", OFFSPRING_ENCOUNTER_ENCOURAGE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 4) {
					return new Response("Scold", "Scold [npc.name], and tell [npc.herHim] to better [npc.herself].", OFFSPRING_ENCOUNTER_SCOLD) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -10));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 5) {
					return new Response("Pet name", "Ask [npc.name] to call you by a different name.", OFFSPRING_ENCOUNTER_CHOOSE_NAME) {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}
					};
					
				} else if (index == 6) {
					if(offspring().getAffection(Main.game.getPlayer()) < AffectionLevel.POSITIVE_FIVE_WORSHIP.getMinimumValue()) {
						return new Response("Inventory", "[npc.Name] doesn't like you enough to allow you to choose what [npc.she] wears, or what [npc.she] eats and drinks.", null);
					} else {
						return new ResponseEffectsOnly("Inventory", "Manage [npc.name]'s inventory.") {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "You shuffle a bit closer to [npc.name], and [npc.she] looks up at you expectantly as [npc.she] asks,"
											+ " [npc.speech(What is it [npc.pcName]?)]"
										+ "</p>"
										+ "<p>"
											+ "[pc.speech(I think we need to have a little talk about the state of your clothes,)]"
											+ " you reply, looking up and down over you [npc.daughter]'s body."
										+ "</p>"
										+ "<p>"
											+ "[npc.speech(Yes [npc.pcName]...)] [npc.she] obediently answers, clearly comfortable with doing whatever [npc.she]'s told."
										+ "</p>");
								Main.mainController.openInventory(offspring(), InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					}
					
				} else if (index == 7) {
					if(Main.game.getPlayer().hasItemType(ItemType.PRESENT)) {
						return new Response("Give Present", "Give [npc.name] the present that you're carrying.", OFFSPRING_PRESENT) {
							@Override
							public void effects() {
								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.PRESENT));
								
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 15));
								
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
								Main.game.getDialogueFlags().offspringDialogueTokens--;
							}
						};
					} else {
						return null;
					}
					
				} else if (index == 8) {
					return new Response("Sex", "Tell [npc.name] that you want to have sex with [npc.herHim].", OFFSPRING_ENCOUNTER_SEX,
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_INCEST)),
							CorruptionLevel.FIVE_CORRUPT,
							null,
							null,
							null) {
						@Override
						public void effects() {
							if(offspring().isAttractedTo(Main.game.getPlayer())) {
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 20));
							} else if(offspring().getHistory()!=History.PROSTITUTE){
								Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -10));
							}
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							Main.game.getDialogueFlags().offspringDialogueTokens--;
						}	
					};
					
				} else if (index == 10) {
	
					return new Response("Attack", "It's time to show [npc.herHim] [npc.her] true place in this family!", OFFSPRING_ENCOUNTER_APARTMENT_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), -100));
							offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", OFFSPRING_ENCOUNTER) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ "As you finish what you were talking about, you glance up at the clock on the wall, letting out a little gasp as you see what the time is,"
											+ " [pc.speech(Sorry [npc.name], I really need to be getting on now...)]"
										+ "</p>"
										+ "<p>"
											+ "Your [npc.daughter] follows your lead as you stand up and get up off the sofa,"
											+ " [npc.speech(Ok [npc.pcName], come back to visit soon!)]"
										+ "</p>"
										+ "<p>"
											+ "[pc.speech(Of course, I'll visit you again as soon as I can,)]"
											+ " you reply, allowing [npc.name] to show you to the door."
											+ " You both say your farewells, and after just a few moments, you find yourself back out in the alleyways once again..."
										+ "</p>");
								offspring().setFlag(NPCFlagValue.flagOffspringApartmentIntroduced, true);
							}
							@Override
							public DialogueNodeOld getNextDialogue() {
								return DebugDialogue.getDefaultDialogueNoEncounter();
							}
						};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_BACKGROUND = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			// TODO use offspring().flagBackgroundProgress
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Deciding that you'd like to get to know [npc.name] a little better, you ask [npc.herHim] about how [npc.she]'s doing,"
						+ " [pc.speech(Tell me about your life! How's everything going for you right now?)]"
					+ "</p>");
			
			if(offspring().getHistory()==History.PROSTITUTE) {
				if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[npc.She] leans back on the sofa and smiles,"
								+ " [npc.speech(Well, you know, there's always <i>someone</i> looking for a quick fuck, so I never have any problems with money."
									+ " I mean, Dominion's a pretty great place for being a prostitute!)]"
							+ "</p>"
							+ "<p>"
								+ "From [npc.her] positive attitude, you can tell that [npc.she] has absolutely no qualms about selling [npc.her] body."
								+ " Before you can offer a response, [npc.she] continues,"
								+ " [npc.speech(So yeah, everything's pretty good for me at the moment. Sure, the location of this place could be better, but it's nice enough!)]"
							+ "</p>"
							+ "<p>"
								+ "You're relieved to hear that your [npc.daughter] is doing well for [npc.herself], and after talking about [npc.her] situation a little more,"
									+ " [npc.she] starts to get carried away and tells you about one of the recent customers [npc.she] had, ");
					
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.speech(... So then he doesn't pull out, and he's all like 'Get pregnant slut!', and I'm like 'Ah, no! I can't get pregnant, please!', and then I get him to pay me extra for finishing inside!"
									+ " And the funny thing is, I was on slut pills the whole time!)]",
							"[npc.speech(... So then she's like 'Oh yeah, my boyfriend is joining in too', and I'm like 'Uhh, that's going to cost double', and so she pays me double, and then her boyfriend doesn't even use me at all!)]",
							"[npc.speech(... And his knot was like, <i>huge</i>, and it locks us together for about 30 minutes. And I'm just like 'Uhh, I charge by the minute by the way!')]",
							"[npc.speech(... So this cute little human wanted me to just sit on her face for half an hour, and it was, like, the easiest money I've ever made!)]",
							"[npc.speech(... And so he's pushing me up against the wall, getting really into fucking me, and then he suddenly pulls out and cums all over my shoes! And I'm like, 'You're paying the cleaning bill for those!')]"));
					
					UtilText.nodeContentSB.append("</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[npc.She] leans back on the sofa and smiles,"
								+ " [npc.speech(Well, you know, there's always <i>someone</i> who has need of my... erm... services, so I never have any problems with money."
									+ " I mean, Dominion's a pretty great place for my line of work... erm... being a prostitute, that is...)]"
							+ "</p>"
							+ "<p>"
								+ "From [npc.her] attitude, you can tell that [npc.she] has no qualms about selling [npc.her] body, although [npc.she]'s a little nervous talking to you about it."
								+ " Before you can offer a response, [npc.she] continues,"
								+ " [npc.speech(So yeah, everything's pretty good for me at the moment. Sure, the location of this place could be better, but it's nice enough!)]"
							+ "</p>"
							+ "<p>"
								+ "You're relieved to hear that your [npc.daughter] is doing well for [npc.herself], and after talking about [npc.her] situation a little more,"
									+ " [npc.she] starts to open up, and gets a little carried away in telling you about one of the recent customers [npc.she] had, ");
					
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.speech(... So then he doesn't pull out, and he's all like 'Get pregnant slut!', and I'm like 'That's going to cost you more you know!', and so he pays me extra for finishing inside!"
									+ " And I didn't tell him, but I was on slut pills the whole time!)]",
							"[npc.speech(... So then she's like 'Oh yeah, my boyfriend is joining in too', and I'm like 'Uhh, that's going to cost you more', and so she pays me a <i>lot</i> more,"
									+ " and then her boyfriend just fucked [npc.herHim] in front of me, and didn't use me at all!)]",
							"[npc.speech(... And his knot was <i>huge</i>, and it locked us together for about 30 minutes! And it was like, the most awkward thing ever!)]",
							"[npc.speech(... So this cute human wanted to just sit on my face for half an hour, and it was pretty much the easiest money I've ever made!)]",
							"[npc.speech(... And so he's pushing me up against the wall, getting really into fucking me, and then he suddenly pulls out and cums all over my shoes!"
									+ " And I'm like, 'Erm... You're going to have to pay the cleaning bill for those...')]"));
					
					UtilText.nodeContentSB.append("</p>");
				}
				
			} else {
				if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[npc.She] leans back on the sofa and smiles,"
								+ " [npc.speech(Well, you know, there's always <i>someone</i> dumb enough to wander around in my territory, so I never have any problems with money."
									+ " I mean, if they didn't want to get mugged, they wouldn't come wandering through the alleyways! Everyone knows that they're dangerous!)]"
							+ "</p>"
							+ "<p>"
								+ "From [npc.her] blase attitude, you can tell that [npc.she] has absolutely no qualms about beating people up and stealing their money."
								+ " Before you can offer a response, [npc.she] continues,"
								+ " [npc.speech(So yeah, everything's pretty good for me at the moment. Sure, the location of this place could be better, but it's nice enough!)]"
							+ "</p>"
							+ "<p>"
								+ "Wanting to learn more about the manner in which [npc.she] earns a living, you encourage your [npc.daughter] to talk about [npc.her] situation a little more,"
									+ " and as you ask a few more questions, [npc.she] starts to get carried away while giving you the details of one of the recent encounters [npc.she] had, ");
					
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.speech(... And <i>he</i> was the one who attacked me first! So it was only fair that I took everything he had!)]",
							"[npc.speech(... So then as she runs off, she's like 'You're gonna be sorry when I tell my boyfriend!', and then later this horse-boy shows up claiming to be her boyfriend, so I beat him up too!)]",
							"[npc.speech(... And then the enforcers turned up, and I only just managed to get away! That was a close one...)]",
							"[npc.speech(... So after I'd robbed her, that slutty cat-girl starts begging for me to 'show her no mercy', and drops down on all fours right there in the alley!)]",
							"[npc.speech(... And I'm pushing him back against the wall, demanding he hands over his wallet, and I feel something pressing out against my leg. That horny dog-boy was getting turned on by being robbed!)]"));
					
					UtilText.nodeContentSB.append("</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "[npc.She] leans back on the sofa and smiles,"
								+ " [npc.speech(Well, you know, there's always <i>someone</i> who's... erm... intruding on my territory, so I never have any problems with money."
									+ " I mean, Dominion's alleys are known to be dangerous, so why would they be here if they didn't want to get robbed?)]"
							+ "</p>"
							+ "<p>"
								+ "From [npc.her] blase attitude, you can tell that [npc.she] has absolutely no qualms about beating people up and stealing their money, although [npc.she]'s a little nervous talking to you about it."
								+ " Before you can offer a response, [npc.she] continues,"
								+ " [npc.speech(So yeah, everything's pretty good for me at the moment. Sure, the location of this place could be better, but it's nice enough!)]"
							+ "</p>"
							+ "<p>"
								+ "Wanting to learn more about the manner in which [npc.she] earns a living, you encourage your [npc.daughter] to talk about [npc.her] situation a little more,"
									+ " and as you ask a few more questions, [npc.she] starts to get carried away while giving you the details of one of the recent encounters [npc.she] had, ");

					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.speech(... And <i>he</i> was the one who attacked me first! So it was only fair that I robbed him to teach him a lesson!)]",
							"[npc.speech(... So then as she runs off, she shouts 'You're gonna be sorry when I tell my boyfriend!', and then later this huge horse-boy shows up claiming to be her boyfriend, and I barely managed to get away!)]",
							"[npc.speech(... And then the enforcers turned up, and I only managed to get away by climbing up a fire escape! That was a close one...)]",
							"[npc.speech(... So after I'd robbed her, that slutty cat-girl starts begging for me to 'show her no mercy', and drops down on all fours right there in the alley!)]",
							"[npc.speech(... So as I'm demanding he hands over his wallet, I see something pressing out in his trousers. That horny dog-boy was getting turned on by being robbed!)]"));
					
					UtilText.nodeContentSB.append("</p>");
				}
			}
			
			UtilText.nodeContentSB.append(getFooterInformationText());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Background", "You've just talked about [npc.name]'s background.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_SMALL_TALK = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You start to talk with [npc.name] about all sorts of unimportant things, ranging from the recent weather you've been having, to the state of Dominion's streets and alleyways."
						+ " Talking to your [npc.daughter] fills you with a deep sense of calm and happiness, and every time [npc.she] smiles and offers [npc.her] opinion on something,"
							+ " you listen with rapt attention to every word that comes out of [npc.her] mouth."
					+ "</p>");
			
			
			switch(offspring().getAffectionLevel(Main.game.getPlayer())) {
				case NEGATIVE_FIVE_LOATHE: case NEGATIVE_FOUR_HATE:case NEGATIVE_THREE_STRONG_DISLIKE: case NEGATIVE_TWO_DISLIKE:case NEGATIVE_ONE_ANNOYED:case ZERO_NEUTRAL:
					UtilText.nodeContentSB.append(
							"<p>"
								+ "After a short while, [npc.she] offers you another drink, and after happily accepting, you sit with your [npc.daughter] on the sofa in [npc.her] apartment,"
									+ " talking away as though you've known each other forever."
							+ "</p>");
					break;
				// TODO improve the following:
				case POSITIVE_ONE_FRIENDLY:
					if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "After a short while, [npc.she] offers you another drink, and after happily accepting, your [npc.daughter] hurries off to the kitchen to fetch it for you."
									+ " When [npc.she] returns, [npc.she] sits down right beside you on the sofa, smiling in a friendly manner as [npc.she] hands you your glass,"
										+ " before the two of you continue talking away as though you've known each other forever."
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "After a short while, [npc.she] offers you another drink, and after happily accepting, your [npc.daughter] hurries off to the kitchen to fetch it for you."
									+ " When [npc.she] returns, [npc.she] sits down beside you on the sofa, smiling in a friendly manner as [npc.she] hands you your glass,"
										+ " before the two of you continue talking away as though you've known each other forever."
								+ "</p>");
					}
					break;
				case POSITIVE_TWO_LIKE: case POSITIVE_THREE_CARING:
					if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "After a short while, [npc.she] offers you another drink, and after happily accepting, your [npc.daughter] hurries off to the kitchen to fetch it for you."
									+ " When [npc.she] returns, [npc.she] sits down right beside you on the sofa, smiling in a loving manner as [npc.she] hands you your glass,"
										+ " before the two of you continue talking away as though you've known each other forever."
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "After a short while, [npc.she] offers you another drink, and after happily accepting, your [npc.daughter] hurries off to the kitchen to fetch it for you."
									+ " When [npc.she] returns, [npc.she] sits down beside you on the sofa, smiling in a loving manner as [npc.she] hands you your glass,"
										+ " before the two of you continue talking away as though you've known each other forever."
								+ "</p>");
					}
					break;
				case POSITIVE_FOUR_LOVE: case POSITIVE_FIVE_WORSHIP:
					if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH) {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "After a short while, [npc.she] offers you another drink, and after happily accepting, your [npc.daughter] hurries off to the kitchen to fetch it for you."
									+ " When [npc.she] returns, [npc.she] sits down right beside you on the sofa, smiling in a loving manner as [npc.she] hands you your glass,"
										+ " before the two of you continue talking away as though you've known each other forever."
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(
								"<p>"
									+ "After a short while, [npc.she] offers you another drink, and after happily accepting, your [npc.daughter] hurries off to the kitchen to fetch it for you."
									+ " When [npc.she] returns, [npc.she] sits down beside you on the sofa, smiling in a loving manner as [npc.she] hands you your glass,"
										+ " before the two of you continue talking away as though you've known each other forever."
								+ "</p>");
					}
					break;
				default:
					break;
			}
			
			UtilText.nodeContentSB.append(getFooterInformationText());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Small talk", "You've just had some small talk with [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_ENCOURAGE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			// Encourage job
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Thinking that it must be hard for [npc.name] to make a living out here in Dominion's alleyways, you decide to offer [npc.her] some words of encouragement,");
			
			if(offspring().getHistory()==History.PROSTITUTE) {
				UtilText.nodeContentSB.append(
						" [pc.speech(I know that it must be hard to try and make a living out here, so I just wanted you to know that I'm very proud of you, [npc.name].)]"
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						" [pc.speech(I know that it must be hard to try and make a living out here, so I just wanted you to know that I'm happy to see that you're taking good care of yourself, [npc.name].)]"
						+ "</p>");
			}
			
			if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.Name] blushes a little, and casts [npc.her] [npc.eyes] to the floor as [npc.she] replies,"
							+ " [npc.speech([npc.PcName], stop! You're embarrassing me!)]"
						+ "</p>"
						+ "<p>"
							+ "Despite [npc.her] reaction, you can tell that your words have made a positive difference, and when [npc.she] looks up again, you see that there's a big smile on [npc.her] face."
							+ " Reiterating what you've just said, you go on to encourage your [npc.daughter] to do [npc.her] best, and once you've finished, [npc.she] sincerely thanks you for your kind words."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.Name] smiles as [npc.she] hears your words, and happily replies,"
							+ " [npc.speech(Thanks [npc.pcName], that really means a lot to me.)]"
						+ "</p>"
						+ "<p>"
							+ "From the sincerity of [npc.her] reaction, you can tell that your words have made a positive difference, and the big smile on [npc.her] face fills your heart with happiness."
							+ " Reiterating what you've just said, you go on to encourage your [npc.daughter] to do [npc.her] best, and once you've finished, [npc.she] lovingly thanks you for your kind words."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(getFooterInformationText());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Encourage", "You've just encouraged [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_SCOLD = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			// Scolding related to job
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Unimpressed by [npc.name]'s method of making a living, you decide to scold [npc.her] in the hopes that [npc.she]'ll change [npc.her] ways,");
			
			if(offspring().getHistory()==History.PROSTITUTE) {
				UtilText.nodeContentSB.append(
						" [pc.speech(I really don't want my [npc.daughter] working as a prostitute. You need to start thinking about getting a real job, [npc.name]!)]"
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						" [pc.speech(I really don't want my [npc.daughter] out there in Dominion's alleys mugging people. You need to start thinking about getting a real job, [npc.name]!)]"
						+ "</p>");
			}
			
			if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.Name] leans back on the sofa, letting out an annoyed snort as [npc.she] hears your words. Rolling [npc.her] [npc.eyes], [npc.she] replies,"
							+ " [npc.speech(Don't come in here and try to tell me how to live my life, [npc.pcName]! I can do what I want!)]"
						+ "</p>"
						+ "<p>"
							+ "You don't like [npc.her] insolent attitude, and after [npc.she] stops speaking, you start to berate [npc.her] some more."
							+ " Reiterating what you've just said, you tell your [npc.daughter] that you expect far more from [npc.herHim], and that you don't want [npc.herHim] prowling the alleyways any longer."
							+ " You're not sure if your words really have any effect, but at least [npc.she] now knows that [npc.her] [pc.mother] doesn't approve of the way in which [npc.she]'s living [npc.her] life."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.Name] leans back on the sofa, letting out a little sigh as [npc.she] hears your words. Rolling [npc.her] [npc.eyes], [npc.she] replies,"
							+ " [npc.speech(This is how I'm choosing to live my life, [npc.pcName]! I can do what I want!)]"
						+ "</p>"
						+ "<p>"
							+ "You don't like [npc.her] insolent attitude, and after [npc.she] stops speaking, you start to berate [npc.her] some more."
							+ " Reiterating what you've just said, you tell your [npc.daughter] that you expect far more from [npc.herHim], and that you don't want [npc.herHim] prowling the alleyways any longer."
							+ " You're not sure if your words really have any effect, but at least [npc.she] now knows that [npc.her] [pc.mother] doesn't approve of the way in which [npc.she]'s living [npc.her] life."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(getFooterInformationText());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 4 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Scold", "You've just scolded [npc.name].", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_CHOOSE_NAME = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You decide to ask [npc.name] to call you by a different name."
						+ " At the moment, [npc.she]'s calling you '[npc.pcName]'."
					+ "</p>"
					
					// TODO align this properly
					
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display: inline-block; padding:0 auto; margin:0 auto;vertical-align:middle;width:100%;'>"
							+ "<p style='float:left; padding:0; margin:0; height:32px; line-height:32px;'>[npc.Name] will call you: </p>"
							+ "<form style='float:left; padding:auto 0 auto 0;'><input type='text' id='offspringPetNameInput' value='"+ Util.formatForHTML(offspring().getPlayerPetName())+ "'></form>"
							+ " <div class='SM-button' id='"+offspring().getId()+"_PET_NAME' style='float:left; width:auto; height:28px;'>"
								+ "Rename"
							+ "</div>"
						+ "</div>"
						+ "<p>"
						+ "<i>The names 'Mom'/'Dad' and 'Mommy'/'Daddy' are special, and will automatically switch to the appropriate femininity of your character.</i>"
						+ "</p>"
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			UtilText.nodeContentSB.append(getFooterInformationText());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Pet name", "You're already asking [npc.name] to call you by a different name.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_PRESENT = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Deciding that it would be nice to give your [npc.daughter] a present this Yuletide, you hold out the gift towards [npc.herHim],"
						+ " [pc.speech(This is for you, [npc.name]. Happy Yuletide!)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.Name] lets out a little gasp as you give [npc.herHim] the present, and, looking up into your [pc.eyes], [npc.she] cries out,"
						+ " [npc.speech([npc.pcName]! Thank you so much! I-I don't know what to say!)]"
					+ "</p>"
					+ "<p>"
						+ "Choosing to express [npc.her] gratitude in a more physical form, [npc.name] places the present to one side, before moving up next to you and pulling you into a loving hug."
						+ " You return your [npc.daughter]'s warm embrace, and spend a few moments pressing your body against [npc.hers] as you both share in a loving moment together."
					+ "</p>"
					+ "<p>"
						+ "After a little while, [npc.name] pulls back, before smiling up at you,"
						+ " [npc.speech(Really, thank you [npc.pcName]. That really means a lot to me...)]"
					+ "</p>"
					);
			
			UtilText.nodeContentSB.append(getFooterInformationText());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 7 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
				return new Response("Give Present", "You're already giving [npc.name] a present.", null);
				
			} else {
				return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
			}
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_SEX = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "From the moment you entered [npc.name]'s apartment, you haven't been able to take your [pc.eyes] off of [npc.herHim]."
						+ " The fact that [npc.she]'s your [npc.daughter] is only serving to make you even more aroused, and as [npc.she] smiles "+(offspring().isFeminine()?"sweetly":"charmigly")+" at you once more, you can't help but act."
					+ "</p>");
			
			if(offspring().getHistory()==History.PROSTITUTE){
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[pc.speech(So, [npc.name],)] you say, putting on your most seductive voice as you shuffle closer to [npc.herHim] on the sofa,"
							+ " [pc.speech(you must be pretty experienced in bed, what with your line of work and all... Perhaps you'd like to teach me a few of your tricks?)]"
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[pc.speech(So, [npc.name],)] you say, putting on your most seductive voice as you shuffle closer to [npc.herHim] on the sofa,"
							+ " [pc.speech(you must have taken a little more than just cash from some of the people wandering through 'your territory'... Perhaps you'd like to show me some of the things you do to them?)]"
						+ "</p>");
			}
			
			if(offspring().isAttractedTo(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.Name]'s mouth opens a little as [npc.she] lets out a desperate little whine."
							+ " With that as your only warning, your [npc.daughter] suddenly lurches forwards, grabbing your head in both [npc.hands] as [npc.she] desperately presses [npc.her] [npc.lips] to yours."
						+ "</p>"
						+ "<p>"
							+ "Reaching up to pull [npc.herHim] close, you eagerly return [npc.name]'s passionate kiss, and, right there in the middle of [npc.her] apartment, you show your [npc.daughter] just how much you love [npc.herHim]..."
						+ "</p>");
				
			} else if(offspring().getHistory()==History.PROSTITUTE){
				UtilText.nodeContentSB.append(
						"<p>"
							+ "A flicker of worry crosses [npc.name]'s face for a moment, but [npc.she] quickly regains [npc.her] composure,"
							+ " [npc.speech(Well, I <i>could</i> take you as a client, but this is strictly professional, ok? I do love you, but not quite in the same way that I think you love me...)]"
						+ "</p>"
						+ "<p>"
							+ "Although you're disappointed to hear that [npc.she]'s not interested in having sex with you, you're nonetheless relieved to hear that there's still an opportunity to get what you want."
							+ " Before you can respond, your [npc.daughter] continues,"
							+ " [npc.speech(Usually I charge fifty flames for an hour, but it's kind of weird the way you're my [pc.mom] and all, so I'm going to have to charge double.)]"
						+ "</p>"
						+ "<p>"
							+ "You wonder if you should pay [npc.name] the 100 flames that [npc.she]'s asking for, or just forget it and do something else..."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.Name] backs away from you with a very worried look on [npc.her] face,"
							+ " [npc.speech([npc.pcName]?! What the hell?! I'm not going to have sex with my own [pc.mother]!)]"
						+ "</p>"
						+ "<p>"
							+ (Main.game.getPlayer().isFeminine()?"You pout":"You frown") + " at your [npc.daughter], upset that [npc.she]'s not interested in what you had in mind,"
								+ " [pc.speech(Well, the offer's out there, if you ever change your mind.)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Ok, can we move on and pretend that this never happened now?!)]"
							+ " [npc.she] says, clearly upset by this turn of events."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(offspring().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Incestuous sex",
							"It's time to show your [npc.daughter] what [npc.her] [pc.mother] can do!",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_CONSENSUAL);
					
				} else if (index == 2) {
					return new ResponseSex("Submissive sex",
							"It's time to let your [npc.daughter] show you what [npc.she] can do!",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_CONSENSUAL);
					
				} else {
					return null;
				}
				
			} else if(offspring().getHistory()==History.PROSTITUTE){
				if (index == 8) {
					if(Main.game.getPlayer().getMoney()>=100) {
						return new ResponseSex("Incestuous sex ("+UtilText.formatAsMoney(100, "span")+")",
								"Pay your [npc.daughter] 100 flames to get what you want!",
								true, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_SUBMISSIVE))),
								AFTER_SEX_CONSENSUAL);
					} else {
						return new Response("Pay "+UtilText.formatAsMoneyUncoloured(100, "span"), "You don't have enough money...", null);
					}
					
				} else if (index == 9) {
					if(Main.game.getPlayer().getMoney()>=100) {
						return new ResponseSex("Submissive sex ("+UtilText.formatAsMoney(100, "span")+")",
								"Pay your [npc.daughter] 100 flames to get what you want!",
								true, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								AFTER_SEX_CONSENSUAL);
					} else {
						return new Response("Pay "+UtilText.formatAsMoneyUncoloured(100, "span"), "You don't have enough money...", null);
					}
				} else {
					return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
				}
				
				
			} else {
				if (index == 8 && Main.game.getDialogueFlags().offspringDialogueTokens>0) {
					return new Response("Sex", "You've just asked [npc.name] to have sex with you...", null);
					
				} else {
					return OFFSPRING_ENCOUNTER_TALKING.getResponse(0, index);
				}
			}
			
		}
	};
	
	public static final DialogueNodeOld OFFSPRING_ENCOUNTER_APARTMENT_FIGHT = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel(){
			return getOffspringLabel();
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
					+ "Deciding that [npc.name] needs to be taught a lesson, you get up off the sofa and step towards [npc.herHim],"
					+ " [pc.speech(No [npc.daughter] of mine is going to live like this and expect not to be punished! It's time that I put you in your place!)]"
				+ "</p>");
			
			if(offspring().getPersonality().get(PersonalityTrait.EXTROVERSION) == PersonalityWeight.HIGH || offspring().getPersonality().get(PersonalityTrait.NEUROTICISM) == PersonalityWeight.HIGH) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.speech(What the hell?!)]"
							+ " [npc.name] cries out, before jumping up and preparing to defend [npc.herself],"
							+ " [npc.speech(You'll be sorry!)]"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.speech(W-What?!)]"
							+ " [npc.name] cries out, before jumping up and preparing to defend [npc.herself],"
							+ " [npc.speech(I can't believe this!)]"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "You find yourself fighting your [npc.daughter]!", offspring()) {
					@Override
					public void effects() {
						offspring().setFlag(NPCFlagValue.fightOffspringInApartment, true);
						offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, true);
					}
				};
				
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
			if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parse(offspring(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " As [npc.she] looks up at you, you see that your powerful aura is having a strong effect on [npc.herHim], and there's a hungry, lustful look in [npc.her] [npc.eyes+]."
							+ " [npc.She] reaches down to [npc.her] crotch and starts stroking [npc.herself], making pitiful little whining noises as [npc.she] squirms on the floor."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Please [npc.pcName]! Fuck me!)], [npc.she] pleads, biting [npc.her] [npc.lip] as [npc.she] continues touching [npc.herself],"
							+ " [npc.speech(I'll be a good [npc.daughter]! Just please, fuck me!)]"
						+ "</p>");
				
			} else {
				return UtilText.parse(offspring(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " As [npc.she] looks up at you, you see that there's a desperate look of fear in [npc.her] [npc.eyes+]."
							+ " Making pitiful little whining noises, [npc.she] tries to shuffle away from you, clearly worried about what your intentions are."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Please [npc.pcName]! Don't! Leave me alone!)], [npc.she] pleads with tears in [npc.her] [npc.eyes],"
							+ " [npc.speech(I'll be a good [npc.daughter]! I promise!)]"
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			
			if (index == 1) {
				return new Response("Apologise", "Maybe you went too far... Perhaps you should apologise?", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "Looking down at your [npc.daughter] as [npc.she] shuffles about on the floor, you're suddenly overcome with regret, and, kneeling down next to [npc.herHim], you pull [npc.herHim] into a loving hug."
									+ " Leaning in over [npc.her] shoulder as you press yourself to [npc.herHim], you apologise for what you've done,"
									+ " [pc.speech([npc.Name], I'm so sorry! I don't know what came over me! Please forgive me!)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.She] hesitantly wraps [npc.her] [npc.arms] around you, and after a few moments, you feel [npc.her] grip tightening, before [npc.she] speaks out in a surprisingly stern voice,"
									+ " [npc.speech(I can't believe what you've done, [npc.pcName]! I'm going to need some time...)]"
								+ "</p>"
								+ "<p>"
									+ "[pc.speech(I understand,)]"
									+ " you say, pulling back from your [npc.daughter],"
									+ " [pc.speech(I really am sorry...)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.Name] walks you to the door in silence, and after trying to say sorry once more, only to have your [npc.daughter] slam the door in your face,"
										+ " you find yourself back out in the alleyways once again..."
								+ "</p>");
						Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 25));
						offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
					}
				};
				
			} else if (index == 2) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							null, null, null, null, null, null,
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down to take hold of one of [npc.name]'s [npc.arms], you pull [npc.herHim] to [npc.her] [npc.feet], before wrapping your [pc.arms] around [npc.her] back and stepping forwards."
								+ " Tilting your head to one side, you press your [pc.lips+] against [npc.hers] and start passionately thrusting your [pc.tongue+] into [npc.her] mouth."
							+ "</p>"
							+ "<p>"
								+ "Much to your delight, you feel [npc.herHim] returning your kiss, and as your [npc.daughter] leans into you, [npc.she] mumbles,"
								+ " [npc.speech(Yes [npc.pcName]... Fuck me...)]"
							+ "</p>");
					
				} else {
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...", Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)),
							null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] firmly in your embrace."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! No! Please!)]"
							+ "</p>") {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 3) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							null, null, null, null, null, null,
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY, "<p>"
								+ "Reaching down to take hold of one of [npc.name]'s [npc.arms], you gently lift [npc.herHim] to [npc.her] [npc.feet], before wrapping your [pc.arms] around [npc.her] back and stepping forwards."
								+ " Tilting your head to one side, you softly press your [pc.lips+] against [npc.hers] and start slowly thrusting your [pc.tongue+] into [npc.her] mouth."
							+ "</p>"
							+ "<p>"
								+ "Much to your delight, you feel [npc.herHim] returning your loving kiss, and as your [npc.daughter] leans into you, [npc.she] mumbles,"
								+ " [npc.speech(Yes [npc.pcName]... Fuck me...)]"
							+ "</p>");
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (gentle)",
							"[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)", Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)),
							null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}	
							},
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you take hold of [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start pressing yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] in your embrace."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! No! Please!)]"
							+ "</p>") {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 4) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							null, null, null, null, null, null,
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down to take hold of one of [npc.name]'s [npc.arms], you roughly yank [npc.herHim] to [npc.her] [npc.feet], before wrapping your [pc.arms] around [npc.her] back and stepping forwards."
								+ " Tilting your head to one side, you greedily press your [pc.lips+] against [npc.hers] and start dominantly thrusting your [pc.tongue+] into [npc.her] mouth."
							+ "</p>"
							+ "<p>"
								+ "Much to your delight, you feel [npc.herHim] returning your forceful kiss, and as your [npc.daughter] leans into you, [npc.she] mumbles,"
								+ " [npc.speech(Yes [npc.pcName]... Fuck me...)]"
							+ "</p>");
					
				} else {
					return new ResponseSex("Rape [npc.herHim] (rough)",
							"[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)", Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)),
							null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}	
							},
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, roughly yanking [npc.herHim] to [npc.her] feet, you start forcefully grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you firmly hold [npc.herHim] in your embrace."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! No! Please!)]"
							+ "</p>") {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringRapeApologyNeeded, true);
						}
					};
				}
				
			} else if (index == 5) {
				if(offspring().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now...</br>"
								+ "Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "Looking down at your [npc.daughter] as [npc.she] shuffles about on the floor, you're suddenly overcome with regret, and, kneeling down next to [npc.herHim], you pull [npc.herHim] into a loving hug."
								+ " Leaning in over [npc.her] shoulder as you press yourself to [npc.herHim], you apologise for what you've done,"
								+ " [pc.speech([npc.Name], I'm so sorry! I don't know what came over me! Please forgive me!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.She] hesitantly wraps [npc.her] [npc.arms] around you, and after a few moments, you feel [npc.her] grip tightening, before [npc.she] speaks out in a surprisingly stern voice,"
								+ " [npc.speech(If you're really sorry, you're going to prove it to me! Show me how much you <i>really</i> love me!)]"
							+ "</p>"
							+ "<p>"
								+ "As you pull away in order to respond, [npc.name] suddenly leans forwards and grabs your head, before pressing [npc.her] [npc.lips+] against yours."
								+ " Shuffling forwards, [npc.she] pins you back against the wall behind you, thrusting [npc.her] [npc.tongue+] into your mouth as [npc.she] takes advantage of your guilty state of mind."
							+ "</p>"
							+ "<p>"
								+ "Eventually, [npc.she] pulls back in order to demand a response from you, and, eager to earn your [npc.daughter]'s forgiveness, you reply,"
								+ " [pc.speech(I love you [npc.name]! Please, do whatever you want to me!)]"
							+ "</p>"
							+ "<p>"
								+ "A hungry grin spreads across [npc.name]'s face, before [npc.she] pulls you to your feet and starts to kiss you once more..."
							+ "</p>") {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(offspring().incrementAffection(Main.game.getPlayer(), 50));
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
				} else {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(offspring(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						if(offspring().hasFlag(NPCFlagValue.fightOffspringInApartment)) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Looking down at your [npc.daughter] as [npc.she] shuffles about on the floor, you remind yourself that you're doing this for [npc.her] own good."
										+ " With your [pc.hands] on your [pc.hips], you speak down to [npc.herHim],"
										+ " [pc.speech([npc.Name], this is the end of your shameful lifestyle! You're going to pack everything up and move elsewhere! It's time for you to find a respectable job and lead an honest life!)]"
									+ "</p>"
									+ "<p>"
										+ "[npc.She] doesn't offer much resistance, and you watch as your [npc.daughter] packs up [npc.her] things and prepares to move out."
										+ " After a short while, [npc.she]'s ready, and before [npc.she] leaves your life forever, [npc.she] turns around and mutters,"
										+ " [npc.speech(I'll make you proud, [pc.mom]...)]"
									+ "</p>"
									+ "<p>"
										+ "With that, [npc.she]'s gone, and you're left with little else to do but set off into the alleyways once again..."
									+ "</p>");
							
						} else {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Looking down at your [npc.daughter] as [npc.she] shuffles about on the floor, you remind yourself that you're doing this for [npc.her] own good."
										+ " With your [pc.hands] on your [pc.hips], you speak down to [npc.herHim],"
										+ " [pc.speech([npc.Name], this is the end of your shameful lifestyle! You're going to pack everything up and move elsewhere! It's time for you to find a respectable job and lead an honest life!)]"
									+ "</p>"
									+ "<p>"
										+ "[npc.She] doesn't offer much resistance, and you watch as your [npc.daughter] meekly hangs [npc.her] head and agrees to do as you say."
										+ " Before [npc.she] walks off and leaves your life forever, [npc.she] turns around and mutters,"
										+ " [npc.speech(I'll make you proud, [pc.mom]...)]"
									+ "</p>"
									+ "<p>"
										+ "With that, [npc.she]'s gone, and you're left with little else to do but set off into the alleyways once again..."
									+ "</p>");
							
						}
						Main.game.banishNPC(offspring());
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Now that you've taught [npc.name] a lesson, you can be on your way...", AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(I hope you've learned your lesson,)]"
									+ " you say, speaking down to your [npc.daughter] as [npc.she] shuffles about on the floor,"
									+ " [pc.speech(Maybe I'll come and visit you another time, but I need to be going now.)]"
								+ "</p>"
								+ "<p>"
									+ "With that, you turn around and walk over to the front door, ignoring the little whimpers coming from your defeated [npc.daughter]."
									+ " Turning the handle, you open the door and make your exit, and within a few minutes, find yourself walking through the alleyways once again..."
								+ "</p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			if(offspring().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(offspring(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Are you ok [npc.pcName]?!)] [npc.name] shouts, before leaning down to grab one of your [pc.arms],"
							+ " [npc.speech(I was only defending myself!)]"
						+ "</p>"
						+ "<p>"
							+ "As you groan in the affirmative, [npc.name] lets out a sigh of relief."
							+ " Pulling you to your feet, [npc.she] suddenly steps forwards and presses [npc.herself] up against you,"
							+ " [npc.speech(Well, so long as you're ok, I think it's time for your apology!)]"
						+ "</p>"
						+ "<p>"
							+ "The moment that you open your mouth to respond, your [npc.daughter] darts forwards, pressing [npc.her] [npc.lips+] against yours as [npc.she] pulls you into a passionate kiss."
							+ " Grinding [npc.herself] up against your body, [npc.she] breaks off the kiss for a moment to [npc.moanVerb] into your [pc.ear],"
							+ " [npc.speech(So, [npc.pcName] likes a little bit of rough play, huh?! Well, I like that sort of thing too!)]"
						+ "</p>");
				
			} else {
				return UtilText.parse(offspring(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Are you ok [npc.pcName]?!)] [npc.name] shouts, before leaning down to grab one of your [pc.arms],"
							+ " [npc.speech(I was only defending myself!)]"
						+ "</p>"
						+ "<p>"
							+ "As you groan in the affirmative, [npc.name] lets out a sigh of relief."
							+ " Pulling you to your feet, [npc.she] makes sure that you're able to stand under your own strength before stepping back and taking on a stern tone,"
							+ " [npc.speech(What the hell was that all about?! You owe me an apology!)]"
						+ "</p>"
						+ "<p>"
							+ "Not being in any kind of situation to refuse, you apologise to your [npc.daughter] for attacking [npc.herHim]."
							+ " Letting out a little huff, [npc.she] continues,"
							+ " [npc.speech(Well, I can't forgive you just yet! Anyway, you can help make it up to me by giving me some allowance money!)]"
						+ "</p>"
						+ "<p>"
							+ "Letting out a groan, you're forced to comply with [npc.her] request, and after handing over some of your cash, [npc.name] leads you away from [npc.her] home."
							+ " Leaving you somewhere in the alleyways, [npc.she] calls out behind [npc.herHim] as [npc.she] takes [npc.her] leave,"
							+ " [npc.speech(Don't bother to come back unless you're truly sorry!)]"
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(offspring().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Time for some fun [npc.pcName]!)]"
							+ "</p>") {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Time for some fun [npc.pcName]!)]"
							+ "</p>") {
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(offspring(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							AFTER_SEX_DEFEAT,
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
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "You're left to continue on your way...", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue(){
							return DebugDialogue.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							offspring().setFlag(NPCFlagValue.flagOffspringFightApologyNeeded, false);
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_CONSENSUAL = new DialogueNodeOld("Post-sex", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 30;
		}
		
		@Override
		public String getDescription(){
			return "You've satisfied your lust for your [npc.daughter]... For now...";
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return UtilText.parse(offspring(),
						"<p>"
							+ "Disentangling yourselves from each other's clutches, you both sink down onto [npc.name]'s sofa once again."
							+ " Turning [npc.her] head towards you, your [npc.daughter] sighs,"
							+ " [npc.speech(Fuck... That was good [npc.pcName]...)]"
						+ "</p>"
						+ "<p>"
							+ "You lie with each other for a little while, enjoying the closeness of one another's company, before eventually it's time for you to leave."
							+ " Giving [npc.name] a kiss goodbye, you promise that you'll come and see [npc.herHim] again, before setting out to continue on your journey."
						+ "</p>");
			} else {
				return UtilText.parse(offspring(),
						"<p>"
							+ "Disentangling yourselves from each other's clutches, you both sink down onto [npc.name]'s sofa once again."
							+ " Turning [npc.her] head towards you, your [npc.daughter] whines,"
							+ " [npc.speech(Fuck... [npc.pcName], I didn't even get to climax...)]"
						+ "</p>"
						+ "<p>"
							+ "You lie with each other for a little while, reassuring [npc.name] that you'll allow [npc.her] to orgasm next time, before eventually it's time for you to leave."
							+ " Giving [npc.herHim] a kiss goodbye, you promise that you'll come and see [npc.herHim] again, before setting out to continue on your journey."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_CONSENSUAL){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
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
			if(offspring().hasFlag(NPCFlagValue.fightOffspringInApartment)) {
				if(!offspring().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
					return UtilText.parse(offspring(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] grabs on to the edge of the sofa and sinks to the floor, letting out a thankful sob as [npc.she] realises that you've finished."
							+ "</p>");
					
				} else {
					if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
						return UtilText.parse(offspring(),
								"<p>"
									+ "As you step back from [npc.name], [npc.she] grabs on to the edge of the sofa and sinks to the floor,"
										+ " totally worn out from the orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+" you gave to [npc.herHim]."
									+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you lean down to pat [npc.her] head."
								+ "</p>"
								+ "<p>"
									+ "[npc.speech(Thanks [npc.pcName],)]"
									+ " [npc.she] sighs, before resting [npc.her] head on the sofa's cushions and falling asleep."
								+ "</p>");
					} else {
						return UtilText.parse(offspring(),
								"<p>"
									+ "As you step back from [npc.name], [npc.she] grabs on to the edge of the sofa and sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
									+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
								+ "</p>"
								+ "<p>"
									+ "[npc.speech([npc.pcName]! I'm still horny!)]"
									+ " [npc.she] whines."
								+ "</p>");
					}
				}
				
			} else {
				if(!offspring().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
					return UtilText.parse(offspring(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks down to floor, letting out a thankful sob as [npc.she] realises that you've finished."
							+ "</p>");
					
				} else {
					if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
						return UtilText.parse(offspring(),
								"<p>"
									+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
									+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
								+ "</p>"
								+ "<p>"
									+ "[npc.speech(Thanks [npc.pcName],)]"
									+ " [npc.she] sighs."
								+ "</p>");
					} else {
						return UtilText.parse(offspring(),
								"<p>"
									+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
									+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
								+ "</p>"
								+ "<p>"
									+ "[npc.speech([npc.pcName]! I'm still horny!)]"
									+ " [npc.she] whines."
								+ "</p>");
					}
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
						if(offspring().hasFlag(NPCFlagValue.fightOffspringInApartment)) {
							if(!offspring().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
								Main.game.getTextStartStringBuilder().append(" Smirking down at your [npc.daughter] one last time, you turn around and walk over to the door, before pulling it open and heading back out into the alleyways.");
								
							} else {
								if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
									Main.game.getTextStartStringBuilder().append("Leaving [npc.herHim] to recover by [npc.herself], you turn around and walk over to the door, before pulling it open and heading back out into the alleyways.");
								} else {
									Main.game.getTextStartStringBuilder().append("Having already had your fun, you leave [npc.herHim] to get some pleasure by [npc.herself]."
												+ " Turning around and walking over to the door, you pull it open and head back out into the alleyways.");
								}
							}
							
						} else {
							if(!offspring().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
								Main.game.getTextStartStringBuilder().append("As you step back from [npc.name], [npc.she] sinks down to floor, letting out a thankful sob as [npc.she] realises that you've finished."
											+ " Smirking down at your [npc.daughter] one last time, you turn around and take your leave, feeling thoroughly satisfied as you continue on your journey through the alleyways.");
								
							} else {
								if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
									Main.game.getTextStartStringBuilder().append("Leaving [npc.herHim] to recover by [npc.herself], you set off and continue on your journey through the alleyways.");
								} else {
									Main.game.getTextStartStringBuilder().append("Leaving [npc.herHim] to get some pleasure by [npc.herself], you set off and continue on your journey through the alleyways.");
								}
							}
						}
						Main.game.getTextStartStringBuilder().append("</p>");
						String parsed = UtilText.parse(offspring(), Main.game.getTextStartStringBuilder().toString());
						Main.game.getTextStartStringBuilder().setLength(0);
						Main.game.getTextStartStringBuilder().append(parsed);
					}
					@Override
					public DialogueNodeOld getNextDialogue(){
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.name]'s clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10 && Main.game.getPlayer().getLocationPlace().getPlaceType() == PlaceType.DOMINION_BACK_ALLEYS) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						if(offspring().hasFlag(NPCFlagValue.fightOffspringInApartment)) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Looking down at your [npc.daughter] as [npc.she] shuffles about on the floor, you remind yourself that you're doing this for [npc.her] own good."
										+ " With your [pc.hands] on your [pc.hips], you speak down to [npc.herHim],"
										+ " [pc.speech([npc.Name], this is the end of your shameful lifestyle! You're going to pack everything up and move elsewhere! It's time for you to find a respectable job and lead an honest life!)]"
									+ "</p>"
									+ "<p>"
										+ "[npc.She] doesn't offer much resistance, and you watch as your [npc.daughter] packs up [npc.her] things and prepares to move out."
										+ " After a short while, [npc.she]'s ready, and before [npc.she] leaves your life forever, [npc.she] turns around and mutters,"
										+ " [npc.speech(I'll make you proud, [pc.mom]...)]"
									+ "</p>"
									+ "<p>"
										+ "With that, [npc.she]'s gone, and you're left with little else to do but set off into the alleyways once again..."
									+ "</p>");
							
						} else {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Looking down at your [npc.daughter] as [npc.she] shuffles about on the floor, you remind yourself that you're doing this for [npc.her] own good."
										+ " With your [pc.hands] on your [pc.hips], you speak down to [npc.herHim],"
										+ " [pc.speech([npc.Name], this is the end of your shameful lifestyle! You're going to pack everything up and move elsewhere! It's time for you to find a respectable job and lead an honest life!)]"
									+ "</p>"
									+ "<p>"
										+ "[npc.She] doesn't offer much resistance, and you watch as your [npc.daughter] meekly hangs [npc.her] head and agrees to do as you say."
										+ " Before [npc.she] walks off and leaves your life forever, [npc.she] turns around and mutters,"
										+ " [npc.speech(I'll make you proud, [pc.mom]...)]"
									+ "</p>"
									+ "<p>"
										+ "With that, [npc.she]'s gone, and you're left with little else to do but set off into the alleyways once again..."
									+ "</p>");
							
						}
						Main.game.banishNPC(offspring());
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
			if(offspring().hasFlag(NPCFlagValue.fightOffspringInApartment)) {
				return UtilText.parse(
						"<p>"
							+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink down onto the sofa, totally worn out from [npc.her] dominant treatment of you."
							+ " Once [npc.she]'s finished getting [npc.her] things in order, [npc.she] walks over and looks down at you, smirking in a very satisfied manner."
							+ " Leaning down, [npc.she] pats you on the head,"
							+ " [npc.speech(Silly [npc.pcName]! This is what happens if you try attacking me!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] hooks one of [npc.her] [npc.arms] under yours, and, after pulling you to your feet and handing you your things, walks you over to the door."
							+ " Still exhausted from your ordeal, you can do nothing but allow yourself to be led out of your [npc.daughter]'s apartment."
						+ "</p>"
						+ "<p>"
							+ "After a short while, [npc.she] comes to a halt, and, turning to look at you, [npc.she] grins,"
							+ " [npc.speech(Don't bother trying to attack me like that again! If you do, I'll just have to keep on fucking you until you've learned your lesson!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and walks off, leaving you to get your things in order before setting off into the alleyways once again..."
						+ "</p>");
			} else {
				return UtilText.parse(
						"<p>"
							+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink down onto the ground, totally worn out from [npc.her] dominant treatment of you."
							+ " Once [npc.she]'s finished getting [npc.her] things in order, [npc.she] walks over and looks down at you, smirking in a very satisfied manner."
							+ " Leaning down, [npc.she] pats you on the head,"
							+ " [npc.speech(Silly [npc.pcName]! This is what happens if you try attacking me!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] hooks one of [npc.her] [npc.arms] under yours, and, after pulling you to your feet and handing you your things, makes sure that you're strong enough to stand under your own strength."
							+ " Still exhausted from your ordeal, you can do nothing but allow yourself to be manhandled by your [npc.daughter], and obediently stand in place as [npc.she] scolds you,"
							+ " [npc.speech(Don't bother trying to attack me like that again! If you do, I'll just have to keep on fucking you until you've learned your lesson!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and walks off, leaving you to get your things in order before setting off into the alleyways once again..."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return DebugDialogue.getDefaultDialogueNoEncounter();
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
		public String getContent() {
			AbstractClothing enslavementClothing = Main.game.getActiveNPC().getEnslavementClothing();
			if(enslavementClothing.getClothingType().equals(ClothingType.NECK_SLAVE_COLLAR)) {
				return UtilText.parse(offspring(),
						"<p>"
							+ "As you lift the collar up to [npc.name]'s neck, you see that the ring attached to the front starts to glow green; a clear indication that it's detecting your [npc.daughter] as a potential enslavement target."
							+ " Encouraged by the light, you finish what you started, and with a heavy metal 'clink', you clasp the collar around [npc.name]'s neck."
						+ "</p>"
						+ "<p>"
							+ "As the collar's arcane enchantment recognises its new wearer as being a criminal, ominous purple lettering starts to glow around the metal band, reading;"
								+ " <i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>New Slave Accepted. Identification: [style.boldArcane("+offspring().getNameIgnoresPlayerKnowledge()+")], [npc.race].</i>"
							+ " Finally realising what's going on, [npc.name] looks up at you with fear in [npc.her] [npc.eyes],"
							+ " [npc.speech(W-Wait, [npc.pcName]! T-This isn't a slave collar is it?!)]"
						+ "</p>"
						+ "<p>"
							+ "Grinning at your [npc.daughter], you don't have time to offer a response, as with a bright purple flash, they suddenly disappear from sight."
							+ " The last thing you see as they're whisked away is a face of shocked betrayal, and you imagine how angry they'll be when you see them next."
						+ "</p>"
						+ "<p>"
							+ "From Finch's instructions, you know that <b>they've been teleported to the 'Slave Administration' building in Slaver Alley</b>, where they'll be waiting for you to pick them up."
						+ "</p>");
			} else {
				return UtilText.parse(offspring(),
						"<p>"
							+ "As you bring the "+enslavementClothing.getName()+" closer to [npc.name], you feel "+(enslavementClothing.getClothingType().isPlural()?"them":"it")
								+" start to steadily hum; a clear indication that your [npc.daughter] is being detected as a potential enslavement target."
							+ " Encouraged by this, you decide to finish what you started, and quickly force [npc.name] to wear the enslaving clothing."
						+ "</p>"
						+ "<p>"
							+ "As the arcane enchantment recognises its new wearer as being a criminal, ominous purple lettering is projected into the air, reading;"
							+ "<i>Slave identification: [style.boldArcane("+Main.game.getActiveNPC().getNameIgnoresPlayerKnowledge()+")]</i>"
							+ " Finally realising what's going on, [npc.name] looks up at you with fear in [npc.her] [npc.eyes],"
							+ " [npc.speech(W-Wait, [npc.pcName]! T-This isn't enslaving me, is it?!)]"
						+ "</p>"
						+ "<p>"
							+ "Grinning at your [npc.daughter], you don't have any time to offer a response, as with a bright purple flash, they suddenly disappear from sight."
							+ " The last thing you see as they're whisked away is a face of shocked betrayal, and you imagine how angry they'll be when you see them next."
						+ "</p>"
						+ "<p>"
							+ "From Finch's instructions, you know that <b>they've been teleported to the 'Slave Administration' building in Slaver Alley</b>, where they'll be waiting for you to pick them up."
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ENSLAVEMENT_DIALOGUE){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().applyEnslavementEffects(Main.game.getPlayer());
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
