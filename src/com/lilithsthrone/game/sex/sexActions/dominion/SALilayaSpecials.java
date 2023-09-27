package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.7
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SALilayaSpecials {
	
	private static boolean isAmazonsSecretActive(GameCharacter targetedCharacter) {
		GameCharacter performingCharacter = Main.sex.getCharacterPerformingAction();
		if(targetedCharacter.hasStatusEffect("innoxia_amazons_secret")) {
			Set<GameCharacter> charactersContactingVagina = new HashSet<>(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaOrifice.VAGINA, SexAreaOrifice.VAGINA));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT));

			return !charactersContactingVagina.isEmpty();
		}
		
		return false;
	}
	
	// Demand pull out
	public static final SexAction PARTNER_DEMAND_PULL_OUT = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Pull back reminder";
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return "Condom check";
			}
			return "Pull out reminder";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
						|| (Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
								&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()))
					&& Main.sex.getCharacterTargetedForSexAction(this).getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue()
					&& !Main.sex.getCharactersRequestingPullout().keySet().contains(Main.sex.getCharacterPerformingAction())
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "While letting out desperate moans and lewd cries, [npc.name] [npc.verb(try)] to push [npc2.namePos] [npc2.hips] away from [npc.her] [npc.pussy],"
							+ " making it clear that [npc.she] doesn't want [npc2.herHim] orgasming in this position due to the effects gained from drinking a bottle of 'Amazon's Secret'.";
					
				} else {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to [npc2.name],"
							+" [npc.speech(Pull away before you orgasm! I know that you've drunk a bottle of Amazon's Secret, and I'm <b>not</b> getting pregnant!)]";
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "While letting out desperate moans and lewd cries, [npc.name] [npc.verb(try)] to push [npc2.namePos] [npc2.hips] away from [npc.her] [npc.pussy],"
							+ " making it clear that [npc.she] doesn't want [npc2.herHim] cumming inside of [npc.herHim].";
					
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to [npc2.name],"
								+" [npc.speech(Your condom's on properly, isn't it? I'm <b>not</b> getting pregnant!)]";
					}
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to [npc2.name],"
							+" [npc.speech(Just remember to pull out! I'm <b>not</b> getting pregnant!)]";
				}
			}
				
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}
	};
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& !isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					return "[npc.Name] lets out a soft [npc.moan] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case DOM_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case DOM_ROUGH:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_EAGER:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for [npc2.name] to reach [npc2.her] orgasm.";
				case SUB_RESISTING:
					return "[npc.Name] lets out [npc.a_moan+] as [npc.she] desperately tries to pull away from [npc2.name] before [npc2.she] [npc2.verb(orgasm)].";
			}
			
			return "";
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Demand pull back";
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return "Condom reassurance";
			}
			return "Demand pull out";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
					|| (Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
							&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()))
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "While letting out desperate moans and lewd cries, [npc.name] [npc.verb(try)] to push [npc2.namePos] [npc2.hips] away from [npc.her] [npc.pussy],"
							+ " making it clear that [npc.she] doesn't want [npc2.herHim] orgasming in this position due to the effects gained from drinking a bottle of 'Amazon's Secret'.";
					
				} else {
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to [npc2.name], "
							+" [npc.speech(Hey, wait! If you orgasm like this, I could get pregnant!)]";
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "While letting out desperate moans and lewd cries, [npc.name] [npc.verb(try)] to push [npc2.namePos] [npc2.hips] away from [npc.her] [npc.pussy],"
							+ " making it clear that [npc.she] doesn't want [npc2.herHim] cumming inside of [npc.herHim].";
					
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to [npc2.name],"
								+" [npc.speech(That condom had better not break! I'm <b>not</b> getting pregnant!)]";
					}
					return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to [npc2.name], "
							+ "[npc.speech(Pull out! I'm <b>not</b> getting pregnant!)]";
				}
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}
	};
	
	// Furious stop sex
	/**
	 * This should no longer ever be seen, as it was replaced by a catch for the player's orgasm override. I left it here just in case...
	 */
	public static final SexAction PARTNER_FURIOUS_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this)) || !Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return "Impregnated?!";
			}
			return "Creampied?!";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this)) || !Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return "[npc.Name] feels a deep, penetrating warmth spreading through her [lilaya.pussy+] and up into her womb. Violently shoving [npc2.name] away from her, she angrily screams, "
						+ "[npc.speechNoEffects(What the fuck?! I told you to pull back!)]";
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaCondomBroke)) {
				return "[npc.Name] feels [npc2.namePos] [npc2.cum] dripping out of her [lilaya.pussy+], and, frantically shoving [npc2.herHim] away from her, she cries out in distress, "
						+" [npc.speechNoEffects(The condom broke! No! Fuck! I could get pregnant from this!)]";
			}
			return "[npc.Name] feels [npc2.namePos] [npc2.cum] dripping out of her [lilaya.pussy+], and, violently shoving [npc2.herHim] away from her, she angrily screams, "
					+ "[npc.speechNoEffects(What the fuck?! I told you to pull out!)]";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
