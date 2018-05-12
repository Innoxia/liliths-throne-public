package com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class PlayerSelfPanties {
	
	public static final SexAction PLAYER_STROKE_VAGINA_PANTIES = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER,
			OrificeType.VAGINA,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Panty-pussy rub";
		}

		@Override
		public String getActionDescription() {
			return "Rub Lilaya's panties over your pussy.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You press the soft fabric of Lilaya's panties down between your [pc.legs+], letting out a deep sigh as you rub them up and down over your exposed [pc.pussy].",
					"Pressing Lilaya's panties up against your [pc.pussy+], you close your [pc.eyes] and imagine your demonic aunt's pussy rubbing against the soft fabric.",
					"Sliding Lilaya's panties over your [pc.pussy+], you let out [pc.a_moan+] as you imagine your demonic aunt wearing them as she works away in her lab.",
					"You eagerly slide Lilaya's panties over your needy [pc.pussy], moaning and sighing as you gently press the fabric against your outer labia.");
		}
		
		@Override
		public String applyEffectsString() {
			if(!LilayasRoom.lilayasPanties.isDirty() && Sex.getWetOrificeTypes(Main.game.getPlayer()).containsKey(OrificeType.VAGINA)) {
				LilayasRoom.lilayasPanties.setDirty(true);
				return "<p style='text-align:center'>"
							+ "[style.italicsDirty(Lilaya's panties are quickly dirtied as you rub them against your wet pussy.)]"
						+ "</p>";
				
			} else {
				return "";
			}
		}
		
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_INCEST);
			} else {
				return null;
			}
		}
		
	};
	
	public static final SexAction PLAYER_STROKE_PENIS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER,
			OrificeType.URETHRA_PENIS,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Panty-cock rub";
		}

		@Override
		public String getActionDescription() {
			return "Rub Lilaya's panties over your [pc.cock].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Wrapping Lilaya's panties around your [pc.cock+], you start stroking up and down your length, [pc.moaning+] as you picture your demonic aunt's pussy rubbing against the soft fabric.",
					"You place Lilaya's panties over the [pc.cockHead+] of your [pc.cock+], before letting out [pc.a_moan+] as you start jerking off with them.",
					"Taking hold of your [pc.cock+], you wrap Lilaya's panties around your shaft, before closing your [pc.eyes] and imagining your demonic aunt's pussy as you masturbate.",
					"Rubbing Lilaya's panties up and down around your [pc.cock+], you let out [pc.a_moan+] as you imagine your demonic aunt wearing them as she works away in her lab.");
		}

		@Override
		public String applyEffectsString() {
			if(!LilayasRoom.lilayasPanties.isDirty() && Sex.getWetPenetrationTypes(Main.game.getPlayer()).containsKey(PenetrationType.PENIS)) {
				LilayasRoom.lilayasPanties.setDirty(true);
				return "<p style='text-align:center'>"
							+ "[style.italicsDirty(Lilaya's panties are quickly dirtied as you rub them over your cock.)]"
						+ "</p>";
				
			} else {
				return "";
			}
		}
		
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_INCEST);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_STROKE_MOUND = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER,
			null,
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasPenis()
					&& !Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public String getActionTitle() {
			return "Panty-mound rub";
		}

		@Override
		public String getActionDescription() {
			return "Rub Lilaya's panties over your genderless mound.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Reaching down between your legs, you rub Lilaya's panties over your doll-like mound, moaning and sighing as you picture your demonic aunt's pussy rubbing against the soft fabric.",
					"You rub Lilaya's panties over the sensitive doll-like mound between your legs, sighing and whining as you imagine your demonic aunt wearing them as she works away in her lab.",
					"Pressing Lilaya's panties up against your sensitive genderless mound, you close your [pc.eyes] and imagine your demonic aunt's pussy as you masturbate.",
					"You eagerly slide Lilaya's panties over your doll-like mound, moaning and sighing as you gently press the fabric against your sensitive [pc.skin].");
		}
		
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASTURBATION);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_SNIFF_PANTIES = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Sniff panties";
		}

		@Override
		public String getActionDescription() {
			return "Sniff Lilaya's used panties.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Bringing Lilaya's panties up to your [pc.face], you press the soft fabric over your nose and take a deep breath, relishing the slightly-musky scent of your demonic aunt's underwear.",
					"You press Lilaya's panties against your face, breathing in the heady, slightly-musky scent of your demonic aunt's used underwear.",
					"Pressing Lilaya's panties up against your nose, you close your [pc.eyes] and imagine your demonic aunt's pussy as you breathe in her musky, perfume-laced scent.",
					"You eagerly press Lilaya's panties over your nose, moaning and sighing as you breathe in the musky, perfume-laced scent of the fabric.");
		}
		
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(
						Fetish.FETISH_INCEST,
						Fetish.FETISH_MASTURBATION);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_MASTURBATION_ORGASM_IN_PANTIES = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			return "Cum in panties";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.game.getPlayer().hasPenisIgnoreDildo()
						&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
						&& !Main.game.getPlayer().isWearingCondom())
					|| (Main.game.getPlayer().hasVagina()
						&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA));
		}

		@Override
		public String getDescription() {
			return GenericOrgasms.getGenericOrgasmDescription(Main.game.getPlayer(), OrgasmCumTarget.LILAYA_PANTIES);
		}
		
		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && !Main.game.getPlayer().isWearingCondom()) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			}
		}
	};

	public static final SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.PLAYER_SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
		@Override
		public String getActionTitle() {
			return "Stop masturbating";
		}

		@Override
		public String getActionDescription() {
			return "Stop masturbating.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isPlayerAbleToStopSex();
		}

		@Override
		public String getDescription() {
			return "Deciding that you've had enough, you release your grip on Lilaya's panties and put an end to your masturbation session.";
		}
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
