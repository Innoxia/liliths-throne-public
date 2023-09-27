package com.lilithsthrone.controller;

import java.util.List;
import java.util.Map;

import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantSortingMethod;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaMilkingRoomDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class OccupantController {
	public static void initRoomManagerListeners() {
		for (Cell c : OccupantManagementDialogue.getImportantCells()) {
			if (MainController.document.getElementById(c.getId()+"_PRESENT_DISABLED") != null) {
				MainController.addTooltipListeners(c.getId()+"_PRESENT_DISABLED", new TooltipInformationEventListener().setInformation("Manage Room", "You are not able to manage this room!"));
			} else if (MainController.document.getElementById(c.getId()+"_DISABLED") != null) {
				MainController.addTooltipListeners(c.getId()+"_DISABLED", new TooltipInformationEventListener().setInformation("Manage Room", "You are not able to manage this room!"));
			} else if (MainController.document.getElementById(c.getId()+"_PRESENT") != null) {
				((EventTarget) MainController.document.getElementById(c.getId()+"_PRESENT")).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = c;
						}
						
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.ROOM_UPGRADES;
						}
					});
				}, false);
				MainController.addTooltipListeners(c.getId()+"_PRESENT", new TooltipInformationEventListener().setInformation("Manage Room", "Open this room's management screen."));
			} else if (MainController.document.getElementById(c.getId()) != null) {
				((EventTarget) MainController.document.getElementById(c.getId())).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = c;
						}
						
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.ROOM_UPGRADES;
						}
					});
				}, false);
				MainController.addTooltipListeners(c.getId(), new TooltipInformationEventListener().setInformation("Manage Room", "Open this room's management screen."));
			}
		}
	}
	
	public static void initRoomUpgradesListeners() {
		String id;
		for (AbstractPlaceUpgrade placeUpgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
			id = "ROOM_MOD_INFO_"+PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade);
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("", (OccupantManagementDialogue.cellToInspect.getPlace().getPlaceUpgrades().contains(placeUpgrade)
								?placeUpgrade.getDescriptionAfterPurchase()
								:placeUpgrade.getDescriptionForPurchase())));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_BUY";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(
							new Response("",
									"",
									placeUpgrade.getInstallationDialogue(OccupantManagementDialogue.cellToInspect) == null
											?Main.game.getCurrentDialogueNode()
											:placeUpgrade.getInstallationDialogue(OccupantManagementDialogue.cellToInspect)) {
								@Override
								public void effects() {
									OccupantManagementDialogue.cellToInspect.addPlaceUpgrade(placeUpgrade);
									Main.game.getPlayer().incrementMoney(-placeUpgrade.getInstallCost());
								}
							});
				}, false);
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_BUY_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_SELL";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect.removePlaceUpgrade(placeUpgrade);
							Main.game.getPlayer().incrementMoney(-placeUpgrade.getRemovalCost());
						}
					});
				}, false);
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Remove Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_SELL_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Remove Modification",
								(!placeUpgrade.getRemovalAvailability(OccupantManagementDialogue.cellToInspect).getKey()
										?placeUpgrade.getRemovalAvailability(OccupantManagementDialogue.cellToInspect).getValue()
										:"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade))));
			}
		}
		
		id = "rename_room_button";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()>32;
					}
					if (!unsuitableName) {
						Main.game.setContent(new Response("Rename Room", "Rename this room to whatever you've entered in the text box.", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								OccupantManagementDialogue.cellToInspect.getPlace().setName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
							}
						});
					}
				}
			}, false);
		}
	}
	
	static void fluidHandler(MilkingRoom room, FluidStored fluid) {
		String idModifier = "MILK";
		if (fluid.isCum()) {
			idModifier = "CUM";
		} else if (fluid.isGirlCum()) {
			idModifier = "GIRLCUM";
		}
		GameCharacter fluidOwner;
		try {
			fluidOwner = fluid.getFluidCharacter();
		} catch (Exception e1) {
			fluidOwner = null;
		}
		
		String fluidName = fluid.getFluid().getName(fluidOwner);
		
		Map<CoverableArea, SexAreaOrifice> areas = Util.newHashMapOfValues(
				new Util.Value<>(CoverableArea.MOUTH, SexAreaOrifice.MOUTH),
				new Util.Value<>(CoverableArea.VAGINA, SexAreaOrifice.VAGINA),
				new Util.Value<>(CoverableArea.ANUS, SexAreaOrifice.ANUS));
		
		for (Map.Entry<CoverableArea, SexAreaOrifice> area : areas.entrySet()) {
			String id = idModifier+"_"+area.getKey()+"_"+fluid.hashCode();
			if (MainController.document.getElementById(id) != null) {
				float milkAmount = Math.min(fluid.getMillilitres(), MilkingRoom.INGESTION_AMOUNT);
				boolean canIngest = room.isAbleToIngestThroughArea(fluid.getFluid().getType().getBaseType(), MilkingRoom.getTargetedCharacter(), area.getKey(), milkAmount);
				
				String fluidOwnerName = fluidOwner == null
						?"the"
						:(fluidOwner.equals(MilkingRoom.getTargetedCharacter())
						?UtilText.parse(fluidOwner, "[npc.her] own")
						:UtilText.parse(fluidOwner, "[npc.namePos]"));
				
				if (canIngest) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getTextEndStringBuilder().append("<p>");
						if (MilkingRoom.getTargetedCharacter().isPlayer()) {
							switch (area.getKey()) {
								case ANUS:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Grabbing one of the free tubes connected to the vat of "+fluidOwnerName+" "+fluidName+", you quickly remove the suction device on the end, before screwing on one of the funnel attachments."
													+" Guiding the end of the tube around to your [pc.ass+], you waste no time in sliding the funnel into your [pc.asshole+]."
													+" Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button, letting out a delighted [pc.moan] as you feel the "+fluidName+" being pumped into your [pc.asshole]."));
									break;
								case MOUTH:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Grabbing one of the free tubes connected to the vat of "+fluidOwnerName+" "+fluidName+", you quickly remove the suction device on the end, before screwing on one of the straw-like attachments."
													+" Lifting the straw up to your mouth, you waste no time in sliding it between your [pc.lips+]."
													+" Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button, letting out a delighted [pc.moan] as you hungrily gulp down the "+fluidName+"."));
									break;
								case VAGINA:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Grabbing one of the free tubes connected to the vat of "+fluidOwnerName+" "+fluidName+", you quickly remove the suction device on the end, before screwing on one of the funnel attachments."
													+" Guiding the end of the tube #IF(pc.hasLegs())between your [pc.legs+]#ELSEto your groin#ENDIF, you waste no time in sliding the funnel into your [pc.pussy+]."
													+" Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button, letting out a delighted [pc.moan] as you feel the "+fluidName+" being pumped"
													+(MilkingRoom.getTargetedCharacter().isVisiblyPregnant()
													?" into your [pc.pussy]."
													:" directly into your waiting womb.")));
									break;
								default:
									break;
							}
						} else {
							switch (area.getKey()) {
								case ANUS:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Wanting to pump [npc.namePos] [npc.ass+] full of "+fluidOwnerName+" "+fluidName+", you instruct [npc.herHim] to"
													+(!MilkingRoom.getTargetedCharacter().isTaur() && MilkingRoom.getTargetedCharacter().getGenitalArrangement() == GenitalArrangement.NORMAL
													?" bend over before you."
													:" kneel down and present [npc.herself] to you.")
													+" As soon as [npc.her] [npc.asshole+] is fully on display, you grab one of the free tubes connected to your selected vat of fluid,"
													+" before quickly removing the suction device on the end and screwing on one of the funnel attachments."
													+"</p>"
													+"<p>"
													+"Guiding the end of the tube up to [npc.namePos] [npc.ass+], you waste no time in sliding the funnel into [npc.her] [npc.asshole+], smiling to yourself as [npc.she] lets out [npc.a_moan+]."
													+" Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button,"
													+" causing [npc.name] to let out yet more delighted [npc.moans] as [npc.she] feels the "+fluidName+" being pumped into [npc.her] [npc.asshole]."));
									break;
								case MOUTH:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Wanting to give [npc.namePos] a taste of "+fluidOwnerName+" "+fluidName+", you instruct [npc.herHim] to kneel down before you."
													+" As soon as [npc.she] complies, you grab one of the free tubes connected to your selected vat of fluid,"
													+" before quickly removing the suction device on the end and screwing on one of the straw-like attachments."
													+"</p>"
													+"Bringing the straw to [npc.namePos] mouth, you waste no time in sliding it between [npc.her] [npc.lips+] and telling [npc.herHim] to prepare [npc.herself] for a tasty meal."
													+" Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button, letting out a delighted [pc.moan] as you watch [npc.name] hungrily gulp down the "+fluidName+"."));
									break;
								case VAGINA:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Wanting to pump [npc.namePos] [npc.pussy+] full of "+fluidOwnerName+" "+fluidName+", you instruct [npc.herHim] to"
													+(!MilkingRoom.getTargetedCharacter().isTaur()
													&& MilkingRoom.getTargetedCharacter().hasLegs()
													&& MilkingRoom.getTargetedCharacter().getGenitalArrangement() == GenitalArrangement.NORMAL
													?" sit down on a nearby chair and spread [npc.her] [npc.legs]."
													:" kneel down and present [npc.herself] to you.")
													+" As soon as [npc.she] complies, and [npc.her] [npc.pussy+] is fully on display, you grab one of the free tubes connected to your selected vat of fluid,"
													+" before quickly removing the suction device on the end and screwing on one of the funnel attachments."
													+"</p>"
													+"<p>"
													+"Guiding the end of the tube up to [npc.namePos] [npc.labia+], you waste no time in sliding the funnel into [npc.her] [npc.pussy+], smiling to yourself as [npc.she] lets out [npc.a_moan+]."
													+" Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button,"
													+" causing [npc.name] to let out yet more delighted [npc.moans] as [npc.she] feels the "+fluidName+" being pumped"
													+(MilkingRoom.getTargetedCharacter().isVisiblyPregnant()
													?" into [npc.her] [npc.pussy]."
													:" directly into [npc.her] waiting womb.")));
									break;
								default:
									break;
							}
						}
						
						String ingestion;
						try {
							GameCharacter c = fluid.getFluidCharacter();
							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(c, fluid.getFluid(), area.getValue(), milkAmount);
						} catch (Exception e1) {
							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(fluid, area.getValue(), milkAmount);
						}
						if (!ingestion.isEmpty()) {
							Main.game.getTextEndStringBuilder().append("</p>"
									+"<p>"
									+ingestion);
						}
						Main.game.getTextEndStringBuilder().append("</p>");
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
										+"<i style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>"+Units.fluid(milkAmount)+" of "+fluidOwnerName+" "+fluidName+" has been consumed!</i>"
										+"</p>");
						
						room.incrementFluidStored(fluid, -milkAmount);
						
						Main.game.setContent(new Response("", "", LilayaMilkingRoomDialogue.MILKED));
						
					}, false);
				}
				String verb = "Drink";
				String description;
				
				if (MilkingRoom.getTargetedCharacter().isPlayer()) {
					description = "Drink "+Units.fluid(milkAmount)+" of the "+fluidName+".";
					if (area.getKey() != CoverableArea.MOUTH) {
						verb = "Pump";
						description = "Pump "+Units.fluid(milkAmount)+" of the "+fluidName+" into your "+area.getKey().getName()+".";
					}
					
				} else {
					description = UtilText.parse(MilkingRoom.getTargetedCharacter(), "Get [npc.name] to drink ")+Units.fluid(milkAmount)+" of the "+fluidName+".";
					if (area.getKey() != CoverableArea.MOUTH) {
						verb = "Pump";
						description = "Pump "+Units.fluid(milkAmount)+" of the "+fluidName+" into "+UtilText.parse(MilkingRoom.getTargetedCharacter(), "[npc.namePos] ")+area.getKey().getName()+".";
					}
				}
				
				TooltipInformationEventListener el;
				if (canIngest) {
					el = new TooltipInformationEventListener().setInformation(verb+" ("+Units.fluid(milkAmount)+")",
							description);
				} else {
					el = new TooltipInformationEventListener().setInformation(verb+" ("+Units.fluid(milkAmount)+")",
							room.getAreaIngestionBlockedDescription(fluid.getFluid().getType().getBaseType(), MilkingRoom.getTargetedCharacter(), area.getKey(), milkAmount));
				}
				MainController.addTooltipListeners(id, el);
			}
		}
		
		String id = idModifier+"_SELL_"+fluid.hashCode();
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				int income = Math.max(1, (int) (fluid.getFluid().getValuePerMl()*fluid.getMillilitres()));
				Main.game.getPlayer().incrementMoney(income);
				room.getFluidsStored().remove(fluid);
				Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>You sold the "+fluidName+" for "+(UtilText.formatAsMoney(income, "span"))+"!</p>");
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id,
					new TooltipInformationEventListener().setInformation("Sell", "Sell all of the "+fluidName+" for "+(Math.max(1, (int) (fluid.getFluid().getValuePerMl()*fluid.getMillilitres())))+" flames."));
		}
	}
	
	public static void initOverviewListeners() {
		for (int i = 6; i>=0; i--) {
			String id = "SLAVE_DAY_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i; // Lambda requirement
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					OccupantManagementDialogue.setDayNumber(Main.game.getDayNumber()-finalI);
					Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initSlaveJobListeners() {
		String id;
		// Job hours:
		for (int i = 0; i<24; i++) {
			id = i+"_WORK";
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
					if (Main.game.getDialogueFlags().getManagementCompanion().getSlaveJob(finalI) == job) {
						Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(finalI, SlaveJob.IDLE);
					} else {
						Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(finalI, job);
					}
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
			} else {
				id = i+"_WORK_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"[style.colourBad(Unavailable Time Slot)]",
							Main.game.getDialogueFlags().getSlaveryManagerJobSelected().getAvailabilityText(i, Main.game.getDialogueFlags().getManagementCompanion())));
				}
			}
		}
		for (SlaveJobHours preset : SlaveJobHours.values()) {
			id = preset+"_TIME";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (preset == SlaveJobHours.NONE) {
						for (int hour = 0; hour<24; hour++) {
							SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
							if (Main.game.getDialogueFlags().getManagementCompanion().getSlaveJob(hour) == job) {
								Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(hour, SlaveJob.IDLE);
							}
						}
					} else {
						for (int hour = preset.getStartHour(); hour<preset.getStartHour()+preset.getLength(); hour++) {
							int appliedHour = hour%24;
							SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
							if (job.isAvailable(appliedHour, Main.game.getDialogueFlags().getManagementCompanion())) {
								Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(appliedHour, job);
							}
						}
					}
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Set Preset Work Hours", preset.getDescription()));
			}
			
			id = preset+"_TIME_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"[style.colourBad(Set Preset Work Hours)]",
						"You cannot assign these hours. Either the job is not available for these hours, or it is too much work."));
			}
		}
		
		// Jobs:
		for (SlaveJob job : SlaveJob.values()) {
			id = "SLAVE_JOB_INFO_"+job;
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setSlaveJob(job, Main.game.getDialogueFlags().getManagementCompanion()));
			}
			
			id = job+"_ASSIGN";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.getDialogueFlags().setSlaveryManagerJobSelected(job);
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setSlaveJob(job, Main.game.getDialogueFlags().getManagementCompanion()));
			}
			
			for (SlaveJobSetting setting : job.getMutualSettings()) {
				id = job+setting.toString()+"_ADD";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().addSlaveJobSettings(job, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> "
									+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
							setting.getDescription()
									+" [style.italicsMinorGood(Click to apply this permission.)]"));
				}
				
				id = job.toString()+setting+"_REMOVE";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().removeSlaveJobSettings(job, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> "
									+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
							setting.getDescription()
									+" [style.italicsMinorBad(Click to revoke this permission.)]"));
				}
			}
			
			for (Map.Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {
				for (SlaveJobSetting setting : entry.getValue()) {
					id = setting+"_TOGGLE_ADD";
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							for (SlaveJobSetting settingRem : entry.getValue()) {
								Main.game.getDialogueFlags().getManagementCompanion().removeSlaveJobSettings(job, settingRem);
							}
							Main.game.getDialogueFlags().getManagementCompanion().addSlaveJobSettings(job, setting);
							Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
						}, false);
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
								"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> "
										+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
								setting.getDescription()
										+" [style.italicsMinorGood(Click to apply this permission.)]"));
					}
					
					id = setting+"_DISABLED";
					if (MainController.document.getElementById(id) != null) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
								"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> "
										+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
								setting.getDescription()
										+" [style.italicsMinorBad(You cannot revoke permissions in this category. Select a different one instead.)]"));
					}
				}
			}
		}
	}
	
	public static void initSlavePermissionsListeners() {
		// Permissions:
		for (SlavePermission permission : SlavePermission.values()) {
			for (SlavePermissionSetting setting : permission.getSettings()) {
				String id = setting+"_ADD";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().addSlavePermissionSetting(permission, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
							setting.getDescription()
									+" [style.italicsMinorGood(Click to apply this permission.)]"
									+(permission.isMutuallyExclusiveSettings()
									?" [style.italicsMinorBad(Only one permission in this category can be active at once.)]"
									:"")));
				}
				
				id = setting+"_REMOVE";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().removeSlavePermissionSetting(permission, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
							setting.getDescription()
									+" [style.italicsMinorBad(Click to revoke this permission.)]"));
				}
				
				id = setting+"_REMOVE_ME";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
							setting.getDescription()
									+" [style.italicsMinorBad(You cannot revoke permissions in this category. Select a different one instead.)]"));
				}
			}
		}
	}
	
	public static void initOccupantListListeners() {
		String id;
		// Sorting
		for (OccupantSortingMethod osm : OccupantSortingMethod.values()) {
			id = "SORT_SLAVES_BY_"+osm;
			if (MainController.document.getElementById(id) != null) {
				String friendlyName = Util.capitaliseSentence(osm.getName());
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.setSlavesSortedBy(osm);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"<b>Sort By "+friendlyName+"</b>",
						osm.getSortingDescription()));
			}
		}
		
		// Sort ascending
		id = "SORT_SLAVES_ASC";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						OccupantManagementDialogue.setSlavesAreInReverseOrder(false);
					}
				});
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
					"Sort in ascending order",
					""));
		}
		
		// Sort Descending
		id = "SORT_SLAVES_DESC";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						OccupantManagementDialogue.setSlavesAreInReverseOrder(true);
					}
				});
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
					"Sort in descending order",
					""));
		}
		
		for (String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			id = slaveId;
			NPC slave;
			
			try {
				slave = (NPC) Main.game.getNPCById(slaveId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, getSlavesOwned", slaveId);
				continue;
			}
			
			if (slave != null) { // slave shouldn't be null...
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(slave)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Inspect Slave",
							UtilText.parse(slave, "Inspect [npc.name].")));
				}
				
				id = slaveId+"_JOB";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(slave)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Slave's Job",
							UtilText.parse(slave, "Set [npc.namePos] job and work hours.")));
				}
				
				id = slaveId+"_PERMISSIONS";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(slave)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Slave's Permissions",
							UtilText.parse(slave, "Manage [npc.namePos] permissions.")));
				}
				
				id = slaveId+"_INVENTORY";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
						Main.mainController.openInventory(slave, InventoryInteraction.FULL_MANAGEMENT);
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Slave's Inventory",
							UtilText.parse(slave, "Manage [npc.namePos] inventory.")));
				}
				
				id = slaveId+"_TRANSFER";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								slave.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
								if(!slave.isAtWork() || slave.getLocationPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
									slave.returnToHome();
								}							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Move Slave Here",
							UtilText.parse(slave, "Move [npc.name] to your current location.")));
				}
				
				id = slaveId+"_TRANSFER_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Move Slave Here",
							UtilText.parse(slave, "You cannot move [npc.name] to this location, as there's no room for [npc.herHim] here.")));
				}
				
				id = slaveId+"_SELL";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (slave.isAbleToBeSold()) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()));
									Main.game.getDialogueFlags().getSlaveTrader().addSlave(slave);
									slave.setLocation(Main.game.getDialogueFlags().getSlaveTrader().getWorldLocation(), Main.game.getDialogueFlags().getSlaveTrader().getLocation(), true);
								}
							});
						}
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Sell Slave",
							UtilText.parse(slave,
									(slave.isAbleToBeSold()
											?"[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", PresetColour.GENERIC_GOOD)+"<br/>"
											+"However, "+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+" will buy [npc.herHim] for "
											+UtilText.formatAsMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", PresetColour.GENERIC_ARCANE)+"."
											:"[npc.Name] cannot be sold!"))));
				}
				
				id = slaveId+"_SELL_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Sell Slave",
							UtilText.parse(slave,
									slave.isAbleToBeSold()
											?"You cannot sell [npc.name], as there's nobody here to sell [npc.herHim] to."
											:"[npc.Name] cannot be sold!")));
				}
				
				id = slaveId+"_COSMETICS";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveCosmeticsDialogue(slave)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								BodyChanging.setTarget(slave);
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Send to Kate",
							UtilText.parse(slave, "Send [npc.name] to Kate's beauty salon, 'Succubi's Secrets', to get [npc.her] appearance changed.")));
				}
				
				id = slaveId+"_COSMETICS_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Send Slave to Kate",
							UtilText.parse(slave, "You haven't met Kate yet!")));
				}
			}
		}
		
		for (String occupantId : Main.game.getPlayer().getFriendlyOccupants()) {
			id = occupantId;
			NPC occupant;
			
			try {
				occupant = (NPC) Main.game.getNPCById(occupantId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, getFriendlyOccupants", occupantId);
				continue;
			}
			
			if (occupant != null) { // It shouldn't equal null...
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(occupant)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Inspect Character",
							UtilText.parse(occupant, "Inspect [npc.name].")));
				}
				
				id = occupantId+"_JOB";
				if (MainController.document.getElementById(id) != null) {
					if(occupant.hasJob()) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Job", UtilText.parse(occupant, "[npc.name] already has a permanent job, so cannot be assigned to work within the mansion...")));
						
					} else {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(occupant)) {
								@Override
								public void effects() {
									CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
									Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								}
							});
						}, false);
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Job", UtilText.parse(occupant, "Assign [npc.name] some temporary work.")));
					}
				}
				
				id = occupantId+"_PERMISSIONS";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Permissions", "You cannot manage a free-willed occupant's permissions."));
				}
				
				id = occupantId+"_INVENTORY";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().setManagementCompanion(occupant);
						Main.mainController.openInventory(occupant, InventoryInteraction.FULL_MANAGEMENT);
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Inventory",
							UtilText.parse(occupant, "Manage [npc.namePos] inventory.")));
				}
				
				id = occupantId+"_TRANSFER";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								occupant.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
								occupant.returnToHome();
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Move Here",
							UtilText.parse(occupant, "Move [npc.name] to your current location.")));
				}
				
				id = occupantId+"_TRANSFER_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Move Here",
							UtilText.parse(occupant, "You cannot move [npc.name] to this location, as there's no room for [npc.herHim] here.")));
				}
				
				id = occupantId+"_COSMETICS";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveCosmeticsDialogue(occupant)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								BodyChanging.setTarget(occupant);
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Send to Kate",
							UtilText.parse(occupant, "Send [npc.name] to Kate's beauty salon, 'Succubi's Secrets', to get [npc.her] appearance changed.")));
				}
				
				id = occupantId+"_COSMETICS_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Send to Kate", "You haven't met Kate yet!"));
				}
			}
		}
	}
	
	public static void initSlaveTraderListeners() {
		for (String slaveId : Main.game.getDialogueFlags().getSlaveTrader().getSlavesOwned()) {
			String id = slaveId+"_TRADER";
			NPC slave;
			try {
				slave = (NPC) Main.game.getNPCById(slaveId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, TRADER.", slaveId);
				continue;
			}
			
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(slave)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
							Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Inspect Slave",
						UtilText.parse(slave, "Take a detailed look at [npc.name].")));
			}
			
			id = slaveId+"_TRADER_JOB";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Slave's Job",
						UtilText.parse(slave, "You cannot manage [npc.namePos] job, as you don't own [npc.herHim]!")));
			}
			
			id = slaveId+"_TRADER_PERMISSIONS";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Slave's Permissions",
						UtilText.parse(slave, "You cannot manage [npc.namePos] permissions, as you don't own [npc.herHim]!")));
			}
			
			id = slaveId+"_TRADER_INVENTORY";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Manage Slave's Inventory",
						UtilText.parse(slave, "You cannot manage [npc.namePos] inventory, as you don't own [npc.herHim]!")));
			}
			
			id = slaveId+"_TRADER_TRANSFER";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Move Slave Here",
						UtilText.parse(slave, "You cannot move [npc.name] to this location, as you don't own [npc.herHim], as well as due to the fact that [npc.sheIs] already here!")));
			}
			
			id = slaveId+"_BUY";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-(int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)));
							Main.game.getPlayer().addSlave(slave);
							slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Buy Slave",
						UtilText.parse(slave, "[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", PresetColour.GENERIC_GOOD)+"<br/>"
								+"However, "+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+" will sell [npc.herHim] for "
								+UtilText.formatAsMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)), "b", PresetColour.GENERIC_ARCANE)+".")));
			}
			
			id = slaveId+"_BUY_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Buy Slave",
						UtilText.parse(slave, "You cannot buy [npc.name], as you don't have enough money!")));
			}
			
			id = slaveId+"_TRADER_COSMETICS";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Send Slave to Kate",
						UtilText.parse(slave, "You can't send a slave you don't own to Kate!")));
			}
		}
	}
}
