package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.90
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisAss {
	
	public static final SexAction TEASE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Hotdogging tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc.cockHead] of your [npc.cock] between [npc2.namePos] ass cheeks.";
		}
		
		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Gently pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(start)] slowly sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down between the cleft that's formed.",

							"Taking a gentle hold of [npc2.namePos] [npc2.ass+], [npc.name] slowly [npc.verb(push)] [npc2.her] cheeks together, before starting to tease [npc.her] [npc.cock+] between the cleft.",

							"Gently grasping each of [npc2.namePos] ass cheeks, [npc.name] slowly [npc.verb(push)] them together,"
									+ " before teasing the [npc.cockHead] of [npc.her] [npc.cock+] up between the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pressing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(start)] forcefully sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down between the cleft that's formed.",

							"Taking a rough grip of [npc2.namePos] [npc2.ass+], [npc.name] forcefully [npc.verb(push)] [npc2.her] cheeks together, before starting to tease [npc.her] [npc.cock+] between the cleft.",

							"With a forceful grasp on each of [npc2.namePos] ass cheeks, [npc.name] roughly [npc.verb(push)] them together,"
									+ " before grinding the [npc.cockHead] of [npc.her] [npc.cock+] up between the crevice that's formed."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Greedily pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(start)] eagerly sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down between the cleft that's formed.",

							"Taking a firm hold of [npc2.namePos] [npc2.ass+], [npc.name] enthusiastically [npc.verb(push)] [npc2.her] cheeks together, before starting to tease [npc.her] [npc.cock+] between the cleft.",

							"With a greedy grasp on each of [npc2.namePos] ass cheeks, [npc.name] eagerly [npc.verb(push)] them together,"
									+ " before enthusiastically teasing the [npc.cockHead] of [npc.her] [npc.cock+] up between the crevice that's formed."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(start)] eagerly sliding the [npc.cockHead] of [npc.her] [npc.cock+] up and down between the cleft that's formed.",

							"Taking a firm hold of [npc2.namePos] [npc2.ass+], [npc.name] enthusiastically [npc.verb(push)] [npc2.her] cheeks together, before starting to tease [npc.her] [npc.cock+] between the cleft.",

							"With a grasp on each of [npc2.namePos] ass cheeks, [npc.name] [npc.verb(push)] them together,"
									+ " before enthusiastically teasing the [npc.cockHead] of [npc.her] [npc.cock+] up between the crevice that's formed."));
					break;
				default:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(Yes, use my ass!)]",
	
							" [npc2.Name] [npc2.verb(let)] out a gentle [npc2.moan], before pleading, [npc2.speech(Go on, use my ass!)]",
	
							" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(beg)], [npc2.speech(Yes! Fuck my ass!)]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(Go on! Use my ass! Put some effort in!)]",
	
							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before growling, [npc2.speech(That's right, use my ass, bitch!)]",
	
							" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(demand)], [npc2.speech(Fuck my ass! Do it right now!)]"));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(Yes! Use my ass! I need to please your cock!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before pleading, [npc2.speech(Go on! Please! Use my ass already!)]",

							" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(beg)], [npc2.speech(Yes! Fuck my ass! I need your cock!)]"));
					break;
				case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(No! Don't! Please! Get away from me!)]",

								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",

								" [npc2.Name] [npc2.sobsVerb] in distress as [npc2.she] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+], [npc2.speech(That's right, use my ass!)]",

							" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before pleading, [npc2.speech(Please! Use my ass!)]",

							" [npc2.Name] [npc2.moanVerb] out loud as [npc2.she] [npc2.verb(beg)], [npc2.speech(Come on, use my ass already!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ASS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction FORCE_COCK_OVER_ASS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Hotdogging tease";
		}

		@Override
		public String getActionDescription() {
			return "Slide the [npc2.cockHead] of [npc2.namePos] [npc2.cock] between your ass cheeks.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			boolean canReachPenis = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaPenetration.PENIS)) {
					canReachPenis = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaPenetration.PENIS).contains(SexAreaPenetration.FINGER)) {
					canReachPenis = true;
				}
			} catch(Exception ex) {
			}
			if(!canReachPenis) { // No available finger-penis actions, so can't reach penis
				return false;
			}
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips] a little, [npc.name] [npc.verb(reach)] back and [npc.verb(press)] [npc.her] ass cheeks together,"
									+ " before slowly sliding the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] up and down between the cleft that's formed.",

							"Taking a gentle hold of [npc.her] [npc.ass], [npc.name] slowly [npc.verb(push)] [npc.her] cheeks together,"
									+ " before starting to tease [npc2.namePos] [npc2.cock+] between the cleft.",

							"With a gentle grasp on each of [npc.her] ass cheeks, [npc.name] slowly [npc.verb(push)] them together,"
									+ " before teasing the [npc2.cockHead] of [npc2.her] [npc2.cock+] between the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips] a little, [npc.name] [npc.verb(reach)] back and roughly [npc.verb(press)] [npc.her] ass cheeks together,"
									+ " before forcefully sliding the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] up and down between the cleft that's formed.",

							"Taking a firm hold of [npc.her] [npc.ass], [npc.name] roughly [npc.verb(push)] [npc.her] cheeks together,"
									+ " before starting to forcefully tease [npc2.namePos] [npc2.cock+] between the cleft.",

							"With a firm grasp on each of [npc.her] ass cheeks, [npc.name] forcefully [npc.verb(push)] them together,"
									+ " before roughly teasing the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] between the crevice that's formed."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips] a little, [npc.name] [npc.verb(reach)] back and eagerly [npc.verb(press)] [npc.her] ass cheeks together,"
									+ " before happily sliding the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] up and down between the cleft that's formed.",

							"Taking a firm hold of [npc.her] [npc.ass], [npc.name] slowly [npc.verb(push)] [npc.her] cheeks together,"
									+ " before starting to eagerly tease [npc2.namePos] [npc2.cock+] between the cleft.",

							"With a firm grasp on each of [npc.her] ass cheeks, [npc.name] eagerly [npc.verb(push)] them together,"
									+ " before teasing the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] between the crevice that's formed."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Shifting [npc.her] [npc.hips] a little, [npc.name] [npc.verb(reach)] back and eagerly [npc.verb(press)] [npc.her] ass cheeks together,"
									+ " before happily sliding the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] up and down between the cleft that's formed.",

							"Taking a firm hold of [npc.her] [npc.ass], [npc.name] slowly [npc.verb(push)] [npc.her] cheeks together,"
									+ " before starting to eagerly tease [npc2.namePos] [npc2.cock+] between the cleft.",

							"With a firm grasp on each of [npc.her] ass cheeks, [npc.name] eagerly [npc.verb(push)] them together,"
									+ " before teasing the [npc2.cockHead] of [npc2.namePos] [npc2.cock+] between the crevice that's formed."));
					break;
				default:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" A soft [npc2.moan] bursts out from between [npc2.her] [npc2.lips+], [npc2.speech(Please! Let me fuck your ass!)]",

							" [npc2.Name] [npc2.verb(let)] out a gentle [npc2.moan], before pleading with [npc.name], [npc2.speech(Yes, I want to fuck your ass!)]",

							" [npc2.Name] softly [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(beg)], [npc2.speech(Yes! Let me fuck you! Please!)]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.her] [npc2.lips+], [npc2.speech(Oh, you dirty slut! You want me to fuck your ass?!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before growling at [npc.name], [npc2.speech(You want me to pound your ass, you dirty slut?!)]",

							" [npc2.Name] [npc2.moansVerb] in delight, before snarling, [npc2.speech(You want your ass fucked, slut?!)]"));
					break;
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] bursts out from between [npc2.her] [npc2.lips+], [npc2.speech(Yes! I want to fuck your ass!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before addressing [npc.name], [npc2.speech(Yes! Your ass could do with a good fucking!)]",

							" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(call)] out to [npc.name], [npc2.speech(I need to fuck your ass!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] drifts out from between [npc2.her] [npc2.lips+], [npc2.speech(Yes, let me fuck your ass!)]",

							" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before calling out, [npc2.speech(Please! I want to fuck your ass!)]",

							" [npc2.Name] [npc2.moansVerb] out loud as [npc2.she] speaks to [npc.herHim], [npc2.speech(Come on, [npc.verb(let)] me fuck you already!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_sob+] bursts out from between [npc2.her] [npc2.lips+], [npc2.speech(No! Don't! Please! Get away from me!)]",

							" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",

							" [npc2.Name] [npc2.sobsVerb] in distress as [npc2.she] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ASS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};
	
	
	public static final SexAction ASS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Start hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.cock+] up between [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.her] [npc2.ass],"
									+ " before gently thrusting [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] [npc.verb(position)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass+], and with a slow, steady pressure,"
									+ " [npc.she] gently [npc.verb(press)] [npc2.her] cheeks together, before starting to fuck the crevice that's formed."));
					break;
				case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.her] [npc2.ass],"
									+ " before greedily pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] [npc.verb(position)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass+],"
									+ " before eagerly pressing [npc2.her] cheeks together and starting to fuck the crevice that's formed."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] forcefully [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.her] [npc2.ass],"
									+ " before violently pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] [npc.verb(position)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass+],"
									+ " before roughly squeezing [npc2.her] cheeks together and starting to aggressively fuck the crevice that's formed."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pushing [npc2.namePos] [npc2.assSize] ass cheeks together, [npc.name] [npc.verb(press)] the [npc.cockHead+] of [npc.her] [npc.cock+] up against [npc2.her] [npc2.ass],"
									+ " before pushing [npc.her] [npc.hips] forwards and starting to fuck the crevice that's formed.",

							"[npc.Name] [npc.verb(position)] the [npc.cockHead+] of [npc.her] [npc.cock] up against [npc2.namePos] [npc2.ass+],"
									+ " before pressing [npc2.her] cheeks together and starting to fuck the crevice that's formed."));
					break;
				default:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc.name] [npc.verb(start)] using [npc2.her] ass,"
									+ " gently bucking [npc2.her] [npc2.hips+] back against [npc.herHim] as [npc2.she] [npc2.verb(help)] to sink the [npc.cock+] even deeper between [npc2.her] ass cheeks.",

							" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.ass] into [npc.namePos] crotch,"
									+ " sinking [npc.her] [npc.cock+] even deeper between [npc2.her] ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] ass,"
									+ " eagerly bucking [npc2.her] [npc2.hips+] back against [npc.herHim] as [npc2.she] [npc2.verb(help)] to sink the [npc.cock+] even deeper between [npc2.her] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.ass] into [npc.namePos] crotch,"
									+ " desperately helping to sink [npc.her] [npc.cock+] even deeper between [npc2.her] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] ass,"
									+ " violently thrusting [npc2.her] [npc2.hips+] back against [npc.herHim] as [npc2.she] roughly [npc2.verb(force)] the [npc.cock+] even deeper between [npc2.her] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.ass] into [npc.namePos] crotch,"
									+ " roughly forcing [npc.herHim] to sink [npc.her] [npc.cock+] even deeper between [npc2.her] ass cheeks."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] ass,"
									+ " eagerly bucking [npc2.her] [npc2.hips+] back against [npc.herHim] as [npc2.she] [npc2.verb(help)] to sink the [npc.cock+] even deeper between [npc2.her] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.ass] into [npc.namePos] crotch,"
									+ " desperately helping to sink [npc.her] [npc.cock+] even deeper between [npc2.her] ass cheeks."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] using [npc2.her] ass,"
									+ " bucking [npc2.her] [npc2.hips+] back against [npc.herHim] as [npc2.she] [npc2.verb(help)] to sink the [npc.cock+] even deeper between [npc2.her] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.her] [npc2.ass] into [npc.namePos] crotch,"
									+ " helping to sink [npc.her] [npc.cock+] even deeper between [npc2.her] ass cheeks."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(start)] using [npc2.her] ass, and,"
									+ " with tears running down [npc2.her] [npc2.face], [npc2.she] desperately [npc2.verb(beg)] for [npc.herHim] to stop.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name];"
									+ " tears running down [npc2.her] [npc2.face] as the unwelcome [npc.cock] pushes deep between [npc2.her] ass cheeks."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] up and down in response,"
								+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] to slide in and out between [npc2.her] ass cheeks.",
	
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, eagerly gyrating [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] up and down between the cheeks of [npc2.her] [npc2.ass+].",
	
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips+] back and forth,"
								+ " begging for [npc.name] to continue hotdogging [npc2.herHim] as [npc2.her] movements help to slide the [npc.cock+] up and down between [npc2.her] cheeks."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to pull out of [npc2.her] [npc2.ass+].",
	
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
								+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.her] [npc2.ass+].",
	
						" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
								+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.her] [npc2.ass+]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips] up and down in response,"
								+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] to slide in and out between [npc2.her] ass cheeks.",
	
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, gyrating [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] up and down between the cheeks of [npc2.her] [npc2.ass+].",
	
						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips+] back and forth,"
								+ " begging for [npc.name] to continue hotdogging [npc2.herHim] as [npc2.her] movements help to slide the [npc.cock+] up and down between [npc2.her] cheeks."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] up and down in response,"
								+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] to slide in and out between [npc2.her] ass cheeks.",
	
						" A gentle [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, slowly gyrating [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(slide)] [npc.namePos] [npc.cock+] up and down between the cheeks of [npc2.her] [npc2.ass+].",
	
						" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips+] back and forth,"
								+ " begging for [npc.name] to continue hotdogging [npc2.herHim] as [npc2.her] movements help to slide the [npc.cock+] up and down between [npc2.her] cheeks."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.hips] up and down in response,"
								+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(cause)] [npc.namePos] [npc.cock+] to pump in and out between [npc2.her] ass cheeks.",
	
						" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
								+ " and, roughly gyrating [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(force)] [npc.namePos] [npc.cock+] up and down between the cheeks of [npc2.her] [npc2.ass+].",
	
						" [npc2.Moaning] in delight, [npc2.name] dominantly [npc2.verb(buck)] [npc2.her] [npc2.hips+] back and forth,"
								+ " ordering [npc.name] to continue hotdogging [npc2.herHim] as [npc2.her] movements force [npc.her] [npc.cock+] up and down between [npc2.her] cheeks."));
				break;
		}
		return "";
	}
	
	public static final SexAction ASS_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Gently fuck [npc2.namePos] ass cheeks.";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently sliding [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
							+ " [npc.name] [npc.verb(start)] steadily bucking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(hotdog)] [npc2.name].",

					"Gently slipping [npc.her] [npc.cock+] between the cheeks of [npc2.namePos] [npc2.ass+],"
							+ " [npc.name] [npc.verb(start)] softly thrusting [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(hotdog)] [npc2.herHim].",

					"Softly pushing the cheeks of [npc2.namePos] [npc2.ass+] together, [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] slowly [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] ass cheeks.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
							+ " [npc.name] [npc.verb(start)] energetically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(hotdog)] [npc2.name].",

					"Desperately pushing [npc.her] [npc.cock+] between the cheeks of [npc2.namePos] [npc2.ass+],"
							+ " [npc.name] [npc.verb(start)] frantically thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(hotdog)] [npc2.herHim].",

					"Greedily pushing the cheeks of [npc2.namePos] [npc2.ass+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to desperately pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] energetically [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc2.namePos] ass cheeks.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Roughly thrusting [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
							+ " [npc.name] [npc.verb(start)] dominantly pumping [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] forcefully [npc.verb(hotdog)] [npc2.name].",

					"Forcefully pushing [npc.her] [npc.cock+] between the cheeks of [npc2.namePos] [npc2.ass+],"
							+ " [npc.name] [npc.verb(start)] violently thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(hotdog)] [npc2.herHim].",

					"Dominantly pushing the cheeks of [npc2.namePos] [npc2.ass+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to slam [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] roughly [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Normal hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] ass cheeks.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
							+ " [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(hotdog)] [npc2.name].",

					"Pushing [npc.her] [npc.cock+] between the cheeks of [npc2.namePos] [npc2.ass+],"
							+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] happily [npc.verb(hotdog)] [npc2.herHim].",

					"Pushing the cheeks of [npc2.namePos] [npc2.ass+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.cock+] between [npc2.namePos] ass cheeks,"
							+ " [npc.name] [npc.verb(start)] energetically bucking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] greedily [npc.verb(hotdog)] [npc2.name].",

					"Desperately pushing [npc.her] [npc.cock+] between the cheeks of [npc2.namePos] [npc2.ass+],"
							+ " [npc.name] [npc.verb(start)] frantically thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(hotdog)] [npc2.herHim].",

					"Greedily pushing the cheeks of [npc2.namePos] [npc2.ass+] together, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to desperately pump [npc.her] [npc.hips] back and forth,"
							+ " breathing in [npc2.her] [npc2.scent] as [npc.she] energetically  [npc.verb(hotdog)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] away from [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] slowly [npc2.verb(thrust)] [npc2.her] [npc2.ass] back against [npc.herHim] and [npc2.verb(continue)] gently forcing [npc.her] [npc.cock+] between [npc2.her] ass cheeks.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.name] firmly [npc2.verb(hold)] [npc.herHim] in place,"
									+ " gently pushing [npc2.her] [npc2.ass] out against [npc.her] groin as [npc2.she] [npc2.verb(force)] [npc.namePos] [npc.cock+] between [npc2.her] ass cheeks.",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.herHim] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " slowly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(sink)] [npc.her] [npc.cock+] deep between [npc2.her] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] violently [npc2.verb(thrust)] [npc2.her] [npc2.ass] back against [npc.herHim] and [npc2.verb(continue)] roughly forcing [npc.her] [npc.cock+] between [npc2.her] ass cheeks.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.she] dominantly hold [npc.herHim] in place,"
									+ " violently pushing [npc2.her] [npc2.ass] out against [npc.her] groin as [npc2.she] roughly [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] ass cheeks.",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.her] ass cheeks."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Desperately trying, and failing, to pull [npc.her] [npc.cock+] away from [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as, ignoring [npc.her] protests,"
									+ " [npc2.she] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.ass] back against [npc.herHim] and [npc2.verb(continue)] rapidly forcing [npc.her] [npc.cock+] between [npc2.her] ass cheeks.",

							"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.name] away, but, totally ignoring [npc.her] protests,"
									+ " [npc2.she] firmly hold [npc.herHim] in place,"
									+ " eagerly pushing [npc2.her] [npc2.ass] out against [npc.her] groin as [npc2.she] desperately [npc2.verb(force)] [npc.her] [npc.cock+] between [npc2.her] ass cheeks.",

							"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.name] to let go of [npc.her] [npc.cock]."
									+ " [npc2.Moaning] in delight, [npc2.name] totally [npc2.verb(ignore)] [npc.her] protests,"
									+ " eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.she] happily [npc2.verb(force)] [npc.her] [npc.cock+] deep between [npc2.her] ass cheeks."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ASS_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ASS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop hotdogging";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out from between [npc2.namePos] ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc.her] [npc.cock+] out from between the cheeks of [npc2.namePos] [npc2.ass+],"
									+ " [npc.name] dominantly [npc.verb(slide)] the [npc.cockHead] of [npc.her] [npc.cock] up and down over [npc2.her] [npc2.ass+] one last time before pulling [npc.her] [npc.hips] back.",

							"Thrusting deep between the cheeks of [npc2.namePos] [npc2.ass+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to the hotdogging."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc.her] [npc.cock+] out from between the cheeks of [npc2.namePos] [npc2.ass+],"
									+ " [npc.name] slaps the [npc.cockHead] of [npc.her] [npc.cock] against [npc2.her] [npc2.ass] one last time before pulling [npc.her] [npc.hips] back.",

							"Pushing deep between the cheeks of [npc2.namePos] [npc2.ass+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to the hotdogging."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] can't help but let out [npc2.sob+] as [npc.name] moves away,"
									+ " and [npc2.she] [npc2.verb(continue)] crying and protesting as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc.herHim] alone.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(beg)] to be left alone."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] back, eager for more of [npc.her] attention.",

							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] lust for [npc.namePos] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction USING_COCK_AGAINST_ASS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Get hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.namePos] [npc2.cock+] between the cheeks of your [npc.ass+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With a gentle [npc.moan], [npc.name] slowly [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.ass+],"
									+ " before softly pressing [npc.her] cheeks together and forcing [npc2.her] [npc2.cock] into the resulting cleavage.",

							"Lining [npc.her] [npc.ass+] up to [npc2.namePos] [npc2.cock+], [npc.name] slowly [npc.verb(push)] [npc.her] cheeks together,"
									+ " softly [npc.moaning] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] into the resulting cleavage."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.ass+],"
									+ " before desperately pressing [npc.her] cheeks together and forcing [npc2.her] [npc2.cock] into the resulting cleavage.",

							"Lining [npc.her] [npc.ass+] up to [npc2.namePos] [npc2.cock+], [npc.name] eagerly [npc.verb(push)] [npc.her] cheeks together,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] into the resulting cleavage."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(force)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.ass+],"
									+ " before greedily pressing [npc.her] cheeks together and violently forcing [npc2.her] [npc2.cock] into the resulting cleavage.",

							"Lining [npc.her] [npc.ass+] up to [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(push)] [npc.her] cheeks together,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] into the resulting cleavage."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] [npc.verb(slide)] [npc2.namePos] [npc2.cock+] over [npc.her] [npc.ass+],"
									+ " before desperately pressing [npc.her] cheeks together and forcing [npc2.her] [npc2.cock] into the resulting cleavage.",

							"Lining [npc.her] [npc.ass+] up to [npc2.namePos] [npc2.cock+], [npc.name] eagerly [npc.verb(push)] [npc.her] cheeks together,"
									+ " [npc.moaning+] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] into the resulting cleavage."));
					break;
				default:
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], gently bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] to fuck [npc.her] ass cheeks.",

							" With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(sink)] [npc2.her] [npc2.cock+] between the cheeks of [npc.namePos] [npc.ass+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] enthusiastically fucking [npc.her] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(sink)] [npc2.her] [npc2.cock+] between the cheeks of [npc.namePos] [npc.ass+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], and, seeking to remind [npc.name] who's in charge,"
									+ " roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] forwards, before starting to ruthlessly fuck [npc.her] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] forwards,"
									+ " seeking to remind [npc.name] who's in charge as [npc2.she] [npc2.verb(start)] ruthlessly fucking [npc.her] ass cheeks."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], bucking [npc2.her] [npc2.hips] forwards as [npc2.she] [npc2.verb(start)] enthusiastically fucking [npc.her] ass cheeks.",

							" With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips] forwards and [npc2.verb(sink)] [npc2.her] [npc2.cock+] between the cheeks of [npc.namePos] [npc.ass+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] between the cheeks of [npc.her] [npc.ass+],"
									+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull away.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep between [npc.her] ass cheeks."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between the cheeks of [npc.namePos] [npc.ass+],"
								+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(hotdog)] [npc.herHim].",
	
						" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep between the [npc.assSize] cheeks of [npc.namePos] [npc.ass].",
								
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] back and forth between the cheeks of [npc.namePos] [npc.ass+]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.ass],"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
	
						" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
								+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] back and forth between the cheeks of [npc.her] [npc.ass+].",
	
						" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.ass+]."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] between the cheeks of [npc.namePos] [npc.ass+],"
								+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(hotdog)] [npc.herHim].",
	
						" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] gently thrusting [npc2.her] [npc2.cock+] between the [npc.assSize] cheeks of [npc.namePos] [npc.ass].",
								
						" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] back and forth between the cheeks of [npc.namePos] [npc.ass+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between the cheeks of [npc.namePos] [npc.ass+],"
								+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(hotdog)] [npc.herHim].",
	
						" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] roughly slamming [npc2.her] [npc2.cock+] deep between the [npc.assSize] cheeks of [npc.namePos] [npc.ass].",
								
						" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] back and forth between the cheeks of [npc.namePos] [npc.ass+]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep between the cheeks of [npc.namePos] [npc.ass+],"
								+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(hotdog)] [npc.herHim].",
	
						" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep between the [npc.assSize] cheeks of [npc.namePos] [npc.ass].",
								
						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] back and forth between the cheeks of [npc.namePos] [npc.ass+]."));
				break;
		}
		return "";
	}
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Gently use [npc2.namePos] [npc2.cock+] to fuck your ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep between the cheeks of [npc.her] [npc.ass+].",

					"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] back and forth between [npc.her] ass cheeks.",

					"Slowly thrusting [npc.her] [npc.hips] back,"
							+ " a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements drive [npc2.namePos] [npc2.cock+] up and down between the cheeks of [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Use [npc2.namePos] [npc2.cock+] to fuck your ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep between the cheeks of [npc.her] [npc.ass+].",

					"With [npc.a_moan+], [npc.name] greedily [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] back and forth between [npc.her] ass cheeks.",

					"Desperately thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(drive)] [npc2.namePos] [npc2.cock+] up and down between the cheeks of [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Roughly force [npc2.namePos] [npc2.cock+] up and down between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Violently slamming [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep between the cheeks of [npc.her] [npc.ass+].",

					"With [npc.a_moan+], [npc.name] roughly [npc.verb(start)] slamming [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] back and forth between [npc.her] ass cheeks.",

					"Roughly thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.she] dominantly [npc.verb(drive)] [npc2.namePos] [npc2.cock+] up and down between the cheeks of [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.ass+] out against [npc2.name] in order to force [npc2.her] [npc2.cock] between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep between the cheeks of [npc.her] [npc.ass+].",

					"With [npc.a_moan+], [npc.name] [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] back and forth between [npc.her] ass cheeks.",

					"Thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.she] [npc.verb(drive)] [npc2.namePos] [npc2.cock+] up and down between the cheeks of [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_COCK_AGAINST_ASS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly push your [npc.ass+] out against [npc2.name] as [npc2.her] [npc2.cock] thrusts between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Eagerly pushing [npc.her] [npc.hips] back, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep between the cheeks of [npc.her] [npc.ass+].",

					"With [npc.a_moan+], [npc.name] greedily [npc.verb(start)] pushing [npc.her] [npc.hips] back, forcing [npc2.namePos] [npc2.cock+] back and forth between [npc.her] ass cheeks.",

					"Desperately thrusting [npc.her] [npc.hips] back,"
							+ " [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.she] greedily [npc.verb(drive)] [npc2.namePos] [npc2.cock+] up and down between the cheeks of [npc.her] [npc.ass+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_AGAINST_ASS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.ass+] away from [npc2.namePos] [npc2.cock+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] gently fucking [npc.her] ass cheeks.",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.ass] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] slowly sliding in and out between [npc.her] ass cheeks.",

							"Trying desperately to pull [npc.her] [npc.ass] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] gently sliding deep between [npc.her] ass cheeks."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Y[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] eagerly fucking [npc.her] ass cheeks.",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.ass] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] eagerly sliding in and out between [npc.her] ass cheeks.",

							"Trying desperately to pull [npc.her] [npc.ass] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] eagerly sliding deep between [npc.her] ass cheeks."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] tears start to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
									+ " weakly trying to pull away from [npc2.namePos] [npc2.cock] as [npc2.she] [npc2.verb(continue)] roughly fucking [npc.her] ass cheeks.",

							"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.ass] away from [npc2.namePos] [npc2.cock],"
									+ " struggling in desperation as [npc2.she] [npc2.verb(continue)] roughly slamming in and out between [npc.her] ass cheeks.",

							"Trying desperately to pull [npc.her] [npc.ass] away from [npc2.name], [npc.name] [npc.sobVerb] in distress as [npc2.her] [npc2.cock+] [npc2.verb(continue)] roughly slamming deep between [npc.her] ass cheeks."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ASS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop hotdogged";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.cock] out from between your ass cheeks.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Yanking [npc2.namePos] [npc2.cock] out from between [npc.her] ass cheeks, [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.herHim] to stop.",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.cock] out from between [npc.her] ass cheeks."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.cock] out from between [npc.her] ass cheeks, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.herHim] to stop.",

							"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.cock] out from between [npc.her] ass cheeks."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.name] [npc.has]n't finished with [npc2.herHim] just yet.",

							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue fucking [npc.her] ass cleavage."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
