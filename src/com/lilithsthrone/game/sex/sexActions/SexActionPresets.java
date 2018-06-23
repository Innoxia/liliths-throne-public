package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisAss;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisThighs;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisUrethraPenis;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisUrethraVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisAss;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisThighs;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisUrethraPenis;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisUrethraVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTongueBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTongueVagina;
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
 * @version 0.2.8
 * @author Innoxia
 */
public class SexActionPresets {
	
	public static List<SexAreaInterface> groinAreas = Util.newArrayListOfValues(
			SexAreaPenetration.PENIS,
			SexAreaPenetration.CLIT,
			SexAreaOrifice.THIGHS,
			SexAreaOrifice.URETHRA_PENIS,
			SexAreaOrifice.URETHRA_VAGINA,
			SexAreaOrifice.VAGINA);

	public static List<SexAreaInterface> thighAreas = Util.newArrayListOfValues(
			SexAreaOrifice.THIGHS);
	
	private static List<SexAreaInterface> vaginaAreas = Util.newArrayListOfValues(
			SexAreaPenetration.CLIT,
			SexAreaOrifice.URETHRA_VAGINA,
			SexAreaOrifice.VAGINA);

	private static List<SexAreaInterface> penisAreas = Util.newArrayListOfValues(
			SexAreaPenetration.PENIS,
			SexAreaOrifice.URETHRA_PENIS);
	
	private static List<SexAreaInterface> assAreas = Util.newArrayListOfValues(
			SexAreaOrifice.ANUS,
			SexAreaOrifice.ASS);
	
	public static List<SexAreaInterface> mouthAreas = Util.newArrayListOfValues(
			SexAreaPenetration.TONGUE,
			SexAreaOrifice.MOUTH);

	private static List<SexAreaInterface> breastAreas = Util.newArrayListOfValues(
			SexAreaOrifice.NIPPLE,
			SexAreaOrifice.BREAST);

	public static List<SexAreaInterface> appendageAreas = Util.newArrayListOfValues(
			SexAreaPenetration.FINGER,
			SexAreaPenetration.TAIL,
			SexAreaPenetration.TENTACLE);

	private static List<SexAreaInterface> lowerHalf = Util.newArrayListOfValues(
			SexAreaPenetration.TAIL,
			SexAreaPenetration.TENTACLE,
			SexAreaPenetration.TOES);
	
	private static List<SexAreaInterface> alLAreas = Util.mergeLists(appendageAreas, groinAreas, assAreas, mouthAreas, breastAreas);
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToAllAreas = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, Util.mergeLists(groinAreas, lowerHalf, assAreas)));
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToAllAreas = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, alLAreas));
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToAllAreas = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, alLAreas));
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToUpperTorso = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, Util.mergeLists(mouthAreas, breastAreas)));
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> fingerToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, Util.mergeLists(groinAreas, lowerHalf, assAreas)));
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToUpperTorso = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, Util.mergeLists(mouthAreas, breastAreas)));
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tailToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, Util.mergeLists(groinAreas, lowerHalf, assAreas)));
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToUpperTorso = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, Util.mergeLists(mouthAreas, breastAreas)));
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> tentacleToLowerHalf = Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, Util.mergeLists(groinAreas, lowerHalf, assAreas)));
	
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> appendagesToGroin = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToGroin = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToVagina = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> groinToAss= new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToVagina = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToBreasts = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToAss = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToMouth = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToThighs = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> vaginaToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> assToPenis = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> penisToPenis = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> vaginaToMouth = new HashMap<>();
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> assToMouth = new HashMap<>();

	public static HashMap<SexAreaInterface, List<SexAreaInterface>> kissing = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> mouthToBreasts = new HashMap<>();
	
	public static HashMap<SexAreaInterface, List<SexAreaInterface>> breastsToMouth = new HashMap<>();
	
	static {

		for(SexAreaInterface area : appendageAreas) {
			appendagesToGroin.put(area, groinAreas);
			appendagesToAllAreas.put(area, alLAreas);
		}
		
		for(SexAreaInterface area : groinAreas) {
			groinToGroin.put(area, groinAreas);
			groinToAss.put(area, assAreas);
			groinToVagina.put(area, vaginaAreas);
		}
		
		for(SexAreaInterface area : mouthAreas) {
			kissing.put(area, mouthAreas);
		}
		
		for(SexAreaInterface area : mouthAreas) {
			mouthToBreasts.put(area, breastAreas);
		}
		
		for(SexAreaInterface area : breastAreas) {
			breastsToMouth.put(area, mouthAreas);
		}
		
		for(SexAreaInterface area : penisAreas) {
			penisToAss.put(area, assAreas);
			penisToVagina.put(area, vaginaAreas);
			penisToBreasts.put(area, breastAreas);
			penisToMouth.put(area, mouthAreas);
			penisToPenis.put(area, penisAreas);
			penisToThighs.put(area, thighAreas);
		}
		for(SexAreaInterface area : assAreas) {
			assToPenis.put(area, penisAreas);
			assToMouth.put(area, mouthAreas);
		}
		for(SexAreaInterface area : vaginaAreas) {
			vaginaToPenis.put(area, penisAreas);
			vaginaToMouth.put(area, mouthAreas);
		}
	}

	public static List<Class<?>> positioningActions = new ArrayList<>();
	public static List<Class<?>> miscActions = new ArrayList<>();
	public static List<Class<?>> sexActions = new ArrayList<>();
	public static List<Class<?>> selfActions = new ArrayList<>();

	public static List<Class<?>> allCommonActions = new ArrayList<>();
	
	static {

		positioningActions.add(GenericPositioning.class);
		
		miscActions.add(GenericActions.class);
		miscActions.add(GenericOrgasms.class);
		miscActions.add(PlayerTalk.class);
		miscActions.add(PartnerTalk.class);

		
		sexActions.add(PartnerFingerAnus.class);
		sexActions.add(PartnerFingerMouth.class);
		sexActions.add(PartnerFingerNipple.class);
		sexActions.add(PartnerFingerPenis.class);
		sexActions.add(PartnerFingerVagina.class);
		sexActions.add(PartnerPenisAnus.class);
		sexActions.add(PartnerPenisAss.class);
		sexActions.add(PartnerPenisBreasts.class);
		sexActions.add(PartnerPenisMouth.class);
		sexActions.add(PartnerPenisNipple.class);
		sexActions.add(PartnerPenisThighs.class);
		sexActions.add(PartnerPenisUrethraPenis.class);
		sexActions.add(PartnerPenisUrethraVagina.class);
		sexActions.add(PartnerPenisVagina.class);
		sexActions.add(PartnerTailAnus.class);
		sexActions.add(PartnerTailMouth.class);
		sexActions.add(PartnerTailNipple.class);
		sexActions.add(PartnerTailVagina.class);
		sexActions.add(PartnerTongueAnus.class);
		sexActions.add(PartnerTongueBreasts.class);
		sexActions.add(PartnerTongueMouth.class);
		sexActions.add(PartnerTongueNipple.class);
		sexActions.add(PartnerTongueVagina.class);

		sexActions.add(PlayerFingerAnus.class);
		sexActions.add(PlayerFingerMouth.class);
		sexActions.add(PlayerFingerNipple.class);
		sexActions.add(PlayerFingerPenis.class);
		sexActions.add(PlayerFingerVagina.class);
		sexActions.add(PlayerPenisAnus.class);
		sexActions.add(PlayerPenisAss.class);
		sexActions.add(PlayerPenisBreasts.class);
		sexActions.add(PlayerPenisMouth.class);
		sexActions.add(PlayerPenisNipple.class);
		sexActions.add(PlayerPenisThighs.class);
		sexActions.add(PlayerPenisUrethraPenis.class);
		sexActions.add(PlayerPenisUrethraVagina.class);
		sexActions.add(PlayerPenisVagina.class);
		sexActions.add(PlayerTailAnus.class);
		sexActions.add(PlayerTailMouth.class);
		sexActions.add(PlayerTailNipple.class);
		sexActions.add(PlayerTailVagina.class);
		sexActions.add(PlayerTongueAnus.class);
		sexActions.add(PlayerTongueBreasts.class);
		sexActions.add(PlayerTongueMouth.class);
		sexActions.add(PlayerTongueNipple.class);
		sexActions.add(PlayerTongueVagina.class);
		
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
