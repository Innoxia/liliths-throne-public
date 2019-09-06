package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;

/**
 * All SexSlots that are used in the STOCKS position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotStocks {

	public static final SexSlot LOCKED_IN_STOCKS = new SexSlot(
			"Locked in stocks",
			"locked in stocks",
			"Unable to move, [npc1.name] [npc1.verb(wriggle)] around in the stocks and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false,
			SexSlotTag.LOCKED_IN_STOCKS);
	public static final SexSlot LOCKED_IN_STOCKS_TWO = new SexSlot(LOCKED_IN_STOCKS) {
		@Override
		public String getDescription() {
			return "locked in stocks (2nd)";
		}
	};
	public static final SexSlot LOCKED_IN_STOCKS_THREE = new SexSlot(LOCKED_IN_STOCKS) {
		@Override
		public String getDescription() {
			return "locked in stocks (3rd)";
		}
	};
	public static final SexSlot LOCKED_IN_STOCKS_FOUR = new SexSlot(LOCKED_IN_STOCKS) {
		@Override
		public String getDescription() {
			return "locked in stocks (4th)";
		}
	};

	
	public static final SexSlot BEHIND_STOCKS = new SexSlot(
			"Behind stocks",
			"behind stocks",
			"[npc1.Name] [npc1.verb(buck)] [npc.her] hips forwards, thrusting into [npc2.namePos] groin and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false,
			SexSlotTag.BEHIND_STOCKS);
	public static final SexSlot BEHIND_STOCKS_TWO = new SexSlot(BEHIND_STOCKS) {
		@Override
		public String getDescription() {
			return "behind stocks (2nd)";
		}
	};
	public static final SexSlot BEHIND_STOCKS_THREE = new SexSlot(BEHIND_STOCKS) {
		@Override
		public String getDescription() {
			return "behind stocks (3rd)";
		}
	};
	public static final SexSlot BEHIND_STOCKS_FOUR = new SexSlot(BEHIND_STOCKS) {
		@Override
		public String getDescription() {
			return "behind stocks (4th)";
		}
	};

	
	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Performing oral",
			"performing oral",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Performing oral",
			"performing oral (2nd)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Performing oral",
			"performing oral (3rd)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Performing oral",
			"performing oral (4th)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_STOCKS) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	

	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"Receiving oral",
			"receiving oral",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true,
			SexSlotTag.RECEIVING_ORAL_STOCKS);
	public static final SexSlot RECEIVING_ORAL_TWO = new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "receiving oral (2nd)";
		}
	};
	public static final SexSlot RECEIVING_ORAL_THREE =  new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "receiving oral (3rd)";
		}
	};
	public static final SexSlot RECEIVING_ORAL_FOUR =  new SexSlot(RECEIVING_ORAL) {
		@Override
		public String getDescription() {
			return "receiving oral (4th)";
		}
	};
}
