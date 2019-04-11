package com.lilithsthrone.game.sex.positions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * AbstractSexPositions for taurs, including taur-biped interactions.
 * 
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class SexPositionOther {

	public static final AbstractSexPosition STANDING = new AbstractSexPosition("Standing",
			true,
			SexActionPresets.positioningActionsNew,
			new ArrayList<>(),
			null) {
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			if(Sex.getSubmissiveParticipants(false).size()==2) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexSlotOther.STANDING_SUBMISSIVE), Sex.getCharacterInPosition(SexSlotOther.STANDING_SUBMISSIVE_TWO),
					"[npc.Name] and [npc2.name] are standing side-by-side, facing"));
			} else {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexSlotOther.STANDING_SUBMISSIVE),
						"[npc.NameIsFull] standing face-to-face with"));
			}
			if(Sex.getDominantParticipants(false).size()==2) {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexSlotOther.STANDING_DOMINANT), Sex.getCharacterInPosition(SexSlotOther.STANDING_DOMINANT_TWO),
						" [npc.name] and [npc2.name]."));
			} else {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexSlotOther.STANDING_DOMINANT),
						" [npc.name]."));
			}
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			if(Sex.getCharacterInPosition(SexSlotOther.STANDING_SUBMISSIVE_TWO)!=null) {
				if(Sex.getCharacterInPosition(SexSlotOther.STANDING_DOMINANT_TWO)!=null) {
					return generateSlotTargetsMap(Util.newArrayListOfValues(
							StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_SUBMISSIVE),
							StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_TWO, SexSlotOther.STANDING_SUBMISSIVE_TWO)));
				} else {
					return generateSlotTargetsMap(Util.newArrayListOfValues(
							StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_SUBMISSIVE),
							StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_SUBMISSIVE_TWO)));
				}
			} else {
				return generateSlotTargetsMap(Util.newArrayListOfValues(
						StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_SUBMISSIVE),
						StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_TWO, SexSlotOther.STANDING_SUBMISSIVE)));
			}
		}
	};
	
	public static final AbstractSexPosition ORAL = new AbstractSexPosition("Oral",
			true,
			SexActionPresets.positioningActionsNew,
			new ArrayList<>(),
			null) {
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			boolean receivingDom = Sex.isDom(Sex.getCharacterInPosition(SexSlotOther.RECEIVING_ORAL));
			Set<GameCharacter> performers;
			if(receivingDom) {
				performers = Sex.getSubmissiveParticipants(false).keySet();
			} else {
				performers = Sex.getDominantParticipants(false).keySet();
			}
			GameCharacter receiving1 = Sex.getCharacterInPosition(SexSlotOther.RECEIVING_ORAL);
			GameCharacter receiving2 = Sex.getCharacterInPosition(SexSlotOther.RECEIVING_ORAL_TWO);
			GameCharacter performing1 = Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL);
			GameCharacter performingBehind1 = Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_BEHIND);
			GameCharacter performing2 = Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_TWO);
			GameCharacter performingBehind2 = Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_BEHIND_TWO);
			GameCharacter standing1 = Sex.getCharacterInPosition(SexSlotOther.STANDING_SUBMISSIVE);
			GameCharacter standing2 = Sex.getCharacterInPosition(SexSlotOther.STANDING_SUBMISSIVE_TWO);
			
			if(receiving2!=null) {
				sb.append(UtilText.parse(receiving1, receiving2,
						"[npc.Name] and [npc2.name] are standing side-by-side, ready to receive oral from "));
				
				List<String> names = new ArrayList<>();
				for(GameCharacter performer : performers) {
					names.add(UtilText.parse(performer, "[npc.name]"));
				}
				sb.append(Util.stringsToStringList(names, false)+".");
				
			} else {
				sb.append(UtilText.parse(receiving1,
						"[npc.NameIsFull] standing before "));
				List<String> names = new ArrayList<>();
				for(GameCharacter performer : performers) {
					names.add(UtilText.parse(performer, "[npc.name]"));
				}
				sb.append(Util.stringsToStringList(names, false)
					+UtilText.parse(receiving1,", and [npc.is] ready to receive oral from ")
					+(performers.size()==1
						?UtilText.parse(performers.iterator().next(), "[npc.herHim].")
						:(performers.contains(Main.game.getPlayer())
								?"the two of you."
								:"them.")));
			}
			
			if(performing1!=null) {
				sb.append(getPositionDescription(SexSlotOther.PERFORMING_ORAL, performing1, receiving1));
			}
			if(performingBehind1!=null) {
				sb.append(getPositionBehindDescription(SexSlotOther.PERFORMING_ORAL_BEHIND, performingBehind1, receiving1));
			}
			if(receiving2!=null) {
				if(performing2!=null) {
					sb.append(getPositionDescription(SexSlotOther.PERFORMING_ORAL, performing2, receiving2));
				}
				if(performingBehind2!=null) {
					sb.append(getPositionBehindDescription(SexSlotOther.PERFORMING_ORAL_BEHIND, performingBehind2, receiving2));
				}
				if(standing2!=null) {
					sb.append(UtilText.parse(standing2, receiving2," [npc.NameIsFull] standing in front of [npc2.name], ready to busy [npc.herself] with [npc2.namePos] [npc2.breasts+] and mouth."));
				}
			} else {
				if(performing2!=null) {
					sb.append(getPositionDescription(SexSlotOther.PERFORMING_ORAL, performing2, receiving1));
				}
				if(performingBehind2!=null) {
					sb.append(getPositionBehindDescription(SexSlotOther.PERFORMING_ORAL_BEHIND, performingBehind2, receiving1));
				}
			}
			if(standing1!=null) {
				sb.append(UtilText.parse(standing1, receiving1," [npc.NameIsFull] standing in front of [npc2.name], ready to busy [npc.herself] with [npc2.namePos] [npc2.breasts+] and mouth."));
			}
			
			return sb.toString();
		}
		private String getPositionDescription(SexSlot performingSlot, GameCharacter performer, GameCharacter receiver) {
			return UtilText.parse(performer, receiver,
					receiver.getLegConfiguration().isBipedalPositionedGenitals()
						?" [npc.NameIsFull] "+(performingSlot.isStanding(performer)?"standing":"kneeling")+" in front [npc2.name], ready to put [npc.her] mouth to use on [npc2.her] genitals."
						:" [npc.NameIsFull] "+(performingSlot.isStanding(performer)?"standing":"kneeling")+" beneath [npc2.namePos] feral [npc2.legRace] body, ready to put [npc.her] mouth to use"
						+(receiver.hasPenis()
								?" and suck [npc2.her] [npc2.cock]."
								:receiver.hasBreastsCrotch()
									?" and suckle on [npc2.her] [npc2.crotchBoobs]."
									:"."));
		}
		private String getPositionBehindDescription(SexSlot performingSlot, GameCharacter performer, GameCharacter receiver) {
			return UtilText.parse(performer, receiver,
					receiver.getLegConfiguration().isBipedalPositionedGenitals()
						?" [npc.NameIsFull] "+(performingSlot.isStanding(performer)?"standing":"kneeling")+" behind [npc2.name], ready to put [npc.her] mouth to use on [npc2.her] [npc2.ass]."
						:" [npc.NameIsFull] "+(performingSlot.isStanding(performer)?"standing":"kneeling")+" behind [npc2.namePos] feral [npc2.legRace] body, ready to put [npc.her] mouth to use"
							+(receiver.hasVagina()
									?" and lick [npc2.her] [npc2.pussy]."
									:" on [npc2.her] [npc2.ass]."));
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			if(Sex.getCharacterInPosition(SexSlotOther.RECEIVING_ORAL_TWO)!=null) {
				return generateSlotTargetsMap(Util.newArrayListOfValues(
						StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.RECEIVING_ORAL),
						StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_BEHIND, SexSlotOther.RECEIVING_ORAL),
						StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_SUBMISSIVE, SexSlotOther.RECEIVING_ORAL),
						StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_TWO, SexSlotOther.RECEIVING_ORAL_TWO),
						StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_BEHIND_TWO, SexSlotOther.RECEIVING_ORAL_TWO),
						StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_SUBMISSIVE_TWO, SexSlotOther.RECEIVING_ORAL_TWO)));
			} else {
				return generateSlotTargetsMap(Util.newArrayListOfValues(
						StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.RECEIVING_ORAL),
						StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_TWO, SexSlotOther.RECEIVING_ORAL),
						StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_BEHIND, SexSlotOther.RECEIVING_ORAL),
						StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_BEHIND_TWO, SexSlotOther.RECEIVING_ORAL),
						StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_SUBMISSIVE, SexSlotOther.RECEIVING_ORAL)));
			}
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Sex.getSexPositionSlot(cumTarget)==SexSlotOther.PERFORMING_ORAL
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.PERFORMING_ORAL_TWO)
				&& (Sex.getSexPositionSlot(cumProvider)==SexSlotOther.RECEIVING_ORAL
						|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.RECEIVING_ORAL_TWO)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition ALL_FOURS = new AbstractSexPosition("",
			true,
			SexActionPresets.positioningActionsNew,
			new ArrayList<>(),
			null) {
		@Override
		public String getName() {
			boolean taurs = false;
			for(GameCharacter character : Sex.getAllParticipants(false)) {
				if(character.getLegConfiguration()==LegConfiguration.TAUR) {
					taurs = true;
				}
			}
			if(taurs) {
				return "Mounting";
			} else {
				return "Doggy-style";
			}
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			GameCharacter fucked1 = Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_FUCKED);
			GameCharacter fucked2 = Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_FUCKED_TWO);
			GameCharacter fucking1 = Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_MOUNTING);
			GameCharacter fucking2 = Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_MOUNTING_TWO);
			GameCharacter inFront1 = Sex.getCharacterInPosition(SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET);
			GameCharacter inFront2 = Sex.getCharacterInPosition(SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET_TWO);

			sb.append(UtilText.parse(fucked1,
					"[npc.NameIsFull] "
					+(SexSlotOther.ALL_FOURS_FUCKED.isStanding(fucked1)
							?"standing upright,"
							:(fucked1.getLegConfiguration().isBipedalPositionedGenitals()?"on all fours,":"lying down on [npc.her] [npc.legRace]'s body,"))));
			
			if(fucking1!=null) {
				sb.append(UtilText.parse(fucked1, fucking1,
						" presenting [npc.herself] to [npc2.name], "));

				if(inFront1!=null) {
					sb.append(UtilText.parse(fucked1, inFront1,
							"while [npc2.nameIsFull] positioned in front of [npc.herHim], ready to put [npc.her] mouth to use."));
				} else {
					sb.append(UtilText.parse(fucking1,
							fucking1.getLegConfiguration().isBipedalPositionedGenitals()
								?"ready to be fucked by [npc.herHim]."
								:"ready to be mounted and fucked by [npc.herHim]."));
				}
				
			} else if(inFront1!=null) {
				sb.append(UtilText.parse(fucked1, inFront1,
						" ready to put [npc.her] mouth to use on [npc2.namePos] genitals."));
			}
			if(fucked2!=null) {
				sb.append(UtilText.parse(fucked2,
						"<br/><br/>"
						+ "[npc.NameIsFull] "
						+(SexSlotOther.ALL_FOURS_FUCKED_TWO.isStanding(fucked2)
								?"standing"
								:(fucked2.getLegConfiguration().isBipedalPositionedGenitals()?"on all fours":"lying [npc.her] [npc.legRace]'s body down"))));

				sb.append(UtilText.parse(fucked2, fucked1,
						" beside [npc2.name], and, in a similar manner to [npc.her] partner, is"));
				
				if(fucking2!=null) {
					sb.append(UtilText.parse(fucked2, fucking2,
							" presenting [npc.herself] to [npc2.name], "));

					if(inFront2!=null) {
						sb.append(UtilText.parse(fucked2, inFront2,
								"while [npc2.nameIsFull] positioned in front of [npc.herHim], ready to use [npc.her] mouth."));
					} else {
						sb.append(UtilText.parse(fucking2,
								fucking2.getLegConfiguration().isBipedalPositionedGenitals()
									?"ready to be fucked by [npc.herHim]."
									:"ready to be mounted and fucked by [npc.herHim]."));
					}
					
				} else if(inFront2!=null) {
					sb.append(UtilText.parse(fucked2, inFront2,
							" ready to put [npc.her] mouth to use on [npc2.namePos] genitals."));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			if(Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_FUCKED_TWO)!=null) {
				return generateSlotTargetsMap(Util.newArrayListOfValues(
						StandardSexActionInteractions.fuckingTaur.getSexActionInteractions(SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.ALL_FOURS_FUCKED),
						(Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_MOUNTING_TWO)!=null
							?StandardSexActionInteractions.fuckingTaur.getSexActionInteractions(SexSlotOther.ALL_FOURS_MOUNTING_TWO, SexSlotOther.ALL_FOURS_FUCKED_TWO)
							:StandardSexActionInteractions.fuckingTaur.getSexActionInteractions(SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.ALL_FOURS_FUCKED_TWO)),
						StandardSexActionInteractions.allFoursFrontInteraction.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET),
						(Sex.getCharacterInPosition(SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET_TWO)!=null
							?StandardSexActionInteractions.allFoursFrontInteraction.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED_TWO, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET_TWO)
							:StandardSexActionInteractions.allFoursFrontInteraction.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED_TWO, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET))));
				
			} else {
				return generateSlotTargetsMap(Util.newArrayListOfValues(
						StandardSexActionInteractions.fuckingTaur.getSexActionInteractions(SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.ALL_FOURS_FUCKED),
						StandardSexActionInteractions.allFoursFrontInteraction.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET)));
			}
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			if((Sex.getSexPositionSlot(cumTarget)==SexSlotOther.ALL_FOURS_FUCKED
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.ALL_FOURS_FUCKED_TWO)) {
				// The character being fucked can use their tails or tentacles to force a creampie:
				if((Sex.getSexPositionSlot(cumProvider)==SexSlotOther.ALL_FOURS_MOUNTING
						|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.ALL_FOURS_MOUNTING_TWO)) {
					return Util.newHashMapOfValues(
							new Value<>(Tail.class, genericGroinForceCreampieAreas),
							new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				}
				// The character performing oral can use their arm(s) to force a mouth creampie:
				if((Sex.getSexPositionSlot(cumProvider)==SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET
						|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET_TWO)) {
					return Util.newHashMapOfValues(
							new Value<>(Arm.class, genericFaceForceCreampieAreas));
				}
			}
			return null;
		}
	};
}
