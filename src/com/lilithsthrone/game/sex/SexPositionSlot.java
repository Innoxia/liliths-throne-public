package com.lilithsthrone.game.sex;

import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.97
 * @version 0.2.10
 * @author Innoxia
 */
public enum SexPositionSlot {


	/**For characters not directly involved in sex.*/
	MISC_WATCHING("Watching",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	/* Doggy-style */
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS("On all fours",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS_SECOND("On all fours",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	/**The partner who's behind the doggy-style target. They are kneeling, and can perform vaginal or anal penetration.*/
	DOGGY_BEHIND("Kneeling behind",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.BACK,
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FEET)),

	/**The partner who's behind the doggy-style target. They are also on all fours, and can perform oral on the doggy-style target.*/
	DOGGY_BEHIND_ORAL("On all fours behind",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),

	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	DOGGY_INFRONT("Kneeling infront",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.FLOOR)),

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	DOGGY_INFRONT_ANAL("Kneeling infront (anal)",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	
	/* Back to wall */
	
	BACK_TO_WALL_AGAINST_WALL("Back against wall",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.LEGS)),
	
	BACK_TO_WALL_FACING_TARGET("Pinning against wall",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.LEGS)),


	/* Face to wall */
	
	FACE_TO_WALL_AGAINST_WALL("Facing wall",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.WALL)),
	
	FACE_TO_WALL_FACING_TARGET("Pinning against wall",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.BACK,
					OrgasmCumTarget.FLOOR)),
	
	
	/* Cowgirl */
	
	COWGIRL_ON_BACK("Cowgirl (on back)",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.FLOOR)),
	
	COWGIRL_RIDING("Cowgirl (riding)",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.HAIR)),

	/* Face-sitting */
	
	FACE_SITTING_ON_FACE("Face-sitting",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.HAIR)),
	
	FACE_SITTING_ON_BACK("Lying on back",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),
	
	/* Sixty-nine */
	
	SIXTY_NINE_TOP("Sixty-nine (top)",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	SIXTY_NINE_BOTTOM("Sixty-nine (bottom)",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	/* Kneeling oral */
	
	KNEELING_RECEIVING_ORAL("Standing",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	KNEELING_PERFORMING_ORAL("Kneeling",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR,
					OrgasmCumTarget.FEET)),
	
	/* Standing */
	
	STANDING_DOMINANT("Standing",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	
	STANDING_SUBMISSIVE("Standing",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	
	MASTURBATING_KNEELING("Kneeling",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.SELF_FEET,
					OrgasmCumTarget.FLOOR)),
	
	/* Chair */
	
	CHAIR_KNEELING("Kneeling",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FEET,
					OrgasmCumTarget.FLOOR)),

	CHAIR_ORAL_SITTING("Sitting",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.SELF_FEET,
					OrgasmCumTarget.FLOOR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	CHAIR_TOP("Sitting in lap",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN)),
	
	CHAIR_BOTTOM("Sitting",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.FLOOR)),
	
	/* Stocks */
	
	STOCKS_LOCKED_IN_STOCKS("Locked in stocks",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	STOCKS_RECEIVING_ORAL("Receiving oral",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	STOCKS_PERFORMING_ORAL("Performing oral",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	STOCKS_FUCKING("Standing behind",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.BACK,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FEET,
					OrgasmCumTarget.FLOOR)),

	/* Milking Stall */
	
	MILKING_STALL_LOCKED_IN_MILKING_STALL("Locked in milking stall",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	MILKING_STALL_RECEIVING_ORAL("Receiving oral",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	MILKING_STALL_PERFORMING_ORAL("Performing oral",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	MILKING_STALL_FUCKING("Standing behind",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.BACK,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FEET,
					OrgasmCumTarget.FLOOR)),
	
	/* Missionary */
	
	MISSIONARY_ON_BACK("Lying on back",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.FLOOR)),
	
	MISSIONARY_KNEELING_BETWEEN_LEGS("Kneeling between legs",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FEET,
					OrgasmCumTarget.FLOOR)),
	
	/* UNIQUE SEX SCENES */
	
	PET_MOUNTING_ON_ALL_FOURS("On all fours",
			false, DOGGY_ON_ALL_FOURS.getAvailableCumTargets()),
	
	PET_MOUNTING_HUMPING("Mounting",
			false, DOGGY_BEHIND.getAvailableCumTargets()),
	
	PET_ORAL_ON_ALL_FOURS("On all fours",
			false, KNEELING_PERFORMING_ORAL.getAvailableCumTargets()),
	
	PET_ORAL_COCKED_LEG("Cocked leg",
			false, KNEELING_RECEIVING_ORAL.getAvailableCumTargets()),
	
	KNEELING_RECEIVING_ORAL_RALPH("Standing",
			true, KNEELING_RECEIVING_ORAL.getAvailableCumTargets()),
	
	KNEELING_PERFORMING_ORAL_RALPH("Kneeling",
			false, KNEELING_PERFORMING_ORAL.getAvailableCumTargets()),
	
	
	FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX("Facing wall",
			true, FACE_TO_WALL_AGAINST_WALL.getAvailableCumTargets()),
	
	FACE_TO_WALL_FACING_TARGET_SHOWER_PIX("Pinning against wall",
			true, FACE_TO_WALL_FACING_TARGET.getAvailableCumTargets()),
	
	
	HAND_SEX_DOM_ROSE("Standing",
			true, STANDING_DOMINANT.getAvailableCumTargets()),
	
	HAND_SEX_SUB_ROSE("Standing",
			true, STANDING_SUBMISSIVE.getAvailableCumTargets()),
	
	
	MISSIONARY_DESK_SUB_VICKY("Lying on counter",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	
	MISSIONARY_DESK_DOM_VICKY("Standing between legs",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FEET,
					OrgasmCumTarget.FLOOR)),
	

	KNEELING_RECEIVING_ORAL_CULTIST("Standing",
			true, KNEELING_RECEIVING_ORAL.getAvailableCumTargets()),
	
	KNEELING_PERFORMING_ORAL_CULTIST("Kneeling",
			false, KNEELING_PERFORMING_ORAL.getAvailableCumTargets()),
	
	MISSIONARY_ALTAR_LYING_ON_ALTAR("Lying on altar",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS("Between legs",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS("Between legs (oral)",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR("Lying on altar",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS("Between legs",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS("Between legs (oral)",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	/* Breeding stalls */
	
	BREEDING_STALL_FRONT("Lying on stomach",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),

	BREEDING_STALL_BACK("Lying on back",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.FLOOR)),
	
	BREEDING_STALL_FUCKING("Standing",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FEET,
					OrgasmCumTarget.FLOOR)),
	

	/* Glory hole */
	
	GLORY_HOLE_KNEELING("Kneeling",
			false, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),

	GLORY_HOLE_FUCKED("Getting fucked",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),

	GLORY_HOLE_RECEIVING_ORAL_ONE("Receiving oral",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.FLOOR)),
	
	GLORY_HOLE_RECEIVING_ORAL_TWO("Receiving oral",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.FLOOR)),
	
	GLORY_HOLE_FUCKING("Fucking",
			true, Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	;
	
	
	private String name;
	private boolean standing;
	private List<OrgasmCumTarget> availableCumTargets;
	
	private SexPositionSlot(String name,
			boolean standing, List<OrgasmCumTarget> availableCumTargets) {
		this.name = name;
		this.standing = standing;
		this.availableCumTargets = availableCumTargets;
	}

	public String getName() {
		return name;
	}

	public List<OrgasmCumTarget> getAvailableCumTargets() {
		return availableCumTargets;
	}
	
	public boolean isStanding() {
		return standing;
	}

}
