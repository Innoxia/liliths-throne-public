package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Alexa;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.2.10
 * @author Innoxia
 */
public class ScarlettsShop {
	
	public static final DialogueNode SCARLETTS_SHOP_EXTERIOR = new DialogueNode("Scarlett's shop", ".", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_ALEXA) {
					return new Response("Enter", "You should go and find Alexa before entering Scarlett's Shop again.", null);
					
				} else {
					return new Response("Enter", "Enter the shop.", SCARLETTS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETTS_SHOP = new DialogueNode("Scarlett's shop", ".", true) {

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_INTRO");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
					return new Response("Ask for Arthur", "Ask Scarlett if she's got a slave named Arthur for sale.", SCARLETT_IS_A_BITCH);

				} else {
					return null;
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Exit the shop.", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}

		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_BITCH = new DialogueNode("Scarlett's shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_BITCH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "Agree to help Scarlett.", SCARLETT_IS_A_SUPER_BITCH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_ALEXA));
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_SUPER_BITCH = new DialogueNode("Scarlett's shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_SUPER_BITCH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Exit the shop.", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode ALEXAS_SHOP_EXTERIOR = new DialogueNode("Alexa's Pet Shop", ".", false) {

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_EXTERIOR_ALEXA_RETURNS");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_EXTERIOR");
			}
			
			//TODO after helping Alexa, exterior looks different:
//				return  "<p>"
//						+ "Once again, you find yourself standing in front of Alexa's Pet Shop."
//						+ " The sign that once read 'Scarlett's shop; open for business' has been totally erased, and in its place, the words 'Alexa's Pet Shop' have been written in fancy gold lettering."
//						+ " The area around the beautiful harpy's shop is far busier than it ever was when Scarlett was in charge."
//					+ "</p>"
//					+ "<p>"
//						+ "You wonder if you should enter Alexa's Pet Shop now, or come back later."
//					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Enter", "Enter the shop.", ALEXAS_SHOP) {
						@Override
						public void effects() {
							Main.game.getNpc(Alexa.class).addSlave(Main.game.getNpc(Scarlett.class));
							Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
							Main.game.getNpc(Scarlett.class).resetInventory(true);
							AbstractClothing collar = AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_BLACK_STEEL, false);
							collar.setSealed(true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Alexa.class));
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_BALLGAG, Colour.CLOTHING_PINK, false), true, Main.game.getNpc(Alexa.class));
						}
					};
					
				} else {
					return new Response("Enter", "Enter the shop.", ALEXAS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ALEXAS_SHOP = new DialogueNode("Alexa's Pet Shop", ".", true) {

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_INTRODUCTION");
					
			} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_OFFER_SCARLETT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP"); //TODO expand
			}
			
			// TODO new interior description once renovated
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Offer to buy", "Offer to buy Scarlett from Alexa.", ALEXAS_SHOP_SCARLETT_FOR_SALE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY));
							if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.punishedByAlexa)) {
								Main.game.getDialogueFlags().scarlettPrice = 10000;
							}
						}
					};
					
				} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
					if(!Main.game.getPlayer().isHasSlaverLicense()) {
						return new Response("Buy Scarlett (" + UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().scarlettPrice, "span")+")",
								"You need to obtain a slaver license from the Slavery Administration before you can buy Scarlett!", null);
						
					} else if(Main.game.getPlayer().getMoney() < Main.game.getDialogueFlags().scarlettPrice) {
						return new Response("Buy Scarlett (" +UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().scarlettPrice, "span")+")", "You don't have enough money to buy Scarlett.", null);
						
					} else {
						return new Response("Buy Scarlett ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().scarlettPrice, "span")+")"
								, "Buy Scarlett for "+Main.game.getDialogueFlags().scarlettPrice+" flames.", ALEXAS_SHOP_BUYING_SCARLETT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().scarlettPrice));
								
								AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
								if (ballgag != null) {
									ballgag.setSealed(false);
									Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Alexa.class));
								}
								
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Alexa.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
							}
						};
					}
					
				} else {
					return new Response("Slave Manager", "Enter the slave management screen.", ALEXAS_SHOP) {
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.getSlaveryManagementDialogue(Main.game.getNpc(Alexa.class));
						}
					};
				}

			} else if (index == 0 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
				return new Response("Leave", "Leave Alexa's Pet Shop.", ALEXAS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ALEXAS_SHOP_SCARLETT_FOR_SALE = new DialogueNode("Alexa's Pet Shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_SCARLETT_FOR_SALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ALEXAS_SHOP.getResponse(0, index);
		}
	};
	
	public static final DialogueNode ALEXAS_SHOP_BUYING_SCARLETT = new DialogueNode("Alexa's Pet Shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_BUYING_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Calm her down", "Gently reassure Scarlett to get her to calm down.", ALEXAS_SHOP_GENTLE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Shout at her", "Shout at Scarlett and remind her that she's now your property.", ALEXAS_SHOP_SHOUT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -2));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(2));
					}
				};
				
			} else if (index == 3) {
				return new Response("Slap her", "Slap Scarlett and remind her that she's now your property.", ALEXAS_SHOP_SLAP,
						Util.newArrayListOfValues(Fetish.FETISH_SADIST),
						CorruptionLevel.FOUR_LUSTFUL,
						null,
						null,
						null
						) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(5));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ALEXAS_SHOP_GENTLE = new DialogueNode("Alexa's Pet Shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_GENTLE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getSlaveryChoiceResponse(index);
		}
	};
	
	public static final DialogueNode ALEXAS_SHOP_SHOUT = new DialogueNode("Alexa's Pet Shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_SHOUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getSlaveryChoiceResponse(index);
		}
	};
	
	public static final DialogueNode ALEXAS_SHOP_SLAP = new DialogueNode("Alexa's Pet Shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_SLAP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getSlaveryChoiceResponse(index);
		}
	};
	
	private static Response getSlaveryChoiceResponse(int index) {
		if (index == 1) {
			return new Response("Keep her", "You decide to keep Scarlett as your slave.", ALEXAS_SHOP_BUYING_SCARLETT_KEEP_HER) {
				@Override
				public void effects() {
					Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
				}
			};

		} else if (index == 2) {
			return new Response("Free her", "You decide to grant Scarlett her freedom.", ALEXAS_SHOP_BUYING_SCARLETT_FREE_HER) {
				@Override
				public void effects() {
					
					AbstractClothing collar = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK);
					if(collar!=null) {
						collar.setSealed(false);
						Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(collar, true, Main.game.getNpc(Alexa.class));
					}
					
					((Scarlett) Main.game.getNpc(Scarlett.class)).equipClothing(true, false, false, true);
					
					Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_ALEXAS_NEST, true);
					Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
					Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), 35));
					Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
				}
			};

		} else {
			return null;
		}
	}
	
	public static final DialogueNode ALEXAS_SHOP_BUYING_SCARLETT_KEEP_HER = new DialogueNode("Alexa's Pet Shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_BUYING_SCARLETT_KEEP_HER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ALEXAS_SHOP.getResponse(0, index);
		}
	};
	
	public static final DialogueNode ALEXAS_SHOP_BUYING_SCARLETT_FREE_HER = new DialogueNode("Alexa's Pet Shop", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_BUYING_SCARLETT_FREE_HER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ALEXAS_SHOP.getResponse(0, index);
		}
	};
	
}
