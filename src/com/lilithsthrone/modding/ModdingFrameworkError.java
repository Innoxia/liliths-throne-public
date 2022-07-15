package com.lilithsthrone.modding;

public class ModdingFrameworkError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6543166493295212957L;
	private String message;

	public ModdingFrameworkError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
