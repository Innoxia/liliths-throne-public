package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;

/**
 * @since 0.2.7
 * @version 0.2.7
 * @author Innoxia
 */
public interface SexAreaInterface {
	
	public boolean isOrifice();
	
	public CoverableArea getRelatedCoverableArea();

	public String getName(GameCharacter owner);
	
	public boolean isFree(GameCharacter owner);

	public default boolean isPenetration() {
		return !isOrifice();
	}
	
	public default boolean isPlural() {
		return false;
	}
	
}
