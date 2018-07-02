package com.lilithsthrone.game.sex.sexActions.baseActionsPartner;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.90
 * @version 0.1.90
 * @author Innoxia
 */
public class PartnerPenisAss {
	
	public static final SexAction PARTNER_TEASE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Hotdogging tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.namePos] [npc.cock] between [npc2.namePos] ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(start)] slowly sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down over the cleft that's formed.",

							"Taking a gentle hold of [npc2.namePos] [npc2.ass+], [npc.name] slowly [npc.verb(push)] [npc2.namePos] cheeks together, before starting to tease [npc.her] [npc.cock+] over the cleft.",

							"With a gentle grasp on each of [npc2.namePos] ass cheeks, [npc.name] slowly [npc.verb(push)] them together, before teasing the [npc.cockHead] of [npc.her] [npc.cock+] up against the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pressing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(start)] forcefully sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down over the cleft that's formed.",

							"Taking a rough grip of [npc2.namePos] [npc2.ass+], [npc.name] forcefully [npc.verb(push)] [npc2.namePos] cheeks together, before starting to tease [npc.her] [npc.cock+] over the cleft.",

							"With a forceful grasp on each of [npc2.namePos] ass cheeks, [npc.name] roughly [npc.verb(push)] them together, before grinding the [npc.cockHead] of [npc.her] [npc.cock+] up against the crevice that's formed."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(start)] eagerly sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down over the cleft that's formed.",

							"Taking a firm hold of [npc2.namePos] [npc2.ass+], [npc.name] enthusiastically [npc.verb(push)] [npc2.namePos] cheeks together, before starting to tease [npc.her] [npc.cock+] over the cleft.",

							"With a greedy grasp on each of [npc2.namePos] ass cheeks, [npc.name] eagerly [npc.verb(push)] them together, before enthusiastically teasing the [npc.cockHead] of [npc.her] [npc.cock+] up against the crevice that's formed."));
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(Please! Use my ass! I need to please [npc2.namePos] cock!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before pleading, [npc2.speech(Go on! Please! Use my ass already!)]",

							" [npc2.Name] [npc2.moan] in delight as [npc2.name] [npc2.verb(beg)], [npc2.speech(Yes! Fuck my ass! I need [npc2.namePos] cock!)]"));
					break;
				case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(No! Don't! Please! Get away from me!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",

								" [npc2.Name] [npc2.sob] in distress as [npc2.name] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(That's right, use my ass!)]",

							" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before pleading, [npc2.speech(Please! Use my ass!)]",

							" [npc2.Name] [npc2.moan] out loud as [npc2.name] [npc2.verb(beg)], [npc2.speech(Come on, use my ass already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ASS);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_DENIAL);
			}
		}
	};
	
	public static final SexAction PLAYER_FORCE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Hotdogging tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of [npc.namePos] [npc.cock] between [npc2.namePos] ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc2.namePos] [npc2.hips] a little, [npc2.name] reach back and press [npc2.namePos] ass cheeks together, before slowly sliding the [npc.cockHead] of [npc.namePos] [npc.cock+] up and down over the cleft that's formed.",

							"Taking a gentle hold of [npc2.namePos] [npc2.ass], [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] cheeks together, before starting to tease [npc.namePos] [npc.cock+] over the cleft.",

							"With a gentle grasp on each of [npc2.namePos] ass cheeks, [npc2.name] slowly [npc2.verb(push)] them together, before teasing the [npc.cockHead] of [npc.namePos] [npc.cock+] up against the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc2.namePos] [npc2.hips] a little, [npc2.name] reach back and roughly press [npc2.namePos] ass cheeks together, before forcefully sliding the [npc.cockHead] of [npc.namePos] [npc.cock+] up and down over the cleft that's formed.",

							"Taking a firm hold of [npc2.namePos] [npc2.ass], [npc2.name] roughly [npc2.verb(push)] [npc2.namePos] cheeks together, before starting to forcefully tease [npc.namePos] [npc.cock+] over the cleft.",

							"With a firm grasp on each of [npc2.namePos] ass cheeks, [npc2.name] forcefully [npc2.verb(push)] them together, before roughly teasing the [npc.cockHead] of [npc.namePos] [npc.cock+] up against the crevice that's formed."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc2.namePos] [npc2.hips] a little, [npc2.name] reach back and eagerly [npc.verb(press)] [npc2.namePos] ass cheeks together, before happily sliding the [npc.cockHead] of [npc.namePos] [npc.cock+] up and down over the cleft that's formed.",

							"Taking a firm hold of [npc2.namePos] [npc2.ass], [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] cheeks together, before starting to eagerly tease [npc.namePos] [npc.cock+] over the cleft.",

							"With a firm grasp on each of [npc2.namePos] ass cheeks, [npc2.name] eagerly [npc2.verb(push)] them together, before teasing the [npc.cockHead] of [npc.namePos] [npc.cock+] up against the crevice that's formed."));
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] bursts out from between [npc.her] [npc.lips+], [npc.speech(Please! [npc2.verb(let)] me fuck [npc2.namePos] ass!)]",

							" [npc.Name] [npc.verb(let)] out a desperate [npc.moan], before pleading with [npc2.herHim], [npc.speech(Yes! Please! I want to fuck [npc2.namePos] ass!)]",

							" [npc.Name] [npc.moansVerb] in delight as [npc.she] [npc2.verb(beg)]s, [npc.speech(Yes! [npc2.verb(let)] me fuck you! Please!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] drifts out from between [npc.her] [npc.lips+], [npc.speech(Yes, [npc2.verb(let)] me fuck [npc2.namePos] ass!)]",

							" [npc.Name] [npc.verb(let)] out a [npc.moan], before calling out, [npc.speech(Please! I want to fuck [npc2.namePos] ass!)]",

							" [npc.Name] [npc.moansVerb] out loud as [npc.she] speaks to [npc2.herHim], [npc.speech(Come on, [npc2.verb(let)] me fuck [npc2.name] already!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_sob+] bursts out from between [npc.her] [npc.lips+], [npc.speech(No! Don't! Please! Get away from me!)]",

							" [npc.Name] [npc.verb(let)] out a desperate [npc.sob], before pleading, [npc.speech(Please! Don't do this! Leave me alone!)]",

							" [npc.Name] [npc.sobsVerb] in distress as [npc.she] [npc2.verb(beg)]s, [npc.speech(No! Stop! Get away from there!)]"));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Sex.transferLubrication(Sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ASS);
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING, Fetish.FETISH_DENIAL);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};
	
	
	public static final SexAction PARTNER_ANAL_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Partner can't penetrate if you're already fucking them, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			return Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Start hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Sink [npc.namePos] [npc.cock+] between [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.ass],"
									+ " before slowly pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass+], and with a slow, steady pressure,"
									+ " [npc.she] gently [npc.verb(press)] [npc2.namePos] cheeks together, before starting to fuck the crevice that's formed."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.ass],"
									+ " before greedily pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass+], before eagerly pressing [npc2.namePos] cheeks together and starting to fuck the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] forcefully [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.ass],"
									+ " before violently pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass+], before roughly pressing [npc2.namePos] cheeks together and starting to aggressively fuck the crevice that's formed."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.ass],"
									+ " before pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] positions the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass+], before pressing [npc2.namePos] cheeks together and starting to fuck the crevice that's formed."));
					break;
				default:
					break;
			}
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.she] [npc.verb(start)] using [npc2.namePos] ass, gently bucking [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks.",

							" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, sinking [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] [npc.verb(start)] using [npc2.namePos] ass, eagerly bucking [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] [npc.verb(start)] using [npc2.namePos] ass, violently thrusting [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] [npc.verb(start)] using [npc2.namePos] ass, eagerly bucking [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, desperately helping to sink [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.she] [npc.verb(start)] using [npc2.namePos] ass, bucking [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.namePos] [npc2.ass] into [npc.her] crotch, helping to sink [npc.her] [npc.cock+] even deeper between [npc2.namePos] ass cheeks."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.she] [npc.verb(start)] using [npc2.namePos] ass, and, with tears running down [npc2.namePos] [npc2.face], [npc2.name] [npc2.verb(beg)] for [npc.herHim] to stop as [npc2.name] weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from [npc.name]; tears running down [npc2.namePos] [npc2.face] as [npc.her] unwelcome [npc.cock] [npc.verb(push)] deep between [npc2.namePos] ass cheeks."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Gentle hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] slowly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] slowly [npc.verb(push)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] gently [npc2.verb(thrust)] into [npc2.namePos] ass cleavage.",

							"Sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to gently buck [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently  [npc.verb(sink)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, causing [npc2.name] to let out [npc2.a_sob+]. With tears streaming down [npc2.namePos] [npc2.face], [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop.",

							"[npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc.name] slowly [npc.verb(push)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and with tears streaming down [npc2.namePos] [npc2.face], [npc2.name] desperately [npc2.verb(plead)] for [npc.herHim] to go away.",

							"Slowly sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] gently bucks [npc.her] [npc.hips] forwards as [npc2.name] weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] slowly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] slowly [npc.verb(push)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] gently [npc2.verb(thrust)] into [npc2.namePos] ass cleavage.",

							"Sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to gently buck [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] rapidly [npc2.verb(thrust)] into [npc2.namePos] ass cleavage.",

							"Enthusiastically thrusting [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to eagerly buck [npc.her] [npc.hips] forwards,"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly  [npc.verb(sink)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, causing [npc2.name] to let out [npc2.a_sob+]. With tears streaming down [npc2.namePos] [npc2.face], [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop.",

							"[npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and with tears streaming down [npc2.namePos] [npc2.face], [npc2.name] desperately [npc2.verb(plead)] for [npc.herHim] to stop.",

							"Enthusiastically thrusting [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] eagerly bucks [npc.her] [npc.hips] forwards as [npc2.name] weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to go away."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] rapidly [npc2.verb(thrust)] into [npc2.namePos] ass cleavage.",

							"Enthusiastically thrusting [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to rapidly buck [npc.her] [npc.hips] forwards,"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Rough hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}


		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] violently bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] roughly slams [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] brutally [npc2.verb(thrust)] into [npc2.namePos] ass cleavage.",

							"Ruthlessly thrusting [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to roughly slam [npc.her] [npc.hips] forwards,"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] roughly slams [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, causing [npc2.name] to let out [npc2.a_sob+]. With tears streaming down [npc2.namePos] [npc2.face], [npc2.name] weakly [npc2.verb(beg)] for [npc.herHim] to stop abusing [npc2.herHim].",

							"[npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc.name] roughly slams [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and with tears streaming down [npc2.namePos] [npc2.face], [npc2.name] desperately [npc2.verb(plead)] for [npc.herHim] to stop abusing [npc2.herHim].",

							"Ruthlessly thrusting [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] roughly slams [npc.her] [npc.hips] forwards as [npc2.name] weakly struggle against [npc.herHim],"
									+ " pleading and crying for [npc.herHim] to stop."));
					break;
				default: // SUB_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly slamming [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(start)] violently bucking [npc.her] [npc.hips],"
									+ " grinning as [npc.she] feels [npc2.name] pushing [npc2.namePos] [npc2.ass] back against [npc.herHim].",

							"[npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc.name] roughly slams [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] find [npc2.herself] pushing [npc2.namePos] [npc2.hips] back against [npc.herHim] as [npc.she] brutally [npc2.verb(thrust)] into [npc2.namePos] ass cleavage.",

							"Ruthlessly thrusting [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to roughly slam [npc.her] [npc.hips] forwards,"
									+ " breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Normal hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "[npc.verb(continue)] fucking [npc2.namePos] ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(draw)] a soft [npc2.moan] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] [npc2.verb(start)] gently pushing [npc2.namePos] [npc2.ass] back against [npc.her] groin.",

							"A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] can't [npc2.verb(help)] but gently [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc.she] carries on fucking [npc2.namePos] ass cleavage.",

							"Sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to thrust [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc2.name] softly [npc2.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(draw)] [npc2.a_moan+] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] roughly [npc.verb(command)] [npc2.herHim] to continue fucking [npc2.namePos] ass cleavage.",

							"[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and, roughly thrusting [npc2.namePos] [npc2.ass] back into [npc.her] groin, [npc2.name] [npc2.verb(order)] [npc.herHim] to continue fucking [npc2.namePos] ass cleavage.",

							"Sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to thrust [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(draw)] [npc2.a_moan+] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] [npc2.verb(start)] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.her] groin.",

							"[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] can't [npc2.verb(help)] but greedily [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc.she] carries on fucking [npc2.namePos] ass cleavage.",

							"Sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to thrust [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc2.name] softly [npc2.moan] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Eager hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(draw)] a soft [npc2.moan] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] [npc2.verb(start)] gently pushing [npc2.namePos] [npc2.ass] back against [npc.her] groin.",

							"A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] can't [npc2.verb(help)] but gently [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc.she] carries on enthusiastically fucking [npc2.namePos] ass cleavage.",

							"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to rapidly [npc2.verb(thrust)] [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc2.name] softly [npc2.moan] in pleasure."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(draw)] [npc2.a_moan+] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] roughly [npc.verb(command)] [npc2.herHim] to continue fucking [npc2.namePos] ass cleavage.",

							"[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and, roughly thrusting [npc2.namePos] [npc2.ass] back into [npc.her] groin, [npc2.name] [npc2.verb(order)] [npc.herHim] to continue fucking [npc2.namePos] ass cleavage.",

							"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to rapidly [npc2.verb(thrust)] [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc2.name] [npc2.moanVerb+] in pleasure."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately sinking [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(draw)] [npc2.a_moan+] from [npc2.namePos] [npc2.lips+] as [npc.she] [npc.verb(start)] eagerly bucking [npc.her] [npc.hips],"
									+ " letting out [npc.a_moan] of [npc.her] own as [npc2.name] [npc2.verb(start)] greedily pushing [npc2.namePos] [npc2.ass] back against [npc.her] groin.",

							"[npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
									+ " and [npc2.name] can't [npc2.verb(help)] but greedily [npc2.verb(push)] [npc2.namePos] [npc2.ass] back against [npc.herHim] as [npc.she] carries on enthusiastically fucking [npc2.namePos] ass cleavage.",

							"Eagerly sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks, [npc.name] [npc.verb(start)] to rapidly [npc2.verb(thrust)] [npc.her] [npc.hips] forwards, breathing in [npc2.namePos] [npc2.scent] as [npc2.name] softly [npc2.moan] in pleasure."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull [npc.namePos] [npc.cock] away from [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.herHim], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.Name] slowly [npc2.verb(thrust)] [npc2.namePos] [npc2.ass] back against [npc.herHim] and [npc.verb(continue)] gently forcing [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] firmly hold [npc.herHim] in place, gently pushing [npc2.namePos] [npc2.ass] out against [npc.her] groin as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks.",

							"[npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.name] as [npc.she] [npc2.verb(plead)]s for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.her] protests, slowly grinding [npc2.herself] against [npc.herHim] as [npc2.name] [npc.verb(sink)] [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.herHim], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.Name] violently [npc2.verb(thrust)] [npc2.namePos] [npc2.ass] back against [npc.herHim] and [npc.verb(continue)] roughly forcing [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] dominantly hold [npc.herHim] in place, violently pushing [npc2.namePos] [npc2.ass] out against [npc.her] groin as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks.",

							"[npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.name] as [npc.she] [npc2.verb(plead)]s for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.her] protests, roughly grinding [npc2.herself] against [npc.herHim] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.herHim], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.Name] eagerly [npc2.verb(thrust)] [npc2.namePos] [npc2.ass] back against [npc.herHim] and [npc.verb(continue)] rapidly forcing [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.Name] firmly hold [npc.herHim] in place, eagerly pushing [npc2.namePos] [npc2.ass] out against [npc.her] groin as [npc2.name] desperately [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks.",

							"[npc.Sobbing] in distress, [npc.name] weakly struggles against [npc2.name] as [npc.she] [npc2.verb(plead)]s for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally ignore [npc.her] protests, eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.name] happily [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ANAL_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this)) ||Sex.isConsensual(); // Partner can only stop if they're in charge (otherwise, this is the player fucking themselves on the partner's cock).
		}
		
		@Override
		public String getActionTitle() {
			return "Stop hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Pull [npc.namePos] [npc.cock+] out from between [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] ass cleavage,"
									+ " [npc.name] dominantly slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.ass+] one last time before pulling [npc.her] [npc.hips] back.",

							"thrusting deep inside of [npc2.namePos] ass cleavage one last time, [npc.name] then [npc2.verb(pull)]s back, putting an end to [npc2.namePos] hotdogging."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock] out of [npc2.namePos] ass cleavage, [npc.name] slides the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.ass+] one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep inside of [npc2.namePos] ass cleavage one last time, [npc.name] then [npc2.verb(pull)]s back, putting an end to [npc2.namePos] hotdogging."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] moves away, and [npc2.name] [npc.verb(continue)] crying and protesting as [npc2.name] [npc.verb(continue)] to weakly struggle against [npc.herHim].",

							" With [npc2.a_sob+], [npc2.name] [npc.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.namePos] [npc2.face] as [npc.she] prepares [npc.her] next move."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] back, eager for more of [npc.her] attention.",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.namePos] lust for [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Player actions:
	
	public static final SexAction PLAYER_USING_COCK_ANALLY_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Player can only [npc2.verb(start)] fucking themselves on the partner's cock in consensual sex or if they're the dom.
			// [npc2.name] can't penetrate if you're already fucking [npc2.namePos] partner, due to physical limitations. (I mean, if you're facing opposite ways and lying on top of each other, it might be possible, but that position will be special.)
			
			if(Sex.isPenetrationTypeFree(Sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS)) {
				return (Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)));
			} else {
				return false;//(Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this))) && !Sex.getOngoingPenetrationMap().get(PenetrationType.PENIS).contains(OrificeType.VAGINA);
			}
		}
		
		@Override
		public String getActionTitle() {
			return "Get hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] slowly slide [npc.her] [npc.cock+] over [npc2.namePos] [npc2.ass+],"
									+ " letting out a little [npc2.moan] before gently pressing [npc2.namePos] cheeks together and forcing [npc.her] [npc.cock] into the resulting cleavage.",

							"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] slowly [npc2.verb(push)] [npc2.namePos] cheeks together,"
									+ " softly [npc2.moaning] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] into the resulting cleavage."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] eagerly slide [npc.her] [npc.cock+] over [npc2.namePos] [npc2.ass+],"
									+ " letting out [npc2.a_moan+] before desperately pressing [npc2.namePos] cheeks together and forcing [npc.her] [npc.cock] into the resulting cleavage.",

							"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] eagerly [npc2.verb(push)] [npc2.namePos] cheeks together,"
									+ " [npc2.moaning+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] into the resulting cleavage."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Forcefully pressing [npc2.herself] against [npc.name], [npc2.name] roughly slide [npc.her] [npc.cock+] over [npc2.namePos] [npc2.ass+],"
									+ " letting out [npc2.a_moan+] before pressing [npc2.namePos] cheeks together and forcing [npc.her] [npc.cock] into the resulting cleavage.",

							"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] roughly [npc2.verb(push)] [npc2.namePos] cheeks together,"
									+ " [npc2.moaning+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] into the resulting cleavage."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pressing [npc2.herself] against [npc.name], [npc2.name] slide [npc.her] [npc.cock+] over [npc2.namePos] [npc2.ass+],"
									+ " letting out [npc2.a_moan+] before pressing [npc2.namePos] cheeks together and forcing [npc.her] [npc.cock] into the resulting cleavage.",

							"Lining [npc2.namePos] [npc2.ass+] up to [npc.namePos] [npc.cock+], [npc2.name] [npc2.verb(push)] [npc2.namePos] cheeks together,"
									+ " [npc2.moaning+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] into the resulting cleavage."));
					break;
				default:
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a soft [npc.moan], gently bucking [npc.her] [npc.hips] forwards as [npc.she] [npc.verb(start)] to fuck [npc2.namePos] ass cheeks.",

							" With a soft [npc.moan], [npc.name] gently [npc2.verb(thrust)] [npc.her] [npc.hips] forwards and  [npc.verb(sink)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+], eagerly bucking [npc.her] [npc.hips] as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] ass cheeks.",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] forwards and  [npc.verb(sink)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+], and, seeking to remind [npc2.name] who's in charge,"
									+ " [npc.she] roughly slams [npc.her] [npc.hips] forwards, before starting to ruthlessly fuck [npc2.namePos] ass cheeks.",

							" With [npc.a_moan+], [npc.name] roughly slams [npc.her] [npc.hips] forwards, seeking to remind [npc2.name] who's in charge as [npc.she] [npc.verb(start)] ruthlessly fucking [npc2.namePos] ass cheeks."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+], eagerly bucking [npc.her] [npc.hips] forwards as [npc.she] [npc.verb(start)] enthusiastically fucking [npc2.namePos] ass cheeks.",

							" With [npc.a_moan+], [npc.name] eagerly [npc2.verb(thrust)] [npc.her] [npc.hips] forwards and  [npc.verb(sink)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_moan+], bucking [npc.her] [npc.hips] forwards as [npc.she] [npc.verb(start)] fucking [npc2.namePos] ass cheeks.",

							" With [npc.a_moan+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] forwards and  [npc.verb(sink)] [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] between [npc2.namePos] ass cheeks, and, struggling against [npc2.herHim], [npc.she] desperately tries to pull away.",

							" With [npc.a_sob+], [npc.name] struggles against [npc2.name] as [npc2.name] [npc2.verb(force)] [npc.her] [npc.cock] deep between [npc2.namePos] ass cheeks."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Gently hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Gently use [npc.namePos] [npc.cock+] to fuck [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
				return UtilText.returnStringAtRandom(
						"Gently pushing [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks.",

						"With a soft [npc2.moan], [npc2.name] gently [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.ass], forcing [npc.her] [npc.cock+] ever deeper between [npc2.namePos] ass cheeks.",

						"Slowly thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, a soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks.");
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Use [npc.namePos] [npc.cock+] to fuck [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
				return UtilText.returnStringAtRandom(
						"Eagerly pushing [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks.",

						"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.ass], forcing [npc.her] [npc.cock+] ever deeper between [npc2.namePos] ass cheeks.",

						"Enthusiastically thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks.");
			
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Roughly hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Roughly use [npc.namePos] [npc.cock+] to fuck [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			
				return UtilText.returnStringAtRandom(
						"Violently slamming [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] roughly [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks.",

						"With [npc2.a_moan+], [npc2.name] aggressively [npc2.verb(start)] gyrating [npc2.namePos] [npc2.hips] into [npc.namePos] [npc.ass], roughly forcing [npc.her] [npc.cock+] ever deeper between [npc2.namePos] ass cheeks.",

						"Roughly thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] forceful movements drive [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks.");
			
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.verb(push)] [npc2.namePos] [npc2.ass+] out against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] between [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc2.namePos] [npc2.ass] out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks.",

					"With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] gyrating [npc2.namePos] [npc2.ass] into [npc.namePos] groin, forcing [npc.her] [npc.cock+] ever deeper between [npc2.namePos] ass cheeks.",

					"thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.a_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_RIDING_COCK_ANALLY_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly [npc2.verb(push)] [npc2.namePos] [npc2.ass+] out against [npc.name] as [npc.her] [npc.cock] [npc2.verb(thrust)] between [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc2.namePos] [npc2.ass] out into [npc.namePos] groin, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] energetically [npc2.verb(help)] to sink [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks.",

					"With [npc2.a_moan+], [npc2.name] energetically [npc2.verb(start)] gyrating [npc2.namePos] [npc2.ass] into [npc.namePos] groin, forcing [npc.her] [npc.cock+] ever deeper between [npc2.namePos] ass cheeks.",

					"Enthusiastically thrusting [npc2.namePos] [npc2.ass] into [npc.namePos] groin, [npc2.a_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.namePos] movements [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.namePos] ass cheeks."));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_ANALLY_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Resist hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Try and [npc2.verb(pull)] [npc2.namePos] [npc2.ass+] away from [npc.namePos] [npc.cock+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull away from [npc.namePos] [npc.cock] as [npc.she] [npc.verb(continue)] gently fucking [npc2.namePos] ass cheeks.",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] away from [npc.namePos] unwanted [npc.cock],"
									+ " struggling in desperation as [npc.she] [npc.verb(continue)] slowly sliding in and out between [npc2.namePos] ass cheeks.",

							"Trying desperately to pull [npc2.namePos] [npc2.ass] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] gently sliding deep between [npc2.namePos] ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull away from [npc.namePos] [npc.cock] as [npc.she] [npc.verb(continue)] eagerly fucking [npc2.namePos] ass cheeks.",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] back from [npc.namePos] unwanted [npc.cock],"
									+ " struggling in desperation as [npc.she] [npc.verb(continue)] eagerly sliding in and out between [npc2.namePos] ass cheeks.",

							"Trying desperately to pull [npc2.namePos] [npc2.ass] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] eagerly sliding deep between [npc2.namePos] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"You [npc2.verb(feel)] tears [npc2.verb(start)] to well up in [npc2.namePos] [npc2.eyes], and, not being able to hold back any longer, [npc2.name] suddenly [npc2.verb(let)] out [npc2.a_sob+],"
									+ " weakly trying to pull [npc.namePos] [npc.cock] out of [npc2.namePos] [npc2.asshole+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.namePos] ass cheeks.",

							"[npc2.A_sob+] bursts out from [npc2.namePos] mouth as [npc2.name] frantically [npc2.verb(try)] to pull [npc2.namePos] [npc2.ass] back from [npc.namePos] unwanted [npc.cock],"
									+ " struggling in desperation as [npc.she] [npc.verb(continue)] roughly slamming in and out between [npc2.namePos] ass cheeks.",

							"Trying desperately to pull [npc2.namePos] [npc2.ass] away from [npc.name], [npc2.name] [npc2.sob] in distress as [npc.her] [npc.cock+] [npc.verb(continue)] roughly slamming deep between [npc2.namePos] ass cheeks."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PLAYER_FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isConsensual() || Sex.isDom(Sex.getCharacterTargetedForSexAction(this)); // Player can only stop in consensual sex or if they're the dom.
		}
		
		@Override
		public String getActionTitle() {
			return "Stop hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc.name] to pull [npc.her] [npc.cock] out from between [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc.namePos] [npc.cock] out from between [npc2.namePos] ass cheeks, [npc2.name] [npc2.verb(let)] out a menacing growl as [npc2.name] [npc.verb(command)] [npc.herHim] to stop.",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before yanking [npc.her] [npc.cock] out from between [npc2.namePos] ass cheeks."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.namePos] [npc.cock] out from between [npc2.namePos] ass cheeks, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.name] tell [npc.herHim] to stop.",

							"You lean into [npc.name], inhaling [npc.her] [npc.scent] before sliding [npc.her] [npc.cock] out from between [npc2.namePos] ass cheeks."));
					break;
			}
			
			switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.Name] [npc.verb(let)] out a relieved sigh, which soon turns into [npc.a_sob+] as [npc.she] realises that [npc2.name] haven't finished with [npc.herHim] just yet.",

							" With [npc.a_sob+], [npc.name] [npc.verb(continue)] to protest and struggle against [npc2.name] as [npc2.name] hold [npc.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.A_moan+] escapes from between [npc.her] [npc.lips+], betraying [npc.her] desire to continue fucking [npc2.namePos] ass cleavage."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
