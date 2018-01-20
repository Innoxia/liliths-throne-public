package com.lilithsthrone.game.sex.sexActions.universal.sub;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.69
 * @version 0.1.79
 * @author Innoxia
 */
public class SubSixtyNine {
	
	// Partner actions:
	
	public static final SexAction PARTNER_MOUND_SNOG = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE,
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Snog mound";
		}

		@Override
		public String getActionDescription() {
			return "Roughly snog [pc.name]'s genderless crotch.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name] roughly grinds [npc.her] [npc.lips+] down against your doll-like mound, forcefully snogging and licking at your sensitive crotch as you [pc.moan+] beneath [npc.herHim].",
						"You let out [pc.a_moan+] as you feel [npc.name] drop [npc.her] head between your [pc.legs] before starting to roughly snog your doll-like mound.",
						"Dropping [npc.her] head down into your crotch, [npc.name] roughly grinds [npc.her] mouth against your genderless crotch, snogging and licking your sensitive mound as you [pc.moan+] beneath [npc.herHim].",
						"Pushing [npc.her] face down into your groin, [npc.name] starts grinding [npc.her] [npc.lips+] against your genderless mound, roughly snogging and licking your sensitive crotch as you [pc.moan+] beneath [npc.herHim].");
		}
	};
	
	public static final SexAction PARTNER_MOUND_KISSING = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE,
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}

		@Override
		public String getActionDescription() {
			return "Passionately kiss and lick [pc.name]'s genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] eagerly presses [npc.her] [npc.lips+] down against your doll-like mound, passionately snogging and licking at your sensitive crotch as you [pc.moan+] beneath [npc.herHim].",
					"You let out [pc.a_moan+] as you feel [npc.name] drop [npc.her] head between your [pc.legs] before starting to passionately snog your doll-like mound.",
					"Dropping [npc.her] head down into your crotch, [npc.name] passionately presses [npc.her] mouth against your genderless crotch, snogging and licking your sensitive mound as you [pc.moan+] beneath [npc.herHim].",
					"Pushing [npc.her] face down into your groin, [npc.name] starts pressing [npc.her] [npc.lips+] against your genderless mound, eagerly snogging and licking your sensitive crotch as you [pc.moan+] beneath [npc.herHim].");
		}
	};
	
	public static final SexAction PARTNER_GENTLE_MOUND_KISSING = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE,
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}

		@Override
		public String getActionDescription() {
			return "Gently kiss and lick [pc.name]'s genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] gently presses [npc.her] [npc.lips+] down against your doll-like mound, planting delicate kisses on your sensitive crotch as you [pc.moan+] beneath [npc.herHim].",
					"You let out [pc.a_moan+] as you feel [npc.name] drop [npc.her] head between your [pc.legs] before starting to gently kiss and lick your doll-like mound.",
					"Dropping [npc.her] head down into your crotch, [npc.name] presses [npc.her] mouth against your genderless crotch, planting a series of gentle kisses on your sensitive mound as you [pc.moan+] beneath [npc.herHim].",
					"Pushing [npc.her] face down into your groin, [npc.name] presses [npc.her] [npc.lips+] against your genderless mound, gently kissing and licking your sensitive crotch as you [pc.moan+] beneath [npc.herHim].");
		}
	};
	
	// Player actions:
	
	public static final SexAction PLAYER_MOUND_SNOG = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE,
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			SexPace.SUB_EAGER,
			null) {
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
			return !Sex.getActivePartner().hasVagina() && !Sex.getActivePartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"You eagerly press your [pc.lips+] up against [npc.name]'s doll-like mound, passionately snogging and licking at [npc.her] sensitive crotch as [npc.she] squeals and moans above you.",
						"[npc.Name] lets out [npc.a_moan+] as you start passionately snogging [npc.her] doll-like mound.",
						"Pushing your head up, you eagerly press your mouth against [npc.name]'s genderless crotch, snogging and licking [npc.her] sensitive mound as [npc.she] squeals and cries out in pleasure.",
						"Reaching around and grabbing [npc.name]'s [npc.ass+], you pull [npc.herHim] down onto your [pc.face+],"
								+ " grinding your [pc.lips] against [npc.her] genderless mound as you enthusiastically snog and lick at [npc.her] sensitive crotch.");
		}
	};
	
	public static final SexAction PLAYER_MOUND_KISSING = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE,
			OrificeType.VAGINA,
			SexParticipantType.PITCHER,
			SexPace.SUB_NORMAL,
			null) {
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
			return !Sex.getActivePartner().hasVagina() && !Sex.getActivePartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"You plant a series of delicate kisses on [npc.name]'s doll-like mound, causing [npc.herHim] to let out [npc.a_moan+] and push [npc.her] [npc.hips] down against your [pc.face+].",
						"[npc.Name] lets out [npc.a_moan+] as you gently kiss and lick [npc.her] doll-like crotch.",
						"With delicate care, you plant a series of gentle kisses on [npc.name]'s genderless mound, causing [npc.herHim] to let out [npc.a_moan+].",
						"You gently kiss and lick at [npc.name]'s genderless crotch, causing [npc.herHim] to let out [npc.a_moan+].");
		}
	};
}
