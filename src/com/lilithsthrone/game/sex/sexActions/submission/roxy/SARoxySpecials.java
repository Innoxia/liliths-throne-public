package com.lilithsthrone.game.sex.sexActions.submission.roxy;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.6
 * @version 0.2.9
 * @author Innoxia
 */
public class SARoxySpecials {
	
	public static final SexAction ROXY_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
			
			if(ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getRoxy().getId())) {
				return "Roxy's moans start to increase in volume, and without warning, she suddenly reaches down to grab hold of your head, before letting her legs collapse out from under her."
						+ " Your mouth is completely smothered by her [roxy.pussy+], and as you let out a muffled cry of surprise, the rat-girl screams, [roxy.speechNoEffects(~Aah!~ Yes! Get yer fuckin' tongue in deep! ~Mmm!~ Yes!)]"
						+ "<br/><br/>"
							+ "Doing as she commands, you drive your tongue deep into the rat-girl's [roxy.pussy+], before eagerly starting to eat her out, desperate for another dose of her addictive girlcum."
							+ " Your enthusiastic response causes Roxy to suddenly clamp her thighs down around your head, and you feel her [roxy.pussy] quivering and squeezing down around your tongue as she reaches her orgasm."
							+ " [roxy.speechNoEffects(Fuck! ~Aah!~ Fuck! Yes! ~Aaah!~)]"
						+ "<br/><br/>"
							+ "As Roxy screams, a huge spurt of female ejaculate squirts out from [roxy.her] [roxy.pussy+], directly into your open mouth."
							+ " [roxy.speechNoEffects(~Aah!~)] the rat-girl screams, [roxy.speechNoEffects(Fuckin' drink it down! ~Mmm!~ Yes!)]"
						+ "<br/><br/>"
							+ "Not needing to be told twice, you hungrily gulp down Roxy's addictive juices, thrusting your tongue up into her [roxy.pussy+] to try and get as much of it into your mouth as possible."
							+ " As her slimy girl-cum slides down your throat, the desperate craving starts to creep into your mind once more, and, reaching up to grab Roxy's thighs, you pull her down onto your face."
							+ " As you start to hungrily lick up as much of the rat-girl's juices as you possibly can, she starts to come down from her climax, and, continuing to sit on your face, she starts to laugh,"
								+ " [roxy.speechNoEffects(Heh! That's right, lick it all up! Tastes good, don't it?)]"
						+ "<br/><br/>"
							+ "[pc.speechNoEffects(~Mmm!~ ~Mmmm!~)] you reply, your words muffled into the rat-girl's [roxy.pussy+]."
						+ "<br/><br/>"
							+ "[roxy.speechNoEffects(Fuck, it's fuckin' good to have my own personal pussy licker. Yer just can't get enough o' my sweet fuckin' cunt, can yer?)]"
						+ "<br/><br/>"
							+ "You can't help but let out a desperate, muffled moan into Roxy's [roxy.pussy] as you continue hungrily licking up as much of her girlcum as you possibly can."
						+ "<br/><br/>"
							+ "[roxy.speechNoEffects(I think that'll do fer now.)] Roxy laughs, [roxy.speech(Yer can always come back an give me anotha thousand flames if yer want more.)]";
				
			} else {
				return "Roxy's moans start to increase in volume, and without warning, she suddenly reaches down to grab hold of your head, before letting her legs collapse out from under her."
						+ " Your mouth is completely smothered by her [roxy.pussy+], and as you let out a muffled cry of surprise, the rat-girl screams, [roxy.speechNoEffects(~Aah!~ Yes! Get yer fuckin' tongue in deep! ~Mmm!~ Yes!)]"
						+ "<br/><br/>"
							+ "Doing as she commands, you drive your tongue deep into the rat-girl's [roxy.pussy+], before desperately starting to eat her out."
							+ " Your enthusiastic response causes Roxy to suddenly clamp her thighs down around your head, and you feel her [roxy.pussy] quivering and squeezing down around your tongue as she reaches her orgasm."
							+ " [roxy.speechNoEffects(Fuck! ~Aah!~ Fuck! Yes! ~Aaah!~)]"
							+ "<br/><br/>"
							+ "As Roxy screams, a huge spurt of female ejaculate squirts out from [roxy.her] [roxy.pussy+], directly into your open mouth."
							+ " [roxy.speechNoEffects(~Aah!~)] the rat-girl screams, [roxy.speechNoEffects(Fuckin' drink it down! ~Mmm!~ Yes!)]"
						+ "<br/><br/>"
							+ "With little option but to do as she says, you gulp down Roxy's juices, before continuing to thrust your tongue up into her [roxy.pussy+]."
							+ " As her slimy girl-cum slides down your throat, you suddenly find yourself with a desperate craving for more, and, reaching up to grab Roxy's thighs, you pull her down onto your face."
							+ " As you start to hungrily lick up as much of the rat-girl's juices as you possibly can, she starts to come down from her climax, and, continuing to sit on your face, she starts to laugh,"
								+ " [roxy.speechNoEffects(Heh! That's right, lick it all up! Tastes good, don't it?)]"
						+ "<br/><br/>"
							+ "[pc.speechNoEffects(~Mmm!~ ~Mmmm!~)] you reply, your words muffled into the rat-girl's [roxy.pussy+]."
						+ "<br/><br/>"
							+ "[roxy.speechNoEffects(Heh! Probably a bit late now, but I should let yer know sumthin. I went and bought one o' those special potions from some bitch up in Dominion."
								+ " 'Makes yer girlcum real fuckin' addictive,' she said. 'Just get some cunt to eat yer out, and they'll be beggin' to be yer permanent pussy licker,' she said."
								+ " From the way yer actin', it's lookin' like she was tellin' the truth!)]"
						+ "<br/><br/>"
							+ "You can't help but let out a desperate, muffled moan into Roxy's [roxy.pussy] as she reveals this."
							+ " The addictive nature of her girlcum is already having a profound effect on your mind, and even as she reveals the fact that you've been tricked into drinking her addictive juices,"
								+ " you can't help but continue hungrily licking and kissing her pussy."
						+ "<br/><br/>"
							+ "[roxy.speechNoEffects(Fuckin' looks like you're well an' truly addicted to my cunt now, ain't yer?)] Roxy laughs, [roxy.speech(That's enough fer me.)]";
			}
		}

		@Override
		public void applyEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.roxyAddicted, true);
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
}
