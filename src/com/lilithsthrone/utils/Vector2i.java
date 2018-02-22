package com.lilithsthrone.utils;

import java.io.Serializable;

/**
 * @since 0.1.0
 * @version 0.1.99
 * @author Innoxia
 */
public class Vector2i implements Serializable {
	private static final long serialVersionUID = 1L;
	int x, y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
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

	@Override
	public boolean equals (Object o) {
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
