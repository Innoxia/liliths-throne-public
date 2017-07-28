package com.base.game;

import javafx.scene.input.KeyCode;

/**
 * @since 0.1.61
 * @version 0.1.7
 * @author Innoxia
 */
public enum KeyboardAction {

	MENU("Menu", KeyCode.ESCAPE, null),

	MOVE_NORTH("Move North", KeyCode.W, KeyCode.UP),
	MOVE_EAST("Move East", KeyCode.D, KeyCode.RIGHT),
	MOVE_SOUTH("Move South", KeyCode.S, KeyCode.DOWN),
	MOVE_WEST("Move West", KeyCode.A, KeyCode.LEFT),

	QUICKSAVE("Quicksave", KeyCode.F5, null),
	QUICKLOAD("Quickload", KeyCode.F9, null),

	MENU_SELECT("Select", KeyCode.ENTER, null),
	INVENTORY("Inventory", KeyCode.I, null),
	JOURNAL("Phone", KeyCode.P, KeyCode.J),
	CHARACTERS("Characters", KeyCode.C, null),
	ZOOM("Zoom", KeyCode.Z, null),

	SCROLL_UP("Scroll Up", KeyCode.PAGE_UP, null),
	SCROLL_DOWN("Scroll Down", KeyCode.PAGE_DOWN, null),

	RESPOND_1("Response 1", KeyCode.DIGIT1, null),
	RESPOND_2("Response 2", KeyCode.DIGIT2, null),
	RESPOND_3("Response 3", KeyCode.DIGIT3, null),
	RESPOND_4("Response 4", KeyCode.DIGIT4, null),
	RESPOND_5("Response 5", KeyCode.DIGIT5, null),
	RESPOND_6("Response 6", KeyCode.DIGIT6, null),
	RESPOND_7("Response 7", KeyCode.DIGIT7, null),
	RESPOND_8("Response 8", KeyCode.DIGIT8, null),
	RESPOND_9("Response 9", KeyCode.DIGIT9, null),
	RESPOND_0("Response 0", KeyCode.DIGIT0, KeyCode.SPACE),

	RESPOND_NEXT_PAGE("Next Response Page", KeyCode.E, null),
	RESPOND_PREVIOUS_PAGE("Previous Response Page", KeyCode.Q, null);

	private String name;
	private KeyCode primaryDefault, secondaryDefault;

	private KeyboardAction(String name, KeyCode primaryDefault, KeyCode secondaryDefault) {
		this.name = name;
		this.primaryDefault = primaryDefault;
		this.secondaryDefault = secondaryDefault;
	}

	public String getName() {
		return name;
	}

	public KeyCode getPrimaryDefault() {
		return primaryDefault;
	}

	public KeyCode getSecondaryDefault() {
		return secondaryDefault;
	}
}