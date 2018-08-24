package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
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
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.2.4
 * @author Innoxia
 */
public class BatMorphCavernAttacker extends NPC {

	public BatMorphCavernAttacker() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public BatMorphCavernAttacker(Gender gender) {
		this(gender, false);
	}
	
	public BatMorphCavernAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public BatMorphCavernAttacker(Gender gender, boolean isImported) {
		super(isImported, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.BAT_MORPH, RaceStage.LESSER,
				new CharacterInventory(10), WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_DARK, false);

		if(!isImported) {
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));

			// Set random level from 8 to 12:
			setLevel(8 + Util.random.nextInt(5));
			
			// RACE & NAME:
			
			Subspecies species = Subspecies.BAT_MORPH;
			
			if(gender.isFeminine()) {
				switch(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species)) {
					case MAXIMUM:
						setBody(gender, species, RaceStage.GREATER);
						break;
					default:
						break;
				}
			} else {
				switch(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(species)) {
					case MAXIMUM:
						setBody(gender, species, RaceStage.GREATER);
						break;
					default:
						break;
				}
			}
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(species.getRace()));
			this.setPlayerKnowsName(false);
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, true);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
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
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos) {
		// Not needed
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos] days of attacking innocent travellers in the bat caverns are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else {
			return (UtilText.parse(this,
					"[npc.Name] is a resident of the bat caverns, and enjoys nothing more than attacking innocent travellers that pass by [npc.her] roost."));
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

}
