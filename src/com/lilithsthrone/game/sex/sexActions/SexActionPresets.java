package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerBreastsCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAss;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreastsCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisThighs;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisUrethraPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisUrethraVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
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
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PositioningMenu;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.SadisticActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfNoPen;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfPenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfNoPen;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfPenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTongueVagina;
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
			SexAreaPenetration.PENIS,
			SexAreaOrifice.URETHRA_PENIS);
	
	public static List<SexAreaInterface> assAreas = Util.newArrayListOfValues(
			SexAreaOrifice.ANUS,
			SexAreaOrifice.ASS);
	
	public static List<SexAreaInterface> mouthAreas = Util.newArrayListOfValues(
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.MOUTH);

	public static List<SexAreaInterface> breastAreas = Util.newArrayListOfValues(
			SexAreaOrifice.NIPPLE,
			SexAreaOrifice.BREAST);

	public static List<SexAreaInterface> appendageAreas = Util.newArrayListOfValues(
			SexAreaPenetration.FINGER,
			SexAreaPenetration.TAIL,
			SexAreaPenetration.TENTACLE);

	public static List<SexAreaInterface> handAreas = Util.newArrayListOfValues(
			SexAreaPenetration.FINGER);

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
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> upperHalfToFinger = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> upperHalfToAppendages = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> crotchBoobsToAppendages = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToAppendages = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToTailAndTentacle = new HashMap<>();
	
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToAllAreas;
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToAllAreas;
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToUpperTorso;
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToLowerHalf;
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToUpperTorso;
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToLowerHalf;
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToUpperTorso;
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToLowerHalf;
	
	
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
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> breastsToMouth = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> handHolding = new HashMap<>();
	
	
	static {
		
		allAreas = Util.mergeLists(appendageAreas, groinAreas, assAreas, mouthAreas, breastAreas);
		
		tailToAllAreas = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, allAreas));
		tentacleToAllAreas = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, allAreas));
		
		fingerToUpperTorso = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, Util.mergeLists(mouthAreas, breastAreas)));
		fingerToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, Util.mergeLists(groinAreas, lowerHalf, assAreas)));
		
		tailToUpperTorso = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, Util.mergeLists(mouthAreas, breastAreas)));
		tailToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, Util.mergeLists(groinAreas, lowerHalf, assAreas)));
		
		tentacleToUpperTorso = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, Util.mergeLists(mouthAreas, breastAreas)));
		tentacleToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, Util.mergeLists(groinAreas, lowerHalf, assAreas)));
		
		handHolding = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, Util.mergeLists(mouthAreas, Util.newArrayListOfValues(SexAreaPenetration.FINGER))));
		
		
		lowerHalf.addAll(groinAreas);
		lowerHalf.addAll(thighAreas);
		lowerHalf.addAll(feetAreas);
		lowerHalf.addAll(vaginaAreas);
		lowerHalf.addAll(penisAreas);
		lowerHalf.addAll(assAreas);
		
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
		}

		for(SexAreaInterface area : upperHalf) {
			upperHalfToFinger.put(area, handAreas);
			upperHalfToAppendages.put(area, appendageAreas);
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
			
			mouthToBreasts.put(area, breastAreas);
			
			mouthToLowerHalf.put(area, Util.mergeLists(groinAreas, lowerHalf, assAreas));
			
			mouthToAppendages.put(area, appendageAreas);
			
			mouthToTailAndTentacle.put(area, tailAndTentacle);
		}
		
		for(SexAreaInterface area : breastAreas) {
			breastsToMouth.put(area, mouthAreas);
			
			breastsToPenis.put(area, penisAreas);
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
		}
		for(SexAreaInterface area : vaginaAreas) {
			vaginaToPenis.put(area, penisAreas);
			vaginaToMouth.put(area, mouthAreas);
			vaginaToVagina.put(area, vaginaAreas);
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
		
		// Finger actions:
		sexActions.add(FingerAnus.class);
		sexActions.add(FingerBreasts.class);
		sexActions.add(FingerBreastsCrotch.class);
		sexActions.add(FingerMouth.class);
		sexActions.add(FingerNipple.class);
		sexActions.add(FingerVagina.class);
		sexActions.add(FingerClit.class);
		sexActions.add(FingerPenis.class);

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
		sexActions.add(PenisMouth.class);

		// Tail actions:
		sexActions.add(TailAnus.class);
		sexActions.add(TailVagina.class);

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
		
		// Vagina actions:
		sexActions.add(ClitClit.class);
		
//		selfActions.add(PlayerMasturbation.class);
//		selfActions.add(PartnerMasturbation.class);

		selfActions.add(PartnerSelfNoPen.class);
		selfActions.add(PartnerSelfFingerAnus.class);
		selfActions.add(PartnerSelfFingerMouth.class);
		selfActions.add(PartnerSelfFingerNipple.class);
		selfActions.add(PartnerSelfFingerVagina.class);
		selfActions.add(PartnerSelfPenisAnus.class);
		selfActions.add(PartnerSelfPenisMouth.class);
		selfActions.add(PartnerSelfPenisNipple.class);
		selfActions.add(PartnerSelfPenisVagina.class);
		selfActions.add(PartnerSelfTailAnus.class);
		selfActions.add(PartnerSelfTailMouth.class);
		selfActions.add(PartnerSelfTailNipple.class);
		selfActions.add(PartnerSelfTailVagina.class);
		selfActions.add(PartnerSelfTongueAnus.class);
		selfActions.add(PartnerSelfTongueMouth.class);
		selfActions.add(PartnerSelfTongueNipple.class);
		selfActions.add(PartnerSelfTongueVagina.class);

		selfActions.add(PlayerSelfNoPen.class);
		selfActions.add(PlayerSelfFingerAnus.class);
		selfActions.add(PlayerSelfFingerMouth.class);
		selfActions.add(PlayerSelfFingerNipple.class);
		selfActions.add(PlayerSelfFingerVagina.class);
		selfActions.add(PlayerSelfPenisAnus.class);
		selfActions.add(PlayerSelfPenisMouth.class);
		selfActions.add(PlayerSelfPenisNipple.class);
		selfActions.add(PlayerSelfPenisVagina.class);
		selfActions.add(PlayerSelfTailAnus.class);
		selfActions.add(PlayerSelfTailMouth.class);
		selfActions.add(PlayerSelfTailNipple.class);
		selfActions.add(PlayerSelfTailVagina.class);
		selfActions.add(PlayerSelfTongueAnus.class);
		selfActions.add(PlayerSelfTongueMouth.class);
		selfActions.add(PlayerSelfTongueNipple.class);
		selfActions.add(PlayerSelfTongueVagina.class);
		
		
		allCommonActions = Util.mergeLists(miscActions, sexActions, selfActions);
		
	}
}
