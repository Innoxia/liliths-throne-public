package com.lilithsthrone.modding;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PluginUtils {
	public static Path GetModPath(Class<?> cls) {
		try {
			return Paths.get(
					new File(
						cls.getProtectionDomain()
						.getCodeSource()
						.getLocation()
						.toURI()).getPath()).getParent().getParent();
		} catch (URISyntaxException e) {
			return null;
		}
	}
}
