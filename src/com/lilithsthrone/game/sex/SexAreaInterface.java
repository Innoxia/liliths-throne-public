package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.7
 * @version 0.4
 * @author Innoxia
 */
public interface SexAreaInterface {
	
	public boolean isOrifice();
	
	public CoverableArea getRelatedCoverableArea(GameCharacter owner);
	
	public InventorySlot getRelatedInventorySlot(GameCharacter owner);

	public default String getName(GameCharacter owner) {
		return getName(owner, false);
	}
	
	/**
	 * @param owner The owner of this sex area.
	 * @param standardName true if you want a non-random standard name of this area.
	 * @return The name of this sex area.
	 */
	public String getName(GameCharacter owner, boolean standardName);
	
	public boolean isFree(GameCharacter owner);

	public default boolean isPenetration() {
		return !isOrifice();
	}
	
	public default boolean isPlural() {
		return false;
	}
	
	public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea);
	

	public default boolean isCharacterInanimate(GameCharacter character) {
		return character!=null
				&& Main.game.isInSex()
				&& Main.sex.isCharacterImmobilised(character)
				&& Main.sex.isCharacterInanimateFromImmobilisation(character);
	}
}
