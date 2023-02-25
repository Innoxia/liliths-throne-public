package com.lilithsthrone.game.sex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;

/**
 * @since 0.1.69.1
 * @version 0.3.7
 * @author Innoxia
 */
public class SexFlags {

	public static boolean selfActionsBlockedPlayer;
	public static boolean selfActionsBlockedPartner;
	
	// Generic:
	public static boolean mutualOrgasmsAllowed;
	public static List<GameCharacter> playerPreparedForCharactersOrgasm;
	/** Typically use the key as a flag, and the value for any integer you want associated with the flag. */
	public static Map<String, Integer> genericFlags;
	
	
	//TODO remove these values and move them into DialogueFlags so that it's all in one place:
	
	// Brax: TODO Temporary awaiting Brax rewrite
	public static boolean braxCumOnChest;
	
	// Ralph
	public static boolean customerAtCounter;
	public static boolean alertedCustomer;
	public static boolean askedForBigDiscount;
	public static int ralphDiscount;
	public static int customerTurnAppearance;
	
	//Pix
	public static boolean pixDemandedPromise;
	public static boolean pixPlayerPromised;
	
	// Claire:
	public static boolean claireSexInterrupted;
	public static int claireSexInterruptedTurn;
	
	
	
	public SexFlags() {
		reset();
	}
	
	public static void reset() {
		genericFlags = new HashMap<>();
		
		selfActionsBlockedPlayer = false;
		selfActionsBlockedPartner = false;
		
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
		
		// Claire:
		claireSexInterrupted = false;
		claireSexInterruptedTurn = -1;
	}
	
}
