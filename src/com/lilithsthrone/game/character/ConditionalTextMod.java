package com.lilithsthrone.game.character;

public class ConditionalTextMod {

	private String conditionalString;
	private String textString;
	
	public ConditionalTextMod(String conditional, String text) {
		this.conditionalString = conditional;
		this.textString = text;
	}
	
	public String getConditionalString() {
		return conditionalString;
	}
	public String getConditionalString(boolean parsed) {
		if(parsed) {
			return conditionalString.replaceAll("\\+\\+", "&&");
		} else {
			return conditionalString;
		}
	}
	public void setConditionalString(String newConditionalString) {
		this.conditionalString = newConditionalString;
	}
	public String getTextString() {
		return textString;
	}
	public void setTextString(String newTextString) {
		this.textString = newTextString;
	}

}
