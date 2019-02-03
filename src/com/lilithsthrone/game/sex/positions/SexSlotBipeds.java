package com.lilithsthrone.game.sex.positions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;

/**
 * @since 0.1.97
 * @version 0.3.1
 * @author Innoxia
 */
public class SexSlotBipeds {


	/**For characters not directly involved in sex.*/
	public static final SexSlot MISC_WATCHING = new SexSlot(
			"Watching",
			"spectating",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and, while watching the lewd display before [npc.herHim], [npc.she] [npc.verb(get)] ready to orgasm.",
			true);
	
	/* Doggy-style */
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	public static final SexSlot DOGGY_ON_ALL_FOURS = new SexSlot(
			"On all fours",
			"on all fours",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] on all fours, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	public static final SexSlot DOGGY_ON_ALL_FOURS_SECOND = new SexSlot(
			"On all fours (second)",
			"on all fours (second)",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] on all fours, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	public static final SexSlot DOGGY_ON_ALL_FOURS_THIRD = new SexSlot(
			"On all fours (third)",
			"on all fours (third)",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] on all fours, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	public static final SexSlot DOGGY_ON_ALL_FOURS_FOURTH = new SexSlot(
			"On all fours (fourth)",
			"on all fours (fourth)",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] on all fours, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	
	/**The partner who's behind the doggy-style target. They are kneeling or standing (SD); and can perform vaginal or anal penetration.*/
	public static final SexSlot DOGGY_BEHIND = new SexSlot(
			"Kneeling behind",
			"kneeling behind",
			"[npc1.Name] [npc1.verb(take)] hold of [npc2.namePos] waist, pulling [npc2.herHim] back into [npc1.her] groin and letting out [npc1.a_moan+].",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = super.getName(target);
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				name = "Standing behind";
			}
			
			if(Sex.getCharacterInPosition(DOGGY_BEHIND_SECOND)!=null) {
				name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]");
			}
			
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};
	
	/**The partner who's behind the second doggy-style target. They are kneeling or standing (SD); and can perform vaginal or anal penetration.*/
	public static final SexSlot DOGGY_BEHIND_SECOND = new SexSlot(
			"Kneeling behind",
			"kneeling behind (second)",
			"[npc1.Name] [npc1.verb(take)] hold of [npc2.namePos] waist, pulling [npc2.herHim] back into [npc1.her] groin and letting out [npc1.a_moan+].",
			false){
		@Override
		public String getName(GameCharacter target) {
			String name = super.getName(target);
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				name = "Standing behind";
			}
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_THIRD)!=null) {
				name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_THIRD), "[npc.name]");
			} else {
				name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]");
			}
			
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	/**The partner who's behind the doggy-style target. They are also on all fours, and can perform oral on the doggy-style target.*/
	public static final SexSlot DOGGY_BEHIND_ORAL = new SexSlot(
			"On all fours behind",
			"on all fours behind",
			"[npc1.Name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false){
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				return "Kneeling behind";
			}
			return super.getName(target);
		}
	};

	/**The partner who's behind the doggy-style target. They are also on all fours, and can perform oral on the doggy-style target.*/
	public static final SexSlot DOGGY_BEHIND_ORAL_SECOND = new SexSlot(
			"On all fours behind",
			"on all fours behind (second)",
			"[npc1.Name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false){
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				return "Kneeling behind";
			}
			return super.getName(target);
		}
	};


	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	public static final SexSlot DOGGY_INFRONT = new SexSlot(
			"Kneeling in front",
			"kneeling in front",
			"[npc1.Name] [npc1.verb(shuffle)] forwards, pressing [npc1.her] groin up against [npc2.namePos] [npc2.face] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false){
		@Override
		public String getName(GameCharacter target) {
			String name = "Kneeling before";
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				name = "Standing before";
			}
			name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]");
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};
	
	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	public static final SexSlot DOGGY_INFRONT_TWO = new SexSlot(
			"Kneeling in front",
			"kneeling in front (second)",
			"[npc1.Name] [npc1.verb(shuffle)] forwards, pressing [npc1.her] groin up against [npc2.namePos] [npc2.face] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = "Kneeling before";
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				name = "Standing before";
			}
			name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]");
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};
	
	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	public static final SexSlot DOGGY_INFRONT_SECOND = new SexSlot(
			"Kneeling in front",
			"kneeling in front (third)",
			"[npc1.Name] [npc1.verb(shuffle)] forwards, pressing [npc1.her] groin up against [npc2.namePos] [npc2.face] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = "Kneeling before";
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				name = "Standing before";
			}
			name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]");
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};
	
	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	public static final SexSlot DOGGY_INFRONT_SECOND_TWO = new SexSlot(
			"Kneeling in front",
			"kneeling in front (fourth)",
			"[npc1.Name] [npc1.verb(shuffle)] forwards, pressing [npc1.her] groin up against [npc2.namePos] [npc2.face] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = "Kneeling before";
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				name = "Standing before";
			}
			name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]");
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	public static final SexSlot DOGGY_INFRONT_ANAL = new SexSlot(
			"Kneeling in front (anal)",
			"kneeling in front (anal)",
			"[npc1.Name] [npc1.verb(shuffle)] backwards, pressing [npc1.her] [npc1.ass+] up against [npc2.namePos] [npc2.face+] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = "Kneeling before";
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				name = "Standing before";
			}
			name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]")+" (anal)";
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	public static final SexSlot DOGGY_INFRONT_ANAL_TWO = new SexSlot(
			"Kneeling in front (anal)",
			"kneeling in front (anal, second)",
			"[npc1.Name] [npc1.verb(shuffle)] backwards, pressing [npc1.her] [npc1.ass+] up against [npc2.namePos] [npc2.face+] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = "Kneeling before";
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				name = "Standing before";
			}
			name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]")+" (anal)";
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	public static final SexSlot DOGGY_INFRONT_ANAL_SECOND = new SexSlot(
			"Kneeling in front (anal)",
			"kneeling in front (anal, third)",
			"[npc1.Name] [npc1.verb(shuffle)] backwards, pressing [npc1.her] [npc1.ass+] up against [npc2.namePos] [npc2.face+] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = "Kneeling before";
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				name = "Standing before";
			}
			name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]")+" (anal)";
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	public static final SexSlot DOGGY_INFRONT_ANAL_SECOND_TWO = new SexSlot(
			"Kneeling in front (anal)",
			"kneeling in front (anal, fourth)",
			"[npc1.Name] [npc1.verb(shuffle)] backwards, pressing [npc1.her] [npc1.ass+] up against [npc2.namePos] [npc2.face+] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = "Kneeling before";
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				name = "Standing before";
			}
			name+=" "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]")+" (anal)";
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};
	
	/**The partner who's kneeling behind the doggy-style target. They are using the doggy target's foot.*/
	public static final SexSlot DOGGY_FEET = new SexSlot(
			"Kneeling feet",
			"kneeling at feet",
			"[npc1.Name] [npc1.verb(take)] a firm grip on [npc2.namePos] [npc2.feet], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "Kneeling at [npc.namePos] [npc.feet]");
		}
	};
	
	/**The partner who's kneeling behind the doggy-style target. They are using the doggy target's foot.*/
	public static final SexSlot DOGGY_FEET_SECOND = new SexSlot(
			"Kneeling feet",
			"kneeling at feet (second)",
			"[npc1.Name] [npc1.verb(take)] a firm grip on [npc2.namePos] [npc2.feet], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "Kneeling at [npc.namePos] [npc.feet]");
		}
	};
	
	
	/**Size difference. The partner who's on top of the doggy-style target. They are humping them, and can perform anal penetration.*/
	public static final SexSlot DOGGY_SD_HUMPING = new SexSlot(
			"Humping",
			"humping",
			"[npc1.Name] [npc1.verb(grip)] [npc2.namePos] [npc2.hips+] while continuing to frantically hump [npc2.herHim], before letting out [npc1.a_moan+] as [npc.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Humping "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]");
		}
	};
	
	/**Size difference. The partner who's on top of the second doggy-style target. They are humping them, and can perform anal penetration.*/
	public static final SexSlot DOGGY_SD_HUMPING_SECOND = new SexSlot(
			"Humping",
			"humping (second)",
			"[npc1.Name] [npc1.verb(grip)] [npc2.namePos] [npc2.hips+] while continuing to frantically hump [npc2.herHim], before letting out [npc1.a_moan+] as [npc.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Humping "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]");
		}
	};

	/**Size difference. The partner who's kneeling beneath the doggy-style target. They are using the doggy target's breasts.*/
	public static final SexSlot DOGGY_SD_UNDER = new SexSlot(
			"Kneeling under",
			"kneeling under",
			"[npc1.Name] [npc1.verb(grope)] [npc2.namePos] [npc2.breasts], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Kneeling under "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]");
		}
	};

	/**Size difference. The partner who's kneeling beneath the doggy-style target. They are using the doggy target's breasts.*/
	public static final SexSlot DOGGY_SD_UNDER_SECOND = new SexSlot(
			"Kneeling under",
			"kneeling under (second)",
			"[npc1.Name] [npc1.verb(grope)] [npc2.namePos] [npc2.breasts], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Kneeling under "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]");
		}
	};
	
	
	
	
	/* Back to wall */
	
	public static final SexSlot BACK_TO_WALL_AGAINST_WALL = new SexSlot(
			"Back against wall",
			"back against wall",
			"Leaning back, [npc.name] [npc.verb(brace)] [npc.herself] against the wall as [npc.she] [npc.verb(feel)] [npc.herself] reaching [npc.her] climax.",
			true);
	
	public static final SexSlot BACK_TO_WALL_FACING_TARGET = new SexSlot(
			"Pinning against wall",
			"pinning against wall",
			"Realising that [npc1.sheIs] about to reach [npc1.her] climax, [npc1.name] [npc1.verb(step)] forwards, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(press)] [npc2.name] back against the wall.",
			true);


	/* Face to wall */
	
	public static final SexSlot FACE_TO_WALL_AGAINST_WALL = new SexSlot(
			"Facing wall",
			"facing wall",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] against the wall in front of [npc1.herHim], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	public static final SexSlot FACE_TO_WALL_FACING_TARGET = new SexSlot(
			"Pinning against wall",
			"pinning against wall",
			"[npc1.Name] [npc1.verb(press)] [npc1.herself] into [npc2.namePos] back, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	
	/* Cowgirl */
	
	public static final SexSlot COWGIRL_ON_BACK = new SexSlot(
			"On back",
			"on back",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(take)] hold of [npc2.namePos] waist, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot COWGIRL_KNEELING_BEHIND = new SexSlot(
			"Kneeling behind",
			"kneeling behind",
			"[npc1.Name] [npc1.verb(take)] hold of [npc2.namePos] [npc2.hips+], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot COWGIRL_RECEIVING_ORAL = new SexSlot(
			"Receiving oral",
			"receiving oral",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			false);
	
	public static final SexSlot COWGIRL_RIDING = new SexSlot(
			"Cowgirl (riding)",
			"cowgril (riding)",
			"[npc1.Name] [npc1.verb(look)] down at [npc2.name] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to climax.",
			false);

	/* Face-sitting */
	
	public static final SexSlot FACE_SITTING_ON_FACE = new SexSlot(
			"Face-sitting",
			"sitting on face",
			"[npc.Name] [npc.verb(let)] [npc.her] [npc.legs+] give out from under [npc.herHim], collapsing down on [npc2.namePos] face as [npc.she] prepares to reach [npc.her] climax.",
			false);
	
	public static final SexSlot FACE_SITTING_ON_BACK = new SexSlot(
			"Lying on back",
			"lying on back",
			"With [npc2.name] still sitting on [npc.her] face, [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	/* Sixty-nine */
	
	public static final SexSlot SIXTY_NINE_TOP = new SexSlot(
			"Sixty-nine (top)",
			"sixty-nine (top)",
			"[npc1.Name] [npc1.verb(drop)] [npc1.her] [npc1.face+] down into [npc2.namePos] groin, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot SIXTY_NINE_BOTTOM = new SexSlot(
			"Sixty-nine (bottom)",
			"sixty-nine (bottom)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(grab)] [npc2.namePos] waist, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	/* Kneeling oral */
	
	public static final SexSlot KNEELING_RECEIVING_ORAL = new SexSlot(
			"Standing",
			"receiving oral",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot KNEELING_RECEIVING_ORAL_TWO = new SexSlot(
			"Standing",
			"receiving oral (second)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot KNEELING_RECEIVING_ORAL_THREE = new SexSlot(
			"Standing",
			"receiving oral (third)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot KNEELING_RECEIVING_ORAL_SECOND = new SexSlot(
			"Standing",
			"receiving oral (fourth)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot KNEELING_RECEIVING_ORAL_SECOND_TWO = new SexSlot(
			"Standing",
			"receiving oral (fifth)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot KNEELING_RECEIVING_ORAL_SECOND_THREE = new SexSlot(
			"Standing",
			"receiving oral (sixth)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot KNEELING_PERFORMING_ORAL = new SexSlot(
			"Kneeling",
			"performing oral",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = this.isStanding(target)?"Standing":"Kneeling";
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null) {
				name+=" before "+UtilText.parse(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL), "[npc.name]");
			}
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	public static final SexSlot KNEELING_PERFORMING_ORAL_TWO = new SexSlot(
			"Kneeling",
			"performing oral (second)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = this.isStanding(target)?"Standing":"Kneeling";
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null) {
				name+=" before "+UtilText.parse(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL), "[npc.name]");
			}
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	public static final SexSlot KNEELING_PERFORMING_ORAL_THREE = new SexSlot(
			"Kneeling",
			"performing oral (third)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = this.isStanding(target)?"Standing":"Kneeling";
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null) {
				name+=" before "+UtilText.parse(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL), "[npc.name]");
			}
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};
	
	public static final SexSlot KNEELING_PERFORMING_ORAL_SECOND = new SexSlot(
			"Kneeling",
			"performing oral (fourth)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = this.isStanding(target)?"Standing":"Kneeling";
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null) {
				name+=" before "+UtilText.parse(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO), "[npc.name]");
			}
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null && Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO).isSizeDifferenceTallerThan(target)) {
				return true;
			} else if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	public static final SexSlot KNEELING_PERFORMING_ORAL_SECOND_TWO = new SexSlot(
			"Kneeling",
			"performing oral (fifth)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = this.isStanding(target)?"Standing":"Kneeling";
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null) {
				name+=" before "+UtilText.parse(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO), "[npc.name]");
			}
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null && Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO).isSizeDifferenceTallerThan(target)) {
				return true;
			} else if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};

	public static final SexSlot KNEELING_PERFORMING_ORAL_SECOND_THREE = new SexSlot(
			"Kneeling",
			"performing oral (sixth)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			String name = this.isStanding(target)?"Standing":"Kneeling";
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null) {
				name+=" before "+UtilText.parse(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO), "[npc.name]");
			}
			return name;
		}

		@Override
		public boolean isStanding(GameCharacter target) {
			if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO)!=null && Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL_TWO).isSizeDifferenceTallerThan(target)) {
				return true;
			} else if(Sex.getCharacterInPosition(KNEELING_RECEIVING_ORAL).isSizeDifferenceTallerThan(target)) {
				return true;
			}
			return super.isStanding(target);
		}
	};
	
	/* Standing */
	
	public static final SexSlot STANDING_DOMINANT = new SexSlot(
			"Standing",
			"standing (dominant)",
			"[npc1.Name] [npc1.verb(reach)] around and [npc1.verb(grab)] [npc2.namePos] [npc2.ass+], pulling [npc2.herHim] into [npc1.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);

	public static final SexSlot STANDING_SUBMISSIVE = new SexSlot(
			"Standing",
			"standing (submissive)",
			"[npc1.Name] [npc.verb(lean)] into [npc2.herHim] and [npc.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	public static final SexSlot STANDING_DOMINANT_BEHIND = new SexSlot(
			"Standing behind",
			"standing behind (dominant)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(grab)] [npc2.namePos] [npc2.hips+], pulling [npc2.herHim] into [npc1.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);

	public static final SexSlot STANDING_PERFORMING_ORAL_BEHIND = new SexSlot(
			"Kneeling behind",
			"performing oral behind",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(take)] hold [npc2.namePos] [npc2.hips+], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot STANDING_PERFORMING_ORAL = new SexSlot(
			"Kneeling infront",
			"performing oral",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot STANDING_SD_TALLER = new SexSlot(
			"Standing",
			"standing (taller size-difference)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(grab)] [npc2.namePos] shoulders, pulling [npc2.herHim] into [npc1.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	public static final SexSlot STANDING_SD_SMALLER = new SexSlot(
			"Standing",
			"standing (smaller size-difference)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(grab)] [npc2.namePos] [npc2.hips], leaning into [npc2.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);

	/* Masturbation */
	
	public static final SexSlot MASTURBATING_KNEELING = new SexSlot(
			"Kneeling",
			"kneeling (masturbating)",
			"[npc.Name] [npc.verb(bite)] [npc.her] lip and [npc.verb(let)] out out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false);
	
	/* Chair */
	
	public static final SexSlot CHAIR_KNEELING = new SexSlot(
			"Kneeling",
			"kneeling in front of chair",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot CHAIR_ORAL_SITTING = new SexSlot(
			"Sitting",
			"sitting on chair (receiving oral)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			false);
	
	public static final SexSlot CHAIR_TOP = new SexSlot(
			"Sitting in lap",
			"kneeling in lap",
			"With [npc1.a_moan+], [npc1.name] [npc1.verb(sink)] down into [npc2.namePos] lap.",
			false);
	
	public static final SexSlot CHAIR_BOTTOM = new SexSlot(
			"Sitting",
			"sitting on chair",
			"Wrapping [npc1.her] [npc1.arms+] around [npc2.name], [npc1.name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(pull)] [npc2.herHim] down into [npc.her] lap.",
			false);
	
	/* Stocks */
	
	public static final SexSlot STOCKS_LOCKED_IN_STOCKS = new SexSlot(
			"Locked in stocks",
			"locked in stocks",
			"Unable to move, [npc1.name] [npc1.verb(wriggle)] around in the stocks and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot STOCKS_RECEIVING_ORAL = new SexSlot(
			"Receiving oral",
			"locked in stocks (receiving oral)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot STOCKS_PERFORMING_ORAL = new SexSlot(
			"Performing oral",
			"performing oral",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot STOCKS_FUCKING = new SexSlot(
			"Standing behind",
			"standing behind",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(grab)] [npc2.namePos] waist, pulling [npc2.herHim] back into [npc1.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);

	/* Milking Stall */
	
	public static final SexSlot MILKING_STALL_LOCKED_IN_MILKING_STALL = new SexSlot(
			"Locked in milking stall",
			"locked in milking stall",
			"Unable to move, [npc1.name] [npc1.verb(wriggle)] around in the stocks and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot MILKING_STALL_RECEIVING_ORAL = new SexSlot(
			"Receiving oral",
			"locked in milking stall (receiving oral)",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot MILKING_STALL_PERFORMING_ORAL = new SexSlot(
			"Performing oral",
			"performing oral",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot MILKING_STALL_FUCKING = new SexSlot(
			"Standing behind",
			"standing behind",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(grab)] [npc2.namePos] waist, pulling [npc2.herHim] back into [npc1.herHim] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	/* Missionary */
	
	public static final SexSlot MISSIONARY_ON_BACK = new SexSlot(
			"Lying back",
			"missionary on back",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and,"
					+ " gazing up at [npc2.namePos] face with lust in [npc.her] [npc.eyes], [npc.she] [npc.verb(spread)] [npc.her] [npc.legs] even wider for [npc2.herHim].",
			false);
	
	public static final SexSlot MISSIONARY_ON_BACK_SECOND = new SexSlot(
			"Lying back (second)",
			"missionary on back (second)",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and,"
					+ " gazing up at [npc2.namePos] face with lust in [npc.her] [npc.eyes], [npc.she] [npc.verb(spread)] [npc.her] [npc.legs] even wider for [npc2.herHim].",
			false);
	
	public static final SexSlot MISSIONARY_ON_BACK_THIRD = new SexSlot(
			"Lying back (third)",
			"missionary on back (third)",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and,"
					+ " gazing up at [npc2.namePos] face with lust in [npc.her] [npc.eyes], [npc.she] [npc.verb(spread)] [npc.her] [npc.legs] even wider for [npc2.herHim].",
			false);
	
	public static final SexSlot MISSIONARY_ON_BACK_FOURTH = new SexSlot(
			"Lying back (fourth)",
			"missionary on back (fourth)",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and,"
					+ " gazing up at [npc2.namePos] face with lust in [npc.her] [npc.eyes], [npc.she] [npc.verb(spread)] [npc.her] [npc.legs] even wider for [npc2.herHim].",
			false);
	
	public static final SexSlot MISSIONARY_KNEELING_BETWEEN_LEGS = new SexSlot(
			"Kneeling between legs",
			"missionary between legs",
			"",
			false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND)!=null) {
				if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_THIRD)!=null) {
					return "Kneeling before " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]") +" and "+ UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]");
				} else {
					return "Kneeling before " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]");
				}
				
			} else {
				return "Kneeling between legs";
			}
		}
		@Override
		public String getOrgasmDescription() {
			if(Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_SD_HUMPING)==null
					&& Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_SD_PAIZURI)==null
					&& Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_FACE_SITTING)==null) {
				return "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and, leaning down on top of [npc2.name], [npc.she] [npc.verb(gaze)] lustfully down into [npc2.her] [npc2.eyes+].";
			} else {
				return "[npc.Name] [npc.verb(let)] out [npc.a_moan+], and, grinding into [npc2.namePos] groin, [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
			}
		}
	};
	
	public static final SexSlot MISSIONARY_SD_HUMPING = new SexSlot(
			"Humping",
			"missionary humping",
			"[npc1.Name] [npc1.verb(grip)] [npc2.namePos] [npc2.hips+] while continuing to frantically hump [npc2.herHim], before letting out [npc1.a_moan+] as [npc.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Humping " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]");
				
			} else {
				return "Humping";
			}
		}
	};

	public static final SexSlot MISSIONARY_SD_PAIZURI = new SexSlot(
			"Straddling chest",
			"missionary on chest",
			"[npc.Name] [npc.verb(let)] [npc.her] [npc.legs+] give out from under [npc.herHim], grinding down on [npc2.namePos] chest as [npc.she] prepares to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Straddling " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.namePos]") + " chest";
				
			} else {
				return "Straddling chest";
			}
		}
	};

	public static final SexSlot MISSIONARY_FACE_SITTING = new SexSlot(
			"Face-sitting",
			"missionary sitting on face",
			"[npc.Name] [npc.verb(let)] [npc.her] [npc.legs+] give out from under [npc.herHim], collapsing down on [npc2.namePos] face as [npc.she] prepares to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Face-sitting " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]");
				
			} else {
				return "Face-sitting";
			}
		}
	};

	public static final SexSlot MISSIONARY_KNEELING_BESIDE = new SexSlot(
			"Kneeling beside",
			"missionary beside",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+], and, shuffling forwards to press [npc.herself] against [npc2.name], [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Kneeling beside " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]");
				
			} else {
				return "Kneeling beside";
			}
		}
	};

	public static final SexSlot MISSIONARY_KNEELING_BESIDE_TWO = new SexSlot(
			"Kneeling beside (second)",
			"missionary beside (second)",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+], and, shuffling forwards to press [npc.herself] against [npc2.name], [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Kneeling beside " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]")+" (second)";
				
			} else {
				return "Kneeling beside (second)";
			}
		}
	};

	public static final SexSlot MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND = new SexSlot(
			"Kneeling between legs",
			"missionary between legs (second)",
			"",
			false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_THIRD)!=null) {
				if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_FOURTH)!=null) {
					return "Kneeling before " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_THIRD), "[npc.name]");
				} else {
					return "Kneeling before " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_THIRD), "[npc.name]") +" and "+ UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_FOURTH), "[npc.name]");
				}
			} else {
				return "Kneeling before " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]");
			}
		}
		@Override
		public String getOrgasmDescription() {
			if(Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_SD_HUMPING_SECOND)==null
					&& Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_SD_PAIZURI_SECOND)==null
					&& Sex.getCharacterInPosition(SexSlotBipeds.MISSIONARY_FACE_SITTING_SECOND)==null) {
				return "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax, and, leaning down on top of [npc2.name], [npc.she] [npc.verb(gaze)] lustfully down into [npc2.her] [npc2.eyes+].";
			} else {
				return "[npc.Name] [npc.verb(let)] out [npc.a_moan+], and, grinding into [npc2.namePos] groin, [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.";
			}
		}
	};
	
	public static final SexSlot MISSIONARY_SD_HUMPING_SECOND = new SexSlot(
			"Humping",
			"missionary humping (second)",
			"[npc1.Name] [npc1.verb(grip)] [npc2.namePos] [npc2.hips+] while continuing to frantically hump [npc2.herHim], before letting out [npc1.a_moan+] as [npc.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Humping " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]");
		}
	};

	public static final SexSlot MISSIONARY_SD_PAIZURI_SECOND = new SexSlot(
			"Straddling chest",
			"missionary on chest (second)",
			"[npc.Name] [npc.verb(let)] [npc.her] [npc.legs+] give out from under [npc.herHim], grinding down on [npc2.namePos] chest as [npc.she] prepares to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Straddling " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.namePos]")+" chest";
		}
	};

	public static final SexSlot MISSIONARY_FACE_SITTING_SECOND = new SexSlot(
			"Face-sitting",
			"missionary sitting on face (second)",
			"[npc.Name] [npc.verb(let)] [npc.her] [npc.legs+] give out from under [npc.herHim], collapsing down on [npc2.namePos] face as [npc.she] prepares to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Face-sitting " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]");
		}
	};

	public static final SexSlot MISSIONARY_KNEELING_BESIDE_SECOND = new SexSlot(
			"Kneeling beside",
			"missionary beside (third)",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+], and, shuffling forwards to press [npc.herself] against [npc2.name], [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Kneeling beside " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]");
		}
	};

	public static final SexSlot MISSIONARY_KNEELING_BESIDE_SECOND_TWO = new SexSlot(
			"Kneeling beside (second)",
			"missionary beside (fourth)",
			"[npc.Name] [npc.verb(let)] out [npc.a_moan+], and, shuffling forwards to press [npc.herself] against [npc2.name], [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return "Kneeling beside " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]")+" (second)";
		}
	};
	
	/* UNIQUE SEX SCENES */
	
	public static final SexSlot PET_MOUNTING_ON_ALL_FOURS = new SexSlot(
			"On all fours",
			"on all fours",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] on all fours, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot PET_MOUNTING_HUMPING = new SexSlot(
			"Mounting",
			"mounting",
			"[npc1.Name] [npc1.verb(take)] hold of [npc2.namePos] waist, pulling [npc2.herHim] back into [npc1.her] groin and letting out [npc1.a_moan+].",
			false);
	
	public static final SexSlot PET_ORAL_ON_ALL_FOURS = new SexSlot(
			"On all fours",
			"on all fours (oral)",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] on all fours, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot PET_ORAL_COCKED_LEG = new SexSlot(
			"Cocked leg",
			"cocked leg",
			"[npc1.Name] [npc1.verb(shuffle)] forwards, pressing [npc1.her] groin up against [npc2.namePos] [npc2.face] and letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot KNEELING_RECEIVING_ORAL_RALPH = new SexSlot(
			"Standing",
			"receiving oral",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot KNEELING_PERFORMING_ORAL_RALPH = new SexSlot(
			"Kneeling",
			"performing oral",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	
	public static final SexSlot FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX = new SexSlot(
			"Facing wall",
			"facing wall",
			"[npc1.Name] [npc1.verb(brace)] [npc1.herself] against the wall in front of [npc1.herHim], letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	public static final SexSlot FACE_TO_WALL_FACING_TARGET_SHOWER_PIX = new SexSlot(
			"Pinning against wall",
			"pinning against wall",
			"[npc1.Name] [npc1.verb(press)] [npc1.herself] into [npc2.namePos] back, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	
	public static final SexSlot HAND_SEX_DOM_ROSE = new SexSlot(
			"Standing",
			"hand sex (dominant)",
			"[npc1.Name] [npc1.verb(look)] into [npc2.namePos] [npc2.eyes+] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot HAND_SEX_SUB_ROSE = new SexSlot(
			"Standing",
			"hand sex (submissive)",
			"[npc1.Name] [npc1.verb(look)] into [npc2.namePos] [npc2.eyes+] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	
	public static final SexSlot MISSIONARY_DESK_SUB = new SexSlot(
			"Lying on desk",
			"lying on desk",
			"[npc1.Name] [npc1.verb(look)] up into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot MISSIONARY_DESK_SUB_SECOND = new SexSlot(
			"Lying on desk",
			"lying on desk (second)",
			"[npc1.Name] [npc1.verb(look)] up into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot MISSIONARY_DESK_DOM = new SexSlot(
			"Standing between legs",
			"standing between legs",
			"[npc1.Name] [npc1.verb(look)] down into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	public static final SexSlot MISSIONARY_DESK_DOM_SECOND = new SexSlot(
			"Standing between legs",
			"standing between legs (second)",
			"[npc1.Name] [npc1.verb(look)] down into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	
	public static final SexSlot MATING_PRESS_BOTTOM = new SexSlot(
			"On back",
			"mating pressed",
			"[npc1.Name] [npc1.verb(thrust)] [npc.her] [npc.hips] against the glory hole and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot MATING_PRESS_TOP = new SexSlot(
			"On top",
			"performing mating press",
			"Looking down into [npc2.namePos] [npc2.eyes] and pressing [npc.her] weight down on top of [npc2.herHim], [npc.name] [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	

	public static final SexSlot KNEELING_RECEIVING_ORAL_CULTIST = new SexSlot(
			"Standing",
			"receiving oral",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(place)] a [npc1.hand] on [npc2.namePos] head, before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(reach)] [npc1.her] climax.",
			true);
	
	public static final SexSlot KNEELING_PERFORMING_ORAL_CULTIST = new SexSlot(
			"Kneeling",
			"performing oral",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot MISSIONARY_ALTAR_LYING_ON_ALTAR = new SexSlot(
			"Lying on altar",
			"lying on altar",
			"[npc1.Name] [npc1.verb(look)] up into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS = new SexSlot(
			"Between legs",
			"between legs",
			"[npc1.Name] [npc1.verb(look)] down into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);

	public static final SexSlot MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS = new SexSlot(
			"Between legs (oral)",
			"between legs (oral)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR = new SexSlot(
			"Lying on altar",
			"lying on altar (sealed)",
			"[npc1.Name] [npc1.verb(look)] up into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS = new SexSlot(
			"Between legs",
			"between legs (sealed)",
			"[npc1.Name] [npc1.verb(look)] down into [npc2.namePos] [npc2.eyes] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);

	public static final SexSlot MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS = new SexSlot(
			"Between legs (oral)",
			"between legs (oral, sealed)",
			"[npc1.Name] [npc1.verb(reach)] up and [npc1.verb(place)] a [npc1.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc.her] climax.",
			false);
	
	/* Breeding stalls */
	
	public static final SexSlot BREEDING_STALL_FRONT = new SexSlot(
			"Lying on stomach",
			"lying on stomach",
			"[npc1.Name] [npc1.verb(spread)] [npc1.her] [npc1.legs] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot BREEDING_STALL_BACK = new SexSlot(
			"Lying on back",
			"lying on back",
			"[npc1.Name] [npc1.verb(spread)] [npc1.her] [npc1.legs] and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);
	
	public static final SexSlot BREEDING_STALL_FUCKING = new SexSlot(
			"Standing",
			"breeding",
			"[npc1.Name] [npc1.verb(reach)] down and [npc1.verb(grab)] [npc2.namePos] waist, before stepping forwards and driving [npc1.her] [npc1.cock] deep into [npc2.namePos] [npc2.pussy+]."
				+ " Letting out [npc1.a_moan+], [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax, and pump [npc2.namePos] womb full of [npc1.cum+].",
			true);
	

	/* Glory hole */
	
	public static final SexSlot GLORY_HOLE_KNEELING = new SexSlot(
			"Kneeling",
			"glory hole (kneeling)",
			"[npc1.Name] [npc1.verb(look)] back and forth between the two glory holes, letting out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			false);

	public static final SexSlot GLORY_HOLE_FUCKED = new SexSlot(
			"Getting fucked",
			"glory hole (getting fucked)",
			"[npc1.Name] [npc1.verb(push)] [npc.her] [npc.hips] back against the toilet stall's wall and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);

	public static final SexSlot GLORY_HOLE_RECEIVING_ORAL_ONE = new SexSlot(
			"Receiving oral",
			"glory hole (receiving oral)",
			"[npc1.Name] [npc1.verb(thrust)] [npc.her] [npc.hips] against the glory hole and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	public static final SexSlot GLORY_HOLE_RECEIVING_ORAL_TWO = new SexSlot(
			"Receiving oral",
			"glory hole (receiving oral, second)",
			"[npc1.Name] [npc1.verb(thrust)] [npc.her] [npc.hips] against the glory hole and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	
	public static final SexSlot GLORY_HOLE_FUCKING = new SexSlot(
			"Fucking",
			"glory hole (fucking)",
			"[npc1.Name] [npc1.verb(thrust)] [npc.her] [npc.hips] against the glory hole and [npc1.verb(let)] out [npc1.a_moan+] as [npc1.she] [npc1.verb(prepare)] to reach [npc1.her] climax.",
			true);
	;
}
