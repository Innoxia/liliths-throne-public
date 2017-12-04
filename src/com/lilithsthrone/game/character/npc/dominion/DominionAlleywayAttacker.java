package com.lilithsthrone.game.character.npc.dominion;

import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.History;
import com.lilithsthrone.game.character.Name;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.alleyway.AlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.alleyway.AlleywayProstituteDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.1.89
 * @author Innoxia
 */
public class DominionAlleywayAttacker extends NPC {

	private static final long serialVersionUID = 1L;

	public DominionAlleywayAttacker() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public DominionAlleywayAttacker(Gender gender) {
		this(gender, false);
	}
	
	public DominionAlleywayAttacker(Gender gender, boolean isImported) {
		super(null, "", 3, gender, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
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
				humanChance = 0.25f;
				
			} else if(Main.getProperties().humanEncountersLevel==3) {
				humanChance = 0.5f;
				
			} else if(Main.getProperties().humanEncountersLevel==4) {
				humanChance = 0.75f;
			}
			
			Map<Race, Integer> availableRaces = Util.newHashMapOfValues(
					new Value<>(Race.DOG_MORPH, 20),
					new Value<>(Race.CAT_MORPH, 20),
					new Value<>(Race.HORSE_MORPH, 20),
					new Value<>(Race.WOLF_MORPH, 20),
					new Value<>(Race.SQUIRREL_MORPH, 10),
					new Value<>(Race.COW_MORPH, 10));
			
			if(gender.isFeminine()) {
				for(Entry<Race, FurryPreference> entry : Main.getProperties().raceFemininePreferencesMap.entrySet()) {
					if(entry.getValue() == FurryPreference.HUMAN) {
						availableRaces.remove(entry.getKey());
					}
				}
			} else {
				for(Entry<Race, FurryPreference> entry : Main.getProperties().raceMasculinePreferencesMap.entrySet()) {
					if(entry.getValue() == FurryPreference.HUMAN) {
						availableRaces.remove(entry.getKey());
					}
				}
			}
			
			if(availableRaces.isEmpty() || Math.random()<humanChance) {
				setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
				
			} else {
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
							setBodyFromPreferences(1, gender, race);
							break;
						case REDUCED:
							setBodyFromPreferences(2, gender, race);
							break;
						case NORMAL:
							setBodyFromPreferences(3, gender, race);
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
							setBodyFromPreferences(1, gender, race);
							break;
						case REDUCED:
							setBodyFromPreferences(2, gender, race);
							break;
						case NORMAL:
							setBodyFromPreferences(3, gender, race);
							break;
						case MAXIMUM:
							setBody(gender, RacialBody.valueOfRace(race), RaceStage.GREATER);
							break;
					}
				}
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
			
			// PERSONALITY & BACKGROUND:
			
			double prostituteChance = 0.25f; //Base 0.25% chance for any random to be a prostitute.
						
			if(this.isFeminine()) { prostituteChance += 0.10f; }
			
			prostituteChance += Math.min((this.body.getBreast().getRawSizeValue()-7)*0.02f, 0.35f);
			
			if(this.hasPenis()) { prostituteChance += Math.min((this.body.getPenis().getRawSizeValue()-5)*0.01f, 0.10f); }
			
			if(this.hasVagina()) { prostituteChance += 0.15f; }
			
			if(this.body.getBreast().getNipples().getOrificeNipples().getRawCapacityValue() >= 4) { prostituteChance += 0.05f; }
			
			if(this.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {prostituteChance = 0;}
				
			if(Math.random()<=prostituteChance) {
				this.setHistory(History.PROSTITUTE);
				this.setAssVirgin(false);
				this.setAssCapacity(this.getAssRawCapacityValue()*1.2f, true);
				this.setAssStretchedCapacity(this.getAssRawCapacityValue());
				if(this.hasVagina()) {
					this.setVaginaVirgin(false);
					this.setVaginaCapacity(this.getVaginaRawCapacityValue()*1.2f, true);
					this.setVaginaStretchedCapacity(this.getVaginaRawCapacityValue());
				}
				setSexualOrientation(SexualOrientation.AMBIPHILIC);
				setName(Name.getRandomProstituteTriplet());
				useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), this, false);
			} else {
				this.setHistory(History.MUGGER);
			}
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
			setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM));
		}
		
		if(this.getHistory()==History.PROSTITUTE) {
			this.setEnslavementDialogue(AlleywayProstituteDialogue.ENSLAVEMENT_DIALOGUE);
		} else {
			this.setEnslavementDialogue(AlleywayAttackerDialogue.ENSLAVEMENT_DIALOGUE);
		}
	}
	
	@Override
	public DominionAlleywayAttacker loadFromXML(Element parentElement, Document doc) {
		DominionAlleywayAttacker npc = new DominionAlleywayAttacker(Gender.F_V_B_FEMALE, true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);
		
		return npc;
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	private void setBodyFromPreferences(int i, Gender gender, Race race) {
		int choice = Util.random.nextInt(i)+1;
		RaceStage raceStage = RaceStage.PARTIAL;
		
		if (choice == 1) {
			raceStage = RaceStage.PARTIAL;
		} else if (choice == 2) {
			raceStage = RaceStage.LESSER;
		} else {
			raceStage = RaceStage.GREATER;
		}
		
		setBody(gender, RacialBody.valueOfRace(race), raceStage);
	}
	
	@Override
	public String getDescription() {
		if(this.getHistory()==History.PROSTITUTE) {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.Name]'s days of whoring [npc.herself] out in the back alleys of Dominion are now over. Having run afoul of the law, [npc.she]'s now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the backalleys of Dominion."));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.Name]'s days of prowling the back alleys of Dominion and mugging innocent travellers are now over. Having run afoul of the law, [npc.she]'s now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Dominion, who prowls the back alleys in search of innocent travellers to mug and rape."));
			}
		}
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
		if(Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType()==PlaceType.DOMINION_BACK_ALLEYS) {
			if(this.getHistory()==History.PROSTITUTE) {
				this.setPlayerKnowsName(true);
				return AlleywayProstituteDialogue.ALLEY_PROSTITUTE;
			} else {
				return AlleywayAttackerDialogue.ALLEY_ATTACK;
			}
		} else {
			return AlleywayAttackerDialogue.STORM_ATTACK;
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
		if(this.getHistory()==History.PROSTITUTE) {
			if (victory) {
				return new Response("", "", AlleywayProstituteDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", AlleywayProstituteDialogue.AFTER_COMBAT_DEFEAT);
			}
		} else {
			if (victory) {
				return new Response("", "", AlleywayAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", AlleywayAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
		}
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
