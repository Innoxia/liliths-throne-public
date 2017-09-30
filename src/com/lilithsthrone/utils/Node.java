package com.lilithsthrone.utils;

/**
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public class Node {
	private Node parent;
	// f = g + h
	// g = the movement cost to move from the starting point A to this node,
	// following the path generated to get there.
	// h = the estimated movement cost to move from this node to the final
	// destination. This is often referred to as the heuristic, because it is a
	// guess.
	private int f, g, h, x, y;

	public Node(Node parent, int x, int y, int f, int g, int h) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.f = f;
		this.g = g;
		this.h = h;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getF() {
		return f;
	}

	public void setF() {
		f = g + h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
