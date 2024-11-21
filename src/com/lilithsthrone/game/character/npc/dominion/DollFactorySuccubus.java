package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayDemonDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * Most of the methods relating to this class are found in Saellatrix's class.
 * This is because those methods needed to be accessed in a non-static way before an instance of this class has been created.
 * 
 * @since 0.4.9.12
 * @version 0.4.9.12
 * @author Innoxia
 */
public class DollFactorySuccubus extends NPC {
	
	public DollFactorySuccubus() {
		this(false);
	}
	
	public DollFactorySuccubus(boolean isImported) {
		super(isImported, null, "Loviennemartuilani",
				"",
				Util.random.nextInt(50)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(28),
				25,
				Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), 
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
//			this.setLocation(Main.game.getPlayer(), true);
			
			setLevel(Util.random.nextInt(11) + 20);
			
			setName(Name.getRandomTriplet(Subspecies.DEMON));
			this.setPlayerKnowsName(false);
//			this.setGenericName("punished succubus");
			
//			// Set starting perks based on the character's race
//			initPerkTreeAndBackgroundPerks();
//			this.setStartingCombatMoves();
//			loadImages();
//			
//			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		this.equipMove("BASIC_STRIKE");
		this.equipMove("BASIC_TEASE");
		this.equipAllSpellMoves();
		this.equipAllSpecialMoves();
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.clearPersonalityTraits();
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.setPersonalityTraits(
					PersonalityTrait.LEWD,
					PersonalityTrait.SELFISH);

			
			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3);
			
			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3);

			this.addSpell(Spell.ARCANE_CLOUD);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_1);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_2);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_3);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_SLAVE);
	
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
		}

		Main.game.getCharacterUtils().randomiseBody(this, true);
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.resetInventory(true);
		
		this.addTattoo(InventorySlot.TORSO_OVER,
				new Tattoo(
					"innoxia_symbol_pentagram",
					PresetColour.CLOTHING_GOLD,
					PresetColour.CLOTHING_GOLD,
					PresetColour.CLOTHING_GOLD,
					false,
					new TattooWriting(
							"Property of Saellatrix",
							PresetColour.CLOTHING_GOLD,
							false),
					new TattooCounter(
							TattooCounterType.VALUE_AS_SLAVE,
							TattooCountType.NUMBERS,
							PresetColour.CLOTHING_GOLD,
							false)));
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this,
				"Having been outplayed and utterly defeated in the game of demonic politics by her sister, Saellatrix, this succubus is now no more than a slave."
				+ " Putting her to use, Saellatrix has ordered her to oversee the dolls who work in her factory.");
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
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character instanceof Saellatrix || character instanceof DollFactorySuccubus) {
			Set<Relationship> result = new LinkedHashSet<>();
			result.add(Relationship.HalfSibling);
			return result;
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AlleywayDemonDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", AlleywayDemonDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	// combat behaviour is tease and spells
	@Override
	public CombatBehaviour getCombatBehaviour() {
		boolean spellsAvailable = false;
		if(Main.game.isInCombat()) {
			for(GameCharacter character : Main.combat.getAllCombatants(true)) {
				if(!getWeightedSpellsAvailable(character).isEmpty()) {
					spellsAvailable = true;
					break;
				}
			}
		}
		if(spellsAvailable && Util.random.nextFloat()<0.6f) {
			return CombatBehaviour.SPELLS;
		}
		return CombatBehaviour.SEDUCE;
	}
	
	// Sex:

	@Override
	public int getUniqueSexPartnerCount() {
		return super.getUniqueSexPartnerCount() + 50 + (this.getLevel() * 3) + (int)(this.getAgeValue() * 0.8f) + (this.getAppearsAsAgeValue() * 2);
	}
	
	@Override
	public Value<AbstractItem, String> getSexItemToUse(GameCharacter partner) {
		if(this.equals(partner) && this.hasFetish(Fetish.FETISH_PENIS_GIVING)) { // Self-using for cock succubus
			if(this.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
				if(!this.hasStatusEffect(StatusEffect.CUM_FULL)
						&& this.hasItemType(ItemType.REJUVENATION_POTION)
						&& Main.sex.getNumberOfOrgasms(this)<3) {
					return new Value<>(Main.game.getItemGen().generateItem(ItemType.REJUVENATION_POTION),
								UtilText.parse(this,
										Main.sex.getNumberOfOrgasms(this)==1
											?"Producing a glass bottle filled with purple liquid, the succubus pulls out the stopper and tosses it over her shoulder, before downing the contents in three big gulps."
												+ " Her gigantic balls visibly swell as the rejuvenation potion does its work, and she happily moans,"
												+ " [npc.speechNoEffects(~Ooh!~ Fuck! My balls feel so full! ~Mmm!~ Now I'm ready to pump another load in you, bitch!)]"
											:"Producing another glass bottle filled with yet more purple liquid, the succubus once again pulls out the stopper and tosses it over her shoulder, before downing the contents in three big gulps."
												+ " Just like before, her gigantic balls visibly swell as the rejuvenation potion does its work, and she excitedly moans,"
												+ " [npc.speechNoEffects(~Aah!~ Fuck! Got another full load for you, bitch! ~Mmm!~ I'm going to fill you up real good...)]"));
				}
			}
		}
		return super.getSexItemToUse(partner);
	}
}
