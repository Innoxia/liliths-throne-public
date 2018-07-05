package com.lilithsthrone.game.sex.sexActions.universal.consensual;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.69.9
 * @version 0.1.78
 * @author Innoxia
 */
public class ConChairTop {

	private static StringBuilder descriptionSB = new StringBuilder();
	
	public static final SexAction PLAYER_KISS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Kiss";
		}

		@Override
		public String getActionDescription() {
			return "Lean down and kiss [npc.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isOrificeFree(Sex.getActivePartner(), SexAreaOrifice.MOUTH) && Sex.isOrificeFree(Main.game.getPlayer(), SexAreaOrifice.MOUTH);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You lean down, taking [npc.namePos] head in your hands before pressing your [pc.lips] firmly against [npc.hers]. [npc.She] lets out a little [npc.moan] as you thrust your [pc.tongue+] into [npc.her] mouth.",
					
					"You bend down, pressing your [pc.lips+] greedily against [npc.namePos] mouth and [pc.moaning] happily as you push your tongue past [npc.her] [npc.lips+].",
					
					"As you lean down, [npc.name] eagerly raises [npc.her] head and puckers [npc.her] [npc.lips+], happily pressing [npc.her] mouth against yours as you start passionately kissing one another.",
					
					"You take hold of [npc.namePos] head, and, encouraged by the little [npc.moan] that [npc.she] fails to contain, you quickly pull [npc.herPro] forwards into a desperate, passionate kiss.");
			
		}
	};
	
	public static final SexAction PLAYER_GRIND_GROIN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Grind groin";
		}

		@Override
		public String getActionDescription() {
			return "Grind your groin down against [npc.namePos] thigh.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isAnyNonSelfOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You slide down so that you're sitting on [npc.namePos] thigh, and as [npc.she] looks up into your [pc.eyes+], you start grinding your groin down against [npc.her] [npc.leg+].",
					
					"You sit down on [npc.namePos] [npc.leg+], gazing hungrily into [npc.her] [npc.eyes+] as you start grinding your groin down against [npc.her] thigh.",
					
					"Sitting down on one of [npc.namePos] [npc.legs+], you let out [pc.a_moan+] as you start grinding your groin down against [npc.her] thigh.",
					
					"Grinding your groin down against [npc.namePos] [npc.leg+], you let out [pc.a_moan+] as you gaze hungrily into [npc.her] [npc.eyes+].");
			
		}
	};
	
	public static final SexAction PLAYER_FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(null, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Feel breasts";
		}

		@Override
		public String getActionDescription() {
			return "Feel [npc.namePos] [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getActivePartner().getBreastRawSizeValue()>=CupSize.AA.getMeasurement();
		}

		@Override
		public String getDescription() {
			if(!Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.NIPPLES)){
				
				return UtilText.returnStringAtRandom(
						"Reaching down, you greedily grope and squeeze at [npc.namePos] [npc.cupSize]-cup breasts, driving the fabric of [npc.her] "
								+Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+" into [npc.her] [npc.breasts] as [npc.she] gasps and squirms under your touch.",
								
						"[npc.NamePos] [npc.breasts+] are quite simply irresistible, and you eagerly start pressing your hands into the fabric of [npc.her] "
								+Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+" as you move your touch from breast to breast.",
						
						"[npc.NamePos] chest heaves up and down as [npc.she] pants in excitement, drawing your attention to [npc.her] [npc.breastRows] [npc.breasts+]."
								+ " [npc.She] lets out a little gasp as you reach down and start groping [npc.her] [npc.breasts+] through the fabric of [npc.her] "+Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"A [npc.moan+] escapes from between [npc.namePos] [npc.lips+] as you grope and massage [npc.her] [npc.breastRows] [npc.breasts+] through the fabric of [npc.her] "
								+Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".");
				
			}else{
				return UtilText.returnStringAtRandom(
						"Reaching down, you greedily grope and squeeze at [npc.namePos] [npc.cupSize]-cup breasts, driving your fingers into [npc.her] [npc.breasts] as [npc.she] gasps and squirms under your touch.",
								
						"[npc.NamePos] [npc.a_breasts+] are quite simply irresistible, and you eagerly start pressing your hands into the mounds of soft flesh as you move your touch from breast to breast.",
						
						"[npc.NamePos] chest heaves up and down as [npc.she] pants in excitement, drawing your attention to [npc.her] [npc.breastRows] [npc.breasts+]."
								+ " [npc.She] lets out a little gasp as you reach down and start groping [npc.her] [npc.breasts+], sinking your fingers into the flesh of [npc.her] chest as [npc.she] squirms and moans beneath you.",
						
						"A [npc.moan+] escapes from between [npc.namePos] [npc.lips+] as you grope and massage [npc.her] [npc.breastRows] [npc.breasts+], smiling greedily as you feel your fingers sinking into [npc.her] pillowy mounds.");
				
			}
		}
		
	};
	
	public static final SexAction PLAYER_PINCH_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(null, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Pinch nipples";
		}

		@Override
		public String getActionDescription() {
			return "[npc.NamePos] [npc.nipples+] are fully exposed. Start pinching and tugging at them to see how sensitive they are.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getActivePartner().hasBreasts();
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"You reach down and start to eagerly pinch and rub at [npc.name(The)]'s [npc.nipples+], quickly causing [npc.herHim] to start squealing and moaning in delight as [npc.she] begs you not to stop.",
					
					(Sex.getActivePartner().getBreastType()==BreastType.DEMON_COMMON
					?"[npc.name(The)]'s [npc.nipples] look exactly like cute little pussies, and you can't resist reaching down to tug and pinch at their outer folds."
							+ " The lewd screams and moans that suddenly erupt from [npc.her] mouth tell you that [npc.she] likes this sort of treatment."
					:"[npc.name(The)]'s [npc.nipples+] are fully on display, and you can't resist reaching down to tug and pinch at them."
							+ " The [npc.moans+] that suddenly erupt from [npc.her] mouth tell you that [npc.she] likes this sort of treatment."),
							
					(Sex.getActivePartner().getBreastType()==BreastType.DEMON_COMMON
					?"You tease your fingers around each of [npc.namePos] demonic little nipple-cunts. Not satisfied with the intensity of the resulting [npc.moans], you decide to start pinching and tugging at them instead,"
							+ " which, much to your delight, causes [npc.herHim] to let out a desperate scream of ecstasy."
					:"You tease your fingers around each of [npc.namePos] [npc.nipples]. Not satisfied with the intensity of the resulting [npc.moans], you decide to start pinching and tugging at them instead,"
						+ " which, much to your delight, causes [npc.herHim] to let out a desperate scream of ecstasy."),
							
					"[npc.name(The)]'s [npc.breastRows] [npc.breasts+] are fully exposed, and before you know what you're doing, you're pinching and squeezing [npc.her] [npc.nipples+] as [npc.she] moans beneath you."));
			
			switch (Sex.getActivePartner().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					descriptionSB.append(" As you squeeze down on [npc.her] [npc.nipples], a small trickle of [npc.milk] leaks out onto your fingertips.");
					break;
				case TWO_SMALL_AMOUNT:
					descriptionSB.append(" As you squeeze down on [npc.her] [npc.nipples], a small squirt of [npc.milk] leaks out onto your fingertips.");
					break;
				case THREE_DECENT_AMOUNT:
					descriptionSB.append(" As you squeeze down on [npc.her] [npc.nipples], a trickle of [npc.milk] runs out onto your fingertips.");
					break;
				case FOUR_LARGE_AMOUNT:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts to flow out over your fingertips as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts drooling out over your fingertips as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts pouring out over your fingertips as you greedily milk [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts pouring out over your fingertips as you greedily milk [npc.her] [npc.breasts+].");
					break;
				default:
					break;
			}
			
			return descriptionSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getActivePartner().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction FINGER_NIPPLES = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Finger nipples";
		}

		@Override
		public String getActionDescription() {
			return "[npc.name(The)]'s [npc.nipples] look to be fuckable, and you're desperately curious to find out if your fingers will fit inside...";
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"Reaching down, you circle your fingers around [npc.namePos] [npc.nipples+], before pushing forwards and sinking your digits into the warm flesh of [npc.her] breasts."
							+ " [npc.She] lets out a [npc.moan+] at the sudden penetration, and as you start eagerly fingering [npc.her] [npc.nipples], [npc.she] looks up at you and continues moaning in pleasure.",
							
					"You press your fingers down against [npc.namePos] [npc.nipples+], and, with a steady pressure, you sink your digits deep inside."
							+ " As [npc.she] cries out and pushes [npc.her] chest up into your hands, you start rapidly fingering [npc.her] [npc.breasts+].",
							
					"[npc.name(The)]'s [npc.nipples+] prove to be too tempting for you to ignore any longer."
							+ " In one swift motion, you bring your fingers down to [npc.her] [npc.breasts], pushing your fingers inside as you start fingering [npc.her] [npc.nipples+].",
							
					"Circling [npc.namePos] [npc.nipples] with your fingers, you decide to take it one step further, and, sinking your digits into [npc.her] inviting [npc.nipples],"
							+ " you start fingering [npc.her] [npc.breasts] as [npc.she] [npc.moansVerb] and sighs in delight."));
			
			switch (Sex.getActivePartner().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					descriptionSB.append(" As you start fingering [npc.her] [npc.nipples], a small trickle of [npc.milk] leaks out to run down [npc.her] [npc.breasts+].");
					break;
				case TWO_SMALL_AMOUNT:
					descriptionSB.append(" As you start fingering [npc.her] [npc.nipples], a small squirt of [npc.milk] leaks out to run down [npc.her] [npc.breasts+].");
					break;
				case THREE_DECENT_AMOUNT:
					descriptionSB.append(" As you start fingering [npc.her] [npc.nipples], a trickle of [npc.milk] runs down over [npc.her] [npc.breasts+].");
					break;
				case FOUR_LARGE_AMOUNT:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts to flow out over your fingertips as you greedily finger [npc.her] [npc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts drooling out over your fingertips as you greedily finger [npc.her] [npc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts pouring out over your fingertips as you greedily finger [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts pouring out over your fingertips as you greedily finger [npc.her] [npc.breasts+].");
					break;
				default:
					break;
			}
		
			return descriptionSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getActivePartner().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction PLAYER_KISS_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Kiss nipples";
		}

		@Override
		public String getActionDescription() {
			return "Kiss [npc.namePos] [npc.nipples].";
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"Leaning down, you press your [pc.lips] against one of [npc.namePos] [npc.nipples+]."
					+ " Darting your [pc.tongue] out, you lick and suck on each of [npc.her] [npc.nipples] as [npc.she] [npc.moansVerb] and sighs beneath you.",
							
					"You press your mouth down against one of [npc.namePos] [npc.nipples+], [pc.moaning] contentedly to yourself as you lick and suck on [npc.her] hard little [npc.nipples].",
							
					"[npc.NamePos] [npc.nipples+] are just begging for some attention, and you find yourself leaning down to kiss and suck on the hard little [npc.nipples].",
							
					"[npc.Name] lets out [npc.a_moan+] as you press your mouth down over one of [npc.her] [npc.nipples+]."
					+ " Running your tongue around [npc.her] sensitive areolae, you soon find yourself joining your own [pc.moans] with [npc.hers]."));
			
			switch (Sex.getActivePartner().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					descriptionSB.append(" As you kiss and suck on [npc.her] [npc.nipples], a small trickle of [npc.milk] leaks out into your mouth.");
					break;
				case TWO_SMALL_AMOUNT:
					descriptionSB.append(" As you kiss and suck on [npc.her] [npc.nipples], a small squirt of [npc.milk] leaks out into your mouth.");
					break;
				case THREE_DECENT_AMOUNT:
					descriptionSB.append(" As you kiss and suck on [npc.her] [npc.nipples], a trickle of [npc.milk] runs out into your mouth.");
					break;
				case FOUR_LARGE_AMOUNT:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts to flow out into your mouth as you greedily suckle at [npc.her] [npc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts drooling out into your mouth as you greedily suckle at [npc.her] [npc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts pouring out into your mouth as you greedily suckle at [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" [npc.NamePos] [npc.milk] starts pouring out into your mouth as you greedily suckle at [npc.her] [npc.breasts+].");
					break;
				default:
					break;
			}
			
			return descriptionSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.addOrificeLubrication(Sex.getActivePartner(), SexAreaOrifice.NIPPLE, LubricationType.PLAYER_SALIVA);
		}
		
		@Override
		public String applyEffectsString() {
			return Sex.getActivePartner().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction PLAYER_STOP_FINGER_NIPPLES = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop nipple fingering";
		}

		@Override
		public String getActionDescription() {
			return "Stop fingering [npc.namePos] [npc.nipples].";
		}

		@Override
		public String getDescription() {
			return "You slide your fingers up and out of [npc.namePos] [npc.nipples+], grinning down at [npc.herHim] as [npc.she] lets out a [npc.moan+].";
		}
	};
	
	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
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
			descriptionSB.setLength(0);

			descriptionSB.append(UtilText.returnStringAtRandom(
					"You grin down at [npc.name] as [npc.she] [npc.moansVerb] beneath you, ",
					"[npc.name(The)] bites [npc.her] [npc.lip] and looks up at you as you speak down to [npc.herHim], "));
			
			descriptionSB.append(Main.game.getPlayer().getDirtyTalk());
			
			return descriptionSB.toString(); 
		}
	};
	
	public static final SexAction PLAYER_ROUGH_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough talk";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc.name] that [npc.sheIs] your little fuck-toy.";
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			if(Sex.isAnyNonSelfOngoingActionHappening()){
				
				descriptionSB.append(UtilText.returnStringAtRandom(
						"Reaching down to grab [npc.her] chin, you grin down evilly at the [npc.moaning] [npc.race], ",
						"You press one hand over [npc.namePos] mouth, suppressing [npc.her] [npc.moans] as you speak down to [npc.her], "));
				
				descriptionSB.append(UtilText.returnStringAtRandom(
						"[pc.speech(That's right, you're just my slutty little fuck-toy!)]",
						"[pc.speech(Ah! I love submissive little sluts like you!)]",
						"[pc.speech(You're my little fuck-toy, understand?! You belong to me!)]",
						"[pc.speech(Yeah! You love being my little fuck-toy, don't you slut?!)]"));
			}else{
				
				descriptionSB.append(UtilText.returnStringAtRandom(
						"You grin down evilly at [npc.name], ",
						"[npc.Name] bites [npc.her] [npc.lip] and looks up at you with big eyes as you roughly grab [npc.her] [npc.hips] and speak down to [npc.her], "));
				
				descriptionSB.append(UtilText.returnStringAtRandom(
						"[pc.speech(I bet you can't wait to get fucked, can you slut?)]",
						"[pc.speech(All you're good for is nice, hard fuck, understood?)]",
						"[pc.speech(You're just my little fuck-toy now, understand?)]",
						"[pc.speech(I love worthless sluts like you!)]"));
			}
			
			return descriptionSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_PENETRATE = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Fuck [npc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Push forwards, penetrating [npc.namePos] pussy before starting to fuck [npc.herHim].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append("Lining the [pc.cockHead+] of your [pc.cock] up to the entrance of [npc.namePos] [npc.pussy], you start to push forwards, letting out a [pc.groan+] as you penetrate [npc.her] [npc.pussy+].");
			
			switch(Main.game.getPlayer().getPenisType()){
				case CANINE:
					descriptionSB.append(" Grinning down at [npc.herHim], you start bucking your hips back and forth, slamming your knot against [npc.her] pussy lips as [npc.she] [npc.moansVerb] in delight, ");
					break;
				case EQUINE:
					descriptionSB.append(" Grinning down at [npc.herHim], you start bucking your hips back and forth, sliding the [pc.cockHead] of your [pc.penis+] in and out of [npc.her] slit as [npc.she] [npc.moansVerb] in delight, ");
					break;
				case FELINE:
					descriptionSB.append(" Grinning down at [npc.herHim], you start bucking your hips back and forth, raking the little barbs that line your [pc.penis+] along the walls of"
							+ " [npc.her] [npc.pussy] as [npc.she] [npc.moansVerb] in delight, ");
					break;
				default:
					descriptionSB.append(" Grinning down at [npc.herHim], you start bucking your hips back and forth, slamming the base of your [pc.penis+] against [npc.her] pussy lips as [npc.she] [npc.moansVerb] in delight, ");
					break;
			}
			
			descriptionSB.append("[npc.speech(Fuck! Yeah! Fuck me!)]");
			
			return descriptionSB.toString();
		}
	};
	
	public static final SexAction PLAYER_NORMAL_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.namePos] [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You smile down at [npc.name] as [npc.she] [npc.moansVerb+] beneath you, completely lost in the pleasure that your [pc.cock+] is giving to [npc.herHim].",
							
					"You steadily thrust your [pc.hips] into [npc.namePos] crotch, grinning at the sound of [npc.her] [npc.moans] as your [pc.cock+] fills [npc.her] [npc.pussy+].",
							
					"As you fill [npc.namePos] [npc.pussy+] with your [pc.cock+], [npc.she] looks up at you and lets out a long, satisfied [npc.moan].",
							
					(Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.FINGER)
							?"You slide a [pc.hand] under [npc.namePos] thighs, running down to grip [npc.her] behind the knee and lifting [npc.her] [npc.leg] up slightly as you plunge your [pc.cock+] in and out of her [npc.pussy+]."
							:"You grin down at [npc.name] as you steadily pump your [pc.cock+] in and out of [npc.her] [npc.pussy+]."));
		}
	};
	
	public static final SexAction PLAYER_ROUGH_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough fuck";
		}

		@Override
		public String getActionDescription() {
			return "Start to roughly fuck [npc.name].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Sex.getActivePartner().hasHorns()
						?"Reaching down, you roughly grab [npc.namePos] [npc.horns+], using them for support as you start to rapidly slam your [pc.cock+] in and out of [npc.her] [npc.pussy+]."
						:"Reaching down, you roughly grab [npc.namePos] [npc.hips+], using them for support as you start to rapidly slam your [pc.cock+] in and out of [npc.her] [npc.pussy+]."),
							
					(Sex.getActivePartner().hasBreasts()
						?"Placing your hands down on [npc.namePos] [npc.breasts+], [npc.she] lets out a desperate whine as you lean heavily down on top of [npc.herHim],"
								+ " using the extra support to frantically thrust your eager [pc.cock] in and out of her [npc.pussy+]."
						:"Placing your hands down on [npc.namePos] chest, [npc.she] lets out a desperate whine as you lean heavily down on top of [npc.herHim],"
						+ " using the extra support to frantically thrust your eager [pc.cock] in and out of her [npc.pussy+]."),
							
					"Roughly grabbing [npc.namePos] [npc.hips+], [npc.she] lets out a startled cry as you forcefully pull [npc.herHim] into your crotch."
								+ " [npc.Her] [npc.hands] drop down for support as you manhandle [npc.herHim] forwards and back, repeatedly spearing [npc.her] [npc.pussy+] on your [pc.cock+].",
							
					(Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.FINGER)
							?"Reaching down, you grab [npc.namePos] chin, pushing your thumb into [npc.her] mouth as you roughly slam your [pc.cock+] in and out of [npc.name] [npc.pussy+]."
							:"You grin down at [npc.name] as you start roughly slamming your [pc.cock+] in and out of [npc.her] [npc.pussy+]."));
		}
		
	};
	
	public static final SexAction PLAYER_PENETRATE_ANAL = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Fuck [npc.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "Push forwards, penetrating [npc.namePos] [npc.asshole] before starting to fuck [npc.herHim].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append("Lining the [pc.cockHead+] of your [pc.cock] up to [npc.namePos] [npc.asshole], you start to push forwards, letting out a [pc.groan+] as you penetrate [npc.her] [npc.ass+].");
			
			switch(Main.game.getPlayer().getPenisType()){
				case CANINE:
					descriptionSB.append(" Grinning down at [npc.herHim], you start bucking your hips back and forth, slamming your knot against [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb] in delight, ");
					break;
				case EQUINE:
					descriptionSB.append(" Grinning down at [npc.herHim], you start bucking your hips back and forth, sliding the [pc.cockHead] of your [pc.penis+] in and out of [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb] in delight, ");
					break;
				case FELINE:
					descriptionSB.append(" Grinning down at [npc.herHim], you start bucking your hips back and forth, raking the little barbs that line your [pc.penis+] along the walls of"
							+ " [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb] in delight, ");
					break;
				default:
					descriptionSB.append(" Grinning down at [npc.herHim], you start bucking your hips back and forth, slamming the base of your [pc.penis+] against [npc.her] [npc.asshole+] as [npc.she] [npc.moansVerb] in delight, ");
					break;
			}
			
			descriptionSB.append("[npc.speech(Fuck! Yeah! Fuck me!)]");
			
			return descriptionSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_NORMAL_ANAL_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal anal";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.namePos] [npc.ass+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You smile down at [npc.name] as [npc.she] [npc.moansVerb+] beneath you, completely lost in the pleasure that your [pc.cock+] is giving to [npc.herHim].",
							
					"You steadily thrust your [pc.hips] into [npc.namePos] [npc.ass], grinning at the sound of [npc.her] [npc.moans] as your [pc.cock+] fills [npc.her] [npc.asshole+].",
							
					"As you fill [npc.namePos] [npc.asshole+] with your [pc.cock+], [npc.she] looks up at you and lets out a long, satisfied [npc.moan].",
							
					(Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.FINGER)
							?"You slide a [pc.hand] under [npc.namePos] thighs, running down to grip [npc.her] behind the knee and lifting [npc.her] [npc.leg] up slightly as you plunge your [pc.cock+] in and out of her [npc.asshole+]."
							:"You grin down at [npc.name] as you steadily pump your [pc.cock+] in and out of [npc.her] [npc.asshole+]."));
		}
		
	};
	
	public static final SexAction PLAYER_ROUGH_ANAL_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough anal";
		}

		@Override
		public String getActionDescription() {
			return "Start to roughly fuck [npc.namePos] [npc.ass].";
		}
		
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Sex.getActivePartner().hasHorns()
						?"Reaching down, you roughly grab [npc.namePos] [npc.horns+], using them for support as you start to rapidly slam your [pc.cock+] in and out of [npc.her] [npc.asshole+]."
						:"Reaching down, you roughly grab [npc.namePos] [npc.hips+], using them for support as you start to rapidly slam your [pc.cock+] in and out of [npc.her] [npc.asshole+]."),
							
					(Sex.getActivePartner().hasBreasts()
						?"Placing your hands down on [npc.namePos] [npc.breasts+], [npc.she] lets out a desperate whine as you lean heavily down on top of [npc.herHim],"
								+ " using the extra support to frantically thrust your eager [pc.cock] in and out of her [npc.asshole+]."
						:"Placing your hands down on [npc.namePos] chest, [npc.she] lets out a desperate whine as you lean heavily down on top of [npc.herHim],"
						+ " using the extra support to frantically thrust your eager [pc.cock] in and out of her [npc.asshole+]."),
							
					"Roughly grabbing [npc.namePos] [npc.hips+], [npc.she] lets out a startled cry as you forcefully pull [npc.herHim] into your crotch."
								+ " [npc.Her] [npc.hands] drop down for support as you manhandle [npc.herHim] forwards and back, repeatedly spearing [npc.her] [npc.asshole+] on your [pc.cock+].",
							
					(Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.FINGER)
							?"Reaching down, you grab [npc.namePos] chin, pushing your thumb into [npc.her] mouth as you roughly slam your [pc.cock+] in and out of [npc.name] [npc.asshole+]."
							:"You grin down at [npc.name] as you start roughly slamming your [pc.cock+] in and out of [npc.her] [npc.asshole+]."));
		}
		
	};
	
	public static final SexAction PLAYER_PENETRATE_BREASTS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Fuck nipples";
		}

		@Override
		public String getActionDescription() {
			return "Penetrate [npc.namePos] [npc.nipples] before starting to fuck [npc.her] [npc.breasts].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			return "Lifting one of your [pc.legs] up and placing it down to one side of [npc.name], you lean forwards, bringing your [pc.cock] up to [npc.her] [npc.breasts+]."
					+ " As you line the [pc.cockHead+] of your [pc.cock+] up to one of [npc.her] [npc.nipples+], [npc.name] looks up at you and bites [npc.her] [npc.lip]."
					+ " Finally getting into a comfortable position, you thrust your [pc.hips] forwards, penetrating [npc.her] [npc.nipple+] and causing [npc.herHim] to let out [npc.a_moan+] as you start fucking [npc.her] [npc.breast].";
		}
		
	};
	
	public static final SexAction PLAYER_NORMAL_NIPPLE_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Normal fuck";
		}

		@Override
		public String getActionDescription() {
			return "Concentrate on fucking [npc.namePos] [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You smile down at [npc.name] as [npc.she] [npc.moansVerb+] beneath you, completely lost in pleasure as your [pc.cock+] slides in and out of [npc.her] [npc.nipple].",
							
					"You steadily thrust your [pc.hips] into [npc.namePos] [npc.breast], grinning at the sound of [npc.her] [npc.moans] as your [pc.cock+] fills [npc.her] [npc.nipple+].",
							
					"As you fill [npc.namePos] [npc.nipple+] with your [pc.cock+], [npc.she] looks up at you and lets out a long, satisfied [npc.moan].",
							
					(Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.FINGER)
							?"You slide a [pc.hand] down to [npc.namePos] [npc.breast], lifting it up slightly as you plunge your [pc.cock+] in and out of it's [npc.nipple+]."
							:"You grin down at [npc.name] as you steadily pump your [pc.cock+] in and out of [npc.her] [npc.nipple+]."));
		}
		
	};
	
	public static final SexAction PLAYER_ROUGH_NIPPLE_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough fuck";
		}

		@Override
		public String getActionDescription() {
			return "Start to roughly fuck [npc.namePos] [npc.nipple].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Sex.getActivePartner().hasHorns()
						?"Reaching down, you roughly grab [npc.namePos] [npc.horns+], using them for support as you start to rapidly slam your [pc.cock+] in and out of [npc.her] [npc.nipple+]."
						:"Reaching down, you roughly grab [npc.namePos] [npc.hips+], using them for support as you start to rapidly slam your [pc.cock+] in and out of [npc.her] [npc.nipple+]."),
							
					"Roughly grabbing [npc.namePos] head, [npc.she] lets out a desperate whine as you lean heavily down on top of [npc.herHim],"
					+ " sinking your [pc.cock] deep into [npc.her] [npc.breast] before frantically thrusting in and out of her [npc.nipple+].",
							
					"Roughly grabbing [npc.namePos] head, [npc.she] lets out a startled cry as you start forcefully slamming your [pc.cock+] in and out of [npc.her] [npc.nipple+].",
							
					"[npc.Name] lets out a desperate [npc.moan] as you roughly slam your [pc.cock+] deep into [npc.her] [npc.nipple+]."
					+ " Drawing back, you then start rapidly fucking [npc.her] [npc.breast], [pc.groaning+] as [npc.she] squirms and sighs beneath you.");
		}
		
	};
	
	public static final SexAction PLAYER_STOP_FUCK = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.name].";
		}

		@Override
		public String getDescription() {
			return "You step back, allowing your [pc.cock+] to slide out of [npc.namePos] [npc.pussy+].";
		}
	};
	
	public static final SexAction PLAYER_STOP_ANAL_FUCK = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.namePos] ass.";
		}

		@Override
		public String getDescription() {
			return "You step back, allowing your [pc.cock+] to slide out of [npc.namePos] [npc.asshole+].";
		}
	};
	
	public static final SexAction PLAYER_STOP_NIPPLE_FUCK = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.namePos] [npc.nipples].";
		}

		@Override
		public String getDescription() {
			return "You step back, allowing your [pc.cock+] to slide out of [npc.namePos] [npc.nipple+].";
		}
	};
	
	
	public static final SexAction PLAYER_RIDE_PARTNERS_COCK = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Ride [npc.her] cock";
		}

		@Override
		public String getActionDescription() {
			return "Sit in [npc.namePos] lap and start riding [npc.her] [npc.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			return "Stepping forwards, you lower yourself down so that you're hovering over [npc.namePos] [npc.cock]."
					+ " Sensing what you're about to do, [npc.she] lets out a pleading whine, which soon turns into [npc.a_moan+] as you line your [pc.pussy+] up to [npc.her] [npc.cock] and drop down, spearing yourself on [npc.her] [npc.cock+].";
		}
	};
	
	public static final SexAction PLAYER_NORMAL_RIDE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Bounce on cock";
		}

		@Override
		public String getActionDescription() {
			return "Slowly bounce up and down on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Lifting yourself up, you smile at [npc.name] before spearing yourself down on [npc.her] [npc.cock+].",
					
					"You lift yourself up, allowing [npc.namePos] [npc.cock+] to almost slip out of your [pc.pussy+], before suddenly dropping down, filling yourself with [npc.her] [npc.cock+].",
					
					"You let out [pc.a_moan+] as you start bouncing up and down in [npc.namePos] lap, fucking yourself on [npc.her] [npc.cock+].",
					
					"With [pc.a_moan+], you start to bounce yourself up and down in [npc.namePos] lap, squealing in delight as [npc.her] [npc.cock+] fills your [pc.pussy+].");
		}
	};
	
	public static final SexAction PLAYER_ROUGH_RIDE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough ride";
		}

		@Override
		public String getActionDescription() {
			return "Start to roughly ride [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Sex.getActivePartner().hasHorns()
						?"Reaching down, you roughly grab [npc.namePos] [npc.horns+], using them for support as you start to rapidly bounce up and down on [npc.her] [npc.cock+]."
						:"Reaching down, you roughly grab [npc.namePos] [npc.hips+], using them for support as you start to rapidly bounce up and down on [npc.her] [npc.cock+]."),
							
					"Placing your hands down on [npc.namePos] [npc.breasts+], you lean into [npc.herHim] for support as you start roughly slamming your [pc.pussy+] up and down on [npc.her] [npc.cock+].",
							
					"Roughly grabbing [npc.namePos] shoulders, you let out [pc.a_moan+] as you start frantically bouncing up and down in [npc.her] lap,"
							+ " repeatedly spearing your [pc.pussy+] on [npc.her] [npc.cock+] as [npc.she] joins [npc.her] [npc.moans] with yours.",
							
					(Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.FINGER)
							?"Reaching down, you grab [npc.namePos] chin, pushing your thumb into [npc.her] mouth as you roughly bounce up and down on [npc.her] [npc.cock+]."
							:"You grin down at [npc.name] as you start roughly bouncing up and down on [npc.her] [npc.cock+]."));
		}
		
	};
	
	
	public static final SexAction PLAYER_RIDE_ANAL_PARTNERS_COCK = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Anal ride";
		}

		@Override
		public String getActionDescription() {
			return "Sit in [npc.namePos] lap and start riding [npc.her] [npc.cock] anally.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			return "Stepping forwards, you lower yourself down so that you're hovering over [npc.namePos] [npc.cock]."
					+ " Sensing what you're about to do, [npc.she] lets out a pleading whine, which soon turns into [npc.a_moan+]"
						+ " as you line your [pc.asshole+] up to [npc.her] [npc.cock] and drop down, spearing yourself on [npc.her] [npc.cock+].";
		}
		
	};
	
	public static final SexAction PLAYER_NORMAL_ANAL_RIDE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Bounce on cock";
		}

		@Override
		public String getActionDescription() {
			return "Slowly bounce up and down on [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Lifting yourself up, you smile at [npc.name] before spearing yourself down on [npc.her] [npc.cock+].",
					
					"You lift yourself up, allowing [npc.namePos] [npc.cock+] to almost slip out of your [pc.asshole+], before suddenly dropping down, filling yourself with [npc.her] [npc.cock+].",
					
					"You let out [pc.a_moan+] as you start bouncing up and down in [npc.namePos] lap, fucking your [pc.ass] on [npc.her] [npc.cock+].",
					
					"With [pc.a_moan+], you start to bounce yourself up and down in [npc.namePos] lap, squealing in delight as [npc.her] [npc.cock+] fills your [pc.asshole+].");
		}
		
	};
	
	public static final SexAction PLAYER_ROUGH_ANAL_RIDE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough ride";
		}

		@Override
		public String getActionDescription() {
			return "Start to roughly ride [npc.namePos] [npc.cock+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					(Sex.getActivePartner().hasHorns()
						?"Reaching down, you roughly grab [npc.namePos] [npc.horns+], using them for support as you start to rapidly bounce up and down on [npc.her] [npc.cock+]."
						:"Reaching down, you roughly grab [npc.namePos] [npc.hips+], using them for support as you start to rapidly bounce up and down on [npc.her] [npc.cock+]."),
							
					"Placing your hands down on [npc.namePos] [npc.breasts+], you lean into [npc.herHim] for support as you start roughly slamming your [pc.ass+] up and down on [npc.her] [npc.cock+].",
							
					"Roughly grabbing [npc.namePos] shoulders, you let out [pc.a_moan+] as you start frantically bouncing up and down in [npc.her] lap,"
							+ " repeatedly spearing your [pc.asshole+] on [npc.her] [npc.cock+] as [npc.she] joins [npc.her] [npc.moans] with yours.",
							
					(Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.FINGER)
							?"Reaching down, you grab [npc.namePos] chin, pushing your thumb into [npc.her] mouth as you roughly bounce up and down on [npc.her] [npc.cock+]."
							:"You grin down at [npc.name] as you start roughly bouncing up and down on [npc.her] [npc.cock+]."));
		}
		
	};
	
	public static final SexAction PLAYER_STOP_RIDING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop riding";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.name].";
		}

		@Override
		public String getDescription() {
			return "You lift yourself up, allowing [npc.namePos] [npc.cock+] to slide out of your [pc.pussy+].";
		}
	};
	
	public static final SexAction PLAYER_STOP_ANAL_RIDING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop riding";
		}

		@Override
		public String getActionDescription() {
			return "Stop fucking [npc.name].";
		}

		@Override
		public String getDescription() {
			return "You lift yourself up, allowing [npc.namePos] [npc.cock+] to slide out of your [pc.asshole+].";
		}
	};
	
	
	public static final SexAction PLAYER_GET_PEGGED = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Get tail-pegged";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to put [npc.her] [npc.tail] up your [pc.ass].";
		}

		@Override
		public String getDescription() {
			return "Reaching down, you grab [npc.namePos] [npc.tail+]. Guiding it around between your [pc.legs], you press the tip up against your exposed asshole, and [npc.name] lets out a happy cry as [npc.she] realises what you want."
					+ " With a sudden, forceful thrust, [npc.she] eagerly spears [npc.her] [npc.tail] deep into your [pc.ass]."
					+ (Main.game.getPlayer().hasPenis()
						?" The lewd groan that you start to release very quickly turns into a desperate gasp as you feel [npc.her] tail start to massage and stroke your prostate."
						:"");
		}
		
	};
		
	public static final SexAction PLAYER_STOP_GETTING_PEGGED = new SexAction(
		        SexActionType.STOP_ONGOING,
		        ArousalIncrease.TWO_LOW,
		        ArousalIncrease.TWO_LOW,
		        CorruptionLevel.ZERO_PURE,
				Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
		        SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
	    @Override
	    public String getActionTitle() {
		    return "Stop getting tail-pegged";
	    }
            @Override
	    public String getActionDescription() {
		    return "Get [npc.name] to pull [npc.her] [npc.tail] out of your [pc.asshole+].";
	    }
		
	    @Override
	    public String getDescription() {
		    UtilText.nodeContentSB.setLength(0);
		    
		    UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
				"Sliding [npc.namePos] [npc.tail] out of your [pc.asshole+], you let out [pc.a_moan+] as you tell [npc.herHim] to stop fucking you.",
				"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.tail] out of your [pc.asshole+]."));
		    UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
				" [npc.Name] lets out [npc.a_moan+] as you stop [npc.herHim] from fucking your [pc.asshole+].",
				" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking your [pc.asshole+]."));
		    return UtilText.nodeContentSB.toString();
	    }
	};
	
	public static final SexAction PLAYER_GET_TAIL_FUCKED = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Get tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to start fucking your [pc.pussy+] with [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return "Reaching down, you grab [npc.namePos] [npc.tail+]. Guiding it up between your [pc.legs], you press the tip against your exposed [pc.pussy], and [npc.name] lets out a happy cry as [npc.she] realises what you want."
					+ " With a sudden, forceful thrust, [npc.she] eagerly spears [npc.her] [npc.tail] deep into your [pc.pussy+]."
					+ " You let out [pc.a_moan+] at the aggressive penetration, and grip down tightly on [npc.her] [npc.tail] for a moment, shuddering in pleasure as it starts thrusting in and out of your [pc.pussy+].";
		}
	};
	
	
	
	// Partner actions:

	public static final SexAction PARTNER_KISS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return Sex.isOrificeFree(Sex.getActivePartner(), SexAreaOrifice.MOUTH) && Sex.isOrificeFree(Main.game.getPlayer(), SexAreaOrifice.MOUTH);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] leans forwards, pressing [npc.her] [npc.lips+] firmly against yours. Letting out a little [npc.moan], [npc.she] eagerly thrusts [npc.her] [npc.tongue] into your mouth.",
					
					"[npc.Name] leans up to your face, pressing [npc.her] [npc.lips+] greedily against yours, before eagerly pushing [npc.her] [npc.tongue+] into your mouth.",
					
					"Leaning forwards, [npc.name] eagerly presses [npc.her] [npc.lips+] against yours, before happily sliding [npc.her] [npc.tongue+] into your mouth.",
					
					"[npc.Name] reaches up and grabs your head, and, letting out a little [npc.moan], [npc.she] quickly pulls you down into a desperate, passionate kiss.");
			
		}
	};
	
	public static final SexAction PARTNER_TALK_DIRTY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			descriptionSB = new StringBuilder();

			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"[npc.Name] makes a show of biting [npc.her] [npc.lip] as [npc.she] looks up at you, ",
					"Gazing up at you, [npc.name] lets out [npc.a_moan+], "));
			
			descriptionSB.append(Sex.getActivePartner().getDirtyTalk());
			
			return descriptionSB.toString();
		}
	};
	
	public static final SexAction PARTNER_SUBMISSIVE_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return Sex.getSexPace(Main.game.getPlayer()) == SexPace.DOM_ROUGH && Sex.getActivePartner().hasFetish(Fetish.FETISH_SUBMISSIVE);
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Sex.getLastUsedPlayerAction() == PLAYER_ROUGH_TALK)
				return SexActionPriority.HIGH;
			
			return SexActionPriority.NORMAL;
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"[npc.Name] seems to be loving your rough treatment of [npc.herHim], and lets out [npc.a_moan+] as [npc.she] reacts to your dominant display, ",
					"[npc.Name] lets out [npc.a_moan+] as you treat [npc.herHim] in such a rough fashion, "));
			
			descriptionSB.append(UtilText.returnStringAtRandom(
					"[npc.speech(Yes! I'm your little slut! Take me however you want!)]",
					"[npc.speech(Yes! I'm yours! Use me! Do what you want with me!)]",
					"[npc.speech(Yeah! I'm your worthless little bitch! Fuck me!)]",
					"[npc.speech(I'm your worthless slut! Use me like your little fuck-toy!)]"));
			
			return descriptionSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ASKS_FOR_ROUGH_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return Sex.getSexPace(Main.game.getPlayer()) != SexPace.DOM_ROUGH
					&& Sex.getActivePartner().hasFetish(Fetish.FETISH_SUBMISSIVE);
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append(
					UtilText.returnStringAtRandom(
					"[npc.Name] looks up into your eyes, begging for you to treat [npc.herHim] badly, ",
					"[npc.Name] bites [npc.her] [npc.lip] and tries [npc.her] best to look innocent, before begging, "));
			
			descriptionSB.append(UtilText.returnStringAtRandom(
					"[npc.speech(Come on! I'm your little slut! Treat me like one!)]",
					"[npc.speech(Fuck me like a worthless slut! Please!)]",
					"[npc.speech(Treat me like a dirty slut! I love it rough!)]",
					"[npc.speech(You know you want to break me! Give me a good, rough fuck! Come on!)]"));
			
			return descriptionSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(null, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
						"Reaching up to your chest, [npc.name] starts greedily groping and squeezing at your [pc.breasts+],"
								+ " driving the fabric of your [pc.lowClothing(nipples)] into your [pc.breasts] and causing you to gasp and squirm under [npc.her] touch.",
								
						"You suddenly find that your [pc.breasts+] are the target of [npc.namePos] attention, and as [npc.she] eagerly starts pressing [npc.her] [npc.hands+]"
								+ " into the fabric of your [pc.topClothing(nipples)], you find yourself letting out [pc.a_moan+].",
						
						"[npc.NamePos] attention is suddenly drawn to your [pc.breastRows] [pc.breasts+], and you find yourself letting out [pc.a_moan+] as [npc.she] reaches up and starts groping"
						+ " your [pc.breasts+] through the fabric of your [pc.topClothing(nipples)].",
						
						"A [pc.moan+] escapes from between your [pc.lips+] as [npc.name] suddenly starts groping your [pc.breastRows] [pc.breasts+] through the fabric of your [pc.topClothing(nipples)].");
				
			}else{
				return UtilText.returnStringAtRandom(
						"Reaching up to your chest, [npc.name] starts greedily groping and squeezing at your [pc.breasts+],"
								+ " driving [npc.her] fingers into your [pc.breasts] and causing you to gasp and squirm under [npc.her] touch.",
								
						"You suddenly find that your [pc.breasts+] are the target of [npc.namePos] attention, and as [npc.she] eagerly starts pressing [npc.her] [npc.hands+]"
								+ " into your [pc.breasts+], you find yourself letting out [pc.a_moan+].",
						
						"[npc.NamePos] attention is suddenly drawn to your [pc.breastRows] [pc.breasts+], and you find yourself letting out [pc.a_moan+] as [npc.she] reaches up and starts greedily groping your chest.",
						
						"[pc.A_moan+] escapes from between your [pc.lips+] as [npc.name] suddenly starts groping and massaging your [pc.breastRows] [pc.breasts+].");
				
			}
		}
		
	};
	
	public static final SexAction PARTNER_PINCH_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(null, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"[npc.Name] reaches up and starts to eagerly pinch and rub at your [pc.nipples+], quickly causing you to release a series of [pc.moans+] as you beg [npc.herHim] not to stop.",
					
					"Your [pc.breasts+] are fully on display, and prove to be too tempting a target for [npc.name] to ignore."
					+ " Before you know what's happening, [npc.sheIs] reaching up to tug and pinch at your [pc.nipples+], causing you to let out a series of [pc.moans+].",
						
					"[npc.Name] teases [npc.her] fingers around your [pc.breastRows] [pc.nipples]."
					+ " Not satisfied with the intensity of your resulting [pc.moans], [npc.she] suddenly starts pinching and tugging at them instead, which causes you to let out a desperate [pc.moan] of ecstasy.",
							
					"Your [pc.breastRows] [pc.breasts+] lie fully exposed above [npc.name], and before you know what's happening, [npc.sheIs] pinching and squeezing your [pc.nipples+], causing you to cry out in pleasure."));
			
			switch (Main.game.getPlayer().getBreastStoredMilk()) {
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
		public String applyEffectsString() {
			return Main.game.getPlayer().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction PARTNER_KISS_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"Leaning forwards, [npc.name] presses [npc.her] [npc.lips] against one of your [pc.breasts+]."
					+ " Darting [npc.her] [npc.tongue] out, [npc.she] starts to lick and suck each of your [pc.nipples+] as [npc.she] [npc.moansVerb] into your [pc.breasts+].",
							
					"[npc.Name] pulls you forwards, pressing [npc.her] mouth up against one of your [pc.breasts+] and [npc.moaning] contentedly to [npc.herself] as [npc.she] licks and sucks on your [pc.nipples+].",
							
					"Your exposed [pc.nipples+] draw [npc.namePos] attention, and [npc.she] suddenly leans forwards, pressing [npc.her] [npc.lips+] against your [pc.breasts] as [npc.she] starts to kiss and suck on your [pc.nipples+].",
							
					"You let out [pc.a_moan+] as [npc.name] presses [npc.her] mouth up against your [pc.breasts+]."
					+ " Running [npc.her] [npc.tongue+] around your sensitive areolae, your [pc.moans] increase in intensity as [npc.she] starts kissing and nibbling on your [pc.nipples+]."));
			
			switch (Sex.getActivePartner().getBreastStoredMilk()) {
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
					descriptionSB.append(" You feel your [pc.milk] start to flow out into [npc.namePos] mouth as [npc.she] greedily suckles at your [pc.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					descriptionSB.append(" You feel your [pc.milk] drooling out into [npc.namePos] mouth as [npc.she] greedily suckles at your [pc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					descriptionSB.append(" You feel your [pc.milk] pouring out into [npc.namePos] mouth as [npc.she] greedily suckles at your [pc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					descriptionSB.append(" You feel your [pc.milk] pouring out into [npc.namePos] mouth as [npc.she] greedily suckles at your [pc.breasts+].");
					break;
				default:
					break;
			}
			
			return descriptionSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.addOrificeLubrication(Main.game.getPlayer(), SexAreaOrifice.NIPPLE, LubricationType.PARTNER_SALIVA);
		}
		
		@Override
		public String applyEffectsString() {
			return Main.game.getPlayer().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction TAIL_PARTNER_HELPS_PLAYER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return Sex.getActivePartner().isTailPrehensile() && Sex.isPenetrationTypeFree(Sex.getActivePartner(), SexAreaPenetration.TAIL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] wraps [npc.her] [npc.tail+] around one of your [pc.legs], squeezing down tightly against your [pc.legSkin+] as [npc.she] smiles up at you.",
					
					"[npc.NamePos] [npc.tail+] snakes its way around one of your [pc.legs], and as it squeezes down around your thigh, [npc.she] lets out a little [npc.moan].",
					
					"You feel [npc.Name] wrapping [npc.her] [npc.tail+] around your [pc.leg], and as you feel it press down against your [pc.legSkin], [npc.she] lets out a little [npc.moan].",
					
					"[npc.Name] curls [npc.her] [npc.tail+] up behind you, and as [npc.she] lets out a soft [npc.moan], you feel it stroke up against your [pc.ass].");
		}
	};
	
	public static final SexAction PARTNER_PEGGING_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"A devious grin flashes across [npc.namePos] face, and you let out a surprised [pc.moan] as you suddenly feel [npc.herHim] driving [npc.her] [npc.tail] deep into your [pc.ass].",
					
					"[npc.Name] winks at you, and as [npc.she] does so, you feel [npc.her] [npc.tail+] ride up hard into your [pc.asshole+], causing you to let out [pc.a_moan+].",
					
					"As [npc.she] lets out [npc.a_moan+], you suddenly feel [npc.namePos] [npc.tail] start to thrust in and out of your [pc.ass],"
							+ " and you notice a devious smile flash across [npc.her] [npc.face] as [npc.she] delights in using her [npc.tail] to fuck your [pc.asshole].",
					
					"[npc.Name] blows you a kiss, and as [npc.she] does so, you feel [npc.her] [npc.tail] roughly press up into your [pc.ass], causing you to let out [pc.a_moan+].");
		}
		
	};
	
	public static final SexAction TAIL_PARTNERFUCKING_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"A devious grin flashes across [npc.namePos] face, and you let out a surprised [pc.moan] as you suddenly feel [npc.herHim] driving [npc.her] [npc.tail] deep into your [pc.pussy+].",
					
					"[npc.Name] winks at you, and as [npc.she] does so, you feel [npc.her] [npc.tail+] ride up hard into your [pc.pussy+], causing you to let out [pc.a_moan+].",
					
					"As [npc.she] lets out [npc.a_moan+], you suddenly feel [npc.namePos] [npc.tail] start to thrust in and out of your [pc.pussy+],"
							+ " and you notice a devious smile flash across [npc.her] [npc.face] as [npc.she] delights in using her [npc.tail] to fuck you.",
					
					"[npc.Name] blows you a kiss, and as [npc.she] does so, you feel [npc.her] [npc.tail] roughly press up into your [pc.pussy+], causing you to let out [pc.a_moan+].");
		}
	};
	
	public static final SexAction PARTNER_SMOULDERING_LOOK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return Sex.getActivePartner().isFeminine()
					&& Sex.getActivePartner().getRace()==Race.DEMON
					&& Sex.getActivePartner().getRaceStage()==RaceStage.GREATER;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"As you look down at the horny demon beneath you, she puts on a smouldering look, and locks her [npc.eyes] onto yours."
							+ " You hear the faint sound of moaning somewhere within your head, and as you feel your heart hammering in your chest, you realise that [npc.name] is using her demonic powers to turn you on.",
					
					"[npc.Name] brushes a loose strand of hair from her face, and as you look down, she bites her [npc.lip] and looks up at you with her big, beautiful eyes."
							+ " You feel your heart hammering in your chest as you gaze upon the face of every man's wet dream.",
					
					"[npc.Name] bats her eyes at you, making sure that you're looking right at her before bringing one of her slender, delicate fingers up to playfully pull down on her lower lip.",
					
					"As you look down at [npc.name], she winks at you, before bringing her hand up to her lips and blowing you a kiss."
							+ " You struggle to maintain your composure as you feel your heart melting in your chest.");
			
		}
	};
	
	public static final SexAction PARTNER_PENETRATES = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			

			descriptionSB.append("[npc.Name] lets out an impatient cry, and without any further warning, [npc.she] suddenly wraps [npc.her] [npc.legs+] around your lower back."
					+ " Letting out [npc.a_moan+], [npc.she] looks up into your [pc.eyes], [npc.speech(Come on, fuck me!)]"
					+ "<br/><br/>"
					+ "Before you can react, [npc.she] uses [npc.her] [npc.legs] to pull you forwards, letting out a delighted [npc.moan] as the [pc.cockHead+] of your [pc.cock+] slips inside [npc.her] [npc.pussy+].");
			

			switch(Main.game.getPlayer().getPenisType()){
				case CANINE:
					descriptionSB.append(" All too eager to respond to [npc.her] request, you start bucking your hips back and forth, slamming your knot against [npc.her] pussy lips as [npc.she] squeals and moans in delight, "
							+ UtilText.parseSpeech("Fuck! Yeah! Fuck me!", Sex.getActivePartner()));
					break;
				case EQUINE:
					descriptionSB.append(" All too eager to respond to [npc.her] request, you start bucking your hips back and forth, sliding the wide head of your horse-like shaft in and out of [npc.her] [npc.pussy]"
							+ " as [npc.she] squeals and moans in delight, "+ UtilText.parseSpeech("Fuck! Yeah! Fuck me!", Sex.getActivePartner()));
					break;
				case FELINE:
					descriptionSB.append(" All too eager to respond to [npc.her] request, you start bucking your hips back and forth,"
							+ " raking the little barbs that line your [pc.cock] along the walls of her [npc.pussy] as [npc.she] squeals and moans in delight, "
							+ UtilText.parseSpeech("Fuck! Yeah! Fuck me!", Sex.getActivePartner()));
					break;
				default:
					descriptionSB.append(" All too eager to respond to [npc.her] request, you start bucking your hips back and forth, slamming the base of your cock against [npc.her] pussy lips as [npc.she] squeals and moans in delight, "
							+ UtilText.parseSpeech("Fuck! Yeah! Fuck me!", Sex.getActivePartner()));
					break;
			}
			
			return descriptionSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_PENETRATES_ANAL = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& (Sex.getActivePartner().hasFetish(Fetish.FETISH_ANAL_GIVING) || !Sex.getActivePartner().hasVagina());
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			

			descriptionSB.append("[npc.Name] lets out an impatient cry, and without any further warning, [npc.she] suddenly wraps [npc.her] [npc.legs+] around your lower back."
					+ " Letting out [npc.a_moan+], [npc.she] looks up into your [pc.eyes], [npc.speech(Come on, fuck my cute little ass!)]"
					+ "<br/><br/>"
					+ "Before you can react, [npc.she] uses [npc.her] [npc.legs] to pull you forwards, letting out a delighted [npc.moan] as [npc.she] forces the [pc.cockHead+] of your [pc.cock+] into [npc.her] [npc.asshole+].");
			

			switch(Main.game.getPlayer().getPenisType()){
				case CANINE:
					descriptionSB.append(" All too eager to respond to [npc.her] request, you start bucking your hips back and forth, slamming your knot against [npc.her] [npc.asshole] as [npc.she] squeals and moans in delight, "
							+ UtilText.parseSpeech("Fuck! Yeah! Fuck me!", Sex.getActivePartner()));
					break;
				case EQUINE:
					descriptionSB.append(" All too eager to respond to [npc.her] request, you start bucking your hips back and forth, sliding the wide head of your horse-like shaft in and out of [npc.her] [npc.ass]"
							+ " as [npc.she] squeals and moans in delight, "+ UtilText.parseSpeech("Fuck! Yeah! Fuck me!", Sex.getActivePartner()));
					break;
				case FELINE:
					descriptionSB.append(" All too eager to respond to [npc.her] request, you start bucking your hips back and forth,"
							+ " raking the little barbs that line your [pc.cock] along the walls of her [npc.ass] as [npc.she] squeals and moans in delight, "
							+ UtilText.parseSpeech("Fuck! Yeah! Fuck me!", Sex.getActivePartner()));
					break;
				default:
					descriptionSB.append(" All too eager to respond to [npc.her] request, you start bucking your hips back and forth, slamming the base of your cock against [npc.her] [npc.asshole] as [npc.she] squeals and moans in delight, "
							+ UtilText.parseSpeech("Fuck! Yeah! Fuck me!", Sex.getActivePartner()));
					break;
			}
			
			return descriptionSB.toString();
		}

	};
	
	public static final SexAction PARTNER_STARTS_PLAYER_RIDE = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return !Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& Sex.getActivePartner().hasFetish(Fetish.FETISH_DOMINANT);
		}

		@Override
		public String getDescription() {
			descriptionSB.setLength(0);
			
			descriptionSB.append("[npc.Name] lets out an impatient cry, and without any further warning, [npc.she] suddenly leans forwards and grabs your [pc.arms]."
					+ " With a forceful yank forwards, [npc.she] pulls you into [npc.her] lap, and as [npc.her] grip shifts to take hold of your [pc.hips], [npc.she] cries out, [npc.speech(I'll show you how to get started!)]"
					+ "<br/><br/>"
					+ "Before you can react, [npc.she] roughly pulls you down, letting out a delighted [npc.moan] as the [npc.cockHead+] of [npc.her] [npc.cock+] slips inside your [pc.pussy+].");
			

			switch(Main.game.getPlayer().getPenisType()){
				case CANINE:
					descriptionSB.append(" As you let out a surprised [pc.moan], [npc.she] quickly starts bucking [npc.her] hips up into your groin, slamming [npc.her] knot against your pussy lips as you squeal and moan in delight.");
					break;
				case EQUINE:
					descriptionSB.append(" As you let out a surprised [pc.moan], [npc.she] quickly starts bucking [npc.her] hips up into your groin, sliding the wide head of [npc.her] horse-like shaft in and out of your [pc.pussy+]"
							+ " as you squeal and moan in delight.");
					break;
				case FELINE:
					descriptionSB.append(" As you let out a surprised [pc.moan], [npc.she] quickly starts bucking [npc.her] hips up into your groin,"
							+ " raking the little barbs that line [npc.her] [npc.cock] along the walls of you [pc.pussy] as you squeal and moan in delight.");
					break;
				default:
					descriptionSB.append(" As you let out a surprised [pc.moan], [npc.she] quickly starts bucking [npc.her] hips up into your groin,"
							+ " slamming the base of [npc.her] [npc.cock+] against your pussy lips as you squeal and moan in delight");
					break;
			}
			
			return descriptionSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_LOCKS_PLAYER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"Without any warning, [npc.name] suddenly wraps [npc.her] [npc.legs] around your lower back and pulls forwards, forcing you to bury your [pc.cock+] deep into [npc.her] [npc.pussy+]."
							+(Sex.getActivePartner().getVaginaType()==VaginaType.DEMON_COMMON?" [npc.She] giggles and holds you there for a moment, massaging your [pc.cock] with [npc.her] [npc.pussy]'s little tentacles, before finally releasing you.":""),
					
					"[npc.NamePos] [npc.legs+] suddenly reach up and wrap themselves around you."
							+ " With one forceful pull, [npc.she] slams your [pc.cock+] deep into her needy [npc.pussy], letting out [npc.a_moan+] as [npc.she] leg-locks you for a few seconds before releasing you.",
					
					"[npc.Name] wraps [npc.her] [npc.legs] around you, pulling you playfully forwards as [npc.she] forces you to sink yourself deep into [npc.her] hungry [npc.pussy].",
					
					"[npc.Name] suddenly wraps [npc.her] [npc.legs+] around your lower back, before slowly, but firmly, pulling you forwards, forcing you to bury your [pc.cock+] deep in [npc.her] [npc.pussy+].");
		}
		
	};
	
	public static final SexAction PARTNER_SPREADS_LEGS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(null, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"[npc.Name] shuffles forwards a little, allowing [npc.her] to spread [npc.her] [npc.legs+] a little wider.",
					
					"[npc.Name] reaches down, and, grabbing [npc.her] [npc.legs] just under the knee, pulls them up and apart, granting you easy access to [npc.her] [npc.pussy+].",
					
					"[npc.Name] lets out a pleading [npc.moan] as [npc.she] tries to spread [npc.her] [npc.legs+] even wider for you.",
					
					"[npc.Name] shuffles about a little, repositioning [npc.herself] slightly to give you even easier access to [npc.her] needy [npc.pussy].");
		}
	};
	
	public static final SexAction PARTNER_BOUNCES = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"[npc.Name] starts bucking [npc.her] [npc.hips] up into your groin, [npc.moaning] in delight as [npc.her] [npc.cock+] thrusts deep into your [pc.pussy+].",
					
					"With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] up, filling your [pc.pussy+] with [npc.her] [npc.cock+] before starting to bounce you up and down in [npc.her] lap.",
					
					"[npc.Name] lets out [npc.a_moan+] as [npc.she] starts bouncing you up and down in [npc.her] lap, smiling up at you as [npc.she] fills your [pc.pussy+] with [npc.her] [npc.cock+].",
					
					"With a little [npc.moan], [npc.name] starts bouncing you up and down in [npc.her] lap, filling your [pc.pussy+] with [npc.her] [npc.cock+] as [npc.she] smiles up at you.");
		}
	};
	
	public static final SexAction PARTNER_ROUGH_BOUNCES = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return Sex.getActivePartner().hasFetish(Fetish.FETISH_DOMINANT);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] suddenly slams [npc.her] [npc.hips] up into your groin, [npc.moaning] in delight as [npc.her] [npc.cock+] violently thrusts deep into your [pc.pussy+].",
					
					"With [npc.a_moan+], [npc.name] violently thrusts [npc.her] [npc.hips] up, slamming the base of [npc.her] [npc.cock+] up against your [pc.pussy+] before starting to roughly bounce you up and down in [npc.her] lap.",
					
					"[npc.Name] lets out [npc.a_moan+] as [npc.she] starts roughly bouncing you up and down in [npc.her] lap, grinning up at you as [npc.she] violently slams [npc.her] [npc.cock+] deep into your [pc.pussy+].",
					
					"With [npc.a_moan+], [npc.name] starts violently bouncing you up and down in [npc.her] lap, roughly slamming [npc.her] [npc.cock+] in and out of your [pc.pussy+] as [npc.she] grins up at you.");
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_BOUNCES = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"[npc.Name] starts bucking [npc.her] [npc.hips] up into your [pc.ass], [npc.moaning] in delight as [npc.her] [npc.cock+] thrusts deep into your [pc.asshole+].",
					
					"With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] up, filling your [pc.asshole+] with [npc.her] [npc.cock+] before starting to bounce you up and down in [npc.her] lap.",
					
					"[npc.Name] lets out [npc.a_moan+] as [npc.she] starts bouncing you up and down in [npc.her] lap, smiling up at you as [npc.she] fills your [pc.asshole+] with [npc.her] [npc.cock+].",
					
					"With a little [npc.moan], [npc.name] starts bouncing you up and down in [npc.her] lap, filling your [pc.asshole+] with [npc.her] [npc.cock+] as [npc.she] smiles up at you.");
		}

	};
	
	public static final SexAction PARTNER_ROUGH_ANAL_BOUNCES = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
			return Sex.getActivePartner().hasFetish(Fetish.FETISH_DOMINANT);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] suddenly slams [npc.her] [npc.hips] up into your [pc.ass], [npc.moaning] in delight as [npc.her] [npc.cock+] violently thrusts deep into your [pc.asshole+].",
					
					"With [npc.a_moan+], [npc.name] violently thrusts [npc.her] [npc.hips] up, slamming the base of [npc.her] [npc.cock+] up against your [pc.asshole+] before starting to roughly bounce you up and down in [npc.her] lap.",
					
					"[npc.Name] lets out [npc.a_moan+] as [npc.she] starts roughly bouncing you up and down in [npc.her] lap, grinning up at you as [npc.she] violently slams [npc.her] [npc.cock+] deep into your [pc.asshole+].",
					
					"With [npc.a_moan+], [npc.name] starts violently bouncing you up and down in [npc.her] lap, roughly slamming [npc.her] [npc.cock+] in and out of your [pc.asshole+] as [npc.she] grins up at you.");
		}
		
	};
	
	public static final SexAction PARTNER_BUCKS_BACK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"[npc.Name] starts pushing [npc.her] [npc.hips] into your groin, [npc.moaning] in delight as your [pc.cock+] thrusts deep into [npc.her] [npc.pussy+].",
					
					"With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.hips] forwards, filling [npc.her] [npc.pussy+] with your [pc.cock+] before starting to rhythmically buck back against your thrusts.",
					
					"[npc.Name] lets out [npc.a_moan+] as [npc.she] starts bucking back against your thrusts, smiling up at you as [npc.she] helps you to fill [npc.her] [npc.pussy+] with your [pc.cock+].",

					"With a little [npc.moan], [npc.name] starts rhythmically bucking [npc.her] [npc.hips] back against you, helping you to fill [npc.her] [npc.pussy+] with your [pc.cock+] as [npc.she] smiles up at you.");
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PARTNER_BUCKS_BACK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
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
					"[npc.Name] starts pushing [npc.her] [npc.ass] into your groin, [npc.moaning] in delight as your [pc.cock+] thrusts deep into [npc.her] [npc.asshole+].",
					
					"With [npc.a_moan+], [npc.name] thrusts [npc.her] [npc.ass] forwards, filling [npc.her] [npc.asshole+] with your [pc.cock+] before starting to rhythmically buck back against your thrusts.",
					
					"[npc.Name] lets out [npc.a_moan+] as [npc.she] starts bucking back against your thrusts, smiling up at you as [npc.she] helps you to fill [npc.her] [npc.asshole+] with your [pc.cock+].",

					"With a little [npc.moan], [npc.name] starts rhythmically bucking [npc.her] [npc.ass] back against you, helping you to fill [npc.her] [pc.asshole+] with your [pc.cock+] as [npc.she] smiles up at you.");
		}

		@Override
		public void applyEffects() {
		}
	};
}
