package com.lilithsthrone.controller.eventListeners;

import java.util.Map.Entry;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.69.9
 * @author Innoxia
 */
public class InventoryRemoveJinx implements EventListener {
	private int index;

	@Override
	public void handleEvent(Event event) {
		Main.game.getTextStartStringBuilder()
				.append("<p>You concentrate on the magical energy in the " + (InventoryDialogue.isJinxRemovalFromFloor() ? InventoryDialogue.getWeaponFloor().getName() : InventoryDialogue.getWeapon().getName()) + ", channelling it into your "
						+ InventoryDialogue.getJinxedClothing().get(index).getName() + ".</p>" + "<p><b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>With a sudden pink flash, the "
						+ (InventoryDialogue.isJinxRemovalFromFloor() ? InventoryDialogue.getWeaponFloor().getName() : InventoryDialogue.getWeapon().getName()) + " vanishes, removing the jinx in the process.</b></p>");

		if (InventoryDialogue.isJinxRemovalFromFloor()) {
			Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().removeWeapon(InventoryDialogue.getWeaponFloor());
		} else {
			Main.game.getPlayer().removeWeapon(InventoryDialogue.getWeapon());
		}
		
		if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(InventoryDialogue.getJinxedClothing().get(index))) {
			for(Entry<Attribute, Integer> entry : InventoryDialogue.getJinxedClothing().get(index).getAttributeModifiers().entrySet()) {
				Main.game.getPlayer().incrementBonusAttribute(entry.getKey(), -entry.getValue());
			}
		}
		
		InventoryDialogue.getJinxedClothing().get(index).setSealed(false);
		InventoryDialogue.getJinxedClothing().get(index).getAttributeModifiers().clear();
		InventoryDialogue.getJinxedClothing().get(index).removeBadEnchantment();

		InventoryDialogue.populateJinxedClothingList();

		Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU){
			@Override
			public QuestLine getQuestLine() {
				if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_JINXED_CLOTHING)) {
					return QuestLine.SIDE_JINXED_CLOTHING;
				}
				return null;
			}	
		});
	}

	public InventoryRemoveJinx setJinxIndex(int i) {
		index = i;
		return this;
	}
}