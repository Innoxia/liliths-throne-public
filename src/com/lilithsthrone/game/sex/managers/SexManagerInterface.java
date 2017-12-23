package com.lilithsthrone.game.sex.managers;

import java.util.List;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;

/**
 * @since 0.1.0
 * @version 0.1.86
 * @author Innoxia
 */
public interface SexManagerInterface {

	public default void initSexActions() {
	}
	
	public void addSexActionClass(Class<?> container);
	
	public SexPosition getPosition();
	
	public List<SexActionInterface> getActionsAvailablePlayer();
	public List<SexActionInterface> getActionsAvailablePartner();
	public List<SexActionInterface> getOrgasmActionsPlayer();
	public List<SexActionInterface> getOrgasmActionsPartner();
//	public List<SexActionInterface> getMutualOrgasmActions();
	
	/**
	 * @param sexActionPlayer The action that the player just took before the partner's turn.
	 * @return The action that the partner takes.
	 */
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer);
	
	public boolean isPlayerAbleToStopSex();
	
	public SexPace getStartingSexPacePlayer();
	public SexPace getStartingSexPacePartner();
	
	public boolean isPlayerCanRemoveOwnClothes();
	public boolean isPlayerCanRemovePartnersClothes();
	
	public boolean isPartnerCanRemoveOwnClothes();
	public boolean isPartnerCanRemovePlayersClothes();
	
	public default boolean isItemUseAvailable() {
		return true;
	}
	
	public default boolean isPlayerStartNaked() {
		return false;
	}

	public default boolean isPartnerStartNaked() {
		return false;
	}
	
	

	public String getStartSexDescription();
	
	// Revealing CoverableAreas:

	// Player:
	public default String getPlayerAssRevealReaction(boolean isSub) {
		return Sex.getPartner().getPlayerAssRevealReaction(isSub);
	}

	public default String getPlayerVaginaRevealReaction(boolean isSub) {
		return Sex.getPartner().getPlayerVaginaRevealReaction(isSub);
	}

	public default String getPlayerBreastsRevealReaction(boolean isSub) {
		return Sex.getPartner().getPlayerBreastsRevealReaction(isSub);
	}

	public default String getPlayerPenisRevealReaction(boolean isSub) {
		return Sex.getPartner().getPlayerPenisRevealReaction(isSub);
	}

	public default String getPlayerMoundRevealReaction(boolean isSub) {
		return Sex.getPartner().getPlayerMoundRevealReaction(isSub);
	}

	// Partner:
	public default String getPartnerAssRevealReaction(boolean isSub) {
		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.ANUS, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getPartner(), Sex.getPartner().getAssDescription())
				+ "</p>"
				+ Sex.getPartner().getPartnerAssRevealReaction(isSub);
		
		return s;
	}

	public default String getPartnerBreastsRevealReaction(boolean isSub) {
		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.NIPPLES, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getPartner(), Sex.getPartner().getBreastDescription())
				+ "</p>"
				+ Sex.getPartner().getPartnerBreastsRevealReaction(isSub);
		
		return s;
	}

	public default String getPartnerPenisRevealReaction(boolean isSub) {
		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getPartner(), Sex.getPartner().getPenisDescription())
				+ "</p>"
				+ Sex.getPartner().getPartnerPenisRevealReaction(isSub);
		
		return s;
	}

	public default String getPartnerVaginaRevealReaction(boolean isSub) {
		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.VAGINA, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getPartner(), Sex.getPartner().getVaginaDescription())
				+ "</p>"
				+ Sex.getPartner().getPartnerVaginaRevealReaction(isSub);
		
		return s;
	}

	public default String getPartnerMoundRevealReaction(boolean isSub) {
		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.VAGINA, true);
		
		String s = "<p>"
				+ UtilText.parse(Sex.getPartner(), Sex.getPartner().getMoundDescription())
				+ "</p>"
				+ Sex.getPartner().getPartnerMoundRevealReaction(isSub);
		
		return s;
	}
}
