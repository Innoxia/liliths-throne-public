package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.Litter;
import com.lilithsthrone.game.character.PregnancyPossibility;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.npcDialogue.elemental.ElementalDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaBirthing;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.universal.SMMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia, tukaima
 */
public class PhoneDialogue {
	
	private static class offspringTableLineSubject {
		boolean female;
		String child_name;
		String race_color;
		String species_name;
		String mother;
		String father;
		String incubator;
		List<String> relationships;

		offspringTableLineSubject(NPC npc) {
			this.female = npc.isFeminine();
			this.child_name = npc.getName(true);
			this.race_color = npc.getRace().getColour().toWebHexString();
			this.species_name = this.female
					? Util.capitaliseSentence(npc.getSubspecies().getSingularFemaleName(npc.getBody()))
					: Util.capitaliseSentence(npc.getSubspecies().getSingularMaleName(npc.getBody()));
			this.mother = (npc.getMother() == null ? "???" : (npc.getMother().isPlayer() ? "[style.colourExcellent(You)]" : Util.capitaliseSentence(npc.getMother().getName(true))));
			if(npc.getMother()==null && !npc.getMotherName().equals("???")) {
				mother = npc.getMotherName();
			}

			this.father = (npc.getFather() == null ? "???" : (npc.getFather().isPlayer() ? "[style.colourExcellent(You)]" : Util.capitaliseSentence(npc.getFather().getName(true))));
			if(npc.getFather()==null && !npc.getFatherName().equals("???")) {
				father = npc.getFatherName();
			}

			this.incubator = (npc.getIncubator() == null ? "[style.colourDisabled(n/a)]" : (npc.getIncubator().isPlayer() ? "[style.colourExcellent(You)]" : Util.capitaliseSentence(npc.getIncubator().getName(true))));
			if(npc.getIncubator()==null && !npc.getIncubatorName().equals("???")) {
				incubator = npc.getIncubatorName();
			}

			Set<Relationship> extraRelationships = Main.game.getPlayer().getRelationshipsTo(npc, Relationship.Parent);
			this.relationships = extraRelationships.stream().map((relationship) -> relationship.getName(Main.game.getPlayer())).collect(Collectors.toList());
			if(npc.getIncubator()!=null && npc.getIncubator().isPlayer()) {
				this.relationships.add(0, "Incubator-mother");

				if(npc.getFather()!=null && npc.getFather().isPlayer()) {
					this.relationships.add(1, "father");
				}

			} else if(npc.getMother()!=null && npc.getMother().isPlayer()) {
				this.relationships.add(0, "Mother");

				if(npc.getFather()!=null && npc.getFather().isPlayer()) {
					this.relationships.add(1, "father");
				}

			} else {
				this.relationships.add(0, "Father");
			}
		}

		offspringTableLineSubject(OffspringSeed os) {
			this.female = os.isFeminine();
			this.child_name = "Unknown";
			this.race_color = os.getRace().getColour().toWebHexString();
			this.species_name = this.female
					? Util.capitaliseSentence(os.getSubspecies().getSingularFemaleName(os.getBody()))
					: Util.capitaliseSentence(os.getSubspecies().getSingularMaleName(os.getBody()));
			this.mother = (os.getMother() == null ? "???" : (os.getMother().isPlayer() ? "[style.colourExcellent(You)]" : Util.capitaliseSentence(os.getMother().getName(true))));
			if(os.getMother()==null && !os.getMotherName().equals("???")) {
				mother = os.getMotherName();
			}

			this.father = (os.getFather() == null ? "???" : (os.getFather().isPlayer() ? "[style.colourExcellent(You)]" : Util.capitaliseSentence(os.getFather().getName(true))));
			if(os.getFather()==null && !os.getFatherName().equals("???")) {
				father = os.getFatherName();
			}

			this.incubator = (os.getIncubator() == null ? "[style.colourDisabled(n/a)]" : (os.getIncubator().isPlayer() ? "[style.colourExcellent(You)]" : Util.capitaliseSentence(os.getIncubator().getName(true))));
			if(os.getIncubator()==null && !os.getIncubatorName().equals("???")) {
				incubator = os.getIncubatorName();
			}
			this.relationships = new ArrayList<>();
//			Set<Relationship> extraRelationships = Main.game.getPlayer().getRelationshipsTo(os, Relationship.Parent);
//			this.relationships = extraRelationships.stream().map((relationship) -> relationship.getName(Main.game.getPlayer())).collect(Collectors.toList());
			if(os.getIncubator()!=null && os.getIncubator().isPlayer()) {
				this.relationships.add(0, "Incubator-mother");

				if(os.getFather()!=null && os.getFather().isPlayer()) {
					this.relationships.add(1, "father");
				}

			} else if(os.getMother()!=null && os.getMother().isPlayer()) {
				this.relationships.add(0, "Mother");

				if(os.getFather()!=null && os.getFather().isPlayer()) {
					this.relationships.add(1, "father");
				}

			} else {
				this.relationships.add(0, "Father");
			}

		}
	}

	private static List<GameCharacter> charactersEncountered;
	private static StringBuilder journalSB;
	private static SexAreaOrifice layingEggsArea;
	private static Set<String> incubationOffspringBirthed;
	
	private static void applyEggLayingEffects() {
		incubationOffspringBirthed = new HashSet<>();
		incubationOffspringBirthed.addAll(Main.game.getPlayer().getIncubationLitter(layingEggsArea).getOffspring());
		Main.game.getPlayer().endIncubationPregnancy(layingEggsArea, true);
		switch(layingEggsArea) {
			case ANUS:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementAssStretchedCapacity(15);
					Main.game.getPlayer().incrementAssCapacity(
							(Main.game.getPlayer().getAssStretchedCapacity()-Main.game.getPlayer().getAssRawCapacityValue())*Main.game.getPlayer().getAssPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case NIPPLE:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementNippleStretchedCapacity(15);
					Main.game.getPlayer().incrementNippleCapacity(
							(Main.game.getPlayer().getNippleStretchedCapacity()-Main.game.getPlayer().getNippleRawCapacityValue())*Main.game.getPlayer().getNipplePlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case NIPPLE_CROTCH:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementNippleCrotchStretchedCapacity(15);
					Main.game.getPlayer().incrementNippleCrotchCapacity(
							(Main.game.getPlayer().getNippleCrotchStretchedCapacity()-Main.game.getPlayer().getNippleCrotchRawCapacityValue())*Main.game.getPlayer().getNippleCrotchPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			case VAGINA:
				if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
					Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
					Main.game.getPlayer().incrementVaginaCapacity(
							(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
							false);
				}
				break;
			default:
				break;
		}
		Main.game.getPlayer().setMana(0);
	}
	
	public static final DialogueNode MENU = new DialogueNode("Phone home screen", "Phone", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
//			if(Main.game.isInGlobalMap()) {
//				UtilText.nodeContentSB.append(RenderingEngine.ENGINE.getFullWorldMap());
//			} else {
//				UtilText.nodeContentSB.append(RenderingEngine.ENGINE.getFullMap(Main.game.getPlayer().getWorldLocation(), true));
//			}
			
			UtilText.nodeContentSB.append("<p>You pull out your phone and tap in the unlock code.</p>");
			
			if(Main.game.isInNewWorld()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+"Using your powerful aura, you've managed to figure out a way to channel the arcane into charging the battery of your phone, although considering that it's the only one in this world, it's not much use for calling anyone."
							+ " Instead, you're using it as a way to store information about things you've discovered in this strange new world."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
				if(index==0) {
					return "Phone";
				} else if(index==1) {
					return "[style.colourYellowLight(Eggs)]";
				}
			}
			return super.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response(
							(Main.game.getPlayer().isMainQuestUpdated() || Main.game.getPlayer().isSideQuestUpdated() || Main.game.getPlayer().isRelationshipQuestUpdated())
								?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Quests</span>"
								:"Quests",
							"Open your planner to view your current quests.", PLANNER_MAIN){
						@Override
						public void effects() {
							Main.game.getPlayer().setMainQuestUpdated(false);
						}
					};
					
				} else if (index == 2) {
					return new Response(
							Main.getProperties().hasValue(PropertyValue.levelUpHightlight)
								? "<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Perk Tree</span>"
								:"Perk Tree",
							"View your character page.", CHARACTER_PERK_TREE) {
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
						}
					};
					
				} else if (index == 3) {
					return new Response("Spells", "View your spells page.", SpellManagement.CHARACTER_SPELLS_EARTH) {
						@Override
						public void effects() {
							SpellManagement.setSpellOwner(Main.game.getPlayer(), MENU);
						}
					};
					
				} else if (index == 4) {
					return new Response("Fetishes", "View your fetishes page.", CHARACTER_FETISHES);
					
				} else if (index == 5) {
					return new Response("Stats", "Take a detailed look at your stats.", CHARACTER_STATS);
					
				} else if (index == 6) {
					return new Response("Selfie", "Take a selfie to get a good view of yourself.", CHARACTER_APPEARANCE);
					
				} else if (index == 7) {
					if(Main.game.getPlayer().getCharactersEncountered().isEmpty()) {
						return new Response("Contacts", "You haven't met anyone yet!", null);
					} else {
						return new Response("Contacts", "Even though you can't call anyone, on account of there being no phones in this world, you've still kept a record of all the people you've come into contact with.", CONTACTS) {
							@Override
							public void effects() {
								Main.game.getPlayer().sortCharactersEncountered();
								charactersEncountered = Main.game.getPlayer().getCharactersEncounteredAsGameCharacters(false);
							}
						};
					}
					
				} else if (index == 8) {
					return new Response(
							(Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
									|| Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))
								? "<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Encyclopedia</span>"
								:"Encyclopedia",
							"Have a look at all the different items and races you've discovered so far.", ENCYCLOPEDIA){
						@Override
						public void effects() {
							resetContentForRaces();
						}
					};
					
				} else if (index == 9) {
					if(Main.game.getPlayer().isAbleToSelfTransform()) {
						return new Response("Transform",
								"Transform your body.",
								BodyChanging.BODY_CHANGING_CORE) {
							@Override
							public void effects() {
								BodyChanging.setTarget(Main.game.getPlayer());
							}
						};
					} else {
						return new Response("Transform", Main.game.getPlayer().getUnableToTransformDescription(), null);
					}
					
				} else if (index == 10) {
					return new Response("Maps", "Take a look at maps of all the places you've visited.", MAP) {
						@Override
						public void effects() {
							worldTypeMap = Main.game.getPlayer().getWorldLocation();
						}
					};
					
				} else if (index == 11) {
					if(Main.game.isSavedDialogueNeutral()) {
						return new Response("Combat Moves", "Adjust the core combat moves you are prepared to perform in combat.", CombatMovesSetup.COMBAT_MOVES_CORE) {
							@Override
							public void effects() {
								CombatMovesSetup.setTarget(Main.game.getPlayer(), PhoneDialogue.MENU);
							}
						};
					} else {
						return new Response("Combat Moves", "You are too busy to change your core combat moves.", null);
					}
					
				} else if (index == 12) {
					if(!Main.game.isSavedDialogueNeutral()) {
							return new Response("Masturbate", "You are too busy to masturbate right now. (Can only be performed in a neutral scene.)", null);
							
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("Masturbate", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("Masturbate",
								"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with masturbating in a place where people could see you!",
								null);
						
					} else {
						return new ResponseSex("Masturbate",
								"Decide to take a break from what you're currently doing in order to masturbate.",
								true,
								true,
								new SMMasturbation(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMasturbation.STANDING))),
								Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)
									?Main.game.getPlayer().getParty()
									:null,
								null,
								AFTER_MASTURBATION,
								UtilText.parseFromXMLFile("misc/misc", "MASTURBATION"));
					}
					
				} else if (index == 13){
					String title = Main.game.isSillyMode()?"Delay":"Loiter";
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response(title, "You can only loiter to pass the time when in a neutral dialogue scene!", null);
					}
					if(Main.game.getPlayerCell().getPlace().isDangerous()) {
						return new Response(title, "You can only loiter to pass the time when in a safe area!", null);
					}
					if(Main.game.getPlayer().getLocationPlace().getPlaceType().isLoiteringEnabledOverride()) {
						if(!Main.game.getPlayer().getLocationPlace().getPlaceType().isLoiteringEnabled()) {
							return new Response(title, "This is not a suitable place in which to be loitering about!", null);
						}
					} else {
						if(!Main.game.getPlayerCell().getType().isLoiteringEnabled()) {
							return new Response(title, "This is not a suitable place in which to be loitering about!", null);
						}
					}
					return new Response(title, "Think about loitering in this area for an as-yet undetermined length of time.", LOITER_SELECTION);
					
				} else if (index == 14){
					if(!Main.game.getPlayer().isElementalSummoned()) {
						if(Main.game.getPlayer().hasDiscoveredElemental()) {
							return new Response("[el.Name]",
									"You have not summoned [el.name], so you cannot talk to [el.herHim]!"
											+ "<br/>[style.italicsMinorGood(You can summon your elemental from your 'Spells' screen!)]",
									null);
						}
						return new Response("Elemental",
								"You have not summoned your elemental, so you cannot talk to them..."
										+ "<br/>[style.italicsMinorGood(You can summon your elemental by learning an elemental-summoning spell and casting it from your 'Spells' screen!)]",
								null);
					}
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("[el.Name]",
								Main.game.isInSex()
									?"You cannot talk to [el.name] during sex!"
									:(Main.game.isInCombat()
										?"You cannot talk to [el.name] during combat!"
										:"You cannot talk to [el.name] in this scene!"),
								null);
					}
					return new Response("[el.Name]",
							"Spend some time talking with [el.name].",
							ElementalDialogue.ELEMENTAL_START);
					
				}
				
			} else if(responseTab==1) {
				Set<SexAreaOrifice> incubationAreas = Main.game.getPlayer().getIncubatingLitters().keySet();
				List<Response> responses = new ArrayList<>();
				
				if(incubationAreas.contains(SexAreaOrifice.VAGINA)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Lay eggs (womb)", "You are too busy to lay eggs right now. (Can only be performed in a neutral scene.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Lay eggs (womb)", "There is no suitable place nearby in which to lay your eggs!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("Lay eggs (womb)", "You are unable to gain access to your vagina, and so cannot currently lay the eggs which have matured in your womb!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Lay eggs (womb)",
								"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with laying your eggs in a place where people could see you!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_3)) {
						responses.add(new Response("Lay eggs (womb)", "You need to wait until the eggs in your womb have finished growing before you're able to lay them.", null));
						
					} else {
						responses.add(new Response("Lay eggs (womb)", "Find a suitable place nearby in which to lay the eggs you've been incubating in your womb.", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.VAGINA;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.ANUS)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Lay eggs (stomach)", "You are too busy to lay eggs right now. (Can only be performed in a neutral scene.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Lay eggs (stomach)", "There is no suitable place nearby in which to lay your eggs!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						responses.add(new Response("Lay eggs (stomach)", "You are unable to gain access to your asshole, and so cannot currently lay the eggs which have matured in your stomach!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Lay eggs (stomach)",
								"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with laying your eggs in a place where people could see you!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
						responses.add(new Response("Lay eggs (stomach)", "You need to wait until the eggs in your stomach have finished growing before you're able to lay them.", null));
						
					} else {
						responses.add(new Response("Lay eggs (stomach)", "Find a suitable place nearby in which to lay the eggs you've been incubating in your stomach.", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.ANUS;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.NIPPLE)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Lay eggs (breasts)", "You are too busy to lay eggs right now. (Can only be performed in a neutral scene.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Lay eggs (breasts)", "There is no suitable place nearby in which to lay your eggs!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
						responses.add(new Response("Lay eggs (breasts)", "You are unable to gain access to your nipples, and so cannot currently lay the eggs which have matured in your breasts!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Lay eggs (breasts)",
								"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with laying your eggs in a place where people could see you!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_3)) {
						responses.add(new Response("Lay eggs (breasts)", "You need to wait until the eggs in your breasts have finished growing before you're able to lay them.", null));
						
					} else {
						responses.add(new Response("Lay eggs (breasts)", "Find a suitable place nearby in which to lay the eggs you've been incubating in your breasts.", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.NIPPLE;
								applyEggLayingEffects();
							}
						});
					}
				}
				if(incubationAreas.contains(SexAreaOrifice.NIPPLE_CROTCH)) {
					String udderName = Main.game.getPlayer().getBreastCrotchShape()==BreastShape.UDDERS?"udders":"crotch-boobs";
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Lay eggs ("+udderName+")", "You are too busy to lay eggs right now. (Can only be performed in a neutral scene.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Lay eggs ("+udderName+")", "There is no suitable place nearby in which to lay your eggs!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES_CROTCH, true)) {
						responses.add(new Response("Lay eggs ("+udderName+")", "You are unable to gain access to your [pc.crotchNipples], and so cannot currently lay the eggs which have matured in your [pc.crotchBoobs]!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Lay eggs ("+udderName+")",
								"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with laying your eggs in a place where people could see you!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)) {
						responses.add(new Response("Lay eggs ("+udderName+")", "You need to wait until the eggs in your [pc.crotchBoobs] have finished growing before you're able to lay them.", null));
						
					} else {
						responses.add(new Response("Lay eggs ("+udderName+")", "Find a suitable place nearby in which to lay the eggs you've been incubating in your [pc.crotchBoobs].", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.NIPPLE_CROTCH;
								applyEggLayingEffects();
							}
						});
					}
				
				}
				if(incubationAreas.contains(SexAreaOrifice.SPINNERET)) {
					if(!Main.game.isSavedDialogueNeutral()) {
						responses.add(new Response("Lay eggs (spinneret)", "You are too busy to lay eggs right now. (Can only be performed in a neutral scene.)", null));
						
					} else if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						responses.add(new Response("Lay eggs (spinneret)", "There is no suitable place nearby in which to lay your eggs!", null));
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						responses.add(new Response("Lay eggs (spinneret)",
								"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with laying your eggs in a place where people could see you!",
								null));
						
					} else if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_3)) {
						responses.add(new Response("Lay eggs (spinneret)", "You need to wait until the eggs in your spinneret have finished growing before you're able to lay them.", null));
						
					} else {
						responses.add(new Response("Lay eggs (spinneret)", "Find a suitable place nearby in which to lay the eggs you've been incubating in your spinneret.", INCUBATION_EGG_LAYING) {
							@Override
							public void effects() {
								layingEggsArea = SexAreaOrifice.SPINNERET;
								applyEggLayingEffects();
							}
						});
					}
				}
				
				if(index>0 && index-1<responses.size()) {
					return responses.get(index-1);
				}
				
				// Removed due to description handling being too messy
//				if(incubationAreas.size()>1) {
//					Set<SexAreaOrifice> readyToLay = new HashSet<>();
//					if(incubationAreas.contains(SexAreaOrifice.VAGINA) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_3)) {
//						readyToLay.add(SexAreaOrifice.VAGINA);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.ANUS) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
//						readyToLay.add(SexAreaOrifice.ANUS);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.NIPPLE) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_3)) {
//						readyToLay.add(SexAreaOrifice.NIPPLE);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.NIPPLE_CROTCH) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)) {
//						readyToLay.add(SexAreaOrifice.NIPPLE_CROTCH);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.SPINNERET) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_3)) {
//						readyToLay.add(SexAreaOrifice.SPINNERET);
//					}
//					if(readyToLay.size()<=1) {
//						return new Response("Lay eggs (all)", "You need to wait until the eggs in at least two of your orifices have finished maturing before you're able to lay them all at once.", null);
//					} else {
//						return new Response("Lay eggs (all)", "Find a suitable place nearby in which to lay all of the matured eggs which you've been incubating in your orifices.", INCUBATION_EGG_LAYING) {
//							@Override
//							public void effects() {
//								layingEggsAreas = new HashSet<>(readyToLay);
//								applyEggLayingEffects();
//							}
//						};
//					}
//				}
			}
			
			if(index == 0) {
				return new ResponseEffectsOnly("Back", "Put your phone away."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
				
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode INCUBATION_EGG_LAYING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.addSpecialParsingString(Util.intToString(incubationOffspringBirthed.size()), true);
			UtilText.addSpecialParsingString(incubationOffspringBirthed.size()==1?"egg":"eggs", false);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()));
			
			if(incubationOffspringBirthed.size()==1) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()+"_SINGLE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_"+layingEggsArea.toString()+"_MULTIPLE"));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Rest", "You have no energy left, and your eyelids seem to be getting heavier...", INCUBATION_EGG_LAYING_FINISHED){
					@Override
					public void effects() {
						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_INCUBATION, Quest.SIDE_UTIL_COMPLETE));
						}
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode INCUBATION_EGG_LAYING_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.addSpecialParsingString(Util.intToString(incubationOffspringBirthed.size()), true);

			if(incubationOffspringBirthed.size()==1) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_SINGLE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_MULTIPLE"));
			}
			
			UtilText.nodeContentSB.append("<p style='text-align:center;'>");
				for(String id : incubationOffspringBirthed) {
					try {
						OffspringSeed offspring = Main.game.getOffspringSeedById(id);
						String descriptor = LilayaBirthing.getOffspringDescriptor(offspring);
						UtilText.nodeContentSB.append("<br/>"
								+ Util.capitaliseSentence(UtilText.generateSingularDeterminer(descriptor))+" "+descriptor
								+ " <i style='color:"+offspring.getGender().getColour().toWebHexString()+";'>"+offspring.getGenderName()+"</i>"
								+ " <i style='color:"+offspring.getSubspecies().getColour(null).toWebHexString()+";'>"+offspring.getSubspecies().getName(offspring.getBody())+"</i>");
					} catch(Exception ex) {
					}
				}
			UtilText.nodeContentSB.append("</p>");
			
			if(incubationOffspringBirthed.size()==1) {
				try {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_END_SINGLE", Main.game.getNPCById(incubationOffspringBirthed.iterator().next())));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/misc", "INCUBATION_EGG_LAYING_FINISHED_END_MULTIPLE"));
			}
	
			return UtilText.nodeContentSB.toString();
		}
	
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Having successfully laid your eggs, you're now free to continue on your way.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_MASTURBATION = new DialogueNode("Finished", "You've had enough of masturbating for now.", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/misc", "AFTER_MASTURBATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way...", Main.game.getDefaultDialogue(false));

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode PLANNER_MAIN = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			// Main Quests:
			QuestLine questLine = QuestLine.MAIN;
			List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
			int index = questList.size()-1;
			Quest q = questList.get(index);
			
			if (Main.game.getPlayer().isQuestCompleted(questLine)) {
				journalSB.append(
						"<details open>"
						+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
							+ "Completed - " + questLine.getName()
						+ "</summary>");
				journalSB.append(getQuestBoxDiv(q, true));
				
			} else{
				journalSB.append(
						"<details open>"
							+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
								+ questLine.getName()
							+ "</summary>");
				journalSB.append(getQuestBoxDiv(q, false));
			}
			
			index--;
				
			while(index>=0) {
				q = questList.get(index);
				journalSB.append(getQuestBoxDiv(q, true));
				index--;
			}

			journalSB.append("</details>");

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", null);
				
			} else if (index == 2) {
				return new Response(
						(Main.game.getPlayer().isSideQuestUpdated()
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
							:"Side quests"),
						"View your side quests.",
						PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
				
			} else if (index == 3) {
				return new Response(
						(Main.game.getPlayer().isRelationshipQuestUpdated()
							?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Romance quests</span>"
							:"Romance quests"),
						"View your romance quests.",
						PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_SIDE = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean sideQuestsFound = false;
			
			// Side Quests:
			List<QuestLine> sideQuests = new ArrayList<>(Main.game.getPlayer().getQuests().keySet());
			sideQuests.sort((q1, q2)->
				Main.game.getPlayer().isQuestCompleted(q1)
					?(Main.game.getPlayer().isQuestCompleted(q2)
						?0
						:1)
					:(Main.game.getPlayer().isQuestCompleted(q2)
						?-1
						:0));
			for (QuestLine questLine : sideQuests) {
				if(questLine.getType()==QuestType.SIDE) {
					sideQuestsFound = true;
					
					List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
					int index = questList.size()-1;
					Quest q = questList.get(index);
					
					if(Main.game.getPlayer().isQuestFailed(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + PresetColour.GENERIC_TERRIBLE.getShades()[1] + ";'>"
									+ "Failed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDivFailed(Main.game.getPlayer().getQuestsFailed().get(questLine)));
//						journalSB.append(getQuestBoxDiv(q, true)); // Do not append, as this was the failed Quest
						
					} else if(Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
									+ "Completed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, false));
					}
					
					index--;
						
					while(index>=0) {
						q = questList.get(index);
						journalSB.append(getQuestBoxDiv(q, true));
						index--;
					}
	
					journalSB.append("</details>");
				}
			}
			
			if(!sideQuestsFound) {
				journalSB.append("<div class='subTitle'>You haven't got any side quests!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response("Side quests", "View your side quests.", null);
			} else if (index == 3) {
				return new Response(
						(Main.game.getPlayer().isRelationshipQuestUpdated()
						?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Romance quests</span>"
							:"Romance quests"),
							"View your romance quests.",
						PLANNER_RELATIONSHIP){
					@Override
					public void effects() {
						Main.game.getPlayer().setRelationshipQuestUpdated(false);
					}
				};
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode PLANNER_RELATIONSHIP = new DialogueNode("Planner", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder();

			boolean relationshipQuestFound = false;
			
			// Romance Quests:
			for (QuestLine questLine : Main.game.getPlayer().getQuests().keySet()) {
				if(questLine.getType()==QuestType.RELATIONSHIP) {
					relationshipQuestFound = true;
					
					List<Quest> questList = Main.game.getPlayer().getQuests().get(questLine);
					int index = questList.size()-1;
					Quest q = questList.get(index);
					
					if(Main.game.getPlayer().isQuestFailed(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + PresetColour.GENERIC_TERRIBLE.getShades()[1] + ";'>"
									+ "Failed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDivFailed(Main.game.getPlayer().getQuestsFailed().get(questLine)));
//						journalSB.append(getQuestBoxDiv(q, true)); // Do not append, as this was the failed Quest
						
					} else if(Main.game.getPlayer().isQuestCompleted(questLine)) {
						journalSB.append(
								"<details>"
								+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().getShades()[1] + ";'>"
									+ "Completed - " + questLine.getName()
								+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, true));
						
					} else{
						journalSB.append(
								"<details open>"
									+ "<summary class='quest-title' style='color:" + questLine.getType().getColour().toWebHexString() + ";'>"
										+ questLine.getName()
									+ "</summary>");
						journalSB.append(getQuestBoxDiv(q, false));
					}
					
					index--;
						
					while(index>=0) {
						q = questList.get(index);
						journalSB.append(getQuestBoxDiv(q, true));
						index--;
					}
	
					journalSB.append("</details>");
				}
			}
			
			if(!relationshipQuestFound) {
				journalSB.append("<div class='subTitle'>You haven't got any romance quests!</div>");
			}

			return journalSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Main quests", "View your progress on the main quest.", PLANNER_MAIN);
			} else if (index == 2) {
				return new Response((Main.game.getPlayer().isSideQuestUpdated()
						?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Side quests</span>"
						:"Side quests"), "View your side quests.", PLANNER_SIDE){
					@Override
					public void effects() {
						Main.game.getPlayer().setSideQuestUpdated(false);
					}
				};
			} else if (index == 3) {
				return new Response("Romance quests", "View your romance quests.", null);
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getQuestBoxDivFailed(Quest q) {
		return "<div class='quest-box'>"
				+ getLevelAndExperienceHTML(q, true)
				+ "<h6 style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";text-align:center;'>"
						+ "<b>Failed - "+ q.getName() + "</b>"
				+ "</h6>"
				+ "<p style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";text-align:center;'>"
					+ q.getCompletedDescription()
				+ "</p>" 
			+ "</div>";
	}
	
	private static String getQuestBoxDiv(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "";
//			return "<div class='quest-box'>"
//					+ "<h6 style='color:" + q.getQuestType().getColour().getShades()[1] + ";text-align:center;'>"
//							+ "<b>Completed - "+ q.getName() + "</b>"
//					+ "</h6>"
//				+ "</div>";
		}
		
		if(completed) {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColour().getShades()[1] + ";text-align:center;'>"
							+ "<b>Completed - "+ q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";text-align:center; margin-top:0;'>"
						+ q.getCompletedDescription()
					+ "</p>" 
				+ "</div>";
			
		} else {
			return "<div class='quest-box'>"
					+ getLevelAndExperienceHTML(q, completed)
					+ "<h6 style='color:" + q.getQuestType().getColour().toWebHexString()+ "; text-align:center; margin-top:0;'>"
						+ "<b>" + q.getName() + "</b>"
					+ "</h6>"
					+ "<p style='text-align:center;'>"
						+ q.getDescription()
					+ "</p>"
				+ "</div>";
		}
	}
	
	private static String getLevelAndExperienceHTML(Quest q, boolean completed) {
		if(q==Quest.SIDE_UTIL_COMPLETE) {
			return "";
		}
		
		if (!completed) {
			if(q.getLevel() <= Main.game.getPlayer().getLevel() - 3) {
				return "<b class='quest-extra level' style='color:"+  PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
				
			} else if (q.getLevel() >= Main.game.getPlayer().getLevel() + 3) {
				return "<b class='quest-extra level' style='color:"+  PresetColour.GENERIC_BAD.toWebHexString() + ";'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
				
			} else {
				return "<b class='quest-extra level'>Level " + q.getLevel()+ "</b>"
						+ "<b class='quest-extra experience' style='color:" + PresetColour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
			}
			
		} else {
			return "<b class='quest-extra level' style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>Level " + q.getLevel() + "</b>"
					+ "<b class='quest-extra experience' style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + q.getExperienceReward() + " xp</b>";
		}
	}
	

	public static final DialogueNode CHARACTER_APPEARANCE = new DialogueNode("Selfie picture", "Take a selfie", true) {

		@Override
		public String getContent() {
//			return Main.game.getPlayer().getBodyDescription();
			return Main.game.getPlayer().getCharacterInformationScreen(true);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static String getAttributeDisplayValue(AbstractAttribute att) {
		String valueForDisplay = Units.number(Main.game.getPlayer().getAttributeValue(att));
		
		if(att.isInfiniteAtUpperLimit() && Main.game.getPlayer().getAttributeValue(att)>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(true);
		}
		
		return valueForDisplay;
	}
	
	public static final DialogueNode CHARACTER_STATS = new DialogueNode("Character Stats", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			AbstractStatusEffect physiqueSE = PhysiqueLevel.getPhysiqueLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getRelatedStatusEffect();
			AbstractStatusEffect arcaneSE = IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)).getRelatedStatusEffect();
			AbstractStatusEffect corruptionSE = CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect();
					
			UtilText.nodeContentSB.append(
				"<div class='container-full-width'>"
					+ statAttributeHeader()
					
					+ "<p style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'><b>Core Attributes</b></p>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_PHYSIQUE,
							"'<b style='color:"+physiqueSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(physiqueSE.getName(Main.game.getPlayer()))+"</b>' effect gained")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_ARCANE,
							"'<b style='color:"+arcaneSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(arcaneSE.getName(Main.game.getPlayer()))+"</b>' effect gained")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MAJOR_CORRUPTION,
							"'<b style='color:"+corruptionSE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(corruptionSE.getName(Main.game.getPlayer()))+"</b>' effect gained")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.HEALTH_MAXIMUM,
							"When reduced to 0, you lose combat")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.MANA_MAXIMUM,
							"Used as a resource for casting spells")
//				+"</div>"
//				
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'><b>Misc. Attributes</b></p>"
					+ getAttributeBox(Main.game.getPlayer(), Attribute.FERTILITY,
							"Increases pregnancy chance")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.VIRILITY,
							"Increases impregnation chance")

					+ "<div class='container-full-width' style='text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
						+ "<b style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>Pregnancy calculation:</b>"
								+ "<br/>"
								+ "<i>"+GameCharacter.PREGNANCY_CALCULATION+"</i>"
					+ "</div>"
//				+"</div>"
//					
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'><b>Combat Attributes</b></p>"
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE,
							"Deals <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.CRITICAL_DAMAGE))+"%</b> normal damage",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.ENERGY_SHIELDING,
							"<b>"+getAttributeDisplayValue(Attribute.ENERGY_SHIELDING)+"</b> Health Block/Turn",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER,
							"Reduces spell cost by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.SPELL_COST_MODIFIER))+"%</b>")
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_SPELLS,
							"Increases spell damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_SPELLS))+"%</b>",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_UNARMED,
							"Increases unarmed damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED))+"%</b>",
							true)
					

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_MELEE_WEAPON,
							"Increases melee weapon damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_MELEE_WEAPON))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_RANGED_WEAPON,
							"Increases ranged weapon damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_RANGED_WEAPON))+"%</b>",
							true)
					
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_PHYSICAL,
							"Increases physical damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_PHYSICAL))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_PHYSICAL,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_PHYSICAL)+"</b> Physical Block/Turn",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_FIRE,
							"Increases fire damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_FIRE))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_FIRE,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_FIRE)+"</b> Fire Block/Turn",
							true)
					
					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ICE,
							"Increases ice damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ICE))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_ICE,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_ICE)+"</b> Ice Block/Turn",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_POISON,
							"Increases poison damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_POISON))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_POISON,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_POISON)+"</b> Poison Block/Turn",
							true)

					+ getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LUST,
							"Increases lust damage by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST))+"%</b>",
							true)
					+ getAttributeBox(Main.game.getPlayer(), Attribute.RESISTANCE_LUST,
							"<b>"+getAttributeDisplayValue(Attribute.RESISTANCE_LUST)+"</b> Lust Block/Turn",
							true)

//				+"</div>"
//				+"<div class='container-full-width'>"
					+ "<p style='color:"+PresetColour.GENERIC_COMBAT.toWebHexString()+"; text-align:center;'><b>Racial Damage Attributes</b></p>");
			
			List<AbstractRace> raceListSorted = new ArrayList<>(Attribute.racialAttributes.keySet());
			raceListSorted.sort((r1, r2) -> r1.getName(true).compareTo(r2.getName(true)));
			
			for(AbstractRace race : raceListSorted) {
				AbstractAttribute attribute = Attribute.racialAttributes.get(race);
				int damageModifier = (int) Main.game.getPlayer().getAttributeValue(attribute);
				if(race==Race.DEMON) {
					// DEMON is split in IMP, DEMON, LILIN, and ELDER_LILIN damage
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_IMP,
									"Increases damage vs imps by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_IMP))+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), attribute,
									"Increases damage vs "+race.getNamePlural(true)+" by <b>"+Units.number(damageModifier)+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_LILIN,
									"Increases damage vs lilin by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LILIN))+"%</b>",
									true));
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), Attribute.DAMAGE_ELDER_LILIN,
									"Increases damage vs elder lilin by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_ELDER_LILIN))+"%</b>",
									true));
				} else {
					UtilText.nodeContentSB.append(
							getAttributeBox(Main.game.getPlayer(), attribute,
									"Increases damage vs "+race.getNamePlural(true)+" by <b>"+Units.number(damageModifier)+"%</b>",
									true));
				}
			}
			
//			List<AbstractAttribute> encounteredAttributes = new ArrayList<>();
//			for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
//				AbstractAttribute damageModifier = subspecies.getDamageMultiplier();
//				if(!encounteredAttributes.contains(damageModifier)) {
//					UtilText.nodeContentSB.append(
//							getAttributeBox(Main.game.getPlayer(), damageModifier,
//									"Increases damage vs "+subspecies.getNamePlural(null)+" by <b>"+Units.number(Main.game.getPlayer().getAttributeValue(damageModifier))+"%</b>",
//									true));
//					encounteredAttributes.add(damageModifier);
//				}
//			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", null);
			
			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static String getBodyStatsPanel(GameCharacter character) {
		boolean knowsNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer());
		boolean knowsCrotchNipples = character.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer());
		boolean knowsPenis = character.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer());
		boolean knowsVagina = character.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer());
		boolean knowsAnus = character.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer());
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='margin-bottom:0;'>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Body Attributes")
				+ statHeader()
				+ statRow(PresetColour.ANDROGYNOUS, "Femininity",
						PresetColour.TEXT, String.valueOf(character.getFemininityValue()),
						character.getFemininity().getColour(), Util.capitaliseSentence(character.getFemininity().getName(false)))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Height",
						PresetColour.TEXT, Units.size(character.getHeightValue()),
						character.getHeight().getColour(), Util.capitaliseSentence(character.getHeight().getDescriptor()))
				+ statRow(PresetColour.MUSCLE_THREE, "Muscle Definition",
						PresetColour.TEXT, String.valueOf(character.getMuscleValue()),
						character.getMuscle().getColour(), Util.capitaliseSentence(character.getMuscle().getName(false)))
				+ statRow(PresetColour.BODY_SIZE_THREE, "Body Size",
						PresetColour.TEXT, String.valueOf(character.getBodySizeValue()),
						character.getBodySize().getColour(), Util.capitaliseSentence(character.getBodySize().getName(false)))
				+ statRow(character.getBodyShape().toWebHexStringColour(), "Body Shape",
						PresetColour.TEXT,
						"<b style='color:"+character.getMuscle().getColour().toWebHexString()+";'>"+character.getMuscleValue()+"</b>"
								+ " <b>|</b> <b style='color:"+character.getBodySize().getColour().toWebHexString()+";'>"+character.getBodySizeValue()+"</b>",
						character.getBodyShape().toWebHexStringColour(), Util.capitaliseSentence(character.getBodyShape().getName(false))));
		
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		sb.append(statRowHeader(PresetColour.TRANSFORMATION_GREATER, "Head & Throat Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Hair Length",
						PresetColour.TEXT, Units.size(character.getHairRawLengthValue()),
						character.getHairLength().getColour(), Util.capitaliseSentence(character.getHairLength().getDescriptor()))
				+ (character.hasHorns()
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "Horn Length",
							PresetColour.TEXT, Units.size(character.getHornLengthValue()),
							character.getHornLength().getColour(), Util.capitaliseSentence(character.getHornLength().getDescriptor()))
						:statRow(PresetColour.TRANSFORMATION_GENERIC, "Horn Length",
								PresetColour.TEXT, Units.size(0),
								PresetColour.TEXT_GREY, "N/A"))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Tongue Length",
						PresetColour.TEXT, Units.size(character.getTongueLengthValue()),
						PresetColour.TRANSFORMATION_GENERIC, Util.capitaliseSentence(character.getTongueLength().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Wetness",
						PresetColour.TEXT, String.valueOf(character.getFaceWetness().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceWetness().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Capacity",
						PresetColour.TEXT, Units.size(character.getFaceRawCapacityValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceCapacity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Depth",
						PresetColour.TEXT, String.valueOf(character.getFaceDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceDepth().getDescriptor()) + " ("+(Math.round(character.getFaceDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Throat Depth Limit",
						PresetColour.GENERIC_MINOR_GOOD, Units.size(character.getFaceMaximumPenetrationDepthComfortable()),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Throat Depth Limit",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":Units.size(character.getFaceMaximumPenetrationDepthUncomfortable()),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Elasticity",
						PresetColour.TEXT, String.valueOf(character.getFaceElasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFaceElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Throat Plasticity",
						PresetColour.TEXT, String.valueOf(character.getFacePlasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getFacePlasticity().getDescriptor())));
				
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasNipples()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Breast Attributes")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Cup Size",
							PresetColour.TEXT, String.valueOf(character.getBreastRawSizeValue()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getBreastSize().getCupSizeName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Count",
							PresetColour.TEXT, String.valueOf(character.getBreastRows()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(Util.capitaliseSentence(Util.intToString(character.getBreastRows()))+" pair"+(character.getBreastRows()==1?"":"s")))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Storage",
							PresetColour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getBreastRawMilkStorageValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastMilkStorage().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Regeneration Per Breast",
							PresetColour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getLactationRegenerationPerSecond(false)*60)+"/minute",
							PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Regeneration Total",
							PresetColour.TEXT, !knowsNipples?"Unknown":Units.fluid(character.getLactationRegenerationPerSecond(true)*60)+"/minute",
							PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getBreastLactationRegeneration().getName()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
							PresetColour.TEXT, !knowsNipples?"Unknown":Units.size(character.getNippleRawCapacityValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNippleCapacity().getDescriptor()))
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "Nipple Depth",
							PresetColour.TEXT, String.valueOf(character.getNippleDepth().getValue()),
							PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getNippleDepth().getDescriptor()) + " ("+(Math.round(character.getNippleDepth().getDepthPercentage()*100))+"%)")
						:"")
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Nipple Depth Limit",
							PresetColour.GENERIC_MINOR_GOOD, (!knowsNipples?"Unknown":Units.size(character.getNippleMaximumPenetrationDepthComfortable())),
							PresetColour.TEXT, "N/A")
						:"")
					+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleRawCapacityValue()>0
						?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Nipple Depth Limit",
							PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(!knowsNipples?"Unknown":Units.size(character.getNippleMaximumPenetrationDepthUncomfortable())),
							PresetColour.TEXT, "N/A")
						:"")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
							PresetColour.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNippleElasticity().getValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNippleElasticity().getDescriptor()))
					+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
							PresetColour.TEXT, !knowsNipples?"Unknown":String.valueOf(character.getNipplePlasticity().getValue()),
							PresetColour.GENERIC_SEX, !knowsNipples?"Unknown":Util.capitaliseSentence(character.getNipplePlasticity().getDescriptor())));
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Breast Attributes")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] no breasts...")));
		}
		
		if(character.hasBreastsCrotch()) {
			sb.append("<span style='height:16px;width:100%;float:left;'></span>");
			rowCount = 0;
			
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, (character.getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs"))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Size",
								PresetColour.TEXT, !character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRawSizeValue()),
								PresetColour.GENERIC_SEX,
								!character.isBreastsCrotchVisibleThroughClothing()&&!knowsCrotchNipples
									?"Unknown"
									:(character.getBreastCrotchShape()==BreastShape.UDDERS
										?Util.capitaliseSentence(character.getBreastCrotchSize().getDescriptor())
										:Util.capitaliseSentence(character.getBreastCrotchSize().getCupSizeName())))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Count",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getBreastCrotchRows()),
								PresetColour.GENERIC_SEX,
								!knowsCrotchNipples
									?"Unknown"
									:(character.getBreastCrotchRows()==0
										?"A single udder"
										:Util.capitaliseSentence(Util.intToString(character.getBreastCrotchRows()))+" pair"+(character.getBreastCrotchRows()==1?"":"s")))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Storage",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getBreastCrotchRawMilkStorageValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchMilkStorage().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Regeneration Per Crotch-boob",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getCrotchLactationRegenerationPerSecond(false)*60)+"/minute",
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Milk Regeneration Total",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":Units.fluid(character.getCrotchLactationRegenerationPerSecond(true)*60)+"/minute",
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getBreastCrotchLactationRegeneration().getName()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchRawCapacityValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchCapacity().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Nipple Depth",
								PresetColour.TEXT, String.valueOf(character.getNippleCrotchDepth().getValue()),
								PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getNippleCrotchDepth().getDescriptor()) + " ("+(Math.round(character.getNippleCrotchDepth().getDepthPercentage()*100))+"%)")
						+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleCrotchRawCapacityValue()>0
							?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Nipple Depth Limit",
								PresetColour.GENERIC_MINOR_GOOD, (!knowsCrotchNipples?"Unknown":Units.size(character.getNippleCrotchMaximumPenetrationDepthComfortable())),
								PresetColour.TEXT, "N/A")
							:"")
						+ (Main.game.isPenetrationLimitationsEnabled() && character.getNippleCrotchRawCapacityValue()>0
							?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Nipple Depth Limit",
								PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(!knowsCrotchNipples?"Unknown":Units.size(character.getNippleCrotchMaximumPenetrationDepthUncomfortable())),
								PresetColour.TEXT, "N/A")
							:"")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchElasticity().getValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchElasticity().getDescriptor()))
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
								PresetColour.TEXT, !knowsCrotchNipples?"Unknown":String.valueOf(character.getNippleCrotchPlasticity().getValue()),
								PresetColour.GENERIC_SEX, !knowsCrotchNipples?"Unknown":Util.capitaliseSentence(character.getNippleCrotchPlasticity().getDescriptor())));
		}

		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasTail()) {
			if(Main.game.isPenetrationLimitationsEnabled()) {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tail Attributes")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Length | Used for penetration",
							PresetColour.TEXT, Units.size(character.getTailLength(false))+" | "+Units.size(character.getTailLength(true)),
							PresetColour.GENERIC_SEX, "N/A"));
			} else {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tail Attributes")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Length",
							PresetColour.TEXT, Units.size(character.getTailLength(false)),
							PresetColour.GENERIC_SEX, "N/A"));
			}
			sb.append(
					statRow(PresetColour.TRANSFORMATION_GENERIC,
						"Diameter at lengths",
						PresetColour.TEXT,
						"[style.colourSize4("+Units.size(character.getTailDiameter(0))+")]"
							+"<br/>[style.colourSize3("+Units.size(character.getTailDiameter(character.getTailLength(false)*0.33f))+")]"
							+"<br/>[style.colourSize2("+Units.size(character.getTailDiameter(character.getTailLength(false)*0.66f))+")]"
							+"<br/>[style.colourSize1("+Units.size(character.getTailDiameter(character.getTailLength(false)))+")]",
						PresetColour.TRANSFORMATION_GENERIC,
						"[style.colourSize4(Base)]"
							+ "<br/>[style.colourSize3(33%)]"
							+ "<br/>[style.colourSize2(66%)]"
							+ "<br/>[style.colourSize1(Tip)]")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC,
							"Circumference at lengths",
							PresetColour.TEXT,
							"[style.colourSize4("+Units.size(character.getTailCircumference(0))+")]"
								+"<br/>[style.colourSize3("+Units.size(character.getTailCircumference(character.getTailLength(false)*0.33f))+")]"
								+"<br/>[style.colourSize2("+Units.size(character.getTailCircumference(character.getTailLength(false)*0.66f))+")]"
								+"<br/>[style.colourSize1("+Units.size(character.getTailCircumference(character.getTailLength(false)))+")]",
							PresetColour.TRANSFORMATION_GENERIC,
							"[style.colourSize4(Base)]"
								+ "<br/>[style.colourSize3(33%)]"
								+ "<br/>[style.colourSize2(66%)]"
								+ "<br/>[style.colourSize1(Tip)]"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tail Attributes")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] no tail...")));
		}

		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(character.hasTentacle()) {
			if(Main.game.isPenetrationLimitationsEnabled()) {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tentacle Attributes")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Length | Used for penetration",
							PresetColour.TEXT, Units.size(character.getTentacleLength(false))+" | "+Units.size(character.getTentacleLength(true)),
							PresetColour.GENERIC_SEX, "N/A"));
			} else {
				sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tentacle Attributes")
						+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Length",
							PresetColour.TEXT, Units.size(character.getTentacleLength(false)),
							PresetColour.GENERIC_SEX, "N/A"));
			}
			sb.append(
					statRow(PresetColour.TRANSFORMATION_GENERIC,
						"Diameter at lengths",
						PresetColour.TEXT,
						"[style.colourSize4("+Units.size(character.getTentacleDiameter(0))+")]"
							+"<br/>[style.colourSize3("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)*0.33f))+")]"
							+"<br/>[style.colourSize2("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)*0.66f))+")]"
							+"<br/>[style.colourSize1("+Units.size(character.getTentacleDiameter(character.getTentacleLength(false)))+")]",
						PresetColour.TRANSFORMATION_GENERIC,
						"[style.colourSize4(Base)]"
							+ "<br/>[style.colourSize3(33%)]"
							+ "<br/>[style.colourSize2(66%)]"
							+ "<br/>[style.colourSize1(Tip)]")
					+ statRow(PresetColour.TRANSFORMATION_GENERIC,
							"Circumference at lengths",
							PresetColour.TEXT,
							"[style.colourSize4("+Units.size(character.getTentacleCircumference(0))+")]"
								+"<br/>[style.colourSize3("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)*0.33f))+")]"
								+"<br/>[style.colourSize2("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)*0.66f))+")]"
								+"<br/>[style.colourSize1("+Units.size(character.getTentacleCircumference(character.getTentacleLength(false)))+")]",
							PresetColour.TRANSFORMATION_GENERIC,
							"[style.colourSize4(Base)]"
								+ "<br/>[style.colourSize3(33%)]"
								+ "<br/>[style.colourSize2(66%)]"
								+ "<br/>[style.colourSize1(Tip)]"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_GENERIC, "Tentacle Attributes")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] no tentacles...")));
		}
		
		sb.append( "<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(!knowsPenis) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Penis Attributes")
					+ statRow(PresetColour.TEXT_GREY, "Unknown!"));
			
		} else if(character.hasPenis()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Penis Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Length",
						PresetColour.TEXT, (Units.size(character.getPenisRawSizeValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getPenisSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Diameter | Circumference",
						PresetColour.TEXT, (Units.size(character.getPenisDiameter())+" | "+Units.size(character.getPenisCircumference())),
						PresetColour.TEXT_GREY, "N/A")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Testicle Size",
						PresetColour.TEXT, (String.valueOf(character.getTesticleSize().getValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getTesticleSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Cum Storage",
						PresetColour.TEXT, (Units.fluid(character.getPenisRawCumStorageValue())),
						PresetColour.GENERIC_SEX, (Util.capitaliseSentence(character.getPenisCumStorage().getDescriptor())))
				+ (Main.getProperties().hasValue(PropertyValue.cumRegenerationContent) ? statRow(PresetColour.TRANSFORMATION_GENERIC, "Cum Regeneration",
						PresetColour.TEXT, Units.fluid(character.getCumRegenerationPerSecond()*60)+"/minute",
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getPenisCumProductionRegeneration().getName()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Cum Expulsion (% of stored cum)",
						PresetColour.TEXT, String.valueOf(character.getPenisRawCumExpulsionValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getPenisCumExpulsion().getDescriptor())) : ""));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Penis Attributes")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] no penis...")));
		}
			
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;
		
		if(!knowsVagina) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Vagina Attributes")
					+ statRow(PresetColour.TEXT_GREY, "Unknown!"));
			
		} else if(character.hasVagina()) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Vagina Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Clitoris Size",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawClitorisSizeValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaClitorisSize().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Wetness",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaWetness().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Units.size(character.getVaginaRawCapacityValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaCapacity().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Depth",
						PresetColour.TEXT, String.valueOf(character.getVaginaDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getVaginaDepth().getDescriptor()) + " ("+(Math.round(character.getVaginaDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Depth Limit",
						PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getVaginaMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Depth Limit",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(Units.size(character.getVaginaMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaElasticity().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaElasticity().getDescriptor())))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
						PresetColour.TEXT, (character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaPlasticity().getValue())),
						PresetColour.GENERIC_SEX, (character.getVaginaType() == VaginaType.NONE ? "N/A" : Util.capitaliseSentence(character.getVaginaPlasticity().getDescriptor()))));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Vagina Attributes")
					+ statRow(PresetColour.TEXT_GREY, UtilText.parse(character, "[npc.NameHasFull] no vagina...")));
		}
			
		sb.append("<span style='height:16px;width:100%;float:left;'></span>");
		rowCount = 0;

		if(!knowsAnus) {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Anus Attributes")
					+ statRow(PresetColour.TEXT_GREY, "Unknown!"));
			
		} else {
			sb.append(statRowHeader(PresetColour.TRANSFORMATION_SEXUAL, "Anus Attributes")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Wetness",
						PresetColour.TEXT, String.valueOf(character.getAssWetness().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssWetness().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Capacity",
						PresetColour.TEXT, Units.size(character.getAssRawCapacityValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssCapacity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Depth",
						PresetColour.TEXT, String.valueOf(character.getAssDepth().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssDepth().getDescriptor()) + " ("+(Math.round(character.getAssDepth().getDepthPercentage()*100))+"%)")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Comfortable Anus Depth Limit",
						PresetColour.GENERIC_MINOR_GOOD, (Units.size(character.getAssMaximumPenetrationDepthComfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ (Main.game.isPenetrationLimitationsEnabled()
					?statRow(PresetColour.TRANSFORMATION_GENERIC, "Uncomfortable Anus Depth Limit",
						PresetColour.GENERIC_MINOR_BAD, !character.getBodyMaterial().isOrificesLimitedDepth()?"No limit":(Units.size(character.getAssMaximumPenetrationDepthUncomfortable())),
						PresetColour.TEXT, "N/A")
					:"")
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Elasticity",
						PresetColour.TEXT, String.valueOf(character.getAssElasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssElasticity().getDescriptor()))
				+ statRow(PresetColour.TRANSFORMATION_GENERIC, "Plasticity",
						PresetColour.TEXT, String.valueOf(character.getAssPlasticity().getValue()),
						PresetColour.GENERIC_SEX, Util.capitaliseSentence(character.getAssPlasticity().getDescriptor())));
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode CHARACTER_STATS_BODY = new DialogueNode("Body Stats", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+"; text-align:center;'>Orifice Mechanics</summary>"
						
						+ "[style.boldSex(Capacity:)] An orifice's capacity determines the maximum diameter of penises and tails that can be comfortably inserted."
						+ " <b>Higher capacity values mean that the orifice can take thicker insertions without stretching.</b>"
						+ "<br/>- The diameter of a penis is calculated from a combination of their 'length' and 'girth' value, with additional modifiers (such as 'flared' or 'tapered') further altering the final value."
						+ "<br/>- The diameter of a tail is calculated from both its 'girth' value as well as the character's height."
						+ "<br/>- Capacity diameter values range from 0 [units.sizes] (extremely tight) to [units.sizes("+Math.round(Capacity.SEVEN_GAPING.getMaximumValue(false))+")] (gaping wide)."
						
						+ "<br/><br/>");
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				UtilText.nodeContentSB.append(
					"[style.boldSex(Depth:)] An orifice's depth determines the maximum length of penises and tails which can be comfortably or uncomfortably inserted into it."
						+ " <b>Higher depth values mean that longer penetrative objects can be accommodated before the character experiences discomfort.</b>"
						+ "<br/>- Comfortable/Uncomfortable depths of an orifice are based on the height of the character, and are then modified by the orifice's 'depth' value."
						+ "<br/>- Sexual partners who are not in the 'rough' sex-pace, and whose penises or tails are too long to be fully inserted into an orifice will stop at the limit of comfortable depth."
						+ "<br/>- Sexual partners who are in the 'rough' sex-pace will push past the comfortable orifice depth limit, causing the character discomfort."
						+ "<br/>- Size queens treat the 'uncomfortable' portion of orifice depth as being comfortable."
						+ "<br/>- Slimes and elementals always have the maximum depth value for each of their orifices."
						+ "<br/>- Depth values range from 0 ("+OrificeDepth.ZERO_EXTREMELY_SHALLOW.getDescriptor()+") to 7 ("+OrificeDepth.SEVEN_FATHOMLESS.getDescriptor()+")."
						
						+ "<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("[style.boldSex(Elasticity:)] An orifice's elasticity determines how quickly it stretches out, and also has an influence on detecting whether a penetrative object is too big for the orifice."
						+ " If a partner's penis is too large for your orifice's capacity value, then your orifice will stretch out each turn during sex, with <b>higher elasticity values meaning that it stretches out quicker</b>."
						+ " <b>Higher elasticity values also have an increased tolerance for objects that would normally be too large for the orifice, allowing larger objects to be inserted before stretching begins</b>."
						+ "<br/>Elasticity values range from 0 (extremely resistant to being stretched out) to 7 (instantly stretching out)."
						
						+ "<br/><br/>"
						
					+ "[style.boldSex(Plasticity:)] An orifice's plasticity determines how quickly it recovers after being stretched out."
						+ " If your orifice has been stretched out during sex, <b>higher plasticity values mean that it will recover slower, with very high values meaning that it will never recover all of its original tightness</b>."
						+ "<br/>Plasticity values range from 0 (instantly returns to starting size after sex) to 7 (recovers none of its original size after sex)."
			+ "</details>"
					
			+ getBodyStatsPanel(Main.game.getPlayer()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", null);
			
			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CHARACTER_STATS_SEX = new DialogueNode("Sex Stats", "", true) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='container-full-width' style='text-align:center; width:100%; padding:0; margin:4px 0;'>"
						+ "You have orgasmed [style.boldSex("+Main.game.getPlayer().getDaysOrgasmCount()+")] time"+(Main.game.getPlayer().getDaysOrgasmCount()==1?"":"s")
							+" today, bringing your total orgasm count to [style.boldSex("+Main.game.getPlayer().getTotalOrgasmCount()+")]."
						+ "<br/>"
						+ "Your record for most orgasms in one day is currently [style.boldSex("+Main.game.getPlayer().getDaysOrgasmCountRecord()+")]."
						+ "<br/>"
						+ "You have had sex with a total of [style.boldSex("+Main.game.getPlayer().getUniqueSexPartnerCount()+")] "+(Main.game.getPlayer().getUniqueSexPartnerCount()==1?"person":"different people")+"."
					+ "</div>");
					
			sb.append(sexStatHeader());
			
			boolean oddRow = false;
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Fingering",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
							-1,
							oddRow));
			
			oddRow = !oddRow;
			
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Anal Fingering",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
									-1,
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
									-1,
									oddRow)
							:""));
			
			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Blowjobs",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
							oddRow));

			oddRow = !oddRow;
			
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Cunnilingus",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
							-1,
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
							-1,
							oddRow));

			oddRow = !oddRow;
			
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Anilingus",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
									-1,
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
									-1,
									oddRow)
							:""));
			
			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}

			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Intercrural",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.THIGHS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.THIGHS, SexAreaPenetration.PENIS)),
									oddRow));

			oddRow = !oddRow;
					
			sb.append((Main.game.isFootContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Footjobs",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FOOT, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			if(Main.game.isFootContentEnabled()) {
				oddRow = !oddRow;
			}

			sb.append((Main.game.isArmpitContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_ONE, "Armpit fuck",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			if(Main.game.isArmpitContentEnabled()) {
				oddRow = !oddRow;
			}
					
			sb.append(sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Vaginal sex",
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
							Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
							oddRow));

			oddRow = !oddRow;
					
			sb.append((Main.game.isAnalContentEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Anal sex",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isAnalContentEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isNipplePenEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Nipple penetration",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isNipplePenEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isNipplePenEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Crotch Nipple penetration",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isNipplePenEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isUrethraEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Penis Urethra penetration",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.PENIS)),
									oddRow)
							:""));

			if(Main.game.isUrethraEnabled()) {
				oddRow = !oddRow;
			}
			
			sb.append((Main.game.isUrethraEnabled()
							?sexStatRow(PresetColour.AROUSAL_STAGE_TWO, "Vagina Urethra penetration",
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA)),
									Main.game.getPlayer().getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
									Main.game.getPlayer().getTotalCumCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, SexAreaPenetration.PENIS)),
									oddRow)
							:""));
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", null);
			
			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", CHARACTER_STATS_PREGNANCY);
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CHARACTER_STATS_PREGNANCY = new DialogueNode("Pregnancy Stats", "", true) {

		private void OffspringHeaderDisplay(StringBuilder output, String word_one, String word_two, String color, int count) {
			output.append("<div class='extraAttribute-quarter'>");
			output.append(word_one);
			output.append("<br/>");
			output.append("<b style='color:").append(color).append(";'>");
			output.append(word_two);
			output.append("</b>");
			output.append("<br/>");
			output.append(count);
			output.append("</div>");
		}

		private void offspringTableLine(StringBuilder output, offspringTableLineSubject subject, boolean evenRow, boolean includeIncubationColumn) {
			String color = subject.female ? PresetColour.FEMININE.toWebHexString() : PresetColour.MASCULINE.toWebHexString();
			
			String innerEntryStyle = "background:transparent; margin:0; padding:0; width:"+(includeIncubationColumn?"15":"20")+"%;";
			String innerEntryStyle2 = "background:transparent; margin:0; padding:0; width:15%;";
			String innerEntryStyleWide = "background:transparent; margin:0; padding:0; width:25%;";
			
			output.append("<div class='container-full-width' style='margin:0; width:100%; background:"+(evenRow?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");

				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
					output.append("<span style='color:").append(color).append(";'>");
						output.append(subject.child_name);
					output.append("</span>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle2+"'>");
					output.append("<span style='color:").append(subject.race_color).append(";'>");
						output.append(subject.species_name);
					output.append("</span>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
//					output.append("<b>");
						output.append(subject.mother);
//					output.append("</b>");
				output.append("</div>");
	
				output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
//					output.append("<b>");
						output.append(subject.father);
//					output.append("</b>");
				output.append("</div>");
	
				if(includeIncubationColumn) {
					output.append("<div class='container-full-width' style='"+innerEntryStyle+"'>");
						output.append(subject.incubator);
					output.append("</div>");
				}
				
				output.append("<div class='container-full-width' style='"+innerEntryStyleWide+"'>");
//					output.append("<b>");
						output.append(Util.stringsToStringList(subject.relationships, false));
//						output.append(
//								isGreyedOut
//									?"[style.colourDisabled("+Util.stringsToStringList(relationships, false)+")]"
//									:Util.stringsToStringList(relationships, false));
//					output.append("</b>");
				output.append("</div>");
			
			output.append("</div>");
		}

		@Override
		public String getContent() {
			int sonsBirthed=0;
			int daughtersBirthed=0;
			int sonsFathered=0;
			int daughtersFathered=0;
			int offspringIncubatedCount=0;
			
			// Birthed with player as the mother:
			for (Litter litter : Main.game.getPlayer().getLittersBirthed()){
				sonsBirthed+=litter.getSonsFromMother()+litter.getSonsFromFather();
				daughtersBirthed+=litter.getDaughtersFromMother()+litter.getDaughtersFromFather();
			}
			// Birthed with player as the father:
			for (Litter litter : Main.game.getPlayer().getLittersFathered()){
				sonsFathered+=(litter.isSelfImpregnation()?0:litter.getSonsFromMother()+litter.getSonsFromFather());
				daughtersFathered+=(litter.isSelfImpregnation()?0:litter.getDaughtersFromMother()+litter.getDaughtersFromFather());
			}
			// Egg-incubated offspring who have been birthed:
			for (Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				for (String id : litter.getOffspring()) {
					if (id.contains("NPCOffspring")) {
						//NPCOffspring is always born
						offspringIncubatedCount += 1;
					} else {
						try {
							OffspringSeed o = Main.game.getOffspringSeedById(id);
							//OffspringSeed may be born or unborn
							if (o.isBorn()) {
								offspringIncubatedCount += 1;
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			// Egg-implanted offspring who have been birthed:
			for (Litter litter : Main.game.getPlayer().getLittersImplanted()) {
				for (String id : litter.getOffspring()) {
					if (id.contains("NPCOffspring")) {
						//NPCOffspring is always born
						offspringIncubatedCount += 1;
					} else {
						try {
							OffspringSeed o = Main.game.getOffspringSeedById(id);
							//OffspringSeed may be born or unborn
							if (o.isBorn()) {
								offspringIncubatedCount += 1;
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}

			UtilText.nodeContentSB.setLength(0);

			OffspringHeaderDisplay(UtilText.nodeContentSB, "Mothered", "Sons", PresetColour.MASCULINE.toWebHexString(), sonsBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Mothered", "Daughters", PresetColour.FEMININE.toWebHexString(), daughtersBirthed);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Fathered", "Sons", PresetColour.MASCULINE.toWebHexString(), sonsFathered);
			OffspringHeaderDisplay(UtilText.nodeContentSB, "Fathered", "Daughters", PresetColour.FEMININE.toWebHexString(), daughtersFathered);

			int childrenMet = Main.game.getOffspring().size();
			int totalChildren = (sonsBirthed+daughtersBirthed+sonsFathered+daughtersFathered+offspringIncubatedCount);
			int percentageMet = totalChildren == 0 ? 100 : (100 * childrenMet / totalChildren);

			UtilText.nodeContentSB.append(
					"<div class='subTitle'>Total offspring: "+ totalChildren+" (Children met: "+ percentageMet +"%)</div>"
					
					+ "<span style='height:16px;width:100%;float:left;'></span>"
					
					+ pregnancyDetails()

					+ "<span style='height:16px;width:100%;float:left;'></span>");
			
			UtilText.nodeContentSB.append(
					"<div class='subTitle'>Offspring list</div>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div class='container-full-width'style='float:left; margin:0; width:100%; font-weight:bold;'>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:20%;'>"
								+ "Name"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "Race"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:20%;'>"
								+ "Mother"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:20%;'>"
								+ "Father"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:25%;'>"
								+ "Your relationship"
							+ "</div>"
						+ "</div>");
			
			int rowCount = 0;
			List<NPC> offspringMet= new ArrayList<>(Main.game.getOffspring());
			offspringMet.removeIf(npc -> npc.getIncubator()!=null && npc.getIncubator().isPlayer()); // Only non-egg incubated offspring
			List<OffspringSeed> offspringUnknown = new ArrayList<>(Main.game.getOffspringNotSpawned(os->true));
			if(offspringMet.isEmpty() && offspringUnknown.isEmpty()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='margin:0; padding:0; width:100%;float:left;'>"
												+ "[style.italicsDisabled(No Offspring...)]"
											+ "</div>");
			} else {
				offspringMet.sort(Comparator.comparing(GameCharacter::getBirthday));
				for(NPC npc : offspringMet) {
					offspringTableLineSubject subject = new offspringTableLineSubject(npc);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount % 2 == 0, false);
					rowCount++;
				}

				offspringUnknown.sort(Comparator.comparing(OffspringSeed::getConceptionDate));
				for(OffspringSeed os : offspringUnknown) {
					offspringTableLineSubject subject = new offspringTableLineSubject(os);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, false);
					rowCount++;
				}
			}

			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append("<span style='height:16px;width:100%;float:left;'></span>");
			
			UtilText.nodeContentSB.append(
					"<div class='subTitle'>Incubated offspring list</div>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div class='container-full-width'style='float:left; margin:0; width:100%; font-weight:bold;'>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "Name"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "Race"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "Mother"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "Father"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:15%;'>"
								+ "Incubated by"
							+ "</div>"
							+ "<div class='container-full-width' style='float:left; margin:0; width:25%;'>"
								+ "Your relationship"
							+ "</div>"
						+ "</div>");
			
			rowCount = 0;
			List<NPC> offspringIncubated = new ArrayList<>(Main.game.getOffspring());
			offspringIncubated.removeIf(npc -> npc.getIncubator()==null || !npc.getIncubator().isPlayer()); // Only egg incubated offspring
			offspringIncubated.removeAll(offspringMet);
			List<OffspringSeed> offspringIncubatedUnknown = new ArrayList<>(Main.game.getOffspringNotSpawned(os->true,true));
			offspringIncubatedUnknown.removeIf(os -> os.getIncubator()==null || !os.getIncubator().isPlayer()); // Only egg incubated offspring
			if(offspringIncubated.isEmpty() && offspringIncubatedUnknown.isEmpty()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='float:left; margin:0; width:100%;'>"
												+ "[style.italicsDisabled(No Incubated Offspring...)]"
											+ "</div>");
			} else {
				offspringIncubated.sort(Comparator.comparing(GameCharacter::getBirthday));
				for(NPC npc : offspringIncubated) {
					offspringTableLineSubject subject = new offspringTableLineSubject(npc);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, true);
					rowCount++;
				}
				offspringIncubatedUnknown.sort(Comparator.comparing(OffspringSeed::getConceptionDate));
				for(OffspringSeed os : offspringIncubatedUnknown) {
					offspringTableLineSubject subject = new offspringTableLineSubject(os);
					offspringTableLine(UtilText.nodeContentSB, subject, rowCount%2==0, true);
					rowCount++;
				}
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Core Stats", "Have a detailed look at your core stats.", CHARACTER_STATS);
			
			} else if (index == 2) {
				return new Response("Body stats", "Have a detailed look at your body's values.", CHARACTER_STATS_BODY);
			
			} else if (index == 3) {
				return new Response("Sex stats", "Have a detailed look at your sex stats.", CHARACTER_STATS_SEX);
			
			} else if (index == 4) {
				return new Response("Pregnancy stats", "Have a detailed look at your pregnancy stats.", null);
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static String sexStatHeader() {
		return "<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; font-weight:bold; text-align:center;'>"
					+ "<div class='container-full-width' style='width:33.3%; padding:0; margin:0;'>"
						+ "Type"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "Performed"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "Creampies<br/>Given"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "Received"
					+ "</div>"
					+ "<div class='container-full-width' style='width:16.66%; padding:0; margin:0;'>"
						+ "Creampies<br/>Received"
					+ "</div>"
				+ "</div>";
	}
	
	private static String sexStatRow(Colour colour, String name, int given, int loadsGiven, int received, int loadsReceived, boolean light) {
		return 
//				"<div class='container-full-width' style='width:100%; padding:0; margin:4px 0; text-align:center;'>"
				"<div class='container-full-width inner' style='text-align:center; margin-bottom:0;"+(light?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='float:left; width:33.3%; padding:0; margin:0;'>"
						+ "<span style='color:" + colour.toWebHexString() + ";'>" + name + "</span>"
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ given
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ (loadsGiven < 0 ? "<span class='option-disabled'>-</span>" : loadsGiven)
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ received
					+ "</div>"
					+ "<div style='float:left; width:16.66%; padding:0; margin:0;'>"
						+ (loadsReceived < 0 ? "<span class='option-disabled'>-</span>" : loadsReceived) 
					+ "</div>"
				+ "</div>";
	}
	
	private static String pregnancyRow(String topLeft, String bottomLeft, String topRight, String bottomRight) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append("<div class='container-full-width' style='text-align:center; margin-top:0; margin-bottom:4px;'>");
			contentSB.append("<div class='container-full-width' style='float:left; margin:0; width:25%; background:transparent;'>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(topLeft);
				contentSB.append("</div>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent; white-space:nowrap;'>");
					contentSB.append(bottomLeft);
				contentSB.append("</div>");
			contentSB.append("</div>");
	
			contentSB.append("<div class='container-full-width' style='float:left; margin:0; width:75%; background:transparent;'>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(topRight);
				contentSB.append("</div>");
				contentSB.append("<div class='container-full-width' style='float:left; margin:0; padding:0; width:100%; background:transparent;'>");
					contentSB.append(bottomRight);
				contentSB.append("</div>");
			contentSB.append("</div>");
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String pregnancyDetails() {
		StringBuilder contentSB = new StringBuilder();

		// Mothered children:
		
		boolean noPregnancies=true;

		contentSB.append("<div class='subTitle'>Mothered & Incubated Offspring</div>");
		
		if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)
				|| Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)){
			
			StringBuilder possibleFathersSB = new StringBuilder();
			int potentialFatherCount = 0;
			for(PregnancyPossibility pp : new ArrayList<>(Main.game.getPlayer().getPotentialPartnersAsMother())){
				if(pp.getFather()!=null) {
					if(potentialFatherCount>0) {
						possibleFathersSB.append("<br/>");
					}
					possibleFathersSB.append(UtilText.parse(pp.getFather(),
							"[npc.Name(A)] ("
								+ (!pp.getFather().getRaceStage().getName().isEmpty()
										?"<span style='color:"+pp.getFather().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getFather().getRaceStage().getName())+"</span> "
										:"")
								+ "<span style='color:"+pp.getFather().getRace().getColour().toWebHexString()+";'>"
								+ (pp.getFather().getGender().isFeminine()
										?Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularFemaleName(pp.getFather().getBody()))
										:Util.capitaliseSentence(pp.getFather().getSubspecies().getSingularMaleName(pp.getFather().getBody())))
								+ "</span>) Probability: "));
					
					if (pp.getProbability() <= 0) {
						possibleFathersSB.append("[style.italicsTerrible(None)]");
					} else if(pp.getProbability()<=0.15f) {
						possibleFathersSB.append("[style.italicsBad(Low)]");
					} else if(pp.getProbability()<=0.3f) {
						possibleFathersSB.append("[style.italicsMinorGood(Average)]");
					} else if(pp.getProbability()<1) {
						possibleFathersSB.append("[style.italicsGood(High)]");
					} else {
						possibleFathersSB.append("[style.italicsExcellent(Certainty)]");
					}
					
					possibleFathersSB.append("</b>");
					potentialFatherCount++;
				}
			}
			
			String stage = "";
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)) {
				stage = StatusEffect.PREGNANT_0.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_1)) {
				stage = StatusEffect.PREGNANT_1.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_2)) {
				stage = StatusEffect.PREGNANT_2.getName(Main.game.getPlayer());
			} else if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				stage = StatusEffect.PREGNANT_3.getName(Main.game.getPlayer());
			}
			
			contentSB.append(pregnancyRow("[style.boldBad(Ongoing Pregnancy)]", "[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]", "<b>Possible Fathers:</b>", possibleFathersSB.toString()));
			
			noPregnancies=false;
		}
		
		Map<SexAreaOrifice, List<AbstractStatusEffect>> incubationEffectMap = Util.newHashMapOfValues(
				new Value<>(SexAreaOrifice.VAGINA, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_WOMB_1, StatusEffect.INCUBATING_EGGS_WOMB_2, StatusEffect.INCUBATING_EGGS_WOMB_3)),
				new Value<>(SexAreaOrifice.ANUS, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_STOMACH_1, StatusEffect.INCUBATING_EGGS_STOMACH_2, StatusEffect.INCUBATING_EGGS_STOMACH_3)),
				new Value<>(SexAreaOrifice.NIPPLE, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_1, StatusEffect.INCUBATING_EGGS_NIPPLES_2, StatusEffect.INCUBATING_EGGS_NIPPLES_3)),
				new Value<>(SexAreaOrifice.NIPPLE_CROTCH, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)),
				new Value<>(SexAreaOrifice.SPINNERET, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_SPINNERET_1, StatusEffect.INCUBATING_EGGS_SPINNERET_2, StatusEffect.INCUBATING_EGGS_SPINNERET_3)));
		
		for(Entry<SexAreaOrifice, List<AbstractStatusEffect>> incubationEntry : incubationEffectMap.entrySet()) {
			if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(0))
					|| Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(1))
					|| Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(2))) {
				String stage = "";
				if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(0))) {
					stage = incubationEntry.getValue().get(0).getName(Main.game.getPlayer());
				} else if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(1))) {
					stage = incubationEntry.getValue().get(1).getName(Main.game.getPlayer());
				} else if(Main.game.getPlayer().hasStatusEffect(incubationEntry.getValue().get(2))) {
					stage = incubationEntry.getValue().get(2).getName(Main.game.getPlayer());
				}
				Litter litter = Main.game.getPlayer().getIncubationLitter(incubationEntry.getKey());
				contentSB.append(pregnancyRow("[style.boldBad(Ongoing)] [style.boldPurple(Incubation)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"Implanter: [style.colourExcellent(Yourself)]"
								:UtilText.parse(litter.getMother(), "Implanter: <span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Implanter: [style.colourDisabled(Unknown)]"),
						"Implanted in "
							+(incubationEntry.getKey()==SexAreaOrifice.VAGINA
								?"womb"
								:(incubationEntry.getKey()==SexAreaOrifice.ANUS || incubationEntry.getKey()==SexAreaOrifice.MOUTH
									?"stomach"
									:incubationEntry.getKey().getName(Main.game.getPlayer(), true)))
							+": "
							+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG),
						"[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]"));
				noPregnancies=false;
			}
		}
		
		
		// Birthed:
		
		if(!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				String unknownName = "[style.colourDisabled(Unknown)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getFatherName().equals("???")) {
							unknownName = "<span style='color:"+c.getFatherFemininity().getColour().toWebHexString()+";'>"+c.getFatherName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getFatherName().equals("???")) {
							unknownName = "<span style='color:"+o.getFatherFemininity().getColour().toWebHexString()+";'>"+o.getFatherName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				contentSB.append(pregnancyRow("[style.boldGood(Resolved Pregnancy)]",
						(litter.getFather()!=null
							?(litter.getFather().isPlayer()
								?"Father: [style.colourExcellent(Yourself)]"
								:UtilText.parse(litter.getFather(), "Father: <span style='color:"+litter.getFather().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Father: "+unknownName),
						"Conceived: "
							+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+" | Delivered: "
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"Birthed: "
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		if(!Main.game.getPlayer().getLittersIncubated().isEmpty()) {
			for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				contentSB.append(pregnancyRow("[style.boldGood(Resolved)] [style.boldPurple(Incubation)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"Implanter: [style.colourExcellent(Yourself)]"
								:UtilText.parse(litter.getMother(), "Implanter: <span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Implanter: [style.colourDisabled(Unknown)]"),
						"Implanted: "
								+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
								+" | Delivered: "
								+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"Birthed: "
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		
		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() +
					(Main.game.getPlayer().getLittersImplanted().isEmpty()
					?";'>You have never been pregnant...</span>"
					:";'>You have never given birth...</span>")
					+ "</div>");
		}
		
		
		// Fathered:
		noPregnancies=true;
		
		contentSB.append("<span style='height:16px;width:100%;float:left;'></span>");
		
		contentSB.append("<div class='subTitle'>Fathered & Implanted Offspring</div>");
		
		for(PregnancyPossibility pp : new ArrayList<>(Main.game.getPlayer().getPotentialPartnersAsFather())) {
			if(pp.getMother()!=null) {
				String impregnationChance = "";
				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					impregnationChance = "Probability of impregnation: ";
					if (pp.getProbability() <= 0) {
						impregnationChance += "[style.italicsTerrible(None)]";
					} else if(pp.getProbability()<=0.15f) {
						impregnationChance += "[style.italicsBad(Low)]";
					} else if(pp.getProbability()<=0.3f) {
						impregnationChance += "[style.italicsMinorGood(Average)]";
					} else if(pp.getProbability()<1) {
						impregnationChance += "[style.italicsGood(High)]";
					} else {
						impregnationChance += "[style.italicsExcellent(Certainty)]";
					}
				}
				
				String stage = "";
				if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_0)) {
					stage = StatusEffect.PREGNANT_0.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_1)) {
					stage = StatusEffect.PREGNANT_1.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_2)) {
					stage = StatusEffect.PREGNANT_2.getName(pp.getMother());
				} else if(pp.getMother().hasStatusEffect(StatusEffect.PREGNANT_3)) {
					stage = StatusEffect.PREGNANT_3.getName(pp.getMother());
				}
				
				String motherName = UtilText.parse(pp.getMother(), "[npc.Name(A)]")
						+" ("
						+ (!pp.getMother().getRaceStage().getName().isEmpty()
								?"<span style='color:"+pp.getMother().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(pp.getMother().getRaceStage().getName())+"</span> "
								:"")
						+ "<span style='color:"+pp.getMother().getRace().getColour().toWebHexString()+";'>"
						+ (pp.getMother().getGender().isFeminine()
								?Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularFemaleName(pp.getMother().getBody()))
								:Util.capitaliseSentence(pp.getMother().getSubspecies().getSingularMaleName(pp.getMother().getBody())))
						+ "</span>)";
				
				contentSB.append(pregnancyRow("[style.boldBad(Ongoing Pregnancy)]", "[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]", motherName, impregnationChance));
				
			}
			noPregnancies=false;
		}
		List<Litter> incubatorOngoingLitters = new ArrayList<>(Main.game.getPlayer().getLittersImplanted());
		incubatorOngoingLitters.removeIf(npc -> npc.getIncubator()==null || npc.getIncubator().getIncubatingLitters().isEmpty());
		Set<GameCharacter> incubatorCharacters = new HashSet<>();
		for(Litter litter : incubatorOngoingLitters) {
			incubatorCharacters.add(litter.getIncubator());
		}
		for(GameCharacter incubator : incubatorCharacters) {
			for(Entry<SexAreaOrifice, List<AbstractStatusEffect>> incubationEntry : incubationEffectMap.entrySet()) {
				Litter litter = incubator.getIncubatingLitters().get(incubationEntry.getKey());
				if(litter!=null) {
					if(incubator.hasStatusEffect(incubationEntry.getValue().get(0))
							|| incubator.hasStatusEffect(incubationEntry.getValue().get(1))
							|| incubator.hasStatusEffect(incubationEntry.getValue().get(2))) {
						String stage = "";
						if(incubator.hasStatusEffect(incubationEntry.getValue().get(0))) {
							stage = incubationEntry.getValue().get(0).getName(incubator);
						} else if(incubator.hasStatusEffect(incubationEntry.getValue().get(1))) {
							stage = incubationEntry.getValue().get(1).getName(incubator);
						} else if(incubator.hasStatusEffect(incubationEntry.getValue().get(2))) {
							stage = incubationEntry.getValue().get(2).getName(incubator);
						}
						contentSB.append(pregnancyRow("[style.boldBad(Ongoing)] [style.boldPurple(Incubation)]",
								UtilText.parse(incubator, "Incubator: <span style='color:"+incubator.getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"),
								"Implanted in "
									+(incubationEntry.getKey()==SexAreaOrifice.VAGINA
										?"womb"
										:(incubationEntry.getKey()==SexAreaOrifice.ANUS || incubationEntry.getKey()==SexAreaOrifice.MOUTH
											?"stomach"
											:incubationEntry.getKey().getName(incubator, true)))
									+": "
									+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG),
								"[style.italicsSex('"+Util.capitaliseSentence(stage)+"')]"));
						noPregnancies=false;
					}
				}
			}
		}
		
		if(!Main.game.getPlayer().getLittersFathered().isEmpty()) {
			for (Litter litter : Main.game.getPlayer().getLittersFathered()) {
				String unknownName = "[style.colourDisabled(Unknown)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getMotherName().equals("???")) {
							unknownName = "<span style='color:"+c.getMotherFemininity().getColour().toWebHexString()+";'>"+c.getMotherName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getMotherName().equals("???")) {
							unknownName = "<span style='color:"+o.getMotherFemininity().getColour().toWebHexString()+";'>"+o.getMotherName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				
				contentSB.append(pregnancyRow("[style.boldGood(Resolved Pregnancy)]",
						(litter.getMother()!=null
							?(litter.getMother().isPlayer()
								?"Mother: [style.colourExcellent(Yourself)]"
								:UtilText.parse(litter.getMother(), "Mother: <span style='color:"+litter.getMother().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Mother: "+unknownName),
						"Conceived: "
							+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+" | Delivered: "
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"Birthed: "
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		if(!Main.game.getPlayer().getLittersImplanted().isEmpty()) {
			List<Litter> incubatorCompletedLitters = new ArrayList<>(Main.game.getPlayer().getLittersImplanted());
			incubatorCompletedLitters.removeAll(incubatorOngoingLitters);
			for (Litter litter : incubatorCompletedLitters) {
				String unknownName = "[style.colourDisabled(Unknown)]";
				try {
					String offspring0 = litter.getOffspring().iterator().next();
					if(offspring0.contains("NPCOffspring")) {
						GameCharacter c = Main.game.getNPCById(offspring0);
						if(!c.getIncubatorName().equals("???")) {
							unknownName = "<span style='color:"+c.getIncubatorFemininity().getColour().toWebHexString()+";'>"+c.getIncubatorName()+"</span>";
						}
					} else {
						OffspringSeed o = Main.game.getOffspringSeedById(offspring0);
						if(!o.getIncubatorName().equals("???")) {
							unknownName = "<span style='color:"+o.getIncubatorFemininity().getColour().toWebHexString()+";'>"+o.getIncubatorName()+"</span>";
						}
					}
				} catch(Exception ex) {
				}
				
				contentSB.append(pregnancyRow("[style.boldGood(Resolved)] [style.boldPurple(Incubation)]",
						(litter.getIncubator()!=null
							?(litter.getIncubator().isPlayer()
								?"Incubator: [style.colourExcellent(Yourself)]"
								:UtilText.parse(litter.getIncubator(), "Incubator: <span style='color:"+litter.getIncubator().getFemininity().getColour().toWebHexString()+";'>[npc.name(A)]</span>"))
							:"Incubator: "+unknownName),
						"Implanted: "
							+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
							+" | Delivered: "
							+Units.date(litter.getBirthDate(), Units.DateType.LONG),
						"Birthed: "
							+litter.getBirthedDescription()));
			}
			noPregnancies=false;
		}
		
		if(noPregnancies){
			contentSB.append("<div class='subTitle'>"
					+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>You have never got anyone pregnant!</span>"
					+ "</div>");
		}
		

		return contentSB.toString();
	}
	
	private static String statHeader() {
		return "<div class='container-full-width' style='margin-bottom:0;'>"
					+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Attribute"
					+ "</div>"
					+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ "Value"
					+ "</div>"
					+ "<div style='float:left; width:30%; font-weight:bold; margin:0; padding:0;'>"
						+ "Description"
					+"</div>"
				+ "</div>";
	}

	private static String statRowHeader(Colour colour, String text) {
		return "<h6 style='color:"+colour.toWebHexString()+"; text-align:center; margin-bottom:0; padding-bottom:0;'>"+text+"</h6>";
	}
	
	private static int rowCount = 0;
	
	private static String statRow(String colourLeft, String left, Colour colourCentre, String centre, String colourRight, String right) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
				+ "<div style='color:"+colourLeft+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ left
				+ "</div>"
				+ "<div style='color:"+colourCentre.toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ centre
				+ "</div>"
				+ "<div style='color:"+colourRight+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
					+ right
				+ "</div>"
			+ "</div>";
	}
	
	private static String statRow(Colour colourLeft, String left, Colour colourCentre, String centre, Colour colourRight, String right) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='color:"+colourLeft.toWebHexString()+"; width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ left
					+ "</div>"
					+ "<div style='color:"+(centre.equalsIgnoreCase("unknown") || centre.equalsIgnoreCase("N/A")?PresetColour.TEXT_GREY:colourCentre).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ centre
					+ "</div>"
					+ "<div style='color:"+(right.equalsIgnoreCase("unknown") || right.equalsIgnoreCase("N/A")?PresetColour.TEXT_GREY:colourRight).toWebHexString()+"; width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
						+ right
					+ "</div>"
				+ "</div>";
	}

	private static String statRow(Colour colour, String text) {
		rowCount++;
		return "<div class='container-full-width inner' style='margin-bottom:0;"+(rowCount%2==0?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+">"
					+ "<div style='color:"+colour.toWebHexString()+"; width:100%; float:left; font-weight:bold; margin:0; padding:0; text-align:center;'>"
						+ text
					+ "</div>"
				+ "</div>";
	}

	private static String statAttributeHeader() {
		return "<div class='container-full-width' style='text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'><b>"
				
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:26%;'>"
						+ "Attribute"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:16%;'>"
						+ "Core | Bonus"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:20%;'>"
						+ "Value/Max."
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:36%;'>"
						+ "Description"
					+ "</div>"
					
				+ "</b></div>";
	}
	
	private static String getAttributeBox(GameCharacter owner, AbstractAttribute att, String effect) {
		return getAttributeBox(owner, att, effect, false);
	}
	
	private static String getAttributeBox(GameCharacter owner, AbstractAttribute att, String effect, boolean half) {
		float value = owner.getAttributeValue(att);

		String valueForDisplay;
		if(((int)value)==value) {
			valueForDisplay = String.valueOf(((int)value));
		} else {
			valueForDisplay = String.valueOf(value);
		}
		if(att.isInfiniteAtUpperLimit() && value>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(true);
		}
		if(att.isPercentage()){
			valueForDisplay = (value>=0?"+":"")+valueForDisplay+"%";
		}
		
		float bonusAttributeValue = owner.getBonusAttributeValue(att) + (att==Attribute.RESISTANCE_PHYSICAL?owner.getPhysicalResistanceAttributeFromClothingAndWeapons():0);
		
		return "<div class='container-full-width' style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
				
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:30%;'>"
						+ "<b style='color:" + att.getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(att.getName())+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:6%;text-align:right;'>"
						+(owner.getBaseAttributeValue(att) > 0 
								? "<b style='color:" + PresetColour.GENERIC_MINOR_GOOD.getShades()[1] + ";"
								: (owner.getBaseAttributeValue(att) < 0
									? "<b style='color:" + PresetColour.GENERIC_MINOR_BAD.getShades()[1] + ";"
									: "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(owner.getBaseAttributeValue(att), 0, 1)
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:6%;text-align:left;'>"
						+" | "	
						+ (bonusAttributeValue > 0 
								? "<b style='color:" + PresetColour.GENERIC_MINOR_GOOD.getShades()[1] + ";"
								: (bonusAttributeValue < 0
									? "<b style='color:" + PresetColour.GENERIC_MINOR_BAD.getShades()[1] + ";"
									: "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";"))+"'>"
							+Units.number(bonusAttributeValue, 0, 1)
						+"</b>"
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:right;'>"
						+ "<b style='color:"+att.getColour().toWebHexString()+";'>"
							+ valueForDisplay
						+"</b>"
					+ "</div>"

					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:8%;text-align:left;'>"
						+ "/"
						+ (value>=att.getUpperLimit() || (att==Attribute.MAJOR_CORRUPTION && value==0)
								?"[style.boldGood("+att.getUpperLimit()+")]"
								:"[style.boldDisabled("+att.getUpperLimit()+")]")
					+ "</div>"
						
					+ "<div class='container-full-width' style='background:transparent;margin:0;padding:0;width:40%;'>"
						+ "[style.italicsDisabled("+effect.replaceAll("<br/>", " ")+")]"
					+ "</div>"
					
				+ "</div>";
	}

	private static String getContactEntry(GameCharacter contact) {
		boolean isOffspring = contact.getMotherId().equals(Main.game.getPlayer().getId()) || contact.getFatherId().equals(Main.game.getPlayer().getId());
		
		return UtilText.parse(contact, "<b style='color:"+contact.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"+(isOffspring
				?(contact.isFeminine()
						?", your [style.colourFeminine(daughter)]"
						:", your [style.colourMasculine(son)]")
				:"")
				+". [npc.She] is "
				+ (contact.getRaceStage()==RaceStage.HUMAN || contact.isRaceConcealed()
					?"[npc.a_race(true)]"
					:"[npc.a_raceStage(true)] [npc.race(true)]")
				+", whose current location is: "
				+ (contact.getWorldLocation()==WorldType.EMPTY
					?"[style.italicsDisabled(Unknown!)]"
					:"<i>"+contact.getWorldLocation().getName()+", "+contact.getLocationPlace().getPlaceType().getName()+"</i>."));
	}
	
	public static final DialogueNode CONTACTS = new DialogueNode("Contacts", "Look at your contacts.", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			for (int i = 0; i < charactersEncountered.size(); i++) {
				GameCharacter npc = charactersEncountered.get(i);
				
				UtilText.nodeContentSB.append("<p>"
												+ getContactEntry(npc)
											+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else if (index <= charactersEncountered.size()) {
				GameCharacter npc = charactersEncountered.get(index-1);
				boolean isOffspring = npc.getMotherId().equals(Main.game.getPlayer().getId()) || npc.getFatherId().equals(Main.game.getPlayer().getId());
				return new Response(
						UtilText.parse(npc, isOffspring
								?(npc.isFeminine()?"[style.colourFeminine([npc.Name])]":"[style.colourMasculine([npc.Name])]")
								:"[npc.Name]"),
						getContactEntry(npc),
						CONTACTS_CHARACTER){
					@Override
					public void effects() {
						CharactersPresentDialogue.resetContent(npc);
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CONTACTS_CHARACTER = new DialogueNode("Contacts", "Look at your contacts.", true) {

		@Override
		public String getLabel() {
			return CharactersPresentDialogue.characterViewed.getName(true);
		}

		@Override
		public String getContent() {
			return CharactersPresentDialogue.menuContent;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else if (index <= Main.game.getPlayer().getCharactersEncountered().size()) {
				try {
					GameCharacter npc = Main.game.getNPCById(Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return new Response(Util.capitaliseSentence(npc.getName(true)),
							UtilText.parse(npc, "Take a detailed look at what [npc.name] looks like."),
							CONTACTS_CHARACTER){
						@Override
						public void effects() {
							CharactersPresentDialogue.resetContent(npc);
							
						}
					};
				} catch (Exception e) {
					Util.logGetNpcByIdError("CONTACTS_CHARACTER.getResponse()", Main.game.getPlayer().getCharactersEncountered().get(index - 1));
					return null;
				}
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static String getSubspeciesDiscoveredIndication() {
		return Main.getProperties().getSubspeciesDiscoveredCount()+"/"+Subspecies.getAllSubspecies().size();
	}

	private static String getWeaponsDiscoveredIndication() {
		int size = weaponsDiscoveredList.size();
		return Math.min(size, Main.getProperties().getWeaponsDiscoveredCount())+"/"+size;
	}
	
	private static String getClothingDiscoveredIndication() {
		int size = clothingDiscoveredList.size();
		return Math.min(size, Main.getProperties().getClothingDiscoveredCount())+"/"+size;
	}
	
	private static String getItemsDiscoveredIndication() {
		int size = itemsDiscoveredList.size();
		return Math.min(size, Main.getProperties().getItemsDiscoveredCount())+"/"+size;
	}
	
	public static final DialogueNode ENCYCLOPEDIA = new DialogueNode("Encyclopedia", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "You have collected information on all the races, weapons, clothing, and items that you've encountered in your travels."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>");
				sb.append("You have discovered:");
				sb.append(Main.getProperties().getSubspeciesDiscoveredCount()==Subspecies.getAllSubspecies().size()
								?"<br/>[style.colourGood(Subspecies: "+getSubspeciesDiscoveredIndication()+")]"
								:"<br/>Subspecies: "+getSubspeciesDiscoveredIndication());

				int size = weaponsDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getWeaponsDiscoveredCount())==weaponsDiscoveredList.size()
								?"<br/>[style.colourGood(Weapons: "+getWeaponsDiscoveredIndication()+")]"
								:"<br/>Weapons: "+getWeaponsDiscoveredIndication());
				
				size = clothingDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getClothingDiscoveredCount())==clothingDiscoveredList.size()
								?"<br/>[style.colourGood(Clothing: "+getClothingDiscoveredIndication()+")]"
								:"<br/>Clothing: "+getClothingDiscoveredIndication());
				
				size = itemsDiscoveredList.size();
				sb.append(Math.min(size, Main.getProperties().getItemsDiscoveredCount())==itemsDiscoveredList.size()
								?"<br/>[style.colourGood(Items: "+getItemsDiscoveredIndication()+")]"
								:"<br/>Items: "+getItemsDiscoveredIndication());
			sb.append("</p>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newRaceDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Races</span>":"Races",
						"Have a look at all the different races that you've encountered in your travels.", RACES){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
					}
				};

			} else if (index == 2) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Weapons</span>":"Weapons",
						"Have a look at all the different weapons that you've encountered in your travels.", WEAPON_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
					}
				};

			} else if (index == 3) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newClothingDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Clothing</span>":"Clothing",
						"Have a look at all the different clothing that you've encountered in your travels.", CLOTHING_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
						clothingSlotKey = clothingSlotCategories.keySet().iterator().next();
					}
				};

			} else if (index == 4) {
				return new Response((Main.getProperties().hasValue(PropertyValue.newItemDiscovered))?"<span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>Items</span>":"Items",
						"Have a look at all the different items that you've encountered in your travels.", ITEM_CATALOGUE){
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
					}
				};

			} else if (index == 5) {
				if(!Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
						&& !Main.getProperties().hasValue(PropertyValue.newRaceDiscovered)) {
					return new Response("Clear alerts", "Clears encyclopedia alerts.<br/><i>You currently do not have any encyclopedia alerts to clear...</i>", null);
					
				} else {
					return new ResponseEffectsOnly("Clear alerts",
							"Clears encyclopedia alerts."){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						@Override
						public void effects() {
							Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
							Main.getProperties().setValue(PropertyValue.newRaceDiscovered, false);
						}
					};
				}
				
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<AbstractItemType> itemsDiscoveredList = new ArrayList<>();
	private static List<AbstractClothingType> clothingDiscoveredList = new ArrayList<>();
	private static List<AbstractWeaponType> weaponsDiscoveredList = new ArrayList<>();
	
	private static Map<String, List<InventorySlot>> clothingSlotCategories;
	private static String clothingSlotKey;
	
	static {
		itemsDiscoveredList.addAll(ItemType.getAllItems());
		itemsDiscoveredList.removeIf((it) -> it.getItemTags().contains(ItemTag.CHEAT_ITEM) || it.getItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(itemsDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		weaponsDiscoveredList.addAll(WeaponType.getAllWeapons());
		weaponsDiscoveredList.removeIf((wt) -> wt.getItemTags().contains(ItemTag.CHEAT_ITEM) || wt.getItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(weaponsDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		clothingDiscoveredList.addAll(ClothingType.getAllClothing());
		clothingDiscoveredList.removeIf((ct) -> ct.getDefaultItemTags().contains(ItemTag.CHEAT_ITEM) || ct.getDefaultItemTags().contains(ItemTag.SILLY_MODE));
		Collections.sort(clothingDiscoveredList, (i1, i2) -> i1.getRarity().compareTo(i2.getRarity()));
		
		clothingSlotCategories = new LinkedHashMap<>();
		
		clothingSlotCategories.put("Head",
				Util.newArrayListOfValues(
				InventorySlot.HEAD,
				InventorySlot.EYES,
				InventorySlot.HAIR,
				InventorySlot.MOUTH,
				InventorySlot.NECK));
		
		clothingSlotCategories.put("Torso",
				Util.newArrayListOfValues(
				InventorySlot.TORSO_OVER,
				InventorySlot.TORSO_UNDER));
		
		clothingSlotCategories.put("Chest",
				Util.newArrayListOfValues(
				InventorySlot.CHEST,
				InventorySlot.NIPPLE));
		
		clothingSlotCategories.put("Arms",
				Util.newArrayListOfValues(
				InventorySlot.HAND,
				InventorySlot.WRIST,
				InventorySlot.FINGER));
		
		clothingSlotCategories.put("Waist",
				Util.newArrayListOfValues(
				InventorySlot.STOMACH,
				InventorySlot.HIPS));
		
		clothingSlotCategories.put("Groin",
				Util.newArrayListOfValues(
				InventorySlot.GROIN,
				InventorySlot.PENIS,
				InventorySlot.VAGINA,
				InventorySlot.ANUS));
		
		clothingSlotCategories.put("Legs",
				Util.newArrayListOfValues(
				InventorySlot.LEG,
				InventorySlot.SOCK));
		
		clothingSlotCategories.put("Feet",
				Util.newArrayListOfValues(
				InventorySlot.FOOT,
				InventorySlot.ANKLE));
		
		clothingSlotCategories.put("Extra",
				Util.newArrayListOfValues(
				InventorySlot.HORNS,
				InventorySlot.WINGS,
				InventorySlot.TAIL));
		
		clothingSlotCategories.put("Piercings",
				Util.newArrayListOfValues(
				InventorySlot.PIERCING_EAR,
				InventorySlot.PIERCING_LIP,
				InventorySlot.PIERCING_NOSE,
				InventorySlot.PIERCING_TONGUE,
				InventorySlot.PIERCING_NIPPLE,
				InventorySlot.PIERCING_STOMACH,
				InventorySlot.PIERCING_PENIS,
				InventorySlot.PIERCING_VAGINA));
	}
	
	public static final DialogueNode WEAPON_CATALOGUE = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Discovered Weapons ("+getWeaponsDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			StringBuilder sbMelee = new StringBuilder();
			StringBuilder sbRanged = new StringBuilder();
			StringBuilder sbDamageTypes = new StringBuilder();

			int meleeCount = 0;
			int meleeKnownCount = 0;
			int rangedCount = 0;
			int rangedKnownCount = 0;
			
			for(AbstractWeaponType weaponType : weaponsDiscoveredList) {
				boolean discovered = Main.getProperties().isWeaponDiscovered(weaponType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+"; width:78%; margin:1%; padding:0; '>"
									+ "<div class='inventory-icon-content'>"+(discovered?weaponType.getSVGImage():"")+"</div>"
									+ "<div class='overlay"+(discovered?"' id='"+weaponType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
								+ "</div>";
				sbDamageTypes.setLength(0);
				if(discovered) {
					for(DamageType dt : weaponType.getAvailableDamageTypes()) {
						sbDamageTypes.append("<div class='square-button' "+(discovered?"id='"+(weaponType.getId()+"_"+dt.toString())+"'":"")
												+ " style='cursor:default; width:18%; margin:1%; padding:0; background-color:"+dt.getMultiplierAttribute().getColour().toWebHexString()+";'>"
											+ "</div>");
					}
				}
				if(weaponType.isMelee()) {
					meleeCount++;
					if(discovered) {
						meleeKnownCount++;
					}
					sbMelee.append("<div class='container-full-width' style='width:11.5%; padding:0; margin:0.5%;'>");
						sbMelee.append(entry);
						sbMelee.append(sbDamageTypes.toString());
					sbMelee.append("</div>");
					
				} else {
					rangedCount++;
					if(discovered) {
						rangedKnownCount++;
					}
					sbRanged.append("<div class='container-full-width' style='width:11.5%; padding:0; margin:0.5%;'>");
						sbRanged.append(entry);
						sbRanged.append(sbDamageTypes.toString());
					sbRanged.append("</div>");
				}
			}
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldBlue(Melee Weapons ("+meleeKnownCount+"/"+meleeCount+"))]");
				sb.append("</p>");
				sb.append(sbMelee.toString());
			sb.append("</div>");
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldYellow(Ranged Weapons ("+rangedKnownCount+"/"+rangedCount+"))]");
				sb.append("</p>");
				sb.append(sbRanged.toString());
			sb.append("</div>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode CLOTHING_CATALOGUE = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Discovered Clothing ("+getClothingDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<InventorySlot> slots = clothingSlotCategories.get(clothingSlotKey);
			Map<InventorySlot, StringBuilder> sbMap = new LinkedHashMap<>();
			Map<InventorySlot, Value<Integer, Integer>> discoveredMap = new HashMap<>();
			for(InventorySlot slot : slots) {
				sbMap.put(slot, new StringBuilder());
				discoveredMap.put(slot, new Value<>(0, 0));
			}
			
			for(AbstractClothingType clothingType : clothingDiscoveredList) {
				if(Collections.disjoint(clothingType.getEquipSlots(), slots)) {
					continue;
				}
				boolean discovered = Main.getProperties().isClothingDiscovered(clothingType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+"; width:8%;'>"
									+ "<div class='inventory-icon-content'>"+(discovered?clothingType.getSVGImageRandomColour(true, false, false):"")+"</div>"
									+ "<div class='overlay"+(discovered?"' id='"+clothingType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
								+ "</div>";
				
				for(InventorySlot slot : clothingType.getEquipSlots()) {
					if(slots.contains(slot)) {
						sbMap.get(slot).append(entry);
						discoveredMap.put(slot, new Value<>(discoveredMap.get(slot).getKey()+(discovered?1:0), discoveredMap.get(slot).getValue()+1));
					}
				}
			}

			for(InventorySlot slot : slots) {
				sb.append("<div class='container-full-width'>");
					sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
						sb.append("[style.boldYellowLight("+Util.capitaliseSentence(slot.getName())+" ("+discoveredMap.get(slot).getKey()+"/"+discoveredMap.get(slot).getValue()+"))]");
					sb.append("</p>");
					sb.append(sbMap.get(slot).toString());
				sb.append("</div>");
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else {
				List<Response> responses = new ArrayList<>();
				for(Entry<String, List<InventorySlot>> entry : clothingSlotCategories.entrySet()) {
					if(clothingSlotKey==entry.getKey()) {
						responses.add(new Response(entry.getKey(), "You are already viewing this category!", null));
						
					} else {
						responses.add(new Response(entry.getKey(), "View all clothing which fits into the following slots:<br/><i>"+Util.capitaliseSentence(Util.inventorySlotsToStringList(entry.getValue()))+".</i>", CLOTHING_CATALOGUE) {
							@Override
							public void effects() {
								clothingSlotKey = entry.getKey();
							}
						});
					}
				}
				if(index-1<responses.size()) {
					return responses.get(index-1);
				}
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	public static final DialogueNode ITEM_CATALOGUE = new DialogueNode("", "View discovered items", true) {
		@Override
		public String getLabel() {
			return "Discovered Items ("+getItemsDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			StringBuilder sbItems = new StringBuilder();
			StringBuilder sbBooks = new StringBuilder();
			StringBuilder sbEssences = new StringBuilder();
			StringBuilder sbSpells = new StringBuilder();
			
			int itemCount = 0;
			int itemKnownCount = 0;
			int bookCount = 0;
			int bookKnownCount = 0;
			int essenceCount = 0;
			int essenceKnownCount = 0;
			int spellCount = 0;
			int spellKnownCount = 0;
			
			for(AbstractItemType itemType : itemsDiscoveredList) {
				boolean discovered = Main.getProperties().isItemDiscovered(itemType);
				String entry = "<div class='inventory-item-slot unequipped' style='background-color:"+itemType.getRarity().getBackgroundColour().toWebHexString()+"; width:8%;'>"
									+ "<div class='inventory-icon-content'>"+(discovered?itemType.getSVGString():"")+"</div>"
									+ "<div class='overlay"+(discovered?"' id='"+itemType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
								+ "</div>";
				
				if(itemType.getItemTags().contains(ItemTag.BOOK)) {
					sbBooks.append(entry);
					bookCount++;
					if(discovered) {
						bookKnownCount++;
					}
					
				} else if(itemType.getItemTags().contains(ItemTag.ESSENCE)) {
					sbEssences.append(entry);
					essenceCount++;
					if(discovered) {
						essenceKnownCount++;
					}
					
				} else if(itemType.getItemTags().contains(ItemTag.SPELL_BOOK) || itemType.getItemTags().contains(ItemTag.SPELL_SCROLL)) {
					sbSpells.append(entry);
					spellCount++;
					if(discovered) {
						spellKnownCount++;
					}
					
				} else {
					sbItems.append(entry);
					itemCount++;
					if(discovered) {
						itemKnownCount++;
					}
				}
			}
			
			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldBlueLight(Items ("+itemKnownCount+"/"+itemCount+"))]");
				sb.append("</p>");
				sb.append(sbItems.toString());
			sb.append("</div>");

			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldOrange(Books ("+bookKnownCount+"/"+bookCount+"))]");
				sb.append("</p>");
				sb.append(sbBooks.toString());
			sb.append("</div>");

			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldArcane(Essences ("+essenceKnownCount+"/"+essenceCount+"))]");
				sb.append("</p>");
				sb.append(sbEssences.toString());
			sb.append("</div>");

			sb.append("<div class='container-full-width'>");
				sb.append("<p style='width:100%; text-align:center; padding:0 margin:0;'>");
					sb.append("[style.boldSpells(Spells ("+spellKnownCount+"/"+spellCount+"))]");
				sb.append("</p>");
				sb.append(sbSpells.toString());
			sb.append("</div>");
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	private static List<AbstractRace> racesDiscovered = new ArrayList<>();
	private static List<AbstractSubspecies> subspeciesDiscovered = new ArrayList<>();
	private static AbstractRace raceSelected;
	private static AbstractSubspecies subspeciesSelected;
	private static StringBuilder subspeciesSB = new StringBuilder();
	
	public static void resetContentForRaces() {
		
		subspeciesDiscovered.clear();
		
		for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			if(Main.getProperties().isRaceDiscovered(subspecies)) {
				AbstractRace race = subspecies.getRace();
				if(!racesDiscovered.contains(race)) {
					racesDiscovered.add(race);
				}
				subspeciesDiscovered.add(subspecies);
			}
		}
		
		racesDiscovered.sort((a, b) -> a.getName(false).compareTo(b.getName(false)));
		subspeciesDiscovered.sort((a, b) -> a.getName(null).compareTo(b.getName(null)));
		
	}

	public static final DialogueNode RACES = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return "Discovered Races ("+getSubspeciesDiscoveredIndication()+")";
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
						+ "You have encountered the following races in your travels:<br/>"
						+ "(Discovered races are [style.boldGood(highlighted)], while undiscovered races are [style.colourDisabled(greyed out)].)"
					+ "</p>");
			List<AbstractRace> sortedRaces = new ArrayList<>();
			sortedRaces.addAll(Race.getAllRaces());
			sortedRaces.remove(Race.NONE);
			sortedRaces.sort((r1, r2) -> r1.getName(false).compareTo(r2.getName(false)));
			for(AbstractRace race : sortedRaces) {
				UtilText.nodeContentSB.append("<div style='box-sizing: border-box; text-align:center; width:50%; padding:8px; margin:0; float:left;'>");
				if(racesDiscovered.contains(race)) {
					UtilText.nodeContentSB.append("<b style='color:"+race.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(race.getName(false)) + "</b>");
				} else {
					UtilText.nodeContentSB.append("[style.colourDisabled(" + Util.capitaliseSentence(race.getName(false)) + ")]");
				}
				UtilText.nodeContentSB.append("</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the encyclopedia.", ENCYCLOPEDIA);
			
			} else if (index <= racesDiscovered.size()) {
				return new Response(Util.capitaliseSentence(racesDiscovered.get(index - 1).getName(false)),
						"Take a look at all the subspecies of the race: '" + racesDiscovered.get(index - 1).getName(false) + "'",
						SUBSPECIES){
					@Override
					public void effects() {
						raceSelected = racesDiscovered.get(index - 1);
						subspeciesSelected = AbstractSubspecies.getMainSubspeciesOfRace(raceSelected);
						if(!subspeciesDiscovered.contains(subspeciesSelected)) {
							for(AbstractSubspecies sub : subspeciesDiscovered) {
								if(sub.getRace()==raceSelected) {
									subspeciesSelected = sub;
									break;
								}
							}
						}
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	private static List<String> getSubspeciesModifiersAsStringList(AbstractSubspecies subspecies) {
		LinkedHashMap<AbstractAttribute, Float> attMods;

		attMods = new LinkedHashMap<>(subspecies.getStatusEffectAttributeModifiers(null));
		
		ArrayList<String> fullModList = new ArrayList<>(getSubspeciesAttributeModifiersToStringList(attMods));
		fullModList.addAll(subspecies.getExtraEffects(null));
		
		if(subspecies.isFeralConfigurationAvailable()) {
			fullModList.add("<br/><b>Additional bonuses when in [style.boldFeral(feral form)]:</b>");
			
			for(String s : subspecies.getFeralEffects()) {
				fullModList.add(s);
			}
		}
		
		return fullModList;
	}

	private static List<String> getSubspeciesAttributeModifiersToStringList(Map<AbstractAttribute, Float> attributeMap) {
		List<String> attributeModifiersList = new ArrayList<>();
		if (attributeMap != null) {
			for (Entry<AbstractAttribute, Float> e : attributeMap.entrySet()) {
				attributeModifiersList.add(e.getKey().getFormattedValue(e.getValue()));
			}
		}
		return attributeModifiersList;
	}
	
	public static final DialogueNode SUBSPECIES = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return Util.capitaliseSentence(subspeciesSelected.getName(null));
		}
		@Override
		public String getContent() {
			subspeciesSB.setLength(0);
			
			Body femaleBody = Main.game.getCharacterUtils().generateBody(null, Gender.F_V_B_FEMALE, subspeciesSelected, RaceStage.GREATER);
			Body maleBody = Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspeciesSelected, RaceStage.GREATER);
			
			subspeciesSB.append(
				"<div class='container-full-width' style='width:40%; float:right;'>"
					+ "<p style='width:100%; text-align:center;'>"
						+ "<b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b>"
						+ "<br/>Average stats"
					+ "</p>"
					+ "<table align='center'>"
						+ "<tr>"
							+ "<td></td>"
							+ "<td>[style.colourFeminine("+Util.capitaliseSentence(subspeciesSelected.getSingularFemaleName(null))+")]</td>"
							+ "<td>[style.colourMasculine("+Util.capitaliseSentence(subspeciesSelected.getSingularMaleName(null))+")]</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Height</td>"
							+ "<td>"+Units.size(femaleBody.getHeightValue())+"</td>"
							+ "<td>"+Units.size(maleBody.getHeightValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Femininity</td>"
							+ "<td>"+femaleBody.getFemininity()+"</td>"
							+ "<td>"+maleBody.getFemininity()+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Breast size</td>"
							+ "<td>"+(femaleBody.getBreast().getRawSizeValue()==0
										?"Flat"
										:femaleBody.getBreast().getSize().getCupSizeName()+"-cup")+"</td>"
							+ "<td>"+(maleBody.getBreast().getRawSizeValue()==0
										?"Flat"
										:maleBody.getBreast().getSize().getCupSizeName()+"-cup")+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Penis length</td>"
							+ "<td>-</td>"
							+ "<td>"+Units.size(maleBody.getPenis().getRawLengthValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Penis diameter</td>"
							+ "<td>-</td>"
							+ "<td>"+Units.size(maleBody.getPenis().getRawGirthValue())+"</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Vagina capacity</td>"
							+ "<td>"+Util.capitaliseSentence(femaleBody.getVagina().getOrificeVagina().getCapacity().getDescriptor())+"</td>"
							+ "<td>-</td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td>Litter size</td>"
							+ "<td>"+subspeciesSelected.getRace().getNumberOfOffspringLow()+"-"+subspeciesSelected.getRace().getNumberOfOffspringHigh()+"</td>"
							+ "<td>-</td>"
						+ "</tr>"
					+ "</table>"
				+ "</div>");
					
			subspeciesSB.append("<p>"
					+ "<b style='color:"+subspeciesSelected.getColour(null).toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getName(null))+"</b>"
					+ (AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==subspeciesSelected
							?" ([style.colourMinorGood(Core)] subspecies of <span style='color:"+raceSelected.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceSelected.getName(false))+"</span>)"
							:" (Subspecies of <span style='color:"+raceSelected.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceSelected.getName(false))+"</span>)")
					+ "<br/>"
					+ "Masculine: <span style='color:"+Femininity.valueOf(maleBody.getFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getSingularMaleName(null))+"</span>"
					+ "<br/>"
					+ "Feminine: <span style='color:"+Femininity.valueOf(femaleBody.getFemininity()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(subspeciesSelected.getSingularFemaleName(null))+"</span>");

			subspeciesSB.append("<br/><br/>"
					+ "<b>Race bonuses:</b>");
			for(String s : getSubspeciesModifiersAsStringList(subspeciesSelected)) {
				subspeciesSB.append("<br/>");
				subspeciesSB.append(s);
			}
			
			subspeciesSB.append("<br/><br/>"
					+ "<i>"+subspeciesSelected.getDescription(null)+"</i>"
				+ "</p>");
					
			subspeciesSB.append("<h6>"+Util.capitaliseSentence(raceSelected.getName(false))+" Lore</h6>"
					+subspeciesSelected.getBasicDescription(null)
					+ (Main.getProperties().isAdvancedRaceKnowledgeDiscovered(subspeciesSelected)
						?subspeciesSelected.getAdvancedDescription(null)
						:"<p style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
							+ "Further information can be discovered in books!"
						+ "</p>"));
			
			return subspeciesSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<AbstractSubspecies> raceSubspecies = Subspecies.getSubspeciesOfRace(raceSelected);
			
			if (index == 0) {
				return new Response("Back", "Return to the race selection screen.", RACES);
			
			} else if (index <= raceSubspecies.size()) {
				AbstractSubspecies indexSubspecies = raceSubspecies.get(index - 1);
				if(!subspeciesDiscovered.contains(indexSubspecies)) {
					return new Response(Util.capitaliseSentence(indexSubspecies.getName(null)),
							"You haven't discovered this subspecies yet!",
							null);
				}
				return new Response(Util.capitaliseSentence(indexSubspecies.getName(null)),
						"Take a detailed look at what " + indexSubspecies.getNamePlural(null) + " are like."
						+ (AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies
							?"<br/>This is the [style.colourMinorGood(core)] "+raceSelected.getName(false)+" subspecies."
							:""),
						SUBSPECIES){
					@Override
					public Colour getHighlightColour() {
						if(AbstractSubspecies.getMainSubspeciesOfRace(raceSelected)==indexSubspecies) {
							return PresetColour.GENERIC_MINOR_GOOD;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						subspeciesSelected = indexSubspecies;
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode CHARACTER_PERK_TREE = new DialogueNode("Perk Tree", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details>"
							+ "<summary>[style.boldPerk(Perk & Trait Information)]</summary>"
							+ "[style.colourPerk(Perks)] (circular icons) apply permanent boosts to your attributes.<br/>"
							+ "[style.colourPerk(Traits)] (square icons) provide unique effects for your character."
								+ " Unlike perks, <b>traits will have no effect on your character until they're slotted into your 'Active Traits' bar</b>.<br/>"
							+ "Perks require perk points to unlock. You earn one perk point each time you level up, and earn an extra two perk points every five levels.<br/><br/>"
							+ "In addition to the perks that can be purchased via perk points, there are also several special, hidden perks that are unlocked via special events."
					+ "</details>");
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(Main.game.getPlayer(), true));
			
			UtilText.nodeContentSB.append("</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
						+ "[style.italicsBad(Please note that this perk tree is a work-in-progress. This is not the final version, and is just a proof of concept!)]"
					+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reset", "Reset all perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)", CHARACTER_PERK_TREE) {
					@Override
					public void effects() {
						Main.game.getPlayer().resetPerksMap(false, false);
					}
				};
				
				
			} else if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.levelUpHightlight, false);
					}
				};
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	
//	private static boolean confirmReset = false;
	public static final DialogueNode CHARACTER_FETISHES = new DialogueNode("Desires & Fetishes", "", true) {

		@Override
		public String getContent() {
			journalSB = new StringBuilder(
					"<details>"
						+ "<summary>[style.boldFetish(Fetish Information)]</summary>"
							+ "You can select your [style.colourLust(desire)] for each fetish [style.colourArcane(for free)],"
							+ " or choose to take the associated [style.colourFetish(fetish)] for a cost of [style.colourArcane(arcane essences)].<br/><br/>"
							+ "Choosing a desire will affect bonus lust gains in sex, while taking a fetish will permanently lock your desire to 'love', and also give you special bonuses."
							+ " Fetishes can only be removed through enchanted potions.<br/><br/>"
							+ "Your currently selected desire has a "+PresetColour.FETISH.getName()+" border, but your true desire (indicated by the coloured desire icon) may be modified by enchanted clothes or other items.<br/><br/>"
							+ "You earn experience for each fetish through performing related actions in sex."
							+ " Experience is earned regardless of whether or not you have the associated fetish."
							+ " Higher level fetishes will cause both you and your partner to gain more arousal from related sex actions, as well as increase the fetish's bonuses.<br/><br/>"
							+ "Finally, derived fetishes cannot be directly unlocked, but are instead automatically applied when you meet their requirements."
					+ "</details>");
			
			// Normal fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>Fetishes</h6></div>");
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_PENIS_GIVING, Fetish.FETISH_PENIS_RECEIVING));
			if(Main.game.isAnalContentEnabled()) {
				journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING));
			}
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF));
			if(Main.game.isLactationContentEnabled()) {
				journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF));
			}
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			if(Main.game.isFootContentEnabled()) {
				journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING));
			}
			if(Main.game.isArmpitContentEnabled()) {
				journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_ARMPIT_GIVING, Fetish.FETISH_ARMPIT_RECEIVING));
			}
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_KINK_GIVING, Fetish.FETISH_KINK_RECEIVING));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_SADIST, Fetish.FETISH_MASOCHIST));
			if(Main.game.isNonConEnabled()) {
				journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_NON_CON_SUB));
			}

			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_BONDAGE_APPLIER, Fetish.FETISH_BONDAGE_VICTIM));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_DENIAL, Fetish.FETISH_DENIAL_SELF));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_VOYEURIST, Fetish.FETISH_EXHIBITIONIST));
			journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_BIMBO, Fetish.FETISH_CROSS_DRESSER));
			if(Main.game.isIncestEnabled()) {
				journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_MASTURBATION, Fetish.FETISH_INCEST));
				if(Main.game.isPenetrationLimitationsEnabled()) {
					journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_SIZE_QUEEN, null));
				}
			} else {
				if(Main.game.isPenetrationLimitationsEnabled()) {
					journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_MASTURBATION, Fetish.FETISH_SIZE_QUEEN));
				} else {
					journalSB.append(getFetishEntry(Main.game.getPlayer(), Fetish.FETISH_MASTURBATION, null));
				}
			}
			
			// Derived fetishes:

			journalSB.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Derived Fetishes</h6></div>");
			journalSB.append("<div class='fetish-container'>");
			
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					journalSB.append(
							"<div id='fetishUnlock" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:2px solid " + PresetColour.FETISH.getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " +  PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString(Main.game.getPlayer())+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
									? ""
									: (fetish.isAvailable(Main.game.getPlayer())
											? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
											: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
							+ "</div>");
				}
			}
			
			// Free Fetishes:
			
			journalSB.append("</div>");
			
			
			return journalSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to your phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	
	public static String getFetishEntry(GameCharacter targetedCharacter, AbstractFetish othersFetish, AbstractFetish selfFetish) {
		return "<div class='container-full-width' style='background:transparent; margin:2px 0; width:100%;'>"
					+ getIndividualFetishEntry(targetedCharacter, othersFetish)
					+ (selfFetish==null?"":getIndividualFetishEntry(targetedCharacter, selfFetish))
				+ "</div>";
	}
	
	private static String getIndividualFetishEntry(GameCharacter targetedCharacter, AbstractFetish fetish) {
		FetishLevel level = FetishLevel.getFetishLevelFromValue(targetedCharacter.getFetishExperience(fetish));
		float experiencePercentage = ((targetedCharacter.getFetishExperience(fetish)) / (float)(level.getMaximumExperience()))*100;
		
		return "<div class='container-half-width' style='margin:0 8px;'>"
					+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
						+ (targetedCharacter.hasFetish(fetish)
								?"[style.colourPink("+Util.capitaliseSentence(fetish.getName(targetedCharacter))+" "+level.getNumeral()+")]"
								:Util.capitaliseSentence(fetish.getName(targetedCharacter))+" "+level.getNumeral())
						+"<div class='container-full-width' style='margin:2px 0; padding:0; width:100%;'></div>" // Spacer
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.ZERO_HATE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.ONE_DISLIKE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.TWO_NEUTRAL)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.THREE_LIKE)
						+getFetishDesireEntry(targetedCharacter, fetish, FetishDesire.FOUR_LOVE)
					+ "</div>"
					+"<div class='container-full-width' style='margin:0 8px; width: calc(22% - 16px);'>"
						+ "<div id='fetishUnlock" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon full" + (targetedCharacter.hasFetish(fetish)
							? " owned' style='border:2px solid " + PresetColour.FETISH.toWebHexString() + ";'>"
							: (fetish.isAvailable(targetedCharacter)
									? " unlocked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + PresetColour.TEXT_GREY_DARK.toWebHexString() + ";'>"))
										+ "<div class='fetish-icon-content'>"+fetish.getSVGString(targetedCharacter)+"</div>"
										+ "<div style='width:40%;height:40%;position:absolute;top:0;right:4px;'>"+level.getSVGImageOverlay()+"</div>"
										+ (targetedCharacter.hasFetish(fetish) // Overlay to create disabled effect:
											? ""
											: (fetish.isAvailable(targetedCharacter)
													? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
													: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width' style='margin:0; padding:0; width:100%;'>"
						+"<div class='container-full-width' style='text-align:center; font-weight:bold; margin:0 8px; width: calc(78% - 16px);'>"
							+"<div class='container-full-width' style='margin:4px 0; padding:2px; width:100%; background:#222;'>"
								+ "<div class='container-full-width' style='margin:0; padding:2px; width:" + experiencePercentage + "%; background:"+level.getColour().toWebHexString()+";'></div>"
							+ "</div>"
						+ "</div>"
						+"<div class='container-full-width' style='text-align:center; margin:0 8px; width: calc(22% - 16px);'>"
							+ "<span style='color:"+level.getColour().toWebHexString()+";'>"+targetedCharacter.getFetishExperience(fetish)+" xp</span>"
						+ "</div>"
//						+ "<div class='overlay no-pointer' id='"+Fetish.getIdFromFetish(fetish)+"_EXPERIENCE'></div>"
					+ "</div>"
				+ "</div>";
	}
	
	private static String getFetishDesireEntry(GameCharacter targetedCharacter, AbstractFetish fetish, FetishDesire desire) {
		boolean disabled = desire!=FetishDesire.FOUR_LOVE && targetedCharacter.hasFetish(fetish);
		
		return "<div class='square-button"+(disabled?" disabled":"")+"' id='"+Fetish.getIdFromFetish(fetish)+"_"+desire+"'"
					+ " style='"+(targetedCharacter.getBaseFetishDesire(fetish)==desire
								?"border:2px solid "+PresetColour.FETISH.getShades()[1]+";"
								:"")+"width:10%; margin:0 5%; float:left; cursor:pointer;'>"
				+ "<div class='square-button-content'>"+(targetedCharacter.getFetishDesire(fetish)==desire?desire.getSVGImage():desire.getSVGImageDesaturated())+"</div>"
				+ (targetedCharacter.hasFetish(fetish) && targetedCharacter.getFetishDesire(fetish)!=desire
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; border-radius:5px;'></div>"
					:targetedCharacter.getFetishDesire(fetish)!=desire
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:5px;'></div>"
						:"")
			+ "</div>";
	}
	
	public static AbstractWorldType worldTypeMap = WorldType.DOMINION;

	private static void setMapResponseTabToCurrentMap() {
		AbstractWorldType world = Main.game.getPlayer().getWorldLocation();
		if(world.getWorldRegion()==WorldRegion.SUBMISSION) {
			Main.game.setResponseTab(1);
		} else if(world.getWorldRegion()==WorldRegion.FIELD_CITY) {
			Main.game.setResponseTab(2);
		} else {
			Main.game.setResponseTab(0);
		}
	}
	
	public static final DialogueNode MAP = new DialogueNode("Maps", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getCurrentDialogueNode()!=MAP) {
				setMapResponseTabToCurrentMap();
			}
		}
		@Override
		public String getLabel() {
			return "Map: "+Util.capitaliseSentence(worldTypeMap.getName());
		}
		@Override
		public String getContent() {
			if(worldTypeMap==WorldType.WORLD_MAP) {
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true, false);
			} else {
				return RenderingEngine.ENGINE.getFullMap(worldTypeMap, true, true);
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Dominion";
			} else if(index==1) {
				return "Submission";
			} else if(index==2) {
				return "Elis";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int i=2;
			List<AbstractWorldType> worldTypes = new ArrayList<>(Main.getProperties().hasValue(PropertyValue.mapReveal)?WorldType.getAllWorldTypes():Main.game.getPlayer().getWorldsVisited());
			
			worldTypes.sort((w1, w2) -> (w1.getName().compareTo(w2.getName())));
			
			worldTypes.sort((w1, w2) -> w1.getMajorAreaIndex()-w2.getMajorAreaIndex());
			
			for(AbstractWorldType world : worldTypes) {
				boolean correctRegion = false;
				if(world.getWorldRegion()==WorldRegion.SUBMISSION) {
					correctRegion = responseTab==1;
				} else if(world.getWorldRegion()==WorldRegion.FIELD_CITY || world.getWorldRegion()==WorldRegion.FIELDS) {
					correctRegion = responseTab==2;
				} else {
					correctRegion = responseTab==0;
				}
				
				if(correctRegion
						&& world != WorldType.WORLD_MAP
						&& (world != WorldType.EMPTY || Main.game.isDebugMode())
						&& world != WorldType.MUSEUM
						&& world != WorldType.MUSEUM_LOST) {
					if(index==i) {
						boolean playerPresent = Main.game.getPlayer().getWorldLocation()==world;
						String responseTitle = (world.isMajorArea()?"<b>":"")+Util.capitaliseSentence(world.getName())+(world.isMajorArea()?"</b>":"");
//						String responseTitle = Util.capitaliseSentence(world.getName());
						
						if(worldTypeMap==world) {
							return new Response(responseTitle, "You are already viewing the map of "+world.getName()+"."+(playerPresent?"<br/>[style.colourGood(You are currently in this area!)]":""), null);
							
						} else if(Main.game.getPlayer().getWorldsVisited().contains(world) || Main.getProperties().hasValue(PropertyValue.mapReveal)) { 
							return new Response(responseTitle, "View the map of "+world.getName()+"."+(playerPresent?"<br/>[style.colourGood(You are currently in this area!)]":""), MAP) {
								@Override
								public Colour getHighlightColour() {
									if(playerPresent) {
										return PresetColour.GENERIC_GOOD;
									}
									if(world==WorldType.EMPTY) {
										return PresetColour.GENERIC_BAD;
									}
									return super.getHighlightColour();
								}
								@Override
								public void effects() {
									Pathing.initPathingVariables();
									worldTypeMap = world;
								}
							};
							
						} else {
							return new Response("???", "You haven't discovered this area yet.", null);
						}
					}
					i++;
				}
			}
			if (index == 1) {
				boolean playerPresent = Main.game.getPlayer().getWorldLocation()==WorldType.WORLD_MAP;
				if(worldTypeMap==WorldType.WORLD_MAP) {
					return new Response("World map", "You are already viewing the world map."+(playerPresent?"<br/>[style.colourGood(You are currently in this area!)]":""), null);
					
				} else if(Main.game.getPlayer().isDiscoveredWorldMap()) {
					return new Response("World map", "Take a look at the world map."+(playerPresent?"<br/>[style.colourGood(You are currently in this area!)]":""), MAP) {
						@Override
						public Colour getHighlightColour() {
							if(playerPresent) {
								return PresetColour.GENERIC_GOOD;
							}
							return super.getHighlightColour();
						}
						@Override
						public void effects() {
							Pathing.initPathingVariables();
							worldTypeMap = WorldType.WORLD_MAP;
						}
					};
				} else {
					return new Response("World map", "You haven't discovered the world map yet!", null);
				}
			
			} else if (index == 0) {
				return new Response("Back", "Return to the phone's main menu.", MENU);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode LOITER_SELECTION = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You think about how long you'd like to spend loitering in the area..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Decide against loitering in this area.", MENU);
			}
			if(index == 1) {
				return new ResponseEffectsOnly("15 minutes", "Loiter in this area for the next fifteen minutes.") {
					@Override
					public void effects() {
						loiter(15);
					}
				};
				
			} else if(index == 2) {
				return new ResponseEffectsOnly("1 hour", "Loiter in this area for the next hour.") {
					@Override
					public void effects() {
						loiter(60);
					}
				};
				
			} else if(index == 3) {
				return new ResponseEffectsOnly("4 hours", "Loiter in this area for the next four hours.") {
					@Override
					public void effects() {
						loiter(60*4);
					}
				};
				
			} else if(index == 4) {
				return new ResponseEffectsOnly("12 hours", "Loiter in this area for the next twelve hours.") {
					@Override
					public void effects() {
						loiter(60*12);
					}
				};
				
			} else if(index == 5) {
				return new ResponseEffectsOnly("24 hours", "Loiter in this area for the next twenty-four hours.") {
					@Override
					public void effects() {
						loiter(60*24);
					}
				};
			}
			return null;
		}

		private void loiter(int minutes) {
			String period = "";
			int hours = minutes / 60;
			int partialMinutes = minutes % 60;
			if (hours != 0) {
				period += (hours==1?"":Util.intToString(hours)) + " hour"+(hours==1?" ":"s ");
			}
			if (partialMinutes != 0) {
				period += Util.intToString(partialMinutes) + " minutes ";
			}
			if (minutes == 0) {
				period = "period ";
			}
			Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" +
					"<i>You spend the next ").append(period).append("loitering about, doing nothing in particular...</i>").append("</p>");
			Main.game.getPlayer().setActive(false);
			Main.game.endTurn(60*minutes);
			Main.game.getPlayer().setActive(true);
			Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
}
