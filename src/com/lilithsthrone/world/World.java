package com.lilithsthrone.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.7.3
 * @author Innoxia
 */
public class World implements XMLSaving {

	public final int WORLD_WIDTH;
	public final int WORLD_HEIGHT;
	public static final int CELL_SIZE = 64;
	
	private Cell[][] grid;
	private AbstractWorldType worldType;

	public World(int worldWidth, int worldHeight, Cell[][] grid, AbstractWorldType worldType) {
		WORLD_WIDTH = worldWidth;
		WORLD_HEIGHT = worldHeight;

		this.grid = grid;
		this.worldType = worldType;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("world");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "worldType", WorldType.getIdFromWorldType(this.getWorldType()));
		XMLUtil.addAttribute(doc, element, "width", String.valueOf(this.WORLD_WIDTH));
		XMLUtil.addAttribute(doc, element, "height", String.valueOf(this.WORLD_HEIGHT));
		
		Element innerElement = doc.createElement("grid");
		element.appendChild(innerElement);
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(!grid[i][j].getPlace().getPlaceType().equals(PlaceType.GENERIC_IMPASSABLE)) {
					grid[i][j].saveAsXML(innerElement, doc);
				}
			}
		}
		
		return element;
	}
	
	public static World loadFromXML(Element parentElement, Document doc) {
		AbstractWorldType type = WorldType.EMPTY;
		String worldType = parentElement.getAttribute("worldType");
		type = WorldType.getWorldTypeFromId(worldType);
		
		int width = Integer.valueOf(parentElement.getAttribute("width"));
		int height = Integer.valueOf(parentElement.getAttribute("height"));
		Cell[][] newGrid = new Cell[width][height];
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				newGrid[i][j] = new Cell(type, new Vector2i(i, j));
				newGrid[i][j].getPlace().setPlaceType(PlaceType.GENERIC_IMPASSABLE);
			}
		}
		
		NodeList cells = ((Element) parentElement.getElementsByTagName("grid").item(0)).getElementsByTagName("cell");
		for(int i = 0; i < cells.getLength(); i++){
			Element e = (Element) cells.item(i);
			
			Cell c = Cell.loadFromXML(e, doc, type);
			newGrid[c.getLocation().getX()][c.getLocation().getY()] = c;
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
		try {
			return grid[vec.getX()][vec.getY()];
		} catch(Exception ex) {
			System.err.println("Error in WorldType: "+WorldType.getIdFromWorldType(this.getWorldType()));
			throw ex;
		}
	}
	
	/**
	 * @param place The AbstractPlaceType to find a Cell of.
	 * @return A Cell of the PlaceType defined by the argument 'place'. If there are multiple Cells with the same PlaceType, the first one that is found is returned. Returns null if place type does not exist.
	 */
	public Cell getCell(AbstractPlaceType place) {
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place)) {
					return grid[i][j];
				}
			}
		}
		return null;
	}

	/**
	 * @param place The AbstractPlaceType to find all Cells of.
	 * @return A List of Cells of the PlaceType defined by the argument 'place'.
	 */
	public List<Cell> getCells(AbstractPlaceType place) {
		List<Cell> cellsFound = new ArrayList<>();
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place)) {
					cellsFound.add(grid[i][j]);
				}
			}
		}
		
		return cellsFound;
	}

	/**
	 * @param place The AbstractPlaceUpgrade to find all Cells of.
	 * @return A List of Cells which have the specified upgrade.
	 */
	public List<Cell> getCells(AbstractPlaceUpgrade placeUpgrade) {
		List<Cell> cellsFound = new ArrayList<>();
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceUpgrades().contains(placeUpgrade)) {
					cellsFound.add(grid[i][j]);
				}
			}
		}
		
		return cellsFound;
	}
	
	/**
	 * @param location The starting location from which to search for the place.
	 * @param place The place of the cell which is being looked for.
	 * @return The cell which has the 'place' place type that's closest to the starting location. Will return null if no cell with the defined place type is found.
	 */
	public Cell getClosestCell(Vector2i location, AbstractPlaceType place) {
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
	 * @param location The starting location from which to search for the place.
	 * @param place The place of the cell which is being looked for.
	 * @return The distance to the cell which has the 'place' place type that's closest to the starting location. Will return 10000 if no cell with the defined place type is found.
	 */
	public float getClosestCellDistance(Vector2i location, AbstractPlaceType place) {
		float distance = 10000f;
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place)) {
					float newDistance = Vector2i.getDistance(location, grid[i][j].getLocation());
					if(newDistance < distance) {
						distance = newDistance;
					}
				}
			}
		}
		return distance;
	}
	
	/**
	 * @param place The PlaceType to find a Cell of.
	 * @return A random, unoccupied Cell of the PlaceType defined by the argument 'place'. If there are no unoccupied Cells with this PlaceType, a random occupied one is returned instead.
	 */
	public Cell getRandomUnoccupiedCell(AbstractPlaceType place) {
		List<Cell> cells = new ArrayList<>();
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].getPlace().getPlaceType().equals(place) && Main.game.getCharactersPresent(grid[i][j]).isEmpty()) {
					cells.add(grid[i][j]);
				}
			}
		}
		if(cells.isEmpty()) {
//			if(Main.DEBUG) {
//				System.err.println("World.getRandomUnoccupiedCell() - No unoccupied cells found, occupied one returned instead.");
//			}
			return getRandomCell(place);
		}
		return cells.get(Util.random.nextInt(cells.size()));
	}
	

	public Cell getRandomCell(AbstractPlaceType place) {
		return getRandomCell(place, Util.random);
	}
	
	/**
	 * @param place The PlaceType to find a Cell of.
	 * @return A Cell of the PlaceType defined by the argument 'place'. If there are multiple Cells with the same PlaceType, a random one is returned.
	 */
	public Cell getRandomCell(AbstractPlaceType place, Random random) {
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
		
		return corridorCells.get(random.nextInt(corridorCells.size()));
	}
	
	public Cell[][] getCellGrid() {
		return grid;
	}

	public AbstractWorldType getWorldType() {
		return worldType;
	}

	public void setCellType(AbstractWorldType worldType) {
		this.worldType = worldType;
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}
}
