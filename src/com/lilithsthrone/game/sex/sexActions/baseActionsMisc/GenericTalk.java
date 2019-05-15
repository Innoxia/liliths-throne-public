package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.3
 * @version 0.3.3
 * @author Innoxia
 */
public class GenericTalk {
	
	public static final SexAction SUBMISSIVE_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
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
			return "Tell [npc2.name] that you're [npc2.her] submissive slut, and want to be fucked like a worthless bitch.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(
					UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(try)] to look as submissive as possible as [npc.she] [npc.verb(cry)] out, ",
					"[npc.Name] [npc.verb(put)] on [npc.her] most innocent expression, before [npc.moaning], "));
			
			sb.append(UtilText.returnStringAtRandom(
					"[npc.speech(Yes! I'm your little slut! Take me however you want!)]",
					"[npc.speech(Yes! I'm yours! Use me! Do what you want with me!)]",
					"[npc.speech(Yeah! I'm your worthless little bitch! Fuck me!)]",
					"[npc.speech(Yes, I'm your worthless slut! Use me like your little fuck-toy!)]"));
			
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
	
	public static final SexAction ROUGH_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Degrading talk";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc2.name] that [npc2.sheIs] your little fuck-toy.";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isPositive()) {
				sb.append(UtilText.returnStringAtRandom(
						"With an evil grin, [npc.name] [npc.verb(snarl)] at [npc2.name], ",
						"[npc.NamePos] voice drips with sadistic glee as [npc.she] [npc.verb(snarl)], "));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"Grinning to [npc.herself], [npc.name] [npc.verb(snarl)] at [npc2.name], ",
						"[npc.Name] puts on [npc.her] most dominant voice as [npc.she] [npc.verb(snarl)], "));
			}
			
			if(Sex.isCharacterEngagedInOngoingAction(Sex.getCharacterPerformingAction(), Sex.getCharacterTargetedForSexAction(this))) {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.speech(That's right, bitch, you're just my slutty little fuck-toy!)]",
						"[npc.speech(Hah! I love fucking pathetic, submissive sluts like you!)]",
						"[npc.speech(You're nothing but my worthless fuck-toy, understand?! You belong to me!)]",
						"[npc.speech(That's right! You love being my worthless little fuck-toy, don't you slut?!)]"));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.speech(I bet you can't wait for me to give you a good, hard fuck, can you, you worthless slut?)]",
						"[npc.speech(All you're good for is being my worthless fuck-toy!)]",
						"[npc.speech(You're just my pathetic little fuck-toy now, understand?! You belong to me!)]",
						"[npc.speech(I love fucking worthless sluts like you!)]"));
			}
			
			return sb.toString();
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
			return "Request rough sex";
		}

		@Override
		public String getActionDescription() {
			return "Ask [npc2.name] to treat you as though you're [npc.her] worthless whore.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.DOM_ROUGH
					&& (Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive()
							|| Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()
							|| Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive())
					&& !Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SUBMISSIVE).isNegative()
					&& !Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_SUB).isNegative()
					&& !Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_MASOCHIST).isNegative();
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(
					UtilText.returnStringAtRandom(
					"Putting on the most pleading voice [npc.she] can muster, [npc.name] [npc.verb(beg)] for [npc2.name] to treat [npc.herHim] badly, ",
					"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(try)] to look as innocent as possible as [npc.she] [npc.verb(beg)], "));
			
			sb.append(UtilText.returnStringAtRandom(
					"[npc.speech(Come on! I'm your worthless little slut! Treat me like one!)]",
					"[npc.speech(Fuck me like I'm your worthless slut! Please!)]",
					"[npc.speech(Treat me like your dirty little slut! I love it rough!)]",
					"[npc.speech(You know you want to break me! Give me a good, rough fuck! Come on!)]"));
			
			return sb.toString();
		}
	};
	
}
