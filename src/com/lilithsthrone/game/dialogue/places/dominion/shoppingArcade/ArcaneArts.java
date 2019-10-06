package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.dominion.SMVickyOverDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.3.2
 * @author Innoxia
 */
public class ArcaneArts {
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Arcane Arts (Exterior)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "EXTERIOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step inside Arcane Arts.", SHOP_WEAPONS);
				
			} else if (index == 6) {
				return new Response("Arcade Entrance", "Fast travel to the entrance to the arcade.", ShoppingArcadeDialogue.ENTRY){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SHOP_WEAPONS = new DialogueNode("Arcane Arts", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Weapons", "Walk over to the counter and see what weapons Vicky has in stock.", Main.game.getNpc(Vicky.class)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
						
						Main.game.getNpc(Vicky.class).clearNonEquippedInventory(false);
						
						for (Entry<AbstractWeapon, Integer> weapon : ((Vicky) Main.game.getNpc(Vicky.class)).getWeaponsForSale().entrySet()) {
							if(Main.game.getNpc(Vicky.class).isInventoryFull()) {
								break;
							}
							Main.game.getNpc(Vicky.class).addWeapon(weapon.getKey(), weapon.getValue(), false, false);
						}
					}
				};
				
			} else if (index == 2) {
				return new ResponseTrade("Potions & Spells", "Walk over to the counter and see what potions, essences, and spells Vicky has in stock.", Main.game.getNpc(Vicky.class)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);

						Main.game.getNpc(Vicky.class).clearNonEquippedInventory(false);
						
						for (Entry<AbstractItem, Integer> item : ((Vicky) Main.game.getNpc(Vicky.class)).getItemsForSale().entrySet()) {
							if(Main.game.getNpc(Vicky.class).isInventoryFull()) {
								break;
							}
							Main.game.getNpc(Vicky.class).addItem(item.getKey(), item.getValue(), false, false);
						}
					}
				};
				
			} else if (index == 3) {
				if(((Vicky) Main.game.getNpc(Vicky.class)).getClothingForSale().isEmpty()) {
					return new Response("Clothing", "Vicky doesn't have any clothing in stock at the moment.", null);
				}
				return new ResponseTrade("Clothing", "Walk over to the counter and see what clothing Vicky has in stock.", Main.game.getNpc(Vicky.class)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);

						Main.game.getNpc(Vicky.class).clearNonEquippedInventory(false);
						
						for (Entry<AbstractClothing, Integer> clothing : ((Vicky) Main.game.getNpc(Vicky.class)).getClothingForSale().entrySet()) {
							if(Main.game.getNpc(Vicky.class).isInventoryFull()) {
								break;
							}
							Main.game.getNpc(Vicky.class).addClothing(clothing.getKey(), clothing.getValue(), false, false);
						}
					}
				};
				
			} else if(index==5) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH)==Quest.SIDE_HYPNO_WATCH_VICKY) {
						if(Main.game.getPlayer().isInventoryFull()) {
							return new Response("Arthur's package", "You don't have enough room in your inventory for the package!", null);
							
						} else {
							return new Response("Arthur's package", "Tell Vicky that you're here to collect Arthur's package.", ARTHURS_PACKAGE) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina())) {
						return new ResponseSex("Offer body", "Let Vicky use your body.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVickyOverDesk(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vicky.class), SexSlotDesk.BETWEEN_LEGS)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))),
								null,
								null,
								VICKY_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_BODY"));
					} else {
						return new Response("Offer body", "Vicky needs to be able to access your anus"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
					}
				}
				
			} else if (index == 6 && Main.getProperties().hasValue(PropertyValue.nonConContent) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina())) {
					
					return new ResponseSex("Nervously leave", "Vicky is far too intimidating for you... Turn around and try to escape from her gaze. [style.boldBad(You get the feeling that this will result in non-consensual sex...)]",
							Util.newArrayListOfValues(
									Fetish.FETISH_SUBMISSIVE,
									Fetish.FETISH_NON_CON_SUB), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							false, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vicky.class), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							null,
							VICKY_POST_SEX_RAPE,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_RAPE"));
					
				} else {
					return new Response("Nervously leave", "Vicky needs to be able to access your anus"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Leave Arcane Arts and head back out into the arcade.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ARTHURS_PACKAGE = new DialogueNode("Arcane Arts", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("Pay ("+UtilText.formatAsMoney(100, "span")+")", "Pay Vicky 100 flames.", ARTHURS_PACKAGE_BOUGHT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursPackageObtained, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.ARTHURS_PACKAGE), false, true));
							Main.game.getPlayer().incrementMoney(-100);
						}
					};
				} else {
					return new Response("Pay ("+UtilText.formatAsMoneyUncoloured(100, "span")+")", "You don't have enough money to pay the fee!", null);	
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina())) {
					return new ResponseSex("Offer body", "Let Vicky use your body as payment for the fee.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
							true, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vicky.class), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))),
							null,
							null,
							VICKY_POST_SEX_PACKAGE,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_SEX"));
				} else {
					return new Response("Offer body", "Vicky needs to be able to access your anus"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
				}
				
			} else if (index == 3 && Main.getProperties().hasValue(PropertyValue.nonConContent)) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && Main.game.getPlayer().hasVagina())) {
					
					return new ResponseSex(
							"Weakly refuse",
							"You can't bring yourself to say no to such an intimidating person... Try to wriggle free and leave... [style.boldBad(You get the feeling that this will result in non-consensual sex...)]",
							Util.newArrayListOfValues(
									Fetish.FETISH_SUBMISSIVE,
									Fetish.FETISH_NON_CON_SUB), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							false, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vicky.class), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							null,
							VICKY_POST_SEX_RAPE_PACKAGE,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_RAPE"));
					
				} else {
					return new Response("Weakly refuse", "Vicky needs to be able to access your anus"+(Main.game.getPlayer().hasVagina()?" or vagina":"")+"!", null);
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Leave Arcane Arts and head back out into the arcade.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_LEAVE"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ARTHURS_PACKAGE_BOUGHT = new DialogueNode("Arcane Arts", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_BOUGHT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode VICKY_POST_SEX_PACKAGE = new DialogueNode("Arcane Arts", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_PACKAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VICKY_POST_SEX_RAPE_PACKAGE = new DialogueNode("Arcane Arts", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_RAPE_PACKAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode VICKY_POST_SEX = new DialogueNode("Arcane Arts", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VICKY_POST_SEX_RAPE = new DialogueNode("Arcane Arts", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_RAPE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
}
