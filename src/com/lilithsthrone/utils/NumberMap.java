package com.lilithsthrone.utils;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Cognitive Mist
 * @since 0.4.7.1
 * @version 0.4.7.1
 */
public class NumberMap<K, V extends Number> extends AbstractMap<K, V> implements Map<K, V> {
	private final Map<K, V> map;
	private final Class<V> cls;
	
	public NumberMap(Class<V> cls) {
		this.map = new HashMap<>();
		this.cls = cls;
	}

	// Required to fulfill AbstractMap
	@Override
	public Set<Entry<K, V>> entrySet() {
		return map.entrySet();
	}

	// Required to make map writeable, and also to prevent using the wrong Number type
	@Override
	public V put(K key, V value) throws ClassCastException, UnsupportedOperationException {
		// Suppressing warnings because there's effectively no good way to do `V.valueOf`
		if (Double.class.isAssignableFrom(cls)) {
			@SuppressWarnings("unchecked")
			V corrected = (V) Double.valueOf(value.doubleValue());
			return map.put(key, corrected);
		} else if (Float.class.isAssignableFrom(cls)) {
			@SuppressWarnings("unchecked")
			V corrected = (V) Float.valueOf(value.floatValue());
			return map.put(key, corrected);
		} else if (Integer.class.isAssignableFrom(cls)) {
			@SuppressWarnings("unchecked")
			V corrected = (V) Integer.valueOf(value.intValue());
			return map.put(key, corrected);
		} else {
			throw new UnsupportedOperationException("NumberMap not implemented for " + cls.getName());
		}
	}
}