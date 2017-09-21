package com.base.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.base.controller.eventListeners.EnchantmentEventListener;
import com.base.controller.eventListeners.InventoryRemoveJinx;
import com.base.controller.eventListeners.InventorySelectedItemEventListener;
import com.base.controller.eventListeners.InventoryTooltipEventListener;
import com.base.controller.eventListeners.LevelUpButtonsEventListener;
import com.base.controller.eventListeners.SetContentEventListener;
import com.base.controller.eventListeners.TooltipHideEventListener;
import com.base.controller.eventListeners.TooltipInformationEventListener;
import com.base.controller.eventListeners.TooltipMoveEventListener;
import com.base.controller.eventListeners.TooltipResponseDescriptionEventListener;
import com.base.controller.eventListeners.TooltipResponseMoveEventListener;
import com.base.controller.eventListeners.buttons.ButtonCharactersEventListener;
import com.base.controller.eventListeners.buttons.ButtonCopyDialogueEventListener;
import com.base.controller.eventListeners.buttons.ButtonInventoryEventHandler;
import com.base.controller.eventListeners.buttons.ButtonJournalEventListener;
import com.base.controller.eventListeners.buttons.ButtonMainMenuEventListener;
import com.base.controller.eventListeners.buttons.ButtonMoveEastEventListener;
import com.base.controller.eventListeners.buttons.ButtonMoveNorthEventListener;
import com.base.controller.eventListeners.buttons.ButtonMoveSouthEventListener;
import com.base.controller.eventListeners.buttons.ButtonMoveWestEventListener;
import com.base.controller.eventListeners.buttons.ButtonZoomEventListener;
import com.base.controller.eventListeners.information.CopyInfoEventListener;
import com.base.game.KeyCodeWithModifiers;
import com.base.game.KeyboardAction;
import com.base.game.character.CharacterChangeEventListener;
import com.base.game.character.GameCharacter;
import com.base.game.character.NameTriplet;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.BodyHair;
import com.base.game.character.body.valueEnums.CoveringPattern;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.body.valueEnums.HairStyle;
import com.base.game.character.body.valueEnums.PiercingType;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.PerkInterface;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.gender.Gender;
import com.base.game.character.gender.GenderPreference;
import com.base.game.character.gender.GenderPronoun;
import com.base.game.character.npc.NPC;
import com.base.game.character.npc.dominion.TestNPC;
import com.base.game.character.race.FurryPreference;
import com.base.game.character.race.Race;
import com.base.game.combat.Combat;
import com.base.game.combat.DamageType;
import com.base.game.combat.SpecialAttack;
import com.base.game.combat.Spell;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.GenericDialogue;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.places.dominion.CityHall;
import com.base.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.base.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.story.CharacterCreation;
import com.base.game.dialogue.utils.CharactersPresentDialogue;
import com.base.game.dialogue.utils.EnchantmentDialogue;
import com.base.game.dialogue.utils.InventoryDialogue;
import com.base.game.dialogue.utils.InventoryInteraction;
import com.base.game.dialogue.utils.MiscDialogue;
import com.base.game.dialogue.utils.OptionsDialogue;
import com.base.game.dialogue.utils.PhoneDialogue;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.AbstractClothingType;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.enchanting.EnchantingUtils;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.ItemEffect;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.inventory.weapon.AbstractWeaponType;
import com.base.game.inventory.weapon.WeaponType;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.rendering.RenderingEngine;
import com.base.utils.Colour;
import com.base.utils.Vector2i;
import com.base.world.WorldType;
import com.base.world.places.GenericPlaces;
import com.base.world.places.PlaceUpgrade;
import com.base.world.places.ShoppingArcade;
import com.base.world.places.SlaverAlley;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
 * @version 0.1.85
 * @author Innoxia
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
	private WebView webViewMain, webViewAttributes, webViewButtons, webViewResponse;

	private WebEngine webEngine, webEngineTooltip, webEngineAttributes, webEngineButtons, webEngineResponse;
	private WebView webviewTooltip;
	private Tooltip tooltip;
	private EventHandler<KeyEvent> actionKeyPressed, actionKeyReleased;

	// Responses:
	public static final int RESPONSE_COUNT = 15;
	
	// Misc:
	private boolean allowInput;
	private KeyCode[] lastKeys;
	
	private Colour flashMessageColour = null;
	private String flashMessageText = null;

	java.net.CookieManager cookieManager = new java.net.CookieManager();
	
	// Hotkey binding:
	private KeyboardAction actionToBind;
	private boolean primaryBinding;

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

		tooltip = new Tooltip();
		tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		tooltip.setGraphic(webviewTooltip);
		tooltip.setMaxWidth(400);
		tooltip.setMaxHeight(400);

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
					if (Main.game.getPlayer().getLocation().getY() < Main.game.getActiveWorld().WORLD_HEIGHT - 1)
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() + 1).setDiscovered(true);
					if (Main.game.getPlayer().getLocation().getY() != 0)
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() - 1).setDiscovered(true);
					if (Main.game.getPlayer().getLocation().getX() < Main.game.getActiveWorld().WORLD_WIDTH - 1)
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() + 1, Main.game.getPlayer().getLocation().getY()).setDiscovered(true);
					if (Main.game.getPlayer().getLocation().getX() != 0)
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() - 1, Main.game.getPlayer().getLocation().getY()).setDiscovered(true);
				}
			}
		});

//		GameCharacter.addPlayerInventoryChangeEventListener(new CharacterChangeEventListener() {
//			@Override
//			public void onChange() {
//				if (RenderingEngine.ENGINE.getCharactersInventoryToRender() != null)
//					if (RenderingEngine.ENGINE.getCharactersInventoryToRender().isPlayer())
//						RenderingEngine.ENGINE.renderInventory();
//			}
//		});
//
//		GameCharacter.addNPCInventoryChangeEventListener(new CharacterChangeEventListener() {
//			@Override
//			public void onChange() {
//				if (RenderingEngine.ENGINE.getCharactersInventoryToRender() != null)
//					if ((Main.game.isInCombat() && RenderingEngine.ENGINE.getCharactersInventoryToRender() == Combat.getOpponent()) || (Main.game.isInSex() && RenderingEngine.ENGINE.getCharactersInventoryToRender() == Sex.getPartner()))
//						RenderingEngine.ENGINE.renderInventory();
//			}
//		});
		
		allowInput = true;
	}

	// All setup methods:
	public void openOptions() {
		if(!Main.game.isStarted())
			return;
		
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.OPTIONS)
			Main.game.restoreSavedContent();
		else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL)
				Main.game.saveDialogueNode();

			Main.game.setContent(new Response("", "", OptionsDialogue.MENU));
		}
	}

	public void openPhone() {
		if(!Main.game.isStarted())
			return;
		
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.PHONE)
			Main.game.restoreSavedContent();
		else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL)
				Main.game.saveDialogueNode();

			Main.game.setContent(new Response("", "", PhoneDialogue.MENU));
		}
	}

	public boolean isInventoryDisabled() {
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.INVENTORY || Main.game.isInCombat() || Main.game.isInSex()) {
			return false;
			
		} else if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.OPTIONS || Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.PHONE) {
			return Main.game.getSavedDialogueNode().isInventoryDisabled();
		
		} else {
			return Main.game.getCurrentDialogueNode().isInventoryDisabled();
		}
	}

	public void openInventory() {
		if(Main.game.isInCombat()) {
			openInventory((NPC) Combat.getOpponent(), InventoryInteraction.COMBAT);
			
		} else if(Main.game.isInSex()) {
			openInventory((NPC) Sex.getPartner(), InventoryInteraction.SEX);
			
		} else {
			openInventory(null, InventoryInteraction.FULL_MANAGEMENT);
		}
	}
	
	public void openInventory(NPC npc, InventoryInteraction interaction) {
		if(!Main.game.isStarted()) {
			return;
		}
		
		InventoryDialogue.setBuyback(false);
		InventoryDialogue.setInventoryNPC(npc);
		InventoryDialogue.setNPCInventoryInteraction(interaction);
		
		
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.INVENTORY) {
			Main.game.restoreSavedContent();

		} else if (!isInventoryDisabled() || npc != null) {
			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL) {
				Main.game.saveDialogueNode();
			}
			
			InventoryDialogue.populateJinxedClothingList();
			Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
		}

		// processNewDialogue();
	}

	public void openCharactersPresent() {
		if(!Main.game.isStarted())
			return;
		
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.CHARACTERS_PRESENT) {
			Main.game.restoreSavedContent();
			
		} else if (!Main.game.getCharactersPresent().isEmpty()) {

			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL)
				Main.game.saveDialogueNode();

			CharactersPresentDialogue.resetContent();
			Main.game.setContent(new Response("", "", CharactersPresentDialogue.MENU));
		}
	}

	/**
	 * Sets up buttons and hotkeys.
	 */
	private List<KeyCode> buttonsPressed = new ArrayList<>();

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
				Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>The key '" + eventCodeName
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

						for (int i = 4; i > 0; i--)
							lastKeys[i] = lastKeys[i - 1];
						lastKeys[0] = event.getCode();
						checkLastKeys();

//						 System.out.println(event.getCode());
						 if(event.getCode()==KeyCode.END){
							 
//							 for(NPC npc : Main.game.getNPCList())
//								 System.out.println(npc.getName());
							 
//							 webViewMain = new WebView();
//							 webViewAttributes = new WebView(); 
//							 webViewInventory = new WebView();
//							 webViewMap = new WebView();
//							 webViewMapTitle = new WebView();
//							 webViewButtons = new WebView();
//							 webViewResponse = new WebView();
//							 
//							 setUpWebViews();
							 
//							File dir = new File("data/clothing");
//							dir.mkdir();
//							for (ClothingType ct : ClothingType.values()) {
//								
//								dir = new File("data/clothing/"+ct);
//								dir.mkdir();
//								
//								for(Colour c : ct.getAvailableColours()) {
//									try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/clothing/"+ct+"/"+ct.getName().replaceAll(" ", "_")+"_"+c+".svg"), "utf-8"))) {
//										writer.write(ct.getSVGImage(c));
//									} catch (IOException e) {
//										e.printStackTrace();
//									}
//								}
//							}
//							 dir = new File("data/items");
//								dir.mkdir();
//							for (ItemType ct : ItemType.values()) {
//								
//								
//								try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/items/"+ct.getName(false).replaceAll(" ", "_")+".svg"), "utf-8"))) {
//									writer.write(ct.getSVGString());
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//							}
//							 dir = new File("data/weapons");
//								dir.mkdir();
//							for (WeaponType ct : WeaponType.values()) {
//								
//								for(DamageType dt : ct.getAvailableDamageTypes())
//								try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/weapons/"+ct.getName().replaceAll(" ", "_")+"("+dt+").svg"), "utf-8"))) {
//									writer.write(ct.getSVGStringMap().get(dt));
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//							}
//							 dir = new File("data/statusEffects");
//								dir.mkdir();
//							for (StatusEffect se : StatusEffect.values()) {
//								if(!se.isSexEffect()) {
//									try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/statusEffects/"+se+"("+se.getName(Main.game.getPlayer()).replaceAll(" ", "_")+").svg"), "utf-8"))) {
//										writer.write(se.getSVGString(Main.game.getPlayer()));
//									} catch (IOException e) {
//										e.printStackTrace();
//									}
//								}
//							}
//							 dir = new File("data/fetishes");
//								dir.mkdir();
//							for (Fetish se : Fetish.values()) {
//								
//								try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/fetishes/"+se+"("+se.getName(Main.game.getPlayer()).replaceAll(" ", "_")+").svg"), "utf-8"))) {
//									writer.write(se.getSVGString());
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//							}
							 
//							 Main.getProperties().savePropertiesAsXML();
//							 System.out.println("Free memory (bytes) -gc: " + Runtime.getRuntime().freeMemory());
//							 System.gc();
//							 System.out.println("Free memory (bytes) +gc: " + Runtime.getRuntime().freeMemory());
//							 System.out.println("Body sizes:");
//							 for(BodySize bs : BodySize.values()) {
//								 System.out.println(bs.getName(false));
//							 }
//							 System.out.println("");
//							 System.out.println("Muscle:");
//							 for(Muscle m : Muscle.values()) {
//								 System.out.println(m.getName(false));
//							 }
//							 System.out.println("");
//							 System.out.println("");
//							 System.out.println("Body shapes:");
//							 for(BodyShape bs : BodyShape.values()) {
//								 System.out.println(bs.getRelatedBodySize().getName(false)+" + "+bs.getRelatedMuscle().getName(false)+" = "+bs.getName());
//							 }
							 
							 
						 }
						 
//						 if(event.getCode()==KeyCode.DELETE){
//							 for(Fetish fetish : Fetish.values()) {
//								 Main.game.getPlayer().addFetish(fetish);
//								 Sex.getPartner().addFetish(fetish);
//							 }
//						 }
//						 System.out.println(event.getCode());
						 

						// Escape Menu:
						if (keyEventMatchesBindings(KeyboardAction.MENU, event))
							openOptions();

						// Movement:
						if (keyEventMatchesBindings(KeyboardAction.MOVE_NORTH, event)) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled() && !event.isControlDown()) {
								moveNorth();
							} else {
								Main.game.responseNavigationUp();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_WEST, event)) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled() && !event.isControlDown()) {
								moveWest();
							} else {
								Main.game.responseNavigationLeft();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_SOUTH, event)) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled() && !event.isControlDown()) {
								moveSouth();
							} else {
								Main.game.responseNavigationDown();
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.MOVE_EAST, event)) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled() && !event.isControlDown()) {
								moveEast();
							} else {
								Main.game.responseNavigationRight();
							}
						}

						// Game stuff:
						if (keyEventMatchesBindings(KeyboardAction.QUICKSAVE, event)) {
							Main.quickSaveGame();
						}
						if (keyEventMatchesBindings(KeyboardAction.QUICKLOAD, event)) {
							Main.quickLoadGame();
						}
						
						boolean allowInput = true;
						
						if(Main.game.getCurrentDialogueNode() == CharacterCreation.CHOOSE_NAME){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput') === document.activeElement"))
								allowInput = false;
						}
						
						if(Main.game.getCurrentDialogueNode() == LilayaHomeGeneric.ROOM_UPGRADES){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput') === document.activeElement"))
								allowInput = false;
						}
						
						if(Main.game.getCurrentDialogueNode() == CityHall.CITY_HALL_NAME_CHANGE_FORM){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput') === document.activeElement"))
								allowInput = false;
						}
						
						
						if(Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('new_save_name') === document.activeElement"))
								allowInput = false;
						}
						
						if(Main.game.getCurrentDialogueNode() == OptionsDialogue.OPTIONS_PRONOUNS){
							for(GenderPronoun gp : GenderPronoun.values())
								if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('feminine_" + gp + "') === document.activeElement")
									|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('masculine_" + gp + "') === document.activeElement"))
									allowInput = false;
						}
						
						if(Main.game.getCurrentDialogueNode() == GenericDialogue.PARSER){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('parseInput') === document.activeElement"))
								allowInput = false;
						}
						
						if(allowInput){
							if (keyEventMatchesBindings(KeyboardAction.INVENTORY, event))
								openInventory();
							if (keyEventMatchesBindings(KeyboardAction.JOURNAL, event))
								openPhone();
							if (keyEventMatchesBindings(KeyboardAction.CHARACTERS, event))
								openCharactersPresent();
							if (keyEventMatchesBindings(KeyboardAction.ZOOM, event))
								zoomMap();
	
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
								Main.game.setContent(Main.game.getResponsePointer());
							}
							
						}
						
						// For name selection:
						if (event.getCode() == KeyCode.ENTER
								&& (Main.game.getCurrentDialogueNode() == CharacterCreation.CHOOSE_NAME
									|| Main.game.getCurrentDialogueNode() == LilayaHomeGeneric.ROOM_UPGRADES
									|| Main.game.getCurrentDialogueNode() == CityHall.CITY_HALL_NAME_CHANGE_FORM)) {
							Main.game.setContent(1);
						}

						// Next/Previous response page:
						if (keyEventMatchesBindings(KeyboardAction.RESPOND_NEXT_PAGE, event)) {
							if (Main.game.isHasNextResponsePage()) {
								Main.game.setResponsePage(Main.game.getResponsePage() + 1);
								Main.game.setResponses(Main.game.getCurrentDialogueNode());
							}
						}
						if (keyEventMatchesBindings(KeyboardAction.RESPOND_PREVIOUS_PAGE, event)) {
							if (Main.game.getResponsePage() != 0) {
								Main.game.setResponsePage(Main.game.getResponsePage() - 1);
								Main.game.setResponses(Main.game.getCurrentDialogueNode());
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

	// /**
	// * Sets up canvas scaling constraints.
	// */
	// private void setUpCanvasControls(){
	// ((AnchorPane)mainCanvas.getParent()).widthProperty().addListener(observable
	// -> {
	// mainCanvas.setWidth(((AnchorPane)mainCanvas.getParent()).getWidth()-10);
	// //mainCanvas.setLayoutX(((AnchorPane)mainCanvas.getParent()).getLayoutX());
	// });
	// ((AnchorPane)mainCanvas.getParent()).heightProperty().addListener(observable
	// -> {
	// mainCanvas.setHeight(((AnchorPane)mainCanvas.getParent()).getHeight()-10);
	// //mainCanvas.setLayoutY(((AnchorPane)mainCanvas.getParent()).getLayoutY());
	// });
	// }

	
	// Event listeners:
	
	// General tooltips:
	private TooltipMoveEventListener moveTooltipListener = new TooltipMoveEventListener();
	private TooltipHideEventListener hideTooltipListener = new TooltipHideEventListener();
	
	// Buttons:
	private ButtonCopyDialogueEventListener copyDialogueButtonListener = new ButtonCopyDialogueEventListener();
	private ButtonCharactersEventListener charactersPresentButtonListener = new ButtonCharactersEventListener();
	private ButtonInventoryEventHandler inventoryButtonListener = new ButtonInventoryEventHandler();
	private ButtonJournalEventListener journalButtonListener = new ButtonJournalEventListener();
	private ButtonMainMenuEventListener menuButtonListener = new ButtonMainMenuEventListener();
	private ButtonZoomEventListener zoomButtonListener = new ButtonZoomEventListener();
	
	// Map movement:
	private ButtonMoveNorthEventListener moveNorthListener = new ButtonMoveNorthEventListener();
	private ButtonMoveSouthEventListener moveSouthListener = new ButtonMoveSouthEventListener();
	private ButtonMoveEastEventListener moveEastListener = new ButtonMoveEastEventListener();
	private ButtonMoveWestEventListener moveWestListener = new ButtonMoveWestEventListener();
	
	// Information:
	private CopyInfoEventListener copyInfoListener = new CopyInfoEventListener();
	
	// Responses:
	private SetContentEventListener[] setContentListeners = new SetContentEventListener[RESPONSE_COUNT];
	private TooltipResponseDescriptionEventListener[] responseDescriptionListeners = new TooltipResponseDescriptionEventListener[RESPONSE_COUNT];
	private TooltipResponseMoveEventListener responseTooltipListener = new TooltipResponseMoveEventListener();
	private SetContentEventListener nextResponsePageListener = new SetContentEventListener().nextPage();
	private SetContentEventListener previousResponsePageListener = new SetContentEventListener().previousPage();
	
	// Temporary ones to clear:
	private Map<Document, List<EventListenerData>> EventListenerDataMap = new HashMap<>();
	
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
	
	private void addEventListener(Document document, String ID, String type, EventListener listener, boolean useCapture) {
		((EventTarget) document.getElementById(ID)).addEventListener(type, listener, useCapture);
		EventListenerDataMap.get(document).add(new EventListenerData(ID, type, listener, useCapture));
	}
	
	public static Document document, documentResponse, documentButtons, documentAttributes, documentInventory, documentMap, documentMapTitle;
	private boolean debugAllowListeners = true;
	/**
	 * Sets up all WebView EventListeners and WebEngines.
	 */
	private void setUpWebViews() {

		java.net.CookieHandler.setDefault(cookieManager);
		
		// Tooltip WebView:
		webviewTooltip.setContextMenuEnabled(false);
		webEngineTooltip = webviewTooltip.getEngine();
		webEngineTooltip.getHistory().setMaxSize(0);
		
		if (Main.getProperties().lightTheme) {
			webEngineTooltip.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewTooltip_stylesheet_light.css").toExternalForm());
		} else {
			webEngineTooltip.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewTooltip_stylesheet.css").toExternalForm());
		}
		
		// Main WebView:
		webViewMain.setContextMenuEnabled(false);
		webEngine = webViewMain.getEngine();
		webEngine.getHistory().setMaxSize(0);
		
		if (Main.getProperties().lightTheme) {
			webEngine.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webView_stylesheet_light.css").toExternalForm());
		} else {
			webEngine.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webView_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
		webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(document);
				manageMainListeners();
			}
		});

		// Responses:
		webViewResponse.setContextMenuEnabled(false);
		webEngineResponse = webViewResponse.getEngine();
		webEngineResponse.getHistory().setMaxSize(0);
		
		if (Main.getProperties().lightTheme) {
			webEngineResponse.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewResponse_stylesheet_light.css").toExternalForm());
		} else {
			webEngineResponse.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewResponse_stylesheet.css").toExternalForm());
		}
		
		for (int i = 0; i < RESPONSE_COUNT; i++) {
			setContentListeners[i] = new SetContentEventListener().setIndex(i);
			responseDescriptionListeners[i] = new TooltipResponseDescriptionEventListener().setIndex(i);
		}
		if(debugAllowListeners)
		webEngineResponse.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(documentResponse);
				manageResponseListeners();
			}
		});

		// Buttons webview:

		webViewButtons.setContextMenuEnabled(false);
		webEngineButtons = webViewButtons.getEngine();
		webEngineButtons.getHistory().setMaxSize(0);
		
		if (Main.getProperties().lightTheme) {
			webEngineButtons.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
		} else {
			webEngineButtons.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewButtons_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
		webEngineButtons.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(documentButtons);
				manageButtonListeners();
			}
		});

		
		// Attributes WebView:
		webViewAttributes.setContextMenuEnabled(false);
		webEngineAttributes = webViewAttributes.getEngine();
		webEngineAttributes.getHistory().setMaxSize(0);
		
		if (Main.getProperties().lightTheme) {
			webEngineAttributes.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
		} else {
			webEngineAttributes.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewAttributes_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
		webEngineAttributes.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(documentAttributes);
				manageAttributeListeners();
			}
		});
	}
	
	private void manageMainListeners() {
		document = (Document) webEngine.executeScript("document");
		EventListenerDataMap.put(document, new ArrayList<>());
		
		String id = "";
		
		if(flashMessageColour !=null && flashMessageText != null) {
			Main.game.flashMessage(flashMessageColour, flashMessageText);
			flashMessageColour = null;
			flashMessageText = null;
		}
		
		if (((EventTarget) document.getElementById("copy-content-button")) != null) {
			addEventListener(document, "copy-content-button", "click", copyDialogueButtonListener, false);
			addEventListener(document, "copy-content-button", "mousemove", moveTooltipListener, false);
			addEventListener(document, "copy-content-button", "mouseleave", hideTooltipListener, false);
			addEventListener(document, "copy-content-button", "mouseenter", copyInfoListener, false);
		}
		
		// Combat tooltips:
		if (Main.game.isInCombat()) {
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (((EventTarget) document.getElementById("COMBAT_PLAYER_SE_" + se)) != null) {
					addEventListener(document, "COMBAT_PLAYER_SE_" + se, "mousemove", moveTooltipListener, false);
					addEventListener(document, "COMBAT_PLAYER_SE_" + se, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, Main.game.getPlayer());
					addEventListener(document, "COMBAT_PLAYER_SE_" + se, "mouseenter", el, false);
				}
			}
			for (SpecialAttack sa : Main.game.getPlayer().getSpecialAttacks()) {
				if (((EventTarget) document.getElementById("COMBAT_PLAYER_SA_" + sa)) != null) {
					addEventListener(document, "COMBAT_PLAYER_SA_" + sa, "mousemove", moveTooltipListener, false);
					addEventListener(document, "COMBAT_PLAYER_SA_" + sa, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setSpecialAttack(sa, Main.game.getPlayer());
					addEventListener(document, "COMBAT_PLAYER_SA_" + sa, "mouseenter", el, false);
				}
			}
			if (Main.game.getPlayer().getMainWeapon() != null) {
				for (Spell s : Main.game.getPlayer().getMainWeapon().getSpells()) {
					if (((EventTarget) document.getElementById("COMBAT_PLAYER_SPELL_MAIN_" + s)) != null) {
						addEventListener(document, "COMBAT_PLAYER_SPELL_MAIN_" + s, "mousemove", moveTooltipListener, false);
						addEventListener(document, "COMBAT_PLAYER_SPELL_MAIN_" + s, "mouseleave", hideTooltipListener, false);
						
						TooltipInformationEventListener el = new TooltipInformationEventListener().setSpell(s, Main.game.getPlayer().getLevel(), Main.game.getPlayer());
						addEventListener(document, "COMBAT_PLAYER_SPELL_MAIN_" + s, "mouseenter", el, false);
					}
				}
			}
			if (Main.game.getPlayer().getOffhandWeapon() != null) {
				for (Spell s : Main.game.getPlayer().getOffhandWeapon().getSpells()) {
					if (((EventTarget) document.getElementById("COMBAT_PLAYER_SPELL_OFFHAND_" + s)) != null) {
						addEventListener(document, "COMBAT_PLAYER_SPELL_OFFHAND_" + s, "mousemove", moveTooltipListener, false);
						addEventListener(document, "COMBAT_PLAYER_SPELL_OFFHAND_" + s, "mouseleave", hideTooltipListener, false);
						
						TooltipInformationEventListener el = new TooltipInformationEventListener().setSpell(s, Main.game.getPlayer().getLevel(), Main.game.getPlayer());
						addEventListener(document, "COMBAT_PLAYER_SPELL_OFFHAND_" + s, "mouseenter", el, false);
					}
				}
			}
			
			for (StatusEffect se : Combat.getOpponent().getStatusEffects()) {
				if (((EventTarget) document.getElementById("SE_COMBAT_" + se)) != null) {
					addEventListener(document, "SE_COMBAT_" + se, "mousemove", moveTooltipListener, false);
					addEventListener(document, "SE_COMBAT_" + se, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, Combat.getOpponent());
					addEventListener(document, "SE_COMBAT_" + se, "mouseenter", el, false);
				}
			}
			if (((EventTarget) document.getElementById("SE_COMBAT_" + StatusEffect.COMBAT_HIDDEN)) != null) {
				addEventListener(document, "SE_COMBAT_" + StatusEffect.COMBAT_HIDDEN, "mousemove", moveTooltipListener, false);
				addEventListener(document, "SE_COMBAT_" + StatusEffect.COMBAT_HIDDEN, "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(StatusEffect.COMBAT_HIDDEN, Combat.getOpponent());
				addEventListener(document, "SE_COMBAT_" + StatusEffect.COMBAT_HIDDEN, "mouseenter", el, false);
			}
			for (Perk p : Combat.getOpponent().getPerks()) {
				if (((EventTarget) document.getElementById("PERK_COMBAT_" + p)) != null) {
					addEventListener(document, "PERK_COMBAT_" + p, "mousemove", moveTooltipListener, false);
					addEventListener(document, "PERK_COMBAT_" + p, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setPerk(p, Combat.getOpponent());
					addEventListener(document, "PERK_COMBAT_" + p, "mouseenter", el, false);
				}
			}
			for (Fetish f : Combat.getOpponent().getFetishes()) {
				if (((EventTarget) document.getElementById("FETISH_COMBAT_" + f)) != null) {
					addEventListener(document, "FETISH_COMBAT_" + f, "mousemove", moveTooltipListener, false);
					addEventListener(document, "FETISH_COMBAT_" + f, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setFetish(f, Combat.getOpponent());
					addEventListener(document, "FETISH_COMBAT_" + f, "mouseenter", el, false);
				}
			}
			for (SpecialAttack sa : Combat.getOpponent().getSpecialAttacks()) {
				if (((EventTarget) document.getElementById("SA_COMBAT_" + sa)) != null) {
					addEventListener(document, "SA_COMBAT_" + sa, "mousemove", moveTooltipListener, false);
					addEventListener(document, "SA_COMBAT_" + sa, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setSpecialAttack(sa, Combat.getOpponent());
					addEventListener(document, "SA_COMBAT_" + sa, "mouseenter", el, false);
				}
			}
			if (Combat.getOpponent().getMainWeapon() != null)
				for (Spell s : Combat.getOpponent().getMainWeapon().getSpells()) {
					if (((EventTarget) document.getElementById("SPELL_MAIN_COMBAT_" + s)) != null) {
						addEventListener(document, "SPELL_MAIN_COMBAT_" + s, "mousemove", moveTooltipListener, false);
						addEventListener(document, "SPELL_MAIN_COMBAT_" + s, "mouseleave", hideTooltipListener, false);
						
						TooltipInformationEventListener el = new TooltipInformationEventListener().setSpell(s, Combat.getOpponent().getLevel(), Combat.getOpponent());
						addEventListener(document, "SPELL_MAIN_COMBAT_" + s, "mouseenter", el , false);
					}
				}
			if (Combat.getOpponent().getOffhandWeapon() != null)
				for (Spell s : Combat.getOpponent().getOffhandWeapon().getSpells()) {
					if (((EventTarget) document.getElementById("SPELL_OFFHAND_COMBAT_" + s)) != null) {
						addEventListener(document, "SPELL_OFFHAND_COMBAT_" + s, "mousemove", moveTooltipListener, false);
						addEventListener(document, "SPELL_OFFHAND_COMBAT_" + s, "mouseleave", hideTooltipListener, false);
						
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setSpell(s, Combat.getOpponent().getLevel(), Combat.getOpponent());
						addEventListener(document, "SPELL_OFFHAND_COMBAT_" + s, "mouseenter", el, false);
					}
				}

			Attribute[] attributes = { Attribute.STRENGTH, Attribute.INTELLIGENCE, Attribute.FITNESS, Attribute.CORRUPTION, Attribute.HEALTH_MAXIMUM, Attribute.MANA_MAXIMUM, Attribute.STAMINA_MAXIMUM };
			for (Attribute a : attributes) {
				if (((EventTarget) document.getElementById("COMBAT_PLAYER_" + a)) != null) {
					addEventListener(document, "COMBAT_PLAYER_" + a, "mousemove", moveTooltipListener, false);
					addEventListener(document, "COMBAT_PLAYER_" + a, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(a, Main.game.getPlayer());
					addEventListener(document, "COMBAT_PLAYER_" + a, "mouseenter", el, false);
				}
				if (((EventTarget) document.getElementById("COMBAT_OPPONENT_" + a)) != null) {
					addEventListener(document, "COMBAT_OPPONENT_" + a, "mousemove", moveTooltipListener, false);
					addEventListener(document, "COMBAT_OPPONENT_" + a, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(a, Combat.getOpponent());
					addEventListener(document, "COMBAT_OPPONENT_" + a, "mouseenter", el, false);
				}
			}

			if (((EventTarget) document.getElementById("COMBAT_OPPONENT_ATTRIBUTES")) != null) {
				addEventListener(document, "COMBAT_OPPONENT_ATTRIBUTES", "mousemove", moveTooltipListener, false);
				addEventListener(document, "COMBAT_OPPONENT_ATTRIBUTES", "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el = new TooltipInformationEventListener().setOpponentExtraAttributes();
				addEventListener(document, "COMBAT_OPPONENT_ATTRIBUTES", "mouseenter", el, false);
			}

		}
		
		if (Main.game.getCurrentDialogueNode()==CharactersPresentDialogue.MENU || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS) {
			
			for (StatusEffect se : CharactersPresentDialogue.characterViewed.getStatusEffects()) {
				if (((EventTarget) document.getElementById("SE_COMBAT_" + se)) != null) {
					addEventListener(document, "SE_COMBAT_" + se, "mousemove", moveTooltipListener, false);
					addEventListener(document, "SE_COMBAT_" + se, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, CharactersPresentDialogue.characterViewed);
					addEventListener(document, "SE_COMBAT_" + se, "mouseenter", el, false);
				}
			}
			if (((EventTarget) document.getElementById("SE_COMBAT_" + StatusEffect.COMBAT_HIDDEN)) != null) {
				addEventListener(document, "SE_COMBAT_" + StatusEffect.COMBAT_HIDDEN, "mousemove", moveTooltipListener, false);
				addEventListener(document, "SE_COMBAT_" + StatusEffect.COMBAT_HIDDEN, "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(StatusEffect.COMBAT_HIDDEN, CharactersPresentDialogue.characterViewed);
				addEventListener(document, "SE_COMBAT_" + StatusEffect.COMBAT_HIDDEN, "mouseenter", el, false);
			}
			for (Perk p : CharactersPresentDialogue.characterViewed.getPerks()) {
				if (((EventTarget) document.getElementById("PERK_COMBAT_" + p)) != null) {
					addEventListener(document, "PERK_COMBAT_" + p, "mousemove", moveTooltipListener, false);
					addEventListener(document, "PERK_COMBAT_" + p, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setPerk(p, CharactersPresentDialogue.characterViewed);
					addEventListener(document, "PERK_COMBAT_" + p, "mouseenter", el, false);
				}
			}
			for (Fetish f : CharactersPresentDialogue.characterViewed.getFetishes()) {
				if (((EventTarget) document.getElementById("FETISH_COMBAT_" + f)) != null) {
					addEventListener(document, "FETISH_COMBAT_" + f, "mousemove", moveTooltipListener, false);
					addEventListener(document, "FETISH_COMBAT_" + f, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setFetish(f, CharactersPresentDialogue.characterViewed);
					addEventListener(document, "FETISH_COMBAT_" + f, "mouseenter", el, false);
				}
			}
			for (SpecialAttack sa : CharactersPresentDialogue.characterViewed.getSpecialAttacks()) {
				if (((EventTarget) document.getElementById("SA_COMBAT_" + sa)) != null) {
					addEventListener(document, "SA_COMBAT_" + sa, "mousemove", moveTooltipListener, false);
					addEventListener(document, "SA_COMBAT_" + sa, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setSpecialAttack(sa, CharactersPresentDialogue.characterViewed);
					addEventListener(document, "SA_COMBAT_" + sa, "mouseenter", el, false);
				}
			}
			if (CharactersPresentDialogue.characterViewed.getMainWeapon() != null)
				for (Spell s : CharactersPresentDialogue.characterViewed.getMainWeapon().getSpells()) {
					if (((EventTarget) document.getElementById("SPELL_MAIN_COMBAT_" + s)) != null) {
						addEventListener(document, "SPELL_MAIN_COMBAT_" + s, "mousemove", moveTooltipListener, false);
						addEventListener(document, "SPELL_MAIN_COMBAT_" + s, "mouseleave", hideTooltipListener, false);
						
						TooltipInformationEventListener el = new TooltipInformationEventListener().setSpell(s, CharactersPresentDialogue.characterViewed.getLevel(), CharactersPresentDialogue.characterViewed);
						addEventListener(document, "SPELL_MAIN_COMBAT_" + s, "mouseenter", el , false);
					}
				}
			if (CharactersPresentDialogue.characterViewed.getOffhandWeapon() != null)
				for (Spell s : CharactersPresentDialogue.characterViewed.getOffhandWeapon().getSpells()) {
					if (((EventTarget) document.getElementById("SPELL_OFFHAND_COMBAT_" + s)) != null) {
						addEventListener(document, "SPELL_OFFHAND_COMBAT_" + s, "mousemove", moveTooltipListener, false);
						addEventListener(document, "SPELL_OFFHAND_COMBAT_" + s, "mouseleave", hideTooltipListener, false);
						
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setSpell(s, CharactersPresentDialogue.characterViewed.getLevel(), CharactersPresentDialogue.characterViewed);
						addEventListener(document, "SPELL_OFFHAND_COMBAT_" + s, "mouseenter", el, false);
					}
				}

			Attribute[] attributes = { Attribute.STRENGTH, Attribute.INTELLIGENCE, Attribute.FITNESS, Attribute.CORRUPTION, Attribute.HEALTH_MAXIMUM, Attribute.MANA_MAXIMUM, Attribute.STAMINA_MAXIMUM };
			for (Attribute a : attributes) {
				if (((EventTarget) document.getElementById("COMBAT_PLAYER_" + a)) != null) {
					addEventListener(document, "COMBAT_PLAYER_" + a, "mousemove", moveTooltipListener, false);
					addEventListener(document, "COMBAT_PLAYER_" + a, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(a, Main.game.getPlayer());
					addEventListener(document, "COMBAT_PLAYER_" + a, "mouseenter", el, false);
				}
				if (((EventTarget) document.getElementById("COMBAT_OPPONENT_" + a)) != null) {
					addEventListener(document, "COMBAT_OPPONENT_" + a, "mousemove", moveTooltipListener, false);
					addEventListener(document, "COMBAT_OPPONENT_" + a, "mouseleave", hideTooltipListener, false);
					
					TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(a, CharactersPresentDialogue.characterViewed);
					addEventListener(document, "COMBAT_OPPONENT_" + a, "mouseenter", el, false);
				}
			}

			if (((EventTarget) document.getElementById("COMBAT_OPPONENT_ATTRIBUTES")) != null) {
				addEventListener(document, "COMBAT_OPPONENT_ATTRIBUTES", "mousemove", moveTooltipListener, false);
				addEventListener(document, "COMBAT_OPPONENT_ATTRIBUTES", "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el = new TooltipInformationEventListener().setOpponentExtraAttributes();
				addEventListener(document, "COMBAT_OPPONENT_ATTRIBUTES", "mouseenter", el, false);
			}

		}
		
		
		// -------------------- Inventory listeners -------------------- //
		
		if(Main.game.isStarted()) {
			id = "";
			
			// Equipped inventory:
			
			// For weapons:
			InventorySlot[] inventorySlots = { InventorySlot.WEAPON_MAIN, InventorySlot.WEAPON_OFFHAND };
			for (InventorySlot invSlot : inventorySlots) {
				id = "PLAYER_" + invSlot.toString() + "Slot";
				if (((EventTarget) document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(Main.game.getPlayer(), invSlot);
					addEventListener(document, id, "click", el, false);
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, Main.game.getPlayer());
					addEventListener(document, id, "mouseenter", el2, false);
				}
				
				id = "NPC_" + invSlot.toString() + "Slot";
				if (((EventTarget) document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(InventoryDialogue.getInventoryNPC(), invSlot);
					addEventListener(document, id, "click", el, false);
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, InventoryDialogue.getInventoryNPC());
					addEventListener(document, id, "mouseenter", el2, false);
				}
				
				id = "NPC_VIEW_" + invSlot.toString() + "Slot";
				if (((EventTarget) document.getElementById(id)) != null) {
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, CharactersPresentDialogue.characterViewed);
					addEventListener(document, id, "mouseenter", el2, false);
				}
			}

			// For all equipped clothing slots:
			for (InventorySlot invSlot : InventorySlot.values()) {
				id = "PLAYER_" + invSlot.toString() + "Slot";
				if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
					if (((EventTarget) document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(Main.game.getPlayer(),invSlot);
						addEventListener(document, id, "click", el, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, Main.game.getPlayer());
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				
				id = "NPC_" + invSlot.toString() + "Slot";
				if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
					if (((EventTarget) document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(InventoryDialogue.getInventoryNPC(), invSlot);
						addEventListener(document, id, "click", el, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, InventoryDialogue.getInventoryNPC());
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				
				id = "NPC_VIEW_" + invSlot.toString() + "Slot";
				if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, CharactersPresentDialogue.characterViewed);
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
			}
			
			
			
			// Non-equipped inventory:
			
			// Player:
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet()) {
				id = "PLAYER_WEAPON_" + entry.getKey().hashCode();
				if (((EventTarget) document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), Main.game.getPlayer());
					addEventListener(document, id, "click", el, false);
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), Main.game.getPlayer());
					addEventListener(document, id, "mouseenter", el2, false);
				}
			}
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet()) {
				id = "PLAYER_ITEM_" + entry.getKey().hashCode();
				if (((EventTarget) document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), Main.game.getPlayer());
					addEventListener(document, id, "click", el, false);
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
					addEventListener(document, id, "mouseenter", el2, false);
				}
			}
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet()) {
				id = "PLAYER_CLOTHING_" + entry.getKey().hashCode();
				if (((EventTarget) document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), Main.game.getPlayer());
					addEventListener(document, id, "click", el, false);
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
					addEventListener(document, id, "mouseenter", el2, false);
				}
			}
			
			// Partner:
			if(InventoryDialogue.getInventoryNPC()!=null) {
				for (Entry<AbstractWeapon, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateWeapons().entrySet()) {
					id = "NPC_WEAPON_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						addEventListener(document, id, "click", el, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), InventoryDialogue.getInventoryNPC());
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				
				for (Entry<AbstractClothing, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateClothing().entrySet()) {
					id = "NPC_CLOTHING_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						addEventListener(document, id, "click", el, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), InventoryDialogue.getInventoryNPC(), null);
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				
				for (Entry<AbstractItem, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateItems().entrySet()) {
					id = "NPC_ITEM_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						addEventListener(document, id, "click", el, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), InventoryDialogue.getInventoryNPC(), null);
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				
			// Floor:
			} else {
				// Weapons on floor:
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons().entrySet()) {
					id = "WEAPON_FLOOR_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), null);
						addEventListener(document, id, "click", el, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), null);
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				
				// Clothing on floor:
				for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
					id = "CLOTHING_FLOOR_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), null);
						addEventListener(document, id, "click", el, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), null, null);
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				
				// Items on floor:
				for (Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
					id = "ITEM_FLOOR_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), null);
						addEventListener(document, id, "click", el, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), null, null);
						addEventListener(document, id, "mouseenter", el2, false);	
					}
				}
			}
			
			if(InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			
				if(InventoryDialogue.getInventoryNPC() != null) {
					// Buyback panel:
					for (int i = Main.game.getPlayer().getBuybackStack().size() - 1; i >= 0; i--) {
						if (((EventTarget) document.getElementById("WEAPON_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory((AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							((EventTarget) document.getElementById("WEAPON_" + i)).addEventListener("click",el, false);
							addEventListener(document, "WEAPON_" + i, "mousemove", moveTooltipListener, false);
							addEventListener(document, "WEAPON_" + i, "mouseleave", hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon((AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC());
							((EventTarget) document.getElementById("WEAPON_" + i)).addEventListener("mouseenter",el2, false);
						}
						
						if (((EventTarget) document.getElementById("CLOTHING_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							addEventListener(document, "CLOTHING_" + i, "click", el, false);
							addEventListener(document, "CLOTHING_" + i, "mousemove", moveTooltipListener, false);
							addEventListener(document, "CLOTHING_" + i, "mouseleave", hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), null);
							addEventListener(document, "CLOTHING_" + i, "mouseenter", el2, false);
						}
						
						if (((EventTarget) document.getElementById("ITEM_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							addEventListener(document, "ITEM_" + i, "click", el, false);
							addEventListener(document, "ITEM_" + i, "mousemove", moveTooltipListener, false);
							addEventListener(document, "ITEM_" + i, "mouseleave", hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), null);
							addEventListener(document, "ITEM_" + i, "mouseenter", el2, false);
						}
					}
				}
			}
			
			for(int i=0; i<InventoryDialogue.getJinxedClothing().size(); i++) {
				if (((EventTarget) document.getElementById("JINXED_" + i)) != null) {
					addEventListener(document, "JINXED_" + i, "click", new InventoryRemoveJinx().setJinxIndex(i), false);
					
					addEventListener(document, "JINXED_" + i, "mousemove", moveTooltipListener, false);
					addEventListener(document, "JINXED_" + i, "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(InventoryDialogue.getJinxedClothing().get(i), Main.game.getPlayer(), null);
					addEventListener(document, "JINXED_" + i, "mouseenter", el2, false);
				}
				
			}
			
			for(TFEssence essence : TFEssence.values()) {
				if (((EventTarget) document.getElementById("ESSENCE_" + essence.hashCode())) != null) {
					addEventListener(document, "ESSENCE_" + essence.hashCode(), "mousemove", moveTooltipListener, false);
					addEventListener(document, "ESSENCE_" + essence.hashCode(), "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setEssence(essence);
					addEventListener(document, "ESSENCE_" + essence.hashCode(), "mouseenter", el2, false);
				}
				if (((EventTarget) document.getElementById("ESSENCE_COST_" + essence.hashCode())) != null) {
					addEventListener(document, "ESSENCE_COST_" + essence.hashCode(), "mousemove", moveTooltipListener, false);
					addEventListener(document, "ESSENCE_COST_" + essence.hashCode(), "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setEssence(essence);
					addEventListener(document, "ESSENCE_COST_" + essence.hashCode(), "mouseenter", el2, false);
				}
			}
			
	
			// -------------------- Enchantments --------------------
			
			// Tooltips:
			if (((EventTarget) document.getElementById("MOD_PRIMARY_ENCHANTING")) != null) {

				EnchantmentEventListener el = new EnchantmentEventListener().setPrimaryModifier(TFModifier.NONE);
				addEventListener(document, "MOD_PRIMARY_ENCHANTING", "click", el, false);
				
				addEventListener(document, "MOD_PRIMARY_ENCHANTING", "mousemove", moveTooltipListener, false);
				addEventListener(document, "MOD_PRIMARY_ENCHANTING", "mouseleave", hideTooltipListener, false);
				
				InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFModifier(EnchantmentDialogue.primaryMod);
				addEventListener(document, "MOD_PRIMARY_ENCHANTING", "mouseenter", el2, false);
			}
			if (((EventTarget) document.getElementById("MOD_SECONDARY_ENCHANTING")) != null) {

				EnchantmentEventListener el = new EnchantmentEventListener().setSecondaryModifier(TFModifier.NONE);
				addEventListener(document, "MOD_SECONDARY_ENCHANTING", "click", el, false);
				
				addEventListener(document, "MOD_SECONDARY_ENCHANTING", "mousemove", moveTooltipListener, false);
				addEventListener(document, "MOD_SECONDARY_ENCHANTING", "mouseleave", hideTooltipListener, false);
				
				InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFModifier(EnchantmentDialogue.secondaryMod);
				addEventListener(document, "MOD_SECONDARY_ENCHANTING", "mouseenter", el2, false);
			}
			
			// Ingredient icon:
			if (((EventTarget) document.getElementById("INGREDIENT_ENCHANTING")) != null) {
				
				((EventTarget) document.getElementById("INGREDIENT_ENCHANTING")).addEventListener("click", e -> {
					Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.ITEM_INVENTORY){
						@Override
						public void effects() {
							EnchantmentDialogue.resetEnchantmentVariables();
						}
					});
				}, false);
				
				addEventListener(document, "INGREDIENT_ENCHANTING", "mousemove", moveTooltipListener, false);
				addEventListener(document, "INGREDIENT_ENCHANTING", "mouseleave", hideTooltipListener, false);
				
				InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem((AbstractItem) EnchantmentDialogue.ingredient, Main.game.getPlayer(), null);
				addEventListener(document, "INGREDIENT_ENCHANTING", "mouseenter", el2, false);
			}
			
			// Output icon:
			if (((EventTarget) document.getElementById("OUTPUT_ENCHANTING")) != null) {
				
				((EventTarget) document.getElementById("OUTPUT_ENCHANTING")).addEventListener("click", e -> {
					 if(EnchantmentDialogue.effects.isEmpty()) {
							
						} else if(EnchantmentDialogue.canAffordCost(EnchantmentDialogue.ingredient, EnchantmentDialogue.effects)) {
							Main.game.setContent(new ResponseEffectsOnly("Craft", "Craft '"+EnchantingUtils.getPotionName(EnchantmentDialogue.ingredient, EnchantmentDialogue.effects)+"'."){
								@Override
								public void effects() {
									EnchantmentDialogue.craftItem(EnchantmentDialogue.ingredient, EnchantmentDialogue.effects);
									
									if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY));
									}
									
									if(Main.game.getPlayer().hasItem((AbstractItem) EnchantmentDialogue.previousIngredient)) {
										EnchantmentDialogue.ingredient = EnchantmentDialogue.previousIngredient;
										Main.game.setContent(new Response("", "", EnchantmentDialogue.ENCHANTMENT_MENU));
									} else {
										Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
									}
									
								}
							});
							
						}
				}, false);
				
				addEventListener(document, "OUTPUT_ENCHANTING", "mousemove", moveTooltipListener, false);
				addEventListener(document, "OUTPUT_ENCHANTING", "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Craft", "Click to craft this item!");
				addEventListener(document, "OUTPUT_ENCHANTING", "mouseenter", el2, false);
			}
			
			// Adding an effect:
			if (((EventTarget) document.getElementById("ENCHANT_ADD_BUTTON")) != null) {
				
				((EventTarget) document.getElementById("ENCHANT_ADD_BUTTON")).addEventListener("click", e -> {
					if(EnchantmentDialogue.ingredient.getEnchantmentEffect().getEffectsDescription(
							EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod, EnchantmentDialogue.potency, EnchantmentDialogue.limit, Main.game.getPlayer(), Main.game.getPlayer())==null) {
						
					} else {
						Main.game.setContent(new Response("Add", "Add the effect.", EnchantmentDialogue.ENCHANTMENT_MENU){
							@Override
							public void effects() {
								EnchantmentDialogue.effects.add(new ItemEffect(
										EnchantmentDialogue.ingredient.getEnchantmentEffect(), EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod, EnchantmentDialogue.potency, EnchantmentDialogue.limit));
							}
						});
					}
				}, false);
				
				addEventListener(document, "ENCHANT_ADD_BUTTON", "mousemove", moveTooltipListener, false);
				addEventListener(document, "ENCHANT_ADD_BUTTON", "mouseleave", hideTooltipListener, false);
			}
			
			// Choosing a primary modifier:
			if(EnchantmentDialogue.ingredient != null) {
				for (TFModifier tfMod : EnchantmentDialogue.ingredient.getEnchantmentEffect().getPrimaryModifiers()) {
					if (((EventTarget) document.getElementById("MOD_PRIMARY_" + tfMod.hashCode())) != null) {
						
						EnchantmentEventListener el = new EnchantmentEventListener().setPrimaryModifier(tfMod);
						addEventListener(document, "MOD_PRIMARY_"+tfMod.hashCode(), "click", el, false);
						
						addEventListener(document, "MOD_PRIMARY_" + tfMod.hashCode(), "mousemove", moveTooltipListener, false);
						addEventListener(document, "MOD_PRIMARY_" + tfMod.hashCode(), "mouseleave", hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFModifier(tfMod);
						addEventListener(document, "MOD_PRIMARY_" + tfMod.hashCode(), "mouseenter", el2, false);
					}
				}
			}
			// Choosing a secondary modifier:
			if(EnchantmentDialogue.ingredient != null) {
				for (TFModifier tfMod : EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.primaryMod)) {
					if (((EventTarget) document.getElementById("MOD_SECONDARY_" + tfMod.hashCode())) != null) {
						
						EnchantmentEventListener el = new EnchantmentEventListener().setSecondaryModifier(tfMod);
						addEventListener(document, "MOD_SECONDARY_"+tfMod.hashCode(), "click", el, false);
						
						addEventListener(document, "MOD_SECONDARY_" + tfMod.hashCode(), "mousemove", moveTooltipListener, false);
						addEventListener(document, "MOD_SECONDARY_" + tfMod.hashCode(), "mouseleave", hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFModifier(tfMod);
						addEventListener(document, "MOD_SECONDARY_" + tfMod.hashCode(), "mouseenter", el2, false);
					}
				}
			}

			
			// -------------------- Room upgrades -------------------- //
			
			for(PlaceUpgrade placeUpgrade : PlaceUpgrade.values()) {
				id = placeUpgrade+"_BUY";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.game.getPlayer().getLocationPlace().addPlaceUpgrade(placeUpgrade);
								Main.game.getPlayer().incrementMoney(-placeUpgrade.getInstallCost());
							}
						});
					}, false);
				}
				
				id = placeUpgrade+"_REMOVE";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.game.getPlayer().getLocationPlace().removePlaceUpgrade(placeUpgrade);
								Main.game.getPlayer().incrementMoney(-placeUpgrade.getRemovalCost());
							}
						});
					}, false);
				}
			}

			
			// -------------------- Slavery -------------------- //
			
			id = "rename_room_button";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {

				boolean unsuitableName = false;
				 if(Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput')")!=null) {
					 
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
									|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32)
								unsuitableName = true;
							else {
								unsuitableName = false;
							}
						}
						
						if (!unsuitableName) {
							Main.game.setContent(new Response("Rename Room", "Rename this room to whatever you've entered in the text box.", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayerCell().getPlace().setName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
								}
							});
						}
					}
						
				}, false);
			}
			
			id = Main.game.getDialogueFlags().slaveryManagerSlaveSelected.getId()+"_RENAME";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {

					boolean unsuitableName = false;
				 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveNameInput')")!=null) {
					 
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveNameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
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
									Main.game.getDialogueFlags().slaveryManagerSlaveSelected.setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
								}
							});
						}
						
					}
						
				}, false);
			}
			
			id = Main.game.getDialogueFlags().slaveryManagerSlaveSelected.getId()+"_CALLS_PLAYER";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {

					boolean unsuitableName = false;
				 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')")!=null) {
					 
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
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
									Main.game.getDialogueFlags().slaveryManagerSlaveSelected.setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
								}
							});
						}
						
					}
						
				}, false);
			}
			
			id = "GLOBAL_CALLS_PLAYER";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {

					boolean unsuitableName = false;
				 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')")!=null) {
					 
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
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
									for(NPC slave : Main.game.getPlayer().getSlavesOwned()) {
										slave.setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								}
							});
						}
						
					}
						
				}, false);
			}
			
			for(NPC slave : Main.game.getPlayer().getSlavesOwned()) {
				id = slave.getId();
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", MiscDialogue.getSlaveryManagementDetailedDialogue(Main.game.getNPCById(slave.getId()))));
					}, false);
				}
				
				id = slave.getId()+"_TRANSFER";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								Main.game.getNPCById(slave.getId()).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
							}
						});
					}, false);
				}
				
				id = slave.getId()+"_SELL";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().slaveTrader.getBuyModifier()));
								Main.game.getDialogueFlags().slaveTrader.addSlave(Main.game.getNPCById(slave.getId()));
								Main.game.getNPCById(slave.getId()).setLocation(Main.game.getDialogueFlags().slaveTrader.getWorldLocation(), Main.game.getDialogueFlags().slaveTrader.getLocation());
							}
						});
					}, false);
				}
			}
			

			if(Main.game.getDialogueFlags().slaveTrader!=null)
			for(NPC slave : Main.game.getDialogueFlags().slaveTrader.getSlavesOwned()) {
				id = slave.getId();
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", MiscDialogue.getSlaveryManagementDetailedDialogue(Main.game.getNPCById(slave.getId()))));
					}, false);
				}
				
				id = slave.getId()+"_BUY";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(-(int)(slave.getValueAsSlave()*Main.game.getDialogueFlags().slaveTrader.getSellModifier()));
								Main.game.getPlayer().addSlave(Main.game.getNPCById(slave.getId()));
								Main.game.getNPCById(slave.getId()).setLocation(WorldType.SLAVER_ALLEY, SlaverAlley.SLAVERY_ADMINISTRATION);
							}
						});
					}, false);
				}
			}
			
			
			// -------------------- Cosmetics --------------------
			
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				id = bct+"_PRIMARY_GLOW_OFF";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct)) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
									
									Main.game.getPlayer().setSkinCovering(new Covering(
											bct,
											Main.game.getPlayer().getCovering(bct).getPattern(),
											Main.game.getPlayer().getCovering(bct).getPrimaryColour(),
											false,
											Main.game.getPlayer().getCovering(bct).getSecondaryColour(),
											Main.game.getPlayer().getCovering(bct).isSecondaryGlowing()), false);
									
								}
							});
						}
					}, false);
				}
				
				id = bct+"_PRIMARY_GLOW_ON";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct)) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
									
									Main.game.getPlayer().setSkinCovering(new Covering(
											bct,
											Main.game.getPlayer().getCovering(bct).getPattern(),
											Main.game.getPlayer().getCovering(bct).getPrimaryColour(),
											true,
											Main.game.getPlayer().getCovering(bct).getSecondaryColour(),
											Main.game.getPlayer().getCovering(bct).isSecondaryGlowing()), false);
									
								}
							});
						}
					}, false);
				}
				
				id = bct+"_SECONDARY_GLOW_OFF";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct)) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
									
									Main.game.getPlayer().setSkinCovering(new Covering(
											bct,
											Main.game.getPlayer().getCovering(bct).getPattern(),
											Main.game.getPlayer().getCovering(bct).getPrimaryColour(),
											Main.game.getPlayer().getCovering(bct).isPrimaryGlowing(),
											Main.game.getPlayer().getCovering(bct).getSecondaryColour(),
											false), false);
									
								}
							});
						}
					}, false);
				}
				
				id = bct+"_SECONDARY_GLOW_ON";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct)) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
									
									Main.game.getPlayer().setSkinCovering(new Covering(
											bct,
											Main.game.getPlayer().getCovering(bct).getPattern(),
											Main.game.getPlayer().getCovering(bct).getPrimaryColour(),
											Main.game.getPlayer().getCovering(bct).isPrimaryGlowing(),
											Main.game.getPlayer().getCovering(bct).getSecondaryColour(),
											true), false);
									
								}
							});
						}
					}, false);
				}
				
				for(CoveringPattern pattern : CoveringPattern.values()) {
					id = bct+"_PATTERN_"+pattern;
					
					if (((EventTarget) document.getElementById(id)) != null) {
						
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct)) {
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
										
										Main.game.getPlayer().setSkinCovering(new Covering(
												bct,
												pattern,
												Main.game.getPlayer().getCovering(bct).getPrimaryColour(),
												Main.game.getPlayer().getCovering(bct).isPrimaryGlowing(),
												Main.game.getPlayer().getCovering(bct).getSecondaryColour(),
												Main.game.getPlayer().getCovering(bct).isSecondaryGlowing()), false);
										
									}
								});
							}
						}, false);
					}
				}
				
				for(Colour colour : bct.getAllColours()) {
					id = bct+"_PRIMARY_"+colour;
					
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct)) {
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
										
										Main.game.getPlayer().setSkinCovering(new Covering(
												bct,
												Main.game.getPlayer().getCovering(bct).getPattern(),
												colour,
												(colour == Colour.COVERING_NONE ? false : Main.game.getPlayer().getCovering(bct).isPrimaryGlowing()),
												Main.game.getPlayer().getCovering(bct).getSecondaryColour(),
												Main.game.getPlayer().getCovering(bct).isSecondaryGlowing()), false);
										
									}
								});
							}
						}, false);
					}
					
					id = bct+"_SECONDARY_"+colour;
					
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct)) {
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
										
										Main.game.getPlayer().setSkinCovering(new Covering(
												bct,
												Main.game.getPlayer().getCovering(bct).getPattern(),
												Main.game.getPlayer().getCovering(bct).getPrimaryColour(),
												Main.game.getPlayer().getCovering(bct).isPrimaryGlowing(),
												colour,
												(colour == Colour.COVERING_NONE ? false : Main.game.getPlayer().getCovering(bct).isSecondaryGlowing())), false);
										
									}
								});
							}
						}, false);
					}
				}
			}
			
			for(HairLength hairLength : HairLength.values()) {
				id = "HAIR_LENGTH_"+hairLength;
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_HAIR_LENGTH_COST) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_LENGTH_COST);
									
									Main.game.getPlayer().setHairLength(hairLength.getMedianValue());
								}
							});
						}
					}, false);
				}
			}
			
			for(HairStyle hairStyle: HairStyle.values()) {
				id = "HAIR_STYLE_"+hairStyle;
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_HAIR_STYLE_COST) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_STYLE_COST);
									
									Main.game.getPlayer().setHairStyle(hairStyle);
								}
							});
						}
					}, false);
				}
			}
			
			for(PiercingType piercingType : PiercingType.values()) {
				id = piercingType+"_PIERCE_REMOVE";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getPiercingCost(piercingType)) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
									
									switch(piercingType) {
										case EAR:
											Main.game.getPlayer().setPiercedEar(false);
											break;
										case LIP:
											Main.game.getPlayer().setPiercedLip(false);
											break;
										case NAVEL:
											Main.game.getPlayer().setPiercedNavel(false);
											break;
										case NIPPLE:
											Main.game.getPlayer().setPiercedNipples(false);
											break;
										case NOSE:
											Main.game.getPlayer().setPiercedNose(false);
											break;
										case PENIS:
											Main.game.getPlayer().setPiercedPenis(false);
											break;
										case TONGUE:
											Main.game.getPlayer().setPiercedTongue(false);
											break;
										case VAGINA:
											Main.game.getPlayer().setPiercedVagina(false);
											break;
									}
								}
							});
						}
					}, false);
				}
				
				id = piercingType+"_PIERCE";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getPiercingCost(piercingType)) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
									
									switch(piercingType) {
										case EAR:
											Main.game.getPlayer().setPiercedEar(true);
											break;
										case LIP:
											Main.game.getPlayer().setPiercedLip(true);
											break;
										case NAVEL:
											Main.game.getPlayer().setPiercedNavel(true);
											break;
										case NIPPLE:
											Main.game.getPlayer().setPiercedNipples(true);
											break;
										case NOSE:
											Main.game.getPlayer().setPiercedNose(true);
											break;
										case PENIS:
											Main.game.getPlayer().setPiercedPenis(true);
											break;
										case TONGUE:
											Main.game.getPlayer().setPiercedTongue(true);
											break;
										case VAGINA:
											Main.game.getPlayer().setPiercedVagina(true);
											break;
									}
								}
							});
						}
					}, false);
				}
			}
			
			if (((EventTarget) document.getElementById("BLEACHING_OFF")) != null) {
				
				((EventTarget) document.getElementById("BLEACHING_OFF")).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_ANAL_BLEACHING_COST) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
								Main.game.getPlayer().setAssBleached(false);
							}
						});
					}
				}, false);
			}
			
			if (((EventTarget) document.getElementById("BLEACHING_ON")) != null) {
				
				((EventTarget) document.getElementById("BLEACHING_ON")).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_ANAL_BLEACHING_COST) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
								Main.game.getPlayer().setAssBleached(true);
							}
						});
					}
				}, false);
			}
			
			for(BodyHair bodyHair: BodyHair.values()) {
				
				id = "ASS_HAIR_"+bodyHair;
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									Main.game.getPlayer().setAssHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "UNDERARM_HAIR_"+bodyHair;
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									Main.game.getPlayer().setUnderarmHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "PUBIC_HAIR_"+bodyHair;
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									Main.game.getPlayer().setPubicHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "FACIAL_HAIR_"+bodyHair;
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									Main.game.getPlayer().setFacialHair(bodyHair);
								}
							});
						}
					}, false);
				}
			}
			
			
			// -------------------- Phone listeners -------------------- // TODO track listeners
			
			// Phone item viewer:
			for (AbstractClothingType clothing : ClothingType.getAllClothing())
				for (Colour c : clothing.getAvailableColours()) {
					if ((EventTarget) document.getElementById(clothing.hashCode() + "_" + c.toString()) != null) {
						addEventListener(document, clothing.hashCode() + "_" + c.toString(), "mousemove", moveTooltipListener, false);
						addEventListener(document, clothing.hashCode() + "_" + c.toString(), "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericClothing(clothing, c);
						addEventListener(document, clothing.hashCode() + "_" + c.toString(), "mouseenter", el2, false);
					}
				}
			for (AbstractWeaponType weapon : WeaponType.allweapons)
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					if ((EventTarget) document.getElementById(weapon.hashCode() + "_" + dt.toString()) != null) {
						addEventListener(document, weapon.hashCode() + "_" + dt.toString(), "mousemove", moveTooltipListener, false);
						addEventListener(document, weapon.hashCode() + "_" + dt.toString(), "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericWeapon(weapon, dt);
						addEventListener(document, weapon.hashCode() + "_" + dt.toString(), "mouseenter", el2, false);
					}
				}
	
			// Level up dialogue:
			if (((EventTarget) document.getElementById("strength-increase")) != null)
				addEventListener(document, "strength-increase", "click", new LevelUpButtonsEventListener().increaseStrength(), false);
			if (((EventTarget) document.getElementById("strength-decrease")) != null)
				addEventListener(document, "strength-decrease", "click", new LevelUpButtonsEventListener().decreaseStrength(), false);
	
			if (((EventTarget) document.getElementById("intelligence-increase")) != null)
				addEventListener(document, "intelligence-increase", "click", new LevelUpButtonsEventListener().increaseIntelligence(), false);
			if (((EventTarget) document.getElementById("intelligence-decrease")) != null)
				addEventListener(document, "intelligence-decrease", "click", new LevelUpButtonsEventListener().decreaseIntelligence(), false);
	
			if (((EventTarget) document.getElementById("fitness-increase")) != null)
				addEventListener(document, "fitness-increase", "click", new LevelUpButtonsEventListener().increaseFitness(), false);
			if (((EventTarget) document.getElementById("fitness-decrease")) != null)
				addEventListener(document, "fitness-decrease", "click", new LevelUpButtonsEventListener().decreaseFitness(), false);
	
			for (Perk p : Perk.values()) {
				if (((EventTarget) document.getElementById("perkUnlock" + p)) != null) {
					addEventListener(document, "perkUnlock" + p, "mousemove", moveTooltipListener, false);
					addEventListener(document, "perkUnlock" + p, "mouseleave", hideTooltipListener, false);
					addEventListener(document, "perkUnlock" + p, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(p, Main.game.getPlayer()), false);
					addEventListener(document, "perkUnlock" + p, "click", new LevelUpButtonsEventListener().handlePerkPress(p), false);
				}
			}
			for (Fetish f : Fetish.values()) {
				if (((EventTarget) document.getElementById("fetishUnlock" + f)) != null) {
					addEventListener(document, "fetishUnlock" + f, "mousemove", moveTooltipListener, false);
					addEventListener(document, "fetishUnlock" + f, "mouseleave", hideTooltipListener, false);
					addEventListener(document, "fetishUnlock" + f, "mouseenter", new TooltipInformationEventListener().setFetish(f, Main.game.getPlayer()), false);
					addEventListener(document, "fetishUnlock" + f, "click", new LevelUpButtonsEventListener().handleFetishPress(f), false);
				}
			}
			
		}

		// Hotkey bindings:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.KEYBINDS) {
			for (KeyboardAction ka : KeyboardAction.values()) {
				if (((EventTarget) document.getElementById("primary_" + ka)) != null)
					((EventTarget) document.getElementById("primary_" + ka)).addEventListener("click", e -> {
						actionToBind = ka;
						primaryBinding = true;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("primaryClear_" + ka)) != null)
					((EventTarget) document.getElementById("primaryClear_" + ka)).addEventListener("click", e -> {
						Main.getProperties().hotkeyMapPrimary.put(ka, null);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

				if (((EventTarget) document.getElementById("secondary_" + ka)) != null)
					((EventTarget) document.getElementById("secondary_" + ka)).addEventListener("click", e -> {
						actionToBind = ka;
						primaryBinding = false;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("secondaryClear_" + ka)) != null)
					((EventTarget) document.getElementById("secondaryClear_" + ka)).addEventListener("click", e -> {
						Main.getProperties().hotkeyMapSecondary.put(ka, null);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

			}
		}
		
		// Gender preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.GENDER_PREFERENCE) {
			for (Gender g : Gender.values()) {
				if (((EventTarget) document.getElementById("gender_preference_off_"+g)) != null)
					((EventTarget) document.getElementById("gender_preference_off_"+g)).addEventListener("click", e -> {
						Main.getProperties().genderPreferencesMap.put(g, GenderPreference.NONE.getValue());
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("gender_preference_low_"+g)) != null)
					((EventTarget) document.getElementById("gender_preference_low_"+g)).addEventListener("click", e -> {
						Main.getProperties().genderPreferencesMap.put(g, GenderPreference.LOW.getValue());
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("gender_preference_normal_"+g)) != null)
					((EventTarget) document.getElementById("gender_preference_normal_"+g)).addEventListener("click", e -> {
						Main.getProperties().genderPreferencesMap.put(g, GenderPreference.NORMAL.getValue());
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("gender_preference_high_"+g)) != null)
					((EventTarget) document.getElementById("gender_preference_high_"+g)).addEventListener("click", e -> {
						Main.getProperties().genderPreferencesMap.put(g, GenderPreference.HIGH.getValue());
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
			}
		}
		
		// Furry preferences:
		// Human encounter rates:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.FURRY_PREFERENCE) {
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_zero")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_zero")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=0;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_one")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_one")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=1;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_two")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_two")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=2;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_three")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_three")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=3;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_four")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_four")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=4;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			// Multi-breast options:
			if (((EventTarget) document.getElementById("furry_preference_multi_breast_zero")) != null) {
				((EventTarget) document.getElementById("furry_preference_multi_breast_zero")).addEventListener("click", e -> {
					Main.getProperties().multiBreasts=0;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, "furry_preference_multi_breast_zero", "mousemove", moveTooltipListener, false);
				addEventListener(document, "furry_preference_multi_breast_zero", "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Off", "Randomly-generated NPCs will never spawn in with multiple rows of breasts.");
				addEventListener(document, "furry_preference_multi_breast_zero", "mouseenter", el, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_multi_breast_one")) != null) {
				((EventTarget) document.getElementById("furry_preference_multi_breast_one")).addEventListener("click", e -> {
					Main.getProperties().multiBreasts=1;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, "furry_preference_multi_breast_one", "mousemove", moveTooltipListener, false);
				addEventListener(document, "furry_preference_multi_breast_one", "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Furry-only", "Randomly-generated NPCs will only spawn in with multiple rows of breasts if they have furry skin.");
				addEventListener(document, "furry_preference_multi_breast_one", "mouseenter", el, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_multi_breast_two")) != null) {
				((EventTarget) document.getElementById("furry_preference_multi_breast_two")).addEventListener("click", e -> {
					Main.getProperties().multiBreasts=2;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, "furry_preference_multi_breast_two", "mousemove", moveTooltipListener, false);
				addEventListener(document, "furry_preference_multi_breast_two", "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("On", "Randomly-generated NPCs will spawn in with multiple rows of breasts if their breast type is furry (starts at 'Minor morph' level).");
				addEventListener(document, "furry_preference_multi_breast_two", "mouseenter", el, false);
			}
			
			// Race preferences:
			if (((EventTarget) document.getElementById("furry_preference_female_human_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_human_all")).addEventListener("click", e -> {
					for (Race r : Race.values()) {
						if(r.isAffectedByFurryPreference()) {
							Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.HUMAN);
							Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.HUMAN);
						}
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_female_minimum_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_minimum_all")).addEventListener("click", e -> {
					for (Race r : Race.values()) {
						if(r.isAffectedByFurryPreference()) {
							Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.MINIMUM);
							Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.MINIMUM);
						}
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_female_reduced_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_reduced_all")).addEventListener("click", e -> {
					for (Race r : Race.values()) {
						if(r.isAffectedByFurryPreference()) {
							Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.REDUCED);
							Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.REDUCED);
						}
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_female_normal_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_normal_all")).addEventListener("click", e -> {
					for (Race r : Race.values()) {
						if(r.isAffectedByFurryPreference()) {
							Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.NORMAL);
							Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.NORMAL);
						}
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_female_maximum_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_maximum_all")).addEventListener("click", e -> {
					for (Race r : Race.values()) {
						if(r.isAffectedByFurryPreference()) {
							Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.MAXIMUM);
							Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.MAXIMUM);
						}
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			for (Race r : Race.values()) {
				if (((EventTarget) document.getElementById("furry_preference_female_human_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_female_human_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.HUMAN);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("furry_preference_female_minimum_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_female_minimum_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.MINIMUM);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("furry_preference_female_reduced_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_female_reduced_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.REDUCED);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("furry_preference_female_normal_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_female_normal_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.NORMAL);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("furry_preference_female_maximum_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_female_maximum_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceFemininePreferencesMap.put(r, FurryPreference.MAXIMUM);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				
				if (((EventTarget) document.getElementById("furry_preference_male_human_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_male_human_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.HUMAN);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("furry_preference_male_minimum_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_male_minimum_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.MINIMUM);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("furry_preference_male_reduced_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_male_reduced_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.REDUCED);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("furry_preference_male_normal_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_male_normal_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.NORMAL);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) document.getElementById("furry_preference_male_maximum_"+r)) != null)
					((EventTarget) document.getElementById("furry_preference_male_maximum_"+r)).addEventListener("click", e -> {
						Main.getProperties().raceMasculinePreferencesMap.put(r, FurryPreference.MAXIMUM);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
			}
		}
		
		// Save/load:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD && !Main.game.isInCombat() && !Main.game.isInSex()) {
			for (File f : Main.getSavedGames()) {
				if (((EventTarget) document.getElementById("overwrite_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )) != null) {
					((EventTarget) document.getElementById("overwrite_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )).addEventListener("click", e -> {
						
						if(!Main.getProperties().overwriteWarning || OptionsDialogue.overwriteConfirmationName.equals(f.getName())) {
							OptionsDialogue.overwriteConfirmationName = "";
							Main.saveGame(f.getName().substring(0, f.getName().lastIndexOf('.')), true);
						} else {
							OptionsDialogue.overwriteConfirmationName = f.getName();
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);
				}
				if (((EventTarget) document.getElementById("load_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )) != null) {
					((EventTarget) document.getElementById("load_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )).addEventListener("click", e -> {
						
						if(!Main.getProperties().overwriteWarning || OptionsDialogue.loadConfirmationName.equals(f.getName())) {
							OptionsDialogue.loadConfirmationName = "";
							Main.loadGame(f.getName().substring(0, f.getName().lastIndexOf('.')));
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = f.getName();
							OptionsDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);
				}
				if (((EventTarget) document.getElementById("delete_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )) != null) {
					((EventTarget) document.getElementById("delete_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )).addEventListener("click", e -> {
						
						if(!Main.getProperties().overwriteWarning || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
							OptionsDialogue.deleteConfirmationName = "";
							Main.deleteGame(f.getName().substring(0, f.getName().lastIndexOf('.')));
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);
				}
			}
			if (((EventTarget) document.getElementById("new_saved")) != null) {
				((EventTarget) document.getElementById("new_saved")).addEventListener("click", e -> {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
					Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
					
				}, false);
			}
		}
		
		// Import:
		if (Main.game.getCurrentDialogueNode() == CharacterCreation.IMPORT_CHOOSE) {
			for (File f : Main.getCharactersForImport()) {
				if (((EventTarget) document.getElementById("character_import_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )) != null) {
					((EventTarget) document.getElementById("character_import_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )).addEventListener("click", e -> {
						Main.importCharacter(f);
						
					}, false);
				}
			}
		}
	}
	
	private void manageResponseListeners() {
		documentResponse = (Document) webEngineResponse.executeScript("document");
		EventListenerDataMap.put(documentResponse, new ArrayList<>());

		for (int i = 0; i < RESPONSE_COUNT; i++) {
			if (((EventTarget) documentResponse.getElementById("option_" + i)) != null) {
				addEventListener(documentResponse, "option_" + i, "click", setContentListeners[i], false);
				addEventListener(documentResponse, "option_" + i, "mousemove", responseTooltipListener, false);
				addEventListener(documentResponse, "option_" + i, "mouseleave", hideTooltipListener, false);
				addEventListener(documentResponse, "option_" + i, "mouseenter", responseDescriptionListeners[i], false);
			}
		}
		addEventListener(documentResponse, "switch_right", "click", nextResponsePageListener, false);
		addEventListener(documentResponse, "switch_left", "click", previousResponsePageListener, false);
	}
	
	private void manageButtonListeners() {
		documentButtons = (Document) webEngineButtons.executeScript("document");
		EventListenerDataMap.put(documentButtons, new ArrayList<>());
		
		if(((EventTarget) documentButtons.getElementById("mainMenu"))!=null)
			addEventListener(documentButtons, "mainMenu", "click", menuButtonListener, true);
		if(((EventTarget) documentButtons.getElementById("inventory"))!=null)
			addEventListener(documentButtons, "inventory", "click", inventoryButtonListener, true);
		if(((EventTarget) documentButtons.getElementById("journal"))!=null)
			addEventListener(documentButtons, "journal", "click", journalButtonListener, true);
		if(((EventTarget) documentButtons.getElementById("charactersPresent"))!=null)
			addEventListener(documentButtons, "charactersPresent", "click", charactersPresentButtonListener, true);
		if(((EventTarget) documentButtons.getElementById("mapZoom"))!=null)
			addEventListener(documentButtons, "mapZoom", "click", zoomButtonListener, true);
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
		
		Attribute[] attributes = {
				Attribute.HEALTH_MAXIMUM,
				Attribute.MANA_MAXIMUM,
				Attribute.STAMINA_MAXIMUM,
				Attribute.EXPERIENCE,
				Attribute.STRENGTH,
				Attribute.INTELLIGENCE,
				Attribute.CORRUPTION,
				Attribute.FITNESS,
				Attribute.AROUSAL };
		for (Attribute a : attributes) {
			if (((EventTarget) documentAttributes.getElementById("PLAYER_"+a.getName())) != null) {
				addEventListener(documentAttributes, "PLAYER_"+a.getName(), "mousemove", moveTooltipListener, false);
				addEventListener(documentAttributes, "PLAYER_"+a.getName(), "mouseleave", hideTooltipListener, false);

				TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(a, Main.game.getPlayer());
				addEventListener(documentAttributes, "PLAYER_"+a.getName(), "mouseenter", el, false);
				
				if(a==Attribute.EXPERIENCE) {
					((EventTarget) documentAttributes.getElementById("PLAYER_"+a.getName())).addEventListener("click", e -> {
						
						//TODO block when in character creation
						
						if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.PHONE) {
							if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_LEVEL_UP) {
								openPhone();
							} else {
								Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_LEVEL_UP){
									@Override
									public void effects() {
										PhoneDialogue.strengthPoints = 0;
										PhoneDialogue.intelligencePoints = 0;
										PhoneDialogue.fitnessPoints = 0;
										PhoneDialogue.spendingPoints = Main.game.getPlayer().getPerkPoints();
										PhoneDialogue.levelUpPerks.clear();
									}
								});
							}
							
						} else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
							if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL)
								Main.game.saveDialogueNode();

							Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_LEVEL_UP){
								@Override
								public void effects() {
									PhoneDialogue.strengthPoints = 0;
									PhoneDialogue.intelligencePoints = 0;
									PhoneDialogue.fitnessPoints = 0;
									PhoneDialogue.spendingPoints = Main.game.getPlayer().getPerkPoints();
									PhoneDialogue.levelUpPerks.clear();
								}
							});
						}
						
					}, false);
				}
			}
			if (((EventTarget) documentAttributes.getElementById("PARTNER_"+a.getName())) != null) {
				addEventListener(documentAttributes, "PARTNER_"+a.getName(), "mousemove", moveTooltipListener, false);
				addEventListener(documentAttributes, "PARTNER_"+a.getName(), "mouseleave", hideTooltipListener, false);

				TooltipInformationEventListener el = new TooltipInformationEventListener().setAttribute(a, Sex.getPartner());
				addEventListener(documentAttributes, "PARTNER_"+a.getName(), "mouseenter", el, false);
			}
		}
		
		// Extra attribute info:
		if(((EventTarget) documentAttributes.getElementById("EXTRA_ATTRIBUTES"))!=null){
			addEventListener(documentAttributes, "EXTRA_ATTRIBUTES", "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, "EXTRA_ATTRIBUTES", "mouseleave", hideTooltipListener, false);

			TooltipInformationEventListener el = new TooltipInformationEventListener().setExtraAttributes();
			addEventListener(documentAttributes, "EXTRA_ATTRIBUTES", "mouseenter", el, false);
		}
		
		// For status effect slots:
		if(Main.game.getPlayer()!=null) {
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (((EventTarget) documentAttributes.getElementById("SE_PLAYER_" + se)) != null) {
					addEventListener(documentAttributes, "SE_PLAYER_" + se, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "SE_PLAYER_" + se, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, Main.game.getPlayer());
					addEventListener(documentAttributes, "SE_PLAYER_" + se, "mouseenter", el, false);
				}
			}
		}
		if(Main.game.isInSex()) {
			for (StatusEffect se : Sex.getPartner().getStatusEffects()) {
				if (((EventTarget) documentAttributes.getElementById("SE_PARTNER_" + se)) != null) {
					addEventListener(documentAttributes, "SE_PARTNER_" + se, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "SE_PARTNER_" + se, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, Sex.getPartner());
					addEventListener(documentAttributes, "SE_PARTNER_" + se, "mouseenter", el, false);
				}
			}
		}
		
		// For perk slots:
		if(Main.game.getPlayer()!=null) {
			for (PerkInterface p : Main.game.getPlayer().getPerks()) {
				if (((EventTarget) documentAttributes.getElementById("PERK_PLAYER_" + p)) != null) {
					addEventListener(documentAttributes, "PERK_PLAYER_" + p, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "PERK_PLAYER_" + p, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setPerk(p, Main.game.getPlayer());
					addEventListener(documentAttributes, "PERK_PLAYER_" + p, "mouseenter", el, false);
				}
			}
			for (Fetish f : Main.game.getPlayer().getFetishes()) {
				if (((EventTarget) documentAttributes.getElementById("FETISH_PLAYER_" + f)) != null) {
					addEventListener(documentAttributes, "FETISH_PLAYER_" + f, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "FETISH_PLAYER_" + f, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setFetish(f, Main.game.getPlayer());
					addEventListener(documentAttributes, "FETISH_PLAYER_" + f, "mouseenter", el, false);
				}
			}
		}
		if(Main.game.isInSex()) {
			for (PerkInterface p : Sex.getPartner().getPerks()) {
				if (((EventTarget) documentAttributes.getElementById("PERK_PARTNER_" + p)) != null) {
					addEventListener(documentAttributes, "PERK_PARTNER_" + p, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "PERK_PARTNER_" + p, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setPerk(p, Sex.getPartner());
					addEventListener(documentAttributes, "PERK_PARTNER_" + p, "mouseenter", el, false);
				}
			}
			for (Fetish f : Sex.getPartner().getFetishes()) {
				if (((EventTarget) documentAttributes.getElementById("FETISH_PARTNER_" + f)) != null) {
					addEventListener(documentAttributes, "FETISH_PARTNER_" + f, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "FETISH_PARTNER_" + f, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setFetish(f, Sex.getPartner());
					addEventListener(documentAttributes, "FETISH_PARTNER_" + f, "mouseenter", el, false);
				}
			}
		}
		if(Main.game.getPlayer()!=null)
			for (SpecialAttack sa : Main.game.getPlayer().getSpecialAttacks()) {
				if (((EventTarget) documentAttributes.getElementById("SA_" + sa)) != null) {
					addEventListener(documentAttributes, "SA_" + sa, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "SA_" + sa, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setSpecialAttack(sa, Main.game.getPlayer());
					addEventListener(documentAttributes, "SA_" + sa, "mouseenter", el, false);
				}
			}
		if(Main.game.getPlayer()!=null)
			if (Main.game.getPlayer().getMainWeapon() != null) {
				for (Spell s : Main.game.getPlayer().getMainWeapon().getSpells()) {
					if (((EventTarget) documentAttributes.getElementById("SPELL_MAIN_" + s)) != null) {
						addEventListener(documentAttributes, "SPELL_MAIN_" + s, "mousemove", moveTooltipListener, false);
						addEventListener(documentAttributes, "SPELL_MAIN_" + s, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el = new TooltipInformationEventListener().setSpell(s, Main.game.getPlayer().getLevel(), Main.game.getPlayer());
						addEventListener(documentAttributes, "SPELL_MAIN_" + s, "mouseenter", el, false);
					}
				}
			}
		if(Main.game.getPlayer()!=null)
			if (Main.game.getPlayer().getOffhandWeapon() != null) {
				for (Spell s : Main.game.getPlayer().getOffhandWeapon().getSpells()) {
					if (((EventTarget) documentAttributes.getElementById("SPELL_OFFHAND_" + s)) != null) {
						addEventListener(documentAttributes, "SPELL_OFFHAND_" + s, "mousemove", moveTooltipListener, false);
						addEventListener(documentAttributes, "SPELL_OFFHAND_" + s, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el = new TooltipInformationEventListener().setSpell(s, Main.game.getPlayer().getLevel(), Main.game.getPlayer());
						addEventListener(documentAttributes, "SPELL_OFFHAND_" + s, "mouseenter", el, false);
					}
				}
			}
	}
	
	
	private boolean useJavascriptToSetContent = true;
	
	private void setWebEngineContent(WebEngine engine, String content) {
		content=content.replaceAll("\r", "");
		content=content.replaceAll("\n", "");
		content=content.replaceAll("\"", "'");
		
		engine.executeScript(
			"document.open('text/html');"
			+ "document.write(\""+content+"\");"
			+"document.close();");
	}
	
	public void setMainContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(document);
			setWebEngineContent(webEngine, content);
			manageMainListeners();
		} else {
			webEngine.loadContent(content);
		}
	}
	
	public void setTooltipContent(String content) {
		if(useJavascriptToSetContent) {
			setWebEngineContent(webEngineTooltip, content);
		} else {
			webEngineTooltip.loadContent(content);
		}
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
	
	public void setButtonsContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentButtons);
			setWebEngineContent(webEngineButtons, content);
			manageButtonListeners();
		} else {
			webEngineButtons.loadContent(content);
		}
	}
	
	public void setResponsesContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentResponse);
			setWebEngineContent(webEngineResponse, content);
			manageResponseListeners();
		} else {
			webEngineResponse.loadContent(content);
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
			Main.game.setContent(new Response("", "", GenericDialogue.DEBUG_MENU));
		}
		if (lastKeysEqual(KeyCode.N, KeyCode.O, KeyCode.X, KeyCode.X, KeyCode.X)) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==ShoppingArcade.GENERIC_SHOP)
				Main.game.setContent(new Response("", "", TestNPC.TEST_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTestNPC().setLocation(WorldType.SHOPPING_ARCADE, Main.game.getPlayer().getLocation());
					}
				});
		}
	}

	private boolean lastKeysEqual(KeyCode one, KeyCode two, KeyCode three, KeyCode four, KeyCode five) {
		return lastKeys[0] == five && lastKeys[1] == four && lastKeys[2] == three && lastKeys[3] == two && lastKeys[4] == one;
	}

	/**
	 * Updates every element of the UI.
	 */
	public void updateUI() {
		if (Main.game.isRenderAttributesSection()) {
			RenderingEngine.ENGINE.renderAttributesPanel();
		}
		RenderingEngine.ENGINE.renderButtons();
	}

	public void zoomMap() {
		if (!Main.game.getCurrentDialogueNode().isTravelDisabled()) {
			RenderingEngine.setZoomedIn(!RenderingEngine.isZoomedIn());
			
			Main.game.reloadContent();
			RenderingEngine.ENGINE.renderButtons();
		}
	}

	/**
	 * Moves the player to the World that is linked to the supplied portal. If
	 * no world exists, a new world is generated.
	 * 
	 * @param forward
	 *            true if move to next world, false if move to previous world
	 */
	public void moveGameWorld(boolean setDefaultDialogue) {
		int timeToTranstition = Main.game.getActiveWorld().getWorldType().getTimeToTransition();
		
		Main.game.setActiveWorld(setDefaultDialogue);
		
		Main.game.endTurn(timeToTranstition + Main.game.getActiveWorld().getWorldType().getTimeToTransition());
	}

	/**
	 * Moves the player North.
	 */
	public void moveNorth() {
		if (Main.game.getPlayer().getLocation().getY() + 1 < Main.game.getActiveWorld().WORLD_HEIGHT) {
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() + 1).getPlace().getPlaceType() != GenericPlaces.IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear())
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory();
				Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() + 1));
				DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
				Main.game.setContent(new Response("", "", dn));
			}
		}
	}

	/**
	 * Moves the player South.
	 */
	public void moveSouth() {
		if (Main.game.getPlayer().getLocation().getY() - 1 >= 0) {
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() - 1).getPlace().getPlaceType() != GenericPlaces.IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear())
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory();
				Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() - 1));
				DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
				Main.game.setContent(new Response("", "", dn));
			}
		}
	}

	/**
	 * Moves the player East.
	 */
	public void moveEast() {
		if (Main.game.getPlayer().getLocation().getX() + 1 < Main.game.getActiveWorld().WORLD_WIDTH) {
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() + 1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType() != GenericPlaces.IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear())
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory();
				Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX() + 1, Main.game.getPlayer().getLocation().getY()));
				DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
				Main.game.setContent(new Response("", "", dn));
			}
		}
	}

	/**
	 * Moves the player West.
	 */
	public void moveWest() {
		if (Main.game.getPlayer().getLocation().getX() - 1 >= 0) {
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() - 1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType() != GenericPlaces.IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear())
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory();
				Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX() - 1, Main.game.getPlayer().getLocation().getY()));
				DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
				Main.game.setContent(new Response("", "", dn));
			}
		}
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

	public WebEngine getWebEngineResponse() {
		return webEngineResponse;
	}

	public WebEngine getWebEngineTooltip() {
		return webEngineTooltip;
	}
	public WebEngine getWebEngineAttributes() {
		return webEngineAttributes;
	}

	public WebEngine getWebEngineButtons() {
		return webEngineButtons;
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
		if (Main.getProperties().lightTheme) {
			Main.mainController.getWebEngineTooltip().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewTooltip_stylesheet.css").toExternalForm());
			Main.mainController.getWebEngine().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webView_stylesheet.css").toExternalForm());
			Main.mainController.getWebEngineButtons().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewButtons_stylesheet.css").toExternalForm());
			Main.mainController.getWebEngineAttributes().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewAttributes_stylesheet.css").toExternalForm());
			Main.mainController.getWebEngineResponse().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewResponse_stylesheet.css").toExternalForm());
	
			Main.mainScene.getStylesheets().clear();
			Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
			Main.primaryStage.setScene(Main.mainScene);
		} else {
			Main.mainController.getWebEngineTooltip().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewTooltip_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngine().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webView_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineButtons().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineAttributes().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineResponse().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewResponse_stylesheet_light.css").toExternalForm());
	
			Main.mainScene.getStylesheets().clear();
			Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet_light.css");
			Main.primaryStage.setScene(Main.mainScene);
		}
		Main.getProperties().lightTheme = !Main.getProperties().lightTheme;
		Main.saveProperties();
	}

	public Colour getFlashMessageColour() {
		return flashMessageColour;
	}

	public void setFlashMessageColour(Colour flashMessageColour) {
		this.flashMessageColour = flashMessageColour;
	}

	public String getFlashMessageText() {
		return flashMessageText;
	}

	public void setFlashMessageText(String flashMessageText) {
		this.flashMessageText = flashMessageText;
	}

}
