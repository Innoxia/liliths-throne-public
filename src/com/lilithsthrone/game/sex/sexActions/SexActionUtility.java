package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PositioningMenu;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.3.2
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
			if(Main.sex.isMasturbation()) {
				return "You remain still, not making a move...";
			}
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING) {
				List<GameCharacter> characters = new ArrayList<>(Main.sex.getAllParticipants());
				characters.remove(Main.sex.getCharacterPerformingAction());
				if(characters.size()>=2) {
					return UtilText.parse(characters,
							UtilText.returnStringAtRandom(
							"You remain in position, watching [npc.name] and [npc2.name] have sex before you.",
							"Staying quite still, you watch [npc.name] and [npc2.name] having fun in front of you.",
							"You carry on watching [npc.name] and [npc2.name], while doing nothing yourself."));
				}
			}
			
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
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
			return true;
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case SUB_EAGER:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"You take a moment to focus on something other than [npc.name], calming yourself down in the process.",
							"Closing your [pc.eyes], you take a deep breath, calming yourself down and lowering your arousal.",
							"Taking a deep breath, you focus on calming yourself down a little.");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"Still weakly struggling against [npc.name], you try to calm yourself down a little, reminding yourself that this will all be over soon.",
							"Scrunching your [pc.eyes] shut, you try to take a deep breath, pretending that this isn't happening as you seek to calm yourself down.",
							Main.sex.isMasturbation()?"":"Taking a deep breath, you try to calm down a little, before continuing to struggle against [npc.name].");
				default:
					return Main.sex.isMasturbation()?"":"You try to focus on something other than the [npc.race] you're currently having sex with. By doing so, you manage to calm yourself down a little, reducing your arousal.";
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
			return "Do nothing";
		}

		@Override
		public String getActionDescription() {
			return "Decide to do nothing.";
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
			return Main.sex.getUsingItemText();
				
		}
	};
	
	public static final SexAction PARTNER_SELF_EQUIP_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private Value<AbstractClothing, String> getSexClothingBeingUsed() {
			return getSexClothingBeingUsed(Main.sex.getCharacterPerformingAction());
		}
		private Value<AbstractClothing, String> getSexClothingBeingUsed(GameCharacter performer) {
			return ((NPC)performer).getSexClothingToSelfEquip(Main.sex.getClothingSelfEquipInformation().getValue().getKey(), false);
		}
		@Override
		public boolean isQuickSexRequirementsMet(GameCharacter performer) {
			return !performer.isPlayer()
					&& Main.sex.isCanRemoveSelfClothing(performer);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexClothingBeingUsed()!=null
					&& Main.sex.isCanRemoveSelfClothing(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(getSexClothingBeingUsed()!=null) {
				return "Equip "+getSexClothingBeingUsed().getKey().getName();
			}
			return "Equip clothing";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexClothingBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString() {
			return "<p>"
						+ Main.sex.getCharacterPerformingAction().equipClothingFromInventory(getSexClothingBeingUsed().getKey(), true, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction())
					+ "</p>";
		}
	};

	public static final SexAction PARTNER_EQUIP_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private Value<AbstractClothing, String> getSexClothingBeingUsed() {
			return getSexClothingBeingUsed(Main.sex.getCharacterPerformingAction());
		}
		private Value<AbstractClothing, String> getSexClothingBeingUsed(GameCharacter performer) {
			return ((NPC) performer).getSexClothingToEquip(Main.sex.getClothingEquipInformation().getValue().getKey(), false);
		}
		@Override
		public boolean isQuickSexRequirementsMet(GameCharacter performer) {
			return !performer.isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(performer, null);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexClothingBeingUsed()!=null
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), getSexClothingBeingUsed().getKey());
		}
		@Override
		public String getActionTitle() {
			if(getSexClothingBeingUsed()!=null) {
				return "Equip "+getSexClothingBeingUsed().getKey().getName();
			}
			return "Equip clothing";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexClothingBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString() {
			return "<p>"
						+ Main.sex.getClothingEquipInformation().getValue().getKey().equipClothingFromInventory(getSexClothingBeingUsed().getKey(), true, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction())
					+ "</p>";
		}
	};
	
	public static final SexAction PARTNER_USE_ITEM = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private Value<AbstractItem, String> getSexItemBeingUsed() {
			return ((NPC) Main.sex.getCharacterPerformingAction()).getSexItemToUse(Main.sex.getItemUseInformation().getValue().getKey());
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexItemBeingUsed()!=null;
		}
		@Override
		public String getActionTitle() {
			if(getSexItemBeingUsed()!=null) {
				return "Use "+getSexItemBeingUsed().getKey().getName();
			}
			return "Use item";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexItemBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString(){
			GameCharacter target = Main.sex.getItemUseInformation().getValue().getKey();
			
			if(target.equals(Main.sex.getCharacterPerformingAction())) { // If self-use, their use description forms part of the getSexItemBeingUsed() description.
				Value<AbstractItem, String> itemBeingUsed = getSexItemBeingUsed();
				Main.sex.addItemUseDenial(Main.sex.getCharacterPerformingAction(), target, itemBeingUsed.getKey().getItemType()); // Don't use the same item more than once in a scene
				return Main.sex.getCharacterPerformingAction().useItem(itemBeingUsed.getKey(), target, false, true); // Append only effects
			}
			
			// If using on NPC, the target is responsible for accepting or not:
			if(!target.isPlayer()) {
				Value<Boolean, String> result = ((NPC)target).getItemUseEffects(getSexItemBeingUsed().getKey(), Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction(), target);
				
				if(!result.getKey()) { // Make sure that this character is tracked as having refused this item (so that it can be checked and not offered again in the NPC.getSexItemToUse() method).
					Main.sex.addItemUseDenial(Main.sex.getCharacterPerformingAction(), target, getSexItemBeingUsed().getKey().getItemType());
				}
				
				return result.getValue();
			}
			
			if(Main.sex.isForcingItemUse(Main.sex.getCharacterPerformingAction(), target)) { // If forced to use item, the use description forms part of the getSexItemBeingUsed() description.
				Main.sex.getCharacterPerformingAction().useItem(getSexItemBeingUsed().getKey(), target, false, true); // Append only effects
			}
			// If using on player, and not forced, the player handles refusing or not in their own SexAction, so return nothing.
			return "";
		}
	};
	
	public static final SexAction PLAYER_ACCEPT_ITEM_FROM_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getItemUseInformation()!=null;
		}
		@Override
		public String getActionTitle() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "Accept "+item.getName(false);
		}
		@Override
		public String getActionDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return Util.capitaliseSentence(item.getUseName())+" the "+item.getName(false)+" "+UtilText.parse(Main.sex.getItemUseInformation().getKey(), " [npc.name] is offering you.");
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			AbstractItem item = Main.sex.getItemUseInformation().getValue().getValue();
			return Main.sex.getItemUseInformation().getKey().useItem(item, Main.game.getPlayer(), false, false); // Append full use + effects
		}
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
		}
	};

	public static final SexAction PLAYER_REFUSE_ITEM_FROM_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getItemUseInformation()!=null;
		}
		@Override
		public String getActionTitle() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "Refuse "+item.getName(false);
		}
		@Override
		public String getActionDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "Refuse to "+item.getUseName()+" the "+item.getName(false)+" "+UtilText.parse(Main.sex.getItemUseInformation().getKey(), " [npc.name] is offering you.");
		}
		@Override
		public String getDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return UtilText.parse(Main.sex.getItemUseInformation().getKey(),
					"You refuse to take the "+item.getName(false)+" from [npc.name]."
					+ " Letting out a disappointed whine, [npc.she] puts "+(item.isPlural()?"them":"it")+" back into [npc.her] inventory...");
		}
		@Override
		public void applyEffects() {
			// Make sure that this character is tracked as having refused this item (so that it can be checked and not offered again in the NPC.getSexItemToUse() method):
			Main.sex.addItemUseDenial(Main.sex.getItemUseInformation().getKey(), Main.game.getPlayer(), Main.sex.getItemUseInformation().getValue().getValue().getItemType());
		}
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
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
			return Main.sex.getUnequipClothingText();
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
			return Main.sex.getDyeClothingText();
		}
	};
	
	public static final SexAction POSITION_SELECTION = new SexAction(
			SexActionType.POSITIONING_MENU,
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
			return "New Position";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "";
		}
		
		@Override
		public void applyEffects() {
			PositioningMenu.setNewSexManager();
			Main.sex.setSexStarted(true);
		}
	};
}
