package com.lilithsthrone.game.character.npc.submission;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.History;
import com.lilithsthrone.game.character.Name;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelAttackDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.1
 * @version 0.2.1
 * @author Innoxia
 */
public class SubmissionAttacker extends NPC {

	private static final long serialVersionUID = 1L;

	public SubmissionAttacker() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public SubmissionAttacker(Gender gender) {
		this(gender, false);
	}
	
	public SubmissionAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public SubmissionAttacker(Gender gender, boolean isImported) {
		super(null, "", 3, gender, RacialBody.ALLIGATOR_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS, false);

		if(!isImported) {
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
			
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			// RACE & NAME:
			
			Subspecies species = Subspecies.ALLIGATOR_MORPH;
			
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
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					case ALLIGATOR_MORPH:
						addToSubspeciesMap(30, gender, s, availableRaces);
						break;
					case ANGEL:
						break;
					case CAT_MORPH:
						break;
					case COW_MORPH:
						break;
					case DEMON:
						break;
					case IMP:
						addToSubspeciesMap(50, gender, s, availableRaces);
						break;
					case IMP_ALPHA:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
					case DOG_MORPH:
						break;
					case DOG_MORPH_DOBERMANN:
						break;
					case HARPY:
						break;
					case HORSE_MORPH:
						break;
					case HUMAN:
						break;
					case SLIME:
					case SLIME_ALLIGATOR:
					case SLIME_ANGEL:
					case SLIME_CAT:
					case SLIME_COW:
					case SLIME_DEMON:
					case SLIME_DOG:
					case SLIME_HARPY:
					case SLIME_HORSE:
					case SLIME_IMP:
					case SLIME_REINDEER:
					case SLIME_SQUIRREL:
					case SLIME_WOLF:
						addToSubspeciesMap(30, gender, s, availableRaces);
						break;
					case REINDEER_MORPH:
						break;
					case SQUIRREL_MORPH:
						break;
					case WOLF_MORPH:
						break;
				}
			}
			
			if(gender.isFeminine()) {
				for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().subspeciesFeminineFurryPreferencesMap.entrySet()) {
					if(entry.getValue() == FurryPreference.HUMAN) {
						availableRaces.remove(entry.getKey());
					}
				}
			} else {
				for(Entry<Subspecies, FurryPreference> entry : Main.getProperties().subspeciesMasculineFurryPreferencesMap.entrySet()) {
					if(entry.getValue() == FurryPreference.HUMAN) {
						availableRaces.remove(entry.getKey());
					}
				}
			}
			
			if(availableRaces.isEmpty() || Math.random()<humanChance) {
				setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
				
			} else {
				species = Util.getRandomObjectFromWeightedMap(availableRaces);
				
				if(gender.isFeminine()) {
					switch(Main.getProperties().subspeciesFeminineFurryPreferencesMap.get(species)) {
						case HUMAN:
							setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
							break;
						case MINIMUM:
							setBodyFromPreferences(1, gender, species);
							break;
						case REDUCED:
							setBodyFromPreferences(2, gender, species);
							break;
						case NORMAL:
							setBodyFromPreferences(3, gender, species);
							break;
						case MAXIMUM:
							setBody(gender, species, RaceStage.GREATER);
							break;
					}
				} else {
					switch(Main.getProperties().subspeciesMasculineFurryPreferencesMap.get(species)) {
						case HUMAN:
							setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
							break;
						case MINIMUM:
							setBodyFromPreferences(1, gender, species);
							break;
						case REDUCED:
							setBodyFromPreferences(2, gender, species);
							break;
						case NORMAL:
							setBodyFromPreferences(3, gender, species);
							break;
						case MAXIMUM:
							setBody(gender, species, RaceStage.GREATER);
							break;
					}
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(species.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Submission, who prowls the tunnels looking for strangers to mug and assault..."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(species.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE);
	}
	
	private void addToSubspeciesMap(int weight, Gender gender, Subspecies subspecies, Map<Subspecies, Integer> map) {
		if(gender.isFeminine()) {
			if(Main.getProperties().subspeciesFeminineFurryPreferencesMap!=FurryPreference.HUMAN && Main.getProperties().subspeciesFemininePreferencesMap.get(subspecies).getValue()>0) {
				map.put(subspecies, weight*Main.getProperties().subspeciesFemininePreferencesMap.get(subspecies).getValue());
			}
		} else {
			if(Main.getProperties().subspeciesMasculineFurryPreferencesMap!=FurryPreference.HUMAN && Main.getProperties().subspeciesMasculinePreferencesMap.get(subspecies).getValue()>0) {
				map.put(subspecies, weight*Main.getProperties().subspeciesMasculinePreferencesMap.get(subspecies).getValue());
			}
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	private void setBodyFromPreferences(int i, Gender gender, Subspecies species) {
		int choice = Util.random.nextInt(i)+1;
		RaceStage raceStage = RaceStage.PARTIAL;
		
		if (choice == 1) {
			raceStage = RaceStage.PARTIAL;
		} else if (choice == 2) {
			raceStage = RaceStage.LESSER;
		} else {
			raceStage = RaceStage.GREATER;
		}
		
		setBody(gender, species, raceStage);
	}
	
	@Override
	public String getDescription() {
		if(this.getHistory()==History.PROSTITUTE) {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.Name]'s days of whoring [npc.herself] out in the tunnels of Submission are now over. Having run afoul of the law, [npc.she]'s now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the tunnels of Submission."));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.Name]'s days of prowling the tunnels of Submission and mugging innocent travellers are now over. Having run afoul of the law, [npc.she]'s now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Submission, who prowls the tunnels in search of innocent travellers to mug and rape."));
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
//		if(Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType()==PlaceType.DOMINION_BACK_ALLEYS) { TODO
//			if(this.getHistory()==History.PROSTITUTE) {
//				this.setPlayerKnowsName(true);
//				return AlleywayProstituteDialogue.ALLEY_PROSTITUTE;
//			} else {
//				return AlleywayAttackerDialogue.ALLEY_ATTACK;
//			}
//		} else {
//			return AlleywayAttackerDialogue.STORM_ATTACK;
//		}

		return TunnelAttackDialogue.TUNNEL_ATTACK;
	}

	// Combat:

	@Override
	public Attack attackType() {
		if(this.getRace()==Race.SLIME) {
			if(!getSpecialAttacks().isEmpty()) {
				if (Math.random() < 0.8) {
					return Attack.SEDUCTION;
				} else if (Math.random() < 0.85) {
					return Attack.MAIN;
				} else {
					return Attack.SPECIAL_ATTACK;
				}
				
			} else {
				if (Math.random() < 0.2) {
					return Attack.MAIN;
				} else {
					return Attack.SEDUCTION;
				}
			}
			
		} else {
			if(!getSpecialAttacks().isEmpty()) {
				if (Math.random() < 0.2) {
					return Attack.SEDUCTION;
				} else if (Math.random() < 0.8) {
					return Attack.MAIN;
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
	public Response endCombat(boolean applyEffects, boolean victory) {
//		if(this.getHistory()==History.PROSTITUTE) { TODO
//			if (victory) {
//				return new Response("", "", AlleywayProstituteDialogue.AFTER_COMBAT_VICTORY);
//			} else {
//				return new Response ("", "", AlleywayProstituteDialogue.AFTER_COMBAT_DEFEAT);
//			}
//		} else {
			if (victory) {
				return new Response("", "", TunnelAttackDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", TunnelAttackDialogue.AFTER_COMBAT_DEFEAT);
			}
//		}
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
						if(Sex.isDom(Main.game.getPlayer())) {
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
						if(Sex.isDom(Main.game.getPlayer())) {
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
					
						if(Sex.isDom(Main.game.getPlayer())) {
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
					
						if(Sex.isDom(Main.game.getPlayer())) {
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
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_ALLIGATOR_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_SQUIRREL_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_WOLF_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_COW_MORPH)) {
					
						if(Sex.isDom(Main.game.getPlayer())) {
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
					
						if(Sex.isDom(Main.game.getPlayer())) {
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
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_SWAMP_WATER)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_BUBBLE_MILK)) {
					
						if(Sex.isDom(Main.game.getPlayer())) {
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
					
					if(Sex.isDom(Main.game.getPlayer())) {
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
			return Sex.getActivePartner().useItem(item, target, false);
		}
	}
	
}
