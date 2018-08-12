package com.lilithsthrone.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.10
 * @author Innoxia
 */
public class World implements Serializable, XMLSaving {
	private static final long serialVersionUID = 1L;

	public final int WORLD_WIDTH, WORLD_HEIGHT;
	public static final int CELL_SIZE = 64;
	
	private Cell[][] grid;
	private WorldType worldType;

	public World(int worldWidth, int worldHeight, Cell[][] grid, WorldType worldType) {
		WORLD_WIDTH = worldWidth;
		WORLD_HEIGHT = worldHeight;

		this.grid = grid;
		this.worldType = worldType;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("world");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "worldType", this.getWorldType().toString());
		CharacterUtils.addAttribute(doc, element, "width", String.valueOf(this.WORLD_WIDTH));
		CharacterUtils.addAttribute(doc, element, "height", String.valueOf(this.WORLD_HEIGHT));
		
		Element innerElement = doc.createElement("grid");
		element.appendChild(innerElement);
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j].saveAsXML(innerElement, doc);
			}
		}
		
		return element;
	}
	
	public static World loadFromXML(Element parentElement, Document doc) {
		int width = Integer.valueOf(parentElement.getAttribute("width"));
		int height = Integer.valueOf(parentElement.getAttribute("height"));
		Cell[][] newGrid = new Cell[width][height];
		NodeList cells = ((Element) parentElement.getElementsByTagName("grid").item(0)).getElementsByTagName("cell");
		for(int i = 0; i < cells.getLength(); i++){
			Element e = (Element) cells.item(i);
			
			Cell c = Cell.loadFromXML(e, doc);
			newGrid[c.getLocation().getX()][c.getLocation().getY()] = c;
		}
		
		WorldType type = WorldType.EMPTY;
		String worldType = parentElement.getAttribute("worldType");
		if(worldType.equals("SEWERS")) {
			type = WorldType.SUBMISSION;
		} else {
			type = WorldType.valueOf(worldType);
		}
		
		return new World(width, height, newGrid, type);
	}

	public Cell getCell(int i, int j) {
		if(i<0 || j<0 || i >= WORLD_WIDTH || j >= WORLD_HEIGHT) {
			return null;
		}
		return grid[i][j];
	}

	public Cell getCell(Vector2i vec) {
		return grid[vec.getX()][vec.getY()];
	}
	
	/**
	 * @param place The PlaceType to find a Cell of.
	 * @return A Cell of the PlaceType defined by the argument 'place'. If there are multiple Cells with the same PlaceType, the first one that is found is returned.
	 */
	public Cell getCell(PlaceType place) {
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place)) {
					return grid[i][j];
				}
			}
		}
		return null;
	}
	
	public Cell getClosestCell(Vector2i location, PlaceType place) {
		float distance = 10000f;
		Cell closestCell = null;
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place)) {
					float newDistance = Vector2i.getDistance(location, grid[i][j].getLocation());
					if(newDistance < distance) {
						closestCell = grid[i][j];
						distance = newDistance;
					}
				}
			}
		}
		return closestCell;
	}
	
	/**
	 * @param place The PlaceType to find a Cell of.
	 * @return A random, unoccupied Cell of the PlaceType defined by the argument 'place'. If there are no unoccupied Cells with this PlaceType, a random occupied one is returned instead.
	 */
	public Cell getRandomUnoccupiedCell(PlaceType place) {
		List<Cell> cells = new ArrayList<>();
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place) && Main.game.getCharactersPresent(grid[i][j]).isEmpty()) {
					cells.add(grid[i][j]);
				}
			}
		}
		if(cells.isEmpty()) {
			System.err.println("World.getRandomUnoccupiedCell() - No unoccupied cells found, occupied one returned instead.");
			return getRandomCell(place);
		}
		return cells.get(Util.random.nextInt(cells.size()));
	}
	
	/**
	 * @param place The PlaceType to find a Cell of.
	 * @return A Cell of the PlaceType defined by the argument 'place'. If there are multiple Cells with the same PlaceType, a random one is returned.
	 */
	public Cell getRandomCell(PlaceType place) {
		List<Cell> corridorCells = new ArrayList<>();
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place)) {
					corridorCells.add(grid[i][j]);
				}
			}
		}
		if(corridorCells.isEmpty()) {
			return null;
		}
		
		return corridorCells.get(Util.random.nextInt(corridorCells.size()));
	}
	
	public Cell getNearestCell(PlaceType place, Vector2i startLocation) {
		Cell nearestCell = null;
		float closestDistance = 10000f;
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place)) {
					float distance = (float) Math.sqrt(Math.pow(Math.abs(i-startLocation.getX()), 2) + Math.pow(Math.abs(j-startLocation.getY()), 2));
					if(distance < closestDistance) {
						nearestCell = grid[i][j];
						closestDistance = distance;
					}
				}
			}
		}
		
		return nearestCell;
	}
	

	public Cell[][] getCellGrid() {
		return grid;
	}

	public WorldType getWorldType() {
		return worldType;
	}

	public void setCellType(WorldType cellType) {
		this.worldType = cellType;
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}
}
