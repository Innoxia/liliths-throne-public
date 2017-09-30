package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.3
 * @version 0.1.85
 * @author Innoxia
 */
public class CharactersPresentDialogue {

	private static String menuContent, menuTitle;
	public static GameCharacter characterViewed = null;

	public static void resetContent(GameCharacter characterViewed) {
		if(characterViewed==null) {
			CharactersPresentDialogue.characterViewed = Main.game.getCharactersPresent().get(0);
		} else {
			CharactersPresentDialogue.characterViewed = characterViewed;
		}
		menuTitle = "Characters present ("+Util.capitaliseSentence(CharactersPresentDialogue.characterViewed.getName())+")";
		menuContent = NPC.getCharacterInformationScreen((NPC) CharactersPresentDialogue.characterViewed);
	}
	
	public static final DialogueNodeOld MENU = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return menuTitle;
		}

		@Override
		public String getContent() {
			return menuContent;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
					@Override
					public void effects() {
						Main.mainController.openCharactersPresent();
					}
				};
				
			} else if (index <= Main.game.getCharactersPresent().size()) {
				return new Response(Util.capitaliseSentence(
						Main.game.getCharactersPresent().get(index - 1).getName()),
						"Take a detailed look at " + Main.game.getCharactersPresent().get(index - 1).getName("the") + ".",
						MENU){
					@Override
					public void effects() {
						characterViewed = Main.game.getCharactersPresent().get(index-1);
						
						menuTitle = "Characters present ("+Util.capitaliseSentence(Main.game.getCharactersPresent().get(index - 1).getName())+")";
						menuContent = NPC.getCharacterInformationScreen((NPC) Main.game.getCharactersPresent().get(index - 1));
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.CHARACTERS_PRESENT;
		}
	};
	
}
