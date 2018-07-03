package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.2.7
 * @author Innoxia
 */
public class GenericActions {
	
	public static final SexAction PLAYER_SELF_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
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
			return Main.game.getPlayer().getRace()==Race.DEMON
					&& !Main.game.getPlayer().hasPenis();
		}

		@Override
		public String getDescription() {
			return "Deciding to use your demonic powers to grow a nice thick cock, you grin at [npc.name] as you [pc.moanVerb],"
					+ " [pc.speech(You're going to love this!)]";
		}

		@Override
		public String applyEffectsString() {
			SexFlags.playerGrewDemonicCock = true;
			
			StringBuilder sb = new StringBuilder();
			sb.append(Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON));
			if(Main.game.getPlayer().getPenisRawCumStorageValue() < CumProduction.FIVE_HUGE.getMedianValue()) {
				sb.append(Main.game.getPlayer().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue()));
				Main.game.getPlayer().fillCumToMaxStorage();
			}
			if(Main.game.getPlayer().getPenisRawSizeValue() < PenisSize.FIVE_ENORMOUS.getMedianValue()) {
				sb.append(Main.game.getPlayer().setPenisSize(PenisSize.FIVE_ENORMOUS.getMedianValue()));
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Grow cock";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to grow a demonic cock.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isMasturbation()
					&& Sex.getActivePartner().getRace()==Race.DEMON
					&& !Sex.getActivePartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return "You decide that you'd like [npc.name] to use [npc.her] transformative powers to grow a nice thick demonic cock for you."
					+ " Grinning down at [npc.herHim], you tell [npc.herHim] as much, [pc.speech(Why don't you grow a nice big cock, so we can have even more fun!)]"
					+ "<br/><br/>"
					+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.PENIS)
						?"[npc.Name] lets out a little giggle, and as you look down at [npc.her] naked groin, you see a large bump start to form beneath [npc.her] [npc.skin]."
								+ " Before you have any time to change your mind, it quickly grows out into a fat demonic cock, and as you stare down at the little wriggling bumps that press out all along its shaft,"
								+ " a little spurt of precum shoots out onto your stomach."
						:"[npc.Name] lets out a little giggle, and as you look down at [npc.her] groin, you see a huge bulge quickly form beneath the fabric of [npc.her] "
								+Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"."
								+ " Before you have any time to change your mind, [npc.name] lets out [npc.a_moan+], and you realise that [npc.sheIs] now got a huge demonic cock hiding beneath [npc.her] clothing.");
		}

		@Override
		public void applyEffects() {
			Sex.getActivePartner().setPenisType(PenisType.DEMON_COMMON);
			Sex.getActivePartner().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
			Sex.getActivePartner().fillCumToMaxStorage();
			Sex.getActivePartner().setTesticleSize(0);
			Sex.getActivePartner().setPenisSize(PenisSize.FIVE_ENORMOUS.getMedianValue());
		}
	};

	
	public static final SexAction PLAYER_HYPNOTIC_SUGGESTION_LUST_DECREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Calming Suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[npc.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc.she] doesn't like having sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getActivePartner().getPsychoactiveFluidsIngested().isEmpty();
		}

		@Override
		public String getDescription() {
			return "Wanting to take advantage of the fact that [npc.name] is under the strong effect of a psychoactive substance, you lean towards [npc.herHim] and [pc.moan],"
					+ " [pc.speech(You aren't really interested in having sex with me, are you?)]"
				+ "</p>"
				+ "<p>"
					+ "[npc.Name]'s [npc.eyes] glaze over a little as [npc.she] answers,"
					+ " [npc.speech(Yes... I... I don't know why I'm having sex with you...)]"
				+ "</p>"
				+ "<p>"
					+ "Pushing a little further, you continue,"
					+ " [pc.speech(You'd rather I wasn't fucking you right now, isn't that right?)]"
				+ "</p>"
				+ "<p>"
					+ (Sex.isDom(Sex.getActivePartner())
							?"As the hypnotic suggestion sinks into [npc.name]'s head, [npc.she] starts to sound a lot calmer, and sighs,"
								+ " [npc.speech(This isn't really all that fun...)]"
							:"As the hypnotic suggestion sinks into [npc.name]'s head, [npc.she] starts to sound a lot more distressed, and cries out,"
								+ " [npc.speech(Wait, w-why is this happening?! Please, stop it! Get away from me!)]")
				+ "</p>";
		}

		@Override
		public void applyEffects() {
			Sex.getActivePartner().incrementLust(-50);
		}
	};
	
	public static final SexAction PLAYER_HYPNOTIC_SUGGESTION_LUST_INCREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Lustful Suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[npc.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc.she] loves to have sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getActivePartner().getPsychoactiveFluidsIngested().isEmpty();
		}

		@Override
		public String getDescription() {
				return "Wanting to take advantage of the fact that [npc.name] is under the strong effect of a psychoactive substance, you lean towards [npc.herHim] and [pc.moan],"
						+ " [pc.speech(You love having sex with me, don't you?)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.Name]'s [npc.eyes] glaze over a little as [npc.she] answers,"
						+ " [npc.speech(Yes... I... I love having sex with you...)]"
					+ "</p>"
					+ "<p>"
						+ "Pushing a little further, you continue,"
						+ " [pc.speech(You love begging for me to fuck you, isn't that right?)]"
					+ "</p>"
					+ "<p>"
						+ "As the hypnotic suggestion sinks into [npc.name]'s head, [npc.she] starts to sound a lot more eager, and [npc.moansVerb],"
						+ " [npc.speech(Yes [pc.name]! I love it! Please, fuck me! I <i>need</i> you to fuck me!)]"
					+ "</p>";
		}

		@Override
		public void applyEffects() {
			Sex.getActivePartner().incrementLust(50);
		}
	};
	
	
	
	public static final SexAction PLAYER_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public ArousalIncrease getArousalGainSelf() {
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.ZERO_NONE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist";
		}

		@Override
		public String getActionDescription() {
			return "Resist having sex with [npc.name].";
		}
		
		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc.name] as you desperately try to struggle out of [npc.her] grip, but your efforts prove to be in vain as [npc.she] easily keeps you pinned back against the wall.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out of [npc.her] grasp, but [npc.her] grip is too strong for you, and [npc.she] easily keeps you pushed you back against the wall.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pushes you back against the wall.");
				
			} else if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"You let out [pc.a_sob+] as you try to crawl away from [npc.name], but your efforts prove to be in vain as [npc.she] grabs your [pc.hips] and pulls your [pc.ass] back into [npc.her] groin.",
						"Trying to crawl away from [npc.name] on all fours, you let out [pc.a_sob+] as you feel [npc.herHim] grasp your [pc.hips], before pulling you back into position.",
						"Begging for [npc.herHim] to leave you alone, you desperately try to crawl away from [npc.name], [pc.sobbing] in distress as [npc.she] takes hold of your [pc.hips] and pulls you back into [npc.herHim].");
				
			} else if(Sex.getPosition()==SexPositionType.FACING_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.FACE_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc.name] as you desperately try to struggle out of [npc.her] grip, but your efforts prove to be in vain as [npc.she] easily keeps you pinned up against the wall.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out of [npc.her] grasp, but [npc.her] grip is too strong for you, and [npc.she] easily keeps you pushed up against the wall.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pushes you up against the wall.");
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"You try to push [npc.name]'s groin away from your [pc.face], but your efforts prove to be in vain as [npc.she] grabs hold of your head and pulls you back into [npc.her] crotch.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to pull your [pc.face] away from [npc.her] groin, but [npc.her] grasp on your head is too strong, and you're quickly forced back into position.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] pulls your [pc.face] back into [npc.her] groin.");
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				return UtilText.returnStringAtRandom(
						"You try to push [npc.name] off of you as you desperately try to wriggle out from under [npc.herHim], but your efforts prove to be in vain as [npc.she] easily pins you to the floor.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out from under [npc.herHim], but [npc.she] presses [npc.her] body down onto yours, preventing you from getting away.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as [npc.she] uses [npc.her] body to pin you to the floor.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc.name] as you desperately try to struggle out of [npc.her] grip, but your efforts prove to be in vain as [npc.she] easily continues restraining you.",
						"Struggling against [npc.name], you let out [pc.a_sob+] as you weakly try to wriggle out of [npc.her] grasp.",
						"Begging for [npc.herHim] to leave you alone, you desperately struggle against [npc.name], [pc.sobbing] in distress as you realise that [npc.her] grip is too strong.");
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
			}
		}
	};
	
	public static final SexAction DENIAL_FETISH_DENY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Deny";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name] to stay perfectly still, holding them in position until they've lost a good portion of their arousal.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) && !Sex.isMasturbation();
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Sex.getActivePartner(),
					"Taking control of the situation, you hold [npc.name] quite still, only releasing [npc.herHim] once [npc.sheIs] lost a good portion of [npc.her] arousal.");
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop penetrations";
		}

		@Override
		public String getActionDescription() {
			return "Stop all ongoing penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isAnyOngoingActionHappening()
					&& (Sex.isDom(Main.game.getPlayer())?true:Sex.isSubHasEqualControl());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			for(SexAreaOrifice ot : SexAreaOrifice.values()) {
				switch(ot) {
					case ANUS:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.asshole+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.asshole+].");
						}
						break;
					case ASS:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you stop using [npc.her] [npc.ass+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.ass+].");
						}
						break;
					case BREAST:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you stop playing with [npc.her] [npc.breasts+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.breasts+].");
						}
						break;
					case MOUTH:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] mouth.");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you pull out of your mouth.");
						}
						break;
					case NIPPLE:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.nipple+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.nipple+].");
						}
						break;
					case URETHRA_PENIS:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.penisUrethra+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.vaginaUrethra+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you pull out of [npc.her] [npc.pussy+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop stimulating your [pc.pussy+].");
						}
						break;
					case THIGHS:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.Name] lets out [npc.a_moan+] as you pull out from between [npc.her] thighs.");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[pc.A_moan+] drifts out from between your [pc.lips+] as you stop playing with your thighs.");
						}
						break;
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Forbid self actions";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl())
					&& Sex.isPartnerAllowedToUseSelfActions();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(Sex.getActivePartner())) {
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [pc.moan] as you force [npc.herHim] to stop stimulating [npc.her] [npc.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As you put an end to [npc.name]'s self-stimulation of [npc.her] [npc.asshole], [npc.she] lets out a pathetic whine.");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] pouts at you as you force [npc.herHim] to stop stimulating [npc.her] [npc.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [pc.moan] as you force [npc.herHim] to stop using [npc.her] mouth.");
				}
			}
			
			if(UtilText.nodeContentSB.length()!=0)
				UtilText.nodeContentSB.append("<br/><br/>");
			UtilText.nodeContentSB.append("[pc.speech(I don't want to see you trying to get yourself off, understood?)] you growl at [npc.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] will no longer use any self-penetrative actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
			
			Sex.setPartnerAllowedToUseSelfActions(false);
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Permit self actions";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc.name] to perform all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl())
					&& !Sex.isPartnerAllowedToUseSelfActions();
		}

		@Override
		public String getDescription() {
			return "[pc.speech(I want to see you trying to get yourself off!)] you growl at [npc.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] is now able to use any self-penetrative actions.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setPartnerAllowedToUseSelfActions(true);
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Forbid clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc.name] from managing any of your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl()) && Sex.isCanRemoveOthersClothing(Sex.getActivePartner());
		}

		@Override
		public String getDescription() {
			return "[pc.speech(Don't you <i>dare</i> try and touch any of my clothes!)] you growl at [npc.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] will not attempt to remove or displace any of your clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveOthersClothing(Sex.getActivePartner(), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING_REMOVAL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Permit clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc.name] to take off and displace your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl()) && !Sex.isCanRemoveOthersClothing(Sex.getActivePartner());
		}

		@Override
		public String getDescription() {
			return "[pc.speech(How about you help me take off some of these clothes?)] you [pc.moan].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] is now able to manage your clothing.</i>";
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Forbid self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc.name] from managing any of [npc.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl()) && Sex.isCanRemoveSelfClothing(Sex.getActivePartner());
		}

		@Override
		public String getDescription() {
			return "[pc.speech(Don't you <i>dare</i> try and touch your clothes!)] you growl at [npc.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] will not attempt to remove or displace any of [npc.her] clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveSelfClothing(Sex.getActivePartner(), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING_SELF_REMOVAL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Permit self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc.name] to take off and displace [npc.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl()) && !Sex.isCanRemoveSelfClothing(Sex.getActivePartner());
		}

		@Override
		public String getDescription() {
			return "[pc.speech(How about you start taking off some of your clothes?)] you [pc.moan].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc.Name] is now able to manage [npc.her] clothing.</i>";
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop partner";
		}

		@Override
		public String getActionDescription() {
			return "Stop [npc.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isCharacterSelfOngoingActionHappening(Sex.getActivePartner())
					&& (Sex.isDom(Main.game.getPlayer())?true:Sex.isSubHasEqualControl());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(Sex.getActivePartner())) {
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [pc.moan] as you force [npc.herHim] to stop stimulating [npc.her] [npc.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As you put an end to [npc.name]'s self-stimulation of [npc.her] [npc.asshole], [npc.she] lets out a pathetic whine.");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] pouts at you as you force [npc.herHim] to stop stimulating [npc.her] [npc.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] lets out a disappointed [pc.moan] as you force [npc.herHim] to stop using [npc.her] mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	// Partner:
	
	public static final SexAction PARTNER_HYPNOTIC_SUGGESTION_LUST_DECREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Calming Suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[pc.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [pc.she] doesn't like having sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getActivePartner())
					&& !Main.game.getPlayer().getPsychoactiveFluidsIngested().isEmpty()
					&& Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_DOM)
					&& Main.game.getPlayer().getLust()>LustLevel.ONE_HORNY.getMaximumValue();
		}

		@Override
		public String getDescription() {
			return "Wanting to take advantage of the fact that you're under the strong effect of a psychoactive substance, [npc.name] leans towards you and [npc.moansVerb],"
					+ " [npc.speech(You aren't really interested in having sex with me, are you?)]"
				+ "</p>"
				+ "<p>"
					+ "You can't help but agree with what [npc.sheIs] saying, and you haltingly answer,"
					+ " [pc.speech(Yes... I... I don't know why I'm having sex with you...)]"
				+ "</p>"
				+ "<p>"
					+ "Pushing a little further, and driven on by [npc.her] fetish for having non-consensual sex, [npc.name] continues,"
					+ " [pc.speech(You'd rather I wasn't fucking you right now, isn't that right?)]"
				+ "</p>"
				+ "<p>"
					+ "As the hypnotic suggestion sinks into your head, you start to feel a lot more distressed, and cry out,"
						+ " [pc.speech(Wait, w-why is this happening?! Please, stop it! Get away from me!)]"
				+ "</p>";
		}

		@Override
		public void applyEffects() {
			Main.game.getPlayer().incrementLust(-50);
		}
	};
	
	public static final SexAction PARTNER_HYPNOTIC_SUGGESTION_LUST_INCREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Lustful Suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[pc.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [pc.she] loves to have sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getActivePartner())
					&& !Main.game.getPlayer().getPsychoactiveFluidsIngested().isEmpty()
					&& !Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_DOM)
					&& Main.game.getPlayer().getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue();
		}

		@Override
		public String getDescription() {
				return "Wanting to take advantage of the fact that you're under the strong effect of a psychoactive substance, [npc.name] leans towards you and [npc.moansVerb],"
						+ " [npc.speech(You love having sex with me, don't you?)]"
					+ "</p>"
					+ "<p>"
						+ "You can't help but agree with what [npc.sheIs] saying, and you haltingly answer,"
						+ " [pc.speech(Yes... I... I love having sex with you...)]"
					+ "</p>"
					+ "<p>"
						+ "Pushing a little further, [npc.name] continues,"
						+ " [npc.speech(You love begging for me to fuck you, isn't that right?)]"
					+ "</p>"
					+ "<p>"
						+ "As the hypnotic suggestion sinks into your head, you can't help but feel a lot more eager, and [pc.moan],"
						+ " [pc.speech(Yes! I love it! Please, fuck me! I <i>need</i> you to fuck me!)]"
					+ "</p>";
		}

		@Override
		public void applyEffects() {
			Main.game.getPlayer().incrementLust(50);
		}
	};
	
	
	public static final SexAction PARTNER_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public ArousalIncrease getArousalGainSelf() {
			if(Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.ZERO_NONE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) && !Sex.isConsensual();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist";
		}

		@Override
		public String getActionDescription() {
			return "Resist having sex with [pc.name].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks you as [npc.she] desperately tries to struggle out of your grip, but [npc.her] efforts prove to be in vain as you easily keep [npc.herHim] pinned back against the wall.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of your grasp, but your grip is too strong for [npc.herHim], and you easily keep [npc.herHim] pushed back against the wall.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you push [npc.herHim] back against the wall.");
				
			} else if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] lets out [npc.a_sob+] as [npc.she] tries to crawl away from you, but [npc.her] efforts prove to be in vain as you grab [npc.her] [npc.hips] and pull [npc.her] [npc.ass] back into your groin.",
						"Trying to crawl away from you on all fours, [npc.name] lets out [npc.a_sob+] as you grasp [npc.her] [npc.hips], before pulling [npc.herHim] back into position.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately tries to crawl away from you, [npc.sobbing] in distress as you take hold of [npc.her] [npc.hips] and pull [npc.herHim] back into you.");
				
			} else if(Sex.getPosition()==SexPositionType.FACING_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks you as [npc.she] desperately tries to struggle out of your grip, but [npc.her] efforts prove to be in vain as you easily keep [npc.herHim] pinned up against the wall.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of your grasp, but your grip is too strong for [npc.herHim], and you easily keep [npc.herHim] pushed up against the wall.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you push [npc.herHim] up against the wall.");
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] tries to push your groin away from [npc.her] [npc.face], but [npc.her] efforts prove to be in vain as you grab hold of [npc.her] head and pull [npc.herHim] back into your crotch.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to pull [npc.her] [npc.face] away from your groin,"
								+ " but your grasp on [npc.her] head is too strong, and you quickly force [npc.herHim] back into position.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you pull [npc.her] [npc.face] back into your groin.");
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] tries to push you off of [npc.herHim] as [npc.she] desperately tries to wriggle out from under you, but [npc.her] efforts prove to be in vain as you easily pin [npc.herHim] to the floor.",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out from under you, but you press your body down onto [npc.hers], preventing [npc.herHim] from getting away.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you use your body to pin [npc.herHim] to the floor.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks you as [npc.she] desperately tries to struggle out of your grip, but [npc.her] efforts prove to be in vain as you easily continue restraining [npc.herHim].",
						"Struggling against you, [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of your grasp.",
						"Begging for you to leave [npc.herHim] alone, [npc.name] desperately struggles against you, [npc.sobbing] in distress as you easily hold [npc.herHim] in place.");
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			}
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
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
			return Sex.isCharacterSelfOngoingActionHappening(Main.game.getPlayer())
					&& !Sex.isAnyNonSelfOngoingActionHappening()
					&& (Sex.isDom(Main.game.getPlayer())?Sex.isSubHasEqualControl():true);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(Main.game.getPlayer())) {
					UtilText.nodeContentSB.append("[npc.Name] lets out an angry growl as [npc.she] forces you to stop stimulating your [pc.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(Main.game.getPlayer())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As [npc.name] puts an end to your self-stimulation of your [pc.asshole], [npc.she] growls menacingly at you.");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.NIPPLE).contains(Main.game.getPlayer())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] frowns at you as [npc.she] forces you to stop stimulating your [pc.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Main.game.getPlayer(), SexAreaOrifice.MOUTH).contains(Main.game.getPlayer())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] makes a disapproving clicking noise with [npc.her] [npc.tongue] as [npc.she] forces you to stop using your mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Main.game.getPlayer(), Main.game.getPlayer());
		}
	};
	
	public static final SexAction PARTNER_BLOCKS_REQUESTS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Respond";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPlayerPenetrationRequests().isEmpty()
					&& Sex.getActivePartner().getSexBehaviourDeniesRequests()
					&& (!Sex.isDom(Main.game.getPlayer()) && !Sex.isSubHasEqualControl());
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "[npc.Name] frowns as you try to tell [npc.herHim] how to use you, and with a menacing tone in [npc.her] voice, [npc.she] growls at you, "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Don't try and tell me what to do!)]",
									"[npc.speech(I'll use whatever hole I feel like!)]");
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			SexFlags.requestsBlockedPlayer=true;
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			}
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX_NOT_HAVING_FUN = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			if(!Sex.isDom(Sex.getActivePartner())) {
				return Sex.getSexPace(Sex.getActivePartner()) == SexPace.SUB_RESISTING && !Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_SUB) && Sex.isSubHasEqualControl();
			} else {
				return false;// doms will never end it
			}
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With an annoyed sigh, [npc.name] disentangles [npc.herself] from your clutches,"
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return Sex.getSexManager().isPartnerWantingToStopSex();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With a satisfied sigh, [npc.name] disentangles [npc.herself] from your clutches, before stating that [npc.sheIs] had enough for now.";
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public String getActionTitle() {
			return Sex.isMasturbation()?"Stop masturbating":"Stop sex";
		}

		@Override
		public String getActionDescription() {
			return Sex.isMasturbation()?"Put an end to your masturbation session.":"Stop having sex with [npc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isPlayerAbleToStopSex();
		}

		@Override
		public String getDescription() {
			return Sex.isMasturbation()?"Deciding that you've had enough, you put an end to your masturbation session.":"Deciding that you've had enough, you step back from [npc.name].";
		}
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
