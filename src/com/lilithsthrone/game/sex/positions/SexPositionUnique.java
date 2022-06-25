package com.lilithsthrone.game.sex.positions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.dominion.CultistSexActions;
import com.lilithsthrone.game.sex.sexActions.dominion.PetMounting;
import com.lilithsthrone.game.sex.sexActions.dominion.PetOral;
import com.lilithsthrone.game.sex.sexActions.dominion.PixShower;
import com.lilithsthrone.game.sex.sexActions.dominion.RalphOral;
import com.lilithsthrone.game.sex.sexActions.dominion.RoseHandHolding;
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
			null, Util.newArrayListOfValues(RoseHandHolding.class, GenericOrgasms.class)) {
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
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			// I spent several hours trying to figure out where UtilText.parse input NPCs were set.
			// I gave up and decided to do it in here so we *KNOW* which [npc] tags are doing what.
			// There is precedent for doing this in in SexPosition.java. 
			
			List<GameCharacter> characters = new ArrayList<>();
			boolean isKneeling = false;
			// 0 - Lying on altar
			// 1 - Standing OR kneeling, as determined by `kneeling`
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			if (Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS));
			} else if(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS));
				isKneeling = true;
			} else {
				return "MISSIONARY_ALTAR_CULTIST: Invalid character positions!";
			}

			// boolean playerIsOnTheAltar = characters.get(0).isPlayer();

			StringBuilder desc = new StringBuilder();
			desc.append("[npc.NameIs] lying back on top of the chapel's altar, and ");
			if (isKneeling) {
				desc.append("[npc2.nameIs] kneeling down between [npc.hisHer] [npc.legs], ready to have some oral fun with [npc.himHer] in the missionary position.");
			} else {
				desc.append("[npc2.nameIs] standing between [npc.hisHer] [npc.legs], ready to have some fun with [npc.himHer] in the missionary position.");
			}
			return UtilText.parse(characters.get(0), characters.get(1), desc.toString());
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
			List<GameCharacter> characters = new ArrayList<>();
			boolean isKneeling = false;
			// 0 - Lying on altar
			// 1 - Standing OR kneeling, as determined by `kneeling`
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			if (Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS));
			} else if(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS));
				isKneeling = true;
			} else {
				return "MISSIONARY_ALTAR_SEALED_CULTIST: Invalid character positions!";
			}

			// boolean playerIsOnTheAltar = characters.get(0).isPlayer();

			StringBuilder desc = new StringBuilder();
			desc.append("[npc.NameIs] lying back on top of the chapel's altar, and ");
			if (isKneeling) {
				desc.append("[npc2.nameIs] kneeling down between [npc.hisHer] [npc.legs], ready to have some oral fun with [npc.himHer] in the missionary position.");
			} else {
				desc.append("[npc2.nameIs] standing between [npc.hisHer] [npc.legs], ready to have some fun with [npc.himHer] in the missionary position.");
			}
			return UtilText.parse(characters.get(0), characters.get(1), desc.toString());
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
	

}
