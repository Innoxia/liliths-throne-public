package com.lilithsthrone.world;

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
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

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
		
		CharacterUtils.addAttribute(doc, element, "worldType", WorldType.getIdFromWorldType(this.getWorldType()));
		CharacterUtils.addAttribute(doc, element, "width", String.valueOf(this.WORLD_WIDTH));
		CharacterUtils.addAttribute(doc, element, "height", String.valueOf(this.WORLD_HEIGHT));
		
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
			System.err.println("Error in WorldType: "+this.getWorldType());
			throw ex;
		}
	}
	
	/**
	 * @param place The AbstractPlaceType to find a Cell of.
	 * @return A Cell of the PlaceType defined by the argument 'place'. If there are multiple Cells with the same PlaceType, the first one that is found is returned.
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
	 * @param place The PlaceUpgrade to find all Cells of.
	 * @return A List of Cells which have the specified upgrade.
	 */
	public List<Cell> getCells(PlaceUpgrade placeUpgrade) {
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
	
	/**
	 * @param place The PlaceType to find a Cell of.
	 * @return A Cell of the PlaceType defined by the argument 'place'. If there are multiple Cells with the same PlaceType, a random one is returned.
	 */
	public Cell getRandomCell(AbstractPlaceType place) {
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
	
	public Cell getNearestCell(AbstractPlaceType place, Vector2i startLocation) {
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
