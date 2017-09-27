package com.lilithsthrone.game;

import java.util.regex.Pattern;

import com.lilithsthrone.utils.Util;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Pimgd
 */
public class KeyCodeWithModifiers {
	
	private final KeyCode keyCode;
	private final boolean controlModifier;
	private final boolean shiftModifier;
	
	public static KeyCodeWithModifiers fromString(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		
		boolean usesShiftModifier = false;
		boolean usesControlModifier = false;
		KeyCode keyCode = null;
		if (!source.equals("+") && source.contains("+")) {
			String[] splitted = source.split(Pattern.quote("+"));
			for (int i = 0; i < splitted.length - 1; i++) {
				if ("SHIFT".equals(splitted[i])) {
					usesShiftModifier = true;
				} else if ("CTRL".equals(splitted[i])) {
					usesControlModifier = true;
				}
			}
			keyCode = KeyCode.valueOf(splitted[splitted.length -1]);
		} else {
			keyCode = KeyCode.valueOf(source);
		}
		
		return new KeyCodeWithModifiers(keyCode, usesControlModifier, usesShiftModifier);
	}
	
	public KeyCodeWithModifiers(KeyCode keyCode) {
		this(keyCode, false, false);
	}

	public KeyCodeWithModifiers(KeyCode keyCode, boolean controlModifier, boolean shiftModifier) {
		this.keyCode = keyCode;
		this.controlModifier = controlModifier;
		this.shiftModifier = shiftModifier;
	}

	public KeyCode getKeyCode() {
		return keyCode;
	}

	public boolean isControlModifier() {
		return controlModifier;
	}

	public boolean isShiftModifier() {
		return shiftModifier;
	}
	
	public boolean matches(KeyEvent keyEvent) {
		return keyEvent.getCode() == keyCode && 
				keyEvent.isControlDown() == controlModifier && 
				keyEvent.isShiftDown() == shiftModifier;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (controlModifier ? 1231 : 1237);
		result = prime * result + ((keyCode == null) ? 0 : keyCode.hashCode());
		result = prime * result + (shiftModifier ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyCodeWithModifiers other = (KeyCodeWithModifiers) obj;
		return keyCode == other.keyCode &&
				shiftModifier == other.shiftModifier &&
				controlModifier == other.controlModifier;
	}

	@Override
	public String toString() {
		return (shiftModifier ? "SHIFT+" : "")
				+ (controlModifier ? "CTRL+" : "")
				+ keyCode.toString();
	}
	
	public String getFullName() {
		return (shiftModifier ? "SHIFT+" : "")
				+ (controlModifier ? "CTRL+" : "")
				+ keyCode.getName();
	}
	
	public String asHotkey() {
		return (shiftModifier ? "SHIFT + " : "")
				+ (controlModifier ? "CTRL + " : "")
				+ Util.getKeyCodeCharacter(keyCode);
	}
}
