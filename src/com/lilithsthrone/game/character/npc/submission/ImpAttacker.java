package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelImpsDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.12
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
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, subspecies, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS, false);
		
		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
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
	
			// Clothing is equipped in the Encounter class, when the imps are spawned.
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setHomeLocation();
		}
		
		setStartingCombatMoves();
	}
	
	@Override
	public void setStartingCombatMoves() {
		this.clearEquippedMoves();
		if(this.isFeminine()) {
			if(this.hasPenis()) {
				this.equipBasicCombatMoves();
				
			} else {
				this.equipMove("tease");
				this.equipMove("avert");
				this.equipMove("block");
			}
		} else {
			this.equipMove("strike");
			this.equipMove("twin-strike");
			this.equipMove("block");
		}
		this.equipAllKnownMoves();
		this.equipAllSpellMoves();
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) { //TODO gang tattoos?
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		CharacterUtils.generateItemsInInventory(this);
		
		CharacterUtils.equipPiercings(this, true);
		
		if(!this.getAllSpells().isEmpty()) {
			if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
				this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_arcanistStaff_arcanist_staff")));
			}
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_impArcanist_arcanist_hat"), false), true, this);
		}
		
		if(this.isFeminine()) {
			AbstractClothing skirt = AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_loinCloth_ragged_skirt"), false);
			this.equipClothingFromNowhere(skirt, true, this);
			
			// Imps are flying, and don't wear anything on their feet.
			// Alpha-imps also wear accessories as symbols of status.
			if(this.getSubspecies()==Subspecies.IMP_ALPHA) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_loinCloth_foot_wraps"), false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_finger_ring", false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(
						Util.randomItemFrom(new AbstractClothingType[] {ClothingType.getClothingTypeFromId("innoxia_neck_ankh_necklace"), ClothingType.getClothingTypeFromId("innoxia_neck_heart_necklace")}), false), true, this);
			}
			
			if(!this.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
				List<AbstractClothingType> underwear = Util.newArrayListOfValues(
						ClothingType.GROIN_THONG,
						ClothingType.GROIN_VSTRING,
						ClothingType.GROIN_PANTIES,
						ClothingType.GROIN_CROTCHLESS_THONG,
						ClothingType.GROIN_CROTCHLESS_PANTIES);
				this.equipClothingFromNowhere(
						AbstractClothingType.generateClothing(Util.randomItemFrom(underwear), false), true, this);

				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_loinCloth_ragged_chest_wrap"), skirt.getColour(), false), true, this);
			}
			
		} else {
			this.equipClothingFromNowhere(
					AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_loinCloth_loin_cloth"), false), true, this);

			// Imps are flying, and don't wear anything on their feet.
			// Alpha-imps also wear accessories as symbols of status.
			if(this.getSubspecies()==Subspecies.IMP_ALPHA) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_loinCloth_foot_wraps"), false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_hand_wraps", false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.STOMACH_SARASHI, false), true, this);
			}
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
	public DialogueNode getEncounterDialogue() {
		return TunnelImpsDialogue.IMP_ATTACK;
	}
	
	@Override
	public DialogueNode getEnslavementDialogue(AbstractClothing enslavementClothing) {
		SlaveDialogue.setEnslavementTarget(this);
		this.enslavementClothing = enslavementClothing;
		
		if(this.getWorldLocation()==WorldType.SUBMISSION) { //TODO handle enslavement in fortresses
			if(Main.game.getCharactersPresent(this.getCell()).stream().filter((character) -> character instanceof ImpAttacker).count()<=1) { //TODO Add support for enslavement of imp groups
				return TunnelImpsDialogue.IMP_ENSLAVEMENT_DIALOGUE;
			}
		}
		return null;
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			return 0;
		}
		return super.getEscapeChance();
	}
	
	@Override
	public void applyEscapeCombatEffects() {
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_ALPHA_ENTRANCE)) {
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA);
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_DEMON_ENTRANCE)) {
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_MALES_ENTRANCE)) {
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_MALES);
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_FEMALES_ENTRANCE)) {
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES);
			
		} else {
			TunnelImpsDialogue.banishImpGroup();
		}
	};
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_ALPHA_ENTRANCE)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_FEMALES_ENTRANCE)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_MALES_ENTRANCE)) {
			if (victory) {
				return new Response("", "", ImpFortressDialogue.GUARDS_AFTER_COMBAT_VICTORY);
			} else {
				return new Response("", "", ImpFortressDialogue.GUARDS_AFTER_COMBAT_DEFEAT);
			}
			
		} else if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if (victory) {
				if(applyEffects) {
					Main.game.getDialogueFlags().impCitadelImpWave++;
				}
				if(Main.game.getDialogueFlags().impCitadelImpWave>5) {
					return new Response("", "", ImpCitadelDialogue.IMP_FIGHT_AFTER_COMBAT_VICTORY) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(
									UtilText.parseFromXMLFile("places/submission/impCitadel"+ImpCitadelDialogue.getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ATTRIBUTE_BOOST", ImpCitadelDialogue.getAllCharacters()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addSpecialPerk(Perk.IMP_SLAYER));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonImpsDefeated, true);
							if(ImpCitadelDialogue.isCompanionDialogue()) {
								Main.game.getTextEndStringBuilder().append(ImpCitadelDialogue.getMainCompanion().addSpecialPerk(Perk.IMP_SLAYER));
							}
						}
					};
				} else {
					return new Response("", "", ImpCitadelDialogue.IMP_CHALLENGE_CONTINUE) {
						@Override
						public void effects() {
							ImpCitadelDialogue.spawnImps();
						}
					};
				}
				
			} else {
				return new Response("", "", ImpCitadelDialogue.IMP_FIGHT_AFTER_COMBAT_DEFEAT) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_LAB)) {
							if(Main.game.isNonConEnabled() && (Main.game.getPlayer().isFeminine() || (ImpCitadelDialogue.isCompanionDialogue() && ImpCitadelDialogue.getMainCompanion().isFeminine()))) {
								ImpCitadelDialogue.getArcanist().displaceClothingForAccess(CoverableArea.VAGINA, null);
								ImpCitadelDialogue.getArcanist().setLustNoText(50);
							}
						}
						ImpCitadelDialogue.getArcanist().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_LAB);
					}
				};
			}
			
		} else {
			if (victory) {
				Value<AbstractItemType, Map<ItemEffect, String>> effects = TunnelImpsDialogue.getImpLeader().generateTransformativePotion(Main.game.getPlayer());
				if(effects!=null) {
					AbstractItem potion = EnchantingUtils.craftItem(AbstractItemType.generateItem(effects.getKey()), new ArrayList<>(effects.getValue().keySet()));
					TunnelImpsDialogue.getImpGroup().get(1).addItem(potion, false);
				}
				if(!Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
					Value<AbstractItemType, Map<ItemEffect, String>> effects2 = TunnelImpsDialogue.getImpLeader().generateTransformativePotion(Main.game.getPlayer().getMainCompanion());
					if(effects2!=null) {
						AbstractItem potion2 = EnchantingUtils.craftItem(AbstractItemType.generateItem(effects2.getKey()), new ArrayList<>(effects2.getValue().keySet()));
						TunnelImpsDialogue.getImpGroup().get(1).addItem(potion2, false);
					}
				}
				
				return new Response("", "", TunnelImpsDialogue.AFTER_COMBAT_VICTORY);
				
			} else {
				return new Response("", "", TunnelImpsDialogue.AFTER_COMBAT_DEFEAT);
			}
		}
	}
	
	// TF potion:
	
	@Override
	public Value<AbstractItemType,Map<ItemEffect,String>> generateTransformativePotion(GameCharacter target) {
		AbstractItemType itemType = ItemType.RACE_INGREDIENT_HUMAN;
		switch(target.getRace()) {
			case ALLIGATOR_MORPH:
				itemType = ItemType.RACE_INGREDIENT_ALLIGATOR_MORPH;
				break;
			case BAT_MORPH:
				itemType = ItemType.RACE_INGREDIENT_BAT_MORPH;
				break;
			case CAT_MORPH:
				itemType = ItemType.RACE_INGREDIENT_CAT_MORPH;
				break;
			case COW_MORPH:
				itemType = ItemType.RACE_INGREDIENT_COW_MORPH;
				break;
			case DOG_MORPH:
				itemType = ItemType.RACE_INGREDIENT_DOG_MORPH;
				break;
			case FOX_MORPH:
				itemType = ItemType.RACE_INGREDIENT_FOX_MORPH;
				break;
			case HARPY:
				itemType = ItemType.RACE_INGREDIENT_HARPY;
				break;
			case HORSE_MORPH:
				itemType = ItemType.RACE_INGREDIENT_HORSE_MORPH;
				break;
			case RABBIT_MORPH:
				itemType = ItemType.RACE_INGREDIENT_RABBIT_MORPH;
				break;
			case RAT_MORPH:
				itemType = ItemType.RACE_INGREDIENT_RAT_MORPH;
				break;
			case REINDEER_MORPH:
				itemType = ItemType.RACE_INGREDIENT_REINDEER_MORPH;
				break;
			case SQUIRREL_MORPH:
				itemType = ItemType.RACE_INGREDIENT_SQUIRREL_MORPH;
				break;
			case WOLF_MORPH:
				itemType = ItemType.RACE_INGREDIENT_WOLF_MORPH;
				break;
			case ANGEL:
			case DEMON:
			case HUMAN:
			case NONE:
			case SLIME:
			case ELEMENTAL:
				break;
		}
		
		Map<ItemEffect, String> effects = new HashMap<>();
//		int numberOfTransformations = (2+Util.random.nextInt(4)) * (target.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)?2:1);
		
		if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				for(Entry<ItemEffect, String> e : getFeminineEffects(target, itemType).entrySet()) {
					effects.put(e.getKey(), e.getValue());
				}
			}
			
			// Add wet vagina:
			if(!target.hasVagina()) {
				effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						"Let's give yer a nice new cunt!");
			}
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Yer pussy needs ta be soppin' wet an ready ta get fucked!");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
			
			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						"Yer gonna need a cock to satisfy us!");
			}
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Yer cock's gonna need ta be bigger'n than!");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Ready ta be cummin' buckets fer us?");
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE_HEAVY) {
				for(Entry<ItemEffect, String> e : getMasculineEffects(target, itemType).entrySet()) {
					effects.put(e.getKey(), e.getValue());
				}
				
			} else {
				for(Entry<ItemEffect, String> e : getFeminineEffects(target, itemType).entrySet()) {
					effects.put(e.getKey(), e.getValue());
				}
			}

			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						"Yer gonna need a cock to satisfy us!");
			}
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Yer cock's gonna need ta be bigger'n than!");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
					"Let's get yer cock nice an' fat!");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Ready ta be cummin' buckets fer us?");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
			
			// Add wet vagina:
			if(!target.hasVagina()) {
				effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						"Let's give yer a nice new cunt!");
			}
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Yer pussy needs ta be soppin' wet an ready ta get fucked!");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE_HEAVY) {
				for(Entry<ItemEffect, String> e : getMasculineEffects(target, itemType).entrySet()) {
					effects.put(e.getKey(), e.getValue());
				}
			}
			
			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
						"Yer gonna need a cock to satisfy us!");
			}
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Yer cock's gonna need ta be bigger'n than!");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
					"Let's get yer cock nice an' fat!");

			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 1),
					"Gonna make yer balls big an' heavy!");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Ready ta be cummin' buckets fer us?");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
			
			// Add long tongue (for cunnilingus):
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1),
					"Yer tongue's betta off being nice an' long, so that yer can give us a special time when eatin' us out!");
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				for(Entry<ItemEffect, String> e : getFeminineEffects(target, itemType).entrySet()) {
					effects.put(e.getKey(), e.getValue());
				}
			}
			
			// Add wet vagina:
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Yer pussy needs ta be soppin' wet an ready ta get fucked!");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"");
		}
		
		return new Value<>(itemType, effects);
	}
	
	private static Map<ItemEffect, String> getMasculineEffects(GameCharacter target, AbstractItemType itemType) {
		Map<ItemEffect, String> effects = new HashMap<>();
		
		for(int i=target.getFemininityValue(); i>Femininity.MASCULINE.getMinimumFemininity(); i-=15) { // Turn masculine:
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 1),
					"Yer gonna need ta be more manly!");
		}
		if(target.getMuscleValue()<Muscle.THREE_MUSCULAR.getMaximumValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
					"I want yer bein' more muscly!");
		}
		if(target.getBodySizeValue()<BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_BOOST, 1),
					"Yer gonna get more bigger!");
		}
		if(target.getHeightValue()<183) { // 6'
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"That's it; get nice an' tall!");
		}
		for(int i=target.getBreastSize().getMeasurement(); i>0; i-=3) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
					"Let's get rid o' them titties of yours!");
		}
		if(target.getHipSize().getValue()>HipSize.TWO_NARROW.getValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1),
					"Gotta get them hips lookin' less wide!");
		}
		if(target.getAssSize().getValue()>AssSize.THREE_NORMAL.getValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
					"Yer ass is far too fat!");
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i>HairLength.TWO_SHORT.getMaximumValue(); i-=15) {
				effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
						"Yer gonna have ta get a more manly hair-do!");
			}
		}
		for(int i=target.getLipSizeValue(); i>LipSize.TWO_FULL.getValue(); i-=2) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
					"Let's red rid o' them cock-suckin' lips o' yers!");
		}
		
		return effects;
	}
	
	private static Map<ItemEffect, String> getFeminineEffects(GameCharacter target, AbstractItemType itemType) {
		Map<ItemEffect, String> effects = new HashMap<>();
		
		for(int i=target.getFemininityValue(); i<Femininity.FEMININE.getMinimumFemininity(); i+=15) { // Turn feminine:
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1),
					"Yer gonna get lookin' far more girly afta this!");
		}
		if(target.getMuscleValue()>Muscle.THREE_MUSCULAR.getMedianValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1),
					"Let's get rid o' all that manly muscle ya got goin' on!");
		}
		if(target.getBodySizeValue()>BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_DRAIN, 1),
					"Yer gonna need ta get slimmer!");
		}
		if(target.getHeightValue()>167) { // 5'6"
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
					"Yer far too tall fer my tastes!");
		}
		for(int i=target.getBreastSize().getMeasurement(); i<CupSize.E.getMeasurement(); i+=3) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Let's give yer some massive titties for me ta play with!");
		}
		if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
					"Yer hips are gonna need ta be nice an' wide; all tha better fer carrying the broods o' imps yer gonna get knocked up with!");
		}
		if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Yer gonna need a fatta ass than that!");
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i<HairLength.THREE_SHOULDER_LENGTH.getMaximumValue(); i+=15) {
				effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
						"Gonna need yer to have longa hair than that!");
			}
		}
		for(int i=target.getLipSizeValue(); i<LipSize.TWO_FULL.getValue(); i+=2) {
			effects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
					"Time ta give yer some nice an' juicy cock-suckin' lips!");
		}
		
		return effects;
	}
}
