package com.lilithsthrone.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * I had plans to add pathing into the game at one stage of development.
 * Although it's not used at the moment, I think this will be useful later on.
 * 
 * It was useful later on.
 * 
 * @since 0.1.0
 * @version 0.3.5
 * @author Innoxia
 */
public class Pathing {
	
	private static List<Cell> pathingCells = new ArrayList<>();
	private static Vector2i endPoint = new Vector2i(0, 0);
	private static WorldType destinationWorld = WorldType.DOMINION;
	
	private static int travelTime = 0;
	private static int dangerousTiles = 0;

	private static boolean impossibleDestination = true;
	
	private static MapTravelType mapTravelType = MapTravelType.WALK_SAFE;

	public Pathing() {
	}

	public static List<Cell> aStarPathing(Cell[][] grid, Vector2i start, Vector2i end, boolean preferSafe) {
		return aStarPathing(grid, start.getX(), start.getY(), end.getX(), end.getY(), preferSafe);
	}
	
	public static List<Cell> aStarPathing(Cell[][] grid, Vector2i start, int endX, int endY, boolean preferSafe) {
		return aStarPathing(grid, start.getX(), start.getY(), endX, endY, preferSafe);
	}
	
	// Implemented using http://www.policyalmanac.org/games/aStarTutorial.htm
	public static List<Cell> aStarPathing(Cell[][] grid, int startX, int startY, int endX, int endY, boolean preferSafe) {
		/*
		 * 1) Add the starting square (or node) to the open list.
		 * 
		 * 2) Repeat the following:
		 * 
		 * a) Look for the lowest F cost square on the open list. We refer to
		 * this as the current square.
		 * 
		 * b) Switch it to the closed list.
		 * 
		 * c) For each of the 8 squares adjacent to this current square �
		 * 
		 * i) If it is not walkable or if it is on the closed list, ignore it.
		 * Otherwise do the following.
		 * 
		 * ii) If it isn't on the open list, add it to the open list. Make the
		 * current square the parent of this square. Record the F, G, and H
		 * costs of the square.
		 * 
		 * iii) If it is on the open list already, check to see if this path to
		 * that square is better, using G cost as the measure. A lower G cost
		 * means that this is a better path. If so, change the parent of the
		 * square to the current square, and recalculate the G and F scores of
		 * the square. If you are keeping your open list sorted by F score, you
		 * may need to resort the list to account for the change.
		 * 
		 * d) Stop when you:
		 * 
		 * i) Add the target square to the closed list, in which case the path
		 * has been found (see note below), or ii) Fail to find the target
		 * square, and the open list is empty. In this case, there is no path.
		 * 
		 * 3) Save the path. Working backwards from the target square, go from
		 * each square to its parent square until you reach the starting square.
		 * That is your path.
		 */
		List<Cell> path = new ArrayList<>();
		impossibleDestination = true;
		
		Node[][] nodeArray = new Node[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				nodeArray[i][j] = new Node(null, i, j, 0, 0, 0);
			}
		}
		
		Queue<Node> openList = new PriorityQueue<>(10, new Comparator<Node>() {
			@Override
			// Sort by F value
			public int compare(Node o1, Node o2) {
				return o1.getF() - o2.getF();
			}
		});
		List<Node> closedList = new ArrayList<>();

		// 1)
		openList.add(nodeArray[startX][startY]);

		Node n;
		// 2) d) ii)
		while (!openList.isEmpty()) {
			// 2) a) Priority queue always gives the lowest F cost square
			n = openList.poll();

			// 2) b)
			closedList.add(n);
			
			// 2) d) i)
			if (n.getX() == endX && n.getY() == endY) {
				impossibleDestination = false;
				break;
			}

			// 2) c)
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (!(i == 0 && j == 0) // Do not calculate the current tile
							// Make sure we don't go out of bounds:
							&& n.getX() + i >= 0
							&& n.getX() + i < grid.length
							&& n.getY() + j >= 0
							&& n.getY() + j < grid[0].length) { 
						if (!closedList.contains(nodeArray[n.getX() + i][n.getY() + j])) { // c) i)
							// Deny diagonals unless in main world map
							Cell c = grid[n.getX() + i][n.getY() + j];

							int time = Main.game.getModifierTravelTime(c.getPlace().getPlaceType().isLand(), (c.getPlace().getPlaceType().getDialogue(false)!=null? c.getPlace().getPlaceType().getDialogue(false).getSecondsPassed() : 10000));
							
							int g = ((i == 0 || j == 0) ? 10 : c.getType().equals(WorldType.WORLD_MAP)?12:1_000_000)
									+ time
									+ (c.getPlace().getPlaceType().equals(PlaceType.GENERIC_IMPASSABLE)?100_000_000:0)
									+ (preferSafe && c.getPlace().getPlaceType()!=null && c.getPlace().getPlaceType().isDangerous()?100_000:0);
							
							
							if (!openList.contains(nodeArray[n.getX() + i][n.getY() + j])) { // c) ii)
								nodeArray[n.getX() + i][n.getY() + j].setParent(n);
								nodeArray[n.getX() + i][n.getY() + j].setG(n.getG() + g);
//								if (heuristic) {
									nodeArray[n.getX() + i][n.getY() + j].setH(Math.abs((n.getX() + i) - endX) + Math.abs((n.getX() + j) - endY)); // Manhattan distance
//								} else {
//									nodeArray[n.getX() + i][n.getY() + j].setH(0);
//								}
								nodeArray[n.getX() + i][n.getY() + j].setF();

								openList.add(nodeArray[n.getX() + i][n.getY() + j]);
								
							} else { // c) iii)
								if ((n.getG() + g) < nodeArray[n.getX() + i][n.getY() + j].getG()) {
									openList.remove(nodeArray[n.getX() + i][n.getY() + j]);

									nodeArray[n.getX() + i][n.getY() + j].setParent(n);
									nodeArray[n.getX() + i][n.getY() + j].setG(n.getG() + g);
									nodeArray[n.getX() + i][n.getY() + j].setF();

									openList.add(nodeArray[n.getX() + i][n.getY() + j]);
								}
							}
						}
					}
				}
			}
		}

		// 3)
		n = nodeArray[endX][endY];
		while (n.getParent() != null) {
			path.add(grid[n.getX()][n.getY()]);
			n = n.getParent();
		}
		Collections.reverse(path);
		return path;
	}
	
	public static Response walkPath(MapTravelType travelType) {
		int totalTimePassed = 0;
		for(Cell c : getPathingCells()) {
			Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
			DialogueNode dialogue = c.getPlace().getDialogue(true);
			
			if(dialogue!=null) {
				totalTimePassed += Main.game.getModifierTravelTime(c.getPlace().getPlaceType().isLand(), dialogue.getSecondsPassed());
//				c.setDiscovered(true);
//				c.setTravelledTo(true);
				
//				Main.game.endTurn(null, dialogue);
				
				if(dialogue.isTravelDisabled()) {
					int time = totalTimePassed;
					Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
					return new Response("", "", dialogue) {
						@Override
						public int getSecondsPassed() {
							return time;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(
									"<p style='text-align:center;'>"
											+ "[style.italicsBad(Your travel was interrupted!)]"
									+ "</p>");
						}
					};
				}
				
				if(totalTimePassed>=2*60*60) { // Every 2 hours, perform an end turn. I didn't encounter any lag when just ending the turn on every tile moved, but I'm sure it could lag when moving huge distances.
					Main.game.endTurn(totalTimePassed);
					totalTimePassed = 0;
				}
			}
		}
		
		// Return destination:
		Cell destination = getPathingCells().get(getPathingCells().size()-1);
		Main.game.getPlayer().setLocation(destination.getType(), destination.getLocation(), false);

		Main.game.endTurn(totalTimePassed);
		
		return new Response("", "", destination.getPlace().getDialogue(false));
	}
	
	public static void initPathingVariables() {
		if(!getMapTravelType().isAvailable(null, Main.game.getPlayer())) {
			setMapTravelType(MapTravelType.WALK_SAFE);
		}
		setPathingCells(new ArrayList<>(), new Vector2i(-1, -1));//new Vector2i(Main.game.getPlayer().getLocation()));
		destinationWorld = Main.game.getPlayer().getWorldLocation();
	}
	
	public static List<Cell> getPathingCells() {
		return pathingCells;
	}

	public static void setPathingCells(List<Cell> pathingCells, Vector2i endPoint) {
		Pathing.pathingCells = pathingCells;
		Pathing.endPoint = endPoint;
		travelTime = calculateTravelTime(Pathing.pathingCells, true);
		dangerousTiles = calculateDangerousTiles(Pathing.pathingCells);
	}

	public static void appendPathingCells(List<Cell> pathingCells, Vector2i endPoint) {
		Pathing.pathingCells.addAll(pathingCells);
		Pathing.endPoint = endPoint;
		travelTime = calculateTravelTime(Pathing.pathingCells, true);
		dangerousTiles = calculateDangerousTiles(Pathing.pathingCells);
	}
	
	public static Vector2i getEndPoint() {
		return endPoint;
	}
	
	private static int calculateTravelTime(List<Cell> cellRoute, boolean withModifiedTravelTime) {
		int seconds = 0;
		for(Cell c : cellRoute) {
			DialogueNode dialogue = c.getPlace().getDialogue(false);
			if(dialogue!=null) {
				if(withModifiedTravelTime) {
					seconds += Main.game.getModifierTravelTime(c.getPlace().getPlaceType().isLand(), dialogue.getSecondsPassed());
				} else {
					seconds += dialogue.getSecondsPassed();
				}
			}
		}
		return seconds;
	}
	
	private static int calculateDangerousTiles(List<Cell> cellRoute) {
		int dangerous = 0;
		for(Cell c : cellRoute) {
			if(c.getPlace().getPlaceType().isDangerous()) {
				dangerous++;
			}
		}
		return dangerous;
	}
	
	/**
	 * @param endPoint New endPoint.
	 * @param worldForRecalculatingFlyTime Pass in null if you don't want to recalculate the flight time.
	 */
	public static void setEndPoint(Vector2i endPoint, Cell cell, WorldType worldForRecalculatingFlyTime) {
		Pathing.endPoint = endPoint;
		if(worldForRecalculatingFlyTime!=null) {
			List<Cell> route = Pathing.aStarPathing(Main.game.getWorlds().get(worldForRecalculatingFlyTime).getCellGrid(), Main.game.getPlayer().getLocation(), endPoint, false);
			travelTime = calculateTravelTime(route, false)/10; // flying is 10 times faster than walking
		}
		dangerousTiles = cell.getPlace().getPlaceType().isDangerous()?1:0;
		destinationWorld = cell.getType();
	}
	
	public static MapTravelType getMapTravelType() {
		return mapTravelType;
	}

	public static void setMapTravelType(MapTravelType mapTravelType) {
		Pathing.mapTravelType = mapTravelType;
	}
	
	/**
	 * Automatically calculated when calling setPathingCells() and appendPathingCells().
	 * @return How many tiles on the path are considered dangerous (chance of bad encounter).
	 */
	public static int getDangerousTilesCrossed() {
		return dangerousTiles;
	}
	
	/**
	 * Automatically calculated when calling setPathingCells() and appendPathingCells().
	 * @return How long it will take the character to travel down the calculated path.
	 */
	public static int getTravelTime() {
		return travelTime;
	}

	public static WorldType getDestinationWorld() {
		return destinationWorld;
	}

	/**
	 * @return true if the last calculated aStarPathing did not reach its destination.
	 */
	public static boolean isImpossibleDestination() {
		return impossibleDestination;
	}

	public static List<TreeEntry<PerkCategory, AbstractPerk>> aStarPathingPerkTree(GameCharacter character, TreeEntry<PerkCategory, AbstractPerk> destination) {
		List<TreeEntry<PerkCategory, AbstractPerk>> startingPerks = PerkManager.getStartingPerks(character);
		
		// Set starting point to the same category as the destination, if available:
		TreeEntry<PerkCategory, AbstractPerk> start = startingPerks.get(0);
		for(TreeEntry<PerkCategory, AbstractPerk> perk : startingPerks) {
			if(perk.getCategory()==destination.getCategory()) {
				start = perk;
			}
		}
		
		return  aStarPathingPerkTree(
						PerkManager.MANAGER.getPerkTree(character),
						start,
						destination);
	}
	
	public static List<TreeEntry<PerkCategory, AbstractPerk>> aStarPathingPerkTree(
			Map<Integer, Map<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>>> perkTree,
			TreeEntry<PerkCategory, AbstractPerk> start,
			TreeEntry<PerkCategory, AbstractPerk> destination) {

		List<TreeEntry<PerkCategory, AbstractPerk>> perkList = new ArrayList<>();
		
		for(Map<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>> entry1 : perkTree.values()) {
			for(List<TreeEntry<PerkCategory, AbstractPerk>> entry2 : entry1.values()) {
				for(TreeEntry<PerkCategory, AbstractPerk> entry3 : entry2) {
					if(entry3.getRow()>0) {
						perkList.add(entry3);
					}
				}
			}
		}
		
		List<TreeEntry<PerkCategory, AbstractPerk>> path = new ArrayList<>();
		
		List<PerkNode> nodeArray = new ArrayList<>();
		PerkNode startNode = null;
		for (int i = 0; i < perkList.size(); i++) {
			PerkNode createdNode = new PerkNode(null, perkList.get(i), 0, 0, 0);
			if(perkList.get(i).equals(start)) {
				startNode = createdNode;
			}
			nodeArray.add(createdNode);
//			System.out.println("Added:: "+perkList.get(i).getEntry().getName(null));
		}
		
		Queue<PerkNode> openList = new PriorityQueue<>(10, new Comparator<PerkNode>() {
			@Override
			// Sort by F value
			public int compare(PerkNode o1, PerkNode o2) {
				return o1.getF() - o2.getF();
			}
		});
		List<PerkNode> closedList = new ArrayList<>();

		// 1)
		openList.add(startNode);//nodeArray.get(0));

		PerkNode n = null;
		PerkNode destinationNode = null;
		// 2) d) ii)
		while (!openList.isEmpty()) {
			// 2) a) Priority queue always gives the lowest F cost square
			n = openList.poll();

			// 2) b)
			closedList.add(n);

			// 2) d) i)
//			if (n.getPerkTreeEntry().equals(destination)) {
			if(n.getPerkTreeEntry().equals(destination)) {
				destinationNode = n;
				break;
			}

			// 2) c)
			for(TreeEntry<PerkCategory, AbstractPerk> link : Util.mergeLists(n.getPerkTreeEntry().getSiblingLinks(), n.getPerkTreeEntry().getChildLinks())) {
				boolean containsLink = false;
				PerkNode closedNode = null;
				for(PerkNode node : closedList) {
					if(node.getPerkTreeEntry().equals(link)) {
						containsLink = true;
						break;
					}
				}
				for(PerkNode node : nodeArray) {
					if(node.getPerkTreeEntry().equals(link)) {
						closedNode = node;
						break;
					}
				}
//				if(closedNode!=null) {
				if(!containsLink) { // c) i)
					int g = 1;

					if (!openList.contains(closedNode)) { // c) ii)
						closedNode.setParent(n);
						closedNode.setG(n.getG() + g);
						closedNode.setH(1); //0?
						closedNode.setF();

						openList.add(closedNode);
						
					} else { // c) iii)
						if ((n.getG() + g) < closedNode.getG()) {
							openList.remove(closedNode);

							closedNode.setParent(n);
							closedNode.setG(n.getG() + g);
							closedNode.setF();

							openList.add(closedNode);
						}
					}
				}
//				}
			}
		}

		// 3)
		n = destinationNode;
		path.add(n.getPerkTreeEntry());
		while(n.getParent() != null) {
			path.add(n.getParent().getPerkTreeEntry());
			n = n.getParent();
		}
		Collections.reverse(path);
		
//		for(TreeEntry<PerkCategory, AbstractPerk> link : path) {
//			System.out.println(link.getEntry().getName(null)+", "+link.getRow()+", "+link.getCategory());
//		}
		return path;
	}
}
