package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * Used in Pathing.
 * 
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public enum MapTravelType {

	WALK_SAFE("Walk (safest)",
			"Use the safest path to your destination.",
			"Click once on the map to mark your desired end point, then click that end point again to travel there. You can queue up waypoints by holding shift while clicking.",
			Colour.GENERIC_MINOR_GOOD) {
				@Override
				public boolean isAvailable(GameCharacter character) {
					return true;
				}

				@Override
				public String getUnavailablilityDescription(GameCharacter character) {
					return "";
				}
			},
	
	WALK_DANGEROUS("Walk (fastest)",
			"Use the fastest path to your destination.",
			"Click once on the map to mark your desired end point, then click that end point again to travel there. You can queue up waypoints by holding shift while clicking.",
			Colour.GENERIC_MINOR_BAD) {
				@Override
				public boolean isAvailable(GameCharacter character) {
					return true;
				}

				@Override
				public String getUnavailablilityDescription(GameCharacter character) {
					return "";
				}
			},
	
	FLYING("Fly",
			"Fly to your destination.",
			"Click once on the map to mark your desired end point, then click that end point again to travel there.",
			Colour.SPELL_SCHOOL_AIR) {
				@Override
				public boolean isAvailable(GameCharacter character) {
					return character.isPartyAbleToFly();
				}

				@Override
				public String getUnavailablilityDescription(GameCharacter character) {
					if(!character.isAbleToFly()) {
						return "You are not able to fly!";
					}
					return "Not all of your companions are able to fly!";
				}
			},
	
	TELEPORT("Teleport",
			"Teleport to your destination.",
			"Click once on the map to mark your desired end point, then click that end point again to travel there.",
			Colour.SPELL_SCHOOL_ARCANE) {
				@Override
				public boolean isAvailable(GameCharacter character) {
					return (character.isAbleToTeleport() && character.getMana()>=Spell.TELEPORT.getModifiedCost(character)) || Main.game.isDebugMode();
				}

				@Override
				public String getUnavailablilityDescription(GameCharacter character) {
					if(!character.isAbleToTeleport()) {
						return character.getUnableToTeleportDescription();
					}
					return "You require at least "+Spell.TELEPORT.getModifiedCost(character)+" aura to cast this spell!";
				}
			};
	
	private String name;
	private String description;
	private String useInstructions;
	private Colour colour;
	
	private MapTravelType(String name, String description, String useInstructions, Colour colour) {
		this.name = name;
		this.description = description;
		this.useInstructions = useInstructions;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getUseInstructions() {
		return useInstructions;
	}

	public Colour getColour() {
		return colour;
	}
	
	public abstract boolean isAvailable(GameCharacter character);

	public abstract String getUnavailablilityDescription(GameCharacter character);
}
