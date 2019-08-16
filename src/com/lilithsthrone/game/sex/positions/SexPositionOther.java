package com.lilithsthrone.game.sex.positions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.universal.ChairSex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * AbstractSexPositions for taurs, including taur-biped interactions.
 * 
 * @since 0.3.1
 * @version 0.3.3.10
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
			List<String> subNames = new ArrayList<>();
			for(GameCharacter sub : Sex.getSubmissiveParticipants(false).keySet()) {
				subNames.add(UtilText.parse(sub, "[npc.name]"));
			}
			List<String> domNames = new ArrayList<>();
			for(GameCharacter dom : Sex.getDominantParticipants(false).keySet()) {
				subNames.add(UtilText.parse(dom, "[npc.name]"));
			}
			
			if(Sex.getSubmissiveParticipants(false).size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subNames, false))+" are standing side-by-side, facing");
			} else {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexSlotOther.STANDING_SUBMISSIVE),
						"[npc.NameIsFull] standing face-to-face with"));
			}
			if(Sex.getDominantParticipants(false).size()>=2) {
				sb.append(Util.stringsToStringList(domNames, false)+".");
			} else {
				sb.append(UtilText.parse(Sex.getCharacterInPosition(SexSlotOther.STANDING_DOMINANT),
						" [npc.name]."));
			}
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			return generateSlotTargetsMap(Util.newArrayListOfValues(
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_SUBMISSIVE),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_SUBMISSIVE_TWO),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_SUBMISSIVE_THREE),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT, SexSlotOther.STANDING_SUBMISSIVE_FOUR),
					
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_TWO, SexSlotOther.STANDING_SUBMISSIVE),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_TWO, SexSlotOther.STANDING_SUBMISSIVE_TWO),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_TWO, SexSlotOther.STANDING_SUBMISSIVE_THREE),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_TWO, SexSlotOther.STANDING_SUBMISSIVE_FOUR),

					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_THREE, SexSlotOther.STANDING_SUBMISSIVE),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_THREE, SexSlotOther.STANDING_SUBMISSIVE_TWO),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_THREE, SexSlotOther.STANDING_SUBMISSIVE_THREE),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_THREE, SexSlotOther.STANDING_SUBMISSIVE_FOUR),

					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_FOUR, SexSlotOther.STANDING_SUBMISSIVE),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_FOUR, SexSlotOther.STANDING_SUBMISSIVE_TWO),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_FOUR, SexSlotOther.STANDING_SUBMISSIVE_THREE),
					StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.STANDING_DOMINANT_FOUR, SexSlotOther.STANDING_SUBMISSIVE_FOUR)
					));
		}
		@Override
		public int getMaximumSlots() {
			return 8;
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
		@Override
		public int getMaximumSlots() {
			return 8;
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
		public int getMaximumSlots() {
			return 6;
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			
			if(Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_FUCKED_TWO)!=null) {
				if(Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_FUCKED).getLegConfiguration().isBipedalPositionedGenitals() == Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_FUCKED_TWO).getLegConfiguration().isBipedalPositionedGenitals()) {
					interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED, SexSlotOther.ALL_FOURS_FUCKED_TWO));
				}
				
				interactions.add(StandardSexActionInteractions.fuckingTaur.getSexActionInteractions(SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.ALL_FOURS_FUCKED));
				
				if(Sex.getCharacterInPosition(SexSlotOther.ALL_FOURS_MOUNTING_TWO)!=null) {
					interactions.add(StandardSexActionInteractions.fuckingTaur.getSexActionInteractions(SexSlotOther.ALL_FOURS_MOUNTING_TWO, SexSlotOther.ALL_FOURS_FUCKED_TWO));
				} else {
					interactions.add(StandardSexActionInteractions.fuckingTaur.getSexActionInteractions(SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.ALL_FOURS_FUCKED_TWO));
				}
				interactions.add(StandardSexActionInteractions.allFoursFrontInteraction.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET));

				if(Sex.getCharacterInPosition(SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET_TWO)!=null) {
					interactions.add(StandardSexActionInteractions.allFoursFrontInteraction.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED_TWO, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET_TWO));
				} else {
					interactions.add(StandardSexActionInteractions.allFoursFrontInteraction.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED_TWO, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET));
				}
				
			} else {
				interactions.add(StandardSexActionInteractions.fuckingTaur.getSexActionInteractions(SexSlotOther.ALL_FOURS_MOUNTING, SexSlotOther.ALL_FOURS_FUCKED));
				interactions.add(StandardSexActionInteractions.allFoursFrontInteraction.getSexActionInteractions(SexSlotOther.ALL_FOURS_FUCKED, SexSlotOther.IN_FRONT_OF_ALL_FOURS_TARGET));
			}

			return generateSlotTargetsMap(interactions);
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
	
	//TODO
	/**
	 * Contains support for:<br/>
	 * <b>Face-sitting</b><br/>
	 * <b>Cow-girl</b><br/>
	 * <b>--- NOT YET IMPLEMENTED BELOW ---</b><br/>
	 * <b>Missionary</b><br/>
	 * <b>Reverse cow-girl</b><br/>
	 * <b>Sixty-nine</b><br/>
	 * <b>Mating press</b><br/>
	 * <b>Scissoring</b>
	 */
	public static final AbstractSexPosition LYING_DOWN = new AbstractSexPosition("",
			true,
			SexActionPresets.positioningActionsNew,
			new ArrayList<>(),
			null) {
		@Override
		public String getName() {
			if(Sex.getCharacterInPosition(SexSlotOther.COWGIRL)!=null) {
				return "Cowgirl";
			}
			if(Sex.getCharacterInPosition(SexSlotOther.FACE_SITTING)!=null) {
				return "Face-sitting";
			}
			return "Missionary";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			GameCharacter fucked1 = Sex.getCharacterInPosition(SexSlotOther.LYING_DOWN);
			GameCharacter cowgirl1 = Sex.getCharacterInPosition(SexSlotOther.COWGIRL);
			GameCharacter faceSitting1 = Sex.getCharacterInPosition(SexSlotOther.FACE_SITTING);
			GameCharacter faceSittingReverse1 = Sex.getCharacterInPosition(SexSlotOther.FACE_SITTING_REVERSE);

			if(fucked1.getLegConfiguration().isBipedalPositionedGenitals()) {
				sb.append(UtilText.parse(fucked1, "[npc.NameIsFull] lying down on [npc.her] back"));
			} else {
				sb.append(UtilText.parse(fucked1, "[npc.NameHasFull] positioned [npc.herself] so that [npc.her] lower, animalistic body is lying down on its back"));
			}
			
			boolean foundExtension = false;
			
			if(cowgirl1!=null) {
				if(cowgirl1.getLegConfiguration().isBipedalPositionedGenitals()) {
					sb.append(UtilText.parse(cowgirl1, fucked1,
							", while [npc.nameHasFull] straddled [npc2.her] groin, ready to start riding [npc2.herHim] in the cowgirl position."));
				} else {
					sb.append(UtilText.parse(cowgirl1, fucked1,
							", while [npc.nameHasFull] stepped over the top of [npc2.herHim] with [npc.her] lower feral body, before dropping [npc.her] groin down to start riding [npc2.herHim] in the cowgirl position."));
				}
				foundExtension = true;
			}

			if(faceSitting1!=null) {
				if(!foundExtension) {
					if(faceSitting1.getLegConfiguration().isBipedalPositionedGenitals()) {
						sb.append(UtilText.parse(faceSitting1, fucked1,
								", while [npc.nameHasFull] sat down on [npc2.her] [npc2.face], positioned such that [npc.sheIs] looking down into [npc2.namePos] [npc2.eyes]."));
					} else {
						sb.append(UtilText.parse(faceSitting1, fucked1,
								", while [npc.nameHasFull] moved around above where [npc2.sheIs] lying down, before backing up with [npc.her] lower feral body and planting [npc.her] groin down on [npc2.her] [npc2.face]."));
					}
				} else {
					if(faceSitting1.getLegConfiguration().isBipedalPositionedGenitals()) {
						sb.append(UtilText.parse(faceSitting1, fucked1,
								" [npc.Name], meanwhile, [npc.has] sat down on [npc2.her] [npc2.face], positioned such that [npc.sheIs] looking down into [npc2.namePos] [npc2.eyes]."));
					} else {
						sb.append(UtilText.parse(faceSitting1, fucked1,
								"[npc.Name], meanwhile, [npc.has] moved around above where [npc2.sheIs] lying down, before backing up with [npc.her] lower feral body and planting [npc.her] groin down on [npc2.her] [npc2.face]."));
					}
				}
				foundExtension = true;
			}

			if(faceSittingReverse1!=null) {
				if(!foundExtension) {
					if(faceSittingReverse1.getLegConfiguration().isBipedalPositionedGenitals()) {
						sb.append(UtilText.parse(faceSittingReverse1, fucked1,
								", while [npc.nameHasFull] sat down on [npc2.her] [npc2.face], positioned such that [npc.sheIs] looking down at [npc2.namePos] lower body."));
					} else {
						sb.append(UtilText.parse(faceSittingReverse1, fucked1,
								", while [npc.nameHasFull] stepped fully over the top of [npc2.herHim], before planting [npc.her] feral groin down on [npc2.her] [npc2.face]."));
					}
				} else {
					if(faceSittingReverse1.getLegConfiguration().isBipedalPositionedGenitals()) {
						sb.append(UtilText.parse(faceSittingReverse1, fucked1,
								" [npc.Name], meanwhile, [npc.has] sat down on [npc2.her] [npc2.face], positioned such that [npc.sheIs] looking down at [npc2.namePos] lower body."));
					} else {
						sb.append(UtilText.parse(faceSittingReverse1, fucked1,
								"[npc.Name], meanwhile, [npc.has] stepped fully over the top of [npc2.herHim], before planting [npc.her] feral groin down on [npc2.her] [npc2.face]."));
					}
				}
				foundExtension = true;
			}
			
			return sb.toString();
		}
		@Override
		public int getMaximumSlots() {
			return 6; //TODO
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			GameCharacter cowgirl = Sex.getCharacterInPosition(SexSlotOther.COWGIRL);
			GameCharacter faceSitting = Sex.getCharacterInPosition(SexSlotOther.FACE_SITTING);
			GameCharacter faceSittingReverse = Sex.getCharacterInPosition(SexSlotOther.FACE_SITTING_REVERSE);
			
			if(cowgirl!=null) {
				if(cowgirl.getLegConfiguration().isBipedalPositionedGenitals()) {
					interactions.add(StandardSexActionInteractions.cowgirlRiding.getSexActionInteractions(SexSlotOther.COWGIRL, SexSlotOther.LYING_DOWN));
				} else {
					interactions.add(StandardSexActionInteractions.cowgirlTaurRiding.getSexActionInteractions(SexSlotOther.COWGIRL, SexSlotOther.LYING_DOWN));
				}
			}

			if(faceSitting!=null) {
				if(faceSitting.getLegConfiguration().isBipedalPositionedGenitals()) {
					interactions.add(StandardSexActionInteractions.faceSittingRiding.getSexActionInteractions(SexSlotOther.FACE_SITTING, SexSlotOther.LYING_DOWN));
				} else {
					interactions.add(StandardSexActionInteractions.faceSittingTaurRiding.getSexActionInteractions(SexSlotOther.FACE_SITTING, SexSlotOther.LYING_DOWN));
				}
				if(cowgirl!=null && cowgirl.getLegConfiguration().isBipedalPositionedGenitals()) {
					if(faceSitting.getLegConfiguration().isBipedalPositionedGenitals()) {
						interactions.add(StandardSexActionInteractions.groinCharacterInteractingWithFaceSitting.getSexActionInteractions(SexSlotOther.COWGIRL, SexSlotOther.FACE_SITTING));
					} else {
						interactions.add(StandardSexActionInteractions.groinCharacterInteractingWithFaceSittingTaur.getSexActionInteractions(SexSlotOther.COWGIRL, SexSlotOther.FACE_SITTING));
					}
				}
			}

			if(faceSittingReverse!=null) {
				if(faceSittingReverse.getLegConfiguration().isBipedalPositionedGenitals()) {
					interactions.add(StandardSexActionInteractions.faceSittingReverseRiding.getSexActionInteractions(SexSlotOther.FACE_SITTING_REVERSE, SexSlotOther.LYING_DOWN));
				} else {
					interactions.add(StandardSexActionInteractions.faceSittingReverseTaurRiding.getSexActionInteractions(SexSlotOther.FACE_SITTING_REVERSE, SexSlotOther.LYING_DOWN));
				}
				if(cowgirl!=null && cowgirl.getLegConfiguration().isBipedalPositionedGenitals()) {
					if(faceSittingReverse.getLegConfiguration().isBipedalPositionedGenitals()) {
						interactions.add(StandardSexActionInteractions.groinCharacterInteractingWithFaceSittingReversed.getSexActionInteractions(SexSlotOther.COWGIRL, SexSlotOther.FACE_SITTING_REVERSE));
					} else {
						interactions.add(StandardSexActionInteractions.groinCharacterInteractingWithFaceSittingTaur.getSexActionInteractions(SexSlotOther.COWGIRL, SexSlotOther.FACE_SITTING_REVERSE));
					}
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character in cowgirl can use their weight to force a creampie:
			if(Sex.getSexPositionSlot(cumTarget)==SexSlotOther.COWGIRL) {
				if((Sex.getSexPositionSlot(cumProvider)==SexSlotOther.LYING_DOWN)) {
					return Util.newHashMapOfValues(
							new Value<>(Skin.class, genericGroinForceCreampieAreas));
				}
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition SITTING = new AbstractSexPosition("Sitting Down",
			true,
			Util.newArrayListOfValues(ChairSex.class),
			new ArrayList<>(),
			null) {
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			// First character sitting down, with anyone in their lap/face:
			GameCharacter sitting = Sex.getCharacterInPosition(SexSlotOther.SITTING);
			sb.append(UtilText.parse(sitting,
					"[npc.NameIsFull] sitting down"));

			GameCharacter sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP);
			if(sub1!=null) {
				if(sub1.getLegConfiguration().isBipedalPositionedGenitals()) {
					sb.append(UtilText.parse(sitting, sub1,
							", with [npc2.name] sitting in [npc.her] lap, face-to-face with [npc.herHim]."));
				} else {
					sb.append(UtilText.parse(sitting, sub1,
							", while [npc2.name] [npc2.has] stood up and turned [npc2.her] animalistic lower body around, before lowering [npc2.her] groin into [npc.namePos] lap."));
				}
			} else {
				sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL);
				if(sub1!=null) {
					sb.append(UtilText.parse(sitting, sub1,
							", while [npc2.nameHasFull] stood up and turned [npc2.her] animalistic lower body around in order to present [npc2.her] genitals right in front of [npc.her] [npc.face]."));
				} else {
					sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_BETWEEN_LEGS);
					if(sub1!=null) {
						if(sub1.getLegConfiguration().isBipedalPositionedGenitals()) {
							sb.append(UtilText.parse(sitting, sub1,
									", while [npc2.nameIsFull] "+(SexSlotOther.SITTING_BETWEEN_LEGS.isStanding(sub1)?"standing":"kneeling")+" between [npc.her] spread [npc.legs], ready to fuck [npc.herHim]."));
						} else {
							sb.append(UtilText.parse(sitting, sub1,
									", while [npc2.nameHasFull] used [npc2.her] animalistic lower body to step over the top of [npc.herHim], ready to fuck [npc.herHim]."));
						}
					} else {
						sb.append(", ready to have some fun in this relaxed position.");
					}
				}
			}

			// Second character sitting down, with anyone in their lap/face:
			GameCharacter sitting2 = Sex.getCharacterInPosition(SexSlotOther.SITTING_TWO);
			if(sitting2!=null) {
				sb.append(UtilText.parse(sitting2, sitting,
						"[npc.NameIsFull] also sitting down next to [npc2.name]"));
	
				sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_TWO);
				if(sub1!=null) {
					if(sub1.getLegConfiguration().isBipedalPositionedGenitals()) {
						sb.append(UtilText.parse(sitting, sub1,
								", while [npc2.nameIsFull] sitting in [npc.her] lap, facing [npc.herHim]."));
					} else {
						sb.append(UtilText.parse(sitting, sub1,
								", and [npc.has] [npc2.namePos] animalistic genitals backed up and lowered into [npc.her] lap."));
					}
				} else {
					sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_TWO);
					if(sub1!=null) {
						sb.append(UtilText.parse(sitting, sub1,
								", and [npc.has] [npc2.namePos] animalistic genitals backed up and positioned right in front of [npc.her] [npc.face]."));
					} else {
						sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_BETWEEN_LEGS_TWO);
						if(sub1!=null) {
							if(sub1.getLegConfiguration().isBipedalPositionedGenitals()) {
								sb.append(UtilText.parse(sitting, sub1,
										", looking up at [npc2.name] as [npc2.she] "+(SexSlotOther.SITTING_BETWEEN_LEGS_TWO.isStanding(sub1)?"[npc.verb(stand)]":"[npc.verb(kneel)]")+" in front of [npc.herHim]."));
							} else {
								sb.append(UtilText.parse(sitting, sub1,
										", while [npc2.nameHasFull] stepped over [npc.herHim] with [npc2.her] animalistic lower body, ready to fuck [npc.herHim]."));
							}
						} else {
							sb.append(".");
						}
					}
				}
			}

			// Third character sitting down, with anyone in their lap/face:
			GameCharacter sitting3 = Sex.getCharacterInPosition(SexSlotOther.SITTING_THREE);
			if(sitting2!=null) {
				sb.append(UtilText.parse(Util.newArrayListOfValues(sitting3, sitting2, sitting),
						"Beside [npc3.name] and [npc2.name], [npc3.nameIsFull] also sitting down"));
	
				sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_THREE);
				if(sub1!=null) {
					if(sub1.getLegConfiguration().isBipedalPositionedGenitals()) {
						sb.append(UtilText.parse(sitting, sub1,
								", and [npc.has] [npc2.name] sitting in [npc.her] lap, with [npc2.her] [npc2.face] just [style.units] away from [npc.hers]."));
					} else {
						sb.append(UtilText.parse(sitting, sub1,
								", while [npc2.name] [npc2.has] stood up and turned [npc2.her] animalistic lower body around, before lowering [npc2.her] groin into [npc.namePos] lap."));
					}
				} else {
					sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_THREE);
					if(sub1!=null) {
						sb.append(UtilText.parse(sitting, sub1,
								", and [npc.is] looking forwards right at [npc2.namePos] animalistic genitals, which have been backed up right in front of [npc.her] [npc.face]."));
					} else {
						sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_BETWEEN_LEGS_THREE);
						if(sub1!=null) {
							if(sub1.getLegConfiguration().isBipedalPositionedGenitals()) {
								sb.append(UtilText.parse(sitting, sub1,
										", spreading [npc.her] [npc.legs] for [npc2.name] as [npc.she] "+(SexSlotOther.SITTING_BETWEEN_LEGS_THREE.isStanding(sub1)?"[npc.verb(stand)]":"[npc.verb(kneel)]")+" before [npc.herHim]."));
							} else {
								sb.append(UtilText.parse(sitting, sub1,
										", while [npc2.nameHasFull] used [npc2.her] animalistic lower body to step over the top of [npc.herHim], ready to fuck [npc.herHim]."));
							}
						} else {
							sb.append(".");
						}
					}
				}
			}

			// Fourth character sitting down, with anyone in their lap/face:
			GameCharacter sitting4 = Sex.getCharacterInPosition(SexSlotOther.SITTING_FOUR);
			if(sitting2!=null) {
				sb.append(UtilText.parse(Util.newArrayListOfValues(sitting4),
						"Finishing off the group of those sitting down is [npc.name]"));
	
				sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_FOUR);
				if(sub1!=null) {
					if(sub1.getLegConfiguration().isBipedalPositionedGenitals()) {
						sb.append(UtilText.parse(sitting, sub1,
								", who [npc.has] [npc2.name] sitting in [npc.her] lap, face-to-face with [npc.herHim]."));
					} else {
						sb.append(UtilText.parse(sitting, sub1,
								", and [npc.has] [npc2.namePos] animalistic genitals backed up and lowered into [npc.her] lap."));
					}
				} else {
					sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_FOUR);
					if(sub1!=null) {
						sb.append(UtilText.parse(sitting, sub1,
								", who [npc.has] [npc2.namePos] animalistic genitals hovering just [style.units] away from [npc.her] [npc.face]."));
					} else {
						sub1 = Sex.getCharacterInPosition(SexSlotOther.SITTING_BETWEEN_LEGS_FOUR);
						if(sub1!=null) {
							if(sub1.getLegConfiguration().isBipedalPositionedGenitals()) {
								sb.append(UtilText.parse(sitting, sub1,
										", spreading [npc.her] [npc.legs] and looking up at [npc2.name] as [npc2.she] "+(SexSlotOther.SITTING_BETWEEN_LEGS_FOUR.isStanding(sub1)?"[npc.verb(stand)]":"[npc.verb(kneel)]")+" in front of [npc.herHim]."));
							} else {
								sb.append(UtilText.parse(sitting, sub1,
										", while [npc2.nameHasFull] stepped over [npc.herHim] with [npc2.her] animalistic lower body, ready to fuck [npc.herHim]."));
							}
						} else {
							sb.append(".");
						}
					}
				}
			}

			// Finish off by describing who is kneeling down ready to perform oral:
			GameCharacter performingOral = Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL);
			if(performingOral!=null) {
				GameCharacter performingOral2 = Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_TWO);
				if(performingOral2==null) {
					sb.append(UtilText.parse(performingOral, sitting,
							" [npc.NameIsFull] "+(SexSlotOther.PERFORMING_ORAL.isStanding(performingOral)?"standing":"kneeling")+" before [npc2.name], ready to perform oral on [npc2.herHim]."));
				} else {
					GameCharacter performingOral3 = Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_THREE);
					if(performingOral3==null) {
						boolean bothStanding = SexSlotOther.PERFORMING_ORAL.isStanding(performingOral)==SexSlotOther.PERFORMING_ORAL_TWO.isStanding(performingOral2);
						sb.append(UtilText.parse(Util.newArrayListOfValues(performingOral, performingOral2, sitting),
								" [npc.Name] and [npc2.name] are "
									+(bothStanding
										?"both "+(SexSlotOther.PERFORMING_ORAL.isStanding(performingOral)?"standing":"kneeling")
										:(SexSlotOther.PERFORMING_ORAL.isStanding(performingOral)?"standing and kneeling":"kneeling and standing"))
									+" before [npc2.name], ready to perform oral on [npc2.herHim]."));
					} else {
						GameCharacter performingOral4 = Sex.getCharacterInPosition(SexSlotOther.PERFORMING_ORAL_FOUR);
						if(performingOral4==null) {
							sb.append(UtilText.parse(Util.newArrayListOfValues(performingOral, performingOral2, performingOral3, sitting),
									" [npc.Name], [npc2.name], and [npc3.name] are all positioned before [npc4.name], ready to perform oral on [npc4.herHim]."));
						} else {
							sb.append(UtilText.parse(Util.newArrayListOfValues(performingOral, performingOral2, performingOral3, performingOral4, sitting),
									" [npc.Name], [npc2.name], [npc3.name], and [npc4.name] are all positioned before [npc5.name], ready to perform oral on [npc5.herHim]."));
						}
					}
				}
			}
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			// All performing oral can interact with one another and the sitting characters who don't have a sitting in lap
			
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			// Only not free if using the standing fucking position
			boolean performerFree1 = true;
			boolean performerFree2 = true;
			boolean performerFree3 = true;
			boolean performerFree4 = true;
			
			if(Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP)!=null) {
				if(Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP).getLegConfiguration().isBipedalPositionedGenitals()) {
					interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotOther.SITTING_IN_LAP, SexSlotOther.SITTING));
				} else {
					interactions.add(StandardSexActionInteractions.sittingInLapTaur.getSexActionInteractions(SexSlotOther.SITTING_IN_LAP, SexSlotOther.SITTING));
				}
			} else if(Sex.getCharacterInPosition(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL)!=null) {
				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotOther.SITTING, SexSlotOther.SITTING_TAUR_PRESENTING_ORAL));
			} else if(Sex.getCharacterInPosition(SexSlotOther.SITTING_BETWEEN_LEGS)!=null) {
				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotOther.SITTING, SexSlotOther.SITTING_BETWEEN_LEGS));
				performerFree1 = false;
			}
			
			if(Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_TWO)!=null) {
				if(Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_TWO).getLegConfiguration().isBipedalPositionedGenitals()) {
					interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotOther.SITTING_IN_LAP_TWO, SexSlotOther.SITTING_TWO));
				} else {
					interactions.add(StandardSexActionInteractions.sittingInLapTaur.getSexActionInteractions(SexSlotOther.SITTING_IN_LAP_TWO, SexSlotOther.SITTING_TWO));
				}
			} else if(Sex.getCharacterInPosition(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_TWO)!=null) {
				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotOther.SITTING_TWO, SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_TWO));
			} else if(Sex.getCharacterInPosition(SexSlotOther.SITTING_BETWEEN_LEGS_TWO)!=null) {
				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotOther.SITTING_TWO, SexSlotOther.SITTING_BETWEEN_LEGS_TWO));
				performerFree2 = false;
			}
			
			if(Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_THREE)!=null) {
				if(Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_THREE).getLegConfiguration().isBipedalPositionedGenitals()) {
					interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotOther.SITTING_IN_LAP_THREE, SexSlotOther.SITTING_THREE));
				} else {
					interactions.add(StandardSexActionInteractions.sittingInLapTaur.getSexActionInteractions(SexSlotOther.SITTING_IN_LAP_THREE, SexSlotOther.SITTING_THREE));
				}
			} else if(Sex.getCharacterInPosition(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_THREE)!=null) {
				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotOther.SITTING_THREE, SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_THREE));
			} else if(Sex.getCharacterInPosition(SexSlotOther.SITTING_BETWEEN_LEGS_THREE)!=null) {
				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotOther.SITTING_THREE, SexSlotOther.SITTING_BETWEEN_LEGS_THREE));
				performerFree3 = false;
			}
			
			if(Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_FOUR)!=null) {
				if(Sex.getCharacterInPosition(SexSlotOther.SITTING_IN_LAP_FOUR).getLegConfiguration().isBipedalPositionedGenitals()) {
					interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotOther.SITTING_IN_LAP_FOUR, SexSlotOther.SITTING_FOUR));
				} else {
					interactions.add(StandardSexActionInteractions.sittingInLapTaur.getSexActionInteractions(SexSlotOther.SITTING_IN_LAP_FOUR, SexSlotOther.SITTING_FOUR));
				}
			} else if(Sex.getCharacterInPosition(SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_FOUR)!=null) {
				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotOther.SITTING_FOUR, SexSlotOther.SITTING_TAUR_PRESENTING_ORAL_FOUR));
			} else if(Sex.getCharacterInPosition(SexSlotOther.SITTING_BETWEEN_LEGS_FOUR)!=null) {
				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotOther.SITTING_FOUR, SexSlotOther.SITTING_BETWEEN_LEGS_FOUR));
				performerFree4 = false;
			}
			
			if(performerFree1) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.SITTING));
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.PERFORMING_ORAL_TWO));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.SITTING_TWO));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.PERFORMING_ORAL_THREE));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL, SexSlotOther.SITTING_FOUR));
				}
			}
			if(performerFree2) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_TWO, SexSlotOther.SITTING_TWO));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_TWO, SexSlotOther.SITTING));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_TWO, SexSlotOther.PERFORMING_ORAL_THREE));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_TWO, SexSlotOther.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_TWO, SexSlotOther.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_TWO, SexSlotOther.SITTING_FOUR));
				}
			}
			if(performerFree3) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_THREE, SexSlotOther.SITTING_THREE));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_THREE, SexSlotOther.SITTING));
				}
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_THREE, SexSlotOther.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_THREE, SexSlotOther.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_THREE, SexSlotOther.SITTING_FOUR));
				}
			}
			if(performerFree4) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_FOUR, SexSlotOther.SITTING_FOUR));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_FOUR, SexSlotOther.SITTING));
				}
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_FOUR, SexSlotOther.SITTING_TWO));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotOther.PERFORMING_ORAL_FOUR, SexSlotOther.SITTING_THREE));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Sex.getSexPositionSlot(cumTarget)==SexSlotOther.PERFORMING_ORAL
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.PERFORMING_ORAL_TWO
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.PERFORMING_ORAL_THREE
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.PERFORMING_ORAL_FOUR)
				&& (Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_TWO
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_THREE
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			// The character riding cock can use their body weight to force a creampie:
			if((Sex.getSexPositionSlot(cumTarget)==SexSlotOther.SITTING_IN_LAP
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.SITTING_IN_LAP_TWO
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.SITTING_IN_LAP_THREE
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.SITTING_IN_LAP_FOUR)
				&& (Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_TWO
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_THREE
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Skin.class, genericGroinForceCreampieAreas));
			}
			// The character sitting getting fucked can use their legs, tail, or tentacles to force a creampie:
			if((Sex.getSexPositionSlot(cumTarget)==SexSlotOther.SITTING
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.SITTING_TWO
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.SITTING_THREE
					|| Sex.getSexPositionSlot(cumTarget)==SexSlotOther.SITTING_FOUR)
				&& (Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_BETWEEN_LEGS
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_BETWEEN_LEGS_TWO
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_BETWEEN_LEGS_THREE
					|| Sex.getSexPositionSlot(cumProvider)==SexSlotOther.SITTING_BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			
			return null;
		}
		@Override
		public int getMaximumSlots() {
			return 4;
		}
	};
}
