package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.game.sex.positions.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.3.3
 * @author Innoxia
 */
public class GenericActions {
	
	public static final SexAction SLAP_FACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
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
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Sex.isPenetrationTypeFree(Sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)
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
						" Instead of a painful cry, [npc2.name] [npc2.verb(let)] out a horny moan, letting [npc.name] know that [npc2.sheIs] a masochist, who's getting turned on by being abused like this."));
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
	
	public static final SexAction CHOKE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
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
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			
			return Sex.isPenetrationTypeFree(Sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)
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
	
	public static final SexAction GENERIC_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public ArousalIncrease getArousalGainSelf() {
			if(Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.ZERO_NONE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isPlayer()
					|| (!Sex.isDom(Sex.getCharacterPerformingAction()) && !Sex.isConsensual());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist";
		}

		@Override
		public String getActionDescription() {
			return "Resist having sex with [npc2.name].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionBipeds.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.BACK_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)], and [npc.verb(kick)] [npc2.name] as [npc.she] desperately [npc.verb(try)] to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.she] easily [npc2.verb(keep)] [npc.herHim] pinned back against the wall.",
						
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to wriggle out of [npc2.her] grasp,"
								+ " but [npc2.her] grip is too strong for [npc.herHim], and [npc2.name] easily [npc2.verb(keep)] [npc.herHim] pushed back against the wall.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(push)] [npc.herHim] back against the wall.");
				
			} else if(Sex.getPosition()==SexPositionBipeds.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] [npc.verb(try)] to crawl away from [npc2.name],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] [npc2.verb(grab)] [npc.her] [npc.hips] and [npc2.verb(pull)] [npc.her] [npc.ass] back into [npc2.her] groin.",
						
						"Trying to crawl away from [npc2.name] on all fours, [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc2.she] [npc2.verb(grasp)] [npc.her] [npc.hips], before pulling [npc.herHim] back into position.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(try)] to crawl away from [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(take)] hold of [npc.her] [npc.hips] and [npc2.verb(pull)] [npc.herHim] back into [npc2.herHim].");
				
			} else if(Sex.getPosition()==SexPositionBipeds.FACING_WALL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.FACE_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)], and [npc.verb(kick)] [npc2.name] as [npc.she] desperately [npc.verb(try)] to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] easily [npc2.verb(keep)] [npc.herHim] pinned up against the wall.",
						
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to wriggle out of [npc2.her] grasp,"
								+ " but [npc2.her] grip is too strong for [npc.herHim], and [npc2.she] easily [npc2.verb(keep)] [npc.herHim] pushed up against the wall.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(push)] [npc.herHim] up against the wall.");
				
			} else if(Sex.getPosition()==SexPositionBipeds.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.KNEELING_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] to push [npc2.namePos] groin away from [npc.her] [npc.face],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] [npc2.verb(grab)] hold of [npc.her] head and [npc2.verb(pull)] [npc.herHim] back into [npc2.her] crotch.",
						
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.face] away from [npc2.her] groin,"
								+ " but [npc2.namePos] grasp on [npc.her] head is too strong, and [npc2.she] quickly [npc2.verb(force)] [npc.herHim] back into position.",
								
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(pull)] [npc.her] [npc.face] back into [npc2.her] groin.");
				
			} else if(Sex.getPosition()==SexPositionBipeds.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.SIXTY_NINE_BOTTOM) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] to push [npc2.name] off of [npc.herHim] as [npc.she] desperately [npc.verb(try)] to wriggle out from under [npc2.herHim],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] easily [npc2.verb(pin)] [npc.herHim] to the floor.",
						
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to wriggle out from under [npc2.herHim],"
								+ " but [npc2.name] [npc2.verb(press)] [npc2.her] body down onto [npc.hers], preventing [npc.herHim] from getting away.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(use)] [npc2.her] body to pin [npc.herHim] to the floor.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)], and [npc.verb(kick)] [npc2.name] as [npc.she] desperately [npc.verb(try)] to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.she] easily [npc2.verb(continue)] restraining [npc.herHim].",
								
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to wriggle out of [npc2.her] grasp.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim], [npc.sobbing] in distress as [npc2.she] easily [npc2.verb(hold)] [npc.herHim] in place.");
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			}
		}
	};
	
	public static final SexAction PLAYER_SELF_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Grow cock (self)";
		}

		@Override
		public String getActionDescription() {
			return "Use your demonic powers to grow a cock for yourself. <b>You will automatically transform the grown cock away when sex ends.</b>";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getPlayer().getRace()==Race.DEMON || Main.game.getPlayer().getRace()==Race.SLIME)
					&& !Main.game.getPlayer().hasPenis()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {//TODO resisting variations
			if(Main.game.getPlayer().getRace()==Race.DEMON) {
				return "Deciding to use your transformative powers to give yourself a thick demonic cock, you grin at [npc2.name] as you [npc.moanVerb],"
						+ " [npc.speech(You're going to love this!)]";
			} else {
				return "Deciding to use your transformative powers to give yourself a thick slimy cock, you grin at [npc2.name] as you [npc.moanVerb],"
						+ " [npc.speech(You're going to love this!)]";
			}
		}

		@Override
		public String applyEffectsString() {
			Sex.getCharactersGrewCock().add(Main.game.getPlayer());
			
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().getRace()==Race.DEMON) {
				sb.append(Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON));
			} else {
				sb.append(Main.game.getPlayer().setPenisType(RacialBody.valueOfRace(Subspecies.getFleshSubspecies(Main.game.getPlayer()).getRace()).getPenisType()));
			}
			if(Main.game.getPlayer().getPenisRawCumStorageValue() < 150) {
				sb.append(Main.game.getPlayer().setPenisCumStorage(150));
			}
			Main.game.getPlayer().fillCumToMaxStorage();
			if(Main.game.getPlayer().getPenisRawSizeValue() < 20) {
				sb.append(Main.game.getPlayer().setPenisSize(20));
			}
			return sb.toString();
		}
	};
	
	public static final SexAction PLAYER_GET_PARTNER_TO_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Grow cock";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to grow a demonic cock.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isMasturbation()
					&& (Sex.getCharacterTargetedForSexAction(this).getRace()==Race.DEMON || Sex.getCharacterTargetedForSexAction(this).getRace()==Race.SLIME)
					&& !Sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {//TODO resisting variations
			if(Sex.getCharacterTargetedForSexAction(this).getRace()==Race.DEMON) {
				return "Grinning down at [npc2.name], you tease, [npc.speech(How about you use your transformative powers to grow a nice thick demonic cock, so that we can have even more fun!)]"
						+ "<br/><br/>"
						+(Sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
							?"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] naked groin, you see a large bump start to form beneath [npc2.her] [npc2.skin]."
									+ " Before you have any time to change your mind, it quickly grows out into a fat demonic cock, and as you stare down at the little wriggling bumps that press out all along its shaft,"
									+ " a bead of precum seeps out and drips from the tip."
							:"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] groin, you see a huge bulge quickly forming in [npc2.her] "
									+Sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."
									+ " Before you have any time to change your mind, [npc2.she] lets out [npc2.a_moan+], and you realise that [npc2.sheHas] now got a huge demonic cock hiding beneath [npc2.her] clothing.");
				
			} else {
				return "Grinning down at [npc2.name], you tease, [npc.speech(How about you use your transformative powers to grow a nice thick slimy cock, so that we can have even more fun!)]"
						+ "<br/><br/>"
						+(Sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
							?"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] naked groin, you see a large bump start to form beneath [npc2.her] [npc2.skin]."
									+ " Before you have any time to change your mind, it quickly grows out into a fat slimy cock, and as you stare down at its throbbing length, a bead of precum seeps out and drips from the tip."
							:"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] groin, you see a huge bulge quickly forming in [npc2.her] "
									+Sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."
									+ " Before you have any time to change your mind, [npc2.she] lets out [npc2.a_moan+], and you realise that [npc2.sheHas] now got a huge slimy cock hiding beneath [npc2.her] clothing.");
			}
		}

		@Override
		public void applyEffects() {
			Sex.getCharactersGrewCock().add(Sex.getCharacterTargetedForSexAction(this));
			
			if(Sex.getCharacterTargetedForSexAction(this).getRace()==Race.DEMON) {
				Sex.getCharacterTargetedForSexAction(this).setPenisType(PenisType.DEMON_COMMON);
			} else {
				Sex.getCharacterTargetedForSexAction(this).setPenisType(RacialBody.valueOfRace(Subspecies.getFleshSubspecies(Sex.getCharacterTargetedForSexAction(this)).getRace()).getPenisType());
			}
			Sex.getCharacterTargetedForSexAction(this).setPenisCumStorage(150);
			Sex.getCharacterTargetedForSexAction(this).fillCumToMaxStorage();
			if(Sex.getCharacterTargetedForSexAction(this).hasVagina()) {
				Sex.getCharacterTargetedForSexAction(this).setTesticleSize(TesticleSize.ZERO_VESTIGIAL);
			} else {
				Sex.getCharacterTargetedForSexAction(this).setTesticleSize(TesticleSize.THREE_LARGE);
			}
			Sex.getCharacterTargetedForSexAction(this).setPenisGirth(PenisGirth.THREE_THICK);
			Sex.getCharacterTargetedForSexAction(this).setPenisSize(20);
		}
	};

	
	public static final SexAction HYPNOTIC_SUGGESTION_LUST_DECREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Calming suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc2.she] doesn't like having sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					&& (Sex.getCharacterPerformingAction().isPlayer() || (Sex.getCharacterTargetedForSexAction(this).getLust()>25 && Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_DOM)));
		}

		@Override
		public String getDescription() {
			return "<p>"
					+ "Wanting to take advantage of the fact that [npc2.nameIsFull] under the strong effect of a psychoactive substance, [npc.name] [npc.verb(lean)] towards [npc2.herHim] and [npc.moansVerb],"
						+ " [npc.speech(You aren't really interested in having sex with me, are you?)]"
					+ "</p>"
					+ "<p>"
						+ "[npc2.Name] can't help but agree with what [npc.sheIs] saying, and [npc2.name] haltingly [npc2.verb(answer)],"
						+ " [npc2.speech(Yes... I... I don't know why I'm having sex with you...)]"
					+ "</p>"
					+ "<p>"
						+ "Pushing a little further,"+(!Sex.getCharacterPerformingAction().isPlayer()?" and driven on by [npc.her] fetish for having non-consensual sex,":"")+" [npc.name] [npc.verb(continue)],"
						+ " [npc.speech(You'd rather I wasn't fucking you right now, isn't that right?)]"
					+ "</p>"
					+ "<p>"
					+ (Sex.isDom(Sex.getCharacterTargetedForSexAction(this))
						?"As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] [npc2.verb(let)] out a disappointed sigh,"
							+ " [npc2.speech(This isn't really all that fun...)]"
						:"As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] [npc2.verb(let)] out a distressed cry,"
							+ " [npc2.speech(Wait, w-why is this happening?! Please, stop it! Get away from me!)]")
					+ "</p>";
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterTargetedForSexAction(this).incrementLust(-50, false);
		}
	};
	
	public static final SexAction HYPNOTIC_SUGGESTION_LUST_INCREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Lustful suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc2.she] loves to have sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					&& (Sex.getCharacterPerformingAction().isPlayer() || (Sex.getCharacterTargetedForSexAction(this).getLust()<75 && !Sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_DOM)));
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Wanting to take advantage of the fact that [npc2.nameIsFull] under the mind-altering effects of a psychoactive substance, [npc.name] [npc.verb(lean)] in towards [npc2.herHim] and [npc.moanVerb],"
						+ " [npc.speech(You love having sex with me, don't you?)]"
					+ "</p>");
			
			if(Sex.getCharacterTargetedForSexAction(this).isPlayer()) {
				sb.append("<p>"
							+ "As [npc.name] says this, you suddenly feel a fuzzy warmth clouding your mind, and you're vaguely aware of your [npc2.eyes] glazing over as you answer,"
							+ " [npc2.speech(Yes... I... I love having sex with you...)]"
						+ "</p>");
			} else {
				sb.append("<p>"
						+ "[npc2.NamePos] [npc2.eyes] glaze over a little as [npc2.she] answers,"
						+ " [npc2.speech(Yes... I... I love having sex with you...)]"
					+ "</p>");
			}
			
			sb.append( "<p>"
						+ "Pushing a little further, [npc.name] [npc.verb(continue)],"
						+ " [npc.speech(You love begging for me to fuck you, isn't that right?)]"
					+ "</p>");

			if(Sex.getCharacterTargetedForSexAction(this).isPlayer()) {
				sb.append("<p>"
							+ "As your mind well and truly gives in to the hypnotic suggestion, you find yourself wanting nothing more than to be fucked by [npc.name], and you eagerly [npc2.moansVerb],"
							+ " [npc2.speech(Yes... Yes! Please, fuck me! I <i>need</i> you to fuck me!)]"
						+ "</p>");
			} else {
				sb.append("<p>"
						+ "As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] starts to sound a lot more eager, and [npc2.moansVerb],"
						+ " [npc2.speech(Yes... Yes, [npc.name]! Please, fuck me! I <i>need</i> you to fuck me!)]"
					+ "</p>");
			}
			
			sb.append( "<p>"
						+ "[npc.speech(That's a good [npc2.girl],)] [npc.name] [npc.verb(say)], pleased to hear that [npc2.namePos] mind is [npc.hers] to twist as [npc.she] [npc.verb(see)] fit. [npc.speech(I'll give you what you want!)]"
					+ "</p>");
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterTargetedForSexAction(this).incrementLust(50, false);
		}
	};
	
	public static final SexAction GENERIC_DENY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Deny";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.name] to stay perfectly still, holding them in position until they've lost a good portion of their arousal.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.isDom(Sex.getCharacterPerformingAction()) && !Sex.isMasturbation()) {
				if(Sex.getCharacterPerformingAction().isPlayer()) {
					return true;
					
				} else {
					return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) // Doms will not deny other doms.
							&& Sex.getCharacterTargetedForSexAction(this).getArousal()>=50 // They will not deny if less than 50 arousal.
							&& Sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_DENIAL).isPositive()
							&& !Sex.getLastUsedSexAction(Sex.getCharacterPerformingAction()).equals(this); // Do not deny twice in a row
				}
			}
			return false;
		}

		@Override
		public String getDescription() {//TODO BDSM gear. penetrations.
			UtilText.nodeContentSB.setLength(0);
			
			boolean alreadyOrgasmed = Sex.getNumberOfOrgasms(Sex.getCharacterTargetedForSexAction(this))>0;
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"Taking a gentle, yet firm, grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] quite still, softly [npc.moaning] as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, ",
							"Firmly gripping [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] in place, letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to calm down, ",
							"Softly [npc.moaning] as [npc.she] [npc.verb(take)] a firm grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(tease)] [npc.her] partner as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, "));

					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"[npc.speech(That's a good [npc2.girl]... We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"just yet")+" now, would we?)]",
							"[npc.speech(Hush now, my good [npc2.girl], and take a moment to calm down. You didn't think you'd get to orgasm "+(alreadyOrgasmed?"again":"")+" so soon with me, did you?)]",
							"[npc.speech(Good [npc2.girl]... You just take a moment to calm down. We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"so soon")+" now, would we?)]"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"Taking a firm, grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] quite still, [npc.moaning+] as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, ",
								"Firmly gripping [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] in place, letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to calm down, ",
								"[npc.Moaning+] as [npc.she] [npc.verb(take)] a firm grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(tease)] [npc.her] partner as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, "));

						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(That's a good [npc2.girl]! We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"just yet")+" now, would we?!)]",
								"[npc.speech(Be a good [npc2.girl] now, and take a moment to calm down. You didn't think you'd get to orgasm "+(alreadyOrgasmed?"again":"")+" so soon with me, did you?!)]",
								"[npc.speech(Good [npc2.girl]! You just take a moment to calm down! We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"so soon")+" now, would we?!)]"));
						break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"Taking a rough, firm grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(force)] [npc2.herHim] to remain quite still, growling in a threatening manner as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, ",
								"Roughly gripping [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(force)] [npc2.herHim] to remain in place, letting out a threatening growl as [npc.she] [npc.verb(make)] [npc2.herHim] calm down, ",
								"Letting out a menacing growl as [npc.she] [npc.verb(take)] a firm grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(threaten)] [npc.her] partner as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, "));

						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(Stay still, you dumb [npc2.bitch]! You're not getting to climax "+(alreadyOrgasmed?"again already":"just yet")+" with me, understood?!)]",
								"[npc.speech(Stay still, [npc2.bitch], and calm down! You didn't think you'd get to orgasm "+(alreadyOrgasmed?"again":"")+" so soon with me, did you?!)]",
								"[npc.speech(That's it, [npc2.bitch]! You stay still and calm down! We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"so soon")+" now, would we?!)]"));
						break;
				default:
					break;
			}
			UtilText.nodeContentSB.append("<br/><br/>");
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"[npc2.speech(No, [npc.name]...)] [npc2.name] [npc2.verb(reply)], trying to contain the excitement in [npc2.her] voice, [npc2.speech(I-I'll try to endure...)]",
								"[npc2.speech(No, [npc.name],)] [npc2.name] [npc2.moansVerb], failing to subdue the intense arousal in [npc2.her] voice, [npc2.speech(I'll do my best to hold back...)]",
								"[npc2.speech(No...)] [npc2.name] [npc2.verb(answer)], before trying to stifle a desperate [npc2.moan], [npc2.speechNoEffects(~Mmm!~ I-I'll try to hold back!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
									"[npc2.speech(No, [npc.name]...)] [npc2.name] [npc2.verb(reply)], [npc2.speech(I-I'll try to endure...)]",
									"[npc2.speech(No, [npc.name],)] [npc2.name] [npc2.moansVerb], [npc2.speech(I'll do my best to hold back...)]",
									"[npc2.speech(No...)] [npc2.name] [npc2.verb(answer)], [npc2.speechNoEffects(I-I'll try to hold back!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
									"[npc2.speech(Just get away from me!)] [npc2.name] frantically [npc2.sobsVerb], [npc2.speech(I hate this!)]",
									"[npc2.speech(Let go of me!)] [npc2.name] desperately [npc2.sobsVerb], trying to pull [npc2.herself] out of [npc.namePos] grasp, [npc2.speech(Stop it! Get away from me!)]",
									"[npc2.speech(Why won't you just let me go?!)] [npc2.name] [npc2.sobsVerb] as [npc2.she] weakly [npc2.verb(try)] to pull away from [npc2.name], [npc2.speechNoEffects(I don't want this!)]"));
					break;
				default:
					break;
			}
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "Once it's clear that [npc2.nameHasFull] calmed down, [npc.name] [npc.verb(loosen)] [npc.her] grip, before continuing as [npc.she] [npc.was] before...");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			}
		}
	};
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop penetrations";
		}

		@Override
		public String getActionDescription() {
			return "Stop all ongoing penetrative actions that [npc2.name] is involved with.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isAnyOngoingActionHappening()
					&& !Sex.isMasturbation()
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			for(GameCharacter character : Sex.getAllParticipants()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					switch(ot) {
						case ANUS:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.asshole+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.asshole+]."));
							}
							break;
						case ASS:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] using [npc2.her] [npc2.ass+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] using [npc2.her] [npc2.ass+]."));
							}
							break;
						case BREAST:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] playing with [npc2.her] [npc2.breasts+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] playing with [npc2.her] [npc2.breasts+]."));
							}
							break;
						case BREAST_CROTCH:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] playing with [npc2.her] [npc2.crotchBoobs+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] playing with [npc2.her] [npc2.crotchBoobs+]."));
							}
							break;
						case MOUTH:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] mouth."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] mouth."));
							}
							break;
						case NIPPLE:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.nipple+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.nipple+]."));
							}
							break;
						case NIPPLE_CROTCH:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.crotchNipple+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.crotchNipple+]."));
							}
							break;
						case URETHRA_PENIS:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.penisUrethra+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.penisUrethra+]."));
							}
							break;
						case URETHRA_VAGINA:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.vaginaUrethra+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.vaginaUrethra+]."));
							}
							break;
						case VAGINA:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.pussy+]."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.pussy+]."));
							}
							break;
						case THIGHS:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Sex.getCharacterTargetedForSexAction(this), "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out from between [npc2.her] thighs."));
							}
							if (Sex.getCharacterContactingSexArea(character, ot).contains(Sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Sex.getCharacterTargetedForSexAction(this), character, "<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out from between [npc2.her] thighs."));
							}
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Sex.getAllParticipants()) {
				Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), character);
			}
		}
	};
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop penetrations (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop all ongoing penetrative actions that you are involved with.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isAnyOngoingActionHappening()
					&& Sex.getAllParticipants(false).size()>2
					&& !Sex.isMasturbation()
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			for(GameCharacter character : Sex.getAllParticipants()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					switch(ot) {
						case ANUS:
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.asshole+].");
							}
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.asshole+].");
							}
							break;
						case ASS:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop using [npc2.her] [npc2.ass+].");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.ass+].");
							}
							break;
						case BREAST:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop playing with [npc2.her] [npc2.breasts+].");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.breasts+].");
							}
							break;
						case BREAST_CROTCH:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop playing with [npc2.her] [npc2.crotchBoobs+].");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.crotchBoobs+].");
							}
							break;
						case MOUTH:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] mouth.");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you pull out of your mouth.");
							}
							break;
						case NIPPLE:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.nipple+].");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.nipple+].");
							}
							break;
						case NIPPLE_CROTCH:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.crotchNipple+].");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.crotchNipple+].");
							}
							break;
						case URETHRA_PENIS:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.penisUrethra+].");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.penisUrethra+].");
							}
							break;
						case URETHRA_VAGINA:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.vaginaUrethra+].");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.vaginaUrethra+].");
							}
							break;
						case VAGINA:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.pussy+].");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.pussy+].");
							}
							break;
						case THIGHS:
							if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out from between [npc2.her] thighs.");
							}
							if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop playing with your thighs.");
							}
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Sex.getAllParticipants()) {
				Sex.stopAllOngoingActions(Main.game.getPlayer(), character);
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Restrict self actions";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Sex.getSexControl(Sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)))
					&& !Sex.isMasturbation()
					&& Sex.isCharacterAllowedToUseSelfActions(Sex.getCharacterTargetedForSexAction(this))
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(Sex.getActivePartner())) {
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As you put an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc2.she] lets out a pathetic whine.");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] pouts at you as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop using [npc2.her] mouth.");
				}
			}
			
			if(UtilText.nodeContentSB.length()!=0) {
				UtilText.nodeContentSB.append("<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("[npc.speech(I don't want to see you trying to get yourself off,)] you [npc.moanVerb] at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will no longer use any self-penetrative actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
			
			Sex.setCharacterAllowedToUseSelfActions(Sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit self actions";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to perform all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Sex.getSexControl(Sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)))
					&& !Sex.isMasturbation()
					&& !Sex.isCharacterAllowedToUseSelfActions(Sex.getCharacterTargetedForSexAction(this))
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(You can touch yourself all you want,)] you [npc.moanVerb] at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to use any self-penetrative actions.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCharacterAllowedToUseSelfActions(Sex.getCharacterTargetedForSexAction(this), true);
		}
	};

	public static final SexAction PLAYER_FORBID_PARTNER_CONTROL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Restrict control";
		}

		@Override
		public String getActionDescription() {
			return "Restrict [npc2.namePos] level of control, preventing [npc2.herHim] from initiating any non-self penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& !Sex.isDom(target)
					&& !Sex.isMasturbation()
					&& Sex.getSexControl(target).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(You're not to do anything without being told,)] you order [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] now has a restricted level of control, and cannot initiate any non-self penetrative actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			Sex.setForcedSexControl(target, SexControl.ONGOING_ONLY);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CONTROL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Unrestrict control";
		}

		@Override
		public String getActionDescription() {
			return "Unrestrict [npc2.namePos] level of control, allowing [npc2.herHim] to initiate non-self penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& !Sex.isDom(target)
					&& !Sex.isMasturbation()
					&& Sex.getSexControl(target).getValue()<SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(You can do what you like,)] you say to [npc2.name], freeing [npc2.herHim] to do as [npc2.she] pleases.<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] now has an unrestricted level of control, and can initiate non-self penetrative actions at will.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			Sex.setForcedSexControl(target, SexControl.FULL);
			for(GameCharacter participant : Sex.getAllParticipants()) {
				if(!participant.equals(target) && Sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					((NPC)target).generateSexChoices(false, participant, null);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_POSITIONING_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Restrict positioning";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from using any positioning actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Sex.getSexControl(target)!=SexControl.FULL || !Sex.isDom(target))
					&& !Sex.isMasturbation()
					&& !Sex.isCharacterForbiddenByOthersFromPositioning(target)
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getInitialSexManager().isPositionChangingAllowed(target);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(I don't want to see you attempting to switch positions,)] you [npc.moanVerb] at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will no longer use any positioning actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
			
			Sex.addCharacterForbiddenByOthersFromPositioning(Sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_POSITIONING_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit positioning";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to use positioning actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Sex.getSexControl(target)!=SexControl.FULL || !Sex.isDom(target))
					&& !Sex.isMasturbation()
					&& Sex.isCharacterForbiddenByOthersFromPositioning(target)
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getInitialSexManager().isPositionChangingAllowed(target);
		}

		@Override
		public String getDescription() {
			return "[npc.speech(If you'd like, you can switch to whatever position you're most comfortable with,)] you [npc.moanVerb] at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to use positioning actions.</i>";
		}

		@Override
		public void applyEffects() {
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			Sex.removeCharacterForbiddenByOthersFromPositioning(Sex.getCharacterTargetedForSexAction(this));
			for(GameCharacter participant : Sex.getAllParticipants()) {
				if(!participant.equals(target) && Sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					((NPC)target).generateSexChoices(false, participant, null);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from managing any of your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Sex.getSexControl(Sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)))
					&& !Sex.isMasturbation()
					&& Sex.isCanRemoveOthersClothing(Sex.getActivePartner(), null)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(Don't you <i>dare</i> try and touch any of my clothes!)] you growl at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will not attempt to remove or displace any of your clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveOthersClothing(Sex.getActivePartner(), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to take off and displace your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Sex.getSexControl(Sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)))
					&& !Sex.isMasturbation()
					&& !Sex.isCanRemoveOthersClothing(Sex.getActivePartner(), null)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(How about you help me take off some of these clothes?)] you [npc.moan].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to manage your clothing.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveOthersClothing(Sex.getActivePartner(), true);
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from managing any of [npc2.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Sex.getSexControl(Sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)))
					&& !Sex.isMasturbation()
					&& Sex.isCanRemoveSelfClothing(Sex.getActivePartner())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(Don't you <i>dare</i> try and touch your clothes!)] you growl at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will not attempt to remove or displace any of [npc2.her] clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveSelfClothing(Sex.getActivePartner(), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to take off and displace [npc2.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Sex.getSexControl(Sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)))
					&& !Sex.isMasturbation()
					&& !Sex.isCanRemoveSelfClothing(Sex.getActivePartner())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(How about you start taking off some of your clothes?)] you [npc.moan].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to manage [npc2.her] clothing.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveSelfClothing(Sex.getActivePartner(), true);
		}
	};
	
	
	public static final SexAction PLAYER_STOP_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop partner";
		}

		@Override
		public String getActionDescription() {
			return "Stop [npc2.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isMasturbation()
					&& Sex.isCharacterSelfOngoingActionHappening(Sex.getActivePartner())
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(Sex.getActivePartner())) {
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As you put an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc2.she] lets out a pathetic whine.");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] pouts at you as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop using [npc2.her] mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PARTNER_STOP_PLAYER_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop player";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isCharacterSelfOngoingActionHappening(Sex.getCharacterTargetedForSexAction(this))
					&& !Sex.isAnyNonSelfOngoingActionHappening()
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc.Name] lets out an angry growl as [npc.she] forces [npc2.name] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As [npc.name] puts an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc.she] growls menacingly at [npc2.herHim].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] frowns at [npc2.name] as [npc.she] forces [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] makes a disapproving clicking noise with [npc.her] [npc.tongue] as [npc.she] forces [npc2.name] to stop using [npc2.her] mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	/**
	 * Special end action for submissive NPCs who end up resisting, and who also have the power to stop sex.
	 */
	public static final SexAction PARTNER_STOP_SEX_NOT_HAVING_FUN = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "End sex";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isPlayer()
					&& (!Sex.getCharacterPerformingAction().isSlave() || !Sex.getAllParticipants().contains(Sex.getCharacterPerformingAction().getOwner()))
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getSexPace(Sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
					&& !Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_SUB);
			
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With an annoyed sigh, [npc.name] disentangles [npc.herself] from [npc2.namePos] clutches,"
					+ " [npc.speechNoEffects(Eugh... I'm not really feeling this right now, ok?)]";
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isPartnerWantingToStopSex(Sex.getCharacterPerformingAction())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With a satisfied sigh, [npc.name] disentangles [npc.herself] from [npc2.namePos] clutches, before stating that [npc.sheHas] had enough for now.";
		}
		
		@Override
		public SexParticipantType getParticipantType() {
			return Sex.isMasturbation()?SexParticipantType.SELF:SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return Sex.isMasturbation()
					?"Stop masturbating"
					:"Stop sex";
		}

		@Override
		public String getActionDescription() {
			return Sex.isMasturbation()
					?"Put an end to your masturbation session."
					:"Stop having sex with [npc2.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getInitialSexManager().isPlayerAbleToStopSex()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return Sex.isMasturbation()
					?"Deciding that you've had enough, you put an end to your masturbation session."
					:"Deciding that you've had enough, you step back from [npc2.name].";
		}
		
		@Override
		public SexParticipantType getParticipantType() {
			return Sex.isMasturbation()?SexParticipantType.SELF:SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
