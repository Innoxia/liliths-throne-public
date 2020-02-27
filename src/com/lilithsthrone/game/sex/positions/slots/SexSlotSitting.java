package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

/**
 * All SexSlots that are used in the SITTING position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotSitting {

	public static final SexSlot SITTING = new SexSlot(
			"Sitting",
			"sitting down",
			"With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false,
			SexSlotTag.SITTING);
	public static final SexSlot SITTING_TWO = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "sitting down (2nd)";
		}
	};
	public static final SexSlot SITTING_THREE = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "sitting down (3rd)";
		}
	};
	public static final SexSlot SITTING_FOUR = new SexSlot(SITTING){
		@Override
		public String getDescription() {
			return "sitting down (4th)";
		}
	};
	
	
	public static final SexSlot SITTING_IN_LAP = new SexSlot(
			"Sitting in lap",
			"sitting in lap",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down into [npc2.namePos] lap, and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false,
			SexSlotTag.SITTING_IN_LAP);
	public static final SexSlot SITTING_IN_LAP_TWO = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "sitting in lap (2nd)";
		}
	};
	public static final SexSlot SITTING_IN_LAP_THREE = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "sitting in lap (3rd)";
		}
	};
	public static final SexSlot SITTING_IN_LAP_FOUR = new SexSlot(SITTING_IN_LAP){
		@Override
		public String getDescription() {
			return "sitting in lap (4th)";
		}
	};
	
	
	public static final SexSlot SITTING_BETWEEN_LEGS = new SexSlot(
			"Between legs",
			"between legs",
			"Leaning down into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_TWO = new SexSlot(
			"Between legs",
			"between legs (2nd)",
			"Leaning down into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_TWO);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_THREE = new SexSlot(
			"Between legs",
			"between legs (3rd)",
			"Leaning down into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_THREE);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_FOUR = new SexSlot(
			"Between legs",
			"between legs (4th)",
			"Leaning down into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(SITTING_FOUR);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Performing oral",
			"performing oral",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Performing oral",
			"performing oral (2nd)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Performing oral",
			"performing oral (3rd)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Performing oral",
			"performing oral (4th)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Main.sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL = new SexSlot(
			"Presenting for oral",
			"presenting for oral",
			"With trembling [npc.legs], [npc.name] [npc.verb(push)] [npc.her] groin back against [npc2.namePos] face, and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false);
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_TWO = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "presenting for oral (2nd)";
		}
	};
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_THREE = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "presenting for oral (3rd)";
		}
	};
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_FOUR = new SexSlot(SITTING_TAUR_PRESENTING_ORAL){
		@Override
		public String getDescription() {
			return "presenting for oral (4th)";
		}
	};
	
}
