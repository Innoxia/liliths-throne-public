package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.8
 * @version 0.3.9
 * @author Innoxia
 */
public class MilkingStall {

	public static final SexAction ATTACH_PUMPS = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private List<InventorySlot> getPumpSlots() {
			List<InventorySlot> list = new ArrayList<>();
			GameCharacter milker = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(MilkingRoom.getActualMilkPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_MILK)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.NIPPLE, null)) {
				if(milker.getClothingInSlot(InventorySlot.NIPPLE)==null) {
					list.add(InventorySlot.NIPPLE);
				}
			}
			if(MilkingRoom.getActualCrotchMilkPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_MILK_CROTCH)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.STOMACH, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.STOMACH, null)) {
				if(milker.getClothingInSlot(InventorySlot.STOMACH)==null) {
					list.add(InventorySlot.STOMACH);
				}
			}
			if(MilkingRoom.getActualCumPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_CUM)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.PENIS, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.PENIS, null)) {
				if(milker.getClothingInSlot(InventorySlot.PENIS)==null) {
					list.add(InventorySlot.PENIS);
				}
			}
			if(MilkingRoom.getActualGirlcumPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_GIRLCUM)
					&& (!milker.hasHymen() || milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_TEAR_HYMEN))
					&& milker.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.VAGINA, null)) {
				if(milker.getClothingInSlot(InventorySlot.VAGINA)==null) {
					list.add(InventorySlot.VAGINA);
				}
			}
			
			return list;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.MILK;
		}
		@Override
		public boolean isDisplayedAsUnavailable() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Attach pumps";
		}
		@Override
		public String getActionDescription() {
			List<InventorySlot> pumps = getPumpSlots();
			if(pumps.isEmpty()) {
				//"[npc2.Name] doesn't have any accessible areas for milking pumps to be attached to!"
				return "<i>[style.italicsBad(Requires:)] One of the following slots to be freely available: 'nipple', 'penis', 'vagina', or 'stomach' (for udder milking)."
						+ " Also for [npc2.namePos] milking job permissions to allow the equipping of pumps into that slot.</i>";
			}
			return "Attach the milking machine's pumps to [npc2.namePos] "+Util.inventorySlotsToStringList(pumps)+".";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			List<InventorySlot> pumps = getPumpSlots();
			return !pumps.isEmpty()
					&& Main.sex.getCharacterPerformingAction().isPlayer() // Limit to the player as otherwise it gets incredibly annoying
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (!Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS) || Main.sex.isMasturbation())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LOCKED_IN_STOCKS);
		}

		@Override
		public String getDescription() {
			List<InventorySlot> pumps = getPumpSlots();
			
			if(pumps.size()>1 || pumps.contains(InventorySlot.NIPPLE) || pumps.contains(InventorySlot.STOMACH)) {
				return "Deciding to get [npc2.name] hooked up to the milking machine, [npc.name] [npc.verb(move)] around to one side of the stall and [npc.verb(grab)] the as-yet unused pumps...";
				
			} else {
				return "Deciding to get [npc2.name] hooked up to the milking machine, [npc.name] [npc.verb(move)] around to one side of the stall and [npc.verb(grab)] the as-yet unused pump...";
			}
		}

		@Override
		public String applyEffectsString(){
			List<InventorySlot> pumps = getPumpSlots();
			GameCharacter equipper = Main.sex.getCharacterPerformingAction();
			GameCharacter milker = Main.sex.getCharacterTargetedForSexAction(this);
			StringBuilder sb = new StringBuilder();
			
			for(InventorySlot slot : pumps) {
				sb.append("<p style='text-align:center;padding:0;margin:0;'><i>");
					switch(slot) {
						case NIPPLE:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, equipper));
							break;
						case STOMACH:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.STOMACH, true, equipper));
							break;
						case PENIS:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_penis_pump"), false), true, equipper));
							break;
						case VAGINA:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, equipper));
							break;
						default:
							break;
					}
				sb.append("</i></p>");
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SWITCH_TO_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Move behind";
		}

		@Override
		public String getActionDescription() {
			return "Move around behind [npc2.name] and get ready to fuck [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to fuck [npc2.name] while [npc2.sheIs] locked in the stocks,"
						+ " [npc.name] [npc.verb(step)] up behind [npc2.herHim] and [npc.verb(start)] grinding [npc.her] groin up against [npc2.her] [npc2.ass+]."
					+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moanVerb], "
					+ "[npc.speech(Be a good [npc2.girl] and hold still while I fuck you!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};

	public static final SexAction SWITCH_TO_BENEATH = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "All fours";
		}

		@Override
		public String getActionDescription() {
			return "Drop down on all fours beneath [npc2.name] and push your [npc.ass+] up against [npc2.her] groin, ready to be fucked by [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Wanting to get fucked by [npc2.name], [npc.name] [npc.verb(drop)] down onto all fours and [npc.verb(crawl)] forwards beneath [npc2.her] stocks."
					+ " Shuffling around to get into a comfortable position, [npc.she] [npc.verb(lift)] [npc.her] [npc.hips+] and [npc.verb(push)] [npc.her] [npc.ass+] back against [npc2.her] groin."
					+ " With an excited [npc.moan], [npc.name] [npc.verb(call)] out,"
					+ " [npc.speech(Lucky you! I'm going to let you fuck me!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.BENEATH_MILKING_STALL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Perform oral";
		}

		@Override
		public String getActionDescription() {
			return "Kneel behind [npc2.name] and perform oral on [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to perform oral on [npc2.name], [npc.name] [npc.verb(kneel)]down behind [npc2.herHim]."
					+ " Bringing [npc.her] mouth up to [npc2.her] groin, [npc.she] [npc.moanVerb],"
					+ " [npc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};
	
	public static final SexAction SWITCH_TO_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.RECEIVING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Move to front";
		}

		@Override
		public String getActionDescription() {
			return "Decide to use [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {
			return "Deciding that [npc.she] [npc.verb(want)] to use [npc2.namePos] mouth, [npc.name] [npc.verb(step)] back, before moving around in front of [npc2.her] [npc2.face]."
					+ " Bringing [npc.her] groin up to [npc2.her] mouth, [npc.she] [npc.moanVerb],"
					+ " [npc.speech(You're going to love this!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};

}
