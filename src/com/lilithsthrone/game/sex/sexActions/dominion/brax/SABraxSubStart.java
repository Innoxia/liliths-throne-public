package com.lilithsthrone.game.sex.sexActions.dominion.brax;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.52
 * @version 0.1.8
 * @author Innoxia
 */
public class SABraxSubStart {

	// Player's actions:

	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			null,
			SexParticipantType.MISC) {
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
			
			if (Sex.getPenetrationTypeInOrifice(Sex.getActivePartner(), OrificeType.MOUTH)==(PenetrationType.PENIS)) {
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
				
			} else if(Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA)==(PenetrationType.TONGUE)) {
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
							"What an obedient beta you are!",
							"Hmm, how should your new alpha use you?")));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
			}
		}
	};
	

	
	// Partner's actions:

	public static final SexAction PARTNER_TALK_DIRTY = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null,
			SexParticipantType.MISC) {
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
					UtilText.nodeContentSB.append(UtilText.parseSpeech("I-I want to be your little bitch...", Sex.getActivePartner()));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Come on, do it already...", Sex.getActivePartner()));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("I-I want you to get started...", Sex.getActivePartner()));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Please... Can you start now?", Sex.getActivePartner()));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};
	
}
