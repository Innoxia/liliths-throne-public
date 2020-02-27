package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHoleSex;
import com.lilithsthrone.game.sex.positions.SexPositionUnique;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.9
 * @version 0.3.4
 * @author Innoxia
 */
public class GloryHole {

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
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotUnique.GLORY_HOLE_KNEELING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionUnique.GLORY_HOLE, SexSlotUnique.GLORY_HOLE_KNEELING, Main.game.getPlayer()));
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
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_KNEELING)),
						Util.newHashMapOfValues(
								new Value<>(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO))));
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_KNEELING)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE))));
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
					&& ((Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED && !Main.sex.getSexManager().getAreasBannedMap().get(Main.sex.getCharacterPerformingAction()).contains(SexAreaOrifice.VAGINA))
						|| !Main.sex.getCharacterTargetedForSexAction(this).equals(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKING))
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()
									&& ((NPC) Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.game.getPlayer()).getPerformingSexArea()==SexAreaOrifice.ANUS));
//									&& ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionBipeds.GLORY_HOLE_SEX, SexSlotUnique.GLORY_HOLE_FUCKED, Main.game.getPlayer())));
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
					return "Standing up, [npc.name] [npc.verb(step)] over towards [npc2.name], before bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Leaning forwards, [npc.she] [npc.verb(position)] [npc.herself] so that [npc.her] mouth is just [unit.sizes] away from the hole on the opposite side of the stall,"
								+ " and, letting out [npc.a_moan+], [npc.she] [npc.verb(prepare)] to have [npc.her] [npc.asshole+] and mouth used at the same time.";
					
				} else {
					return "Pulling away from the glory holes, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] bending down and pushing [npc.her] [npc.ass+] up against [npc2.namePos] glory hole."
							+ " Leaning forwards, [npc.she] [npc.verb(position)] [npc.herself] so that [npc.her] mouth is just [unit.sizes] away from the hole on the opposite side of the stall,"
								+ " and, letting out [npc.a_moan+], [npc.she] [npc.verb(prepare)] to have [npc.her] [npc.asshole+] and mouth used at the same time.";
				}
				
			} else {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Standing up, [npc.name] [npc.verb(step)] over towards [npc2.name], before bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Letting out [npc.a_moan+], [npc.she] [npc.verb(press)] back against the wall, preparing to have [npc.her] [npc.asshole+] used.";
					
				} else {
					return "Pulling away from the glory holes, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Letting out [npc.a_moan+], [npc.she] [npc.verb(press)] back against the wall, preparing to have [npc.her] [npc.asshole+] used.";
				}
			}
		}

		@Override
		public void applyEffects() {
			Map<GameCharacter, List<SexAreaInterface>> bannedAreaMap = new HashMap<>();
			bannedAreaMap.put(Main.sex.getCharacterPerformingAction(), Util.newArrayListOfValues(SexAreaOrifice.VAGINA));

			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				GameCharacter characterFucked = Main.sex.getCharacterPerformingAction();
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
				GameCharacter characterFucking = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
				
				Main.sex.setSexManager(new SMGloryHoleSex(
						Util.newHashMapOfValues(new Value<>(characterFucked, SexSlotUnique.GLORY_HOLE_FUCKED)),
						Util.newHashMapOfValues(
								new Value<>(characterFucking, SexSlotUnique.GLORY_HOLE_FUCKING),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE))) {
					@Override
					public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
						return bannedAreaMap;
					}
					@Override
					public boolean isAreasBannedMapAppliedToSelfActions(GameCharacter character) {
						return false;
					}
				});
				
			} else {
				Main.sex.setSexManager(new SMGloryHoleSex(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_FUCKED)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_FUCKING))) {
					@Override
					public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
						return bannedAreaMap;
					}
					@Override
					public boolean isAreasBannedMapAppliedToSelfActions(GameCharacter character) {
						return false;
					}
				});
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
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& ((Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED && !Main.sex.getSexManager().getAreasBannedMap().get(Main.sex.getCharacterPerformingAction()).contains(SexAreaOrifice.ANUS))
						|| !Main.sex.getCharacterTargetedForSexAction(this).equals(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKING))
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& Main.sex.getCharacterPerformingAction().hasVagina()
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()
									&& ((NPC) Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.game.getPlayer()).getPerformingSexArea()==SexAreaOrifice.VAGINA));
//									&& ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPositionBipeds.GLORY_HOLE_SEX, SexSlotUnique.GLORY_HOLE_FUCKED, Main.game.getPlayer())));
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
					return "Standing up, [npc.name] [npc.verb(step)] over towards [npc2.name], before bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Leaning forwards, [npc.she] [npc.verb(position)] [npc.herself] so that [npc.her] mouth is just [unit.sizes] away from the hole on the opposite side of the stall,"
								+ " and, letting out [npc.a_moan+], [npc.she] [npc.verb(prepare)] to have [npc.her] [npc.pussy+] and mouth used at the same time.";
					
				} else {
					return "Pulling away from the glory holes, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] bending down and pushing [npc.her] [npc.ass+] up against [npc2.namePos] glory hole."
							+ " Leaning forwards, [npc.she] [npc.verb(position)] [npc.herself] so that [npc.her] mouth is just [unit.sizes] away from the hole on the opposite side of the stall,"
								+ " and, letting out [npc.a_moan+], [npc.she] [npc.verb(prepare)] to have [npc.her] [npc.pussy+] and mouth used at the same time.";
				}
				
			} else {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Standing up, [npc.name] [npc.verb(step)] over towards [npc2.name], before bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Letting out [npc.a_moan+], [npc.she] [npc.verb(press)] back against the wall, preparing to have [npc.her] [npc.pussy+] used.";
					
				} else {
					return "Pulling away from the glory holes, [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.sheIs] bending down and pushing [npc.her] [npc.ass+] up against the glory hole."
							+ " Letting out [npc.a_moan+], [npc.she] [npc.verb(press)] back against the wall, preparing to have [npc.her] [npc.pussy+] used.";
				}
			}
		}

		@Override
		public void applyEffects() {
			Map<GameCharacter, List<SexAreaInterface>> bannedAreaMap = new HashMap<>();
			bannedAreaMap.put(Main.sex.getCharacterPerformingAction(), Util.newArrayListOfValues(SexAreaOrifice.ANUS));

			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				GameCharacter characterFucked = Main.sex.getCharacterPerformingAction();
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
				GameCharacter characterFucking = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
				
				Main.sex.setSexManager(new SMGloryHoleSex(
						Util.newHashMapOfValues(new Value<>(characterFucked, SexSlotUnique.GLORY_HOLE_FUCKED)),
						Util.newHashMapOfValues(
								new Value<>(characterFucking, SexSlotUnique.GLORY_HOLE_FUCKING),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE))) {
					@Override
					public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
						return bannedAreaMap;
					}
					@Override
					public boolean isAreasBannedMapAppliedToSelfActions(GameCharacter character) {
						return false;
					}
				});
				
			} else {
				Main.sex.setSexManager(new SMGloryHoleSex(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_FUCKED)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_FUCKING))) {
					@Override
					public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
						return bannedAreaMap;
					}
					@Override
					public boolean isAreasBannedMapAppliedToSelfActions(GameCharacter character) {
						return false;
					}
				});
			}
		}
	};
}
