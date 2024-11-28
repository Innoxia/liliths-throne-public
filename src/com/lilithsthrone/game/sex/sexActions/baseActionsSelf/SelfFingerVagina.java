package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerVagina {
	
	public static final SexAction FINGER_INSEMINATION_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Finger self-insemination";
		}
		@Override
		public String getActionDescription() {
			return "Scoop up the cum on your body and push it deep into your cunt.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTotalAmountCummedOn(Main.sex.getCharacterPerformingAction())>0
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()));
		}
		private String getRandomCharacterCumDescription(boolean withName) {
			Set<GameCharacter> charactersCummedOnPerformer = Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction()).keySet();
			
			GameCharacter character = Util.randomItemFrom(charactersCummedOnPerformer);
			
			if(character==Main.sex.getCharacterPerformingAction()) {
				return UtilText.parse(character, (withName?"[npc.her] own ":"")+"[npc.cum+]");
			} else {
				return UtilText.parse(character, (withName?"[npc.namePos] ":"")+"[npc.cum+]");
			}
		}
		@Override
		public String getDescription() {
			return "As [npc.name] [npc.verb(feel)] "+getRandomCharacterCumDescription(true)+" sliding down over [npc.her] [npc.skin], a fun idea suddenly springs into [npc.her] mind."
						+ " Pulling [npc.her] [npc.fingers] out of [npc.her] [npc.pussy+], [npc.she] [npc.verb(reach)] up to the "+getRandomCharacterCumDescription(false)+" that's splattered over [npc.her] body, before scooping up some of the fresh seed."
						+ " Satisfied that [npc.sheHas] collected enough, [npc.she] [npc.verb(push)] [npc.her] [npc.fingers] back into [npc.her] hungry pussy."
						+ "<br/>"
						+ "[npc.Name] [npc.verb(grin)] as [npc.she] [npc.verb(feel)] the "+getRandomCharacterCumDescription(false)+" on [npc.her] fingers being pushed deep into [npc.her] cunt,"
							+ " and [npc.moansVerb+] as [npc.she] [npc.verb(start)] fingering [npc.herself] once again, using the slick cum as lubricant."
						+ " Grinding [npc.her] hips against [npc.her] [npc.hand], [npc.name] [npc.verb(let)] out a desperate, shuddering [npc.moan] at the feeling of being inseminated.";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			Map<GameCharacter, Integer> cumProvidersToTotalCum = new HashMap<>();
			for(Entry<GameCharacter, Map<InventorySlot, Integer>> cumDetails : new HashMap<>(Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction())).entrySet()) {
				for(Entry<InventorySlot, Integer> areaDetails : cumDetails.getValue().entrySet()) {
					int amountOfCumUsed = Math.min(5, areaDetails.getValue());
					cumProvidersToTotalCum.putIfAbsent(cumDetails.getKey(), 0);
					cumProvidersToTotalCum.put(cumDetails.getKey(), cumProvidersToTotalCum.get(cumDetails.getKey())+amountOfCumUsed);
					Main.sex.incrementAmountCummedOn(cumDetails.getKey(), Main.sex.getCharacterPerformingAction(), areaDetails.getKey(), -amountOfCumUsed); // Remove the cum
				}
			}
			for(Entry<GameCharacter, Integer> e : cumProvidersToTotalCum.entrySet()) {
				sb.append(Main.sex.getCharacterPerformingAction().ingestFluid(e.getKey(), e.getKey().getCum(), SexAreaOrifice.VAGINA, e.getValue()));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY, Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
		}
	};
	public static final SexAction FINGER_INSEMINATION_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Finger self-insemination";
		}
		@Override
		public String getActionDescription() {
			return "Scoop up the cum on your body and push it deep into your cunt.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTotalAmountCummedOn(Main.sex.getCharacterPerformingAction())>0
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()));
		}
		private String getRandomCharacterCumDescription(boolean withName) {
			Set<GameCharacter> charactersCummedOnPerformer = Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction()).keySet();
			
			GameCharacter character = Util.randomItemFrom(charactersCummedOnPerformer);
			
			if(character==Main.sex.getCharacterPerformingAction()) {
				return UtilText.parse(character, (withName?"[npc.her] own ":"")+"[npc.cum+]");
			} else {
				return UtilText.parse(character, (withName?"[npc.namePos] ":"")+"[npc.cum+]");
			}
		}
		@Override
		public String getDescription() {
			return "As [npc.name] [npc.verb(feel)] "+getRandomCharacterCumDescription(true)+" sliding down over [npc.her] [npc.skin], a fun idea suddenly springs into [npc.her] mind."
					+ " Lifting [npc.her] [npc.fingers] to the "+getRandomCharacterCumDescription(false)+" that's splattered over [npc.her] body, [npc.she] [npc.verb(scoop)] up some of the fresh seed."
					+ " Satisfied that [npc.sheHas] collected enough, [npc.she] [npc.verb(push)] [npc.her] [npc.fingers] into [npc.her] hungry pussy."
					+ "<br/>"
					+ "[npc.Name] [npc.verb(grin)] as [npc.she] [npc.verb(feel)] the "+getRandomCharacterCumDescription(false)+" on [npc.her] fingers being pushed deep into [npc.her] cunt,"
						+ " and [npc.moansVerb+] as [npc.she] [npc.verb(start)] fingering [npc.herself], using the slick cum as lubricant."
					+ " Grinding [npc.her] hips against [npc.her] [npc.hand], [npc.name] [npc.verb(let)] out a desperate, shuddering [npc.moan] at the feeling of being inseminated.";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			Map<GameCharacter, Integer> cumProvidersToTotalCum = new HashMap<>();
			for(Entry<GameCharacter, Map<InventorySlot, Integer>> cumDetails : new HashMap<>(Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction())).entrySet()) {
				for(Entry<InventorySlot, Integer> areaDetails : cumDetails.getValue().entrySet()) {
					int amountOfCumUsed = Math.min(5, areaDetails.getValue());
					cumProvidersToTotalCum.putIfAbsent(cumDetails.getKey(), 0);
					cumProvidersToTotalCum.put(cumDetails.getKey(), cumProvidersToTotalCum.get(cumDetails.getKey())+amountOfCumUsed);
					Main.sex.incrementAmountCummedOn(cumDetails.getKey(), Main.sex.getCharacterPerformingAction(), areaDetails.getKey(), -amountOfCumUsed); // Remove the cum
				}
			}
			for(Entry<GameCharacter, Integer> e : cumProvidersToTotalCum.entrySet()) {
				sb.append(Main.sex.getCharacterPerformingAction().ingestFluid(e.getKey(), e.getKey().getCum(), SexAreaOrifice.VAGINA, e.getValue()));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY, Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
		}
	};
	
	public static final SexAction SELF_FINGER_VAGINA_SPREAD_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Spread pussy";
		}

		@Override
		public String getActionDescription() {
			return "Use your [npc.fingers] to spread your pussy.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				return (UtilText.returnStringAtRandom(
						"Reaching back between [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] over the entrance to [npc.her] [npc.pussy+],"
								+ " before letting out [npc.a_moan+] as [npc.she] [npc.verb(use)] [npc.her] digits to spread out [npc.her] labia.",
						"[npc.Name] [npc.verb(probe)] [npc.her] [npc.fingers] back between [npc.her] [npc.legs], [npc.moaning] softly as [npc.she] [npc.verb(use)] two of [npc.her] digits to spread out [npc.her] [npc.pussy+].",
						"Sliding [npc.her] fingertips over [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(shake)] [npc.her] ass a little, before using [npc.her] digits to spread out [npc.her] pussy lips.",
						"[npc.Name] eagerly [npc.verb(slide)] [npc.her] [npc.fingers] over [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] digits to part [npc.her] soft folds."));
			} else {
				return (UtilText.returnStringAtRandom(
						"Reaching down between [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] over the entrance to [npc.her] [npc.pussy+],"
								+ " before letting out [npc.a_moan+] as [npc.she] [npc.verb(use)] [npc.her] digits to spread out [npc.her] labia.",
						"[npc.Name] [npc.verb(probe)] [npc.her] [npc.fingers] down between [npc.her] [npc.legs], [npc.moaning] softly as [npc.she] [npc.verb(use)] two of [npc.her] digits to spread out [npc.her] [npc.pussy+].",
						"Sliding [npc.her] fingertips over [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(use)] [npc.her] digits to spread out [npc.her] pussy lips.",
						"[npc.Name] eagerly [npc.verb(slide)] [npc.her] [npc.fingers] over [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] [npc.verb(use)] [npc.her] digits to part [npc.her] soft folds."));
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction SELF_FINGER_VAGINA_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Finger [npc.herself]";
		}

		@Override
		public String getActionDescription() {
			return "Start fingering [npc.herself].";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"Reaching down between [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] over the entrance to [npc.her] [npc.pussy+], before letting out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] digits deep inside.",
					"[npc.Name] [npc.verb(probe)] [npc.her] fingers down between [npc.her] [npc.legs], [npc.moaning] softly as [npc.she] [npc.verb(push)] two of [npc.her] digits into [npc.her] inviting [npc.pussy+].",
					"Sliding [npc.her] fingertips over [npc.her] neglected [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] digits inside and [npc.verb(start)] fingering [npc.herself].",
					"[npc.Name] eagerly [npc.verb(push)] [npc.her] fingers into [npc.her] needy [npc.pussy], [npc.moaning+] as [npc.she] [npc.verb(curl)] [npc.her] digits up inside [npc.herself] and [npc.verb(start)] stroking in a 'come-hither' motion."));
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Gently finger [npc.her] [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] slowly [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					"Gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.herself].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out a little whimper as [npc.she] [npc.verb(start)] stroking [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] gently pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Finger self";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.herself].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] stroking [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Roughly finger [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] roughly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+], before starting to roughly finger [npc.herself].",
					"Roughly pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] ruthlessly [npc.verb(finger)] [npc.herself].",
					"Forcefully curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] roughly grinding [npc.her] fingertips up against [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "Finger self";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fingering [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(push)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+].",
					"Pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] rhythmically [npc.verb(finger)] [npc.herself].",
					"Curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] stroking [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] [npc.verb(start)] pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "Eager fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly finger [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] escapes from between [npc.namePos] [npc.lips+] as [npc.she] eagerly [npc.verb(slam)] [npc.her] [npc.fingers] deep inside [npc.her] [npc.pussy+], before starting to desperately finger [npc.herself].",
					"Enthusiastically pumping [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] letting out a series of delighted [npc.moans] as [npc.she] frantically [npc.verb(finger)] [npc.herself].",
					"Desperately curling [npc.her] [npc.fingers] up inside [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(start)] eagerly grinding [npc.her] fingertips up against [npc.her] vaginal walls.",
					"Focusing on the pleasure [npc.sheIs] giving [npc.herself] between [npc.her] [npc.legs], [npc.name] eagerly [npc.verb(start)] slamming [npc.her] [npc.fingers] in and out of [npc.her] [npc.pussy+].");
		}

	};
	
	public static final SexAction SELF_FINGER_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Stop fingering (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering [npc.herself].";
		}

		@Override
		public String getDescription() {
			return "With [npc.a_groan+], [npc.name] [npc.verb(slide)] [npc.her] fingers out of [npc.her] [npc.pussy+].";
		}
	};
}
