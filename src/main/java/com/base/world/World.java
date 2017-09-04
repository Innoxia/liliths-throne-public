package com.base.world;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.base.utils.Vector2i;
import com.base.world.places.PlaceInterface;

/**
 * @since 0.1.0
 * @version 0.1.75
 * @author Innoxia
 */
public class World implements Serializable {
	private static final long serialVersionUID = 1L;

	public final int WORLD_WIDTH, WORLD_HEIGHT;
	public final static int CELL_SIZE = 64;

	private Cell[][] grid;
	private WorldType WorldType;
	private Map<PlaceInterface, Vector2i> placesOfInterest;

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

	public Map<PlaceInterface, Vector2i> getPlacesOfInterest() {
		return placesOfInterest;
	}
	
	public void addPlaceOfInterest(PlaceInterface place, Vector2i vector) {
		placesOfInterest.put(place, vector);
	}
}
