package com.lilithsthrone.modding;

import org.w3c.dom.Element;

public final class PluginContributor {

	private String name;
	private String email = null;
	private String role;

	public static PluginContributor FromElement(Element contribElement) {
		PluginContributor contrib = new PluginContributor();
		contrib.name = contribElement.getAttribute("name");
		if(contribElement.hasAttribute("email"))
			contrib.email = contribElement.getAttribute("email");
		contrib.role = contribElement.getAttribute("role");
		return contrib;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}
}
