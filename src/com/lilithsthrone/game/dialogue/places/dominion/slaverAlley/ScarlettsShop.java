package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
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
	
	public static final DialogueNodeOld SCARLETTS_SHOP_EXTERIOR = new DialogueNodeOld("Scarlett's shop", ".", false) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld SCARLETTS_SHOP = new DialogueNodeOld("Scarlett's shop", ".", true) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld SCARLETT_IS_A_BITCH = new DialogueNodeOld("Scarlett's shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

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
	
	public static final DialogueNodeOld SCARLETT_IS_A_SUPER_BITCH = new DialogueNodeOld("Scarlett's shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

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
	
	
	public static final DialogueNodeOld ALEXAS_SHOP_EXTERIOR = new DialogueNodeOld("Alexa's Pet Shop", ".", false) {
		private static final long serialVersionUID = 1L;

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
							Main.game.getAlexa().addSlave(Main.game.getScarlett());
							Main.game.getScarlett().setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
							Main.game.getScarlett().resetInventory();
							AbstractClothing collar = AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_BLACK_STEEL, false);
							collar.setSealed(true);
							Main.game.getScarlett().equipClothingFromNowhere(collar, true, Main.game.getAlexa());
							Main.game.getScarlett().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_BALLGAG, Colour.CLOTHING_PINK, false), true, Main.game.getAlexa());
							Main.game.getScarlett().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_WRIST_RESTRAINTS, Colour.CLOTHING_PINK, false), true, Main.game.getAlexa());
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
	
	public static final DialogueNodeOld ALEXAS_SHOP = new DialogueNodeOld("Alexa's Pet Shop", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_INTRODUCTION");
					
			} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_OFFER_SCARLETT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP"); //TODO expand
			}
			
			// TODO new interior description once rennovated
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
						return new Response("Buy Scarlett (" + UtilText.getCurrencySymbol() + " "+Main.game.getDialogueFlags().scarlettPrice+")", "You need to obtain a slaver license from the Slavery Administration before you can buy Scarlett!", null);
						
					} else if(Main.game.getPlayer().getMoney() < Main.game.getDialogueFlags().scarlettPrice) {
						return new Response("Buy Scarlett (" + UtilText.getCurrencySymbol() + " "+Main.game.getDialogueFlags().scarlettPrice+")", "You don't have enough money to buy Scarlett.", null);
						
					} else {
						return new Response("Buy Scarlett (<span style='color:" + Colour.CURRENCY_GOLD.toWebHexString() + ";'>" + UtilText.getCurrencySymbol() + "</span> "+Main.game.getDialogueFlags().scarlettPrice+")"
								, "Buy Scarlett for "+Main.game.getDialogueFlags().scarlettPrice+" flames.", ALEXAS_SHOP_BUYING_SCARLETT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().scarlettPrice));
								
								AbstractClothing ballgag = Main.game.getScarlett().getClothingInSlot(InventorySlot.MOUTH);
								ballgag.setSealed(false);
								Main.game.getScarlett().unequipClothingIntoVoid(ballgag, true, Main.game.getAlexa());
								AbstractClothing wristRestraints = Main.game.getScarlett().getClothingInSlot(InventorySlot.WRIST);
								wristRestraints.setSealed(false);
								Main.game.getScarlett().unequipClothingIntoVoid(wristRestraints, true, Main.game.getAlexa());
								
								Main.game.getScarlett().setAffection(Main.game.getAlexa(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getScarlett().setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
								Main.game.getScarlett().setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getPlayer().addSlave(Main.game.getScarlett());
							}
						};
					}
					
				} else {
					return new Response("Slave Manager", "Enter the slave management screen.", ALEXAS_SHOP) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return OccupantManagementDialogue.getSlaveryManagementDialogue(Main.game.getAlexa());
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
	
	public static final DialogueNodeOld ALEXAS_SHOP_SCARLETT_FOR_SALE = new DialogueNodeOld("Alexa's Pet Shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_SCARLETT_FOR_SALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ALEXAS_SHOP.getResponse(0, index);
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_BUYING_SCARLETT = new DialogueNodeOld("Alexa's Pet Shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

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
						Main.game.getTextEndStringBuilder().append(Main.game.getScarlett().incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Shout at her", "Shout at Scarlett and remind her that she's now your property.", ALEXAS_SHOP_SHOUT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						Main.game.getTextEndStringBuilder().append(Main.game.getScarlett().incrementAffection(Main.game.getPlayer(), -2));
						Main.game.getTextEndStringBuilder().append(Main.game.getScarlett().incrementObedience(2));
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
						Main.game.getTextEndStringBuilder().append(Main.game.getScarlett().incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getScarlett().incrementObedience(5));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_GENTLE = new DialogueNodeOld("Alexa's Pet Shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_GENTLE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getSlaveryChoiceResponse(index);
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_SHOUT = new DialogueNodeOld("Alexa's Pet Shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_SHOUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getSlaveryChoiceResponse(index);
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_SLAP = new DialogueNodeOld("Alexa's Pet Shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

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
					Main.game.getScarlett().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
				}
			};

		} else if (index == 2) {
			return new Response("Free her", "You decide to grant Scarlett her freedom.", ALEXAS_SHOP_BUYING_SCARLETT_FREE_HER) {
				@Override
				public void effects() {
					
					AbstractClothing collar = Main.game.getScarlett().getClothingInSlot(InventorySlot.NECK);
					collar.setSealed(false);
					Main.game.getScarlett().unequipClothingIntoVoid(collar, true, Main.game.getAlexa());
					
					((Scarlett) Main.game.getScarlett()).getDressed();
					
					Main.game.getScarlett().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_ALEXAS_NEST, true);
					Main.game.getScarlett().setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
					Main.game.getScarlett().setAffection(Main.game.getPlayer(), AffectionLevel.ZERO_NEUTRAL.getMedianValue());
					Main.game.getPlayer().removeSlave(Main.game.getScarlett());
				}
			};

		} else {
			return null;
		}
	}
	
	public static final DialogueNodeOld ALEXAS_SHOP_BUYING_SCARLETT_KEEP_HER = new DialogueNodeOld("Alexa's Pet Shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "ALEXAS_SHOP_BUYING_SCARLETT_KEEP_HER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ALEXAS_SHOP.getResponse(0, index);
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_BUYING_SCARLETT_FREE_HER = new DialogueNodeOld("Alexa's Pet Shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

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
