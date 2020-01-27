package com.lilithsthrone.controller;
import org.w3c.dom.events.EventListener;

/**
 * @since 0.1.075
 * @version 0.1.75
 * @author Innoxia
 */
public class EventListenerData {
	public String ID, type;
	public EventListener listener;
	public boolean useCapture;
	
	public EventListenerData(String ID, String type, EventListener listener, boolean useCapture) {
		this.ID = ID;
		this.type = type;
		this.listener = listener;
		this.useCapture = useCapture;
	}
	
}
