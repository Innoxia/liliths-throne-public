package com.lilithsthrone.utils;

import java.util.Stack;

/**
 * @since 0.2.?
 * @version 0.3.1
 * @author Innoxia
 */
public class SizedStack<T> extends Stack<T> {
	private static final long serialVersionUID = 1L;

	private int maxSize;

	public SizedStack(int maxSize) {
		super();
		this.maxSize = maxSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	@Override
	public T push(T object) {
		// If the stack is too big, remove elements until it's the right size.
		while (this.size() >= maxSize) {
			this.remove(0);
		}
		return super.push(object);
	}
}