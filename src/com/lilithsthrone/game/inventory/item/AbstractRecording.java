package com.lilithsthrone.game.inventory.item;

import java.io.Serializable;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

public class AbstractRecording extends AbstractItem implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	DialogueNodeOld dialogue;

	public AbstractRecording(AbstractItemType itemType, boolean consensual, boolean subHasEqualControl, SexManagerInterface sexManager,
			List<GameCharacter> spectators, DialogueNodeOld postSexDialogue, String sexStartDescription) {
		super(itemType);
		this.dialogue = Main.sexEngine.initialiseSex(consensual, subHasEqualControl, sexManager, spectators, postSexDialogue, sexStartDescription);
	}

	public String getUseDescription(GameCharacter user, GameCharacter target) {
		// FIXME this probably does not display well..
		Main.game.setContent(new Response("Recording", "", this.dialogue));

		return ItemType.getGenericUseDescription(user, target,
					"Here the recording stops. Your cheeks still burn with shame.",
					"You show the recording to [npc.name] while your cheaks burn with shame. A lustful look appears in [npc.her] eyes.",
					"Looking at the recording of you, [npc.name] starts masturbating.",
					"[npc.name] grins while showing you the recording. The recording shows you in an obscene sex scene, your cheeks burn crimson with shame.");
	}
}
