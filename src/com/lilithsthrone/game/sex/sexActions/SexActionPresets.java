package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerBreastsCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerFinger;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.FootMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisArmpit;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAss;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreastsCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisSpinneret;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisThighs;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisUrethraPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisUrethraVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TentacleAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TentacleMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TentacleNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.TentacleNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.TentacleVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueArmpit;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueBreastsCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMound;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.LovingActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PositioningMenu;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.SadisticActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerCrotchNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfNoPen;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfPenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTongueVagina;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.97
 * @version 0.3.3
 * @author Innoxia
 */
public class SexActionPresets {
	
	public static List<SexAreaInterface> groinAreas = Util.newArrayListOfValues(
			SexAreaPenetration.PENIS,
			SexAreaPenetration.CLIT,
			SexAreaOrifice.THIGHS,
			SexAreaOrifice.URETHRA_PENIS,
			SexAreaOrifice.URETHRA_VAGINA,
			SexAreaOrifice.VAGINA,
			SexAreaOrifice.NIPPLE_CROTCH,
			SexAreaOrifice.BREAST_CROTCH);

	public static List<SexAreaInterface> crotchBoobAreas = Util.newArrayListOfValues(
			SexAreaOrifice.NIPPLE_CROTCH,
			SexAreaOrifice.BREAST_CROTCH);

	public static List<SexAreaInterface> thighAreas = Util.newArrayListOfValues(
			SexAreaOrifice.THIGHS);

	public static List<SexAreaInterface> feetAreas = Util.newArrayListOfValues(
			SexAreaPenetration.FOOT);
	
	public static List<SexAreaInterface> vaginaAreas = Util.newArrayListOfValues(
			SexAreaPenetration.CLIT,
			SexAreaOrifice.URETHRA_VAGINA,
			SexAreaOrifice.VAGINA);

	public static List<SexAreaInterface> penisAreas = Util.newArrayListOfValues(
			SexAreaPenetration.CLIT, // Add clit here as clit actions are related to when the clit is a penetrative object, and should be acting like a penis (position-wise)
			SexAreaPenetration.PENIS,
			SexAreaOrifice.URETHRA_PENIS);
	
	public static List<SexAreaInterface> assAreas = Util.newArrayListOfValues(
			SexAreaOrifice.ANUS,
			SexAreaOrifice.ASS,
			SexAreaOrifice.SPINNERET);
	
	public static List<SexAreaInterface> mouthAreas = Util.newArrayListOfValues(
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.MOUTH);

	public static List<SexAreaInterface> breastAreas = Util.newArrayListOfValues(
			SexAreaOrifice.NIPPLE,
			SexAreaOrifice.BREAST,
			SexAreaOrifice.ARMPITS); // Add armpits to breast areas as they seem the closest in terms of being able to physical reach them

	public static List<SexAreaInterface> appendageAreas = Util.newArrayListOfValues(
			SexAreaPenetration.FINGER,
			SexAreaPenetration.TAIL,
			SexAreaPenetration.TENTACLE);

	public static List<SexAreaInterface> handAreas = Util.newArrayListOfValues(
			SexAreaPenetration.FINGER);

	public static List<SexAreaInterface> tailAreas = Util.newArrayListOfValues(
			SexAreaPenetration.TAIL,
			SexAreaOrifice.SPINNERET);
	
	public static List<SexAreaInterface> tailAndTentacle = Util.newArrayListOfValues(
			SexAreaPenetration.TAIL,
			SexAreaPenetration.TENTACLE);
	
	public static List<SexAreaInterface> lowerHalf = Util.newArrayListOfValues( // Populated in static block
			SexAreaPenetration.TAIL,
			SexAreaPenetration.TENTACLE);

	public static List<SexAreaInterface> upperHalf = Util.newArrayListOfValues(
			SexAreaPenetration.FINGER,
			SexAreaOrifice.NIPPLE,
			SexAreaOrifice.BREAST,
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.MOUTH);

	public static List<SexAreaInterface> allowedInterPenetrationAreas = Util.newArrayListOfValues(
			SexAreaPenetration.FOOT,
			SexAreaPenetration.FINGER,
			SexAreaPenetration.TAIL,
			SexAreaPenetration.TENTACLE,
			SexAreaOrifice.MOUTH,
			SexAreaOrifice.VAGINA);
	
	private static List<SexAreaInterface> allAreas;
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToAllAreas = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToLowerHalf = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToAss = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToUpperHalf = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToBreasts = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToCrotchBoobs = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> allAreasToAppendages = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> allAreasToTailAndTentacle = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> allAreasToLowerHalf = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> lowerHalfToAppendages = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> lowerHalfToFinger = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> lowerHalfToTailTentacle = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> upperHalfToFinger = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> upperHalfToTailTentacle = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> upperHalfToAppendages = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> crotchBoobsToAppendages = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToAppendages = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToTailAndTentacle = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToAllAreas = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToAllAreas = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToUpperTorso = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToLowerHalf = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToUpperTorso = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToLowerHalf = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToUpperTorso = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToLowerHalf = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> feetToGroin = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> feetToMouth = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToGroin = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToGroin = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToVagina = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToAss = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToMouth = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToFeet = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToCrotchBoobs = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToVagina = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToBreasts = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToAss = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToMouth = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToThighs = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToFeet = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToMouth = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToVagina = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> vaginaToVagina = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> vaginaToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> assToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> anusToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> thighsToPenis = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> vaginaToAppendages = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> assToAppendages = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> vaginaToMouth = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> assToMouth = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> assToGroin = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> kissing = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> breastsToPenis = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToBreasts = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToLowerHalf = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToAss = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToVagina = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToCrotchBoobs = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToFeet = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> breastsToMouth = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> handHolding = new HashMap<>();
	
	
	static {
		
		allAreas = Util.mergeLists(appendageAreas, groinAreas, assAreas, mouthAreas, breastAreas);

		lowerHalf.addAll(groinAreas);
		lowerHalf.addAll(thighAreas);
		lowerHalf.addAll(feetAreas);
		lowerHalf.addAll(vaginaAreas);
		lowerHalf.addAll(penisAreas);
		lowerHalf.addAll(assAreas);

		tailToAllAreas = new HashMap<>();
		tailToUpperTorso = new HashMap<>();
		tailToLowerHalf = new HashMap<>();
		
		for(SexAreaInterface tailArea : tailAreas) {
			tailToAllAreas.put(tailArea, allAreas);
			tailToUpperTorso.put(tailArea, Util.mergeLists(mouthAreas, breastAreas));
			tailToLowerHalf.put(tailArea, lowerHalf);
		}
		
		tentacleToAllAreas = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, allAreas));
		
		tentacleToUpperTorso = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, Util.mergeLists(mouthAreas, breastAreas)));
		tentacleToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, lowerHalf));
		
		fingerToUpperTorso = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, Util.mergeLists(mouthAreas, breastAreas)));
		fingerToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, lowerHalf));
		
		feetToGroin = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, groinAreas));
		
		handHolding = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, Util.mergeLists(mouthAreas, Util.newArrayListOfValues(SexAreaPenetration.FINGER))));
		
		fingerToMouth.put(SexAreaPenetration.FINGER, mouthAreas);
		fingerToPenis.put(SexAreaPenetration.FINGER, penisAreas);
		fingerToVagina.put(SexAreaPenetration.FINGER, vaginaAreas);
		anusToPenis.put(SexAreaOrifice.ANUS, penisAreas);
		
		for(SexAreaInterface area : appendageAreas) {
			appendagesToGroin.put(area, groinAreas);
			
			appendagesToAllAreas.put(area, allAreas);
			
			appendagesToBreasts.put(area, breastAreas);
			appendagesToCrotchBoobs.put(area, crotchBoobAreas);
			
			appendagesToLowerHalf.put(area, Util.mergeLists(groinAreas, lowerHalf, assAreas));
			
			appendagesToAss.put(area, assAreas);

			appendagesToUpperHalf.put(area, Util.mergeLists(mouthAreas, breastAreas));
		}

		for(SexAreaInterface area : allAreas) {
			allAreasToAppendages.put(area, appendageAreas);
			allAreasToTailAndTentacle.put(area, tailAndTentacle);
			allAreasToLowerHalf.put(area, lowerHalf);
		}
		
		for(SexAreaInterface area : lowerHalf) {
			lowerHalfToAppendages.put(area, appendageAreas);
			lowerHalfToFinger.put(area, handAreas);
			lowerHalfToTailTentacle.put(area, tailAndTentacle);
		}

		for(SexAreaInterface area : upperHalf) {
			upperHalfToFinger.put(area, handAreas);
			upperHalfToAppendages.put(area, appendageAreas);
			upperHalfToTailTentacle.put(area, tailAndTentacle);
		}
		
		for(SexAreaInterface area : crotchBoobAreas) {
			crotchBoobsToAppendages.put(area, appendageAreas);
		}
		
		for(SexAreaInterface area : groinAreas) {
			groinToGroin.put(area, groinAreas);
			groinToAss.put(area, assAreas);
			groinToPenis.put(area, penisAreas);
			groinToVagina.put(area, vaginaAreas);
			groinToMouth.put(area, mouthAreas);
			groinToFeet.put(area, feetAreas);
			groinToCrotchBoobs.put(area, crotchBoobAreas);
		}
		
		for(SexAreaInterface area : mouthAreas) {
			kissing.put(area, mouthAreas);
			mouthToAss.put(area, assAreas);
			mouthToVagina.put(area, vaginaAreas);
			mouthToPenis.put(area, penisAreas);
			mouthToCrotchBoobs.put(area, crotchBoobAreas);
			mouthToFeet.put(area, feetAreas);
			
			mouthToBreasts.put(area, breastAreas);
			
			mouthToLowerHalf.put(area, Util.mergeLists(groinAreas, lowerHalf, assAreas));
			
			mouthToAppendages.put(area, appendageAreas);
			
			mouthToTailAndTentacle.put(area, tailAndTentacle);
		}
		
		for(SexAreaInterface area : breastAreas) {
			breastsToMouth.put(area, mouthAreas);
			
			breastsToPenis.put(area, penisAreas);
		}
		for(SexAreaInterface area : thighAreas) {
			thighsToPenis.put(area, penisAreas);
		}
		for(SexAreaInterface area : feetAreas) {
			feetToMouth.put(area, mouthAreas);
		}
		
		for(SexAreaInterface area : penisAreas) {
			penisToAss.put(area, assAreas);
			penisToVagina.put(area, vaginaAreas);
			penisToBreasts.put(area, breastAreas);
			penisToMouth.put(area, mouthAreas);
			penisToPenis.put(area, penisAreas);
			penisToThighs.put(area, thighAreas);
			penisToFeet.put(area, feetAreas);
		}
		for(SexAreaInterface area : assAreas) {
			assToPenis.put(area, penisAreas);
			assToMouth.put(area, mouthAreas);
			assToGroin.put(area, groinAreas);
			assToAppendages.put(area, appendageAreas);
		}
		for(SexAreaInterface area : vaginaAreas) {
			vaginaToPenis.put(area, penisAreas);
			vaginaToMouth.put(area, mouthAreas);
			vaginaToVagina.put(area, vaginaAreas);
			vaginaToAppendages.put(area, appendageAreas);
		}
	}

	public static List<Class<?>> positioningActionsNew = new ArrayList<>();
	public static List<Class<?>> miscActions = new ArrayList<>();
	public static List<Class<?>> sexActions = new ArrayList<>();
	public static List<Class<?>> selfActions = new ArrayList<>();

	public static List<Class<?>> allCommonActions = new ArrayList<>();
	
	static {
//		positioningActions.add(GenericPositioning.class);
		positioningActionsNew.add(PositioningMenu.class);
		positioningActionsNew.add(GenericPositioning.class);
		
		miscActions.add(GenericActions.class);
		miscActions.add(GenericOrgasms.class);
		miscActions.add(PlayerTalk.class);
		miscActions.add(PartnerTalk.class);
		miscActions.add(GenericTalk.class);

		// Sadistic actions:
		sexActions.add(SadisticActions.class);

		// Gentle actions:
		sexActions.add(LovingActions.class);
		
		// Finger actions:
		sexActions.add(FingerAnus.class);
		sexActions.add(FingerBreasts.class);
		sexActions.add(FingerBreastsCrotch.class);
		sexActions.add(FingerMouth.class);
		sexActions.add(FingerNipple.class);
		sexActions.add(FingerNippleCrotch.class);
		sexActions.add(FingerVagina.class);
		sexActions.add(FingerClit.class);
		sexActions.add(FingerPenis.class);
		sexActions.add(FingerFinger.class);

		// Oral actions:
		sexActions.add(TongueMouth.class);
		sexActions.add(TongueAnus.class);
		sexActions.add(TongueVagina.class);
		sexActions.add(ClitMouth.class);
		sexActions.add(TongueMound.class);
		sexActions.add(TongueBreasts.class);
		sexActions.add(TongueBreastsCrotch.class);
		sexActions.add(TongueNipple.class);
		sexActions.add(TongueNippleCrotch.class);
		sexActions.add(FootMouth.class);
		sexActions.add(PenisMouth.class);
		sexActions.add(TongueArmpit.class);

		// Tail actions:
		sexActions.add(TailAnus.class);
		sexActions.add(TailVagina.class);
		sexActions.add(TailMouth.class);
		sexActions.add(TailNipple.class);
		sexActions.add(TailNippleCrotch.class);

		// Tentacle actions:
		sexActions.add(TentacleAnus.class);
		sexActions.add(TentacleVagina.class);
		sexActions.add(TentacleMouth.class);
		sexActions.add(TentacleNipple.class);
		sexActions.add(TentacleNippleCrotch.class);
		
		// Penis actions:
		sexActions.add(PenisAss.class);
		sexActions.add(PenisAnus.class);
		sexActions.add(PenisNipple.class);
		sexActions.add(PenisNippleCrotch.class);
		sexActions.add(PenisVagina.class);
		sexActions.add(PenisBreasts.class);
		sexActions.add(PenisBreastsCrotch.class);
		sexActions.add(PenisThighs.class);
		sexActions.add(PenisFoot.class);
		sexActions.add(PenisFeet.class);
		sexActions.add(PenisUrethraVagina.class);
		sexActions.add(PenisUrethraPenis.class);
		sexActions.add(PenisSpinneret.class);
		sexActions.add(PenisArmpit.class);
		
		// Vagina/clit actions:
		sexActions.add(ClitClit.class);
		sexActions.add(ClitVagina.class);
		sexActions.add(ClitAnus.class);
		sexActions.add(ClitNipple.class);
		sexActions.add(ClitNippleCrotch.class);
		
		// Self actions:
		selfActions.add(SelfNoPen.class);
		selfActions.add(SelfFingerAnus.class);
		selfActions.add(SelfFingerBreasts.class);
		selfActions.add(SelfFingerCrotchNipple.class);
		selfActions.add(SelfFingerMouth.class);
		selfActions.add(SelfFingerNipple.class);
		selfActions.add(SelfFingerPenis.class);
		selfActions.add(SelfFingerVagina.class);
		selfActions.add(SelfPenisAnus.class);
		selfActions.add(SelfPenisMouth.class);
		selfActions.add(SelfPenisNipple.class);
		selfActions.add(SelfPenisVagina.class);
		selfActions.add(SelfTailAnus.class);
		selfActions.add(SelfTailMouth.class);
		selfActions.add(SelfTailNipple.class);
		selfActions.add(SelfTailVagina.class);
		selfActions.add(SelfTongueAnus.class);
		selfActions.add(SelfTongueMouth.class);
		selfActions.add(SelfTongueNipple.class);
		selfActions.add(SelfTongueVagina.class);

		allCommonActions = Util.mergeLists(miscActions, sexActions, selfActions);
	}
}
