package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.BatCavernAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.BatCavernBatAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.BatCavernSlimeAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelAttackDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.2.6
 * @author Innoxia
 */
public class SlimeCavernAttacker extends NPC {

	private static final long serialVersionUID = 1L;

	public SlimeCavernAttacker() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public SlimeCavernAttacker(Gender gender) {
		this(gender, false);
	}
	
	public SlimeCavernAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public SlimeCavernAttacker(Gender gender, boolean isImported) {
		super(null, "",
				Util.random.nextInt(21)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, RacialBody.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10), WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_DARK, false);

		if(!isImported) {
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
			
			// Set random level from 8 to 12:
			setLevel(8 + Util.random.nextInt(5));
			
			// RACE & NAME:
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No chance of spawn:
					case ANGEL:
					case CAT_MORPH:
					case CAT_MORPH_LEOPARD:
					case CAT_MORPH_LEOPARD_SNOW:
					case CAT_MORPH_LION:
					case CAT_MORPH_TIGER:
					case CAT_MORPH_LYNX:
					case CAT_MORPH_CHEETAH:
					case CAT_MORPH_CARACAL:
					case COW_MORPH:
					case DEMON:
					case DOG_MORPH:
					case DOG_MORPH_DOBERMANN:
					case DOG_MORPH_BORDER_COLLIE:
					case FOX_MORPH:
					case FOX_MORPH_FENNEC:
					case FOX_ASCENDANT:
					case FOX_ASCENDANT_FENNEC:
					case HARPY:
					case HARPY_RAVEN:
					case HARPY_BALD_EAGLE:
					case HORSE_MORPH:
					case HORSE_MORPH_ZEBRA:
					case HUMAN:
					case REINDEER_MORPH:
					case SQUIRREL_MORPH:
					case WOLF_MORPH:
					case RABBIT_MORPH:
					case RABBIT_MORPH_LOP:
					case BAT_MORPH:
					case IMP_ALPHA:
					case ALLIGATOR_MORPH:
					case IMP:
					case RAT_MORPH:
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
						break;
						
					case SLIME:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
					case SLIME_ALLIGATOR:
					case SLIME_ANGEL:
					case SLIME_CAT:
					case SLIME_CAT_LEOPARD:
					case SLIME_CAT_LEOPARD_SNOW:
					case SLIME_CAT_LYNX:
					case SLIME_CAT_LION:
					case SLIME_CAT_TIGER:
					case SLIME_CAT_CARACAL:
					case SLIME_CAT_CHEETAH:
					case SLIME_COW:
					case SLIME_DEMON:
					case SLIME_DOG:
					case SLIME_DOG_DOBERMANN:
					case SLIME_DOG_BORDER_COLLIE:
					case SLIME_FOX:
					case SLIME_FOX_FENNEC:
					case SLIME_HARPY:
					case SLIME_HARPY_RAVEN:
					case SLIME_HARPY_BALD_EAGLE:
					case SLIME_HORSE:
					case SLIME_IMP:
					case SLIME_REINDEER:
					case SLIME_SQUIRREL:
					case SLIME_WOLF:
					case SLIME_RAT:
					case SLIME_BAT:
					case SLIME_RABBIT:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, true);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			this.setBodyMaterial(BodyMaterial.SLIME);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(50 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
			this.useItem(AbstractItemType.generateItem(ItemType.MUSHROOM), this, false);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void hourlyUpdate() {
		if(!this.isSlave()) {
			this.useItem(AbstractItemType.generateItem(ItemType.MUSHROOM), this, false);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos] days of getting high on mushrooms and attacking innocent travellers in the Bat Caverns are now over."
							+ " Having been enslaved as punishment for [npc.her] lawless behaviour, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else {
			return (UtilText.parse(this,
					"[npc.Name] is a resident of the bat caverns, and loves nothing more than getting high on mushrooms, attacking innocent travellers, and having sex."));
		}
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
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
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
			return BatCavernSlimeAttackerDialogue.SLIME_ATTACK;
			
		} if(this.getRace()==Race.BAT_MORPH) {
			return BatCavernBatAttackerDialogue.BAT_MORPH_ATTACK;
			
		} else {
			return BatCavernAttackerDialogue.ATTACK;
		}
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
			if (victory) {
				return new Response("", "", BatCavernSlimeAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", BatCavernSlimeAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
			
		} if(this.getRace()==Race.BAT_MORPH) {
			if (victory) {
				return new Response("", "", BatCavernBatAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", BatCavernBatAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
			
		} else {
			if (victory) {
				return new Response("", "", TunnelAttackDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", TunnelAttackDialogue.AFTER_COMBAT_DEFEAT);
			}
		}
	}

	@Override
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		return getItemUseEffectsAllowingUse(item, user, target);
	}
}
