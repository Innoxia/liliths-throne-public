package com.lilithsthrone.utils;

import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class Resource {

	private String author;
	private ResourceType type;
	private String name;
	private Path path;

	Resource(String author, ResourceType type, String name, Path path) {
		this.author = author;
		this.type = type;
		this.name = name;
		this.path = path;
	}

	@Override
	public String toString() {
		return "Resource{" +
				"author='" + author + '\'' +
				", type=" + type +
				", name='" + name + '\'' +
				", path='" + path + '\'' +
				'}';
	}

	public void create() {
		try {
			Predicate<? super Path> xmlFilter = p -> p.toString().endsWith(".xml");
			switch (type) {
				case TATTOO:
					Files.walk(path)
							.filter(xmlFilter)
//							.map(type::apply)
//							.map(abstractCoreType -> (AbstractTattooType)abstractCoreType)
//							.filter(Objects::nonNull)
							.forEach(xmlPath -> {
								AbstractTattooType abstractTattooType = (AbstractTattooType) type.apply(xmlPath);
								String id = author+"_"+name+"_"+xmlPath.getFileName().toString().split("\\.")[0];
//								String id = author+"_"+name+"_"+abstractTattooType.getName().split("\\.")[0];
								addToMap(abstractTattooType, id);
							});
					break;
				case WEAPON:
					Files.walk(path)
							.filter(xmlFilter)
//							.map(type::apply)
//							.map(abstractCoreType -> (AbstractWeaponType)abstractCoreType)
//							.filter(Objects::nonNull)
							.forEach(xmlPath -> {
								AbstractWeaponType abstractWeaponType = (AbstractWeaponType) type.apply(xmlPath);
								assert(abstractWeaponType != null);
								WeaponType.getAllWeapons().add(abstractWeaponType);
								String id = author+"_"+name+"_"+xmlPath.getFileName().toString().split("\\.")[0];
//								String id = author+"_"+name+"_"+abstractWeaponType.getName().split("\\.")[0];
								addToMap(abstractWeaponType, id);
							});
					break;
				case CLOTHING:
					Files.walk(path)
							.filter(xmlFilter)
//							.map(type::apply)
//							.map(abstractCoreType -> (AbstractClothingType)abstractCoreType)
//							.filter(Objects::nonNull)
							.forEach(xmlPath -> {
								AbstractClothingType abstractClothingType = (AbstractClothingType) type.apply(xmlPath);
								assert(abstractClothingType != null);
								ClothingType.getAllClothing().add(abstractClothingType);
								String id = author+"_"+name+"_"+xmlPath.getFileName().toString().split("\\.")[0];
//								String id = author+"_"+name+"_"+abstractClothingType.getName().split("\\.")[0];
								addToMap(abstractClothingType, id);
							});
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addToMap(AbstractCoreType abstractWeaponType, String id) {
		type.itemToId.put(abstractWeaponType, id);
		type.idToItem.put(id, abstractWeaponType);
	}
}
