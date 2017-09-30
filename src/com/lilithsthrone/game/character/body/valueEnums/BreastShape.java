package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.86
 * @version 0.1.86
 * @author Innoxia
 */
public enum BreastShape {

	ROUND("round"),
	POINTY("pointy"),
	SIDE_SET("side-set"),
	WIDE("wide"),
	NARROW("narrow");
	
	private String descriptor;

	private BreastShape(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
	
	public static BreastShape getRandomBreastShape() {
		List<BreastShape> availableStyles = new ArrayList<>();
		
		for(BreastShape hs : BreastShape.values()) {
			availableStyles.add(hs);
		}
		
		return availableStyles.get(Util.random.nextInt(availableStyles.size()));
	}
}
