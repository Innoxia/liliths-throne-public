package com.lilithsthrone.game.sex.sexActions.baseActionsPlayer;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.90
 * @version 0.1.90
 * @author Innoxia
 */
public class PlayerPenisAss {
	
	public static final SexAction PLAYER_HOTDOGGING_TEASE = new SexAction(
			SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER) {
		
		@Override
		public String getActionTitle() {
			return "Hotdogging tease";
		}

		@Override
		public String getActionDescription() {
			return "Push [npc.name]'s ass cheeks together and tease your [pc.cockHead+] over the cleft.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing [npc.name]'s [npc.assSize] ass cheeks together, you start slowly sliding the [pc.cockHead] of your [pc.cock+] up and down over the cleft that's formed.",
							"Taking a gentle hold of [npc.name]'s [npc.ass+], you slowly push [npc.her] cheeks together, before starting to tease your [pc.cock+] over the cleft.",
							"With a gentle grasp on each of [npc.name]'s ass cheeks, you slowly push them together, before teasing the [pc.cockHead] of your [pc.cock+] up against the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pressing [npc.name]'s [npc.assSize] ass cheeks together, you start forcefully sliding the [pc.cockHead] of your [pc.cock+] up and down over the cleft that's formed.",
							"Taking a rough grip of [npc.name]'s [npc.ass+], you forcefully push [npc.her] cheeks together, before starting to tease your [pc.cock+] over the cleft.",
							"With a forceful grasp on each of [npc.name]'s ass cheeks, you roughly push them together, before grinding the [pc.cockHead] of your [pc.cock+] up against the crevice that's formed."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily pushing [npc.name]'s [npc.assSize] ass cheeks together, you start eagerly sliding the [pc.cockHead] of your [pc.cock+] up and down over the cleft that's formed.",
							"Taking a firm hold of [npc.name]'s [npc.ass+], you enthusiastically push [npc.her] cheeks together, before starting to tease your [pc.cock+] over the cleft.",
							"With a greedy grasp on each of [npc.name]'s ass cheeks, you eagerly push them together, before enthusiastically teasing the [pc.cockHead] of your [pc.cock+] up against the crevice that's formed."));
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you prepare to start using [npc.her] [npc.ass], [npc.speech(Ah, yes!)]",
							" [npc.Name] lets out a desperate [npc.moan], before pleading, [npc.speech(Yes! Use my ass!!)]",
							" [npc.Name] [npc.moansVerb] in delight as [npc.she] begs, [npc.speech(Yes! Use my ass!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from between [npc.name]'s [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",
							" [npc.Name] lets out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",
							" [npc.Name] [npc.sobsVerb] in distress as [npc.she] begs, [npc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you prepare to start using [npc.her] [npc.ass], [npc.speech(~Mmm!~)]",
							" [npc.Name] lets out a [npc.moan], before addressing you, [npc.speech(Go on! Use my ass!)]",
							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to you, [npc.speech(Come on, use my ass already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Main.game.getPlayer(), Sex.getActivePartner(), PenetrationType.PENIS_PLAYER, OrificeType.ASS_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING), new ListValue<>(Fetish.FETISH_DENIAL));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
			}
		}
	};
	
	public static final SexAction PARTNER_FORCE_COCK_OVER_ASS = new SexAction(
			SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER) {
		@Override
		public String getActionTitle() {
			return "Hotdogging tease";
		}

		@Override
		public String getActionDescription() {
			return "Push your ass cheeks together and tease [pc.name]'s [pc.cockHead+] over the cleft.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips] a little, [npc.name] reaches back and presses [npc.her] ass cheeks together, before slowly sliding the [pc.cockHead] of your [pc.cock+] up and down over the cleft that's formed.",
							"Taking a gentle hold of [npc.her] [npc.ass], [npc.name] slowly pushes [npc.her] cheeks together, before starting to tease your [pc.cock+] over the cleft.",
							"With a gentle grasp on each of [npc.her] ass cheeks, [npc.name] slowly pushes them together, before teasing the [pc.cockHead] of your [pc.cock+] up against the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips] a little, [npc.name] reaches back and roughly presses [npc.her] ass cheeks together, before forcefully sliding the [pc.cockHead] of your [pc.cock+] up and down over the cleft that's formed.",
							"Taking a firm hold of [npc.her] [npc.ass], [npc.name] roughly pushes [npc.her] cheeks together, before starting to forcefully tease your [pc.cock+] over the cleft.",
							"With a firm grasp on each of [npc.her] ass cheeks, [npc.name] forcefully pushes them together, before roughly teasing the [pc.cockHead] of your [pc.cock+] up against the crevice that's formed."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips] a little, [npc.name] reaches back and eagerly presses [npc.her] ass cheeks together, before happily sliding the [pc.cockHead] of your [pc.cock+] up and down over the cleft that's formed.",
							"Taking a firm hold of [npc.her] [npc.ass], [npc.name] slowly pushes [npc.her] cheeks together, before starting to eagerly tease your [pc.cock+] over the cleft.",
							"With a firm grasp on each of [npc.her] ass cheeks, [npc.name] eagerly pushes them together, before teasing the [pc.cockHead] of your [pc.cock+] up against the crevice that's formed."));
					break;
			}
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] bursts out from between your [pc.lips+], [pc.speech(Please! Let me use your ass already!)]",
							" You let out a desperate [pc.moan], before pleading with [npc.herHim], [pc.speech(Yes! Please, let me use your ass!)]",
							" You [pc.moan] in delight as you beg, [pc.speech(Yes! Let me use your ass! Please!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_sob+] bursts out from between your [pc.lips+], [pc.speech(No! Don't! Please! Get away from me!)]",
							" You let out a desperate [pc.sob], before pleading, [pc.speech(Please! Don't do this! Leave me alone!)]",
							" You [pc.sob] in distress as you beg, [pc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [pc.A_moan+] drifts out from between your [pc.lips+], [pc.speech(Yes, let me use your ass!)]",
							" You let out a [pc.moan], before calling out, [pc.speech(Please! Let me use your ass!)]",
							" You [pc.moan] out loud as you speak to [npc.herHim], [pc.speech(Come on, let me use your ass already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getActivePartner(), Main.game.getPlayer(), PenetrationType.PENIS_PLAYER, OrificeType.ASS_PARTNER);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_GIVING));
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL_RECEIVING), new ListValue<>(Fetish.FETISH_DENIAL));
			}
		}
	};
	
	
	public static final SexAction PLAYER_HOTDOGGING_START = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// You can't penetrate if your partner is already fucking you, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.isPenetrationTypeFree(Sex.getActivePartner(), PenetrationType.PENIS_PARTNER) && (Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl());
		}
		
		@Override
		public String getActionTitle() {
			return "Start hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Press [npc.name]'s ass cheeks together and start fucking the crevice that forms.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly pushing [npc.name]'s [npc.assSize] ass cheeks together, you press the [pc.cockHead+] of your [pc.cock+] up against [npc.her] [npc.ass],"
									+ " before slowly pushing your [pc.hips] forwards and starting to fuck the crevice that's formed.",
							"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and with a slow, steady pressure, you gently press [npc.her] cheeks together, before starting to fuck the crevice that's formed."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc.name]'s [npc.assSize] ass cheeks together, you press the [pc.cockHead+] of your [pc.cock+] up against [npc.her] [npc.ass],"
									+ " before desperately pushing your [pc.hips] forwards and starting to fuck the crevice that's formed.",
							"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and, gently pressing [npc.her] cheeks together, you start to fuck the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pressing [npc.name]'s [npc.assSize] ass cheeks together, you grind the [pc.cockHead+] of your [pc.cock+] up against [npc.her] [npc.ass],"
									+ " before forcefully pushing your [pc.hips] forwards and starting to fuck the crevice that's formed.",
							"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and, roughly pressing [npc.her] cheeks together, you start to forcefully fuck the crevice that's formed."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc.name]'s [npc.assSize] ass cheeks together, you press the [pc.cockHead+] of your [pc.cock+] up against [npc.her] [npc.ass],"
									+ " before pushing your [pc.hips] forwards and starting to fuck the crevice that's formed.",
							"You position the [pc.cockHead+] of your [pc.cock] up against [npc.name]'s [npc.ass+], and, pressing [npc.her] cheeks together, you start to fuck the crevice that's formed."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out a soft [npc.moan] as you start using [npc.her] ass, gently bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper between [npc.her] cheeks.",
							" With a soft [npc.moan], [npc.she] starts gently bucking [npc.her] [npc.hips] into your crotch, sinking your [pc.cock+] even deeper between [npc.her] cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you start using [npc.her] ass, eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper between [npc.her] cheeks.",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper between [npc.her] cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you start using [npc.her] ass, violently thrusting [npc.her] [npc.hips] back against you as [npc.she] roughly forces your [pc.cock+] even deeper between [npc.her] cheeks.",
							" With [npc.a_moan+], [npc.she] starts violently bucking [npc.her] [npc.hips] into your crotch, roughly forcing you to sink your [pc.cock+] even deeper between [npc.her] cheeks."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you start using [npc.her] ass, eagerly bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper between [npc.her] cheeks.",
							" With [npc.a_moan+], [npc.she] starts eagerly bucking [npc.her] [npc.hips] into your crotch, desperately helping to sink your [pc.cock+] even deeper between [npc.her] cheeks."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_moan+] as you start using [npc.her] ass, bucking [npc.her] [npc.hips] back against you as [npc.she] helps to sink your [pc.cock+] even deeper between [npc.her] cheeks.",
							" With [npc.a_moan+], [npc.she] starts bucking [npc.her] [npc.hips] into your crotch, helping to sink your [pc.cock+] even deeper between [npc.her] cheeks."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.She] lets out [npc.a_sob+] as you start using [npc.her] ass, and, with tears running down [npc.her] [npc.face], [npc.she] begs for you to stop as [npc.she] weakly struggles against you.",
							" With [npc.a_sob+], [npc.she] tries, in vain, to pull away; tears running down [npc.her] [npc.face] as your unwelcome [pc.cock] pushes deep between [npc.her] cheeks."));
					break;
				default:
					break;
			}
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_HOTDOGGING_DOM_GENTLE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			SexPace.DOM_GENTLE,
			null) {
		@Override
		public String getActionTitle() {
			return "Gentle hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] greedily pushing back against you as you softly fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you slowly push your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you feel [npc.herHim] pushing [npc.her] [npc.hips] back against you as [npc.she] begs for you to continue.",
							"Sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to gently buck your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] [npc.moansVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.cock+] between [npc.name]'s ass cheeks, [npc.she] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to stop.",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as you slowly push your [pc.cock+] between [npc.her] ass cheeks,"
									+ " tears streaming down [npc.her] [npc.face] as [npc.she] pleads for you to leave [npc.herHim] alone.",
							"Sliding your [pc.cock+] between [npc.name]'s ass cheeks, you gently buck your [pc.hips] into [npc.her] ass cleavage as [npc.she] weakly struggles against you, pleading and crying for you to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start bucking your [pc.hips], grinning to yourself as you softly fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you slowly push your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you soon find your own [pc.moans] joining with [npc.hers] as [npc.she] begs for you to continue.",
							"Sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to gently buck your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] lets out [npc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_HOTDOGGING_DOM_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			SexPace.DOM_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Normal hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] greedily pushing back against you as you enthusiastically fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you eagerly thrust your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you feel [npc.herHim] pushing [npc.her] [npc.hips] back against you as [npc.she] begs for you to continue.",
							"Eagerly sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to rapidly thrust your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] [npc.moansVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] between [npc.name]'s ass cheeks, [npc.she] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to stop.",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as you eagerly push your [pc.cock+] between [npc.her] ass cheeks,"
									+ " tears streaming down [npc.her] [npc.face] as [npc.she] pleads for you to leave [npc.herHim] alone.",
							"Eagerly sliding your [pc.cock+] between [npc.name]'s ass cheeks, you rapidly buck your [pc.hips] into [npc.her] ass cleavage as [npc.she] weakly struggles against you, pleading and crying for you to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start rapidly bucking your [pc.hips],"
									+ " grinning to yourself as you greedily fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you eagerly push your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you soon find your own [pc.moans] joining with [npc.hers] as [npc.she] begs for you to continue.",
							"Eagerly sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to frantically buck your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] lets out [npc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_HOTDOGGING_DOM_ROUGH = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public String getActionTitle() {
			return "Rough hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming your [pc.cock+] between [npc.name]'s ass cheeks, you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start violently pumping your [pc.hips],"
									+ " grinning as you feel [npc.herHim] greedily pushing back against you as you enthusiastically fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you violently thrust your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you feel [npc.herHim] pushing [npc.her] [npc.hips] back against you as [npc.she] begs for you to continue.",
							"Ruthlessly thrusting your [pc.cock+] between [npc.name]'s ass cheeks, you start to violently buck your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] [npc.moansVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming your [pc.cock+] between [npc.name]'s ass cheeks, [npc.she] lets out [npc.a_sob+], tears streaming down [npc.her] face as [npc.she] weakly begs for you to stop.",
							"[npc.A_sob+] bursts out from between [npc.name]'s [npc.lips] as you violently thrust your [pc.cock+] between [npc.her] ass cheeks,"
									+ " tears streaming down [npc.her] [npc.face] as [npc.she] pleads for you to leave [npc.herHim] alone.",
							"Ruthlessly thrusting your [pc.cock+] between [npc.name]'s ass cheeks, you violently buck your [pc.hips] into [npc.her] ass cleavage as [npc.she] weakly struggles against you, pleading and crying for you to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming your [pc.cock+] between [npc.name]'s ass cheeks, you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start violently pumping your [pc.hips],"
									+ " grinning to yourself as you greedily fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you violently thrust your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you soon find your own [pc.moans] joining with [npc.hers] as [npc.she] begs for you to continue.",
							"Ruthlessly thrusting your [pc.cock+] between [npc.name]'s ass cheeks, you start to violently buck your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] lets out [npc.a_moan+]."));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_HOTDOGGING_SUB_NORMAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			SexPace.SUB_NORMAL,
			null) {
		@Override
		public String getActionTitle() {
			return "Normal hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc.name]'s ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] gently pushing back against you as you fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you thrust your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you feel [npc.herHim] gently pushing [npc.her] [npc.hips] back against you as [npc.she] begs for you to continue.",
							"Sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to thrust your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] softly [npc.moansVerb] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] roughly pushing back against you as you fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you thrust your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you feel [npc.herHim] roughly pushing [npc.her] [npc.hips] back against you as [npc.she] orders you to continue.",
							"Sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to thrust your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] [npc.moansVerb+] in pleasure."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning to yourself as you fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you push your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you soon find your own [pc.moans] joining with [npc.hers] as [npc.she] begs for you to continue.",
							"Sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to buck your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] lets out [npc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_HOTDOGGING_SUB_EAGER = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			SexPace.SUB_EAGER,
			null) {
		@Override
		public String getActionTitle() {
			return "Eager hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] gently pushing back against you as you enthusiastically fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you eagerly thrust your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you feel [npc.herHim] gently pushing [npc.her] [npc.hips] back against you as [npc.she] begs for you to continue.",
							"Eagerly sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to rapidly thrust your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] softly [npc.moansVerb] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw an eager [npc.moan] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning as you feel [npc.herHim] roughly pushing back against you as you enthusiastically fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] bursts out from between [npc.name]'s [npc.lips+] as you eagerly thrust your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you feel [npc.herHim] roughly pushing [npc.her] [npc.hips] back against you as [npc.she] orders you to continue.",
							"Eagerly sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to rapidly thrust your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] [npc.moansVerb+] in pleasure."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking your [pc.cock+] between [npc.name]'s ass cheeks, you draw [npc.a_moan+] from [npc.her] [npc.lips+] as you start bucking your [pc.hips],"
									+ " grinning to yourself as you enthusiastically fuck [npc.her] ass cleavage.",
							"[npc.A_moan+] drifts out from between [npc.name]'s [npc.lips+] as you eagerly push your [pc.cock+] between [npc.her] ass cheeks,"
									+ " and you soon find your own [pc.moans] joining with [npc.hers] as [npc.she] begs for you to continue.",
							"Eagerly sliding your [pc.cock+] between [npc.name]'s ass cheeks, you start to rapidly thrust your [pc.hips], breathing in [npc.her] [npc.scent] as [npc.she] lets out [npc.a_moan+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_HOTDOGGING_SUB_RESIST = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			SexPace.SUB_RESISTING,
			null) {
		@Override
		public String getActionTitle() {
			return "Resist hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [pc.cock] away from [npc.name]'s [npc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull your [pc.cock] away from [npc.name]'s [npc.ass+], you can't help but let out [pc.a_sob+] as, ignoring your protests,"
									+ " [npc.she] slowly thrusts [npc.her] [npc.hips] back against you and continues gently forcing your [pc.cock+] between [npc.her] cheeks.",
							"[pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, but, totally ignoring your protests,"
									+ " [npc.she] firmly holds you in place, gently pushing [npc.her] [npc.hips] out against your groin as [npc.she] forces your [pc.cock+] between [npc.her] cheeks.",
							"[pc.Sobbing] in distress, you weakly struggle against [npc.name] as you plead for [npc.herHim] to let go of your [pc.cock]."
									+ " [npc.Moaning] in delight, [npc.she] totally ignores your protests, slowly grinding [npc.herself] against you as [npc.she] sinks your [pc.cock+] deep between [npc.her] cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s [npc.ass+], you can't help but let out [pc.a_sob+] as, ignoring your protests,"
									+ " [npc.she] violently thrusts [npc.her] [npc.hips] back against you and continues roughly forcing your [pc.cock+] between [npc.her] cheeks.",
							"[pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, but, totally ignoring your protests,"
									+ " [npc.she] dominantly holds you in place, violently pushing [npc.her] [npc.hips] out against your groin as [npc.she] roughly forces your [pc.cock+] between [npc.her] cheeks.",
							"[pc.Sobbing] in distress, you weakly struggle against [npc.name] as you plead for [npc.herHim] to let go of your [pc.cock]."
									+ " [npc.Moaning] in delight, [npc.she] totally ignores your protests, roughly grinding [npc.herself] against you as [npc.she] sinks your [pc.cock+] deep between [npc.her] cheeks."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull your [pc.cock] free from [npc.name]'s [npc.ass+], you can't help but let out [pc.a_sob+] as, ignoring your protests,"
									+ " [npc.she] eagerly thrusts [npc.her] [npc.hips] back against you and continues rapidly forcing your [pc.cock+] between [npc.her] cheeks.",
							"[pc.A_sob+] bursts out from between your [pc.lips] as you weakly try to push [npc.name] away, but, totally ignoring your protests,"
									+ " [npc.she] firmly holds you in place, eagerly pushing [npc.her] [npc.hips] out against your groin as [npc.she] desperately sinks your [pc.cock+] deep between [npc.her] cheeks.",
							"[pc.Sobbing] in distress, you weakly struggle against [npc.name] as you plead for [npc.herHim] to let go of your [pc.cock]."
									+ " [npc.Moaning] in delight, [npc.she] totally ignores your protests, eagerly grinding [npc.herself] against you as [npc.she] happily forces your [pc.cock+] deep between [npc.her] cheeks."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_HOTDOGGING_STOP = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl(); // Player can only stop if they're in charge (otherwise, this is the partner fucking themselves on the player's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [pc.cock+] out from between [npc.name]'s ass cheeks and stop hotdogging [npc.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking your [pc.cock+] out of [npc.name]'s ass cleavage, you dominantly slide your [pc.cockHead] up and down over [npc.her] [npc.assSize] cheeks one last time before pulling your [pc.hips] back.",
							"Thrusting deep between [npc.name]'s ass cheeks one last time, you then pull back, putting an end to [npc.her] rough hotdogging."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out of [npc.name]'s ass cleavage, you slide your [pc.cockHead] up and down over [npc.her] [npc.assSize] cheeks one last time before pulling your [pc.hips] back.",
							"Pushing deep between [npc.name]'s ass cheeks one last time, you then pull back, putting an end to [npc.her] hotdogging."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.sobsVerb+] as you stop, but [npc.she] doesn't stop crying as [npc.she] continues to weakly struggle against you.",
							" With [npc.a_sob+], and with tears streaming down [npc.her] [npc.face], [npc.name] continues to struggle against you."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] lets out [npc.a_moan+] as you stop.",
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] lust for your [pc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction PARTNER_USING_COCK_ANALLY_START = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can only start fucking themselves on the player's cock in consensual sex or if they're the dom.
			// You can't penetrate if your partner is already fucking you, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			if(Sex.isPenetrationTypeFree(Sex.getActivePartner(), PenetrationType.PENIS_PARTNER)) {
				return !Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
			} else {
				return false; //(Sex.isConsensual() || !Sex.isDom(Main.game.getPlayer())) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS_PARTNER).contains(OrificeType.VAGINA_PLAYER);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Get hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Force [pc.name]'s [pc.cock+] between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] slowly slides your [pc.cock+] over [npc.her] [npc.ass+],"
									+ " letting out a little [npc.moan] before gently pressing [npc.her] cheeks together and forcing your [pc.cock] into the resulting cleavage.",
							"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] slowly pushes [npc.her] cheeks together,"
									+ " softly [npc.moaning] as [npc.she] forces your [pc.cock] into the resulting cleavage."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] eagerly guides your [pc.cock+] over [npc.her] [npc.ass+],"
									+ " letting out [npc.a_moan+] before desperately pressing [npc.her] cheeks together and forcing your [pc.cock] into the resulting cleavage.",
							"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] eagerly pushes [npc.her] cheeks together,"
									+ " [npc.moaning+] as [npc.she] forces your [pc.cock] into the resulting cleavage."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully grinding [npc.herself] against you, [npc.name] guides your [pc.cock+] over [npc.her] [npc.ass+],"
									+ " letting out [npc.a_moan+] before roughly pressing [npc.her] cheeks together and forcing your [pc.cock] into the resulting cleavage.",
							"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] violently pushes [npc.her] cheeks together,"
									+ " [npc.moaning+] as [npc.she] forces your [pc.cock] into the resulting cleavage."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc.herself] against you, [npc.name] guides your [pc.cock+] over [npc.her] [npc.ass+],"
									+ " letting out [npc.a_moan+] before pressing [npc.her] cheeks together and forcing your [pc.cock] into the resulting cleavage.",
							"Lining [npc.her] [npc.ass+] up to your [pc.cock+], [npc.name] pushes [npc.her] cheeks together,"
									+ " [npc.moaning+] as [npc.she] forces your [pc.cock] into the resulting cleavage."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a soft [pc.moan], gently bucking your [pc.hips] as you start to fucking [npc.her] ass cheeks.",
							" With a soft [pc.moan], you gently thrust your [pc.hips] between [npc.her] ass cheeks."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], eagerly bucking your [pc.hips] as you start to fucking [npc.her] ass cheeks.",
							" With [pc.a_moan+], you eagerly thrust your [pc.hips] between [npc.her] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], and, seeking to remind [npc.herHim] who's in charge, you roughly slam your [pc.hips] forwards as you start to fucking [npc.her] ass cheeks.",
							" With [pc.a_moan+], you roughly thrust your [pc.hips] between [npc.her] ass cheeks, seeking to remind [npc.name] who's in charge as you order [npc.herHim] hold still."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+], bucking your [pc.hips] forwards as you start to fucking [npc.her] ass cheeks.",
							" With [pc.a_moan+], you thrust your [pc.hips] between [npc.her] ass cheeks."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_sob+], and, struggling against [npc.herHim] in vain, you desperately try to pull your [pc.cock] away from [npc.name].",
							" With [pc.a_sob+], you weakly struggle against [npc.name] as [npc.she] continues using your [pc.cock]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_COCK_ANALLY_DOM_GENTLE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			null,
			SexPace.DOM_GENTLE) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Gently hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Gently use [pc.name]'s [pc.cock+] to fuck your ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			return UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.ass] into your groin, [npc.name] lets out a soft [npc.moan] as [npc.she] helps you to sink your [pc.cock+] deep between [npc.her] ass cheeks.",
					"With a soft [npc.moan], [npc.name] gently starts bucking [npc.her] [npc.ass] into your groin, forcing your [pc.cock+] ever deeper between [npc.her] ass cheeks.",
					"Slowly thrusting [npc.her] [npc.ass] into your groin, [npc.name] softly [npc.moansVerb] as [npc.her] movements force your [pc.cock+] deep between [npc.her] ass cheeks.");
			
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_COCK_ANALLY_DOM_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			null,
			SexPace.DOM_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Use [pc.name]'s [pc.cock+] to fuck your ass cheeks.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.ass] into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep between [npc.her] ass cheeks.",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.ass] into your groin, forcing your [pc.cock+] ever deeper between [npc.her] ass cheeks.",
					"Enthusiastically thrusting [npc.her] [npc.ass] into your groin, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep between [npc.her] ass cheeks.");
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_COCK_ANALLY_DOM_ROUGH = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Roughly use [pc.name]'s [pc.cock+] to fuck your ass cheeks.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
				"Violently slamming [npc.her] [npc.ass] into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] roughly forces your [pc.cock+] deep between [npc.her] ass cheeks.",
				"With [npc.a_moan+], [npc.name] starts aggressively thrusting [npc.her] [npc.ass] into your groin, roughly forcing your [pc.cock+] ever deeper between [npc.her] ass cheeks.",
				"Roughly thrusting [npc.her] [npc.ass] into your groin, [npc.name] [npc.moansVerb+] as [npc.her] forceful movements drive your [pc.cock+] deep between [npc.her] ass cheeks.");
			
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			null,
			SexPace.SUB_NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Push out ass";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.ass] out against [pc.name] as [pc.her] [pc.cock] thrusts between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Thrusting [npc.her] [npc.ass] out into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] helps to sink your [pc.cock+] deep between [npc.her] ass cheeks.",
					"With [npc.a_moan+], [npc.name] starts pushing [npc.her] [npc.ass] into your groin, forcing your [pc.cock+] ever deeper between [npc.her] ass cheeks.",
					"Thrusting [npc.her] [npc.ass] into your groin, [npc.name] [npc.moansVerb+] as [npc.her] movements force your [pc.cock+] deep between [npc.her] ass cheeks.");
		}
		
	};
	
	public static final SexAction PARTNER_RIDING_COCK_ANALLY_SUB_EAGER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			null,
			SexPace.SUB_EAGER) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly push out ass";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly push your [npc.ass] out against [pc.name] as [pc.her] [pc.cock] thrusts between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Eagerly thrusting [npc.her] [npc.ass] into your groin, [npc.name] lets out [npc.a_moan+] as [npc.she] energetically helps to sink your [pc.cock+] deep between [npc.her] ass cheeks.",
					"With [npc.a_moan+], [npc.name] starts energetically bucking [npc.her] [npc.ass] into your groin, forcing your [pc.cock+] ever deeper between [npc.her] ass cheeks.",
					"Enthusiastically thrusting [npc.her] [npc.ass] into your groin, [npc.name] [npc.moansVerb+] as [npc.her] eager movements force your [pc.cock+] deep between [npc.her] ass cheeks.");
					
		}
		
	};
	
	public static final SexAction PARTNER_FUCKED_ANALLY_SUB_RESIST = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER,
			null,
			SexPace.SUB_RESISTING) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.ass+] away from [pc.name]'s [pc.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to push your [pc.cock] away from [npc.her] [npc.ass+] as you continue gently fucking [npc.her] ass cheeks.",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your unwanted [pc.cock],"
									+ " struggling in desperation as you continue sliding in and out between [npc.her] cheeks.",
							"Trying desperately to pull [npc.her] [npc.ass] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues gently sliding between [npc.her] ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to push your [pc.cock] away from [npc.her] [npc.ass+] as you continue eagerly fucking [npc.herHim].",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your unwanted [pc.cock],"
									+ " struggling in desperation as you continue eagerly sliding in and out between [npc.her] cheeks.",
							"Trying desperately to pull [npc.her] [npc.ass] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues eagerly sliding deep between [npc.her] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Tears start to well up in [npc.name]'s [npc.eyes], before [npc.she] suddenly lets out [npc.a_sob+], weakly trying to push your [pc.cock] away from [npc.her] [npc.ass+] as you continue roughly fucking [npc.her] ass cheeks.",
							"[npc.A_sob+] bursts out from [npc.name]'s mouth as [npc.she] frantically tries to pull [npc.her] [npc.ass] back from your unwanted [pc.cock],"
									+ " struggling in desperation as you continue roughly slamming in and out between [npc.her] cheeks.",
							"Trying desperately to pull [npc.her] [npc.ass] away from you, [npc.name] [npc.sobsVerb] in distress as your [pc.cock+] continues roughly slamming deep between [npc.her] ass cheeks."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_ANALLY_FUCKED_STOP = new SexAction(
			SexActionType.PARTNER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PLAYER,
			OrificeType.ASS_PARTNER) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getActionTitle() {
			return "Stop hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Get [pc.name] to pull [pc.her] [pc.cock] out from between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking your [pc.cock] away from [npc.her] ass cheeks, [npc.name] growls at you as [npc.she] commands you to stop.",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] yanks your [pc.cock] away from [npc.her] ass cheeks."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding your [pc.cock] out from between [npc.her] ass cheeks, [npc.name] lets out [npc.a_moan+] as [npc.she] tells you to stop.",
							"[npc.Name] leans into you, causing you to inhale [npc.her] [npc.scent] before [npc.she] slides your [pc.cock] away from [npc.her] ass cheeks."));
					break;
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out a relieved sigh, which soon turns into [pc.a_sob+] as you realise that [npc.she] isn't finished with you yet.",
							" With [pc.a_sob+], you continue to protest and struggle against [npc.herHim] as [npc.she] holds you firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" You let out [pc.a_moan+] as [npc.she] stops you from fucking [npc.her] ass cleavage.",
							" [pc.A_moan+] escapes from between your [pc.lips+], betraying your desire to continue fucking [npc.her] ass cleavage."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
}
