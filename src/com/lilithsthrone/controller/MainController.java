package com.lilithsthrone.controller;

import java.io.File;
import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.EnchantmentEventListener;
import com.lilithsthrone.controller.eventListeners.InventorySelectedItemEventListener;
import com.lilithsthrone.controller.eventListeners.InventoryTooltipEventListener;
import com.lilithsthrone.controller.eventListeners.SetContentEventListener;
import com.lilithsthrone.controller.eventListeners.TooltipHideEventListener;
import com.lilithsthrone.controller.eventListeners.TooltipInformationEventListener;
import com.lilithsthrone.controller.eventListeners.TooltipMoveEventListener;
import com.lilithsthrone.controller.eventListeners.TooltipResponseDescriptionEventListener;
import com.lilithsthrone.controller.eventListeners.TooltipResponseMoveEventListener;
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
import com.lilithsthrone.controller.eventListeners.information.CopyInfoEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterChangeEventListener;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.TestNPC;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.slavery.SlaveJob;
import com.lilithsthrone.game.slavery.SlaveJobHours;
import com.lilithsthrone.game.slavery.SlaveJobSetting;
import com.lilithsthrone.game.slavery.SlavePermission;
import com.lilithsthrone.game.slavery.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

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
 * @version 0.2.4
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
	private WebView webViewMain, webViewAttributes, webViewRight, webViewButtons;

	private WebEngine webEngine, webEngineTooltip, webEngineAttributes, webEngineRight, webEngineButtons;
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
		
		allowInput = true;
	}

	// All setup methods:
	public void openOptions() {
		if(!Main.game.isStarted()) {
			return;
		}
		
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.OPTIONS) {
			Main.game.restoreSavedContent();
			
		} else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL || !Main.game.isInNewWorld()) {
				Main.game.saveDialogueNode();
			}
			
			Main.game.setContent(new Response("", "", OptionsDialogue.MENU));
		}
	}

	public void openPhone() {
		if(!Main.game.isStarted() || !Main.game.isInNewWorld()) {
			return;
		}
		
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
		if(!Main.game.isInNewWorld() && !Main.game.isInSex()) {
			openInventory(null, InventoryInteraction.CHARACTER_CREATION);
			
		} else if(Main.game.isInCombat()) {
			openInventory((NPC) Combat.getTargetedCombatant(Main.game.getPlayer()), InventoryInteraction.COMBAT);
			
		} else if(Main.game.isInSex()) {
			openInventory((NPC) Sex.getActivePartner(), InventoryInteraction.SEX);
			
		} else if(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected() != null) {
			openInventory(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected(), InventoryInteraction.FULL_MANAGEMENT);
			
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
		
		// Why did I do this? BlobHyperThink
//		if(npc!=null) {
//			npc.setPendingClothingDressing(true);
//		}
		
		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.INVENTORY) {
			Main.game.restoreSavedContent();

		} else if (!isInventoryDisabled() || npc != null) {
			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL) {
				Main.game.saveDialogueNode();
			}
			
			Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
		}

		// processNewDialogue();
	}

	public void openCharactersPresent() {
		openCharactersPresent(null);
	}
	
	public void openCharactersPresent(GameCharacter characterViewed) {
		if(!Main.game.isStarted()) {
			return;
		}
		
		if(characterViewed!=null && characterViewed != CharactersPresentDialogue.characterViewed) {
			
			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL) {
				Main.game.saveDialogueNode();
			}
			
			CharactersPresentDialogue.resetContent(characterViewed);
			Main.game.setContent(new Response("", "", CharactersPresentDialogue.MENU));
			
		} else {
			if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.CHARACTERS_PRESENT) {
				Main.game.restoreSavedContent();
				
			} else if (!Main.game.getCharactersPresent().isEmpty()) {
	
				if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL) {
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

						System.arraycopy(lastKeys, 0, lastKeys, 1, 4);
						lastKeys[0] = event.getCode();
						checkLastKeys();

//						 System.out.println(event.getCode());
						

//						 if(event.getCode()==KeyCode.HOME){
//							 Game.importGame();
//						 }
						
						 if(event.getCode()==KeyCode.END){
							 
//							 Main.game.getPlayer().resetSpells();
//							 
//							 System.out.println(Main.game.getPlayer().toString());
							 
//							 File image = new File("res/images/characters/jam/brandiNaked1.png");
////								+"file:/"+image.toURI().getPath()
////							+"<img src=\"file:/"+image.toURI().getPath()+"\" width='250' height='600'/>"
//							 
//							 webEngine.load(image.toURI().toString());
//							 webEngine.loadContent("", "");
//							 Cell[][] grid = new Cell[5][5];
//							 for(int i=0; i<grid.length;i++) {
//								 for(int j=0; j<grid[0].length;j++) {
//									 grid[i][j] = new Cell(WorldType.SEWERS, new Vector2i(i, j));
//									 grid[i][j].setPlace(new GenericPlace(PlaceType.SUBMISSION_IMP_PALACE));
//								 }
//							 }
//							 
//							 Generation.printMaze(WorldType.SEWERS, Generation.generateTestMap(WorldType.SEWERS, 0, 0, grid, 2));
							 
//							 Main.game.getPlayer().incrementCummedInArea(OrificeType.VAGINA, 10000);
							 
//							 Main.game.getPlayer().addPsychoactiveFluidIngested(FluidType.CUM_HUMAN);
//							 Main.game.getPlayer().addPsychoactiveFluidIngested(FluidType.MILK_HUMAN);
//							 Main.game.getPlayer().addPsychoactiveFluidIngested(FluidType.GIRL_CUM_HUMAN);
//							 Main.game.getPlayer().addStatusEffect(StatusEffect.PSYCHOACTIVE, 60*6);
							 
//							 Main.game.getPlayer().addAddiction(new Addiction(FluidType.MILK_HUMAN, Main.game.getMinutesPassed()));
							 
//							 Main.game.getPlayer().incrementAlcoholLevel(0.2f);
							 
//							 for(Fetish f : Fetish.values()) {
//								 Main.game.getPlayer().incrementFetishExperience(f, (int) (Math.random()*20));
//							 }
							 
//							 Main.game.getPlayer().incrementCummedInArea(OrificeType.MOUTH, 2500);
							 
//							 for(NPC npc : Main.game.getNPCMap().values()) {
//								 System.out.println(npc.getId());
//							 }
//							 for(int i=0; i<=1000; i++) {
//								 System.out.println(Util.intToString(i));
//							 }
							 
//							 Main.game.getPlayer().addDirtySlot(InventorySlot.GROIN);
//							 Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
//							 Main.game.getPlayer().addDirtySlot(InventorySlot.LEG);
							 
							 
//							 for(int i=0;i<10;i++) {
//								 System.out.println(Name.getRandomTriplet(Race.DEMON));
//							 }
							 
//							 Game.exportGame();
							 
//							 System.out.println(Main.game.getNumberOfWitches());
							 
//							 SlaveryUtil.calculateEvent(Main.game.getMinutesPassed(), Main.game.getPlayer().getSlavesOwned().get(0));
//							 for(String npc : Main.game.getNPCMap().keySet()) {
//								 System.out.println(npc);
//							 }
//							 
//							 System.out.println(ItemType.BOOK_CAT_MORPH.getId());
							 
//							 System.out.println(Main.game.getPlayer().getNextClothingToRemoveForCoverableAreaAccess(CoverableArea.VAGINA).getKey().getName());
							 
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
						if(Main.game.getCurrentDialogueNode() == CharacterCreation.CHOOSE_NAME || Main.game.getCurrentDialogueNode() == CityHall.CITY_HALL_NAME_CHANGE_FORM){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput') === document.activeElement")) {
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
						if(Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.ROOM_UPGRADES
								|| Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.ROOM_UPGRADES_MANAGEMENT){
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
						if(Main.game.getCurrentDialogueNode() == CityHall.CITY_HALL_NAME_CHANGE_FORM){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									Main.game.setContent(1);
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
										Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
									}
									Main.game.setContent(new Response("Save", "", Main.game.getCurrentDialogueNode()));
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.SLAVE_MANAGEMENT_INSPECT
								|| Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.SLAVE_MANAGEMENT_JOBS
								|| Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.SLAVE_MANAGEMENT_PERMISSIONS){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput') === document.activeElement")) {
								allowInput = false;
								if (event.getCode() == KeyCode.ENTER) {
									enterConsumed = true;
									boolean unsuitableName = false;
								 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')")!=null) {
									 
										Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
										if(Main.mainController.getWebEngine().getDocument()!=null) {
											unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
															|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
										}
										
										if (!unsuitableName) {
											Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
												@Override
												public void effects() {
													Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
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
													Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
												}
											});
										} else {
											Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
										}
									}
								}
							}
						}
						
						if(((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('offspringPetNameInput') === document.activeElement"))) {
							allowInput = false;
						}
						
						if(Main.game.getCurrentDialogueNode() == OptionsDialogue.OPTIONS_PRONOUNS){
							for(GenderPronoun gp : GenderPronoun.values()) {
								if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('feminine_" + gp + "') === document.activeElement")
									|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('masculine_" + gp + "') === document.activeElement")) {
									allowInput = false;
									if (event.getCode() == KeyCode.ENTER) {
										enterConsumed = true;
										Main.game.setContent(1);
									}
								}
							}
							for(GenderNames genderName : GenderNames.values()) {
								if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_MASCULINE_" + genderName + "') === document.activeElement")
									|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_ANDROGYNOUS_" + genderName + "') === document.activeElement")
									|| (boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_FEMININE_" + genderName + "') === document.activeElement")) {
									allowInput = false;
									if (event.getCode() == KeyCode.ENTER) {
										enterConsumed = true;
										Main.game.setContent(1);
									}
								}
							}
						}
						
						if(Main.game.getCurrentDialogueNode() == DebugDialogue.PARSER){
							if((boolean) Main.mainController.getWebEngine().executeScript("document.getElementById('parseInput') === document.activeElement"))
								allowInput = false;
						}
						
						if(allowInput){
							if (keyEventMatchesBindings(KeyboardAction.INVENTORY, event))
								openInventory();
							if (keyEventMatchesBindings(KeyboardAction.JOURNAL, event))
								openPhone();
							if (keyEventMatchesBindings(KeyboardAction.CHARACTERS, event))
								openCharactersPresent(null);
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
	
	public static Document document, documentButtons, documentAttributes, documentRight, documentInventory, documentMap, documentMapTitle;
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
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineTooltip.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewTooltip_stylesheet_light.css").toExternalForm());
		} else {
			webEngineTooltip.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewTooltip_stylesheet.css").toExternalForm());
		}
		
		// Main WebView:
		webViewMain.setContextMenuEnabled(false);
		webEngine = webViewMain.getEngine();
		webEngine.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngine.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webView_stylesheet_light.css").toExternalForm());
		} else {
			webEngine.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webView_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
		webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(document);
				manageMainListeners();
			}
		});

		// Buttons webview:

		webViewButtons.setContextMenuEnabled(false);
		webEngineButtons = webViewButtons.getEngine();
		webEngineButtons.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineButtons.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
		} else {
			webEngineButtons.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet.css").toExternalForm());
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
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineAttributes.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
		} else {
			webEngineAttributes.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
		webEngineAttributes.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(documentAttributes);
				manageAttributeListeners();
			}
		});
		
		// Attributes WebView:
		webViewRight.setContextMenuEnabled(false);
		webEngineRight = webViewRight.getEngine();
		webEngineRight.getHistory().setMaxSize(0);
		
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			webEngineRight.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet_light.css").toExternalForm());
		} else {
			webEngineRight.setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
		}
		
		if(debugAllowListeners)
			webEngineRight.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
			if (newState == State.SUCCEEDED) {
				unbindListeners(documentRight);
				manageRightListeners();
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
		
		if (((EventTarget) document.getElementById("export-character-button")) != null) {
			addEventListener(document, "export-character-button", "click", e -> {
				if(Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)) {
					Game.exportCharacter(Main.game.getPlayer());
				} else {
					Game.exportCharacter(CharactersPresentDialogue.characterViewed);
				}
				
				Main.game.flashMessage(Colour.GENERIC_EXCELLENT, "Character Exported!");
			}, false);
			addEventListener(document, "export-character-button", "mousemove", moveTooltipListener, false);
			addEventListener(document, "export-character-button", "mouseleave", hideTooltipListener, false);
			addEventListener(document, "export-character-button", "mouseenter", new TooltipInformationEventListener().setInformation(
					"Export Character",
					"Export the currently displayed character to the 'data/characters' folder. Exported characters can be imported at the auction block in Slaver Alley."), false);
		}
		
		if(Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
				|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER)
				|| Main.game.getCurrentDialogueNode().equals(SlaveryManagementDialogue.SLAVE_MANAGEMENT_INSPECT)) {
			if(CharactersPresentDialogue.characterViewed instanceof NPC && !((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().isEmpty()) {
				
				try {
					Artwork artwork = ((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().get(((NPC)CharactersPresentDialogue.characterViewed).getArtworkIndex());
					
					id = "ARTWORK_INFO";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(!artwork.getArtist().getWebsites().isEmpty()) {
								Util.openLinkInDefaultBrowser(artwork.getArtist().getWebsites().get(0).getURL());
							}
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
								"Artwork by <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getName()+"</b>",
								(artwork.getArtist().getWebsites().isEmpty()
										?"This artist has no associated websites!"
										:"Click this button to open <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getWebsites().get(0).getName()+"</b>"
											+ " ("+artwork.getArtist().getWebsites().get(0).getURL()+") <b>externally</b> in your default browser!")),
								false);
					}
					
					id = "ARTWORK_PREVIOUS";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(artwork.getTotalArtworkCount()>1) {
								artwork.incrementIndex(-1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_NEXT";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(artwork.getTotalArtworkCount()>1) {
								artwork.incrementIndex(1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_ARTIST_PREVIOUS";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().size()>1) {
								((NPC)CharactersPresentDialogue.characterViewed).incrementArtworkIndex(-1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_ARTIST_NEXT";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().size()>1) {
								((NPC)CharactersPresentDialogue.characterViewed).incrementArtworkIndex(1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
				} catch(Exception ex) {
					System.err.println("MainController Artwork handling error.");
				}
				
			}
		}
		
		// -------------------- Debug menu -------------------- //
		
		if(Main.game.getCurrentDialogueNode().equals(DebugDialogue.SPAWN_MENU)) {
			id = "";
			
			for(AbstractClothingType clothingType : DebugDialogue.clothingTotal) {
				id = clothingType.getId() + "_SPAWN";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addClothing(AbstractClothingType.generateClothing(clothingType));
						this.updateUIRightPanel();
					}, false);

					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericClothing(clothingType, clothingType.getAvailablePrimaryColours().get(0));
					addEventListener(document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractWeaponType weaponType : DebugDialogue.weaponsTotal) {
				id = weaponType.getId() + "_SPAWN";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(AbstractWeaponType.generateWeapon(weaponType));
						this.updateUIRightPanel();
					}, false);

					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericWeapon(weaponType, weaponType.getAvailableDamageTypes().get(0));
					addEventListener(document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractItemType itemType : DebugDialogue.itemsTotal) {
				id = itemType.getId() + "_SPAWN";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(AbstractItemType.generateItem(itemType));
						this.updateUIRightPanel();
					}, false);

					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericItem(itemType);
					addEventListener(document, id, "mouseenter", el, false);
				}
			}
			
			for(InventorySlot slot : InventorySlot.values()) {
				id = slot + "_SPAWN_SELECT";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						DebugDialogue.activeSlot = slot;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
					
				}
			}
			id = "ITEM_SPAWN_SELECT";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
			}
		}
		
		
		
		
		if(Main.game.getCurrentDialogueNode() == CharacterCreation.BACKGROUND_SELECTION_MENU) {
			for(History history : History.values()) {
				id = "HISTORY_"+history;
				if (((EventTarget) document.getElementById(id)) != null) {
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setPerk(history.getAssociatedPerk(), Main.game.getPlayer()), false);
					
					((EventTarget) document.getElementById(id)).addEventListener("click", event -> {
						Main.game.getPlayer().setHistory(history);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			
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
			
			// Gifts:
			if(Main.game.getCurrentDialogueNode().equals(GiftDialogue.GIFT_DIALOGUE)) {
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeWeapon(entry.getKey());
								}
							});
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), Main.game.getPlayer());
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeItem(entry.getKey());
								}
							});
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeClothing(entry.getKey());
								}
							});
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
			}
			
			// Non-equipped inventory:
			for(int i=0 ; i<RenderingEngine.INVENTORY_PAGES; i++) {
				setInventoryPageLeft(i);
				setInventoryPageRight(i);
			}
			
			
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
				String idModifier = "NPC_"+InventoryDialogue.getInventoryNPC().getId()+"_";
				for (Entry<AbstractWeapon, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateWeapons().entrySet()) {
					id = idModifier+"WEAPON_" + entry.getKey().hashCode();
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
					id = idModifier+"CLOTHING_" + entry.getKey().hashCode();
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
					id = idModifier+"ITEM_" + entry.getKey().hashCode();
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
					id = "FLOOR_WEAPON_" + entry.getKey().hashCode();
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
					id = "FLOOR_CLOTHING_" + entry.getKey().hashCode();
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
					id = "FLOOR_ITEM_" + entry.getKey().hashCode();
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

			for(TFPotency potency : TFPotency.values()) {
				id = "POTENCY_"+potency;
				if (((EventTarget) document.getElementById(id)) != null) {
	
					EnchantmentEventListener el = new EnchantmentEventListener().setPotency(potency);
					addEventListener(document, id, "click", el, false);
					
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFPotency(potency);
					addEventListener(document, id, "mouseenter", el2, false);
				}
			}
			for(int effectCount=0; effectCount<EnchantmentDialogue.effects.size(); effectCount++) {
				id = "DELETE_EFFECT_"+effectCount;
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					EnchantmentEventListener el = new EnchantmentEventListener().removeEffect(effectCount);
					addEventListener(document, id, "click", el, false);
					
					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation("Delete Effect", "");
					addEventListener(document, id, "mouseenter", el2, false);
				}
			}
			
			id = "LIMIT_MINIMUM";
			if (((EventTarget) document.getElementById(id)) != null) {
				if(EnchantmentDialogue.limit>0) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(0);
					addEventListener(document, id, "click", el, false);
				}
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation((EnchantmentDialogue.limit==0?"Minimum Limit Reached":"Limit Minimum"), "");
				addEventListener(document, id, "mouseenter", el2, false);
			}
			
			id = "LIMIT_DECREASE_LARGE";
			if (((EventTarget) document.getElementById(id)) != null) {
				if(EnchantmentDialogue.limit>0) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(Math.max(0, EnchantmentDialogue.limit-(EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)/10)));
					addEventListener(document, id, "click", el, false);
				}
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation((EnchantmentDialogue.limit==0?"Minimum Limit Reached":"Large Limit Decrease"), "");
				addEventListener(document, id, "mouseenter", el2, false);
			}
			
			id = "LIMIT_DECREASE";
			if (((EventTarget) document.getElementById(id)) != null) {
				if(EnchantmentDialogue.limit>0) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(EnchantmentDialogue.limit-1);
					addEventListener(document, id, "click", el, false);
				}
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation((EnchantmentDialogue.limit==0?"Minimum Limit Reached":"Limit Decrease"), "");
				addEventListener(document, id, "mouseenter", el2, false);
			}
			
			id = "LIMIT_INCREASE";
			if (((EventTarget) document.getElementById(id)) != null) {
				if(EnchantmentDialogue.limit < EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(EnchantmentDialogue.limit+1);
					addEventListener(document, id, "click", el, false);
				}
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation(
						(EnchantmentDialogue.limit < EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)
								?"Limit Increase"
								:"Maximum Limit Reached"), "");
				addEventListener(document, id, "mouseenter", el2, false);
			}
			
			id = "LIMIT_INCREASE_LARGE";
			if (((EventTarget) document.getElementById(id)) != null) {
				int limit = EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod);
				if(EnchantmentDialogue.limit < limit) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(Math.min(limit, EnchantmentDialogue.limit+(limit/10)));
					addEventListener(document, id, "click", el, false);
				}
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation(
						(EnchantmentDialogue.limit < EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)
								?"Large Limit Increase"
								:"Maximum Limit Reached"), "");
				addEventListener(document, id, "mouseenter", el2, false);
			}

			id = "LIMIT_MAXIMUM";
			if (((EventTarget) document.getElementById(id)) != null) {
				if(EnchantmentDialogue.limit < EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod));
					addEventListener(document, id, "click", el, false);
				}
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation(
						(EnchantmentDialogue.limit < EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)
								?"Set Limit To Maximum"
								:"Maximum Limit Reached"), "");
				addEventListener(document, id, "mouseenter", el2, false);
			}
			
			// Ingredient icon:
			if (((EventTarget) document.getElementById("INGREDIENT_ENCHANTING")) != null) {
				
				((EventTarget) document.getElementById("INGREDIENT_ENCHANTING")).addEventListener("click", e -> {
					Main.game.setResponseTab(1);
					if(EnchantmentDialogue.ingredient instanceof AbstractItem) {
						Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.ITEM_INVENTORY){
							@Override
							public void effects() {
								EnchantmentDialogue.resetEnchantmentVariables();
							}
						});
					} else if(EnchantmentDialogue.ingredient instanceof AbstractClothing) {
						Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.CLOTHING_INVENTORY){
							@Override
							public void effects() {
								EnchantmentDialogue.resetEnchantmentVariables();
							}
						});
						
					} else {
						Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.WEAPON_INVENTORY){
							@Override
							public void effects() {
								EnchantmentDialogue.resetEnchantmentVariables();
							}
						});
						
					}
					
				}, false);
				
				addEventListener(document, "INGREDIENT_ENCHANTING", "mousemove", moveTooltipListener, false);
				addEventListener(document, "INGREDIENT_ENCHANTING", "mouseleave", hideTooltipListener, false);
				
				InventoryTooltipEventListener el2;
				if(EnchantmentDialogue.ingredient instanceof AbstractItem) {
					el2 = new InventoryTooltipEventListener().setItem((AbstractItem) EnchantmentDialogue.ingredient, Main.game.getPlayer(), null);
				} else if(EnchantmentDialogue.ingredient instanceof AbstractClothing) {
					el2 = new InventoryTooltipEventListener().setClothing((AbstractClothing) EnchantmentDialogue.ingredient, Main.game.getPlayer(), null);
				} else {
					el2 = new InventoryTooltipEventListener().setWeapon((AbstractWeapon) EnchantmentDialogue.ingredient, Main.game.getPlayer());
				}
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
									
									if((EnchantmentDialogue.previousIngredient instanceof AbstractItem?Main.game.getPlayer().hasItem((AbstractItem) EnchantmentDialogue.previousIngredient):true)
											&& (EnchantmentDialogue.previousIngredient instanceof AbstractClothing?Main.game.getPlayer().hasClothing((AbstractClothing) EnchantmentDialogue.previousIngredient):true)
											&& (EnchantmentDialogue.previousIngredient instanceof AbstractWeapon?Main.game.getPlayer().hasWeapon((AbstractWeapon) EnchantmentDialogue.previousIngredient):true)) {
										EnchantmentDialogue.ingredient = EnchantmentDialogue.previousIngredient;
										Main.game.setContent(new Response("", "", EnchantmentDialogue.ENCHANTMENT_MENU));
									} else {
										Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
									}
									
//									EnchantmentDialogue.craftItem(EnchantmentDialogue.ingredient, EnchantmentDialogue.effects);
//									
//									if(EnchantmentDialogue.ingredient instanceof AbstractItem) {
//										if(Main.game.getPlayer().hasItem((AbstractItem) EnchantmentDialogue.previousIngredient)) {
//											EnchantmentDialogue.ingredient = EnchantmentDialogue.previousIngredient;
//											Main.game.setContent(new Response("", "", EnchantmentDialogue.ENCHANTMENT_MENU));
//										} else {
//											Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
//										}
//										
//									} else if(EnchantmentDialogue.ingredient instanceof AbstractClothing) {
//										if(Main.game.getPlayer().hasClothing((AbstractClothing) EnchantmentDialogue.previousIngredient)) {
//											EnchantmentDialogue.ingredient = EnchantmentDialogue.previousIngredient;
//											Main.game.setContent(new Response("", "", EnchantmentDialogue.ENCHANTMENT_MENU));
//										} else {
//											Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
//										}
//										
//									} else if(EnchantmentDialogue.ingredient instanceof AbstractWeapon) {
//										if(Main.game.getPlayer().hasWeapon((AbstractWeapon) EnchantmentDialogue.previousIngredient)) {
//											EnchantmentDialogue.ingredient = EnchantmentDialogue.previousIngredient;
//											Main.game.setContent(new Response("", "", EnchantmentDialogue.ENCHANTMENT_MENU));
//										} else {
//											Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
//										}
//									}
									
									
									
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
			id = "ENCHANT_ADD_BUTTON";
			if (((EventTarget) document.getElementById(id)) != null) {
				
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
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
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);

				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation("Add Effect", "");
				addEventListener(document, id, "mouseenter", el2, false);
			}
			
			id = "ENCHANT_ADD_BUTTON_DISABLED";
			if (((EventTarget) document.getElementById(id)) != null) {
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);

				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation("Add Effect",
						EnchantmentDialogue.effects.size() >= EnchantmentDialogue.ingredient.getEnchantmentLimit()?"You have already added the maximum number of effects for this item!":"");
				addEventListener(document, id, "mouseenter", el2, false);
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
			
			if(Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.ROOM_MANAGEMENT) {
				for(Cell c : SlaveryManagementDialogue.getImportantCells()) {
					id = c.getId();
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									SlaveryManagementDialogue.cellToInspect = c;
								}
								@Override
								public DialogueNodeOld getNextDialogue() {
									return SlaveryManagementDialogue.ROOM_UPGRADES_MANAGEMENT;
								}
							});
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Room", "Open this room's management screen.");
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = c.getId()+"_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Room", "You are not able to manage this room!");
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = c.getId()+"_PRESENT";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									SlaveryManagementDialogue.cellToInspect = c;
								}
								@Override
								public DialogueNodeOld getNextDialogue() {
									return SlaveryManagementDialogue.ROOM_UPGRADES_MANAGEMENT;
								}
							});
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Room", "Open this room's management screen.");
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = c.getId()+"_PRESENT_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Room", "You are not able to manage this room!");
						addEventListener(document, id, "mouseenter", el, false);
					}
				}
			}

			if(Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.ROOM_UPGRADES
					|| Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.ROOM_UPGRADES_MANAGEMENT) {
				for(PlaceUpgrade placeUpgrade : PlaceUpgrade.values()) {
					
					id = "ROOM_MOD_INFO_"+placeUpgrade;
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("", (SlaveryManagementDialogue.cellToInspect.getPlace().getPlaceUpgrades().contains(placeUpgrade)
								?placeUpgrade.getDescriptionAfterPurchase()
								:placeUpgrade.getDescriptionForPurchase()));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = placeUpgrade+"_BUY";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(placeUpgrade!=PlaceUpgrade.LILAYA_ARTHUR_ROOM) {
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										SlaveryManagementDialogue.cellToInspect.getPlace().addPlaceUpgrade(placeUpgrade);
										Main.game.getPlayer().incrementMoney(-placeUpgrade.getInstallCost());
									}
								});
							} else {
								Main.game.setContent(new Response("", "", LilayaHomeGeneric.ROOM_ARTHUR_INSTALLATION){
									@Override
									public void effects() {
										Main.game.getArthur().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
										SlaveryManagementDialogue.cellToInspect.getPlace().addPlaceUpgrade(placeUpgrade);
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursRoomInstalled, true);
									}
								});
							}
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())+"</br>"+SlaveryManagementDialogue.getPurchaseAvailabilityTooltipText(SlaveryManagementDialogue.cellToInspect, placeUpgrade));
						addEventListener(document, id, "mouseenter", el, false);
					}
					id = placeUpgrade+"_BUY_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())+"</br>"+SlaveryManagementDialogue.getPurchaseAvailabilityTooltipText(SlaveryManagementDialogue.cellToInspect, placeUpgrade));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = placeUpgrade+"_SELL";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									SlaveryManagementDialogue.cellToInspect.getPlace().removePlaceUpgrade(placeUpgrade);
									Main.game.getPlayer().incrementMoney(-placeUpgrade.getRemovalCost());
								}
							});
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())+"</br>"+SlaveryManagementDialogue.getPurchaseAvailabilityTooltipText(SlaveryManagementDialogue.cellToInspect, placeUpgrade));
						addEventListener(document, id, "mouseenter", el, false);
					}
					id = placeUpgrade+"_SELL_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Modification",
								(placeUpgrade.isCoreRoomUpgrade()
										?"You cannot directly remove core upgrades. Instead, you'll have to purchase a different core modification in order to remove the current one."
										:"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())+"</br>"+SlaveryManagementDialogue.getPurchaseAvailabilityTooltipText(SlaveryManagementDialogue.cellToInspect, placeUpgrade)));
						addEventListener(document, id, "mouseenter", el, false);
					}
				}
				

				id = "rename_room_button";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {

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
										SlaveryManagementDialogue.cellToInspect.getPlace().setName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								});
							}
						}
							
					}, false);
				}
			}
			
			
			
			// -------------------- Slavery -------------------- //
			
			if(Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.SLAVERY_OVERVIEW) {
				id ="PREVIOUS_DAY";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						SlaveryManagementDialogue.incrementDayNumber(-1);
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				id ="NEXT_DAY";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						SlaveryManagementDialogue.incrementDayNumber(1);
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			if(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected()!=null) {
				
				id = Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().getId()+"_RENAME";
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
										Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
									}
								});
							}
							
						}
							
					}, false);
				}
				
				id = Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().getId()+"_CALLS_PLAYER";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
	
						boolean unsuitableName = false;
					 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')")!=null) {
						 
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
												|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
							}
							
							if (!unsuitableName) {
								Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
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
								unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
												|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
							}
							
							if (!unsuitableName) {
								Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										for(String id: Main.game.getPlayer().getSlavesOwned()) {
											Main.game.getNPCById(id).setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
										}
									}
								});
							}
							
						}
							
					}, false);
				}
				
				// Job hours:
				for(int i=0 ; i<24; i++) {
					allocateWorkTime(i);
				}
				for(SlaveJobHours preset : SlaveJobHours.values()) {

					id = preset+"_TIME";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().resetWorkHours();
							for(int hour = preset.getStartHour(); hour<preset.getStartHour()+preset.getLength(); hour++) {
								if(hour>=24) {
									Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setWorkHour(hour-24, true);
								} else {
									Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setWorkHour(hour, true);
								}
							}
							
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Set Preset Work Hours", preset.getDescription());
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = preset+"_TIME_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Set Preset Work Hours", "You can't assign hours to a slave who is idle. Assign them a job first.");
						addEventListener(document, id, "mouseenter", el, false);
					}
				}
				
				
				// Jobs:
				for(SlaveJob job : SlaveJob.values()) {
					id = "SLAVE_JOB_INFO_" + job;
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())),
								UtilText.parse(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected(), job.getDescription()));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = job+"_ASSIGN";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setSlaveJob(job);
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Assign Job", job.getDescription());
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = job+"_ASSIGN_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Assign Job",
								UtilText.parse(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected(), job.getAvailabilityText(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					for(SlaveJobSetting setting : job.getMutualSettings()) {
						id = setting+"_ADD";
						if (((EventTarget) document.getElementById(id)) != null) {
							((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().addSlaveJobSettings(setting);
								Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
							}, false);
							
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Setting", setting.getDescription());
							addEventListener(document, id, "mouseenter", el, false);
						}
						
						id = setting+"_REMOVE";
						if (((EventTarget) document.getElementById(id)) != null) {
							((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().removeSlaveJobSettings(setting);
								Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
							}, false);
							
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Setting", setting.getDescription());
							addEventListener(document, id, "mouseenter", el, false);
						}
						
						id = setting+"_DISABLED";
						if (((EventTarget) document.getElementById(id)) != null) {
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Setting",
									UtilText.parse(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected(), "You'll need to assign this job to [npc.name] before you can apply any settings."));
							addEventListener(document, id, "mouseenter", el, false);
						}
					}

					for(Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {

						for(SlaveJobSetting setting : entry.getValue()) {
							id = setting+"_TOGGLE_ADD";
							if (((EventTarget) document.getElementById(id)) != null) {
								((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
									for(SlaveJobSetting settingRem : entry.getValue()) {
										Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().removeSlaveJobSettings(settingRem);
									}
									Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().addSlaveJobSettings(setting);
									Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
								}, false);
								
								addEventListener(document, id, "mousemove", moveTooltipListener, false);
								addEventListener(document, id, "mouseleave", hideTooltipListener, false);
								TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Setting", setting.getDescription());
								addEventListener(document, id, "mouseenter", el, false);
							}
							
							id = setting+"_DISABLED";
							if (((EventTarget) document.getElementById(id)) != null) {
								addEventListener(document, id, "mousemove", moveTooltipListener, false);
								addEventListener(document, id, "mouseleave", hideTooltipListener, false);
								TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Setting Applied", setting.getDescription());
								addEventListener(document, id, "mouseenter", el, false);
							}
						}
					}
				}
				
				// Permissions:
				for(SlavePermission permission : SlavePermission.values()) {
					for(SlavePermissionSetting setting : permission.getSettings()) {
						id = setting+"_ADD";
						if (((EventTarget) document.getElementById(id)) != null) {
							((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().addSlavePermissionSetting(permission, setting);
								Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
							}, false);
							
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Setting", setting.getDescription());
							addEventListener(document, id, "mouseenter", el, false);
						}
						
						id = setting+"_REMOVE";
						if (((EventTarget) document.getElementById(id)) != null) {
							((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().removeSlavePermissionSetting(permission, setting);
								Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
							}, false);
							
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Setting", setting.getDescription());
							addEventListener(document, id, "mouseenter", el, false);
						}
						
						id = setting+"_REMOVE_ME";
						if (((EventTarget) document.getElementById(id)) != null) {
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Setting", "You cannot remove mutually exclusive settings! Choose a different option instead.");
							addEventListener(document, id, "mouseenter", el, false);
						}
					}
				}
			}
			
			
			for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {
				id = slaveId;
				NPC slave = (NPC) Main.game.getNPCById(slaveId);
				if(slave!=null) { // It shouldn't equal null...
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementInspectSlaveDialogue(slave)));
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Inspect Slave",
								UtilText.parse(slave, "Inspect [npc.name]."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_JOB"; //TODO
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(slave)));
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Job",
								UtilText.parse(slave, "Set [npc.name]'s job and work hours."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_PERMISSIONS"; //TODO
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(slave)));
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Permissions",
								UtilText.parse(slave, "Manage [npc.name]'s permissions."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_INVENTORY";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.mainController.openInventory(slave, InventoryInteraction.FULL_MANAGEMENT);
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Inventory",
								UtilText.parse(slave, "Manage [npc.name]'s inventory."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRANSFER";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									slave.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
									slave.returnToHome();
								}
							});
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Slave Here",
								UtilText.parse(slave, "Move [npc.name] to your current location."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRANSFER_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Slave Here",
								UtilText.parse(slave, "You cannot move [npc.name] to this location, as there's no room for [npc.herHim] here."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_SELL";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()));
									Main.game.getDialogueFlags().getSlaveTrader().addSlave(slave);
									slave.setLocation(Main.game.getDialogueFlags().getSlaveTrader().getWorldLocation(), Main.game.getDialogueFlags().getSlaveTrader().getLocation(), true);
								}
							});
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell Slave",
								UtilText.parse(slave, "[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(), "b", Colour.GENERIC_GOOD)+"</br>"
										+ "However, "+Main.game.getDialogueFlags().getSlaveTrader().getName()+" will buy [npc.herHim] for "
											+UtilText.formatAsMoney((int)(slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", Colour.GENERIC_ARCANE)+"."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_SELL_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell Slave",
								UtilText.parse(slave, "You cannot sell [npc.name], as there's nobody here to sell [npc.herHim] to."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_COSMETICS";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(slave);
									BodyChanging.setTarget(slave);
								}
							});
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send Slave to Kate",
								UtilText.parse(slave, "Send [npc.name] to Kate's beauty salon, 'Succubi's Secrets', to get [npc.her] appearance changed."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_COSMETICS_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send Slave to Kate",
								UtilText.parse(slave, "You haven't met Kate yet!"));
						addEventListener(document, id, "mouseenter", el, false);
					}
				}
			}
			

			if(Main.game.getDialogueFlags().getSlaveTrader()!=null) {
				for(String slaveId : Main.game.getDialogueFlags().getSlaveTrader().getSlavesOwned()) {
					id = slaveId+"_TRADER";
					NPC slave = (NPC) Main.game.getNPCById(slaveId);
					
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementInspectSlaveDialogue(slave)));
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Inspect Slave",
								UtilText.parse(slave, "Take a detailed look at [npc.name]."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_JOB";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Job",
								UtilText.parse(slave, "You cannot manage [npc.name]'s job, as you don't own [npc.herHim]!"));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_PERMISSIONS";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Permissions",
								UtilText.parse(slave, "You cannot manage [npc.name]'s permissions, as you don't own [npc.herHim]!"));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_INVENTORY";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Inventory",
								UtilText.parse(slave, "You cannot manage [npc.name]'s inventory, as you don't own [npc.herHim]!"));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					
					id = slaveId+"_TRADER_TRANSFER";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Slave To Here",
								UtilText.parse(slave, "You cannot move [npc.name] to this location, as you don't own [npc.herHim], as well as due to the fact that [npc.she]'s already here!"));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_BUY";
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-(int)(slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier()));
									Main.game.getPlayer().addSlave(slave);
									slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
								}
							});
						}, false);
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Buy Slave",
								UtilText.parse(slave, "[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(), "b", Colour.GENERIC_GOOD)+"</br>"
										+ "However, "+Main.game.getDialogueFlags().getSlaveTrader().getName()+" will sell [npc.herHim] for "
											+UtilText.formatAsMoney((int)(slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier()), "b", Colour.GENERIC_ARCANE)+"."));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_BUY_DISABLED";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Buy Slave",
								UtilText.parse(slave, "You cannot buy [npc.name], as you don't have enough money!"));
						addEventListener(document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_COSMETICS";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send Slave to Kate",
								UtilText.parse(slave, "You can't send a slave you don't own to Kate!"));
						addEventListener(document, id, "mouseenter", el, false);
					}
				}
			}
			
			
			// -------------------- Incest Renaming -------------------- //
			
			if(Main.game.getActiveNPC()!=null) {
				id = Main.game.getActiveNPC().getId()+"_PET_NAME";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
	
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
										Main.game.getActiveNPC().setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								});
							}
							
						}
							
					}, false);
				}
			}
			
			
			// -------------------- Character Creation -------------------- //
			
			if(!Main.game.isInNewWorld()) {
				for(Month month : Month.values()) {
					id = "STARTING_MONTH_"+month;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setStartingDateMonth(month);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				// Sex experiences:
				for(int i : CharacterModificationUtils.soSilly) {
					
					// Given:
					
					id = "HANDJOBS_GIVEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.URETHRA_PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "FINGERINGS_GIVEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "BLOWJOBS_GIVEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "CUNNILINGUS_GIVEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "VAGINAL_GIVEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "ANAL_GIVEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					// Received:

					id = "HANDJOBS_TAKEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.URETHRA_PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "FINGERINGS_TAKEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "BLOWJOBS_TAKEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "CUNNILINGUS_TAKEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "VAGINAL_TAKEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "ANAL_TAKEN_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
			}
			
			if(!Main.game.isInNewWorld()
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_CORE)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_FACE)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_ASS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_BREASTS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_VAGINA)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_PENIS)) {
				
				
				// Gender:
				id = "CHOOSE_GENDER_MALE";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						CharacterCreation.setGenderMale();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}

				id = "CHOOSE_GENDER_FEMALE";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						CharacterCreation.setGenderFemale();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Femininity
				id = "CHOOSE_FEM_MASCULINE_STRONG";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.MASCULINE_STRONG.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_MASCULINE";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.MASCULINE.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_ANDROGYNOUS";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_FEMININE";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.FEMININE.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_FEMININE_STRONG";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.FEMININE_STRONG.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				// Personality:
				for(PersonalityTrait trait : PersonalityTrait.values()) {
					id = "PERSONALITY_INFO_"+trait;
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(trait.getName()), trait.getDescription()), false);
					}
					
					for(PersonalityWeight weight : PersonalityWeight.values()) {
						id = "PERSONALITY_"+trait+"_"+weight;
						if (((EventTarget) document.getElementById(id)) != null) {
							((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
								BodyChanging.getTarget().setPersonalityTrait(trait, weight);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				
				// Orientation:
				for(SexualOrientation orientation : SexualOrientation.values()) {
					id = "SEXUAL_ORIENTATION_"+orientation;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setSexualOrientation(orientation);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
			
				// Height:
				id = "HEIGHT_INCREASE";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_INCREASE_LARGE";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_DECREASE";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_DECREASE_LARGE";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Body size:
				for(BodySize bs : BodySize.values()) {
					id = "BODY_SIZE_"+bs;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBodySize(bs.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Muscles:
				for(Muscle muscle : Muscle.values()) {
					id = "MUSCLE_"+muscle;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setMuscle(muscle.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// ------------ Character creation -------------- //
				
				// Lip Size:
				for(LipSize ls : LipSize.values()) {
					id = "LIP_SIZE_"+ls;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLipSize(ls.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Lip puffiness:
				id = "LIP_PUFFY_ON";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().addFaceOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "LIP_PUFFY_OFF";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().removeFaceOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Breast size:
				for(CupSize cs : CupSize.values()) {
					id = "BREAST_SIZE_"+cs;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastSize(cs.getMeasurement());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Breast Shape:
				for(BreastShape bs : BreastShape.values()) {
					id = "BREAST_SHAPE_"+bs;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastShape(bs);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple size:
				for(NippleSize ns : NippleSize.values()) {
					id = "NIPPLE_SIZE_"+ns;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleSize(ns.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Areolae size:
				for(AreolaeSize as : AreolaeSize.values()) {
					id = "AREOLAE_SIZE_"+as;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAreolaeSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple puffiness:
				id = "NIPPLE_PUFFY_ON";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().addNippleOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "NIPPLE_PUFFY_OFF";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().removeNippleOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Lactation:
				for(int i : CharacterModificationUtils.getLactationQuantitiesAvailable()) {
					id = "LACTATION_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastMilkStorage(i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass size:
				for(AssSize as : CharacterModificationUtils.getAssSizesAvailable()) {
					id = "ASS_SIZE_"+as;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Hip size:
				for(HipSize hs : CharacterModificationUtils.getHipSizesAvailable()) {
					id = "HIP_SIZE_"+hs;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHipSize(hs.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Bleached anus:
				id = "ANUS_BLEACHED_ON";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAssBleached(true);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "ANUS_BLEACHED_OFF";
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAssBleached(false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Penis size:
				for(int ps : CharacterModificationUtils.getPenisSizesAvailable()) {
					id = "PENIS_SIZE_"+ps;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisSize(ps);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				// Penis girth:
				for(PenisGirth girth : PenisGirth.values()) {
					id = "PENIS_GIRTH_"+girth;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisGirth(girth.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				// Testicle size:
				for(TesticleSize ts : CharacterModificationUtils.getTesticleSizesAvailable()) {
					id = "TESTICLE_SIZE_"+ts;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTesticleSize(ts.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Cum production:
				for(CumProduction cp: CharacterModificationUtils.getCumProductionAvailable()) {
					id = "CUM_PRODUCTION_"+cp;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setCumProduction(cp.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina capacity:
				for(Capacity capacity: Capacity.values()) {
					id = "VAGINA_CAPACITY_"+capacity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina wetness:
				for(Wetness wetness: Wetness.values()) {
					id = "VAGINA_WETNESS_"+wetness;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaWetness(wetness.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina elastcity:
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "VAGINA_ELASTICITY_"+elasticity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina plasticity:
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "VAGINA_PLASTICITY_"+plasticity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Clit size:
				for(ClitorisSize cs: ClitorisSize.values()) {
					id = "CLITORIS_SIZE_"+cs;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaClitorisSize(cs.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Labia size:
				for(LabiaSize ls: LabiaSize.values()) {
					id = "LABIA_SIZE_"+ls;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaLabiaSize(ls.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				

				// ------------ Demonic/Slime transformations -------------- //
				
				for(ArmType armType: ArmType.values()) {
					id = "CHANGE_ARM_"+armType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setArmType(armType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(EyeType eyeType: EyeType.values()) {
					id = "CHANGE_EYE_"+eyeType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setEyeType(eyeType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(EarType earType: EarType.values()) {
					id = "CHANGE_EAR_"+earType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setEarType(earType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(HornType hornType: HornType.values()) {
					id = "CHANGE_HORN_"+hornType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHornType(hornType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(AntennaType antennaType: AntennaType.values()) {
					id = "CHANGE_ANTENNA_"+antennaType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAntennaType(antennaType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(HairType hairType: HairType.values()) {
					id = "CHANGE_HAIR_"+hairType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHairType(hairType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				for(LegType legType: LegType.values()) {
					id = "CHANGE_LEG_"+legType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLegType(legType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(FaceType faceType: FaceType.values()) {
					id = "CHANGE_FACE_"+faceType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFaceType(faceType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(SkinType skinType: SkinType.values()) {
					id = "CHANGE_SKIN_"+skinType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setSkinType(skinType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TailType tailType: TailType.values()) {
					id = "CHANGE_TAIL_"+tailType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTailType(tailType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(WingType wingType: WingType.values()) {
					id = "CHANGE_WING_"+wingType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setWingType(wingType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(AssType assType: AssType.values()) {
					id = "CHANGE_ASS_"+assType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssType(assType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(BreastType breastType: BreastType.values()) {
					id = "CHANGE_BREAST_"+breastType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastType(breastType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				// Face:
				
				for(EyeShape eyeShape : EyeShape.values()) {
					id = "CHANGE_IRIS_SHAPE_"+eyeShape;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setIrisShape(eyeShape);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "CHANGE_PUPIL_SHAPE_"+eyeShape;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPupilShape(eyeShape);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(LipSize lipSize : LipSize.values()) {
					id = "CHANGE_LIP_SIZE_"+lipSize;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLipSize(lipSize.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_MOUTH_MOD_"+orificeMod;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasFaceOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeFaceOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addFaceOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TongueModifier tongueMod : TongueModifier.values()) {
					id = "CHANGE_TONGUE_MOD_"+tongueMod;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasTongueModifier(tongueMod)) {
								BodyChanging.getTarget().removeTongueModifier(tongueMod);
							} else {
								BodyChanging.getTarget().addTongueModifier(tongueMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass:
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_ANUS_MOD_"+orificeMod;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasAssOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeAssOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addAssOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass size:
				for(AssSize as : AssSize.values()) {
					id = "CHANGE_ASS_SIZE_"+as;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(HipSize hs : HipSize.values()) {
					id = "CHANGE_HIP_SIZE_"+hs;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHipSize(hs.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Capacity capacity: Capacity.values()) {
					id = "ANUS_CAPACITY_"+capacity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Wetness wetness: Wetness.values()) {
					id = "ANUS_WETNESS_"+wetness;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssWetness(wetness.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "ANUS_ELASTICITY_"+elasticity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "ANUS_PLASTICITY_"+plasticity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				
				// Breasts:
				
				for(int i=1; i <= Breast.MAXIMUM_BREAST_ROWS; i++) {
					setBreastCountListener(i);
				}
				
				for(int i=1; i <= Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
					setNippleCountListener(i);
				}
				
				// Nipple capacity:
				for(Capacity capacity: Capacity.values()) {
					id = "NIPPLE_CAPACITY_"+capacity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple elastcity:
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "NIPPLE_ELASTICITY_"+elasticity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple plasticity:
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "NIPPLE_PLASTICITY_"+plasticity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNipplePlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(int i : CharacterModificationUtils.demonLactationValues) {
					id = "LACTATION_"+i;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastMilkStorage(i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_NIPPLE_MOD_"+orificeMod;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasNippleOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeNippleOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addNippleOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina:
				
				for(VaginaType vaginaType: VaginaType.values()) {
					id = "CHANGE_VAGINA_"+vaginaType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaType(vaginaType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_VAGINA_MOD_"+orificeMod;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasVaginaOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeVaginaOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addVaginaOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Penis:
				
				for(PenisType penisType: PenisType.values()) {
					id = "CHANGE_PENIS_"+penisType;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisType(penisType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(PenisSize ps : PenisSize.values()) {
					id = "PENIS_SIZE_"+ps.getMinimumValue();
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisSize(ps.getMinimumValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "PENIS_SIZE_"+ps.getMedianValue();
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisSize(ps.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TesticleSize size : TesticleSize.values()) {
					id = "TESTICLE_SIZE_"+size;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTesticleSize(size.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(int i=Testicle.MIN_TESTICLE_COUNT; i<=Testicle.MAX_TESTICLE_COUNT; i+=2) {
					setTesticleCountListener(i);
				}
				
				for(Capacity capacity: Capacity.values()) {
					id = "URETHRA_CAPACITY_"+capacity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(CumProduction cumProduction : CumProduction.values()) {
					id = "CUM_PRODUCTION_"+cumProduction.getMinimumValue();
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setCumProduction(cumProduction.getMinimumValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "CUM_PRODUCTION_"+cumProduction.getMedianValue();
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setCumProduction(cumProduction.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "URETHRA_ELASTICITY_"+elasticity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setUrethraElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "URETHRA_PLASTICITY_"+plasticity;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setUrethraPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(PenisModifier orificeMod : PenisModifier.values()) {
					id = "CHANGE_PENIS_MOD_"+orificeMod;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasPenisModifier(orificeMod)) {
								BodyChanging.getTarget().removePenisModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addPenisModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_URETHRA_MOD_"+orificeMod;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasUrethraOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeUrethraOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addUrethraOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
			}
			
			// -------------------- Cosmetics -------------------- //
			
			
			boolean noCost = !Main.game.isInNewWorld() || Main.game.getCurrentDialogueNode().getMapDisplay()==MapDisplay.PHONE;

			for(BodyCoveringType bct : BodyCoveringType.values()) {
				
				id = "APPLY_COVERING_"+bct;
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(CharacterModificationUtils.getCoveringsToBeApplied().containsKey(bct)) {
										if(!noCost) {
											Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
										}
										
										BodyChanging.getTarget().setSkinCovering(new Covering(CharacterModificationUtils.getCoveringsToBeApplied().get(bct)), false);
										
										if(noCost) {
											if(bct == BodyCoveringType.HUMAN) {
												BodyChanging.getTarget().getBody().updateCoverings(false, false, false, true);
											}
										}
									}
								}
							});
						}
					}, false);
				}
				
				
				id = bct+"_PRIMARY_GLOW_OFF";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(false);
							}
						});
					}, false);
				}
				
				id = bct+"_PRIMARY_GLOW_ON";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(true);
								
							}
						});
					}, false);
				}
				
				id = bct+"_SECONDARY_GLOW_OFF";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(false);
							}
						});
					}, false);
				}
				
				id = bct+"_SECONDARY_GLOW_ON";
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(true);
							}
						});
					}, false);
				}
				
				for(CoveringPattern pattern : CoveringPattern.values()) {
					id = bct+"_PATTERN_"+pattern;
					
					if (((EventTarget) document.getElementById(id)) != null) {
						
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPattern(pattern);
								}
							});
						}, false);
					}
				}
				
				for(CoveringModifier modifier : CoveringModifier.values()) {
					id = bct+"_MODIFIER_"+modifier;
					
					if (((EventTarget) document.getElementById(id)) != null) {
						
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setModifier(modifier);
								}
							});
						}, false);
					}
				}

				for(Colour colour : bct.getAllPrimaryColours()) {
					id = bct+"_PRIMARY_"+colour;
					
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryColour(colour);
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing((colour != Colour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isPrimaryGlowing()));
								}
							});
						}, false);
					}
				}
				for(Colour colour : bct.getAllSecondaryColours()) {
					id = bct+"_SECONDARY_"+colour;
					
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryColour(colour);
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(colour != Colour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isSecondaryGlowing());
								}
							});
						}, false);
					}
				}
			}
			
			for(HairLength hairLength : HairLength.values()) {
				id = "HAIR_LENGTH_"+hairLength;
				
				if (((EventTarget) document.getElementById(id)) != null) {
					
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_HAIR_LENGTH_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_LENGTH_COST);
									}
									BodyChanging.getTarget().setHairLength(hairLength.getMedianValue());
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
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_HAIR_STYLE_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_STYLE_COST);
									}
									BodyChanging.getTarget().setHairStyle(hairStyle);
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
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getPiercingCost(piercingType) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
									}
									switch(piercingType) {
										case EAR:
											BodyChanging.getTarget().setPiercedEar(false);
											break;
										case LIP:
											BodyChanging.getTarget().setPiercedLip(false);
											break;
										case NAVEL:
											BodyChanging.getTarget().setPiercedNavel(false);
											break;
										case NIPPLE:
											BodyChanging.getTarget().setPiercedNipples(false);
											break;
										case NOSE:
											BodyChanging.getTarget().setPiercedNose(false);
											break;
										case PENIS:
											BodyChanging.getTarget().setPiercedPenis(false);
											break;
										case TONGUE:
											BodyChanging.getTarget().setPiercedTongue(false);
											break;
										case VAGINA:
											BodyChanging.getTarget().setPiercedVagina(false);
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
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getPiercingCost(piercingType) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
									}
									switch(piercingType) {
										case EAR:
											BodyChanging.getTarget().setPiercedEar(true);
											break;
										case LIP:
											BodyChanging.getTarget().setPiercedLip(true);
											break;
										case NAVEL:
											BodyChanging.getTarget().setPiercedNavel(true);
											break;
										case NIPPLE:
											BodyChanging.getTarget().setPiercedNipples(true);
											break;
										case NOSE:
											BodyChanging.getTarget().setPiercedNose(true);
											break;
										case PENIS:
											BodyChanging.getTarget().setPiercedPenis(true);
											break;
										case TONGUE:
											BodyChanging.getTarget().setPiercedTongue(true);
											break;
										case VAGINA:
											BodyChanging.getTarget().setPiercedVagina(true);
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
					if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_ANAL_BLEACHING_COST || noCost) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								if(!noCost) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
								}
								BodyChanging.getTarget().setAssBleached(false);
							}
						});
					}
				}, false);
			}
			
			if (((EventTarget) document.getElementById("BLEACHING_ON")) != null) {
				
				((EventTarget) document.getElementById("BLEACHING_ON")).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_ANAL_BLEACHING_COST || noCost) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								if(!noCost) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
								}
								BodyChanging.getTarget().setAssBleached(true);
							}
						});
					}
				}, false);
			}
			
			for(BodyHair bodyHair: BodyHair.values()) {
				
				id = "ASS_HAIR_"+bodyHair;
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setAssHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "UNDERARM_HAIR_"+bodyHair;
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setUnderarmHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "PUBIC_HAIR_"+bodyHair;
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setPubicHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "FACIAL_HAIR_"+bodyHair;
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setFacialHair(bodyHair);
								}
							});
						}
					}, false);
				}
			}
			
			
			// -------------------- Phone listeners -------------------- // TODO track listeners
			
			// Phone item viewer:
			for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
				for (Colour c : clothing.getAllAvailablePrimaryColours()) {
					id = "PRIMARY_"+clothing.hashCode() + "_" + c.toString();
					if ((EventTarget) document.getElementById(id) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							InventoryDialogue.dyePreviewPrimary = c;
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingPrimary(InventoryDialogue.getClothing(), c);
						addEventListener(document, id, "mouseenter", el2, false);
					}
				}
				if(!clothing.getAllAvailableSecondaryColours().isEmpty()) {
					for (Colour c : clothing.getAllAvailableSecondaryColours()) {
						id = "SECONDARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) document.getElementById(id) != null) {
							((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewSecondary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingSecondary(InventoryDialogue.getClothing(), c);
							addEventListener(document, id, "mouseenter", el2, false);
						}
					}
				}
				if(!clothing.getAllAvailableTertiaryColours().isEmpty()) {
					for (Colour c : clothing.getAllAvailableTertiaryColours()) {
						id = "TERTIARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) document.getElementById(id) != null) {
							((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewTertiary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingTertiary(InventoryDialogue.getClothing(), c);
							addEventListener(document, id, "mouseenter", el2, false);
						}
					}
				}
			}
			
			for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
				for (Colour colour : clothing.getAllAvailablePrimaryColours()) {
					if ((EventTarget) document.getElementById(clothing.hashCode() + "_" + colour.toString()) != null) {
						addEventListener(document, clothing.hashCode() + "_" + colour.toString(), "mousemove", moveTooltipListener, false);
						addEventListener(document, clothing.hashCode() + "_" + colour.toString(), "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericClothing(clothing, colour);
						addEventListener(document, clothing.hashCode() + "_" + colour.toString(), "mouseenter", el2, false);
					}
				}
			}
			for (AbstractWeaponType weapon : WeaponType.allweapons) {
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					if ((EventTarget) document.getElementById(weapon.hashCode() + "_" + dt.toString()) != null) {
						addEventListener(document, weapon.hashCode() + "_" + dt.toString(), "mousemove", moveTooltipListener, false);
						addEventListener(document, weapon.hashCode() + "_" + dt.toString(), "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericWeapon(weapon, dt);
						addEventListener(document, weapon.hashCode() + "_" + dt.toString(), "mouseenter", el2, false);
					}
				}
			}

			for(Perk perk : Perk.values()) { //TODO
				if(perk.getPerkCategory() == PerkCategory.JOB) {
					id = "HISTORY_"+perk;
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(0, perk, Main.game.getPlayer()), false);
					}
					
				} else {
					id = "TRAIT_"+perk;
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(PerkManager.MANAGER.getPerkRow(perk), perk, Main.game.getPlayer()), false);
						
						((EventTarget) document.getElementById(id)).addEventListener("click", event -> {
							Main.game.getPlayer().removeTrait(perk);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
			}
			
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_ARCANE
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_EARTH
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_WATER
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_AIR
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_FIRE) {
				for(Spell s : Spell.values()) {
					id = "SPELL_TREE_" + s;
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setSpell(s, Main.game.getPlayer()), false);
						
					}
					for(List<TreeEntry<SpellSchool, SpellUpgrade>> upgradeList : s.getSpellUpgradeTree().values()) {
						for(TreeEntry<SpellSchool, SpellUpgrade> upgrade : upgradeList) {
							id = "SPELL_UPGRADE_" + upgrade.getEntry();
							if (((EventTarget) document.getElementById(id)) != null) {
								addEventListener(document, id, "mousemove", moveTooltipListener, false);
								addEventListener(document, id, "mouseleave", hideTooltipListener, false);
								addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setSpellUpgrade(upgrade.getEntry(), Main.game.getPlayer()), false);
								
								((EventTarget) document.getElementById(id)).addEventListener("click", event -> {
									if(Spell.isSpellUpgradeAvailable(Main.game.getPlayer(), s, upgrade) && Main.game.getPlayer().getSpellUpgradePoints(upgrade.getCategory())>0) {
										Main.game.getPlayer().addSpellUpgrade(upgrade.getEntry());
										Main.game.getPlayer().incrementSpellUpgradePoints(upgrade.getCategory(), -1);
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									}
								}, false);
							}
						}
					}
				}
				
			}
			
			// Level up dialogue:
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_LEVEL_UP) {
				for(int i = 0; i<PerkManager.ROWS; i++) {
					for(Entry<PerkCategory, List<TreeEntry<PerkCategory, Perk>>> entry : PerkManager.MANAGER.getPerkTree().get(i).entrySet()) {
						for(TreeEntry<PerkCategory, Perk> e : entry.getValue()) {
							id = i+"_"+e.getEntry();
	
							if (((EventTarget) document.getElementById(id)) != null) {
								addEventListener(document, id, "mousemove", moveTooltipListener, false);
								addEventListener(document, id, "mouseleave", hideTooltipListener, false);
								addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(i, e.getEntry(), Main.game.getPlayer()), false);
								((EventTarget) document.getElementById(id)).addEventListener("click", event -> {
									if(e.getEntry().isEquippableTrait() && PerkManager.MANAGER.isPerkOwned(e)) {
										if(!Main.game.getPlayer().hasTraitActivated(e.getEntry())) {
											Main.game.getPlayer().addTrait(e.getEntry());
										} else {
											Main.game.getPlayer().removeTrait(e.getEntry());
										}
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
										
									} else if(Main.game.getPlayer().getPerkPoints()>=1 && PerkManager.MANAGER.isPerkAvailable(e)) {
										if(Main.game.getPlayer().addPerk(e.getRow(), e.getEntry())) {
											Main.game.getPlayer().incrementPerkPoints(-1);
											if(e.getEntry().isEquippableTrait() && Main.game.getPlayer().getTraits().size()<GameCharacter.MAX_TRAITS) {
												Main.game.getPlayer().addTrait(e.getEntry());
											}
											Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
										}
									}
								}, false);
							}
						}
					}
				}
			}
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_FETISHES) {
				for (Fetish f : Fetish.values()) {
					id = "fetishUnlock" + f;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=f.getCost()) {
								if(Main.game.getPlayer().addFetish(f)) {
									Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -f.getCost(), false);
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
								}
							}
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setFetish(f, Main.game.getPlayer()), false);
					}
					
					id = f+"_EXPERIENCE";
					if (((EventTarget) document.getElementById(id)) != null) {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setFetishExperience(f, Main.game.getPlayer()), false);
					}
					
					for (FetishDesire desire : FetishDesire.values()) {
						id = f+"_"+desire;
						if (((EventTarget) document.getElementById(id)) != null) {
							((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
								if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=FetishDesire.getCostToChange()) {
									if(Main.game.getPlayer().setFetishDesire(f, desire)) {
										Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -FetishDesire.getCostToChange(), false);
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									}
								}
							}, false);
							
							addEventListener(document, id, "mousemove", moveTooltipListener, false);
							addEventListener(document, id, "mouseleave", hideTooltipListener, false);
							addEventListener(document, id, "mouseenter", new TooltipInformationEventListener().setFetishDesire(f, desire, Main.game.getPlayer()), false);
						}
					}
				}
			}
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.MENU) {
				Cell[][] grid = Main.game.getWorlds().get(Main.game.getPlayer().getWorldLocation()).getGrid();

				for(int i=grid[0].length-1; i>=0; i--) {
					for(int j=0; j<grid.length; j++) {
						Cell c = grid[j][i];
						boolean discovered = c.isDiscovered() || Main.game.isDebugMode();
						if(!discovered) {
							continue;
						}
						if(c.getPlace().getPlaceType() == PlaceType.GENERIC_IMPASSABLE) {
							continue;
						}
						id = "MAP_NODE_" + i + "_" + j;
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						
						TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(c.getPlaceName()), "");
						addEventListener(document, id, "mouseenter", el2, false);
					}
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
				for(GenderPreference preference : GenderPreference.values()) {
					if (((EventTarget) document.getElementById(preference+"_"+g)) != null) {
						((EventTarget) document.getElementById(preference+"_"+g)).addEventListener("click", e -> {
							Main.getProperties().genderPreferencesMap.put(g, preference.getValue());
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
			}
		}
		
		// Furry preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.FURRY_PREFERENCE) {

			// Human encounter rates:
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_zero")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_zero")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=0;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, "furry_preference_human_encounter_zero", "mousemove", moveTooltipListener, false);
				addEventListener(document, "furry_preference_human_encounter_zero", "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Disabled", "Randomly generated NPCs will never be fully human, unless all of the other furry preference options are set to disabled.");
				addEventListener(document, "furry_preference_human_encounter_zero", "mouseenter", el, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_one")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_one")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=1;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, "furry_preference_human_encounter_one", "mousemove", moveTooltipListener, false);
				addEventListener(document, "furry_preference_human_encounter_one", "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("5%",
						"There will be a 5% chance for any randomly generated NPC to be fully human. (It will be 100% if all of the other furry preference options are set to disabled)");
				addEventListener(document, "furry_preference_human_encounter_one", "mouseenter", el, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_two")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_two")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=2;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, "furry_preference_human_encounter_two", "mousemove", moveTooltipListener, false);
				addEventListener(document, "furry_preference_human_encounter_two", "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("25%",
						"There will be a 25% chance for any randomly generated NPC to be fully human. (It will be 100% if all of the other furry preference options are set to disabled)");
				addEventListener(document, "furry_preference_human_encounter_two", "mouseenter", el, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_three")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_three")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=3;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, "furry_preference_human_encounter_three", "mousemove", moveTooltipListener, false);
				addEventListener(document, "furry_preference_human_encounter_three", "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("50%",
						"There will be a 50% chance for any randomly generated NPC to be fully human. (It will be 100% if all of the other furry preference options are set to disabled)");
				addEventListener(document, "furry_preference_human_encounter_three", "mouseenter", el, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_human_encounter_four")) != null) {
				((EventTarget) document.getElementById("furry_preference_human_encounter_four")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=4;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, "furry_preference_human_encounter_four", "mousemove", moveTooltipListener, false);
				addEventListener(document, "furry_preference_human_encounter_four", "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("75%",
						"There will be a 75% chance for any randomly generated NPC to be fully human. (It will be 100% if all of the other furry preference options are set to disabled)");
				addEventListener(document, "furry_preference_human_encounter_four", "mouseenter", el, false);
			}
			
			
			// Forced TF racial limits:
			id = "forced_tf_limit_human";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.HUMAN;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Human Only",
						"Forced transformations from NPCs will only ever affect your body's non-racial stats, and if a new part is required (such as a vagina or penis) it will always grow to be a human one.");
				addEventListener(document, id, "mouseenter", el, false);
			}
			id = "forced_tf_limit_minimum";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.MINIMUM;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Minimum Furry",
						"Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, and wings. All other parts will always remain human.");
				addEventListener(document, id, "mouseenter", el, false);
			}
			id = "forced_tf_limit_reduced";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.REDUCED;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Lesser Furry",
						"Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, ass, genitalia, arms, and legs. Your skin and face will always remain human.");
				addEventListener(document, id, "mouseenter", el, false);
			}
			id = "forced_tf_limit_normal";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.NORMAL;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Greater Furry",
						"Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, ass, genitalia, arms, legs, skin, and face. (So everything can be affected.)");
				addEventListener(document, id, "mouseenter", el, false);
			}
			id = "forced_tf_limit_maximum";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.MAXIMUM;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Always Greater Furry",
						"Forced transformations from NPCs will always give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, genitalia, ass, arms, legs, skin, and face. (So everything will be affected.)");
				addEventListener(document, id, "mouseenter", el, false);
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
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.HUMAN);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.HUMAN);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_female_minimum_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_minimum_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.MINIMUM);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.MINIMUM);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_female_reduced_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_reduced_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.REDUCED);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.REDUCED);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_female_normal_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_normal_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.NORMAL);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.NORMAL);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) document.getElementById("furry_preference_female_maximum_all")) != null) {
				((EventTarget) document.getElementById("furry_preference_female_maximum_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.MAXIMUM);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.MAXIMUM);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			for (Subspecies s : Subspecies.values()) {
				for(FurryPreference preference : FurryPreference.values()) {
					id = "FEMININE_" + preference+"_"+s;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(s, preference);
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
	
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(preference.getName(), preference.getDescriptionFeminine(s));
						addEventListener(document, id, "mouseenter", el, false);
					}
					id = "MASCULINE_" + preference+"_"+s;
					if (((EventTarget) document.getElementById(id)) != null) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(s, preference);
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
	
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(preference.getName(), preference.getDescriptionMasculine(s));
						addEventListener(document, id, "mouseenter", el, false);
					}
				}
			}
			
//			for (Subspecies s : Subspecies.values()) {
//				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
//					id = "FEMININE_" + preference+"_"+s;
//					if (((EventTarget) document.getElementById(id)) != null) {
//						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
//							Main.getProperties().subspeciesFemininePreferencesMap.put(s, preference);
//							Main.saveProperties();
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}, false);
//
//						addEventListener(document, id, "mousemove", moveTooltipListener, false);
//						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
//						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(preference.getName(), "");
//						addEventListener(document, id, "mouseenter", el, false);
//					}
//					id = "MASCULINE_" + preference+"_"+s;
//					if (((EventTarget) document.getElementById(id)) != null) {
//						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
//							Main.getProperties().subspeciesFemininePreferencesMap.put(s, preference);
//							Main.saveProperties();
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}, false);
//
//						addEventListener(document, id, "mousemove", moveTooltipListener, false);
//						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
//						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(preference.getName(), "");
//						addEventListener(document, id, "mouseenter", el, false);
//					}
//				}
//			}
		}
		
//		// Race preferences:
//		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.SPECIES_PREFERENCE) {
//			for (Subspecies s : Subspecies.values()) {
//				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
//					if (((EventTarget) document.getElementById(preference+"_"+s)) != null) {
//						((EventTarget) document.getElementById(preference+"_"+s)).addEventListener("click", e -> {
//							Main.getProperties().subspeciesPreferencesMap.put(s, preference);
//							Main.saveProperties();
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}, false);
//					}
//				}
//			}
//		}
		
		// Content preferences:

		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.CONTENT_PREFERENCE || Main.game.getCurrentDialogueNode() == CharacterCreation.CONTENT_PREFERENCES) {
			id = "ARTWORK_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.artwork, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "ARTWORK_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.artwork, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}

			for(Artist artist : Artwork.allArtists) {
				id = "ARTIST_"+artist.getFolderName();
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						Main.getProperties().preferredArtist = artist.getFolderName();
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			id = "NON_CON_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.nonConContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "NON_CON_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.nonConContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "INCEST_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.incestContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "INCEST_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.incestContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "LACTATION_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.lactationContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "LACTATION_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.lactationContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "URETHRAL_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.urethralContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "URETHRAL_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.urethralContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "NIPPLE_PEN_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.nipplePenContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "NIPPLE_PEN_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.nipplePenContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "HAIR_FACIAL_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.facialHairContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HAIR_FACIAL_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.facialHairContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "HAIR_PUBIC_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.pubicHairContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HAIR_PUBIC_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.pubicHairContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "HAIR_BODY_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.bodyHairContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HAIR_BODY_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.bodyHairContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "HAIR_ASS_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.assHairContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HAIR_ASS_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.assHairContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "FEMININE_BEARD_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.feminineBeardsContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "FEMININE_BEARD_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.feminineBeardsContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			id = "FURRY_TAIL_PENETRATION_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.furryTailPenetrationContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "FURRY_TAIL_PENETRATION_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.furryTailPenetrationContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "INFLATION_CONTENT_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.inflationContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "INFLATION_CONTENT_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.inflationContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			

			id = "FORCED_TF_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPercentage = Math.min(100, Main.getProperties().forcedTFPercentage+10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "FORCED_TF_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPercentage = Math.max(0, Main.getProperties().forcedTFPercentage-10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			

			id = "PREGNANCY_BREAST_GROWTH_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowth = Math.min(10, Main.getProperties().pregnancyBreastGrowth+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_BREAST_GROWTH_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowth = Math.max(0, Main.getProperties().pregnancyBreastGrowth-1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "PREGNANCY_BREAST_GROWTH_LIMIT_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowthLimit = Math.min(100, Main.getProperties().pregnancyBreastGrowthLimit+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_BREAST_GROWTH_LIMIT_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowthLimit = Math.max(0, Main.getProperties().pregnancyBreastGrowthLimit-1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			id = "PREGNANCY_LACTATION_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationIncrease = Math.min(1000, Main.getProperties().pregnancyLactationIncrease+50);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationIncrease = Math.max(0, Main.getProperties().pregnancyLactationIncrease-50);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "PREGNANCY_LACTATION_LIMIT_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationLimit = Math.min(Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), Main.getProperties().pregnancyLactationLimit+250);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_LIMIT_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationLimit = Math.max(0, Main.getProperties().pregnancyLactationLimit-250);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			id = "FORCED_FETISH_ON";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishPercentage = Math.min(100, Main.getProperties().forcedFetishPercentage+10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "FORCED_FETISH_OFF";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishPercentage = Math.max(0, Main.getProperties().forcedFetishPercentage-10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			// Forced TF Tendency setting events
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.NEUTRAL;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.NEUTRAL;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.NEUTRAL.getName(),
						ForcedTFTendency.NEUTRAL.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			
			
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.FEMININE;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.FEMININE;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.FEMININE.getName(),
						ForcedTFTendency.FEMININE.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.FEMININE_HEAVY;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.FEMININE_HEAVY;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.FEMININE_HEAVY.getName(),
						ForcedTFTendency.FEMININE_HEAVY.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.MASCULINE;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.MASCULINE;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.MASCULINE.getName(),
						ForcedTFTendency.MASCULINE.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.MASCULINE_HEAVY;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.MASCULINE_HEAVY;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.MASCULINE_HEAVY.getName(),
						ForcedTFTendency.MASCULINE_HEAVY.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			// Forced Fetish Tendency setting events
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.NEUTRAL;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.NEUTRAL;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.NEUTRAL.getName(),
						ForcedFetishTendency.NEUTRAL.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			
			
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.BOTTOM;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.BOTTOM;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.BOTTOM.getName(),
						ForcedFetishTendency.BOTTOM.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.BOTTOM_HEAVY;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.BOTTOM_HEAVY;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.BOTTOM_HEAVY.getName(),
						ForcedFetishTendency.BOTTOM_HEAVY.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.TOP;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.TOP;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.TOP.getName(),
						ForcedFetishTendency.TOP.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.TOP_HEAVY;
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.TOP_HEAVY;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.TOP_HEAVY.getName(),
						ForcedFetishTendency.TOP_HEAVY.getDescription());
				addEventListener(document, id, "mouseenter", el, false);
			}
		}
		
		// Save/load:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD) {
			for (File f : Main.getSavedGames()) {
				id = "overwrite_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.'));
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.overwriteConfirmationName.equals(f.getName())) {
							OptionsDialogue.overwriteConfirmationName = "";
							Main.saveGame(f.getName().substring(0, f.getName().lastIndexOf('.')), true);
						} else {
							OptionsDialogue.overwriteConfirmationName = f.getName();
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Overwrite", "");
					addEventListener(document, id, "mouseenter", el2, false);
				}
				id = "load_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.'));
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.loadConfirmationName.equals(f.getName())) {
							OptionsDialogue.loadConfirmationName = "";
							Main.loadGame(f.getName().substring(0, f.getName().lastIndexOf('.')));
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = f.getName();
							OptionsDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Load", "");
					addEventListener(document, id, "mouseenter", el2, false);
				}
				id = "delete_saved_" + f.getName().substring(0, f.getName().lastIndexOf('.'));
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
							OptionsDialogue.deleteConfirmationName = "";
							Main.deleteGame(f.getName().substring(0, f.getName().lastIndexOf('.')));
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Delete", "");
					addEventListener(document, id, "mouseenter", el2, false);
				}
			}
			id = "new_saved";
			if (((EventTarget) document.getElementById(id)) != null) {
				((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
					Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
					
				}, false);

				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Save", "");
				addEventListener(document, id, "mouseenter", el2, false);
			}
			id = "new_saved_disabled";
			if (((EventTarget) document.getElementById(id)) != null) {
				addEventListener(document, id, "mousemove", moveTooltipListener, false);
				addEventListener(document, id, "mouseleave", hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Save (Disabled)",
						(!Main.game.isStarted()
								?"You need to have started a game before you can save!"
								:"You cannot save the game unless you are in a tile's default scene!"));
				addEventListener(document, id, "mouseenter", el2, false);
			}
		}
		
		// Import:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.IMPORT_EXPORT) {
			for (File f : Main.getCharactersForImport()) {
				id = "delete_saved_character_" + f.getName().substring(0, f.getName().lastIndexOf('.'));
				if (((EventTarget) document.getElementById(id)) != null) {
					((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
							OptionsDialogue.deleteConfirmationName = "";
							Main.deleteExportedCharacter(f.getName().substring(0, f.getName().lastIndexOf('.')));
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					addEventListener(document, id, "mousemove", moveTooltipListener, false);
					addEventListener(document, id, "mouseleave", hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Delete", "");
					addEventListener(document, id, "mouseenter", el2, false);
				}
			}
			if (((EventTarget) document.getElementById("new_saved")) != null) {
				((EventTarget) document.getElementById("new_saved")).addEventListener("click", e -> {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
					Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
					
				}, false);
			}
		}
		
		if (Main.game.getCurrentDialogueNode() == CharacterCreation.IMPORT_CHOOSE) {
			for (File f : Main.getCharactersForImport()) {
				if (((EventTarget) document.getElementById("character_import_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )) != null) {
					((EventTarget) document.getElementById("character_import_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )).addEventListener("click", e -> {
						Main.importCharacter(f);
						
					}, false);
				}
			}
		}
		
		// Slave import:
		if (Main.game.getCurrentDialogueNode() == SlaverAlleyDialogue.AUCTION_IMPORT) {
			for (File f : Main.getSlavesForImport()) {
				if (((EventTarget) document.getElementById("import_slave_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )) != null) {
					((EventTarget) document.getElementById("import_slave_" + f.getName().substring(0, f.getName().lastIndexOf('.')) )).addEventListener("click", e -> {
						
						try {
							Game.importCharacterAsSlave(f.getName().substring(0, f.getName().lastIndexOf('.')));
							this.updateUI();
							Main.game.flashMessage(Colour.GENERIC_GOOD, "Imported Character!");
						
						} catch(Exception ex) {
							Main.game.flashMessage(Colour.GENERIC_BAD, "Import Failed!");
						}
						
							
					}, false);
				}
			}
		}
		if (Main.game.getCurrentDialogueNode() == SlaverAlleyDialogue.AUCTION_BLOCK_LIST) {
			for (NPC npc : Main.game.getCharactersPresent()) {
				id = npc.getId()+"_BID";
				if (((EventTarget) document.getElementById(id)) != null) {
					if(Main.game.getPlayer().isHasSlaverLicense()) {
						((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
							SlaverAlleyDialogue.setupBidding(npc);
							Main.game.setContent(new Response("", "", SlaverAlleyDialogue.AUCTION_BIDDING));
						}, false);
						
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								UtilText.parse(npc, "Bid on [npc.name]"),
								UtilText.parse(npc, "Start bidding on [npc.name]. There's a chance that the bidding might exceed [npc.her] value, so make sure you have enough money first!"));
						addEventListener(document, id, "mouseenter", el, false);
					} else {
						addEventListener(document, id, "mousemove", moveTooltipListener, false);
						addEventListener(document, id, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								UtilText.parse(npc, "Bid on [npc.name]"),
								UtilText.parse(npc, "You don't have a slaver license, so you're unable to big on any slaves!"));
						addEventListener(document, id, "mouseenter", el, false);
					}
				}
			}
		}
		
		setResponseEventListeners();
	}
	
	public void setResponseEventListeners() {
		
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
	
	private void setResponseTabListeners(int responsePageCounter) {
		String id = "tab_" + responsePageCounter;

		((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				Main.game.setResponseTab(responsePageCounter);
				Main.game.updateResponses();
			}, false);
		
//		addEventListener(document, id, "mousemove", responseTooltipListener, false);
//		addEventListener(document, id, "mouseleave", hideTooltipListener, false);
//		TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation(Main.game.getCurrentDialogueNode().getResponseTabTitle(responsePageCounter), "");
//		addEventListener(document, id, "mouseenter", el2, false);
	}
	
	private void allocateWorkTime(int i) {
		String id = i+"_WORK";
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setWorkHour(i, !Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().getWorkHours()[i]);
				Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
			}, false);
		}
	}
	
	private void setInventoryPageLeft(int i) {
		String id = "INV_PAGE_LEFT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				RenderingEngine.setPageLeft(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	private void setInventoryPageRight(int i) {
		String id = "INV_PAGE_RIGHT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				RenderingEngine.setPageRight(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	private void setBreastCountListener(int i) {
		String id = "BREAST_COUNT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				BodyChanging.getTarget().setBreastRows(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	private void setNippleCountListener(int i) {
		String id = "NIPPLE_COUNT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				BodyChanging.getTarget().setNippleCountPerBreast(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	private void setTesticleCountListener(int i) {
		String id = "TESTICLE_COUNT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				BodyChanging.getTarget().setTesticleCount(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
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
		
		// Inventory:
		// For all equipped clothing slots:
		String id;
		for (InventorySlot invSlot : InventorySlot.values()) {
			id = invSlot.toString() + "Slot";
			if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
				if (((EventTarget) documentAttributes.getElementById(id)) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(Main.game.getPlayer(),invSlot);
					addEventListener(documentAttributes, id, "click", el, false);
					addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, Main.game.getPlayer());
					addEventListener(documentAttributes, id, "mouseenter", el2, false);
				}
			} else {
				if (((EventTarget) documentAttributes.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(Main.game.getPlayer(),invSlot);
					addEventListener(documentAttributes, id, "click", el, false);
					addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, Main.game.getPlayer());
					addEventListener(documentAttributes, id, "mouseenter", el2, false);
				}
			}
		}

		id = "DATE_DISPLAY_TOGGLE";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			((EventTarget) documentAttributes.getElementById(id)).addEventListener("click", e -> {
				Main.getProperties().setValue(PropertyValue.calendarDisplay, !Main.getProperties().hasValue(PropertyValue.calendarDisplay));
				Main.saveProperties();
				this.updateUI();
			}, false);
			
			addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Toggle Calendar Display",
					"Toggle the date's display between a calendar and day count.");
			addEventListener(documentAttributes, id, "mouseenter", el2, false);
		}
		
		id = "TWENTY_FOUR_HOUR_TIME_TOGGLE";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			((EventTarget) documentAttributes.getElementById(id)).addEventListener("click", e -> {
				Main.getProperties().setValue(PropertyValue.twentyFourHourTime, !Main.getProperties().hasValue(PropertyValue.twentyFourHourTime));
				Main.saveProperties();
				this.updateUI();
			}, false);
			
			addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Toggle Time Display",
					"Toggle the display of time between a 24 and 12-hour clock.");
			addEventListener(documentAttributes, id, "mouseenter", el2, false);
		}
		
		if (((EventTarget) documentAttributes.getElementById("ESSENCE_" + TFEssence.ARCANE.hashCode())) != null) {
			addEventListener(documentAttributes, "ESSENCE_" + TFEssence.ARCANE.hashCode(), "mousemove", moveTooltipListener, false);
			addEventListener(documentAttributes, "ESSENCE_" + TFEssence.ARCANE.hashCode(), "mouseleave", hideTooltipListener, false);
			
			InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setEssence(TFEssence.ARCANE);
			addEventListener(documentAttributes, "ESSENCE_" + TFEssence.ARCANE.hashCode(), "mouseenter", el2, false);
		}
		
		
		
		Attribute[] attributes = {
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
			charactersBeingRendered.addAll(Sex.getDominantParticipants().keySet());
			charactersBeingRendered.addAll(Sex.getSubmissiveParticipants().keySet());
		} else if(Main.game.isInCombat()) {
			charactersBeingRendered.add(Main.game.getPlayer());
			charactersBeingRendered.addAll(Combat.getAllies());
		} else {
			if(Main.game.getPlayer()!=null) {
				charactersBeingRendered.add(Main.game.getPlayer());
				charactersBeingRendered.addAll(Main.game.getPlayer().getCompanions());
			}
		}
		
		for(GameCharacter character : charactersBeingRendered) {
			String idModifier = (character.isPlayer()?"PLAYER_":"NPC_"+character.getId()+"_");
			
			for (Attribute a : attributes) {
				if (((EventTarget) documentAttributes.getElementById(idModifier+a.getName())) != null) {
					if(a == Attribute.EXPERIENCE) {
						((EventTarget) documentAttributes.getElementById(idModifier+a.getName())).addEventListener("click", e -> {
							
							if(character.isPlayer()) {
								// block when in character creation
								if(Main.game.isInNewWorld()) {
									if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.PHONE) {
										if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_APPEARANCE) {
											openPhone();
										} else {
											Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_APPEARANCE));
										}
										
									} else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
										if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL) {
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
							if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.PHONE) {
								if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_LEVEL_UP) {
									openPhone();
								} else {
									Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_LEVEL_UP));
								}
								
							} else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
								if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL) {
									Main.game.saveDialogueNode();
								}
								
								Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_LEVEL_UP));
							}
						}
						
					} else { //TODO display NPC perk tree
						openCharactersPresent(character);
					}
				}, false);
				addEventListener(documentAttributes, idModifier+"ATTRIBUTES", "mousemove", moveTooltipListener, false);
				addEventListener(documentAttributes, idModifier+"ATTRIBUTES", "mouseleave", hideTooltipListener, false);
	
				TooltipInformationEventListener el = new TooltipInformationEventListener().setExtraAttributes(character);
				addEventListener(documentAttributes, idModifier+"ATTRIBUTES", "mouseenter", el, false);
			}
			
			// For status effect slots:
			for (StatusEffect se : character.getStatusEffects()) {
				if (((EventTarget) documentAttributes.getElementById("SE_"+idModifier + se)) != null) {
					addEventListener(documentAttributes, "SE_"+idModifier + se, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "SE_"+idModifier + se, "mouseleave", hideTooltipListener, false);
	
					TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, character);
					addEventListener(documentAttributes, "SE_"+idModifier + se, "mouseenter", el, false);
				}
			}
			
			
			for (Perk trait : character.getTraits()) {
				id = "TRAIT_" + idModifier + trait;
				if (((EventTarget) documentAttributes.getElementById(id)) != null) {
					addEventListener(documentAttributes, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, id, "mouseleave", hideTooltipListener, false);
					TooltipInformationEventListener el = new TooltipInformationEventListener().setPerk(trait, character);
					addEventListener(documentAttributes, id, "mouseenter", el, false);
				}
			}
			for (Fetish f : character.getFetishes()) {
				if (((EventTarget) documentAttributes.getElementById("FETISH_"+idModifier + f)) != null) {
					addEventListener(documentAttributes, "FETISH_"+idModifier + f, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "FETISH_"+idModifier + f, "mouseleave", hideTooltipListener, false);

					TooltipInformationEventListener el = new TooltipInformationEventListener().setFetish(f, character);
					addEventListener(documentAttributes, "FETISH_"+idModifier + f, "mouseenter", el, false);
				}
			}
			for (SpecialAttack sa : character.getSpecialAttacks()) {
				if (((EventTarget) documentAttributes.getElementById("SA_"+idModifier + sa)) != null) {
					addEventListener(documentAttributes, "SA_"+idModifier + sa, "mousemove", moveTooltipListener, false);
					addEventListener(documentAttributes, "SA_"+idModifier + sa, "mouseleave", hideTooltipListener, false);
	
					TooltipInformationEventListener el = new TooltipInformationEventListener().setSpecialAttack(sa, character);
					addEventListener(documentAttributes, "SA_"+idModifier + sa, "mouseenter", el, false);
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
	
	private void manageRightListeners() {
		documentRight = (Document) webEngineRight.executeScript("document");
		EventListenerDataMap.put(documentRight, new ArrayList<>());

		Map<InventorySlot, List<AbstractClothing>> concealedSlots = new HashMap<>();
		
		if(RenderingEngine.getCharacterToRender()!=null) {
			concealedSlots = RenderingEngine.getCharacterToRender().getInventorySlotsConcealed();
		}
		
		// Inventory:
		String id;
		for (InventorySlot invSlot : InventorySlot.values()) {
			id = invSlot.toString() + "Slot";
			if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					if(concealedSlots.keySet().contains(invSlot)) {
						addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
						TooltipInformationEventListener el2 = new TooltipInformationEventListener().setConcealedSlot(invSlot);
						addEventListener(documentRight, id, "mouseenter", el2, false);
						
					} else {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(RenderingEngine.getCharacterToRender(), invSlot);
						addEventListener(documentRight, id, "click", el, false);
						
						addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, RenderingEngine.getCharacterToRender());
						addEventListener(documentRight, id, "mouseenter", el2, false);
					}
				}
			} else {
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(RenderingEngine.getCharacterToRender(), invSlot);
					addEventListener(documentRight, id, "click", el, false);
					
					addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, RenderingEngine.getCharacterToRender());
					addEventListener(documentRight, id, "mouseenter", el2, false);
				}
			}
		}
		
		for(NPC character : Main.game.getCharactersPresent()) {
			id = "CHARACTERS_PRESENT_"+character.getId();
			if (((EventTarget) documentRight.getElementById(id)) != null) {
				((EventTarget) documentRight.getElementById(id)).addEventListener("click", e -> {
					openCharactersPresent(Main.game.getNPCById(character.getId()));
				}, false);
			}
		}
		if(Main.game.getPlayer()!=null) {
			// Weapons on floor:
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons().entrySet()) {
				id = "WEAPON_FLOOR_" + entry.getKey().hashCode();
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled()) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), null);
						addEventListener(documentRight, id, "click", el, false);
					}
					addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), null);
					addEventListener(documentRight, id, "mouseenter", el2, false);
				}
			}
			
			// Clothing on floor:
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
				id = "CLOTHING_FLOOR_" + entry.getKey().hashCode();
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled()) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), null);
						addEventListener(documentRight, id, "click", el, false);
					}
					addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), null, null);
					addEventListener(documentRight, id, "mouseenter", el2, false);
				}
			}
			
			// Items on floor:
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
				id = "ITEM_FLOOR_" + entry.getKey().hashCode();
				if (((EventTarget) documentRight.getElementById(id)) != null) {
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled()) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), null);
						addEventListener(documentRight, id, "click", el, false);
					}
					addEventListener(documentRight, id, "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, id, "mouseleave", hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), null, null);
					addEventListener(documentRight, id, "mouseenter", el2, false);	
				}
			}
		}
		
		if(RenderingEngine.getCharacterToRender()!=null) {
			Attribute[] attributes = {
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
				charactersBeingRendered.addAll(Sex.getDominantParticipants().keySet());
				charactersBeingRendered.addAll(Sex.getSubmissiveParticipants().keySet());
			} else if(Main.game.isInCombat()) {
				charactersBeingRendered.addAll(Combat.getEnemies());
			} else {
				charactersBeingRendered.add(RenderingEngine.getCharacterToRender());
			}
			charactersBeingRendered.remove(Main.game.getPlayer());
			
			for(GameCharacter character : charactersBeingRendered) {
				String idModifier = character.getId()+"_";
				
				for (Attribute a : attributes) {
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
	//				((EventTarget) documentRight.getElementById("NPC_"+idModifier+"ATTRIBUTES")).addEventListener("click", e -> {
	//					openCharactersPresent(Main.game.getNPCById(Main.game.getActiveNPC().getId()));
	//				}, false);
					addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mousemove", moveTooltipListener, false);
					addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mouseleave", hideTooltipListener, false);
		
					TooltipInformationEventListener el = new TooltipInformationEventListener().setExtraAttributes(character);
					addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mouseenter", el, false);
				}
				
				// For status effect slots:
				for (StatusEffect se : character.getStatusEffects()) {
					if (((EventTarget) documentRight.getElementById("SE_NPC_"+idModifier + se)) != null) {
						addEventListener(documentRight, "SE_NPC_"+idModifier + se, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, "SE_NPC_"+idModifier + se, "mouseleave", hideTooltipListener, false);
		
						TooltipInformationEventListener el = new TooltipInformationEventListener().setStatusEffect(se, character);
						addEventListener(documentRight, "SE_NPC_"+idModifier + se, "mouseenter", el, false);
					}
				}
				
				// For perk slots:
				for (Perk p : character.getMajorPerks()) {
					if (((EventTarget) documentRight.getElementById("PERK_NPC_"+idModifier + p)) != null) {
						addEventListener(documentRight, "PERK_NPC_"+idModifier + p, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, "PERK_NPC_"+idModifier + p, "mouseleave", hideTooltipListener, false);
		
						TooltipInformationEventListener el = new TooltipInformationEventListener().setPerk(p, character);
						addEventListener(documentRight, "PERK_NPC_"+idModifier + p, "mouseenter", el, false);
					}
				}
				for (Fetish f : character.getFetishes()) {
					if (((EventTarget) documentRight.getElementById("FETISH_NPC_"+idModifier + f)) != null) {
						addEventListener(documentRight, "FETISH_NPC_"+idModifier + f, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, "FETISH_NPC_"+idModifier + f, "mouseleave", hideTooltipListener, false);
	
						TooltipInformationEventListener el = new TooltipInformationEventListener().setFetish(f, character);
						addEventListener(documentRight, "FETISH_NPC_"+idModifier + f, "mouseenter", el, false);
					}
				}
				for (SpecialAttack sa : character.getSpecialAttacks()) {
					if (((EventTarget) documentRight.getElementById("SA_NPC_"+idModifier + sa)) != null) {
						addEventListener(documentRight, "SA_NPC_"+idModifier + sa, "mousemove", moveTooltipListener, false);
						addEventListener(documentRight, "SA_NPC_"+idModifier + sa, "mouseleave", hideTooltipListener, false);
		
						TooltipInformationEventListener el = new TooltipInformationEventListener().setSpecialAttack(sa, character);
						addEventListener(documentRight, "SA_NPC_"+idModifier + sa, "mouseenter", el, false);
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
		if(useJavascriptToSetContent
				 // For rendering images from file:
				&& !Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
				&& !Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER)
				&& !Main.game.getCurrentDialogueNode().equals(SlaveryManagementDialogue.SLAVE_MANAGEMENT_INSPECT)) {
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
	
	public void setRightPanelContent(String content) {
		if(useJavascriptToSetContent) {
			unbindListeners(documentRight);
			setWebEngineContent(webEngineRight, content);
			manageRightListeners();
		} else {
			webEngineRight.loadContent(content);
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
			Main.game.setContent(new Response("", "", DebugDialogue.DEBUG_MENU));
		}
		if (lastKeysEqual(KeyCode.N, KeyCode.O, KeyCode.X, KeyCode.X, KeyCode.X)) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SHOPPING_ARCADE_GENERIC_SHOP && !Main.game.getTestNPC().isSlave()) {
				Main.game.setActiveNPC(Main.game.getTestNPC());
				Main.game.setContent(new Response("", "", TestNPC.TEST_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTestNPC().setLocation(WorldType.SHOPPING_ARCADE, Main.game.getPlayer().getLocation(), true);
					}
				});
			}
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
			RenderingEngine.ENGINE.renderAttributesPanelLeft();
			RenderingEngine.ENGINE.renderAttributesPanelRight();
		}
		RenderingEngine.ENGINE.renderButtons();
	}
	
	public void updateUILeftPanel() {
		RenderingEngine.ENGINE.renderAttributesPanelLeft();
	}
	
	public void updateUIRightPanel() {
		RenderingEngine.ENGINE.renderAttributesPanelRight();
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
	public void moveGameWorld(WorldType worldType, PlaceType placeType, boolean setDefaultDialogue) {
		int timeToTranstition = Main.game.getActiveWorld().getWorldType().getTimeToTransition();

		Main.game.setActiveWorld(Main.game.getWorlds().get(worldType), placeType, true);
		
		Main.game.endTurn(timeToTranstition + Main.game.getActiveWorld().getWorldType().getTimeToTransition());
	}

	/**
	 * Moves the player North.
	 */
	public void moveNorth() {
		if (Main.game.getPlayer().getLocation().getY() + 1 < Main.game.getActiveWorld().WORLD_HEIGHT) {
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() + 1).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear()) {
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory(Util.newArrayListOfValues(new ListValue<>(Rarity.LEGENDARY)));
				}
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
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() - 1).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear()) {
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory(Util.newArrayListOfValues(new ListValue<>(Rarity.LEGENDARY)));
				}
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
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() + 1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear()) {
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory(Util.newArrayListOfValues(new ListValue<>(Rarity.LEGENDARY)));
				}
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
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX() - 1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear()) {
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory(Util.newArrayListOfValues(new ListValue<>(Rarity.LEGENDARY)));
				}
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

	public WebEngine getWebEngineTooltip() {
		return webEngineTooltip;
	}
	
	public WebEngine getWebEngineAttributes() {
		return webEngineAttributes;
	}
	
	public WebEngine getWebEngineRight() {
		return webEngineRight;
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
		if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			getWebEngineTooltip().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewTooltip_stylesheet.css").toExternalForm());
			getWebEngine().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webView_stylesheet.css").toExternalForm());
			getWebEngineButtons().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet.css").toExternalForm());
			getWebEngineAttributes().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
			getWebEngineRight().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewAttributes_stylesheet.css").toExternalForm());
	
			Main.mainScene.getStylesheets().clear();
			Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
			Main.primaryStage.setScene(Main.mainScene);
		} else {
			getWebEngineTooltip().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewTooltip_stylesheet_light.css").toExternalForm());
			getWebEngine().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webView_stylesheet_light.css").toExternalForm());
			getWebEngineButtons().setUserStyleSheetLocation(getClass().getResource("/com/lilithsthrone/res/css/webViewButtons_stylesheet_light.css").toExternalForm());
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
		this.flashMessageColour = flashMessageColour;
	}

	public String getFlashMessageText() {
		return flashMessageText;
	}

	public void setFlashMessageText(String flashMessageText) {
		this.flashMessageText = flashMessageText;
	}

}
