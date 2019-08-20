package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;

/**
 * All SexSlots that are used in the STANDING position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotStanding {

	public static final SexSlot STANDING_DOMINANT = new SexSlot(
			"Standing",
			"standing (dom)",
			"[npc.Name] [npc.verb(lean)] heavily into [npc2.namePos] [npc2.breasts] and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getOrgasmDescription() {
			SexSlot targetedSlot = Sex.getSexPositionSlot(Sex.getTargetedPartner(Sex.getCharacterInPosition(this)));
			if(targetedSlot==SexSlotStanding.PERFORMING_ORAL || targetedSlot==SexSlotStanding.PERFORMING_ORAL_TWO || targetedSlot==SexSlotStanding.PERFORMING_ORAL_THREE || targetedSlot==SexSlotStanding.PERFORMING_ORAL_FOUR) {
				return "With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] groin into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			if(targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR) {
				return "With a small backwards thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] rear end back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			return "[npc.Name] [npc.verb(reach)] around and [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.namePos] back, pulling [npc2.herHim] close and letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
		}
	};
	
	public static final SexSlot STANDING_DOMINANT_TWO = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (2nd dom)";
		}
		@Override
		public String getOrgasmDescription() {
			SexSlot targetedSlot = Sex.getSexPositionSlot(Sex.getTargetedPartner(Sex.getCharacterInPosition(this)));
			if(targetedSlot==SexSlotStanding.PERFORMING_ORAL || targetedSlot==SexSlotStanding.PERFORMING_ORAL_TWO || targetedSlot==SexSlotStanding.PERFORMING_ORAL_THREE || targetedSlot==SexSlotStanding.PERFORMING_ORAL_FOUR) {
				return "With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] groin into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			if(targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR) {
				return "With a small backwards thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] rear end back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			return "[npc.Name] [npc.verb(reach)] around and [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.namePos] back, pulling [npc2.herHim] close and letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
		}
	};
	public static final SexSlot STANDING_DOMINANT_THREE = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (3rd dom)";
		}
		@Override
		public String getOrgasmDescription() {
			SexSlot targetedSlot = Sex.getSexPositionSlot(Sex.getTargetedPartner(Sex.getCharacterInPosition(this)));
			if(targetedSlot==SexSlotStanding.PERFORMING_ORAL || targetedSlot==SexSlotStanding.PERFORMING_ORAL_TWO || targetedSlot==SexSlotStanding.PERFORMING_ORAL_THREE || targetedSlot==SexSlotStanding.PERFORMING_ORAL_FOUR) {
				return "With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] groin into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			if(targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR) {
				return "With a small backwards thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] rear end back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			return "[npc.Name] [npc.verb(reach)] around and [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.namePos] back, pulling [npc2.herHim] close and letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
		}
	};
	public static final SexSlot STANDING_DOMINANT_FOUR = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (4th dom)";
		}
		@Override
		public String getOrgasmDescription() {
			SexSlot targetedSlot = Sex.getSexPositionSlot(Sex.getTargetedPartner(Sex.getCharacterInPosition(this)));
			if(targetedSlot==SexSlotStanding.PERFORMING_ORAL || targetedSlot==SexSlotStanding.PERFORMING_ORAL_TWO || targetedSlot==SexSlotStanding.PERFORMING_ORAL_THREE || targetedSlot==SexSlotStanding.PERFORMING_ORAL_FOUR) {
				return "With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] groin into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			if(targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE || targetedSlot==SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR) {
				return "With a small backwards thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] rear end back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			return "[npc.Name] [npc.verb(reach)] around and [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.namePos] back, pulling [npc2.herHim] close and letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
		}
	};

	public static final SexSlot STANDING_SUBMISSIVE = new SexSlot(
			"Standing",
			"standing (sub)",
			"[npc.Name] [npc.verb(lean)] heavily into [npc2.namePos] [npc2.breasts] and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true);

	public static final SexSlot STANDING_SUBMISSIVE_TWO = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (2nd sub)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_THREE = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (3rd sub)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_FOUR = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (4th sub)";
		}
	};

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Performing oral (front)",
			"performing oral (front)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Performing oral (front)",
			"performing oral (2nd front)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Performing oral (front)",
			"performing oral (3rd front)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Performing oral (front)",
			"performing oral (4th front)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND = new SexSlot(
			"Performing oral (behind)",
			"performing oral (behind)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_TWO = new SexSlot(
			"Performing oral (behind)",
			"performing oral (2nd behind)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_THREE = new SexSlot(
			"Performing oral (behind)",
			"performing oral (3rd behind)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_FOUR = new SexSlot(
			"Performing oral (behind)",
			"performing oral (4th behind)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
}
