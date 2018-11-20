package com.lilithsthrone.game.dialogue;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.npcDialogue.unique.LumiDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.ParserCommand;
import com.lilithsthrone.game.dialogue.utils.ParserTarget;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public class DebugDialogue {

	public static final DialogueNodeOld DEBUG_MENU = new DialogueNodeOld("A powerful tool", "Open debug menu.", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>"
					+ "As you finish speaking the magic word, you suddenly hear a little thudding noise close behind you."
					+ " Spinning around, you see a small metallic device lying on the floor, which sort of resembles a t.v. remote from back in your old world."
					+ "</p>"

					+ "<p>"
					+ "You lean down and pick it up, and as you turn it over in your hands, you see a little label stuck to the back."
					+ " Someone's written a message on it, and you read the following:" 
					+ "</p>"

					+ "<p style='text-align:center;'><i>"
					+ "Hi " + Main.game.getPlayer().getName() + "!<br/>"
					+ "It looks like you know about the magic debug code! Just to give you a warning, all the options here are really buggy!"
					+ " If you spawn in any clothing or items, just be aware that some of them aren't officially in the game just yet, so they may not work exactly as expected."
					+ " Thanks for playing!<br/><br/>"
					+ "~Innoxia~<br/></i>"
					+ "</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "Main";
				
			} else if(index == 1) {
				return "Stats";
				
			} else if(index == 2) {
				return "Misc.";
				
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Back", "", DEBUG_MENU){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			}
			
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Open parser", "Test the parser.", PARSER);
					
				} else if (index == 2) {
					return new Response("Debug mode: ", "Unlocks enchanting and slavery without first having to do the associated quests.", DEBUG_MENU){
						@Override
						public String getTitle() {
							return "Debug mode: "+(Main.game.isDebugMode()?"[style.colourGood(ON)]":"[style.colourDisabled(OFF)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.debugMode, !Main.game.isDebugMode());
						}
					};
					
				} else if (index == 3) {
					return new Response("Reveal Maps: ", "Reveals all map tiles.", DEBUG_MENU){
						@Override
						public String getTitle() {
							return "Reveal Maps: "+(Main.game.isMapReveal()?"[style.colourGood(ON)]":"[style.colourDisabled(OFF)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.mapReveal, !Main.game.isMapReveal());
						}
					};
					
				} else if (index == 4) {
					return new Response("Reveal bodies: ", "When toggled on, clothing does not conceal inventory slots, and you'll know what all character's genitals look like without first having to see them.", DEBUG_MENU){
						@Override
						public String getTitle() {
							return "Reveal bodies: "+(Main.game.isConcealedSlotsReveal()?"[style.colourGood(ON)]":"[style.colourDisabled(OFF)]");
						}
						
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.concealedSlotsReveal, !Main.game.isConcealedSlotsReveal());
						}
					};
					
				} else if(index==5 && Main.DEBUG) {
					return new Response("All items", "View icons of all the clothing, weapon, and items in the game. <i>Warning: Very sluggish and slow to load.</i>", ALL_ITEMS_VIEW);
					
				}  else if (index == 6) {
					return new Response("Spawn Menu", "View the clothing, weapon, and item spawn menu.", SPAWN_MENU);
					
				} else if (index == 7) {
					return new Response("Transform", "Transform your body.", BodyChanging.BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer(), true);
						}
					};
					
				} else if (index == 8) {
					return new Response("Set body material", "Set your body material.", BODY_PART_MATERIAL);
					
				} else if (index == 9) {
					return new Response("Race resets", "View the race reset options.", BODY_PART_RACE_RESET);
					
				} else if (index == 11) {
					return new Response(UtilText.formatAsMoney(10000, "span"), "Add 10000 flames.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(10000);
						}
					};
					
				} else if (index == 12) {
					return new Response("+50 essences", "Add 50 arcane essences.", DEBUG_MENU){
						@Override
						public void effects() {
							for(TFEssence essence : TFEssence.values()) {
								Main.game.getPlayer().incrementEssenceCount(essence, 50, false);
							}
						}
					};
					
				}
				
			} else if(responseTab==1) {
				if (index == 1) {
					return new Response("<span style='color:"+Colour.GENERIC_EXPERIENCE.toWebHexString()+";'>+500 xp</span>", "Give yourself 500xp.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementExperience(500, false);
							
						}
					};
					
				} else if(index==2) {
					return new Response("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+";'>Physique</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, 5);
						}
					};
					
				} else if(index==3) {
					return new Response("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+Colour.ATTRIBUTE_ARCANE.toWebHexString()+";'>Arcane</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, 5);
						}
					};
					
				} else if(index==4) {
					return new Response("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+Colour.ATTRIBUTE_CORRUPTION.toWebHexString()+";'>Corruption</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, 5);
						}
					};
					
				} else if(index==5) {
					return new Response("<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Max all attributes</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_ARCANE, 100);
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, 100);
						}
					};
					
				}  else if(index==6) {
					return new Response("<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>+1</span> <span style='color:"+Colour.PERK.toWebHexString()+";'>Perk point</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().setPerkPoints(Main.game.getPlayer().getPerkPoints()+1);
						}
					};
					
				} else if(index==7) {
					return new Response("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+";'>Physique</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, -5);
						}
					};
				} else if(index==8) {
					return new Response("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+Colour.ATTRIBUTE_ARCANE.toWebHexString()+";'>Arcane</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, -5);
						}
					};
				} else if(index==9) {
					return new Response("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+Colour.ATTRIBUTE_CORRUPTION.toWebHexString()+";'>Corruption</span>", "", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, -5);
						}
					};
					
				} else if (index == 11) {
					return new Response("Reset spells", "Resets all of your spells and upgrades, and removes all of your spell upgrade points.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().resetSpells();
							Main.game.getPlayer().clearSpellUpgradePoints();
							
						}
					};
					
				} else if (index == 12) {
					return new Response("+10 Spell Points", "Add 10 spell points to each spell school.", DEBUG_MENU){
						@Override
						public void effects() {
							for(SpellSchool school : SpellSchool.values()) {
								Main.game.getPlayer().incrementSpellUpgradePoints(school, 10);
							}
						}
					};
					
				}
				
			} else if(responseTab==2) {
				if (index == 1) {
					return new Response("Test colours", "Test text for readability", COLOURS){
						@Override
						public void effects() {
							coloursSB = new StringBuilder("<p>");
							for (Colour c : Colour.values())
								coloursSB.append(c + ": <span style='color:" + c.toWebHexString() + ";'>Test text for readability.</span><br/>");
							coloursSB.append("<br/><br/>");
							for (BaseColour bc : BaseColour.values())
								coloursSB.append(bc + ": <span style='color:" + bc.toWebHexString() + ";'>Test text for readability.</span><br/>");
							coloursSB.append("</p>");
							
						}
					};
					
				} else if (index == 2) {
					return new Response("Offspring", "View available offspring", OFFSPRING);
					
				} else if (index == 3) {
					return new Response("[style.boldBad(Month -)]", "Reduce current month by 1.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.setStartingDateMonth(Main.game.getStartingDate().getMonth().minus(1));
							
						}
					};
					
				} else if (index == 4) {
						return new Response("[style.boldGood(Month +)]", "Increase current month by 1.", DEBUG_MENU){
							@Override
							public void effects() {
								Main.game.setStartingDateMonth(Main.game.getStartingDate().getMonth().plus(1));
								
							}
						};
						
				}  else if (index == 5) {
					if(Main.game.getPlayer().getLocationPlace().getPlaceType()!=PlaceType.DOMINION_BACK_ALLEYS) {
						return new Response("Lumi test", "Lumi can only be spawned in alleyway tiles.", null);
						
					} else if(!Main.game.getNonCompanionCharactersPresent().isEmpty()) {
						return new Response("Lumi test", "Lumi can only be spawned into empty tiles!", null);
						
					}  else if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
						return new Response("Lumi test", "Lumi can not be spawned during an arcane storm.", null);
					}
					return new ResponseEffectsOnly("Lumi test", "Spawn Lumi to test her dialogue and scenes."){
						@Override
						public void effects() {
							Main.game.setContent(new Response("", "", LumiDialogue.LUMI_APPEARS));
						}
					};
					
				} else if (index == 6) {
					return new Response("Brax's revenge", "Brax cums in your vagina!", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().ingestFluid(Main.game.getBrax(), Main.game.getBrax().getCum(), SexAreaOrifice.VAGINA, 1000);
						}
					};
					
				}
			}
			
			return null;
		}
	};
	private static StringBuilder coloursSB;
	public static final DialogueNodeOld COLOURS = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return coloursSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	private static NPC activeOffspring = null;
	
	public static final DialogueNodeOld OFFSPRING = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			for(NPC npc : Main.game.getOffspring()) {
				if(npc.isFeminine()) {
					UtilText.nodeContentSB.append(npc.getName()+" "+npc.getMother().getName()+"'s daughter ("+npc.getRace().getName()+") Father:"+npc.getFather().getName()+" Mother:"+npc.getMother().getName()+"<br/>");
				} else {
					UtilText.nodeContentSB.append(npc.getName()+" "+npc.getFather().getName()+"'s son ("+npc.getRace().getName()+") Father:"+npc.getFather().getName()+" Mother:"+npc.getMother().getName()+"<br/>");
				}
			}
			if(activeOffspring!=null) {
				for(Fetish f : activeOffspring.getFetishes()) {
					UtilText.nodeContentSB.append("<br/>[style.boldSex(Fetish:)] "+f.getName(activeOffspring));
				}
				UtilText.nodeContentSB.append(
						"<br/>" + activeOffspring.getDescription()
						+"<br/>" + activeOffspring.getBodyDescription());
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else if(index-1 < Main.game.getOffspring().size()) {
				return new Response(Main.game.getOffspring().get(index-1).getName(), "View the character page for this offspring.", OFFSPRING) {
					@Override
					public void effects() {
						activeOffspring = Main.game.getOffspring().get(index-1);
						for(CoverableArea ca : CoverableArea.values()) {
							activeOffspring.setAreaKnownByCharacter(ca, Main.game.getPlayer(), true);
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static List<AbstractClothingType> clothingTotal = new ArrayList<>();
	public static InventorySlot activeSlot = null;
	public static ItemTag itemTag = null;
	public static int spawnCount = 1;
	static {
		clothingTotal.addAll(ClothingType.getAllClothing());
	}
	public static List<AbstractWeaponType> weaponsTotal = new ArrayList<>();
	static {
		weaponsTotal.addAll(WeaponType.allweapons);
	}
	public static List<AbstractItemType> itemsTotal = new ArrayList<>();
	static {
		for (AbstractItemType c : ItemType.getAllItems()) {
			if(!c.getItemTags().contains(ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {
				itemsTotal.add(c);
			}
		}
	}
	private static StringBuilder inventorySB = new StringBuilder();
	public static final DialogueNodeOld SPAWN_MENU = new DialogueNodeOld("Spawn Menu", "Access the spawn menu.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			inventorySB.setLength(0);
			
			inventorySB.append("<div class='container-half-width'>");

			inventorySB.append(
					"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
						+ (activeSlot==null ?
								"<b style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>Spawn Item</b>"
								:(activeSlot == InventorySlot.WEAPON_MAIN || activeSlot == InventorySlot.WEAPON_OFFHAND
									? "<b style='color:"+Colour.BASE_RED_LIGHT.toWebHexString()+";'>Spawn Weapon</b> ("+Util.capitaliseSentence(activeSlot.getName())+")"
									: "<b style='color:"+Colour.BASE_YELLOW_LIGHT.toWebHexString()+";'>Spawn Clothing</b> ("+Util.capitaliseSentence(activeSlot.getName())+")"))
					+"</p>");
			
			int count=0;
			inventorySB.append("<div class='inventory-not-equipped'>");
			if(activeSlot == null) {
				for(AbstractItemType itemType : itemsTotal) {
					if((itemTag==null
							&& (!itemType.getItemTags().contains(ItemTag.BOOK)
							&& !itemType.getItemTags().contains(ItemTag.ESSENCE)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_BOOK)
							&& !itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)))
							|| (itemTag!=null
								&& (itemType.getItemTags().contains(itemTag)
										|| (itemTag==ItemTag.SPELL_BOOK && itemType.getItemTags().contains(ItemTag.SPELL_SCROLL))))) {
						inventorySB.append("<div class='inventory-item-slot unequipped "+ itemType.getRarity().getName() + "'>"
												+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
												+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div>"
											+ "</div>");
					}
					count++;
				}
				
			} else if(activeSlot == InventorySlot.WEAPON_MAIN || activeSlot == InventorySlot.WEAPON_OFFHAND) {
				for(AbstractWeaponType weaponType : weaponsTotal) {
					if((weaponType.isMelee() && activeSlot==InventorySlot.WEAPON_MAIN)
							|| (!weaponType.isMelee() && activeSlot==InventorySlot.WEAPON_OFFHAND)) {
						inventorySB.append("<div class='inventory-item-slot unequipped "+ weaponType.getRarity().getName() + "'>"
												+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage(
														weaponType.getAvailableDamageTypes().get(0),
														weaponType.getAvailablePrimaryColours().isEmpty()?null:weaponType.getAvailablePrimaryColours().get(0),
														weaponType.getAvailableSecondaryColours().isEmpty()?null:weaponType.getAvailableSecondaryColours().get(0))
												+"</div>"
												+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div>"
											+ "</div>");
						count++;
					}
				}
				
			} else {
				for(AbstractClothingType clothingType : clothingTotal) {
					if(clothingType.getSlot()==activeSlot) {
						inventorySB.append("<div class='inventory-item-slot unequipped "+ clothingType.getRarity().getName() + "'>"
												+ "<div class='inventory-icon-content'>"
													+clothingType.getSVGImage(
														clothingType.getAllAvailablePrimaryColours().get(0),
														clothingType.getAvailableSecondaryColours().isEmpty()?null:clothingType.getAvailableSecondaryColours().get(0),
														clothingType.getAvailableTertiaryColours().isEmpty()?null:clothingType.getAvailableTertiaryColours().get(0),
														null, null, null, null)
												+"</div>"
												+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div>"
											+ "</div>");
						count++;
					}
				}
			}
			
			// Fill space:
			for (int i = count; i <48; i++) {
				inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
			}
			inventorySB.append("</div>"
					+ "</div>");
			
			inventorySB.append("<div class='container-half-width'>");
			for(InventorySlot slot : InventorySlot.values()) {
				inventorySB.append("<div class='normal-button' id='"+slot+"_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"
						+ (slot == InventorySlot.WEAPON_MAIN || slot == InventorySlot.WEAPON_OFFHAND ? Colour.BASE_RED_LIGHT.toWebHexString() : Colour.BASE_YELLOW_LIGHT.toWebHexString())+";'>"
						+(slot == InventorySlot.WEAPON_MAIN
							?"Melee"
							:(slot == InventorySlot.WEAPON_OFFHAND
									?"Ranged"
									:Util.capitaliseSentence(slot.getName())))
						+"</div>");
			}
			inventorySB.append("<div class='normal-button' id='ITEM_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>Items</div>");
			inventorySB.append("<div class='normal-button' id='ESSENCE_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Essences</div>");
			inventorySB.append("<div class='normal-button' id='BOOK_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+Colour.BASE_ORANGE.toWebHexString()+";'>Books</div>");
			inventorySB.append("<div class='normal-button' id='SPELL_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+Colour.DAMAGE_TYPE_SPELL.toWebHexString()+";'>Spells</div>");
			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ALL_ITEMS_VIEW = new DialogueNodeOld("", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			inventorySB.setLength(0);
			
			inventorySB.append(
					"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
						+ (activeSlot==null ?
								"<b style='color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>Spawn Item</b>"
								:(activeSlot == InventorySlot.WEAPON_MAIN || activeSlot == InventorySlot.WEAPON_OFFHAND
									? "<b style='color:"+Colour.BASE_RED_LIGHT.toWebHexString()+";'>Spawn Weapon</b> ("+Util.capitaliseSentence(activeSlot.getName())+")"
									: "<b style='color:"+Colour.BASE_YELLOW_LIGHT.toWebHexString()+";'>Spawn Clothing</b> ("+Util.capitaliseSentence(activeSlot.getName())+")"))
					+"</p>");
			
			int count=0;
			inventorySB.append("<div class='inventory-not-equipped'>");
			for(AbstractItemType itemType : itemsTotal) {
				if((itemTag==null
						&& (!itemType.getItemTags().contains(ItemTag.BOOK)
						&& !itemType.getItemTags().contains(ItemTag.ESSENCE)
						&& !itemType.getItemTags().contains(ItemTag.SPELL_BOOK)
						&& !itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)))
						|| (itemTag!=null
							&& (itemType.getItemTags().contains(itemTag)
									|| (itemTag==ItemTag.SPELL_BOOK && itemType.getItemTags().contains(ItemTag.SPELL_SCROLL))))) {
					inventorySB.append("<div class='inventory-item-slot unequipped "+ itemType.getRarity().getName() + "' style='width:5%'>"
											+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
											+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div>"
										+ "</div>");
				}
				count++;
			}
			
			for(AbstractWeaponType weaponType : weaponsTotal) {
				inventorySB.append("<div class='inventory-item-slot unequipped "+ weaponType.getRarity().getName() + "' style='width:5%'>"
										+ "<div class='inventory-icon-content'>"+weaponType.getSVGImage(
												weaponType.getAvailableDamageTypes().get(0),
												weaponType.getAvailablePrimaryColours().isEmpty()?null:Util.randomItemFrom(weaponType.getAvailablePrimaryColours()),
												weaponType.getAvailableSecondaryColours().isEmpty()?null:Util.randomItemFrom(weaponType.getAvailableSecondaryColours()))
										+"</div>"
										+ "<div class='overlay' id='" + weaponType.getId() + "_SPAWN'></div>"
									+ "</div>");
				count++;
			}
			
			System.out.println(clothingTotal.size());
			for(AbstractClothingType clothingType : clothingTotal) {
				inventorySB.append("<div class='inventory-item-slot unequipped "+ clothingType.getRarity().getName() + "' style='width:5%'>"
										+ "<div class='inventory-icon-content'>"
											+clothingType.getSVGImage(
												Util.randomItemFrom(clothingType.getAvailablePrimaryColours()),
												clothingType.getAvailableSecondaryColours().isEmpty()?null:Util.randomItemFrom(clothingType.getAvailableSecondaryColours()),
												clothingType.getAvailableTertiaryColours().isEmpty()?null:Util.randomItemFrom(clothingType.getAvailableTertiaryColours()),
												null, null, null, null)
										+"</div>"
										+ "<div class='overlay' id='" + clothingType.getId() + "_SPAWN'></div>"
									+ "</div>");
				count++;
			}
			
			// Fill space:
			for (int i = count; i <48; i++) {
				inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
			}
			inventorySB.append("</div>");
			
			
			return inventorySB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return DEBUG_MENU.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEBUG_MENU.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld BODY_PART_MATERIAL = new DialogueNodeOld("Set body material", "Set body material.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a material type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < BodyMaterial.values().length+1) {
				return new Response(Util.capitaliseSentence(BodyMaterial.values()[index - 1].getName()), "Set your body to be made out of "+BodyMaterial.values()[index - 1].getName()+".", BODY_PART_MATERIAL){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setBodyMaterial(BodyMaterial.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_RACE_RESET = new DialogueNodeOld("Reset body", "Set race.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Select one of the races to reset your body to the default values of that race. (i.e. Regenerate your current body as that of a different race.)"
					+ "</p>"
					+ "<p>"
						+ "[style.colourTfPartial(Partial)]: Sets body to human, with selected race's antennae, eyes, ears, hair, horns, tail, and wings.</br>"
						+ "[style.colourTfMinor(Minor)]: Same as partial, but also includes ass, breasts, penis, and vagina.</br>"
						+ "[style.colourTfLesser(Lesser)]: Same as minor, but also includes arms and legs.</br>"
						+ "[style.colourTfGreater(Greater)]: Sets all parts to the race's.</br>"
					+ "</p>";
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "[style.colourTfPartial(Partial)]";

			} else if(index == 1) {
				return "[style.colourTfMinor(Minor)]";
				
			} else if(index == 2) {
				return "[style.colourTfLesser(Lesser)]";
				
			} else if(index == 3) {
				return "[style.colourTfGreater(Greater)]";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			
			if (index != 0 && index < Subspecies.values().length) {
				String name = Subspecies.values()[index - 1].getName(Main.game.getPlayer());
				return new Response(
						Util.capitaliseSentence(name),
						"Set your body as that of "+UtilText.generateSingularDeterminer(name)+" "+name+".",
						BODY_PART_RACE_RESET){
					@Override
					public void effects() {
						CharacterUtils.reassignBody(Main.game.getPlayer(), Main.game.getPlayer().getBody(), Main.game.getPlayer().getGender(), Subspecies.values()[index - 1],
								responseTab==0
									?RaceStage.PARTIAL
									:(responseTab==1
										?RaceStage.PARTIAL_FULL
										:(responseTab==2
											?RaceStage.LESSER
											:RaceStage.GREATER)));
						Main.game.getTextEndStringBuilder().append(
								"<p>"
										+ "[style.boldTfGeneric(Transformed:)] You are now "+UtilText.generateSingularDeterminer(name)+" "+name+"!"
								+ "</p>");
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};
	
	
	
	
	
	public static final DialogueNodeOld CLOTHING_COLLAGE = new DialogueNodeOld("Clothing collage", "Clothing collage.", false) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return clothingCollage();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};

	private static StringBuilder clothingCollageSB;

	private static String clothingCollage() {
		clothingCollageSB = new StringBuilder("<div style='position:inline-block;width:90vw;float:left;'>");

		for (AbstractClothingType c : ClothingType.getAllClothing()) {
			AbstractClothing ac = AbstractClothingType.generateClothing(c);
			clothingCollageSB.append("<html><div style='width:10vw;height:10vw;float:left;all: unset;'>" + ac.getSVGString() + "</div></html>");
		}

		clothingCollageSB.append("</div>");

		return clothingCollageSB.toString();
	}

	private static String parsedText = "", rawText = "";
	public static final DialogueNodeOld PARSER = new DialogueNodeOld("Parser", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			return ("<p>"
					+ "<b>Please</b> at least skim over the help page before viewing the 'commands' pages! (The number of commands could be off-putting if you're not aware of how simple they really are.)"
					+ "</p>"
					+ "<p>"
					+ "<b>This parser accepts html formatting.</b>"
					+ "</p>"

					+ "<p style='padding:0;margin:0;text-align:center;'>Parse:</p>"
					+ "<form style='padding:0;margin:0;text-align:center;'>"
					+ "<textarea id='parseInput' name='Text1' style='width:760px;height:200px;'>"+rawText+"</textarea>"
					+ "</form>");
		}
		
		@Override
		public String getContent() {
			return  "<p>"
					+ "<b>Output:</b>"
					+ "</p>"
					+ "<p>"+parsedText+"</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Parse!", "Parse the text you've entered.", PARSER){
					@Override
					public void effects() {
						rawText = (String) Main.mainController.getWebEngine().executeScript("document.getElementById('parseInput').value");
						parsedText = UtilText.parse(rawText);
					}
				};
				
			} else if (index == 2) {
				return new Response("Help", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("Targets", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("Commands", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("Commands list", "", PARSER_COMMANDS);
				
			} else if (index == 6) {
				return new Response("Load example 1", "", PARSER){
					@Override
					public void effects() {
						rawText = "You see [lilaya.name] sitting at one of the tables in [lilaya.herHis] lab, playing around with an inert demonstone."
								+ " [rose.name] is sitting beside [lilaya.herPro], gazing up lovingly into [lilaya.namePos] [lilaya.eyes+] as [rose.her] [rose.tail+] gently swishes back and forth behind [rose.herPro]."
								+ "\n<br/><br/>\n"
								+ "Unable to concentrate with [lilaya.her] slave hovering so close at hand, [lilaya.name] places the demonstone down onto the table, before wrapping [lilaya.her] [lilaya.arms+] around [rose.namePos] back and pulling"
								+ " [rose.herPro] into [lilaya.her] lap. [rose.name] lets out a happy little cry as [rose.her] master finally starts giving [rose.herPro] the attention [rose.she] was craving, and as [lilaya.namePos] hand slips down"
								+ " under [rose.her] dress, [rose.she] starts moaning in delight.";
					}
				};
				
			} else if (index == 7) {
				return new Response("Load example 2", "", PARSER){
					@Override
					public void effects() {
						rawText = "[brax.name] paces back and forth in [brax.herHis] office, growling softly to [brax.himself], [brax.speech(Hrmph... My new poster still hasn't arrived...)]";
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNodeOld PARSER_HELP = new DialogueNodeOld("Innoxia's super fun and interesting guide to parsing", "", true) {
		private static final long serialVersionUID = 1L;

		/*
		 * I've seen String concatenation... String concatenation that you've seen.
		 * But you have no right to call me a bad programmer.
		 * You have a right to ridicule my code. You have a right to do that... but you have no right to judge me.
		 * 
		 * It's impossible for words to describe what is necessary to those who do not know what String concatenation means.
		 * String concatenation... String concatenation has a face... and you must make a friend of String concatenation. String concatenation and html parsing are your friends. If they are not, then they are enemies to be feared.
		 */
		@Override
		public String getHeaderContent() {
			return  "<p style='text-align:center;'><i>You put in the input, and it returns a nice output!</i></p>"
					
					+ "<p>"
					+ "<h6>Input:</h6><br/>"
					+"Everything is parsed using square brackets, split into the following pattern:<br/>"
					+"[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>]<br/>"
					+"or, for the few special commands that require arguments,<br/>"
					+"[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
							+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]<br/>"
					+"or, for parsing as a script,<br/>"
					+"[#<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>]<br/>"
					+ "</p>"
					
					+ "<p>"
					+"An example of use in a sentence would be:<br/><br/>"
					+"As you start to read Innoxia's tedious parsing documentation, [lilaya.name] steps up behind you and wraps [lilaya.her] [lilaya.tail+] around your [pc.leg]."
					+" Leaning in over your shoulder, [lilaya.she] groans, [lilaya.speech(Oh my God. This is so boring, [#pc.getName()]!)]'<br/><br/>"
					+ "parses to:<br/><br/>"
					+ UtilText.parse("As you start to read Innoxia's tedious parsing documentation, [lilaya.name] steps up behind you and wraps [lilaya.her] [lilaya.tail+] around your [pc.leg]."
							+ " Leaning in over your shoulder, [lilaya.she] groans, [lilaya.speech(Oh my God. This is so boring, [#pc.getName()]!)]")
					+ "</p>"
					+ "<br/>"
					
					
					+"<h6><b style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>Target</b> <b>tag:</b></h6>"
					+"<p>"
					+"The target of a command is an NPC's name, or 'pc' for the player character. Target tags are <b>case-insensitive.</b> (i.e. pc is treated the same as PC, pC, or Pc)<br/>"
					+" If an unrecognised name is passed, the output will read 'INVALID_TARGET_NAME'.<br/>"
					+" Currently accepted target tags can be viewed in the 'Targets' page.<br/>"
					+ "e.g.:<br/>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.command]<br/>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>pc</i>.command(arguments)]<br/>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>npc</i>.command]"
					+ "</p>"
					+ "<br/>"
					
					
					+"<h6><b style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>Command</b> <b>and</b> <b style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>argument</b> <b>tags:</b></h6>"
					+"<p>"
					+"Command tags determine what output is returned. They come in two varieties; with and without arguments.<br/>"
					+"Arguments are passed inside brackets that follow the command, ignoring any spaces that you may insert.<br/>"
					+ "i.e. [pc.command(arguments)] is the same as [pc.command   (arguments)].<br/>"
					+"Command tags are <b>only case-sensitive for the first letter</b>. (i.e. command is treated the same as cOMMAND, cOmMaNd, or commanD)<br/>"
					+ "Arguments are specific to each command, and you'll have to refer to the command documentation to find out what arguments a command takes. (Don't worry, there aren't many that take arguments.)<br/>"
					+ "e.g.:<br/>"
					+ "[pc.speech<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(Hello reader!)</i>] outputs "+UtilText.parsePlayerSpeech("Hello reader!")+""
					+ ""
					+ "</p>"
					
					+"<p>"
					+ "<b>Command modifier (a_ an_)</b><br/>"
					+"You may insert 'a_' or 'an_' to automatically generate the appropriate pronoun before an argument. (It's your choice if you prefer a_ or an_, they both work in exactly the same way.)<br/>"
					+"e.g.:<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>height</i>] outputs 'tall'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_height</i>] outputs 'a tall'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>an_height</i>] <b>also</b> outputs 'a tall'<br/><br/>"
					
					+"For some body part names, this provides a little more complexity.<br/>"
					+ "e.g.:<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms</i>] outputs 'wings'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_arms</i>] outputs 'a pair of wings'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>an_arms</i>] <b>also</b> outputs 'a pair of wings'<br/><br/>"
					+ "</p>"
					
					+"<p>"
					+ "<b>Command modifier (Capitalisation)</b><br/>"
					+"Most commands are able to apply capitalisation. The ones that don't, such as numeric output commands, will still happily take a capitalised command, but capitalisation won't be applied.<br/>"
					+ "To capitalise an output, all you have to do is capitalise <b>the first letter</b> of the command name.<br/>"
					+"e.g.:<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>height</i>] outputs 'tall'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>hEIGHT</i>] <b>also</b> outputs 'tall' (I'm sure nobody would ever do this...)<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>Height</i>] outputs 'Tall'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_height</i>] outputs 'a tall'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>A_height</i>]  outputs 'A tall'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_Height</i>] <b>also</b> outputs 'A tall'<br/><br/>"
					
					+"<p>"
					+ "<b>Command modifier (+ D)</b><br/>"
					+"Most commands that return a name are able to apply additional <b>randomised descriptors</b>. (You can check to see what commands accept '+' and 'D' modifiers in the 'Commands' page.)<br/>"
					+ "To apply additional descriptors to the returned output, all you have to do is add a '+', 'd', or 'D' to the end of the command.<br/>"
					+ "<b>This works in combination with 'a_ an_' and 'Capitalisation' modifiers.</b><br/>"
					+"e.g.:<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms</i>] outputs 'wings'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms+</i>] outputs 'feathered wings'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>armsD</i>] <b>also</b> outputs 'feathered wings'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>armsd</i>] <b>also</b> outputs 'feathered wings'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>ArmsD</i>] outputs 'Feathered wings'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_arms+</i>] outputs 'a pair of feathered wings'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>A_arms+</i>]  outputs 'A pair of feathered wings'<br/><br/>"
					
					+"Some outputs have more randomisation than others.<br/>"
					+ "e.g.:<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy</i>] outputs 'slit'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy</i>] outputs 'cunt'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy+</i>] outputs 'wet pussy'<br/>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy+</i>] outputs 'tentacle-lined twat'<br/><br/>"
					+ "</p>"
					+ "<br/>"

					+ "<h6>Conclusion:</h6><br/>"
					+"<p>"
					+"<b>Valid</b> command syntax:<br/>"
					+"[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>]<br/>"
					+"or<br/>"
					+"[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
							+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]<br/><br/>"
					+ "<br/>"

					+ "<h6>Examples:</h6><br/>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ " leans back in [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>his</i>]"
						+ " chair, wondering what happened to [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>arthur</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ " after [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>he</i>]"
						+ " handed [[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>arthur</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>him</i>]] over to Scarlett.<br/>"
					+ "outputs:<br/>"
					+ "'Brax leans back in his chair, wondering what happened to Arthur after he handed him over to Scarlett.'<br/><br/>"
					
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ " leans back in "
						+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>herHuis</i>]"
						+ " chair, letting out a sigh as "
						+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>shyeHe</i>]"
						+ " takes a sip of [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>] coffee.<br/>"
					+ "outputs:<br/>"
					+ UtilText.parse("[rose.name] leans back in [rose.herHuis] chair, letting out a sigh as [rose.shyeHe] takes a sip of [rose.name] coffee.")+"<br/>"
					+ "<b>Note:</b> <i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Typos</i> will cause the parsing system to return an invalid command string, whereas"
							+ " <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>incorrect commands</i> (such as typing .name instead of .herHis) will not throw an error!<br/><br/>"
					
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ " storms up to [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>innoxia</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>],"
						+ " shouting angrily in response to finding out that"
						+ " [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>her</i>] sex scenes haven't been fixed yet,"
								+ " [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>speech</i>"
										+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(What the hell are you doing Innoxia?! You said my scenes were going to be re-written weeks ago!)</i>]<br/>"
					+ "outputs:<br/>"
					+ "'Lilaya storms up to Innoxia, shouting angrily in response to finding out that her sex scenes haven't been fixed yet, "
						+UtilText.parseSpeech("What the hell are you doing Innoxia?! You said my scenes were going to be re-written weeks ago!", Main.game.getLilaya())+"'"
					
					+ "</p>";
		}
		
		@Override
		public String getContent(){
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Parser", "", PARSER);
					
			} else if (index == 2) {
				return new Response("Help", "", null);
					
			} else if (index == 3) {
				return new Response("Targets", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("Commands", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("Commands list", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNodeOld PARSER_TARGETS = new DialogueNodeOld("Parser", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>targets</i>, for use in the parsing syntax:<br/>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
							+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]</p>");
			
			for(ParserTarget character : ParserTarget.values()) {
				UtilText.nodeContentSB.append("<hr/>"
						+"<p>");
				
				boolean first=true;
				for(String s : character.getTags()) {
					UtilText.nodeContentSB.append((first?"":" | ") +"<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>"+s+"</i>");
					first = false;
				}
				
				UtilText.nodeContentSB.append("<br/>"
						+character.getDescription()
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Parser", "", PARSER);
				
			} else if (index == 2) {
				return new Response("Help", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("Targets", "", null);
				
			} else if (index == 4) {
				return new Response("Commands", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("Commands list", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNodeOld PARSER_COMMANDS = new DialogueNodeOld("Parser", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>commands</i>, for use in the parsing syntax:<br/>"
						+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
						+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]</p>"
						+ "<p>"
							+ "<b>Please don't be intimidated by the number of commands!</b>"
							+ " The <i>vast</i> majority of them are automatically generated 'standard' variations for body parts, following a (hopefully) intuitive naming system."
						+ "</p>");
			
			int count=1;
			for(ParserCommand command : UtilText.commandsList) {
				UtilText.nodeContentSB.append("<hr/>"
						+ "<p>"
						+ "<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+String.format("%03d.", count)+"</b> <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(0)+"</i>");
				
				if(command.getTags().size()>1) {
					for(int i = 1; i<command.getTags().size(); i++)
						UtilText.nodeContentSB.append(" | <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(i)+"</i>");
				}
				
				UtilText.nodeContentSB.append("</p>");
				count++;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Parser", "", PARSER);
				
			} else if (index == 2) {
				return new Response("Help", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("Targets", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("Commands", "", PARSER_COMMANDS_NEAT);
				
			} else if (index == 5) {
				return new Response("Commands list", "", null);
				
			} else if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};
	
	public static final DialogueNodeOld PARSER_COMMANDS_NEAT = new DialogueNodeOld("Parser", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>commands</i>, for use in the parsing syntax:<br/>"
						+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
						+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]</p>"
						+ "<p>"
							+ "<b>Please don't be intimidated by the number of commands!</b>"
							+ " The <i>vast</i> majority of them are automatically generated 'standard' variations for body parts, following a (hopefully) intuitive naming system."
						+ "</p>");
			
			int count = 1;
			for(BodyPartType bpt : BodyPartType.values()) {
				UtilText.nodeContentSB.append("<details>"
						+ "<summary style='cursor:pointer;'><b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>("
							+String.format("%03d", count)+" - "+String.format("%03d", count+UtilText.commandsMap.get(bpt).size()-1)+")</b> "+Util.capitaliseSentence(bpt.getName())+"</summary>");
				for(ParserCommand command : UtilText.commandsMap.get(bpt)) {
					UtilText.nodeContentSB.append("<p>"
							+ "<hr/>"
							+ "<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+String.format("%03d.", count)+"</b> <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(0)+"</i>");
					
					if(command.getTags().size()>1) {
						for(int i = 1; i<command.getTags().size(); i++)
							UtilText.nodeContentSB.append(" | <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(i)+"</i>");
					}
					
					UtilText.nodeContentSB.append("<br/>"
							+(command.getArguments()==""?"<i style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No arguments</i>":"<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>"+command.getArguments()+"</i>")+"<br/>"
							+(command.isAllowsCapitalisation()?"<i style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Capitalisation</i>":"<i style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Capitalisation</i>")
								+ " | " +(command.isAllowsPronoun()?"<i style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Pronouns</i>":"<i style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Pronouns</i>")
								+"<br/>"
							+command.getDescription()+"<br/>"
							+"Examples:<br/>"
							+ command.getExampleBeforeParse("lilaya", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[lilaya."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")+"<br/>"
							
							+ command.getExampleBeforeParse("brax", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[brax."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")+"<br/>"
							
							+ command.getExampleBeforeParse("kate", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[kate."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")
							+"</p>");
					
					count++;
				}

				UtilText.nodeContentSB.append("</details>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return  "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Parser", "", PARSER);
				
			} else if (index == 2) {
				return new Response("Help", "", PARSER_HELP);
				
			} else if (index == 3) {
				return new Response("Targets", "", PARSER_TARGETS);
				
			} else if (index == 4) {
				return new Response("Commands", "", null);
				
			} else if (index == 5) {
				return new Response("Commands list", "", PARSER_COMMANDS);
				
			} else if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean disableHeaderParsing() {
			return true;
		}
	};

}
