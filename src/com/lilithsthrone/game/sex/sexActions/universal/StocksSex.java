package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.3.1
 * @author Innoxia
 */
public class StocksSex {

	public static final SexAction SWITCH_TO_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "Move behind";
		}

		@Override
		public String getActionDescription() {
			return "Move around behind [npc2.name] and get ready to fuck [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to fuck [npc2.name] while [npc2.sheIs] locked in the stocks,"
						+ " [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF up behind [npc2.herHim] and [npc.verb(start)] grinding [npc.her] groin up against [npc2.her] [npc2.ass+]."
					+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moanVerb], "
					+ "[npc.speech(Be a good [npc2.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.BEHIND_STOCKS)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};

	public static final SexAction SWITCH_TO_BENEATH = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "All fours";
		}

		@Override
		public String getActionDescription() {
			return "Drop down on all fours beneath [npc2.name] and push your [npc.ass+] up against [npc2.her] groin, ready to be fucked by [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Wanting to get fucked by [npc2.name], [npc.name] [npc.verb(drop)] down onto all fours and [npc.verb(crawl)] forwards beneath [npc2.her] stocks."
					+ " Shuffling around to get into a comfortable position, [npc.she] [npc.verb(lift)] [npc.her] [npc.hips+] and [npc.verb(push)] [npc.her] [npc.ass+] back against [npc2.her] groin."
					+ " With an excited [npc.moan], [npc.name] [npc.verb(call)] out,"
					+ " [npc.speech(Lucky you! I'm going to let you fuck me!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.BENEATH_STOCKS)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "Perform oral";
		}

		@Override
		public String getActionDescription() {
			return "Kneel behind [npc2.name] and perform oral on [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to perform oral on [npc2.name], [npc.name] [npc.verb(kneel)]down behind [npc2.herHim]."
					+ " Bringing [npc.her] mouth up to [npc2.her] groin, [npc.she] [npc.moanVerb],"
					+ " [npc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};
	
	public static final SexAction SWITCH_TO_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.RECEIVING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "Move to front";
		}

		@Override
		public String getActionDescription() {
			return "Decide to use [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to use [npc2.namePos] mouth, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF back, before moving around in front of [npc2.her] [npc2.face]."
					+ " Bringing [npc.her] groin up to [npc2.her] mouth, [npc.she] [npc.moanVerb],"
					+ " [npc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMStocks(
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS),
					!Main.sex.getInitialSexManager().getAreasBannedMap().get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.MOUTH),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotStocks.RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStocks.LOCKED_IN_STOCKS))));
		}
	};

}
