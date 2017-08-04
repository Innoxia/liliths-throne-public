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
import com.base.game.KeyboardAction;
import com.base.game.character.CharacterChangeEventListener;
import com.base.game.character.GameCharacter;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.PerkInterface;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.gender.Gender;
import com.base.game.character.gender.GenderPreference;
import com.base.game.character.gender.GenderPronoun;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.Race;
import com.base.game.character.race.FurryPreference;
import com.base.game.combat.Combat;
import com.base.game.combat.DamageType;
import com.base.game.combat.SpecialAttack;
import com.base.game.combat.Spell;
import com.base.game.dialogue.GenericDialogue;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.places.dominion.CityHall;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.story.CharacterCreationDialogue;
import com.base.game.dialogue.utils.CharactersPresentDialogue;
import com.base.game.dialogue.utils.EnchantmentDialogue;
import com.base.game.dialogue.utils.InventoryDialogue;
import com.base.game.dialogue.utils.OptionsDialogue;
import com.base.game.dialogue.utils.PhoneDialogue;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.enchanting.EnchantingUtils;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.ItemEffect;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.inventory.weapon.WeaponType;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.rendering.RenderingEngine;
import com.base.utils.Colour;
import com.base.utils.Vector2i;
import com.base.world.places.GenericPlace;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * @since 0.1.0
 * @version 0.1.8
 * @author Innoxia
 */
public class MainController implements Initializable {

	// FXML elements:
	@FXML
	private GridPane mainPane;
	@FXML
	private ListView<AbstractCoreItem> listViewInventoryCell, listViewInventoryPlayer;
	@FXML
	private AnchorPane anchorPaneCanvas;
	@FXML
	private VBox vBoxLeft;

	// UI-related elements:
	@FXML
	private WebView webViewMain, webViewAttributes, webViewInventory, webViewMap, webViewMapTitle, webViewButtons, webViewResponse;

	private WebEngine webEngine, webEngineTooltip, webEngineAttributes, webEngineInventory, webEngineMap, webEngineMapTitle, webEngineButtons, webEngineResponse;
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

		webViewInventory.setVisible(false);

		vBoxLeft.getStyleClass().add("vbox");

		// Set up controls and buttons:
		setUpButtons();

		// Set up webViews:
		setUpWebViews();

		anchorPaneCanvas.prefHeightProperty().bind(anchorPaneCanvas.widthProperty());

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
					renderMap();
				}
			}
		});

		GameCharacter.addPlayerInventoryChangeEventListener(new CharacterChangeEventListener() {
			@Override
			public void onChange() {
				if (RenderingEngine.ENGINE.getCharactersInventoryToRender() != null)
					if (RenderingEngine.ENGINE.getCharactersInventoryToRender().isPlayer())
						RenderingEngine.ENGINE.renderInventory();
			}
		});

		GameCharacter.addNPCInventoryChangeEventListener(new CharacterChangeEventListener() {
			@Override
			public void onChange() {
				if (RenderingEngine.ENGINE.getCharactersInventoryToRender() != null)
					if ((Main.game.isInCombat() && RenderingEngine.ENGINE.getCharactersInventoryToRender() == Combat.getOpponent()) || (Main.game.isInSex() && RenderingEngine.ENGINE.getCharactersInventoryToRender() == Sex.getPartner()))
						RenderingEngine.ENGINE.renderInventory();
			}
		});
		
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
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.INVENTORY || Main.game.isInCombat() || Main.game.isInSex())
			return false;
		
		else if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.OPTIONS || Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.PHONE)
			return Main.game.getSavedDialogueNode().isInventoryDisabled();
		
		else
			return Main.game.getCurrentDialogueNode().isInventoryDisabled();
	}

	public void openInventory() {
		openInventory(null);
	}

	public void openInventory(NPC tradePartner) {
		if(!Main.game.isStarted())
			return;
		
		Main.game.getDialogueFlags().tradePartner = (tradePartner);
		InventoryDialogue.setBuyback(false);

		if (Main.game.isInCombat()) {
			if (RenderingEngine.ENGINE.getCharactersInventoryToRender() == Main.game.getPlayer())
				RenderingEngine.ENGINE.setCharactersInventoryToRender(Combat.getOpponent());
			else
				RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getPlayer());

		} else if (Main.game.isInSex()) {
			if (RenderingEngine.ENGINE.getCharactersInventoryToRender() == Main.game.getPlayer())
				RenderingEngine.ENGINE.setCharactersInventoryToRender(Sex.getPartner());
			else
				RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getPlayer());

		} else if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.INVENTORY) {
			Main.game.restoreSavedContent();

		} else if (!isInventoryDisabled() || tradePartner != null) {
			RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getPlayer());
			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL)
				Main.game.saveDialogueNode();

			InventoryDialogue.populateJinxedClothingList();
			Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
		}

		RenderingEngine.ENGINE.renderMapTitle();
		RenderingEngine.ENGINE.renderInventory();
		// processNewDialogue();
	}

	public void openCharactersPresent() {
		if(!Main.game.isStarted())
			return;
		
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.CHARACTERS_PRESENT) {
			RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getPlayer());
			Main.game.restoreSavedContent();
			
		} else if (!Main.game.getCharactersPresent().isEmpty()) {

			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL || Main.game.isInCombat())
				Main.game.saveDialogueNode();

			CharactersPresentDialogue.resetContent();
			Main.game.setContent(new Response("", "", CharactersPresentDialogue.MENU));
		}

		RenderingEngine.ENGINE.renderInventory();
		// processNewDialogue();
	}

	/**
	 * Sets up buttons and hotkeys.
	 */
	private List<KeyCode> buttonsPressed = new ArrayList<>();

	private void setUpButtons() {
		// HOTKEYS:
		actionKeyPressed = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (allowInput) {
					// Hotkey bindings:
					if (Main.game.getCurrentDialogueNode() == OptionsDialogue.KEYBINDS) {
						if (actionToBind != null) {
							// System.out.println(event.getCode().getName()+"
							// "+actionToBind);

							for (Entry<KeyboardAction, KeyCode> entry : Main.getProperties().hotkeyMapPrimary.entrySet())
								if (entry.getValue() == event.getCode()) {
									actionToBind = null;
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>The key '" + event.getCode().getName()
											+ "' is already the primary bind for the action '" + entry.getKey().getName() + "'!</b>" + "</p>");
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									return;
								}
							for (Entry<KeyboardAction, KeyCode> entry : Main.getProperties().hotkeyMapSecondary.entrySet())
								if (entry.getValue() == event.getCode()) {
									actionToBind = null;
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>The key '" + event.getCode().getName()
											+ "' is already the secondary bind for the action '" + entry.getKey().getName() + "'!</b>" + "</p>");
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									return;
								}

							if (primaryBinding)
								Main.getProperties().hotkeyMapPrimary.put(actionToBind, event.getCode());
							else
								Main.getProperties().hotkeyMapSecondary.put(actionToBind, event.getCode());
							actionToBind = null;
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							return;
						}
					} else
						actionToBind = null;

					if (!buttonsPressed.contains(event.getCode())) {
						buttonsPressed.add(event.getCode());

						for (int i = 4; i > 0; i--)
							lastKeys[i] = lastKeys[i - 1];
						lastKeys[0] = event.getCode();
						checkLastKeys();

//						 System.out.println(event.getCode());
						 if(event.getCode()==KeyCode.END){
							 
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
//							for (ItemType ct : ItemType.availableItems) {
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
						 }
						 
//						 if(event.getCode()==KeyCode.DELETE){
//							 for(Fetish fetish : Fetish.values()) {
//								 Main.game.getPlayer().addFetish(fetish);
//								 Sex.getPartner().addFetish(fetish);
//							 }
//						 }
//						 System.out.println(event.getCode());
						 

						// Escape Menu:
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MENU) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.MENU) == event.getCode())
							openOptions();

						// Movement:
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_NORTH) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.MOVE_NORTH) == event.getCode()) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled() && !event.isControlDown()) {
								moveNorth();
							} else {
								Main.game.responseNavigationUp();
							}
						}
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_WEST) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.MOVE_WEST) == event.getCode()) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled() && !event.isControlDown()) {
								moveWest();
							} else {
								Main.game.responseNavigationLeft();
							}
						}
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_SOUTH) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.MOVE_SOUTH) == event.getCode()) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled() && !event.isControlDown()) {
								moveSouth();
							} else {
								Main.game.responseNavigationDown();
							}
						}
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_EAST) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.MOVE_EAST) == event.getCode()) {
							if (!Main.game.getCurrentDialogueNode().isTravelDisabled() && !event.isControlDown()) {
								moveEast();
							} else {
								Main.game.responseNavigationRight();
							}
						}

						// Game stuff:
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.QUICKSAVE) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.QUICKSAVE) == event.getCode()) {
							Main.quickSaveGame();
						}
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.QUICKLOAD) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.QUICKLOAD) == event.getCode()) {
							Main.quickLoadGame();
						}
						
						boolean allowInput = true;
						
						if(Main.game.getCurrentDialogueNode() == CharacterCreationDialogue.CHOOSE_NAME){
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
							if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.INVENTORY) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.INVENTORY) == event.getCode())
								openInventory();
							if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.JOURNAL) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.JOURNAL) == event.getCode())
								openPhone();
							if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.CHARACTERS) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.CHARACTERS) == event.getCode())
								openCharactersPresent();
							if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.ZOOM) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.ZOOM) == event.getCode())
								zoomMap();
	
							if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.SCROLL_UP) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.SCROLL_UP) == event.getCode())
								Main.mainController.getWebEngine().executeScript("document.getElementById('main-content').scrollTop -= 50");
							if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.SCROLL_DOWN) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.SCROLL_DOWN) == event.getCode())
								Main.mainController.getWebEngine().executeScript("document.getElementById('main-content').scrollTop += 50");
							
						
							// Responses:
							if(!event.isControlDown()) {
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_1) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_1) == event.getCode())
									processResponse(1);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_2) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_2) == event.getCode())
									processResponse(2);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_3) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_3) == event.getCode())
									processResponse(3);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_4) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_4) == event.getCode())
									processResponse(4);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_5) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_5) == event.getCode())
									processResponse(5);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_6) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_6) == event.getCode())
									processResponse(6);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_7) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_7) == event.getCode())
									processResponse(7);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_8) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_8) == event.getCode())
									processResponse(8);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_9) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_9) == event.getCode())
									processResponse(9);
								if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_0) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_0) == event.getCode())
									processResponse(0);
								
								
							} else {
							
								if ((Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_1) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_1) == event.getCode()))
									processResponse(10);
								if ((Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_2) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_2) == event.getCode()))
									processResponse(11);
								if ((Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_3) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_3) == event.getCode()))
									processResponse(12);
								if ((Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_4) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_4) == event.getCode()))
									processResponse(13);
								if ((Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_5) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_5) == event.getCode()))
									processResponse(14);
								
								
							}
							
							if (event.getCode() == Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MENU_SELECT) || event.getCode() == Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.MENU_SELECT)) {
								Main.game.setContent(Main.game.getResponsePointer());
							}
							
						}
						
						// For name selection:
						if (event.getCode() == KeyCode.ENTER && Main.game.getCurrentDialogueNode() == CharacterCreationDialogue.CHOOSE_NAME) {
							Main.game.setContent(1);
						}

						// Next/Previous response page:
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_NEXT_PAGE) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_NEXT_PAGE) == event.getCode())
							if (Main.game.isHasNextResponsePage()) {
								Main.game.setResponsePage(Main.game.getResponsePage() + 1);
								Main.game.setResponses(Main.game.getCurrentDialogueNode());
							}
						if (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE) == event.getCode() || Main.getProperties().hotkeyMapSecondary.get(KeyboardAction.RESPOND_PREVIOUS_PAGE) == event.getCode())
							if (Main.game.getResponsePage() != 0) {
								Main.game.setResponsePage(Main.game.getResponsePage() - 1);
								Main.game.setResponses(Main.game.getCurrentDialogueNode());
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

		// Inventory WebView:
		webViewInventory.setContextMenuEnabled(false);
		webEngineInventory = webViewInventory.getEngine();
		webEngineInventory.getHistory().setMaxSize(0);
		
		if (Main.getProperties().lightTheme) {
			webEngineInventory.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewInventory_stylesheet_light.css").toExternalForm());
		} else {
			webEngineInventory.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewInventory_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
		webEngineInventory.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(documentInventory);
				manageInventoryListeners();
			}
		});

		// Map webView:
		webViewMap.setContextMenuEnabled(false);
		webEngineMap = webViewMap.getEngine();
		webEngineMap.getHistory().setMaxSize(0);
		
		if (Main.getProperties().lightTheme) {
			webEngineMap.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewMap_stylesheet_light.css").toExternalForm());
		} else {
			webEngineMap.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewMap_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners) {
			webEngineMap.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
				if (newState == State.SUCCEEDED) {
					unbindListeners(documentMap);
					manageMapListeners();
				}
			});
		}
		
		// Map title:
		webViewMapTitle.setContextMenuEnabled(false);
		webEngineMapTitle = webViewMapTitle.getEngine();
		webEngineMapTitle.getHistory().setMaxSize(0);
		
		if (Main.getProperties().lightTheme) {
			webEngineMapTitle.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewMap_stylesheet_light.css").toExternalForm());
		} else {
			webEngineMapTitle.setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewMap_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners) {
			webEngineMapTitle.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
				if (newState == State.SUCCEEDED) {
					unbindListeners(documentMapTitle);
					manageMapTitleListeners();
				}
			});
		}
	}
	
	private void manageMainListeners() {
		document = (Document) webEngine.executeScript("document");
		EventListenerDataMap.put(document, new ArrayList<>());
		
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

		// Weapons in inventory:
		if(Main.game.isStarted()) {
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet())
				if (((EventTarget) document.getElementById("WEAPON_" + entry.getKey().hashCode())) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), Main.game.getPlayer());
					addEventListener(document, "WEAPON_" + entry.getKey().hashCode(), "click", el, false);
					
					addEventListener(document, "WEAPON_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
					addEventListener(document, "WEAPON_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), Main.game.getPlayer(), null);
					addEventListener(document, "WEAPON_" + entry.getKey().hashCode(), "mouseenter", el2, false);
				}
			
			// Clothing in inventory:
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet())
				if (((EventTarget) document.getElementById("CLOTHING_" + entry.getKey().hashCode())) != null) {
	
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), Main.game.getPlayer());
					addEventListener(document, "CLOTHING_" + entry.getKey().hashCode(), "click", el, false);
					
					addEventListener(document, "CLOTHING_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
					addEventListener(document, "CLOTHING_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
					addEventListener(document, "CLOTHING_" + entry.getKey().hashCode(), "mouseenter", el2, false);
				}
			
			// Items in inventory:
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet())
				if (((EventTarget) document.getElementById("ITEM_" + entry.getKey().hashCode())) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), Main.game.getPlayer());
					addEventListener(document, "ITEM_" + entry.getKey().hashCode(), "click", el, false);
					
					addEventListener(document, "ITEM_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
					addEventListener(document, "ITEM_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
	
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
					addEventListener(document, "ITEM_" + entry.getKey().hashCode(), "mouseenter", el2, false);
				}
			
			// Weapons on floor:
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons().entrySet())
				if (((EventTarget) document.getElementById("WEAPON_FLOOR_" + entry.getKey().hashCode())) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponFloor(entry.getKey());
					addEventListener(document, "WEAPON_FLOOR_" + entry.getKey().hashCode(), "click", el, false);
					
					addEventListener(document, "WEAPON_FLOOR_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
					addEventListener(document, "WEAPON_FLOOR_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
	
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), null, null);
					addEventListener(document, "WEAPON_FLOOR_" + entry.getKey().hashCode(), "mouseenter", el2, false);
				}
			
			// Clothing on floor:
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet())
				if (((EventTarget) document.getElementById("CLOTHING_FLOOR_" + entry.getKey().hashCode())) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingFloor(entry.getKey());
					addEventListener(document, "CLOTHING_FLOOR_" + entry.getKey().hashCode(), "click", el, false);
					
					addEventListener(document, "CLOTHING_FLOOR_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
					addEventListener(document, "CLOTHING_FLOOR_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), null, null);
					addEventListener(document, "CLOTHING_FLOOR_" + entry.getKey().hashCode(), "mouseenter", el2, false);
				}
			
			// Items on floor:
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet())
				if (((EventTarget) document.getElementById("ITEM_FLOOR_" + entry.getKey().hashCode())) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemFloor(entry.getKey());
					addEventListener(document, "ITEM_FLOOR_" + entry.getKey().hashCode(), "click", el, false);
					
					addEventListener(document, "ITEM_FLOOR_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
					addEventListener(document, "ITEM_FLOOR_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
	
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), null, null);
					addEventListener(document, "ITEM_FLOOR_" + entry.getKey().hashCode(), "mouseenter", el2, false);	
				}
		
		
			if(Main.game.getDialogueFlags().tradePartner != null) {
				// Weapons owned by trader:
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getDialogueFlags().tradePartner.getMapOfDuplicateWeapons().entrySet())
					if (((EventTarget) document.getElementById("WEAPON_TRADER_" + entry.getKey().hashCode())) != null) {
	
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), Main.game.getDialogueFlags().tradePartner);
						addEventListener(document, "WEAPON_TRADER_" + entry.getKey().hashCode(), "click", el, false);
						
						addEventListener(document, "WEAPON_TRADER_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
						addEventListener(document, "WEAPON_TRADER_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 =  new InventoryTooltipEventListener().setWeapon(entry.getKey(), Main.game.getDialogueFlags().tradePartner, null);
						((EventTarget) document.getElementById("WEAPON_TRADER_" + entry.getKey().hashCode())).addEventListener("mouseenter",el2, false);
					}
				
				// Clothing owned by trader:
				for (Entry<AbstractClothing, Integer> entry : Main.game.getDialogueFlags().tradePartner.getMapOfDuplicateClothing().entrySet())
					if (((EventTarget) document.getElementById("CLOTHING_TRADER_" + entry.getKey().hashCode())) != null) {
	
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), Main.game.getDialogueFlags().tradePartner);
						addEventListener(document, "CLOTHING_TRADER_" + entry.getKey().hashCode(), "click", el, false);
						
						addEventListener(document, "CLOTHING_TRADER_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
						addEventListener(document, "CLOTHING_TRADER_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), Main.game.getDialogueFlags().tradePartner, null);
						addEventListener(document, "CLOTHING_TRADER_" + entry.getKey().hashCode(), "mouseenter", el2, false);
					}
				
				// Items owned by trader:
				for (Entry<AbstractItem, Integer> entry : Main.game.getDialogueFlags().tradePartner.getMapOfDuplicateItems().entrySet())
					if (((EventTarget) document.getElementById("ITEM_TRADER_" + entry.getKey().hashCode())) != null) {
						
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), Main.game.getDialogueFlags().tradePartner);
						addEventListener(document, "ITEM_TRADER_" + entry.getKey().hashCode(), "click", el, false);
						
						addEventListener(document, "ITEM_TRADER_" + entry.getKey().hashCode(), "mousemove", moveTooltipListener, false);
						addEventListener(document, "ITEM_TRADER_" + entry.getKey().hashCode(), "mouseleave", hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), Main.game.getDialogueFlags().tradePartner, null);
						addEventListener(document, "ITEM_TRADER_" + entry.getKey().hashCode(), "mouseenter", el2, false);
					}
				
				// Buyback panel:
				for (int i = Main.game.getPlayer().getBuybackStack().size() - 1; i >= 0; i--) {
					if (((EventTarget) document.getElementById("WEAPON_BUYBACK_" + i)) != null) {
		
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponBuyback(
								(AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), Main.game.getPlayer().getBuybackStack().get(i).getPrice(), i);
						((EventTarget) document.getElementById("WEAPON_BUYBACK_" + i)).addEventListener("click",el, false);
						
						addEventListener(document, "WEAPON_BUYBACK_" + i, "mousemove", moveTooltipListener, false);
						addEventListener(document, "WEAPON_BUYBACK_" + i, "mouseleave", hideTooltipListener, false);
		
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon((AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), Main.game.getDialogueFlags().tradePartner, null);
						((EventTarget) document.getElementById("WEAPON_BUYBACK_" + i)).addEventListener("mouseenter",el2, false);
					}
					if (((EventTarget) document.getElementById("CLOTHING_BUYBACK_" + i)) != null) {
		
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingBuyback(
								(AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), Main.game.getPlayer().getBuybackStack().get(i).getPrice(), i);
						addEventListener(document, "CLOTHING_BUYBACK_" + i, "click", el, false);
						
						addEventListener(document, "CLOTHING_BUYBACK_" + i, "mousemove", moveTooltipListener, false);
						addEventListener(document, "CLOTHING_BUYBACK_" + i, "mouseleave", hideTooltipListener, false);
		
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), Main.game.getDialogueFlags().tradePartner, null);
						addEventListener(document, "CLOTHING_BUYBACK_" + i, "mouseenter", el2, false);
					}
					if (((EventTarget) document.getElementById("ITEM_BUYBACK_" + i)) != null) {
		
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemBuyback(
								(AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), Main.game.getPlayer().getBuybackStack().get(i).getPrice(), i);
						addEventListener(document, "ITEM_BUYBACK_" + i, "click", el, false);
						
						addEventListener(document, "ITEM_BUYBACK_" + i, "mousemove", moveTooltipListener, false);
						addEventListener(document, "ITEM_BUYBACK_" + i, "mouseleave", hideTooltipListener, false);
		
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), Main.game.getDialogueFlags().tradePartner, null);
						addEventListener(document, "ITEM_BUYBACK_" + i, "mouseenter", el2, false);
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
							EnchantmentDialogue.ingredient = null;
							EnchantmentDialogue.primaryMod = TFModifier.NONE;
							EnchantmentDialogue.secondaryMod = TFModifier.NONE;
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
									
									if(!Main.game.getPlayer().isSideQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
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
					if(EnchantmentDialogue.ingredient.getEnchantmentEffect().getEffectsDescription(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)==null) {
						
					} else {
						Main.game.setContent(new Response("Add", "Add the effect.", EnchantmentDialogue.ENCHANTMENT_MENU){
							@Override
							public void effects() {
								EnchantmentDialogue.effects.add(new ItemEffect(EnchantmentDialogue.ingredient.getEnchantmentEffect(), EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod));
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
			
			
			// -------------------- Phone listeners -------------------- // TODO track listeners
			
			// Phone item viewer:
			for (ClothingType clothing : ClothingType.values())
				for (Colour c : clothing.getAvailableColours()) {
					if ((EventTarget) document.getElementById(clothing.toString() + "_" + c.toString()) != null) {
						addEventListener(document, clothing.toString() + "_" + c.toString(), "mousemove", moveTooltipListener, false);
						addEventListener(document, clothing.toString() + "_" + c.toString(), "mouseleave", hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericClothing(clothing, c);
						addEventListener(document, clothing.toString() + "_" + c.toString(), "mouseenter", el2, false);
					}
				}
			for (WeaponType weapon : WeaponType.values())
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					if ((EventTarget) document.getElementById(weapon.toString() + "_" + dt.toString()) != null) {
						addEventListener(document, weapon.toString() + "_" + dt.toString(), "mousemove", moveTooltipListener, false);
						addEventListener(document, weapon.toString() + "_" + dt.toString(), "mouseleave", hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericWeapon(weapon, dt);
						addEventListener(document, weapon.toString() + "_" + dt.toString(), "mouseenter", el2, false);
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
		if (Main.game.getCurrentDialogueNode() == CharacterCreationDialogue.IMPORT_CHOOSE) {
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
	
	private void manageInventoryListeners() {
		documentInventory = (Document) webEngineInventory.executeScript("document");
		EventListenerDataMap.put(documentInventory, new ArrayList<>());

		// For weapons:
		InventorySlot[] inventorySlots = { InventorySlot.WEAPON_MAIN, InventorySlot.WEAPON_OFFHAND };
		for (InventorySlot invSlot : inventorySlots) {
			if (((EventTarget) documentInventory.getElementById(invSlot.toString() + "Slot")) != null) {
				
				InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(invSlot);
				addEventListener(documentInventory, invSlot.toString() + "Slot", "click", el, false);
				
				addEventListener(documentInventory, invSlot.toString() + "Slot", "mousemove", moveTooltipListener, false);
				addEventListener(documentInventory, invSlot.toString() + "Slot", "mouseleave", hideTooltipListener, false);

				InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, RenderingEngine.ENGINE.getCharactersInventoryToRender(), RenderingEngine.ENGINE.getCharactersInventoryToRender());
				addEventListener(documentInventory, invSlot.toString() + "Slot", "mouseenter", el2, false);
			}
		}

		// For all clothing slots:
		for (InventorySlot invSlot : InventorySlot.values()) {
			if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
				if (((EventTarget) documentInventory.getElementById(invSlot.toString() + "Slot")) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(invSlot);
					addEventListener(documentInventory, invSlot.toString() + "Slot", "click", el, false);
					
					addEventListener(documentInventory, invSlot.toString() + "Slot", "mousemove", moveTooltipListener, false);
					addEventListener(documentInventory, invSlot.toString() + "Slot", "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, RenderingEngine.ENGINE.getCharactersInventoryToRender(), RenderingEngine.ENGINE.getCharactersInventoryToRender());
					addEventListener(documentInventory, invSlot.toString() + "Slot", "mouseenter", el2, false);
				}
			}
		}
		
		// For other slots:
		addEventListener(documentInventory, "protectionSlot", "mousemove", moveTooltipListener, false);
		addEventListener(documentInventory, "protectionSlot", "mouseleave", hideTooltipListener, false);
		((EventTarget) documentInventory.getElementById("protectionSlot")).addEventListener("mouseenter",
				new TooltipInformationEventListener().setProtection(RenderingEngine.ENGINE.getCharactersInventoryToRender()), false);
		
		addEventListener(documentInventory, "tattooSlot", "mousemove", moveTooltipListener, false);
		addEventListener(documentInventory, "tattooSlot", "mouseleave", hideTooltipListener, false);
		((EventTarget) documentInventory.getElementById("tattooSlot")).addEventListener("mouseenter",
				new TooltipInformationEventListener().setTattoo(RenderingEngine.ENGINE.getCharactersInventoryToRender()), false);
	}
	
	private void manageMapListeners() {
		documentMap = (Document) webEngineMap.executeScript("document");
		EventListenerDataMap.put(documentMap, new ArrayList<>());

		if (((EventTarget) documentMap.getElementById("upButton")) != null) {
			addEventListener(documentMap, "upButton", "click", moveNorthListener, true);
		}
		if (((EventTarget) documentMap.getElementById("downButton")) != null) {
			addEventListener(documentMap, "downButton", "click", moveSouthListener, true);
		}
		if (((EventTarget) documentMap.getElementById("leftButton")) != null) {
			addEventListener(documentMap, "leftButton", "click", moveWestListener, true);
		}
		if (((EventTarget) documentMap.getElementById("rightButton")) != null) {
			addEventListener(documentMap, "rightButton", "click", moveEastListener, true);
		}
	}
	
	private void manageMapTitleListeners() {
		documentMapTitle = (Document) webEngineMapTitle.executeScript("document");
		EventListenerDataMap.put(documentMapTitle, new ArrayList<>());

		if (((EventTarget) documentMapTitle.getElementById("weather")) != null) {
			addEventListener(documentMapTitle, "weather", "mousemove", moveTooltipListener, false);
			addEventListener(documentMapTitle, "weather", "mouseleave", hideTooltipListener, false);
			addEventListener(documentMapTitle, "weather", "mouseenter", new TooltipInformationEventListener().setWeather(), true);
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
	
	public void setInventoryViewContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentInventory);
			setWebEngineContent(webEngineInventory, content);
			manageInventoryListeners();
		} else {
			webEngineInventory.loadContent(content);
		}
	}
	
	public void setMapViewContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentMap);
			setWebEngineContent(webEngineMap, content);
			manageMapListeners();
		} else {
			webEngineMap.loadContent(content);
		}
	}
	
	public void setMapTitleContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentMapTitle);
			setWebEngineContent(webEngineMapTitle, content);
			manageMapTitleListeners();
		} else {
			webEngineMapTitle.loadContent(content);
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
	
	/**
	 * For cheat codes. debug: toggle debug mode spawn: open enemy spawn menu
	 */
	private void checkLastKeys() {
		if (lastKeysEqual(KeyCode.B, KeyCode.U, KeyCode.G, KeyCode.G, KeyCode.Y)) {
			Main.game.setContent(new Response("", "", GenericDialogue.DEBUG_MENU));
		}

	}

	private boolean lastKeysEqual(KeyCode one, KeyCode two, KeyCode three, KeyCode four, KeyCode five) {
		if (lastKeys[0] == five && lastKeys[1] == four && lastKeys[2] == three && lastKeys[3] == two && lastKeys[4] == one)
			return true;
		else
			return false;
	}

	/**
	 * Updates every element of the UI.
	 */
	public void updateUI() {
		if (Main.game.isRenderAttributesSection()) {
			RenderingEngine.ENGINE.renderAttributesPanel();
		}
		if (Main.game.isRenderMapSection()) {
			RenderingEngine.ENGINE.renderButtons();
		}
	}

	public void forceInventoryRender() {
		RenderingEngine.ENGINE.renderInventory();
	}

	public void renderMap() {
		if (Main.game.getActiveWorld() != null && Main.game.isRenderMapSection()) {
			setMapViewContent(RenderingEngine.ENGINE.renderedHTMLMap());
		}
	}

	public void zoomMap() {
		if (Main.game.isRenderMapSection() && !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
			RenderingEngine.setZoomedIn(!RenderingEngine.isZoomedIn());

			renderMap();
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
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() + 1).getPlace() != GenericPlace.IMPASSABLE) {
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
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() - 1).getPlace() != GenericPlace.IMPASSABLE) {
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
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() + 1, Main.game.getPlayer().getLocation().getY()).getPlace() != GenericPlace.IMPASSABLE) {
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
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() - 1, Main.game.getPlayer().getLocation().getY()).getPlace() != GenericPlace.IMPASSABLE) {
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

	public WebEngine getWebEngineInventory() {
		return webEngineInventory;
	}

	public WebEngine getWebEngineAttributes() {
		return webEngineAttributes;
	}

	public WebEngine getWebEngineMapTitle() {
		return webEngineMapTitle;
	}

	public WebEngine getWebEngineButtons() {
		return webEngineButtons;
	}

	public WebView getWebViewInventory() {
		return webViewInventory;
	}

	public WebEngine getWebEngineMap() {
		return webEngineMap;
	}

	public WebView getWebViewMap() {
		return webViewMap;
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
			Main.mainController.getWebEngineInventory().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewInventory_stylesheet.css").toExternalForm());
			Main.mainController.getWebEngineMap().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewMap_stylesheet.css").toExternalForm());
			Main.mainController.getWebEngineMapTitle().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewMap_stylesheet.css").toExternalForm());
	
			Main.mainScene.getStylesheets().clear();
			Main.mainScene.getStylesheets().add("/com/base/res/css/stylesheet.css");
			Main.primaryStage.setScene(Main.mainScene);
		} else {
			Main.mainController.getWebEngineTooltip().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewTooltip_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngine().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webView_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineButtons().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineAttributes().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineResponse().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewResponse_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineInventory().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewInventory_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineMap().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewMap_stylesheet_light.css").toExternalForm());
			Main.mainController.getWebEngineMapTitle().setUserStyleSheetLocation(getClass().getResource("/com/base/res/css/webViewMap_stylesheet_light.css").toExternalForm());
	
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
