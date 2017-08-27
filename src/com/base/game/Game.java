package com.base.game;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.base.controller.MainController;
import com.base.controller.TooltipUpdateThread;
import com.base.game.character.GameCharacter;
import com.base.game.character.PlayerCharacter;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.AffectionLevel;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.ObedienceLevel;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.npc.NPC;
import com.base.game.character.npc.dominion.Alexa;
import com.base.game.character.npc.dominion.Brax;
import com.base.game.character.npc.dominion.CandiReceptionist;
import com.base.game.character.npc.dominion.HarpyBimbo;
import com.base.game.character.npc.dominion.HarpyBimboCompanion;
import com.base.game.character.npc.dominion.HarpyDominant;
import com.base.game.character.npc.dominion.HarpyDominantCompanion;
import com.base.game.character.npc.dominion.HarpyNympho;
import com.base.game.character.npc.dominion.HarpyNymphoCompanion;
import com.base.game.character.npc.dominion.Kate;
import com.base.game.character.npc.dominion.Lilaya;
import com.base.game.character.npc.dominion.NPCRandomDominion;
import com.base.game.character.npc.dominion.Finch;
import com.base.game.character.npc.dominion.Nyan;
import com.base.game.character.npc.dominion.Pazu;
import com.base.game.character.npc.dominion.Pix;
import com.base.game.character.npc.dominion.Ralph;
import com.base.game.character.npc.dominion.Rose;
import com.base.game.character.npc.dominion.Scarlett;
import com.base.game.character.npc.dominion.TestNPC;
import com.base.game.character.npc.dominion.Vicky;
import com.base.game.character.npc.generic.GenericAndrogynousNPC;
import com.base.game.character.npc.generic.GenericFemaleNPC;
import com.base.game.character.npc.generic.GenericMaleNPC;
import com.base.game.character.race.Race;
import com.base.game.dialogue.DialogueFlags;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.encounters.Encounter;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseCombat;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.responses.ResponseTrade;
import com.base.game.dialogue.utils.MiscellaneousDialogue;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.inventory.weapon.WeaponType;
import com.base.main.Main;
import com.base.rendering.RenderingEngine;
import com.base.rendering.SVGImages;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Vector2i;
import com.base.world.Cell;
import com.base.world.World;
import com.base.world.WorldType;
import com.base.world.places.Dominion;
import com.base.world.places.PlaceInterface;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int FONT_SIZE_NORMAL = 18, FONT_SIZE_LARGE = 24, FONT_SIZE_HUGE = 30;

	// The real MVP:
	private PlayerCharacter player;
	
	// Unique NPCs:
	private NPC
		testNPC,				// NPC for testing purposes.
		lilaya,		 			// The player's aunt.
		rose,		 			// Lilaya's slave.
		brax,		 			// The enforcer chief.
//		arthur,		 			// Lilaya's colleague.
		ralph,		 			// Ingredients & items shop-keeper.
		nyan,					// Clothing shop-keeper.
		vicky,		 			// Weapons & potions shop-keeper.
		pix, 		 			// Gym trainer.
		kate, 		 			// Beauty salon owner.
		scarlett, 	 			// Slave trader.
		alexa, 		 			// Harpy matriarch.
		harpyBimbo, 			// Bimbo harpy matriarch.
		harpyBimboCompanion,	// Bimbo harpy matriarch's companion.
		harpyDominant, 			// Dominant harpy matriarch.
		harpyDominantCompanion, // Dominant harpy matriarch's companion.
		harpyNympho, 			// Nymphomaniac harpy matriarch.
		harpyNymphoCompanion, 	// Nymphomaniac harpy matriarch's companion.
		pazu,					// Kumiko's harpy.
		candiReceptionist,		// Receptionist at the Enforcer HQ.	 
		finch;					// Manager of Slaver Alley's 'Slave Administration'
	
	// Generic NPCS:
	private NPC genericMaleNPC, genericFemaleNPC, genericAndrogynousNPC;
	
	// NPCs:
	private NPC currentRandomAttacker;
	private List<NPC> NPCList;
	private List<NPC> playerOffspring;
	private List<NPC> offspringSpawned;
	
	private World activeWorld;
	private Map<WorldType, World> worlds;
	private long minutesPassed;
	private boolean debugMode, renderAttributesSection, renderMapSection, inCombat, inSex, imperialMeasurements;

	private Cell currentCell;
	private Encounter currentEncounter;

	private boolean hintsOn, started;

	private Weather currentWeather;
	private long nextStormTime;
	private int weatherTimeRemaining;

	private DialogueFlags dialogueFlags;

	// Maps of unlocked:
	private Map<ClothingType, Set<Colour>> unlockedClothes;
	private List<WeaponType> unlockedWeapons;

	// Responses:
	private int responsePointer = 0;
	
	// Dialogues:
	private DialogueNodeOld currentDialogueNode;
	private DialogueNodeOld savedDialogueNode = null;
	private String currentDialogue, savedDialogue, previousPastDialogueSBContents = "";
	private int initialPositionAnchor = 0, responsePage = 0;
	private StringBuilder pastDialogueSB = new StringBuilder(), choicesDialogueSB = new StringBuilder();
	private StringBuilder textEndStringBuilder = new StringBuilder(), textStartStringBuilder = new StringBuilder();

	public Game() throws ClassNotFoundException, IOException {
		activeWorld = null;
		worlds = new EnumMap<>(WorldType.class);
		for (WorldType type : WorldType.values())
			worlds.put(type, null);
		minutesPassed = 22 * 60;
		inCombat = false;
		inSex = false;
		renderAttributesSection = false;
		renderMapSection = false;
		debugMode = false;
		imperialMeasurements = false;

		dialogueFlags = new DialogueFlags();

		hintsOn = false;
		started = false;

		unlockedClothes = new HashMap<>();
		unlockedWeapons = new ArrayList<>();

		// Start in clouds:
		currentWeather = Weather.CLOUD;
		weatherTimeRemaining = 300;
		nextStormTime = minutesPassed + (60*48) + (60*Util.random.nextInt(24)); // Next storm in 2-3 days
	}

	public void initNewGame(DialogueNodeOld startingDialogueNode) {
		// Set up NPCs:
		NPCList = new ArrayList<>();
		
		playerOffspring = new ArrayList<>();
		offspringSpawned = new ArrayList<>();
		
		testNPC = new TestNPC();
		NPCList.add(testNPC);
		
		lilaya = new Lilaya();
		NPCList.add(lilaya);
		lilaya.addRelationship(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
		
		rose = new Rose();
		NPCList.add(rose);
		rose.addRelationship(Main.game.getPlayer(), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
		lilaya.addSlave(rose);
		rose.setObedience(ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMedianValue());
		lilaya.addRelationship(rose, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
		rose.addRelationship(lilaya, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
		
		brax = new Brax();
		NPCList.add(brax);

		candiReceptionist = new CandiReceptionist();
		NPCList.add(candiReceptionist);

		brax.addRelationship(candiReceptionist, AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		candiReceptionist.addRelationship(brax, AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		
		ralph = new Ralph();
		NPCList.add(ralph);
		
		nyan = new Nyan();
		NPCList.add(nyan);
		
		vicky = new Vicky();
		NPCList.add(vicky);
		
//		arthur = new Arthur();
//		NPCList.add(arthur);
		
		pix = new Pix();
		NPCList.add(pix);
		
		kate = new Kate();
		NPCList.add(kate);
		
		scarlett = new Scarlett();
		NPCList.add(scarlett);
		
		alexa = new Alexa();
		NPCList.add(alexa);
		
		alexa.addRelationship(scarlett, AffectionLevel.NEGATIVE_FOUR_HATE.getMedianValue());
		scarlett.addRelationship(alexa, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
		scarlett.addRelationship(Main.game.getPlayer(), AffectionLevel.NEGATIVE_TWO_DISLIKE.getMedianValue());
		
		harpyBimbo = new HarpyBimbo();
		NPCList.add(harpyBimbo);
		
		harpyBimboCompanion = new HarpyBimboCompanion();
		NPCList.add(harpyBimboCompanion);

		harpyBimbo.addRelationship(harpyBimboCompanion, AffectionLevel.POSITIVE_THREE_CARING.getMedianValue());
		harpyBimboCompanion.addRelationship(harpyBimbo, AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
		
		harpyDominant = new HarpyDominant();
		NPCList.add(harpyDominant);

		harpyDominantCompanion = new HarpyDominantCompanion();
		NPCList.add(harpyDominantCompanion);

		harpyDominant.addRelationship(harpyDominantCompanion, AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
		harpyDominantCompanion.addRelationship(harpyDominant, AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
		
		harpyNympho = new HarpyNympho();
		NPCList.add(harpyNympho);

		harpyNymphoCompanion = new HarpyNymphoCompanion();
		NPCList.add(harpyNymphoCompanion);

		harpyNympho.addRelationship(harpyNymphoCompanion, AffectionLevel.POSITIVE_FOUR_LOVE.getMedianValue());
		harpyNymphoCompanion.addRelationship(harpyNympho, AffectionLevel.POSITIVE_FIVE_WORSHIP.getMedianValue());
		
		pazu = new Pazu();
		NPCList.add(pazu);
		
		finch = new Finch();
		NPCList.add(finch);
		
		
		genericMaleNPC = new GenericMaleNPC();
		genericFemaleNPC = new GenericFemaleNPC();
		genericAndrogynousNPC = new GenericAndrogynousNPC();

		// This is due to the fact that on new world creation, the player is placed at coordinates (0, 0), which reveals the three squares at the bottom left corner of the map:
		Main.game.getActiveWorld().getCell(0, 0).setDiscovered(false);
		Main.game.getActiveWorld().getCell(0, 1).setDiscovered(false);
		Main.game.getActiveWorld().getCell(1, 0).setDiscovered(false);
		
		// Set starting locations:
		player.setLocation(worlds.get(player.getWorldLocation()).getPlacesOfInterest().get(player.getStartingPlace()));
		
		for(NPC npc : NPCList) {
			npc.setLocation(worlds.get(npc.getWorldLocation()).getPlacesOfInterest().get(npc.getStartingPlace()));
		}
		
		started = true;

		setContent(new Response(startingDialogueNode.getLabel(), startingDialogueNode.getDescription(), startingDialogueNode));
	}

	// Main updating for game mechanics, as everything is based on turns.
	public void endTurn(int turnTime) {
		endTurn(turnTime, true);
	}
	
	public void endTurn(int turnTime, boolean advanceTime) {

		if(advanceTime) {
			minutesPassed += turnTime;
			setResponses(currentDialogueNode, false);
		}

		handleAtmosphericConditions(turnTime);

		// Remove Dominion attackers if they aren't in alleyways:
		Iterator<NPC> npcIterator = NPCList.iterator();
		while(npcIterator.hasNext()) {
			NPC npc = npcIterator.next();
			if(npc instanceof NPCRandomDominion) {
				if(npc.getLocationPlace()!=Dominion.CITY_BACK_ALLEYS && !Main.game.getPlayer().getLocation().equals(npc.getLocation())) {
					// Remove ongoing pregnancies for NPCs that are removed:
					if(npc.isPregnant()) {
						npc.endPregnancy(false);
					}
					npcIterator.remove();
				}
			}
		}
		
		boolean randomAttackerSEApplied=false;
		// Apply status effects for all NPCs:
		for (NPC npc : NPCList) {
			npc.calculateStatusEffects(turnTime);
			
			if(npc.isPendingClothingDressing()) {
				npc.equipClothing(true, true);
				npc.setPendingClothingDressing(false);
			}
			
			if(npc.hasStatusEffect(StatusEffect.PREGNANT_3) && (minutesPassed - npc.getTimeProgressedToFinalPregnancyStage())>(12*60)) {
				if(npc == lilaya) {
					if(!Main.game.getDialogueFlags().reactedToPregnancyLilaya) { // Lilaya will only end pregnancy after you've seen it.
						npc.endPregnancy(true);
					}
					
				} else {
					npc.endPregnancy(true);
					if(npc == kate) {
						Main.game.getDialogueFlags().reactedToKatePregnancy = false;
					}
				}
			}
			
			if(npc==currentRandomAttacker) {
				randomAttackerSEApplied = true;
			}
			
			if(npc.getLocation().equals(Main.game.getPlayer().getLocation()) && npc.getWorldLocation()==Main.game.getPlayer().getWorldLocation()) {
				for(CoverableArea ca : CoverableArea.values()) {
					if(npc.isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
						npc.getPlayerKnowsAreasMap().put(ca, true);
					}
				}
			}
		}
		
		if (currentRandomAttacker!=null && !randomAttackerSEApplied) {
			currentRandomAttacker.calculateStatusEffects(turnTime);
		}
		
		// If not in combat:
		if (!inCombat) {
			currentCell = activeWorld.getCell(player.getLocation());
			
			if(Main.game.getCurrentDialogueNode()!=MiscellaneousDialogue.STATUS_EFFECTS) {
				Main.game.getPlayer().calculateStatusEffects(turnTime);
			}
			
			// Regenerate health and stamina over time:
			if (!inSex && !currentDialogueNode.isRegenerationDisabled()) {
				if (Main.game.getPlayer().getStaminaPercentage() < 1)
					Main.game.getPlayer().incrementStamina(turnTime * 0.1f);
				if (Main.game.getPlayer().getHealthPercentage() < 1)
					Main.game.getPlayer().incrementHealth(turnTime * 0.1f);
				if (Main.game.getPlayer().getManaPercentage() < 1)
					Main.game.getPlayer().incrementMana(turnTime * 0.1f);
			}

		}

		// At the stroke of midnight (sort of):
		if ((int) (minutesPassed / (60 * 24)) != (int) (((minutesPassed - turnTime) / (60 * 24)))) {
			// Reset all shops:
			ralph.applyReset();
			nyan.applyReset();
			vicky.applyReset();
			kate.applyReset();
		}
		
		if (Main.game.isRenderMapSection()) {
			RenderingEngine.ENGINE.renderMapTitle();
			Main.mainController.renderMap();
			RenderingEngine.ENGINE.renderButtons();
		}

		Main.mainController.updateUI();
		
		Main.mainController.getTooltip().hide();
		
		if(!Main.game.getPlayer().getStatusEffectDescriptions().isEmpty() && Main.game.getCurrentDialogueNode()!=MiscellaneousDialogue.STATUS_EFFECTS){
			if(Main.game.getCurrentDialogueNode().getMapDisplay()==MapDisplay.NORMAL)
				Main.game.saveDialogueNode();
			
			Main.game.setContent(new Response("", "", MiscellaneousDialogue.STATUS_EFFECTS){
				
				@Override
				public void effects() {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().hasNonArcaneEssences()) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY));
					}
					
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().hasStatusEffect(StatusEffect.CLOTHING_JINXED)) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.SIDE_JINXED_CLOTHING));
					}
					
					if (!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) && Main.game.getPlayer().isVisiblyPregnant()) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY));
					}
				}	
			});
			Main.game.getPlayer().getStatusEffectDescriptions().clear();
		}
	}

	// Set weather and time remaining.
	// Handles Lilith's Lust build up.
	// Appends description of storm gathering and breaking to mainController.
	private void handleAtmosphericConditions(int timeTaken) {

		weatherTimeRemaining -= timeTaken;

		// Weather change:
		if (weatherTimeRemaining < 0) {
			switch (currentWeather) {
				case CLEAR:
					if(minutesPassed >= nextStormTime) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemaining = 4 * 60 + Util.random.nextInt(2 * 60); // Gathering storm lasts for 4-6 hours
					} else {
						currentWeather = Weather.CLOUD;
						weatherTimeRemaining = 2 * 60 + Util.random.nextInt(2 * 60); // Clouds last for 2-4 hours
					}
					break;
					
				case CLOUD:
					if(minutesPassed >= nextStormTime) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemaining = 4 * 60 + Util.random.nextInt(2 * 60); // Gathering storm lasts for 4-6 hours
					} else {
						if (Math.random() > 0.4) { // 40% chance that will start raining
							currentWeather = Weather.RAIN;
							weatherTimeRemaining = 1 * 60 + Util.random.nextInt(5 * 60); // Rain lasts for 1-6 hours
						} else {
							currentWeather = Weather.CLEAR;
							weatherTimeRemaining = 4 * 60 + Util.random.nextInt(4 * 60); // Clear weather lasts for 4-8 hours
						}
					}
					break;
					
				case MAGIC_STORM:
					nextStormTime = minutesPassed + (60*48) + (60*Util.random.nextInt(24)); // Next storm in 2-3 days
					currentWeather = Weather.CLEAR;
					weatherTimeRemaining = 4 * 60 + Util.random.nextInt(4 * 60);
					break;
					
				case MAGIC_STORM_GATHERING:
					currentWeather = Weather.MAGIC_STORM;
					Main.game.getDialogueFlags().stormTextUpdate=true;
					weatherTimeRemaining = 8 * 60 + Util.random.nextInt(4 * 60); // Storm lasts 8-12 hours
					break;
					
				case RAIN:
					if(minutesPassed >= nextStormTime) {
						currentWeather = Weather.MAGIC_STORM_GATHERING;
						weatherTimeRemaining = 4 * 60 + Util.random.nextInt(2 * 60); // Gathering storm lasts for 4-6 hours
					} else {
						currentWeather = Weather.CLOUD;
						weatherTimeRemaining = 2 * 60 + Util.random.nextInt(2 * 60); // Clouds last for 2-4 hours
					}
					break;
					
				default:
					break;
			}
		}
	}

	public Weather getCurrentWeather() {
		return currentWeather;
	}

	/**
	 * Sets the content of the main WebView based on the response of the current
	 * Dialogue Node's index.
	 * 
	 * @param index
	 *            The dialogue choice index.
	 */
	private int positionAnchor = 0;
	private String dialogueTitle = "";
	public void setContent(int index) {
		
		Response response = currentDialogueNode.getResponse(index);
		
		if (response != null) {
			
			String corruptionGains = "";
			
			if(!response.isAvailable()) {
				if(!response.isAbleToBypass()) {
					return;
				} else {
					Main.game.getPlayer().incrementAttribute(Attribute.CORRUPTION, response.getCorruptionNeeded().getCorruptionBypass());
					corruptionGains = ("<p style='text-align:center;'>"
							+ "<b>You have gained +"+response.getCorruptionNeeded().getCorruptionBypass()+"</b> <b style='color:"+Attribute.CORRUPTION.getColour().toWebHexString()+";'>corruption</b><b>!</b>"
							+ "</p>");
				}
			}
			
			String chosenResponse = response.getTitle();
			DialogueNodeOld node = response.getNextDialogue();
			response.applyEffects();
			
			if(response instanceof ResponseCombat) {
				setContent(new Response("", "", ((ResponseCombat)response).initCombat()));
				return;
				
			} else if(response instanceof ResponseSex) {
				setContent(new Response("", "", ((ResponseSex)response).initSex()));
				return;
				
			} else if(response instanceof ResponseEffectsOnly) {
				return;
				
			} else if(response instanceof ResponseTrade) {
				((ResponseTrade)response).openTrade();
				return;
			} 
			
			
			if (node != null) {
				
				// Add characters in this scene to the player's encountered characters list:
				if(started) {
					if (!getCharactersPresent().isEmpty()) {
						for (GameCharacter character : getCharactersPresent())
							if (!Main.game.getPlayer().getCharactersEncountered().contains(character)) {
								if (character instanceof NPC) {
									if (((NPC) character).isAddedToContacts()) {
										Main.game.getPlayer().addCharacterEncountered(character);
									} 
									if(Main.game.getPlayer().addRaceDiscovered(character.getRace())) {
										Main.game.getTextEndStringBuilder().append(getRaceDiscoveredMessage(character.getRace()));
									}
									((NPC) character).setLastTimeEncountered(minutesPassed);
								}
							}
					}
				}
				
				String headerContent = node.getHeaderContent();
				String content = node.getContent();

				if (currentDialogueNode != null) {
					if (node.isContinuesDialogue()) {
						if (!node.isNoTextForContinuesDialogue()) {
							if (currentDialogueNode.getMapDisplay() == MapDisplay.NORMAL)
								positionAnchor++;
							pastDialogueSB.append("<hr id='position" + positionAnchor + "'><p class='option-disabled'>&gt " + chosenResponse + "</p>");

							if (getMapDisplay() == MapDisplay.NORMAL)
								initialPositionAnchor = positionAnchor;

							pastDialogueSB.append(
									UtilText.parse(
										corruptionGains 
										+ textStartStringBuilder.toString()
										+ content
										+ textEndStringBuilder.toString()
										+ (response.getQuestLine() != null ? Main.game.getPlayer().incrementQuest(response.getQuestLine()) : "")
									));
						}
					} else {
						dialogueTitle = UtilText.parse(node.getLabel());
						
						if (getMapDisplay() == MapDisplay.NORMAL)
							initialPositionAnchor = positionAnchor;

						if (currentDialogueNode.getMapDisplay() == MapDisplay.NORMAL)
							positionAnchor = 0;
						
						pastDialogueSB.setLength(0);
						pastDialogueSB.append(
								UtilText.parse(
									corruptionGains
									+ textStartStringBuilder.toString()
									+ content
									+ textEndStringBuilder.toString()
									+ (response.getQuestLine() != null ? Main.game.getPlayer().incrementQuest(response.getQuestLine()) : "")
								));
					}
				} else {
					dialogueTitle = UtilText.parse(node.getLabel());
				}
				// currentDialogueNode.applyResponse(index, true);
				// updateUIAttributes();


				boolean resetPointer = true;
				if(node != currentDialogueNode) {
					responsePage = 0;
					currentDialogueNode = node;
					
				} else {
					currentDialogueNode = node;
					checkForResponsePage();
					resetPointer = false;
				}

				

				if (node.isContinuesDialogue()) {
					
					currentDialogue = "<body onLoad='scrollToElement()'>"
								+ " <script>function scrollToElement() {"
										+ "document.getElementById('main-content').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop;"
									+ "}</script>"
								+"<div id='copy-content-button'>"+SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()+"</div>"
								+ "<div id='main-content'>"
								+ "<h4 style='text-align:center;'>" + dialogueTitle + "</h4>"
									+ "<div class='div-center'>"
										+ (headerContent != null
											? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
												+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
												+ "</div>"
											: "")
										+ (content != null ? "<div id='text-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
												+ pastDialogueSB.toString() + "</div>" : "")
//												+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
									+ "</div>"
									+"<div id='bottom-text'>Game saved!</div>"
								+ "</div>"

							+ "</body>";

				} else {
					currentDialogue = "<body>"
							+"<div id='copy-content-button'>"+SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()+"</div>"
							+ "<div id='main-content'>"
								+ "<h4 style='text-align:center;'>" + dialogueTitle + "</h4>"
								+ "<span id='position" + positionAnchor + "'></span>"
								+ "<div class='div-center'>"
									+ (headerContent != null
										? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
											+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
											+ "</div>"
										: "")
									+ (content != null ? "<div id='text-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
											+ pastDialogueSB.toString() + "</div>" : "")
//									+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
								+ "</div>"
								+"<div id='bottom-text'>Game saved!</div>"
							+ "</div>"

							+ "</body>";
				}

//				Main.mainController.unbindListeners();
				Main.mainController.setMainContent(currentDialogue);
				setResponses(currentDialogueNode, resetPointer);
				
				textEndStringBuilder.setLength(0);
				textStartStringBuilder.setLength(0);
				
				if(started) {
					Main.game.endTurn(getCurrentDialogueNode().getMinutesPassed());
				}
				
				TooltipUpdateThread.cancelThreads=true;
				//Main.mainController.processNewDialogue();

			}
		}
	}

	public void setContent(Response response, boolean allowTimeProgress, Colour flashMessageColour, String flashMessageText){
		
		DialogueNodeOld node = response.getNextDialogue();
		response.applyEffects();
		
		if (node == null){
			return;
		}
		
		String headerContent = node.getHeaderContent();
		String content = node.getContent();
		boolean resetPointer = false;
		
		if (getMapDisplay() == MapDisplay.NORMAL) {
			initialPositionAnchor = positionAnchor;
		}
		
		
		// Add characters in this scene to the player's encountered characters list:
		if(started) {
			if (!getCharactersPresent().isEmpty()) {
				for (GameCharacter character : getCharactersPresent()) {
					if (character instanceof NPC) {
						if (((NPC) character).isAddedToContacts()) {
							Main.game.getPlayer().addCharacterEncountered(character);
						}
						if(Main.game.getPlayer().addRaceDiscovered(character.getRace())) {
							Main.game.getTextEndStringBuilder().append(getRaceDiscoveredMessage(character.getRace()));
						}
						
						((NPC) character).setLastTimeEncountered(minutesPassed);
					}
				}
			}
		}
		
		if (currentDialogueNode != null) {
			if (node.isContinuesDialogue()) {
				if (!node.isNoTextForContinuesDialogue()) {
					if (currentDialogueNode.getMapDisplay() == MapDisplay.NORMAL) {
						positionAnchor++;
					}
					
					pastDialogueSB.append(UtilText.parse(
							"<hr id='position" + positionAnchor + "'><p class='option-disabled'>&gt " + currentDialogueNode.getLabel() + "</p>" + content
							+ (response.getQuestLine() != null ? Main.game.getPlayer().incrementQuest(response.getQuestLine()) : "")));
				}
			} else {
				dialogueTitle = UtilText.parse(node.getLabel());
				if (currentDialogueNode.getMapDisplay() == MapDisplay.NORMAL)
					positionAnchor = 0;
				
				pastDialogueSB.setLength(0);
				pastDialogueSB.append(
						UtilText.parse(
							"<b id='position" + positionAnchor + "'></b>"
							+ textStartStringBuilder.toString()
							+ content
							 + textEndStringBuilder.toString()
							+ (response.getQuestLine() != null ? Main.game.getPlayer().incrementQuest(response.getQuestLine()) : "")
						));
			}
		} else {
			dialogueTitle = UtilText.parse(node.getLabel());
			pastDialogueSB.setLength(0);
			pastDialogueSB.append(
					UtilText.parse(
						textStartStringBuilder.toString()
						+ content
						 + textEndStringBuilder.toString()
						+ (response.getQuestLine() != null ? Main.game.getPlayer().incrementQuest(response.getQuestLine()) : "")
					));
		}
		
		if(node != currentDialogueNode) {
			responsePage = 0;
			currentDialogueNode = node;
			resetPointer = true;
			
		} else {
			currentDialogueNode = node;
			checkForResponsePage();
			resetPointer = false;
		}


		if (node.isContinuesDialogue()) {
			currentDialogue = "<body onLoad='scrollToElement()'>"
					+ " <script>function scrollToElement() {"
							+ "document.getElementById('main-content').scrollTop = document.getElementById('position" + (positionAnchor) + "').offsetTop;"
					+ "}</script>"
					+"<div id='copy-content-button'>"+SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()+"</div>"
					+ "<div id='main-content'>"
					+ "<h4 style='text-align:center;'>" + dialogueTitle + "</h4>"
						+ "<div class='div-center'>"
							+ (headerContent != null
								? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
									+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
									+ "</div>"
								: "") 
							+ (content != null
								? "<div id='text-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
								+ pastDialogueSB.toString() + "</div>" : "")
//									+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
						+ "</div>"
						+"<div id='bottom-text'>Game saved!</div>"
					+ "</div>"

				+ "</body>";

		} else {
			currentDialogue = "<body>"
					+"<div id='copy-content-button'>"+SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()+"</div>"
					+ "<div id='main-content'>"
					+ "<h4 style='text-align:center;'>" + dialogueTitle + "</h4>"
					+ "<span id='position" + positionAnchor + "'></span>"
						+ "<div class='div-center'>"
							+ (headerContent != null
								? "<div id='header-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;-webkit-user-select: none;'>"
									+ (currentDialogueNode.disableHeaderParsing() ? headerContent : UtilText.parse(headerContent))
									+ "</div>"
								: "")
							+ (content != null ? "<div id='text-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
								+ pastDialogueSB.toString() + "</div>" : "")
//								+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
						+ "</div>"
					+"<div id='bottom-text'>Game saved!</div>"
					+ "</div>"
					
				+ "</body>";

		}
	
		Main.mainController.setFlashMessageColour(flashMessageColour);
		Main.mainController.setFlashMessageText(flashMessageText);

		//-------------------- MEMORY LEAK PROBLEM
		Main.mainController.setMainContent(currentDialogue);
		setResponses(currentDialogueNode, resetPointer);
		//--------------------
		
		textEndStringBuilder.setLength(0);
		textStartStringBuilder.setLength(0);

		//-------------------- MEMORY LEAK PROBLEM
		if(started) {
			if(allowTimeProgress) {
				Main.game.endTurn(getCurrentDialogueNode().getMinutesPassed());
			} else {
				Main.game.endTurn(0);
			}
		}
		//--------------------
			
	}
	
	/**
	 * Sets the content of the main WebView based on a DialogueNode.
	 * 
	 * @param node
	 *            The DialogueNode to set the content of.
	 * @param isRestore
	 *            True if this is a restoration after using inventory-like
	 *            dialogue.
	 */
	public void setContent(Response response) {
		setContent(response, true, null, null);
	}

	public void setContent(Response response, Colour colour, String messageText) {
		setContent(response, true, colour, messageText);
	}
	
	private void resetResponsePointer() {
		responsePointer=responsePage*MainController.RESPONSE_COUNT;
		
		for (int i=responsePage*MainController.RESPONSE_COUNT; i<responsePage*MainController.RESPONSE_COUNT+(MainController.RESPONSE_COUNT-1); i++) {
			if(currentDialogueNode.getResponse(i) != null) {
				responsePointer = i;
				break;
			}
		}
	}
	
	private void checkForResponsePage() {
		for (int i = responsePage*MainController.RESPONSE_COUNT; i<responsePage*MainController.RESPONSE_COUNT+(MainController.RESPONSE_COUNT-1); i++) {
			if(currentDialogueNode.getResponse(i) != null) {
				return;
			}
		}
		responsePage=0;
	}
	
	/**
	 * Create the response box html.
	 */
	public void setResponses(DialogueNodeOld node) {
		setResponses(node, true);
	}

	public void setResponses(DialogueNodeOld node, boolean withPointerReset) {
		if(withPointerReset)
			resetResponsePointer();
		
		choicesDialogueSB = new StringBuilder("");
		choicesDialogueSB.append("<div class='response-full-container'>");
		
		if (responsePage > 0) {
			choicesDialogueSB.append("<div class='response-switcher left' id='switch_left'><b class='hotkey-icon'>"
					+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE).getFullName()) + "</b>&#60</div>");
		} else {
			choicesDialogueSB.append("<div class='response-switcher left disabled' id='switch_left'><b class='hotkey-icon disabled'>"
					+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE).getFullName())
					+ "</b><span class='option-disabled'>&#60</span></div>");
		}
		
		choicesDialogueSB.append("<div class='response-container'>");
		
		Response response;
		if (responsePage == 0) {
			for (int i = 1; i < MainController.RESPONSE_COUNT; i++) {
				response = node.getResponse(i);
				if (response != null) {
					choicesDialogueSB.append(getResponseBoxDiv(response, i));
				} else
					choicesDialogueSB.append("<div class='response-box disabled"+(responsePointer==i?" selected":"")+"' id='option_" + i + "'>"
												+ "<b class='hotkey-icon disabled'>" + getResponseHotkey(i) + "</b>"
											+ "</div>");
			}
			response = node.getResponse(0);
			if (response != null) {
				choicesDialogueSB.append(getResponseBoxDiv(response, 0));

			} else
				choicesDialogueSB.append("<div class='response-box disabled"+(responsePointer==0?" selected":"")+"' id='option_0'>"
											+ "<b class='hotkey-icon disabled'>" + getResponseHotkey(0) + "</b>"
										+ "</div>");
			
		} else {
			for (int i = 0; i < (MainController.RESPONSE_COUNT-1); i++) {
				response = node.getResponse(i + (responsePage * MainController.RESPONSE_COUNT));
				if (response != null) {
					choicesDialogueSB.append(getResponseBoxDiv(response, i + 1));
				} else {
					choicesDialogueSB.append("<div class='response-box disabled"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)==i?" selected":"")+"' id='option_" + (i + 1) + "'>"
												+ "<b class='hotkey-icon disabled'>" + getResponseHotkey(i + 1) + "</b>"
											+ "</div>");
				}
			}
			response = node.getResponse(MainController.RESPONSE_COUNT-1 + (responsePage * MainController.RESPONSE_COUNT));
			if (response != null) {
				choicesDialogueSB.append(getResponseBoxDiv(response, 0));
			} else {
				choicesDialogueSB.append("<div class='response-box disabled"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==0?" selected":"")+"' id='option_0'>"
												+ "<b class='hotkey-icon disabled'>" + getResponseHotkey(0) + "</b>"
											+ "</div>");
			}
			
		}
		choicesDialogueSB.append("</div>");
		
		if (node.getResponse(((responsePage + 1) * MainController.RESPONSE_COUNT)) != null){
			choicesDialogueSB.append("<div class='response-switcher right' id='switch_right'><b class='hotkey-icon'>"
					+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE).getFullName()) + "</b>" + "&#62</div>");
			
		}else{
			choicesDialogueSB.append("<div class='response-switcher right disabled' id='switch_right'><b class='hotkey-icon disabled'>"
					+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE).getFullName())
					+ "</b><span class='option-disabled'>&#62</span></div>");
		}
		
		choicesDialogueSB.append("</div>");
		
		Main.mainController.setResponsesContent(choicesDialogueSB.toString());
	}
	
	public String getContentForClipboard(){
		return "<body style='background:#1e1e20; color:#DDD; font-family:Calibri;'>"
				+ "<style>"
				+ ".speech:before { content: '\"'; }"
				+ ".speech:after { content: '\"'; }"
				+ "</style>"
					+ "<h4 style='text-align:center; font-size:1.4em;'>" + dialogueTitle + "</h4>"
					+ "<div style='max-width:800px; margin:0 auto;'>"
						+ (currentDialogueNode.getHeaderContent() != null ? "<div id='header-content'>" + currentDialogueNode.getHeaderContent() + "</div>" : "")
						+ (currentDialogueNode.getContent() != null ? "<div id='text-content' style='font-size:" + Main.getProperties().fontSize + "px; line-height:" + (Main.getProperties().fontSize + 6) + "px;'>"
								+ textStartStringBuilder.toString() + pastDialogueSB.toString() + textEndStringBuilder.toString() + "</div>" : "")
					+ "</div>"
				+ "</div>"
				+"<p style='text-align:center;font-size:0.6em;color:#777;'>Dialogue written by "+currentDialogueNode.getAuthor()+" for <i>Lilith's Throne v"+Main.VERSION_NUMBER+"</i></p>"
				+ "</body>";
	}

	private String getResponseBoxDiv(Response response, int option) {
		String style="", iconRight="", iconLeft="";
		boolean responseDisabled = false;
		
		if(response.disabledOnNullDialogue() && response.getNextDialogue()==null) {
			responseDisabled = true;
			
		} else if (response.isAbleToBypass()) {
			iconRight = "<div class='response-icon'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseCorruptionBypass()+"</div>";
//			responseDisabled = false;
		}
		else if(response.hasRequirements()) {
			if(response.isAvailable()) {
//				iconRight = "<div class='response-icon'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseUnlocked()+"</div>";
				responseDisabled = false;
			} else {
//				iconRight = "<div class='response-icon'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseLocked()+"</div>";
				responseDisabled = true;
			}
		}
		
		if(response.getSexPace()!=null) {
			switch(response.getSexPace()) {
				case DOM_GENTLE:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomGentle()+"</div>";
					break;
				case DOM_NORMAL:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomNormal()+"</div>";
					break;
				case DOM_ROUGH:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseDomRough()+"</div>";
					break;
				case SUB_EAGER:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubEager()+"</div>";
					break;
				case SUB_NORMAL:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubNormal()+"</div>";
					break;
				case SUB_RESISTING:
					iconLeft = "<div class='response-icon-left'>"+SVGImages.SVG_IMAGE_PROVIDER.getResponseSubResist()+"</div>";
					break;
			}
		}
		
		
		float fontSize = 1;
		String strippedTitle = UtilText.parse(response.getTitle()).replaceAll("<.*?>", "");
		// <test ok>test<...>
		if(strippedTitle.length()>18) {
			fontSize-=(strippedTitle.length()-18)*0.03f;
		}
		
		style = "style='font-size:"+fontSize+"em;'";
		
		if (response.isCombatHighlight()) {
			if(response.isAvailable() || response.isAbleToBypass())
				style = "style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+"; font-size:"+fontSize+"em;'";
			
		} else if (response.isSexHighlight()) {
			if(response.isAvailable() || response.isAbleToBypass())
				style = "style='color:"+Colour.GENERIC_SEX.toWebHexString()+"; font-size:"+fontSize+"em;'";
			
		} else if (response.isSexPenetrationHighlight()) {
			if(response.isAvailable() || response.isAbleToBypass())
				style = "style='color:"+Colour.GENERIC_SEX.toWebHexString()+"; font-size:"+fontSize+"em;'";
			
		} else if (response.isSexPositioningHighlight()) {
			if(response.isAvailable() || response.isAbleToBypass())
				style = "style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+"; font-size:"+fontSize+"em;'";
			
		}
		
		String titleText = UtilText.parse(response.getTitle());
		
		if(responsePage==0) {
			return "<div class='response-box"+(responsePointer==option?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer==option?" selected":"")+"' "+style+">" + (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeft
						+ iconRight
					+ "</div>";
			
		} else {
			if(option == 0) {
				return "<div class='response-box"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==(option)?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer-((responsePage+1)*MainController.RESPONSE_COUNT)+1==option?" selected":"")+"' "+style+">"
						+ (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeft
						+ iconRight
					+ "</div>";
			} else {
				return "<div class='response-box"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)+1==(option)?" selected":"")+"' id='option_" + option + "'>"
						+ "<b class='hotkey-icon'>" + getResponseHotkey(option) + "</b>"
						+ "<p class='response-text"+(responsePointer-(responsePage*MainController.RESPONSE_COUNT)+1==option?" selected":"")+"' "+style+">"
						+ (!responseDisabled ? titleText : UtilText.getDisabledResponse(titleText)) + "</p>"
						+ iconLeft
						+ iconRight
					+ "</div>";
			}
		}
	}

	private String getResponseHotkey(int i) {
		if (!(0 <= i && i <= 14)) { return ""; }
		
		KeyboardAction[] keyboardActions = 
			{
					KeyboardAction.RESPOND_0, KeyboardAction.RESPOND_1, KeyboardAction.RESPOND_2, KeyboardAction.RESPOND_3, KeyboardAction.RESPOND_4,
					KeyboardAction.RESPOND_5, KeyboardAction.RESPOND_6, KeyboardAction.RESPOND_7, KeyboardAction.RESPOND_8, KeyboardAction.RESPOND_9,
					KeyboardAction.RESPOND_10, KeyboardAction.RESPOND_11, KeyboardAction.RESPOND_12, KeyboardAction.RESPOND_13, KeyboardAction.RESPOND_14
			};
		KeyboardAction currentAction = keyboardActions[i];
		KeyCodeWithModifiers hotkeyForCurrentAction = Main.getProperties().hotkeyMapPrimary.get(currentAction);
		if (hotkeyForCurrentAction == null) {
			return "";
		} else {
			return hotkeyForCurrentAction.asHotkey();
		}
	}
	

	public void responseNavigationUp(){
		int minIndex = responsePage*MainController.RESPONSE_COUNT;
		
		if(responsePointer==0) {
			responsePointer=MainController.RESPONSE_COUNT-5;
		} else if(responsePointer>minIndex+5) {
			responsePointer-=5;
		}
		
		setResponses(currentDialogueNode, false);
//		setTooltip();
	}
	
	
	public void responseNavigationDown(){
		int maxIndex = responsePage*MainController.RESPONSE_COUNT + MainController.RESPONSE_COUNT-1;
		
		if(responsePointer==MainController.RESPONSE_COUNT-5) {
			responsePointer=0;
			
		} else if(responsePointer<=maxIndex-5 && responsePointer!=0) {
			responsePointer+=5;
		}
		
		setResponses(currentDialogueNode, false);
//		setTooltip();
	}
	
	public void responseNavigationLeft(){
		if(responsePage==0) {
			int minIndex = responsePointer - ((responsePointer-1)%5);
			
			if(responsePointer==0) {
				responsePointer=MainController.RESPONSE_COUNT-1;
				
			} else if(responsePointer > minIndex) {
				responsePointer--;
			}
			
		} else {
			int minIndex = ((responsePointer/5))*5;
			
			if(responsePointer > minIndex) {
				responsePointer--;
			}
		}
		
		setResponses(currentDialogueNode, false);
//		setTooltip();
	}
	
	public void responseNavigationRight(){
		if(responsePage==0) {
			int maxIndex = responsePointer + (4 - (responsePointer-1)%5);
			
			if(responsePointer==MainController.RESPONSE_COUNT-1) {
				responsePointer=0;
				
			} else if(responsePointer < maxIndex && responsePointer!=0) {
				responsePointer++;
			}
			
		} else {
			int maxIndex = ((responsePointer/5)+1)*5;
			
			if(responsePointer < maxIndex-1) {
				responsePointer++;
			}
		}
		
		setResponses(currentDialogueNode, false);
//		setTooltip();
	}
	
	//TODO Think of a neater way to display these response tooltips... (They obscure the game's text at the moment)
	
//	private StringBuilder tooltipSB = new StringBuilder();
//	private void setTooltip() {
//		Response response = Main.game.getCurrentDialogueNode().getResponse(responsePointer);
//		
//		if (response != null) {
//			tooltipSB.setLength(0);
//			
//			int boxHeight = 130;
//			
//			if(!response.hasRequirements()) {
//				
//				if(response.isSexHighlight()) {
//					tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span></div>");
//					boxHeight+=44;
//				} else if(response.isCombatHighlight()) {
//					tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span></div>");
//					boxHeight+=44;
//				}
//				
//				tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
//			
//			} else {
//				
//				if(response.isAvailable()) {
//					if(response.isSexHighlight())
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> (<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Available</span>)</div>");
//					else if(response.isCombatHighlight())
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> (<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Available</span>)</div>");
//					else
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Available</span></div>");
//					boxHeight+=44;
//					
//					tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
//					
//				} else if(response.isAbleToBypass()) {
//					if(response.isSexHighlight())
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> (<span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Corruptive</span>)</div>");
//					else if(response.isCombatHighlight())
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> (<span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Corruptive</span>)</div>");
//					else
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Corruptive</span></div>");
//					boxHeight+=44;
//					
//					tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
//					
//				} else {
//					if(response.isSexHighlight())
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> (<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Unavailable</span>)</div>");
//					else if(response.isCombatHighlight())
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> (<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Unavailable</span>)</div>");
//					else
//						tooltipSB.append("<div class='title'><span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Unavailable</span></div>");
//					boxHeight+=44;
//
//					tooltipSB.append("<div class='description'><span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>" + response.getTooltipText() + "</span></div>");
//				}
//
//				tooltipSB.append("<div class='description-small'>");
//				tooltipSB.append(response.getTooltipCorruptionBypassText());
//				tooltipSB.append("</div>");
//				boxHeight+=54;
//				
//				tooltipSB.append("<div class='subTitle-half' style='min-height:"+((response.lineHeight()+1)*18)+";'>Requirements");
//				tooltipSB.append(response.getTooltipBlockingList());
//				tooltipSB.append("</div>");
//				
//				tooltipSB.append("<div class='subTitle-half' style='min-height:"+((response.lineHeight()+1)*18)+";'>Unlocks");
//				tooltipSB.append(response.getTooltipRequiredList());
//				tooltipSB.append("</div>");
//				
//				boxHeight+= 24 + ((response.lineHeight()+1)*18);
//			}
//			
//			Main.mainController.setTooltipSize(360, boxHeight);
//			
//			double xPosition = Main.primaryStage.getX();
//			if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
//				xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
//			
//			double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - boxHeight
//					- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());
//			
//			Main.mainController.getTooltip().setAnchorX(xPosition);
//			Main.mainController.getTooltip().setAnchorY(yPosition);
//			
//			(new Thread(new TooltipUpdateThread(xPosition, yPosition))).start();
//			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
//		
//		} else {
//			TooltipUpdateThread.cancelThreads = true;
//			Main.mainController.getTooltip().hide();
//		}
//		
//	}

	public void saveDialogueNode() {

//		System.out.println("SAVING: "+currentDialogueNode.getLabel());
		
		savedDialogue = currentDialogue;
		savedDialogueNode = currentDialogueNode;
		previousPastDialogueSBContents = pastDialogueSB.toString();
	}
	
	/**
	 * Flashes a message at the bottom of the screen.
	 * @param colour Colour of the text message.
	 * @param text Content of the message.
	 */
	public void flashMessage(Colour colour, String text){
		Main.mainController.getWebEngine().executeScript(
				"document.getElementById('bottom-text').innerHTML=\"<span style='color:"+colour.toWebHexString()+";'>"+text+"</span>\";"
				+ "document.getElementById('bottom-text').classList.add('demo');"
				+ "setTimeout(function(){"
				+ "document.getElementById('bottom-text').classList.remove('demo');"
				+ "}, 2000);");
	}

	public void restoreSavedContent() {

//		System.out.println("RESTORING: "+savedDialogueNode.getLabel() + dialogueTitle);
		
		positionAnchor = initialPositionAnchor;
		dialogueTitle = UtilText.parse(savedDialogueNode.getLabel());
		
		currentDialogueNode = savedDialogueNode;

		setResponses(currentDialogueNode);

		currentDialogue = savedDialogue;
		pastDialogueSB.setLength(0);
		pastDialogueSB.append(previousPastDialogueSBContents);

		if (Main.getProperties().fontSize == FONT_SIZE_NORMAL) {
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px'; line-height:" + (FONT_SIZE_LARGE + 6) + "px;>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px'; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;>");
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px'; line-height:" + (FONT_SIZE_HUGE + 6) + "px;>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px'; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;>");
		} else if (Main.getProperties().fontSize == FONT_SIZE_LARGE) {
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px; line-height:" + (FONT_SIZE_LARGE + 6) + "px;'>");
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px; line-height:" + (FONT_SIZE_HUGE + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px; line-height:" + (FONT_SIZE_LARGE + 6) + "px;'>");
		} else if (Main.getProperties().fontSize == FONT_SIZE_HUGE) {
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_NORMAL + "px; line-height:" + (FONT_SIZE_NORMAL + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px; line-height:" + (FONT_SIZE_HUGE + 6) + "px;'>");
			currentDialogue = currentDialogue.replaceFirst("<div class='div-center' style='font-size:" + FONT_SIZE_LARGE + "px; line-height:" + (FONT_SIZE_LARGE + 6) + "px;'>",
					"<div class='div-center' style='font-size:" + FONT_SIZE_HUGE + "px; line-height:" + (FONT_SIZE_HUGE + 6) + "px;'>");
		}

		Main.mainController.setMainContent(currentDialogue);
		Main.mainController.setResponsesContent(choicesDialogueSB.toString());

		textEndStringBuilder.setLength(0);
		textStartStringBuilder.setLength(0);
		
		Main.game.endTurn(0);
		//Main.mainController.processNewDialogue();

	}
	
	private List<GameCharacter> charactersPresent = new ArrayList<>();
	public List<GameCharacter> getCharactersPresent() {
		charactersPresent.clear();
		
		for(NPC npc : NPCList) {
			if(npc.getWorldLocation()==player.getWorldLocation() && npc.getLocation().equals(player.getLocation())) {
				charactersPresent.add(npc);
			}
		}
		
		Collections.sort(charactersPresent, (a, b)->{return a.getName().compareTo(b.getName());});
		
		return charactersPresent;
	}
	
	public String getWeatherImage() {
		if (isDayTime()) {
			switch (currentWeather) {
			case CLEAR:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayClear();
			case CLOUD:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayCloud();
			case RAIN:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayRain();
			case MAGIC_STORM_GATHERING:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormIncoming();
			case MAGIC_STORM:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			}
		} else {
			switch (currentWeather) {
			case CLEAR:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightClear();
			case CLOUD:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightCloud();
			case RAIN:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightRain();
			case MAGIC_STORM_GATHERING:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormIncoming();
			case MAGIC_STORM:
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
		return "";
	}

	public int getWeatherTimeRemaining() {
		return weatherTimeRemaining;
	}
	
	public void setWeather(Weather weather, int timeRemaining) {
		currentWeather = weather;
		weatherTimeRemaining = timeRemaining;
	}

	public World getActiveWorld() {
		return activeWorld;
	}

	public Map<WorldType, World> getWorlds() {
		return worlds;
	}

	/**
	 * @param world
	 * @param location Location to set player to
	 */
	public void setActiveWorld(World world, Vector2i location, boolean setDefaultDialogue) {
		activeWorld = world;
		player.setWorldLocation(world.getWorldType());
		player.setLocation(location);
		
		if(setDefaultDialogue) {
			DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
			Main.game.setContent(new Response("", "", dn));
		}
		
		if(Main.game.started) {
			Main.saveGame("AutoSave_"+Main.game.getPlayer().getName(), true);
		}
	}
	
	public void setActiveWorld(World world, PlaceInterface placeType, boolean setDefaultDialogue) {
		setActiveWorld(
				world,
				world.getPlacesOfInterest().get(placeType),
				setDefaultDialogue);
	}
	
	public void setActiveWorld(boolean setDefaultDialogue) {
		setActiveWorld(
				getWorlds().get(getPlayerCell().getPlace().getLinkedWorldType()),
				getWorlds().get(getPlayerCell().getPlace().getLinkedWorldType()).getPlacesOfInterest().get(getPlayerCell().getPlace().getLinkedPlaceInterface()),
				setDefaultDialogue);
	}

	public void setPlayer(PlayerCharacter player) {
		this.player = player;
	}

	public PlayerCharacter getPlayer() {
		return player;
	}

	public long getMinutesPassed() {
		return minutesPassed;
	}

	public boolean isDayTime() {
		return minutesPassed % (24 * 60) >= (60 * 7) && minutesPassed % (24 * 60) < (60 * 21);
	}
	
	public boolean isMorning() {
		return minutesPassed % (24 * 60) >= 0 && minutesPassed % (24 * 60) < (60 * 12);
	}

	public int getDayNumber() {
		return (int) (1 + (getMinutesPassed() / (24 * 60)));
	}

	public boolean isInCombat() {
		return inCombat;
	}

	public void setInCombat(boolean inCombat) {
		this.inCombat = inCombat;
	}

	public boolean isInSex() {
		return inSex;
	}

	public void setInSex(boolean inSex) {
		this.inSex = inSex;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public boolean isImperialMeasurements() {
		return imperialMeasurements;
	}

	public void setImperialMeasurements(boolean imperialMeasurements) {
		this.imperialMeasurements = imperialMeasurements;
	}

	public Encounter getCurrentEncounter() {
		return currentEncounter;
	}

	public void setCurrentEncounter(Encounter currentEncounter) {
		this.currentEncounter = currentEncounter;
	}

	public NPC getTestNPC() {
		return testNPC;
	}

	public NPC getLilaya() {
		return lilaya;
	}

	public NPC getRose() {
		return rose;
	}

	public NPC getBrax() {
		return brax;
	}

//	public NPC getArthur() {
//		return arthur;
//	}

	public NPC getPix() {
		return pix;
	}

	public NPC getRalph() {
		return ralph;
	}

	public NPC getNyan() {
		return nyan;
	}

	public NPC getVicky() {
		return vicky;
	}

	public NPC getKate() {
		return kate;
	}

	public NPC getScarlett() {
		return scarlett;
	}
	
	public NPC getAlexa() {
		return alexa;
	}

	public NPC getHarpyBimbo() {
		return harpyBimbo;
	}

	public NPC getHarpyDominant() {
		return harpyDominant;
	}

	public NPC getHarpyNympho() {
		return harpyNympho;
	}

	public NPC getHarpyBimboCompanion() {
		return harpyBimboCompanion;
	}

	public NPC getHarpyDominantCompanion() {
		return harpyDominantCompanion;
	}

	public NPC getHarpyNymphoCompanion() {
		return harpyNymphoCompanion;
	}

	public NPC getPazu() {
		return pazu;
	}
	
	public NPC getCandi() {
		return candiReceptionist;
	}
	
	public NPC getFinch() {
		return finch;
	}

	public NPC getGenericMaleNPC() {
		return genericMaleNPC;
	}

	public NPC getGenericFemaleNPC() {
		return genericFemaleNPC;
	}

	public NPC getGenericAndrogynousNPC() {
		return genericAndrogynousNPC;
	}

	public List<NPC> getNPCList() {
		return NPCList;
	}
	
	public List<NPC> getOffspring() {
		return playerOffspring;
	}
	
	public List<NPC> getOffspringSpawned() {
		return offspringSpawned;
	}
	
	public boolean addNPC(NPC npc) {
		return NPCList.add(npc);
	}
	
	public boolean removeNPC(NPC npc) {
		if(npc.isPregnant()) {
			npc.endPregnancy(false);
		}
		return NPCList.remove(npc);
	}

	public NPC getCurrentRandomAttacker() {
		return currentRandomAttacker;
	}

	public void setCurrentRandomAttacker(NPC currentDominionAttacker) {
		this.currentRandomAttacker = currentDominionAttacker;
	}

	public boolean isHintsOn() {
		return hintsOn;
	}

	public void setHintsOn(boolean hintsOn) {
		this.hintsOn = hintsOn;
	}

	public boolean isStarted() {
		return started;
	}

	public Map<ClothingType, Set<Colour>> getUnlockedClothes() {
		return unlockedClothes;
	}

	public void unlockClothing(ClothingType clothing, Colour colour) {
		if (unlockedClothes.get(clothing) == null)
			unlockedClothes.put(clothing, new HashSet<>());

		unlockedClothes.get(clothing).add(colour);
	}

	public List<WeaponType> getUnlockedWeapons() {
		return unlockedWeapons;
	}

	// Dialogues:

	public StringBuilder getTextStartStringBuilder() {
		return textStartStringBuilder;
	}

	public void clearTextStartStringBuilder() {
		textStartStringBuilder.setLength(0);
	}

	public StringBuilder getTextEndStringBuilder() {
		return textEndStringBuilder;
	}

	public void clearTextEndStringBuilder() {
		textEndStringBuilder.setLength(0);
	}

	public DialogueNodeOld getCurrentDialogueNode() {
		return currentDialogueNode;
	}

	public MapDisplay getMapDisplay() {
		if (currentDialogueNode != null)
			return currentDialogueNode.getMapDisplay();
		else
			return null;
	}

	public boolean isRenderAttributesSection() {
		return renderAttributesSection;
	}

	public void setRenderAttributesSection(boolean renderAttributesSection) {
		this.renderAttributesSection = renderAttributesSection;
	}

	public boolean isRenderMapSection() {
		return renderMapSection;
	}

	public void setRenderMapSection(boolean renderMapSection) {
		this.renderMapSection = renderMapSection;
	}

	public int getResponsePage() {
		return responsePage;
	}

	public void setResponsePage(int responsePage) {
		this.responsePage = responsePage;
	}

	public boolean isHasNextResponsePage() {
		return currentDialogueNode.getResponse(((responsePage + 1) * MainController.RESPONSE_COUNT)) != null;
	}

	public DialogueNodeOld getSavedDialogueNode() {
		return savedDialogueNode;
	}

	public String getCurrencySymbol() {
		return "&#164";
	}

	public Cell getPlayerCell() {
		return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation());
	}

	public DialogueFlags getDialogueFlags() {
		return dialogueFlags;
	}

	public Cell getCurrentCell() {
		return currentCell;
	}

	public int getResponsePointer() {
		return responsePointer;
	}

	public void setResponsePointer(int responsePointer) {
		this.responsePointer = responsePointer;
	}
	
	public boolean isPlayerTileFull() {
		return getActiveWorld().getCell(getPlayer().getLocation()).getInventory().isInventoryFull();
	}
	
	public boolean isNonConEnabled() {
		return Main.getProperties().nonConContent;
	}
	
	public boolean isIncestEnabled() {
		return Main.getProperties().incestContent;
	}
	
	public boolean isForcedTFEnabled() {
		return Main.getProperties().forcedTransformationContent;
	}
	
	public boolean isFacialHairEnabled() {
		return Main.getProperties().facialHairContent;
	}
	
	public boolean isPubicHairEnabled() {
		return Main.getProperties().pubicHairContent;
	}
	
	public boolean isBodyHairEnabled() {
		return Main.getProperties().bodyHairContent;
	}
	
	public static String getRaceDiscoveredMessage(Race race) {
		return "<p style='text-align:center;'>"
					+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia!</b>"
					+ "</br>"
					+ "<b>Race:</b> <b style='color:"+race.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(race.getName())+"</b>"
				+ "</p>";
	}
	
	public static String getRaceAdvancedKnowledgeMessage(Race race) {
		return "<p style='text-align:center;'>"
					+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Advanced knowledge unlocked in your phone's encyclopedia!</b>"
					+ "</br>"
					+ "<b>Race:</b> <b style='color:"+race.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(race.getName())+"</b>"
				+ "</p>";
	}
}
