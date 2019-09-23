package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public class SAClaireDangerSex {

	public static final SexAction PARTNER_INTERRUPTED = new SexAction(
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
		public boolean isBaseRequirementsMet() {
			return !SexFlags.claireSexInterrupted
					&& Sex.getTurn() - SexFlags.claireSexInterruptedTurn > 4
					&& !Sex.isInForeplay(Main.game.getNpc(Claire.class))
					&& Main.game.getPlayer().getArousal()<75
					&& Main.game.getNpc(Claire.class).getArousal()<75
					&& Math.random()<0.25f;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "The door opens!";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("[claire.speechNoEffects(~Mmm!~ Yes!)] Claire moans, before suddenly opening her eyes wide and hissing, [claire.speech(I think I hear someone coming!)]"
					+ "<br/><br/>"
					+ "Sure enough, as you look over towards the door to the storeroom, you see the handle beginning to turn.");
			
			if(Sex.getSexPositionSlot(Main.game.getPlayer()).isStanding(Main.game.getPlayer())) {
				sb.append(" Before you can make a move, Claire frantically grabs hold of you and pulls you behind a nearby filing cabinet."
						+ " With her hot breath falling on your bare [pc.skin], [npc.she] hisses, [claire.speech(Don't make a sound!)]");
			} else {
				sb.append(" Before you can make a move, Claire frantically grabs hold of you and pulls you down onto the carpeted floor behind a stack of papers."
						+ " With her hot breath falling on your bare [pc.skin], [npc.she] hisses, [claire.speech(Don't make a sound!)]");
			}
			
			sb.append("[style.italicsBad(There is an enforcer entering the stockroom!)]");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = true;
			SexFlags.claireSexInterruptedTurn = Sex.getTurn();
		}
	};
	
	public static final SexAction PLAYER_STAY_QUIET = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.claireSexInterrupted;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Stay quiet";
		}
		@Override
		public String getActionDescription() {
			return "Do as Claire says and remain quiet until the enforcer has left.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append("From your hiding place, you and Claire are only just concealed from the angle of the doorway, and as you hear it swing open, you wonder if the two of you are about to be discovered."
					+ " Calling out behind them, the enforcer who's entering the room shouts,"
					+ (Math.random()<0.5f?" [genericMale.speech(":" [genericFemale.speech(")
					+ UtilText.returnStringAtRandom(
							"What?! Just one box?!",
							"Yeah, I know, I'm getting it now!",
							"Did you say you were after the red ones?! ... Ok!",
							"I think I know where they're kept by now! I <i>have</i> been working here for two years, remember?!",
							"Yeah, I'll grab you some pencils as well, hold on!")
					+ ")]");
			
			boolean foundPenetration = false;
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Sex.getOngoingActionsMap(Main.game.getPlayer());
			if(map.containsKey(SexAreaPenetration.PENIS)
					&& map.get(SexAreaPenetration.PENIS).containsKey(Main.game.getNpc(Claire.class))) {
				SexAreaInterface area = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next();
				if(area.isOrifice()) {
					foundPenetration = true;
					sb.append("<br/><br/>");
					switch((SexAreaOrifice)area) {
						case ANUS:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing deep inside Claire's [claire.asshole]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.ass+] back against you, driving your cock even deeper into her [claire.asshole].");
							break;
						case ASS:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing between Claire's [claire.assSize] ass cheeks."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly bucks her [claire.hips+] back against you, driving your cock up and down over her [claire.asshole].");
							break;
						case BREAST:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing between Claire's [claire.breastSize] boobs."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.breasts+] together, enveloping your cock in her cleavage.");
							break;
						case BREAST_CROTCH:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing between Claire's [claire.crotchBoobSize] [claire.crotchBoobs]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.crotchBoobs+] together, enveloping your cock in her cleavage.");
							break;
						case MOUTH:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing inside Claire's mouth."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her head forwards, driving your cock deep down her throat.");
							break;
						case NIPPLE:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing deep inside Claire's [claire.nipple]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her chest out into you, driving your cock even deeper into her [claire.nipple+].");
							break;
						case NIPPLE_CROTCH:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing deep inside Claire's [claire.crotchNipple]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her stomach out into you, driving your cock even deeper into her [claire.crotchNipple+].");
							break;
						case THIGHS:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing between Claire's thick thighs."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly bucks her [claire.hips+] back against you, driving your cock between her soft legs.");
							break;
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing deep inside Claire's [claire.urethraVagina]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.hips+] out into you, driving your cock even deeper into her [claire.urethraVagina].");
							break;
						case VAGINA:
							sb.append("Remaining completely still and silent as the enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] throbbing deep inside Claire's [claire.pussy+]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.hips+] out into you, driving your cock even deeper into her [claire.pussy+].");
							break;
					}
					sb.append("<br/><br/>"
							+ "Just as you feel as though you can't take it any more, the enforcer finds what they're looking for, and, without spotting the two of you locked in coitus, they leave just as quickly as they entered.");
				}
			}
			
			if(foundPenetration) {
				sb.append("<br/><br/>"
						+ "After a nerve-wracking thirty or so seconds, the enforcer finally finds what they're looking for, and, without spotting the two of you locked in coitus, they leave just as quickly as they entered.");
			}
			sb.append(" Letting out a sigh of relief, you and Claire move back into your previous position, before continuing from where you left off...");

			return sb.toString();
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = false;
		}
	};
}
