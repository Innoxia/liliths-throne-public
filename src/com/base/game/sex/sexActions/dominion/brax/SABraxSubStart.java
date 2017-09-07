package com.base.game.sex.sexActions.dominion.brax;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionType;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.52
 * @version 0.1.8
 * @author Innoxia
 */
public class SABraxSubStart {

	// Player's actions:

	public static SexAction PLAYER_DIRTY_TALK = new SexAction(
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
			return "Tell Brax that he's your bitch now.";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)==(PenetrationType.PENIS_PLAYER)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Looking down at [brax.name] as [brax.he] sucks your [pc.cock], you speak down to [brax.him], ",
						"With a grin, you speak down to the [brax.race] between your legs, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Aah!~ Come on [brax.name]! You can do better than that!",
							"~Mmm!~ You know you love it really, don't you?!",
							"Aww, come on [brax.name]! Little betas like you should love sucking their alpha's cock!",
							"Come on [brax.name]! This is what being a little beta is all about!")));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)==(PenetrationType.TONGUE_PARTNER)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Looking down at [brax.name]'s submissive form between your legs, you speak down to [brax.him], ",
						"With a grin, you speak down to [brax.name], "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Aah!~ Yeah, good little beta!",
							"~Mmm!~ Keep going! Yeah, like that!",
							"Good [brax.boy] [brax.name]! Keep licking! Don't stop!",
							"~Aah!~ Yes! Keep doing it like that!")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Looking down at [brax.name]'s submissive form beneath you, you speak down to [brax.him], ",
						"With a grin, you speak down to [brax.name], "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"You're going to be a good little beta, aren't you [brax.name]?",
							"That's right, stay on your knees like a good little beta!",
							"Whan an obedient beta you are!",
							"Hmm, how should your new alpha use you?")));
			}
			
			return UtilText.nodeContentSB.toString();
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
	

	
	// Partner's actions:

	public static SexAction PARTNER_TALK_DIRTY = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
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
		public boolean isBaseRequirementsMet() {
			return !Sex.isAnyPenetrationHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Util.random.nextInt(2)){
				case 0:
					UtilText.nodeContentSB.append("Brax nervously shuffles about on his knees, mumbling into the floor, ");
					break;
				default:
					UtilText.nodeContentSB.append("With a little whine, Brax speaks up to you, ");
					break;
			}
			
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("I-I want to be your little bitch...", Sex.getPartner()));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Come on, do it already...", Sex.getPartner()));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("I-I want you to get started...", Sex.getPartner()));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Please... Can you start now?", Sex.getPartner()));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};
	
}
