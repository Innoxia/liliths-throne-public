package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
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
 * @since 0.1.82
 * @version 0.3.4.5
 * @author Innoxia
 */
public class PenisAnus {

	// -- Methods for multiple ongoing characters:
	
	static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingDP) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingDP, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
	}

	private static List<GameCharacter> getCharactersForParsing(GameCharacter characterReceivingDP) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingDP)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	public static GameCharacter getPrimaryDPPerformer(GameCharacter characterReceivingDP) {
		return Main.sex.getOngoingActionsMap(characterReceivingDP).get(SexAreaOrifice.ANUS).keySet().iterator().next();
	}
	
	// ---
	
	public static final SexAction TEASE_PENIS_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Tease asshole";
		}

		@Override
		public String getActionDescription() {
			return "Tease [npc2.name] by sliding the [npc.cockHead] of your [npc.cock] over [npc2.her] [npc2.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			if(!getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).isEmpty()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Not deterred by the fact that [npc3.namePos] [npc3.cock] is already deep in [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(start)] slowly teasing the [npc.cockTip+] of [npc.her] own [npc.cock+] up against [npc2.her] [npc2.assCloaca+], threatening to start double-penetrating [npc.herHim] at any moment.",
								"With a soft [npc.moan], [npc.name] gently [npc.verb(guide)] the [npc.cockTip+] of [npc.her] [npc.cock] over the exposed parts of [npc2.namePos] [npc2.assCloaca+],"
										+ " indicating that [npc.she] might join [npc3.name] and start double-penetrating [npc2.her] [npc2.asshole+].",
								"Gently sliding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over the exposed parts of [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(signal)] that [npc.she] might join [npc3.name] in some double-penetration.")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Not deterred by the fact that [npc3.namePos] [npc3.cock] is already deep in [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(start)] roughly grinding the [npc.cockTip+] of [npc.her] own [npc.cock+] up against [npc2.her] [npc2.assCloaca+], threatening to start double-penetrating [npc.herHim] at any moment.",
								"With [npc.a_moan+], [npc.name] forcefully [npc.verb(rub)] the [npc.cockTip+] of [npc.her] [npc.cock] over the exposed parts of [npc2.namePos] [npc2.assCloaca+],"
										+ " indicating that [npc.she] might join [npc3.name] and start double-penetrating [npc2.her] [npc2.asshole+].",
								"Roughly rubbing the [npc.cockTip+] of [npc.her] [npc.cock] up and down over the exposed parts of [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(signal)] that [npc.she] might join [npc3.name] in some double-penetration.")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Not deterred by the fact that [npc3.namePos] [npc3.cock] is already deep in [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(start)] grinding the [npc.cockTip+] of [npc.her] own [npc.cock+] up against [npc2.her] [npc2.assCloaca+], threatening to start double-penetrating [npc.herHim] at any moment.",
								"With [npc.a_moan+], [npc.name] [npc.verb(rub)] the [npc.cockTip+] of [npc.her] [npc.cock] over the exposed parts of [npc2.namePos] [npc2.assCloaca+],"
										+ " indicating that [npc.she] might join [npc3.name] and start double-penetrating [npc2.her] [npc2.asshole+].",
								"Rubbing the [npc.cockTip+] of [npc.her] [npc.cock] up and down over the exposed parts of [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(signal)] that [npc.she] might join [npc3.name] in some double-penetration.")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Not deterred by the fact that [npc3.namePos] [npc3.cock] is already deep in [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(start)] eagerly grinding the [npc.cockTip+] of [npc.her] own [npc.cock+] up against [npc2.her] [npc2.assCloaca+], threatening to start double-penetrating [npc.herHim] at any moment.",
								"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(rub)] the [npc.cockTip+] of [npc.her] [npc.cock] over the exposed parts of [npc2.namePos] [npc2.assCloaca+],"
										+ " indicating that [npc.she] might join [npc3.name] and start double-penetrating [npc2.her] [npc2.asshole+].",
								"Eagerly rubbing the [npc.cockTip+] of [npc.her] [npc.cock] up and down over the exposed parts of [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(signal)] that [npc.she] might join [npc3.name] in some double-penetration.")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Guiding [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(start)] slowly teasing the [npc.cockTip+] up and down over [npc2.her] [npc2.assCloaca+], ready to penetrate [npc2.herHim] at any moment.",
								"With a soft [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.asshole+], before starting to gently slide the [npc.cockTip] up and down over [npc2.her] [npc2.assCloaca+].",
								"Gently sliding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grinding [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(pull)] back a little before starting to slide the [npc.cockTip+] up and down over [npc2.her] [npc2.assCloaca+], ready to start fucking [npc2.herHim] at any moment.",
								"With [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.asshole+],"
										+ " before starting to roughly [npc2.verb(grind)] the [npc.cockTip] up and down over [npc2.her] [npc2.assCloaca+].",
								"Roughly grinding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to start fucking [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Guiding [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(start)] sliding the [npc.cockTip+] up and down over [npc2.her] [npc2.assCloaca+], ready to penetrate [npc2.herHim] at any moment.",
								"With [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.asshole+], before starting to slide the [npc.cockTip] up and down over [npc2.her] [npc2.assCloaca+].",
								"Sliding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Guiding [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(start)] eagerly sliding the [npc.cockTip+] up and down over [npc2.her] [npc2.assCloaca+], ready to penetrate [npc2.herHim] at any moment.",
								"With [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.asshole+], before starting to eagerly slide the [npc.cockTip] up and down over [npc2.her] [npc2.assCloaca+].",
								"Eagerly sliding the [npc.cockTip+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] at the thought of being able to penetrate [npc2.herHim] whenever [npc.she] [npc.verb(feel)] like it."));
						break;
				}
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.asshole+] being stimulated,"
										+ " and [npc2.she] gently [npc2.verb(push)] [npc2.her] [npc2.asshole+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], before gently pushing [npc2.her] [npc2.asshole+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock] stimulating [npc2.her] [npc2.asshole+], and gently [npc2.verb(push)] [npc2.her] [npc2.hips+] out in response."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.asshole+] being stimulated,"
										+ " and [npc2.she] eagerly [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before eagerly thrusting [npc2.her] [npc2.asshole+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock] stimulating [npc2.her] [npc2.asshole+], and desperately [npc2.verb(buck)] [npc2.her] [npc2.hips+] out in response."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.asshole+] being stimulated,"
										+ " and [npc2.she] forcefully [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before roughly thrusting [npc2.her] [npc2.asshole+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock] stimulating [npc2.her] [npc2.asshole+], and violently [npc2.verb(buck)] [npc2.her] [npc2.hips+] out in response."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] feels [npc2.her] [npc2.asshole+] being stimulated,"
										+ " and [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.verb(let)] out a [npc2.moan], before thrusting [npc2.her] [npc2.asshole+] out against [npc.namePos] [npc.cock].",
								" [npc2.Name] [npc2.moanVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock] stimulating [npc2.her] [npc2.asshole+], and [npc2.verb(buck)] [npc2.her] [npc2.hips+] out in response."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isAssVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of being about to lose [npc2.her] anal virginity.",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob] as [npc2.she] [npc2.verb(realise)] that [npc2.she] might be about to lose [npc2.her] anal virginity.",
										" [npc2.Name] [npc2.sobVerb] in distress at the thought of losing [npc2.her] anal virginity to [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] at the thought of what's about to happen, [npc2.speech(No! Stop! I've never done anal before!)]",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! I've never done anal!)]",
										" [npc2.Name] [npc2.sobVerb] in distress at the thought of what's about to happen, before desperately begging, [npc2.speech(No! Stop! I don't want to lose my anal virginity!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.assCloaca] away from [npc.namePos] [npc.cock].",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob] as [npc2.she] [npc2.verb(struggle)] against [npc.name].",
										" [npc2.Name] [npc2.sobVerb] in distress as [npc2.she] [npc2.verb(try)] to pull away from [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.assCloaca] away from [npc.namePos] [npc.cock],"
												+ " [npc2.speech(No! Don't! Please, get away from me!)]",
										" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before pleading, [npc2.speech(Please! Don't do this! Leave me alone!)]",
										" [npc2.Name] [npc2.sobVerb] in distress as [npc2.name] [npc2.verb(beg)], [npc2.speech(No! Stop! Get away from there!)]"));
							}
						}
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction FORCE_PENIS_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Cock tease (anal)";
		}

		@Override
		public String getActionDescription() {
			return "Tease [npc2.name] by sliding the [npc2.cockTip] of [npc2.her] [npc2.cock] over your [npc.asshole+].";
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
			if(!getOngoingCharacters(Main.sex.getCharacterPerformingAction()).isEmpty()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Not satisfied with having just [npc3.namePos] [npc3.cock] penetrating [npc.herHim], [npc.name] [npc.verb(take)] hold of [npc2.namePos] [npc2.cock+],"
										+ " before guiding it up to an exposed part of [npc.her] [npc.asshole] and gently teasing the [npc.cockHead] over [npc.her] [npc.assCloaca+].",
								"Wanting more than just [npc3.namePos] [npc3.cock+] stuffed in [npc.her] [npc.asshole+],"
										+ " [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.cock+], before starting to slide the [npc2.cockTip] up and down over an exposed part of [npc.her] [npc.assCloaca+].",
								"With [npc3.namePos] [npc3.cock+] still thrusting in and out of [npc.her] [npc.asshole+],"
										+ " [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before gently sliding the [npc2.cockTip+] over an exposed part of [npc.her] [npc.assCloaca+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Not satisfied with having just [npc3.namePos] [npc3.cock] penetrating [npc.herHim], [npc.name] roughly [npc.verb(grab)] hold of [npc2.namePos] [npc2.cock+],"
										+ " before guiding it up to an exposed part of [npc.her] [npc.asshole] and forcefully rubbing the [npc.cockHead] over [npc.her] [npc.assCloaca+].",
								"Wanting more than just [npc3.namePos] [npc3.cock+] stuffed in [npc.her] [npc.asshole+],"
										+ " [npc.name] roughly [npc.verb(seize)] hold of [npc2.namePos] [npc2.cock+], before starting to forcefully rub the [npc2.cockTip] up and down over an exposed part of [npc.her] [npc.assCloaca+].",
								"With [npc3.namePos] [npc3.cock+] still thrusting in and out of [npc.her] [npc.asshole+],"
										+ " [npc.name] forcefully [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before roughly rubbing the [npc2.cockTip+] over an exposed part of [npc.her] [npc.assCloaca+].")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Not satisfied with having just [npc3.namePos] [npc3.cock] penetrating [npc.herHim], [npc.name] [npc.verb(take)] hold of [npc2.namePos] [npc2.cock+],"
										+ " before guiding it up to an exposed part of [npc.her] [npc.asshole] and rubbing the [npc.cockHead] over [npc.her] [npc.assCloaca+].",
								"Wanting more than just [npc3.namePos] [npc3.cock+] stuffed in [npc.her] [npc.asshole+],"
										+ " [npc.name] [npc.verb(take)] hold of [npc2.namePos] [npc2.cock+], before starting to rub the [npc2.cockTip] up and down over an exposed part of [npc.her] [npc.assCloaca+].",
								"With [npc3.namePos] [npc3.cock+] still thrusting in and out of [npc.her] [npc.asshole+],"
										+ " [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before rubbing the [npc2.cockTip+] over an exposed part of [npc.her] [npc.assCloaca+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getPrimaryDPPerformer(Main.sex.getCharacterTargetedForSexAction(this))),
								UtilText.returnStringAtRandom(
								"Not satisfied with having just [npc3.namePos] [npc3.cock] penetrating [npc.herHim], [npc.name] eagerly [npc.verb(grab)] hold of [npc2.namePos] [npc2.cock+],"
										+ " before guiding it up to an exposed part of [npc.her] [npc.asshole] and desperately rubbing the [npc.cockHead] over [npc.her] [npc.assCloaca+].",
								"Wanting more than just [npc3.namePos] [npc3.cock+] stuffed in [npc.her] [npc.asshole+],"
										+ " [npc.name] desperately [npc.verb(seize)] hold of [npc2.namePos] [npc2.cock+], before starting to energetically rub the [npc2.cockTip] up and down over an exposed part of [npc.her] [npc.assCloaca+].",
								"With [npc3.namePos] [npc3.cock+] still thrusting in and out of [npc.her] [npc.asshole+],"
										+ " [npc.name] eagerly [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before happily rubbing the [npc2.cockTip+] over an exposed part of [npc.her] [npc.assCloaca+].")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before guiding it up to [npc.her] [npc.asshole+]."
										+ " Slowly pushing the [npc2.cockTip+] up and down over [npc.her] [npc.assCloaca+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",
								"With a soft [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] and [npc.verb(guide)] it up to [npc.her] [npc.asshole+],"
										+ " before starting to gently slide the [npc2.cockTip] up and down over [npc.her] [npc.assCloaca+].",
								"Grabbing [npc2.namePos] [npc2.cock+], [npc.name] gently [npc.verb(slide)] the [npc2.cockTip+] over [npc.her] [npc.asshole+],"
										+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before yanking it up to [npc.her] [npc.asshole+]."
										+ " Roughly forcing the [npc2.cockTip+] up and down over [npc.her] [npc.assCloaca+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",
								"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] and yank it up to [npc.her] [npc.asshole+],"
										+ " before starting to roughly [npc.verb(force)] the [npc2.cockTip] up and down over [npc.her] [npc.assCloaca+].",
								"Grabbing [npc2.namePos] [npc2.cock+], [npc.name] roughly [npc.verb(grind)] the [npc2.cockTip+] over [npc.her] [npc.asshole+],"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before guiding it up to [npc.her] [npc.asshole+]."
										+ " Pushing the [npc2.cockTip+] up and down over [npc.her] [npc.assCloaca+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",
								"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] and [npc.verb(guide)] it up to [npc.her] [npc.asshole+],"
										+ " before starting to slide the [npc2.cockTip] up and down over [npc.her] [npc.assCloaca+].",
								"Grabbing [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(slide)] the [npc2.cockTip+] over [npc.her] [npc.asshole+],"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], before guiding it up to [npc.her] [npc.asshole+]."
										+ " Eagerly pushing the [npc2.cockTip+] up and down over [npc.her] [npc.assCloaca+], [npc.she] [npc.verb(tease)] [npc2.name] with the promise of penetration at any moment.",
								"With [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] and [npc.verb(guide)] it up to [npc.her] [npc.asshole+],"
										+ " before starting to eagerly slide the [npc2.cockTip] up and down over [npc.her] [npc.assCloaca+].",
								"Grabbing [npc2.namePos] [npc2.cock+], [npc.name] eagerly [npc.verb(slide)] the [npc2.cockTip+] over [npc.her] [npc.asshole+],"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(tease)] [npc2.herHim] with the promise of penetration."));
						break;
				}
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] gently rubbing [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan], before gently sliding [npc2.her] [npc2.cock] back and forth over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] gently sliding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] roughly grinding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], before forcefully grinding [npc2.her] [npc2.cock] back and forth over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] being stimulated, and, seeking to remind [npc.name] who's in charge,"
										+ " [npc2.she] [npc2.verb(start)] roughly grinding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] enthusiastically rubbing [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before eagerly sliding [npc2.her] [npc2.cock] back and forth over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] eagerly sliding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], and [npc2.she] [npc2.verb(start)] rubbing [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.moan], before sliding [npc2.her] [npc2.cock] back and forth over [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] in delight as [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] being stimulated, and, needing no further encouragement,"
										+ " [npc2.she] [npc2.verb(start)] sliding [npc2.her] [npc2.her] [npc2.cock] up and down over [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.verb(try)] to pull [npc2.her] [npc2.cock+] away from [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] out a desperate [npc2.sob], before frantically trying to pull [npc2.her] [npc2.cock+] away from [npc.namePos] [npc.assCloaca+].",
	
								" [npc2.Name] [npc2.sobsVerb] in distress as [npc2.she] [npc2.verb(beg)] [npc.name] to let go of [npc2.her] [npc2.cock]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction ASSHOLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Asshole control";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your internally-muscled asshole down around [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getAssOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				return UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"Letting out [npc.a_moan+], [npc.name] [npc.verb(concentrate)] on squeezing the extra internal muscles within [npc.her] [npc.asshole] down around"
								+ " #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks+(true)].",
						(!isTargetedCharacterInanimate()
							?"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling the extra muscles lining the insides of [npc.her] [npc.asshole]."
								+ " Gripping and squeezing them down around #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks+(true)], [npc.name] [npc.verb(cause)] the two of "
								+ (getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.game.getPlayer())?"you":"them")
								+ " to let out involuntary cries of pleasure."
							:""),
						"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans] falling into a steady rhythm as [npc.she] [npc.verb(concentrate)]"
								+ " on squeezing the extra muscles within [npc.her] [npc.asshole+] down around the two [npc2.cocks(true)] currently double-penetrating [npc.herHim].",
						"With [npc.a_moan+], [npc.name] [npc.verb(focus)] on controlling the extra muscles deep within [npc.her] [npc.asshole],"
								+ " gripping them down and massaging #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cock+] as [npc.she] [npc.verb(squeal)] in pleasure."));
				
			} else {
				return UtilText.returnStringAtRandom(
						"Letting out [npc.a_moan+], [npc.name] [npc.verb(concentrate)] on squeezing the extra internal muscles within [npc.her] [npc.asshole] down around [npc2.namePos] [npc2.cock+].",
						(!isTargetedCharacterInanimate()
							?"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling the extra muscles lining the insides of [npc.her] [npc.asshole]."
								+ " Gripping and squeezing them down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(cause)] [npc2.herHim] to let out an involuntary cry of pleasure."
							:""),
						"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans] falling into a steady rhythm as [npc.she] [npc.verb(concentrate)]"
								+ " on squeezing the extra muscles within [npc.her] [npc.asshole+] down around [npc2.namePos] [npc2.cock+].",
						"With [npc.a_moan+], [npc.name] [npc.verb(focus)] on controlling the extra muscles deep within [npc.her] [npc.asshole],"
								+ " gripping them down and massaging [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(squeal)] in pleasure.");
			}
		}
	};
	
	public static final SexAction PENIS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Start anal";
		}

		@Override
		public String getActionDescription() {
			return "Sink your [npc.cock+] into [npc2.namePos] [npc2.asshole+] and start fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			String assTargeting = "between [npc2.namePos] ass cheeks";
			if(Main.sex.getCharacterTargetedForSexAction(this).getGenitalArrangement()!=GenitalArrangement.NORMAL) {
				assTargeting = "over [npc2.namePos] [npc2.assCloaca+]";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Slowly teasing the [npc.cockTip+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out a little [npc.moan] before slowly pushing forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+","
									+ " and with a slow, steady pressure, [npc.she] gently [npc.verb(sink)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockTip+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+", "
									+ "and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly grinding the [npc.cockTip+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before violently slamming forwards, forcing [npc.her] [npc.cock+] deep into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+","
									+ " and with a forceful thrust, [npc.she] roughly [npc.verb(slam)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Eagerly teasing the [npc.cockTip+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, greedily sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+","
									+ " and with a determined thrust, [npc.she] eagerly [npc.verb(sink)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Teasing the [npc.cockTip+] of [npc.her] [npc.cock] over [npc2.namePos] [npc2.assCloaca+],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] before thrusting forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] the [npc.cockTip+] of [npc.her] [npc.cock] "+assTargeting+","
									+ " and with a little thrust, [npc.she] [npc.verb(sink)] it deep into [npc2.her] [npc2.asshole+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as the [npc.cock+] enters [npc2.herHim],"
										+ " before gently thrusting out [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.asshole+].",
	
								" With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips],"
										+ " sinking [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before violently thrusting out [npc2.her] [npc2.hips] in order to force it even deeper into [npc2.her] [npc2.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] violently bucking [npc2.her] [npc2.hips],"
										+ " roughly forcing [npc.name] to sink [npc.her] [npc.cock+] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before eagerly bucking [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips],"
										+ " desperately helping to sink [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the [npc.cock+] enters [npc2.herHim],"
										+ " before bucking [npc2.her] [npc2.hips] in order to sink it even deeper into [npc2.her] [npc2.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] bucking [npc2.her] [npc2.hips],"
										+ " helping to sink [npc.namePos] [npc.cock+] even deeper into [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as the [npc.cock+] enters [npc2.herHim],"
										+ " and, with tears running down [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to pull out.",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from the unwanted penetration,"
										+ " tears running down [npc2.her] [npc2.face] as [npc.namePos] unwelcome [npc.cock] pushes deep into [npc2.her] [npc2.asshole+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	

	public static final SexAction PENIS_FUCKING_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		private List<GameCharacter> getCharactersForParsing() {
			return PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();
			
			int size = PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size();
			
			map.put("one other character fucking [npc2.namePos] asshole", size==1);
			map.put("[npc.namePos] penis to be exposed", Main.sex.getCharacterPerformingAction().isPenetrationTypeExposed(SexAreaPenetration.PENIS));
			map.put("[npc.namePos] penis to be free", SexAreaPenetration.PENIS.isFree(Main.sex.getCharacterPerformingAction()));
			
			return map;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()==1
					&& !getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Double-penetration (anal)";
		}
		
		@Override
		public String getActionDescription() {
			return UtilText.parse(getCharactersForParsing(),
					"Join [npc3.name] in fucking [npc2.namePos] [npc2.asshole+].");
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"Wanting to join in on the fun, [npc.name] [npc.verb(bring)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.asshole+], and the next time [npc3.name] [npc3.verb(pull)] back,"
									+ " [npc.she] gently [npc.verb(push)] forwards, pushing [npc.her] [npc.cock+] in alongside [npc3.hers] and joining [npc3.herHim] in double-penetrating [npc2.name]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"Wanting to join in on the fun, [npc.name] [npc.verb(bring)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.asshole+], and the next time [npc3.name] [npc3.verb(pull)] back,"
									+ " [npc.she] roughly [npc.verb(thrust)] forwards, forcing [npc.her] [npc.cock+] in alongside [npc3.hers] and joining [npc3.herHim] in double-penetrating [npc2.name]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"Wanting to join in on the fun, [npc.name] [npc.verb(bring)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.asshole+], and the next time [npc3.name] [npc3.verb(pull)] back,"
									+ " [npc.she] [npc.verb(thrust)] forwards, pushing [npc.her] [npc.cock+] in alongside [npc3.hers] and joining [npc3.herHim] in double-penetrating [npc2.name]."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
							"Wanting to join in on the fun, [npc.name] [npc.verb(bring)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.asshole+], and the next time [npc3.name] [npc3.verb(pull)] back,"
									+ " [npc.she] desperately [npc.verb(thrust)] forwards, ramming [npc.her] [npc.cock+] in alongside [npc3.hers] and joining [npc3.herHim] in double-penetrating [npc2.name]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as the second [npc.cock] enters [npc2.her] [npc2.asshole+],"
										+ " before gently thrusting [npc2.her] [npc2.hips] out in order to sink it deeply in beside [npc3.namePos] [npc3.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the second [npc.cock] enters [npc2.her] [npc2.asshole+],"
										+ " before roughly slamming [npc2.her] [npc2.hips] out in order to sink it deeply in beside [npc3.namePos] [npc3.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the second [npc.cock] enters [npc2.her] [npc2.asshole+],"
										+ " before bucking [npc2.her] [npc2.hips] out in order to sink it deeply in beside [npc3.namePos] [npc3.cock+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as the second [npc.cock] enters [npc2.herHim],"
										+ " and, with tears running down [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)] for [npc.name] and [npc3.name] to pull out.",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)], in vain, to pull away from the second unwanted penetration,"
										+ " and tears start once again running freely down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(feel)] [npc.namePos] unwelcome [npc.cock] pushing deep into [npc2.her] [npc2.asshole+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as the second [npc.cock] enters [npc2.her] [npc2.asshole+],"
										+ " before greedily bucking [npc2.her] [npc2.hips] out in order to sink it deeply in beside [npc3.namePos] [npc3.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from the two [npc.cocks(true)] inside of [npc2.herHim],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] and [npc3.name] to pull out of [npc2.her] [npc2.asshole+].",
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] and [npc3.name] away,"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for the two of "
										+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())?"you":"them")+" to pull out of [npc2.her] [npc2.asshole+].",
								" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
										+ " [npc2.name] weakly [npc2.verb(struggle)] in a vain attempt to dislodge the [npc.cocks(true)] thrusting into [npc2.her] [npc2.asshole+], pleading and crying for [npc.name] and [npc3.name] to leave [npc2.herHim] alone.")));
					case SUB_NORMAL:
						return (UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(help)] to sink the two [npc.cocks(true)] even deeper into [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)] for [npc.name] and [npc3.name] to carry on fucking [npc2.herHim].",
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips+] out,"
										+ " begging for the two of "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"you to continue fucking [npc2.herHim] as [npc2.her] movements help to sink your [npc.cocks(true)] deep into [npc2.her] [npc2.asshole+]."
												:"them to continue fucking [npc2.herHim] as [npc2.her] movements help to sink their [npc.cocks(true)] deep into [npc2.her] [npc2.asshole+]."))));
					case DOM_GENTLE:
						return (UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" [npc2.Name] slowly [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(start)] gently forcing the two [npc.cocks(true)] even deeper into [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, slowly bucking [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)] [npc.name] and [npc3.name] to carry on fucking [npc2.herHim].",
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.hips+] out,"
										+ " begging for the two of "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"you to continue fucking [npc2.herHim] as [npc2.her] rhythmic movements help to sink your [npc.cocks(true)] deep into [npc2.her] [npc2.asshole+]."
												:"them to continue fucking [npc2.herHim] as [npc2.her] rhythmic movements help to sink their [npc.cocks(true)] deep into [npc2.her] [npc2.asshole+]."))));
					case DOM_ROUGH:
						return (UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(force)] the two [npc.cocks(true)] even deeper into [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, roughly thrusting [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(order)] [npc.name] and [npc3.name] to carry on fucking [npc2.herHim].",
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips+] out,"
										+ " ordering the two of "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"you to continue fucking [npc2.herHim] as [npc2.her] violent movements help to sink your [npc.cocks(true)] deep into [npc2.her] [npc2.asshole+]."
												:"them to continue fucking [npc2.herHim] as [npc2.her] violent movements help to sink their [npc.cocks(true)] deep into [npc2.her] [npc2.asshole+]."))));
					default:
						return (UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(action)), UtilText.returnStringAtRandom(
								" [npc2.Name] desperately [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] eagerly [npc2.verb(force)] the two [npc.cocks(true)] even deeper into [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, energetically thrusting [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)] for [npc.name] and [npc3.name] to carry on fucking [npc2.herHim].",
								" [npc2.Moaning] in delight, [npc2.name] happily [npc2.verb(buck)] [npc2.her] [npc2.hips+] out,"
										+ " begging for the two of "+(getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).contains(Main.game.getPlayer())
												?"you to continue fucking [npc2.herHim] as [npc2.her] energetic movements help to sink your [npc.cocks(true)] deep into [npc2.her] [npc2.asshole+]."
												:"them to continue fucking [npc2.herHim] as [npc2.her] energetic movements help to sink their [npc.cocks(true)] deep into [npc2.her] [npc2.asshole+]."))));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.returnStringAtRandom(
								" Desperately trying, and failing, to pull away from [npc.namePos] [npc.cock],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(beg)] for [npc.name] to pull out of [npc2.her] [npc2.asshole+].",
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to pull out of [npc2.her] [npc2.asshole+].",
								" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
										+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name], pleading and crying for [npc.herHim] to pull out of [npc2.her] [npc2.asshole+]."));
					case SUB_NORMAL:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(help)] to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(push)] [npc2.her] [npc2.hips+] out,"
										+ " begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.asshole+]."));
					case DOM_GENTLE:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] slowly [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(start)] gently imploring [npc.name] to continue fucking [npc2.her] [npc2.asshole+].",
								" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, slowly bucking [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(implore)] [npc.name] to carry on fucking [npc2.herHim].",
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(push)] [npc2.her] [npc2.hips+] back,"
										+ " begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.asshole+]."));
					case DOM_ROUGH:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(demand)] that [npc.name] [npc.verb(continue)] fucking [npc2.herHim].",
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, roughly slamming [npc2.her] [npc2.hips] into [npc.her] groin, [npc2.she] [npc2.verb(order)] [npc.name] to carry on fucking [npc2.herHim].",
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips+] out into [npc.namePos] groin,"
										+ " ordering [npc.herHim] to continue fucking [npc2.herHim] as [npc2.her] movements force [npc.her] [npc.cock+] deep into [npc2.her] [npc2.asshole+]."));
					default:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(help)] to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.asshole+].",
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
										+ " and, eagerly thrusting out [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(beg)] for [npc.name] to carry on fucking [npc2.herHim].",
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.hips+] out,"
										+ " eagerly begging for [npc.name] to continue fucking [npc2.herHim] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.asshole+]."));
				}
			}
		}
		return "";
	}
	
	public static final SexAction PENIS_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle anal";
		}

		@Override
		public String getActionDescription() {
			return "Gently slide your [npc.cock] in and out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"With [npc.her] [npc.cock+] rubbing up against [npc3.namePos], [npc.name] gently [npc.verb(thrust)] deep inside of [npc2.namePos] [npc2.asshole+],"
								+ " letting out a little [npc.moan] with every buck of [npc.her] [npc.hips] as [npc.she] slowly [npc.verb(fuck)] [npc2.herHim].",
						"Joining [npc3.name] in double-penetrating [npc2.namePos] [npc2.asshole+], [npc.name] softly [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.herHim].",
						"With both [npc.her] and [npc3.namePos] [npc.cocks(true)] stuffed deep in [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] gently [npc.verb(pump)] [npc.her] [npc.hips] back and forth, breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] [npc.verb(start)] rocking [npc.her] [npc.hips] back and forth, letting out a little [npc.moan] with every thrust as [npc.she] slowly [npc.verb(fuck)] [npc2.name].",
						"Slowly pushing [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] softly [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out a little [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.herHim].",
						"Sliding [npc.her] [npc.cock+] into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(start)] to gently pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] slowly [npc.verb(fuck)] [npc2.herHim]."));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Anal";
		}

		@Override
		public String getActionDescription() {
			return "Continue thrusting your [npc.cock+] in and out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"With [npc.her] [npc.cock+] rubbing up against [npc3.namePos], [npc.name] eagerly [npc.verb(thrust)] deep inside of [npc2.namePos] [npc2.asshole+],"
								+ " letting out [npc.a_moan+] with every energetic buck of [npc.her] [npc.hips] as [npc.she] frantically [npc.verb(fuck)] [npc2.herHim].",
						"Joining [npc3.name] in double-penetrating [npc2.namePos] [npc2.asshole+], [npc.name] enthusiastically [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.herHim].",
						"With both [npc.her] and [npc3.namePos] [npc.cocks(true)] stuffed deep in [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] happily [npc.verb(pump)] [npc.her] [npc.hips] back and forth, breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] [npc.verb(start)] enthusiastically rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",
						"Enthusiastically pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] frantically [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.herHim].",
						"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(fuck)] [npc2.herHim]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough anal";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.cock+] in and out of [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"With [npc.her] [npc.cock+] rubbing up against [npc3.namePos], [npc.name] roughly [npc.verb(thrust)] deep inside of [npc2.namePos] [npc2.asshole+],"
								+ " letting out [npc.a_moan+] with every violent slam of [npc.her] [npc.hips] as [npc.she] forcefully [npc.verb(fuck)] [npc2.herHim].",
						"Joining [npc3.name] in double-penetrating [npc2.namePos] [npc2.asshole+], [npc.name] roughly [npc.verb(slam)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] forcefully [npc.verb(fuck)] [npc2.herHim].",
						"With both [npc.her] and [npc3.namePos] [npc.cocks(true)] stuffed deep in [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] violently [npc.verb(thrust)] [npc.her] [npc.hips] back and forth, breathing in [npc2.namePos] [npc2.scent] as [npc.she] aggressively [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Brutally pounding [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] brutally [npc.verb(fuck)] [npc2.name].",
						"Violently thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] [npc.verb(start)] roughly slamming [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] forcefully [npc.verb(fuck)] [npc2.herHim].",
						"Forcefully driving [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to roughly slam [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] violently [npc.verb(fuck)] [npc2.herHim]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Anal";
		}

		@Override
		public String getActionDescription() {
			return "Continue fucking [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"With [npc.her] [npc.cock+] rubbing up against [npc3.namePos], [npc.name] [npc.verb(thrust)] deep inside of [npc2.namePos] [npc2.asshole+],"
								+ " letting out [npc.a_moan+] with every buck of [npc.her] [npc.hips] as [npc.she] happily [npc.verb(fuck)] [npc2.herHim].",
						"Joining [npc3.name] in double-penetrating [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] [npc.verb(fuck)] [npc2.herHim].",
						"With both [npc.her] and [npc3.namePos] [npc.cocks(true)] stuffed deep in [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] happily [npc.verb(pump)] [npc.her] [npc.hips] back and forth, breathing in [npc2.namePos] [npc2.scent] as [npc.she] [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] [npc.verb(start)] rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",
						"Pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] [npc.verb(start)] thrusting [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.herHim].",
						"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] [npc.verb(fuck)] [npc2.herHim]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager anal";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly pump your [npc.cock+] in and out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
						"With [npc.her] [npc.cock+] rubbing up against [npc3.namePos], [npc.name] eagerly [npc.verb(thrust)] deep inside of [npc2.namePos] [npc2.asshole+],"
								+ " letting out [npc.a_moan+] with every energetic buck of [npc.her] [npc.hips] as [npc.she] frantically [npc.verb(fuck)] [npc2.herHim].",
						"Joining [npc3.name] in double-penetrating [npc2.namePos] [npc2.asshole+], [npc.name] enthusiastically [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.herHim].",
						"With both [npc.her] and [npc3.namePos] [npc.cocks(true)] stuffed deep in [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] happily [npc.verb(pump)] [npc.her] [npc.hips] back and forth, breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(fuck)] [npc2.herHim].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly sinking [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] [npc.verb(start)] enthusiastically rocking [npc.her] [npc.hips] back and forth, letting out [npc.a_moan+] with every thrust as [npc.she] happily [npc.verb(fuck)] [npc2.name].",
						"Enthusiastically pushing [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+],"
								+ " [npc.name] frantically [npc.verb(thrust)] [npc.her] [npc.hips] forwards, letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(fuck)] [npc2.herHim].",
						"Thrusting [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(start)] to eagerly pump [npc.her] [npc.hips] back and forth,"
								+ " breathing in [npc2.namePos] [npc2.scent] as [npc.she] desperately [npc.verb(fuck)] [npc2.herHim]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist anal";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.cock] out of [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] down and [npc2.verb(take)] a gentle hold of it,"
										+ " before softly forcing it back into [npc2.her] [npc2.asshole+] alongside [npc3.namePos].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it,"
										+ " before gently forcing it back inside [npc2.her] [npc2.asshole+] alongside [npc3.namePos].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.asshole+],"
										+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] gently [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.cock+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a rough hold of it,"
										+ " before aggressively forcing it back into [npc2.her] [npc2.asshole+] alongside [npc3.namePos].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it,"
										+ " before roughly forcing it back inside [npc2.her] [npc2.asshole+] alongside [npc3.namePos].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.asshole+],"
										+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.cock+].")));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a firm hold of it,"
										+ " before eagerly forcing it back into [npc2.her] [npc2.asshole+] alongside [npc3.namePos].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it,"
										+ " before eagerly forcing it back inside [npc2.her] [npc2.asshole+] alongside [npc3.namePos].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.asshole+],"
										+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.cock+].")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] down and [npc2.verb(take)] a gentle hold of it, before softly forcing it back into [npc2.her] [npc2.asshole+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before gently forcing it back inside [npc2.her] [npc2.asshole+].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.asshole+],"
										+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] gently [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a rough hold of it, before aggressively forcing it back into [npc2.her] [npc2.asshole+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before roughly forcing it back inside [npc2.her] [npc2.asshole+].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.asshole+],"
										+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.cock+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole],"
										+ " but [npc.her] efforts prove to be in vain as [npc2.name] quickly [npc2.verb(reach)] back and [npc2.verb(take)] a firm hold of it, before eagerly forcing it back into [npc2.her] [npc2.asshole+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.name], but [npc2.she] quickly [npc2.verb(grab)] it, before eagerly forcing it back inside [npc2.her] [npc2.asshole+].",
								"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock] away from [npc2.namePos] [npc2.asshole+],"
										+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.asshole+] down onto [npc.her] [npc.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop anal";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [npc2.namePos] [npc2.asshole+] and stop fucking [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] dominantly [npc.verb(slide)] the [npc.cockTip+] up and down over [npc2.her] [npc2.assCloaca+] one last time before pulling back and leaving [npc3.name] to continue fucking [npc2.herHim].",
								"Thrusting deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(yank)] [npc.her] [npc.cock+] back out of [npc2.her] [npc2.asshole+], putting an end to the rough double-penetration.")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
								"Sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] [npc.verb(rub)] the [npc.cockTip] up and down over [npc2.her] [npc2.assCloaca+] one last time before pulling back and leaving [npc3.name] to continue fucking [npc2.herHim].",
								"Pushing deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(slide)] [npc.her] [npc.cock+] back out of [npc2.her] [npc2.asshole+], putting an end to the double-penetration.")));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
									" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.asshole],"
											+ " and [npc2.she] [npc2.verb(continue)] crying and protesting as [npc3.name] [npc3.verb(carry)] on thrusting in and out of [npc2.herHim].",
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc3.name] [npc3.verb(continue)] to pound [npc2.her] [npc2.asshole+].")));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this)), UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out of [npc2.her] [npc2.asshole+], eager for more of [npc.her] attention.",
									" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention.")));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly yanking [npc.her] [npc.cock+] out of [npc2.namePos] [npc2.asshole+],"
										+ " [npc.name] dominantly [npc.verb(slide)] the [npc.cockTip+] up and down over [npc2.her] [npc2.assCloaca+] one last time before pulling back.",
								"Thrusting deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(yank)] [npc.her] [npc.cock+] back out of [npc2.her] [npc2.asshole+], putting an end to the rough fucking."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.her] [npc.cock] out of [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(rub)] the [npc.cockTip] up and down over [npc2.her] [npc2.assCloaca+] one last time before pulling back.",
								"Pushing deep inside of [npc2.name] one last time, [npc.name] then [npc.verb(slide)] [npc.her] [npc.cock+] back out of [npc2.her] [npc2.asshole+], putting an end to the fucking."));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] can't [npc2.verb(help)] but [npc2.verb(let)] out [npc2.sob+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.asshole],"
											+ " and [npc2.she] [npc2.verb(continue)] crying and protesting as [npc2.she] [npc2.verb(carry)] on weakly struggling against [npc.herHim].",
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.asshole+] away from [npc.name]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] out of [npc2.her] [npc2.asshole+], eager for more of [npc.her] attention.",
									" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	//TODO DP
	
	public static final SexAction USING_PENIS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Receive anal";
		}

		@Override
		public String getActionDescription() {
			return "Slide [npc2.namePos] [npc2.cock+] into your [npc.asshole+] and get fucked.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			String assTargeting = "between [npc.her] ass cheeks";
			if(Main.sex.getCharacterPerformingAction().getGenitalArrangement()!=GenitalArrangement.NORMAL) {
				assTargeting = "to [npc.her] [npc.assCloaca+]";
			}
			
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] slowly [npc.verb(guide)] it up "+assTargeting+","
										+ " letting out a little [npc.moan] before gently bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.asshole+],"
										+ " before slowly pushing [npc.her] [npc.hips] back and letting out a soft [npc.moan] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] roughly yank it up "+assTargeting+","
										+ " letting out [npc.a_moan+] before violently slamming [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.asshole+],"
										+ " before eagerly slamming [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] it up "+assTargeting+","
										+ " letting out [npc.a_moan+] before bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.asshole+],"
										+ " before pushing [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] eagerly [npc.verb(guide)] it up "+assTargeting+","
										+ " letting out [npc.a_moan+] before desperately bucking [npc.her] [npc.hips] and forcing [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",
								"Grabbing [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up to [npc.her] [npc.asshole+],"
										+ " before eagerly thrusting [npc.her] [npc.hips] back and letting out [npc.a_moan+] as [npc.she] [npc.verb(penetrate)] [npc.herself] on [npc2.her] [npc2.cock+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] slowly [npc.verb(move)] [npc.her] [npc.hips] until [npc2.namePos] [npc2.cock+] is pressed up "+assTargeting+","
										+ " and then with a little [npc.moan], [npc.she] gently [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",
								"Pushing [npc.her] [npc.asshole+] back against [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up over [npc.her] [npc.assCloaca+],"
										+ " before slowly moving back and letting out a soft [npc.moan] as [npc.she] [npc.verb(force)] [npc2.herHim] to penetrate [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] forcefully [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up "+assTargeting+","
										+ " and then with [npc.a_moan+], [npc.she] violently [npc.verb(slam)] back and [npc.verb(force)] [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",
								"Roughly pushing [npc.her] [npc.asshole+] back against [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up over [npc.her] [npc.assCloaca+],"
										+ " before dominantly moving back and letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to penetrate [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up "+assTargeting+","
										+ " and then with [npc.a_moan+], [npc.she] [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",
								"Pushing [npc.her] [npc.asshole+] back against [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up over [npc.her] [npc.assCloaca+],"
										+ " before moving back and letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to penetrate [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] eagerly [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up "+assTargeting+","
										+ " and then with [npc.a_moan+], [npc.she] greedily [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to penetrate [npc.her] [npc.asshole+].",
								"Desperately pushing [npc.her] [npc.asshole+] back against [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] it up over [npc.her] [npc.assCloaca+],"
										+ " before eagerly moving back and letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to penetrate [npc.herHim]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " gently pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] to fuck [npc.namePos] [npc.asshole+].",
	
								" With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(start)] fucking [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " eagerly pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] enthusiastically fucking [npc.namePos] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(start)] energetically fucking [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " and, seeking to remind [npc.name] who's in charge, [npc2.she] roughly slams [npc2.her] [npc2.cock] forwards and [npc2.verb(start)] to ruthlessly fuck [npc.her] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock] forwards,"
										+ " seeking to remind [npc.name] who's in charge as [npc2.she] [npc2.verb(start)] ruthlessly fucking [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim], pushing [npc2.her] [npc2.cock] forwards as [npc2.she] [npc2.verb(start)] fucking [npc.namePos] [npc.asshole+].",
	
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards, sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(start)] fucking [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] inside of [npc.herHim],"
										+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.cock+] free from [npc.her] [npc.asshole+].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep into [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction USING_PENIS_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		private List<GameCharacter> getCharactersForParsing() {
			return PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();
			
			int size = PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			
			map.put("one other character fucking [npc.namePos] asshole", size==1);
			map.put("[npc2.namePos] penis to be exposed", Main.sex.getCharacterTargetedForSexAction(this).isPenetrationTypeExposed(SexAreaPenetration.PENIS));
			map.put("[npc2.namePos] penis to be free", SexAreaPenetration.PENIS.isFree(Main.sex.getCharacterTargetedForSexAction(this)));
			
			return map;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()==1
					&& !getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public String getActionTitle() {
			return "Get double-penetrated (anal)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getCharactersForParsing(),
				"Get [npc2.name] to join [npc3.name] in fucking your [npc.asshole+].");
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			String assTargeting = "between [npc.her] ass cheeks";
			if(Main.sex.getCharacterPerformingAction().getGenitalArrangement()!=GenitalArrangement.NORMAL) {
				assTargeting = "to [npc.her] [npc.assCloaca+]";
			}

			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"Not satisfied with having just [npc3.name] fucking [npc.her] [npc.asshole+], [npc.name] gently [npc.verb(take)] hold of [npc2.namePos] [npc2.cock], before slowly guiding it up to [npc.her] [npc.assCloaca+]."
									+ " Letting out a little [npc.moan], [npc.she] then gently [npc.verb(buck)] [npc.her] [npc.hips] and [npc.verb(force)] [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].",
							"Wanting more than just [npc3.namePos] [npc3.cock] in [npc.her] [npc.asshole], [npc.name] [npc.verb(take)] hold of [npc2.namePos] [npc2.cock], before gently lining it up "+assTargeting+"."
									+ " Slowly pushing [npc.her] [npc.hips] back, [npc.she] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] push in alongside [npc3.namePos] into [npc.her] [npc.asshole+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"Not satisfied with having just [npc3.name] fucking [npc.her] [npc.asshole+], [npc.name] roughly [npc.verb(grab)] [npc2.namePos] [npc2.cock], before forcefully moving it up to [npc.her] [npc.assCloaca+]."
									+ " Letting out [npc.a_moan+], [npc.she] then violently [npc.verb(buck)] [npc.her] [npc.hips] and [npc.verb(force)] [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].",
							"Wanting more than just [npc3.namePos] [npc3.cock] in [npc.her] [npc.asshole], [npc.name] forcefully [npc.verb(grab)] [npc2.namePos] [npc2.cock], before quickly lining it up "+assTargeting+"."
									+ " Roughly slamming [npc.her] [npc.hips] back, [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] push in alongside [npc3.namePos] into [npc.her] [npc.asshole+].")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"Not satisfied with having just [npc3.name] fucking [npc.her] [npc.asshole+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock], before moving it up to [npc.her] [npc.assCloaca+]."
									+ " Letting out [npc.a_moan+], [npc.she] then [npc.verb(buck)] [npc.her] [npc.hips] and [npc.verb(force)] [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].",
							"Wanting more than just [npc3.namePos] [npc3.cock] in [npc.her] [npc.asshole], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock], before lining it up "+assTargeting+"."
									+ " Bucking [npc.her] [npc.hips] back, [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] push in alongside [npc3.namePos] into [npc.her] [npc.asshole+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
							"Not satisfied with having just [npc3.name] fucking [npc.her] [npc.asshole+], [npc.name] impatiently [npc.verb(grab)] [npc2.namePos] [npc2.cock], before eagerly moving it up to [npc.her] [npc.assCloaca+]."
									+ " Letting out [npc.a_moan+], [npc.she] then desperately [npc.verb(buck)] [npc.her] [npc.hips] and [npc.verb(force)] [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].",
							"Wanting more than just [npc3.namePos] [npc3.cock] in [npc.her] [npc.asshole], [npc.name] greedily [npc.verb(grab)] [npc2.namePos] [npc2.cock], before quickly lining it up "+assTargeting+"."
									+ " Impatiently bucking [npc.her] [npc.hips] back, [npc.she] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] [npc2.namePos] [npc2.cock+] push in alongside [npc3.namePos] into [npc.her] [npc.asshole+].")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"Not satisfied with having just [npc3.name] fucking [npc.her] [npc.asshole+], [npc.name] slowly [npc.verb(move)] [npc.her] [npc.hips] until [npc2.namePos] [npc2.cock+] is pressed up to [npc.her] [npc.assCloaca+],"
										+ " and then with a little [npc.moan], [npc.she] gently [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].",
								"Wanting to be fucked by more than just [npc3.name], [npc.name] [npc.verb(push)] back against [npc2.namePos] [npc2.cock], making sure to line the [npc2.cockHead] up "+assTargeting+""
										+ " before slowly moving back and forcing [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"Not satisfied with having just [npc3.name] fucking [npc.her] [npc.asshole+], [npc.name] forcefully [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up to [npc.her] [npc.assCloaca+],"
										+ " and then with [npc.a_moan+], [npc.she] violently [npc.verb(slam)] back and [npc.verb(force)] [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].",
								"Wanting to be fucked by more than just [npc3.name], [npc.name] roughly [npc.verb(push)] back against [npc2.namePos] [npc2.cock], making sure to line the [npc2.cockHead] up "+assTargeting+""
										+ " before dominantly moving back and forcing [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"Not satisfied with having just [npc3.name] fucking [npc.her] [npc.asshole+], [npc.name] [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up to [npc.her] [npc.assCloaca+],"
										+ " and then with [npc.a_moan+], [npc.she] [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].",
								"Wanting to be fucked by more than just [npc3.name], [npc.name] [npc.verb(push)] back against [npc2.namePos] [npc2.cock], making sure to line the [npc2.cockHead] up "+assTargeting+""
										+ " before moving back and forcing [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								"Not satisfied with having just [npc3.name] fucking [npc.her] [npc.asshole+], [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.hips] back until [npc2.namePos] [npc2.cock+] is pressed up to [npc.her] [npc.assCloaca+],"
										+ " and then with [npc.a_moan+], [npc.she] greedily [npc.verb(buck)] back and [npc.verb(force)] [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].",
								"Wanting to be fucked by more than just [npc3.name], [npc.name] desperately [npc.verb(push)] back against [npc2.namePos] [npc2.cock], making sure to line the [npc2.cockHead] up "+assTargeting+""
										+ " before eagerly moving back and forcing [npc2.herHim] to join [npc3.name] in double-penetrating [npc.her] [npc.asshole+].")));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " gently pushing [npc2.her] [npc2.cock] forwards beside [npc3.namePos] as [npc2.she] [npc2.verb(join)] [npc3.herHim] in fucking [npc.namePos] [npc.asshole+].",
								" With a soft [npc2.moan], [npc2.name] gently [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(join)] [npc3.name] in fucking [npc.herHim].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " roughly pushing [npc2.her] [npc2.cock] forwards beside [npc3.namePos] as [npc2.she] [npc2.verb(join)] [npc3.herHim] in fucking [npc.namePos] [npc.asshole+].",
								" With [npc2.a_moan+], [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock] forwards,"
										+ " seeking to remind [npc.name] who's in charge as [npc2.she] [npc2.verb(join)] [npc3.name] in ruthlessly fucking [npc.herHim].")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " pushing [npc2.her] [npc2.cock] forwards beside [npc3.namePos] as [npc2.she] [npc2.verb(join)] [npc3.herHim] in fucking [npc.namePos] [npc.asshole+].",
								" With [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards, sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(join)] [npc3.name] in fucking [npc.herHim].")));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] inside of [npc.herHim],"
										+ " and, struggling against [npc.herHim], [npc2.she] desperately [npc2.verb(try)] to pull [npc2.her] [npc2.cock+] free from [npc.her] [npc.asshole+].",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] deep into [npc.her] [npc.asshole+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(getCharactersForParsing(), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enter)] [npc.herHim],"
										+ " eagerly pushing [npc2.her] [npc2.cock] forwards beside [npc3.namePos] as [npc2.she] [npc2.verb(join)] [npc3.herHim] in enthusiastically fucking [npc.namePos] [npc.asshole+].",
								" With [npc2.a_moan+], [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] [npc2.cock] forwards,"
										+ " sinking it deep into [npc.namePos] [npc.asshole+] as [npc2.she] [npc2.verb(join)] [npc3.name] in energetically fucking [npc.herHim].")));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.asshole],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force both [npc2.her] and [npc3.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+].",
								" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.asshole+]."));
					case DOM_GENTLE:
						return UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+],"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(join)] [npc3.name] in gently penetrating [npc.her] [npc.asshole+].",
								" A gentle [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.cock+] in alongside [npc3.namePos] deep into [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(join)] [npc3.name] in gently sliding [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+]."));
					case DOM_ROUGH:
						return UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(join)] [npc3.name] in roughly pounding [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.cock+] in alongside [npc3.namePos] deep into [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(join)] [npc3.name] in roughly slamming [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+]."));
					case SUB_NORMAL:
						return UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+],"
										+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(join)] [npc3.name] in roughly pounding [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] in alongside [npc3.namePos] deep into [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(join)] [npc3.name] in thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+]."));
					default:
						return UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(join)] [npc3.name] in enthusiastically pounding [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] eagerly thrusting [npc2.her] [npc2.cock+] in alongside [npc3.namePos] deep into [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(join)] [npc3.name] in eagerly driving [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+]."));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_RESISTING:
						return (UtilText.returnStringAtRandom(
								" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.asshole],"
										+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
								" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
										+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] deep into [npc.her] [npc.asshole+].",
								" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] [npc.asshole+]."));
					case DOM_GENTLE:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+],"
										+ " letting out a soft [npc2.moan] as [npc2.she] gently [npc2.verb(penetrate)] [npc.her] [npc.asshole+].",
								" A gentle [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+]."));
					case DOM_ROUGH:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] roughly [npc2.verb(penetrate)] [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] [npc.asshole+]."));
					case SUB_NORMAL:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+]."));
					default:
						return (UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+],"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(penetrate)] [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.asshole+].",
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] [npc.asshole+]."));
				}
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_PENIS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gently receive anal";
		}

		@Override
		public String getActionDescription() {
			return "Gently have your [npc.asshole+] fucked by [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"With a gentle thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink the two [npc2.cocks(true)] penetrating [npc.herHim] ever deeper into [npc.her] [npc.asshole+].",
						"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] bucking [npc.her] [npc.hips], forcing #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks+(true)] ever deeper into [npc.her] [npc.asshole+].",
						"Slowly gyrating [npc.her] [npc.hips], a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force the two [npc2.cocks(true)] deep into [npc.her] [npc.asshole+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With a gentle thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+].",
						"With a soft [npc.moan], [npc.name] gently [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.asshole+].",
						"Slowly gyrating [npc.her] [npc.hips], a soft [npc.moan] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive anal";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [npc.asshole+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"With an eager bucking of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink the two [npc2.cocks(true)] penetrating [npc.herHim] ever deeper into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] bucking [npc.her] [npc.hips], forcing #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks+(true)] ever deeper into [npc.her] [npc.asshole+].",
						"Energetically gyrating [npc.her] [npc.hips], [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force the two [npc2.cocks(true)] deep into [npc.her] [npc.asshole+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With an eager bucking of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.asshole+].",
						"Energetically gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Roughly receive anal";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck your [npc.asshole+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"With a violent slamming of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] the two [npc2.cocks(true)] penetrating [npc.herHim] ever deeper into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] roughly [npc.verb(start)] bucking [npc.her] [npc.hips], forcing #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks+(true)] ever deeper into [npc.her] [npc.asshole+].",
						"Forcefully gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements roughly force the two [npc2.cocks(true)] deep into [npc.her] [npc.asshole+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With a violent slam of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] roughly [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.asshole+].",
						"Forcefully gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements roughly force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_PENIS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive anal";
		}

		@Override
		public String getActionDescription() {
			return "Fuck your [npc.asshole+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"With a quick buck of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(force)] the two [npc2.cocks(true)] penetrating [npc.herHim] ever deeper into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips], forcing #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks+(true)] ever deeper into [npc.her] [npc.asshole+].",
						"Gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force the two [npc2.cocks(true)] deep into [npc.her] [npc.asshole+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With a quick buck of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.asshole+].",
						"Gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eagerly receive anal";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly fuck your [npc.asshole+] on [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
						"With an eager bucking of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink the two [npc2.cocks(true)] penetrating [npc.herHim] ever deeper into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] bucking [npc.her] [npc.hips], forcing #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks+(true)] ever deeper into [npc.her] [npc.asshole+].",
						"Energetically gyrating [npc.her] [npc.hips], [npc.a_moan+] drifts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force the two [npc2.cocks(true)] deep into [npc.her] [npc.asshole+].")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With an eager bucking of [npc.her] [npc.hips], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(help)] to sink [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+].",
						"With [npc.a_moan+], [npc.name] enthusiastically [npc.verb(start)] bucking [npc.her] [npc.hips], forcing [npc2.namePos] [npc2.cock+] ever deeper into [npc.her] [npc.asshole+].",
						"Energetically gyrating [npc.her] [npc.hips], [npc.a_moan+] bursts out from between [npc.namePos] [npc.lips+] as [npc.her] movements force [npc2.namePos] [npc2.cock+] deep into [npc.her] [npc.asshole+]."));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist receiving anal";
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.asshole+] away from [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
										+ " before weakly trying to pull both #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks(true)] out of [npc.her] [npc.asshole+].",
								"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.asshole] away from the unwanted double-penetration,"
										+ " struggling in desperation as the two [npc2.cocks(true)] continue slowly sliding in and out of [npc.her] [npc.asshole+].",
								"Trying desperately to pull [npc.her] [npc.hips] away,"
										+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] continues to join [npc3.namePos] in gently sliding deep into [npc.her] [npc.asshole+].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
										+ " before weakly trying to pull both #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks(true)] out of [npc.her] [npc.asshole+].",
								"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.asshole] away from the unwanted double-penetration,"
										+ " struggling in desperation as the two [npc2.cocks(true)] continue roughly slamming in and out of [npc.her] [npc.asshole+].",
								"Trying desperately to pull [npc.her] [npc.hips] away,"
										+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] continues to join [npc3.namePos] in violently thrusting deep into [npc.her] [npc.asshole+].")));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.parse(PenisAnus.getCharactersForParsing(Main.sex.getCharacterPerformingAction()), UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
										+ " before weakly trying to pull both #IF(npc3.isPlayer())[npc3.namePos] and [npc2.namePos]#ELSE[npc2.namePos] and [npc3.namePos]#ENDIF [npc2.cocks(true)] out of [npc.her] [npc.asshole+].",
								"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.asshole] away from the unwanted double-penetration,"
										+ " struggling in desperation as the two [npc2.cocks(true)] continue frantically pumping in and out of [npc.her] [npc.asshole+].",
								"Trying desperately to pull [npc.her] [npc.hips] away,"
										+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] continues to join [npc3.namePos] in greedily thrusting deep into [npc.her] [npc.asshole+].")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
										+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.asshole+].",
								"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.ass+] away from [npc2.namePos] unwanted penetration,"
										+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] slowly sliding in and out of [npc.her] [npc.asshole+].",
								"Trying desperately to pull [npc.her] [npc.hips] away,"
										+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] continues gently sliding deep into [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
										+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.asshole+].",
								"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.ass+] away from [npc2.namePos] unwanted penetration,"
										+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] roughly slamming in and out of [npc.her] [npc.asshole+].",
								"Trying desperately to pull [npc.her] [npc.hips] away,"
										+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] continues violently thrusting deep into [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] tears [npc.verb(start)] to well up in [npc.her] [npc.eyes], and, not being able to hold back any longer, [npc.she] suddenly [npc.verb(let)] out [npc.a_sob+],"
										+ " before weakly trying to pull [npc2.namePos] [npc2.cock] out of [npc.her] [npc.asshole+].",
								"[npc.A_sob+] bursts out from [npc.namePos] mouth as [npc.she] frantically [npc.verb(try)] to pull [npc.her] [npc.ass+] away from [npc2.namePos] unwanted penetration,"
										+ " struggling in desperation as [npc2.her] [npc2.cock+] [npc2.verb(continue)] frantically pumping in and out of [npc.her] [npc.asshole+].",
								"Trying desperately to pull [npc.her] [npc.hips] away,"
										+ " [npc.name] [npc.sobVerb] in distress as [npc2.namePos] [npc2.cock+] continues greedily thrusting deep into [npc.her] [npc.asshole+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Stop receiving anal";
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull [npc2.her] [npc2.cock] out of your [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(PenisAnus.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Yanking [npc2.namePos] [npc2.cock] out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.name] to stop fucking [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.cock] out of [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc2.namePos] [npc2.cock] out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.name] to stop fucking [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.cock] out of [npc.her] [npc.asshole+]."));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.nameIsFull]n't finished with [npc2.herHim] just yet.",
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from fucking [npc.her] [npc.asshole+].",
									" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue fucking [npc.namePos] [npc.asshole+]."));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Yanking [npc2.namePos] [npc2.cock] out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out a menacing growl as [npc.she] [npc.verb(command)] [npc2.name] to stop fucking [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before yanking [npc2.her] [npc2.cock] out of [npc.her] [npc.asshole+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc2.namePos] [npc2.cock] out of [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(tell)] [npc2.name] to stop fucking [npc.herHim].",
								"[npc.Name] [npc.verb(lean)] into [npc2.name], inhaling [npc2.her] [npc2.scent] before sliding [npc2.her] [npc2.cock] out of [npc.her] [npc.asshole+]."));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.nameIsFull]n't finished with [npc2.herHim] just yet.",
									" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] [npc2.herHim] from fucking [npc.her] [npc.asshole+].",
									" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue fucking [npc.namePos] [npc.asshole+]."));
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
