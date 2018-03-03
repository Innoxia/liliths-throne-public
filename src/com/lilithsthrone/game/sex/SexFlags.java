package com.lilithsthrone.game.sex;

import java.io.Serializable;

/**
 * @since 0.1.69.1
 * @version 0.2.0
 * @author Innoxia
 */
public class SexFlags implements Serializable {

	private static final long serialVersionUID = 1L;

	public static boolean
	requestsBlockedPlayer, requestsBlockedPartner,
	positioningBlockedPlayer, positioningBlockedPartner,
	selfActionsBlockedPlayer, selfActionsBlockedPartner,
	playerRequestedPullOut, playerRequestedCreampie,
	partnerRequestedPullOut, partnerRequestedCreampie,
	
	// Generic:
	mutualOrgasmsAllowed,
	playerPreparedForOrgasm,
	playerGrewDemonicCock,
	
	// Position requests:
	requestedCowgirl,
	requested69,
	requestedDoggy,
	requestedDoggyOral,
	requestedDoggyReceiveOral,
	requestedDomFuckedDoggy,
	requestedBackToWall,
	requestedFaceToWall,
	requestedKneeling,
	requestedSelfKneeling,
	requestedMissionary,
	requestedMissionaryOnBack,
	
	// Brax: TODO Temporary awaiting Brax rewrite
	braxCumOnChest,
	
	// Ralph
	customerAtCounter, alertedCustomer, askedForBigDiscount,
	
	//Pix
	pixDemandedPromise, pixPlayerPromised;
	
	public static int ralphDiscount;

	public SexFlags() {
		reset();
	}
	
	public static void reset() {
		
		requestsBlockedPlayer = false;
		requestsBlockedPartner = false;
		
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
	}

}
