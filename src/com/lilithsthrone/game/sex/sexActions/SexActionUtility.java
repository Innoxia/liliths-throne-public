package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public class SexActionUtility {

	// GENERIC:
	
	public static final SexAction PLAYER_NONE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Do nothing";
		}

		@Override
		public String getActionDescription() {
			return "Don't make a move.";
		}
		
		@Override
		public String getDescription() {
			if(Sex.isMasturbation()) {
				return "You remain still, not making a move...";
			}
			
			if(Sex.getSexPositionSlot(Sex.getCharacterPerformingAction())==SexPositionSlot.MISC_WATCHING) {
				List<GameCharacter> characters = new ArrayList<>(Sex.getAllParticipants());
				characters.remove(Sex.getCharacterPerformingAction());
				if(characters.size()>=2) {
					return UtilText.parse(characters,
							UtilText.returnStringAtRandom(
							"You remain in position, watching [npc.name] and [npc2.name] have sex before you.",
							"Staying quite still, you watch [npc.name] and [npc2.name] having fun in front of you.",
							"You carry on watching [npc.name] and [npc2.name], while doing nothing yourself."));
				}
			}
			
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							"You remain in position, gently pressing yourself against [npc.name], but not making any sort of move on [npc.herHim].",
							"Staying quite still, you press yourself up against [npc.name], waiting for [npc.herHim] to make the next move.",
							"You gently press yourself against [npc.name], content to let [npc.herHim] make the next move.");
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							"You remain in position, pressing yourself against [npc.name], but not making any sort of move on [npc.herHim].",
							"Staying quite still, you press yourself up against [npc.name], waiting for [npc.herHim] to make the next move.",
							"You press yourself against [npc.name], content to let [npc.herHim] make the next move.");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							"You remain in position, grinding yourself against [npc.name], but not making any sort of move on [npc.herHim].",
							"Staying quite still, you grind yourself up against [npc.name], waiting for [npc.herHim] to make the next move.",
							"You grind yourself against [npc.name], content to let [npc.herHim] make the next move.");
				case SUB_EAGER:
					return UtilText.returnStringAtRandom(
							"You remain in position, pressing yourself against [npc.name], but not making any sort of move on [npc.herHim].",
							"Staying quite still, you press yourself up against [npc.name], waiting for [npc.herHim] to make the next move.",
							"You press yourself against [npc.name], content to let [npc.herHim] make the next move.");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							"You remain in position, pressing yourself against [npc.name], but not making any sort of move on [npc.herHim].",
							"Staying quite still, you press yourself up against [npc.name], waiting for [npc.herHim] to make the next move.",
							"You press yourself against [npc.name], content to let [npc.herHim] make the next move.");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							"You continue struggling against [npc.name], refusing to make any sort of move on [npc.herHim].",
							"Struggling and [pc.sobbing], you try to wriggle out of [npc.namePos] grasp, dreading what [npc.her] next move might be.",
							"You try to push [npc.name] away from you, [pc.sobbing] and struggling in distress as you refuse to submit.");
			}

			return "You remain in position, content to simply wait and see what [npc.name] does next.";
		}
	};
	
	public static final SexAction PLAYER_CALM_DOWN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.NEGATIVE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Calm down";
		}

		@Override
		public String getActionDescription() {
			return "Focus on calming yourself down, which lowers your arousal.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;//Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl();
		}
		
		@Override
		public String getDescription() {
			switch(Sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							Sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							Sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							Sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case SUB_EAGER:
					return UtilText.returnStringAtRandom(
							Sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							Sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							Sex.isMasturbation()?"":"Still weakly struggling against [npc.name], you try to calm yourself down a little, reminding yourself that this will all be over soon.",
							"Scrunching your [pc.eyes] shut, you try to take a deep breath, pretending that this isn't happening as you seek to calm yourself down.",
							Sex.isMasturbation()?"":"Taking a deep breath, you try to calm down a little, before continuing to struggle against [npc.name].");
				default:
					return Sex.isMasturbation()?"":"You try to focus on something other than the [npc.race] you're currently having sex with. By doing so, you manage to calm yourself down a little, reducing your arousal.";
			}
		}
	};
	
	public static final SexAction PARTNER_NONE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return null;
		}

		@Override
		public String getDescription() {
			return "[npc.Name] doesn't make a move.";
		}
	};
	
	public static final SexAction PARTNER_ORGASM_SKIP = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public String getDescription() {
			return "[npc.Name] lets out [npc.a_moan+].";
		}
	};

	public static final SexAction PLAYER_USE_ITEM = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Use item";
		}

		@Override
		public String getActionDescription() {
			return "See what items you could use.";
		}
		
		@Override
		public String getDescription() {
			return Sex.getUsingItemText();
				
		}
	};

	public static final SexAction CLOTHING_REMOVAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Manage clothing";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "<i style='color:" + Colour.DISPLACED.toWebHexString() + ";'>Clothing removal</i> - "+Sex.getUnequipClothingText();
		}
	};
	
	public static final SexAction CLOTHING_DYE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Manage clothing";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return Sex.getDyeClothingText();
		}
	};
}
