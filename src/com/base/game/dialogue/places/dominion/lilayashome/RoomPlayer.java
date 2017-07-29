package com.base.game.dialogue.places.dominion.lilayashome;

import com.base.game.character.attributes.Attribute;
import com.base.game.character.effects.StatusEffect;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.world.WorldType;
import com.base.world.places.LilayasHome;

/**
 * @since 0.1.75
 * @version 0.1.8
 * @author Innoxia
 */
public class RoomPlayer {
	
	private static int sleepTimer = 240;

	private static Response getResponseRoom(int index) {
		sleepTimer = (Main.game.isDayTime() ? (int) ((60
				* 21)
				- (Main.game.getMinutesPassed()
						% (24
								* 60)))
				: (int) ((60
						* 31)
						- (Main.game.getMinutesPassed()
								% (24
										* 60))));

		if (index == 1) {
			return new Response("Rest", "Rest for four hours. As well as replenishing your health, willpower and stamina, you will also get the 'Well Rested' status effect.", AUNT_HOME_PLAYERS_ROOM_SLEEP){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));
					Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6 * 60) + 240);
				}
			};

		} else if (index == 2) {
			return new Response("Rest until " + (Main.game.isDayTime() ? "Evening" : "Morning"),
					"Rest for " + (sleepTimer >= 60 ? sleepTimer / 60 + " hours " : " ")
					+ (sleepTimer % 60 != 0 ? sleepTimer % 60 + " minutes" : "")
					+ " until " + (Main.game.isDayTime() ? "evening (21:00)." : "morning (07:00).")
					+ " As well as replenishing your health, willpower and stamina, you will also get the 'Well Rested' status effect", AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));

					Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6
							* 60)
							+ sleepTimer);
				}
			};

		} else if (index == 3) {
			return new Response("Wash", "Use your room's en-suite to take a bath or shower. Rose will come and clean your clothes while you wash yourself.", AUNT_HOME_PLAYERS_ROOM_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setStamina(Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM));
					for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
						c.setDirty(false);
					Main.game.getPlayer().cleanAllClothing();
					Main.game.getPlayer().removeStatusEffect(StatusEffect.CREAMPIE_ANUS);
					Main.game.getPlayer().removeStatusEffect(StatusEffect.CREAMPIE_NIPPLES);
					Main.game.getPlayer().removeStatusEffect(StatusEffect.CREAMPIE_PENIS);
					Main.game.getPlayer().removeStatusEffect(StatusEffect.CREAMPIE_VAGINA);
				}
			};

		}
//		else if (index == 4) {
//			return new Response("Books", "Check out some of the titles in the bookcase.", AUNT_HOME_PLAYERS_ROOM_BOOKS){
//				@Override
//				public void effects() {
//					bookContent = "Most of the books seem to be either too dull or too complicated to be worth reading. There are a few, however, that catch your eye.";
//				}
//			};
//
//		}
//		else if(index == 5) {
//			if(Main.game.isDebugMode())
//				return new Response("Enchanting", "Start enchanting.", EnchantmentDialogue.ENCHANTMENT_MENU);
//			
//			if(Main.game.getPlayer().hasSideQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
//				if(Main.game.getPlayer().getSideQuestProgress(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)!=0) {
//					return new Response("Enchanting", "Start enchanting.", EnchantmentDialogue.ENCHANTMENT_MENU);
//				}
//			}
//			return null;
//			
//		}
		else if (index == 6) {
			return new ResponseEffectsOnly("Entrance hall", "Fast travel down to the entrance hall."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), LilayasHome.LILAYA_HOME_ENTRANCE_HALL, true);
				}
			};

		} else {
			return null;
		}
	}

	public static final DialogueNodeOld ROOM = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "Your room is well-furnished, containing a king-sized bed, two sets of drawers and a full-height wardrobe."
					+ " There's also a well-stocked bookcase sitting to one side of your en-suite's door, and you suppose that you could find out some more information about this strange new world by checking out some of the volumes on display."
					+ " A row of windows provide a good view of the courtyard garden below, which is flanked on all sides by different wings of Lilaya's house."
					+ "</p>"
					+ "<p>"
					+ "Your private en-suite bathroom is accessible from here via a door on one side of the room. The bathroom is considerably larger than any other that you've used before, and is extravagantly decorated in marble and gold."
					+ "</p>"
					+ "<p>"
					+ "Like everything else that normally would have run on electricity in your world, the lighting, shower, and radiators all appear to be powered by the arcane."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_SLEEP = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 240;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You set your phone's alarm before drawing the curtains, lying on your bed and closing your eyes."
					+ " You feel extremely safe and comfortable here in Lilaya's home, and as you think about soon drift off to sleep..."
					+ "</p>"
					+ "<p>"
					+ "<i>Beep-beep... beep-beep... bee-</i>"
					+ "</p>"
					+ "<p>"
					+ "Rolling over, you fumble for your phone, turning off the alarm before sinking back onto the bed."
					+ " You decide that you'd better get up, and as you do so, you let out a satisfied yawn and stretch your "
					+ Main.game.getPlayer().getArmName()
					+ "."
					+ " You open the curtains and gather your things, ready to set out once more."
					+ "</p>"
					+ "<p>"
					+ "<b style='color:"
					+ Colour.GENERIC_GOOD.()
					+ ";'>You feel completely refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return sleepTimer;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You set your phone's alarm before drawing the curtains, lying on your bed and closing your eyes."
					+ " You feel extremely safe and comfortable here in Lilaya's home, and as you think about soon drift off to sleep..."
					+ "</p>"
					+ "<p>"
					+ "<i>Beep-beep... beep-beep... bee-</i>"
					+ "</p>"
					+ "<p>"
					+ "Rolling over, you fumble for your phone, turning off the alarm before sinking back onto the bed."
					+ " You decide that you'd better get up, and as you do so, you let out a satisfied yawn and stretch your "
					+ Main.game.getPlayer().getArmName()
					+ "."
					+ " You open the curtains and gather your things, ready to set out once more."
					+ "</p>"
					+ "<p>"
					+ "<b style='color:"
					+ Colour.GENERIC_GOOD.()
					+ ";'>You feel completely refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_WASH = new DialogueNodeOld("Your room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You step into the en-suite, marvelling at its extravagant design. Leaving your dirty clothes on the other side of the door, you take a long, relaxing shower."
					+ "</p>"
					+ "<p>"
					+ "<b style='color:"
					+ Colour.GENERIC_GOOD.()
					+ ";'>Your clothes have been cleaned, and you feel refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};

}
