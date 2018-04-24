package com.lilithsthrone.game.dialogue;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.responses.Response;
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
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 */
public class DebugDialogue {
	// Holds all basic DialogueNodes that don't belong to a specific
	// NPC/Dungeon/Event

	public static DialogueNodeOld getDefaultDialogue() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
	}

	public static DialogueNodeOld getDefaultDialogueNoEncounter() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
	}

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
					+ "Hi " + Main.game.getPlayer().getName() + "!</br>"
					+ "It looks like you know about the magic debug code! Just to give you a warning, all the options here are really buggy!"
					+ " If you spawn in any clothing or items, just be aware that some of them aren't officially in the game just yet, so they may not work exactly as expected."
					+ " Thanks for playing!</br></br>"
					+ "~Innoxia~</br></i>"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Back", "", DEBUG_MENU){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 1) {
				return new Response("Test colours", "Test text for readability", COLOURS){
					@Override
					public void effects() {
						coloursSB = new StringBuilder("<p>");
						for (Colour c : Colour.values())
							coloursSB.append(c + ": <span style='color:" + c.toWebHexString() + ";'>Test text for readability.</span></br>");
						coloursSB.append("</br></br>");
						for (BaseColour bc : BaseColour.values())
							coloursSB.append(bc + ": <span style='color:" + bc.toWebHexString() + ";'>Test text for readability.</span></br>");
						coloursSB.append("</p>");
						
					}
				};
				
			} else if (index == 2) {
				return new Response("Open parser", "Test the parser.", PARSER);
				
			} else if (index == 3) {
				return new Response("+500 xp", "Give yourself 500xp.", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementExperience(500, false);
						
					}
				};
				
			} else if (index == 4) {
				return new Response("Debug mode: ", "Reveal the entire map. Unlock enchanting in Lilaya's house.", DEBUG_MENU){
					@Override
					public String getTitle() {
						return "Debug mode: "+(Main.game.isDebugMode()?"ON":"OFF");
					}
					
					@Override
					public void effects() {
						Main.game.setDebugMode(!Main.game.isDebugMode());
						
					}
				};
				
			} else if (index == 5) {
				return new Response("+10000 " + UtilText.getCurrencySymbol(), "Add 10000 flames.", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(10000);
					}
				};
				
			} else if (index == 6) {
				return new Response("+50 essences", "Add 50 to each essence type.", DEBUG_MENU){
					@Override
					public void effects() {
						for(TFEssence essence : TFEssence.values()) {
							Main.game.getPlayer().incrementEssenceCount(essence, 50, false);
						}
					}
				};
				
			} else if (index == 7) {
				return new Response("Spawn Menu", "View the clothing, weapon, and item spawn menu.", SPAWN_MENU);
				
			} else if (index == 8) {
				return new Response("Brax's revenge", "Brax gets you pregnant! (If you have 0 fertility, this will probably crash the game!)", DEBUG_MENU){
					@Override
					public void effects() {
						int i=0;
						while (!Main.game.getPlayer().isPregnant() && i<100) {
							Main.game.getPlayer().rollForPregnancy(Main.game.getBrax());
							i++;
						}
						
					}
				};
				
			} else if (index == 9) {
				return new Response("Set body parts", "Manually set your body parts.", BODY_PART);
				
			}  else if(index==10){
				return new Response("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+";'>Physique</span>", "", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, 5);
						
					}
				};
			}
			else if(index==11){
				return new Response("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+Colour.ATTRIBUTE_ARCANE.toWebHexString()+";'>Arcane</span>", "", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, 5);
						
					}
				};
			}
			else if(index==12){
				return new Response("<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>+5</span> <span style='color:"+Colour.ATTRIBUTE_CORRUPTION.toWebHexString()+";'>Corruption</span>", "", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, 5);
						
					}
				};
			}
			else if(index==13){
				return new Response("<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Max all attributes</span>", "", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
						Main.game.getPlayer().setAttribute(Attribute.MAJOR_ARCANE, 100);
						Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, 100);
						
					}
				};
			}
			else if(index==14){
				return new Response("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+";'>Physique</span>", "", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_PHYSIQUE, -5);
						
					}
				};
			}
			else if(index==15){
				return new Response("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+Colour.ATTRIBUTE_ARCANE.toWebHexString()+";'>Arcane</span>", "", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_ARCANE, -5);
						
					}
				};
			}
			else if(index==16){
				return new Response("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>-5</span> <span style='color:"+Colour.ATTRIBUTE_CORRUPTION.toWebHexString()+";'>Corruption</span>", "", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, -5);
						
					}
				};
				
			}
			else if(index==17){
				return new Response("<span style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>+1</span> <span style='color:"+Colour.PERK.toWebHexString()+";'>Perk point</span>", "", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.getPlayer().setPerkPoints(Main.game.getPlayer().getPerkPoints()+1);
						
					}
				};
				
			} else if (index == 18) {
					return new Response("Offspring", "View available offspring", OFFSPRING);
					
			} else if (index == 19) {
				return new Response("[style.boldBad(Month -)]", "Reduce current month by 1.", DEBUG_MENU){
					@Override
					public void effects() {
						Main.game.setStartingDateMonth(Main.game.getStartingDate().getMonth().minus(1));
						
					}
				};
				
			} else if (index == 20) {
					return new Response("[style.boldGood(Month +)]", "Increase current month by 1.", DEBUG_MENU){
						@Override
						public void effects() {
							Main.game.setStartingDateMonth(Main.game.getStartingDate().getMonth().plus(1));
							
						}
					};
					
			}
			else {
				return null;
			}
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
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
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
					UtilText.nodeContentSB.append(npc.getName()+" "+npc.getMother().getName()+"'s daughter ("+npc.getRace().getName()+") Father:"+npc.getFather().getName()+" Mother:"+npc.getMother().getName()+"</br>");
				} else {
					UtilText.nodeContentSB.append(npc.getName()+" "+npc.getFather().getName()+"'s son ("+npc.getRace().getName()+") Father:"+npc.getFather().getName()+" Mother:"+npc.getMother().getName()+"</br>");
				}
			}
			if(activeOffspring!=null) {
				for(Fetish f : activeOffspring.getFetishes()) {
					UtilText.nodeContentSB.append("</br>[style.boldSex(Fetish:)] "+f.getName(activeOffspring));
				}
				UtilText.nodeContentSB.append(
						"</br>" + activeOffspring.getDescription()
						+"</br>" + activeOffspring.getBodyDescription());
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else if(index-1 < Main.game.getOffspring().size()) {
				return new Response(Main.game.getOffspring().get(index-1).getName(), "", OFFSPRING) {
					@Override
					public void effects() {
						activeOffspring = Main.game.getOffspring().get(index-1);
						for(CoverableArea ca : CoverableArea.values()) {
							activeOffspring.getPlayerKnowsAreas().add(ca);
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
		for (AbstractItemType c : ItemType.allItems) {
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
					inventorySB.append("<div class='inventory-item-slot unequipped "+ itemType.getRarity().getName() + "'>"
											+ "<div class='inventory-icon-content'>"+itemType.getSVGString()+"</div>"
											+ "<div class='overlay' id='" + itemType.getId() + "_SPAWN'></div>"
										+ "</div>");
					count++;
				}
				
			} else if(activeSlot == InventorySlot.WEAPON_MAIN || activeSlot == InventorySlot.WEAPON_OFFHAND) {
				for(AbstractWeaponType weaponType : weaponsTotal) {
					if(weaponType.getSlot()==activeSlot) {
						inventorySB.append("<div class='inventory-item-slot unequipped "+ weaponType.getRarity().getName() + "'>"
												+ "<div class='inventory-icon-content'>"+weaponType.getSVGStringMap().get(weaponType.getAvailableDamageTypes().get(0))+"</div>"
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
														clothingType.getAvailableTertiaryColours().isEmpty()?null:clothingType.getAvailableTertiaryColours().get(0))
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
						+ (slot == InventorySlot.WEAPON_MAIN || slot == InventorySlot.WEAPON_OFFHAND ? Colour.BASE_RED_LIGHT.toWebHexString() : Colour.BASE_YELLOW_LIGHT.toWebHexString())+";'>"+Util.capitaliseSentence(slot.getName())+"</div>");
			}
			inventorySB.append("<div class='normal-button' id='ITEM_SPAWN_SELECT' style='width:18%; margin:1%; padding:2px; font-size:0.9em; color:"+Colour.BASE_BLUE_LIGHT.toWebHexString()+";'>Items</div>");
			inventorySB.append("</div>");
			
			return inventorySB.toString();
		}

		@Override
		public String getContent() {
			return "";
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
	
	public static final DialogueNodeOld BODY_PART = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a body part.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Face choices", "", BODY_PART_FACE);
				
			} else if (index == 2) {
				return new Response("Skin choices", "", BODY_PART_SKIN);
				
			} else if (index == 3) {
				return new Response("Arm choices", "", BODY_PART_ARM);
				
			} else if (index == 4) {
				return new Response("Leg choices", "", BODY_PART_LEG);
				
			} else if (index == 5) {
				return new Response("Hair choices", "", BODY_PART_HAIR);
				
			} else if (index == 6) {
				return new Response("Eye choices", "", BODY_PART_EYE);
				
			} else if (index == 7) {
				return new Response("Ear choices", "", BODY_PART_EAR);
				
			} else if (index == 8) {
				return new Response("Horn choices", "", BODY_PART_HORN);
				
			} else if (index == 9) {
				return new Response("Tail choices", "", BODY_PART_TAIL);
				
			} else if (index == 10) {
				return new Response("Wing choices", "", BODY_PART_WING);
				
			} else if (index == 11) {
				return new Response("Penis choices", "", BODY_PART_PENIS);
				
			} else if (index == 12) {
				return new Response("Vagina choices", "", BODY_PART_VAGINA);
				
			} else if (index == 13) {
				return new Response("Breast choices", "", BODY_PART_BREAST);
				
			} else if (index == 14) {
				return new Response("Ass choices", "", BODY_PART_ASS);
				
			} else if (index == 15) {
				return new Response("Material choices", "", BODY_PART_MATERIAL);
				
			} else if (index == 0) {
				return new Response("Back", "", DEBUG_MENU);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_FACE = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a face type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < FaceType.values().length+1) {
				return new Response(FaceType.values()[index - 1].getRace().getName(), "", BODY_PART_FACE){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setFaceType(FaceType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_SKIN = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a skin type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < Race.values().length+1) {
				return new Response(Race.values()[index - 1].getName()+" - "+RacialBody.valueOfRace(Race.values()[index - 1]).getSkinType().getName(Main.game.getPlayer()), "", BODY_PART_SKIN){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setSkinType(RacialBody.valueOfRace(Race.values()[index - 1]).getSkinType()));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_ARM = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose an arm type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < ArmType.values().length+1) {
				return new Response(ArmType.values()[index - 1].getRace().getName(), "", BODY_PART_ARM){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setArmType(ArmType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_LEG = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a leg type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < LegType.values().length+1) {
				return new Response(LegType.values()[index - 1].getRace().getName(), "", BODY_PART_LEG){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setLegType(LegType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_HAIR = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a hair type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < Race.values().length+1) {
				return new Response(Race.values()[index - 1].getName()+" - "+RacialBody.valueOfRace(Race.values()[index - 1]).getHairType().getName(Main.game.getPlayer()), "", BODY_PART_HAIR){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setHairType(RacialBody.valueOfRace(Race.values()[index - 1]).getHairType()));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_EYE = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose an eye type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < Race.values().length+1) {
				return new Response(Race.values()[index - 1].getName()+" - "+RacialBody.valueOfRace(Race.values()[index - 1]).getEyeType().getName(Main.game.getPlayer()), "", BODY_PART_EYE){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setEyeType(RacialBody.valueOfRace(Race.values()[index - 1]).getEyeType()));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_EAR = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose an ear type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < EarType.values().length+1) {
				return new Response(EarType.values()[index - 1].getRace().getName(), "", BODY_PART_EAR){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setEarType(EarType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_HORN = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a horn type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < HornType.values().length+1) {
				return new Response(HornType.values()[index - 1].getRace()!=null?HornType.values()[index - 1].getRace().getName():"None", "", BODY_PART_HORN){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setHornType(HornType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_TAIL = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a tail type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < TailType.values().length+1) {
				return new Response(TailType.values()[index - 1].getRace()!=null?TailType.values()[index - 1].getRace().getName():"None", "", BODY_PART_TAIL){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setTailType(TailType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_WING = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a wing type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < WingType.values().length+1) {
				return new Response(WingType.values()[index - 1].getRace()!=null?WingType.values()[index - 1].getRace().getName():"None", "", BODY_PART_WING){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setWingType(WingType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_PENIS = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a penis type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < PenisType.values().length+1) {
				return new Response(PenisType.values()[index - 1].getRace()!=null?PenisType.values()[index - 1].getRace().getName():"None", "", BODY_PART_PENIS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setPenisType(PenisType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_VAGINA = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a vagina type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < VaginaType.values().length+1) {
				return new Response(VaginaType.values()[index - 1].getRace()!=null?VaginaType.values()[index - 1].getRace().getName():"None", "", BODY_PART_VAGINA){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setVaginaType(VaginaType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_BREAST = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a breast type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < BreastType.values().length+1) {
				return new Response(BreastType.values()[index - 1].getRace().getName(), "", BODY_PART_BREAST){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setBreastType(BreastType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_ASS = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose an ass type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < AssType.values().length+1) {
				return new Response(AssType.values()[index - 1].getRace().getName(), "", BODY_PART_ASS){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setAssType(AssType.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld BODY_PART_MATERIAL = new DialogueNodeOld("Set body parts", "Set body parts.", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "Choose a material type.";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index != 0 && index < BodyMaterial.values().length+1) {
				return new Response(BodyMaterial.values()[index - 1].getName(), "", BODY_PART_MATERIAL){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setBodyMaterial(BodyMaterial.values()[index - 1]));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "", BODY_PART);
				
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
								+ " [rose.name] is sitting beside [lilaya.herPro], gazing up lovingly into [lilaya.name]'s [lilaya.eyes+] as [rose.her] [rose.tail+] gently swishes back and forth behind [rose.herPro]."
								+ "\n</br></br>\n"
								+ "Unable to concentrate with [lilaya.her] slave hovering so close at hand, [lilaya.name] places the demonstone down onto the table, before wrapping [lilaya.her] [lilaya.arms+] around [rose.name]'s back and pulling"
								+ " [rose.herPro] into [lilaya.her] lap. [rose.name] lets out a happy little cry as [rose.her] master finally starts giving [rose.herPro] the attention [rose.she] was craving, and as [lilaya.name]'s hand slips down"
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
					+ "<h6>Input:</h6></br>"
					+"Everything is parsed using square brackets, split into the following pattern:</br>"
					+"[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>]</br>"
					+"or, for the few special commands that require arguments,</br>"
					+"[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
							+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]</br>"
					+ "</p>"
					
					+ "<p>"
					+"An example of use in a sentence would be:</br></br>"
					+"As you start to read Innoxia's tedious parsing documentation, [lilaya.name] steps up behind you and wraps [lilaya.her] [lilaya.tail+] around your [pc.leg]."
					+" Leaning in over your shoulder, [lilaya.she] groans, [lilaya.speech(Oh my God. This is so boring!)]'</br></br>"
					+ "parses to:</br></br>"
					+ UtilText.parse("As you start to read Innoxia's tedious parsing documentation, [lilaya.name] steps up behind you and wraps [lilaya.her] [lilaya.tail+] around your [pc.leg]."
							+ " Leaning in over your shoulder, [lilaya.she] groans, [lilaya.speech(Oh my God. This is so boring!)]")
					+ "</p>"
					+ "</br>"
					
					
					+"<h6><b style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>Target</b> <b>tag:</b></h6>"
					+"<p>"
					+"The target of a command is an NPC's name, or 'pc' for the player character. Target tags are <b>case-insensitive.</b> (i.e. pc is treated the same as PC, pC, or Pc)</br>"
					+" If an unrecognised name is passed, the output will read 'INVALID_TARGET_NAME'.</br>"
					+" Currently accepted target tags can be viewed in the 'Targets' page.</br>"
					+ "e.g.:</br>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.command]</br>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>pc</i>.command(arguments)]</br>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>npc</i>.command]"
					+ "</p>"
					+ "</br>"
					
					
					+"<h6><b style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>Command</b> <b>and</b> <b style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>argument</b> <b>tags:</b></h6>"
					+"<p>"
					+"Command tags determine what output is returned. They come in two varieties; with and without arguments.</br>"
					+"Arguments are passed inside brackets that follow the command, ignoring any spaces that you may insert.</br>"
					+ "i.e. [pc.command(arguments)] is the same as [pc.command   (arguments)].</br>"
					+"Command tags are <b>only case-sensitive for the first letter</b>. (i.e. command is treated the same as cOMMAND, cOmMaNd, or commanD)</br>"
					+ "Arguments are specific to each command, and you'll have to refer to the command documentation to find out what arguments a command takes. (Don't worry, there aren't many that take arguments.)</br>"
					+ "e.g.:</br>"
					+ "[pc.speech<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(Hello reader!)</i>] outputs "+UtilText.parsePlayerSpeech("Hello reader!")+""
					+ ""
					+ "</p>"
					
					+"<p>"
					+ "<b>Command modifier (a_ an_)</b></br>"
					+"You may insert 'a_' or 'an_' to automatically generate the appropriate pronoun before an argument. (It's your choice if you prefer a_ or an_, they both work in exactly the same way.)</br>"
					+"e.g.:</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>height</i>] outputs 'tall'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_height</i>] outputs 'a tall'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>an_height</i>] <b>also</b> outputs 'a tall'</br></br>"
					
					+"For some body part names, this provides a little more complexity.</br>"
					+ "e.g.:</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms</i>] outputs 'wings'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_arms</i>] outputs 'a pair of wings'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>an_arms</i>] <b>also</b> outputs 'a pair of wings'</br></br>"
					+ "</p>"
					
					+"<p>"
					+ "<b>Command modifier (Capitalisation)</b></br>"
					+"Most commands are able to apply capitalisation. The ones that don't, such as numeric output commands, will still happily take a capitalised command, but capitalisation won't be applied.</br>"
					+ "To capitalise an output, all you have to do is capitalise <b>the first letter</b> of the command name.</br>"
					+"e.g.:</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>height</i>] outputs 'tall'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>hEIGHT</i>] <b>also</b> outputs 'tall' (I'm sure nobody would ever do this...)</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>Height</i>] outputs 'Tall'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_height</i>] outputs 'a tall'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>A_height</i>]  outputs 'A tall'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_Height</i>] <b>also</b> outputs 'A tall'</br></br>"
					
					+"<p>"
					+ "<b>Command modifier (+ D)</b></br>"
					+"Most commands that return a name are able to apply additional <b>randomised descriptors</b>. (You can check to see what commands accept '+' and 'D' modifiers in the 'Commands' page.)</br>"
					+ "To apply additional descriptors to the returned output, all you have to do is add a '+', 'd', or 'D' to the end of the command.</br>"
					+ "<b>This works in combination with 'a_ an_' and 'Capitalisation' modifiers.</b></br>"
					+"e.g.:</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms</i>] outputs 'wings'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>arms+</i>] outputs 'feathered wings'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>armsD</i>] <b>also</b> outputs 'feathered wings'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>armsd</i>] <b>also</b> outputs 'feathered wings'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>ArmsD</i>] outputs 'Feathered wings'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>a_arms+</i>] outputs 'a pair of feathered wings'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>A_arms+</i>]  outputs 'A pair of feathered wings'</br></br>"
					
					+"Some outputs have more randomisation than others.</br>"
					+ "e.g.:</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy</i>] outputs 'slit'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy</i>] outputs 'cunt'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy+</i>] outputs 'wet pussy'</br>"
					+ "[npc.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>pussy+</i>] outputs 'tentacle-lined twat'</br></br>"
					+ "</p>"
					+ "</br>"

					+ "<h6>Conclusion:</h6></br>"
					+"<p>"
					+"<b>Valid</b> command syntax:</br>"
					+"[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>]</br>"
					+"or</br>"
					+"[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
							+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]</br></br>"
					+ "</br>"

					+ "<h6>Examples:</h6></br>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ " leans back in [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>his</i>]"
						+ " chair, wondering what happened to [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>arthur</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ " after [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>brax</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>he</i>]"
						+ " handed [[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>arthur</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>him</i>]] over to Scarlett.</br>"
					+ "outputs:</br>"
					+ "'Brax leans back in his chair, wondering what happened to Arthur after he handed him over to Scarlett.'</br></br>"
					
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ " leans back in "
						+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>herHuis</i>]"
						+ " chair, letting out a sigh as "
						+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>shyeHe</i>]"
						+ " takes a sip of [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>rose</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>] coffee.</br>"
					+ "outputs:</br>"
					+ UtilText.parse("[rose.name] leans back in [rose.herHuis] chair, letting out a sigh as [rose.shyeHe] takes a sip of [rose.name] coffee.")+"</br>"
					+ "<b>Note:</b> <i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Typos</i> will cause the parsing system to return an invalid command string, whereas"
							+ " <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>incorrect commands</i> (such as typing .name instead of .herHis) will not throw an error!</br></br>"
					
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>]"
						+ " storms up to [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>innoxia</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>name</i>],"
						+ " shouting angrily in response to finding out that"
						+ " [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>her</i>] sex scenes haven't been fixed yet,"
								+ " [<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>lilaya</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>speech</i>"
										+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(What the hell are you doing Innoxia?! You said my scenes were going to be re-written weeks ago!)</i>]</br>"
					+ "outputs:</br>"
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
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>targets</i>, for use in the parsing syntax:</br>"
					+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
							+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]</p>");
			
			for(ParserTarget character : ParserTarget.values()) {
				UtilText.nodeContentSB.append("<hr></hr>"
						+"<p>");
				
				boolean first=true;
				for(String s : character.getTags()) {
					UtilText.nodeContentSB.append((first?"":" | ") +"<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>"+s+"</i>");
				}
				
				UtilText.nodeContentSB.append("</br>"
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
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>commands</i>, for use in the parsing syntax:</br>"
						+ "[<i style='color:"+Colour.CLOTHING_BLUE_LIGHT.toWebHexString()+";'>target</i>.<i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>command</i>"
						+ "<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>(arguments)</i>]</p>"
						+ "<p>"
							+ "<b>Please don't be intimidated by the number of commands!</b>"
							+ " The <i>vast</i> majority of them are automatically generated 'standard' variations for body parts, following a (hopefully) intuitive naming system."
						+ "</p>");
			
			int count=1;
			for(ParserCommand command : UtilText.commandsList) {
				UtilText.nodeContentSB.append("<hr></hr>"
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
			
			UtilText.nodeContentSB.append("<p>Here are a list of accepted <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>commands</i>, for use in the parsing syntax:</br>"
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
							+ "<hr></hr>"
							+ "<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+String.format("%03d.", count)+"</b> <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(0)+"</i>");
					
					if(command.getTags().size()>1) {
						for(int i = 1; i<command.getTags().size(); i++)
							UtilText.nodeContentSB.append(" | <i style='color:"+Colour.CLOTHING_PINK_LIGHT.toWebHexString()+";'>"+command.getTags().get(i)+"</i>");
					}
					
					UtilText.nodeContentSB.append("</br>"
							+(command.getArguments()==""?"<i style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No arguments</i>":"<i style='color:"+Colour.CLOTHING_YELLOW.toWebHexString()+";'>"+command.getArguments()+"</i>")+"</br>"
							+(command.isAllowsCapitalisation()?"<i style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Capitalisation</i>":"<i style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Capitalisation</i>")
								+ " | " +(command.isAllowsPronoun()?"<i style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Pronouns</i>":"<i style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Pronouns</i>")
								+"</br>"
							+command.getDescription()+"</br>"
							+"Examples:</br>"
							+ command.getExampleBeforeParse("lilaya", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[lilaya."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")+"</br>"
							
							+ command.getExampleBeforeParse("brax", (command.getArguments()==""?"":command.getArgumentExample()))+" -> "
								+UtilText.parse("[brax."+command.getTags().get(0)+(command.getArguments()==""?"":"("+command.getArgumentExample()+")")+"]")+"</br>"
							
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
