package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * Dialogue for when the player (plus companion, if applicable) is taken captive by the rats in the Rat Warrens.
 * 
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RatWarrensCaptiveDialogue {
	
	private static CaptiveInteractionType playerInteraction;
	private static boolean playerMurkSex;
	private static Value<SexSlot, SexType> playerFuckedSexType;
	
	private static List<GameCharacter> getCharacters(boolean includeCompanion, boolean includeMilkers) {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc) || (!includeMilkers && (npc instanceof RatWarrensCaptive)));
		Collections.sort(guards, (a, b)->a.getLevel()-b.getLevel());
		if(Main.game.getPlayer().hasCompanions() && includeCompanion) {
			guards.add(0, Main.game.getPlayer().getMainCompanion());
		}
		return guards;
	}
	
	private static GameCharacter getMainCompanion() {
		if(Main.game.getPlayer().hasCompanions()) {
			return Main.game.getPlayer().getMainCompanion();
		}
		return null;
	}
	
	private static void spawnRats(int count) {
		List<String> adjectives = new ArrayList<>();
		for(int i=0;i<count;i++) {
			try {
				String[] names = new String[] {"thug", "gangster", "gang-member", "mobster"};
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, i==0));
				Main.game.addNPC(rat, false);
				rat.setLevel(8-i);
				rat.setLocation(Main.game.getPlayer(), true);
				adjectives.add(CharacterUtils.setGenericName(rat, Util.randomItemFrom(names), adjectives));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void banishRats() {
		for(GameCharacter rat : getCharacters(false, false)) {
			Main.game.banishNPC((NPC) rat);
		}
	}
	
	private static GameCharacter getOwner() {
		return Main.game.getNpc(Murk.class);
	}
	
	private static boolean isCompanionDialogue() {
		return getMainCompanion()!=null;
	}

	public static String equipCollar(GameCharacter character, Colour collarColour) {
		AbstractClothing collar = AbstractClothingType.generateClothing("innoxia_bdsm_metal_collar", collarColour, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_GUNMETAL, false);
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		return character.equipClothingFromNowhere(collar, true, character);
	}
	
	private static String equipRingGag(GameCharacter character) {
		AbstractClothing gag = AbstractClothingType.generateClothing(ClothingType.BDSM_RINGGAG, PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_STEEL, false);
		gag.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.BOOST, 0));
		gag.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_BOOST, 0));
		return character.equipClothingFromNowhere(gag, true, getOwner());
	}
	
	private static String getCompanionTfEffects() {
		StringBuilder sb = new StringBuilder();
		// Companion effects:
		if(isCompanionDialogue()) {
			CaptiveTransformation companionTf = CaptiveTransformation.getTransformationType(getMainCompanion());
			
			if(companionTf!=null) {
				if(getMainCompanion().isAbleToSelfTransform()
						&& companionTf!=CaptiveTransformation.FEMININE_FETISH
						&& companionTf!=CaptiveTransformation.MASCULINE_FETISH) {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_SELF", getCharacters(true, false)));
					
				} else if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive() || getMainCompanion().getClothingInSlot(InventorySlot.MOUTH)!=null) {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_SWALLOW", getCharacters(true, false)));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_REFUSE", getCharacters(true, false)));
					equipRingGag(getMainCompanion());
					return sb.toString(); // Return before applying effects, as this is the variation where your companion spits out the potion. 
				}
				
//				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION", getCharacters(false)));
				Map<String, String> effects = companionTf.getEffects(getMainCompanion());
				for(Entry<String, String> entry : effects.entrySet()) {
					sb.append(
							"<p>"
								+ UtilText.parse(getOwner(), "[npc.speech("+entry.getKey()+")]")
							+ "</p>"
							+ entry.getValue());
				}
				
			} else {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCompanionFuckedByMurk)) {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_FIRST_FUCK", getCharacters(true, false)));
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveMilkingStartedCompanion)) {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_MILKING", getCharacters(true, false)));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_MILKING_START", getCharacters(true, false)));
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveMilkingStartedCompanion, true);
				}
			}
		}
		
		return sb.toString();
	}
	
	private static SexType getRandomSexTypeForCompanion(GameCharacter partner) {
		SexType sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
		float rnd = (float) Math.random();
		if(getMainCompanion().hasVagina() && rnd<0.8f) {
			sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
		}
		if(Main.game.isAnalContentEnabled() && rnd<0.4f) {
			sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
		}
		if(partner==null && getMainCompanion().hasPenis() && rnd<0.2f) { // Murk does not do this
			if(rnd<0.05f) {
				sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
			} else {
				sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
			}
		}
		return sexType;
	}
	
	private static void calculatePlayerSexType(boolean includeForeplayTypes) {
		List<Value<SexSlot, SexType>> sexTypes = new ArrayList<>();
		
		if(Main.game.isAnalContentEnabled()) {
			sexTypes.add(new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)));
		}
		if(Main.game.getPlayer().hasVagina()) {
			sexTypes.add(new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)));
		}
		sexTypes.add(new Value<>(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)));
		
		if(includeForeplayTypes) {
			if(Main.game.isAnalContentEnabled()) {
				sexTypes.add(new Value<>(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)));
				sexTypes.add(new Value<>(SexSlotMilkingStall.PERFORMING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)));
			}
			
			if(Main.game.getPlayer().hasPenis()) {
				sexTypes.add(new Value<>(SexSlotMilkingStall.PERFORMING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)));
			}
			
			if(Main.game.getPlayer().hasVagina()) {
				sexTypes.add(new Value<>(SexSlotMilkingStall.PERFORMING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)));
				sexTypes.add(new Value<>(SexSlotMilkingStall.PERFORMING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)));
			}
		}
		
		playerFuckedSexType = Util.randomItemFrom(sexTypes);
	}
	
	private static ResponseSex getPlayerOwnerSexResponse(String title, String description, DialogueNode node) {
		String introDescriptionPath = "BEG_FOR_SEX";
		if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
			introDescriptionPath = "BEG_FOR_SEX_ANAL";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
			introDescriptionPath = "BEG_FOR_SEX_VAGINAL";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
			introDescriptionPath = "BEG_FOR_SEX_BLOWJOB";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
			introDescriptionPath = "BEG_FOR_SEX_ANILINGUS";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))) {
			introDescriptionPath = "BEG_FOR_SEX_RECEIVE_ANILINGUS";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS))) {
			introDescriptionPath = "BEG_FOR_SEX_RECEIVE_HANDJOB";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))) {
			introDescriptionPath = "BEG_FOR_SEX_RECEIVE_FINGERING";
			
		} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))) {
			introDescriptionPath = "BEG_FOR_SEX_RECEIVE_CUNNILINGUS";
		}
		
		return new ResponseSex(title,
				description,
				true,
				false,
				new SexManagerDefault(
						SexPosition.MILKING_STALL,
						Util.newHashMapOfValues(
								new Value<>(getOwner(), playerFuckedSexType.getKey())),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
					@Override
					public SexControl getSexControl(GameCharacter character) {
						if(character.isPlayer() || character.equals(getMainCompanion())) {
							return SexControl.NONE;
						}
						return super.getSexControl(character);
					}
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
						if(character.isPlayer() || character.equals(getMainCompanion())) {
							return false;
						}
						return super.isAbleToRemoveOthersClothing(character, clothing);
					}
					@Override
					public boolean isAbleToEquipSexClothing(GameCharacter character) {
						return !character.isPlayer() && !character.equals(getMainCompanion());
					}
					@Override
					public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
						return !character.isPlayer() && !character.equals(getMainCompanion());
					}
					@Override
					public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
						if(!performer.isPlayer()
								&& (playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaPenetration.PENIS || playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaOrifice.VAGINA)) {
							return Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA);
						}
						return new ArrayList<>();
					}
					@Override
					public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getForeplayPreference(character, targetedCharacter);
						}
						return playerFuckedSexType.getValue();
					}
					@Override
					public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getMainSexPreference(character, targetedCharacter);
						}
						return playerFuckedSexType.getValue();
					}
					
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", introDescriptionPath, getCharacters(true, false))) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));

				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
				}
				return super.getInitialSexActions();
			}
		};
	}
	
	private static ResponseSex getPlayerOwnerEscapeSexResponse(DialogueNode node, String nodePathOral, String nodePathSex) {
		playerFuckedSexType =  new Value<>(SexSlotAllFours.IN_FRONT, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)) {
			if(Main.game.getPlayer().hasVagina()) {
				playerFuckedSexType = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
				
			} else if(Main.game.isAnalContentEnabled()) {
				playerFuckedSexType = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
			}
		}
		return new ResponseSex(
				playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH
					?"Get down"
					:"Lie back",
				playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH
					?"Get down on all fours to show your submission to Murk, allowing him to fuck your face."
					:"Lie down on your back to show your submission to Murk, allowing him to fuck you.",
				true,
				false,
				new SexManagerDefault(
						playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH
							?SexPosition.ALL_FOURS
							:SexPosition.LYING_DOWN,
						Util.newHashMapOfValues(
								new Value<>(getOwner(), playerFuckedSexType.getKey())),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), 
										playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH
											?SexSlotAllFours.ALL_FOURS
											:SexSlotLyingDown.LYING_DOWN))) {
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
						if(character.isPlayer() || character.equals(getMainCompanion())) {
							return false;
						}
						return super.isAbleToRemoveOthersClothing(character, clothing);
					}
					@Override
					public boolean isAbleToEquipSexClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
						return !character.isPlayer();
					}
					@Override
					public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
						if(!performer.isPlayer()
								&& (playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaPenetration.PENIS || playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaOrifice.VAGINA)) {
							return Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA);
						}
						return new ArrayList<>();
					}
					@Override
					public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getForeplayPreference(character, targetedCharacter);
						}
						return playerFuckedSexType.getValue();
					}
					@Override
					public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getMainSexPreference(character, targetedCharacter);
						}
						return playerFuckedSexType.getValue();
					}
					
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH?nodePathOral:nodePathSex, getCharacters(true, false))) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					
				} else if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					
				} else {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					
				}
			}
		};
	}
	
	private static ResponseSex getPlayerMilkingStallSexResponse(String title, String description, DialogueNode node, Map<GameCharacter, SexSlot> npcSlots, String introDialoguePath) {
		return new ResponseSex(title,
				description,
				true,
				false,
				new SexManagerDefault(
						SexPosition.MILKING_STALL,
						npcSlots,
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
					@Override
					public SexControl getSexControl(GameCharacter character) {
						if(character.isPlayer() || character.equals(getMainCompanion())) {
							return SexControl.NONE;
						}
						return super.getSexControl(character);
					}
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
						if(character.isPlayer() || character.equals(getMainCompanion())) {
							return false;
						}
						return super.isAbleToRemoveOthersClothing(character, clothing);
					}
					@Override
					public boolean isAbleToEquipSexClothing(GameCharacter character) {
						return !character.isPlayer() && !character.equals(getMainCompanion());
					}
					@Override
					public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
						return !character.isPlayer() && !character.equals(getMainCompanion());
					}
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", introDialoguePath, getCharacters(true, false)));
	}
	
	private static void applyWaitingEffects() {
		Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_START", getCharacters(true, false)));
		
		getOwner().returnToHome();
		
		if(isCompanionDialogue()) {
			if(getMainCompanion().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				getMainCompanion().endPregnancy(true);
				getMainCompanion().setMana(0);
				
//				if(getMainCompanion().getBodyMaterial()!=BodyMaterial.SLIME) {
//					getMainCompanion().incrementVaginaStretchedCapacity(15);
//					getMainCompanion().incrementVaginaCapacity(
//							(getMainCompanion().getVaginaStretchedCapacity()-getMainCompanion().getVaginaRawCapacityValue())*getMainCompanion().getVaginaPlasticity().getCapacityIncreaseModifier(),
//							false);
//				}
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveCompanionGivenBirth, true);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_END_PREGNANACY", getCharacters(true, false)));
				
			} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCompanionGivenBirth)){ // If given birth today, let them rest
				CaptiveInteractionType companionInteraction = CaptiveInteractionType.getRandomType(getMainCompanion());
				boolean murkSex=false;
				switch(companionInteraction) {
					case MILKING:
						getOwner().setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_MILKING", getCharacters(true, false)));
						murkSex = Math.random()<0.25f
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveOwnerCompanionSex);
						break;
						
					case PUNISHMENT:
						getOwner().setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_PUNISHMENT", getCharacters(true, false)));
						murkSex = Math.random()<0.25f
								&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCompanionFuckedByMurk)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveOwnerCompanionSex);
						break;
						
					case RAT_SEX:
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_SEX", getCharacters(true, false)));
						Main.game.getTextStartStringBuilder().append(getMainCompanion().calculateGenericSexEffects(false, true, null, Subspecies.RAT_MORPH, null, getRandomSexTypeForCompanion(null), GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED));
						break;
						
					case RAT_SEX_THREESOME:
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_SEX_THREESOME", getCharacters(true, false)));
						Main.game.getTextStartStringBuilder().append(getMainCompanion().calculateGenericSexEffects(false, true, null, Subspecies.RAT_MORPH, null, getRandomSexTypeForCompanion(null), GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED));
						Main.game.getTextStartStringBuilder().append(getMainCompanion().calculateGenericSexEffects(false, true, null, Subspecies.RAT_MORPH, null, getRandomSexTypeForCompanion(null), GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED));
						break;
						
					case TEASE:
						getOwner().setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_TEASE", getCharacters(true, false)));
						murkSex = Math.random()<0.25f
								&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCompanionFuckedByMurk)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveOwnerCompanionSex);
						break;
						
					case INSPECTION:
						getOwner().setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_INSPECTION", getCharacters(true, false)));
						murkSex = Math.random()<0.25f
								&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCompanionFuckedByMurk)
								&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveOwnerCompanionSex);
						break;
						
					case MURK_SEX:
						getOwner().setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_MURK_SEX", getCharacters(true, false)));
						Main.game.getTextStartStringBuilder().append(getMainCompanion().calculateGenericSexEffects(false, true, getOwner(), getRandomSexTypeForCompanion(getOwner()), GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED));
						break;
				}
				if(murkSex) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_COMPANION_MURK_SEX_AFTER_OTHER", getCharacters(true, false)));
					Main.game.getTextStartStringBuilder().append(getMainCompanion().calculateGenericSexEffects(false, true, getOwner(), getRandomSexTypeForCompanion(getOwner()), GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED));
				}
			}
		}

		getOwner().returnToHome();

		calculatePlayerSexType(true);
		playerInteraction = CaptiveInteractionType.getRandomType(Main.game.getPlayer());
		playerMurkSex = false;
		switch(playerInteraction) {
			case MILKING:
				getOwner().setLocation(Main.game.getPlayer(), false);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_MILKING", getCharacters(true, false)));
				break;
				
			case PUNISHMENT:
				getOwner().setLocation(Main.game.getPlayer(), false);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_PUNISHMENT", getCharacters(true, false)));
				break;
				
			case RAT_SEX:
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_SEX", getCharacters(false, false)));
				spawnRats(1);
				break;
				
			case RAT_SEX_THREESOME:
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_SEX_THREESOME", getCharacters(false, false)));
				spawnRats(2);
				break;
				
			case TEASE:
				calculatePlayerSexType(false);
				getOwner().setLocation(Main.game.getPlayer(), false);
				playerMurkSex = Math.random()<0.25f
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveOwnerSex);
				
				if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_TEASE_VAGINA"+(playerMurkSex?"_SEX":""), getCharacters(true, false)));
				} else if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_TEASE_ANUS"+(playerMurkSex?"_SEX":""), getCharacters(true, false)));
				} else {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_TEASE"+(playerMurkSex?"_SEX":""), getCharacters(true, false)));
				}
				break;
				
			case INSPECTION:
				getOwner().setLocation(Main.game.getPlayer(), false);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_INSPECTION", getCharacters(true, false)));
				playerMurkSex = Math.random()<0.25f
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveOwnerSex);
				break;
				
			case MURK_SEX:
				calculatePlayerSexType(false);
				getOwner().setLocation(Main.game.getPlayer(), false);
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_MURK_SEX", getCharacters(true, false)));
				break;
		}
	}
	
	public static void restoreInventories() {
		int essences = Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE);
		Main.game.getPlayer().setInventory(Main.game.getSavedInventories().get(Main.game.getPlayer().getId()));
		Main.game.getPlayer().setEssenceCount(TFEssence.ARCANE, essences);
		
		if(isCompanionDialogue()) {
			essences = getMainCompanion().getEssenceCount(TFEssence.ARCANE);
			getMainCompanion().setInventory(Main.game.getSavedInventories().get(getMainCompanion().getId()));
			getMainCompanion().setEssenceCount(TFEssence.ARCANE, essences);
		}
	}
	
	public static final DialogueNode STOCKS_INITIAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_INITIAL", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep",
						(isCompanionDialogue()
							?UtilText.parse(getCharacters(true, false), "Completely exhausted, you and [npc.name] find yourselves drifting off to sleep...")
							:"Completely exhausted, you find yourself drifting off to sleep...")
						+ "<br/>[style.italicsTfGeneric(Your 'Forced TF Gender Tendency' content setting determines whether you will be transformed into a feminine or masculine milker during your captivity!)]",
						STOCKS_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut, false);
		}
		@Override
		public int getSecondsPassed() {
			long secondsOfDay = Main.game.getSecondsPassed()%(60*60*24);
			if(secondsOfDay<(60*60*6)) {
				return (int) ((60*60*6) - secondsOfDay);
			}
			return (int) ((60*60*24) - secondsOfDay) + (60*60*6);
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP", getCharacters(true, false)));

			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveTransformationsStarted)) {
				if(playerTf.isFeminine()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_INTRO_FEMININE", getCharacters(true, false)));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_INTRO_MASCULINE", getCharacters(true, false)));
				}
				
			} else {
				if(playerTf!=null) {
					if(Main.game.getPlayer().isAbleToSelfTransform()) { // If possible, they try to make you self-tf:
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_SELF_TF_DEMAND", getCharacters(true, false)));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_POTION", getCharacters(true, false)));
					}
					
				} else {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_MILKING_FIRST_FUCK", getCharacters(true, false)));
						
					} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveMilkingStarted)) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_MILKING_CHECK_FIRST", getCharacters(true, false)));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_MILKING_CHECK", getCharacters(true, false)));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveTransformationsStarted)) {
				if(!Main.getProperties().getForcedTFTendency().isMasculine()) {
					if(index==1) {
						return new Response("[murk.Name]",
								"Look up at Murk, signalling that you'd prefer to be transformed into a [style.colourFeminine(female)] milker.",
								STOCKS_SLEEP_FIRST_DAY) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveTransformationsStarted, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFeminine, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_TF_FEMALE", getCharacters(true, false)));
								if(playerTf==null) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_ALREADY_TF_COMPLETE", getCharacters(true, false)));
								} else {
									if(Main.game.getPlayer().isAbleToSelfTransform()) { // If possible, they try to make you self-tf:
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_SELF_TF_DEMAND", getCharacters(true, false)));
									} else {
										UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_POTION", getCharacters(true, false)));
									}
								}
							}
						};
						
					} else if(index==2) {
						return new Response("Rat-girl",
								"Look up at the rat-girl, signalling that you'd prefer to be transformed into a [style.colourFeminineStrong(futa)] milker.",
								STOCKS_SLEEP_FIRST_DAY) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveTransformationsStarted, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFuta, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_TF_FUTA", getCharacters(true, false)));
								if(playerTf==null) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_ALREADY_TF_COMPLETE", getCharacters(true, false)));
								} else {
									if(Main.game.getPlayer().isAbleToSelfTransform()) { // If possible, they try to make you self-tf:
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_SELF_TF_DEMAND", getCharacters(true, false)));
									} else {
										UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_POTION", getCharacters(true, false)));
									}
								}
							}
						};
					}
					
				} else {
					if(index==1) {
						return new Response("Pull away",
								"Pull away from [murk.namePos] cock, which will make him want to transform you into a [style.colourMasculineStrong(masculine)] cum-milker.",
								STOCKS_SLEEP_FIRST_DAY) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveTransformationsStarted, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveMasculine, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_TF_MASCULINE", getCharacters(true, false)));
								if(playerTf==null) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_ALREADY_TF_COMPLETE", getCharacters(true, false)));
								} else {
									if(Main.game.getPlayer().isAbleToSelfTransform()) { // If possible, they try to make you self-tf:
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_SELF_TF_DEMAND", getCharacters(true, false)));
									} else {
										UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_POTION", getCharacters(true, false)));
									}
								}
							}
						};
						
					} else if(index==2) {
						return new Response("Push back",
								"Raise your hips and push your ass back into [murk.namePos] cock, which will make him want to transform you into an [style.colourAndrogynous(androgynous)] sissy cum-milker.",
								STOCKS_SLEEP_FIRST_DAY) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveTransformationsStarted, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveSissy, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_TF_SISSY", getCharacters(true, false)));
								if(playerTf==null) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_FIRST_DAY_ALREADY_TF_COMPLETE", getCharacters(true, false)));
								} else {
									if(Main.game.getPlayer().isAbleToSelfTransform()) { // If possible, they try to make you self-tf:
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_SELF_TF_DEMAND", getCharacters(true, false)));
									} else {
										UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_SLEEP_POTION", getCharacters(true, false)));
									}
								}
							}
						};
					}
				}
				
			} else {
				if(playerTf!=null) {
					if(Main.game.getPlayer().isAbleToSelfTransform()
							&& playerTf!=CaptiveTransformation.FEMININE_FETISH
							&& playerTf!=CaptiveTransformation.MASCULINE_FETISH) {
						if(index==1) {
							return new Response("Self-transform",
									"As you've been given the self-transformation fetish, you can't help but contain your excitement at the prospect of being allowed to transform yourself...",
									STOCKS_TRANSFORM) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_SELF", getCharacters(true, false)));
									Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
									for(Entry<String, String> entry : effects.entrySet()) {
										Main.game.getTextStartStringBuilder().append(
												"<p>"
													+ UtilText.parse(getOwner(), "[npc.speech("+entry.getKey()+")]")
												+ "</p>"
												+ entry.getValue());
									}
									if(isCompanionDialogue() && CaptiveTransformation.getTransformationType(getMainCompanion())!=null) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_NEXT", getCharacters(true, false)));
									}
								}
							};
						}
						
					} else {
						if(index==1) {
							return new Response("Swallow", "Do as Murk says and swallow the potion...", STOCKS_TRANSFORM) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_SWALLOW", getCharacters(true, false)));
									Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
									for(Entry<String, String> entry : effects.entrySet()) {
										Main.game.getTextStartStringBuilder().append(
												"<p>"
													+ UtilText.parse(getOwner(), "[npc.speech("+entry.getKey()+")]")
												+ "</p>"
												+ entry.getValue());
									}
									if(isCompanionDialogue() && CaptiveTransformation.getTransformationType(getMainCompanion())!=null) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_NEXT", getCharacters(true, false)));
									}
								}
							};
							
						} else if(index==2) {
							if(Main.game.isSpittingDisabled()) {
								return Response.getDisallowedSpittingResponse();
							}
							if(Main.game.getPlayer().getClothingInSlot(InventorySlot.MOUTH)!=null) {
								return new Response("Spit", "Due to the fact that you've been forced to wear a ring gag, you can't do anything to stop Murk from pouring the liquid down your throat...", null);
							}
							if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								return new Response("Spit",
										"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
											+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
										null);
							}
							return new Response("Spit", "Spit out the transformation potion!", STOCKS_TRANSFORM) {
								@Override
								public void effects() {
									equipRingGag(Main.game.getPlayer());
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_REFUSE", getCharacters(true, false)));
									if(isCompanionDialogue() && CaptiveTransformation.getTransformationType(getMainCompanion())!=null) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_NEXT", getCharacters(true, false)));
									}
								}
							};
						}
					}
					
				} else {
					if(index==1) {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)) {
							return new Response("Beg", "Murk has got you so turned on that you can't help but to do exactly as he says and beg for his cock...", STOCKS_FIRST_FUCK) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk, true);

									if(Main.game.getPlayer().hasVagina()) {
										playerFuckedSexType = new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
										if(Main.game.isGapeContentEnabled()) {
											UtilText.addSpecialParsingString(Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE), true);
										}
									} else if(Main.game.isAnalContentEnabled()) {
										playerFuckedSexType = new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
									} else {
										playerFuckedSexType =  new Value<>(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
									}
								}
							};
						}
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveMilkingStarted)) {
							return new Response("Milked", UtilText.parse(getOwner(), "[npc.Name] prepares to turn on your milking machine for the first time..."), STOCKS_TRANSFORM) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_MILKING_FIRST_TIME", getCharacters(true, false)));
									if(isCompanionDialogue() && CaptiveTransformation.getTransformationType(getMainCompanion())!=null) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_NEXT", getCharacters(true, false)));
									}
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveMilkingStarted, true);
								}
							};
						}
						return new Response("Milked", UtilText.parse(getOwner(), "[npc.Name] prepares to turn on your milking machine..."), STOCKS_TRANSFORM) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_MILKING", getCharacters(true, false)));
								if(isCompanionDialogue() && CaptiveTransformation.getTransformationType(getMainCompanion())!=null) {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_NEXT", getCharacters(true, false)));
								}
							}
						};
					}
				}
				
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_SLEEP_FIRST_DAY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STOCKS_SLEEP.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode STOCKS_FIRST_FUCK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_FIRST_FUCK", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						Main.game.getPlayer().hasVagina() || Main.game.isAnalContentEnabled()
							?"Fucked"
							:"Suck cock",
						Main.game.getPlayer().hasVagina() || Main.game.isAnalContentEnabled()
							?"Raise your ass and beg for Murk to start fucking you."
							:"Wrap your lips around Murk's cock and start sucking.",
						true,
						false,
						new SexManagerDefault(
								false,
								SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(
										new Value<>(getOwner(), playerFuckedSexType.getKey())),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								if(Main.sex.getNumberOfOrgasms(partner)==0) {
									return false;
								}
								if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
									return !Capacity.isPenetrationDiameterTooBig(Main.game.getPlayer().getVaginaElasticity(), Main.game.getPlayer().getVaginaStretchedCapacity(), getOwner().getPenisDiameter(), true);
								}
								if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
									return !Capacity.isPenetrationDiameterTooBig(Main.game.getPlayer().getAssElasticity(), Main.game.getPlayer().getAssStretchedCapacity(), getOwner().getPenisDiameter(), true);
								}
								return true;
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.NONE;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								if(character.isPlayer() || character.equals(getMainCompanion())) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return super.getForeplayPreference(character, targetedCharacter);
								}
								return playerFuckedSexType.getValue();
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return super.getMainSexPreference(character, targetedCharacter);
								}
								return playerFuckedSexType.getValue();
							}
							
						},
						null,
						null,
						FIRST_FUCK_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_SEX_START", getCharacters(true, false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FIRST_FUCK_AFTER_SEX = new DialogueNode("Finished", "Murk steps back...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasVagina()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_AFTER_SEX", getCharacters(false, false));

			} else if(Main.game.isAnalContentEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_AFTER_SEX_ANAL", getCharacters(false, false));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_AFTER_SEX_BLOWJOB", getCharacters(false, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasVagina()) {
				if(index==1) {
					return new Response("Pussy",
							"Do as Murk commands and worship his cock, begging for him to fuck your pussy again..."
							+ (Main.game.isAnalContentEnabled()
									?"<br/>[style.italicsSex(Murk will put a sealed butt-plug in your ass, preventing you from receiving anal from random rats in the future...)]"
									:""),
							FIRST_FUCK_AFTER_SEX_NEXT) {
						@Override
						public void effects() {
							playerFuckedSexType =  new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							AbstractClothing buttplug = AbstractClothingType.generateClothing("innoxia_buttPlug_butt_plug_jewel", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, null, false);
							buttplug.setSealed(true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(buttplug, true, getOwner()));
						}
					};
					
				} else if(index==2) {
					if(Main.game.isAnalContentEnabled()) {
						return new Response("Ass",
								"Do as Murk commands and worship his cock, begging for him to fuck your asshole this time around...",
								FIRST_FUCK_AFTER_SEX_NEXT) {
							@Override
							public void effects() {
								playerFuckedSexType =  new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							}
						};
					} else {
						return new Response("Oral",
								"Do as Murk commands and worship his cock, begging for him to use your mouth this time around...",
								FIRST_FUCK_AFTER_SEX_NEXT) {
							@Override
							public void effects() {
								playerFuckedSexType =  new Value<>(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
							}
						};
					}
				}
				
			} else {
				if(index==1) {
					return new Response("Obey",
							"Do as Murk commands and worship his cock, begging for him to fuck you again until he does so...",
							FIRST_FUCK_AFTER_SEX_NEXT) {
						@Override
						public void effects() {
							if(Main.game.isAnalContentEnabled()) {
								playerFuckedSexType = new Value<>(SexSlotMilkingStall.BEHIND_MILKING_STALL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							} else {
								playerFuckedSexType =  new Value<>(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
							}
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode FIRST_FUCK_AFTER_SEX_NEXT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasVagina()) {
				if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_SECOND_SEX_MORE_VAGINAL", getCharacters(false, false));
				} else if(Main.game.isAnalContentEnabled()) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_SECOND_SEX_ANAL", getCharacters(false, false));
				} else {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_SECOND_SEX_ORAL", getCharacters(false, false));
				}

			} else if(Main.game.isAnalContentEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_SECOND_SEX_MORE_ANAL", getCharacters(false, false));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_SECOND_SEX_MORE_BLOWJOB", getCharacters(false, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Submit",
						playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA
							?"Tell Murk that you submit to his cock and beg for him to fuck your pussy."
							:(Main.game.isAnalContentEnabled()
								?"Tell Murk that you submit to his cock and beg for him to fuck your ass."
								:"Tell Murk that you submit to his cock and beg for him to fuck your throat."),
						true,
						false,
						new SexManagerDefault(
								false,
								SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(
										new Value<>(getOwner(), playerFuckedSexType.getKey())),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.NONE;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								if(character.isPlayer() || character.equals(getMainCompanion())) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								if(!performer.isPlayer()
										&& (playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaPenetration.PENIS || playerFuckedSexType.getValue().getPerformingSexArea()==SexAreaOrifice.VAGINA)) {
									return Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA);
								}
								return new ArrayList<>();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return super.getForeplayPreference(character, targetedCharacter);
								}
								return playerFuckedSexType.getValue();
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return super.getMainSexPreference(character, targetedCharacter);
								}
								return playerFuckedSexType.getValue();
							}
							
						},
						null,
						null,
						FIRST_FUCK_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive",
								playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA
									?"FIRST_FUCK_SECOND_SEX_START"
									:(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS
										?"FIRST_FUCK_SECOND_SEX_ANUS_START"
										:"FIRST_FUCK_SECOND_SEX_ORAL_START"),
									getCharacters(true, false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getOwner(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FIRST_FUCK_END = new DialogueNode("Finished", "Murk has finished breaking you in...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_END", getCharacters(true, false));
				
			} else if(playerFuckedSexType.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_END_ANAL", getCharacters(true, false));
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "FIRST_FUCK_END_ORAL", getCharacters(true, false));
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue",
						"You can't do anything about your situation at the moment, other than wait to see what happens to you next...",
						Main.game.isExtendedWorkTime()
							?STOCKS_WAITING
							:STOCKS_NIGHT) {
					@Override
					public void effects() {
						if(Main.game.isExtendedWorkTime()) {
							applyWaitingEffects();
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_FIRST_FUCK_COMPANION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue",
						UtilText.parse(getMainCompanion(), "Although you can't see [npc.name] from your current position, you can hear what Murk is doing to [npc.herHim] next..."),
						STOCKS_FIRST_FUCK_COMPANION_SECOND_FUCK) {
					@Override
					public void effects() {
						SexType companionSexType;
						if(Main.game.isAnalContentEnabled()) {
							if(Main.game.getPlayer().hasVagina()) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_FIRST_FUCK_COMPANION_SECOND_FUCK_ANAL", getCharacters(true, false)));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_FIRST_FUCK_COMPANION_SECOND_FUCK_MORE_ANAL", getCharacters(true, false)));
							}
							companionSexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_FIRST_FUCK_COMPANION_SECOND_FUCK_BLOWJOB", getCharacters(true, false)));
							companionSexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
						}
						
						getMainCompanion().calculateGenericSexEffects(false, true, getOwner(), companionSexType, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STOCKS_FIRST_FUCK_COMPANION_SECOND_FUCK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Finished",
						UtilText.parse(getMainCompanion(), "It sounds like Murk has finished breaking [npc.name] in..."),
						STOCKS_FIRST_FUCK_COMPANION_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_FIRST_FUCK_COMPANION_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_FIRST_FUCK_COMPANION_END", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue",
						"You can't do anything about your situation at the moment, other than wait to see what happens to you next...",
						Main.game.isExtendedWorkTime()
							?STOCKS_WAITING
							:STOCKS_NIGHT) {
					@Override
					public void effects() {
						if(Main.game.isExtendedWorkTime()) {
							applyWaitingEffects();
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_TRANSFORM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveDailyTransformed)
					&& CaptiveTransformation.getTransformationType(getMainCompanion())!=null) {
				if(index==1) {
					return new Response(UtilText.parse(getMainCompanion(), "[npc.Name]"),
							UtilText.parse(getMainCompanion(), "Murk prepares to transform [npc.name]..."),
							STOCKS_TRANSFORM) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_TRANSFORM_COMPANION_TRANSFORM", getCharacters(true, false)));
							Main.game.getTextStartStringBuilder().append(getCompanionTfEffects());
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveDailyTransformed, true);
						}
					};
				}
				
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				// Silence delivers
				if(index==1) {
					return new Response("Birthing", UtilText.parse(getOwner(), "[npc.Name] notices that you're ready to give birth..."), STOCKS_GIVE_BIRTH) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							Main.game.getPlayer().setMana(0);
							
							if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
								Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
								Main.game.getPlayer().incrementVaginaCapacity(
										(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
										false);
							}
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) { // If birthing side quest is not complete, remove it, as otherwise completion (referencing Lily) doesn't make any sense.
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY);
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					if(isCompanionDialogue()
							&& CaptiveTransformation.getTransformationType(getMainCompanion())==null
							&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCompanionFuckedByMurk)) {
						return new Response(UtilText.parse(getMainCompanion(), "Listen"),
								UtilText.parse(getMainCompanion(), "Having finished transforming [npc.herHim], Murk starts breaking in [npc.name], leaving you with little else to do except wait and listen to what's happening to [npc.herHim]..."),
								STOCKS_FIRST_FUCK_COMPANION) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveCompanionFuckedByMurk, true);
	
								SexType companionSexType;
								if(Main.game.getPlayer().hasVagina()) {
									if(Main.game.isGapeContentEnabled()) {
										getMainCompanion().setVaginaCapacity(Main.game.getNpc(Murk.class).getPenisRawSizeValue(), true);
										UtilText.addSpecialParsingString(getMainCompanion().setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE), true);
									}
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_FIRST_FUCK_COMPANION_START", getCharacters(true, false)));
									companionSexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
									
								} else if(Main.game.isAnalContentEnabled()) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_FIRST_FUCK_COMPANION_START_ANAL", getCharacters(true, false)));
									companionSexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
									
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_FIRST_FUCK_COMPANION_START_BLOWJOB", getCharacters(true, false)));
									companionSexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
								}
								
								getMainCompanion().calculateGenericSexEffects(false, true, getOwner(), companionSexType);
							}
						};
						
					} else {
						return new Response("Wait",
								"You can't do anything about your situation at the moment, other than wait to see what happens to you next...",
								Main.game.isExtendedWorkTime()
									?STOCKS_WAITING
									:STOCKS_NIGHT) {
							@Override
							public void effects() {
								if(Main.game.isExtendedWorkTime()) {
									applyWaitingEffects();
								}
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	
	public static final DialogueNode STOCKS_GIVE_BIRTH = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getVaginaType().isEggLayer()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_GIVE_BIRTH_EGGS", getCharacters(true, false));
			} else {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_GIVE_BIRTH", getCharacters(true, false));
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getVaginaType().isEggLayer()) {
					return new Response("Protect the eggs!", "You spend some time recovering from your ordeal...", STOCKS_GIVE_BIRTH_PROTECT_THE_EGGS) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).returnToHome();
						}
					};
				} else {
					return new Response("Rest", "You spend some time recovering from your ordeal...", STOCKS_GIVE_BIRTH_FINISHED) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).returnToHome();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_GIVE_BIRTH_PROTECT_THE_EGGS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 12*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_GIVE_BIRTH_PROTECT_THE_EGGS", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Rest", "You spend some time recovering from your ordeal...", STOCKS_GIVE_BIRTH_FINISHED);
			}
			return null;
		}
	};

	public static final DialogueNode STOCKS_GIVE_BIRTH_FINISHED = new DialogueNode("", "", true) { //TODO append offspring.
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=3) {
				Main.game.getPlayer().setVaginaCapacity(1, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getVaginaType().isEggLayer()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_GIVE_BIRTH_FINISHED_EGGS", getCharacters(true, false));
			} else {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_GIVE_BIRTH_FINISHED", getCharacters(true, false));
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STOCKS_TRANSFORM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode STOCKS_WAITING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			int hours = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuckedByMurk)
					?1+Util.random.nextInt(4) // 1-4 Hours
					:3+Util.random.nextInt(4); // 3-7 hours
			int seconds = (hours)*60*60;
			int progression = seconds + Main.game.getDaySeconds();
			if(progression > 22*60*60) { // Do not pass 22:10:
				seconds = seconds - (progression - 22*60*60);
				seconds += 10*60;
			}
			return seconds; 
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			switch(playerInteraction) {
				case MILKING:
				case PUNISHMENT:
					if(index==1) {
						return new Response("Wait",
								UtilText.parse(getOwner(), "Having had [npc.her] fun, [npc.name] heads back to the milk storage room, leaving you to wait in the stocks..."),
								Main.game.isExtendedWorkTime()
									?STOCKS_WAITING
									:STOCKS_NIGHT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_MURK_LEAVES", getCharacters(true, false)));
								getOwner().returnToHome();
								if(Main.game.isExtendedWorkTime()) {
									applyWaitingEffects();
								}
							}
						};
					}
					break;
					
				case TEASE:
				case INSPECTION: //TODO split with different responses
					if(index==1) {
						return new Response(
								"Continue",
								playerMurkSex
									?"Stay quiet and wait for Murk to leave..."
									:"Murk isn't interested in doing anything else with you right now, and turns around to leave...",
								Main.game.isExtendedWorkTime()
									?STOCKS_WAITING
									:STOCKS_NIGHT) {
							@Override
							public void effects() {
								if(playerMurkSex) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_REFUSE_TO_BEG", getCharacters(true, false)));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_WAITING_MURK_LEAVES", getCharacters(true, false)));
								}
								getOwner().returnToHome();
								if(Main.game.isExtendedWorkTime()) {
									applyWaitingEffects();
								}
							}
						};
						
					} else if(index==2 && playerMurkSex) {
						return getPlayerOwnerSexResponse("Beg for it",
								UtilText.parse(getOwner(), "Now that [npc.name] has turned you on so much, you can't help but beg for [npc.herHim] to fuck you!"),
								STOCKS_AFTER_SEX);
					}
					break;
					
				case RAT_SEX:
					if(index==1) {
						return getPlayerMilkingStallSexResponse("Fucked",
								UtilText.parse(getCharacters(false, false).get(0), "There's nothing you can do to stop the rat from having [npc.her] way with you..."),
								STOCKS_AFTER_SEX, 
								Util.newHashMapOfValues(
										new Value<>(getCharacters(false, false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
								"STOCKS_WAITING_SOLO_SEX");
					}
					break;
					
				case RAT_SEX_THREESOME:
					if(index==1) {
						return getPlayerMilkingStallSexResponse("Fucked",
								UtilText.parse(getCharacters(false, false).get(0), "There's nothing you can do to stop the rats from having their way with you..."),
								STOCKS_AFTER_SEX, 
								Util.newHashMapOfValues(
										new Value<>(getCharacters(false, false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL),
										new Value<>(getCharacters(false, false).get(1), SexSlotMilkingStall.RECEIVING_ORAL)),
								"STOCKS_WAITING_THREESOME_SEX");
					}
					break;
					
				case MURK_SEX:
					if(index==1) {
						return getPlayerOwnerSexResponse("Beg for it",
								UtilText.parse(getOwner(), "Now that [npc.name] has turned you on so much, you can't help but beg for [npc.herHim] to fuck you!"),
								STOCKS_AFTER_SEX);
					}
					break;
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			if(Main.sex.getDominantParticipants(false).containsKey(getOwner())) {
				return UtilText.parse(getOwner(), "[npc.NameHasFull] finished with you for now...");
			}
			if(Main.sex.getDominantParticipants(false).size()>1) {
				return "The rats have finished with you...";
			}
			return UtilText.parse(getCharacters(false, false), "[npc.Name] has finished with you...");
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getDominantParticipants(false).containsKey(getOwner())) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_AFTER_SEX_MURK", getCharacters(false, false));
				
			} else if(Main.sex.getDominantParticipants(false).size()>1) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_AFTER_SEX_THREESOME", getCharacters(false, false));
				
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_AFTER_SEX_SOLO", getCharacters(false, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait",
						"You can't do anything about your situation at the moment, other than wait to see what happens to you next...",
						Main.game.isExtendedWorkTime()
							?STOCKS_WAITING
							:STOCKS_NIGHT) {
					@Override
					public void effects() {
						banishRats();
						if(Main.sex.getDominantParticipants(false).containsKey(getOwner())) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveOwnerSex, true);
						}
						getOwner().returnToHome();
						if(Main.game.isExtendedWorkTime()) {
							applyWaitingEffects();
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STOCKS_NIGHT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_NIGHT", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Fall asleep...", STOCKS_SLEEP);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut)) {
					return new Response("Call out", "Murk won't pay attention to you if you call out again. You'll have to wait until tomorrow night...", null);
				}
				return new Response("Call out", "Call out for Murk...", STOCKS_CALL_OUT);
				
			} else if(index==3) {
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)>=80) {
					return new Response("Break lock", "Use your raw physical power to break free of the lock...", STOCKS_BROKEN_FREE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_NIGHT_BREAK_LOCK", getCharacters(true, false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("Break lock", "You are not strong enough to break the lock...<br/>[style.italicsMinorBad(Required at least 80 "+Attribute.MAJOR_PHYSIQUE.getName()+"...)]", null);
				
			} else if(index==4) {
				if(Main.game.getPlayer().hasSpell(Spell.FIREBALL)
						|| Main.game.getPlayer().hasSpell(Spell.ICE_SHARD)
						|| Main.game.getPlayer().hasSpell(Spell.SLAM)) {
					return new Response("Break lock (Spell)",
							"Spend some time channelling your arcane power in an attempt to overcome your slave collar's enchantment and cast a spell to break the lock on the stocks.",
							STOCKS_BROKEN_FREE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_NIGHT_BREAK_LOCK_SPELL", getCharacters(true, false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("Break lock",
						"You do not know a spell that would be suitable to break the lock..."
								+ "<br/>[style.italicsMinorBad(Requires knowing one of the following spells: "+Spell.FIREBALL.getName()+"; "+Spell.ICE_SHARD.getName()+"; "+Spell.SLAM.getName()+".)]",
						null);
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_BROKEN_FREE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_BROKEN_FREE", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						"Now that you're free of your chain, you can finally attack [murk.name]!",
						(NPC) getOwner(),
						Util.newHashMapOfValues(new Value<>(getOwner(), "[murk.speech(Yer gonna pay fer this!)] [murk.name] shouts as [murk.she] prepares to fight you."))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return getPlayerOwnerEscapeSexResponse(STOCKS_RELEASED_AFTER_SEX, "STOCKS_BROKEN_FREE_SUBMIT_ORAL", "STOCKS_BROKEN_FREE_SUBMIT_SEX");
			}
			return null;
		}
	};

	public static final DialogueNode STOCKS_CALL_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_CALL_OUT", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Feign choking", "Pretend to be choking in order to trick [murk.name] into releasing you...", STOCKS_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_CALL_OUT_CHOKE", getCharacters(true, false)));
//						AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
//						if(collar!=null) {
//							Main.game.getPlayer().forceUnequipClothingIntoVoid(getOwner(), collar);
//						}
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().hasPerkAnywhereInTree(Perk.CONVINCING_REQUESTS)) {
					return new Response("Seduce",
							UtilText.parse(getOwner(),
									"You aren't convincing enough at seduction to attempt to trick [npc.name] into taking your collar off..."
									+ "<br/>[style.italicsMinorBad(Requires the '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' perk.)]"),
							null);
				}
				return new Response("Seduce",
						UtilText.parse(getOwner(),
								"Tell [npc.name] that you're desperate for sex in an attempt to trick [npc.herHim] into taking your collar off..."
								+ "<br/>[style.italicsMinorGood(Unlocked from having the '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"' perk.)]"),
						STOCKS_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_CALL_OUT_SEDUCE", getCharacters(true, false)));
//						AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
//						if(collar!=null) {
//							Main.game.getPlayer().forceUnequipClothingIntoVoid(getOwner(), collar);
//						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_CALL_OUT_RELEASED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight",
						UtilText.parse(getOwner(), "Now that you're free of your collar, you can finally attack [npc.name]!"),
						null,
						(NPC) getOwner(),
						Util.newArrayListOfValues(getOwner()),
						Util.newHashMapOfValues(new Value<>(getOwner(), "[npc.speech(Y-Yer gonna pay fer this!)] [npc.name] shouts in panic, wielding his bat ."))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return getPlayerOwnerEscapeSexResponse(STOCKS_RELEASED_AFTER_SEX, "STOCKS_CALL_OUT_RELEASED_ORAL", "STOCKS_CALL_OUT_RELEASED_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_RELEASED_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_RELEASED_AFTER_SEX", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Locked up", "Let Murk lock you back into the stocks...", STOCKS_CALL_OUT_END_LOCKED_UP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_RELEASED_AFTER_SEX_LOCKED_UP", getCharacters(true, false)));
						equipCollar(Main.game.getPlayer(), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
				
			} else if(index==2) {
				return new Response("Offer company", "Offer to sleep with Murk, which would give you the opportunity to sneak out and escape...", STOCKS_RELEASED_OFFER_COMPANY);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_RELEASED_OFFER_COMPANY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_RELEASED_OFFER_COMPANY", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Slip away", "Use this opportunity to slip away and attempt to escape...", STOCKS_ESCAPING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_RELEASED_OFFER_COMPANY_SLIP_AWAY", getCharacters(true, false)));
						restoreInventories();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Stay", "Stay with Murk until it's time for you to be locked back into the stocks...", STOCKS_CALL_OUT_END_LOCKED_UP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_RELEASED_OFFER_COMPANY_LOCKED_UP", getCharacters(true, false)));
						equipCollar(Main.game.getPlayer(), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_CALL_OUT_END_LOCKED_UP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return STOCKS_SLEEP.getSecondsPassed(); // Advance to morning
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Fall asleep...", STOCKS_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_ESCAPE_FIGHT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_ESCAPE_FIGHT_VICTORY", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Tunnels", "Head back through the tunnels and escape from the Rat Warrens.", STOCKS_ESCAPING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_ESCAPE_FIGHT_VICTORY_ESCAPING", getCharacters(true, false)));
						restoreInventories();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_ESCAPE_FIGHT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_ESCAPE_FIGHT_DEFEAT", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(STOCKS_AFTER_DEFEAT_SEX, "DEFEAT_SEX_ORAL_START", "DEFEAT_SEX_START");
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_AFTER_DEFEAT_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getOwner(), "[npc.NameHasFull] finished with you for now...");
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "STOCKS_AFTER_DEFEAT_SEX", getCharacters(true, false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Completely exhausted from your fight and the subsequent sex, you soon find yourself falling asleep...", STOCKS_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode STOCKS_ESCAPING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Shadow and Silence as they lead you out of the Rat Warrens.", ESCAPING) {
					@Override
					public void effects() {
						RatWarrensCaptiveDialogue.restoreInventories();
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ESCAPING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "ESCAPING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Escape", "Follow Shadow through the tunnels.", RatWarrensDialogue.POST_CAPTIVITY_SWORD_RAID) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
						Main.game.getNpc(Silence.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
					}
				};
			}
			return null;
		}
	};
	
}
