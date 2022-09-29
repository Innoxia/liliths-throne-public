package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RatGangMember extends NPC {

	public RatGangMember() {
		this(Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public RatGangMember(Gender gender) {
		this(gender, false);
	}
	
	public RatGangMember(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public RatGangMember(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5,
				null, null, null,
				new CharacterInventory(10), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);

		if(!isImported) {
			// RACE:
			
			Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
			for(Entry<AbstractSubspecies, SubspeciesPreference> entry : gender.getGenderName().isHasPenis()?Main.getProperties().getSubspeciesMasculinePreferencesMap().entrySet():Main.getProperties().getSubspeciesFemininePreferencesMap().entrySet()) {
				if(entry.getKey().getRace()==Race.RAT_MORPH) {
					subspeciesMap.put(entry.getKey(), entry.getValue().getValue());
				}
			}
			AbstractSubspecies subspecies = Util.getRandomObjectFromWeightedMap(subspeciesMap);
			if(subspecies==null) {
				subspecies = Subspecies.RAT_MORPH;
			}
			RaceStage stage = RaceStage.getRaceStageFromUserPreferences(gender, subspecies);
			if(stage==RaceStage.HUMAN) {
				stage = RaceStage.PARTIAL;
			}
			
			this.setBody(gender, subspecies, stage, true);
			
			Main.game.getCharacterUtils().addFetishes(this);
			// Do not give a negative fetish desire towards these fetishes, as otherwise it ends up in the gang members being gentle in sex, which doesn't really fit, or not using appropriate actions:
			AbstractFetish[] fetishes = new AbstractFetish[] {
					Fetish.FETISH_NON_CON_DOM,
					Fetish.FETISH_SADIST,
					Fetish.FETISH_DOMINANT,
					Fetish.FETISH_PENIS_GIVING,
					Fetish.FETISH_ANAL_GIVING,
					Fetish.FETISH_VAGINAL_GIVING,
					Fetish.FETISH_VAGINAL_RECEIVING,
					Fetish.FETISH_ORAL_RECEIVING};
			for(AbstractFetish f : fetishes) {
				if(this.getFetishDesire(f).isNegative()) {
					this.setFetishDesire(f, FetishDesire.TWO_NEUTRAL);
				}
			}
			
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			this.setGenericName("gang-member");
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this);
	
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			Main.game.getCharacterUtils().applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();
			
			initHealthAndManaToMax();
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
			this.removePersonalityTrait(PersonalityTrait.LISP);
			this.removePersonalityTrait(PersonalityTrait.STUTTER);
			this.removePersonalityTrait(PersonalityTrait.MUTE);
		}
		
		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC); // Just to make player defeats easier to handle
			
			this.setHistory(Occupation.NPC_GANG_MEMBER);
		}
		if(this.hasPenis()) {
			this.setPenisVirgin(false);
		}
		if(this.hasVagina() && !this.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
			this.setVaginaVirgin(false);
		}
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		
		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			this.addTattoo(InventorySlot.WRIST, new Tattoo(TattooType.getTattooTypeFromId("innoxia_gang_rat_skull"), PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false, null, null));
		}
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"Having run afoul of the law while being a member of Vengar's gang, [npc.name] has ended up being enslaved, and is now no more than [npc.her] owner's property."));
		} else {
			return (UtilText.parse(this,
					"[npc.Name] is a member of Vengar's gang, and has undoubtedly committed a large number of crimes against the residents of Submission."));
		}
	}
	
	@Override
	public void endSex() {
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
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(victory) {
			return new Response("", "", RatWarrensDialogue.GUARD_COMBAT_VICTORY) {
				@Override
				public void effects() {
					if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT
							|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_MILKING_ROOM
							|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_MILKING_STORAGE
							|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_LEFT) {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, true);
						
					} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_RIGHT) {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, true);
						
					} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_DICE_DEN
							|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_ENTRANCE) {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, true);
						
					} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, true);
						} else {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, true);
						}
						
					} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, true);
						} else {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, true);
						}
					}
					if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
							|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT
							|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_LEFT) {
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
					}
					RatWarrensDialogue.banishTwoGuards();
				}
			};
			
		} else {
			return new Response("", "", RatWarrensDialogue.GUARD_COMBAT_DEFEAT) {
				@Override
				public void effects() {
					RatWarrensDialogue.applyCombatDefeatFlagsReset();
					Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
				}
			};
		}
	}
}
