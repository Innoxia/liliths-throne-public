package com.lilithsthrone.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.InventorySelectedItemEventListener;
import com.lilithsthrone.controller.eventListeners.SetContentEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonCharactersEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonCopyDialogueEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonInventoryEventHandler;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonJournalEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonMainMenuEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonMoveEastEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonMoveNorthEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonMoveSouthEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonMoveWestEventListener;
import com.lilithsthrone.controller.eventListeners.buttons.ButtonZoomEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipHideEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInventoryEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipMoveEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipResponseDescriptionEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipResponseMoveEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterChangeEventListener;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Kay;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.elemental.ElementalDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.offspring.GenericOffspringDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallDemographics;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaMilkingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.KaysWarehouse;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.DebugDialogue;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PositioningMenu;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.ImageCache;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.DayPeriod;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.population.Population;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * @since 0.1.0
 * @version 0.4.6.4
 * @author Innoxia, Maxis010
 */
public class MainController implements Initializable {

	// FXML elements:
	@FXML
	private GridPane mainPane;
	@FXML
	private ListView<AbstractCoreItem> listViewInventoryCell, listViewInventoryPlayer;
	@FXML
	private VBox vBoxLeft;

	// UI-related elements:
	@FXML
	private WebView webViewMain, webViewAttributes, webViewRight, webViewButtonsLeft, webViewButtonsRight;

	static WebEngine webEngine;
	private WebEngine webEngineTooltip;
	private WebEngine webEngineAttributes;
	private WebEngine webEngineRight;
	private WebEngine webEngineButtonsLeft;
	private WebEngine webEngineButtonsRight;
	private WebView webviewTooltip;
	private Tooltip tooltip;
	private EventHandler<KeyEvent> actionKeyPressed, actionKeyReleased;

	// Responses:
	public static final int RESPONSE_COUNT = 15;
	
	// Misc:
	private boolean allowInput;
	private KeyCode[] lastKeys;
	
	static Colour flashMessageColour = null;
	static String flashMessageText = null;

	java.net.CookieManager cookieManager = new java.net.CookieManager();
	
	// Hotkey binding:
	static KeyboardAction actionToBind;
	static boolean primaryBinding;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		allowInput = true;
		lastKeys = new KeyCode[5];

		actionToBind = null;
		primaryBinding = true;

		webviewTooltip = new WebView();
		webviewTooltip.setMaxWidth(400);
		webviewTooltip.setMaxHeight(400);
		webviewTooltip.getEngine().getHistory().setMaxSize(0);
		Scene scene = new Scene(webviewTooltip);
        scene.setFill(null);
		
		tooltip = new Tooltip();
		tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		tooltip.setGraphic(webviewTooltip);
		tooltip.setMaxWidth(400);
		tooltip.setMaxHeight(400);
		tooltip.setAutoHide(true);
		tooltip.setConsumeAutoHidingEvents(false);

		vBoxLeft.getStyleClass().add("vbox");

		// Set up controls and buttons:
		setUpButtons();

		// Set up webViews:
		setUpWebViews();

		GameCharacter.addPlayerLocationChangeEventListener(new CharacterChangeEventListener() {
			@Override
			public void onChange() {
				if (Main.game.getPlayer() != null) {
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).setDiscovered(true);
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).setTravelledTo(true);
					if (Main.game.getPlayer().getLocation().getY() < Main.game.getActiveWorld().WORLD_HEIGHT - 1) {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() + 1).setDiscovered(true);
					}
					if (Main.game.getPlayer().getLocation().getY() != 0) {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() - 1).setDiscovered(true);
					}
					if (Main.game.getPlayer().getLocation().getX() < Main.game.getActiveWorld().WORLD_WIDTH - 1) {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() + 1, Main.game.getPlayer().getLocation().getY()).setDiscovered(true);
					}
					if (Main.game.getPlayer().getLocation().getX() != 0) {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() - 1, Main.game.getPlayer().getLocation().getY()).setDiscovered(true);
					}
					
					// Make sure that images of present characters are cached
					for (NPC character : Main.game.getCharactersPresent())
						if (character.hasArtwork() && Main.getProperties().hasValue(PropertyValue.artwork))
							ImageCache.INSTANCE.requestCache(character.getCurrentArtwork().getCurrentImage());
				}
			}
		});
		
		allowInput = true;
	}

	// All setup methods:
	public void openOptions() {
		if(!Main.game.isStarted()) {
			return;
		}
		
		if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OPTIONS) {
			Main.game.restoreSavedContent(false);
			
		} else {
			DialogueNodeType currentDialogueNodeType = Main.game.getCurrentDialogueNode().getDialogueNodeType();
			if (currentDialogueNodeType == DialogueNodeType.NORMAL
					|| currentDialogueNodeType == DialogueNodeType.OCCUPANT_MANAGEMENT // This was commented out at some point, which was causing issues with the opening/closing of the main menu screen.
					|| (!Main.game.isInNewWorld() && currentDialogueNodeType != DialogueNodeType.CHARACTERS_PRESENT)) {
				Main.game.saveDialogueNode();
			}
			
			Main.game.setContent(new Response("", "", OptionsDialogue.MENU));
		}
	}

	public boolean isPhoneDisabled() {
		return !Main.game.isStarted() || !Main.game.isInNewWorld();
	}
	
	public void openPhone() {
		openPhone(PhoneDialogue.MENU);
	}
	
	public void openPhone(DialogueNode toDialogue) {
		if(isPhoneDisabled() && toDialogue!=PositioningMenu.POSITIONING_MENU && Main.game.getCurrentDialogueNode()!=PositioningMenu.POSITIONING_MENU) {
			return;
		}
		
		if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE) {
			Main.game.restoreSavedContent(false);
			
		} else {
			if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
//					|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OCCUPANT_MANAGEMENT
					) {
				Main.game.saveDialogueNode();
			}
			
			Pathing.initPathingVariables();
			if(toDialogue.equals(PhoneDialogue.MAP)) {
				PhoneDialogue.worldTypeMap = Main.game.getPlayer().getWorldLocation();
			}
			Main.game.setContent(new Response("", "", toDialogue));
		}
	}

	public boolean isInventoryDisabled() {
		if(!Main.game.isInNewWorld() && !Main.game.isInSex()) {
			return true;
		}
		if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.INVENTORY
				|| Main.game.isInCombat()
				/*|| Main.game.isInSex()*/) {
			return false;
			
		} else if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OPTIONS || Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE) {
			if(Main.game.getSavedDialogueNode()==null) {
				return true;
			}
			return Main.game.getSavedDialogueNode().isInventoryDisabled();
			
		} else {
			return Main.game.getCurrentDialogueNode().isInventoryDisabled();
		}
	}

	public void openInventory() {
		if(!Main.game.isInNewWorld() && !Main.game.isInSex()) {
			//openInventory(null, InventoryInteraction.CHARACTER_CREATION);
			
		} else if(Main.game.isInCombat()) {
			if(Main.combat.getTargetedCombatant().isPlayer()) {
				openInventory((NPC) Main.combat.getEnemies(Main.game.getPlayer()).get(0), InventoryInteraction.COMBAT);
			} else {
				openInventory((NPC) Main.combat.getTargetedCombatant(), InventoryInteraction.COMBAT);
			}
			
		} else if(Main.game.isInSex()) {
			if(isInventoryDisabled()) {
				return;
			}
			openInventory(
					Main.sex.isMasturbation()
						?null
						:(NPC) Main.sex.getTargetedPartner(Main.game.getPlayer()),
					InventoryInteraction.SEX);
			
		} else if(Main.game.getDialogueFlags().getManagementCompanion() != null) {
			openInventory(Main.game.getDialogueFlags().getManagementCompanion(), InventoryInteraction.FULL_MANAGEMENT);
			
		} else {
			openInventory(null, InventoryInteraction.FULL_MANAGEMENT);
		}
	}
	
	public void openInventory(NPC npc, InventoryInteraction interaction) {
		if(!Main.game.isStarted()) {
			return;
		}

		RenderingEngine.setPageLeft(0);
		RenderingEngine.setPageRight(0);
		
		InventoryDialogue.setBuyback(false);
		InventoryDialogue.setInventoryNPC(npc);
		InventoryDialogue.setNPCInventoryInteraction(interaction);
		
		if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.INVENTORY) {
			if(Main.game.getDialogueFlags().getManagementCompanion() != null) {
				Main.game.setContent(new Response("", "", CompanionManagement.getCoreNode()) {
					@Override
					public void effects() {
						Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
						if(CompanionManagement.getCoreNode()==OccupantManagementDialogue.SLAVE_LIST) {
							CompanionManagement.initManagement(null, 0, null);
						}
					}
				});
				
			} else {
				Main.game.restoreSavedContent(Main.game.getDialogueFlags().getManagementCompanion() != null
//						|| Main.game.getSavedDialogueNode().getDialogueNodeType()==DialogueNodeType.OCCUPANT_MANAGEMENT
						);
			}

		} else if(!isInventoryDisabled() || npc!=null) {
			if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
//					|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OCCUPANT_MANAGEMENT
					) {
				Main.game.saveDialogueNode();
			}
			
			if(npc!=null) {
				npc.sortInventory();
			} else {
				Main.game.getPlayerCell().getInventory().sortInventory();
			}
			
			Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
		}
	}

	public void openCharactersPresent() {
		openCharactersPresent(null);
	}
	
	public void openCharactersPresent(GameCharacter characterViewed) {
		if(!Main.game.isStarted()) {
			return;
		}
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.coveringChangeListenersRequired, true);
		if(characterViewed!=null && characterViewed != CharactersPresentDialogue.characterViewed) {
			if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
//					|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OCCUPANT_MANAGEMENT
					) {
				Main.game.saveDialogueNode();
			}
			
			CharactersPresentDialogue.resetContent(characterViewed);
			Main.game.setContent(new Response("", "", CharactersPresentDialogue.MENU));
			
		} else {
			if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.CHARACTERS_PRESENT) {
				Main.game.restoreSavedContent(false);
				
			} else if (!Main.game.getCharactersPresent().isEmpty()) {
				if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
//						|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OCCUPANT_MANAGEMENT
						) {
					Main.game.saveDialogueNode();
				}
				
				CharactersPresentDialogue.resetContent(characterViewed);
				Main.game.setContent(new Response("", "", CharactersPresentDialogue.MENU));
			}
		}
	}
	
	/**
	 * Sets up buttons and hotkeys.
	 */
	public List<KeyCode> buttonsPressed = new ArrayList<>();

	private void setUpButtons() {
		// HOTKEYS:
		actionKeyPressed = new EventHandler<KeyEvent>() {
			
			private Map.Entry<KeyboardAction, KeyCodeWithModifiers> findExistingBinding(Map<KeyboardAction, KeyCodeWithModifiers> bindings, KeyEvent lookingFor){
				return bindings.entrySet()
					.stream()
					.filter(entry -> entry.getValue() != null)
					.filter(entry -> entry.getValue().matches(lookingFor))
					.findFirst().orElse(null);
			}
			
			private void printAlreadyExistingBinding(String primarySecondary, String actionName, String eventCodeName) {
				Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + "<b style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>The key '" + eventCodeName
						+ "' is already the " + primarySecondary + " bind for the action '" + actionName + "'!</b>" + "</p>");
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}
			
			private boolean handleExistingBindings(Map<KeyboardAction, KeyCodeWithModifiers> bindings, KeyEvent lookingFor, String primarySecondary) {
				Map.Entry<KeyboardAction, KeyCodeWithModifiers> existingBinding = findExistingBinding(bindings, lookingFor);
				boolean hasExistingBinding = existingBinding != null;
				if (hasExistingBinding) {
					actionToBind = null;
					printAlreadyExistingBinding(primarySecondary, existingBinding.getKey().getName(), existingBinding.getValue().getFullName());
				}
				return hasExistingBinding;
			}
			
			public void handle(KeyEvent event) {
				if (allowInput) {
					// Hotkey bindings:
					if (Main.game.getCurrentDialogueNode() == OptionsDialogue.KEYBINDS) {
						if (actionToBind != null) {
							KeyCode eventCode = event.getCode();
							
							//System.out.println(eventCode.getName()+" "+actionToBind);
							
							if (eventCode == KeyCode.SHIFT || eventCode == KeyCode.CONTROL) {
								return; // these are explicitly blocked to allow SHIFT + key and CTRL + key
							}
							
							if (handleExistingBindings(Main.getProperties().hotkeyMapPrimary, event, "primary")
								|| handleExistingBindings(Main.getProperties().hotkeyMapSecondary, event, "secondary")) {
								return; //such a binding already exists
							}
							KeyCodeWithModifiers newBinding = new KeyCodeWithModifiers(eventCode, event.isControlDown(), event.isShiftDown());
							if (primaryBinding)
								Main.getProperties().hotkeyMapPrimary.put(actionToBind, newBinding);
							else
								Main.getProperties().hotkeyMapSecondary.put(actionToBind, newBinding);
							actionToBind = null;
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							return;
						}
						
					} else {
						actionToBind = null;
					}
					
					if (!buttonsPressed.contains(event.getCode())) {
						buttonsPressed.add(event.getCode());

						System.arraycopy(lastKeys, 0, lastKeys, 1, 4);
						lastKeys[0] = event.getCode();
						checkLastKeys();
						
						if(event.getCode()==KeyCode.END && Main.DEBUG){
							
//							System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
							
							// Testing genetics/inheritance:
//							Main.game.getPlayer().guaranteePregnancyOnNextRoll();
//							Main.game.getPlayer().rollForPregnancy(Main.game.getCharactersPresent().get(0), 100, true);
//							for(OffspringSeed os : Main.game.getPlayer().getPregnantLitter().getOffspringSeed()) {
//								Covering covering = os.getBody().getCovering(os.getBody().getTorsoType().getBodyCoveringType(os.getBody()), false);
//								System.out.println(os.getName()+": "+os.getBody().getRaceStage().getName()+" "+os.getRace().getName(os.getBody(), os.isFeral()));
//								System.out.println("Primary: "+covering.getPrimaryColour().getName());
//								System.out.println("Secondary: "+covering.getPrimaryColour().getName());
//								
//								covering = os.getBody().getCovering(os.getBody().getEyeType().getBodyCoveringType(os.getBody()), false);
//								System.out.println("Eyes: "+covering.getPrimaryColour().getName());
//								
//								covering = os.getBody().getCovering(os.getBody().getHairType().getBodyCoveringType(os.getBody()), false);
//								System.out.println("Hair: "+covering.getPrimaryColour().getName());
//							}
							
//							Main.game.getCharactersPresent().get(0).incrementAttribute(Attribute.MAJOR_CORRUPTION, 100);
//							Main.game.getCharactersPresent().get(0).addFetish(Fetish.FETISH_BONDAGE_APPLIER);
							
//							Main.game.getCharactersPresent().get(0).addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PINK, false), 2, false, false);
//							Main.game.getCharactersPresent().get(0).removeFetish(Fetish.FETISH_CUM_STUD);
//							Main.game.getCharactersPresent().get(0).setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.TWO_NEUTRAL);
							
//							Main.game.getPlayer().incrementFetishExperience(Fetish.FETISH_DOMINANT, 200);
//							if(Main.game.isInSex()) {
//								for(GameCharacter c : Main.sex.getSubmissiveSpectators()) {
//									c.setArousal(98);
//									c.setLust(98);
//								}
//							}
//							System.out.println("START");
//							Util.addDrunkSlur(Util.addMuffle(Util.addBimbo(
//									"Hello, my name is [rose.name], would you like to see my [rose.hands(true)]? #IF(nyan.isFeminine())Or my [rose.breasts+]?#ENDIF Or should I call [lilaya.name]? Answer me now.",
//									4), 4), 4);
//							System.out.println("END");
							
//							System.out.println("-----");
//							for(NameTriplet nameTriplet : Name.getAllNameTriplets(Subspecies.getSubspeciesFromId("innoxia_hyena_subspecies_spotted"))) {
//								System.out.println(nameTriplet.getMasculine()+", "+nameTriplet.getAndrogynous()+", "+nameTriplet.getFeminine());
//							}
//							System.out.println("-----");
//							for(NameTriplet nameTriplet : Name.getAllNameTriplets(Subspecies.getSubspeciesFromId("innoxia_hyena_subspecies_striped"))) {
//								System.out.println(nameTriplet.getMasculine()+", "+nameTriplet.getAndrogynous()+", "+nameTriplet.getFeminine());
//							}
							
//							System.out.println("------");
//							for(AbstractCombatMove move : Main.game.getPlayer().getEquippedMoves()) {
//								System.out.println(move.getName(0, Main.game.getPlayer()));
//							}
							
//							System.out.println("--- Penis diameters ---");
//							for(int i=10; i<75; i+=5) {
//								System.out.println(i+" : "+Penis.getGenericDiameter(i, PenetrationGirth.THREE_AVERAGE));
//							}
							
//							if(Main.game.isInSex()) {
//								System.out.println("----");
//								for(GameCharacter character : Main.sex.getAllParticipants()) {
//									System.out.println(character.getName()+" -> "+Main.sex.getTargetedPartner(character).getName());
//								}
//							}

//							for(NPC npc : Main.game.getAllNPCs()) {
//								if(npc.isUnique() && !npc.hasArtwork()
////										&& (npc.getWorldLocation().getWorldRegion()==WorldRegion.DOMINION)
////										&& npc.isFeminine()
//										&& npc.getFaceType().getBodyCoveringType(npc).getCategory()!=BodyCoveringCategory.MAIN_SKIN
//										&& npc.isAbleToBeImpregnated()
//										&& npc.isFeminine()
////										&& (npc.getClass().getName().contains("dominion.") || npc.getClass().getName().contains("submission."))
//										) {
//									System.out.println(npc.getNameIgnoresPlayerKnowledge() + " "+npc.getClass().getName().split(".npc.")[1]);// + " " + npc.getSurname());
//								}
//							}
							
//							Map<AbstractSubspecies, Integer> subCountMap = new HashMap<>();
//							Map<AbstractSubspecies, Integer> subHDCountMap = new HashMap<>();
//							for(NPC npc : Main.game.getAllNPCs()) {
//								if(npc.isUnique()) {
//									if(npc.getSubspecies()==Subspecies.HALF_DEMON) {
//										AbstractSubspecies ss = npc.getHalfDemonSubspecies();
//										subHDCountMap.putIfAbsent(ss, 0);
//										subHDCountMap.put(ss, subHDCountMap.get(ss)+1);
//										
//									} else {
//										AbstractSubspecies ss = npc.getTrueSubspecies();
//										subCountMap.putIfAbsent(ss, 0);
//										subCountMap.put(ss, subCountMap.get(ss)+1);
//									}
//								}
//							}
//							for(AbstractSubspecies ss : Subspecies.getAllSubspecies()) {
//								subCountMap.putIfAbsent(ss, 0);
//								subHDCountMap.putIfAbsent(ss, 0);
//								System.out.println(ss.getName(null)+": "+subCountMap.get(ss)+" "+subHDCountMap.get(ss));
//							}
							
//							Main.game.getPlayer().incrementPerkCategoryPoints(PerkCategory.PHYSICAL, 1);
//							Main.game.getPlayer().incrementPerkCategoryPoints(PerkCategory.ARCANE, 1);
//							Main.game.getPlayer().incrementPerkCategoryPoints(PerkCategory.LUST, 1);
//							for(NPC npc : Main.game.getCharactersTreatingCellAsHome()) {
//								System.out.println(npc.getNameIgnoresPlayerKnowledge()+npc.getClass().getName());
//							}
//							Main.sex.getTargetedPartner(Main.game.getPlayer()).generateSexChoices(true, Main.game.getPlayer());
//							Main.game.getPlayer().setFeral(Subspecies.HORSE_MORPH);
//							UtilText.addSpecialParsingString("true", true);
//							System.out.println(UtilText.parse("#IF([#SPECIAL_PARSE_0]):3#ELSE:(#ENDIF"));
//							System.out.println(Main.sex.isSexStarted());
						}
						

						// Escape Menu:
						if (keyEventMatchesBindings(KeyboardAction.MENU, event)) {
							openOptions();
						}
						
						// Misc.:
						if (keyEventMatchesBindings(KeyboardAction.FULL_SCREEN, event)) {
							Main.primaryStage.setFullScreenExitHint("Press 'Esc' or '"+KeyboardAction.FULL_SCREEN.getPrimaryDefault().getFullName()+"' to exit fullscreen mode.");
							Main.primaryStage.setFullScreen(!Main.primaryStage.isFullScreen());
						}
						
						// Movement:
						if (keyEventMatchesBindings(KeyboardAction.MOVE_NORTH, event)) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled()) {
								moveNorth();
							} else {
								Main.game.responseNavigationUp();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_WEST, event)) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled()) {
								moveWest();
							} else {
								Main.game.responseNavigationLeft();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_SOUTH, event)) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled()) {
								moveSouth();
							} else {
								Main.game.responseNavigationDown();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_EAST, event)) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled()) {
								moveEast();
							} else {
								Main.game.responseNavigationRight();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_RESPONSE_CURSOR_NORTH, event)) {
							Main.game.responseNavigationUp();
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_RESPONSE_CURSOR_WEST, event)) {
							Main.game.responseNavigationLeft();
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_RESPONSE_CURSOR_SOUTH, event)) {
							Main.game.responseNavigationDown();
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_RESPONSE_CURSOR_EAST, event)) {
							Main.game.responseNavigationRight();
						}

						// Game stuff:
						if (keyEventMatchesBindings(KeyboardAction.QUICKSAVE, event)) {
							Main.quickSaveGame();
						}
						if (keyEventMatchesBindings(KeyboardAction.QUICKLOAD, event)) {
							Main.quickLoadGame();
						}
						
						boolean allowInput = true;
						boolean enterConsumed = false;
						
						// Name selections:
						if(Main.game.getCurrentDialogueNode() == CharacterCreation.CHOOSE_NAME || Main.game.getCurrentDialogueNode() == CityHallDemographics.NAME_CHANGE){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameMasculineInput') === document.activeElement")
									|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameAndrogynousInput') === document.activeElement")
									|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameFeminineInput') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									Main.game.setContent(1);
								}
							}
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('surnameInput') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									Main.game.setContent(1);
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.ROOM_UPGRADES){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									boolean unsuitableName = false;
									if(Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput')")!=null) {
										
										Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
										if(Main.mainController.getWebEngine().getDocument()!=null) {
											unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
															|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
										}
										
										if (!unsuitableName) {
											Main.game.setContent(new Response("Rename Room", "Rename this room to whatever you've entered in the text box.", Main.game.getCurrentDialogueNode()){
												@Override
												public void effects() {
													Main.game.getPlayerCell().getPlace().setName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
												}
											});
										} else {
											Main.game.setContent(new Response("Rename Room", "", Main.game.getCurrentDialogueNode()));
										}
									}
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('new_save_name') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
									if(Main.isSaveGameAvailable()) {
										Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, false);
									}
									Main.game.setContent(new Response("Save", "", Main.game.getCurrentDialogueNode()));
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == EnchantmentDialogue.ENCHANTMENT_MENU){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('output_name') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
								} else {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
									EnchantmentDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('new_save_name') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
									EnchantmentDialogue.saveEnchant(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD);
									Main.game.setContent(new Response("Save", "", Main.game.getCurrentDialogueNode()));
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == BodyChanging.BODY_CHANGING_SAVE_LOAD){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('new_save_name') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
									BodyChanging.saveBody(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
									Main.game.setContent(new Response("Save", "", Main.game.getCurrentDialogueNode()));
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS_ADD
								|| Main.game.getCurrentDialogueNode() == CompanionManagement.SLAVE_MANAGEMENT_TATTOOS_ADD
								|| Main.game.getCurrentDialogueNode() == CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD
								|| Main.game.getCurrentDialogueNode() == CosmeticsDialogue.BEAUTICIAN_TATTOOS_ADD){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('tattoo_name') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
								} else {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == CompanionManagement.OCCUPANT_CHOOSE_NAME
								|| Main.game.getCurrentDialogueNode() == ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY
								|| Main.game.getCurrentDialogueNode() == KaysWarehouse.KAY_OFFICE_DOMINATE_NAMING
								|| Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_CHOOSE_NAME) {
							
							GameCharacter slave = Main.game.getDialogueFlags().getManagementCompanion();
							if(Main.game.getCurrentDialogueNode() == ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
								slave = BodyChanging.getTarget();
							} else if(Main.game.getCurrentDialogueNode() == KaysWarehouse.KAY_OFFICE_DOMINATE_NAMING) {
								slave = Main.game.getNpc(Kay.class);
							} else if(Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_CHOOSE_NAME) {
								slave = Main.game.getPlayer().getElemental();
							}
							GameCharacter slaveFinal = slave;
							
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput') === document.activeElement")) {
								allowInput = false;
								if(event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									boolean unsuitableName = false;
								 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')")!=null) {
									 
										Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
										if(Main.mainController.getWebEngine().getDocument()!=null) {
											unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
															|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
										}
										
										if(!unsuitableName) {
											Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
												@Override
												public void effects() {
													slaveFinal.setPetName(Main.game.getPlayer(), Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
												}
											});
										} else {
											Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
										}
										
									}
								}
							}
							if(((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('slaveNameInput') === document.activeElement"))) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									boolean unsuitableName = false;
								 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveNameInput')")!=null) {
									 
										Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveNameInput').value;");
										if(Main.mainController.getWebEngine().getDocument()!=null) {
											unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
															|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
										}
										
										if (!unsuitableName) {
											Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
												@Override
												public void effects() {
													slaveFinal.setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
												}
											});
										} else {
											Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
										}
									}
								}
							}
							if(((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('slaveSurnameInput') === document.activeElement"))) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									boolean unsuitableName = false;
								 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveSurnameInput')")!=null) {
									 
										Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveSurnameInput').value;");
										if(Main.mainController.getWebEngine().getDocument()!=null) {
											unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
															|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
										}
										
										if (!unsuitableName) {
											Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
												@Override
												public void effects() {
													slaveFinal.setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
												}
											});
										} else {
											Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
										}
									}
								}
							}
						}
						
						//TODO for some reason, if this catches a ResponseEffectsOnly and sets content using that, it blanks the main page. I have absolutely no idea why.
						if(Main.game.getCurrentDialogueNode() == OptionsDialogue.OPTIONS_PRONOUNS) {
							allowInput = false;
							if (event.getCode() == KeyCode.ENTER) {
								enterConsumed = true;
								boolean found = false;
								for(GenderPronoun gp : GenderPronoun.values()) {
									if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('feminine_" + gp + "') === document.activeElement")
										|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('masculine_" + gp + "') === document.activeElement")) {
										Main.game.setContent(
												new Response("Save", "Save all the pronouns that are currently displayed.", OptionsDialogue.OPTIONS_PRONOUNS) {
													@Override
													public void effects() {
														OptionsDialogue.OPTIONS_PRONOUNS.getResponse(0, 1).effects();
													}
												});
										found = true;
										break;
									}
								}
								if(!found) {
									for(GenderNames genderName : GenderNames.values()) {
										if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_MASCULINE_" + genderName + "') === document.activeElement")
											|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_ANDROGYNOUS_" + genderName + "') === document.activeElement")
											|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_FEMININE_" + genderName + "') === document.activeElement")) {
											Main.game.setContent(
													new Response("Save", "Save all the pronouns that are currently displayed.", OptionsDialogue.OPTIONS_PRONOUNS) {
														@Override
														public void effects() {
															OptionsDialogue.OPTIONS_PRONOUNS.getResponse(0, 1).effects();
														}
													});
										}
									}
								}
							}
						}
						
						
						if(((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('offspringPetNameInput') === document.activeElement"))) {
							allowInput = false;
							if (event.getCode() == KeyCode.ENTER) {
								enterConsumed = true;
								boolean unsuitableName = false;
							 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('offspringPetNameInput')")!=null) {
								 
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('offspringPetNameInput').value;");
									if(Main.mainController.getWebEngine().getDocument()!=null) {
										if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
												|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32)
											unsuitableName = true;
										else {
											unsuitableName = false;
										}
									}
									
									if (!unsuitableName) {
										Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
											@Override
											public void effects() {
												Main.game.getActiveNPC().setPetName(Main.game.getPlayer(), Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
											}
										});
									} else {
										Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
									}
									
								}
							}
						}
						
						if(Main.game.getCurrentDialogueNode() == DebugDialogue.PARSER){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('parseInput') === document.activeElement")
									|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('xmlTest') === document.activeElement")) {
								allowInput = false;
							}
						}

						// Allow users to type in slave metadata without getting whisked away.
						if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('SET_SLAVE_NOTES') === document.activeElement")
						|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('SET_SLAVE_CATEGORY') === document.activeElement")) {
							allowInput = false;
						}
						
						if(allowInput){
							if (keyEventMatchesBindings(KeyboardAction.INVENTORY, event)) {
								openInventory();
							}
							if (keyEventMatchesBindings(KeyboardAction.JOURNAL, event)) {
								openPhone();
							}
							if (keyEventMatchesBindings(KeyboardAction.MAP, event)) {
								openPhone(PhoneDialogue.MAP);
							}
							if (keyEventMatchesBindings(KeyboardAction.CHARACTERS, event)) {
								openCharactersPresent(null);
							}
							if (keyEventMatchesBindings(KeyboardAction.ZOOM, event)) {
								zoomMap();
							}
	
							if (keyEventMatchesBindings(KeyboardAction.SCROLL_UP, event))
								Main.mainController.getWebEngine().executeScript("document.getElementById('main-content').scrollTop -= 50");
							if (keyEventMatchesBindings(KeyboardAction.SCROLL_DOWN, event))
								Main.mainController.getWebEngine().executeScript("document.getElementById('main-content').scrollTop += 50");
							
							// Responses:
							KeyboardAction[] keyboardActionsForResponses =
								{
										KeyboardAction.RESPOND_0, KeyboardAction.RESPOND_1, KeyboardAction.RESPOND_2, KeyboardAction.RESPOND_3, KeyboardAction.RESPOND_4,
										KeyboardAction.RESPOND_5, KeyboardAction.RESPOND_6, KeyboardAction.RESPOND_7, KeyboardAction.RESPOND_8, KeyboardAction.RESPOND_9,
										KeyboardAction.RESPOND_10, KeyboardAction.RESPOND_11, KeyboardAction.RESPOND_12, KeyboardAction.RESPOND_13, KeyboardAction.RESPOND_14
								};
							
							for (int i = 0; i < keyboardActionsForResponses.length; i++) {
								if (keyEventMatchesBindings(keyboardActionsForResponses[i], event)) {
									processResponse(i);
								}
							}
							
							if (keyEventMatchesBindings(KeyboardAction.MENU_SELECT, event)) {
								if(event.getCode() == KeyCode.ENTER) {
									if(!enterConsumed) {
										Main.game.setContent(Main.game.getResponsePointer());
									}
								} else {
									Main.game.setContent(Main.game.getResponsePointer());
								}
							}
							
						}
						
						// Next/Previous response tab:
						if (keyEventMatchesBindings(KeyboardAction.RESPOND_NEXT_TAB, event)) {
							if (Main.game.incrementResponseTab()) {
								Main.game.updateResponses();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.RESPOND_PREVIOUS_TAB, event)) {
							if (Main.game.decrementResponseTab()) {
								Main.game.updateResponses();
							}
						}
						
						// Next/Previous response page:
						if (keyEventMatchesBindings(KeyboardAction.RESPOND_NEXT_PAGE, event)) {
							if (Main.game.isHasNextResponsePage()) {
								Main.game.setResponsePage(Main.game.getResponsePage() + 1);
								Main.game.updateResponses();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.RESPOND_PREVIOUS_PAGE, event)) {
							if (Main.game.getResponsePage() != 0) {
								Main.game.setResponsePage(Main.game.getResponsePage() - 1);
								Main.game.updateResponses();
							}
						}
					}
				}
			}
		};

		actionKeyReleased = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (buttonsPressed.contains(event.getCode())) {
					buttonsPressed.remove(event.getCode());
				}
			}
		};

		Main.primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, actionKeyPressed);
		Main.primaryStage.addEventFilter(KeyEvent.KEY_RELEASED, actionKeyReleased);
	}

	private void processResponse(int index) {
		if (Main.game.getResponsePage() == 0) {
			Main.game.setContent(Main.game.getResponsePage() * RESPONSE_COUNT + index);
			
		} else {
			if (index != 0) {
				Main.game.setContent(Main.game.getResponsePage() * RESPONSE_COUNT + index - 1);
			} else {
				Main.game.setContent(Main.game.getResponsePage() * RESPONSE_COUNT + (RESPONSE_COUNT-1));
			}
		}
	}

	// Event listeners:
	
	// General tooltips:
	static TooltipMoveEventListener moveTooltipListener = new TooltipMoveEventListener();
	static TooltipHideEventListener hideTooltipListener = new TooltipHideEventListener();
	
	// Buttons:
	static ButtonCopyDialogueEventListener copyDialogueButtonListener = new ButtonCopyDialogueEventListener();
	static ButtonCharactersEventListener charactersPresentButtonListener = new ButtonCharactersEventListener();
	static ButtonInventoryEventHandler inventoryButtonListener = new ButtonInventoryEventHandler();
	static ButtonJournalEventListener journalButtonListener = new ButtonJournalEventListener();
	static ButtonMainMenuEventListener menuButtonListener = new ButtonMainMenuEventListener();
	static ButtonZoomEventListener zoomButtonListener = new ButtonZoomEventListener();
	
	// Map movement:
	private ButtonMoveNorthEventListener moveNorthListener = new ButtonMoveNorthEventListener();
	private ButtonMoveSouthEventListener moveSouthListener = new ButtonMoveSouthEventListener();
	private ButtonMoveEastEventListener moveEastListener = new ButtonMoveEastEventListener();
	private ButtonMoveWestEventListener moveWestListener = new ButtonMoveWestEventListener();
	
	// Responses:
	static TooltipResponseMoveEventListener responseTooltipListener = new TooltipResponseMoveEventListener();
	static SetContentEventListener nextResponsePageListener = new SetContentEventListener().nextPage();
	static SetContentEventListener previousResponsePageListener = new SetContentEventListener().previousPage();
	
	// Temporary ones to clear:
	static Map<Document, List<EventListenerData>> EventListenerDataMap = new HashMap<>();
	
	private void unbindListeners(Document document) {
		cookieManager.getCookieStore().removeAll();
		if(document!=null) {
			for(EventListenerData data : EventListenerDataMap.get(document)) {
				((EventTarget) document.getElementById(data.ID)).removeEventListener(data.type, data.listener, data.useCapture);
			}
			EventListenerDataMap.get(document).clear();
			
			EventListenerDataMap.remove(document);
		}
	}
	
	static void addEventListener(Document document, String ID, String type, EventListener listener, boolean useCapture) {
		((EventTarget) document.getElementById(ID)).addEventListener(type, listener, useCapture);
		EventListenerDataMap.get(document).add(new EventListenerData(ID, type, listener, useCapture));
	}
	
	public static void addTooltipListeners(String id, EventListener tooltip) {
		addTooltipListeners(id, tooltip, null, false);
	}
	
	public static void addTooltipListeners(String id, EventListener tooltip, EventListener click, boolean capture) {
		addEventListener(document, id, "mousemove", moveTooltipListener, false);
		addEventListener(document, id, "mouseleave", hideTooltipListener, false);
		addEventListener(document, id, "mouseenter", tooltip, false);
		if(click !=null) {
			addEventListener(document, id, "click", click, capture);
		}
	}
	
	public static Document document, documentButtonsLeft, documentButtonsRight, documentAttributes, documentRight, documentInventory, documentMap, documentMapTitle;
	private boolean debugAllowListeners = true;
	/**
	 * Sets up all WebView EventListeners and WebEngines.
	 */
	private void setUpWebViews() {

		java.net.CookieHandler.setDefault(cookieManager);
		
		// Tooltip WebView:
		webviewTooltip.setContextMenuEnabled(false);
		webEngineTooltip = webviewTooltip.getEngine();
		webEngineTooltip.setJavaScriptEnabled(false);
		webEngineTooltip.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineTooltip.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewTooltip_stylesheet_light.css").toExternalForm());
		} else {
			webEngineTooltip.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewTooltip_stylesheet.css").toExternalForm());
		}
		
		// Main WebView:
		webViewMain.setContextMenuEnabled(false);
		webEngine = webViewMain.getEngine();
//		webEngine.setJavaScriptEnabled(false);
		webEngine.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngine.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webView_stylesheet_light.css").toExternalForm());
		} else {
			webEngine.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webView_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners) {
			webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
				if (newState == State.SUCCEEDED) {
					unbindListeners(document);
					manageMainListeners();
				}
			});
		}
		webEngine.executeScript("var timer = false;");

		// Buttons webview:

		webViewButtonsLeft.setContextMenuEnabled(false);
		webEngineButtonsLeft = webViewButtonsLeft.getEngine();
		webEngineButtonsLeft.setJavaScriptEnabled(false);
		webEngineButtonsLeft.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineButtonsLeft.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
		} else {
			webEngineButtonsLeft.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
		webEngineButtonsLeft.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(documentButtonsLeft);
				manageButtonLeftListeners();
			}
		});

		webViewButtonsRight.setContextMenuEnabled(false);
		webEngineButtonsRight = webViewButtonsRight.getEngine();
		webEngineButtonsRight.setJavaScriptEnabled(false);
		webEngineButtonsRight.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineButtonsRight.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
		} else {
			webEngineButtonsRight.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
		webEngineButtonsRight.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(documentButtonsRight);
				manageButtonRightListeners();
			}
		});
		
		
		// Attributes WebView:
		webViewAttributes.setContextMenuEnabled(false);
		webEngineAttributes = webViewAttributes.getEngine();
		webEngineAttributes.setJavaScriptEnabled(false);
		webEngineAttributes.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineAttributes.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
		} else {
			webEngineAttributes.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners){
			webEngineAttributes.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
				if (newState == State.SUCCEEDED) {
					unbindListeners(documentAttributes);
					manageAttributeListeners();
				}
			});
		}
		
		// Attributes WebView:
		webViewRight.setContextMenuEnabled(false);
		webEngineRight = webViewRight.getEngine();
		webEngineRight.setJavaScriptEnabled(false);
		webEngineRight.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineRight.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
		} else {
			webEngineRight.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners) {
			webEngineRight.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
				if (newState == State.SUCCEEDED) {
					unbindListeners(documentRight);
					manageRightListeners();
				}
			});
		}
		
	}
	
	private void manageMainListeners() {
		document = (Document) webEngine.executeScript("document");
		EventListenerDataMap.put(document, new ArrayList<>());
		
		if (flashMessageColour != null && flashMessageText != null) {
			Main.game.flashMessage(flashMessageColour, flashMessageText);
			flashMessageColour = null;
			flashMessageText = null;
		}
		
		// Initialise tooltip listeners if there are tooltips to be initialised
		if (Game.informationTooltips.size() > 0) {
			MiscController.initInfoTooltipListeners();
		}
		
		// There are too many dialogue nodes that require CoveringChangeListeners so better to check by flag
		if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.coveringChangeListenersRequired)) {
			CoveringController.initCoveringChangeListeners();
		}
		
		// Initialise dice listeners if there are dice to be initialised
		if (Main.game.isStarted() && DicePoker.progress>0) {
			for (int i = 0; i<DicePoker.getPlayerDice().size(); i++) {
				String id = "DICE_PLAYER_"+i;
				if ((MainController.document.getElementById(id)) != null) {
					int finalI = i;
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(DicePoker.isAbleToSelectReroll()) {
							DicePoker.setReroll(DicePoker.getPlayerDice().get(finalI));
						}
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"Mark for reroll",
							DicePoker.isAbleToSelectReroll()?"":"[style.italicsBad(You cannot mark your dice for reroll in this scene!)]"));
				}
			}
		}
		
		DialogueNode currentNode = Main.game.getCurrentDialogueNode();
		// Init combat listeners separately, as otherwise no menu buttons work
		if (Main.game.isInCombat()) {
			MiscController.initCombatListeners();
			if(currentNode.getDialogueNodeType() == DialogueNodeType.INVENTORY) {
				InventoryController.initInventoryListeners();
			}
		}
		
		if (currentNode.equals(BodyChanging.BODY_CHANGING_ASS)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS)) {
			CreationController.initAnusCapacityListeners();
			CreationController.initAnusDepthListeners();
			CreationController.initAnusElasticityListeners();
			CreationController.initAnusModifierListeners();
			CreationController.initAnusPlasticityListeners();
			CreationController.initAnusWetnessListeners();
			CreationController.initAssTypeListeners();
			CreationController.initAssSizeListeners(false);
			CreationController.initHipSizeListeners(false);
			if (Main.game.isAssHairEnabled()) {
				CoveringController.initAssHairListeners();
			}
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_BREASTS)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS)) {
			CreationController.initAreolaeSizeListeners();
			CreationController.initBreastCountListeners();
			CreationController.initBreastShapeListeners();
			CreationController.initBreastSizeListeners(false);
			CreationController.initBreastTypeListeners();
			CreationController.initLactationCapacityListeners();
			CreationController.initLactationFlavourListeners();
			CreationController.initLactationModifierListeners();
			CreationController.initLactationRegenListeners();
			CreationController.initNippleCountListeners();
			CreationController.initNippleModifierListeners();
			CreationController.initNippleShapeListeners();
			CreationController.initNippleSizeListeners();
			if (BodyChanging.isDebugMenu() || Main.game.isNipplePenEnabled()) {
				CreationController.initNippleCapacityListeners();
				CreationController.initNippleDepthListeners();
				CreationController.initNippleElasticityListeners();
				CreationController.initNipplePlasticityListeners();
			}
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_BREASTS_CROTCH)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH)) {
			CreationController.initCrotchAreolaeSizeListeners();
			CreationController.initCrotchBreastCountListeners();
			CreationController.initCrotchBreastShapeListeners();
			CreationController.initCrotchBreastSizeListeners();
			CreationController.initCrotchBreastTypeListeners();
			CreationController.initCrotchLactationCapacityListeners();
			CreationController.initCrotchLactationFlavourListeners();
			CreationController.initCrotchLactationModifierListeners();
			CreationController.initCrotchLactationRegenListeners();
			CreationController.initCrotchNippleCountListeners();
			CreationController.initCrotchNippleModifierListeners();
			CreationController.initCrotchNippleShapeListeners();
			CreationController.initCrotchNippleSizeListeners();
			if (BodyChanging.isDebugMenu() || Main.game.isNipplePenEnabled()) {
				CreationController.initCrotchNippleCapacityListeners();
				CreationController.initCrotchNippleDepthListeners();
				CreationController.initCrotchNippleElasticityListeners();
				CreationController.initCrotchNipplePlasticityListeners();
			}
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_CORE)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE)) {
			CreationController.initAgeAppearanceListeners();
			CreationController.initArmCountListeners();
			CreationController.initArmTypeListeners();
			CreationController.initBodySizeListeners();
			CreationController.initFaceTypeListeners();
			CreationController.initFemininityListeners();
			CreationController.initFootStructureListeners();
			CreationController.initGenitalArrangementListeners();
			CreationController.initHeightListeners();
			CreationController.initLegConfigListeners();
			CreationController.initLegTypeListeners();
			CreationController.initMuscleListeners();
			CreationController.initTailCountListeners();
			CreationController.initTailGirthListeners();
			CreationController.initTailLengthListeners();
			CreationController.initTailTypeListeners();
			CreationController.initTentacleGirthListeners();
			CreationController.initTentacleLengthListeners();
			CreationController.initTorsoTypeListeners();
			CreationController.initWingSizeListeners();
			CreationController.initWingTypeListeners();
			if (BodyChanging.getTarget().isElemental()) {
				CreationController.initBodyMaterialListeners();
			}
			if (Main.game.isBodyHairEnabled()) {
				CoveringController.initUnderarmHairListeners();
			}
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_EYES)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES)) {
			CreationController.initEyeCountListeners();
			CreationController.initEyeShapeListeners();
			CreationController.initEyeTypeListeners();
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_HAIR)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR)
				|| currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_COSMETICS_HAIR)
				|| currentNode.equals(RoomPlayer.AUNT_HOME_PLAYERS_ROOM_MAKEUP)) {
			CoveringController.initHairLengthListeners();
			CoveringController.initHairStyleListeners();
			CreationController.initHairTypeListeners();
			CreationController.initNeckFluffListeners();
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_HEAD)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD)) {
			CreationController.initAntennaCountListeners();
			CreationController.initAntennaLengthListeners();
			CreationController.initAntennaRowsListeners();
			CreationController.initAntennaTypeListeners();
			CreationController.initEarTypeListeners();
			CreationController.initHornCountListeners();
			CreationController.initHornLengthListeners();
			CreationController.initHornRowsListeners();
			CreationController.initHornTypeListeners();
			CreationController.initLipSizeListeners();
			CreationController.initThroatCapacityListeners();
			CreationController.initThroatDepthListeners();
			CreationController.initThroatElasticityListeners();
			CreationController.initThroatModifierListeners();
			CreationController.initThroatPlasticityListeners();
			CreationController.initThroatWetnessListeners();
			CreationController.initTongueLengthListeners();
			CreationController.initTongueModifierListeners();
			if (Main.game.isFacialHairEnabled() && (!BodyChanging.getTarget().isFeminine() || Main.game.isFemaleFacialHairEnabled())) {
				CoveringController.initFacialHairListeners();
			}
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_PENIS)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS)) {
			CreationController.initPenisGirthListeners();
			CreationController.initPenisModifierListeners();
			CreationController.initPenisSizeListeners(false);
			CreationController.initPenisTypeListeners();
			CreationController.initPenisCumExpulsionListeners();
			CreationController.initPenisCumFlavourListeners();
			CreationController.initPenisCumModifierListeners();
			CreationController.initPenisCumProductionListeners();
			CreationController.initPenisCumRegenListeners();
			CreationController.initTesticleCountListeners();
			CreationController.initTesticleInternalListeners();
			CreationController.initTesticleSizeListeners(false);
			CreationController.initUrethraCapacityListeners();
			CreationController.initUrethraDepthListeners();
			CreationController.initUrethraElasticityListeners();
			CreationController.initUrethraModifierListeners();
			CreationController.initUrethraPlasticityListeners();
			if (Main.game.isPubicHairEnabled()) {
				CoveringController.initPubicHairListeners();
			}
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_SPINNERET)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET)) {
			CreationController.initSpinneretCapacityListeners();
			CreationController.initSpinneretDepthListeners();
			CreationController.initSpinneretElasticityListeners();
			CreationController.initSpinneretModifierListeners();
			CreationController.initSpinneretPlasticityListeners();
			CreationController.initSpinneretWetnessListeners();
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_VAGINA)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA)) {
			CreationController.initVaginaCapacityListeners();
			CreationController.initVaginaDepthListeners();
			CreationController.initVaginaEggLayerListeners();
			CreationController.initVaginaElasticityListeners();
			CreationController.initVaginaHymenListeners();
			CreationController.initVaginaModifierListeners();
			CreationController.initVaginaPlasticityListeners();
			CreationController.initVaginaSquinterListeners();
			CreationController.initVaginaTypeListeners();
			CreationController.initVaginaWetnessListeners();
			CreationController.initVaginaCumFlavourListeners();
			CreationController.initVaginaCumModifierListeners();
			CreationController.initClitorisGirthListeners();
			CreationController.initClitorisModifierListeners();
			CreationController.initClitorisSizeListeners(false);
			CreationController.initLabiaSizeListeners();
			CreationController.initVaginaUrethraCapacityListeners();
			CreationController.initVaginaUrethraDepthListeners();
			CreationController.initVaginaUrethraElasticityListeners();
			CreationController.initVaginaUrethraModifierListeners();
			CreationController.initVaginaUrethraPlasticityListeners();
			if (Main.game.isPubicHairEnabled()) {
				CoveringController.initPubicHairListeners();
			}
		} else if (currentNode.equals(BodyChanging.BODY_CHANGING_SAVE_LOAD)) {
			FileController.initBodySaveLoadListeners();
		} else if (currentNode.equals(CharacterCreation.BACKGROUND_SELECTION_MENU)) {
			CreationController.initBackgroundSelectionListeners();
		} else if (currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_COSMETICS_OTHER)) {
			CoveringController.initBleachingListeners();
			CoveringController.initAssHairListeners();
			CoveringController.initFacialHairListeners();
			CoveringController.initPubicHairListeners();
			CoveringController.initUnderarmHairListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_ASS)) {
			CoveringController.initBleachingListeners();
			CreationController.initAssSizeListeners(true);
			CreationController.initHipSizeListeners(true);
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_BREASTS)) {
			CreationController.initAreolaeSizeListeners();
			CreationController.initBreastShapeListeners();
			CreationController.initBreastSizeListeners(true);
			CreationController.initNipplePuffinessListeners();
			CreationController.initNippleSizeListeners();
			CreationController.initLactationCapacityListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR)) {
			CoveringController.initAssHairListeners();
			CoveringController.initFacialHairListeners();
			CoveringController.initPubicHairListeners();
			CoveringController.initUnderarmHairListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_CORE)) {
			CreationController.initBodySizeListeners();
			CreationController.initHeightListeners();
			CreationController.initMuscleListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_FACE)) {
			CreationController.initLipSizeListeners();
			CreationController.initLipPuffinessListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_GENITALS)) {
			if (Main.game.getPlayer().hasPenis()) {
				CreationController.initPenisSizeListeners(true);
				CreationController.initTesticleSizeListeners(true);
				CreationController.initPenisCumProductionListeners();
			} else {
				CreationController.initClitorisSizeListeners(true);
				CreationController.initLabiaSizeListeners();
				CreationController.initVaginaCapacityListeners();
			}
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_HAIR)) {
			CoveringController.initHairLengthListeners();
			CoveringController.initHairStyleListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_PIERCINGS)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS)
				|| currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_COSMETICS_PIERCINGS)
				|| currentNode.equals(SuccubisSecrets.SHOP_BEAUTY_SALON_PIERCINGS)) {
			CoveringController.initPiercingsListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS)
				|| currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_TATTOOS)
				|| currentNode.equals(CosmeticsDialogue.BEAUTICIAN_TATTOOS)
				|| currentNode.equals(SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS)) {
			CreationController.initTattooInfoListeners(currentNode);
		} else if (currentNode.equals(CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD)
				|| currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_TATTOOS_ADD)
				|| currentNode.equals(CosmeticsDialogue.BEAUTICIAN_TATTOOS_ADD)
				|| currentNode.equals(SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS_ADD)) {
			CreationController.initTattooAddListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_APPEARANCE)) {
			CreationController.initAgeListeners();
			CreationController.initBirthdayListeners();
			CreationController.initFemininityPresetListeners();
			CreationController.initGenderListeners();
			CreationController.initOrientationListeners();
			CreationController.initPersonalityListeners();
		} else if (currentNode.equals(CharacterCreation.CHOOSE_SEX_EXPERIENCE)
				|| currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_FINISH)) {
			CreationController.initSexExperienceListeners();
			CreationController.initFetishListeners();
		} else if (currentNode.equals(CharacterCreation.IMPORT_CHOOSE)) {
			FileController.initPlayerImportListeners();
		} else if (currentNode.equals(CharactersPresentDialogue.MENU)) {
			FileController.initArtworkListeners();
			MiscController.initPerkListeners(currentNode);
		} else if (currentNode.equals(CityHall.LODGER_IMPORT)) {
			FileController.initLodgerImportListeners();
		} else if (currentNode.equals(CityHall.CITY_HALL_WAITING_AREA_LODGER_LIST)) {
			FileController.initLodgerWaitingListeners();
		} else if (currentNode.equals(NightlifeDistrict.WATERING_HOLE_IMPORT)) {
			FileController.initClubberImportListeners();
		} else if (currentNode.equals(CombatMovesSetup.COMBAT_MOVES_CORE)) {
			MiscController.initCombatMoveListeners();
		} else if (currentNode.equals(CompanionManagement.OCCUPANT_CHOOSE_NAME)
				|| currentNode.equals(ElementalDialogue.ELEMENTAL_CHOOSE_NAME)) {
			MiscController.initRenameListeners();
		} else if (currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_INSPECT)
				|| currentNode.equals(PhoneDialogue.CONTACTS_CHARACTER)
				|| currentNode.equals(PhoneDialogue.CHARACTER_APPEARANCE)) {
			FileController.initArtworkListeners();
		} else if (currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_JOBS)) {
			OccupantController.initSlaveJobListeners();
		} else if (currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_PERMISSIONS)) {
			OccupantController.initSlavePermissionsListeners();
		} else if (currentNode.equals(CharactersPresentDialogue.PERKS)
				|| currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_PERKS)
				|| currentNode.equals(ElementalDialogue.ELEMENTAL_PERKS)
				|| currentNode.equals(PhoneDialogue.CHARACTER_APPEARANCE)
				|| currentNode.equals(PhoneDialogue.CHARACTER_PERK_TREE)) {
			MiscController.initPerkListeners(currentNode);
		} else if (currentNode.equals(DebugDialogue.SPAWN_MENU)
				|| currentNode.equals(DebugDialogue.ITEM_VIEWER)) {
			DebugController.initSpawnItemListeners();
		} else if (currentNode.equals(DebugDialogue.SPAWN_MENU_SET)) {
			DebugController.initSpawnSetListeners();
		} else if (currentNode.equals(ElementalDialogue.ELEMENTAL_FETISHES)
				|| currentNode.equals(PhoneDialogue.CHARACTER_FETISHES)) {
			MiscController.initFetishListeners();
		} else if (currentNode.equals(EnchantmentDialogue.ENCHANTMENT_MENU)) {
			EnchantmentController.initEnchantmentMenuListeners();
		} else if (currentNode.equals(EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD)) {
			FileController.initEnchantmentSaveLoadListeners();
		} else if (currentNode.equals(GenericOffspringDialogue.OFFSPRING_ENCOUNTER_CHOOSE_NAME)) {
			MiscController.initFamilyRenameListeners();
		} else if (currentNode.equals(GiftDialogue.GIFT_DIALOGUE)) {
			MiscController.initGiftListeners();
		} else if (currentNode.getDialogueNodeType() == DialogueNodeType.INVENTORY) {
			if (currentNode.equals(InventoryDialogue.DYE_CLOTHING)
					|| currentNode.equals(InventoryDialogue.DYE_CLOTHING_CHARACTER_CREATION)
					|| currentNode.equals(InventoryDialogue.DYE_EQUIPPED_CLOTHING)
					|| currentNode.equals(InventoryDialogue.DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION)) {
				InventoryController.initClothingDyeListeners();
				InventoryController.initPatternListeners();
				InventoryController.initPatternRecolourListeners();
				InventoryController.initStickerListeners();
			} else if (currentNode.equals(InventoryDialogue.DYE_WEAPON)
					|| currentNode.equals(InventoryDialogue.DYE_EQUIPPED_WEAPON)) {
				InventoryController.initWeaponDyeListeners();
				InventoryController.initDamageTypeListeners();
			} else {
				InventoryController.initInventoryListeners();
			}
		} else if (currentNode.equals(Library.DOMINION_MAP)) {
			MiscController.initMapListeners(WorldType.DOMINION, false);
		} else if (currentNode.equals(LilayaMilkingRoomDialogue.MILKING_ROOM)) {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());
			for (FluidStored fluid : room.getFluidsStored()) {
				OccupantController.fluidHandler(room, fluid);
			}
		} else if (currentNode.equals(OccupantManagementDialogue.ROOM_MANAGEMENT)) {
			OccupantController.initRoomManagerListeners();
		} else if (currentNode.equals(OccupantManagementDialogue.ROOM_UPGRADES)) {
			OccupantController.initRoomUpgradesListeners();
		} else if (currentNode.equals(OccupantManagementDialogue.SLAVE_LIST)
				|| currentNode.equals(OccupantManagementDialogue.SLAVE_LIST_MANAGEMENT)) {
			OccupantController.initOccupantListListeners();
			if (Main.game.getDialogueFlags().getSlaveTrader() != null) {
				OccupantController.initSlaveTraderListeners();
			}
		} else if (currentNode.equals(OccupantManagementDialogue.OCCUPANT_OVERVIEW)) {
			OccupantController.initOverviewListeners();
		} else if (currentNode.equals(OptionsDialogue.AGE_PREFERENCE)) {
			OptionsController.initAgeListeners();
		} else if (currentNode.equals(OptionsDialogue.BODIES)) {
			OptionsController.initBodiesListeners();
		} else if (currentNode.equals(OptionsDialogue.GAMEPLAY)) {
			OptionsController.initGameplayListeners();
		} else if (currentNode.equals(OptionsDialogue.GENDER_PREFERENCE)) {
			OptionsController.initGenderListeners();
		} else if (currentNode.equals(OptionsDialogue.FETISH_PREFERENCE)) {
			OptionsController.initFetishListeners();
		} else if (currentNode.equals(OptionsDialogue.FURRY_PREFERENCE)) {
			OptionsController.initFurryListeners();
		} else if (currentNode.equals(OptionsDialogue.IMPORT_EXPORT)) {
			FileController.initImportExportListeners();
		} else if (currentNode.equals(OptionsDialogue.KEYBINDS)) {
			OptionsController.initKeybindListeners();
		} else if (currentNode.equals(OptionsDialogue.MISCELLANEOUS)) {
			OptionsController.initMiscellaneousListeners();
		} else if (currentNode.equals(OptionsDialogue.ORIENTATION_PREFERENCE)) {
			OptionsController.initOrientationListeners();
		} else if (currentNode.equals(OptionsDialogue.SAVE_LOAD)) {
			FileController.initSaveLoadListeners();
		} else if (currentNode.equals(OptionsDialogue.SEX)) {
			OptionsController.initSexListeners();
		} else if (currentNode.equals(OptionsDialogue.UNIT_PREFERENCE)) {
			OptionsController.initUnitListeners();
		} else if (currentNode.equals(PhoneDialogue.CLOTHING_CATALOGUE)) {
			MiscController.initEncyclopediaClothingListeners();
		} else if (currentNode.equals(PhoneDialogue.ITEM_CATALOGUE)) {
			MiscController.initEncyclopediaItemListeners();
		} else if (currentNode.equals(PhoneDialogue.MAP)) {
			MiscController.initMapListeners(PhoneDialogue.worldTypeMap, true);
		} else if (currentNode.equals(PhoneDialogue.WEAPON_CATALOGUE)) {
			MiscController.initEncyclopediaWeaponListeners();
		} else if (currentNode.equals(RoomPlayer.ROOM_SET_ALARM)) {
			MiscController.initAlarmListeners();
		} else if (currentNode.equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY)) {
			CreationController.initAffectionListeners();
			CreationController.initAgeListeners();
			CreationController.initFetishListeners();
			CreationController.initObedienceListeners();
			CreationController.initOrientationListeners();
			CreationController.initPersonalityListeners();
			MiscController.initRenameListeners();
		} else if (currentNode.equals(SlaverAlleyDialogue.AUCTION_BLOCK_LIST)) {
			FileController.initAuctionListeners();
		} else if (currentNode.equals(SlaverAlleyDialogue.AUCTION_IMPORT)) {
			FileController.initSlaveImportListeners();
		} else if (currentNode.equals(SpellManagement.CHARACTER_SPELLS_ARCANE)) {
			MiscController.initSpellListeners(SpellSchool.ARCANE);
		} else if (currentNode.equals(SpellManagement.CHARACTER_SPELLS_AIR)) {
			MiscController.initSpellListeners(SpellSchool.AIR);
		} else if (currentNode.equals(SpellManagement.CHARACTER_SPELLS_EARTH)) {
			MiscController.initSpellListeners(SpellSchool.EARTH);
		} else if (currentNode.equals(SpellManagement.CHARACTER_SPELLS_FIRE)) {
			MiscController.initSpellListeners(SpellSchool.FIRE);
		} else if (currentNode.equals(SpellManagement.CHARACTER_SPELLS_MISC)) {
			MiscController.initSpellListeners(null);
		} else if (currentNode.equals(SpellManagement.CHARACTER_SPELLS_WATER)) {
			MiscController.initSpellListeners(SpellSchool.WATER);
		} else if (currentNode.equals(SuccubisSecrets.SHOP_BEAUTY_SALON_OTHER)) {
			CoveringController.initAssHairListeners();
			CoveringController.initBleachingListeners();
			CoveringController.initFacialHairListeners();
			CoveringController.initPubicHairListeners();
			CoveringController.initUnderarmHairListeners();
		} else if (currentNode.equals(SuccubisSecrets.SHOP_BEAUTY_SALON_HAIR)) {
			CoveringController.initHairLengthListeners();
			CoveringController.initHairStyleListeners();
		}
		setResponseEventListeners();
	}
	
	public static void setResponseEventListeners() {
		
		if(Main.game.getCurrentDialogueNode().getResponseTabTitle(0) != null && !Main.game.getCurrentDialogueNode().getResponseTabTitle(0).isEmpty()) {
			int responsePageCounter = 0;
			while (Main.game.getCurrentDialogueNode().getResponseTabTitle(responsePageCounter) != null){
				setResponseTabListeners(responsePageCounter);
				responsePageCounter++;
			}
		}
		
		// Responses:
		for (int i = 0; i < RESPONSE_COUNT; i++) {
			String id = "option_" + i;
			if (((EventTarget) document.getElementById(id)) != null) {
				SetContentEventListener el = new SetContentEventListener().setIndex(i);
				((EventTarget) document.getElementById(id)).addEventListener("click", el, false);
				
				addEventListener(document, id, "mousemove", responseTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipResponseDescriptionEventListener el2 = new TooltipResponseDescriptionEventListener().setIndex(i);
				addEventListener(document, id, "mouseenter", el2, false);
			}
		}
		if (((EventTarget) document.getElementById("switch_right")) != null) {
			addEventListener(document, "switch_right", "click", nextResponsePageListener, false);
		}
		if (((EventTarget) document.getElementById("switch_left")) != null) {
			addEventListener(document, "switch_left", "click", previousResponsePageListener, false);
		}
	}
	
	private static void setResponseTabListeners(int responsePageCounter) {
		String id = "tab_" + responsePageCounter;

		((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				Main.game.setResponseTab(responsePageCounter);
				Main.game.updateResponses();
			}, false);
	}
	
	static void setInventoryPageLeft(int i) {
		String id = "INV_PAGE_LEFT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			if(i!=5 || Main.game.getPlayer().isCarryingQuestItems()) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					RenderingEngine.setPageLeft(i);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if(i==5) {
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation("Unique Items", "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
		}
	}
	
	static void setInventoryPageRight(int i) {
		String id = "INV_PAGE_RIGHT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				RenderingEngine.setPageRight(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	private void manageButtonLeftListeners() {
		documentButtonsLeft = (Document) webEngineButtonsLeft.executeScript("document");
		EventListenerDataMap.put(documentButtonsLeft, new ArrayList<>());
		
		KeyCodeWithModifiers hotKey;
		if(((EventTarget) documentButtonsLeft.getElementById("mainMenu"))!=null) {
			addEventListener(documentButtonsLeft, "mainMenu", "click", menuButtonListener, true);
			MainController.addEventListener(documentButtonsLeft, "mainMenu", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(documentButtonsLeft, "mainMenu", "mouseleave", MainController.hideTooltipListener, false);
			hotKey = Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MENU);
			MainController.addEventListener(documentButtonsLeft, "mainMenu", "mouseenter", new TooltipInformationEventListener().setInformation("Main Menu"+(hotKey==null?"":" ("+hotKey.getFullName()+")"), ""), false);
		}
		if(((EventTarget) documentButtonsLeft.getElementById("inventory"))!=null) {
			addEventListener(documentButtonsLeft, "inventory", "click", inventoryButtonListener, true);
			MainController.addEventListener(documentButtonsLeft, "inventory", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(documentButtonsLeft, "inventory", "mouseleave", MainController.hideTooltipListener, false);
			hotKey = Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.INVENTORY);
			MainController.addEventListener(documentButtonsLeft, "inventory", "mouseenter", new TooltipInformationEventListener().setInformation("Inventory"+(hotKey==null?"":" ("+hotKey.getFullName()+")"), ""), false);
		}
		if(((EventTarget) documentButtonsLeft.getElementById("journal"))!=null) {
			addEventListener(documentButtonsLeft, "journal", "click", journalButtonListener, true);
			MainController.addEventListener(documentButtonsLeft, "journal", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(documentButtonsLeft, "journal", "mouseleave", MainController.hideTooltipListener, false);
			hotKey = Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.JOURNAL);
			MainController.addEventListener(documentButtonsLeft, "journal", "mouseenter", new TooltipInformationEventListener().setInformation("Phone"+(hotKey==null?"":" ("+hotKey.getFullName()+")"), ""), false);
		}
		if(((EventTarget) documentButtonsLeft.getElementById("charactersPresent"))!=null) {
			addEventListener(documentButtonsLeft, "charactersPresent", "click", charactersPresentButtonListener, true);
			MainController.addEventListener(documentButtonsLeft, "charactersPresent", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(documentButtonsLeft, "charactersPresent", "mouseleave", MainController.hideTooltipListener, false);
			hotKey = Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.CHARACTERS);
			MainController.addEventListener(documentButtonsLeft, "charactersPresent", "mouseenter", new TooltipInformationEventListener().setInformation("Characters Present"+(hotKey==null?"":" ("+hotKey.getFullName()+")"), ""), false);
		}
		if(((EventTarget) documentButtonsLeft.getElementById("mapZoom"))!=null) {
			addEventListener(documentButtonsLeft, "mapZoom", "click", zoomButtonListener, true);
			MainController.addEventListener(documentButtonsLeft, "mapZoom", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(documentButtonsLeft, "mapZoom", "mouseleave", MainController.hideTooltipListener, false);
			hotKey = Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.ZOOM);
			MainController.addEventListener(documentButtonsLeft, "mapZoom", "mouseenter", new TooltipInformationEventListener().setInformation("Minimap Zoom"+(hotKey==null?"":" ("+hotKey.getFullName()+")"), ""), false);
		}
	}
	
	private void manageButtonRightListeners() {
		documentButtonsRight = (Document) webEngineButtonsRight.executeScript("document");
		EventListenerDataMap.put(documentButtonsRight, new ArrayList<>());
		
		boolean quickSaveAvailable = Main.isQuickSaveAvailable() && Main.game.isInNewWorld();
		boolean quickLoadAvailable = Main.isLoadGameAvailable(Main.getQuickSaveName()) && Main.game.isInNewWorld();
		KeyCodeWithModifiers hotKey;
		
		if(((EventTarget) documentButtonsRight.getElementById("quickSave"))!=null) {
			addEventListener(documentButtonsRight, "quickSave", "click", e -> {
				Main.quickSaveGame();
			}, false);
		
			MainController.addEventListener(documentButtonsRight, "quickSave", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(documentButtonsRight, "quickSave", "mouseleave", MainController.hideTooltipListener, false);
			hotKey = Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.QUICKSAVE);
			MainController.addEventListener(documentButtonsRight, "quickSave", "mouseenter", new TooltipInformationEventListener().setInformation(
					quickSaveAvailable
						?"[style.colourGood(Quick Save"+(hotKey==null?"":" ("+hotKey.getFullName()+")")+")]"
						:"[style.colourBad(Quick Save"+(hotKey==null?"":" ("+hotKey.getFullName()+")")+")]",
					"Either creates or "+(quickLoadAvailable?"[style.italicsBad(overrides)]":"")+" the save file with the name '"+Main.getQuickSaveName()+"'."
						+ (hotKey==null
							?"<br/>There is no keyboard shortcut currently bound to this action."
							:"<br/>You can also quick save by pressing the '"+hotKey.getFullName()+"' key.")
						+ (quickSaveAvailable
							?"<br/>[style.italicsGood(You can save the game in this scene!)]"
							:"<br/>[style.italicsBad("+Main.getQuickSaveUnavailabilityDescription()+")]")),
					false);
		}
		
		if(((EventTarget) documentButtonsRight.getElementById("quickLoad"))!=null) {
			addEventListener(documentButtonsRight, "quickLoad", "click", e -> {
				Main.quickLoadGame();
			}, false);
		
			MainController.addEventListener(documentButtonsRight, "quickLoad", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(documentButtonsRight, "quickLoad", "mouseleave", MainController.hideTooltipListener, false);
			hotKey = Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.QUICKLOAD);
			MainController.addEventListener(documentButtonsRight, "quickLoad", "mouseenter", new TooltipInformationEventListener().setInformation(
					quickLoadAvailable
						?"[style.colourGood(Quick Load"+(hotKey==null?"":" ("+hotKey.getFullName()+")")+")]"
						:"[style.colourBad(Quick Load"+(hotKey==null?"":" ("+hotKey.getFullName()+")")+")]",
					"Loads the game file with the name '"+Main.getQuickSaveName()+"'."
						+(hotKey==null
							?"<br/>There is no keyboard shortcut currently bound to this action."
							:"<br/>You can also quick load by pressing the '"+hotKey.getFullName()+"' key.")
						+(quickLoadAvailable
							?"<br/>[style.italicsGood(Quick save file is detected for this character!)]"
							:"<br/>[style.italicsBad(No quick save file detected for this character!)]")),
					false);
		}
		
		String id = "copyContent";
		if(((EventTarget) documentButtonsRight.getElementById(id))!=null) {
			MainController.addEventListener(documentButtonsRight, id, "click", copyDialogueButtonListener, false);
			MainController.addEventListener(documentButtonsRight, id, "mousemove", moveTooltipListener, false);
			MainController.addEventListener(documentButtonsRight, id, "mouseleave", hideTooltipListener, false);
			

			MainController.addEventListener(documentButtonsRight, id, "mouseenter", new TooltipInformationEventListener().setInformation(
					Main.game.getCurrentDialogueNode().getLabel() == "" || Main.game.getCurrentDialogueNode().getLabel() == null ? "-" : Main.game.getCurrentDialogueNode().getLabel(),
					"Click to copy the currently displayed dialogue to your clipboard. You can then paste the output into a text editor, save it as a <i>.html</i> file, then open that file in a browser to retain the game's formatting.<br/>"
					+ "This scene was written by: <b>"+Main.game.getCurrentDialogueNode().getAuthor()+"</b>",
					80), false);
			
		}
		
		id = "exportCharacter";
		if(((EventTarget) documentButtonsRight.getElementById(id))!=null) {
			boolean exportAvailable = Main.game.isStarted()
					&& (Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
						|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)
						|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER));
			

			MainController.addEventListener(documentButtonsRight, id, "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(documentButtonsRight, id, "mouseleave", MainController.hideTooltipListener, false);
			
			if(exportAvailable) {
				GameCharacter exportCharacter = Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)?Main.game.getPlayer():CharactersPresentDialogue.characterViewed;
				String name = "<span style='color:"+exportCharacter.getFemininity().getColour().toWebHexString()+";'>"+exportCharacter.getName(false)+"</span>";
				MainController.addEventListener(documentButtonsRight, id, "click", e -> {
					Game.exportCharacter(exportCharacter);
					Main.game.flashMessage(PresetColour.GENERIC_EXCELLENT, "Character Exported!");
				}, false);
				MainController.addEventListener(documentButtonsRight, id, "mouseenter", new TooltipInformationEventListener().setInformation(
						"Export Character",
						"Export "+name+" to the 'data/characters' folder. Exported characters can be imported at the auction block in Slaver Alley.",
						48), false);
				
			} else {
				MainController.addEventListener(documentButtonsRight, id, "mouseenter", new TooltipInformationEventListener().setInformation(
						"Export Character",
						"To export any character, including your own, you need to be viewing them in your phone's 'Characters Present' screen.",
						48), false);
			}
		}
	}
	
	private void manageAttributeListeners() {
		documentAttributes = (Document) webEngineAttributes.executeScript("document");
		EventListenerDataMap.put(documentAttributes, new ArrayList<>());
		
		// Map:
		if (((EventTarget) documentAttributes.getElementById("upButton")) != null) {
			addEventListener(documentAttributes, "upButton", "click", moveNorthListener, true);
		}
		if (((EventTarget) documentAttributes.getElementById("downButton")) != null) {
			addEventListener(documentAttributes, "downButton", "click", moveSouthListener, true);
		}
		if (((EventTarget) documentAttributes.getElementById("leftButton")) != null) {
			addEventListener(documentAttributes, "leftButton", "click", moveWestListener, true);
		}
		if (((EventTarget) documentAttributes.getElementById("rightButton")) != null) {
			addEventListener(documentAttributes, "rightButton", "click", moveEastListener, true);
		}
		
		// Inventory:
		// For all equipped clothing slots:
		String id;
		for (InventorySlot invSlot : InventorySlot.values()) {
			id = invSlot.toString() + "Slot";
			if (!invSlot.isWeapon()) {
				if (((EventTarget) documentAttributes.getElementById(id)) != null) {
					if(!RenderingEngine.ENGINE.isRenderingTattoosLeft() || invSlot.isJewellery()) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(Main.game.getPlayer(), invSlot);
						addEventListener(documentAttributes, id, "click", el, false);
					}
					addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setInventorySlot(invSlot, Main.game.getPlayer());
					addEventListener(documentAttributes, id, "mouseenter", el2, false);
				}
			} else {
				if (((EventTarget) documentAttributes.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(Main.game.getPlayer(),invSlot);
					addEventListener(documentAttributes, id, "click", el, false);
					addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setInventorySlot(invSlot, Main.game.getPlayer());
					addEventListener(documentAttributes, id, "mouseenter", el2, false);
				}
			}
		}
		
		id = "TATTOO_SWITCH_LEFT";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			((EventTarget) documentAttributes.getElementById(id)).addEventListener("click", e -> {
				RenderingEngine.ENGINE.setRenderingTattoosLeft(!RenderingEngine.ENGINE.isRenderingTattoosLeft());
				this.updateUILeftPanel();
			}, false);
			addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation(
					!RenderingEngine.ENGINE.isRenderingTattoosLeft()
						?"Switch to tattoos"
						:"Switch to clothing",
					"");
			addEventListener(documentAttributes, id, "mouseenter", el2, false);
		}
		
		id = "INVENTORY_ENCHANTMENT_LIMIT";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation(
					Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName()),
					"The total amount of weapon, clothing, and tattoo attribute enchantments you're able to handle without incurring massive penalties."
					+" Your limit is calculated from: <i>10 + (level) + (perk gains)</i>");
			addEventListener(documentAttributes, id, "mouseenter", el2, false);
		}
		
		id = "INVENTORY_ENCHANTMENT_LIMIT_NPC";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation(
					Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName()),
					UtilText.parse(RenderingEngine.getCharacterToRender(),
							"The total amount of weapon, clothing, and tattoo attribute enchantments you're able to handle without incurring massive penalties."
								+" [npc.Her] maximum is calculated from: <i>10 + (level) + (perk gains)</i>"));
			addEventListener(documentAttributes, id, "mouseenter", el2, false);
		}

		boolean dateKnown = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.knowsDate) || !Main.game.isInNewWorld();
		
		id = "DATE_DISPLAY_TOGGLE";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			((EventTarget) documentAttributes.getElementById(id)).addEventListener("click", e -> {
				if(!Main.game.isBadEnd()) {
					Main.getProperties().setValue(PropertyValue.calendarDisplay, !Main.getProperties().hasValue(PropertyValue.calendarDisplay));
					Main.saveProperties();
					MainController.updateUI();
				}
			}, false);
			
			addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
			String day = Main.game.getDateNow().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			StringBuilder dateSB = new StringBuilder();
			if(Main.game.isBadEnd()) {
				dateSB.append("[style.italicsBad(Given your current situation, you've lost track of what day it is...)]");
			} else {
				if(!dateKnown) {
					dateSB.append("[style.italicsMinorBad(Look at the calendar in your room to reveal the date!)]");
				} else {
					dateSB.append("The current date is: [style.colourGood("+ Main.game.getDisplayDate(true)+")]");
				}
				dateSB.append("<br/>");
				dateSB.append("It is currently "+UtilText.generateSingularDeterminer(day)+" [style.colourBlueLight("+day+")].<br/>"
						+ "You've been in this new world for: [style.colourExcellent("+Main.game.getDayNumber()+" day"+(Main.game.getDayNumber()>1?"s":"")+")]");
				dateSB.append("<br/><i>Click to toggle between a date and day count.</i>");
			}
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Toggle Calendar Display",
						dateSB.toString());
			addEventListener(documentAttributes, id, "mouseenter", el2, false);
		}
		
		id = "TWENTY_FOUR_HOUR_TIME_TOGGLE";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			((EventTarget) documentAttributes.getElementById(id)).addEventListener("click", e -> {
				if(!Main.game.isBadEnd()) {
				    overrideAutoLocale();
					Main.getProperties().setValue(PropertyValue.twentyFourHourTime, !Main.getProperties().hasValue(PropertyValue.twentyFourHourTime));
					Main.saveProperties();
					Units.FORMATTER.updateTimeFormat(Main.getProperties().hasValue(PropertyValue.autoLocale));
					MainController.updateUI();
				}
			}, false);
			
			DayPeriod dp = DateAndTime.getDayPeriod(Main.game.getDateNow(), Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
			LocalDateTime[] ldt = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
			
			
			addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
			StringBuilder timeSB = new StringBuilder();
			if(Main.game.isBadEnd()) {
				timeSB.append("[style.italicsBad(Given your current situation, you've lost track of what time it is...)]");
			} else {
				timeSB.append("Current time: "+Units.time(Main.game.getDateNow())+" (<span style='color:"+dp.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(dp.getName())+"</span>)<br/>"
					+ "Sunrise: "+(!dateKnown?"[style.colourMinorBad(??/??/??)] [style.colourYellow("+Units.time(ldt[0]):"[style.colourYellow("+Units.dateTime(ldt[0]))+")]<br/>"
					+ "Sunset: "+(!dateKnown?"[style.colourMinorBad(??/??/??)] [style.colourOrange("+Units.time(ldt[1]):"[style.colourOrange("+Units.dateTime(ldt[1]))+")]<br/>"
					+ "<i>Click to toggle the time display between a 24-hour and 12-hour clock.</i>");
			}
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation(
					(Main.game.isDayTime()?"Day-time":"Night-time"),
					timeSB.toString());
			addEventListener(documentAttributes, id, "mouseenter", el2, false);
		}

		id = "ESSENCE_ICON";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
			
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("[style.boldArcane(Arcane Essences)]", "");
			addEventListener(documentAttributes, id, "mouseenter", el2, false);
		}
		
		AbstractAttribute[] attributes = {
				Attribute.HEALTH_MAXIMUM,
				Attribute.MANA_MAXIMUM,
				Attribute.EXPERIENCE,
				Attribute.MAJOR_PHYSIQUE,
				Attribute.MAJOR_ARCANE,
				Attribute.MAJOR_CORRUPTION,
				Attribute.AROUSAL,
				Attribute.LUST };
		
		List<GameCharacter> charactersBeingRendered = new ArrayList<>();
		if(Main.game.isInSex()) {
			charactersBeingRendered.addAll(Main.sex.getDominantParticipants(true).keySet());
			charactersBeingRendered.addAll(Main.sex.getSubmissiveParticipants(true).keySet());
		} else if(Main.game.isInCombat()) {
			charactersBeingRendered.add(Main.game.getPlayer());
			charactersBeingRendered.addAll(Main.combat.getAllies(Main.game.getPlayer()));
		} else {
			if(Main.game.getPlayer()!=null) {
				charactersBeingRendered.add(Main.game.getPlayer());
				charactersBeingRendered.addAll(Main.game.getPlayer().getCompanions());
			}
		}
		
		for(GameCharacter character : charactersBeingRendered) {
			String idModifier = (character.isPlayer()?"PLAYER_":"NPC_"+character.getId()+"_");
			
			for (AbstractAttribute a : attributes) {
				if (((EventTarget) documentAttributes.getElementById(idModifier+a.getName())) != null) {
					if(a == Attribute.EXPERIENCE) {
						((EventTarget) documentAttributes.getElementById(idModifier+a.getName())).addEventListener("click", e -> {
							if(character.isPlayer()) {
								// block when in character creation
								if(Main.game.isInNewWorld()) {
									if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE) {
										if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_APPEARANCE) {
											openPhone();
										} else {
											Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_APPEARANCE));
										}
										
									} else {
										if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
//												|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OCCUPANT_MANAGEMENT
												) {
											Main.game.saveDialogueNode();
										}
										
										Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_APPEARANCE));
									}
								}
								
							} else {
								openCharactersPresent(character);
							}
						}, false);
					}
					addEventListener(documentAttributes, idModifier+a.getName(), "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, idModifier+a.getName(), "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(a, character);
					addEventListener(documentAttributes, idModifier+a.getName(), "mouseenter", el, false);
				}
			}
			
			if(((EventTarget) documentAttributes.getElementById(idModifier+"ATTRIBUTES"))!=null){
				((EventTarget) documentAttributes.getElementById(idModifier+"ATTRIBUTES")).addEventListener("click", e -> {
					if(character.isPlayer()) {
						// block when in character creation
						if(Main.game.isInNewWorld()) {
							if(Main.game.isInCombat()) {
								Main.combat.setTargetedCombatant(character);
								updateUI();
								Main.game.updateResponses();
								
							} else if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE) {
								if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_PERK_TREE) {
									openPhone();
								} else {
									Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_PERK_TREE));
								}
								
							} else {
								if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
//										|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OCCUPANT_MANAGEMENT
										) {
									Main.game.saveDialogueNode();
								}
								
								Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_PERK_TREE));
							}
						}
						
					} else { //TODO display NPC perk tree
						if(Main.game.isInSex()) {
							Main.sex.setTargetedPartner(Main.game.getPlayer(), character);
							Main.sex.recalculateSexActions();
							updateUI();
							Main.game.updateResponses();
							
						} else if(Main.game.isInCombat()) {
							Main.combat.setTargetedCombatant((NPC) character);
							updateUI();
							Main.game.updateResponses();
							
						} else {
							openCharactersPresent(character);
						}
					}
				}, false);
				addEventListener(documentAttributes, idModifier+"ATTRIBUTES", "mousemove", moveTooltipListener, false);
				addEventListener(documentAttributes, idModifier+"ATTRIBUTES", "mouseleave", hideTooltipListener, false);
	
				TooltipInformationEventListener el = new TooltipInformationEventListener().setExtraAttributes(character);
				addEventListener(documentAttributes, idModifier+"ATTRIBUTES", "mouseenter", el, false);
			}
			
			// Elemental:
			id = idModifier+"ELEMENTAL_"+Attribute.EXPERIENCE.getName();
			if (((EventTarget) documentAttributes.getElementById(id)) != null) {
				((EventTarget) documentAttributes.getElementById(id)).addEventListener("click", e -> {
					if(character.isPlayer() && Main.game.isInNeutralDialogue()) {
						Main.game.setContent(new Response("", "", ElementalDialogue.ELEMENTAL_START));
					}
				}, false);
				addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
				addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(Attribute.EXPERIENCE, character.getElemental());
				addEventListener(documentAttributes, id, "mouseenter", el, false);
			}
			
			id = idModifier+"ELEMENTAL_ATTRIBUTES";
			if(((EventTarget) documentAttributes.getElementById(id))!=null){
				((EventTarget) documentAttributes.getElementById(id)).addEventListener("click", e -> {
					if(character.isPlayer() && Main.game.isInNeutralDialogue()) {
						Main.game.setContent(new Response("", "", ElementalDialogue.ELEMENTAL_START));
					}
				}, false);
				addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
				addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setExtraAttributes(character.getElemental());
				addEventListener(documentAttributes, id, "mouseenter", el, false);
			}
			
			
			// For status effect slots:
			for (AbstractStatusEffect se : character.getStatusEffects()) {
				id = "SE_"+idModifier + se;
				if (((EventTarget) documentAttributes.getElementById(id)) != null) {
					addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
					
					// Set target to whoever is interacting with this area:
					if(Main.game.isInSex()) { //TODO add click helper text //TODO add border or something to show that it's clickable
						SexAreaInterface si = null;
						if(se==StatusEffect.PENIS_STATUS) {
							si = SexAreaPenetration.PENIS;
						} else if(se==StatusEffect.ANUS_STATUS) {
							si = SexAreaOrifice.ANUS;
						} else if(se==StatusEffect.ASS_STATUS) {
							si = SexAreaOrifice.ASS;
						} else if(se==StatusEffect.MOUTH_STATUS) {
							si = SexAreaOrifice.MOUTH;
						} else if(se==StatusEffect.BREAST_STATUS) {
							si = SexAreaOrifice.BREAST;
						} else if(se==StatusEffect.BREAST_CROTCH_STATUS) {
							si = SexAreaOrifice.BREAST_CROTCH;
						} else if(se==StatusEffect.NIPPLE_STATUS) {
							si = SexAreaOrifice.NIPPLE;
						} else if(se==StatusEffect.NIPPLE_CROTCH_STATUS) {
							si = SexAreaOrifice.NIPPLE_CROTCH;
						} else if(se==StatusEffect.THIGH_STATUS) {
							si = SexAreaOrifice.THIGHS;
						} else if(se==StatusEffect.URETHRA_PENIS_STATUS) {
							si = SexAreaOrifice.URETHRA_PENIS;
						} else if(se==StatusEffect.URETHRA_VAGINA_STATUS) {
							si = SexAreaOrifice.URETHRA_VAGINA;
						} else if(se==StatusEffect.VAGINA_STATUS) {
							si = SexAreaOrifice.VAGINA;
						}
						if(si!=null) {
							setStatusEffectSexTargetChangeListener(documentAttributes, id, character, si);
						}
					}
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, character);
					addEventListener(documentAttributes, id, "mouseenter", el, false);
				}
			}
			if(character.isElementalSummoned() && !character.isElementalActive()) {
				for (AbstractStatusEffect se : character.getElemental().getStatusEffects()) {
					id = "SE_ELEMENTAL_"+idModifier + se;
					if (((EventTarget) documentAttributes.getElementById(id)) != null) {
						addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
						
						TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, character.getElemental());
						addEventListener(documentAttributes, id, "mouseenter", el, false);
					}
				}
			}
			
			for (AbstractPerk trait : character.getTraits()) {
				id = "TRAIT_" + idModifier + Perk.getIdFromPerk(trait);
				if (((EventTarget) documentAttributes.getElementById(id)) != null) {
					addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
					TooltipInformationEventListener el = new TooltipInformationEventListener().setPerk(trait, character);
					addEventListener(documentAttributes, id, "mouseenter", el, false);
				}
			}
			for (AbstractFetish f : character.getFetishes(true)) {
				if (((EventTarget) documentAttributes.getElementById("FETISH_"+idModifier + Fetish.getIdFromFetish(f))) != null) {
					addEventListener(documentAttributes, "FETISH_"+idModifier + Fetish.getIdFromFetish(f), "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "FETISH_"+idModifier + Fetish.getIdFromFetish(f), "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setFetish(f, character);
					addEventListener(documentAttributes, "FETISH_"+idModifier + Fetish.getIdFromFetish(f), "mouseenter", el, false);
				}
			}
			for (AbstractCombatMove combatMove : character.getAvailableMoves()) {
				id = "CM_"+idModifier + combatMove.getIdentifier();
				if (((EventTarget) documentAttributes.getElementById(id)) != null) {
					addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
	
					TooltipInformationEventListener el = new TooltipInformationEventListener().setCombatMove(combatMove, character);
					addEventListener(documentAttributes, id, "mouseenter", el, false);
				}
			}
			for (Spell s : character.getAllSpells()) {
				if (((EventTarget) documentAttributes.getElementById("SPELL_"+idModifier + s)) != null) {
					addEventListener(documentAttributes, "SPELL_"+idModifier + s, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "SPELL_"+idModifier + s, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setSpell(s, character);
					addEventListener(documentAttributes, "SPELL_"+idModifier + s, "mouseenter", el, false);
				}
			}
		}
		
	}

	public static void overrideAutoLocale() {
		if (Main.getProperties().hasValue(PropertyValue.autoLocale)) {
			Main.getProperties().setValue(PropertyValue.autoLocale, false);
			Units.FORMATTER.updateNumberFormat(false);
		}
	}

	private static void setStatusEffectSexTargetChangeListener(Document document, String id, GameCharacter character, SexAreaInterface si) {
		((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
			GameCharacter target = Main.sex.getCharactersHavingOngoingActionWith(character, si).isEmpty()
					?null
					:Main.sex.getCharactersHavingOngoingActionWith(character, si).get(0);
			if(target!=null && target instanceof NPC) {
				Main.sex.setTargetedPartner(Main.game.getPlayer(), target);
				Main.sex.recalculateSexActions();
				updateUI();
				Main.game.updateResponses();
			}
		}, false);
	}
	
	private void manageRightListeners() {
		documentRight = (Document) webEngineRight.executeScript("document");
		EventListenerDataMap.put(documentRight, new ArrayList<>());

		Map<InventorySlot, List<AbstractClothing>> concealedSlots = new HashMap<>();
		
		if(RenderingEngine.getCharacterToRender()!=null) {
			concealedSlots = RenderingEngine.getCharacterToRender().getInventorySlotsConcealed(Main.game.getPlayer());
		}
		
		// Inventory:
		String id;
		for (InventorySlot invSlot : InventorySlot.values()) {
			id = invSlot.toString() + "Slot";
			if (!invSlot.isWeapon()) {
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					if(concealedSlots.keySet().contains(invSlot)) {
						addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el2 = new TooltipInformationEventListener().setConcealedSlot(invSlot);
						addEventListener(documentRight, id, "mouseenter", el2, false);
						
					} else {
						if(!RenderingEngine.ENGINE.isRenderingTattoosRight() || invSlot.isJewellery()) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(RenderingEngine.getCharacterToRender(), invSlot);
							addEventListener(documentRight, id, "click", el, false);
						}
						
						addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setInventorySlot(invSlot, RenderingEngine.getCharacterToRender());
						addEventListener(documentRight, id, "mouseenter", el2, false);
					}
				}
			} else {
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(RenderingEngine.getCharacterToRender(), invSlot);
					addEventListener(documentRight, id, "click", el, false);
					
					addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setInventorySlot(invSlot, RenderingEngine.getCharacterToRender());
					addEventListener(documentRight, id, "mouseenter", el2, false);
				}
			}
		}
		
		id = "TATTOO_SWITCH_RIGHT";
		if (((EventTarget) documentRight.getElementById(id)) != null) {
			((EventTarget) documentRight.getElementById(id)).addEventListener("click", e -> {
				RenderingEngine.ENGINE.setRenderingTattoosRight(!RenderingEngine.ENGINE.isRenderingTattoosRight());
				updateUIRightPanel();
			}, false);
			addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation(
					!RenderingEngine.ENGINE.isRenderingTattoosRight()
						?"Switch to tattoos"
						:"Switch to clothing",
					"");
			addEventListener(documentRight, id, "mouseenter", el2, false);
		}
		
		if(Main.game.getPlayer()!=null) {
			// Money on floor:
			id = "MONEY_ON_FLOOR";
			if (((EventTarget) documentRight.getElementById(id)) != null) {
				if(!Main.game.getCurrentDialogueNode().isInventoryDisabled() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY) {
					addEventListener(documentRight, id, "click", e -> {
						Main.mainController.openInventory();
					}, false);
				}
			}

			// Weapons on floor:
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
				id = "WEAPON_FLOOR_" + entry.getKey().hashCode();
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), null);
						addEventListener(documentRight, id, "click", el, false);
					}
					addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setWeapon(entry.getKey(), null, false);
					addEventListener(documentRight, id, "mouseenter", el2, false);
				}
			}
			
			// Clothing on floor:
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
				id = "CLOTHING_FLOOR_" + entry.getKey().hashCode();
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), null);
						addEventListener(documentRight, id, "click", el, false);
					}
					addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setClothing(entry.getKey(), null, null);
					addEventListener(documentRight, id, "mouseenter", el2, false);
				}
			}
			
			// Items on floor:
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getAllItemsInInventory().entrySet()) {
				id = "ITEM_FLOOR_" + entry.getKey().hashCode();
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), null);
						addEventListener(documentRight, id, "click", el, false);
					}
					addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setItem(entry.getKey(), null, null);
					addEventListener(documentRight, id, "mouseenter", el2, false);
				}
			}
		}
		
		AbstractAttribute[] attributes = {
				Attribute.HEALTH_MAXIMUM,
				Attribute.MANA_MAXIMUM,
				Attribute.EXPERIENCE,
				Attribute.MAJOR_PHYSIQUE,
				Attribute.MAJOR_ARCANE,
				Attribute.MAJOR_CORRUPTION,
				Attribute.AROUSAL,
				Attribute.LUST };
		if(!RenderingEngine.ENGINE.isRenderingCharactersRightPanel()) {
			attributes = new AbstractAttribute[] {Attribute.EXPERIENCE};
		}
		
		List<GameCharacter> charactersBeingRendered = new ArrayList<>();
		if(Main.game.isInSex()) {
			charactersBeingRendered.addAll(Main.sex.getDominantParticipants(true).keySet());
			charactersBeingRendered.addAll(Main.sex.getSubmissiveParticipants(true).keySet());
			
		} else if(Main.game.isInCombat()) {
			charactersBeingRendered.addAll(Main.combat.getEnemies(Main.game.getPlayer()));
			
		} else if(RenderingEngine.ENGINE.isRenderingCharactersRightPanel()) {
			charactersBeingRendered.add(RenderingEngine.getCharacterToRender());
			
		} else {
			charactersBeingRendered.addAll(Main.game.getCharactersPresent());
			
			if(Main.game.isStarted()) {
				int i=0;
				for(Population pop : Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation()) {
					id = "PLACE_POPULATION_"+i;
					if (((EventTarget) documentRight.getElementById(id)) != null) {
						addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
						
						Set<AbstractSubspecies> subspecies = new HashSet<>();
						subspecies.addAll(pop.getSpecies().keySet());
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(
								"Races Present",
								Util.subspeciesToStringList(subspecies, true)+".",
								16 + ((subspecies.size()/3)*16));
						addEventListener(documentRight, id, "mouseenter", el, false);
					}
					i++;
				}
			}
		}
		
		charactersBeingRendered.remove(Main.game.getPlayer());
		
		for(GameCharacter character : charactersBeingRendered) {
			String idModifier = character.getId()+"_";
			
			for (AbstractAttribute a : attributes) {
				if (((EventTarget) documentRight.getElementById("NPC_"+idModifier+a.getName())) != null) {
					if(a == Attribute.EXPERIENCE) {
						((EventTarget) documentRight.getElementById("NPC_"+idModifier+a.getName())).addEventListener("click", e -> {
							openCharactersPresent(character);
						}, false);
					}
					addEventListener(documentRight, "NPC_"+idModifier+a.getName(), "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, "NPC_"+idModifier+a.getName(), "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(a, character);
					addEventListener(documentRight, "NPC_"+idModifier+a.getName(), "mouseenter", el, false);
				}
			}
			
			// Extra attribute info:
			if(((EventTarget) documentRight.getElementById("NPC_"+idModifier+"ATTRIBUTES"))!=null){
				if(!RenderingEngine.ENGINE.isRenderingCharactersRightPanel()) {
					((EventTarget) documentRight.getElementById("NPC_"+idModifier+"ATTRIBUTES")).addEventListener("click", e -> {
						openCharactersPresent(character);
					}, false);
					
				} else if(Main.game.isInSex()) {
					((EventTarget) documentRight.getElementById("NPC_"+idModifier+"ATTRIBUTES")).addEventListener("click", e -> {
						Main.sex.setTargetedPartner(Main.game.getPlayer(), character);
						Main.sex.recalculateSexActions();
						updateUI();
						Main.game.updateResponses();
					}, false);
					
				} else if(Main.game.isInCombat()) {
					((EventTarget) documentRight.getElementById("NPC_"+idModifier+"ATTRIBUTES")).addEventListener("click", e -> {
						Main.combat.setTargetedCombatant((NPC) character);
						updateUI();
						Main.game.updateResponses();
					}, false);
				}
				addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mousemove", moveTooltipListener, false);
				addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mouseleave", hideTooltipListener, false);
	
				TooltipInformationEventListener el = new TooltipInformationEventListener().setExtraAttributes(character);
				addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mouseenter", el, false);
			}
			
			// Elemental:
			id = "NPC_"+idModifier+"ELEMENTAL_"+Attribute.EXPERIENCE.getName();
			if (((EventTarget) documentRight.getElementById(id)) != null) {
				addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
				addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(Attribute.EXPERIENCE, character.getElemental());
				addEventListener(documentRight, id, "mouseenter", el, false);
			}
			
			id = "NPC_"+idModifier+"ELEMENTAL_ATTRIBUTES";
			if(((EventTarget) documentRight.getElementById(id))!=null){
				addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
				addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setExtraAttributes(character.getElemental());
				addEventListener(documentRight, id, "mouseenter", el, false);
			}
			
			if(RenderingEngine.ENGINE.isRenderingCharactersRightPanel()) {
				// For status effect slots:
				for (AbstractStatusEffect se : character.getStatusEffects()) {
					id = "SE_NPC_"+idModifier + se;
					if (((EventTarget) documentRight.getElementById(id)) != null) {
						addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
		
						// Set target to whoever is interacting with this area:
						if(Main.game.isInSex()) { //TODO add click helper text
							SexAreaInterface si = null;
							if(se==StatusEffect.PENIS_STATUS) {
								si = SexAreaPenetration.PENIS;
							} else if(se==StatusEffect.ANUS_STATUS) {
								si = SexAreaOrifice.ANUS;
							} else if(se==StatusEffect.ASS_STATUS) {
								si = SexAreaOrifice.ASS;
							} else if(se==StatusEffect.MOUTH_STATUS) {
								si = SexAreaOrifice.MOUTH;
							} else if(se==StatusEffect.BREAST_STATUS) {
								si = SexAreaOrifice.BREAST;
							} else if(se==StatusEffect.NIPPLE_STATUS) {
								si = SexAreaOrifice.NIPPLE;
							} else if(se==StatusEffect.THIGH_STATUS) {
								si = SexAreaOrifice.THIGHS;
							} else if(se==StatusEffect.URETHRA_PENIS_STATUS) {
								si = SexAreaOrifice.URETHRA_PENIS;
							} else if(se==StatusEffect.URETHRA_VAGINA_STATUS) {
								si = SexAreaOrifice.URETHRA_VAGINA;
							} else if(se==StatusEffect.VAGINA_STATUS) {
								si = SexAreaOrifice.VAGINA;
							}
							if(si!=null) {
								setStatusEffectSexTargetChangeListener(documentRight, id, character, si);
							}
						}
						
						TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, character);
						addEventListener(documentRight, id, "mouseenter", el, false);
					}
				}
				if(character.isElementalSummoned() && !character.isElementalActive()) {
					for (AbstractStatusEffect se : character.getElemental().getStatusEffects()) {
						id = "SE_ELEMENTAL_NPC_"+idModifier + se;
						if (((EventTarget) documentRight.getElementById(id)) != null) {
							addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
							addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
							
							TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, character.getElemental());
							addEventListener(documentRight, id, "mouseenter", el, false);
						}
					}
				}
				
				// For perk slots:
				for (AbstractPerk p : character.getMajorPerks()) {
					id = "PERK_NPC_"+idModifier + Perk.getIdFromPerk(p);
					if (((EventTarget) documentRight.getElementById(id)) != null) {
						addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
		
						TooltipInformationEventListener el = new TooltipInformationEventListener().setPerk(p, character);
						addEventListener(documentRight, id, "mouseenter", el, false);
					}
				}
				for (AbstractFetish f : character.getFetishes(true)) {
					if (((EventTarget) documentRight.getElementById("FETISH_NPC_"+idModifier + Fetish.getIdFromFetish(f))) != null) {
						addEventListener(documentRight, "FETISH_NPC_"+idModifier + Fetish.getIdFromFetish(f), "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, "FETISH_NPC_"+idModifier + Fetish.getIdFromFetish(f), "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el = new TooltipInformationEventListener().setFetish(f, character);
						addEventListener(documentRight, "FETISH_NPC_"+idModifier + Fetish.getIdFromFetish(f), "mouseenter", el, false);
					}
				}
				for (AbstractCombatMove combatMove : character.getAvailableMoves()) {
					id = "CM_NPC_"+idModifier + combatMove.getIdentifier();
					if (((EventTarget) documentRight.getElementById(id)) != null) {
						addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
		
						TooltipInformationEventListener el = new TooltipInformationEventListener().setCombatMove(combatMove, character);
						addEventListener(documentRight, id, "mouseenter", el, false);
					}
				}
				for (Spell s : character.getAllSpells()) {
					if (((EventTarget) documentRight.getElementById("SPELL_"+idModifier + s)) != null) {
						addEventListener(documentRight, "SPELL_"+idModifier + s, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, "SPELL_"+idModifier + s, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el = new TooltipInformationEventListener().setSpell(s, character);
						addEventListener(documentRight, "SPELL_"+idModifier + s, "mouseenter", el, false);
					}
				}
			}
		}
	}
	
	
	private boolean useJavascriptToSetContent = true;
	
	private void setWebEngineContent(WebEngine engine, String content) {
		content=content.replaceAll("[\r\n]", "");
		content=content.replaceAll("\"", "'");
		
		engine.executeScript(
			"document.open('text/html');"
			+ "document.write(\""+content+"\");"
			+"document.close();");
	}
	
	public void setMainContent(String content) {
		if(useJavascriptToSetContent
				 // For rendering images from file:
				&& !Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
				&& !Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER)
				&& !Main.game.getCurrentDialogueNode().equals(CompanionManagement.SLAVE_MANAGEMENT_INSPECT)) {
			unbindListeners(document);
			setWebEngineContent(webEngine, content);
			manageMainListeners();
		} else {
			webEngine.loadContent(content);
		}
	}

	/**
	 * Sets the tooltip content, and returns the pixel height
	 * of the resultant tooltip content.
	 *
	 * To calculate height, wraps the content in a #sizing-box
	 * div, and checks that element's height.
	 *
	 * This does not set the tooltip height, but only measures
	 * the content height. The tooltip must be manually
	 * resized as needed.
	 *
	 * Additionally, this should be called before setting the
	 * tooltip position, if that requires knowledge of the
	 * final height.
	 *
	 * @param content HTML content to display in the tooltip
	 * @return the calculated height of the tooltip content
	 */
	public int setTooltipContent(String content) {
		if (Main.getProperties().hasValue(PropertyValue.fadeInText)) {
			content = "<div class='tooltip-animation' style='width: 100%;'>" + content + "</div>";
		}
		content = "<div id='sizing-box' style='width: 100%;'>" + content + "</div>";
		if(useJavascriptToSetContent) {
			setWebEngineContent(webEngineTooltip, content);
		} else {
			webEngineTooltip.loadContent(content);
		}
		int height = 0;
		try {
			// add 8 + 8 to account for the top + bottom margins
			height = 16 + (int)Main.mainController.getWebEngineTooltip().executeScript("document.getElementById('sizing-box').scrollHeight");
		} catch(Exception e) {
			System.err.println("Failed to locate the tooltip sizing box!");
			e.printStackTrace();
		}
		return height;
	}
	
	public void setAttributePanelContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentAttributes);
			setWebEngineContent(webEngineAttributes, content);
			manageAttributeListeners();
		} else {
			webEngineAttributes.loadContent(content);
		}
	}
	
	public void setRightPanelContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentRight);
			setWebEngineContent(webEngineRight, content);
			manageRightListeners();
		} else {
			webEngineRight.loadContent(content);
		}
	}
	
	public void setButtonsLeftContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentButtonsLeft);
			setWebEngineContent(webEngineButtonsLeft, content);
			manageButtonLeftListeners();
		} else {
			webEngineButtonsLeft.loadContent(content);
		}
	}
	
	public void setButtonsRightContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentButtonsRight);
			setWebEngineContent(webEngineButtonsRight, content);
			manageButtonRightListeners();
		} else {
			webEngineButtonsRight.loadContent(content);
		}
	}
	
	private boolean keyEventMatchesBindings(KeyboardAction binding, KeyEvent keyEvent) {
		KeyCodeWithModifiers primary = Main.getProperties().hotkeyMapPrimary.get(binding);
		KeyCodeWithModifiers secondary = Main.getProperties().hotkeyMapSecondary.get(binding);
		return (primary != null && primary.matches(keyEvent)) || (secondary != null && secondary.matches(keyEvent));
	}
	
	/**
	 * For cheat codes. debug: toggle debug mode spawn: open enemy spawn menu
	 */
	private void checkLastKeys() {
		if (lastKeysEqual(KeyCode.B, KeyCode.U, KeyCode.G, KeyCode.G, KeyCode.Y)) {
			if(Main.game!=null) {
				if(Main.game.isStarted() && Main.game.isInNewWorld() && Main.game.isPrologueFinished()) {
					Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					Main.game.saveDialogueNode();
					Main.game.setContent(new Response("", "", DebugDialogue.DEBUG_MENU));
				} else {
					Main.game.flashMessage(PresetColour.GENERIC_BAD, "Unavailable in prologue!");
				}
			}
		}
//		if (lastKeysEqual(KeyCode.N, KeyCode.O, KeyCode.X, KeyCode.X, KeyCode.X)) {
//			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_GENERIC_SHOP) && !Main.game.getNpc(TestNPC.class).isSlave()) {
//				Main.game.setActiveNPC(Main.game.getNpc(TestNPC.class));
//				Main.game.setContent(new Response("", "", TestNPC.TEST_DIALOGUE) {
//					@Override
//					public void effects() {
//						Main.game.getNpc(TestNPC.class).setLocation(WorldType.SHOPPING_ARCADE, Main.game.getPlayer().getLocation(), true);
//					}
//				});
//			}
//		}
		if (lastKeysEqual(KeyCode.D, KeyCode.K, KeyCode.O, KeyCode.M, KeyCode.A)) {
			if(Main.game!=null) {
				if(Main.game.isStarted()
						&& Main.game.isInNewWorld()
						&& Main.game.isInCombat()
						&& Main.combat.getAllCombatants(false).size()==1
						&& !Main.combat.getEnemies(Main.game.getPlayer()).get(0).isUnique()
						&& Main.game.getPlayer().hasPenis()
						&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
						&& Main.combat.getEnemies(Main.game.getPlayer()).get(0).hasVagina()
						&& Main.combat.getEnemies(Main.game.getPlayer()).get(0).isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					GameCharacter target = Main.combat.getEnemies(Main.game.getPlayer()).get(0);
					Main.combat.endCombat(true);
//					Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					Main.game.setContent(new ResponseSex(
							"Dominate",
							"",
							false,
							false,
							new SexManagerDefault(
									true,
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MATING_PRESS)),
									Util.newHashMapOfValues(new Value<>(target, SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(target, Util.newArrayListOfValues(CoverableArea.VAGINA)));
								}
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setLustNoText(100);
										character.setArousal(25);
									} else {
										super.initStartingLustAndArousal(character);
									}
								}
								@Override
								public SexPace getForcedSexPace(GameCharacter character) {
									if(!character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
								@Override
								public boolean isPartnerWantingToStopSex(GameCharacter partner) {
									return false;
								}
								@Override
								public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
									return false;
								}
							},
							null,
							null,
							DebugDialogue.POST_SEX_2KOMA,
							UtilText.parseFromXMLFile("misc/misc", "START_SEX_2KOMA", Main.game.getPlayer(), target)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), target, PenisVagina.PENIS_FUCKING_START, true, true));
						}
					});
					
				} else {
					Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot dominate!");
				}
			}
		}

		if (lastKeysEqual(KeyCode.S, KeyCode.K, KeyCode.O, KeyCode.M, KeyCode.A)) {
			if(Main.game!=null) {
				if(Main.game.isStarted()
						&& Main.game.isInNewWorld()
						&& Main.game.isInCombat()
						&& Main.combat.getAllCombatants(false).size()==1
						&& !Main.combat.getEnemies(Main.game.getPlayer()).get(0).isUnique()
						&& Main.combat.getEnemies(Main.game.getPlayer()).get(0).hasPenis()
						&& Main.combat.getEnemies(Main.game.getPlayer()).get(0).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
						&& Main.game.getPlayer().hasVagina()
						&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					GameCharacter target = Main.combat.getEnemies(Main.game.getPlayer()).get(0);
					Main.combat.endCombat(false);
//					Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					Main.game.setContent(new ResponseSex(
							"Dominated",
							"",
							false,
							false,
							new SexManagerDefault(
									true,
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(target, SexSlotLyingDown.MATING_PRESS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(target, Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									character.setLustNoText(100);
									character.setArousal(25);
								}
								@Override
								public SexPace getForcedSexPace(GameCharacter character) {
									if(!character.isPlayer()) {
										if(character.getFetishDesire(Fetish.FETISH_DOMINANT).isNegative()) {
											return SexPace.DOM_NORMAL;
										}
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
								@Override
								public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isAppendStartingWetDescriptions() {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
								}
							},
							null,
							null,
							DebugDialogue.POST_SEX_2KOMA,
							UtilText.parseFromXMLFile("misc/misc", "START_SEX_2KOMA", target, Main.game.getPlayer())) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(target, Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, true, true));
						}
					});
					
				} else {
					Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot dominate!");
				}
			}
		}
	}

	private boolean lastKeysEqual(KeyCode one, KeyCode two, KeyCode three, KeyCode four, KeyCode five) {
		return lastKeys[0] == five && lastKeys[1] == four && lastKeys[2] == three && lastKeys[3] == two && lastKeys[4] == one;
	}

	/**
	 * Updates every element of the UI.
	 */
	public static void updateUI() {
		if (Main.game.isRenderAttributesSection()) {
			RenderingEngine.ENGINE.renderAttributesPanelLeft();
			RenderingEngine.ENGINE.renderAttributesPanelRight();
		}
		updateUIButtons();
	}
	
	public static void updateUIButtons() {
		RenderingEngine.ENGINE.renderButtonsLeft();
		RenderingEngine.ENGINE.renderButtonsRight();
	}
	
	public void updateUILeftPanel() {
		RenderingEngine.ENGINE.renderAttributesPanelLeft();
	}
	
	public static void updateUIRightPanel() {
		RenderingEngine.ENGINE.renderAttributesPanelRight();
	}

	public void zoomMap() {
		if (!Main.game.getCurrentDialogueNode().isTravelDisabled()) {
			RenderingEngine.setZoomedIn(!RenderingEngine.isZoomedIn());
			
			Main.mainController.updateUILeftPanel();
			RenderingEngine.ENGINE.renderButtonsLeft();
			RenderingEngine.ENGINE.renderButtonsRight();
		}
	}

	private void moveTile(int xOffset, int yOffset) {
		Vector2i location = Main.game.getPlayer().getLocation();
		if (location.getY() + yOffset < Main.game.getActiveWorld().WORLD_HEIGHT
				&& location.getY() + yOffset >= 0
				&& location.getX() + xOffset < Main.game.getActiveWorld().WORLD_WIDTH
				&& location.getX() + xOffset >= 0) {
			
			Cell placeTypeTarget = Main.game.getActiveWorld().getCell(location.getX() + xOffset, location.getY() + yOffset);
			
			if(!placeTypeTarget.getPlace().getPlaceType().equals(PlaceType.GENERIC_IMPASSABLE)) {
				if(Main.game.isInGlobalMap() && placeTypeTarget.getDialogue(false)==null) {
					Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot travel here!");
					
				} else {
					if(Main.game.isInGlobalMap()) {
						Main.game.getPlayer().setGlobalLocation(new Vector2i(location.getX() + xOffset, location.getY() + yOffset));
					} else {
						Main.game.getPlayer().setGlobalLocation(Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(Main.game.getPlayerCell().getType().getGlobalMapLocation()).getLocation());
					}
					Main.game.getPlayer().setLocation(new Vector2i(location.getX() + xOffset, location.getY() + yOffset));
					
					DialogueNode dn = Main.game.getPlayerCell().getDialogue(true);
					
					Main.game.setContent(new Response("", "", dn) {
						@Override
						public int getSecondsPassed() {
							return Main.game.getModifierTravelTime(Main.game.getPlayer().getLocationPlace().getPlaceType().isLand(), dn.getSecondsPassed());
						}
					});
				}
			}
		}
	}
	
	public void moveNorth() {
		moveTile(0, 1);
	}

	public void moveSouth() {
		moveTile(0, -1);
	}

	public void moveEast() {
		moveTile(1, 0);
	}

	public void moveWest() {
		moveTile(-1, 0);
	}

	
	// Getters & Setters:

	// Rendering related:
	public WebView getWebViewMain() {
		return webViewMain;
	}

	public WebView getWebViewAttributes() {
		return webViewAttributes;
	}

	public WebEngine getWebEngine() {
		return webEngine;
	}

	public WebEngine getWebEngineTooltip() {
		return webEngineTooltip;
	}
	
	public WebEngine getWebEngineAttributes() {
		return webEngineAttributes;
	}
	
	public WebEngine getWebEngineRight() {
		return webEngineRight;
	}
	
	public WebEngine getWebEngineButtonsLeft() {
		return webEngineButtonsLeft;
	}
	
	public WebEngine getWebEngineButtonsRight() {
		return webEngineButtonsRight;
	}

	// UI related:
	public Tooltip getTooltip() {
		return tooltip;
	}

	public WebView getWebviewTooltip() {
		return webviewTooltip;
	}

	public void setTooltipSize(int width, int height) {
		webviewTooltip.setMaxWidth(width);
		webviewTooltip.setMaxHeight(height);
		tooltip.setMaxWidth(width);
		tooltip.setMaxHeight(height);
	}

	public KeyboardAction getActionToBind() {
		return actionToBind;
	}

	public boolean isPrimaryBinding() {
		return primaryBinding;
	}
	
	public void switchTheme() {
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			getWebEngineTooltip().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewTooltip_stylesheet.css").toExternalForm());
			getWebEngine().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webView_stylesheet.css").toExternalForm());
			getWebEngineButtonsLeft().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet.css").toExternalForm());
			getWebEngineButtonsRight().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet.css").toExternalForm());
			getWebEngineAttributes().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
			getWebEngineRight().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
	
			Main.mainScene.getStylesheets().clear();
			Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
			Main.primaryStage.setScene(Main.mainScene);
		} else {
			getWebEngineTooltip().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewTooltip_stylesheet_light.css").toExternalForm());
			getWebEngine().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webView_stylesheet_light.css").toExternalForm());
			getWebEngineButtonsLeft().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
			getWebEngineButtonsRight().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
			getWebEngineAttributes().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
			getWebEngineRight().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
	
			Main.mainScene.getStylesheets().clear();
			Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
			Main.primaryStage.setScene(Main.mainScene);
		}
		Main.getProperties().setValue(PropertyValue.lightTheme, !Main.getProperties().hasValue(PropertyValue.lightTheme));
		Main.saveProperties();
	}

	public Colour getFlashMessageColour() {
		return flashMessageColour;
	}

	public void setFlashMessageColour(Colour flashMessageColour) {
		MainController.flashMessageColour = flashMessageColour;
	}

	public String getFlashMessageText() {
		return flashMessageText;
	}

	public void setFlashMessageText(String flashMessageText) {
		MainController.flashMessageText = flashMessageText;
	}

}
