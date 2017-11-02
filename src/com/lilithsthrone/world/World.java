package com.lilithsthrone.world;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceInterface;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public class World implements Serializable {
	private static final long serialVersionUID = 1L;

	public final int WORLD_WIDTH, WORLD_HEIGHT;
	public static final int CELL_SIZE = 64;
	
	private Cell[][] grid;
	private WorldType WorldType;
	private Map<GenericPlace, Vector2i> placesOfInterest;

	public World(int worldWidth, int worldHeight, Cell[][] grid, WorldType WorldType) {
		WORLD_WIDTH = worldWidth;
		WORLD_HEIGHT = worldHeight;

		this.grid = grid;
		this.WorldType = WorldType;
		
		placesOfInterest = new HashMap<>();
	}

	public Cell getCell(int i, int j) {
		return grid[i][j];
	}

	public Cell getCell(Vector2i vec) {
		return grid[vec.getX()][vec.getY()];
	}
	
	public Cell getCell(PlaceInterface place) {
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place)) {
					return grid[i][j];
				}
			}
		}
		return null;
	}

	public Cell[][] getCellGrid() {
		return grid;
	}

	public WorldType getWorldType() {
		return WorldType;
	}

	public void setCellType(WorldType cellType) {
		this.WorldType = cellType;
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

	public Map<GenericPlace, Vector2i> getPlacesOfInterest() {
		return placesOfInterest;
	}
	
	public void addPlaceOfInterest(GenericPlace place, Vector2i vector) {
		placesOfInterest.put(place, vector);
	}
}
