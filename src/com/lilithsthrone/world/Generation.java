package com.lilithsthrone.world;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.places.GenericPlace;

import javafx.concurrent.Task;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class Generation extends Task<Boolean> {

	private boolean debug = false;

	public Generation() {
	}

	@Override
	public Boolean call() {
		int maxSize = WorldType.values().length;
		int count = 0;

		for(WorldType wt : WorldType.values()) {
			if(debug) {
				System.out.println(wt);
			}
			worldGeneration(wt);
			count++;
			updateProgress(count, maxSize);
		}

		return true;
	}

	public void worldGeneration(WorldType worldType) {
//		System.out.println(worldType);
		if(worldType.isUsesFile()) {
			try {
				BufferedImage img = ImageIO.read((getClass().getResource(worldType.getFileLocation())));

				World world = new World(img.getWidth(), img.getHeight(), null, worldType);
				Main.game.getWorlds().put(worldType, world);

				if(debug)
					System.out.println(worldType.getName()+" Start-File 1");

				Cell[][] grid = new Cell[img.getWidth()][img.getHeight()];

				for (int i = 0; i < img.getWidth(); i++) {
					for (int j = 0; j < img.getHeight(); j++) {
						grid[i][j] = new Cell(worldType, new Vector2i(i, j));
						if(worldType.isRevealedOnStart()) {
							grid[i][j].setDiscovered(true);
							grid[i][j].setTravelledTo(true);

						} else if(worldType.isDiscoveredOnStart()) {
							grid[i][j].setDiscovered(true);
						}
					}
				}

				if(debug)
					System.out.println(worldType.getName()+" Start-File 2");

				for(int w = 0 ; w < img.getWidth(); w++) {
					for(int h = 0 ; h < img.getHeight(); h++) {
						grid[w][img.getHeight()-1-h].setPlace(new GenericPlace(worldType.getPlacesMap().get(new Color(img.getRGB(w, h)))), true);
					}
				}

				if(debug)
					System.out.println(worldType.getName()+" Start-File 3");

				world.setGrid(grid);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			//TODO
		}
	}


}
