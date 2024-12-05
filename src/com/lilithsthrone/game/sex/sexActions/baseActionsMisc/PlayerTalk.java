package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
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
public class PlayerTalk {
	
	public static final SexAction DIRTY_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
				return CorruptionLevel.ZERO_PURE;
			} else {
				return CorruptionLevel.ONE_VANILLA;
			}
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					default:
						return Main.sex.getCharacterPerformingAction().isFeminine()?"Horny moan":"Horny groan";
					case DOM_ROUGH:
						return "Rough growl";
					case SUB_RESISTING:
						return "Protesting cry";
				}
			}
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
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
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					default:
						return "As your mouth is blocked, you can't talk dirty to [npc2.name], but you <i>can</i> still make a horny "+(Main.sex.getCharacterPerformingAction().isFeminine()?"moan":"groan")+" for [npc2.herHim].";
					case DOM_ROUGH:
						return "As your mouth is blocked, you can't make any rough sexual comments to [npc2.name], but you <i>can</i> still growl at [npc2.herHim].";
					case SUB_RESISTING:
						return "As your mouth is blocked, you can't beg for [npc2.herHim] to stop using you, but you <i>can</i> still make protesting cries of discomfort.";
				}
			}
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				default:
					return "Talk dirty to [npc2.name].";
				case DOM_ROUGH:
					return "Talk rough to [npc2.name].";
				case SUB_RESISTING:
					return "Beg for [npc2.herHim] to stop using you.";
			}
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
			
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.ALL_FOURS)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Turning your head to look back at [npc2.name], [npc.a_moan+] escapes from between your [npc.lips+], ",
								"You turn your head to look back at [npc2.name], letting out [npc.a_moan+] before calling out, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Desperately trying to crawl away from [npc2.name], [npc2.she] grabs your [npc.hips] and pulls you back as you [npc.sob], ",
								"Trying to crawl away from [npc2.name], [npc2.she] holds you firmly in place as you let out [npc.a_sob+], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"Turning your head to look back at [npc2.name], [npc.a_moan] escapes from between your [npc.lips+], ",
								"You turn your head to look back at [npc2.name], letting out [npc.a_moan] before calling out, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You look down at [npc2.name] as [npc2.she] kneels beneath you, ",
								"Looking down at [npc2.name], you sigh, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You grin down at [npc2.name] as [npc2.she] kneels beneath you, ",
								"Looking down at [npc2.name], you growl, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"You look down at [npc2.name] as [npc2.she] kneels beneath you, ",
								"Looking down at [npc2.name], you [npc.moan], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"You glance up at [npc2.name] as you [npc.moan] up to [npc2.herHim], ",
								"Looking up at [npc2.name] standing above you, you [npc.moan] up to [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"You glance up at [npc2.name] before letting out [npc.a_sob+], ",
								"Looking up at [npc2.name] standing above you, you let out [npc.a_sob+], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"You glance up at [npc2.name] as you speak up to [npc2.herHim], ",
								"Looking up at [npc2.name] standing above you, you speak up to [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.SIXTY_NINE)) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You look back at [npc2.name] as [npc2.she] lies beneath you, and speak down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] lies beneath you, you speak down to [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You look back at [npc2.name] as [npc2.she] lies beneath you, and growl down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] lies beneath you, you growl down to [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"You look back at [npc2.name] as [npc2.she] lies beneath you, and [npc.moanVerb] down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] lies beneath you, you [npc.moanVerb] down to [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You let out a soft [npc.moan], ",
								"You gently sigh, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You let out a rough growl before speaking out loud, ",
								"You make a threatening growling noise before speaking, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"A desperate [npc.moan] escapes from between your [npc.lips+], ",
								"You let out a desperate [npc.moan] before addressing [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"A protesting whine escapes from between your [npc.lips+] as you struggle against [npc2.name], ",
								"You let out a distressed whining noise as you try to shuffle away from [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.A_moan] escapes from between your [npc.lips+], ",
								"You let out [npc.a_moan] before addressing [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
			
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
				}
			} else {
				if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
				}
			}
			
			return null;
		}
	};
	
	private static String getOfferResponse(boolean denied, SexPace sexPace, String areaDescription) {
		if (denied) {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name] frowns as you beg for [npc2.herHim] to use your "+areaDescription+", and firmly replies, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(I don't think so. Don't ask for that again.)]",
											"[npc2.speech(Don't tell me to use your "+areaDescription+", and don't ask me for that again.)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] frowns as you beg for [npc2.herHim] to use your "+areaDescription+", and with a menacing tone in [npc2.her] voice, [npc2.she] growls at you, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Don't try and tell me to use your "+areaDescription+"! Keep those suggestions to yourself next time!)]",
											"[npc2.speech(I'll use whatever part of you I feel like! Don't ask me for that again!)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name] frowns as you beg for [npc2.herHim] to use your "+areaDescription+", and with a menacing snarl, [npc2.she] snaps back, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Shut up, you filthy whore! I'm not listening to some slut who begs to have [npc.her( )] "+areaDescription+" used!)]",
											"[npc2.speech(I'll use whatever part of you I feel like, you fucking slut! Don't ask me for that again!)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name] frowns as you beg for [npc2.herHim] to use your "+areaDescription+", and with a disappointed whine, [npc2.she] replies, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(I don't really want to use your "+areaDescription+" though...)]",
											"[npc2.speech(But I don't want to use your "+areaDescription+"...)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name] lets out [npc.a_sob] as you beg for [npc2.herHim] to use your "+areaDescription+", and, struggling against you, [npc2.she] cries out, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Please, I don't want to! Let me go already!)]",
											"[npc2.speech(No! Get your "+areaDescription+" away from me!)]"));
					
			}
			
		} else {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name] grins as you beg for [npc2.herHim] to use your "+areaDescription+", before softly [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Well, if that's what you really want, I'll use your "+areaDescription+", sure...)]",
											"[npc2.speech(You want me to use your "+areaDescription+", hmm? Well, if you insist...)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] grins as you beg for [npc2.herHim] to use your "+areaDescription+", before [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Mmm, if that's what you want, I'll use your "+areaDescription+"!)]",
											"[npc2.speech(You want me to use your "+areaDescription+", huh?! That's fine with me!)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name] grins as you beg for [npc2.herHim] to use your "+areaDescription+", before snarling, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(What a filthy slut! Desperate to have your "+areaDescription+" fucked, huh?! Well, I don't mind going along with that!)]",
											"[npc2.speech(You dirty whore, begging to have your "+areaDescription+" used! I'll give you what you want, slut!)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name] grins as you beg for [npc2.herHim] to use your "+areaDescription+", before [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Mmm, yes, I'll use your "+areaDescription+"...)]",
											"[npc2.speech(Of course I'll use your "+areaDescription+"!)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name] lets out [npc.a_sob] as you beg for [npc2.herHim] to use your "+areaDescription+", and, struggling against you, [npc2.she] cries out, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Please, I don't want to! Let me go already!)]",
											"[npc2.speech(No! Get your "+areaDescription+" away from me!)]"));
					
			}
		}
		return "";
	}
	
	private static String getRequestResponse(boolean denied, SexPace sexPace, String areaDescription) {
		if (denied) {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name] frowns as you beg to use [npc2.her] "+areaDescription+", and firmly replies, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(I don't think so. Don't ask for that again.)]",
											"[npc2.speech(Don't ask me to use my "+areaDescription+", and don't ask me for that again.)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] frowns as you beg to use [npc2.her] "+areaDescription+", and with a menacing tone in [npc2.her] voice, [npc2.she] growls at you, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Don't try and tell me to use my "+areaDescription+"! Keep those suggestions to yourself next time!)]",
											"[npc2.speech(I'll use whatever parts I feel like! Don't ask me for that again!)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name] frowns as you beg to use [npc2.her] "+areaDescription+", and with a menacing snarl, [npc2.she] snaps back, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Shut up, you filthy whore! I'm not listening to some slut who begs to get some "+areaDescription+" action!)]",
											"[npc2.speech(I'll use whatever parts I feel like, you fucking slut! Don't ask me for that again!)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name] frowns as you beg to use [npc2.her] "+areaDescription+", and with a disappointed whine, [npc2.she] replies, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(I don't really want to use my "+areaDescription+" though...)]",
											"[npc2.speech(But I don't want to use my "+areaDescription+"...)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name] lets out [npc.a_sob] as you beg to use [npc2.her] "+areaDescription+", and, struggling against you, [npc2.she] cries out, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Please, I don't want to! Let me go already!)]",
											"[npc2.speech(No! Get your "+areaDescription+" away from me!)]"));
					
			}
			
		} else {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name] grins as you beg to use [npc2.her] "+areaDescription+", before softly [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Sure thing, I'll use my "+areaDescription+"!)]",
											"[npc2.speech(Of course I'll use my "+areaDescription+"! Come here...)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] grins as you beg to use [npc2.her] "+areaDescription+", before [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Sure thing, I'll use my "+areaDescription+"!)]",
											"[npc2.speech(Of course I'll use my "+areaDescription+"!)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name] grins as you beg to use [npc2.her] "+areaDescription+", before snarling, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(What a filthy slut! Desperate for a taste of my "+areaDescription+", huh?! Well, I don't mind going along with that!)]",
											"[npc2.speech(You dirty whore, begging for a taste of my "+areaDescription+"! I'll give you what you want, slut!)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name] grins as you beg to use [npc2.her] "+areaDescription+", before [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Sure thing, I'll use my "+areaDescription+"!)]",
											"[npc2.speech(Of course I'll use my "+areaDescription+"!)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name] lets out [npc.a_sob] as you beg to use [npc2.her] "+areaDescription+", and, struggling against you, [npc2.she] begs, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Please, I don't want to! Let me go already!)]",
											"[npc2.speech(No! Get your "+areaDescription+" away from me!)]"));
					
			}
		}
		return "";
	}
	
	public static final SexAction PLAYER_OFFER_VAGINAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Offer pussy";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Thrust your hips out in an attempt to entice [npc2.name] into using your pussy.";
			}
			return "Tell [npc2.name] that you'd like [npc2.herHim] to use your pussy.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to thrusting [npc.her] [npc.hips] out at [npc2.name] in an attempt to get [npc2.herHim] to use [npc.her] [npc.pussy+].",
								"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply making a suggestive move with [npc.her] [npc.hips] in order to entice [npc2.name] into using [npc.her] [npc.pussy+].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Please, use my pussy!)]",
										"[npc.speech(Use my little pussy, please!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"pussy"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_VAGINAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Request pussy";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Move closer to [npc2.namePos] pussy in an attempt to show [npc2.herHim] that that's what you want.";
			}
			return "Tell [npc2.name] that you'd like to use [npc2.her] pussy.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null))
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to trying to move closer to [npc2.namePos] [npc2.pussy+] in an attempt to show [npc2.herHim] that that's what [npc.sheIs] interested in.",
								"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply moving closer to [npc2.namePos] [npc2.pussy+] as [npc.she] [npc.verb(try)] to indicate that that's what [npc.she] [npc.verb(want)].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Please, I want to use your pussy!)]",
										"[npc.speech(I want to use your pussy, please!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"pussy"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null));
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_ANAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Offer ass";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Thrust your hips back in an attempt to entice [npc2.name] into using your ass.";
			}
			return "Tell [npc2.name] that you'd like [npc2.herHim] to use your ass.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to thrusting [npc.her] [npc.hips] back at [npc2.name] in an attempt to get [npc2.herHim] to use [npc.her] [npc.ass+].",
								"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply making a suggestive backwards move with [npc.her] [npc.hips] in order to entice [npc2.name] into using [npc.her] [npc.ass+].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Please, use my ass!)]",
										"[npc.speech(Use my ass, please!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"ass"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
					return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING, Fetish.FETISH_PURE_VIRGIN);
					
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
				}
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_ANAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Request anal";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Move closer to [npc2.namePos] ass in an attempt to show [npc2.herHim] that that's what you want.";
			}
			return "Tell [npc2.name] that you'd like to use [npc2.her] asshole.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null))
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to trying to move closer to [npc2.namePos] [npc2.ass+] in an attempt to show [npc2.herHim] that that's what [npc.sheIs] interested in.",
								"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply moving closer to [npc2.namePos] [npc2.ass+] as [npc.she] [npc.verb(try)] to indicate that that's what [npc.she] [npc.verb(want)].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Please, I want to use your ass!)]",
										"[npc.speech(I want to use your asshole, please!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"ass"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null));
			}
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_ORAL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Offer oral";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc2.name] that you'd like [npc2.herHim] to use your mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, use my mouth!)]",
									"[npc.speech(Use my mouth, please!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"mouth"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(
						new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH),
						new SexType(SexParticipantType.NORMAL, null, SexAreaPenetration.TONGUE)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_ORAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Request oral";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Move closer to [npc2.namePos] mouth in an attempt to show [npc2.herHim] that that's what you want.";
			}
			return "Tell [npc2.name] that you want to use [npc2.her] mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null))
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to trying to move closer to [npc2.namePos] [npc2.face] in an attempt to show [npc2.herHim] that [npc.sheIs] interested in using [npc2.her] mouth.",
								"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply moving closer to [npc2.namePos] [npc2.face] as [npc.she] [npc.verb(try)] to indicate that [npc.sheIs] [npc.verb(want)] to use [npc2.her] mouth.")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Please, I want you to use your mouth!)]",
										"[npc.speech(Use your mouth, please!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"mouth"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null),
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_NIPPLE = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Offer nipple-sex";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Thrust your chest out in an attempt to entice [npc2.name] into using your [npc.nipples+].";
			}
			return "Tell [npc2.name] that you'd like [npc2.herHim] to use your [npc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isBreastFuckableNipplePenetration()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to thrusting [npc.her] chest out at [npc2.name] in an attempt to get [npc2.herHim] to use [npc.her] [npc.nipples+].",
								"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply making a suggestive move with [npc.her] [npc.chest] in order to entice [npc2.name] into using [npc.her] [npc.nipples+].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Please, fuck my nipples!)]",
										"[npc.speech(Fuck my nipples, please!)]")
								+"<br/><br/>");
			}
									
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"[npc.nipples]"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_PAIZURI = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Offer paizuri";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Thrust your chest out in an attempt to entice [npc2.name] into fucking your [npc.breasts+].";
			}
			return "Tell [npc2.name] that you'd like [npc2.herHim] to fuck your [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.BREASTS, true)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to thrusting [npc.her] chest out at [npc2.name] in an attempt to get [npc2.herHim] to use [npc.her] [npc.breasts+].",
								"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply making a suggestive move with [npc.her] [npc.chest] in order to entice [npc2.name] into using [npc.her] [npc.breasts+].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Please, fuck my tits!)]",
										"[npc.speech(Fuck my breasts, please!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"breasts"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_NAIZURI = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Offer naizuri";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Thrust your chest out in an attempt to entice [npc2.name] into grinding [npc2.her] [npc2.cock] up against your chest.";
			}
			return "Tell [npc2.name] that you'd like [npc2.herHim] to grind [npc2.her] [npc2.cock] up against your chest.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.BREASTS, true)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(resort)] to thrusting [npc.her] chest out at [npc2.name] in an attempt to get [npc2.herHim] to use it.",
								"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on simply making a suggestive move with [npc.her] [npc.chest] in order to entice [npc2.name] into using it.")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Please, grind your cock up against my chest!)]",
										"[npc.speech(Grind on my chest, please!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"chest"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
}
