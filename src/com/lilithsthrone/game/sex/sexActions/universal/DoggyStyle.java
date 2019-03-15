package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.11
 * @author Innoxia
 */
public class DoggyStyle {
	
	public static final SexAction SLAP_ASS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterPerformingAction())
					&& (Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_FOURTH)
					&& (Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_BEHIND
						|| Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_BEHIND_SECOND);
		}
		
		@Override
		public String getActionTitle() {
			return "Slap ass";
		}

		@Override
		public String getActionDescription() {
			return "Start slapping [npc2.namePos] ass.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Sex.getAllContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] down and roughly [npc.verb(grope)] [npc2.her] [npc2.ass+],"
								+ " before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
					default:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy+], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
											+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
				}
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place,"
								+ " before starting make good on [npc.her] threat by aggressively slapping [npc2.her] exposed ass cheeks.";
						break;
					default:
						tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+] in one [npc.hand],"
											+ " roughly yanking [npc2.her] [npc2.ass+] up high in the air before starting to aggressively slap [npc2.her] exposed cheeks.";
						break;
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] slapping [npc2.her] [npc2.ass+] in time with [npc.her] powerful thrusts into [npc2.her] [npc2.pussy+].",
							
							"Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(use)] one [npc.hand] to hold [npc2.herHim] still,"
								+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] exposed ass cheeks.",
							
							"While [npc.name] [npc.verb(continue)] pounding away at [npc2.namePos] [npc2.pussy+], [npc.she] [npc.verb(reach)] down and [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " growling in glee as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(squeal)] under [npc.her] stinging blows.");
				
			} else if (Sex.getAllContactingSexAreas(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] down and roughly [npc.verb(grope)] [npc2.her] [npc2.ass+],"
								+ " before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
					default:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole+], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
											+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
				}
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place,"
								+ " before starting make good on [npc.her] threat by aggressively slapping [npc2.her] exposed ass cheeks.";
						break;
					default:
						tailSpecial2 = "Still ploughing away at [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+] in one [npc.hand],"
											+ " roughly yanking [npc2.her] [npc2.ass+] up high in the air before starting to aggressively slap [npc2.her] exposed cheeks.";
						break;
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] slapping [npc2.her] [npc2.ass+] in time with [npc.her] powerful thrusts into [npc2.her] [npc2.asshole+].",
							
							"Hilting [npc.her] [npc.cock+] deep inside [npc2.namePos] [npc2.asshole], [npc.name] [npc.verb(use)] one [npc.hand] to hold [npc2.herHim] still,"
								+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] exposed ass cheeks.",
							
							"While [npc.name] [npc.verb(continue)] pounding away at [npc2.namePos] [npc2.asshole+], [npc.she] [npc.verb(reach)] down and [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " growling in glee as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(squeal)] under [npc.her] stinging blows.");
			
			} else {
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial1 = "Growling down into [npc2.namePos] [npc2.ear+], [npc.name] [npc.verb(reach)] down and [npc.verb(grab)] [npc2.her] waist, using one hand to hold [npc2.herHim] still,"
											+ " while using [npc.her] other to deliver a series of stinging slaps to [npc2.her] [npc2.ass+].";
						break;
					default:
						tailSpecial1 = "Growling down into [npc2.namePos] [npc2.ear], [npc.name] roughly [npc.verb(grab)] the base of [npc2.her] [npc2.tail+] and [npc.verb(yank)] upwards,"
									+ " raising [npc2.her] [npc2.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc2.her] exposed cheeks.";
						break;
				}
				switch(Sex.getCharacterTargetedForSexAction(this).getTailType()) {
					case NONE:
						tailSpecial2 = "[npc.Name] [npc.verb(reach)] down and [npc.verb(grab)] [npc2.namePos] waist with one hand,"
								+ " holding [npc2.herHim] firmly in [npc.her] grip as [npc.she] [npc.verb(start)] to aggressively slap [npc2.her] exposed cheeks.";
						break;
					default:
						tailSpecial2 = "[npc.Name] [npc.verb(reach)] down and [npc.verb(grab)] the base of [npc2.namePos] [npc2.tail+], causing [npc2.herHim] to let out a surprised yelp as [npc.she] roughly [npc.verb(yank)] upwards,"
											+ " forcing [npc2.herHim] to push [npc2.her] [npc2.ass+] up high in the air as [npc.name] [npc.verb(start)] to aggressively slap [npc2.her] exposed cheeks.";
						break;
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] roughly slapping [npc2.her] [npc2.ass+],"
									+ " and [npc.name] [npc.verb(find)] [npc.herself] grinning in glee as [npc.she] [npc.verb(watch)] [npc2.herHim] squirm and cry out beneath [npc.her] stinging blows.",
							
							"[npc2.Name] [npc2.verb(let)] out a startled wail as [npc.name] [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+],"
									+ " and [npc.name] quickly [npc.verb(find)] [npc.herself] grinning in glee as [npc.she] [npc.verb(watch)] [npc2.herHim] squirm and wail beneath [npc.her] relentless blows.",
							
							"[npc.Name] [npc.verb(growl)] down that [npc.sheIs] going to put [npc2.name] in [npc2.her] place, before starting to aggressively slap [npc2.her] [npc2.ass+],"
									+ " smirking down at [npc2.her] submissive form as [npc2.she] squeals and cries out beneath [npc.her] relentless blows.");
			}
		}
		
	};
	
	public static final SexAction PULL_HAIR = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Pull hair";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] hair and pull [npc2.her] head back.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;
			if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_BEHIND) {
				if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS) {
					suitableSlot = Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_SD_HUMPING)==null;
					
				} else if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND) {
					suitableSlot = Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_SD_HUMPING_SECOND)==null
							&& (Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_BEHIND_SECOND)==null || Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD)!=null);
					
				} else if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_FOURTH) {
					suitableSlot = Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_BEHIND_SECOND)==null;
				}
				
			} else if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_BEHIND_SECOND) {
				if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND) {
					suitableSlot = Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_SD_HUMPING_SECOND)==null
							&& Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD)==null;
					
				} else if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_FOURTH) {
					suitableSlot = true;;
				}
				
			} else if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_SD_HUMPING) {
				suitableSlot = Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS;
				
			}else if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_SD_HUMPING_SECOND) {
				suitableSlot = Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND;
			}
			
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Sex.getPenetrationTypeFreeCount(Sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=1 
					&& Sex.getCharacterTargetedForSexAction(this).getHairRawLengthValue()>=HairLength.TWO_SHORT.getMinimumValue()
					&& suitableSlot;
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Sex.getOngoingActionsMap(Sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Sex.getOngoingActionsMap(Sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.hair], and, while continuing to rhythmically slide [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] slowly [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.hair] in one [npc.hand], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while burying [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Sex.getCharacterPerformingAction().isFeminine()?" softly giggling":" smirking")+" to [npc.herself] as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to slowly sink [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.hair],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.hair], before slowly pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.hair] in one [npc.hand], [npc.name] "+(Sex.getCharacterPerformingAction().isFeminine()?" softly [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" to [npc.herself] as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.hair],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] a handful of [npc2.namePos] [npc2.hair], and, while continuing to forcefully slam [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] sharply [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing a fistful of [npc2.namePos] [npc2.hair], [npc.name] simultaneously [npc.verb(yank)] [npc2.her] head back while slamming [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Sex.getCharacterPerformingAction().isFeminine()?" maniacally giggling":" deeply grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to roughly slam [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards and [npc.verb(grab)] a fistful of [npc2.her] [npc2.hair],"
										+ " before violently yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] a handful of [npc2.namePos] [npc2.hair], before sharply yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing a fistful of [npc2.namePos] [npc2.hair], [npc.name] "+(Sex.getCharacterPerformingAction().isFeminine()?" maniacally [npc.verb(giggle)]":" deeply [npc.verb(grunt)]")
									+" as [npc.she] violently [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Groping and pawing [npc.her] way up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards and [npc.verb(grab)] a fistful of [npc2.her] [npc2.hair],"
										+ " before roughly tugging [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.hair], and, while continuing to rhythmically pump [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] steadily [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.hair] in one [npc.hand], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while thrusting [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Sex.getCharacterPerformingAction().isFeminine()?" happily giggling":" grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to energetically thrust [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards and [npc.verb(take)] a firm hold of [npc2.her] [npc2.hair],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.hair], before steadily pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.hair] in one [npc.hand], [npc.name] "+(Sex.getCharacterPerformingAction().isFeminine()?" happily [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" in pleasure as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards and [npc.verb(take)] a firm hold of [npc2.her] [npc2.hair],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
			}
		}
		
	};
	
	public static final SexAction PULL_EARS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Pull ears";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.ears+] and pull [npc2.her] head back.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;
			if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_BEHIND) {
				if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS) {
					suitableSlot = Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_SD_HUMPING)==null;
					
				} else if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND) {
					suitableSlot = Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_SD_HUMPING_SECOND)==null
							&& (Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_BEHIND_SECOND)==null || Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD)!=null);
					
				} else if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_FOURTH) {
					suitableSlot = Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_BEHIND_SECOND)==null;
				}
				
			} else if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_BEHIND_SECOND) {
				if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND) {
					suitableSlot = Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_SD_HUMPING_SECOND)==null
							&& Sex.getCharacterInPosition(SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD)==null;
					
				} else if(Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD
						|| Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_FOURTH) {
					suitableSlot = true;;
				}
				
			} else if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_SD_HUMPING) {
				suitableSlot = Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS;
				
			}else if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexSlotBipeds.DOGGY_SD_HUMPING_SECOND) {
				suitableSlot = Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this))==SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND;
			}
			
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Sex.getPenetrationTypeFreeCount(Sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& Sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex()
					&& suitableSlot;
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Sex.getOngoingActionsMap(Sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Sex.getOngoingActionsMap(Sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], and, while continuing to rhythmically slide [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] slowly [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while burying [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Sex.getCharacterPerformingAction().isFeminine()?" softly giggling":" smirking")+" to [npc.herself] as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to slowly sink [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.ears+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], before slowly pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a gentle hold of [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] "+(Sex.getCharacterPerformingAction().isFeminine()?" softly [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" to [npc.herself] as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a gentle hold of [npc2.her] [npc2.ears+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.ears+], and, while continuing to forcefully slam [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] sharply [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] simultaneously [npc.verb(yank)] [npc2.her] head back while slamming [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Sex.getCharacterPerformingAction().isFeminine()?" maniacally giggling":" deeply grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to roughly slam [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(grab)] [npc2.her] [npc2.ears+],"
										+ " before violently yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], before sharply yanking [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Grabbing [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] "+(Sex.getCharacterPerformingAction().isFeminine()?" maniacally [npc.verb(giggle)]":" deeply [npc.verb(grunt)]")
									+" as [npc.she] violently [npc.verb(yank)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Groping and pawing [npc.her] way up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(grab)] [npc2.her] [npc2.ears+],"
										+ " before roughly tugging [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], and, while continuing to rhythmically pump [npc.her] [npc.cock] in and out of [npc2.her] [npc2."+tag+"+],"
										+ " [npc.she] steadily [npc.verb(pull)] back, causing [npc2.name] to lift [npc2.her] head and let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] simultaneously [npc.verb(pull)] [npc2.her] head back while thrusting [npc.her] [npc.cock+] deep into [npc2.her] [npc2."+tag+"],"
										+(Sex.getCharacterPerformingAction().isFeminine()?" happily giggling":" grunting")+" in pleasure as [npc2.name] [npc2.verb(let)] out [npc2.moan+].",
								"Continuing to energetically thrust [npc.her] [npc.cock+] in and out of [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a firm hold of [npc2.her] [npc2.ears+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Reaching forwards, [npc.name] firmly [npc.verb(take)] hold of [npc2.namePos] [npc2.ears+] in each [npc.hand], before steadily pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].",
								"Taking a firm hold of [npc2.namePos] [npc2.ears+] with both [npc.hands], [npc.name] "+(Sex.getCharacterPerformingAction().isFeminine()?" happily [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" in pleasure as [npc.she] [npc.verb(pull)] [npc2.her] head back, causing [npc2.herHim] to let out [npc2.moan+].",
								"Tracing [npc.her] [npc.fingers+] up the length of [npc2.namePos] back, [npc.name] [npc.verb(reach)] forwards with two [npc.hands] and [npc.verb(take)] a firm hold of [npc2.her] [npc2.ears+],"
										+ " before pulling [npc2.her] head back and causing [npc2.herHim] to let out [npc2.a_moan+].");
					}
			}
		}
		
	};
	
	
	// Player's orgasms:
	
	public static final SexAction PLAYER_DOGGY_OVER_BACK_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, null)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Cum on back";
		}

		@Override
		public String getActionDescription() {
			return "Cum over [npc2.namePos] ass and back.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo();
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("As you feel yourself reaching your climax, you decide that you're going to shoot your sticky seed all over [npc.namePos] ass."
						+ " Grabbing [npc.her] hips, you slide backwards, slipping your cock free from [npc.her] well-used hole, and brace for your orgasm. ");
				
			} else {
				UtilText.nodeContentSB.append("As you feel yourself reaching your climax, you decide that you're going to shoot your sticky seed all over [npc.namePos] ass."
						+ " Grabbing [npc.her] hips, you shuffle forwards, lining your [pc.cock+] up to [npc.her] [npc.ass+], and brace for your orgasm. ");
			}
			
			String flaredSpecial = "", knottedSpecial = "", barbedSpecial = "";
			
			if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				knottedSpecial = " Your [pc.cock+] suddenly starts violently throbbing, and you know that you're about to cum."
										+ " Grabbing your [pc.cock] in one [pc.hand], you point it at [npc.namePos] ass, furiously masturbating as your thick knot swells up.";
			}
			if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.BARBED)) {
				barbedSpecial = " Your barbed [pc.cock] suddenly starts violently throbbing, and you know that you're about to cum."
						+ " Grabbing your [pc.cock] in one hand, you point it at [npc.namePos] ass, furiously masturbating as your hand slides up and down over your sensitive little barbs.";
			}
			if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.FLARED)) {
				flaredSpecial = " Your flared [pc.cock] suddenly starts violently throbbing, and you know that you're about to cum."
						+ " Grabbing your [pc.cock] in one [pc.hand], you point it at [npc.namePos] ass, furiously masturbating as your flared head swells up.";
			}
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					flaredSpecial,
					knottedSpecial,
					barbedSpecial,
					"Your [pc.cock+] suddenly starts violently throbbing, and you know that you're about to cum."
							+ " Grabbing your [pc.cock] in one hand, you point it at [npc.namePos] ass, furiously masturbating as you let out [pc.a_moan+]."));
			
			if(Main.game.getPlayer().isWearingCondom()) {
				UtilText.nodeContentSB.append(" As your [pc.balls+] tense up");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", you feel a small trickle of [pc.cum+] squirting");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", you feel a small amount of [pc.cum+] squirting");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] squirting");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] shooting");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					default:
						break;
				}
				UtilText.nodeContentSB.append(" out into the condom that you're wearing.");
				
			} else {
				UtilText.nodeContentSB.append(" You feel your [pc.balls+] tense up as your orgasm washes over you.");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(" Unfortunately, you aren't able to produce even one drop of cum, somewhat diminishing the pleasure of your climax.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [pc.cum+] squirts onto [npc.namePos] ass, and you groan in satisfaction as you see your [pc.cum] sliding down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small amount of [pc.cum+] squirts onto [npc.namePos] ass, and you groan in satisfaction as you see your sticky seed sliding down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(" Your [pc.cum+] shoots out as your cock throbs, and you groan in satisfaction as you see your sticky seed splatter over [npc.namePos] ass and lower back, before dripping down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(" Your [pc.cum+] shoots out as your cock throbs, and you groan in satisfaction as you see your sticky seed splatter over [npc.namePos] ass and lower back, before dripping down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(" Your large load pours out as your cock throbs, and you groan in satisfaction as you see your [pc.cum+] splatter all over [npc.namePos] ass and lower back, before sliding down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(" Your huge load pours out as your cock throbs, and you groan in satisfaction as you see your [pc.cum+] completely cover [npc.namePos] ass and back, before sliding down all over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(" Your enormous load pours out as your cock throbs, and you groan in satisfaction as you see your [pc.cum+] completely drenching [npc.namePos] ass and back,"
								+ " before pouring down all over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+" to form a puddle around [npc.her] knees.");
						break;
					default:
						break;
				}
			}

			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/>"
						+ "As you grasp your softening member in your [pc.hand], you feel a second orgasm building deep in your groin."
						+ " Grabbing [npc.namePos] [npc.hips] to brace yourself, you go weak at the knees and clench your thighs together as your [pc.pussy+] shudders and quivers."
						+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as your feminine sex joins in on the fun."
						+ " Panting heavily, you soon come down from your second climax and start to recover.");
			} else {
				UtilText.nodeContentSB.append("<br/>"
						+ "You pant heavily as you pull your softening member out of [npc.namePos] used hole, before giving [npc.her] [npc.ass] a slap.");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.ANUS);
			Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Sex.getCharacterPerformingAction()) && cumTarget.equals(Sex.getTargetedPartner(cumProvider))) {
				return Util.newArrayListOfValues(
						CoverableArea.BACK);
			}
			return null;
		}
	};
	
	public static final SexAction PLAYER_DOGGY_DOMINANT_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Sex.isDom(Main.game.getPlayer())
					&& Main.game.getPlayer().hasPenisIgnoreDildo();
		}
		
		@Override
		public String getActionTitle() {
			return "Ass to mouth";
		}

		@Override
		public String getActionDescription() {
			if(Sex.getCharacterPerformingAction().isWearingCondom()) {
				return "Roughly fuck [npc2.name] into the floor before filling your condom with your cum. Then slide it off and use [npc2.her] mouth to clean yourself off.";
			}
			return "Roughly fuck [npc2.name] into the floor before filling [npc2.her] [npc2.ass] with your cum. Then use [npc2.her] mouth to clean yourself off.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("As you feel [npc.namePos] [npc.asshole+] squeezing down around your [pc.cock+], you decide that you're going to show [npc.herHim] how an alpha treats their submissive little beta."
					+ " Letting out [pc.a_moan+], you slam your [pc.cock] deep into [npc.her] ass, grinning devilishly as [npc.she] lets out [npc.a_moan+]."
					+ "<br/><br/>"
					+ "Reaching down, you grab the [npc.race]'s shoulders, pushing your weight down onto [npc.her] back as you roughly mount [npc.herHim], which causes [npc.her] torso to collapse down to the floor."
					+ " Bending down, with your [pc.cock] still hilted in [npc.her] [npc.asshole], you growl menacingly in [npc.her] [npc.ear], "
							+ "[pc.speech(You little bitch! All you're good for is taking my load!)]"
					+"<br/><br/>"
					+ "[npc.Name] lets out another [npc.moan], which is enough to send you over the edge."
					+ " As you grind [npc.her] [npc.face+] into the floor, you reach your climax, and as your [pc.balls+] tense up");

			switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", you realise that you aren't able to produce even one drop of cum, somewhat lessening the impact of your dominant display.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", a small trickle of [pc.cum+] squirts");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", a small amount of [pc.cum+] squirts");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", your [pc.cum+] shoots");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", your [pc.cum+] shoots");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", your load pours out");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", your huge load pours out");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", your enormous load pours out");
					break;
				default:
					break;
			}
			if(Main.game.getPlayer().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				if(Sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" into the condom that you're wearing, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
				} else {
					UtilText.nodeContentSB.append(" deep into [npc.namePos] ass, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
				}
			}

			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you slide your softening member out from [npc.namePos] well-used ass, you feel a second orgasm building deep in your groin."
						+ " Grabbing [npc.her] hips to brace yourself, you go weak at the knees and clench your thighs together as your [pc.pussy+] shudders and quivers."
						+ " A mind-splitting orgasm washes through you, and you [pc.moanVerb] in delight as your feminine sex joins in on the fun."
						+ " Panting heavily, you suddenly remember what you had planned, and shuffle around to where [npc.namePos] face is still collapsed down against the floor.");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you slide your softening member out from [npc.namePos] well-used ass, you look down, grinning, at the mess you've made of [npc.herHim]."
						+ " Panting heavily, you suddenly remember what you had planned, and shuffle around to where [npc.namePos] face is still collapsed down against the floor.");
			}

			UtilText.nodeContentSB.append("<br/><br/>");
			if(Sex.getCharacterPerformingAction().isWearingCondom()) {
				UtilText.nodeContentSB.append("Reaching down, you grab a fistful of [npc.namePos] [npc.hair+],"
						+ " and before [npc.she] has a chance to react, you slide your condom off and roughly shove [npc.her] [npc.face+] down onto your [pc.cock+].");
			} else {
				UtilText.nodeContentSB.append("Reaching down, you grab a fistful of [npc.namePos] [npc.hair+], and before [npc.she] has a chance to react, you shove [npc.her] [npc.face+] down onto your [pc.cock+].");
			}
			UtilText.nodeContentSB.append(
					" [npc.She] [npc.moansVerb] and squirms as you give [npc.herHim] a taste of [npc.her] ass, but you hold [npc.herHim] tightly in position, [pc.moaning] softly as [npc.her] frantic [npc.tongue] cleans you off."
					+ "<br/>"
					+ "After a minute of using the unfortunate [npc.race] in this manner, you finally release [npc.herHim], and, with a deep gasp, [npc.she] collapses to the floor, completely exhausted from your dominant treatment.");

			return UtilText.nodeContentSB.toString();
		}
		

		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.game.getPlayer()) && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
			} else {
				return null;
			}
		}

		@Override
		public void applyEffects() {
			Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.ANUS);
		}
		
	};
	
	public static final SexAction PLAYER_DOGGY_DOMINANT_ORGASM_PUSSY = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Sex.isDom(Main.game.getPlayer())
					&& Main.game.getPlayer().hasPenisIgnoreDildo();
		}
		
		@Override
		public String getActionTitle() {
			return "Pussy to mouth";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.name] into the floor before filling [npc2.her] [npc2.pussy] with your cum. Then use [npc2.her] mouth to clean yourself off.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("As you feel [npc.namePos] [npc.pussy+] squeezing down around your [pc.cock+], you decide that you're going to show [npc.herHim] how an alpha treats their submissive little beta."
					+ " Letting out [pc.a_moan+], you slam your [pc.cock] deep into [npc.her] [npc.pussy], grinning devilishly as [npc.she] lets out [npc.a_moan+]."
					+ "<br/><br/>"
					+ "Reaching down, you grab the [npc.race]'s shoulders, pushing your weight down onto [npc.her] back as you roughly mount [npc.herHim], which causes [npc.her] torso to collapse down to the floor."
					+ " Bending down, with your [pc.cock] still hilted in [npc.her] [npc.pussy+], you growl menacingly in [npc.her] [npc.ear], "
							+ "[pc.speech(You little bitch! All you're good for is taking my load!)]"
					+"<br/><br/>"
					+ "[npc.Name] lets out another [npc.moan], which is enough to send you over the edge."
					+ " As you grind [npc.her] [npc.face+] into the floor, you reach your climax, and as your [pc.balls+] tense up");

			switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", you realise that you aren't able to produce even one drop of cum, somewhat lessening the impact of your dominant display.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", a small trickle of [pc.cum+] squirts deep into [npc.namePos] [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", a small amount of [pc.cum+] squirts deep into [npc.namePos] [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", your [pc.cum+] shoots deep into [npc.namePos] [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", your [pc.cum+] shoots deep into [npc.namePos] [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", your load pours out deep into [npc.namePos] [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", your huge load pours out deep into [npc.namePos] [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", your enormous load pours out deep into [npc.namePos] [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				default:
					break;
			}

			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you slide your softening member out from [npc.namePos] well-used [npc.pussy], you feel a second orgasm building deep in your groin."
						+ " Grabbing [npc.her] hips to brace yourself, you go weak at the knees and clench your thighs together as your [pc.pussy+] shudders and quivers."
						+ " A mind-splitting orgasm washes through you, and you [pc.moanVerb] in delight as your feminine sex joins in on the fun."
						+ " Panting heavily, you suddenly remember what you had planned, and shuffle around to where [npc.namePos] face is still collapsed down against the floor.");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you slide your softening member out from [npc.namePos] well-used [npc.pussy], you look down, grinning, at the mess you've made of [npc.herHim]."
						+ " Panting heavily, you suddenly remember what you had planned, and shuffle around to where [npc.namePos] face is still collapsed down against the floor.");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "Reaching down, you grab a fistful of [npc.namePos] [npc.hair+], and before [npc.she] has a chance to react, you shove [npc.her] [npc.face+] down onto your [pc.cock+]."
					+ " [npc.She] [npc.moansVerb] and squirms as you give [npc.herHim] a taste of [npc.her] [npc.pussy], but you hold [npc.herHim] tightly in position, [pc.moaning] softly as [npc.her] frantic [npc.tongue] cleans you off."
					+ "<br/>"
					+ "After a minute of using the unfortunate [npc.race] in this manner, you finally release [npc.herHim], and, with a deep gasp, [npc.she] collapses to the floor, completely exhausted from your dominant treatment.");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.game.getPlayer()) && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
			} else {
				return null;
			}
		}

		@Override
		public void applyEffects() {
			Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.VAGINA);
		}
		
	};

	
	public static final SexAction LOOK_BACK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			SexSlot slot = Sex.getSexPositionSlot(Sex.getCharacterPerformingAction());
			SexSlot targetedSlot = Sex.getSexPositionSlot(Sex.getCharacterTargetedForSexAction(this));
			return Sex.getSexPace(Sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& (slot==SexSlotBipeds.DOGGY_ON_ALL_FOURS || slot==SexSlotBipeds.DOGGY_ON_ALL_FOURS_SECOND || slot==SexSlotBipeds.DOGGY_ON_ALL_FOURS_THIRD || slot==SexSlotBipeds.DOGGY_ON_ALL_FOURS_FOURTH)
					&& (targetedSlot==SexSlotBipeds.DOGGY_BEHIND || targetedSlot==SexSlotBipeds.DOGGY_BEHIND_SECOND || targetedSlot==SexSlotBipeds.DOGGY_BEHIND_ORAL || targetedSlot==SexSlotBipeds.DOGGY_BEHIND_ORAL_SECOND);
		}
		
		@Override
		public String getActionTitle() {
			return "Seductive look";
		}
		@Override
		public String getActionDescription() {
			return "Turn your head back and give [npc2.name] a seductive look.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Turning [npc.her] head back, [npc.name] [npc.verb(look)] up at [npc2.name] and [npc.verb(bite)] [npc.her] [npc.lip], putting on [npc.her] most seductive look as [npc.she] [npc.verb(entice)] [npc2.herHim] to use [npc.herHim].",
					"Looking back at [npc2.name] as [npc2.she] [npc2.verb(tower)] over [npc.her] [npc.ass+], [npc.name] [npc.verb(put)] on a seductive look,"
							+ " [npc.moaning] in delight as [npc.she] [npc.verb(entice)] [npc2.herHim] into using [npc.her] body.",
					"[npc.Name] [npc.verb(turn)] [npc.her] head and [npc.verb(bite)] [npc.her] [npc.lip] at [npc2.name], doing [npc.her] best to look as seductive as possible.",
					"Looking back, [npc.name] [npc.verb(put)] on a seductive look for [npc2.name], feeling extremely pleased with [npc.herself] as [npc.she] [npc.verb(see)] [npc2.herHim] gazing hungrily down at [npc.herHim] in return.");
		}
	};

}
