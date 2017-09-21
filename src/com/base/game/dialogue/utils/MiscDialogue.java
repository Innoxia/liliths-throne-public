package com.base.game.dialogue.utils;

import java.util.Map.Entry;

import com.base.game.character.attributes.AffectionLevel;
import com.base.game.character.attributes.ObedienceLevel;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.npc.NPC;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.world.places.GenericPlace;

/**
 * @since 0.1.62
 * @version 0.1.85
 * @author Innoxia
 */
public class MiscDialogue {
	
	private static StringBuilder descriptionSB = new StringBuilder();
	
	public static final DialogueNodeOld STATUS_EFFECTS = new DialogueNodeOld("Important status effect updates", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB.setLength(0);
			
			for(Entry<StatusEffect, String> e : Main.game.getPlayer().getStatusEffectDescriptions().entrySet()){
				descriptionSB.append("<p>"
										+"<h6 style='text-align:center;'>"+Util.capitaliseSentence(e.getKey().getName(Main.game.getPlayer()))+"</h6>"
										+ e.getValue()
									+"</p>");
			}
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Continue", "Carry on with whatever you were doing."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent();
					}
				};
			} else {
				return null;
			}
		}
		
		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.STATUS_EFFECT_MESSAGE;
		}
	};
	
	public static DialogueNodeOld getSlaveryManagementDialogue(DialogueNodeOld rootDialogue, NPC slaveTrader) {
		Main.game.getDialogueFlags().slaveryManagerRootDialogue = rootDialogue;
		Main.game.getDialogueFlags().slaveTrader = slaveTrader;
		return SLAVE_MANAGEMENT;
	}
	
	private static final DialogueNodeOld SLAVE_MANAGEMENT = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='SM-container'>");
			
			// Append for sale first:
			for(NPC slave : Main.game.getCharactersPresent(Main.game.getPlayerCell())) {
				if(slave.isSlave() && !slave.getOwner().isPlayer()) {
					AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(slave.getAffection(Main.game.getPlayer()));
					ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(slave.getObedience());
					float affectionChange = slave.getDailyAffectionChange();
					float obedienceChange = slave.getDailyObedienceChange();
					
					UtilText.nodeContentSB.append(
							"<div class='SM-inner-container'>"
									+ "<div style='float:right; width:33%;'>"
										+ "<div class='SM-button' id='"+slave.getId()+"' style='width:100%; margin:4px 0 4px 0;'>"
											+ "Inspect"
										+ "</div>"
										+ "<div class='SM-button disabled' style='width:100%; margin:4px 0 4px 0;'>"
											+ "Transfer Here"
										+ "</div>");
					
					if(Main.game.getPlayer().getMoney()>=((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().slaveTrader.getSellModifier()))) {
						UtilText.nodeContentSB.append(
										"<div class='SM-button' id='"+slave.getId()+"_BUY' style='width:100%; margin:4px 0 4px 0;'>"
												+ "Buy ("+UtilText.formatAsMoney((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().slaveTrader.getSellModifier()), "span")+")"
										+ "</div>");
					} else {
						UtilText.nodeContentSB.append(
										"<div class='SM-button disabled' style='width:100%; margin:4px 0 4px 0;'>"
												+ "Buy ("+UtilText.formatAsMoneyUncoloured((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().slaveTrader.getBuyModifier()), "span")+")"
										+ "</div>");
					}
					UtilText.nodeContentSB.append(
								"</div>"
								+ "<h5 class='SM-title' style='color:"+slave.getFemininity().getColour().toWebHexString()+"; margin-right:12px;'>"+slave.getName()+"</h5>"
								+ "<h5 class='SM-title'>"
									+ "("
									+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getGender().getName())+"</span>"
									+ " <span style='color:"+slave.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getRace().getName())+"</span>"
									+ ")"
								+ "</h5></br>"
								+ "<p>"
									+"<b>Location:</b> "+slave.getWorldLocation().getName()+", "+slave.getLocationPlace().getName()+"</br>"
									+"<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection:</b> "
										+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+slave.getAffection(Main.game.getPlayer())+ "</b> | "
												+"<b style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")+affectionChange+"</b><b>/day</b>"
												+ " | " + AffectionLevel.getDescription(slave, Main.game.getPlayer(), affection, true)+"</br>"
									
									+"<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience:</b> "
										+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+slave.getObedience()+ "</b> | "
												+"<b style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")+obedienceChange+"</b><b>/day</b>"
												+ " | " + ObedienceLevel.getDescription(slave, obedience, true, false)+"</br>"
												
									+ UtilText.parse(slave, "[style.boldBad([npc.Name] belongs to "+slave.getOwner().getName("the")+".)]")
								+ "</p>"
							+ "</div>");
				}
			}
			
			
			if(Main.game.getPlayer().getSlavesOwned().isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='SM-inner-container' style='text-align:center;'>"
								+"<h5 style='color:"+Colour.BASE_GREY.toWebHexString()+";'>You do not own any slaves...</h5>"
						+ "</div>");
				
			} else {
				for(NPC slave : Main.game.getPlayer().getSlavesOwned()) {
					AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(slave.getAffection(Main.game.getPlayer()));
					ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(slave.getObedience());
					float affectionChange = slave.getDailyAffectionChange();
					float obedienceChange = slave.getDailyObedienceChange();
					GenericPlace place = Main.game.getPlayerCell().getPlace();
					
					UtilText.nodeContentSB.append(
							"<div class='SM-inner-container'>"
									+ "<div style='float:right; width:33%;'>"
										+ "<div class='SM-button' id='"+slave.getId()+"' style='width:100%; margin:4px 0 4px 0;'>"
											+ "Detailed Management"
										+ "</div>"
										+ "<div class='SM-button"+(place.getCapacity()<=Main.game.getCharactersPresent(Main.game.getPlayerCell()).size()
												?" disabled'"
												:"' id='"+slave.getId()+"_TRANSFER'")+" style='width:100%; margin:4px 0 4px 0;'>"
											+ "Transfer Here"
										+ "</div>");
					
					if(Main.game.getDialogueFlags().slaveTrader==null) {
						UtilText.nodeContentSB.append("<div class='SM-button disabled' style='width:100%; margin:4px 0 4px 0;'>"
											+ "Sell ("+UtilText.formatAsMoneyUncoloured((int) (slave.getValueAsSlave()), "span")+")"
										+ "</div>");
					} else {
						UtilText.nodeContentSB.append("<div class='SM-button' id='"+slave.getId()+"_SELL' style='width:100%; margin:4px 0 4px 0;'>"
											+ "Sell ("+UtilText.formatAsMoney((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().slaveTrader.getBuyModifier()), "span")+")"
										+ "</div>");
					}
					
					UtilText.nodeContentSB.append(
								"</div>"
								+"<h5 class='SM-title' style='color:"+slave.getFemininity().getColour().toWebHexString()+"; margin-right:12px;'>"+slave.getName()+"</h5>"
								+ "<h5 class='SM-title'>"
									+ "("
									+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getGender().getName())+"</span>"
									+ " <span style='color:"+slave.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getRace().getName())+"</span>"
									+ ")"
								+ "</h5></br>"
								+ "<p>"
									+"<b>Location:</b> "+slave.getWorldLocation().getName()+", "+slave.getLocationPlace().getName()+"</br>"
									
									+"<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection:</b> "
										+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+slave.getAffection(Main.game.getPlayer())+ "</b> | "
												+"<b style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")+affectionChange+"</b><b>/day</b>"
												+ " | " + AffectionLevel.getDescription(slave, Main.game.getPlayer(), affection, true)+"</br>"
									
									+"<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience:</b> "
										+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+slave.getObedience()+ "</b> | "
												+"<b style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")+obedienceChange+"</b><b>/day</b>"
												+ " | " + ObedienceLevel.getDescription(slave, obedience, true, false)+"</br>"
												
									+ UtilText.parse(slave, "[style.boldGood([npc.Name] belongs to you.)]")
								+ "</p>"
							+ "</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</div>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Exit the slave management screen.", Main.game.getDialogueFlags().slaveryManagerRootDialogue);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
	};
	
	public static DialogueNodeOld getSlaveryManagementDetailedDialogue(NPC slave) {
		Main.game.getDialogueFlags().slaveryManagerSlaveSelected = slave;
		return SLAVE_MANAGEMENT_DETAILED_VIEW;
	}
	
	private static final DialogueNodeOld SLAVE_MANAGEMENT_DETAILED_VIEW = new DialogueNodeOld("Slave Management", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return UtilText.parse(Main.game.getDialogueFlags().slaveryManagerSlaveSelected, "[npc.Name] - Slave Management");
		}
		
		@Override
		public String getContent() {
			NPC character = Main.game.getDialogueFlags().slaveryManagerSlaveSelected;
			AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(character.getAffection(Main.game.getPlayer()));
			ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedience());
			float affectionChange = character.getDailyAffectionChange();
			float obedienceChange = character.getDailyObedienceChange();
			
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					NPC.getCharacterInfoBox(character));
				
			if(character.getOwner().isPlayer()) {
				UtilText.nodeContentSB.append("<h4>Slave Names</h4>"
						+ "<div style='position:relative; display: inline-block; padding-bottom:0;margin-bottom:0;vertical-align:middle;width:70%;'>"
							+ "<p style='float:left; padding:0; margin:0; height:32px; line-height:32px;'>[npc.Her] name: </p>"
							+ "<form style='float:left;padding:auto 0 auto 0;'><input type='text' id='slaveNameInput' value='"+ character.getName()+ "'></form>"
							+ " <div class='SM-button' id='"+character.getId()+"_RENAME' style='float:left; width:auto; height:28px;'>"
								+ "Rename"
							+ "</div>"
						+ "</div>"
						
						+ "<div style='position:relative; display: inline-block; padding-bottom:0;margin-bottom:0;vertical-align:middle;width:70%;'>"
							+ "<p style='float:left; padding:0; margin:0; height:32px; line-height:32px;'>[npc.She] will call you: </p>"
							+ "<form style='float:left;padding:auto 0 auto 0;'><input type='text' id='slaveToPlayerNameInput' value='"+ character.getPlayerPetName()+ "'></form>"
							+ " <div class='SM-button' id='"+character.getId()+"_CALLS_PLAYER' style='float:left; width:auto; height:28px;'>"
								+ "Apply"
							+ "</div>"
							+ " <div class='SM-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:auto; height:28px;'>"
								+ "Apply to all slaves"
							+ "</div>"
						+ "</div>"
						+ "<p id='hiddenFieldName' style='display:none;'></p>");
			}
			
			UtilText.nodeContentSB.append("<h4>Slave Information</h4>"
					+ "<p>"
					+"<b>Location:</b> "+character.getWorldLocation().getName()+", "+character.getLocationPlace().getName()+"</br>"
					
					+"<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection:</b> "
						+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+character.getAffection(Main.game.getPlayer())+ "</b> | "
								+"<b style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")+affectionChange+"</b><b>/day</b>"
								+ " | " + AffectionLevel.getDescription(character, Main.game.getPlayer(), affection, true)+"</br>"
					
					+"<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience:</b> "
						+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedience()+ "</b> | "
								+"<b style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")+obedienceChange+"</b><b>/day</b>"
								+ " | " + ObedienceLevel.getDescription(character, obedience, true, false)+"</br>"
						
				+ "</p>");
			
			UtilText.nodeContentSB.append(
					"<h4>Background</h4>"
					+ "<p>"
						+ character.getDescription()
					+ "</p>"
					+ "<h4>Appearance</h4>"
					+ "<p>"
						+ character.getBodyDescription()
					+ "</p>"
					+ NPC.getStats(character));
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}

		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the slave management screen.", SLAVE_MANAGEMENT);

			} else if (index == 1) {
				if( Main.game.getDialogueFlags().slaveryManagerSlaveSelected.getOwner().isPlayer()) {
					return new ResponseEffectsOnly("Inventory", UtilText.parse(Main.game.getDialogueFlags().slaveryManagerSlaveSelected, "Manage [npc.name]'s inventory.")){
						@Override
						public void effects() {
							Main.mainController.openInventory(Main.game.getDialogueFlags().slaveryManagerSlaveSelected, InventoryInteraction.FULL_MANAGEMENT);
						}
					};
				} else {
					return new Response("Inventory", UtilText.parse(Main.game.getDialogueFlags().slaveryManagerSlaveSelected, "You cna't manage [npc.name]'s inventory, as you don't own [npc.herHim]!"), null);
				}
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isMapDisabled() {
			return true;
		}
	};
	
}
