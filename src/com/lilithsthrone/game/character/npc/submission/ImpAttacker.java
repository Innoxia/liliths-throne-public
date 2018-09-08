package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelImpsDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class ImpAttacker extends NPC {

	public ImpAttacker() {
		this(Subspecies.IMP, Gender.F_V_B_FEMALE, false);
	}
	
	public ImpAttacker(boolean isImported) {
		this(Subspecies.IMP, Gender.F_V_B_FEMALE, isImported);
	}
	
	public ImpAttacker(Subspecies subspecies, Gender gender) {
		this(subspecies, gender, false);
	}
	
	public ImpAttacker(Subspecies subspecies, Gender gender, boolean isImported) {
		super(isImported, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, subspecies, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS, false);

		if(!isImported) {
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
			
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"Imps, such as this one, have no interest in anything but sex, and will attack anyone who's not a member of their clan in order to get what they want..."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, true);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this, true);
			
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
		
		this.setEnslavementDialogue(TunnelImpsDialogue.IMP_ENSLAVEMENT_DIALOGUE);
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
					"[npc.NamePos] days of prowling the tunnels of Submission and assaulting innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else {
			return (UtilText.parse(this, description));
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
		return TunnelImpsDialogue.IMP_ATTACK;
	}

	// Combat:
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", TunnelImpsDialogue.AFTER_COMBAT_VICTORY);
			
		} else {
			return new Response("", "", TunnelImpsDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	// TF potion:

	@Override
	public Value<String, AbstractItem> getTransfomativePotion(GameCharacter target, boolean generateNew) {
		if(generateNew) {
			this.heldTransformativePotion = generateTransformativePotion(target);
		}
		
		return this.heldTransformativePotion;
	}
	
	@Override
	public Value<String, AbstractItem> generateTransformativePotion(GameCharacter target) {
		
		AbstractItemType itemType = ItemType.RACE_INGREDIENT_HUMAN;
		List<ItemEffect> effects = new ArrayList<>();

		
		/* TODO
		 * Alpha:
		 * Give penis and vagina and increase cum and wetness
		 * Increase cum should refill
		 * 
		 * Demon:
		 * Transforms into female or male (based on player preferences).
		 * Gives kinks so want to get fucked.
		 * 
		 * Females:
		 * If can access cock, give cock.
		 * If can access mouth, give long tongue and oral fetish.
		 * 
		 * Males:
		 * If can access vagina, give vagina.
		 * 
		 */
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA) { //TODO all
			// Alpha imp group encounter:
			// Add vagina:
			if(!target.hasVagina()) {
				effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1));
			}
			effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1));
			effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1));
			// Add penis:
			if(!target.hasPenis()) {
				effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1));
			}
			effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1));
			effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1));
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_DEMON) {
			// Demon group encounter:
			if(target.isFeminine()) {
				effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1));
				if(target.getMuscleValue()>Muscle.THREE_MUSCULAR.getMedianValue()) {
					effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.DRAIN, 1));
				}
				if(target.getBodySizeValue()>BodySize.TWO_AVERAGE.getMinimumValue()) {
					effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.DRAIN, 1));
				}
				if(target.getHeightValue()>167) { // 5'6"
					effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1));
				}
				effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1));
				effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1));
				if(target.getBreastRawSizeValue()<CupSize.FF.getMeasurement()) {
					effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1));
				}
				
			} else {
				effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 1));
				if(target.getMuscleValue()<Muscle.THREE_MUSCULAR.getMaximumValue()) {
					effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1));
				}
				if(target.getBodySizeValue()<BodySize.TWO_AVERAGE.getMinimumValue()) {
					effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.BOOST, 1));
				}
				if(target.getHeightValue()<183) { // 6'
					effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1));
				}
				if(target.hasBreasts()) {
					effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1));
				}
			}
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES) {
			// Female imps encounter:
			// Add penis:
			if(!target.hasPenis()) {
				effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1));
			}
			effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1));
			effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1));
			
		} else {
			// Male imps encounter:
			// Add vagina:
			if(!target.hasVagina()) {
				effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1));
			}
			effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1));
			effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1));
		}
		
		return new Value<>(
				"",
				EnchantingUtils.craftItem(AbstractItemType.generateItem(itemType), effects));
	}
}
