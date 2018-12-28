package com.lilithsthrone.game.sex.sexActions.submission;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3
 * @version 0.3
 * @author Innoxia
 */
public class SALyssiethSpecials {

	public static final SexAction DEMON_TF_TO_MISSIONARY_DESK = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF // demon TF scene
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1
					&& Sex.getSexPositionSlot(Main.game.getNpc(Lyssieth.class))!=SexPositionSlot.MISSIONARY_DESK_DOM
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Push over desk";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc2.name] down onto [npc2.her] back over your desk and stand between [npc2.her] [npc2.legs], ready to have sex in the missionary position.";
		}

		@Override
		public String getDescription() {
			return "[lyssieth.speech(Stand up, dear,)] Lyssieth giggles, stepping back and fully sliding her cock from out of your mouth, [lyssieth.speech(Foreplay's over!)]"
					+ "</p>"
					+ "<p>"
						+ "Doing as you're told, you get to your feet, wobbling a little as you get used to the change in balance that's come from having a new tail and pair of wings."
						+ " Stepping forwards, Lyssieth takes hold of you, and, before you can say anything, she presses her lips against yours and thrusts her tongue into your cummy mouth."
						+ " As she starts passionately kissing you, she begins to run her hands up and down over your body, pressing, prodding, and gently pinching your [pc.skin] in several different places."
					+ "</p>"
					+ "<p>"
						+ "Moaning into her mouth, you suddenly start to feel your proportions shifting and changing."
						+ " The power of the demonic, corruptive cum in your stomach starts to spread out and react to the elder lilin's touch, and her wings wrap around you to hold you close as she transforms your body."
						+ " Being wrapped in the tight cocoon which her wings have formed, you can't do anything but return Lyssieth's kiss as she changes your body..."
					+ "</p>"
					+ "<p>"
						+ "After just a few minutes, her wings fold back, and, breaking off the kiss, she takes a step away from you to admire her work."
						+ " Looking down at your body, you see that you're now completely feminine, with wide, womanly hips, a large ass, and big, E-cup breasts."
						+ " Lyssieth has also altered your height and body shape, so that you're now a fit, muscular half-succubus, who stands at around five and a half feet tall."
					+ "</p>"
					+ "<p>"
						+ "#IF pc.hasFetish(FETISH_INCEST)"
						+ "#THEN [lyssieth.speech(Do you approve, my new daughter?)] Lyssieth asks, stepping forwards once again to place her hands on your hips, [lyssieth.speech(You're just as mommy likes you, now, but we're far from done.)]"
						+ "#ELSE [lyssieth.speech(Do you approve, little succubus?)] Lyssieth asks, stepping forwards once again to place her hands on your hips, [lyssieth.speech(You're just as I like, now, but we're far from done.)]"
						+ "#ENDIF"
					+ "</p>"
					+ "<p>"
						+ "#IF pc.hasFetish(FETISH_INCEST)"
						+ "#THEN [pc.speech(Yes, mommy,)] you reply, [pc.speech(I'll be your good girl.)]"
						+ "#ELSE [pc.speech(Mmm, yes,)] you reply, [pc.speech(This new body feels amazing...)]"
						+ "#ENDIF"
					+ "</p>"
					+ "<p>"
						+ "Still gripping your wide hips, Lyssieth slowly swings you around, before forcing you to take a step back towards her desk."
						+ " With a firm pressure, she pushes you down onto the wooden surface, so that you're lying on your back with your legs spread wide before her."
						+ " Placing a hand on your groin, she starts gently rubbing, and as she does so, you feel a tingling in your groin, ass, and breasts..."
					+ "</p>"
					+ "<p>"
						+ "The first things to be corrupted are your asshole and nipples, and as the tingling feeling spreads into your ass and breasts, it causes them to shift and transform into their demonic counterparts."
						+ " More alarming than these transformations, however, is what's happening to your groin."
						+ " As Lyssieth's hand circles and presses down on your crotch, your genitals shrink down and smooth over, until there's nothing but a genderless mound between your legs."
					+ "</p>"
					+ "<p>"
						+ "[lyssieth.speech(And now for the fun part,)] Lyssieth softly laughs, before starting to trace her forefinger up and down in a vertical line over your doll-like mound."
						+ " At first, her touch simply produces the usual tingling, itching feeling, but then, slowly at first, and then quickening, your groin starts to turn once more into a highly erogenous zone."
						+ " Lyssieth's strokes start to feel better and better, and as she starts gently pinching and squeezing at an extremely sensitive point between your legs, you can't help but let out a frantic squeal."
					+ "</p>"
					+ "<p>"
						+ "[lyssieth.speech(Mmm, you like that?)] Lyssieth teases, before suddenly pushing her fingers into your crotch."
						+ " Instead of the pressure that you expected, her fingers slide <i>into</i> you, and with a desperate gasp, you look down to see that you have a virgin, demonic pussy between your legs."
						+ " You can't help but bite your lip and whine as you feel just how tight it is; Lyssieth's fingers are barely managing to fit inside."
					+ "</p>"
					+ "<p>"
						+ "#IF pc.hasFetish(FETISH_INCEST)"
						+ "#THEN [lyssieth.speech(Mmm, yes, nice and tight for mommy,)] Lyssieth giggles, sliding her fingers out of your cunt, [lyssieth.speech(Now beg for me to break you in!)]"
						+ "#ELSE [lyssieth.speech(Mmm, yes, nice and tight,)] Lyssieth giggles, sliding her fingers out of your cunt, [lyssieth.speech(Now beg for me to break you in!)]"
						+ "#ENDIF"
					+ "</p>"
					+ "<p>"
						+ "#IF pc.hasFetish(FETISH_INCEST)"
						+ "#THEN [pc.speech(Please, mommy, fuck me!)] you cry, spreading your legs wide open before her."
						+ "#ELSE [pc.speech(Yes, please, fuck me!)] you cry, spreading your legs wide open before her."
						+ "#ENDIF"
					+ "</p>"
					+ "<p>"
						+ "Grinning down at you, the elder lilin steps forwards, rubbing the head of her huge cock over your tiny labia, before stepping forwards and letting her huge, heavy shaft rest on your stomach."
						+ " Biting her lip in anticipation of what's to come, she teases,"
						+ " [lyssieth.speech(It's going to be fun getting all of this inside of you!)]";
		}

		@Override
		public void applyEffects() {
			Main.game.getPlayer().setFemininity(100);
			Main.game.getPlayer().setHipSize(HipSize.FIVE_VERY_WIDE);
			Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE);
			Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			Main.game.getPlayer().setHeight(168);
			Main.game.getPlayer().setAssType(AssType.DEMON_COMMON);
			Main.game.getPlayer().setBreastType(BreastType.DEMON_COMMON);
			Main.game.getPlayer().setBreastSize(CupSize.E);
			Main.game.getPlayer().setPenisType(PenisType.NONE);
			Main.game.getPlayer().setVaginaType(VaginaType.DEMON_COMMON, true);
			Main.game.getPlayer().setVaginaVirgin(true);
			Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.ZERO_TINY);
			Main.game.getPlayer().setVaginaWetness(Wetness.SEVEN_DROOLING);
			Main.game.getPlayer().setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMinimumValue(), true);
			Main.game.getPlayer().setVaginaElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
			Main.game.getPlayer().setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPositionType.MISSIONARY_DESK,
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexPositionSlot.MISSIONARY_DESK_DOM)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_SUB))));
			
			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};

	public static final SexAction DEMON_TF_TO_MISSIONARY_DESK_GETTING_FUCKED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF // demon TF scene
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2
					&& Sex.getSexPositionSlot(Main.game.getNpc(Lyssieth.class))!=SexPositionSlot.MISSIONARY_DESK_SUB
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Swap position";
		}

		@Override
		public String getActionDescription() {
			return "Swap position with [pc.name], and let [pc.herHim] fuck you with [pc.her] new demonic cock.";
		}

		@Override
		public String getDescription() {
			return "Reaching down between your legs, and with her fat cock still stuffed inside your cunt, Lyssieth lets out a soft laugh as she starts gently pinching and prodding at the skin above your sensitive clit."
					+ " Looking down, your eyes open wide as you see a thick demonic cock of your own starting to grow out above your cum-stuffed cunt."
					+ "</p>"
					+ "<p>"
						+ "#IF pc.hasFetish(FETISH_INCEST)"
						+ "#THEN [lyssieth.speech(Mommy wants to have some fun too, you know,)] Lyssieth laughs as she you staring at your new, 16-inch demonic cock, [lyssieth.speech(I think that's big enough.)]"
						+ "#ELSE [lyssieth.speech(I want to have some fun too, you know,)] Lyssieth laughs as she you staring at your new, 16-inch demonic cock, [lyssieth.speech(I think that's big enough.)]"
						+ "#ENDIF"
					+ "</p>"
					+ "<p>"
						+ "Stepping back a little, Lyssieth slides her cock out from your pussy, and then pulls you up from off of her desk, before lying down in the spot you just vacated."
						+ " Spreading her legs, the elder lilin parts her labia with two of her fingers, presenting herself to you as she moans,"
						+ " [lyssieth.speech(Come on! Pump me full of that demonic seed of yours!)]";
		}

		@Override
		public void applyEffects() {
			Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
			Main.game.getPlayer().setPenisSize(16);
			Main.game.getPlayer().setPenisGirth(PenisGirth.THREE_THICK.getValue());
			Main.game.getPlayer().setPenisCumStorage(500);
			Main.game.getPlayer().fillCumToMaxStorage();
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPositionType.MISSIONARY_DESK,
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexPositionSlot.MISSIONARY_DESK_SUB)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_DESK_DOM))));
		}
	};
	
	public static final SexAction DEMON_TF_LYSSIETH_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
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
			return Sex.getSexManager() instanceof SMLyssiethDemonTF // demon TF scene
					&& Sex.getSexManager().isPartnerWantingToStopSex(Sex.getCharacterPerformingAction())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getDescription() {
			return "With a satisfied sigh, [npc.name] disentangles [npc.herself] from [npc2.namePos] clutches, before stating that [npc.sheHas] had enough for now.";
		}
		
		@Override
		public SexParticipantType getParticipantType() {
			return Sex.isMasturbation()?SexParticipantType.SELF:SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
