package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.3
 * @version 0.3.5.1
 * @author Innoxia
 */
public class CharactersPresentDialogue {

	public static String menuContent;
	public static String menuTitle;
	public static NPC characterViewed = null;
	
	private static NPC targetedCharacterForSex;
	private static NPC companionCharacter;
	
	public static void resetContent(GameCharacter characterViewed) {
		if(characterViewed==null) {
			CharactersPresentDialogue.characterViewed = Main.game.getCharactersPresent().get(0);
		} else {
			CharactersPresentDialogue.characterViewed = (NPC) characterViewed;
		}
		menuTitle = "Characters Present ("+Util.capitaliseSentence(CharactersPresentDialogue.characterViewed.getName(true))+")";
		menuContent = ((NPC) CharactersPresentDialogue.characterViewed).getCharacterInformationScreen(true);

		if(Main.game.getPlayer().hasCompanion(CharactersPresentDialogue.characterViewed)) {
			if(CharactersPresentDialogue.characterViewed.isSlave() && CharactersPresentDialogue.characterViewed.getOwner().isPlayer()) {
				SlaveDialogue.initDialogue((NPC) CharactersPresentDialogue.characterViewed, true);
			} else {
				OccupantDialogue.initDialogue((NPC) CharactersPresentDialogue.characterViewed, false, true);
			}
			CompanionManagement.initManagement(MENU, 2, CharactersPresentDialogue.characterViewed);
		}
		
//		Main.game.setActiveNPC(characterViewed);
		targetedCharacterForSex = (NPC) CharactersPresentDialogue.characterViewed;

		if(Main.game.getPlayer().getCompanions().size()>1) {
			companionCharacter = (NPC) Main.game.getPlayer().getMainCompanion();
			if(Objects.equals(getCompanionCharacter(), targetedCharacterForSex)) {
				companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
			}
		} else {
			companionCharacter = null;
		}
	}

	private static NPC getCharacterViewed() {
		return characterViewed;
	}
	
	private static NPC getTargetedCharacterForSex() {
		if(!Main.game.getCharactersPresent().contains(targetedCharacterForSex)) {
			targetedCharacterForSex = (NPC) CharactersPresentDialogue.characterViewed;
			if(Objects.equals(getCompanionCharacter(), targetedCharacterForSex)) {
				if(Main.game.getPlayer().getCompanions().size()>1) {
					companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
				}
			}
		}
		return targetedCharacterForSex;
	}
	
	private static NPC getCompanionCharacter() {
		if(!Main.game.getCharactersPresent().contains(companionCharacter)) {
			if(Main.game.getPlayer().getCompanions().size()>1) {
				companionCharacter = (NPC) Main.game.getPlayer().getMainCompanion();
				if(Objects.equals(companionCharacter, targetedCharacterForSex)) {
					companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
				}
			} else {
				companionCharacter = null;
			}
		}
		return companionCharacter;
	}

	private static String getTextFilePath() {
		if(targetedCharacterForSex.isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}
	
	public static final DialogueNode MENU = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.CHARACTERS_PRESENT;
		}
		
		@Override
		public String getLabel() {
			return menuTitle;
		}

		@Override
		public String getContent() {
			return menuContent;
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getPlayer().hasCompanion(characterViewed)) {
				if(index == 0) {
					return "Characters";
				} else if(index == 1) {
					return UtilText.parse("[style.colourSex(Sex)]");
				} else if(index == 2) {
					return UtilText.parse("[style.colourCompanion(Manage)]");
				}
				
			} else {
				if(index == 0) {
					return "Characters";
				} else if(index == 1) {
					return "Sex";
				} else if(index == 2) {
					return "Manage";
				}
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			Collections.sort(charactersPresent, (c1, c2) -> Main.game.getPlayer().hasCompanion(c1)?1:0);
			
			if(responseTab==0) {
				if (index == 0) {
					return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							Main.mainController.openCharactersPresent();
						}
					};
					
				} else if (index <= charactersPresent.size()) {
					String title = "[npc.Name]";
					String description = "Take a detailed look at [npc.name].";
					
					if(charactersPresent.get(index - 1).equals(characterViewed)) {
						if(!charactersPresent.get(index - 1).isRaceConcealed() || charactersPresent.get(index - 1).isPlayerKnowsName()) {
							title = "[style.colourDisabled([npc.Name])]";
							description = "You are already looking at [npc.name]!";
						}else {
							title = "[style.colourDisabled(Unknown person)]";
							description = "You don't know what this person looks like!";
						}
							
						
					} else if(Main.game.getPlayer().hasCompanion(charactersPresent.get(index - 1))) {
						title = "[style.colourCompanion([npc.Name])]";
						description = "Take a detailed look at your [style.colourCompanion(companion)], [npc.name].";
					}
					
					return new Response(
							UtilText.parse(charactersPresent.get(index - 1), title),
							UtilText.parse(charactersPresent.get(index - 1), description),
							charactersPresent.get(index - 1).equals(characterViewed)?null:MENU) {
						@Override
						public void effects() {
							characterViewed = charactersPresent.get(index-1);
							menuTitle = "Characters Present ("+Util.capitaliseSentence(charactersPresent.get(index - 1).getName(true))+")";
							menuContent = ((NPC) charactersPresent.get(index - 1)).getCharacterInformationScreen(true);
						}
					};
					
				} else {
					return null;
				}
				
			} else if (responseTab==1 && Main.game.getPlayer().hasCompanion(characterViewed)) {
				if (index == 0) {
					return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							Main.mainController.openCharactersPresent();
						}
					};
				} 
				if(index>0 && index<5 && !characterViewed.isCompanionAvailableForSex(true)) {
					if(index==1) {
						return new Response("Sex", characterViewed.getCompanionSexRejectionReason(true), null);
					}
					return null;
				}
				if(index>5 && index<10 && !characterViewed.isCompanionAvailableForSex(false)) {
					if(index==6) {
						return new Response("Submissive Sex", characterViewed.getCompanionSexRejectionReason(false), null);
					}
					return null;
				}
				if(characterViewed.isSlave() && characterViewed.getOwner().isPlayer()) {
					return SlaveDialogue.SLAVE_START.getResponse(responseTab, index);
				} else {
					return OccupantDialogue.OCCUPANT_START.getResponse(responseTab, index);
				}
				
			} else if(responseTab==2 && Main.game.getPlayer().hasCompanion(characterViewed)) {
				return CompanionManagement.getManagementResponses(index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Step back", "", true) {
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().size()>2) {
				List<GameCharacter> parsingCharacters = new ArrayList<>(Main.sex.getAllParticipants());
				parsingCharacters.remove(Main.game.getPlayer());
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_THREESOME", parsingCharacters);
				
			} else if(Main.sex.getNumberOfOrgasms(getCharacterViewed()) >= getCharacterViewed().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX", getTargetedCharacterForSex());
				
			} else {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_NO_ORGASM", getTargetedCharacterForSex());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Decide what to do next.", AFTER_SEX) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.setActiveNPC(null);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	

	public static final DialogueNode PERKS = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.CHARACTERS_PRESENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(getCharacterViewed(), "[npc.NamePos] Perk Tree");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parse(characterViewed,
					"<details>"
							+ "<summary>[style.boldPerk(Perk & Trait Information)]</summary>"
							+ "[style.colourPerk(Perks)] (circular icons) apply permanent boosts to [npc.namePos] attributes.<br/>"
							+ "[style.colourPerk(Traits)] (square icons) provide unique effects for [npc.name]."
								+ " Unlike perks, <b>traits will have no effect on [npc.name] until they're slotted into [npc.her] 'Active Traits' bar</b>.<br/>"
							+ "Perks require perk points to unlock. [npc.Name] earns one perk point each time [npc.she] levels up, and earns an extra two perk points every five levels.<br/><br/>"
							+ "In addition to the perks that can be purchased via perk points, there are also several special, hidden perks that are unlocked via special events."
					+ "</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(characterViewed, true));
			
			UtilText.nodeContentSB.append("</div>");
			
			if(!characterViewed.isElemental() && !characterViewed.isDoll()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'>"
							+ "<i>Please note that this perk tree is a work-in-progress. This is not the final version, and is just a proof of concept!</i>"
						+ "</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("Perk Tree", UtilText.parse(characterViewed, "You are already assigning [npc.namePos] perk points."), null);
				
			} else if(index==7) {
				return new Response("Reset perks", "Reset all of [npc.namePos] perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)", PERKS) {
					@Override
					public void effects() {
						characterViewed.resetPerksMap(false, false);
					}
				};
			}
			
			return MENU.getResponse(responseTab, index);
		}
	};
}
