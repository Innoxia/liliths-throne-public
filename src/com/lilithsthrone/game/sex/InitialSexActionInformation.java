package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionManager;

/**
 * @since 0.3.1
 * @version 0.4.1
 * @author Innoxia
 */
public class InitialSexActionInformation {
	
	private boolean fromExternalFile;
	private GameCharacter performer;
	private GameCharacter target;
	private SexActionInterface sexAction;
	private boolean appendDescription;
	private boolean appendEffects;
	
	// For when loaded from an external file:

	private String conditional;
	private String performerId;
	private String targetId;
	private String sexActionId;
	private String appendDescriptionString;
	private String appendEffectsString;
	
	public InitialSexActionInformation(GameCharacter performer, GameCharacter target, SexActionInterface sexAction, boolean appendDescription, boolean appendEffects) {
		this.fromExternalFile = false;
		this.performer = performer;
		this.target = target;
		this.sexAction = sexAction;
		this.appendDescription = appendDescription;
		this.appendEffects = appendEffects;
	}
	
	public InitialSexActionInformation(String conditional, String performerId, String targetId, String sexActionId, String appendDescriptionString, String appendEffectsString) {
		this.fromExternalFile = true;
		this.conditional = conditional;
		this.performerId = performerId;
		this.targetId = targetId;
		this.sexActionId = sexActionId;
		this.appendDescriptionString = appendDescriptionString;
		this.appendEffectsString = appendEffectsString;
	}
	
	public boolean isConditionalMet() {
		return !fromExternalFile || Boolean.valueOf(UtilText.parse(conditional).trim());
	}

	public GameCharacter getPerformer() {
		if(fromExternalFile) {
			return UtilText.findFirstCharacterFromParserTarget(UtilText.parse(performerId).trim());
		}
		return performer;
	}

	public GameCharacter getTarget() {
		if(fromExternalFile) {
			return UtilText.findFirstCharacterFromParserTarget(UtilText.parse(targetId).trim());
		}
		return target;
	}

	public SexActionInterface getSexAction() {
		if(fromExternalFile) {
			return SexActionManager.getSexActionFromId(UtilText.parse(sexActionId).trim());
		}
		return sexAction;
	}

	public boolean isAppendDescription() {
		if(fromExternalFile) {
			return Boolean.valueOf(UtilText.parse(appendDescriptionString).trim());
		}
		return appendDescription;
	}

	public boolean isAppendEffects() {
		if(fromExternalFile) {
			return Boolean.valueOf(UtilText.parse(appendEffectsString).trim());
		}
		return appendEffects;
	}
	
}
