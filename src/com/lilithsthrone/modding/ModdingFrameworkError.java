package com.lilithsthrone.modding;

public class ModdingFrameworkError extends Exception {

	private String message;

	public ModdingFrameworkError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
