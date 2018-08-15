package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.2.9
 * @author Innoxia
 */
public class PlayerTalk {
	
	public static final SexAction DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
				return CorruptionLevel.ZERO_PURE;
			} else {
				return CorruptionLevel.ONE_VANILLA;
			}
		}
		
		@Override
		public String getActionTitle() {
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					return "Dirty talk";
				case DOM_ROUGH:
					return "Rough talk";
				case SUB_RESISTING:
					return "Beg to stop";
			}
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
				case SUB_EAGER:
				case SUB_NORMAL:
					return "Talk dirty to [npc2.name].";
				case DOM_ROUGH:
					return "Talk rough to [npc2.name].";
				case SUB_RESISTING:
					return "Beg for [npc2.herHim] to stop using you.";
			}
			return "Talk dirty to [npc2.name].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Turning your head to look back at [npc2.name], [npc.a_moan+] escapes from between your [npc.lips+], ",
								"You turn your head to look back at [npc2.name], letting out [npc.a_moan+] before calling out, ")
								+ Main.game.getPlayer().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Desperately trying to crawl away from [npc2.name], [npc2.she] grabs your [npc.hips] and pulls you back as you [npc.sob], ",
								"Trying to crawl away from [npc2.name], [npc2.she] holds you firmly in place as you let out [npc.a_sob+], ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"Turning your head to look back at [npc2.name], [npc.a_moan] escapes from between your [npc.lips+], ",
								"You turn your head to look back at [npc2.name], letting out [npc.a_moan] before calling out, ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You look down at [npc2.name] as [npc2.she] kneels beneath you, ",
								"Looking down at [npc2.name], you sigh, ")
								+ Main.game.getPlayer().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You grin down at [npc2.name] as [npc2.she] kneels beneath you, ",
								"Looking down at [npc2.name], you growl, ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"You look down at [npc2.name] as [npc2.she] kneels beneath you, ",
								"Looking down at [npc2.name], you [npc.moan], ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"You glance up at [npc2.name] as you [npc.moan] up to [npc2.herHim], ",
								"Looking up at [npc2.name] standing above you, you [npc.moan] up to [npc2.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"You glance up at [npc2.name] before letting out [npc.a_sob+], ",
								"Looking up at [npc2.name] standing above you, you let out [npc.a_sob+], ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"You glance up at [npc2.name] as you speak up to [npc2.herHim], ",
								"Looking up at [npc2.name] standing above you, you speak up to [npc2.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You look back at [npc2.name] as [npc2.she] lies beneath you, and speak down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] lies beneath you, you speak down to [npc2.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You look back at [npc2.name] as [npc2.she] lies beneath you, and growl down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] lies beneath you, you growl down to [npc2.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"You look back at [npc2.name] as [npc2.she] lies beneath you, and [npc.moanVerb] down to [npc2.herHim], ",
								"Looking back at [npc2.name] as [npc2.she] lies beneath you, you [npc.moanVerb] down to [npc2.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
				
			} else {
			
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You let out a soft [npc.moan], ",
								"You gently sigh, ")
								+ Main.game.getPlayer().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You let out a rough growl before speaking out loud, ",
								"You make a threatening growling noise before speaking, ")
								+ Main.game.getPlayer().getDirtyTalk();
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"A desperate [npc.moan] escapes from between your [npc.lips+], ",
								"You let out a desperate [npc.moan] before addressing [npc2.name], ")
								+ Main.game.getPlayer().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"A protesting whine escapes from between your [npc.lips+] as you struggle against [npc2.name], ",
								"You let out a distressed whining noise as you try to shuffle away from [npc2.name], ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.A_moan] escapes from between your [npc.lips+], ",
								"You let out [npc.a_moan] before addressing [npc2.name], ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
			
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
				}
			} else {
				if(Sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
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
											"[npc2.speech(Sure thing, I'll use your "+areaDescription+"!)]",
											"[npc2.speech(Of course I'll use your "+areaDescription+"! Come here...)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] grins as you beg for [npc2.herHim] to use your "+areaDescription+", before [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Sure thing, I'll use your "+areaDescription+"!)]",
											"[npc2.speech(Of course I'll use your "+areaDescription+"!)]"));
					
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
											"[npc2.speech(Sure thing, I'll use your "+areaDescription+"!)]",
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
			SexActionType.ONGOING,
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
			return "Tell [npc2.name] that you'd like [npc2.herHim] to use your pussy.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA))
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, use my pussy!)]",
									"[npc.speech(Use my little pussy, please!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"pussy"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_VAGINAL = new SexAction(
			SexActionType.ONGOING,
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
			return "Tell [npc2.name] that you'd like to use [npc2.her] pussy.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
					&& Sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null))
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, I want to use your pussy!)]",
									"[npc.speech(I want to use your pussy, please!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"pussy"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null));
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_ANAL = new SexAction(
			SexActionType.ONGOING,
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
			return "Tell [npc2.name] that you'd like [npc2.herHim] to use your ass.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, use my ass!)]",
									"[npc.speech(Use my ass, please!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"ass"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
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
			SexActionType.ONGOING,
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
			return "Tell [npc2.name] that you'd like to use [npc2.her] asshole.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null))
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, I want to use your ass!)]",
									"[npc.speech(I want to use your asshole, please!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"ass"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null));
			}
		}

		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_ORAL = new SexAction(
			SexActionType.ONGOING,
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
			return !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& Sex.getCharacterPerformingAction().isPlayer();
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
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"mouth"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH));
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_ORAL = new SexAction(
			SexActionType.ONGOING,
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
			return "Tell [npc2.name] that you want to use [npc2.her] mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null))
					&& Sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, I want you to use your mouth!)]",
									"[npc.speech(Use your mouth, please!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"mouth"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null));
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_NIPPLE = new SexAction(
			SexActionType.ONGOING,
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
			return "Tell [npc2.name] that you'd like [npc2.herHim] to use your [npc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isBreastFuckableNipplePenetration()
					&& !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, fuck my nipples!)]",
									"[npc.speech(Fuck my nipples, please!)]")
							+"<br/><br/>");
									
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"[npc.nipples]"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE));
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_PAIZURI = new SexAction(
			SexActionType.ONGOING,
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
			return "Tell [npc2.name] that you'd like [npc2.herHim] to fuck your [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.BREASTS, true)
					&& Sex.getActivePartner().hasPenis()
					&& Sex.getActivePartner().isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, fuck my tits!)]",
									"[npc.speech(Fuck my breasts, please!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"breasts"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_NAIZURI = new SexAction(
			SexActionType.ONGOING,
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
			return "Tell [npc2.name] that you'd like [npc2.herHim] to grind [npc2.her] [npc2.cock] up against your chest.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !Sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.BREASTS, true)
					&& Sex.getActivePartner().hasPenis()
					&& Sex.getActivePartner().isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Putting on the most pleading expression you can muster, you cry out to [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Please, grind your cock up against my chest!)]",
									"[npc.speech(Grind on my chest, please!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)),
					Sex.getSexPace(Sex.getCharacterTargetedForSexAction(this)),
					"chest"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))) {
				((NPC)Sex.getCharacterTargetedForSexAction(this)).generateSexChoices(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
				
			} else if(!Sex.isDom(Main.game.getPlayer())) {
				Sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
}
