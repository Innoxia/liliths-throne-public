package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMScarlettShopOral;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class SAScarlett {

	public static final SexAction GENERIC_PREPARATION_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Scarlett.class))
					&& Main.sex.isDom(Main.game.getNpc(Scarlett.class))
					&& !Main.game.getNpc(Scarlett.class).isMute()
					&& Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
					&& Main.sex.getSexPace(Main.game.getNpc(Scarlett.class))==SexPace.DOM_ROUGH
					&& Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Scarlett.class)).contains(SexAreaPenetration.PENIS);
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getActionTitle() {
			return "Buttslut tease";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return "Realising that you're close to reaching your climax, [scarlett.name] slams [scarlett.her] [scarlett.cock+] deep into your [pc.asshole+] and teases,"
					+ " [scarlett.speechNoEffects(Come on, bitch! ~Mmm!~ Prove to me that you're a dirty buttslut and cum from the feeling of my cock in your ass!)]";
		}
	};
	
	public static final SexAction CUSTOMER_INTERRUPTION = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& (Main.sex.getInitialSexManager() instanceof SMScarlettShopOral)
					&& (!SexFlags.genericFlags.containsKey("scarlettInterruptedTurn") || Main.sex.getTurn()-SexFlags.genericFlags.get("scarlettInterruptedTurn") > 10) // Make this very infrequent
					&& Main.sex.getTurn()>2
					&& Main.game.getPlayer().getArousal()<75
					&& Main.game.getNpc(Scarlett.class).getArousal()<75
					&& Math.random()<0.5f;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Customer interruption";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile("characters/dominion/scarlett", "CUSTOMER_INTERRUPTION");
		}
		@Override
		public void applyEffects() {
			SexFlags.genericFlags.put("scarlettInterruptedTurn", Main.sex.getTurn());
		}
	};
	
	public static final SexAction HELENA_INTERRUPTION = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& (Main.sex.getInitialSexManager() instanceof SMScarlettShopOral)
					&& !SexFlags.genericFlags.containsKey("helenaInterruptedTurn")
					&& SexFlags.genericFlags.containsKey("scarlettInterruptedTurn")
					&& Main.game.getPlayer().getArousal()<95
					&& Main.game.getNpc(Scarlett.class).getArousal()>75 && Main.game.getNpc(Scarlett.class).getArousal()<95;
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getActionTitle() {
			return "Helena interruption";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile("characters/dominion/scarlett", "HELENA_INTERRUPTION");
		}
		@Override
		public void applyEffects() {
			SexFlags.genericFlags.put("helenaInterruptedTurn", Main.sex.getTurn());
		}
	};
}
