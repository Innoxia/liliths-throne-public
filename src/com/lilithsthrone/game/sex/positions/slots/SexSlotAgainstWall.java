package com.lilithsthrone.game.sex.positions.slots;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the AGAINST_WALL position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotAgainstWall {
	
	public static final SexSlot FACE_TO_WALL = new SexSlot(
			"Face-to-wall",
			"facing wall",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] against the wall in front of [npc1.herHim], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true,
			SexSlotTag.FACE_TO_WALL);
	public static final SexSlot FACE_TO_WALL_TWO = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "facing wall (2nd)";
		}
	};
	public static final SexSlot FACE_TO_WALL_THREE = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "facing wall (3rd)";
		}
	};
	public static final SexSlot FACE_TO_WALL_FOUR = new SexSlot(FACE_TO_WALL) {
		@Override
		public String getDescription() {
			return "facing wall (4th)";
		}
	};

	public static final SexSlot BACK_TO_WALL = new SexSlot(
			"Back against wall",
			"back to wall",
			"Leaning back, [npc.name] [npc.verb(brace)] [npc.herself] against the wall as [npc.she] [npc.verb(feel)] [npc.herself] reaching [npc.her] climax.",
			true,
			SexSlotTag.BACK_TO_WALL);
	public static final SexSlot BACK_TO_WALL_TWO = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "back to wall (2nd)";
		}
	};
	public static final SexSlot BACK_TO_WALL_THREE = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "back to wall (3rd)";
		}
	};
	public static final SexSlot BACK_TO_WALL_FOUR = new SexSlot(BACK_TO_WALL) {
		@Override
		public String getDescription() {
			return "back to wall (4th)";
		}
	};

	public static final SexSlot STANDING_WALL = new SexSlot(
			"Standing",
			"standing",
			"Realising that [npc1.sheIs] about to reach [npc1.her] climax, [npc1.name] [npc1.verb(step)] forwards, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(press)] [npc2.name] against the wall.",
			true);
	
	public static final SexSlot STANDING_WALL_TWO = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "standing (2nd)";
		}
	};
	public static final SexSlot STANDING_WALL_THREE = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "standing (3rd)";
		}
	};
	public static final SexSlot STANDING_WALL_FOUR = new SexSlot(STANDING_WALL) {
		@Override
		public String getDescription() {
			return "standing (4th)";
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL = new SexSlot(
			"Performing oral",
			"performing oral",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_TWO = new SexSlot(
			"Performing oral",
			"performing oral (2nd)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_THREE = new SexSlot(
			"Performing oral",
			"performing oral (3rd)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_WALL_FOUR = new SexSlot(
			"Performing oral",
			"performing oral (4th)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
}
