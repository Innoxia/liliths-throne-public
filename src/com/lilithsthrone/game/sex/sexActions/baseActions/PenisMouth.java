package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.3.4.5
 * @author Innoxia
 */
public class PenisMouth {
	
	// -- Methods for multiple ongoing characters:
	
	static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingBlowjob) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingBlowjob, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
	}

	private static List<GameCharacter> getCharactersForParsing(GameCharacter characterReceivingBlowjob) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingBlowjob)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	private static String getOngoingNames(GameCharacter characterReceivingBlowjob, GameCharacter... charactersToExclude) {
		List<String> names = new ArrayList<>();
		List<GameCharacter> exclusions = Arrays.asList(charactersToExclude);
		for(GameCharacter c : getOngoingCharacters(characterReceivingBlowjob)) {
			if(!exclusions.contains(c)) {
				names.add(UtilText.parse(c, "[npc.name]"));
			}
		}
		return Util.stringsToStringList(names, false);
	}
	
	public static GameCharacter getPrimaryBlowjobPerformer(GameCharacter characterReceivingBlowjob) {
		return Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).keySet().iterator().next();
	}
	
	private static GameCharacter getSecondaryBlowjobPerformer(GameCharacter characterReceivingBlowjob) {
		return new ArrayList<>(Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).keySet()).get(1);
	}
	
	// ---
	
	public static final SexAction COCK_SLAP = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Cock slap";
		}

		@Override
		public String getActionDescription() {
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(Main.sex.getCharacterPerformingAction());
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), primary, target),
					"Pull your [npc.cock] out of [npc2.namePos] mouth and slap "+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" face with it.");
		}

		@Override
		public String getDescription() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return UtilText.parse(Util.newArrayListOfValues(performer, primary, target),
						UtilText.returnStringAtRandom(
							"Grinning down at "+PenisMouth.getOngoingNames(performer)+", [npc.name] [npc.verb(pull)] back, sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
								+ " As [npc3.name] [npc3.verb(look)] up at [npc.herHim], [npc.she] quickly [npc.verb(slap)] [npc.her] hard shaft against [npc3.her] cheek, splattering "
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"cummy":"wet")
									+" saliva across [npc3.her] face, before thrusting [npc.her] [npc.cock+] down [npc3.her] throat.",
	
							"Stepping back, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] free from [npc2.namePos] mouth,"
									+ " before proceeding to slap the saliva-coated [npc1.cockHead] against "+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" cheeks."
									+ " As [npc3.name] [npc3.verb(open)] [npc3.her] mouth to gasp in shock, [npc.name] [npc.verb(use)] the opportunity to guide the [npc.cockHead] of [npc.her] [npc.cock] past [npc3.her] [npc3.lips+],"
									+ " before pushing it deep down [npc3.her] throat.",
	
							"[npc.Name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF back, allowing [npc.her] [npc.cock+] to slide out from [npc2.namePos] mouth."
								+ " Looking up at [npc.herHim], [npc3.nameIs] taken by surprise as [npc.name] suddenly [npc.verb(slap)] [npc.her] "
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"slimy":"saliva-coated")+" cock against [npc3.her] face"
									+ ", before forcing [npc.her] [npc.cock+] down [npc3.her] throat.",
	
							"Quickly pulling [npc.her] [npc.cock+] out from [npc2.namePos] mouth, [npc.name] [npc.verb(hold)] the base in one hand while holding "+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" head still with the other."
								+ " As [npc3.name] [npc2.verb(look)] up to see what's happening, [npc3.sheIs] met with a wet slap as [npc.name] [npc.verb(swing)] [npc.her] "
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"slimy":"saliva-coated")+" [npc.cock] against [npc3.her] cheek."
								+ " As [npc3.name] [npc3.verb(open)] [npc3.her] mouth in shock, [npc.name] [npc.verb(use)] the opportunity to thrust [npc.her] [npc.cock+] down [npc3.her] throat."));
				
			} else {
				return UtilText.parse(Util.newArrayListOfValues(performer, primary, target),
						UtilText.returnStringAtRandom(
							"Pulling [npc.her] [npc.hips] back, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
								+ " Before "+(primary.equals(target)?"[npc2.she]":"[npc3.name]")+" can react, [npc.she] quickly [npc.verb(slap)] [npc.her] hard shaft against [npc3.her] cheek, splattering saliva "
								+(Main.sex.hasLubricationTypeFromAnyone(performer, SexAreaPenetration.PENIS, LubricationType.PRECUM)?"and precum":"")
								+" across [npc3.her] [npc3.face], before thrusting [npc.her] [npc.cock+] down [npc3.her] throat.",
	
							"Pulling back, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] free from [npc2.namePos] mouth,"
									+ " and with [npc.a_moan+], [npc.she] [npc.verb(proceed)] to slap the saliva-coated [npc.cockHead] against "+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" [npc3.face],"
									+ " before sliding [npc.her] throbbing length down [npc3.her] throat.",
	
							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out from [npc2.namePos] mouth, and, grinning to [npc.herself], [npc.she] then [npc.verb(slap)] [npc.her] hard shaft against "
									+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" [npc2.face]."
								+ " With a streak of "+(Main.sex.hasLubricationTypeFromAnyone(performer, SexAreaPenetration.PENIS, LubricationType.PRECUM)?"cummy":"wet")
									+" saliva now drooling down [npc3.her] cheek, [npc3.name] [npc3.verb(open)] [npc3.her] [npc3.eyes] wide in surprise as [npc.name] [npc.verb(force)] [npc.her] [npc.cock] down [npc3.her] throat.",
	
							"Quickly pulling [npc.her] [npc.hips+] back, [npc.name] [npc.verb(draw)] [npc.her] [npc.cock+] out from [npc2.namePos] mouth, before starting to slap [npc.her] slimy length against "
									+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" cheeks."
								+ " Before [npc3.name] can react, [npc.name] suddenly [npc.verb(push)] [npc.her] [npc.hips] forwards, ramming [npc.her] [npc.cock+] down [npc3.her] throat."));
			}
		}
	};
	
	public static final SexAction FORCE_BALLS_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				GameCharacter performer = Main.sex.getCharacterPerformingAction();
				GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(performer);
				if(primary.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
					Main.sex.setPrimaryOngoingCharacter(secondary, performer, SexAreaPenetration.PENIS);
				}
			}
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isInternalTesticles()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Balls focus";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.name] to pay some attention to your [npc.balls+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			int rnd = Util.random.nextInt(4);
			
			String[] start;
			String[] mid = new String[] {};
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(performer);
				if(!primary.equals(target)) {
					start = new String[] {
							"With [npc2.namePos] [npc2.lips+] still wrapped around [npc.her] [npc.cock+],",
							"Thrusting [npc.her] [npc.cock+] down [npc2.namePos] throat,",
							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] into [npc2.namePos] mouth,",
							"Sliding [npc.her] [npc.cock+] down [npc2.namePos] throat,"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary),
							start[rnd]));
					
				} else {
					start = new String[] {
							"Getting [npc2.name] to back off, and then having [npc3.name] wrap [npc3.her] [npc3.lips+] around [npc.her] [npc.cock+],",
							"After pulling back from [npc2.name] and then thrusting [npc.her] [npc.cock+] down [npc3.namePos] throat,",
							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth and then down [npc3.namePos] throat,",
							"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] throat, and into [npc3.namePos] mouth,"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary, secondary),
							start[rnd]));
				}

				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						mid = new String[] {
								" [npc.name] [npc.verb(shuffle)] about until [npc.her] [npc.balls+] are gently pressing against [npc3.namePos] [npc3.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are gently pressing against [npc3.namePos] [npc3.lips+].",
								" before repositioning [npc.herself] so that [npc3.namePos] [npc3.lips+] are gently pressed against [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] gently pressing [npc.her] [npc.balls+] against [npc3.namePos] [npc3.lips+]."};
						break;
					case DOM_ROUGH:
						mid = new String[] {
								" [npc.name] [npc.verb(shuffle)] about until [npc.her] [npc.balls+] are roughly grinding against [npc3.namePos] [npc3.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are roughly grinding against [npc3.namePos] [npc3.lips+].",
								" before repositioning [npc.herself] so that [npc3.namePos] [npc3.lips+] are roughly grinding against [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] roughly forcing [npc.her] [npc.balls+] against [npc3.namePos] [npc3.lips+]."};
						break;
					case SUB_NORMAL:
						mid = new String[] {
								" [npc.name] [npc.verb(shuffle)] about until [npc.her] [npc.balls+] are pressing down against [npc3.namePos] [npc3.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc3.namePos] [npc3.lips+].",
								" before repositioning [npc.herself] so that [npc3.namePos] [npc3.lips+] are pressed against [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] forcing [npc.her] [npc.balls+] against [npc3.namePos] [npc3.lips+]."};
						break;
					default: // Dom normal and sub eager:
						mid = new String[] {
								" [npc.name] [npc.verb(shuffle)] about until [npc.her] [npc.balls+] are pressing against [npc3.namePos] [npc3.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc3.namePos] [npc3.lips+].",
								" before repositioning [npc.herself] so that [npc3.namePos] [npc3.lips+] are pressed against [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] forcing [npc.her] [npc.balls+] against [npc3.namePos] [npc3.lips+]."};
						break;
				}
				UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary, target),
						mid[rnd]));
				
			} else {
				start = new String[] {
						"Drawing [npc.her] [npc.hips] back, [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+] to slide out of [npc2.namePos] mouth,",
						"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth,",
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] out of [npc2.namePos] mouth,",
						"Quickly sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth,"};
				UtilText.nodeContentSB.append(start[rnd]);

				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						mid = new String[] {
								" before shuffling about until [npc.her] [npc.balls+] are gently pressing against [npc2.her] [npc2.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are gently pressing against [npc2.her] [npc2.lips+].",
								" before repositioning [npc.herself] so that [npc2.her] [npc2.lips+] are gently pressed against [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] gently pressing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."};
						break;
					case DOM_ROUGH:
						mid = new String[] {
								" before shuffling about until [npc.her] [npc.balls+] are roughly grinding against [npc2.her] [npc2.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are roughly grinding against [npc2.her] [npc2.lips+].",
								" before repositioning [npc.herself] so that [npc2.her] [npc2.lips+] are roughly grinding against [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] roughly forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."};
						break;
					case SUB_NORMAL:
						mid = new String[] {
								" before shuffling about until [npc.her] [npc.balls+] are pressing down against [npc2.her] [npc2.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc2.her] [npc2.lips+].",
								" before repositioning [npc.herself] so that [npc2.her] [npc2.lips+] are pressed against [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."};
						break;
					default: // Dom normal and sub eager:
						mid = new String[] {
								" before shuffling about until [npc.her] [npc.balls+] are pressing against [npc2.her] [npc2.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] so that [npc.her] [npc.balls+] are pressing against [npc2.her] [npc2.lips+].",
								" before repositioning [npc.herself] so that [npc2.her] [npc2.lips+] are pressed against [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] until [npc.sheIs] forcing [npc.her] [npc.balls+] against [npc2.namePos] [npc2.lips+]."};
						break;
				}
				UtilText.nodeContentSB.append(mid[rnd]);
			}

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Slowly sliding [npc2.her] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to gently lick and kiss [npc.namePos] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" [npc2.Name] gently [npc2.verb(start)] to kiss and lick [npc.namePos] [npc.balls+], drawing [npc.a_moan+] from out of [npc.her] mouth."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Eagerly darting [npc2.her] [npc2.tongue+] out, [npc2.name] greedily [npc2.verb(start)] to lick and kiss [npc.namePos] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" [npc2.Name] eagerly [npc2.verb(start)] to kiss and lick [npc.namePos] [npc.balls+], drawing [npc.a_moan+] from out of [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Sliding [npc2.her] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to roughly lick and kiss [npc.namePos] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" [npc2.Name] roughly [npc2.verb(start)] kissing and licking [npc.namePos] [npc.balls+], drawing [npc.a_moan+] from out of [npc.her] mouth."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Darting [npc2.her] [npc2.tongue+] out, [npc2.name] [npc2.verb(start)] to lick and kiss [npc.namePos] [npc.balls+], causing [npc.a_moan+] to drift out from between [npc.her] [npc.lips+].",
							" [npc2.Name] [npc2.verb(start)] to kiss and lick [npc.namePos] [npc.balls+], drawing [npc.a_moan+] from out of [npc.her] mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] desperately [npc2.verb(try)] to pull away, letting out [npc2.a_sob+] as [npc.namePos] [npc.balls+] [npc.verb(continue)] grinding against [npc2.her] [npc2.lips+].",
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.sob], trying desperately to pull away from [npc.namePos] [npc.balls+] as [npc2.she] [npc2.verb(struggle)] against [npc.herHim]."));
					break;
				default:
					break;
			
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction SUCK_BALLS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects(){
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
				GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(target);
				if(primary.equals(Main.sex.getCharacterPerformingAction())) {
					Main.sex.setPrimaryOngoingCharacter(secondary, target, SexAreaPenetration.PENIS);
				}
			}
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isInternalTesticles()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick balls";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss [npc2.namePos] [npc2.balls] for a while.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			int rnd = Util.random.nextInt(4);
			
			String[] start;
			String[] mid = new String[] {};
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(target);
				if(!primary.equals(performer)) {
					start = new String[] {
							"With [npc2.namePos] [npc2.cock+] still hilted down [npc3.namePos] throat,",
							"As [npc3.name] [npc3.verb(bob)] [npc3.her] head up in [npc2.namePos] lap, [npc.name] [npc.verb(lean)] in to plant a kiss on the side of [npc2.her] [npc2.cock+],",
							"As [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] into [npc3.namePos] mouth,",
							"Making sure not to get in the way of [npc3.name] as [npc3.her] head bobs up and down on [npc2.namePos] [npc2.cock+],"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
							start[rnd]));
					
				} else {
					start = new String[] {
							"Pulling back and letting [npc3.name] take [npc2.namePos] [npc2.cock+] into [npc3.her] mouth,",
							"[npc.Name] [npc.verb(swap)] with [npc3.name], allowing [npc3.herHim] to take [npc2.namePos] [npc2.cock+] into [npc3.her] mouth,",
							"Sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, and then getting [npc3.name] to swallow the slimy shaft,",
							"First sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth, then getting [npc3.name] to start sucking on it,"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, secondary),
							start[rnd]));
				}
				
			} else {
				start = new String[] {
						"Letting [npc2.namePos] [npc2.cock+] slip completely out of [npc.her] mouth,",
						"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.cock+] slide out of [npc.her] mouth,",
						"Sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth,",
						"First sliding [npc2.namePos] [npc2.cock+] out from [npc.her] mouth,"};
				UtilText.nodeContentSB.append(start[rnd]);
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					mid = new String[] {
							" [npc.name] [npc.verb(move)] [npc.her] head down and [npc.verb(start)] to gently lick and suck on [npc2.her] [npc2.balls+].",
							" before moving [npc.her] [npc.lips+] down to start gently licking and kissing [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] head down to start gently kissing and licking [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] head down, before starting to gently kiss and nuzzle into [npc2.her] [npc2.balls+]."};
					break;
				case DOM_ROUGH:
					mid = new String[] {
							" [npc.name] [npc.verb(move)] [npc.her] head down and [npc.verb(start)] to roughly lick and suck on [npc2.her] [npc2.balls+].",
							" before moving [npc.her] [npc.lips+] down to start roughly licking and kissing [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] head down to start roughly kissing and licking [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] head down, before starting to roughly kiss and nuzzle into [npc2.her] [npc2.balls+]."};
					break;
				case SUB_NORMAL:
					mid = new String[] {
							" [npc.name] [npc.verb(move)] [npc.her] head down and [npc.verb(start)] to lick and suck on [npc2.her] [npc2.balls+].",
							" before moving [npc.her] [npc.lips+] down to start licking and kissing [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] head down to start kissing and licking [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] head down, before starting to kiss and nuzzle into [npc2.her] [npc2.balls+]."};
					break;
				default: // Dom normal and sub eager:
					mid = new String[] {
							" [npc.name] [npc.verb(move)] [npc.her] head down and [npc.verb(start)] to eagerly lick and suck on [npc2.her] [npc2.balls+].",
							" before moving [npc.her] [npc.lips+] down to start desperately licking and kissing [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] head down to start eagerly kissing and licking [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] head down, before starting to eagerly kiss and nuzzle into [npc2.her] [npc2.balls+]."};
					break;
			}
			UtilText.nodeContentSB.append(UtilText.parse(performer, target, mid[rnd]));
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction LICK_HEAD = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Lick head";
		}

		@Override
		public String getActionDescription() {
			return "Lick and kiss the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {

			List<String> descriptions = new ArrayList<>();

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				List<String> descriptionsEnd = new ArrayList<>();
				
				for(PenetrationModifier pm : Main.sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
					switch(pm) {
						case FLARED:
							descriptions.add("Lifting [npc3.namePos] head up so that [npc2.namePos] [npc2.cock+] slides fully out of [npc3.her] mouth, [npc.name] quickly [npc.verb(move)] to take [npc3.her] place."
									+ " Immediately pressing [npc.her] [npc.lips+] down against the wide, flared head, [npc.she] then [npc.verb(start)] to passionately kiss and lick the top of [npc2.namePos] [npc2.cock+].");
							break;
						default:
							break;
					}
				}
				descriptions.add("With a hungry [npc.moan], [npc.name] [npc.verb(pull)] [npc3.namePos] head back, before taking [npc3.her] place and wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+]."
						+ " Letting out a satisfied [npc.moan], [npc.she] then starts to lick and kiss [npc2.her] [npc2.cockHead+].");
				descriptions.add("Wanting to get in on the action, [npc.name] [npc.verb(pull)] [npc3.namePos] head back,"
						+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(take)] [npc3.her] place and [npc.verb(start)] concentrating on sucking and kissing the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+].");
				descriptions.add("Pulling [npc3.namePos] head back a little, [npc.name] [npc.verb(take)] [npc3.her] place, and,"
						+ " focusing on using [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] licking and kissing the [npc2.cockHead+] that's now pressed up against [npc.her] [npc.lips+].");

				// If not resisting
				switch(Main.sex.getSexPace(primary)) {
					case SUB_RESISTING:
						descriptionsEnd.add(" Happy to have been pushed aside, [npc3.name] [npc3.verb(try)] to stay quiet as [npc3.she] [npc3.verb(attempt)] to shuffle further away, but, realising that [npc3.she] [npc3.verb(need)] some 'encouragement',"
								+ " [npc.name] soon [npc.verb(pull)] back from [npc2.namePos] crotch, before grabbing [npc3.name] and pushing [npc3.her] mouth down onto [npc2.namePos] [npc2.cock+] once more.");
						descriptionsEnd.add(" Relieved to have been pushed out of [npc3.her] position, [npc3.name] [npc3.verb(try)] to get away from [npc2.name], but, seeing what's happening,"
								+ " [npc.name] quickly [npc.verb(put)] an end to [npc.her] oral servicing, before grabbing [npc3.name] and forcing [npc3.herHim] to swallow [npc2.namePos] [npc2.cock+] once again.");
						break;
					default:
						descriptionsEnd.add(" Not happy with having been left to service the sides of [npc2.namePos] shaft, [npc3.name] [npc3.verb(allow)] [npc.name] to have [npc.her] fun for a few moments,"
								+ " before pushing [npc.her] aside and once again taking [npc2.namePos] [npc2.cock] down [npc3.her] throat.");
						descriptionsEnd.add(" Impatient to regain [npc3.her] position, [npc3.name] [npc3.verb(let)] [npc.name] orally service [npc2.name] in this manner for a short while,"
								+ " before pushing [npc.herHim] back out of the way and taking [npc2.namePos] [npc2.cock+] into [npc3.her] mouth once more.");
						break;
				}
				
				return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
						Util.randomItemFrom(descriptions))
						+ UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								Util.randomItemFrom(descriptionsEnd));
				
			} else {
				for(PenetrationModifier pm : Main.sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("[npc.Name] [npc.verb(slide)] [npc.her] head back, letting out a muffled [npc.moan] as [npc.she] [npc.verb(feel)] the barbs lining [npc2.namePos] [npc2.cock] rake their way up [npc.her] throat."
									+ " Leaving just the [npc2.cockHead+] pushed past [npc.her] [npc.lips+], [npc.she] then [npc.verb(start)] to passionately kiss and suck the tip of [npc2.her] [npc2.cock+].");
							break;
						case FLARED:
							descriptions.add("Sliding [npc.her] head back, [npc.name] [npc.verb(allow)] [npc2.namePos] [npc2.cock+] to slip completely out of [npc.her] mouth."
									+ " Immediately pressing [npc.her] [npc.lips+] down against the wide, flared head, [npc.she] then [npc.verb(start)] to passionately kiss and lick it.");
							break;
						default:
							break;
					}
				}
				descriptions.add("With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] head back, wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+], before starting to lick and kiss it.");
				descriptions.add("[npc.Name] [npc.verb(pull)] [npc.her] head back, letting out [npc.a_moan+] as [npc.she] [npc.verb(start)] concentrating on sucking and kissing the [npc2.cockHead+] of [npc2.namePos] [npc2.cock+].");
				descriptions.add("Pulling [npc.her] head back a little, [npc.name] [npc.verb(let)] most of [npc2.namePos] [npc2.cock+] slide out of [npc.her] mouth, and,"
						+ " focusing on using [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] licking and kissing the [npc2.cockHead+] that's left poking past [npc.her] [npc.lips+].");
				
				return Util.randomItemFrom(descriptions);
			}
		}
		
	};
	
	public static final SexAction HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
				return "Futa fun";
			} else {
				return "Herm fun";
			}
		}

		@Override
		public String getActionDescription() {
			return "Pleasure both [npc2.namePos] [npc2.cock+] and [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] the [npc2.cockHead] of [npc2.her] [npc2.cock+] a gentle suck,"
									+ " before pulling back and starting to slowly lick and kiss [npc2.her] [npc2.pussy+].",

							"Deciding to give [npc2.namePos] [npc2.pussy] some attention, [npc.name] [npc.verb(deliver)] a long, slow lick up the length of [npc2.her] [npc2.cock+],"
									+ " before pressing forwards and gently sliding [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Delivering a gentle kiss to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] down,"
									+ " bringing [npc.her] [npc.lips+] to [npc2.her] [npc2.pussy+] before slowly pushing [npc.her] [npc.tongue+] between [npc2.her] [npc2.labia+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] the [npc2.cockHead] of [npc2.her] [npc2.cock+] a wet suck,"
									+ " before pulling back and starting to eagerly lick and kiss [npc2.her] [npc2.pussy+].",

							"Deciding to give [npc2.namePos] [npc2.pussy] some attention, [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.cock+],"
									+ " before pressing forwards and eagerly darting [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Delivering a wet kiss to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] down,"
									+ " bringing [npc.her] [npc.lips+] to [npc2.her] [npc2.pussy+] before eagerly darting [npc.her] [npc.tongue+] between [npc2.her] [npc2.labia+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] the [npc2.cockHead] of [npc2.her] [npc2.cock+] a wet suck,"
									+ " before pulling back and starting to roughly lick and kiss [npc2.her] [npc2.pussy+].",

							"Deciding to give [npc2.namePos] [npc2.pussy] some attention, [npc.name] [npc.verb(deliver)] a long, roughly lick up the length of [npc2.her] [npc2.cock+],"
									+ " before pressing forwards and dominantly thrusting [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Delivering a rough kiss to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] down,"
									+ " bringing [npc.her] [npc.lips+] to [npc2.her] [npc2.pussy+] before dominantly thrusting [npc.her] [npc.tongue+] between [npc2.her] [npc2.labia+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Not wanting to ignore [npc2.namePos] other sexual organ, [npc.name] [npc.verb(give)] the [npc2.cockHead] of [npc2.her] [npc2.cock+] a wet suck,"
									+ " before pulling back and starting to lick and kiss [npc2.her] [npc2.pussy+].",

							"Deciding to give [npc2.namePos] [npc2.pussy] some attention, [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.cock+],"
									+ " before pressing forwards and darting [npc.her] [npc.tongue+] into [npc2.her] [npc2.pussy+].",

							"Delivering a wet kiss to the [npc2.cockHead] of [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] down,"
									+ " bringing [npc.her] [npc.lips+] to [npc2.her] [npc2.pussy+] before darting [npc.her] [npc.tongue+] between [npc2.her] [npc2.labia+]."));
					break;
				default:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan],"
									+ " gently pushing [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+] before [npc.she] [npc.verb(decide)] to shift [npc2.her] focus back to [npc2.her] [npc2.cock+] once again.",

							" Gently thrusting [npc2.her] [npc2.pussy+] into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out a soft [npc2.moan], before [npc.name] decide to move back to focusing on [npc2.her] [npc2.cock+].",

							" Softly [npc2.moaning], [npc2.name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back to [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " eagerly pushing [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+] before [npc.she] [npc.verb(decide)] to shift [npc2.her] focus back to [npc2.her] [npc2.cock+] once again.",

							" Eagerly thrusting [npc2.her] [npc2.pussy+] into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.name] decide to move back to focusing on [npc2.her] [npc2.cock+].",

							" [npc2.Moaning+], [npc2.name] eagerly [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back to [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " roughly grinding [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+] before [npc.she] [npc.verb(decide)] to shift [npc2.her] focus back to [npc2.her] [npc2.cock+] once again.",

							" Roughly grinding [npc2.her] [npc2.pussy+] into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.name] decide to move back to focusing on [npc2.her] [npc2.cock+].",

							" [npc2.Moaning+], [npc2.name] roughly [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back to [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+],"
									+ " pushing [npc2.her] [npc2.pussy+] against [npc.namePos] [npc.lips+] before [npc.she] [npc.verb(decide)] to shift [npc2.her] focus back to [npc2.her] [npc2.cock+] once again.",

							" Thrusting [npc2.her] [npc2.pussy+] into [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] out [npc2.a_moan+], before [npc.name] decide to move back to focusing on [npc2.her] [npc2.cock+].",

							" [npc2.Moaning+], [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face] for a few moments,"
									+ " before [npc.she] [npc.verb(decide)] to shift [npc.her] oral attention back to [npc2.her] [npc2.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Sobbing] and squirming in discomfort, [npc2.she] desperately [npc2.verb(try)] to pull away from [npc.herHim],"
									+ " begging to be left alone as [npc.she] [npc.verb(shift)] [npc.her] attention back down to [npc2.her] [npc2.cock+] once again.",

							" With [npc2.a_sob+], [npc2.name] tries to push [npc.name] away, squirming in discomfort as [npc.she] [npc.verb(move)] back to focusing [npc.her] attention on [npc2.her] [npc2.cock+].",

							" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.verb(move)] back to focusing on [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Twintail pull";
			} else {
				return "Twin braids pull";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Grab [npc2.namePos] twintails and pull [npc2.her] forwards onto your [npc.cock+].";
			} else {
				return "Grab [npc2.namePos] twin braids and pull [npc2.her] forwards onto your [npc.cock+].";
			}
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HAIR)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS || Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_BRAIDS)
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex();
		}

		@Override
		public String getDescription() {
			
			String style = Main.sex.getCharacterTargetedForSexAction(this).getHairStyle().getName(Main.sex.getCharacterTargetedForSexAction(this));
			
			UtilText.nodeContentSB.setLength(0);
			
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First sliding [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] forwards to grab hold of [npc2.namePos] "+style+","
										+ " before gently [npc.verb(pull)] [npc2.her] forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After slowly pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to take a gentle, but firm, grip on each of [npc2.namePos] "+style+", before steadily pulling [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Gently pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to take hold of [npc2.namePos] "+style+","
										+ " before slowly pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First pushing [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] roughly [npc.verb(grasp)] [npc2.namePos] "+style+","
										+ " before violently jerking [npc2.her] head forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After roughly pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] "+style+", before mercilessly yanking [npc2.her] head into [npc.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Roughly pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to grab [npc2.namePos] "+style+","
										+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First sliding [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] forwards to take hold of [npc2.namePos] "+style+","
										+ " before firmly pulling [npc2.her] head forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to take a firm grip on each of [npc2.namePos] "+style+", before steadily pulling [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to take hold of [npc2.namePos] "+style+","
										+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] "+style+", [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] "+style+", [npc.name] slowly [npc.verb(pull)] [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] "+style+","
										+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grasping [npc2.namePos] "+style+", [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to grab [npc2.namePos] "+style+", [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] "+style+","
										+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] "+style+", [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to take a firm grip on each of [npc2.namePos] "+style+", [npc.name] steadily [npc.verb(pull)] [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] "+style+","
										+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
				}
			}
			
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Gently humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to softly caress the underside of [npc.namePos] [npc.cock],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.herHim] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc2.her] [npc2.hair(true)] and sliding [npc.her] [npc.cock+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against #IF(npc.isPlayer())your#ELSE[npc.her] tormentor's#ENDIF thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to give #IF(npc.isPlayer())you#ELSE[npc.her] tormentor#ENDIF some extra pleasure,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair(true)] is released and [npc.namePos] [npc.cock+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Happily humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to lovingly caress the underside of [npc.namePos] [npc.cock],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction EAR_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Ear pull";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.ears+] and pull [npc2.her] forwards onto your [npc.cock+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HEAD)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex());
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First sliding [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] forwards to grab hold of [npc2.namePos] [npc2.ears+],"
										+ " before gently [npc.verb(pull)] [npc2.her] forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After slowly pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.ears+], before steadily pulling [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Gently pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.ears+],"
										+ " before slowly pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First pushing [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] roughly [npc.verb(grasp)] [npc2.namePos] [npc2.ears+],"
										+ " before violently jerking [npc2.her] head forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After roughly pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.ears+], before mercilessly yanking [npc2.her] head into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Roughly pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to grab [npc2.namePos] [npc2.ears+],"
										+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First sliding [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] forwards to take hold of [npc2.namePos] [npc2.ears+],"
										+ " before firmly pulling [npc2.her] head forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to take a firm grip on each of [npc2.namePos] [npc2.ears+], before steadily pulling [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.ears+],"
										+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.ears+], [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.ears+], [npc.name] slowly [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.ears+],"
										+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grasping [npc2.namePos] [npc2.ears+], [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to grab [npc2.namePos] [npc2.ears+], [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.ears+],"
										+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.ears+], [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to take a firm grip on each of [npc2.namePos] [npc2.ears+], [npc.name] steadily [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.ears+],"
										+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Gently humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to softly caress the underside of [npc.namePos] [npc.cock],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.herHim] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc2.her] [npc2.hair(true)] and sliding [npc.her] [npc.cock+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against #IF(npc.isPlayer())your#ELSE[npc.her] tormentor's#ENDIF thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to give #IF(npc.isPlayer())you#ELSE[npc.her] tormentor#ENDIF some extra pleasure,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair(true)] is released and [npc.namePos] [npc.cock+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Happily humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to lovingly caress the underside of [npc.namePos] [npc.cock],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Grab [npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.horns+] and pull [npc2.her] forwards onto your [npc.cock+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First sliding [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] forwards to grab hold of [npc2.namePos] [npc2.horns+],"
										+ " before gently [npc.verb(pull)] [npc2.her] forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After slowly pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.horns+], before steadily pulling [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Gently pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.horns+],"
										+ " before slowly pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First pushing [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] roughly [npc.verb(grasp)] [npc2.namePos] [npc2.horns+],"
										+ " before violently jerking [npc2.her] head forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After roughly pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.horns+], before mercilessly yanking [npc2.her] head into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Roughly pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to grab [npc2.namePos] [npc2.horns+],"
										+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First sliding [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] forwards to take hold of [npc2.namePos] [npc2.horns+],"
										+ " before firmly pulling [npc2.her] head forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to take a firm grip on each of [npc2.namePos] [npc2.horns+], before steadily pulling [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.horns+],"
										+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.horns+], [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.horns+], [npc.name] slowly [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.horns+],"
										+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grasping [npc2.namePos] [npc2.horns+], [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to grab [npc2.namePos] [npc2.horns+], [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.horns+],"
										+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.horns+], [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to take a firm grip on each of [npc2.namePos] [npc2.horns+], [npc.name] steadily [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.horns+],"
										+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Gently humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to softly caress the underside of [npc.namePos] [npc.cock],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.herHim] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc2.her] [npc2.hair(true)] and sliding [npc.her] [npc.cock+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against #IF(npc.isPlayer())your#ELSE[npc.her] tormentor's#ENDIF thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to give #IF(npc.isPlayer())you#ELSE[npc.her] tormentor#ENDIF some extra pleasure,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair(true)] is released and [npc.namePos] [npc.cock+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Happily humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to lovingly caress the underside of [npc.namePos] [npc.cock],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Grab [npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "Grab [npc2.namePos] [npc2.antennae+] and pull [npc2.her] forwards onto your [npc.cock+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First sliding [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] forwards to grab hold of [npc2.namePos] [npc2.antennae+],"
										+ " before gently [npc.verb(pull)] [npc2.her] forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After slowly pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.antennae+], before steadily pulling [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Gently pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.antennae+],"
										+ " before slowly pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First pushing [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] roughly [npc.verb(grasp)] [npc2.namePos] [npc2.antennae+],"
										+ " before violently jerking [npc2.her] head forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After roughly pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.antennae+], before mercilessly yanking [npc2.her] head into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Roughly pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to grab [npc2.namePos] [npc2.antennae+],"
										+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"First sliding [npc3.name] back off of [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] forwards to take hold of [npc2.namePos] [npc2.antennae+],"
										+ " before firmly pulling [npc2.her] head forwards and forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"After pushing [npc3.name] back, [npc.name] [npc.verb(reach)] down to take a firm grip on each of [npc2.namePos] [npc2.antennae+], before steadily pulling [npc2.herHim] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"Pushing [npc3.name] back, [npc.name] then [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.antennae+],"
										+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+".")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.antennae+], [npc.name] gently [npc.verb(pull)] [npc2.her] forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to take a gentle, but firm, grip on each of [npc2.namePos] [npc2.antennae+], [npc.name] slowly [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.antennae+],"
										+ " before gently pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly grasping [npc2.namePos] [npc2.antennae+], [npc.name] violently [npc.verb(jerk)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to grab [npc2.namePos] [npc2.antennae+], [npc.name] mercilessly [npc.verb(yank)] [npc2.her] head into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to grab [npc2.namePos] [npc2.antennae+],"
										+ " before violently yanking [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Taking hold of [npc2.namePos] [npc2.antennae+], [npc.name] firmly [npc.verb(pull)] [npc2.her] head forwards, forcing [npc2.herHim] to swallow [npc.her] [npc.cock+] all the way down to the base.",
								"Reaching down to take a firm grip on each of [npc2.namePos] [npc2.antennae+], [npc.name] steadily [npc.verb(pull)] [npc2.her] into [npc2.her] groin,"
										+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to deepthroat [npc.her] [npc.cock+].",
								"With [npc.a_moan+], [npc.name] [npc.verb(reach)] down to take hold of [npc2.namePos] [npc2.antennae+],"
										+ " before steadily pulling [npc2.herHim] forwards, hilting [npc.her] [npc.cock+] down [npc2.her] throat so that [npc2.her] [npc2.lips+] are pressed up against "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"[npc.her] knot":"the base")+"."));
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment gently caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Gently humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to softly caress the underside of [npc.namePos] [npc.cock],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Narrowing [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] up with [npc.namePos] daring move for just a moment,"
									+ " before jerking [npc2.her] head back and pointedly reminding [npc.herHim] who's in charge.",
							" The rumbling vibrations of [npc2.namePos] threatening growls, while serving to provide some extra pleasure,"
									+ " nonetheless intimidate [npc.name] into quickly letting go of [npc2.her] [npc2.hair(true)] and sliding [npc.her] [npc.cock+] free from [npc2.her] throat."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Scrunching [npc2.her] [npc2.eyes] shut, [npc2.name] [npc2.verb(beat)] against #IF(npc.isPlayer())your#ELSE[npc.her] tormentor's#ENDIF thighs in a futile gesture of resistance,"
									+ " before finally being able to cry and gasp for air as [npc.name] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" The vibrations of [npc2.namePos] muffled cries and sobs only serve to give #IF(npc.isPlayer())you#ELSE[npc.her] tormentor#ENDIF some extra pleasure,"
									+ " but, after spending several seconds punching and pushing against [npc.namePos] thighs,"
									+ " [npc2.she] finally [npc2.verb(achieve)] a small victory as [npc2.her] [npc2.hair(true)] is released and [npc.namePos] [npc.cock+] is momentarily slid free from [npc2.her] throat."));
					break;
				default: // For dom normal, sub normal, and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] a moment caressing the underside of [npc.namePos] [npc.cock] with [npc2.her] [npc2.tongue],"
									+ " before finally being able to gasp for air as [npc.she] momentarily [npc.verb(withdraw)] [npc.her] [npc.cock+] from [npc2.her] throat.",
							" Happily humming and [npc2.moaning] in delight, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue] to lovingly caress the underside of [npc.namePos] [npc.cock],"
									+ " obediently holding [npc2.her] breath until [npc.her] [npc.cock+] is eventually pulled free from [npc2.her] throat."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	public static final SexAction THROAT_MUSCLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Throat control";
		}

		@Override
		public String getActionDescription() {
			return "Squeeze your internally-muscled throat down around [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return UtilText.parse(performer, target,
					UtilText.returnStringAtRandom(
						"Letting out a muffled [npc.moan], [npc.name] [npc.verb(concentrate)] on squeezing the extra internal muscles within [npc.her] throat down around [npc2.namePos] [npc2.cock+].",
						"[npc.Name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(focus)] on controlling the extra muscles lining the insides of [npc.her] throat."
								+ " Gripping and squeezing them down around the [npc2.cock+] in [npc.her] mouth, [npc.name] [npc.verb(cause)] [npc2.name] to let out an involuntary cry of pleasure.",
						"[npc.Name] [npc.verb(find)] [npc.her] letting out a series of muffled [npc.moans] as [npc.she] [npc.verb(concentrate)] on squeezing the extra muscles within [npc.her] throat down around [npc2.namePos] [npc2.cock+].",
						"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on controlling the extra muscles deep within [npc.her] throat, gripping them down and massaging [npc2.namePos] [npc2.cock+]."));
		}
	};
	
	public static final SexAction BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Slide your [npc.cock+] into [npc2.namePos] mouth and get [npc2.herHim] to give you a blowjob.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.her] [npc2.lips+],"
										+ " before gently pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before slowly bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and gently sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.her] [npc2.lips+],"
										+ " before eagerly pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before eagerly bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.her] [npc2.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] forwards and forcing [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before roughly slamming [npc.her] [npc.hips] into [npc2.her] [npc2.face] and forcing [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Reaching down to grab [npc2.namePos] head, [npc.name] [npc.verb(line)] the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.her] [npc2.lips+],"
										+ " before pushing [npc.her] [npc.hips] forwards and sliding [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Reaching down to take hold of [npc2.namePos] head, [npc.name] [npc.verb(push)] the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " before bucking [npc.her] [npc.hips] into [npc2.her] [npc2.face] and sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					default:
						break;
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] slowly [npc.verb(lower)] [npc.her] [npc.cock+] down to [npc2.namePos] mouth,"
										+ " before gently pushing the [npc.cockHead+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Gently lowering [npc.her] [npc.cock+] down to [npc2.namePos] mouth, [npc.name] slowly [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past [npc2.her] [npc2.lips+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly [npc.verb(drop)] [npc.her] [npc.cock+] down to [npc2.namePos] mouth,"
										+ " before eagerly pushing the [npc.cockHead+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Quickly dropping [npc.her] [npc.cock+] down to [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] eagerly forces the [npc.cockHead+] past [npc2.her] [npc2.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.cock+] down against [npc2.namePos] mouth,"
										+ " before forcefully pushing the [npc.cockHead+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Roughly grinding [npc.her] [npc.cock+] down against [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past [npc2.her] [npc2.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Shuffling back a little, [npc.name] quickly [npc.verb(drop)] [npc.her] [npc.cock+] down to [npc2.namePos] mouth,"
										+ " before pushing the [npc.cockHead+] past [npc2.her] [npc2.lips+] and collapsing down onto [npc2.her] [npc2.face+].",

								"Quickly dropping [npc.her] [npc.cock+] down to [npc2.namePos] mouth, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs] to give out from under [npc.herHim]"
										+ " as [npc.she] forces the [npc.cockHead+] past [npc2.her] [npc2.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] gently [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], gently sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] eagerly [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] eagerly [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] roughly slams [npc.her] [npc.hips] forwards and [npc.verb(force)] [npc.her] [npc.cock+] into [npc2.namePos] mouth.",

								"Grinding the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] roughly [npc.verb(slam)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], forcing [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Lining the [npc.cockHead+] of [npc.her] [npc.cock] up to [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] [npc.verb(push)] [npc.her] [npc.hips] forwards and [npc.verb(slide)] [npc.her] [npc.cock+] into [npc2.her] mouth.",

								"Pushing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.lips+],"
										+ " [npc.name] [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], sliding [npc.her] [npc.cock+] into [npc2.her] mouth."));
						break;
					default:
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan], slowly sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] gently sucking [npc.namePos] [npc.cock+].",

							" With a soft, muffled [npc2.moan], [npc2.name] [npc2.verb(start)] gently sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] happily sucking [npc.namePos] [npc.cock+].",

							" With an eager, and very muffled [npc2.moan], [npc2.name] [npc2.verb(start)] happily sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], roughly slamming [npc2.namePos] head forwards as [npc2.she] [npc2.verb(start)] forcing [npc.namePos] [npc.cock+] deep down [npc2.her] throat.",

							" With an eager, and very muffled [npc2.moan], [npc2.name] forcefully [npc2.verb(push)] [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] a rough blowjob."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] sucking [npc.namePos] [npc.cock+].",

							" With a muffled [npc2.moan], [npc2.name] [npc2.verb(start)] sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(start)] giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.sob], gargling and choking on [npc.namePos] [npc.cock+] as [npc2.she] frantically [npc2.verb(try)] to pull [npc2.her] head away from [npc.her] groin.",

							" With a muffled [npc2.sob], [npc2.name] frantically [npc2.verb(try)] to pull away from [npc.namePos] [npc.cock+],"
									+ " gargling and choking as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(struggle)] against [npc.herHim]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction());
		}

		private List<GameCharacter> getCharactersForParsing() {
			return PenisMouth.getCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}
		
		private String getOngoingNames() {
			return PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterPerformingAction())) {
				return false; // Do not allow additional blowjobs if the performing character is performing autofellatio
			}
			int size = PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();

			map.put("[npc.nameIsFull] not performing autofellatio", !PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterPerformingAction()));
			map.put("one or two characters performing blowjob", size>0 && size<3);
			map.put("only ongoing penis-actions are blowjobs", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).size());
			map.put("[npc2.namePos] mouth to be exposed", Main.sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc2.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterTargetedForSexAction(this)));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "Join blowjob";
		}
		
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to join "+getOngoingNames()+" in giving you a blowjob.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Not satisfied with having just "+getOngoingNames()+" sucking [npc.her] [npc.cock], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"them")+" to pull back and temporarily move aside a little,"
										+ " before lining the saliva-coated [npc.cockHead] of [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.lips+] and gently sliding it into [npc2.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Not satisfied with having just "+getOngoingNames()+" sucking [npc.her] [npc.cock], [npc.name] [npc.verb(push)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"them")+" back,"
										+ " before thrusting the saliva-coated [npc.cockHead] of [npc.her] [npc.cock+] up against [npc2.namePos] [npc2.lips+] and roughly forcing it into [npc2.her] mouth."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Not satisfied with having just "+getOngoingNames()+" sucking [npc.her] [npc.cock], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"them")+" to pull back and temporarily move aside a little,"
										+ " before lining the saliva-coated [npc.cockHead] of [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.lips+] and sliding it into [npc2.her] mouth."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Not satisfied with having just "+getOngoingNames()+" sucking [npc.her] [npc.cock], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"them")+" to pull back and temporarily move aside a little,"
										+ " before eagerly lining the saliva-coated [npc.cockHead] of [npc.her] [npc.cock+] up to [npc2.namePos] [npc2.lips+] and quickly thrusting it into [npc2.her] mouth."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.moan], slowly sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" in gently sucking [npc.namePos] [npc.cock+].",
							" With a soft, muffled [npc2.moan], [npc2.name] [npc2.verb(start)] gently sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" in giving [npc.herHim] a blowjob."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], roughly slamming [npc2.namePos] head forwards as [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" in forcing [npc.namePos] [npc.cock+] deep down [npc2.her] throat.",
							" With an eager, and very muffled [npc2.moan], [npc2.name] forcefully [npc2.verb(push)] [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" in giving [npc.herHim] a rough blowjob."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" in sucking [npc.namePos] [npc.cock+].",
							" With a muffled [npc2.moan], [npc2.name] [npc2.verb(start)] sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" in giving [npc.herHim] a blowjob."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out a muffled [npc2.sob], gargling and choking on [npc.namePos] [npc.cock+] as [npc2.she] frantically [npc2.verb(try)] to pull [npc2.her] head away from [npc.her] groin.",
							" With a muffled [npc2.sob], [npc2.name] frantically [npc2.verb(try)] to pull away from [npc.namePos] [npc.cock+],"
									+ " gargling and choking as [npc2.she] [npc2.verb(squirm)] and [npc2.verb(struggle)] against [npc.herHim]."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], eagerly sliding [npc2.her] head forwards as [npc2.she] [npc2.verb(start)] happily joining "+getOngoingNames()+" in sucking [npc.namePos] [npc.cock+].",
							" With an eager, and very muffled [npc2.moan], [npc2.name] [npc2.verb(start)] happily sliding [npc2.her] head forwards,"
									+ " wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+] as [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" in giving [npc.herHim] a blowjob."));
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
						" [npc2.Name] eagerly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] enthusiastically [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, eagerly wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.she] enthusiastically [npc2.verb(continue)] to give [npc.herHim] head.",
	
						" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " eagerly licking and sucking [npc.her] [npc.cock] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Desperately trying, and failing, to pull back from [npc.namePos] [npc.cock],"
								+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+], tears streaming down [npc2.her] [npc2.face] as [npc2.she] weakly [npc2.verb(make)] muffled noises of protest.",
	
						" A muffled [npc2.sob] escapes from [npc2.namePos] mouth as [npc2.she] weakly [npc2.verb(try)] to pull back,"
								+ " tears streaming down [npc2.her] [npc2.face] as [npc.namePos] [npc.cock+] continues sliding back and forth past [npc2.her] [npc2.lips+].",
	
						" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face],"
								+ " [npc2.name] weakly [npc2.verb(struggle)] against [npc.name] as [npc2.she] [npc2.verb(make)] muffled noises of protest."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.she] [npc2.verb(continue)] to give [npc.herHim] head.",
	
						" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " licking and sucking [npc.her] [npc.cock] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] gently [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] slowly [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, gently wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.she] lovingly [npc2.verb(continue)] to give [npc.herHim] head.",
	
						" [npc2.Moaning] in delight, [npc2.name] softly [npc2.verb(wrap)] [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+],"
								+ " gently licking and sucking [npc.her] [npc.cock] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] forcefully [npc2.verb(grip)] [npc2.her] [npc2.lips+] down around [npc.namePos] [npc.cock+],"
								+ " letting out a very muffled [npc2.moan] as [npc2.she] roughly [npc2.verb(bob)] [npc2.her] head up and down.",
	
						" A very muffled [npc2.moan] drifts out from [npc2.namePos] mouth,"
								+ " and, forcefully wrapping [npc2.her] [npc2.lips+] around [npc.namePos] [npc.cock+], [npc2.she] roughly [npc2.verb(continue)] to give [npc.herHim] head.",
	
						" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(grip)] [npc2.her] [npc2.lips+] down around [npc.namePos] [npc.cock+],"
								+ " forcefully licking and sucking [npc.her] [npc.cock] as [npc2.she] [npc2.verb(continue)] making muffled [npc2.moaning] noises."));
				break;
		}
		return "";
	}
	
	public static final SexAction BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Gently receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Hold still and let [npc2.name] suck your [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Getting "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to move aside a little, [npc.name] gently [npc.verb(slide)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out a soft [npc.moan] as [npc.she] steadily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"Gently instructing "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to let [npc2.name] take the lead,"
									+ " [npc.name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], letting out a soft [npc.moan] as [npc.she] rhythmically [npc.verb(fuck)] [npc2.her] throat.",
							"After telling "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to make room for [npc2.name], [npc.name] gently [npc.verb(buck)] [npc.her] [npc.hips+] into [npc2.her] [npc2.face],"
									+ " letting out a soft [npc.moan] as [npc.she] [npc.verb(get)] [npc2.herHim] to suck [npc.her] [npc.cock+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] gently [npc.verb(slide)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out a soft [npc.moan] as [npc.she] steadily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"[npc.Name] slowly [npc.verb(push)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out a soft [npc.moan] as [npc.she] gently [npc.verb(fuck)] [npc2.her] throat.",
							"Gently bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(continue)] receiving [npc.her] blowjob."));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.cock+] into [npc2.namePos] face to encourage [npc2.herHim] to continue giving you a blowjob.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Getting "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to move aside a little, [npc.name] eagerly [npc.verb(slide)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"Impatiently instructing "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to let [npc2.name] take the lead,"
									+ " [npc.name] desperately [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] throat.",
							"After telling "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to make room for [npc2.name], [npc.name] enthusiastically [npc.verb(buck)] [npc.her] [npc.hips+] into [npc2.her] [npc2.face],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(get)] [npc2.herHim] to suck [npc.her] [npc.cock+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"[npc.Name] desperately [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] throat.",
							"Enthusiastically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc.her] blowjob."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Face fuck";
		}

		@Override
		public String getActionDescription() {
			return "Roughly thrust your [npc.cock+] down [npc2.namePos] throat and give [npc2.herHim] a good face-fucking.";
		}

		@Override
		public String getDescription() {
			List<String> descriptions = new ArrayList<>();

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.STANDING)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING)) {

				if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
					
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcing "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to move aside,",
								"Impatiently shouting at "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to let [npc2.name] take the lead,",
								"After ordering "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to make room for [npc2.name],"));
					
					for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
						switch(pm) {
							case BARBED:
								descriptions.add(" [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
													+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] [npc2.face],"
													+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as the barbs lining [npc.namePos] shaft repeatedly rake up the sides of [npc2.her] throat.");
								break;
							case FLARED:
								descriptions.add(" [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
													+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.her] [npc2.face],"
													+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as [npc.namePos] wide, flared head forces its way up and down [npc2.her] throat.");
								break;
							case KNOTTED:
								descriptions.add(" [npc.name] suddenly, and violently, [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
													+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.her] [npc2.face],"
													+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as [npc.namePos] [npc.verb(slam)] [npc.her] knot repeatedly against [npc2.her] [npc2.lips+].");
								break;
							default:
								break;
						}
					}
					
					descriptions.add(" [npc.name] [npc.verb(grab)] the sides of [npc2.namePos] head, and before [npc2.she] [npc2.verb(know)] what's happening,"
										+ " [npc.sheIs] roughly slamming [npc.her] [npc.cock+] in and out of [npc2.her] facial fuck-hole.");
					descriptions.add(" [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] all the way down [npc2.namePos] throat."
										+ " As [npc2.she] [npc2.verb(blink)] back tears, [npc.name] [npc.verb(start)] rapidly bucking [npc.her] [npc.hips] back and forth,"
										+ " holding [npc2.namePos] head in place with both hands as [npc.she] ruthlessly [npc.verb(fuck)] [npc2.her] [npc2.face].");
					descriptions.add(" [npc.name] roughly [npc.verb(pull)] [npc2.her] face into [npc.her] groin,"
										+ " sinking [npc.her] [npc.cock+] deep down [npc2.her] throat before starting to ruthlessly fuck [npc2.her] [npc2.face].");
					descriptions.add(" [npc.name] roughly [npc.verb(hilt)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
										+ " As a slimy stream of saliva "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"and precum ":"")
										+"drools from the corners of [npc2.her] mouth, [npc.name] bucks back, letting [npc2.name] gasp for air for a brief moment before starting to aggressively fuck [npc2.her] [npc2.face].");

					
					return UtilText.nodeContentSB.toString()
							+Util.randomItemFrom(descriptions);
					
				} else {
					for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
						switch(pm) {
							case BARBED:
								descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
													+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] [npc2.face],"
													+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as the barbs lining [npc.namePos] shaft repeatedly rake up the sides of [npc2.her] throat.");
								break;
							case FLARED:
								descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
													+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.her] [npc2.face],"
													+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as [npc.namePos] wide, flared head forces its way up and down [npc2.her] throat.");
								break;
							case KNOTTED:
								descriptions.add("With a sudden, violent thrust forwards, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
													+ " Holding [npc2.her] head in place with both [npc.hands], [npc.she] then proceeds to start roughly fucking [npc2.her] [npc2.face],"
													+ " causing tears to start streaming from [npc2.her] [npc2.eyes] as [npc.namePos] [npc.verb(slam)] [npc.her] knot repeatedly against [npc2.her] [npc2.lips+].");
								break;
							default:
								break;
						}
					}
					
					descriptions.add("[npc.Name] [npc.verb(grab)] the sides of [npc2.namePos] head, and before [npc2.she] [npc2.verb(know)] what's happening,"
										+ " [npc.sheIs] roughly slamming [npc.her] [npc.cock+] in and out of [npc2.her] facial fuck-hole.");
					descriptions.add("Letting out [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] all the way down [npc2.namePos] throat."
										+ " As [npc2.she] [npc2.verb(blink)] back tears, [npc.name] [npc.verb(start)] rapidly bucking [npc.her] [npc.hips] back and forth,"
										+ " holding [npc2.namePos] head in place with both hands as [npc.she] ruthlessly [npc.verb(fuck)] [npc2.her] [npc2.face].");
					descriptions.add("Grabbing the sides of [npc2.namePos] head, [npc.name] roughly [npc2.verb(pull)] [npc2.her] face into [npc.her] groin,"
										+ " sinking [npc.her] [npc.cock+] deep down [npc2.her] throat before starting to ruthlessly fuck [npc2.her] [npc2.face].");
					descriptions.add("With a forceful thrust, [npc.name] [npc.verb(hilt)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
										+ " As a slimy stream of saliva "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"and precum ":"")
										+"drools from the corners of [npc2.her] mouth, [npc.name] bucks back, letting [npc2.name] gasp for air for a brief moment before starting to aggressively fuck [npc2.her] [npc2.face].");
					
					return Util.randomItemFrom(descriptions);
				}
				
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] throat being raked by the series of barbs that line the sides of [npc.her] [npc.cock+].");
							break;
						case BLUNT:
							break;
						case FLARED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] throat being stretched out by the wide, flared head of [npc.her] [npc.cock+].");
							break;
						case KNOTTED:
							descriptions.add("Spreading [npc.her] knees out on either side of [npc2.namePos] head, [npc.name] violently [npc.verb(thrust)] downwards, burying [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
												+ " Grinding the base against [npc2.her] [npc2.lips+] for moment, [npc.she] then [npc.verb(proceed)] to start roughly fucking [npc2.her] face,"
												+ " causing [npc2.name] to let out a muffled [npc2.moan] as [npc2.she] [npc2.verb(struggle)] to breathe,"
												+ " and, squirming about beneath [npc.name], [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.lips+] being repeatedly battered by the fat knot at the base of [npc.her] [npc.cock+].");
							break;
						case PREHENSILE:
						case RIBBED:
						case SHEATHED:
						case TAPERED:
						case TENTACLED:
						case VEINY:
						case OVIPOSITOR:
							break;
					}
				}

				if(Main.sex.getCharacterPerformingAction().hasLegs()) {
					descriptions.add("[npc.Name] [npc.verb(spread)] [npc.her] knees out on either side of [npc2.namePos] head, and before [npc2.she] [npc2.verb(know)] what's happening,"
							+ " [npc.nameIs] roughly slamming [npc.her] [npc.cock+] in and out of [npc2.her] facial fuck-hole.");
					
				} else {
					descriptions.add("[npc.Name] [npc.verb(push)] [npc.her] groin down onto [npc2.namePos] face, and before [npc2.she] [npc2.verb(know)] what's happening,"
							+ " [npc.nameIs] roughly slamming [npc.her] [npc.cock+] in and out of [npc2.her] facial fuck-hole.");
				}

				descriptions.add("Letting out [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] all the way down [npc2.namePos] throat."
									+ " As [npc2.she] [npc2.verb(blink)] back tears, [npc.name] [npc.verb(start)] rapidly slamming [npc.her] [npc.hips] up and down,"
									+ " letting out more [npc.moans+] as [npc.she] ruthlessly [npc.verb(fuck)] [npc2.her] [npc2.face].");

				descriptions.add("Dropping down onto [npc2.namePos] face, [npc.name] roughly slams [npc.her] [npc.cock+] deep down [npc2.her] throat,"
									+ " letting out [npc.a_moan+] before starting to violently slam [npc.her] [npc.hips] up and down as [npc.she] ruthlessly [npc.verb(fuck)] [npc2.her] face.");

				descriptions.add("With a forceful thrust, [npc.name] [npc.verb(hilt)] [npc.her] [npc.cock+] deep down [npc2.namePos] throat."
									+ " As a slimy stream of "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"cummy ":"")
									+"saliva drools from the corners of [npc2.her] mouth, [npc.name] lifts [npc.herself] up,"
										+ " letting [npc2.name] gasp for air for a brief moment before sinking down once more and starting to aggressively fuck [npc2.her] face.");

				return Util.randomItemFrom(descriptions);
				
			} else {
				UtilText.nodeContentSB.setLength(0);

				if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcing "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to move aside, [npc.name] roughly [npc.verb(slam)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
										+ " letting out [npc.a_moan+] as [npc.she] violently [npc.verb(thrust)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
								"Impatiently shouting at "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to let [npc2.name] take the lead,"
										+ " [npc.name] violently [npc.verb(slam)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] throat.",
								"After ordering "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to make room for [npc2.name], [npc.name] aggressively [npc.verb(buck)] [npc.her] [npc.hips+] into [npc2.her] [npc2.face],"
										+ " letting out [npc.a_moan+] as [npc.she] forcefully [npc.verb(fuck)] [npc2.her] face."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] roughly [npc.verb(slam)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
										+ " letting out [npc.a_moan+] as [npc.she] violently [npc.verb(thrust)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
								"[npc.Name] violently [npc.verb(slam)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] roughly [npc.verb(fuck)] [npc2.her] throat.",
								"Aggressively bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
										+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] roughly fucking [npc2.her] face."));
				}
				UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
		
	};
	
	public static final SexAction BLOWJOB_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Resist blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Try to pull away from [npc2.namePos] [npc2.cock].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] gently, but firmly,"
											+ " [npc2.verb(hold)] [npc.herHim] in place, planting kiss after gentle kiss on the sides of [npc.her] [npc.cock+] and [npc.balls] as [npc3.name] [npc3.verb(continue)] giving [npc.herHim] an unwanted blowjob.",
									"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc3.namePos] mouth."
											+ " [npc2.NamePos] grip proves to be too strong, however, and [npc2.she] gently, but firmly, [npc2.verb(hold)] [npc.herHim] in place,"
											+ " kissing the sides of [npc.her] [npc.cock+] as [npc3.namePos] [npc3.lips+] run up and down around [npc.her] [npc.cock].",
									"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc3.namePos] mouth, but [npc2.namePos] grip is too strong for [npc.herHim] to break away from"
											+ ", and [npc.her] struggles prove to be in vain as [npc2.she] [npc2.verb(hold)] [npc.herHim] in place,"
											+ " gently kissing the sides and base of [npc.her] [npc.cock] while [npc3.name] [npc3.verb(bob)] [npc3.her] head up and down in [npc.her] lap.")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] roughly"
											+ " [npc2.verb(hold)] [npc.herHim] in place, planting kiss after forceful kiss on the sides of [npc.her] [npc.cock+] and [npc.balls] as [npc3.name] [npc3.verb(continue)] giving [npc.herHim] an unwanted blowjob.",
									"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc3.namePos] mouth."
											+ " [npc2.NamePos] grip proves to be far too strong, however, and [npc2.she] forcefully [npc2.verb(hold)] [npc.herHim] in place,"
											+ " [npc2.verb(let)] out a threatening growl as [npc3.namePos] [npc3.lips+] run up and down around [npc.her] [npc.cock].",
									"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc3.namePos] mouth, but [npc2.namePos] grip is far too strong for [npc.herHim] to break away from"
											+ ", and [npc.her] struggles prove to be in vain as [npc2.she] [npc2.verb(hold)] [npc.herHim] in place,"
											+ " roughly kissing the sides and base of [npc.her] [npc.cock] while [npc3.name] [npc3.verb(bob)] [npc3.her] head up and down in [npc.her] lap.")));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] firmly"
											+ " [npc2.verb(hold)] [npc.herHim] in place, planting kiss after eager kiss on the sides of [npc.her] [npc.cock+] and [npc.balls] as [npc3.name] [npc3.verb(continue)] giving [npc.herHim] an unwanted blowjob.",
									"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc3.namePos] mouth."
											+ " [npc2.NamePos] grip proves to be too strong, however, and [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
											+ " kissing the sides of [npc.her] [npc.cock+] as [npc3.namePos] [npc3.lips+] run up and down around [npc.her] [npc.cock].",
									"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc3.namePos] mouth, but [npc2.namePos] grip is too strong for [npc.herHim] to break away from"
											+ ", and [npc.her] struggles prove to be in vain as [npc2.she] [npc2.verb(hold)] [npc.herHim] in place,"
											+ " eagerly kissing the sides and base of [npc.her] [npc.cock] while [npc3.name] [npc3.verb(bob)] [npc3.her] head up and down in [npc.her] lap.")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] gently, but firmly,"
									+ " [npc2.verb(hold)] [npc.herHim] in place, slowly sliding [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.cock+] as [npc2.she] [npc2.verb(continue)] giving [npc.herHim] an unwanted blowjob.",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
									+ " [npc2.Her] grip proves to be too strong, however, and [npc2.name] gently, but firmly, [npc.verb(continue)] to suck [npc.her] [npc.cock+] as [npc.she] weakly struggles against [npc2.herHim].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
									+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] a slow, gentle blowjob."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] roughly [npc2.verb(hold)] [npc.herHim] in place,"
									+ " growling in a threatening manner as [npc2.she] forcefully [npc2.verb(slide)] [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.cock+].",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
									+ " [npc2.Her] grip proves to be far too strong, however, and [npc2.name] [npc2.verb(let)] out a threatening growl as [npc2.name] [npc2.verb(continue)] to suck [npc.her] [npc.cock+].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
									+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] a rough, forceful blowjob."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Letting out [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] hips away from [npc2.namePos] face, but [npc.her] efforts prove to be in vain as [npc2.she] firmly [npc2.verb(hold)] [npc.herHim] in place,"
									+ " eagerly sliding [npc2.her] [npc2.lips+] up and down the length of [npc.her] [npc.cock+] as [npc2.she] [npc2.verb(continue)] giving [npc.herHim] an unwanted blowjob.",
	
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth."
									+ " [npc2.Her] grip proves to be too strong, however, and [npc2.she] eagerly [npc.verb(continue)] to suck [npc.her] [npc.cock+] as [npc.she] weakly struggles against [npc2.herHim].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)] to pull [npc.her] [npc.cock+] out of [npc2.namePos] mouth, but [npc2.her] grip is too strong for [npc.herHim]"
									+ ", and [npc.her] struggles prove to be in vain as [npc2.name] [npc2.verb(continue)] giving [npc.herHim] an eager blowjob."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Push your [npc.cock+] into [npc2.namePos] face to encourage [npc2.herHim] to continue giving you a blowjob.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Getting "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to move aside a little, [npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"Instructing "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to let [npc2.name] take the lead,"
									+ " [npc.name] [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], letting out [npc.a_moan+] as [npc.she] [npc.verb(fuck)] [npc2.her] throat.",
							"After telling "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to make room for [npc2.name], [npc.name] [npc.verb(buck)] [npc.her] [npc.hips+] into [npc2.her] [npc2.face],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(get)] [npc2.herHim] to suck [npc.her] [npc.cock+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"[npc.Name] [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] [npc.verb(fuck)] [npc2.her] throat.",
							"Bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc.her] blowjob."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Eagerly receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly thrust your [npc.hips] into [npc2.namePos] face, forcing your [npc.cock+] down [npc2.her] throat.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Getting "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to move aside a little, [npc.name] eagerly [npc.verb(slide)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"Impatiently instructing "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to let [npc2.name] take the lead,"
									+ " [npc.name] desperately [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.her] [npc2.face], letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] throat.",
							"After telling "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" to make room for [npc2.name], [npc.name] enthusiastically [npc.verb(buck)] [npc.her] [npc.hips+] into [npc2.her] [npc2.face],"
									+ " letting out [npc.a_moan+] as [npc.she] [npc.verb(get)] [npc2.herHim] to suck [npc.her] [npc.cock+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] eagerly [npc.verb(thrust)] [npc.her] [npc.cock+] past [npc2.namePos] [npc2.lips+],"
									+ " letting out [npc.a_moan+] as [npc.she] greedily [npc.verb(pump)] [npc.her] [npc.hips] into [npc2.her] [npc2.face].",
							"[npc.Name] desperately [npc.verb(buck)] [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], letting out [npc.a_moan+] as [npc.she] eagerly [npc.verb(fuck)] [npc2.her] throat.",
							"Enthusiastically bucking [npc.her] [npc.hips+] into [npc2.namePos] [npc2.face],"
									+ " [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(continue)] happily receiving [npc.her] blowjob."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop receiving blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [npc2.namePos] mouth and stop receiving a blowjob from [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				UtilText.nodeContentSB.append("Using [npc.her] knees to lift [npc.herself] up, [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+] to slide up and out of [npc2.namePos] mouth."
							+ " A slimy strand of saliva links [npc2.namePos] [npc2.lips+] to the [npc.cockHead+] of [npc.her] [npc.cock] for a brief moment, before breaking to fall down over [npc2.her] face.");
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly slamming [npc.her] [npc.cock+] deep down [npc2.namePos] throat one last time, [npc.name] then [npc2.verb(pull)] [npc.her] [npc.hips] back,"
										+ " grinning as [npc2.name] [npc2.verb(splutter)] and [npc2.verb(gasp)] for air.",

								"Slamming [npc.her] [npc.hips] into [npc2.namePos] [npc2.face], [npc.name] forces [npc.her] [npc.cock+] deep down [npc2.her] throat, before pulling completely back and out of [npc2.her] mouth."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Sliding [npc.her] [npc.cock+] out of [npc2.namePos] mouth, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] puts an end to the blowjob.",

								"With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] [npc.hips] back, sliding [npc.her] [npc.cock+] fully out of [npc2.namePos] mouth."));
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], struggling in desperation as [npc2.she] [npc2.verb(plead)] for [npc.name] to let [npc2.herHim] go.",

							" [npc2.Name] [npc2.verb(feel)] tears streaming down [npc2.her] cheeks as [npc2.she] [npc2.verb(try)] to struggle out of [npc.namePos] grip,"
									+ " letting out [npc2.a_sob+] before begging for [npc.herHim] to let [npc2.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], revealing [npc2.her] desire to continue sucking [npc.namePos] [npc.cock+].",

							" [npc2.Name] [npc2.moanVerb] as [npc.name] withdraws from [npc2.her] mouth, betraying [npc2.her] desperate desire to continue sucking [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction GIVING_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.cock+] into your mouth and start giving [npc2.herHim] a blowjob.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] slowly [npc.verb(lower)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " passionately kissing the [npc2.cockHead+] of [npc2.her] [npc2.cock+] before taking [npc2.herHim] into [npc.her] mouth.",

								"Gently lowering [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, slow lick up the length of [npc2.her] [npc2.cock+], before taking the [npc2.cockHead+] into [npc.her] mouth."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] eagerly [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " passionately kissing the [npc2.cockHead+] of [npc2.her] [npc2.cock+] before greedily taking [npc2.herHim] into [npc.her] mouth.",

								"Eagerly dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.cock+], before greedily taking the [npc2.cockHead+] into [npc.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] quickly [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " roughly kissing the [npc2.cockHead+] of [npc2.her] [npc2.cock+] before forcefully taking [npc2.herHim] into [npc.her] mouth.",

								"Dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a rough, wet lick up the length of [npc2.her] [npc2.cock+], before forcefully taking the [npc2.cockHead+] into [npc.her] mouth."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.NamePos] hot breath falls down onto [npc2.namePos] groin as [npc.she] [npc.verb(drop)] [npc.her] head down between [npc2.her] [npc2.legs],"
										+ " kissing the [npc2.cockHead+] of [npc2.her] [npc2.cock+] before taking [npc2.herHim] into [npc.her] mouth.",

								"Dropping [npc.her] head down between [npc2.namePos] [npc2.legs],"
										+ " [npc.name] [npc.verb(deliver)] a long, wet lick up the length of [npc2.her] [npc2.cock+], before taking the [npc2.cockHead+] into [npc.her] mouth."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] to the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] slowly [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a blowjob.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a gentle blowjob."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] eagerly [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] happily [npc.verb(start)] giving [npc2.herHim] a blowjob.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] eagerly [npc.verb(start)] giving [npc2.herHim] an enthusiastic blowjob."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] forcefully [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a rough blowjob.",

								"Forcefully wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a rough blowjob."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Bringing [npc.her] [npc.lips+] up to the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(take)] [npc2.herHim] into [npc.her] mouth, letting out a muffled [npc.moan] as [npc.she] [npc.verb(start)] giving [npc2.herHim] a blowjob.",

								"Wrapping [npc.her] [npc.lips+] around the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
										+ " [npc.name] [npc.verb(let)] out a muffled [npc.moan] as [npc.she] eagerly [npc.verb(start)] giving [npc2.herHim] a blowjob."));
						break;
					default:
						break;
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], softly [npc2.moaning] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",

							" With a gentle buck of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out a soft [npc2.moan] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] greedily [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",

							" With an energetic buck of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] roughly [npc2.verb(slam)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",

							" With a rough [npc.verb(thrust)] of [npc2.her] [npc2.hips],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.moaning+] as [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",

							" Bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(enjoy)] the feeling of [npc.namePos] [npc.lips+] sliding up and down the length of [npc2.her] [npc2.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], trying, and failing, to pull [npc2.her] [npc2.cock] out of [npc.namePos] mouth as [npc2.she] [npc2.verb(struggle)] against the unwanted oral attention.",

							" With tears welling up in [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(struggle)] against the unwanted oral attention, [npc2.sobbing] and squirming as [npc2.she] [npc2.verb(beg)] for [npc.herHim] to stop."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction GIVING_BLOWJOB_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this));
		}

		private List<GameCharacter> getCharactersForParsing() {
			return PenisMouth.getCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		private String getOngoingNames() {
			return PenisMouth.getOngoingNames(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return false; // Do not allow additional blowjobs if the targeted character is performing autofellatio
			}
			int size = getOngoingCharacters().size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();

			map.put("[npc2.nameIsFull] not performing autofellatio", !PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterTargetedForSexAction(this)));
			map.put("one or two characters performing blowjob", size>0 && size<3);
			map.put("only ongoing penis-actions are blowjobs", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS).size());
			map.put("[npc.namePos] mouth to be exposed", Main.sex.getCharacterPerformingAction().isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction()));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "Join blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Assist "+getOngoingNames()+" in orally servicing [npc2.namePos] [npc2.cock+] by joining in on giving [npc2.herHim] a blowjob.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Wanting to join "+getOngoingNames()+" in giving [npc2.name] a blowjob, [npc.name] [npc.verb(move)] forwards beside "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"them")
								+", before leaning in and delivering a gentle kiss to the side of [npc2.her] [npc2.penisGirth] [npc2.cock]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Wanting to join "+getOngoingNames()+" in giving [npc2.name] a blowjob, [npc.name] [npc.verb(elbow)] [npc.her] way in beside "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"them")
								+", before leaning in and delivering a rough kiss to the side of [npc2.her] [npc2.penisGirth] [npc2.cock]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Wanting to join "+getOngoingNames()+" in giving [npc2.name] a blowjob, [npc.name] [npc.verb(move)] forwards beside "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"them")
								+", before leaning in and delivering a quick kiss to the side of [npc2.her] [npc2.penisGirth] [npc2.cock]."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Wanting to join "+getOngoingNames()+" in giving [npc2.name] a blowjob, [npc.name] eagerly [npc.verb(move)] forwards beside "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"them")
								+", before leaning in and delivering an enthusiastic, sloppy kiss to the side of [npc2.her] [npc2.penisGirth] [npc2.cock]."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							" [npc2.Name] [npc2.verb(let)] out a gentle [npc2.moan] in response, and, getting "+getOngoingNames()+" to temporarily back off a little,"
									+ " [npc2.she] [npc2.verb(allow)] [npc.name] to take [npc2.her] [npc2.cock+] into [npc.her] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							" [npc2.Name] [npc2.verb(let)] out a pleased [npc2.moan] in response, and, roughly ordering "+getOngoingNames()+" to temporarily back off, [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.cock+] into [npc.namePos] mouth."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan] in response, and, getting "+getOngoingNames()+" to temporarily back off a little,"
									+ " [npc2.she] [npc2.verb(allow)] [npc.name] to take [npc2.her] [npc2.cock+] into [npc.her] mouth."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							" [npc2.Name] [npc2.verb(let)] out a distressed [npc2.moan] in response,"
									+ " which only gets louder and more frantic as [npc.name] [npc.verb(push)] "+getOngoingNames()+" aside in order to take [npc2.her] [npc2.cock+] into [npc.her] mouth."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, and, getting "+getOngoingNames()+" to temporarily back off a little,"
									+ " [npc2.she] happily [npc2.verb(encourage)] [npc.name] to take [npc2.her] [npc2.cock+] into [npc.her] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction GIVING_BLOWJOB_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Deep throat";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.cock+] as deep as possible down your throat.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Getting [npc3.name] to pull back, [npc.name] gently [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+],"
											+ " before pushing [npc.her] head forwards and taking it as deep down [npc.her] throat as [npc.she] possibly can.",
									"With a soft [npc.moan], [npc.name] gently [npc.verb(pull)] [npc3.name] away from [npc2.namePos] [npc2.cock+], before leaning forwards,"
											+ " parting [npc.her] [npc.lips+], and taking as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] possibly can.",
									"After gently moving [npc3.name] out of the way, [npc.name] [npc.verb(slide)] [npc.her] head forwards,"
											+ " before gently parting [npc.her] [npc.lips+] and taking [npc2.namePos] [npc2.cock+] deep down [npc.her] throat.")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Unceremoniously pushing [npc3.name] out of the way, [npc.name] roughly [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+],"
											+ " before forcing [npc.her] head forwards and forcing it as deep down [npc.her] throat as [npc.she] possibly can.",
									"With [npc.a_moan+], [npc.name] roughly [npc.verb(push)] [npc3.name] away from [npc2.namePos] [npc2.cock+], before quickly leaning forwards,"
											+ " parting [npc.her] [npc.lips+], and forcefully taking as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] possibly can.",
									"After aggressively shoving [npc3.name] out of the way, [npc.name] [npc.verb(push)] [npc.her] head forwards,"
											+ " before greedily parting [npc.her] [npc.lips+] and forcing [npc2.namePos] [npc2.cock+] deep down [npc.her] throat.")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Getting [npc3.name] to pull back, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+],"
											+ " before pushing [npc.her] head forwards and taking it as deep down [npc.her] throat as [npc.she] possibly can.",
									"With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc3.name] away from [npc2.namePos] [npc2.cock+], before leaning forwards,"
											+ " parting [npc.her] [npc.lips+], and taking as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] possibly can.",
									"After moving [npc3.name] out of the way, [npc.name] [npc.verb(slide)] [npc.her] head forwards,"
											+ " before parting [npc.her] [npc.lips+] and taking [npc2.namePos] [npc2.cock+] deep down [npc.her] throat.")));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Getting [npc3.name] to pull back, [npc.name] eagerly [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+],"
											+ " before quickly pushing [npc.her] head forwards and greedily taking it as deep down [npc.her] throat as [npc.she] possibly can.",
									"With [npc.a_moan+], [npc.name] quickly [npc.verb(pull)] [npc3.name] away from [npc2.namePos] [npc2.cock+], before eagerly leaning forwards,"
											+ " parting [npc.her] [npc.lips+], and desperately taking as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] possibly can.",
									"After impatiently moving [npc3.name] out of the way, [npc.name] [npc.verb(slide)] [npc.her] head forwards,"
											+ " before greedily parting [npc.her] [npc.lips+] and taking [npc2.namePos] [npc2.cock+] deep down [npc.her] throat.")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently wrapping [npc.her] [npc.lips+] around the [npc2.cock+] in [npc.her] mouth, [npc.name] [npc.verb(push)] [npc.her] head forwards,"
										+ " taking [npc2.namePos] [npc2.cock+] as deep down [npc.her] throat as [npc.she] possibly can.",
	
								"With a soft, muffled [npc.moan], [npc.name] gently [npc.verb(lean)] forwards,"
										+ " parting [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] can.",
	
								"Slowly sliding [npc.her] head forwards, [npc.name] gently [npc.verb(part)] [npc.her] [npc.lips+], before taking [npc2.namePos] [npc2.cock+] deep down [npc.her] throat."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly wrapping [npc.her] [npc.lips+] around the [npc2.cock+] in [npc.her] mouth, [npc.name] quickly [npc.verb(push)] [npc.her] head forwards,"
										+ " greedily taking [npc2.namePos] [npc2.cock+] as deep down [npc.her] throat as [npc.she] possibly can.",
	
								"With a muffled, [npc.moan+], [npc.name] eagerly [npc.verb(lean)] forwards,"
										+ " parting [npc.her] [npc.lips+] as [npc.she] desperately [npc.verb(take)] as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] can.",
	
								"Greedily sliding [npc.her] head forwards, [npc.name] readily [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.cock+] deep down [npc.her] throat."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Forcefully wrapping [npc.her] [npc.lips+] around the [npc2.cock+] in [npc.her] mouth, [npc.name] roughly [npc.verb(slam)] [npc.her] head forwards,"
										+ " forcing [npc2.namePos] [npc2.cock+] as deep down [npc.her] throat as [npc.she] possibly can.",
	
								"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(lean)] forwards,"
										+ " parting [npc.her] [npc.lips+] as [npc.she] roughly [npc.verb(force)] as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] can.",
	
								"Aggressively pushing [npc.her] head forwards, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] deep down [npc.her] throat."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Wrapping [npc.her] [npc.lips+] around the [npc2.cock+] in [npc.her] mouth, [npc.name] quickly [npc.verb(push)] [npc.her] head forwards,"
										+ " taking [npc2.namePos] [npc2.cock+] as deep down [npc.her] throat as [npc.she] possibly can.",
	
								"With a muffled, [npc.moan+], [npc.name] [npc.verb(lean)] forwards,"
										+ " parting [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] as much of [npc2.namePos] [npc2.cock+] down [npc.her] throat as [npc.she] can.",
	
								"Sliding [npc.her] head forwards, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] as [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.cock+] deep down [npc.her] throat."));
						break;
					default:
						break;
				}
				
				UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {

		GameCharacter performer = Main.sex.getCharacterPerformingAction();
		GameCharacter target = Main.sex.getCharacterTargetedForSexAction(action);
		GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
		
		if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).size()>1) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				default:
					return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
							UtilText.returnStringAtRandom(
								" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat,"
										+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(receive)] [npc2.her] group blowjob.",
								" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep down [npc3.namePos] throat.",
								" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] as deep as possible into [npc3.namePos] throat."));
				case SUB_RESISTING:
					return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
							UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.cock] away from "+PenisMouth.getOngoingNames(target)+","
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push "+PenisMouth.getOngoingNames(target)+" away,"
									+ " squirming and protesting as [npc2.her] [npc2.cock+] continues to be mercilessly played with.",
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from "+PenisMouth.getOngoingNames(target)+"."));
				case DOM_GENTLE:
					return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
							UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat,"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(receive)] [npc2.her] group blowjob.",
							" A soft [npc2.moan] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat."));
				case DOM_ROUGH:
					return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
							UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(receive)] [npc2.her] group blowjob.",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] as deep as possible into [npc3.namePos] throat."));
				case SUB_NORMAL:
					return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
							UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(receive)] [npc2.her] group blowjob.",
							" [npc2.A_moan+] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc3.namePos] throat."));
			}
			
		} else {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				default:
					return UtilText.returnStringAtRandom(
							" [npc2.Name] greedily [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep down [npc.namePos] throat,"
									+ " letting out [npc2.a_moan+] as [npc2.she] enthusiastically [npc2.verb(receive)] [npc2.her] blowjob.",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep down [npc.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(drive)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] throat.");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.cock] away from [npc.namePos] mouth,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.name] [npc.verb(continue)] to force [npc2.her] [npc2.cock+] deep into [npc.her] throat.",
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.cock] away from [npc.namePos] mouth.");
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat,"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(receive)] [npc2.her] blowjob.",
							" [npc2.A_moan+] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] slowly sliding [npc2.her] [npc2.cock+] deep into [npc.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat.");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(receive)] [npc2.her] blowjob.",
							" [npc2.A_moan+] drifts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] violently thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.cock+] as deep as possible into [npc.namePos] throat.");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(receive)] [npc2.her] blowjob.",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth, before [npc2.she] [npc2.verb(start)] thrusting [npc2.her] [npc2.cock+] deep into [npc.namePos] throat.",
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] throat.");
			}
		}
	}
	
	public static final SexAction GIVING_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Gently suck [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Leaning in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against an exposed part of [npc2.namePos] [npc2.cock+], before starting to gently kiss and lick [npc2.her] shaft.",
						"With a soft [npc.moan], [npc.name] gently [npc.verb(lean)] in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " pressing [npc.her] [npc.lips+] against a free part of [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(kiss)] and [npc.verb(nuzzle)] in against [npc2.herHim].",
						"With [npc.her] head positioned in close alongside "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] gently [npc.verb(kiss)] and [npc.verb(lick)] any exposed part of [npc2.namePos] [npc2.cock+] that [npc.she] can find."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Gently wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] a loving blowjob.",
						"With a soft, muffled [npc.moan], [npc.name] gently [npc.verb(start)] bobbing [npc.her] head up and down,"
								+ " wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",
						"Slowly bobbing [npc.her] head up and down, [npc.name] gently [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] a blowjob."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Happily leaning in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against an exposed part of [npc2.namePos] [npc2.cock+], before starting to greedily kiss and lick [npc2.her] shaft.",
						"With [npc.a_moan+], [npc.name] impatiently [npc.verb(lean)] in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " pressing [npc.her] [npc.lips+] against a free part of [npc2.namePos] [npc2.cock+] as [npc.she] desperately [npc.verb(kiss)] and [npc.verb(nuzzle)] in against [npc2.herHim].",
						"With [npc.her] head positioned in close alongside "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] greedily [npc.verb(kiss)] and [npc.verb(lick)] any exposed part of [npc2.namePos] [npc2.cock+] that [npc.she] can find."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] an enthusiastic blowjob.",
						"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
								+ " greedily wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",
						"Rapidly bobbing [npc.her] head up and down, [npc.name] desperately [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] an eager blowjob."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Roughly suck [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Elbowing [npc.her] way in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " [npc.name] roughly [npc.verb(press)] [npc.her] [npc.lips+] against an exposed part of [npc2.namePos] [npc2.cock+], before starting to forcefully kiss and lick [npc2.her] shaft.",
						"With [npc.a_moan+], [npc.name] aggressively [npc.verb(push)] in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " pressing [npc.her] [npc.lips+] against a free part of [npc2.namePos] [npc2.cock+] as [npc.she] roughly [npc.verb(kiss)] and [npc.verb(press)] in against [npc2.herHim].",
						"With [npc.her] head positioned in close alongside "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] forcefully [npc.verb(kiss)] and [npc.verb(lick)] any exposed part of [npc2.namePos] [npc2.cock+] that [npc.she] can find."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Forcefully gripping [npc.her] [npc.lips+] down around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] aggressively bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] a rough blowjob.",
						"With a muffled, [npc.moan+], [npc.name] violently [npc.verb(start)] bobbing [npc.her] head up and down,"
								+ " roughly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",
						"Roughly bobbing [npc.her] head up and down, [npc.name] dominantly [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] a forceful blowjob."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist performing blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Try and push [npc2.namePos] [npc2.cock+] out of your mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] [npc2.verb(hold)] [npc.herHim] in place alongside "
										+PenisMouth.getOngoingNames(target, performer)+", gently pressing [npc.her] [npc.face] in towards [npc2.her] [npc2.cock+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back and away from [npc2.namePos] [npc2.cock+],"
										+ " but [npc.her] movements prove to be in vain as [npc2.name] gently, but firmly, [npc2.verb(pull)] [npc.herHim] back in alongside "+PenisMouth.getOngoingNames(target, performer)+".",
								"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
										+ " [npc.sobbing] in distress as [npc.sheIs] quickly pulled back in towards [npc2.her] [npc2.cock+] alongside "+PenisMouth.getOngoingNames(target, performer)+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] forcefully [npc2.verb(hold)] [npc.herHim] in place alongside "
										+PenisMouth.getOngoingNames(target, performer)+", roughly slamming [npc.her] [npc.face] down against [npc2.her] [npc2.cock+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back and away from [npc2.namePos] [npc2.cock+],"
										+ " but [npc.her] movements prove to be in vain as [npc2.name] roughly [npc2.verb(force)] [npc.herHim] back in alongside "+PenisMouth.getOngoingNames(target, performer)+".",
								"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
										+ " [npc.sobbing] in distress as [npc.sheIs] quickly forced back in towards [npc2.her] [npc2.cock+] alongside "+PenisMouth.getOngoingNames(target, performer)+"."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] [npc2.verb(hold)] [npc.herHim] in place alongside "
										+PenisMouth.getOngoingNames(target, performer)+", eagerly pressing [npc.her] [npc.face] in towards [npc2.her] [npc2.cock+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back and away from [npc2.namePos] [npc2.cock+],"
										+ " but [npc.her] movements prove to be in vain as [npc2.name] firmly [npc2.verb(pull)] [npc.herHim] back in alongside "+PenisMouth.getOngoingNames(target, performer)+".",
								"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
										+ " [npc.sobbing] in distress as [npc.sheIs] quickly pulled back in towards [npc2.her] [npc2.cock+] alongside "+PenisMouth.getOngoingNames(target, performer)+"."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] holds [npc.herHim] in place,"
										+ " slowly sliding [npc2.her] [npc2.cock+] back and forth past [npc.her] [npc.lips+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
										+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] gently pushing [npc2.her] [npc2.cock+] down [npc.her] throat.",
								"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
										+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] gently sliding [npc2.her] [npc2.cock+] in and out of [npc.her] mouth."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] forcefully holds [npc.herHim] in place,"
										+ " roughly pumping [npc2.her] [npc2.cock+] back and forth past [npc.her] [npc.lips+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
										+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] roughly slamming [npc2.her] [npc2.cock+] down [npc.her] throat.",
								"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
										+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] roughly thrusting [npc2.her] [npc2.cock+] in and out of [npc.her] mouth."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] out a muffled [npc.sob], squirming and struggling in distress as [npc2.name] holds [npc.herHim] in place,"
										+ " eagerly sliding [npc2.her] [npc2.cock+] back and forth past [npc.her] [npc.lips+].",
								"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull [npc.her] head back,"
										+ " [npc.her] protestations coming out in gargled bursts as [npc2.name] [npc2.verb(continue)] eagerly pushing [npc2.her] [npc2.cock+] down [npc.her] throat.",
								"[npc.Name] [npc.verb(feel)] tears running down [npc.namePos] cheeks as [npc.she] weakly [npc.verb(try)] to push [npc2.name] away,"
										+ " [npc.sobbing] in distress as [npc2.name] [npc2.verb(continue)] eagerly sliding [npc2.her] [npc2.cock+] in and out of [npc.her] mouth."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GIVING_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Perform blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Continue sucking [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Leaning in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against an exposed part of [npc2.namePos] [npc2.cock+], before starting to kiss and lick [npc2.her] shaft.",
						"With [npc.a_moan+], [npc.name] [npc.verb(lean)] in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " pressing [npc.her] [npc.lips+] against a free part of [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(kiss)] and [npc.verb(nuzzle)] in against [npc2.herHim].",
						"With [npc.her] head positioned in close alongside "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] [npc.verb(kiss)] and [npc.verb(lick)] any exposed part of [npc2.namePos] [npc2.cock+] that [npc.she] can find."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] a blowjob.",
						"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
								+ " wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",
						"Rapidly bobbing [npc.her] head up and down, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] a blowjob."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Eagerly suck [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Happily leaning in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.lips+] against an exposed part of [npc2.namePos] [npc2.cock+], before starting to greedily kiss and lick [npc2.her] shaft.",
						"With [npc.a_moan+], [npc.name] impatiently [npc.verb(lean)] in alongside "+PenisMouth.getOngoingNames(target, performer)+","
								+ " pressing [npc.her] [npc.lips+] against a free part of [npc2.namePos] [npc2.cock+] as [npc.she] desperately [npc.verb(kiss)] and [npc.verb(nuzzle)] in against [npc2.herHim].",
						"With [npc.her] head positioned in close alongside "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] greedily [npc.verb(kiss)] and [npc.verb(lick)] any exposed part of [npc2.namePos] [npc2.cock+] that [npc.she] can find."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Eagerly wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] rapidly bobbing [npc.her] head up and down as [npc.she] [npc.verb(give)] [npc2.herHim] an enthusiastic blowjob.",
						"With a muffled, [npc.moan+], [npc.name] quickly [npc.verb(start)] bobbing [npc.her] head up and down,"
								+ " greedily wrapping [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] head.",
						"Rapidly bobbing [npc.her] head up and down, [npc.name] desperately [npc.verb(wrap)] [npc.her] [npc.lips+] around [npc2.namePos] [npc2.cock+] as [npc.she] [npc.verb(give)] [npc2.herHim] an eager blowjob."));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop blowjob";
		}

		@Override
		public String getActionDescription() {
			return "Take [npc2.namePos] [npc2.cock+] out of your mouth and stop giving [npc2.herHim] a blowjob.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly forcing [npc2.namePos] [npc2.cock+] down [npc.her] throat one last time, [npc.name] then [npc.verb(pull)] [npc.her] head back, putting a quick end to [npc2.her] blowjob.",

							"Slamming [npc.her] [npc.face] into [npc2.namePos] groin, [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock+] deep down [npc.her] throat,"
									+ " before pulling completely back and letting [npc2.herHim] slip out of [npc.her] mouth."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Sliding [npc2.namePos] [npc2.cock+] out of [npc.her] mouth, [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(put)] an end to the blowjob.",

							"With [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] head back, sliding [npc2.namePos] [npc2.cock+] fully out of [npc.her] mouth."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+], struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to let [npc2.herHim] go.",

							" Tears stream down [npc2.namePos] cheeks as [npc2.she] tries to struggle out of [npc.namePos] grip, letting out [npc2.a_sob+] before begging for [npc.herHim] to let [npc2.herHim] go."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+], revealing [npc2.her] desire for [npc.name] to continue sucking [npc2.her] [npc2.cock+].",

							" [npc2.Name] [npc2.moansVerb] as [npc.name] withdraws from [npc2.her] groin, betraying [npc2.her] desire to feel [npc.her] [npc.lips+] wrapped around [npc2.her] [npc2.cock+] once more."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
