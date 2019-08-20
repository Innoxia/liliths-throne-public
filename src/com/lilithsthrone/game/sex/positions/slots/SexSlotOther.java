package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;

/**
 * @since 0.3.1
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SexSlotOther {
	
	/* STANDING */
	
	public static final SexSlot STANDING_DOMINANT = new SexSlot(
			"Standing",
			"standing (dom)",
			null,
			true);
	
	public static final SexSlot STANDING_DOMINANT_TWO = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (2nd dom)";
		}
	};
	public static final SexSlot STANDING_DOMINANT_THREE = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (3rd dom)";
		}
	};
	public static final SexSlot STANDING_DOMINANT_FOUR = new SexSlot(STANDING_DOMINANT) {
		@Override
		public String getDescription() {
			return "standing (4th dom)";
		}
	};

	public static final SexSlot STANDING_SUBMISSIVE = new SexSlot(
			"Standing",
			"standing (sub)",
			null,
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
	
	
	/* ORAL */
	
	//TODO remove
	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"Standing",
			"receiving oral",
			null,
			true);

	//TODO remove
	public static final SexSlot RECEIVING_ORAL_TWO = new SexSlot(
			"Standing",
			"receiving oral",
			null,
			true);

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Performing oral (front)",
			"Performing oral (front)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Performing oral (front)",
			"Performing oral (2nd front)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_THREE = new SexSlot(
			"Performing oral (front)",
			"Performing oral (3rd front)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_FOUR = new SexSlot(
			"Performing oral (front)",
			"Performing oral (4th front)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND = new SexSlot(
			"Performing oral (behind)",
			"Performing oral (behind)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_TWO = new SexSlot(
			"Performing oral (behind)",
			"Performing oral (2nd behind)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_THREE = new SexSlot(
			"Performing oral (behind)",
			"Performing oral (3rd behind)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_FOUR = new SexSlot(
			"Performing oral (behind)",
			"Performing oral (4th behind)",
			null,
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	
	
	/* AGAINST WALL */
	
	
	
	/* MOUNTING */
	
	public static final SexSlot ALL_FOURS_FUCKED = new SexSlot(
			"Standing/On all fours",
			"being fucked",
			"With trembling [npc.legs], [npc.name] [npc.do] [npc.her] best to [npc.verb(steady)] [npc2.herself], and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true){
		@Override
		public String getName(GameCharacter target) {
			return isStanding(target)
					?"Standing"
					:(target.getLegConfiguration()==LegConfiguration.TAUR
						?"Lying down"
						:"On all fours");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_MOUNTING);
			GameCharacter oralPartner = Sex.getCharacterInPosition(IN_FRONT_OF_ALL_FOURS_TARGET);
			if(oralPartner!=null || partner==null || target.getLegConfiguration()!=LegConfiguration.TAUR) {
				return false; // If nobody is fucking them, they are not a taur, or if they are performing oral, they kneel.
			}
			
			 // Only standing if they are both taurs (so they can get easily mounted by the taur fucking them):
			return target.getLegConfiguration()==LegConfiguration.TAUR && partner.getLegConfiguration()==LegConfiguration.TAUR;
		}
	};

	public static final SexSlot ALL_FOURS_FUCKED_TWO = new SexSlot(
			"Standing/On all fours",
			"being fucked",
			"With trembling [npc.legs], [npc.name] [npc.do] [npc.her] best to [npc.verb(steady)] [npc2.herself], and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true){
		@Override
		public String getName(GameCharacter target) {
			return isStanding(target)
					?"Standing"
					:(target.getLegConfiguration()==LegConfiguration.TAUR
						?"Lying down"
						:"On all fours");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_MOUNTING);
			GameCharacter partner2 = Sex.getCharacterInPosition(ALL_FOURS_MOUNTING_TWO);
			GameCharacter oralPartner = Sex.getCharacterInPosition(IN_FRONT_OF_ALL_FOURS_TARGET);
			GameCharacter oralPartner2 = Sex.getCharacterInPosition(IN_FRONT_OF_ALL_FOURS_TARGET_TWO);
			if(oralPartner!=null || oralPartner2!=null || (partner==null && partner2==null) || target.getLegConfiguration()!=LegConfiguration.TAUR) {
				return false; // If nobody is fucking them, they are not a taur, or if they are performing oral, they kneel.
			}
			
			 // Only standing if they are both taurs (so they can get easily mounted by the taur fucking them):
			return target.getLegConfiguration()==LegConfiguration.TAUR
					&& ((partner!=null && partner.getLegConfiguration()==LegConfiguration.TAUR) || (partner2!=null && partner2.getLegConfiguration()==LegConfiguration.TAUR));
		}
	};

	public static final SexSlot ALL_FOURS_MOUNTING = new SexSlot(
			"Mounting/Kneeling behind",
			"mounting",
			"[npc.Name] wildly [npc.verb(buck)] [npc.her] [npc.hips] and [npc.verb(press)] [npc.her] weight down on top of [npc2.namePos] body, letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			return (target.getLegConfiguration()==LegConfiguration.TAUR
						?"Mounting"
						:standing?"Standing behind":"Kneeling behind")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			return partner!=null && (ALL_FOURS_FUCKED.isStanding(partner) || partner.isSizeDifferenceTallerThan(target));
		}
	};

	public static final SexSlot ALL_FOURS_MOUNTING_TWO = new SexSlot(
			"Mounting/Fucking",
			"mounting (second)",
			"[npc.Name] wildly [npc.verb(buck)] [npc.her] [npc.hips] and [npc.verb(press)] [npc.her] weight down on top of [npc2.namePos] body, letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED_TWO);
			if(partner==null) {
				partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			}
			return (target.getLegConfiguration()==LegConfiguration.TAUR
						?"Mounting"
						:standing?"Standing behind":"Kneeling behind")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED_TWO);
			return partner!=null && (ALL_FOURS_FUCKED_TWO.isStanding(partner) || partner.isSizeDifferenceTallerThan(target));
		}
	};

	public static final SexSlot IN_FRONT_OF_ALL_FOURS_TARGET = new SexSlot(
			"Standing/kneeling",
			"in front",
			"[npc.Name] [npc.verb(press)] [npc.herself] in against [npc2.namePos] [npc2.breasts], letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			return (this.isStanding(target)?"Standing":"Kneeling")+" before "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			return (target.getLegConfiguration().isBipedalPositionedGenitals() && partner!=null && !partner.getLegConfiguration().isBipedalPositionedGenitals())
					|| ALL_FOURS_FUCKED.isStanding(partner)
					|| partner.isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot IN_FRONT_OF_ALL_FOURS_TARGET_TWO = new SexSlot(
			"Standing/kneeling",
			"in front",
			"[npc.Name] [npc.verb(press)] [npc.herself] in against [npc2.namePos] [npc2.breasts], letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED_TWO);
			if(partner==null) {
				partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			}
			return (this.isStanding(target)?"Standing":"Kneeling")+" before "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED_TWO);
			return (target.getLegConfiguration().isBipedalPositionedGenitals() && partner!=null && !partner.getLegConfiguration().isBipedalPositionedGenitals())
					|| ALL_FOURS_FUCKED_TWO.isStanding(partner)
					|| partner.isSizeDifferenceTallerThan(target);
		}
	};
	

	/* LYING DOWN */

	public static final SexSlot LYING_DOWN = new SexSlot(
			"Lying on back",
			"lying on back",
			"With a buck of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot LYING_DOWN_TWO = new SexSlot(
			"Lying on back",
			"lying on back (second)",
			"With a buck of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot COWGIRL = new SexSlot(
			"Riding",
			"riding cow-girl style",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down into [npc2.namePos] groin, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot COWGIRL_TWO = new SexSlot(
			"Riding",
			"riding cow-girl style (second)",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down into [npc2.namePos] groin, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot COWGIRL_REVERSE = new SexSlot(
			"Reversed riding",
			"riding reverse cow-girl style",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down into [npc2.namePos] groin, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot COWGIRL_REVERSE_TWO = new SexSlot(
			"Reversed riding",
			"riding reverse cow-girl style (second)",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down into [npc2.namePos] groin, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot FACE_SITTING = new SexSlot(
			"Frontal face-sitting",
			"face-sitting",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down onto [npc2.namePos] face, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot FACE_SITTING_TWO = new SexSlot(
			"Frontal face-sitting",
			"face-sitting (second)",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down onto [npc2.namePos] face, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot FACE_FUCK = new SexSlot(
			"Face-fucking",
			"face-fucking",
			"With trembling [npc.legs], [npc.name] [npc.verb(drop)] [npc.her] [npc.hips] down into [npc2.namePos] face, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot FACE_FUCK_TWO = new SexSlot(
			"Face-fucking",
			"face-fucking (second)",
			"With trembling [npc.legs], [npc.name] [npc.verb(drop)] [npc.her] [npc.hips] down into [npc2.namePos] face, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot FACE_SITTING_REVERSE = new SexSlot(
			"Reverse face-sitting",
			"reverse face-sitting",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down onto [npc2.namePos] face, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot FACE_SITTING_REVERSE_TWO = new SexSlot(
			"Reverse face-sitting",
			"reverse face-sitting (second)",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down onto [npc2.namePos] face, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot SIXTY_NINE = new SexSlot(
			"Sixty-nine",
			"sixty-nine",
			"With trembling [npc.legs], [npc.name] [npc.verb(allow)] [npc.her] crotch to drop down onto [npc2.namePos] face, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot SIXTY_NINE_TWO = new SexSlot(
			"Sixty-nine",
			"sixty-nine (second)",
			"With trembling [npc.legs], [npc.name] [npc.verb(allow)] [npc.her] crotch to drop down onto [npc2.namePos] face, letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);

	public static final SexSlot MISSIONARY = new SexSlot(
			"Between legs",
			"between legs",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and, leaning down on top of [npc2.name], [npc.she] [npc.verb(gaze)] lustfully down into [npc2.her] [npc2.eyes+].",
			false);

	public static final SexSlot MISSIONARY_TWO = new SexSlot(
			"Between legs",
			"between legs (second)",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and, leaning down on top of [npc2.name], [npc.she] [npc.verb(gaze)] lustfully down into [npc2.her] [npc2.eyes+].",
			false);

	public static final SexSlot MATING_PRESS = new SexSlot(
			"Mating press",
			"mating press",
			"Looking down into [npc2.namePos] [npc2.eyes] and pressing [npc.her] weight down on top of [npc2.herHim], [npc.name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot MATING_PRESS_TWO = new SexSlot(
			"Mating press",
			"mating press (second)",
			"Looking down into [npc2.namePos] [npc2.eyes] and pressing [npc.her] weight down on top of [npc2.herHim], [npc.name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot SCISSORING = new SexSlot(
			"Scissoring",
			"scissoring",
			"Desperately thrusting [npc.her] [npc.hips] forwards in order to grind [npc.her] groin against [npc2.namePos], [npc.name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot SCISSORING_TWO = new SexSlot(
			"Scissoring",
			"scissoring (second)",
			"Desperately thrusting [npc.her] [npc.hips] forwards in order to grind [npc.her] groin against [npc2.namePos], [npc.name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	
	
	/* SITTING */
	
	public static final SexSlot SITTING = new SexSlot(
			"Sitting",
			"sitting down",
			"With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			false);
	public static final SexSlot SITTING_TWO = new SexSlot(SITTING);
	public static final SexSlot SITTING_THREE = new SexSlot(SITTING);
	public static final SexSlot SITTING_FOUR = new SexSlot(SITTING);
	
	
	public static final SexSlot SITTING_IN_LAP = new SexSlot(
			"Sitting in lap",
			"sitting in lap",
			"With trembling [npc.legs], [npc.name] [npc.verb(sink)] down into [npc2.namePos] lap, and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false);
	public static final SexSlot SITTING_IN_LAP_TWO = new SexSlot(SITTING_IN_LAP);
	public static final SexSlot SITTING_IN_LAP_THREE = new SexSlot(SITTING_IN_LAP);
	public static final SexSlot SITTING_IN_LAP_FOUR = new SexSlot(SITTING_IN_LAP);
	
	
	public static final SexSlot SITTING_BETWEEN_LEGS = new SexSlot(
			"Between legs",
			"between legs",
			"Leaning down into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(SITTING);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_TWO = new SexSlot(
			"Between legs",
			"between legs",
			"Leaning down into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(SITTING_TWO);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_THREE = new SexSlot(
			"Between legs",
			"between legs",
			"Leaning down into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(SITTING_THREE);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot SITTING_BETWEEN_LEGS_FOUR = new SexSlot(
			"Between legs",
			"between legs",
			"Leaning down into [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(SITTING_FOUR);
			return partner!=null && !partner.isSizeDifferenceTallerThan(target);
		}
	};
	
	
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL = new SexSlot(
			"Presenting for oral",
			"presenting for oral",
			"With trembling [npc.legs], [npc.name] [npc.verb(push)] [npc.her] groin back against [npc2.namePos] face, and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false);
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_TWO = new SexSlot(SITTING_TAUR_PRESENTING_ORAL);
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_THREE = new SexSlot(SITTING_TAUR_PRESENTING_ORAL);
	public static final SexSlot SITTING_TAUR_PRESENTING_ORAL_FOUR = new SexSlot(SITTING_TAUR_PRESENTING_ORAL);
	
}
