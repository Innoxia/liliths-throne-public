package com.lilithsthrone.game.dialogue.companions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.game.occupantManagement.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.SlavePermission;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.5.1
 * @version 0.3.5.1
 * @author Innoxia
 */
public class CompanionManagement {

	private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	private static DialogueNode coreNode;
	private static int defaultResponseTab;
	
	public static DialogueNode getCoreNode() {
		return coreNode;
	}

	public static int getDefaultResponseTab() {
		return defaultResponseTab;
	}

	public static void initManagement(DialogueNode coreNode, int defaultResponseTab, NPC targetedCharacter) {
		CompanionManagement.coreNode = coreNode;
		CompanionManagement.defaultResponseTab = defaultResponseTab;
		Main.game.getDialogueFlags().setManagementCompanion(targetedCharacter);
	}

	public static DialogueNode getSlaveryManagementInspectSlaveDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_INSPECT;
	}
	
	public static DialogueNode getSlaveryManagementSlaveJobsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_JOBS;
	}
	
	public static DialogueNode getSlaveryManagementSlavePermissionsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_PERMISSIONS;
	}
	
	public static NPC characterSelected() {
		return Main.game.getDialogueFlags().getManagementCompanion();
	}
	
	private static String getSlaveInformationHeader(NPC character) {
		StringBuilder headerSB = new StringBuilder();
		AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(character.getAffection(Main.game.getPlayer()));
		ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue());
		float affectionChange = character.getDailyAffectionChange();
		float obedienceChange = character.getDailyObedienceChange();
		
		headerSB.append(
				"<div class='container-full-width' style='margin-top:0; text-align:center; border-radius: 5px 0 0 5px;'>"
					// Extra core information:
					+"<div class='container-full-width inner' style='margin-bottom:0;'>"
						+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "Location"
						+ "</div>"
						+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</b>"
						+"</div>"
						+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
						+"</div>"
						+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Income</b>"
						+"</div>"
						+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "Value"
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width inner' style='margin-top:0; border-radius: 0 5px 5px; 0'>"
						+"<div style='width:30%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+character.getLocationPlace().getColourString()+";'>"+character.getLocationPlace().getName()+"</b>"
							+",<br/>"
							+ "<span style='color:"+character.getWorldLocation().getColour().toWebHexString()+";'>"+character.getWorldLocation().getName()+"</span>"
						+ "</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+character.getAffection(Main.game.getPlayer())+ "</b>"
							+ "<br/><span style='color:"+(affectionChange==0?Colour.BASE_GREY:(affectionChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?Colour.BASE_GREY:(obedienceChange>0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+obedience.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ UtilText.formatAsMoney(SlaveJob.getFinalDailyIncomeAfterModifiers(character))+"/day"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ UtilText.formatAsMoney(character.getValueAsSlave(true))
						+"</div>"
					+ "</div>");

		
		// Job:
		headerSB.append("<div class='container-half-width' style='width:50%; margin:0;'>[style.boldExcellent(Job Settings:)]");
		List<String> noPermissions = new ArrayList<>();
		for(SlaveJob job : SlaveJob.values()) {
			if(character.hasSlaveJobAssigned(job)) {
				List<String> permissions = new ArrayList<>();
				if(!character.getSlaveJobSettings(job).isEmpty()) {
					headerSB.append("<br/><b>"+Util.capitaliseSentence(job.getName(character))+":</b> ");
					for(SlaveJobSetting setting : character.getSlaveJobSettings(job)) {
						permissions.add("<span style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</span>");
					}
					headerSB.append(Util.stringsToStringList(permissions, false)+".");
				} else {
					noPermissions.add(job.getName(character));
				}
			}
		}
		if(!noPermissions.isEmpty()) {
			headerSB.append("<br/><b>"+Util.stringsToStringList(noPermissions, true)+":</b> [style.colourDisabled(n/a)]");
		}
		headerSB.append("</div>");
		
		
		// Permissions:
		headerSB.append("<div class='container-half-width' style='width:50%; margin:0;'>[style.boldArcane(General Permissions:)]<br/>");
		int permissionCount=0;
		for(SlavePermission permission : SlavePermission.values()) {
			for(SlavePermissionSetting setting : permission.getSettings()) {
				if(character.getSlavePermissionSettings().get(permission).contains(setting)) {
					headerSB.append((permissionCount==0?"":", ")+"<span style='color:"+permission.getColour().toWebHexString()+";'>"+setting.getName()+"</span>");
					permissionCount++;
				}
			}
		}
		headerSB.append(".</div>");
		
		
		headerSB.append("</div>");
		
		return headerSB.toString();
	}
	
	private static String getBusyWarning() {
		return "<br/>[style.italicsMinorBad(Can only be used when in a tile's default dialogue.)]";
	}
	
	public static Response getManagementResponses(int index) {
		if(coreNode==CharactersPresentDialogue.MENU) { // Companion in the player's party:
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			Collections.sort(charactersPresent, (c1, c2) -> Main.game.getPlayer().hasCompanion(c1)?1:0);
			
			if (index == 0) {
				return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setManagementCompanion(null);
						Main.game.restoreSavedContent(false);
						MainController.updateUI();
					}
				};

			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("Inspect", UtilText.parse(characterSelected(), "You are already taking a closer look at [npc.name]!"), null);
				}
				return new Response("Inspect", UtilText.parse(characterSelected(), "Take a closer look at [npc.name]."), SLAVE_MANAGEMENT_INSPECT);

			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("Job", UtilText.parse(characterSelected(), "You are already setting [npc.namePos] job and work hours!"), null);
				}
				if(characterSelected().isSlave()) {
					return new Response("Job", "Set this slave's job and work hours.", SLAVE_MANAGEMENT_JOBS);
				}
				return new Response("Job", "You cannot manage the job of a free-willed companion. This option is only available for slaves.", null);

			} else if(index==3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("Permissions", UtilText.parse(characterSelected(), "You are already setting [npc.namePos] permissions!"), null);
				}
				if(characterSelected().isSlave()) {
					return new Response("Permissions", "Set this slave's job and work hours.", SLAVE_MANAGEMENT_PERMISSIONS);
				}
				return new Response("Permissions", "You cannot manage the permissions of a free-willed companion. This option is only available for slaves.", null);
				
			} else if(index==4) {
				if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("Inventory", "You're in the middle of something right now!"+getBusyWarning(), null);
					
				} else {
					return new ResponseEffectsOnly("Inventory", UtilText.parse(characterSelected(), "Manage [npc.namePos] inventory.")) {
						@Override
						public void effects() {
							Main.mainController.openInventory((NPC) characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
				}
						
			} else if(index==5) {
				return new Response("Send to Kate", UtilText.parse(characterSelected(), "You cannot send your party companions to Kate! You'll need to send [npc.name] home first..."), null);
				
			} else if (index == 6) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(Reset perks)]",
							UtilText.parse(characterSelected(), "Reset all of [npc.namePos] perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("Perks", UtilText.parse(characterSelected(), "Assign [npc.namePos] perk points."), SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("Transformations", characterSelected().getUnableToTransformDescription(), null);
					
				} else if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("Transformations", "You're in the middle of something right now!"+getBusyWarning(), null);
					
				} else {
					return new Response("Transformations",
							UtilText.parse(characterSelected(), "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected());
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("Set names", UtilText.parse(characterSelected(), "You are managing [npc.namePos] name!"), null);
				}
				 if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("Set names", "You're in the middle of something right now!"+getBusyWarning(), null);
				}
				return new Response("Set names",
						characterSelected().isSlave()
							?UtilText.parse(characterSelected(), "Change [npc.namePos] name or tell [npc.herHim] to call you by a different name.")
							:UtilText.parse(characterSelected(), "Tell [npc.name] to call you by a different name."),
						OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10) {
				if(Main.game.getPlayer().isCaptive()) {
					return new Response(
							characterSelected().isElemental()
								?"Dispel"
								:"Send home",
							"As you are a captive, you cannot dismiss your companions!",
							null);
					
				} else if(!Main.game.isSavedDialogueNeutral()) {
					return new Response(
							characterSelected().isElemental()
								?"Dispel"
								:"Send home",
							"You're in the middle of something right now!"+getBusyWarning(),
							null);
					
				} else {
					if(charactersPresent.size()==1 || (charactersPresent.size()==2 && characterSelected().isElementalSummoned())) {
						return new ResponseEffectsOnly(
								characterSelected().isElemental()
									?"Dispel"
									:"Send home",
									UtilText.parse(characterSelected(), 
										characterSelected().isElemental()
											?"Dispel [npc.namePos] physical form, and return [npc.herHim] to your arcane aura."
											:"Tell [npc.name] to go home.")) {
							@Override
							public void effects() {
								if(characterSelected().isElementalSummoned()) {
									characterSelected().removeCompanion(characterSelected().getElemental());
									characterSelected().getElemental().returnToHome();
								}
								Main.game.getPlayer().removeCompanion(characterSelected());
								characterSelected().returnToHome();
//								Main.mainController.openCharactersPresent();
								Main.game.restoreSavedContent(false);
							}
						};
						
					} else {
						return new Response(
								characterSelected().isElemental()
									?"Dispel"
									:"Send home",
									UtilText.parse(characterSelected(), 
										characterSelected().isElemental()
											?"Dispel [npc.namePos] physical form, and return [npc.herHim] to your arcane aura."
											:"Tell [npc.name] to go home."),
								coreNode){
							@Override
							public void effects() {
								if(characterSelected().isElementalSummoned()) {
									characterSelected().removeCompanion(characterSelected().getElemental());
									characterSelected().getElemental().returnToHome();
								}
								Main.game.getPlayer().removeCompanion(characterSelected());
								characterSelected().returnToHome();

								Main.game.setResponseTab(0);
								CharactersPresentDialogue.resetContent(Main.game.getCharactersPresent().get(0));
							}
						};
					}
				}
				
			} else if(index==11) {
				if(Main.game.isSavedDialogueNeutral()) {
					return new Response("Combat moves", UtilText.parse(characterSelected(), "Adjust the moves [npc.name] can perform in combat."), CombatMovesSetup.COMBAT_MOVES_CORE) {
						@Override
						public void effects() {
							CombatMovesSetup.setTarget(characterSelected(), coreNode);
						}
					};
				} else {
					return new Response("Combat moves", "You're in the middle of something right now!"+getBusyWarning(), null);
				}
				
			} else if(index==12) {
				if(Main.game.isSavedDialogueNeutral()) {
					return new Response("Spells", UtilText.parse(characterSelected(), "Manage [npc.namePos] spells."), SpellManagement.CHARACTER_SPELLS_EARTH) {
						@Override
						public void effects() {
							SpellManagement.setTarget(characterSelected(), coreNode);
						}
					};
					
				} else {
					return new Response("Spells", "You're in the middle of something right now!"+getBusyWarning(), null);
				}
				
			} else if(index==13) {
				if(!characterSelected().isElementalSummoned()) {
					return new Response("Dispel elemental", UtilText.parse(characterSelected(), "[npc.Name] doesn't have an elemental summoned..."), null);
					
				} else {
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("Dispel elemental", "You're in the middle of something right now!"+getBusyWarning(), null);
						
					} else {
						return new Response("Dispel elemental", UtilText.parse(characterSelected(), "Tell [npc.name] to dispel [npc.her] elemental."), coreNode){
							@Override
							public void effects() {
								characterSelected().removeCompanion(characterSelected().getElemental());
								characterSelected().getElemental().returnToHome();
							}
						};
					}
				}
			}
			
		} else if(characterSelected()!=null && characterSelected().isSlave()) { // Slave not currently in the player's party:
			if (index == 1) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("Inspect", UtilText.parse(characterSelected(), "You are already taking a closer look at [npc.name]!"), null);
				}
				return new Response("Inspect", UtilText.parse(characterSelected(), "Take a closer look at [npc.name]!"), SLAVE_MANAGEMENT_INSPECT);
				
			} else if (index == 2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("Job", UtilText.parse(characterSelected(), "You are already setting [npc.namePos] job and work hours!"), null);
				}
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Job", "You cannot manage the job of a slave you do not own!", null);
				}
				return new Response("Job", "Set this slave's job and work hours.", SLAVE_MANAGEMENT_JOBS);
				
			} else if (index == 3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("Permissions", UtilText.parse(characterSelected(), "You are already setting [npc.namePos] permissions!"), null);
				}
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Permissions", "You cannot manage the permissions of a slave you do not own!", null);
				}
				return new Response("Permissions", "Set this slave's permissions.", SLAVE_MANAGEMENT_PERMISSIONS);
				
			} else if (index == 4) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Inventory", "You cannot manage the inventory of a slave you do not own!", null);
				}
				
				if(characterSelected().getOwner().isPlayer()) {
					return new ResponseEffectsOnly("Inventory", UtilText.parse(characterSelected(), "Manage [npc.namePos] inventory.")){
						@Override
						public void effects() {
							Main.mainController.openInventory(characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
				} else {
					return new Response("Inventory", UtilText.parse(characterSelected(), "You can't manage [npc.namePos] inventory, as you don't own [npc.herHim]!"), null);
				}
				
			} else if(index == 5) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Send to Kate", "You cannot send slaves which you do not own to Kate!", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
					return new Response("Send to Kate",
							UtilText.parse(characterSelected(), "Send [npc.name] to Kate's beauty salon, 'Succubi's secrets', to get [npc.her] appearance changed."),
							SLAVE_MANAGEMENT_COSMETICS_HAIR) {
								@Override
								public void effects() {
									BodyChanging.setTarget(characterSelected());
								}
							};
				} else {
					return new Response("Send to Kate", "You haven't met Kate yet!", null);
				}
				
			} else if (index == 6) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Perks", "You can't manage the perks of slaves that you do not own!", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(Reset perks)]",
							UtilText.parse(characterSelected(), "Reset all of [npc.namePos] perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("Perks", "Spend your slave's perk points.", SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("Transformations", characterSelected().getUnableToTransformDescription(), null);
					
				} else {
					return new Response("Transformations",
							UtilText.parse(characterSelected(), "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected(), coreNode, defaultResponseTab);
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("Set names", UtilText.parse(characterSelected(), "You are managing [npc.namePos] name!"), null);
				}
				if(characterSelected() == null) {
					return new Response("Set names", "You haven't selected anyone...", null);
				}
				return new Response("Set names", UtilText.parse(characterSelected(), "Change [npc.namePos] name or tell [npc.herHim] to call you by a different name."), OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10) {
				return new Response("Send home", UtilText.parse(characterSelected(), "[npc.Name] isn't in your party, so you can't send [npc.herHim] home..."), null);
				
			} else if(index==11) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("Combat moves", "You can't manage the combat moves of slaves that you do not own!", null);
				}
				return new Response("Combat moves", UtilText.parse(characterSelected(), "Adjust the moves [npc.name] can perform in combat."), CombatMovesSetup.COMBAT_MOVES_CORE) {
					@Override
					public void effects() {
						CombatMovesSetup.setTarget(characterSelected(), coreNode);
					}
				};
				
			} else if(index==12) {
				return new Response("Spells", UtilText.parse(characterSelected(), "Manage [npc.namePos] spells."), SpellManagement.CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						SpellManagement.setTarget(characterSelected(), coreNode);
					}
				};
				
			} else if(index==13) {
				if(!characterSelected().isElementalSummoned()) {
					return new Response("Dispel elemental", UtilText.parse(characterSelected(), "[npc.Name] doesn't have an elemental summoned..."), null);
					
				} else {
					return new Response("Dispel elemental", UtilText.parse(characterSelected(), "Tell [npc.name] to dispel [npc.her] elemental."), coreNode) {
						@Override
						public void effects() {
							characterSelected().removeCompanion(characterSelected().getElemental());
							characterSelected().getElemental().returnToHome();
						}
					};
				}
				
			} else if(index == 0) {
				if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
					return new Response("Back", "Return to the occupant list overview.", coreNode) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							coreNode = null;
						}
					};
				}
				return new Response("Leave", "Exit the occupant management screen.", Main.game.getDefaultDialogue());
			}
		
		} else { // Friendly occupant or null character not currently in the player's party:
			if (index == 1) {
				if(characterSelected() == null) {
					return new Response("Inspect", "You haven't selected anyone...", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("Inspect", UtilText.parse(characterSelected(), "You are already taking a closer look at [npc.name]!"), null);
				}
				return new Response("Inspect", UtilText.parse(characterSelected(), "Take a closer look at [npc.name]."), SLAVE_MANAGEMENT_INSPECT);
				
			} else if (index == 2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("Job", UtilText.parse(characterSelected(), "You are already setting [npc.namePos] job and work hours!"), null);
				}
				if(characterSelected() == null) {
					return new Response("Job", "You haven't selected anyone...", null);
				}
				return new Response("Job", "You cannot manage the job of a free-willed occupant. This option is only available for slaves.", null);
				
			} else if (index == 3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("Permissions", UtilText.parse(characterSelected(), "You are already setting [npc.namePos] permissions!"), null);
				}
				if(characterSelected() == null) {
					return new Response("Permissions", "You haven't selected anyone...", null);
				}
				return new Response("Permissions", "You cannot manage the permissions of a free-willed occupant. This option is only available for slaves.", null);
				
			} else if (index == 4) {
				if(characterSelected() == null) {
					return new Response("Inventory", "You haven't selected anyone...", null);
				}
				return new ResponseEffectsOnly("Inventory", UtilText.parse(characterSelected(), "Manage [npc.namePos] inventory.")){
					@Override
					public void effects() {
						Main.mainController.openInventory(characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if(index == 5) {
				if(characterSelected() == null) {
					return new Response("Send to Kate", "You haven't selected anyone...", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
					return new Response("Send to Kate",
							UtilText.parse(characterSelected(), "Send [npc.name] to Kate's beauty salon, 'Succubi's secrets', to get [npc.her] appearance changed."),
							SLAVE_MANAGEMENT_COSMETICS_HAIR) {
								@Override
								public void effects() {
									BodyChanging.setTarget(characterSelected());
								}
							};
				} else {
					return new Response("Send to Kate", "You haven't met Kate yet!", null);
				}
				
			} else if (index == 6) {
				if(characterSelected() == null) {
					return new Response("Perks", "You haven't selected anyone...", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(Reset perks)]",
							UtilText.parse(characterSelected(), "Reset all of [npc.namePos] perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("Perks", UtilText.parse(characterSelected(), "Assign [npc.namePos] perk points."), SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(characterSelected() == null) {
					return new Response("Transformations", "You haven't selected anyone...", null);
				}
				if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("Transformations", characterSelected().getUnableToTransformDescription(), null);
					
				} else {
					return new Response("Transformations",
							UtilText.parse(characterSelected(), "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected(), coreNode, defaultResponseTab);
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("Set names", UtilText.parse(characterSelected(), "You are managing [npc.namePos] name!"), null);
				}
				if(characterSelected() == null) {
					return new Response("Set names", "You haven't selected anyone...", null);
				}
				return new Response("Set names", UtilText.parse(characterSelected(), "Tell [npc.name] to call you by a different name."), OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10) {
				if(characterSelected() == null) {
					return new Response("Send home", "You haven't selected anyone...", null);
				}
				return new Response("Send home", UtilText.parse(characterSelected(), "[npc.Name] isn't in your party, so you can't send [npc.herHim] home..."), null);
				
			} else if(index==11) {
				if(characterSelected() == null) {
					return new Response("Combat moves", "You haven't selected anyone...", null);
				}
				return new Response("Combat moves", UtilText.parse(characterSelected(), "Adjust the moves [npc.name] can perform in combat."), CombatMovesSetup.COMBAT_MOVES_CORE) {
					@Override
					public void effects() {
						CombatMovesSetup.setTarget(characterSelected(), coreNode);
					}
				};
				
			} else if(index==12) {
				if(characterSelected() == null) {
					return new Response("Spells", "You haven't selected anyone...", null);
				}
				return new Response("Spells", UtilText.parse(characterSelected(), "Manage [npc.namePos] spells."), SpellManagement.CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						SpellManagement.setTarget(characterSelected(), coreNode);
					}
				};
				
			} else if(index==13) {
				if(characterSelected() == null) {
					return new Response("Dispel elemental", "You haven't selected anyone...", null);
				}
				if(!characterSelected().isElementalSummoned()) {
					return new Response("Dispel elemental", UtilText.parse(characterSelected(), "[npc.Name] doesn't have an elemental summoned..."), null);
					
				} else {
					return new Response("Dispel elemental", UtilText.parse(characterSelected(), "Tell [npc.name] to dispel [npc.her] elemental."), coreNode) {
						@Override
						public void effects() {
							characterSelected().removeCompanion(characterSelected().getElemental());
							characterSelected().getElemental().returnToHome();
						}
					};
				}
				
			} else if(index == 0) {
				if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
					return new Response("Back", "Return to the occupant list overview.", coreNode) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							coreNode = null;
						}
					};
				}
				return new Response("Leave", "Exit the occupant management screen.", Main.game.getDefaultDialogue());
			}
		}
		
		return null;
	}
	
	/**
	 * <b>Use getSlaveryManagementDetailedDialogue(NPC slave) to initialise this!!!</b>
	 */
	public static final DialogueNode SLAVE_MANAGEMENT_INSPECT = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "Inspecting [npc.Name]");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			
			UtilText.nodeContentSB.setLength(0);
			
			if(character.isSlave() && character.getOwner().isPlayer()) {
				UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			}
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
							+ "<h6 style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Inspection</h6>"
							+ "<div class='container-full-width inner'>"
								+character.getCharacterInformationScreen(false)
							+"</div>"
					+"</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_JOBS = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.NamePos] Job");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue());
			float affectionChange = character.getDailyAffectionChange();
			float obedienceChange = character.getDailyObedienceChange();
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Job hours
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>");
			
			UtilText.nodeContentSB.append(
							"<div class='container-full-width inner' style='text-align:center;'>"
							+ "<div style='width:100%;margin-top:8px;'><b>Available Jobs</b></div>");
								for(SlaveJob job : SlaveJob.values()) {
									UtilText.nodeContentSB.append(
											"<div class='normal-button' id='"+job+"_ASSIGN' style='width:16%; margin:2px;color:"
														+job.getColour().toWebHexString()+";"+(Main.game.getDialogueFlags().getSlaveryManagerJobSelected()==job?"border-color:"+job.getColour().toWebHexString()+";":"")+"'>"
													+Util.capitaliseSentence(job.getName(character))
											+"</div>");
								}
								
			UtilText.nodeContentSB.append("<div style='width:100%;margin-top:8px;'><b>Time Slots</b></div>");
			for(int i=0 ; i< 24; i++) {
				Colour c = character.getSlaveJob(i).getColour();
				boolean jobAvailable = Main.game.getDialogueFlags().getSlaveryManagerJobSelected().isAvailable(i, character);
				if(!jobAvailable) {
					UtilText.nodeContentSB.append(
							"<div class='normal-button hour disabled' style='background:"+c.getShades()[0]+";border-color:"+c.toWebHexString()+";color:"+c.getShades()[4]+";' id='"+i+"_WORK_DISABLED'>");
				} else {
					UtilText.nodeContentSB.append(
							"<div class='normal-button hour' style='background:"+c.getShades()[0]+";border-color:"+c.toWebHexString()+";color:"+c.getShades()[4]+";' id='"+i+"_WORK'>");
				}
				UtilText.nodeContentSB.append(String.format("%02d", i)+":00</div>");
			}
			float fatigue = character.getSlaveJobTotalFatigue();
			UtilText.nodeContentSB.append(
								"<div style='width:100%;margin-top:8px;'>"
//										+ "<b>Presets</b>"
									+"<i>Current daily fatigue: "+(fatigue<=0?"[style.colourGood(":"[style.colourBad(")+fatigue+")]</i>"
								+ "</div>");
								for(SlaveJobHours preset : SlaveJobHours.values()) {
									UtilText.nodeContentSB.append("<div class='normal-button' id='"+preset+"_TIME' style='width:16%; margin:2px;'>"+preset.getName()+"</div>");
								}
			UtilText.nodeContentSB.append(
							"</div>"
//						+ "</div>"
					+ "</div>");
			
			
			// Jobs:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Job Settings & Related Information</h6>"
						+"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Job"
							+ "</div>"
							+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b>Workers</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>Affection</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
							+"</div>"
							+ "<div style='float:left; width:40%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Income</b>"
										+ " (+<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience Bonus</b>)"
							+"</div>"
						+ "</div>");
			
			for(SlaveJob job : SlaveJob.values()) {
				affectionChange = job.getAffectionGain(Main.game.getHourOfDay(), character);
				obedienceChange = job.getObedienceGain(Main.game.getHourOfDay(), character);
				int income = job.getFinalHourlyIncomeAfterModifiers(character);
				boolean isCurrentJob = character.hasSlaveJobAssigned(job);
				
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' "+(isCurrentJob?"style='background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'":"")+">"
							+ "<div style='width:5%; float:left; margin:0; padding:0;'>"
								+ "<div class='title-button no-select' id='SLAVE_JOB_INFO_"+job+"' style='position:relative; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
							+ "</div>"
							+"<div style='width:15%; float:left; margin:0; padding:0;'>"
								+ (isCurrentJob
									? "<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(character))+"</b>"
									: "[style.colourDisabled("+Util.capitaliseSentence(job.getName(character))+")]")
							+ "</div>"
							+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
								+ Main.game.getPlayer().getTotalSlavesWorkingJob(job)+"/"+(job.getSlaveLimit()<0?"&#8734;":job.getSlaveLimit())
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ (affectionChange>0
										?"<b style='color:"+Colour.AFFECTION.toWebHexString()+";'>+"+decimalFormat.format(affectionChange)+ "</b>"
										:(affectionChange<0
												?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(affectionChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/hour"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ (obedienceChange>0
										?"<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>+"+decimalFormat.format(obedienceChange)+ "</b>"
										:(obedienceChange<0
												?"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(obedienceChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/hour"
							+"</div>"
							+ "<div style='float:left; width:40%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(job.getIncome())
								+ " + ("
								+ (job.getObedienceIncomeModifier()>0
										?"[style.colourObedience("+job.getObedienceIncomeModifier()+")]"
										:"[style.colourDisabled("+job.getObedienceIncomeModifier()+")]")
										+ "*<span style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+"</span>)"
								+ " = "+UtilText.formatAsMoney(income, "b", (income>0?null:Colour.GENERIC_BAD))+"/hour"
							+"</div>"
							);
				
				// Job Settings:
				for(SlaveJobSetting setting : job.getMutualSettings()) {
					boolean settingActive = character.hasSlaveJobSetting(job, setting);
					
					String id = settingActive
							?job.toString()+setting.toString()+"_REMOVE"
							:job.toString()+setting.toString()+"_ADD";
			
					UtilText.nodeContentSB.append(
							"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='width:23%; margin:1%; text-align:center;"
										+(settingActive?"border-color:"+job.getColour().toWebHexString()+";":"border-color:"+job.getColour().getShades()[0]+";")+"'>"
								+ (settingActive
										?"<span style='color:"+job.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
										:"[style.colourDisabled("+setting.getName()+")]")
							+ "</div>");
				}
				
				for(Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {
					UtilText.nodeContentSB.append("<div class='container-full-width inner' style='"+(!isCurrentJob?"background:#1B1B1B;":"")+"'>"
													+ "<div style='width:100%; float:left; margin:0; padding:0;"+(isCurrentJob?"":"color:#777;")+"'>"
													+ "<b>"+Util.capitaliseSentence(entry.getKey())+"</b>"
													+ "</div>");
					
					for(SlaveJobSetting setting : entry.getValue()) {
						boolean settingActive = character.hasSlaveJobSetting(job, setting);
						

						String id = settingActive
								?setting.toString()+"_DISABLED"
								:setting.toString()+"_TOGGLE_ADD";
				
						UtilText.nodeContentSB.append(
								"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='width:23%; margin:1%; text-align:center;"
											+(settingActive?"border-color:"+job.getColour().toWebHexString()+";":"")+"'>"
									+ (settingActive
											?"<span style='color:"+job.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
											:"[style.colourDisabled("+setting.getName()+")]")
								+ "</div>");
					}
					UtilText.nodeContentSB.append("</div>");
				}
				
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_PERMISSIONS = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Permissions");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Permissions:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Permissions</h6>");
			
			for(SlavePermission permission : SlavePermission.values()) {
				UtilText.nodeContentSB.append("<div class='container-full-width inner' style='box-sizing:border-box; position:relative; width:98%; margin:4px 1%; background:"+Colour.BACKGROUND_ALT.toWebHexString()+";'>");
				
				// Job Settings:
				for(SlavePermissionSetting setting : permission.getSettings()) {
					boolean settingActive = character.getSlavePermissionSettings().get(permission).contains(setting);
					
					String id = (permission.isMutuallyExclusiveSettings()
									?(settingActive?setting+"_REMOVE_ME":setting+"_ADD")
									:(settingActive?setting+"_REMOVE":setting+"_ADD"));
					
					String style = "width:23%; margin:1%;";
					if(permission.getSettings().size()==5) {
						style = "width:18%; margin:1%;"; // These settings can fit 5 on a line
					}
					
					UtilText.nodeContentSB.append(
							"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='"+style+"text-align:center;"
										+(settingActive
											?"border-color:"+permission.getColour().toWebHexString()+";"
											:(permission.isMutuallyExclusiveSettings()
													?""
													:"border-color:"+permission.getColour().getShades()[0]+";"))+"'>"
								+ (settingActive
										?"<span style='color:"+permission.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
										:"[style.colourDisabled("+setting.getName()+")]")
							+ "</div>");
				}
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append("</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	

	private static Map<BodyCoveringType, List<String>> CoveringsNamesMap;
	
	private static Response getCosmeticsResponse(int responseTab, int index) {
		if (index == 1) {
			if(!BodyChanging.getTarget().getBodyMaterial().isAbleToWearMakeup()) {
				return new Response("Makeup", UtilText.parse(BodyChanging.getTarget(), "As [npc.namePos] body is made of "+Main.game.getPlayer().getBodyMaterial().getName()+", Kate is unable to apply any makeup!"), null);
				
			} else {
				return new Response("Makeup",
						"Kate offers a wide range of different cosmetic services, and several pages of the brochure are devoted to images displaying different styles and colours of lipstick, nail polish, and other forms of makeup.",
						SLAVE_MANAGEMENT_COSMETICS_MAKEUP);
			}
			
		} else if (index == 2) {
			return new Response("Hair",
					"There's a double-page spread of all the different dyes, styles, and lengths of hair that Kate's able to work with.",
					SLAVE_MANAGEMENT_COSMETICS_HAIR);

		} else if (index == 3) {
				return new Response("Piercings",
						"Kate offers a wide range of different piercings.",
						SLAVE_MANAGEMENT_COSMETICS_PIERCINGS);

		}  else if (index == 4) {
				return new Response("Eyes",
						"There's a special page near the front of the brochure, advertising Kate's ability to recolour a person's eyes."
						+ " Just like skin recolourings, this is quite demanding on her aura, and is therefore very expensive.", SLAVE_MANAGEMENT_COSMETICS_EYES);

		} else if (index == 5) {
			return new Response("Coverings",
					"There's a special page in the middle of the brochure, advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
					+ " Apparently, this is quite demanding on her aura, and is therefore very expensive.",
					SLAVE_MANAGEMENT_COSMETICS_COVERINGS){
				@Override
				public void effects() {
					
					CoveringsNamesMap = new LinkedHashMap<>();

					if(BodyChanging.getTarget().getBodyMaterial()==BodyMaterial.SLIME) {
						CoveringsNamesMap.put(BodyCoveringType.SLIME, Util.newArrayListOfValues("SLIME"));
					} else {
						for(BodyPartInterface bp : BodyChanging.getTarget().getAllBodyParts()){
							if(bp.getBodyCoveringType(BodyChanging.getTarget())!=null
									&& !(bp instanceof Hair)
									&& !(bp instanceof Eye)) {
								
								String name = bp.getName(BodyChanging.getTarget());
								if(bp instanceof Skin) {
									name = "torso";
								} else if(bp instanceof Vagina) {
									name = "vagina";
								}
								
								if(CoveringsNamesMap.containsKey(bp.getBodyCoveringType(BodyChanging.getTarget()))) {
									CoveringsNamesMap.get(bp.getBodyCoveringType(BodyChanging.getTarget())).add(name);
								} else {
									CoveringsNamesMap.put(bp.getBodyCoveringType(BodyChanging.getTarget()), Util.newArrayListOfValues(name));
								}
							}
						}
						CoveringsNamesMap.put(BodyCoveringType.ANUS, Util.newArrayListOfValues("anus"));
						CoveringsNamesMap.put(BodyCoveringType.MOUTH, Util.newArrayListOfValues("mouth"));
						CoveringsNamesMap.put(BodyCoveringType.NIPPLES, Util.newArrayListOfValues("nipples"));
						CoveringsNamesMap.put(BodyCoveringType.TONGUE, Util.newArrayListOfValues("tongue"));
						if(BodyChanging.getTarget().hasBreastsCrotch()) {
							CoveringsNamesMap.put(BodyCoveringType.NIPPLES_CROTCH, Util.newArrayListOfValues("crotch nipples"));
						}
					}
				}
			};

		} else if (index == 6) {
			return new Response("Other", "Kate can offer other miscellaneous services, such as anal bleaching.", SLAVE_MANAGEMENT_COSMETICS_OTHER);

		} else if (index == 7) {
			return new Response("Tattoos", "Most of the brochure is taken up with drawings and photographs displaying Kate's considerable artistic talents."
					+ " She's even able to apply arcane-enchanted tattoos, but they look to be very expensive...", SLAVE_MANAGEMENT_TATTOOS);

		} else if (index == 0) {
			return new Response("Back", "Return to the slave management screen.", coreNode) {
				@Override
				public void effects() {
					Main.game.setResponseTab(defaultResponseTab);
				}
			};

		} else {
			return null;
		}
	}
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_MAKEUP = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Makeup");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on [npc.namePos] [npc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on [npc.namePos] [npc.feet].", true, true)
					);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Makeup",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] makeup!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_HAIR = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Hair");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivHairLengths(true, "Hair Length", "Hair length determines what hair styles [npc.namePos] able to have. The longer [npc.her] [npc.hair], the more styles are available.")

					+CharacterModificationUtils.getKatesDivHairStyles(true, "Hair Style", "Hair style availability is determined by [npc.namePos] [npc.hair] length.")
					
					+(BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.SLIME
						?CharacterModificationUtils.getKatesDivCoveringsNew(
								true, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(),
								UtilText.parse(BodyChanging.getTarget(), "[npc.Hair] Colour"),
								"All hair recolourings are permanent, so if you want to change your colour again at a later time, you'll have to visit Kate again.", true, true)
						:"")
					);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2) {
				return new Response("Hair",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] hair!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_PIERCINGS = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Piercings");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"

				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.EAR, "Ear Piercing", "Ears are the most common area of the body that are pierced, and enable the equipping of earrings and other ear-related jewellery.")

				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NOSE, "Nose Piercing", "Having a nose piercing will allow [npc.name] to equip jewellery such as nose rings or studs.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.LIP, "Lip Piercing", "A lip piercing will allow [npc.name] to wear lip rings.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NAVEL, "Navel Piercing", "Getting [npc.her] navel (belly button) pierced will allow [npc.name] to equip navel-related jewellery.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.TONGUE, "Tongue Piercing", "Getting a tongue piercing will allow [npc.name] to equip tongue bars.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NIPPLE, "Nipple Piercing", "Nipple piercings will allow [npc.name] to equip nipple bars.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.PENIS, "Penis Piercing", "Having a penis piercing will allow [npc.name] to equip penis-related jewellery.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.VAGINA, "Vagina Piercing", "Having a vagina piercing will allow [npc.name] to equip vagina-related jewellery."));
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3) {
				return new Response("Piercings",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] piercings!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_EYES = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Eyes");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyChanging.getTarget().getEyeCovering(), "Irises", "The iris is the coloured part of the eye that's responsible for controlling the diameter and size of the pupil.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.EYE_PUPILS, "Pupils", "The pupil is a hole located in the centre of the iris that allows light to strike the retina.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.EYE_SCLERA, "Sclera", "The sclera is the (typically white) part of the eye that surrounds the iris.", true, true));
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 4) {
				return new Response("Eyes",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] eyes!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_COVERINGS = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Coverings");
		}
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>"
									+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
								+ "</h6>");
			
			for(Entry<BodyCoveringType, List<String>> entry : CoveringsNamesMap.entrySet()){
				BodyCoveringType bct = entry.getKey();
				
				String title = Util.capitaliseSentence(bct.getName(BodyChanging.getTarget()));
				String description = "This is the "+bct.getName(BodyChanging.getTarget())+" that's currently covering [npc.namePos] "+Util.stringsToStringList(entry.getValue(), false)+".";
				
				if(bct == BodyCoveringType.ANUS) {
					title = "Anus";
					description = "This is the skin that's currently covering [npc.namePos] anal rim. The secondary colour determines what [npc.her] anus's inner-walls look like.";
					
				} else if(bct == BodyCoveringType.VAGINA) {
					title = "Vagina";
					description = "This is the skin that's currently covering [npc.namePos] labia. The secondary colour determines what [npc.her] vagina's inner-walls look like.";
					
				} else if(bct == BodyCoveringType.PENIS) {
					title = "Penis";
					description = "This is the skin that's currently covering [npc.namePos] penis. The secondary colour determines what the inside of [npc.her] urethra looks like (if it's fuckable).";
					
				} else if(bct == BodyCoveringType.NIPPLES) {
					title = "Nipples";
					description = "This is the skin that's currently covering [npc.namePos] nipples and areolae. The secondary colour determines what [npc.her] nipples' inner-walls look like (if they are fuckable).";
					
				} else if(bct == BodyCoveringType.NIPPLES_CROTCH) {
					title = "Crotch Nipples";
					description = "This is the skin that's currently covering the nipples and areolae on [npc.namePos] [npc.crotchBoobs]. The secondary colour determines what [npc.her] nipples' inner-walls look like (if they are fuckable).";
					
				} else if(bct == BodyCoveringType.MOUTH) {
					title = "Lips & Throat";
					if(BodyChanging.getTarget().getFaceType() == FaceType.HARPY) {
						description = "This is the colour of [npc.namePos] beak. The secondary colour determines what the insides of [npc.her] mouth and throat look like.";
					} else {
						description = "This is the skin that's currently covering [npc.namePos] lips. The secondary colour determines what the insides of [npc.her] mouth and throat look like.";
					}
					
				} else if(bct == BodyCoveringType.TONGUE) {
					title = "Tongue";
					description = "This is the skin that's currently covering [npc.namePos] tongue.";
				}
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true, 
						bct,
						title,
						description,
						true,
						true));
			}
			
			
			return UtilText.parse(BodyChanging.getTarget(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5) {
				return new Response("Coverings",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] coverings!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_OTHER = new DialogueNode("Slave Management", ".", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name] - Other");
		}
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivAnalBleaching("Anal bleaching", "Anal bleaching is the process of lightening the colour of the skin around the anus, to make it more uniform with the surrounding area.")

					+(Main.game.isFacialHairEnabled()
							? CharacterModificationUtils.getKatesDivFacialHair(true, "Facial hair", "The body hair found on [npc.namePos] face." 
									+ (Main.game.isFemaleFacialHairEnabled() ? "" : " Feminine characters cannot grow facial hair."))
							:"")
					
					+(Main.game.isPubicHairEnabled()
							?CharacterModificationUtils.getKatesDivPubicHair(true, "Pubic hair", "The body hair found in the genital area; located on and around [npc.namePos] sex organs and crotch.")
							:"")
					
					+(Main.game.isBodyHairEnabled()
							?CharacterModificationUtils.getKatesDivUnderarmHair(true, "Underarm hair", "The body hair found in [npc.namePos] armpits.")
							:"")
					
					+(Main.game.isAssHairEnabled()
							?CharacterModificationUtils.getKatesDivAssHair(true, "Ass hair", "The body hair found around [npc.namePos] asshole.")
							:"")
					);
			
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				if((Main.game.isFacialHairEnabled() && BodyChanging.getTarget().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && BodyChanging.getTarget().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() && BodyChanging.getTarget().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && BodyChanging.getTarget().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, bct, "Body hair", "Your body hair.", true, true));
					
				}
			}
			
			return UtilText.parse(BodyChanging.getTarget(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("Other",
						UtilText.parse(BodyChanging.getTarget(), "You are already changing [npc.namePos] other features!"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_TATTOOS = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoos();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 7) {
				return new Response("Tattoos",
						UtilText.parse(BodyChanging.getTarget(), "You are already managing [npc.namePos] tattoos!"),
						null);
				
			} else if(index==11) {
				return new Response("Confirmations: ",
						"Toggle tattoo removal confirmations."
							+ " When turned on, it will take two clicks to remove tattoos."
							+ " When turned off, it will only take one click.",
							SLAVE_MANAGEMENT_TATTOOS) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)
									?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
									:"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.tattooRemovalConfirmations, !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations));
						Main.getProperties().savePropertiesAsXML();
					}
				};
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_TATTOOS_ADD = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getLabel() {
			return "Succubi's Secrets - "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getName()) +" Tattoo";
		}
		
		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoosAdd();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")",
							UtilText.parse(BodyChanging.getTarget(), "You don't have enough money to give [npc.name] a tattoo!"),  null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.NONE)
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "You need to select a tattoo type, add some writing, or add a counter in order to make a tattoo!", null);
					
				} else {
					return new Response("Apply ("+UtilText.formatAsMoney(value, "span")+")", 
							UtilText.parse(BodyChanging.getTarget(), "Pay Kate to give [npc.name] this tattoo!"), SLAVE_MANAGEMENT_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							BodyChanging.getTarget().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==0) {
				return new Response("Back", "Decide not to get this tattoo and return to the main selection screen.", SLAVE_MANAGEMENT_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_PERKS = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.NamePos] Perks");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(characterSelected(),
					"<details>"
							+ "<summary>[style.boldPerk(Perk & Trait Information)]</summary>"
							+ "[style.colourPerk(Perks)] (circular icons) apply permanent boosts to [npc.namePos] attributes.<br/>"
							+ "[style.colourPerk(Traits)] (square icons) provide unique effects for [npc.name]."
								+ " Unlike perks, <b>traits will have no effect on [npc.name] until they're slotted into [npc.her] 'Active Traits' bar</b>.<br/>"
							+ "Perks require perk points to unlock. [npc.Name] earns one perk point each time [npc.she] levels up, and earns an extra two perk points every five levels.<br/><br/>"
							+ "In addition to the perks that can be purchased via perk points, there are also several special, hidden perks that are unlocked via special events."
					+ "</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(characterSelected(), true));
			
			UtilText.nodeContentSB.append("</div>");
			
			if(!(characterSelected().isElemental())) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'>"
							+ "<i>Please note that this perk tree is a work-in-progress. This is not the final version, and is just a proof of concept!</i>"
						+ "</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode OCCUPANT_CHOOSE_NAME = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(characterSelected().isSlave()) {

				UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
					"<p>"
						+ "At the moment, [npc.nameIsFull] calling you '[npc.pcName]', and you wonder if you should get [npc.her] to call you by a different name or title."
						+ " As [npc.sheIs] your slave, you could also change [npc.her] name to whatever you'd like it to be..."
					+ "</p>"));
				
				UtilText.nodeContentSB.append(
					"<div class='container-full-width inner' style='padding:8px;'>"
						+ "<div style='width:50%; float:left; font-weight:bold; margin:0; padding:0; text-align:center;'>"
							+ "Name and Surname"
						+ "</div>"
						+ "<div style='width:50%; float:left; font-weight:bold; margin:0; padding:0; text-align:center;'>"
							+ UtilText.parse(characterSelected(), "What [npc.she] calls you")
						+ "</div>"
						+ "<div style='width:49%; float:left; font-weight:bold; margin:0 1% 0 0; padding:0;'>"
							+ "<form style='float:left; width:39%; margin:0; padding:0;'><input type='text' id='slaveNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form>"
							+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME' style='float:left; width:9%; height:28px; line-height:28px; margin:0 1% 0 1%; padding:0; text-align:center;'>"
								+ "&#10003;"
							+ "</div>"
							+ "<form style='float:left; width:40%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form>"
							+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME_SURNAME' style='float:left; width:9%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0; text-align:center;'>"
								+ "&#10003;"
							+ "</div>"
						+ "</div>"
						+ "<div style='width:49%; float:left; font-weight:bold; margin:0 0 0 1%; padding:0;'>"
							+ "<form style='float:left; width:50%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getPetName(Main.game.getPlayer()))
								+ "' style='width:100%; margin:0; padding:0;'></form>"
							+ "<div class='normal-button' id='"+characterSelected().getId()+"_CALLS_PLAYER' style='float:left; width:9%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0; text-align:center;'>"
								+ "&#10003;"
							+ "</div>"
							+ " <div class='normal-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:39%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0; text-align:center;'>"
								+ "All Slaves"
							+ "</div>"
						+ "</div>");
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
						"<p>"
							+ "At the moment, [npc.nameIsFull] calling you '[npc.pcName]', and you wonder if you should get [npc.her] to call you by a different name or title."
							+ " As [npc.sheIs] not your slave, you can't get [npc.herHim] to change [npc.her] name."
						+ "</p>"));

				UtilText.nodeContentSB.append(
					"<div class='container-full-width inner' style='padding:8px;'>"
						+ "<div style='width:50%; float:left; font-weight:bold; margin:0; padding:0; text-align:center;'>"
							+ "Name and Surname"
						+ "</div>"
						+ "<div style='width:50%; float:left; font-weight:bold; margin:0; padding:0; text-align:center;'>"
							+ UtilText.parse(characterSelected(), "What [npc.she] calls you")
						+ "</div>"
						+ "<div style='width:49%; float:left; font-weight:bold; margin:0 1% 0 0; padding:0;'>"
							+ "<form style='float:left; width:39%; margin:0; padding:0;'><input type='text' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getName(false))+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
							+ "<div class='normal-button disabled' style='float:left; width:9%; height:28px; line-height:28px; margin:0 1% 0 1%; padding:0; text-align:center;'>"
								+ "&#10003;"
							+ "</div>"
							+ "<form style='float:left; width:40%; margin:0; padding:0;'><input type='text' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getSurname())+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
							+ "<div class='normal-button disabled' style='float:left; width:9%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0; text-align:center;'>"
								+ "&#10003;"
							+ "</div>"
						+ "</div>"
						+ "<div style='width:49%; float:left; font-weight:bold; margin:0 0 0 1%; padding:0;'>"
							+ "<form style='float:left; width:50%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getPetName(Main.game.getPlayer()))
								+ "' style='width:100%; margin:0; padding:0;'></form>"
							+ "<div class='normal-button' id='"+characterSelected().getId()+"_CALLS_PLAYER' style='float:left; width:9%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0; text-align:center;'>"
								+ "&#10003;"
							+ "</div>"
							+ " <div class='normal-button disabled' style='float:left; width:39%; height:28px; line-height:28px; margin:0 0 0 1%; padding:0; text-align:center;'>"
								+ "All Slaves"
							+ "</div>"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
						"<div class='container-full-width inner'>"
							+ "<i>If [npc.name] is told to call you 'Mom' or 'Dad', 'Mommy' or 'Daddy', 'Mistress' or 'Master', or 'Ma'am' or 'Sir',"
							+ " then [npc.she] will automatically switch to the appropriate paired name depending on the femininity of your character.</i>"
						+ "</div>"
					+ "</div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
	};
}
