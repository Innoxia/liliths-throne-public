package com.lilithsthrone.game.dialogue.companions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobFlag;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

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
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) {
			Main.game.saveDialogueNode();
		}
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
	
	public static DialogueNode getSlaveryManagementSlaveCosmeticsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_COSMETICS_MAKEUP;
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
							+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Affection</b>"
						+"</div>"
						+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
						+"</div>"
						+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Income</b>"
						+"</div>"
						+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "Value"
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width inner' style='margin-top:0; border-radius: 0 5px 5px; 0'>"
						+"<div style='width:30%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+character.getLocationPlace().getColour().toWebHexString()+";'>"+character.getLocationPlace().getName()+"</b>"
							+",<br/>"
							+ "<span style='color:"+character.getWorldLocation().getColour().toWebHexString()+";'>"+character.getWorldLocation().getName()+"</span>"
						+ "</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+character.getAffection(Main.game.getPlayer())+ "</b>"
							+ "<br/><span style='color:"+(affectionChange==0?PresetColour.BASE_GREY:(affectionChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/day"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?PresetColour.BASE_GREY:(obedienceChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
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
		if(characterSelected().isSlave()) {
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
		}
		
		
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
				if(!characterSelected().hasJob()) {
					return new Response("Job", "Set this character's job and work hours.", SLAVE_MANAGEMENT_JOBS);
				}
				return new Response("Job", "You cannot manage the job of an employed resident. This option is only available for slaves & unemployed residents.", null);

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
					if(charactersPresent.size()==1 || (charactersPresent.size()==2 && characterSelected().isElementalSummoned() && !Main.game.getPlayer().isElementalSummoned())) {
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
							SpellManagement.setSpellOwner(characterSelected(), coreNode);
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
								characterSelected().getElemental().returnToHome();
								characterSelected().setElementalSummoned(false);
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
				
			} else if(index==10 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
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
						SpellManagement.setSpellOwner(characterSelected(), coreNode);
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
				
			} else if(index==14) {
				if(!Main.game.getPlayer().hasItemType("innoxia_slavery_freedom_certification")) {
					return new Response("Set free",
							UtilText.parse(characterSelected(),
									"You do not have a Freedom Certification, so you cannot set [npc.name] free..."
									+ "<br/><i>Freedom Certifications can be purchased from Finch in Slaver Alley's Slaver Administration building.</i>"),
							null);
					
				} else {
					if(characterSelected() instanceof Scarlett) {
						return new Response("Set free",
								UtilText.parse(characterSelected(),
										"Fill out a Freedom Certification and set [npc.name] free!"
										+ "<br/>If you do this, [npc.she] will undoubtedly immediately leave and return to Helena's nest..."),
								SET_SLAVE_FREE_SCARLETT);
					}
					if(characterSelected() instanceof Brax) {
						return new Response("Set free",
								UtilText.parse(characterSelected(),
										"You cannot set [npc.name] free...<br/><i>(This will be added later when I add more content for Brax!)</i>"),
								null);
					}
					
					String unavailableGuestDescription = "";
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						unavailableGuestDescription = "[style.italicsMinorBad(As you do not have Lilaya's permission to house guests, you will be unable to invite [npc.name] to stay here in the mansion after freeing [npc.herHim]!)]";
						
					} else if(!characterSelected().isAffectionHighEnoughToInviteHome()) {
						unavailableGuestDescription = "[style.italicsBad([npc.Name] does not like you enough to consider staying with you after being set free!)]";
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						unavailableGuestDescription = "[style.italicsMinorBad(As you do not have a free guest room for [npc.name] to move in to, you will be unable to invite [npc.herHim] to stay here in the mansion after freeing [npc.herHim]!)]";
					}
					String thanksjava = unavailableGuestDescription;
					
					return new Response("Set free",
							UtilText.parse(characterSelected(),
									"Fill out a Freedom Certification and set [npc.name] free!"
									+ "<br/>"
									+ (thanksjava.isEmpty()
										?"[style.italicsMinorGood([npc.Name] likes you enough to accept an offer of staying here to live with you, so after freeing [npc.herHim] you can offer to let [npc.herHim] stay in one of the free guest rooms!)]"
										:thanksjava+"<br/>[style.italicsBad(This will permanently remove [npc.herHim] (and everything in [npc.her] inventory) from the game!)]")),
							SET_SLAVE_FREE) {
						@Override
						public Colour getHighlightColour() {
							if(thanksjava.isEmpty()) {
								return PresetColour.GENERIC_MINOR_GOOD;
							}
							return PresetColour.GENERIC_NPC_REMOVAL;
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
				return new Response("Leave", "Exit the occupant management screen.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
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
				if(!characterSelected().hasJob()) {
					return new Response("Job", "Set this occupant's temporary job and work hours.", SLAVE_MANAGEMENT_JOBS);
				}
				return new Response("Job", "This occupant already has a permanent job.", null);
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
				
			} else if(index==10 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
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
						SpellManagement.setSpellOwner(characterSelected(), coreNode);
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
				return new Response("Leave", "Exit the occupant management screen.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
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
							+ "<h6 style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>Inspection</h6>"
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
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Job hours
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>");
			
			UtilText.nodeContentSB.append(
							"<div class='container-full-width inner' style='text-align:center;'>"
							+ "<div style='width:100%;margin-top:8px;'><b>Available Jobs</b></div>");
								for(SlaveJob job : SlaveJob.values()) {
									if(character.isSlave() || job.hasFlag(SlaveJobFlag.GUEST_CAN_WORK)) {
										UtilText.nodeContentSB.append(
												"<div class='normal-button' id='"+job+"_ASSIGN' style='width:16%; margin:2px;color:"
														+job.getColour().toWebHexString()+";"+(Main.game.getDialogueFlags().getSlaveryManagerJobSelected()==job?"border-color:"+job.getColour().toWebHexString()+";":"")+"'>"
														+Util.capitaliseSentence(job.getName(character))
														+"</div>");
									}
								}
								
			UtilText.nodeContentSB.append("<div style='width:100%;margin-top:8px;'><b>Time Slots</b></div>");
			float stamina = character.getDailySlaveJobStamina();
			SlaveJob jobSelected = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
			for(int i=0 ; i< 24; i++) {
				Colour colour = character.getSlaveJob(i).getColour();
				Colour borderColour = colour;
				Colour backgroundColour = colour;
				String background = "background:"+backgroundColour.getShades()[0]+";";
//				if(character.isSleepingAtHour(i)) {
////					colour = PresetColour.BASE_PURPLE_DARK;
////					background = "background:"+PresetColour.BASE_PURPLE.getShades()[0]+";";
////					String c1 = backgroundColour.getShades()[0];
////					String c2 = PresetColour.BASE_PURPLE_LIGHT.getShades()[0];
////					background = "background: repeating-linear-gradient(135deg, "+c1+", "+c1+" 10px, "+c2+" 10px, "+c2+" 20px);";
//				}
				if(!jobSelected.isAvailable(i, character)
						|| (!character.isSlave()
						&& stamina-jobSelected.getHourlyStaminaDrain()+character.getSlaveJob(i).getHourlyStaminaDrain()<0f)
						&& !jobSelected.equals(SlaveJob.IDLE)) { // Always allow idle job
					UtilText.nodeContentSB.append(
							"<div class='normal-button hour disabled' style='"+background+"border-color:"+borderColour.toWebHexString()+";color:"+colour.getShades()[4]+";' id='"+i+"_WORK_DISABLED'>");
				} else {
					UtilText.nodeContentSB.append(
							"<div class='normal-button hour' style='"+background+"border-color:"+borderColour.toWebHexString()+";color:"+colour.getShades()[4]+";' id='"+i+"_WORK'>");
				}
				UtilText.nodeContentSB.append(String.format("%02d", i)+":00");
				if(character.isSleepingAtHour(i)) { // Sleeping indication via 'zzZ'
					String stroke = "text-shadow:"
									+ "1px 1px 0 #000,"
									+ "-1px -1px 0 #000, "
									+ "1px -1px 0 #000,"
									+ "-1px 1px 0 #000,"
									+ "1px 1px 0 #000;";
					UtilText.nodeContentSB.append("<b class='hotkey-icon' style='color:"+PresetColour.SLEEP.toWebHexString()+";"+stroke+"'><span style='font-size:0.8em;'>z</span>zZ</b>");
				}
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append(
								"<div style='width:100%;margin-top:8px;'>"
									+"<i>[style.colourStamina(Current daily stamina:)] "+(stamina>=0?"[style.colourGood(":"[style.colourBad(")+stamina+")]/"+SlaveJob.BASE_STAMINA+"</i>"
								+ "</div>");
								for(SlaveJobHours preset : SlaveJobHours.values()) {
									boolean jobDisabled = false;
									boolean resetI = false;
									for (int i = preset.getStartHour(); i<preset.getStartHour()+preset.getLength(); i++) {
										if (i>23) {
											i = i-24; // Wrap around to 0
											resetI = true;
										}
										if (!jobSelected.isAvailable(i, character)) {
											jobDisabled = true;
											break;
										}
										if (resetI) {
											i = i+24; // Reset i to maintain the loop
										}
									}
									if ((!character.isSlave() && jobSelected.getHourlyStaminaDrain()*preset.getLength()>stamina) || jobDisabled) {
										UtilText.nodeContentSB.append("<div class='normal-button disabled' id='"+preset+"_TIME_DISABLED' style='width:16%; margin:2px;'>"+preset.getName()+"</div>");
									} else {
										UtilText.nodeContentSB.append("<div class='normal-button' id='"+preset+"_TIME' style='width:16%; margin:2px;'>"+preset.getName()+"</div>");
									}
								}
			UtilText.nodeContentSB.append(
							"</div>"
//						+ "</div>"
					+ "</div>");
			
			
			// Jobs:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>"
						+ "<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Job Settings & Related Information</h6>"
						+"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div style='width:20%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Job"
							+ "</div>"
							+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b>Workers</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>Affection</b>"
							+"</div>"
							+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
							+"</div>"
							+ "<div style='float:left; width:40%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Income</b>"
										+ " (+<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience Bonus</b>)"
							+"</div>"
						+ "</div>");
			
			for(SlaveJob job : SlaveJob.values()) {
				if(!character.isSlave() && !job.hasFlag(SlaveJobFlag.GUEST_CAN_WORK)) {
					continue;
				}
				float affectionChange = job.getAffectionGain(character);
				float obedienceChange = job.getObedienceGain(character);
				int income = job.getFinalHourlyIncomeAfterModifiers(character);
				boolean isCurrentJob = character.hasSlaveJobAssigned(job);
				
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' "+(isCurrentJob?"style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"")+">"
							+ "<div style='width:5%; float:left; margin:0; padding:0;'>"
								+ "<div class='title-button no-select' id='SLAVE_JOB_INFO_"+job+"' style='position:relative; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
							+ "</div>"
							+"<div style='width:15%; float:left; margin:0; padding:0;'>"
								+ (isCurrentJob
									? "<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(character))+"</b>"
									: "[style.colourDisabled("+Util.capitaliseSentence(job.getName(character))+")]")
							+ "</div>"
							+ "<div style='float:left; width:10%; font-weight:bold; margin:0; padding:0;'>"
								+ Main.game.getOccupancyUtil().getTotalCharactersWorkingJob(job)+"/"+(job.getSlaveLimit()<0?"&#8734;":job.getSlaveLimit())
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ (affectionChange>0
										?"<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>+"+decimalFormat.format(affectionChange)+ "</b>"
										:(affectionChange<0
												?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(affectionChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/hour"
							+"</div>"
							+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
								+ (obedienceChange>0
										?"<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>+"+decimalFormat.format(obedienceChange)+ "</b>"
										:(obedienceChange<0
												?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(obedienceChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/hour"
							+"</div>"
							+ "<div style='float:left; width:40%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(job.getIncome())
								+ " + ("
								+ (job.getObedienceIncomeModifier()>0
										?"[style.colourObedience("+job.getObedienceIncomeModifier()+")]"
										:"[style.colourDisabled("+job.getObedienceIncomeModifier()+")]")
										+ "*<span style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+"</span>)"
								+ " = "
								+(income>0
									?UtilText.formatAsMoney(income, "b")
									:UtilText.formatAsMoney(income, "b", PresetColour.GENERIC_BAD))
								+"/hour"
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

//			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>");
//			UtilText.nodeContentSB.append(  "<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Additional Book-Keeping Information</h6>");
//			UtilText.nodeContentSB.append(  "<p>Optional extra information you may add for this slave for book-keeping purposes.</p>");
//			UtilText.nodeContentSB.append(  "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(    "<h7>Category</h7>");
//			UtilText.nodeContentSB.append(    "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(      "<input id='SET_SLAVE_CATEGORY' type='text' value='"+UtilText.parseForHTMLDisplay(character.getSlaveCategory())+"'/>");
//			UtilText.nodeContentSB.append(    "</div>");
//			UtilText.nodeContentSB.append(  "</div>");
//			UtilText.nodeContentSB.append(  "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(    "<h7>Notes</h7>");
//			UtilText.nodeContentSB.append(    "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(      "<form style='padding:0;margin:0;text-align:center;'>");
//			UtilText.nodeContentSB.append(        "<textarea id='SET_SLAVE_NOTES' style='width:760px;height:200px;'>"+character.getSlaveNotes()+"</textarea>");
//			UtilText.nodeContentSB.append(      "</form>");
//			UtilText.nodeContentSB.append(    "</div>");
//			UtilText.nodeContentSB.append(  "</div>");
//			UtilText.nodeContentSB.append("</div>");
			
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
						+ "<h6 style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>Permissions</h6>");
			
			for(SlavePermission permission : SlavePermission.values()) {
				UtilText.nodeContentSB.append("<div class='container-full-width inner' style='box-sizing:border-box; position:relative; width:98%; margin:4px 1%; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>");
				
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
	
	private static Response getCosmeticsResponse(int responseTab, int index) {
		if (index == 1) {
			if(!BodyChanging.getTarget().isAbleToWearMakeup()) {
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
					SuccubisSecrets.initCoveringsMap(BodyChanging.getTarget());
				}
			};

		} else if (index == 6) {
			return new Response("Other", "Kate can offer other miscellaneous services, such as anal bleaching.", SLAVE_MANAGEMENT_COSMETICS_OTHER);

		} else if (index == 7) {
			return new Response("Tattoos", "Most of the brochure is taken up with drawings and photographs displaying Kate's considerable artistic talents."
					+ " She's even able to apply arcane-enchanted tattoos, but they look to be very expensive...", SLAVE_MANAGEMENT_TATTOOS);

		} else if (index == 0) {
			if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
				return new Response("Back", "Return to the occupant list overview.", coreNode) {
					@Override
					public void effects() {
						Main.game.setResponseTab(defaultResponseTab);
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
			}
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
							true, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on [npc.namePos] [npc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on [npc.namePos] [npc.feet].", true, true)
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
					+CharacterModificationUtils.getKatesDivHairLengths(true, "Hair Length", "Hair length determines what hair styles [npc.namePos] able to have. The longer [npc.her] [npc.hair(true)], the more styles are available.")

					+CharacterModificationUtils.getKatesDivHairStyles(true, "Hair Style", "Hair style availability is determined by [npc.namePos] [npc.hair(true)] length.")
					
					+(BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.SLIME
						?CharacterModificationUtils.getKatesDivCoveringsNew(
								true, BodyChanging.getTarget().getHairType().getRace(), BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(),
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
					
				+CharacterModificationUtils.getKatesDivPiercings(false));
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
							true, BodyChanging.getTarget().getEyeType().getRace(), BodyChanging.getTarget().getEyeCovering(),
							"Irises", "The iris is the coloured part of the eye that's responsible for controlling the diameter and size of the pupil.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(BodyChanging.getTarget().getBodyMaterial(), BodyCoveringCategory.EYE_PUPIL)
								:BodyCoveringType.EYE_PUPILS,
							"Pupils", "The pupil is a hole located in the centre of the iris that allows light to strike the retina.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(BodyChanging.getTarget().getBodyMaterial(), BodyCoveringCategory.EYE_SCLERA)
								:BodyCoveringType.EYE_SCLERA,
							"Sclera", "The sclera is the (typically white) part of the eye that surrounds the iris.", true, true));
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
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();
				GameCharacter target = BodyChanging.getTarget();

				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(target, bct, entry.getValue().getValue());
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(target, titleDescription.getValue()),
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
					+CharacterModificationUtils.getKatesDivAnalBleaching()

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
			
			for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
				if((Main.game.isFacialHairEnabled() && BodyChanging.getTarget().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && BodyChanging.getTarget().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() && BodyChanging.getTarget().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && BodyChanging.getTarget().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, bct, "Body hair", "Your body hair.", true, true));
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
									?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
									:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
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
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
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
						+ "At the moment, [npc.nameIsFull] calling you '[npc.pcName]', and you wonder if you should get [npc.herHim] to call you by a different name or title."
						+ " As [npc.sheIs] your slave, you could also change [npc.her] name to whatever you'd like it to be..."
					+ "</p>"));
				
				UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='padding:8px 16px;'>"
						+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "Name"
						+ "</div>"
						+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "Surname"
						+ "</div>"
						+ "<div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(characterSelected(), "What [npc.she] calls you")
						+ "</div>"
						
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
							
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
						
						+ "<form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ " <div class='normal-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:12%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "All Slaves"
						+ "</div>");
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
						"<p>"
							+ "At the moment, [npc.nameIsFull] calling you '[npc.pcName]', and you wonder if you should get [npc.herHim] to call you by a different name or title."
							+ " As [npc.sheIs] not your slave, you can't get [npc.herHim] to change [npc.her] name."
						+ "</p>"));
				

				UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='padding:8px 16px;'>"
						+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "Name"
						+ "</div>"
						+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "Surname"
						+ "</div>"
						+ "<div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(characterSelected(), "What [npc.she] calls you")
						+ "</div>"
						
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getName(false))+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
						+ "<div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
							
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getSurname())+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
						+ "<div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
						
						+ "<form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ " <div class='normal-button disabled' style='float:left; width:12%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "All Slaves"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
						"<p style='text-align:center; margin-top:4px;'>"
							+ "<i>If [npc.name] is told to call you 'Mom' or 'Dad', 'Mommy' or 'Daddy', 'Mistress' or 'Master', or 'Ma'am' or 'Sir',"
							+ " then [npc.she] will automatically switch to the appropriate paired name depending on the femininity of your character.</i>"
						+ "</p>"
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
	
	private static boolean isFreedSlaveAvailableAsGuest() {
		return characterSelected().isAffectionHighEnoughToInviteHome()
				&& Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)
				&& OccupancyUtil.isFreeRoomAvailableForOccupant();
	}

	public static final DialogueNode SET_SLAVE_FREE_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeSlave(characterSelected());
			characterSelected().getPetNameMap().remove(Main.game.getPlayer().getId());// Reset pet name
			Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
			
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
			((Scarlett)Main.game.getNpc(Scarlett.class)).resetName();
			((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();

			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_SCARLETT", characterSelected()));
			Main.game.getDialogueFlags().setManagementCompanion(null);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue",
						"With Scarlett having run off, there's nothing more for you to do except continue with your day...",
						Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	private static boolean freedSlaveDeleted;
	
	public static final DialogueNode SET_SLAVE_FREE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_slavery_freedom_certification"));
			Main.game.getPlayer().removeSlave(characterSelected());
			characterSelected().setEnslavementDialogue(SlaveDialogue.FREEDOM_DIALOG, false);
			if(!isFreedSlaveAvailableAsGuest()) {
				freedSlaveDeleted = true;
				if(!characterSelected().isAffectionHighEnoughToInviteHome()) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_DISLIKE", characterSelected()));
					
				} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION) || !OccupancyUtil.isFreeRoomAvailableForOccupant()) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_NO_GUEST", characterSelected()));
					
				}
				Main.game.banishNPC(characterSelected());
				Main.game.getDialogueFlags().setManagementCompanion(null);
				
			} else {
				freedSlaveDeleted = false;
				characterSelected().getPetNameMap().remove(Main.game.getPlayer().getId());// Reset pet name
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GUEST_CHOICE", characterSelected()));
				Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(freedSlaveDeleted) {
				if(index == 1) {
					return new Response("Continue",
							"Now that your slave has been freed and left your life for good, there's little else for you to do except continue with your other plans for the day...",
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
						}
					};
				}
			} else {
				if(index == 1) {
					return new Response("Offer room",
							UtilText.parse(characterSelected(), "Ask [npc.name] if [npc.she] would like to stay in the mansion as a guest."),
							SET_SLAVE_FREE_GUEST_ROOM) {
						@Override
						public void effects() {
							Cell c = OccupancyUtil.getFreeRoomForOccupant();
							characterSelected().setLocation(c.getType(), c.getLocation(), true);
							Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
							Main.game.getPlayer().addFriendlyOccupant(characterSelected());
							Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
						}
					};
					
				} else if(index == 2) {
					return new Response("Goodbye",
							UtilText.parse(characterSelected(), "Say goodbye to [npc.name]...<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
							SET_SLAVE_FREE_END_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GOODBYE", characterSelected()));
							Main.game.banishNPC(characterSelected());
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
					
				} else if(index == 3) {
					return new Response("Throw out",
							UtilText.parse(characterSelected(), "Call for Rose to unceremoniously throw [npc.name] out of the mansion...<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]"),
							SET_SLAVE_FREE_END_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_THROWN_OUT", characterSelected()));
							Main.game.banishNPC(characterSelected());
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				
			}
			return null;
		}
	};
	
	public static final DialogueNode SET_SLAVE_FREE_END_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SET_SLAVE_FREE_GUEST_ROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GUEST_ROOM", characterSelected());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", UtilText.parse(characterSelected(), "Having left [npc.name] to get settled into [npc.her] new room, you continue with your plans for the day..."), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setManagementCompanion(null);
					}
				};
			}
			return null;
		}
	};
	
	
}
