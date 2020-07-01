package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;

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
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().getWorldLocation()==WorldType.SUBMISSION
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Claire.class))
					&& !SexFlags.claireSexInterrupted
					&& Main.sex.getTurn() - SexFlags.claireSexInterruptedTurn > 4
					&& !Main.sex.isInForeplay(Main.game.getNpc(Claire.class))
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
			return UtilText.parseFromXMLFile("characters/submission/claire", "PARTNER_INTERRUPTED");
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = true;
			SexFlags.claireSexInterruptedTurn = Main.sex.getTurn();
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
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& SexFlags.claireSexInterrupted;
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
			return "Do as Claire says and remain quiet until the Enforcer has left.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("characters/submission/claire", "ENFORCER_ENTERING"));
			
			boolean foundPenetration = false;
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			if(map.containsKey(SexAreaPenetration.PENIS)
					&& map.get(SexAreaPenetration.PENIS).containsKey(Main.game.getNpc(Claire.class))) {
				SexAreaInterface area = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next();
				if(area.isOrifice()) {
					foundPenetration = true;
					sb.append("<br/><br/>");
					sb.append("Remaining completely still and silent as the Enforcer starts searching through the storeroom, you suddenly become aware of your [pc.cock+] ");
					switch((SexAreaOrifice)area) {
						case ANUS:
							sb.append("throbbing deep inside Claire's [claire.asshole]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.ass+] back against you, driving your cock even deeper into her [claire.asshole].");
							break;
						case ASS:
							sb.append("throbbing between Claire's [claire.assSize] ass cheeks."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly bucks her [claire.hips+] back against you, driving your cock up and down over her [claire.asshole].");
							break;
						case BREAST:
							sb.append("throbbing between Claire's [claire.breastSize] boobs."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.breasts+] together, enveloping your cock in her cleavage.");
							break;
						case BREAST_CROTCH:
							sb.append("throbbing between Claire's [claire.crotchBoobSize] [claire.crotchBoobs]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.crotchBoobs+] together, enveloping your cock in her cleavage.");
							break;
						case MOUTH:
							sb.append("throbbing inside Claire's mouth."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her head forwards, driving your cock deep down her throat.");
							break;
						case NIPPLE:
							sb.append("throbbing deep inside Claire's [claire.nipple(true)]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her chest out into you, driving your cock even deeper into her [claire.nipple+].");
							break;
						case NIPPLE_CROTCH:
							sb.append("throbbing deep inside Claire's [claire.crotchNipple]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her stomach out into you, driving your cock even deeper into her [claire.crotchNipple+].");
							break;
						case THIGHS:
							sb.append("throbbing between Claire's thick thighs."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly bucks her [claire.hips+] back against you, driving your cock between her soft legs.");
							break;
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							sb.append("throbbing deep inside Claire's [claire.urethraVagina]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.hips+] out into you, driving your cock even deeper into her [claire.urethraVagina].");
							break;
						case VAGINA:
							sb.append("throbbing deep inside Claire's [claire.pussy+]."
									+ " Obviously madly turned-on by the situation which the two of you find yourselves in, the horny cat-girl slowly pushes her [claire.hips+] out into you, driving your cock even deeper into her [claire.pussy+].");
							break;
					}
					sb.append("<br/><br/>"
							+ "Just as you feel as though you can't take it any more, the Enforcer finds what they're looking for, and, without spotting the two of you locked in coitus, they leave just as quickly as they entered.");
				}
			}
			
			if(!foundPenetration) {
				sb.append("<br/><br/>"
						+ "After a nerve-wracking thirty or so seconds, the Enforcer finally finds what they're looking for, and, without spotting the two of you locked in coitus, they leave just as quickly as they entered.");
			}
			sb.append(" Letting out a sigh of relief, you and Claire move back into your previous position, before continuing from where you left off...");

			return sb.toString();
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = false;
		}
	};
	

	public static final SexAction PLAYER_KEEP_GOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			boolean foundPenetration = false;
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			if(map.containsKey(SexAreaPenetration.PENIS) && map.get(SexAreaPenetration.PENIS).containsKey(Main.game.getNpc(Claire.class))) {
				foundPenetration = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next().isOrifice();
			}
			return foundPenetration
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& SexFlags.claireSexInterrupted;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Keep going";
		}
		@Override
		public String getActionDescription() {
			return "Ignore Claire's plea to remain quiet and keep on fucking her.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("characters/submission/claire", "ENFORCER_ENTERING"));
			
			Map<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> map = Main.sex.getOngoingActionsMap(Main.game.getPlayer());
			SexAreaInterface area = map.get(SexAreaPenetration.PENIS).get(Main.game.getNpc(Claire.class)).iterator().next();
			if(area.isOrifice()) {
				sb.append("<br/><br/>");
				sb.append("Seeing no reason to do as Claire asks, you ignore the fact that there's an Enforcer searching through the storeroom and continue to ");
				switch((SexAreaOrifice)area) {
					case ANUS:
						sb.append(" thrust your [pc.cock+] in and out of Claire's [claire.asshole+]."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her ass.");
						break;
					case ASS:
						sb.append(" thrust your [pc.cock+] up and down between Claire's [claire.assSize] ass cheeks."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her ass's cleavage.");
						break;
					case BREAST:
						sb.append(" thrust your [pc.cock+] up and down between Claire's [claire.breastSize] boobs."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her ample cleavage.");
						break;
					case BREAST_CROTCH:
						sb.append("thrust your [pc.cock+] up and down between Claire's [claire.crotchBoobSize] [claire.crotchBoobs]."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her crotch-boob's cleavage.");
						break;
					case MOUTH:
						sb.append(" thrust your [pc.cock+] down Claire's throat."
								+ " Not wanting to be discovered, the horny cat-girl does her best to remain quiet as you continue to relentlessly fuck her face.");
						break;
					case NIPPLE:
						sb.append(" thrust your [pc.cock+] in and out of Claire's [claire.nipple(true)+]."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her [claire.nipple(true)].");
						break;
					case NIPPLE_CROTCH:
						sb.append(" thrust your [pc.cock+] in and out of Claire's [claire.crotchNipple(true)+]."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her [claire.crotchNipple(true)].");
						break;
					case THIGHS:
						sb.append(" thrust your [pc.cock+] in and out of Claire's thick thighs."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her thighs.");
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						sb.append(" thrust your [pc.cock+] in and out of Claire's [claire.urethraVagina]."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her [claire.urethraVagina].");
						break;
					case VAGINA:
						sb.append(" thrust your [pc.cock+] in and out of Claire's [claire.pussy+]."
								+ " Not wanting to be discovered, the horny cat-girl covers her mouth with both hands and does her best to remain quiet as you continue to relentlessly fuck her [claire.pussy].");
						break;
				}
				sb.append("<br/><br/>"
						+ "Just as it seems as though the busty cat-girl can't take any more, the Enforcer finds what they're looking for, and, without spotting the two of you locked in coitus, they leave just as quickly as they entered.");
			}
			
			sb.append(" Finally able to break her silence, Claire lets out a particularly-erotic moan and exclaims, [claire.speechNoEffects(~Aah!~ [pc.Name]! ~Ooh!~ I-I told you to stop!)]");
			sb.append("<br/><br/>");
			sb.append("Telling your horny partner that you were having too much fun to do as she asked, you flash her a cheeky smile before moving back into your previous position and quickly continuing from where you left off...");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			SexFlags.claireSexInterrupted = false;
		}
	};
}
