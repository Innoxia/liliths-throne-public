package com.lilithsthrone.game.character;

/**
 * @since 0.2.9
 * @version 0.2.9
 * @author Innoxia
 */
public class SexCount {
	
	private int sexConsensualCount;
	private int sexAsSubCount;
	private int sexAsDomCount;
	
	public SexCount() {
		sexConsensualCount = 0;
		sexAsSubCount = 0;
		sexAsDomCount = 0;
	}
	
	public int getSexConsensualCount() {
		return sexConsensualCount;
	}
	public void setSexConsensualCount(int sexConsensualCount) {
		this.sexConsensualCount = sexConsensualCount;
	}
	public int getSexAsSubCount() {
		return sexAsSubCount;
	}
	public void setSexAsSubCount(int sexAsSubCount) {
		this.sexAsSubCount = sexAsSubCount;
	}
	public int getSexAsDomCount() {
		return sexAsDomCount;
	}
	public void setSexAsDomCount(int sexAsDomCount) {
		this.sexAsDomCount = sexAsDomCount;
	}
	public int getTotalTimesHadSex() {
		return getSexAsDomCount() + getSexAsSubCount();
	}
}
