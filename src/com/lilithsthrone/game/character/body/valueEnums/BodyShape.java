package com.lilithsthrone.game.character.body.valueEnums;
import java.util.List;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

import javafx.scene.paint.Color;

/**
 * @since 0.1.83
 * @version 0.2.8
 * @author Innoxia
 */
public enum BodyShape {
	
	/*
	 * Ectomorph: Lean and long, no muscle
	 * Endomorph: Big, high body fat
	 * Mesomorph: Muscular and well-built
	 */
	
	// BodySize == ZERO_SKINNY
	SKINNY_SOFT("gaunt", BodySize.ZERO_SKINNY, Muscle.ZERO_SOFT, Util.newArrayListOfValues("slender", "skinny")),
	SKINNY_LIGHTLY_MUSCLED("petite", BodySize.ZERO_SKINNY, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("slender", "skinny")),
	SKINNY_TONED("willowy", BodySize.ZERO_SKINNY, Muscle.TWO_TONED, Util.newArrayListOfValues("slender")),
	SKINNY_MUSCULAR("lean", BodySize.ZERO_SKINNY, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("slender", "toned")),
	SKINNY_RIPPED("gymnastic", BodySize.ZERO_SKINNY, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("slender", "toned")),

	// BodySize == ONE_SLENDER
	SLENDER_SOFT("slim", BodySize.ONE_SLENDER, Muscle.ZERO_SOFT, Util.newArrayListOfValues("slim", "slender")),
	SLENDER_LIGHTLY_MUSCLED("thin", BodySize.ONE_SLENDER, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("slim", "slender")),
	SLENDER_TONED("spry", BodySize.ONE_SLENDER, Muscle.TWO_TONED, Util.newArrayListOfValues("slim", "slender", "toned")),
	SLENDER_MUSCULAR("lithe", BodySize.ONE_SLENDER, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("slim", "toned")),
	SLENDER_RIPPED("aerobicised", BodySize.ONE_SLENDER, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("slim", "toned")),
	
	// BodySize == TWO_AVERAGE
	AVERAGE_SOFT("chubby", BodySize.TWO_AVERAGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("chubby", "soft")),
	AVERAGE_LIGHTLY_MUSCLED("average", BodySize.TWO_AVERAGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("slightly toned")),
	AVERAGE_TONED("healthy", BodySize.TWO_AVERAGE, Muscle.TWO_TONED, Util.newArrayListOfValues("toned")),
	AVERAGE_MUSCULAR("fit", BodySize.TWO_AVERAGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("toned", "muscular")),
	AVERAGE_RIPPED("athletic", BodySize.TWO_AVERAGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("toned", "muscular", "strong")),
	
	// BodySize == THREE_LARGE
	LARGE_SOFT("fat", BodySize.THREE_LARGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("chubby", "soft", "fat")),
	LARGE_LIGHTLY_MUSCLED("plump", BodySize.THREE_LARGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("chubby", "soft", "plump")),
	LARGE_TONED("burly", BodySize.THREE_LARGE, Muscle.TWO_TONED, Util.newArrayListOfValues("large", "strong")),
	LARGE_MUSCULAR("powerful", BodySize.THREE_LARGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("large", "muscular", "strong")),
	LARGE_RIPPED("buff", BodySize.THREE_LARGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("huge", "muscular", "strong")),
	
	// BodySize == FOUR_HUGE
	HUGE_SOFT("obese", BodySize.FOUR_HUGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("chubby", "soft", "fat")),
	HUGE_LIGHTLY_MUSCLED("chunky", BodySize.FOUR_HUGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("chubby", "soft", "plump")),
	HUGE_TONED("robust", BodySize.FOUR_HUGE, Muscle.TWO_TONED, Util.newArrayListOfValues("large", "strong")),
	HUGE_MUSCULAR("thickset", BodySize.FOUR_HUGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("large", "muscular", "strong")),
	HUGE_RIPPED("jacked", BodySize.FOUR_HUGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("huge", "muscular", "strong"));
	
	private String name;
	private List<String> limbDescriptors;
	private BodySize relatedBodySize;
	private Muscle relatedMuscle;
	
	private BodyShape(String name, BodySize relatedBodySize, Muscle relatedMuscle, List<String> limbDescriptors) {
		this.name = name;
		this.relatedBodySize = relatedBodySize;
		this.relatedMuscle = relatedMuscle;
		this.limbDescriptors = limbDescriptors;
	}

	public String getName(boolean withDeterminer) {
		if(withDeterminer) {
			return UtilText.generateSingularDeterminer(name) + " " + name;
		} else {
			return name;
		}
	}

	public BodySize getRelatedBodySize() {
		return relatedBodySize;
	}

	public Muscle getRelatedMuscle() {
		return relatedMuscle;
	}
	
	public static BodyShape valueOf(Muscle muscle, BodySize bodySize) {
		for(BodyShape bs : BodyShape.values()) {
			if(muscle == bs.getRelatedMuscle() && bodySize == bs.getRelatedBodySize()) {
				return bs;
			}
		}
		return AVERAGE_LIGHTLY_MUSCLED;
	}
	
	public Color getDerivedColor() {
		return Util.midpointColor(relatedBodySize.getColour().getColor(), relatedMuscle.getColour().getColor());
	}
	
	public String toWebHexStringColour() {
		return Util.toWebHexString(getDerivedColor());
	}

	public List<String> getLimbDescriptors() {
		return limbDescriptors;
	}
}
