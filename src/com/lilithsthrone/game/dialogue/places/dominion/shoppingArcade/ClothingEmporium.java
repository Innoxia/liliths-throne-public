package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMMissionary;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBipeds;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.1.99
 * @author Innoxia
 */
public class ClothingEmporium {

	public static final DialogueNode EXTERIOR = new DialogueNode("Nyan's Clothing Emporium (Exterior)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXTERIOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.nyanIntroduced)) {
					return new Response("Enter", "Step inside Nyan's Clothing Emporium.", SHOP_CLOTHING_REPEAT);
					
				} else {
					return new Response("Enter", "Step inside Nyan's Clothing Emporium.", SHOP_CLOTHING);
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Arcade Entrance", "Fast travel to the entrance to the arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Stockroom", "Nyan has shown you into the stockroom.", SHOP_CLOTHING_STOCK_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.nyanIntroduced);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING_STOCK_ROOM = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_STOCK_ROOM");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_CLOTHING_REPEAT = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getAuthor() {
			if(Main.game.getNpc(Nyan.class).isPregnant() && !Main.game.getNpc(Nyan.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				return "Duner";
			} else {
				return "Innoxia";
			}
		}
		
		@Override
		public String getContent() {
			if(Main.game.getNpc(Nyan.class).isVisiblyPregnant() && !Main.game.getNpc(Nyan.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT_PREGNANT");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT");
			}
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Trade";
			} else if(index==1) {
				return "Talk";
			} else {
				return null;
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
					if(index==1) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanTalkedTo)) {
							return new Response("Small Talk", "You've already had a chat with Nyan today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Small Talk", "Talk with Nyan about her store, the weather, and other such topics.", ROMANCE_TALK) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 1f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanTalkedTo, true);
									if(Main.game.getNpc(Nyan.class).isVisiblyPregnant()) {
										Main.game.getNpc(Nyan.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==2) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanComplimented)) {
							return new Response("Compliment", "You've already complimented Nyan today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Compliment", "Compliment Nyan's appearance and abilities.", ROMANCE_COMPLIMENT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 3f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanComplimented, true);
									if(Main.game.getNpc(Nyan.class).isVisiblyPregnant()) {
										Main.game.getNpc(Nyan.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==3) {
						int requiredAffection = AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue();
						if(Main.game.getNpc(Nyan.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Flirt",
									"You can tell that attempting to flirt with Nyan would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Nyan's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Nyan.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanFlirtedWith)) {
							return new Response("Flirt", "You've already flirted with Nyan today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Flirt", "Flirt with Nyan a little.", ROMANCE_FLIRT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 4f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanFlirtedWith, true);
									if(Main.game.getNpc(Nyan.class).isVisiblyPregnant()) {
										Main.game.getNpc(Nyan.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==4) {
						int requiredAffection = AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue();
						if(Main.game.getNpc(Nyan.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Kiss",
									"You can tell that attempting to kiss Nyan would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Nyan's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Nyan.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanKissed)) {
							return new Response("Kiss", "You've already kissed Nyan today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Kiss", "You can tell from the way Nyan looks at you that she wouldn't mind being kissed. Lean forwards and give her what she wants.", ROMANCE_KISS) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 5f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanKissed, true);
									if(Main.game.getNpc(Nyan.class).isVisiblyPregnant()) {
										Main.game.getNpc(Nyan.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==5) {
						int requiredAffection = AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue();
						if(Main.game.getNpc(Nyan.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Make Out",
									"You can tell that attempting to make out with Nyan would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Nyan's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Nyan.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanMakeOut)) {
							return new Response("Make Out", "You've already made out with Nyan today. You can repeat this action tomorrow.", null);
							
						} else {
							return new Response("Make Out", "You can tell that Nyan is desperate for some intense physical contact with you. Lean forwards and passionately start making out with her.", ROMANCE_MAKE_OUT) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 6f));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanMakeOut, true);
									if(Main.game.getNpc(Nyan.class).isVisiblyPregnant()) {
										Main.game.getNpc(Nyan.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==6) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanGift)) {
							return new Response("Gift", "You've already given Nyan a gift today. You can repeat this action tomorrow.", null);
						} else {
							return new Response("Gift", "Give Nyan a gift (opens gift selection screen).", ROMANCE_GIFT) {
								@Override
								public DialogueNode getNextDialogue() {
									return GiftDialogue.getGiftDialogue(Main.game.getNpc(Nyan.class), SHOP_CLOTHING_REPEAT, 1, ROMANCE_GIFT, 1);
								}
								@Override
								public void effects() {
//									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanGift, true);
									if(Main.game.getNpc(Nyan.class).isVisiblyPregnant()) {
										Main.game.getNpc(Nyan.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
						
					} else if(index==10) {
						int requiredAffection = AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue();
						
						if(Main.game.getNpc(Nyan.class).getAffection(Main.game.getPlayer())<requiredAffection) {
							return new Response(
									"Sex",
									"You can tell that propositioning Nyan for sex would end in disaster."
										+ " You should work on getting to know her a little better first."
										+ " ([style.italicsBad(Requires Nyan's affection to be at least "+requiredAffection+", and is currently "+Main.game.getNpc(Nyan.class).getAffection(Main.game.getPlayer())+".)])",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanSex)) {
							return new Response("Sex", "You've already had sex with Nyan today. You can repeat this action tomorrow.", null);
							
						} else {
							return new ResponseSex("Sex",
									"Have sex with Nyan.",
									null, null, null, null, null, null,
									true, true,
									Main.game.getPlayer().getLegConfiguration().isBipedalPositionedGenitals()
										?new SMMissionary(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.MISSIONARY_KNEELING_BETWEEN_LEGS)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Nyan.class), SexSlotBipeds.MISSIONARY_ON_BACK)))
										:new SMGeneric(
												Util.newArrayListOfValues(Main.game.getPlayer()),
												Util.newArrayListOfValues(Main.game.getNpc(Nyan.class)),
												null,
												null),
									null,
									null,
									END_SEX,
									UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_SEX")) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanSex, true);
									if(Main.game.getNpc(Nyan.class).isVisiblyPregnant()) {
										Main.game.getNpc(Nyan.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN) {
					if(index==1) {
						return new Response("Report back", "Tell Nyan that you've dealt with the suppliers.", SHOP_REPORT_BACK) {
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(5000);
								Main.game.getNpc(Nyan.class).setSellModifier(1.25f);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 25));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.SIDE_UTIL_COMPLETE));
							}
						};
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_AGREED_TO_HELP) {
					if(index==1) {
						return new Response("Report back", "Report back to Nyan once you've dealt with the suppliers.", null);
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP) == Quest.RELATIONSHIP_NYAN_STOCK_ISSUES) {
					if(index==1) {
						return new Response("Offer help", "Tell Nyan that you'll help her with her supplier problem.", SHOP_OFFER_HELP) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Nyan.class).incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_STOCK_ISSUES_AGREED_TO_HELP));
							}
						};
					}
					
				} else if(!Main.game.getPlayer().hasQuest(QuestLine.RELATIONSHIP_NYAN_HELP)){
					if(index==1) {
						return new Response("Enchanted Clothing", "Ask Nyan if she stocks enchanted clothing.", SHOP_ENCHANTED_CLOTHING) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.RELATIONSHIP_NYAN_HELP));
							}
						};
					}
				}
				
				
				if (index == 0) {
					return new Response("Leave", "Tell Nyan that you've got to get going.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
							if(Main.game.getNpc(Nyan.class).isVisiblyPregnant()) {
								Main.game.getNpc(Nyan.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
						}
					};
					
				} else {
					return null;
				}
				
				
			} else if(responseTab==0) {
				if (index == 1) {
					return new ResponseTrade("Female Clothing", "Ask her what female clothing is available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonFemaleClothing()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseTrade("Female Lingerie", "Ask her what female lingerie is available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonFemaleUnderwear()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 3) {
					return new ResponseTrade("Female Accessories", "Ask her what female accessories are available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonFemaleAccessories()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 6) {
					return new ResponseTrade("Male Clothing", "Ask her what male clothing is available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonMaleClothing()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 7) {
					return new ResponseTrade("Male Underwear", "Ask her what male underwear is available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonMaleLingerie()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 8) {
					return new ResponseTrade("Male Accessories", "Ask her what male accessories are is available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonMaleAccessories()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 11) {
					return new ResponseTrade("Unisex Clothing", "Ask her what unisex clothing is available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonAndrogynousClothing()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 12) {
					return new ResponseTrade("Unisex Underwear", "Ask her what unisex underwear is available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonAndrogynousLingerie()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 13) {
					return new ResponseTrade("Unisex Accessories", "Ask her what unisex accessories are is available.", Main.game.getNpc(Nyan.class)){
						@Override
						public void effects() {
							Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getCommonAndrogynousAccessories()) {
								if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
									break;
								}
								Main.game.getNpc(Nyan.class).addClothing(c, false);
							}
						}
					};
					
				} else if (index == 5) {
					if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
						return new ResponseTrade("Specials", "Ask Nyan about any special items of clothing she might have in stock.", Main.game.getNpc(Nyan.class)){
							@Override
							public void effects() {
								Main.game.getNpc(Nyan.class).clearNonEquippedInventory();
		
								for (AbstractClothing c : ((Nyan) Main.game.getNpc(Nyan.class)).getSpecials()) {
									if(Main.game.getNpc(Nyan.class).isInventoryFull()) {
										break;
									}
									Main.game.getNpc(Nyan.class).addClothing(c, false);
								}
							}
						};
					} else {
						return new Response("Specials", "Nyan doesn't have any special items of clothing on offer at the moment...", null);
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell Nyan that you've got to get going.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
						}
					};
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SHOP_ENCHANTED_CLOTHING = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_SUPPLIER_PROBLEM");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_OFFER_HELP = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_OFFER_HELP");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_REPORT_BACK = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_REPORT_BACK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Exit the store.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT_EMBARRASSED"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROMANCE_TALK = new DialogueNode("Nyan's Clothing Emporium", "-", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_COMPLIMENT = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_FLIRT = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_KISS = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_MAKE_OUT = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode END_SEX = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Main.game.getNpc(Nyan.class))==0) {
				return UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_END_SEX_NO_ORGASM");
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_END_SEX");
			}
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROMANCE_GIFT = new DialogueNode("Nyan's Clothing Emporium", "-", true) {

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
}
