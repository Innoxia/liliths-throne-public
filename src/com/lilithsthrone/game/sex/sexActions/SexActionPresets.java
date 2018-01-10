package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerUrethra;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisAss;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisThighs;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPartner.PartnerTongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerUrethra;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisAss;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisThighs;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsPlayer.PlayerTongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfNoPen;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfNoPen;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPlayer.PlayerSelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.universal.dom.DomDoggy;
import com.lilithsthrone.game.sex.sexActions.universal.sub.SubDoggy;

public class SexActionPresets {

	private static List<Class<?>> playerGenerics = new ArrayList<>();
	private static List<Class<?>> partnerGenerics = new ArrayList<>();
	
	private static List<Class<?>> playerDoggySelf = new ArrayList<>();
	private static List<Class<?>> partnerDoggySelf = new ArrayList<>();
	
	public static List<Class<?>> playerDoggyOnAllFours = new ArrayList<>();
	public static List<Class<?>> partnerDoggyOnAllFours = new ArrayList<>();
	
	public static List<Class<?>> playerDoggyBehind = new ArrayList<>();
	public static List<Class<?>> partnerDoggyBehind = new ArrayList<>();
	
	public static List<Class<?>> playerDoggyBehindOral = new ArrayList<>();
	public static List<Class<?>> partnerDoggyBehindOral = new ArrayList<>();

	public static List<Class<?>> playerDoggyInfront = new ArrayList<>();
	public static List<Class<?>> partnerDoggyInfront = new ArrayList<>();

	public static List<Class<?>> playerDoggyInfrontAnal = new ArrayList<>();
	public static List<Class<?>> partnerDoggyInfrontAnal = new ArrayList<>();
	
	static {
		
		// Generics:
		
		playerGenerics.add(GenericPositioning.class);
		playerGenerics.add(PlayerTalk.class);
		playerGenerics.add(GenericActions.class);
		playerGenerics.add(GenericOrgasms.class);
		
		partnerGenerics.add(GenericPositioning.class);
		partnerGenerics.add(PlayerTalk.class);
		partnerGenerics.add(GenericActions.class);
		partnerGenerics.add(GenericOrgasms.class);
		
		// Doggy self-actions:
		
		playerDoggySelf.add(PlayerSelfFingerAnus.class);
		playerDoggySelf.add(PlayerSelfFingerMouth.class);
		playerDoggySelf.add(PlayerSelfFingerNipple.class);
		playerDoggySelf.add(PlayerSelfFingerVagina.class);
		playerDoggySelf.add(PlayerSelfTailAnus.class);
		playerDoggySelf.add(PlayerSelfTailMouth.class);
		playerDoggySelf.add(PlayerSelfTailNipple.class);
		playerDoggySelf.add(PlayerSelfTailVagina.class);
		playerDoggySelf.add(PlayerSelfNoPen.class);
		
		partnerDoggySelf.add(PartnerSelfFingerAnus.class);
		partnerDoggySelf.add(PartnerSelfFingerMouth.class);
		partnerDoggySelf.add(PartnerSelfFingerNipple.class);
		partnerDoggySelf.add(PartnerSelfFingerVagina.class);
		partnerDoggySelf.add(PartnerSelfTailAnus.class);
		partnerDoggySelf.add(PartnerSelfTailMouth.class);
		partnerDoggySelf.add(PartnerSelfTailNipple.class);
		partnerDoggySelf.add(PartnerSelfTailVagina.class);
		partnerDoggySelf.add(PartnerSelfNoPen.class);
		
		
		// Doggy on all fours player:
		
		playerDoggyOnAllFours.addAll(playerDoggySelf);
		
		playerDoggyOnAllFours.add(SubDoggy.class);

		playerDoggyOnAllFours.addAll(playerGenerics);
		
		// Doggy on all fours partner:

		partnerDoggyOnAllFours.addAll(partnerDoggySelf);
		
		partnerDoggyOnAllFours.add(DomDoggy.class);

		partnerDoggyOnAllFours.addAll(partnerGenerics);
		
		// Doggy player behind:

		playerDoggyBehind.addAll(playerDoggySelf);
		
		playerDoggyBehind.add(PlayerFingerNipple.class);
		playerDoggyBehind.add(PlayerFingerUrethra.class);
		playerDoggyBehind.add(PlayerFingerVagina.class);
		playerDoggyBehind.add(PlayerFingerAnus.class);
		playerDoggyBehind.add(PlayerPenisVagina.class);
		playerDoggyBehind.add(PlayerPenisThighs.class);
		playerDoggyBehind.add(PlayerPenisAnus.class);
		playerDoggyBehind.add(PlayerPenisAss.class);
		playerDoggyBehind.add(PlayerTailVagina.class);
		playerDoggyBehind.add(PlayerTailAnus.class);

		playerDoggyBehind.add(PartnerTailVagina.class);
		playerDoggyBehind.add(PartnerTailAnus.class);

		playerDoggyBehind.addAll(playerGenerics);
		
		// Doggy partner behind:

		partnerDoggyBehind.addAll(partnerDoggySelf);
		
		partnerDoggyBehind.add(PartnerFingerNipple.class);
		partnerDoggyBehind.add(PartnerFingerUrethra.class);
		partnerDoggyBehind.add(PartnerFingerVagina.class);
		partnerDoggyBehind.add(PartnerFingerAnus.class);
		partnerDoggyBehind.add(PartnerPenisVagina.class);
		partnerDoggyBehind.add(PartnerPenisThighs.class);
		partnerDoggyBehind.add(PartnerPenisAnus.class);
		partnerDoggyBehind.add(PartnerPenisAss.class);
		partnerDoggyBehind.add(PartnerTailVagina.class);
		partnerDoggyBehind.add(PartnerTailAnus.class);

		partnerDoggyBehind.add(PlayerTailVagina.class);
		partnerDoggyBehind.add(PlayerTailAnus.class);

		partnerDoggyBehind.addAll(partnerGenerics);
		
		// Doggy player behind oral:

		playerDoggyBehindOral.addAll(playerDoggySelf);
		
		playerDoggyBehindOral.add(PlayerFingerUrethra.class);
		playerDoggyBehindOral.add(PlayerFingerVagina.class);
		playerDoggyBehindOral.add(PlayerFingerAnus.class);
		playerDoggyBehindOral.add(PlayerTongueVagina.class);
		playerDoggyBehindOral.add(PlayerTongueAnus.class);

		playerDoggyBehindOral.addAll(playerGenerics);
		
		// Doggy partner behind oral:

		partnerDoggyBehindOral.addAll(partnerDoggySelf);
		
		partnerDoggyBehindOral.add(PartnerFingerUrethra.class);
		partnerDoggyBehindOral.add(PartnerFingerVagina.class);
		partnerDoggyBehindOral.add(PartnerFingerAnus.class);
		partnerDoggyBehindOral.add(PartnerTongueVagina.class);
		partnerDoggyBehindOral.add(PartnerTongueAnus.class);

		partnerDoggyBehindOral.addAll(partnerGenerics);
		
		// Doggy player infront:

		playerDoggyInfront.addAll(playerDoggySelf);
		
		playerDoggyInfront.add(PlayerPenisMouth.class);
		playerDoggyInfront.add(PartnerFingerUrethra.class);
		playerDoggyInfront.add(PartnerFingerVagina.class);
		playerDoggyInfront.add(PartnerFingerAnus.class);
		playerDoggyInfront.add(PartnerTongueVagina.class);

		playerDoggyInfront.addAll(playerGenerics);
		
		// Doggy partner infront:

		partnerDoggyInfront.addAll(partnerDoggySelf);
		
		partnerDoggyInfront.add(PartnerPenisMouth.class);
		partnerDoggyInfront.add(PlayerFingerUrethra.class);
		partnerDoggyInfront.add(PlayerFingerVagina.class);
		partnerDoggyInfront.add(PlayerFingerAnus.class);
		partnerDoggyInfront.add(PlayerTongueVagina.class);

		partnerDoggyInfront.addAll(partnerGenerics);

		// Doggy player infront anal:
		
		playerDoggyInfrontAnal.addAll(playerDoggySelf);
		
		playerDoggyInfrontAnal.add(PartnerTongueAnus.class);
		playerDoggyInfrontAnal.add(PartnerFingerUrethra.class);
		playerDoggyInfrontAnal.add(PartnerFingerVagina.class);
		playerDoggyInfrontAnal.add(PartnerFingerAnus.class);

		playerDoggyInfrontAnal.addAll(playerGenerics);
		
		// Doggy partner infront anal:

		partnerDoggyInfrontAnal.addAll(partnerDoggySelf);
		
		partnerDoggyInfrontAnal.add(PlayerTongueAnus.class);
		partnerDoggyInfrontAnal.add(PlayerFingerUrethra.class);
		partnerDoggyInfrontAnal.add(PlayerFingerVagina.class);
		partnerDoggyInfrontAnal.add(PlayerFingerAnus.class);

		partnerDoggyInfrontAnal.addAll(partnerGenerics);
		
	}
}
