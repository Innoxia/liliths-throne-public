package com.lilithsthrone.game.sex.positions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.dominion.CultistSexActions;
import com.lilithsthrone.game.sex.sexActions.dominion.GloryHole;
import com.lilithsthrone.game.sex.sexActions.dominion.PetMounting;
import com.lilithsthrone.game.sex.sexActions.dominion.PetOral;
import com.lilithsthrone.game.sex.sexActions.dominion.PixShower;
import com.lilithsthrone.game.sex.sexActions.dominion.RalphOral;
import com.lilithsthrone.game.sex.sexActions.universal.HandHolding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * 
 * @since 0.1.97
 * @version 0.3.4
 * @author Innoxia
 */
public class SexPositionUnique {

	public static final AbstractSexPosition PET_MOUNTING = new AbstractSexPosition("Mounted",
			2,
			true,
			null, Util.newArrayListOfValues(PetMounting.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS), Main.sex.getCharacterInPosition(SexSlotUnique.PET_MOUNTING_HUMPING),
					"[npc.NameIs] down on all fours, and [npc.has] been mounted by [npc2.name], who's desperate to penetrate and start humping [npc.herHim].");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(SexSlotUnique.PET_MOUNTING_HUMPING, SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their tails or tentacles to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.PET_MOUNTING_HUMPING) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition PET_ORAL = new AbstractSexPosition("Pet Oral",
			2,
			true,
			null, Util.newArrayListOfValues(PetOral.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotUnique.PET_ORAL_ON_ALL_FOURS), Main.sex.getCharacterInPosition(SexSlotUnique.PET_ORAL_COCKED_LEG),
					"[npc.NameIs] down on all fours, with [npc2.namePos] [npc2.leg] hooked over [npc.her] neck, leaving [npc.her] face just [unit.sizes] away from [npc2.namePos] [npc2.cock+].");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.PET_ORAL_ON_ALL_FOURS, SexSlotUnique.PET_ORAL_COCKED_LEG));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition UNDER_DESK_RALPH = new AbstractSexPosition("Under desk",
			2,
			false,
			null, Util.newArrayListOfValues(RalphOral.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "You're kneeling under Ralph's counter, with your face just [unit.sizes] away from his crotch.";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOralRalph.getSexActionInteractions(SexSlotUnique.RALPH_SUB, SexSlotUnique.RALPH_DOM));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character taking oral can use their arms to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.RALPH_SUB
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.RALPH_DOM) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			// The character being fucked can use their legs to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.RALPH_SUB
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.RALPH_DOM) {
					return Util.newHashMapOfValues(
							new Value<>(Leg.class, genericGroinForceCreampieAreas));
				}
			return null;
		}
	};
	
	public static final AbstractSexPosition SHOWER_TIME_PIX = new AbstractSexPosition("Shower sex",
			2,
			false,
			null, Util.newArrayListOfValues(PixShower.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "You're standing with your face pressed up against one wall of the shower, and behind you, Pix is growling hungrily into your ear.";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.standingBehindCharacterFacingWall.getSexActionInteractions(SexSlotUnique.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX, SexSlotUnique.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition HANDS_ROSE = new AbstractSexPosition("Hand-holding",
			2,
			false,
			null, Util.newArrayListOfValues(HandHolding.class, GenericOrgasms.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "You and the cat-girl maid, Rose, are standing facing one another, ready to perform lewd acts with one another's hands.";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.handHolding.getSexActionInteractions(SexSlotUnique.HAND_SEX_DOM_ROSE, SexSlotUnique.HAND_SEX_SUB_ROSE));
			interactions.add(StandardSexActionInteractions.handHolding.getSexActionInteractions(SexSlotUnique.HAND_SEX_SUB_ROSE, SexSlotUnique.HAND_SEX_DOM_ROSE));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition KNEELING_ORAL_CULTIST = new AbstractSexPosition("Kneeling",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just [unit.sizes] away from [npc.her] groin.";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST, SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character taking oral can use their arms to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition MISSIONARY_ALTAR_CULTIST = new AbstractSexPosition("Missionary on altar",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {//TODO
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR) {
				if(Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.game.getPlayer()))==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
					return "You're lying back on top of the chapel's altar, and [npc.namePos] standing between your [pc.legs], ready to have some fun with you in the missionary position.";
				} else {
					return "You're lying back on top of the chapel's altar, and [npc.namePos] kneeling down between your [pc.legs], ready to have some oral fun with you in the missionary position.";
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're standing between [npc.her] [npc.legs], ready to have some fun in the missionary position.";
				
			} else {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're kneeling down between [npc.her] [npc.legs], ready to have some oral fun in the missionary position.";
			}
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their legs, tails, or tentacles to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				
			// The character on top can use their body weight to force a creampie:
			} else if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition MISSIONARY_ALTAR_SEALED_CULTIST = new AbstractSexPosition("Missionary on altar",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {//TODO
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
				return "You're lying back on top of the chapel's altar, and [npc.namePos] standing between your [pc.legs], ready to have some fun with you in the missionary position.";
			} else if(Main.sex.getSexPositionSlot(Main.game.getPlayer())==SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS) {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're standing between [npc.her] [npc.legs], ready to have some fun in the missionary position.";
			} else {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're kneeling down between [npc.her] [npc.legs], ready to have some oral fun in the missionary position.";
			}
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if(Main.sex.getSexPositionSlot(performer) == SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
				if((action.getActionType()==SexActionType.ONGOING
						|| action.getActionType()==SexActionType.START_ONGOING
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION
						|| action.getActionType()==SexActionType.REQUIRES_EXPOSED
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED)) {
					return true;
				}
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character on top can use their body weight to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	
	
	public static final AbstractSexPosition GLORY_HOLE = new AbstractSexPosition("Glory hole oral",
			3,
			true,
			null, Util.newArrayListOfValues(GloryHole.class)) {
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			List<SexActionInterface> blockedActions = Util.newArrayListOfValues(
					GenericOrgasms.GENERIC_PREPARATION_DENIAL,
					PlayerTalk.PLAYER_OFFER_ANAL,
					PlayerTalk.PLAYER_OFFER_NAIZURI,
					PlayerTalk.PLAYER_OFFER_NIPPLE,
					PlayerTalk.PLAYER_OFFER_ORAL,
					PlayerTalk.PLAYER_OFFER_PAIZURI,
					PlayerTalk.PLAYER_OFFER_VAGINAL,
					PlayerTalk.PLAYER_REQUEST_ANAL,
					PlayerTalk.PLAYER_REQUEST_ORAL,
					PlayerTalk.PLAYER_REQUEST_VAGINAL);
			if(blockedActions.contains(action)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			List<GameCharacter> characters = new ArrayList<>();
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_KNEELING));
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			
			StringBuilder sb = new StringBuilder();

			boolean c1Taur = characters.get(1).isTaur();
			if(Main.sex.getTotalParticipantCount(false)==3) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
				
				boolean c2Taur = characters.get(2).isTaur();
				
				if(c1Taur || c2Taur) {
					sb.append("[npc.NameIsFull] kneeling on the floor, ready to service whatever sets of genitals [npc2.name] and [npc3.name] put through the glory holes to either side of [npc.herHim].");
					for(int i=1; i<3; i++) {
						GameCharacter character = characters.get(i);
						if(character.hasPenis() && character.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							sb.append(UtilText.parse(characters.get(0), character,
									"<br/><i>As [npc2.namePos] [npc2.cock+] is positioned below [npc2.her] lower [npc2.legRace]'s body,"
										+ " [npc2.name] quickly [npc2.verb(discover)] that [npc2.sheIs] unable to get into a position in which [npc2.she] could push [npc2.her] cock through the gloryhole."
									+ " Instead, [npc2.she] [npc2.has] to make do with turning around and presenting [npc2.her] [npc2.pussy+] to [npc.name]...</i>"));
							
						} else {
							sb.append(UtilText.parse(characters.get(0), character,
									"<br/><i>Due to possessing the lower body of [npc2.a_legRace],"
									+ " [npc2.name] [npc2.verb(make)] a great deal of noise as [npc2.she] awkwardly [npc2.verb(turn)] around in the cramped stall and [npc2.verb(present)] [npc2.her] [npc2.pussy+] to [npc.name]...</i>"));
						}
					}
					
				} else {
					if(characters.get(1).hasPenis() && characters.get(1).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
							&& characters.get(2).hasPenis() && characters.get(2).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						sb.append("[npc.NameIsFull] kneeling on the floor, ready to serve [npc2.namePos] [npc2.cock+] on one side, an [npc3.namePos] [npc3.cock+] on the other.");
					} else {
						sb.append("[npc.NameIsFull] kneeling on the floor, ready to service whatever sets of genitals [npc2.name] and [npc3.name] put through the glory holes to either side of [npc.herHim].");
					}
				}
				
			} else {
				if(c1Taur) {
					sb.append("[npc.NameIsFull] kneeling on the floor with [npc.her] mouth pushed up against the glory hole, ready to service whatever set of genitals [npc2.name] [npc2.verb(present)] [npc.herHim] with.");
					GameCharacter character = characters.get(1);
					if(character.hasPenis() && character.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						sb.append(UtilText.parse(characters.get(0), character,
								"<br/><i>As [npc2.namePos] [npc2.cock+] is positioned below [npc2.her] lower [npc2.legRace]'s body,"
									+ " [npc2.name] quickly [npc2.verb(discover)] that [npc2.sheIs] unable to get into a position in which [npc2.she] could push [npc2.her] cock through the gloryhole."
								+ " Instead, [npc2.she] [npc2.has] to make do with turning around and presenting [npc2.her] [npc2.pussy+] to [npc.name]...</i>"));
						
					} else {
						sb.append(UtilText.parse(characters.get(0), character,
								"<br/><i>Due to possessing the lower body of [npc2.a_legRace],"
								+ " [npc2.name] [npc2.verb(make)] a great deal of noise as [npc2.she] awkwardly [npc2.verb(turn)] around in the cramped stall and [npc2.verb(present)] [npc2.her] [npc2.pussy+] to [npc.name]...</i>"));
					}
					
				} else {
					sb.append("[npc.NameIsFull] kneeling on the floor with [npc.her] mouth pushed up against the glory hole, ready to service whatever set of genitals [npc2.name] [npc2.verb(present)] [npc.herHim] with.");
				}
			}
			
			return UtilText.parse(characters, sb.toString());
		}
		
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_KNEELING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_KNEELING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
			
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition GLORY_HOLE_SEX = new AbstractSexPosition("Glory hole sex",
			3,
			true,
			null, Util.newArrayListOfValues(GloryHole.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			List<GameCharacter> characters = new ArrayList<>();
			
			boolean analFucking = false;
			if(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKED)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKED));
			} else {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED));
				analFucking = true;
			}
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKING));
			
			StringBuilder sb = new StringBuilder();
			
			if(analFucking) {
				sb.append("[npc.NameIsFull] pressing [npc.her] [npc.asshole+] up against the glory hole behind [npc.herHim],");
			} else {
				sb.append("[npc.NameIsFull] pressing [npc.her] [npc.pussy+] up against the glory hole behind [npc.herHim],");
			}
			
			if(characters.get(1).hasPenis() && characters.get(1).isAbleToAccessCoverableArea(CoverableArea.PENIS, true) && !characters.get(1).isTaur()) {
				sb.append(" ready to have [npc2.name] thrust forwards and sink [npc2.her] [npc2.cock+] into it.");
			} else {
				sb.append(" ready to have [npc2.name] start using it.");
			}

			if(Main.sex.getTotalParticipantCount(false)==3) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
				sb.append(" On the other side of the stall, [npc.namePos] leaning down to continue pleasuring [npc3.namePos] gloryhole with [npc.her] mouth.");
			}
			
			return UtilText.parse(characters, sb.toString());
		}
		
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.gettingFuckedGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_FUCKED, SexSlotUnique.GLORY_HOLE_FUCKING));
			interactions.add(StandardSexActionInteractions.gettingAnallyFuckedGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED, SexSlotUnique.GLORY_HOLE_FUCKING));
			
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_FUCKED, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			
			return generateSlotTargetsMap(interactions);
		}
	};

}
