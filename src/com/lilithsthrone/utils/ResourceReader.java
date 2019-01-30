package com.lilithsthrone.utils;

import com.lilithsthrone.game.inventory.weapon.WeaponType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ResourceReader {

	private static ResourceReader reader = new ResourceReader();

	static {
		//TODO Because weaponType gets loaded after ResourceReader, need to figure out a way to fix.
		WeaponType.allWeapons = new ArrayList<>();
		WeaponType.idToWeaponMap = new HashMap<>();
		WeaponType.weaponToIdMap = new HashMap<>();
		File mods = new File("res/mods");

		if (mods.exists() && mods.isDirectory()) {
			try {
				Files.walk(mods.toPath(), 4)
						.filter(path -> ResourceType.isResourceType(path.getParent().getFileName()))
						.map(path -> {
							String author = path.getParent().getParent().getParent().getFileName().toString();
							ResourceType type = ResourceType.getInstance(path.getParent().getFileName().toString());
							String name = path.getFileName().toString();
							return new Resource(author, type, name, path);
						})
						.forEach(Resource::create);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File res = new File("res");
		if (res.exists() && res.isDirectory()) {
			try {
				Files.walk(res.toPath(), 3)
						.filter(path -> {
							if (path.getParent() == null || path.getParent().getParent() == null) return false;
							return ResourceType.isResourceType(path.getParent().getParent().getFileName());
						})
						.map(path -> {
							Path author = path.getParent();
							ResourceType type = ResourceType.getInstance(author.getParent().getFileName().toString());
							String name = path.getFileName().toString();
							return new Resource(author.getFileName().toString(), type, name, path);
						})
						.forEach(Resource::create);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static ResourceReader getReader() {
		return reader;
	}
}
