package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.dominion.SMBraxDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
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
 * @version 0.3.4
 * @author Innoxia
 */
public class EnforcerHQDialogue {
	

	public static final DialogueNode EXTERIOR = new DialogueNode("Enforcer HQ", "Enforcer HQ", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
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

	public static final DialogueNode RECEPTION_DESK = new DialogueNode("Reception desk", "-", false) {

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
								UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "START_SEX_CANDI"));
						
					} else {
						return new Response("Help Candi", "You've recently helped Candi to deal with her overwhelming horniness, but it's only going to be a matter of hours before she's begging to have sex with you again...", null);
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
									Main.game.getDialogueFlags().values.add(DialogueFlagValue.seenBraxAfterQuest);
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
									Main.game.getDialogueFlags().values.add(DialogueFlagValue.seenBraxAfterQuest);
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
									Main.game.getDialogueFlags().values.add(DialogueFlagValue.seenBraxAfterQuest);
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
									Main.game.getDialogueFlags().values.add(DialogueFlagValue.seenBraxAfterQuest);
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
									Main.game.getDialogueFlags().values.add(DialogueFlagValue.seenBraxAfterQuest);
								}
							};
							
						} else if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
							return new Response("Bimbofy [brax.name]", "Transform [brax.name] into a brain-dead bimbo.", INTERIOR_SECRETARY_BRAX_BIMBOFY){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().values.add(DialogueFlagValue.seenBraxAfterQuest);
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
											Main.game.getNpc(Brax.class).setObedience(50);
										} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feminisedBrax)) {
											Main.game.getNpc(Brax.class).setObedience(-20);
										} else {
											Main.game.getNpc(Brax.class).setObedience(-80);
										}
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
						
						if(!Main.game.getNpc(CandiReceptionist.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) && Main.game.getNpc(CandiReceptionist.class).isVisiblyPregnant()) {
							Main.game.getNpc(CandiReceptionist.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
					}
				};

			} else if (index == 0) {
				return new ResponseEffectsOnly("Leave", "Tell Candi that you'll be back later and step away from her desk, allowing her to continue applying her makeup.") {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_LEAVE"));

							Main.game.setActiveWorld(Main.game.getActiveWorld(), PlaceType.ENFORCER_HQ_WAITING_AREA, true);

							if(!Main.game.getNpc(CandiReceptionist.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) && Main.game.getNpc(CandiReceptionist.class).isVisiblyPregnant()) {
								Main.game.getNpc(CandiReceptionist.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
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
					}
				};

			} else if (index == 0) {
				return new ResponseEffectsOnly("Leave", "Tell Candi that you'll be back later and step away from her desk, allowing her to continue applying her makeup.") {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BIMBO_LEAVE"));

							Main.game.setActiveWorld(Main.game.getActiveWorld(), PlaceType.ENFORCER_HQ_WAITING_AREA, true);
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
				return new Response("Leave", "Change your mind and leave [brax.name] the way he is.", INTERIOR_SECRETARY_BRAX_FEMINISE_CHANGE_MIND);
				
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
				return new Response("Decline", "Decide against having sex with Bree.", INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_NO_SEX);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_NO_SEX = new DialogueNode("Enforcer HQ", "-", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_NO_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_FEMINISE_CHANGE_MIND = new DialogueNode("Enforcer HQ", "-", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_CHANGE_MIND");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
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
				return new Response("Leave", "Change your mind and leave Bree the way she is.", INTERIOR_SECRETARY_BRAX_BIMBOFY_CHANGE_MIND);
				
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
				return new Response("Decline", "Decide against having sex with Brandi.", INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_NO_SEX);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_NO_SEX = new DialogueNode("Enforcer HQ", "-", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_NO_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBOFY_CHANGE_MIND = new DialogueNode("Enforcer HQ", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_CHANGE_MIND");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
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
	
	public static final DialogueNode BUYING_BRAX_INITIAL = new DialogueNode("Reception desk", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_INITIAL");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_PERFUME_DELIVERY = new DialogueNode("Reception desk", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_PERFUME_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_LOLLIPOP_DELIVERY = new DialogueNode("Reception desk", "", false) {
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
	
	
	//---------- [brax.namePos] office:
	
	
	public static final DialogueNode INTERIOR_BRAX = new DialogueNode("[brax.namePos] Office", "-", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "You soon find the door that you're looking for, marked with the words '[brax.name]: Chief of Dominion Operations'."
						+ " Wondering just how important this [brax.name] person must be to have a title like that, you reach up and knock on the door."
						+ " Almost instantly, a low, gruff voice barks out in response,"
						+ " [brax.speech(Enter!)]"
					+ "</p>"
					+ "<p>"
						+ "You push open the door and step into [brax.namePos] office."
						+ " As you take a quick glance around, the first thing you immediately notice is that there are numerous posters of scantily-clad girls plastered all over the walls."
						+ " Apart from that oddity, the only other features of any interest are the large mahogany desk on the other side of the room, and the figure who's sitting behind it."
					+ "</p>"
					+ "<p>"
						+ "As the door swings shut behind you, the person behind the desk, who you assume to be [brax.name], stands up."
						+ " He's a greater wolf-morph, and apart from the streak of dark black on the top of his head, is completely covered in [brax.skinColour] fur."
						+ " A pair of vivid [brax.eyeColour] eyes glare at you from across the room, and the wolf-like muzzle beneath them turns up into a snarl."
						+ " He slams his fist on the desk, and you see his huge muscles bulging underneath his enforcer's uniform."
					+ "</p>"
					+ "<p>"
					+ "[brax.speech(I told that stupid slut to stop handing out passes! Who the hell are you?!)]"
					+ " he growls, staring right at you."
					+ "</p>"
					+ (Main.game.getPlayer().getRace()==Race.WOLF_MORPH
						&& (Main.game.getPlayer().getRaceStage()==RaceStage.PARTIAL_FULL || Main.game.getPlayer().getRaceStage()==RaceStage.LESSER || Main.game.getPlayer().getRaceStage()==RaceStage.GREATER)
						&& Main.game.getPlayer().isFeminine()
							?"<p>"
							+ "As he finishes barking out his question, you see his hungry eyes drop down to roam over your wolf-like body, and you wonder if he has a weakness for wolf-girls."
							+ " Wanting to test your theory, you turn back to face the door, pushing it firmly shut while not-so-subtly sticking out your ass towards [brax.name]."
							+ " Turning back to face him, your suspicions are proven to be correct as you see his eyes fogged over with lust..."
							+ "</p>"
							:"");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Truth", "Tell [brax.name] who you are, and that you're here to find out what happened to Arthur.", INTERIOR_BRAX_TRUTH) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else if (index == 2) {
				return new Response("Lie", "You notice that all of the models in the posters are wolf-girls. Perhaps you could pretend that Arthur is a patron of an exclusive wolf-girl themed brothel that you so happen to own...",
						INTERIOR_BRAX_LIE,
						null, null, Util.newArrayListOfValues(Perk.OBSERVANT), null, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else if (index == 3) {
				return new Response("Wolf-tease", "Use your feminine wolf-like body to tease [brax.name] into giving you information about Arthur.", INTERIOR_BRAX_GETTING_TEASED,
						null, null, null, Femininity.FEMININE, Race.WOLF_MORPH){
					@Override
					public void effects(){
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.braxBeaten);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_REPEAT = new DialogueNode("[brax.namePos] Office", "-", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "As you step into [brax.namePos] office once again, his jaw drops. "
						+ "[brax.speech(W-What the hell are you doing back here?! Did that braindead bimbo give you <i>another</i> pass?!)]"
					+ "</p>"
					+ "<p>"
						+ "As you enter the office, ready for another fight, [brax.name] stands up, and you see the familiar sight of arcane fire being summoned around his arm."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(I guess you've just got a craving for wolf dick!)]"
						+" [brax.name] laughs, and you realise that if you lose again, you're going to be getting another taste of his cock..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[brax.name] looks like he's ready to give you another beating!", Main.game.getNpc(Brax.class));
					
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTERIOR_BRAX_TRUTH = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You decide that's it's probably for the best if you just tell the truth, "
						+ "[pc.speech(I'm here to find out what's happened to my friend, Arthur. His apartment was empty, and your name was on the arrest warrant stamped to his door.)]"
					+ "</p>"
					+ "<p>"
						+ "As you mention Arthur's name, [brax.name] lets out a dismissive grunt, and as you finish speaking, he huffs, "
						+ "[brax.speech(As if I'd tell you that! You come barging into my office, then expect me to hand out classified information?! I think I know exactly what punishment you deserve!)]"
					+ "</p>"
					+ "<p>"
						+ "As [brax.name] finishes speaking, you see the familiar sight of arcane fire being summoned around his arm."
						+ " From the angry expression on his face, it's not too hard to figure out that he intends to fight you."
						+ " You feel as though [brax.name] is being pretty unreasonable, and he seemed to be just itching for an excuse to try this 'punishment' out on someone..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "If you want to find out what happened to Arthur, you're going to have to fight [brax.name]!", Main.game.getNpc(Brax.class));
					
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTERIOR_BRAX_LIE = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
					+ "As you take a closer look at [brax.namePos] office, you notice that the scantily-clad model in every single poster is a wolf-girl."
					+ " Looking past [brax.namePos] desk, your eyes are drawn to a glass display cabinet sitting just behind him, and you see that it's full of little, half-naked, models of wolf-girls."
					+ " From your observations of [brax.namePos] office, it's safe to assume that he has a fetish for females of the same race as him."
					+ "</p>"
					+ "<p>"
					+ "Deciding to take a bold course of action, you make up a story right there on the spot, "
					+ UtilText.parsePlayerSpeech("Well, after I informed your secretary that I'm the owner of the establishment 'The She-wolf's Den', she said that you'd probably want to talk to me in person.")
					+ "</p>"
					+ "<p>"
					+ "Just as you're kicking yourself for thinking up such a ridiculous name, you notice [brax.namePos] snarl fading away, and you struggle to keep your composure as he impatiently blurts out, "
					+ "[brax.speech(Wait, what's all this about a she-wolf's den?)]"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Keep on bluffing", "Imply that 'The She-wolf's Den' is a brothel you own. If he'll give you information about Arthur, you'll give him VIP status.", INTERIOR_BRAX_LIE_BLUFFING);
					
			} else if (index == 2) {
				return new Response("Drop the act", "Tell [brax.name] that he's an idiot and you're here to find out what he's done with Arthur.", INTERIOR_BRAX_LIE_IDIOT_BRAX);
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_IDIOT_BRAX = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
					+ "Almost feeling sorry for [brax.name], you decide to tell him that he's a gullible idiot, "
					+ UtilText.parsePlayerSpeech("I can't believe you actually fell for that! Are you really the 'Chief of Dominion Operations'? That's just sad...")
					+ "</p>"
					+ "<p>"
					+ "As you reveal that he's been fooled, [brax.name] blushes and stammers out, "
					+ UtilText.parseSpeech("W-What do you mean? I-I knew you were lying all along! I don't even like wolf-girls! You'd better tell me what you're here for, <i>right now</i>!", Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
					+ "You can't help but laugh at [brax.namePos] stuttering denial of his all-too-apparent weakness, but as he finishes talking, you realise that he means business. "
					+ UtilText.parsePlayerSpeech("Well, if you want the truth, I'm here to find out what you've done with my friend, Arthur. His apartment was empty, and your name was on the arrest warrant stamped to his door.")
					+ "</p>"
					+ "<p>"
					+ "[brax.namePos] face turns back up into the same snarl that you saw when you first entered his office. "
					+ UtilText.parseSpeech("As if I'd tell you that! You come barging into my office, try to make a fool of me, then expect me to hand out classified information?! I think I know exactly what punishment you deserve!",
							Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
					+ "As [brax.name] finishes speaking, you see the familiar sight of arcane fire being summoned around his arm."
					+ " From the angry, although somewhat flustered, expression on his face, it's not too hard to figure out that he intends to fight you."
					+ " Although it was fun to wind him up, you're not too sure if you want to find out what punishment he has planned for you..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[brax.name] looks extremely embarrassed, and you're sure that you've given yourself at least a small advantage by tricking him like this!", Main.game.getNpc(Brax.class)){
					@Override
					public void effects(){
						Main.game.getNpc(Brax.class).setLustNoText(30);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
					+ "You wonder just how gullible this 'Chief of Dominion Operations' really is, and, pushing your luck, you continue bluffing, "
					+ UtilText.parsePlayerSpeech("You know, I pride myself on the fact that 'The She-wolf's Den' is only known only amongst the most important and influential people in Dominion."
							+ " You see, it takes people of that refinement to really appreciate the fact that I hire only the most beautiful and skillful of wolf-girls.")
					+ "</p>"
					+ "<p>"
					+ "You struggle not to laugh as you see [brax.namePos] eyes glazing over with lust. "
					+ UtilText.parseSpeech("So... erm... what do these wolf-girls do?", Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerSpeech("<i>Anything</i> you want,")
					+" you reply, and as you see that [brax.name] is totally taken in by your ruse, you decide to move onto business, "
							+ UtilText.parsePlayerSpeech("I'll tell you what, I think the 'Chief of Dominion Operations' deserves to be on the VIP list. However, I have a slight problem that needs solving before that...")
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Anything!", Main.game.getNpc(Brax.class))
					+" [brax.name] blurts out."
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerSpeech("An acquaintance of mine, going by the name of Arthur, owes me a good deal of money, you see,"
							+ " but when I went round to see him, his apartment was empty, and your name was on an arrest warrant stamped to his door.")
					+ "</p>"
					+ "<p>"
					+ "[brax.name] fidgets uncomfortably as you say Arthur's name. "
					+ UtilText.parseSpeech("Ah... Erm, well, you see, I really shouldn't-", Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerSpeech("You know, many of our patrons like to take two or three girls at once...")
					+ "</p>"
					+ "<p>"
					+ "You don't think you've ever seen anyone move so fast in your life, and before you've even finished your sentence, [brax.name] is thrusting a piece of paper into your hands. "
					+ UtilText.parseSpeech("Don't tell anyone I gave you this! Now, where's this establishment of yours?!", Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
					+ "Wanting to trick him one last time, you give a series of instructions to [brax.name] that will end up with him returning right back here at the Enforcer's HQ."
					+ " Just as you thought, he's so completely blinded by his lust for wolf-girls that he doesn't realise you're sending him in a huge circuit of the city."
					+ " As he leaps up and runs for the door, you wonder if you should tell him what a gullible idiot he is..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Let him go", "Tell [brax.name] to have fun. From your directions, it'll take at least a couple of hours before he figures out he's been fooled.", INTERIOR_BRAX_LIE_BLUFFING_SUCCESS){
					@Override
					public void effects(){
						if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_C_WOLFS_DEN) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
						}
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_MINI_SKIRT, Colour.CLOTHING_BLUE, false), false);
					}
				};
					
			} else if (index == 2) {
				return new Response("Stop [brax.name]", "Tell [brax.name] that he's an idiot and you're going to beat him up for being such a gullible fool.", INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX);
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING_SUCCESS = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You barely have time to say goodbye to your gullible friend before he bounds out of the door, muttering about how he won't need 'that slut downstairs' anymore."
					+ "</p>"
					+ "<p>"
						+ "You finally have a moment to look at the piece of paper [brax.name] gave to you, and as you read, you let out an annoyed tutting sound, realising that Arthur is a hard person to track down."
						+ " On the paper, you read:"
					+ "</p>"
					+ "<h6 style='text-align:center;'>Dominion Enforcer Department</h6>"
					+ "<h5 style='text-align:center;'>RECORD OF SLAVE TRANSFER</h5>"
					+ "<p style='text-align:center;'>"
						+ "The person of <i>Arthur Fairbanks</i>, having being found guilty of <i>treason</i>, has, according to law, been enslaved."
						+ " Following standard procedure, the slave's ownership has been transferred from the Dominion Enforcer Department to a registered slave trader, who has been chosen by random lottery."
						+ "<br/><br/>"
						+ "Officer in charge of transferring slave ownership: <i>[brax.name]</i>"
						+ "<br/><br/>"
						+ "Slave trader taking ownership: <i>Scarlett</i>"
						+ "<br/><br/>"
						+ "Contact address: <i>Scarlett's shop, Slaver Alley</i>"
					+ "</p>"
					+ "<p>"
						+ "After reading it over a second time, you realise that you're going to have to make your way to Slaver Alley and see if you can find some way to buy Arthur's freedom."
						+ " Taking one last look around [brax.namePos] office, you notice that there's a neatly-folded spare uniform lying on top of a cabinet."
						+ " Deciding to punish [brax.name] a little more for falling for such an obvious trick, <b>you take the spare uniform, and add it to your inventory</b>."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave the Enforcer HQ.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_ENFORCER_HQ, true);
						((Brax) Main.game.getNpc(Brax.class)).setBraxsPostQuestStatus();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
					+ "Almost feeling sorry for [brax.name], you decide to tell him that he's a gullible idiot, "
					+ UtilText.parsePlayerSpeech("Do you really believe that there's a place called 'The She-wolf's Den'? You do realise that I made all that up, right? I mean, no one can be <i>that</i> much of a gullible idiot!")
					+ "</p>"
					+ "<p>"
					+ "With his hand on the door-handle, [brax.name] stops, and with an unsure stammer, turns back to you, "
					+ UtilText.parseSpeech("W-What do you mean? I-I knew you were lying all along! I don't even like wolf-girls!", Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
					+ "You can't help but laugh at [brax.namePos] stuttering denial of his all-too-apparent weakness. "
					+ (Main.game.getPlayer().getRace()==Race.WOLF_MORPH && Main.game.getPlayer().isFeminine()
						?UtilText.parsePlayerSpeech("Oh, please! I see the way you keep glancing at my body! You couldn't make it any more obvious if you painted a big sign on your head reading '[brax.name] is a bitch for wolf-girls'!")
						:UtilText.parsePlayerSpeech("Oh, please! You couldn't make it any more obvious if you painted a big sign on your head reading '[brax.name] is a bitch for wolf-girls'!"))
					+ "</p>"
					+ "<p>"
					+ "[brax.namePos] face turns back up into the same snarl that you saw when you first entered his office. "
					+ UtilText.parseSpeech("S-Shut up! You want to see how much I like wolf-girls?! Fine! I know exactly what punishment you deserve!",
							Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
					+ "With lightning speed, [brax.name] snatches the piece of paper out from your hands, and as he leaps back, you see the familiar sight of arcane fire being summoned around his arm."
					+ " From the angry, although extremely flustered, expression on his face, it's not too hard to figure out that he intends to fight you."
					+ " Although it was fun to wind him up, you're not too sure if you want to find out what punishment he has planned for you..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[brax.name] looks extremely embarrassed, and you're sure that you've given yourself a big advantage by tricking him like this!", Main.game.getNpc(Brax.class)){
					@Override
					public void effects(){
						Main.game.getNpc(Brax.class).setLustNoText(50);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
					+ "Realising that you can use your body to get what you want, you slowly walk over to [brax.namePos] desk, suggestively swaying your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips with each step."
					+ " [brax.name] slowly sits back down, his thoughts lost in a pink haze of arousal as he watches your provocative movements."
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerSpeech("You know, it's not often that I get to meet such handsome wolf-boys...")
					+" you sigh, leaning forwards over [brax.namePos] desk as you bat your eyelids at him."
					+ "</p>"
					+ "<p>"
					+ "Despite it being completely obvious what you're trying to do, [brax.namePos] weakness for wolf-girls has left him completely unable to act in a rational manner."
					+ " As he hears you calling him handsome, a stupid grin crosses his face, and you smile back as you realise that you have him right where you want him."
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerSpeech("You know, [brax.name],")
					+" you say, reaching forwards to playfully tug at his collar, "
					+ UtilText.parsePlayerSpeech("I was hoping you could help me. My friend, Arthur, seems to have been arrested, and your name was sort of on the arrest warrant. Could you maybe tell me what happened to him?")
					+"</p>"
					+ "<p>"
					+ "In a hypnotised trance, [brax.name] leans down and produces a piece of paper, before pushing it into your hands. "
					+ UtilText.parseSpeech("Just for you, but don't tell anyone I gave you this!", Main.game.getNpc(Brax.class))
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Read", "Read the piece of paper [brax.name] just handed to you.", INTERIOR_BRAX_GETTING_TEASED_UH_OH){
					@Override
					public void effects(){
						if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_C_WOLFS_DEN) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
						}
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_MINI_SKIRT, Colour.CLOTHING_BLUE, false), false);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED_UH_OH = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
					+ "You look down at the piece of paper [brax.name] gave to you, and as you read, you let out an annoyed groan, realising that Arthur is a hard person to track down."
					+ " On the paper, you read:"
					+ "</p>"
					+ "<p>"
					+ "<h6 style='text-align:center;'>Dominion Enforcer Department</h6>"
					+ "<h5 style='text-align:center;'>RECORD OF SLAVE TRANSFER</h5>"
					+ "<p style='text-align:center;'>The person of <i>Arthur Fairbanks</i>, having being found guilty of <i>treason</i>, has, according to law, been enslaved."
					+ " Following standard procedure, the slave's ownership has been transferred from the Dominion Enforcer Department to a registered slave trader, who has been chosen by random lottery."
					+ "<br/><br/>"
					+ "Officer in charge of transferring slave ownership: <i>[brax.name]</i>"
					+ "<br/><br/>"
					+ "Slave trader taking ownership: <i>Scarlett</i>"
					+ "<br/><br/>"
					+ "Contact address: <i>Scarlett's shop, Slaver Alley</i></p>"
					+ "</p>"
					+ "<p>"
					+ "To [brax.namePos] lust-filled mind, your groan sounds just like a lewd moan, and before you can react, he grabs your head and pulls you into a sudden, rough kiss."
					+ " After a moment of frantically forcing his tongue down your throat, he releases you, and you fall back, panting, as he stand up and grins down at you. "
					+ UtilText.parseSpeech("Don't come in here acting the tease and expect to get away with it!", Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
					+ "Before you can make a move, he steps forwards, grabbing you around the waist before pulling you into him."
					+ " You feel his massive erection pressing against your leg, and you gulp as he growls down at you, "
					+ UtilText.parseSpeech("I haven't fucked a wolf-girl as pretty as you in a good long while! I hope you like it rough!", Main.game.getNpc(Brax.class))
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Escape", "Push [brax.name] off of you and make a quick excuse before running away.", INTERIOR_BRAX_GETTING_TEASED_ESCAPE);
					
			} else if (index == 2) {
				return new ResponseSex("Get fucked", "Let [brax.name] take control and fuck you.",
						true, false, 
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null, Brax.AFTER_SUBMISSIVE_SEX, "<p>"
							+ "[brax.namePos] strong, confident grip on your hips quickly moves down and around to your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass,"
							+ " and as he gives it a forceful squeeze, you suddenly realise that you've ended up leaning into his masculine chest."
							+ " With a little whimper, you look up into his hungry, wolf-like eyes, and as you do, he leans down and forces his tongue into your mouth once again."
						+ "</p>"
						+ "<p>"
							+ "As you passionately kiss the dominant wolf-boy, you feel his hands continue to grope and squeeze your rear end."
							+ " Moaning happily into his mouth, you grind yourself against his muscular figure, pressing your "+Main.game.getPlayer().getBreastSize().getDescriptor()
							+ " breasts firmly against his torso as you close your eyes and enjoy the safe, warm feeling of his embrace."
						+ "</p>"
						+ "<p>"
							+ "[brax.name] is the first to make the next move, and he suddenly pulls his head back to break off the kiss before leaning down to growl into your ear, "
							+ UtilText.parseSpeech("Good bitch, now get down on all fours and present yourself!", Main.game.getNpc(Brax.class))
						+ "</p>"
						+ "<p>"
							+ "You obediently do as you are told, dropping down onto your hands and knees as you present yourself to Brax..."
						+ "</p>");
					
			} else if (index == 3) {
				return new ResponseSex("Take control", "Take control of the situation and turn [brax.name] into your little bitch.", Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
						null, null, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Brax.class)),
						null,
						null), Brax.AFTER_DOMINANT_SEX, "<p>"
							+ "You feel a devious smile spread across your face as [brax.name] mentions it might get rough, and, leaning into him, you growl up into his ear, "
							+ UtilText.parsePlayerSpeech("Mmm, yes, [brax.name]. I <i>do</i> like it rough!")
						+ "</p>"
						+ "<p>"
							+ "[brax.name] lets out a surprised cry as you reach down and firmly grab his groin with one hand."
							+ " Pressing your lips against his to prevent him from making any more noise, you thrust your tongue into his mouth, squeezing down on his satisfyingly large package as he squirms and melts under your touch."
						+ "</p>"
						+ "<p>"
							+ "Breaking off the kiss, but making sure not to let go of his crotch, you growl up to him again, "
							+ UtilText.parsePlayerSpeech("So, the real question is, do <i>you</i> like it rough?")
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("W-Wait I-", Main.game.getNpc(Brax.class))
						+ "</p>"
						+ "<p>"
							+ UtilText.parsePlayerSpeech("Wrong answer!")
							+" you cry, giving [brax.namePos] throbbing cock a hard squeeze as you interrupt his response."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("Aah! Yes! Yes, I like it rough!", Main.game.getNpc(Brax.class))
						+ "</p>"
						+ "<p>"
							+ UtilText.parsePlayerSpeech("Mmm, that's right, ")
							+" you sigh, softening your grip before running your fingers up and down [brax.namePos] shorts, biting your lip as you get a good feel of the impressive length of his throbbing cock, "
							+ UtilText.parsePlayerSpeech("and who's going to be a good little bitch for their alpha she-wolf?")
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("I-I am...", Main.game.getNpc(Brax.class))
							+" [brax.name] groans, admitting defeat."
						+ "</p>"
						+ "<p>"
							+ UtilText.parsePlayerSpeech("Good little beta!")
							+" you squeal, happy now that you've asserted your dominance over the handsome wolf-boy."
						+ "</p>");
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED_ESCAPE = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return "<p>"
						+ UtilText.parsePlayerSpeech("Ah! Look at the time! I really need to be going now,")
						+" you say, disentangling yourself from [brax.namePos] clutches. "
						+ UtilText.parsePlayerSpeech("I'll come back later, and we can have some fun then, alright?")
					+"</p>"
					+ "<p>"
						+ "Letting out a disappointed grunt, [brax.name] reluctantly steps back, seemingly placated for now by your promise of a future rendezvous. "
						+ UtilText.parseSpeech("You'd better be back soon!", Main.game.getNpc(Brax.class))
					+ "</p>"
					+ "<p>"
						+ "Making more vague promises, you step back out of the door, pulling it firmly shut behind you and breathing a sigh of relief before heading back down to the front entrance."
					+ "</p>"
					+ "<p>"
						+ "As you pass an empty office, you look in and notice that there's a neatly-folded spare uniform lying on top of a cabinet."
						+ " Deciding that you need a little compensation after being forcefully kissed by [brax.name], <b>you take the spare uniform, and add it to your inventory on your way out</b>."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave the Enforcer HQ.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_ENFORCER_HQ, true);
						((Brax) Main.game.getNpc(Brax.class)).setBraxsPostQuestStatus();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	
	//----------- Repeatable [brax.name] encounter: -----------
	
	
}
