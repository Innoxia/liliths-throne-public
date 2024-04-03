package com.lilithsthrone.game.sex;

/**
 * @since 0.4
 * @version 0.4.9.1
 * @author Innoxia
 */
public enum ImmobilisationType {

	/** Generic bondage type, representing being tied up with ropes. */
	ROPE(false),

	/** Generic bondage type, representing being tied up with metallic chains. */
	CHAINS(false),
	
	/** From the spell granted by the witch's broom. */
	WITCH_SEAL(false),
	
	/** Wrapped up in webbing, typically from a spider-morph's spinneret. */
	COCOON(false),
	
	/** Held in place via tentacles. */
	TENTACLE_RESTRICTION(false),

	/** Held in place via a tail, typically from a lamia. */
	TAIL_CONSTRICTION(false),

	/** Commanded to remain motionless, for dolls. */
	COMMAND(true),
	
	/** Asleep (for characters with the HEAVY_SLEEPER perk). */
	SLEEP(true);
	
	private boolean silence;
	
	private ImmobilisationType(boolean silence) {
		this.silence = silence;
	}

	public boolean isSilence() {
		return silence;
	}
}
