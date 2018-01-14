package com.lilithsthrone.world;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

import javafx.concurrent.Task;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public class Generation extends Task<Boolean> {
	
	/* TODO
	 * Only supports an even number for world sizes.
	 */

	private Random rnd;
	private boolean debug = false;

	public Generation() {
		rnd = new Random();
	}

	@Override
	public Boolean call() {
		int maxSize = WorldType.values().length;
		int count = 0;
		
		for(WorldType wt : WorldType.values()) {
			worldGeneration(wt);
			count++;
			updateProgress(count, maxSize);
		}
		
		return true;
	}

	public void worldGeneration(WorldType worldType) {
		
		if(worldType.isUsesFile()) {
			try {
				BufferedImage img = ImageIO.read((getClass().getResource(worldType.getFileLocation())));
				
				World world = new World(img.getWidth(), img.getHeight(), null, worldType);
				Main.game.getWorlds().put(worldType, world);
				
				Cell[][] grid = new Cell[img.getWidth()][img.getHeight()];
				
				for (int i = 0; i < img.getWidth(); i++) {
					for (int j = 0; j < img.getHeight(); j++) {
						grid[i][j] = new Cell(worldType, new Vector2i(i, j));
						if(worldType.isRevealedOnStart()) {
							grid[i][j].setDiscovered(true);
						}
					}
				}
				
				for(int w = 0 ; w < img.getWidth(); w++) {
					for(int h = 0 ; h < img.getHeight(); h++) {
						grid[w][img.getHeight()-1-h].setPlace(new GenericPlace(worldType.getPlacesMap().get(new Color(img.getRGB(w, h)))));
						world.addPlaceOfInterest(grid[w][img.getHeight()-1-h].getPlace(), new Vector2i(w, img.getHeight()-1-h));
					}
				}
				
				world.setGrid(grid);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			
			if(debug)
				System.out.println(worldType.getName()+" Start 1");
			
			int width = worldType.getWorldSize();
			int height = worldType.getWorldSize();

			if(debug)
				System.out.println(worldType.getName()+" Start 2  [width:"+width+"] [height:"+height+"]");
			
			// Create new world of this type:
			World w = new World(width * 2 - 1, height * 2 - 1, null, worldType);
	
			Main.game.getWorlds().put(w.getWorldType(), w);

			if(debug)
				System.out.println(worldType.getName()+" Start 3");
			
			// Initialise grid:
			Cell[][] grid = new Cell[width][height];
			
			if(debug)
				System.out.println(worldType.getName()+" Start 4");
			
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					grid[i][j] = new Cell(worldType, new Vector2i(i, j));
					if(worldType.isRevealedOnStart()) {
						grid[i][j].setDiscovered(true);
					}
				}
			}
			
			if(debug)
				System.out.println(worldType.getName()+" Init finished");
			
			// Put blocks in alternating checkerboard of 4 squares:
			/*
			 * C = chance for 1 block in the 2x2 square . = never a block here
			 * C|C|.|.|C|C
			 * C|C|.|.|C|C
			 * .|.|C|C|.|.
			 * .|.|C|C|.|.
			 * C|C|.|.|C|C
			 * C|C|.|.|C|C
			 * 
			 * This will stop any impassable areas being created
			 */
			boolean[][] visited = new boolean[width][height];
			int x = 0, y = 0;
			List<Vector2i> dangerousPlaces = new ArrayList<>();
			for (int i = 0; i < width / 2; i++) {
				for (int j = 0; j < height / 2; j++) {
					if ((i + j) % 2 == 0) {
						switch (rnd.nextInt(4)) {
						case 0:
							x = 0;
							y = 0;
							break;
						case 1:
							x = 0;
							y = 1;
							break;
						case 2:
							x = 1;
							y = 0;
							break;
						case 3:
							x = 0;
							y = 1;
							break;
						}
						visited[i * 2 + x][j * 2 + y] = true;
						grid[i * 2 + x][j * 2 + y].setBlocked(true);
						if (worldType.getCutOffZone() != null) {
							grid[i * 2 + x][j * 2 + y].setPlace(new GenericPlace(worldType.getCutOffZone()));
							dangerousPlaces.add(new Vector2i(i * 2 + x, j * 2 + y));
						}
					}
				}
			}
			
			if(debug)
				System.out.println(worldType.getName()+" Break 1");
			
			int coreX = 0, coreY = 0;
			// Set aligned entrances:
			for(PlaceType pt : worldType.getPlaces()) {
				if(pt.getParentWorldType()!=null) {
					if(pt.getParentAlignment()!=null) {
						if(debug)
							System.out.println(pt.getName()+" Break 1a");
						Vector2i location = null;
						switch(pt.getParentAlignment()) {
							case ALIGNED:
								location = Main.game.getWorlds().get(pt.getParentWorldType()).getPlacesOfInterest().get(new GenericPlace(pt.getParentPlaceType()));
								if(debug)
									System.out.println(location);
								grid[location.getX()/2][location.getY()/2].setPlace(new GenericPlace(pt));
								grid[location.getX()/2][location.getY()/2].setBlocked(false);
								visited[location.getX()/2][location.getY()/2] = false;
								if(debug)
									System.out.println(location);
								break;
							case ALIGNED_FLIP_HORIZONTAL:
								location = Main.game.getWorlds().get(pt.getParentWorldType()).getPlacesOfInterest().get(new GenericPlace(pt.getParentPlaceType()));
								grid[width - 1 - (location.getX())/2][location.getY()/2].setPlace(new GenericPlace(pt));
								grid[width - 1 - (location.getX())/2][location.getY()/2].setBlocked(false);
								visited[width - 1 - (location.getX())/2][location.getY()/2] = false;
								if(debug)
									System.out.println(location);
								break;
							case ALIGNED_FLIP_VERTICAL:
								location = Main.game.getWorlds().get(pt.getParentWorldType()).getPlacesOfInterest().get(new GenericPlace(pt.getParentPlaceType()));
								grid[location.getX()/2][height - 1 - (location.getY())/2].setPlace(new GenericPlace(pt));
								grid[location.getX()/2][height - 1 - (location.getY())/2].setBlocked(false);
								visited[location.getX()/2][height - 1 - (location.getY())/2] = false;
								if(debug)
									System.out.println(location);
								break;
							default:
								break;
						}
						if(debug)
							System.out.println(pt.getName()+" Break 1b");
					}
				}
				if(debug)
					System.out.println(worldType.getName()+" Break 1c");
			}
			
			if(debug)
				System.out.println(worldType.getName()+" Break 2");
			
			// Set exits:
			for(PlaceType pt : worldType.getPlaces()){
				if(pt.getBearing()!=null){
					// To get a little bit of spread, use quadrants of map to place exits.
					int quadrant = 1;
	
					// Don't place exits in corners.
					do {
						if (pt.getBearing() == Bearing.NORTH) {
							coreY = height - 1;

							if (quadrant == 1) {
								coreX = rnd.nextInt(width / 2 - 1) + 1;

								if (!grid[coreX][coreY].isBlocked())
									quadrant++;
							} else {
								coreX = (width / 2) + rnd.nextInt(width / 2 - 1);

								if (!grid[coreX][coreY].isBlocked())
									quadrant = 1;
							}

						} else if (pt.getBearing() == Bearing.EAST) {
							coreX = width - 1;

							if (quadrant == 1) {
								coreY = rnd.nextInt(height / 2 - 1) + 1;

								if (!grid[coreX][coreY].isBlocked())
									quadrant++;
							} else {
								coreY = (height / 2) + rnd.nextInt(height / 2 - 1);

								if (!grid[coreX][coreY].isBlocked())
									quadrant = 1;
							}

						} else if (pt.getBearing() == Bearing.SOUTH) {
							coreY = 0;

							if (quadrant == 1) {
								coreX = rnd.nextInt(width / 2 - 1) + 1;

								if (!grid[coreX][coreY].isBlocked())
									quadrant++;
							} else {
								coreX = (width / 2) + rnd.nextInt(width / 2 - 1);

								if (!grid[coreX][coreY].isBlocked())
									quadrant = 1;
							}

						} else if (pt.getBearing() == Bearing.WEST) {
							coreX = 0;

							if (quadrant == 1) {
								coreY = rnd.nextInt(height / 2 - 1) + 1;

								if (!grid[coreX][coreY].isBlocked())
									quadrant++;
							} else {
								coreY = (height / 2) + rnd.nextInt(height / 2 - 1);

								if (!grid[coreX][coreY].isBlocked())
									quadrant = 1;
							}

						} else {
							// If no bearing, the entrance can't be on a map edge:

							if (quadrant == 1) {
								coreX = rnd.nextInt((width - 2) / 2) + 1;
								coreY = rnd.nextInt((height - 2) / 2) + 1;

								if (!grid[coreX][coreY].isBlocked())
									quadrant++;
							} else if (quadrant == 2) {
								coreX = (width - 2) / 2 + rnd.nextInt((width - 2) / 2) + 1;
								coreY = rnd.nextInt((height - 2) / 2) + 1;

								if (!grid[coreX][coreY].isBlocked())
									quadrant++;
							} else if (quadrant == 3) {
								coreX = rnd.nextInt((width - 2) / 2) + 1;
								coreY = (height - 2) / 2 + rnd.nextInt((height - 2) / 2) + 1;

								if (!grid[coreX][coreY].isBlocked())
									quadrant++;
							} else {
								coreX = (width - 2) / 2 + rnd.nextInt((width - 2) / 2) + 1;
								coreY = (height - 2) / 2 + rnd.nextInt((height - 2) / 2) + 1;

								if (!grid[coreX][coreY].isBlocked())
									quadrant = 1;
							}
						}
					} while (grid[coreX][coreY].isBlocked());
	
					grid[coreX][coreY].setBlocked(false);
					visited[coreX][coreY] = false;
					grid[coreX][coreY].setPlace(new GenericPlace(pt));

					if (pt.getBearing() == Bearing.NORTH)
						grid[coreX][coreY].setNorthAccess(true);
					else if (pt.getBearing() == Bearing.EAST)
						grid[coreX][coreY].setEastAccess(true);
					else if (pt.getBearing() == Bearing.SOUTH)
						grid[coreX][coreY].setSouthAccess(true);
					else if (pt.getBearing() == Bearing.WEST)
						grid[coreX][coreY].setWestAccess(true);
				}
			}
			
			if(debug)
				System.out.println(worldType.getName()+" Break 3");
			
			// Add places:
			int quadrant = 1;
			List<PlaceType> places = new ArrayList<>();
			for (PlaceType p : worldType.getPlaces()) {
				if(p.getBearing()==null && p.getParentAlignment()==null)
					places.add(p);
			}
			Collections.shuffle(places);
	
			for (PlaceType p : places) {
				do {
					coreX = rnd.nextInt(width);
					coreY = rnd.nextInt(height);
					if (quadrant == 1) {
						coreX = rnd.nextInt(width / 2);
						coreY = rnd.nextInt(height / 2);
					} else if (quadrant == 2) {
						coreX = width / 2 + rnd.nextInt(width / 2);
						coreY = rnd.nextInt(height / 2);
					} else if (quadrant == 3) {
						coreX = rnd.nextInt(width / 2);
						coreY = height / 2 + rnd.nextInt(height / 2);
					} else {
						coreX = width / 2 + rnd.nextInt(width / 2);
						coreY = height / 2 + rnd.nextInt(height / 2);
					}
				} while (grid[coreX][coreY].isBlocked() || grid[coreX][coreY].getPlace().getPlaceType() != worldType.getStandardPlace());
	
				quadrant++;
				if (quadrant > 4)
					quadrant = 1;
	
				grid[coreX][coreY].setPlace(new GenericPlace(p));
	
			}
	
			// Add cuttOffZone places:
			if (worldType.getDangerousPlaces() != null)
				for (PlaceType p : worldType.getDangerousPlaces()) {
					Vector2i vTemp = dangerousPlaces.get(Util.random.nextInt(dangerousPlaces.size()));
	
					grid[vTemp.getX()][vTemp.getY()].setPlace(new GenericPlace(p));
	
					dangerousPlaces.remove(vTemp);
				}
	
			Cell[][] finalGrid = generateMap(width / 2, height / 2, grid, visited);
	

			if(debug)
				System.out.println(worldType.getName()+" Break 4");
			
			// Expand the generated grid:
	
			Cell[][] expandedGrid = new Cell[width * 2 - 1][height * 2 - 1];
			for (int i = 0; i < width * 2 - 1; i++)
				for (int j = 0; j < height * 2 - 1; j++)
					expandedGrid[i][j] = new Cell(worldType, new Vector2i(i, j));
	
			for (int i = 0; i < width * 2 - 1; i++)
				for (int j = 0; j < height * 2 - 1; j++) {
					if (i % 2 == 0 && j % 2 == 0) {
						if (finalGrid[i / 2][j / 2].getPlace().getPlaceType() != worldType.getStandardPlace()) {
							expandedGrid[i][j].setPlace(finalGrid[i / 2][j / 2].getPlace());
							w.addPlaceOfInterest(finalGrid[i / 2][j / 2].getPlace(), new Vector2i(i, j));
						}
					} else if (i % 2 == 0) {
						if (finalGrid[i / 2][j / 2].isNorthAccess())
							expandedGrid[i][j].setSouthAccess(true);
						if (finalGrid[i / 2][(j + 1) / 2].isSouthAccess())
							expandedGrid[i][j].setNorthAccess(true);
						if (!finalGrid[i / 2][j / 2].isNorthAccess() && !finalGrid[i / 2][(j + 1) / 2].isSouthAccess()) {
							expandedGrid[i][j].setPlace(new GenericPlace(worldType.getCutOffZone()));
						}
	
					} else if (j % 2 == 0) {
						if (finalGrid[i / 2][j / 2].isEastAccess())
							expandedGrid[i][j].setWestAccess(true);
						if (finalGrid[(i + 1) / 2][j / 2].isWestAccess())
							expandedGrid[i][j].setEastAccess(true);
						if (!finalGrid[i / 2][j / 2].isEastAccess() && !finalGrid[(i + 1) / 2][j / 2].isWestAccess()) {
							expandedGrid[i][j].setPlace(new GenericPlace(worldType.getCutOffZone()));
						}
	
					} else {
						if (Math.random() > 0.8) {
							expandedGrid[i][j].setPlace(new GenericPlace(worldType.getCutOffZone()));
						} else {
							expandedGrid[i][j].setPlace(new GenericPlace(PlaceType.GENERIC_IMPASSABLE));
						}
					}
	
				}
	
//			printMaze(expandedGrid);
	
			w.setGrid(expandedGrid);
		}
	}

	// start point for generation
	public Cell[][] generateMap(int x, int y, Cell[][] grid, boolean[][] visited) {
		Random rnd = new Random();

		if (Math.random() >= 0.5) {
			visited[x][y] = true;
		}
		
		int width = grid.length;
		int height = grid[0].length;
		
		// Check each surrounding cell to see if it's been visited. If it has,
		// skip over it. If it hasn't, break the wall to it:
		// y+2
		// x-2 ORIGIN x+2
		// y-2

		// randomise this order:
		int[] direction = { 1, 2, 3, 4 };
		for (int i = direction.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = direction[index];
			direction[index] = direction[i];
			direction[i] = a;
		}

		for (Integer index : direction) {
			switch (index) {
			case 1: // West
				if (x - 1 >= 0 && y >= 0 && y <= height - 1)
					if (!visited[x - 1][y] && !grid[x - 1][y].isBlocked()) {
						grid[x][y].setWestAccess(true);
						grid[x - 1][y].setEastAccess(true);
						generateMap(x - 1, y, grid, visited);
					}
				break;

			case 2: // East
				if (x + 1 <= width - 1 && y >= 0 && y <= height - 1)
					if (!visited[x + 1][y] && !grid[x + 1][y].isBlocked()) {
						grid[x][y].setEastAccess(true);
						grid[x + 1][y].setWestAccess(true);
						generateMap(x + 1, y, grid, visited);
					}
				break;

			case 3: // South
				if (y - 1 >= 0 && x >= 0 && x <= width - 1)
					if (!visited[x][y - 1] && !grid[x][y - 1].isBlocked()) {
						grid[x][y].setSouthAccess(true);
						grid[x][y - 1].setNorthAccess(true);
						generateMap(x, y - 1, grid, visited);
					}
				break;

			case 4: // North
				if (y + 1 <= height - 1 && x >= 0 && x <= width - 1)
					if (!visited[x][y + 1] && !grid[x][y + 1].isBlocked()) {
						grid[x][y].setNorthAccess(true);
						grid[x][y + 1].setSouthAccess(true);
						generateMap(x, y + 1, grid, visited);
					}
				break;
			}
		}

		return grid;
	}

	public static void printMaze(Cell[][] maze) {
		StringBuilder lineOneBuffer = new StringBuilder(""), lineTwoBuffer = new StringBuilder(""), lineThreeBuffer = new StringBuilder("");
		for (int y = maze[0].length - 1; y >= 0; y--) {
			for (int x = 0; x < maze.length; x++) {
				lineOneBuffer.append('#');
				if (maze[x][y].isNorthAccess())
					lineOneBuffer.append('_');
				else
					lineOneBuffer.append('#');
				lineOneBuffer.append('#');

				if (maze[x][y].isWestAccess())
					lineTwoBuffer.append('_');
				else
					lineTwoBuffer.append('#');

				if (maze[x][y].isEastAccess())
					lineTwoBuffer.append('_');
				else
					lineTwoBuffer.append('#');

				lineThreeBuffer.append('#');
				if (maze[x][y].isSouthAccess())
					lineThreeBuffer.append('_');
				else
					lineThreeBuffer.append('#');
				lineThreeBuffer.append('#');
			}

			System.out.println(lineOneBuffer.toString());
			lineOneBuffer = new StringBuilder("");
			System.out.println(lineTwoBuffer.toString() + " " + y);
			lineTwoBuffer = new StringBuilder("");
			System.out.println(lineThreeBuffer.toString());
			lineThreeBuffer = new StringBuilder("");
		}
	}

}
