package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.1.88
 * @author Innoxia
 */
public class PlayerTalk {
	
	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
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
					return "Talk dirty to [npc.name].";
				case DOM_ROUGH:
					return "Talk rough to [npc.name].";
				case SUB_RESISTING:
					return "Beg for [npc.herHim] to stop using you.";
			}
			return "Talk dirty to [npc.name].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Turning your head to look back at [npc.name], [pc.a_moan+] escapes from between your [pc.lips+], ",
								"You turn your head to look back at [npc.name], letting out [pc.a_moan+] before calling out, ")
								+ Main.game.getPlayer().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Desperately trying to crawl away from [npc.name], [npc.she] grabs your [pc.hips] and pulls you back as you [pc.sob], ",
								"Trying to crawl away from [npc.name], [npc.she] holds you firmly in place as you let out [pc.a_sob+], ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"Turning your head to look back at [npc.name], [pc.a_moan] escapes from between your [pc.lips+], ",
								"You turn your head to look back at [npc.name], letting out [pc.a_moan] before calling out, ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You look down at [npc.name] as [npc.she] kneels beneath you, ",
								"Looking down at [npc.name], you sigh, ")
								+ Main.game.getPlayer().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You grin down at [npc.name] as [npc.she] kneels beneath you, ",
								"Looking down at [npc.name], you growl, ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"You look down at [npc.name] as [npc.she] kneels beneath you, ",
								"Looking down at [npc.name], you [pc.moan], ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"You glance up at [npc.name] as you [pc.moan] up to [npc.herHim], ",
								"Looking up at [npc.name] standing above you, you [pc.moan] up to [npc.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"You glance up at [npc.name] before letting out [pc.a_sob+], ",
								"Looking up at [npc.name] standing above you, you let out [pc.a_sob+], ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"You glance up at [npc.name] as you speak up to [npc.herHim], ",
								"Looking up at [npc.name] standing above you, you speak up to [npc.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_TOP) {
				
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You look back at [npc.name] as [npc.she] lies beneath you, and speak down to [npc.herHim], ",
								"Looking back at [npc.name] as [npc.she] lies beneath you, you speak down to [npc.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You look back at [npc.name] as [npc.she] lies beneath you, and growl down to [npc.herHim], ",
								"Looking back at [npc.name] as [npc.she] lies beneath you, you growl down to [npc.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: 
						return UtilText.returnStringAtRandom(
								"You look back at [npc.name] as [npc.she] lies beneath you, and [pc.moanVerb] down to [npc.herHim], ",
								"Looking back at [npc.name] as [npc.she] lies beneath you, you [pc.moanVerb] down to [npc.herHim], ")
								+ Main.game.getPlayer().getDirtyTalk();
				}
				
			} else {
			
				switch(Sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"You let out a soft [pc.moan], ",
								"You gently sigh, ")
								+ Main.game.getPlayer().getDirtyTalk();
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"You let out a rough growl before speaking out loud, ",
								"You make a threatening growling noise before speaking, ")
								+ Main.game.getPlayer().getDirtyTalk();
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"A desperate [pc.moan] escapes from between your [pc.lips+], ",
								"You let out a desperate [pc.moan] before addressing [npc.name], ")
								+ Main.game.getPlayer().getDirtyTalk();
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"A protesting whine escapes from between your [pc.lips+] as you struggle against [npc.name], ",
								"You let out a distressed whining noise as you try to shuffle away from [npc.name], ")
								+ Main.game.getPlayer().getDirtyTalk();
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[pc.A_moan] escapes from between your [pc.lips+], ",
								"You let out [pc.a_moan] before addressing [npc.name], ")
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
	
	public static final SexAction PLAYER_REQUEST_VAGINAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Request vaginal";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc.name] that you'd like [npc.herHim] to use your pussy.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasVagina()
					&& !Sex.getPlayerPenetrationRequests().contains(SexAreaOrifice.VAGINA)
					&& !SexFlags.requestsBlockedPlayer
					&& !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			return "Putting on the most pleading expression you can muster, you cry out to [npc.name], "
							+UtilText.returnStringAtRandom(
									"[pc.speech(Please, use my pussy!)]",
									"[pc.speech(Use my little pussy, please!)]");
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			Sex.addPlayerPenetrationRequest(SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction PLAYER_REQUEST_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Request anal";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc.name] that you'd like [npc.herHim] to use your ass.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPlayerPenetrationRequests().contains(SexAreaOrifice.ANUS)
					&& !SexFlags.requestsBlockedPlayer
					&& !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			return "Putting on the most pleading expression you can muster, you cry out to [npc.name], "
							+UtilText.returnStringAtRandom(
									"[pc.speech(Please, use my ass!)]",
									"[pc.speech(Use my ass, please!)]");
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			Sex.addPlayerPenetrationRequest(SexAreaOrifice.ANUS);
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
	
	public static final SexAction PLAYER_REQUEST_ORAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Request oral";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc.name] that you'd like [npc.herHim] to use your mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPlayerPenetrationRequests().contains(SexAreaOrifice.MOUTH)
					&& !SexFlags.requestsBlockedPlayer
					&& !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			return "Putting on the most pleading expression you can muster, you cry out to [npc.name], "
							+UtilText.returnStringAtRandom(
									"[pc.speech(Please, use my mouth!)]",
									"[pc.speech(Use my mouth, please!)]");
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			Sex.addPlayerPenetrationRequest(SexAreaOrifice.MOUTH);
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
	
	public static final SexAction PLAYER_REQUEST_NIPPLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Request nipple-sex";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc.name] that you'd like [npc.herHim] to use your [pc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPlayerPenetrationRequests().contains(SexAreaOrifice.NIPPLE)
					&& Main.game.getPlayer().isBreastFuckableNipplePenetration()
					&& !SexFlags.requestsBlockedPlayer
					&& !Sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			return "Putting on the most pleading expression you can muster, you cry out to [npc.name], "
							+UtilText.returnStringAtRandom(
									"[pc.speech(Please, fuck my nipples!)]",
									"[pc.speech(Fuck my nipples, please!)]");
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			Sex.addPlayerPenetrationRequest(SexAreaOrifice.NIPPLE);
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
	
	public static final SexAction PLAYER_REQUEST_PAIZURI = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Request paizuri";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc.name] that you'd like [npc.herHim] to fuck your [pc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPlayerPenetrationRequests().contains(SexAreaOrifice.BREAST)
					&& Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !SexFlags.requestsBlockedPlayer
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getActivePartner().hasPenis()
					&& Sex.getActivePartner().getPlayerKnowsAreas().contains(CoverableArea.PENIS);
		}

		@Override
		public String getDescription() {
			return "Putting on the most pleading expression you can muster, you cry out to [npc.name], "
							+UtilText.returnStringAtRandom(
									"[pc.speech(Please, fuck my tits!)]",
									"[pc.speech(Fuck my breasts, please!)]");
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			Sex.addPlayerPenetrationRequest(SexAreaOrifice.BREAST);
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
	
	public static final SexAction PLAYER_REQUEST_NAIZURI = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.MISC) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Request naizuri";
		}

		@Override
		public String getActionDescription() {
			return "Tell [npc.name] that you'd like [npc.herHim] to grind [npc.her] [npc.cock] up against your chest.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getPlayerPenetrationRequests().contains(SexAreaOrifice.BREAST)
					&& !Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !SexFlags.requestsBlockedPlayer
					&& !Sex.isDom(Main.game.getPlayer())
					&& Sex.getActivePartner().hasPenis()
					&& Sex.getActivePartner().getPlayerKnowsAreas().contains(CoverableArea.PENIS);
		}

		@Override
		public String getDescription() {
			return "Putting on the most pleading expression you can muster, you cry out to [npc.name], "
							+UtilText.returnStringAtRandom(
									"[pc.speech(Please, grind your cock up against my chest!)]",
									"[pc.speech(Grind on my chest, please!)]");
		}

		@Override
		public void applyEffects() {
			Sex.clearPlayerPenetrationRequests();
			Sex.addPlayerPenetrationRequest(SexAreaOrifice.BREAST);
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
