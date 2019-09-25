package com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.dominion.SMBraxDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5
 * @author Innoxia
 */
public class EnforcerHQDialogue {
	

	public static final DialogueNode EXTERIOR = new DialogueNode("Enforcer HQ", "Enforcer HQ", false) {
		@Override
		public int getSecondsPassed() {
			return CityPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "EXTERIOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Enter", "Cross the grounds and enter the Enforcer HQ."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Corridor", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("Entrance hall", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "ENTRANCE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave the Enforcer HQ."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WAITING_AREA = new DialogueNode("Waiting area", "-", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "WAITING_AREA");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("Locked office", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "OFFICE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CELLS_OFFICE = new DialogueNode("Cells office", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CELLS_OFFICE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CELL = new DialogueNode("Cell", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CELL");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode GUARDED_DOOR = new DialogueNode("Guarded door", "-", true) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "GUARDED_DOOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && (!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.accessToEnforcerHQ) || Main.game.isBraxMainQuestComplete())) {
				return new ResponseEffectsOnly("Step back", "You don't really see much option other than to do as the enforcer says.") {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "GUARDED_DOOR_STEP_BACK"));
						
						Main.game.setActiveWorld(Main.game.getActiveWorld(), PlaceType.ENFORCER_HQ_WAITING_AREA, true);
					}
				};
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.accessToEnforcerHQ) || Main.game.isBraxMainQuestComplete();
		}
	};
	
	public static final DialogueNode LOCKED_DOOR = new DialogueNode("Locked door", "-", true) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "LOCKED_DOOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Step back", "There's nothing else for you to do other than step back from the locked door...", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "LOCKED_DOOR_STEP_BACK"));
						
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CORRIDOR, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode RECEPTION_DESK = new DialogueNode("Reception desk", "-", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Step back",
						"Tell Candi that you'll be back later and step away from her desk, allowing her to continue applying her makeup.",
						WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA, false);
						if(!Main.game.getNpc(CandiReceptionist.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) && Main.game.getNpc(CandiReceptionist.class).isVisiblyPregnant()) {
							Main.game.getNpc(CandiReceptionist.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						if(Main.game.getNpc(Brax.class).isSlave() && Main.game.getNpc(Brax.class).getOwner().equals(Main.game.getNpc(CandiReceptionist.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
						}
					}
				};
			}
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_BUYING_BRAX)) {
				if(index==1) {
					if(Main.game.getSecondsPassed()-Main.game.getDialogueFlags().candiSexTimer>60*60*12) {
						return new ResponseSex("Help Candi",
								"Agree to help Candi deal with her overwhelming horniness.",
								null, null, null, null, null, null,
								true,
								true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(CandiReceptionist.class), SexSlotStanding.STANDING_SUBMISSIVE))),
								null,
								null,
								AFTER_SEX_CANDI,
								UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "START_SEX_CANDI")) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(CandiReceptionist.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) && Main.game.getNpc(CandiReceptionist.class).isVisiblyPregnant()) {
									Main.game.getNpc(CandiReceptionist.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
							}
						};
						
					} else {
						return new Response("Help Candi",
								"You've recently helped Candi to deal with her overwhelming horniness, but it's only going to be a matter of hours before she's begging to have sex with you again...",
								null);
					}
				}
				
			} else if(Main.game.isBraxMainQuestComplete()) {
				if(Main.game.getNpc(Brax.class).isSlave() && Main.game.getNpc(Brax.class).getOwner().equals(Main.game.getNpc(CandiReceptionist.class))) {
					if (index == 1) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new ResponseSex("Punish [brax.name]", "Have dominant sex with [brax.name].",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BRAX")) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
							return new ResponseSex("Punish [brax.name]", "Have dominant sex with [brax.name].",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BREE")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else {
							return new ResponseSex("Punish [brax.name]", "Have dominant sex with [brax.name].",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BRANDI")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
						}
						
					} else if (index == 2) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new ResponseSex("Get punished by [brax.name]", "Get [brax.name] to take out [brax.his] frustration on you.", Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
									null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
									true, false,
									new SMBraxDoggy(
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISHED_BY_BRAX")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else {
							return new Response("Get punished by [brax.name]", "[brax.Name] is too submissive to punish you. If you want to have sex with [brax.him], you'll have to take charge.", null);
						}
						
					} else if (index == 3) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new Response("Feminise [brax.name]", "Transform [brax.name] into a wolf-girl.", INTERIOR_SECRETARY_BRAX_FEMINISE){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
							return new Response("Bimbofy [brax.name]", "Transform [brax.name] into a brain-dead bimbo.", INTERIOR_SECRETARY_BRAX_BIMBOFY){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
						}
						
					} else if (index == 4) {
						if(!Main.game.getPlayer().isHasSlaverLicense()) {
							return new Response("Buy [brax.name]", "As you don't have a slaver license, you're unable to own slaves, and so couldn't buy [brax.name] even if Candi was willing to sell [brax.herHim].", null);
						}
						if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)) {
							return new Response("Buy [brax.name]", "Ask Candi if she'd be willing to sell [brax.name] to you.", BUYING_BRAX_INITIAL){
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_BUYING_BRAX));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(500));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_START
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_PERFUME) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_PERFUMES)) {
								return new Response("Deliver perfumes", "Give Candi the bottles of perfume which you collected from Kate.", BUYING_BRAX_PERFUME_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_PERFUMES);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_PERFUMES));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_LOLLIPOPS));
									}
								};
								
							} else {
								return new Response("Deliver perfumes", "You need to collect Candi's perfumes from the shop 'Succubi's Secrets' in the Shopping Arcade before being able to deliver them to her!", null);
							}
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LOLLIPOPS
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_LOLLIPOPS) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_CONTRABAND)) {
								return new Response("Deliver lollipops", "Give Candi the box of contraband lollipops which you retrieved from the Harpy Nests' Enforcer checkpoint.", BUYING_BRAX_LOLLIPOP_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_CONTRABAND);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_CONTRABAND));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_LIPSTICK));
									}
								};
								
							} else {
								return new Response("Deliver lollipops", "You need to collect the box of lollipops from the Harpy Nests' Enforcer checkpoint before being able to deliver them to her!", null);
							}
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LIPSTICK
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_LIPSTICK) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_HUNDRED_KISSES)) {
								return new Response("Deliver lipstick", "Give Candi the box of 'A Hundred Kisses'.", BUYING_BRAX_LIPSTICK_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_HUNDRED_KISSES);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_HUNDRED_KISSES));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.SIDE_UTIL_COMPLETE));
										Main.game.getPlayer().addSlave(Main.game.getNpc(Brax.class));
										
										if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bimbofiedBrax)) {
											Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfskirt", Colour.CLOTHING_BLACK, false), false);
											Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_flsldshirt", Colour.CLOTHING_PINK, false), false);
											Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_TIE, Colour.CLOTHING_BLACK, false), false);
											
											Main.game.getNpc(Brax.class).setObedience(50);
										} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feminisedBrax)) {
											Main.game.getNpc(Brax.class).setObedience(-20);
										} else {
											Main.game.getNpc(Brax.class).setObedience(-80);
										}
										Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_uniques_enfdjacket_brax", Colour.CLOTHING_BLACK, false), false);
										Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdbelt", Colour.CLOTHING_DESATURATED_BROWN, false), false);
										Main.game.getNpc(Brax.class).addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_pcap", Colour.CLOTHING_BLACK, false), false);
										
										Main.game.getNpc(Brax.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION);
									}
								};
								
							} else {
								return new Response("Deliver lipstick", "You need to collect the box of 'A Hundred Kisses' from the shop 'Ralph's Snacks' in the Shopping Arcade before being able to deliver them to her!", null);
							}
						}
					}
				}
				
			} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_C_WOLFS_DEN && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.accessToEnforcerHQ)) {
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_BIMBO)) {
						return new Response("Greet Candi", "Like, ohmygosh, she's so pretty and stuff!", INTERIOR_SECRETARY_BIMBO);
						
					} else {
						return new Response("Greet Candi", "Get her attention by saying hello.", INTERIOR_SECRETARY,
								null, null, null, null, null);
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY = new DialogueNode("Enforcer HQ", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("[brax.name]", "Tell her that you're here to see [brax.name].", INTERIOR_SECRETARY_BRAX){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.accessToEnforcerHQ);
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};

			} else if (index == 0) {
				return new Response("Step back",
						"Tell Candi that you'll be back later and step away from her desk, allowing her to continue applying her makeup.",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_LEAVE"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX = new DialogueNode("Enforcer HQ", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BIMBO = new DialogueNode("Enforcer HQ", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BIMBO");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Like, [brax.name] and stuff", "Tell her that you're here to see [brax.name].", INTERIOR_SECRETARY_BRAX_BIMBO){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.accessToEnforcerHQ);
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};

			} else if (index == 0) {
				return new Response("Step back",
						"Tell Candi that you'll be back later and step away from her desk, allowing her to continue applying her makeup.",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BIMBO_LEAVE"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBO = new DialogueNode("Enforcer HQ", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BIMBO");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_FEMINISE = new DialogueNode("Enforcer HQ", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Bree",
						"You and Candi force-feed [brax.name] his own potion, turning him into a wolf-girl named Bree.",
						INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feminisedBrax, true);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("Bree", "Bree", "Bree"));
						
						Main.game.getNpc(Brax.class).removeFetish(Fetish.FETISH_DOMINANT);
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_SUBMISSIVE);
						
						Main.game.getNpc(Brax.class).setFemininity(75);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.C.getMeasurement());
						
						Main.game.getNpc(Brax.class).setBreastRows(3);
						
						Main.game.getNpc(Brax.class).setHipSize(HipSize.THREE_GIRLY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.FOUR_LARGE.getValue());
						Main.game.getNpc(Brax.class).setPenisType(PenisType.NONE);
						Main.game.getNpc(Brax.class).setVaginaType(VaginaType.WOLF_MORPH);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.ONE_SLIGHTLY_MOIST.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.ONE_RIGID.getValue());

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						
						Main.game.getNpc(Brax.class).setHeight(175);
						
						Main.game.getNpc(Brax.class).setVaginaVirgin(true);

						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} if (index == 2) {
				return new Response("Bree (futa)",
						"You and Candi force-feed [brax.name] his own potion, turning him into a wolf-girl futanari named Bree.",
						INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feminisedBrax, true);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("Bree", "Bree", "Bree"));
						
						Main.game.getNpc(Brax.class).removeFetish(Fetish.FETISH_DOMINANT);
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_SUBMISSIVE);
						
						Main.game.getNpc(Brax.class).setFemininity(75);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.C.getMeasurement());
						
						Main.game.getNpc(Brax.class).setBreastRows(3);
						
						Main.game.getNpc(Brax.class).setHipSize(HipSize.THREE_GIRLY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.FOUR_LARGE.getValue());
						Main.game.getNpc(Brax.class).setVaginaType(VaginaType.WOLF_MORPH);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.ONE_SLIGHTLY_MOIST.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.ONE_RIGID.getValue());

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						
						Main.game.getNpc(Brax.class).setHeight(175);
						
						Main.game.getNpc(Brax.class).setVaginaVirgin(true);

						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Change your mind and leave [brax.name] the way he is.",  WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_CHANGE_MIND"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED = new DialogueNode("Enforcer HQ", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex with [brax.name]", "Have sex with [brax.name].",
						false, false,
						new SMStanding(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_SEX"));
				
			} else if(index==2) {
				return new Response("Decline", "Decide against having sex with Bree.", WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_NO_SEX"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBOFY = new DialogueNode("Enforcer HQ", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Brandi", "Transform Bree into a brain-dead bimbo, called Brandi.", INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimbofiedBrax);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("Brandi", "Brandi", "Brandi"));
						
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_BIMBO);
						
						Main.game.getNpc(Brax.class).setFemininity(100);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.KK.getMeasurement());
						Main.game.getNpc(Brax.class).setHipSize(HipSize.SEVEN_ABSURDLY_WIDE.getValue());
						Main.game.getNpc(Brax.class).setAssWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.SEVEN_GIGANTIC.getValue());
						
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());

						Main.game.getNpc(Brax.class).setHeight(162);

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
//						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, CoveringPattern.NONE, Colour.COVERING_BLEACH_BLONDE, false, Colour.COVERING_BLEACH_BLONDE, false), true);
						
						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 2) {
				return new Response("Brandi (futa)", "Transform Bree into a brain-dead futanari bimbo, called Brandi.",
						INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimbofiedBrax);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("Brandi", "Brandi", "Brandi"));
						
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_BIMBO);
						
						Main.game.getNpc(Brax.class).setFemininity(100);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.KK.getMeasurement());
						Main.game.getNpc(Brax.class).setHipSize(HipSize.SEVEN_ABSURDLY_WIDE.getValue());
						Main.game.getNpc(Brax.class).setAssWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.SEVEN_GIGANTIC.getValue());

						Main.game.getNpc(Brax.class).setPenisType(PenisType.LUPINE);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());

						Main.game.getNpc(Brax.class).setHeight(162);

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
//						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, CoveringPattern.NONE, Colour.COVERING_BLEACH_BLONDE, false, Colour.COVERING_BLEACH_BLONDE, false), true);
						
						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Change your mind and leave Bree the way she is.", WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_CHANGE_MIND"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED = new DialogueNode("Enforcer HQ", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex with Brandi", "Have sex with Brandi.",
						true, false,
						new SMStanding(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_SEX"));

			} else if(index==2) {
				return new Response("Decline", "Decide against having sex with Brandi.",  WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_NO_SEX"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Finished", "Return to the Reception desk.", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "AFTER_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_CANDI = new DialogueNode("Finished", "Step back around to the other side of the reception desk.", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "AFTER_SEX_CANDI");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode BUYING_BRAX_INITIAL = new DialogueNode("Reception desk", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_INITIAL");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_PERFUME_DELIVERY = new DialogueNode("Reception desk", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_PERFUME_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_LOLLIPOP_DELIVERY = new DialogueNode("Reception desk", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_LOLLIPOP_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_LIPSTICK_DELIVERY = new DialogueNode("Reception desk", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_LIPSTICK_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getSecondsPassed()-Main.game.getDialogueFlags().candiSexTimer>60*60*12) {
					return new ResponseSex("Help Candi",
							"Agree to help Candi deal with her overwhelming horniness.",
							null, null, null, null, null, null,
							true,
							true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(CandiReceptionist.class), SexSlotStanding.STANDING_SUBMISSIVE))),
							null,
							null,
							AFTER_SEX_CANDI,
							UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "START_SEX_CANDI"));
					
				} else {
					return new Response("Help Candi", "You've recently helped Candi to deal with her overwhelming horniness, but it's only going to be a matter of hours before she's begging to have sex with you again...", null);
				}
				
			} else 
				if(index==2) {
					return new Response("Refuse", "Refuse to help Candi with her overwhelming horniness, before stepping back into the waiting area.", WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "HELP_CANDI_DENIED"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};
				}
			return null;
		}
	};
	
	
	
}
