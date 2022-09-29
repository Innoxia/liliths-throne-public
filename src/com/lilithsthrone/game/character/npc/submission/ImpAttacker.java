package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
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
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
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
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.12
 * @author Innoxia
 */
public class ImpAttacker extends NPC {

	public ImpAttacker() {
		this(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public ImpAttacker(boolean isImported) {
		this(Subspecies.IMP, Gender.F_V_B_FEMALE, isImported);
	}
	
	public ImpAttacker(AbstractSubspecies subspecies, Gender gender) {
		this(subspecies, gender, false);
	}
	
	public ImpAttacker(AbstractSubspecies subspecies, Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, subspecies, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS, false);
		
		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"Imps, such as this one, have no interest in anything but sex, and will attack anyone who's not a member of their clan in order to get what they want..."));
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this);
	
			// Clothing is equipped in the Encounter class, when the imps are spawned.
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		
		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setHomeLocation();
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
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
		Main.game.getCharacterUtils().generateItemsInInventory(this);

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
	public boolean isAbleToBeEnslaved() {
		return this.getWorldLocation()!=WorldType.IMP_FORTRESS_ALPHA
				&& this.getWorldLocation()!=WorldType.IMP_FORTRESS_DEMON
				&& this.getWorldLocation()!=WorldType.IMP_FORTRESS_FEMALES
				&& this.getWorldLocation()!=WorldType.IMP_FORTRESS_MALES;
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
								for(GameCharacter companion : Main.game.getPlayer().getParty()) {
									Main.game.getTextEndStringBuilder().append(companion.addSpecialPerk(Perk.IMP_SLAYER));
								}
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
				TransformativePotion effects = TunnelImpsDialogue.getImpLeader().generateTransformativePotion(Main.game.getPlayer());
				if(effects!=null) {
					AbstractItem potion = EnchantingUtils.craftItem(
						Main.game.getItemGen().generateItem(effects.getItemType()),
						effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
					potion.setName("Imp's Elixir");
					TunnelImpsDialogue.getImpGroup().get(1).addItem(potion, false);
				}
				if(!Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
					TransformativePotion effects2 = TunnelImpsDialogue.getImpLeader().generateTransformativePotion(Main.game.getPlayer().getMainCompanion());
					if(effects2!=null) {
						AbstractItem potion2 = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects2.getItemType()),
							effects2.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						potion2.setName("Imp's Elixir");
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
	public TransformativePotion generateTransformativePotion(GameCharacter target) {
		AbstractItemType itemType = target.getSubspecies().getTransformativeItem(target);
        if(itemType==null || itemType.equals(ItemType.getItemTypeFromId("innoxia_race_slime_slime_quencher"))) {
            itemType = ItemType.getItemTypeFromId("innoxia_race_human_bread_roll");
        }
		
		List<PossibleItemEffect> effects = new ArrayList<>();
//		int numberOfTransformations = (2+Util.random.nextInt(4)) * (target.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)?2:1);
		
		if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				effects.addAll(getFeminineEffects(target, itemType));
			}
			
			// Add wet vagina:
			if(!target.hasVagina()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"Let's give yer a nice new cunt!"));
			}
			if(target.getVaginaWetness().getValue()<Wetness.SIX_SOPPING_WET.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Yer pussy needs ta be soppin' wet an ready ta get fucked!"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			
			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"Yer gonna need a cock to satisfy us!"));
			}
			if(target.getPenisRawSizeValue()<PenisLength.THREE_LARGE.getMedianValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Yer cock's gonna need ta be bigger'n than!"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			if(target.getPenisRawCumStorageValue()<100) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Yer gonna be cummin' all over the place!"));
			}
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE_HEAVY) {
				effects.addAll(getMasculineEffects(target, itemType));
			} else {
				effects.addAll(getFeminineEffects(target, itemType));
			}

			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"Yer gonna need a cock to satisfy us!"));
			}
			if(target.getPenisRawSizeValue()<PenisLength.THREE_LARGE.getMedianValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Yer cock's gonna need ta be bigger'n than!"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			if(target.getPenisRawGirthValue()<PenetrationGirth.FOUR_GIRTHY.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
					"Let's get yer cock nice an' thick!"));
			}
			if(target.getPenisRawCumStorageValue()<250) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Ready ta be cummin' buckets fer us?"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			
			// Add wet vagina:
			if(!target.hasVagina()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"Let's give yer a nice new cunt!"));
			}
			if(target.getVaginaWetness().getValue()<Wetness.SIX_SOPPING_WET.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Yer pussy needs ta be soppin' wet an ready ta get fucked!"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE_HEAVY) {
				effects.addAll(getMasculineEffects(target, itemType));
			}
			
			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"Yer gonna need a cock to satisfy us!"));
			}
			if(target.getPenisRawSizeValue()<PenisLength.THREE_LARGE.getMedianValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Yer cock's gonna need ta be bigger'n than!"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			if(target.getPenisRawGirthValue()<PenetrationGirth.FOUR_GIRTHY.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
					"Let's get yer cock nice an' thick!"));
			}

			if(target.getTesticleSize().getValue()<TesticleSize.FOUR_HUGE.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 1),
					"Gonna make yer balls big an' heavy!"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 1),
					""));
			}
			if(target.getPenisRawCumStorageValue()<250) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Ready ta be cummin' buckets fer us?"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			
			// Add long tongue (for cunnilingus):
			if(target.getTongueLengthValue()<TongueLength.TWO_VERY_LONG.getMedianValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1),
					"Yer tongue's betta off being nice an' long, so that yer can give us a special time when eatin' us out!"));
			}
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				effects.addAll(getFeminineEffects(target, itemType));
			}
			
			// Add wet vagina:
			if(!target.hasVagina()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"Let's give yer a cunt ta fuck!"));
			}
			if(target.getVaginaWetness().getValue()<Wetness.SIX_SOPPING_WET.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"Yer pussy needs ta be soppin' wet an ready ta get fucked!"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
		}

		if(effects.isEmpty()) {
			return null;
		}
		
		return new TransformativePotion(itemType, effects);
	}
	
	private static List<PossibleItemEffect> getMasculineEffects(GameCharacter target, AbstractItemType itemType) {
		List<PossibleItemEffect> effects = new ArrayList<>();
		
		for(int i=target.getFemininityValue(); i>Femininity.MASCULINE.getMinimumFemininity(); i-=15) { // Turn masculine:
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 1),
				"Yer gonna need ta be more manly!"));
		}
		if(target.getMuscleValue()<Muscle.THREE_MUSCULAR.getMaximumValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
				"I want yer bein' more muscly!"));
		}
		if(target.getBodySizeValue()<BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_BOOST, 1),
				"Yer gonna get more bigger!"));
		}
		if(target.getHeightValue()<183) { // 6'
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
				"That's it; get nice an' tall!"));
		}
		for(int i=target.getBreastSize().getMeasurement(); i>0; i-=3) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
				"Let's get rid o' them titties of yours!"));
		}
		if(target.getHipSize().getValue()>HipSize.TWO_NARROW.getValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1),
				"Gotta get them hips lookin' less wide!"));
		}
		if(target.getAssSize().getValue()>AssSize.THREE_NORMAL.getValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
				"Yer ass is far too fat!"));
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i>HairLength.TWO_SHORT.getMaximumValue(); i-=15) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
					"Yer gonna have ta get a more manly hair-do!"));
			}
		}
		for(int i=target.getLipSizeValue(); i>LipSize.TWO_FULL.getValue(); i-=2) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
				"Let's red rid o' them cock-suckin' lips o' yers!"));
		}
		
		return effects;
	}
	
	private static List<PossibleItemEffect> getFeminineEffects(GameCharacter target, AbstractItemType itemType) {
		List<PossibleItemEffect> effects = new ArrayList<>();
		
		for(int i=target.getFemininityValue(); i<Femininity.FEMININE.getMinimumFemininity(); i+=15) { // Turn feminine:
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1),
				"Yer gonna get lookin' far more girly afta this!"));
		}
		if(target.getMuscleValue()>Muscle.THREE_MUSCULAR.getMedianValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1),
				"Let's get rid o' all that manly muscle ya got goin' on!"));
		}
		if(target.getBodySizeValue()>BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_DRAIN, 1),
				"Yer gonna need ta get slimmer!"));
		}
		if(target.getHeightValue()>167) { // 5'6"
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
				"Yer far too tall fer my tastes!"));
		}
		for(int i=target.getBreastSize().getMeasurement(); i<CupSize.E.getMeasurement(); i+=3) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
				"Let's give yer some massive titties for me ta play with!"));
		}
		if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
				"Yer hips are gonna need ta be nice an' wide; all tha better fer carrying the broods o' imps yer gonna get knocked up with!"));
		}
		if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
				"Yer gonna need a fatta ass than that!"));
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i<HairLength.THREE_SHOULDER_LENGTH.getMaximumValue(); i+=15) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"Gonna need yer to have longa hair than that!"));
			}
		}
		for(int i=target.getLipSizeValue(); i<LipSize.TWO_FULL.getValue(); i+=2) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
				"Time ta give yer some nice an' juicy cock-suckin' lips!"));
		}
		
		return effects;
	}
}
