package com.base.game.sex.sexActions.universal.consensual;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.effects.Fetish;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.Race;
import com.base.game.character.race.RaceStage;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.LubricationType;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.69.9
 * @version 0.1.79
 * @author Innoxia
 */
public class ConChairBottom {

	private static StringBuilder descriptionSB = new StringBuilder();
	
	public static SexAction PARTNER_KISS = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Kiss";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isPartnerFreeMouth() && Sex.isPlayerFreeMouth();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] leans down, taking your head in [npc.her] [npc.hands+] before pressing [npc.her] [npc.lips] firmly against yours. You let out a little [pc.moan] as you feel [npc.herHim] thrust [npc.her] [npc.tongue+] into your mouth.",
					
					"[npc.Name] bends down, pressing [npc.her] [npc.lips+] greedily against your mouth and letting out [npc.a_moan+] as [npc.she] pushes [npc.her] [npc.tongue+] past your [pc.lips+].",
					
					"As [npc.name] leans down, you eagerly raise your head and pucker your [pc.lips+], happily pressing your mouth against [npc.hers] as you start passionately kissing one another.",
					
					"[npc.Name] takes hold of your head, and, encouraged by the little [pc.moan] that you fail to contain, [npc.she] quickly pulls you forwards into a desperate, passionate kiss.");
			
		}
	};
	
	public static SexAction PARTNER_GRIND_GROIN = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Grind groin";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isAnyNonSelfPenetrationHappening();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] slides down so that [npc.she]'s sitting on your thigh, and as you look up into [npc.her] [npc.eyes+], [npc.she] starts grinding [npc.her] groin down against your [pc.leg+].",
					
					"[npc.Name] sits down on your [pc.leg+], gazing hungrily into your [pc.eyes+] as [npc.she] starts grinding [npc.her] groin down against your thigh.",
					
					"Sitting down on one of your [pc.legs+], [npc.name] lets out [npc.a_moan+] as [npc.she] starts grinding [npc.her] groin down against your thigh.",
					
					"Grinding [npc.her] groin down against your [pc.leg+], [npc.name] lets out [npc.a_moan+] as [npc.she] gazes hungrily into your [pc.eyes+].");
			
		}
	};
	
	public static SexAction PARTNER_FEEL_BREASTS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Feel breasts";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)){
				
				return UtilText.returnStringAtRandom(
						"Reaching down, [npc.name] greedily gropes and squeezes at your chest, driving the fabric of your [pc.lowClothing(nipples)] into your [pc.breasts+] as you gasp and squirm under [npc.her] touch.",
								
						"Your [pc.breasts+] prove to be too tempting a target for [npc.name] to ignore, and [npc.she] eagerly starts pressing [npc.her] [npc.hands+] into the fabric of your [pc.topClothing(nipples)],"
								+ " causing you to let out [pc.a_moan+].",
						
						"Your chest heaves up and down as you pant in excitement, drawing [npc.name]'s attention to your [pc.breastRows] [pc.breasts+]."
								+ " You let out a little gasp as [npc.she] suddenly reaches down and starts groping your [pc.breasts+] through the fabric of your [pc.topClothing(nipples)].",
						
						"[pc.A_moan+] escapes from between your [pc.lips+] as [npc.name] starts groping and massaging your [pc.breastRows] [pc.breasts+] through the fabric of your [pc.topClothing(nipples)].");
				
			}else{
				return UtilText.returnStringAtRandom(
						"Reaching down, [npc.name] greedily gropes and squeezes at your chest, driving [npc.her] fingers into your [pc.breasts+] as you gasp and squirm under [npc.her] touch.",
						
						"Your [pc.breasts+] prove to be too tempting a target for [npc.name] to ignore, and [npc.she] eagerly starts pressing [npc.her] [npc.hands+] into your [pc.breasts+], causing you to let out [pc.a_moan+].",
						
						"Your chest heaves up and down as you pant in excitement, drawing [npc.name]'s attention to your [pc.breastRows] [pc.breasts+]."
								+ " You let out a little gasp as [npc.she] suddenly reaches down and starts groping your [pc.breasts+], sinking [npc.her] fingers into the flesh of your chest as you squirm and [pc.moan] beneath [npc.herHim].",
						
						"[pc.A_moan+] escapes from between your [pc.lips+] as [npc.name] starts groping and massaging your [pc.breastRows] [pc.breasts+], smiling greedily as [npc.she] sinks [npc.her] fingers into your heaving [pc.breasts].");
				
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static SexAction PARTNER_PINCH_NIPPLES = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"[npc.Name] reaches down and starts to eagerly pinch and rub at your [pc.nipples+], quickly causing you to start squealing and moaning in delight as you beg [npc.herHim] not to stop.",
					
					"Your [pc.breasts+], being fully on display, are too tempting a target for [npc.name] to resist, and with a little [npc.moan], [npc.she] reaches down to tug and pinch at your [pc.nipples+].",
					
					"[npc.Name] teases [npc.her] fingers around each of your [pc.nipples]. Not satisfied with the intensity of your resulting [pc.moan], [npc.she] decides to start pinching and tugging at them instead,"
						+ " which, much to [npc.her] delight, causes you to let out a desperate scream of ecstasy.",
							
					"[npc.Name] greedily starts pinching and squeezing at your [pc.breastRows] [pc.nipples+], causing you to let out a series of [pc.moans+]."));
			
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					descriptionSB.append(" As [npc.she] starts pinching your [pc.nipples], a small trickle of [pc.milk] leaks out to run down your [pc.breasts+].");
					break;
				case TWO_SMALL_AMOUNT:
					descriptionSB.append(" As [npc.she] starts pinching your [pc.nipples], a small squirt of [pc.milk] leaks out to run down your [pc.breasts+].");
					break;
				case THREE_DECENT_AMOUNT:
					descriptionSB.append(" As [npc.she] starts pinching your [pc.nipples], a trickle of [pc.milk] runs down over your [pc.breasts+].");
					break;
				case FOUR_LARGE_AMOUNT:
					descriptionSB.append(" Your [pc.milk] starts to flow out over [npc.her] fingertips as [npc.she] greedily milks your [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" Your [pc.milk] starts drooling out over [npc.her] fingertips as [npc.she] greedily milks your [pc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" Your [pc.milk] starts pouring out over [npc.her] fingertips as [npc.she] greedily milks your [pc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" Your [pc.milk] starts pouring out over [npc.her] fingertips as [npc.she] greedily milks your [pc.breasts+].");
					break;
				default:
					break;
			}
			
			return descriptionSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static SexAction FINGER_PARTNER_NIPPLES = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Finger nipples";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"Reaching down, [npc.name] circles [npc.her] fingers around your [pc.nipples+], before pushing forwards and sinking [npc.her] digits into the warm flesh of your [pc.breasts]."
							+ " You let out [pc.a_moan+] at the sudden penetration, and as [npc.she] starts eagerly fingering your [npc.nipples], you look up at [npc.herHim] and continue moaning in pleasure.",
							
					"[npc.Name] presses [npc.her] fingers down against your [pc.nipples+], and, with a steady pressure, [npc.she] sinks [npc.her] digits deep inside."
							+ " You find yourself crying out and pushing your chest up into [npc.her] hands as [npc.she] starts rapidly fingering your [pc.breasts+].",
							
					"Your [pc.nipples+] prove to be too tempting for [npc.name] to ignore any longer."
							+ " In one swift motion, [npc.she] brings [npc.her] [npc.hands+] down to your [npc.breasts], pushing [npc.her] digits inside as [npc.she] starts fingering your [pc.nipples+].",
							
					"Circling your [pc.nipples+] with [npc.her] fingers, [npc.name] decides to take it one step further, and, sinking [npc.her] digits into your inviting [npc.nipples],"
							+ " [npc.she] starts fingering your [pc.breasts+], causing you to [pc.moan] and sigh in delight."));
			
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					descriptionSB.append(" As [npc.she] starts fingering your [pc.nipples], a small trickle of [pc.milk] leaks out to run down your [pc.breasts+].");
					break;
				case TWO_SMALL_AMOUNT:
					descriptionSB.append(" As [npc.she] starts fingering your [pc.nipples], a small squirt of [pc.milk] leaks out to run down your [pc.breasts+].");
					break;
				case THREE_DECENT_AMOUNT:
					descriptionSB.append(" As [npc.she] starts fingering your [pc.nipples], a trickle of [pc.milk] runs down over your [pc.breasts+].");
					break;
				case FOUR_LARGE_AMOUNT:
					descriptionSB.append(" Your [pc.milk] starts to flow out over [npc.her] fingertips as [npc.she] greedily fingers your [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" Your [pc.milk] starts drooling out over [npc.her] fingertips as [npc.she] greedily fingers your [pc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" Your [pc.milk] starts pouring out over [npc.her] fingertips as [npc.she] greedily fingers your [pc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" Your [pc.milk] starts pouring out over [npc.her] fingertips as [npc.she] greedily fingers your [pc.breasts+].");
					break;
				default:
					break;
			}
		
			return descriptionSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static SexAction PARTNER_KISS_NIPPLES = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Kiss nipples";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"Leaning down, [npc.name] presses [npc.her] [npc.lips] against one of your [pc.nipples+]."
							+ " Darting [npc.her] [npc.tongue+] out, [npc.she] licks and sucks on each of your [pc.nipples] as you [pc.moan] and sigh beneath [npc.herHim].",
							
					"[npc.Name] presses [npc.her] mouth down against one of your [pc.breasts+], [npc.moaning] contentedly to [npc.herself] as [npc.she] licks and sucks on your hard little [pc.nipples].",
							
					"Your [pc.breasts+] are just begging for some attention, and you let out a delighted [pc.moan] as [npc.name] leans down to kiss and suck on your hard little [pc.nipples].",
							
					"You let out [pc.a_moan+] as [npc.name] presses [npc.her] mouth down over one of your [pc.nipples+]."
							+ " As [npc.she] runs [npc.her] [npc.tongue] around your sensitive areolae, you find yourself [pc.moaning] in delight as you push your chest up against [npc.her] [npc.face]."));
			
			switch (Sex.getPartner().getBreastLactation()) {
				case ONE_TRICKLE:
					descriptionSB.append(" As [npc.she] kisses and sucks on your [pc.nipples], a small trickle of [pc.milk] leaks out into [npc.her] mouth.");
					break;
				case TWO_SMALL_AMOUNT:
					descriptionSB.append(" As [npc.she] kisses and sucks on your [pc.nipples], a small squirt of [pc.milk] leaks out into [npc.her] mouth.");
					break;
				case THREE_DECENT_AMOUNT:
					descriptionSB.append(" As [npc.she] kisses and sucks on your [pc.nipples], a trickle of [pc.milk] runs out into [npc.her] mouth.");
					break;
				case FOUR_LARGE_AMOUNT:
					descriptionSB.append(" You feel your [pc.milk] start to flow out into [npc.name]'s mouth as [npc.she] greedily suckles at your [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" You feel your [pc.milk] drooling out into [npc.name]'s mouth as [npc.she] greedily suckles at your [pc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" You feel your [pc.milk] pouring out into [npc.name]'s mouth as [npc.she] greedily suckles at your [pc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" You feel your [pc.milk] pouring out into [npc.name]'s mouth as [npc.she] greedily suckles at your [pc.breasts+].");
					break;
				default:
					break;
			}
			
			return descriptionSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.addOrificeLubrication(OrificeType.NIPPLE_PLAYER, LubricationType.PARTNER_SALIVA);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static SexAction PARTNER_STOP_FINGER_NIPPLES = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Stop nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "[npc.Name] slides [npc.her] fingers up and out of your [pc.nipples+], grinning down at you as you let out [pc.a_moan+].";
		}
	};
	
	public static SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);

			descriptionSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] grins down at you as you [pc.moan] beneath [npc.herHim], ",
					"You look up into [npc.name]'s [npc.eyes+] as [npc.she] speaks down to you, "));

			descriptionSB.append(((NPC)Sex.getPartner()).getDirtyTalk(Sex.isPlayerDom()));
			
			return descriptionSB.toString(); 
		}
	};
	
	public static SexAction PARTNER_ROUGH_TALK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough talk";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc.name] that [npc.she]'s your little fuck-toy.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasFetish(Fetish.FETISH_DOMINANT);
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			if(Sex.isAnyNonSelfPenetrationHappening()){
				
				descriptionSB.append(UtilText.returnStringAtRandom(
						"Reaching down to grab your chin, [npc.name] grins down evilly at you, ",
						"[npc.Name] presses [npc.a_hand+] over your mouth, suppressing your [pc.moans] as [npc.she] speaks down to you, "));
				
				descriptionSB.append(UtilText.returnStringAtRandom(
						"[npc.speech(That's right, you're just my slutty little fuck-toy!)]",
						"[npc.speech(Ah! I love submissive little sluts like you!)]",
						"[npc.speech(You're my little fuck-toy, understand?! You belong to me!)]",
						"[npc.speech(Yeah! You love being my little fuck-toy, don't you slut?!)]"));
			}else{
				
				descriptionSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] grins down evilly at you, ",
						"You look up into [npc.name]'s [npc.eyes+] as [npc.she] roughly grabs your [pc.hips] and speaks down to you, "));
				
				descriptionSB.append(UtilText.returnStringAtRandom(
						"[npc.speech(I bet you can't wait to get fucked, can you slut?)]",
						"[npc.speech(All you're good for is nice, hard fuck, understood?)]",
						"[npc.speech(You're just my little fuck-toy now, understand?)]",
						"[npc.speech(I love worthless sluts like you!)]"));
			}
			
			return descriptionSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static SexAction PARTNER_PENETRATE = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Fuck [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=PenetrationType.PENIS_PLAYER
					&& Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=PenetrationType.PENIS_PLAYER;
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append("Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to the entrance of your [pc.pussy+], [npc.name] starts to push forwards, letting out [npc.a_groan+] as [npc.she] penetrates your [pc.pussy+].");
			
			switch(Sex.getPartner().getPenisType()){
				case CANINE:
					descriptionSB.append(" Grinning down at you, [npc.she] starts bucking [npc.her] hips back and forth, slamming [npc.her] knot against your pussy lips as you [pc.moan] in delight, ");
					break;
				case EQUINE:
					descriptionSB.append(" Grinning down at you, [npc.she] starts bucking [npc.her] hips back and forth, sliding the [npc.cockHead] of [npc.her] [npc.penis+] in and out of your [pc.pussy] as you [pc.moan] in delight, ");
					break;
				case FELINE:
					descriptionSB.append(" Grinning down at you, [npc.she] starts bucking [npc.her] hips back and forth, raking the little barbs that line [npc.her] [npc.penis+] along the walls of your [pc.pussy] as you [pc.moan] in delight, ");
					break;
				default:
					descriptionSB.append(" Grinning down at you, [npc.she] starts bucking [npc.her] hips back and forth, slamming the base of [npc.her] [npc.penis+] against your pussy lips as you [pc.moan] in delight, ");
					break;
			}
			
			descriptionSB.append("[pc.speech(Fuck! Yeah! Fuck me!)]");
			
			return descriptionSB.toString();
		}
	};
	
	public static SexAction PARTNER_NORMAL_FUCK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] smiles down at you as you [pc.moan+] beneath [npc.herHim], completely lost in the pleasure that [npc.her] [npc.cock+] is giving to you.",
							
					"[npc.Name] steadily thrusts [npc.her] [npc.hips] into your crotch, grinning at the sound of your [pc.moans] as [npc.her] [npc.cock+] fills your [pc.pussy+].",
							
					"As [npc.name] fills your [pc.pussy+] with [npc.her] [npc.cock+], you struggle to do anything other than look up at [npc.herHim] and let out a long, satisfied [pc.moan].",
							
					(Sex.hasFreeHandPartner()
							?"[npc.Name] slides [npc.a_hand+] under your thigh, running down to grip you behind the knee before lifting your [pc.leg] up slightly as [npc.she] plunges [npc.her] [npc.cock+] in and out of your [pc.pussy+]."
							:"[npc.Name] grins down at you as [npc.she] steadily pumps [npc.her] [npc.cock+] in and out of your [pc.pussy+]."));
		}
	};
	
	public static SexAction PARTNER_ROUGH_FUCK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Rough fuck";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasFetish(Fetish.FETISH_DOMINANT);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Main.game.getPlayer().hasHorns()
						?"Reaching down, [npc.name] roughly grabs your [pc.horns+], using them for support as [npc.she] starts to rapidly slam [npc.her] [npc.cock+] in and out of your [pc.pussy+]."
						:"Reaching down, [npc.name] roughly grabs your [pc.hips+], using them for support as [npc.she] starts to rapidly slam [npc.her] [npc.cock+] in and out of your [pc.pussy+]."),
							
					"Roughly pushing [npc.her] [npc.hands+] down on to your [pc.breasts+], [npc.name] lets out a gleeful [npc.moan] as [npc.she] leans heavily down on top of you,"
								+ " using the extra support to frantically thrust [npc.her] eager [npc.cock] in and out of your [pc.pussy+].",
							
					"[npc.Name] roughly grabs your [pc.hips+], causing you to let out a startled cry as [npc.she] forcefully pulls you into [npc.her] crotch."
								+ " You drop your [pc.hands] down for support as [npc.she] manhandles you forwards and back, repeatedly spearing your [pc.pussy+] on [npc.her] [npc.cock+].",
							
					(Sex.hasFreeHandPartner()
							?"Reaching down, [npc.name] grabs your chin, pushing [npc.her] thumb into your mouth as [npc.she] roughly slams [npc.her] [npc.cock+] in and out of your [pc.pussy+]."
							:"[npc.Name] grins down at you as [npc.she] starts roughly slamming [npc.her] [npc.cock+] in and out of your [pc.pussy+]."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static SexAction PARTNER_PENETRATE_ANAL = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Fuck [npc.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=PenetrationType.PENIS_PLAYER
					&& Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=PenetrationType.PENIS_PLAYER
					&& (Sex.getPartner().hasFetish(Fetish.FETISH_ANAL_GIVING) || !Main.game.getPlayer().hasVagina());
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append("Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to your [pc.ass+], [npc.name] starts to push forwards, letting out [npc.a_groan+] as [npc.she] penetrates your [pc.asshole+].");
			
			switch(Sex.getPartner().getPenisType()){
				case CANINE:
					descriptionSB.append(" Grinning down at you, [npc.she] starts bucking [npc.her] hips back and forth, slamming [npc.her] knot against your [pc.asshole] as you [pc.moan] in delight, ");
					break;
				case EQUINE:
					descriptionSB.append(" Grinning down at you, [npc.she] starts bucking [npc.her] hips back and forth, sliding the [npc.cockHead] of [npc.her] [npc.penis+] in and out of your [pc.ass] as you [pc.moan] in delight, ");
					break;
				case FELINE:
					descriptionSB.append(" Grinning down at you, [npc.she] starts bucking [npc.her] hips back and forth, raking the little barbs that line [npc.her] [npc.penis+] along the walls of your [pc.asshole] as you [pc.moan] in delight, ");
					break;
				default:
					descriptionSB.append(" Grinning down at you, [npc.she] starts bucking [npc.her] hips back and forth, slamming the base of [npc.her] [npc.penis+] against your [pc.asshole] as you [pc.moan] in delight, ");
					break;
			}
			
			descriptionSB.append("[pc.speech(Fuck! Yeah! Fuck me!)]");
			
			return descriptionSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
	};
	
	public static SexAction PARTNER_NORMAL_ANAL_FUCK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] smiles down at you as you [pc.moan+] beneath [npc.herHim], completely lost in the pleasure that [npc.her] [npc.cock+] is giving to you.",
							
					"[npc.Name] steadily thrusts [npc.her] [npc.hips] into your [pc.ass], grinning at the sound of your [pc.moans] as [npc.her] [npc.cock+] fills your [pc.asshole+].",
							
					"As [npc.name] fills your [pc.asshole+] with [npc.her] [npc.cock+], you struggle to do anything other than look up at [npc.herHim] and let out a long, satisfied [pc.moan].",
							
					(Sex.hasFreeHandPartner()
							?"[npc.Name] slides [npc.a_hand+] under your thigh, running down to grip you behind the knee before lifting your [pc.leg] up slightly as [npc.she] plunges [npc.her] [npc.cock+] in and out of your [pc.asshole+]."
							:"[npc.Name] grins down at you as [npc.she] steadily pumps [npc.her] [npc.cock+] in and out of your [pc.asshole+]."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
	};
	
	public static SexAction PARTNER_ROUGH_ANAL_FUCK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Rough anal";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Main.game.getPlayer().hasHorns()
						?"Reaching down, [npc.name] roughly grabs your [pc.horns+], using them for support as [npc.she] starts to rapidly slam [npc.her] [npc.cock+] in and out of your [pc.asshole+]."
						:"Reaching down, [npc.name] roughly grabs your [pc.hips+], using them for support as [npc.she] starts to rapidly slam [npc.her] [npc.cock+] in and out of your [pc.asshole+]."),
							
					"Roughly pushing [npc.her] [npc.hands+] down on to your [pc.breasts+], [npc.name] lets out a gleeful [npc.moan] as [npc.she] leans heavily down on top of you,"
								+ " using the extra support to frantically thrust [npc.her] eager [npc.cock] in and out of your [pc.asshole+].",
							
					"[npc.Name] roughly grabs your [pc.hips+], causing you to let out a startled cry as [npc.she] forcefully pulls your [pc.ass] into [npc.her] crotch."
								+ " You drop your [pc.hands] down for support as [npc.she] manhandles you forwards and back, repeatedly spearing your [pc.asshole+] on [npc.her] [npc.cock+].",
							
					(Sex.hasFreeHandPartner()
							?"Reaching down, [npc.name] grabs your chin, pushing [npc.her] thumb into your mouth as [npc.she] roughly slams [npc.her] [npc.cock+] in and out of your [pc.asshole+]."
							:"[npc.Name] grins down at you as [npc.she] starts roughly slamming [npc.her] [npc.cock+] in and out of your [pc.asshole+]."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
	};
	
	public static SexAction PARTNER_PENETRATE_BREASTS = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Fuck nipples";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=PenetrationType.PENIS_PLAYER
					&& Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=PenetrationType.PENIS_PLAYER
					&& (Sex.getPartner().hasFetish(Fetish.FETISH_BREASTS_OTHERS) || !Main.game.getPlayer().hasVagina());
		}

		@Override
		public String getDescription() {
			return "[npc.Name] suddenly lifts one of [npc.her] [npc.legs], before stepping forwards to place it down to one side of you."
					+ " Leaning forwards, [npc.she] brings [npc.her] [npc.cock+] up to your [pc.breasts+], grinning wolfishly as [npc.she] lines [npc.her] [npc.cockHead+] up to one of your [pc.nipples+]."
					+ " Finally getting into a comfortable position, [npc.she] suddenly thrusts [npc.her] [npc.hips] forwards, penetrating your [pc.nipple+] and causing you to let out [pc.a_moan+] as [npc.she] starts fucking your [pc.breast].";
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static SexAction PARTNER_NORMAL_NIPPLE_FUCK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] smiles down at you as you [pc.moan+] beneath [npc.herHim], completely lost in pleasure as [npc.her] [npc.cock+] slides in and out of your [pc.nipple].",
							
					"[npc.Name] steadily thrusts [npc.her] [npc.hips] into your [pc.breast], grinning at the sound of your [pc.moans] as [npc.her] [npc.cock+] fills your [pc.nipple+].",
							
					"As [npc.she] fills your [pc.nipple+] with [npc.her] [npc.cock+], [npc.name] looks down at you and lets out a gleeful [npc.moan].",
							
					(Sex.hasFreeHandPartner()
							?"[npc.Name] slides [npc.a_hand+] down to your [pc.breast], lifting it up slightly as [npc.she] plunges [npc.her] [npc.cock+] in and out of your [pc.nipple+]."
							:"[npc.Name] grins down at you as [npc.she] steadily pumps [npc.her] [npc.cock+] in and out of your [pc.nipple+]."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
	};
	
	public static SexAction PARTNER_ROUGH_NIPPLE_FUCK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Rough fuck";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Main.game.getPlayer().hasHorns()
						?"Reaching down, [npc.name] roughly grabs your [pc.horns+], using them for support as [npc.she] starts to rapidly slam [npc.her] [npc.cock+] in and out of your [pc.nipple+]."
						:"Reaching down, [npc.name] roughly grabs your [pc.hips+], using them for support as [npc.she] starts to rapidly slam [npc.her] [npc.cock+] in and out of your [pc.nipple+]."),
							
					"Reaching down to roughly grab your head, [npc.name] lets out [npc.a_maon+] as [npc.she] leans heavily down on top of you,"
							+ " sinking [npc.her] [npc.cock+] deep into your [pc.breast] before frantically thrusting in and out of your [pc.nipple+].",
							
					"[npc.Name] reaches down to roughly grab your head, causing you to let out a startled cry as [npc.she] starts forcefully slamming [npc.her] [npc.cock+] in and out of your [pc.nipple+].",
							
					"You let out a desperate [pc.moan] as [npc.name] roughly slams [npc.her] [npc.cock+] deep into your [pc.nipple+]."
							+ " Drawing back, [npc.she] then starts rapidly fucking your [pc.breast], letting out a series of [npc.moans+] as you pant and sigh beneath [npc.herHim].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS), new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	
	public static SexAction PARTNER_RIDE_PLAYERS_COCK = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Ride [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=PenetrationType.PENIS_PARTNER
					&& Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=PenetrationType.PENIS_PARTNER;
		}

		@Override
		public String getDescription() {
			return "Stepping forwards, [npc.name] slides down into your lap, lowering [npc.herself] down so that [npc.she]'s hovering over your [pc.cock+]."
					+ " Sensing what [npc.she]'s about to do, you let out a pleading whine, which soon turns into [pc.a_moan+] as [npc.she] lines [npc.her] [npc.pussy+] up to your [pc.cockHead] and drops down,"
					+ " spearing [npc.herself] on your [pc.cock+].";
		}
	};
	
	public static SexAction PARTNER_NORMAL_RIDE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Normal ride";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Lifting [npc.herself] up, [npc.name] bites [npc.her] [npc.lip] before dropping down, spearing [npc.herself] on your [pc.cock+].",
					
					"[npc.Name] lifts [npc.herself] up, allowing your [pc.cock+] to almost slip out of [npc.her] [npc.pussy+], before suddenly dropping down, slamming your [pc.cock+] deep into [npc.her] [npc.pussy+].",
					
					"[npc.Name] lets out [npc.a_moan+] as [npc.she] starts bouncing up and down in your lap, grinning down at you as [npc.she] fucks [npc.herself] on your [pc.cock+].",
					
					"With [npc.a_moan+], [npc.name] sinks down in your lap, squealing in delight as [npc.she] fills [npc.herself] with your [pc.cock+], before starting to slowly gyrate [npc.her] [npc.hips] down into your crotch.");
		}
	};
	
	public static SexAction PARTNER_ROUGH_RIDE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Rough ride";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasFetish(Fetish.FETISH_DOMINANT);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Main.game.getPlayer().hasHorns()
						?"Reaching down, [npc.name] roughly grabs your [pc.horns+], using them for support as [npc.she] starts to rapidly bounce up and down on your [pc.cock+]."
						:"Reaching down, [npc.name] roughly grabs your [pc.hips+], using them for support as [npc.she] starts to rapidly bounce up and down on your [npc.cock+]."),
							
					"Placing [npc.her] [npc.hands+] down on your [pc.breasts+], [npc.name] leans into you for support as [npc.she] starts roughly slamming [npc.her] [npc.pussy+] up and down on your [pc.cock+].",
							
					"Roughly grabbing your shoulders, [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bouncing up and down in your lap,"
							+ " repeatedly spearing [npc.her] [npc.pussy+] on your [pc.cock+] as you join your [pc.moans] with [npc.hers].",
							
					(Sex.hasFreeHandPartner()
							?"Reaching down, [npc.name] grabs your chin, pushing [npc.her] thumb into your mouth as [npc.she] roughly bounces up and down on your [pc.cock+]."
							:"[npc.Name] grins down at you as [npc.she] starts roughly bouncing up and down on your [pc.cock+]."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	
	public static SexAction PARTNER_RIDE_ANAL_PLAYERS_COCK = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Anal ride";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=PenetrationType.PENIS_PARTNER
					&& Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=PenetrationType.PENIS_PARTNER
					&& (Sex.getPartner().hasFetish(Fetish.FETISH_ANAL_RECEIVING) || !Sex.getPartner().hasVagina());
		}

		@Override
		public String getDescription() {
			return "Stepping forwards, [npc.name] slides down into your lap, lowering [npc.herself] down so that [npc.her] [npc.ass] is hovering over your [pc.cock+]."
					+ " Sensing what [npc.she]'s about to do, you let out a pleading whine, which soon turns into [pc.a_moan+] as [npc.she] lines [npc.her] [npc.asshole+] up to your [pc.cockHead] and drops down,"
					+ " spearing [npc.herself] on your [pc.cock+].";
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static SexAction PARTNER_NORMAL_ANAL_RIDE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Normal ride";
		}

		@Override
		public String getActionDescription() {
			return "Slowly bounce up and down on [npc.name]'s [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Lifting [npc.herself] up, [npc.name] bites [npc.her] [npc.lip] before dropping down, spearing [npc.herself] on your [pc.cock+].",
					
					"[npc.Name] lifts [npc.herself] up, allowing your [pc.cock+] to almost slip out of [npc.her] [npc.asshole+], before suddenly dropping down, slamming your [pc.cock+] deep into [npc.her] [npc.ass+].",
					
					"[npc.Name] lets out [npc.a_moan+] as [npc.she] starts bouncing up and down in your lap, grinning down at you as [npc.she] fucks [npc.her] [npc.ass] on your [pc.cock+].",
					
					"With [npc.a_moan+], [npc.name] sinks down in your lap, squealing in delight as [npc.she] fills [npc.her] [npc.asshole+] with your [pc.cock+], before starting to slowly gyrate [npc.her] [npc.ass] down against your crotch.");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static SexAction PARTNER_ROUGH_ANAL_RIDE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Rough ride";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasFetish(Fetish.FETISH_DOMINANT);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Main.game.getPlayer().hasHorns()
						?"Reaching down, [npc.name] roughly grabs your [pc.horns+], using them for support as [npc.she] starts to rapidly bounce up and down on your [pc.cock+]."
						:"Reaching down, [npc.name] roughly grabs your [pc.hips+], using them for support as [npc.she] starts to rapidly bounce up and down on your [npc.cock+]."),
							
					"Placing [npc.her] [npc.hands+] down on your [pc.breasts+], [npc.name] leans into you for support as [npc.she] starts roughly slamming [npc.her] [npc.ass+] up and down on your [pc.cock+].",
							
					"Roughly grabbing your shoulders, [npc.name] lets out [npc.a_moan+] as [npc.she] starts frantically bouncing up and down in your lap,"
							+ " repeatedly spearing [npc.her] [npc.asshole+] on your [pc.cock+] as you join your [pc.moans] with [npc.hers].",
							
					(Sex.hasFreeHandPartner()
							?"Reaching down, [npc.name] grabs your chin, pushing [npc.her] thumb into your mouth as [npc.she] roughly bounces up and down on your [pc.cock+]."
							:"[npc.Name] grins down at you as [npc.she] starts roughly bouncing up and down on your [pc.cock+]."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	
	public static SexAction PARTNER_GET_PEGGED = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Get tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasFetish(Fetish.FETISH_ANAL_RECEIVING);
		}

		@Override
		public String getDescription() {
			return "Reaching down, [npc.name] suddenly grabs your [pc.tail+]."
					+ " Guiding it around between [npc.her] [npc.legs], [npc.she] presses the tip up against [npc.her] exposed [npc.asshole], and you let out a happy cry as you realise what [npc.she] wants."
					+ " With a determined, forceful thrust, you eagerly spear your [pc.tail] deep into [npc.her] [npc.ass], causing [npc.her] to let out [npc.a_moan+]."
					+ (Sex.getPartner().hasPenis()
						?" [npc.Her] [npc.moans] soon turn into a desperate gasp as you purposefully press your [pc.tail] up and start to massage and stroke [npc.her] prostate."
						:"");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static SexAction PARTNER_GET_TAIL_FUCKED = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Get tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Reaching down, [npc.name] suddenly grabs your [pc.tail+]."
					+ " Guiding it up between [npc.her] [npc.legs], [npc.she] presses the tip up against [npc.her] exposed [npc.pussy], and you let out a happy cry as you realise what [npc.she] wants."
					+ " With a determined, forceful thrust, you eagerly spear your [pc.tail] deep into [npc.her] [npc.pussy+], causing [npc.her] to let out [npc.a_moan+]."
					+ " [npc.Her] [npc.legs] shudder, and [npc.she] grips down tightly on your [pc.tail] for a moment, [npc.moaning] in pleasure as you start thrusting it in and out of [npc.her] [npc.pussy+].";
		}
	};
	
	
	// Player actions:

	public static SexAction PLAYER_KISS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Kiss";
		}

		@Override
		public String getActionDescription() {
			return "Lean forwards and kiss [npc.name].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You lean forwards, pressing your [pc.lips+] firmly against [npc.name]'s. Letting out a little [pc.moan], you eagerly thrust your [pc.tongue] into [npc.her] mouth.",
					
					"You lean up to [npc.name]'s [npc.face], pressing your [pc.lips+] greedily against [npc.hers], before eagerly pushing your [pc.tongue+] into [npc.her] mouth.",
					
					"Leaning forwards, you eagerly press your [pc.lips+] against [npc.name]'s, before happily sliding your [pc.tongue+] into [npc.her] mouth.",
					
					"You reach up and grab [npc.name]'s head, and, letting out a little [pc.moan], you quickly pull [npc.herHim] down into a desperate, passionate kiss.");
		}
	};
	
	public static SexAction PLAYER_TALK_DIRTY = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			return "Talk dirty to [npc.name].";
		}

		@Override
		public String getDescription() {
			descriptionSB = new StringBuilder();

			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"Looking up at [npc.name], you let out a little [pc.moan], ",
					"Gazing up at [npc.name], you let out [pc.a_moan+], "));

			descriptionSB.append(((NPC)Sex.getPartner()).getPlayerDirtyTalk(Sex.isPlayerDom()));
			
			return descriptionSB.toString();
		}
	};
	
	public static SexAction PLAYER_SUBMISSIVE_TALK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Submissive talk";
		}

		@Override
		public String getActionDescription() {
			return "Show [npc.name] how submissive you are.";
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"Looking up at [npc.name], you bite your [pc.lip] and try to look as submissive as possible as you cry out, ",
					"You look up at [npc.name], putting on your most innocent expression as you [pc.moan] up to [npc.herHim], "));
			
			descriptionSB.append(UtilText.returnStringAtRandom(
					"[pc.speech(Yes! I'm your little slut! Take me however you want!)]",
					"[pc.speech(Yes! I'm yours! Use me! Do what you want with me!)]",
					"[pc.speech(Yeah! I'm your worthless little bitch! Fuck me!)]",
					"[pc.speech(I'm your worthless slut! Use me like your little fuck-toy!)]"));
			
			return descriptionSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static SexAction PLAYER_FEEL_BREASTS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Feel breasts";
		}

		@Override
		public String getActionDescription() {
			return "Reach up and grope [npc.name]'s [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasBreasts();
		}

		@Override
		public String getDescription() {
			if(!Sex.getPartner().isCoverableAreaExposed(CoverableArea.NIPPLES)){
				return UtilText.returnStringAtRandom(
						"Reaching up to [npc.name]'s chest, you start greedily groping and squeezing at [npc.her] [npc.breasts+],"
								+ " driving the fabric of [npc.her] [npc.lowClothing(nipples)] into [npc.her] [npc.breasts] and causing [npc.herHim] to gasp and squirm under your touch.",
								
						"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and as you reach up to eagerly press your [pc.hands+]"
								+ " into the fabric of [npc.her] [npc.topClothing(nipples)], [npc.she] lets out [npc.a_moan+].",
						
						"Your attention is suddenly drawn to [npc.name]'s [npc.breastRows] [npc.breasts+], and you find yourself letting out a little [pc.moan] as you reach up and start groping"
								+ " [npc.her] [npc.breasts+] through the fabric of [npc.her] [npc.topClothing(nipples)].",
						
						"[pc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as you reach up to start groping at [npc.her] [npc.breastRows] [npc.breasts+] through the fabric of [npc.her] [npc.topClothing(nipples)].");
				
			}else{
				return UtilText.returnStringAtRandom(
						"Reaching up to [npc.name]'s chest, you start greedily groping and squeezing at [npc.her] [npc.breasts+],"
								+ " driving your fingers into [npc.her] [npc.breasts] and causing [npc.herHim] to gasp and squirm under your touch.",
								
						"You find yourself unable to resist the temptation of [npc.name]'s [npc.breasts+], and as you reach up to eagerly press your [pc.hands+]"
								+ " into [npc.her] [npc.breasts+], [npc.she] lets out [npc.a_moan+].",
						
						"Your attention is suddenly drawn to [npc.name]'s [npc.breastRows] [npc.breasts+], and you find yourself letting out a little [pc.moan] as you reach up and start groping [npc.her] chest.",
						
						"[pc.A_moan+] escapes from between [npc.name]'s [npc.lips+] as you reach up to start groping at [npc.her] [npc.breastRows] [npc.breasts+].");
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static SexAction PLAYER_PINCH_NIPPLES = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "Reach up and start pinching [npc.name]'s [npc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasBreasts();
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"You reach up and start to eagerly pinch and rub at [npc.name]'s [npc.nipples+], quickly causing [npc.herHim] to release a series of [npc.moans+] as [npc.she] begs you not to stop.",
					
					"[npc.Name]'s [npc.breasts+] are fully on display, and prove to be too tempting a target for you to ignore."
						+ " Before [npc.she] knows what's happening, you're reaching up to tug and pinch at [npc.her] [npc.nipples+], causing [npc.herHim] to let out a series of [npc.moans+].",
						
					"You tease your fingers around [npc.name]'s [npc.breastRows] [npc.nipples]."
						+ " Not satisfied with the intensity of [npc.her] resulting [npc.moans], you decide to start pinching and tugging at them instead, which, much to your delight, causes [npc.herHim] to let out [pc.a_moan+].",
							
					"[npc.Name]'s [npc.breastRows] [npc.breasts+] lie fully exposed above you, and, unable to resist such a tempting opportunity,"
							+ " you reach up and start pinching and squeezing [npc.her] [npc.nipples+], causing [npc.herHim] to cry out in pleasure."));
			
			switch (Sex.getPartner().getBreastLactation()) {
				case ONE_TRICKLE:
					descriptionSB.append(" As you start pinching [npc.her] [npc.nipples], a small trickle of [npc.milk] leaks out to run down [npc.her] [npc.breasts+].");
					break;
				case TWO_SMALL_AMOUNT:
					descriptionSB.append(" As you start pinching [npc.her] [npc.nipples], a small squirt of [pc.milk] leaks out to run down [npc.her] [npc.breasts+].");
					break;
				case THREE_DECENT_AMOUNT:
					descriptionSB.append(" As you start pinching [npc.her] [npc.nipples], a trickle of [npc.milk] runs down over [npc.her] [npc.breasts+].");
					break;
				case FOUR_LARGE_AMOUNT:
					descriptionSB.append(" [npc.Her] [npc.milk] starts to flow out over your fingertips as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" [npc.Her] [npc.milk] starts drooling out over your fingertips as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" [npc.Her] [npc.milk] starts pouring out over your fingertips as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" [npc.Her] [npc.milk] starts pouring out over your fingertips as you greedily milk [npc.her] [npc.breasts+].");
					break;
				default:
					break;
			}
		
			return descriptionSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static SexAction PLAYER_KISS_NIPPLES = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.TONGUE_PLAYER,
			OrificeType.NIPPLE_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Kiss nipples";
		}

		@Override
		public String getActionDescription() {
			return "Lean forwards and start kissing [npc.name]'s [npc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().hasBreasts();
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"Leaning forwards, you press your [pc.lips] against one of [npc.name]'s [npc.breasts+]."
					+ " Darting your [pc.tongue] out, you start to lick and suck each of [npc.her] [npc.nipples+] as you [pc.moan] into [npc.her] [npc.breasts+].",
							
					"You pull [npc.name] forwards, pressing your mouth up against one of [npc.her] [npc.breasts+] and [pc.moaning] contentedly to yourself as you lick and suck on [npc.her] [npc.nipples+].",
							
					"[npc.Name]'s exposed chest draws your attention, and you suddenly lean forwards, pressing your [pc.lips+] against [npc.her] [npc.breasts] as you start to kiss and suck on [npc.her] [npc.nipples+].",
							
					"[npc.Name] lets out [npc.a_moan+] as you press your mouth up against [npc.her] [npc.breasts+]."
							+ " Running your [pc.tongue+] around [npc.her] sensitive areolae, [npc.her] [npc.moans] increase in intensity as you start kissing and nibbling on [npc.her] [npc.nipples+]."));
			
			switch (Sex.getPartner().getBreastLactation()) {
				case ONE_TRICKLE:
					descriptionSB.append(" As you kiss and suck on [npc.her] [npc.nipples], a small trickle of [npc.milk] leaks out into your mouth.");
					break;
				case TWO_SMALL_AMOUNT:
					descriptionSB.append(" As you kiss and suck on [npc.her] [npc.nipples], a small squirt of [pc.milk] leaks out into [npc.her] mouth.");
					break;
				case THREE_DECENT_AMOUNT:
					descriptionSB.append(" As you kiss and suck on [npc.her] [npc.nipples], a trickle of [pc.milk] runs out into [npc.her] mouth.");
					break;
				case FOUR_LARGE_AMOUNT:
					descriptionSB.append(" [npc.Name]'s [npc.milk] starts to flow out into your mouth as you greedily suckle at [npc.her] [npc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" [npc.Name]'s [npc.milk] starts drooling out into your mouth as you greedily suckle at [npc.her] [npc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" [npc.Name]'s [npc.milk] starts pouring out into your mouth as you greedily suckle at [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" [npc.Name]'s [npc.milk] starts pouring out into your mouth as you greedily suckle at [npc.her] [npc.breasts+].");
					break;
				default:
					break;
			}
			
			return descriptionSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.addOrificeLubrication(OrificeType.NIPPLE_PARTNER, LubricationType.PLAYER_SALIVA);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_BREASTS_SELF));
		}
	};
	
	public static SexAction TAIL_PLAYER_HELPS_PARTNER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Exploring tail";
		}

		@Override
		public String getActionDescription() {
			return "Start stroking your [pc.tail+] over [npc.name]'s lower body.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isTailPrehensile()
					&& Sex.isPlayerFreeTail();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You wrap your [pc.tail+] around one of [npc.name]'s [npc.legs], squeezing down tightly against [npc.her] [npc.legSkin+] as you smile up at [npc.herHim].",
					
					"You snake your [pc.tail+] around one of [npc.name]'s [npc.legs], and as you squeeze down around [npc.her] thigh, [npc.she] lets out a little [npc.moan].",
					
					"You wrap your [pc.tail+] around [npc.name]'s [npc.leg], and as you press it down against [npc.her] [npc.legSkin], [npc.she] lets out a little [npc.moan].",
					
					"Curling your [pc.tail+] up behind [npc.name], you hear [npc.herHim] let out a soft [npc.moan] as you stroke it up against [npc.her] [npc.ass].");
		}
	};
	
	public static SexAction PLAYER_PEGGING_FUN = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Thrust tail";
		}

		@Override
		public String getActionDescription() {
			return "Thrust your [pc.tail] deep into [npc.name]'s [npc.ass].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Grinning up at [npc.name], you force a surprised [npc.moan] out of [npc.her] mouth as you suddenly drive your [pc.tail] deep into [npc.her] [npc.ass].",
					
					"Winking at [npc.name], you thrust your [npc.tail+] up hard into [npc.her] [npc.asshole+], causing [npc.herHim] to let out [npc.a_moan+].",
					
					"Letting out [pc.a_moan+], you start thrusting your [pc.tail] in and out of [npc.name]'s [npc.ass],"
							+ " quickly causing [npc.her] to join [npc.her] [npc.moans] with yours as you carry on using your [pc.tail] to fuck [npc.her] [npc.asshole].",
					
					"Smiling up at [npc.name], you force your [pc.tail+] up deep into [npc.her] [npc.ass], causing [npc.herHim] to let out [npc.a_moan+].");
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static SexAction TAIL_PLAYERFUCKING_FUN = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.TAIL_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Thrust tail";
		}

		@Override
		public String getActionDescription() {
			return "Thrust your [pc.tail] deep into [npc.name]'s [npc.pussy+].";
		}
		
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Grinning up at [npc.name], you force a surprised [npc.moan] out of [npc.her] mouth as you suddenly drive your [pc.tail] deep into [npc.her] [npc.pussy+].",
					
					"Winking at [npc.name], you thrust your [npc.tail+] up hard into [npc.her] [npc.pussy+], causing [npc.herHim] to let out [npc.a_moan+].",
					
					"Letting out [pc.a_moan+], you start thrusting your [pc.tail] in and out of [npc.name]'s [npc.pussy],"
							+ " quickly causing [npc.her] to join [npc.her] [npc.moans] with yours as you carry on using your [pc.tail] to fuck [npc.her] [npc.pussy+].",
					
					"Smiling up at [npc.name], you force your [pc.tail+] up deep into [npc.her] [npc.pussy+], causing [npc.herHim] to let out [npc.a_moan+].");
		}
	};
	
	public static SexAction PLAYER_SMOULDERING_LOOK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Smouldering look";
		}

		@Override
		public String getActionDescription() {
			return "Use your demonic powers to fill [npc.name] with lust.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isFeminine()
					&& Main.game.getPlayer().getRace()==Race.DEMON
					&& Main.game.getPlayer().getRaceStage()==RaceStage.GREATER;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Looking up at [npc.name], you put on a smouldering look as you lock your [pc.eyes] onto [npc.hers]."
							+ " Concentrating on your demonic aura, you send a series of lewd moaning noises up into [npc.her] mind, and from the flushed look that suddenly crosses [npc.her] face, you know that they're having quite a strong effect.",
					
					"You let out a little pleading whine, and as [npc.name] looks down at you, you bite your [pc.lip] and send a series of psychic moans into [npc.her] mind.",
					
					"You bat your eyes up at [npc.name], making sure that [npc.she]'s looking right at you before bringing one of your slender,"
							+ " delicate fingers up to playfully pull down on your lower lip, using your demonic powers to send [pc.a_moan+] directly into [npc.her] mind at the same time.",
					
					"Winking at [npc.name] as [npc.she] looks down at you, you bring your hand up to your [pc.lips+] and blow [npc.herHim] a kiss, sending [pc.a_moan+] directly into [npc.her] mind as you let out a little giggle.");
			
		}
	};
	
	public static SexAction PLAYER_PENETRATES = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Get penetrated";
		}

		@Override
		public String getActionDescription() {
			return "Wrap your legs around [npc.name] and force [npc.herHim] to penetrate you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=PenetrationType.PENIS_PLAYER
					&& Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=PenetrationType.PENIS_PLAYER;
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append("You can't wait any longer, and with an impatient cry, you throw your [pc.legs+] up around [npc.name]'s lower back."
					+ " Letting out [pc.a_moan+], you look up into [npc.her] [npc.eyes], [pc.speech(Come on, fuck me already!)]"
					+ "</br></br>"
					+ "Not giving [npc.herHim] any time to react, you use your [pc.legs] to pull [npc.herHim] forwards, letting out a delighted [pc.moan] as you force the [npc.cockHead+] of [npc.her] [npc.cock+] inside your [pc.pussy+].");
			

			switch(Sex.getPartner().getPenisType()){
				case CANINE:
					descriptionSB.append(" All too eager to respond to your request, [npc.she] starts bucking [npc.her] hips back and forth, slamming [npc.her] knot against your pussy lips as you squeal and moan in delight, "
							+ "[pc.speech(Fuck! Yeah! Fuck me!)]");
					break;
				case EQUINE:
					descriptionSB.append(" All too eager to respond to your request, [npc.she] starts bucking [npc.her] hips back and forth, sliding the wide head of [npc.her] horse-like shaft in and out of your [pc.pussy]"
							+ " as you squeal and moan in delight, [pc.speech(Fuck! Yeah! Fuck me!)]");
					break;
				case FELINE:
					descriptionSB.append(" All too eager to respond to your request, [npc.she] starts bucking [npc.her] hips back and forth,"
							+ " raking the little barbs that line [npc.her] [npc.cock] along the walls of your [pc.pussy+] as you squeal and moan in delight, [pc.speech(Fuck! Yeah! Fuck me!)]");
					break;
				default:
					descriptionSB.append(" All too eager to respond to your request, [npc.she] starts bucking [npc.her] hips back and forth, slamming the base of [npc.her] [npc.cock+] against your pussy lips as you squeal and moan in delight,"
							+ " [pc.speech(Fuck! Yeah! Fuck me!)]");
					break;
			}
			
			return descriptionSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_PENETRATES_ANAL = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Start anal";
		}

		@Override
		public String getActionDescription() {
			return "Wrap your legs around [npc.name] and force [npc.herHim] to penetrate your [pc.ass].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)!=PenetrationType.PENIS_PLAYER
					&& Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=PenetrationType.PENIS_PLAYER;
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append("You can't wait any longer, and with an impatient cry, you throw your [pc.legs+] up around [npc.name]'s lower back."
					+ " Letting out [pc.a_moan+], you look up into [npc.her] [npc.eyes], [pc.speech(Come on, fuck me already!)]"
					+ "</br></br>"
					+ "Not giving [npc.herHim] any time to react, you use your [pc.legs] to pull [npc.herHim] forwards, letting out a delighted [pc.moan] as you lift up your [pc.ass],"
					+ " forcing the [npc.cockHead+] of [npc.her] [npc.cock+] inside your [pc.asshole+].");
			

			switch(Sex.getPartner().getPenisType()){
				case CANINE:
					descriptionSB.append(" All too eager to respond to your request, [npc.she] starts bucking [npc.her] hips back and forth, slamming [npc.her] knot against your [pc.ass] as you squeal and moan in delight, "
							+ "[pc.speech(Fuck! Yeah! Fuck me!)]");
					break;
				case EQUINE:
					descriptionSB.append(" All too eager to respond to your request, [npc.she] starts bucking [npc.her] hips back and forth, sliding the wide head of [npc.her] horse-like shaft in and out of your [pc.ass]"
							+ " as you squeal and moan in delight, [pc.speech(Fuck! Yeah! Fuck me!)]");
					break;
				case FELINE:
					descriptionSB.append(" All too eager to respond to your request, [npc.she] starts bucking [npc.her] hips back and forth,"
							+ " raking the little barbs that line [npc.her] [npc.cock] along the walls of your [pc.asshole] as you squeal and moan in delight, [pc.speech(Fuck! Yeah! Fuck me!)]");
					break;
				default:
					descriptionSB.append(" All too eager to respond to your request, [npc.she] starts bucking [npc.her] hips back and forth, slamming the base of [npc.her] [npc.cock+] against your [pc.ass] as you squeal and moan in delight,"
							+ " [pc.speech(Fuck! Yeah! Fuck me!)]");
					break;
			}
			
			return descriptionSB.toString();
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
	};
	
	public static SexAction PLAYER_STARTS_PARTNER_RIDE = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Pull onto cock";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.name] into your lap and get [npc.herHim] to ride your [pc.cock+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=PenetrationType.PENIS_PARTNER
					&& Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=PenetrationType.PENIS_PARTNER;
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append("You can't hold back any longer, and with an impatient cry, you lean forwards and grab [npc.name]'s [npc.arms]."
					+ " With a forceful yank forwards, you pull [npc.herHim] into your lap, and, shifting your grip down to take hold of [npc.her] [npc.hips], you cry out, [pc.speech(I'll show you how to get started!)]"
					+ "</br></br>"
					+ "Before [npc.name] can react, you roughly pull [npc.herHim] down, letting out a delighted [pc.moan] as the [pc.cockHead+] of your [pc.cock+] slips inside [npc.her] [npc.pussy+].");
			

			switch(Main.game.getPlayer().getPenisType()){
				case CANINE:
					descriptionSB.append(" As [npc.she] lets out a surprised [npc.moan], you quickly start bucking your hips up into [npc.her] groin, slamming your knot against [npc.her] pussy lips as [npc.she] squeals and moans in delight.");
					break;
				case EQUINE:
					descriptionSB.append(" As [npc.she] lets out a surprised [npc.moan], you quickly start bucking your hips up into [npc.her] groin, sliding the wide head of your horse-like shaft in and out of [npc.her] [npc.pussy+]"
							+ " as [npc.she] squeals and moans in delight.");
					break;
				case FELINE:
					descriptionSB.append(" As [npc.she] lets out a surprised [npc.moan], you quickly start bucking your hips up into [npc.her] groin,"
							+ " raking the little barbs that line your [pc.cock] along the walls of [npc.her] [npc.pussy] as [npc.she] squeals and moans in delight.");
					break;
				default:
					descriptionSB.append(" As [npc.she] lets out a surprised [npc.moan], you quickly start bucking your hips up into [npc.her] groin,"
							+ " slamming the base of your [pc.cock+] against [npc.her] pussy lips as [npc.she] squeals and moans in delight");
					break;
			}
			
			return descriptionSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_LOCKS_PARTNER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Leg-lock";
		}

		@Override
		public String getActionDescription() {
			return "Wrap your legs around [npc.name] and pull [npc.herHim] into your crotch.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Wrapping your [pc.legs] around [npc.name]'s lower back, you sharply pull forwards, forcing [npc.herHim] to bury [npc.her] [npc.cock+] deep into your [pc.pussy+].",
					
					"Lifting your [pc.legs] up behind [npc.name], you let out a playful laugh as you wrap them around [npc.her] lower back."
							+ " With one forceful pull, you slam [npc.her] [npc.cock+] deep into your needy [pc.pussy], letting out [pc.a_moan+] as you leg-lock [npc.herHim].",
					
					"You wrap your [pc.legs] around [npc.name], pulling [npc.herHim] playfully forwards as you force [npc.herHim] to sink [npc.her] [npc.cock+] deep into your hungry [pc.pussy].",
					
					"You wrap your [pc.legs+] around [npc.name]'s lower back, before slowly, but firmly, pulling [npc.herHim] forwards, forcing [npc.herHim] to bury [npc.her] [npc.cock+] deep in your [pc.pussy+].");
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_SPREADS_LEGS = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Spread legs";
		}

		@Override
		public String getActionDescription() {
			return "Spread your legs out a little wider, to give better access to your [pc.pussy+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You shuffle forwards a little, getting in a more comfortable position before spreading your [pc.legs+] and presenting your [pc.pussy+] to [npc.name].",
					
					"Reaching down, you grab your [pc.legs] just under the knee, before pulling them up and apart in order to grant [npc.name] easy access to your [pc.pussy+].",
					
					"You let out a pleading [pc.moan] as you spread out your [pc.legs+], presenting your [pc.pussy+] to [npc.name].",
					
					"You shuffle about a little, repositioning yourself slightly in order to give [npc.name] even easier access to your needy [pc.pussy].");
		}
	};
	
	public static SexAction PLAYER_BOUNCES = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Bounce in lap";
		}

		@Override
		public String getActionDescription() {
			return "Bounce [npc.name] up and down on your [pc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Grinning up at [npc.name], you start bucking your [pc.hips] up into [npc.her] groin, [pc.moaning] in delight as your [pc.cock+] thrusts deep into [npc.her] [npc.pussy+].",
					
					"With [pc.a_moan+], you thrust your [pc.hips] up, filling [npc.name]'s [npc.pussy+] with your [pc.cock+] before starting to bounce [npc.herHim] up and down in your lap.",
					
					"You let out [pc.a_moan+] as you start bouncing [npc.name] up and down in your lap, smiling up at [npc.herHim] as you fill [npc.her] [npc.pussy+] with your [pc.cock+].",
					
					"With a little [pc.moan], you start bouncing [npc.name] up and down in your lap, filling [npc.her] [npc.pussy+] with your [pc.cock+] as you smile up at [npc.herHim].");
		}
	};
	
	public static SexAction PLAYER_ROUGH_BOUNCES = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Rough bouncing";
		}

		@Override
		public String getActionDescription() {
			return "Roughly start bouncing [npc.name] up and down on your [pc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"With a devilish grin, you suddenly slam your [pc.hips] up into [npc.name]'s groin, [pc.moaning] in delight as your [pc.cock+] violently thrusts deep into [npc.her] [npc.pussy+].",
					
					"With [pc.a_moan+], you violently thrust your [pc.hips] up, slamming your [pc.cock+] deep inside [npc.name]'s [npc.pussy+] before starting to roughly bounce [npc.herHim] up and down in your lap.",
					
					"You let out [pc.a_moan+] as you start roughly bouncing [npc.name] up and down in your lap, grinning up at [npc.herHim] as you violently slam your [pc.cock+] deep into [npc.her] [npc.pussy+].",
					
					"With [pc.a_moan+], you start violently bouncing [npc.name] up and down in your lap, roughly slamming your [pc.cock+] in and out of [npc.her] [npc.pussy+] as you grin up at [npc.herHim].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_ANAL_BOUNCES = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Bounce in lap";
		}

		@Override
		public String getActionDescription() {
			return "Bounce [npc.name] up and down on your [pc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Grinning up at [npc.name], you start bucking your [pc.hips] up into [npc.her] [npc.ass], [pc.moaning] in delight as your [pc.cock+] thrusts deep into [npc.her] [npc.asshole+].",
					
					"With [pc.a_moan+], you thrust your [pc.hips] up, filling [npc.name]'s [npc.asshole+] with your [pc.cock+] before starting to bounce [npc.herHim] up and down in your lap.",
					
					"You let out [pc.a_moan+] as you start bouncing [npc.name] up and down in your lap, smiling up at [npc.herHim] as you fill [npc.her] [npc.asshole+] with your [pc.cock+].",
					
					"With a little [pc.moan], you start bouncing [npc.name] up and down in your lap, filling [npc.her] [npc.asshole+] with your [pc.cock+] as you smile up at [npc.herHim].");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
		}
	};
	
	public static SexAction PLAYER_ROUGH_ANAL_BOUNCES = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Rough bouncing";
		}

		@Override
		public String getActionDescription() {
			return "Roughly start bouncing [npc.name] up and down on your [pc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"With a devilish grin, you suddenly slam your [pc.hips] up into [npc.name]'s [npc.ass], [pc.moaning] in delight as your [pc.cock+] violently thrusts deep into [npc.her] [npc.asshole+].",
					
					"With [pc.a_moan+], you violently thrust your [pc.hips] up, slamming your [pc.cock+] deep inside [npc.name]'s [npc.asshole+] before starting to roughly bounce [npc.herHim] up and down in your lap.",
					
					"You let out [pc.a_moan+] as you start roughly bouncing [npc.name] up and down in your lap, grinning up at [npc.herHim] as you violently slam your [pc.cock+] deep into [npc.her] [npc.asshole+].",

					"With [pc.a_moan+], you start violently bouncing [npc.name] up and down in your lap, roughly slamming your [pc.cock+] in and out of [npc.her] [npc.asshole+] as you grin up at [npc.herHim].");
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_DOMINANT));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_BUCKS_BACK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Push back";
		}

		@Override
		public String getActionDescription() {
			return "Push your hips down against [npc.name]'s groin, helping [npc.herHim] to fuck you.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You start pushing your [pc.hips] into [npc.name]'s groin in time with [npc.her] thrusts, [pc.moaning] in delight as [npc.her] [npc.cock+] thrusts deep into your [pc.pussy+].",
					
					"With [pc.a_moan+], you thrust your [pc.hips] down against [npc.name], filling your [pc.pussy+] with [npc.her] [npc.cock+] and crying out in pleasure before starting to rhythmically buck back against [npc.her] thrusts.",
					
					"You let out [pc.a_moan+] as you start bucking back against [npc.name]'s thrusts, smiling up at [npc.herHim] as you help [npc.herHim] to fill your [pc.pussy+] with [npc.her] [npc.cock+].",

					"With a little [pc.moan], you start rhythmically bucking your [pc.hips] back against [npc.name], helping [npc.herHim] to fill your [pc.pussy+] with [npc.her] [npc.cock+] as you smile up at [npc.herHim].");
		}
	};
	
	public static SexAction PLAYER_BUCKS_BACK_ANAL= new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Push back";
		}

		@Override
		public String getActionDescription() {
			return "Push your [pc.ass] back against [npc.name], helping [npc.herHim] to fuck you.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You start pushing your [pc.ass] back into [npc.name]'s groin in time with [npc.her] thrusts, [pc.moaning] in delight as [npc.her] [npc.cock+] thrusts deep into your [pc.asshole+].",
					
					"With [pc.a_moan+], you thrust your [pc.ass] down against [npc.name], filling your [pc.asshole+] with [npc.her] [npc.cock+] and crying out in pleasure before starting to rhythmically buck back against [npc.her] thrusts.",
					
					"You let out [pc.a_moan+] as you start bucking back against [npc.name]'s thrusts, smiling up at [npc.herHim] as you help [npc.herHim] to fill your [pc.asshole+] with [npc.her] [npc.cock+].",

					"With a little [pc.moan], you start rhythmically bucking your [pc.ass] back against [npc.name], helping [npc.herHim] to fill your [pc.asshole+] with [npc.her] [npc.cock+] as you smile up at [npc.herHim].");
		}
	};
	

	
	public static SexAction PLAYER_GET_PARTNER_TO_GROW_PENIS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Grow cock";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to grow a demonic cock.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().getRace()==Race.DEMON
					&& !Sex.getPartner().hasPenis();
		}

		@Override
		public String getDescription() {
			return "You decide that you'd like [npc.name] to use [npc.her] transformative powers to grow a nice thick demonic cock for you."
					+ " Grinning down at [npc.herHim], you tell [npc.herHim] as much, [pc.speech(Why don't you grow a nice big cock, so we can have even more fun!)]"
					+ "</br></br>"
					+(Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS)
						?"[npc.Name] lets out a little giggle, and as you look down at [npc.her] naked groin, you see a large bump start to form beneath [npc.her] [npc.skin]."
								+ " Before you have any time to change your mind, it quickly grows out into a fat demonic cock, and as you stare down at the little wriggling bumps that press out all along its shaft,"
								+ " a little spurt of precum shoots out onto your stomach."
						:"[npc.Name] lets out a little giggle, and as you look down at [npc.her] groin, you see a huge bulge quickly form beneath the fabric of [npc.her] "
								+Sex.getPartner().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"."
								+ " Before you have any time to change your mind, [npc.name] lets out [npc.a_moan+], and you realise that [npc.she]'s now got a huge demonic cock hiding beneath [npc.her] clothing.");
		}

		@Override
		public void applyEffects() {
			Sex.getPartner().setPenisType(PenisType.DEMON_COMMON);
			Sex.getPartner().setCumProduction(CumProduction.FIVE_HUGE.getMedianValue());
			Sex.getPartner().setTesticleSize(0);
			Sex.getPartner().setPenisSize(PenisSize.FIVE_ENORMOUS.getMedianValue());
		}
	};
	
	public static SexAction PLAYER_STOP_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Stop [npc.name] from fucking you.";
		}

		@Override
		public String getDescription() {
			return "You push [npc.name] back, forcing [npc.her] [npc.cock+] to slide out of your [pc.pussy+], and ignoring the disappointed whine that [npc.she] makes.";
		}
	};
	
	public static SexAction PLAYER_STOP_ANAL_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Stop [npc.name] from fucking your ass.";
		}

		@Override
		public String getDescription() {
			return "You push [npc.name] back, forcing [npc.her] [npc.cock+] to slide out of your [npc.asshole+], and ignoring the disappointed whine that [npc.she] makes.";
		}
	};
	
	public static SexAction PLAYER_STOP_NIPPLE_PENETRATION = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Stop [npc.name] from fucking your ass.";
		}

		@Override
		public String getDescription() {
			return "You push [npc.name] back, forcing [npc.her] [npc.cock+] to slide out of your [npc.nipple+], and ignoring the disappointed whine that [npc.she] makes.";
		}
	};
	
	public static SexAction PLAYER_STOP_RIDING = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.VAGINA_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Stop riding";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name] to stop riding your [pc.cock].";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s [npc.hips], you pull [npc.herHim] up, forcing [npc.her] to step back as your [pc.cock+] slides out of [npc.her] [npc.pussy+].";
		}
	};
	
	public static SexAction PLAYER_STOP_ANAL_RIDING = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ANUS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Stop riding";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc.name] to stop riding your [pc.cock].";
		}

		@Override
		public String getDescription() {
			return "Taking hold of [npc.name]'s [npc.hips], you pull [npc.herHim] up, forcing [npc.her] to step back as your [pc.cock+] slides out of [npc.her] [npc.asshole+].";
		}
	};
}
