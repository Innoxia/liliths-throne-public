package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * All SexSlots that are used in the OVER_DESK position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotDesk {

	public static final SexSlot OVER_DESK_ON_BACK = new SexSlot(
			"Lying back",
			"lying back",
			"[npc.Name] [npc.verb(collapse)] back down onto the desk's surface, before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false,
			SexSlotTag.OVER_DESK_BACK);
	public static final SexSlot OVER_DESK_ON_BACK_TWO = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "lying back (2nd)";
		}
	};
	public static final SexSlot OVER_DESK_ON_BACK_THREE = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "lying back (3rd)";
		}
	};
	public static final SexSlot OVER_DESK_ON_BACK_FOUR = new SexSlot(OVER_DESK_ON_BACK) {
		@Override
		public String getDescription() {
			return "lying back (4th)";
		}
	};

	
	public static final SexSlot OVER_DESK_ON_FRONT = new SexSlot(
			"Bent over",
			"bent over",
			"[npc.Name] [npc.verb(collapse)] down onto the desk's surface, before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false,
			SexSlotTag.OVER_DESK_FRONT);
	public static final SexSlot OVER_DESK_ON_FRONT_TWO = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "bent over (2nd)";
		}
	};
	public static final SexSlot OVER_DESK_ON_FRONT_THREE = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "bent over (3rd)";
		}
	};
	public static final SexSlot OVER_DESK_ON_FRONT_FOUR = new SexSlot(OVER_DESK_ON_FRONT) {
		@Override
		public String getDescription() {
			return "bent over (4th)";
		}
	};

	
	public static final SexSlot BETWEEN_LEGS = new SexSlot(
			"Between legs",
			"between legs",
			null,
			true,
			SexSlotTag.OVER_DESK_BETWEEN_LEGS);
	public static final SexSlot BETWEEN_LEGS_TWO = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "between legs (2nd)";
		}
	};
	public static final SexSlot BETWEEN_LEGS_THREE = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "between legs (3rd)";
		}
	};
	public static final SexSlot BETWEEN_LEGS_FOUR = new SexSlot(BETWEEN_LEGS) {
		@Override
		public String getDescription() {
			return "between legs (4th)";
		}
	};

	
	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Performing oral",
			"performing oral",
			"[npc.Name] [npc.verb(lower)] [npc.her] head down into [npc2.namePos] groin, before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Performing oral",
			"performing oral (2nd)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Performing oral",
			"performing oral (3rd)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Performing oral",
			"performing oral (4th)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return target.getHeightValue()<=80; // Assuming a desk is 70cm high, a character stands to perform oral if they're 80cm or shorter
		}
	};
	

	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"Receiving oral",
			"receiving oral",
			"[npc.Name] [npc.verb(press)] [npc.herself] up close against [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true);
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
