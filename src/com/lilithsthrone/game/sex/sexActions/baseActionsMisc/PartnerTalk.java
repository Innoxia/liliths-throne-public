package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
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
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
				return CorruptionLevel.ZERO_PURE;
			} else {
				return CorruptionLevel.ONE_VANILLA;
			}
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					default:
						return Main.sex.getCharacterPerformingAction().isFeminine()?"Horny moan":"Horny groan";
					case DOM_ROUGH:
						return "Rough growl";
					case SUB_RESISTING:
						return "Protesting cry";
				}
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
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
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
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
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Turning [npc.her] head to look back at [npc2.name], [npc.a_moan+] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] turns [npc.her] head to look back at [npc2.name], letting out [npc.a_moan+] before calling out, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Desperately trying to crawl away from [npc2.name], [npc.name] [npc.sobsVerb+] as [npc2.she] [npc2.verb(grab)] [npc.her] [npc.hips+] and pull [npc.herHim] back, ",
								"Trying to crawl away from [npc2.name], [npc.name] lets out [npc.a_sob+] as [npc2.she] [npc2.verb(hold)] [npc.herHim] firmly in place, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"Turning [npc.her] head to look back at [npc2.name], [npc.a_moan] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] turns [npc.her] head to look back at [npc2.name], letting out [npc.a_moan] before calling out, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				if(Main.sex.getCharacterPerformingAction().isTaur()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							return UtilText.returnStringAtRandom(
									"[npc.Name] softly [npc.verb(call)] out to [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
									"With [npc2.name] kneeling down beneath [npc.her] feral [npc.legRace]'s body, [npc.name] softly sighs, ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						case DOM_ROUGH:
							return UtilText.returnStringAtRandom(
									"[npc.Name] [npc.moansVerb+] out to [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
									"With [npc2.name] kneeling down beneath [npc.her] feral [npc.legRace]'s body, [npc.name] [npc.moansVerb+], ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						default: 
							return UtilText.returnStringAtRandom(
									"[npc.Name] [npc.moansVerb+] out to [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
									"With [npc2.name] kneeling down beneath [npc.her] feral [npc.legRace]'s body, [npc.name] [npc.moansVerb+], ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					}
				
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							return UtilText.returnStringAtRandom(
									"[npc.Name] looks down at [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
									"Looking down at [npc2.name], [npc.name] sighs, ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						case DOM_ROUGH:
							return UtilText.returnStringAtRandom(
									"[npc.Name] smirks down at [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
									"Smirking down at [npc2.name], [npc.name] [npc.moansVerb+], ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						default: 
							return UtilText.returnStringAtRandom(
									"[npc.Name] looks down at [npc2.name] as [npc2.she] [npc2.verb(kneel)] beneath [npc.herHim], ",
									"Looking down at [npc2.name], [npc.name] [npc.moansVerb], ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					}
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at [npc2.name] as [npc.she] [npc.moansVerb], ",
								"Looking up at [npc2.name], [npc.name] [npc.moansVerb], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at [npc2.name], before letting out [npc.a_sob+], ",
								"Looking up at [npc2.name], [npc.name] lets out [npc.a_sob+], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at [npc2.name] as [npc.she] speaks, ",
								"Looking up at [npc2.name], [npc.name] speaks, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], and speaks down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], [npc.name] speaks down to [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], and growls down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], [npc.name] growls down to [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], and [npc.moansVerb] down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] [npc2.verb(lie)] beneath [npc.herHim], [npc.name] [npc.moansVerb] down to [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] lets out a soft [npc.moan], ",
								"[npc.Name] gently sighs, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] lets out a rough growl before speaking out loud, ",
								"[npc.Name] makes a threatening growling noise before speaking, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"A desperate [npc.moan] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] lets out a desperate [npc.moan] before addressing [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"A protesting whine escapes from between [npc.namePos] [npc.lips+] as [npc.she] struggles against [npc2.name], ",
								"[npc.Name] lets out a distressed whining noise as [npc.she] tries to shuffle away from [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.A_moan] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] lets out [npc.a_moan] before addressing [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
				}
				
			} else {
				if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
				}
			}

			return null;
		}
	};
	
}
