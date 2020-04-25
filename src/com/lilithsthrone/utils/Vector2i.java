package com.lilithsthrone.utils;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class Vector2i {
	int x, y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2i(Vector2i vectorToCopy) {
		this.x = vectorToCopy.getX();
		this.y = vectorToCopy.getY();
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
	
	public static float getDistance(Vector2i point1, Vector2i point2) {
		return (float) Math.sqrt(Math.pow(point1.getX()-point2.getX(), 2) + Math.pow(point1.getY()-point2.getY(), 2));
	}
	
	@Override
	public String toString() {
		return "("+x+", "+y+")";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Vector2i){
			if(((Vector2i)o).getX() == x
				&& ((Vector2i)o).getY() == y){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}
	
	public boolean equals (int x, int y) {
		return this.x==x && this.y==y;
	}
}
