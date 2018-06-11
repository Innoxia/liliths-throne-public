package com.lilithsthrone.game.sex.sexActions.universal.sub;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class SubSelfKneeling {

	public static final SexAction PARTNER_MOUND_SNOG = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_EAGER) {
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
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.VAGINA,
			SexParticipantType.PITCHER,
			null,
			SexPace.SUB_NORMAL) {
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
}
