package com.lilithsthrone.game.sex.managers;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionNew;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 */
public interface SexManagerInterface {

	public SexPositionNew getPosition();
	
	public Map<GameCharacter, SexPositionSlot> getDominants();
	public Map<GameCharacter, SexPositionSlot> getSubmissives();
	
	public default SexPace getStartingSexPaceOverride(GameCharacter character) {
		return null;
	}
	
	public default boolean isPlayerDom() {
		return getDominants().containsKey(Main.game.getPlayer());
	}
	
	/**
	 * @param sexActionPlayer The action that the player just took before the partner's turn.
	 * @return The action that the partner takes.
	 */
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer);
	
	
	public default String getStartSexDescription() {
		return "";
	}
	
	public default boolean isPlayerAbleToStopSex() {
		return Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
	}
	
	public default boolean isPlayerCanRemoveOwnClothes(){
		return true;
	}
	
	public default boolean isPlayerCanRemovePartnersClothes(){
		return true;
	}
	
	public default boolean isPartnerCanRemoveOwnClothes(){
		return true;
	}
	
	public default boolean isPartnerCanRemovePlayersClothes(){
		return true;
	}
	
	public default boolean isItemUseAvailable() {
		return true;
	}
	
	public default boolean isPlayerStartNaked() {
		return false;
	}

	public default boolean isPartnerStartNaked() {
		return false;
	}
	
	public Map<GameCharacter, List<OrificeType>> getOrificesBannedMap();
	
	// Revealing CoverableAreas:

	// Player:
	public default String getPlayerAssRevealReaction(boolean isSub) {
		return Sex.getActivePartner().getPlayerAssRevealReaction(isSub);
	}

	public default String getPlayerVaginaRevealReaction(boolean isSub) {
		return Sex.getActivePartner().getPlayerVaginaRevealReaction(isSub);
	}

	public default String getPlayerBreastsRevealReaction(boolean isSub) {
		return Sex.getActivePartner().getPlayerBreastsRevealReaction(isSub);
	}

	public default String getPlayerPenisRevealReaction(boolean isSub) {
		return Sex.getActivePartner().getPlayerPenisRevealReaction(isSub);
	}

	public default String getPlayerMoundRevealReaction(boolean isSub) {
		return Sex.getActivePartner().getPlayerMoundRevealReaction(isSub);
	}

	// Partner:
	public default String getPartnerAssRevealReaction(boolean isSub) {
		Sex.getActivePartner().getPlayerKnowsAreasMap().put(CoverableArea.ANUS, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getActivePartner(), Sex.getActivePartner().getAssDescription())
				+ "</p>"
				+ Sex.getActivePartner().getPartnerAssRevealReaction(isSub);
		
		return s;
	}

	public default String getPartnerBreastsRevealReaction(boolean isSub) {
		Sex.getActivePartner().getPlayerKnowsAreasMap().put(CoverableArea.NIPPLES, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getActivePartner(), Sex.getActivePartner().getBreastDescription())
				+ "</p>"
				+ Sex.getActivePartner().getPartnerBreastsRevealReaction(isSub);
		
		return s;
	}

	public default String getPartnerPenisRevealReaction(boolean isSub) {
		Sex.getActivePartner().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getActivePartner(), Sex.getActivePartner().getPenisDescription())
				+ "</p>"
				+ Sex.getActivePartner().getPartnerPenisRevealReaction(isSub);
		
		return s;
	}

	public default String getPartnerVaginaRevealReaction(boolean isSub) {
		Sex.getActivePartner().getPlayerKnowsAreasMap().put(CoverableArea.VAGINA, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getActivePartner(), Sex.getActivePartner().getVaginaDescription())
				+ "</p>"
				+ Sex.getActivePartner().getPartnerVaginaRevealReaction(isSub);
		
		return s;
	}

	public default String getPartnerMoundRevealReaction(boolean isSub) {
		Sex.getActivePartner().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
		Sex.getActivePartner().getPlayerKnowsAreasMap().put(CoverableArea.VAGINA, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getActivePartner(), Sex.getActivePartner().getMoundDescription())
				+ "</p>"
				+ Sex.getActivePartner().getPartnerMoundRevealReaction(isSub);
		
		return s;
	}
}
