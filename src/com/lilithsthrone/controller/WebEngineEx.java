package com.lilithsthrone.controller;

import javafx.scene.web.WebEngine;

/**
 * A wrapper around WebEngine to manipulate HTML elements' styles and content
 * @author aimozg
 */
public class WebEngineEx {
	protected final WebEngine engine;

	public WebEngine getEngine() {
		return engine;
	}

	public WebEngineEx(WebEngine engine) {
		this.engine = engine;
	}

	protected void executeScriptForEverySelectedElement(String selector, String script) {
		engine.executeScript("" +
				"var a = document.querySelectorAll(" + WebEngineEx.escapeJsString(selector) + ");" +
				"for (var i=0,n=a.length; i<n; i++) {" + script + "}"
		);
	}

	protected void setStyleOf(String selector, String property, String value) {
		executeScriptForEverySelectedElement(selector,
				"a[i].style[" + WebEngineEx.escapeJsString(property) + "] = " + escapeJsString(value) + ";"
		);
	}
	protected void setContentOf(String selector, String content, boolean isHtml) {
		executeScriptForEverySelectedElement(selector,
					"a[i]."+ (isHtml ? "innerHTML" : "innerText") +" = "+ WebEngineEx.escapeJsString(content)+";"
		);
	}
	protected void setTextOf(String selector, String text) {
		setContentOf(selector,text,false);
	}
	protected void setHtmlOf(String selector, String html) {
		setContentOf(selector,html,true);
	}
	protected void removeElements(String selector) {
		executeScriptForEverySelectedElement(selector,
				"if (a[i].parentNode) a[i].parentNode.removeChild(a[i]);"
		);
	}
	protected void showElements(String selector) {
		setStyleOf(selector,"display","");
	}
	protected void hideElements(String selector) {
		setStyleOf(selector,"display","none");
	}

	public void hide() {
		setStyleOf("body","display","none");
	}
	public void show() {
		setStyleOf("body","display","");
	}
	public void setBodyContent(String content) {
		engine.executeScript(""
				+ "document.open('text/html');"
				+ "document.write("+ WebEngineEx.escapeJsString(content)+");"
				+ "document.close();");
	}

	/*
	 * UTILITIES
	 */

	public static String escapeJsString(String source) {
		source = source.replaceAll("\\\\","\\\\\\\\");
		source = source.replaceAll("\r","\\\\r");
		source = source.replaceAll("\n","\\\\n");
		source = source.replaceAll("\"","\\\\\"");
		return "\""+source+"\"";
	}

}
