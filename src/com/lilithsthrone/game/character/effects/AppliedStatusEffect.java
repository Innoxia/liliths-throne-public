package com.lilithsthrone.game.character.effects;

/**
 * @since 0.3.8.2
 * @version 0.3.8.2
 * @author Innoxia
 */
public class AppliedStatusEffect {
	
	private AbstractStatusEffect effect;
	private long lastTimeAppliedEffect;
	private long secondsPassed;
	private int secondsRemaining;
	
	public AppliedStatusEffect(AbstractStatusEffect effect, long lastTimeAppliedEffect, long secondsPassed, int secondsRemaining) {
		this.effect = effect;
		this.lastTimeAppliedEffect = lastTimeAppliedEffect;
		this.secondsPassed = secondsPassed;
		this.secondsRemaining = secondsRemaining;
	}

	public AbstractStatusEffect getEffect() {
		return effect;
	}

	public long getLastTimeAppliedEffect() {
		return lastTimeAppliedEffect;
	}

	public void setLastTimeAppliedEffect(long lastTimeAppliedEffect) {
		this.lastTimeAppliedEffect = lastTimeAppliedEffect;
	}

	public long getSecondsPassed() {
		return secondsPassed;
	}
	public void setSecondsPassed(long secondsPassed) {
		this.secondsPassed = secondsPassed;
	}

	public int getSecondsRemaining() {
		return secondsRemaining;
	}
	
	public void setSecondsRemaining(int secondsRemaining) {
		this.secondsRemaining = secondsRemaining;
	}

	
}
