package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.4.7.8
 * @version 0.4.7.8
 * @author Innoxia
 */
public class VickySpecials {
	
	public static final SexAction VICKY_MARKING_ORGASM = new SexAction(GenericOrgasms.GENERIC_ORGASM_FLOOR) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return GenericOrgasms.isCumTargetRequirementsMet(OrgasmCumTarget.FACE)
					&& Main.game.isMuskContentEnabled()
					&& (Main.sex.getCharacterPerformingAction() instanceof Vicky);
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Handjob onto face";
				}
				return "Pull out (facial)";
			}
			return "Facial";
		}
		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer. Direct your cum onto [npc2.namePos] face.";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
//			sb.append(GenericOrgasms.getGenericOrgasmDescription(VICKY_MARKING_ORGASM, Main.sex.getCharacterPerformingAction(), OrgasmCumTarget.FACE));
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_MARKING_ORGASM"));
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			GenericOrgasms.applyGenericPullOutEffects(this, OrgasmCumTarget.FACE);
		}
		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
