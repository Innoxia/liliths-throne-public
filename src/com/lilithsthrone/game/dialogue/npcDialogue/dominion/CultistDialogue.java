package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMCultistKneeling;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.88
 * @version 0.3.7.9
 * @author Innoxia
 */
public class CultistDialogue {

	private static NPC getCultist() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNode ENCOUNTER_START = new DialogueNode("A Witch Appears!", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Make your excuses and get away from this annoying cultist.", ENCOUNTER_START){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_START_LEAVE", getCultist()));
					}
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else if(index==2) {
				return new Response("Chapel", "Do as the cultist asks and follow her down a nearby back-alley. What could possibly go wrong?", ENCOUNTER_CHAPEL) {
					@Override
					public void effects() {
						// Pull up dress:
						getCultist().displaceClothingForAccess(CoverableArea.PENIS, null);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL = new DialogueNode("The Witch's Chapel", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "You're left with no choice but to fight!", getCultist(), Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), "You tell the succubus that you're not interested, and just as you expected, she moves to attack!"),
						new Value<>(getCultist(), "[npc.Name] readies her broomstick and shouts, [npc.speech(How <i>dare</i> you try to refuse my gift! I'll give it to you by force!)]")));
				
			} else if(index==2) {
				return new ResponseSex("Accept", "Drop to your knees and prepare to service her orally.",
						true, true,
						new SMCultistKneeling(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_ORAL_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_ORAL_SEX", getCultist())) {
					@Override
					public void effects() {
						// Remove seals so that player can get access to mouth:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.MOUTH, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.MOUTH, true);
							}
						}
					}
				};
				
			} else if(index == 3) {
				if(Main.game.getPlayer().hasVagina()) {
					return new ResponseSex("Offer Pussy", "Offer [npc.name] your pussy instead.", Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY),
							null, Fetish.FETISH_PREGNANCY.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMAltarMissionary(
									Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							},
							null,
							null,
							ENCOUNTER_CHAPEL_POST_VAGINAL_SEX,
							UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_VAGINAL_SEX", getCultist())) {
						@Override
						public void effects() {
							((Cultist)getCultist()).setRequestedAnal(false);
							
							// Remove seals so that player can get access to vagina:
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
								while (clothing != null) {
									clothing.setSealed(false);
									System.out.println(clothing.getName());
									clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
								}
							}
						}
					};
					
				} else {
					return new Response("Offer Pussy", "You'd need a vagina in order to offer it to [npc.name]...", null);
				}
				
			} else if(index==4) {
				return new ResponseSex("Offer Ass", "Offer [npc.name] your ass instead.", Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING),
						null, Fetish.FETISH_ANAL_RECEIVING.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_ANAL_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_ANAL_SEX", getCultist())) {
					@Override
					public void effects() {
						((Cultist)getCultist()).setRequestedAnal(true);
						
						// Remove seals so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_REPEAT = new DialogueNode("The Witch's Chapel", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Pull up dress:
			getCultist().displaceClothingForAccess(CoverableArea.PENIS, null);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_REPEAT", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENCOUNTER_CHAPEL.getResponse(0, index);
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_LEAVING = new DialogueNode("The Witch's Chapel", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_LEAVING", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Leave the chapel and head back out into the streets of Dominion.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
			
			} else if(index==10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away."
							+ "<br/>[style.italicsBad(This will permanently remove [npc.herHim] from the game!)]",
						ENCOUNTER_CHAPEL_LEAVING) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getCultist());
					}
				};
			
			} else {
				return null;
			}
		}
		
		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_COMBAT_VICTORY = new DialogueNode("The Witch's Chapel", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Colour colour = PresetColour.CLOTHING_BLACK;
						if(getCultist().getClothingInSlot(InventorySlot.TORSO_UNDER)!=null && getCultist().getClothingInSlot(InventorySlot.TORSO_UNDER).getColour(0)==PresetColour.CLOTHING_WHITE) {
							 colour = PresetColour.CLOTHING_WHITE;
						}
						
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", colour, PresetColour.CLOTHING_GOLD, colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", colour, PresetColour.CLOTHING_GOLD, colour, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
			
			} else if(index==2) {
				return new ResponseSex("Sex", "Decide against using [npc.namePos] broomstick to seal [npc.herHim] in place and just have normal, dominant sex with [npc.herHim] instead...",
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY_SEX", getCultist())) {
				};
			
			} else if(index == 3) {
				return new ResponseSex("Witch's Seal", "Use her broomstick to cast Witch's Seal on her.",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public Set<GameCharacter> getCharactersSealed() {
								return Util.newHashSetOfValues(getCultist());
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_VICTORY_BROOMSTICK_SEAL_SEX", getCultist())) {
				};
				
			} else if (index == 4) {
				return new Response("Full transformations",
						"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getCultist());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_COMBAT_LOSS = new DialogueNode("The Witch's Chapel", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Witch's Toy", "You're completely immobilised, and can do nothing as the witch prepares to use you as her toy.",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(getCultist(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public Set<GameCharacter> getCharactersSealed() {
								return Util.newHashSetOfValues(Main.game.getPlayer());
							}
						},
						null,
						null,
						ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_COMBAT_LOSS_SEX", getCultist())) {
					@Override
					public void effects() {
						((Cultist)getCultist()).setRequestedAnal(false);
						
						// Remove seals so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							}
						}
						// Remove seals so that player can get access to anus:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_ORAL_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name] has had enough of receiving oral sex from you...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_ORAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_VAGINAL_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name] has had enough of fucking your pussy...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_VAGINAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_ANAL_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name] has had enough of fucking your ass...");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_ANAL_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "[npc.Name] has had enough of fucking you...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
					@Override
					public DialogueNode getNextDialogue(){
						return Main.game.getDefaultDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_DOM_SEX = new DialogueNode("Post-sex", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCultist(), "You've had enough of fucking [npc.name]...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_DOM_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX = new DialogueNode("Post-sex", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/cultist", "ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX", getCultist());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
