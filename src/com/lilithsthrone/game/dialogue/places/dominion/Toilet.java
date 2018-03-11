package com.lilithsthrone.game.dialogue.places.dominion;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.0
 * @version 0.1.87
 * @author Picobyte
 */
public class Toilet {

	public static final DialogueNodeOld HALL = new DialogueNodeOld("Toilet", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>You stand in the hall before the toilets; You could enter a cell to relieve yourself or wash under the faucet.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "You're allowed to pee in here.", CELL);
			} else if (index == 2) {
				return new Response("Wash", "wash your hands", HALL){
					@Override
					public void effects() {
						Main.game.getPlayer().cleanAllDirtySlots();
						Main.game.getTextEndStringBuilder().append("<p>"
								+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>"
								+ "You clean your hands and your face of any bodily fluids.</b>"
								+ " To clean clothing you'll need to go back to your room, though."
							+ "</p>");
					}
				};
			} else if (index == 5) {
				return new ResponseEffectsOnly("Diureticum", "Obtain diureticum."){
					@Override
					public void effects() {
						Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.DIURETICUM), false, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld CELL = new DialogueNodeOld("Toilet ", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You're crammed in a small cubicle designed for one purpose. Although you could masturbate or read a magazine, for other purposes it is less suited. "
					+ "It may be possible for two to enter, but for a threesome the room would definitely not be spacious enough."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Pee", "Use the toilet.", HALL){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>You sit down and relieve yourself.</p>");
						// FIXME: act on factor.
						Main.game.getPlayer().incrementBonusAttribute(Attribute.BLADDER, -Main.game.getPlayer().getBonusAttributeValue(Attribute.BLADDER));
					}
				};
			} else if (index == 0) {
				return new Response("Back", "Decide not to relieve yourself.", HALL);

			} else {
				return null;
			}
		}
	};


}

