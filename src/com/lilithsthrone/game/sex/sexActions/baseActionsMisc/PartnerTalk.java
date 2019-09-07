package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.3.4.5
 * @author Innoxia
 */
public class PartnerTalk {
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING) {
				return CorruptionLevel.ZERO_PURE;
			} else {
				return CorruptionLevel.ONE_VANILLA;
			}
		}
		
		@Override
		public String getActionTitle() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					default:
						return Sex.getCharacterPerformingAction().isFeminine()?"Horny moan":"Horny groan";
					case DOM_ROUGH:
						return "Rough growl";
					case SUB_RESISTING:
						return "Protesting cry";
				}
			}
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				default:
					return "Dirty talk";
				case DOM_ROUGH:
					return "Rough talk";
				case SUB_RESISTING:
					return "Beg to stop";
			}
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			if(Sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Sex.getSexPace(Sex.getCharacterPerformingAction())) {
					default:
						return UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, all [npc.she] can do in place of talking dirty to [npc2.name] is let out a series of horny, muffled [npc.moans].",
								"With [npc.her] mouth being currently blocked, [npc.nameIsFull] only able to make a series of muffled, lewd [npc.moans] to let [npc2.name] know that [npc.sheIs] enjoying [npc.herself].",
								"Wanting to let [npc2.name] know that [npc.sheIs] enjoying [npc.herself], but having [npc.her] mouth blocked, [npc.name] [npc.verb(make)] do with letting out a series of incredibly lewd, muffled [npc.moans].",
								"Although [npc.her] mouth is blocked, [npc.nameIsFull] still able to let out a series of horny, muffled [npc.moans], letting [npc2.name] know that [npc.sheIs] enjoying [npc.herself].");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, all [npc.she] can do in place of talking rough to [npc2.name] is let out a series of aggressive, muffled growls.",
								"With [npc.her] mouth being currently blocked, [npc.nameIsFull] only able to make a series of muffled, rough growls to let [npc2.name] know that [npc.sheIs] still firmly in charge.",
								"Wanting to let [npc2.name] know that [npc.sheIs] still in charge, but having [npc.her] mouth blocked, [npc.name] [npc.verb(make)] do with letting out a series of menacing, muffled growls.",
								"Although [npc.her] mouth is blocked, [npc.nameIsFull] still able to let out a series of deep, muffled growls, letting [npc2.name] know that [npc.sheIs] still in charge.");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, all [npc.she] can do in place of begging for mercy is to let out a series of pathetic whines and muffled cries.",
								"With [npc.her] mouth being currently blocked, [npc.nameIsFull] only able to make a series of muffled sobs and distressed whines to let [npc2.name] know that [npc.she] [npc.verb(want)] to be let go.",
								"Wanting to let [npc2.name] know that [npc.she] desires to be released, but having [npc.her] mouth blocked, [npc.name] [npc.verb(make)] do with letting out a series of distressed sobs and muffled cries.",
								"Although [npc.her] mouth is blocked, [npc.nameIsFull] still able to let out a series of distressed whines and muffled sobs, letting [npc2.name] know that [npc.she] [npc.verb(want)] this to stop.");
				}
			}
			
			if(Sex.getSexPositionSlot(Sex.getActivePartner()).hasTag(SexSlotTag.ALL_FOURS)) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Turning [npc.her] head to look back at [npc2.name], [npc.a_moan+] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] turns [npc.her] head to look back at [npc2.name], letting out [npc.a_moan+] before calling out, ")
								+ Sex.getActivePartner().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Desperately trying to crawl away from [npc2.name], [npc.name] [npc.sobsVerb+] as [npc2.she] [npc2.verb(grab)] [npc.her] [npc.hips+] and pull [npc.herHim] back, ",
								"Trying to crawl away from [npc2.name], [npc.name] lets out [npc.a_sob+] as [npc2.she] [npc2.verb(hold)] [npc.herHim] firmly in place, ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"Turning [npc.her] head to look back at [npc2.name], [npc.a_moan] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] turns [npc.her] head to look back at [npc2.name], letting out [npc.a_moan] before calling out, ")
								+ Sex.getActivePartner().getDirtyTalk();
				}
				
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks down at [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
								"Looking down at [npc2.name], [npc.name] sighs, ")
								+ Sex.getActivePartner().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] smirks down at [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
								"Smirking down at [npc2.name], [npc.name] sighs, ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks down at [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
								"Looking down at [npc2.name], [npc.name] [npc.moansVerb], ")
								+ Sex.getActivePartner().getDirtyTalk();
				}
				
			} else if(Sex.getSexPositionSlot(Sex.getActivePartner()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at [npc2.name] as [npc.she] [npc.moansVerb], ",
								"Looking up at [npc2.name], [npc.name] [npc.moansVerb], ")
								+ Sex.getActivePartner().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at [npc2.name], before letting out [npc.a_sob+], ",
								"Looking up at [npc2.name], [npc.name] lets out [npc.a_sob+], ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at [npc2.name] as [npc.she] speaks, ",
								"Looking up at [npc2.name], [npc.name] speaks, ")
								+ Sex.getActivePartner().getDirtyTalk();
				}
				
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.LYING_DOWN)) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], and speaks down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], [npc.name] speaks down to [npc2.herHim], ")
								+ Sex.getActivePartner().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], and growls down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], [npc.name] growls down to [npc2.herHim], ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], and [npc.moansVerb] down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], [npc.name] [npc.moansVerb] down to [npc2.herHim], ")
								+ Sex.getActivePartner().getDirtyTalk();
				}
				
			} else {
			
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] lets out a soft [npc.moan], ",
								"[npc.Name] gently sighs, ")
								+ Sex.getActivePartner().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] lets out a rough growl before speaking out loud, ",
								"[npc.Name] makes a threatening growling noise before speaking, ")
								+ Sex.getActivePartner().getDirtyTalk();
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"A desperate [npc.moan] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] lets out a desperate [npc.moan] before addressing [npc2.name], ")
								+ Sex.getActivePartner().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"A protesting whine escapes from between [npc.namePos] [npc.lips+] as [npc.she] struggles against [npc2.name], ",
								"[npc.Name] lets out a distressed whining noise as [npc.she] tries to shuffle away from [npc2.name], ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.A_moan] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] lets out [npc.a_moan] before addressing [npc2.name], ")
								+ Sex.getActivePartner().getDirtyTalk();
				}
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
				}
				
			} else {
				if(Sex.getSexPace(Sex.getActivePartner())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
				}
			}

			return null;
		}
	};
	
}
