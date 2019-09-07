package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.3
 * @version 0.3.4.5
 * @author Innoxia
 */
public class GenericTalk {

	public static final SexAction ROUGH_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.getProperties().hasValue(PropertyValue.sadisticSexContent)) {
				return false;
			}
			return (Sex.getCharacterPerformingAction().isPlayer() || Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DOMINANT) || Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST))
					&& !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Threatening growl";
			}
			return "Degrading talk";
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you're not able to tell [npc2.name] that [npc2.sheIs] your little fuck-toy, but you can still make an aggressive growling noise to let [npc2.her] know you still mean business.";
			}
			return "Tell [npc2.name] that [npc2.sheIs] your little fuck-toy.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"As [npc.namePos] mouth is blocked, [npc.sheIs] not able to speak, but, still wanting to give [npc2.name] an audible reminder that [npc2.sheIs] [npc.her] bitch, [npc.she] [npc.verb(let)] out a particularly aggressive growl.",
						"Due to [npc.her] mouth currently being blocked, [npc.nameIsFull] not able to speak, and instead decides to let out a deep, menacing growl to let [npc2.name] know that [npc2.sheIs] still [npc.her] worthless bitch.",
						"Not being deterred by [npc.her] current lack of ability to speak, [npc.name] [npc.verb(let)] out a particularly threatening growl, letting [npc2.name] know that [npc2.sheIs] going to be treated like a pathetic fuck-toy.",
						"Although [npc.her] mouth is blocked, making [npc.herHim] unable to speak,"
								+ " [npc.nameIsFull] not deterred from making one of the most menacing growls [npc.she] can muster, letting [npc2.name] know that [npc2.sheIs] going to be treated like a submissive bitch."));
				
			} else {
				if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isPositive()) {
					sb.append(UtilText.returnStringAtRandom(
							"With an evil grin, [npc.name] [npc.verb(snarl)] at [npc2.name], ",
							"[npc.NamePos] voice drips with sadistic glee as [npc.she] [npc.verb(snarl)], "));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							"Grinning to [npc.herself], [npc.name] [npc.verb(snarl)] at [npc2.name], ",
							"[npc.Name] puts on [npc.her] most dominant voice as [npc.she] [npc.verb(snarl)], "));
				}
				
				sb.append(Sex.getCharacterPerformingAction().getRoughTalk());
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SUBMISSIVE_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SUBMISSIVE);
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Submissive whine";
			}
			return "Submissive talk";
		}
			
		@Override
		public SexActionPriority getPriority() {
			if(!Sex.getCharacterPerformingAction().isPlayer() && Sex.getLastUsedPlayerAction()==ROUGH_TALK && Math.random()<0.5f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you're not able to tell [npc2.name] that you're [npc2.her] submissive slut, but you can still make a pitiful whine to let [npc2.her] know you're still [npc2.her] loyal bitch.";
			}
			return "Tell [npc2.name] that you're [npc2.her] submissive slut, and want to be fucked like a worthless bitch.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"As [npc.namePos] mouth is blocked, [npc.sheIs] not able to speak, but, still wanting to give [npc2.name] an audible indication that [npc.sheIs] enjoying [npc.herself], [npc.she] [npc.verb(let)] out a particularly pathetic whine.",
						"Due to [npc.her] mouth currently being blocked, [npc.nameIsFull] not able to speak, and instead decides to let out an incredibly submissive, horny whine to let [npc2.name] know that [npc.sheIs] enjoying [npc.herself].",
						"Not being deterred by [npc.her] current lack of ability to speak, [npc.name] [npc.verb(let)] out a pathetic, horny whine, letting [npc2.name] know that [npc.sheIs] happy to remain [npc2.her] submissive bitch.",
						"Although [npc.her] mouth is blocked, making [npc.herHim] unable to speak,"
								+ " [npc.nameIsFull] not deterred from making one of the most pathetic whines [npc.she] can, letting [npc2.name] know that [npc.sheIs] enjoying being [npc2.her] submissive bitch."));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(try)] to look as submissive as possible as [npc.she] [npc.verb(cry)] out, ",
						"[npc.Name] [npc.verb(put)] on [npc.her] most innocent expression, before [npc.moaning], "));
	
				sb.append(Sex.getCharacterPerformingAction().getSubmissiveTalk());
			}
			
			return sb.toString();
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction ASKING_FOR_ROUGH_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Look small";
			}
			return "Request rough sex";
		}

		private boolean isAcceptingRequest() {
			return Sex.isCharacterObeyingTarget(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterPerformingAction())
					|| Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DOMINANT).isPositive()
					|| Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SADIST).isPositive()
					|| Sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.CONVINCING_REQUESTS);
		}
		
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you can't directly ask [npc2.name] for [npc2.herHim] to treat you roughly."
						+ " Instead, you could make yourself look as small and pathetic as possible in an attempt to entice [npc2.herHim] to do so."
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Requires [npc.name] to like the '"+Fetish.FETISH_DOMINANT.getName(Sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " like the '"+Fetish.FETISH_SADIST.getName(Sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " or for you to have the '"+Perk.CONVINCING_REQUESTS.getName(Sex.getCharacterPerformingAction())+"' perk.)]";
			}
			return "Ask [npc2.name] to treat you as though you're [npc.her] worthless whore."
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Requires [npc.name] to like the '"+Fetish.FETISH_DOMINANT.getName(Sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " like the '"+Fetish.FETISH_SADIST.getName(Sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " or for you to have the '"+Perk.CONVINCING_REQUESTS.getName(Sex.getCharacterPerformingAction())+"' perk.)]";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this))
					&& Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.DOM_ROUGH
					&& Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_MASOCHIST);
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"As [npc.her] mouth is currently blocked, [npc.name] can't make a direct request, so instead,"
								+ " [npc.she] [npc.verb(let)] out a pathetic whine and [npc.verb(try)] to make [npc.herself] look as small as possible in an attempt to entice [npc2.name] to start treating [npc.herHim] roughly.",
						"Making [npc.herself] look as small and pathetic as possible, [npc.name] [npc.verb(let)] out a high-pitched whine as [npc.she] [npc.verb(attempt)] to entice [npc2.name] into treating [npc.her] badly.",
						"Desperate to be treated like a worthless bitch, [npc.name] [npc.verb(try)] to make [npc.herself] look as small as possible, while simultaneously letting out a pathetic, horny whine.",
						"Not able to speak, due to [npc.her] mouth being blocked, [npc.name] [npc.do] the next best thing [npc.she] can think of in an attempt to get [npc2.name] to start treating [npc.her] badly,"
								+ " and [npc.verb(make)] [npc.herself] look as small and pathetic as possible, while also letting out a high-pitched whine."));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"Putting on the most pleading voice [npc.she] can muster, [npc.name] [npc.verb(beg)] for [npc2.name] to treat [npc.herHim] badly, ",
						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(try)] to look as innocent as possible as [npc.she] [npc.verb(beg)], "));
				
				sb.append(UtilText.returnStringAtRandom(
						"[npc.speech(Come on! I'm your worthless little slut! Treat me like one!)]",
						"[npc.speech(Fuck me like I'm your worthless slut! Please!)]",
						"[npc.speech(Treat me like your dirty little slut! I love it rough!)]",
						"[npc.speech(You know you want to break me! Give me a good, rough fuck! Come on!)]"));
			}
			
			if(Sex.getCharacterPerformingAction().isPlayer()) {
				if(isAcceptingRequest()) {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"Grinning in sadistic delight as you ask this, [npc2.name] lets out a menacing growl as [npc2.she] replies, ",
								"Letting out a deep growl in response to your request, [npc2.name] responds in the affirmative, "));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(Oh, so that's what you want, you little bitch?! Fine, I'll treat you like the worthless slut you are!)]",
							"[npc2.speech(So that's what you really want, is it?! In that case, get ready to be treated like my worthless fuck-toy!)]",
							"[npc2.speech(You dirty little whore! Fine, I'm all too happy to fuck you senseless!)]"));
					
				} else {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"Furrowing [npc2.her] eyebrows as you ask this, [npc2.name] lets out a sigh as [npc2.she] replies, ",
								"Letting out a disappointed sigh in response to your request, [npc2.name] responds in the negative, "));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(Sorry, but I'm not up for that... Let's just keep things going as they are, ok?)]",
							"[npc2.speech(I'm not really into that sort of thing... Let's just keep things as they are.)]",
							"[npc2.speech(Sorry... I'm not one to be doing that sort of thing...)]"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			if(Sex.getCharacterPerformingAction().isPlayer() && isAcceptingRequest()) {
				Sex.setSexPace(Sex.getCharacterTargetedForSexAction(this), SexPace.DOM_ROUGH);
			}
		}
	};
	
	public static final SexAction ASKING_FOR_GENTLE_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Look worried";
			}
			return "Request gentle sex";
		}
		
		private boolean isAcceptingRequest() {
			return Sex.isCharacterObeyingTarget(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterPerformingAction())
					|| Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive()
					|| Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SADIST).isNegative()
					|| Sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.CONVINCING_REQUESTS);
		}
		
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you can't directly ask [npc2.name] for [npc2.herHim] to treat you gently."
						+ " Instead, you could make yourself look quite worried in an attempt to convince [npc2.herHim] to slow down."
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Requires [npc.name] to like the '"+Fetish.FETISH_SUBMISSIVE.getName(Sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " dislike the '"+Fetish.FETISH_SADIST.getName(Sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " or for you to have the '"+Perk.CONVINCING_REQUESTS.getName(Sex.getCharacterPerformingAction())+"' perk.)]";
			}
			return "Ask [npc2.name] to be more gentle with you."
					+ "<br/>"
					+ (isAcceptingRequest()
							?"[style.italicsGood("
							:"[style.italicsBad(")
					+"Requires [npc.name] to like the '"+Fetish.FETISH_SUBMISSIVE.getName(Sex.getCharacterTargetedForSexAction(this))+"' fetish,"
						+ " dislike the '"+Fetish.FETISH_SADIST.getName(Sex.getCharacterTargetedForSexAction(this))+"' fetish,"
						+ " or for you to have the '"+Perk.CONVINCING_REQUESTS.getName(Sex.getCharacterPerformingAction())+"' perk.)]";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& !Sex.isDom(Sex.getCharacterPerformingAction())
					&& Sex.isDom(Sex.getCharacterTargetedForSexAction(this))
					&& Sex.isConsensual()
					&& Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.DOM_GENTLE
					&& Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_MASOCHIST).isNegative();
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"As [npc.her] mouth is currently blocked, [npc.name] can't make a direct request, so instead,"
								+ " [npc.she] [npc.verb(raise)] [npc.her] eyebrows and [npc.verb(let)] out a concerned whine in an attempt to convince [npc2.name] to slow down and be more gentle with [npc.herHim].",
						"Putting on a worried look, [npc.name] [npc.verb(let)] out an anxious whine as [npc.she] [npc.verb(attempt)] to convey to [npc2.name] that [npc.she] [npc.verb(want)] to be treated in a more gentle manner.",
						"Wanting to be treated a little more gently, [npc.name] [npc.verb(raise)] [npc.her] eyebrows, while simultaneously letting out an uneasy whine.",
						"Not able to speak, due to [npc.her] mouth being blocked, [npc.name] [npc.do] the next best thing [npc.she] can think of in an attempt to get [npc2.name] to start treating [npc.her] more gently,"
								+ " and [npc.verb(put)] on an anxious look, while also letting out a worried whine."));
				
			} else {
				sb.append(
						UtilText.returnStringAtRandom(
						"Putting on the most pleading voice [npc.she] can muster, [npc.name] [npc.verb(ask)] for [npc2.name] to calm down, ",
						"[npc.Name] [npc.verb(raise)] [npc.her] eyebrows and [npc.verb(put)] on a worried look as [npc.she] [npc.verb(ask)], "));
				
				sb.append(UtilText.returnStringAtRandom(
						"[npc.speech(P-Please, can you not be so rough? I-I'd like this to be a little more loving...)]",
						"[npc.speech(S-Slow down, please! C-Can't you be a little gentler?)]",
						"[npc.speech(C-Can you treat me a little gentler? T-This is too much for me...)]",
						"[npc.speech(P-Please can you slow down? I-I'd like you to be a little gentler...)]"));
			}

			if(Sex.getCharacterPerformingAction().isPlayer()) {
				if(isAcceptingRequest()) {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"Happily smiling as you ask this, [npc2.name] lets out a soothing sigh as [npc2.she] replies, ",
								"Letting out a soft [npc.moan] in response to your request, [npc2.name] responds in the affirmative, "));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(Oh, so that's what you want, hmm? I'm happy to take things a little slower...)]",
							"[npc2.speech(That's fine with me... Let's take things a little slower, ok?)]",
							"[npc2.speech(That sounds like a good idea... I'll be gentler with you from now on...)]"));
					
				} else {
					sb.append("<br/><br/>"
							+UtilText.returnStringAtRandom(
								"Furrowing [npc2.her] eyebrows as you ask this, [npc2.name] lets out a tutting noise as [npc2.she] replies, ",
								"Letting out a disapproving tut in response to your request, [npc2.name] responds in the negative, "));

					sb.append(UtilText.returnStringAtRandom(
							"[npc2.speech(Yeah, I'm not up for that soft crap. You're just going to have to learn to like this!)]",
							"[npc2.speech(I'm not really into all that gentle loving shit. You're going to have to just deal with the way I like doing things.)]",
							"[npc2.speech(Yeah, that's not happening. Doing ask for all that gentle loving crap again!)]"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			if(Sex.getCharacterPerformingAction().isPlayer() && isAcceptingRequest()) {
				Sex.setSexPace(Sex.getCharacterTargetedForSexAction(this), SexPace.DOM_GENTLE);
			}
		}
	};
	
}
