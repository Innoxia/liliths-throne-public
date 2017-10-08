package com.lilithsthrone.game.sex.sexActions.universal.sub;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.69
 * @version 0.1.79
 * @author Innoxia
 */
public class SubKneeling {
	
	public static final SexAction PLAYER_MOUND_SNOG = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Snog mound";
		}

		@Override
		public String getActionDescription() {
			return "Passionately snog [npc.name]'s genderless crotch.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPartner().hasVagina() && !Sex.getPartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You eagerly press your [pc.lips+] against [npc.name]'s doll-like mound, passionately snogging and licking at [npc.her] sensitive crotch as [npc.she] lets out [npc.a_moan+].",
					
					"[npc.Name] lets out [npc.a_moan+] as you press your [pc.lips+] against [npc.her] genderless crotch, before starting to passionately kiss and lick [npc.her] doll-like mound.",
					
					"With a little shuffle forwards, you  eagerly press your mouth against [npc.name]'s genderless crotch, snogging and licking [npc.her] sensitive mound as [npc.she] squeals and cries out in pleasure.",

					"Reaching around and grabbing [npc.name]'s [npc.ass+], you pull [npc.herHim] forwards, grinding your [pc.lips+] against [npc.her] genderless mound as you enthusiastically snog and lick [npc.her] sensitive crotch.");
		}
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PLAYER_MOUND_KISSING = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}

		@Override
		public String getActionDescription() {
			return "Gently kiss and lick [npc.name]'s doll-like crotch.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPartner().hasVagina() && !Sex.getPartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You plant a series of delicate kisses on [npc.name]'s doll-like mound, causing [npc.herHim] to let out [npc.a_moan+] and buck [npc.her] [npc.hips] back against your [pc.face].",
					
					"[npc.Name] lets out [npc.a_moan+] as you press your [pc.lips+] against [npc.her] genderless crotch, before gently kissing and licking [npc.her] doll-like mound.",
					
					"With delicate care, you plant a series of gentle kisses on [npc.name]'s genderless mound, causing [npc.herHim] to let out [npc.a_moan+].",

					"You kiss and lick [npc.name]'s genderless crotch, causing [npc.herHim] to start moaning and sighing at the feel of your gentle touch.");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	// Special orgasms for this scene:
	
	public static final SexAction PARTNER_ORGASM_THIGH_SQUEEZE = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Thigh squeeze";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your thighs around [pc.name]'s head and collapse down onto [pc.her] face as you orgasm.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"With a desperate squeal, [npc.name] suddenly clamps [npc.her] thighs down hard around your head."
					+ " [npc.Her] [npc.legs] start to shake and give out from under [npc.herHim], and with [npc.a_moan+], [npc.she] starts collapsing forwards."
					+ " You're quickly slammed to the floor, and you find yourself looking up at [npc.name]'s [npc.breasts+] as [npc.she] sits on your face,"
					+ " screaming in ecstasy as [npc.her] [npc.pussy+] spasms and clenches down around your [pc.tongue+].");
			
			if(Sex.getPartner().hasPenis()) {
				UtilText.nodeContentSB.append("</br></br>"
						+ "As [npc.she] grinds [npc.her] [npc.pussy+] down against your [pc.lips], [npc.her] other sexual organ starts to react to [npc.her] climax.");
				
				if(Sex.getPartner().hasPenisModifier(PenisModifier.KNOTTED)) {
					UtilText.nodeContentSB.append(" [npc.She] lets out [npc.a_moan+] as the knot at the base of [npc.her] [npc.cock+] swells up as [npc.she] prepares to cum,");
					
				} else if(Sex.getPartner().hasPenisModifier(PenisModifier.FLARED)) {
					UtilText.nodeContentSB.append(" [npc.She] lets out [npc.a_moan+] as the wide, flared head of [npc.her] [pc.cock+] swells up as [npc.she] prepares to cum,");
					
				} else {
					UtilText.nodeContentSB.append(" [npc.She] lets out [npc.a_moan+] as [npc.her] [npc.cock+] twitches and throbs as [npc.she] prepare to cum,");
				}
		
				// Describe cum amount:
				UtilText.nodeContentSB.append(" and as [npc.her] [npc.balls+] tense up");
				
				switch (Main.game.getPlayer().getPenisCumProduction()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you see that [npc.she]'s not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", a small trickle of [npc.cum+] squirts out onto the floor above your head.");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", a small amount of [npc.cum+] squirts out onto the floor above your head.");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] squirts out onto the floor above your head.");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] shoots out onto the floor above your head.");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above your head.");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above your head.");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out onto the floor above your head.");
						break;
					default:
						break;
				}
			}
			
			UtilText.nodeContentSB.append("</br></br>"
					+ "After a few moments of grinding down on your face, [npc.her] overwhelming orgasm starts to fade, and [npc.name] stands up on shaky [npc.legs],"
							+ " grinning down at you as a slick stream of saliva and [npc.girlCum] drools down from [npc.her] [npc.pussy+].");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Sex.removePenetration(PenetrationType.TONGUE_PLAYER, OrificeType.VAGINA_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_ORGASM_CUM_DOWN_THROAT = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().getPenisRawSizeValue() >= PenisSize.TWO_AVERAGE.getMedianValue();
		}
		
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Push your cock deep down [pc.name]'s throat and fill [pc.herHim] with [npc.cum+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You feel [npc.name]'s [npc.cock] start to twitch and throb in your mouth, and before you can react, [npc.she] grabs the sides of your head and slams [npc.her] entire length fully down your throat.");
			
			if(Sex.getPartner().hasPenisModifier(PenisModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" Forcing the knot at the base of [npc.her] [npc.cock] past your [pc.lips+], you shuffle around on your knees as it quickly swells up, locking you both in place");
				
			} else if(Sex.getPartner().hasPenisModifier(PenisModifier.FLARED)) {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and [npc.she] reaches down to hold your head in position as [npc.her] wide, flared head swells up in your throat.");
				
			} else if(Sex.getPartner().hasPenisModifier(PenisModifier.BARBED)) {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and [npc.she] starts making little thrusting motions into your [pc.face],"
						+ " raking [npc.her] barbs up and down your throat as [npc.she] brings [npc.herself] to a climax.");
				
			} else {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and [npc.she] reaches down to hold your head in position as [npc.she] prepares for [npc.her] climax.");
			}

			UtilText.nodeContentSB.append(" [npc.Her] [npc.balls+] tense up, and as [npc.she] lets out [npc.a_moan+]");
			if(Sex.getPartner().isWearingCondom()) {
				UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out into the condom that [npc.she]'s wearing.");
			} else {
				switch (Sex.getPartner().getPenisCumProduction()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you realise that [npc.she]'s not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", you feel a small trickle of [npc.cum+] squirting down your throat into your stomach.");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", you feel a small amount of [npc.cum+] squirting down your throat into your stomach.");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", you feel [npc.her] [npc.cum+] squirting down your throat into your stomach.");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", you feel [npc.her] [npc.cum+] shooting down your throat into your stomach.");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", you feel [npc.her] [npc.cum+] pouring down your throat into your stomach.");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", you feel [npc.her] [npc.cum+] pouring down your throat into your stomach.");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", you feel [npc.her] [npc.cum+] pouring down your throat into your stomach.");
						break;
					default:
						break;
				}
			}
			
			if(Sex.getPartner().hasVagina()) {
				UtilText.nodeContentSB.append("</br></br>"
						+ "Letting out a desperate moan, [npc.name]'s climax drops down into [npc.her] [npc.pussy+],"
							+ " and [npc.she] roughly grinds the base of [npc.her] [npc.cock+] down against your lips as [npc.she] shudders and moans in pleasure.");
			}
			
			UtilText.nodeContentSB.append("</br></br>"
						+ "After a few moments, [npc.her] balls have completely emptied themselves, and [npc.she] steps back,"
							+ " sliding [npc.her] rapidly-softening [npc.cock] out from your [pc.lips+] as [npc.she] inadvertently covers your [pc.tongue] with the last few drops of [npc.her] salty seed.");
			
			return UtilText.genderParsing(Sex.getPartner(),
					UtilText.nodeContentSB.toString());
		}

		@Override
		public void applyEffects() {
			Sex.removePenetration(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER);
		}

		@Override
		public List<OrificeType> getPlayerAreasCummedIn() {
			return Util.newArrayListOfValues(new ListValue<>(OrificeType.MOUTH_PLAYER));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static final SexAction PARTNER_ORGASM_FACIAL = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You feel [npc.name]'s [npc.cock] start to twitch and throb in your mouth, and before you can react, [npc.she] suddenly slides [npc.her] shaft up out of your throat"
					+ (Sex.getPartner().isWearingCondom()?", sliding [npc.her] condom off as [npc.she] does so.":"."));
			
			if(Sex.getPartner().hasPenisModifier(PenisModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" Reaching down to start masturbating over your face, [npc.she] uses your slimy saliva as lubrication as [npc.she] squeezes and fondles [npc.her] thick swollen knot.");
				
			} else if(Sex.getPartner().hasPenisModifier(PenisModifier.FLARED)) {
				UtilText.nodeContentSB.append(" Looking down at you, [npc.she] quickly starts masturbating, using your slimy saliva as lubrication as [npc.she] rubs [npc.her] thumb over the top of [npc.her] wide, flared head.");
				
			} else if(Sex.getPartner().hasPenisModifier(PenisModifier.BARBED)) {
				UtilText.nodeContentSB.append(" You let out a desperate wail as [npc.her] barbs rake their way up your throat, but [npc.she] seems oblivious to your discomfort as [npc.she] quickly starts masturbating,"
							+ " using your slimy saliva as lubrication as [npc.she] rapidly brings [npc.herself] to a climax.");
				
			} else {
				UtilText.nodeContentSB.append(" Looking down at you, [npc.she] quickly starts masturbating, using your slimy saliva as lubrication as [npc.she] runs [npc.her] hand up and down the length of [npc.her] shaft.");
			}

			UtilText.nodeContentSB.append(" [npc.Her] [npc.balls+] tense up, and as [npc.she] lets out [npc.a_moan+]");
			switch (Sex.getPartner().getPenisCumProduction()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", you see that [npc.she]'s not able to produce even one drop of cum.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", a small trickle of [npc.cum+] squirts out all over your [pc.face+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", a small amount of [pc.cum+] squirts out all over your [pc.face+].");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", [npc.her] [pc.cum+] squirts out all over your [pc.face+].");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", [npc.her] [pc.cum+] shoots out all over your [pc.face+].");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", [npc.her] [pc.cum+] pours out all over your [pc.face+].");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", [npc.her] [pc.cum+] pours out all over your [pc.face+].");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", [npc.her] [pc.cum+] pours out all over your [pc.face+].");
					break;
				default:
					break;
			}
			
			if(Sex.getPartner().getPenisCumProduction()!=CumProduction.ZERO_NONE) {
				UtilText.nodeContentSB.append("  You flinch as you receive your facial, noticing that quite a few strands of [npc.cum] fall down to land on your "
						+(Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES) != null ? Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName() : "exposed [pc.breasts]")+".");
			}
			
			if(Sex.getPartner().getVaginaType()!=VaginaType.NONE) {
				UtilText.nodeContentSB.append("</br></br>"
						+ "Letting out [npc.a_moan+], [npc.name]'s climax drops down into [npc.her] [npc.pussy+], and [npc.she] roughly grinds [npc.her] feminine sex down against your forehead,"
							+ " shuddering and moaning in pleasure as [npc.her] [npc.cock+] flops down on the top of your head.");
			}
			
			UtilText.nodeContentSB.append("</br></br>"
						+ " After a few moments, [npc.she] comes down from [npc.her] climax and steps back, smirking down at you as you wipe [npc.her] [npc.cum+] from your eyes.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(Sex.getPartner().getPenisCumProduction()!=CumProduction.ZERO_NONE) {
				if (Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES) != null) {
					Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(true);
				}
			}
			Sex.removePenetration(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}

		@Override
		public boolean ignorePartnerCondom() {
			return true;
		}
	};
}
