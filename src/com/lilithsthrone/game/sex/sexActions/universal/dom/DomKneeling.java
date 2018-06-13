package com.lilithsthrone.game.sex.sexActions.universal.dom;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.69
 * @version 0.1.79
 * @author Innoxia
 */
public class DomKneeling {
	
	public static final SexAction PARTNER_MOUND_SNOG = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Mound snog";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly snog [pc.name]'s genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name] eagerly presses [npc.her] [npc.lips+] against your doll-like mound, passionately snogging and licking at your sensitive crotch as you let out [pc.a_moan+].",
						
						"You let out [pc.a_moan+] as [npc.name] presses [npc.her] [npc.lips+] against your genderless crotch, before starting to passionately kiss and lick your doll-like mound.",
						
						"With a little shuffle forwards, [npc.name] eagerly presses [npc.her] mouth against your genderless crotch, snogging and licking your sensitive mound as you squeal and cry out in pleasure.",

						"Reaching around and grabbing your [pc.ass+], [npc.name] pulls you forwards, grinding [npc.her] [npc.lips+] against your genderless mound as [npc.she] enthusiastically snogs and licks your sensitive crotch.");
		}

	};
	
	public static final SexAction PARTNER_MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}

		@Override
		public String getActionDescription() {
			return "Kiss [pc.name]'s genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name] plants a series of delicate kisses on your doll-like mound, causing you to let out [pc.a_moan+] and buck your hips back against [npc.her] [npc.face].",
						
						"You let out a satisfied sigh as [npc.name] presses [npc.her] [npc.lips+] against your genderless crotch, before gently kissing and licking your doll-like mound.",
						
						"With delicate care, [npc.name] plants a series of gentle kisses on your genderless mound, causing you to let out [pc.a_moan+].",

						"[npc.Name] kisses and licks your genderless crotch, and you soon find yourself moaning and sighing at the feel of [npc.her] gentle touch.");
		}
		
	};
	
	// Special orgasms for this scene:
	
	public static final SexAction PLAYER_ORGASM_THIGH_SQUEEZE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.CATCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Thigh squeeze";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your thighs around [npc.name]'s head and collapse down onto [npc.her] face as you orgasm.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"You feel an overwhelming wave of burning arousal start to build up deep within your groin, and with a desperate squeal, you clamp your thighs down hard around [npc.name]'s head."
					+ " Your [pc.legs] start to shake and give out from under you, and with [pc.a_moan+], you start collapsing forwards."
					+ " [npc.Name] is quickly slammed to the floor, and you find yourself sitting on [npc.her] face,"
					+ " screaming in ecstasy as your [pc.pussy+] spasms and clenches down around the [npc.tongue+] that's being forced deep into your soft folds.");
			
			if(Main.game.getPlayer().hasPenis()) {
				UtilText.nodeContentSB.append("</br></br>"
						+ "As you grind your [pc.pussy+] down against [npc.her] [npc.lips], you feel your other sexual organ start to react to your climax.");
				
				if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
					UtilText.nodeContentSB.append(" You let out [pc.a_moan+] as you feel the knot at the base of your [pc.cock+] swelling up as you prepare to cum,");
					
				} else if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
					UtilText.nodeContentSB.append(" You let out [pc.a_moan+] as you feel the wide, flared head of your [pc.cock+] swelling up as you prepare to cum,");
					
				} else {
					UtilText.nodeContentSB.append(" You let out [pc.a_moan+] as you feel your [pc.cock+] twitch and throb as you prepare to cum,");
				}
		
				// Describe cum amount:
				UtilText.nodeContentSB.append(" and as your [pc.balls+] tense up");
				
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", a small trickle of [pc.cum+] squirts out onto the floor above [npc.name]'s head.");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", a small amount of [pc.cum+] squirts out onto the floor above [npc.name]'s head.");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", your [pc.cum+] squirts out onto the floor above [npc.name]'s head.");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", your [pc.cum+] shoots out onto the floor above [npc.name]'s head.");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", your [pc.cum+] pours out onto the floor above [npc.name]'s head.");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", your [pc.cum+] pours out onto the floor above [npc.name]'s head.");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", your [pc.cum+] pours out onto the floor above [npc.name]'s head.");
						break;
					default:
						break;
				}
			}
			
			UtilText.nodeContentSB.append("</br></br>"
					+ "After a few moments of grinding down on [npc.name]'s face, your overwhelming orgasm starts to fade, and you stand up on shaky [pc.legs],"
							+ " grinning down at your partner as you feel a slick stream of saliva and [pc.girlCum] drooling down from your [pc.pussy+].");
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	public static final SexAction PLAYER_ORGASM_COCK_DEEPTHROAT = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Push your cock deep down [npc.name]'s throat and fill [npc.herHim] with [pc.cum+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().getPenisRawSizeValue() >= PenisSize.TWO_AVERAGE.getMedianValue()
					&& Main.game.getPlayer().hasPenisIgnoreDildo();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You feel a desperate heat building in your groin, and with a lewd cry, you slam your [pc.cock+] deep down [npc.name]'s throat.");
			
			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" You force the knot at the base of your [pc.cock] past [npc.her] [npc.lips+], and [npc.she] shuffles around on [npc.her] knees as it quickly swells up, locking you both in place.");
				
			} else if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and you reach down to hold [npc.her] head in position as your wide, flared head swells up in [npc.her] throat.");
				
			} else if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and you start making little thrusting motions into [npc.her] [npc.face], raking your barbs up and down [npc.her] throat as you bring yourself to a climax.");
				
			} else {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and you reach down to hold [npc.her] head in position as you prepare for your climax.");
			}

			UtilText.nodeContentSB.append(" Your [pc.balls+] tense up, and as you let out [pc.a_moan+]");
			switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", you realise that you're not able to produce even one drop of cum.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", you feel a small trickle of [pc.cum+] squirting down [npc.name]'s throat into [npc.her] stomach.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", you feel a small amount of [pc.cum+] squirting down [npc.name]'s throat into [npc.her] stomach.");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", you feel your [pc.cum+] squirting down [npc.name]'s throat into [npc.her] stomach.");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", you feel your [pc.cum+] shooting down [npc.name]'s throat into [npc.her] stomach.");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring down [npc.name]'s throat into [npc.her] stomach.");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring down [npc.name]'s throat into [npc.her] stomach.");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring down [npc.name]'s throat into [npc.her] stomach.");
					break;
				default:
					break;
			}
			
			if(Main.game.getPlayer().hasVagina()){
				UtilText.nodeContentSB.append("</br></br>"
						+ "A desperate, shuddering heat suddenly crashes up from your neglected feminine sex, and you let out a manic scream as a blinding wave of pure ecstasy washes over you.");
			}
			
			UtilText.nodeContentSB.append("</br></br>"
					+ "After a few moments, your balls have completely emptied themselves, and you take a moment to catch your breath, grinning down at [npc.name] as you keep your [pc.cock+] hilted down [npc.her] throat.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<SexAreaOrifice> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.game.getPlayer()) && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
			} else {
				return null;
			}
		}
		
	};
	
	public static final SexAction PLAYER_ORGASM_COCK_FACIAL = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Orgasm facial";
		}

		@Override
		public String getActionDescription() {
			return "Pull your cock out from [npc.name]'s throat and give [npc.herHim] a facial.";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo();
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You feel a desperate heat building in your groin, and with [pc.a_moan+], you pull your [pc.cock] out from [npc.name]'s throat"
					+ (Main.game.getPlayer().isWearingCondom()?", sliding your condom off as you do so.":"."));
			
			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" The knot at the base of your [pc.cock+] quickly swells up as you rapidly stroke your shaft over [npc.name]'s [npc.face].");
				
			} else if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
				UtilText.nodeContentSB.append(" The wide, flared head of your [pc.cock+] quickly swells up as you rapidly stroke your shaft over [npc.name]'s [npc.face].");
				
			} else {
				UtilText.nodeContentSB.append(" Your [pc.cock+] starts twitching and throbbing as you rapidly stroke your shaft over [npc.name]'s [npc.face].");
			}

			UtilText.nodeContentSB.append(" You feel your [pc.balls+] tense up, and as you let out [pc.a_moan+]");
			switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", you realise that you're not able to produce even one drop of cum.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", you see a small trickle of [pc.cum+] squirting out all over [npc.name]'s [npc.face+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", you see a small amount of [pc.cum+] squirting out all over [npc.name]'s [npc.face+].");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", you see your [pc.cum+] squirting out all over [npc.name]'s [npc.face+].");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", you see your [pc.cum+] shooting out all over [npc.name]'s [npc.face+].");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", you see your [pc.cum+] pouring out all over [npc.name]'s [npc.face+].");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", you see your [pc.cum+] pouring out all over [npc.name]'s [npc.face+].");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", you see your [pc.cum+] pouring out all over [npc.name]'s [npc.face+].");
					break;
				default:
					break;
			}
			
			if(Main.game.getPlayer().hasVagina()){
				UtilText.nodeContentSB.append("</br></br>"
						+ "A desperate, shuddering heat suddenly crashes up from your neglected feminine sex, and you let out a manic scream as a blinding wave of pure ecstasy washes over you.");
			}
			
			UtilText.nodeContentSB.append("</br></br>"
					+ "After a moment, you start to come down from your climax, and push your [pc.cock+] past [npc.name]'s [npc.lips+] as you force [npc.herHim] to lick the [pc.cockHead] of your [pc.cock] clean.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.isPlayer() && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean ignoreCondom(GameCharacter condomWearer) {
			return condomWearer.equals(Main.game.getPlayer());
			
		}
	};
}
