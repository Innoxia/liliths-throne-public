package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ShoppingArcadeDialogue {
	
	public static GameCharacter getGloryHoleCharacter() {
		List<GameCharacter> characters = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		characters.removeIf((npc) -> !(npc instanceof GenericSexualPartner));
		return characters.get(0);
	}
	
	public static String getCoreResponseTab(int index) {
		if(index==0) {
			return "Actions";
		} else if(index==1) {
			return "Fast travel";
		}
		return null;
	}
	
	public static Response getFastTravelResponses(int responseTab, int index) {
		if(responseTab==1) {
			if (index == 1) {
				return new Response("Entrance", "Fast travel to the Shopping Arcade's main entrance.", PlaceType.SHOPPING_ARCADE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 2) {
				return new Response("Ralph's Snacks", "Fast travel to Ralph's Snacks.", PlaceType.SHOPPING_ARCADE_RALPHS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 3) {
				return new Response("Nyan's Clothing Emporium", "Fast travel to Nyan's Clothing Emporium.", PlaceType.SHOPPING_ARCADE_NYANS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 4) {
				return new Response("Arcane Arts", "Fast travel to Arcane Arts.", PlaceType.SHOPPING_ARCADE_VICKYS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_VICKYS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 5) {
				return new Response("Succubi's Secrets", "Fast travel to Succubi's Secrets.", PlaceType.SHOPPING_ARCADE_KATES_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_KATES_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 6) {
				return new Response("Pix's Playground", "Fast travel to the gym, 'Pix's Playground'.", PlaceType.SHOPPING_ARCADE_PIXS_GYM.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_PIXS_GYM, false);
						Main.game.setResponseTab(0);
					}
				};
	
			} else if (index == 7) {
				return new Response("Dream Lover", "Fast travel to Dream Lover.", PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP, false);
						Main.game.setResponseTab(0);
					}
				};
				
			} else if (index == 8) {
				return new Response("The Oaken Glade", "Fast travel to the restaurant, 'The Oaken Glade'.", PlaceType.SHOPPING_ARCADE_RESTAURANT.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT, false);
						Main.game.setResponseTab(0);
					}
				};
			}
		}
		return null;
	}
	
	// Dialogue noes:
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Shopping arcade", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "OUTSIDE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step through the entrance and enter the shopping arcade.", PlaceType.SHOPPING_ARCADE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRY = new DialogueNode("Entrance to the arcade", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ENTRY");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Exit", "Leave the Shopping Arcade.", PlaceType.DOMINION_SHOPPING_ARCADE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_SHOPPING_ARCADE, false);
						}
					};
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
		
	};
	
	public static final DialogueNode ARCADE = new DialogueNode("Shopping arcade", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE"));
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_START
					&& Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ANTIQUES).getLocation())==1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ARCADE_WES_REMINDER"));
			}
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index==1) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_START) {
						if(Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_ANTIQUES).getLocation())==1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.wesQuestMet)) {
								return new Response("Meet Enforcer", "You've already met Wes today, and so will be unable to meet with him again until tomorrow at the earliest...", null);
								
							} else if(Main.game.getHourOfDay()!=13) {
								return new Response("Meet Enforcer", "The mysterious Enforcer told you to meet him between the hours of [units.time(13)] and [units.time(14)], so you'll have to come back then...", null);
								
							} else {
								return new Response("Meet Enforcer", "Loiter around the area and wait for the mysterious Enforcer to contact you...", WesQuest.WES_QUEST_SHOPPING_ARCADE_MEETING);
							}
						}
					}
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
		
	};
	
	public static final DialogueNode GENERIC_SHOP = new DialogueNode("Shop", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "GENERIC_SHOP");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode RESTAURANT = new DialogueNode("The Oaken Glade", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "RESTAURANT");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode ANTIQUES = new DialogueNode("Antiques Shop", "-", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return getCoreResponseTab(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if(index==1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Enter", "The antiques shop is currently closed; you'll have to return at another time if you want to take a look inside.", null);
					}
					return new Response("Enter", "Step inside the antiques shop and take a look around.", ANTIQUES_INTERIOR);
				}
			}
			return getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR = new DialogueNode("Antiques Shop", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR"));
			
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Scarlett.class))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()).contains(Main.game.getNpc(Scarlett.class))) {
				if(index==1) {
					return new Response("Scarlett", "Head over to the store's employee and ask them about Scarlett.", ANTIQUES_INTERIOR_SCARLETT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_START"));
							if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || (Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_TRAP && Main.game.getNpc(Scarlett.class).getGender()!=Gender.F_P_B_SHEMALE)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_TRANSFORMED"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT"));
							}
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_END"));
							
							((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar")), true, Main.game.getNpc(Scarlett.class));
						}
					};
				}
			}
			if(index==0) {
				return new Response("Exit", "Head back out into the Shopping Arcade.", ANTIQUES);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT = new DialogueNode("", "", true) {
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
			if(index==1) {
				return new Response("Explain", "Explain that while you're not here from the business which the squirrel-boy is talking about, you <i>are</i> here to take Scarlett off his hands.", ANTIQUES_INTERIOR_SCARLETT_EXPLAIN);
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_EXPLAIN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_EXPLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<10_000) {
					return new Response("Pay ("+UtilText.formatAsMoney(10_000, "span")+")", "You cannot afford to pay the squirrel-boy the ten thousand flames he's asking for!", null);
					
				} else {
					return new Response("Pay ("+UtilText.formatAsMoney(10_000, "span")+")", "Pay the squirrel-boy the ten thousand flames he's asking for.", ANTIQUES_INTERIOR_SCARLETT_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_PURCHASED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Haggle", "Haggle with the squirrel-boy in an attempt to drive down the price which he's asking for Scarlett.", ANTIQUES_INTERIOR_SCARLETT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "ANTIQUES_INTERIOR_SCARLETT_HAGGLE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ANTIQUES_INTERIOR_SCARLETT_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
			Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK).setSealed(false);
			Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK), true, Main.game.getNpc(Scarlett.class));
			Main.game.getNpc(Scarlett.class).getOwner().removeSlave(Main.game.getNpc(Scarlett.class));
			Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX()+1, Main.game.getPlayer().getLocation().getY()));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettToldToReturn, true);
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
			if(index==1) {
				return new Response("Continue",
						"Continue on your way out into the Shopping Arcade.",
						ARCADE);
			}
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
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS");
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
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_USE");
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
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_WASH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TOILETS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode TOILETS_GLORY_HOLE_DOM = new DialogueNode("Toilets", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.isExtendedWorkTime()) {
				return 20*60;
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "On second thoughts, you don't really want some stranger having fun with your private parts...", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_LEAVE", getGloryHoleCharacter()));
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
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_START", getGloryHoleCharacter()));
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
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_DOM_POST_SEX", getGloryHoleCharacter());
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
			if(Main.game.isExtendedWorkTime()) {
				return 20*60;
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB", getGloryHoleCharacter());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "On second thoughts, you don't really want to suck the cock of some random stranger...", TOILETS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_LEAVE", getGloryHoleCharacter()));
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
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_START", getGloryHoleCharacter()));
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
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/generic", "TOILETS_GLORY_HOLE_SUB_POST_SEX", getGloryHoleCharacter());
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
