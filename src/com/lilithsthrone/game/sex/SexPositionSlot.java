package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.97
 * @version 0.2.11
 * @author Innoxia
 */
public enum SexPositionSlot {


	/**For characters not directly involved in sex.*/
	MISC_WATCHING("Watching",
			true),
	
	/* Doggy-style */
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS("On all fours",
			false),
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS_SECOND("On all fours (second)",
			false),
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS_THIRD("On all fours (third)",
			false),
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS_FOURTH("On all fours (fourth)",
			false),
	
	
	/**The partner who's behind the doggy-style target. They are kneeling or standing (SD), and can perform vaginal or anal penetration.*/
	DOGGY_BEHIND("Kneeling behind", false) {
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
	},
	
	/**The partner who's behind the second doggy-style target. They are kneeling or standing (SD), and can perform vaginal or anal penetration.*/
	DOGGY_BEHIND_SECOND("Kneeling behind", false){
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
	},

	/**The partner who's behind the doggy-style target. They are also on all fours, and can perform oral on the doggy-style target.*/
	DOGGY_BEHIND_ORAL("On all fours behind", false){
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS).isSizeDifferenceTallerThan(target)) {
				return "Kneeling behind";
			}
			return super.getName(target);
		}
	},

	/**The partner who's behind the doggy-style target. They are also on all fours, and can perform oral on the doggy-style target.*/
	DOGGY_BEHIND_ORAL_SECOND("On all fours behind", false){
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND).isSizeDifferenceTallerThan(target)) {
				return "Kneeling behind";
			}
			return super.getName(target);
		}
	},


	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	DOGGY_INFRONT("Kneeling before", false){
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
	},
	
	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	DOGGY_INFRONT_TWO("Kneeling in front", false) {
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
	},
	
	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	DOGGY_INFRONT_SECOND("Kneeling in front", false) {
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
	},
	
	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	DOGGY_INFRONT_SECOND_TWO("Kneeling in front", false) {
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
	},

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	DOGGY_INFRONT_ANAL("Kneeling in front (anal)", false) {
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
	},

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	DOGGY_INFRONT_ANAL_TWO("Kneeling in front (anal)", false) {
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
	},

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	DOGGY_INFRONT_ANAL_SECOND("Kneeling in front (anal)", false) {
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
	},

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	DOGGY_INFRONT_ANAL_SECOND_TWO("Kneeling in front (anal)", false) {
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
	},
	
	/**The partner who's kneeling behind the doggy-style target. They are using the doggy target's foot.*/
	DOGGY_FEET("Kneeling feet", false) {
		@Override
		public String getName(GameCharacter target) {
			return UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "Kneeling at [npc.namePos] [npc.feet]");
		}
	},
	
	/**The partner who's kneeling behind the doggy-style target. They are using the doggy target's foot.*/
	DOGGY_FEET_SECOND("Kneeling feet", false) {
		@Override
		public String getName(GameCharacter target) {
			return UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "Kneeling at [npc.namePos] [npc.feet]");
		}
	},
	
	
	/**Size difference. The partner who's on top of the doggy-style target. They are humping them, and can perform anal penetration.*/
	DOGGY_SD_HUMPING("Humping", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Humping "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]");
		}
	},
	
	/**Size difference. The partner who's on top of the second doggy-style target. They are humping them, and can perform anal penetration.*/
	DOGGY_SD_HUMPING_SECOND("Humping", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Humping "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]");
		}
	},

	/**Size difference. The partner who's kneeling beneath the doggy-style target. They are using the doggy target's breasts.*/
	DOGGY_SD_UNDER("Kneeling under", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Kneeling under "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS), "[npc.name]");
		}
	},

	/**Size difference. The partner who's kneeling beneath the doggy-style target. They are using the doggy target's breasts.*/
	DOGGY_SD_UNDER_SECOND("Kneeling under", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Kneeling under "+UtilText.parse(Sex.getCharacterInPosition(DOGGY_ON_ALL_FOURS_SECOND), "[npc.name]");
		}
	},
	
	
	
	
	/* Back to wall */
	
	BACK_TO_WALL_AGAINST_WALL("Back against wall",
			true),
	
	BACK_TO_WALL_FACING_TARGET("Pinning against wall",
			true),


	/* Face to wall */
	
	FACE_TO_WALL_AGAINST_WALL("Facing wall",
			true),
	
	FACE_TO_WALL_FACING_TARGET("Pinning against wall",
			true),
	
	
	/* Cowgirl */
	
	COWGIRL_ON_BACK("Cowgirl (on back)",
			false),
	
	COWGIRL_RIDING("Cowgirl (riding)",
			false),

	/* Face-sitting */
	
	FACE_SITTING_ON_FACE("Face-sitting",
			false),
	
	FACE_SITTING_ON_BACK("Lying on back",
			false),
	
	/* Sixty-nine */
	
	SIXTY_NINE_TOP("Sixty-nine (top)",
			false),
	
	SIXTY_NINE_BOTTOM("Sixty-nine (bottom)",
			false),
	
	/* Kneeling oral */
	
	KNEELING_RECEIVING_ORAL("Standing",
			true),
	
	KNEELING_PERFORMING_ORAL("Kneeling",
			false),
	
	/* Standing */
	
	STANDING_DOMINANT("Standing",
			true),
	
	STANDING_SUBMISSIVE("Standing",
			true),
	
	STANDING_SD_TALLER("Standing",
			true),
	
	STANDING_SD_SMALLER("Standing",
			true),

	/* Masturbation */
	
	MASTURBATING_KNEELING("Kneeling",
			false),
	
	/* Chair */
	
	CHAIR_KNEELING("Kneeling",
			false),

	CHAIR_ORAL_SITTING("Sitting",
			false),
	
	CHAIR_TOP("Sitting in lap",
			false),
	
	CHAIR_BOTTOM("Sitting",
			false),
	
	/* Stocks */
	
	STOCKS_LOCKED_IN_STOCKS("Locked in stocks",
			false),
	
	STOCKS_RECEIVING_ORAL("Receiving oral",
			false),
	
	STOCKS_PERFORMING_ORAL("Performing oral",
			false),
	
	STOCKS_FUCKING("Standing behind",
			true),

	/* Milking Stall */
	
	MILKING_STALL_LOCKED_IN_MILKING_STALL("Locked in milking stall",
			false),
	
	MILKING_STALL_RECEIVING_ORAL("Receiving oral",
			false),
	
	MILKING_STALL_PERFORMING_ORAL("Performing oral",
			false),
	
	MILKING_STALL_FUCKING("Standing behind",
			true),
	
	/* Missionary */
	
	MISSIONARY_ON_BACK("Lying back",
			false),
	
	MISSIONARY_ON_BACK_SECOND("Lying back (second)",
			false),
	
	MISSIONARY_ON_BACK_THIRD("Lying back (third)",
			false),
	
	MISSIONARY_ON_BACK_FOURTH("Lying back (fourth)",
			false),
	
	MISSIONARY_KNEELING_BETWEEN_LEGS("Kneeling between legs", false) {
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
	},
	
	MISSIONARY_SD_HUMPING("Humping", false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Humping " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]");
				
			} else {
				return "Humping";
			}
		}
	},

	MISSIONARY_SD_PAIZURI("Straddling chest", false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Straddling " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.namePos]") + " chest";
				
			} else {
				return "Straddling chest";
			}
		}
	},

	MISSIONARY_FACE_SITTING("Face-sitting", false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Face-sitting " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]");
				
			} else {
				return "Face-sitting";
			}
		}
	},

	MISSIONARY_KNEELING_BESIDE("Kneeling beside", false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Kneeling beside " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]");
				
			} else {
				return "Kneeling beside";
			}
		}
	},

	MISSIONARY_KNEELING_BESIDE_TWO("Kneeling beside (second)", false) {
		@Override
		public String getName(GameCharacter target) {
			if(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND)!=null) {
				return "Kneeling beside " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK), "[npc.name]")+" (second)";
				
			} else {
				return "Kneeling beside (second)";
			}
		}
	},

	MISSIONARY_KNEELING_BETWEEN_LEGS_SECOND("Kneeling between legs", false) {
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
	},
	
	MISSIONARY_SD_HUMPING_SECOND("Humping", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Humping " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]");
		}
	},

	MISSIONARY_SD_PAIZURI_SECOND("Straddling chest", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Straddling " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.namePos]")+" chest";
		}
	},

	MISSIONARY_FACE_SITTING_SECOND("Face-sitting", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Face-sitting " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]");
		}
	},

	MISSIONARY_KNEELING_BESIDE_SECOND("Kneeling beside", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Kneeling beside " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]");
		}
	},

	MISSIONARY_KNEELING_BESIDE_SECOND_TWO("Kneeling beside (second)", false) {
		@Override
		public String getName(GameCharacter target) {
			return "Kneeling beside " + UtilText.parse(Sex.getCharacterInPosition(MISSIONARY_ON_BACK_SECOND), "[npc.name]")+" (second)";
		}
	},
	
	/* UNIQUE SEX SCENES */
	
	PET_MOUNTING_ON_ALL_FOURS("On all fours",
			false),
	
	PET_MOUNTING_HUMPING("Mounting",
			false),
	
	PET_ORAL_ON_ALL_FOURS("On all fours",
			false),
	
	PET_ORAL_COCKED_LEG("Cocked leg",
			false),
	
	KNEELING_RECEIVING_ORAL_RALPH("Standing",
			true),
	
	KNEELING_PERFORMING_ORAL_RALPH("Kneeling",
			false),
	
	
	FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX("Facing wall",
			true),
	
	FACE_TO_WALL_FACING_TARGET_SHOWER_PIX("Pinning against wall",
			true),
	
	
	HAND_SEX_DOM_ROSE("Standing",
			true),
	
	HAND_SEX_SUB_ROSE("Standing",
			true),
	
	
	MISSIONARY_DESK_SUB_VICKY("Lying on counter",
			false),
	
	MISSIONARY_DESK_DOM_VICKY("Standing between legs",
			true),
	

	KNEELING_RECEIVING_ORAL_CULTIST("Standing",
			true),
	
	KNEELING_PERFORMING_ORAL_CULTIST("Kneeling",
			false),
	
	MISSIONARY_ALTAR_LYING_ON_ALTAR("Lying on altar",
			false),

	MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS("Between legs",
			true),

	MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS("Between legs (oral)",
			false),
	
	MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR("Lying on altar",
			false),

	MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS("Between legs",
			true),

	MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS("Between legs (oral)",
			false),
	
	/* Breeding stalls */
	
	BREEDING_STALL_FRONT("Lying on stomach",
			false),

	BREEDING_STALL_BACK("Lying on back",
			false),
	
	BREEDING_STALL_FUCKING("Standing",
			true),
	

	/* Glory hole */
	
	GLORY_HOLE_KNEELING("Kneeling",
			false),

	GLORY_HOLE_FUCKED("Getting fucked",
			true),

	GLORY_HOLE_RECEIVING_ORAL_ONE("Receiving oral",
			true),
	
	GLORY_HOLE_RECEIVING_ORAL_TWO("Receiving oral",
			true),
	
	GLORY_HOLE_FUCKING("Fucking",
			true),
	;
	
	
	private String name;
	private boolean standing;
	
	private SexPositionSlot(String name,
			boolean standing) {
		this.name = name;
		this.standing = standing;
	}

	/**
	 * @param target The person in this slot.
	 */
	public String getName(GameCharacter target) {
		return name;
	}
	
	/**
	 * Used to check whether the position can use both feet or just one. (If standing, can only use one.)
	 * @param target The person in this slot.
	 * @return
	 */
	public boolean isStanding(GameCharacter target) {
		return standing;
	}

}
