package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * @since 0.3.1
 * @version 0.3.8.1
 * @author Innoxia
 */
public class GenericPositioning {

	private static String getRequestTooltipText() {
		return "[style.italicsSex(This is a request, which [npc2.name] might refuse!)]";
	}
	
	public static final SexAction POSITION_SWAP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isPositionSwap() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().equals(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getInitialSexManager().isSwapPositionAllowed(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration()==Main.sex.getCharacterTargetedForSexAction(this).getLegConfiguration() // Can only swap if have same body type
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Swap with [npc2.name]";
		}

		@Override
		public String getActionDescription() {
			return "Swap position with [npc2.name].";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc2.name], you move [npc2.herHim] around and swap position with [npc2.herHim], before [npc.moaning],"
					+ " [npc.speech(It'll be more fun like this!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.swapSexPositionSlots(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static boolean checkBaseRequirements(PositioningData data, boolean request) {
		for(SexSlot slot : data.getPartnerSlots()) {
			if(!Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), slot)) {
				return false;
			}
		}
		for(SexSlot slot : data.getPerformerSlots()) {
			if(!Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterPerformingAction(), slot)) {
				return false;
			}
		}
		return Main.sex.getInitialSexManager().getAllowedSexPositions().contains(data.getPosition())
				&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
				&& !(Main.sex.getPosition() == data.getPosition()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
					&& Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
				&& data.getPosition().getMaximumSlots()>=Main.sex.getTotalParticipantCount(false)
				&& Main.sex.getTotalParticipantCount(false)<=(data.getPerformerSlots().size()+data.getPartnerSlots().size())
				&& (request
					?(Main.sex.getCharacterPerformingAction().isPlayer()
							&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL
							&& !Main.sex.isPositioningRequestBlocked(Main.sex.getCharacterPerformingAction(), data.getPosition()))
					:(Main.sex.getCharacterPerformingAction().isPlayer()
						?Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS)
						:!Main.sex.isCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterPerformingAction())
							&& ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))));
	}

	public static void setNewSexManager(PositioningData data, boolean requestAccepted) {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		List<GameCharacter> dominantSpectators = new ArrayList<>(Main.sex.getDominantSpectators());
		List<GameCharacter> submissiveSpectators = new ArrayList<>(Main.sex.getSubmissiveSpectators());
		
		GameCharacter performer = Main.sex.getCharacterPerformingAction();
		GameCharacter target = Main.sex.getTargetedPartner(performer);
		if(requestAccepted) {
			target = Main.sex.getCharacterPerformingAction();
			performer = Main.sex.getTargetedPartner(target);
		}
		
		if(Main.sex.isDom(performer)) {
			doms.remove(performer);
			dominants.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				if(i+1<data.getPerformerSlots().size()) {
					dominants.put(doms.get(i), data.getPerformerSlots().get(i+1));
				} else {
					dominantSpectators.add(doms.get(i));
				}
			}
			subs.remove(target);
			submissives.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				if(i+1<data.getPartnerSlots().size()) {
					submissives.put(subs.get(i), data.getPartnerSlots().get(i+1));
				} else {
					submissiveSpectators.add(subs.get(i));
				}
			}
			
		} else {
			doms.remove(target);
			dominants.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				if(i+1<data.getPartnerSlots().size()) {
					dominants.put(doms.get(i), data.getPartnerSlots().get(i+1));
				} else {
					dominantSpectators.add(doms.get(i));
				}
			}
			subs.remove(performer);
			submissives.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				if(i+1<data.getPerformerSlots().size()) {
					submissives.put(subs.get(i), data.getPerformerSlots().get(i+1));
				} else {
					submissiveSpectators.add(subs.get(i));
				}
			}
		}
		Main.sex.setSexManager(new SexManagerDefault(
						data.getPosition(),
						dominants,
						submissives){
				},
				dominantSpectators,
				submissiveSpectators);
		Main.sex.setPositionRequest(null);
	}

	
	
	
	//--------------- ORAL ---------------//
	
	private static List<SexSlot> generatePerformerOralData(GameCharacter performer, GameCharacter receiver) {
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		doms.remove(receiver);
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		subs.remove(receiver);

		boolean bipedalOral1 = !receiver.isTaur();
		boolean doubleReceiving = false;
		GameCharacter receiver2 = null;
		if(Main.sex.isDom(receiver)) {
			doubleReceiving = doms.size()>=1;
			if(doubleReceiving) {
				receiver2 = doms.get(0);
			}
		} else {
			doubleReceiving = subs.size()>=1;
			if(doubleReceiving) {
				receiver2 = subs.get(0);
			}
		}
		boolean bipedalOral2 = receiver2!=null?!receiver2.isTaur():false;
		
		List<SexSlot> performerSlots = new ArrayList<>();
		if(bipedalOral1) {
			performerSlots.add(SexSlotStanding.PERFORMING_ORAL);
		} else {
			if(receiver.hasPenis()
					&& (!Main.game.isInSex() || ((performer instanceof NPC) && ((NPC)performer).getCurrentSexPreference(receiver)!=null && ((NPC)performer).getCurrentSexPreference(receiver).getTargetedSexArea()==SexAreaPenetration.PENIS))) {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL);
			} else {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}
		}
		if(doubleReceiving) {
			if(bipedalOral2) {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL_TWO);
			} else {
				if(receiver2.hasPenis()) {
					performerSlots.add(SexSlotStanding.PERFORMING_ORAL_TWO);
				} else {
					performerSlots.add(SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO);
				}
			}
		}
		SexSlot[] slots = new SexSlot[] {
				SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.PERFORMING_ORAL_TWO,
				SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO};
		for(SexSlot slot : slots) {
			if(!performerSlots.contains(slot)) {
				performerSlots.add(slot);
			}
		}
		
		return performerSlots;
	}
	
	private static PositioningData generateReceivingOralData(GameCharacter performer, GameCharacter receiver) {
		return new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO),
				generatePerformerOralData(performer, receiver));
	}

	private static PositioningData generatePerformingOralData(GameCharacter performer, GameCharacter receiver) {
		return new PositioningData(
				SexPosition.STANDING,
				generatePerformerOralData(performer, receiver),
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO));
	}
	
	public static final SexAction POSITION_ORAL_RECEIVING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public String getActionTitle() {
			return "Standing receive oral";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Get [npc2.name] to perform oral on you. Once [npc2.sheHasFull] started, you can get [npc2.herHim] to switch between your front and back.";
			} else {
				return "Get [npc2.name] to perform oral on you. Once [npc2.sheHasFull] started, you can get [npc2.herHim] to switch between kneeling beneath or behind your animalistic body.";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(position)] [npc2.herHim] so that [npc2.sheIs] standing before [npc.herHim]."
								+ " Grinning down at [npc2.herHim], [npc.name] [npc.verb(order)],"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					} else {
						return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling before [npc.herHim]."
								+ " Grinning down at [npc2.herHim], [npc.name] [npc.verb(order)],"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "Wanting [npc2.name] to perform anilingus, [npc.name] [npc.verb(position)] [npc2.herHim] so that [npc2.sheIs] standing behind [npc.herHim]."
								+ " Looking back over [npc.her] shoulder, [npc.she] [npc.verb(grin)] down at [npc2.herHim], before ordering,"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					} else {
						return "Wanting [npc2.name] to perform anilingus, [npc.name] [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling behind [npc.herHim]."
								+ " Looking back over [npc.her] shoulder, [npc.she] [npc.verb(grin)] down at [npc2.herHim], before ordering,"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(position)] [npc2.herHim] so that [npc2.sheIs] standing beneath [npc.her] lower [npc.legRace]'s body."
								+ " Stepping forwards and pushing [npc.herself] against [npc2.herHim], [npc.name] [npc.verb(call)] out,"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					} else {
						return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling beneath [npc.her] lower [npc.legRace]'s body."
								+ " Stepping forwards and pushing [npc.herself] against [npc2.herHim], [npc.name] [npc.verb(call)] out,"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "Wanting [npc2.name] to perform oral on the rear part of [npc.her] lower [npc.legRace]'s body, [npc.name] [npc.verb(position)] [npc2.herHim] so that [npc2.sheIs] standing behind [npc.herHim]."
								+ " Looking back over [npc.her] shoulder, [npc.she] [npc.verb(grin)] down at [npc2.herHim], before ordering,"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					} else {
						return "Wanting [npc2.name] to perform oral on the rear part of [npc.her] lower [npc.legRace]'s body, [npc.name] [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling behind [npc.herHim]."
								+ " Looking back over [npc.her] shoulder, [npc.she] [npc.verb(grin)] down at [npc2.herHim], before ordering,"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isFeral()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ORAL_RECEIVING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO;
		}
		@Override
		public String getActionTitle() {
			return "Standing receive oral (R)";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Try and get [npc2.name] to perform oral on you. If [npc2.she] accepts, you can further request [npc2.herHim] to switch between your front and back.<br/>"
						+ getRequestTooltipText();
			} else {
				return "Try and get [npc2.name] to perform oral on you. If [npc2.she] accepts, you can further request [npc2.herHim] to switch between kneeling beneath or behind your animalistic body.<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing before [npc2.herHim]."
							+ " Gazing into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, I want you to use your mouth!)]";
				} else {
					return "Wanting [npc2.name] to perform anilingus on [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing with [npc.her] back to [npc2.herHim]."
							+ " Looking back over [npc.her] shoulder, [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, I want you to use your mouth!)]";
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] presenting the underside of [npc.her] lower [npc.legRace]'s body to [npc2.herHim]."
							+ " Gazing into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, I want you to use your mouth!)]";
				} else {
					return "Wanting [npc2.name] to perform oral on the rear part of [npc.her] lower [npc.legRace]'s body, [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] presenting [npc.her] hindquarters to [npc2.herHim]."
							+ " Gazing back over [npc.her] shoulder, [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, I want you to use your mouth!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ORAL_RECEIVING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_MOVE_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			return "Move [npc2.herHim] behind";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Get [npc2.name] to switch position so that [npc2.sheIsFull] behind you, and so able to perform oral on your [npc.asshole].";
			} else {
				return "Get [npc2.name] to switch position so that [npc2.sheIsFull] behind you, and so able to perform oral on your [npc.asshole]"+(Main.sex.getCharacterPerformingAction().hasVagina()?" and [npc.pussy]":"")+".";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "Wanting [npc2.name] to perform anilingus on [npc.herHim], [npc.name] [npc.verb(reposition)] [npc2.herHim] so that [npc2.sheIs] standing behind [npc.herHim]."
							+ " Pushing [npc.her] [npc.ass+] back against [npc2.her] [npc2.face], [npc.she] [npc.verb(order)],"
							+ " [npc.speech(Go on, put your mouth to use!)]";
				} else {
					return "Wanting [npc2.name] to perform anilingus on [npc.herHim], [npc.name] [npc.verb(reposition)] and [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling behind [npc.herHim]."
							+ " Pushing [npc.her] [npc.ass+] back against [npc2.her] [npc2.face], [npc.she] [npc.verb(order)],"
							+ " [npc.speech(Go on, put your mouth to use!)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "Wanting [npc2.name] to perform oral on the rear part of [npc.her] lower [npc.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc2.herHim] so that [npc2.sheIs] standing behind [npc.herHim]."
							+ " Pushing [npc.her] [npc.ass+] back against [npc2.her] [npc2.face], [npc.she] [npc.verb(order)],"
							+ " [npc.speech(Go on, put your mouth to use!)]";
				} else {
					return "Wanting [npc2.name] to perform oral on the rear part of [npc.her] lower [npc.legRace]'s body, [npc.name] [npc.verb(reposition)] and [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling behind [npc.herHim]."
							+ " Pushing [npc.her] [npc.ass+] back against [npc2.her] [npc2.face], [npc.she] [npc.verb(order)],"
							+ " [npc.speech(Go on, put your mouth to use!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_MOVE_IN_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Move [npc2.herHim] in front";
			} else {
				return "Move [npc2.herHim] beneath";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Get [npc2.name] to switch position so that [npc2.sheIsFull] in front of you, and so able to perform oral on your genitals.";
			} else {
				if(Main.sex.getCharacterPerformingAction().hasPenis()) {
					return "Get [npc2.name] to switch position so that [npc2.sheIsFull] kneeling beneath you, and so able to perform oral on your [npc.cock]"+(Main.sex.getCharacterPerformingAction().hasBreastsCrotch()?" and [npc.crotchBoobs]":"")+".";
				} else {
					return "Get [npc2.name] to switch position so that [npc2.sheIsFull] kneeling beneath you"+(Main.sex.getCharacterPerformingAction().hasBreastsCrotch()?", and so able to perform oral on your [npc.crotchBoobs]":"")+".";
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(reposition)] [npc2.herHim] so that [npc2.sheIs] standing in front of [npc.herHim]."
							+ " Grinning down at [npc2.herHim], [npc.name] [npc.verb(order)],"
							+ " [npc.speech(Go on, put that mouth of yours to use!)]";
				} else {
					return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(reposition)] and [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling in front of [npc.herHim]."
							+ " Grinning down at [npc2.herHim], [npc.name] [npc.verb(order)],"
							+ " [npc.speech(Go on, put that mouth of yours to use!)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "Wanting [npc2.name] to perform oral on the underside of [npc.her] lower [npc.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc2.herHim] so that [npc2.sheIs] standing beneath [npc.herHim]."
							+ " Stepping forwards and pushing [npc.herself] against [npc2.herHim], [npc.name] [npc.verb(call)] out,"
							+ " [npc.speech(Go on, put that mouth of yours to use!)]";
				} else {
					return "Wanting [npc2.name] to perform oral on the underside of [npc.her] lower [npc.legRace]'s body, [npc.name] [npc.verb(reposition)] and [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling beneath [npc.herHim]."
							+ " Stepping forwards and pushing [npc.herself] against [npc2.herHim], [npc.name] [npc.verb(call)] out,"
							+ " [npc.speech(Go on, put that mouth of yours to use!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_PERFORMING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public String getActionTitle() {
			return "Perform oral";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Get down and perform oral on [npc2.name]. Once you have started, you can switch between [npc2.her] front and back.";
			} else {
				return "Get down and perform oral on [npc2.name]. Once you have started, you can switch between kneeling beneath or behind [npc2.her] animalistic body.";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing before [npc2.herHim]."
								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(That's right, let me put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down before [npc2.herHim]."
								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(That's right, let me put my mouth to use!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing behind [npc2.herHim]."
								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(Oh yes, let me eat your ass!)]";
					} else {
						return "Wanting [npc2.name] to perform anilingus, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]."
								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(Oh yes, let me eat your ass!)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing beneath [npc2.her] lower [npc2.legRace]'s body."
								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down beneath [npc2.her] lower [npc2.legRace]'s body."
								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing behind [npc2.herHim]."
								+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]."
								+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isFeral()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ORAL_PERFORMING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO;
		}
		@Override
		public String getActionTitle() {
			return "Perform oral (R)";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Get down and try to convince [npc2.name] to let you perform oral on [npc2.herHim]. If [npc2.she] accepts, you can further request to switch between [npc2.her] front and back.<br/>"
						+ getRequestTooltipText();
			} else {
				return "Get down and try to convince [npc2.name] to let you perform oral on [npc2.herHim]. If [npc2.she] accepts, you can further request to switch between kneeling beneath or behind [npc2.her] animalistic body.<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing before [npc2.herHim]."
								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(drop)] down onto [npc.her] knees before [npc2.herHim]."
								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing behind [npc2.herHim]."
								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					} else {
						return "Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(drop)] down onto [npc.her] knees behind [npc2.herHim]."
								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing beneath [npc2.her] lower [npc2.legRace]'s body."
								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(drop)] down onto [npc.her] knees beneath [npc2.her] lower [npc2.legRace]'s body."
								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing behind [npc2.herHim]."
								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(drop)] down onto [npc.her] knees behind [npc2.herHim]."
								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)));
//			System.out.println(Main.sex.getPositionRequest().getPosition().getName()+", "+Main.sex.getPositionRequest().getPerformerSlots().get(0).getName(null)+", "+Main.sex.getPositionRequest().getPartnerSlots().get(0).getName(null));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ORAL_PERFORMING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
//	public static final SexAction POSITION_ORAL_PERFORMING_BEHIND = new SexAction(
//			SexActionType.POSITIONING,
//			ArousalIncrease.ONE_MINIMUM,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ONE_VANILLA,
//			null,
//			SexParticipantType.NORMAL) {
//		
//		@Override
//		public boolean isBaseRequirementsMet() {
//			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterTargetedForSexAction(this)), false);
//		}
//		@Override
//		public String getActionTitle() {
//			return "Perform oral (behind)";
//		}
//		@Override
//		public String getActionDescription() {
//			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
//				return "Get down behind [npc2.name], so that you're able to perform oral on [npc2.her] [npc2.asshole].";
//			} else {
//				return "Get down behind [npc2.name], so that you're able to perform oral on [npc2.her] [npc2.asshole]"+(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()?" and [npc2.pussy]":"")+".";
//			}
//		}
//		@Override
//		public String getDescription() {
//			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
//				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
//					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing before [npc2.herHim]."
//								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(That's right, let me put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down before [npc2.herHim]."
//								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(That's right, let me put my mouth to use!)]";
//					}
//				} else {
//					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing behind [npc2.herHim]."
//								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, let me eat your ass!)]";
//					} else {
//						return "Wanting [npc2.name] to perform anilingus, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]."
//								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, let me eat your ass!)]";
//					}
//				}
//				
//			} else { // Taur body:
//				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
//					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing beneath [npc2.her] lower [npc2.legRace]'s body."
//								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down beneath [npc2.her] lower [npc2.legRace]'s body."
//								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					}
//				} else {
//					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing behind [npc2.herHim]."
//								+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]."
//								+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					}
//				}
//			}
//		}
//		@Override
//		public void applyEffects() {
//			setNewSexManager(generatePerformingOralData(Main.sex.getCharacterTargetedForSexAction(this)), false);
//		}
//		@Override
//		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
//			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
//			if(characterPerformingActionFetishes) {
//				fetishes.add(Fetish.FETISH_ORAL_GIVING);
//			} else {
//				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
//			}
//			return new ArrayList<>(fetishes);
//		}
//	};
	
	public static final SexAction POSITION_PERFORMING_ORAL_MOVE_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			return "Move behind [npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "Switch position so that you are behind [npc2.name], and so able to perform oral on [npc2.her] [npc2.asshole].";
			} else {
				return "Switch position so that you are behind [npc2.name], and so able to perform oral on [npc2.her] [npc2.asshole]"+(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()?" and [npc2.pussy]":"")+".";
			}
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
					sb.append("Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] standing behind [npc2.herHim]."
							+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],");
				} else {
					sb.append("Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] and [npc.verb(drop)] down onto [npc.her] knees behind [npc2.herHim]."
							+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],");
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
					sb.append("Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] standing behind [npc2.herHim]."
							+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.verb(plead)],");
				} else {
					sb.append("Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc.herself] and [npc.verb(drop)] down onto [npc.her] knees behind [npc2.herHim]."
							+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.verb(plead)],");
				}
			}
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
				sb.append(" [npc.speech(Now to get a taste of your ass!)]");
			} else {
				sb.append(" [npc.speech(Please, let me put my mouth to use!)]");
			}
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterPerformingAction();
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_PERFORMING_ORAL_MOVE_IN_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "Move in front";
			} else {
				return "Move beneath [npc2.herHim]";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "Move around in front of [npc2.name] so that you're able to perform oral on [npc2.her] genitals.";
			} else {
				if(Main.sex.getCharacterTargetedForSexAction(this).hasPenis()) {
					return "Kneel down and shuffle forwards beneath [npc2.namePos] feral [npc2.legRaceFeral] body so that you're able to perform oral on [npc2.her] [npc2.cock]"
								+(Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()?" and [npc2.crotchBoobs]":"")+".";
				} else {
					return "Kneel down and shuffle forwards beneath [npc2.namePos] feral [npc2.legRaceFeral] body"
								+(Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()?" so that you're able to perform oral on [npc2.her] [npc2.crotchBoobs]":"")+".";
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
					return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] standing in front of [npc2.herHim]."
							+ " Gazing up into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				} else {
					return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] and [npc.verb(drop)] down onto [npc.her] knees in front of [npc2.herHim]."
							+ " Gazing up into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
					return "Wanting to perform oral on the underside of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] standing beneath [npc2.herHim]."
							+ " Placing [npc.a_hand] on one of [npc2.her] rear [npc2.legs], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				} else {
					return "Wanting to perform oral on the underside of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc.herself] and [npc.verb(drop)] down onto [npc.her] knees beneath [npc2.herHim]."
							+ " Placing [npc.a_hand] on one of [npc2.her] rear [npc2.legs], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterPerformingAction();
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	

	
	
	//--------------- AGAINST WALL ---------------//

	public static final SexAction POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Face-to-[pc.wall]";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] up against a nearby [pc.wall].";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] up against a nearby [pc.wall]."
					+ " Grinding [npc.her] body up against [npc2.her] back, [npc.she] [npc.moansVerb] into [npc2.her] [npc2.ear],"
					+ " [npc.speech(Good [npc2.girl]! Now hold still while I fuck you!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Face-to-[pc.wall] (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try and move into a position so that you're facing a nearby [pc.wall].<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against a nearby [pc.wall]."
					+ " Placing your hands up against the solid surface that's now in front of you, you push your [npc.ass+] out, shaking it at [npc2.name] as you try to encourage [npc2.herHim] to fuck you like this.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Back-to-[pc.wall]";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] back against a nearby [pc.wall].";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] back against a nearby [pc.wall]."
					+ " Grinding [npc.her] body up against [npc2.hers], [npc.she] [npc.moansVerb] into [npc2.her] [npc2.ear], "
					+ "[npc.speech(Good [npc2.girl]! Now hold still while I fuck you!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Back-to-[pc.wall] (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try and move into a position so that your back is up against a nearby [pc.wall].<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Before [npc2.name] can react, you quickly move up against a nearby [pc.wall]."
					+ " Leaning back against the solid surface that's now behind you, you give [npc2.name] your most seductive look, trying to encourage [npc2.herHim] to fuck you like this.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	
	
	
	
	//--------------- LYING DOWN ---------------//
	
	public static final SexAction POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Missionary";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and kneel between [npc2.her] [npc2.legs], ready to have sex in the missionary position.";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
						+ " Kneeling down between [npc2.her] [npc2.legs], [npc.she] [npc.moansVerb] as [npc.she] [npc.verb(look)] down into [npc2.her] [npc2.eyes+],"
						+ " [npc.speech(That's right, spread your legs for me...)]";
			} else {
				return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
						+ " Kneeling down over [npc2.her] groin, [npc.she] [npc.moansVerb] as [npc.she] [npc.verb(look)] down into [npc2.her] [npc2.eyes+],"
						+ " [npc.speech(That's right, present yourself to me...)]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Missionary (R)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Try to get [npc2.name] to lie down on [npc2.her] back and spread [npc2.her] [npc2.legs] so that you can have sex with [npc2.herHim] in the missionary position.<br/>"
							+ getRequestTooltipText();
			} else {
				return "Try to get [npc2.name] to lie down on [npc2.her] back and present you with [npc2.her] groin so that you can have sex with [npc2.herHim] in the missionary position.<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			return "You reach up to take hold of [npc2.namePos] shoulders, and, pushing down, you try to get [npc2.herHim] to lie down on [npc2.her] back.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Missionary (on back)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Lie down on your back and spread your [npc.legs], ready to have sex with [npc2.name] in the missionary position.";
			} else {
				return "Lie down on your back and present your groin, ready to have sex with [npc2.name] in the missionary position.";
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] knees."
						+ " Kneeling down before [npc2.herHim], [npc.she] then [npc.verb(lie)] down on [npc.her] back, spreading [npc.her] [npc.legs] and looking up into [npc2.namePos] [npc2.eyes+] as [npc.she] [npc.moanVerb],"
						+ " [npc.speech(Come and take me!)]";
			} else {
				return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] knees."
						+ " Kneeling down before [npc2.herHim], [npc.she] then [npc.verb(lie)] down on [npc.her] back, presenting [npc.her] groin and looking up into [npc2.namePos] [npc2.eyes+] as [npc.she] [npc.moanVerb],"
						+ " [npc.speech(Come and take me!)]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Missionary (on back) (R)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Lie down on your back and spread your [npc.legs] to try and encourage [npc2.name] to have sex with you in the missionary position.<br/>"
							+ getRequestTooltipText();
			} else {
				return "Lie down on your back and present your groin to try and encourage [npc2.name] to have sex with you in the missionary position.<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "[npc.Name] [npc.verb(lie)] down on [npc.her] back before [npc2.name],"
						+ " letting out a little [npc.moan] as [npc.she] [npc.verb(spread)] [npc.her] [npc.legs] to try and encourage [npc2.name] to have sex with [npc.herHim] in the missionary position.";
			} else {
				return "[npc.Name] [npc.verb(lie)] down on [npc.her] back before [npc2.name],"
						+ " letting out a little [npc.moan] as [npc.she] [npc.verb(present)] [npc.her] groin to try and encourage [npc2.name] to have sex with [npc.herHim] in the missionary position.";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Sixty-nine (top)";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and straddle [npc2.her] face, in the sixty-nine position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
					+ " Quickly lowering [npc.herself] down onto all fours over the top of [npc2.herHim],"
						+ " [npc.she] [npc.verb(drop)] [npc.her] crotch down over [npc2.her] face as [npc.she] similarly [npc.verb(position)] [npc.her] own head over [npc2.her] groin."
					+ " Looking back beneath [npc.herHim], [npc.name] [npc.moansVerb], "
					+ "[npc.speech(Good [npc2.girl]! Now let's have some fun!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Sixty-nine (top) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try and push [npc2.name] down onto [npc2.her] back so that you can sixty-nine with [npc2.herHim].<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Reaching up to take hold of [npc2.namePos] shoulders, you put on the most enticing look you can muster as you try to push [npc2.herHim] down, pleading,"
					+ " [npc.speech(I want to sixty-nine with you... Please!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SIXTY_NINE_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
						|| !Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive());
		}
		@Override
		public String getActionTitle() {
			return "Sixty-nine (bottom)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and let [npc2.name] straddle your face, in the sixty-nine position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(tug)] [npc2.herHim] down on top of [npc.herHim] as [npc.she] [npc.verb(lie)] down on [npc.her] back."
					+ " Pulling [npc2.her] [npc2.hips] back so that [npc2.sheIs] in a reversed all-fours position over the top of [npc.herHim], [npc.name] [npc.verb(look)] up at [npc2.her] crotch hovering over [npc.her] face, and [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! It's time for some oral fun!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_SIXTY_NINE_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Sixty-nine (bottom) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and ask [npc2.name] to sixty-nine with you.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Sinking down to lie on your back, you put on the most enticing look you can muster as you plead, "
					+ "[npc.speech(I want to sixty-nine with you... Please!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_COW_GIRL_RIDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Cowgirl (riding)";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and straddle [npc2.her] groin, in the cow-girl position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
					+ " [npc.She] then lowers [npc.herself] down on top of [npc2.herHim], bringing [npc.her] crotch down to bump against [npc2.hers] as [npc.she] [npc.verb(straddle)] [npc2.herHim] in the cowgirl position."
					+ " Once [npc.sheHas] made [npc.herself] comfortable, [npc.she] [npc.verb(grin)] down at [npc2.name] and [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! It's time to give you a ride!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};

	public static final SexAction REQUEST_POSITION_COWGIRL_RIDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Cowgirl (riding) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to push [npc2.name] down onto [npc2.her] back so that you can straddle [npc2.herHim] in the cowgirl position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Lifting your [npc.arms], you take hold of [npc2.namePos] shoulders, and with a little pressure, try to get [npc2.herHim] to drop down onto [npc2.her] back as you plead, "
					+ "[npc.speech(Let me ride you... Please!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};

	public static final SexAction POSITION_COW_GIRL_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Cowgirl (bottom)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down onto your back and get [npc2.name] to straddle your groin, in the cow-girl position.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(pull)] [npc2.herHim] down with [npc.herHim] as [npc.she] [npc.verb(lie)] down on [npc.her] back."
					+ " With a firm grip on [npc2.namePos] [npc2.hips], [npc.she] [npc.verb(push)] [npc2.herHim] back so that [npc2.sheIs] straddling [npc.herHim] in the cowgirl position."
					+ " Once [npc.sheHas] made [npc.herself] comfortable, [npc.she] [npc.verb(grin)] up at [npc2.name] and [npc.moansVerb],"
					+ " [npc.speech(Good [npc2.girl]! It's time for you to have a ride!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};

	public static final SexAction REQUEST_POSITION_COWGIRL_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Cowgirl (bottom) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and ask [npc2.name] to straddle you in the cowgirl position.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Dropping down and quickly lying on your back, you put on the most enticing look you can muster as you plead, "
					+ "[npc.speech(Come and ride me... Please!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Sit on face";
		}
		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back and sit on [npc2.her] face.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back."
					+ " [npc.She] then [npc.verb(lower)] [npc.herself] down over the top of [npc2.herHim], such that [npc.her] crotch is hovering just above [npc2.her] [npc2.face]."
					+ " Once [npc.sheHas] made [npc.herself] comfortable, [npc.she] [npc.verb(allow)] [npc.her] [npc.legs] to give way, firmly planting [npc.her] groin down against [npc2.namePos] mouth.";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Sit on face (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to push [npc2.name] down onto [npc2.her] back so that you can sit on [npc2.her] face.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Reaching up to take hold of [npc2.namePos] shoulders, you try to push [npc2.herHim] down onto [npc2.her] back, pleading, "
					+ "[npc.speech(Please, let me sit on your face!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Face sitting";
		}
		@Override
		public String getActionDescription() {
			return "Lie down on your back and get [npc2.name] to sit on your face.";
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(pull)] [npc2.herHim] down with [npc.herHim] as [npc.she] [npc.verb(lie)] down on [npc.her] back."
					+ " Reaching around to grab [npc2.her] thighs, [npc.she] then [npc.verb(pull)] [npc2.herHim] down on top of [npc.herHim], so that [npc2.her] crotch is hovering just over [npc.her] [npc.face]."
					+ " At that moment, [npc2.namePos] [npc2.legs] suddenly give way, causing [npc2.herHim] to firmly plant [npc2.her] groin down against [npc.namePos] mouth.";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Face sitting (R)";
		}
		@Override
		public String getActionDescription() {
			return "Try to lie down on your back so that [npc2.name] can sit on your face.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Taking hold of [npc2.namePos] [npc2.hands], you move to lie down on your back, pleading, "
					+ "[npc.speech(Please, sit on my face!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};

	
	public static final SexAction POSITION_MATING_PRESS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasPenis()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()) {
				return SexActionPriority.HIGH;
			} else {
				return SexActionPriority.NORMAL;
			}
		}
		@Override
		public String getActionTitle() {
			return "Mating press";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Force [npc2.name] down onto [npc2.her] back, push [npc2.her] [npc2.legs] apart and up towards [npc2.her] head, and then lie down on top of [npc2.herHim], in the 'mating press' position.";
			} else {
				return "Force [npc2.name] down onto [npc2.her] back, then lie down on top of [npc2.herHim], in the 'mating press' position.";
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto the ground, forcing [npc2.herHim] to lie on [npc2.her] back."
						+ " Grabbing [npc2.her] [npc2.legs], [npc.name] [npc.verb(push)] them apart and back up towards [npc2.her] head, before lying down on top of [npc2.herHim] and bumping [npc.her] groin against [npc2.hers]."
						+ " Pinning [npc2.namePos] wrists to the floor on either side of [npc2.her] head, [npc.name] [npc.moansVerb],"
						+ " [npc.speech(It's time to"
						+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this),
								"#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN give you a good hard fuck!#ELSE breed you!#ENDIF")
						+ ")]";
			} else {
				return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto the ground, forcing [npc2.herHim] to lie on [npc2.her] back."
						+ " Quickly lying down on top of [npc2.herHim], [npc.she] [npc.verb(bump)] [npc.her] groin against [npc2.hers], before pinning [npc2.namePos] wrists to the floor on either side of [npc2.her] head and [npc.moaning],"
						+ " [npc.speech(It's time to"
						+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this),
								"#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN give you a good hard fuck!#ELSE breed you!#ENDIF")
						+ ")]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_MATING_PRESS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Mating pressed (R)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Get down on your back and pull your [npc.legs] apart and back up towards your head, presenting yourself to be mounted and bred in the 'mating press' position.<br/>"
							+ getRequestTooltipText();
			} else {
				return "Get down on your back and present your groin to [npc2.name] so that you're ready to be mounted and bred in the 'mating press' position.<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Before [npc2.name] can react, you quickly drop down onto your back, before spreading your [npc.legs] and pulling them back up towards your head."
						+ " Presenting your groin to [npc2.name], you [npc.moansVerb],"
						+ " [npc.speech(Pin me down and"
						+ UtilText.parse(Main.sex.getCharacterPerformingAction(), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN fuck me!#ELSE breed me!#ENDIF")
						+ ")]";
			} else {
				return "Before [npc2.name] can react, you quickly drop down onto your back, before presenting your groin to [npc2.name] and [npc.moaning],"
						+ " [npc.speech(Pin me down and"
						+ UtilText.parse(Main.sex.getCharacterPerformingAction(), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN fuck me!#ELSE breed me!#ENDIF")
						+ ")]";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	
	
	//--------------- ALL FOURS ---------------//
	
	private static List<SexSlot> generatePerformerAllFoursData(GameCharacter receiver) {
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		doms.remove(receiver);
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		subs.remove(receiver);

		boolean doubleReceiving = false;
		if(Main.sex.isDom(receiver)) {
			doubleReceiving = doms.size()>=1;
		} else {
			doubleReceiving = subs.size()>=1;
		}
		List<SexSlot> performerSlots = new ArrayList<>();
		performerSlots.add(SexSlotAllFours.BEHIND);
		if(doubleReceiving) {
			performerSlots.add(SexSlotAllFours.BEHIND_TWO);
		}
		SexSlot[] slots = new SexSlot[] {
				SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
				SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.IN_FRONT_TWO};
		for(SexSlot slot : slots) {
			if(!performerSlots.contains(slot)) {
				performerSlots.add(slot);
			}
		}
		
		return performerSlots;
	}
	
	private static PositioningData generateReceivingAllFoursData(GameCharacter receiver) {
		return new PositioningData(
				SexPosition.ALL_FOURS,
				Util.newArrayListOfValues(
						SexSlotAllFours.ALL_FOURS,
						SexSlotAllFours.ALL_FOURS_TWO),
				generatePerformerAllFoursData(receiver));
	}

	private static PositioningData generatePerformingAllFoursData(GameCharacter receiver) {
		return new PositioningData(
				SexPosition.ALL_FOURS,
				generatePerformerAllFoursData(receiver),
				Util.newArrayListOfValues(
						SexSlotAllFours.ALL_FOURS,
						SexSlotAllFours.ALL_FOURS_TWO));
	}
	
	private static boolean isAllFoursAvailable(GameCharacter gettingFucked) {
		switch(gettingFucked.getLegConfiguration()) {
			case ARACHNID:
			case TAIL:
			case TAIL_LONG:
				return false;
			case BIPEDAL:
			case CEPHALOPOD:
			case QUADRUPEDAL:
			case AVIAN:
			case WINGED_BIPED:
				return true;
		}
		return true;
	}
	
	public static final SexAction POSITION_ALL_FOURS_GETTING_FUCKED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), false)
					&& isAllFoursAvailable(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Doggy-style fucked";
				} else {
					return "Get fucked";
				}
			} else {
				return "Get mounted";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Drop down onto all fours so that [npc2.name] can fuck you, doggy-style.";
				} else {
					return "Drop down onto all fours so that [npc2.name] can mount and rut you.";
				}
			} else {
				return "Present your hindquarters to [npc2.name] and get [npc2.herHim] to mount your animalistic body.";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Wanting to get fucked by [npc2.name] in the doggy-style position, [npc.name] [npc.verb(drop)] down onto all fours before [npc2.herHim]."
							+ " Crawling back and pushing [npc.her] [npc.ass+] against [npc2.namePos] groin, [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.moansVerb],"
							+ " [npc.speech(Come on, fuck me like an animal!)]";
				} else {
					return "Wanting [npc2.name] to mount [npc.herHim] and start rutting [npc.herHim] like an animal, [npc.name] [npc.verb(drop)] down onto all fours before [npc2.herHim]."
							+ " Crawling back so that [npc.sheIs] under [npc2.namePos] feral [npc2.legRace]'s body, [npc.she] [npc.verb(raise)] [npc.her] [npc.hips+] and [npc.moansVerb],"
							+ " [npc.speech(Come on, mount me already!)]";
				}
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Wanting [npc2.name] to fuck [npc.herHim], [npc.name] [npc.verb(turn)] around and [npc.verb(present)] the rear end of [npc.her] feral [npc.legRace]'s body to [npc2.herHim]."
							+ " Stepping back and pushing [npc.her] [npc.ass+] against [npc2.namePos] groin, [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.moansVerb],"
							+ " [npc.speech(Come on, fuck me like an animal!)]";
				} else {
					return "Wanting [npc2.name] to mount [npc.herHim] and start rutting [npc.herHim], [npc.name] [npc.verb(present)] the rear end of [npc.her] feral [npc.legRace]'s body to [npc2.herHim]."
							+ " Stepping back and pushing [npc.her] [npc.ass+] against [npc2.name], [npc.she] [npc.verb(force)] [npc2.herHim] to rear up and mount [npc.herHim]."
							+ " Looking back over [npc.her] shoulder, [npc.she] [npc.moansVerb],"
							+ " [npc.speech(That's right, fuck me like an animal!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				if(characterPerformingAction.hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				fetishes.add(Fetish.FETISH_SUBMISSIVE);
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_RECEIVING);
				}
			} else {
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
				fetishes.add(Fetish.FETISH_DOMINANT);
				if(characterPerformingAction.hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_GIVING);
				}
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ALL_FOURS_GETTING_FUCKED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.ALL_FOURS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.ALL_FOURS_TWO
					&& isAllFoursAvailable(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Doggy-style fucked (R)";
				} else {
					return "Get fucked (R)";
				}
			} else {
				return "Get mounted (R)";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Drop down onto all fours and ask [npc2.name] to fuck you, doggy-style.<br/>"
						+ getRequestTooltipText();
				} else {
					return "Drop down onto all fours and present yourself to [npc2.name] in the hopes that [npc2.she] wants to mount and rut you.<br/>"
						+ getRequestTooltipText();
				}
			} else {
				return "Present your hindquarters to [npc2.name], in the hopes that [npc2.she] wants to mount your animalistic body and fuck you.<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Wanting to get fucked by [npc2.name] in the doggy-style position, [npc.name] [npc.verb(drop)] down onto all fours before [npc2.herHim]."
							+ " Crawling back and pushing [npc.her] [npc.ass+] against [npc2.namePos] groin, [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.verb(plead)],"
							+ " [npc.speech(Please, fuck me like an animal!)]";
				} else {
					return "Wanting [npc2.name] to mount [npc.herHim] and start rutting [npc.herHim] like an animal, [npc.name] [npc.verb(drop)] down onto all fours before [npc2.herHim]."
							+ " Crawling back so that [npc.sheIs] under [npc2.namePos] feral [npc2.legRace]'s body, [npc.she] [npc.verb(raise)] [npc.her] [npc.hips+] and [npc.verb(plead)],"
							+ " [npc.speech(Please, fuck me like an animal!)]";
				}
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Wanting [npc2.name] to fuck [npc.herHim], [npc.name] [npc.verb(turn)] around and [npc.verb(present)] the rear end of [npc.her] feral [npc.legRace]'s body to [npc2.herHim]."
							+ " Stepping back and pushing [npc.her] [npc.ass+] against [npc2.namePos] groin, [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.verb(plead)],"
							+ " [npc.speech(Please, fuck me like an animal!)]";
				} else {
					return "Wanting [npc2.name] to mount [npc.herHim] and start rutting [npc.herHim] like an animal, [npc.name] [npc.verb(present)] the rear end of [npc.her] feral [npc.legRace]'s body to [npc2.herHim]."
							+ " Stepping back and pushing [npc.her] [npc.ass+] against [npc2.name], [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.verb(plead)],"
							+ " [npc.speech(Please, fuck me like an animal!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ALL_FOURS_GETTING_FUCKED.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
	public static final SexAction POSITION_ALL_FOURS_FUCKING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), false)
					&& isAllFoursAvailable(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Doggy-style [npc2.herHim]";
				} else {
					return "Fuck [npc2.herHim]";
				}
			} else {
				return "Mount [npc2.herHim]";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Make [npc2.name] get down on all fours so that you can fuck [npc2.herHim], doggy-style.";
				} else {
					return "Make [npc2.name] present [npc2.herself] to you so that you can fuck [npc2.herHim].";
				}
			} else {
				return "Make [npc2.name] present [npc2.her] hindquarters to you so that you can mount [npc2.herHim].";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Wanting to fuck [npc2.name] in the doggy-style position, [npc.name] [npc.verb(push)] [npc2.herHim] down onto all fours before [npc.herHim]."
							+ " Kneeling down behind [npc2.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips+] and [npc.verb(pull)] [npc2.her] [npc2.ass+] back against [npc.her] groin, [npc.moaning],"
							+ " [npc.speech(Time to fuck you like an animal!)]";
				} else {
					return "Wanting to mount [npc2.name] and start rutting [npc2.herHim] like an animal, [npc.name] [npc.verb(push)] [npc2.herHim] down onto all fours before [npc.herHim]."
							+ " Stepping over [npc2.herHim] so that [npc2.sheIs] under [npc.her] feral [npc.legRace]'s body, [npc.she] [npc.verb(bend)] [npc.her] [npc.legs] a little and [npc.moansVerb],"
							+ " [npc.speech(Oh yes, now to fuck you like an animal!)]";
				}
				
			} else {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Wanting to fuck [npc2.name], [npc.name] [npc.verb(move)] around behind [npc2.her] feral [npc2.legRace]'s body."
							+ " With [npc2.her] rear end now presented to [npc.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips] and [npc.verb(push)] [npc.her] groin against [npc2.her] [npc2.ass+], [npc.moaning],"
							+ " [npc.speech(Time to fuck you like an animal!)]";
				} else {
					return "Wanting to mount [npc2.name] and start rutting [npc2.herHim] like an animal, [npc.name] [npc.verb(move)] around behind [npc2.her] feral [npc2.legRace]'s body."
							+ " With [npc2.her] rear end now presented to [npc.herHim], [npc.she] [npc.verb(jolt)] forwards and [npc.verb(rear)] up, mounting [npc2.herHim] in one swift movement."
							+ " [npc.speech(Oh yes, now to fuck you like an animal!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
				fetishes.add(Fetish.FETISH_DOMINANT);
				if(characterPerformingAction.hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_GIVING);
				}
			} else {
				if(characterPerformingAction.hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				fetishes.add(Fetish.FETISH_SUBMISSIVE);
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_RECEIVING);
				}
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ALL_FOURS_FUCKING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.BEHIND
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.BEHIND_TWO
					&& isAllFoursAvailable(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Doggy-style [npc2.herHim] (R)";
				} else {
					return "Fuck [npc2.herHim] (R)";
				}
			} else {
				return "Mount [npc2.herHim] (R)";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Try to get [npc2.name] down on all fours so that you can fuck [npc2.herHim], doggy-style.<br/>"
						+ getRequestTooltipText();
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Try to get [npc2.name] down on all fours so that you can mount [npc2.herHim].<br/>"
						+ getRequestTooltipText();
				} else {
					return "Try to get [npc2.name] to present [npc2.her] hindquarters to you so that you can mount [npc2.herHim].<br/>"
						+ getRequestTooltipText();
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Wanting to fuck [npc2.name] in the doggy-style position, [npc.name] [npc.verb(try)] to push [npc2.herHim] down onto all fours before [npc.herHim]."
							+ " Gripping [npc2.her] shoulders and exerting a downwards pressure, [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, get down and let me fuck you like an animal!)]";
				} else {
					return "Wanting to mount [npc2.name] and start rutting [npc2.herHim] like an animal, [npc.name] [npc.verb(try)] to push [npc2.herHim] down onto all fours before [npc.herHim]."
							+ " Gripping [npc2.her] shoulders and exerting a downwards pressure, [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, get down and let me fuck you like an animal!)]";
				}
				
			} else {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Wanting to fuck [npc2.name], [npc.name] [npc.verb(move)] around behind [npc2.her] feral [npc2.legRace]'s body."
							+ " With [npc2.her] rear end now presented to [npc.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips] and [npc.verb(plead)],"
							+ " [npc.speech(Please, let me fuck you like an animal!)]";
				} else {
					return "Wanting to mount [npc2.name] and start rutting [npc2.herHim] like an animal, [npc.name] [npc.verb(move)] around behind [npc2.her] feral [npc2.legRace]'s body."
							+ " With [npc2.her] rear end now presented to [npc.herHim], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me mount you!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ALL_FOURS_FUCKING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};

	
	public static final SexAction SWITCH_TO_SITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING),
				Util.newArrayListOfValues(
						SexSlotSitting.PERFORMING_ORAL,
						SexSlotSitting.PERFORMING_ORAL_TWO,
						SexSlotSitting.PERFORMING_ORAL_THREE));

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Switch to sitting";
		}
		@Override
		public String getActionDescription() {
			return "Sit down on a nearby surface, with [npc2.name] kneeling before you, ready to perform oral.";
		}
		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to switch into a different position, [npc.name] [npc.verb(get)] [npc2.name] to kneel down before a nearby raised surface."
					+ " Sitting down in front of [npc2.herHim], [npc.name] [npc.moansVerb], "
					+ "[npc.speech(Yes... This is more like it...)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction SWITCH_TO_SITTING_TAUR = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING,
						SexSlotSitting.PERFORMING_ORAL_TWO,
						SexSlotSitting.PERFORMING_ORAL_THREE));

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isTaur()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Switch to sitting";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to sit down on a nearby surface, before stepping over [npc2.herHim] with your lower animalistic body, ready to start fucking [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to switch into a different position, [npc.name] [npc.verb(get)] [npc2.name] to sit down on a nearby raised surface."
					+ " Stepping up over the top of [npc.her] partner, [npc.name] [npc.moansVerb], "
					+ "[npc.speech(Yes... It should be fun fucking you like this...)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	
	public static final SexAction PARTNER_POSITION_RESPONSE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPositionRequest()!=null
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Respond to positioning request";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			boolean isHappy = ((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Main.sex.getPositionRequest().getPosition(),
					Main.sex.getPositionRequest().getPartnerSlots().get(0),
					Main.sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer());
			
			if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL) {
				if(isHappy) {
					boolean standing = SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction());
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(Don't think that this means you're the one in charge!)]"
									+ " [npc.name] growls, before "+(standing?"stepping forwards":"kneeling down")+" and bringing [npc.her] [npc.face] to your groin."
									+ " Reaching up to roughly grab hold of your [pc.hips], [npc.she] looks up into your [pc.eyes] and snarls,"
									+ " [npc.speech(Just stay still and be thankful that I wanted to do this!)]";
						default:
							return "[npc.speech(Oh, yeah, that'd be fun!)]"
									+ " [npc.name] happily replies, before "+(standing?"stepping forwards":"kneeling down")+" and bringing [npc.her] [npc.face] to your groin."
									+ " Reaching up to take hold of your [pc.hips], [npc.she] looks up into your [pc.eyes] and [npc.moansVerb],"
									+ " [npc.speech(Now stay still and enjoy this!)]";
					}
					
				} else {
					return "Outright refusing to do as you ask, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(I'm <i>not</i> performing oral on you! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL_BEHIND) {
				if(isHappy) {
					boolean standing = SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction());
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(Don't think that this means you're the one in charge!)]"
									+ " [npc.name] growls, before "+(standing?"stepping forwards":"kneeling down")+" and bringing [npc.her] [npc.face] to your [pc.ass+]."
									+ " Reaching up to roughly grab hold of your [pc.hips], [npc.she] snarls,"
									+ " [npc.speech(Just stay still and be thankful that I wanted to do this!)]";
						default:
							return "[npc.speech(Oh, yeah, that'd be fun!)]"
									+ " [npc.name] happily replies, before "+(standing?"stepping forwards":"kneeling down")+" and bringing [npc.her] [npc.face] to your [pc.ass+]."
									+ " Reaching up to take hold of your [pc.hips], [npc.she] [npc.moansVerb],"
									+ " [npc.speech(Now stay still and enjoy this!)]";
					}
					
				} else {
					return "Outright refusing to do as you ask, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(I'm <i>not</i> performing oral on you! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.STANDING_DOMINANT
					&& (Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL || Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL_BEHIND)) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(Don't think that this means you're the one in charge!)]"
									+ " [npc.name] growls, before roughly pressing "+(!biped?"[npc.her] feral [npc.legRace] body":"[npc.herself]")+" against your [pc.face]."
									+ " Bucking [npc.her] [pc.hips], [npc.she] snarls,"
									+ " [npc.speech(Just get to it already, and be thankful that I wanted you to do this!)]";
						default:
							return "[npc.speech(Oh, yeah, that'd be fun!)]"
									+ " [npc.name] happily replies, before pressing "+(!biped?"[npc.her] feral [npc.legRace] body":"[npc.herself]")+" against your [pc.face]."
									+ " Bucking [npc.her] [pc.hips], [npc.she] [npc.moansVerb],"
									+ " [npc.speech(Go on then, get to it!)]";
					}
					
				} else {
					return "Outright refusing to do as you ask, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(I'm <i>not</i> interested in having you perform oral on me! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAllFours.ALL_FOURS) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					boolean bipedPlayer = !Main.game.getPlayer().isTaur();
					boolean standingPlayer = SexSlotAllFours.BEHIND.isStanding(Main.game.getPlayer());
					StringBuilder sb = new StringBuilder();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							sb.append("[npc.speech(Don't think that this means you're the one in charge!)]"
									+ " [npc.name] growls, before "+(!biped?"roughly pushing [npc.her] feral [npc.legRace] body":"dropping down onto all fours and pushing [npc.herself]")+" back against you."
									+ " Bucking [npc.her] [pc.hips], [npc.she] snarls,"
									+ " [npc.speech(Just get to it "+(!bipedPlayer?"and mount me":"and fuck me")+" already, and be thankful that I wanted you to do this!)]");
							break;
						default:
							sb.append("[npc.speech(Oh, yeah, that'd be fun!)]"
									+ " [npc.name] happily replies, before "+(biped?"eagerly pushing [npc.her] feral [npc.legRace] body":"dropping down onto all fours and pushing [npc.herself]")+" back against you."
									+ " Bucking [npc.her] [pc.hips], [npc.she] [npc.moansVerb],"
									+ " [npc.speech(Come on, "+(!bipedPlayer?"mount me":"fuck me")+" already!)]");
							break;
					}
					sb.append((!bipedPlayer
										?(!biped
											?"<br/>"
												+ "Doing as [npc.she] asks, you rear up onto your hind legs, and, jolting forwards, you mount [npc.herHim]."
												+ " [npc.She] lets out a desperate [npc.moan] in response to your eager move, and,"
													+ " allowing you to use [npc.her] body to support your weight, [npc.she] prepares for you to start rutting [npc.herHim] like an animal."
											:"<br/>"
												+ "Doing as [npc.she] asks, you step forwards so that you're standing over [npc.herHim], before lowering yourself down a little in order to mount [npc.herHim]."
												+ " [npc.She] lets out a desperate [npc.moan] in response to your eager move, and prepares for you to start rutting [npc.herHim] like an animal.")
										:"<br/>"
											+ "Doing as [npc.she] asks, you "+(standingPlayer?"step":"drop to your knees and shuffle")+" forwards so that your groin is pressing against [npc.her] [npc.ass+]."
											+ " [npc.She] lets out a desperate [npc.moan] in response to your touch, and prepares for you to start fucking [npc.herHim] like an animal."));
					return sb.toString();
					
				} else {
					return "Outright refusing to do as you ask, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(I'm <i>not</i> getting fucked by you! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAllFours.BEHIND) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					boolean bipedPlayer = !Main.game.getPlayer().isTaur();
					boolean standing = SexSlotAllFours.BEHIND.isStanding(Main.sex.getCharacterPerformingAction());
					StringBuilder sb = new StringBuilder();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							sb.append("[npc.speech(Don't think that this means you're the one in charge!)]"
									+ " [npc.name] growls, before "
										+(!bipedPlayer
											?(!biped
												?" rearing up and jolting forwards, mounting you with [npc.her] feral [npc.legRace] body."
												:(standing?"stepping forwards":"dropping to [npc.her] knees, shuffling forwards,")+" and pressing [npc.her] groin against your feral [pc.legRace] body.")
											:(!biped
												?" stepping forwards over the top of you, mounting you with [npc.her] feral [npc.legRace] body."
												:(standing?"stepping forwards":"dropping to [npc.her] knees, shuffling forwards,")+" and pressing [npc.her] groin against your [pc.ass+]."))
									+ " Bucking [npc.her] [pc.hips], [npc.she] snarls,"
									+ " [npc.speech(Be thankful that I wanted to fuck you like the dirty animal you are!)]");
							break;
						default:
							sb.append("[npc.speech(Oh yeah, that sounds fun!)]"
									+ " [npc.name] happily replies, before "
										+(!bipedPlayer
											?(!biped
												?" rearing up and jolting forwards, mounting you with [npc.her] feral [npc.legRace] body."
												:(standing?"stepping forwards":"dropping to [npc.her] knees, shuffling forwards,")+" and pressing [npc.her] groin against your feral [pc.legRace] body.")
											:(!biped
												?" stepping forwards over the top of you, mounting you with [npc.her] feral [npc.legRace] body."
												:(standing?"stepping forwards":"dropping to [npc.her] knees, shuffling forwards,")+" and pressing [npc.her] groin against your [pc.ass+]."))
									+ " Bucking [npc.her] [pc.hips], [npc.she] [npc.moansVerb],"
									+ " [npc.speech(Now be a good [pc.girl] and enjoy your fucking!)]");
							break;
					}
					return sb.toString();
					
				} else {
					return "Outright refusing to do as you ask, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(I'm <i>not</i> fucking you like that! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.SIXTY_NINE) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Jumping down onto all fours, [npc.name] lowers [npc.herself] down over the top of you, bringing [npc.her] crotch down to your face as [npc.she] drops [npc.her] head down between your [pc.legs]."
									+ " Turning [npc.her] head back to look at you, [npc.she] growls, "
									+ "[npc.speech(Good idea slut! Now <i>stay still</i> so I can use you properly!)]";
						default:
							return "Jumping down onto all fours, [npc.name] lowers [npc.herself] down over the top of you, bringing [npc.her] crotch down to your face as [npc.she] drops [npc.her] head down between your [pc.legs]."
									+ " Turning [npc.her] head back to look at you, [npc.she] grins, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.SIXTY_NINE) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Allowing you to push [npc.herHim] down, [npc.name] reaches up and takes a firm grip on your [pc.hips], before roughly tugging you down so that you're over the top of [npc.herHim] in a reversed all-fours position."
									+ " Pulling your groin down to [npc.her] face, [npc.she] growls, "
									+ "[npc.speech(Good idea, slut! Now put your mouth to use!)]";
						default:
							return "Allowing you to push [npc.herHim] down, [npc.name] reaches up and takes a firm grip on your [pc.hips], before pulling you down so that you're over the top of [npc.herHim] in a reversed all-fours position."
									+ " Pulling your groin down to [npc.her] face, [npc.she] [npc.moansVerb], "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Knocking your [pc.arms] away from [npc.her] shoulders, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.COWGIRL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over your groin, bringing [npc.her] crotch down to yours as [npc.she] straddles you in the cowgirl position."
									+ " Leaning forwards a little, [npc.she] growls down at you, "
									+ "[npc.speech(Good idea, slut! Now <i>stay still</i> so I can use you properly!)]";
						default:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over your groin, bringing [npc.her] crotch down to yours as [npc.she] straddles you in the cowgirl position."
									+ " Leaning forwards a little, [npc.she] grins down at you, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.COWGIRL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Allowing you to push [npc.herHim] down, [npc.name] reaches up and takes a firm grip on your [pc.hips], before roughly pulling you down so that you're straddling [npc.herHim] in the cowgirl position."
									+ " Looking up into your [pc.eyes], [npc.she] growls, "
									+ "[npc.speech(Good idea, slut! Now put some effort into it!)]";
						default:
							return "Allowing you to push [npc.herHim] down, [npc.name] reaches up and takes a firm grip on your [pc.hips], before pulling you down so that you're straddling [npc.herHim] in the cowgirl position."
									+ " Looking up into your [pc.eyes], [npc.she] [npc.moansVerb], "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Knocking your [pc.arms] away from [npc.her] shoulders, [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.FACE_SITTING) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Slapping your [pc.hands] away from [npc.herHim], [npc.name] grabs you by the wrists and yanks you forwards, growling, "
									+ "[npc.speech(Don't you <i>dare</i> try to take control here! I'll let you sit on my face, but only because <i>I</i> want it, understood?!)]"
									+ "<br/>"
									+ "With that, [npc.she] quickly lies down on [npc.her] back, dragging you down with [npc.herHim]."
									+ " Despite [npc.namePos] angry words, you've ended up getting exactly what you wanted, and happily plant your groin down on [npc.her] face.";
						default:
							return "Smiling at you, [npc.name] responds, "
									+ "[npc.speech(That's a great idea! Oh, this is gonna be good!)]"
									+ "<br/>"
									+ "With that, [npc.she] quickly lies down on [npc.her] back, pulling you down with [npc.herHim]."
									+ " You let out a happy [pc.moan] as you find yourself getting exactly what you wanted, and eagerly plant your groin down on [npc.her] face.";
					}
					
				} else {
					return "Slapping your [pc.hands] away from [npc.herHim], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.FACE_SITTING) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over you, bringing [npc.her] crotch down over your face before planting [npc.her] groin down onto your mouth."
									+ " Grinding roughly down against your face, [npc.she] growls, "
									+ "[npc.speech(Good idea slut! Now <i>stay still</i> so I can use you properly!)]";
						default:
							return "Jumping down over the top of you, [npc.name] lowers [npc.herself] down over you, bringing [npc.her] crotch down over your face before planting [npc.her] groin down onto your mouth."
									+ " Grinding down against your face, [npc.she] [npc.moans], "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.MATING_PRESS) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "With an eager, lustful grin on [npc.her] face, [npc.name] does just as you say, and drops [npc.her] full weight down on top of you."
									+ " Roughly grinding [npc.her] crotch against yours, [npc.she] pins your wrists to the floor on either side of your head and growls, "
									+ "[npc.speech(You filthy slut!"
									+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN I'm going to fuck you real good!#ELSE I'm going to breed you real good!#ENDIF")
									+ ")]";
						default:
							return "With an eager, lustful smile on [npc.her] face, [npc.name] does just as you say, and lies down over the top of you."
									+ " Desperately [npc.her] crotch against yours, [npc.she] pins your wrists to the floor on either side of your head and [npc.moansVerb], "
									+ "[npc.speech(Good [pc.girl]!"
									+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN I'm going to fuck you real good!#ELSE I'm going to breed you real good!#ENDIF")
									+ ")]";
					}
					
				} else {
					return "Reaching down to grab you by the [pc.arm], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.MISSIONARY) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							if(Main.sex.getCharacterPerformingAction().hasLegs()) {
								return "Much to your delight, [npc.name] allows [npc.herself] to be pushed down onto [npc.her] back, but as [npc.she] spreads [npc.her] [npc.legs] for you, [npc.she] growls in a menacing tone, "
										+ "[npc.speech(Don't get carried away, bitch! I'm still the one in charge here!)]";
							} else {
								return "Much to your delight, [npc.name] allows [npc.herself] to be pushed down onto [npc.her] back, but as [npc.she] presents [npc.her] groin to you, [npc.she] growls in a menacing tone, "
										+ "[npc.speech(Don't get carried away, bitch! I'm still the one in charge here!)]";
							}
						default:
							if(Main.sex.getCharacterPerformingAction().hasLegs()) {
								return "Much to your delight, [npc.name] allows [npc.herself] to be pushed down onto [npc.her] back, and as [npc.she] spreads [npc.her] [npc.legs] for you, [npc.she] [npc.moansVerb], "
										+ "[npc.speech(I like it when my partner shows a bit of initiative! Come take me!)]";
							} else {
								return "Much to your delight, [npc.name] allows [npc.herself] to be pushed down onto [npc.her] back, and as [npc.she] presents [npc.her] groin to you, [npc.she] [npc.moansVerb], "
										+ "[npc.speech(I like it when my partner shows a bit of initiative! Come take me!)]";
							}
					}
				} else {
					return "Slapping your [pc.hands] away, [npc.name] pushes you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.MISSIONARY) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Much to your delight, [npc.name] kneels down between your [pc.legs], and as [npc.she] grabs your [npc.legs] to push them apart, [npc.she] growls, "
									+ "[npc.speech(That's right, bitch! Spread your legs like the slut you are!)]";
						default:
							return "Much to your delight, [npc.name] kneels down between your [pc.legs], and as [npc.she] takes hold of your [npc.legs] to help push them apart, [npc.she] [npc.moansVerb], "
									+ "[npc.speech(Good idea! Spread your legs nice and wide, now!)]";
					}
				} else {
					return "Grabbing one of your [pc.arms], [npc.name] pulls you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotAgainstWall.FACE_TO_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Much to your delight, you feel [npc.name] reach down and roughly grab your hips, and, grinding [npc.herself] into your back, [npc.she] growls into your ear, "
									+ "[npc.speech(I love fucking bitches like you from behind! Now <i>stay still</i> like a good slut!)]";
						default:
							return "Much to your delight, you feel [npc.name] reach down to take hold of your hips, and as [npc.she] leans in over your shoulder, [npc.she] [npc.moans] into your ear, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
				} else {
					return "Grabbing you by the shoulders, [npc.name] pulls you away from the [pc.wall], pushing you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotAgainstWall.BACK_TO_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name] grins as you try to entice [npc.herHim] to come over and fuck you against the [pc.wall]."
									+ " Moving up to roughly grind [npc.her] body against yours, [npc.she] leans in over your shoulder and growls into your ear, "
									+ "[npc.speech(Good slut! Now <i>stay still</i> so I can give you a proper fucking!)]";
						default:
							return "[npc.Name] grins as you try to entice [npc.herHim] to come over and fuck you against the [pc.wall]."
									+ " Moving up to press [npc.her] body against yours, [npc.she] leans in over your shoulder and [npc.moans] into your ear, "
									+ "[npc.speech(Good [pc.girl]! This is gonna be fun!)]";
					}
					
				} else {
					return "Grabbing you by the shoulders, [npc.name] pulls you away from the [pc.wall], pushing you back into your old position as [npc.she] angrily scolds you, "
							+ "[npc.speech(What do you think you're doing?! Don't you <i>dare</i> try that again!)]";
				}
				
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
						Main.sex.getPositionRequest().getPosition(),
						Main.sex.getPositionRequest().getPartnerSlots().get(0),
						Main.sex.getPositionRequest().getPerformerSlots().get(0),
						Main.game.getPlayer())) {
				GenericPositioning.setNewSexManager(Main.sex.getPositionRequest(), true);
			} else {
				Main.sex.addPositioningRequestsBlocked(Main.game.getPlayer(), Main.sex.getPositionRequest().getPosition());
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
	
}
