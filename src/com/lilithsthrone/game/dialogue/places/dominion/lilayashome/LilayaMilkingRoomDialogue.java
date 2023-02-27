package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class LilayaMilkingRoomDialogue {
	
	private static GameCharacter getMilkingTarget() {
		return MilkingRoom.getTargetedCharacter();
	}
	
	public static final DialogueNode MILKING_ROOM = new DialogueNode("Room", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "<b style='color:"+PlaceUpgrade.LILAYA_MILKING_ROOM.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_MILKING_ROOM.getName()+"</b><br/>"
						+ "This large room has been converted into a milking room, in which eight of your slaves can be milked of their various fluids."
						+ " Four machines are set along the left-hand side of the wall, with the other four being placed on the opposite side of the room."
					+ "</p>");
			
			sb.append(room.getRoomDescription());
			sb.append("<br/>");
			
			for(AbstractPlaceUpgrade up : Main.game.getPlayerCell().getPlace().getPlaceUpgrades()) {
				if(!up.isCoreRoomUpgrade()) {
					sb.append("<p>"
								+ "<b style='color:"+up.getColour().toWebHexString()+";'>"+up.getName()+"</b><br/>"
								+ up.getRoomDescription(Main.game.getPlayerCell())
							+ "</p>");
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage room", "Enter the management screen for this particular room.", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("Manage room", "You'll either need a slaver license, or permission from Lilaya to house your friends, before you can access this menu!",  null);
				}
				
			}  else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Manage people", "Enter the management screen for your slaves and friendly occupants.", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Manage people", "You'll either need a slaver license, or permission from Lilaya to house your friends, before you can access this menu!",  null);
				}
				
			} else if(index>=3 && index<6) {
				return null;
				
			} else if(index==6) {
				if(getMilkingTarget().getBreastRawStoredMilkValue()==0) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "There is no milk stored in [npc.namePos] breasts, so [npc.she] can't be milked at the moment!"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] unable to get access to [npc.her] nipples, so [npc.she] can't be milked at the moment!"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "There are no free milking machines for [npc.name] to use!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(),
									"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you."),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative())) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]")),
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()
												?"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for both the "
													+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" and "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" fetishes."
												:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for the "+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" fetish.")
											:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for the "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" fetish.")),
							null);
					
				} else {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							"Use this room's spare milking equipment to milk "+(getMilkingTarget().isPlayer()?"yourself":UtilText.parse(getMilkingTarget(), "[npc.name]"))+".",
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualMilkPerHour(getMilkingTarget());
							if(milked < getMilkingTarget().getBreastRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(getMilkingTarget())) {
								milked = (int) Math.min(getMilkingTarget().getBreastRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(getMilkingTarget()));
							}
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getMilk(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_TARGET", getMilkingTarget()));
							}
							
							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.MILK.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " of [npc.milk] added to this room's storage!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualMilkPerHour(getMilkingTarget());
							getMilkingTarget().incrementBreastStoredMilk(-milked);
							return true;
						}
					};
				}
				
			} else if(index==7) {
				if(!getMilkingTarget().hasPenisIgnoreDildo()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "[npc.Name] [npc.do]n't have a penis, so [npc.she] can't produce any cum..."),
							null);
					
				} else if(getMilkingTarget().getPenisRawStoredCumValue()==0) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "There isn't any cum stored in [npc.namePos] balls, so [npc.her] cock can't be milked..."),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] unable to get access to [npc.her] cock, so [npc.she] can't be milked at the moment..."),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "There are no free milking machines for [npc.name] to use!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion() && !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(),
									"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you."),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative())) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative()
												?"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for both the "
													+Fetish.FETISH_CUM_STUD.getName(getMilkingTarget())+" and "+Fetish.FETISH_PENIS_GIVING.getName(getMilkingTarget())+" fetishes."
												:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for the "+Fetish.FETISH_CUM_STUD.getName(getMilkingTarget())+" fetish.")
											:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for the "+Fetish.FETISH_PENIS_GIVING.getName(getMilkingTarget())+" fetish.")),
							null);
					
				} else {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" cum",
							UtilText.parse(getMilkingTarget(), "Use this room's spare milking equipment to milk [npc.namePos] cock."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualCumPerHour(getMilkingTarget());
							room.incrementFluidStored(new FluidStored(getMilkingTarget(), getMilkingTarget().getCum(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_TARGET", getMilkingTarget()));
							}

							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.CUM.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " of [npc.cum] added to this room's storage!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualCumPerHour(getMilkingTarget());
							getMilkingTarget().incrementPenisStoredCum(-milked);
							getMilkingTarget().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed());
							return true;
						}
					};
				}
				
			} else if(index==8) {
				if(!getMilkingTarget().hasVagina()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "[npc.Name] [npc.do]n't have a vagina, so [npc.she] can't produce any girlcum..."),
							null);
					
				} else if(getMilkingTarget().getVaginaWetness().getValue()==0) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "[npc.NamePos] pussy is completely dry, and cannot produce even one drop of girlcum..."),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] unable access to [npc.her] pussy, so can't be milked of [npc.her] girlcum at the moment..."),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "There are no free milking machines for [npc.name] to use!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(),
									"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you."),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for the "+Fetish.FETISH_VAGINAL_RECEIVING.getName(getMilkingTarget())+" fetish."),
							null);
					
				}else {
					return new Response(
							"Milk "+(getMilkingTarget().isPlayer()?"self":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" girlcum",
							UtilText.parse(getMilkingTarget(), "Use this room's spare milking equipment to milk [npc.namePos] pussy."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualGirlcumPerHour(getMilkingTarget());
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getGirlcum(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_TARGET", getMilkingTarget()));
							}

							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center; color:"+PresetColour.GIRLCUM.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " of [npc.girlcum] added to this room's storage!")
									+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							getMilkingTarget().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed());
							return true;
						}
					};
				}
				
				
			} else if(index==9) {
				if(!getMilkingTarget().hasBreastsCrotch()) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self udders"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] udders")),
							UtilText.parse(getMilkingTarget(), "[npc.Name] [npc.do]n't have any udders to milk!"),
							null);
					
				} else if(getMilkingTarget().getBreastCrotchRawStoredMilkValue()==0) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "There is no milk stored in [npc.namePos] [npc.crotchBoobs], so [npc.she] can't be milked at the moment!"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.NIPPLES_CROTCH, true)) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] unable access to [npc.her] [npc.crotchNipples], so can't be milked at the moment..."),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "There are no free milking machines for [npc.name] to use!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(),
									"As [npc.sheIs] not your slave, [npc.name] will only let you do this if [npc.she]"
											+ " <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> you."),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative())) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()
												?"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for both the "
													+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" and "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" fetishes."
												:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for the "+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" fetish.")
											:"As [npc.sheIs] not your slave, [npc.name] not let you do this, as [npc.sheHasFull] a negative desire for the "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" fetish.")),
							null);
					
				} else {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Milk self [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Milk [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "Use this room's spare milking equipment to milk [npc.namePos] [npc.crotchBoobs]."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualCrotchMilkPerHour(getMilkingTarget());
							if(milked < getMilkingTarget().getBreastCrotchRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(getMilkingTarget())) {
								milked = (int) Math.min(getMilkingTarget().getBreastCrotchRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(getMilkingTarget()));
							}
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getMilkCrotch(), milked), milked);

							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_TARGET", getMilkingTarget()));
							}
							
							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.MILK.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " of [npc.crotchMilk] added to this room's storage!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualCrotchMilkPerHour(getMilkingTarget());
							getMilkingTarget().incrementBreastCrotchStoredMilk(-milked);
							return true;
						}
					};
				}
				
			} else if(index==10) {
				if(Main.game.getCharactersPresent().isEmpty()) {
					return new ResponseEffectsOnly(
							"Target: <span style='color:"+getMilkingTarget().getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(getMilkingTarget(), "[npc.Name]")+"</span>",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] currently targeted as the person both to be milked and to have fluids pumped inside of [npc.herHim]."
									+ "If this room wasn't empty, you could use this button to cycle between available targets.")) {
						
					};
					
				} else {
					return new Response(
							"Target: <span style='color:"+getMilkingTarget().getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(getMilkingTarget(), "[npc.Name]")+"</span>",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] currently targeted as the person both to be milked and to have fluids pumped inside of [npc.herHim]. Activate this button to cycle between available targets."),
							null) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getCurrentDialogueNode();
						}
						@Override
						public void effects() {
							List<GameCharacter> targetCharactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
							targetCharactersPresent.add(Main.game.getPlayer());
							for(int i=0; i<targetCharactersPresent.size();i++) {
								if(targetCharactersPresent.get(i).equals(getMilkingTarget())) {
									if(i==targetCharactersPresent.size()-1) {
										MilkingRoom.setTargetedCharacter(targetCharactersPresent.get(0));
									} else {
										MilkingRoom.setTargetedCharacter(targetCharactersPresent.get(i+1));
									}
									break;
								}
							}
							Main.game.updateResponses();
						}
					};
				}
				
			} else if(index-11<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-11);
				return LilayaHomeGeneric.interactWithNPC(slave);
			}
				
			return null;
		}
	};
	
	public static final DialogueNode MILKED = new DialogueNode("Room", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Unstrap yourself from the milking machine and continue on your way.", MILKED) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			} else {
				return null;
			}
		}
	};
}
