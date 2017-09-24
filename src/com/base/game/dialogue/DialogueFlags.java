package com.base.game.dialogue;

import java.io.Serializable;

import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.npc.NPC;
import com.base.game.inventory.enchanting.TFEssence;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public class DialogueFlags implements Serializable {

	private static final long serialVersionUID = 1L;

	// Discounts:
	public long ralphDiscountStartTime;
	public int ralphDiscount, scarlettPrice;
	
	public BodyCoveringType skinTypeSelected;
	
	public TFEssence focusedEssence;
	
	public DialogueNodeOld slaveryManagerRootDialogue;
	public NPC slaveTrader, slaveryManagerSlaveSelected;
	
	public boolean
			// Misc:
			
			quickTrade,
			jinxedClothingDiscovered,
			stormTextUpdateRequired,
	
			// Gym:
			gymIntroduced, gymHadTour, gymIsMember,
			
			// Shopping arcade:
			ralphIntroduced,
			nyanIntroduced,
			kateIntroduced, reactedToKatePregnancy,
			
			// Aunt's Home:
			auntHomeJustEntered, hadSexWithLilaya, reactedToPregnancyLilaya, waitingOnLilayaPregnancyResults,
			essenceExtractionKnown,
			readBook1, readBook2, readBook3,
			
			// Brax:
			accessToEnforcerHQ, braxTransformedPlayer, braxBeaten, seenBraxAfterQuest, feminisedBrax, bimbofiedBrax,
			
			// Harpy Nests:
			hasHarpyNestAccess, bimboEncountered, bimboPacified, dominantEncountered, dominantPacified, nymphoEncountered, nymphoPacified,
			punishedByAlexa,
			
			// Slaver Alley:
			finchIntroduced;

	public DialogueFlags() {

		quickTrade = false;
		jinxedClothingDiscovered = false;
		stormTextUpdateRequired = false;
		
		focusedEssence = null;
		
		slaveryManagerRootDialogue = null;
		slaveryManagerSlaveSelected = null;
		slaveTrader = null;
		
		ralphDiscountStartTime=-1;
		ralphDiscount=0;
		
		scarlettPrice = 2000;
		
		gymIntroduced = false;
		gymHadTour = false;
		gymIsMember = false;
		
		ralphIntroduced = false;
		nyanIntroduced = false;
		kateIntroduced = false;
		reactedToKatePregnancy = false;
		
		skinTypeSelected = null;

		// Aunt's Home:
		auntHomeJustEntered = false;
		hadSexWithLilaya = false;
		reactedToPregnancyLilaya = false;
		waitingOnLilayaPregnancyResults = false;
		essenceExtractionKnown = false;
		readBook1 = false;
		readBook2 = false;
		readBook3 = false;
		
		// Brax:
		accessToEnforcerHQ = false;
		braxBeaten = false;
		seenBraxAfterQuest = false;
		feminisedBrax = false;
		bimbofiedBrax = false;

		// Harpy Nests:
		hasHarpyNestAccess = false;
		bimboEncountered = false;
		bimboPacified = false;
		dominantEncountered = false;
		dominantPacified = false;
		nymphoEncountered = false;
		nymphoPacified = false;
		
		punishedByAlexa = false;

		// Slaver Alley:
		finchIntroduced = false;
	}

}
