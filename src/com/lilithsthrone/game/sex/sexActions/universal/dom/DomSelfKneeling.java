package com.lilithsthrone.game.sex.sexActions.universal.dom;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class DomSelfKneeling {

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
}
