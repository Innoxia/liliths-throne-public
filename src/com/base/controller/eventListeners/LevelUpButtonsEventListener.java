package com.base.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.base.game.character.attributes.Attribute;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.PhoneDialogue;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.main.Main;

/**
 * @since 0.1.4
 * @version 0.1.78
 * @author Innoxia
 */
public class LevelUpButtonsEventListener implements EventListener {
	private boolean strIncrease, strDecrease, intIncrease, intDecrease, fitIncrease, fitDecrease;
	private Perk p = null;
	private Fetish f = null;

	@Override
	public void handleEvent(Event event) {
		if (strIncrease) {
			if (Main.game.getPlayer().getLevelUpPoints() - (PhoneDialogue.strengthPoints + PhoneDialogue.intelligencePoints + PhoneDialogue.fitnessPoints) > 0
					&& (Main.game.getPlayer().getBaseAttributeValue(Attribute.STRENGTH) + PhoneDialogue.strengthPoints < 100)) {
				PhoneDialogue.strengthPoints++;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
			
		} else if (strDecrease) {
			if (PhoneDialogue.strengthPoints > 0) {
				PhoneDialogue.strengthPoints--;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
			
		} else if (intIncrease) {
			if (Main.game.getPlayer().getLevelUpPoints() - (PhoneDialogue.strengthPoints + PhoneDialogue.intelligencePoints + PhoneDialogue.fitnessPoints) > 0
					&& (Main.game.getPlayer().getBaseAttributeValue(Attribute.INTELLIGENCE) + PhoneDialogue.intelligencePoints < 100)) {
				PhoneDialogue.intelligencePoints++;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
			
		} else if (intDecrease) {
			if (PhoneDialogue.intelligencePoints > 0) {
				PhoneDialogue.intelligencePoints--;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
			
		} else if (fitIncrease) {
			if (Main.game.getPlayer().getLevelUpPoints() - (PhoneDialogue.strengthPoints + PhoneDialogue.intelligencePoints + PhoneDialogue.fitnessPoints) > 0
					&& (Main.game.getPlayer().getBaseAttributeValue(Attribute.FITNESS) + PhoneDialogue.fitnessPoints < 100)) {
				PhoneDialogue.fitnessPoints++;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
			
		} else if (fitDecrease) {
			if (PhoneDialogue.fitnessPoints > 0) {
				PhoneDialogue.fitnessPoints--;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
			
		} else if (p != null) {
			if (PhoneDialogue.levelUpPerks.contains(p)) { // Already selected this perk, so remove it and all of its next level perks from the selection:
				int refundPoints = 0;

				PhoneDialogue.levelUpPerks.remove(p);
				refundPoints++;

				while (p.getNextLevelPerk() != null && PhoneDialogue.levelUpPerks.contains(p.getNextLevelPerk())) {
					p = p.getNextLevelPerk();
					PhoneDialogue.levelUpPerks.remove(p);
					refundPoints++;
				}
				
				PhoneDialogue.spendingPoints += refundPoints;

				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));

			} else if (PhoneDialogue.spendingPoints > 0
					&& !Main.game.getPlayer().hasPerk(p)
					&& p.isAvailable(Main.game.getPlayer(), PhoneDialogue.levelUpPerks)) { // Have points to spend, player doesn't own this perk:

				// Find if player has a more advanced perk in the perk tree. If they do, don't allow this (less advanced) perk to be selected:
				Perk nextPerk = p.getNextLevelPerk();
				while (nextPerk != null) {
					if (Main.game.getPlayer().hasPerk(nextPerk))
						return;
					nextPerk = nextPerk.getNextLevelPerk();
				}

				PhoneDialogue.levelUpPerks.add(p);

				PhoneDialogue.spendingPoints-=1;

				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
			
		} else if (f != null) {
			if (PhoneDialogue.levelUpFetishes.contains(f)) { // Already selected this fetish, so remove it:

				
				if(Main.game.getPlayer().getFetishes().size()+PhoneDialogue.levelUpFetishes.size()>1)
					PhoneDialogue.spendingFetishPoints -= PhoneDialogue.getFetishCost();

				PhoneDialogue.levelUpFetishes.remove(f);
				
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));

			} else if (
					((PhoneDialogue.spendingFetishPoints+PhoneDialogue.getFetishCost() <= Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)) || (Main.game.getPlayer().getFetishes().size()+PhoneDialogue.levelUpFetishes.size()==0))
					&& !Main.game.getPlayer().hasFetish(f)
					&& f.isAvailable(Main.game.getPlayer())
					&& f.getFetishesForAutomaticUnlock().isEmpty()) { // Have points to spend, player doesn't own this fetish:

				PhoneDialogue.levelUpFetishes.add(f);
				
				if(Main.game.getPlayer().getFetishes().size()+PhoneDialogue.levelUpFetishes.size()>1)
					PhoneDialogue.spendingFetishPoints += PhoneDialogue.getFetishCost();
				

				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
		}
	}

	public LevelUpButtonsEventListener increaseStrength() {
		resetVariables();

		strIncrease = true;
		return this;
	}

	public LevelUpButtonsEventListener decreaseStrength() {
		resetVariables();

		strDecrease = true;
		return this;
	}

	public LevelUpButtonsEventListener increaseIntelligence() {
		resetVariables();

		intIncrease = true;
		return this;
	}

	public LevelUpButtonsEventListener decreaseIntelligence() {
		resetVariables();

		intDecrease = true;
		return this;
	}

	public LevelUpButtonsEventListener increaseFitness() {
		resetVariables();

		fitIncrease = true;
		return this;
	}

	public LevelUpButtonsEventListener decreaseFitness() {
		resetVariables();

		fitDecrease = true;
		return this;
	}

	public LevelUpButtonsEventListener handlePerkPress(Perk p) {
		resetVariables();
		
		this.p=p;
		return this;
	}
	
	public LevelUpButtonsEventListener handleFetishPress(Fetish f) {
		resetVariables();
		
		this.f=f;
		return this;
	}
	
	
	private void resetVariables() {
		p=null;
		f=null;
		strIncrease = false;
		strDecrease = false;
		intIncrease = false;
		intDecrease = false;
		fitIncrease = false;
		fitDecrease = false;
	}
}
