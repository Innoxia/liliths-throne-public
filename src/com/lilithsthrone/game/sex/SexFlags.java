package com.lilithsthrone.game.sex;

import java.io.Serializable;

/**
 * @since 0.1.69.1
 * @version 0.2.8
 * @author Innoxia
 */
public class SexFlags implements Serializable {

	private static final long serialVersionUID = 1L;

	public static boolean positioningBlockedPlayer;
	public static boolean positioningBlockedPartner;
	public static boolean selfActionsBlockedPlayer;
	public static boolean selfActionsBlockedPartner;
	public static boolean playerRequestedPullOut;
	public static boolean playerRequestedCreampie;
	public static boolean partnerRequestedPullOut;
	public static boolean partnerRequestedCreampie;
	
	// Generic:
	public static boolean mutualOrgasmsAllowed;
	public static boolean playerPreparedForOrgasm;
	public static boolean playerGrewDemonicCock;
	public static boolean playerDeniedPartner;
	
	// Position requests:
	public static boolean requestedCowgirl;
	public static boolean requested69;
	public static boolean requestedDoggy;
	public static boolean requestedDoggyOral;
	public static boolean requestedDoggyReceiveOral;
	public static boolean requestedDomFuckedDoggy;
	public static boolean requestedBackToWall;
	public static boolean requestedFaceToWall;
	public static boolean requestedKneeling;
	public static boolean requestedSelfKneeling;
	public static boolean requestedMissionary;
	public static boolean requestedMissionaryOnBack;
	public static boolean requestedSitOnFace;
	public static boolean requestedFaceSitting;
	
	
	public static boolean requestedChairBottom;
	public static boolean requestedChairTop;
	public static boolean requestedChairOralGiving;
	public static boolean requestedChairOralReceiving;
	
	// Brax: TODO Temporary awaiting Brax rewrite
	public static boolean braxCumOnChest;
	
	// Ralph
	public static boolean customerAtCounter;
	public static boolean alertedCustomer;
	public static boolean askedForBigDiscount;
	
	//Pix
	public static boolean pixDemandedPromise;
	public static boolean pixPlayerPromised;
	
	public static int ralphDiscount;

	public SexFlags() {
		reset();
	}
	
	public static void reset() {
		
		positioningBlockedPlayer = false;
		positioningBlockedPartner = false;
		
		selfActionsBlockedPlayer = false;
		selfActionsBlockedPartner = false;
		
		playerRequestedPullOut = false;
		playerRequestedCreampie = false;
		partnerRequestedPullOut = false;
		partnerRequestedCreampie = false;
		
		mutualOrgasmsAllowed = true;
		playerPreparedForOrgasm = false;
		
		playerGrewDemonicCock = false;
		playerDeniedPartner = false;
		
		resetRequests();
		
		// Brax:
		braxCumOnChest = false;
		
		// Ralph:
		customerAtCounter = false;
		alertedCustomer = false;
		askedForBigDiscount = false;
		ralphDiscount = 25;
		
		// Pix:
		pixDemandedPromise=false;
		pixPlayerPromised = false;
	}
	
	public static void resetRequests() {
		requestedCowgirl = false;
		requested69 = false;
		requestedDoggy = false;
		requestedDoggyOral = false;
		requestedDoggyReceiveOral = false;
		requestedDomFuckedDoggy = false;
		requestedBackToWall = false;
		requestedFaceToWall = false;
		requestedKneeling = false;
		requestedSelfKneeling = false;
		requestedMissionary = false;
		requestedMissionaryOnBack = false;
		requestedSitOnFace = false;
		requestedFaceSitting = false;
		
		requestedChairBottom = false;
		requestedChairTop = false;
		requestedChairOralGiving = false;
		requestedChairOralReceiving = false;
	}

}
