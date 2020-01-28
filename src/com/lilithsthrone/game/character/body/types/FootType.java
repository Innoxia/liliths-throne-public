package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.10
 * @version 0.3.1
 * @author Innoxia
 */
public class FootType {
	
	public static AbstractFootType HUMANOID = new AbstractFootType("humanoid",
			"foot",
			"feet",
			Util.newArrayListOfValues("masculine"),
			Util.newArrayListOfValues("feminine", "soft", "delicate", "slender"),
			"toe",
			"toes",
			Util.newArrayListOfValues("masculine"),
			Util.newArrayListOfValues("feminine", "soft", "delicate", "slender"),
			"footjob",
			"[npc.SheHasFull] human-like feet.",
			Util.newArrayListOfValues(FootStructure.PLANTIGRADE)) {
	};

	public static AbstractFootType PAWS = new AbstractFootType("paw-like",
			"paw",
			"paws",
			Util.newArrayListOfValues("masculine","padded"),
			Util.newArrayListOfValues("feminine", "soft", "padded", "delicate", "slender"),
			"toe",
			"toes",
			Util.newArrayListOfValues("masculine", "padded"),
			Util.newArrayListOfValues("feminine", "soft", "padded", "delicate", "slender"),
			"footjob",
			"[npc.SheHasFull] paw-like feet.",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
	};

	public static AbstractFootType HOOFS = new AbstractFootType("hoof-like",
			"hoof",
			"hoofs",
			Util.newArrayListOfValues("masculine","hard"),
			Util.newArrayListOfValues("feminine", "delicate", "hard"),
			"hoof",
			"hoofs",
			Util.newArrayListOfValues("masculine", "hard"),
			Util.newArrayListOfValues("feminine", "hard", "delicate"),
			"hoofjob",
			"[npc.SheHasFull] hoofs in place of feet.",
			Util.newArrayListOfValues(
					FootStructure.UNGULIGRADE)) {
	};

	public static AbstractFootType TALONS = new AbstractFootType("bird-like",
			"talon",
			"talons",
			Util.newArrayListOfValues("masculine","clawed"),
			Util.newArrayListOfValues("feminine", "clawed", "slender"),
			"claw",
			"claws",
			Util.newArrayListOfValues("masculine", "sharp"),
			Util.newArrayListOfValues("feminine", "sharp", "slender"),
			"clawjob",
			"[npc.SheHasFull] bird-like talons in place of feet.",
			Util.newArrayListOfValues(
					FootStructure.DIGITIGRADE)) {
	};
	
}
