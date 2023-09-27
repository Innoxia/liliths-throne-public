package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.KaysWarehouse;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class BountyHunterLodge {

	private static final float BARMAN_BUY_MODIFIER = 0.75f;
	
	private static final Map<String, String> barConsumablesMap = Util.newHashMapOfValues(
			new Value<>("innoxia_race_squirrel_round_nuts", "BAR_NUTS"),
			new Value<>("innoxia_race_dog_canine_crush", "BAR_BEER"),
			new Value<>("innoxia_race_horse_equine_cider", "BAR_CIDER"),
			new Value<>("dsg_race_bear_vodka", "BAR_VODKA"),
			new Value<>("innoxia_race_wolf_wolf_whiskey", "BAR_WHISKEY"),
			new Value<>("innoxia_race_rat_black_rats_rum", "BAR_RUM"));

	private static Response getDobermannsUpstairsSexResponse(int startIndex, int index, String title) {
		if(index == startIndex) {
			title = title.replaceAll("sex_type_replacement", "oral");
			
			if(!KaysWarehouse.isPlayerMouthFree()) {
				return new Response(title,
						"As you cannot gain access to your mouth, you cannot offer to perform oral on the dobermann-boys...",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.SITTING,
						SexSlotSitting.SITTING, SexAreaOrifice.MOUTH,
						SexSlotSitting.SITTING, SexAreaPenetration.FINGER,
						SexSlotSitting.PERFORMING_ORAL,
						title,
						"Offer to suck the dobermann-boys' cocks...",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_ORAL"));
			}
			
		} else if(index == startIndex+1) {
			title = title.replaceAll("sex_type_replacement", "spit-roast");
			
			if(!KaysWarehouse.isPlayerMouthFree()) {
				return new Response(title,
						"As you cannot gain access to your mouth, you cannot offer to be spit-roasted by the dobermann-boys...",
						null);
				
			} else if(!KaysWarehouse.isPlayerAssFree() && !KaysWarehouse.isPlayerVaginaFree()) {
				return new Response(title,
						"As you cannot gain access to your ass"+(Main.game.getPlayer().hasVagina()?" or pussy":"")+", you cannot offer to be spit-roasted by the dobermann-boys...",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.ALL_FOURS,
						SexSlotAllFours.BEHIND, KaysWarehouse.isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotAllFours.IN_FRONT, SexAreaOrifice.MOUTH,
						SexSlotAllFours.ALL_FOURS,
						title,
						"Offer to let the two dobermann-boys spit-roast you...",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_SPITROAST"));
			}
			
		} else if(index == startIndex+2) {
			title = title.replaceAll("sex_type_replacement", "ride");
			
			if(!KaysWarehouse.isPlayerAssFree() && !KaysWarehouse.isPlayerVaginaFree()) {
				return new Response(title,
						"As you cannot gain access to your ass"+(Main.game.getPlayer().hasVagina()?" or pussy":"")+", you cannot offer to ride the dobermann-boys...",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.LYING_DOWN,
						SexSlotLyingDown.LYING_DOWN, KaysWarehouse.isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotLyingDown.MISSIONARY, KaysWarehouse.isPlayerAssFree()?SexAreaOrifice.ANUS:SexAreaOrifice.VAGINA,
						SexSlotLyingDown.COWGIRL,
						title,
						"Offer to give the two dobermann-boys a ride...",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_RIDE"));
			}
			
		} else if(index == startIndex+3 && Main.game.isNipplePenEnabled()) {
			title = title.replaceAll("sex_type_replacement", "nipple-fuck");
			
			if(!KaysWarehouse.isPlayerNippleFuckFree()) {
				return new Response(title,
						Main.game.getPlayer().isBreastFuckableNipplePenetration()
							?"As you cannot gain access to your fuckable nipples, you cannot offer them to the dobermann-boys..."
							:"As you do not have fuckable nipples, you cannot offer them to the dobermann-boys...",
						null);
				
			} else {
				return KaysWarehouse.getDobermannsSexResponse(SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT, SexAreaOrifice.NIPPLE,
						SexSlotStanding.STANDING_DOMINANT_TWO, SexAreaOrifice.NIPPLE,
						SexSlotStanding.PERFORMING_ORAL,
						title,
						"Offer to let the two dobermann-boys fuck your nipples...",
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEX_OFFER_NIPPLES"));
			}
		}
		return null;
	}
	
	public static final DialogueNode ENTRANCE_INITITAL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE_INITITAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Exit", "Step back out into Slaver Alley.", PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "ENTRANCE_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FLOOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "FLOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1 && Main.game.getCharactersPresent().contains(Main.game.getNpc(SupplierLeader.class))) {
				return new Response("Dobermanns", "Head over and say something to Wolfgang and Karl...", DOBERMANNS);
			}
			return null;
		}
	};

	public static final DialogueNode DOBERMANNS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!KaysWarehouse.isSexAvailable()) {
					return new Response("Seduce", "As you're unable to access any of your orifices, you're unable to seduce the dobermann-boys...", null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dobermannDefeatSeduced) || Main.game.getNpc(SupplierLeader.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
					return new Response("Seduce",
							"Offer to let the dobermann-boys fuck you again..."
								+ "<br/>"
								+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_DOMINANT)
										?"[style.italicsSexDom(As you)]"
												+ " [style.italicsMinorGood(have the "+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+" fetish)]"
												+ "[style.italicsSexDom(, you will be able to take either the dominant or submissive role in the following sex scene.)]"
										:"[style.italicsSex(As you)]"
												+ " [style.italicsMinorBad(do not have the "+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+" fetish)]"
												+ "[style.italicsSex(, you will only be able to take the submissive role in the following sex scene.)]"),
							DOBERMANNS_UPSTAIRS) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEDUCE_REPEAT"));
						}
					};
					
				} else {
					return new Response("Seduce",
							"Seduce the dobermann-boys and have sex with them..."
									+ "<br/>"
									+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_DOMINANT)
											?"[style.italicsSexDom(As you)]"
													+ " [style.italicsMinorGood(have the "+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+" fetish)]"
													+ "[style.italicsSexDom(, you will be able to take either the dominant or submissive role in the following sex scene.)]"
											:"[style.italicsSex(As you)]"
													+ " [style.italicsMinorBad(do not have the "+Fetish.FETISH_DOMINANT.getName(Main.game.getPlayer())+" fetish)]"
													+ "[style.italicsSex(, you will only be able to take the submissive role in the following sex scene.)]"),
							DOBERMANNS_UPSTAIRS,
							null,
							null,
							Util.newArrayListOfValues(Perk.MALE_ATTRACTION, Perk.OBJECT_OF_DESIRE),
							null,
							null) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_SEDUCE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatSeduced, true);
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Leave", "Step away from the table and head back into the tavern.", FLOOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DOBERMANNS_UPSTAIRS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, PlaceType.BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_DOBERMANNS);
			Main.game.getNpc(SupplierLeader.class).setLocation(Main.game.getPlayer(), true);
			Main.game.getNpc(SupplierPartner.class).setLocation(Main.game.getPlayer(), true);
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
				return new ResponseSex("Fuck Them",
						"Push Wolfgang and Karl down side-by-side in the doggy-style position, ready to have some fun with them...",
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
						null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								null,
								null,
								ResponseTag.PREFER_DOGGY),
						DOBERMANNS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_UPSTAIRS_FUCK_THEM"));
			}
			return getDobermannsUpstairsSexResponse(2, index, "Submit (sex_type_replacement)");
		}
	};

	public static final DialogueNode DOBERMANNS_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Downstairs", "Head back downstairs to the tavern's ground floor...", STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "DOBERMANNS_AFTER_SEX_DOWNSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_STAIRS);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SEATING = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "SEATING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1 && Main.game.getCharactersPresent().contains(Main.game.getNpc(Silence.class))) {
				return new Response("Silence", "Head over and say something to Silence...<br/>[style.italicsBad(A mini-quest involving Silence and Shadow will be added soon!)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode STAIRS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "STAIRS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Upstairs", "Head up the stairs to the tavern's first floor.", UPSTAIRS_STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "STAIRS_UPSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, PlaceType.BOUNTY_HUNTER_LODGE_UPSTAIRS_STAIRS);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode BOUNTY_BOARD = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "BOUNTY_BOARD");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Bounties", "Take a closer look at which bounties are available.<br/>[style.italicsMinorBad(Will be added in a later update!)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode BAR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "BAR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Register", "Tell the barman that you'd like to register as an official bounty hunter.<br/>[style.italicsMinorBad(Will be added in a later update!)]", null);
			}
			
			List<Response> responses = new ArrayList<>();
			
			for(Entry<String, String> entry : barConsumablesMap.entrySet()) {
				AbstractItem consumable = Main.game.getItemGen().generateItem(entry.getKey());
				int price = consumable.getPrice(BARMAN_BUY_MODIFIER);
				
				if(Main.game.getPlayer().getMoney()<price) {
					responses.add(new Response(consumable.getName()+" ("+UtilText.formatAsMoneyUncoloured(price, "span")+")",
							"You do not have the "+Util.intToString(price)+" flames required, and so cannot order "+consumable.getName(true, false)+"...",
							null));
					
				} else {
					responses.add(new Response(consumable.getName()+" ("+UtilText.formatAsMoney(price, "span")+")",
							"Hand over "+Util.intToString(price)+" flames and order "+consumable.getName(true, false)+".",
							BAR_CONSUME) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(Util.intToString(price), true);
							UtilText.addSpecialParsingString(consumable.getName(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", entry.getValue()));
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().useItem(consumable, Main.game.getPlayer(), false, true));
						}
					});
				}
			}
			
			if(index>0 && index-2<responses.size()) {
				return responses.get(index-2);
			}
			
			return null;
		}
	};

	public static final DialogueNode BAR_CONSUME = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BAR.getResponse(responseTab, index);
		}
	};
	
	// First floor:
	
	public static final DialogueNode UPSTAIRS_CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode UPSTAIRS_STAIRS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_STAIRS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Downstairs", "Head back down the stairs to the tavern's ground floor.", STAIRS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_STAIRS_DOWNSTAIRS"));
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_STAIRS);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode UPSTAIRS_ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "UPSTAIRS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
}
