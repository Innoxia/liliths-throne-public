package com.lilithsthrone.game.sex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * @since 0.1.97
 * @version 0.1.97
 * @author Innoxia
 */
public enum SexPositionSlot {

	/* Doggy-style */
	
	/**The partner who's on all fours, being fucked doggy-style.*/
	DOGGY_ON_ALL_FOURS("All fours",
			SexActionPresets.playerDoggyOnAllFours,
			SexActionPresets.partnerDoggyOnAllFours),
	
	/**The partner who's behind the doggy-style target. They are kneeling, and can perform vaginal or anal penetration.*/
	DOGGY_BEHIND("Behind",
			SexActionPresets.playerDoggyBehind,
			SexActionPresets.partnerDoggyBehind),

	/**The partner who's behind the doggy-style target. They are also on all fours, and can perform oral on the doggy-style target.*/
	DOGGY_BEHIND_ORAL("Behind (oral)",
			SexActionPresets.playerDoggyBehindOral,
			SexActionPresets.partnerDoggyBehindOral),

	/**The partner who's in front of the doggy-style target. They can receive oral from the doggy-style target.*/
	DOGGY_INFRONT("Infront",
			SexActionPresets.playerDoggyInfront,
			SexActionPresets.partnerDoggyInfront),

	/**The partner who's in front of the doggy-style target. They are turned around, and can receive anilingus from the doggy-style target.*/
	DOGGY_INFRONT_ANAL("Infront (anal)",
			SexActionPresets.playerDoggyInfrontAnal,
			SexActionPresets.partnerDoggyInfrontAnal),
	
	
	/* Back to wall */
	
	BACK_TO_WALL_AGAINST_WALL("Back-to-wall",
			SexActionPresets.playerBackToWallAgainstWall,
			SexActionPresets.partnerBackToWallAgainstWall),
	
	BACK_TO_WALL_FACING_TARGET("Pin against wall",
			SexActionPresets.playerBackToWallFacingTarget,
			SexActionPresets.partnerBackToWallFacingTarget),


	/* Face to wall */
	
	FACE_TO_WALL_AGAINST_WALL("Face-to-wall",
			SexActionPresets.playerFacingWallAgainstWall,
			SexActionPresets.partnerFacingWallAgainstWall),
	
	FACE_TO_WALL_FACING_TARGET("Pin against wall",
			SexActionPresets.playerFacingWallFacingTarget,
			SexActionPresets.partnerFacingWallFacingTarget),
	
	
	/* Cowgirl */
	
	COWGIRL_ON_BACK("Cowgirl (on back)",
			SexActionPresets.playerCowgirlOnBack,
			SexActionPresets.partnerCowgirlOnBack),
	
	COWGIRL_RIDING("Cowgirl (riding)",
			SexActionPresets.playerCowgirlRiding,
			SexActionPresets.partnerCowgirlRiding),
	
	/* Sixty-nine */
	
	SIXTY_NINE_TOP("Sixty-nine (top)",
			SexActionPresets.playerSixtyNineOnTop,
			SexActionPresets.partnerSixtyNineOnTop),
	
	SIXTY_NINE_BOTTOM("Sixty-nine (bottom)",
			SexActionPresets.playerSixtyNineOnBottom,
			SexActionPresets.partnerSixtyNineOnBottom),
	
	/* Kneeling oral */
	
	KNEELING_RECEIVING_ORAL("Standing",
			SexActionPresets.playerKneelingReceivingOral,
			SexActionPresets.partnerKneelingReceivingOral),
	
	KNEELING_PERFORMING_ORAL("Kneeling",
			SexActionPresets.playerKneelingPerformingOral,
			SexActionPresets.partnerKneelingPerformingOral),
	
	/* Standing */
	
	STANDING_DOMINANT("Standing",
			SexActionPresets.playerStandingDom,
			SexActionPresets.partnerStandingDom),
	
	STANDING_SUBMISSIVE("Standing",
			SexActionPresets.playerStandingSub,
			SexActionPresets.partnerStandingSub),
	
	/* Chair */
	
	CHAIR_TOP("Standing",
			SexActionPresets.playerChairTop,
			SexActionPresets.partnerChairTop),
	
	CHAIR_BOTTOM("Sitting",
			SexActionPresets.playerChairBottom,
			SexActionPresets.partnerChairBottom),
	
	/* Stocks */
	
	STOCKS_LOCKED_IN_STOCKS("Locked in stocks",
			SexActionPresets.playerStocksLockedInStocks,
			SexActionPresets.partnerStocksLockedInStocks),
	
	STOCKS_RECEIVING_ORAL("Receiving oral",
			SexActionPresets.playerStocksReceivingOral,
			SexActionPresets.partnerStocksReceivingOral),
	
	STOCKS_PERFORMING_ORAL("Performing oral",
			SexActionPresets.playerStocksPerformingOral,
			SexActionPresets.partnerStocksPerformingOral),
	
	STOCKS_FUCKING("Standing behind",
			SexActionPresets.playerStocksStandingBehind,
			SexActionPresets.partnerStocksStandingBehind),
	
	
	/* UNIQUE SEX SCENES */
	
	CHAIR_TOP_LILAYA("Standing",
			SexActionPresets.playerChairTopLilaya,
			SexActionPresets.partnerChairTopLilaya),
	
	CHAIR_BOTTOM_LILAYA("Sitting",
			SexActionPresets.playerChairBottomLilaya,
			SexActionPresets.partnerChairBottomLilaya),
	
	DOGGY_ON_ALL_FOURS_AMBER("All fours",
			SexActionPresets.playerDoggyOnAllFoursAmber,
			SexActionPresets.empty),
	
	DOGGY_BEHIND_AMBER("Behind",
			SexActionPresets.empty,
			SexActionPresets.partnerDoggyBehindAmber),
	
	KNEELING_RECEIVING_ORAL_ZARANIX("Standing",
			SexActionPresets.empty,
			SexActionPresets.partnerKneelingReceivingOralZaranix),
	
	KNEELING_PERFORMING_ORAL_ZARANIX("Kneeling",
			SexActionPresets.playerKneelingPerformingOralZaranix,
			SexActionPresets.empty),
	
	KNEELING_RECEIVING_ORAL_RALPH("Standing",
			SexActionPresets.empty,
			SexActionPresets.partnerKneelingReceivingOralRalph),
	
	KNEELING_PERFORMING_ORAL_RALPH("Kneeling",
			SexActionPresets.playerKneelingPerformingOralRalph,
			SexActionPresets.empty),
	
	
	FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX("Face-to-wall",
			SexActionPresets.playerFacingWallAgainstWallPix,
			SexActionPresets.empty),
	
	FACE_TO_WALL_FACING_TARGET_SHOWER_PIX("Pin against wall",
			SexActionPresets.empty,
			SexActionPresets.partnerFacingWallFacingTargetPix),
	
	
	HAND_SEX_DOM_ROSE("Standing",
			SexActionPresets.playerDomHandsRose,
			SexActionPresets.partnerDomHandsRose),
	
	HAND_SEX_SUB_ROSE("Standing",
			SexActionPresets.playerSubHandsRose,
			SexActionPresets.playerSubHandsRose),
	
	
	MISSIONARY_DESK_SUB_VICKY("Lying on counter",
			SexActionPresets.playerMissionaryDeskSubVicky,
			SexActionPresets.partnerMissionaryDeskSubVicky),
	
	MISSIONARY_DESK_DOM_VICKY("Standing between legs",
			SexActionPresets.playerMissionaryDeskDomVicky,
			SexActionPresets.playerMissionaryDeskDomVicky),
	
	
	MISSIONARY_ALTAR_LYING_ON_ALTAR("Lying on altar",
			SexActionPresets.playerMissionaryAltarSubCultist,
			SexActionPresets.partnerMissionaryAltarSubCultist),

	MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS("Between legs",
			SexActionPresets.playerMissionaryAltarDomCultist,
			SexActionPresets.partnerMissionaryAltarDomCultist),

	MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS("Between legs (oral)",
			SexActionPresets.playerMissionaryAltarDomCultistOral,
			SexActionPresets.partnerMissionaryAltarDomCultistOral),
	
	MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR("Lying on altar",
			SexActionPresets.playerMissionaryAltarSealedSubCultist,
			SexActionPresets.partnerMissionaryAltarSealedSubCultist),

	MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS("Between legs",
			SexActionPresets.playerMissionaryAltarSealedDomCultist,
			SexActionPresets.partnerMissionaryAltarSealedDomCultist),

	MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS("Between legs (oral)",
			SexActionPresets.playerMissionaryAltarSealedDomCultistOral,
			SexActionPresets.partnerMissionaryAltarSealedDomCultistOral),
	
	;
	
	
	private String name;
	
	private List<SexActionInterface> actionsAvailablePlayer;
	private List<SexActionInterface> actionsAvailablePartner;
	private List<SexActionInterface> orgasmActionsPlayer;
	private List<SexActionInterface> orgasmActionsPartner;
	private List<SexActionInterface> mutualOrgasmActions;
	
	private SexPositionSlot(String name,
			List<Class<?>> playerSexActionContainingClasses,
			List<Class<?>> partnerSexActionContainingClasses) {
		this.name = name;
		
		List<Class<?>> sexActionContainingClasses = new ArrayList<>();
		sexActionContainingClasses.addAll(playerSexActionContainingClasses);
		sexActionContainingClasses.addAll(partnerSexActionContainingClasses);

		actionsAvailablePlayer = new ArrayList<>();
		actionsAvailablePartner = new ArrayList<>();
		orgasmActionsPlayer = new ArrayList<>();
		orgasmActionsPartner = new ArrayList<>();
		mutualOrgasmActions = new ArrayList<>();
		
		try {
			if (!sexActionContainingClasses.isEmpty()) {
				for(Class<?> container : sexActionContainingClasses) {
					if(container!=null) {
						Field[] fields = container.getFields();
						
						for(Field f : fields){
							
							if (SexAction.class.isAssignableFrom(f.getType())) {
								if (((SexAction) f.get(null)).getActionType().isOrgasmOption()) {
									if (((SexAction) f.get(null)).getActionType() == SexActionType.MUTUAL_ORGASM) {
										mutualOrgasmActions.add(((SexAction) f.get(null)));
										
									} else if (((SexAction) f.get(null)).getActionType().isPlayerAction()) {
										orgasmActionsPlayer.add(((SexAction) f.get(null)));
										
									} else {
										orgasmActionsPartner.add(((SexAction) f.get(null)));
									}
									
								} else {
									if (((SexAction) f.get(null)).getActionType().isPlayerAction()) {
										actionsAvailablePlayer.add(((SexAction) f.get(null)));
										
									} else {
										actionsAvailablePartner.add(((SexAction) f.get(null)));
									}
								}
							}
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public List<SexActionInterface> getActionsAvailablePlayer() {
		return actionsAvailablePlayer;
	}

	public List<SexActionInterface> getActionsAvailablePartner() {
		return actionsAvailablePartner;
	}

	public List<SexActionInterface> getOrgasmActionsPlayer() {
		return orgasmActionsPlayer;
	}

	public List<SexActionInterface> getOrgasmActionsPartner() {
		return orgasmActionsPartner;
	}

	public List<SexActionInterface> getMutualOrgasmActions() {
		return mutualOrgasmActions;
	}
}
