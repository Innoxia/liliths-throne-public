package com.base.utils;

import java.io.Serializable;

/**
 * @since 0.1.0
 * @version 0.1.69.9
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
	public boolean equals(Object obj) {
		if (!(obj instanceof Vector2i))
			return false;
		if (obj == this)
			return true;

		Vector2i comparisonObject = (Vector2i) obj;
		if (this.x == comparisonObject.x && this.y == comparisonObject.y)
			return true;
		else
			return false;
	}
	
	public boolean equals (int x, int y) {
		if(this.x==x && this.y==y)
			return true;
		else
			return false;
	}
}
