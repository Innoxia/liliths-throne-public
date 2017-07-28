package com.base.game.sex.managers;

import java.util.List;

import com.base.game.character.npc.NPC;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.sexActions.SexActionInterface;

/**
 * @since 0.1.0
 * @version 0.1.79
 * @author Innoxia
 */
public interface SexManagerInterface {

	public default void initSexActions() {
	}
	
	public SexPosition getPosition();
	
	public List<SexActionInterface> getActionsAvailablePlayer();
	public List<SexActionInterface> getActionsAvailablePartner();
	public List<SexActionInterface> getOrgasmActionsPlayer();
	public List<SexActionInterface> getOrgasmActionsPartner();
	public List<SexActionInterface> getMutualOrgasmActions();
	
	/**
	 * @param sexActionPlayer
	 *            The action that the player just took before the partner's
	 *            turn.
	 * @return The action that the partner takes.
	 */
	public SexActionInterface getPartnerSexAction(SexActionInterface sexActionPlayer);
	
	public boolean isConsensualSex();
	public boolean isPlayerAbleToStopSex();
	
	public SexPace getStartingSexPacePlayer();
	public SexPace getStartingSexPacePartner();
	
	public boolean isPlayerCanRemoveOwnClothes();
	public boolean isPlayerCanRemovePartnersClothes();
	
	public boolean isPartnerCanRemoveOwnClothes();
	public boolean isPartnerCanRemovePlayersClothes();
	
	public default boolean isItemUseAvalable() {
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
		return ((NPC)Sex.getPartner()).getPlayerAssRevealReaction(isSub);
	}

	public default String getPlayerVaginaRevealReaction(boolean isSub) {
		return ((NPC)Sex.getPartner()).getPlayerVaginaRevealReaction(isSub);
	}

	public default String getPlayerBreastsRevealReaction(boolean isSub) {
		return ((NPC)Sex.getPartner()).getPlayerBreastsRevealReaction(isSub);
	}

	public default String getPlayerPenisRevealReaction(boolean isSub) {
		return ((NPC)Sex.getPartner()).getPlayerPenisRevealReaction(isSub);
	}

	public default String getPlayerMoundRevealReaction(boolean isSub) {
		return ((NPC)Sex.getPartner()).getPlayerMoundRevealReaction(isSub);
	}

	// Partner:
	public default String getPartnerAssRevealReaction(boolean isSub) {
		String s = "<p>"
				+ UtilText.genderParsing(Sex.getPartner(), Sex.getPartner().getAssDescription())
				+ "</p>"
				+ ((NPC) Sex.getPartner()).getPartnerAssRevealReaction(isSub);

		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.ANUS, true);
		
		return s;
	}

	public default String getPartnerBreastsRevealReaction(boolean isSub) {
		String s = "<p>"
				+ UtilText.genderParsing(Sex.getPartner(), Sex.getPartner().getBreastDescription())
				+ "</p>"
				+ ((NPC) Sex.getPartner()).getPartnerBreastsRevealReaction(isSub);

		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.NIPPLES, true);
		
		return s;
	}

	public default String getPartnerPenisRevealReaction(boolean isSub) {
		String s = "<p>"
				+ UtilText.genderParsing(Sex.getPartner(), Sex.getPartner().getPenisDescription())
				+ "</p>"
				+ ((NPC) Sex.getPartner()).getPartnerPenisRevealReaction(isSub);

		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
		
		return s;
	}

	public default String getPartnerVaginaRevealReaction(boolean isSub) {
		String s = "<p>"
				+ UtilText.genderParsing(Sex.getPartner(), Sex.getPartner().getVaginaDescription())
				+ "</p>"
				+ ((NPC) Sex.getPartner()).getPartnerVaginaRevealReaction(isSub);

		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.VAGINA, true);
		
		return s;
	}

	public default String getPartnerMoundRevealReaction(boolean isSub) {
		String s = "<p>"
				+ UtilText.genderParsing(Sex.getPartner(), Sex.getPartner().getMoundDescription())
				+ "</p>"
				+ ((NPC) Sex.getPartner()).getPartnerMoundRevealReaction(isSub);

		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
		Sex.getPartner().getPlayerKnowsAreasMap().put(CoverableArea.VAGINA, true);
		
		return s;
	}
}
