package com.lilithsthrone.game.character.npc.generic;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.DominionAlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;

/**
 * @since 0.1.87
 * @version 0.1.89
 * @author Innoxia
 */
public class SlaveImport extends NPC {

	private static final long serialVersionUID = 1L;

	public SlaveImport(Gender gender) {
		super(null, "", 3, gender, RacialBody.HUMAN, RaceStage.HUMAN, new CharacterInventory(10), WorldType.DOMINION, Dominion.CITY_BACK_ALLEYS, true);
		
		this.setPlayerKnowsName(true);
		setDescription(UtilText.parse(this, "[npc.Name] is a slave."));
		
		// INVENTORY:
		
		this.setEnslavementDialogue(DominionAlleywayAttackerDialogue.ENSLAVEMENT_DIALOGUE);
		
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM));
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	public static SlaveImport loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		SlaveImport character = new SlaveImport(Gender.F_V_B_FEMALE);

		GameCharacter.loadGameCharacterVariablesFromXML(character, log, parentElement, doc);
		
		return character;
	}
	
	@Override
	public String getDescription() {
		return (UtilText.parse(this,
				"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
	}
	
	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			if(!isSlave()) {
				setPendingClothingDressing(true);
			}
		}
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		if(Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType()==Dominion.CITY_BACK_ALLEYS) {
			return DominionAlleywayAttackerDialogue.ALLEY_ATTACK;
		} else {
			return DominionAlleywayAttackerDialogue.STORM_ATTACK;
		}
	}

	// Combat:

	@Override
	public Attack attackType() {
		if(!getSpecialAttacks().isEmpty()) {
			if (Math.random() < 0.6) {
				return Attack.MAIN;
			} else if (Math.random() < 0.8) {
				return Attack.SEDUCTION;
			} else {
				return Attack.SPECIAL_ATTACK;
			}
			
		} else {
			if (Math.random() < 0.7) {
				return Attack.MAIN;
			} else {
				return Attack.SEDUCTION;
			}
		}
	}

	@Override
	public String getCombatDescription() {
		if(this.isPregnant()) {
			return "The consequence of your refusal to pull out of [npc.name] is standing right before you."
					+ " Visibly pregnant, your one-time sexual partner has a devious grin on [npc.her] face, and you're not quite sure if you want to know what [npc.she]'s planning for [npc.her] revenge...";
		} else {
			if(this.isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(this, "[npc.Name] is quite clearly turned on by your strong aura. [npc.She]'s willing to fight you in order to claim your body.");
				
			} else {
				return UtilText.parse(this, "Although your strong aura is having an effect on [npc.name], [npc.she]'s only really interested in robbing you of your possessions.");
				
			}
		}
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {

		if (attackType == Attack.MAIN) {
			switch (Util.random.nextInt(3)) {
			case 0:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] feints a punch, and as you dodge away, [npc.she] tries to deliver a kick aimed at your legs."
							+ (isHit ? "" : " You see [npc.her] kick coming and jump backwards out of harm's way.")
						+ "</p>");
			case 1:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] jumps forwards, trying to deliver a punch to your stomach."
							+ (isHit ? "" : " You manage to twist to one side, narrowly avoiding [npc.her] attack.")
						+ "</p>");
			default:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] darts forwards, throwing a punch at your torso."
							+ (isHit ? "" : " You manage to dodge [npc.her] attack by leaping to one side.")
						+ "</p>");
			}
		} else {
			if(isFeminine()) {
				switch (Util.random.nextInt(3)) {
					case 0:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] erotically runs [npc.her] hands down [npc.her] legs and bends forwards as [npc.she] teases you, "
									+ "[npc.speech(Come on baby, I can show you a good time!)]"
								+ "</p>");
					case 1:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] pushes out [npc.her] chest and lets out an erotic moan, "
									+ "[npc.speech(Come play with me!)]"
								+ "</p>");
					default:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] slowly runs [npc.her] hands down between [npc.her] thighs, "
									+ "[npc.speech(You know you want it!)]"
								+ "</p>");
				}
			} else {
				switch (Util.random.nextInt(3)) {
					case 0:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] winks at you and flexes [npc.his] muscles, "
									+ "[npc.speech(My body's aching for your touch!)]"
								+ "</p>");
					case 1:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] strikes a heroic pose before blowing a kiss your way, "
									+ "[npc.speech(Come on, I can show you a good time!)]"
								+ "</p>");
					default:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] grins at you as [npc.he] reaches down and grabs [npc.his] crotch, "
									+ "[npc.speech(You know you want a taste of this!)]"
								+ "</p>");
				}

			}
		}
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", DominionAlleywayAttackerDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", DominionAlleywayAttackerDialogue.AFTER_COMBAT_DEFEAT);
		}
	}

	@Override
	public String getLostVirginityDescriptor() {
		return "in the streets of Dominion";
	}
	
	@Override
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()){
			// Player uses item on themselves:
			if(target.isPlayer()){
				return Main.game.getPlayer().useItem(item, target, false);
				
			// Player uses item on NPC:
			}else{
				if(item.getItemType().equals(ItemType.PROMISCUITY_PILL)) {
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.isPlayerDom()) {
							return "<p>"
									+ "Holding out a 'Promiscuity pill' to [npc.name], you tell [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
									+ " Letting out a reluctant sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] whines at you,"
									+ " [npc.speech(Fine! I kinda like the taste of these things anyway...)]"
									+ "</p>";
						} else {
							return "<p>"
									+ "Holding out a 'Promiscuity pill' to [npc.name], you ask [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
									+ " Letting out an annoyed sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] growls at you,"
									+ " [npc.speech(Fine! I don't care either way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
	
				} else if(item.getItemType().equals(ItemType.VIXENS_VIRILITY)) {
					
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.isPlayerDom()) {
							return "<p>"
									+ "Holding out a 'Vixen's Virility' pill to [npc.name], you tell [npc.her] to swallow it."
									+ " Letting out a reluctant sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] whines at you,"
									+ " [npc.speech(Fine! I kinda like the taste of these things anyway...)]"
									+ "</p>";
						} else {
							return "<p>"
									+ "Holding out a 'Vixen's Virility' pill to [npc.name], you ask [npc.her] to swallow it."
									+ " Letting out an annoyed sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] growls at you,"
									+ " [npc.speech(Fine! I don't care either way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.POTION) || item.getItemType().equals(ItemType.ELIXIR) || item.getItemType().equals(ItemType.FETISH_UNREFINED) || item.getItemType().equals(ItemType.FETISH_REFINED)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking your "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink some rando~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you quickly remove the bottle's stopper, and, rather unceremoniously, shove the neck down [npc.her] throat."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.MOTHERS_MILK)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking the bottle of "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you unceremoniously shove the bottle's teat into [npc.her] mouth."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.RACE_INGREDIENT_CAT_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_DOG_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HARPY)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HORSE_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_SQUIRREL_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_WOLF_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_COW_MORPH)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking the "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to eat tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you unceremoniously shove the "+item.getName()+" into [npc.her] mouth."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to gulp down the entire meal before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the food's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to eat that?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.SEX_INGREDIENT_HARPY_PERFUME)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking the "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to use tha~Hey!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you squirt the "+item.getName()+" onto [npc.her] neck."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the perfume's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to use that?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.COR_INGREDIENT_LILITHS_GIFT)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HUMAN)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_DEMON)
						|| item.getItemType().equals(ItemType.FIT_INGREDIENT_CANINE_CRUSH)
						|| item.getItemType().equals(ItemType.FIT_INGREDIENT_SQUIRREL_JAVA)
						|| item.getItemType().equals(ItemType.INT_INGREDIENT_FELINE_FANCY)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_EQUINE_CIDER)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_WOLF_WHISKEY)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_BUBBLE_MILK)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking the bottle of "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you quickly remove the bottle's cap, and, rather unceremoniously, shove the neck down [npc.her] throat."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
	
				} else if(item.getItemType().equals(ItemType.EGGPLANT)) {
					
					if(Sex.isPlayerDom()) {
						return "<p>"
									+ "Taking the eggplant from your inventory, you hold it out to [npc.name]."
									+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
									+ " [npc.speech(W-What are you going to do with th-~Mrph!~)]"
								+ "</p>"
								+ "<p>"
									+ "Not liking the start of [npc.her] response, you quickly shove the eggplant into [npc.her] mouth, grinning as you force [npc.herHim] to eat the purple fruit..."
								+ "</p>"
								+Main.game.getPlayer().useItem(item, target, false, true);
					} else {
						return "<p>"
									+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
									+ " [npc.speech(Hah! Did you really think I was going to eat that?!)]</br>"
									+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
								+ "</p>";
					}
					
				} else {
					return "<p>"
								+ "You try to give [npc.name] "+item.getItemType().getDeterminer()+" "+item.getName()+", but [npc.she] refuses to take it. You put the "+item.getName()+" back in your inventory."
							+ "</p>";
				}
			}
			
		// NPC is using an item:
		}else{
			return Sex.getPartner().useItem(item, target, false);
		}
	}
	
}
