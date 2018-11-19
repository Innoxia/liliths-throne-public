package com.lilithsthrone.game.dialogue.places.submission.impFortress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class ImpCitadelDialogue {

	private static Value<String, AbstractItem> guardsPotion = null;
	private static Value<String, AbstractItem> guardsCompanionPotion = null;
	
	private static boolean isGuardsDefeated() {
		return getImpGuards().isEmpty();
	}
	
	private static boolean isGuardsPacified() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonGuardsPacified);
	}
	
	private static boolean isGuardsPacifiedByTF() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressTransformedByGuards);
	}
	
	private static boolean isDefeated() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated);
	}
	
	private static boolean isBossEncountered() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonBossEncountered);
	}
	
	public static void clearFortress() {
		
		banishImpGuards();
		
		for(GameCharacter character : getImpBossGroup()) {
			if(!character.equals(getBoss())) {
				Main.game.banishNPC(character.getId());
			}
		}
		getBoss().setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		
		Main.game.getDialogueFlags().impFortressDemonDefeatedTime = Main.game.getMinutesPassed();
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonDefeated, true);

		// Move NPCs out of hiding:
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
			if(character.getHomeLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_DEMON) {
				character.returnToHome();
			}
		}
	}
	
	public static void resetFortress() {
		// Make sure everything is reset:
		clearFortress();
		resetGuards();

		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonDefeated, false);
		
		List<GameCharacter> impGroup = new ArrayList<>();
		try {
			// Boss guards:
			
			impGroup = new ArrayList<>();
			List<String> impAdjectives = new ArrayList<>();
			
			ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
			impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
			imp.setLevel(15);
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
			imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
			impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
			imp.setLevel(14);
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
			imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
			impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
			imp.setLevel(13);
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
			imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
			imp.setGenericName("alpha-imp archer");
			imp.setLevel(12);
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_BOW_AND_ARROW, Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_V_B_FEMALE, false);
			imp.setGenericName("alpha-imp witch");
			imp.setAttribute(Attribute.MAJOR_ARCANE, 50);
			imp.addSpell(Spell.ARCANE_AROUSAL);
			imp.addSpell(Spell.FIREBALL);
			imp.addSpell(Spell.ICE_SHARD);
			imp.addSpell(Spell.TELEKENETIC_SHOWER);
			imp.setLevel(11);
			Main.game.addNPC(imp, false);
			impGroup.add(imp);

			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
			imp.setGenericName("alpha-imp wizard");
			imp.setAttribute(Attribute.MAJOR_ARCANE, 50);
			imp.addSpell(Spell.ARCANE_AROUSAL);
			imp.addSpell(Spell.FIREBALL);
			imp.addSpell(Spell.ICE_SHARD);
			imp.addSpell(Spell.TELEKENETIC_SHOWER);
			imp.setLevel(10);
			Main.game.addNPC(imp, false);
			impGroup.add(imp);
			
			for(GameCharacter impCharacter : impGroup) {
				impCharacter.setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
				((NPC)impCharacter).equipClothing(true, true, true, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Move boss back to fortress:
		Main.game.getFortressDemonLeader().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		
		// Move NPCs into hiding:
		Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
		for(int i=0; i< cells.length;i++) {
			for(int j=0; j< cells[i].length;j++) {
				Cell cell = cells[j][i];
				if(cell.getPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_DEMON) {
					for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
						if(!Main.game.getPlayer().getCompanions().contains(character)) {
							character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
							character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						}
					}
				}
			}
		}
	}
	
	public static List<GameCharacter> getImpBossGroup() {
		List<GameCharacter> bossGroup = new ArrayList<>();
		
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP)) {
			if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
				bossGroup.add(character);
			}
		}
		
		bossGroup.sort((imp1, imp2) -> imp2.getLevel()-imp1.getLevel());
		return bossGroup;
	}

	public static List<GameCharacter> getImpGuards() {
		
		List<GameCharacter> impGuards = new ArrayList<>();

		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE)) {
			if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
				impGuards.add(character);
			}
		}
		
		impGuards.sort((imp1, imp2) -> imp2.getLevel()-imp1.getLevel());
		return impGuards;
	}

	public static GameCharacter getBoss() {
		return Main.game.getFortressDemonLeader();
	}

	public static ImpAttacker getImpGuardLeader() {
		return (ImpAttacker) getImpGuards().get(0);
	}

	public static void banishImpGuards() {
		for(GameCharacter imp : getImpGuards()) {
			if(!imp.isSlave() && imp.getPartyLeader()==null) {
				Main.game.banishNPC(imp.getId());
			}
		}
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		// There's a reason I can't just add all from getCharactersPresent(), but I forgot. Maybe it was because the Elemental companion gets added?
		List<GameCharacter> allCharacters = new ArrayList<>();
		
		if(isCompanionDialogue()) {
			allCharacters.add(getMainCompanion());
		}
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_DEMON_ENTRANCE) {
			allCharacters.addAll(getImpGuards());
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.FORTRESS_DEMON_KEEP) {
			allCharacters.addAll(getImpBossGroup());
		}

		Collections.sort(allCharacters, (c1, c2) -> c1 instanceof Elemental?(c2 instanceof Elemental?0:1):(c2 instanceof Elemental?-1:0));
		return allCharacters;
	}
	
	private static void resetGuards() {
		List<GameCharacter> impGroup = new ArrayList<>();
		List<String> impAdjectives = new ArrayList<>();
		try {
			ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
			imp.setGenericName("alpha-imp leader");
			imp.setLevel(14);
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
			impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
			imp.setLevel(12);
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
			imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
			imp.setGenericName("alpha-imp archer");
			imp.setLevel(11);
			Main.game.addNPC(imp, false);
			imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_BOW_AND_ARROW, Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
			impGroup.add(imp);
			
			imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
			imp.setGenericName("alpha-imp arcanist");
			imp.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
			imp.setAttribute(Attribute.MAJOR_ARCANE, 50);
			imp.addSpell(Spell.ARCANE_AROUSAL);
			imp.addSpell(Spell.FIREBALL);
			imp.addSpell(Spell.ICE_SHARD);
			imp.addSpell(Spell.TELEKENETIC_SHOWER);
			imp.setLevel(8);
			Main.game.addNPC(imp, false);
			impGroup.add(imp);
			
			for(GameCharacter impCharacter : impGroup) {
				impCharacter.setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
				((NPC)impCharacter).equipClothing(true, true, true, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getDialogueEncounterId() {
		StringBuilder idSB = new StringBuilder();
		idSB.append("Demon");
			
		if(isCompanionDialogue()) {
			idSB.append("Companions");
		}
		return idSB.toString();
	}
	
	// Dialogues: TODO
	
	public static final DialogueNodeOld ENTRANCE = new DialogueNodeOld("Gateway", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isTravelDisabled() {
			return !isGuardsPacified() && !isGuardsDefeated();
		}
		
		@Override
		public int getMinutesPassed() {
			return 1;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isGuardsPacifiedByTF()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_PACIFIED_BY_TF", getAllCharacters()));
				
			} else if(isGuardsPacified()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_PACIFIED", getAllCharacters()));
				
			} else if(isGuardsDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_DESERTED", getAllCharacters()));
				if(!isDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_DESERTED_GUARD_RETURN_WARNING", getAllCharacters()));
				}
				
			} else if(Main.game.getPlayer().getSubspecies()==Subspecies.DEMON) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_DEMON", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isGuardsPacified() || isGuardsDefeated()) {
				if (index == 1) {
					return new Response("Leave", "Head back out into the tunnels.", PlaceType.SUBMISSION_IMP_FORTRESS_DEMON.getDialogue(false)) {
						@Override
						public void effects() {
							if(isGuardsDefeated()) {
								resetGuards();
							}
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "LEAVE_FORTRESS", getAllCharacters()));
						}
					};
	
				} else if(!isGuardsDefeated()) {
					if(index==2) {
						return new ResponseSex(isCompanionDialogue()?"Offer sex (solo)":"Offer sex",
								isGuardsPacifiedByTF()
									?"Agree with the imp's taunts, and offer to let them 'try you out' again."
									:"Offer to have sex with the imps.",
								true,
								false,
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
										isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
								GUARDS_AFTER_SEX_CONSENSUAL, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_GIVE_SEX_PACIFIED", getAllCharacters())) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonGuardsPacified, true);
							}
						};
						
					} else if(index==3 && isCompanionDialogue()) {
						if(!getMainCompanion().isAttractedTo(getImpGuardLeader()) && !getMainCompanion().isSlave() && !(getMainCompanion() instanceof Elemental)) {
							return new Response("Offer sex (both)",
									UtilText.parse(getMainCompanion(), "[npc.Name] is not interested in performing oral sex on the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex("Offer sex (both)",
									UtilText.parse(getMainCompanion(), "Tell the imps that both you and [npc.name] want to have sex with them."),
									true,
									false,
									getImpGuards(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									GUARDS_AFTER_SEX_CONSENSUAL_WITH_COMPANION, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_GIVE_SEX_WITH_COMPANION_PACIFIED", getAllCharacters())) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonGuardsPacified, true);
								}
							};
						}
						
					}
					if(isCompanionDialogue()?index==4:index==3) {
						return new ResponseCombat("Attack", "Change your mind about not wanting to fight the imps, and decide to teach them a lesson!", getImpGuards(), null);
					}
				}
				return null;
				
			} else {
				if (index == 1) {
					if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)) {
						return new Response("Chuuni",
								"Tell these insolent imps who they're dealing with!",
								ENTRANCE_CHUUNI_COMMAND) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonGuardsPacified, true);
							}
						};
					} else {
						return new Response("Chuuni",
								"If you were a shameless chuuni, perhaps you'd be able to bluff your way past the imps. As you're not, however, you'll need to decide upon another course of action...",
								null);
					}
	
				} else if(index==2) {
					return new ResponseCombat("Attack", "Defend yourself against the imps!", getImpGuards(), null);
					
				} else if(index==3) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response(isCompanionDialogue()?"Accept TF (solo)":"Accept TF",
								"As your mouth is blocked, you can't accept the TF potion in exchange for them letting you into the fortress!",
								null);
						
					} else {
						return new Response(isCompanionDialogue()?"Accept TF (solo)":"Accept TF",
								"Agree to perform oral sex on the imps in exchange for them letting you into the fortress.",
								GUARDS_TF_FOR_ENTRY) {
							@Override
							public void effects() {
								guardsPotion = getImpGuardLeader().getTransformativePotion(Main.game.getPlayer(), true);
								
								for(GameCharacter imp : getImpGuards()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
									if(Main.game.getPlayer().hasBreasts()) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
									}
								}

								Main.game.getTextStartStringBuilder().append(
										// TODO TF offer is to wait for the arcanist to brew it - takes 15 minutes
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_TF_ACCEPTED", getAllCharacters())
										+ "<p>"
											+ getImpGuardLeader().useItem(guardsPotion.getValue(), Main.game.getPlayer(), false, true)
										+"</p>");
								
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressTransformedByGuards, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonGuardsPacified, true);
							}
						};
					}
					
				} else if(index==4 && isCompanionDialogue()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Accept TF (both)",
								"As your mouth is blocked, you can't accept the TF potion in exchange for being let into the fortress!",
								null);
						
					} else if(!getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING) && !getMainCompanion().isSlave() && !(getMainCompanion() instanceof Elemental)) {
						return new Response("Accept TF (both)",
								UtilText.parse(getMainCompanion(), "[npc.Name] is not interested in getting transformed by the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Offer companion",
								UtilText.parse(getMainCompanion(), "As [npc.namePos] mouth is blocked, [npc.she] can't accept the TF potion in exchange for the two of you being let into the fortress!"),
								null);
						
					} else {
						return new Response("Accept TF (both)",
								UtilText.parse(getMainCompanion(), "Tell the imps that both you and [npc.name] will drink their experimental potion."),
								GUARDS_TF_FOR_ENTRY) {
							@Override
							public void effects() {
								if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
									guardsPotion = getImpGuardLeader().getTransformativePotion(Main.game.getPlayer(), true);
								}
								if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
									guardsCompanionPotion = getImpGuardLeader().getTransformativePotion(getMainCompanion(), true);
								}
								
								for(GameCharacter imp : getImpGuards()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
									if(Main.game.getPlayer().hasBreasts()) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
									}
								}
								for(GameCharacter imp : getImpGuards()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(getMainCompanion().hasBreasts()) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}

								Main.game.getTextStartStringBuilder().append(
										// TODO TF offer is to wait for the arcanist to brew it - takes 15 minutes
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_TF_ACCEPTED", getAllCharacters())
										+ "<p>"
											+ getImpGuardLeader().useItem(guardsPotion.getValue(), Main.game.getPlayer(), false, true)
										+ "</p>"
										+ UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_TF_ACCEPTED_WITH_COMPANION", getAllCharacters())
										+ "<p>"
											+ getImpGuardLeader().useItem(guardsCompanionPotion.getValue(), getMainCompanion(), false, true)
										+"</p>");
									
								
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressTransformedByGuards, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonGuardsPacified, true);
							}
						};
					}
					
				} else if(index==5 && isCompanionDialogue()) {
					if(!getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING) && !getMainCompanion().isSlave() && !(getMainCompanion() instanceof Elemental)) {
						return new Response("Offer companion",
								UtilText.parse(getMainCompanion(), "[npc.Name] is not interested in getting transformed by the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
						
					} else if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Offer companion",
								UtilText.parse(getMainCompanion(), "As [npc.namePos] mouth is blocked, [npc.she] can't drink the TF potion in exchange for the two of you being let into the fortress!"),
								null);
						
					} else {
						return new Response("Offer companion",
								UtilText.parse(getMainCompanion(), "Tell the imps that [npc.name] will drink their experimental potion in exchange for the two of you being let into the fortress."),
								GUARDS_TF_FOR_ENTRY) {
							@Override
							public void effects() {
								guardsCompanionPotion = getImpGuardLeader().getTransformativePotion(getMainCompanion(), true);
								
								for(GameCharacter imp : getImpGuards()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(getMainCompanion().hasBreasts()) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}

								Main.game.getTextStartStringBuilder().append(
										// TODO TF offer is to wait for the arcanist to brew it - takes 15 minutes
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_TF_ACCEPTED_FOR_COMPANION", getAllCharacters())
										+ "<p>"
											+ getImpGuardLeader().useItem(guardsCompanionPotion.getValue(), getMainCompanion(), false, true)
										+"</p>");
								
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressTransformedByGuards, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonGuardsPacified, true);
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld GUARDS_TF_FOR_ENTRY = new DialogueNodeOld("Finished", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_SEX_CONSENSUAL = new DialogueNodeOld("Finished", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_SEX_CONSENSUAL", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNodeOld GUARDS_AFTER_SEX_CONSENSUAL_WITH_COMPANION = new DialogueNodeOld("Finished", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_SEX_CONSENSUAL_WITH_COMPANION", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ENTRANCE_CHUUNI_COMMAND = new DialogueNodeOld("", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_CHUUNI_COMMAND", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated the imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Standard";
				
			} else if(index==1) {
				return "Inventories";
				
			} else if(index==2) {
				return "Transformations";
				
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Scare off", "Tell the imps to get out of here while they still can...", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								banishImpGuards();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Sex",
								"Well, they <i>are</i> asking for it!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								"Well, they <i>are</i> asking for it! (Start the sex scene in the 'rough' pace.)",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Submit",
								"You're not really sure what to do now... Perhaps it would be best to let the imps choose what to do next...",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGuards(),
								Main.game.getPlayer().getParty(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {

				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Scare off", "Tell the imps to get out of here while they still can...", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								banishImpGuards();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Solo sex",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps. (Start sex in the gentle pace.)"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Solo sex (Rough)",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you have sex with the imps. (Start sex in the rough pace.)"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Solo submission",
								UtilText.parse(getMainCompanion(), "Tell [npc.name] to stand to one side and watch as you submit to the imps, allowing them to have dominant sex with you."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group sex",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									UtilText.parse(companion, "Have dominant sex with the imps, and get [npc.name] to join in with the fun."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpGuards(),
									null,
									null,
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "GUARDS_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group submission",
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group submission"),
									UtilText.parse(companion, "Get [npc.name] to join you in submitting to the imps, allowing them to have dominant sex with the two of you."),
									true,
									false,
									getImpGuards(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "GUARDS_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "[npc.Name] is not interested in having sex with the imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, "Tell [npc.name] that [npc.she] can have some fun with the imps while you watch."),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGuards(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "GUARDS_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpGuardLeader()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with the imps, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do so..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, "Hand [npc.name] over to the imps, and watch as they have sex with [npc.herHim]."),
									true,
									false,
									getImpGuards(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "GUARDS_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpGuardLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			}
		}
	};

	public static final DialogueNodeOld GUARDS_AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription() {
			return "You have been defeated by the imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"Allow the imps to move you into position...",
						false,
						false,
						getImpGuards(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						GUARDS_AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"Eagerly allow yourself to be moved into position by the gang of imps...",
						false,
						false,
						getImpGuards(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"Try to resist as the gang of imps move you into position...",
						false,
						false,
						getImpGuards(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						GUARDS_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_RESIST);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the imps to recover and disperse.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "GUARDS_AFTER_SEX_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return GUARDS_AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							banishImpGuards();
						}
					};
				}
				
			} else if(responseTab==1) {
				return GUARDS_AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld GUARDS_AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			if(Sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld COURTYARD = new DialogueNodeOld("Courtyard", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "COURTYARD", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld WELL = new DialogueNodeOld("Well", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "WELL", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO escape method
			return null;
		}
	};

	public static final DialogueNodeOld CELLS = new DialogueNodeOld("Cells", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "CELLS", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld LABORATORY = new DialogueNodeOld("Laboratory", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "LABORATORY", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld TREASURY = new DialogueNodeOld("Treasury", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "TREASURY", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld TREASURY_DOOR = new DialogueNodeOld("Treasury Door", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "TREASURY_DOOR", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld TREASURY_CLOTHES = new DialogueNodeOld("Treasury", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "TREASURY_CLOTHES", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNodeOld KEEP = new DialogueNodeOld("Keep", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_DEFEATED", getAllCharacters()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(isDefeated()) {
					return new Response("Enter", "The keep is deserted, and there's nothing of value inside...", null);
				} else {
					return new Response("Enter", "Push open the doors of the keep and step inside.", KEEP_ENTRY) {
						@Override
						public void effects() {
							getBoss().setPlayerKnowsName(true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNodeOld KEEP_THRONE_ROOM = new DialogueNodeOld("Throne Room", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_THRONE_ROOM", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld KEEP_ENTRY = new DialogueNodeOld("Keep", ".", true) { //TODO from here
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isBossEncountered()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_RETURN", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)) {
					return new Response("Challenge", UtilText.parse(getBoss(), "Challenge [npc.name] to a duel between the two greatest arcane-users in the world!"), KEEP_CHALLENGE);
				} else {
					return new Response("Challenge", "You can't bring yourself to engage in the same level of dialogue as this embarrassing succubus. Perhaps if you were a chuuni as well, things would be different...", null);
				}
				
			} else if(index==2) {
				return new ResponseCombat("Fight imps", UtilText.parse(getBoss(), "Defend yourself against [npc.namePos] minions!"), getImpBossGroup(), null);
				
			} else if(index==3) {
				return new ResponseSex(isCompanionDialogue()?"Surrender (solo)":"Surrender",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), 
									"Tell [npc.name] to stand back as you surrender your body to [npc2.name] and [npc2.her] imps in exchange for being allowed to leave without a fight.")
							:UtilText.parse(getBoss(), "Surrender your body to [npc.name] and [npc.her] imps in exchange for being allowed to leave without a fight."),
						true,
						false,
						getImpBossGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						KEEP_AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_OFFER_ORAL", getAllCharacters()));
				
			} else if(index==4 && isCompanionDialogue()) {
				return new ResponseSex("Surrender (both)",
						UtilText.parse(getMainCompanion(), getBoss(), "Surrender both yourself and [npc.name] to [npc2.name], allowing [npc2.herHim] and [npc.2her] imps to do what they please with you."),
						true,
						false,
						getImpBossGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						KEEP_AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "ENTRANCE_OFFER_ORAL_WITH_COMPANION", getAllCharacters()));
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld KEEP_CHALLENGE = new DialogueNodeOld("Keep", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_CHALLENGE", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) { //TODO and have ring
				return new Response("Show ring", UtilText.parse(getBoss(), "Show [npc.name] that you have [npc.her] mother's ring, and with its power, you will easily be able to defeat [npc.herHim]!"), KEEP_CHALLENGE_RING);

			} else if(index==2) {
				return new ResponseCombat("Duel", UtilText.parse(getBoss(), "Accept [npc.namePos] offer, and fight [npc.herHim] one-on-one!"), getImpBossGroup(), null);
				
			}
			return null;
		}
	};

	public static final DialogueNodeOld KEEP_CHALLENGE_RING = new DialogueNodeOld("Keep", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Give ring", UtilText.parse(getBoss(), "Agree to give [npc.name] [npc.her] mother's ring, in exchange for having [npc.herHim] agree to duel you one-on-one."
						+ " <i style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>You can tell that [npc.sheWill] instantly put it on, thus enslaving [npc.herHim] without having to fight!</i>"), KEEP_CHALLENGE_RING_GIVEN);

			} else if(index==2) {
				return new ResponseCombat("Fight", UtilText.parse(getBoss(), "Defend yourself against [npc.name] and [npc.her] minions!"), getImpBossGroup(), null);
				
			}
			return null;
		}
	};

	public static final DialogueNodeOld KEEP_CHALLENGE_RING_GIVEN = new DialogueNodeOld("Keep", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Give ring", UtilText.parse(getBoss(), "Agree to give [npc.name] [npc.her] mother's ring, in exchange for having [npc.herHim] agree to duel you one-on-one."
						+ " <i style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>You can tell that [npc.sheWill] instantly put it on, thus enslaving [npc.herHim] without having to fight!</i>"), KEEP_CHALLENGE_RING);

			} else if(index==2) {
				return new ResponseCombat("Fight", UtilText.parse(getBoss(), "Defend yourself against [npc.name] and [npc.her] minions!"), getImpBossGroup(), null);
				
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld KEEP_AFTER_COMBAT_VICTORY = new DialogueNodeOld("Keep", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "You have defeated [npc.name] and [npc.her] imps!");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Standard";
				
			} else if(index==1) {
				return "Inventories";
				
			} else if(index==2) {
				return "Transformations";
				
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Scare off", UtilText.parse(getBoss(), "Tell [npc.name] and [npc.her] gang to get out of here, before you change your mind..."), Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
								clearFortress();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang."),
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpBossGroup(),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Gentle Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang. (Start the sex scene in the 'gentle' pace.)"),
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpBossGroup(),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Rough Sex",
								UtilText.parse(getBoss(), "Now that they've been defeated, there's nothing stopping you from having sex with [npc.name] and [npc.her] imp gang. (Start the sex scene in the 'rough' pace.)"),
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpBossGroup(),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Submit",
								UtilText.parse(getBoss(), "Feeling sorry for [npc.name] and [npc.her] imps, you decide to let them have some fun with your body before you force them to flee their fortress..."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpBossGroup(),
								Main.game.getPlayer().getParty(),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpBossGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpBossGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Scare off",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc2.name] and [npc2.her] gang to get out of here, before you and [npc.name] change your minds..."), Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
								clearFortress();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Solo sex",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "KEEP_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("Solo sex (Gentle)",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang. (Start sex in the 'gentle' pace.)"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "KEEP_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Solo sex (Rough)",
								UtilText.parse(getMainCompanion(), getBoss(), "Tell [npc.name] to stand to one side and watch as you have sex with [npc2.name] and [npc2.her] imp gang. (Start sex in the 'rough' pace.)"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "KEEP_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Solo submission",
								UtilText.parse(getMainCompanion(), getBoss(),
										"Tell [npc.name] to stand to one side, and then let [npc2.name] and [npc2.her] gang have some fun with your body, before forcing them to flee their fortress..."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpBossGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "KEEP_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group sex",
									UtilText.parse(companion, getBoss(),
											"[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group sex"),
									UtilText.parse(companion, getBoss(), "Have dominant sex with [npc2.name] and [npc2.her] imps, and get [npc.name] to join in with the fun."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpBossGroup(),
									null,
									null,
									KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "KEEP_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response("Group submission",
									UtilText.parse(companion, getBoss(), "[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Group submission"),
									UtilText.parse(companion, getBoss(), "Get [npc.name] to join you in submitting to [npc2.name] and [npc2.her] imps, allowing them to have dominant sex with the two of you."),
									true,
									false,
									getImpBossGroup(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, getBoss(), "[npc.Name] is not interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not a slave, you can't force [npc.herHim] to do so..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
									UtilText.parse(companion, getBoss(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] and [npc2.her] imps while you watch."),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpBossGroup(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "KEEP_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getBoss()) && !companion.isSlave() && !(companion instanceof Elemental)) {
							return new Response(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, getBoss(),
											"You can tell that [npc.name] isn't at all interested in having sex with [npc2.name] and [npc2.her] imps, and as [npc.sheIs] not your slave, you can't force [npc.herHim] to do so..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
									UtilText.parse(companion, getBoss(), "Hand [npc.name] over to [npc2.name] and [npc2.her] imps, and watch as they have sex with [npc.herHim], before making them flee their fortress."),
									true,
									false,
									getImpBossGroup(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressDemonCompanions", "KEEP_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getBoss()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpBossGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpBossGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			}
		}
	};

	public static final DialogueNodeOld KEEP_AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Keep", ".", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc2.name] and [npc2.her] imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Allow [npc2.name] and [npc2.her] imps to move you and [npc.name] into position...")
							:UtilText.parse(getBoss(), "Allow [npc.name] and [npc.her] imps to move you and [npc.name] into position..."),
						false,
						false,
						getImpBossGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						KEEP_AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Eagerly help [npc2.name] and [npc2.her] imps to move you and [npc.name] into position...")
							:UtilText.parse(getBoss(), "Eagerly help [npc.name] and [npc.her] imps to move you into position..."),
						false,
						false,
						getImpBossGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "Try to resist as [npc2.name] and [npc2.her] imps to move you and [npc.name] into position...")
							:UtilText.parse(getBoss(), "Try to resist as [npc.name] and [npc.her] imps start to move you into position..."),
						false,
						false,
						getImpBossGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_RESIST);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld KEEP_AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "Now that you've had your fun, you can step back and leave [npc.name] and [npc.her] imps to flee their fortress and disperse into the tunnels of Submission.");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return KEEP_AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							clearFortress();
						}
					};
				}
				
			} else if(responseTab==1) {
				return KEEP_AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld KEEP_AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.");
		}

		@Override
		public String getContent() {
			if(Sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						if(isGuardsDefeated()) {
							resetGuards();
						}
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
