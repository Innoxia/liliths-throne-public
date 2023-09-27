package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.EnforcerWarehouseGuard;
import com.lilithsthrone.game.character.npc.dominion.Sean;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.SMClaireWarehouse;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public class EnforcerWarehouse {
	
	private static EnforcerWarehouseGuard arrestingGuard = null;
	private static List<GameCharacter> randomSexPartners = null;
	
	public static List<GameCharacter> getEnforcersPresent() {
		List<GameCharacter> list = new ArrayList<>();
		for(GameCharacter character : Main.game.getCharactersPresent()) {
			if(character instanceof EnforcerWarehouseGuard) {
				if(character.getHistory()==Occupation.NPC_ENFORCER_SWORD_INSPECTOR) {
					list.add(0, character);
				} else {
					list.add(character);
				}
			}
		}
		if(arrestingGuard!=null) {
			list.add(0, arrestingGuard);
		}
		return list;
	}
	
	private static EnforcerWarehouseGuard generateGuard(Occupation occupation) {
		Gender gender = Gender.getGenderFromUserPreferences(false, false);
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		
		// Make SWORD guards a predator subspecies:
		List <AbstractSubspecies> subspeciesAvailable = Util.newArrayListOfValues(
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger"),
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_lion"),
				Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_leopard"),
				Subspecies.DOG_MORPH_DOBERMANN,
				Subspecies.DOG_MORPH_GERMAN_SHEPHERD,
				Subspecies.FOX_MORPH,
				Subspecies.WOLF_MORPH);
		
		for(AbstractSubspecies subspecies : subspeciesAvailable) {
			if(gender.isFeminine()) {
				if(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN
						&& Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue()>0) {
					subspeciesMap.put(subspecies, Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue());
				}
			} else {
				if(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN
						&& Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue()>0) {
					subspeciesMap.put(subspecies, Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue());
				}
			}
		}
		if(gender.isFeminine()) {
			for(Entry<AbstractSubspecies, FurryPreference> entry : Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		} else {
			for(Entry<AbstractSubspecies, FurryPreference> entry : Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		}
		
		int total = 0;
		for(Integer i : subspeciesMap.values()) {
			total += i;
		}
		
		if(subspeciesMap.isEmpty() || total==0) {
			try {
				// If there is no suitable subspecies, use one at random and make them partial (as humans cannot be in SWORD):
				EnforcerWarehouseGuard guard = new EnforcerWarehouseGuard(occupation, Util.randomItemFrom(subspeciesAvailable), RaceStage.PARTIAL, gender, false);
				Main.game.addNPC(guard, false);
				return guard;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			AbstractSubspecies species = Util.getRandomObjectFromWeightedMap(subspeciesMap);
			RaceStage stage = RaceStage.GREATER;
			if(gender.isFeminine()) {
				stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(species), gender, species);
			} else {
				stage = Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(species), gender, species);
			}
			
			try {
				EnforcerWarehouseGuard guard = new EnforcerWarehouseGuard(occupation, species, stage, gender, false);
				Main.game.addNPC(guard, false);
				return guard;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return null;
	}
	
	public static void initWarehouse() {
		List<String> usedAdjectives = Util.newArrayListOfValues("nervous", "cowardly");
		
		// Add an Enforcer onto each of the Enforcer post tiles:
		for(Cell c : Main.game.getWorlds().get(WorldType.ENFORCER_WAREHOUSE).getCells(PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST)) {
			EnforcerWarehouseGuard guard = generateGuard(Occupation.NPC_ENFORCER_SWORD_CONSTABLE);
			guard.setLocation(c.getType(), c.getLocation(), true);
			usedAdjectives.add(Main.game.getCharacterUtils().setGenericName(guard, "SWORD guard", usedAdjectives));
		}
		
		// Add four Enforcers to the entrance:
		EnforcerWarehouseGuard guard = generateGuard(Occupation.NPC_ENFORCER_SWORD_INSPECTOR);
		guard.setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, true);
		guard.setGenericName("SWORD Inspector");
		
		guard = generateGuard(Occupation.NPC_ENFORCER_SWORD_SERGEANT);
		guard.setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, true);
		usedAdjectives.add(Main.game.getCharacterUtils().setGenericName(guard, "SWORD guard", usedAdjectives));
		
		for(int i=0; i<2; i++) {
			guard = generateGuard(Occupation.NPC_ENFORCER_SWORD_CONSTABLE);
			guard.setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, true);
			usedAdjectives.add(Main.game.getCharacterUtils().setGenericName(guard, "SWORD guard", usedAdjectives));
		}
	}
	
	private static Response getClaireCratesSexResponse() {
		if(Main.game.getNpc(Claire.class).getLust()>75) {
			return new ResponseSex(
					"Offer help",
					"There's a suitable place behind some nearby crates where you could help Claire get some relief...",
					true,
					true,
					new SMClaireWarehouse(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.STANDING_WALL)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Claire.class), SexSlotAgainstWall.BACK_TO_WALL))),
					null,
					null,
					AFTER_CLAIRE_SEX,
					UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "START_CLAIRE_SEX"));
			
		} else if(Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
			return new Response(
					"Another round",
					"See if Claire wants to have some more sex behind the crates...",
					ASK_CLAIRE_REPEAT_SEX);
			
		} else {
			return null;
		}
	}
	
	//---- ENCLOSURE DIALOGUE ----//
	
	public static final DialogueNode ENCLOSURE_TELEPORT_PADS = new DialogueNode("Teleport pads", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.clairePadsInvestigated);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.clairePadsInvestigated)) {
					return new Response("Investigate pad", "Take a closer look at the teleportation pad and try to find this 'essence port' Claire is searching for.", ENCLOSURE_TELEPORT_PADS_INVESTIGATE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.clairePadsInvestigated, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ENCLOSURE_TELEPORT_PADS_INVESTIGATE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS_INVESTIGATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Be stern",
						"It isn't like Claire to be acting in such a defeatist manner. Tell her to pull herself together.<br/>[style.italicsGood(You think that taking a no-nonsense approach to this will help Claire the most.)]",
						ENCLOSURE_TELEPORT_PADS_INVESTIGATE_RESPOND_TO_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS_PULL_TOGETHER"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Claire.class).incrementAffection(Main.game.getPlayer(), 15));
					}
				};
				
			} else if(index==2) {
				return new Response("Reassure",
						"Tell Claire not to despair, because everything is sure to turn out ok in the end.<br/>[style.italicsMinorGood(You're sure that Claire will appreciate this.)]",
						ENCLOSURE_TELEPORT_PADS_INVESTIGATE_RESPOND_TO_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS_REASSURE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Claire.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index==3) {
				return new Response("Hug",
						"Perhaps Claire just needs a hug to calm her down?<br/>[style.italicsMinorGood(Claire might find this to be a little too much, but will surely still appreciate the thought.)]",
						ENCLOSURE_TELEPORT_PADS_INVESTIGATE_RESPOND_TO_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_TELEPORT_PADS_HUG"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Claire.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCLOSURE_TELEPORT_PADS_INVESTIGATE_RESPOND_TO_CLAIRE = new DialogueNode("", "", false) {
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
			return ENCLOSURE_TELEPORT_PADS.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNode ENCLOSURE = new DialogueNode("Warehouse", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	
	public static final DialogueNode ENCLOSURE_SHELVING = new DialogueNode("Shelving", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_SHELVING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireEnclosureEscaped)) {
				if(index==1) {
					return new Response("Move shelves", "Move the shelves to try and open up a way out", ENCLOSURE_SHELVING_MOVE) {
						@Override
						public void effects() {
							Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
							Cell c = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()+1, Main.game.getPlayer().getLocation().getY());
							c.getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CORRIDOR);
							c.getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CORRIDOR.getName());
							Main.game.getPlayerCell().getInventory().addItem(Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEPORT)));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ENCLOSURE_SHELVING_MOVE = new DialogueNode("Warehouse", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_SHELVING_MOVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Learn spell", "Skim through the teleport spell book and try to learn it as quickly as possible.", ENCLOSURE_SHELVING_DRAG_TELEPORT_LEARNED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_SHELVING_DRAG_TELEPORT_LEARNED_START"));
						Main.game.getTextStartStringBuilder().append(
								"<span style='border:0; padding:0; text-align:center;'><i>"
									+Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEPORT)), Main.game.getPlayer(), true)
								+"</i></span>");
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENCLOSURE_SHELVING_DRAG_TELEPORT_LEARNED_END"));
						// Removed as it was just annoying later on when the telepathy spell is meant to be available:
//						int manaCost = (int)Spell.TELEPORT.getModifiedCost(Main.game.getPlayer());
//						Main.game.getPlayer().incrementMana(-manaCost);
//						Main.game.getTextStartStringBuilder().append(
//								UtilText.parse(Main.game.getPlayer(),
//										"<p style='text-align:center;'><b>[npc.Name] [style.colourBad([npc.verb(lose)])] "+(manaCost)+" "+Attribute.MANA_MAXIMUM.getName()+"!</b></p>"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ENCLOSURE_SHELVING_DRAG_TELEPORT_LEARNED = new DialogueNode("", "", true) {
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
				return new Response("Onwards", "Set off into the warehouse and look for the exit.", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireEnclosureEscaped, true);
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX()+1, Main.game.getPlayer().getLocation().getY()));
					}
				};
			}
			return null;
		}
	};

	

	//---- CORRIDOR DIALOGUE ----//
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Corridor", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			List<String> dangerousDirections = new ArrayList<>();
			Vector2i location = Main.game.getPlayer().getLocation();
			boolean isMainEntrance = false;
			Cell c = Main.game.getActiveWorld().getCell(location.getX(), location.getY()+1);
			if(c!=null && c.getPlace().getPlaceType().isDangerous()) {
				dangerousDirections.add("north");
				if(c.getPlace().getPlaceType().equals(PlaceType.ENFORCER_WAREHOUSE_ENTRANCE)) {
					isMainEntrance = true;
				}
			}
			c = Main.game.getActiveWorld().getCell(location.getX(), location.getY()-1);
			if(c!=null && c.getPlace().getPlaceType().isDangerous()) {
				dangerousDirections.add("south");
				if(c.getPlace().getPlaceType().equals(PlaceType.ENFORCER_WAREHOUSE_ENTRANCE)) {
					isMainEntrance = true;
				}
			}
			c = Main.game.getActiveWorld().getCell(location.getX()+1, location.getY());
			if(c!=null && c.getPlace().getPlaceType().isDangerous()) {
				dangerousDirections.add("east");
				if(c.getPlace().getPlaceType().equals(PlaceType.ENFORCER_WAREHOUSE_ENTRANCE)) {
					isMainEntrance = true;
				}
			}
			c = Main.game.getActiveWorld().getCell(location.getX()-1, location.getY());
			if(c!=null && c.getPlace().getPlaceType().isDangerous()) {
				dangerousDirections.add("west");
				if(c.getPlace().getPlaceType().equals(PlaceType.ENFORCER_WAREHOUSE_ENTRANCE)) {
					isMainEntrance = true;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CORRIDOR"));
			if(isMainEntrance) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CORRIDOR_ENTRANCE_WARNING"));
				
			} else {
				if(!dangerousDirections.isEmpty()) {
					sb.append("<p style='test-align:center; color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'><i>");
						if(dangerousDirections.size()>1) {
							sb.append("There are manned guard posts to the "+Util.stringsToStringList(dangerousDirections, false)+"! Entering these areas will surely result in a fight!");
						} else {
							sb.append("There is a manned guard post to the "+Util.stringsToStringList(dangerousDirections, false)+"! Entering that area will surely result in a fight!");
						}
					sb.append("</i></p>");
				}
			}
			
			return sb.toString();
			
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode CLAIRE_WARNING = new DialogueNode("Corridor", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireWarning);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireWarning)) {
				NPC guard = Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.ENFORCER_WAREHOUSE).getClosestCell(Main.game.getPlayer().getLocation(), PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST)).get(0);
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CLAIRE_WARNING", guard);
			} else {
				return CORRIDOR.getContent();
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireWarning)) {
				if(index==1) {
					return new Response("Continue", "Continue travelling through the warehouse...", CLAIRE_WARNING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireWarning, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_CLAIRE_SEX = new DialogueNode("Relieved", "You helped Claire to deal with her lust...", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_CLAIRE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue travelling through the warehouse...", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};

	public static final DialogueNode ASK_CLAIRE_REPEAT_SEX = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ASK_CLAIRE_REPEAT_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue().getResponse(responseTab, index);
		}
	};
	
	
	
	
	
	//---- CRATES DIALOGUE ----//
	
	public static final DialogueNode CRATES = new DialogueNode("Crates", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATE_SEARCHED"));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES"));

			if(Main.game.getNpc(Claire.class).getLust()>75 || Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_RECESS"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED
						|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED) {
					return new Response("Search crates", "You've already searched through the unsealed crates in this area!", null);
					
				} else {
					return new Response("Search crates", "Search through the crates to try and find something valuable.", CRATES_SEARCH) {
						@Override
						public void effects() {
							
							double rnd = Math.random();
							if(rnd<0.25) {
								List<AbstractClothingType> clothingToGenerate = new ArrayList<>(ClothingType.getAllClothing());
								clothingToGenerate.removeIf((clothing) -> !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN));
								
								AbstractClothing clothing = Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), false);
								for(int i=0; i<Util.random.nextInt(4); i++) {
									TFModifier rndMod = TFModifier.getClothingAttributeList().get(Util.random.nextInt(TFModifier.getClothingAttributeList().size()));
									clothing.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, rndMod, TFPotency.getRandomWeightedPositivePotency(), 0));
								}
								
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(clothing, 1, false, true));
								
							} else if(rnd < 0.5) {
								List<AbstractWeaponType> weaponToGenerate = new ArrayList<>(WeaponType.getAllWeapons());
								weaponToGenerate.removeIf((weapon) -> (weapon.getRarity()!=Rarity.RARE && weapon.getRarity()!=Rarity.EPIC) || !weapon.getItemTags().contains(ItemTag.SOLD_BY_VICKY));
								
								AbstractWeapon weapon = Main.game.getItemGen().generateWeapon(Util.randomItemFrom(weaponToGenerate));
								
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(weapon, 1, false, true));
								
							} else {
								List<AbstractItemType> itemTypes = Util.newArrayListOfValues(ItemType.getItemTypeFromId("BOTTLED_ESSENCE_DEMON"), ItemType.getItemTypeFromId("innoxia_race_demon_liliths_gift"), ItemType.FETISH_UNREFINED);
								AbstractItem item = Main.game.getItemGen().generateItem(Util.randomItemFrom(itemTypes));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, 3+Util.random.nextInt(6), false, true));
							}

							if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK) {
								Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED);
								Main.game.getPlayerCell().getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED.getName());
								
							} else {
								Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED);
								Main.game.getPlayerCell().getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED.getName());
							}
						}
					};
				}
				
			} else if(index==2) {
				return getClaireCratesSexResponse();
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CRATES_SEARCH = new DialogueNode("Crates", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_SEARCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CRATES.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode CRATES_ARK = new DialogueNode("Crates", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(Main.game.isSillyModeEnabled()) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED) {
					return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_ARK_SEARCHED");
				}
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_ARK");
			}
			return CRATES.getContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CRATES.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode CRATES_LUST_WEAPON = new DialogueNode("'Top Secret' Crate", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireObtainedLightningGlobe)) {
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON_SEARCHED");
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON"));

			if(Main.game.getNpc(Claire.class).getLust()>75 || Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_RECESS"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireObtainedLightningGlobe)) {
					return new Response("Search crate", "You've already searched through the unsealed crates in this area!", null);
				} else {
					return new Response("Search crate", "Search through the 'Top Secret' crate to try and find something which will help you escape from this warehouse.", CRATES_LUST_WEAPON_SEARCH) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireObtainedLightningGlobe, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Claire.class).setLust(80));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementLust(20, false));

							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CRATES_SEARCHED.getName());
						}
					};
				}
				
			} else if(index==2) {
				return getClaireCratesSexResponse();
			}
			
			return null;
		}
	};

	public static final DialogueNode CRATES_LUST_WEAPON_SEARCH = new DialogueNode("'Top Secret' Crate", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON_SEARCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {	
				return new Response("Help", "Ask Claire if she's ok.", CRATES_LUST_WEAPON_OBTAINED) {
					@Override
					public void effects() {
						AbstractItem item = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("BOTTLED_ESSENCE_DEMON"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_lightningGlobe_lightning_globe", DamageType.LUST), 1, false, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, 3, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CRATES_LUST_WEAPON_OBTAINED = new DialogueNode("'Top Secret' Crate", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON_OBTAINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Trust that Claire will be able to control herself and continue on your way.", CRATES_LUST_WEAPON_OBTAINED_CONTINUE) {
					@Override
					public void effects() {
//						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_CORRIDOR, false);
					}
				};
				
			} else if(index==2) {
				return getClaireCratesSexResponse();
			}
			
			return null;
		}
	};

	public static final DialogueNode CRATES_LUST_WEAPON_OBTAINED_CONTINUE = new DialogueNode("'Top Secret' Crate", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_LUST_WEAPON_OBTAINED_CONTINUE"));
			sb.append(CRATES_LUST_WEAPON.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CRATES_LUST_WEAPON.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SHELVES_SPELL_BOOK = new DialogueNode("Shelving", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED) {
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "SHELVES_SPELL_BOOK_SEARCHED");
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "SHELVES_SPELL_BOOK"));

			if(Main.game.getNpc(Claire.class).getLust()>75 || Main.game.getNpc(Claire.class).getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_RECESS"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED) {
					return new Response("Search shelves", "You've already searched through the shelves in this area!", null);
					
				} else {
					return new Response("Search shelves", "Search through the shelves to try and find something valuable.", SHELVES_SPELL_BOOK_SEARCH) {
						@Override
						public void effects() {
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED.getName());
							
							AbstractItem item = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEPATHIC_COMMUNICATION));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, 1, false, true));
						}
					};
				}
				
			} else if(index==2) {
				return getClaireCratesSexResponse();
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SHELVES_SPELL_BOOK_SEARCH = new DialogueNode("Crates", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CRATES_SPELL_BOOK_SEARCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHELVES_SPELL_BOOK.getResponse(responseTab, index);
		}
	};

	
	
	//---- ENFORCER POST DIALOGUE ----//
	
	public static final DialogueNode ENFORCER_GUARD_POST = new DialogueNode("Enforcer guard post", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().warehouseDefeatedIDs.contains(getEnforcersPresent().get(0).getId());
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().warehouseDefeatedIDs.contains(getEnforcersPresent().get(0).getId())) {
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST_CLEARED", getEnforcersPresent());
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST", getEnforcersPresent());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter guard = getEnforcersPresent().get(0);
			if(!Main.game.getDialogueFlags().warehouseDefeatedIDs.contains(guard.getId())) {
				if(index==1) {
//					return new Response("Back off",
//							"Step back into the warehouse and re-think how you're going to get out of here.",
//							Main.game.getNpc(Claire.class).getCell().getPlace().getPlaceType().getDialogue(false)) {
//						@Override
//						public void effects() {
//							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST_BACK_OFF", getEnforcersPresent()));
//							Main.game.getPlayer().setLocation(Main.game.getNpc(Claire.class), false);
//						}
//					};
//					
//				} else if(index==2) {
					return new ResponseCombat("Defend yourself",
							UtilText.parse(guard, "Defend yourself against the trigger-happy [npc.race] SWORD guard."),
							(NPC) guard,
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST_PLAYER_FIGHT_START", getEnforcersPresent())),
									new Value<>(guard, UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENFORCER_GUARD_POST_GUARD_FIGHT_START", getEnforcersPresent()))));
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_GUARD_COMBAT_VICTORY = new DialogueNode("Victory", "You've managed to defeat the SWORD guard!", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_GUARD_COMBAT_VICTORY", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter guard = getEnforcersPresent().get(0);
			if(index==1) {
				return new Response("Continue",
						UtilText.parse(guard, "Leave the defeated [npc.race] behind and continue on your way through the warehouse."),
						Main.game.getDefaultDialogue());
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_GUARD_COMBAT_DEFEAT = new DialogueNode("Defeat", "The SWORD guard has proven to be too much for you to handle!", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_GUARD_COMBAT_DEFEAT", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter guard = getEnforcersPresent().get(0);
			if(index==1) {
				return new Response("Arrested...",
						UtilText.parse(guard, "Having been defeated, you can offer no resistance as [npc.name(the)] drags you off to meet your fate..."),
						AFTER_GUARD_COMBAT_DEFEAT_ENTRANCE) {
					@Override
					public void effects() {
						arrestingGuard = (EnforcerWarehouseGuard) guard;
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, false);
						arrestingGuard.setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, false);
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_GUARD_COMBAT_DEFEAT_ENTRANCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_GUARD_COMBAT_DEFEAT_ENTRANCE", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isNonConEnabled()) {
					return new Response("Stocks",
							UtilText.parse(arrestingGuard, "[npc.Name(the)] drags you off to the stocks..."),
							AFTER_COMBAT_DEFEAT_SENT_TO_STOCKS) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false);
							arrestingGuard.setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Claire.class).returnToHome();
							Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.TELEPORTING_CAUGHT));
						}
					};
					
				} else {
					return new Response("Locked up",
							UtilText.parse(arrestingGuard, "[npc.Name(the)] drags you into the Enforcer Headquarters and down to the cells..."),
							AFTER_COMBAT_DEFEAT_SENT_TO_CELLS) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELL, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_COMBAT_DEFEAT_SENT_TO_CELLS", arrestingGuard));
							arrestingGuard.returnToHome();
							Main.game.getNpc(Claire.class).returnToHome();
							Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.TELEPORTING_CAUGHT));
						}
					};
				}
			}
			return null;
		}
	};
	
	
	
	//---- ENTRANCE DIALOGUE ----//
	
	public static final DialogueNode ENTRANCE = new DialogueNode("Warehouse entrance", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			if(index==0) {
				return new Response("Back off",
						"Step back into the warehouse and re-think how you're going to get out of here.",
						CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_BACK_OFF"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CORRIDOR"));
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_CORRIDOR, false);
					}
				};
				
			} else if(index==1) {
				return new ResponseCombat("Fight",
						"There's only way to get out of this mess, and that's by besting these SWORD Enforcers in combat!",
						(NPC) getEnforcersPresent().get(0),
						getEnforcersPresent(),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_PLAYER_CHALLENGE", getEnforcersPresent())),
								new Value<>(getEnforcersPresent().get(0), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_GUARD_RESPONSE_1", getEnforcersPresent().get(0))),
								new Value<>(getEnforcersPresent().get(1), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_GUARD_RESPONSE_2", getEnforcersPresent().get(1))),
								new Value<>(getEnforcersPresent().get(2), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_GUARD_RESPONSE_3", getEnforcersPresent().get(2))),
								new Value<>(getEnforcersPresent().get(3), UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_GUARD_RESPONSE_4", getEnforcersPresent().get(3)))));
				
			} else if(index==2) {
				if(!Main.game.getPlayer().hasWeaponType(WeaponType.getWeaponTypeFromId("innoxia_lightningGlobe_lightning_globe"), true)) {
					return new Response("Lightning globe", "You haven't discovered the lightning globe in the warehouse...", null);
				}
				return new Response("Lightning globe",
						"Overload the lightning globe and roll it along the floor towards the Enforcers. The lustful discharge should hopefully get them so horny that they will desert their post to have sex with one another.",
						ENTRANCE_LIGHTNING_GLOBE) {
					@Override
					public void effects() {
						for(GameCharacter c : getEnforcersPresent()) {
							c.setLust(100);
						}
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'><i>You [style.colourBad(lose)] <b>"+Main.game.getPlayer().getMana()+"</b> [style.colourAura(aura)]!</i></p>");
						Main.game.getPlayer().setMana(0);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().hasSpell(Spell.TELEPATHIC_COMMUNICATION)) {
					if(Main.game.getPlayer().getMana()>=Spell.TELEPATHIC_COMMUNICATION.getModifiedCost(Main.game.getPlayer())) {
						return new Response("Telepathic trickery",
								UtilText.parse(getEnforcersPresent(),
										"Cast the spell '"+Spell.TELEPATHIC_COMMUNICATION.getName()+"' on [npc.name(the)] and then use this connection to relay a distress message, pretending that you need help further inside the warehouse..."),
								ENTRANCE_TELEPATHIC_TRICKERY);
						
					} else {
						return new Response("Telepathic trickery",
								"You do not have enough aura to cast the spell '"+Spell.TELEPATHIC_COMMUNICATION.getName()+"'...",
								null);
					}
					
				} else {
					return new Response("Telepathic trickery",
							"You do not know the spell '"+Spell.TELEPATHIC_COMMUNICATION.getName()+"'...",
							null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_ENTRANCE_VICTORY = new DialogueNode("Victory", "You've managed to defeat the four SWORD guards!", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_ENTRANCE_VICTORY", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Escaped",
						"You and Claire have finally managed to escape from the warehouse!",
						AFTER_ENTRANCE_VICTORY_ESCAPE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_ENTRANCE_VICTORY_ESCAPE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_ENTRANCE_VICTORY_ESCAPE", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Say goodbye",
						"Say goodbye to Claire for now, before continuing on your way...",
						Main.game.getDefaultDialogue()) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_ENTRANCE_VICTORY_ESCAPE_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_ENTRANCE_DEFEAT = new DialogueNode("Defeat", "The four SWORD Enforcers prove to be too much for you to handle, and you collapse to the floor, completely defeated.", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_ENTRANCE_DEFEAT", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Stocks",
						UtilText.parse(getEnforcersPresent().get(0), "[npc.Name(the)] drags you off to the stocks..."),
						AFTER_COMBAT_DEFEAT_SENT_TO_STOCKS) {
					@Override
					public void effects() {
						arrestingGuard = (EnforcerWarehouseGuard) getEnforcersPresent().get(0);
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false);
						arrestingGuard.setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.TELEPORTING_CAUGHT));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ENTRANCE_LIGHTNING_GLOBE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_LIGHTNING_GLOBE", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Escaped",
						"You and Claire have finally managed to escape from the warehouse!",
						AFTER_ENTRANCE_VICTORY_ESCAPE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
					}
				};
			}
			return null;
		}
	};
	
	
	public static final DialogueNode ENTRANCE_TELEPATHIC_TRICKERY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "ENTRANCE_TELEPATHIC_TRICKERY", getEnforcersPresent());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Escaped",
						"You and Claire have finally managed to escape from the warehouse!",
						AFTER_ENTRANCE_VICTORY_ESCAPE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
					}
				};
				
			}
			return null;
		}
	};
	
	
	
	//---- DEFEATED ----//
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SENT_TO_STOCKS = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setCaptive(true);
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_COMBAT_DEFEAT_SENT_TO_STOCKS", arrestingGuard);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Locked in stocks",
						UtilText.parse(arrestingGuard, "You're unable to do anything to stop [npc.name] from doing whatever [npc.she] wants with you."),
						false,
						false,
						new SexManagerDefault(
								SexPosition.STOCKS,
								Util.newHashMapOfValues(new Value<>(arrestingGuard,
										arrestingGuard.hasPenis()||!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
											?SexSlotStocks.BEHIND_STOCKS
											:SexSlotStocks.RECEIVING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
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
								if(character.isPlayer()) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
								return !equippingCharacter.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
						},
						null,
						null,
						AFTER_STOCKS_ENFORCER_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "START_STOCKS_ENFORCER_SEX"));
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STOCKS_ENFORCER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public void applyPreParsingEffects() {
			arrestingGuard.returnToHome();
		}
		@Override
		public String getDescription() {
			return UtilText.parse(arrestingGuard, "[npc.Name] has finished with you...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_STOCKS_ENFORCER_SEX", arrestingGuard);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Strangers approach",
						"A couple of strangers step forwards, ready to have some fun with you...",
						STOCKS_RANDOMS) {
					@Override
					public void effects() {
						randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STOCKS_RANDOMS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "STOCKS_RANDOMS", randomSexPartners);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used again",
						"The two strangers move into position to start using you...",
						false,
						false,
						new SexManagerDefault(
								SexPosition.STOCKS,
								Util.newHashMapOfValues(
										new Value<>(randomSexPartners.get(0), SexSlotStocks.BEHIND_STOCKS),
										new Value<>(randomSexPartners.get(1), SexSlotStocks.RECEIVING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
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
								if(character.isPlayer()) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
								return !equippingCharacter.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
						},
						null,
						null,
						AFTER_STOCKS_RANDOM_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "START_STOCKS_RANDOM_SEX", randomSexPartners)) {
					@Override
					public void effects() {
						arrestingGuard.returnToHome();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STOCKS_RANDOM_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getDescription() {
			return UtilText.parse(randomSexPartners, "[npc.Name] and [npc2.name] have finished with you...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_STOCKS_RANDOM_SEX", randomSexPartners);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Saved",
						"The Enforcer overseer unlocks the stocks and sets you free.",
						STOCKS_SET_FREE) {
					@Override
					public void effects() {
						for(GameCharacter character : randomSexPartners) {
							Main.game.banishNPC((NPC) character);
						}
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STOCKS_SET_FREE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "STOCKS_SET_FREE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way...", Main.game.getDefaultDialogue()) {
					@Override
					public void effects() {
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
					}
				};
			}
			return null;
		}
	};


	public static final DialogueNode AFTER_COMBAT_DEFEAT_SENT_TO_CELLS = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setCaptive(true);
		}
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
				return new Response("Wait",
						"There's nothing else to do but wait for someone to rescue you...",
						AFTER_COMBAT_DEFEAT_CELLS_WAITING) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT_CELLS_WAITING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 120*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "AFTER_COMBAT_DEFEAT_CELLS_WAITING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Saved",
						"Claire orders the Enforcer overseeing the cells to unlock you and set you free.",
						CELLS_SET_FREE) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).returnToHome();
						Main.game.getNpc(Claire.class).setLust(Main.game.getNpc(Claire.class).getRestingLust());
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_TELEPORTATION, Quest.SIDE_UTIL_COMPLETE));
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CELLS_SET_FREE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerWarehouse/generic", "CELLS_SET_FREE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way...", Main.game.getDefaultDialogue());
			}
			return null;
		}
	};

}
