package com.base.world;

import java.io.Serializable;

import com.base.game.inventory.CharacterInventory;
import com.base.utils.Vector2i;
import com.base.world.places.PlaceInterface;

/**
 * @since 0.1.0
 * @version 0.1.75
 * @author Innoxia
 */
public class Cell implements Serializable {
	private static final long serialVersionUID = 1L;

	private WorldType type;

	private Vector2i location;

	private String name;
	private boolean discovered, northAccess, southAccess, eastAccess, westAccess, blocked;
	private PlaceInterface place;
	private CharacterInventory inventory;

	public Cell(WorldType type, Vector2i location) {
		this.type = type;
		this.location = location;
		
		name = "";
		discovered = false;
		place = type.getStandardPlace();
		
		inventory = new CharacterInventory(0);
		
		northAccess = false;
		southAccess = false;
		eastAccess = false;
		westAccess = false;
		blocked = false;
	}

	@Override
	public String toString() {
		return "Name: " + name;
	}

	public WorldType getType() {
		return type;
	}

	public void setType(WorldType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDiscovered() {
		return discovered;
	}

	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	public PlaceInterface getPlace() {
		return place;
	}

	public void setPlace(PlaceInterface place) {
		this.place = place;
	}

	public Vector2i getLocation() {
		return location;
	}

	public void setLocation(Vector2i location) {
		this.location = location;
	}

	public boolean isNorthAccess() {
		return northAccess;
	}

	public void setNorthAccess(boolean northAccess) {
		this.northAccess = northAccess;
	}

	public boolean isSouthAccess() {
		return southAccess;
	}

	public void setSouthAccess(boolean southAccess) {
		this.southAccess = southAccess;
	}

	public boolean isEastAccess() {
		return eastAccess;
	}

	public void setEastAccess(boolean eastAccess) {
		this.eastAccess = eastAccess;
	}

	public boolean isWestAccess() {
		return westAccess;
	}

	public void setWestAccess(boolean westAccess) {
		this.westAccess = westAccess;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	public void resetInventory(){
		this.inventory = new CharacterInventory(0);
	}

	public CharacterInventory getInventory() {
		return inventory;
	}

	public void setInventory(CharacterInventory inventory) {
		this.inventory = inventory;
	}
}
