package com.base.game.character.npc.dominion;

import java.util.Map;
import java.util.Map.Entry;

import com.base.game.character.CharacterUtils;
import com.base.game.character.GameCharacter;
import com.base.game.character.Name;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.Race;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.npcDialogue.DominionAlleywayAttacker;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.Value;
import com.base.utils.Vector2i;
import com.base.world.WorldType;
import com.base.world.places.Dominion;

/**
 * @since 0.1.66
 * @version 0.1.83
 * @author Innoxia
 */
public class NPCRandomDominion extends NPC {

	private static final long serialVersionUID = 1L;

	public NPCRandomDominion(Gender gender) {
		super(null, "", 3, gender, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, Dominion.CITY_BACK_ALLEYS, false);

		setAttribute(Attribute.STRENGTH, (int)(this.getAttributeValue(Attribute.STRENGTH) * (0.5f+Math.random())));
		setAttribute(Attribute.INTELLIGENCE, (int)(this.getAttributeValue(Attribute.INTELLIGENCE) * (0.5f+Math.random())));
		setAttribute(Attribute.FITNESS, (int)(this.getAttributeValue(Attribute.FITNESS) * (0.5f+Math.random())));
		setAttribute(Attribute.CORRUPTION, (int)(20 * (0.5f+Math.random())));

		this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
		this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
		
		// Set random level from 1 to 3:
		setLevel(Util.random.nextInt(3) + 1);
		
		
		// RACE & NAME:
		
		Race race = Race.DOG_MORPH;
		
		double humanChance = 0;
		
		if(Main.getProperties().humanEncountersLevel==1) {
			humanChance = 0.05f;
			
		} else if(Main.getProperties().humanEncountersLevel==2) {
			humanChance = 0.1f;
			
		} else if(Main.getProperties().humanEncountersLevel==3) {
			humanChance = 0.2f;
			
		} else if(Main.getProperties().humanEncountersLevel==4) {
			humanChance = 0.5f;
		}
		
		if(Math.random()>humanChance) {
			Map<Race, Integer> availableRaces = Util.newHashMapOfValues(
					new Value<>(Race.DOG_MORPH, 3),
					new Value<>(Race.CAT_MORPH, 3),
					new Value<>(Race.HORSE_MORPH, 3),
					new Value<>(Race.WOLF_MORPH, 3),
					new Value<>(Race.SQUIRREL_MORPH, 1));
			
			int total = 0;
			for(int i : availableRaces.values()) {
				total+=i;
			}
			
			int choice = Util.random.nextInt(total) + 1;
			
			total = 0;
			for(Entry<Race, Integer> entry : availableRaces.entrySet()) {
				total+=entry.getValue();
				if(choice<=total) {
					race = entry.getKey();
					break;
				}
			}
			
			if(gender.isFeminine()) {
				switch(Main.getProperties().raceFemininePreferencesMap.get(race)) {
					case HUMAN:
						setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
						break;
					case MINIMUM:
						setBodyFromPreferences(2, gender, race);
						break;
					case REDUCED:
						setBodyFromPreferences(3, gender, race);
						break;
					case NORMAL:
						setBodyFromPreferences(4, gender, race);
						break;
					case MAXIMUM:
						setBody(gender, RacialBody.valueOfRace(race), RaceStage.GREATER);
						break;
				}
			} else {
				switch(Main.getProperties().raceMasculinePreferencesMap.get(race)) {
					case HUMAN:
						setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
						break;
					case MINIMUM:
						setBodyFromPreferences(2, gender, race);
						break;
					case REDUCED:
						setBodyFromPreferences(3, gender, race);
						break;
					case NORMAL:
						setBodyFromPreferences(4, gender, race);
						break;
					case MAXIMUM:
						setBody(gender, RacialBody.valueOfRace(race), RaceStage.GREATER);
						break;
				}
			}
			
		} else {
			setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
		}
		
		setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(gender));

		setName(Name.getRandomTriplet(race));
		this.setPlayerKnowsName(false);
		setDescription(UtilText.parse(this,
				"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
		
		// ADDING FETISHES:
		
		CharacterUtils.addFetishes(this);
		
		// BODY RANDOMISATION:
		
		CharacterUtils.randomiseBody(this);
		
		
		// INVENTORY:
		
		resetInventory();
		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

		CharacterUtils.equipClothing(this, true, false);
		
		
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM));
	}
	
	private void setBodyFromPreferences(int i, Gender gender, Race race) {
		int choice = Util.random.nextInt(i)+1;
		RaceStage raceStage = RaceStage.PARTIAL;
		
		if (choice == 1) {
			raceStage = RaceStage.PARTIAL;
		} else if (choice == 2) {
			raceStage = RaceStage.PARTIAL_FULL;
		} else if (choice == 3) {
			raceStage = RaceStage.LESSER;
		} else {
			raceStage = RaceStage.GREATER;
		}
		
		setBody(gender, RacialBody.valueOfRace(race), raceStage);
	}
	
	@Override
	public String getDescription() {
		return (UtilText.parse(this,
				"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
	}
	
	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			setPendingClothingDressing(true);
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
	public void applyReset() {
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM));
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		if(Main.game.getActiveWorld().getCell(location).getPlace()==Dominion.CITY_BACK_ALLEYS) {
			return DominionAlleywayAttacker.ALLEY_ATTACK;
		} else {
			return DominionAlleywayAttacker.STORM_ATTACK;
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
			if(this.isWantsToHaveSexWithPlayer()) {
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
			return new Response("", "", DominionAlleywayAttacker.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", DominionAlleywayAttacker.AFTER_COMBAT_DEFEAT);
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
				switch(item.getItemType()){
					case CONDOM:
						if(target.isWearingCondom()) {
							return "<p>"
									+ "[npc.Name] is already wearing a condom, and [npc.she] refuses to wear two at once."
									+ "</p>";
							
						} else if(target.hasPenis()) {
							Main.game.getPlayer().useItem(item, target, false);
							if(Sex.isPlayerDom()) {
								return "<p>"
										+ "Holding out a condom to [npc.name], you force [npc.her] to take it and put it on."
										+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] whines at you,"
										+ " [npc.speech(Do I really have to? It feels so much better without one...)]"
										+ "</p>";
							} else {
								return "<p>"
										+ "Holding out a condom to [npc.name], you let out a sigh of relief as [npc.she] reluctantly takes it."
										+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] growls at you,"
										+ " [npc.speech(You'd better be glad that I'm in a good mood!)]"
										+ "</p>";
							}
						} else {
							return "<p>"
									+ "[npc.Name] doesn't have a penis, so [npc.she] can't use the condom!"
									+ "</p>";
						}
					case PROMISCUITY_PILL:
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
									+ " [npc.speech(Fine! I don't care eit[npc.her] way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
					case VIXENS_VIRILITY:
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
									+ " [npc.speech(Fine! I don't care eit[npc.her] way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
					case POTION: case ELIXIR:
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
					case MOTHERS_MILK:
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
					case RACE_INGREDIENT_CAT_MORPH: case RACE_INGREDIENT_DOG_MORPH: case RACE_INGREDIENT_HARPY: case RACE_INGREDIENT_HORSE_MORPH: case RACE_INGREDIENT_SQUIRREL_MORPH: case RACE_INGREDIENT_WOLF_MORPH:
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
					case SEX_INGREDIENT_HARPY_PERFUME:
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
					case COR_INGREDIENT_LILITHS_GIFT: case RACE_INGREDIENT_HUMAN: case RACE_INGREDIENT_DEMON: case FIT_INGREDIENT_CANINE_CRUSH: case FIT_INGREDIENT_SQUIRREL_JAVA: case INT_INGREDIENT_FELINE_FANCY: case STR_INGREDIENT_EQUINE_CIDER: case STR_INGREDIENT_WOLF_WHISKEY:
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
					case EGGPLANT:
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
					default:
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
