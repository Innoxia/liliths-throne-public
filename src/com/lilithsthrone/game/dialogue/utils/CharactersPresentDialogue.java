package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMChair;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.3
 * @version 0.2.10
 * @author Innoxia
 */
public class CharactersPresentDialogue {

	public static String menuContent, menuTitle;
	public static GameCharacter characterViewed = null;

	public static void resetContent(GameCharacter characterViewed) {
		if(characterViewed==null) {
			CharactersPresentDialogue.characterViewed = Main.game.getCharactersPresent().get(0);
		} else {
			CharactersPresentDialogue.characterViewed = characterViewed;
		}
		menuTitle = "Characters Present ("+Util.capitaliseSentence(CharactersPresentDialogue.characterViewed.getName())+")";
		menuContent = ((NPC) CharactersPresentDialogue.characterViewed).getCharacterInformationScreen();
	}
	
	private static boolean isCompanionSexPublic() {
		return Main.game.getPlayer().getLocationPlace().isPopulated()
				&& Main.game.getPlayer().getLocationPlace().getPlaceType()!=PlaceType.WATERING_HOLE_SEATING_AREA
				&& Main.game.getPlayer().getLocationPlace().getPlaceType()!=PlaceType.WATERING_HOLE_TOILETS;
	}

	private static boolean isSittingSex() {
		return Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.WATERING_HOLE_SEATING_AREA
				|| Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.WATERING_HOLE_VIP_AREA;
	}
	
	
	public static final DialogueNodeOld MENU = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

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
			java.util.Collections.sort(charactersPresent, (c1, c2) -> Main.game.getPlayer().hasCompanion(c1)?1:0);
			
			if(responseTab==0) {
				
				if (index == 0) {
					return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
						@Override
						public void effects() {
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
							charactersPresent.get(index - 1).equals(characterViewed)?null:MENU){
						@Override
						public void effects() {
							characterViewed = charactersPresent.get(index-1);
							menuTitle = "Characters Present ("+Util.capitaliseSentence(charactersPresent.get(index - 1).getName())+")";
							menuContent = ((NPC) charactersPresent.get(index - 1)).getCharacterInformationScreen();
						}
					};
					
				} else {
					return null;
				}
				
			} else if (responseTab==1 && Main.game.getPlayer().hasCompanion(characterViewed)){
				if (index == 0) {
					return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
						@Override
						public void effects() {
							Main.mainController.openCharactersPresent();
						}
					};
					
				} else if (index == 1) { //TODO improve descriptions and affection hit from rape
					if(Main.game.isNonConEnabled() && !((NPC) characterViewed).isAttractedTo(Main.game.getPlayer())) {
						if(!characterViewed.isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
							return new Response("Rape", characterViewed.getCompanionSexRejectionReason(true), null);
							
						} else {
							return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
									false, false,
									isSittingSex()
										? new SMChair(
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM)),
												Util.newHashMapOfValues(new Value<>(characterViewed, SexPositionSlot.CHAIR_TOP))) {
											@Override
											public boolean isPublicSex() {
												return isCompanionSexPublic();
											}
										}
										: new SMStanding(
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(characterViewed, SexPositionSlot.STANDING_SUBMISSIVE))) {
											@Override
											public boolean isPublicSex() {
												return isCompanionSexPublic();
											}
										},
									null,
									AFTER_SEX, "<p>"
										+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
										+ " [npc.She] desperately tries to push you away, [npc.moaning],"
										+ " [npc.speech(No! Stop!)]"
									+ "</p>") {
								@Override
								public void effects() {
									Main.game.setActiveNPC((NPC) characterViewed);
									if(Main.game.getActiveNPC().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 10));
									} else {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						if(!characterViewed.isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
							return new Response("Sex", characterViewed.getCompanionSexRejectionReason(true), null);
							
						} else {
							return new ResponseSex("Sex", "Have sex with [npc.name].", 
									true, false,
									isSittingSex()
										? new SMChair(
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_BOTTOM)),
												Util.newHashMapOfValues(new Value<>(characterViewed, SexPositionSlot.CHAIR_TOP))) {
											@Override
											public boolean isPublicSex() {
												return isCompanionSexPublic();
											}
										}
										: new SMStanding(
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(characterViewed, SexPositionSlot.STANDING_SUBMISSIVE))) {
											@Override
											public boolean isPublicSex() {
												return isCompanionSexPublic();
											}
										},
									null,
									AFTER_SEX, "<p>"
										+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
										+ " [npc.She] desperately leans into you, [npc.moaning],"
										+ " [npc.speech(~Mmm!~ Yes!)]"
									+ "</p>") {
								@Override
								public void effects() {
									Main.game.setActiveNPC((NPC) characterViewed);
									Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
						}
					}
					
				} else if (index == 2) {
					if(!characterViewed.isCompanionAvailableForSex(false)) {
						return new Response("Submissive Sex", characterViewed.getCompanionSexRejectionReason(false), null);
						
					} else {
						if(((NPC) characterViewed).isAttractedTo(Main.game.getPlayer())) {
							return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
									true, true,
									isSittingSex()
										? new SMChair(
												Util.newHashMapOfValues(new Value<>(characterViewed, SexPositionSlot.CHAIR_BOTTOM)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP))) {
											@Override
											public boolean isPublicSex() {
												return isCompanionSexPublic();
											}
										}
										: new SMStanding(
												Util.newHashMapOfValues(new Value<>(characterViewed, SexPositionSlot.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
											@Override
											public boolean isPublicSex() {
												return isCompanionSexPublic();
											}
										},
									null,
									AFTER_SEX, "<p>"
										+ "Taking hold of [npc.namePos] [npc.arms], you take a step forwards, guiding [npc.her] [npc.hands] around your body as you press forwards into a passionate kiss."
										+ " [npc.She] eagerly pulls you into [npc.herHim], [npc.moaning],"
										+ " [npc.speech(Looking for some fun, hmm?)]"
									+ "</p>") {
								@Override
								public void effects() {
									Main.game.setActiveNPC((NPC) characterViewed);
									Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
							
						} else {
							return new Response("Submissive sex", "[npc.Name] is not too keen on having sex with you, so you'd need to be the dom...", null);
						}
					}
					
				}
				
			} else if (responseTab==2 && Main.game.getPlayer().hasCompanion(characterViewed)){
				if (index == 0) {
					return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
						@Override
						public void effects() {
							Main.mainController.openCharactersPresent();
						}
					};
					
				} else if(index==1) {
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("Inventory", "You're in the middle of something right now! (Can only be used when in a tile's default dialogue.)", null);
						
					} else {
						return new ResponseEffectsOnly("Inventory", "Manage [npc.namePos] inventory.") {
									@Override
									public void effects() {
										Main.mainController.openInventory((NPC) characterViewed, InventoryInteraction.FULL_MANAGEMENT);
									}
								};
					}
							
				} else if (index == 2) {
					
					if(!characterViewed.isAbleToSelfTransform()) {
						return new Response("Transformations", "Only demons and slimes can transform themselves on command...", null);
						
					} else if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("Transformations", "You're in the middle of something right now! (Can only be used when in a tile's default dialogue.)", null);
						
					} else {
						return new Response("Transformations",
								"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
								BodyChanging.BODY_CHANGING_CORE){
							@Override
							public void effects() {
								BodyChanging.setTarget(characterViewed);
							}
						};
					}
					
				} else if(index==5) {
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response(characterViewed instanceof Elemental?"Dispell":"Go Home", "You're in the middle of something right now! (Can only be used when in a tile's default dialogue.)", null);
						
					} else {
						if(charactersPresent.size()==1 || (charactersPresent.size()==2 && characterViewed.isElementalSummoned())) {
							return new ResponseEffectsOnly(characterViewed instanceof Elemental?"Dispell":"Go Home",
									characterViewed instanceof Elemental?"Dispell [npc.namePos] physical form, and return [npc.herHim] to your arcane aura.":"Tell [npc.name] to go home."){
								@Override
								public void effects() {
									if(characterViewed.isElementalSummoned()) {
										characterViewed.removeCompanion(characterViewed.getElemental());
										characterViewed.getElemental().returnToHome();
									}
									Main.game.getPlayer().removeCompanion(characterViewed);
									characterViewed.returnToHome();
									Main.mainController.openCharactersPresent();
								}
							};
						} else {
							return new Response(characterViewed instanceof Elemental?"Dispell":"Go Home",
									characterViewed instanceof Elemental?"Dispell [npc.namePos] physical form, and return [npc.herHim] to your arcane aura.":"Tell [npc.name] to go home.",
									MENU){
								@Override
								public void effects() {
									if(characterViewed.isElementalSummoned()) {
										characterViewed.removeCompanion(characterViewed.getElemental());
										characterViewed.getElemental().returnToHome();
									}
									Main.game.getPlayer().removeCompanion(characterViewed);
									characterViewed.returnToHome();
									
									Main.game.setResponseTab(0);
									characterViewed = charactersPresent.get(0);
									//no need for character conceal check since its for follower
									menuTitle = "Characters Present ("+Util.capitaliseSentence(charactersPresent.get(0).getName())+")";
									menuContent = ((NPC) charactersPresent.get(0)).getCharacterInformationScreen();
								}
							};
						}
					}
					
				} else if (index == 6) {
					return new Response("Perk Tree", "Assign [npc.namePos] perk points.", PERKS);
					
				} else if(index==10) {
					if(!characterViewed.isElementalSummoned()) {
						return new Response("Dispell Elemental", "[npc.Name] doesn't have an elemental summoned...", null);
						
					} else {
						if(!Main.game.isSavedDialogueNeutral()) {
							return new Response("Dispell Elemental", "You're in the middle of something right now! (Can only be used when in a tile's default dialogue.)", null);
							
						} else {
							return new Response("Dispell Elemental", "Tell [npc.name] to dispell [npc.her] elemental.", MENU){
								@Override
								public void effects() {
									characterViewed.removeCompanion(characterViewed.getElemental());
									characterViewed.getElemental().returnToHome();
								}
							};
						}
					}
				}
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(!Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a thankful sob as [npc.she] realises that you've finished."
						+ "</p>");
				
			} else {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
								+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
							+ "</p>");
				} else {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
								+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! I'm still horny!)]"
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Decide what to do next.", AFTER_SEX) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
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
	

	public static final DialogueNodeOld PERKS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.CHARACTERS_PRESENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterViewed, "[npc.NamePos] Perk Tree");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(characterViewed,
					"<div class='container-full-width' style='padding:8px;'>"
						+ "<span style='color:"+Colour.PERK.toWebHexString()+";'>Perks</span> (circular icons) apply permanent boosts to [npc.namePos] attributes.<br/>"
						+ "<span style='color:"+Colour.TRAIT.toWebHexString()+";'>Traits</span> (square icons) provide unique effects for [npc.name]."
							+ " Unlike perks, <b>traits will have no effect on [npc.name] until they're slotted into [npc.her] 'Active Traits' bar</b>.<br/>"
						+ "Perks require perk points to unlock. [npc.Name] earns one perk point each time [npc.she] levels up, and an extra two perk points every five levels."
					+ "</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
					+ "<h6 style='text-align:center;'>Active Traits</h6>"));

			UtilText.nodeContentSB.append(
					"<div id='OCCUPATION_" + characterViewed.getHistory().getAssociatedPerk()+ "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + Colour.TRAIT.toWebHexString() + ";'>"
						+ "<div class='square-button-content'>"+characterViewed.getHistory().getAssociatedPerk().getSVGString()+"</div>"
					+ "</div>");
			
			for(int i=0;i<GameCharacter.MAX_TRAITS;i++) {
				Perk p = null;
				if(i<characterViewed.getTraits().size()) {
					p = characterViewed.getTraits().get(i);
				}
				if(p!=null) {
					UtilText.nodeContentSB.append("<div id='TRAIT_" + p + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + Colour.TRAIT.toWebHexString() + ";'>"
							+ "<div class='square-button-content'>"+p.getSVGString()+"</div>"
							+ "</div>");
					
				} else {
					UtilText.nodeContentSB.append("<div id='TRAIT_" + i + "' class='square-button small' style='display:inline-block; float:none;'></div>");
					
				}
			}
			UtilText.nodeContentSB.append("</div>"
					+ "<div class='container-full-width' style='padding:8px; text-align:center;'>"
						+ "<i>Please note that this perk tree is a work-in-progress. This is not the final version, and is just a proof of concept!</i>"
					+ "</div>");
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(characterViewed));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("Perks", UtilText.parse(characterViewed, "You are already assigning [npc.namePos] perk points."), null);
				
			} else if(index==7) {
				return new Response("Reset perks", "Reset all of [npc.namePos] perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)", PERKS) {
					@Override
					public void effects() {
						characterViewed.resetPerksMap();
						characterViewed.setPerkPoints(characterViewed.getPerkPointsAtLevel(characterViewed.getLevel()));
						characterViewed.clearTraits();
					}
				};
			}
			
			return MENU.getResponse(responseTab, index);
		}
	};
}
