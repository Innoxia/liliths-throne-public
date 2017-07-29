package com.base.game.sex.sexActions.dominion.brax;

import java.util.List;

import com.base.game.character.attributes.ArousalLevel;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.effects.Fetish;
import com.base.game.character.race.Race;
import com.base.game.character.race.RaceStage;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexFlags;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionPriority;
import com.base.game.sex.sexActions.SexActionType;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.52
 * @version 0.1.78
 * @author Innoxia
 */
public class SABraxDom {

	// Player's actions:

	public static SexAction PLAYER_TOUCH_SELF_FEMALE = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.VAGINA_PLAYER) {
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA))
				return "Finger self";
			else
				return "Feel groin";
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
					return "Reach back and finger yourself as Brax pounds away at your ass.";
				else
					return "Reach back and finger yourself, trying to entice Brax to penetrate your needy pussy.";
			
			} else {
				return "Get some pleasure from touching yourself.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)) {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You reach back and sink your digits into your " +Main.game.getPlayer().getVaginaName(true)+", letting out a lewd moan as you start fingering yourself.");
						break;
					case 1:
						UtilText.nodeContentSB.append("Propping yourself up on one elbow, you reach back between your legs, letting out a soft moan as you push two of your digits into your empty "+Main.game.getPlayer().getVaginaName(false)+".");
						break;
					case 2:
						UtilText.nodeContentSB.append("Sliding your fingertips down to your exposed "+Main.game.getPlayer().getVaginaName(false)+", you hear Brax let out an approving grunt as he looks down to see you pushing your digits inside.");
						break;
					default:
						UtilText.nodeContentSB.append("You hear Brax grunting in approval as he sees you eagerly sinking your fingers into your needy "+Main.game.getPlayer().getVaginaName(false)
								+", and you let out a desperate moan as you curl your digits up inside yourself and start stroking in a 'come-hither' motion.");
						break;
				}
			} else {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("Reaching back, you run your hand over your groin, pressing your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" down hard against your needy "
								+Main.game.getPlayer().getVaginaName(false)+" as you let out a little whimper.");
						break;
					case 1:
						UtilText.nodeContentSB.append("Propping yourself up on one elbow, you reach back between your legs, letting out a soft moan as you feel your "
								+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" rubbing against your pussy lips.");
						break;
					case 2:
						UtilText.nodeContentSB.append("You slide your fingertips down over your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()+", before pressing down and trying to stimulate your "
								+Main.game.getPlayer().getVaginaName(true)+" through your clothing.");
						break;
					default:
						UtilText.nodeContentSB.append("Reaching down beneath yourself with one hand, you hear Brax let out a approving grunt as he sees you desperately pressing your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+" down against your needy "+Main.game.getPlayer().getVaginaName(false)+".");
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_TOUCH_SELF_MALE = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			null) {
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS))
				return "Stroke cock";
			else
				return "Feel groin";
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
				if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
					return "Reach back and give your cock some attention as Brax pounds away at your ass.";
				else
					return "Reach back and start giving your cock some attention.";
			
			} else {
				return "Get some pleasure from touching yourself.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.hasFreeHandPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB=new StringBuilder();
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("Reaching down between your legs, you take hold of your "+Main.game.getPlayer().getPenisName(true)+", stroking up and down its length as you let out a desperate whine.");
						break;
					case 1:
						UtilText.nodeContentSB.append("Propping yourself up on one elbow, you reach back beneath yourself, teasing your fingers over the head of your "+Main.game.getPlayer().getPenisName(true)
								+" before starting to groan and sigh as you rub your thumb over the tip.");
						break;
					case 2:
						UtilText.nodeContentSB.append("Reaching down and taking hold of your "+Main.game.getPlayer().getPenisName(true)+" in one hand, you eagerly start jerking off,"
								+ " grinning happily to yourself as you hear Brax let out an approving grunt at your submissive display.");
						break;
					default:
						UtilText.nodeContentSB.append("Wrapping your fingers around your "+Main.game.getPlayer().getPenisName(true)+", you start masturbating, letting out a little cry of delight as you hear Brax grunting in approval at your submissive display.");
						break;
				}
			} else {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You run your hand down over your groin, pressing your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+" down against your "
								+Main.game.getPlayer().getPenisName(true)+" as you let out a little groan.");
						break;
					case 1:
						UtilText.nodeContentSB.append("Propping yourself up on one elbow, you slide your fingers down between your legs, letting out a little groan as you feel your "
								+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName() +" rubbing down against your "+Main.game.getPlayer().getPenisName(true)+".");
						break;
					case 2:
						UtilText.nodeContentSB.append("You slide your fingertips down over your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+", before pressing down and trying to stimulate your "
								+Main.game.getPlayer().getPenisName(true)+" through your clothing.");
						break;
					default:
						UtilText.nodeContentSB.append("Reaching down beneath yourself with one hand, you hear Brax let out a approving grunt as he sees you desperately pressing your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
								+" down against your "+Main.game.getPlayer().getPenisName(false)+".");
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_TOUCH_SELF_GENDERLESS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Stroke mound";
		}

		@Override
		public String getActionDescription() {
			return "Get some pleasure from touching your genderless mound.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina()
					&& Sex.hasFreeHandPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB=new StringBuilder();
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("Reaching back between your legs, you run your fingertips over your doll-like mound, moaning and sighing as you tease the sensitive area.");
						break;
					case 1:
						UtilText.nodeContentSB.append("You tease your fingers over the sensitive doll-like mound between your legs, sighing and whining as you stimulate yourself.");
						break;
					case 2:
						UtilText.nodeContentSB.append("With probing fingers, you reach down and start to pinch and rub at your surprisingly delicate genderless crotch, letting out a happy whine as you hear Brax grunting in approval at your submissive display.");
						break;
					default:
						UtilText.nodeContentSB.append("Despite lacking genitalia, your crotch remains a highly sensitive erogenous zone, and you eagerly start rubbing and pressing down on it with greedy fingers.");
						break;
				}
			} else {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You run your hand over your groin, pressing your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+" down against your doll-like mound as you let out a little sigh.");
						break;
					case 1:
						UtilText.nodeContentSB.append("Propping yourself up on one elbow, you push your fingers down between your legs, moaning and sighing as you feel your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+" rubbing against your genderless crotch.");
						break;
					case 2:
						UtilText.nodeContentSB.append("You slide your fingertips over your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.VAGINA).getName()
								+", before pressing down and trying to stimulate your doll-like mound through your clothing, smiling to yourself as you hear Brax grunting in approval at your submissive display.");
						break;
					default:
						UtilText.nodeContentSB.append("Reaching down beneath yourself with one hand, you hear Brax let out a approving grunt as he sees you desperately pressing your "
								+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName() +" down against your genderless mound.");
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_PINCH_NIPPLES = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PLAYER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "Play with your nipples.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getRace()==Race.WOLF_MORPH && Main.game.getPlayer().getRaceStage().compareTo(RaceStage.LESSER)>=0) {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("The little claws on the end of your wolf-like fingers are easily retracted, allowing you to comfortably pinch and squeeze at each of your hard nipples.");
						break;
					case 1:
						UtilText.nodeContentSB.append("You reach down beneath yourself, running your fingertips over each of your hard nipples before starting to pinch and squeeze at the sensitive little nubs.");
						break;
					case 2:
						if(Main.game.getPlayer().getBreastRows()==3)
							UtilText.nodeContentSB.append("Propping yourself up on one elbow, your run one of your wolf-like hands over your three pairs of tits, grabbing and squeezing at each of your hard little nipples as you let out a lewd moan.");
						else
							UtilText.nodeContentSB.append("Propping yourself up on one elbow, your run one of your wolf-like hands over your tits, grabbing and squeezing at your hard little nipples as you let out a lewd moan.");
						break;
					default:
						UtilText.nodeContentSB.append("Your nipples are just begging for some attention, and you reach back and start to pinch at them, whining in delight as Brax looks on with a lustful grin.");
						break;
				}
				
			} else {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You reach back and start playing with your hard nipples, pinching and rubbing them as you moan with arousal.");
						break;
					case 1:
						UtilText.nodeContentSB.append("Propping yourself up on one elbow, you tease your fingertips over your breasts, stopping to pinch and tug at your nipples as you moan and sigh in delight.");
						break;
					case 2:
						UtilText.nodeContentSB.append("You reach down beneath yourself, and with eager fingers, start to pinch and rub at your exposed nipples.");
						break;
					default:
						UtilText.nodeContentSB.append("Your nipples are just begging for some attention, and you reach back and start to pinch at them, whining in delight as Brax looks on with a lustful grin.");
						break;
				}
			}
			
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of " + Main.game.getPlayer().getMilkName() + " leaks out onto your fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of " + Main.game.getPlayer().getMilkName() + " leaks out onto your fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of " + Main.game.getPlayer().getMilkName() + " runs out over your fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" Your " + Main.game.getPlayer().getMilkName() + " starts to flow out over your fingers and run down your breasts.");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(Util.capitaliseSentence(Main.game.getPlayer().getMilkName()) + " starts drooling out in a little stream over your fingers.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(Util.capitaliseSentence(Main.game.getPlayer().getMilkName()) + " starts pouring out in a constant stream to run down your breasts.");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(Util.capitaliseSentence(Main.game.getPlayer().getMilkName()) + " starts pouring out in a heavy flow to quickly soak your breasts.");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_BREASTS));
		}
	};
	
	public static SexAction PLAYER_TALK_DIRTY = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
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
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null)
				return "Tell Brax that you love feeling his throbbing dog-cock deep in your pussy.";
			else if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
				return "Tell Brax that you love feeling like a slut as he sinks his throbbing dog-cock deep in your ass.";
			else
				return "Tell Brax that you can't wait for him to start fucking you.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null || Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You turn your head back, making a show of biting your lip as you look up at Brax, "
								+ UtilText.parsePlayerSpeech("~Mmm!~ Yeah! Fuck me!"));
						break;
					case 1:
						UtilText.nodeContentSB.append("You turn your head back, making a show of biting your lip as you look up at Brax, "
								+ UtilText.parsePlayerSpeech("~Aah!~ Brax! Your cock's so good!"));
						break;
					case 2:
						UtilText.nodeContentSB.append("You turn your head back, and with a desperate whine, you gaze up at Brax, "
								+ UtilText.parsePlayerSpeech("~Aah!~ Yeah! Fuck your little beta!"));
						break;
					default:
						UtilText.nodeContentSB.append("You turn your head back, and with a desperate whine, you gaze up at Brax, "
								+ UtilText.parsePlayerSpeech("~Mmm!~ I love your alpha cock!"));
						break;
				}
				
			} else {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You turn your head back, making a show of biting your lip as you look up at Brax, "
								+ UtilText.parsePlayerSpeech("Come on! Fuck your little beta already!"));
						break;
					case 1:
						UtilText.nodeContentSB.append("You turn your head back, making a show of biting your lip as you look up at Brax, "
								+ UtilText.parsePlayerSpeech("I need your big alpha cock!"));
						break;
					case 2:
						UtilText.nodeContentSB.append("You turn your head back, and with a desperate whine, you gaze up at Brax, "
								+ UtilText.parsePlayerSpeech("Please Brax! Fuck me already!"));
						break;
					default:
						UtilText.nodeContentSB.append("You turn your head back, and with a desperate whine, you gaze up at Brax, "
								+ UtilText.parsePlayerSpeech("I'll be a good "+Main.game.getPlayer().getGender().getNounYoung()+"! Please, give me your cock!"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
	
	public static SexAction PLAYER_PUSH_BACK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Push back";
		}
		@Override
		public String getActionDescription() {
			return "Start bucking back against Brax in time with his thrusts.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Bracing yourself with both hands flat on the floor, you start to push back against Brax in time with his thrusts, letting out a series of lewd moans as you help his "
							+Sex.getPartner().getPenisName(true)+" to slam deep into your "+Main.game.getPlayer().getAssholeName(true)+".",
					
					"Deciding to help Brax out a little, you start pushing yourself back in time with his thrusts, helping to push his "
							+Sex.getPartner().getPenisName(true)+" deep into your "+Main.game.getPlayer().getAssholeName(true)+".",
					
					"With a series of lewd moans, you start bucking back against Brax, helping him to slam his "
							+Sex.getPartner().getPenisName(true)+" deep into your "+Main.game.getPlayer().getAssholeName(true)+".",
					
					"Each time Brax bucks forwards, you push yourself back, letting out a desperate moan as you feel his "
							+Sex.getPartner().getPenisName(true)+" slamming deep into your "+Main.game.getPlayer().getAssholeName(true)+".");
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
	
	public static SexAction PLAYER_PUSH_BACK_ANAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Push back";
		}
		@Override
		public String getActionDescription() {
			return "Start bucking back against Brax in time with his thrusts.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Bracing yourself with both hands flat on the floor, you start to push back against Brax in time with his thrusts, letting out a series of lewd moans as you help his "
							+Sex.getPartner().getPenisName(true)+" to slam deep into your "+Main.game.getPlayer().getAssholeName(true)+".",
					
					"Deciding to help Brax out a little, you start pushing yourself back in time with his thrusts, helping to push his "
							+Sex.getPartner().getPenisName(true)+" deep into your "+Main.game.getPlayer().getAssholeName(true)+".",
					
					"With a series of lewd moans, you start bucking back against Brax, helping him to slam his "
							+Sex.getPartner().getPenisName(true)+" deep into your "+Main.game.getPlayer().getAssholeName(true)+".",
					
					"Each time Brax bucks forwards, you push yourself back, letting out a desperate moan as you feel his "
							+Sex.getPartner().getPenisName(true)+" slamming deep into your "+Main.game.getPlayer().getAssholeName(true)+".");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};
	
	// TODO look back
	
	public static SexAction PLAYER_ASK_FOR_CREAMPIE_VAGINAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Ask for creampie";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that Brax is fast approaching his orgasm. Ask him to fill your pussy with his cum.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue()
					&& !SexFlags.playerRequestedCreampie;
		}

		@Override
		public String getDescription() {
			return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you look back at Brax, "
					+UtilText.parsePlayerSpeech("Fuck! Yeah! ~Aah!~ Fill me with your cum! Don't pull out!");
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = true;
			SexFlags.playerRequestedPullOut = false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_PREGNANCY));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_PREGNANCY));
		}
	};
	
	public static SexAction PLAYER_ASK_FOR_CREAMPIE_ANAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Ask for anal creampie";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that Brax is fast approaching his orgasm. Ask him to fill your ass with his cum.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue()
					&& !SexFlags.playerRequestedCreampie;
		}

		@Override
		public String getDescription() {
			return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you look back and ask Brax for his cum, "
					+UtilText.parsePlayerSpeech("Fuck! Yeah! ~Aah!~ Fill my slutty ass with your cum!");
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = true;
			SexFlags.playerRequestedPullOut = false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL));
		}
	};
	
	public static SexAction PLAYER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Ask for pullout";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that Brax is fast approaching his orgasm. Ask him to pull out before he cums.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue()
					&& !SexFlags.playerRequestedPullOut;
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you look back at Brax,"
						+ " [pc.speech(Fuck! Pull out! I don't want your dirty cum in me!)]";
			} else {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you look back at Brax,"
						+ " [pc.speech(Fuck! Pull out! I don't want to get pregnant!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = false;
			SexFlags.playerRequestedPullOut = true;
		}
	};
	
	public static SexAction PLAYER_ASK_FOR_PULL_OUT_ANUS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Ask for pullout";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that Brax is fast approaching his orgasm. Ask him to pull out before he cums.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getPartner().getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue()
					&& !SexFlags.playerRequestedPullOut;
		}

		@Override
		public String getDescription() {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you look back at Brax,"
						+ " [pc.speech(Fuck! Just make sure you pull out!)]";
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = false;
			SexFlags.playerRequestedPullOut = true;
		}
	};
	
	public static SexAction PLAYER_ASK_FOR_VAGINAL = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Ask for vaginal";
		}

		@Override
		public String getActionDescription() {
			return "Ask Brax to start fucking your pussy.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasVagina()
					&& !Sex.getPlayerPenetrationRequests().contains(OrificeType.VAGINA_PLAYER);
		}

		@Override
		public String getDescription() {
			return "Turning your head back, you bite your lip as you look up Brax,"
					+ " [pc.speech(Please! Fuck my pussy! Come stuff your nice big cock in my little cunt!)]";
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			Sex.addPlayerPenetrationRequest(OrificeType.VAGINA_PLAYER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_PREGNANCY));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	public static SexAction PLAYER_ASK_FOR_ANAL = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Ask for anal";
		}

		@Override
		public String getActionDescription() {
			return "Ask Brax to start fucking your ass.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPlayerPenetrationRequests().contains(OrificeType.ANUS_PLAYER);
		}

		@Override
		public String getDescription() {
			return "Turning your head back, you bite your lip as you look up Brax,"
					+ " [pc.speech(Please! Fuck my slutty little ass!)]";
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			Sex.addPlayerPenetrationRequest(OrificeType.ANUS_PLAYER);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE)
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL), new ListValue<>(Fetish.FETISH_PURE_VIRGIN));
			else
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};

	// Partner's methods:

	public static SexAction PARTNER_TALK_DIRTY = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
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
			UtilText.nodeContentSB.setLength(0);

			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null) {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("Brax hilts his massive dog-cock deep in your "+Main.game.getPlayer().getVaginaName(true)+", causing you to let out a high-pitched squeal, "
								+ UtilText.parseSpeech("You love my cock, don't you slut?", Sex.getPartner()));
						break;
					case 1:
						UtilText.nodeContentSB.append("Brax hilts his massive dog-cock deep in your "+Main.game.getPlayer().getVaginaName(true)+", causing you to let out a high-pitched squeal, "
								+ UtilText.parseSpeech("That's right, bitch! Your little cunt belongs to me!", Sex.getPartner()));
						break;
					case 2:
						UtilText.nodeContentSB.append("Brax suddenly buries his throbbing shaft deep in your "+Main.game.getPlayer().getVaginaName(true)+", before leaning down to growl in your ear, "
								+ UtilText.parseSpeech("Your little cunt belongs to my fat cock, and you love it, don't you slut?", Sex.getPartner()));
						break;
					default:
						UtilText.nodeContentSB.append("Brax suddenly buries his throbbing shaft deep in your "+Main.game.getPlayer().getVaginaName(true)+", before leaning down to growl in your ear, "
								+ UtilText.parseSpeech("That's right, slut, all you're good for is pleasing your alpha's cock!", Sex.getPartner()));
						break;
				}
				
			} else if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("Brax hilts his massive dog-cock deep in your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, causing you to let out a high-pitched squeal, "
								+ UtilText.parseSpeech("What a dirty anal slut!", Sex.getPartner()));
						break;
					case 1:
						UtilText.nodeContentSB.append("Brax hilts his massive dog-cock deep in your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, causing you to let out a high-pitched squeal, "
								+ UtilText.parseSpeech("That's right bitch! Your ass belongs to me!", Sex.getPartner()));
						break;
					case 2:
						UtilText.nodeContentSB.append("Brax suddenly buries his throbbing shaft deep in your back door, before leaning down to growl in your ear, "
								+ UtilText.parseSpeech("Your ass belongs to my fat cock, and you love it, don't you slut?", Sex.getPartner()));
						break;
					default:
						UtilText.nodeContentSB.append("Brax suddenly buries his throbbing shaft deep in your back door, before leaning down to growl in your ear, "
								+ UtilText.parseSpeech("That's right, you little butt slut, all you're good for is pleasing your alpha's cock!", Sex.getPartner()));
						break;
				}
				
			} else {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You let out a desperate whimper as Brax speaks down to you, "
								+ UtilText.parseSpeech("Looking forwards to pleasing your alpha's cock, huh bitch?", Sex.getPartner()));
						break;
					case 1:
						UtilText.nodeContentSB.append("You let out a desperate whimper as Brax speaks down to you, "
								+ UtilText.parseSpeech("You can't wait to be filled with my fat cock, can you slut?", Sex.getPartner()));
						break;
					case 2:
						UtilText.nodeContentSB.append("Brax growls down to you in a rough voice, "
								+ UtilText.parseSpeech("I'm gonna make you squeal, you little beta bitch!", Sex.getPartner()));
						break;
					default:
						UtilText.nodeContentSB.append("Brax growls down to you in a rough voice, "
								+ UtilText.parseSpeech("You're gonna be a good little fuck-toy, aren't you bitch?!", Sex.getPartner()));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
	
	public static SexAction PARTNER_SLAP_ASS = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
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
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null || Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You let out a lewd squeal as Brax starts slapping your ass in time with his powerful thrusts.");
						break;
					case 1:
						if(Main.game.getPlayer().getRace()==Race.WOLF_MORPH && Main.game.getPlayer().getRaceStage().compareTo(RaceStage.LESSER)>=0)
							UtilText.nodeContentSB.append("Hilting his cock deep inside you for a moment, Brax roughly grabs your wolf-like tail and yanks it upwards,"
									+ " raising your ass up high before starting to deliver a series of stinging slaps to your exposed cheeks.");
						else
							UtilText.nodeContentSB.append("Hilting his cock deep inside you for a moment, Brax uses one hand to hold you still, while the other starts to deliver a series of stinging slaps to your exposed ass cheeks.");
						break;
					case 2:
						UtilText.nodeContentSB.append("While Brax continues to fuck you, he starts to roughly slap your ass, growling in glee as you squirm and squeal under his stinging blows.");
						break;
					default:
						if(Main.game.getPlayer().getRace()==Race.WOLF_MORPH && Main.game.getPlayer().getRaceStage().compareTo(RaceStage.LESSER)>=0)
							UtilText.nodeContentSB.append("Still ploughing away at your slutty hole, Brax grabs your wolf-like tail in one hand, roughly yanking your ass up high before starting to aggressively slap your exposed cheeks.");
						else
							UtilText.nodeContentSB.append("Still ploughing away at your slutty hole, Brax growls down that he's going to put you in your place before starting to aggressively slap your exposed ass cheeks.");
						break;
				}
				
			} else {
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append("You let out a lewd squeal as Brax starts roughly slapping your ass, and you hear him growling in glee as he watches you squirm beneath his touch.");
						break;
					case 1:
						if(Main.game.getPlayer().getRace()==Race.WOLF_MORPH && Main.game.getPlayer().getRaceStage().compareTo(RaceStage.LESSER)>=0)
							UtilText.nodeContentSB.append("With a sudden growl, Brax roughly grabs your wolf-like tail and yanks it upwards, raising your ass up high before starting to deliver a series of stinging slaps to your exposed cheeks.");
						else
							UtilText.nodeContentSB.append("With a sudden growl, Brax uses one hand to hold you still, while the other starts to deliver a series of stinging slaps to your ass.");
						break;
					case 2:
						UtilText.nodeContentSB.append("You let out a startled cry as Brax starts to roughly slap your ass, growling in glee as you squirm and squeal under his stinging blows.");
						break;
					default:
						if(Main.game.getPlayer().getRace()==Race.WOLF_MORPH && Main.game.getPlayer().getRaceStage().compareTo(RaceStage.LESSER)>=0)
							UtilText.nodeContentSB.append("You feel Brax suddenly grab your wolf-like tail, and you let out a surprised yelp as he roughly yanks upwards,"
									+ " forcing you to push your ass up high before he starts to aggressively slap your exposed cheeks.");
						else
							UtilText.nodeContentSB.append("Brax growls down that he's going to put you in your place before starting to aggressively slap your ass, causing you to squeal and cry out in pleasure and pain.");
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
	
	public static SexAction PARTNER_ROUGH_FUCKING = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
			return UtilText.returnStringAtRandom(
				"With a deep, menacing growl, Brax roughly grabs your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, before shuffling forwards a little and burying his "+Sex.getPartner().getPenisName(true)
				+" deep in your "+Main.game.getPlayer().getVaginaName(true)+". Once he's happy with his new position, he starts to furiously pump his hips back and forth, causing your desperate moans to come out in little bursts"
						+ " as he ruthlessly fucks you on the floor of his office.",
						
				(Main.game.getPlayer().getTailType()==TailType.LYCAN
					?"Grabbing your wolf-like tail, Brax starts roughly pulling you back onto his "+Sex.getPartner().getPenisName(true)+" in time with his thrusts, causing you to let out a series of lewd squeals"
						+ " as you feel his knot repeatedly bumping against your pussy lips."
					:"Grabbing your hips, Brax starts roughly pulling you back onto his "+Sex.getPartner().getPenisName(true)+" in time with his thrusts, causing you to let out a series of lewd squeals"
						+ " as you feel his knot repeatedly bumping against your pussy."),
				
				"You let out a little cry as, with a guttural grunt, Brax roughly digs his wolf-like hands into your waist. With his new leverage, he starts to rapidly slam his "
						+Sex.getPartner().getPenisName(true)+" in and out of your" + " "+Main.game.getPlayer().getVaginaName(true)+", causing you to squeal and moan as his knot rams repeatedly against your outer lips.",
						
				"With a powerful shove, Brax knocks your arms out from under you, and you find your [pc.face+] being pressed into the floor as the dominant wolf-boy stoops over you, furiously slamming his"
						+ " "+Sex.getPartner().getPenisName(true)+" in and out of your "+Main.game.getPlayer().getVaginaName(true)+" as he holds your ass up in the air.",
				
				(Main.game.getPlayer().getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()
				?"With an ominous growl, Brax suddenly grabs a fistful of your hair, using his new leverage to pull you back onto his "+Sex.getPartner().getPenisName(true)+" as he starts roughly fucking your"
								+ " "+Main.game.getPlayer().getVaginaName(true)+" at an incredible pace."
				:"With an ominous growl, Brax suddenly grabs your hips, using his new leverage to pull you back onto his "+Sex.getPartner().getPenisName(true)+" as he starts roughly fucking your"
								+ " "+Main.game.getPlayer().getVaginaName(true)+" at an incredible pace."));
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
	
	public static SexAction PARTNER_ROUGH_FUCKING_ANAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
			return UtilText.returnStringAtRandom(
					"With a deep, menacing growl, Brax roughly grabs your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, before shuffling forwards a little and burying his "+Sex.getPartner().getPenisName(true)
							+" deep in your slutty back door. Once he's happy with his new position, he starts to furiously pump his hips back and forth, causing your desperate moans to come out in little bursts"
									+ " as he ruthlessly fucks you on the floor of his office.",
							
					(Main.game.getPlayer().getTailType()==TailType.LYCAN
						?"Grabbing your wolf-like tail, Brax starts roughly pulling you back onto his "+Sex.getPartner().getPenisName(true)+" in time with his thrusts, causing you to let out a series of lewd squeals"
								+ " as you feel his knot repeatedly bumping against your asshole."
						:"Grabbing your hips, Brax starts roughly pulling you back onto his "+Sex.getPartner().getPenisName(true)+" in time with his thrusts, causing you to let out a series of lewd squeals"
								+ " as you feel his knot repeatedly bumping against your asshole."),
					
					"You let out a little cry as, with a guttural grunt, Brax roughly digs his wolf-like hands into your waist. With his new leverage, he starts to rapidly slam his "
							+Sex.getPartner().getPenisName(true)+" in and out of your" + " rear entrance, causing you to squeal and moan as his knot rams repeatedly against your asshole.",
							
					"With a powerful shove, Brax knocks your arms out from under you, and you find your [pc.face+] being pressed into the floor as the dominant wolf-boy stoops over you, furiously slamming his"
								+ " "+Sex.getPartner().getPenisName(true)+" in and out of your rear entrance as he holds your ass up in the air.",
					
					(Main.game.getPlayer().getHairRawLengthValue()>=HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()
					?"With an ominous growl, Brax suddenly grabs a fistful of your hair, using his new leverage to pull you back onto his [npc.cock+] as he starts roughly fucking your [pc.asshole] at an incredible pace."
					:"With an ominous growl, Brax suddenly grabs your hips, using his new leverage to pull you back onto his [npc.cock+] as he starts roughly fucking your [pc.asshole] at an incredible pace."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};
	
	public static SexAction PARTNER_GROPE_BREASTS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.FINGER_PARTNER,
			OrificeType.NIPPLE_PLAYER) {
		@Override
		public String getActionTitle() {
			return "";
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
			UtilText.nodeContentSB.setLength(0);
		
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append("Brax suddenly reaches down beneath you, and you let out a lewd little cry as he starts to rough grab and squeeze your "+Main.game.getPlayer().getBreastSize().getDescriptor()+" breasts.");
					break;
				case 1:
					UtilText.nodeContentSB.append("With a deep grunt, Brax bends down, reaching beneath you to fondle and grope your "+Main.game.getPlayer().getBreastSize().getDescriptor()+" breasts with his rough wolf-like hands.");
					break;
				case 2:
					if(Main.game.getPlayer().getBreastRows()==3)
						UtilText.nodeContentSB.append("You let out a little squeal as Brax greedily digs his wolf-like hands into the soft flesh of your "+Main.game.getPlayer().getBreastSize().getDescriptor()+" breasts."
								+ " He grunts approvingly into your ear as he moves his rough touch between your three pairs of tits, grabbing and squeezing at each one as you moan and writhe under his dominant touch.");
					else
						UtilText.nodeContentSB.append("You let out a little squeal as Brax greedily digs his wolf-like hands into the soft flesh of your "+Main.game.getPlayer().getBreastSize().getDescriptor()+" breasts."
								+ " He grunts approvingly into your ear as he takes a tit in each hand, grabbing and squeezing at each one as you moan and writhe under his dominant touch.");
					break;
				default:
					UtilText.nodeContentSB.append("Brax's rough, wolf-like hands suddenly reach down and grab your exposed breasts, and you find yourself moaning and squirming under his dominant touch as he roughly gropes and squeezes your chest.");
					break;
			}
			
			switch (Main.game.getPlayer().getBreastLactation()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" A small trickle of " + Main.game.getPlayer().getMilkName() + " leaks out onto Brax's fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" A small squirt of " + Main.game.getPlayer().getMilkName() + " leaks out onto Brax's fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" A trickle of " + Main.game.getPlayer().getMilkName() + " runs out over Brax's fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" Your " + Main.game.getPlayer().getMilkName() + " starts to flow out over Brax's fingers and run down your breasts.");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(Util.capitaliseSentence(Main.game.getPlayer().getMilkName()) + " starts drooling out in a little stream over Brax's fingers.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(Util.capitaliseSentence(Main.game.getPlayer().getMilkName()) + " starts pouring out in a constant stream to run down your breasts.");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(Util.capitaliseSentence(Main.game.getPlayer().getMilkName()) + " starts pouring out in a heavy flow to quickly soak your breasts.");
					break;
				default:
					break;
			}
	
			return UtilText.nodeContentSB.toString();
		}
	
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_BREASTS));
		}
	};
	
	public static SexAction PARTNER_PENETRATE_VAGINAL = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public SexActionPriority getPriority() {
	    if (Sex.getPlayerPenetrationRequests().contains(OrificeType.VAGINA_PLAYER))
				return SexActionPriority.UNIQUE_MAX;
			else
				return SexActionPriority.HIGH;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
				UtilText.nodeContentSB.append("Brax pulls back, letting his "+Sex.getPartner().getPenisName(true)+" slide out of your tight ass.</br>");
			
	    if (Sex.getPlayerPenetrationRequests().contains(OrificeType.VAGINA_PLAYER)) {
				UtilText.nodeContentSB.append("Brax growls down to you as he brings the tip of his cock up to your pussy, "
					+ UtilText.parseSpeech("You want me to fuck your little cunt, huh? Well, I like to make my little beta happy...", Main.game.getBrax())
					+ "</br>");
			}
			
			UtilText.nodeContentSB.append("Brax lets out a deep grunt, and you feel him teasing the tip of his "+Sex.getPartner().getPenisName(true)+" up and down between your folds."
					+ " Once he's got you moaning and begging for him to penetrate you already, ");
			
			if(Main.game.getPlayer().getRace()==Race.WOLF_MORPH && Main.game.getPlayer().getRaceStage().compareTo(RaceStage.LESSER)>=0)
				UtilText.nodeContentSB.append("he grabs your wolf-like tail, and with one powerful thrust, slams his cock deep into your hungry cunt.");
			else
				UtilText.nodeContentSB.append("he grabs your hips, and with one powerful thrust, slams his cock deep into your hungry cunt.");
			
			return UtilText.nodeContentSB.toString();
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
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public SexActionPriority getPriority() {
			if (Sex.getPlayerPenetrationRequests().contains(OrificeType.ANUS_PLAYER)) {
				return SexActionPriority.UNIQUE_MAX;
			} else {
				return SexActionPriority.NORMAL;
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null)
				UtilText.nodeContentSB.append("Brax pulls back, letting his "+Sex.getPartner().getPenisName(true)+" slide out of your "+Main.game.getPlayer().getVaginaName(true)+".</br>");
			
			if (Sex.getPlayerPenetrationRequests().contains(OrificeType.ANUS_PLAYER)) {
				UtilText.nodeContentSB.append("Brax growls down to you as he brings the tip of his cock up to your rear entrance, "
					+ UtilText.parseSpeech("So, you're just a horny butt slut, huh? Well, I like to make my little beta happy...", Main.game.getBrax())
					+ "</br>");
			}
			
			UtilText.nodeContentSB.append("Brax lets out a deep grunt, and you feel him teasing the tip of his "+Sex.getPartner().getPenisName(true)+" around the rim of your asshole."
					+ " Once he's got you moaning and begging for him to penetrate you already, ");
			
			if(Main.game.getPlayer().getRace()==Race.WOLF_MORPH && Main.game.getPlayer().getRaceStage().compareTo(RaceStage.LESSER)>=0)
				UtilText.nodeContentSB.append("he grabs your wolf-like tail, and with one powerful thrust, slams his cock deep into your slutty back door.");
			else
				UtilText.nodeContentSB.append("he grabs your hips, and with one powerful thrust, slams his cock deep into your slutty back door.");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};

	// Player orgasm:

	public static SexAction PLAYER_ORGASM = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You can't hold back any longer, you're going to orgasm!";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You feel a desperate heat building in your groin, and with a lewd little cry, you reach your orgasm.");
			
			if(Main.game.getPlayer().getPenisType()!=PenisType.NONE){
				switch(Main.game.getPlayer().getPenisType()){
					case CANINE:
						UtilText.nodeContentSB.append("</br>The knot at the base of your dog-like cock swells up,");
						break;
					case EQUINE:
						UtilText.nodeContentSB.append("</br>Your horse-cock start powerfully twitching,");
						break;
					case FELINE:
						UtilText.nodeContentSB.append("</br>Your cat-like cock starts twitching,");
						break;
					default:
						UtilText.nodeContentSB.append("</br>Your cock starts twitching,");
						break;
				}
	
				UtilText.nodeContentSB.append(" and as your "+Main.game.getPlayer().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Main.game.getPlayer().getPenisCumProduction()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", you feel a small trickle of cum squirting");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", you feel a small amount of cum squirting");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", you feel your cum squirting");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", you feel your cum shooting");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", you feel your cum pouring");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", you feel your cum pouring");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", you feel your cum pouring");
						break;
					default:
						break;
				}
				if(Main.game.getPlayer().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS))
						UtilText.nodeContentSB.append(" into your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+".");
					else
						UtilText.nodeContentSB.append(" out all over the floor beneath you.");
				}
			}
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null) {
				UtilText.nodeContentSB.append("</br>A desperate, shuddering heat suddenly crashes up from your pussy, and you let out a manic scream as a blinding wave of pure ecstasy washes over you."
						+ " You feel your "+Main.game.getPlayer().getVaginaName(true)+" clenching and gripping down on Brax's fat dog-like cock, and your legs start to tremble as he ruthlessly continues fucking you through your intense orgasm.");
			
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
				UtilText.nodeContentSB.append("</br>A desperate, shuddering heat suddenly crashes up from your neglected pussy, and you let out a manic scream as a blinding wave of pure ecstasy washes over you."
						+ " The sensation of Brax's fat dog-like cock pumping away at your asshole causes your legs to start to tremble, and you whine and moan as he ruthlessly continues fucking you through your intense orgasm.");
				
			} else {
				UtilText.nodeContentSB.append("</br>A desperate, shuddering heat suddenly crashes up from your neglected pussy, and you let out a manic scream as a blinding wave of pure ecstasy washes over you.");
			}
			
			UtilText.nodeContentSB.append("</br>You hear Brax let out a deep laugh as he see you climax, "
					+ UtilText.parseSpeech("Good beta slut! Let my alpha cock show you what a submissive little bitch you are!", Sex.getPartner()));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
			else
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL));
			else
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
	};
	
	// Partner orgasm:

	public static SexAction PARTNER_ORGASM_CREAMPIE = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (SexFlags.playerRequestedCreampie || (!SexFlags.playerRequestedCreampie && !SexFlags.playerRequestedPullOut));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null) {
				UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he grabs your hips before slamming his dog-like cock roughly into your "+Main.game.getPlayer().getVaginaName(true)+"."
						+ " With a final, grinding ram forwards, the fat knot at the base slips inside your pussy, and you let out a lewd scream as you feel it swell up and lock Brax's throbbing shaft in place."
						+ " The thick cock inside of you start to twitch, and without any further warning, a huge spurt of potent wolf-cum shoots out deep into your waiting womb."
						+ "</br>"
						+ "Brax hold you in place for a moment, grunting desperately as he empties his balls in your cunt."
						+ " After a short while, you feel his knot deflating, and with a wet pop, he pulls his rapidly-softening shaft out from your well-used slit.");
				
			} else {
				UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he grabs your hips before slamming his dog-like cock roughly into your rear entrance."
						+ " With a final, grinding ram forwards, the fat knot at the base slips inside your ass, and you let out a lewd scream as you feel it swell up and lock Brax's throbbing shaft in place."
						+ " The thick cock inside of you start to twitch, and without any further warning, a huge spurt of potent wolf-cum shoots out deep into your slutty back door."
						+ "</br>"
						+ "Brax hold you in place for a moment, grunting desperately as he empties his balls in your ass."
						+ " After a short while, you feel his knot deflating, and with a wet pop, he pulls his rapidly-softening shaft out from your well-used asshole.");
				
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<OrificeType> getPlayerAreasCummedIn() {
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null)
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA_PLAYER));
			else
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS_PLAYER));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
			else
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_PREGNANCY));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL));
			else
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_PREGNANCY));
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static SexAction PARTNER_ORGASM_PULL_OUT = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.playerRequestedPullOut || (!SexFlags.playerRequestedCreampie && !SexFlags.playerRequestedPullOut);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null) {
				UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he grabs your hips before sliding his dog-like cock out of your "+Main.game.getPlayer().getVaginaName(true)+".");
				
			} else {
				UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he grabs your hips before sliding his dog-like cock out of your rear entrance.");
				
			}
			
			UtilText.nodeContentSB.append(" Turning your head, you see Brax rapidly stroking his huge dog-like cock over your ass as he brings himself to his climax."
					+ " You bite your lip and give him an innocent look, which is enough to push him over the edge."
					+ " A huge spurt of potent wolf-cum shoots out all over your lower back as Brax groans and pumps away at his throbbing member."
					+(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO)!=null?" You let out a little sigh as you see his seed splattering onto your "+Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO).getName()+".":"")
					+ "</br>"
					+ "After a few moments, his balls have been totally emptied, and he smirks down at you as he admires his handiwork."
					+ " You feel his slimy cum dripping down over your asshole, and you let out a soft little moan as you realise that your fun has come to an end.");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO)!=null)
				Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO).setDirty(true);
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	// Mutual orgasms:
	
	public static SexAction MUTUAL_ORGASM = new SexAction(
			SexActionType.MUTUAL_ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Submissive";
		}

		@Override
		public String getActionDescription() {
			return "What with the situation as it is, you don't really have any choice in the manner of your orgasm...";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You feel a desperate heat building in your groin, and with a lewd little cry, you reach your orgasm.");
			
			if(Main.game.getPlayer().getPenisType()!=PenisType.NONE){
				switch(Main.game.getPlayer().getPenisType()){
					case CANINE:
						UtilText.nodeContentSB.append("</br>The knot at the base of your dog-like cock swells up,");
						break;
					case EQUINE:
						UtilText.nodeContentSB.append("</br>Your horse-cock start powerfully twitching,");
						break;
					case FELINE:
						UtilText.nodeContentSB.append("</br>Your cat-like cock starts twitching,");
						break;
					default:
						UtilText.nodeContentSB.append("</br>Your cock starts twitching,");
						break;
				}
	
				UtilText.nodeContentSB.append(" and as your "+Main.game.getPlayer().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Main.game.getPlayer().getPenisCumProduction()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", you feel a small trickle of cum squirting");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", you feel a small amount of cum squirting");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", you feel your cum squirting");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", you feel your cum shooting");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", you feel your cum pouring");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", you feel your cum pouring");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", you feel your cum pouring");
						break;
					default:
						break;
				}
				if(Main.game.getPlayer().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS))
						UtilText.nodeContentSB.append(" into your "+Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+".");
					else
						UtilText.nodeContentSB.append(" out all over the floor beneath you.");
				}
			}
			
			if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null) {
					UtilText.nodeContentSB.append("</br>A desperate, shuddering heat suddenly crashes up from your pussy, and you let out a manic scream as a blinding wave of pure ecstasy washes over you."
							+ " You feel your "+Main.game.getPlayer().getVaginaName(true)+" clenching and gripping down on Brax's fat dog-like cock, and your legs start to tremble as he ruthlessly continues fucking you through your intense orgasm.");
				
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
					UtilText.nodeContentSB.append("</br>A desperate, shuddering heat suddenly crashes up from your neglected pussy, and you let out a manic scream as a blinding wave of pure ecstasy washes over you."
							+ " The sensation of Brax's fat dog-like cock pumping away at your asshole causes your legs to start to tremble, and you whine and moan as he ruthlessly continues fucking you through your intense orgasm.");
					
				} else {
					UtilText.nodeContentSB.append("</br>A desperate, shuddering heat suddenly crashes up from your neglected pussy, and you let out a manic scream as a blinding wave of pure ecstasy washes over you.");
				}
			}
			
			UtilText.nodeContentSB.append("</br></br>");
			
			
			// Brax's orgasm:
			if(SexFlags.playerRequestedPullOut || !Sex.isAnyPenetrationHappening()) {
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null) {
					UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he grabs your hips before sliding his dog-like cock out of your "+Main.game.getPlayer().getVaginaName(true)+".");
					
				} else if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
					UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he grabs your hips before sliding his dog-like cock out of your rear entrance.");
					
				} else {
					UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he gives your ass a sharp slap.");
				}
				
				UtilText.nodeContentSB.append(" Turning your head, you see him rapidly stroking his huge wolf-like cock over your ass as he brings himself to his climax."
						+ " You bite your lip and give him an innocent look, which is enough to push him over the edge."
						+ " A huge spurt of potent wolf-cum shoots out all over your lower back as Brax groans and pumps away at his throbbing member."
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO)!=null?" You let out a little sigh as you see his seed splattering onto your "+Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO).getName()+".":"")
						+ "</br>"
						+ "After a few moments, his balls have been totally emptied, and he smirks down at you as he admires his handiwork."
						+ " You feel his slimy cum dripping down over your asshole, and you let out a soft little moan as you realise that your fun has come to an end.");
			
			} else {
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null) {
					UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he grabs your hips before slamming his dog-like cock roughly into your "+Main.game.getPlayer().getVaginaName(true)+"."
							+ " With a final, grinding ram forwards, the fat knot at the base slips inside your pussy, and you let out a lewd scream as you feel it swell up and lock Brax's throbbing shaft in place."
							+ " The thick cock inside of you start to twitch, and without any further warning, a huge spurt of potent wolf-cum shoots out deep into your waiting womb."
							+ "</br>"
							+ "Brax hold you in place for a moment, grunting desperately as he empties his balls in your cunt."
							+ " After a short while, you feel his knot deflating, and with a wet pop, he pulls his rapidly-softening shaft out from your well-used slit.");
					
				} else {
					UtilText.nodeContentSB.append("You notice Brax's grunts increasing in volume, and with a guttural shout, he grabs your hips before slamming his dog-like cock roughly into your rear entrance."
							+ " With a final, grinding ram forwards, the fat knot at the base slips inside your ass, and you let out a lewd scream as you feel it swell up and lock Brax's throbbing shaft in place."
							+ " The thick cock inside of you start to twitch, and without any further warning, a huge spurt of potent wolf-cum shoots out deep into your slutty back door."
							+ "</br>"
							+ "Brax hold you in place for a moment, grunting desperately as he empties his balls in your ass."
							+ " After a short while, you feel his knot deflating, and with a wet pop, he pulls his rapidly-softening shaft out from your well-used asshole.");
					
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.playerRequestedPullOut) {
				if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO)!=null)
					Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO).setDirty(true);	
				
			}
		}
		
		@Override
		public List<OrificeType> getPlayerAreasCummedIn() {
			if(!SexFlags.playerRequestedPullOut) {
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null)
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA_PLAYER));
				else
					return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS_PLAYER));
			
			} else
				return null;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
			} else {
				if(!SexFlags.playerRequestedPullOut)
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_PREGNANCY));
				else
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
			}
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_ANAL));
			} else {
				if(!SexFlags.playerRequestedPullOut)
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT), new ListValue<>(Fetish.FETISH_PREGNANCY));
				else
					return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
			}
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
