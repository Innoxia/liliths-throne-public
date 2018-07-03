package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class KneelingOral {

	public static final SexAction PLAYER_ORGASM_THIGH_SQUEEZE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
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
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you grind your [pc.pussy+] down against [npc.her] [npc.lips], you feel your other sexual organ start to react to your climax.");
				
				if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					UtilText.nodeContentSB.append(" You let out [pc.a_moan+] as you feel the knot at the base of your [pc.cock+] swelling up as you prepare to cum,");
					
				} else if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.FLARED)) {
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
			
			UtilText.nodeContentSB.append("<br/><br/>"
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
			SexParticipantType.NORMAL) {
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
			
			if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" You force the knot at the base of your [pc.cock] past [npc.her] [npc.lips+], and [npc.she] shuffles around on [npc.her] knees as it quickly swells up, locking you both in place.");
				
			} else if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.FLARED)) {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and you reach down to hold [npc.her] head in position as your wide, flared head swells up in [npc.her] throat.");
				
			} else if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.BARBED)) {
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
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "A desperate, shuddering heat suddenly crashes up from your neglected feminine sex, and you let out a manic scream as a blinding wave of pure ecstasy washes over you.");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
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
			SexParticipantType.NORMAL) {
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
			
			if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" The knot at the base of your [pc.cock+] quickly swells up as you rapidly stroke your shaft over [npc.name]'s [npc.face].");
				
			} else if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.FLARED)) {
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
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "A desperate, shuddering heat suddenly crashes up from your neglected feminine sex, and you let out a manic scream as a blinding wave of pure ecstasy washes over you.");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
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
	

	public static final SexAction PARTNER_ORGASM_THIGH_SQUEEZE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			
			if(Sex.getActivePartner().hasPenis()) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As [npc.she] grinds [npc.her] [npc.pussy+] down against your [pc.lips], [npc.her] other sexual organ starts to react to [npc.her] climax.");
				
				if(Sex.getActivePartner().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					UtilText.nodeContentSB.append(" [npc.She] lets out [npc.a_moan+] as the knot at the base of [npc.her] [npc.cock+] swells up as [npc.she] prepares to cum,");
					
				} else if(Sex.getActivePartner().hasPenisModifier(PenetrationModifier.FLARED)) {
					UtilText.nodeContentSB.append(" [npc.She] lets out [npc.a_moan+] as the wide, flared head of [npc.her] [pc.cock+] swells up as [npc.she] prepares to cum,");
					
				} else {
					UtilText.nodeContentSB.append(" [npc.She] lets out [npc.a_moan+] as [npc.her] [npc.cock+] twitches and throbs as [npc.she] prepare to cum,");
				}
		
				// Describe cum amount:
				UtilText.nodeContentSB.append(" and as [npc.her] [npc.balls+] tense up");
				
				switch (Sex.getActivePartner().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you see that [npc.sheIs] not able to produce even one drop of cum.");
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
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "After a few moments of grinding down on your face, [npc.her] overwhelming orgasm starts to fade, and [npc.name] stands up on shaky [npc.legs],"
							+ " grinning down at you as a slick stream of saliva and [npc.girlCum] drools down from [npc.her] [npc.pussy+].");
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_ORGASM_CUM_DOWN_THROAT = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getActivePartner().getPenisRawSizeValue() >= PenisSize.TWO_AVERAGE.getMedianValue()
					&& Sex.getActivePartner().hasPenisIgnoreDildo();
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
			
			if(Sex.getActivePartner().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" Forcing the knot at the base of [npc.her] [npc.cock] past your [pc.lips+], you shuffle around on your knees as it quickly swells up, locking you both in place.");
				
			} else if(Sex.getActivePartner().hasPenisModifier(PenetrationModifier.FLARED)) {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and [npc.she] reaches down to hold your head in position as [npc.her] wide, flared head swells up in your throat.");
				
			} else if(Sex.getActivePartner().hasPenisModifier(PenetrationModifier.BARBED)) {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and [npc.she] starts making little thrusting motions into your [pc.face],"
						+ " raking [npc.her] barbs up and down your throat as [npc.she] brings [npc.herself] to a climax.");
				
			} else {
				UtilText.nodeContentSB.append(" You feel it start to twitch and throb, and [npc.she] reaches down to hold your head in position as [npc.she] prepares for [npc.her] climax.");
			}

			UtilText.nodeContentSB.append(" [npc.Her] [npc.balls+] tense up, and as [npc.she] lets out [npc.a_moan+]");
			if(Sex.getActivePartner().isWearingCondom()) {
				UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out into the condom that [npc.sheIs] wearing.");
			} else {
				switch (Sex.getActivePartner().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you realise that [npc.sheIs] not able to produce even one drop of cum.");
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
			
			if(Sex.getActivePartner().hasVagina()) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "Letting out a desperate moan, [npc.name]'s climax drops down into [npc.her] [npc.pussy+],"
							+ " and [npc.she] roughly grinds the base of [npc.her] [npc.cock+] down against your lips as [npc.she] shudders and moans in pleasure.");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
						+ "After a few moments, [npc.her] balls have completely emptied themselves, and [npc.she] takes a moment to catch [npc.her] breath, grinning down at you as [npc.she] keeps [npc.her] [npc.cock+] hilted down your throat.");
			
			return UtilText.parse(Sex.getActivePartner(), UtilText.nodeContentSB.toString());
		}

		@Override
		public List<SexAreaOrifice> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getActivePartner()) && cumTarget.equals(Sex.getTargetedPartner(Sex.getActivePartner()))) {
				return Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PARTNER_ORGASM_FACIAL = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
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
			return Sex.getActivePartner().hasPenisIgnoreDildo();
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You feel [npc.name]'s [npc.cock] start to twitch and throb in your mouth, and before you can react, [npc.she] suddenly slides [npc.her] shaft up out of your throat"
					+ (Sex.getActivePartner().isWearingCondom()?", sliding [npc.her] condom off as [npc.she] does so.":"."));
			
			if(Sex.getActivePartner().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				UtilText.nodeContentSB.append(" Reaching down to start masturbating over your face, [npc.she] uses your slimy saliva as lubrication as [npc.she] squeezes and fondles [npc.her] thick swollen knot.");
				
			} else if(Sex.getActivePartner().hasPenisModifier(PenetrationModifier.FLARED)) {
				UtilText.nodeContentSB.append(" Looking down at you, [npc.she] quickly starts masturbating, using your slimy saliva as lubrication as [npc.she] rubs [npc.her] thumb over the top of [npc.her] wide, flared head.");
				
			} else if(Sex.getActivePartner().hasPenisModifier(PenetrationModifier.BARBED)) {
				UtilText.nodeContentSB.append(" You let out a desperate wail as [npc.her] barbs rake their way up your throat, but [npc.she] seems oblivious to your discomfort as [npc.she] quickly starts masturbating,"
							+ " using your slimy saliva as lubrication as [npc.she] rapidly brings [npc.herself] to a climax.");
				
			} else {
				UtilText.nodeContentSB.append(" Looking down at you, [npc.she] quickly starts masturbating, using your slimy saliva as lubrication as [npc.she] runs [npc.her] hand up and down the length of [npc.her] shaft.");
			}

			UtilText.nodeContentSB.append(" [npc.Her] [npc.balls+] tense up, and as [npc.she] lets out [npc.a_moan+]");
			switch (Sex.getActivePartner().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", you see that [npc.sheIs] not able to produce even one drop of cum.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", a small trickle of [npc.cum+] squirts out all over your [pc.face+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", a small amount of [npc.cum+] squirts out all over your [pc.face+].");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] squirts out all over your [pc.face+].");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] shoots out all over your [pc.face+].");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out all over your [pc.face+].");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out all over your [pc.face+].");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", [npc.her] [npc.cum+] pours out all over your [pc.face+].");
					break;
				default:
					break;
			}
			
			if(Sex.getActivePartner().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				UtilText.nodeContentSB.append("  You flinch as you receive your facial, noticing that quite a few strands of [npc.cum] fall down to land on your "
						+(Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES) != null ? Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName() : "exposed [pc.breasts]")+".");
			}
			
			if(Sex.getActivePartner().getVaginaType()!=VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "Letting out [npc.a_moan+], [npc.name]'s climax drops down into [npc.her] [npc.pussy+], and [npc.she] roughly grinds [npc.her] feminine sex down against your forehead,"
							+ " shuddering and moaning in pleasure as [npc.her] [npc.cock+] flops down on the top of your head.");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
						+ " After a few moments, [npc.she] comes down from [npc.her] climax and takes hold of your head, smirking down at you as [npc.she] slides [npc.her] [npc.cock+] back into your mouth.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(Sex.getActivePartner().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				if (Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES) != null) {
					Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(true);
				}
			}
			Sex.stopOngoingAction(Sex.getActivePartner(), SexAreaPenetration.PENIS, Main.game.getPlayer(), SexAreaOrifice.MOUTH);
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
			return condomWearer.equals(Sex.getActivePartner());
		}
	};
}
