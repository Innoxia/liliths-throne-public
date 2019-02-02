package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexSlotOther;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexPositionOther;
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
 * @version 0.3.1
 * @author Innoxia
 */
public class GenericPositioningNew {

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
		public boolean isBaseRequirementsMet() {
			return !Sex.isCharacterBannedFromPositioning(Sex.getCharacterPerformingAction())
					&& Sex.getCharacterPerformingAction().getLegConfiguration()==Sex.getCharacterTargetedForSexAction(this).getLegConfiguration() // Can only swap if have same body type
					&& Sex.getSexManager().isPlayerAbleToSwapPositions()
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
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
			Sex.swapSexPositionSlots(Main.game.getPlayer(), Sex.getActivePartner());
		}
	};
	
	private static boolean checkBaseRequirements(PositioningData data, boolean request) {
		return !Sex.isCharacterBannedFromPositioning(Sex.getCharacterPerformingAction())
				&& !(Sex.getPosition() == data.getPosition() && Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0))
				&& data.getPosition().getMaximumSlots()>=Sex.getTotalParticipantCount(false)
				&& Sex.getTotalParticipantCount(false)<=(data.getPerformerSlots().size()+data.getPartnerSlots().size())
				&& (request
						?Sex.getSexControl(Sex.getCharacterPerformingAction())!=SexControl.FULL
						:Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL)
				&& (request
						?Sex.getCharacterPerformingAction().isPlayer()
						:true)
				&& (!request && !Sex.getCharacterPerformingAction().isPlayer()
						?(((NPC) Sex.getCharacterPerformingAction()).getSexPositionPreferences(Sex.getTargetedPartner(Sex.getCharacterPerformingAction())).contains(data.getPerformerSlots().get(0))
								|| ((NPC) Sex.getCharacterPerformingAction()).getSexPositionPreferences(Sex.getCharacterPerformingAction()).isEmpty())
						:true);
	}

	private static void setNewSexManager(PositioningData data, boolean requestAccepted) {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();
		List<GameCharacter> doms = new ArrayList<>(Sex.getDominantParticipants().keySet());
		List<GameCharacter> subs = new ArrayList<>(Sex.getSubmissiveParticipants().keySet());
		
		GameCharacter performer = Sex.getCharacterPerformingAction();
		GameCharacter target = Sex.getTargetedPartner(performer);
		if(requestAccepted) {
			target = Sex.getCharacterPerformingAction();
			performer = Sex.getTargetedPartner(target);
		}
		
		if(Sex.isDom(performer)) {
			doms.remove(performer);
			dominants.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				dominants.put(doms.get(i), data.getPerformerSlots().get(i+1));
			}
			subs.remove(target);
			submissives.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				submissives.put(subs.get(i), data.getPartnerSlots().get(i+1));
			}
		} else {
			doms.remove(target);
			dominants.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				dominants.put(doms.get(i), data.getPartnerSlots().get(i+1));
			}
			subs.remove(performer);
			submissives.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				submissives.put(subs.get(i), data.getPerformerSlots().get(i+1));
			}
		}
		Sex.setSexManager(new SexManagerDefault(
				data.getPosition(),
				dominants,
				submissives){
		});
		Sex.setPositionRequest(null);
	}

	
	
	
	//--------------- ORAL ---------------//
	
	private static List<SexSlot> generatePerformerOralData(GameCharacter receiver) {
		List<GameCharacter> doms = new ArrayList<>(Sex.getDominantParticipants().keySet());
		doms.remove(receiver);
		List<GameCharacter> subs = new ArrayList<>(Sex.getSubmissiveParticipants().keySet());
		subs.remove(receiver);

		boolean bipedalOral1 = receiver.getLegConfiguration().isBipedalPositionedGenitals();
		boolean doubleReceiving = false;
		GameCharacter receiver2 = null;
		if(Sex.isDom(receiver)) {
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
		boolean bipedalOral2 = receiver2!=null?receiver2.getLegConfiguration().isBipedalPositionedGenitals():false;
		
		List<SexSlot> performerSlots = new ArrayList<>();
		if(bipedalOral1) {
			performerSlots.add(SexSlotOther.PERFORMING_ORAL);
		} else {
			if(receiver.hasPenis()) {
				performerSlots.add(SexSlotOther.PERFORMING_ORAL);
			} else {
				performerSlots.add(SexSlotOther.PERFORMING_ORAL_BEHIND);
			}
		}
		if(doubleReceiving) {
			if(bipedalOral2) {
				performerSlots.add(SexSlotOther.PERFORMING_ORAL_TWO);
			} else {
				if(receiver2.hasPenis()) {
					performerSlots.add(SexSlotOther.PERFORMING_ORAL_TWO);
				} else {
					performerSlots.add(SexSlotOther.PERFORMING_ORAL_BEHIND_TWO);
				}
			}
		}
		SexSlot[] slots = new SexSlot[] {
				SexSlotOther.PERFORMING_ORAL, SexSlotOther.PERFORMING_ORAL_BEHIND, SexSlotOther.PERFORMING_ORAL_TWO,
				SexSlotOther.PERFORMING_ORAL_BEHIND_TWO, SexSlotOther.STANDING_SUBMISSIVE, SexSlotOther.STANDING_SUBMISSIVE_TWO};
		for(SexSlot slot : slots) {
			if(!performerSlots.contains(slot)) {
				performerSlots.add(slot);
			}
		}
		
		return performerSlots;
	}
	
	private static PositioningData generateReceivingOralData(GameCharacter receiver) {
		return new PositioningData(
				SexPositionOther.ORAL,
				Util.newArrayListOfValues(
						SexSlotOther.RECEIVING_ORAL,
						SexSlotOther.RECEIVING_ORAL_TWO),
				generatePerformerOralData(receiver));
	}

	private static PositioningData generatePerformingOralData(GameCharacter receiver) {
		return new PositioningData(
				SexPositionOther.ORAL,
				generatePerformerOralData(receiver),
				Util.newArrayListOfValues(
						SexSlotOther.RECEIVING_ORAL,
						SexSlotOther.RECEIVING_ORAL_TWO));
	}
	
	public static final SexAction POSITION_ORAL_RECEIVING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingOralData(Sex.getCharacterPerformingAction()), false);
		}
		@Override
		public String getActionTitle() {
			return "Receive oral";
		}
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Get [npc2.name] to perform oral on you. Once [npc2.sheHasFull] started, you can get [npc2.herHim] to switch between your front and back.";
			} else {
				return "Get [npc2.name] to perform oral on you. Once [npc2.sheHasFull] started, you can get [npc2.herHim] to switch between kneeling beneath or behind your animalistic body.";
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) { // Biped body:
				if(generatePerformerOralData(Sex.getCharacterPerformingAction()).get(0)==SexSlotOther.PERFORMING_ORAL) {
					if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterTargetedForSexAction(this))) {
						return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(position)] [npc2.herHim] so that [npc2.sheIs] standing before [npc.herHim]."
								+ " Grinning down at [npc2.herHim], [npc.name] [npc.verb(order)],"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					} else {
						return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling before [npc.herHim]."
								+ " Grinning down at [npc2.herHim], [npc.name] [npc.verb(order)],"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					}
				} else {
					if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterTargetedForSexAction(this))) {
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
				if(generatePerformerOralData(Sex.getCharacterPerformingAction()).get(0)==SexSlotOther.PERFORMING_ORAL) {
					if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterTargetedForSexAction(this))) {
						return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(position)] [npc2.herHim] so that [npc2.sheIs] standing beneath [npc.her] lower [npc.legRace]'s body."
								+ " Stepping forwards and pushing [npc.herself] against [npc2.herHim], [npc.name] [npc.verb(call)] out,"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					} else {
						return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling beneath [npc.her] lower [npc.legRace]'s body."
								+ " Stepping forwards and pushing [npc.herself] against [npc2.herHim], [npc.name] [npc.verb(call)] out,"
								+ " [npc.speech(Go on, put that mouth of yours to use!)]";
					}
				} else {
					if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterTargetedForSexAction(this))) {
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
			setNewSexManager(generateReceivingOralData(Sex.getCharacterPerformingAction()), false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_ORAL_RECEIVING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingOralData(Sex.getCharacterPerformingAction()), true)
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.RECEIVING_ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.RECEIVING_ORAL_TWO;
		}
		@Override
		public String getActionTitle() {
			return "Receive oral (R)";
		}
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Try and get [npc2.name] to perform oral on you. If [npc2.she] accepts, you can further request [npc2.herHim] to switch between your front and back.<br/>"
						+ getRequestTooltipText();
			} else {
				return "Try and get [npc2.name] to perform oral on you. If [npc2.she] accepts, you can further request [npc2.herHim] to switch between kneeling beneath or behind your animalistic body.<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) { // Biped body:
				if(generatePerformerOralData(Sex.getCharacterPerformingAction()).get(0)==SexSlotOther.PERFORMING_ORAL) {
					return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing before [npc2.herHim]."
							+ " Gazing into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, I want you to use your mouth!)]";
				} else {
					return "Wanting [npc2.name] to perform anilingus on [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing with [npc.her] back to [npc2.herHim]."
							+ " Looking back over [npc.her] shoulder, [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, I want you to use your mouth!)]";
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Sex.getCharacterPerformingAction()).get(0)==SexSlotOther.PERFORMING_ORAL) {
					return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] presenting the underside of [npc.her] lower [npc2.legRace]'s body to [npc2.herHim]."
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
			Sex.setPositionRequest(generateReceivingOralData(Sex.getCharacterPerformingAction()));
		}
	};
	
	public static final SexAction POSITION_ORAL_MOVE_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPosition()==SexPositionOther.ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))!=SexSlotOther.RECEIVING_ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))!=SexSlotOther.RECEIVING_ORAL_TWO
					&& Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))!=SexSlotOther.PERFORMING_ORAL_BEHIND
					&& (Sex.getCharacterInPosition(SexSlotOther.RECEIVING_ORAL_TWO)!=null
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))!=SexSlotOther.PERFORMING_ORAL_BEHIND_TWO)
					
					&& !Sex.isCharacterBannedFromPositioning(Sex.getCharacterPerformingAction())
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			return "Move [npc2.herHim] behind";
		}
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Get [npc2.name] to switch position so that [npc2.sheIsFull] behind you, and so able to perform oral on your [npc.asshole].";
			} else {
				return "Get [npc2.name] to switch position so that [npc2.sheIsFull] behind you, and so able to perform oral on your [npc.asshole]"+(Sex.getCharacterPerformingAction().hasVagina()?" and [npc.pussy]":"")+".";
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) { // Biped body:
				if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterTargetedForSexAction(this))) {
					return "Wanting [npc2.name] to perform anilingus on [npc.herHim], [npc.name] [npc.verb(reposition)] [npc2.herHim] so that [npc2.sheIs] standing behind [npc.herHim]."
							+ " Pushing [npc.her] [npc.ass+] back against [npc2.her] [npc2.face], [npc.she] [npc.verb(order)],"
							+ " [npc.speech(Go on, put your mouth to use!)]";
				} else {
					return "Wanting [npc2.name] to perform anilingus on [npc.herHim], [npc.name] [npc.verb(reposition)] and [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling behind [npc.herHim]."
							+ " Pushing [npc.her] [npc.ass+] back against [npc2.her] [npc2.face], [npc.she] [npc.verb(order)],"
							+ " [npc.speech(Go on, put your mouth to use!)]";
				}
				
			} else { // Taur body:
				if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterTargetedForSexAction(this))) {
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
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			if(Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_BEHIND)!=null) {
				Sex.swapSexPositionSlots(target, Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_BEHIND));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Sex.getDominantParticipants());
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Sex.getSubmissiveParticipants());
			
			if(Sex.isDom(target)) {
				dominants.put(target, SexSlotOther.PERFORMING_ORAL_BEHIND);
			} else {
				submissives.put(target, SexSlotOther.PERFORMING_ORAL_BEHIND);
			}

			Sex.setSexManager(new SexManagerDefault(
					SexPositionOther.ORAL,
					dominants,
					submissives){
			});
		}
	};
	
	public static final SexAction POSITION_ORAL_MOVE_IN_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPosition()==SexPositionOther.ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))!=SexSlotOther.PERFORMING_ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))!=SexSlotOther.RECEIVING_ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))!=SexSlotOther.RECEIVING_ORAL_TWO
					&& (Sex.getCharacterInPosition(SexSlotOther.RECEIVING_ORAL_TWO)!=null
							|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))!=SexSlotOther.PERFORMING_ORAL_TWO)
					
					&& !Sex.isCharacterBannedFromPositioning(Sex.getCharacterPerformingAction())
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Move [npc2.herHim] in front";
			} else {
				return "Move [npc2.herHim] beneath";
			}
		}
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Get [npc2.name] to switch position so that [npc2.sheIsFull] in front of you, and so able to perform oral on your genitals.";
			} else {
				if(Sex.getCharacterPerformingAction().hasPenis()) {
					return "Get [npc2.name] to switch position so that [npc2.sheIsFull] kneeling beneath you, and so able to perform oral on your [npc.cock]"+(Sex.getCharacterPerformingAction().hasBreastsCrotch()?" and [npc.crotchBoobs]":"")+".";
				} else {
					return "Get [npc2.name] to switch position so that [npc2.sheIsFull] kneeling beneath you"+(Sex.getCharacterPerformingAction().hasBreastsCrotch()?", and so able to perform oral on your [npc.crotchBoobs]":"")+".";
				}
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) { // Biped body:
				if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterTargetedForSexAction(this))) {
					return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(reposition)] [npc2.herHim] so that [npc2.sheIs] standing in front of [npc.herHim]."
							+ " Grinning down at [npc2.herHim], [npc.name] [npc.verb(order)],"
							+ " [npc.speech(Go on, put that mouth of yours to use!)]";
				} else {
					return "Wanting [npc2.name] to perform oral on [npc.herHim], [npc.name] [npc.verb(reposition)] and [npc.verb(push)] [npc2.herHim] down so that [npc2.sheIs] kneeling in front of [npc.herHim]."
							+ " Grinning down at [npc2.herHim], [npc.name] [npc.verb(order)],"
							+ " [npc.speech(Go on, put that mouth of yours to use!)]";
				}
				
			} else { // Taur body:
				if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterTargetedForSexAction(this))) {
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
			GameCharacter target = Sex.getCharacterTargetedForSexAction(this);
			if(Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL)!=null) {
				Sex.swapSexPositionSlots(target, Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Sex.getDominantParticipants());
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Sex.getSubmissiveParticipants());
			
			if(Sex.isDom(target)) {
				dominants.put(target, SexSlotOther.PERFORMING_ORAL);
			} else {
				submissives.put(target, SexSlotOther.PERFORMING_ORAL);
			}

			Sex.setSexManager(new SexManagerDefault(
					SexPositionOther.ORAL,
					dominants,
					submissives){
			});
		}
	};
	
	public static final SexAction POSITION_ORAL_PERFORMING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingOralData(Sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public String getActionTitle() {
			return "Perform oral";
		}
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Get down and perform oral on [npc2.name]. Once you have started, you can switch between [npc.her] front and back.";
			} else {
				return "Get down and perform oral on [npc2.name]. Once you have started, you can switch between kneeling beneath or behind [npc.her] animalistic body.";
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) { // Biped body:
				if(generatePerformerOralData(Sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotOther.PERFORMING_ORAL) {
					if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing before [npc2.herHim]."
								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(That's right, let me put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down before [npc2.herHim]."
								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(That's right, let me put my mouth to use!)]";
					}
				} else {
					if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterPerformingAction())) {
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
				if(generatePerformerOralData(Sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotOther.PERFORMING_ORAL) {
					if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing beneath [npc.her] lower [npc.legRace]'s body."
								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down beneath [npc.her] lower [npc.legRace]'s body."
								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
					}
				} else {
					if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterPerformingAction())) {
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
			setNewSexManager(generatePerformingOralData(Sex.getCharacterTargetedForSexAction(this)), false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_ORAL_PERFORMING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingOralData(Sex.getCharacterTargetedForSexAction(this)), true)
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.PERFORMING_ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.PERFORMING_ORAL_TWO
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.PERFORMING_ORAL_BEHIND
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.PERFORMING_ORAL_BEHIND_TWO;
		}
		@Override
		public String getActionTitle() {
			return "Perform oral (R)";
		}
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Get down and try to convince [npc2.name] to let you perform oral on [npc2.herHim]. If [npc2.she] accepts, you can further request to switch between [npc2.her] front and back.<br/>"
						+ getRequestTooltipText();
			} else {
				return "Get down and try to convince [npc2.name] to let you perform oral on [npc2.herHim]. If [npc2.she] accepts, you can further request to switch between kneeling beneath or behind [npc2.her] animalistic body.<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) { // Biped body:
				if(generatePerformerOralData(Sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotOther.PERFORMING_ORAL) {
					if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing before [npc2.herHim]."
								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(drop)] down onto [npc.her] knees before [npc2.herHim]."
								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					}
				} else {
					if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterPerformingAction())) {
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
				if(generatePerformerOralData(Sex.getCharacterPerformingAction()).get(0)==SexSlotOther.PERFORMING_ORAL) {
					if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterPerformingAction())) {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(position)] [npc.herself] so that [npc.sheIs] standing beneath [npc2.her] lower [npc2.legRace]'s body."
								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					} else {
						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(drop)] down onto [npc.her] knees beneath [npc2.her] lower [npc2.legRace]'s body."
								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.verb(plead)],"
								+ " [npc.speech(Please, let me put my mouth to use!)]";
					}
				} else {
					if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterPerformingAction())) {
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
			Sex.setPositionRequest(generatePerformingOralData(Sex.getCharacterTargetedForSexAction(this)));
		}
	};
	
	public static final SexAction POSITION_PERFORMING_ORAL_MOVE_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPosition()==SexPositionOther.ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.RECEIVING_ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.RECEIVING_ORAL_TWO
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.PERFORMING_ORAL_BEHIND
					&& (Sex.getCharacterInPosition(SexSlotOther.RECEIVING_ORAL_TWO)!=null
						|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.PERFORMING_ORAL_BEHIND_TWO)
					
					&& !Sex.isCharacterBannedFromPositioning(Sex.getCharacterPerformingAction())
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			return "Move behind [npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Switch position so that you are behind [npc2.name], and so able to perform oral on [npc2.her] [npc2.asshole].";
			} else {
				return "Switch position so that you are behind [npc2.name], and so able to perform oral on [npc2.her] [npc2.asshole]"+(Sex.getCharacterTargetedForSexAction(this).hasVagina()?" and [npc2.pussy]":"")+".";
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) { // Biped body:
				if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterPerformingAction())) {
					return "Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] standing behind [npc2.herHim]."
							+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				} else {
					return "Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] and [npc.verb(drop)] down onto [npc.her] knees behind [npc2.herHim]."
							+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				}
				
			} else { // Taur body:
				if(SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterPerformingAction())) {
					return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] standing behind [npc2.herHim]."
							+ " Running [npc.a_hand] up and over [npc.her] [npc2.ass+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				} else {
					return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc.herself] and [npc.verb(drop)] down onto [npc.her] knees behind [npc2.herHim]."
							+ " Running [npc.a_hand] up and over [npc.her] [npc2.ass+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Sex.getCharacterPerformingAction();
			if(Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_BEHIND)!=null) {
				Sex.swapSexPositionSlots(target, Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_BEHIND));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Sex.getDominantParticipants());
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Sex.getSubmissiveParticipants());
			
			if(Sex.isDom(target)) {
				dominants.put(target, SexSlotOther.PERFORMING_ORAL_BEHIND);
			} else {
				submissives.put(target, SexSlotOther.PERFORMING_ORAL_BEHIND);
			}

			Sex.setSexManager(new SexManagerDefault(
					SexPositionOther.ORAL,
					dominants,
					submissives){
			});
		}
	};
	
	public static final SexAction POSITION_PERFORMING_ORAL_MOVE_IN_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPosition()==SexPositionOther.ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.RECEIVING_ORAL
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.RECEIVING_ORAL_TWO
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.PERFORMING_ORAL
					&& (Sex.getCharacterInPosition(SexSlotOther.RECEIVING_ORAL_TWO)!=null
							|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.PERFORMING_ORAL_TWO)
					
					&& !Sex.isCharacterBannedFromPositioning(Sex.getCharacterPerformingAction())
					&& Sex.getSexControl(Sex.getCharacterPerformingAction())==SexControl.FULL
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Move in front";
			} else {
				return "Move beneath [npc2.herHim]";
			}
		}
		@Override
		public String getActionDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Get [npc2.name] to switch position so that [npc2.sheIsFull] in front of you, and so able to perform oral on your genitals.";
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).hasPenis()) {
					return "Get [npc2.name] to switch position so that [npc2.sheIsFull] kneeling beneath you, and so able to perform oral on your [npc.cock]"+(Sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()?" and [npc.crotchBoobs]":"")+".";
				} else {
					return "Get [npc2.name] to switch position so that [npc2.sheIsFull] kneeling beneath you"+(Sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()?", and so able to perform oral on your [npc.crotchBoobs]":"")+".";
				}
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) { // Biped body:
				if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterPerformingAction())) {
					return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] standing in front of [npc2.herHim]."
							+ " Gazing up into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				} else {
					return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] and [npc.verb(drop)] down onto [npc.her] knees in front of [npc2.herHim]."
							+ " Gazing up into [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				}
				
			} else { // Taur body:
				if(SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterPerformingAction())) {
					return "Wanting to perform oral on the underside of [npc2.namePos] lower [npc.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] standing beneath [npc2.herHim]."
							+ " Placing [npc.a_hand] on one of [npc2.her] rear [npc2.legs], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				} else {
					return "Wanting to perform oral on the underside of [npc2.namePos] lower [npc.legRace]'s body, [npc.name] [npc.verb(reposition)] [npc.herself] and [npc.verb(drop)] down onto [npc.her] knees beneath [npc2.herHim]."
							+ " Placing [npc.a_hand] on one of [npc2.her] rear [npc2.legs], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me put my mouth to use!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Sex.getCharacterPerformingAction();
			if(Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL)!=null) {
				Sex.swapSexPositionSlots(target, Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Sex.getDominantParticipants());
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Sex.getSubmissiveParticipants());
			
			if(Sex.isDom(target)) {
				dominants.put(target, SexSlotOther.PERFORMING_ORAL);
			} else {
				submissives.put(target, SexSlotOther.PERFORMING_ORAL);
			}

			Sex.setSexManager(new SexManagerDefault(
					SexPositionOther.ORAL,
					dominants,
					submissives){
			});
		}
	};

	
	
	
	//--------------- ALL FOURS ---------------//
	
	private static List<SexSlot> generatePerformerAllFoursData(GameCharacter receiver) {
		List<GameCharacter> doms = new ArrayList<>(Sex.getDominantParticipants().keySet());
		doms.remove(receiver);
		List<GameCharacter> subs = new ArrayList<>(Sex.getSubmissiveParticipants().keySet());
		subs.remove(receiver);

		boolean doubleReceiving = false;
		if(Sex.isDom(receiver)) {
			doubleReceiving = doms.size()>=1;
		} else {
			doubleReceiving = subs.size()>=1;
		}
		List<SexSlot> performerSlots = new ArrayList<>();
		performerSlots.add(SexSlotOther.ALL_FOURS_MOUNTING);
		if(doubleReceiving) {
			performerSlots.add(SexSlotOther.ALL_FOURS_MOUNTING_TWO);
		}
		SexSlot[] slots = new SexSlot[] {
				SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET,
				SexSlotOther.ALL_FOURS_MOUNTING_TWO, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET_TWO};
		for(SexSlot slot : slots) {
			if(!performerSlots.contains(slot)) {
				performerSlots.add(slot);
			}
		}
		
		return performerSlots;
	}
	
	private static PositioningData generateReceivingAllFoursData(GameCharacter receiver) {
		return new PositioningData(
				SexPositionOther.ALL_FOURS,
				Util.newArrayListOfValues(
						SexSlotOther.ALL_FOURS_FUCKED,
						SexSlotOther.ALL_FOURS_FUCKED_TWO),
				generatePerformerAllFoursData(receiver));
	}

	private static PositioningData generatePerformingAllFoursData(GameCharacter receiver) {
		return new PositioningData(
				SexPositionOther.ALL_FOURS,
				generatePerformerAllFoursData(receiver),
				Util.newArrayListOfValues(
						SexSlotOther.ALL_FOURS_FUCKED,
						SexSlotOther.ALL_FOURS_FUCKED_TWO));
	}
	
	private static boolean isAllFoursAvailable(GameCharacter gettingFucked) {
		switch(gettingFucked.getLegConfiguration()) {
			case ARACHNID:
			case TAIL:
			case TAIL_LONG:
				return false;
			case BIPEDAL:
			case CEPHALOPOD:
			case TAUR:
				return true;
		}
		return true;
	}
	
	public static final SexAction POSITION_ALL_FOURS_GETTING_FUCKED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingAllFoursData(Sex.getCharacterPerformingAction()), false)
					&& isAllFoursAvailable(Sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
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
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Drop down onto all fours so that [npc.name] can fuck you, doggy-style.";
				} else {
					return "Drop down onto all fours so that [npc.name] can mount and rut you.";
				}
			} else {
				return "Present your hindquarters to [npc2.name] and get [npc.herHim] to mount your animalistic body.";
			}
		}
		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Wanting to get fucked by [npc2.name] in the doggy-style position, [npc.name] [npc.verb(drop)] down onto all fours before [npc2.herHim]."
							+ " Crawling back and pushing [npc.her] [npc.ass+] against [npc2.namePos] groin, [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.moansVerb],"
							+ " [npc.speech(Come on, fuck me like an animal!)]";
				} else {
					return "Wanting [npc2.name] to mount [npc.herHim] and start rutting [npc.herHim] like an animal, [npc.name] [npc.verb(drop)] down onto all fours before [npc2.herHim]."
							+ " Crawling back so that [npc.sheIs] under [npc2.namePos] feral [npc2.legRace]'s body, [npc.she] [npc.verb(raise)] [npc.her] [npc.hips+] and [npc.moansVerb],"
							+ " [npc.speech(Come on, mount me and rut me like an animal!)]";
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Wanting [npc2.name] to fuck [npc.herHim], [npc.name] [npc.verb(turn)] around and [npc.verb(present)] the rear end of [npc.her] feral [npc.legRace]'s body to [npc2.herHim]."
							+ " Stepping back and pushing [npc.her] [npc.ass+] against [npc2.namePos] groin, [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.moansVerb],"
							+ " [npc.speech(Come on, fuck me like an animal!)]";
				} else {
					return "Wanting [npc2.name] to mount [npc.herHim] and start rutting [npc.herHim] like an animal, [npc.name] [npc.verb(present)] the rear end of [npc.her] feral [npc.legRace]'s body to [npc2.herHim]."
							+ " Stepping back and pushing [npc.her] [npc.ass+] against [npc2.name], [npc.she] [npc.verb(force)] [npc2.herHim] to rear up and mount [npc.herHim]."
							+ " Looking back over [npc.her] shoulder, [npc.she] [npc.moansVerb],"
							+ " [npc.speech(That's right, mount me and rut me like an animal!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generateReceivingAllFoursData(Sex.getCharacterPerformingAction()), false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_ALL_FOURS_GETTING_FUCKED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingAllFoursData(Sex.getCharacterPerformingAction()), true)
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.ALL_FOURS_FUCKED
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.ALL_FOURS_FUCKED_TWO
					&& isAllFoursAvailable(Sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
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
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
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
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Wanting to get fucked by [npc2.name] in the doggy-style position, [npc.name] [npc.verb(drop)] down onto all fours before [npc2.herHim]."
							+ " Crawling back and pushing [npc.her] [npc.ass+] against [npc2.namePos] groin, [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.verb(plead)],"
							+ " [npc.speech(Please, fuck me like an animal!)]";
				} else {
					return "Wanting [npc2.name] to mount [npc.herHim] and start rutting [npc.herHim] like an animal, [npc.name] [npc.verb(drop)] down onto all fours before [npc2.herHim]."
							+ " Crawling back so that [npc.sheIs] under [npc2.namePos] feral [npc2.legRace]'s body, [npc.she] [npc.verb(raise)] [npc.her] [npc.hips+] and [npc.verb(plead)],"
							+ " [npc.speech(Please, fuck me like an animal!)]";
				}
				
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Wanting [npc2.name] to fuck [npc.herHim], [npc.name] [npc.verb(turn)] around and [npc.verb(present)] the rear end of [npc.her] feral [npc.legRace]'s body to [npc2.herHim]."
							+ " Stepping back and pushing [npc.her] [npc.ass+] against [npc2.namePos] groin, [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.verb(plead)],"
							+ " [npc.speech(Please, fuck me like an animal!)]";
				} else {
					return "Wanting [npc2.name] to mount [npc.herHim] and start rutting [npc.herHim] like an animal, [npc.name] [npc.verb(present)] the rear end of [npc.her] feral [npc.legRace]'s body to [npc2.herHim]."
							+ " Stepping back and pushing [npc.her] [npc.ass+] against [npc2.name], [npc.she] [npc.verb(look)] back over [npc.her] shoulder and [npc.verb(plead)],"
							+ " [npc.speech(Please, mount me and rut me like an animal!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(generateReceivingAllFoursData(Sex.getCharacterPerformingAction()));
		}
	};
	
	public static final SexAction POSITION_ALL_FOURS_FUCKING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingAllFoursData(Sex.getCharacterTargetedForSexAction(this)), false)
					&& isAllFoursAvailable(Sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
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
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
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
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Wanting to fuck [npc2.name] in the doggy-style position, [npc.name] [npc.verb(push)] [npc2.herHim] down onto all fours before [npc.herHim]."
							+ " Kneeling down behind [npc2.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips+] and [npc.verb(pull)] [npc.her] [npc.ass+] back against [npc.her] groin, [npc.moaning],"
							+ " [npc.speech(Time to fuck you like an animal!)]";
				} else {
					return "Wanting to mount [npc2.name] and start rutting [npc2.herHim] like an animal, [npc.name] [npc.verb(push)] [npc2.herHim] down onto all fours before [npc.herHim]."
							+ " Stepping over [npc2.herHim] so that [npc2.sheIs] under [npc.her] feral [npc.legRace]'s body, [npc.she] [npc.verb(bend)] [npc.her] [npc.legs] a little and [npc.moansVerb],"
							+ " [npc.speech(Oh yes, now to mount you and rut you like an animal!)]";
				}
				
			} else {
				if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Wanting to fuck [npc2.name], [npc.name] [npc.verb(move)] around behind [npc2.her] feral [npc2.legRace]'s body."
							+ " With [npc2.her] rear end now presented to [npc.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips] and [npc.verb(push)] [npc.her] groin against [npc2.her] [npc2.ass+], [npc.moaning],"
							+ " [npc.speech(Time to fuck you like an animal!)]";
				} else {
					return "Wanting to mount [npc2.name] and start rutting [npc2.herHim] like an animal, [npc.name] [npc.verb(move)] around behind [npc2.her] feral [npc2.legRace]'s body."
							+ " With [npc2.her] rear end now presented to [npc.herHim], [npc.she] [npc.verb(jolt)] forwards and [npc.verb(rear)] up, mounting [npc2.herHim] in one swift movement."
							+ " [npc.speech(Oh yes, now to rut you like an animal!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generatePerformingAllFoursData(Sex.getCharacterTargetedForSexAction(this)), false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_ALL_FOURS_FUCKING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingAllFoursData(Sex.getCharacterTargetedForSexAction(this)), true)
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.ALL_FOURS_MOUNTING
					&& Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())!=SexSlotOther.ALL_FOURS_MOUNTING_TWO
					&& isAllFoursAvailable(Sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
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
			if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
				return "Try to get [npc2.name] down on all fours so that you can fuck [npc2.herHim], doggy-style.<br/>"
						+ getRequestTooltipText();
			} else {
				if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
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
			if(Sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isBipedalPositionedGenitals()) {
				if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Wanting to fuck [npc2.name] in the doggy-style position, [npc.name] [npc.verb(try)] to push [npc2.herHim] down onto all fours before [npc.herHim]."
							+ " Gripping [npc2.her] shoulders and exerting a downwards pressure, [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, get down and let me fuck you like an animal!)]";
				} else {
					return "Wanting to mount [npc2.name] and start rutting [npc2.herHim] like an animal, [npc.name] [npc.verb(try)] to push [npc2.herHim] down onto all fours before [npc.herHim]."
							+ " Gripping [npc2.her] shoulders and exerting a downwards pressure, [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, get down and let me mount you like an animal!)]";
				}
				
			} else {
				if(Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals()) {
					return "Wanting to fuck [npc2.name], [npc.name] [npc.verb(move)] around behind [npc2.her] feral [npc2.legRace]'s body."
							+ " With [npc2.her] rear end now presented to [npc.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips] and [npc.verb(plead)],"
							+ " [npc.speech(Please, let me fuck you like an animal!)]";
				} else {
					return "Wanting to mount [npc2.name] and start rutting [npc2.herHim] like an animal, [npc.name] [npc.verb(move)] around behind [npc2.her] feral [npc2.legRace]'s body."
							+ " With [npc2.her] rear end now presented to [npc.herHim], [npc.she] [npc.verb(plead)],"
							+ " [npc.speech(Please, let me mount you and rut you like an animal!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Sex.setPositionRequest(generatePerformingAllFoursData(Sex.getCharacterTargetedForSexAction(this)));
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
			return Sex.getPositionRequest()!=null
					&& !Sex.getCharacterPerformingAction().isPlayer();
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
			if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.PERFORMING_ORAL) {
				if(((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ORAL, SexSlotOther.PERFORMING_ORAL, Main.game.getPlayer())) {
					boolean standing = SexSlotOther.PERFORMING_ORAL.isStanding(Sex.getCharacterPerformingAction());
					switch(Sex.getSexPace(Sex.getActivePartner())) {
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
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.PERFORMING_ORAL_BEHIND) {
				if(((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ORAL, SexSlotOther.PERFORMING_ORAL_BEHIND, Main.game.getPlayer())) {
					boolean standing = SexSlotOther.PERFORMING_ORAL_BEHIND.isStanding(Sex.getCharacterPerformingAction());
					switch(Sex.getSexPace(Sex.getActivePartner())) {
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
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.RECEIVING_ORAL) {
				if(((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ORAL, SexSlotOther.RECEIVING_ORAL, Main.game.getPlayer())) {
					boolean biped = Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals();
					switch(Sex.getSexPace(Sex.getActivePartner())) {
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
							+ "[npc.speech(I'm <i>not</i> performing oral on you! Don't you <i>dare</i> try that again!)]";
				}
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.ALL_FOURS_FUCKED) {
				if(((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ALL_FOURS, SexSlotOther.ALL_FOURS_FUCKED, Main.game.getPlayer())) {
					boolean biped = Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals();
					boolean bipedPlayer = Main.game.getPlayer().getLegConfiguration().isBipedalPositionedGenitals();
					boolean standingPlayer = SexSlotOther.ALL_FOURS_MOUNTING.isStanding(Main.game.getPlayer());
					StringBuilder sb = new StringBuilder();
					switch(Sex.getSexPace(Sex.getActivePartner())) {
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
				
			} else if(Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.ALL_FOURS_MOUNTING) {
				if(((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ALL_FOURS, SexSlotOther.ALL_FOURS_MOUNTING, Main.game.getPlayer())) {
					boolean biped = Sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedGenitals();
					boolean bipedPlayer = Main.game.getPlayer().getLegConfiguration().isBipedalPositionedGenitals();
					boolean standing = SexSlotOther.ALL_FOURS_MOUNTING.isStanding(Sex.getCharacterPerformingAction());
					StringBuilder sb = new StringBuilder();
					switch(Sex.getSexPace(Sex.getActivePartner())) {
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
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if((Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.PERFORMING_ORAL && ((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ORAL, SexSlotOther.PERFORMING_ORAL, Main.game.getPlayer()))
					|| (Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.PERFORMING_ORAL_BEHIND && ((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ORAL, SexSlotOther.PERFORMING_ORAL_BEHIND, Main.game.getPlayer()))
					|| (Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.RECEIVING_ORAL && ((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ORAL, SexSlotOther.RECEIVING_ORAL, Main.game.getPlayer()))
					|| (Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.ALL_FOURS_FUCKED && ((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ALL_FOURS, SexSlotOther.ALL_FOURS_FUCKED, Main.game.getPlayer()))
					|| (Sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotOther.ALL_FOURS_MOUNTING && ((NPC)Sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionOther.ALL_FOURS, SexSlotOther.ALL_FOURS_MOUNTING, Main.game.getPlayer()))) {
				setNewSexManager(Sex.getPositionRequest(), true);
			}
			
			Sex.setPositionRequest(null);
		}
	};
	
}
