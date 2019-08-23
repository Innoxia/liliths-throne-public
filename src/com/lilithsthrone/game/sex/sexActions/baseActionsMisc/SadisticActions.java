package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SadisticActions {

	public static final SexAction ROUGH_TALK = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DOMINANT) || Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST)) && !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
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
	
	public static final SexAction SLAP_FACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.getProperties().hasValue(PropertyValue.sadisticSexContent)) {
				return false;
			}
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Sex.getPosition().getSlotTargets().get(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())).get(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Sex.getPosition().getSlotTargets().get(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))).get(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaPenetration.FINGER.isFree(Sex.getCharacterPerformingAction())
					&& !Sex.isDom(Sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed)
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		
		@Override
		public String getActionTitle() {
			return "Slap face";
		}

		@Override
		public String getActionDescription() {
			return "Slap [npc2.namePos] face to put [npc2.herHim] in [npc2.her] place.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Wanting to put [npc2.name] in [npc2.her] place, [npc.name] [npc.verb(lift)] [npc.her] [npc.hand], before swinging down to deliver a sharp, stinging slap to [npc2.her] face.",
					"Lifting [npc.her] [npc.hand], [npc.name] [npc.verb(swing)] down, delivering a sharp slap to [npc2.namePos] face in order to put [npc2.herHim] in [npc2.her] place.",
					"Seeking to remind [npc2.name] who's in charge, [npc.name] [npc.verb(raise)] [npc.her] [npc.hand], before swiping down and delivering a sharp slap to [npc2.her] face."));
			
			if(Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" A clearly aroused yelp escapes from [npc2.namePos] mouth at the moment of contact, letting [npc.name] know that [npc2.sheIs] deriving masochistic pleasure from being abused in such a fashion.",
						" The horny squeal that escapes [npc2.namePos] mouth is enough to let anyone realise that [npc2.sheIs] getting turned on from being treated in such a degrading manner.",
						" Instead of a painful cry, [npc2.name] [npc2.verb(let)] out a horny moan, letting [npc.name] know that [npc2.sheIs] a masochist who's getting turned on by being abused like this."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" A protesting yelp escapes from [npc2.namePos] mouth at the moment of contact,"
								+ (Sex.getCharacterPerformingAction().isPlayer()
										?" which is precisely the reaction you were looking for."
										:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
						" The pained squeal which immediately escapes from [npc2.namePos] mouth is exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] tears well up in"
								+ (Sex.getCharacterTargetedForSexAction(this).isPlayer()
										?" your [npc2.eyes+]."
										:" [npc.her] submissive bitch's [npc2.eyes+]."),
						" After letting out a shocked cry, tears start to well up in [npc2.namePos] [npc2.eyes], letting [npc.name] know that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SPIT_FACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.getProperties().hasValue(PropertyValue.sadisticSexContent)) {
				return false;
			}
			boolean mouthTongue = false;
			boolean mouthTongueReversed = false;
			try {
				mouthTongue = Sex.getPosition().getSlotTargets().get(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())).get(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.TONGUE).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthTongueReversed = Sex.getPosition().getSlotTargets().get(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))).get(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.TONGUE);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaOrifice.MOUTH.isFree(Sex.getCharacterPerformingAction())
					&& !Sex.isDom(Sex.getCharacterTargetedForSexAction(this))
					&& (mouthTongue || mouthTongueReversed)
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		
		@Override
		public String getActionTitle() {
			return "Spit in face";
		}

		@Override
		public String getActionDescription() {
			return "Spit in [npc2.namePos] face show [npc2.herHim] that [npc2.sheIs] worthless.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Wanting to show [npc2.name] that [npc2.sheIs] a worthless bitch, [npc.name] [npc.verb(lean)] in towards [npc.herHim], before spitting on [npc2.her] face.",
					"Leaning in towards [npc2.name], [npc.name] [npc.verb(purse)] [npc.her] [npc.lips+], before spitting directly on [npc2.her] face.",
					"Seeking to remind [npc2.name] of how worthless [npc2.sheIsFull], [npc.name] [npc.verb(lean)] in towards [npc2.herHim], before pursing [npc.her] [npc.lips+] and spitting directly on [npc2.her] face."));
			
			if(Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" A clearly aroused [npc2.moan] escapes from [npc2.namePos] mouth as the ball of saliva splatters onto [npc2.her] cheek, letting [npc.name] know that [npc2.sheIs] deriving masochistic pleasure from being abused in such a fashion.",
						" The horny squeal that escapes [npc2.namePos] mouth is enough to let anyone realise that [npc2.sheIs] getting turned on from being treated in such a degrading manner.",
						" Instead of a disgusted cry, [npc2.name] [npc2.verb(let)] out a horny [npc2.moan], letting [npc.name] know that [npc2.sheIs] a masochist who's getting turned on by being abused like this."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" A disgusted cry escapes from [npc2.namePos] mouth as the ball of saliva splatters onto [npc2.her] cheek,"
								+ (Sex.getCharacterPerformingAction().isPlayer()
										?" which is precisely the reaction you were looking for."
										:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
						" The horrified protestations which [npc2.name] immediately [npc2.verb(make)] is exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] tears well up in"
								+ (Sex.getCharacterTargetedForSexAction(this).isPlayer()
										?" your [npc2.eyes+]."
										:" [npc.her] submissive bitch's [npc2.eyes+]."),
						" After letting out a shocked cry, tears start to well up in [npc2.namePos] [npc2.eyes], letting [npc.name] know that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction CHOKE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.getProperties().hasValue(PropertyValue.sadisticSexContent)) {
				return false;
			}
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Sex.getPosition().getSlotTargets().get(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())).get(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Sex.getPosition().getSlotTargets().get(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))).get(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}

			return SexAreaPenetration.FINGER.isFree(Sex.getCharacterPerformingAction())
					&& !Sex.isDom(Sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed)
					&& (Sex.getCharacterPerformingAction().isPlayer() || Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public String getActionTitle() {
			return "Choke";
		}

		@Override
		public String getActionDescription() {
			return "Grab and squeeze [npc2.namePos] neck in order to choke [npc2.herHim].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Sex.getAllContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				sb.append(UtilText.returnStringAtRandom(
						"Continuing to ram [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] up to grab [npc2.her] neck, before squeezing down and choking [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] up to grab [npc2.namePos] neck, and, while still forcefully pounding [npc2.her] [npc2.pussy+], [npc.she] roughly [npc.verb(squeeze)] down in order to choke [npc2.herHim].",
						"Slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] up and [npc.verb(grab)] [npc2.her] neck,"
								+ " squeezing down and choking [npc2.herHim] as [npc.she] [npc.verb(pull)] back and [npc.verb(continue)] roughly fucking [npc2.herHim]."));
				
			} else if(Sex.getAllContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				sb.append(UtilText.returnStringAtRandom(
						"Continuing to ram [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] up to grab [npc2.her] neck, before squeezing down and choking [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] up to grab [npc2.namePos] neck, and, while still forcefully pounding [npc2.her] [npc2.asshole+], [npc.she] roughly [npc.verb(squeeze)] down in order to choke [npc2.herHim].",
						"Slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] up and [npc.verb(grab)] [npc2.her] neck,"
								+ " squeezing down and choking [npc2.herHim] as [npc.she] [npc.verb(pull)] back and [npc.verb(continue)] roughly fucking [npc2.herHim]."));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"With a sadistic grin on [npc.her] face, [npc.name] [npc.verb(reach)] up to grab [npc2.name] by [npc2.her] neck, before squeezing down and choking [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] up to grab [npc2.namePos] neck, and, letting out a sadistic laugh, [npc.she] roughly [npc.verb(squeeze)] down in order to choke [npc2.herHim].",
						"With a cruel laugh, [npc.name] [npc.verb(reach)] up and [npc.verb(grab)] [npc2.name] by [npc2.her] neck, before sadistically squeezing down and choking [npc2.herHim]."));
			}
			
			if(Sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" The gargled spluttering sounds which [npc2.name] [npc2.verb(start)] to make in response have a distinctly lewd quality to them, letting [npc.name] know that [npc2.sheIs] being turned on by this abuse.",
						" The horny, gargled [npc2.moans] which [npc2.name] immediately [npc2.verb(start)] to produce are more than enough to let anyone realise that [npc2.sheIs] a masochist, and is getting aroused by this poor treatment.",
						" The spluttering gasps which [npc2.name] [npc2.verb(begin)] to emit are intermingled with several horny [npc2.moans], letting [npc.name] know that [npc2.sheIs] deriving masochistic pleasure from being abused in such a fashion."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" A series of gargled spluttering noises escape from [npc2.namePos] mouth,"
								+ (Sex.getCharacterPerformingAction().isPlayer()
										?" which is precisely the reaction you were looking for."
										:" and from the wicked grin that settles on [npc.namePos] face, this was just the reaction [npc.she] [npc.was] looking for."),
						" The distressed, gargled cries which [npc2.name] [npc2.verb(start)] to make are exactly what [npc.name] [npc.was] looking for, and [npc.she] can't help but grin as [npc.she] [npc.verb(see)] tears well up in"
								+ (Sex.getCharacterTargetedForSexAction(this).isPlayer()
										?" your [npc2.eyes+]."
										:" [npc.her] submissive bitch's [npc2.eyes+]."),
						" Letting out a series of spluttering gasps, tears quickly start to well up in [npc2.namePos] [npc2.eyes], letting [npc.name] know that [npc.her] abuse is having the exact effect [npc.she] [npc.was] looking for."));
			}
			
			return sb.toString();
		}
	};
	
}
