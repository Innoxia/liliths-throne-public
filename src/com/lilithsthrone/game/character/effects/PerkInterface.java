package com.lilithsthrone.game.character.effects;

import java.util.HashMap;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public interface PerkInterface {

	boolean isAvailable(GameCharacter character);

	boolean isAvailable(GameCharacter character, List<PerkInterface> additionalPerks);

	List<String> getPerkRequirements(GameCharacter character, List<PerkInterface> additionalPerks);

	String getName(GameCharacter owner);

	String getDescription(GameCharacter target);

	List<String> getModifiersAsStringList();

	HashMap<Attribute, Integer> getAttributeModifiers();

	String applyPerkGained(GameCharacter character);

	String applyPerkLost(GameCharacter character);

	PerkInterface getPreviousLevelPerk();

	PerkInterface getNextLevelPerk();

	CorruptionLevel getAssociatedCorruptionLevel();

	int getRenderingPriority();

	List<String> getExtraEffects();

	String getSVGString();

	PerkLevel getRequiredLevel();

	PerkCategory getPerkCategory();

}