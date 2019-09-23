package com.lilithsthrone.world;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public enum TeleportPermissions {

	NONE(false, false),
	
	INCOMING_ONLY(true, false),
	
	OUTGOING_ONLY(false, true),
	
	BOTH(true, true);
	
	private boolean incoming;
	private boolean outgoing;
	
	private TeleportPermissions(boolean incoming, boolean outgoing) {
		this.incoming = incoming;
		this.outgoing = outgoing;
	}

	public boolean isIncoming() {
		return incoming;
	}

	public boolean isOutgoing() {
		return outgoing;
	}
	
}
