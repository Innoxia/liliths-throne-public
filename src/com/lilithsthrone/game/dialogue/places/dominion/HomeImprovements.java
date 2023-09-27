package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class HomeImprovements {

	public static GameCharacter getGloryHoleCharacter() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters.get(0);
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Argus's DIY Depot", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "OUTSIDE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isExtendedWorkTime()) {
					return new Response("Enter", "Enter the warehouse signposted as being open to members of the public.", PlaceType.HOME_IMPROVEMENTS_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE, false);
						}
					};
				} else {
					return new Response("Enter", "The warehouse is quite clearly closed at the moment. You'll have to come back later if you want to get inside...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Exit", "Exit the warehouse and head back out into Dominion.", PlaceType.DOMINION_HOME_IMPROVEMENT.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HOME_IMPROVEMENT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR_PAINT_OPTIONS"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "CORRIDOR_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SHELVING_PREMIUM = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PREMIUM"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PREMIUM_PRICE"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<ItemType.PAINT_CAN_PREMIUM.getValue()) {
						return new Response("Purchase ("+UtilText.formatAsMoneyUncoloured(ItemType.PAINT_CAN_PREMIUM.getValue(), "span")+")",
								"Although this can of paint [style.colourGood(is the one Helena requested)], you [style.colourbad(cannot afford to buy it)]!",
								null);
					}
					return new Response("Purchase ("+UtilText.formatAsMoney(ItemType.PAINT_CAN_PREMIUM.getValue(), "span")+")",
							"Purchase a can of "+ItemType.PAINT_CAN_PREMIUM.getName(false)+", which [style.colourGood(is the one Helena requested)].",
							PAINT_PURCHASED) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE);
							UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.PAINT_CAN_PREMIUM.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.PAINT_CAN_PREMIUM), false, true));
							((Helena)Main.game.getNpc(Helena.class)).sellOffRemainingSlaves();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode SHELVING_STANDARD = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_STANDARD"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(!Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
						&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
					UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), true);
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_STANDARD_PRICE"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "SHELVING_PAINT_PURCHASED"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<ItemType.PAINT_CAN.getValue()) {
						return new Response("Purchase ("+UtilText.formatAsMoneyUncoloured(ItemType.PAINT_CAN.getValue(), "span")+")",
								"This can of paint [style.colourBad(is not the one Helena requested)], and even if you did want to purchase it, you [style.colourbad(cannot afford it)]!",
								null);
					}
					return new Response("Purchase ("+UtilText.formatAsMoney(ItemType.PAINT_CAN.getValue(), "span")+")",
							"Purchase a can of "+ItemType.PAINT_CAN.getName(false)+", which [style.colourBad(is not the one Helena requested)]!",
							PAINT_PURCHASED) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.HOME_IMPROVEMENTS, PlaceType.HOME_IMPROVEMENTS_ENTRANCE);
							UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.PAINT_CAN.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.PAINT_CAN), false, true));
							((Helena)Main.game.getNpc(Helena.class)).sellOffRemainingSlaves();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PAINT_PURCHASED = new DialogueNode("", ".", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().getRace()==Race.DEMON) {
				return 5*60;
			} else {
				return 15*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "PAINT_PURCHASED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave",
						"Now that you've got what you came here for, you may as well leave.",
						PlaceType.DOMINION_HOME_IMPROVEMENT.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_HOME_IMPROVEMENT);
					}
				};
				
			} else if(index==2) {
				return new Response("Continue shopping",
						"You could always stay and look around a little more before leaving.",
						ENTRANCE);
			}
			return null;
		}
	};
	
	public static final DialogueNode BUILDING_SUPPLIES = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "BUILDING_SUPPLIES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode TOILETS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Toilet", "Use the toilet.", TOILETS_USE);
				
			} else if(index==2) {
				List<InventorySlot> washSlots = Util.newArrayListOfValues(InventorySlot.HEAD, InventorySlot.EYES, InventorySlot.MOUTH, InventorySlot.NECK, InventorySlot.HAIR, InventorySlot.FINGER, InventorySlot.HAND, InventorySlot.WRIST);
				return new Response("Wash",
						"Use the sinks to wash your hands and face."
							+ "<br/>[style.italicsGood(This will clean your "+Util.inventorySlotsToParsedStringList(washSlots, Main.game.getPlayer())+", as well as any clothing worn in these slots.)]"
							+ "<br/>[style.italicsMinorBad(This does <b>not</b> clean companions.)]",
						TOILETS_WASH) {
					@Override
					public void effects() {
						for(InventorySlot slot : washSlots) {
							Main.game.getPlayer().removeDirtySlot(slot, true);
							AbstractClothing c = Main.game.getPlayer().getClothingInSlot(slot);
							if(c!=null) {
								c.setDirty(Main.game.getPlayer(), false);
							}
						}
					}
				};
				
			} else if(index==3) {
				boolean penisAvailable = Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
				boolean vaginaAvailable = Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
				
				if((penisAvailable && !Main.game.getPlayer().isTaur()) || vaginaAvailable) {
					return new Response("Glory hole (use)",
							"One of the toilet's stalls has a glory hole in it. Enter the stall and wait for someone on the other side to service you.",
							TOILETS_GLORY_HOLE_DOM) {
						@Override
						public void effects() {
							Main.game.spawnSubGloryHoleNPC("stranger");
						}
					};
					
				} else if(penisAvailable && Main.game.getPlayer().isTaur()) {
					return new Response("Glory hole (use)",
							"Due to the shape of your [pc.legRace]'s body, you cannot get into a suitable position for using the glory hole...",
							null);
					
				} else {
					return new Response("Glory hole (use)",
							"You can't get access to your genitals, so can't get serviced at a glory hole.",
							null);
				}
				
			} else if(index==4) {
				if((Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true))
						|| (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
						|| (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))) {
					return new Response("Glory hole (service)",
							"One of the toilet's stalls has a glory hole in it. Enter the stall and wait to service someone on the other side.",
							TOILETS_GLORY_HOLE_SUB) {
						@Override
						public void effects() {
							Main.game.spawnDomGloryHoleNPC("stranger");
							getGloryHoleCharacter().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						}
					};
				
				} else {
					return new Response("Glory hole (service)",
							"You can't get access to your mouth, genitals, or asshole, so can't service any strangers at the glory holes.",
							null);
				}
			}
			return null;
		}
	};

	public static final DialogueNode TOILETS_USE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_USE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode TOILETS_WASH = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_WASH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM = new DialogueNode("Toilets", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "On second thoughts, you don't really want some stranger having fun with your private parts...", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_LEAVE", getGloryHoleCharacter()));
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
				
			} else if(index==1) {
				return new ResponseSex("Start",
						UtilText.parse(getGloryHoleCharacter(), "Do as [npc.name] says and step up to the glory hole."),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(getGloryHoleCharacter(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						TOILETS_GLORY_HOLE_DOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_START", getGloryHoleCharacter()));
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM_POST_SEX = new DialogueNode("Toilets", "The stranger quickly exits their stall, and heads back into the store, leaving you to do the same...", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_DOM_POST_SEX", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Walk out of the stall.", TOILETS) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_SUB = new DialogueNode("Toilets", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "On second thoughts, you don't really want to suck the cock of some random stranger...", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_LEAVE", getGloryHoleCharacter()));
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
				
			} else if(index==1) {
				return new ResponseSex("Start",
						UtilText.parse(getGloryHoleCharacter(), "Do as [npc.name] says and get ready to service [npc.her] cock."),
						true, false,
						new SMGloryHole(
								SexPosition.GLORY_HOLE,
								Util.newHashMapOfValues(new Value<>(getGloryHoleCharacter(), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.GLORY_HOLE_KNEELING))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						TOILETS_GLORY_HOLE_SUB_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_START", getGloryHoleCharacter()));
			}
			return null;
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_SUB_POST_SEX = new DialogueNode("Toilets", "The stranger quickly exits their stall, and heads back into the store, leaving you to do the same...", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/homeImprovements/generic", "TOILETS_GLORY_HOLE_SUB_POST_SEX", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Walk out of the stall.", TOILETS) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getGloryHoleCharacter());
					}
				};
			}
			return null;
		}
	};
}
