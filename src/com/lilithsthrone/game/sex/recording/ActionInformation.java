package com.lilithsthrone.game.sex.recording;

import java.util.List;

/**
 * @since 0.4.11.4
 * @version 0.4.11.4
 * @author Innoxia
 */
public class ActionInformation {
	
	// Kate her herHim she
	// You your 
	
	/** The index at which this action was performed during a complete turn in sex. */
	private int turnIndex;
	
	/** The ID of the character who performed this action. */
	private String performerId;

	/** The IDs of all of characters who need to be parsed during this action. Index 0 will typically be the targeted character for this action, but that shouldn't matter. */
	private List<String> targetIds;

	/** The partially-parsed String of this action's title. */
	private String actionTitle;

	/** The partially-parsed String of this action's description. */
	private String actionDescription;
}
