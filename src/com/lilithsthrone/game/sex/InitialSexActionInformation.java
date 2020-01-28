package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class InitialSexActionInformation {
	
	private GameCharacter performer;
	private GameCharacter target;
	private SexActionInterface sexAction;
	private boolean appendDescription;
	private boolean appendEffects;
	
	public InitialSexActionInformation(GameCharacter performer, GameCharacter target, SexActionInterface sexAction, boolean appendDescription, boolean appendEffects) {
		this.performer = performer;
		this.target = target;
		this.sexAction = sexAction;
		this.appendDescription = appendDescription;
		this.appendEffects = appendEffects;
	}

	public GameCharacter getPerformer() {
		return performer;
	}

	public GameCharacter getTarget() {
		return target;
	}

	public SexActionInterface getSexAction() {
		return sexAction;
	}

	public boolean isAppendDescription() {
		return appendDescription;
	}

	public boolean isAppendEffects() {
		return appendEffects;
	}
	
}
