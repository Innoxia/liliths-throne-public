package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.2.2
 * @author Innoxia
 */
public class AlleywayProstituteDialogue {

	private static int prostitutePrice() {
		return CharacterUtils.getProstitutePrice(Main.game.getActiveNPC());
	}
	
	private static boolean isCanal() {
		PlaceType pt = Main.game.getActiveNPC().getLocationPlace().getPlaceType();
		return (pt == PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
				|| pt == PlaceType.DOMINION_CANAL
				|| pt == PlaceType.DOMINION_CANAL_END);
	}
	
	public static final DialogueNodeOld ALLEY_PROSTITUTE = new DialogueNodeOld("Prostitute", "You run into someone who's selling their body.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Prostitute";
		}
		
		@Override
		public String getContent() {
			
			UtilText.nodeContentSB.setLength(0);
			
			// You've encountered them before:
			if(Main.game.getActiveNPC().getLastTimeEncountered() != -1) {
				
				if(isCanal()) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "You find yourself wandering down the path that runs alongside Dominion's canal, staying alert for any sign of trouble."
								+ " Up ahead, you suddenly spot the familiar figure of [npc.name], [npc.a_fullRace(true)], leaning back against a brick wall."
							+ "</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "You find yourself wandering down yet another of Dominion's dark, isolated alleyways, staying alert for any sign of trouble."
								+ " Reaching a turn in your path, you step around the corner to see a rare sign of life, taking the form of the familiar figure of [npc.name], [npc.a_fullRace(true)], leaning back against one wall."
							+ "</p>");
				}
				
				if(Main.game.getActiveNPC().getFoughtPlayerCount()>0) { // You've fought them before, so they're a little scared:
					if(Main.game.getActiveNPC().isVisiblyPregnant()){ // Pregnant encounters:
						if(!Main.game.getActiveNPC().isReactedToPregnancy()) {
							UtilText.nodeContentSB.append(
									"<p>"
										+ "Instantly, your eyes are drawn down to the clearly-visible bump in [npc.her] stomach, and as [npc.name] looks across at you, [npc.she] lets out a shocked gasp."
										+ UtilText.returnStringAtRandom(
												"[npc.speech(Oh, it's you again... There's no need for violence this time, ok? Look! You knocked me up!)]",
												"[npc.speech(Oh, hi again... There's no need for violence, ok? Look what you did! I'm carrying your kids!)]",
												"[npc.speech(Hey again... We don't need to fight this time, ok? Anyway, look, you managed to knock me up!)]")
									+ "</p>"
									+ "<p>"
										+ "[pc.speech(How can you be sure that it's mine?)]"
										+ " you ask, concerned over the possibility that [npc.namePos] pregnant by another of [npc.her] clients."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Well, it's kind of hard getting business around here, you know."
											+ " You're the only one who... used me... around the time I could have got knocked up, so, unless it's some crazy kind of arcane spell, yeah... it's yours.)]"
									+ "</p>"
									+ "<p>"
										+ "You step up to [npc.name], allowing [npc.her] to take hold of your [pc.hands] and hesitantly guide them down to stroke [npc.her] swollen bump."
										+ " [npc.She] lets out a nervous little laugh, clearly worried that you're going to attack [npc.herHim] again, before biting [npc.her] [npc.lip] and batting [npc.her] eyelids at you."
										+ " [npc.speech(So, you wanna fuck a pregnant "+(Main.game.getActiveNPC().isFeminine()?"chick":"dude")+"? I'll even give you a discount; twenty percent off for the father of our kids!)]"
									+ "</p>");
						} else {
							UtilText.nodeContentSB.append(
									"<p>"
										+ "Instantly, your eyes are drawn down to the still-visible bump in [npc.her] stomach, and as [npc.name] looks across at you, [npc.she] lets out a shocked gasp."
										+ UtilText.returnStringAtRandom(
												"[npc.speech(Oh, it's you again... There's no need for violence this time, ok? I'm still waiting for our kids to pop out!)]",
												"[npc.speech(Oh, hi again... There's no need for violence, ok? Take a look, I'm still carrying your kids!)]",
												"[npc.speech(Hey again... We don't need to fight this time, ok? These kids of ours sure do take a while to grow!)]")
									+ "</p>"
									+ "<p>"
										+ "You walk up to [npc.name], once again allowing [npc.her] to take hold of your [pc.hands] and gingerly guide them down to stroke [npc.her] swollen bump."
										+ " [npc.She] lets out hesitant laugh, before biting [npc.her] [npc.lip] and batting [npc.her] eyelids at you."
										+ " [npc.speech(So, you wanna fuck a pregnant "+(Main.game.getActiveNPC().isFeminine()?"chick":"dude")+"? I'll even give you a discount; twenty percent off for the father of our kids!)]"
									+ "</p>");
						}
						
					} else {
						UtilText.nodeContentSB.append("<p>"
									+ "Looking over at you, [npc.she] smiles and steps back, clearly nervous about what your intentions are, "
									+ UtilText.returnStringAtRandom(
											"[npc.speech(Oh, it's you again... There's no need for violence this time, ok? You lookin' for a good time?)]",
											"[npc.speech(Oh, hi again... There's no need for violence, ok? You want a good time?)]",
											"[npc.speech(Hey again... We don't need to fight this time, ok? I can show you a real good time!)]")
								+"</p>"
								+ "<p>"
									+ "From [npc.her] slutty clothing, the heavy amount of makeup on [npc.her] face, and, of course, [npc.her] reaction upon seeing you, it's quite obvious that [npc.sheIs] still working as a prostitute."
									+ " You've seen plenty of other hookers offering their services out in the main streets of Dominion, and you wonder why this particular one would be hiding so far away from the safety of those areas."
									+ " You assume that [npc.she] must have run afoul of the law, for there's no other reason to be working in a place as dangerous and client-starved as these alleyways."
								+ "</p>"
								+ "<p>"
									+ "As you're wondering how to react, the [npc.race] whines,"
									+ " [npc.speech(Come on! Only "+Util.intToString(prostitutePrice())+" flames, and [npc.namePos] all yours!)]"
								+ "</p>");
					}
				} else {
					if(Main.game.getActiveNPC().isVisiblyPregnant()){ // Pregnant encounters:
						if(!Main.game.getActiveNPC().isReactedToPregnancy()) {
							UtilText.nodeContentSB.append(
									"<p>"
										+ "Instantly, your eyes are drawn down to the clearly-visible bump in [npc.her] stomach, and as [npc.name] looks across at you, [npc.she] lets out a little laugh."
										+ UtilText.returnStringAtRandom(
												"[npc.speech(Yeah, that's right, hot stuff! You knocked me up!)]",
												"[npc.speech(Hey again, babe! Look what you did! I'm carrying your kids!)]",
												"[npc.speech(Hey again, good lookin'! Check it out! You managed to knock me up!)]")
									+ "</p>"
									+ "<p>"
										+ "[pc.speech(How can you be sure that it's mine?)]"
										+ " you ask, concerned over the possibility that [npc.namePos] pregnant by another of [npc.her] clients."
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Well, it's kind of hard getting business around here, you know."
											+ " You're the only one I slept with around the time I could have got knocked up, so, unless it's some crazy kind of arcane spell, yeah... it's yours.)]"
									+ "</p>"
									+ "<p>"
										+ "You step up to [npc.name], allowing [npc.her] to take hold of your [pc.hands] and guide them down to stroke [npc.her] swollen bump."
										+ " [npc.She] lets out another little laugh, before biting [npc.her] [npc.lip] and batting [npc.her] eyelids at you."
										+ " [npc.speech(So, you wanna fuck a pregnant "+(Main.game.getActiveNPC().isFeminine()?"chick":"dude")+"? I'll even give you a discount; twenty percent off for the father of our kids!)]"
									+ "</p>");
						} else {
							UtilText.nodeContentSB.append(
									"<p>"
										+ "Instantly, your eyes are drawn down to the still-visible bump in [npc.her] stomach, and as [npc.name] looks across at you, [npc.she] lets out a little laugh."
										+ UtilText.returnStringAtRandom(
												"[npc.speech(Yeah, that's right, hot stuff! I'm still waiting for them to pop out!)]",
												"[npc.speech(Hey again, babe! Yeah, that's right, I'm still carrying your kids!)]",
												"[npc.speech(Hey again, good lookin'! These kids of ours sure do take a while to grow!)]")
									+ "</p>"
									+ "<p>"
										+ "You walk up to [npc.name], once again allowing [npc.her] to take hold of your [pc.hands] and guide them down to stroke [npc.her] swollen bump."
										+ " [npc.She] lets out another little laugh, before biting [npc.her] [npc.lip] and batting [npc.her] eyelids at you,"
										+ " [npc.speech(So, you wanna fuck a pregnant "+(Main.game.getActiveNPC().isFeminine()?"chick":"dude")+"? I'll even give you a discount; twenty percent off for the father of our kids!)]"
									+ "</p>");
						}
						
					} else {
						UtilText.nodeContentSB.append("<p>"
									+ "Looking over at you, [npc.she] smiles and steps forwards, blocking your way. "
									+ UtilText.returnStringAtRandom(
											"[npc.speech(Hey again, hot stuff! You lookin' for a good time?)]",
											"[npc.speech(Hey again, babe! You want a good time?)]",
											"[npc.speech(Hey again, good lookin'! I can show you a real good time!)]")
								+"</p>"
								+ "<p>"
									+ "From [npc.her] slutty clothing, the heavy amount of makeup on [npc.her] face, and, of course, [npc.her] reaction upon seeing you, it's quite obvious that [npc.sheIs] still working as a prostitute."
									+ " You've seen plenty of other hookers offering their services out in the main streets of Dominion, and you wonder why this particular one would be hiding so far away from the safety of those areas."
									+ " You assume that [npc.she] must have run afoul of the law, for there's no other reason to be working in a place as dangerous and client-starved as these alleyways."
								+ "</p>"
								+ "<p>"
									+ "As you're wondering how to react, the [npc.race] whines,"
									+ " [npc.speech(Come on! Only "+Util.intToString(prostitutePrice())+" flames, and [npc.namePos] all yours!)]"
								+ "</p>");
					}
				}
				
			} else {
				
				if(isCanal()) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "You find yourself wandering down the path that runs alongside Dominion's canal, staying alert for any sign of trouble."
								+ " Up ahead, you suddenly spot a rare sign of life, taking the form of [npc.a_fullRace(true)], leaning back against a brick wall."
							+ "</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "You find yourself wandering down yet another of Dominion's dark, isolated alleyways, staying alert for any sign of trouble."
								+ " Reaching a turn in your path, you step around the corner to see a rare sign of life, taking the form of [npc.a_fullRace(true)], leaning back against one wall."
							+ "</p>");
				}
				
				UtilText.nodeContentSB.append("<p>"
						+ "Looking over at you, [npc.she] smiles and steps forwards, blocking your way. "
						+ UtilText.returnStringAtRandom(
								"[npc.speech(Hey, hot stuff! You lookin' for a good time?)]",
								"[npc.speech(Hey, babe! You want a good time?)]",
								"[npc.speech(Hey, good lookin'! I can show you a real good time!)]")
					+"</p>"
					+ "<p>"
						+ "From [npc.her] slutty clothing, the heavy amount of makeup on [npc.her] face, and, of course, [npc.her] reaction upon seeing you, it's quite obvious that [npc.sheIs] a prostitute."
						+ " You've seen plenty of other hookers offering their services out in the main streets of Dominion, and you wonder why this particular one would be hiding so far away from the safety of those areas."
						+ " You assume that [npc.she] must have run afoul of the law, for there's no other reason to be working in a place as dangerous and client-starved as these alleyways."
					+ "</p>"
					+ "<p>"
						+ "As you're wondering what to make of [npc.herHim], the [npc.race] whines,"
						+ " [npc.speech(Come on! Only "+Util.intToString(prostitutePrice())+" flames, and [npc.namePos] all yours!)]"
					+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "You're not at all interested in having sex with a prostitute. Walk around [npc.herHim] and continue on your way.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(I'm not interested,)] you say, stepping around [npc.name] and continuing on your way."
								+ "</p>"
								+ "<p>"
									+ "As you walk off, [npc.she] calls out after you,"
									+ " [npc.speech(Come back if you ever want the best fuck of your life! Oh, and tell your friends too!)]"
								+ "</p>");
					}
				};
				
			} else if (index == 2) {
				int cost = prostitutePrice();
				
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("Dominant Sex ("+UtilText.formatAsMoney(cost, "span")+")", "You don't have "+cost+" flames, so you can't afford a good time with [npc.name].", null);
					
				} else {
					return new ResponseSex("Dominant Sex ("+UtilText.formatAsMoney(cost, "span")+")",
							"Pay [npc.name] "+cost+" flames to have a good time with [npc.herHim].",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_PAID,
							"<p>"
									+ "[pc.speech(Sure, I could do with having a good time,)]"
									+ " you reply, handing over "+cost+" flames to the [npc.race]."
								+ "</p>"
								+ "<p>"
									+ "[npc.speech(~Mmm~ You've got me for an hour,)]"
									+ " [npc.name] [npc.moans]."
									+ " [npc.speech(Follow me!)]"
								+ "</p>"
								+ (isCanal()
										?"<p>"
											+ "Leading you a little way down the canal, [npc.name] suddenly turns off the path, crossing a small clearing as [npc.she] leads you over to a nondescript doorway."
											+ " Producing a key from [npc.her] bag, the [npc.race] reaches towards the lock, and, after a twist and a shove, opens the door."
											+ " Motioning for you to follow [npc.herHim] in, [npc.name] then steps inside."
											+ " Trailing in [npc.her] footsteps, you enter the [npc.race]'s apartment, and find yourself pleasantly surprised by the clean, well-lit interior."
										+ "</p>"
										:"<p>"
											+ "Producing a key from [npc.her] bag, the [npc.race] turns around, before unlocking a nondescript door behind [npc.herHim]."
											+ " Opening the door and motioning for you to follow [npc.herHim] in, [npc.name] steps inside."
											+ " Trailing in [npc.her] footsteps, you enter the [npc.race]'s apartment, and find yourself pleasantly surprised by the clean, well-lit interior."
										+ "</p>")
								+ "<p>"
									+ "Closing the door behind you, [npc.name] then leads you into [npc.her] bedroom, where [npc.she] turns around and grins at you."
									+ " [npc.speech(Let's get this party started!)]"
								+ "</p>") {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-cost);
						}
					};
				}
				
			} else if (index == 3) {
				int cost = prostitutePrice();
				
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("Submissive Sex ("+UtilText.formatAsMoney(cost, "span")+")", "You don't have "+cost+" flames, so you can't afford a good time with [npc.name].", null);
					
				} else {
					return new ResponseSex("Submissive Sex ("+UtilText.formatAsMoney(cost, "span")+")",
							"Pay [npc.name] "+cost+" flames to have a good time with [npc.herHim].",
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_PAID,
							"<p>"
								+ "[pc.speech(Sure, I could do with having a good time,)]"
								+ " you reply,"
								+ " [pc.speech(but only if you're on top...)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(~Mmm~ Don't you worry, sweet thing, I can take charge,)]"
								+ " [npc.name] [npc.moans], stepping up to you and taking the "+cost+" flames from your [pc.hand]."
								+ " [npc.speech(Follow me!)]"
							+ "</p>"
							+ "<p>"
							+ (isCanal()
									?"<p>"
										+ "Leading you a little way down the canal, [npc.name] suddenly turns off the path, crossing a small clearing as [npc.she] leads you over to a nondescript doorway."
										+ " Producing a key from [npc.her] bag, the [npc.race] reaches towards the lock, and, after a twist and a shove, opens the door."
										+ " Motioning for you to follow [npc.herHim] in, [npc.name] then steps inside."
										+ " Trailing in [npc.her] footsteps, you enter the [npc.race]'s apartment, and find yourself pleasantly surprised by the clean, well-lit interior."
									+ "</p>"
									:"<p>"
										+ "Producing a key from [npc.her] bag, the [npc.race] turns around, before unlocking a nondescript door behind [npc.herHim]."
										+ " Opening the door and motioning for you to follow [npc.herHim] in, [npc.name] steps inside."
										+ " Trailing in [npc.her] footsteps, you enter the [npc.race]'s apartment, and find yourself pleasantly surprised by the clean, well-lit interior."
									+ "</p>")
							+ "</p>"
							+ "<p>"
								+ "Closing the door behind you, [npc.name] then leads you into [npc.her] bedroom, where [npc.she] turns around and grins at you."
								+ " [npc.speech(Let's get this party started!)]"
							+ "</p>") {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-cost);
						}
					};
				}
				
			} else if (index == 4) {
				return new Response("Attack", "If you really wanted to, there's nothing stopping you from attacking [npc.name]. After all, if [npc.sheIs] run afoul of the law, as you assume [npc.she] has, then [npc.sheIs] fair game!", ALLEY_PROSTITUTE_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
				
			} else if (index == 5 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
				if(RedLightDistrict.isSpaceForMoreProstitutes()) {
					return new Response("Angel's Kiss", "Offer [npc.name] a job at Angel's Kiss.", ALLEY_PROSTITUTE_SAVED) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementKarma(25);
							Main.game.getActiveNPC().setDescription(UtilText.parse(Main.game.getActiveNPC(), "You first found [npc.name] in the alleyways of Dominion, where [npc.she] was illegally selling [npc.her] body."
									+ " You offered [npc.herHim] the chance to move and work out of Angel's Kiss; an offer that [npc.she] happily accepted."));
							Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 50));
							Main.game.getActiveNPC().setRandomUnoccupiedLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_BEDROOM, true);
						}
					};
					
				} else {
					return new Response("Angel's Kiss", "There's no room available at Angel's Kiss for another prostitute...", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PROSTITUTE_SAVED = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Knowing that if your suspicions are correct, [npc.name] may act in an unpredictable manner, you try to look as non-threatening as possible as you ask,"
						+ " [pc.speech(You're working out of these alleyways illegally, aren't you? If you had a prostitution license, you'd be out on the streets, or working out of a brothel in the red-light district.)]"
					+ "</p>"
					+ "<p>"
						+ "A look of panic quickly flashes over [npc.namePos] face, and [npc.she] cries out,"
						+ " [npc.speech(N-No, I-I just like working back here, t-that's all!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(You can relax,)]"
						+ " you say,"
						+ " [pc.speech(I'm not with the Enforcers or anything."
						+ " I'm only asking because if that's the truth, then I can offer you some help."
						+ " There's a large brothel in the middle of the red-light district, called 'Angel's Kiss', and I have an offer for you from the owner."
						+ " She's in desperate need of prostitutes to come and work for her, and would be willing to provide you with a safe place to work out of.)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.NamePos] [npc.eyes] light up as you say this, and [npc.she] gasps,"
						+ " [npc.speech(R-Really?! Y-You're not tricking me or anything? I can... I can finally escape these alleyways?!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(What would I have to gain from tricking you like that? I'm telling the truth,)]"
						+ " you continue,"
						+ " [pc.speech(so if you're interested, just make your way over there and ask for 'Angel'. Tell her that [pc.name] sent you to come and work for her.)]"
					+ "</p>"
					+ "<p>"
						+ "With tears in [npc.her] eyes, [npc.name] accepts your offer."
						+ " [npc.speech(Thank you, thank you so much! I can't really... That is, I'm so grateful... I-I don't know what to say...)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Don't worry about saying anything, and you're welcome,)]"
						+ " you say, smiling at [npc.name]."
					+ "</p>"
					+ "<p>"
						+ "Quickly grabbing [npc.her] things, [npc.name] hurries off in the direction of the red-light district, and you prepare to carry on your way, happy with the fact that you've helped [npc.name] to escape these dangerous alleyways."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way through Dominion's alleyways...", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PROSTITUTE_FIGHT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
				return "<p>"
							+ "Grinning at the [npc.race], you look into [npc.her] [npc.eyes] and question [npc.herHim],"
							+ " [pc.speech(So, why don't you work out in the streets like all the other prostitutes?)]"
						+ "</p>"
						+ "<p>"
							+ "A look of worry flashes across [npc.namePos] face, and [npc.she] takes a step back, clearly afraid of your intentions."
							+ " [npc.speech(I-I just prefer it back here! There's no other reason!)]"
						+ "</p>"
						+ "<p>"
							+ "You laugh at the obvious lie."
							+ " [pc.speech(I don't think so! You're wanted by the enforcers, aren't you? I wonder what punishment they've got in store for you?)]"
						+ "</p>"
						+ "<p>"
							+ "Realising that [npc.sheIs] been caught, [npc.namePos] expression quickly turns into one of anger, and [npc.she] launches [npc.herself] at you in a blind fury."
							+ " [npc.speech(I'll never be a slave! Fuck you!)]"
						+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[npc.Name] is determined not to be caught, leaving you with no choice but to defend yourself!", Main.game.getActiveNPC());
				
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
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "[npc.Name] collapses to the floor, completely defeated."
						+ " [npc.She] looks up at you, and you see that there's a desperate, wild look in [npc.her] [npc.eyes]."
						+ " Making a pitiful little whining noise, [npc.she] begs,"
						+ " [npc.speech(Do what you want with me! Just, please, I don't want to be a slave! Don't hand me over to the enforcers!)]"
					+ "</p>"
					+ "<p>"
						+ "Despite the awful situation that [npc.she] finds [npc.herself] in, it appears as though your powerful arcane aura is having a strong effect on [npc.name]."
						+ " Even as [npc.she] begs for you to have mercy, you see [npc.herHim] glance down hungrily at your body."
						+ " Seemingly unaware of what [npc.sheIs] doing, [npc.her] [npc.hands] suddenly drop down between [npc.her] [npc.legs], and [npc.she] lets out [npc.a_moan+] as [npc.she] starts touching [npc.herself]."
					+ "</p>"
					+ "<p>"
						+ "<i>Now that you've revealed [npc.namePos] status as a fugitive from the law, you realise that this will be the last time you ever see [npc.herHim], as [npc.sheIs] sure to move on once you leave.</i>"
					+ "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Leave [npc.name] and carry on your way. <b>[npc.Name] will disappear from this area!</b>", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Have some fun",
						"It's clear that [npc.she] wants you. Have some fun with [npc.name].",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SEX_VICTORY);
				
			} else if (index == 3) {
				return new ResponseSex("Gentle fun",
						"It's clear that [npc.she] wants you. Have some fun with [npc.name]. (Start the sex scene in the 'gentle' pace.)",
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
						AFTER_SEX_VICTORY);
				
			} else if (index == 4) {
				return new ResponseSex("Rough fun",
						"It's clear that [npc.she] wants you. Have some fun with [npc.name]. (Start the sex scene in the 'rough' pace.)",
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
						AFTER_SEX_VICTORY);
				
			} else if (index == 5) {
				return new ResponseSex("Submit",
						"You're not really sure what to do now...<br/>"
							+ "Perhaps it would be best to let [npc.name] choose what to do next?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SEX_DEFEAT,
						"<p>"
							+ "You really aren't sure what to do next, and start to feel pretty uncomfortable with the fact that you just beat up this poor [npc.race]."
							+ " Leaning down, you do the first thing that comes into your mind, and start apologising,"
							+ " [pc.speech(Sorry... I don't know what I was thinking... Erm... Is there anything I can do to make it up to you?)]"
						+ "</p>"
						+ "<p>"
							+ "For a moment, a look of confusion crosses over [npc.namePos] face, but as [npc.she] sees that you're genuinely troubled by what you've just done, an evil grin crosses [npc.her] face."
							+ " Consumed by [npc.her] lust that's being brought about due to your powerful arcane aura, [npc.she] stands up, and, grabbing you by the [pc.arm], roughly pulls you into [npc.her] as [npc.she] growls,"
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
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
							+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
							+ " Pulling you to your feet, [npc.name] pushes you against a nearby wall, before growling,"
							+ " [npc.speech(Looks like I'll be having to move on again now. I'm not sticking around for your little enforcer friends to show up and arrest me!"
							+ " But before I go, I think I'll teach you a lesson not to fuck around with strangers!)]"
						+ "</p>"
						+ "<p>"
							+ "<i>Now that you've revealed [npc.namePos] status as a fugitive from the law, you realise that this will be the last time you ever see [npc.herHim], as [npc.sheIs] sure to move on once [npc.sheIs] had [npc.her] fun with you.</i>"
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
							+ "[npc.speech(Looks like I'll be having to move on again now. I'm not sticking around for your little enforcer friends to show up and arrest me!)] [npc.she] growls down at you, before turning around and running off."
						+ "</p>"
						+ "<p>"
							+ "<i>Now that you've revealed [npc.namePos] status as a fugitive from the law, you realise that this was the last time you'll ever see [npc.herHim].</i>"
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.NamePos] [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally pulling away."
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
							AFTER_SEX_DEFEAT,
							"<p>"
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
							AFTER_SEX_DEFEAT,
							"<p>"
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
	
	public static final DialogueNodeOld AFTER_SEX_PAID = new DialogueNodeOld("Step back", "", true) {
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
							+ "As you step back from [npc.name], [npc.she] sinks back onto [npc.her] bed, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
							+ " Looking up at you, a satisfied smile settles across [npc.her] face, and [npc.she] sighs,"
							+ " [npc.speech(Damn, you're good! It looks like I've got a new favourite customer! Please come back again soon!)]"
						+ "</p>");
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks back onto [npc.her] bed, letting out a deep sigh as [npc.she] realises that you've finished."
							+ " Looking up at you, a tired smile settles across [npc.her] face, and [npc.she] sighs,"
							+ " [npc.speech(I hope you had a good time! Be sure to come back again soon!)]"
						+ "</p>");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "You grin down at the panting [npc.race], taking one last look at [npc.her] well-used body, before turning around and heading out of [npc.her] apartment."
									+ " Satisfied that you got your money's worth, you head back out into the alleyways, ready to continue on your journey."
								+ "</p>");
					}
				};
				
			} if (index == 2) {
				return new Response("Attack", "If you really wanted to, there's nothing stopping you from attacking [npc.name]. After all, if [npc.sheIs] run afoul of the law, as you assume [npc.she] has, then [npc.sheIs] fair game!", AFTER_SEX_PAID_FIGHT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
				
			} else if (index == 10) {
				if(Main.game.getPlayer().getMoney()<5000) {
					return new Response("Remove character ("+UtilText.formatAsMoney(5000, "span")+")", "You don't have 5000 flames, so you can't afford to pay [npc.name] to leave this area.", null);
				} else {
					return new Response(
							"Remove character ("+UtilText.formatAsMoney(5000, "span")+")",
							"Give [npc.name] enough money to pay off the enforcers who are after [npc.herHim], which would allow [npc.her] to stop having to work in these dangerous alleyways."
									+ " <b>This will permanently remove [npc.herHim] from the game.</b>",
							AFTER_SEX_PAID_PAY_THEM_TO_LEAVE) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-5000);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_PAID_FIGHT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
				return "<p>"
							+ "Grinning down at the panting [npc.race], you take a step forwards."
							+ " [pc.speech(So, why don't you work out in the streets like all the other prostitutes?)]"
						+ "</p>"
						+ "<p>"
							+ "A look of worry flashes across [npc.namePos] face, and [npc.she] shuffles back on the bed, clearly afraid of your intentions."
							+ " [npc.speech(I-I just prefer it back here! There's no other reason!)]"
						+ "</p>"
						+ "<p>"
							+ "You laugh at the obvious lie."
							+ " [pc.speech(I don't think so! You're wanted by the enforcers, aren't you? I wonder what punishment they've got in store for you?)]"
						+ "</p>"
						+ "<p>"
							+ "Realising that [npc.sheIs] been caught, [npc.namePos] expression quickly turns into one of anger, and [npc.she] launches [npc.herself] at you in a blind fury."
							+ " [npc.speech(I'll never be a slave! Fuck you!)]"
						+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Defend yourself", "[npc.Name] is determined not to be caught, leaving you with no choice but to defend yourself!", Main.game.getActiveNPC());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_PAID_PAY_THEM_TO_LEAVE = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
				return "<p>"
							+ "Looking down at the panting [npc.race], you ask [npc.herHim] the question at the forefront of your mind,"
							+ " [pc.speech(So, why don't you work out in the streets like all the other prostitutes?)]"
						+ "</p>"
						+ "<p>"
							+ "A look of worry flashes across [npc.namePos] face, and [npc.she] shuffles back on the bed, clearly afraid of your intentions."
							+ " [npc.speech(I-I just prefer it back here! There's no other reason!)]"
						+ "</p>"
						+ "<p>"
							+ "You let out a sigh at the obvious lie."
							+ " [pc.speech(You're wanted by the enforcers, aren't you?)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.Name] lets out a panicked gasp, proving your assumption correct."
							+ " Before [npc.she] can say anything in response, you continue,"
							+ " [pc.speech(What would it take to get them off your back? I want to help.)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(I must be crazy to be telling you this...)]"
							+ " [npc.name] starts, but despite the hesitation in [npc.her] voice, [npc.she] lets out a sigh and continues,"
							+ " [npc.speech(I owe someone very powerful almost five-thousand flames, and they've got the enforcers to brand me as a criminal until I can pay up...)]"
						+ "</p>"
						+ "<p>"
							+ "Not wanting to leave [npc.herHim] trapped in such a horrible situation, you step forwards and hand the [npc.race] a bag of money."
							+ " [pc.speech(There's five-thousand flames in there. Try not to let this happen again; I doubt I'll be around to help out if there's a next time.)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Oh my goodness!)]"
							+ " [npc.name] cries, clasping [npc.her] [npc.hands] over [npc.her] mouth, before slowly reaching out to take the money, almost as if [npc.she] expects you to pull it away a the last moment."
							+ " No such thing is on your mind, however, and you let [npc.name] take the bag from you."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(I don't know what to say! Thank you! Thank you so much! With this, I won't have to loiter around in the alleys anymore!)]"
							+ " [npc.name] sobs, crying tears of joy as [npc.she] looks up into your [pc.eyes]."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Don't mention it,)]"
							+ " you say, smiling down at the [npc.race] one last time, before turning around and heading out of [npc.her] apartment."
							+ " Satisfied that you've done a good deed, you head back out into the alleyways, ready to continue on your journey."
						+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						"Continue",
						"Continue on your way.",
						Main.game.getDefaultDialogueNoEncounter()){
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
						+ "</p>"
						+ "<p>"
							+ "<i>Now that you've revealed [npc.namePos] status as a fugitive from the law, you realise that this will be the last time you ever see [npc.herHim], as [npc.sheIs] sure to move on once you leave.</i>"
						+ "</p>");
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
							+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
						+ "</p>"
						+ "<p>"
							+ "<i>Now that you've revealed [npc.namePos] status as a fugitive from the law, you realise that this will be the last time you ever see [npc.herHim], as [npc.sheIs] sure to move on once you leave.</i>"
						+ "</p>");
			}
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Leave [npc.name] and carry on your way. <b>[npc.Name] will disappear from this area!</b>", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
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
						+ " [npc.She] leans down and pats you on the head,"
						+ " [npc.speech(Farewell forever! You and your enforcer cronies will have to try harder than that to catch me!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>"
					+ "<p>"
						+ "<i>Now that you've revealed [npc.namePos] status as a fugitive from the law, you realise that this was the last time you'll ever see [npc.herHim], as [npc.sheIs] sure to move on to a new location.</i>"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()){
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
	
}
