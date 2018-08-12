package com.lilithsthrone.game.settings;

import javafx.scene.input.KeyCode;

/**
 * @since 0.1.61
 * @version 0.1.89
 * @author Innoxia
 */
public enum KeyboardAction {

	MENU("Menu", new KeyCodeWithModifiers(KeyCode.ESCAPE), null),

	MOVE_NORTH("Move North", new KeyCodeWithModifiers(KeyCode.W), new KeyCodeWithModifiers(KeyCode.UP)),
	MOVE_WEST("Move West", new KeyCodeWithModifiers(KeyCode.A), new KeyCodeWithModifiers(KeyCode.LEFT)),
	MOVE_SOUTH("Move South", new KeyCodeWithModifiers(KeyCode.S), new KeyCodeWithModifiers(KeyCode.DOWN)),
	MOVE_EAST("Move East", new KeyCodeWithModifiers(KeyCode.D), new KeyCodeWithModifiers(KeyCode.RIGHT)),
	
	MOVE_RESPONSE_CURSOR_NORTH("Response Cursor Up", new KeyCodeWithModifiers(KeyCode.W, false, true), new KeyCodeWithModifiers(KeyCode.UP, false, true)),
	MOVE_RESPONSE_CURSOR_WEST("Response Cursor Left", new KeyCodeWithModifiers(KeyCode.A, false, true), new KeyCodeWithModifiers(KeyCode.LEFT, false, true)),
	MOVE_RESPONSE_CURSOR_SOUTH("Response Cursor Down", new KeyCodeWithModifiers(KeyCode.S, false, true), new KeyCodeWithModifiers(KeyCode.DOWN, false, true)),
	MOVE_RESPONSE_CURSOR_EAST("Response Cursor Right", new KeyCodeWithModifiers(KeyCode.D, false, true), new KeyCodeWithModifiers(KeyCode.RIGHT, false, true)),

	QUICKSAVE("Quicksave", new KeyCodeWithModifiers(KeyCode.F5), null),
	QUICKLOAD("Quickload", new KeyCodeWithModifiers(KeyCode.F9), null),

	MENU_SELECT("Select", new KeyCodeWithModifiers(KeyCode.SPACE), new KeyCodeWithModifiers(KeyCode.ENTER)),
	INVENTORY("Inventory", new KeyCodeWithModifiers(KeyCode.I), new KeyCodeWithModifiers(KeyCode.TAB)),
	JOURNAL("Phone", new KeyCodeWithModifiers(KeyCode.M), new KeyCodeWithModifiers(KeyCode.H)),
	CHARACTERS("Characters", new KeyCodeWithModifiers(KeyCode.C), null),
	ZOOM("Zoom", new KeyCodeWithModifiers(KeyCode.Z), null),

	SCROLL_UP("Scroll Up", new KeyCodeWithModifiers(KeyCode.PAGE_UP), null),
	SCROLL_DOWN("Scroll Down", new KeyCodeWithModifiers(KeyCode.PAGE_DOWN), null),

	RESPOND_1("Response 1", new KeyCodeWithModifiers(KeyCode.DIGIT1), null),
	RESPOND_2("Response 2", new KeyCodeWithModifiers(KeyCode.DIGIT2), null),
	RESPOND_3("Response 3", new KeyCodeWithModifiers(KeyCode.DIGIT3), null),
	RESPOND_4("Response 4", new KeyCodeWithModifiers(KeyCode.DIGIT4), null),
	RESPOND_5("Response 5", new KeyCodeWithModifiers(KeyCode.DIGIT5), null),
	RESPOND_6("Response 6", new KeyCodeWithModifiers(KeyCode.DIGIT1, false, true), null),
	RESPOND_7("Response 7", new KeyCodeWithModifiers(KeyCode.DIGIT2, false, true), null),
	RESPOND_8("Response 8", new KeyCodeWithModifiers(KeyCode.DIGIT3, false, true), null),
	RESPOND_9("Response 9", new KeyCodeWithModifiers(KeyCode.DIGIT4, false, true), null),
	RESPOND_10("Response 10", new KeyCodeWithModifiers(KeyCode.DIGIT5, false, true), null),
	RESPOND_11("Response 11", new KeyCodeWithModifiers(KeyCode.DIGIT1, true, false), null),
	RESPOND_12("Response 12", new KeyCodeWithModifiers(KeyCode.DIGIT2, true, false), null),
	RESPOND_13("Response 13", new KeyCodeWithModifiers(KeyCode.DIGIT3, true, false), null),
	RESPOND_14("Response 14", new KeyCodeWithModifiers(KeyCode.DIGIT4, true, false), null),
	RESPOND_0("Response 15", new KeyCodeWithModifiers(KeyCode.DIGIT5, true, false), new KeyCodeWithModifiers(KeyCode.BACK_SPACE)),

	RESPOND_NEXT_TAB("Next Response Tab", new KeyCodeWithModifiers(KeyCode.E, false, true), null),
	RESPOND_PREVIOUS_TAB("Previous Response Tab", new KeyCodeWithModifiers(KeyCode.Q, false, true), null),
	
	RESPOND_NEXT_PAGE("Next Response Page", new KeyCodeWithModifiers(KeyCode.E), null),
	RESPOND_PREVIOUS_PAGE("Previous Response Page", new KeyCodeWithModifiers(KeyCode.Q), null);

	private String name;
	private KeyCodeWithModifiers primaryDefault, secondaryDefault;

	private KeyboardAction(String name, KeyCodeWithModifiers primaryDefault, KeyCodeWithModifiers secondaryDefault) {
		this.name = name;
		this.primaryDefault = primaryDefault;
		this.secondaryDefault = secondaryDefault;
	}

	public String getName() {
		return name;
	}

	public KeyCodeWithModifiers getPrimaryDefault() {
		return primaryDefault;
	}

	public KeyCodeWithModifiers getSecondaryDefault() {
		return secondaryDefault;
	}
}