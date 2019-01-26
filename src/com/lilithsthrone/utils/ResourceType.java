package com.lilithsthrone.utils;

import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;

import java.nio.file.Path;
import java.util.Map;

public enum ResourceType {
	CLOTHING("clothing", ClothingType.getClothingToIdMap(), ClothingType.getIdToClothingMap()) {
		@Override
		AbstractCoreType apply(Path p) {
			try {
				return new AbstractClothingType(p.toFile()) {};
			} catch (XMLLoadException e) {
				System.err.println(e.getMessage());
				return null;
			}
		}
	},
	WEAPON("weapons", WeaponType.weaponToIdMap, WeaponType.idToWeaponMap) {
		@Override
		AbstractCoreType apply(Path p) {
			try {
				return new AbstractWeaponType(p.toFile()) {};
			} catch (Exception e) {
				System.err.println("Loading modded weapon failed at 'WeaponType' Code 1. File path: " + p.toAbsolutePath());
				return null;
			}
		}
	},
	TATTOO("tattoos", TattooType.tattooToIdMap, TattooType.idToTattooMap) {
		@Override
		AbstractCoreType apply(Path p) {
			try {
				return new AbstractTattooType(p.toFile()) {};
			} catch (Exception e) {
				System.err.println("Loading modded tattoo failed in 'mod folder tattoos'. File path: " + p.toAbsolutePath());
				return null;
			}
		}
	};

	String folderName;
	Map<AbstractCoreType, String> itemToId;
	Map<String, AbstractCoreType> idToItem;

	ResourceType(String folderName, Map<AbstractCoreType, String> itemToId, Map<String, AbstractCoreType> idToItem) {
		this.folderName = folderName;
		this.idToItem = idToItem;
		this.itemToId = itemToId;
	}

	public static ResourceType getInstance(String folderName) {
		for (ResourceType type: ResourceType.values()) {
			if (type.folderName.equals(folderName)) return type;
		}
		return null;
	}

	public static boolean isResourceType(Path fileName) {
		for (ResourceType type: ResourceType.values()) {
			if (type.folderName.equals(fileName.toString())) return true;
		}
		return false;
	}

	abstract AbstractCoreType apply(Path p);
}
