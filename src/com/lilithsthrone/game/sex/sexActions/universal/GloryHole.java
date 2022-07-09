package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class GloryHole {
	
	private static void applyChangeSlotEffects() {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();

		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		
		if(Main.sex.isDom(Main.game.getPlayer())) {
			dominants.put(Main.game.getPlayer(), Main.sex.getPositionRequest().getPartnerSlots().get(0));
			if(Main.sex.getDominantParticipants(false).size()==2) {
				doms.remove(Main.game.getPlayer());
				dominants.put(doms.get(0), Main.sex.getPositionRequest().getPartnerSlots().get(1));
			}
			submissives.put(subs.get(0), Main.sex.getPositionRequest().getPerformerSlots().get(0));
			
		} else {
			submissives.put(Main.game.getPlayer(), Main.sex.getPositionRequest().getPartnerSlots().get(0));
			if(Main.sex.getDominantParticipants(false).size()==2) {
				subs.remove(Main.game.getPlayer());
				submissives.put(subs.get(0), Main.sex.getPositionRequest().getPartnerSlots().get(1));
			}
			dominants.put(doms.get(0), Main.sex.getPositionRequest().getPerformerSlots().get(0));
		}
		
		Main.sex.setSexManager(new SexManagerDefault(
				Main.sex.getPositionRequest().getPosition(),
				dominants,
				submissives){
		});
	}

	public static final SexAction POSITION_DOUBLE_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPosition.GLORY_HOLE, SexSlotUnique.GLORY_HOLE_KNEELING, Main.game.getPlayer()));
		}
		
		@Override
		public String getActionTitle() {
			return "Kneel";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				return "Kneel down on the floor, ready to service the glory holes with your mouth.";
			} else {
				return "Kneel down on the floor, ready to service the glory hole with your mouth.";
			}
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				return "Pulling away from the glory holes, [npc.name] [npc.verb(take)] a step back, before kneeling down on the floor between them."
						+ " Looking back and forth at the genitals on display, [npc.she] [npc.verb(let)] out [npc.a_moan+], before getting ready to use [npc.her] mouth.";
				
			} else {
				return "Pulling away from the glory hole, [npc.name] [npc.verb(take)] a step back, before kneeling down on the floor before it."
						+ " Grinning hungrily at the genitals on display, [npc.she] [npc.verb(let)] out [npc.a_moan+], before leaning forwards and getting ready to use [npc.her] mouth.";
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));

				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE,
						Util.newHashMapOfValues(
								new Value<>(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_KNEELING))));
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_KNEELING))));
			}
		}
	};
	
	public static final SexAction POSITION_ANAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()
									&& ((NPC) Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaOrifice.ANUS));
		}
		
		@Override
		public String getActionTitle() {
			return "Present asshole";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.ass+] up against [npc2.namePos] glory hole, ready for [npc2.herHim] to use your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Standing up, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF over towards [npc2.name], before bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Leaning forwards, [npc.she] [npc.verb(position)] [npc.herself] so that [npc.her] mouth is just [unit.sizes] away from the hole on the opposite side of the stall,"
								+ " and, letting out [npc.a_moan+], [npc.she] [npc.verb(prepare)] to have [npc.her] [npc.asshole+] and mouth used at the same time.";
					
				} else {
					return "Pulling away from the glory holes, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] bending down and pushing [npc.her] [npc.ass+] up against [npc2.namePos] glory hole."
							+ " Leaning forwards, [npc.she] [npc.verb(position)] [npc.herself] so that [npc.her] mouth is just [unit.sizes] away from the hole on the opposite side of the stall,"
								+ " and, letting out [npc.a_moan+], [npc.she] [npc.verb(prepare)] to have [npc.her] [npc.asshole+] and mouth used at the same time.";
				}
				
			} else {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Standing up, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF over towards [npc2.name], before bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Letting out [npc.a_moan+], [npc.she] [npc.verb(press)] back against the wall, preparing to have [npc.her] [npc.asshole+] used.";
					
				} else {
					return "Pulling away from the glory holes, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Letting out [npc.a_moan+], [npc.she] [npc.verb(press)] back against the wall, preparing to have [npc.her] [npc.asshole+] used.";
				}
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				GameCharacter characterFucked = Main.sex.getCharacterPerformingAction();
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
				GameCharacter characterFucking = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
				
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(
								new Value<>(characterFucking, SexSlotUnique.GLORY_HOLE_FUCKING),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(
								new Value<>(characterFucked, SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED))));
				
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_FUCKING)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED))));
			}
		}
	};

	public static final SexAction POSITION_VAGINAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasVagina()
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isNegative()
									&& ((NPC) Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaOrifice.VAGINA));
		}
		
		@Override
		public String getActionTitle() {
			return "Present pussy";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.ass+] up against [npc2.namePos] glory hole, ready for [npc2.herHim] to use your [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Standing up, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF over towards [npc2.name], before bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Leaning forwards, [npc.she] [npc.verb(position)] [npc.herself] so that [npc.her] mouth is just [unit.sizes] away from the hole on the opposite side of the stall,"
								+ " and, letting out [npc.a_moan+], [npc.she] [npc.verb(prepare)] to have [npc.her] [npc.pussy+] and mouth used at the same time.";
					
				} else {
					return "Pulling away from the glory holes, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] bending down and pushing [npc.her] [npc.ass+] up against [npc2.namePos] glory hole."
							+ " Leaning forwards, [npc.she] [npc.verb(position)] [npc.herself] so that [npc.her] mouth is just [unit.sizes] away from the hole on the opposite side of the stall,"
								+ " and, letting out [npc.a_moan+], [npc.she] [npc.verb(prepare)] to have [npc.her] [npc.pussy+] and mouth used at the same time.";
				}
				
			} else {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Standing up, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF over towards [npc2.name], before bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Letting out [npc.a_moan+], [npc.she] [npc.verb(press)] back against the wall, preparing to have [npc.her] [npc.pussy+] used.";
					
				} else {
					return "Pulling away from the glory holes, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Letting out [npc.a_moan+], [npc.she] [npc.verb(press)] back against the wall, preparing to have [npc.her] [npc.pussy+] used.";
				}
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				GameCharacter characterFucked = Main.sex.getCharacterPerformingAction();
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
				GameCharacter characterFucking = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
				
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(
								new Value<>(characterFucking, SexSlotUnique.GLORY_HOLE_FUCKING),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(
								new Value<>(characterFucked, SexSlotUnique.GLORY_HOLE_FUCKED))));
				
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_FUCKING)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_FUCKED))));
			}
		}
	};
	
	public static final SexAction POSITION_VAGINAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE_SEX,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKED),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
		}
		@Override
		public String getActionTitle() {
			return "Fuck pussy (R)";
		}
		@Override
		public String getActionDescription() {
			return "Ask [npc2.name] to push [npc2.her] pussy up against the glory hole so that you can fuck it.";
		}
		@Override
		public String getDescription() {
			return "Wanting to fuck [npc2.namePos] pussy, you call out loud enough for [npc2.herHim] to hear, [npc.speech(Push your pussy up against the hole! I want to fuck you!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_VAGINAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_VAGINAL_RECEIVING);
			}
		}
	};

	public static final SexAction POSITION_ANAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE_SEX,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_FUCKED)
					&& Main.game.isAnalContentEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
		}
		@Override
		public String getActionTitle() {
			return "Fuck ass (R)";
		}
		@Override
		public String getActionDescription() {
			return "Ask [npc2.name] to push [npc2.her] ass up against the glory hole so that you can fuck it.";
		}
		@Override
		public String getDescription() {
			return "Wanting to fuck [npc2.namePos] ass, you call out loud enough for [npc2.herHim] to hear, [npc.speech(Push your butt up against the hole! I want to fuck your ass!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};

	public static final SexAction POSITION_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_KNEELING),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			return "Oral (R)";
		}
		@Override
		public String getActionDescription() {
			return "Ask [npc2.name] to kneel down in front of the glory hole so that [npc2.she] can perform oral.";
		}
		@Override
		public String getDescription() {
			return "Wanting [npc2.name] to perform oral, you call out loud enough for [npc2.herHim] to hear, [npc.speech(Kneel down and put your mouth to use!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
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
			return "";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
				if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
					return "Standing up and moving back so as to push [npc.her] [npc.pussy+] against the glory hole, [npc.name] [npc.moansVerb],"
							+ " [npc.speech(Come on then, fuck my pussy!)]";
					
				} else {
					return "Staying in [npc.her] current position, [npc.name] angrily calls out, "
							+ "[npc.speech(I'm not going to let you fuck my pussy! Just be happy with what you've got!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
				if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
					return "Standing up and moving back so as to push [npc.her] [npc.asshole+] against the glory hole, [npc.name] [npc.moansVerb],"
							+ " [npc.speech(Come on then, fuck my ass!)]";
					
				} else {
					return "Staying in [npc.her] current position, [npc.name] angrily calls out, "
							+ "[npc.speech(I'm not going to let you fuck my pussy! Just be happy with what you've got!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_KNEELING) {
				return "Dropping down onto [npc.her] knees, [npc.name] [npc.moansVerb],"
							+ (Main.game.getPlayer().hasPenis()
								?" [npc.speech(Let me suck your cock!)]"
								:" [npc.speech(Let me use my mouth!)]");
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if((Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()
					|| (Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()))
					|| Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_KNEELING) {
				if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)));
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)));
					
				} else {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null)));
				}
				applyChangeSlotEffects();
				
			} else {
				if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
					Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
					Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
				}
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
}
