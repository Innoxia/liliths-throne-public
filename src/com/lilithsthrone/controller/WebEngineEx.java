package com.lilithsthrone.controller;

import javafx.scene.web.WebEngine;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A wrapper around WebEngine to manipulate HTML elements' styles and content
 * @author aimozg
 */
public class WebEngineEx {
	protected final WebEngine engine;
	protected boolean useJavascriptToSetContent = true;
	private Consumer<WebEngineEx> beforeContentUpdate = null;
	private Consumer<WebEngineEx> afterContentUpdate = null;

	public void setBeforeContentUpdate(Consumer<WebEngineEx> beforeContentUpdate) {
		this.beforeContentUpdate = beforeContentUpdate;
	}

	public void setAfterContentUpdate(Consumer<WebEngineEx> afterContentUpdate) {
		this.afterContentUpdate = afterContentUpdate;
	}

	public WebEngine getEngine() {
		return engine;
	}

	public WebEngineEx(WebEngine engine) {
		this.engine = engine;
	}

	protected void executeScriptForEverySelectedElement(String selector, String script) {
		engine.executeScript("" +
				"var a = document.querySelectorAll(" + escapeJsString(selector) + ");" +
				"for (var i=0,n=a.length; i<n; i++) {" + script + "}"
		);
	}

	protected void setStyleOf(String selector, String property, String value) {
		executeScriptForEverySelectedElement(selector,
				"a[i].style[" + escapeJsString(property) + "] = " + escapeJsString(value) + ";"
		);
	}
	protected void addClass(String selector, String classname) {
		executeScriptForEverySelectedElement(selector,
				"a[i].classList.add(" + escapeJsString(classname)+");"
		);
	}
	protected void removeClass(String selector, String classname) {
		executeScriptForEverySelectedElement(selector,
				"a[i].classList.remove(" + escapeJsString(classname)+");"
		);
	}
	protected void setContentOf(String selector, String content, boolean isHtml) {
		executeScriptForEverySelectedElement(selector,
					"a[i]."+ (isHtml ? "innerHTML" : "innerText") +" = "+ escapeJsString(content)+";"
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
		content = escapeJsString(content);
		if (beforeContentUpdate != null) beforeContentUpdate.accept(this);
		if (useJavascriptToSetContent) {
			engine.executeScript(""
					+ "document.open('text/html');"
					+ "document.write(" + content + ");"
					+ "document.close();");
		} else {
			engine.loadContent(content);
		}
		if (afterContentUpdate!= null) afterContentUpdate.accept(this);
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
