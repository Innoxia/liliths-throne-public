package com.lilithsthrone.game.sex;

import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.97
 * @version 0.2.0
 * @author Innoxia
 */
public enum SexPositionSlot {

	/* Doggy-style */
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS("On all fours",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS_SECOND("On all fours",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	/**The partner who's behind the doggy-style target. They are kneeling, and can perform vaginal or anal penetration.*/
	DOGGY_BEHIND("Kneeling behind",
			Util.newArrayListOfValues(
					OrgasmCumTarget.BACK,
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS)),

	/**The partner who's behind the doggy-style target. They are also on all fours, and can perform oral on the doggy-style target.*/
	DOGGY_BEHIND_ORAL("On all fours behind",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),

	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	DOGGY_INFRONT("Kneeling infront",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.FLOOR)),

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	DOGGY_INFRONT_ANAL("Kneeling infront (anal)",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	
	/* Back to wall */
	
	BACK_TO_WALL_AGAINST_WALL("Back against wall",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.LEGS)),
	
	BACK_TO_WALL_FACING_TARGET("Pinning against wall",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.LEGS)),


	/* Face to wall */
	
	FACE_TO_WALL_AGAINST_WALL("Facing wall",
			Util.newArrayListOfValues(
					OrgasmCumTarget.WALL)),
	
	FACE_TO_WALL_FACING_TARGET("Pinning against wall",
			Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.BACK,
					OrgasmCumTarget.FLOOR)),
	
	
	/* Cowgirl */
	
	COWGIRL_ON_BACK("Cowgirl (on back)",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.FLOOR)),
	
	COWGIRL_RIDING("Cowgirl (riding)",
			Util.newArrayListOfValues(
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.HAIR)),

	/* Face-sitting */
	
	FACE_SITTING_ON_FACE("Face-sitting",
			Util.newArrayListOfValues(
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.HAIR)),
	
	FACE_SITTING_ON_BACK("Lying on back",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),
	
	/* Sixty-nine */
	
	SIXTY_NINE_TOP("Sixty-nine (top)",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	SIXTY_NINE_BOTTOM("Sixty-nine (bottom)",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	/* Kneeling oral */
	
	KNEELING_RECEIVING_ORAL("Standing",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	KNEELING_PERFORMING_ORAL("Kneeling",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),
	
	/* Standing */
	
	STANDING_DOMINANT("Standing",
			Util.newArrayListOfValues(
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	
	STANDING_SUBMISSIVE("Standing",
			Util.newArrayListOfValues(
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	
	MASTURBATING_KNEELING("Kneeling",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),
	
	/* Chair */
	
	CHAIR_KNEELING("Kneeling",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),

	CHAIR_ORAL_SITTING("Sitting",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	CHAIR_TOP("Sitting in lap",
			Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN)),
	
	CHAIR_BOTTOM("Sitting",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.FLOOR)),
	
	/* Stocks */
	
	STOCKS_LOCKED_IN_STOCKS("Locked in stocks",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	STOCKS_RECEIVING_ORAL("Receiving oral",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	STOCKS_PERFORMING_ORAL("Performing oral",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	STOCKS_FUCKING("Standing behind",
			Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.BACK,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	/* Milking Stall */
	
	MILKING_STALL_LOCKED_IN_MILKING_STALL("Locked in milking stall",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	MILKING_STALL_RECEIVING_ORAL("Receiving oral",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FLOOR)),
	
	MILKING_STALL_PERFORMING_ORAL("Performing oral",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	MILKING_STALL_FUCKING("Standing behind",
			Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.BACK,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	
	/* Missionary */
	
	MISSIONARY_ON_BACK("Lying on back",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.FLOOR)),
	
	MISSIONARY_KNEELING_BETWEEN_LEGS("Kneeling between legs",
			Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	
	/* UNIQUE SEX SCENES */
	
	PET_MOUNTING_ON_ALL_FOURS("On all fours",
			DOGGY_ON_ALL_FOURS.getAvailableCumTargets()),
	
	PET_MOUNTING_HUMPING("Mounting",
			DOGGY_BEHIND.getAvailableCumTargets()),
	
	PET_ORAL_ON_ALL_FOURS("On all fours",
			KNEELING_PERFORMING_ORAL.getAvailableCumTargets()),
	
	PET_ORAL_COCKED_LEG("Cocked leg",
			KNEELING_RECEIVING_ORAL.getAvailableCumTargets()),
	
	KNEELING_RECEIVING_ORAL_RALPH("Standing",
			KNEELING_RECEIVING_ORAL.getAvailableCumTargets()),
	
	KNEELING_PERFORMING_ORAL_RALPH("Kneeling",
			KNEELING_PERFORMING_ORAL.getAvailableCumTargets()),
	
	
	FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX("Facing wall",
			FACE_TO_WALL_AGAINST_WALL.getAvailableCumTargets()),
	
	FACE_TO_WALL_FACING_TARGET_SHOWER_PIX("Pinning against wall",
			FACE_TO_WALL_FACING_TARGET.getAvailableCumTargets()),
	
	
	HAND_SEX_DOM_ROSE("Standing",
			STANDING_DOMINANT.getAvailableCumTargets()),
	
	HAND_SEX_SUB_ROSE("Standing",
			STANDING_SUBMISSIVE.getAvailableCumTargets()),
	
	
	MISSIONARY_DESK_SUB_VICKY("Lying on counter",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	
	MISSIONARY_DESK_DOM_VICKY("Standing between legs",
			Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	

	KNEELING_RECEIVING_ORAL_CULTIST("Standing",
			KNEELING_RECEIVING_ORAL.getAvailableCumTargets()),
	
	KNEELING_PERFORMING_ORAL_CULTIST("Kneeling",
			KNEELING_PERFORMING_ORAL.getAvailableCumTargets()),
	
	MISSIONARY_ALTAR_LYING_ON_ALTAR("Lying on altar",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS("Between legs",
			Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS("Between legs (oral)",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR("Lying on altar",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_BREASTS,
					OrgasmCumTarget.SELF_FACE,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS("Between legs",
			Util.newArrayListOfValues(
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),

	MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS("Between legs (oral)",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),
	
	/* Breeding stalls */
	
	BREEDING_STALL_FRONT("Lying on stomach",
			Util.newArrayListOfValues(
					OrgasmCumTarget.FLOOR)),

	BREEDING_STALL_BACK("Lying on back",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.STOMACH,
					OrgasmCumTarget.FLOOR)),
	
	BREEDING_STALL_FUCKING("Standing",
			Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	

	/* Glory hole */
	
	GLORY_HOLE_KNEELING("Kneeling",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_STOMACH,
					OrgasmCumTarget.SELF_GROIN,
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),

	GLORY_HOLE_FUCKED("Getting fucked",
			Util.newArrayListOfValues(
					OrgasmCumTarget.SELF_LEGS,
					OrgasmCumTarget.FLOOR)),

	GLORY_HOLE_RECEIVING_ORAL_ONE("Receiving oral",
			Util.newArrayListOfValues(
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.FLOOR)),
	
	GLORY_HOLE_RECEIVING_ORAL_TWO("Receiving oral",
			Util.newArrayListOfValues(
					OrgasmCumTarget.BREASTS,
					OrgasmCumTarget.HAIR,
					OrgasmCumTarget.FACE,
					OrgasmCumTarget.FLOOR)),
	
	GLORY_HOLE_FUCKING("Fucking",
			Util.newArrayListOfValues(
					OrgasmCumTarget.ASS,
					OrgasmCumTarget.GROIN,
					OrgasmCumTarget.LEGS,
					OrgasmCumTarget.FLOOR)),
	;
	
	
	private String name;
	
	private List<OrgasmCumTarget> availableCumTargets;
	
	private SexPositionSlot(String name,
			List<OrgasmCumTarget> availableCumTargets) {
		this.name = name;
		
		this.availableCumTargets = availableCumTargets;
	}

	public String getName() {
		return name;
	}

	public List<OrgasmCumTarget> getAvailableCumTargets() {
		return availableCumTargets;
	}

}
