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
			"standing",
			"[npc.Name] [npc.verb(lean)] heavily into [npc2.namePos] [npc2.breasts] and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true,
			SexSlotTag.STANDING) {
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			SexSlot targetedSlot = Sex.getSexPositionSlot(targetedCharacter);
			if(targetedSlot.hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return "With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] groin into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			if(targetedSlot.hasTag(SexSlotTag.PERFORMING_ORAL_BEHIND)) {
				return "With a small backwards thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] rear end back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			if(targetedSlot.hasTag(SexSlotTag.STANDING_BEHIND)) {
				return "Leaning back into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.";
			}
			if(targetedCharacter.isTaur()) {
				return "[npc.Name] [npc.verb(reach)] around and [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.namePos] back, pulling [npc2.herHim] close and letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
			}
			return "[npc.Name] [npc.verb(reach)] around and [npc.verb(grab)] [npc2.namePos] [npc2.ass+], pulling [npc2.herHim] close and letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
		}
	};
	
	public static final SexSlot STANDING_DOMINANT_TWO = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (2nd)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getGenericOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot STANDING_DOMINANT_THREE = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (3rd)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getGenericOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};
	public static final SexSlot STANDING_DOMINANT_FOUR = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (4th)";
		}
		@Override
		public String getOrgasmDescription(GameCharacter orgasmingCharacter, GameCharacter targetedCharacter) {
			return STANDING_DOMINANT.getGenericOrgasmDescription(orgasmingCharacter, targetedCharacter);
		}
	};

	public static final SexSlot STANDING_SUBMISSIVE = new SexSlot(
			"Standing",
			"standing (in front)",
			"[npc.Name] [npc.verb(lean)] heavily into [npc2.namePos] [npc2.breasts] and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true);

	public static final SexSlot STANDING_SUBMISSIVE_TWO = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (2nd in front)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_THREE = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (3rd in front)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_FOUR = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (4th in front)";
		}
	};

	public static final SexSlot STANDING_SUBMISSIVE_BEHIND = new SexSlot(
			"Standing",
			"standing (behind)",
			"[npc.Name] [npc.verb(pull)] [npc2.name] back in against [npc.her] [npc.breasts] and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true,
			SexSlotTag.STANDING_BEHIND);

	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_TWO = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (2nd behind)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_THREE = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (3rd behind)";
		}
	};
	public static final SexSlot STANDING_SUBMISSIVE_BEHIND_FOUR = new SexSlot(STANDING_SUBMISSIVE) {
		@Override
		public String getDescription() {
			return "standing (4th behind)";
		}
	};

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Performing oral (front)",
			"performing oral (front)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Performing oral (front)",
			"performing oral (2nd front)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Performing oral (front)",
			"performing oral (3rd front)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Performing oral (front)",
			"performing oral (4th front)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND = new SexSlot(
			"Performing oral (behind)",
			"performing oral (behind)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_TWO = new SexSlot(
			"Performing oral (behind)",
			"performing oral (2nd behind)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_THREE = new SexSlot(
			"Performing oral (behind)",
			"performing oral (3rd behind)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_FOUR = new SexSlot(
			"Performing oral (behind)",
			"performing oral (4th behind)",
			null,
			false,
			SexSlotTag.PERFORMING_ORAL_BEHIND) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
}
