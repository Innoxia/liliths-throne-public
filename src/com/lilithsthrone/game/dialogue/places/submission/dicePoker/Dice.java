package com.lilithsthrone.game.dialogue.places.submission.dicePoker;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class Dice {
	
	private DiceFace face;
	private Map<DiceFace, Float> diceBias;
	
	public Dice() {
		face = DiceFace.SIX;
		diceBias = new HashMap<>();
		for(DiceFace face : DiceFace.values()) {
			diceBias.put(face, 1f);
		}
	}
	
	public Dice(Map<DiceFace, Float> diceBias) {
		this();
		for(Entry<DiceFace, Float> entry : diceBias.entrySet()) {
			this.diceBias.put(entry.getKey(), entry.getValue());
		}
	}

	public void roll() {
		face = Util.getRandomObjectFromWeightedFloatMap(diceBias);
	}
	
	public DiceFace getFace() {
		return face;
	}
	
	public void setFace(DiceFace face) {
		this.face = face;
	}
	
	public Map<DiceFace, Float> getDiceBias() {
		return diceBias;
	}
}
