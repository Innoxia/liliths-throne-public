package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class ScarlettsShop {
	
	private static void spawnDeliveryNpcs() {
		Main.game.getNpc(Natalya.class).setLocation(Main.game.getPlayer(), false);
		
		String[] names = new String[] {"obedient centaur", "loyal centaur"};
		for(int i=0; i<2; i++) {
			NPC npc = new GenericSexualPartner(Gender.M_P_MALE, WorldType.EMPTY, Main.game.getWorlds().get(WorldType.EMPTY).getCell(PlaceType.GENERIC_HOLDING_CELL).getLocation(), false, (s)->s!=Subspecies.CENTAUR);
			
			npc.unequipAllClothing(npc, true, true);
			npc.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), Colour.CLOTHING_GOLD, false), true, npc);
			
			npc.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			npc.setBodySize(BodySize.FOUR_HUGE.getMedianValue());
			npc.setHeight(230+Util.random.nextInt(11));
			
			npc.setTesticleSize(TesticleSize.FIVE_MASSIVE);
			npc.setPenisCumStorage(750);
			npc.setPenisCumExpulsion(80);
			
			npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			npc.clearFetishes();
			npc.clearFetishDesires();
			npc.addFetish(Fetish.FETISH_SUBMISSIVE);
			npc.addFetish(Fetish.FETISH_ANAL_GIVING);
			npc.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.ZERO_HATE);
			npc.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.ZERO_HATE);
			
			npc.setAssVirgin(false);
			npc.setPenisVirgin(false);
			npc.setFaceVirgin(false);
			
			npc.setGenericName(names[i]);
			
			npc.setLocation(Main.game.getPlayer(), false);
			try {
				Main.game.addNPC(npc, false);
				Main.game.setActiveNPC(npc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static List<GameCharacter> getDeliveryNpcs() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		list.removeIf((npc)->npc.isUnique());
		return list;
	}
	
	public static final DialogueNode SCARLETTS_SHOP_EXTERIOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.isExtendedWorkTime()) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR_CLOSED");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Enter", "Scarlett's Shop is currently closed, and will re-open at six in the morning. You'll have to come back some time after then.", null);
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
					return new Response("Enter", "You should go and find Helena before entering Scarlett's Shop again.", null);
					
				} else {
					return new Response("Enter", "Enter the shop.", SCARLETTS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETTS_SHOP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
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
	
	public static final DialogueNode SCARLETT_IS_A_BITCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
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
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA));
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_SUPER_BITCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_SUPER_BITCH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_EXTERIOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_HELENA_RETURNS");
				
			} else if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_ROMANCE_FAILED");
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_GONE_HOME"));
					
				} else if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_CLOSED"));
				}
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)
					&& Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_9_FINISH)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED"));
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED_CLOSED"));
				}
				
			} else {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
					if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_FINISHED_OPEN"));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_FINISHED_CLOSED"));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR"));
				}
				
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_CLOSED"));
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_ROMANCE_5"));
				int days = (60*60*24*7) - (int)((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().helenaScarlettTimer)/(60*60*24));
				UtilText.addSpecialParsingString(Util.intToString(days), true);
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_TIMER"));
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_9_FINISH) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_ROMANCE_9"));
				int days = (60*60*24*14) - (int)((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().helenaScarlettTimer)/(60*60*24));
				UtilText.addSpecialParsingString(Util.intToString(days), true);
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_TIMER"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Enter", "Enter the shop.", HELENAS_SHOP) {
						@Override
						public void effects() {
							Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
							Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
							Main.game.getNpc(Scarlett.class).resetInventory(true);
							AbstractClothing collar = AbstractClothingType.generateClothing("innoxia_bdsm_metal_collar", Colour.CLOTHING_BLACK_STEEL, false);
							collar.setSealed(true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Helena.class));
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_BALLGAG, Colour.CLOTHING_PINK, false), true, Main.game.getNpc(Helena.class));
						}
					};
					
				} else if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
					return new Response("Enter",
							"Having given up on her plans of running a successful slave shop, Helena has permanently returned to her nest and has had this property of hers boarded up.",
							null);
					
				} else if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					return new Response("Enter",
							"Helena's shop is currently closed, and will re-open at nine in the morning. You'll have to come back some time after then.",
							null);
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
					if(Main.game.getSecondsPassed() - Main.game.getDialogueFlags().helenaScarlettTimer <= 60*60*24*7) {
						int days = (60*60*24*7) - (int)((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().helenaScarlettTimer)/(60*60*24));
						return new Response("Enter",
								"It hasn't been a week since you last spoke with Helena yet..."
										+ " You'll have to come back "+(days<=1?"tomorrow.":"after at least another "+Util.intToString(days)+" more days."),
								null);
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_9_FINISH) {
					if(Main.game.getSecondsPassed() - Main.game.getDialogueFlags().helenaScarlettTimer <= 60*60*24*14) {
						int days = (60*60*24*14) - (int)((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().helenaScarlettTimer)/(60*60*24));
						return new Response("Enter",
								"It hasn't been a week since you last spoke with Helena yet..."
										+ " You'll have to come back "+(days<=1?"tomorrow.":"after at least another "+Util.intToString(days)+" more days."),
								null);
					}
				}
				return new Response("Enter",
						"Enter the shop.",
						Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)
							?ROMANCE_SHOP_CORE
							:HELENAS_SHOP);

			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_INTRODUCTION");
					
			} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_OFFER_SCARLETT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP"); //TODO mention slaves in here for sale
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
				return new Response("Leave", "Say goodbye to Helena and exit her shop.", HELENAS_SHOP_EXTERIOR);
				
			} else if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Offer to buy", "Offer to buy Scarlett from Helena.", HELENAS_SHOP_SCARLETT_FOR_SALE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY));
							if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.punishedByHelena)) {
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
						return new Response("Buy Scarlett ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().scarlettPrice, "span")+")",
								"Buy Scarlett for "+Main.game.getDialogueFlags().scarlettPrice+" flames.", HELENAS_SHOP_BUYING_SCARLETT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().scarlettPrice));
								
								AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
								if (ballgag != null) {
									ballgag.setSealed(false);
									Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Helena.class));
								}
								
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
							}
						};
					}
					
				} else {
					return new Response("Slave Manager", "Enter the slave management screen.", HELENAS_SHOP) {
						@Override
						public boolean isTradeHighlight() {
							return true;
						}
						@Override
						public DialogueNode getNextDialogue() {
							CompanionManagement.initManagement(null, 0, null);
							return OccupantManagementDialogue.getSlaveryManagementDialogue(HELENAS_SHOP, Main.game.getNpc(Helena.class));
						}
					};
				}
			}
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY)) {
				if(index==2) {
					return new Response("Business",
							"Ask Helena why she's chosen to remain here and run this business herself."
									+ "<br/>[style.italicsQuestRomance(This is the start of Helena's romance quest, and <b>will be added for the next version</b>!)]",
							null);
//					if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)) {
//						return new Response("Business",
//								"Ask Helena why she's chosen to remain here and run this business herself."
//										+ "<br/>[style.italicsQuestRomance(This will start Helena's romance quest!)]",
//								ROMANCE_BUSINESS) {
//							@Override
//							public Colour getHighlightColour() {
//								return Colour.QUEST_RELATIONSHIP;
//							}
//						};
//					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_FOR_SALE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_FOR_SALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP.getResponse(0, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().scarlettPrice), true);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Calm her down", "Gently reassure Scarlett to get her to calm down.", HELENAS_SHOP_SCARLETT_PURCHASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_GENTLE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Shout at her", "Shout at Scarlett and remind her that she's now your property.", HELENAS_SHOP_SCARLETT_PURCHASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_SHOUT"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(5));
					}
				};
				
			} else if (index == 3) {
				return new Response("Slap her", "Slap Scarlett and remind her that she's now your property.", HELENAS_SHOP_SCARLETT_PURCHASED,
						Util.newArrayListOfValues(Fetish.FETISH_SADIST),
						CorruptionLevel.FOUR_LUSTFUL,
						null, null, null) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_SLAP"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -15));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(10));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_PURCHASED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Keep her", "You decide to keep Scarlett as your slave.", HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
					}
				};

			} else if (index == 2) {
				return new Response("Free her", "You decide to grant Scarlett her freedom.", HELENAS_SHOP_BUYING_SCARLETT_FREE_HER) {
					@Override
					public void effects() {
						
						AbstractClothing collar = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK);
						if(collar!=null) {
							collar.setSealed(false);
							Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(collar, true, Main.game.getNpc(Helena.class));
						}
						
						((Scarlett) Main.game.getNpc(Scarlett.class)).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.REMOVE_SEALS, EquipClothingSetting.ADD_ACCESSORIES));
						
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
						Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Exit the shop.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT_FREE_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT_FREE_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Exit the shop.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	
	// Helena romance quest:
	
	public static final DialogueNode ROMANCE_SHOP_CORE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_1_OFFER_HELP) {
				//TODO if scarlett is companion, Helena comments
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP")
						+ UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_1");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				//TODO if scarlett is companion, Helena comments
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2"));
				if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
						|| Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2_PAINT"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2_NO_PAINT"));
				}
				return sb.toString();
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR) {
				//TODO if scarlett is companion, Helena comments
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_3_PAINT_EXTERIOR");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				//TODO if scarlett is companion, Helena comments
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_3_PAINT_SIGN");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SELLING_SCARLETT"); // Helena demands you sell Scarlett to her
				} else {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_TOLD_TO_LEAVE"); // Helena tells you to come back in a week, once she's got everything set up
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5"); // Helena is with a client; Scarlett tells you to wait on the sofa
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_6_ADVERTISING) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_6"); // Helena asks you if you've put up the posters yet
					
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_7_MORE_ADVERTISING) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_7"); // Helena asks you if you've given out the cards yet
					
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_8_RETURN_TO_HELENA) {
				//TODO Change into immediate grand opening preparation scene (for tomorrow - you have to stay overnight and work with Scarlett to get things ready)
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_8"); // You tell Helena that you've given out the cards, she tells you to come back in two weeks once the effects are known for a potential reward
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_9_FINISH) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_9"); // Helena tells you to come into the back room
			}
			
			//TODO vary based on virginity loss
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_COMPLETE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index==0
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_8_RETURN_TO_HELENA
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_9_FINISH) {
				return new Response("Leave", "Say goodbye to Helena and exit her shop.", HELENAS_SHOP_EXTERIOR);
			}
			
			if(index == 1) {
				//TODO Remove/change when romance quest done
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
					return new Response("Design slave", "Ask Helena about designing a custom slave.", null);
				}
				if((Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_2_PURCHASE_PAINT)
							|| Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
							|| Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM))
						&& Main.game.getNpc(Helena.class).getSlavesOwned().isEmpty()) {
					return new Response("Slave Manager", "Helena is not currently interested in buying or selling slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", ROMANCE_SHOP_CORE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(ROMANCE_SHOP_CORE, Main.game.getNpc(Helena.class));
					}
				};
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_1_OFFER_HELP) {
				if(index==2) {
					return new Response("Offer help", "Tell Helena that you'd be willing to provide whatever help she needs in order to improve her business.", ROMANCE_OFFER_HELP);
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(index==2) {
					if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
						return new Response("Paint", "Show Helena the 'Purple-star' can of golden paint which she asked for.", ROMANCE_PAINT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT_PREMIUM"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR));
								Main.game.getPlayer().removeItemByType(ItemType.PAINT_CAN_PREMIUM);
							}
						};
						
					} else if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
						return new Response("Paint", "Show Helena the can of 'Bronze-star' golden paint, and hope that she doesn't notice that it's not the exact one she asked for...", ROMANCE_PAINT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaCheapPaint, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT_STANDARD"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR));
								Main.game.getPlayer().removeItemByType(ItemType.PAINT_CAN);
							}
						};
						
					} else {
						return new Response("Paint", "You haven't yet purchased the golden paint which Helena asked for...", null);
					}
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR) {
				if(index==2) {
					return new Response("Strip paint", "Do as Helena ordered and strip all the old, peeling paint from the store's frontage.", ROMANCE_PAINTING_A) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR));
						}
					};
				}
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR) {
				if(index==2) {
					return new Response("Paint frontage", "Do as Helena ordered and paint the entire frontage in a fresh new coat of white paint.", ROMANCE_PAINTING_B);
				}
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				if(index==2) {
					return new Response("Take paint", "Do as Helena instructs and take the golden paint outside.", ROMANCE_PAINTING_C);
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				if(index==2) {
					if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
						return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 1);
						
					} else {
						return new Response("Leave", "Do as Helena says and leave her and Scarlett to get the business up and running.", ROMANCE_EXIT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_LEAVE"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
								Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
								Main.game.getDialogueFlags().helenaScarlettTimer = Main.game.getSecondsPassed();
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
						return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 2);
					}
					
				} else if(index==5) {
					if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
						return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 5);
					}
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				if(index==2) {
					return new Response("Wait", "Do as Scarlett asks and wait on the sofa while Helena finishes with her client.", ROMANCE_ADVERTISING);
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_8_RETURN_TO_HELENA) {
				if(index==2) {
					return new Response("Leave", "Do as Helena says and leave her and Scarlett to get the business up and running.", ROMANCE_EXIT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_ADVERTISING_FINISHED_LEAVE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_9_FINISH));
							Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
							Main.game.getDialogueFlags().helenaScarlettTimer = Main.game.getSecondsPassed();
						}
					};
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_9_FINISH) {
				if(index==2) {
					return new Response("Wait", "Do as Scarlett asks and wait on the sofa while Helena finishes with her client.", ROMANCE_END);
				}
			}
			// TODO if complete, Scarlett talk in action 5
			// She tells you that now she's working for Helena, she never has a chance to seduce harpies, so tehre's no point in keeping her cock small and feminine (which is what most harpies like)
			// Asks you to bring a potion that will grow her cock
			
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_BUSINESS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thank her", "Perhaps she's waiting for you to thank her?", ROMANCE_BUSINESS_FOLLOWUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_THANK_HER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_HELENA));
					}
				};
				
			} else if(index==2) {
				return new Response("Prompt her", "Tell her to get on with it then.", ROMANCE_BUSINESS_FOLLOWUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_PROMPT_HER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_HELENA));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_BUSINESS_FOLLOWUP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_FOLLOWUP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(0, index);
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()>=10_000) {
					return new Response("Pay ("+UtilText.formatAsMoney(10_000, "span")+")", "Pay Helena the ten thousand flames which she's asking for.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAY"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getNpc(Helena.class).incrementMoney(10_000);
						}
					};
					
				} else if(Main.game.getPlayer().getMoney()>0) {
					return new Response("Pay ("+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")+")", "Tell Helena that you don't have ten thousand flames, so cannot pay her.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(Util.intToString(Main.game.getPlayer().getMoney()), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAY_REDUCED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getPlayer().getMoney()));
							Main.game.getNpc(Helena.class).incrementMoney(Main.game.getPlayer().getMoney());
						}
					};
					
				} else {
					return new Response("Cannot pay", "Tell Helena that you don't have any flames on you at all, so cannot pay her.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_CANNOT_PAY"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Refuse", "Tell Helena that she's being extremely unreasonable; <i>she's</i> the one who should be paying <i>you</i>!", ROMANCE_OFFER_HELP_PAYMENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_REFUSE_TO_PAY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_PAYMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAYMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Supplies", "Do as Helena commands and fetch the box of supplies.", ROMANCE_OFFER_HELP_FETCH_SUPPLIES);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_FETCH_SUPPLIES = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_FETCH_SUPPLIES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait for Helena to find what it is she's looking for.", ROMANCE_OFFER_HELP_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_2_PURCHASE_PAINT));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue(null)), true);
			UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue(null)), false);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_A = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 60*60;
			} else {
				return 4*60*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_1");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave",
						Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
							?"You'll have to wait until Monday before continuing with your work..."
							:"You'll have to wait until tomorrow before continuing with your work...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_B = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
			spawnDeliveryNpcs();
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 60*60;
			} else {
				return 4*60*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_2", getDeliveryNpcs());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Introduction",
						"Introduce yourself as the person this [natalya.race] is looking for, and then proceed to take delivery of the furniture which must be in the back of the cart.",
						ROMANCE_PAINTING_FURNITURE_DELIVERY);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Don't offer any help, and instead stand back and wait for the centaurs to be done with their task.", ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT_NO_HELP"));
					}
				};
				
			} else if(index==2) {
				return new Response("Offer help", "Ask Natalya if there's anything you can do to help.", ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT_HELP"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Remain", "Wait next to the cart for Natalya to return.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 15*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_WAIT"));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.isAnalContentEnabled()) {
					return new Response("Follow",
							"You get the feeling that following Natalya down the alleyway would lead to something you'd rather not see..."
									+ "<br/>[style.italicsMinorBad(Natalya's scenes involve anal content, and as such will be disabled for as long as your 'Anal Content' setting is turned off.)]",
							null);
				}
				return new Response("Follow", "Follow Natalya down the alleyway and see what she's up to.", ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : getDeliveryNpcs()) {
				Main.game.banishNPC((NPC) npc);
			}
			Main.game.getNpc(Natalya.class).returnToHome();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR));
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
				return new Response("Lock up",
						"Lock up the store and prepare to leave.",
						ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP = new DialogueNode("", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SlaverAlleyDialogue.ALLEYWAY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and leave Natalya to get herself off without your help.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 15*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_LEAVE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if(index==2) {
				return new Response("Submit",
						"Do as the dominant [natalya.race] commands, and after calling her 'Mistress', kneel down before her and submit to whatever plans she has in store for you...",
						ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerSubmittedToNatalya, true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Obey", "Do as Mistress Natalya commands and start stroking her thick horse-like cock.",
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public boolean isAbleToSkipSexScene() {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return false;
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return getForeplayPreference(character, targetedCharacter);
								}
								return character.getMainSexPreference(targetedCharacter);
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								Map<GameCharacter, List<SexAreaInterface>> map = new HashMap<>();
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(SexAreaOrifice.MOUTH));
								map.put(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(SexAreaOrifice.ANUS));
								return map;
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.PULL_OUT;
							}
							@Override
							public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
								if(!character.isPlayer()) {
									if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
										return OrgasmCumTarget.FACE;
									} else {
										return OrgasmCumTarget.FLOOR;
									}
								}
								return null;
							}
						},
						null,
						null,
						ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX = new DialogueNode("Finished", "Mistress Natalya has finished with you for now.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Mistress Natalya to see if the two centaurs have finished unpacking the furniture yet.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX_FOLLOW"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Paint sign", "Under the close supervision of Helena, use the golden paint you purchased to paint the words 'Helena's Boutique' over the shop's entrance.", ROMANCE_PAINTING_C_PAINT_SIGN);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C_PAINT_SIGN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 10*60;
			}
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_PAINT_SIGN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Helena back into her store.", ROMANCE_PAINTING_C_FINISHED);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Make tea",
						"Do as Helena says and make some tea for the two of you.",
						ROMANCE_PAINTING_C_FINISHED_SCARLETT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_TEA"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse",
						"Refuse to make Helena some tea, and instead bluntly ask her if there's anything else she requires of you.",
						ROMANCE_PAINTING_C_FINISHED_SCARLETT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_NO_TEA"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_SCARLETT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Read", "Read the letter which Helena just handed to you.", ROMANCE_PAINTING_C_FINISHED_LETTER) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED_LETTER = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_LETTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
				if(index==1) {
					if(Main.game.getPlayer().hasCompanion(Main.game.getNpc(Scarlett.class))) {
						return new Response("Sell Scarlett ("+UtilText.formatAsMoney(10_000, "span", Colour.GENERIC_GOOD)+")", "Sell Scarlett back to Helena for "+UtilText.formatAsMoney(10_000)+".", ROMANCE_SCARLETT_SOLD) {
							@Override
							public void effects() {
								Main.game.getPlayer().removeCompanion(Main.game.getNpc(Scarlett.class));
								Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
								Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
								
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
							}
						};
						
					} else {
						return new Response("Sell Scarlett ("+UtilText.formatAsMoneyUncoloured(10_000, "span")+")", "You need to bring Scarlett here as your companion before being able to sell [scarlett.herHim] to Helena!", null);
					}
					
				} else if(index==2) {
					if(Main.game.getPlayer().hasCompanion(Main.game.getNpc(Scarlett.class))) {
						return new Response("Give Scarlett", "Give Scarlett back to Helena and do not accept the "+UtilText.formatAsMoney(10_000)+" she's offering you.", ROMANCE_SCARLETT_SOLD) {
							@Override
							public void effects() {
								Main.game.getPlayer().removeCompanion(Main.game.getNpc(Scarlett.class));
								Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
								Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
								
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_GIVEN_AWAY"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
							}
						};
						
					} else {
						return new Response("Give Scarlett", "You need to bring Scarlett here as your companion before being able to give [scarlett.herHim] away to Helena!", null);
					}
				}
				if(!Main.game.getPlayer().hasCompanion(Main.game.getNpc(Scarlett.class))) {
					if(index==3) {
						return new Response("Agree", "Agree to go and fetch Scarlett and bring [scarlett.herHim] back here.", ROMANCE_EXIT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FINISHED_LETTER_END_AS_OWNER"));
								Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
							}
						};
					}
				}
				if(index==5) {
					return new Response("Refuse",
							"Refuse to sell Scarlett to Helena."
									+ "<br/>[style.italicsBad(This will fail the quest, and without your help, Helena will have to close down the store and return to the Harpy Nests!)]",
									ROMANCE_EXIT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_REFUSED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setAffection(Main.game.getPlayer(), -100));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_FAILED)); //TODO test
							Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
							Main.game.getNpc(Helena.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
						}
					};
				}
				return null;
			}
			
			if(index==1) {
				if(Main.game.getNpc(Scarlett.class).getHomeWorldLocation()==WorldType.HARPY_NEST) {
					return new Response("Agree", "Agree to go and fetch Scarlett from Helena's nest.", ROMANCE_EXIT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FINISHED_LETTER_END_FETCHING"));
							Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
						}
					};
					
				} else {
					return new Response("Agree", "Agree to track down Scarlett and purchase her for Helena.", ROMANCE_EXIT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FINISHED_LETTER_END_BUYING"));
							Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
							Main.game.getNpc(GenericMaleNPC.class).addSlave(Main.game.getNpc(Scarlett.class)); //TODO test
							Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ANTIQUES);
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode ROMANCE_EXIT = new DialogueNode("", "", true) {
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
			return SlaverAlleyDialogue.ALLEYWAY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_SCARLETT_SOLD = new DialogueNode("", "", true) {
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
				return new Response("Leave", "Do as Helena says and leave her and Scarlett to get the business up and running.", ROMANCE_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD_LEAVE"));
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
						Main.game.getDialogueFlags().helenaScarlettTimer = Main.game.getSecondsPassed();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_ADVERTISING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Posters", "Wait for Scarlett to return with the posters.", ROMANCE_ADVERTISING_POSTERS) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ROLLED_UP_POSTERS), false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_6_ADVERTISING));
					}
				};
			}
			return null;
		}
	};
	
	//TODO Helena offers player harpy TF potion - she thinks that it would be better if it were to be seen that a harpy is working for her

	public static final DialogueNode ROMANCE_ADVERTISING_POSTERS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Compliment", "Compliment Helena on how beautiful she looks on the posters.", ROMANCE_ADVERTISING_POSTERS_END) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_COMPLIMENT"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Question", "Agree with Scarlett and question how these posters are supposed to advertise the business.", ROMANCE_ADVERTISING_POSTERS_END) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_QUESTION"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_ADVERTISING_POSTERS_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			//TODO need work late on Fridays
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Having received your orders from Helena, you're ready to set out into Slaver Alley and put up these posters.", ROMANCE_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_END_LEAVE"));
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	//TODO attend grand reopening and have to do several events
	
	public static final DialogueNode ROMANCE_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Helena into the back room for your reward.", ROMANCE_END_REWARD);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_END_REWARD = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_REWARD");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Close eyes", "Do as Helena orders and close your eyes.", ROMANCE_ADVERTISING_POSTERS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED_CLOSE_EYES"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.SIDE_UTIL_COMPLETE));
					}
				};
				
			} else if(index==2) {
				return new Response("Peek", "Pretend to close your eyes, but secretly peek to see what it is Helena is planning.", ROMANCE_ADVERTISING_POSTERS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED_PEEK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.SIDE_UTIL_COMPLETE));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_END_KISSED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Helena back out into the shop.", ROMANCE_SHOP_CORE);
			}
			return null;
		}
	};
	
}
