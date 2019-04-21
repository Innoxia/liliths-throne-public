package com.lilithsthrone.game.sex;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.1.69.1
 * @version 0.3.1
 * @author Innoxia
 */
public class SexFlags {

	public static boolean selfActionsBlockedPlayer;
	public static boolean selfActionsBlockedPartner;
	public static boolean characterRequestedPullOut;
	public static boolean characterRequestedCreampie;
	
	// Generic:
	public static boolean mutualOrgasmsAllowed;
	public static List<GameCharacter> playerPreparedForCharactersOrgasm;

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
		selfActionsBlockedPlayer = false;
		selfActionsBlockedPartner = false;
		
		characterRequestedPullOut = false;
		characterRequestedCreampie = false;
		
		mutualOrgasmsAllowed = true;
		playerPreparedForCharactersOrgasm = new ArrayList<>();
		
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
	
}
