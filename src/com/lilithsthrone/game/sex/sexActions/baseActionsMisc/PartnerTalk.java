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
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.2.8
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
			switch(Sex.getSexPace(Sex.getActivePartner())) {
				case DOM_GENTLE:
					return "Dirty talk";
				case DOM_NORMAL:
					return "Dirty talk";
				case DOM_ROUGH:
					return "Rough talk";
				case SUB_EAGER:
					return "Dirty talk";
				case SUB_NORMAL:
					return "Dirty talk";
				case SUB_RESISTING:
					return "Beg for [npc2.herHim] to stop";
				default:
					return "Dirty talk";
			}
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Turning [npc.her] head to look back at you, [npc.a_moan+] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] turns [npc.her] head to look back at you, letting out [npc.a_moan+] before calling out, ")
								+ Sex.getActivePartner().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Desperately trying to crawl away from you, [npc.name] [npc.sobsVerb+] as you grab [npc.her] [npc.hips+] and pull [npc.herHim] back, ",
								"Trying to crawl away from you, [npc.name] lets out [npc.a_sob+] as you hold [npc.herHim] firmly in place, ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"Turning [npc.her] head to look back at you, [npc.a_moan] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] turns [npc.her] head to look back at you, letting out [npc.a_moan] before calling out, ")
								+ Sex.getActivePartner().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks down at you as you kneel beneath [npc.herHim], ",
								"Looking down at you, [npc.name] sighs, ")
								+ Sex.getActivePartner().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] smirks down at you as you kneel beneath [npc.herHim], ",
								"Smirking down at you, [npc.name] sighs, ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks down at you as you kneel beneath [npc.herHim], ",
								"Looking down at you, [npc.name] [npc.moansVerb], ")
								+ Sex.getActivePartner().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at you as [npc.she] [npc.moansVerb], ",
								"Looking up at you, [npc.name] [npc.moansVerb], ")
								+ Sex.getActivePartner().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at you, before letting out [npc.a_sob+], ",
								"Looking up at you, [npc.name] lets out [npc.a_sob+], ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] glances up at you as [npc.she] speaks, ",
								"Looking up at you, [npc.name] speaks, ")
								+ Sex.getActivePartner().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				
				switch(Sex.getSexPace(Sex.getActivePartner())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at you as you lie beneath [npc.herHim], and speaks down to you, ",
								"Looking back at you as you lie beneath [npc.herHim], [npc.name] speaks down to you, ")
								+ Sex.getActivePartner().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at you as you lie beneath [npc.herHim], and growls down to you, ",
								"Looking back at you as you lie beneath [npc.herHim], [npc.name] growls down to you, ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] looks back at you as you lie beneath [npc.herHim], and [npc.moansVerb] down to you, ",
								"Looking back at you as you lie beneath [npc.herHim], [npc.name] [npc.moansVerb] down to you, ")
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
								"[npc.Name] lets out a desperate [npc.moan] before addressing you, ")
								+ Sex.getActivePartner().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"A protesting whine escapes from between [npc.namePos] [npc.lips+] as [npc.she] struggles against you, ",
								"[npc.Name] lets out a distressed whining noise as [npc.she] tries to shuffle away from you, ")
								+ Sex.getActivePartner().getDirtyTalk();
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.A_moan] escapes from between [npc.namePos] [npc.lips+], ",
								"[npc.Name] lets out [npc.a_moan] before addressing you, ")
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
