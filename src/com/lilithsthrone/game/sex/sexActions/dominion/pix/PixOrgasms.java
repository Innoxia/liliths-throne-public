package com.lilithsthrone.game.sex.sexActions.dominion.pix;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.69.9
 * @version 0.1.89
 * @author Innoxia
 */
public class PixOrgasms {

	// Player:
	
	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PLAYER_PREPARE_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc.name] is fast approaching [npc.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			return "You find yourself unable to make a move as Pix presses herself against your back; pinning you to the wall of the shower as she prepares to orgasm.";
		}
	};
	
	public static final SexAction PLAYER_ORGASM_PROMISE = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Promise";
		}

		@Override
		public String getActionDescription() {
			return "Promise Pix that you'll come back again.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You can't take any more of Pix's teasing, and as a desperate heat starts to build in your groin, you let out [pc.a_moan+] and do as she asks, "
										+ "[pc.speech(Ok! I promise to come back and train again! Please, just let me get off!)]."
										+ "</br></br>"
										+ "Pix lets out a delighted cry in response, and before you know what's happening, she's darting both of her hands down between your legs and softly growling into your ear, "
										+ "[pix.speech(Good [pc.girl]! Y'know, I think you deserve a little reward for being such a good little thing!)]");
			
			UtilText.nodeContentSB.append("</br></br>");
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().getPenisType()!=PenisType.NONE){
				
				UtilText.nodeContentSB.append("You feel one of Pix's [pix.hands+] wrap around your [pc.cock+], and as she starts furiously jerking you off, she carries on telling you what a good [pc.girl] you've been.");
				
				// Describe cum amount:
				UtilText.nodeContentSB.append(" You don't last more than a few seconds under her expert touch, and as you feel your [pc.balls+] tense up");
				switch (Main.game.getPlayer().getPenisCumProduction()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you let out [pc.a_moan+] as you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", you feel a small trickle of [pc.cum+] squirting");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", you feel a small amount of [pc.cum+] squirting");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] squirting");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] shooting");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					UtilText.nodeContentSB.append(" out all over the wall in front of you. ");
				}
				
				UtilText.nodeContentSB.append("</br></br>");
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				UtilText.nodeContentSB.append("As Pix presses her [pix.breastRows] [pix.breasts+] into your back, you feel a desperate, shuddering heat crashing up from your [pc.pussy+]."
						+ " With a little giggle, Pix suddenly thrusts two of her [pix.fingers+] deep into your [pc.pussy+], and you cry out in ecstasy as she uses her other hand to press down and rub against your sensitive little clit."
						+ " With a final, ear-splitting scream, your climax crashes over you, and you find yourself collapsing back into Pix as you feel your legs giving out from under you.");
			}
			
			// MOUND ORGASM:
			if (Main.game.getPlayer().getPenisType()==PenisType.NONE && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				UtilText.nodeContentSB.append("With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through you."
						+ " The muscles within your genderless mound start to spasm and contract, and you let out a desperate scream as Pix reaches down and starts rubbing and flicking at your extremely sensitive crotch."
						+ " With a final, ear-splitting scream, your climax crashes over you, and you find yourself collapsing back into Pix as you feel your legs giving out from under you.");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_ORGASM_REFUSE = new SexAction(
			SexActionType.PLAYER_ORGASM_NO_AROUSAL_RESET,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Refuse";
		}

		@Override
		public String getActionDescription() {
			return "Stay quiet in the hopes that Pix doesn't notice you're about to reach your climax.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			return "You feel a desperate heat rising in your groin, but, not wanting to promise Pix that you'll come back and see her again, you decide to keep quiet and orgasm without her help."
					+ " As your climax builds and builds, you suddenly find yourself letting out a little [pc.moan]."
					+ "</br></br>"
					+ "Betrayed by your inability to keep quiet, Pix realises what's going on, and with an angry growl, she grabs your [pc.arms], pinning them behind your back as she slams you into the wall, "
					+ "[pix.speech(What did I say?! You're <i>not</i> orgasming until you promise to come back! Y'know, I can do this all day!)]"
					+ "</br></br>"
					+"She holds you there, pinned up against the wall, for several minutes, and try as you might, you're completely unable to break free."
					+ " You even try desperately rubbing your crotch up against the wall of the shower, but it's no use, and you feel your climax slowly ebbing away, leaving you incredibly frustrated and horny."
					+ " Only once it's clear that you've calmed down, does Pix finally release you, and, sliding her hands down over your body, she prepares to bring you to the edge once more...";
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DENIAL));
		}
	};
	
	// Pix orgasm:
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PARTNER_PREPARE_PLAYER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [pc.name] is fast approaching [pc.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			return "Pix suddenly presses you up against the wall, and, leaning in over your shoulder, she growls into your ear, "
					+"[npc.speech(Aww, you ready to cum? Promise that you're gonna come back to train, or else you're never gonna get off!)]";
		}
	};
	
	public static final SexAction PARTNER_ORGASM_PIX_REWARD = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			return "You hear Pix letting out desperate little whines, and you realise that she's getting off on preventing you from orgasming."
					+ " Sensing your chance to break free, you try pushing her off of you, but that only seems to increase the intensity of her moans,"
					+ " and with an ear-splitting scream, she slams you forwards against the wall, grinding her naked [pix.pussy] up against your [pc.ass] as her orgasm washes over her."
					+ "</br></br>"
					+ "After a few moments, she finally recovers, and, straightening up, she happily sighs into your ear, "
					+ "[pix.speech(Fuck... That was good!)]";
		}

		@Override
		public void applyEffects() {
		}
	};
	
}
