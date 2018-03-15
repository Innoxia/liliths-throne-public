package com.lilithsthrone.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 *
 */
public class World implements Serializable, XMLSaving {
	private static final long serialVersionUID = 1L;

	public final int WORLD_WIDTH, WORLD_HEIGHT;
	public static final int CELL_SIZE = 64;
	
	private Cell[][] grid;
	private WorldType worldType;
	private Map<GenericPlace, Vector2i> placesOfInterest;

	public World(int worldWidth, int worldHeight, Cell[][] grid, WorldType worldType) {
		WORLD_WIDTH = worldWidth;
		WORLD_HEIGHT = worldHeight;

		this.grid = grid;
		this.worldType = worldType;
		
		placesOfInterest = new HashMap<>();
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
		
		innerElement = doc.createElement("placesOfInterest");
		element.appendChild(innerElement);
		for(Entry<GenericPlace, Vector2i> entry : placesOfInterest.entrySet()) {
			Element e = doc.createElement("entry");
			innerElement.appendChild(e);
			
			Element place = doc.createElement("placeEntry");
			e.appendChild(place);
			entry.getKey().saveAsXML(place, doc);
			
			Element location = doc.createElement("locationEntry");
			e.appendChild(location);
			CharacterUtils.addAttribute(doc, location, "x", String.valueOf(entry.getValue().getX()));
			CharacterUtils.addAttribute(doc, location, "y", String.valueOf(entry.getValue().getY()));
		}
		
		return element;
	}
	
	public static World loadFromXML(Element parentElement, Document doc) {
		Cell[][] newGrid = new Cell[Integer.valueOf(parentElement.getAttribute("width"))][Integer.valueOf(parentElement.getAttribute("height"))];
		
		for(int i=0; i<((Element) parentElement.getElementsByTagName("grid").item(0)).getElementsByTagName("cell").getLength(); i++){
			Element e = (Element) ((Element) parentElement.getElementsByTagName("grid").item(0)).getElementsByTagName("cell").item(i);
			
			Cell c = Cell.loadFromXML(e, doc);
			newGrid[c.getLocation().getX()][c.getLocation().getY()] = c;
		}
		
		WorldType type = WorldType.EMPTY;
		if(parentElement.getAttribute("worldType").equals("SEWERS")) {
			type = WorldType.SUBMISSION;
		} else {
			type = WorldType.valueOf(parentElement.getAttribute("worldType"));
		}
		
		World world = new World(
				Integer.valueOf(parentElement.getAttribute("width")),
				Integer.valueOf(parentElement.getAttribute("height")),
				newGrid,
				type);
		
		for(int i=0; i<((Element) parentElement.getElementsByTagName("placesOfInterest").item(0)).getElementsByTagName("entry").getLength(); i++){
			Element e = (Element) ((Element) parentElement.getElementsByTagName("placesOfInterest").item(0)).getElementsByTagName("entry").item(i);
			
			world.addPlaceOfInterest(
					GenericPlace.loadFromXML((Element) ((Element)e.getElementsByTagName("placeEntry").item(0)).getElementsByTagName("place").item(0), doc),
					new Vector2i(
							Integer.valueOf(((Element)e.getElementsByTagName("locationEntry").item(0)).getAttribute("x")),
							Integer.valueOf(((Element)e.getElementsByTagName("locationEntry").item(0)).getAttribute("y"))));
		}
		
		return world;
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

	public Map<GenericPlace, Vector2i> getPlacesOfInterest() {
		return placesOfInterest;
	}
	
	public void addPlaceOfInterest(GenericPlace place, Vector2i vector) {
		placesOfInterest.put(place, vector);
	}
}
