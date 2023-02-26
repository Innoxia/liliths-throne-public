package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.3
 * @version 0.4.2.1
 * @author Innoxia
 */
public class GenericTalk {

	public static final SexAction STOP_RAPE_PLAY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_DOM).isPositive()
							&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST)))
					&& !Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING
					&& !Main.sex.isSexPaceForced(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).getLustLevel().isResistingFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			return "Stop rape-play";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you're not able to tell [npc2.name] to stop pretending to resist, but you can still make a disapproving noise to let [npc2.her] know that you want [npc2.herHim] to stop it.";
			}
			return "Tell [npc2.name] to stop pretending to resist.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append("Although [npc.namePos] mouth is blocked, [npc.sheIs] still able to make a disapproving noise to signal to [npc2.name] that [npc2.she] should stop pretending to resist.");
				
			} else {
				sb.append("Not liking [npc2.namePos] behaviour, [npc.name] [npc.verb(tell)] [npc2.herHim] in no uncertain terms to stop pretending to resist.");
			}

			sb.append(" Although [npc2.she] [npc2.verb(let)] out a disappointed [npc2.moan], [npc2.name] [npc2.verb(decide)] to agree to [npc.namePos] request and [npc2.verb(stop)] pretending to resist.");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.setCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};

	public static final SexAction ALLOW_RAPE_PLAY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_DOM).isPositive()
						|| Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST))
					&& !Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
					&& Main.game.isNonConEnabled()
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					&& !Main.sex.isSexPaceForced(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.isCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_NON_CON_SUB);
		}
		@Override
		public String getActionTitle() {
			return "Allow rape-play";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Although your mouth is blocked, you can still make a suggestive noise to let [npc2.her] know that [npc2.she] can pretend to resist if [npc2.she] wants to.";
			}
			return "Tell [npc2.name] that [npc.she] can pretend to resist if [npc2.she] wants to.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append("Although [npc.namePos] mouth is blocked, [npc.sheIs] still able to make a suggestive noise to signal to [npc2.name] that [npc2.she] can pretend to resist if [npc2.she] [npc2.verb(want)] to.");
				
			} else {
				sb.append("Wanting things to get a little kinkier, [npc.name] [npc.verb(tell)] [npc2.name] that [npc2.she] can pretend to resist if [npc2.she] [npc2.verb(want)] to.");
			}

			sb.append(" Letting out an excited [npc2.moan], [npc2.name] [npc2.verb(take)] a moment in which to decide whether or not to continue pretending to resist...");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.setCharacterBannedFromRapePlay(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction ROUGH_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DOMINANT) || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Threatening growl";
			}
			return "Degrading talk";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you're not able to tell [npc2.name] that [npc2.sheIs] your little fuck-toy, but you can still make an aggressive growling noise to let [npc2.her] know you still mean business.";
			}
			return "Tell [npc2.name] that [npc2.sheIs] your little fuck-toy.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"As [npc.namePos] mouth is blocked, [npc.sheIs] not able to speak, but, still wanting to give [npc2.name] an audible reminder that [npc2.sheIs] [npc.her] bitch, [npc.she] [npc.verb(let)] out a particularly aggressive growl.",
						"Due to [npc.her] mouth currently being blocked, [npc.nameIsFull] not able to speak, and instead decides to let out a deep, menacing growl to let [npc2.name] know that [npc2.sheIs] still [npc.her] worthless bitch.",
						"Not being deterred by [npc.her] current lack of ability to speak, [npc.name] [npc.verb(let)] out a particularly threatening growl, letting [npc2.name] know that [npc2.sheIs] going to be treated like a pathetic fuck-toy.",
						"Although [npc.her] mouth is blocked, making [npc.herHim] unable to speak,"
								+ " [npc.nameIsFull] not deterred from making one of the most menacing growls [npc.she] can muster, letting [npc2.name] know that [npc2.sheIs] going to be treated like a submissive bitch."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isPositive()) {
					sb.append(UtilText.returnStringAtRandom(
							"With an evil grin, [npc.name] [npc.verb(snarl)] at [npc2.name], ",
							"[npc.NamePos] voice drips with sadistic glee as [npc.she] [npc.verb(snarl)], "));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							"Grinning to [npc.herself], [npc.name] [npc.verb(snarl)] at [npc2.name], ",
							"[npc.Name] puts on [npc.her] most dominant voice as [npc.she] [npc.verb(snarl)], "));
				}
				
				sb.append(Main.sex.getRoughTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SUBMISSIVE_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SUBMISSIVE);
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Submissive whine";
			}
			return "Submissive talk";
		}
			
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getLastUsedPlayerAction()==ROUGH_TALK && Math.random()<0.5f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you're not able to tell [npc2.name] that you're [npc2.her] submissive slut, but you can still make a pitiful whine to let [npc2.her] know you're still [npc2.her] loyal bitch.";
			}
			return "Tell [npc2.name] that you're [npc2.her] submissive slut, and want to be fucked like a worthless bitch.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
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
	
				sb.append(Main.sex.getSubmissiveTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction LOVING_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Loving sigh";
			}
			return "Loving confession";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you're not able to tell [npc2.name] that you love [npc2.herHim], but you can still make a loving humming noise to let [npc2.her] know what your feelings are.";
			}
			return "Gently tell [npc2.name] that you love [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"As [npc.namePos] mouth is blocked, [npc.sheIs] not able to speak, but, still wanting to give [npc2.name] a reminder of [npc.her] feelings, [npc.she] [npc.verb(let)] out a particularly loving sigh.",
						"Due to [npc.her] mouth currently being blocked, [npc.nameIsFull] not able to speak, and instead decides to let out a soft, whimsical sigh to let [npc2.name] know that [npc.she] [npc.verb(love)] [npc2.herHim].",
						"Not being deterred by [npc.her] current lack of ability to speak, [npc.name] [npc.verb(let)] out a gentle, pining sigh, letting [npc2.name] know that [npc.she] [npc.verb(love)] [npc2.herHim]."));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"With a loving smile on [npc.her] face,",
						"Lovingly gazing at [npc2.name],"));
				sb.append(UtilText.returnStringAtRandom(
						" [npc.name] softly",
						" [npc.name] gently"));
				sb.append(UtilText.returnStringAtRandom(
						" [npc.verb(sigh)], ",
						" [npc.verb(whisper)], "));
				
				sb.append(Main.sex.getLovingTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction LOVING_REPLY = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			null) {
		@Override
		public boolean isLovingAction() {
			return true;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getLastUsedSexAction(Main.sex.getCharacterTargetedForSexAction(this))==LOVING_TALK;
		}
		@Override
		public String getActionTitle() {
			return "Loving response";
		}
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().isPlayer() && Math.random()<0.8f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you're not able to tell [npc2.name] that you love [npc2.herHim] too, but you can still make a gentle sigh to let [npc2.her] know your feelings.";
			}
			return "Tell [npc2.name] that you love [npc2.herHim] too.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				sb.append(
						UtilText.returnStringAtRandom(
						"As [npc.namePos] mouth is blocked, [npc.sheIs] not able to speak, but, still wanting to tell [npc2.name] that [npc2.her] feelings are reciprocated, [npc.she] [npc.verb(let)] out a deeply loving moan.",
						"Due to [npc.her] mouth currently being blocked, [npc.nameIsFull] not able to speak, and instead decides to let out a loving moan to let [npc2.name] know that [npc.she] [npc.verb(love)] [npc2.herHim] too.",
						"Not being deterred by [npc.her] current lack of ability to speak, [npc.name] [npc.verb(let)] out a loving moan, letting [npc2.name] know that [npc.she] [npc.verb(love)] [npc2.herHim] too."));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"Happily smiling at [npc2.name],",
						"Letting out a happy moan,"));
				sb.append(UtilText.returnStringAtRandom(
						" [npc.name] lovingly",
						" [npc.name] passionately"));
				sb.append(UtilText.returnStringAtRandom(
						" [npc.verb(reply)], ",
						" [npc.verb(respond)], "));
				
				sb.append(Main.sex.getLovingResponseTalk(Main.sex.getCharacterPerformingAction()));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction ASKING_FOR_ROUGH_SEX = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Look small";
			}
			return "Request rough sex";
		}

		private boolean isAcceptingRequest() {
			return Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())
//					|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DOMINANT).isPositive()
					|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SADIST).isPositive()
					|| Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS);
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you can't directly ask [npc2.name] for [npc2.herHim] to treat you roughly."
						+ " Instead, you could make yourself look as small and pathetic as possible in an attempt to entice [npc2.herHim] to do so."
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Requires [npc2.name] to like the '"+Fetish.FETISH_DOMINANT.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " like the '"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " or for you to have the '"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"' trait activated.)]";
			}
			return "Ask [npc2.name] to treat you as though you're [npc2.her] worthless whore."
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Requires [npc2.name] to like the '"+Fetish.FETISH_DOMINANT.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " like the '"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " or for you to have the '"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"' trait activated.)]";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.DOM_ROUGH
					&& (Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_MASOCHIST) || Main.sex.getCharacterPerformingAction().isPlayer());
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
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
			
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
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
			if(Main.sex.getCharacterPerformingAction().isPlayer() && isAcceptingRequest()) {
				Main.sex.setSexPace(Main.sex.getCharacterTargetedForSexAction(this), SexPace.DOM_ROUGH);
			}
		}
	};
	
	public static final SexAction ASKING_FOR_GENTLE_SEX = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Look worried";
			}
			return "Object to rough";
		}
		
		private boolean isAcceptingRequest() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_SADIST)
					&& (Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())
						|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive()
						|| Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}
		
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "As your mouth is blocked, you can't directly ask [npc2.name] for [npc2.herHim] to treat you gently."
						+ " Instead, you could make yourself look quite worried in an attempt to convince [npc2.herHim] to slow down."
						+ "<br/>"
						+ (isAcceptingRequest()
								?"[style.italicsGood("
								:"[style.italicsBad(")
						+"Requires [npc.name] to like the '"+Fetish.FETISH_SUBMISSIVE.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " dislike the '"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish,"
							+ " or for you to have the '"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"' trait activated.)]";
			}
			return "Ask [npc2.name] to be more gentle with you."
					+ "<br/>"
					+ (isAcceptingRequest()
							?"[style.italicsGood("
							:"[style.italicsBad(")
					+"Requires [npc.name] to like the '"+Fetish.FETISH_SUBMISSIVE.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish,"
						+ " dislike the '"+Fetish.FETISH_SADIST.getName(Main.sex.getCharacterTargetedForSexAction(this))+"' fetish,"
						+ " or for you to have the '"+Perk.CONVINCING_REQUESTS.getName(Main.sex.getCharacterPerformingAction())+"' trait activated.)]";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.DOM_ROUGH
					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_MASOCHIST).isNegative() || Main.sex.getCharacterPerformingAction().isPlayer());
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
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
						"[npc.speech(Please, can you not be so rough? I'd like this to be a little more loving...)]",
						"[npc.speech(Slow down, please! Can't you be a little gentler?)]",
						"[npc.speech(Can you treat me a little gentler? This is too much for me...)]",
						"[npc.speech(Please can you slow down? I'd like you to be a little gentler...)]"));
			}

			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
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
							"[npc2.speech(Yeah, that's not happening. Don't ask for all that gentle loving crap again!)]"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isPlayer() && isAcceptingRequest()) {
				Main.sex.setSexPace(Main.sex.getCharacterTargetedForSexAction(this), SexPace.DOM_GENTLE);
			}
		}
	};
	
}
