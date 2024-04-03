package com.lilithsthrone.game.sex.sexActions.baseActions;

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
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class TongueMound {
	
	private static String getDescriptionFinisher(SexAction action, int variation) {
		if(!action.isTargetedCharacterInanimate()) {
			if(variation==1) {
				return " as [npc2.name] [npc2.moan+] beneath [npc.herHim].";
			}
			return " as [npc2.she] [npc2.moan+] beneath [npc.herHim].";
		}
		return ".";
	}
	
	public static final SexAction MOUND_SNOG = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough mound kiss";
		}

		@Override
		public String getActionDescription() {
			return "Roughly kiss [npc2.namePos] genderless crotch.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name] roughly [npc.verb(grind)] [npc.her] [npc.lips+] down against [npc2.namePos] doll-like mound,"
								+ " forcefully kissing and licking at [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0),

						"Dropping [npc.her] head down into [npc2.namePos] crotch, [npc.name] roughly [npc.verb(grind)] [npc.her] mouth against [npc2.her] genderless crotch,"
								+ " kissing and licking [npc2.her] sensitive mound"+getDescriptionFinisher(this, 1),

						"Pushing [npc.her] face down into [npc2.namePos] groin, [npc.name] [npc.verb(start)] grinding [npc.her] [npc.lips+] against [npc2.her] genderless mound,"
								+ " roughly kissing and licking [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}

		@Override
		public String getActionDescription() {
			return "Passionately kiss and lick [npc2.namePos] genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] down against [npc2.namePos] doll-like mound,"
							+ " passionately kissing and licking at [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0),

					"Dropping [npc.her] head down into [npc2.namePos] crotch, [npc.name] passionately [npc.verb(press)] [npc.her] mouth against [npc2.her] genderless crotch,"
							+ " kissing and licking [npc2.her] sensitive mound"+getDescriptionFinisher(this, 1),

					"Pushing [npc.her] face down into [npc2.namePos] groin, [npc.name] [npc.verb(start)] pressing [npc.her] [npc.lips+] against [npc2.her] genderless mound,"
							+ " eagerly kissing and licking [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction GENTLE_MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}

		@Override
		public String getActionDescription() {
			return "Gently kiss and lick [npc2.namePos] genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] gently [npc.verb(press)] [npc.her] [npc.lips+] down against [npc2.namePos] doll-like mound,"
							+ " planting delicate kisses on [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0),

					"Dropping [npc.her] head down into [npc2.namePos] crotch, [npc.name] gently [npc.verb(press)] [npc.her] mouth against [npc2.her] genderless crotch,"
							+ " kissing and licking [npc2.her] sensitive mound"+getDescriptionFinisher(this, 1),

					"Pushing [npc.her] face down into [npc2.namePos] groin, [npc.name] [npc.verb(start)] softly pressing [npc.her] [npc.lips+] against [npc2.her] genderless mound,"
							+ " gently kissing and licking [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_SNOG_SUB_EAGER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Snog mound";
		}

		@Override
		public String getActionDescription() {
			return "Passionately snog [npc2.namePos] genderless crotch.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] down against [npc2.namePos] doll-like mound,"
							+ " passionately kissing and licking at [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0),

					"Dropping [npc.her] head down into [npc2.namePos] crotch, [npc.name] passionately [npc.verb(press)] [npc.her] mouth against [npc2.her] genderless crotch,"
							+ " kissing and licking [npc2.her] sensitive mound"+getDescriptionFinisher(this, 1),

					"Pushing [npc.her] face down into [npc2.namePos] groin, [npc.name] [npc.verb(start)] pressing [npc.her] [npc.lips+] against [npc2.her] genderless mound,"
							+ " eagerly kissing and licking [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_KISSING_SUB_NORMAL = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Kiss mound";
		}
	
		@Override
		public String getActionDescription() {
			return "Gently kiss and lick [npc.namePos] doll-like crotch.";
		}
	
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}
	
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(press)] [npc.her] [npc.lips+] down against [npc2.namePos] doll-like mound,"
							+ " kissing and licking at [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0),

					"Dropping [npc.her] head down into [npc2.namePos] crotch, [npc.name] [npc.verb(press)] [npc.her] mouth against [npc2.her] genderless crotch,"
							+ " kissing and licking [npc2.her] sensitive mound"+getDescriptionFinisher(this, 1),

					"Pushing [npc.her] face down into [npc2.namePos] groin, [npc.name] [npc.verb(start)] pressing [npc.her] [npc.lips+] against [npc2.her] genderless mound,"
							+ " kissing and licking [npc2.her] sensitive crotch"+getDescriptionFinisher(this, 0));
		}
	};
}
