package com.lilithsthrone.game.sex.positions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBreedingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.dominion.MasturbationPanties;
import com.lilithsthrone.game.sex.sexActions.universal.ChairSex;
import com.lilithsthrone.game.sex.sexActions.universal.Cowgirl;
import com.lilithsthrone.game.sex.sexActions.universal.DoggyStyle;
import com.lilithsthrone.game.sex.sexActions.universal.FaceSitting;
import com.lilithsthrone.game.sex.sexActions.universal.GloryHole;
import com.lilithsthrone.game.sex.sexActions.universal.KneelingOral;
import com.lilithsthrone.game.sex.sexActions.universal.Masturbation;
import com.lilithsthrone.game.sex.sexActions.universal.MatingPress;
import com.lilithsthrone.game.sex.sexActions.universal.MilkingStall;
import com.lilithsthrone.game.sex.sexActions.universal.Missionary;
import com.lilithsthrone.game.sex.sexActions.universal.MissionaryDesk;
import com.lilithsthrone.game.sex.sexActions.universal.SixtyNine;
import com.lilithsthrone.game.sex.sexActions.universal.StocksSex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * AbstractSexPositions for taurs, including taur-biped interactions.
 * 
 * @since 0.3.1
 * @version 0.3.4
 * @author Innoxia
 */
public class SexPosition {
	
	public static final AbstractSexPosition MASTURBATION = new AbstractSexPosition("",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(
					MasturbationPanties.class,
					Masturbation.class)) {
		@Override
		public Set<SexSlot> getAllAvailableSexPositions() {
			return Util.newHashSetOfValues(SexSlotMasturbation.KNEELING, SexSlotMasturbation.KNEELING_PANTIES, SexSlotMasturbation.SITTING, SexSlotMasturbation.STANDING);
		}
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(characterToTakeSlot.isTaur() && slot==SexSlotMasturbation.SITTING) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Due to the proportions of [npc.her] animalistic lower body, [npc.nameIsFull] unable to sit down and masturbate at the same time."));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING), "[npc.NameIs] kneeling on the floor, ready to masturbate.");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.STANDING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.STANDING), "[npc.NameIs] standing upright, ready to masturbate.");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.SITTING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.SITTING), "[npc.NameIs] sitting down, ready to masturbate.");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES), "[npc.NameIs] kneeling on the floor, ready to masturbate with the aid of Lilaya's panties.");
			}
			
			return UtilText.parse("You are ready to masturbate.");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> masturbationSlots = Util.newArrayListOfValues(SexSlotMasturbation.KNEELING, SexSlotMasturbation.STANDING, SexSlotMasturbation.SITTING);
			
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES)!=null) {
				interactions.add(StandardSexActionInteractions.masturbation.getSexActionInteractions(SexSlotMasturbation.KNEELING_PANTIES, SexSlotMasturbation.KNEELING_PANTIES));
			} else {
				for(SexSlot slot : masturbationSlots) {
					interactions.add(StandardSexActionInteractions.masturbation.getSexActionInteractions(slot, slot));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
	};
	
	
	public static final AbstractSexPosition STANDING = new AbstractSexPosition("Standing",
			8,
			true,
			SexActionPresets.positioningActionsNew, Util.newArrayListOfValues(KneelingOral.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.STANDING_SUBMISSIVE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.STANDING_SUBMISSIVE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_THREE, SexSlotStanding.STANDING_SUBMISSIVE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_FOUR, SexSlotStanding.STANDING_SUBMISSIVE_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
												+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			// This position requires at least one character to be standing.
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotStanding.STANDING_DOMINANT
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_TWO
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_THREE
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_FOUR) {
					suitablePosition = true;
					break;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "At least one character needs to be in a standing slot for this position to work.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			List<String> subNames = new ArrayList<>();
			List<String> subStandingNames = new ArrayList<>();
			List<String> subStandingBehindNames = new ArrayList<>();
			List<String> subKneelingNames = new ArrayList<>();
			List<String> subKneelingBehindNames = new ArrayList<>();
			List<String> subSizeDiffKneelingNames = new ArrayList<>();
			List<String> subSizeDiffBehindNames = new ArrayList<>();
			List<String> domNames = new ArrayList<>();
			
//			List<String> sizeDifferenceAdditions = new ArrayList<>();
			Map<GameCharacter, SexSlot> doms = new HashMap<>();
			Map<GameCharacter, SexSlot> domTaurs = new HashMap<>();
			Map<GameCharacter, SexSlot> subs = new HashMap<>();
			List<GameCharacter> subsStanding = new ArrayList<>();
			List<GameCharacter> subsStandingBehind = new ArrayList<>();
			
			boolean playerInDoms = false;
			boolean playerInSubs = false;
			
			GameCharacter mainDom = null;
			GameCharacter mainSub = null;
			GameCharacter mainStandingSub = null;
			GameCharacter mainStandingBehindSub = null;
			GameCharacter mainKneelingSub = null;
			GameCharacter mainBehindSub = null;
			GameCharacter mainKneelingSizeDiffSub = null;
			GameCharacter mainBehindSizeDiffSub = null;
			
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotStanding.STANDING_DOMINANT
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_TWO
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_THREE
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_FOUR) {
						if(mainDom==null) {
							mainDom=e.getKey();
						}
						if(!e.getKey().isTaur()) {
							doms.put(e.getKey(), e.getValue());
						} else {
							domTaurs.put(e.getKey(), e.getValue());
						}
						if(e.getKey().isPlayer()) {
							playerInDoms = true;
						}
						
					} else {
						if(e.getKey().isPlayer()) {
							playerInSubs = true;
						}
						subs.put(e.getKey(), e.getValue());
					}
				}
			}
			
			for(Entry<GameCharacter, SexSlot> sub : subs.entrySet()) {
				if(mainSub==null) {
					mainSub = sub.getKey();
				}
				if(sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_TWO
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_THREE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_FOUR) {
					if(sub.getKey().isPlayer()) {
						subStandingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
					} else {
						subStandingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
					}
					if(mainStandingSub==null) {
						mainStandingSub=sub.getKey();
					}
					subsStanding.add(sub.getKey());
					
				} else if(sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR) {
					if(sub.getKey().isPlayer()) {
						subStandingBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
					} else {
						subStandingBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
					}
					if(mainStandingBehindSub==null) {
						mainStandingBehindSub=sub.getKey();
					}
					subsStandingBehind.add(sub.getKey());
					
				} else if(sub.getValue()==SexSlotStanding.PERFORMING_ORAL
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_TWO
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_THREE
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_FOUR) {
					if(sub.getKey().isSizeDifferenceShorterThan(mainDom)) {
						if(sub.getKey().isPlayer()) {
							subSizeDiffKneelingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subSizeDiffKneelingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainKneelingSizeDiffSub==null) {
							mainKneelingSizeDiffSub=sub.getKey();
						}
					} else {
						if(sub.getKey().isPlayer()) {
							subKneelingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subKneelingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainKneelingSub==null) {
							mainKneelingSub=sub.getKey();
						}
					}
					
				} else {
					if(sub.getKey().isSizeDifferenceShorterThan(mainDom)) {
						if(sub.getKey().isPlayer()) {
							subSizeDiffBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subSizeDiffBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainBehindSizeDiffSub==null) {
							mainBehindSizeDiffSub=sub.getKey();
						}
					} else {
						if(sub.getKey().isPlayer()) {
							subKneelingBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subKneelingBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainBehindSub==null) {
							mainBehindSub=sub.getKey();
						}
					}
				}
				subNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
			}
			
			for(GameCharacter c : doms.keySet()) {
				if(c.isPlayer()) {
					domNames.add(0, UtilText.parse(c, "[npc.name]"));
				} else {
					domNames.add(UtilText.parse(c, "[npc.name]"));
				}
			}
			for(GameCharacter c : domTaurs.keySet()) {
				if(c.isPlayer()) {
					domNames.add(0, UtilText.parse(c, "[npc.name]"));
				} else {
					domNames.add(UtilText.parse(c, "[npc.name]"));
				}
			}
			
			int totalDoms = doms.size()+domTaurs.size();
			if(totalDoms>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(domNames, false))+" are standing side-by-side before ");
			} else {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(domNames, false))
						+UtilText.parse(mainDom, " [npc.is]")+" standing before ");
			}

			sb.append(Util.stringsToStringList(subNames, false)+", ready to have some fun with "
					+(subNames.size()>1
							?(playerInSubs?(playerInSubs?"the "+Util.intToString(subs.size())+" of you":"them"):"them")
							:UtilText.parse(subs.keySet().iterator().next(), " [npc.herHim]"))+". ");
			
			// Standing:
			if(subStandingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingNames, false))
						+" are similarly standing beside one another in front of "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?"the "+Util.intToString(totalDoms)+" of you":"them"))+". ");
				
			} else if(subStandingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingNames, false))
						+UtilText.parse(mainStandingSub, " [npc.is]")+" standing in front of "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?"the "+Util.intToString(totalDoms)+" of you":"them"))+". ");
			}

			// Kneeling:
			if(subKneelingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingNames, false))
						+" are kneeling down beside one another");
				if(domTaurs.isEmpty()) {
					sb.append(" before "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" you":"the "+Util.intToString(totalDoms)+" of them"))+", ready to perform oral." );
				} else if(doms.isEmpty()) {
					sb.append(", and are ready to shuffle forwards beneath "
								+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] animalistic body"):(playerInDoms?"one of your animalistic bodies":"one of their animalistic bodies"))+" in order to perform oral. ");
				} else {
					sb.append(", and are ready to shuffle forwards and start performing oral. ");
				}
				
			} else if(subKneelingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingNames, false))
						+UtilText.parse(mainKneelingSub, " [npc.is]")+" kneeling down");
				if(domTaurs.isEmpty()) {
					sb.append(" before "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" you":"the "+Util.intToString(totalDoms)+" of them"))+", ready to perform oral. ");
				} else if(doms.isEmpty()) {
					sb.append(" and "+UtilText.parse(mainKneelingSub, " [npc.is]")+" ready to shuffle forwards beneath "
								+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] animalistic body"):(playerInDoms?"one of your animalistic bodies":"one of their animalistic bodies"))+" in order to perform oral. ");
				} else {
					sb.append(" and "+UtilText.parse(mainKneelingSub, " [npc.is]")+" ready to shuffle forwards and start performing oral. ");
				}
			}
			// Kneeling size difference:
			if(subSizeDiffKneelingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffKneelingNames, false))
						+" are ready to perform oral, but due to "+(playerInSubs?"your":"their")+" short stature, "+(playerInSubs?"you":"they")+" do not need to kneel, and are instead standing before "
						+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" you":"the "+Util.intToString(totalDoms)+" of them"))+".");
				
			} else if(subSizeDiffKneelingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffKneelingNames, false))
						+UtilText.parse(mainKneelingSizeDiffSub, " [npc.is] ready to perform oral, but due to [npc.her] short stature, [npc.she] [npc.does] not need to kneel, and [npc.is] instead standing before ")
						+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" you":"the "+Util.intToString(totalDoms)+" of them"))+".");
			}

			// Standing behind:
			if(subStandingBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingBehindNames, false))
						+" are standing behind, next to one another.");
				
			} else if(subStandingBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingBehindNames, false))
						+UtilText.parse(mainStandingBehindSub, " [npc.is]")+" standing behind "+(totalDoms==1?UtilText.parse(mainDom, " [npc.name]"):(playerInDoms?" you":" them"))+".");
			}
			
			// Kneeling behind:
			if(subKneelingBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingBehindNames, false))
						+" have knelt down beside one another");
				if(domTaurs.isEmpty()) {
					sb.append(" behind "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" you":" them"))+", ready to perform anilingus.");
				} else if(doms.isEmpty()) {
					sb.append(" behind "+
							(totalDoms==1
								?UtilText.parse(mainDom, " [npc.herHim], and are ready to perform oral on [npc.her] animalistic rear-half.")
								:(playerInDoms
									?" you, and are ready to perform oral on one of your animalistic rear-halves."
									:" them, and are ready to perform oral on one of their animalistic rear-halves.")));
				} else {
					sb.append(", and are ready to perform oral on "+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] rear-end."):(playerInDoms?" your rear-ends.":" their rear-ends.")));
				}
				
			} else if(subKneelingBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingBehindNames, false))
						+UtilText.parse(mainBehindSub, " [npc.is]")+" kneeling down behind "+(totalDoms==1?UtilText.parse(mainDom, " [npc.herHim]"):(playerInDoms?" you":" them")));
				if(domTaurs.isEmpty()) {
					sb.append(", ready to perform anilingus.");
				} else if(doms.isEmpty()) {
					sb.append((totalDoms==1
								?UtilText.parse(mainDom, ", and "+UtilText.parse(mainBehindSub, " [npc.is]")+" ready to perform oral on [npc.her] animalistic rear-half.")
								:(playerInDoms
									?", and "+UtilText.parse(mainBehindSub, " [npc.is]")+" ready to perform oral on one of your animalistic rear-halves."
									:", and "+UtilText.parse(mainBehindSub, " [npc.is]")+" ready to perform oral on one of their animalistic rear-halves.")));
				} else {
					sb.append(", ready to perform oral on "+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] rear-end."):(playerInDoms?" your rear-ends.":" their rear-ends.")));
				}
			}
			// Kneeling behind size difference:
			if(subSizeDiffBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffBehindNames, false))
						+" are positioned behind "+(totalDoms==1?UtilText.parse(mainDom, "[npc.name]"):(playerInDoms?"you":"the "+Util.intToString(totalDoms)+" of them"))
						+ ", ready to perform oral on "+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] rear-end"):(playerInDoms?" your rear-ends":" their rear-ends"))
						+", but due to "+(playerInSubs?"your":"their")+" short stature, "+(playerInSubs?"you":"they")+" do not need to kneel, and are instead standing upright.");
				
			} else if(subSizeDiffBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffBehindNames, false))
						+UtilText.parse(mainBehindSizeDiffSub, " [npc.is] positioned behind ")+(totalDoms==1?UtilText.parse(mainDom, "[npc.name]"):(playerInDoms?"you":"the "+Util.intToString(totalDoms)+" of them"))
						+ ", ready to perform oral on "+(totalDoms==1?UtilText.parse(mainDom, " [npc.her] rear-end"):(playerInDoms?" your rear-ends":" their rear-ends"))
						+UtilText.parse(mainBehindSizeDiffSub, ", but due to [npc.her] short stature, [npc.she] [npc.does] not need to kneel, and [npc.is] instead standing upright."));
			}
			

			// Size difference:
//			List<GameCharacter> allDoms = new ArrayList<>(doms.keySet());
//			allDoms.addAll(domTaurs.keySet());
//			playerInDoms = false;
//			playerInSubs = false;
//			for(GameCharacter sub : subsStanding) {
//				mainSub = sub;
//				List<String> names = new ArrayList<>();
//				for(GameCharacter dom : allDoms) {
//					if(sub.isSizeDifferenceShorterThan(dom)) {
//						names.add(UtilText.parse(dom, "[npc.name]"));
//						if(dom.isPlayer()) {
//							playerInDoms = true;
//						}
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(sub,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "+(names.size()>1?(playerInDoms?"you":"them"):UtilText.parse(mainDom, "[npc.herHim]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(GameCharacter sub : subsStandingBehind) {
//				mainSub = sub;
//				List<String> names = new ArrayList<>();
//				for(GameCharacter dom : allDoms) {
//					if(sub.isSizeDifferenceShorterThan(dom)) {
//						names.add(UtilText.parse(dom, "[npc.name]"));
//						if(dom.isPlayer()) {
//							playerInDoms = true;
//						}
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(sub,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "
//									+(names.size()>1?(playerInDoms?"your rear end":"their rear ends"):UtilText.parse(mainDom, "[npc.namePos] [npc.ass+]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(GameCharacter dom : allDoms) {
//				List<String> names = new ArrayList<>();
//				for(GameCharacter sub : subsStanding) {
//					if(dom.isSizeDifferenceShorterThan(sub)) {
//						names.add(UtilText.parse(sub, "[npc.name]"));
//					}
//					if(sub.isPlayer()) {
//						playerInSubs = true;
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(dom,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "+(names.size()>1?(playerInSubs?"you":"them"):UtilText.parse(mainSub, "[npc.herHim]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(String s : sizeDifferenceAdditions) {
//				sb.append("<br/>[style.italicsOrange("+s+")]");
//			}
			
			// Size difference:
			Map<GameCharacter, List<String>> shorterMap = new HashMap<>(); // Mapping short character to list of larger characters
			for(GameCharacter c1 : occupiedSlots.keySet()) {
				for(GameCharacter c2 : occupiedSlots.keySet()) {
					if(c1!=c2 && c1.isSizeDifferenceShorterThan(c2)) {
						shorterMap.putIfAbsent(c1, new ArrayList<>());
						shorterMap.get(c1).add(UtilText.parse(c2, "<span style='color:"+c2.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
					}
				}
			}
			if(!shorterMap.isEmpty()) {
				sb.append("<br/>[style.italicsOrange(Some characters are significantly shorter than others, and as such will be in a position to perform oral on their larger partners even while standing:)]");
				for(Entry<GameCharacter, List<String>> entry : shorterMap.entrySet()) {
					sb.append(UtilText.parse(entry.getKey(), "<br/><span style='color:"+entry.getKey().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span> [npc.is] shorter than "));
					sb.append("");
					sb.append(Util.stringsToStringList(entry.getValue(), false));
					sb.append(".");
				}
			}
						
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			
			List<SexSlot> domStanding = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_DOMINANT_TWO, SexSlotStanding.STANDING_DOMINANT_THREE, SexSlotStanding.STANDING_DOMINANT_FOUR);
			List<SexSlot> subStanding = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO, SexSlotStanding.STANDING_SUBMISSIVE_THREE, SexSlotStanding.STANDING_SUBMISSIVE_FOUR);
			List<SexSlot> subStandingBehind = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(
					SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.PERFORMING_ORAL_THREE, SexSlotStanding.PERFORMING_ORAL_FOUR);
			List<SexSlot> performingOralBehind = Util.newArrayListOfValues(
					SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE, SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR);
			
			// Adding faceToFace for every dominant to every submissive:
			for(SexSlot slotD : domStanding) {
				for(SexSlot slotD2 : subStanding) {
					if(slotD!=slotD2) {
						interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotD, slotD2));
					}
				}
				for(SexSlot slotS : subStanding) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(slotD, slotS));
					for(SexSlot slotS2 : subStanding) {
						if(slotS!=slotS2) {
							interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotS, slotS2));
						}
					}
				}
				for(SexSlot slotS : subStandingBehind) {
					interactions.add(StandardSexActionInteractions.standingBehind.getSexActionInteractions(slotS, slotD));
					for(SexSlot slotS2 : subStandingBehind) {
						if(slotS!=slotS2) {
							interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotS, slotS2));
						}
					}
				}
			}
			
			// Adding performingOral for to every dominant:
			for(SexSlot performingO : performingOral) {
				for(SexSlot slotD : domStanding) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(performingO, slotD));
				}
			}

			// Adding performingOralBehind for to every dominant:
			for(SexSlot performingOB : performingOralBehind) {
				for(SexSlot slotD : domStanding) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(performingOB, slotD));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
	};
	

	public static final AbstractSexPosition AGAINST_WALL = new AbstractSexPosition("Against [pc.wall]",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			new ArrayList<>()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.BACK_TO_WALL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_TWO, SexSlotAgainstWall.BACK_TO_WALL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_THREE, SexSlotAgainstWall.BACK_TO_WALL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_FOUR, SexSlotAgainstWall.BACK_TO_WALL_FOUR));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_TWO, SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_THREE, SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_FOUR, SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
												+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePositionStandingOrOral=false;
			boolean suitablePositionWall=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotAgainstWall.FACE_TO_WALL
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_FOUR
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_FOUR) {
					suitablePositionWall = true;
				}
				if(e.getValue()==SexSlotAgainstWall.STANDING_WALL
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_FOUR
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR) {
					suitablePositionStandingOrOral = true;
				}
			}
			if(!suitablePositionWall || !suitablePositionStandingOrOral) {
				return new Value<Boolean, String>(false, "At least one character needs to be against [pc.a_wall], and another either standing or performing oral for this position to work.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			List<String> standingNames = new ArrayList<>();
			List<String> facingWallNames = new ArrayList<>();
			List<String> backToWallNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			
			GameCharacter mainStanding = null;
			GameCharacter mainFacingWall = null;
			GameCharacter mainBackToWall = null;
			GameCharacter mainPerformingOral = null;
			
			boolean playerFacingWall = false;
			boolean playerBackToWall = false;
			boolean playerPerformingOral = false;
			
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotAgainstWall.STANDING_WALL
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotAgainstWall.FACE_TO_WALL
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_FOUR) {
						if(mainFacingWall==null) {
							mainFacingWall=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							facingWallNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							facingWallNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerFacingWall = true;
						}
						
					} else if(e.getValue()==SexSlotAgainstWall.BACK_TO_WALL
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_FOUR) {
						if(mainBackToWall==null) {
							mainBackToWall=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							backToWallNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							backToWallNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerBackToWall = true;
						}
						
					} else {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerPerformingOral = true;
						}
					}
				}
			}

			int facingWallCount = facingWallNames.size();
			if(facingWallCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(facingWallNames, false))+" are standing side-by-side facing a nearby [pc.wall]");
				
			} else if(facingWallCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(facingWallNames, false))+UtilText.parse(mainFacingWall, " [npc.is] standing facing a nearby [pc.wall]"));
			}
			
			if(backToWallNames.size()>=2) {
				if(facingWallCount>0) {
					sb.append(", while "+Util.stringsToStringList(backToWallNames, false)+" are standing beside "
								+(facingWallCount==1?UtilText.parse(mainFacingWall,"[npc.herHim]"):(playerBackToWall?"you":"them"))+", with "+(playerBackToWall?"your":"their")+" backs to the [pc.wall] instead. ");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(backToWallNames, false))+" are standing side-by-side, with "+(playerBackToWall?"your":"their")+" rears pressed back against the [pc.wall] behind them. ");
				}
				
			} else if(backToWallNames.size()==1) {
				if(facingWallCount>0) {
					sb.append(", while "+Util.stringsToStringList(backToWallNames, false)+UtilText.parse(mainBackToWall, " [npc.is] standing beside ")
							+(facingWallCount==1?UtilText.parse(mainFacingWall,"[npc.herHim]"):(playerBackToWall?"you":"them"))+", with "+UtilText.parse(mainBackToWall,"[npc.her]")+" back to the [pc.wall] instead. ");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(backToWallNames, false))+UtilText.parse(mainBackToWall, " [npc.is] standing with [npc.her] rear pressed back against a [pc.wall] behind [npc.herHim]. "));
				}
			} else {
				if(facingWallCount>1) {
					sb.append(", with each of "+(playerFacingWall?"you":"them")+" throwing the occasional glance back over "+(playerFacingWall?"your":"their")+" shoulders. ");
				} else {
					sb.append(UtilText.parse(mainFacingWall, ", and occasionally [npc.verb(throw)] a rearward glance back over [npc.her] shoulder. "));
				}
			}

			int totalAgainstWall = facingWallCount + backToWallNames.size();
			int standingCount = standingNames.size();
			GameCharacter mainWall = mainBackToWall==null?mainFacingWall:mainBackToWall;
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+" are positioned so as to be standing right up against "
							+(totalAgainstWall>1?(playerFacingWall || playerBackToWall?"you":"them"):UtilText.parse(mainWall, "[npc.name]")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding," [npc.is] positioned so as to be standing right up against ")+(totalAgainstWall>1?(playerFacingWall || playerBackToWall?"you":"them"):UtilText.parse(mainWall, "[npc.name]")));
			}
			
			if(performingOralNames.size()>=2) {
				if(standingCount>0) {
					sb.append(", while "+Util.stringsToStringList(performingOralNames, false)+" are down on "+(playerPerformingOral?"your":"their")+" knees, ready to perform oral on "
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"you":"them"))+".");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+" are positioned close behind and are down on "+(playerPerformingOral?"your":"their")+" knees, ready to perform oral on "
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"you":"them"))+".");
				}
				
			} else if(performingOralNames.size()==1) {
				if(standingCount>0) {
					sb.append(", while "+Util.stringsToStringList(performingOralNames, false)+UtilText.parse(mainPerformingOral, " [npc.is] down on [npc.her] knees, ready to perform oral on ")
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"you":"them"))+".");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral, " [npc.is] positioned close behind and [npc.is] down on [npc.her] knees, ready to perform oral on ")
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"you":"them"))+".");
				}
				
			} else {
				if(totalAgainstWall>1) {
					sb.append(", ready to have some fun with the "+Util.intToString(totalAgainstWall)+" of "+(playerFacingWall?"you":"them")+".");
				} else {
					sb.append(", ready to have some fun with "+UtilText.parse(mainWall,"[npc.herHim]")+".");
				}
			}
			

			// Size difference:
			Map<GameCharacter, List<String>> shorterMap = new HashMap<>(); // Mapping short character to list of larger characters
			for(GameCharacter c1 : occupiedSlots.keySet()) {
				for(GameCharacter c2 : occupiedSlots.keySet()) {
					if(c1!=c2 && c1.isSizeDifferenceShorterThan(c2)) {
						shorterMap.putIfAbsent(c1, new ArrayList<>());
						shorterMap.get(c1).add(UtilText.parse(c2, "<span style='color:"+c2.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
					}
				}
			}
			if(!shorterMap.isEmpty()) {
				sb.append("<br/>[style.italicsOrange(Some characters are significantly shorter than others, and as such will be in a position to perform oral on their larger partners even while standing:)]");
				for(Entry<GameCharacter, List<String>> entry : shorterMap.entrySet()) {
					sb.append(UtilText.parse(entry.getKey(), "<br/><span style='color:"+entry.getKey().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span> [npc.is] shorter than "));
					sb.append("");
					sb.append(Util.stringsToStringList(entry.getValue(), false));
					sb.append(".");
				}
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> facingWall = Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.FACE_TO_WALL_TWO, SexSlotAgainstWall.FACE_TO_WALL_THREE, SexSlotAgainstWall.FACE_TO_WALL_FOUR);
			List<SexSlot> backToWall = Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL, SexSlotAgainstWall.BACK_TO_WALL_TWO, SexSlotAgainstWall.BACK_TO_WALL_THREE, SexSlotAgainstWall.BACK_TO_WALL_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL, SexSlotAgainstWall.STANDING_WALL_TWO, SexSlotAgainstWall.STANDING_WALL_THREE, SexSlotAgainstWall.STANDING_WALL_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotAgainstWall.PERFORMING_ORAL_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO, SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE, SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR);
			
			// Assign all actions to all recipients.
			// It might make more sense to limit actions to just the target "in front" of the standing or oral-performing character, but then it would require a bit of micro-management for the player to move into the correct slot.
			// I decided that giving the performing characters a wide range of targets was better than making them micro-manage their slots.
			
			for(SexSlot slotStanding : standing) {
				for(SexSlot slotFacing : facingWall) {
					interactions.add(StandardSexActionInteractions.standingBehindCharacterFacingWall.getSexActionInteractions(slotStanding, slotFacing));
				}
				for(SexSlot slotBack : backToWall) {
					interactions.add(StandardSexActionInteractions.standingBehindCharacterBackToWall.getSexActionInteractions(slotStanding, slotBack));
				}
			}
			for(SexSlot slotOral : performingOral) {
				for(SexSlot slotFacing : facingWall) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(slotOral, slotFacing));
				}
				for(SexSlot slotBack : backToWall) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(slotOral, slotBack));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_FOUR
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			// The character pressing another against the wall can use body weight to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	

	public static final AbstractSexPosition OVER_DESK = new AbstractSexPosition("Over [pc.desk]",
			8,
			true,
			SexActionPresets.positioningActionsNew, Util.newArrayListOfValues(MissionaryDesk.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			Map<SexSlot, GameCharacter> reversedPositioningSlotsMap = new HashMap<>();
			for(Entry<GameCharacter, SexSlot> entry : positioningSlots.entrySet()) {
				reversedPositioningSlotsMap.put(entry.getValue(), entry.getKey());
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS, SexSlotDesk.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_TWO, SexSlotDesk.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_THREE, SexSlotDesk.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_FOUR, SexSlotDesk.PERFORMING_ORAL_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.OVER_DESK_ON_BACK));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_TWO, SexSlotDesk.OVER_DESK_ON_BACK_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_THREE, SexSlotDesk.OVER_DESK_ON_BACK_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_FOUR, SexSlotDesk.OVER_DESK_ON_BACK_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
												+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}

			if((slot==SexSlotDesk.HUMPING && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK))
					|| (slot==SexSlotDesk.HUMPING_TWO && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_TWO) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_TWO))
					|| (slot==SexSlotDesk.HUMPING_THREE && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_THREE) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_THREE))
					|| (slot==SexSlotDesk.HUMPING_FOUR && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_FOUR))) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "There is no free character lying over a [pc.desk] to hump!"));
			}

			// Cannot hump when the person standing behind the intended target is a taur:
			if((slot==SexSlotDesk.HUMPING && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS), "[npc.Name] cannot hump anyone while [npc2.namePos] [npc2.legRace]'s body is in the way!"));
			} else if((slot==SexSlotDesk.HUMPING_TWO && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_TWO) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_TWO).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_TWO), "[npc.Name] cannot hump anyone while [npc2.namePos] [npc2.legRace]'s body is in the way!"));
			} else if((slot==SexSlotDesk.HUMPING_THREE && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_THREE) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_THREE).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_THREE), "[npc.Name] cannot hump anyone while [npc2.namePos] [npc2.legRace]'s body is in the way!"));
			} else if((slot==SexSlotDesk.HUMPING_FOUR && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_FOUR) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_FOUR).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_FOUR), "[npc.Name] cannot hump anyone while [npc2.namePos] [npc2.legRace]'s body is in the way!"));
			}
			// And vice-versa:
			if((slot==SexSlotDesk.BETWEEN_LEGS && positioningSlots.containsValue(SexSlotDesk.HUMPING) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING), "[npc.NamePos] [npc2.legRace]'s body will not fit while [npc2.name] is in the way!"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_TWO && positioningSlots.containsValue(SexSlotDesk.HUMPING_TWO) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_TWO), "[npc.NamePos] [npc2.legRace]'s body will not fit while [npc2.name] is in the way!"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_THREE && positioningSlots.containsValue(SexSlotDesk.HUMPING_THREE) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_THREE), "[npc.NamePos] [npc2.legRace]'s body will not fit while [npc2.name] is in the way!"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_FOUR && positioningSlots.containsValue(SexSlotDesk.HUMPING_FOUR) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_FOUR), "[npc.NamePos] [npc2.legRace]'s body will not fit while [npc2.name] is in the way!"));
			}
			
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotDesk.OVER_DESK_ON_BACK
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Due to the proportions of [npc.her] animalistic lower body, [npc.nameIsFull] unable to lie down on [npc.her] back over the [pc.desk]."));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePositionStandingOrOral=false;
			boolean suitablePositionDesk=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_FOUR
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) {
					suitablePositionDesk = true;
				}
				if(e.getValue()==SexSlotDesk.BETWEEN_LEGS
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_TWO
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_THREE
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_FOUR
						|| e.getValue()==SexSlotDesk.HUMPING
						|| e.getValue()==SexSlotDesk.HUMPING_TWO
						|| e.getValue()==SexSlotDesk.HUMPING_THREE
						|| e.getValue()==SexSlotDesk.HUMPING_FOUR
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_TWO
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_THREE
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_FOUR
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_TWO
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_THREE
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_FOUR) {
					suitablePositionStandingOrOral = true;
				}
			}
			if(!suitablePositionDesk || !suitablePositionStandingOrOral) {
				return new Value<Boolean, String>(false, "At least one character needs to be on top of the [pc.desk], and another either standing, humping, receiving oral, or performing oral for this position to work.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			List<String> deskBackNames = new ArrayList<>();
			List<String> deskFrontNames = new ArrayList<>();
			List<String> deskFrontTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainDeskFront = null;
			GameCharacter mainDeskFrontTaur = null;
			GameCharacter mainDeskBack = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerDeskFront = false;
			boolean playerDeskBack = false;

			// TODO Characters under 80cm tall need to be described as having climbed up onto the desk.
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) {
						if(mainDeskFront==null && !e.getKey().isTaur()) {
							mainDeskFront=e.getKey();
						}
						if(mainDeskFrontTaur==null && e.getKey().isTaur()) {
							mainDeskFrontTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerDeskFront = true;
							if(!e.getKey().isTaur()) {
								deskFrontNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								deskFrontTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								deskFrontNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								deskFrontTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_TWO
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_THREE
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_FOUR) {
						if(mainDeskBack==null) {
							mainDeskBack=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerDeskBack = true;
							deskBackNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							deskBackNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotDesk.BETWEEN_LEGS
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_TWO
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_THREE
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_FOUR) {
						if(mainStanding==null && !e.getKey().isTaur()) {
							mainStanding=e.getKey();
						}
						if(mainStandingTaur==null && e.getKey().isTaur()) {
							mainStandingTaur=e.getKey();
						}
						
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotDesk.HUMPING
							|| e.getValue()==SexSlotDesk.HUMPING_TWO
							|| e.getValue()==SexSlotDesk.HUMPING_THREE
							|| e.getValue()==SexSlotDesk.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotDesk.PERFORMING_ORAL
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			
			int deskBackCount = deskBackNames.size();
			if(deskBackCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskBackNames, false))+" are lying back over the [pc.desk]");
				
			} else if(deskBackCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskBackNames, false))+UtilText.parse(mainDeskBack, " [npc.is] lying back over the [pc.desk]"));
			}
			
			if(deskFrontNames.size()>=2) {
				if(deskBackCount>0) {
					sb.append(", while "+Util.stringsToStringList(deskFrontNames, false)+" are lying down, face-first, beside "
								+(deskBackCount==1?UtilText.parse(mainDeskBack,"[npc.herHim]"):(playerDeskBack?"you":"them"))+". ");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontNames, false))+" are lying down, face-first, over the [pc.desk]. "); 
				}
				
			} else if(deskFrontNames.size()==1) {
				if(deskBackCount>0) {
					sb.append(", while "+Util.stringsToStringList(deskFrontNames, false)+UtilText.parse(mainDeskFront, " [npc.is] lying down, face-first, over the [pc.desk]. "));
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontNames, false))+UtilText.parse(mainDeskFront, " [npc.is] lying down, face-first, over the [pc.desk]. "));
				}
				
			} else if(deskBackCount>0) {
				if(deskBackCount>1) {
					sb.append(", side-by-side with one another. ");
				} else {
					sb.append(UtilText.parse(mainDeskBack, ", ready to be fucked. "));
				}
			}
			
			// Desk taurs:
			if(deskFrontTaurNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))+" have used their front legs to step up on top of the [pc.desk], before lying down and resting their animalistic lower bodies over it. ");
				
			} else if(deskFrontTaurNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))+UtilText.parse(mainDeskFrontTaur,
						" [npc.has] used [npc.her] front legs to step up on top of the [pc.desk], before lying down and resting [npc.her] animalistic lower body over it. "));
			}
			

			// Standing between legs:
			
			int standingCount = standingNames.size();
			int totalDeskCount = deskBackCount + deskFrontNames.size() + deskFrontTaurNames.size();
			GameCharacter mainDesk = mainDeskBack==null
					?mainDeskFront==null
						?mainDeskFrontTaur
						:mainDeskFront
					:mainDeskBack;
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+" have moved forwards so as to be standing right up between "
							+(totalDeskCount>1?(playerDeskBack||playerDeskFront?"your legs":"their legs"):UtilText.parse(mainDesk, "[npc.namePos] [npc.legs]")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding," [npc.has] moved forwards so as to be standing right up between ")
							+(totalDeskCount>1?(playerDeskBack||playerDeskFront?"your legs":"their legs"):UtilText.parse(mainDesk, "[npc.namePos] [npc.legs]")));
			}
			if(standingCount>0) {
				if(totalDeskCount>1) {
					sb.append(", ready to have some fun with the "+Util.intToString(totalDeskCount)+" of "+(playerDeskBack||playerDeskFront?"you":"them")+". ");
				} else {
					sb.append(", ready to have some fun with "+UtilText.parse(mainDesk,"[npc.herHim]")+". ");
				}
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+" have stepped up onto the [pc.desk] with their front legs, and are ready to use their feral lower bodies");
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStandingTaur," [npc.has] stepped up onto the [pc.desk] with [npc.her] front legs, and [npc.is] ready to use [npc.her] feral lower body"));
			}
			if(standingCountTaurs>0) {
				if(totalDeskCount>1) {
					sb.append(" to mount the "+Util.intToString(totalDeskCount)+" of "+(playerDeskBack||playerDeskFront?"you":"them")+". ");
				} else {
					sb.append(" to mount "+UtilText.parse(mainDesk,"[npc.herHim]")+". ");
				}
			}
			

			// Humping:
			
			int humpingCount = humpingNames.size();
			if(humpingCount>0) {
				if(humpingCount>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+" have eagerly clambered up onto the edge of the [pc.desk], and are ready to start humping their partners.");
					
				} else if(humpingCount==1) {
					sb.append(UtilText.parse(mainHumping, "[npc.Name] [npc.has] eagerly clambered up onto the edge of the [pc.desk], and [npc.is] ready to start humping"));
					if(totalDeskCount>1) {
						sb.append(UtilText.parse(mainHumping," [npc.her] partner. "));
					} else {
						sb.append(UtilText.parse(mainDesk," [npc.name]")+". ");
					}
				}
			}
			
			
			// Performing oral:
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+" are positioned so as to be right up between "
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"your legs":"their legs")+", ready to perform oral on "+(playerDeskBack||playerDeskFront?"you. ":"them. ")
									:UtilText.parse(mainDesk, "[npc.namePos] [npc.legs], ready to perform oral on [npc.herHim]. ")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral," [npc.is] positioned so as to be right up between ")
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"your legs":"their legs")+", ready to perform oral on "+(playerDeskBack||playerDeskFront?"you. ":"them. ")
									:UtilText.parse(mainDesk, "[npc.namePos] [npc.legs], ready to perform oral on [npc.herHim]. ")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", meanwhile,":"")+" are positioned around the other side of the [pc.desk], next to "
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"your faces":"their faces")+", in order to receive oral from "+(playerDeskBack||playerDeskFront?"you.":"them.")
									:UtilText.parse(mainDesk, "[npc.namePos] [npc.face], in order to receive oral from [npc.herHim].")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", meanwhile,":"")+UtilText.parse(mainReceivingOral, " [npc.is] positioned around the other side of the [pc.desk], next to ")
						+(totalDeskCount>1
								?(playerDeskBack||playerDeskFront?"your faces":"their faces")+", in order to receive oral from "+(playerDeskBack||playerDeskFront?"you.":"them.")
								:UtilText.parse(mainDesk, "[npc.namePos] [npc.face], in order to receive oral from [npc.herHim].")));
			}
			


			// Size difference:
			// Taurs cannot bend down far enough to perform oral.
			boolean playerTaurPerformingOral = playerDeskFront&&Main.game.getPlayer().isTaur();
			List<String> sizeDifferenceAdditions = new ArrayList<>();
			
			if(!deskFrontTaurNames.isEmpty() && !receivingOralNames.isEmpty()) {
				if(deskFrontTaurNames.size()>=2) {
					sizeDifferenceAdditions.add(
							Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))
								+" are unable to perform oral on "+Util.stringsToStringChoice(receivingOralNames, false)+", due to the fact that "
									+(playerTaurPerformingOral
											?"you are unable to bend your humanoid upper-bodies down low enough"
											:"they are unable to bend their humanoid upper-bodies down low enough."));
				} else {
					sizeDifferenceAdditions.add(
							Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))
								+UtilText.parse(mainDeskFrontTaur,
										" [npc.is] unable to perform oral on "+Util.stringsToStringChoice(receivingOralNames, false)+", due to the fact that [npc.sheIsFull] unable to bend [npc.her] humanoid upper-body down low enough."));
				}
			}
			
			for(String s : sizeDifferenceAdditions) {
				sb.append("<br/>[style.italicsOrange("+s+")]");
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> onDeskBack = Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_BACK, SexSlotDesk.OVER_DESK_ON_BACK_TWO, SexSlotDesk.OVER_DESK_ON_BACK_THREE, SexSlotDesk.OVER_DESK_ON_BACK_FOUR);
			List<SexSlot> onDeskFront = Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.OVER_DESK_ON_FRONT_TWO, SexSlotDesk.OVER_DESK_ON_FRONT_THREE, SexSlotDesk.OVER_DESK_ON_FRONT_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS, SexSlotDesk.BETWEEN_LEGS_TWO, SexSlotDesk.BETWEEN_LEGS_THREE, SexSlotDesk.BETWEEN_LEGS_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotDesk.HUMPING, SexSlotDesk.HUMPING_TWO, SexSlotDesk.HUMPING_THREE, SexSlotDesk.HUMPING_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotDesk.PERFORMING_ORAL, SexSlotDesk.PERFORMING_ORAL_TWO, SexSlotDesk.PERFORMING_ORAL_THREE, SexSlotDesk.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotDesk.RECEIVING_ORAL, SexSlotDesk.RECEIVING_ORAL_TWO, SexSlotDesk.RECEIVING_ORAL_THREE, SexSlotDesk.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskBack.getSexActionInteractions(slotStanding, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskFront.getSexActionInteractions(slotStanding, slotFront));
				}
				for(SexSlot receivingSlot : receivingOral) {
					interactions.add(StandardSexActionInteractions.standingDeskToReceivingOral.getSexActionInteractions(slotStanding, receivingSlot));
				}
			}

			for(SexSlot slotHumping : humping) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskBack.getSexActionInteractions(slotHumping, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskFront.getSexActionInteractions(slotHumping, slotFront));
				}
				for(SexSlot receivingSlot : receivingOral) {
					interactions.add(StandardSexActionInteractions.standingDeskToReceivingOral.getSexActionInteractions(slotHumping, receivingSlot));
				}
			}
			
			for(SexSlot slotOral : performingOral) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(slotOral, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(slotOral, slotFront));
				}
			}
			
			for(SexSlot slotBack : onDeskBack) {
				for(SexSlot slotOral : receivingOral) {
					interactions.add(StandardSexActionInteractions.lyingOnDeskPerformingOral.getSexActionInteractions(slotBack, slotOral));
				}
			}
			for(SexSlot slotFront : onDeskFront) {
				for(SexSlot slotOral : receivingOral) {
					interactions.add(StandardSexActionInteractions.lyingOnDeskPerformingOral.getSexActionInteractions(slotFront, slotOral));
				}
			}

			// Those on the desk can kiss the ones next to them:
			for(int i=0;i<3;i++) {
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskBack.get(i)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskFront.get(i), onDeskFront.get(i)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskFront.get(i)));
				
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskBack.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskFront.get(i), onDeskFront.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskFront.get(i+1)));
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character lying back can use their legs, tails, or tentacles to force a facial creampie on characters performing oral on them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericFaceForceCreampieAreas),
						new Value<>(Tail.class, genericFaceForceCreampieAreas),
						new Value<>(Tentacle.class, genericFaceForceCreampieAreas));
			}
			// The character lying back can use their legs, tails, or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character lying on their front can use their tails, or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character between legs can use their weight to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			// The character lying back or on front can use their arms to force a facial creampie on characters receiving oral from them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
	};

	
	public static final AbstractSexPosition STOCKS = new AbstractSexPosition("Stocks",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(StocksSex.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(slot!= positioningSlots.get(characterToTakeSlot)) {
				if(positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "[npc.NameIsFull] locked into the stocks, and so cannot switch slot!"));
					
				} else if(slot==SexSlotStocks.LOCKED_IN_STOCKS
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Characters cannot be locked into or out of stocks during this sex scene!"));
				}
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS, SexSlotStocks.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_TWO, SexSlotStocks.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_THREE, SexSlotStocks.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_FOUR, SexSlotStocks.PERFORMING_ORAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
												+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean characterInStocks=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					characterInStocks = true;
				}
			}
			if(!characterInStocks) {
				return new Value<Boolean, String>(false, "At least one character needs to be locked into the stocks for this position to work.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> allStocksNames = new ArrayList<>();
			List<String> stocksNames = new ArrayList<>();
			List<String> stocksTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> beneathNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainStocks = null;
			GameCharacter mainStocksTaur = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainBeneath = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerStocks = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
						if(mainStocks==null && !e.getKey().isTaur()) {
							mainStocks=e.getKey();
						}
						if(mainStocksTaur==null && e.getKey().isTaur()) {
							mainStocksTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerStocks = true;
							allStocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allStocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotStocks.BEHIND_STOCKS
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotStocks.HUMPING
							|| e.getValue()==SexSlotStocks.HUMPING_TWO
							|| e.getValue()==SexSlotStocks.HUMPING_THREE
							|| e.getValue()==SexSlotStocks.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotStocks.BENEATH_STOCKS
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_FOUR) {
						if(mainBeneath==null) {
							mainBeneath=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							beneathNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							beneathNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotStocks.PERFORMING_ORAL
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			int stocksCount = stocksNames.size() + stocksTaurNames.size();
			GameCharacter soloStocks = mainStocks==null?mainStocksTaur:mainStocks;
			if(stocksNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))+" are locked into "+Util.intToString(stocksNames.size())
						+" sets of stocks, and are completely at the mercy of anyone who wants to use "+(playerStocks?"you":"them")+". "); 
				
			} else if(stocksTaurNames.isEmpty()) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))
						+UtilText.parse(soloStocks,
								" [npc.is] locked into a set of stocks, and [npc.is] completely at the mercy of anyone who wants to use [npc.herHim]. ")); 
			}
			
			if(stocksTaurNames.size()>=2) {
				sb.append(" Having feral lower bodies, "+Util.stringsToStringList(stocksTaurNames, false)
						+" have been locked into special sets of stocks, which force their front halves down into a kneeling position, while their animalistic rear halves are held fully upright,"
						+ " allowing anyone to step up and use their mouths or genitals as they'd like. "); 
				
			} else if(stocksTaurNames.size()==1) {
				sb.append(UtilText.parse(mainStocksTaur,
						" Having the feral lower body of [npc.a_legRace], "+Util.stringsToStringList(stocksTaurNames, false)
						+" [npc.has] been locked into a special set of stocks, which forces [npc.her] front half down into a kneeling position, while [npc.her] animalistic rear half is held fully upright,"
						+ " allowing anyone to step up and use [npc.her] mouth or genitals as they'd like. ")); 
			}
			
			// Using stocks target:
			
			int standingCount = standingNames.size();
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+" have moved forwards so as to be standing right up behind "
							+(stocksCount>1
									?(playerStocks?"you, ready to take advantage of your immobility. ":"them, ready to take advantage of their immobility. ")
									:UtilText.parse(soloStocks, "[npc.name], ready to take advantage of [npc.her] immobility. ")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding," [npc.has] moved forwards so as to be standing right up behind ")
							+(stocksCount>1
									?(playerStocks?"you, ready to take advantage of your immobility. ":"them, ready to take advantage of their immobility. ")
									:UtilText.parse(soloStocks, "[npc.name], ready to take advantage of [npc.her] immobility. ")));
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+" have reared up on their hind legs, before planting their front "
							+UtilText.parse(mainStandingTaur, "[npc.feet]")+" on the top of the stocks, allowing them to "
							+(stocksCount>1
									?(playerStocks?"effectively mount the "+(Util.intToString(stocksCount))+" of you. ":"effectively mount the "+(Util.intToString(stocksCount))+" of them. ")
									:UtilText.parse(soloStocks, "effectively mount [npc.name]. ")));
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStanding," [npc.has] reared up on [npc.her] hind legs, before planting [npc.her] front [npc.feet] on the top of the stocks, allowing [npc.herHim] to ")
							+(stocksCount>1
									?(playerStocks
											?UtilText.parse(mainStanding,"effectively mount whichever of the "+(Util.intToString(stocksCount))+" of you [npc.she] [npc.verb(want)]. ")
											:UtilText.parse(mainStanding,"effectively mount whichever of the "+(Util.intToString(stocksCount))+" of them [npc.she] [npc.verb(want)]. "))
									:UtilText.parse(soloStocks, "effectively mount [npc.name]. ")));
			}
			
			if(humpingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+" have clambered up onto "
						+(stocksCount>1
								?(playerStocks?"your lower back":"their lower backs")+", ready to start humping "+(playerStocks?"you. ":"them. ")
								:UtilText.parse(soloStocks, "[npc.namePos] lower back, ready to start humping [npc.herHim]. ")));
			
			
			} else if(humpingNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+UtilText.parse(mainHumping," [npc.has] clambered up onto ")
							+UtilText.parse(soloStocks, "[npc.namePos] lower back, ready to start humping [npc.herHim]. "));
			}
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+" are positioned so as to be right up between "
							+(stocksCount>1
									?(playerStocks?"your legs":"their legs")+", ready to perform oral on "+(playerStocks?"you. ":"them. ")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.legs], ready to perform oral on [npc.herHim]. ")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral," [npc.is] positioned so as to be right up between ")
							+(stocksCount>1
									?(playerStocks?"your legs":"their legs")+", ready to perform oral on "+(playerStocks?"you. ":"them. ")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.legs], ready to perform oral on [npc.herHim]. ")));
			}
			

			if(beneathNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+" have dropped down beneath "
							+(stocksCount>1
									?(playerStocks?"you":"them")+", ready to get fucked. "
									:UtilText.parse(soloStocks, "[npc.name], ready to get fucked by [npc.herHim]. ")));
				
				
			} else if(beneathNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+UtilText.parse(mainBeneath," [npc.has] dropped down beneath ")
							+(stocksCount>1
									?(playerStocks?"you":"them")+", ready to get fucked. "
									:UtilText.parse(soloStocks, "[npc.name], ready to get fucked by [npc.herHim]. ")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty() || !beneathNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", meanwhile,":"")+" are positioned around the other side of the stocks, next to "
							+(stocksCount>1
									?(playerStocks?"your faces":"their faces")+", in order to receive oral from "+(playerStocks?"you.":"them.")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.face], in order to receive oral from [npc.herHim].")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", meanwhile,":"")+UtilText.parse(mainReceivingOral, " [npc.is] positioned around the other side of the stocks, next to ")
						+(stocksCount>1
								?(playerStocks?"your faces":"their faces")+", in order to receive oral from "+(playerStocks?"you.":"them.")
								:UtilText.parse(soloStocks, "[npc.namePos] [npc.face], in order to receive oral from [npc.herHim].")));
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> inStocks = Util.newArrayListOfValues(SexSlotStocks.LOCKED_IN_STOCKS, SexSlotStocks.LOCKED_IN_STOCKS_TWO, SexSlotStocks.LOCKED_IN_STOCKS_THREE, SexSlotStocks.LOCKED_IN_STOCKS_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotStocks.BEHIND_STOCKS, SexSlotStocks.BEHIND_STOCKS_TWO, SexSlotStocks.BEHIND_STOCKS_THREE, SexSlotStocks.BEHIND_STOCKS_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotStocks.HUMPING, SexSlotStocks.HUMPING_TWO, SexSlotStocks.HUMPING_THREE, SexSlotStocks.HUMPING_FOUR);
			List<SexSlot> beneath = Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS, SexSlotStocks.BENEATH_STOCKS_TWO, SexSlotStocks.BENEATH_STOCKS_THREE, SexSlotStocks.BENEATH_STOCKS_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotStocks.PERFORMING_ORAL, SexSlotStocks.PERFORMING_ORAL_TWO, SexSlotStocks.PERFORMING_ORAL_THREE, SexSlotStocks.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotStocks.RECEIVING_ORAL, SexSlotStocks.RECEIVING_ORAL_TWO, SexSlotStocks.RECEIVING_ORAL_THREE, SexSlotStocks.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckingCharacterInStocks.getSexActionInteractions(slotStanding, stockSlot));
				}
				for(SexSlot slotHumping : humping) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(slotStanding, slotHumping));
				}
			}

			for(SexSlot slotHumping : humping) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.humpingCharacterInStocks.getSexActionInteractions(slotHumping, stockSlot));
				}
			}
			
			for(SexSlot slotBeneath: beneath) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckedByCharacterInStocks.getSexActionInteractions(slotBeneath, stockSlot));
				}
			}
			
			for(SexSlot slotOral : performingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.performingOralOnStocks.getSexActionInteractions(slotOral, stockSlot));
				}
			}

			for(SexSlot slotOral : receivingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.stocksCharacterPerformingOral.getSexActionInteractions(stockSlot, slotOral));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if((Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)
					&& action.getParticipantType()==SexParticipantType.SELF)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character in the stocks can use their tails or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.BEHIND_STOCKS)
					|| Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.HUMPING_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character performing oral can use their arms to force a facial creampie from those locked in stocks:
			if((Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS))
					&& (Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			// Characters locked in stocks cannot use fingers:
			if(Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_TWO
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_THREE
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(penetrator.getArmRows()*2)));
			}
			return super.getRestrictedPenetrationCounts(penetrator);
		}
	};
	
	public static final AbstractSexPosition MILKING_STALL = new AbstractSexPosition("Milking stall",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(MilkingStall.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(slot!= positioningSlots.get(characterToTakeSlot)) {
				if(positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "[npc.NameIsFull] locked into the stall, and so cannot switch slot!"));
					
				} else if(slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Characters cannot be locked into or out of stalls during this sex scene!"));
				}
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL, SexSlotMilkingStall.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO, SexSlotMilkingStall.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE, SexSlotMilkingStall.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR, SexSlotMilkingStall.PERFORMING_ORAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
												+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean characterInStocks=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					characterInStocks = true;
				}
			}
			if(!characterInStocks) {
				return new Value<Boolean, String>(false, "At least one character needs to be locked into the milking stall for this position to work.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> allStocksNames = new ArrayList<>();
			List<String> stocksNames = new ArrayList<>();
			List<String> stocksTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> beneathNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainStocks = null;
			GameCharacter mainStocksTaur = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainBeneath = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerStocks = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
						if(mainStocks==null && !e.getKey().isTaur()) {
							mainStocks=e.getKey();
						}
						if(mainStocksTaur==null && e.getKey().isTaur()) {
							mainStocksTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerStocks = true;
							allStocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allStocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.HUMPING
							|| e.getValue()==SexSlotMilkingStall.HUMPING_TWO
							|| e.getValue()==SexSlotMilkingStall.HUMPING_THREE
							|| e.getValue()==SexSlotMilkingStall.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_FOUR) {
						if(mainBeneath==null) {
							mainBeneath=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							beneathNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							beneathNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			int stocksCount = stocksNames.size() + stocksTaurNames.size();
			GameCharacter soloStocks = mainStocks==null?mainStocksTaur:mainStocks;
			if(stocksNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))+" are locked into "+Util.intToString(stocksNames.size())
						+" sets of milking stalls, and are completely at the mercy of anyone who wants to use "+(playerStocks?"you":"them")+". "); 
				
			} else if(stocksTaurNames.isEmpty()) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))
						+UtilText.parse(soloStocks,
								" [npc.is] locked into a milking stall, and [npc.is] completely at the mercy of anyone who wants to use [npc.herHim]. ")); 
			}
			
			if(stocksTaurNames.size()>=2) {
				sb.append(" Having feral lower bodies, "+Util.stringsToStringList(stocksTaurNames, false)
						+" have been locked into specially-designed milking stalls, which force their front halves down into a kneeling position, while their animalistic rear halves are held fully upright,"
						+ " allowing anyone to step up and use their mouths or genitals as they'd like. "); 
				
			} else if(stocksTaurNames.size()==1) {
				sb.append(UtilText.parse(mainStocksTaur,
						" Having the feral lower body of [npc.a_legRace], "+Util.stringsToStringList(stocksTaurNames, false)
						+" [npc.has] been locked into a specially-designed milking stall, which forces [npc.her] front half down into a kneeling position, while [npc.her] animalistic rear half is held fully upright,"
						+ " allowing anyone to step up and use [npc.her] mouth or genitals as they'd like. ")); 
			}
			
			// Standing behind:
			
			int standingCount = standingNames.size();
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+" have moved forwards so as to be standing right up behind "
							+(stocksCount>1
									?(playerStocks?"you, ready to take advantage of your immobility. ":"them, ready to take advantage of their immobility. ")
									:UtilText.parse(soloStocks, "[npc.name], ready to take advantage of [npc.her] immobility. ")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding," [npc.has] moved forwards so as to be standing right up behind ")
							+(stocksCount>1
									?(playerStocks?"you, ready to take advantage of your immobility. ":"them, ready to take advantage of their immobility. ")
									:UtilText.parse(soloStocks, "[npc.name], ready to take advantage of [npc.her] immobility. ")));
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+" have reared up on their hind legs, before planting their front "
							+UtilText.parse(mainStandingTaur, "[npc.feet]")+" on the top of the stall, allowing them to "
							+(stocksCount>1
									?(playerStocks?"effectively mount the "+(Util.intToString(stocksCount))+" of you. ":"effectively mount the "+(Util.intToString(stocksCount))+" of them. ")
									:UtilText.parse(soloStocks, "effectively mount [npc.name]. ")));
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStanding," [npc.has] reared up on [npc.her] hind legs, before planting [npc.her] front [npc.feet] on the top of the stall, allowing [npc.herHim] to ")
							+(stocksCount>1
									?(playerStocks
											?UtilText.parse(mainStanding,"effectively mount whichever of the "+(Util.intToString(stocksCount))+" of you [npc.she] [npc.verb(want)]. ")
											:UtilText.parse(mainStanding,"effectively mount whichever of the "+(Util.intToString(stocksCount))+" of them [npc.she] [npc.verb(want)]. "))
									:UtilText.parse(soloStocks, "effectively mount [npc.name]. ")));
			}

			
			if(humpingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+" have clambered up onto "
						+(stocksCount>1
								?(playerStocks?"your lower back":"their lower backs")+", ready to start humping "+(playerStocks?"you. ":"them. ")
								:UtilText.parse(soloStocks, "[npc.namePos] lower back, ready to start humping [npc.herHim]. ")));
			
			
			} else if(humpingNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+UtilText.parse(mainHumping," [npc.has] clambered up onto ")
							+UtilText.parse(soloStocks, "[npc.namePos] lower back, ready to start humping [npc.herHim]. "));
			}
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+" are positioned so as to be right up between "
							+(stocksCount>1
									?(playerStocks?"your legs":"their legs")+", ready to perform oral on "+(playerStocks?"you. ":"them. ")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.legs], ready to perform oral on [npc.herHim]. ")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral," [npc.is] positioned so as to be right up between ")
							+(stocksCount>1
									?(playerStocks?"your legs":"their legs")+", ready to perform oral on "+(playerStocks?"you. ":"them. ")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.legs], ready to perform oral on [npc.herHim]. ")));
			}

			
			if(beneathNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+" have dropped down beneath "
							+(stocksCount>1
									?(playerStocks?"you":"them")+", ready to get fucked. "
									:UtilText.parse(soloStocks, "[npc.name], ready to get fucked by [npc.herHim]. ")));
				
				
			} else if(beneathNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+UtilText.parse(mainBeneath," [npc.has] dropped down beneath ")
							+(stocksCount>1
									?(playerStocks?"you":"them")+", ready to get fucked. "
									:UtilText.parse(soloStocks, "[npc.name], ready to get fucked by [npc.herHim]. ")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", meanwhile,":"")+" are positioned around the other side of the "+(stocksCount==1?"stall":"stalls")+", next to "
							+(stocksCount>1
									?(playerStocks?"your faces":"their faces")+", in order to receive oral from "+(playerStocks?"you.":"them.")
									:UtilText.parse(soloStocks, "[npc.namePos] [npc.face], in order to receive oral from [npc.herHim].")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?", meanwhile,":"")
						+UtilText.parse(mainReceivingOral, " [npc.is] positioned around the other side of the "+(stocksCount==1?"stall":"stalls")+", next to ")
						+(stocksCount>1
								?(playerStocks?"your faces":"their faces")+", in order to receive oral from "+(playerStocks?"you.":"them.")
								:UtilText.parse(soloStocks, "[npc.namePos] [npc.face], in order to receive oral from [npc.herHim].")));
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> inStocks = Util.newArrayListOfValues(SexSlotMilkingStall.LOCKED_IN_MILKING_STALL, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL, SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO, SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE, SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotMilkingStall.HUMPING, SexSlotMilkingStall.HUMPING_TWO, SexSlotMilkingStall.HUMPING_THREE, SexSlotMilkingStall.HUMPING_FOUR);
			List<SexSlot> beneath = Util.newArrayListOfValues(SexSlotMilkingStall.BENEATH_MILKING_STALL, SexSlotMilkingStall.BENEATH_MILKING_STALL_TWO, SexSlotMilkingStall.BENEATH_MILKING_STALL_THREE, SexSlotMilkingStall.BENEATH_MILKING_STALL_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotMilkingStall.PERFORMING_ORAL, SexSlotMilkingStall.PERFORMING_ORAL_TWO, SexSlotMilkingStall.PERFORMING_ORAL_THREE, SexSlotMilkingStall.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotMilkingStall.RECEIVING_ORAL, SexSlotMilkingStall.RECEIVING_ORAL_TWO, SexSlotMilkingStall.RECEIVING_ORAL_THREE, SexSlotMilkingStall.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckingCharacterInStocks.getSexActionInteractions(slotStanding, stockSlot));
				}
				for(SexSlot slotHumping : humping) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(slotStanding, slotHumping));
				}
			}
			
			for(SexSlot slotHumping : humping) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.humpingCharacterInStocks.getSexActionInteractions(slotHumping, stockSlot));
				}
			}

			for(SexSlot slotBeneath: beneath) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckedByCharacterInStocks.getSexActionInteractions(slotBeneath, stockSlot));
				}
			}

			for(SexSlot slotOral : performingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.performingOralOnStocks.getSexActionInteractions(slotOral, stockSlot));
				}
			}

			for(SexSlot slotOral : receivingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.stocksCharacterPerformingOral.getSexActionInteractions(stockSlot, slotOral));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if((Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)
					&& action.getParticipantType()==SexParticipantType.SELF)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character in the stocks can use their tails or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.BEHIND_STOCKS)
					|| Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.HUMPING_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character performing oral can use their arms to force a facial creampie from those locked in stocks:
			if((Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			// Characters locked in stocks cannot use fingers:
			if(Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(penetrator.getArmRows()*2)));
			}
			return super.getRestrictedPenetrationCounts(penetrator);
		}
	};
	
	
	public static final AbstractSexPosition ALL_FOURS = new AbstractSexPosition("All fours",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(DoggyStyle.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_FOUR, SexSlotAllFours.BEHIND_ORAL_FOUR));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_ANAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_ANAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_ANAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_FOUR, SexSlotAllFours.IN_FRONT_ANAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
												+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
			// Added to limit humping character to those of a small stature, but it's more fun to let anyone take that slot
//			List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
//			List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
//			for(int i=0;i<4;i++) {
//				if(slot==humpingList.get(i)) {
//					for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
//						if(e.getValue()==allFoursList.get(i)) {
//							if(!characterToTakeSlot.isSizeDifferenceShorterThan(e.getKey())) {
//								return new Value<Boolean, String>(
//										false,
//										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' can only be used if "+UtilText.parse(characterToTakeSlot, "[npc.nameIsFull]")
//											+" significantly shorter than the character in the '"+Util.capitaliseSentence(allFoursList.get(i).getDescription())+"' slot.");
//							}
//						}
//					}
//				}
//			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS)
					&& (slot==SexSlotAllFours.BEHIND
							|| slot==SexSlotAllFours.BEHIND_ORAL
							|| slot==SexSlotAllFours.HUMPING
							|| slot==SexSlotAllFours.IN_FRONT_ANAL
							|| slot==SexSlotAllFours.IN_FRONT
							|| slot==SexSlotAllFours.USING_FEET)) {
				return new Value<Boolean, String>(
						false,
						"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used unless there is a character assigned to the '"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS.getDescription())+"' slot.");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_TWO)
					&& (slot==SexSlotAllFours.BEHIND_TWO
							|| slot==SexSlotAllFours.BEHIND_ORAL_TWO
							|| slot==SexSlotAllFours.HUMPING_TWO
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_TWO
							|| slot==SexSlotAllFours.IN_FRONT_TWO
							|| slot==SexSlotAllFours.USING_FEET_TWO)) {
				return new Value<Boolean, String>(
						false,
						"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used unless there is a character assigned to the '"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_TWO.getDescription())+"' slot.");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_THREE)
					&& (slot==SexSlotAllFours.BEHIND_THREE
							|| slot==SexSlotAllFours.BEHIND_ORAL_THREE
							|| slot==SexSlotAllFours.HUMPING_THREE
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_THREE
							|| slot==SexSlotAllFours.IN_FRONT_THREE
							|| slot==SexSlotAllFours.USING_FEET_THREE)) {
				return new Value<Boolean, String>(
						false,
						"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used unless there is a character assigned to the '"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_THREE.getDescription())+"' slot.");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_FOUR)
					&& (slot==SexSlotAllFours.BEHIND_FOUR
							|| slot==SexSlotAllFours.BEHIND_ORAL_FOUR
							|| slot==SexSlotAllFours.HUMPING_FOUR
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_FOUR
							|| slot==SexSlotAllFours.IN_FRONT_FOUR
							|| slot==SexSlotAllFours.USING_FEET_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used unless there is a character assigned to the '"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_FOUR.getDescription())+"' slot.");
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotAllFours.ALL_FOURS
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_TWO
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_THREE
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_FOUR) {
					suitablePosition = true;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "At least one character needs to be down on all fours for this position to work.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			// For each character on all fours, describe them and those who are interacting with them:

			List<SexSlot> position1 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_ORAL, SexSlotAllFours.USING_FEET, SexSlotAllFours.HUMPING, SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_ANAL);
			List<SexSlot> position2 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_ORAL_TWO, SexSlotAllFours.USING_FEET_TWO, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_ANAL_TWO);
			List<SexSlot> position3 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_ORAL_THREE, SexSlotAllFours.USING_FEET_THREE, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_ANAL_THREE);
			List<SexSlot> position4 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_FOUR, SexSlotAllFours.BEHIND_FOUR, SexSlotAllFours.BEHIND_ORAL_FOUR, SexSlotAllFours.USING_FEET_FOUR, SexSlotAllFours.HUMPING_FOUR, SexSlotAllFours.IN_FRONT_FOUR, SexSlotAllFours.IN_FRONT_ANAL_FOUR);
			
			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			int count=0;
			for(List<SexSlot> positions : positionLists) {
				GameCharacter allFours = null;
				GameCharacter behind = null;
				GameCharacter behindOral = null;
				GameCharacter usingFeet = null;
				GameCharacter humping = null;
				GameCharacter inFront = null;
				GameCharacter inFrontAnal = null;
				
				GameCharacter fallBackAllFours1 = null;
				GameCharacter fallBackAllFours2 = null;
				GameCharacter fallBackAllFours3 = null;
				
				for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
					if(e.getValue()==positions.get(0)) {
						allFours = e.getKey();
					}
					if(e.getValue()==positions.get(1)) {
						behind = e.getKey();
					}
					if(e.getValue()==positions.get(2)) {
						behindOral = e.getKey();
					}
					if(e.getValue()==positions.get(3)) {
						usingFeet = e.getKey();
					}
					if(e.getValue()==positions.get(4)) {
						humping = e.getKey();
					}
					if(e.getValue()==positions.get(5)) {
						inFront = e.getKey();
					}
					if(e.getValue()==positions.get(6)) {
						inFrontAnal = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS_THREE) {
						fallBackAllFours1 = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS_TWO) {
						fallBackAllFours2 = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS) {
						fallBackAllFours3 = e.getKey();
					}
				}
				
				boolean skipAllFours = false;
				if(allFours == null) {
					skipAllFours = true;
					allFours = fallBackAllFours1;
				}
				if(allFours == null) {
					allFours = fallBackAllFours2;
				}
				if(allFours == null) {
					allFours = fallBackAllFours3;
				}
				
				if(!skipAllFours) {
					switch(count) {
						case 0:
							sb.append(UtilText.parse(allFours,
									(!allFours.isTaur()
										?"[npc.NameIsFull] down on all fours, ready to be fucked in the doggy-style position. "
										:"[npc.NameHasFull] dropped the front legs of [npc.her] lower feral [npc.legRace]'s body down into a kneeling position, while keeping [npc.her] rear end raised up, ready to be fucked. ")));
							break;
						case 1:
							sb.append(UtilText.parse(allFours, fallBackAllFours3,
									(!allFours.isTaur()
										?" In a similar manner to [npc2.name], [npc.nameHasFull] dropped down on all fours, presenting [npc.herself] in order to be fucked like an animal. "
										:" In a similar manner to [npc2.name], [npc.nameHasFull] knelt down with the front half of [npc.her] [npc.legRace] body, while raising [npc.her] [npc.ass+] up in order to be fucked like an animal. ")));
							break;
						case 2:
							sb.append(UtilText.parse(Util.newArrayListOfValues(allFours, fallBackAllFours3, fallBackAllFours2),
									(!allFours.isTaur()
										?" Just like [npc2.name] and [npc3.name], [npc.nameHasFull] sunk down onto [npc.her] [npc.hands] and knees. "
										:" Just like [npc2.name] and [npc3.name], [npc.nameHasFull] sunk down onto [npc.her] front [npc.legs], while lifting [npc.her] [npc.ass+] up and presenting [npc.her] lower [npc.legRace]'s body to be rutted. ")));
							break;
						case 3:
							sb.append(UtilText.parse(Util.newArrayListOfValues(allFours, fallBackAllFours3, fallBackAllFours2, fallBackAllFours1),
									(!allFours.isTaur()
										?" Finishing off the group of four, [npc.nameIsFull] down on all fours beside [npc2.name], [npc3.name], and [npc4.name]. "
										:" Finishing off the group of four, [npc.nameIsFull] down beside [npc2.name], [npc3.name], and [npc4.name], and [npc.is] presenting [npc.her] feral [npc.legRace]'s body like a horny animal. ")));
							break;
					}
				}
				
				boolean continuation = false;
				if(humping!=null) {
					if(humping.isSizeDifferenceShorterThan(allFours)) {
						sb.append(UtilText.parse(humping, allFours,
								" Not letting [npc.her] smaller stature prevent [npc.herHim] from having fun, [npc.NameHasFull] climbed up onto [npc2.namePos] lower back, before gripping [npc2.her] waist and preparing to start humping [npc2.herHim]."));
					} else {
						sb.append(UtilText.parse(humping, allFours,
								" [npc.NameHasFull] crouched fully over [npc2.namePos] back, and, bumping [npc.her] groin against [npc2.her] [npc2.ass+], [npc.is] prepared to start humping [npc2.herHim]."));
					}
					continuation = true;
				}
				if(behind!=null) {
					sb.append(UtilText.parse(behind, allFours,
							!behind.isTaur()
								?" [npc.NameIsFull] "+(positions.get(1).isStanding(behind)?"standing":"kneeling")+" just behind [npc2.herHim], in a position where [npc.she] can start fucking [npc2.herHim] at any moment."
								:" [npc.NameHasFull] stepped fully over the top of [npc2.herHim] with [npc.her] feral [npc.legRace]'s body, effectively mounting [npc2.herHim]."));
					continuation = true;
				}
				if(behindOral!=null) {
					sb.append(UtilText.parse(behindOral, allFours,
							" [npc.NameHasFull] "+(positions.get(2).isStanding(behindOral)?"moved up to stand":"dropped down onto [npc.her] knees")+" behind [npc2.name], ready to perform oral on [npc2.herHim]."));
					continuation = true;
				}
				if(usingFeet!=null) {
					sb.append(UtilText.parse(usingFeet, allFours,
							" By sinking down onto [npc.her] knees behind [npc2.name], [npc.name] [npc.has] positioned [npc.herself] in such a way as to be able to use [npc2.her] [npc2.feet+]."));
					continuation = true;
				}

				if(inFront!=null) {
					sb.append(UtilText.parse(inFront, allFours,
							continuation
							?" Meanwhile, around the other side of [npc2.name], [npc.nameHasFull] "+(positions.get(5).isStanding(inFront)?"stepped":"knelt down")
									+(inFront.isTaur()
										?" over the top of [npc2.herHim], before pushing [npc.her] animalistic groin towards [npc2.her] [npc2.face] so that [npc.she] can receive oral from [npc2.herHim]."
										:" up in front of [npc2.her] [npc2.face], ready to receive oral from [npc2.herHim].")
							:" Instead of taking advantage of [npc2.herHim] from behind, [npc.nameHasFull] "+(positions.get(5).isStanding(inFront)?"stepped up":"knelt down")
								+" right in front of [npc2.her] [npc2.face], ready to receive oral from [npc2.herHim]."));
				}
				if(inFrontAnal!=null) {
					sb.append(UtilText.parse(inFrontAnal, allFours,
							continuation
							?" Meanwhile, around the other side of [npc2.name], [npc.nameHasFull] "+(positions.get(6).isStanding(inFrontAnal)?"stepped up and turned around":"knelt down and shuffled back")
									+" towards [npc2.her] [npc2.face], ready to put [npc2.her] mouth to use on [npc.her] [npc.ass+]."
							:" Instead of taking advantage of [npc2.herHim] from behind, [npc.nameHasFull] "+(positions.get(6).isStanding(inFrontAnal)?"stepped up and turned around":"knelt down and shuffled back")
								+" towards [npc2.her] [npc2.face], ready to put [npc2.her] mouth to use on [npc.her] [npc.ass+]."));
				}

				if(!skipAllFours) {
					count++;
				}
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
			List<SexSlot> behindList = Util.newArrayListOfValues(SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_FOUR);
			List<SexSlot> behindOralList = Util.newArrayListOfValues(SexSlotAllFours.BEHIND_ORAL, SexSlotAllFours.BEHIND_ORAL_TWO, SexSlotAllFours.BEHIND_ORAL_THREE, SexSlotAllFours.BEHIND_ORAL_FOUR);
			List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
			List<SexSlot> feetList = Util.newArrayListOfValues(SexSlotAllFours.USING_FEET, SexSlotAllFours.USING_FEET_TWO, SexSlotAllFours.USING_FEET_THREE, SexSlotAllFours.USING_FEET_FOUR);
			List<SexSlot> inFrontList = Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_FOUR);
			List<SexSlot> inFrontAnalList = Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_ANAL, SexSlotAllFours.IN_FRONT_ANAL_TWO, SexSlotAllFours.IN_FRONT_ANAL_THREE, SexSlotAllFours.IN_FRONT_ANAL_FOUR);
			
			// Those down on all fours can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO));
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE));
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR));
			
			// All those behind can interact with all those on all fours, those humping, and those in front:
			for(SexSlot behindSlot : behindList) {
				for(SexSlot allFoursSlot : allFoursList) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(behindSlot, allFoursSlot));
				}
				for(SexSlot humpingSlot : humpingList) {
					interactions.add(StandardSexActionInteractions.allFoursBehindToHumping.getSexActionInteractions(behindSlot, humpingSlot));
				}
			}
			for(int i=0; i<4; i++) {
				if(Main.sex.getCharacterInPosition(allFoursList.get(i))!=null && !Main.sex.getCharacterInPosition(allFoursList.get(i)).isTaur()) {
					for(SexSlot inFrontSlot : inFrontList) {
						interactions.add(StandardSexActionInteractions.allFoursCharacterBehindToCharactersFront.getSexActionInteractions(behindList.get(i), inFrontSlot));
					}
				}
			}
			
			// Those performing oral behind can oral the humpers:
			for(SexSlot behindOralSlot : behindOralList) {
				for(SexSlot humpingSlot : humpingList) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(behindOralSlot, humpingSlot));
				}
			}
			
			for(SexSlot allFoursSlot : allFoursList) {
				// All those performing oral behind can interact with all those on all fours:
				for(SexSlot behindOralSlot : behindOralList) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(behindOralSlot, allFoursSlot));
				}
				// All those behind wanting to use feet can interact with all those on all fours:
				for(SexSlot feetSlot : feetList) {
					interactions.add(StandardSexActionInteractions.allFoursFeet.getSexActionInteractions(feetSlot, allFoursSlot));
				}
				// All those receiving oral interact with all those on all fours:
				for(SexSlot inFrontSlot : inFrontList) {
					interactions.add(StandardSexActionInteractions.allFoursPerformingOral.getSexActionInteractions(allFoursSlot, inFrontSlot));
				}
				// All those receiving anal oral interact with all those on all fours:
				for(SexSlot inFrontAnalSlot : inFrontAnalList) {
					interactions.add(StandardSexActionInteractions.allFoursPerformingOralBehind.getSexActionInteractions(allFoursSlot, inFrontAnalSlot));
				}
			}
			
			// Humping characters can only interact with those they are on top of:
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING, SexSlotAllFours.ALL_FOURS));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.ALL_FOURS_TWO));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.ALL_FOURS_THREE));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_FOUR, SexSlotAllFours.ALL_FOURS_FOUR));

			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their tails or tentacles to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_FOUR)) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_FOUR
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Tail.class, genericGroinForceCreampieAreas),
							new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				}
			}
			// Non-bipedal characters performing oral can use their arm(s) to force a mouth creampie:
			if(cumTarget.isTaur()
					&& (Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_FOUR)) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Arm.class, genericFaceForceCreampieAreas));
				}
			}
			return null;
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			// Restrict anal actions if the one humping is in the way:
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.ALL_FOURS) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.BEHIND_ALL_FOURS)) {
				List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
				List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter humper = Main.sex.getCharacterInPosition(humpingList.get(i));
					GameCharacter allFours = Main.sex.getCharacterInPosition(allFoursList.get(i));
					if(humper!=null
							&& target.equals(allFours)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.ANUS) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.ASS))
							&& Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaOrifice.VAGINA).contains(humper)) {
						return true;
					}
				}
			}
			return super.isActionBlocked(performer, target, action);
		}
	};
	
	
	//TODO sitting on top for piazuri
	/**
	 * Contains support for:<br/>
	 * <b>Face-sitting</b><br/>
	 * <b>Cow-girl</b><br/>
	 * <b>Missionary</b><br/>
	 * <b>Reverse cow-girl</b><br/>
	 * <b>Sixty-nine</b><br/>
	 * <b>Mating press</b><br/>
	 * <b>Scissoring</b>
	 */
	public static final AbstractSexPosition LYING_DOWN = new AbstractSexPosition("Lying down",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(Cowgirl.class, FaceSitting.class, MatingPress.class, Missionary.class, SixtyNine.class)) {
		
		private List<SexSlot> position1 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN, //0
				SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.SCISSORING, //1-5
				SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.SIXTY_NINE, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.BESIDE); //6-11
		private List<SexSlot> position2 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_TWO, //0
				SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.SCISSORING_TWO, //1-5
				SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO, SexSlotLyingDown.SIXTY_NINE_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.BESIDE_TWO); //6-11
		private List<SexSlot> position3 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_THREE, //0
				SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.SCISSORING_THREE, //1-5
				SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE, SexSlotLyingDown.SIXTY_NINE_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.BESIDE_THREE); //6-11
		private List<SexSlot> position4 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_FOUR, //0
				SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.MISSIONARY_FOUR, SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.SCISSORING_FOUR, //1-5
				SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.MISSIONARY_ORAL_FOUR, SexSlotLyingDown.BESIDE_FOUR); //6-11
		
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.SCISSORING));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.SCISSORING_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.SCISSORING_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.SCISSORING_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.SIXTY_NINE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.SIXTY_NINE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.SIXTY_NINE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR));

			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.SCISSORING));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.SCISSORING_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.SCISSORING_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_FOUR, SexSlotLyingDown.MISSIONARY_ORAL_FOUR, SexSlotLyingDown.SCISSORING_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
												+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}

			// Mating press is only compatible with missionary & oral:
			mutuallyExclusiveSlots.clear();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.FACE_SITTING_REVERSE,
					SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.SIXTY_NINE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO,
					SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.SCISSORING_TWO, SexSlotLyingDown.SIXTY_NINE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE,
					SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.SCISSORING_THREE, SexSlotLyingDown.SIXTY_NINE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR,
					SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.SCISSORING_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR));

			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				SexSlot matingPressSlot = entry.get(0);
				for(SexSlot s : entry) {
					if(s==slot) {
						if(s==matingPressSlot) {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
													+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
								}
							}
							
						} else {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(e.getValue()==matingPressSlot && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
													+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
								}
							}
						}
					}
				}
			}
			
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotLyingDown.SCISSORING
						|| slot==SexSlotLyingDown.SCISSORING_TWO
						|| slot==SexSlotLyingDown.SCISSORING_THREE
						|| slot==SexSlotLyingDown.SCISSORING_FOUR)) {
				return new Value<Boolean, String>(false, "The slot '"+Util.capitaliseSentence(slot.getDescription())+"' can only be used by characters with a bipedal lower body.");
			}
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotLyingDown.SIXTY_NINE
						|| slot==SexSlotLyingDown.SIXTY_NINE_TWO
						|| slot==SexSlotLyingDown.SIXTY_NINE_THREE
						|| slot==SexSlotLyingDown.SIXTY_NINE_FOUR)) {
				return new Value<Boolean, String>(false, "The slot '"+Util.capitaliseSentence(slot.getDescription())+"' can only be used by characters with a bipedal lower body.");
			}
			
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN)
					&& (slot==SexSlotLyingDown.COWGIRL
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE
							|| slot==SexSlotLyingDown.FACE_SITTING
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE
							|| slot==SexSlotLyingDown.LAP_PILLOW
							|| slot==SexSlotLyingDown.MATING_PRESS
							|| slot==SexSlotLyingDown.MISSIONARY
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL
							|| slot==SexSlotLyingDown.SCISSORING
							|| slot==SexSlotLyingDown.SIXTY_NINE)) {
				return new Value<Boolean, String>(
						false,
						"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used unless there is a character assigned to the '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN.getDescription())+"' slot.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_TWO)
					&& (slot==SexSlotLyingDown.COWGIRL_TWO
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_TWO
							|| slot==SexSlotLyingDown.FACE_SITTING_TWO
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_TWO
							|| slot==SexSlotLyingDown.LAP_PILLOW_TWO
							|| slot==SexSlotLyingDown.MATING_PRESS_TWO
							|| slot==SexSlotLyingDown.MISSIONARY_TWO
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_TWO
							|| slot==SexSlotLyingDown.SCISSORING_TWO
							|| slot==SexSlotLyingDown.SIXTY_NINE_TWO)) {
				return new Value<Boolean, String>(
						false,
						"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used unless there is a character assigned to the '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_TWO.getDescription())+"' slot.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_THREE)
					&& (slot==SexSlotLyingDown.COWGIRL_THREE
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_THREE
							|| slot==SexSlotLyingDown.FACE_SITTING_THREE
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_THREE
							|| slot==SexSlotLyingDown.LAP_PILLOW_THREE
							|| slot==SexSlotLyingDown.MATING_PRESS_THREE
							|| slot==SexSlotLyingDown.MISSIONARY_THREE
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_THREE
							|| slot==SexSlotLyingDown.SCISSORING_THREE
							|| slot==SexSlotLyingDown.SIXTY_NINE_THREE)) {
				return new Value<Boolean, String>(
						false,
						"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used unless there is a character assigned to the '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_THREE.getDescription())+"' slot.");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FOUR)
					&& (slot==SexSlotLyingDown.COWGIRL_FOUR
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_FOUR
							|| slot==SexSlotLyingDown.FACE_SITTING_FOUR
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR
							|| slot==SexSlotLyingDown.LAP_PILLOW_FOUR
							|| slot==SexSlotLyingDown.MATING_PRESS_FOUR
							|| slot==SexSlotLyingDown.MISSIONARY_FOUR
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_FOUR
							|| slot==SexSlotLyingDown.SCISSORING_FOUR
							|| slot==SexSlotLyingDown.SIXTY_NINE_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used unless there is a character assigned to the '"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FOUR.getDescription())+"' slot.");
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotLyingDown.LYING_DOWN
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_TWO
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_THREE
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FOUR) {
					suitablePosition = true;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "At least one character needs to be lying down for this position to work.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			// For each character lying down, describe them and those who are interacting with them:

			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			List<String> besideNames = new ArrayList<>();
			GameCharacter mainBeside = null;
			
			int count=0;
			for(List<SexSlot> positions : positionLists) {
				GameCharacter lyingDown = null;
				GameCharacter cowgirl = null;
				GameCharacter cowgirlReverse = null;
				GameCharacter missionary = null;
				GameCharacter matingPress = null;
				GameCharacter scissoring = null;
				GameCharacter faceSitting = null;
				GameCharacter faceSittingReverse = null;
				GameCharacter sixtyNine = null;
				GameCharacter lapPillow = null;
				GameCharacter performingOral = null;
				
				GameCharacter fallBackLyingDown1 = null;
				GameCharacter fallBackLyingDown2 = null;
				GameCharacter fallBackLyingDown3 = null;
				
				for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
					if(e.getValue()==positions.get(0)) {
						lyingDown = e.getKey();
					}
					if(e.getValue()==positions.get(1)) {
						cowgirl = e.getKey();
					}
					if(e.getValue()==positions.get(2)) {
						cowgirlReverse = e.getKey();
					}
					if(e.getValue()==positions.get(3)) {
						missionary = e.getKey();
					}
					if(e.getValue()==positions.get(4)) {
						matingPress = e.getKey();
					}
					if(e.getValue()==positions.get(5)) {
						scissoring = e.getKey();
					}
					if(e.getValue()==positions.get(6)) {
						faceSitting = e.getKey();
					}
					if(e.getValue()==positions.get(7)) {
						faceSittingReverse = e.getKey();
					}
					if(e.getValue()==positions.get(8)) {
						sixtyNine = e.getKey();
					}
					if(e.getValue()==positions.get(9)) {
						lapPillow = e.getKey();
					}
					if(e.getValue()==positions.get(10)) {
						performingOral = e.getKey();
					}
					if(e.getValue()==positions.get(11)) {
						if(mainBeside==null) {
							mainBeside = e.getKey();
						}
						if(e.getKey().isPlayer()) {
							besideNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							besideNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
					
					if(e.getValue()==SexSlotLyingDown.LYING_DOWN_THREE) {
						fallBackLyingDown1 = e.getKey();
					}
					if(e.getValue()==SexSlotLyingDown.LYING_DOWN_TWO) {
						fallBackLyingDown2 = e.getKey();
					}
					if(e.getValue()==SexSlotLyingDown.LYING_DOWN) {
						fallBackLyingDown3 = e.getKey();
					}
				}
				
				boolean skipLyingdown = false;
				if(lyingDown == null) {
					skipLyingdown = true;
					lyingDown = fallBackLyingDown1;
				}
				if(lyingDown == null) {
					lyingDown = fallBackLyingDown2;
				}
				if(lyingDown == null) {
					lyingDown = fallBackLyingDown3;
				}
				
				if(!skipLyingdown) {
					switch(count) {
						case 0:
							sb.append(UtilText.parse(lyingDown,
									(!lyingDown.isTaur()
										?"[npc.NameIsFull] lying down on [npc.her] back, submissively exposing [npc.her] stomach, [npc.face], and groin. "
										:"[npc.NameHasFull] lain down on [npc.her] feral [npc.legRace]'s body, before rolling over onto [npc.her] back in order to submissively expose [npc.her] stomach. ")));
							break;
						case 1:
							sb.append(UtilText.parse(lyingDown, fallBackLyingDown3,
									(!lyingDown.isTaur()
										?" In a similar manner to [npc2.name], [npc.nameHasFull] dropped down to lie on [npc.her] back. "
										:" In a similar manner to [npc2.name], [npc.nameHasFull] knelt down onto [npc.her] feral [npc.legRace]'s body, before rolling over and presenting [npc.her] underside. ")));
							break;
						case 2:
							sb.append(UtilText.parse(Util.newArrayListOfValues(lyingDown, fallBackLyingDown3, fallBackLyingDown2),
									(!lyingDown.isTaur()
										?" Just like [npc2.name] and [npc3.name], [npc.nameHasFull] sunk down onto the floor, before lying down on [npc.her] back. "
										:" Just like [npc2.name] and [npc3.name], [npc.nameHasFull] sunk down onto [npc.her] feral [npc.legRace]'s body, before rolling over onto [npc.her] back and presenting [npc.herself]. ")));
							break;
						case 3:
							sb.append(UtilText.parse(Util.newArrayListOfValues(lyingDown, fallBackLyingDown3, fallBackLyingDown2, fallBackLyingDown1),
									(!lyingDown.isTaur()
										?" Finishing off the group of four, [npc.nameIsFull] lying down on [npc.her] back beside [npc2.name], [npc3.name], and [npc4.name]. "
										:" Finishing off the group of four, [npc.nameIsFull] lying down beside [npc2.name], [npc3.name], and [npc4.name], and [npc.has] rolled over to present the underside of [npc.her] feral [npc.legRace]'s body. ")));
							break;
					}
					count++;
				}
				
				boolean continuation = false;
				// These four slots are mutually exclusive:
				if(cowgirl!=null) {
					if(!cowgirl.isTaur()) {
						sb.append(UtilText.parse(cowgirl, lyingDown,
								" [npc.NameHasFull] stepped over the top of [npc2.name], before lowering [npc.herself] down and straddling [npc2.her] groin, ready to start riding [npc2.herHim] in the cowgirl position."));
					} else {
						sb.append(UtilText.parse(cowgirl, lyingDown,
								" [npc.NameHasFull] stepped over the top of [npc2.name], before dropping the rear-half of [npc.her] feral [npc.legRace]'s body down in order to plant [npc.her] groin firmly against [npc2.hers]."));
					}
					continuation = true;
				}
				if(cowgirlReverse!=null) {
					if(!cowgirlReverse.isTaur()) {
						sb.append(UtilText.parse(cowgirlReverse, lyingDown,
								" [npc.NameHasFull] stepped over the top of [npc2.name], before turning around and lowering [npc.herself] down and straddling [npc2.her] groin, ready to start riding [npc2.herHim] in the reverse cowgirl position."));
					} else {
						sb.append(UtilText.parse(cowgirlReverse, lyingDown,
								" [npc.NameHasFull] stepped over the top of [npc2.name], before turning around and dropping the rear-half of [npc.her] feral [npc.legRace]'s body down in order to plant [npc.her] groin firmly against [npc2.hers]."));
					}
					continuation = true;
				}
				if(matingPress!=null) {
					if(!matingPress.hasLegs()) {
						sb.append(UtilText.parse(matingPress, lyingDown,
								" [npc.NameHasFull] pushed [npc2.namePos] down onto [npc2.her] back, before lying down on top of [npc2.herHim] with [npc.her] groin hovering just over [npc2.hers]."
									+ " [npc.SheIs] dominantly using [npc.her] [npc.hands] to pin [npc2.namePos] wrists to the floor on either side of [npc2.her] head,"
										+ " thereby fully locking [npc2.herHim] down beneath [npc.herHim] in a position suitable for being bred."));
						
					} else if(!matingPress.isTaur()) {
						sb.append(UtilText.parse(matingPress, lyingDown,
								" [npc.NameHasFull] pushed [npc2.namePos] [npc2.legs] apart and back up towards [npc2.her] head, before lying down on top of [npc2.herHim] with [npc.her] groin hovering just over [npc2.hers]."
									+ " [npc.SheIs] dominantly using [npc.her] [npc.hands] to pin [npc2.namePos] wrists to the floor on either side of [npc2.her] head,"
										+ " thereby fully locking [npc2.herHim] down beneath [npc.herHim] in a position suitable for being bred."));
						
					} else {
						sb.append(UtilText.parse(matingPress, lyingDown,
								" [npc.NameHasFull] pushed [npc2.namePos] [npc2.legs] apart and back up towards [npc2.her] head, before dropping [npc.her] feral [npc.legRace]'s body down on top of [npc2.herHim],"
										+ " with [npc.her] animalistic groin hovering just over [npc2.hers]."
									+ " [npc.SheIs] dominantly using [npc.her] front [npc.legs] to pin [npc2.name] wrists to the floor on either side of [npc2.her] head,"
										+ " thereby fully locking [npc2.herHim] down beneath [npc.herHim] in a position suitable for being bred."));
					}
					continuation = true;
				}
				if(scissoring!=null) {
					sb.append(UtilText.parse(scissoring, lyingDown,
							" [npc.NameHasFull] laid down on [npc.her] back, and by [npc.spreadingHerLegs] and shuffling forwards,"
									+ " [npc.has] brought [npc.her] groin into contact with [npc2.hers], ready to start scissoring [npc2.herHim]."));
					continuation = true;
				}

				if(missionary!=null) {
					if(!missionary.isTaur()) {
						sb.append(UtilText.parse(missionary, lyingDown,
								" [npc.NameHasFull] dropped down between [npc2.namePos] [npc2.legs], ready to fuck [npc2.herHim] in the missionary position."));
					} else {
						sb.append(UtilText.parse(missionary, lyingDown,
								" [npc.NameHasFull] lowered [npc.her] feral [npc.legRace]'s body down over the top of [npc2.name], and [npc.is] ready to start rutting [npc2.herHim] in the missionary position."));
					}
					continuation = true;
				}
				
				if(faceSitting!=null) {
					if(!faceSitting.isTaur()) {
						sb.append(UtilText.parse(faceSitting, lyingDown,
								continuation
								?" Meanwhile, eager for some face-sitting action, [npc.nameHasFull] stepped over the top of [npc2.name], before lowering [npc.herself] down and planting [npc.her] groin firmly over [npc2.her] [npc2.face]."
								:" Eager for some face-sitting action, [npc.nameHasFull] stepped over the top of [npc2.name], before lowering [npc.herself] down and planting [npc.her] groin firmly over [npc2.her] [npc2.face]."));
					} else {
						sb.append(UtilText.parse(faceSitting, lyingDown,
								continuation
								?" Meanwhile, eager for some face-sitting action, [npc.nameHasFull] stepped over the top of [npc2.name],"
										+ " before lowering [npc.her] feral [npc.legRace]'s body down and planting [npc.her] animalistic groin firmly over [npc2.her] [npc2.face]."
								:" Eager for some face-sitting action, [npc.nameHasFull] stepped over the top of [npc2.name],"
										+ " before lowering [npc.her] feral [npc.legRace]'s body down and planting [npc.her] animalistic groin firmly over [npc2.her] [npc2.face]."));
					}
				}
				if(faceSittingReverse!=null) {
					if(!faceSittingReverse.isTaur()) {
						sb.append(UtilText.parse(faceSittingReverse, lyingDown,
								continuation
								?" Meanwhile, [npc.nameHasFull] stepped over the top of [npc2.name], before turning around to face [npc2.her] lower body and then lowering [npc.herself] down in order to assume the reverse face-sitting position."
								:" [npc.NameHasFull] stepped over the top of [npc2.name], before turning around to face [npc2.her] lower body and then lowering [npc.herself] down in order to assume the reverse face-sitting position."));
					} else {
						sb.append(UtilText.parse(faceSittingReverse, lyingDown,
								continuation
								?" Meanwhile, [npc.nameHasFull] stepped over the top of [npc2.name],"
										+ " before turning around to face [npc2.her] lower body and then lowering [npc.her] feral [npc.legRace]'s body down in order to assume the reverse face-sitting position."
								:" [npc.NameHasFull] stepped over the top of [npc2.name],"
										+ " before turning around to face [npc2.her] lower body and then lowering [npc.her] feral [npc.legRace]'s body down in order to assume the reverse face-sitting position."));
					}
				}
				if(sixtyNine!=null) {
					sb.append(UtilText.parse(sixtyNine, lyingDown,
							continuation
							?" Meanwhile, eager for some oral action, [npc.nameHasFull] stepped over the top of [npc2.name], before turning around and lowering [npc.herself] over [npc2.herHim] in the sixty-nine position."
							:" Eager for some oral action, [npc.nameHasFull] stepped over the top of [npc2.name], before turning around and lowering [npc.herself] over [npc2.herHim] in the sixty-nine position."));
				}
				if(lapPillow!=null) {
					sb.append(UtilText.parse(lapPillow, lyingDown,
							continuation
							?" Meanwhile, eager to fuss over [npc2.herHim], [npc.nameHasFull] knelt down beside [npc2.namePos] [npc2.face], before lifting [npc2.her] head and resting it on [npc.her] lap."
							:" Eager to fuss over [npc2.herHim], [npc.nameHasFull] knelt down beside [npc2.namePos] [npc2.face], before lifting [npc2.her] head and resting it on [npc.her] lap."));
				}

				if(performingOral!=null) {
					sb.append(UtilText.parse(performingOral, lyingDown,
							" Wanting to perform oral on [npc2.name], [npc.nameHasFull] laid down between [npc2.her] [npc2.legs], bringing [npc.her] [npc.face] right down to [npc2.her] groin."));
				}
				
			}
			
			if(besideNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(besideNames, false))
						+" are positioned off to the side, ready to receive oral or some non-penetrative sex. "); 
				
			} else if(besideNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(besideNames, false))
						+UtilText.parse(mainBeside,
								" [npc.is] positioned off to the side, ready to receive oral or some non-penetrative sex. ")); 
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			// For each character lying down, describe them and those who are interacting with them:

			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			List<SexSlot> besideSlots = Util.newArrayListOfValues(SexSlotLyingDown.BESIDE, SexSlotLyingDown.BESIDE_TWO, SexSlotLyingDown.BESIDE_THREE, SexSlotLyingDown.BESIDE_FOUR);
			
			for(int i=0; i<positionLists.size(); i++) {
				List<SexSlot> positions = positionLists.get(i);
				
				SexSlot lyingDown = positions.get(0);
				SexSlot cowgirl = positions.get(1);
				SexSlot cowgirlReverse = positions.get(2);
				SexSlot missionary = positions.get(3);
				SexSlot matingPress = positions.get(4);
				SexSlot scissoring = positions.get(5);
				SexSlot faceSitting = positions.get(6);
				SexSlot faceSittingReverse = positions.get(7);
				SexSlot sixtyNine = positions.get(8);
				SexSlot lapPillow = positions.get(9);
				SexSlot performingOral = positions.get(10);
				
				interactions.add(StandardSexActionInteractions.cowgirlRiding.getSexActionInteractions(cowgirl, lyingDown));
				interactions.add(StandardSexActionInteractions.cowgirlReverseRiding.getSexActionInteractions(cowgirlReverse, lyingDown));
				interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, lyingDown));
				// Add missionary to lying down characters beside the main target:
				if(i>0) {
					interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, positionLists.get(i-1).get(0)));
				}
				if(i+1<positionLists.size()) {
					interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, positionLists.get(i+1).get(0)));
				}
				
				interactions.add(StandardSexActionInteractions.matingPress.getSexActionInteractions(matingPress, lyingDown));
				interactions.add(StandardSexActionInteractions.scissoring.getSexActionInteractions(scissoring, lyingDown));
				interactions.add(StandardSexActionInteractions.faceSittingRiding.getSexActionInteractions(faceSitting, lyingDown));
				interactions.add(StandardSexActionInteractions.faceSittingReverseRiding.getSexActionInteractions(faceSittingReverse, lyingDown));
				interactions.add(StandardSexActionInteractions.sixtyNine.getSexActionInteractions(sixtyNine, lyingDown));
				interactions.add(StandardSexActionInteractions.sixtyNine.getSexActionInteractions(lyingDown, sixtyNine));
				interactions.add(StandardSexActionInteractions.lapPillow.getSexActionInteractions(lapPillow, lyingDown));
				
				interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, lyingDown));
				// Add oral to lying down characters beside the main target:
				if(i>0) {
					interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, positionLists.get(i-1).get(0)));
				}
				if(i+1<positionLists.size()) {
					interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, positionLists.get(i+1).get(0)));
				}
				
				interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, matingPress));
				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(performingOral, cowgirl));
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(performingOral, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.performingOralToSixtyNine.getSexActionInteractions(performingOral, sixtyNine));
				
				
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, scissoring));
				interactions.add(StandardSexActionInteractions.characterToCharactersBack.getSexActionInteractions(faceSittingReverse, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(lapPillow, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(lapPillow, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersBack.getSexActionInteractions(lapPillow, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(cowgirlReverse, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(cowgirlReverse, scissoring));

				interactions.add(StandardSexActionInteractions.characterToCharactersBackSex.getSexActionInteractions(missionary, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersBackSex.getSexActionInteractions(missionary, matingPress));

				// Those beside can interact with all lying down and kneeling:
				for(SexSlot beside : besideSlots) {
					interactions.add(StandardSexActionInteractions.besideLyingDown.getSexActionInteractions(beside, lyingDown));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, cowgirl));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, cowgirlReverse));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, missionary));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, scissoring));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, faceSitting));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, faceSittingReverse));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, lapPillow));
				}
			}
			
			// Those lying down can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR));

			// Those in missionary positions can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_FOUR));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// Characters riding the one lying down can use their bodyweight to force a creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_FOUR
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_FOUR
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_FOUR) {
						return Util.newHashMapOfValues(
								new Value<>(Torso.class, genericGroinForceCreampieAreas));
					}
			}
			// Characters performing sixty-nine or lying down oral can use weight to force a facial creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_FOUR
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_FOUR) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericFaceForceCreampieAreas));
				}
			}
			// Characters lying down can use their legs to force a creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_FOUR
				|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Leg.class, genericGroinForceCreampieAreas),
							new Value<>(Tail.class, genericGroinForceCreampieAreas),
							new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				}
			}
			// Characters performing oral on sixty-nine can use arms to force a facial creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Arm.class, genericFaceForceCreampieAreas));
				}
			}
			
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			List<SexSlot> slotsTop = Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.MATING_PRESS_FOUR);
			List<SexSlot> slotsBottom = Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR);
			
			for(int i=0;i<4;i++) {
				GameCharacter top = Main.sex.getCharacterInPosition(slotsTop.get(i));
				GameCharacter bottom = Main.sex.getCharacterInPosition(slotsBottom.get(i));
				
				if(penetrator.equals(top) && bottom!=null) {
					return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(bottom.getArmRows()*2)));
				}
				if(penetrator.equals(bottom)&& top!=null) {
					return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(top.getArmRows()*2)));
				}
			}
			
			return super.getRestrictedPenetrationCounts(penetrator);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
//			// Restrict targeting lying down oral and missionary if there's another person in the same position focused on them:
//			if(performer!=target) {
//				List<SexSlot> slotsLyingDown = Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR);
//				List<SexSlot> slotsPerformingOral = Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.MISSIONARY_ORAL_FOUR);
//				List<SexSlot> slotsMissionary = Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_FOUR);
//				for(int i=0; i<4; i++) {
//					if(Main.sex.getSexPositionSlot(target)!=slotsLyingDown.get(i) && (Main.sex.getSexPositionSlot(performer)==slotsPerformingOral.get(i) || Main.sex.getSexPositionSlot(performer)==slotsMissionary.get(i))) {
//						int targetIndex = 0;
//						for(int j=0; j<4; j++) {
//							if(Main.sex.getSexPositionSlot(target)==slotsLyingDown.get(j)) {
//								targetIndex = j;
//								break;
//							}
//						}
//						if(Main.sex.getSexPositionSlot(performer)==slotsPerformingOral.get(i) && Main.sex.getAllOccupiedSlots(false).containsValue(slotsPerformingOral.get(targetIndex))) {
//							return true;
//						}
//						if(Main.sex.getSexPositionSlot(performer)==slotsMissionary.get(i) && Main.sex.getAllOccupiedSlots(false).containsValue(slotsMissionary.get(targetIndex))) {
//							return true;
//						}
//					}
//				}
//			}
			
			// Restrict fucking breasts if cowgirl character is riding cock.
			if(Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.COWGIRL) && Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(performer.equals(cowgirl)
							&& action.getPerformingCharacterAreas().contains(SexAreaPenetration.PENIS)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.BREAST) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE))
							&& (Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.VAGINA, target).contains(SexAreaPenetration.PENIS)
									|| Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.ANUS, target).contains(SexAreaPenetration.PENIS)
									|| Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.ASS, target).contains(SexAreaPenetration.PENIS))) {
						return true;
					}
				}
			}
			
			// Restrict vaginal actions if cowgirl is riding cock anally.
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.COWGIRL) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(target.equals(cowgirl)
							&& action.getTargetedCharacterAreas().contains(SexAreaOrifice.VAGINA)
							&& (Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.ANUS) || Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.ASS))) {
						return true;
					}
				}
			}
			
			// Restrict anal actions if reverse cowgirl is riding cock.
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.COWGIRL_REVERSE) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.COWGIRL_REVERSE_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(target.equals(cowgirl)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.ANUS) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.ASS))
							&& Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.VAGINA)) {
						return true;
					}
				}
			}
			
			return super.isActionBlocked(performer, target, action);
		}
	};
	
	public static final AbstractSexPosition SITTING = new AbstractSexPosition("Sitting Down",
			8,
			true,
			Util.mergeLists(
					SexActionPresets.positioningActionsNew,
					Util.newArrayListOfValues(ChairSex.class)),
			new ArrayList<>()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));

			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL, SexSlotSitting.SITTING_BETWEEN_LEGS));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' cannot be used while the slot"
												+ " '"+Util.capitaliseSentence(e.getValue().getDescription())+"' is already assigned to "+(UtilText.parse(e.getKey(), "[npc.name]"))+".");
							}
						}
					}
				}
			}
			
//			if(characterToTakeSlot.isTaur()
//					&& (slot==SexSlotSitting.SITTING
//							|| slot==SexSlotSitting.SITTING_TWO
//							|| slot==SexSlotSitting.SITTING_THREE
//							|| slot==SexSlotSitting.SITTING_FOUR)) {
//				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Due to the proportions of [npc.her] animalistic lower body, [npc.nameIsFull] unable to use the '"+Util.capitaliseSentence(slot.getDescription())+"' slot."));
//			}
			if(!characterToTakeSlot.isTaur()
					&& (slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Only characters with the lower body of a feral animal can use the '"+Util.capitaliseSentence(slot.getDescription())+"' slot."));
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitableSitting = false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotSitting.SITTING
						|| e.getValue()==SexSlotSitting.SITTING_TWO
						|| e.getValue()==SexSlotSitting.SITTING_THREE
						|| e.getValue()==SexSlotSitting.SITTING_FOUR) {
					suitableSitting = true;
				}
			}
			if(!suitableSitting) {
				return new Value<Boolean, String>(false, "At least one character needs to be sitting down for this position to work.");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> sittingNames = new ArrayList<>();
			List<String> allInLapNames = new ArrayList<>();
			List<String> inLapNames = new ArrayList<>();
			List<String> inLapNamesTaur = new ArrayList<>();
			List<String> allBetweenLegsNames = new ArrayList<>();
			List<String> betweenLegsNames = new ArrayList<>();
			List<String> betweenLegsNamesTaur = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> taurPresentingNames = new ArrayList<>();

			GameCharacter mainSitting = null;
			GameCharacter mainInLap = null;
			GameCharacter mainInLapTaur = null;
			GameCharacter mainBetweenLegs = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainTaurPresenting = null;
			
			boolean playerSitting = false;
			boolean playerBetweenLegs = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotSitting.SITTING
							|| e.getValue()==SexSlotSitting.SITTING_TWO
							|| e.getValue()==SexSlotSitting.SITTING_THREE
							|| e.getValue()==SexSlotSitting.SITTING_FOUR) {
						if(mainSitting==null) {
							mainSitting=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerSitting = true;
							sittingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							sittingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotSitting.SITTING_IN_LAP
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_TWO
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_THREE
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_FOUR) {
						if(mainInLap==null && !e.getKey().isTaur()) {
							mainInLap=e.getKey();
						}
						if(mainInLapTaur==null && e.getKey().isTaur()) {
							mainInLapTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							allInLapNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								inLapNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								inLapNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allInLapNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								inLapNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								inLapNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_TWO
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_THREE
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR) {
						if(mainBetweenLegs==null) {
							mainBetweenLegs=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerBetweenLegs = true;
							allBetweenLegsNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								betweenLegsNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								betweenLegsNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allBetweenLegsNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								betweenLegsNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								betweenLegsNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotSitting.PERFORMING_ORAL
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else {
						if(mainTaurPresenting==null) {
							mainTaurPresenting=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							taurPresentingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							taurPresentingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			
			if(sittingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(sittingNames, false))+" are sitting down next to one another, while "); 
				
			} else {
				sb.append(UtilText.parse(mainSitting, "[npc.Name] [npc.is] sitting down on a chair, while ")); 
			}
			
			
			// Sitting in lap:
			int sittingLapCount = allInLapNames.size();
			boolean sentenceContinuationFound = true;
			if(sittingLapCount>=2) {
				if(inLapNames.isEmpty()) {
					sb.append(Util.stringsToStringList(inLapNamesTaur, false)+ " have turned around and stepped backwards, before lowering their feral bodies down into "
					+(sittingNames.size()>1
							?(playerSitting?"your laps. ":"their laps. ")
							:UtilText.parse(mainSitting, "[npc.her] lap. ")));
					
				} else {
						sb.append(Util.stringsToStringList(allInLapNames, false)+" have stepped forwards so as to be sitting in "
									+(sittingNames.size()>1
											?(playerSitting?"your laps. ":"their laps. ")
											:UtilText.parse(mainSitting, "[npc.her] lap. ")));
						if(!inLapNamesTaur.isEmpty()) {
							sb.append(" In order to do this, "+Util.capitaliseSentence(Util.stringsToStringList(inLapNamesTaur, false))
								+" have turned around and stepped backwards, allowing them to plant their feral bodies down against "
								+(sittingNames.size()>1
										?(playerSitting?"your groins. ":"their groins. ")
										:UtilText.parse(mainSitting, "[npc.her] groin. ")));
						}
				}
				
			} else if(inLapNames.size()==1) {
				sb.append(Util.stringsToStringList(inLapNames, false)+UtilText.parse(mainInLap, " [npc.has] stepped forwards so as to be sitting in ")
						+(sittingNames.size()>1
								?(playerSitting?"your lap. ":"their lap. ")
								:UtilText.parse(mainSitting, "[npc.her] lap. ")));
				
			} else if(inLapNamesTaur.size()==1) {
				sb.append(Util.stringsToStringList(inLapNamesTaur, false)+UtilText.parse(mainInLapTaur, " [npc.has] turned around and stepped backwards, before lowering [npc.her] feral [npc.legRace]'s body down into ")
				+(sittingNames.size()>1
						?(playerSitting?"your lap. ":"their lap. ")
						:UtilText.parse(mainSitting, "[npc.her] lap. ")));
			} else {
				sentenceContinuationFound=false;
			}
			
			// Between legs:
			int betweenLegsCount = allBetweenLegsNames.size();
			if(betweenLegsCount>=2) {
				if(betweenLegsNames.isEmpty()) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNamesTaur, false)):Util.stringsToStringList(betweenLegsNamesTaur, false))
						+ " have stepped forwards over the top of "
					+(sittingNames.size()>1
							?(playerSitting ?"you" :"them")
							:UtilText.parse(mainSitting, "[npc.name]"))
					+", bringing the groins of "+(playerBetweenLegs?"your":"their")+" animalistic lower bodies right up against "
					+(sittingNames.size()>1
							?(playerSitting ?"yours. " :"theirs. ")
							:UtilText.parse(mainSitting, "[npc.hers]. ")));
					
				} else {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNames, false)):Util.stringsToStringList(betweenLegsNames, false))
							+" have stepped forwards so as to be standing in between "
								+(sittingNames.size()>1
										?(playerSitting?"your legs":"their legs")
										:UtilText.parse(mainSitting, "[npc.namePos] [npc.legs]")));
					if(!betweenLegsNamesTaur.isEmpty()) {
						sb.append(", while "+Util.stringsToStringList(betweenLegsNamesTaur, false)+ " "+(betweenLegsNamesTaur.size()==1?UtilText.parse(mainBetweenLegs, "[npc.has]"):"have")+" stepped forwards over the top of "
								+(sittingNames.size()>1
										?(playerSitting ?"you" :"them")
										:UtilText.parse(mainSitting, "[npc.name]"))
								+(betweenLegsNamesTaur.size()==1
									?UtilText.parse(mainBetweenLegs, ", bringing the groin of [npc.her] animalistic [npc.legRace]'s body right up against ")
									:", bringing the groins of "+(playerBetweenLegs?"your":"their")+" animalistic lower bodies right up against")
								+(sittingNames.size()>1
										?(playerSitting ?"yours. " :"theirs. ")
										:UtilText.parse(mainSitting, "[npc.hers]. ")));
					} else {
						sb.append(".");
					}
				}
				sentenceContinuationFound = true;
				
			} else if(betweenLegsNames.size()==1) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNames, false)):Util.stringsToStringList(betweenLegsNames, false))
						+UtilText.parse(mainBetweenLegs, " [npc.has] stepped forwards so as to be standing in between ")
						+(sittingNames.size()>1
								?(playerSitting?"your legs. ":"their legs. ")
								:UtilText.parse(mainSitting, "[npc.her] [npc.legs]. ")));
				sentenceContinuationFound = true;
				
			} else if(betweenLegsNamesTaur.size()==1) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNamesTaur, false)):Util.stringsToStringList(betweenLegsNamesTaur, false))
						+UtilText.parse(mainBetweenLegs, " [npc.has] stepped forwards over the top of ")
						+(sittingNames.size()>1
								?(playerSitting ?"you" :"them")
								:UtilText.parse(mainSitting, "[npc.name]"))
						+UtilText.parse(mainBetweenLegs, ", bringing the groin of [npc.her] animalistic [npc.legRace]'s body right up against ")
						+(sittingNames.size()>1
								?(playerSitting ?"yours. " :"theirs. ")
								:UtilText.parse(mainSitting, "[npc.hers]. ")));
				sentenceContinuationFound = true;
			}
			
			// Performing oral:
			if(performingOralNames.size()>=2) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false)):Util.stringsToStringList(performingOralNames, false))
							+" are positioned so as to be down between "
							+(sittingNames.size()>1
									?(playerSitting?"your legs":"their legs")+", ready to perform oral on "+(playerSitting?"you. ":"them. ")
									:UtilText.parse(mainSitting, "[npc.namePos] [npc.legs], ready to perform oral on [npc.herHim]. ")));
					sentenceContinuationFound = true;
				
			} else if(performingOralNames.size()==1) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false)):Util.stringsToStringList(performingOralNames, false))
							+UtilText.parse(mainPerformingOral," [npc.is] positioned so as to be right up between ")
					+(sittingNames.size()>1
							?(playerSitting?"your legs":"their legs")+", ready to perform oral on "+(playerSitting?"you. ":"them. ")
							:UtilText.parse(mainSitting, "[npc.namePos] [npc.legs], ready to perform oral on [npc.herHim]. ")));
					sentenceContinuationFound = true;
			}

			
			// Receiving oral:
			if(taurPresentingNames.size()>=2) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(taurPresentingNames, false)):Util.stringsToStringList(taurPresentingNames, false))
						+" have turned around and stepped back in order to present their animalistic genitals to "
						+(sittingNames.size()>1
								?(playerSitting?"the "+Util.intToString(sittingNames.size())+" of you.":"the "+Util.intToString(sittingNames.size())+" of them.")
								:UtilText.parse(mainSitting, "[npc.name].")));
			
				
			} else if(taurPresentingNames.size()==1) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(taurPresentingNames, false)):Util.stringsToStringList(taurPresentingNames, false))
							+UtilText.parse(mainTaurPresenting," [npc.has] turned around and stepped back in order to present [npc.her] feral rear end to ")
					+(sittingNames.size()>1
							?(playerSitting?"the "+Util.intToString(sittingNames.size())+" of you.":"the "+Util.intToString(sittingNames.size())+" of them.")
							:UtilText.parse(mainSitting, "[npc.name].")));
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
			
			// Commented-out the interactions within if statements in v0.4.2.2, as having them be unavailable without someone in that slot was preventing NPCs from moving into those slots during sex
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING_BETWEEN_LEGS));
					performerFree1 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_BETWEEN_LEGS));
				performerFree1 = false;
			}

			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_TWO));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_TWO)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
					performerFree2 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
				performerFree2 = false;
			}
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_THREE));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_THREE)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
					performerFree3 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
				performerFree3 = false;
			}
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_FOUR));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
					performerFree4 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
				performerFree4 = false;
			}
			
			// v0.4.2.2 added these from the commented-out ones above
			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_BETWEEN_LEGS));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_TWO));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_THREE));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_FOUR));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
			
			
			
			// Sitting characters can kiss/use appendages on ones sitting next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_FOUR));
			
			if(performerFree1) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING));
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_TWO));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_TWO));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_THREE));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree2) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_TWO));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.PERFORMING_ORAL_THREE));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree3) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_THREE));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING));
				}
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree4) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_FOUR));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING));
				}
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_TWO));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_THREE));
				}
			}

			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			// The character riding cock can use their body weight to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			// The character sitting getting fucked can use their legs, tail, or tentacles to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			
			return null;
		}
	};
	
	
	
	
	//--- Unique and one-off sex scenes ---//
	
	public static final AbstractSexPosition BREEDING_STALL = new AbstractSexPosition("Breeding Stall",
			2,
			true,
			null, Util.newArrayListOfValues()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(characterToTakeSlot.isTaur() && (slot==SexSlotBreedingStall.BREEDING_STALL_BACK)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Due to the proportions of [npc.her] animalistic lower body, [npc.nameIsFull] unable to lie down on [npc.her] back in order to get bred."));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			boolean front = true;
			GameCharacter lyingDown = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_FRONT);
			if(lyingDown==null) {
				front = false;
				lyingDown = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_BACK);
			}
			
			if(lyingDown.isPlayer()) {
				return "You're lying on your "+(front?"front":"back")+" on the padded bench, with your legs and lower abdomen projecting out of the hole in the wall, exposing your pussy to the breeders beyond."
						+ (Main.game.getPlayer().hasTail()
								?" As you get into position, someone on the other side of the wall fastens your "
									+(Main.game.getPlayer().getTailCount()>1?"[pc.tailCount] [pc.tails] to the wall by means of metal clamps":" [pc.tail] to the wall by means of a metal clamp")
									+ ", in order to prevent you from using"+(Main.game.getPlayer().getTailCount()>1?"them":"it")+" to block your [pc.pussy+]."
								:"");
			} else {
				GameCharacter character = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_FRONT);
				if(character!=null) {
					return "[npc.Name] is lying on [npc.her] "+(front?"front":"back")+" on the padded bench, with [npc.her] legs and lower abdomen projecting out of the hole in the wall. [npc.Her] pussy is completely exposed to you, ready for breeding."
							+ (character.hasTail()
									?" As [npc.she] gets into position, Epona steps forwards and fastens [npc.her] "
										+(character.getTailCount()>1?"[npc.tailCount] [npc.tails] to the wall by means of metal clamps":" [npc.tail] to the wall by means of a metal clamp")
										+ ", in order to prevent [npc.herHim] from using"+(character.getTailCount()>1?"them":"it")+" to block [npc.her] [npc.pussy+]."
									:"");
				} else {
					return "";
				}
			}
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.breedingStallFucking.getSexActionInteractions(SexSlotBreedingStall.BREEDING_STALL_FUCKING, SexSlotBreedingStall.BREEDING_STALL_FRONT));
			interactions.add(StandardSexActionInteractions.breedingStallFucking.getSexActionInteractions(SexSlotBreedingStall.BREEDING_STALL_FUCKING, SexSlotBreedingStall.BREEDING_STALL_BACK));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			boolean performerIsMother = Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_FRONT || Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_BACK;
			if((performerIsMother
					&& (action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.TAIL)
							|| ((action.getSexAreaInteractions().values().contains(SexAreaOrifice.VAGINA) || action.getSexAreaInteractions().values().contains(SexAreaPenetration.PENIS)) && action.getParticipantType()==SexParticipantType.SELF)))
				|| (Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_FUCKING
						&& action.getSexAreaInteractions().values().contains(SexAreaPenetration.TAIL)
						&& action.getParticipantType()!=SexParticipantType.SELF)) {
				return true;
			}
			
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaOrifice>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotBreedingStall.BREEDING_STALL_BACK
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotBreedingStall.BREEDING_STALL_FUCKING) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas));
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
				sb.append(" On the other side of the stall, [npc.name] [npc.is] leaning down to continue pleasuring [npc3.namePos] gloryhole with [npc.her] mouth.");
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
	
	

	public static List<AbstractSexPosition> allSexPositions;
	
	public static Map<AbstractSexPosition, String> sexPositionToIdMap = new HashMap<>();
	public static Map<String, AbstractSexPosition> idToSexPositionMap = new HashMap<>();
	
	public static AbstractSexPosition getSexPositionFromId(String id) {
		id = Util.getClosestStringMatch(id, idToSexPositionMap.keySet());
		
		return idToSexPositionMap.get(id);
	}
	
	public static String getIdFromSexPosition(AbstractSexPosition perk) {
		return sexPositionToIdMap.get(perk);
	}

	static {
		allSexPositions = new ArrayList<>();
		
		// Hard-coded status effects (all those up above):
		
		Field[] fields = SexPosition.class.getFields();
		
		for(Field f : fields){
			if (AbstractSexPosition.class.isAssignableFrom(f.getType())) {
				
				AbstractSexPosition sexPosition;
				
				try {
					sexPosition = ((AbstractSexPosition) f.get(null));

					sexPositionToIdMap.put(sexPosition, f.getName());
					idToSexPositionMap.put(f.getName(), sexPosition);
					allSexPositions.add(sexPosition);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractSexPosition> getAllSexPositions() {
		return allSexPositions;
	}
}
