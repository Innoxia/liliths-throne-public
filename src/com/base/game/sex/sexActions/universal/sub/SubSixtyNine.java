package com.base.game.sex.sexActions.universal.sub;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.69
 * @version 0.1.79
 * @author Innoxia
 */
public class SubSixtyNine {
	
	// Partner actions:
	
	public static SexAction PARTNER_MOUND_SNOG = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
	};
	
	public static SexAction PARTNER_MOUND_KISSING = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
	};
	
	public static SexAction PARTNER_GENTLE_MOUND_KISSING = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.VAGINA_PLAYER,
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
	};
	
	// Player actions:
	
	public static SexAction PLAYER_MOUND_SNOG = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
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
			return !Sex.getPartner().hasVagina() && !Sex.getPartner().hasPenis();
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_RECEIVING));
		}
	};
	
	public static SexAction PLAYER_MOUND_KISSING = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.VAGINA_PARTNER,
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
			return !Sex.getPartner().hasVagina() && !Sex.getPartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"You plant a series of delicate kisses on [npc.name]'s doll-like mound, causing [npc.herHim] to let out [npc.a_moan+] and push [npc.her] [npc.hips] down against your [pc.face+].",
						"[npc.Name] lets out [npc.a_moan+] as you gently kiss and lick [npc.her] doll-like crotch.",
						"With delicate care, you plant a series of gentle kisses on [npc.name]'s genderless mound, causing [npc.her] to let out [npc.a_moan+].",
						"You gently kiss and lick at [npc.name]'s genderless crotch, causing [npc.her] to let out [npc.a_moan+].");
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
