package com.lilithsthrone.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;

/**
 * I had plans to add pathing into the game at one stage of development.
 * Although it's not used at the moment, I think this will be useful later on.
 * 
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public class Pathing {

	public Pathing() {
	}

	// Implemented using http://www.policyalmanac.org/games/aStarTutorial.htm
	public static List<Cell> aStarPathing(Cell[][] grid, int startX, int startY, int endX, int endY, boolean heuristic) {
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
		 * ii) If it isn�t on the open list, add it to the open list. Make the
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

		Node[][] nodeArray = new Node[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				nodeArray[i][j] = new Node(null, i, j, 0, 0, 0);

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
			if (n.getX() == endX && n.getY() == endY)
				break;

			// 2) c)
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 1; j++)
					if (!(i == 0 && j == 0) && n.getX() + i >= 0 && n.getX() + i < grid.length - 1 && n.getY() + j >= 0 && n.getY() + j < grid[0].length - 1) // Make
																																								// sure
																																								// we
																																								// don't
																																								// go
																																								// out
																																								// of
																																								// bounds
																																								// //TODO
																																								// add
																																								// checks
																																								// to
																																								// see
																																								// if
																																								// the
																																								// direction
																																								// is
																																								// open
						if (!closedList.contains(nodeArray[n.getX() + i][n.getY() + j])) { // c)
																							// i)
							// 1000 if diagonal, 10 if vertical/horizontal
							int g = ((i == 0 || j == 0) ? 10 : 1000) + grid[n.getX() + i][n.getY() + j].getType().getMoveCost();

							if (!openList.contains(nodeArray[n.getX() + i][n.getY() + j])) { // c)
																								// ii)
								nodeArray[n.getX() + i][n.getY() + j].setParent(n);
								nodeArray[n.getX() + i][n.getY() + j].setG(n.getG() + g);
								if (heuristic)
									nodeArray[n.getX() + i][n.getY() + j].setH(Math.abs((n.getX() + i) - endX) + Math.abs((n.getX() + j) - endY)); // Manhattan
																																					// distance
								else
									nodeArray[n.getX() + i][n.getY() + j].setH(0);
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

		// 3)
		n = nodeArray[endX][endY];
		while (n.getParent() != null) {
			path.add(grid[n.getX()][n.getY()]);
			n = n.getParent();
		}

		return path;
	}

	// Using a CellType instead of coordinates for an endpoint
	public static List<Cell> aStarPathing(Cell[][] grid, int startX, int startY, WorldType endType, WorldType... types) {
		int endX = 0, endY = 0;
		List<Cell> path = new ArrayList<>();

		List<WorldType> cellTypes = new ArrayList<>();
		cellTypes.add(endType);
		Collections.addAll(cellTypes, types);

		if (cellTypes.contains(grid[startX][startY].getType()))
			return path;

		Node[][] nodeArray = new Node[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				nodeArray[i][j] = new Node(null, i, j, 0, 0, 0);

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
			if (cellTypes.contains(grid[n.getX()][n.getY()].getType())) {
				endX = n.getX();
				endY = n.getY();
				break;
			}

			// 2) c)
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 1; j++)
					if (!(i == 0 && j == 0) && n.getX() + i >= 0 && n.getX() + i < grid.length - 1 && n.getY() + j >= 0 && n.getY() + j < grid[0].length - 1) // Make
																																								// sure
																																								// we
																																								// don't
																																								// go
																																								// out
																																								// of
																																								// bounds
																																								// //TODO
																																								// add
																																								// checks
																																								// to
																																								// see
																																								// if
																																								// the
																																								// direction
																																								// is
																																								// open
						if (!closedList.contains(nodeArray[n.getX() + i][n.getY() + j])) { // c)
																							// i)
							// 14 if diagonal, 10 if vertical/horizontal
							int g = ((i == 0 || j == 0) ? 10 : 14) + grid[n.getX() + i][n.getY() + j].getType().getMoveCost();

							if (!openList.contains(nodeArray[n.getX() + i][n.getY() + j])) { // c)
																								// ii)
								nodeArray[n.getX() + i][n.getY() + j].setParent(n);
								nodeArray[n.getX() + i][n.getY() + j].setG(n.getG() + g);
								nodeArray[n.getX() + i][n.getY() + j].setH(0);
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

		// 3)
		n = nodeArray[endX][endY];
		while (n.getParent() != null) {
			path.add(grid[n.getX()][n.getY()]);
			n = n.getParent();
		}

		return path;
	}

	public static List<Cell> cellsInDistance(Cell[][] grid, int startX, int startY, int distance, boolean amphibious) {
		List<Cell> path = new ArrayList<>();

		Node[][] nodeArray = new Node[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				nodeArray[i][j] = new Node(null, i, j, 0, 0, 0);

		Queue<Node> openList = new PriorityQueue<>(10, new Comparator<Node>() {
			@Override
			// Sort by F value
			public int compare(Node o1, Node o2) {
				return o1.getF() - o2.getF();
			}
		});
		List<Node> closedList = new ArrayList<>();

		List<Node> returnList = new ArrayList<>();
		// 1)
		openList.add(nodeArray[startX][startY]);
		returnList.add(nodeArray[startX][startY]);

		Node n;
		// 2) d) ii)
		while (!openList.isEmpty()) {
			// 2) a) Priority queue always gives the lowest F cost square
			n = openList.poll();

			// 2) b)
			closedList.add(n);
			// 2) d) i)
			// if(n.getX()==endX && n.getY()==endY)
			// break;

			// 2) c)
			for (int i = -1; i <= 1; i++)
				for (int j = -1; j <= 1; j++)
					if (!(i == 0 && j == 0) && n.getX() + i >= 0 && n.getX() + i < grid.length - 1 && n.getY() + j >= 0 && n.getY() + j < grid[0].length - 1) // Make
																																								// sure
																																								// we
																																								// don't
																																								// go
																																								// out
																																								// of
																																								// bounds
						if (!closedList.contains(nodeArray[n.getX() + i][n.getY() + j]) && n.getG() < distance * 10) { // c)
																														// i)
							// Always 1
							int g = ((i == 0 || j == 0) ? 10 : 14);

							if (!openList.contains(nodeArray[n.getX() + i][n.getY() + j])) { // c)
																								// ii)
								nodeArray[n.getX() + i][n.getY() + j].setParent(n);
								nodeArray[n.getX() + i][n.getY() + j].setG(n.getG() + g);

								nodeArray[n.getX() + i][n.getY() + j].setH(0);
								nodeArray[n.getX() + i][n.getY() + j].setF();

								openList.add(nodeArray[n.getX() + i][n.getY() + j]);

								if ((!returnList.contains(nodeArray[n.getX() + i][n.getY() + j])) && (nodeArray[n.getX() + i][n.getY() + j].getG() <= distance * 10))
									returnList.add(nodeArray[n.getX() + i][n.getY() + j]);

							} else { // c) iii)
								if ((n.getG() + g) < nodeArray[n.getX() + i][n.getY() + j].getG()) {
									openList.remove(nodeArray[n.getX() + i][n.getY() + j]);

									nodeArray[n.getX() + i][n.getY() + j].setParent(n);
									nodeArray[n.getX() + i][n.getY() + j].setG(n.getG() + g);
									nodeArray[n.getX() + i][n.getY() + j].setF();

									openList.add(nodeArray[n.getX() + i][n.getY() + j]);

									if ((!returnList.contains(nodeArray[n.getX() + i][n.getY() + j])) && (nodeArray[n.getX() + i][n.getY() + j].getG() <= distance * 10))
										returnList.add(nodeArray[n.getX() + i][n.getY() + j]);

								}
							}
						}
		}

		// 3)
		for (Node node : returnList)
			path.add(grid[node.getX()][node.getY()]);

		return path;
	}

}
