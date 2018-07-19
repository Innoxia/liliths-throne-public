package com.lilithsthrone.controller;

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
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterChangeEventListener;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.TestNPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
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
 * @version 0.2.6
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

	static WebEngine webEngine;
	private WebEngine webEngineTooltip;
	private WebEngine webEngineAttributes;
	private WebEngine webEngineRight;
	private WebEngine webEngineButtons;
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
		
		if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OPTIONS) {
			Main.game.restoreSavedContent();
			
		} else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
			DialogueNodeType currentDialogueNodeType = Main.game.getCurrentDialogueNode().getDialogueNodeType();
			if (currentDialogueNodeType == DialogueNodeType.NORMAL
					|| currentDialogueNodeType == DialogueNodeType.SLAVERY_MANAGEMENT
					|| (!Main.game.isInNewWorld() && currentDialogueNodeType != DialogueNodeType.CHARACTERS_PRESENT)) {
				Main.game.saveDialogueNode();
			}
			
			Main.game.setContent(new Response("", "", OptionsDialogue.MENU));
		}
	}

	public void openPhone() {
		if(!Main.game.isStarted() || !Main.game.isInNewWorld()) {
			return;
		}
		
		if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE)
			Main.game.restoreSavedContent();
		else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
			if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
					|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.SLAVERY_MANAGEMENT)
				Main.game.saveDialogueNode();

			Main.game.setContent(new Response("", "", PhoneDialogue.MENU));
		}
	}

	public boolean isInventoryDisabled() {
		if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.INVENTORY || Main.game.isInCombat() || Main.game.isInSex()) {
			return false;
			
		} else if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.OPTIONS || Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE) {
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
		
		if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.INVENTORY) {
			Main.game.restoreSavedContent();

		} else if (!isInventoryDisabled() || npc != null) {
			if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
					|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.SLAVERY_MANAGEMENT) {
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
			
			if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
					|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.SLAVERY_MANAGEMENT) {
				Main.game.saveDialogueNode();
			}
			
			CharactersPresentDialogue.resetContent(characterViewed);
			Main.game.setContent(new Response("", "", CharactersPresentDialogue.MENU));
			
		} else {
			if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.CHARACTERS_PRESENT) {
				Main.game.restoreSavedContent();
				
			} else if (!Main.game.getCharactersPresent().isEmpty()) {
	
				if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL
						|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.SLAVERY_MANAGEMENT) {
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
							 
//							 Main.game.getPlayer().setScar(InventorySlot.EYES, new Scar(ScarType.CLAW_MARKS, true));
//							 
//							 Tattoo tat = new Tattoo(TattooType.FLOWERS, Colour.CLOTHING_PINK, false, Colour.CLOTHING_GREEN, false, Colour.CLOTHING_YELLOW, false,
//									 new TattooWriting("Times I've been a Mommy", Colour.CLOTHING_WHITE, true, TattooWritingStyle.CURSIVE),
//									 new TattooCounter(TattooCounterType.PREGNANCY, TattooCountType.WRITTEN, Colour.CLOTHING_PURPLE, false));
//							 
//							 tat.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0));
//							 
//							 Main.game.getPlayer().addTattoo(InventorySlot.WRIST, tat);
//							 
//							 
//							 Main.game.getPlayer().addTattoo(InventorySlot.TORSO_OVER, new Tattoo(TattooType.TRIBAL, Colour.CLOTHING_BLACK, false, null, false, null, false,
//									 null,
//									 null));
							 
//							 System.out.println("Numeral:"+Util.intToNumerals(3788));
//							 System.out.println(Util.intToTally(56));
							 
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
									EnchantmentDialogue.saveEnchant(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
									Main.game.setContent(new Response("Save", "", Main.game.getCurrentDialogueNode()));
								}
							}
						}
						if(Main.game.getCurrentDialogueNode() == SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS_ADD
								|| Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.SLAVE_MANAGEMENT_TATTOOS_ADD
								|| Main.game.getCurrentDialogueNode() == CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD){
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
												Main.game.getActiveNPC().setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
											}
										});
									} else {
										Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
									}
									
								}
							}
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
	
	// Information:
	static CopyInfoEventListener copyInfoListener = new CopyInfoEventListener();
	
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

		// Buttons webview:

		webViewButtons.setContextMenuEnabled(false);
		webEngineButtons = webViewButtons.getEngine();
		webEngineButtons.setJavaScriptEnabled(false);
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
		MainControllerInitMethod.initMainControllerListeners();
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
	
	static void setMapLocationListeners(Cell c, int i, int j) { //TODO
		String id = "MAP_NODE_" + i + "_" + j;
		
		addEventListener(document, id, "mousemove", moveTooltipListener, false);
		addEventListener(document, id, "mouseleave", hideTooltipListener, false);
		
		TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(c.getPlaceName()), "");
		addEventListener(document, id, "mouseenter", el2, false);
		
		((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
			if(Main.game.getPlayer().isAbleToTeleport()
					&& Main.game.getSavedDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false))
					&& Main.game.getPlayer().getMana()>=Spell.TELEPORT.getModifiedCost(Main.game.getPlayer())
					&& c.getPlace().getPlaceType()!=PlaceType.GENERIC_IMPASSABLE) {
				Main.mainController.openPhone();
				Main.game.getPlayer().incrementMana(-Spell.TELEPORT.getModifiedCost(Main.game.getPlayer()));
				Main.game.getPlayer().setLocation(new Vector2i(j, i));
				DialogueNodeOld dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
				Main.game.getTextStartStringBuilder().append("<p>You teleport! :3</p>");
				Main.game.setContent(new Response("", "", dn));
			}
		}, false);
	}
	
	private static void setResponseTabListeners(int responsePageCounter) {
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
	
	static void allocateWorkTime(int i) {
		String id = i+"_WORK";
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setWorkHour(i, !Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().getWorkHours()[i]);
				Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
			}, false);
		}
	}
	
	static void setInventoryPageLeft(int i) {
		String id = "INV_PAGE_LEFT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				RenderingEngine.setPageLeft(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
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
	
	static void setBreastCountListener(int i) {
		String id = "BREAST_COUNT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				BodyChanging.getTarget().setBreastRows(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	static void setNippleCountListener(int i) {
		String id = "NIPPLE_COUNT_"+i;
		if (((EventTarget) document.getElementById(id)) != null) {
			((EventTarget) document.getElementById(id)).addEventListener("click", e -> {
				BodyChanging.getTarget().setNippleCountPerBreast(i);
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	static void setTesticleCountListener(int i) {
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
					if(!RenderingEngine.ENGINE.isRenderingTattoosLeft()) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(Main.game.getPlayer(), invSlot);
						addEventListener(documentAttributes, id, "click", el, false);
					}
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

		id = "DATE_DISPLAY_TOGGLE";
		if (((EventTarget) documentAttributes.getElementById(id)) != null) {
			((EventTarget) documentAttributes.getElementById(id)).addEventListener("click", e -> {
				Main.getProperties().setValue(PropertyValue.calendarDisplay, !Main.getProperties().hasValue(PropertyValue.calendarDisplay));
				Main.saveProperties();
				MainController.updateUI();
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
				MainController.updateUI();
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
									if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE) {
										if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_APPEARANCE) {
											openPhone();
										} else {
											Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_APPEARANCE));
										}
										
									} else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
										if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL) {
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
							if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE) {
								if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_LEVEL_UP) {
									openPhone();
								} else {
									Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_LEVEL_UP));
								}
								
							} else if (!Main.game.getCurrentDialogueNode().isOptionsDisabled()) {
								if (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.NORMAL) {
									Main.game.saveDialogueNode();
								}
								
								Main.game.setContent(new Response("", "", PhoneDialogue.CHARACTER_LEVEL_UP));
							}
						}
						
					} else { //TODO display NPC perk tree
						if(Main.game.isInSex()) {
							Sex.setActivePartner((NPC) character);
							Sex.recalculateSexActions();
							updateUI();
							Main.game.updateResponses();
								
						} else if(Main.game.isInCombat()) {
							Combat.setTargetedCombatant((NPC) character);
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
						if(!RenderingEngine.ENGINE.isRenderingTattoosRight()) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(RenderingEngine.getCharacterToRender(), invSlot);
							addEventListener(documentRight, id, "click", el, false);
						}
						
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
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY) {
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
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY) {
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
					if(!Main.game.getCurrentDialogueNode().isInventoryDisabled() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY) {
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
		
		Attribute[] attributes = {
				Attribute.HEALTH_MAXIMUM,
				Attribute.MANA_MAXIMUM,
				Attribute.EXPERIENCE,
				Attribute.MAJOR_PHYSIQUE,
				Attribute.MAJOR_ARCANE,
				Attribute.MAJOR_CORRUPTION,
				Attribute.AROUSAL,
				Attribute.LUST };
		if(!RenderingEngine.ENGINE.isRenderingCharactersRightPanel()) {
			attributes = new Attribute[] {Attribute.EXPERIENCE};
		}
		
		List<GameCharacter> charactersBeingRendered = new ArrayList<>();
		if(Main.game.isInSex()) {
			charactersBeingRendered.addAll(Sex.getDominantParticipants().keySet());
			charactersBeingRendered.addAll(Sex.getSubmissiveParticipants().keySet());
			
		} else if(Main.game.isInCombat()) {
			charactersBeingRendered.addAll(Combat.getEnemies());
			
		} else if(RenderingEngine.ENGINE.isRenderingCharactersRightPanel()) {
			charactersBeingRendered.add(RenderingEngine.getCharacterToRender());
			
		} else {
			charactersBeingRendered.addAll(Main.game.getCharactersPresent());
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
				if(!RenderingEngine.ENGINE.isRenderingCharactersRightPanel()) {
					((EventTarget) documentRight.getElementById("NPC_"+idModifier+"ATTRIBUTES")).addEventListener("click", e -> {
						openCharactersPresent(character);
					}, false);
				} else if(Main.game.isInSex()) {
					((EventTarget) documentRight.getElementById("NPC_"+idModifier+"ATTRIBUTES")).addEventListener("click", e -> {
						Sex.setActivePartner((NPC) character);
						Sex.recalculateSexActions();
						updateUI();
						Main.game.updateResponses();
					}, false);
				} else if(Main.game.isInCombat()) {
					((EventTarget) documentRight.getElementById("NPC_"+idModifier+"ATTRIBUTES")).addEventListener("click", e -> {
						Combat.setTargetedCombatant((NPC) character);
						updateUI();
						Main.game.updateResponses();
					}, false);
				}
				addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mousemove", moveTooltipListener, false);
				addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mouseleave", hideTooltipListener, false);
	
				TooltipInformationEventListener el = new TooltipInformationEventListener().setExtraAttributes(character);
				addEventListener(documentRight, "NPC_"+idModifier+"ATTRIBUTES", "mouseenter", el, false);
			}
			
			if(RenderingEngine.ENGINE.isRenderingCharactersRightPanel()) {
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
			if(Main.game.isInNewWorld() && Main.game.isPrologueFinished()) {
				Main.game.setContent(new Response("", "", DebugDialogue.DEBUG_MENU));
			} else {
				Main.game.flashMessage(Colour.GENERIC_BAD, "Unavailable in prologue!");
			}
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
	public static void updateUI() {
		if (Main.game.isRenderAttributesSection()) {
			RenderingEngine.ENGINE.renderAttributesPanelLeft();
			RenderingEngine.ENGINE.renderAttributesPanelRight();
		}
		RenderingEngine.ENGINE.renderButtons();
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

		Main.game.setActiveWorld(Main.game.getWorlds().get(worldType), placeType, setDefaultDialogue);
		
		Main.game.endTurn(timeToTranstition + Main.game.getActiveWorld().getWorldType().getTimeToTransition());
	}

	/**
	 * Moves the player North.
	 */
	public void moveNorth() {
		if (Main.game.getPlayer().getLocation().getY() + 1 < Main.game.getActiveWorld().WORLD_HEIGHT) {
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY() + 1).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
				if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().isItemsDisappear()) {
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory(Util.newArrayListOfValues(Rarity.LEGENDARY));
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
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory(Util.newArrayListOfValues(Rarity.LEGENDARY));
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
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory(Util.newArrayListOfValues(Rarity.LEGENDARY));
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
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).resetInventory(Util.newArrayListOfValues(Rarity.LEGENDARY));
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
		MainController.flashMessageColour = flashMessageColour;
	}

	public String getFlashMessageText() {
		return flashMessageText;
	}

	public void setFlashMessageText(String flashMessageText) {
		MainController.flashMessageText = flashMessageText;
	}

}
